package org.finastra.hackathon.yourule.model;

//import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Conditions")
public class Conditions {
 public List<Condition> getCondList() {
		return condList;
	}

	public void setCondList(List<Condition> condList) {
		this.condList = condList;
	}

List<Condition> condList;
}
