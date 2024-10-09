/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQOD.java";
/*     */   protected static final int sizeOfMQODv1 = 168;
/*     */   protected static final int sizeOfMQODv2 = 200;
/*     */   
/*     */   static {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQOD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQOD.java");
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
/*  88 */   public String AlternateUserId = "";
/*     */ 
/*     */ 
/*     */   
/*  92 */   protected int browseMarkTimeout = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public String DynamicQName = MQSESSION.getProductPrefix() + ".*";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   private com.ibm.mq.jmqi.MQOD jmqiStructure = MQSESSION.getJmqiEnv().newMQOD();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public String ObjectName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public String ObjectQMgrName = "";
/*     */ 
/*     */ 
/*     */   
/* 123 */   public int ObjectType = 1;
/*     */ 
/*     */   
/*     */   public MQOD() {
/* 127 */     super(MQSESSION.getJmqiEnv());
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "<init>()");
/*     */     }
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getInvalidDestCount() {
/* 142 */     int traceRet1 = this.jmqiStructure.getInvalidDestCount();
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "getInvalidDestCount()", "getter", 
/* 145 */           Integer.valueOf(traceRet1));
/*     */     }
/* 147 */     return traceRet1;
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
/*     */   protected com.ibm.mq.jmqi.MQOD getJMQIStructure() {
/* 159 */     this.jmqiStructure.setObjectType(this.ObjectType);
/* 160 */     this.jmqiStructure.setObjectName(this.ObjectName);
/* 161 */     this.jmqiStructure.setObjectQMgrName(this.ObjectQMgrName);
/* 162 */     this.jmqiStructure.setDynamicQName(this.DynamicQName);
/* 163 */     this.jmqiStructure.setAlternateUserId(this.AlternateUserId);
/*     */     
/* 165 */     this.jmqiStructure.setKnownDestCount(this.browseMarkTimeout);
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 170 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getKnownDestCount() {
/* 178 */     int traceRet1 = this.jmqiStructure.getKnownDestCount();
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "getKnownDestCount()", "getter", 
/* 181 */           Integer.valueOf(traceRet1));
/*     */     }
/* 183 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int GetNumberofRecords() {
/* 191 */     int traceRet1 = this.jmqiStructure.getRecsPresent();
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "GetNumberofRecords()", "getter", 
/* 194 */           Integer.valueOf(traceRet1));
/*     */     }
/* 196 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getUnknownDestCount() {
/* 204 */     int traceRet1 = this.jmqiStructure.getUnknownDestCount();
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "getUnknownDestCount()", "getter", 
/* 207 */           Integer.valueOf(traceRet1));
/*     */     }
/* 209 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "updateFromJMQIStructure()");
/*     */     }
/*     */     
/* 221 */     this.ObjectType = this.jmqiStructure.getObjectType();
/* 222 */     this.ObjectName = this.jmqiStructure.getObjectName();
/* 223 */     this.ObjectQMgrName = this.jmqiStructure.getObjectQMgrName();
/* 224 */     this.DynamicQName = this.jmqiStructure.getDynamicQName();
/* 225 */     this.AlternateUserId = this.jmqiStructure.getAlternateUserId();
/* 226 */     if (Trace.isOn)
/* 227 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQOD", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQOD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */