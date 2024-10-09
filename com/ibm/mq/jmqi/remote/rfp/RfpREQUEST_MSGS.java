/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpREQUEST_MSGS
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpREQUEST_MSGS.java";
/*     */   private static final int VERSION_OFFSET = 0;
/*     */   private static final int HOBJ_OFFSET = 4;
/*     */   private static final int RECEIVED_BYTES_OFFSET = 8;
/*     */   private static final int REQUESTED_BYTES_OFFSET = 12;
/*     */   private static final int MAX_MSG_LENGTH_OFFSET = 16;
/*     */   private static final int GET_MSG_OPTIONS_OFFSET = 20;
/*     */   private static final int WAIT_INTERVAL_OFFSET = 24;
/*     */   private static final int QUEUESTATUS_OFFSET = 28;
/*     */   private static final int REQUEST_FLAGS_OFFSET = 32;
/*     */   private static final int GLOBAL_MESSAGE_INDEX_OFFSET = 36;
/*     */   private static final int SELECTION_INDEX_OFFSET = 40;
/*     */   private static final int MQMD_VERSION_OFFSET = 42;
/*     */   private static final int CODEDCHARSETID_OFFSET = 44;
/*     */   private static final int ENCODING_OFFSET = 48;
/*     */   private static final int MSG_SEQ_NUMBER_OFFSET = 52;
/*     */   private static final int OFFSET_OFFSET = 56;
/*     */   private static final int MATCH_OPTIONS_OFFSET = 60;
/*     */   public static final int SIZE_V1_SELECTION_FIXED_PART = 64;
/*     */   public static final int SIZE_V1_NO_SELECTION = 40;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpREQUEST_MSGS.java");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpREQUEST_MSGS(JmqiEnvironment env, byte[] buffer, int offset) {
/* 116 */     super(env, buffer, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion(boolean swap) {
/* 124 */     if (Trace.isOn)
/* 125 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getVersion(boolean)", new Object[] {
/* 126 */             Boolean.valueOf(swap)
/*     */           }); 
/* 128 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getVersion(boolean)", 
/* 132 */           Integer.valueOf(traceRet1));
/*     */     }
/* 134 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHobj(boolean swap) {
/* 142 */     if (Trace.isOn)
/* 143 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getHobj(boolean)", new Object[] {
/* 144 */             Boolean.valueOf(swap)
/*     */           }); 
/* 146 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getHobj(boolean)", 
/* 150 */           Integer.valueOf(traceRet1));
/*     */     }
/* 152 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReceivedBytes(boolean swap) {
/* 160 */     if (Trace.isOn)
/* 161 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getReceivedBytes(boolean)", new Object[] {
/* 162 */             Boolean.valueOf(swap)
/*     */           }); 
/* 164 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getReceivedBytes(boolean)", 
/* 168 */           Integer.valueOf(traceRet1));
/*     */     }
/* 170 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequestedBytes(boolean swap) {
/* 178 */     if (Trace.isOn)
/* 179 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getRequestedBytes(boolean)", new Object[] {
/* 180 */             Boolean.valueOf(swap)
/*     */           }); 
/* 182 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getRequestedBytes(boolean)", 
/* 186 */           Integer.valueOf(traceRet1));
/*     */     }
/* 188 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxMsgLength(boolean swap) {
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMaxMsgLength(boolean)", new Object[] {
/* 198 */             Boolean.valueOf(swap)
/*     */           }); 
/* 200 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMaxMsgLength(boolean)", 
/* 204 */           Integer.valueOf(traceRet1));
/*     */     }
/* 206 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGetMsgOptions(boolean swap) {
/* 214 */     if (Trace.isOn)
/* 215 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getGetMsgOptions(boolean)", new Object[] {
/* 216 */             Boolean.valueOf(swap)
/*     */           }); 
/* 218 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 20, swap);
/*     */     
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getGetMsgOptions(boolean)", 
/* 222 */           Integer.valueOf(traceRet1));
/*     */     }
/* 224 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWaitInterval(boolean swap) {
/* 232 */     if (Trace.isOn)
/* 233 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getWaitInterval(boolean)", new Object[] {
/* 234 */             Boolean.valueOf(swap)
/*     */           }); 
/* 236 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 24, swap);
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getWaitInterval(boolean)", 
/* 240 */           Integer.valueOf(traceRet1));
/*     */     }
/* 242 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQueueStatus(boolean swap) {
/* 250 */     if (Trace.isOn)
/* 251 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getQueueStatus(boolean)", new Object[] {
/* 252 */             Boolean.valueOf(swap)
/*     */           }); 
/* 254 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 28, swap);
/*     */     
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getQueueStatus(boolean)", 
/* 258 */           Integer.valueOf(traceRet1));
/*     */     }
/* 260 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequestFlags(boolean swap) {
/* 268 */     if (Trace.isOn)
/* 269 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getRequestFlags(boolean)", new Object[] {
/* 270 */             Boolean.valueOf(swap)
/*     */           }); 
/* 272 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 32, swap);
/*     */     
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getRequestFlags(boolean)", 
/* 276 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpMF_"));
/*     */     }
/* 278 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlobalMessageIndex(boolean swap) {
/* 286 */     if (Trace.isOn)
/* 287 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getGlobalMessageIndex(boolean)", new Object[] {
/* 288 */             Boolean.valueOf(swap)
/*     */           }); 
/* 290 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 36, swap);
/*     */     
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getGlobalMessageIndex(boolean)", 
/* 294 */           Integer.valueOf(traceRet1));
/*     */     }
/* 296 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionIndex(boolean swap) {
/* 304 */     if (Trace.isOn)
/* 305 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getSelectionIndex(boolean)", new Object[] {
/* 306 */             Boolean.valueOf(swap)
/*     */           }); 
/* 308 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 40, swap);
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getSelectionIndex(boolean)", 
/* 312 */           Integer.valueOf(traceRet1));
/*     */     }
/* 314 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMQMDVersion(boolean swap) {
/* 322 */     if (Trace.isOn)
/* 323 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMQMDVersion(boolean)", new Object[] {
/* 324 */             Boolean.valueOf(swap)
/*     */           }); 
/* 326 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 42, swap);
/*     */     
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMQMDVersion(boolean)", 
/* 330 */           Integer.valueOf(traceRet1));
/*     */     }
/* 332 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId(boolean swap) {
/* 340 */     if (Trace.isOn)
/* 341 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getCodedCharSetId(boolean)", new Object[] {
/* 342 */             Boolean.valueOf(swap)
/*     */           }); 
/* 344 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 44, swap);
/*     */     
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getCodedCharSetId(boolean)", 
/* 348 */           Integer.valueOf(traceRet1));
/*     */     }
/* 350 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding(boolean swap) {
/* 358 */     if (Trace.isOn)
/* 359 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getEncoding(boolean)", new Object[] {
/* 360 */             Boolean.valueOf(swap)
/*     */           }); 
/* 362 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 48, swap);
/*     */     
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getEncoding(boolean)", 
/* 366 */           Integer.valueOf(traceRet1));
/*     */     }
/* 368 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgSeqNumber(boolean swap) {
/* 376 */     if (Trace.isOn)
/* 377 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMsgSeqNumber(boolean)", new Object[] {
/* 378 */             Boolean.valueOf(swap)
/*     */           }); 
/* 380 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 52, swap);
/*     */     
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMsgSeqNumber(boolean)", 
/* 384 */           Integer.valueOf(traceRet1));
/*     */     }
/* 386 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffset(boolean swap) {
/* 394 */     if (Trace.isOn)
/* 395 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getOffset(boolean)", new Object[] {
/* 396 */             Boolean.valueOf(swap)
/*     */           }); 
/* 398 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 56, swap);
/*     */     
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getOffset(boolean)", 
/* 402 */           Integer.valueOf(traceRet1));
/*     */     }
/* 404 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMatchOptions(boolean swap) {
/* 412 */     if (Trace.isOn)
/* 413 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMatchOptions(boolean)", new Object[] {
/* 414 */             Boolean.valueOf(swap)
/*     */           }); 
/* 416 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 60, swap);
/*     */     
/* 418 */     if (Trace.isOn) {
/* 419 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMatchOptions(boolean)", 
/* 420 */           Integer.valueOf(traceRet1));
/*     */     }
/* 422 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getMsgId(byte[] msgId, int msgIdOffset) {
/* 431 */     if (Trace.isOn) {
/* 432 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMsgId(byte [ ],int)", new Object[] { msgId, 
/* 433 */             Integer.valueOf(msgIdOffset) });
/*     */     }
/* 435 */     System.arraycopy(this.buffer, this.offset + msgIdOffset, msgId, 0, 24);
/*     */     
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMsgId(byte [ ],int)");
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
/*     */   public void getCorrelId(byte[] correlId, int correlIdOffset) {
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getCorrelId(byte [ ],int)", new Object[] { correlId, 
/* 452 */             Integer.valueOf(correlIdOffset) });
/*     */     }
/* 454 */     System.arraycopy(this.buffer, this.offset + correlIdOffset, correlId, 0, 24);
/*     */     
/* 456 */     if (Trace.isOn) {
/* 457 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getCorrelId(byte [ ],int)");
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
/*     */   public void getGroupId(byte[] groupId, int groupIdOffset) {
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getGroupId(byte [ ],int)", new Object[] { groupId, 
/* 471 */             Integer.valueOf(groupIdOffset) });
/*     */     }
/* 473 */     System.arraycopy(this.buffer, this.offset + groupIdOffset, groupId, 0, 24);
/*     */     
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getGroupId(byte [ ],int)");
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
/*     */   public void getMsgToken(byte[] msgToken, int msgTokenOffset) {
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMsgToken(byte [ ],int)", new Object[] { msgToken, 
/* 490 */             Integer.valueOf(msgTokenOffset) });
/*     */     }
/* 492 */     System.arraycopy(this.buffer, this.offset + msgTokenOffset, msgToken, 0, 16);
/*     */     
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "getMsgToken(byte [ ],int)");
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
/*     */   public void setVersion(int version, boolean swap) {
/* 507 */     if (Trace.isOn)
/* 508 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setVersion(int,boolean)", new Object[] {
/* 509 */             Integer.valueOf(version), Boolean.valueOf(swap)
/*     */           }); 
/* 511 */     this.dc.writeI32(version, this.buffer, this.offset + 0, swap);
/*     */     
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setVersion(int,boolean)");
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
/*     */   public void setHobj(int hobj, boolean swap) {
/* 526 */     if (Trace.isOn)
/* 527 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setHobj(int,boolean)", new Object[] {
/* 528 */             Integer.valueOf(hobj), Boolean.valueOf(swap)
/*     */           }); 
/* 530 */     this.dc.writeI32(hobj, this.buffer, this.offset + 4, swap);
/*     */     
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setHobj(int,boolean)");
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
/*     */   public void setReceivedBytes(int receivedBytes, boolean swap) {
/* 545 */     if (Trace.isOn)
/* 546 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setReceivedBytes(int,boolean)", new Object[] {
/* 547 */             Integer.valueOf(receivedBytes), Boolean.valueOf(swap)
/*     */           }); 
/* 549 */     this.dc.writeI32(receivedBytes, this.buffer, this.offset + 8, swap);
/*     */     
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setReceivedBytes(int,boolean)");
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
/*     */   public void setRequestedBytes(int requestedBytes, boolean swap) {
/* 564 */     if (Trace.isOn)
/* 565 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setRequestedBytes(int,boolean)", new Object[] {
/* 566 */             Integer.valueOf(requestedBytes), Boolean.valueOf(swap)
/*     */           }); 
/* 568 */     this.dc.writeI32(requestedBytes, this.buffer, this.offset + 12, swap);
/*     */     
/* 570 */     if (Trace.isOn) {
/* 571 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setRequestedBytes(int,boolean)");
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
/*     */   public void setMaxMsgLength(int maxMsgLength, boolean swap) {
/* 583 */     if (Trace.isOn)
/* 584 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMaxMsgLength(int,boolean)", new Object[] {
/* 585 */             Integer.valueOf(maxMsgLength), Boolean.valueOf(swap)
/*     */           }); 
/* 587 */     this.dc.writeI32(maxMsgLength, this.buffer, this.offset + 16, swap);
/*     */     
/* 589 */     if (Trace.isOn) {
/* 590 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMaxMsgLength(int,boolean)");
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
/*     */   public void setGetMsgOptions(int getMsgOptions, boolean swap) {
/* 602 */     if (Trace.isOn)
/* 603 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setGetMsgOptions(int,boolean)", new Object[] {
/* 604 */             Integer.valueOf(getMsgOptions), Boolean.valueOf(swap)
/*     */           }); 
/* 606 */     this.dc.writeI32(getMsgOptions, this.buffer, this.offset + 20, swap);
/*     */     
/* 608 */     if (Trace.isOn) {
/* 609 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setGetMsgOptions(int,boolean)");
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
/*     */   public void setWaitInterval(int waitInterval, boolean swap) {
/* 621 */     if (Trace.isOn)
/* 622 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setWaitInterval(int,boolean)", new Object[] {
/* 623 */             Integer.valueOf(waitInterval), Boolean.valueOf(swap)
/*     */           }); 
/* 625 */     this.dc.writeI32(waitInterval, this.buffer, this.offset + 24, swap);
/*     */     
/* 627 */     if (Trace.isOn) {
/* 628 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setWaitInterval(int,boolean)");
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
/*     */   public void setQueueStatus(int qStatus, boolean swap) {
/* 640 */     if (Trace.isOn)
/* 641 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setQueueStatus(int,boolean)", new Object[] {
/* 642 */             Integer.valueOf(qStatus), Boolean.valueOf(swap)
/*     */           }); 
/* 644 */     this.dc.writeI32(qStatus, this.buffer, this.offset + 28, swap);
/*     */     
/* 646 */     if (Trace.isOn) {
/* 647 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setQueueStatus(int,boolean)");
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
/*     */   public void setRequestFlags(int requestFlags, boolean swap) {
/* 659 */     if (Trace.isOn)
/* 660 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setRequestFlags(int,boolean)", new Object[] {
/* 661 */             RemoteConstantDecoder.formatOptions(requestFlags, "rfpMF_"), 
/* 662 */             Boolean.valueOf(swap)
/*     */           }); 
/* 664 */     this.dc.writeI32(requestFlags, this.buffer, this.offset + 32, swap);
/*     */     
/* 666 */     if (Trace.isOn) {
/* 667 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setRequestFlags(int,boolean)");
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
/*     */   public void setGlobalMessageIndex(int globalMessageIndex, boolean swap) {
/* 679 */     if (Trace.isOn)
/* 680 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setGlobalMessageIndex(int,boolean)", new Object[] {
/* 681 */             Integer.valueOf(globalMessageIndex), Boolean.valueOf(swap)
/*     */           }); 
/* 683 */     this.dc.writeI32(globalMessageIndex, this.buffer, this.offset + 36, swap);
/*     */     
/* 685 */     if (Trace.isOn) {
/* 686 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setGlobalMessageIndex(int,boolean)");
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
/*     */   public void setSelectionIndex(int selectionIndex, boolean swap) {
/* 698 */     if (Trace.isOn)
/* 699 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setSelectionIndex(int,boolean)", new Object[] {
/* 700 */             Integer.valueOf(selectionIndex), Boolean.valueOf(swap)
/*     */           }); 
/* 702 */     this.dc.writeU16(selectionIndex, this.buffer, this.offset + 40, swap);
/*     */     
/* 704 */     if (Trace.isOn) {
/* 705 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setSelectionIndex(int,boolean)");
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
/*     */   public void setMQMDVersion(int mqmdVersion, boolean swap) {
/* 717 */     if (Trace.isOn)
/* 718 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMQMDVersion(int,boolean)", new Object[] {
/* 719 */             Integer.valueOf(mqmdVersion), Boolean.valueOf(swap)
/*     */           }); 
/* 721 */     this.dc.writeU16(mqmdVersion, this.buffer, this.offset + 42, swap);
/*     */     
/* 723 */     if (Trace.isOn) {
/* 724 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMQMDVersion(int,boolean)");
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
/*     */   public void setCodedCharSetId(int ccsid, boolean swap) {
/* 736 */     if (Trace.isOn)
/* 737 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setCodedCharSetId(int,boolean)", new Object[] {
/* 738 */             Integer.valueOf(ccsid), Boolean.valueOf(swap)
/*     */           }); 
/* 740 */     this.dc.writeI32(ccsid, this.buffer, this.offset + 44, swap);
/*     */     
/* 742 */     if (Trace.isOn) {
/* 743 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setCodedCharSetId(int,boolean)");
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
/*     */   public void setEncoding(int encoding, boolean swap) {
/* 755 */     if (Trace.isOn)
/* 756 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setEncoding(int,boolean)", new Object[] {
/* 757 */             Integer.valueOf(encoding), Boolean.valueOf(swap)
/*     */           }); 
/* 759 */     this.dc.writeI32(encoding, this.buffer, this.offset + 48, swap);
/*     */     
/* 761 */     if (Trace.isOn) {
/* 762 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setEncoding(int,boolean)");
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
/*     */   public void setMsgSeqNumber(int msgSeqNumber, boolean swap) {
/* 774 */     if (Trace.isOn)
/* 775 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMsgSeqNumber(int,boolean)", new Object[] {
/* 776 */             Integer.valueOf(msgSeqNumber), Boolean.valueOf(swap)
/*     */           }); 
/* 778 */     this.dc.writeI32(msgSeqNumber, this.buffer, this.offset + 52, swap);
/*     */     
/* 780 */     if (Trace.isOn) {
/* 781 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMsgSeqNumber(int,boolean)");
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
/*     */   public void setOffset(int physicalMsgOffset, boolean swap) {
/* 793 */     if (Trace.isOn)
/* 794 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setOffset(int,boolean)", new Object[] {
/* 795 */             Integer.valueOf(physicalMsgOffset), Boolean.valueOf(swap)
/*     */           }); 
/* 797 */     this.dc.writeI32(physicalMsgOffset, this.buffer, this.offset + 56, swap);
/*     */     
/* 799 */     if (Trace.isOn) {
/* 800 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setOffset(int,boolean)");
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
/*     */   public void setMatchOptions(int matchOptions, boolean swap) {
/* 812 */     if (Trace.isOn)
/* 813 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMatchOptions(int,boolean)", new Object[] {
/* 814 */             Integer.valueOf(matchOptions), Boolean.valueOf(swap)
/*     */           }); 
/* 816 */     this.dc.writeI32(matchOptions, this.buffer, this.offset + 60, swap);
/*     */     
/* 818 */     if (Trace.isOn) {
/* 819 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMatchOptions(int,boolean)");
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
/*     */   public void setMsgId(byte[] msgId, int msgIdOffset) {
/* 831 */     if (Trace.isOn) {
/* 832 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMsgId(byte [ ],int)", new Object[] { msgId, 
/* 833 */             Integer.valueOf(msgIdOffset) });
/*     */     }
/* 835 */     System.arraycopy(msgId, 0, this.buffer, this.offset + msgIdOffset, 24);
/*     */     
/* 837 */     if (Trace.isOn) {
/* 838 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMsgId(byte [ ],int)");
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
/*     */   public void setCorrelId(byte[] correlId, int correlIdOffset) {
/* 850 */     if (Trace.isOn) {
/* 851 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setCorrelId(byte [ ],int)", new Object[] { correlId, 
/* 852 */             Integer.valueOf(correlIdOffset) });
/*     */     }
/* 854 */     System.arraycopy(correlId, 0, this.buffer, this.offset + correlIdOffset, 24);
/*     */     
/* 856 */     if (Trace.isOn) {
/* 857 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setCorrelId(byte [ ],int)");
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
/*     */   public void setGroupId(byte[] groupId, int groupIdOffset) {
/* 869 */     if (Trace.isOn) {
/* 870 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setGroupId(byte [ ],int)", new Object[] { groupId, 
/* 871 */             Integer.valueOf(groupIdOffset) });
/*     */     }
/* 873 */     System.arraycopy(groupId, 0, this.buffer, this.offset + groupIdOffset, 24);
/*     */     
/* 875 */     if (Trace.isOn) {
/* 876 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setGroupId(byte [ ],int)");
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
/*     */   public void setMsgToken(byte[] msgToken, int msgTokenOffset) {
/* 888 */     if (Trace.isOn) {
/* 889 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMsgToken(byte [ ],int)", new Object[] { msgToken, 
/* 890 */             Integer.valueOf(msgTokenOffset) });
/*     */     }
/* 892 */     System.arraycopy(msgToken, 0, this.buffer, this.offset + msgTokenOffset, 16);
/*     */     
/* 894 */     if (Trace.isOn)
/* 895 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS", "setMsgToken(byte [ ],int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpREQUEST_MSGS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */