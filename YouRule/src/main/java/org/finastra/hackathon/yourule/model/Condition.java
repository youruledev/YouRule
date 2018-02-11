package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Condition")
@XmlType(propOrder = {"type", "leftCondition", "rightCondition", "logicalOperator", "leftValue", "rightValue", "operator",  "leftValueBind"})
public class Condition
{
    


	private String type;
    private Condition leftCondition;
    private Condition rightCondition;
    private String logicalOperator;
    private String rightValue;
    private String leftValue;
    private String operator;
    private String leftValueBind;


    /**
	 * @param type
	 * @param leftCondition
	 * @param rightCondition
	 * @param logicalOperator
	 * @param rightValue
	 * @param leftValue
	 * @param operator
	 * @param leftValueBind
	 */
	public Condition(String type, Condition leftCondition,
			Condition rightCondition, String logicalOperator,
			String rightValue, String leftValue, String operator,
			String leftValueBind) {
		this.type = type;
		this.leftCondition = leftCondition;
		this.rightCondition = rightCondition;
		this.logicalOperator = logicalOperator;
		this.rightValue = rightValue;
		this.leftValue = leftValue;
		this.operator = operator;
		this.leftValueBind = leftValueBind;
	}

	public Condition() {
	}
	
    public String getLogicalOperator ()
    {
        return logicalOperator;
    }

    public void setLogicalOperator (String logicalOperator)
    {
        this.logicalOperator = logicalOperator;
    }

    public String getLeftValueBind ()
    {
        return leftValueBind;
    }

    public void setLeftValueBind (String leftValueBind)
    {
        this.leftValueBind = leftValueBind;
    }

    public Condition getLeftCondition ()
    {
        return leftCondition;
    }

    public void setLeftCondition (Condition leftCondition)
    {
        this.leftCondition = leftCondition;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getRightValue ()
    {
        return rightValue;
    }

    public void setRightValue (String rightValue)
    {
        this.rightValue = rightValue;
    }

    public String getOperator ()
    {
        return operator;
    }

    public void setOperator (String operator)
    {
        this.operator = operator;
    }

    public String getLeftValue ()
    {
        return leftValue;
    }

    public void setLeftValue (String leftValue)
    {
        this.leftValue = leftValue;
    }

    public Condition getRightCondition ()
    {
        return rightCondition;
    }

    public void setRightCondition (Condition rightCondition)
    {
        this.rightCondition = rightCondition;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [logicalOperator = "+logicalOperator+", leftValueBind = "+leftValueBind+", leftCondition = "+leftCondition+", type = "+type+", rightValue = "+rightValue+", operator = "+operator+", leftValue = "+leftValue+", rightCondition = "+rightCondition+"]";
    }
}