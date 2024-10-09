/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCHARV;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
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
/*     */ public class LpiSRD
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiSRD.java";
/*     */   public static final String lpiSRD_STRUCID = "LSRD";
/*     */   public static final int lpiSRD_VERSION_1 = 1;
/*     */   public static final int lpiSRD_CURRENT_VERSION = 1;
/*     */   public static final int lpiSR_ACTION_PUBLICATION = 1;
/*     */   public static final int lpiSRO_NONE = 0;
/*     */   public static final int lpiSRO_SCOPE_GLOBAL = 1;
/*     */   public static final int lpiSRO_ALTERNATE_USER_AUTHORITY = 2;
/*     */   public static final int lpiSRD_LENGTH_1_32BIT = 112;
/*     */   public static final int lpiSRD_LENGTH_1_64BIT = 120;
/*     */   public static final int lpiSRD_LENGTH_1_128BIT = 144;
/*     */   public static final int lpiSRD_CURRENT_LENGTH_32BIT = 112;
/*     */   public static final int lpiSRD_CURRENT_LENGTH_64BIT = 120;
/*     */   public static final int lpiSRD_CURRENT_LENGTH_128BIT = 144;
/*     */   private static final int SIZEOF_STRUC_ID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_ALTERNATE_USER_ID = 12;
/*     */   private static final int SIZEOF_ALTERNATE_SECURITY_ID = 40;
/*     */   private static final int SIZEOF_SUB_ID = 24;
/*     */   private static final int SIZEOF_SUB_NAME = 20;
/*     */   private static final int SIZEOF_ACTION_COUNTER = 4;
/* 158 */   private int version = 1;
/* 159 */   private int options = 0;
/* 160 */   private String alternateUserId = "";
/* 161 */   private byte[] alternateSecurityId = CMQC.MQSID_NONE;
/* 162 */   private byte[] subId = CMQC.MQCI_NONE;
/*     */   private MQCHARV subName;
/* 164 */   private int actionCounter = 0;
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */ 
/*     */   
/*     */   protected LpiSRD(JmqiEnvironment env) {
/* 172 */     super(env);
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSRD", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */ 
/*     */     
/* 178 */     this.subName = env.newMQCHARV();
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSRD", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentVersion() {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getCurrentVersion()", "getter", 
/* 193 */           Integer.valueOf(1));
/*     */     }
/* 195 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(JmqiEnvironment env, int ptrSize) {
/* 206 */     int size = 0;
/* 207 */     size += 4;
/* 208 */     size += 4;
/* 209 */     size += 4;
/* 210 */     size += 12;
/* 211 */     size += 40;
/* 212 */     size += 24;
/* 213 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 214 */     size += 20;
/* 215 */     size += 4;
/*     */     
/* 217 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/* 228 */     int size = getFieldSizeV1(env, ptrSize);
/* 229 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 231 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(int ptrSize) throws JmqiException {
/* 242 */     if (Trace.isOn)
/* 243 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSRD", "getSize(int)", new Object[] {
/* 244 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 246 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSRD", "getSize(int)", Integer.valueOf(traceRet1));
/*     */     }
/* 250 */     return traceRet1;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/* 266 */     switch (version) {
/*     */       case 1:
/* 268 */         size = getSizeV1(env, ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 277 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 6128, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 290 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 293 */     size += this.subName.getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 295 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredInputBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSRD", "getRequiredInputBufferSize(int,JmqiCodepage)", new Object[] {
/* 308 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/*     */     
/* 312 */     int size = getSize(ptrSize);
/*     */ 
/*     */     
/* 315 */     size += this.subName.getRequiredInputBufferSize(ptrSize, cp);
/*     */     
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSRD", "getRequiredInputBufferSize(int,JmqiCodepage)", 
/* 319 */           Integer.valueOf(size));
/*     */     }
/* 321 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaximumRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSRD", "getMaximumRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 334 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 339 */     int size = getSize(ptrSize);
/*     */ 
/*     */     
/* 342 */     size += 10240;
/*     */     
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSRD", "getMaximumRequiredBufferSize(int,JmqiCodepage)", 
/* 346 */           Integer.valueOf(size));
/*     */     }
/* 348 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getVersion()", "getter", 
/* 358 */           Integer.valueOf(this.version));
/*     */     }
/* 360 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "setVersion(int)", "setter", 
/* 370 */           Integer.valueOf(version));
/*     */     }
/* 372 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getOptions()", "getter", 
/* 381 */           Integer.valueOf(this.options));
/*     */     }
/* 383 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "setOptions(int)", "setter", 
/* 392 */           Integer.valueOf(options));
/*     */     }
/* 394 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateUserId() {
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getAlternateUserId()", "getter", this.alternateUserId);
/*     */     }
/*     */     
/* 405 */     return this.alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateUserId(String alternateUserId) {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "setAlternateUserId(String)", "setter", alternateUserId);
/*     */     }
/*     */     
/* 416 */     this.alternateUserId = alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAlternateSecurityId() {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getAlternateSecurityId()", "getter", this.alternateSecurityId);
/*     */     }
/*     */     
/* 427 */     return this.alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateSecurityId(byte[] alternateSecurityId) {
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "setAlternateSecurityId(byte [ ])", "setter", alternateSecurityId);
/*     */     }
/*     */     
/* 438 */     this.alternateSecurityId = alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSubId() {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getSubId()", "getter", this.subId);
/*     */     }
/* 448 */     return this.subId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubId(byte[] subId) {
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "setSubId(byte [ ])", "setter", subId);
/*     */     }
/* 458 */     this.subId = subId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubName() {
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getSubName()", "getter", this.subName);
/*     */     }
/* 468 */     return this.subName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActionCounter() {
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "getActionCounter()", "getter", 
/* 477 */           Integer.valueOf(this.actionCounter));
/*     */     }
/* 479 */     return this.actionCounter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionCounter(int actionCounter) {
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSRD", "setActionCounter(int)", "setter", 
/* 488 */           Integer.valueOf(actionCounter));
/*     */     }
/* 490 */     this.actionCounter = actionCounter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 498 */     if (Trace.isOn) {
/* 499 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSRD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 501 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 504 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 505 */     int pos = offset;
/* 506 */     int startPos = pos;
/*     */     
/* 508 */     int subNamePos = -1;
/*     */ 
/*     */     
/* 511 */     dc.writeMQField("LSRD", buffer, pos, 4, cp, tls);
/* 512 */     pos += 4;
/*     */ 
/*     */     
/* 515 */     dc.writeI32(this.version, buffer, pos, swap);
/* 516 */     pos += 4;
/*     */ 
/*     */     
/* 519 */     dc.writeI32(this.options, buffer, pos, swap);
/* 520 */     pos += 4;
/*     */ 
/*     */     
/* 523 */     dc.writeField(this.alternateUserId, buffer, pos, 12, cp, tls);
/* 524 */     pos += 12;
/*     */ 
/*     */     
/* 527 */     System.arraycopy(this.alternateSecurityId, 0, buffer, pos, 40);
/* 528 */     pos += 40;
/*     */ 
/*     */     
/* 531 */     System.arraycopy(this.subId, 0, buffer, pos, 24);
/* 532 */     pos += 24;
/*     */ 
/*     */     
/* 535 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 536 */     dc.clear(buffer, pos, padding);
/* 537 */     pos += padding;
/*     */ 
/*     */     
/* 540 */     subNamePos = pos;
/* 541 */     pos = MQCHARV.getSize(ptrSize);
/*     */ 
/*     */     
/* 544 */     dc.writeI32(this.actionCounter, buffer, pos, swap);
/* 545 */     pos += 4;
/*     */ 
/*     */     
/* 548 */     if (this.version == 1) {
/* 549 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 550 */       dc.clear(buffer, pos, padding);
/* 551 */       pos += padding;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 556 */     pos = this.subName.writeToBuffer(buffer, startPos, subNamePos, pos, ptrSize, swap, cp);
/*     */     
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSRD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 560 */           Integer.valueOf(pos));
/*     */     }
/* 562 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 570 */     if (Trace.isOn) {
/* 571 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSRD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 573 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 576 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 577 */     int pos = offset;
/* 578 */     int startPos = pos;
/*     */     
/* 580 */     int variableDataEnd = 0;
/*     */ 
/*     */ 
/*     */     
/* 584 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 585 */     if (!strucId.equals("LSRD")) {
/*     */       
/* 587 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 588 */       if (Trace.isOn) {
/* 589 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LpiSRD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 592 */       throw traceRet1;
/*     */     } 
/* 594 */     pos += 4;
/*     */ 
/*     */     
/* 597 */     this.version = dc.readI32(buffer, pos, swap);
/* 598 */     pos += 4;
/*     */ 
/*     */     
/* 601 */     this.options = dc.readI32(buffer, pos, swap);
/* 602 */     pos += 4;
/*     */ 
/*     */     
/* 605 */     this.alternateUserId = dc.readField(buffer, pos, 12, cp, tls);
/* 606 */     pos += 12;
/*     */ 
/*     */     
/* 609 */     System.arraycopy(buffer, pos, this.alternateSecurityId, 0, 40);
/* 610 */     pos += 40;
/*     */ 
/*     */     
/* 613 */     System.arraycopy(buffer, pos, this.subId, 0, 24);
/* 614 */     pos += 24;
/*     */ 
/*     */     
/* 617 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 618 */     pos += padding;
/*     */ 
/*     */     
/* 621 */     pos = this.subName.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 622 */     int cvEnd = this.subName.getEndPosAligned(startPos);
/* 623 */     if (cvEnd > variableDataEnd) {
/* 624 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 628 */     this.actionCounter = dc.readI32(buffer, pos, swap);
/* 629 */     pos += 4;
/*     */ 
/*     */     
/* 632 */     if (this.version == 1) {
/* 633 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 634 */       pos += padding;
/*     */     } 
/*     */ 
/*     */     
/* 638 */     if (variableDataEnd > pos) {
/* 639 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 642 */     if (Trace.isOn) {
/* 643 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSRD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 644 */           Integer.valueOf(pos));
/*     */     }
/* 646 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 658 */     fmt.add("version", this.version);
/* 659 */     fmt.add("options", this.options);
/* 660 */     String optionDescription = formatOptions(this.options, getFields(), "lpiSRO_");
/* 661 */     fmt.add("option flags", optionDescription);
/* 662 */     fmt.add("alternateUserId", this.alternateUserId);
/* 663 */     fmt.add("alternateSecurityId", this.alternateSecurityId);
/* 664 */     fmt.add("subId", this.subId);
/* 665 */     fmt.add("subName", this.subName);
/* 666 */     fmt.add("actionCounter", this.actionCounter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 676 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 679 */     if (ref == null || (fields = ref.get()) == null) {
/* 680 */       fieldsRef = (Reference)new SoftReference<>(fields = LpiSRD.class.getFields());
/*     */     }
/*     */     
/* 683 */     if (Trace.isOn) {
/* 684 */       Trace.data("com.ibm.mq.jmqi.system.LpiSRD", "getFields()", "getter", fields);
/*     */     }
/* 686 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiSRD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */