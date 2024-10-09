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
/*    */ public class InvalidCertificateException
/*    */   extends AMBIException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/InvalidCertificateException.java";
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.ese.pki.InvalidCertificateException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/InvalidCertificateException.java");
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
/*    */   public InvalidCertificateException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 52 */     super(msg, messageid, explanation, useraction, inserts);
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidCertificateException(String messageid) {
/* 66 */     super(messageid);
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.entry(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String)", new Object[] { messageid });
/*    */     }
/*    */     
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.exit(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public InvalidCertificateException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 78 */     super(messageid, inserts);
/* 79 */     if (Trace.isOn) {
/* 80 */       Trace.entry(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*    */     }
/*    */     
/* 83 */     if (Trace.isOn) {
/* 84 */       Trace.exit(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidCertificateException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 91 */     super(messageid, inserts, cause);
/* 92 */     if (Trace.isOn) {
/* 93 */       Trace.entry(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*    */     }
/*    */ 
/*    */     
/* 97 */     if (Trace.isOn)
/* 98 */       Trace.exit(this, "com.ibm.mq.ese.pki.InvalidCertificateException", "<init>(String,HashMap<String , ? extends Object>,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\InvalidCertificateException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */