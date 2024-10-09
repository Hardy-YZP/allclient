/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQPD;
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
/*     */ public class MQPropertyDescriptor
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPropertyDescriptor.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.MQPropertyDescriptor", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPropertyDescriptor.java");
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
/*  53 */   private MQPD jmqiStructure = MQSESSION.getJmqiEnv().newMQPD();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public int context = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int copyOptions = 22;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int options = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public int support = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public int version = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPropertyDescriptor() {
/* 109 */     super(MQSESSION.getJmqiEnv());
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.MQPropertyDescriptor", "<init>()");
/*     */     }
/*     */     
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.MQPropertyDescriptor", "<init>()");
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
/*     */   protected MQPD getJMQIStructure() {
/* 129 */     this.jmqiStructure.setVersion(this.version);
/* 130 */     this.jmqiStructure.setOptions(this.options);
/* 131 */     this.jmqiStructure.setContext(this.context);
/* 132 */     this.jmqiStructure.setCopyOptions(this.copyOptions);
/* 133 */     this.jmqiStructure.setSupport(this.support);
/*     */ 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.data(this, "com.ibm.mq.MQPropertyDescriptor", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 140 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.MQPropertyDescriptor", "updateFromJMQIStructure()");
/*     */     }
/* 151 */     this.version = this.jmqiStructure.getVersion();
/* 152 */     this.options = this.jmqiStructure.getOptions();
/* 153 */     this.context = this.jmqiStructure.getContext();
/* 154 */     this.copyOptions = this.jmqiStructure.getCopyOptions();
/* 155 */     this.support = this.jmqiStructure.getSupport();
/*     */ 
/*     */     
/* 158 */     if (Trace.isOn)
/* 159 */       Trace.exit(this, "com.ibm.mq.MQPropertyDescriptor", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQPropertyDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */