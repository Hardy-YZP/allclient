/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.internal.MQCommonServices;
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
/*     */ public abstract class OptionRule
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/OptionRule.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.headers.internal.OptionRule", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/OptionRule.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected OptionRule() {
/*  46 */     super(MQCommonServices.jmqiEnv);
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.entry(this, "com.ibm.mq.headers.internal.OptionRule", "<init>()");
/*     */     }
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.exit(this, "com.ibm.mq.headers.internal.OptionRule", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final OptionRule DEFAULT = new DefaultRule();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static OptionRule createOptionRule(MQLongField field, int value) {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry("com.ibm.mq.headers.internal.OptionRule", "createOptionRule(MQLongField,int)", new Object[] { field, 
/*  67 */             Integer.valueOf(value) });
/*     */     }
/*  69 */     OptionRule traceRet1 = new IntOptionRule(field, value);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit("com.ibm.mq.headers.internal.OptionRule", "createOptionRule(MQLongField,int)", traceRet1);
/*     */     }
/*     */     
/*  74 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static OptionRule createOptionRule(MQCharField field, String value) {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry("com.ibm.mq.headers.internal.OptionRule", "createOptionRule(MQCharField,String)", new Object[] { field, value });
/*     */     }
/*     */     
/*  87 */     OptionRule traceRet1 = new StringOptionRule(field, value);
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit("com.ibm.mq.headers.internal.OptionRule", "createOptionRule(MQCharField,String)", traceRet1);
/*     */     }
/*     */     
/*  92 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean isPresent(Header paramHeader);
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void setPresent(Header paramHeader);
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class DefaultRule
/*     */     extends OptionRule
/*     */   {
/*     */     private DefaultRule() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isPresent(Header h) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.entry(this, "com.ibm.mq.headers.internal.DefaultRule", "isPresent(Header)", new Object[] { h });
/*     */       }
/*     */       
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.exit(this, "com.ibm.mq.headers.internal.DefaultRule", "isPresent(Header)", 
/* 119 */             Boolean.valueOf(true));
/*     */       }
/* 121 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPresent(Header h) {
/* 126 */       if (Trace.isOn)
/* 127 */         Trace.data(this, "com.ibm.mq.headers.internal.DefaultRule", "setPresent(Header)", "setter", h); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\OptionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */