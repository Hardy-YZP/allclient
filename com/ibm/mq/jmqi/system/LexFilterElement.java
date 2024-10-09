/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
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
/*     */ public class LexFilterElement
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LexFilterElement.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_TYPE = 4;
/*     */   private static final int SIZEOF_PARAMETER = 4;
/*     */   private static final int SIZEOF_OPERATOR = 4;
/*     */   private static final int SIZEOF_PARAMETER_SIZE = 4;
/*     */   private static final int SIZEOF_FILTER_VALUE_LENGTH = 4;
/*     */   private static final int SIZEOF_FILTER_INT_VALUE = 4;
/*     */   private static final int SIZEOF_FILTER_CCSID = 4;
/*     */   public static final String lexFLT_STRUC_ID = "LFLT";
/*     */   public static final int lexFLT_VERSION_1 = 1;
/*  75 */   private int version = 1;
/*  76 */   private int type = 0;
/*  77 */   private int parameter = 0;
/*  78 */   private int operator = 0;
/*  79 */   private int parameterSize = 0;
/*  80 */   private int filterIntValue = 0;
/*  81 */   private int filterCCSID = 0;
/*  82 */   private String filterStringValue = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] filterStringValue_cachedBytes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexFilterElement(JmqiEnvironment env) {
/*  95 */     super(env);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexFilterElement", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexFilterElement", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 110 */     if (Trace.isOn)
/* 111 */       Trace.entry("com.ibm.mq.jmqi.system.LexFilterElement", "getFieldSizeV1(int)", new Object[] {
/* 112 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 114 */     int size = 0;
/* 115 */     size += 4;
/* 116 */     size += 4;
/* 117 */     size += 4;
/* 118 */     size += 4;
/* 119 */     size += 4;
/* 120 */     size += 4;
/* 121 */     size += 4;
/* 122 */     size += 4;
/* 123 */     size += 4;
/* 124 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 125 */     size += ptrSize;
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit("com.ibm.mq.jmqi.system.LexFilterElement", "getFieldSizeV1(int)", 
/* 129 */           Integer.valueOf(size));
/*     */     }
/* 131 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 139 */     if (Trace.isOn)
/* 140 */       Trace.entry("com.ibm.mq.jmqi.system.LexFilterElement", "getSizeV1(int)", new Object[] {
/* 141 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 143 */     int size = getFieldSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */     
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit("com.ibm.mq.jmqi.system.LexFilterElement", "getSizeV1(int)", 
/* 149 */           Integer.valueOf(size));
/*     */     }
/* 151 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     JmqiException e;
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry("com.ibm.mq.jmqi.system.LexFilterElement", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 164 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/* 166 */     int size = 0;
/* 167 */     switch (version) {
/*     */       case 1:
/* 169 */         size = getSizeV1(ptrSize);
/*     */         break;
/*     */       default:
/* 172 */         e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */ 
/*     */         
/* 175 */         if (Trace.isOn) {
/* 176 */           Trace.throwing("com.ibm.mq.jmqi.system.LexFilterElement", "getSize(JmqiEnvironment,int,int)", (Throwable)e);
/*     */         }
/*     */         
/* 179 */         throw e;
/*     */     } 
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit("com.ibm.mq.jmqi.system.LexFilterElement", "getSize(JmqiEnvironment,int,int)", 
/* 183 */           Integer.valueOf(size));
/*     */     }
/* 185 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 198 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 200 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 202 */     this.filterStringValue_cachedBytes = dc.getStrBytes(this.filterStringValue, cp);
/* 203 */     int size = getSize(this.env, this.version, ptrSize);
/* 204 */     size += this.filterStringValue_cachedBytes.length;
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 208 */           Integer.valueOf(size));
/*     */     }
/* 210 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getVersion()", "getter", 
/* 220 */           Integer.valueOf(this.version));
/*     */     }
/* 222 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setVersion(int)", "setter", 
/* 232 */           Integer.valueOf(version));
/*     */     }
/* 234 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getType()", "getter", 
/* 243 */           Integer.valueOf(this.type));
/*     */     }
/* 245 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setType(int)", "setter", 
/* 254 */           Integer.valueOf(type));
/*     */     }
/* 256 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getParameter()", "getter", 
/* 265 */           Integer.valueOf(this.parameter));
/*     */     }
/* 267 */     return this.parameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int parameter) {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setParameter(int)", "setter", 
/* 276 */           Integer.valueOf(parameter));
/*     */     }
/* 278 */     this.parameter = parameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getOperator()", "getter", 
/* 287 */           Integer.valueOf(this.operator));
/*     */     }
/* 289 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int operator) {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setOperator(int)", "setter", 
/* 298 */           Integer.valueOf(operator));
/*     */     }
/* 300 */     this.operator = operator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameterSize() {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getParameterSize()", "getter", 
/* 309 */           Integer.valueOf(this.parameterSize));
/*     */     }
/* 311 */     return this.parameterSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameterSize(int parameterSize) {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setParameterSize(int)", "setter", 
/* 320 */           Integer.valueOf(parameterSize));
/*     */     }
/* 322 */     this.parameterSize = parameterSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterIntValue() {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getFilterIntValue()", "getter", 
/* 331 */           Integer.valueOf(this.filterIntValue));
/*     */     }
/* 333 */     return this.filterIntValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterIntValue(int filterIntValue) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setFilterIntValue(int)", "setter", 
/* 342 */           Integer.valueOf(filterIntValue));
/*     */     }
/* 344 */     this.filterIntValue = filterIntValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterCCSID() {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getFilterCCSID()", "getter", 
/* 353 */           Integer.valueOf(this.filterCCSID));
/*     */     }
/* 355 */     return this.filterCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterCCSID(int filterCCSID) {
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setFilterCCSID(int)", "setter", 
/* 364 */           Integer.valueOf(filterCCSID));
/*     */     }
/* 366 */     this.filterCCSID = filterCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilterStringValue() {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "getFilterStringValue()", "getter", this.filterStringValue);
/*     */     }
/*     */     
/* 377 */     return this.filterStringValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterStringValue(String filterStringValue) {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexFilterElement", "setFilterStringValue(String)", "setter", filterStringValue);
/*     */     }
/*     */     
/* 388 */     this.filterStringValue = filterStringValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*     */     byte[] filterStringValueBytes;
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexFilterElement", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 400 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 402 */     int pos = offset;
/*     */     
/* 404 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 406 */     dc.writeMQField("LFLT", buffer, pos, 4, cp, tls);
/* 407 */     pos += 4;
/*     */     
/* 409 */     dc.writeI32(this.version, buffer, pos, swap);
/* 410 */     pos += 4;
/*     */     
/* 412 */     dc.writeI32(this.type, buffer, pos, swap);
/* 413 */     pos += 4;
/*     */     
/* 415 */     dc.writeI32(this.parameter, buffer, pos, swap);
/* 416 */     pos += 4;
/*     */     
/* 418 */     dc.writeI32(this.operator, buffer, pos, swap);
/* 419 */     pos += 4;
/*     */     
/* 421 */     dc.writeI32(this.parameterSize, buffer, pos, swap);
/* 422 */     pos += 4;
/*     */     
/* 424 */     int filterValueLengthPosition = pos;
/* 425 */     pos += 4;
/*     */     
/* 427 */     dc.writeI32(this.filterIntValue, buffer, pos, swap);
/* 428 */     pos += 4;
/*     */     
/* 430 */     dc.writeI32(this.filterCCSID, buffer, pos, swap);
/* 431 */     pos += 4;
/*     */     
/* 433 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 434 */     dc.clear(buffer, pos, padding);
/* 435 */     pos += padding;
/*     */     
/* 437 */     dc.clear(buffer, pos, ptrSize);
/* 438 */     pos += ptrSize;
/*     */     
/* 440 */     if (this.version == 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 450 */     if (this.filterStringValue_cachedBytes != null) {
/* 451 */       filterStringValueBytes = this.filterStringValue_cachedBytes;
/* 452 */       this.filterStringValue_cachedBytes = null;
/*     */     } else {
/*     */       
/* 455 */       filterStringValueBytes = dc.getStrBytes(this.filterStringValue, cp);
/*     */     } 
/* 457 */     dc.writeI32(filterStringValueBytes.length, buffer, filterValueLengthPosition, swap);
/* 458 */     System.arraycopy(filterStringValueBytes, 0, buffer, pos, filterStringValueBytes.length);
/* 459 */     pos += filterStringValueBytes.length;
/*     */     
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexFilterElement", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 463 */           Integer.valueOf(pos));
/*     */     }
/* 465 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexFilterElement", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 477 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 479 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 480 */     int pos = offset;
/*     */     
/* 482 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 483 */     if (!strucId.equals("LFLT")) {
/* 484 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */ 
/*     */       
/* 487 */       if (Trace.isOn) {
/* 488 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LexFilterElement", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 491 */       throw traceRet1;
/*     */     } 
/* 493 */     pos += 4;
/*     */ 
/*     */     
/* 496 */     this.version = dc.readI32(buffer, pos, swap);
/* 497 */     pos += 4;
/*     */ 
/*     */     
/* 500 */     this.type = dc.readI32(buffer, pos, swap);
/* 501 */     pos += 4;
/*     */ 
/*     */     
/* 504 */     this.parameter = dc.readI32(buffer, pos, swap);
/* 505 */     pos += 4;
/*     */ 
/*     */     
/* 508 */     this.operator = dc.readI32(buffer, pos, swap);
/* 509 */     pos += 4;
/*     */ 
/*     */     
/* 512 */     this.parameterSize = dc.readI32(buffer, pos, swap);
/* 513 */     pos += 4;
/*     */ 
/*     */     
/* 516 */     int filterValueLength = dc.readI32(buffer, pos, swap);
/* 517 */     pos += 4;
/*     */ 
/*     */     
/* 520 */     this.filterIntValue = dc.readI32(buffer, pos, swap);
/* 521 */     pos += 4;
/*     */ 
/*     */     
/* 524 */     this.filterCCSID = dc.readI32(buffer, pos, swap);
/* 525 */     pos += 4;
/*     */ 
/*     */     
/* 528 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 529 */     pos += padding;
/*     */ 
/*     */     
/* 532 */     pos += ptrSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 541 */     this.filterStringValue = dc.readField(buffer, pos, filterValueLength, cp, tls);
/* 542 */     pos += filterValueLength;
/*     */     
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexFilterElement", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 546 */           Integer.valueOf(pos));
/*     */     }
/* 548 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 559 */     fmt.add("version", this.version);
/* 560 */     fmt.add("type", this.type);
/* 561 */     fmt.add("parameter", this.parameter);
/* 562 */     fmt.add("operator", this.operator);
/* 563 */     fmt.add("parameterSize", this.parameterSize);
/* 564 */     fmt.add("filterIntValue", this.filterIntValue);
/* 565 */     fmt.add("filterCCSID", this.filterCCSID);
/* 566 */     fmt.add("filterStringValue", this.filterStringValue);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LexFilterElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */