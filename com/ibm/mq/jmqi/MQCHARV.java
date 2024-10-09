/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCHARV
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCHARV.java";
/*     */   private static final int SIZEOF_VSOFFSET = 4;
/*     */   private static final int SIZEOF_VSBUFSIZE = 4;
/*     */   private static final int SIZEOF_VSLENGTH = 4;
/*     */   private static final int SIZEOF_VSCCSID = 4;
/*     */   private int vsOffset;
/*     */   private int vsBufSize;
/*     */   private int vsLength;
/*     */   private int vsCcsid;
/*     */   private String vsString;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jmqi.MQCHARV", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCHARV.java");
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
/*     */   private boolean remote = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private byte[] temporaryArray = new byte[64];
/*  90 */   private int temporaryArray_Length = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV(JmqiEnvironment env) {
/*  97 */     super(env);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(int ptrSize) {
/* 106 */     return ptrSize + 4 + 4 + 4 + 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 116 */     if (Trace.isOn)
/* 117 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCHARV", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 118 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 120 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 121 */     int size = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 128 */       int inputLength = convertStringToCachedByteArray(cp);
/* 129 */       int length = Math.max(inputLength, this.vsBufSize);
/* 130 */       size = dc.getAlignedLength(length);
/*     */     }
/* 132 */     catch (Exception e) {
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.MQCHARV", "getRequiredBufferSize(int,JmqiCodepage)", e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 139 */       JmqiException e2 = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(e), null, JmqiTools.getFailingCall(e) }, 2, 2195, e);
/*     */       
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCHARV", "getRequiredBufferSize(int,JmqiCodepage)", e2);
/*     */       }
/*     */       
/* 145 */       throw e2;
/*     */     } 
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCHARV", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 150 */           Integer.valueOf(size));
/*     */     }
/* 152 */     return size;
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
/* 163 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 165 */     int size = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 172 */       int inputLength = convertStringToCachedByteArray(cp);
/* 173 */       size = dc.getAlignedLength(inputLength);
/*     */     }
/* 175 */     catch (Exception e) {
/*     */       
/* 177 */       JmqiException e2 = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(e), null, JmqiTools.getFailingCall(e) }, 2, 2195, e);
/*     */       
/* 179 */       throw e2;
/*     */     } 
/*     */     
/* 182 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVsBufSize() {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.jmqi.MQCHARV", "getVsBufSize()", "getter", 
/* 191 */           Integer.valueOf(this.vsBufSize));
/*     */     }
/* 193 */     return this.vsBufSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVsBufSize(int vsBufSize) {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.jmqi.MQCHARV", "setVsBufSize(int)", "setter", 
/* 202 */           Integer.valueOf(vsBufSize));
/*     */     }
/* 204 */     this.vsBufSize = vsBufSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVsString() {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.data(this, "com.ibm.mq.jmqi.MQCHARV", "getVsString()", "getter", this.vsString);
/*     */     }
/* 214 */     return this.vsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVsString(String vsString) {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.data(this, "com.ibm.mq.jmqi.MQCHARV", "setVsString(String)", "setter", vsString);
/*     */     }
/* 224 */     this.vsString = vsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int convertStringToCachedByteArray(JmqiCodepage cp) {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCHARV", "convertStringToCachedByteArray(JmqiCodepage)", new Object[] { cp });
/*     */     }
/*     */     
/* 236 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 237 */     this.vsCcsid = 1208;
/* 238 */     if (this.vsString == null) {
/* 239 */       this.temporaryArray_Length = 0;
/*     */     } else {
/*     */       
/* 242 */       this.temporaryArray = dc.getTemporaryArray(this.temporaryArray, this.vsString);
/*     */       
/* 244 */       this.temporaryArray_Length = dc.convUtf16Utf8(this.vsString, 0, this.temporaryArray, 0, -2);
/*     */     } 
/*     */     
/* 247 */     int traceRet1 = Math.max(this.temporaryArray_Length, this.vsLength);
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCHARV", "convertStringToCachedByteArray(JmqiCodepage)", 
/* 251 */           Integer.valueOf(traceRet1));
/*     */     }
/* 253 */     return traceRet1;
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
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int structPos, int offset, int vsStringOffset, int ptrSize, boolean swap, JmqiCodepage cp) {
/* 273 */     int pos = offset;
/* 274 */     int vsStringPos = vsStringOffset;
/*     */ 
/*     */     
/* 277 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */ 
/*     */     
/* 280 */     if (this.temporaryArray_Length < 0) {
/* 281 */       convertStringToCachedByteArray(cp);
/*     */     }
/*     */     int i;
/* 284 */     for (i = 0; i < this.temporaryArray_Length; i++) {
/* 285 */       buffer[vsStringPos + i] = this.temporaryArray[i];
/*     */     }
/* 287 */     int padLength = dc.getPaddingLength(this.temporaryArray_Length);
/* 288 */     for (i = 0; i < padLength; i++) {
/* 289 */       buffer[vsStringPos + this.temporaryArray_Length + i] = 0;
/*     */     }
/*     */     
/* 292 */     this.vsLength = this.temporaryArray_Length;
/*     */     
/* 294 */     this.vsOffset = vsStringPos - structPos;
/*     */     
/* 296 */     this.temporaryArray_Length = -1;
/*     */ 
/*     */     
/* 299 */     int alignedBufferLength = this.vsLength + padLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 308 */     dc.clear(buffer, pos, ptrSize);
/* 309 */     pos += ptrSize;
/*     */ 
/*     */     
/* 312 */     dc.writeI32(this.vsOffset, buffer, pos, swap);
/* 313 */     pos += 4;
/*     */ 
/*     */     
/* 316 */     dc.writeI32(this.vsBufSize, buffer, pos, swap);
/* 317 */     pos += 4;
/*     */ 
/*     */     
/* 320 */     dc.writeI32(this.vsLength, buffer, pos, swap);
/* 321 */     pos += 4;
/*     */ 
/*     */     
/* 324 */     dc.writeI32(this.vsCcsid, buffer, pos, swap);
/* 325 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 330 */     if (this.vsBufSize > 0 && !isRemote()) {
/* 331 */       return vsStringPos + this.vsBufSize;
/*     */     }
/*     */ 
/*     */     
/* 335 */     return vsStringPos + alignedBufferLength;
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
/*     */   public int readFromBuffer(byte[] buffer, int structPos, int offset, int ptrSize, boolean swap, JmqiTls tls) throws JmqiException {
/* 353 */     int pos = offset;
/* 354 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */ 
/*     */     
/* 357 */     pos += ptrSize;
/*     */ 
/*     */     
/* 360 */     this.vsOffset = dc.readI32(buffer, pos, swap);
/* 361 */     pos += 4;
/*     */ 
/*     */     
/* 364 */     this.vsBufSize = dc.readI32(buffer, pos, swap);
/* 365 */     pos += 4;
/*     */ 
/*     */     
/* 368 */     this.vsLength = dc.readI32(buffer, pos, swap);
/* 369 */     pos += 4;
/*     */ 
/*     */     
/* 372 */     this.vsCcsid = dc.readI32(buffer, pos, swap);
/* 373 */     pos += 4;
/*     */ 
/*     */     
/* 376 */     int startOffset = structPos + this.vsOffset;
/* 377 */     if (this.vsLength == -1) {
/* 378 */       this.vsLength = 0;
/* 379 */       int charPosition = startOffset;
/* 380 */       while (buffer[charPosition] != 0) {
/* 381 */         charPosition++;
/* 382 */         this.vsLength++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 387 */     JmqiCodepage cp = null;
/*     */     try {
/* 389 */       cp = JmqiCodepage.getJmqiCodepage(this.env, this.vsCcsid);
/*     */       
/* 391 */       if (cp == null) {
/* 392 */         throw new UnsupportedEncodingException(Integer.toString(this.vsCcsid));
/*     */       
/*     */       }
/*     */     }
/* 396 */     catch (UnsupportedEncodingException e) {
/* 397 */       JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(this.vsCcsid), null, null, null, "???" }, 2, 2195, e);
/*     */       
/* 399 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 403 */     int length = Math.min(this.vsLength, this.vsBufSize);
/* 404 */     this.vsString = dc.readField(buffer, startOffset, length, cp, tls);
/*     */     
/* 406 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndPosAligned(int structPos) {
/* 414 */     if (Trace.isOn)
/* 415 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCHARV", "getEndPosAligned(int)", new Object[] {
/* 416 */             Integer.valueOf(structPos)
/*     */           }); 
/* 418 */     int endPos = 0;
/*     */     
/* 420 */     endPos = structPos + this.vsOffset + Math.min(this.vsLength, this.vsBufSize);
/* 421 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 422 */     int traceRet1 = dc.getAlignedLength(endPos);
/*     */     
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCHARV", "getEndPosAligned(int)", 
/* 426 */           Integer.valueOf(traceRet1));
/*     */     }
/* 428 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCHARV", "toString()");
/*     */     }
/* 439 */     StringBuffer buffer = new StringBuffer();
/* 440 */     buffer.append("vsOffset:");
/* 441 */     buffer.append(this.vsOffset);
/* 442 */     buffer.append(" vsBufSize:");
/* 443 */     buffer.append(this.vsBufSize);
/* 444 */     buffer.append(" vsLength:");
/* 445 */     buffer.append(this.vsLength);
/* 446 */     buffer.append(" vsCcsid:");
/* 447 */     buffer.append(this.vsCcsid);
/* 448 */     buffer.append(" vsString:");
/* 449 */     buffer.append(JmqiTools.safeString(this.vsString));
/* 450 */     String traceRet1 = buffer.toString();
/* 451 */     if (Trace.isOn) {
/* 452 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCHARV", "toString()", traceRet1);
/*     */     }
/* 454 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRemote(boolean remote) {
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.data(this, "com.ibm.mq.jmqi.MQCHARV", "setRemote(boolean)", "setter", 
/* 465 */           Boolean.valueOf(remote));
/*     */     }
/* 467 */     this.remote = remote;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRemote() {
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.data(this, "com.ibm.mq.jmqi.MQCHARV", "isRemote()", "getter", Boolean.valueOf(this.remote));
/*     */     }
/*     */     
/* 478 */     return this.remote;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCHARV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */