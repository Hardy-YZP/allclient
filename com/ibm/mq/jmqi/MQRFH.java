/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.CharBuffer;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQRFH
/*     */   extends AbstractMqHeaderStructure
/*     */ {
/*     */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQRFH.java";
/*     */   private static final int SIZEOF_NAMEVALUECCSID = 4;
/*     */   private static final int SIZEOF_NAMEVALUELENGTH = 4;
/*     */   
/*     */   static {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.data("com.ibm.mq.jmqi.MQRFH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQRFH.java");
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
/*  83 */   private int nameValueCcsid = 1208;
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] nameValueData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/*  93 */     int sizeV1 = MQHeader.getSize(env, ptrSize);
/*  94 */     return sizeV1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(JmqiEnvironment env, int ptrSize) {
/* 104 */     int sizeV2 = getSizeV1(env, ptrSize) + 4;
/* 105 */     return sizeV2;
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
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry("com.ibm.mq.jmqi.MQRFH", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 118 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/* 120 */     int size = 0;
/* 121 */     switch (version) {
/*     */       case 1:
/* 123 */         size = getSizeV1(env, ptrSize);
/*     */         break;
/*     */       case 2:
/* 126 */         size = getSizeV2(env, ptrSize);
/*     */         break;
/*     */       default:
/* 129 */         e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */         
/* 131 */         if (Trace.isOn) {
/* 132 */           Trace.throwing("com.ibm.mq.jmqi.MQRFH", "getSize(JmqiEnvironment,int,int)", e);
/*     */         }
/* 134 */         throw e;
/*     */     } 
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit("com.ibm.mq.jmqi.MQRFH", "getSize(JmqiEnvironment,int,int)", 
/* 138 */           Integer.valueOf(size));
/*     */     }
/* 140 */     return size;
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
/*     */   @Deprecated
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 154 */     if (Trace.isOn)
/* 155 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 156 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 160 */           Integer.valueOf(0));
/*     */     }
/* 162 */     return 0;
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
/*     */   public int getRequiredBufferSize(JmqiMQ mq, int ptrSize, JmqiCodepage cp, boolean swap) throws JmqiException {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "getRequiredBufferSize(JmqiMQ,int,JmqiCodepage,boolean)", new Object[] { mq, 
/*     */             
/* 178 */             Integer.valueOf(ptrSize), cp, Boolean.valueOf(swap) });
/*     */     }
/* 180 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/* 181 */     int compId = mq.getTlsComponentId();
/* 182 */     JmqiComponentTls tls = sysenv.getComponentTls(compId);
/* 183 */     JmqiTls jTls = sysenv.getJmqiTls(tls);
/*     */     
/* 185 */     int size = getSize(this.env, getMqHeader().getVersion(), ptrSize);
/*     */ 
/*     */ 
/*     */     
/* 189 */     size += convertNameValueDataToCachedByteArray(jTls, cp, swap);
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "getRequiredBufferSize(JmqiMQ,int,JmqiCodepage,boolean)", 
/* 193 */           Integer.valueOf(size));
/*     */     }
/* 195 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int convertNameValueDataToCachedByteArray(JmqiTls tls, JmqiCodepage cp, boolean swap) throws JmqiException {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "convertNameValueDataToCachedByteArray(JmqiTls,JmqiCodepage,boolean)", new Object[] { tls, cp, 
/*     */             
/* 208 */             Boolean.valueOf(swap) });
/*     */     }
/* 210 */     int pos = 0;
/*     */     
/*     */     try {
/* 213 */       int allocateSize = 0;
/* 214 */       for (int i = 0; i < this.nameValueData.length; i++) {
/* 215 */         String string = this.nameValueData[i];
/* 216 */         if (string != null) {
/* 217 */           allocateSize += 4 + (string.length() + 3) * 2;
/*     */         }
/*     */       } 
/* 220 */       if (tls.rfhNameValueDataBuf == null || tls.rfhNameValueDataBuf.length < allocateSize) {
/* 221 */         tls.rfhNameValueDataBuf = new byte[allocateSize];
/*     */       }
/*     */ 
/*     */       
/* 225 */       for (int index = 0; index < this.nameValueData.length; index++) {
/* 226 */         String string = this.nameValueData[index];
/* 227 */         if (string != null) {
/* 228 */           pos += convertElementToCachedByteArray(tls, pos, string, cp, swap);
/*     */         }
/*     */       } 
/*     */       
/* 232 */       tls.rfhNameValueDataBuf_Length = pos;
/*     */     }
/* 234 */     catch (Exception e) {
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.MQRFH", "convertNameValueDataToCachedByteArray(JmqiTls,JmqiCodepage,boolean)", e);
/*     */       }
/*     */       
/* 239 */       JmqiException e2 = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(e), null, JmqiTools.getFailingCall(e) }, 2, 2195, e);
/*     */       
/* 241 */       if (Trace.isOn) {
/* 242 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "convertNameValueDataToCachedByteArray(JmqiTls,JmqiCodepage,boolean)", e2);
/*     */       }
/*     */       
/* 245 */       throw e2;
/*     */     } 
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "convertNameValueDataToCachedByteArray(JmqiTls,JmqiCodepage,boolean)", 
/* 250 */           Integer.valueOf(pos));
/*     */     }
/*     */     
/* 253 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int convertElementToCachedByteArray(JmqiTls tls, int offset, String string, JmqiCodepage cp, boolean swap) {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "convertElementToCachedByteArray(JmqiTls,int,String,JmqiCodepage,boolean)", new Object[] { tls, 
/*     */             
/* 267 */             Integer.valueOf(offset), string, cp, Boolean.valueOf(swap) });
/*     */     }
/* 269 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 270 */     int pos = offset;
/*     */     
/* 272 */     int stringPos = pos + 4;
/* 273 */     int nameValueLength = dc.convUtf16Utf8(string, 0, tls.rfhNameValueDataBuf, stringPos, -2);
/*     */     
/* 275 */     int padLength = dc.getPaddingLength(nameValueLength);
/* 276 */     for (int i = 0; i < padLength; i++) {
/* 277 */       tls.rfhNameValueDataBuf[stringPos + nameValueLength + i] = cp.spaceByte;
/*     */     }
/* 279 */     int alignedLength = nameValueLength + padLength;
/*     */     
/* 281 */     dc.writeI32(alignedLength, tls.rfhNameValueDataBuf, pos, swap);
/*     */     
/* 283 */     int traceRet1 = 4 + alignedLength;
/*     */     
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "convertElementToCachedByteArray(JmqiTls,int,String,JmqiCodepage,boolean)", 
/*     */           
/* 288 */           Integer.valueOf(traceRet1));
/*     */     }
/* 290 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQRFH(JmqiEnvironment env, int capacity) {
/* 298 */     super(env);
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/* 301 */             Integer.valueOf(capacity) });
/*     */     }
/* 303 */     this.nameValueData = new String[capacity];
/* 304 */     MQHeader mqHeader = env.newMQHeader();
/* 305 */     mqHeader.setStrucId("RFH ");
/* 306 */     mqHeader.setVersion(2);
/* 307 */     mqHeader.setStrucLength(36);
/* 308 */     setMqHeader(mqHeader);
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "<init>(JmqiEnvironment,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNameValueCcsid() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.jmqi.MQRFH", "getNameValueCcsid()", "getter", 
/* 322 */           Integer.valueOf(this.nameValueCcsid));
/*     */     }
/* 324 */     return this.nameValueCcsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameValueCcsid(int nameValueCcsid) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.jmqi.MQRFH", "setNameValueCcsid(int)", "setter", 
/* 333 */           Integer.valueOf(nameValueCcsid));
/*     */     }
/* 335 */     this.nameValueCcsid = nameValueCcsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNameValueDataLength() {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.jmqi.MQRFH", "getNameValueDataLength()", "getter", 
/* 344 */           Integer.valueOf(this.nameValueData.length));
/*     */     }
/* 346 */     return this.nameValueData.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameValueData(int index) {
/* 354 */     if (Trace.isOn)
/* 355 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "getNameValueData(int)", new Object[] {
/* 356 */             Integer.valueOf(index)
/*     */           }); 
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "getNameValueData(int)", this.nameValueData[index]);
/*     */     }
/* 361 */     return this.nameValueData[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameValueData(int index, String value) {
/* 369 */     if (Trace.isOn)
/* 370 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "setNameValueData(int,String)", new Object[] {
/* 371 */             Integer.valueOf(index), value
/*     */           }); 
/* 373 */     if (index >= this.nameValueData.length) {
/* 374 */       String[] array = new String[index];
/* 375 */       for (int i = 0; i < this.nameValueData.length; i++) {
/* 376 */         array[i] = this.nameValueData[i];
/*     */       }
/* 378 */       this.nameValueData = array;
/*     */     } 
/* 380 */     this.nameValueData[index] = value;
/*     */     
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "setNameValueData(int,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 396 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 398 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 399 */     MQHeader mqHeader = getMqHeader();
/* 400 */     int version = mqHeader.getVersion();
/*     */     
/* 402 */     JmqiCodepage nameValuesCp = cp;
/* 403 */     if (version >= 2) {
/*     */       try {
/* 405 */         nameValuesCp = JmqiCodepage.getJmqiCodepage(this.env, this.nameValueCcsid);
/*     */         
/* 407 */         if (nameValuesCp == null)
/*     */         {
/* 409 */           UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(String.valueOf(this.nameValueCcsid));
/* 410 */           if (Trace.isOn) {
/* 411 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet2, 1);
/*     */           }
/*     */           
/* 414 */           throw traceRet2;
/*     */         }
/*     */       
/*     */       }
/* 418 */       catch (UnsupportedEncodingException e) {
/* 419 */         if (Trace.isOn) {
/* 420 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQRFH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*     */         }
/*     */         
/* 423 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(this.nameValueCcsid), null, null, null, "???" }, 2, 2195, e);
/*     */         
/* 425 */         if (Trace.isOn) {
/* 426 */           Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1, 2);
/*     */         }
/*     */         
/* 429 */         throw traceRet1;
/*     */       } 
/*     */     }
/*     */     
/* 433 */     if (tls.rfhNameValueDataBuf_Length < 0) {
/* 434 */       convertNameValueDataToCachedByteArray(tls, nameValuesCp, swap);
/*     */     }
/* 436 */     int strucLength = getSize(this.env, version, ptrSize) + tls.rfhNameValueDataBuf_Length;
/*     */     
/* 438 */     mqHeader.setStrucLength(strucLength);
/* 439 */     int pos = mqHeader.writeToBuffer(buffer, offset, ptrSize, swap, cp, tls);
/* 440 */     if (version >= 2) {
/*     */       
/* 442 */       dc.writeI32(this.nameValueCcsid, buffer, pos, swap);
/* 443 */       pos += 4;
/*     */     } 
/*     */     
/* 446 */     System.arraycopy(tls.rfhNameValueDataBuf, 0, buffer, pos, tls.rfhNameValueDataBuf_Length);
/* 447 */     pos += tls.rfhNameValueDataBuf_Length;
/*     */     
/* 449 */     tls.rfhNameValueDataBuf_Length = -1;
/*     */     
/* 451 */     if (Trace.isOn) {
/* 452 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 453 */           Integer.valueOf(pos));
/*     */     }
/* 455 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 466 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 468 */     int pos = offset;
/*     */     
/* 470 */     MQHeader mqHeader = getMqHeader();
/* 471 */     pos = mqHeader.readFromBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 473 */     String strucId = mqHeader.getStrucId();
/* 474 */     if (!strucId.equals("RFH ")) {
/* 475 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2334, null);
/*     */       
/* 477 */       if (Trace.isOn) {
/* 478 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 481 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 485 */     pos = readBodyFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     
/* 487 */     if (Trace.isOn) {
/* 488 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 489 */           Integer.valueOf(pos));
/*     */     }
/* 491 */     return pos;
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
/*     */   public int readBodyFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 510 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 512 */     int pos = offset;
/* 513 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 514 */     MQHeader mqHeader = getMqHeader();
/*     */     
/* 516 */     JmqiCodepage nameValuesCp = cp;
/* 517 */     if (mqHeader.getVersion() >= 2) {
/*     */       
/* 519 */       this.nameValueCcsid = dc.readI32(buffer, pos, swap);
/* 520 */       pos += 4;
/*     */       try {
/* 522 */         nameValuesCp = JmqiCodepage.getJmqiCodepage(this.env, this.nameValueCcsid);
/*     */         
/* 524 */         if (nameValuesCp == null)
/*     */         {
/* 526 */           UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(String.valueOf(this.nameValueCcsid));
/* 527 */           if (Trace.isOn) {
/* 528 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet2, 1);
/*     */           }
/*     */           
/* 531 */           throw traceRet2;
/*     */         }
/*     */       
/*     */       }
/* 535 */       catch (UnsupportedEncodingException e) {
/* 536 */         if (Trace.isOn) {
/* 537 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*     */         }
/*     */         
/* 540 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(this.nameValueCcsid), null, null, null, "???" }, 2, 2195, e);
/*     */         
/* 542 */         if (Trace.isOn) {
/* 543 */           Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1, 2);
/*     */         }
/*     */         
/* 546 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 553 */     if (tls.rfhNameValueData_List == null) {
/* 554 */       tls.rfhNameValueData_List = new ArrayList();
/*     */     }
/* 556 */     tls.rfhNameValueData_List.clear();
/*     */     
/* 558 */     int strucLength = mqHeader.getStrucLength();
/* 559 */     int limit = strucLength - MQHeader.getSize(this.env, ptrSize) + offset;
/* 560 */     while (pos < limit) {
/*     */       
/* 562 */       int nameValueLength = dc.readI32(buffer, pos, swap);
/* 563 */       if (nameValueLength > buffer.length - pos) {
/*     */         
/* 565 */         JmqiException e = new JmqiException(this.env, -1, null, 2, 2142, null);
/*     */         
/* 567 */         if (Trace.isOn) {
/* 568 */           Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 3);
/*     */         }
/*     */         
/* 571 */         throw e;
/*     */       } 
/* 573 */       pos += 4;
/*     */       
/* 575 */       CharBuffer target = CharBuffer.allocate(nameValueLength);
/* 576 */       pos += dc.readString(buffer, pos, nameValueLength, target, nameValuesCp, tls);
/* 577 */       String string = target.toString();
/* 578 */       tls.rfhNameValueData_List.add(string);
/*     */     } 
/* 580 */     this.nameValueData = new String[tls.rfhNameValueData_List.size()];
/* 581 */     for (int i = 0; i < tls.rfhNameValueData_List.size(); i++) {
/* 582 */       this.nameValueData[i] = tls.rfhNameValueData_List.get(i);
/*     */     }
/* 584 */     if (pos != limit) {
/*     */       
/* 586 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2142, null);
/*     */       
/* 588 */       if (Trace.isOn) {
/* 589 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 4);
/*     */       }
/*     */       
/* 592 */       throw e;
/*     */     } 
/*     */ 
/*     */     
/* 596 */     tls.rfhNameValueData_List.clear();
/*     */     
/* 598 */     if (Trace.isOn) {
/* 599 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRFH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 600 */           Integer.valueOf(pos));
/*     */     }
/* 602 */     return pos;
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
/* 614 */     getMqHeader().addFieldsToFormatter(fmt);
/* 615 */     fmt.add("nameValueCcsid", this.nameValueCcsid);
/* 616 */     fmt.add("nameValueData", this.nameValueData);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQRFH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */