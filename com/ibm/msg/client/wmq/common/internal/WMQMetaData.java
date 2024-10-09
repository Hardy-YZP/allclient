/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMetaData;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQMetaData
/*     */   extends WMQPropertyContext
/*     */   implements ProviderMetaData
/*     */ {
/*     */   private static final long serialVersionUID = 4142330227642551805L;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQMetaData", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQMetaData.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static Vector<String> jmsXPropertyNames = new Vector<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQMetaData.java";
/*     */ 
/*     */   
/*  70 */   private int providerMajorVersion = 0;
/*     */ 
/*     */   
/*  73 */   private int providerMinorVersion = 0;
/*     */ 
/*     */   
/*     */   private String providerVersion;
/*     */ 
/*     */   
/*     */   private int cmdLevel;
/*     */   
/*  81 */   private int JMS_MINOR = 0;
/*     */   
/*  83 */   private int JMS_MAJOR = 2;
/*     */ 
/*     */   
/*  86 */   private static String providerTitle = null;
/*     */ 
/*     */   
/*     */   private final boolean isCICSUnmanaged;
/*     */ 
/*     */   
/*     */   private final boolean isIMS;
/*     */   
/*     */   private final boolean isProviderAdvCap;
/*     */ 
/*     */   
/*     */   static {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMetaData", "static()");
/*     */     }
/* 101 */     jmsXPropertyNames.add("JMSXUserID");
/* 102 */     jmsXPropertyNames.add("JMSXAppID");
/* 103 */     jmsXPropertyNames.add("JMSXDeliveryCount");
/* 104 */     jmsXPropertyNames.add("JMSXGroupID");
/* 105 */     jmsXPropertyNames.add("JMSXGroupSeq");
/* 106 */     jmsXPropertyNames.add("XMSC_IS_Z_SERIES");
/*     */ 
/*     */ 
/*     */     
/* 110 */     ComponentManager compMgr = ComponentManager.getInstance();
/* 111 */     HashMap<Object, Object> filter = new HashMap<>();
/* 112 */     filter.put("XMSC_PROVIDER_NAME", "com.ibm.msg.client.wmq");
/*     */     try {
/* 114 */       Component[] wmqComp = compMgr.getComponents("MPI", filter);
/*     */ 
/*     */       
/* 117 */       if (wmqComp.length > 0) {
/* 118 */         providerTitle = wmqComp[0].getTitle();
/*     */       }
/*     */     }
/* 121 */     catch (CSIException e) {
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.WMQMetaData", "static()", (Throwable)e);
/*     */       }
/*     */       
/* 126 */       HashMap<String, Object> data = new HashMap<>();
/* 127 */       data.put("exception", e);
/* 128 */       data.put("filter", filter);
/* 129 */       Trace.ffst("WMQMetaData", "<clinit>", "XM005001", data, null);
/*     */     } 
/*     */     
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMetaData", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQMetaData(JmsPropertyContext parentMetaData, WMQPropertyContext connection) throws JMSException {
/* 143 */     super(parentMetaData);
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "<init>(JmsPropertyContext,WMQPropertyContext)", new Object[] { parentMetaData, connection });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 150 */     this.providerVersion = connection.getStringProperty("XMSC_WMQ_PROVIDER_VERSION");
/* 151 */     this.cmdLevel = connection.getIntProperty("XMSC_WMQ_COMMAND_LEVEL");
/*     */     
/* 153 */     setIntProperty("XMSC_WMQ_COMMAND_LEVEL", this.cmdLevel);
/* 154 */     setStringProperty("XMSC_WMQ_PROVIDER_VERSION", this.providerVersion);
/*     */ 
/*     */     
/* 157 */     String[] versions = this.providerVersion.split("\\.");
/*     */     
/*     */     try {
/* 160 */       if (versions.length > 1) {
/* 161 */         this.providerMajorVersion = Integer.parseInt(versions[0]);
/* 162 */         if (versions.length > 2) {
/* 163 */           this.providerMinorVersion = Integer.parseInt(versions[1]);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 168 */         this.providerMajorVersion = Integer.parseInt(this.providerVersion);
/*     */       }
/*     */     
/* 171 */     } catch (NumberFormatException e) {
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "<init>(JmsPropertyContext,WMQPropertyContext)", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     this.isCICSUnmanaged = connection.getBooleanProperty("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED");
/* 182 */     this.isIMS = connection.getBooleanProperty("XMSC_CAPABILITY_NATIVE_IMS");
/* 183 */     this.isProviderAdvCap = connection.getBooleanProperty("XMSC_WMQ_CAPABILITY_ADVANCED");
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "<init>(JmsPropertyContext,WMQPropertyContext)");
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
/*     */   public boolean equals(Object o) {
/* 199 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConnectionTypeName() throws JMSException {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getConnectionTypeName()", "getter", "XMSC_PROVIDER_NAME");
/*     */     }
/*     */     
/* 211 */     return "XMSC_PROVIDER_NAME";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> getJMSXPropertyNames() throws JMSException {
/* 220 */     Enumeration<String> traceRet1 = jmsXPropertyNames.elements();
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getJMSXPropertyNames()", "getter", traceRet1);
/*     */     }
/*     */     
/* 225 */     return traceRet1;
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
/*     */   public int getJMSMajorVersion() {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getJMSMajorVersion()", "getter", 
/* 239 */           Integer.valueOf(this.JMS_MAJOR));
/*     */     }
/* 241 */     return this.JMS_MAJOR;
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
/*     */   public int getJMSMinorVersion() {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getJMSMinorVersion()", "getter", 
/* 255 */           Integer.valueOf(this.JMS_MINOR));
/*     */     }
/* 257 */     return this.JMS_MINOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSVersion() {
/* 268 */     String traceRet1 = this.JMS_MAJOR + "." + this.JMS_MINOR;
/*     */     
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getJMSVersion()", "getter", traceRet1);
/*     */     }
/*     */     
/* 274 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProviderMajorVersion() throws JMSException {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getProviderMajorVersion()", "getter", 
/* 285 */           Integer.valueOf(this.providerMajorVersion));
/*     */     }
/* 287 */     return this.providerMajorVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProviderMinorVersion() throws JMSException {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getProviderMinorVersion()", "getter", 
/* 298 */           Integer.valueOf(this.providerMinorVersion));
/*     */     }
/* 300 */     return this.providerMinorVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderName() throws JMSException {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getProviderName()", "getter", providerTitle);
/*     */     }
/*     */     
/* 313 */     return providerTitle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderVersion() throws JMSException {
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "getProviderVersion()", "getter", this.providerVersion);
/*     */     }
/*     */     
/* 327 */     return this.providerVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesConnectionSupport(String fn) {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", new Object[] { fn });
/*     */     }
/*     */     
/* 340 */     if ((fn.equals("XMSC_CAPABILITY_JMS2_FUNCTION") || fn.equals("XMSC_CAPABILITY_JMS2_API")) && this.cmdLevel >= 800) {
/* 341 */       if (Trace.isOn) {
/* 342 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", 
/* 343 */             Boolean.valueOf(true), 1);
/*     */       }
/* 345 */       return true;
/*     */     } 
/* 347 */     if (fn.equals("XMSC_CAPABILITY_JMS2_API") && this.cmdLevel >= 700) {
/* 348 */       if (Trace.isOn) {
/* 349 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", 
/* 350 */             Boolean.valueOf(true), 2);
/*     */       }
/* 352 */       return true;
/*     */     } 
/* 354 */     if (fn.equals("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED")) {
/* 355 */       if (Trace.isOn) {
/* 356 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", 
/* 357 */             Boolean.valueOf(this.isCICSUnmanaged), 3);
/*     */       }
/* 359 */       return this.isCICSUnmanaged;
/*     */     } 
/* 361 */     if (fn.equals("XMSC_CAPABILITY_NATIVE_IMS")) {
/* 362 */       if (Trace.isOn) {
/* 363 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", 
/* 364 */             Boolean.valueOf(this.isIMS), 4);
/*     */       }
/* 366 */       return this.isIMS;
/*     */     } 
/* 368 */     if (fn.equals("XMSC_WMQ_CAPABILITY_ADVANCED")) {
/* 369 */       if (Trace.isOn) {
/* 370 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", 
/* 371 */             Boolean.valueOf(this.isProviderAdvCap), 5);
/*     */       }
/* 373 */       return this.isProviderAdvCap;
/*     */     } 
/*     */     
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQMetaData", "doesConnectionSupport(String)", 
/* 378 */           Boolean.valueOf(false), 6);
/*     */     }
/* 380 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */