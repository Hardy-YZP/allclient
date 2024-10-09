/*    */ package com.ibm.mq.ese.service;
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
/*    */ public class UserMapException
/*    */   extends AMBIException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/UserMapException.java";
/*    */   private static final long serialVersionUID = -5709069845228200090L;
/*    */   
/*    */   static {
/* 38 */     if (Trace.isOn) {
/* 39 */       Trace.data("com.ibm.mq.ese.service.UserMapException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/UserMapException.java");
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
/*    */   public UserMapException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 53 */     super(msg, messageid, explanation, useraction, inserts);
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.exit(this, "com.ibm.mq.ese.service.UserMapException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UserMapException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 67 */     super(messageid, inserts);
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*    */     }
/*    */     
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.exit(this, "com.ibm.mq.ese.service.UserMapException", "<init>(String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UserMapException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 80 */     super(messageid, inserts, cause);
/* 81 */     if (Trace.isOn) {
/* 82 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*    */     }
/*    */ 
/*    */     
/* 86 */     if (Trace.isOn)
/* 87 */       Trace.exit(this, "com.ibm.mq.ese.service.UserMapException", "<init>(String,HashMap<String , ? extends Object>,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\UserMapException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */