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
/*     */ public class RXPB
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/RXPB.java";
/*     */   public static final int CONTEXT_TOKEN_LENGTH = 16;
/*     */   private static final int SIZEOF_HEXID = 2;
/*     */   private static final int SIZEOF_CBLEN = 2;
/*     */   private static final int SIZEOF_ID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_CTXTKN = 16;
/*     */   private static final int SIZEOF_FLAGS = 4;
/*     */   private static final int SIZEOF_PREV_CTXTKN = 16;
/*     */   private static final int SIZEOF_RESERVED = 12;
/*     */   private static final int SIZEOF_WAS_TRAN_ID = 8;
/*     */   private static final int SIZEOF_QMID = 4;
/*     */   private static final int SIZEOF_RESERVED2 = 4;
/*     */   public static final int RXPB_HEXID = 49665;
/*     */   public static final String RXPB_EYE = "RXPB";
/*     */   public static final int RXPB_VERSION_1 = 1;
/*     */   public static final int RXPB_VERSION_2 = 2;
/*     */   public static final int RXPB_VERSION_3 = 3;
/*     */   public static final int RXPB_CURRENT_VERSION = 3;
/*     */   public static final int RXPB_FLAGS_DEFAULT = 0;
/*     */   public static final int RXPB_FLAGS_FORCE_LOCAL = 1;
/*     */   public static final int RXPB_FLAGS_NEW_CONTEXT = 2;
/*     */   public static final int RXPB_FLAGS_AUTHENTICATE = 4;
/*     */   public static final int RXPB_FLAGS_COPY_UID = 8;
/*     */   public static final int RXPB_FLAGS_FORCE_PRIVATE = 16;
/*     */   public static final int RXPB_FLAGS_SET_PREV_CTX = 32;
/*     */   public static final int RXPB_FLAGS_RXPB_WITH_CNO = 64;
/* 110 */   private int version = 1;
/* 111 */   private byte[] ctxTkn = new byte[16];
/*     */   private int flags;
/* 113 */   private byte[] prevCtxTkn = new byte[16];
/* 114 */   private long wasTranId = -1L;
/* 115 */   private String QMId = "    ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RXPB(JmqiEnvironment env) {
/* 123 */     super(env);
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPB", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPB", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 139 */     if (Trace.isOn)
/* 140 */       Trace.entry("com.ibm.mq.jmqi.system.RXPB", "getFieldSizeV1(int)", new Object[] {
/* 141 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 143 */     int size = 0;
/* 144 */     size += 2;
/* 145 */     size += 2;
/* 146 */     size += 4;
/* 147 */     size += 4;
/* 148 */     size += 16;
/* 149 */     size += 4;
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit("com.ibm.mq.jmqi.system.RXPB", "getFieldSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 153 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 161 */     if (Trace.isOn)
/* 162 */       Trace.entry("com.ibm.mq.jmqi.system.RXPB", "getSizeV1(int)", new Object[] {
/* 163 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 165 */     int size = getFieldSizeV1(ptrSize);
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit("com.ibm.mq.jmqi.system.RXPB", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 169 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize) {
/* 177 */     if (Trace.isOn)
/* 178 */       Trace.entry("com.ibm.mq.jmqi.system.RXPB", "getFieldSizeV2(int)", new Object[] {
/* 179 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 181 */     int size = getFieldSizeV1(ptrSize);
/* 182 */     size += ptrSize;
/* 183 */     size += 16;
/* 184 */     size += 12;
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit("com.ibm.mq.jmqi.system.RXPB", "getFieldSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 188 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry("com.ibm.mq.jmqi.system.RXPB", "getSizeV2(int)", new Object[] {
/* 198 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 200 */     int size = getFieldSizeV2(ptrSize);
/* 201 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit("com.ibm.mq.jmqi.system.RXPB", "getSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 206 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV3(int ptrSize) {
/* 214 */     if (Trace.isOn)
/* 215 */       Trace.entry("com.ibm.mq.jmqi.system.RXPB", "getFieldSizeV3(int)", new Object[] {
/* 216 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 218 */     int size = getFieldSizeV2(ptrSize);
/* 219 */     size += 8;
/* 220 */     size += 4;
/* 221 */     size += 4;
/* 222 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit("com.ibm.mq.jmqi.system.RXPB", "getFieldSizeV3(int)", Integer.valueOf(size));
/*     */     }
/* 227 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV3(int ptrSize) {
/* 235 */     if (Trace.isOn)
/* 236 */       Trace.entry("com.ibm.mq.jmqi.system.RXPB", "getSizeV3(int)", new Object[] {
/* 237 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 239 */     int size = getFieldSizeV3(ptrSize);
/*     */     
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit("com.ibm.mq.jmqi.system.RXPB", "getSizeV3(int)", Integer.valueOf(size));
/*     */     }
/* 244 */     return size;
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
/*     */     int size;
/* 256 */     if (this.version == 1) {
/* 257 */       size = getSizeV1(ptrSize);
/*     */     }
/* 259 */     else if (this.version == 2) {
/* 260 */       size = getSizeV2(ptrSize);
/*     */     }
/* 262 */     else if (this.version == 3) {
/* 263 */       size = getSizeV3(ptrSize);
/*     */     } else {
/*     */       
/* 266 */       throw new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */     } 
/*     */     
/* 269 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getCtxTkn() {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "getCtxTkn()", "getter", this.ctxTkn);
/*     */     }
/* 279 */     return this.ctxTkn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCtxTkn(byte[] ctxTkn) {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "setCtxTkn(byte [ ])", "setter", ctxTkn);
/*     */     }
/* 289 */     JmqiTools.byteArrayCopy(ctxTkn, 0, this.ctxTkn, 0, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "getFlags()", "getter", 
/* 298 */           Integer.valueOf(this.flags));
/*     */     }
/* 300 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "setFlags(int)", "setter", 
/* 309 */           Integer.valueOf(flags));
/*     */     }
/* 311 */     this.flags = flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "getVersion()", "getter", 
/* 321 */           Integer.valueOf(this.version));
/*     */     }
/* 323 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "setVersion(int)", "setter", 
/* 333 */           Integer.valueOf(version));
/*     */     }
/* 335 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getPrevCtxTkn() {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "getPrevCtxTkn()", "getter", this.prevCtxTkn);
/*     */     }
/* 345 */     return this.prevCtxTkn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrevCtxTkn(byte[] prevCtxTkn) {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "setPrevCtxTkn(byte [ ])", "setter", prevCtxTkn);
/*     */     }
/*     */     
/* 356 */     JmqiTools.byteArrayCopy(prevCtxTkn, 0, this.prevCtxTkn, 0, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getWasTranId() {
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "getWasTranId()", "getter", 
/* 365 */           Long.valueOf(this.wasTranId));
/*     */     }
/* 367 */     return this.wasTranId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWasTranId(long wasTranId) {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "setWasTranId(long)", "setter", 
/* 376 */           Long.valueOf(wasTranId));
/*     */     }
/* 378 */     this.wasTranId = wasTranId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMId() {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "getQMId()", "getter", this.QMId);
/*     */     }
/* 388 */     return this.QMId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQMId(String id) {
/* 395 */     if (Trace.isOn) {
/* 396 */       Trace.data(this, "com.ibm.mq.jmqi.system.RXPB", "setQMId(String)", "setter", id);
/*     */     }
/* 398 */     this.QMId = id;
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
/* 409 */     if (Trace.isOn)
/* 410 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPB", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 411 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 413 */     int traceRet1 = getSize(ptrSize);
/*     */     
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPB", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 417 */           Integer.valueOf(traceRet1));
/*     */     }
/* 419 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPB", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 431 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 433 */     int pos = offset;
/* 434 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */ 
/*     */     
/* 437 */     dc.writeU16(49665, buffer, pos, swap);
/* 438 */     pos += 2;
/*     */     
/* 440 */     int size = getSize(ptrSize);
/* 441 */     dc.writeU16(size, buffer, pos, swap);
/* 442 */     pos += 2;
/*     */     
/* 444 */     dc.writeMQField("RXPB", buffer, pos, 4, cp, tls);
/* 445 */     pos += 4;
/*     */     
/* 447 */     dc.writeI32(this.version, buffer, pos, swap);
/* 448 */     pos += 4;
/*     */     
/* 450 */     System.arraycopy(this.ctxTkn, 0, buffer, pos, 16);
/* 451 */     pos += 16;
/*     */     
/* 453 */     dc.writeI32(this.flags, buffer, pos, swap);
/* 454 */     pos += 4;
/* 455 */     if (this.version >= 2) {
/*     */       
/* 457 */       dc.clear(buffer, pos, ptrSize);
/* 458 */       pos += ptrSize;
/*     */       
/* 460 */       System.arraycopy(this.prevCtxTkn, 0, buffer, pos, 16);
/* 461 */       pos += 16;
/*     */       
/* 463 */       dc.clear(buffer, pos, 12);
/* 464 */       pos += 12;
/*     */     } 
/*     */     
/* 467 */     if (this.version == 2) {
/* 468 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 469 */       dc.clear(buffer, pos, padding);
/* 470 */       pos += padding;
/*     */     } 
/* 472 */     if (this.version >= 3) {
/*     */       
/* 474 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 475 */       dc.clear(buffer, pos, padding);
/* 476 */       pos += padding;
/*     */       
/* 478 */       dc.writeI64(this.wasTranId, buffer, pos, swap);
/* 479 */       pos += 8;
/*     */       
/* 481 */       dc.writeMQField(this.QMId, buffer, pos, 4, cp, tls);
/* 482 */       pos += 4;
/*     */       
/* 484 */       dc.clear(buffer, pos, 4);
/* 485 */       pos += 4;
/*     */     } 
/*     */     
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPB", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 490 */           Integer.valueOf(pos));
/*     */     }
/* 492 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPB", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 504 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 506 */     int pos = offset;
/* 507 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */ 
/*     */     
/* 510 */     int hexid = dc.readU16(buffer, pos, swap);
/* 511 */     if (hexid != 49665) {
/* 512 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */ 
/*     */       
/* 515 */       if (Trace.isOn) {
/* 516 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.RXPB", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 519 */       throw traceRet1;
/*     */     } 
/* 521 */     pos += 2;
/*     */ 
/*     */     
/* 524 */     pos += 2;
/*     */ 
/*     */     
/* 527 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 528 */     if (!strucId.equals("RXPB")) {
/* 529 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */ 
/*     */       
/* 532 */       if (Trace.isOn) {
/* 533 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.RXPB", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 536 */       throw traceRet1;
/*     */     } 
/* 538 */     pos += 4;
/*     */ 
/*     */     
/* 541 */     pos += 4;
/*     */ 
/*     */     
/* 544 */     System.arraycopy(buffer, pos, this.ctxTkn, 0, 16);
/* 545 */     pos += 16;
/*     */ 
/*     */     
/* 548 */     pos += 4;
/*     */     
/* 550 */     if (this.version >= 2) {
/*     */       
/* 552 */       pos += ptrSize;
/*     */ 
/*     */       
/* 555 */       System.arraycopy(buffer, pos, this.prevCtxTkn, 0, 16);
/* 556 */       pos += 16;
/*     */ 
/*     */       
/* 559 */       pos += 12;
/*     */     } 
/*     */ 
/*     */     
/* 563 */     if (this.version == 2) {
/* 564 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 565 */       pos += padding;
/*     */     } 
/*     */     
/* 568 */     if (this.version >= 3) {
/*     */ 
/*     */       
/* 571 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 572 */       pos += padding;
/*     */ 
/*     */       
/* 575 */       this.wasTranId = dc.readI64(buffer, pos, swap);
/* 576 */       pos += 8;
/*     */ 
/*     */       
/* 579 */       this.QMId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 580 */       pos += 4;
/*     */ 
/*     */       
/* 583 */       pos += 4;
/*     */     } 
/*     */     
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPB", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 588 */           Integer.valueOf(pos));
/*     */     }
/* 590 */     return pos;
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
/* 602 */     fmt.add("version", this.version);
/* 603 */     fmt.add("ctxTkn", this.ctxTkn);
/* 604 */     fmt.add("flags", this.flags);
/* 605 */     fmt.add("wasTranId", this.wasTranId);
/* 606 */     fmt.add("QMId", this.QMId);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\RXPB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */