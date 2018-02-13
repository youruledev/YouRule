package org.finastra.hackathon.yourule.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 #RULE_TYPE_ID_EVAL_START####RULE_TYPE_ID#=#114####RULE_SUB_TYPE_ID#=#null####RULE_TYPE_NAME#=#Inbound message type selection####EVALUATE_TYPE#=#FIRST####EXEC_TYPE#=#SIMPLE####TIME_STAMP#=#2018-02-07 11:08:06.301
####RULE_OBJECT_ASSOCIATION#=#***###RULE_SEQUANCE#=#1
###EXECUTION_SCRIPT#=#SELECT CASE  WHEN :P_ORIG_MSG_TYPE <> 'NACHA' AND  :P_ORIG_MSG_TYPE like 'NACHA' || '%' THEN '***^114^NACHA_MP_MT_SEL_ALREADY_RAN,STOP,NA,0' WHEN (:P_DBT_MOP = 'MYIBGDD' OR :P_CDT_MOP = 'MYIBGDD' OR :D_FILE_SRC = 'MYIBGDD') AND :X_TRANSACTION_CD = '26' AND  :GET_MULTI_FIELD_VAL = 'R00' THEN '***^114^MYIBG_DD_MP_ACK,NACHA_DD_ACK,NACHA_DD,1' WHEN (:P_DBT_MOP = 'MYIBGDD' OR :P_CDT_MOP = 'MYIBGDD' OR :D_FILE_SRC = 'MYIBGDD') AND :X_TRANSACTION_CD = '26' AND  :GET_MULTI_FIELD_VAL <> 'R00' THEN '***^114^MYIBG_DD_MP_REJECT,NACHA_DD_REJ,NACHA_DD,2' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND  :P_MSG_SUB_TYPE in('ACK','ATX') THEN '***^114^NACHA_MP_ACK_CT,NACHA_ACK,NACHA_CT,3' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND ((:COMPARE_STRING = 1 AND  :P_MSG_SUB_TYPE = 'COR') OR  :P_MSG_SUB_TYPE in('DNE','ENR')) THEN '***^114^NACHA_MP_ADMIN_CT,NACHA_ADMIN,NACHA_CT,4' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND  :COMPARE_STRING = 1 AND  :P_MSG_SUB_TYPE = 'COR' THEN '***^114^NACHA_MP_ADMIN_DD,NACHA_ADMIN,NACHA_DD,5' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND  :COMPARE_STRING = 1 THEN '***^114^NACHA_MP_CT,NACHA_CT,NA,6' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND  :COMPARE_STRING = 1 THEN '***^114^NACHA_MP_DD,NACHA_DD,NA,7' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND  :COMPARE_STRING = 1 AND  :P_MSG_SUB_TYPE <> 'COR' THEN '***^114^NACHA_MP_RTN_CT,NACHA_RTN,NACHA_CT,8' WHEN :P_ORIG_MSG_TYPE = 'NACHA' AND :COMPARE_STRING = 1 AND  :P_MSG_SUB_TYPE <> 'COR' THEN '***^114^NACHA_MP_RTN_DD,NACHA_RTN,NACHA_DD,9' END FROM DUAL
###ALIAS_BINDIND#=#[[Ljava.lang.Object;@4c704ff1, [Ljava.lang.Object;@61907e6b, [Ljava.lang.Object;@555bd4d7, [Ljava.lang.Object;@7ff29b08, [Ljava.lang.Object;@10a06bd8, [Ljava.lang.Object;@14083a6f, [Ljava.lang.Object;@283457af, [Ljava.lang.Object;@468b6c8, [Ljava.lang.Object;@76b806ba, [Ljava.lang.Object;@2bc1da3a, [Ljava.lang.Object;@43de2c47, [Ljava.lang.Object;@2f2d0c20, [Ljava.lang.Object;@36772112, [Ljava.lang.Object;@471c40ad, [Ljava.lang.Object;@fda4254, [Ljava.lang.Object;@4238a0ed, [Ljava.lang.Object;@13cd4992, [Ljava.lang.Object;@a991a66, [Ljava.lang.Object;@499423f9, [Ljava.lang.Object;@1cc5d62c, [Ljava.lang.Object;@6ff176, [Ljava.lang.Object;@35557a33, [Ljava.lang.Object;@7ec9c59f, [Ljava.lang.Object;@70cd8ac0, [Ljava.lang.Object;@46507d1b, [Ljava.lang.Object;@4e459bad, [Ljava.lang.Object;@3befa300, [Ljava.lang.Object;@71b673bd, [Ljava.lang.Object;@168b79e, [Ljava.lang.Object;@1eee6cb, [Ljava.lang.Object;@6b721a6a]
###IS_USING_CACHE_RESULTS#=#false
###NOT_CACHED_PARAM_VALUE_BINDING#=#[[Pacs_008, 12, P_ORIG_MSG_TYPE, null, true], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [null, 12, P_DBT_MOP, null, true], [null, 12, P_CDT_MOP, null, true], [null, 12, D_FILE_SRC, null, true], [null, 12, X_TRANSACTION_CD, null, true], [, 12, ##GET_MULTI_FIELD_VAL##, GET_MULTI_FIELD_VAL(X_ADDENDA_INFO,1,X_RSN_CD), true], [null, 12, P_DBT_MOP, null, false], [null, 12, P_CDT_MOP, null, false], [null, 12, D_FILE_SRC, null, false], [null, 12, X_TRANSACTION_CD, null, false], [, 12, ##GET_MULTI_FIELD_VAL##, GET_MULTI_FIELD_VAL(X_ADDENDA_INFO,1,X_RSN_CD), false], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [null, 12, P_MSG_SUB_TYPE, null, true], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [0, -7, ##COMPARE_STRING##, COMPARE_STRING(X_TRANSACTION_CD=null,In Value List,NACHA_MP_RTN_NOC_CT_TX_CDS,''), true], [null, 12, P_MSG_SUB_TYPE, null, false], [null, 12, P_MSG_SUB_TYPE, null, false], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [0, -7, ##COMPARE_STRING##, COMPARE_STRING(X_TRANSACTION_CD=null,In Value List,NACHA_MP_RTN_NOC_DD_TX_CDS,''), true], [null, 12, P_MSG_SUB_TYPE, null, false], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [0, -7, ##COMPARE_STRING##, COMPARE_STRING(X_TRANSACTION_CD=null,In Value List,NACHA_MP_CT_TX_CDS,''), true], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [0, -7, ##COMPARE_STRING##, COMPARE_STRING(X_TRANSACTION_CD=null,In Value List,NACHA_MP_DD_TX_CDS,''), true], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [0, -7, ##COMPARE_STRING##, COMPARE_STRING(X_TRANSACTION_CD=null,In Value List,NACHA_MP_RTN_NOC_CT_TX_CDS,''), false], [null, 12, P_MSG_SUB_TYPE, null, false], [Pacs_008, 12, P_ORIG_MSG_TYPE, null, false], [0, -7, ##COMPARE_STRING##, COMPARE_STRING(X_TRANSACTION_CD=null,In Value List,NACHA_MP_RTN_NOC_DD_TX_CDS,''), false], [null, 12, P_MSG_SUB_TYPE, null, false]]
###CACHDE_RESULTS_COMPLETION_CODE#=#NORMAL_TERMINATION
 */
