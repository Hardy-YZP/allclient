/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class MQBrokerMessage
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerMessage.java";
/*      */   private static final String clsName = "MQBrokerMessage";
/*      */   public static final String MQPS_COMMAND = "MQPSCommand";
/*      */   public static final String MQPS_COMP_CODE = "MQPSCompCode";
/*      */   public static final String MQPS_DELETE_OPTIONS = "MQPSDelOpts";
/*      */   public static final String MQPS_ERROR_ID = "MQPSErrorId";
/*      */   public static final String MQPS_ERROR_POS = "MQPSErrorPos";
/*      */   public static final String MQPS_INTEGER_DATA = "MQPSIntData";
/*      */   public static final String MQPS_PARAMETER_ID = "MQPSParmId";
/*      */   public static final String MQPS_PUBLICATION_OPTIONS = "MQPSPubOpts";
/*      */   public static final String MQPS_PUBLISH_TIMESTAMP = "MQPSPubTime";
/*      */   public static final String MQPS_Q_MGR_NAME = "MQPSQMgrName";
/*      */   public static final String MQPS_Q_NAME = "MQPSQName";
/*      */   public static final String MQPS_REASON = "MQPSReason";
/*      */   public static final String MQPS_REASON_TEXT = "MQPSReasonText";
/*      */   public static final String MQPS_REGISTRATION_OPTIONS = "MQPSRegOpts";
/*      */   public static final String MQPS_SEQUENCE_NUMBER = "MQPSSeqNum";
/*      */   public static final String MQPS_STREAM_NAME = "MQPSStreamName";
/*      */   public static final String MQPS_STRING_DATA = "MQPSStringData";
/*      */   public static final String MQPS_TOPIC = "MQPSTopic";
/*      */   public static final String MQPS_USER_ID = "MQPSUserId";
/*      */   public static final String MQPS_SUBSCRIPTION_IDENTITY = "MQPSSubIdentity";
/*      */   public static final String MQPS_SUBSCRIPTION_NAME = "MQPSSubName";
/*      */   public static final String MQPS_SUBSCRIPTION_USER_DATA = "MQPSSubUserData";
/*      */   public static final String MQPS_CORREL_ID = "MQPSCorrelId";
/*      */   public static final String MQPS_FILTER = "MQPSFilter";
/*      */   public static final String MQPS_DELETE_PUBLICATION = "DeletePub";
/*      */   public static final String MQPS_DEREGISTER_PUBLISHER = "DeregPub";
/*      */   public static final String MQPS_DEREGISTER_SUBSCRIBER = "DeregSub";
/*      */   public static final String MQPS_PUBLISH = "Publish";
/*      */   public static final String MQPS_REGISTER_PUBLISHER = "RegPub";
/*      */   public static final String MQPS_REGISTER_SUBSCRIBER = "RegSub";
/*      */   public static final String MQPS_REQUEST_UPDATE = "ReqUpdate";
/*      */   public static final String MQPS_ANONYMOUS = "Anon";
/*      */   public static final String MQPS_CORREL_ID_AS_IDENTITY = "CorrelAsId";
/*      */   public static final String MQPS_DEREGISTER_ALL = "DeregAll";
/*      */   public static final String MQPS_DIRECT_REQUESTS = "DirectReq";
/*      */   public static final String MQPS_INCLUDE_STREAM_NAME = "InclStreamName";
/*      */   public static final String MQPS_INFORM_IF_RETAINED = "InformIfRet";
/*      */   public static final String MQPS_IS_RETAINED_PUBLICATION = "IsRetainedPub";
/*      */   public static final String MQPS_LOCAL = "Local";
/*      */   public static final String MQPS_NEW_PUBLICATIONS_ONLY = "NewPubsOnly";
/*      */   public static final String MQPS_NO_REGISTRATION = "NoReg";
/*      */   public static final String MQPS_NONE = "None";
/*      */   public static final String MQPS_OTHER_SUBSCRIBERS_ONLY = "OtherSubsOnly";
/*      */   
/*      */   static {
/*   63 */     if (Trace.isOn) {
/*   64 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerMessage.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_PUBLISH_ON_REQUEST_ONLY = "PubOnReqOnly";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_RETAIN_PUBLICATION = "RetainPub";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_DUPLICATES_OK = "DupsOK";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_NON_PERSISTENT = "NonPers";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_PERSISTENT = "Pers";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_PERSISTENT_AS_PUBLISH = "PersAsPub";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_PERSISTENT_AS_Q = "PersAsQueue";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_ADD_NAME = "AddName";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_NO_ALTERATION = "NoAlter";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_FULL_RESPONSE = "FullResp";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_JOIN_SHARED = "JoinShared";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_JOIN_EXCLUSIVE = "JoinExcl";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_LEAVE_ONLY = "LeaveOnly";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_VARIABLE_USER_ID = "VariableUserId";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQPS_LOCKED = "Locked";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQDELO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQDELO_LOCAL = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPUBO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPUBO_CORREL_ID_AS_IDENTITY = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPUBO_RETAIN_PUBLICATION = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPUBO_OTHER_SUBSCRIBERS_ONLY = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPUBO_NO_REGISTRATION = 8;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPUBO_IS_RETAINED_PUBLICATION = 16;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_NONE = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_CORREL_ID_AS_IDENTITY = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_ANONYMOUS = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_LOCAL = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_DIRECT_REQUESTS = 8;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_NEW_PUBLICATIONS_ONLY = 16;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_PUBLISH_ON_REQUEST_ONLY = 32;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_DEREGISTER_ALL = 64;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_INCLUDE_STREAM_NAME = 128;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_INFORM_IF_RETAINED = 256;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_DUPLICATES_OK = 512;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_NON_PERSISTENT = 1024;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_PERSISTENT = 2048;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_PERSISTENT_AS_PUBLISH = 4096;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_PERSISTENT_AS_Q = 8192;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_ADD_NAME = 16384;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_NO_ALTERATION = 32768;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_FULL_RESPONSE = 65536;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_JOIN_SHARED = 131072;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_JOIN_EXCLUSIVE = 262144;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_LEAVE_ONLY = 524288;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_VARIABLE_USER_ID = 1048576;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQREGO_LOCKED = 2097152;
/*      */ 
/*      */ 
/*      */   
/*  276 */   private static Hashtable optionFields = new Hashtable<>();
/*      */   
/*      */   static {
/*  279 */     if (Trace.isOn) {
/*  280 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  286 */     Hashtable<Object, Object> flags = new Hashtable<>();
/*  287 */     flags.put("Local", Integer.valueOf(4));
/*  288 */     optionFields.put("MQPSDelOpts", flags);
/*      */ 
/*      */     
/*  291 */     flags = new Hashtable<>();
/*  292 */     flags.put("CorrelAsId", Integer.valueOf(1));
/*  293 */     flags.put("Anon", Integer.valueOf(2));
/*  294 */     flags.put("Local", Integer.valueOf(4));
/*  295 */     flags.put("DirectReq", Integer.valueOf(8));
/*  296 */     flags.put("NewPubsOnly", Integer.valueOf(16));
/*  297 */     flags.put("PubOnReqOnly", Integer.valueOf(32));
/*  298 */     flags.put("DeregAll", Integer.valueOf(64));
/*  299 */     flags.put("InclStreamName", Integer.valueOf(128));
/*  300 */     flags.put("InformIfRet", Integer.valueOf(256));
/*  301 */     flags.put("DupsOK", Integer.valueOf(512));
/*  302 */     flags.put("NonPers", Integer.valueOf(1024));
/*  303 */     flags.put("Pers", Integer.valueOf(2048));
/*  304 */     flags.put("PersAsPub", Integer.valueOf(4096));
/*  305 */     flags.put("PersAsQueue", Integer.valueOf(8192));
/*  306 */     flags.put("AddName", Integer.valueOf(16384));
/*  307 */     flags.put("NoAlter", Integer.valueOf(32768));
/*  308 */     flags.put("FullResp", Integer.valueOf(65536));
/*  309 */     flags.put("JoinShared", Integer.valueOf(131072));
/*  310 */     flags.put("JoinExcl", Integer.valueOf(262144));
/*  311 */     flags.put("LeaveOnly", Integer.valueOf(524288));
/*  312 */     flags.put("VariableUserId", Integer.valueOf(1048576));
/*  313 */     flags.put("Locked", Integer.valueOf(2097152));
/*      */     
/*  315 */     optionFields.put("MQPSRegOpts", flags);
/*      */ 
/*      */     
/*  318 */     flags = new Hashtable<>();
/*  319 */     flags.put("CorrelAsId", Integer.valueOf(1));
/*  320 */     flags.put("RetainPub", Integer.valueOf(2));
/*  321 */     flags.put("OtherSubsOnly", Integer.valueOf(4));
/*  322 */     flags.put("NoReg", Integer.valueOf(8));
/*  323 */     flags.put("IsRetainedPub", Integer.valueOf(16));
/*  324 */     optionFields.put("MQPSPubOpts", flags);
/*      */     
/*  326 */     if (Trace.isOn) {
/*  327 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isOptionField(String fieldname) {
/*  336 */     if (Trace.isOn) {
/*  337 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionField(String)", new Object[] { fieldname });
/*      */     }
/*      */     
/*  340 */     boolean traceRet1 = optionFields.containsKey(fieldname);
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionField(String)", 
/*  343 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  345 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int optionToFlag(String field, String option) throws JMSException {
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "optionToFlag(String,String)", new Object[] { field, option });
/*      */     }
/*      */     
/*  365 */     int out = 0;
/*      */     try {
/*  367 */       boolean interpretAsDecimal = false;
/*  368 */       Hashtable flags = (Hashtable)optionFields.get(field);
/*      */       
/*  370 */       if (flags != null) {
/*  371 */         Integer v = (Integer)flags.get(option);
/*      */         
/*  373 */         if (v != null) {
/*  374 */           out = v.intValue();
/*      */         } else {
/*  376 */           interpretAsDecimal = true;
/*      */         } 
/*      */       } else {
/*  379 */         interpretAsDecimal = true;
/*      */       } 
/*      */       
/*  382 */       if (interpretAsDecimal) {
/*      */         try {
/*  384 */           out = Integer.parseInt(option);
/*      */         }
/*  386 */         catch (NumberFormatException nfe) {
/*  387 */           if (Trace.isOn) {
/*  388 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "optionToFlag(String,String)", nfe, 1);
/*      */           }
/*      */           
/*  391 */           JMSException je = ConfigEnvironment.newException("MQJMS3040", option);
/*      */           
/*  393 */           je.setLinkedException(nfe);
/*  394 */           if (Trace.isOn) {
/*  395 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "optionToFlag(String,String)", (Throwable)je, 1);
/*      */           }
/*      */           
/*  398 */           throw je;
/*      */         } 
/*      */       }
/*      */       
/*  402 */       if (Trace.isOn) {
/*  403 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "optionToFlag(String,String)", 
/*  404 */             Integer.valueOf(out));
/*      */       }
/*  406 */       return out;
/*      */     
/*      */     }
/*  409 */     catch (JMSException je) {
/*  410 */       if (Trace.isOn) {
/*  411 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "optionToFlag(String,String)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  414 */       if (Trace.isOn) {
/*  415 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "optionToFlag(String,String)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  418 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  427 */   private Hashtable nameValuePairs = new Hashtable<>();
/*      */ 
/*      */ 
/*      */   
/*  431 */   private int encoding = 273;
/*  432 */   private int codedCharSetId = 1208;
/*  433 */   private String format = "        ";
/*      */   
/*      */   private static final int MQRFH_ASCII_STRUCID_INT = 1380337696;
/*      */   
/*      */   private static final int MQRFH_EBCDIC_STRUCID_INT = -641284032;
/*      */ 
/*      */   
/*      */   MQBrokerMessage() {
/*  441 */     if (Trace.isOn) {
/*  442 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "<init>()");
/*      */     }
/*      */     
/*  445 */     if (Trace.isOn) {
/*  446 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void set(String name, String value) throws JMSException {
/*  461 */     if (Trace.isOn) {
/*  462 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,String)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  467 */       if (isOptionField(name)) {
/*      */         
/*  469 */         JMSException je = ConfigEnvironment.newException("MQJMS3040", name);
/*  470 */         if (Trace.isOn) {
/*  471 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,String)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  474 */         throw je;
/*      */       } 
/*      */       
/*  477 */       this.nameValuePairs.put(name, value);
/*      */     }
/*  479 */     catch (JMSException je) {
/*  480 */       if (Trace.isOn) {
/*  481 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  484 */       if (Trace.isOn) {
/*  485 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,String)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  488 */       throw je;
/*      */     } 
/*      */     
/*  491 */     if (Trace.isOn) {
/*  492 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void set(String name, int options) {
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,int)", new Object[] { name, 
/*  507 */             Integer.valueOf(options) });
/*      */     }
/*      */     
/*  510 */     this.nameValuePairs.put(name, Integer.valueOf(options));
/*      */     
/*  512 */     if (Trace.isOn) {
/*  513 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "set(String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOption(String name, String option) throws JMSException {
/*  529 */     if (Trace.isOn) {
/*  530 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,String)", new Object[] { name, option });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  535 */       int flag = optionToFlag(name, option);
/*  536 */       setOption(name, flag);
/*      */     }
/*  538 */     catch (JMSException je) {
/*  539 */       if (Trace.isOn) {
/*  540 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  543 */       if (Trace.isOn) {
/*  544 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  547 */       throw je;
/*      */     } 
/*      */     
/*  550 */     if (Trace.isOn) {
/*  551 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOption(String name, int option) throws JMSException {
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,int)", new Object[] { name, 
/*  568 */             Integer.valueOf(option) });
/*      */     }
/*      */     
/*      */     try {
/*  572 */       Object old = this.nameValuePairs.get(name);
/*      */       
/*  574 */       int newValue = 0;
/*  575 */       if (old != null) {
/*  576 */         if (!(old instanceof Integer)) {
/*      */           
/*  578 */           JMSException je = ConfigEnvironment.newException("MQJMS3040", name);
/*  579 */           if (Trace.isOn) {
/*  580 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,int)", (Throwable)je, 1);
/*      */           }
/*      */           
/*  583 */           throw je;
/*      */         } 
/*  585 */         newValue = ((Integer)old).intValue();
/*      */       } 
/*  587 */       newValue |= option;
/*  588 */       this.nameValuePairs.put(name, Integer.valueOf(newValue));
/*      */     }
/*  590 */     catch (JMSException je) {
/*  591 */       if (Trace.isOn) {
/*  592 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,int)", (Throwable)je);
/*      */       }
/*      */       
/*  595 */       if (Trace.isOn) {
/*  596 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  599 */       throw je;
/*      */     } 
/*      */     
/*  602 */     if (Trace.isOn) {
/*  603 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setOption(String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update(String name, String valueP) throws JMSException {
/*  620 */     if (Trace.isOn) {
/*  621 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,String)", new Object[] { name, valueP });
/*      */     }
/*      */ 
/*      */     
/*  625 */     String value = valueP;
/*      */     try {
/*  627 */       value = value.trim();
/*  628 */       Object old = this.nameValuePairs.get(name);
/*  629 */       boolean option = isOptionField(name);
/*  630 */       if (option)
/*      */       {
/*      */ 
/*      */         
/*  634 */         int flag = optionToFlag(name, value);
/*      */         
/*  636 */         int newValue = 0;
/*  637 */         if (old != null) {
/*  638 */           if (!(old instanceof Integer)) {
/*      */ 
/*      */             
/*  641 */             JMSException je = ConfigEnvironment.newException("MQJMS3040", name);
/*  642 */             if (Trace.isOn) {
/*  643 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,String)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/*  647 */             throw je;
/*      */           } 
/*  649 */           newValue = ((Integer)old).intValue();
/*      */         } 
/*  651 */         newValue |= flag;
/*  652 */         this.nameValuePairs.put(name, Integer.valueOf(newValue));
/*      */       }
/*      */       else
/*      */       {
/*  656 */         if (old != null) {
/*      */           
/*  658 */           JMSException je = ConfigEnvironment.newException("MQJMS3041", name);
/*  659 */           if (Trace.isOn) {
/*  660 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,String)", (Throwable)je, 2);
/*      */           }
/*      */           
/*  663 */           throw je;
/*      */         } 
/*  665 */         this.nameValuePairs.put(name, value);
/*      */       }
/*      */     
/*  668 */     } catch (JMSException je) {
/*  669 */       if (Trace.isOn) {
/*  670 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,String)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  675 */       if (Trace.isOn) {
/*  676 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,String)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  679 */       throw je;
/*      */     } 
/*  681 */     if (Trace.isOn) {
/*  682 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update(String name, int value) throws JMSException {
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,int)", new Object[] { name, 
/*  699 */             Integer.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/*  703 */       Object old = this.nameValuePairs.get(name);
/*  704 */       if (old != null && !(old instanceof Integer)) {
/*      */         
/*  706 */         JMSException je = ConfigEnvironment.newException("MQJMS3040", name);
/*  707 */         if (Trace.isOn) {
/*  708 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,int)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  711 */         throw je;
/*      */       } 
/*      */       
/*  714 */       int newValue = 0;
/*  715 */       if (old != null) {
/*  716 */         newValue = ((Integer)old).intValue();
/*      */       }
/*  718 */       newValue |= value;
/*  719 */       this.nameValuePairs.put(name, Integer.valueOf(newValue));
/*      */     }
/*  721 */     catch (JMSException je) {
/*  722 */       if (Trace.isOn) {
/*  723 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,int)", (Throwable)je);
/*      */       }
/*      */       
/*  726 */       if (Trace.isOn) {
/*  727 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  730 */       throw je;
/*      */     } 
/*      */     
/*  733 */     if (Trace.isOn) {
/*  734 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "update(String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "clear()");
/*      */     }
/*      */     
/*  748 */     this.nameValuePairs.clear();
/*      */     
/*  750 */     if (Trace.isOn) {
/*  751 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "clear()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unset(String name) {
/*  762 */     if (Trace.isOn) {
/*  763 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unset(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  767 */     this.nameValuePairs.remove(name);
/*      */     
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unset(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetOption(String field, String option) throws JMSException {
/*  785 */     if (Trace.isOn) {
/*  786 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,String)", new Object[] { field, option });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  791 */       int flag = optionToFlag(field, option);
/*  792 */       unsetOption(field, flag);
/*      */     }
/*  794 */     catch (JMSException je) {
/*  795 */       if (Trace.isOn) {
/*  796 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  799 */       if (Trace.isOn) {
/*  800 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  803 */       throw je;
/*      */     } 
/*      */     
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetOption(String field, int value) throws JMSException {
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,int)", new Object[] { field, 
/*  823 */             Integer.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/*  827 */       Object old = this.nameValuePairs.get(field);
/*      */ 
/*      */       
/*  830 */       if (old != null && !(old instanceof Integer)) {
/*      */ 
/*      */         
/*  833 */         JMSException je = ConfigEnvironment.newException("MQJMS3040", field);
/*  834 */         if (Trace.isOn) {
/*  835 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,int)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  838 */         throw je;
/*  839 */       }  if (old != null) {
/*  840 */         int newValue = 0;
/*  841 */         newValue = ((Integer)old).intValue();
/*  842 */         newValue &= value ^ 0xFFFFFFFF;
/*  843 */         this.nameValuePairs.put(field, Integer.valueOf(newValue));
/*      */       }
/*      */     
/*  846 */     } catch (JMSException je) {
/*  847 */       if (Trace.isOn) {
/*  848 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,int)", (Throwable)je);
/*      */       }
/*      */       
/*  851 */       if (Trace.isOn) {
/*  852 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  855 */       throw je;
/*      */     } 
/*      */     
/*  858 */     if (Trace.isOn) {
/*  859 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "unsetOption(String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration getFields() {
/*  870 */     Enumeration e = this.nameValuePairs.keys();
/*      */     
/*  872 */     if (Trace.isOn) {
/*  873 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getFields()", "getter", e);
/*      */     }
/*      */     
/*  876 */     return e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String get(String name) {
/*  886 */     if (Trace.isOn) {
/*  887 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "get(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  891 */     Object v = this.nameValuePairs.get(name);
/*      */     
/*  893 */     if (v == null) {
/*  894 */       if (Trace.isOn) {
/*  895 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "get(String)", null, 1);
/*      */       }
/*      */       
/*  898 */       return null;
/*      */     } 
/*  900 */     String traceRet1 = v.toString();
/*  901 */     if (Trace.isOn) {
/*  902 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "get(String)", traceRet1, 2);
/*      */     }
/*      */     
/*  905 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOptions(String name) throws JMSException {
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getOptions(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  921 */       Object v = this.nameValuePairs.get(name);
/*      */       
/*  923 */       if (v == null) {
/*  924 */         if (Trace.isOn) {
/*  925 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getOptions(String)", 
/*  926 */               Integer.valueOf(0), 1);
/*      */         }
/*  928 */         return 0;
/*      */       } 
/*  930 */       if (v instanceof Integer) {
/*  931 */         int traceRet1 = ((Integer)v).intValue();
/*  932 */         if (Trace.isOn) {
/*  933 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getOptions(String)", 
/*  934 */               Integer.valueOf(traceRet1), 2);
/*      */         }
/*  936 */         return traceRet1;
/*      */       } 
/*      */       
/*  939 */       JMSException je = ConfigEnvironment.newException("MQJMS3040", name);
/*  940 */       if (Trace.isOn) {
/*  941 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getOptions(String)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  944 */       throw je;
/*      */     }
/*  946 */     catch (JMSException je) {
/*  947 */       if (Trace.isOn) {
/*  948 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getOptions(String)", (Throwable)je);
/*      */       }
/*      */       
/*  951 */       if (Trace.isOn) {
/*  952 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getOptions(String)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  955 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOptionSet(String field, String option) throws JMSException {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,String)", new Object[] { field, option });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  975 */       int flag = optionToFlag(field, option);
/*  976 */       boolean traceRet1 = isOptionSet(field, flag);
/*  977 */       if (Trace.isOn) {
/*  978 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,String)", 
/*  979 */             Boolean.valueOf(traceRet1));
/*      */       }
/*  981 */       return traceRet1;
/*      */     }
/*  983 */     catch (JMSException je) {
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  988 */       if (Trace.isOn) {
/*  989 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  992 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOptionSet(String field, int option) throws JMSException {
/* 1006 */     if (Trace.isOn) {
/* 1007 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,int)", new Object[] { field, 
/* 1008 */             Integer.valueOf(option) });
/*      */     }
/*      */     
/*      */     try {
/* 1012 */       Object v = this.nameValuePairs.get(field);
/*      */       
/* 1014 */       if (v == null) {
/* 1015 */         if (Trace.isOn) {
/* 1016 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,int)", 
/* 1017 */               Boolean.valueOf(false), 1);
/*      */         }
/* 1019 */         return false;
/*      */       } 
/* 1021 */       if (v instanceof Integer) {
/* 1022 */         int set = ((Integer)v).intValue();
/* 1023 */         boolean traceRet1 = ((set & option) == option);
/* 1024 */         if (Trace.isOn) {
/* 1025 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,int)", 
/* 1026 */               Boolean.valueOf(traceRet1), 2);
/*      */         }
/* 1028 */         return traceRet1;
/*      */       } 
/*      */       
/* 1031 */       JMSException je = ConfigEnvironment.newException("MQJMS3040", field);
/* 1032 */       if (Trace.isOn) {
/* 1033 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,int)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1036 */       throw je;
/*      */     }
/* 1038 */     catch (JMSException je) {
/* 1039 */       if (Trace.isOn) {
/* 1040 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,int)", (Throwable)je);
/*      */       }
/*      */       
/* 1043 */       if (Trace.isOn) {
/* 1044 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "isOptionSet(String,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1047 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int encoding) {
/* 1058 */     if (Trace.isOn) {
/* 1059 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setEncoding(int)", "setter", 
/* 1060 */           Integer.valueOf(encoding));
/*      */     }
/* 1062 */     this.encoding = encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/* 1071 */     if (Trace.isOn) {
/* 1072 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getEncoding()", "getter", 
/* 1073 */           Integer.valueOf(this.encoding));
/*      */     }
/* 1075 */     return this.encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCodedCharSetId(int codedCharSetId) {
/* 1084 */     if (Trace.isOn) {
/* 1085 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setCodedCharSetId(int)", "setter", 
/* 1086 */           Integer.valueOf(codedCharSetId));
/*      */     }
/* 1088 */     this.codedCharSetId = codedCharSetId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCodedCharSetId() {
/* 1097 */     if (Trace.isOn) {
/* 1098 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getCodedCharSetId()", "getter", 
/* 1099 */           Integer.valueOf(this.codedCharSetId));
/*      */     }
/* 1101 */     return this.codedCharSetId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String format) {
/* 1110 */     if (Trace.isOn) {
/* 1111 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "setFormat(String)", "setter", format);
/*      */     }
/*      */     
/* 1114 */     this.format = format;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/* 1123 */     if (Trace.isOn) {
/* 1124 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "getFormat()", "getter", this.format);
/*      */     }
/*      */     
/* 1127 */     return this.format;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static MQBrokerMessage fromMessage(MQMsg2 message) throws JMSException {
/* 1167 */     if (Trace.isOn) {
/* 1168 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", new Object[] { message });
/*      */     }
/*      */     
/*      */     try {
/*      */       int strucId, version;
/*      */       
/*      */       MQBrokerMessage out;
/*      */       
/*      */       try {
/* 1177 */         strucId = message.readInt(1);
/*      */       }
/* 1179 */       catch (Exception e) {
/* 1180 */         if (Trace.isOn) {
/* 1181 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", e, 1);
/*      */         }
/*      */ 
/*      */         
/* 1185 */         JMSException je = ConfigEnvironment.newException("MQJMS1089", e.toString());
/* 1186 */         je.setLinkedException(e);
/* 1187 */         if (Trace.isOn) {
/* 1188 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1191 */         throw je;
/*      */       } 
/*      */       
/*      */       try {
/* 1195 */         version = message.readInt(message.getEncoding());
/*      */       }
/* 1197 */       catch (Exception e) {
/* 1198 */         if (Trace.isOn) {
/* 1199 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", e, 2);
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 1204 */           message.skipReadingBytes(-4);
/*      */         }
/* 1206 */         catch (Exception e2) {
/* 1207 */           if (Trace.isOn) {
/* 1208 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", e2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1214 */         JMSException je = ConfigEnvironment.newException("MQJMS1089", e.toString());
/* 1215 */         je.setLinkedException(e);
/* 1216 */         if (Trace.isOn) {
/* 1217 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1220 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1225 */         message.skipReadingBytes(-8);
/*      */       }
/* 1227 */       catch (Exception e) {
/* 1228 */         if (Trace.isOn) {
/* 1229 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", e, 4);
/*      */         }
/*      */ 
/*      */         
/* 1233 */         JMSException je = ConfigEnvironment.newException("MQJMS1089", e.toString());
/* 1234 */         je.setLinkedException(e);
/* 1235 */         if (Trace.isOn) {
/* 1236 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1239 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1245 */       if (strucId == 1380337696 || strucId == -641284032) {
/*      */         
/* 1247 */         if (version == 1) {
/* 1248 */           out = new RFH1BrokerMessageImpl();
/* 1249 */         } else if (version == 2) {
/* 1250 */           out = new RFH2BrokerMessageImpl();
/*      */         } else {
/* 1252 */           out = null;
/*      */         } 
/*      */       } else {
/* 1255 */         out = null;
/*      */       } 
/*      */       
/* 1258 */       if (out == null) {
/* 1259 */         if (Trace.isOn) {
/* 1260 */           Trace.traceData("MQBrokerMessage", "ProviderMessage header not understood", null);
/*      */         }
/*      */         
/* 1263 */         JMSException je = ConfigEnvironment.newException("MQJMS3042");
/* 1264 */         if (Trace.isOn) {
/* 1265 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 1268 */         throw je;
/*      */       } 
/*      */       
/* 1271 */       out.initializeFromMessage(message);
/* 1272 */       if (Trace.isOn) {
/* 1273 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", out);
/*      */       }
/*      */       
/* 1276 */       return out;
/*      */     }
/* 1278 */     catch (JMSException je) {
/* 1279 */       JMSException jMSException1; if (Trace.isOn) {
/* 1280 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", (Throwable)jMSException1, 5);
/*      */       }
/*      */       
/* 1283 */       if (Trace.isOn) {
/* 1284 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerMessage", "fromMessage(MQMsg2)", (Throwable)jMSException1, 5);
/*      */       }
/*      */       
/* 1287 */       throw jMSException1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void writeToMessage(MQMsg2 paramMQMsg2) throws JMSException;
/*      */   
/*      */   public abstract void initializeFromMessage(MQMsg2 paramMQMsg2) throws JMSException;
/*      */   
/*      */   public abstract String getHeaderFormat();
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQBrokerMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */