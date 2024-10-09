/*      */ package com.ibm.mq.headers;
/*      */ 
/*      */ import com.ibm.mq.MQMD;
/*      */ import com.ibm.mq.headers.internal.Header;
/*      */ import com.ibm.mq.headers.internal.HeaderField;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.IOException;
/*      */ import java.text.DateFormat;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.TimeZone;
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
/*      */ public class MQMD1
/*      */   extends Header
/*      */   implements MQChainable
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMD1.java";
/*      */   public static final int SIZE = 324;
/*      */   
/*      */   static {
/*   83 */     if (Trace.isOn) {
/*   84 */       Trace.data("com.ibm.mq.headers.MQMD1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMD1.java");
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
/*   96 */   static final HeaderType TYPE = new HeaderType("MQMD1");
/*      */   
/*   98 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "MD  ");
/*   99 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  100 */   static final HeaderField Report = TYPE.addMQLong("Report", 0);
/*  101 */   static final HeaderField MsgType = TYPE.addMQLong("MsgType", 8);
/*  102 */   static final HeaderField Expiry = TYPE.addMQLong("Expiry", -1);
/*  103 */   static final HeaderField Feedback = TYPE.addMQLong("Feedback", 0);
/*  104 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  105 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId", 0);
/*  106 */   static final HeaderField Format = TYPE.addMQChar("Format", "        ");
/*  107 */   static final HeaderField Priority = TYPE.addMQLong("Priority", -1);
/*  108 */   static final HeaderField Persistence = TYPE.addMQLong("Persistence", 2);
/*      */   
/*  110 */   static final HeaderField MsgId = TYPE.addMQByte("MsgId", 24);
/*  111 */   static final HeaderField CorrelId = TYPE.addMQByte("CorrelId", 24);
/*  112 */   static final HeaderField BackoutCount = TYPE.addMQLong("BackoutCount", 0);
/*  113 */   static final HeaderField ReplyToQ = TYPE.addMQChar("ReplyToQ", 48);
/*  114 */   static final HeaderField ReplyToQMgr = TYPE.addMQChar("ReplyToQMgr", 48);
/*  115 */   static final HeaderField UserIdentifier = TYPE.addMQChar("UserIdentifier", 12);
/*  116 */   static final HeaderField AccountingToken = TYPE.addMQByte("AccountingToken", 32);
/*  117 */   static final HeaderField ApplIdentityData = TYPE.addMQChar("ApplIdentityData", 32);
/*  118 */   static final HeaderField PutApplType = TYPE.addMQLong("PutApplType", 0);
/*  119 */   static final HeaderField PutApplName = TYPE.addMQChar("PutApplName", 28);
/*  120 */   static final HeaderField PutDate = TYPE.addMQChar("PutDate", 8);
/*  121 */   static final HeaderField PutTime = TYPE.addMQChar("PutTime", 8);
/*  122 */   static final HeaderField ApplOriginData = TYPE.addMQChar("ApplOriginData", 4);
/*      */   
/*  124 */   static final DateFormat timeFormat = new SimpleDateFormat("HHmmssSSS");
/*  125 */   static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
/*  126 */   static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
/*      */   static final int timeFieldLength = 8;
/*      */   
/*      */   static {
/*  130 */     if (Trace.isOn) {
/*  131 */       Trace.entry("com.ibm.mq.headers.MQMD1", "static()");
/*      */     }
/*  133 */     TimeZone gmt = TimeZone.getTimeZone("GMT+0:00");
/*      */     
/*  135 */     timeFormat.setTimeZone(gmt);
/*  136 */     dateFormat.setTimeZone(gmt);
/*  137 */     if (Trace.isOn) {
/*  138 */       Trace.exit("com.ibm.mq.headers.MQMD1", "static()");
/*      */     }
/*      */   }
/*      */   
/*      */   protected MQMD1(HeaderType headerType) {
/*  143 */     super(headerType);
/*  144 */     if (Trace.isOn) {
/*  145 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "<init>(HeaderType)", new Object[] { headerType });
/*      */     }
/*      */     
/*  148 */     if (Trace.isOn) {
/*  149 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "<init>(HeaderType)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMD1() {
/*  158 */     super(TYPE);
/*  159 */     if (Trace.isOn) {
/*  160 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "<init>()");
/*      */     }
/*  162 */     if (Trace.isOn) {
/*  163 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "<init>()");
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
/*      */   public MQMD1(DataInput message) throws MQDataException, IOException {
/*  179 */     this();
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*  183 */     read((DataInput)MessageWrapper.wrap(message));
/*      */     
/*  185 */     if (Trace.isOn) {
/*  186 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput)");
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
/*      */   public MQMD1(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*  201 */     this();
/*  202 */     if (Trace.isOn) {
/*  203 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", new Object[] { message, 
/*  204 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     try {
/*  207 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*      */     }
/*  209 */     catch (MQDataException mde) {
/*  210 */       if (Trace.isOn) {
/*  211 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", mde, 1);
/*      */       }
/*      */       
/*  214 */       if (Trace.isOn) {
/*  215 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", mde, 1);
/*      */       }
/*  217 */       throw mde;
/*      */     }
/*  219 */     catch (IOException ioe) {
/*  220 */       if (Trace.isOn) {
/*  221 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", ioe, 2);
/*      */       }
/*  223 */       if (Trace.isOn) {
/*  224 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", ioe, 2);
/*      */       }
/*  226 */       throw ioe;
/*      */     }
/*  228 */     catch (Exception e) {
/*  229 */       if (Trace.isOn) {
/*  230 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", e, 3);
/*      */       }
/*  232 */       RuntimeException traceRet1 = new RuntimeException(e);
/*  233 */       if (Trace.isOn) {
/*  234 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)", traceRet1, 3);
/*      */       }
/*      */       
/*  237 */       throw traceRet1;
/*      */     } 
/*  239 */     if (Trace.isOn) {
/*  240 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "<init>(DataInput,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStrucId() {
/*  251 */     String traceRet1 = getStringValue(StrucId);
/*  252 */     if (Trace.isOn) {
/*  253 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getStrucId()", "getter", traceRet1);
/*      */     }
/*  255 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  264 */     int traceRet1 = getIntValue(Version);
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getVersion()", "getter", 
/*  267 */           Integer.valueOf(traceRet1));
/*      */     }
/*  269 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReport() {
/*  278 */     int traceRet1 = getIntValue(Report);
/*  279 */     if (Trace.isOn) {
/*  280 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getReport()", "getter", 
/*  281 */           Integer.valueOf(traceRet1));
/*      */     }
/*  283 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReport(int value) {
/*  292 */     if (Trace.isOn) {
/*  293 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setReport(int)", "setter", 
/*  294 */           Integer.valueOf(value));
/*      */     }
/*  296 */     setIntValue(Report, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgType() {
/*  305 */     int traceRet1 = getIntValue(MsgType);
/*  306 */     if (Trace.isOn) {
/*  307 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getMsgType()", "getter", 
/*  308 */           Integer.valueOf(traceRet1));
/*      */     }
/*  310 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgType(int value) {
/*  319 */     if (Trace.isOn) {
/*  320 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setMsgType(int)", "setter", 
/*  321 */           Integer.valueOf(value));
/*      */     }
/*  323 */     setIntValue(MsgType, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpiry() {
/*  332 */     int traceRet1 = getIntValue(Expiry);
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getExpiry()", "getter", 
/*  335 */           Integer.valueOf(traceRet1));
/*      */     }
/*  337 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExpiry(int value) {
/*  346 */     if (Trace.isOn) {
/*  347 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setExpiry(int)", "setter", 
/*  348 */           Integer.valueOf(value));
/*      */     }
/*  350 */     setIntValue(Expiry, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeedback() {
/*  359 */     int traceRet1 = getIntValue(Feedback);
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getFeedback()", "getter", 
/*  362 */           Integer.valueOf(traceRet1));
/*      */     }
/*  364 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeedback(int value) {
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setFeedback(int)", "setter", 
/*  375 */           Integer.valueOf(value));
/*      */     }
/*  377 */     setIntValue(Feedback, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  386 */     int traceRet1 = getIntValue(Encoding);
/*  387 */     if (Trace.isOn) {
/*  388 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getEncoding()", "getter", 
/*  389 */           Integer.valueOf(traceRet1));
/*      */     }
/*  391 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int value) {
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setEncoding(int)", "setter", 
/*  402 */           Integer.valueOf(value));
/*      */     }
/*  404 */     setIntValue(Encoding, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCodedCharSetId() {
/*  413 */     int traceRet1 = getIntValue(CodedCharSetId);
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getCodedCharSetId()", "getter", 
/*  416 */           Integer.valueOf(traceRet1));
/*      */     }
/*  418 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCodedCharSetId(int value) {
/*  427 */     if (Trace.isOn) {
/*  428 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setCodedCharSetId(int)", "setter", 
/*  429 */           Integer.valueOf(value));
/*      */     }
/*  431 */     setIntValue(CodedCharSetId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/*  440 */     String traceRet1 = getStringValue(Format);
/*  441 */     if (Trace.isOn) {
/*  442 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getFormat()", "getter", traceRet1);
/*      */     }
/*  444 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String value) {
/*  453 */     if (Trace.isOn) {
/*  454 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setFormat(String)", "setter", value);
/*      */     }
/*  456 */     setStringValue(Format, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPriority() {
/*  465 */     int traceRet1 = getIntValue(Priority);
/*  466 */     if (Trace.isOn) {
/*  467 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getPriority()", "getter", 
/*  468 */           Integer.valueOf(traceRet1));
/*      */     }
/*  470 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(int value) {
/*  479 */     if (Trace.isOn) {
/*  480 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPriority(int)", "setter", 
/*  481 */           Integer.valueOf(value));
/*      */     }
/*  483 */     setIntValue(Priority, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPersistence() {
/*  492 */     int traceRet1 = getIntValue(Persistence);
/*  493 */     if (Trace.isOn) {
/*  494 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getPersistence()", "getter", 
/*  495 */           Integer.valueOf(traceRet1));
/*      */     }
/*  497 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistence(int value) {
/*  506 */     if (Trace.isOn) {
/*  507 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPersistence(int)", "setter", 
/*  508 */           Integer.valueOf(value));
/*      */     }
/*  510 */     setIntValue(Persistence, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgId() {
/*  519 */     byte[] traceRet1 = getBytesValue(MsgId);
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getMsgId()", "getter", traceRet1);
/*      */     }
/*  523 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgId(byte[] value) {
/*  532 */     if (Trace.isOn) {
/*  533 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setMsgId(byte [ ])", "setter", value);
/*      */     }
/*  535 */     setBytesValue(MsgId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getCorrelId() {
/*  544 */     byte[] traceRet1 = getBytesValue(CorrelId);
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getCorrelId()", "getter", traceRet1);
/*      */     }
/*  548 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCorrelId(byte[] value) {
/*  557 */     if (Trace.isOn) {
/*  558 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setCorrelId(byte [ ])", "setter", value);
/*      */     }
/*  560 */     setBytesValue(CorrelId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBackoutCount() {
/*  569 */     int traceRet1 = getIntValue(BackoutCount);
/*  570 */     if (Trace.isOn) {
/*  571 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getBackoutCount()", "getter", 
/*  572 */           Integer.valueOf(traceRet1));
/*      */     }
/*  574 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackoutCount(int value) {
/*  583 */     if (Trace.isOn) {
/*  584 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setBackoutCount(int)", "setter", 
/*  585 */           Integer.valueOf(value));
/*      */     }
/*  587 */     setIntValue(BackoutCount, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQ() {
/*  596 */     String traceRet1 = getStringValue(ReplyToQ);
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getReplyToQ()", "getter", traceRet1);
/*      */     }
/*  600 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQ(String value) {
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setReplyToQ(String)", "setter", value);
/*      */     }
/*  612 */     setStringValue(ReplyToQ, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQMgr() {
/*  621 */     String traceRet1 = getStringValue(ReplyToQMgr);
/*  622 */     if (Trace.isOn) {
/*  623 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getReplyToQMgr()", "getter", traceRet1);
/*      */     }
/*  625 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQMgr(String value) {
/*  634 */     if (Trace.isOn) {
/*  635 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setReplyToQMgr(String)", "setter", value);
/*      */     }
/*  637 */     setStringValue(ReplyToQMgr, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserIdentifier() {
/*  646 */     String traceRet1 = getStringValue(UserIdentifier);
/*  647 */     if (Trace.isOn) {
/*  648 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getUserIdentifier()", "getter", traceRet1);
/*      */     }
/*  650 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserIdentifier(String value) {
/*  659 */     if (Trace.isOn) {
/*  660 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setUserIdentifier(String)", "setter", value);
/*      */     }
/*  662 */     setStringValue(UserIdentifier, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getAccountingToken() {
/*  671 */     byte[] traceRet1 = getBytesValue(AccountingToken);
/*  672 */     if (Trace.isOn) {
/*  673 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getAccountingToken()", "getter", traceRet1);
/*      */     }
/*  675 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAccountingToken(byte[] value) {
/*  684 */     if (Trace.isOn) {
/*  685 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setAccountingToken(byte [ ])", "setter", value);
/*      */     }
/*      */     
/*  688 */     setBytesValue(AccountingToken, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplIdentityData() {
/*  697 */     String traceRet1 = getStringValue(ApplIdentityData);
/*  698 */     if (Trace.isOn) {
/*  699 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getApplIdentityData()", "getter", traceRet1);
/*      */     }
/*  701 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplIdentityData(String value) {
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setApplIdentityData(String)", "setter", value);
/*      */     }
/*      */     
/*  714 */     setStringValue(ApplIdentityData, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPutApplType() {
/*  723 */     int traceRet1 = getIntValue(PutApplType);
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getPutApplType()", "getter", 
/*  726 */           Integer.valueOf(traceRet1));
/*      */     }
/*  728 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplType(int value) {
/*  737 */     if (Trace.isOn) {
/*  738 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPutApplType(int)", "setter", 
/*  739 */           Integer.valueOf(value));
/*      */     }
/*  741 */     setIntValue(PutApplType, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutApplName() {
/*  750 */     String traceRet1 = getStringValue(PutApplName);
/*  751 */     if (Trace.isOn) {
/*  752 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getPutApplName()", "getter", traceRet1);
/*      */     }
/*  754 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplName(String value) {
/*  763 */     if (Trace.isOn) {
/*  764 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPutApplName(String)", "setter", value);
/*      */     }
/*  766 */     setStringValue(PutApplName, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutDate() {
/*  775 */     String traceRet1 = getStringValue(PutDate);
/*  776 */     if (Trace.isOn) {
/*  777 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getPutDate()", "getter", traceRet1);
/*      */     }
/*  779 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutDate(String value) {
/*  788 */     if (Trace.isOn) {
/*  789 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPutDate(String)", "setter", value);
/*      */     }
/*  791 */     setStringValue(PutDate, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutTime() {
/*  800 */     String traceRet1 = getStringValue(PutTime);
/*  801 */     if (Trace.isOn) {
/*  802 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getPutTime()", "getter", traceRet1);
/*      */     }
/*  804 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutTime(String value) {
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPutTime(String)", "setter", value);
/*      */     }
/*  816 */     setStringValue(PutTime, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPutDateTime() {
/*  826 */     if (Trace.isOn) {
/*  827 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "getPutDateTime()");
/*      */     }
/*      */     try {
/*  830 */       long traceRet1 = -1L;
/*  831 */       synchronized (MQMD1.class) {
/*  832 */         traceRet1 = dateTimeFormat.parse(getPutDate() + getPutTime() + "0").getTime();
/*      */       } 
/*      */       
/*  835 */       if (Trace.isOn) {
/*  836 */         Trace.exit(this, "com.ibm.mq.headers.MQMD1", "getPutDateTime()", Long.valueOf(traceRet1), 1);
/*      */       }
/*      */       
/*  839 */       return traceRet1;
/*      */     
/*      */     }
/*  842 */     catch (ParseException e) {
/*  843 */       if (Trace.isOn) {
/*  844 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD1", "getPutDateTime()", e);
/*      */       }
/*  846 */       long traceRet2 = System.currentTimeMillis();
/*  847 */       if (Trace.isOn) {
/*  848 */         Trace.exit(this, "com.ibm.mq.headers.MQMD1", "getPutDateTime()", Long.valueOf(traceRet2), 2);
/*      */       }
/*      */       
/*  851 */       return traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutDateTime(long millis) {
/*  862 */     if (Trace.isOn) {
/*  863 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setPutDateTime(long)", "setter", 
/*  864 */           Long.valueOf(millis));
/*      */     }
/*  866 */     Date date = new Date(millis);
/*      */     
/*  868 */     synchronized (MQMD1.class) {
/*  869 */       setPutDate(dateFormat.format(date));
/*      */ 
/*      */ 
/*      */       
/*  873 */       setPutTime(timeFormat.format(date).substring(0, 8));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplOriginData() {
/*  884 */     String traceRet1 = getStringValue(ApplOriginData);
/*  885 */     if (Trace.isOn) {
/*  886 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "getApplOriginData()", "getter", traceRet1);
/*      */     }
/*  888 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplOriginData(String value) {
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.data(this, "com.ibm.mq.headers.MQMD1", "setApplOriginData(String)", "setter", value);
/*      */     }
/*  900 */     setStringValue(ApplOriginData, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextEncoding() {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "nextEncoding()");
/*      */     }
/*  913 */     int traceRet1 = getEncoding();
/*      */     
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "nextEncoding()", Integer.valueOf(traceRet1));
/*      */     }
/*  918 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextEncoding(int value) {
/*  926 */     if (Trace.isOn)
/*  927 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "nextEncoding(int)", new Object[] {
/*  928 */             Integer.valueOf(value)
/*      */           }); 
/*  930 */     setEncoding(value);
/*      */     
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "nextEncoding(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextCharacterSet() {
/*  943 */     if (Trace.isOn) {
/*  944 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "nextCharacterSet()");
/*      */     }
/*  946 */     int traceRet1 = getCodedCharSetId();
/*      */     
/*  948 */     if (Trace.isOn) {
/*  949 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "nextCharacterSet()", 
/*  950 */           Integer.valueOf(traceRet1));
/*      */     }
/*  952 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextCharacterSet(int value) {
/*  960 */     if (Trace.isOn)
/*  961 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "nextCharacterSet(int)", new Object[] {
/*  962 */             Integer.valueOf(value)
/*      */           }); 
/*  964 */     setCodedCharSetId(value);
/*      */     
/*  966 */     if (Trace.isOn) {
/*  967 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "nextCharacterSet(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nextFormat() {
/*  977 */     if (Trace.isOn) {
/*  978 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "nextFormat()");
/*      */     }
/*  980 */     String traceRet1 = getFormat();
/*      */     
/*  982 */     if (Trace.isOn) {
/*  983 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "nextFormat()", traceRet1);
/*      */     }
/*  985 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextFormat(String value) {
/*  993 */     if (Trace.isOn) {
/*  994 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "nextFormat(String)", new Object[] { value });
/*      */     }
/*  996 */     setFormat(value);
/*      */     
/*  998 */     if (Trace.isOn) {
/*  999 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "nextFormat(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format() {
/* 1009 */     if (Trace.isOn) {
/* 1010 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "format()");
/*      */     }
/* 1012 */     if (Trace.isOn) {
/* 1013 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "format()", null);
/*      */     }
/* 1015 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasExtensionContent() {
/* 1025 */     if (Trace.isOn) {
/* 1026 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "hasExtensionContent()");
/*      */     }
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "hasExtensionContent()", Boolean.valueOf(false));
/*      */     }
/*      */     
/* 1032 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copyFrom(MQMD md) {
/* 1041 */     if (Trace.isOn) {
/* 1042 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "copyFrom(com.ibm.mq.MQMD)", new Object[] { md });
/*      */     }
/*      */     
/* 1045 */     setReport(md.report);
/* 1046 */     setMsgType(md.messageType);
/* 1047 */     setExpiry(md.expiry);
/* 1048 */     setFeedback(md.feedback);
/* 1049 */     setEncoding(md.encoding);
/* 1050 */     setCodedCharSetId(md.characterSet);
/* 1051 */     setFormat(md.format);
/* 1052 */     setPriority(md.priority);
/* 1053 */     setPersistence(md.persistence);
/* 1054 */     setMsgId(md.messageId);
/* 1055 */     setCorrelId(md.correlationId);
/* 1056 */     setBackoutCount(md.backoutCount);
/* 1057 */     setReplyToQ(md.replyToQueueName);
/* 1058 */     setReplyToQMgr(md.replyToQueueManagerName);
/* 1059 */     setUserIdentifier(md.userId);
/* 1060 */     setAccountingToken(md.accountingToken);
/* 1061 */     setApplIdentityData(md.applicationIdData);
/* 1062 */     setPutApplType(md.putApplicationType);
/* 1063 */     setPutApplName(md.putApplicationName);
/* 1064 */     setPutDateTime(md.putDateTime.getTimeInMillis());
/* 1065 */     setApplOriginData(md.applicationOriginData);
/*      */     
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "copyFrom(com.ibm.mq.MQMD)");
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
/*      */   public void copyTo(MQMD md) throws MQDataException {
/* 1080 */     if (Trace.isOn) {
/* 1081 */       Trace.entry(this, "com.ibm.mq.headers.MQMD1", "copyTo(com.ibm.mq.MQMD)", new Object[] { md });
/*      */     }
/*      */     try {
/* 1084 */       md.setVersion(1);
/*      */     }
/* 1086 */     catch (Exception e) {
/* 1087 */       if (Trace.isOn) {
/* 1088 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD1", "copyTo(com.ibm.mq.MQMD)", e);
/*      */       }
/*      */       
/* 1091 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/* 1092 */       if (Trace.isOn) {
/* 1093 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD1", "copyTo(com.ibm.mq.MQMD)", traceRet1);
/*      */       }
/* 1095 */       throw traceRet1;
/*      */     } 
/* 1097 */     md.report = getReport();
/* 1098 */     md.messageType = getMsgType();
/* 1099 */     md.expiry = getExpiry();
/* 1100 */     md.feedback = getFeedback();
/* 1101 */     md.encoding = getEncoding();
/* 1102 */     md.characterSet = getCodedCharSetId();
/* 1103 */     md.format = getFormat();
/* 1104 */     md.priority = getPriority();
/* 1105 */     md.persistence = getPersistence();
/* 1106 */     md.messageId = getMsgId();
/* 1107 */     md.correlationId = getCorrelId();
/* 1108 */     md.backoutCount = getBackoutCount();
/* 1109 */     md.replyToQueueName = getReplyToQ();
/* 1110 */     md.replyToQueueManagerName = getReplyToQMgr();
/* 1111 */     md.userId = getUserIdentifier();
/* 1112 */     md.accountingToken = getAccountingToken();
/* 1113 */     md.applicationIdData = getApplIdentityData();
/* 1114 */     md.putApplicationType = getPutApplType();
/* 1115 */     md.putApplicationName = getPutApplName();
/* 1116 */     md.putDateTime.setTimeInMillis(getPutDateTime());
/* 1117 */     md.applicationOriginData = getApplOriginData();
/* 1118 */     if (Trace.isOn)
/* 1119 */       Trace.exit(this, "com.ibm.mq.headers.MQMD1", "copyTo(com.ibm.mq.MQMD)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQMD1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */