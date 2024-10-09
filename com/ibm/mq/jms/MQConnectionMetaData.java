/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionMetaData;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMetaData;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.ConnectionMetaData;
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
/*     */ public class MQConnectionMetaData
/*     */   extends MQRoot
/*     */   implements ConnectionMetaData, JmsConnectionMetaData
/*     */ {
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.MQConnectionMetaData", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionMetaData.java");
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
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry("com.ibm.mq.jms.MQConnectionMetaData", "static()");
/*     */     }
/*  78 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ");
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit("com.ibm.mq.jms.MQConnectionMetaData", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected JmsConnectionMetaData commonMetaData = null;
/*     */   
/*     */   private static final long serialVersionUID = -2654952703987040469L;
/*     */   
/*     */   private boolean isProviderAdvCap = false;
/*     */   
/*     */   @Deprecated
/*     */   public MQConnectionMetaData() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "<init>()");
/*     */     }
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "<init>()");
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
/*     */   @Deprecated
/*     */   public MQConnectionMetaData(int conntype) {
/* 121 */     if (Trace.isOn)
/* 122 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "<init>(int)", new Object[] {
/* 123 */             Integer.valueOf(conntype)
/*     */           }); 
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "<init>(int)");
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
/*     */   protected MQConnectionMetaData(JmsConnectionMetaData metaData) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "<init>(JmsConnectionMetaData)", new Object[] { metaData });
/*     */     }
/*     */     
/* 142 */     setDelegate(metaData);
/*     */     
/* 144 */     if (metaData instanceof ProviderMetaData && ((ProviderMetaData)metaData).doesConnectionSupport("XMSC_WMQ_CAPABILITY_ADVANCED")) {
/* 145 */       this.isProviderAdvCap = true;
/*     */     } else {
/* 147 */       this.isProviderAdvCap = false;
/*     */     } 
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "<init>(JmsConnectionMetaData)", Boolean.valueOf(this.isProviderAdvCap));
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
/*     */   public int getJMSMajorVersion() {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSMajorVersion()");
/*     */     }
/*     */     
/* 167 */     int v = 1;
/*     */     
/*     */     try {
/* 170 */       if (this.commonMetaData != null) {
/* 171 */         v = this.commonMetaData.getJMSMajorVersion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 176 */         Log.log(this, "getJMSMajorVersion()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 179 */     } catch (JMSException e) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSMajorVersion()", (Throwable)e);
/*     */       }
/* 183 */       HashMap<String, JMSException> data = new HashMap<>();
/* 184 */       data.put("exception", e);
/* 185 */       Trace.ffst(this, "getJMSMajorVersion()", "XF00A001", data, null);
/*     */     } 
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSMajorVersion()", 
/* 190 */           Integer.valueOf(v));
/*     */     }
/* 192 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getJMSMinorVersion() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSMinorVersion()");
/*     */     }
/* 205 */     int v = 1;
/*     */     
/*     */     try {
/* 208 */       if (this.commonMetaData != null) {
/* 209 */         v = this.commonMetaData.getJMSMinorVersion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 214 */         Log.log(this, "getJMSMinorVersion()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 217 */     } catch (JMSException e) {
/* 218 */       if (Trace.isOn) {
/* 219 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSMinorVersion()", (Throwable)e);
/*     */       }
/* 221 */       HashMap<String, JMSException> data = new HashMap<>();
/* 222 */       data.put("exception", e);
/* 223 */       Trace.ffst(this, "getJMSMinorVersion()", "XF00A002", data, null);
/*     */     } 
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSMinorVersion()", 
/* 228 */           Integer.valueOf(v));
/*     */     }
/* 230 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSProviderName() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSProviderName()");
/*     */     }
/* 243 */     String v = "IBM MQ";
/*     */ 
/*     */     
/*     */     try {
/* 247 */       if (this.commonMetaData != null) {
/* 248 */         v = this.commonMetaData.getJMSProviderName();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 253 */         Log.log(this, "getJMSProviderName()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 256 */     } catch (JMSException e) {
/* 257 */       if (Trace.isOn) {
/* 258 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSProviderName()", (Throwable)e);
/*     */       }
/* 260 */       HashMap<String, JMSException> data = new HashMap<>();
/* 261 */       data.put("exception", e);
/* 262 */       Trace.ffst(this, "getJMSProviderName()", "XF00A003", data, null);
/*     */     } 
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSProviderName()", v);
/*     */     }
/* 268 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSVersion() {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSVersion()");
/*     */     }
/* 281 */     String v = "1.1";
/*     */     
/*     */     try {
/* 284 */       if (this.commonMetaData != null) {
/* 285 */         v = this.commonMetaData.getJMSVersion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 290 */         Log.log(this, "getJMSVersion()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 293 */     } catch (JMSException e) {
/* 294 */       if (Trace.isOn) {
/* 295 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSVersion()", (Throwable)e);
/*     */       }
/* 297 */       HashMap<String, JMSException> data = new HashMap<>();
/* 298 */       data.put("exception", e);
/* 299 */       Trace.ffst(this, "getJMSVersion()", "XF00A004", data, null);
/*     */     } 
/*     */     
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSVersion()", v);
/*     */     }
/* 305 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<?> getJMSXPropertyNames() throws JMSException {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSXPropertyNames()");
/*     */     }
/* 319 */     Enumeration<?> v = null;
/*     */     
/*     */     try {
/* 322 */       if (this.commonMetaData != null) {
/* 323 */         v = this.commonMetaData.getJMSXPropertyNames();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 328 */         Log.log(this, "getJMSXPropertyNames()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 331 */     } catch (JMSException e) {
/* 332 */       if (Trace.isOn) {
/* 333 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSXPropertyNames()", (Throwable)e);
/*     */       }
/*     */       
/* 336 */       HashMap<String, JMSException> data = new HashMap<>();
/* 337 */       data.put("exception", e);
/* 338 */       Trace.ffst(this, "getJMSXPropertyNames()", "XF00A005", data, null);
/*     */     } 
/*     */     
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getJMSXPropertyNames()", v);
/*     */     }
/* 344 */     return v;
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
/*     */   public int getProviderMajorVersion() {
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderMajorVersion()");
/*     */     }
/* 362 */     int v = 8;
/*     */     
/*     */     try {
/* 365 */       if (this.commonMetaData != null) {
/* 366 */         v = this.commonMetaData.getProviderMajorVersion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 371 */         Log.log(this, "getProviderMajorVersion()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 374 */     } catch (JMSException e) {
/* 375 */       if (Trace.isOn) {
/* 376 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderMajorVersion()", (Throwable)e);
/*     */       }
/*     */       
/* 379 */       HashMap<String, JMSException> data = new HashMap<>();
/* 380 */       data.put("exception", e);
/* 381 */       Trace.ffst(this, "getProviderMajorVersion()", "XF00A006", data, null);
/*     */     } 
/*     */     
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderMajorVersion()", 
/* 386 */           Integer.valueOf(v));
/*     */     }
/* 388 */     return v;
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
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderMinorVersion()");
/*     */     }
/* 402 */     int v = 0;
/*     */     
/*     */     try {
/* 405 */       if (this.commonMetaData != null) {
/* 406 */         v = this.commonMetaData.getProviderMinorVersion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 411 */         Log.log(this, "getProviderMinorVersion()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 414 */     } catch (JMSException e) {
/* 415 */       if (Trace.isOn) {
/* 416 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderMinorVersion()", (Throwable)e);
/*     */       }
/*     */       
/* 419 */       HashMap<String, JMSException> data = new HashMap<>();
/* 420 */       data.put("exception", e);
/* 421 */       Trace.ffst(this, "getProviderMinorVersion()", "XF00A007", data, null);
/*     */     } 
/*     */     
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderMinorVersion()", 
/* 426 */           Integer.valueOf(v));
/*     */     }
/* 428 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProviderVersion() {
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderVersion()");
/*     */     }
/* 441 */     String v = "7.0";
/*     */     
/*     */     try {
/* 444 */       if (this.commonMetaData != null) {
/* 445 */         v = this.commonMetaData.getProviderVersion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 450 */         Log.log(this, "getProviderVersion()", "JMSMQ1121", null);
/*     */       }
/*     */     
/* 453 */     } catch (JMSException e) {
/* 454 */       if (Trace.isOn) {
/* 455 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderVersion()", (Throwable)e);
/*     */       }
/* 457 */       HashMap<String, JMSException> data = new HashMap<>();
/* 458 */       data.put("exception", e);
/* 459 */       Trace.ffst(this, "getProviderVersion()", "XF00A008", data, null);
/*     */     } 
/*     */     
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "getProviderVersion()", v);
/*     */     }
/* 465 */     return v;
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
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionMetaData", "toString()");
/*     */     }
/* 479 */     String s = "IBM WebSpher MQ 7.0";
/*     */ 
/*     */     
/* 482 */     if (this.commonMetaData != null) {
/* 483 */       s = this.commonMetaData.toString();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 488 */       Log.log(this, "getProviderVersion()", "JMSMQ1121", null);
/*     */     } 
/*     */     
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionMetaData", "toString()", s);
/*     */     }
/* 494 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDelegate(JmsConnectionMetaData metaData) {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionMetaData", "setDelegate(JmsConnectionMetaData)", "setter", metaData);
/*     */     }
/*     */     
/* 509 */     this.commonMetaData = metaData;
/* 510 */     this.delegate = (JmsPropertyContext)this.commonMetaData;
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
/*     */   public boolean getBooleanProperty(String name) throws JMSException {
/*     */     boolean result;
/* 523 */     if (name.equals("XMSC_WMQ_CAPABILITY_ADVANCED")) {
/* 524 */       if (this.isProviderAdvCap) {
/* 525 */         result = true;
/*     */       } else {
/* 527 */         result = false;
/*     */       } 
/*     */     } else {
/* 530 */       result = super.getBooleanProperty(name);
/*     */     } 
/* 532 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQConnectionMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */