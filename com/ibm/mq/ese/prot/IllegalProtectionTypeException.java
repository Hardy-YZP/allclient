/*     */ package com.ibm.mq.ese.prot;
/*     */ 
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IllegalProtectionTypeException
/*     */   extends MessageProtectionException
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/IllegalProtectionTypeException.java";
/*     */   private String qop;
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.ese.prot.IllegalProtectionTypeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/IllegalProtectionTypeException.java");
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
/*     */   public static IllegalProtectionTypeException create(String arg, Throwable e) {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry("com.ibm.mq.ese.prot.IllegalProtectionTypeException", "create(String,Throwable)", new Object[] { arg, e });
/*     */     }
/*     */     
/*  62 */     HashMap<String, Object> inserts = new HashMap<>();
/*  63 */     inserts.put("AMS_INSERT_QUALITY_OF_PROTECTION", arg);
/*  64 */     IllegalProtectionTypeException ex = new IllegalProtectionTypeException(AmsErrorMessages.mjp_msg_error_invalid_quality_of_protection_IllegalProtectionTypeException, inserts, e);
/*  65 */     ex.qop = arg;
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit("com.ibm.mq.ese.prot.IllegalProtectionTypeException", "create(String,Throwable)", ex);
/*     */     }
/*     */     
/*  70 */     return ex;
/*     */   }
/*     */   
/*     */   public IllegalProtectionTypeException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  74 */     super(msg, messageid, explanation, useraction, inserts);
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IllegalProtectionTypeException(String messageid, HashMap<String, ? extends Object> inserts) {
/*  88 */     super(messageid, inserts);
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IllegalProtectionTypeException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 101 */     super(messageid, inserts, cause);
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "<init>(String,HashMap<String , ? extends Object>,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQop() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.ese.prot.IllegalProtectionTypeException", "getQop()", "getter", this.qop);
/*     */     }
/*     */     
/* 123 */     return this.qop;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\IllegalProtectionTypeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */