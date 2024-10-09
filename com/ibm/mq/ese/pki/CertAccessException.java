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
/*    */ 
/*    */ public class CertAccessException
/*    */   extends AMBIException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CertAccessException.java";
/*    */   private static final long serialVersionUID = -816314560819294346L;
/*    */   
/*    */   static {
/* 38 */     if (Trace.isOn) {
/* 39 */       Trace.data("com.ibm.mq.ese.pki.CertAccessException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CertAccessException.java");
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
/*    */   public CertAccessException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 52 */     super(msg, messageid, explanation, useraction, inserts);
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CertAccessException(AMBIException e) {
/* 66 */     super((Throwable)e);
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessException", "<init>(AMBIException)", new Object[] { e });
/*    */     }
/*    */     
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessException", "<init>(AMBIException)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public CertAccessException(String messageid) {
/* 78 */     super(messageid);
/* 79 */     if (Trace.isOn) {
/* 80 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessException", "<init>(String)", new Object[] { messageid });
/*    */     }
/*    */     
/* 83 */     if (Trace.isOn)
/* 84 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\CertAccessException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */