/*     */ package com.ibm.mq.jmqi.handles;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ public class HmsgImpl
/*     */   extends JmqiObject
/*     */   implements Hmsg
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/HmsgImpl.java";
/*     */   private long value;
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.handles.HmsgImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/HmsgImpl.java");
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
/*     */   public HmsgImpl(JmqiEnvironment env, long value) {
/*  57 */     super(env);
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.HmsgImpl", "<init>(JmqiEnvironment,long)", new Object[] { env, 
/*  60 */             Long.valueOf(value) });
/*     */     }
/*  62 */     this.value = value;
/*     */     
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.HmsgImpl", "<init>(JmqiEnvironment,long)");
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
/*     */   public long getLongHandle() {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.data(this, "com.ibm.mq.jmqi.handles.HmsgImpl", "getLongHandle()", "getter", 
/*  79 */           Long.valueOf(this.value));
/*     */     }
/*  81 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getValue() {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.data(this, "com.ibm.mq.jmqi.handles.HmsgImpl", "getValue()", "getter", 
/*  92 */           Long.valueOf(this.value));
/*     */     }
/*  94 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(long newValue) {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.mq.jmqi.handles.HmsgImpl", "setValue(long)", "setter", 
/* 105 */           Long.valueOf(newValue));
/*     */     }
/* 107 */     this.value = newValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     if (this.value == 0L)
/* 117 */       return "MQHM_NONE"; 
/* 118 */     if (this.value == -1L) {
/* 119 */       return "MQHM_UNUSABLE_HMSG";
/*     */     }
/* 121 */     String stringVal = "0x" + Long.toHexString(this.value);
/* 122 */     return stringVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\HmsgImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */