/*     */ package com.ibm.msg.client.commonservices;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InternalException
/*     */   extends RuntimeException
/*     */ {
/*     */   private static final long serialVersionUID = 179821735653335677L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/InternalException.java";
/*     */   private String ffdcFileName;
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.msg.client.commonservices.InternalException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/InternalException.java");
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
/*     */   public InternalException(String message, String ffdcFileName) {
/*  60 */     super(message);
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.msg.client.commonservices.InternalException", "<init>(String,String)", new Object[] { message, ffdcFileName });
/*     */     }
/*     */     
/*  65 */     this.ffdcFileName = ffdcFileName;
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.msg.client.commonservices.InternalException", "<init>(String,String)");
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
/*     */   public InternalException(String message, Throwable cause, String ffdcFileName) {
/*  81 */     super(message, cause);
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.msg.client.commonservices.InternalException", "<init>(String,Throwable,String)", new Object[] { message, cause, ffdcFileName });
/*     */     }
/*     */     
/*  86 */     this.ffdcFileName = ffdcFileName;
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.msg.client.commonservices.InternalException", "<init>(String,Throwable,String)");
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
/*     */   public String getFfdcFileName() {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.data(this, "com.ibm.msg.client.commonservices.InternalException", "getFfdcFileName()", "getter", this.ffdcFileName);
/*     */     }
/*     */     
/* 105 */     return this.ffdcFileName;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\InternalException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */