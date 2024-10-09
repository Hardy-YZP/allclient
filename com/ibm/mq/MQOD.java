/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.MQOR;
/*     */ import com.ibm.mq.jmqi.MQRR;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
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
/*     */ public class MQOD
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQOD.java";
/*     */   
/*     */   static {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.data("com.ibm.mq.MQOD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQOD.java");
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
/*  91 */   private MQOD jmqiStructure = MQSESSION.getJmqiEnv().newMQOD();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public int ObjectType = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String ObjectName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public String ObjectQMgrName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public String DynamicQName = MQSESSION.getProductPrefix() + ".*";
/*     */ 
/*     */   
/* 118 */   public String AlternateUserId = "";
/*     */ 
/*     */   
/* 121 */   private MQOR[] objectRecords = null;
/* 122 */   private MQRR[] responseRecords = null;
/* 123 */   private MQDistributionListItem[] ditems = null;
/*     */ 
/*     */ 
/*     */   
/* 127 */   protected int browseMarkTimeout = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQOD() {
/* 134 */     super(MQSESSION.getJmqiEnv());
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.entry(this, "com.ibm.mq.MQOD", "<init>()");
/*     */     }
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.MQOD", "<init>()");
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
/*     */   public MQOD(MQDistributionListItem[] ditems) {
/* 151 */     super(MQSESSION.getJmqiEnv());
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry(this, "com.ibm.mq.MQOD", "<init>(MQDistributionListItem [ ])", new Object[] { ditems });
/*     */     }
/*     */     
/* 156 */     this.jmqiStructure.setVersion(2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     this.ditems = ditems;
/* 162 */     this.objectRecords = new MQOR[ditems.length];
/* 163 */     this.responseRecords = new MQRR[ditems.length];
/* 164 */     for (int i = 0; i < ditems.length; i++) {
/* 165 */       this.objectRecords[i] = MQSESSION.getJmqiEnv().newMQOR();
/* 166 */       this.responseRecords[i] = MQSESSION.getJmqiEnv().newMQRR();
/*     */     } 
/* 168 */     this.jmqiStructure.setRecsPresent(ditems.length);
/* 169 */     this.jmqiStructure.setObjectRecords(this.objectRecords);
/* 170 */     this.jmqiStructure.setResponseRecords(this.responseRecords);
/* 171 */     getBrowseMarkTimeout();
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.MQOD", "<init>(MQDistributionListItem [ ])");
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
/*     */   private void getBrowseMarkTimeout() {
/* 194 */     String sTimeout = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           
/*     */           public String run()
/*     */           {
/*     */             try {
/* 200 */               return System.getProperty("com.ibm.mq.browsemarktimeout");
/*     */             }
/* 202 */             catch (AccessControlException ace) {
/* 203 */               return null;
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 208 */     if (null == sTimeout) {
/* 209 */       this.browseMarkTimeout = 0;
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/* 214 */         this.browseMarkTimeout = Integer.parseInt(sTimeout);
/*     */       }
/* 216 */       catch (NumberFormatException nfe) {
/* 217 */         this.browseMarkTimeout = 0;
/*     */       } 
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
/*     */   protected int getNumberofRecords() {
/* 231 */     int traceRet1 = this.jmqiStructure.getRecsPresent();
/*     */     
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.data(this, "com.ibm.mq.MQOD", "getNumberofRecords()", "getter", 
/* 235 */           Integer.valueOf(traceRet1));
/*     */     }
/* 237 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getKnownDestCount() {
/* 246 */     int traceRet1 = this.jmqiStructure.getKnownDestCount();
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.MQOD", "getKnownDestCount()", "getter", 
/* 249 */           Integer.valueOf(traceRet1));
/*     */     }
/* 251 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getUnknownDestCount() {
/* 260 */     int traceRet1 = this.jmqiStructure.getUnknownDestCount();
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.MQOD", "getUnknownDestCount()", "getter", 
/* 263 */           Integer.valueOf(traceRet1));
/*     */     }
/* 265 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getInvalidDestCount() {
/* 274 */     int traceRet1 = this.jmqiStructure.getInvalidDestCount();
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.MQOD", "getInvalidDestCount()", "getter", 
/* 277 */           Integer.valueOf(traceRet1));
/*     */     }
/* 279 */     return traceRet1;
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
/*     */   protected MQOD getJMQIStructure() {
/* 292 */     this.jmqiStructure.setObjectType(this.ObjectType);
/* 293 */     this.jmqiStructure.setObjectName(this.ObjectName);
/* 294 */     this.jmqiStructure.setObjectQMgrName(this.ObjectQMgrName);
/* 295 */     this.jmqiStructure.setDynamicQName(this.DynamicQName);
/* 296 */     this.jmqiStructure.setAlternateUserId(this.AlternateUserId);
/*     */     
/* 298 */     this.jmqiStructure.setKnownDestCount(this.browseMarkTimeout);
/* 299 */     if (this.ditems != null) {
/* 300 */       for (int i = 0; i < this.ditems.length; i++) {
/* 301 */         this.objectRecords[i].setObjectName((this.ditems[i]).queueName);
/* 302 */         this.objectRecords[i].setObjectQMgrName((this.ditems[i]).queueManagerName);
/* 303 */         this.responseRecords[i].setCompCode((this.ditems[i]).completionCode);
/* 304 */         this.responseRecords[i].setReason((this.ditems[i]).reasonCode);
/*     */       } 
/*     */     }
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.MQOD", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/* 311 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.entry(this, "com.ibm.mq.MQOD", "updateFromJMQIStructure()");
/*     */     }
/* 323 */     this.ObjectType = this.jmqiStructure.getObjectType();
/* 324 */     this.ObjectName = this.jmqiStructure.getObjectName();
/* 325 */     this.ObjectQMgrName = this.jmqiStructure.getObjectQMgrName();
/* 326 */     this.DynamicQName = this.jmqiStructure.getDynamicQName();
/* 327 */     this.AlternateUserId = this.jmqiStructure.getAlternateUserId();
/*     */     
/* 329 */     if (this.ditems != null) {
/*     */       
/* 331 */       MQOR[] objectRecords = this.jmqiStructure.getObjectRecords();
/*     */       
/* 333 */       MQRR[] responseRecords = this.jmqiStructure.getResponseRecords();
/* 334 */       for (int i = 0; i < this.jmqiStructure.getRecsPresent(); i++) {
/* 335 */         (this.ditems[i]).queueName = objectRecords[i].getObjectName();
/* 336 */         (this.ditems[i]).queueManagerName = objectRecords[i].getObjectQMgrName();
/* 337 */         (this.ditems[i]).completionCode = responseRecords[i].getCompCode();
/* 338 */         (this.ditems[i]).reasonCode = responseRecords[i].getReason();
/*     */       } 
/*     */     } 
/*     */     
/* 342 */     if (Trace.isOn)
/* 343 */       Trace.exit(this, "com.ibm.mq.MQOD", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQOD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */