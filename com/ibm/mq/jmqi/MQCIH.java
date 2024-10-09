/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   extends AbstractMqHeaderStructure
/*      */ {
/*      */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCIH.java";
/*      */   private static final int SIZEOF_RETURNCODE = 4;
/*      */   private static final int SIZEOF_COMPCODE = 4;
/*      */   private static final int SIZEOF_REASON = 4;
/*      */   private static final int SIZEOF_UOWCONTROL = 4;
/*      */   private static final int SIZEOF_GETWAITINTERVAL = 4;
/*      */   private static final int SIZEOF_LINKTYPE = 4;
/*      */   private static final int SIZEOF_OUTPUTDATALENGTH = 4;
/*      */   private static final int SIZEOF_FACILITYKEEPTIME = 4;
/*      */   private static final int SIZEOF_ADSDESCRIPTOR = 4;
/*      */   private static final int SIZEOF_CONVERSATIONALTASK = 4;
/*      */   private static final int SIZEOF_TASKENDSTATUS = 4;
/*      */   private static final int SIZEOF_FACILITY = 8;
/*      */   private static final int SIZEOF_FUNCTION = 4;
/*      */   private static final int SIZEOF_ABENDCODE = 4;
/*      */   private static final int SIZEOF_AUTHENTICATOR = 8;
/*      */   private static final int SIZEOF_RESERVED1 = 4;
/*      */   private static final int SIZEOF_REPLYTOFORMAT = 8;
/*      */   private static final int SIZEOF_REMOTESYSID = 4;
/*      */   private static final int SIZEOF_REMOTETRANSID = 4;
/*      */   private static final int SIZEOF_TRANSACTIONID = 4;
/*      */   private static final int SIZEOF_FACILITYLIKE = 4;
/*      */   private static final int SIZEOF_ATTENTIONID = 4;
/*      */   private static final int SIZEOF_STARTCODE = 4;
/*      */   private static final int SIZEOF_CANCELCODE = 4;
/*      */   private static final int SIZEOF_NEXTTRANSACTIONID = 4;
/*      */   private static final int SIZEOF_RESERVED2 = 8;
/*      */   private static final int SIZEOF_RESERVED3 = 8;
/*      */   private static final int SIZEOF_CURSORPOSITION = 4;
/*      */   private static final int SIZEOF_ERROROFFSET = 4;
/*      */   private static final int SIZEOF_INPUTITEM = 4;
/*      */   private static final int SIZEOF_RESERVED4 = 4;
/*      */   private static final String BLANK4 = "    ";
/*      */   private static final String BLANK8 = "        ";
/*      */   
/*      */   static {
/*   85 */     if (Trace.isOn) {
/*   86 */       Trace.data("com.ibm.mq.jmqi.MQCIH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCIH.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   private int returnCode = 0;
/*  130 */   private int compCode = 0;
/*  131 */   private int reason = 0;
/*  132 */   private int uowControl = 273;
/*  133 */   private int getWaitInterval = -2;
/*  134 */   private int linkType = 1;
/*  135 */   private int outputDataLength = -1;
/*      */   private int facilityKeepTime;
/*  137 */   private int adsDescriptor = 0;
/*  138 */   private int conversationalTask = 0;
/*  139 */   private int taskEndStatus = 0;
/*  140 */   private byte[] facility = CMQC.MQCFAC_NONE;
/*  141 */   private String function = "    ";
/*  142 */   private String abendCode = "    ";
/*  143 */   private String authenticator = "        ";
/*  144 */   private String replyToFormat = "        ";
/*  145 */   private String remoteSysId = "    ";
/*  146 */   private String remoteTransId = "    ";
/*  147 */   private String transactionId = "    ";
/*  148 */   private String facilityLike = "    ";
/*  149 */   private String attentionId = "    ";
/*  150 */   private String startCode = "    ";
/*  151 */   private String cancelCode = "    ";
/*  152 */   private String nextTransactionId = "    ";
/*      */ 
/*      */   
/*      */   private int cursorPosition;
/*      */   
/*      */   private int errorOffset;
/*      */   
/*      */   private int inputItem;
/*      */ 
/*      */   
/*      */   protected MQCIH(JmqiEnvironment env) {
/*  163 */     super(env);
/*  164 */     if (Trace.isOn) {
/*  165 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*  167 */     MQHeader mqHeader = env.newMQHeader();
/*  168 */     mqHeader.setStrucId("CIH ");
/*  169 */     mqHeader.setVersion(1);
/*  170 */     mqHeader.setStrucLength(164);
/*  171 */     setMqHeader(mqHeader);
/*      */     
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQCIH(JmqiEnvironment env, MQHeader mqHeader) {
/*  185 */     super(env, mqHeader);
/*  186 */     if (Trace.isOn) {
/*  187 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "<init>(JmqiEnvironment,MQHeader)", new Object[] { env, mqHeader });
/*      */     }
/*      */     
/*  190 */     if (Trace.isOn) {
/*  191 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "<init>(JmqiEnvironment,MQHeader)");
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
/*      */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/*  204 */     if (Trace.isOn) {
/*  205 */       Trace.entry("com.ibm.mq.jmqi.MQCIH", "getSizeV1(JmqiEnvironment,int)", new Object[] { env, 
/*  206 */             Integer.valueOf(ptrSize) });
/*      */     }
/*  208 */     int size = MQHeader.getSize(env, ptrSize);
/*  209 */     size += 128;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  214 */     if (Trace.isOn) {
/*  215 */       Trace.exit("com.ibm.mq.jmqi.MQCIH", "getSizeV1(JmqiEnvironment,int)", 
/*  216 */           Integer.valueOf(size));
/*      */     }
/*  218 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV2(JmqiEnvironment env, int ptrSize) {
/*  229 */     if (Trace.isOn) {
/*  230 */       Trace.entry("com.ibm.mq.jmqi.MQCIH", "getSizeV2(JmqiEnvironment,int)", new Object[] { env, 
/*  231 */             Integer.valueOf(ptrSize) });
/*      */     }
/*  233 */     int size = getSizeV1(env, ptrSize);
/*  234 */     size += 16;
/*      */     
/*  236 */     if (Trace.isOn) {
/*  237 */       Trace.exit("com.ibm.mq.jmqi.MQCIH", "getSizeV2(JmqiEnvironment,int)", Integer.valueOf(size));
/*      */     }
/*      */     
/*  240 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSize(JmqiEnvironment env, int version, int ptrsize) throws JmqiException {
/*      */     int size;
/*      */     JmqiException e;
/*  253 */     if (Trace.isOn) {
/*  254 */       Trace.entry("com.ibm.mq.jmqi.MQCIH", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/*  255 */             Integer.valueOf(version), Integer.valueOf(ptrsize) });
/*      */     }
/*      */     
/*  258 */     switch (version) {
/*      */       case 1:
/*  260 */         size = getSizeV1(env, ptrsize);
/*      */         break;
/*      */       case 2:
/*  263 */         size = getSizeV2(env, ptrsize);
/*      */         break;
/*      */       default:
/*  266 */         e = new JmqiException(env, -1, null, 2, 2142, null);
/*      */         
/*  268 */         if (Trace.isOn) {
/*  269 */           Trace.throwing("com.ibm.mq.jmqi.MQCIH", "getSize(JmqiEnvironment,int,int)", e);
/*      */         }
/*  271 */         throw e;
/*      */     } 
/*      */     
/*  274 */     if (Trace.isOn) {
/*  275 */       Trace.exit("com.ibm.mq.jmqi.MQCIH", "getSize(JmqiEnvironment,int,int)", 
/*  276 */           Integer.valueOf(size));
/*      */     }
/*  278 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  286 */     if (Trace.isOn)
/*  287 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/*  288 */             Integer.valueOf(ptrSize), cp
/*      */           }); 
/*  290 */     int size = getSize(this.env, getMqHeader().getVersion(), ptrSize);
/*      */     
/*  292 */     if (Trace.isOn) {
/*  293 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "getRequiredBufferSize(int,JmqiCodepage)", 
/*  294 */           Integer.valueOf(size));
/*      */     }
/*  296 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReturnCode() {
/*  303 */     if (Trace.isOn) {
/*  304 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getReturnCode()", "getter", 
/*  305 */           Integer.valueOf(this.returnCode));
/*      */     }
/*  307 */     return this.returnCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReturnCode(int returnCode) {
/*  314 */     if (Trace.isOn)
/*  315 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "setReturnCode(int)", new Object[] {
/*  316 */             Integer.valueOf(returnCode)
/*      */           }); 
/*  318 */     this.returnCode = returnCode;
/*      */     
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "setReturnCode(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompCode() {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getCompCode()", "getter", 
/*  332 */           Integer.valueOf(this.compCode));
/*      */     }
/*  334 */     return this.compCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCompCode(int compCode) {
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setCompCode(int)", "setter", 
/*  343 */           Integer.valueOf(compCode));
/*      */     }
/*  345 */     this.compCode = compCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReason() {
/*  352 */     if (Trace.isOn) {
/*  353 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getReason()", "getter", Integer.valueOf(this.reason));
/*      */     }
/*  355 */     return this.reason;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReason(int reason) {
/*  362 */     if (Trace.isOn) {
/*  363 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setReason(int)", "setter", 
/*  364 */           Integer.valueOf(reason));
/*      */     }
/*  366 */     this.reason = reason;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUowControl() {
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getUowControl()", "getter", 
/*  375 */           Integer.valueOf(this.uowControl));
/*      */     }
/*  377 */     return this.uowControl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUowControl(int uowControl) {
/*  384 */     if (Trace.isOn) {
/*  385 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setUowControl(int)", "setter", 
/*  386 */           Integer.valueOf(uowControl));
/*      */     }
/*  388 */     this.uowControl = uowControl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getGetWaitInterval() {
/*  395 */     if (Trace.isOn) {
/*  396 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getGetWaitInterval()", "getter", 
/*  397 */           Integer.valueOf(this.getWaitInterval));
/*      */     }
/*  399 */     return this.getWaitInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGetWaitInterval(int getWaitInterval) {
/*  406 */     if (Trace.isOn) {
/*  407 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setGetWaitInterval(int)", "setter", 
/*  408 */           Integer.valueOf(getWaitInterval));
/*      */     }
/*  410 */     this.getWaitInterval = getWaitInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLinkType() {
/*  417 */     if (Trace.isOn) {
/*  418 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getLinkType()", "getter", 
/*  419 */           Integer.valueOf(this.linkType));
/*      */     }
/*  421 */     return this.linkType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLinkType(int linkType) {
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setLinkType(int)", "setter", 
/*  430 */           Integer.valueOf(linkType));
/*      */     }
/*  432 */     this.linkType = linkType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOutputDataLength() {
/*  439 */     if (Trace.isOn) {
/*  440 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getOutputDataLength()", "getter", 
/*  441 */           Integer.valueOf(this.outputDataLength));
/*      */     }
/*  443 */     return this.outputDataLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOutputDataLength(int outputDataLength) {
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setOutputDataLength(int)", "setter", 
/*  452 */           Integer.valueOf(outputDataLength));
/*      */     }
/*  454 */     this.outputDataLength = outputDataLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFacilityKeepTime() {
/*  461 */     if (Trace.isOn) {
/*  462 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getFacilityKeepTime()", "getter", 
/*  463 */           Integer.valueOf(this.facilityKeepTime));
/*      */     }
/*  465 */     return this.facilityKeepTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFacilityKeepTime(int facilityKeepTime) {
/*  472 */     if (Trace.isOn) {
/*  473 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setFacilityKeepTime(int)", "setter", 
/*  474 */           Integer.valueOf(facilityKeepTime));
/*      */     }
/*  476 */     this.facilityKeepTime = facilityKeepTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAdsDescriptor() {
/*  483 */     if (Trace.isOn) {
/*  484 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getAdsDescriptor()", "getter", 
/*  485 */           Integer.valueOf(this.adsDescriptor));
/*      */     }
/*  487 */     return this.adsDescriptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAdsDescriptor(int adsDescriptor) {
/*  494 */     if (Trace.isOn) {
/*  495 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setAdsDescriptor(int)", "setter", 
/*  496 */           Integer.valueOf(adsDescriptor));
/*      */     }
/*  498 */     this.adsDescriptor = adsDescriptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConversationalTask() {
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getConversationalTask()", "getter", 
/*  507 */           Integer.valueOf(this.conversationalTask));
/*      */     }
/*  509 */     return this.conversationalTask;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConversationalTask(int conversationalTask) {
/*  516 */     if (Trace.isOn) {
/*  517 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setConversationalTask(int)", "setter", 
/*  518 */           Integer.valueOf(conversationalTask));
/*      */     }
/*  520 */     this.conversationalTask = conversationalTask;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTaskEndStatus() {
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getTaskEndStatus()", "getter", 
/*  529 */           Integer.valueOf(this.taskEndStatus));
/*      */     }
/*  531 */     return this.taskEndStatus;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTaskEndStatus(int taskEndStatus) {
/*  538 */     if (Trace.isOn) {
/*  539 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setTaskEndStatus(int)", "setter", 
/*  540 */           Integer.valueOf(taskEndStatus));
/*      */     }
/*  542 */     this.taskEndStatus = taskEndStatus;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getFacility() {
/*  549 */     if (Trace.isOn) {
/*  550 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getFacility()", "getter", this.facility);
/*      */     }
/*  552 */     return this.facility;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFacility(byte[] facility) {
/*  559 */     if (Trace.isOn) {
/*  560 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setFacility(byte [ ])", "setter", facility);
/*      */     }
/*  562 */     this.facility = facility;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFunction() {
/*  569 */     if (Trace.isOn) {
/*  570 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getFunction()", "getter", this.function);
/*      */     }
/*  572 */     return this.function;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFunction(String function) {
/*  579 */     if (Trace.isOn) {
/*  580 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setFunction(String)", "setter", function);
/*      */     }
/*  582 */     this.function = function;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAbendCode() {
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getAbendCode()", "getter", this.abendCode);
/*      */     }
/*  592 */     return this.abendCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAbendCode(String abendCode) {
/*  599 */     if (Trace.isOn) {
/*  600 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setAbendCode(String)", "setter", abendCode);
/*      */     }
/*  602 */     this.abendCode = abendCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAuthenticator() {
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getAuthenticator()", "getter", this.authenticator);
/*      */     }
/*  612 */     return this.authenticator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAuthenticator(String authenticator) {
/*  619 */     if (Trace.isOn) {
/*  620 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setAuthenticator(String)", "setter", authenticator);
/*      */     }
/*      */     
/*  623 */     this.authenticator = authenticator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyToFormat() {
/*  630 */     if (Trace.isOn) {
/*  631 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getReplyToFormat()", "getter", this.replyToFormat);
/*      */     }
/*  633 */     return this.replyToFormat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyToFormat(String replyToFormat) {
/*  640 */     if (Trace.isOn) {
/*  641 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setReplyToFormat(String)", "setter", replyToFormat);
/*      */     }
/*      */     
/*  644 */     this.replyToFormat = replyToFormat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteSysId() {
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getRemoteSysId()", "getter", this.remoteSysId);
/*      */     }
/*  654 */     return this.remoteSysId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteSysId(String remoteSysId) {
/*  661 */     if (Trace.isOn) {
/*  662 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setRemoteSysId(String)", "setter", remoteSysId);
/*      */     }
/*  664 */     this.remoteSysId = remoteSysId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteTransId() {
/*  671 */     if (Trace.isOn) {
/*  672 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getRemoteTransId()", "getter", this.remoteTransId);
/*      */     }
/*  674 */     return this.remoteTransId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteTransId(String remoteTransId) {
/*  681 */     if (Trace.isOn) {
/*  682 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setRemoteTransId(String)", "setter", remoteTransId);
/*      */     }
/*      */     
/*  685 */     this.remoteTransId = remoteTransId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTransactionId() {
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getTransactionId()", "getter", this.transactionId);
/*      */     }
/*  695 */     return this.transactionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransactionId(String transactionId) {
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setTransactionId(String)", "setter", transactionId);
/*      */     }
/*      */     
/*  706 */     this.transactionId = transactionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFacilityLike() {
/*  713 */     if (Trace.isOn) {
/*  714 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getFacilityLike()", "getter", this.facilityLike);
/*      */     }
/*  716 */     return this.facilityLike;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFacilityLike(String facilityLike) {
/*  723 */     if (Trace.isOn) {
/*  724 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setFacilityLike(String)", "setter", facilityLike);
/*      */     }
/*      */     
/*  727 */     this.facilityLike = facilityLike;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttentionId() {
/*  734 */     if (Trace.isOn) {
/*  735 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getAttentionId()", "getter", this.attentionId);
/*      */     }
/*  737 */     return this.attentionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttentionId(String attentionId) {
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setAttentionId(String)", "setter", attentionId);
/*      */     }
/*  747 */     this.attentionId = attentionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStartCode() {
/*  754 */     if (Trace.isOn) {
/*  755 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getStartCode()", "getter", this.startCode);
/*      */     }
/*  757 */     return this.startCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStartCode(String startCode) {
/*  764 */     if (Trace.isOn) {
/*  765 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setStartCode(String)", "setter", startCode);
/*      */     }
/*  767 */     this.startCode = startCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCancelCode() {
/*  774 */     if (Trace.isOn) {
/*  775 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getCancelCode()", "getter", this.cancelCode);
/*      */     }
/*  777 */     return this.cancelCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCancelCode(String cancelCode) {
/*  784 */     if (Trace.isOn) {
/*  785 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setCancelCode(String)", "setter", cancelCode);
/*      */     }
/*  787 */     this.cancelCode = cancelCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNextTransactionId() {
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getNextTransactionId()", "getter", this.nextTransactionId);
/*      */     }
/*      */     
/*  798 */     return this.nextTransactionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNextTransactionId(String nextTransactionId) {
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setNextTransactionId(String)", "setter", nextTransactionId);
/*      */     }
/*      */     
/*  809 */     this.nextTransactionId = nextTransactionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCursorPosition() {
/*  816 */     if (Trace.isOn) {
/*  817 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getCursorPosition()", "getter", 
/*  818 */           Integer.valueOf(this.cursorPosition));
/*      */     }
/*  820 */     return this.cursorPosition;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCursorPosition(int cursorPosition) {
/*  827 */     if (Trace.isOn) {
/*  828 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setCursorPosition(int)", "setter", 
/*  829 */           Integer.valueOf(cursorPosition));
/*      */     }
/*  831 */     this.cursorPosition = cursorPosition;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getErrorOffset() {
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getErrorOffset()", "getter", 
/*  840 */           Integer.valueOf(this.errorOffset));
/*      */     }
/*  842 */     return this.errorOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setErrorOffset(int errorOffset) {
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setErrorOffset(int)", "setter", 
/*  851 */           Integer.valueOf(errorOffset));
/*      */     }
/*  853 */     this.errorOffset = errorOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInputItem() {
/*  860 */     if (Trace.isOn) {
/*  861 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "getInputItem()", "getter", 
/*  862 */           Integer.valueOf(this.inputItem));
/*      */     }
/*  864 */     return this.inputItem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInputItem(int inputItem) {
/*  871 */     if (Trace.isOn) {
/*  872 */       Trace.data(this, "com.ibm.mq.jmqi.MQCIH", "setInputItem(int)", "setter", 
/*  873 */           Integer.valueOf(inputItem));
/*      */     }
/*  875 */     this.inputItem = inputItem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  883 */     if (Trace.isOn) {
/*  884 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  886 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*  888 */     int pos = offset;
/*  889 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/*  891 */     MQHeader mqHeader = getMqHeader();
/*  892 */     int version = mqHeader.getVersion();
/*  893 */     int structLength = getSize(this.env, version, ptrSize);
/*  894 */     mqHeader.setStrucLength(structLength);
/*  895 */     pos += mqHeader.writeToBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*      */     
/*  897 */     dc.writeI32(this.returnCode, buffer, pos, swap);
/*  898 */     pos += 4;
/*      */     
/*  900 */     dc.writeI32(this.compCode, buffer, pos, swap);
/*  901 */     pos += 4;
/*      */     
/*  903 */     dc.writeI32(this.reason, buffer, pos, swap);
/*  904 */     pos += 4;
/*      */     
/*  906 */     dc.writeI32(this.uowControl, buffer, pos, swap);
/*  907 */     pos += 4;
/*      */     
/*  909 */     dc.writeI32(this.getWaitInterval, buffer, pos, swap);
/*  910 */     pos += 4;
/*      */     
/*  912 */     dc.writeI32(this.linkType, buffer, pos, swap);
/*  913 */     pos += 4;
/*      */     
/*  915 */     dc.writeI32(this.outputDataLength, buffer, pos, swap);
/*  916 */     pos += 4;
/*      */     
/*  918 */     dc.writeI32(this.facilityKeepTime, buffer, pos, swap);
/*  919 */     pos += 4;
/*      */     
/*  921 */     dc.writeI32(this.adsDescriptor, buffer, pos, swap);
/*  922 */     pos += 4;
/*      */     
/*  924 */     dc.writeI32(this.conversationalTask, buffer, pos, swap);
/*  925 */     pos += 4;
/*      */     
/*  927 */     dc.writeI32(this.taskEndStatus, buffer, pos, swap);
/*  928 */     pos += 4;
/*      */     
/*  930 */     System.arraycopy(this.facility, 0, buffer, pos, 8);
/*  931 */     pos += 8;
/*      */     
/*  933 */     dc.writeMQField(this.function, buffer, pos, 4, cp, tls);
/*  934 */     pos += 4;
/*      */     
/*  936 */     dc.writeMQField(this.abendCode, buffer, pos, 4, cp, tls);
/*  937 */     pos += 4;
/*      */     
/*  939 */     dc.writeMQField(this.authenticator, buffer, pos, 4, cp, tls);
/*  940 */     pos += 8;
/*      */     
/*  942 */     dc.writeField(null, buffer, pos, 4, cp, tls);
/*  943 */     pos += 4;
/*      */     
/*  945 */     dc.writeMQField(this.replyToFormat, buffer, pos, 4, cp, tls);
/*  946 */     pos += 8;
/*      */     
/*  948 */     dc.writeMQField(this.remoteSysId, buffer, pos, 4, cp, tls);
/*  949 */     pos += 8;
/*      */     
/*  951 */     dc.writeMQField(this.remoteTransId, buffer, pos, 4, cp, tls);
/*  952 */     pos += 4;
/*      */     
/*  954 */     dc.writeMQField(this.transactionId, buffer, pos, 4, cp, tls);
/*  955 */     pos += 4;
/*      */     
/*  957 */     dc.writeMQField(this.facilityLike, buffer, pos, 4, cp, tls);
/*  958 */     pos += 4;
/*      */     
/*  960 */     dc.writeMQField(this.attentionId, buffer, pos, 4, cp, tls);
/*  961 */     pos += 4;
/*      */     
/*  963 */     dc.writeMQField(this.startCode, buffer, pos, 4, cp, tls);
/*  964 */     pos += 4;
/*      */     
/*  966 */     dc.writeMQField(this.cancelCode, buffer, pos, 4, cp, tls);
/*  967 */     pos += 4;
/*      */     
/*  969 */     dc.writeMQField(this.nextTransactionId, buffer, pos, 4, cp, tls);
/*  970 */     pos += 4;
/*      */     
/*  972 */     dc.writeField(null, buffer, pos, 8, cp, tls);
/*  973 */     pos += 8;
/*      */     
/*  975 */     dc.writeField(null, buffer, pos, 8, cp, tls);
/*  976 */     pos += 8;
/*      */     
/*  978 */     if (mqHeader.getVersion() >= 2) {
/*      */       
/*  980 */       dc.writeI32(this.cursorPosition, buffer, pos, swap);
/*  981 */       pos += 4;
/*      */       
/*  983 */       dc.writeI32(this.errorOffset, buffer, pos, swap);
/*  984 */       pos += 4;
/*      */       
/*  986 */       dc.writeI32(this.inputItem, buffer, pos, swap);
/*  987 */       pos += 4;
/*      */       
/*  989 */       dc.clear(buffer, pos, 4);
/*  990 */       pos += 4;
/*      */     } 
/*      */     
/*  993 */     if (Trace.isOn) {
/*  994 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/*  995 */           Integer.valueOf(pos));
/*      */     }
/*  997 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1005 */     if (Trace.isOn) {
/* 1006 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1008 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*      */     
/* 1011 */     MQHeader mqHeader = getMqHeader();
/* 1012 */     int pos = mqHeader.readFromBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*      */     
/* 1014 */     String strucId = mqHeader.getStrucId();
/* 1015 */     if (!strucId.equals("CIH ")) {
/* 1016 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2142, null);
/*      */       
/* 1018 */       if (Trace.isOn) {
/* 1019 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*      */       }
/*      */       
/* 1022 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1026 */     pos += readBodyFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */     
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1030 */           Integer.valueOf(pos));
/*      */     }
/* 1032 */     return pos;
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
/*      */   public int readBodyFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1048 */     if (Trace.isOn) {
/* 1049 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1051 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 1053 */     int pos = offset;
/* 1054 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 1055 */     MQHeader mqHeader = getMqHeader();
/*      */     
/* 1057 */     this.returnCode = dc.readI32(buffer, pos, swap);
/* 1058 */     pos += 4;
/*      */     
/* 1060 */     this.compCode = dc.readI32(buffer, pos, swap);
/* 1061 */     pos += 4;
/*      */     
/* 1063 */     this.reason = dc.readI32(buffer, pos, swap);
/* 1064 */     pos += 4;
/*      */     
/* 1066 */     this.uowControl = dc.readI32(buffer, pos, swap);
/* 1067 */     pos += 4;
/*      */     
/* 1069 */     this.getWaitInterval = dc.readI32(buffer, pos, swap);
/* 1070 */     pos += 4;
/*      */     
/* 1072 */     this.linkType = dc.readI32(buffer, pos, swap);
/* 1073 */     pos += 4;
/*      */     
/* 1075 */     this.outputDataLength = dc.readI32(buffer, pos, swap);
/* 1076 */     pos += 4;
/*      */     
/* 1078 */     this.facilityKeepTime = dc.readI32(buffer, pos, swap);
/* 1079 */     pos += 4;
/*      */     
/* 1081 */     this.adsDescriptor = dc.readI32(buffer, pos, swap);
/* 1082 */     pos += 4;
/*      */     
/* 1084 */     this.conversationalTask = dc.readI32(buffer, pos, swap);
/* 1085 */     pos += 4;
/*      */     
/* 1087 */     this.taskEndStatus = dc.readI32(buffer, pos, swap);
/* 1088 */     pos += 4;
/*      */     
/* 1090 */     System.arraycopy(buffer, pos, this.facility, 0, 8);
/* 1091 */     pos += 8;
/*      */     
/* 1093 */     this.function = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1094 */     pos += 4;
/*      */     
/* 1096 */     this.abendCode = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1097 */     pos += 4;
/*      */     
/* 1099 */     this.authenticator = dc.readMQField(buffer, pos, 8, cp, tls);
/* 1100 */     pos += 8;
/*      */     
/* 1102 */     pos += 4;
/*      */     
/* 1104 */     this.replyToFormat = dc.readMQField(buffer, pos, 8, cp, tls);
/* 1105 */     pos += 8;
/*      */     
/* 1107 */     this.remoteSysId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1108 */     pos += 4;
/*      */     
/* 1110 */     this.remoteTransId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1111 */     pos += 4;
/*      */     
/* 1113 */     this.transactionId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1114 */     pos += 4;
/*      */     
/* 1116 */     this.facilityLike = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1117 */     pos += 4;
/*      */     
/* 1119 */     this.attentionId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1120 */     pos += 4;
/*      */     
/* 1122 */     this.startCode = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1123 */     pos += 4;
/*      */     
/* 1125 */     this.cancelCode = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1126 */     pos += 4;
/*      */     
/* 1128 */     this.nextTransactionId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1129 */     pos += 4;
/*      */     
/* 1131 */     pos += 8;
/*      */     
/* 1133 */     pos += 8;
/*      */     
/* 1135 */     if (mqHeader.getVersion() >= 2) {
/*      */       
/* 1137 */       this.cursorPosition = dc.readI32(buffer, pos, swap);
/* 1138 */       pos += 4;
/*      */       
/* 1140 */       this.errorOffset = dc.readI32(buffer, pos, swap);
/* 1141 */       pos += 4;
/*      */       
/* 1143 */       this.inputItem = dc.readI32(buffer, pos, swap);
/* 1144 */       pos += 4;
/*      */       
/* 1146 */       pos += 4;
/*      */     } 
/* 1148 */     if (pos - offset + MQHeader.getSize(this.env, ptrSize) != mqHeader.getStrucLength()) {
/*      */       
/* 1150 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2142, null);
/*      */       
/* 1152 */       if (Trace.isOn) {
/* 1153 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*      */       }
/*      */       
/* 1156 */       throw e;
/*      */     } 
/*      */     
/* 1159 */     if (Trace.isOn) {
/* 1160 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1161 */           Integer.valueOf(pos));
/*      */     }
/* 1163 */     return pos;
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
/* 1175 */     getMqHeader().addFieldsToFormatter(fmt);
/* 1176 */     fmt.add("returnCode", this.returnCode);
/* 1177 */     fmt.add("compCode", this.compCode);
/* 1178 */     fmt.add("reason", this.reason);
/* 1179 */     fmt.add("uowControl", this.uowControl);
/* 1180 */     fmt.add("getWaitInterval", this.getWaitInterval);
/* 1181 */     fmt.add("linkType", this.linkType);
/* 1182 */     fmt.add("outputDataLength", this.outputDataLength);
/* 1183 */     fmt.add("facilityKeepTime", this.facilityKeepTime);
/* 1184 */     fmt.add("adsDescriptor", this.adsDescriptor);
/* 1185 */     fmt.add("conversationalTask", this.conversationalTask);
/* 1186 */     fmt.add("taskEndStatus", this.taskEndStatus);
/* 1187 */     fmt.add("facility", this.facility);
/* 1188 */     fmt.add("function", this.function);
/* 1189 */     fmt.add("abendCode", this.abendCode);
/* 1190 */     fmt.add("authenticator", this.authenticator);
/* 1191 */     fmt.add("replyToFormat", this.replyToFormat);
/* 1192 */     fmt.add("remoteSysId", this.remoteSysId);
/* 1193 */     fmt.add("remoteTransId", this.remoteTransId);
/* 1194 */     fmt.add("transactionId", this.transactionId);
/* 1195 */     fmt.add("facilityLike", this.facilityLike);
/* 1196 */     fmt.add("attentionId", this.attentionId);
/* 1197 */     fmt.add("startCode", this.startCode);
/* 1198 */     fmt.add("cancelCode", this.cancelCode);
/* 1199 */     fmt.add("nextTransactionId", this.nextTransactionId);
/* 1200 */     fmt.add("cursorPosition", this.cursorPosition);
/* 1201 */     fmt.add("errorOffset", this.errorOffset);
/* 1202 */     fmt.add("inputItem", this.inputItem);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCIH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */