package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="finalCondition")
@XmlType(propOrder = {"type","leftOperand", "rightOperand", "operator", "leftOperandValue"})

public class FinalCondition extends Condition 
{
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String leftOperand;
	private String rightOperand;
	private String operator;
	private String leftOperandValue;
	
	public FinalCondition()
	{
	}
	
	public FinalCondition(String leftOperand, String rightOperand, String operator, String leftOperandValue)
	{
	    this.type = CONDITION_TYPE_FINAL;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		this.leftOperandValue = leftOperandValue;
	}

	
	public String getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}
	
	public String getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(String rightOperand) {
		this.rightOperand = rightOperand;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLeftOperandValue() {
		return leftOperandValue;
	}

	public void setLeftOperandValue(String leftOperandValue) {
		this.leftOperandValue = leftOperandValue;
	}
	
	@Override
	public String toString()
	{
		return leftOperand + " ( " + leftOperandValue + " ) " + rightOperand;
	}
	

	
}
