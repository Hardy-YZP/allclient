/*    */ package com.ibm.mq.ese.prot;
/*    */ 
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
/*    */ public class IllegalAlgorithmNameException
/*    */   extends MessageProtectionException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/IllegalAlgorithmNameException.java";
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/IllegalAlgorithmNameException.java");
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
/*    */   public IllegalAlgorithmNameException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 52 */     super(msg, messageid, explanation, useraction, inserts);
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IllegalAlgorithmNameException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 66 */     super(messageid, inserts, cause);
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.entry(this, "com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*    */     }
/*    */ 
/*    */     
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.exit(this, "com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "<init>(String,HashMap<String , ? extends Object>,Throwable)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IllegalAlgorithmNameException(String messageid, Throwable cause) {
/* 80 */     super(messageid, cause);
/* 81 */     if (Trace.isOn) {
/* 82 */       Trace.entry(this, "com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*    */     }
/*    */     
/* 85 */     if (Trace.isOn)
/* 86 */       Trace.exit(this, "com.ibm.mq.ese.prot.IllegalAlgorithmNameException", "<init>(String,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\IllegalAlgorithmNameException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */