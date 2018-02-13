package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Condition")
@XmlType(propOrder = {"type", "leftCondition", "rightCondition", "logicalOperator", "leftOperand", "rightOperand", "operator",  "leftOperandValue"})
public class Condition
{

	private String type;
    private Condition leftCondition;
    private Condition rightCondition;
    private String logicalOperator;
    private String leftOperand;
    private String rightOperand;    
    private String operator;
    private String leftOperandValue;



	/**
	 * @param type
	 * @param leftCondition
	 * @param rightCondition
	 * @param logicalOperator
	 * @param leftOperand
	 * @param rightOperand
	 * @param operator
	 * @param leftOperandValue
	 */
	public Condition(String type, Condition leftCondition,
			Condition rightCondition, String logicalOperator,
			String leftOperand, String rightOperand, String operator,
			String leftOperandValue) {
		this.type = type;
		this.leftCondition = leftCondition;
		this.rightCondition = rightCondition;
		this.logicalOperator = logicalOperator;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		this.leftOperandValue = leftOperandValue;
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

    public String getLeftOperandValue ()
    {
        return leftOperandValue;
    }

    public void setLeftOperandValue (String leftOperandValue)
    {
        this.leftOperandValue = leftOperandValue;
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

    public String getRightOperand ()
    {
        return rightOperand;
    }

    public void setRightOperand (String rightOperand)
    {
        this.rightOperand = rightOperand;
    }

    public String getOperator ()
    {
        return operator;
    }

    public void setOperator (String operator)
    {
        this.operator = operator;
    }

    public String getLeftOperand ()
    {
        return leftOperand;
    }

    public void setLeftOperand (String leftOperand)
    {
        this.leftOperand = leftOperand;
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
        return "ClassPojo [logicalOperator = "+logicalOperator+", leftOperandValue = "+leftOperandValue+", leftCondition = "+leftCondition+", type = "+type+", leftOperand = "+rightOperand+", operator = "+operator+", leftOperand = "+leftOperand+", rightCondition = "+rightCondition+"]";
    }
}