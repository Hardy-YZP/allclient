/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQJMSStringResources
/*     */   implements JMSStringResources
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQJMSStringResources.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQJMSStringResources.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private static HashMap<Integer, String> keyConvert = null;
/*     */ 
/*     */   
/*  73 */   private static Hashtable<String, String> JMS_IBM_props = null;
/*     */   
/*     */   static {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "static()");
/*     */     }
/*  79 */     keyConvert = new HashMap<>(35);
/*  80 */     keyConvert.put(Integer.valueOf(3), "MQJMS0003");
/*  81 */     keyConvert.put(Integer.valueOf(5), "MQJMS0005");
/*  82 */     keyConvert.put(Integer.valueOf(6), "MQJMS0006");
/*  83 */     keyConvert.put(Integer.valueOf(7), "MQJMS0007");
/*  84 */     keyConvert.put(Integer.valueOf(8), "MQJMS0008");
/*  85 */     keyConvert.put(Integer.valueOf(9), "MQJMS0009");
/*  86 */     keyConvert.put(Integer.valueOf(1000), "MQJMS1000");
/*  87 */     keyConvert.put(Integer.valueOf(1001), "MQJMS1016");
/*  88 */     keyConvert.put(Integer.valueOf(1002), "MQJMS1028");
/*  89 */     keyConvert.put(Integer.valueOf(1003), "MQJMS1029");
/*  90 */     keyConvert.put(Integer.valueOf(1004), "MQJMS1030");
/*  91 */     keyConvert.put(Integer.valueOf(1005), "MQJMS1031");
/*  92 */     keyConvert.put(Integer.valueOf(1006), "MQJMS1044");
/*  93 */     keyConvert.put(Integer.valueOf(1007), "MQJMS1045");
/*  94 */     keyConvert.put(Integer.valueOf(1008), "MQJMS1046");
/*  95 */     keyConvert.put(Integer.valueOf(1009), "MQJMS1047");
/*  96 */     keyConvert.put(Integer.valueOf(1010), "MQJMS1048");
/*  97 */     keyConvert.put(Integer.valueOf(1011), "MQJMS1049");
/*  98 */     keyConvert.put(Integer.valueOf(1012), "MQJMS1050");
/*  99 */     keyConvert.put(Integer.valueOf(1013), "MQJMS1051");
/* 100 */     keyConvert.put(Integer.valueOf(1014), "MQJMS1052");
/* 101 */     keyConvert.put(Integer.valueOf(1015), "MQJMS1053");
/* 102 */     keyConvert.put(Integer.valueOf(1016), "MQJMS1054");
/* 103 */     keyConvert.put(Integer.valueOf(1017), "MQJMS1055");
/* 104 */     keyConvert.put(Integer.valueOf(1018), "MQJMS1056");
/* 105 */     keyConvert.put(Integer.valueOf(1019), "MQJMS1057");
/* 106 */     keyConvert.put(Integer.valueOf(1020), "MQJMS1058");
/* 107 */     keyConvert.put(Integer.valueOf(1021), "MQJMS1066");
/* 108 */     keyConvert.put(Integer.valueOf(1022), "MQJMS1059");
/* 109 */     keyConvert.put(Integer.valueOf(1023), "MQJMS1060");
/* 110 */     keyConvert.put(Integer.valueOf(1024), "MQJMS1061");
/* 111 */     keyConvert.put(Integer.valueOf(1025), "MQJMS1062");
/* 112 */     keyConvert.put(Integer.valueOf(1026), "MQJMS1063");
/* 113 */     keyConvert.put(Integer.valueOf(1027), "MQJMS1064");
/* 114 */     keyConvert.put(Integer.valueOf(1028), "MQJMS1006");
/*     */ 
/*     */     
/* 117 */     JMS_IBM_props = new Hashtable<>(20);
/* 118 */     JMS_IBM_props.put("JMS_IBM_Format", "");
/* 119 */     JMS_IBM_props.put("JMS_IBM_MsgType", "");
/* 120 */     JMS_IBM_props.put("JMS_IBM_Feedback", "");
/* 121 */     JMS_IBM_props.put("JMS_IBM_PutApplType", "");
/* 122 */     JMS_IBM_props.put("JMS_IBM_Report_Exception", "");
/* 123 */     JMS_IBM_props.put("JMS_IBM_Report_Expiration", "");
/* 124 */     JMS_IBM_props.put("JMS_IBM_Report_COA", "");
/* 125 */     JMS_IBM_props.put("JMS_IBM_Report_COD", "");
/* 126 */     JMS_IBM_props.put("JMS_IBM_Report_PAN", "");
/* 127 */     JMS_IBM_props.put("JMS_IBM_Report_NAN", "");
/* 128 */     JMS_IBM_props.put("JMS_IBM_Report_Pass_Msg_ID", "");
/* 129 */     JMS_IBM_props.put("JMS_IBM_Report_Pass_Correl_ID", "");
/* 130 */     JMS_IBM_props.put("JMS_IBM_Encoding", "");
/* 131 */     JMS_IBM_props.put("JMS_IBM_Character_Set", "");
/* 132 */     JMS_IBM_props.put("JMS_IBM_Report_Discard_Msg", "");
/* 133 */     JMS_IBM_props.put("JMS_IBM_Last_Msg_In_Group", "");
/* 134 */     JMS_IBM_props.put("JMS_IBM_PutDate", "");
/* 135 */     JMS_IBM_props.put("JMS_IBM_PutTime", "");
/* 136 */     JMS_IBM_props.put("JMS_IBM_ArmCorrelator", "");
/* 137 */     JMS_IBM_props.put("JMS_IBM_RMCorrelator", "");
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQJMSStringResources() {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "<init>()");
/*     */     }
/*     */     
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorMessage(int key) {
/* 165 */     if (Trace.isOn)
/* 166 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getErrorMessage(int)", new Object[] {
/* 167 */             Integer.valueOf(key)
/*     */           }); 
/* 169 */     String traceRet1 = ConfigEnvironment.getErrorMessage(getNativeKey(key));
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getErrorMessage(int)", traceRet1);
/*     */     }
/*     */     
/* 174 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorMessage(int key, Object insert) {
/* 182 */     if (Trace.isOn)
/* 183 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getErrorMessage(int,Object)", new Object[] {
/* 184 */             Integer.valueOf(key), insert
/*     */           }); 
/* 186 */     String traceRet1 = ConfigEnvironment.getErrorMessage(getNativeKey(key), insert);
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getErrorMessage(int,Object)", traceRet1);
/*     */     }
/*     */     
/* 191 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorMessage(int key, Object insert1, Object insert2) {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getErrorMessage(int,Object,Object)", new Object[] {
/* 201 */             Integer.valueOf(key), insert1, insert2
/*     */           });
/*     */     }
/*     */     
/* 205 */     String traceRet1 = ConfigEnvironment.getErrorMessage(getNativeKey(key), insert1, insert2);
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getErrorMessage(int,Object,Object)", traceRet1);
/*     */     }
/*     */     
/* 210 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable<String, String> getJMS_IBM_names() {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getJMS_IBM_names()", "getter", JMS_IBM_props);
/*     */     }
/*     */     
/* 222 */     return JMS_IBM_props;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage(int key) {
/* 230 */     if (Trace.isOn)
/* 231 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getMessage(int)", new Object[] {
/* 232 */             Integer.valueOf(key)
/*     */           }); 
/* 234 */     String traceRet1 = ConfigEnvironment.getMessage(getNativeKey(key));
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getMessage(int)", traceRet1);
/*     */     }
/*     */     
/* 239 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage(int key, Object insert) {
/* 247 */     if (Trace.isOn)
/* 248 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getMessage(int,Object)", new Object[] {
/* 249 */             Integer.valueOf(key), insert
/*     */           }); 
/* 251 */     String traceRet1 = ConfigEnvironment.getMessage(getNativeKey(key), insert);
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getMessage(int,Object)", traceRet1);
/*     */     }
/*     */     
/* 256 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage(int key, Object insert1, Object insert2) {
/* 264 */     if (Trace.isOn)
/* 265 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getMessage(int,Object,Object)", new Object[] {
/* 266 */             Integer.valueOf(key), insert1, insert2
/*     */           }); 
/* 268 */     String traceRet1 = ConfigEnvironment.getMessage(getNativeKey(key), insert1, insert2);
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getMessage(int,Object,Object)", traceRet1);
/*     */     }
/*     */     
/* 273 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNativeKey(int key) {
/* 281 */     if (Trace.isOn)
/* 282 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getNativeKey(int)", new Object[] {
/* 283 */             Integer.valueOf(key)
/*     */           }); 
/* 285 */     String retval = keyConvert.get(Integer.valueOf(key));
/*     */     
/* 287 */     if (retval == null) {
/* 288 */       retval = "MQJMS1016";
/*     */     }
/*     */     
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSStringResources", "getNativeKey(int)", retval);
/*     */     }
/*     */     
/* 295 */     return retval;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQJMSStringResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */