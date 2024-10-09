/*      */ package com.ibm.mq.exits;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
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
/*      */ public class MQCXP
/*      */   extends AbstractMqiStructure
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/MQCXP.java";
/*      */   private static final int SIZEOF_STRUC_ID = 4;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_EXIT_ID = 4;
/*      */   private static final int SIZEOF_EXIT_REASON = 4;
/*      */   private static final int SIZEOF_EXIT_RESPONSE = 4;
/*      */   private static final int SIZEOF_EXIT_RESPONSE2 = 4;
/*      */   private static final int SIZEOF_FEEDBACK = 4;
/*      */   private static final int SIZEOF_MAX_SEGMENT_LENGTH = 4;
/*      */   private static final int SIZEOF_EXIT_USER_AREA = 16;
/*      */   private static final int SIZEOF_EXIT_DATA = 32;
/*      */   private static final int SIZEOF_MSG_RETRY_COUNT = 4;
/*      */   private static final int SIZEOF_MSG_RETRY_INTERVAL = 4;
/*      */   private static final int SIZEOF_MSG_RETRY_REASON = 4;
/*      */   private static final int SIZEOF_HEADER_LENGTH = 4;
/*      */   private static final int SIZEOF_PARTNER_NAME = 48;
/*      */   private static final int SIZEOF_FAP_LEVEL = 4;
/*      */   private static final int SIZEOF_CAPABILITY_FLAGS = 4;
/*      */   private static final int SIZEOF_EXIT_NUMBER = 4;
/*      */   private static final int SIZEOF_EXIT_SPACE = 4;
/*      */   private static final int SIZEOF_SSL_CERT_USERID = 12;
/*      */   private static final int SIZEOF_SSL_REM_CERT_ISS_NAME_LENGTH = 4;
/*      */   private static final int SIZEOF_CUR_HDR_COMPRESSION = 4;
/*      */   private static final int SIZEOF_CUR_MSG_COMPRESSION = 4;
/*      */   private static final int SIZEOF_HCONN = 4;
/*      */   private static final int SIZEOF_SHARING_CONVERSATIONS = 4;
/*      */   private static final int SIZEOF_USER_SOURCE = 4;
/*      */   private static final int SIZEOF_REMOTE_PRODUCT = 4;
/*      */   private static final int SIZEOF_REMOTE_VERSION = 8;
/*      */   
/*      */   static {
/*   82 */     if (Trace.isOn) {
/*   83 */       Trace.data("com.ibm.mq.exits.MQCXP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/MQCXP.java");
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
/*      */ 
/*      */ 
/*      */   
/*  129 */   private int version = 7;
/*  130 */   private int exitId = 0;
/*  131 */   private int exitReason = 0;
/*  132 */   private int exitResponse = 0;
/*  133 */   private int exitResponse2 = 0;
/*  134 */   private int feedback = 0;
/*  135 */   private int maxSegmentLength = 0;
/*  136 */   private byte[] exitUserArea = new byte[16];
/*  137 */   private String exitData = null;
/*  138 */   private int msgRetryCount = 0;
/*  139 */   private int msgRetryInterval = 0;
/*  140 */   private int msgRetryReason = 0;
/*  141 */   private int headerLength = 0;
/*  142 */   private String partnerName = null;
/*  143 */   private int fapLevel = 0;
/*  144 */   private int capabilityFlags = 0;
/*  145 */   private int exitNumber = 1;
/*  146 */   private int exitSpace = 0;
/*  147 */   private String sslCertUserid = null;
/*  148 */   private String sslRemCertIssName = null;
/*  149 */   private MQCSP securityParms = null;
/*  150 */   private int curHdrCompression = 0;
/*  151 */   private int curMsgCompression = 0;
/*  152 */   private int hconn = -1;
/*      */   private boolean sharingConversations = false;
/*  154 */   private int userSource = 0;
/*  155 */   private String remoteProduct = null;
/*  156 */   private String remoteVersion = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCSP createMQCSP() {
/*  165 */     if (Trace.isOn) {
/*  166 */       Trace.entry(this, "com.ibm.mq.exits.MQCXP", "createMQCSP()");
/*      */     }
/*  168 */     MQCSP traceRet1 = this.env.newMQCSP();
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.exit(this, "com.ibm.mq.exits.MQCXP", "createMQCSP()", traceRet1);
/*      */     }
/*  172 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstVersion() {
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getFirstVersion()", "getter", 
/*  182 */           Integer.valueOf(3));
/*      */     }
/*  184 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentVersion() {
/*  192 */     if (Trace.isOn) {
/*  193 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getCurrentVersion()", "getter", 
/*  194 */           Integer.valueOf(9));
/*      */     }
/*  196 */     return 9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV3(JmqiEnvironment env, int ptrSize) {
/*  207 */     int size = 0;
/*  208 */     size += 4;
/*  209 */     size += 4;
/*  210 */     size += 4;
/*  211 */     size += 4;
/*  212 */     size += 4;
/*  213 */     size += 4;
/*  214 */     size += 4;
/*  215 */     size += 4;
/*  216 */     size += 16;
/*  217 */     size += 32;
/*  218 */     size += 4;
/*  219 */     size += 4;
/*  220 */     size += 4;
/*  221 */     size += 4;
/*  222 */     size += 48;
/*  223 */     size += 4;
/*  224 */     size += 4;
/*  225 */     size += 4;
/*      */     
/*  227 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV3(JmqiEnvironment env, int ptrSize) {
/*  238 */     int size = getFieldSizeV3(env, ptrSize);
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
/*      */   private static int getFieldSizeV4(JmqiEnvironment env, int ptrSize) {
/*  251 */     int size = getFieldSizeV3(env, ptrSize);
/*      */     
/*  253 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV4(JmqiEnvironment env, int ptrSize) {
/*  264 */     int size = getFieldSizeV4(env, ptrSize);
/*      */     
/*  266 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV5(JmqiEnvironment env, int ptrSize) {
/*  277 */     int size = getFieldSizeV4(env, ptrSize);
/*  278 */     size += 4;
/*      */     
/*  280 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV5(JmqiEnvironment env, int ptrSize) {
/*  291 */     int size = getFieldSizeV5(env, ptrSize);
/*      */     
/*  293 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV6(JmqiEnvironment env, int ptrSize) {
/*  304 */     int size = getFieldSizeV5(env, ptrSize);
/*  305 */     size += 12;
/*  306 */     size += 4;
/*  307 */     size += ptrSize;
/*  308 */     size += ptrSize;
/*  309 */     size += 4;
/*  310 */     size += 4;
/*      */     
/*  312 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV6(JmqiEnvironment env, int ptrSize) {
/*  323 */     int size = getFieldSizeV6(env, ptrSize);
/*  324 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  326 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV7(JmqiEnvironment env, int ptrSize) {
/*  337 */     int size = getFieldSizeV6(env, ptrSize);
/*  338 */     size += 4;
/*  339 */     size += 4;
/*      */     
/*  341 */     return size;
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
/*      */   public static int getSizeV7(JmqiEnvironment env, int ptrSize) {
/*  353 */     int size = getFieldSizeV7(env, ptrSize);
/*      */     
/*  355 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV8(JmqiEnvironment env, int ptrSize) {
/*  366 */     int size = getFieldSizeV7(env, ptrSize);
/*  367 */     size += 4;
/*  368 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  369 */     size += ptrSize;
/*      */     
/*  371 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV8(JmqiEnvironment env, int ptrSize) {
/*  382 */     int size = getFieldSizeV8(env, ptrSize);
/*      */     
/*  384 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV9(JmqiEnvironment env, int ptrSize) {
/*  395 */     int size = getFieldSizeV8(env, ptrSize);
/*  396 */     size += 4;
/*  397 */     size += 8;
/*  398 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  400 */     return size;
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
/*      */   public static int getSizeV9(JmqiEnvironment env, int ptrSize) {
/*  412 */     int size = getFieldSizeV9(env, ptrSize);
/*      */     
/*  414 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSize(int ptrSize) throws JmqiException {
/*  425 */     if (Trace.isOn)
/*  426 */       Trace.entry(this, "com.ibm.mq.exits.MQCXP", "getSize(int)", new Object[] {
/*  427 */             Integer.valueOf(ptrSize)
/*      */           }); 
/*  429 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/*      */     
/*  431 */     if (Trace.isOn) {
/*  432 */       Trace.exit(this, "com.ibm.mq.exits.MQCXP", "getSize(int)", Integer.valueOf(traceRet1));
/*      */     }
/*  434 */     return traceRet1;
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
/*      */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*      */     int size;
/*  450 */     switch (version) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*  454 */         size = getSizeV3(env, ptrSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  487 */         return size;case 4: size = getSizeV4(env, ptrSize); return size;case 5: size = getSizeV5(env, ptrSize); return size;case 6: size = getSizeV6(env, ptrSize); return size;case 7: size = getSizeV7(env, ptrSize); return size;case 8: size = getSizeV8(env, ptrSize); return size;case 9: size = getSizeV9(env, ptrSize); return size;
/*      */     } 
/*      */     JmqiException e = new JmqiException(env, -1, null, 2, 6128, null);
/*      */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCXP(JmqiEnvironment env) {
/*  498 */     super(env);
/*  499 */     if (Trace.isOn) {
/*  500 */       Trace.entry(this, "com.ibm.mq.exits.MQCXP", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.exit(this, "com.ibm.mq.exits.MQCXP", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCapabilityFlags() {
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getCapabilityFlags()", "getter", 
/*  515 */           Integer.valueOf(this.capabilityFlags));
/*      */     }
/*  517 */     return this.capabilityFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCapabilityFlags(int capabilityFlags) {
/*  525 */     if (Trace.isOn) {
/*  526 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setCapabilityFlags(int)", "setter", 
/*  527 */           Integer.valueOf(capabilityFlags));
/*      */     }
/*  529 */     this.capabilityFlags = capabilityFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurHdrCompression() {
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getCurHdrCompression()", "getter", 
/*  539 */           Integer.valueOf(this.curHdrCompression));
/*      */     }
/*  541 */     return this.curHdrCompression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurHdrCompression(int curHdrCompression) {
/*  549 */     if (Trace.isOn) {
/*  550 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setCurHdrCompression(int)", "setter", 
/*  551 */           Integer.valueOf(curHdrCompression));
/*      */     }
/*  553 */     this.curHdrCompression = curHdrCompression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurMsgCompression() {
/*  561 */     if (Trace.isOn) {
/*  562 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getCurMsgCompression()", "getter", 
/*  563 */           Integer.valueOf(this.curMsgCompression));
/*      */     }
/*  565 */     return this.curMsgCompression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurMsgCompression(int curMsgCompression) {
/*  573 */     if (Trace.isOn) {
/*  574 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setCurMsgCompression(int)", "setter", 
/*  575 */           Integer.valueOf(curMsgCompression));
/*      */     }
/*  577 */     this.curMsgCompression = curMsgCompression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getExitData() {
/*  585 */     if (Trace.isOn) {
/*  586 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitData()", "getter", this.exitData);
/*      */     }
/*  588 */     return this.exitData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitData(String exitData) {
/*  596 */     if (Trace.isOn) {
/*  597 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitData(String)", "setter", exitData);
/*      */     }
/*  599 */     this.exitData = exitData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitReason() {
/*  606 */     if (Trace.isOn) {
/*  607 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitReason()", "getter", 
/*  608 */           Integer.valueOf(this.exitReason));
/*      */     }
/*  610 */     return this.exitReason;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitReason(int exitReason) {
/*  617 */     if (Trace.isOn) {
/*  618 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitReason(int)", "setter", 
/*  619 */           Integer.valueOf(exitReason));
/*      */     }
/*  621 */     this.exitReason = exitReason;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitResponse() {
/*  628 */     if (Trace.isOn) {
/*  629 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitResponse()", "getter", 
/*  630 */           Integer.valueOf(this.exitResponse));
/*      */     }
/*  632 */     return this.exitResponse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitResponse(int exitResponse) {
/*  639 */     if (Trace.isOn) {
/*  640 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitResponse(int)", "setter", 
/*  641 */           Integer.valueOf(exitResponse));
/*      */     }
/*  643 */     this.exitResponse = exitResponse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitResponse2() {
/*  650 */     if (Trace.isOn) {
/*  651 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitResponse2()", "getter", 
/*  652 */           Integer.valueOf(this.exitResponse2));
/*      */     }
/*  654 */     return this.exitResponse2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitResponse2(int exitResponse2) {
/*  661 */     if (Trace.isOn) {
/*  662 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitResponse2(int)", "setter", 
/*  663 */           Integer.valueOf(exitResponse2));
/*      */     }
/*  665 */     this.exitResponse2 = exitResponse2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitSpace() {
/*  673 */     if (Trace.isOn) {
/*  674 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitSpace()", "getter", 
/*  675 */           Integer.valueOf(this.exitSpace));
/*      */     }
/*  677 */     return this.exitSpace;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitSpace(int exitSpace) {
/*  685 */     if (Trace.isOn) {
/*  686 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitSpace(int)", "setter", 
/*  687 */           Integer.valueOf(exitSpace));
/*      */     }
/*  689 */     this.exitSpace = exitSpace;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getExitUserArea() {
/*  696 */     if (Trace.isOn) {
/*  697 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitUserArea()", "getter", this.exitUserArea);
/*      */     }
/*  699 */     return this.exitUserArea;
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
/*      */   public void setExitUserArea(byte[] exitUserArea) {
/*  711 */     if (Trace.isOn) {
/*  712 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitUserArea(byte [ ])", "setter", exitUserArea);
/*      */     }
/*      */     
/*  715 */     JmqiTools.byteArrayCopy(exitUserArea, 0, this.exitUserArea, 0, 16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFapLevel() {
/*  723 */     if (Trace.isOn) {
/*  724 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getFapLevel()", "getter", 
/*  725 */           Integer.valueOf(this.fapLevel));
/*      */     }
/*  727 */     return this.fapLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFapLevel(int fapLevel) {
/*  735 */     if (Trace.isOn) {
/*  736 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setFapLevel(int)", "setter", 
/*  737 */           Integer.valueOf(fapLevel));
/*      */     }
/*  739 */     this.fapLevel = fapLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeedback() {
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getFeedback()", "getter", 
/*  748 */           Integer.valueOf(this.feedback));
/*      */     }
/*  750 */     return this.feedback;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeedback(int feedback) {
/*  757 */     if (Trace.isOn) {
/*  758 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setFeedback(int)", "setter", 
/*  759 */           Integer.valueOf(feedback));
/*      */     }
/*  761 */     this.feedback = feedback;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeaderLength() {
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getHeaderLength()", "getter", 
/*  771 */           Integer.valueOf(this.headerLength));
/*      */     }
/*  773 */     return this.headerLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeaderLength(int headerLength) {
/*  781 */     if (Trace.isOn) {
/*  782 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setHeaderLength(int)", "setter", 
/*  783 */           Integer.valueOf(headerLength));
/*      */     }
/*  785 */     this.headerLength = headerLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCSP getSecurityParms() {
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getSecurityParms()", "getter", this.securityParms);
/*      */     }
/*  797 */     return this.securityParms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityParms(MQCSP mqCSP) {
/*  808 */     if (Trace.isOn) {
/*  809 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setSecurityParms(MQCSP)", "setter", mqCSP);
/*      */     }
/*  811 */     this.securityParms = mqCSP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgRetryCount() {
/*  820 */     if (Trace.isOn) {
/*  821 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getMsgRetryCount()", "getter", 
/*  822 */           Integer.valueOf(this.msgRetryCount));
/*      */     }
/*  824 */     return this.msgRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryCount(int msgRetryCount) {
/*  831 */     if (Trace.isOn) {
/*  832 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setMsgRetryCount(int)", "setter", 
/*  833 */           Integer.valueOf(msgRetryCount));
/*      */     }
/*  835 */     this.msgRetryCount = msgRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgRetryInterval() {
/*  843 */     if (Trace.isOn) {
/*  844 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getMsgRetryInterval()", "getter", 
/*  845 */           Integer.valueOf(this.msgRetryInterval));
/*      */     }
/*  847 */     return this.msgRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryInterval(int msgRetryInterval) {
/*  854 */     if (Trace.isOn) {
/*  855 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setMsgRetryInterval(int)", "setter", 
/*  856 */           Integer.valueOf(msgRetryInterval));
/*      */     }
/*  858 */     this.msgRetryInterval = msgRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgRetryReason() {
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getMsgRetryReason()", "getter", 
/*  868 */           Integer.valueOf(this.msgRetryReason));
/*      */     }
/*  870 */     return this.msgRetryReason;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryReason(int msgRetryReason) {
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setMsgRetryReason(int)", "setter", 
/*  879 */           Integer.valueOf(msgRetryReason));
/*      */     }
/*  881 */     this.msgRetryReason = msgRetryReason;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSegmentLength() {
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getMaxSegmentLength()", "getter", 
/*  891 */           Integer.valueOf(this.maxSegmentLength));
/*      */     }
/*  893 */     return this.maxSegmentLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxSegmentLength(int maxSegmentLength) {
/*  900 */     if (Trace.isOn) {
/*  901 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setMaxSegmentLength(int)", "setter", 
/*  902 */           Integer.valueOf(maxSegmentLength));
/*      */     }
/*  904 */     this.maxSegmentLength = maxSegmentLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPartnerName() {
/*  912 */     if (Trace.isOn) {
/*  913 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getPartnerName()", "getter", this.partnerName);
/*      */     }
/*  915 */     return this.partnerName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPartnerName(String partnerName) {
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setPartnerName(String)", "setter", partnerName);
/*      */     }
/*  926 */     this.partnerName = partnerName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSslCertUserid() {
/*  934 */     if (Trace.isOn) {
/*  935 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getSslCertUserid()", "getter", this.sslCertUserid);
/*      */     }
/*  937 */     return this.sslCertUserid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslCertUserid(String sslCertUserid) {
/*  945 */     if (Trace.isOn) {
/*  946 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setSslCertUserid(String)", "setter", sslCertUserid);
/*      */     }
/*      */     
/*  949 */     this.sslCertUserid = sslCertUserid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSslRemCertIssName() {
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getSslRemCertIssName()", "getter", this.sslRemCertIssName);
/*      */     }
/*      */     
/*  961 */     return this.sslRemCertIssName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslRemCertIssName(String sslRemCertIssName) {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setSslRemCertIssName(String)", "setter", sslRemCertIssName);
/*      */     }
/*      */     
/*  973 */     this.sslRemCertIssName = sslRemCertIssName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitId() {
/*  980 */     if (Trace.isOn) {
/*  981 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitId()", "getter", Integer.valueOf(this.exitId));
/*      */     }
/*      */     
/*  984 */     return this.exitId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitId(int exitId) {
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitId(int)", "setter", 
/*  993 */           Integer.valueOf(exitId));
/*      */     }
/*  995 */     this.exitId = exitId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitNumber() {
/* 1003 */     if (Trace.isOn) {
/* 1004 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getExitNumber()", "getter", 
/* 1005 */           Integer.valueOf(this.exitNumber));
/*      */     }
/* 1007 */     return this.exitNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitNumber(int exitNumber) {
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setExitNumber(int)", "setter", 
/* 1017 */           Integer.valueOf(exitNumber));
/*      */     }
/* 1019 */     this.exitNumber = exitNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getVersion()", "getter", 
/* 1030 */           Integer.valueOf(this.version));
/*      */     }
/* 1032 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/* 1041 */     if (Trace.isOn) {
/* 1042 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setVersion(int)", "setter", 
/* 1043 */           Integer.valueOf(version));
/*      */     }
/* 1045 */     this.version = version;
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
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 1058 */     int size = getSize(this.env, this.version, ptrSize);
/*      */     
/* 1060 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSharingConversations() {
/* 1068 */     if (Trace.isOn) {
/* 1069 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getSharingConversations()", "getter", 
/* 1070 */           Boolean.valueOf(this.sharingConversations));
/*      */     }
/* 1072 */     return this.sharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSharingConversations(boolean sharingConversations) {
/* 1080 */     if (Trace.isOn) {
/* 1081 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setSharingConversations(boolean)", "setter", 
/* 1082 */           Boolean.valueOf(sharingConversations));
/*      */     }
/* 1084 */     this.sharingConversations = sharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUserSource() {
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getUserSource()", "getter", 
/* 1094 */           Boolean.valueOf(this.sharingConversations));
/*      */     }
/* 1096 */     return this.sharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserSource(int userSource) {
/* 1104 */     if (Trace.isOn) {
/* 1105 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setUserSource(int)", "setter", 
/* 1106 */           Integer.valueOf(userSource));
/*      */     }
/* 1108 */     this.userSource = userSource;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteProduct() {
/* 1116 */     if (Trace.isOn) {
/* 1117 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getRemoteProduct()", "getter", this.remoteProduct);
/*      */     }
/* 1119 */     return this.remoteProduct;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteProduct(String remoteProduct) {
/* 1127 */     if (Trace.isOn) {
/* 1128 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setRemoteProduct(String)", "setter", remoteProduct);
/*      */     }
/*      */     
/* 1131 */     this.remoteProduct = remoteProduct;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteVersion() {
/* 1139 */     if (Trace.isOn) {
/* 1140 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "getRemoteVersion()", "getter", this.remoteVersion);
/*      */     }
/* 1142 */     return this.remoteVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteVersion(String remoteVersion) {
/* 1150 */     if (Trace.isOn) {
/* 1151 */       Trace.data(this, "com.ibm.mq.exits.MQCXP", "setRemoteVersion(String)", "setter", remoteVersion);
/*      */     }
/*      */     
/* 1154 */     this.remoteVersion = remoteVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1162 */     if (Trace.isOn) {
/* 1163 */       Trace.entry(this, "com.ibm.mq.exits.MQCXP", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1165 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 1167 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 1168 */     int pos = offset;
/*      */ 
/*      */     
/* 1171 */     dc.writeMQField("CXP ", buffer, pos, 4, cp, tls);
/* 1172 */     pos += 4;
/*      */     
/* 1174 */     dc.writeI32(this.version, buffer, pos, swap);
/* 1175 */     pos += 4;
/*      */     
/* 1177 */     dc.writeI32(this.exitId, buffer, pos, swap);
/* 1178 */     pos += 4;
/*      */     
/* 1180 */     dc.writeI32(this.exitReason, buffer, pos, swap);
/* 1181 */     pos += 4;
/*      */     
/* 1183 */     dc.writeI32(this.exitResponse, buffer, pos, swap);
/* 1184 */     pos += 4;
/*      */     
/* 1186 */     dc.writeI32(this.exitResponse2, buffer, pos, swap);
/* 1187 */     pos += 4;
/*      */     
/* 1189 */     dc.writeI32(this.feedback, buffer, pos, swap);
/* 1190 */     pos += 4;
/*      */     
/* 1192 */     dc.writeI32(this.maxSegmentLength, buffer, pos, swap);
/* 1193 */     pos += 4;
/*      */     
/* 1195 */     System.arraycopy(this.exitUserArea, 0, buffer, pos, 16);
/* 1196 */     pos += 16;
/*      */     
/* 1198 */     dc.writeField(this.exitData, buffer, pos, 32, cp, tls);
/* 1199 */     pos += 32;
/*      */     
/* 1201 */     dc.writeI32(this.msgRetryCount, buffer, pos, swap);
/* 1202 */     pos += 4;
/*      */     
/* 1204 */     dc.writeI32(this.msgRetryInterval, buffer, pos, swap);
/* 1205 */     pos += 4;
/*      */     
/* 1207 */     dc.writeI32(this.msgRetryReason, buffer, pos, swap);
/* 1208 */     pos += 4;
/*      */     
/* 1210 */     dc.writeI32(this.headerLength, buffer, pos, swap);
/* 1211 */     pos += 4;
/*      */     
/* 1213 */     dc.writeField(this.partnerName, buffer, pos, 48, cp, tls);
/* 1214 */     pos += 48;
/*      */     
/* 1216 */     dc.writeI32(this.fapLevel, buffer, pos, swap);
/* 1217 */     pos += 4;
/*      */     
/* 1219 */     dc.writeI32(this.capabilityFlags, buffer, pos, swap);
/* 1220 */     pos += 4;
/*      */     
/* 1222 */     dc.writeI32(this.exitNumber, buffer, pos, swap);
/* 1223 */     pos += 4;
/* 1224 */     if (this.version >= 5) {
/*      */       
/* 1226 */       dc.writeI32(this.exitSpace, buffer, pos, swap);
/* 1227 */       pos += 4;
/*      */     } 
/* 1229 */     if (this.version >= 6) {
/*      */       
/* 1231 */       dc.writeField(this.sslCertUserid, buffer, pos, 12, cp, tls);
/* 1232 */       pos += 12;
/*      */ 
/*      */ 
/*      */       
/* 1236 */       dc.clear(buffer, pos, 4);
/* 1237 */       pos += 4;
/*      */       
/* 1239 */       dc.clear(buffer, pos, ptrSize);
/* 1240 */       pos += ptrSize;
/*      */       
/* 1242 */       dc.clear(buffer, pos, ptrSize);
/* 1243 */       pos += ptrSize;
/*      */       
/* 1245 */       dc.writeI32(this.curHdrCompression, buffer, pos, swap);
/* 1246 */       pos += 4;
/*      */       
/* 1248 */       dc.writeI32(this.curMsgCompression, buffer, pos, swap);
/* 1249 */       pos += 4;
/*      */       
/* 1251 */       if (this.version == 6) {
/* 1252 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1253 */         dc.clear(buffer, pos, padding);
/* 1254 */         pos += padding;
/*      */       } 
/*      */     } 
/* 1257 */     if (this.version >= 7) {
/*      */       
/* 1259 */       dc.writeI32(this.hconn, buffer, pos, swap);
/* 1260 */       pos += 4;
/*      */       
/* 1262 */       dc.writeI32((this.sharingConversations == true) ? 1 : 0, buffer, pos, swap);
/* 1263 */       pos += 4;
/*      */     } 
/* 1265 */     if (this.version >= 8) {
/*      */       
/* 1267 */       dc.writeI32(this.userSource, buffer, pos, swap);
/* 1268 */       pos += 4;
/*      */       
/* 1270 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1271 */       dc.clear(buffer, pos, padding);
/* 1272 */       pos += padding;
/*      */       
/* 1274 */       dc.clear(buffer, pos, ptrSize);
/* 1275 */       pos += ptrSize;
/*      */     } 
/* 1277 */     if (this.version >= 9) {
/*      */       
/* 1279 */       dc.writeField(this.remoteProduct, buffer, pos, 4, cp, tls);
/* 1280 */       pos += 4;
/*      */       
/* 1282 */       dc.writeField(this.remoteVersion, buffer, pos, 8, cp, tls);
/* 1283 */       pos += 8;
/*      */     } 
/*      */     
/* 1286 */     if (Trace.isOn) {
/* 1287 */       Trace.exit(this, "com.ibm.mq.exits.MQCXP", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1288 */           Integer.valueOf(pos));
/*      */     }
/* 1290 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1299 */     if (Trace.isOn) {
/* 1300 */       Trace.entry(this, "com.ibm.mq.exits.MQCXP", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1302 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 1304 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 1305 */     int pos = offset;
/*      */ 
/*      */     
/* 1308 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1309 */     if (!strucId.equals("CXP ")) {
/*      */       
/* 1311 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 1312 */       if (Trace.isOn) {
/* 1313 */         Trace.throwing(this, "com.ibm.mq.exits.MQCXP", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1316 */       throw traceRet1;
/*      */     } 
/* 1318 */     pos += 4;
/*      */ 
/*      */     
/* 1321 */     pos += 4;
/*      */ 
/*      */     
/* 1324 */     pos += 4;
/*      */ 
/*      */     
/* 1327 */     pos += 4;
/*      */ 
/*      */     
/* 1330 */     this.exitResponse = dc.readI32(buffer, pos, swap);
/* 1331 */     pos += 4;
/*      */ 
/*      */     
/* 1334 */     this.exitResponse2 = dc.readI32(buffer, pos, swap);
/* 1335 */     pos += 4;
/*      */ 
/*      */     
/* 1338 */     this.feedback = dc.readI32(buffer, pos, swap);
/* 1339 */     pos += 4;
/*      */ 
/*      */     
/* 1342 */     pos += 4;
/*      */ 
/*      */     
/* 1345 */     System.arraycopy(buffer, pos, this.exitUserArea, 0, 16);
/* 1346 */     pos += 16;
/*      */ 
/*      */     
/* 1349 */     pos += 36;
/*      */ 
/*      */     
/* 1352 */     this.msgRetryInterval = dc.readI32(buffer, pos, swap);
/* 1353 */     pos += 4;
/*      */ 
/*      */     
/* 1356 */     pos += 4;
/*      */ 
/*      */     
/* 1359 */     this.headerLength = dc.readI32(buffer, pos, swap);
/* 1360 */     pos += 4;
/*      */ 
/*      */     
/* 1363 */     pos += 60;
/*      */ 
/*      */     
/* 1366 */     if (this.version >= 5) {
/*      */       
/* 1368 */       this.exitSpace = dc.readI32(buffer, pos, swap);
/* 1369 */       pos += 4;
/*      */     } 
/*      */ 
/*      */     
/* 1373 */     if (this.version >= 6) {
/*      */ 
/*      */ 
/*      */       
/* 1377 */       pos += 16 + ptrSize + ptrSize;
/*      */ 
/*      */       
/* 1380 */       this.curHdrCompression = dc.readI32(buffer, pos, swap);
/* 1381 */       pos += 4;
/*      */ 
/*      */       
/* 1384 */       this.curMsgCompression = dc.readI32(buffer, pos, swap);
/* 1385 */       pos += 4;
/*      */ 
/*      */       
/* 1388 */       if (this.version == 6) {
/* 1389 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1390 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1394 */     if (this.version >= 7) {
/*      */       
/* 1396 */       pos += 4;
/*      */ 
/*      */       
/* 1399 */       pos += 4;
/*      */     } 
/*      */     
/* 1402 */     if (this.version >= 8) {
/*      */       
/* 1404 */       this.userSource = dc.readI32(buffer, pos, swap);
/* 1405 */       pos += 4;
/*      */ 
/*      */       
/* 1408 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1409 */       pos += padding;
/*      */ 
/*      */       
/* 1412 */       pos += ptrSize;
/*      */     } 
/*      */     
/* 1415 */     if (this.version >= 9)
/*      */     {
/* 1417 */       pos += 12;
/*      */     }
/*      */     
/* 1420 */     if (Trace.isOn) {
/* 1421 */       Trace.exit(this, "com.ibm.mq.exits.MQCXP", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1422 */           Integer.valueOf(pos));
/*      */     }
/* 1424 */     return pos;
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
/* 1436 */     fmt.add("version", this.version);
/* 1437 */     fmt.add("exitId", this.exitId);
/* 1438 */     fmt.add("exitReason", this.exitReason);
/* 1439 */     fmt.add("exitResponse", this.exitResponse);
/* 1440 */     fmt.add("exitResponse2", this.exitResponse2);
/* 1441 */     fmt.add("feedback", this.feedback);
/* 1442 */     fmt.add("maxSegmentLength", this.maxSegmentLength);
/* 1443 */     fmt.add("exitUserArea", this.exitUserArea);
/* 1444 */     fmt.add("exitData", this.exitData);
/* 1445 */     fmt.add("msgRetryCount", this.msgRetryCount);
/* 1446 */     fmt.add("msgRetryInterval", this.msgRetryInterval);
/* 1447 */     fmt.add("msgRetryReason", this.msgRetryReason);
/* 1448 */     fmt.add("headerLength", this.headerLength);
/* 1449 */     fmt.add("partnerName", this.partnerName);
/* 1450 */     fmt.add("fapLevel", this.fapLevel);
/* 1451 */     fmt.add("capabilityFlags", this.capabilityFlags);
/* 1452 */     fmt.add("exitNumber", this.exitNumber);
/* 1453 */     fmt.add("exitSpace", this.exitSpace);
/* 1454 */     fmt.add("sslCertUserid", this.sslCertUserid);
/* 1455 */     fmt.add("securityParms", this.securityParms);
/* 1456 */     fmt.add("curHdrCompression", this.curHdrCompression);
/* 1457 */     fmt.add("curMsgCompression", this.curMsgCompression);
/* 1458 */     fmt.add("hconn", this.hconn);
/* 1459 */     fmt.add("sharingConversations", this.sharingConversations);
/* 1460 */     fmt.add("userSource", this.userSource);
/* 1461 */     fmt.add("remoteProduct", this.remoteProduct);
/* 1462 */     fmt.add("remoteVersion", this.remoteVersion);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\exits\MQCXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */