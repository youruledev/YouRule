package org.finastra.hackathon.yourule.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class RuleAssociationData {
	private static String KEY_DELIMITER = "####";
	private static String EXPRESSION_DELIMITER = "#=#";
	
	private static String[] supportedKey = new String[] {"RULE_OBJECT_ASSOCIATION","EXECUTION_SCRIPT","ALIAS_BINDIND","IS_USING_CACHE_RESULTS","NOT_CACHED_PARAM_VALUE_BINDING","CACHDE_RESULTS_COMPLETION_CODE"};
	
	
	String rawData;
	
	Map<String,String> ruleAssociationMap= new HashMap<String,String>(); 
	
	List<RuleResultAction> ruleResultActionList = new ArrayList<RuleResultAction>();
	
	RuleAssociationData(String rawData)
	{
		this.rawData = rawData;
		
		parseRawData();
	}
	
	void parseRawData()
	{
		Scanner ruleAssociationScanner = new Scanner(rawData);
		
		ruleAssociationScanner.useDelimiter(KEY_DELIMITER);
		
		String exp;
		String[] splitExp;
		List<String> supportedKeyList = new ArrayList<String>(Arrays.asList(supportedKey));
		
		while (ruleAssociationScanner.hasNext()) 
		{
			exp = ruleAssociationScanner.next();
			

			if (exp.startsWith("RESULT_ACTION"))
			{
				ruleResultActionList.add(new RuleResultAction(exp));
			}
			else
			{
				splitExp = exp.split(EXPRESSION_DELIMITER);
	
				if (splitExp.length == 2) 
				{
					ruleAssociationMap.put(splitExp[0], splitExp[1]);
				}
				else
				{
					System.out.println("Not supported expression###:" + splitExp);
				}
			}
		}
	}
	
	public String getRuleResultActionList()
	{
		String resultList="";
		
		for (RuleResultAction ruleresultAction: ruleResultActionList)
		{
			if (ruleresultAction.getResultAction() != null)
			{
				resultList += ruleresultAction.getResultAction() + ", ";
			}
		}
		
		if (resultList.equals(""))
			resultList = "NONE";
		
		return resultList;
	}
	
	public String getRuleResultRuleUidList()
	{
		String resultList=null;
		
		for (RuleResultAction ruleresultAction: ruleResultActionList)
		{
			if (ruleresultAction.getResultRuleId() != null)
				resultList += ruleresultAction.getResultRuleId();
		}
		
		return resultList;
	}
	
	

	public String  getRuleObjectAssociation() { 
		return   ruleAssociationMap.get("RULE_OBJECT_ASSOCIATION");
	}
	
	public String getExecutionScript() { 
		return   ruleAssociationMap.get("EXECUTION_SCRIPT");
	}
	
	public String getAliasBinding() { 
		return   ruleAssociationMap.get("ALIAS_BINDIND");
	}
	
	public String getIsUsingCacheResults() { 
		return   ruleAssociationMap.get("IS_USING_CACHE_RESULTS");
	}
	
	public String getNotCachedParamValueBinding() { 
		return   ruleAssociationMap.get("NOT_CACHED_PARAM_VALUE_BINDING");
	}
	

	public String getCachedParamValueBinding() { 
		return   ruleAssociationMap.get("CACHED_PARAM_VALUE_BINDING");
	}

	
	
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tRULE_OBJECT_ASSOCIATION=").append(getRuleObjectAssociation())
		.append("\n\tEXECUTION_SCRIPT=").append(getExecutionScript())
		.append("\n\tALIAS_BINDIND=").append(getAliasBinding())
		.append("\n\tIS_USING_CACHE_RESULTS=").append(getIsUsingCacheResults())
		.append("\n\tNOT_CACHED_PARAM_VALUE_BINDING=").append(getNotCachedParamValueBinding());
		
		return sb.toString();
	}
	
	
}
