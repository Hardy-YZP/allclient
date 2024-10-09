/*    */ package com.ibm.mq.ese.util;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ public class DuplicateKeyException
/*    */   extends RuntimeException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/DuplicateKeyException.java";
/*    */   private static final long serialVersionUID = -880685864453675318L;
/*    */   private String key;
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.mq.ese.util.DuplicateKeyException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/DuplicateKeyException.java");
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
/*    */   public DuplicateKeyException(String key) {
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.entry(this, "com.ibm.mq.ese.util.DuplicateKeyException", "<init>(String)", new Object[] { key });
/*    */     }
/*    */     
/* 63 */     this.key = key;
/* 64 */     if (Trace.isOn) {
/* 65 */       Trace.exit(this, "com.ibm.mq.ese.util.DuplicateKeyException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 75 */     if (Trace.isOn) {
/* 76 */       Trace.data(this, "com.ibm.mq.ese.util.DuplicateKeyException", "getKey()", "getter", this.key);
/*    */     }
/* 78 */     return this.key;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\es\\util\DuplicateKeyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */