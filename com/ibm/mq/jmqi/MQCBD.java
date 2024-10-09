/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
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
/*     */ public class MQCBD
/*     */   extends AbstractMqiStructure
/*     */   implements Cloneable
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCBD.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_CALLBACK_TYPE = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_CALLBACK_NAME = 128;
/*     */   private static final int SIZEOF_MAX_MSG_LENGTH = 4;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.jmqi.MQCBD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCBD.java");
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
/*  75 */   private int version = 1;
/*  76 */   private int callbackType = 1;
/*  77 */   private int options = 0;
/*     */   private Object callbackArea;
/*     */   private MQConsumer callbackFunction;
/*     */   private String callbackName;
/*  81 */   private int maxMsgLength = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inhibitESE = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inhibitESE() {
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBD", "inhibitESE()");
/*     */     }
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBD", "inhibitESE()", Boolean.valueOf(this.inhibitESE));
/*     */     }
/*  98 */     return this.inhibitESE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inhibitESE(boolean inhibitESE1) {
/* 105 */     if (Trace.isOn)
/* 106 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBD", "inhibitESE(boolean)", new Object[] {
/* 107 */             Boolean.valueOf(inhibitESE1)
/*     */           }); 
/* 109 */     this.inhibitESE = inhibitESE1;
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBD", "inhibitESE(boolean)");
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
/*     */   public static int getSizeV1(int ptrSize) {
/* 124 */     int size = 16 + 2 * ptrSize + 128 + 4;
/*     */ 
/*     */ 
/*     */     
/* 128 */     int padding = JmqiTools.alignToGrain(ptrSize, size);
/* 129 */     size += padding;
/*     */     
/* 131 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/* 144 */     int size = 0;
/* 145 */     switch (version) {
/*     */       case 1:
/* 147 */         size = getSizeV1(sizeofNativePointer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 155 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2444, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCBD(JmqiEnvironment env) {
/* 164 */     super(env);
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBD", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBD", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCallbackType() {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getCallbackType()", "getter", 
/* 180 */           Integer.valueOf(this.callbackType));
/*     */     }
/* 182 */     return this.callbackType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallbackType(int callbackType) {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setCallbackType(int)", "setter", 
/* 193 */           Integer.valueOf(callbackType));
/*     */     }
/* 195 */     this.callbackType = callbackType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/*     */     
/* 206 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setOptions(int)", "setter", 
/* 217 */           Integer.valueOf(options));
/*     */     }
/* 219 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 231 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setVersion(int)", "setter", 
/* 243 */           Integer.valueOf(version));
/*     */     }
/* 245 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCallbackArea() {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getCallbackArea()", "getter", this.callbackArea);
/*     */     }
/* 255 */     return this.callbackArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallbackArea(Object callbackArea) {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setCallbackArea(Object)", "setter", callbackArea);
/*     */     }
/*     */     
/* 268 */     this.callbackArea = callbackArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCallbackName() {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getCallbackName()", "getter", this.callbackName);
/*     */     }
/* 278 */     return this.callbackName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallbackName(String callbackName) {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setCallbackName(String)", "setter", callbackName);
/*     */     }
/*     */     
/* 291 */     this.callbackName = callbackName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxMsgLength() {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getMaxMsgLength()", "getter", 
/* 300 */           Integer.valueOf(this.maxMsgLength));
/*     */     }
/* 302 */     return this.maxMsgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxMsgLength(int maxMsgLength) {
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setMaxMsgLength(int)", "setter", 
/* 313 */           Integer.valueOf(maxMsgLength));
/*     */     }
/* 315 */     this.maxMsgLength = maxMsgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConsumer getCallbackFunction() {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "getCallbackFunction()", "getter", this.callbackFunction);
/*     */     }
/*     */     
/* 326 */     return this.callbackFunction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallbackFunction(MQConsumer mqConsumer) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBD", "setCallbackFunction(MQConsumer)", "setter", mqConsumer);
/*     */     }
/*     */     
/* 339 */     this.callbackFunction = mqConsumer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 348 */     return getSize(this.env, this.version, ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 360 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 362 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 363 */     int pos = offset;
/*     */     
/* 365 */     dc.writeMQField("CBD ", buffer, pos, 4, cp, tls);
/* 366 */     pos += 4;
/*     */     
/* 368 */     dc.writeI32(this.version, buffer, pos, swap);
/* 369 */     pos += 4;
/*     */     
/* 371 */     dc.writeI32(this.callbackType, buffer, pos, swap);
/* 372 */     pos += 4;
/*     */     
/* 374 */     dc.writeI32(this.options, buffer, pos, swap);
/* 375 */     pos += 4;
/*     */     
/* 377 */     dc.clear(buffer, pos, ptrSize);
/* 378 */     pos += ptrSize;
/*     */ 
/*     */     
/* 381 */     dc.clear(buffer, pos, ptrSize);
/* 382 */     pos += ptrSize;
/*     */     
/* 384 */     dc.writeNullTerminatedField(this.callbackName, buffer, pos, 128, cp, tls);
/* 385 */     pos += 128;
/*     */     
/* 387 */     dc.writeI32(this.maxMsgLength, buffer, pos, swap);
/* 388 */     pos += 4;
/*     */     
/* 390 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 391 */     dc.clear(buffer, pos, padding);
/* 392 */     pos += padding;
/*     */     
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 396 */           Integer.valueOf(pos));
/*     */     }
/* 398 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 410 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 412 */     int pos = offset;
/* 413 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 415 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 416 */     if (!strucId.equals("CBD ")) {
/*     */       
/* 418 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2444, null);
/*     */       
/* 420 */       if (Trace.isOn) {
/* 421 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCBD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 424 */       throw traceRet1;
/*     */     } 
/* 426 */     pos += 4;
/*     */ 
/*     */     
/* 429 */     pos += 12 + ptrSize + ptrSize + 128 + 4;
/*     */ 
/*     */ 
/*     */     
/* 433 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 434 */     pos += padding;
/*     */     
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 438 */           Integer.valueOf(pos));
/*     */     }
/* 440 */     return pos;
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
/* 452 */     fmt.add("version", this.version);
/* 453 */     fmt.add("callbackType", this.callbackType);
/* 454 */     fmt.add("options", this.options);
/* 455 */     fmt.add("callbackArea", this.callbackArea);
/* 456 */     fmt.add("callbackName", this.callbackName);
/* 457 */     fmt.add("maxMsgLength", this.maxMsgLength);
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
/*     */   public Object clone() throws CloneNotSupportedException {
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBD", "clone()");
/*     */     }
/* 472 */     Object traceRet1 = super.clone();
/*     */     
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBD", "clone()", traceRet1);
/*     */     }
/* 477 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCBD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */