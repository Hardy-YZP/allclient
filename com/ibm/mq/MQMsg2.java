/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQMsg2
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQMsg2.java";
/*      */   
/*      */   static {
/*   91 */     if (Trace.isOn) {
/*   92 */       Trace.data("com.ibm.mq.MQMsg2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQMsg2.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   private static final byte[] blank8 = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32 };
/*  103 */   private static final byte[] eyecatcherMQMD = new byte[] { 77, 68, 32, 32 };
/*  104 */   private static final byte[] pad = new byte[48];
/*      */   static {
/*  106 */     if (Trace.isOn) {
/*  107 */       Trace.entry("com.ibm.mq.MQMsg2", "static()");
/*      */     }
/*  109 */     for (int i = 0; i < pad.length; i++) {
/*  110 */       pad[i] = 0;
/*      */     }
/*  112 */     if (Trace.isOn) {
/*  113 */       Trace.exit("com.ibm.mq.MQMsg2", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  125 */   private MQMD jmqiStructure = MQSESSION.getJmqiEnv().newMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long putTimeMillis;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean putTimeCached = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  162 */   private ByteBuffer messageData = null;
/*      */ 
/*      */   
/*  165 */   private int messageDataLength = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMsg2() {
/*  173 */     super(MQSESSION.getJmqiEnv());
/*  174 */     if (Trace.isOn) {
/*  175 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "<init>()");
/*      */     }
/*      */     
/*  178 */     this.jmqiStructure.setCodedCharSetId(MQSESSION.getDefaultCCSID());
/*      */     
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() throws MQException {
/*  202 */     String traceRet1 = this.jmqiStructure.getFormat();
/*  203 */     if (Trace.isOn) {
/*  204 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getFormat()", "getter", traceRet1);
/*      */     }
/*  206 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String s) throws MQException {
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setFormat(String)", "setter", s);
/*      */     }
/*  219 */     this.jmqiStructure.setFormat(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQueueName() throws MQException {
/*  229 */     String traceRet1 = this.jmqiStructure.getReplyToQ();
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getReplyToQueueName()", "getter", traceRet1);
/*      */     }
/*  233 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQueueName(String s) throws MQException {
/*  243 */     if (Trace.isOn) {
/*  244 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setReplyToQueueName(String)", "setter", s);
/*      */     }
/*  246 */     this.jmqiStructure.setReplyToQ(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQueueManagerName() throws MQException {
/*  256 */     String traceRet1 = this.jmqiStructure.getReplyToQMgr();
/*  257 */     if (Trace.isOn) {
/*  258 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getReplyToQueueManagerName()", "getter", traceRet1);
/*      */     }
/*  260 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQueueManagerName(String s) throws MQException {
/*  270 */     if (Trace.isOn) {
/*  271 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setReplyToQueueManagerName(String)", "setter", s);
/*      */     }
/*  273 */     this.jmqiStructure.setReplyToQMgr(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserId() throws MQException {
/*  283 */     String traceRet1 = this.jmqiStructure.getUserIdentifier();
/*  284 */     if (Trace.isOn) {
/*  285 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getUserId()", "getter", traceRet1);
/*      */     }
/*  287 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserId(String s) throws MQException {
/*  297 */     if (Trace.isOn) {
/*  298 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setUserId(String)", "setter", s);
/*      */     }
/*  300 */     this.jmqiStructure.setUserIdentifier(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplicationIdData() throws MQException {
/*  310 */     String traceRet1 = this.jmqiStructure.getApplIdentityData();
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getApplicationIdData()", "getter", traceRet1);
/*      */     }
/*  314 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplicationIdData(String s) throws MQException {
/*  324 */     if (Trace.isOn) {
/*  325 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setApplicationIdData(String)", "setter", s);
/*      */     }
/*  327 */     this.jmqiStructure.setApplIdentityData(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutApplicationName() throws MQException {
/*  337 */     String traceRet1 = this.jmqiStructure.getPutApplName();
/*  338 */     if (Trace.isOn) {
/*  339 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPutApplicationName()", "getter", traceRet1);
/*      */     }
/*  341 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplicationName(String s) throws MQException {
/*  351 */     if (Trace.isOn) {
/*  352 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setPutApplicationName(String)", "setter", s);
/*      */     }
/*  354 */     this.jmqiStructure.setPutApplName(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplicationOriginData() throws MQException {
/*  364 */     String traceRet1 = this.jmqiStructure.getApplOriginData();
/*  365 */     if (Trace.isOn) {
/*  366 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getApplicationOriginData()", "getter", traceRet1);
/*      */     }
/*  368 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplicationOriginData(String s) throws MQException {
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setApplicationOriginData(String)", "setter", s);
/*      */     }
/*  381 */     this.jmqiStructure.setApplOriginData(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReport() {
/*  395 */     int traceRet1 = this.jmqiStructure.getReport();
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getReport()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*  399 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReport(int i) {
/*  408 */     if (Trace.isOn) {
/*  409 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setReport(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  411 */     this.jmqiStructure.setReport(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageType() {
/*  420 */     int traceRet1 = this.jmqiStructure.getMsgType();
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getMessageType()", "getter", 
/*  423 */           Integer.valueOf(traceRet1));
/*      */     }
/*  425 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageType(int i) {
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setMessageType(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  437 */     this.jmqiStructure.setMsgType(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpiry() {
/*  446 */     int traceRet1 = this.jmqiStructure.getExpiry();
/*  447 */     if (Trace.isOn) {
/*  448 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getExpiry()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*  450 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExpiry(int i) {
/*  459 */     if (Trace.isOn) {
/*  460 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setExpiry(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  462 */     this.jmqiStructure.setExpiry(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeedback() {
/*  471 */     int traceRet1 = this.jmqiStructure.getFeedback();
/*  472 */     if (Trace.isOn) {
/*  473 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getFeedback()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*      */     
/*  476 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeedback(int i) {
/*  485 */     if (Trace.isOn) {
/*  486 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setFeedback(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  488 */     this.jmqiStructure.setFeedback(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  497 */     int traceRet1 = this.jmqiStructure.getEncoding();
/*  498 */     if (Trace.isOn) {
/*  499 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getEncoding()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*      */     
/*  502 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int i) {
/*  511 */     if (Trace.isOn) {
/*  512 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setEncoding(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  514 */     this.jmqiStructure.setEncoding(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCharacterSet() {
/*  523 */     int traceRet1 = this.jmqiStructure.getCodedCharSetId();
/*  524 */     if (Trace.isOn) {
/*  525 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getCharacterSet()", "getter", 
/*  526 */           Integer.valueOf(traceRet1));
/*      */     }
/*  528 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterSet(int i) {
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setCharacterSet(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  540 */     this.jmqiStructure.setCodedCharSetId(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPriority() {
/*  549 */     int traceRet1 = this.jmqiStructure.getPriority();
/*  550 */     if (Trace.isOn) {
/*  551 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPriority()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*      */     
/*  554 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(int i) {
/*  563 */     if (Trace.isOn) {
/*  564 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setPriority(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  566 */     this.jmqiStructure.setPriority(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPersistence() {
/*  575 */     int traceRet1 = this.jmqiStructure.getPersistence();
/*  576 */     if (Trace.isOn) {
/*  577 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPersistence()", "getter", 
/*  578 */           Integer.valueOf(traceRet1));
/*      */     }
/*  580 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistence(int i) {
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setPersistence(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  592 */     this.jmqiStructure.setPersistence(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBackoutCount() {
/*  601 */     int traceRet1 = this.jmqiStructure.getBackoutCount();
/*  602 */     if (Trace.isOn) {
/*  603 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getBackoutCount()", "getter", 
/*  604 */           Integer.valueOf(traceRet1));
/*      */     }
/*  606 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackoutCount(int i) {
/*  615 */     if (Trace.isOn) {
/*  616 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setBackoutCount(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  618 */     this.jmqiStructure.setBackoutCount(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPutApplicationType() {
/*  627 */     int traceRet1 = this.jmqiStructure.getPutApplType();
/*  628 */     if (Trace.isOn) {
/*  629 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPutApplicationType()", "getter", 
/*  630 */           Integer.valueOf(traceRet1));
/*      */     }
/*  632 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplicationType(int i) {
/*  641 */     if (Trace.isOn) {
/*  642 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setPutApplicationType(int)", "setter", 
/*  643 */           Integer.valueOf(i));
/*      */     }
/*  645 */     this.jmqiStructure.setPutApplType(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageSequenceNumber() {
/*  654 */     int traceRet1 = this.jmqiStructure.getMsgSeqNumber();
/*  655 */     if (Trace.isOn) {
/*  656 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getMessageSequenceNumber()", "getter", 
/*  657 */           Integer.valueOf(traceRet1));
/*      */     }
/*  659 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageSequenceNumber(int i) {
/*  668 */     if (Trace.isOn) {
/*  669 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setMessageSequenceNumber(int)", "setter", 
/*  670 */           Integer.valueOf(i));
/*      */     }
/*  672 */     this.jmqiStructure.setMsgSeqNumber(i);
/*      */ 
/*      */     
/*  675 */     if (this.jmqiStructure.getVersion() < 2) {
/*  676 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOffset() {
/*  686 */     int traceRet1 = this.jmqiStructure.getOffset();
/*  687 */     if (Trace.isOn) {
/*  688 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getOffset()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*  690 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOffset(int i) {
/*  699 */     if (Trace.isOn) {
/*  700 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setOffset(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  702 */     this.jmqiStructure.setOffset(i);
/*      */ 
/*      */     
/*  705 */     if (this.jmqiStructure.getVersion() < 2) {
/*  706 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageFlags() {
/*  716 */     int traceRet1 = this.jmqiStructure.getMsgFlags();
/*  717 */     if (Trace.isOn) {
/*  718 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getMessageFlags()", "getter", 
/*  719 */           Integer.valueOf(traceRet1));
/*      */     }
/*  721 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageFlags(int i) {
/*  730 */     if (Trace.isOn) {
/*  731 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setMessageFlags(int)", "setter", Integer.valueOf(i));
/*      */     }
/*  733 */     this.jmqiStructure.setMsgFlags(i);
/*      */ 
/*      */     
/*  736 */     if (this.jmqiStructure.getVersion() < 2) {
/*  737 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOriginalLength() {
/*  747 */     int traceRet1 = this.jmqiStructure.getOriginalLength();
/*  748 */     if (Trace.isOn) {
/*  749 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getOriginalLength()", "getter", 
/*  750 */           Integer.valueOf(traceRet1));
/*      */     }
/*  752 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOriginalLength(int i) {
/*  761 */     if (Trace.isOn) {
/*  762 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setOriginalLength(int)", "setter", Integer.valueOf(i));
/*      */     }
/*      */     
/*  765 */     this.jmqiStructure.setOriginalLength(i);
/*      */ 
/*      */     
/*  768 */     if (this.jmqiStructure.getVersion() < 2) {
/*  769 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMessageId() {
/*  786 */     if (Trace.isOn) {
/*  787 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "getMessageId()");
/*      */     }
/*  789 */     byte[] returnValue = new byte[24];
/*  790 */     System.arraycopy(this.jmqiStructure.getMsgId(), 0, returnValue, 0, 24);
/*      */     
/*  792 */     if (Trace.isOn) {
/*  793 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "getMessageId()", returnValue);
/*      */     }
/*  795 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageId(byte[] value) {
/*  804 */     if (Trace.isOn) {
/*  805 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setMessageId(byte [ ])", "setter", value);
/*      */     }
/*  807 */     this.jmqiStructure.setMsgId(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getCorrelationId() {
/*  816 */     if (Trace.isOn) {
/*  817 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "getCorrelationId()");
/*      */     }
/*  819 */     byte[] returnValue = new byte[24];
/*  820 */     System.arraycopy(this.jmqiStructure.getCorrelId(), 0, returnValue, 0, 24);
/*      */     
/*  822 */     if (Trace.isOn) {
/*  823 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "getCorrelationId()", returnValue);
/*      */     }
/*  825 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCorrelationId(byte[] value) {
/*  834 */     if (Trace.isOn) {
/*  835 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setCorrelationId(byte [ ])", "setter", value);
/*      */     }
/*  837 */     this.jmqiStructure.setCorrelId(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getAccountingToken() {
/*  846 */     if (Trace.isOn) {
/*  847 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "getAccountingToken()");
/*      */     }
/*  849 */     byte[] returnValue = new byte[32];
/*  850 */     System.arraycopy(this.jmqiStructure.getAccountingToken(), 0, returnValue, 0, 32);
/*      */     
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "getAccountingToken()", returnValue);
/*      */     }
/*  855 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAccountingToken(byte[] value) {
/*  864 */     if (Trace.isOn) {
/*  865 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setAccountingToken(byte [ ])", "setter", value);
/*      */     }
/*  867 */     this.jmqiStructure.setAccountingToken(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getGroupId() {
/*  876 */     if (Trace.isOn) {
/*  877 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "getGroupId()");
/*      */     }
/*  879 */     byte[] returnValue = new byte[24];
/*  880 */     System.arraycopy(this.jmqiStructure.getGroupId(), 0, returnValue, 0, 24);
/*      */     
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "getGroupId()", returnValue);
/*      */     }
/*  885 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroupId(byte[] value) {
/*  894 */     if (Trace.isOn) {
/*  895 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setGroupId(byte [ ])", "setter", value);
/*      */     }
/*  897 */     this.jmqiStructure.setGroupId(value);
/*      */ 
/*      */     
/*  900 */     if (this.jmqiStructure.getVersion() < 2) {
/*  901 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getPutDateAsBytes() {
/*  911 */     byte[] traceRet1 = this.jmqiStructure.getPutDate().getBytes(Charset.defaultCharset());
/*  912 */     if (Trace.isOn) {
/*  913 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPutDateAsBytes()", "getter", traceRet1);
/*      */     }
/*  915 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getPutTimeAsBytes() {
/*  924 */     byte[] traceRet1 = this.jmqiStructure.getPutTime().getBytes(Charset.defaultCharset());
/*  925 */     if (Trace.isOn) {
/*  926 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPutTimeAsBytes()", "getter", traceRet1);
/*      */     }
/*  928 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  938 */     int traceRet1 = this.jmqiStructure.getVersion();
/*  939 */     if (Trace.isOn) {
/*  940 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getVersion()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*  942 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPutTimeMillis() throws MQException {
/*  953 */     if (!this.putTimeCached) {
/*  954 */       this.putTimeMillis = MQDateConverter.mqDateTimeToMillis(this.jmqiStructure.getPutTime(), this.jmqiStructure.getPutDate());
/*  955 */       this.putTimeCached = true;
/*      */     } 
/*      */     
/*  958 */     if (Trace.isOn) {
/*  959 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getPutTimeMillis()", "getter", 
/*  960 */           Long.valueOf(this.putTimeMillis));
/*      */     }
/*  962 */     return this.putTimeMillis;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMessageData() {
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "getMessageData()");
/*      */     }
/*  975 */     byte[] returnData = new byte[this.messageDataLength];
/*  976 */     if (this.messageDataLength > 0) {
/*  977 */       this.messageData.position(0);
/*  978 */       this.messageData.get(returnData, 0, this.messageDataLength);
/*      */     } 
/*      */     
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "getMessageData()", returnData);
/*      */     }
/*  984 */     return returnData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMessageDataRef() {
/*  995 */     byte[] traceRet1 = null;
/*  996 */     if (this.messageData != null) {
/*  997 */       traceRet1 = this.messageData.array();
/*      */     }
/*  999 */     if (Trace.isOn) {
/* 1000 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getMessageDataRef()", "getter", traceRet1);
/*      */     }
/* 1002 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageData(byte[] data) throws MQException {
/* 1012 */     if (Trace.isOn) {
/* 1013 */       Trace.data(this, "com.ibm.mq.MQMsg2", "setMessageData(byte [ ])", "setter", data);
/*      */     }
/* 1015 */     setMessageData(data, data.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageData(byte[] data, int messageLength) {
/* 1026 */     if (Trace.isOn) {
/* 1027 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "setMessageData(byte [ ],int)", new Object[] { data, 
/* 1028 */             Integer.valueOf(messageLength) });
/*      */     }
/* 1030 */     this.messageData = ByteBuffer.allocate(messageLength);
/* 1031 */     this.messageData.put(data, 0, messageLength);
/* 1032 */     this.messageDataLength = messageLength;
/*      */     
/* 1034 */     if (Trace.isOn) {
/* 1035 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "setMessageData(byte [ ],int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setInternalBuffer(byte[] data, int len) {
/* 1055 */     if (Trace.isOn) {
/* 1056 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "setInternalBuffer(byte [ ],int)", new Object[] { data, 
/* 1057 */             Integer.valueOf(len) });
/*      */     }
/* 1059 */     this.messageData = ByteBuffer.wrap(data);
/* 1060 */     this.messageDataLength = len;
/*      */     
/* 1062 */     if (Trace.isOn) {
/* 1063 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "setInternalBuffer(byte [ ],int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ByteBuffer getInternalBuffer() {
/* 1079 */     if (Trace.isOn) {
/* 1080 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getInternalBuffer()", "getter", this.messageData);
/*      */     }
/* 1082 */     return this.messageData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageDataLength() {
/* 1091 */     if (Trace.isOn) {
/* 1092 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getMessageDataLength()", "getter", 
/* 1093 */           Integer.valueOf(this.messageDataLength));
/*      */     }
/* 1095 */     return this.messageDataLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearMessageData() {
/* 1102 */     if (Trace.isOn) {
/* 1103 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "clearMessageData()");
/*      */     }
/* 1105 */     this.messageData = null;
/* 1106 */     this.messageDataLength = 0;
/*      */     
/* 1108 */     if (Trace.isOn) {
/* 1109 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "clearMessageData()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQMD getJMQIStructure() {
/* 1121 */     if (Trace.isOn) {
/* 1122 */       Trace.data(this, "com.ibm.mq.MQMsg2", "getJMQIStructure()", "getter", this.jmqiStructure);
/*      */     }
/* 1124 */     return this.jmqiStructure;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DataInputStream readFrom(DataInputStream dataBuffer, int ccsid, boolean ccsidIsAscii) throws IOException, MQException {
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "readFrom(DataInputStream,int,boolean)", new Object[] { dataBuffer, 
/* 1142 */             Integer.valueOf(ccsid), Boolean.valueOf(ccsidIsAscii) });
/*      */     }
/* 1144 */     int bufferLen = dataBuffer.available();
/*      */     
/* 1146 */     if (bufferLen < MQMD.getSizeV1(4)) {
/* 1147 */       MQException traceRet1 = new MQException(2, 2195, this, "MQJE043");
/*      */       
/* 1149 */       if (Trace.isOn) {
/* 1150 */         Trace.throwing(this, "com.ibm.mq.MQMsg2", "readFrom(DataInputStream,int,boolean)", traceRet1, 1);
/*      */       }
/*      */       
/* 1153 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1157 */     byte[] eyecatcherBytes = new byte[4];
/* 1158 */     safeRead(dataBuffer, eyecatcherBytes, 0, 4);
/* 1159 */     if (eyecatcherBytes[0] != 77 || eyecatcherBytes[1] != 68 || eyecatcherBytes[2] != 32 || eyecatcherBytes[3] != 32) {
/*      */       
/* 1161 */       MQException traceRet2 = new MQException(2, 2195, this, "MQJE042", MQEnvironment.stringFromBytes(eyecatcherBytes));
/* 1162 */       if (Trace.isOn) {
/* 1163 */         Trace.throwing(this, "com.ibm.mq.MQMsg2", "readFrom(DataInputStream,int,boolean)", traceRet2, 2);
/*      */       }
/*      */       
/* 1166 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 1170 */     JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(this.env, 1208);
/*      */     
/* 1172 */     this.jmqiStructure.setVersion(dataBuffer.readInt());
/* 1173 */     this.jmqiStructure.setReport(dataBuffer.readInt());
/* 1174 */     this.jmqiStructure.setMsgType(dataBuffer.readInt());
/* 1175 */     this.jmqiStructure.setExpiry(dataBuffer.readInt());
/* 1176 */     this.jmqiStructure.setFeedback(dataBuffer.readInt());
/* 1177 */     this.jmqiStructure.setEncoding(dataBuffer.readInt());
/* 1178 */     this.jmqiStructure.setCodedCharSetId(dataBuffer.readInt());
/*      */     
/* 1180 */     byte[] format = new byte[8];
/* 1181 */     safeRead(dataBuffer, format, 0, 8);
/* 1182 */     this.jmqiStructure.setFormat(codepage.bytesToString(format));
/*      */     
/* 1184 */     this.jmqiStructure.setPriority(dataBuffer.readInt());
/* 1185 */     this.jmqiStructure.setPersistence(dataBuffer.readInt());
/*      */     
/* 1187 */     byte[] messageId = new byte[24];
/* 1188 */     safeRead(dataBuffer, messageId, 0, 24);
/* 1189 */     this.jmqiStructure.setMsgId(messageId);
/*      */     
/* 1191 */     byte[] correlationId = new byte[24];
/* 1192 */     safeRead(dataBuffer, correlationId, 0, 24);
/* 1193 */     this.jmqiStructure.setCorrelId(correlationId);
/* 1194 */     this.jmqiStructure.setBackoutCount(dataBuffer.readInt());
/*      */     
/* 1196 */     byte[] replyToQueueName = new byte[48];
/* 1197 */     safeRead(dataBuffer, replyToQueueName, 0, 48);
/* 1198 */     this.jmqiStructure.setReplyToQ(codepage.bytesToString(replyToQueueName));
/*      */     
/* 1200 */     byte[] replyToQueueManagerName = new byte[48];
/* 1201 */     safeRead(dataBuffer, replyToQueueManagerName, 0, 48);
/* 1202 */     this.jmqiStructure.setReplyToQMgr(codepage.bytesToString(replyToQueueManagerName));
/*      */     
/* 1204 */     byte[] userId = new byte[12];
/* 1205 */     safeRead(dataBuffer, userId, 0, 12);
/* 1206 */     this.jmqiStructure.setUserIdentifier(codepage.bytesToString(userId));
/*      */     
/* 1208 */     byte[] accountingToken = new byte[32];
/* 1209 */     safeRead(dataBuffer, accountingToken, 0, 32);
/* 1210 */     this.jmqiStructure.setAccountingToken(accountingToken);
/*      */     
/* 1212 */     byte[] applicationIdData = new byte[32];
/* 1213 */     safeRead(dataBuffer, applicationIdData, 0, 32);
/* 1214 */     this.jmqiStructure.setApplIdentityData(codepage.bytesToString(applicationIdData));
/*      */     
/* 1216 */     this.jmqiStructure.setPutApplType(dataBuffer.readInt());
/*      */     
/* 1218 */     byte[] putApplicationName = new byte[28];
/* 1219 */     safeRead(dataBuffer, putApplicationName, 0, 28);
/* 1220 */     this.jmqiStructure.setPutApplName(codepage.bytesToString(putApplicationName));
/*      */     
/* 1222 */     byte[] dateText = new byte[8];
/* 1223 */     safeRead(dataBuffer, dateText, 0, 8);
/* 1224 */     this.jmqiStructure.setPutDate(codepage.bytesToString(dateText));
/*      */     
/* 1226 */     byte[] timeText = new byte[8];
/* 1227 */     safeRead(dataBuffer, timeText, 0, 8);
/* 1228 */     this.jmqiStructure.setPutTime(codepage.bytesToString(timeText));
/* 1229 */     this.putTimeCached = false;
/*      */     
/* 1231 */     byte[] applicationOriginData = new byte[4];
/* 1232 */     safeRead(dataBuffer, applicationOriginData, 0, 4);
/* 1233 */     this.jmqiStructure.setApplOriginData(codepage.bytesToString(applicationOriginData));
/*      */     
/* 1235 */     if (this.jmqiStructure.getVersion() > 1) {
/* 1236 */       byte[] groupId = new byte[24];
/* 1237 */       safeRead(dataBuffer, groupId, 0, 24);
/* 1238 */       this.jmqiStructure.setGroupId(groupId);
/* 1239 */       this.jmqiStructure.setMsgSeqNumber(dataBuffer.readInt());
/* 1240 */       this.jmqiStructure.setOffset(dataBuffer.readInt());
/* 1241 */       this.jmqiStructure.setMsgFlags(dataBuffer.readInt());
/* 1242 */       this.jmqiStructure.setOriginalLength(dataBuffer.readInt());
/*      */     } else {
/*      */       
/* 1245 */       this.jmqiStructure.setGroupId(CMQC.MQGI_NONE);
/* 1246 */       this.jmqiStructure.setMsgSeqNumber(1);
/* 1247 */       this.jmqiStructure.setOffset(0);
/* 1248 */       this.jmqiStructure.setMsgFlags(0);
/* 1249 */       this.jmqiStructure.setOriginalLength(-1);
/*      */     } 
/* 1251 */     if (Trace.isOn) {
/* 1252 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "readFrom(DataInputStream,int,boolean)", dataBuffer);
/*      */     }
/* 1254 */     return dataBuffer;
/*      */   }
/*      */   
/*      */   private void safeRead(DataInputStream dataBuffer, byte[] dest, int offset, int len) throws IOException {
/* 1258 */     if (Trace.isOn) {
/* 1259 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "safeRead(DataInputStream,byte [ ],int,int)", new Object[] { dataBuffer, dest, 
/* 1260 */             Integer.valueOf(offset), Integer.valueOf(len) });
/*      */     }
/* 1262 */     if (dataBuffer.read(dest, offset, len) != len) {
/* 1263 */       EOFException traceRet1 = new EOFException();
/*      */       
/* 1265 */       if (Trace.isOn) {
/* 1266 */         Trace.throwing(this, "com.ibm.mq.MQMsg2", "safeRead(DataInputStream,byte [ ],int,int)", traceRet1);
/*      */       }
/*      */       
/* 1269 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1272 */     if (Trace.isOn) {
/* 1273 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "safeRead(DataInputStream,byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DataOutputStream writeTo(DataOutputStream dataBuffer, int ccsid, boolean ccsidIsAscii) throws IOException, MQException {
/* 1296 */     if (Trace.isOn) {
/* 1297 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "writeTo(DataOutputStream,int,boolean)", new Object[] { dataBuffer, 
/* 1298 */             Integer.valueOf(ccsid), Boolean.valueOf(ccsidIsAscii) });
/*      */     }
/* 1300 */     DataOutputStream outDataBuffer = writeToWithoutConversion(dataBuffer);
/*      */     
/* 1302 */     if (Trace.isOn) {
/* 1303 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "writeTo(DataOutputStream,int,boolean)", outDataBuffer);
/*      */     }
/*      */     
/* 1306 */     return outDataBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private DataOutputStream writeToWithoutConversion(DataOutputStream dataBuffer) throws IOException {
/* 1319 */     if (Trace.isOn) {
/* 1320 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "writeToWithoutConversion(DataOutputStream)", new Object[] { dataBuffer });
/*      */     }
/*      */     
/* 1323 */     dataBuffer.write(eyecatcherMQMD);
/* 1324 */     dataBuffer.writeInt(this.jmqiStructure.getVersion());
/* 1325 */     dataBuffer.writeInt(this.jmqiStructure.getReport());
/* 1326 */     dataBuffer.writeInt(this.jmqiStructure.getMsgType());
/* 1327 */     dataBuffer.writeInt(this.jmqiStructure.getExpiry());
/* 1328 */     dataBuffer.writeInt(this.jmqiStructure.getFeedback());
/* 1329 */     dataBuffer.writeInt(this.jmqiStructure.getEncoding());
/* 1330 */     dataBuffer.writeInt(this.jmqiStructure.getCodedCharSetId());
/* 1331 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getFormat(), 8);
/* 1332 */     dataBuffer.writeInt(this.jmqiStructure.getPriority());
/* 1333 */     dataBuffer.writeInt(this.jmqiStructure.getPersistence());
/* 1334 */     dataBuffer.write(this.jmqiStructure.getMsgId());
/* 1335 */     dataBuffer.write(this.jmqiStructure.getCorrelId());
/* 1336 */     dataBuffer.writeInt(this.jmqiStructure.getBackoutCount());
/* 1337 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getReplyToQ(), 48);
/* 1338 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getReplyToQMgr(), 48);
/* 1339 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getUserIdentifier(), 12);
/* 1340 */     dataBuffer.write(this.jmqiStructure.getAccountingToken());
/* 1341 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getApplIdentityData(), 32);
/* 1342 */     dataBuffer.writeInt(this.jmqiStructure.getPutApplType());
/* 1343 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getPutApplName(), 28);
/* 1344 */     dataBuffer.write(blank8);
/* 1345 */     dataBuffer.write(blank8);
/* 1346 */     writeStringValueToBuffer(dataBuffer, this.jmqiStructure.getApplOriginData(), 4);
/* 1347 */     if (this.jmqiStructure.getVersion() > 1) {
/* 1348 */       dataBuffer.write(this.jmqiStructure.getGroupId());
/* 1349 */       dataBuffer.writeInt(this.jmqiStructure.getMsgSeqNumber());
/* 1350 */       dataBuffer.writeInt(this.jmqiStructure.getOffset());
/* 1351 */       dataBuffer.writeInt(this.jmqiStructure.getMsgFlags());
/* 1352 */       dataBuffer.writeInt(this.jmqiStructure.getOriginalLength());
/*      */     } 
/*      */     
/* 1355 */     if (Trace.isOn) {
/* 1356 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "writeToWithoutConversion(DataOutputStream)", dataBuffer);
/*      */     }
/*      */     
/* 1359 */     return dataBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void writeStringValueToBuffer(DataOutputStream buffer, String value, int fieldLength) throws IOException {
/* 1371 */     if (Trace.isOn) {
/* 1372 */       Trace.entry(this, "com.ibm.mq.MQMsg2", "writeStringValueToBuffer(DataOutputStream,String,int)", new Object[] { buffer, value, 
/*      */             
/* 1374 */             Integer.valueOf(fieldLength) });
/*      */     }
/*      */     
/* 1377 */     if (value != null) {
/* 1378 */       int len = value.length();
/*      */       
/* 1380 */       if (len <= fieldLength) {
/* 1381 */         buffer.write(value.getBytes(Charset.defaultCharset()));
/* 1382 */         if (len < fieldLength) {
/* 1383 */           buffer.write(pad, 0, fieldLength - len);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1388 */         buffer.write(value.substring(0, fieldLength).getBytes(Charset.defaultCharset()));
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1393 */       buffer.write(pad, 0, fieldLength);
/*      */     } 
/*      */     
/* 1396 */     if (Trace.isOn)
/* 1397 */       Trace.exit(this, "com.ibm.mq.MQMsg2", "writeStringValueToBuffer(DataOutputStream,String,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQMsg2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */