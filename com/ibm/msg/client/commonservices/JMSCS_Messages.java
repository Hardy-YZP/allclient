/*    */ package com.ibm.msg.client.commonservices;
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
/*    */ 
/*    */ 
/*    */ public class JMSCS_Messages
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/JMSCS_Messages.java";
/*    */   public static final String NAMESPACE = "JMSCS";
/*    */   public static final String PROPERTIES_FILE_IOEXCEPTION = "JMSCS0001";
/*    */   public static final String CSI_UNINITIALIZED_EXCEPTION = "JMSCS0002";
/*    */   public static final String CSI_COMPONENT_NOT_FOUND = "JMSCS0003";
/*    */   public static final String MPI_COMPONENT_NOT_FOUND = "JMSCS0004";
/*    */   public static final String COMPONENT_NOT_FOUND = "JMSCS0005";
/*    */   public static final String INTERNAL_ERROR = "JMSCS0006";
/*    */   public static final String EXPLANATION = "JMSCS0007";
/*    */   public static final String ACTION = "JMSCS0008";
/*    */   public static final String SECURITY_EXCEPTION = "JMSCS0009";
/*    */   public static final String COMPONENT_FAILURE = "JMSCS0010";
/*    */   public static final String EX_MESSAGE = "JMSCS0011";
/*    */   public static final String EX_CLASS = "JMSCS0012";
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn)
/* 34 */       Trace.data("com.ibm.msg.client.commonservices.JMSCS_Messages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/JMSCS_Messages.java"); 
/*    */   }
/*    */   
/*    */   public static final String EX_STACK = "JMSCS0013";
/*    */   public static final String EX_LINKED = "JMSCS0014";
/*    */   public static final String CSI_COMMAND_OBJECT_INVALID_STATE = "JMSCS0015";
/*    */   public static final String CSI_COMMAND_MANAGER_EXCEPTION = "JMSCS0016";
/*    */   public static final String CSI_CONFIG_FILE_LOCATION = "JMSCS0017";
/*    */   public static final String PROPERTIES_UNKNOWN_PROPERTY = "JMSCS0018";
/*    */   public static final String PROPERTIES_UPDATE_NOT_PERMITTED = "JMSCS0019";
/*    */   public static final String SERIALIZATION_NOT_PERMITTED = "JMSCS0020";
/*    */   public static final String ALLOWLIST_FILE_CANNOT_BE_OPENED = "JMSCS0021";
/*    */   public static final String ALLOWLIST_FILE_ALREADY_EXISTS = "JMSCS0022";
/*    */   public static final String ALLOWLIST_INVALID_FORMAT = "JMSCS0023";
/*    */   public static final String ALLOWLIST_FILE_WRITE_FAILED = "JMSCS0024";
/*    */   public static final String ALLOWLIST_FILE_ENFORCEMENT_ENABLED = "JMSCS0025";
/*    */   public static final String ALLOWLIST_FILE_DISCOVERY_ENABLED = "JMSCS0026";
/*    */   public static final String ALLOWLIST_ENFORCEMENT_ENABLED = "JMSCS0027";
/*    */   public static final String ALLOWLIST_DISCOVERY_REQUESTED = "JMSCS0028";
/*    */   public static final String ALLOWLIST_NOT_REQUESTED = "JMSCS0029";
/*    */   public static final String UNABLE_TO_WRITE_TO_SPECIFIED_TRACE_LOCATION = "JMSCS0030";
/*    */   public static final String EXCEPTION_OCCURRED_WHEN_WRITING_TO_SPECIFIED_TRACE_LOCATION = "JMSCS0031";
/*    */   public static final String FAILED_TO_INITIALIZE_TRACE = "JMSCS0032";
/*    */   public static final String TRACE_CANNOT_ACCESS_DEFAULT = "JMSCS0033";
/*    */   public static final String TRACE_CANNOT_ACCESS_SPECIFIED = "JMSCS0034";
/*    */   public static final String TRACE_CANNOT_ACCESS_CURRENT = "JMSCS0035";
/*    */   public static final String TRACE_BAD_SEARCH_STRING = "JMSCS0036";
/*    */   public static final String TRACE_BAD_HANDLER_NAME = "JMSCS0037";
/*    */   public static final String TRACE_BAD_FORMATTER_NAME = "JMSCS0038";
/*    */   public static final String FAILED_TO_WRITE_FDC = "JMSCS0039";
/*    */   public static final String TRACING_TO_FILE = "JMSCS0040";
/*    */   public static final String TRACING_TO_CONSOLE = "JMSCS0041";
/*    */   public static final String PROPERTY_VALUE = "JMSCS0042";
/*    */   public static final String QUEUEING_RECONNECT = "JMSCS0043";
/*    */   public static final String STARTING_PARENT_RECONNECT = "JMSCS0044";
/*    */   public static final String ABORTING_PARENT_RECONNECT = "JMSCS0045";
/*    */   public static final String RESCHEDULING_PARENT_RECONNECT = "JMSCS0046";
/*    */   public static final String COMPLETED_PARENT_RECONNECT = "JMSCS0047";
/*    */   public static final String STARTING_CHILD_RECONNECT = "JMSCS0048";
/*    */   public static final String FAILED_CHILD_RECONNECT = "JMSCS0049";
/*    */   public static final String COMPLETED_CHILD_RECONNECT = "JMSCS0050";
/*    */   public static final String PARENT_NO_LONGER_RECONNECTABLE = "JMSCS0051";
/*    */   public static final String PARENT_RECONNECT_EXPIRED = "JMSCS0052";
/*    */   public static final String RECONNECTION_TIMES = "JMSCS0053";
/*    */   public static final String IGNORING_RECONNECT = "JMSCS0054";
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\JMSCS_Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */