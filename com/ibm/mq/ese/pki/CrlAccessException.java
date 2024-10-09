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
/*    */ public class CrlAccessException
/*    */   extends AMBIException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CrlAccessException.java";
/*    */   private static final long serialVersionUID = 8099454875175326502L;
/*    */   
/*    */   static {
/* 38 */     if (Trace.isOn) {
/* 39 */       Trace.data("com.ibm.mq.ese.pki.CrlAccessException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CrlAccessException.java");
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
/*    */   public CrlAccessException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 52 */     super(msg, messageid, explanation, useraction, inserts);
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CrlAccessException(String messageid, Throwable cause) {
/* 66 */     super(messageid, cause);
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.entry(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*    */     }
/*    */     
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.exit(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String,Throwable)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public CrlAccessException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 78 */     super(messageid, inserts, cause);
/* 79 */     if (Trace.isOn) {
/* 80 */       Trace.entry(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*    */     }
/*    */ 
/*    */     
/* 84 */     if (Trace.isOn) {
/* 85 */       Trace.exit(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String,HashMap<String , ? extends Object>,Throwable)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CrlAccessException(String messageid) {
/* 92 */     super(messageid);
/* 93 */     if (Trace.isOn) {
/* 94 */       Trace.entry(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String)", new Object[] { messageid });
/*    */     }
/*    */     
/* 97 */     if (Trace.isOn)
/* 98 */       Trace.exit(this, "com.ibm.mq.ese.pki.CrlAccessException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\CrlAccessException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */