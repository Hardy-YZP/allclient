/*    */ package com.ibm.mq.headers.pcf;
/*    */ 
/*    */ import com.ibm.mq.headers.internal.Header;
/*    */ import com.ibm.mq.headers.internal.HeaderType;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PCFHeader
/*    */   extends Header
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFHeader.java";
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.headers.pcf.PCFHeader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFHeader.java");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract int getType();
/*    */   
/*    */   protected PCFHeader(HeaderType type) {
/* 46 */     super(type);
/* 47 */     if (Trace.isOn) {
/* 48 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFHeader", "<init>(HeaderType)", new Object[] { type });
/*    */     }
/*    */     
/* 51 */     if (Trace.isOn)
/* 52 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeader", "<init>(HeaderType)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */