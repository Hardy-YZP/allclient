/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReceiveZRFP
/*     */   extends ZRFP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/ReceiveZRFP.java";
/*     */   private boolean readSwap;
/*     */   private JmqiCodepage readCp;
/*     */   private Triplet[] triplets;
/*     */   private int tripletsIndex;
/*     */   private static final int foldersSupportedOnReadSize = 5;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/ReceiveZRFP.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final HashMap<String, Boolean> foldersSupportedOnRead = new HashMap<>(5);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry("com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "static()");
/*     */     }
/*     */     
/*  81 */     foldersSupportedOnRead.put("mcd", Boolean.TRUE);
/*  82 */     foldersSupportedOnRead.put("jms", Boolean.TRUE);
/*  83 */     foldersSupportedOnRead.put("usr", Boolean.TRUE);
/*  84 */     foldersSupportedOnRead.put("mqext", Boolean.TRUE);
/*  85 */     foldersSupportedOnRead.put("mqps", Boolean.TRUE);
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit("com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReceiveZRFP(JmqiEnvironment env, JmqiTls tls) {
/*  98 */     super(env, tls);
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "<init>(JmqiEnvironment,JmqiTls)", new Object[] { env, tls });
/*     */     }
/*     */     
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "<init>(JmqiEnvironment,JmqiTls)");
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
/*     */   public int readFromBuffer(byte[] buffer, int startOffset) throws JmqiException {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readFromBuffer(byte [ ],int)", new Object[] { buffer, 
/* 121 */             Integer.valueOf(startOffset) });
/*     */     }
/* 123 */     assert buffer != null : "buffer is unexpectedly null";
/* 124 */     assert startOffset >= 0 : "startOffset is negative";
/*     */     
/* 126 */     this.triplets = new Triplet[5];
/* 127 */     this.tripletsIndex = 0;
/*     */     
/* 129 */     int pos = this.fixedPart.read(buffer, startOffset);
/* 130 */     this.readSwap = this.fixedPart.getReadSwap();
/*     */     
/* 132 */     int readCCSID = this.fixedPart.getCcsid();
/*     */     
/* 134 */     if (this.readCp == null || this.readCp.getCCSID() != readCCSID) {
/*     */       try {
/* 136 */         this.readCp = JmqiCodepage.getJmqiCodepage(this.env, readCCSID);
/*     */         
/* 138 */         if (this.readCp == null)
/*     */         {
/* 140 */           UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(readCCSID));
/* 141 */           if (Trace.isOn) {
/* 142 */             Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readFromBuffer(byte [ ],int)", traceRet2, 1);
/*     */           }
/*     */           
/* 145 */           throw traceRet2;
/*     */         }
/*     */       
/*     */       }
/* 149 */       catch (UnsupportedEncodingException e) {
/* 150 */         if (Trace.isOn) {
/* 151 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readFromBuffer(byte [ ],int)", e);
/*     */         }
/*     */         
/* 154 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { String.valueOf(readCCSID), null, null, null, "???" }, 2, 2195, e);
/*     */         
/* 156 */         if (Trace.isOn) {
/* 157 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readFromBuffer(byte [ ],int)", (Throwable)traceRet1, 2);
/*     */         }
/*     */         
/* 160 */         throw traceRet1;
/*     */       } 
/*     */     }
/* 163 */     assert this.readCp != null : "readCp is unexpectedly null";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     int strucLength = (int)this.fixedPart.getStrucLength();
/* 169 */     if (strucLength < 0) {
/* 170 */       throwStrucLengthException((Throwable)null);
/*     */     }
/*     */     
/* 173 */     int maxPos = startOffset + strucLength;
/*     */     
/* 175 */     int bufferLength = buffer.length;
/* 176 */     if (bufferLength < maxPos) {
/* 177 */       throwInsufficientBufferException((Throwable)null);
/*     */     }
/*     */ 
/*     */     
/* 181 */     while (pos < maxPos) {
/*     */       
/* 183 */       int loopEnterPos = pos;
/*     */       
/* 185 */       int[] tmp = Utils.readU8U24(buffer, pos, this.readSwap);
/* 186 */       int type = tmp[0];
/* 187 */       int flags = tmp[1];
/* 188 */       throwPropTypeException(type, false);
/*     */       
/* 190 */       if (type == 10) {
/* 191 */         if (isNonNestedNode(flags)) {
/* 192 */           pos = readParentNode(buffer, pos);
/*     */         } else {
/*     */           
/* 195 */           pos = skipReadingNestedParentNode(buffer, pos);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 200 */         throwBufferException((Throwable)null);
/*     */       } 
/*     */       
/* 203 */       int loopExitPos = pos;
/* 204 */       if (loopEnterPos == loopExitPos)
/*     */       {
/* 206 */         throwBufferException((Throwable)null);
/*     */       }
/*     */     } 
/*     */     
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readFromBuffer(byte [ ],int)", 
/* 212 */           Integer.valueOf(pos));
/*     */     }
/* 214 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int readParentNode(byte[] buffer, int startPos) throws JmqiException {
/*     */     int endPos;
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readParentNode(byte [ ],int)", new Object[] { buffer, 
/* 228 */             Integer.valueOf(startPos) });
/*     */     }
/* 230 */     int pos = startPos;
/* 231 */     pos = this.parentNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 232 */     String folderName = this.parentNode.getName();
/*     */ 
/*     */ 
/*     */     
/* 236 */     int folderGroupLength = (int)this.parentNode.getGroupLength();
/* 237 */     int maxPos = startPos + folderGroupLength;
/*     */     
/* 239 */     if (foldersSupportedOnRead.containsKey(folderName)) {
/*     */       
/* 241 */       Triplet newTriplet = new Triplet(folderName, 10);
/*     */ 
/*     */       
/* 244 */       while (pos < maxPos) {
/*     */         
/* 246 */         int loopEnterPos = pos;
/*     */         
/* 248 */         int[] tmp = Utils.readU8U24(buffer, pos, this.readSwap);
/* 249 */         int type = tmp[0];
/* 250 */         int flags = tmp[1];
/* 251 */         throwPropTypeException(type, false);
/*     */         
/* 253 */         boolean typeNotOverridden = isAsStringFlagNotEnabled(flags);
/*     */         
/* 255 */         String propName = null;
/* 256 */         Object propValue = null;
/* 257 */         if (typeNotOverridden) {
/* 258 */           zrfpUnknown unknownNode; switch (type) {
/*     */             
/*     */             case 0:
/* 261 */               pos = this.stringNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 262 */               propName = this.stringNode.getName();
/* 263 */               propValue = this.stringNode.getValue();
/*     */               break;
/*     */             case 5:
/* 266 */               pos = this.int32Node.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 267 */               propName = this.int32Node.getName();
/* 268 */               propValue = Integer.valueOf(this.int32Node.getValue());
/*     */               break;
/*     */             case 1:
/* 271 */               pos = this.booleanNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 272 */               propName = this.booleanNode.getName();
/* 273 */               propValue = this.booleanNode.getValue() ? Boolean.TRUE : Boolean.FALSE;
/*     */               break;
/*     */             case 2:
/* 276 */               pos = this.byteArrayNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 277 */               propName = this.byteArrayNode.getName();
/* 278 */               propValue = this.byteArrayNode.getValue();
/*     */               break;
/*     */             case 7:
/* 281 */               pos = this.float32Node.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 282 */               propName = this.float32Node.getName();
/* 283 */               propValue = Float.valueOf(this.float32Node.getValue());
/*     */               break;
/*     */             case 8:
/* 286 */               pos = this.float64Node.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 287 */               propName = this.float64Node.getName();
/* 288 */               propValue = Double.valueOf(this.float64Node.getValue());
/*     */               break;
/*     */             case 3:
/* 291 */               pos = this.int8Node.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 292 */               propName = this.int8Node.getName();
/* 293 */               propValue = Byte.valueOf(this.int8Node.getValue());
/*     */               break;
/*     */             case 4:
/* 296 */               pos = this.int16Node.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 297 */               propName = this.int16Node.getName();
/* 298 */               propValue = Short.valueOf(this.int16Node.getValue());
/*     */               break;
/*     */             case 6:
/* 301 */               pos = this.int64Node.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 302 */               propName = this.int64Node.getName();
/* 303 */               propValue = Long.valueOf(this.int64Node.getValue());
/*     */               break;
/*     */             case 9:
/* 306 */               pos = this.nullNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 307 */               propName = this.nullNode.getName();
/* 308 */               propValue = null;
/*     */               break;
/*     */ 
/*     */             
/*     */             case 11:
/* 313 */               unknownNode = new zrfpUnknown(this.env, this.dc);
/* 314 */               pos = unknownNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 315 */               propName = null;
/* 316 */               propValue = null;
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/* 321 */               throwPropTypeException(type, true);
/*     */               break;
/*     */           } 
/*     */         
/*     */         } else {
/* 326 */           pos = this.stringNode.read(buffer, pos, this.readSwap, this.readCp, this.tls);
/* 327 */           propName = this.stringNode.getName();
/* 328 */           propValue = getTypedValue(this.stringNode.getValue(), type);
/*     */         } 
/*     */         
/* 331 */         if (type != 11) {
/* 332 */           assert propName != null : "propName is unexpectedly null";
/* 333 */           Integer typeObject = Utils.getTypeObject(type);
/* 334 */           newTriplet.add(propName, propValue, typeObject);
/*     */         } 
/*     */         
/* 337 */         int loopExitPos = pos;
/* 338 */         if (loopEnterPos == loopExitPos)
/*     */         {
/* 340 */           throwBufferException((Throwable)null);
/*     */         }
/*     */       } 
/*     */       
/* 344 */       this.triplets[this.tripletsIndex++] = newTriplet;
/* 345 */       endPos = pos;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 350 */       endPos = maxPos;
/*     */     } 
/*     */     
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "readParentNode(byte [ ],int)", 
/* 355 */           Integer.valueOf(endPos));
/*     */     }
/* 357 */     return endPos;
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
/*     */   private Object getTypedValue(String stringValue, int type) throws JmqiException {
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "getTypedValue(String,int)", new Object[] { stringValue, 
/* 371 */             Integer.valueOf(type) });
/*     */     }
/* 373 */     assert stringValue != null : "stringValue is unexpectedly null";
/* 374 */     Object typedValue = null; try {
/*     */       int intValue; boolean boolValue;
/* 376 */       switch (type) {
/*     */         case 5:
/* 378 */           typedValue = Integer.valueOf(stringValue);
/*     */           break;
/*     */         case 1:
/* 381 */           intValue = Integer.parseInt(stringValue);
/* 382 */           boolValue = !(intValue == 0);
/* 383 */           typedValue = Boolean.valueOf(boolValue);
/*     */           break;
/*     */         case 7:
/* 386 */           typedValue = Float.valueOf(stringValue);
/*     */           break;
/*     */         case 8:
/* 389 */           typedValue = Double.valueOf(stringValue);
/*     */           break;
/*     */         case 3:
/* 392 */           typedValue = Byte.valueOf(stringValue);
/*     */           break;
/*     */         case 4:
/* 395 */           typedValue = Short.valueOf(stringValue);
/*     */           break;
/*     */         case 6:
/* 398 */           typedValue = Long.valueOf(stringValue);
/*     */           break;
/*     */         case 0:
/*     */         case 2:
/* 402 */           typedValue = stringValue;
/*     */           break;
/*     */         case 9:
/*     */         case 11:
/* 406 */           throwPropConvNotSupportedException((Throwable)null);
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 411 */           throwPropTypeException(type, true);
/*     */           break;
/*     */       } 
/* 414 */     } catch (NumberFormatException nfe) {
/* 415 */       if (Trace.isOn) {
/* 416 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "getTypedValue(String,int)", nfe);
/*     */       }
/*     */ 
/*     */       
/* 420 */       throwPropNumberFormatException(nfe);
/*     */     } 
/*     */     
/* 423 */     assert typedValue != null : "typedValue is unexpectedly null";
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "getTypedValue(String,int)", typedValue);
/*     */     }
/*     */     
/* 428 */     return typedValue;
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
/*     */   private int skipReadingNestedParentNode(byte[] buffer, int startPos) throws JmqiException {
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "skipReadingNestedParentNode(byte [ ],int)", new Object[] { buffer, 
/*     */             
/* 446 */             Integer.valueOf(startPos) });
/*     */     }
/* 448 */     this.parentNode.read(buffer, startPos, this.readSwap, this.readCp, this.tls);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 454 */     int groupLength = (int)this.parentNode.getGroupLength();
/* 455 */     int endPos = startPos + groupLength;
/*     */     
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "skipReadingNestedParentNode(byte [ ],int)", 
/* 459 */           Integer.valueOf(endPos));
/*     */     }
/* 461 */     return endPos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Triplet[] getTriplets() {
/* 470 */     Triplet[] result = new Triplet[this.tripletsIndex];
/* 471 */     for (int i = 0; i < this.tripletsIndex; i++) {
/* 472 */       result[i] = this.triplets[i];
/*     */     }
/*     */     
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.data(this, "com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP", "getTriplets()", "getter", result);
/*     */     }
/*     */     
/* 479 */     return result;
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
/*     */   private boolean isAsStringFlagNotEnabled(int flags) {
/* 491 */     boolean asString = ((flags & 0x1) == 1);
/* 492 */     return !asString;
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
/*     */   private boolean isNonNestedNode(int flags) {
/* 504 */     boolean nested = ((flags & 0x80) == 128);
/* 505 */     return !nested;
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
/*     */   private void throwPropTypeException(int type, boolean unconditionalThrow) throws JmqiException {
/* 517 */     if (unconditionalThrow || type < 0 || type >= 12) {
/* 518 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/* 519 */       throw jmqiEx;
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
/*     */   private void throwBufferException(Throwable nestedException) throws JmqiException {
/* 531 */     JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2004, nestedException);
/* 532 */     throw jmqiEx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void throwInsufficientBufferException(Throwable nestedException) throws JmqiException {
/* 543 */     JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 6113, nestedException);
/* 544 */     throw jmqiEx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void throwPropConvNotSupportedException(Throwable nestedException) throws JmqiException {
/* 555 */     JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2470, nestedException);
/* 556 */     throw jmqiEx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void throwPropNumberFormatException(Throwable nestedException) throws JmqiException {
/* 567 */     JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2472, nestedException);
/* 568 */     throw jmqiEx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void throwStrucLengthException(Throwable nestedException) throws JmqiException {
/* 579 */     JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 6123, nestedException);
/* 580 */     throw jmqiEx;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\ReceiveZRFP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */