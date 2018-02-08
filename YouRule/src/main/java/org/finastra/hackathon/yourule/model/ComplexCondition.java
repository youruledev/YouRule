package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ComplexCondition")
@XmlType(propOrder = {"type","leftOperand", "rightOperand", "operator"})

public class ComplexCondition extends Condition {

	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private Condition leftOperand;
	private Condition rightOperand;
	private String operator; // AND/OR
	
	public ComplexCondition()
	{
	}
	
	public ComplexCondition(Condition leftOperand, Condition rightOperand, String operator)
	{
		this.type = CONDITION_TYPE_COMPLEX;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
	}

//	
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	} 
	public Condition getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(Condition leftOperand) {
		this.leftOperand = leftOperand;
	}

	public Condition getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(Condition rightOperand) {
		this.rightOperand = rightOperand;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString()
	{
		return rightOperand + " " + operator + " " + leftOperand;
	}
	
	
}
