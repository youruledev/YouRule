package org.finastra.hackathon.yourule.model;



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"ruleName", "ruleAction", "ruleResult", "ruleEvaluation"})

public class Rule
{


	private String ruleName;
	
	private String ruleAction;

    private boolean ruleResult;

    private RuleEvaluation ruleEvaluation;

    
    


	/**
	 * @param ruleName
	 * @param ruleAction
	 * @param ruleResult
	 * @param ruleEvaluation
	 */
	public Rule(String ruleName, String ruleAction, boolean ruleResult,
			RuleEvaluation ruleEvaluation) {
		this.ruleName = ruleName;
		this.ruleAction = ruleAction;
		this.ruleResult = ruleResult;
		this.ruleEvaluation = ruleEvaluation;
	}

	public Rule() {
	}

    public String getRuleName ()
    {
        return ruleName;
    }

    public void setRuleName (String ruleName)
    {
        this.ruleName = ruleName;
    }

    public boolean getRuleResult ()
    {
        return ruleResult;
    }

    public void setRuleResult (boolean ruleResult)
    {
        this.ruleResult = ruleResult;
    }

    public RuleEvaluation getRuleEvaluation ()
    {
        return ruleEvaluation;
    }

    public void setRuleEvaluation (RuleEvaluation ruleEvaluation)
    {
        this.ruleEvaluation = ruleEvaluation;
    }

    public String getRuleAction ()
    {
        return ruleAction;
    }

    public void setRuleAction (String ruleAction)
    {
        this.ruleAction = ruleAction;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ruleName = "+ruleName+", ruleResult = "+ruleResult+", ruleEvaluation = "+ruleEvaluation+", ruleAction = "+ruleAction+"]";
    }
}
			
		