package org.finastra.hackathon.yourule.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"ruleName", "ruleAction", "ruleEvaluationResult", "ruleConditionsEvaluation"})

public class Rule 
{
	private String ruleName;
	private String ruleAction;
	private boolean ruleEvaluationResult;
	private List<Condition> ruleConditionsEvaluation;
	
	public Rule()
	{
	}
	
	public Rule(String ruleName, String ruleAction, boolean ruleEvaluationResult, List<Condition> ruleConditionsEvaluation)
	{
		this.ruleName = ruleName;
		this.ruleAction = ruleAction;
		this.ruleEvaluationResult = ruleEvaluationResult;
		this.ruleConditionsEvaluation = ruleConditionsEvaluation;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	public boolean isRuleEvaluationResult() {
		return ruleEvaluationResult;
	}
	public void setRuleEvaluationResult(boolean ruleEvaluationResult) {
		this.ruleEvaluationResult = ruleEvaluationResult;
	}
	public List<Condition> getRuleConditionsEvaluation() {
		return ruleConditionsEvaluation;
	}
	public void setRuleConditionsEvaluation(List<Condition> ruleConditionsEvaluation) {
		this.ruleConditionsEvaluation = ruleConditionsEvaluation;
	}

}
