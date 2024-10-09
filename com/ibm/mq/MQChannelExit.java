/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ public class MQChannelExit extends JmqiObject {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQChannelExit.java";
/*     */   @Deprecated
/*     */   public static final int MQXT_CHANNEL_SEC_EXIT = 11;
/*     */   @Deprecated
/*     */   public static final int MQXT_CHANNEL_SEND_EXIT = 13;
/*     */   @Deprecated
/*     */   public static final int MQXT_CHANNEL_RCV_EXIT = 14;
/*     */   @Deprecated
/*     */   public static final int MQXR_INIT = 11;
/*     */   @Deprecated
/*     */   public static final int MQXR_TERM = 12;
/*     */   @Deprecated
/*     */   public static final int MQXR_XMIT = 14;
/*     */   @Deprecated
/*     */   public static final int MQXR_SEC_MSG = 15;
/*     */   @Deprecated
/*     */   public static final int MQXR_INIT_SEC = 16;
/*     */   @Deprecated
/*     */   public static final int MQXCC_OK = 0;
/*     */   @Deprecated
/*     */   public static final int MQXCC_SUPPRESS_FUNCTION = -1;
/*     */   @Deprecated
/*     */   public static final int MQXCC_SEND_AND_REQUEST_SEC_MSG = -3;
/*     */   @Deprecated
/*     */   public static final int MQXCC_SEND_SEC_MSG = -4;
/*     */   @Deprecated
/*     */   public static final int MQXCC_SUPPRESS_EXIT = -5;
/*     */   @Deprecated
/*     */   public static final int MQXCC_CLOSE_CHANNEL = -6;
/*     */   public int exitID;
/*     */   public int exitReason;
/*     */   public int exitResponse;
/*     */   public int maxSegmentLength;
/*     */   public byte[] exitUserArea;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.MQChannelExit", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQChannelExit.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public int fapLevel = 9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public int capabilityFlags = 1;
/*     */ 
/*     */   
/* 195 */   public int CurHdrCompression = 0;
/*     */   
/* 197 */   public int CurMsgCompression = 0;
/*     */ 
/*     */   
/* 200 */   private MQConnectionSecurityParameters csp = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQChannelExit() {
/* 206 */     super(JmqiSESSION.getJmqiEnv());
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.MQChannelExit", "<init>()");
/*     */     }
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.MQChannelExit", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQCSP(MQConnectionSecurityParameters mqcsp) {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.MQChannelExit", "setMQCSP(MQConnectionSecurityParameters)", "setter", mqcsp);
/*     */     }
/*     */     
/* 234 */     this.csp = mqcsp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionSecurityParameters getMQCSP() {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.mq.MQChannelExit", "getMQCSP()", "getter", this.csp);
/*     */     }
/* 248 */     return this.csp;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQChannelExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */