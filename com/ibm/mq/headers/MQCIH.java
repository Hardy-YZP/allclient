/*      */ package com.ibm.mq.headers;
/*      */ 
/*      */ import com.ibm.mq.headers.internal.FieldGroup;
/*      */ import com.ibm.mq.headers.internal.Header;
/*      */ import com.ibm.mq.headers.internal.HeaderField;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.IOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQCIH
/*      */   extends Header
/*      */   implements MQChainable
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQCIH.java";
/*      */   public static final int SIZE1 = 164;
/*      */   public static final int SIZE2 = 180;
/*      */   
/*      */   static {
/*   89 */     if (Trace.isOn) {
/*   90 */       Trace.data("com.ibm.mq.headers.MQCIH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQCIH.java");
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
/*  107 */   static final HeaderType v1 = new HeaderType("MQCIH");
/*      */   
/*  109 */   static final HeaderField StrucId = v1.addMQChar("StrucId", "CIH ");
/*  110 */   static final HeaderField Version = v1.addMQLong("Version", 2);
/*  111 */   static final HeaderField StrucLength = v1.addMQLong("StrucLength", 180);
/*  112 */   static final HeaderField Encoding = v1.addMQLong("Encoding");
/*  113 */   static final HeaderField CodedCharSetId = v1.addMQLong("CodedCharSetId");
/*  114 */   static final HeaderField Format = v1.addMQChar("Format", "        ");
/*  115 */   static final HeaderField Flags = v1.addMQLong("Flags");
/*  116 */   static final HeaderField ReturnCode = v1.addMQLong("ReturnCode");
/*  117 */   static final HeaderField CompCode = v1.addMQLong("CompCode");
/*  118 */   static final HeaderField Reason = v1.addMQLong("Reason");
/*  119 */   static final HeaderField UOWControl = v1.addMQLong("UOWControl");
/*  120 */   static final HeaderField GetWaitInterval = v1.addMQLong("GetWaitInterval");
/*  121 */   static final HeaderField LinkType = v1.addMQLong("LinkType");
/*  122 */   static final HeaderField OutputDataLength = v1.addMQLong("OutputDataLength", -1);
/*  123 */   static final HeaderField FacilityKeepTime = v1.addMQLong("FacilityKeepTime");
/*  124 */   static final HeaderField ADSDescriptor = v1.addMQLong("ADSDescriptor");
/*  125 */   static final HeaderField ConversationalTask = v1.addMQLong("ConversationalTask");
/*  126 */   static final HeaderField TaskEndStatus = v1.addMQLong("TaskEndStatus");
/*  127 */   static final HeaderField Facility = v1.addMQByte("Facility", 8);
/*  128 */   static final HeaderField Function = v1.addMQChar("Function", 4);
/*  129 */   static final HeaderField AbendCode = v1.addMQChar("AbendCode", 4);
/*  130 */   static final HeaderField Authenticator = v1.addMQChar("Authenticator", 8);
/*  131 */   static final HeaderField Reserved1 = v1.addMQChar("Reserved1", 8);
/*  132 */   static final HeaderField ReplyToFormat = v1.addMQChar("ReplyToFormat", 8);
/*  133 */   static final HeaderField RemoteSysId = v1.addMQChar("RemoteSysId", 4);
/*  134 */   static final HeaderField RemoteTransId = v1.addMQChar("RemoteTransId", 4);
/*  135 */   static final HeaderField TransactionId = v1.addMQChar("TransactionId", 4);
/*  136 */   static final HeaderField FacilityLike = v1.addMQChar("FacilityLike", 4);
/*  137 */   static final HeaderField AttentionId = v1.addMQChar("AttentionId", 4);
/*  138 */   static final HeaderField StartCode = v1.addMQChar("StartCode", 4);
/*  139 */   static final HeaderField CancelCode = v1.addMQChar("CancelCode", 4);
/*  140 */   static final HeaderField NextTransactionId = v1.addMQChar("NextTransactionId", 4);
/*  141 */   static final HeaderField Reserved2 = v1.addMQChar("Reserved2", 8);
/*  142 */   static final HeaderField Reserved3 = v1.addMQChar("Reserved3", 8);
/*      */   
/*  144 */   static final FieldGroup v2 = v1.addFieldGroup(Version, 2);
/*      */   
/*  146 */   static final HeaderField CursorPosition = v2.addMQLong("CursorPosition");
/*  147 */   static final HeaderField ErrorOffset = v2.addMQLong("ErrorOffset");
/*  148 */   static final HeaderField InputItem = v2.addMQLong("InputItem");
/*  149 */   static final HeaderField Reserved4 = v2.addMQLong("Reserved4");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCIH() {
/*  155 */     super(v1);
/*  156 */     if (Trace.isOn) {
/*  157 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "<init>()");
/*      */     }
/*  159 */     if (Trace.isOn) {
/*  160 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "<init>()");
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
/*      */   public MQCIH(DataInput message) throws MQDataException, IOException {
/*  173 */     this();
/*  174 */     if (Trace.isOn) {
/*  175 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*  177 */     read((DataInput)MessageWrapper.wrap(message));
/*      */     
/*  179 */     if (Trace.isOn) {
/*  180 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "<init>(DataInput)");
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
/*      */   public MQCIH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*  195 */     this();
/*  196 */     if (Trace.isOn) {
/*  197 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "<init>(DataInput,int,int)", new Object[] { message, 
/*  198 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     try {
/*  201 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*      */     }
/*  203 */     catch (Exception e) {
/*  204 */       if (Trace.isOn) {
/*  205 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQCIH", "<init>(DataInput,int,int)", e);
/*      */       }
/*      */       
/*  208 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  209 */       if (Trace.isOn) {
/*  210 */         Trace.throwing(this, "com.ibm.mq.headers.MQCIH", "<init>(DataInput,int,int)", traceRet1);
/*      */       }
/*  212 */       throw traceRet1;
/*      */     } 
/*  214 */     if (Trace.isOn) {
/*  215 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "<init>(DataInput,int,int)");
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
/*  226 */     String traceRet1 = getStringValue(StrucId);
/*  227 */     if (Trace.isOn) {
/*  228 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getStrucId()", "getter", traceRet1);
/*      */     }
/*  230 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  239 */     int traceRet1 = getIntValue(Version);
/*  240 */     if (Trace.isOn) {
/*  241 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getVersion()", "getter", 
/*  242 */           Integer.valueOf(traceRet1));
/*      */     }
/*  244 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int value) {
/*  253 */     if (Trace.isOn) {
/*  254 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setVersion(int)", "setter", 
/*  255 */           Integer.valueOf(value));
/*      */     }
/*  257 */     setIntValue(Version, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStrucLength() {
/*  266 */     int traceRet1 = getIntValue(StrucLength);
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getStrucLength()", "getter", 
/*  269 */           Integer.valueOf(traceRet1));
/*      */     }
/*  271 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  280 */     int traceRet1 = getIntValue(Encoding);
/*  281 */     if (Trace.isOn) {
/*  282 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getEncoding()", "getter", 
/*  283 */           Integer.valueOf(traceRet1));
/*      */     }
/*  285 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int value) {
/*  294 */     if (Trace.isOn) {
/*  295 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setEncoding(int)", "setter", 
/*  296 */           Integer.valueOf(value));
/*      */     }
/*  298 */     setIntValue(Encoding, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCodedCharSetId() {
/*  307 */     int traceRet1 = getIntValue(CodedCharSetId);
/*  308 */     if (Trace.isOn) {
/*  309 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getCodedCharSetId()", "getter", 
/*  310 */           Integer.valueOf(traceRet1));
/*      */     }
/*  312 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCodedCharSetId(int value) {
/*  321 */     if (Trace.isOn) {
/*  322 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setCodedCharSetId(int)", "setter", 
/*  323 */           Integer.valueOf(value));
/*      */     }
/*  325 */     setIntValue(CodedCharSetId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/*  334 */     String traceRet1 = getStringValue(Format);
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getFormat()", "getter", traceRet1);
/*      */     }
/*  338 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String value) {
/*  347 */     if (Trace.isOn) {
/*  348 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setFormat(String)", "setter", value);
/*      */     }
/*  350 */     setStringValue(Format, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFlags() {
/*  359 */     int traceRet1 = getIntValue(Flags);
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getFlags()", "getter", 
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
/*      */   public void setFlags(int value) {
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setFlags(int)", "setter", 
/*  375 */           Integer.valueOf(value));
/*      */     }
/*  377 */     setIntValue(Flags, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReturnCode() {
/*  386 */     int traceRet1 = getIntValue(ReturnCode);
/*  387 */     if (Trace.isOn) {
/*  388 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReturnCode()", "getter", 
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
/*      */   public void setReturnCode(int value) {
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReturnCode(int)", "setter", 
/*  402 */           Integer.valueOf(value));
/*      */     }
/*  404 */     setIntValue(ReturnCode, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompCode() {
/*  413 */     int traceRet1 = getIntValue(CompCode);
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getCompCode()", "getter", 
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
/*      */   public void setCompCode(int value) {
/*  427 */     if (Trace.isOn) {
/*  428 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setCompCode(int)", "setter", 
/*  429 */           Integer.valueOf(value));
/*      */     }
/*  431 */     setIntValue(CompCode, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReason() {
/*  440 */     int traceRet1 = getIntValue(Reason);
/*  441 */     if (Trace.isOn) {
/*  442 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReason()", "getter", 
/*  443 */           Integer.valueOf(traceRet1));
/*      */     }
/*  445 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReason(int value) {
/*  454 */     if (Trace.isOn) {
/*  455 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReason(int)", "setter", 
/*  456 */           Integer.valueOf(value));
/*      */     }
/*  458 */     setIntValue(Reason, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUOWControl() {
/*  467 */     int traceRet1 = getIntValue(UOWControl);
/*  468 */     if (Trace.isOn) {
/*  469 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getUOWControl()", "getter", 
/*  470 */           Integer.valueOf(traceRet1));
/*      */     }
/*  472 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUOWControl(int value) {
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setUOWControl(int)", "setter", 
/*  483 */           Integer.valueOf(value));
/*      */     }
/*  485 */     setIntValue(UOWControl, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getGetWaitInterval() {
/*  494 */     int traceRet1 = getIntValue(GetWaitInterval);
/*  495 */     if (Trace.isOn) {
/*  496 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getGetWaitInterval()", "getter", 
/*  497 */           Integer.valueOf(traceRet1));
/*      */     }
/*  499 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGetWaitInterval(int value) {
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setGetWaitInterval(int)", "setter", 
/*  510 */           Integer.valueOf(value));
/*      */     }
/*  512 */     setIntValue(GetWaitInterval, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLinkType() {
/*  521 */     int traceRet1 = getIntValue(LinkType);
/*  522 */     if (Trace.isOn) {
/*  523 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getLinkType()", "getter", 
/*  524 */           Integer.valueOf(traceRet1));
/*      */     }
/*  526 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLinkType(int value) {
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setLinkType(int)", "setter", 
/*  537 */           Integer.valueOf(value));
/*      */     }
/*  539 */     setIntValue(LinkType, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOutputDataLength() {
/*  548 */     int traceRet1 = getIntValue(OutputDataLength);
/*  549 */     if (Trace.isOn) {
/*  550 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getOutputDataLength()", "getter", 
/*  551 */           Integer.valueOf(traceRet1));
/*      */     }
/*  553 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOutputDataLength(int value) {
/*  562 */     if (Trace.isOn) {
/*  563 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setOutputDataLength(int)", "setter", 
/*  564 */           Integer.valueOf(value));
/*      */     }
/*  566 */     setIntValue(OutputDataLength, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFacilityKeepTime() {
/*  575 */     int traceRet1 = getIntValue(FacilityKeepTime);
/*  576 */     if (Trace.isOn) {
/*  577 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getFacilityKeepTime()", "getter", 
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
/*      */   public void setFacilityKeepTime(int value) {
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setFacilityKeepTime(int)", "setter", 
/*  591 */           Integer.valueOf(value));
/*      */     }
/*  593 */     setIntValue(FacilityKeepTime, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getADSDescriptor() {
/*  602 */     int traceRet1 = getIntValue(ADSDescriptor);
/*  603 */     if (Trace.isOn) {
/*  604 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getADSDescriptor()", "getter", 
/*  605 */           Integer.valueOf(traceRet1));
/*      */     }
/*  607 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setADSDescriptor(int value) {
/*  616 */     if (Trace.isOn) {
/*  617 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setADSDescriptor(int)", "setter", 
/*  618 */           Integer.valueOf(value));
/*      */     }
/*  620 */     setIntValue(ADSDescriptor, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConversationalTask() {
/*  629 */     int traceRet1 = getIntValue(ConversationalTask);
/*  630 */     if (Trace.isOn) {
/*  631 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getConversationalTask()", "getter", 
/*  632 */           Integer.valueOf(traceRet1));
/*      */     }
/*  634 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConversationalTask(int value) {
/*  643 */     if (Trace.isOn) {
/*  644 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setConversationalTask(int)", "setter", 
/*  645 */           Integer.valueOf(value));
/*      */     }
/*  647 */     setIntValue(ConversationalTask, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTaskEndStatus() {
/*  656 */     int traceRet1 = getIntValue(TaskEndStatus);
/*  657 */     if (Trace.isOn) {
/*  658 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getTaskEndStatus()", "getter", 
/*  659 */           Integer.valueOf(traceRet1));
/*      */     }
/*  661 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTaskEndStatus(int value) {
/*  670 */     if (Trace.isOn) {
/*  671 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setTaskEndStatus(int)", "setter", 
/*  672 */           Integer.valueOf(value));
/*      */     }
/*  674 */     setIntValue(TaskEndStatus, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getFacility() {
/*  683 */     byte[] traceRet1 = getBytesValue(Facility);
/*  684 */     if (Trace.isOn) {
/*  685 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getFacility()", "getter", traceRet1);
/*      */     }
/*  687 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFacility(byte[] value) {
/*  696 */     if (Trace.isOn) {
/*  697 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setFacility(byte [ ])", "setter", value);
/*      */     }
/*  699 */     setBytesValue(Facility, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFunction() {
/*  708 */     String traceRet1 = getStringValue(Function);
/*  709 */     if (Trace.isOn) {
/*  710 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getFunction()", "getter", traceRet1);
/*      */     }
/*  712 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFunction(String value) {
/*  721 */     if (Trace.isOn) {
/*  722 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setFunction(String)", "setter", value);
/*      */     }
/*  724 */     setStringValue(Function, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAbendCode() {
/*  733 */     String traceRet1 = getStringValue(AbendCode);
/*  734 */     if (Trace.isOn) {
/*  735 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getAbendCode()", "getter", traceRet1);
/*      */     }
/*  737 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAbendCode(String value) {
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setAbendCode(String)", "setter", value);
/*      */     }
/*  749 */     setStringValue(AbendCode, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAuthenticator() {
/*  758 */     String traceRet1 = getStringValue(Authenticator);
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getAuthenticator()", "getter", traceRet1);
/*      */     }
/*  762 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAuthenticator(String value) {
/*  771 */     if (Trace.isOn) {
/*  772 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setAuthenticator(String)", "setter", value);
/*      */     }
/*  774 */     setStringValue(Authenticator, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReserved1() {
/*  782 */     String traceRet1 = getStringValue(Reserved1);
/*  783 */     if (Trace.isOn) {
/*  784 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReserved1()", "getter", traceRet1);
/*      */     }
/*  786 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReserved1(String value) {
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReserved1(String)", "setter", value);
/*      */     }
/*  797 */     setStringValue(Reserved1, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToFormat() {
/*  806 */     String traceRet1 = getStringValue(ReplyToFormat);
/*  807 */     if (Trace.isOn) {
/*  808 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReplyToFormat()", "getter", traceRet1);
/*      */     }
/*  810 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToFormat(String value) {
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReplyToFormat(String)", "setter", value);
/*      */     }
/*  822 */     setStringValue(ReplyToFormat, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteSysId() {
/*  831 */     String traceRet1 = getStringValue(RemoteSysId);
/*  832 */     if (Trace.isOn) {
/*  833 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getRemoteSysId()", "getter", traceRet1);
/*      */     }
/*  835 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteSysId(String value) {
/*  844 */     if (Trace.isOn) {
/*  845 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setRemoteSysId(String)", "setter", value);
/*      */     }
/*  847 */     setStringValue(RemoteSysId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteTransId() {
/*  856 */     String traceRet1 = getStringValue(RemoteTransId);
/*  857 */     if (Trace.isOn) {
/*  858 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getRemoteTransId()", "getter", traceRet1);
/*      */     }
/*  860 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteTransId(String value) {
/*  869 */     if (Trace.isOn) {
/*  870 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setRemoteTransId(String)", "setter", value);
/*      */     }
/*  872 */     setStringValue(RemoteTransId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTransactionId() {
/*  881 */     String traceRet1 = getStringValue(TransactionId);
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getTransactionId()", "getter", traceRet1);
/*      */     }
/*  885 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransactionId(String value) {
/*  894 */     if (Trace.isOn) {
/*  895 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setTransactionId(String)", "setter", value);
/*      */     }
/*  897 */     setStringValue(TransactionId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFacilityLike() {
/*  906 */     String traceRet1 = getStringValue(FacilityLike);
/*  907 */     if (Trace.isOn) {
/*  908 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getFacilityLike()", "getter", traceRet1);
/*      */     }
/*  910 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFacilityLike(String value) {
/*  919 */     if (Trace.isOn) {
/*  920 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setFacilityLike(String)", "setter", value);
/*      */     }
/*  922 */     setStringValue(FacilityLike, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttentionId() {
/*  931 */     String traceRet1 = getStringValue(AttentionId);
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getAttentionId()", "getter", traceRet1);
/*      */     }
/*  935 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttentionId(String value) {
/*  944 */     if (Trace.isOn) {
/*  945 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setAttentionId(String)", "setter", value);
/*      */     }
/*  947 */     setStringValue(AttentionId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStartCode() {
/*  956 */     String traceRet1 = getStringValue(StartCode);
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getStartCode()", "getter", traceRet1);
/*      */     }
/*  960 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStartCode(String value) {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setStartCode(String)", "setter", value);
/*      */     }
/*  972 */     setStringValue(StartCode, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCancelCode() {
/*  981 */     String traceRet1 = getStringValue(CancelCode);
/*  982 */     if (Trace.isOn) {
/*  983 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getCancelCode()", "getter", traceRet1);
/*      */     }
/*  985 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCancelCode(String value) {
/*  994 */     if (Trace.isOn) {
/*  995 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setCancelCode(String)", "setter", value);
/*      */     }
/*  997 */     setStringValue(CancelCode, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNextTransactionId() {
/* 1006 */     String traceRet1 = getStringValue(NextTransactionId);
/* 1007 */     if (Trace.isOn) {
/* 1008 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getNextTransactionId()", "getter", traceRet1);
/*      */     }
/* 1010 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNextTransactionId(String value) {
/* 1019 */     if (Trace.isOn) {
/* 1020 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setNextTransactionId(String)", "setter", value);
/*      */     }
/*      */     
/* 1023 */     setStringValue(NextTransactionId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReserved2() {
/* 1031 */     String traceRet1 = getStringValue(Reserved2);
/* 1032 */     if (Trace.isOn) {
/* 1033 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReserved2()", "getter", traceRet1);
/*      */     }
/* 1035 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReserved2(String value) {
/* 1043 */     if (Trace.isOn) {
/* 1044 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReserved2(String)", "setter", value);
/*      */     }
/* 1046 */     setStringValue(Reserved2, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReserved3() {
/* 1054 */     String traceRet1 = getStringValue(Reserved3);
/* 1055 */     if (Trace.isOn) {
/* 1056 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReserved3()", "getter", traceRet1);
/*      */     }
/* 1058 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReserved3(String value) {
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReserved3(String)", "setter", value);
/*      */     }
/* 1069 */     setStringValue(Reserved3, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCursorPosition() {
/* 1078 */     int traceRet1 = getIntValue(CursorPosition);
/* 1079 */     if (Trace.isOn) {
/* 1080 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getCursorPosition()", "getter", 
/* 1081 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1083 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCursorPosition(int value) {
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setCursorPosition(int)", "setter", 
/* 1094 */           Integer.valueOf(value));
/*      */     }
/* 1096 */     setIntValue(CursorPosition, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getErrorOffset() {
/* 1105 */     int traceRet1 = getIntValue(ErrorOffset);
/* 1106 */     if (Trace.isOn) {
/* 1107 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getErrorOffset()", "getter", 
/* 1108 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1110 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setErrorOffset(int value) {
/* 1119 */     if (Trace.isOn) {
/* 1120 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setErrorOffset(int)", "setter", 
/* 1121 */           Integer.valueOf(value));
/*      */     }
/* 1123 */     setIntValue(ErrorOffset, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInputItem() {
/* 1132 */     int traceRet1 = getIntValue(InputItem);
/* 1133 */     if (Trace.isOn) {
/* 1134 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getInputItem()", "getter", 
/* 1135 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1137 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInputItem(int value) {
/* 1146 */     if (Trace.isOn) {
/* 1147 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setInputItem(int)", "setter", 
/* 1148 */           Integer.valueOf(value));
/*      */     }
/* 1150 */     setIntValue(InputItem, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReserved4() {
/* 1158 */     String traceRet1 = getStringValue(Reserved4);
/* 1159 */     if (Trace.isOn) {
/* 1160 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "getReserved4()", "getter", traceRet1);
/*      */     }
/* 1162 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReserved4(String value) {
/* 1170 */     if (Trace.isOn) {
/* 1171 */       Trace.data(this, "com.ibm.mq.headers.MQCIH", "setReserved4(String)", "setter", value);
/*      */     }
/* 1173 */     setStringValue(Reserved4, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextEncoding() {
/* 1183 */     if (Trace.isOn) {
/* 1184 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "nextEncoding()");
/*      */     }
/* 1186 */     int traceRet1 = getEncoding();
/*      */     
/* 1188 */     if (Trace.isOn) {
/* 1189 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "nextEncoding()", Integer.valueOf(traceRet1));
/*      */     }
/* 1191 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextEncoding(int value) {
/* 1199 */     if (Trace.isOn)
/* 1200 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "nextEncoding(int)", new Object[] {
/* 1201 */             Integer.valueOf(value)
/*      */           }); 
/* 1203 */     setEncoding(value);
/*      */     
/* 1205 */     if (Trace.isOn) {
/* 1206 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "nextEncoding(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextCharacterSet() {
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "nextCharacterSet()");
/*      */     }
/* 1219 */     int traceRet1 = getCodedCharSetId();
/*      */     
/* 1221 */     if (Trace.isOn) {
/* 1222 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "nextCharacterSet()", 
/* 1223 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1225 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextCharacterSet(int value) {
/* 1233 */     if (Trace.isOn)
/* 1234 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "nextCharacterSet(int)", new Object[] {
/* 1235 */             Integer.valueOf(value)
/*      */           }); 
/* 1237 */     setCodedCharSetId(value);
/*      */     
/* 1239 */     if (Trace.isOn) {
/* 1240 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "nextCharacterSet(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nextFormat() {
/* 1250 */     if (Trace.isOn) {
/* 1251 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "nextFormat()");
/*      */     }
/* 1253 */     String traceRet1 = getFormat();
/*      */     
/* 1255 */     if (Trace.isOn) {
/* 1256 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "nextFormat()", traceRet1);
/*      */     }
/* 1258 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextFormat(String value) {
/* 1266 */     if (Trace.isOn) {
/* 1267 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "nextFormat(String)", new Object[] { value });
/*      */     }
/* 1269 */     setFormat(value);
/*      */     
/* 1271 */     if (Trace.isOn) {
/* 1272 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "nextFormat(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format() {
/* 1282 */     if (Trace.isOn) {
/* 1283 */       Trace.entry(this, "com.ibm.mq.headers.MQCIH", "format()");
/*      */     }
/* 1285 */     if (Trace.isOn) {
/* 1286 */       Trace.exit(this, "com.ibm.mq.headers.MQCIH", "format()", "MQCICS  ");
/*      */     }
/* 1288 */     return "MQCICS  ";
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQCIH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */