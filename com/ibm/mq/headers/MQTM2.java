/*    */ package com.ibm.mq.headers;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.io.DataInput;
/*    */ import java.io.IOException;
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
/*    */ public class MQTM2
/*    */   extends MQTMC2
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQTM2.java";
/*    */   
/*    */   static {
/* 32 */     if (Trace.isOn) {
/* 33 */       Trace.data("com.ibm.mq.headers.MQTM2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQTM2.java");
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
/*    */   public MQTM2() {
/* 46 */     if (Trace.isOn) {
/* 47 */       Trace.entry(this, "com.ibm.mq.headers.MQTM2", "<init>()");
/*    */     }
/* 49 */     if (Trace.isOn) {
/* 50 */       Trace.exit(this, "com.ibm.mq.headers.MQTM2", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MQTM2(DataInput message) throws MQDataException, IOException {
/* 61 */     super(message);
/* 62 */     if (Trace.isOn) {
/* 63 */       Trace.entry(this, "com.ibm.mq.headers.MQTM2", "<init>(DataInput)", new Object[] { message });
/*    */     }
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.exit(this, "com.ibm.mq.headers.MQTM2", "<init>(DataInput)");
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
/*    */   public MQTM2(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 79 */     super(message, encoding, characterSet);
/* 80 */     if (Trace.isOn) {
/* 81 */       Trace.entry(this, "com.ibm.mq.headers.MQTM2", "<init>(DataInput,int,int)", new Object[] { message, 
/* 82 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*    */     }
/* 84 */     if (Trace.isOn)
/* 85 */       Trace.exit(this, "com.ibm.mq.headers.MQTM2", "<init>(DataInput,int,int)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQTM2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */