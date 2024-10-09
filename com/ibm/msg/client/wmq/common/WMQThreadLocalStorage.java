/*    */ package com.ibm.msg.client.wmq.common;
/*    */ 
/*    */ import com.ibm.mq.jmqi.MQRFH;
/*    */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*    */ import com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP;
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
/*    */ public class WMQThreadLocalStorage
/*    */   extends JmqiComponentTls
/*    */ {
/*    */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQThreadLocalStorage.java";
/*    */   public MQRFH mqrfh;
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.msg.client.wmq.common.WMQThreadLocalStorage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQThreadLocalStorage.java");
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
/* 51 */   public byte[] rfhBuffer = new byte[512];
/*    */ 
/*    */   
/* 54 */   public byte[] bodyBuffer = new byte[1024];
/*    */   public ReceiveZRFP receiveZRFP;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\WMQThreadLocalStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */