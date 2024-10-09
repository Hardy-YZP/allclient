/*     */ package com.ibm.msg.client.wmq.common;
/*     */ 
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
/*     */ public class WMQException
/*     */   extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = -2063475301747648279L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQException.java";
/*     */   private int compcode;
/*     */   private String messageid;
/*     */   private int reason;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.msg.client.wmq.common.WMQException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQException.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQException(String message, String messageid, int reason, int compcode) {
/*  61 */     super(message);
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.WMQException", "<init>(String,String,int,int)", new Object[] { message, messageid, 
/*  64 */             Integer.valueOf(reason), 
/*  65 */             Integer.valueOf(compcode) });
/*     */     }
/*  67 */     this.messageid = messageid;
/*  68 */     this.reason = reason;
/*  69 */     this.compcode = compcode;
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.WMQException", "<init>(String,String,int,int)");
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
/*     */   public int getCompCode() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQException", "getCompCode()", "getter", 
/*  85 */           Integer.valueOf(this.compcode));
/*     */     }
/*  87 */     return this.compcode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorCode() {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQException", "getErrorCode()", "getter", this.messageid);
/*     */     }
/*     */     
/* 100 */     return this.messageid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQException", "getReason()", "getter", 
/* 111 */           Integer.valueOf(this.reason));
/*     */     }
/* 113 */     return this.reason;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\WMQException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */