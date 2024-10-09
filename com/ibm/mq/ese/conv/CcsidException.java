/*    */ package com.ibm.mq.ese.conv;
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
/*    */ public class CcsidException
/*    */   extends AMBIException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/conv/CcsidException.java";
/*    */   private static final long serialVersionUID = 5374038539187707192L;
/*    */   
/*    */   static {
/* 38 */     if (Trace.isOn) {
/* 39 */       Trace.data("com.ibm.mq.ese.conv.CcsidException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/conv/CcsidException.java");
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
/*    */   public CcsidException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/* 53 */     super(msg, messageid, explanation, useraction, inserts);
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.entry(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*    */     }
/*    */ 
/*    */     
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.exit(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CcsidException(String messageid) {
/* 67 */     super(messageid);
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String)", new Object[] { messageid });
/*    */     }
/*    */     
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.exit(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public CcsidException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 79 */     super(messageid, inserts);
/* 80 */     if (Trace.isOn) {
/* 81 */       Trace.entry(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*    */     }
/*    */     
/* 84 */     if (Trace.isOn) {
/* 85 */       Trace.exit(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String,HashMap<String , ? extends Object>)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CcsidException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 92 */     super(messageid, inserts, cause);
/* 93 */     if (Trace.isOn) {
/* 94 */       Trace.entry(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*    */     }
/*    */ 
/*    */     
/* 98 */     if (Trace.isOn)
/* 99 */       Trace.exit(this, "com.ibm.mq.ese.conv.CcsidException", "<init>(String,HashMap<String , ? extends Object>,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\conv\CcsidException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */