/*    */ package com.ibm.mq.jms;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JMSMQ_Messages
/*    */ {
/*    */   public static final String NAMESPACE = "JMSMQ";
/*    */   public static final String MQJMS_E_BAD_CCSID = "JMSMQ1046";
/*    */   public static final String MQJMS_PRODUCT_NAME = "JMSMQ1002";
/*    */   public static final String MQJMS_E_CLEANUP_REP_BAD_LEVEL = "JMSMQ3043";
/*    */   public static final String MQJMS_UTIL_CONNCLOSE = "JMSMQ5032";
/*    */   public static final String MQJMS_UTIL_CONNCREATE = "JMSMQ5010";
/*    */   public static final String MQJMS_UTIL_EXCCAUGHT = "JMSMQ5034";
/*    */   public static final String MQJMS_UTIL_EXCMSGFAIL = "JMSMQ5021";
/*    */   public static final String MQJMS_UTIL_IGNOREICF = "JMSMQ5002";
/*    */   public static final String MQJMS_UTIL_IGNOREUNK = "JMSMQ5003";
/*    */   public static final String MQJMS_UTIL_IGNOREURL = "JMSMQ5001";
/*    */   public static final String MQJMS_UTIL_INITCTXFAIL = "JMSMQ5006";
/*    */   public static final String MQJMS_UTIL_IVTFINISH = "JMSMQ5035";
/*    */   public static final String MQJMS_UTIL_IVTNAME = "JMSMQ5000";
/*    */   public static final String MQJMS_UTIL_IVTOK = "JMSMQ5033";
/*    */   public static final String MQJMS_UTIL_MSGCREATE = "JMSMQ5017";
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn)
/* 34 */       Trace.data("com.ibm.mq.jms.JMSMQ_Messages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSMQ_Messages.java"); 
/*    */   }
/*    */   
/*    */   public static final String MQJMS_UTIL_MSGDIFF = "JMSMQ5024";
/*    */   public static final String MQJMS_UTIL_MSGEQUALS = "JMSMQ5023";
/*    */   public static final String MQJMS_UTIL_MSGFAIL = "JMSMQ5020";
/*    */   public static final String MQJMS_UTIL_MSGGET = "JMSMQ5019";
/*    */   public static final String MQJMS_UTIL_MSGGOT = "JMSMQ5022";
/*    */   public static final String MQJMS_UTIL_MSGNOTTEXT = "JMSMQ5027";
/*    */   public static final String MQJMS_UTIL_MSGORIG = "JMSMQ5025";
/*    */   public static final String MQJMS_UTIL_MSGREPLY = "JMSMQ5026";
/*    */   public static final String MQJMS_UTIL_MSGSEND = "JMSMQ5018";
/*    */   public static final String MQJMS_UTIL_MSGTYPE = "JMSMQ5028";
/*    */   public static final String MQJMS_UTIL_NO_CLASS = "JMSMQ5037";
/*    */   public static final String MQJMS_UTIL_NO_JNDI_PROV = "JMSMQ5038";
/*    */   public static final String MQJMS_UTIL_NO_PARAM_VALUE = "JMSMQ5036";
/*    */   public static final String MQJMS_UTIL_PORT_NOT_NUMBER = "JMSMQ5064";
/*    */   public static final String MQJMS_UTIL_PS_LINKED_E = "JMSMQ5058";
/*    */   public static final String MQJMS_UTIL_QCFCREATE = "JMSMQ5007";
/*    */   public static final String MQJMS_UTIL_QCFFAIL = "JMSMQ5009";
/*    */   public static final String MQJMS_UTIL_QCFGET = "JMSMQ5008";
/*    */   public static final String MQJMS_UTIL_QCREATE = "JMSMQ5012";
/*    */   public static final String MQJMS_UTIL_QFAIL = "JMSMQ5014";
/*    */   public static final String MQJMS_UTIL_QGET = "JMSMQ5013";
/*    */   public static final String MQJMS_UTIL_QRCLOSE = "JMSMQ5029";
/*    */   public static final String MQJMS_UTIL_QRCREATE = "JMSMQ5016";
/*    */   public static final String MQJMS_UTIL_QSCLOSE = "JMSMQ5030";
/*    */   public static final String MQJMS_UTIL_QSCREATE = "JMSMQ5015";
/*    */   public static final String MQJMS_UTIL_SESSCLOSE = "JMSMQ5031";
/*    */   public static final String MQJMS_UTIL_SESSCREATE = "JMSMQ5011";
/*    */   public static final String MQJMS_UTIL_SYNC_SESSCREATE = "JMSMQ5073";
/*    */   public static final String MQJMS_UTIL_ASYNC_SESSCREATE = "JMSMQ5074";
/*    */   public static final String MQJMS_UTIL_START_CONN = "JMSMQ5075";
/*    */   public static final String MQJMS_UTIL_ONMESSAGE = "JMSMQ5076";
/*    */   public static final String MQJMS_UTIL_MSGLISETNER_SET = "JMSMQ5077";
/*    */   public static final String MQJMS_UTIL_SPECIFYURL = "JMSMQ5004";
/*    */   public static final String MQJMS_UTIL_USAGE_INTRO = "JMSMQ5065";
/*    */   public static final String MQJMS_UTIL_USAGE_LINE1 = "JMSMQ5066";
/*    */   public static final String MQJMS_UTIL_USAGE_LINE2 = "JMSMQ5067";
/*    */   public static final String MQJMS_UTIL_USAGE_LINE3 = "JMSMQ5068";
/*    */   public static final String MQJMS_UTIL_USINGADMINOBJ = "JMSMQ5005";
/*    */   public static final String MQJMS_UTIL_PS_NAME = "JMSMQ5039";
/*    */   public static final String MQJMS_EX_MESSAGE = "JMSMQ5100";
/*    */   public static final String MQJMS_EX_CLASS = "JMSMQ5101";
/*    */   public static final String MQJMS_EX_STACK = "JMSMQ5102";
/*    */   public static final String MQJMS_EX_LINKED = "JMSMQ5103";
/*    */   public static final String MQJMS_EX_ERROR_CODE = "JMSMQ5104";
/*    */   public static final String MQJMS_EX_EXPLANATION = "JMSMQ5105";
/*    */   public static final String MQJMS_EX_USER_ACTION = "JMSMQ5106";
/*    */   public static final String MQJMS_EX_COMPLETION_CODE = "JMSMQ5107";
/*    */   public static final String MQJMS_EX_REASON_CODE = "JMSMQ5108";
/*    */   public static final String MQJMS_EX_LOG_MESSAGE = "JMSMQ5109";
/*    */   public static final String MQJMS_EX_LOG_EXPLANATION = "JMSMQ5110";
/*    */   public static final String MQJMS_EX_LOG_SEVERITY = "JMSMQ5111";
/*    */   public static final String MQJMS_EX_LOG_SUMMARY = "JMSMQ5112";
/*    */   public static final String MQJMS_EX_USER_RESPONCE = "JMSMQ5113";
/*    */   public static final String MQJMS_CLASS_RUNNING_FROM = "JMSMQ5114";
/*    */   public static final String MQJMS_CANT_SET_QCF_PROPS = "JMSMQ5115";
/*    */   public static final String MQJMS_PASSWORD_PROMPT = "JMSMQ5116";
/*    */   public static final String MQJMS_PASSWORD_PROMPT_FAILED = "JMSMQ5117";
/*    */   public static final String MQJMS_UTIL_PS_RETREIVE_TCF = "JMSMQ5040";
/*    */   public static final String MQJMS_UTIL_PS_NO_TCF = "JMSMQ5041";
/*    */   public static final String MQJMS_UTIL_PS_RETRIEVE_TOPIC = "JMSMQ5042";
/*    */   public static final String MQJMS_UTIL_PS_NO_TOPIC = "JMSMQ5043";
/*    */   public static final String MQJMS_UTIL_PS_CREATE_TCF = "JMSMQ5044";
/*    */   public static final String MQJMS_UTIL_PS_CREATE_TCF_FAILED = "JMSMQ5045";
/*    */   public static final String MQJMS_UTIL_PS_CREATE_TOPIC = "JMSMQ5046";
/*    */   public static final String MQJMS_UTIL_PS_CREATE_TOPIC_FAILED = "JMSMQ5047";
/*    */   public static final String MQJMS_UTIL_PS_CREATE_PUB = "JMSMQ5048";
/*    */   public static final String MQJMS_UTIL_PS_CREATE_SUB = "JMSMQ5049";
/*    */   public static final String MQJMS_UTIL_PS_ADD_TXT = "JMSMQ5050";
/*    */   public static final String MQJMS_UTIL_PS_PUB_MSG = "JMSMQ5051";
/*    */   public static final String MQJMS_UTIL_PS_WAITING = "JMSMQ5052";
/*    */   public static final String MQJMS_UTIL_PS_NO_BROKER = "JMSMQ5053";
/*    */   public static final String MQJMS_UTIL_PS_NO_MSG = "JMSMQ5054";
/*    */   public static final String MQJMS_UTIL_PS_FAILED = "JMSMQ5055";
/*    */   public static final String MQJMS_UTIL_PS_CLOSE_SUB = "JMSMQ5056";
/*    */   public static final String MQJMS_UTIL_PS_CLOSE_PUB = "JMSMQ5057";
/*    */   public static final String MQJMS_UTIL_PS_FINISHED = "JMSMQ5059";
/*    */   public static final String MQJMS_UTIL_PS_NO_QM = "JMSMQ5060";
/*    */   public static final String MQJMS_UTIL_PS_NO_BRK_Q = "JMSMQ5061";
/*    */   public static final String MQJMS_UTIL_PS_INSTALL_BRK = "JMSMQ5062";
/*    */   public static final String MQJMS_UTIL_PS_BIR_EXCPTN = "JMSMQ5063";
/*    */   public static final String MQJMS_UTIL_PS_USAGE_INTRO = "JMSMQ5069";
/*    */   public static final String MQJMS_UTIL_PS_USAGE_LINE1 = "JMSMQ5070";
/*    */   public static final String MQJMS_UTIL_PS_USAGE_LINE2 = "JMSMQ5071";
/*    */   public static final String MQJMS_UTIL_PS_USAGE_LINE3 = "JMSMQ5072";
/*    */   public static final String MQJMS_UTIL_CLEANUP_CLIENT_NOT_SET = "JMSMQ5078";
/*    */   public static final String MQJMS_UTIL_CLEANUP_NAME = "JMSMQ5079";
/*    */   public static final String MQJMS_UTIL_CLEANUP_USAGE_INTRO = "JMSMQ5080";
/*    */   public static final String MQJMS_UTIL_CLEANUP_USAGE_LINE1 = "JMSMQ5081";
/*    */   public static final String MQJMS_UTIL_CLEANUP_USAGE_LINE2 = "JMSMQ5082";
/*    */   public static final String MQJMS_UTIL_CLEANUP_USAGE_LINE3 = "JMSMQ5083";
/*    */   public static final String MQJMS_UTIL_CLEANUP_COMPLETE = "JMSMQ5084";
/*    */   public static final String MQJMS_UTIL_CLEANUP_EXCEPTION = "JMSMQ5085";
/*    */   public static final String MQJMS_UTIL_CLEANUP_LINKED = "JMSMQ5086";
/*    */   public static final String MQJMS_UTIL_CLEANUP_NO_LEVEL = "JMSMQ5090";
/*    */   public static final String MQJMS_UTIL_CLEANUP_NO_HOSTNAME = "JMSMQ5091";
/*    */   public static final String MQJMS_UTIL_NON_NUMERIC = "JMSMQ5092";
/*    */   public static final String MQJMS_UTIL_UNREC_PARAM = "JMSMQ5093";
/*    */   public static final String MQJMS_UTIL_NO_ARGUMENT = "JMSMQ5094";
/*    */   public static final String MQJMS_IVT_FAIL = "JMSMQ5095";
/*    */   public static final String MQJMS_EXCEPTION_BAD_VALUE = "JMSMQ1006";
/*    */   public static final String MQJMS_E_INVALID_MESSAGE_REFERENCE = "JMSMQ1096";
/*    */   public static final String MQJMS_INVALID_DOMAIN_SPECIFIC = "JMSMQ1112";
/*    */   public static final String MQJMS_DESTINATION_NAME_UNSET = "JMSMQ1113";
/*    */   public static final String MQJMS_INVALID_DESTINATION = "JMSMQ0003";
/*    */   public static final String MQJMS_INTERNAL_METHOD = "JMSMQ1114";
/*    */   public static final String MQJMS_CANNOT_SET_VERSION = "JMSMQ0012";
/*    */   public static final String MQJMS_EXCEPTION_ILLEGAL_STATE = "JMSMQ0000";
/*    */   public static final String MQJMS_NO_COMPONENTS_FOUND = "JMSMQ1115";
/*    */   public static final String MQJMS_BAD_CLEANUP_PROVIDER_VERSION = "JMSMQ1120";
/*    */   public static final String MQJMS_META_DATA_WRONGLY_CREATED = "JMSMQ1121";
/*    */   public static final String MQJMS_OPERATION_DEPRECATED = "JMSMQ1122";
/*    */   public static final String MQJMS_ASSUME_CLIENT_INSTALLATION = "JMSMQ1123";
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSMQ_Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */