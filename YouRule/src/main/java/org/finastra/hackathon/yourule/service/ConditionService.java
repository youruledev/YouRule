package org.finastra.hackathon.yourule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import org.finastra.hackathon.yourule.model.ComplexCondition;
import org.finastra.hackathon.yourule.model.Condition;
import org.finastra.hackathon.yourule.model.Conditions;
import org.finastra.hackathon.yourule.model.FinalCondition;
import org.finastra.hackathon.yourule.model.compCondion;

public class ConditionService 
{
//	static Condition c1 = new FinalCondition ("Msg tp", "Pacs_008", "=", "Pain_001");
//	static Condition c2 = new FinalCondition ("Dbt MOP", "SCT", "=", "BOOK");
//	static Condition c3 = new FinalCondition ("Dbt MOP", "BOOK", "=", "BOOK");
	
	static FinalCondition c1 = new FinalCondition ("Msg tp", "Pacs_008", "=", "Pain_001");
	static FinalCondition c2 = new FinalCondition ("Dbt MOP", "SCT", "=", "BOOK");
	static FinalCondition c3 = new FinalCondition ("Dbt MOP", "BOOK", "=", "BOOK");
	static compCondion c8 = new compCondion("Dbt MOP", "BOOK", "=", "BOOK");
	
	//static Condition c4 = new ComplexCondition(c1,c2,"AND");
	//static Condition c5 = new ComplexCondition(c3,c4,"OR");
	static ComplexCondition c4 = new ComplexCondition(c1,c2,"AND");
	static ComplexCondition c6 = new ComplexCondition(c1,c2,"AND");
	static ComplexCondition c7 = new ComplexCondition(c3,c4,"OR");
	
	//static List<Condition> list = new ArrayList<>(Arrays.asList(c1,c2));
	//static List<Condition> list = new ArrayList<>(Arrays.asList(c1,c2,c3,c4,c5));
	//static List<ComplexCondition> list = new ArrayList<>(Arrays.asList(c6,c7));
	//static List<FinalCondition> list = new ArrayList<>(Arrays.asList(c1,c1));
	
	static List<compCondion> list = Arrays.asList(c8,c8);
	
	public List<compCondion> getAllConditions()
	{	
		return list;
	}
	
//	public Conditions getAllConditions()
//	{	
//		Conditions con = new Conditions();
//		con.setCondList(list);
//		return con;
//	}
	
	public Condition getCondition(int i)
	{
		if (i < list.size())
			return list.get(i); 
		return null;
	}
}