public class RuleTypeEvalData {
	public static String RULE_TYPE_EVAL_INFO_DELIMITER = "#RULE_TYPE_ID_EVAL_START";
	
	private static String KEY_DELIMITER = "####";
	private static String EXPRESSION_DELIMITER = "#=#";
	
	private static String[] supportedKey = new String[] {"RULE_TYPE_ID","RULE_SUB_TYPE_ID","RULE_TYPE_NAME","EVALUATE_TYPE","EXEC_TYPE","TIME_STAMP"};
	
	String rawData;
	Map<String,String> ruleTypeEvalMap= new HashMap<String,String>(); 
	List<RuleAssociationData> ruleAssociationInfoList = new ArrayList<RuleAssociationData>();
	


	RuleTypeEvalData(String rawData) {
		this.rawData = rawData;
		
		parseRawData();
	}
	

	
	void parseRawData()
	{
		Scanner ruleTypeEvalScanner = new Scanner(rawData);
		
		ruleTypeEvalScanner.useDelimiter(KEY_DELIMITER);
		
		String exp;
		String[] splitExp;
		List<String> supportedKeyList = new ArrayList<String>(Arrays.asList(supportedKey));
		
		while (ruleTypeEvalScanner.hasNext())
		{
			exp = ruleTypeEvalScanner.next();
			
			if (exp.startsWith("RULE_OBJECT_ASSOCIATION"))
			{
				ruleAssociationInfoList.add(new RuleAssociationData(exp));
			}
			else
			{
				splitExp = exp.split(EXPRESSION_DELIMITER);
				
				if (splitExp.length == 2 && supportedKeyList.contains(splitExp[0]))
				{
					ruleTypeEvalMap.put(splitExp[0], splitExp[1]);
				}
				else
				{
					System.out.println("Not supported expression##:" + splitExp);
				}
			}
			
		}
	}
	

	String getRuleTypeId() { 
		return   ruleTypeEvalMap.get("RULE_TYPE_ID");
	}
	
	String getRuleSubTypeId() { 
		return   ruleTypeEvalMap.get("RULE_SUB_TYPE_ID");
	}
	
	String getRuleTypeName() { 
		return   ruleTypeEvalMap.get("RULE_TYPE_NAME");
	}
	
	String getEvaluateType() { 
		return   ruleTypeEvalMap.get("EVALUATE_TYPE");
	}
	
	String getExecType() { 
		return   ruleTypeEvalMap.get("EXEC_TYPE");
	}
	
	String getTimeStamp() { 
		return   ruleTypeEvalMap.get("TIME_STAMP");
	}
	
	RuleAssociationData getRuleAssociationInfo( int index)
	{
		return ruleAssociationInfoList.get(index);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tRULE_TYPE_ID=").append(getRuleTypeId())
		.append("\n\tRULE_SUB_TYPE_ID=").append(getRuleSubTypeId())
		.append("\n\tRULE_TYPE_NAME=").append(getRuleTypeName())
		.append("\n\tEVALUATE_TYPE=").append(getEvaluateType())
		.append("\n\tEXEC_TYPE=").append(getExecType())
		.append("\n\tTIME_STAMP=").append(getTimeStamp())
		.append(ruleAssociationInfoList.size()>0?ruleAssociationInfoList.get(0):null)
		.append(ruleAssociationInfoList.size()>1?ruleAssociationInfoList.get(1):null);
		
		
		
		return sb.toString();
	}
	
}