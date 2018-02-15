package org.finastra.hackathon.yourule.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class RuleAssociationData {
	private static String KEY_DELIMITER = "###";
	private static String EXPRESSION_DELIMITER = "#=#";
	
	private static String[] supportedKey = new String[] {"RULE_OBJECT_ASSOCIATION","EXECUTION_SCRIPT","ALIAS_BINDIND","IS_USING_CACHE_RESULTS","NOT_CACHED_PARAM_VALUE_BINDING","CACHDE_RESULTS_COMPLETION_CODE"};
	
	
	String rawData;
	
	Map<String,String> ruleAssociationMap= new HashMap<String,String>(); 
	
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

	public String getResultAction() { 
		return   ruleAssociationMap.get("RESULT_ACTION");
	}
	
	public String getResultObjectUid() { 
		return   ruleAssociationMap.get("RESULT_OBJECT_UID");
	}
	
	public String getResultRuleSequence() { 
		return   ruleAssociationMap.get("RESULT_RULE_SEQUENCE");
	}
	
	public String getResultRuleTypeId() { 
		return   ruleAssociationMap.get("RESULT_TYPE_ID");
	}
	
	public String getResultRuleId() { 
		return   ruleAssociationMap.get("RESULT_RULE_UID");
	}
	
	public String getSecAction() { 
		return   ruleAssociationMap.get("RESULT_SEC_ACTION");
	}	
	
	public String getCachedResultsCompletionCode() { 
		return   ruleAssociationMap.get("CACHDE_RESULTS_COMPLETION_CODE");
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tRULE_OBJECT_ASSOCIATION=").append(getRuleObjectAssociation())
		.append("\n\tEXECUTION_SCRIPT=").append(getExecutionScript())
		.append("\n\tALIAS_BINDIND=").append(getAliasBinding())
		.append("\n\tIS_USING_CACHE_RESULTS=").append(getIsUsingCacheResults())
		.append("\n\tNOT_CACHED_PARAM_VALUE_BINDING=").append(getNotCachedParamValueBinding())
		.append("\n\tCACHDE_RESULTS_COMPLETION_CODE=").append(getCachedResultsCompletionCode());
		
		return sb.toString();
	}
	
	
}
