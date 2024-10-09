/*     */ package com.ibm.mq.jmqi.handles;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ public class Phmsg
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Phmsg.java";
/*     */   private Hmsg hmsg;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.handles.Phmsg", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Phmsg.java");
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
/*     */   public Phmsg(JmqiEnvironment env, Hmsg hmsg) {
/*  56 */     super(env);
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.Phmsg", "<init>(JmqiEnvironment,Hmsg)", new Object[] { env, hmsg });
/*     */     }
/*     */     
/*  61 */     this.hmsg = hmsg;
/*     */     
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.Phmsg", "<init>(JmqiEnvironment,Hmsg)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hmsg getHmsg() {
/*     */     Hmsg result;
/*  74 */     if (this.hmsg == CMQC.jmqi_MQHM_NONE) {
/*  75 */       result = new HmsgImpl(this.env, 0L);
/*  76 */     } else if (this.hmsg == CMQC.jmqi_MQHM_UNUSABLE_HMSG) {
/*  77 */       result = new HmsgImpl(this.env, -1L);
/*     */     } else {
/*  79 */       result = this.hmsg;
/*     */     } 
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.data(this, "com.ibm.mq.jmqi.handles.Phmsg", "getHmsg()", "getter", result);
/*     */     }
/*  85 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHmsg(Hmsg hmsg) {
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.data(this, "com.ibm.mq.jmqi.handles.Phmsg", "setHmsg(Hmsg)", "setter", hmsg);
/*     */     }
/*  95 */     Hmsg newHmsg = null;
/*  96 */     long value = hmsg.getLongHandle();
/*  97 */     if (value == 0L) {
/*  98 */       newHmsg = CMQC.jmqi_MQHM_NONE;
/*  99 */     } else if (value == -1L) {
/* 100 */       newHmsg = CMQC.jmqi_MQHM_UNUSABLE_HMSG;
/*     */     } else {
/* 102 */       newHmsg = hmsg;
/*     */     } 
/* 104 */     this.hmsg = newHmsg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     return (this.hmsg != null) ? this.hmsg.toString() : "<null>";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\Phmsg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */