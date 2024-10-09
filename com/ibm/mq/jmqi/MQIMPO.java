/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
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
/*     */ public class MQIMPO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQIMPO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_REQUESTED_ENCODING = 4;
/*     */   private static final int SIZEOF_REQUESTED_CCSID = 4;
/*     */   private static final int SIZEOF_RETURNED_ENCODING = 4;
/*     */   private static final int SIZEOF_RETURNED_CCSID = 4;
/*     */   private static final int SIZEOF_RESERVED1 = 4;
/*     */   private static final int SIZEOF_TYPE_STRING = 8;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.mq.jmqi.MQIMPO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQIMPO.java");
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
/*  79 */   private int version = 1;
/*  80 */   private int options = 0;
/*  81 */   private int requestedEncoding = 273;
/*  82 */   private int requestedCCSID = -3;
/*  83 */   private int returnedEncoding = 273;
/*  84 */   private int returnedCCSID = 0;
/*  85 */   private int reserved1 = 0;
/*  86 */   private MQCHARV returnedName = null;
/*  87 */   private String typeString = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/*  98 */     int size = 32 + MQCHARV.getSize(ptrSize) + 8;
/*     */ 
/*     */ 
/*     */     
/* 102 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/* 117 */     int size = 0;
/* 118 */     switch (version) {
/*     */       case 1:
/* 120 */         size = getSizeV1(sizeofNativePointer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2464, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQIMPO(JmqiEnvironment env) {
/* 136 */     super(env);
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIMPO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 140 */     this.returnedName = env.newMQCHARV();
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIMPO", "<init>(JmqiEnvironment)");
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
/*     */   public int getVersion() {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getVersion()", "getter", 
/* 157 */           Integer.valueOf(this.version));
/*     */     }
/* 159 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setVersion(int)", "setter", 
/* 171 */           Integer.valueOf(version));
/*     */     }
/* 173 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getOptions()", "getter", 
/* 184 */           Integer.valueOf(this.options));
/*     */     }
/* 186 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setOptions(int)", "setter", 
/* 197 */           Integer.valueOf(options));
/*     */     }
/* 199 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequestedEncoding() {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getRequestedEncoding()", "getter", 
/* 210 */           Integer.valueOf(this.requestedEncoding));
/*     */     }
/* 212 */     return this.requestedEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequestedEncoding(int requestedEncoding) {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setRequestedEncoding(int)", "setter", 
/* 223 */           Integer.valueOf(requestedEncoding));
/*     */     }
/* 225 */     this.requestedEncoding = requestedEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequestedCCSID() {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getRequestedCCSID()", "getter", 
/* 236 */           Integer.valueOf(this.requestedCCSID));
/*     */     }
/* 238 */     return this.requestedCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequestedCCSID(int requestedCCSID) {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setRequestedCCSID(int)", "setter", 
/* 249 */           Integer.valueOf(requestedCCSID));
/*     */     }
/* 251 */     this.requestedCCSID = requestedCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReturnedEncoding() {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getReturnedEncoding()", "getter", 
/* 262 */           Integer.valueOf(this.returnedEncoding));
/*     */     }
/* 264 */     return this.returnedEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnedEncoding(int returnedEncoding) {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setReturnedEncoding(int)", "setter", 
/* 275 */           Integer.valueOf(returnedEncoding));
/*     */     }
/* 277 */     this.returnedEncoding = returnedEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReturnedCCSID() {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getReturnedCCSID()", "getter", 
/* 288 */           Integer.valueOf(this.returnedCCSID));
/*     */     }
/* 290 */     return this.returnedCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnedCCSID(int returnedCCSID) {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setReturnedCCSID(int)", "setter", 
/* 301 */           Integer.valueOf(returnedCCSID));
/*     */     }
/* 303 */     this.returnedCCSID = returnedCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReserved1() {
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getReserved1()", "getter", 
/* 314 */           Integer.valueOf(this.reserved1));
/*     */     }
/* 316 */     return this.reserved1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved1(int reserved1) {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setReserved1(int)", "setter", 
/* 327 */           Integer.valueOf(reserved1));
/*     */     }
/* 329 */     this.reserved1 = reserved1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getReturnedName() {
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getReturnedName()", "getter", this.returnedName);
/*     */     }
/* 341 */     return this.returnedName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnedName(MQCHARV returnedName) {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setReturnedName(MQCHARV)", "setter", returnedName);
/*     */     }
/*     */     
/* 354 */     this.returnedName = returnedName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "getTypeString()", "getter", this.typeString);
/*     */     }
/* 366 */     return this.typeString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTypeString(String typeString) {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.data(this, "com.ibm.mq.jmqi.MQIMPO", "setTypeString(String)", "setter", typeString);
/*     */     }
/* 378 */     this.typeString = typeString;
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
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 390 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 393 */     size += this.returnedName.getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 395 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIMPO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 408 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 410 */     int returnedNamePos = -1;
/* 411 */     int pos = offset;
/* 412 */     int startPos = pos;
/* 413 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 415 */     dc.writeMQField("IMPO", buffer, pos, 4, cp, tls);
/* 416 */     pos += 4;
/*     */     
/* 418 */     dc.writeI32(this.version, buffer, pos, swap);
/* 419 */     pos += 4;
/*     */     
/* 421 */     dc.writeI32(this.options, buffer, pos, swap);
/* 422 */     pos += 4;
/*     */     
/* 424 */     dc.writeI32(this.requestedEncoding, buffer, pos, swap);
/* 425 */     pos += 4;
/*     */     
/* 427 */     dc.writeI32(this.requestedCCSID, buffer, pos, swap);
/* 428 */     pos += 4;
/*     */     
/* 430 */     dc.writeI32(this.returnedEncoding, buffer, pos, swap);
/* 431 */     pos += 4;
/*     */     
/* 433 */     dc.writeI32(this.returnedCCSID, buffer, pos, swap);
/* 434 */     pos += 4;
/*     */     
/* 436 */     dc.writeI32(this.reserved1, buffer, pos, swap);
/* 437 */     pos += 4;
/*     */ 
/*     */     
/* 440 */     returnedNamePos = pos;
/* 441 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 443 */     dc.writeMQField(this.typeString, buffer, pos, 8, cp, tls);
/* 444 */     pos += 8;
/*     */     
/* 446 */     pos = this.returnedName.writeToBuffer(buffer, startPos, returnedNamePos, pos, ptrSize, swap, cp);
/*     */     
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIMPO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 450 */           Integer.valueOf(pos));
/*     */     }
/* 452 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIMPO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 466 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 468 */     int pos = offset;
/* 469 */     int startPos = pos;
/* 470 */     int variableDataEnd = 0;
/*     */     
/* 472 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 474 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 475 */     if (!strucId.equals("IMPO")) {
/*     */       
/* 477 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 2464, null);
/*     */ 
/*     */       
/* 480 */       if (Trace.isOn) {
/* 481 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQIMPO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", ex);
/*     */       }
/*     */       
/* 484 */       throw ex;
/*     */     } 
/* 486 */     pos += 4;
/*     */ 
/*     */     
/* 489 */     pos += 8;
/*     */ 
/*     */     
/* 492 */     this.requestedEncoding = dc.readI32(buffer, pos, swap);
/* 493 */     pos += 4;
/*     */ 
/*     */     
/* 496 */     this.requestedCCSID = dc.readI32(buffer, pos, swap);
/* 497 */     pos += 4;
/*     */ 
/*     */     
/* 500 */     this.returnedEncoding = dc.readI32(buffer, pos, swap);
/* 501 */     pos += 4;
/*     */ 
/*     */     
/* 504 */     this.returnedCCSID = dc.readI32(buffer, pos, swap);
/* 505 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 510 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 515 */     pos = this.returnedName.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 516 */     int cvEnd = this.returnedName.getEndPosAligned(startPos);
/* 517 */     if (cvEnd > variableDataEnd) {
/* 518 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 522 */     this.typeString = dc.readMQField(buffer, pos, 8, cp, tls);
/* 523 */     pos += 8;
/*     */ 
/*     */     
/* 526 */     if (variableDataEnd > pos) {
/* 527 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 530 */     if (Trace.isOn) {
/* 531 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIMPO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 532 */           Integer.valueOf(pos));
/*     */     }
/* 534 */     return pos;
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
/* 546 */     fmt.add("Version", this.version);
/* 547 */     fmt.add("Options", this.options);
/* 548 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQDMPO_.*");
/* 549 */     fmt.add("option flags", optionDescription);
/* 550 */     fmt.add("RequestedEncoding", this.requestedEncoding);
/* 551 */     fmt.add("RequestedCCSID", this.requestedCCSID);
/* 552 */     fmt.add("ReturnedEncoding", this.returnedEncoding);
/* 553 */     fmt.add("ReturnedCCSID", this.returnedCCSID);
/*     */     
/* 555 */     fmt.add("ReturnedName", this.returnedName);
/* 556 */     fmt.add("TypeString", this.typeString);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQIMPO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */