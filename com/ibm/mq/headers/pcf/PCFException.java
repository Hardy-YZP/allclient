/*    */ package com.ibm.mq.headers.pcf;
/*    */ 
/*    */ import com.ibm.mq.headers.MQDataException;
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
/*    */ public class PCFException
/*    */   extends MQDataException
/*    */ {
/*    */   private static final long serialVersionUID = 1200265518226962482L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFException.java";
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.headers.pcf.PCFException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFException.java");
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
/* 57 */     super(compCode, reason, source);
/* 58 */     if (Trace.isOn)
/* 59 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFException", "<init>(int,int,Object)", new Object[] {
/* 60 */             Integer.valueOf(compCode), Integer.valueOf(reason), source
/*    */           }); 
/* 62 */     if (Trace.isOn)
/* 63 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFException", "<init>(int,int,Object)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */