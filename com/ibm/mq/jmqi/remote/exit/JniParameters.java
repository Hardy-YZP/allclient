/*    */ package com.ibm.mq.jmqi.remote.exit;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.nio.ByteBuffer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JniParameters
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/JniParameters.java";
/*    */   
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jmqi.remote.exit.JniParameters", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/JniParameters.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   String longMCAUserId = null;
/* 52 */   String longRemoteUserId = null;
/* 53 */   String sslPeerName = null;
/* 54 */   String sslRemCertIssName = null;
/* 55 */   ByteBuffer exitBuffer = null;
/* 56 */   ByteBuffer mqcsp = null;
/* 57 */   int exitNameLength = 0;
/* 58 */   int exitDataLength = 0;
/* 59 */   int msgExitsDefined = 0;
/* 60 */   byte[] msgExitPtr = null;
/* 61 */   byte[] msgUserDataPtr = null;
/* 62 */   int sendExitsDefined = 0;
/* 63 */   byte[] sendExitPtr = null;
/* 64 */   byte[] sendUserDataPtr = null;
/* 65 */   int receiveExitsDefined = 0;
/* 66 */   byte[] receiveExitPtr = null;
/* 67 */   byte[] receiveUserDataPtr = null;
/*    */   int exitBufferLength;
/*    */   int dataLength;
/* 70 */   byte[] dllHandle = null;
/* 71 */   byte[] fnPointer = null;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\exit\JniParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */