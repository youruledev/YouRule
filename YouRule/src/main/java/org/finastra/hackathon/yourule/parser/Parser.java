package org.finastra.hackathon.yourule.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.finastra.hackathon.yourule.loader.RuleAssociationData;
import org.finastra.hackathon.yourule.loader.RuleTypeData;
import org.finastra.hackathon.yourule.loader.WhereThenData;
import org.finastra.hackathon.yourule.model.Condition;
import org.finastra.hackathon.yourule.model.Rule;
import org.finastra.hackathon.yourule.model.RuleEvaluation;
import org.finastra.hackathon.yourule.model.RuleType;
import org.finastra.hackathon.yourule.model.StringsDef;

public class Parser {
	
	
	public List<RuleType> parse(List<RuleTypeData> rulesTypesData)
	{
		List<RuleType> retRuleTypes = new ArrayList<RuleType>();
		for(RuleTypeData ruleTypeDataElem: rulesTypesData)
		{	
			RuleType ruleType = parseRuleType(ruleTypeDataElem);
			if (ruleType != null)
			{
				retRuleTypes.add(ruleType);
			}	
		}
		return retRuleTypes;
	}

	private String parseRuleSubTypeID(RuleTypeData ruleTypeData)
	{
		String ruleSubTypeID =  ruleTypeData.getRuleSubTypeId();
		if ((ruleSubTypeID == null) || ruleSubTypeID.equals("null"))
		{
			ruleSubTypeID = "0";
		}
		return ruleSubTypeID;
	}

	
	
	private RuleType parseRuleType(RuleTypeData ruleTypeData) {
		
		String comment = StringsDef.RULE_TYPE_COMMENT;		
		String ruleTypeID = ruleTypeData.getRuleTypeId();		
		String ruleSubTypeID = parseRuleSubTypeID(ruleTypeData);
		String ruleTypeName = ruleTypeData.getRuleTypeName();
		String executionTimeStamp = ruleTypeData.getTimeStamp();	
		String actionResult = "NONE";
	    List<Rule> rules = new ArrayList<Rule>();
	    
		
		RuleType ruleType = null;
		List<RuleAssociationData>  ruleAssciationList = ruleTypeData.getRuleAssociations();
		//if (ruleAssciationList.size() == 0) //no rule were attached for this rule type
		//{
		//	ruleType = null;
		//}
		//else
		//{
			for(RuleAssociationData ruleAssociationDataElem:ruleAssciationList)
			{
				rules = parseRuleAssociation(ruleAssociationDataElem);
				
				//TODO: adjust when supporting ALLSET
				if (ruleAssociationDataElem.getResultAction() != null)
				{
					actionResult = ruleAssociationDataElem.getResultAction();
				}
				
			}
			
			//TODO: adjust when supporting ALLSET
			if (ruleTypeData.getEvaluateType().equals("FIRST"))
			{
				ruleType = new RuleType(comment, ruleTypeID, ruleSubTypeID, ruleTypeName, executionTimeStamp, actionResult, rules);
			}
		//}
		
	
		return ruleType;
	}

	
	private List<Rule> parseRuleAssociation(RuleAssociationData ruleAssociationDataElem) 
	{
		List<WhereThenData> whereThenList = parseExecutionScript(ruleAssociationDataElem.getExecutionScript());
		List<Rule> rules = new ArrayList<Rule> ();
		String []splitAction;
		
		if (whereThenList != null)
		{
			for (WhereThenData whereThenData:whereThenList)
			{
				RuleEvaluation ruleEval = new RuleEvaluation(true, whereThenData.getsWhere(), null, null);
				
				splitAction = whereThenData.getsThen().split(",");
				//splitAction[0] ruleUid - ***^114^NACHA_MP_MT_SEL_ALREADY_RAN
				//splitAction[1] rule action  - STOP
				//splitAction[2]   - NA
				//splitAction[3]   - 0
				
				Rule rule = new Rule (splitAction[0], splitAction[1], splitAction[0].equals(ruleAssociationDataElem.getResultRuleId()), ruleEval);
				
				rules.add(rule);
			}
		}
		
		return rules;
		
		
	}
	
	
	
	private List<WhereThenData> parseExecutionScript(String execScript)
	{
		if (execScript.startsWith("SELECT CASE  WHEN"))
		{
			execScript = execScript.substring(17); // length of "SELECT CASE  WHEN"
			return parseFirstExecutionScript(execScript);
		}
		else  if (execScript.startsWith("SELECT"))
		{
			return null;
		}
		
		return null;
	}
	
	private List<WhereThenData> parseFirstExecutionScript(String execScript)
	{
		
		/*
		 SELECT CASE 
		WHEN :P_ORIG_MSG_TYPE <> 'NACHA'
			AND :P_ORIG_MSG_TYPE LIKE 'NACHA' || '%'
			THEN '***^114^NACHA_MP_MT_SEL_ALREADY_RAN,STOP,NA,0'
		WHEN (
				:P_DBT_MOP = 'MYIBGDD'
				OR :P_CDT_MOP = 'MYIBGDD'
				OR :D_FILE_SRC = 'MYIBGDD'
				)
			AND :X_TRANSACTION_CD = '26'
			AND :GET_MULTI_FIELD_VAL = 'R00'
			THEN '***^114^MYIBG_DD_MP_ACK,NACHA_DD_ACK,NACHA_DD,1'
		WHEN (
				:P_DBT_MOP = 'MYIBGDD'
				OR :P_CDT_MOP = 'MYIBGDD'
				OR :D_FILE_SRC = 'MYIBGDD'
				)
			AND :X_TRANSACTION_CD = '26'
			AND :GET_MULTI_FIELD_VAL <> 'R00'
			THEN '***^114^MYIBG_DD_MP_REJECT,NACHA_DD_REJ,NACHA_DD,2'
			END
		FROM DUAL
		 */
		
		Scanner ruleScanner = new Scanner(execScript);
		
		ruleScanner.useDelimiter("WHEN");
		
		String exp;
		String[] splitRule;
		String splitRuleAction;
				
		List<WhereThenData> retList = new ArrayList<WhereThenData>();
		
		while (ruleScanner.hasNext()) 
		{
			exp = ruleScanner.next();

			splitRule = exp.split("THEN");
			// splitExp[0] - rawCondition - :P_ORIG_MSG_TYPE <> 'NACHA' AND :P_ORIG_MSG_TYPE LIKE 'NACHA' || '%'			
			// splitExp[1] - rulename and action - '***^114^NACHA_MP_MT_SEL_ALREADY_RAN,STOP,NA,0'
			
			splitRuleAction = splitRule[1];
			splitRuleAction.trim();
			splitRuleAction = splitRuleAction.substring(2); // removing first '
					
			if (splitRuleAction.indexOf("END FROM DUAL") > 0) // handling last statement
			{
				splitRuleAction = splitRuleAction.substring(0, splitRuleAction.indexOf("END FROM DUAL"));
				splitRuleAction.trim();
			}
			
			splitRuleAction = splitRuleAction.substring(0, splitRuleAction.length()-2); // removing last first '
			
			retList.add(new WhereThenData(splitRule[0], splitRuleAction));
		}
		
		return retList;
	}
	
	
	
	private void parseAllsetExecutionScript(String execScript)
	{
				
	}
	
	
	

}
