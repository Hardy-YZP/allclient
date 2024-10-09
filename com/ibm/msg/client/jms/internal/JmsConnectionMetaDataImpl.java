/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionMetaData;
/*     */ import com.ibm.msg.client.provider.ProviderConnection;
/*     */ import com.ibm.msg.client.provider.ProviderMetaData;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
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
/*     */ public class JmsConnectionMetaDataImpl
/*     */   extends JmsPropertyContextImpl
/*     */   implements JmsConnectionMetaData, ProviderMetaData
/*     */ {
/*     */   private static final long serialVersionUID = -5283281176774919988L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionMetaDataImpl.java";
/*     */   private ProviderMetaData providerMetaData;
/*     */   private boolean isInitialized;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionMetaDataImpl.java");
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
/*     */   protected JmsConnectionMetaDataImpl(ProviderConnection providerConnection) throws JMSException {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "<init>(ProviderConnection)", new Object[] { providerConnection });
/*     */     }
/*     */ 
/*     */     
/*  76 */     this.providerMetaData = providerConnection.getMetaData(this);
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (this.providerMetaData instanceof JmsConnectionMetaDataImpl) {
/*  81 */       HashMap<String, String> errorData = new HashMap<>();
/*  82 */       errorData.put("MSG", "ProviderConnection ProviderMetaData cannot be another instance of this class");
/*  83 */       Trace.ffst("JmsConnectionMetaDataImpl", "<init>(ProviderConnection)", "XJ005001", errorData, JMSException.class);
/*     */     } 
/*     */     
/*  86 */     setStringProperty("XMSC_JMS_VERSION", this.providerMetaData.getJMSVersion());
/*  87 */     setIntProperty("XMSC_JMS_MAJOR_VERSION", this.providerMetaData.getJMSMajorVersion());
/*  88 */     setIntProperty("XMSC_JMS_MINOR_VERSION", this.providerMetaData.getJMSMinorVersion());
/*  89 */     setStringProperty("XMSC_PROVIDER_NAME", this.providerMetaData.getProviderName());
/*  90 */     setStringProperty("XMSC_VERSION", this.providerMetaData.getProviderVersion());
/*  91 */     setIntProperty("XMSC_MAJOR_VERSION", this.providerMetaData.getProviderMajorVersion());
/*  92 */     setIntProperty("XMSC_MINOR_VERSION", this.providerMetaData.getProviderMinorVersion());
/*     */ 
/*     */     
/*     */     try {
/*  96 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*     */     }
/*  98 */     catch (JMSException e) {
/*  99 */       if (Trace.isOn) {
/* 100 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "<init>(ProviderConnection)", "Caught expected exception", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     this.isInitialized = true;
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "<init>(ProviderConnection)");
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
/*     */   public String getJMSVersion() throws JMSException {
/* 120 */     String traceRet1 = getStringProperty("XMSC_JMS_VERSION");
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getJMSVersion()", "getter", traceRet1);
/*     */     }
/*     */     
/* 125 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getJMSMajorVersion() throws JMSException {
/* 134 */     int traceRet1 = getIntProperty("XMSC_JMS_MAJOR_VERSION");
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getJMSMajorVersion()", "getter", 
/* 137 */           Integer.valueOf(traceRet1));
/*     */     }
/* 139 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getJMSMinorVersion() throws JMSException {
/* 148 */     int traceRet1 = getIntProperty("XMSC_JMS_MINOR_VERSION");
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getJMSMinorVersion()", "getter", 
/* 151 */           Integer.valueOf(traceRet1));
/*     */     }
/* 153 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSProviderName() throws JMSException {
/* 161 */     String traceRet1 = getStringProperty("XMSC_PROVIDER_NAME");
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getJMSProviderName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 166 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderName() throws JMSException {
/* 174 */     return getJMSProviderName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderVersion() throws JMSException {
/* 182 */     String traceRet1 = getStringProperty("XMSC_VERSION");
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getProviderVersion()", "getter", traceRet1);
/*     */     }
/*     */     
/* 187 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProviderMajorVersion() throws JMSException {
/* 195 */     int traceRet1 = getIntProperty("XMSC_MAJOR_VERSION");
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getProviderMajorVersion()", "getter", 
/* 198 */           Integer.valueOf(traceRet1));
/*     */     }
/* 200 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProviderMinorVersion() throws JMSException {
/* 209 */     int traceRet1 = getIntProperty("XMSC_MINOR_VERSION");
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getProviderMinorVersion()", "getter", 
/* 212 */           Integer.valueOf(traceRet1));
/*     */     }
/* 214 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> getJMSXPropertyNames() throws JMSException {
/*     */     Enumeration<String> traceRet1;
/* 225 */     if (!(this.providerMetaData instanceof JmsConnectionMetaDataImpl)) {
/* 226 */       traceRet1 = this.providerMetaData.getJMSXPropertyNames();
/*     */     } else {
/*     */       
/* 229 */       traceRet1 = Collections.enumeration(Arrays.asList(new String[] { "JMSXUserID", "JMSXAppID", "JMSXDeliveryCount", "JMSXGroupID", "JMSXGroupSeq", "XMSC_IS_Z_SERIES" }));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getJMSXPropertyNames()", "getter", traceRet1);
/*     */     }
/*     */     
/* 240 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateProperty(String name, Object value) throws JMSException {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "validateProperty(String,Object)", new Object[] { name, value });
/*     */     }
/*     */ 
/*     */     
/* 254 */     if (this.isInitialized) {
/*     */ 
/*     */       
/* 257 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC3040", null);
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "validateProperty(String,Object)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 262 */       throw traceRet1;
/*     */     } 
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "validateProperty(String,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 277 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnectionMetaData");
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 282 */     throw traceRet1;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 290 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnectionMetaData");
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "readObject(ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 295 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesConnectionSupport(String fn) {
/*     */     boolean traceRet1;
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "doesConnectionSupport(String)", new Object[] { fn });
/*     */     }
/*     */ 
/*     */     
/* 307 */     if (!(this.providerMetaData instanceof JmsConnectionMetaDataImpl)) {
/* 308 */       traceRet1 = this.providerMetaData.doesConnectionSupport(fn);
/*     */     } else {
/*     */       
/* 311 */       traceRet1 = false;
/*     */     } 
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "doesConnectionSupport(String)", 
/* 315 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 317 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConnectionTypeName() throws JMSException {
/*     */     String traceRet1;
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getConnectionTypeName()");
/*     */     }
/* 328 */     if (!(this.providerMetaData instanceof JmsConnectionMetaDataImpl)) {
/* 329 */       traceRet1 = this.providerMetaData.getConnectionTypeName();
/*     */     } else {
/*     */       
/* 332 */       traceRet1 = "XMSC_PROVIDER_NAME";
/*     */     } 
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionMetaDataImpl", "getConnectionTypeName()", traceRet1);
/*     */     }
/*     */     
/* 338 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsConnectionMetaDataImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */