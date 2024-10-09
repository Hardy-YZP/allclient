/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.nio.charset.CodingErrorAction;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQMD
/*      */   extends AbstractMqiStructure
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQMD.java";
/*      */   private static final int SIZEOF_STRUCID = 4;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_REPORT = 4;
/*      */   private static final int SIZEOF_MSG_TYPE = 4;
/*      */   private static final int SIZEOF_EXPIRY = 4;
/*      */   private static final int SIZEOF_FEEDBACK = 4;
/*      */   private static final int SIZEOF_ENCODING = 4;
/*      */   private static final int SIZEOF_CCSID = 4;
/*      */   private static final int SIZEOF_FORMAT = 8;
/*      */   private static final int SIZEOF_PRIORITY = 4;
/*      */   private static final int SIZEOF_PERSISTENCE = 4;
/*      */   private static final int SIZEOF_MSGID = 24;
/*      */   private static final int SIZEOF_CORRELID = 24;
/*      */   private static final int SIZEOF_BACKOUT_COUNT = 4;
/*      */   
/*      */   static {
/*   44 */     if (Trace.isOn) {
/*   45 */       Trace.data("com.ibm.mq.jmqi.MQMD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQMD.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_REPLY_TO_Q = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_REPLY_TO_Q_MGR = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_USER_IDENTIFIER = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_ACCOUNTING_TOKEN = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_APPL_IDENTITY_DATA = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_PUT_APPL_TYPE = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_PUT_APPL_NAME = 28;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_PUT_DATE = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_PUT_TIME = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_APPL_ORIGIN_DATA = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_GROUP_ID = 24;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_MSG_SEQ_NUMBER = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_OFFSET = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_MSG_FLAGS = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int SIZEOF_ORIGINAL_LENGTH = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   private int version = 1;
/*  129 */   private int report = 0;
/*  130 */   private int msgType = 8;
/*  131 */   private int expiry = -1;
/*  132 */   private int feedback = 0;
/*  133 */   private int encoding = 273;
/*  134 */   private int codedCharSetId = 0;
/*  135 */   private String format = "        ";
/*  136 */   private int priority = -1;
/*  137 */   private int persistence = 2;
/*  138 */   private byte[] msgId = new byte[24];
/*  139 */   private byte[] correlId = new byte[24];
/*  140 */   private int backoutCount = 0;
/*  141 */   private String replyToQ = null;
/*  142 */   private String replyToQMgr = null;
/*  143 */   private String userIdentifier = null;
/*  144 */   private byte[] accountingToken = new byte[32];
/*  145 */   private String applIdentityData = null;
/*  146 */   private int putApplType = 0;
/*  147 */   private String putApplName = null;
/*  148 */   private String putDate = null;
/*  149 */   private String putTime = null;
/*  150 */   private String applOriginData = null;
/*  151 */   private byte[] groupId = new byte[24];
/*  152 */   private int msgSeqNumber = 1;
/*  153 */   private int physicalMsgOffset = 0;
/*  154 */   private int msgFlags = 0;
/*  155 */   private int originalLength = -1;
/*  156 */   private CodingErrorAction unmappableAction = CodingErrorAction.REPLACE;
/*  157 */   private byte[] unMappableReplacement = new byte[] { 63 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV1(int sizeofNativePointer) {
/*  167 */     int sizeOfStructureV1 = 324;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  173 */     return sizeOfStructureV1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV2(int sizeofNativePointer) {
/*  184 */     int sizeOfStructureV2 = getSizeV1(sizeofNativePointer) + 24 + 4 + 4 + 4 + 4;
/*      */     
/*  186 */     return sizeOfStructureV2;
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
/*      */   public static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/*      */     int size;
/*  202 */     if (version == 1) {
/*  203 */       size = getSizeV1(sizeofNativePointer);
/*      */     }
/*  205 */     else if (version == 2) {
/*  206 */       size = getSizeV2(sizeofNativePointer);
/*      */     }
/*      */     else {
/*      */       
/*  210 */       JmqiException e = new JmqiException(env, -1, null, 2, 2026, null);
/*      */       
/*  212 */       throw e;
/*      */     } 
/*  214 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQMD(JmqiEnvironment env) {
/*  223 */     super(env);
/*  224 */     if (Trace.isOn) {
/*  225 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMD", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*  227 */     if (Trace.isOn) {
/*  228 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMD", "<init>(JmqiEnvironment)");
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
/*      */   protected MQMD(JmqiEnvironment env, MQMD source) {
/*  240 */     super(env);
/*  241 */     if (Trace.isOn) {
/*  242 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMD", "<init>(JmqiEnvironment,MQMD)", new Object[] { env, source });
/*      */     }
/*      */ 
/*      */     
/*  246 */     this.version = source.version;
/*  247 */     this.report = source.report;
/*  248 */     this.msgType = source.msgType;
/*  249 */     this.expiry = source.expiry;
/*  250 */     this.feedback = source.feedback;
/*  251 */     this.encoding = source.encoding;
/*  252 */     this.codedCharSetId = source.codedCharSetId;
/*  253 */     this.unmappableAction = source.unmappableAction;
/*  254 */     this.unMappableReplacement = source.unMappableReplacement;
/*  255 */     this.format = source.format;
/*  256 */     this.priority = source.priority;
/*  257 */     this.persistence = source.persistence;
/*  258 */     JmqiTools.byteArrayCopy(source.msgId, 0, this.msgId, 0, 24);
/*  259 */     JmqiTools.byteArrayCopy(source.correlId, 0, this.correlId, 0, 24);
/*  260 */     this.backoutCount = source.backoutCount;
/*  261 */     this.replyToQ = source.replyToQ;
/*  262 */     this.replyToQMgr = source.replyToQMgr;
/*  263 */     this.userIdentifier = source.userIdentifier;
/*  264 */     JmqiTools.byteArrayCopy(source.accountingToken, 0, this.accountingToken, 0, 32);
/*      */     
/*  266 */     this.applIdentityData = source.applIdentityData;
/*  267 */     this.putApplType = source.putApplType;
/*  268 */     this.putApplName = source.putApplName;
/*  269 */     this.putDate = source.putDate;
/*  270 */     this.putTime = source.putTime;
/*  271 */     this.applOriginData = source.applOriginData;
/*  272 */     JmqiTools.byteArrayCopy(source.groupId, 0, this.groupId, 0, 24);
/*  273 */     this.msgSeqNumber = source.msgSeqNumber;
/*  274 */     this.physicalMsgOffset = source.physicalMsgOffset;
/*  275 */     this.msgFlags = source.msgFlags;
/*  276 */     this.originalLength = source.originalLength;
/*      */     
/*  278 */     if (Trace.isOn) {
/*  279 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMD", "<init>(JmqiEnvironment,MQMD)");
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
/*      */   static void conditionalCopy(MQMD source, MQMD target) {
/*  296 */     target.report = source.report;
/*  297 */     target.msgType = source.msgType;
/*  298 */     target.expiry = source.expiry;
/*  299 */     target.feedback = source.feedback;
/*  300 */     target.encoding = source.encoding;
/*  301 */     target.codedCharSetId = source.codedCharSetId;
/*  302 */     target.unmappableAction = source.unmappableAction;
/*  303 */     target.unMappableReplacement = source.unMappableReplacement;
/*  304 */     target.format = source.format;
/*  305 */     target.priority = source.priority;
/*  306 */     target.persistence = source.persistence;
/*      */ 
/*      */     
/*  309 */     System.arraycopy(source.msgId, 0, target.msgId, 0, 24);
/*  310 */     System.arraycopy(source.correlId, 0, target.correlId, 0, 24);
/*  311 */     target.backoutCount = source.backoutCount;
/*  312 */     target.replyToQ = source.replyToQ;
/*  313 */     target.replyToQMgr = source.replyToQMgr;
/*  314 */     target.userIdentifier = source.userIdentifier;
/*  315 */     System.arraycopy(source.accountingToken, 0, target.accountingToken, 0, 32);
/*      */     
/*  317 */     target.applIdentityData = source.applIdentityData;
/*  318 */     target.putApplType = source.putApplType;
/*  319 */     target.putApplName = source.putApplName;
/*  320 */     target.putDate = source.putDate;
/*  321 */     target.putTime = source.putTime;
/*  322 */     target.applOriginData = source.applOriginData;
/*      */ 
/*      */     
/*  325 */     if (target.version >= 2) {
/*  326 */       System.arraycopy(source.groupId, 0, target.groupId, 0, 24);
/*  327 */       target.msgSeqNumber = source.msgSeqNumber;
/*  328 */       target.physicalMsgOffset = source.physicalMsgOffset;
/*  329 */       target.msgFlags = source.msgFlags;
/*  330 */       target.originalLength = source.originalLength;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getAccountingToken() {
/*  340 */     if (Trace.isOn) {
/*  341 */       Trace.data(this, "getAccountingToken()", this.accountingToken);
/*      */     }
/*  343 */     return this.accountingToken;
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
/*      */   public void setAccountingToken(byte[] accountingToken) {
/*  357 */     if (Trace.isOn) {
/*  358 */       Trace.data(this, "setAccountingToken(byte [ ])", accountingToken);
/*      */     }
/*      */     
/*  361 */     JmqiTools.byteArrayCopy(accountingToken, 0, this.accountingToken, 0, 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplIdentityData() {
/*  369 */     if (Trace.isOn) {
/*  370 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getApplIdentityData()", "getter", this.applIdentityData);
/*      */     }
/*      */     
/*  373 */     return this.applIdentityData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplIdentityData(String applIdentityData) {
/*  381 */     if (Trace.isOn) {
/*  382 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setApplIdentityData(String)", "setter", applIdentityData);
/*      */     }
/*      */     
/*  385 */     this.applIdentityData = applIdentityData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplOriginData() {
/*  393 */     if (Trace.isOn) {
/*  394 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getApplOriginData()", "getter", this.applOriginData);
/*      */     }
/*  396 */     return this.applOriginData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplOriginData(String applOriginData) {
/*  404 */     if (Trace.isOn) {
/*  405 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setApplOriginData(String)", "setter", applOriginData);
/*      */     }
/*      */     
/*  408 */     this.applOriginData = applOriginData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBackoutCount() {
/*  416 */     if (Trace.isOn) {
/*  417 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getBackoutCount()", "getter", 
/*  418 */           Integer.valueOf(this.backoutCount));
/*      */     }
/*  420 */     return this.backoutCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackoutCount(int backoutCount) {
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setBackoutCount(int)", "setter", 
/*  430 */           Integer.valueOf(backoutCount));
/*      */     }
/*  432 */     this.backoutCount = backoutCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCodedCharSetId() {
/*  440 */     if (Trace.isOn) {
/*  441 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getCodedCharSetId()", "getter", 
/*  442 */           Integer.valueOf(this.codedCharSetId));
/*      */     }
/*  444 */     return this.codedCharSetId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCodedCharSetId(int codedCharSetId) {
/*  452 */     if (Trace.isOn) {
/*  453 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setCodedCharSetId(int)", "setter", 
/*  454 */           Integer.valueOf(codedCharSetId));
/*      */     }
/*  456 */     this.codedCharSetId = codedCharSetId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getCorrelId() {
/*  466 */     if (Trace.isOn) {
/*  467 */       Trace.data(this, "getCorrelId()", this.correlId);
/*      */     }
/*  469 */     return this.correlId;
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
/*      */   public void setCorrelId(byte[] correlId) {
/*  482 */     if (Trace.isOn) {
/*  483 */       Trace.data(this, "setCorrelId(byte [ ])", correlId);
/*      */     }
/*  485 */     JmqiTools.byteArrayCopy(correlId, 0, this.correlId, 0, 24);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  492 */     if (Trace.isOn) {
/*  493 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getEncoding()", "getter", 
/*  494 */           Integer.valueOf(this.encoding));
/*      */     }
/*  496 */     return this.encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int encoding) {
/*  504 */     if (Trace.isOn) {
/*  505 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setEncoding(int)", "setter", 
/*  506 */           Integer.valueOf(encoding));
/*      */     }
/*  508 */     this.encoding = encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpiry() {
/*  516 */     if (Trace.isOn) {
/*  517 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getExpiry()", "getter", Integer.valueOf(this.expiry));
/*      */     }
/*  519 */     return this.expiry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExpiry(int expiry) {
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setExpiry(int)", "setter", Integer.valueOf(expiry));
/*      */     }
/*      */     
/*  531 */     this.expiry = expiry;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeedback() {
/*  539 */     if (Trace.isOn) {
/*  540 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getFeedback()", "getter", 
/*  541 */           Integer.valueOf(this.feedback));
/*      */     }
/*  543 */     return this.feedback;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeedback(int feedback) {
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setFeedback(int)", "setter", 
/*  553 */           Integer.valueOf(feedback));
/*      */     }
/*  555 */     this.feedback = feedback;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/*  563 */     if (Trace.isOn) {
/*  564 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getFormat()", "getter", this.format);
/*      */     }
/*  566 */     return this.format;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String format) {
/*  574 */     if (Trace.isOn) {
/*  575 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setFormat(String)", "setter", format);
/*      */     }
/*      */     
/*  578 */     if (format == null) {
/*  579 */       this.format = "        ";
/*      */     }
/*  581 */     else if (format.length() < 8) {
/*  582 */       this.format = JmqiTools.left(format, 8);
/*      */     } else {
/*      */       
/*  585 */       this.format = format;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getGroupId() {
/*  596 */     if (Trace.isOn) {
/*  597 */       Trace.data(this, "getGroupId()", this.groupId);
/*      */     }
/*  599 */     return this.groupId;
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
/*      */   public void setGroupId(byte[] groupId) {
/*  612 */     if (Trace.isOn) {
/*  613 */       Trace.data(this, "setGroupId(byte [ ])", groupId);
/*      */     }
/*  615 */     JmqiTools.byteArrayCopy(groupId, 0, this.groupId, 0, 24);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgFlags() {
/*  622 */     if (Trace.isOn) {
/*  623 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getMsgFlags()", "getter", 
/*  624 */           Integer.valueOf(this.msgFlags));
/*      */     }
/*  626 */     return this.msgFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgFlags(int msgFlags) {
/*  634 */     if (Trace.isOn) {
/*  635 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setMsgFlags(int)", "setter", 
/*  636 */           Integer.valueOf(msgFlags));
/*      */     }
/*  638 */     this.msgFlags = msgFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgId() {
/*  648 */     if (Trace.isOn) {
/*  649 */       Trace.data(this, "getMsgId()", this.msgId);
/*      */     }
/*  651 */     return this.msgId;
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
/*      */   public void setMsgId(byte[] msgId) {
/*  664 */     if (Trace.isOn) {
/*  665 */       Trace.data(this, "setMsgId(byte [ ])", msgId);
/*      */     }
/*  667 */     JmqiTools.byteArrayCopy(msgId, 0, this.msgId, 0, 24);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgSeqNumber() {
/*  674 */     if (Trace.isOn) {
/*  675 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getMsgSeqNumber()", "getter", 
/*  676 */           Integer.valueOf(this.msgSeqNumber));
/*      */     }
/*  678 */     return this.msgSeqNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgSeqNumber(int msgSeqNumber) {
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setMsgSeqNumber(int)", "setter", 
/*  688 */           Integer.valueOf(msgSeqNumber));
/*      */     }
/*  690 */     this.msgSeqNumber = msgSeqNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgType() {
/*  698 */     if (Trace.isOn) {
/*  699 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getMsgType()", "getter", Integer.valueOf(this.msgType));
/*      */     }
/*      */     
/*  702 */     return this.msgType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgType(int msgType) {
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setMsgType(int)", "setter", 
/*  712 */           Integer.valueOf(msgType));
/*      */     }
/*  714 */     this.msgType = msgType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOffset() {
/*  722 */     if (Trace.isOn) {
/*  723 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getOffset()", "getter", 
/*  724 */           Integer.valueOf(this.physicalMsgOffset));
/*      */     }
/*  726 */     return this.physicalMsgOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOffset(int physicalMsgOffset) {
/*  734 */     if (Trace.isOn) {
/*  735 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setOffset(int)", "setter", 
/*  736 */           Integer.valueOf(physicalMsgOffset));
/*      */     }
/*  738 */     this.physicalMsgOffset = physicalMsgOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOriginalLength() {
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getOriginalLength()", "getter", 
/*  748 */           Integer.valueOf(this.originalLength));
/*      */     }
/*  750 */     return this.originalLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOriginalLength(int originalLength) {
/*  758 */     if (Trace.isOn) {
/*  759 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setOriginalLength(int)", "setter", 
/*  760 */           Integer.valueOf(originalLength));
/*      */     }
/*  762 */     this.originalLength = originalLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPersistence() {
/*  770 */     if (Trace.isOn) {
/*  771 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getPersistence()", "getter", 
/*  772 */           Integer.valueOf(this.persistence));
/*      */     }
/*  774 */     return this.persistence;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistence(int persistence) {
/*  782 */     if (Trace.isOn) {
/*  783 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setPersistence(int)", "setter", 
/*  784 */           Integer.valueOf(persistence));
/*      */     }
/*  786 */     this.persistence = persistence;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPriority() {
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getPriority()", "getter", 
/*  796 */           Integer.valueOf(this.priority));
/*      */     }
/*  798 */     return this.priority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(int priority) {
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setPriority(int)", "setter", 
/*  808 */           Integer.valueOf(priority));
/*      */     }
/*  810 */     this.priority = priority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutApplName() {
/*  818 */     if (Trace.isOn) {
/*  819 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getPutApplName()", "getter", this.putApplName);
/*      */     }
/*  821 */     return this.putApplName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplName(String putApplName) {
/*  829 */     if (Trace.isOn) {
/*  830 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setPutApplName(String)", "setter", putApplName);
/*      */     }
/*  832 */     this.putApplName = putApplName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPutApplType() {
/*  840 */     if (Trace.isOn) {
/*  841 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getPutApplType()", "getter", 
/*  842 */           Integer.valueOf(this.putApplType));
/*      */     }
/*  844 */     return this.putApplType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutApplType(int putApplType) {
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setPutApplType(int)", "setter", 
/*  854 */           Integer.valueOf(putApplType));
/*      */     }
/*  856 */     this.putApplType = putApplType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutDate() {
/*  864 */     if (Trace.isOn) {
/*  865 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getPutDate()", "getter", this.putDate);
/*      */     }
/*  867 */     return this.putDate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutDate(String putDate) {
/*  875 */     if (Trace.isOn) {
/*  876 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setPutDate(String)", "setter", putDate);
/*      */     }
/*  878 */     this.putDate = putDate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPutTime() {
/*  886 */     if (Trace.isOn) {
/*  887 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getPutTime()", "getter", this.putTime);
/*      */     }
/*  889 */     return this.putTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutTime(String putTime) {
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setPutTime(String)", "setter", putTime);
/*      */     }
/*  900 */     this.putTime = putTime;
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
/*      */   public void setPutDateAndTime(int year, int month, int day, int hour, int minute, int second, int millis) {
/*  917 */     if (Trace.isOn) {
/*  918 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMD", "setPutDateAndTime(int,int,int,int,int,int,int)", new Object[] {
/*  919 */             Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), 
/*  920 */             Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second), 
/*  921 */             Integer.valueOf(millis)
/*      */           });
/*      */     }
/*  924 */     StringBuffer buffer = new StringBuffer();
/*  925 */     buffer.append(JmqiTools.fix(year, 4));
/*  926 */     buffer.append(JmqiTools.fix(month, 2));
/*  927 */     buffer.append(JmqiTools.fix(day, 2));
/*  928 */     this.putDate = buffer.toString();
/*      */     
/*  930 */     buffer = new StringBuffer();
/*  931 */     buffer.append(JmqiTools.fix(hour, 2));
/*  932 */     buffer.append(JmqiTools.fix(minute, 2));
/*  933 */     buffer.append(JmqiTools.fix(second, 2));
/*  934 */     buffer.append(JmqiTools.fix(millis, 2));
/*  935 */     this.putTime = buffer.toString();
/*      */     
/*  937 */     if (Trace.isOn) {
/*  938 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMD", "setPutDateAndTime(int,int,int,int,int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQ() {
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getReplyToQ()", "getter", this.replyToQ);
/*      */     }
/*  950 */     return this.replyToQ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQ(String replyToQ) {
/*  958 */     if (Trace.isOn) {
/*  959 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setReplyToQ(String)", "setter", replyToQ);
/*      */     }
/*  961 */     this.replyToQ = replyToQ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToQMgr() {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getReplyToQMgr()", "getter", this.replyToQMgr);
/*      */     }
/*  972 */     return this.replyToQMgr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToQMgr(String replyToQmgr) {
/*  980 */     if (Trace.isOn) {
/*  981 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setReplyToQMgr(String)", "setter", replyToQmgr);
/*      */     }
/*  983 */     this.replyToQMgr = replyToQmgr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReport() {
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getReport()", "getter", Integer.valueOf(this.report));
/*      */     }
/*  994 */     return this.report;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReport(int report) {
/* 1002 */     if (Trace.isOn) {
/* 1003 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setReport(int)", "setter", Integer.valueOf(report));
/*      */     }
/*      */     
/* 1006 */     this.report = report;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserIdentifier() {
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getUserIdentifier()", "getter", this.userIdentifier);
/*      */     }
/* 1017 */     return this.userIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserIdentifier(String userIdentifier) {
/* 1025 */     if (Trace.isOn) {
/* 1026 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setUserIdentifier(String)", "setter", userIdentifier);
/*      */     }
/*      */     
/* 1029 */     this.userIdentifier = userIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/* 1038 */     if (Trace.isOn) {
/* 1039 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getVersion()", "getter", Integer.valueOf(this.version));
/*      */     }
/*      */     
/* 1042 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/* 1051 */     if (Trace.isOn) {
/* 1052 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setVersion(int)", "setter", 
/* 1053 */           Integer.valueOf(version));
/*      */     }
/* 1055 */     this.version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 1065 */     return getSize(this.env, this.version, ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1075 */     return writeToBuffer(buffer, pos, false, true, true, ptrSize, swap, cp, tls);
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
/*      */   public int writeToBuffer(byte[] buffer, int offset, boolean isGet, boolean setId, boolean setOrig, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1099 */     if (Trace.isOn) {
/* 1100 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMD", "writeToBuffer(byte [ ],int,boolean,boolean,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1102 */             Integer.valueOf(offset), Boolean.valueOf(isGet), 
/* 1103 */             Boolean.valueOf(setId), Boolean.valueOf(setOrig), Integer.valueOf(ptrSize), 
/* 1104 */             Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 1106 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 1107 */     int pos = offset;
/*      */     
/* 1109 */     dc.writeMQField("MD  ", buffer, pos, 4, cp, tls);
/* 1110 */     pos += 4;
/*      */     
/* 1112 */     dc.writeI32(this.version, buffer, pos, swap);
/* 1113 */     pos += 4;
/* 1114 */     if (isGet) {
/*      */       
/* 1116 */       dc.clear(buffer, pos, 16);
/* 1117 */       pos += 16;
/*      */     }
/*      */     else {
/*      */       
/* 1121 */       dc.writeI32(this.report, buffer, pos, swap);
/* 1122 */       pos += 4;
/*      */       
/* 1124 */       dc.writeI32(this.msgType, buffer, pos, swap);
/* 1125 */       pos += 4;
/*      */       
/* 1127 */       dc.writeI32(this.expiry, buffer, pos, swap);
/* 1128 */       pos += 4;
/*      */       
/* 1130 */       dc.writeI32(this.feedback, buffer, pos, swap);
/* 1131 */       pos += 4;
/*      */     } 
/*      */     
/* 1134 */     dc.writeI32(this.encoding, buffer, pos, swap);
/* 1135 */     pos += 4;
/*      */     
/* 1137 */     dc.writeI32(this.codedCharSetId, buffer, pos, swap);
/* 1138 */     pos += 4;
/* 1139 */     if (isGet) {
/*      */       
/* 1141 */       dc.clear(buffer, pos, 16);
/* 1142 */       pos += 16;
/*      */     }
/*      */     else {
/*      */       
/* 1146 */       dc.writeMQField(this.format, buffer, pos, 8, cp, tls);
/* 1147 */       pos += 8;
/*      */       
/* 1149 */       dc.writeI32(this.priority, buffer, pos, swap);
/* 1150 */       pos += 4;
/*      */       
/* 1152 */       dc.writeI32(this.persistence, buffer, pos, swap);
/* 1153 */       pos += 4;
/*      */     } 
/*      */     
/* 1156 */     System.arraycopy(this.msgId, 0, buffer, pos, 24);
/* 1157 */     pos += 24;
/*      */     
/* 1159 */     System.arraycopy(this.correlId, 0, buffer, pos, 24);
/* 1160 */     pos += 24;
/* 1161 */     if (isGet) {
/* 1162 */       int skip = 228;
/*      */ 
/*      */ 
/*      */       
/* 1166 */       dc.clear(buffer, pos, skip);
/* 1167 */       pos += skip;
/*      */     }
/*      */     else {
/*      */       
/* 1171 */       dc.writeI32(this.backoutCount, buffer, pos, swap);
/* 1172 */       pos += 4;
/*      */       
/* 1174 */       dc.writeMQField(this.replyToQ, buffer, pos, 48, cp, tls);
/* 1175 */       pos += 48;
/*      */       
/* 1177 */       dc.writeMQField(this.replyToQMgr, buffer, pos, 48, cp, tls);
/* 1178 */       pos += 48;
/*      */       
/* 1180 */       if (setId) {
/*      */         
/* 1182 */         dc.writeMQField(this.userIdentifier, buffer, pos, 12, cp, tls);
/* 1183 */         pos += 12;
/*      */         
/* 1185 */         System.arraycopy(this.accountingToken, 0, buffer, pos, 32);
/* 1186 */         pos += 32;
/*      */         
/* 1188 */         dc.writeField(this.applIdentityData, buffer, pos, 32, cp, tls);
/* 1189 */         pos += 32;
/*      */       } else {
/*      */         
/* 1192 */         dc.clear(buffer, pos, 76);
/*      */         
/* 1194 */         pos += 76;
/*      */       } 
/*      */       
/* 1197 */       if (setOrig) {
/*      */         
/* 1199 */         dc.writeI32(this.putApplType, buffer, pos, swap);
/* 1200 */         pos += 4;
/*      */         
/* 1202 */         dc.writeField(this.putApplName, buffer, pos, 28, cp, tls);
/* 1203 */         pos += 28;
/*      */         
/* 1205 */         dc.writeMQField(this.putDate, buffer, pos, 8, cp, tls);
/* 1206 */         pos += 8;
/*      */         
/* 1208 */         dc.writeMQField(this.putTime, buffer, pos, 8, cp, tls);
/* 1209 */         pos += 8;
/*      */         
/* 1211 */         dc.writeMQField(this.applOriginData, buffer, pos, 4, cp, tls);
/* 1212 */         pos += 4;
/*      */       } else {
/*      */         
/* 1215 */         int skip = 52;
/*      */         
/* 1217 */         dc.clear(buffer, pos, skip);
/* 1218 */         pos += skip;
/*      */       } 
/*      */     } 
/*      */     
/* 1222 */     if (this.version >= 2) {
/*      */       
/* 1224 */       System.arraycopy(this.groupId, 0, buffer, pos, 24);
/* 1225 */       pos += 24;
/*      */       
/* 1227 */       dc.writeI32(this.msgSeqNumber, buffer, pos, swap);
/* 1228 */       pos += 4;
/*      */       
/* 1230 */       dc.writeI32(this.physicalMsgOffset, buffer, pos, swap);
/* 1231 */       pos += 4;
/* 1232 */       if (isGet) {
/* 1233 */         dc.clear(buffer, pos, 8);
/* 1234 */         pos += 8;
/*      */       }
/*      */       else {
/*      */         
/* 1238 */         dc.writeI32(this.msgFlags, buffer, pos, swap);
/* 1239 */         pos += 4;
/*      */         
/* 1241 */         dc.writeI32(this.originalLength, buffer, pos, swap);
/* 1242 */         pos += 4;
/*      */       } 
/*      */     } 
/*      */     
/* 1246 */     if (Trace.isOn) {
/* 1247 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMD", "writeToBuffer(byte [ ],int,boolean,boolean,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*      */           
/* 1249 */           Integer.valueOf(pos));
/*      */     }
/* 1251 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1261 */     return readFromBuffer(buffer, pos, false, false, false, ptrSize, swap, cp, tls);
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
/*      */   public int readFromBuffer(byte[] buffer, int offset, boolean isPut, boolean setId, boolean setOrig, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMD", "readFromBuffer(byte [ ],int,boolean,boolean,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1287 */             Integer.valueOf(offset), Boolean.valueOf(isPut), 
/* 1288 */             Boolean.valueOf(setId), Boolean.valueOf(setOrig), Integer.valueOf(ptrSize), 
/* 1289 */             Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 1291 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 1292 */     int pos = offset;
/*      */     
/* 1294 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1295 */     if (!strucId.equals("MD  ")) {
/*      */       
/* 1297 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2026, null);
/*      */       
/* 1299 */       if (Trace.isOn) {
/* 1300 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQMD", "readFromBuffer(byte [ ],int,boolean,boolean,boolean,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*      */       }
/*      */ 
/*      */       
/* 1304 */       throw traceRet1;
/*      */     } 
/* 1306 */     pos += 4;
/*      */     
/* 1308 */     if (isPut) {
/*      */       
/* 1310 */       pos += 44;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1315 */       this.version = dc.readI32(buffer, pos, swap);
/* 1316 */       pos += 4;
/*      */ 
/*      */       
/* 1319 */       this.report = dc.readI32(buffer, pos, swap);
/* 1320 */       pos += 4;
/*      */ 
/*      */       
/* 1323 */       this.msgType = dc.readI32(buffer, pos, swap);
/* 1324 */       pos += 4;
/*      */ 
/*      */       
/* 1327 */       this.expiry = dc.readI32(buffer, pos, swap);
/* 1328 */       pos += 4;
/*      */ 
/*      */       
/* 1331 */       this.feedback = dc.readI32(buffer, pos, swap);
/* 1332 */       pos += 4;
/*      */ 
/*      */       
/* 1335 */       this.encoding = dc.readI32(buffer, pos, swap);
/* 1336 */       pos += 4;
/*      */ 
/*      */       
/* 1339 */       this.codedCharSetId = dc.readI32(buffer, pos, swap);
/* 1340 */       pos += 4;
/*      */ 
/*      */       
/* 1343 */       this.format = dc.readMQField(buffer, pos, 8, cp, tls);
/* 1344 */       pos += 8;
/*      */ 
/*      */       
/* 1347 */       this.priority = dc.readI32(buffer, pos, swap);
/* 1348 */       pos += 4;
/*      */ 
/*      */       
/* 1351 */       this.persistence = dc.readI32(buffer, pos, swap);
/* 1352 */       pos += 4;
/*      */     } 
/*      */ 
/*      */     
/* 1356 */     System.arraycopy(buffer, pos, this.msgId, 0, 24);
/* 1357 */     pos += 24;
/*      */ 
/*      */     
/* 1360 */     System.arraycopy(buffer, pos, this.correlId, 0, 24);
/* 1361 */     pos += 24;
/*      */     
/* 1363 */     if (isPut) {
/*      */       
/* 1365 */       pos += 100;
/*      */     }
/*      */     else {
/*      */       
/* 1369 */       this.backoutCount = dc.readI32(buffer, pos, swap);
/* 1370 */       pos += 4;
/*      */ 
/*      */       
/* 1373 */       this.replyToQ = dc.readMQField(buffer, pos, 48, cp, tls);
/* 1374 */       pos += 48;
/*      */ 
/*      */       
/* 1377 */       this.replyToQMgr = dc.readMQField(buffer, pos, 48, cp, tls);
/* 1378 */       pos += 48;
/*      */     } 
/*      */ 
/*      */     
/* 1382 */     if (setId) {
/* 1383 */       pos += 76;
/*      */     }
/*      */     else {
/*      */       
/* 1387 */       this.userIdentifier = dc.readMQField(buffer, pos, 12, cp, tls);
/* 1388 */       pos += 12;
/*      */ 
/*      */       
/* 1391 */       System.arraycopy(buffer, pos, this.accountingToken, 0, 32);
/* 1392 */       pos += 32;
/*      */ 
/*      */       
/* 1395 */       this.applIdentityData = dc.readField(buffer, pos, 32, cp, tls);
/* 1396 */       pos += 32;
/*      */     } 
/*      */ 
/*      */     
/* 1400 */     if (setOrig) {
/* 1401 */       pos += 52;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1406 */       this.putApplType = dc.readI32(buffer, pos, swap);
/* 1407 */       pos += 4;
/*      */ 
/*      */       
/* 1410 */       this.putApplName = dc.readField(buffer, pos, 28, cp, tls);
/* 1411 */       pos += 28;
/*      */ 
/*      */       
/* 1414 */       this.putDate = dc.readMQField(buffer, pos, 8, cp, tls);
/* 1415 */       pos += 8;
/*      */ 
/*      */       
/* 1418 */       this.putTime = dc.readMQField(buffer, pos, 8, cp, tls);
/* 1419 */       pos += 8;
/*      */ 
/*      */       
/* 1422 */       this.applOriginData = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1423 */       pos += 4;
/*      */     } 
/*      */ 
/*      */     
/* 1427 */     if (this.version >= 2) {
/*      */ 
/*      */       
/* 1430 */       System.arraycopy(buffer, pos, this.groupId, 0, 24);
/* 1431 */       pos += 24;
/*      */ 
/*      */       
/* 1434 */       this.msgSeqNumber = dc.readI32(buffer, pos, swap);
/* 1435 */       pos += 4;
/*      */ 
/*      */       
/* 1438 */       this.physicalMsgOffset = dc.readI32(buffer, pos, swap);
/* 1439 */       pos += 4;
/*      */ 
/*      */       
/* 1442 */       this.msgFlags = dc.readI32(buffer, pos, swap);
/* 1443 */       pos += 4;
/*      */ 
/*      */       
/* 1446 */       this.originalLength = dc.readI32(buffer, pos, swap);
/* 1447 */       pos += 4;
/*      */     } 
/*      */ 
/*      */     
/* 1451 */     if (Trace.isOn) {
/* 1452 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMD", "readFromBuffer(byte [ ],int,boolean,boolean,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*      */           
/* 1454 */           Integer.valueOf(pos));
/*      */     }
/* 1456 */     return pos;
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
/*      */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 1468 */     fmt.add("version", this.version);
/* 1469 */     fmt.add("report", this.report);
/* 1470 */     fmt.add("msgType", this.msgType);
/* 1471 */     fmt.add("expiry", this.expiry);
/* 1472 */     fmt.add("feedback", this.feedback);
/* 1473 */     fmt.add("encoding", this.encoding);
/* 1474 */     fmt.add("codedCharSetId", this.codedCharSetId);
/* 1475 */     fmt.add("format", this.format);
/* 1476 */     fmt.add("priority", this.priority);
/* 1477 */     fmt.add("persistence", this.persistence);
/* 1478 */     fmt.add("msgId", this.msgId);
/* 1479 */     fmt.add("correlId", this.correlId);
/* 1480 */     fmt.add("backoutCount", this.backoutCount);
/* 1481 */     fmt.add("replyToQ", this.replyToQ);
/* 1482 */     fmt.add("replyToQMgr", this.replyToQMgr);
/* 1483 */     fmt.add("userIdentifier", this.userIdentifier);
/* 1484 */     fmt.add("accountingToken", this.accountingToken);
/* 1485 */     fmt.add("applIdentityData", this.applIdentityData);
/* 1486 */     fmt.add("putApplType", this.putApplType);
/* 1487 */     fmt.add("putApplName", this.putApplName);
/* 1488 */     fmt.add("putDate", this.putDate);
/* 1489 */     fmt.add("putTime", this.putTime);
/* 1490 */     fmt.add("applOriginData", this.applOriginData);
/* 1491 */     fmt.add("groupId", this.groupId);
/* 1492 */     fmt.add("msgSeqNumber", this.msgSeqNumber);
/* 1493 */     fmt.add("physicalMsgOffset", this.physicalMsgOffset);
/* 1494 */     fmt.add("msgFlags", this.msgFlags);
/* 1495 */     fmt.add("originalLength", this.originalLength);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CodingErrorAction getUnmappableAction() {
/* 1502 */     if (Trace.isOn) {
/* 1503 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getUnmappableAction()", "getter", this.unmappableAction);
/*      */     }
/*      */     
/* 1506 */     return this.unmappableAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnmappableAction(CodingErrorAction unmappableAction) {
/* 1513 */     if (Trace.isOn) {
/* 1514 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setUnmappableAction(CodingErrorAction)", "setter", unmappableAction);
/*      */     }
/*      */     
/* 1517 */     this.unmappableAction = unmappableAction;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getUnMappableReplacement() {
/* 1524 */     if (Trace.isOn) {
/* 1525 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "getUnMappableReplacement()", "getter", this.unMappableReplacement);
/*      */     }
/*      */     
/* 1528 */     return this.unMappableReplacement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnMappableReplacement(byte[] unMappableReplacement) {
/* 1535 */     if (Trace.isOn) {
/* 1536 */       Trace.data(this, "com.ibm.mq.jmqi.MQMD", "setUnMappableReplacement(byte [ ])", "setter", unMappableReplacement);
/*      */     }
/*      */     
/* 1539 */     this.unMappableReplacement = unMappableReplacement;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQMD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */