package org.finastra.hackathon.yourule.parser;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Arrays;
import java.util.Scanner;


import org.finastra.hackathon.yourule.loader.RuleAssociationData;
import org.finastra.hackathon.yourule.loader.RuleTypeData;
import org.finastra.hackathon.yourule.loader.WhereThenData;
import org.finastra.hackathon.yourule.model.Condition;
import org.finastra.hackathon.yourule.model.Rule;
import org.finastra.hackathon.yourule.model.RuleDetails;
import org.finastra.hackathon.yourule.model.RuleEvaluation;
import org.finastra.hackathon.yourule.model.RuleType;
import org.finastra.hackathon.yourule.model.StringsDef;

public class Parser {
	
	
	public List<RuleType> parse(List<RuleTypeData> rulesTypesData)
	{
		List<RuleType> retRuleTypes = new ArrayList<RuleType>();
		try{
		
		for(RuleTypeData ruleTypeDataElem: rulesTypesData)
		{	
			RuleType ruleType = parseRuleType(ruleTypeDataElem);
			if (ruleType != null)
			{
				retRuleTypes.add(ruleType);
			}	
		}
		}catch (Exception e){
			System.out.println("Error while parsing: " + e.getMessage());
			return null;
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
		
		String comment = null;		
		String ruleTypeID = ruleTypeData.getRuleTypeId();	
		String ruleSubTypeID = parseRuleSubTypeID(ruleTypeData);
		String ruleTypeName = ruleTypeData.getRuleTypeName();
		System.out.println("Parsing rule Type name: "+ ruleTypeName + " rule Type id: " + ruleTypeID);
		String executionTimeStamp = parseRuleTimeStamp(ruleTypeData);	
		String actionResult = "";
	    List<Rule> rules = new ArrayList<Rule>();
	    List<Rule> rulesFromOneAssoc;
	    
		RuleType ruleType = null;
		List<RuleAssociationData> ruleAssciationList = ruleTypeData
				.getRuleAssociations();

		for (RuleAssociationData ruleAssociationDataElem : ruleAssciationList) {
			rulesFromOneAssoc = parseRuleAssociation(ruleAssociationDataElem);
			rules.addAll(rulesFromOneAssoc);

			actionResult += ruleAssociationDataElem.getRuleResultActionList();

		}
		
		if (actionResult.equals(""))
			actionResult = "NONE";
		
		actionResult = actionResult.trim();
		if (actionResult.endsWith(","))
			actionResult = actionResult.substring(0, actionResult.length()-1);

		
		ruleType = new RuleType(comment, ruleTypeID, ruleSubTypeID,	ruleTypeName, executionTimeStamp, actionResult, rules);
		

		return ruleType;
	}


	private String parseRuleTimeStamp(RuleTypeData ruleTypeData) {
		String TimeStamp = ruleTypeData.getTimeStamp();
		TimeStamp = TimeStamp.replaceAll("\\n", "");
		TimeStamp = TimeStamp.replaceAll("\r", "");
		return TimeStamp;
	}

	private boolean parseIsUsingCacheResults(RuleAssociationData ruleAssociationData)
	{
		boolean isUsingCahceResults = true;
		String strIsUsingCacheResults = ruleAssociationData.getIsUsingCacheResults();
		strIsUsingCacheResults = strIsUsingCacheResults.replaceAll("\\n", "");
		strIsUsingCacheResults = strIsUsingCacheResults.replaceAll("\r", "");
		if (strIsUsingCacheResults.equals("false"))
		{
			isUsingCahceResults = false;
		}
		return isUsingCahceResults;
	}
	private Map<String,String> parseParamValueBinding(RuleAssociationData ruleAssociationData)
	{
		boolean isUsingCachedResults = parseIsUsingCacheResults(ruleAssociationData);
		Map<String,String> ParamValueBind;
		if (isUsingCachedResults == true)
		{
			ParamValueBind = parseParamValueBindingCached(ruleAssociationData);
		}
		else
		{
			ParamValueBind = parseParamValueBindingNotCached(ruleAssociationData);
		}
		return ParamValueBind;
	}
	
	
	private Map<String,String> parseParamValueBindingNotCached(RuleAssociationData ruleAssociationData) {
		
		//example for rawBinding: [[null, 12, X_INITG_PTY_ID, null, true], [null, 12, P_PMNT_SRC, null, true], [DIAS-CT-HV, 12, P_DBT_MOP, null, true], [null, 12, P_PMNT_SRC, null, false]]
		String rawBinding = ruleAssociationData.getNotCachedParamValueBinding();
		rawBinding = rawBinding.trim();
		Map<String,String> ParamValueBind= new HashMap<String,String>(); 
		int len  = rawBinding.length();
		if (rawBinding != null)
		{
			//remove first "["
			rawBinding = rawBinding.substring(1);

			
			//prepare list of strings, each represent one binding. example of a string: [null, 12, X_INITG_PTY_ID, null, true]
			//example2: [true, -7, ##IS_EMPTY##, IS_EMPTY((,[X_DBTR_ACCT]), true]
			List<String> BindingData = new ArrayList<String>();
			int indexOfNextOpeningBracket = rawBinding.indexOf("[");
			int indexOfNextClosingBracket = rawBinding.indexOf("]");
			while ((indexOfNextOpeningBracket != -1) && (indexOfNextClosingBracket != -1))
			{
				
				
				String tempNextBinding = rawBinding.substring(indexOfNextOpeningBracket+1, indexOfNextClosingBracket);
				boolean foundInnerBracket  = tempNextBinding.contains("[");//next binding contains inner "[]"
				while (foundInnerBracket)
				{
					int indexOfNextClosingBracketOld = indexOfNextClosingBracket;
					indexOfNextClosingBracket = indexOfNextClosingBracket + rawBinding.substring(indexOfNextClosingBracket+1).indexOf("]") + 1;
					tempNextBinding = rawBinding.substring(indexOfNextClosingBracketOld+1, indexOfNextClosingBracket);
					foundInnerBracket  = tempNextBinding.contains("[");
				}
				String nextBinding = rawBinding.substring(indexOfNextOpeningBracket+1, indexOfNextClosingBracket);
				
				BindingData.add(nextBinding);
				rawBinding = rawBinding.substring(indexOfNextClosingBracket+1);
				indexOfNextOpeningBracket = rawBinding.indexOf("[");
				indexOfNextClosingBracket = rawBinding.indexOf("]");
			}
			
			//Preapare map of Param-Value pairs
			int nextComma;
			int nextStartPos;
			String Value;
			String Param;
			int charLen = 1;
			int spaceLen = 1;
			for (String bindingDataElem:BindingData )
			{
				nextComma = bindingDataElem.indexOf(",");
				nextStartPos = 0;
				Value = bindingDataElem.substring(nextStartPos, nextComma);
				nextStartPos = bindingDataElem.indexOf(",", nextComma + charLen) + spaceLen;
				nextComma = bindingDataElem.indexOf(",", nextStartPos);
				Param = bindingDataElem.substring(nextStartPos, nextComma);
				ParamValueBind.put(Param.trim(), Value.trim());
				
			}
			
			
			
			
			
		}
		return ParamValueBind;
	}

	private Map<String,String> parseParamValueBindingCached(RuleAssociationData ruleAssociationData) 
	{
		//example for rawBinding (the binding is inside the "[]" at the end of string): [DAOPrules.executeRule()]: Executing the following rule, (rule type ID 1, name Department): 
		//SELECT CASE  WHEN    'Pacs_008' = 'Pain_009' THEN 'GR1^1^TEST1,TRF,0,0' WHEN   150.00 <= 100 THEN 'GR1^1^TEST2,TRF,0,1' WHEN 1 = 1 THEN 'GR1^1^GR1_DEPT,TRF,0,2' END FROM DUAL
		//[P_MSG_TYPE='Pacs_008', X_STTLM_AMT=150.00]
		String rawBinding = ruleAssociationData.getCachedParamValueBinding();
		rawBinding = rawBinding.trim();
		Map<String,String> ParamValueBind= new HashMap<String,String>(); 
		List<String> bindingElements = new ArrayList<String>();
		String strParamValue = null;
		int nextOpenBracket = rawBinding.indexOf("[");
		int startPos = -1;
		int nextComma;
		if (nextOpenBracket != -1)
		{
			nextOpenBracket = rawBinding.indexOf("[", nextOpenBracket + 1);
			while (nextOpenBracket != -1)
			{
				startPos = nextOpenBracket + 1;
				nextOpenBracket = rawBinding.indexOf("[", nextOpenBracket + 1);
			}
			
			//prepare list of bindingElements. eacch element in the form of param=value. example: P_MSG_TYPE='Pacs_008'
			nextComma = rawBinding.indexOf(",", startPos);
			while (nextComma != -1)
			{
				strParamValue = rawBinding.substring(startPos, nextComma);
				bindingElements.add(strParamValue);
				startPos = nextComma + 1;
				nextComma = rawBinding.indexOf(",", startPos);
				
			}
			int closeBracketPos = rawBinding.indexOf("]", startPos);
			strParamValue = rawBinding.substring(startPos, closeBracketPos);
			bindingElements.add(strParamValue);
			
			
			//fill Param-Value map
			int equalSignPos = 0;
			String Param;
			String Value;
			for (String bindingElem:bindingElements )
			{
				equalSignPos = bindingElem.indexOf("=");
				if (equalSignPos == -1)
				{
					System.out.println("rawBinding=" + rawBinding);
				}
				else
				{
					Param = bindingElem.substring(0, equalSignPos);
					Value = bindingElem.substring(equalSignPos, bindingElem.length());
					ParamValueBind.put(Param.trim(), Value.trim());
				}
				
				
			}

			
			
		}
		
		return ParamValueBind;
	}

	//private List<Rule> parseRuleAssociation(RuleAssociationData ruleAssociationDataElem) 
	//{
		//String rawBinding = parseParamValueBinding(ruleAssociationDataElem);
		//Condition finalCond1 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "a", "5", "=", "4");
		//Condition finalCond2 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "b", "5", "=", "5");
		//RuleEvaluation re1 = new RuleEvaluation(false, "a=5", rawBinding, finalCond1);
		//Rule ruleWith1Final = new Rule("business area rule name", "action1", false,   re1);
		//List<Rule> rules = new ArrayList<Rule>();
		//rules.add(ruleWith1Final);
		//rules.add(ruleWith1Final);

	
	private List<Rule> parseRuleAssociation(RuleAssociationData ruleAssociationDataElem) 
	{
		List<WhereThenData> whereThenList = parseExecutionScript(ruleAssociationDataElem.getExecutionScript());
		List<Rule> rules = new ArrayList<Rule> ();
		String []splitAction;
		Map<String,String> rawBindingPerRuleType = parseParamValueBinding(ruleAssociationDataElem);
		String rawBindingPerRule = null;
		
		if (whereThenList != null)
		{
			for (WhereThenData whereThenData:whereThenList)
			{
				rawBindingPerRule = parseParamValueBindingPerRule(rawBindingPerRuleType, whereThenData.getsWhere());
				RuleEvaluation ruleEval = new RuleEvaluation(true, whereThenData.getsWhere(), rawBindingPerRule, null);
				
				
				splitAction = whereThenData.getsThen().split(",");
				//splitAction[0] ruleUid - ***^114^NACHA_MP_MT_SEL_ALREADY_RAN
				//splitAction[1] rule action  - STOP
				//splitAction[2]   - NA
				//splitAction[3]   - 0
				
				//String []splitRuleUid = splitAction[0].split("\\^");
				
				String ruleResultRuleUid = ruleAssociationDataElem.getRuleResultRuleUidList();
				Rule rule = new Rule (splitAction[0].split("\\^")[2], splitAction[1], ruleResultRuleUid != null && ruleResultRuleUid.contains(splitAction[0]), ruleEval);
				
				rules.add(rule);
			}
		}

		return rules;
		
		
	}
	
	
	
	private String parseParamValueBindingPerRule(Map<String, String> rawBindingPerRuleType, String RawConsitions) {
		Map<String, String> rawBindingPerRule = new HashMap<String,String>(); 
		for (Map.Entry<String, String> rawBindingElem: rawBindingPerRuleType.entrySet())
		{
			if (RawConsitions.contains(rawBindingElem.getKey()))
			{
				rawBindingPerRule.put(rawBindingElem.getKey(), rawBindingElem.getValue());
			}
		}
		return rawBindingPerRule.toString();
	}

	private List<WhereThenData> parseExecutionScript(String execScript)
	{
		if (execScript.startsWith("SELECT CASE  WHEN"))
		{
			execScript = execScript.substring(17); // length of "SELECT CASE  WHEN"
			return parseFirstTypeExecutionScript(execScript);
		}
		else  if (execScript.startsWith("SELECT"))
		{
			return parseAllsetExecutionScript(execScript);
		}
		
		return null;
	}
	
	private List<WhereThenData> parseFirstTypeExecutionScript(String execScript)
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
	
	
	
	private List<WhereThenData> parseAllsetExecutionScript(String execScript)
	{
		
		/*
		 SELECT 'GR1^3^DIAS-CT-HV,GR1^DIAS-CT-HV,0,0'
	,0
FROM DUAL
WHERE :X_LCL_INSTRM_PRTRY IN (
		'STP'
		,'CCP'
		,'CCT'
		,'LOAN-TR'
		)

UNION ALL

SELECT 'GR1^3^DIAS-ONL-CT-HV,GR1^DIAS-ONL-CT-HV,0,1'
	,1
FROM DUAL
WHERE :P_PRODUCT_CD IN (
		'ABX'
		,'OLR'
		,'IOP'
		,'DEF_PROD'
		)

UNION ALL

SELECT 'GR1^3^DIAS_EQ _CT-HV,GR1^DIAS-EQ-CT-HV,0,2'
	,2
FROM DUAL
WHERE :X_STTLM_CCY = 'EUR'
	AND :X_CDTR_ACCT_IBAN IS NOT NULL
	AND :X_CHRG_BR IN (
		'SLEV'
		,'SHAR'
		)
	AND :X_LCL_INSTRM_PRTRY <> 'EMPTY'
	AND :P_ORIG_MSG_TYPE = 'Pain_001'

UNION ALL
		 */
		
		
		Scanner ruleScanner = new Scanner(execScript);
		
		ruleScanner.useDelimiter("UNION ALL");
		
		String exp;
		String[] splitRule;
		String splitRuleAction;
				
		List<WhereThenData> retList = new ArrayList<WhereThenData>();
		
		while (ruleScanner.hasNext()) 
		{
			exp = ruleScanner.next();

			splitRule = exp.split("WHERE");
			// splitExp[0] - rulename and action - SELECT 'GR1^3^DIAS-CT-HV,GR1^DIAS-CT-HV,0,0' ,0 FROM DUAL			
			// splitExp[1] - rawCondition  - :P_PRODUCT_CD IN ('ABX' ,'OLR' , 'IOP' ,'DEF_PROD' )
			
			splitRuleAction = splitRule[0];
			
			// removing first "SELECT " + lst of ",0 FROM DUAL"
			if (splitRuleAction.indexOf("SELECT") > -1)
			{
				splitRuleAction = splitRuleAction.substring("SELECT ".length(), splitRuleAction.lastIndexOf(','));
			}
			
			splitRuleAction = splitRuleAction.substring(2); // removing first '
			splitRuleAction = splitRuleAction.substring(0, splitRuleAction.length()-2); // removing last first '
			
			retList.add(new WhereThenData(splitRule[1], splitRuleAction));
		}
		
		return retList;
				
	}

	public List<RuleDetails> parseRulesDetails(List<RuleTypeData> rulesTypesData, String mid) {
		
		List<RuleDetails> retRuleDetails = new ArrayList<RuleDetails>();
		try{
		
		for(RuleTypeData ruleTypeDataElem: rulesTypesData)
		{	
			List<RuleDetails> parsedRuleDetails = parseRulesFromRuleType(ruleTypeDataElem, mid);
			if (parsedRuleDetails != null)
			{
				retRuleDetails.addAll(parsedRuleDetails);
			}	
		}
		}catch (Exception e){
			System.out.println("Error while parsing: " + e.getMessage());
			return null;
		}
		return retRuleDetails;
		
	}

	private List<RuleDetails> parseRulesFromRuleType(RuleTypeData ruleTypeDataElem, String mid) {
		
		List<RuleDetails> retRuleDetails = new ArrayList<RuleDetails>();
		String comment = null;		
		String ruleTypeID = ruleTypeDataElem.getRuleTypeId();	
		String ruleSubTypeID = parseRuleSubTypeID(ruleTypeDataElem);
		String ruleTypeName = ruleTypeDataElem.getRuleTypeName();
		System.out.println("Parsing rule Type name: "+ ruleTypeName + " rule Type id: " + ruleTypeID);
		String executionTimeStamp = parseRuleTimeStamp(ruleTypeDataElem);	
		String actionResult = "NONE";
	    List<Rule> rules = new ArrayList<Rule>();
	    
		
		RuleType ruleType = null;
		List<RuleAssociationData>  ruleAssciationList = ruleTypeDataElem.getRuleAssociations();
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
				if (ruleAssociationDataElem.getRuleResultActionList() != null)
				{
					actionResult = ruleAssociationDataElem.getRuleResultActionList();
				}
				
				/**
				 * @param ruleName
				 * @param ruleAction
				 * @param ruleResult
				 * @param ruleTypeID
				 * @param ruleSubTypeID
				 * @param ruleTypeName
				 * @param executionTimeStamp
				 * @param actionResult
				 * @param rawConditions
				 * @param rawBinding
				 */
				RuleDetails ruleDetails;
				for(Rule rule: rules)
				{
					ruleDetails = new RuleDetails(mid, rule.getRuleName(), rule.getRuleAction(), rule.getRuleResult(), ruleTypeID, ruleSubTypeID, ruleTypeName, executionTimeStamp, actionResult, rule.getRuleEvaluation().getRawConditions(), rule.getRuleEvaluation().getRawBinding());
					retRuleDetails.add(ruleDetails);
				}
				
			}
			
			//TODO: adjust when supporting ALLSET
//			if (ruleTypeDataElem.getEvaluateType().equals("FIRST"))
//			{
//				ruleType = new RuleType(comment, ruleTypeID, ruleSubTypeID, ruleTypeName, executionTimeStamp, actionResult, rules);
//			}
		//}
		
	
		return retRuleDetails;
	}
	
	
	

}
