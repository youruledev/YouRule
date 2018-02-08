package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="compCondion")
@XmlType(propOrder = {"type","leftOperand", "rightOperand", "operator", "leftOperandValue", "dog"})
public class compCondion extends Condition{
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	private String type;
	private String leftOperand;
	private String rightOperand;
	private String operator;
	private String leftOperandValue;
	private Dog dog;

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public compCondion()
	{
	}
	
	public compCondion(String leftOperand, String rightOperand, String operator, String leftOperandValue)
	{
	    this.type = CONDITION_TYPE_COMPLEX;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		this.leftOperandValue = leftOperandValue;
		dog = new Dog("buffy");
	}
}
