/*    */ package com.ibm.mq.jmqi.local;
/*    */ 
/*    */ import com.ibm.mq.jmqi.MQGMO;
/*    */ import com.ibm.mq.jmqi.MQMD;
/*    */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
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
/*    */ public class LocalTls
/*    */   extends JmqiComponentTls
/*    */ {
/*    */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalTls.java";
/*    */   byte[] rxpbBuf;
/*    */   byte[] descBuf;
/*    */   byte[] lpiOptBuf;
/*    */   byte[] optBuf;
/*    */   byte[] mqmdBuf;
/*    */   byte[] lpiCHLAUTHQueryBuff;
/*    */   public MQMD mqmd;
/*    */   public MQGMO mqgmo;
/*    */   public boolean alreadyCheckSize;
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn)
/* 35 */       Trace.data("com.ibm.mq.jmqi.local.LocalTls", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalTls.java"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\LocalTls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */