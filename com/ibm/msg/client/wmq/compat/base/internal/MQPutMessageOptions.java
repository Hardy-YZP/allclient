/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQPMO;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
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
/*     */ public class MQPutMessageOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQPutMessageOptions.java";
/*     */   protected static final int sizeofMQPutMessageOptionsv1 = 128;
/*     */   protected static final int sizeofMQPutMessageOptionsv2 = 152;
/*     */   
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQPutMessageOptions.java");
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
/*  84 */   public MQQueue contextReference = null;
/*     */   
/*  86 */   public Hobj contextReferenceHandle = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public int invalidDestCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private MQPMO jmqiStructure = MQSESSION.getJmqiEnv().newMQPMO();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public int knownDestCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean noReadBack = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public int options = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public int recordFields = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public String resolvedQueueManagerName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public String resolvedQueueName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public int unknownDestCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPutMessageOptions() {
/* 168 */     super(MQSESSION.getJmqiEnv());
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "<init>()");
/*     */     }
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "<init>()");
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
/*     */   public MQPutMessageOptions(boolean noReadBack) {
/* 189 */     this();
/* 190 */     if (Trace.isOn)
/* 191 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "<init>(boolean)", new Object[] {
/* 192 */             Boolean.valueOf(noReadBack)
/*     */           }); 
/* 194 */     if (noReadBack == true) {
/* 195 */       this.noReadBack = true;
/*     */     }
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "<init>(boolean)");
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
/*     */   protected MQPMO getJMQIStructure() {
/* 213 */     this.jmqiStructure.setOptions(this.options);
/*     */     
/* 215 */     int context = (this.contextReferenceHandle == null) ? 0 : this.contextReferenceHandle.getIntegerHandle();
/* 216 */     this.jmqiStructure.setContext(context);
/*     */     
/* 218 */     this.jmqiStructure.setResolvedQName(this.resolvedQueueName);
/* 219 */     this.jmqiStructure.setResolvedQMgrName(this.resolvedQueueManagerName);
/* 220 */     this.jmqiStructure.setKnownDestCount(this.knownDestCount);
/* 221 */     this.jmqiStructure.setUnknownDestCount(this.unknownDestCount);
/* 222 */     this.jmqiStructure.setInvalidDestCount(this.invalidDestCount);
/*     */     
/* 224 */     int localRecordFields = this.recordFields;
/* 225 */     this.jmqiStructure.setPutMsgRecFields(localRecordFields);
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 230 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "updateFromJMQIStructure()");
/*     */     }
/*     */     
/* 242 */     if (!this.noReadBack) {
/* 243 */       this.options = this.jmqiStructure.getOptions();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 249 */       this.resolvedQueueName = this.jmqiStructure.getResolvedQName();
/* 250 */       this.resolvedQueueManagerName = this.jmqiStructure.getResolvedQMgrName();
/* 251 */       this.knownDestCount = this.jmqiStructure.getKnownDestCount();
/* 252 */       this.unknownDestCount = this.jmqiStructure.getUnknownDestCount();
/* 253 */       this.invalidDestCount = this.jmqiStructure.getInvalidDestCount();
/*     */       
/* 255 */       int localRecordFields = this.jmqiStructure.getPutMsgRecFields();
/* 256 */       this.recordFields = localRecordFields;
/*     */     } 
/* 258 */     if (Trace.isOn)
/* 259 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQPutMessageOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */