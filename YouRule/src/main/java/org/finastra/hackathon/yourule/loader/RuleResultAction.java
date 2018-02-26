package org.finastra.hackathon.yourule.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RuleResultAction {

	private static String KEY_DELIMITER = "###";
	private static String EXPRESSION_DELIMITER = "#=#";

	// ###RESULT_ACTION#=#DEF_PROD###RESULT_OBJECT_UID#=#GR1###RESULT_RULE_SEQUENCE#=#9###RESULT_TYPE_ID#=#12###RESULT_RULE_UID#=#GR1^12^DEF_PRODUCT###RESULT_SEC_ACTION#=#0
	private static String[] supportedKey = new String[] {"RESULT_ACTION","RESULT_OBJECT_UID","RESULT_RULE_SEQUENCE","RESULT_TYPE_ID","RESULT_RULE_UID","RESULT_SEC_ACTION"};
	
	String rawData;
	Map<String,String> ruleResultActionMap= new HashMap<String,String>(); 
	
	RuleResultAction(String rawData)
	{
		this.rawData = rawData;
		
		parseRawData();
	}
	
	void parseRawData()
	{
		Scanner ruleresultScanner = new Scanner(rawData);
		
		ruleresultScanner.useDelimiter(KEY_DELIMITER);
		
		String exp;
		String[] splitExp;
		List<String> supportedKeyList = new ArrayList<String>(Arrays.asList(supportedKey));
		
		while (ruleresultScanner.hasNext()) 
		{
			exp = ruleresultScanner.next();
			
			splitExp = exp.split(EXPRESSION_DELIMITER);
	
			if (splitExp.length == 2) 
			{
				ruleResultActionMap.put(splitExp[0], splitExp[1]);
			}
			else
			{
				System.out.println("Not supported expression###:" + splitExp);
			}
			
		}
	}
	
	public String getResultAction() { 
		return   ruleResultActionMap.get("RESULT_ACTION");
	}
	
	public String getResultObjectUid() { 
		return   ruleResultActionMap.get("RESULT_OBJECT_UID");
	}
	
	public String getResultRuleSequence() { 
		return   ruleResultActionMap.get("RESULT_RULE_SEQUENCE");
	}
	
	public String getResultRuleTypeId() { 
		return   ruleResultActionMap.get("RESULT_TYPE_ID");
	}
	
	public String getResultRuleId() { 
		return   ruleResultActionMap.get("RESULT_RULE_UID");
	}
	
	public String getSecAction() { 
		return   ruleResultActionMap.get("RESULT_SEC_ACTION");
	}	
	
	//public String getCachedResultsCompletionCode() { 
	//	return   ruleResultActionMap.get("CACHDE_RESULTS_COMPLETION_CODE");
	//}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tRESULT_ACTION=").append(getResultAction())
		.append("\n\tRESULT_OBJECT_UID=").append(getResultObjectUid())
		.append("\n\tRESULT_RULE_SEQUENCE=").append(getResultRuleSequence())
		.append("\n\tRESULT_TYPE_ID=").append(getResultRuleTypeId())
		.append("\n\tRESULT_RULE_UID=").append(getResultRuleId())
		.append("\n\tRESULT_SEC_ACTION=").append(getSecAction());
		//.append("\n\tCACHDE_RESULTS_COMPLETION_CODE=").append(getCachedResultsCompletionCode());
		
		return sb.toString();
	}
	
	

}
