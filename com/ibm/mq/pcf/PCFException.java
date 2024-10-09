/*    */ package com.ibm.mq.pcf;
/*    */ 
/*    */ import com.ibm.mq.MQException;
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
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class PCFException
/*    */   extends MQException
/*    */ {
/*    */   private static final long serialVersionUID = 429464840265879315L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFException.java";
/*    */   
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.pcf.PCFException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFException.java");
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
/*    */   public PCFException(int compCode, int reason, Object source) {
/* 60 */     super(compCode, reason, source);
/* 61 */     if (Trace.isOn)
/* 62 */       Trace.entry(this, "com.ibm.mq.pcf.PCFException", "<init>(int,int,Object)", new Object[] {
/* 63 */             Integer.valueOf(compCode), Integer.valueOf(reason), source
/*    */           }); 
/* 65 */     if (Trace.isOn)
/* 66 */       Trace.exit(this, "com.ibm.mq.pcf.PCFException", "<init>(int,int,Object)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */