package org.finastra.hackathon.yourule.parser;

import java.util.ArrayList;
import java.util.List;

import org.finastra.hackathon.yourule.loader.RuleAssociationData;
import org.finastra.hackathon.yourule.loader.RuleTypeData;
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
		String actionResult = "UNKNOWN";
	    List<Rule> rules = new ArrayList<Rule>();

	    
	    Condition finalCond1 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "a", "5", "=", "4");
		Condition finalCond2 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "b", "5", "=", "5");
		RuleEvaluation re1 = new RuleEvaluation(false, "a=5", "a=4", finalCond1);
		Rule ruleWith1Final = new Rule("business area rule name", "action1", false,   re1);
		rules.add(ruleWith1Final);
		rules.add(ruleWith1Final);
		
		
		RuleType ruleType;
		List<RuleAssociationData>  ruleAssciationList = ruleTypeData.getRuleAssociations();
		if (ruleAssciationList.size() == 0) //no rule were attached for this rule type
		{
			ruleType = null;
		}
		else
		{
			for(RuleAssociationData ruleAssociationDataElem:ruleAssciationList)
			{
				List<Rule> rulesInAssociation = parseRuleAssociation(ruleAssociationDataElem);
				if (rulesInAssociation != null)
				{
					rules.addAll(rulesInAssociation);
				}
				
			}
			ruleType = new RuleType(comment, ruleTypeID, ruleSubTypeID, ruleTypeName, executionTimeStamp, actionResult, rules);
		}
		
	
		

		return ruleType;
	}

	private List<Rule> parseRuleAssociation(RuleAssociationData ruleAssociationDataElem) 
	{
		Condition finalCond1 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "a", "5", "=", "4");
		Condition finalCond2 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "b", "5", "=", "5");
		RuleEvaluation re1 = new RuleEvaluation(false, "a=5", "a=4", finalCond1);
		Rule ruleWith1Final = new Rule("business area rule name", "action1", false,   re1);
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(ruleWith1Final);
		rules.add(ruleWith1Final);
		return rules;
	}
	
	

}
