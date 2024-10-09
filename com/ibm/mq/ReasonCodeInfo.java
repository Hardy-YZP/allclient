/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ class ReasonCodeInfo
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/ReasonCodeInfo.java";
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72, 5655-R36, 5724-L26, 5655-L82                (c) Copyright IBM Corp. 2008 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final byte RCC_NORMAL = 0;
/*     */   public static final byte RCC_USAGE = 1;
/*     */   public static final byte RCC_CONFIGURATION = 2;
/*     */   public static final byte RCC_SYSTEM = 3;
/*     */   public static final byte RCC_ADAPTER = 4;
/*     */   public static final byte RCC_RESOURCE_SHORTAGE = 5;
/*     */   public static final byte RCC_UNKNOWN = 15;
/*     */   private static final byte UNKNOWN_REASON_CODE = -1;
/*     */   private static final int MINIMUM_REASON_CODE = 2001;
/*     */   private static final int MAXIMUM_REASON_CODE = 2362;
/*     */   
/*     */   static {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data("com.ibm.mq.ReasonCodeInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/ReasonCodeInfo.java");
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
/*     */   public ReasonCodeInfo() {
/* 101 */     super(MQSESSION.getJmqiEnv());
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.ReasonCodeInfo", "<init>()");
/*     */     }
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.ReasonCodeInfo", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getReasonCodeCategory(int reason) {
/*     */     int value;
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry("com.ibm.mq.ReasonCodeInfo", "getReasonCodeCategory(int)", new Object[] {
/* 115 */             Integer.valueOf(reason)
/*     */           });
/*     */     }
/*     */     
/* 119 */     if (reason < 2001 || reason >= 2362) {
/* 120 */       value = -1;
/*     */     } else {
/* 122 */       value = table[reason - 2001];
/*     */     } 
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit("com.ibm.mq.ReasonCodeInfo", "getReasonCodeCategory(int)", Integer.valueOf(value));
/*     */     }
/*     */     
/* 129 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MQResourceException getResourceException(MQException e) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry("com.ibm.mq.ReasonCodeInfo", "getResourceException(MQException)", new Object[] { e });
/*     */     }
/*     */     
/* 142 */     int compCode = e.completionCode;
/* 143 */     int reasonCode = e.reasonCode;
/* 144 */     MQResourceException out = new MQResourceException(compCode, reasonCode, e);
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit("com.ibm.mq.ReasonCodeInfo", "getResourceException(MQException)", out);
/*     */     }
/* 148 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   private static final byte[] table = new byte[] { 2, 0, 0, 4, 4, 1, 1, 1, 3, 1, 1, 0, 1, 1, -1, 2, 5, 4, 4, 1, 1, 1, 1, 5, 5, 1, 1, -1, 1, 2, 2, -1, 0, 0, 2, 1, 1, 1, 1, 1, 0, 0, 1, 4, 1, 1, 1, 1, 1, 1, 2, 2, 5, -1, 1, 5, 1, 1, 3, -1, 1, 1, 3, -1, 4, 1, 1, 1, 1, 0, 5, 1, -1, -1, 1, 1, 1, 1, 0, 1, -1, 2, -1, -1, 2, 4, 2, -1, -1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 4, 1, 3, 5, 4, 1, 2, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 5, 1, 3, 3, 3, 3, 3, 4, 1, 1, 1, 3, 4, 3, 1, 1, 2, 2, 2, 2, -1, 1, 1, 1, -1, 4, 4, 4, 4, 4, 4, 1, 4, 2, 3, 3, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 3, 3, 2, 1, 1, 4, 1, 2, 1, 1, 5, 2, 1, 3, 2, 2, 2, 2, -1, 1, 3, 3, 3, -1, 1, 1, 3, 1, -1, -1, -1, -1, -1, -1, 3, 2, 2, 4, 1, -1, 3, 3, 3, 3, 3, 3, -1, -1, -1, -1, 1, 3, 3, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 2, 2, 2, 3, 2, 1, 1, 3, 1, -1, -1, 4, 4, 3, 4, 4, 3, 3, 3, 2, 3, 3, 2, 3, 2, 0, 2, 2, 2, 3, 3, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 4, 1, 1, 4, 0, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 2, 4, 1, 1, 1, 4, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 3, 2, 0, 3, 0, 0, 2, 2, 2, 1, 1, 1, 3, 1, 1, 3, 1, 0, 1, 4, 3, 3, 3 };
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ReasonCodeInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */