package org.finastra.hackathon.yourule.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Dog")
public class Dog {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Dog()
	{
	}
	
	public Dog(String Name)
	{
		this.name=Name;
	}
	

}
