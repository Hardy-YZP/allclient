/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDateConverter;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.HashMap;
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
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQMsg2.java";
/*      */   private static final int INITIAL_MSG_SIZE = 2048;
/*      */   
/*      */   static {
/*   98 */     if (Trace.isOn) {
/*   99 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQMsg2.java");
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
/*  113 */   private static JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*  114 */   private static JmqiSystemEnvironment jmqiSysEnv = (JmqiSystemEnvironment)jmqiEnv;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  124 */   private MQMD jmqiStructure = MQSESSION.getJmqiEnv().newMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int ccsidForStrings;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean stringsNeedCcsidConversion = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean ccsidForStringsIsAscii;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   private ByteBuffer messageData = null;
/*      */ 
/*      */   
/*  149 */   private int messageDataOffset = 0;
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
/*  164 */   private int readCursor = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMsg2() {
/*  175 */     super(MQSESSION.getJmqiEnv());
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "<init>()");
/*      */     }
/*  179 */     this.jmqiStructure.setCodedCharSetId(MQSESSION.getDefaultCCSID());
/*  180 */     this.jmqiStructure.setVersion(2);
/*  181 */     this.messageData = ByteBuffer.allocate(2048);
/*      */     
/*  183 */     if (Trace.isOn) {
/*  184 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "<init>()");
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
/*      */   public String getFormat() throws MQException {
/*  201 */     String traceRet1 = this.jmqiStructure.getFormat();
/*  202 */     if (Trace.isOn) {
/*  203 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getFormat()", "getter", traceRet1);
/*      */     }
/*      */     
/*  206 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public long getFormatAsLong() throws MQException {
/*  218 */     long retValue = 0L;
/*  219 */     String fmt = this.jmqiStructure.getFormat();
/*      */ 
/*      */     
/*  222 */     if (fmt != null) {
/*  223 */       byte[] format = fmt.getBytes(Charset.forName("UTF-8"));
/*  224 */       for (int i = 0; i < 8; i++) {
/*  225 */         retValue <<= 8L;
/*  226 */         long value = format[i];
/*  227 */         if (value < 0L) {
/*  228 */           value += 256L;
/*      */         }
/*  230 */         retValue += value;
/*      */       } 
/*      */     } else {
/*  233 */       HashMap<String, Object> data = new HashMap<>();
/*  234 */       data.put("MQMD.format", "null");
/*  235 */       Trace.ffst(this, "getFormatAsLong", "XO003001", data, MQException.class);
/*      */     } 
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getFormatAsLong()", "getter", 
/*  239 */           Long.valueOf(retValue));
/*      */     }
/*  241 */     return retValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String s) throws MQException {
/*  248 */     if (Trace.isOn) {
/*  249 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setFormat(String)", "setter", s);
/*      */     }
/*      */     
/*  252 */     this.jmqiStructure.setFormat(s);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQueueName() throws MQException {
/*  258 */     String traceRet1 = this.jmqiStructure.getReplyToQ();
/*  259 */     if (Trace.isOn) {
/*  260 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getReplyToQueueName()", "getter", traceRet1);
/*      */     }
/*      */     
/*  263 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQueueName(String s) throws MQException {
/*  270 */     if (Trace.isOn) {
/*  271 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setReplyToQueueName(String)", "setter", s);
/*      */     }
/*      */     
/*  274 */     this.jmqiStructure.setReplyToQ(s);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetReplyToQueueName() throws MQException {
/*  280 */     if (Trace.isOn) {
/*  281 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "resetReplyToQueueName()");
/*      */     }
/*      */     
/*  284 */     this.jmqiStructure.setReplyToQ(null);
/*  285 */     if (Trace.isOn) {
/*  286 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "resetReplyToQueueName()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQueueManagerName() throws MQException {
/*  295 */     String result = this.jmqiStructure.getReplyToQMgr();
/*  296 */     if (result == null) {
/*  297 */       result = "";
/*      */     }
/*  299 */     if (Trace.isOn) {
/*  300 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getReplyToQueueManagerName()", "getter", result);
/*      */     }
/*      */     
/*  303 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQueueManagerName(String s) throws MQException {
/*  310 */     if (Trace.isOn) {
/*  311 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setReplyToQueueManagerName(String)", "setter", s);
/*      */     }
/*      */     
/*  314 */     this.jmqiStructure.setReplyToQMgr(s);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetReplyToQueueManagerName() throws MQException {
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "resetReplyToQueueManagerName()");
/*      */     }
/*      */     
/*  324 */     this.jmqiStructure.setReplyToQMgr(null);
/*  325 */     if (Trace.isOn) {
/*  326 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "resetReplyToQueueManagerName()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserId() throws MQException {
/*  335 */     String traceRet1 = this.jmqiStructure.getUserIdentifier();
/*  336 */     if (Trace.isOn) {
/*  337 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getUserId()", "getter", traceRet1);
/*      */     }
/*      */     
/*  340 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public byte[] getUserIdAsBytes() {
/*  352 */     if (Trace.isOn) {
/*  353 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getUserIdAsBytes()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  358 */     String s = this.jmqiStructure.getUserIdentifier();
/*      */     
/*  360 */     byte[] traceRet1 = null;
/*  361 */     if (s != null) {
/*      */       
/*  363 */       traceRet1 = s.getBytes(Charset.forName("UTF-8"));
/*      */     } else {
/*      */       
/*  366 */       traceRet1 = new byte[12];
/*      */     } 
/*  368 */     if (Trace.isOn) {
/*  369 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getUserIdAsBytes()", traceRet1);
/*      */     }
/*      */     
/*  372 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserId(String s) throws MQException {
/*  379 */     if (Trace.isOn) {
/*  380 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setUserId(String)", "setter", s);
/*      */     }
/*      */     
/*  383 */     this.jmqiStructure.setUserIdentifier(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplicationIdData() throws MQException {
/*  390 */     String traceRet1 = this.jmqiStructure.getApplIdentityData();
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getApplicationIdData()", "getter", traceRet1);
/*      */     }
/*      */     
/*  395 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplicationIdData(String s) throws MQException {
/*  403 */     if (Trace.isOn) {
/*  404 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setApplicationIdData(String)", "setter", s);
/*      */     }
/*      */     
/*  407 */     this.jmqiStructure.setApplIdentityData(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutApplicationName() throws MQException {
/*  414 */     String traceRet1 = this.jmqiStructure.getPutApplName();
/*  415 */     if (Trace.isOn) {
/*  416 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutApplicationName()", "getter", traceRet1);
/*      */     }
/*      */     
/*  419 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public byte[] getPutApplicationNameAsBytes() {
/*  431 */     if (Trace.isOn) {
/*  432 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutApplicationNameAsBytes()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  437 */     String s = this.jmqiStructure.getPutApplName();
/*      */     
/*  439 */     byte[] traceRet1 = null;
/*  440 */     if (s != null) {
/*      */       
/*  442 */       traceRet1 = s.getBytes(Charset.forName("UTF-8"));
/*      */     } else {
/*      */       
/*  445 */       traceRet1 = new byte[28];
/*      */     } 
/*  447 */     if (Trace.isOn) {
/*  448 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutApplicationNameAsBytes()", traceRet1);
/*      */     }
/*      */     
/*  451 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplicationName(String s) throws MQException {
/*  459 */     if (Trace.isOn) {
/*  460 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setPutApplicationName(String)", "setter", s);
/*      */     }
/*      */     
/*  463 */     this.jmqiStructure.setPutApplName(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplicationOriginData() throws MQException {
/*  470 */     String traceRet1 = this.jmqiStructure.getApplOriginData();
/*  471 */     if (Trace.isOn) {
/*  472 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getApplicationOriginData()", "getter", traceRet1);
/*      */     }
/*      */     
/*  475 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplicationOriginData(String s) throws MQException {
/*  483 */     if (Trace.isOn) {
/*  484 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setApplicationOriginData(String)", "setter", s);
/*      */     }
/*      */     
/*  487 */     this.jmqiStructure.setApplOriginData(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReport() {
/*  498 */     int traceRet1 = this.jmqiStructure.getReport();
/*  499 */     if (Trace.isOn) {
/*  500 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getReport()", "getter", 
/*  501 */           Integer.valueOf(traceRet1));
/*      */     }
/*  503 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReport(int i) {
/*  510 */     if (Trace.isOn) {
/*  511 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setReport(int)", "setter", 
/*  512 */           Integer.valueOf(i));
/*      */     }
/*  514 */     this.jmqiStructure.setReport(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageType() {
/*  520 */     int traceRet1 = this.jmqiStructure.getMsgType();
/*  521 */     if (Trace.isOn) {
/*  522 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageType()", "getter", 
/*  523 */           Integer.valueOf(traceRet1));
/*      */     }
/*  525 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageType(int i) {
/*  532 */     if (Trace.isOn) {
/*  533 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageType(int)", "setter", 
/*  534 */           Integer.valueOf(i));
/*      */     }
/*  536 */     this.jmqiStructure.setMsgType(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpiry() {
/*  542 */     int traceRet1 = this.jmqiStructure.getExpiry();
/*  543 */     if (Trace.isOn) {
/*  544 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getExpiry()", "getter", 
/*  545 */           Integer.valueOf(traceRet1));
/*      */     }
/*  547 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExpiry(int i) {
/*  554 */     if (Trace.isOn) {
/*  555 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setExpiry(int)", "setter", 
/*  556 */           Integer.valueOf(i));
/*      */     }
/*  558 */     this.jmqiStructure.setExpiry(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeedback() {
/*  564 */     int traceRet1 = this.jmqiStructure.getFeedback();
/*  565 */     if (Trace.isOn) {
/*  566 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getFeedback()", "getter", 
/*  567 */           Integer.valueOf(traceRet1));
/*      */     }
/*  569 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeedback(int i) {
/*  576 */     if (Trace.isOn) {
/*  577 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setFeedback(int)", "setter", 
/*  578 */           Integer.valueOf(i));
/*      */     }
/*  580 */     this.jmqiStructure.setFeedback(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  586 */     int traceRet1 = this.jmqiStructure.getEncoding();
/*  587 */     if (Trace.isOn) {
/*  588 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getEncoding()", "getter", 
/*  589 */           Integer.valueOf(traceRet1));
/*      */     }
/*  591 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int i) {
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setEncoding(int)", "setter", 
/*  600 */           Integer.valueOf(i));
/*      */     }
/*  602 */     this.jmqiStructure.setEncoding(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCharacterSet() {
/*  608 */     int traceRet1 = this.jmqiStructure.getCodedCharSetId();
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getCharacterSet()", "getter", 
/*  611 */           Integer.valueOf(traceRet1));
/*      */     }
/*  613 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterSet(int i) {
/*  620 */     if (Trace.isOn) {
/*  621 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setCharacterSet(int)", "setter", 
/*  622 */           Integer.valueOf(i));
/*      */     }
/*  624 */     this.jmqiStructure.setCodedCharSetId(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPriority() {
/*  630 */     int traceRet1 = this.jmqiStructure.getPriority();
/*  631 */     if (Trace.isOn) {
/*  632 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPriority()", "getter", 
/*  633 */           Integer.valueOf(traceRet1));
/*      */     }
/*  635 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(int i) {
/*  642 */     if (Trace.isOn) {
/*  643 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setPriority(int)", "setter", 
/*  644 */           Integer.valueOf(i));
/*      */     }
/*  646 */     this.jmqiStructure.setPriority(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPersistence() {
/*  652 */     int traceRet1 = this.jmqiStructure.getPersistence();
/*  653 */     if (Trace.isOn) {
/*  654 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPersistence()", "getter", 
/*  655 */           Integer.valueOf(traceRet1));
/*      */     }
/*  657 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistence(int i) {
/*  664 */     if (Trace.isOn) {
/*  665 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setPersistence(int)", "setter", 
/*  666 */           Integer.valueOf(i));
/*      */     }
/*  668 */     this.jmqiStructure.setPersistence(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBackoutCount() {
/*  674 */     int traceRet1 = this.jmqiStructure.getBackoutCount();
/*  675 */     if (Trace.isOn) {
/*  676 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getBackoutCount()", "getter", 
/*  677 */           Integer.valueOf(traceRet1));
/*      */     }
/*  679 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackoutCount(int i) {
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setBackoutCount(int)", "setter", 
/*  688 */           Integer.valueOf(i));
/*      */     }
/*  690 */     this.jmqiStructure.setBackoutCount(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPutApplicationType() {
/*  696 */     int traceRet1 = this.jmqiStructure.getPutApplType();
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutApplicationType()", "getter", 
/*  699 */           Integer.valueOf(traceRet1));
/*      */     }
/*  701 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplicationType(int i) {
/*  708 */     if (Trace.isOn) {
/*  709 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setPutApplicationType(int)", "setter", 
/*  710 */           Integer.valueOf(i));
/*      */     }
/*  712 */     this.jmqiStructure.setPutApplType(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageSequenceNumber() {
/*  718 */     int traceRet1 = this.jmqiStructure.getMsgSeqNumber();
/*  719 */     if (Trace.isOn) {
/*  720 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageSequenceNumber()", "getter", 
/*  721 */           Integer.valueOf(traceRet1));
/*      */     }
/*  723 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageSequenceNumber(int i) {
/*  730 */     if (Trace.isOn) {
/*  731 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageSequenceNumber(int)", "setter", 
/*  732 */           Integer.valueOf(i));
/*      */     }
/*  734 */     this.jmqiStructure.setMsgSeqNumber(i);
/*      */ 
/*      */     
/*  737 */     if (this.jmqiStructure.getVersion() < 2) {
/*  738 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOffset() {
/*  745 */     int traceRet1 = this.jmqiStructure.getOffset();
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getOffset()", "getter", 
/*  748 */           Integer.valueOf(traceRet1));
/*      */     }
/*  750 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOffset(int i) {
/*  757 */     if (Trace.isOn) {
/*  758 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setOffset(int)", "setter", 
/*  759 */           Integer.valueOf(i));
/*      */     }
/*  761 */     this.jmqiStructure.setOffset(i);
/*      */ 
/*      */     
/*  764 */     if (this.jmqiStructure.getVersion() < 2) {
/*  765 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageFlags() {
/*  772 */     int traceRet1 = this.jmqiStructure.getMsgFlags();
/*  773 */     if (Trace.isOn) {
/*  774 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageFlags()", "getter", 
/*  775 */           Integer.valueOf(traceRet1));
/*      */     }
/*  777 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageFlags(int i) {
/*  784 */     if (Trace.isOn) {
/*  785 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageFlags(int)", "setter", 
/*  786 */           Integer.valueOf(i));
/*      */     }
/*  788 */     this.jmqiStructure.setMsgFlags(i);
/*      */ 
/*      */     
/*  791 */     if (this.jmqiStructure.getVersion() < 2) {
/*  792 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOriginalLength() {
/*  799 */     int traceRet1 = this.jmqiStructure.getOriginalLength();
/*  800 */     if (Trace.isOn) {
/*  801 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getOriginalLength()", "getter", 
/*  802 */           Integer.valueOf(traceRet1));
/*      */     }
/*  804 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOriginalLength(int i) {
/*  811 */     if (Trace.isOn) {
/*  812 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setOriginalLength(int)", "setter", 
/*  813 */           Integer.valueOf(i));
/*      */     }
/*  815 */     this.jmqiStructure.setOriginalLength(i);
/*      */ 
/*      */     
/*  818 */     if (this.jmqiStructure.getVersion() < 2) {
/*  819 */       this.jmqiStructure.setVersion(2);
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
/*      */   public byte[] getMessageId() {
/*  833 */     if (Trace.isOn) {
/*  834 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageId()");
/*      */     }
/*  836 */     byte[] returnValue = new byte[24];
/*  837 */     System.arraycopy(this.jmqiStructure.getMsgId(), 0, returnValue, 0, 24);
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageId()", returnValue);
/*      */     }
/*      */     
/*  842 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageId(byte[] value) {
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageId(byte [ ])", "setter", value);
/*      */     }
/*      */     
/*  853 */     this.jmqiStructure.setMsgId(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMessageIdRef() {
/*  860 */     byte[] traceRet1 = this.jmqiStructure.getMsgId();
/*  861 */     if (Trace.isOn) {
/*  862 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageIdRef()", "getter", traceRet1);
/*      */     }
/*      */     
/*  865 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getCorrelationId() {
/*  871 */     if (Trace.isOn) {
/*  872 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getCorrelationId()");
/*      */     }
/*      */     
/*  875 */     byte[] returnValue = new byte[24];
/*  876 */     System.arraycopy(this.jmqiStructure.getCorrelId(), 0, returnValue, 0, 24);
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getCorrelationId()", returnValue);
/*      */     }
/*      */     
/*  881 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCorrelationId(byte[] value) {
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setCorrelationId(byte [ ])", "setter", value);
/*      */     }
/*      */     
/*  892 */     this.jmqiStructure.setCorrelId(value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getCorrelationIdRef() {
/*  898 */     byte[] traceRet1 = this.jmqiStructure.getCorrelId();
/*  899 */     if (Trace.isOn) {
/*  900 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getCorrelationIdRef()", "getter", traceRet1);
/*      */     }
/*      */     
/*  903 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getAccountingToken() {
/*  909 */     if (Trace.isOn) {
/*  910 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getAccountingToken()");
/*      */     }
/*      */     
/*  913 */     byte[] returnValue = new byte[32];
/*  914 */     System.arraycopy(this.jmqiStructure.getAccountingToken(), 0, returnValue, 0, 32);
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getAccountingToken()", returnValue);
/*      */     }
/*      */     
/*  919 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAccountingToken(byte[] b) throws MQException {
/*  927 */     if (Trace.isOn) {
/*  928 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setAccountingToken(byte [ ])", "setter", b);
/*      */     }
/*      */     
/*  931 */     this.jmqiStructure.setAccountingToken(b);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getGroupId() {
/*  937 */     if (Trace.isOn) {
/*  938 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getGroupId()");
/*      */     }
/*  940 */     byte[] returnValue = new byte[24];
/*  941 */     System.arraycopy(this.jmqiStructure.getGroupId(), 0, returnValue, 0, 24);
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getGroupId()", returnValue);
/*      */     }
/*      */     
/*  946 */     return returnValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGroupId(byte[] value) {
/*  953 */     if (Trace.isOn) {
/*  954 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setGroupId(byte [ ])", "setter", value);
/*      */     }
/*      */     
/*  957 */     this.jmqiStructure.setGroupId(value);
/*      */ 
/*      */     
/*  960 */     if (this.jmqiStructure.getVersion() < 2) {
/*  961 */       this.jmqiStructure.setVersion(2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public byte[] getPutDateAsBytes() {
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutDateAsBytes()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  978 */     String s = this.jmqiStructure.getPutDate();
/*      */     
/*  980 */     byte[] traceRet1 = null;
/*  981 */     if (s != null) {
/*      */       
/*  983 */       traceRet1 = s.getBytes(Charset.forName("UTF-8"));
/*      */     } else {
/*      */       
/*  986 */       traceRet1 = new byte[8];
/*      */     } 
/*  988 */     if (Trace.isOn) {
/*  989 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutDateAsBytes()", traceRet1);
/*      */     }
/*      */     
/*  992 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutDate() {
/* 1000 */     String traceRet1 = this.jmqiStructure.getPutDate();
/* 1001 */     if (Trace.isOn) {
/* 1002 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutDate()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1005 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutDate(String s) {
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setPutDate(String)", "setter", s);
/*      */     }
/*      */     
/* 1018 */     this.jmqiStructure.setPutDate(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public byte[] getPutTimeAsBytes() {
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutTimeAsBytes()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1034 */     String s = this.jmqiStructure.getPutTime();
/*      */     
/* 1036 */     byte[] traceRet1 = null;
/* 1037 */     if (s != null) {
/*      */       
/* 1039 */       traceRet1 = s.getBytes(Charset.forName("UTF-8"));
/*      */     } else {
/*      */       
/* 1042 */       traceRet1 = new byte[8];
/*      */     } 
/* 1044 */     if (Trace.isOn) {
/* 1045 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutTimeAsBytes()", traceRet1);
/*      */     }
/*      */     
/* 1048 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutTime() {
/* 1056 */     String traceRet1 = this.jmqiStructure.getPutTime();
/* 1057 */     if (Trace.isOn) {
/* 1058 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutTime()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1061 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutTime(String s) {
/* 1070 */     if (Trace.isOn) {
/* 1071 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setPutTime(String)", "setter", s);
/*      */     }
/*      */     
/* 1074 */     this.jmqiStructure.setPutTime(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/* 1082 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 1083 */     if (Trace.isOn) {
/* 1084 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getVersion()", "getter", 
/* 1085 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1087 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPutTimeMillis() throws MQException {
/* 1096 */     String putTime = this.jmqiStructure.getPutTime();
/* 1097 */     String putDate = this.jmqiStructure.getPutDate();
/* 1098 */     long putTimeMillis = 0L;
/* 1099 */     if (putTime != null && !"".equals(putTime.trim()) && putDate != null && 
/* 1100 */       !"".equals(putDate.trim())) {
/* 1101 */       putTimeMillis = WMQDateConverter.mqDateTimeToMillis(putTime
/* 1102 */           .trim().getBytes(Charset.forName("UTF-8")), putDate
/* 1103 */           .trim().getBytes(Charset.forName("UTF-8")));
/*      */     }
/*      */     
/* 1106 */     if (Trace.isOn) {
/* 1107 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getPutTimeMillis()", "getter", 
/* 1108 */           Long.valueOf(putTimeMillis));
/*      */     }
/* 1110 */     return putTimeMillis;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public int _getCcsid() {
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "_getCcsid()");
/*      */     }
/* 1123 */     if (Trace.isOn) {
/* 1124 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "_getCcsid()", 
/* 1125 */           Integer.valueOf(this.ccsidForStrings));
/*      */     }
/* 1127 */     return this.ccsidForStrings;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean _getIsCcsidAscii() {
/* 1137 */     if (Trace.isOn) {
/* 1138 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "_getIsCcsidAscii()");
/*      */     }
/*      */     
/* 1141 */     if (Trace.isOn) {
/* 1142 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "_getIsCcsidAscii()", 
/* 1143 */           Boolean.valueOf(this.ccsidForStringsIsAscii));
/*      */     }
/* 1145 */     return this.ccsidForStringsIsAscii;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean _doStringsNeedCcsidConversion() {
/* 1155 */     if (Trace.isOn) {
/* 1156 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "_doStringsNeedCcsidConversion()");
/*      */     }
/*      */     
/* 1159 */     if (Trace.isOn) {
/* 1160 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "_doStringsNeedCcsidConversion()", 
/* 1161 */           Boolean.valueOf(this.stringsNeedCcsidConversion));
/*      */     }
/* 1163 */     return this.stringsNeedCcsidConversion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterSetToDefault() {
/* 1170 */     if (Trace.isOn) {
/* 1171 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setCharacterSetToDefault()");
/*      */     }
/*      */     
/* 1174 */     this.jmqiStructure.setCodedCharSetId(MQSESSION.getDefaultCCSID());
/* 1175 */     if (Trace.isOn) {
/* 1176 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setCharacterSetToDefault()");
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
/*      */   private boolean isVersion1Safe() {
/* 1194 */     boolean invalidGroupId = false;
/* 1195 */     byte[] groupId = this.jmqiStructure.getGroupId();
/* 1196 */     if (groupId != null) {
/* 1197 */       for (int i = 0; i < groupId.length && !invalidGroupId; i++) {
/* 1198 */         if (groupId[i] != 0) {
/* 1199 */           invalidGroupId = true;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1206 */     boolean traceRet1 = (!invalidGroupId && this.jmqiStructure.getMsgSeqNumber() == 1 && this.jmqiStructure.getOffset() == 0 && this.jmqiStructure.getMsgFlags() == 0 && this.jmqiStructure.getOriginalLength() == -1);
/* 1207 */     if (Trace.isOn) {
/* 1208 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "isVersion1Safe()", "getter", 
/* 1209 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1211 */     return traceRet1;
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
/*      */   public void setVersion(int newVersion) throws MQException {
/* 1223 */     if (Trace.isOn) {
/* 1224 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setVersion(int)", "setter", 
/* 1225 */           Integer.valueOf(newVersion));
/*      */     }
/* 1227 */     if (newVersion > 2 || newVersion < 1) {
/* 1228 */       MQException traceRet1 = new MQException(2, 2026, this, "MQJE064", "" + newVersion);
/* 1229 */       if (Trace.isOn) {
/* 1230 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setVersion(int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1233 */       throw traceRet1;
/*      */     } 
/* 1235 */     if (newVersion == 1 && !isVersion1Safe()) {
/* 1236 */       MQException traceRet2 = new MQException(2, 2026, this, "MQJE055");
/* 1237 */       if (Trace.isOn) {
/* 1238 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setVersion(int)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1241 */       throw traceRet2;
/*      */     } 
/* 1243 */     this.jmqiStructure.setVersion(newVersion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMessageData() {
/* 1253 */     if (Trace.isOn) {
/* 1254 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageData()");
/*      */     }
/* 1256 */     byte[] returnData = null;
/* 1257 */     returnData = new byte[this.messageDataOffset];
/* 1258 */     this.messageData.position(0);
/* 1259 */     this.messageData.get(returnData, 0, this.messageDataOffset);
/* 1260 */     if (Trace.isOn) {
/* 1261 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageData()", returnData);
/*      */     }
/*      */     
/* 1264 */     return returnData;
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
/* 1275 */     byte[] traceRet1 = this.messageData.array();
/* 1276 */     if (Trace.isOn) {
/* 1277 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageDataRef()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1280 */     return traceRet1;
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
/*      */   public void setMessageData(ByteBuffer data) throws MQException {
/* 1298 */     if (Trace.isOn) {
/* 1299 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageData(ByteBuffer)", "setter", data);
/*      */     }
/*      */     
/* 1302 */     setMessageData(data, data.limit());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageData(ByteBuffer data, int messageLength) {
/* 1312 */     if (Trace.isOn) {
/* 1313 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageData(ByteBuffer,int)", new Object[] { data, 
/* 1314 */             Integer.valueOf(messageLength) });
/*      */     }
/* 1316 */     if (messageLength == data.limit()) {
/* 1317 */       this.messageData = data;
/*      */     } else {
/* 1319 */       this.messageData = ByteBuffer.allocate(messageLength);
/* 1320 */       this.messageData.put(data.array(), 0, messageLength);
/*      */     } 
/* 1322 */     this.messageDataOffset = messageLength;
/* 1323 */     this.readCursor = 0;
/* 1324 */     if (Trace.isOn) {
/* 1325 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setMessageData(ByteBuffer,int)");
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
/*      */   protected void setInternalBuffer(ByteBuffer data, int len) {
/* 1343 */     if (Trace.isOn) {
/* 1344 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setInternalBuffer(ByteBuffer,int)", new Object[] { data, 
/* 1345 */             Integer.valueOf(len) });
/*      */     }
/* 1347 */     this.messageData = data;
/* 1348 */     this.messageDataOffset = len;
/* 1349 */     this.readCursor = 0;
/* 1350 */     if (Trace.isOn) {
/* 1351 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "setInternalBuffer(ByteBuffer,int)");
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
/*      */   protected ByteBuffer getInternalBuffer() {
/* 1366 */     if (Trace.isOn) {
/* 1367 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getInternalBuffer()", "getter", this.messageData);
/*      */     }
/*      */     
/* 1370 */     return this.messageData;
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
/*      */   private void expandMessageData(int newMinSize) {
/* 1382 */     if (Trace.isOn)
/* 1383 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "expandMessageData(int)", new Object[] {
/* 1384 */             Integer.valueOf(newMinSize)
/*      */           }); 
/* 1386 */     ByteBuffer tmp = ByteBuffer.allocate(newMinSize);
/* 1387 */     this.messageData.position(0);
/* 1388 */     this.messageData.limit(this.messageDataOffset);
/* 1389 */     tmp.put(this.messageData);
/* 1390 */     this.messageData = tmp;
/* 1391 */     if (Trace.isOn) {
/* 1392 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "expandMessageData(int)");
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
/*      */   public final void appendInt(int value, int encoding) throws IOException, MQException {
/* 1408 */     if (Trace.isOn)
/* 1409 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendInt(int,int)", new Object[] {
/* 1410 */             Integer.valueOf(value), Integer.valueOf(encoding)
/*      */           }); 
/* 1412 */     if (this.messageData.capacity() < this.messageDataOffset + 4)
/* 1413 */       expandMessageData(this.messageDataOffset + 4); 
/*      */     try {
/*      */       IOException traceRet1;
/* 1416 */       switch (encoding & 0xF) {
/*      */         case 0:
/*      */         case 1:
/* 1419 */           this.messageData.order(ByteOrder.BIG_ENDIAN);
/* 1420 */           this.messageData.putInt(this.messageDataOffset, value);
/* 1421 */           this.messageDataOffset += 4;
/*      */           break;
/*      */         case 2:
/* 1424 */           this.messageData.order(ByteOrder.LITTLE_ENDIAN);
/* 1425 */           this.messageData.putInt(this.messageDataOffset, value);
/* 1426 */           this.messageDataOffset += 4;
/*      */           break;
/*      */         
/*      */         default:
/* 1430 */           traceRet1 = new IOException("unknown encoding: " + (encoding & 0xF));
/* 1431 */           if (Trace.isOn) {
/* 1432 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendInt(int,int)", traceRet1, 1);
/*      */           }
/*      */           
/* 1435 */           throw traceRet1;
/*      */       } 
/*      */ 
/*      */     
/* 1439 */     } catch (ArrayIndexOutOfBoundsException aioobe) {
/* 1440 */       if (Trace.isOn) {
/* 1441 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendInt(int,int)", aioobe);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1447 */       IOException traceRet2 = new IOException(aioobe.getMessage());
/* 1448 */       if (Trace.isOn) {
/* 1449 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendInt(int,int)", traceRet2, 2);
/*      */       }
/*      */       
/* 1452 */       throw traceRet2;
/*      */     } 
/* 1454 */     if (Trace.isOn) {
/* 1455 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendInt(int,int)");
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
/*      */   public final void appendByteArray(byte[] data) throws IOException, MQException {
/* 1469 */     if (Trace.isOn) {
/* 1470 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendByteArray(byte [ ])", new Object[] { data });
/*      */     }
/*      */     
/* 1473 */     if (this.messageData.capacity() < this.messageDataOffset + data.length) {
/* 1474 */       expandMessageData(this.messageDataOffset + data.length);
/*      */     }
/* 1476 */     this.messageData.position(this.messageDataOffset);
/* 1477 */     this.messageData.put(data);
/* 1478 */     this.messageDataOffset += data.length;
/* 1479 */     if (Trace.isOn) {
/* 1480 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendByteArray(byte [ ])");
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
/*      */   public void appendByteArray(byte[] data, int offset, int length) throws IOException, MQException {
/* 1496 */     if (Trace.isOn) {
/* 1497 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendByteArray(byte [ ],int,int)", new Object[] { data, 
/* 1498 */             Integer.valueOf(offset), 
/* 1499 */             Integer.valueOf(length) });
/*      */     }
/* 1501 */     if (this.messageData.capacity() < this.messageDataOffset + length) {
/* 1502 */       expandMessageData(this.messageDataOffset + length);
/*      */     }
/* 1504 */     this.messageData.position(this.messageDataOffset);
/* 1505 */     this.messageData.put(data, offset, length);
/* 1506 */     this.messageDataOffset += length;
/* 1507 */     if (Trace.isOn) {
/* 1508 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "appendByteArray(byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageDataLength() {
/* 1519 */     if (Trace.isOn) {
/* 1520 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getMessageDataLength()", "getter", 
/* 1521 */           Integer.valueOf(this.messageDataOffset));
/*      */     }
/* 1523 */     return this.messageDataOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearMessageData() {
/* 1530 */     if (Trace.isOn) {
/* 1531 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "clearMessageData()");
/*      */     }
/*      */     
/* 1534 */     this.messageDataOffset = 0;
/* 1535 */     this.readCursor = 0;
/*      */     
/* 1537 */     this.messageData.clear();
/*      */ 
/*      */     
/* 1540 */     if (Trace.isOn) {
/* 1541 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "clearMessageData()");
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
/*      */   public int readInt(int encoding) throws IOException, EOFException, MQException {
/* 1559 */     if (Trace.isOn)
/* 1560 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "readInt(int)", new Object[] {
/* 1561 */             Integer.valueOf(encoding)
/*      */           }); 
/* 1563 */     if (this.messageData.capacity() - this.readCursor < 4) {
/*      */       
/* 1565 */       EOFException traceRet1 = new EOFException("end of message reached");
/* 1566 */       if (Trace.isOn) {
/* 1567 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "readInt(int)", traceRet1, 1);
/*      */       }
/*      */       
/* 1570 */       throw traceRet1;
/*      */     } 
/*      */     
/*      */     try {
/*      */       IOException traceRet2;
/* 1575 */       switch (encoding & 0xF) {
/*      */         case 0:
/*      */         case 1:
/* 1578 */           this.messageData.order(ByteOrder.BIG_ENDIAN);
/*      */           break;
/*      */         case 2:
/* 1581 */           this.messageData.order(ByteOrder.LITTLE_ENDIAN);
/*      */           break;
/*      */         
/*      */         default:
/* 1585 */           traceRet2 = new IOException("unknown encoding: " + (encoding & 0xF));
/* 1586 */           if (Trace.isOn) {
/* 1587 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "readInt(int)", traceRet2, 2);
/*      */           }
/*      */           
/* 1590 */           throw traceRet2;
/*      */       } 
/*      */ 
/*      */     
/* 1594 */     } catch (ArrayIndexOutOfBoundsException aioobe) {
/* 1595 */       if (Trace.isOn) {
/* 1596 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "readInt(int)", aioobe);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1601 */       IOException traceRet3 = new IOException(aioobe.getMessage());
/* 1602 */       if (Trace.isOn) {
/* 1603 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "readInt(int)", traceRet3, 3);
/*      */       }
/*      */       
/* 1606 */       throw traceRet3;
/*      */     } 
/* 1608 */     int retVal = this.messageData.getInt(this.readCursor);
/* 1609 */     this.readCursor += 4;
/* 1610 */     if (Trace.isOn) {
/* 1611 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "readInt(int)", 
/* 1612 */           Integer.valueOf(retVal));
/*      */     }
/* 1614 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void skipReadingBytes(int bytesToSkip) throws MQException {
/* 1624 */     if (Trace.isOn)
/* 1625 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "skipReadingBytes(int)", new Object[] {
/* 1626 */             Integer.valueOf(bytesToSkip)
/*      */           }); 
/* 1628 */     this.readCursor += bytesToSkip;
/* 1629 */     if (Trace.isOn) {
/* 1630 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "skipReadingBytes(int)");
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
/*      */   public int read(byte[] data) throws MQException {
/* 1645 */     if (Trace.isOn) {
/* 1646 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "read(byte [ ])", new Object[] { data });
/*      */     }
/*      */     
/* 1649 */     int readLen = data.length;
/* 1650 */     if (this.messageDataOffset - this.readCursor < readLen) {
/* 1651 */       readLen = this.messageDataOffset - this.readCursor;
/*      */     }
/* 1653 */     if (readLen > 0) {
/* 1654 */       this.messageData.position(this.readCursor);
/* 1655 */       this.messageData.get(data, 0, readLen);
/*      */     } 
/* 1657 */     this.readCursor += readLen;
/* 1658 */     int traceRet1 = (readLen > 0) ? readLen : -1;
/* 1659 */     if (Trace.isOn) {
/* 1660 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "read(byte [ ])", 
/* 1661 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1663 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetReadPosition() throws MQException {
/* 1672 */     if (Trace.isOn) {
/* 1673 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "resetReadPosition()");
/*      */     }
/*      */     
/* 1676 */     this.readCursor = 0;
/* 1677 */     if (Trace.isOn) {
/* 1678 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "resetReadPosition()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQMD getJMQIStructure() {
/* 1689 */     if (Trace.isOn) {
/* 1690 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "getJMQIStructure()", "getter", this.jmqiStructure);
/*      */     }
/*      */     
/* 1693 */     return this.jmqiStructure;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int sizeOfMQMD() {
/* 1699 */     if (Trace.isOn) {
/* 1700 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "sizeOfMQMD()");
/*      */     }
/* 1702 */     int version = this.jmqiStructure.getVersion();
/* 1703 */     int pointerSize = 4;
/* 1704 */     int size = 0;
/*      */     
/*      */     try {
/* 1707 */       size = MQMD.getSize(this.env, version, pointerSize);
/*      */     }
/* 1709 */     catch (JmqiException e) {
/* 1710 */       if (Trace.isOn) {
/* 1711 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "sizeOfMQMD()", (Throwable)e);
/*      */       }
/*      */       
/* 1714 */       HashMap<String, Object> data = new HashMap<>();
/* 1715 */       data.put("version", Integer.toString(version));
/* 1716 */       data.put("pointerSize", Integer.toString(pointerSize));
/* 1717 */       data.put("exception", e);
/* 1718 */       Trace.ffst(this, "sizeOfMQMD", "probe1", data, null);
/*      */     } 
/*      */     
/* 1721 */     if (Trace.isOn) {
/* 1722 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "sizeOfMQMD()", 
/* 1723 */           Integer.valueOf(size));
/*      */     }
/* 1725 */     return size;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DataOutputStream writeTo(DataOutputStream dataBuffer, int ccsid, boolean ccsidIsAscii) throws IOException, MQException {
/* 1751 */     if (Trace.isOn) {
/* 1752 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "writeTo(DataOutputStream,int,boolean)", new Object[] { dataBuffer, 
/* 1753 */             Integer.valueOf(ccsid), 
/* 1754 */             Boolean.valueOf(ccsidIsAscii) });
/*      */     }
/*      */     
/*      */     try {
/* 1758 */       int pointerSize = 4;
/* 1759 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/* 1760 */       int requiredSize = this.jmqiStructure.getRequiredBufferSize(pointerSize, cp);
/*      */ 
/*      */       
/* 1763 */       byte[] data = new byte[requiredSize];
/* 1764 */       boolean swap = false;
/*      */ 
/*      */       
/* 1767 */       JmqiComponentTls tls = jmqiSysEnv.getComponentTls(MQSESSION.jmsv6CompId);
/* 1768 */       JmqiTls jTls = jmqiSysEnv.getJmqiTls(tls);
/*      */ 
/*      */       
/* 1771 */       this.jmqiStructure.writeToBuffer(data, 0, pointerSize, swap, cp, jTls);
/* 1772 */       dataBuffer.write(data);
/*      */     }
/* 1774 */     catch (JmqiException e) {
/* 1775 */       if (Trace.isOn) {
/* 1776 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "writeTo(DataOutputStream,int,boolean)", (Throwable)e);
/*      */       }
/*      */       
/* 1779 */       HashMap<String, Object> data = new HashMap<>();
/* 1780 */       data.put("ccsid", Integer.toString(ccsid));
/* 1781 */       data.put("exception", e);
/* 1782 */       Trace.ffst(this, "writeTo", "probe1", data, MQException.class);
/*      */     } 
/* 1784 */     if (Trace.isOn) {
/* 1785 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMsg2", "writeTo(DataOutputStream,int,boolean)", dataBuffer);
/*      */     }
/*      */     
/* 1788 */     return dataBuffer;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQMsg2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */