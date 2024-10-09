/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ public class RfpASYNC_MESSAGE
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpASYNC_MESSAGE.java";
/*     */   static final int VERSION_OFFSET = 0;
/*     */   static final int HOBJ_OFFSET = 4;
/*     */   static final int MESSAGE_INDEX_OFFSET = 8;
/*     */   static final int GLOBAL_MESSAGE_INDEX_OFFSET = 12;
/*     */   static final int SEGMENT_LENGTH_OFFSET = 16;
/*     */   static final int SEGMENT_INDEX_OFFSET = 20;
/*     */   static final int SELECTION_INDEX_OFFSET = 22;
/*     */   public static final int REASON_CODE_OFFSET = 24;
/*     */   static final int TOTAL_MSG_LENGTH_OFFSET = 28;
/*     */   static final int ACTUAL_MSG_LENGTH_OFFSET = 32;
/*     */   static final int MSG_TOKEN_OFFSET = 36;
/*     */   static final int STATUS_OFFSET = 52;
/*     */   public static final int RESOLVEDQNAMELEN_OFFSET = 54;
/*     */   public static final int SIZE_TO_QNAME_SEG0 = 55;
/*     */   public static final int SIZE_TO_MSG_SEG1 = 24;
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpASYNC_MESSAGE.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpASYNC_MESSAGE(JmqiEnvironment env, byte[] buffer, int offset) {
/* 110 */     super(env, buffer, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion(boolean swap) {
/* 116 */     if (Trace.isOn)
/* 117 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getVersion(boolean)", new Object[] {
/* 118 */             Boolean.valueOf(swap)
/*     */           }); 
/* 120 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getVersion(boolean)", 
/* 124 */           Integer.valueOf(traceRet1));
/*     */     }
/* 126 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHobj(boolean swap) {
/* 132 */     if (Trace.isOn)
/* 133 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getHobj(boolean)", new Object[] {
/* 134 */             Boolean.valueOf(swap)
/*     */           }); 
/* 136 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getHobj(boolean)", 
/* 140 */           Integer.valueOf(traceRet1));
/*     */     }
/* 142 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMessageIndex(boolean swap) {
/* 148 */     if (Trace.isOn)
/* 149 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getMessageIndex(boolean)", new Object[] {
/* 150 */             Boolean.valueOf(swap)
/*     */           }); 
/* 152 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getMessageIndex(boolean)", 
/* 156 */           Integer.valueOf(traceRet1));
/*     */     }
/* 158 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlobalMessageIndex(boolean swap) {
/* 164 */     if (Trace.isOn)
/* 165 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getGlobalMessageIndex(boolean)", new Object[] {
/* 166 */             Boolean.valueOf(swap)
/*     */           }); 
/* 168 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */     
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getGlobalMessageIndex(boolean)", 
/* 172 */           Integer.valueOf(traceRet1));
/*     */     }
/* 174 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSegmentLength(boolean swap) {
/* 180 */     if (Trace.isOn)
/* 181 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getSegmentLength(boolean)", new Object[] {
/* 182 */             Boolean.valueOf(swap)
/*     */           }); 
/* 184 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*     */     
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getSegmentLength(boolean)", 
/* 188 */           Integer.valueOf(traceRet1));
/*     */     }
/* 190 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSegmentIndex(boolean swap) {
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getSegmentIndex(boolean)", new Object[] {
/* 198 */             Boolean.valueOf(swap)
/*     */           }); 
/* 200 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 20, swap);
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getSegmentIndex(boolean)", 
/* 204 */           Integer.valueOf(traceRet1));
/*     */     }
/* 206 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionIndex(boolean swap) {
/* 212 */     if (Trace.isOn)
/* 213 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getSelectionIndex(boolean)", new Object[] {
/* 214 */             Boolean.valueOf(swap)
/*     */           }); 
/* 216 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 22, swap);
/*     */     
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getSelectionIndex(boolean)", 
/* 220 */           Integer.valueOf(traceRet1));
/*     */     }
/* 222 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReasonCode(boolean swap) {
/* 228 */     if (Trace.isOn)
/* 229 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getReasonCode(boolean)", new Object[] {
/* 230 */             Boolean.valueOf(swap)
/*     */           }); 
/* 232 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 24, swap);
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getReasonCode(boolean)", 
/* 236 */           Integer.valueOf(traceRet1));
/*     */     }
/* 238 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public short getStatus(boolean swap) {
/* 244 */     if (Trace.isOn)
/* 245 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getStatus(boolean)", new Object[] {
/* 246 */             Boolean.valueOf(swap)
/*     */           }); 
/* 248 */     short traceRet1 = (short)this.dc.readU16(this.buffer, this.offset + 52, swap);
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getStatus(boolean)", 
/* 252 */           Short.valueOf(traceRet1));
/*     */     }
/* 254 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalMsgLength(boolean swap) {
/* 260 */     if (Trace.isOn)
/* 261 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getTotalMsgLength(boolean)", new Object[] {
/* 262 */             Boolean.valueOf(swap)
/*     */           }); 
/* 264 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 28, swap);
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getTotalMsgLength(boolean)", 
/* 268 */           Integer.valueOf(traceRet1));
/*     */     }
/* 270 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActualMsgLength(boolean swap) {
/* 276 */     if (Trace.isOn)
/* 277 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getActualMsgLength(boolean)", new Object[] {
/* 278 */             Boolean.valueOf(swap)
/*     */           }); 
/* 280 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 32, swap);
/*     */     
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getActualMsgLength(boolean)", 
/* 284 */           Integer.valueOf(traceRet1));
/*     */     }
/* 286 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void getMsgToken(byte[] msgToken) {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "getMsgToken(byte[])", JmqiTools.arrayToHexString(msgToken));
/*     */     }
/*     */     
/* 296 */     System.arraycopy(this.buffer, this.offset + 36, msgToken, 0, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getResolvedQNameLen() {
/* 301 */     int traceRet1 = this.buffer[this.offset + 54];
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "getResolvedQNameLen()", "getter", 
/* 304 */           Integer.valueOf(traceRet1));
/*     */     }
/* 306 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version, boolean swap) {
/* 313 */     if (Trace.isOn)
/* 314 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setVersion(int,boolean)", new Object[] {
/* 315 */             Integer.valueOf(version), Boolean.valueOf(swap)
/*     */           }); 
/* 317 */     this.dc.writeI32(version, this.buffer, this.offset + 0, swap);
/*     */     
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHobj(int hobj, boolean swap) {
/* 329 */     if (Trace.isOn)
/* 330 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setHobj(int,boolean)", new Object[] {
/* 331 */             Integer.valueOf(hobj), Boolean.valueOf(swap)
/*     */           }); 
/* 333 */     this.dc.writeI32(hobj, this.buffer, this.offset + 4, swap);
/*     */     
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setHobj(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageIndex(int messageIndex, boolean swap) {
/* 345 */     if (Trace.isOn)
/* 346 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setMessageIndex(int,boolean)", new Object[] {
/* 347 */             Integer.valueOf(messageIndex), 
/* 348 */             Boolean.valueOf(swap)
/*     */           }); 
/* 350 */     this.dc.writeI32(messageIndex, this.buffer, this.offset + 8, swap);
/*     */     
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setMessageIndex(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlobalMessageIndex(int globalMessageIndex, boolean swap) {
/* 363 */     if (Trace.isOn)
/* 364 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setGlobalMessageIndex(int,boolean)", new Object[] {
/* 365 */             Integer.valueOf(globalMessageIndex), 
/* 366 */             Boolean.valueOf(swap)
/*     */           }); 
/* 368 */     this.dc.writeI32(globalMessageIndex, this.buffer, this.offset + 12, swap);
/*     */     
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setGlobalMessageIndex(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSegmentLength(int segmentLength, boolean swap) {
/* 381 */     if (Trace.isOn)
/* 382 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setSegmentLength(int,boolean)", new Object[] {
/* 383 */             Integer.valueOf(segmentLength), 
/* 384 */             Boolean.valueOf(swap)
/*     */           }); 
/* 386 */     this.dc.writeI32(segmentLength, this.buffer, this.offset + 16, swap);
/*     */     
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setSegmentLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSegmentIndex(int segmentIndex, boolean swap) {
/* 399 */     if (Trace.isOn)
/* 400 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setSegmentIndex(int,boolean)", new Object[] {
/* 401 */             Integer.valueOf(segmentIndex), 
/* 402 */             Boolean.valueOf(swap)
/*     */           }); 
/* 404 */     this.dc.writeU16(segmentIndex, this.buffer, this.offset + 20, swap);
/*     */     
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setSegmentIndex(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectionIndex(int selectionIndex, boolean swap) {
/* 417 */     if (Trace.isOn)
/* 418 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setSelectionIndex(int,boolean)", new Object[] {
/* 419 */             Integer.valueOf(selectionIndex), 
/* 420 */             Boolean.valueOf(swap)
/*     */           }); 
/* 422 */     this.dc.writeU16(selectionIndex, this.buffer, this.offset + 22, swap);
/*     */     
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setSelectionIndex(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReasonCode(int reasonCode, boolean swap) {
/* 435 */     if (Trace.isOn)
/* 436 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setReasonCode(int,boolean)", new Object[] {
/* 437 */             Integer.valueOf(reasonCode), 
/* 438 */             Boolean.valueOf(swap)
/*     */           }); 
/* 440 */     this.dc.writeI32(reasonCode, this.buffer, this.offset + 24, swap);
/*     */     
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setReasonCode(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(int status, boolean swap) {
/* 453 */     if (Trace.isOn)
/* 454 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setStatus(int,boolean)", new Object[] {
/* 455 */             Integer.valueOf(status), Boolean.valueOf(swap)
/*     */           }); 
/* 457 */     this.dc.writeU16(status, this.buffer, this.offset + 52, swap);
/*     */     
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setStatus(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTotalMsgLength(int totalMsgLength, boolean swap) {
/* 469 */     if (Trace.isOn)
/* 470 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setTotalMsgLength(int,boolean)", new Object[] {
/* 471 */             Integer.valueOf(totalMsgLength), 
/* 472 */             Boolean.valueOf(swap)
/*     */           }); 
/* 474 */     this.dc.writeI32(totalMsgLength, this.buffer, this.offset + 28, swap);
/*     */     
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setTotalMsgLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActualMsgLength(int actualMsgLength, boolean swap) {
/* 487 */     if (Trace.isOn)
/* 488 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setActualMsgLength(int,boolean)", new Object[] {
/* 489 */             Integer.valueOf(actualMsgLength), 
/* 490 */             Boolean.valueOf(swap)
/*     */           }); 
/* 492 */     this.dc.writeI32(actualMsgLength, this.buffer, this.offset + 32, swap);
/*     */     
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE", "setActualMsgLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgToken(byte[] msgToken) {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.data(this, "setMsgToken(byte[])", JmqiTools.arrayToHexString(msgToken));
/*     */     }
/*     */     
/* 509 */     System.arraycopy(msgToken, 0, this.buffer, this.offset + 36, 16);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpASYNC_MESSAGE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */