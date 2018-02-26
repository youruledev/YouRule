package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rules")
@XmlType(propOrder = {"mid", "ruleName", "ruleAction", "ruleResult", "ruleTypeID", "ruleSubTypeID", "ruleTypeName", "executionTimeStamp", "actionResult", "rawConditions", "rawBinding"})
public class RuleDetails {
	
	private String mid;
	private String ruleName;
	private String ruleAction;

    private boolean ruleResult;
    
	private String ruleTypeID;
	
	private String ruleSubTypeID;
	
	private String ruleTypeName;
	
	private String executionTimeStamp;
	
	private String actionResult;
	
	private String rawConditions;
	
	private String rawBinding;
	
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
	public RuleDetails(String mid, String ruleName, String ruleAction, boolean ruleResult,
			String ruleTypeID, String ruleSubTypeID, String ruleTypeName,
			String executionTimeStamp, String actionResult,
			String rawConditions, String rawBinding) {
		this.mid = mid;
		this.ruleName = ruleName;
		this.ruleAction = ruleAction;
		this.ruleResult = ruleResult;
		this.ruleTypeID = ruleTypeID;
		this.ruleSubTypeID = ruleSubTypeID;
		this.ruleTypeName = ruleTypeName;
		this.executionTimeStamp = executionTimeStamp;
		this.actionResult = actionResult;
		this.rawConditions = rawConditions;
		this.rawBinding = rawBinding;
	}

	public RuleDetails()
	{
	}
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
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

	public boolean isRuleResult() {
		return ruleResult;
	}

	public void setRuleResult(boolean ruleResult) {
		this.ruleResult = ruleResult;
	}

	public String getRuleTypeID() {
		return ruleTypeID;
	}

	public void setRuleTypeID(String ruleTypeID) {
		this.ruleTypeID = ruleTypeID;
	}

	public String getRuleSubTypeID() {
		return ruleSubTypeID;
	}

	public void setRuleSubTypeID(String ruleSubTypeID) {
		this.ruleSubTypeID = ruleSubTypeID;
	}

	public String getRuleTypeName() {
		return ruleTypeName;
	}

	public void setRuleTypeName(String ruleTypeName) {
		this.ruleTypeName = ruleTypeName;
	}

	public String getExecutionTimeStamp() {
		return executionTimeStamp;
	}

	public void setExecutionTimeStamp(String executionTimeStamp) {
		this.executionTimeStamp = executionTimeStamp;
	}

	public String getActionResult() {
		return actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	public String getRawConditions() {
		return rawConditions;
	}

	public void setRawConditions(String rawConditions) {
		this.rawConditions = rawConditions;
	}

	public String getRawBinding() {
		return rawBinding;
	}

	public void setRawBinding(String rawBinding) {
		this.rawBinding = rawBinding;
	}



}
