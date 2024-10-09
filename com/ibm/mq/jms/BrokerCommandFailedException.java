/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.jms.JMSException;
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
/*     */ @Deprecated
/*     */ public class BrokerCommandFailedException
/*     */   extends JMSException
/*     */ {
/*     */   private static final long serialVersionUID = 5101131681424876584L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/BrokerCommandFailedException.java";
/*     */   private int reason;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jms.BrokerCommandFailedException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/BrokerCommandFailedException.java");
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
/*  57 */   private String userId = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BrokerCommandFailedException(String str) {
/*  67 */     super(str);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.jms.BrokerCommandFailedException", "<init>(String)", new Object[] { str });
/*     */     }
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.mq.jms.BrokerCommandFailedException", "<init>(String)");
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
/*     */   public BrokerCommandFailedException(String str, String errorCode) {
/*  86 */     super(str, errorCode);
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.jms.BrokerCommandFailedException", "<init>(String,String)", new Object[] { str, errorCode });
/*     */     }
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.jms.BrokerCommandFailedException", "<init>(String,String)");
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
/*     */   public void setReason(int x) {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.mq.jms.BrokerCommandFailedException", "setReason(int)", "setter", 
/* 106 */           Integer.valueOf(x));
/*     */     }
/* 108 */     this.reason = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.data(this, "com.ibm.mq.jms.BrokerCommandFailedException", "getReason()", "getter", 
/* 120 */           Integer.valueOf(this.reason));
/*     */     }
/* 122 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserId(String x) {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data(this, "com.ibm.mq.jms.BrokerCommandFailedException", "setUserId(String)", "setter", x);
/*     */     }
/*     */     
/* 134 */     this.userId = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserId() {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.jms.BrokerCommandFailedException", "getUserId()", "getter", this.userId);
/*     */     }
/*     */     
/* 146 */     return this.userId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinkedException(Exception exception) {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.data(this, "com.ibm.mq.jms.BrokerCommandFailedException", "setLinkedException(Exception)", "setter", exception);
/*     */     }
/*     */     
/* 158 */     super.setLinkedException(exception);
/*     */     try {
/* 160 */       initCause(exception);
/*     */     }
/* 162 */     catch (IllegalStateException ise) {
/* 163 */       if (Trace.isOn)
/* 164 */         Trace.catchBlock(this, "com.ibm.mq.jms.BrokerCommandFailedException", "setLinkedException(Exception)", ise); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\BrokerCommandFailedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */