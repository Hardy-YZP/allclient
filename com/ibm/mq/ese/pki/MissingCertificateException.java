/*    */ package com.ibm.mq.ese.pki;
/*    */ 
/*    */ import com.ibm.mq.ese.core.AMBIException;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MissingCertificateException
/*    */   extends AMBIException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/MissingCertificateException.java";
/*    */   private static final long serialVersionUID = 5217080581921053932L;
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.ese.pki.MissingCertificateException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/MissingCertificateException.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MissingCertificateException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 52 */     super(msg, messageid, explanation, useraction, inserts);
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MissingCertificateException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 66 */     super(messageid, inserts);
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.entry(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*    */     }
/*    */     
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.exit(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MissingCertificateException(String messageid) {
/* 79 */     super(messageid);
/* 80 */     if (Trace.isOn) {
/* 81 */       Trace.entry(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String)", new Object[] { messageid });
/*    */     }
/*    */     
/* 84 */     if (Trace.isOn) {
/* 85 */       Trace.exit(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MissingCertificateException(String messageid, Throwable cause) {
/* 91 */     super(messageid, cause);
/* 92 */     if (Trace.isOn) {
/* 93 */       Trace.entry(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*    */     }
/*    */     
/* 96 */     if (Trace.isOn)
/* 97 */       Trace.exit(this, "com.ibm.mq.ese.pki.MissingCertificateException", "<init>(String,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\MissingCertificateException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */