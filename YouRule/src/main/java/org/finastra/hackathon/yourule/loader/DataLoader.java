package org.finastra.hackathon.yourule.loader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class DataLoader {

	List<RuleTypeEvalData> ruleTypeList = new ArrayList<RuleTypeEvalData>();
	
	public  void load(String filePath) throws Exception
	{
		FileReader fr = new FileReader(filePath);
		Scanner scan = new Scanner(fr);
		scan.useDelimiter(RuleTypeEvalData.RULE_TYPE_EVAL_INFO_DELIMITER);
		
		
		
		while (scan.hasNext())
		{
			ruleTypeList.add(new RuleTypeEvalData(scan.next()));
		}
		
		
		
		fr.close();
		
		System.out.println("Loading done!");

	}
	
	
	public List<RuleTypeEvalData> getAllRuleTypeEvalData()
	{
		return ruleTypeList;
	}
	
	public RuleTypeEvalData getRuleTypeEvalData(int index)
	{
		return ruleTypeList.get(index);
	}
	

}
