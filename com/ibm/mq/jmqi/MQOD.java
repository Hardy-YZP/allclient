/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.jmqi.handles.Pint;
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
/*      */ public class MQOD
/*      */   extends AbstractMqiStructure
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQOD.java";
/*      */   private static final int SIZEOF_STRUCID = 4;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_OBJECTTYPE = 4;
/*      */   private static final int SIZEOF_OBJECTNAME = 48;
/*      */   private static final int SIZEOF_OBJECTQMGRNAME = 48;
/*      */   private static final int SIZEOF_DYNAMICQNAME = 48;
/*      */   private static final int SIZEOF_ALTERNATEUSERID = 12;
/*      */   private static final int SIZEOF_RECSPRESENT = 4;
/*      */   private static final int SIZEOF_KNOWNDESTCOUNT = 4;
/*      */   private static final int SIZEOF_UNKNOWNDESTCOUNT = 4;
/*      */   private static final int SIZEOF_INVALIDDESTCOUNT = 4;
/*      */   private static final int SIZEOF_OBJECTRECOFFSET = 4;
/*      */   private static final int SIZEOF_RESPONSERECOFFSET = 4;
/*      */   private static final int SIZEOF_ALTERNATESECURITYID = 40;
/*      */   private static final int SIZEOF_RESOLVEDQNAME = 48;
/*      */   private static final int SIZEOF_RESOLVEDQMGRNAME = 48;
/*      */   private static final int SIZEOF_RESOLVEDTYPE = 4;
/*      */   
/*      */   static {
/*   43 */     if (Trace.isOn) {
/*   44 */       Trace.data("com.ibm.mq.jmqi.MQOD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQOD.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV1(int sizeofNativePointer) {
/*  135 */     int sizeOfStructureV1 = 168;
/*  136 */     return sizeOfStructureV1;
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
/*  147 */     int sizeOfStructureV2 = getSizeV1(sizeofNativePointer) + 4 + 4 + 4 + 4 + 4 + 4 + sizeofNativePointer + sizeofNativePointer;
/*      */     
/*  149 */     return sizeOfStructureV2;
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
/*      */   public static int getSizeV3(int ptrSize) {
/*  161 */     int size = getSizeV2(ptrSize) + 40 + 48 + 48;
/*      */ 
/*      */     
/*  164 */     int padding = JmqiTools.alignToGrain(ptrSize, size);
/*  165 */     size += padding;
/*      */     
/*  167 */     return size;
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
/*      */   public static int getSizeV4(int ptrSize) {
/*  179 */     int size = getSizeV3(ptrSize) + 3 * MQCHARV.getSize(ptrSize) + 4;
/*      */ 
/*      */     
/*  182 */     int padding = JmqiTools.alignToGrain(ptrSize, size);
/*  183 */     size += padding;
/*      */     
/*  185 */     return size;
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
/*      */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*      */     int size;
/*  200 */     if (version == 1) {
/*  201 */       size = getSizeV1(ptrSize);
/*      */     }
/*  203 */     else if (version == 2) {
/*  204 */       size = getSizeV2(ptrSize);
/*      */     }
/*  206 */     else if (version == 3) {
/*  207 */       size = getSizeV3(ptrSize);
/*      */     }
/*  209 */     else if (version == 4) {
/*  210 */       size = getSizeV4(ptrSize);
/*      */     }
/*      */     else {
/*      */       
/*  214 */       JmqiException e = new JmqiException(env, -1, null, 2, 2044, null);
/*  215 */       throw e;
/*      */     } 
/*  217 */     return size;
/*      */   }
/*      */   
/*  220 */   private int version = 1;
/*  221 */   private int objectType = 1;
/*  222 */   private String objectName = null;
/*  223 */   private String objectQMgrName = null;
/*  224 */   private String dynamicQName = "AMQ.*";
/*  225 */   private String alternateUserId = null;
/*  226 */   private int recsPresent = 0;
/*  227 */   private int knownDestCount = 0;
/*  228 */   private int unknownDestCount = 0;
/*  229 */   private int invalidDestCount = 0;
/*  230 */   private MQOR[] objectRecords = null;
/*  231 */   private MQRR[] responseRecords = null;
/*  232 */   private byte[] alternateSecurityId = new byte[40];
/*  233 */   private String resolvedQName = null;
/*  234 */   private String resolvedQMgrName = null;
/*  235 */   private MQCHARV objectString = null;
/*  236 */   private MQCHARV selectionString = null;
/*  237 */   private MQCHARV resObjectString = null;
/*  238 */   private int resolvedType = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQOD(JmqiEnvironment env) {
/*  246 */     super(env);
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOD", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*  250 */     this.objectString = env.newMQCHARV();
/*  251 */     this.selectionString = env.newMQCHARV();
/*  252 */     this.resObjectString = env.newMQCHARV();
/*      */     
/*  254 */     if (Trace.isOn) {
/*  255 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOD", "<init>(JmqiEnvironment)");
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
/*      */   public byte[] getAlternateSecurityId() {
/*  269 */     if (Trace.isOn) {
/*  270 */       Trace.data(this, "getAlternateSecurityId()", this.alternateSecurityId);
/*      */     }
/*  272 */     return this.alternateSecurityId;
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
/*      */   public void setAlternateSecurityId(byte[] alternateSecurityId) {
/*  286 */     if (Trace.isOn) {
/*  287 */       Trace.data(this, "setAlternateSecurityId(byte [ ])", alternateSecurityId);
/*      */     }
/*  289 */     JmqiTools.byteArrayCopy(alternateSecurityId, 0, this.alternateSecurityId, 0, 40);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAlternateUserId() {
/*  296 */     if (Trace.isOn) {
/*  297 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getAlternateUserId()", "getter", this.alternateUserId);
/*      */     }
/*  299 */     return this.alternateUserId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlternateUserId(String alternateUserId) {
/*  307 */     if (Trace.isOn) {
/*  308 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setAlternateUserId(String)", "setter", alternateUserId);
/*      */     }
/*      */     
/*  311 */     this.alternateUserId = alternateUserId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDynamicQName() {
/*  319 */     if (Trace.isOn) {
/*  320 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getDynamicQName()", "getter", this.dynamicQName);
/*      */     }
/*  322 */     return this.dynamicQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamicQName(String dynamicQName) {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setDynamicQName(String)", "setter", dynamicQName);
/*      */     }
/*  333 */     this.dynamicQName = dynamicQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInvalidDestCount() {
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getInvalidDestCount()", "getter", 
/*  343 */           Integer.valueOf(this.invalidDestCount));
/*      */     }
/*  345 */     return this.invalidDestCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInvalidDestCount(int invalidDestCount) {
/*  353 */     if (Trace.isOn) {
/*  354 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setInvalidDestCount(int)", "setter", 
/*  355 */           Integer.valueOf(invalidDestCount));
/*      */     }
/*  357 */     this.invalidDestCount = invalidDestCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKnownDestCount() {
/*  365 */     if (Trace.isOn) {
/*  366 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getKnownDestCount()", "getter", 
/*  367 */           Integer.valueOf(this.knownDestCount));
/*      */     }
/*  369 */     return this.knownDestCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKnownDestCount(int knownDestCount) {
/*  377 */     if (Trace.isOn) {
/*  378 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setKnownDestCount(int)", "setter", 
/*  379 */           Integer.valueOf(knownDestCount));
/*      */     }
/*  381 */     this.knownDestCount = knownDestCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getObjectName() {
/*  389 */     if (Trace.isOn) {
/*  390 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getObjectName()", "getter", this.objectName);
/*      */     }
/*  392 */     return this.objectName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectName(String objectName) {
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setObjectName(String)", "setter", objectName);
/*      */     }
/*  403 */     this.objectName = objectName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getObjectQMgrName() {
/*  411 */     if (Trace.isOn) {
/*  412 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getObjectQMgrName()", "getter", this.objectQMgrName);
/*      */     }
/*  414 */     return this.objectQMgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectQMgrName(String objectQmgrName) {
/*  422 */     if (Trace.isOn) {
/*  423 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setObjectQMgrName(String)", "setter", objectQmgrName);
/*      */     }
/*      */     
/*  426 */     this.objectQMgrName = objectQmgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getObjectType() {
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getObjectType()", "getter", 
/*  436 */           Integer.valueOf(this.objectType));
/*      */     }
/*  438 */     return this.objectType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectType(int objectType) {
/*  446 */     if (Trace.isOn) {
/*  447 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setObjectType(int)", "setter", 
/*  448 */           Integer.valueOf(objectType));
/*      */     }
/*  450 */     this.objectType = objectType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRecsPresent() {
/*  458 */     if (Trace.isOn) {
/*  459 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getRecsPresent()", "getter", 
/*  460 */           Integer.valueOf(this.recsPresent));
/*      */     }
/*  462 */     return this.recsPresent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRecsPresent(int recsPresent) {
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setRecsPresent(int)", "setter", 
/*  472 */           Integer.valueOf(recsPresent));
/*      */     }
/*  474 */     this.recsPresent = recsPresent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResolvedQMgrName() {
/*  482 */     if (Trace.isOn) {
/*  483 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getResolvedQMgrName()", "getter", this.resolvedQMgrName);
/*      */     }
/*  485 */     return this.resolvedQMgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setResolvedQMgrName(String resolvedQMgrName) {
/*  493 */     if (Trace.isOn) {
/*  494 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setResolvedQMgrName(String)", "setter", resolvedQMgrName);
/*      */     }
/*      */     
/*  497 */     this.resolvedQMgrName = resolvedQMgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResolvedQName() {
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getResolvedQName()", "getter", this.resolvedQName);
/*      */     }
/*  508 */     return this.resolvedQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setResolvedQName(String resolvedQName) {
/*  516 */     if (Trace.isOn) {
/*  517 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setResolvedQName(String)", "setter", resolvedQName);
/*      */     }
/*  519 */     this.resolvedQName = resolvedQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUnknownDestCount() {
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getUnknownDestCount()", "getter", 
/*  529 */           Integer.valueOf(this.unknownDestCount));
/*      */     }
/*  531 */     return this.unknownDestCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnknownDestCount(int unknownDestCount) {
/*  539 */     if (Trace.isOn) {
/*  540 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setUnknownDestCount(int)", "setter", 
/*  541 */           Integer.valueOf(unknownDestCount));
/*      */     }
/*  543 */     this.unknownDestCount = unknownDestCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getVersion()", "getter", Integer.valueOf(this.version));
/*      */     }
/*  555 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setVersion(int)", "setter", 
/*  566 */           Integer.valueOf(version));
/*      */     }
/*  568 */     this.version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQOR[] getObjectRecords() {
/*  576 */     if (Trace.isOn) {
/*  577 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getObjectRecords()", "getter", this.objectRecords);
/*      */     }
/*  579 */     return this.objectRecords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectRecords(MQOR[] objectRecords) {
/*  587 */     if (Trace.isOn) {
/*  588 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setObjectRecords(MQOR [ ])", "setter", objectRecords);
/*      */     }
/*      */     
/*  591 */     this.objectRecords = objectRecords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQRR[] getResponseRecords() {
/*  599 */     if (Trace.isOn) {
/*  600 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getResponseRecords()", "getter", this.responseRecords);
/*      */     }
/*  602 */     return this.responseRecords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setResponseRecords(MQRR[] responseRecords) {
/*  610 */     if (Trace.isOn) {
/*  611 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "setResponseRecords(MQRR [ ])", "setter", responseRecords);
/*      */     }
/*      */     
/*  614 */     this.responseRecords = responseRecords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCHARV getObjectString() {
/*  622 */     if (Trace.isOn) {
/*  623 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getObjectString()", "getter", this.objectString);
/*      */     }
/*  625 */     return this.objectString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCHARV getSelectionString() {
/*  633 */     if (Trace.isOn) {
/*  634 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getSelectionString()", "getter", this.selectionString);
/*      */     }
/*  636 */     return this.selectionString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public MQCHARV getResolvedObjectString() {
/*  645 */     MQCHARV traceRet1 = getResObjectString();
/*  646 */     if (Trace.isOn) {
/*  647 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getResolvedObjectString()", "getter", traceRet1);
/*      */     }
/*  649 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCHARV getResObjectString() {
/*  656 */     if (Trace.isOn) {
/*  657 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getResObjectString()", "getter", this.resObjectString);
/*      */     }
/*  659 */     return this.resObjectString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  670 */     int size = getSize(this.env, this.version, ptrSize);
/*      */ 
/*      */     
/*  673 */     if (this.version >= 2 && 
/*  674 */       this.recsPresent > 0) {
/*  675 */       if (this.objectRecords != null) {
/*  676 */         size += this.recsPresent * MQOR.getSize(this.env, ptrSize);
/*      */       }
/*  678 */       if (this.responseRecords != null) {
/*  679 */         size += this.recsPresent * MQRR.getSize(this.env, ptrSize);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  685 */     if (this.version >= 4) {
/*  686 */       size += this.objectString.getRequiredBufferSize(ptrSize, cp);
/*  687 */       size += this.selectionString.getRequiredBufferSize(ptrSize, cp);
/*  688 */       size += this.resObjectString.getRequiredBufferSize(ptrSize, cp);
/*      */     } 
/*      */     
/*  691 */     return size;
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
/*      */   public int getRequiredInputBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  706 */     int size = getSize(this.env, this.version, ptrSize);
/*      */ 
/*      */     
/*  709 */     if (this.version >= 2 && 
/*  710 */       this.recsPresent > 0) {
/*  711 */       if (this.objectRecords != null) {
/*  712 */         size += this.recsPresent * MQOR.getSize(this.env, ptrSize);
/*      */       }
/*  714 */       if (this.responseRecords != null) {
/*  715 */         size += this.recsPresent * MQRR.getSize(this.env, ptrSize);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  721 */     if (this.version >= 4) {
/*  722 */       size += this.objectString.getRequiredInputBufferSize(ptrSize, cp);
/*  723 */       size += this.selectionString.getRequiredInputBufferSize(ptrSize, cp);
/*  724 */       size += this.resObjectString.getRequiredInputBufferSize(ptrSize, cp);
/*      */     } 
/*      */     
/*  727 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  735 */     if (Trace.isOn) {
/*  736 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  738 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*  740 */     int pos = offset;
/*  741 */     int startPos = offset;
/*  742 */     int objectRecOffsetPos = -1;
/*  743 */     int responseRecOffsetPos = -1;
/*  744 */     int objectStringPos = -1;
/*  745 */     int selectionStringPos = -1;
/*  746 */     int resObjectStringPos = -1;
/*  747 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/*  749 */     dc.writeMQField("OD  ", buffer, pos, 4, cp, tls);
/*  750 */     pos += 4;
/*      */     
/*  752 */     dc.writeI32(this.version, buffer, pos, swap);
/*  753 */     pos += 4;
/*      */     
/*  755 */     dc.writeI32(this.objectType, buffer, pos, swap);
/*  756 */     pos += 4;
/*      */     
/*  758 */     dc.writeMQField(this.objectName, buffer, pos, 48, cp, tls);
/*  759 */     pos += 48;
/*      */     
/*  761 */     dc.writeMQField(this.objectQMgrName, buffer, pos, 48, cp, tls);
/*  762 */     pos += 48;
/*      */     
/*  764 */     dc.writeMQField(this.dynamicQName, buffer, pos, 48, cp, tls);
/*  765 */     pos += 48;
/*      */     
/*  767 */     dc.writeMQField(this.alternateUserId, buffer, pos, 12, cp, tls);
/*  768 */     pos += 12;
/*      */     
/*  770 */     if (this.version >= 2) {
/*      */       
/*  772 */       dc.writeI32(this.recsPresent, buffer, pos, swap);
/*  773 */       pos += 4;
/*      */ 
/*      */       
/*  776 */       dc.clear(buffer, pos, 20 + ptrSize + ptrSize);
/*  777 */       pos += 12;
/*      */       
/*  779 */       objectRecOffsetPos = pos;
/*  780 */       pos += 4;
/*      */       
/*  782 */       responseRecOffsetPos = pos;
/*  783 */       pos += 4;
/*      */       
/*  785 */       pos += ptrSize + ptrSize;
/*      */     } 
/*      */     
/*  788 */     if (this.version >= 3) {
/*      */       
/*  790 */       System.arraycopy(this.alternateSecurityId, 0, buffer, pos, 40);
/*  791 */       pos += 40;
/*      */       
/*  793 */       dc.clear(buffer, pos, 96);
/*  794 */       pos += 96;
/*      */       
/*  796 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  797 */       pos += padding;
/*      */     } 
/*      */     
/*  800 */     if (this.version >= 4) {
/*      */       
/*  802 */       objectStringPos = pos;
/*  803 */       pos += MQCHARV.getSize(ptrSize);
/*      */       
/*  805 */       selectionStringPos = pos;
/*  806 */       pos += MQCHARV.getSize(ptrSize);
/*      */ 
/*      */       
/*  809 */       resObjectStringPos = pos;
/*  810 */       pos += MQCHARV.getSize(ptrSize);
/*      */       
/*  812 */       dc.writeI32(this.resolvedType, buffer, pos, swap);
/*  813 */       pos += 4;
/*      */       
/*  815 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  816 */       pos += padding;
/*      */     } 
/*      */     
/*  819 */     if (this.version >= 2 && this.recsPresent > 0) {
/*      */       
/*  821 */       if (this.objectRecords == null && this.responseRecords == null) {
/*      */         
/*  823 */         JmqiException e = new JmqiException(this.env, -1, null, 2, 2154, null);
/*  824 */         if (Trace.isOn) {
/*  825 */           Trace.throwing(this, "com.ibm.mq.jmqi.MQOD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 1);
/*      */         }
/*      */         
/*  828 */         throw e;
/*      */       } 
/*      */ 
/*      */       
/*  832 */       if (this.objectRecords != null) {
/*  833 */         if (this.objectRecords.length < this.recsPresent) {
/*      */           
/*  835 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2155, null);
/*  836 */           if (Trace.isOn) {
/*  837 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQOD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 2);
/*      */           }
/*      */           
/*  840 */           throw e;
/*      */         } 
/*      */         
/*  843 */         dc.writeI32(pos - startPos, buffer, objectRecOffsetPos, swap);
/*      */         
/*  845 */         for (int i = 0; i < this.recsPresent; i++) {
/*  846 */           if (this.objectRecords[i] == null) {
/*  847 */             this.objectRecords[i] = this.env.newMQOR();
/*      */           }
/*  849 */           pos = this.objectRecords[i].writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  854 */       if (this.responseRecords != null) {
/*  855 */         if (this.responseRecords.length < this.recsPresent) {
/*      */           
/*  857 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2156, null);
/*  858 */           if (Trace.isOn) {
/*  859 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQOD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 3);
/*      */           }
/*      */           
/*  862 */           throw e;
/*      */         } 
/*      */         
/*  865 */         dc.writeI32(pos - startPos, buffer, responseRecOffsetPos, swap);
/*      */         
/*  867 */         for (int i = 0; i < this.recsPresent; i++) {
/*  868 */           if (this.responseRecords[i] == null) {
/*  869 */             this.responseRecords[i] = this.env.newMQRR();
/*      */           }
/*  871 */           pos = this.responseRecords[i].writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  877 */     if (objectStringPos > 0) {
/*  878 */       pos = this.objectString.writeToBuffer(buffer, startPos, objectStringPos, pos, ptrSize, swap, cp);
/*      */     }
/*      */ 
/*      */     
/*  882 */     if (selectionStringPos > 0) {
/*  883 */       pos = this.selectionString.writeToBuffer(buffer, startPos, selectionStringPos, pos, ptrSize, swap, cp);
/*      */     }
/*      */ 
/*      */     
/*  887 */     if (resObjectStringPos > 0) {
/*  888 */       pos = this.resObjectString.writeToBuffer(buffer, startPos, resObjectStringPos, pos, ptrSize, swap, cp);
/*      */     }
/*      */     
/*  891 */     if (Trace.isOn) {
/*  892 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/*  893 */           Integer.valueOf(pos));
/*      */     }
/*  895 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  903 */     if (Trace.isOn) {
/*  904 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  906 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*  908 */     int pos = readFromBuffer(buffer, offset, ptrSize, swap, cp, tls, null);
/*  909 */     if (Trace.isOn) {
/*  910 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", pos);
/*      */     }
/*      */ 
/*      */     
/*  914 */     return pos;
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
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls, Pint optionsOffset) throws JmqiException {
/*  929 */     if (Trace.isOn) {
/*  930 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  932 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls, optionsOffset });
/*      */     }
/*      */     
/*  935 */     int pos = offset;
/*  936 */     int startPos = offset;
/*  937 */     int objectRecOffset = -1;
/*  938 */     int responseRecOffset = -1;
/*  939 */     int variableDataEnd = -1;
/*  940 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/*  942 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/*  943 */     if (!strucId.equals("OD  ")) {
/*      */       
/*  945 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2044, null);
/*  946 */       if (Trace.isOn) {
/*  947 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQOD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*      */       }
/*      */       
/*  950 */       throw traceRet1;
/*      */     } 
/*  952 */     pos += 4;
/*      */ 
/*      */     
/*  955 */     this.version = dc.readI32(buffer, pos, swap);
/*  956 */     pos += 4;
/*      */ 
/*      */     
/*  959 */     this.objectType = dc.readI32(buffer, pos, swap);
/*  960 */     pos += 4;
/*      */ 
/*      */     
/*  963 */     this.objectName = dc.readMQField(buffer, pos, 48, cp, tls);
/*  964 */     pos += 48;
/*      */ 
/*      */     
/*  967 */     this.objectQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/*  968 */     pos += 48;
/*      */ 
/*      */     
/*  971 */     this.dynamicQName = dc.readMQField(buffer, pos, 48, cp, tls);
/*  972 */     pos += 48;
/*      */ 
/*      */     
/*  975 */     this.alternateUserId = dc.readMQField(buffer, pos, 12, cp, tls);
/*  976 */     pos += 12;
/*      */ 
/*      */     
/*  979 */     if (this.version >= 2) {
/*      */ 
/*      */       
/*  982 */       this.recsPresent = dc.readI32(buffer, pos, swap);
/*  983 */       pos += 4;
/*      */ 
/*      */       
/*  986 */       this.knownDestCount = dc.readI32(buffer, pos, swap);
/*  987 */       pos += 4;
/*      */ 
/*      */       
/*  990 */       this.unknownDestCount = dc.readI32(buffer, pos, swap);
/*  991 */       pos += 4;
/*      */ 
/*      */       
/*  994 */       this.invalidDestCount = dc.readI32(buffer, pos, swap);
/*  995 */       pos += 4;
/*      */ 
/*      */       
/*  998 */       objectRecOffset = dc.readI32(buffer, pos, swap);
/*  999 */       pos += 4;
/* 1000 */       if (objectRecOffset != 0 && this.recsPresent > 0) {
/*      */         
/* 1002 */         if (this.objectRecords == null) {
/* 1003 */           this.objectRecords = new MQOR[this.recsPresent];
/*      */         
/*      */         }
/* 1006 */         else if (this.recsPresent > this.objectRecords.length) {
/* 1007 */           MQOR[] newObjectRecords = new MQOR[this.recsPresent];
/* 1008 */           System.arraycopy(this.objectRecords, 0, newObjectRecords, 0, this.objectRecords.length);
/* 1009 */           this.objectRecords = newObjectRecords;
/*      */         } 
/*      */         
/* 1012 */         int orEnd = startPos + objectRecOffset;
/* 1013 */         for (int i = 0; i < this.recsPresent; i++) {
/* 1014 */           if (this.objectRecords[i] == null) {
/* 1015 */             this.objectRecords[i] = this.env.newMQOR();
/*      */           }
/* 1017 */           orEnd = this.objectRecords[i].readFromBuffer(buffer, orEnd, ptrSize, swap, cp, tls);
/*      */         } 
/*      */         
/* 1020 */         if (orEnd > variableDataEnd) {
/* 1021 */           variableDataEnd = orEnd;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1026 */       responseRecOffset = dc.readI32(buffer, pos, swap);
/* 1027 */       pos += 4;
/* 1028 */       if (responseRecOffset != 0 && this.recsPresent > 0) {
/*      */         
/* 1030 */         if (this.responseRecords == null) {
/* 1031 */           this.responseRecords = new MQRR[this.recsPresent];
/*      */         
/*      */         }
/* 1034 */         else if (this.recsPresent > this.responseRecords.length) {
/* 1035 */           MQRR[] newResponseRecords = new MQRR[this.recsPresent];
/* 1036 */           System.arraycopy(this.responseRecords, 0, newResponseRecords, 0, this.responseRecords.length);
/* 1037 */           this.responseRecords = newResponseRecords;
/*      */         } 
/*      */         
/* 1040 */         int rrEnd = startPos + responseRecOffset;
/* 1041 */         for (int i = 0; i < this.recsPresent; i++) {
/* 1042 */           if (this.responseRecords[i] == null) {
/* 1043 */             this.responseRecords[i] = this.env.newMQRR();
/*      */           }
/* 1045 */           rrEnd = this.responseRecords[i].readFromBuffer(buffer, rrEnd, ptrSize, swap, cp, tls);
/*      */         } 
/*      */         
/* 1048 */         if (rrEnd > variableDataEnd) {
/* 1049 */           variableDataEnd = rrEnd;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1054 */       pos += ptrSize + ptrSize;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1059 */     if (this.version >= 3) {
/*      */       
/* 1061 */       System.arraycopy(buffer, pos, this.alternateSecurityId, 0, 40);
/* 1062 */       pos += 40;
/*      */ 
/*      */       
/* 1065 */       this.resolvedQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 1066 */       pos += 48;
/*      */ 
/*      */       
/* 1069 */       this.resolvedQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 1070 */       pos += 48;
/*      */ 
/*      */       
/* 1073 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1074 */       pos += padding;
/*      */     } 
/*      */ 
/*      */     
/* 1078 */     if (this.version >= 4) {
/*      */ 
/*      */       
/* 1081 */       pos = this.objectString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 1082 */       int osEnd = this.objectString.getEndPosAligned(startPos);
/* 1083 */       if (osEnd > variableDataEnd) {
/* 1084 */         variableDataEnd = osEnd;
/*      */       }
/*      */ 
/*      */       
/* 1088 */       pos = this.selectionString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 1089 */       int ssEnd = this.selectionString.getEndPosAligned(startPos);
/* 1090 */       if (ssEnd > variableDataEnd) {
/* 1091 */         variableDataEnd = ssEnd;
/*      */       }
/*      */ 
/*      */       
/* 1095 */       pos = this.resObjectString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 1096 */       int rsEnd = this.resObjectString.getEndPosAligned(startPos);
/*      */ 
/*      */ 
/*      */       
/* 1100 */       if (rsEnd > variableDataEnd) {
/* 1101 */         if (optionsOffset != null) {
/* 1102 */           optionsOffset.x = variableDataEnd;
/*      */         }
/* 1104 */         variableDataEnd = rsEnd;
/*      */       } 
/*      */ 
/*      */       
/* 1108 */       this.resolvedType = dc.readI32(buffer, pos, swap);
/* 1109 */       pos += 4;
/*      */ 
/*      */       
/* 1112 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1113 */       pos += padding;
/*      */     } 
/*      */ 
/*      */     
/* 1117 */     if (variableDataEnd > pos) {
/* 1118 */       pos = variableDataEnd;
/*      */     }
/*      */     
/* 1121 */     if (Trace.isOn) {
/* 1122 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1123 */           Integer.valueOf(pos));
/*      */     }
/* 1125 */     return pos;
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
/* 1137 */     fmt.add("version", this.version);
/* 1138 */     fmt.add("objectType", this.objectType);
/* 1139 */     fmt.add("objectName", this.objectName);
/* 1140 */     fmt.add("objectQMgrName", this.objectQMgrName);
/* 1141 */     fmt.add("dynamicQName", this.dynamicQName);
/* 1142 */     fmt.add("alternateUserId", this.alternateUserId);
/* 1143 */     fmt.add("recsPresent", this.recsPresent);
/* 1144 */     fmt.add("knownDestCount", this.knownDestCount);
/* 1145 */     fmt.add("unknownDestCount", this.unknownDestCount);
/* 1146 */     fmt.add("invalidDestCount", this.invalidDestCount);
/* 1147 */     fmt.add("objectRecords", this.objectRecords);
/* 1148 */     fmt.add("responseRecords", this.responseRecords);
/* 1149 */     fmt.add("alternateSecurityId", this.alternateSecurityId);
/* 1150 */     fmt.add("resolvedQName", this.resolvedQName);
/* 1151 */     fmt.add("resolvedQMgrName", this.resolvedQMgrName);
/* 1152 */     fmt.add("objectString", this.objectString);
/* 1153 */     fmt.add("selectionString", this.selectionString);
/* 1154 */     fmt.add("resObjectString", this.resObjectString);
/* 1155 */     fmt.add("resolvedType", this.resolvedType);
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
/*      */   public MQOD cloneForReconnect() {
/* 1170 */     MQOD theClone = new MQOD(this.env);
/* 1171 */     theClone.version = this.version;
/* 1172 */     theClone.objectType = this.objectType;
/*      */     
/* 1174 */     theClone.objectName = this.objectName;
/* 1175 */     theClone.objectString = this.objectString;
/* 1176 */     theClone.objectQMgrName = this.objectQMgrName;
/* 1177 */     theClone.dynamicQName = this.dynamicQName;
/* 1178 */     theClone.selectionString = this.selectionString;
/*      */     
/* 1180 */     theClone.alternateUserId = this.alternateUserId;
/* 1181 */     theClone.alternateSecurityId = this.alternateSecurityId;
/*      */     
/* 1183 */     theClone.recsPresent = this.recsPresent;
/* 1184 */     theClone.responseRecords = this.responseRecords;
/*      */     
/* 1186 */     theClone.knownDestCount = 0;
/* 1187 */     theClone.unknownDestCount = 0;
/* 1188 */     theClone.invalidDestCount = 0;
/*      */     
/* 1190 */     theClone.resObjectString = this.resObjectString;
/* 1191 */     theClone.resolvedQMgrName = this.resolvedQMgrName;
/* 1192 */     theClone.resolvedQName = this.resolvedQMgrName;
/* 1193 */     theClone.resolvedType = this.resolvedType;
/*      */     
/* 1195 */     return theClone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getResolvedType() {
/* 1202 */     if (Trace.isOn) {
/* 1203 */       Trace.data(this, "com.ibm.mq.jmqi.MQOD", "getResolvedType()", "getter", 
/* 1204 */           Integer.valueOf(this.resolvedType));
/*      */     }
/* 1206 */     return this.resolvedType;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQOD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */