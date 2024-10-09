/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMetaData;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.jms.JMSException;
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
/*     */ public class MQConnectionMetaData
/*     */   extends WMQPropertyContext
/*     */   implements ProviderMetaData
/*     */ {
/*     */   private static final long serialVersionUID = 2334756542008915894L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQConnectionMetaData.java";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQConnectionMetaData.java");
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
/*  62 */   private static final String[] SUPPORTED_JMSX = new String[] { "JMSXUserID", "JMSXAppID", "JMSXDeliveryCount", "JMSXGroupID", "JMSXGroupSeq" };
/*     */   
/*  64 */   private int connectionType = 0;
/*     */ 
/*     */   
/*     */   private static final int JMS_MAJOR = 1;
/*     */ 
/*     */   
/*     */   private static final int JMS_MINOR = 1;
/*     */   
/*     */   private static final int PROV_MAJOR = 6;
/*     */   
/*     */   private static final int PROV_MINOR = 0;
/*     */   
/*     */   private static final String PROV_NAME = "IBM MQ";
/*     */ 
/*     */   
/*     */   public MQConnectionMetaData(JmsPropertyContext propContext, MQConnection connection) throws JMSException {
/*  80 */     this(propContext, connection, 0);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "<init>(JmsPropertyContext,MQConnection)", new Object[] { propContext, connection });
/*     */     }
/*     */ 
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "<init>(JmsPropertyContext,MQConnection)");
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
/*     */   public MQConnectionMetaData(JmsPropertyContext propContext, MQConnection connection, int conntype) throws JMSException {
/* 111 */     super(propContext);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "<init>(JmsPropertyContext,MQConnection,int)", new Object[] { propContext, connection, 
/*     */             
/* 115 */             Integer.valueOf(conntype) });
/*     */     }
/* 117 */     String providerVersion = connection.getStringProperty("XMSC_WMQ_PROVIDER_VERSION");
/* 118 */     int cmdLevel = connection.getIntProperty("XMSC_WMQ_COMMAND_LEVEL");
/*     */     
/* 120 */     setIntProperty("XMSC_WMQ_COMMAND_LEVEL", cmdLevel);
/* 121 */     setStringProperty("XMSC_WMQ_PROVIDER_VERSION", providerVersion);
/* 122 */     this.connectionType = conntype;
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "<init>(JmsPropertyContext,MQConnection,int)");
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
/*     */   
/*     */   public String getJMSVersion() {
/* 138 */     String traceRet1 = "1.1";
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getJMSVersion()", "getter", traceRet1);
/*     */     }
/*     */     
/* 143 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getJMSMajorVersion() {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getJMSMajorVersion()", "getter", 
/* 156 */           Integer.valueOf(1));
/*     */     }
/* 158 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getJMSMinorVersion() {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getJMSMinorVersion()", "getter", 
/* 171 */           Integer.valueOf(1));
/*     */     }
/* 173 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSProviderName() {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getJMSProviderName()", "getter", "IBM MQ");
/*     */     }
/*     */     
/* 187 */     return "IBM MQ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderName() {
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getProviderName()", "getter", "IBM MQ");
/*     */     }
/*     */     
/* 202 */     return "IBM MQ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderVersion() {
/* 213 */     String res = "6.0";
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getProviderVersion()", "getter", res);
/*     */     }
/*     */     
/* 218 */     return res;
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
/*     */ 
/*     */   
/*     */   public int getProviderMajorVersion() {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getProviderMajorVersion()", "getter", 
/* 234 */           Integer.valueOf(6));
/*     */     }
/* 236 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProviderMinorVersion() {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getProviderMinorVersion()", "getter", 
/* 249 */           Integer.valueOf(0));
/*     */     }
/* 251 */     return 0;
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
/*     */   public Enumeration<String> getJMSXPropertyNames() throws JMSException {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getJMSXPropertyNames()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     Vector<String> tmpVec = new Vector<>(SUPPORTED_JMSX.length);
/* 276 */     for (int i = 0; i < SUPPORTED_JMSX.length; i++) {
/* 277 */       tmpVec.addElement(SUPPORTED_JMSX[i]);
/*     */     }
/*     */     
/* 280 */     Enumeration<String> traceRet1 = tmpVec.elements();
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getJMSXPropertyNames()", traceRet1);
/*     */     }
/*     */     
/* 285 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConnectionTypeName() {
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "getConnectionTypeName()", "getter", "com.ibm.msg.client.wmq");
/*     */     }
/*     */     
/* 298 */     return "com.ibm.msg.client.wmq";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "toString()");
/*     */     }
/*     */     
/* 313 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 315 */     sb.append("ProviderMetaData. JMSVersion=" + getJMSVersion());
/* 316 */     sb.append(", Provider = IBM MQ");
/* 317 */     sb.append(", ProviderVersion=" + getProviderVersion());
/*     */     
/* 319 */     String traceRet1 = sb.toString();
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "toString()", traceRet1);
/*     */     }
/*     */     
/* 324 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesConnectionSupport(String fn) {
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "doesConnectionSupport(String)", new Object[] { fn });
/*     */     }
/*     */     
/* 337 */     boolean rc = true;
/*     */     
/* 339 */     if (fn.equals("XMSC_CAPABILITY_JMS2_FUNCTION") || fn.equals("XMSC_CAPABILITY_JMS2_API")) {
/* 340 */       rc = false;
/*     */     }
/* 342 */     else if (fn.equals("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED") || fn.equals("XMSC_CAPABILITY_NATIVE_IMS")) {
/*     */       
/* 344 */       rc = false;
/*     */     } 
/*     */     
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionMetaData", "doesConnectionSupport(String)", 
/* 349 */           Boolean.valueOf(rc));
/*     */     }
/* 351 */     return rc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 360 */     return super.equals(o);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQConnectionMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */