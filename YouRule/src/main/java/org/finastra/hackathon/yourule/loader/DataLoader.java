package org.finastra.hackathon.yourule.loader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class DataLoader {

	List<RuleTypeData> ruleTypeList = new ArrayList<RuleTypeData>();
	
	public  void load(String filePath) throws Exception
	{
		FileReader fr = new FileReader(filePath);
		Scanner scan = new Scanner(fr);
		scan.useDelimiter(RuleTypeData.RULE_TYPE_EVAL_INFO_DELIMITER);
		
		
		
		while (scan.hasNext())
		{
			ruleTypeList.add(new RuleTypeData(scan.next()));
		}
		
		
		
		fr.close();
		
		System.out.println("Loading done!");

	}
	
	
	public List<RuleTypeData> getAllRuleTypeEvalData()
	{
		return ruleTypeList;
	}
	
	public RuleTypeData getRuleTypeEvalData(int index)
	{
		return ruleTypeList.get(index);
	}
	

}
