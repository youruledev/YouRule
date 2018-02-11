package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rulesTypes")
@XmlType(propOrder = {"comment", "ruleTypeID", "ruleSubTypeID", "ruleTypeName", "executionTimeStamp", "actionResult", "rules"})
public class RuleType
{


	private String actionResult;

    private String ruleTypeName;

    private String ruleSubTypeID;

    private String executionTimeStamp;

    private String ruleTypeID;

    private String comment;

    private Rule[] rules;

    public RuleType()
	{
	}
    
    /**
	 * @param actionResult
	 * @param ruleTypeName
	 * @param ruleSubTypeID
	 * @param executionTimeStamp
	 * @param ruleTypeID
	 * @param comment
	 * @param rules
	 */
	public RuleType(String comment, String ruleTypeID, String ruleSubTypeID, String ruleTypeName,
			 String executionTimeStamp, String actionResult, Rule[] rules) {
		this.actionResult = actionResult;
		this.ruleTypeName = ruleTypeName;
		this.ruleSubTypeID = ruleSubTypeID;
		this.executionTimeStamp = executionTimeStamp;
		this.ruleTypeID = ruleTypeID;
		this.comment = comment;
		this.rules = rules;
	}
    
    public String getActionResult ()
    {
        return actionResult;
    }

    public void setActionResult (String actionResult)
    {
        this.actionResult = actionResult;
    }

    public String getRuleTypeName ()
    {
        return ruleTypeName;
    }

    public void setRuleTypeName (String ruleTypeName)
    {
        this.ruleTypeName = ruleTypeName;
    }

    public String getRuleSubTypeID ()
    {
        return ruleSubTypeID;
    }

    public void setRuleSubTypeID (String ruleSubTypeID)
    {
        this.ruleSubTypeID = ruleSubTypeID;
    }

    public String getExecutionTimeStamp ()
    {
        return executionTimeStamp;
    }

    public void setExecutionTimeStamp (String executionTimeStamp)
    {
        this.executionTimeStamp = executionTimeStamp;
    }

    public String getRuleTypeID ()
    {
        return ruleTypeID;
    }

    public void setRuleTypeID (String ruleTypeID)
    {
        this.ruleTypeID = ruleTypeID;
    }

    public String getComment ()
    {
        return comment;
    }

    public void setComment (String comment)
    {
        this.comment = comment;
    }

    public Rule[] getRules ()
    {
        return rules;
    }

    public void setRules (Rule[] rules)
    {
        this.rules = rules;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [actionResult = "+actionResult+", ruleTypeName = "+ruleTypeName+", ruleSubTypeID = "+ruleSubTypeID+", executionTimeStamp = "+executionTimeStamp+", ruleTypeID = "+ruleTypeID+", comment = "+comment+", rules = "+rules+"]";
    }
}
	
