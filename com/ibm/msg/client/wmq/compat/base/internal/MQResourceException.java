/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQResourceException
/*     */   extends Exception
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQResourceException.java";
/*     */   private static final long serialVersionUID = 4815162342L;
/*     */   
/*     */   static {
/*  14 */     if (Trace.isOn) {
/*  15 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQResourceException.java");
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
/*  27 */   private Exception linkedException = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQResourceException() {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "<init>()");
/*     */     }
/*     */     
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "<init>()");
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
/*     */   public MQResourceException(String msg) {
/*  51 */     super(msg);
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "<init>(String)", new Object[] { msg });
/*     */     }
/*     */     
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "<init>(String)");
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
/*     */   public MQResourceException(String msg, String reason) {
/*  70 */     super(msg + "::" + reason);
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "<init>(String,String)", new Object[] { msg, reason });
/*     */     }
/*     */     
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "<init>(String,String)");
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
/*     */   public void setLinkedException(Exception linkedException) {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "setLinkedException(Exception)", "setter", linkedException);
/*     */     }
/*     */     
/*  92 */     this.linkedException = linkedException;
/*     */     try {
/*  94 */       initCause(linkedException);
/*     */     }
/*  96 */     catch (IllegalStateException ise) {
/*  97 */       if (Trace.isOn) {
/*  98 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "setLinkedException(Exception)", ise);
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
/*     */   public Exception getLinkedException() {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQResourceException", "getLinkedException()", "getter", this.linkedException);
/*     */     }
/*     */     
/* 115 */     return this.linkedException;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQResourceException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */