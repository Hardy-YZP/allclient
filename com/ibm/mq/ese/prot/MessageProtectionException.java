/*     */ package com.ibm.mq.ese.prot;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
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
/*     */ public class MessageProtectionException
/*     */   extends AMBIException
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/MessageProtectionException.java";
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.ese.prot.MessageProtectionException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/MessageProtectionException.java");
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
/*     */   public MessageProtectionException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  52 */     super(msg, messageid, explanation, useraction, inserts);
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProtectionException(String messageid, HashMap<String, ? extends Object> inserts) {
/*  66 */     super(messageid, inserts);
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProtectionException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/*  79 */     super(messageid, inserts, cause);
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,HashMap<String , ? extends Object>,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProtectionException(String messageid) {
/*  93 */     super(messageid);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String)", new Object[] { messageid });
/*     */     }
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MessageProtectionException(String messageid, Throwable cause) {
/* 105 */     super(messageid, cause);
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*     */     }
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(String,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProtectionException(Throwable t) {
/* 118 */     super(t);
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(Throwable)", new Object[] { t });
/*     */     }
/*     */     
/* 123 */     if (Trace.isOn)
/* 124 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionException", "<init>(Throwable)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\MessageProtectionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */