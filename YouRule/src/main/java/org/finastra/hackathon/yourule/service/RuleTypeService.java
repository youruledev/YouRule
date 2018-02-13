package org.finastra.hackathon.yourule.service;


import java.util.List;
import java.util.Arrays;


import org.finastra.hackathon.yourule.model.Condition;
import org.finastra.hackathon.yourule.model.Rule;
import org.finastra.hackathon.yourule.model.RuleEvaluation;
import org.finastra.hackathon.yourule.model.RuleType;
import org.finastra.hackathon.yourule.model.StringsDef;

public class RuleTypeService 
{

	public List<RuleType> getAllRuleTypes()
	{	
		Condition finalCond1 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "a", "5", "=", "4");
		Condition finalCond2 = new Condition(StringsDef.CONDITION_FINAL, null, null, null, "b", "5", "=", "5");
		RuleEvaluation re1 = new RuleEvaluation(false, "a=5", "a=4", finalCond1);
		Rule ruleWith1Final = new Rule("business area rule name", "action1", false,   re1);
		
		
		Condition complexCond = new Condition(StringsDef.CONDITION_COMPLEX, finalCond1, finalCond2, "AND", null, null, null, null);
		RuleEvaluation re2 = new RuleEvaluation(false, "a=5 and b=5", "a=4, b=5", complexCond);
		Rule ruleWith2Final = new Rule("business area rule name", "action1", false,   re2);
		Rule[] ra1 = {ruleWith1Final};
		Rule[] ra2 = {ruleWith1Final,ruleWith2Final};
		
		
		
		RuleEvaluation reRawCond = new RuleEvaluation(true, "CCY=EUR", "CCY=EUR", null);
		Rule ruleWithRawBinding = new Rule ("department", "DEF", true, reRawCond);
		Rule[] ra3 = {ruleWithRawBinding,ruleWithRawBinding};
		
		
		RuleType rt1 = new RuleType(StringsDef.RULE_TYPE_COMMENT, "30", "0", "business area", "2018-01-11 13:04:38.504", "no action", ra1);
		RuleType rt2 = new RuleType(StringsDef.RULE_TYPE_COMMENT, "30", "0", "business area", "2018-01-11 13:04:38.504", "no action", ra2);
		RuleType rt3 = new RuleType(StringsDef.RULE_TYPE_COMMENT, "1", "0", "Department", "2018-01-11 13:04:38.504", "DEF", ra3);
		List<RuleType> list = Arrays.asList(rt1, rt2, rt3);
		return list;
	}
}
