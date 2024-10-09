/*      */ package com.ibm.mq.jmqi.remote.rfp;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RfpID
/*      */   extends RfpStructure
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpID.java";
/*      */   
/*      */   static {
/*   44 */     if (Trace.isOn) {
/*   45 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpID.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RfpID(JmqiEnvironment env, byte[] buffer, int offset) {
/*   60 */     super(env, buffer, offset);
/*      */   }
/*      */   
/*   63 */   private static final byte[] rfpID_ID = new byte[] { 73, 68, 32, 32 };
/*      */   
/*   65 */   private static final byte[] rfpID_ID_ASCII = new byte[] { 73, 68, 32, 32 };
/*      */   
/*   67 */   private static final byte[] rfpID_ID_EBCDIC = new byte[] { -55, -60, 64, 64 };
/*      */ 
/*      */   
/*      */   private static final int FAP_LEVEL_OFFSET = 4;
/*      */ 
/*      */   
/*      */   private static final int ID_FLAGS_OFFSET = 5;
/*      */ 
/*      */   
/*      */   private static final int IDE_FLAGS_OFFSET = 6;
/*      */ 
/*      */   
/*      */   private static final int ERR_FLAGS_OFFSET = 7;
/*      */ 
/*      */   
/*      */   private static final int RESERVED_OFFSET = 8;
/*      */ 
/*      */   
/*      */   private static final int MAX_MESSAGES_PER_BATCH_OFFSET = 10;
/*      */ 
/*      */   
/*      */   private static final int MAX_TRANSMISSION_SIZE_OFFSET = 12;
/*      */ 
/*      */   
/*      */   private static final int MAX_MESSAGE_SIZE_OFFSET = 16;
/*      */ 
/*      */   
/*      */   private static final int MESSAGE_SEQUENCE_WRAP_VALUE_OFFSET = 20;
/*      */ 
/*      */   
/*      */   private static final int CHANNEL_NAME_OFFSET = 24;
/*      */ 
/*      */   
/*      */   private static final int ID_FLAGS_2_OFFSET = 44;
/*      */ 
/*      */   
/*      */   private static final int IDE_FLAGS_2_OFFSET = 45;
/*      */ 
/*      */   
/*      */   private static final int CCSID_OFFSET = 46;
/*      */ 
/*      */   
/*      */   private static final int QUEUE_MANAGER_NAME_OFFSET = 48;
/*      */ 
/*      */   
/*      */   private static final int HEARTBEAT_INTERVAL_OFFSET = 96;
/*      */ 
/*      */   
/*      */   private static final int EFL_LENGTH_OFFSET = 100;
/*      */ 
/*      */   
/*      */   private static final int ERR_FLAGS_2_OFFSET = 102;
/*      */ 
/*      */   
/*      */   private static final int RESERVED_1_OFFSET = 103;
/*      */ 
/*      */   
/*      */   private static final int HDR_COMP_LIST_OFFSET = 104;
/*      */ 
/*      */   
/*      */   private static final int MSG_COMP_LIST_OFFSET = 106;
/*      */ 
/*      */   
/*      */   private static final int RESERVED_2_OFFSET = 122;
/*      */ 
/*      */   
/*      */   private static final int SSL_KEY_RESET_OFFSET = 124;
/*      */ 
/*      */   
/*      */   private static final int CONVPERSOCKET_OFFSET = 128;
/*      */ 
/*      */   
/*      */   private static final int ID_FLAGS_3_OFFSET = 132;
/*      */ 
/*      */   
/*      */   private static final int IDE_FLAGS_3_OFFSET = 133;
/*      */ 
/*      */   
/*      */   private static final int RESERVED_3_OFFSET = 134;
/*      */ 
/*      */   
/*      */   private static final int PROCESSIDENTIFIER_OFFSET = 136;
/*      */ 
/*      */   
/*      */   private static final int THREADIDENTIFIER_OFFSET = 140;
/*      */ 
/*      */   
/*      */   private static final int TRACEIDENTIFIER_OFFSET = 144;
/*      */ 
/*      */   
/*      */   private static final int PRODUCTIDENTIFIER_OFFSET = 148;
/*      */ 
/*      */   
/*      */   private static final int QUEUE_MANAGER_IDENTIFIER_OFFSET = 160;
/*      */ 
/*      */   
/*      */   private static final int PAL_OFFSET = 208;
/*      */ 
/*      */   
/*      */   private static final int R_OFFSET = 228;
/*      */ 
/*      */   
/*      */   public static final int SIZE_FAP2 = 44;
/*      */ 
/*      */   
/*      */   public static final int SIZE_FAP3 = 48;
/*      */ 
/*      */   
/*      */   public static final int SIZE_FAP7 = 102;
/*      */ 
/*      */   
/*      */   public static final int SIZE_FAP8 = 128;
/*      */   
/*      */   public static final int SIZE_FAP9 = 160;
/*      */   
/*      */   public static final int SIZE_FAP10 = 208;
/*      */   
/*      */   public static final int SIZE_FAP13 = 240;
/*      */   
/*      */   public static final int SIZE_CURRENT = 240;
/*      */ 
/*      */   
/*      */   public void initEyecatcher() {
/*  190 */     System.arraycopy(rfpID_ID, 0, this.buffer, this.offset, rfpID_ID.length);
/*      */     
/*  192 */     this.dc.clear(this.buffer, this.offset + 8, 2);
/*  193 */     this.dc.clear(this.buffer, this.offset + 103, 1);
/*  194 */     this.dc.clear(this.buffer, this.offset + 122, 2);
/*  195 */     this.dc.clear(this.buffer, this.offset + 134, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFapLevel(int fapLevel) {
/*  201 */     if (Trace.isOn) {
/*  202 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setFapLevel(int)", "setter", 
/*  203 */           Integer.valueOf(fapLevel));
/*      */     }
/*      */     
/*  206 */     this.buffer[this.offset + 4] = (byte)fapLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIDFlags(int idFlags) {
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setIDFlags(int)", "setter", 
/*  214 */           RemoteConstantDecoder.formatOptions(idFlags, "rfpICF_"));
/*      */     }
/*      */     
/*  217 */     this.buffer[this.offset + 5] = (byte)idFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIDEFlags(int ideFlags) {
/*  223 */     if (Trace.isOn) {
/*  224 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setIDEFlags(int)", "setter", 
/*  225 */           RemoteConstantDecoder.formatOptions(ideFlags, "rfpIEF_"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  230 */     this.buffer[this.offset + 6] = (byte)ideFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setErrFlags(int errFlags) {
/*  236 */     if (Trace.isOn) {
/*  237 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setErrFlags(int)", "setter", 
/*  238 */           RemoteConstantDecoder.formatOptions(errFlags, "rfpERR_"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  243 */     this.buffer[this.offset + 7] = (byte)errFlags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxMessagesPerBatch(int maxMessagesPerBatch, boolean swap) {
/*  250 */     if (Trace.isOn)
/*  251 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMaxMessagesPerBatch(int,boolean)", new Object[] {
/*  252 */             Integer.valueOf(maxMessagesPerBatch), Boolean.valueOf(swap)
/*      */           }); 
/*  254 */     this.dc.writeU16(maxMessagesPerBatch, this.buffer, this.offset + 10, swap);
/*      */     
/*  256 */     if (Trace.isOn) {
/*  257 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMaxMessagesPerBatch(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxTransmissionSize(int maxTransmissionSize, boolean swap) {
/*  266 */     if (Trace.isOn)
/*  267 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMaxTransmissionSize(int,boolean)", new Object[] {
/*  268 */             Integer.valueOf(maxTransmissionSize), Boolean.valueOf(swap)
/*      */           }); 
/*  270 */     this.dc.writeI32(maxTransmissionSize, this.buffer, this.offset + 12, swap);
/*      */     
/*  272 */     if (Trace.isOn) {
/*  273 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMaxTransmissionSize(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxMessageSize(int maxMessageSize, boolean swap) {
/*  282 */     if (Trace.isOn)
/*  283 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMaxMessageSize(int,boolean)", new Object[] {
/*  284 */             Integer.valueOf(maxMessageSize), Boolean.valueOf(swap)
/*      */           }); 
/*  286 */     this.dc.writeI32(maxMessageSize, this.buffer, this.offset + 16, swap);
/*      */     
/*  288 */     if (Trace.isOn) {
/*  289 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMaxMessageSize(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageSequenceWrapValue(int messageSequenceWrapValue, boolean swap) {
/*  298 */     if (Trace.isOn)
/*  299 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMessageSequenceWrapValue(int,boolean)", new Object[] {
/*      */             
/*  301 */             Integer.valueOf(messageSequenceWrapValue), Boolean.valueOf(swap)
/*      */           }); 
/*  303 */     this.dc.writeI32(messageSequenceWrapValue, this.buffer, this.offset + 20, swap);
/*      */     
/*  305 */     if (Trace.isOn) {
/*  306 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMessageSequenceWrapValue(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChannelName(String channelName, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  318 */     if (Trace.isOn) {
/*  319 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setChannelName(String,JmqiCodepage,JmqiTls)", new Object[] { channelName, cp, tls });
/*      */     }
/*      */     
/*  322 */     this.dc.writeMQField(channelName, this.buffer, this.offset + 24, 20, cp, tls);
/*      */     
/*  324 */     if (Trace.isOn) {
/*  325 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setChannelName(String,JmqiCodepage,JmqiTls)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIDFlags2(int idFlags2) {
/*  334 */     if (Trace.isOn) {
/*  335 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setIDFlags2(int)", "setter", 
/*  336 */           RemoteConstantDecoder.formatOptions(idFlags2, "rfpICF2_"));
/*      */     }
/*      */     
/*  339 */     this.buffer[this.offset + 44] = (byte)idFlags2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIDEFlags2(int ideFlags2) {
/*  345 */     if (Trace.isOn) {
/*  346 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setIDEFlags2(int)", "setter", 
/*  347 */           RemoteConstantDecoder.formatOptions(ideFlags2, "rfpIEF2_"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  352 */     this.buffer[this.offset + 45] = (byte)ideFlags2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCcsid(int ccsid, boolean swap) {
/*  359 */     if (Trace.isOn)
/*  360 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setCcsid(int,boolean)", new Object[] {
/*  361 */             Integer.valueOf(ccsid), Boolean.valueOf(swap)
/*      */           }); 
/*  363 */     this.dc.writeU16(ccsid, this.buffer, this.offset + 46, swap);
/*      */     
/*  365 */     if (Trace.isOn) {
/*  366 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setCcsid(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQueueManagerName(String queueManagerName, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  377 */     if (Trace.isOn) {
/*  378 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setQueueManagerName(String,JmqiCodepage,JmqiTls)", new Object[] { queueManagerName, cp, tls });
/*      */     }
/*      */ 
/*      */     
/*  382 */     this.dc.writeMQField(queueManagerName, this.buffer, this.offset + 48, 48, cp, tls);
/*      */     
/*  384 */     if (Trace.isOn) {
/*  385 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setQueueManagerName(String,JmqiCodepage,JmqiTls)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeartbeatInterval(int heartbeatInterval, boolean swap) {
/*  395 */     if (Trace.isOn)
/*  396 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setHeartbeatInterval(int,boolean)", new Object[] {
/*  397 */             Integer.valueOf(heartbeatInterval), Boolean.valueOf(swap)
/*      */           }); 
/*  399 */     this.dc.writeI32(heartbeatInterval, this.buffer, this.offset + 96, swap);
/*      */     
/*  401 */     if (Trace.isOn) {
/*  402 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setHeartbeatInterval(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEFLLength(int eflLength, boolean swap) {
/*  411 */     if (Trace.isOn)
/*  412 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setEFLLength(int,boolean)", new Object[] {
/*  413 */             Integer.valueOf(eflLength), Boolean.valueOf(swap)
/*      */           }); 
/*  415 */     this.dc.writeU16(eflLength, this.buffer, this.offset + 100, swap);
/*      */     
/*  417 */     if (Trace.isOn) {
/*  418 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setEFLLength(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEFLLength(boolean swap) {
/*  427 */     setEFLLength(138, swap);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setErrFlags2(int errFlags2) {
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setErrFlags2(int)", "setter", 
/*  435 */           Integer.valueOf(errFlags2));
/*      */     }
/*      */     
/*  438 */     this.buffer[this.offset + 102] = (byte)errFlags2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHdrCompList(int[] hdrCompList) {
/*  444 */     if (Trace.isOn) {
/*  445 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setHdrCompList(int [ ])", "setter", hdrCompList);
/*      */     }
/*      */     
/*  448 */     int currentOffset = this.offset + 104;
/*  449 */     for (int i = 0; i < 2; i++) {
/*  450 */       if (hdrCompList != null && hdrCompList.length > i) {
/*  451 */         this.buffer[currentOffset] = (byte)hdrCompList[i];
/*      */       } else {
/*      */         
/*  454 */         this.buffer[currentOffset] = -1;
/*      */       } 
/*  456 */       currentOffset++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgCompList(int[] msgCompList) {
/*  464 */     if (Trace.isOn) {
/*  465 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setMsgCompList(int [ ])", "setter", msgCompList);
/*      */     }
/*      */     
/*  468 */     int currentOffset = this.offset + 106;
/*  469 */     for (int i = 0; i < 16; i++) {
/*  470 */       if (msgCompList != null && msgCompList.length > i) {
/*  471 */         this.buffer[currentOffset] = (byte)msgCompList[i];
/*      */       } else {
/*      */         
/*  474 */         this.buffer[currentOffset] = -1;
/*      */       } 
/*  476 */       currentOffset++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPal(int[] pal, boolean swap) {
/*  491 */     int currentOffset = this.offset + 208;
/*      */     
/*  493 */     for (int i = 0; i < 10; i++) {
/*  494 */       if (pal != null && pal.length > i) {
/*  495 */         this.dc.writeU16(pal[i], this.buffer, currentOffset, swap);
/*      */       } else {
/*      */         
/*  498 */         this.dc.writeU16(65535, this.buffer, currentOffset, swap);
/*      */       } 
/*  500 */       currentOffset += 2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setR(byte[] r) {
/*  513 */     int currentOffset = this.offset + 228;
/*  514 */     System.arraycopy(r, 0, this.buffer, currentOffset, 12);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSSLKeyReset(int sslKeyReset, boolean swap) {
/*  523 */     if (Trace.isOn)
/*  524 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setSSLKeyReset(int,boolean)", new Object[] {
/*  525 */             Integer.valueOf(sslKeyReset), Boolean.valueOf(swap)
/*      */           }); 
/*  527 */     this.dc.writeI32(sslKeyReset, this.buffer, this.offset + 124, swap);
/*      */     
/*  529 */     if (Trace.isOn) {
/*  530 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setSSLKeyReset(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConvPerSocket(int convPerSocket, boolean swap) {
/*  539 */     if (Trace.isOn)
/*  540 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setConvPerSocket(int,boolean)", new Object[] {
/*  541 */             Integer.valueOf(convPerSocket), Boolean.valueOf(swap)
/*      */           }); 
/*  543 */     this.dc.writeI32(convPerSocket, this.buffer, this.offset + 128, swap);
/*      */     
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setConvPerSocket(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIDFlags3(int idFlags3) {
/*  554 */     if (Trace.isOn) {
/*  555 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setIDFlags3(int)", "setter", 
/*  556 */           RemoteConstantDecoder.formatOptions(idFlags3, "rfpICF3_"));
/*      */     }
/*  558 */     this.buffer[this.offset + 132] = (byte)idFlags3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIDEFlags3(int ideFlags3) {
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setIDEFlags3(int)", "setter", 
/*  566 */           RemoteConstantDecoder.formatOptions(ideFlags3, "rfpICF_"));
/*      */     }
/*  568 */     this.buffer[this.offset + 133] = (byte)ideFlags3;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFapLevel() {
/*  573 */     int traceRet1 = this.buffer[this.offset + 4] & 0xFF;
/*      */     
/*  575 */     if (Trace.isOn) {
/*  576 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getFapLevel()", "getter", 
/*  577 */           Integer.valueOf(traceRet1));
/*      */     }
/*  579 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIDFlags() {
/*  584 */     int traceRet1 = this.buffer[this.offset + 5] & 0xFF;
/*      */     
/*  586 */     if (Trace.isOn) {
/*  587 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getIDFlags()", "getter", 
/*  588 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpICF_"));
/*      */     }
/*  590 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIDEFlags() {
/*  595 */     int traceRet1 = this.buffer[this.offset + 6] & 0xFF;
/*      */     
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getIDEFlags()", "getter", 
/*  599 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpIEF_"));
/*      */     }
/*  601 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getErrFlags() {
/*  606 */     int traceRet1 = this.buffer[this.offset + 7] & 0xFF;
/*      */     
/*  608 */     if (Trace.isOn) {
/*  609 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getErrFlags()", "getter", 
/*  610 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpERR_"));
/*      */     }
/*  612 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxMessagesPerBatch(boolean swap) {
/*  618 */     if (Trace.isOn)
/*  619 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMaxMessagesPerBatch(boolean)", new Object[] {
/*  620 */             Boolean.valueOf(swap)
/*      */           }); 
/*  622 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 10, swap);
/*      */     
/*  624 */     if (Trace.isOn) {
/*  625 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMaxMessagesPerBatch(boolean)", 
/*  626 */           Integer.valueOf(traceRet1));
/*      */     }
/*  628 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxTransmissionSize(boolean swap) {
/*  634 */     if (Trace.isOn)
/*  635 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMaxTransmissionSize(boolean)", new Object[] {
/*  636 */             Boolean.valueOf(swap)
/*      */           }); 
/*  638 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*      */     
/*  640 */     if (Trace.isOn) {
/*  641 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMaxTransmissionSize(boolean)", 
/*  642 */           Integer.valueOf(traceRet1));
/*      */     }
/*  644 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxMessageSize(boolean swap) {
/*  650 */     if (Trace.isOn)
/*  651 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMaxMessageSize(boolean)", new Object[] {
/*  652 */             Boolean.valueOf(swap)
/*      */           }); 
/*  654 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*      */     
/*  656 */     if (Trace.isOn) {
/*  657 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMaxMessageSize(boolean)", 
/*  658 */           Integer.valueOf(traceRet1));
/*      */     }
/*  660 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageSequenceWrapValue(boolean swap) {
/*  666 */     if (Trace.isOn)
/*  667 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMessageSequenceWrapValue(boolean)", new Object[] {
/*  668 */             Boolean.valueOf(swap)
/*      */           }); 
/*  670 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 20, swap);
/*      */     
/*  672 */     if (Trace.isOn) {
/*  673 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMessageSequenceWrapValue(boolean)", 
/*  674 */           Integer.valueOf(traceRet1));
/*      */     }
/*  676 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getChannelName(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  684 */     if (Trace.isOn) {
/*  685 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getChannelName(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*      */     }
/*      */     
/*  688 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 24, 20, cp, tls);
/*      */     
/*  690 */     if (Trace.isOn) {
/*  691 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getChannelName(JmqiCodepage,JmqiTls)", traceRet1);
/*      */     }
/*      */     
/*  694 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIDFlags2() {
/*  699 */     int traceRet1 = this.buffer[this.offset + 44] & 0xFF;
/*      */     
/*  701 */     if (Trace.isOn) {
/*  702 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getIDFlags2()", "getter", 
/*  703 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpICF2_"));
/*      */     }
/*  705 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIDEFlags2() {
/*  710 */     int traceRet1 = this.buffer[this.offset + 45] & 0xFF;
/*      */     
/*  712 */     if (Trace.isOn) {
/*  713 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getIDEFlags2()", "getter", 
/*  714 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpIEF2_"));
/*      */     }
/*  716 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCcsid(boolean swap) {
/*  722 */     if (Trace.isOn)
/*  723 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getCcsid(boolean)", new Object[] {
/*  724 */             Boolean.valueOf(swap)
/*      */           }); 
/*  726 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 46, swap);
/*      */     
/*  728 */     if (Trace.isOn) {
/*  729 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getCcsid(boolean)", 
/*  730 */           Integer.valueOf(traceRet1));
/*      */     }
/*  732 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQueueManagerName(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  740 */     if (Trace.isOn) {
/*  741 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getQueueManagerName(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*      */     }
/*      */     
/*  744 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 48, 48, cp, tls);
/*      */     
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getQueueManagerName(JmqiCodepage,JmqiTls)", traceRet1);
/*      */     }
/*      */     
/*  750 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeartbeatInterval(boolean swap) {
/*  756 */     if (Trace.isOn)
/*  757 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getHeartbeatInterval(boolean)", new Object[] {
/*  758 */             Boolean.valueOf(swap)
/*      */           }); 
/*  760 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 96, swap);
/*      */     
/*  762 */     if (Trace.isOn) {
/*  763 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getHeartbeatInterval(boolean)", 
/*  764 */           Integer.valueOf(traceRet1));
/*      */     }
/*  766 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEFLLength(boolean swap) {
/*  772 */     if (Trace.isOn)
/*  773 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getEFLLength(boolean)", new Object[] {
/*  774 */             Boolean.valueOf(swap)
/*      */           }); 
/*  776 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + 100, swap);
/*      */     
/*  778 */     if (Trace.isOn) {
/*  779 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getEFLLength(boolean)", 
/*  780 */           Integer.valueOf(traceRet1));
/*      */     }
/*  782 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getErrFlags2() {
/*  787 */     int traceRet1 = this.buffer[this.offset + 102] & 0xFF;
/*      */     
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getErrFlags2()", "getter", 
/*  791 */           Integer.valueOf(traceRet1));
/*      */     }
/*  793 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int[] getHdrCompList() {
/*  798 */     int[] newHdrCompList = new int[2];
/*  799 */     int currentOffset = this.offset + 104;
/*  800 */     for (int i = 0; i < 2; i++) {
/*  801 */       newHdrCompList[i] = this.buffer[currentOffset] & 0xFF;
/*  802 */       currentOffset++;
/*      */     } 
/*      */     
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getHdrCompList()", "getter", newHdrCompList);
/*      */     }
/*      */     
/*  809 */     return newHdrCompList;
/*      */   }
/*      */ 
/*      */   
/*      */   public int[] getMsgCompList() {
/*  814 */     int[] newMsgCompList = new int[16];
/*  815 */     int currentOffset = this.offset + 106;
/*  816 */     for (int i = 0; i < 16; i++) {
/*  817 */       newMsgCompList[i] = this.buffer[currentOffset] & 0xFF;
/*  818 */       currentOffset++;
/*      */     } 
/*      */     
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getMsgCompList()", "getter", newMsgCompList);
/*      */     }
/*      */     
/*  825 */     return newMsgCompList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSSLKeyReset(boolean swap) {
/*  831 */     if (Trace.isOn)
/*  832 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getSSLKeyReset(boolean)", new Object[] {
/*  833 */             Boolean.valueOf(swap)
/*      */           }); 
/*  835 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 124, swap);
/*      */     
/*  837 */     if (Trace.isOn) {
/*  838 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getSSLKeyReset(boolean)", 
/*  839 */           Integer.valueOf(traceRet1));
/*      */     }
/*  841 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConvPerSocket(boolean swap) {
/*  847 */     if (Trace.isOn)
/*  848 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getConvPerSocket(boolean)", new Object[] {
/*  849 */             Boolean.valueOf(swap)
/*      */           }); 
/*  851 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 128, swap);
/*      */     
/*  853 */     if (Trace.isOn) {
/*  854 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getConvPerSocket(boolean)", 
/*  855 */           Integer.valueOf(traceRet1));
/*      */     }
/*  857 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIDFlags3() {
/*  862 */     int traceRet1 = this.buffer[this.offset + 132] & 0xFF;
/*  863 */     if (Trace.isOn) {
/*  864 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getIDFlags3()", "getter", 
/*  865 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpICF3_"));
/*      */     }
/*  867 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIDEFlags3() {
/*  872 */     int traceRet1 = this.buffer[this.offset + 133] & 0xFF;
/*  873 */     if (Trace.isOn) {
/*  874 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getIDEFlags3()", "getter", 
/*  875 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpIEF3_"));
/*      */     }
/*  877 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getProcessId(boolean swap) {
/*  883 */     if (Trace.isOn)
/*  884 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getProcessId(boolean)", new Object[] {
/*  885 */             Boolean.valueOf(swap)
/*      */           }); 
/*  887 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 136, swap);
/*      */     
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getProcessId(boolean)", 
/*  891 */           Integer.valueOf(traceRet1));
/*      */     }
/*  893 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getThreadId(boolean swap) {
/*  899 */     if (Trace.isOn)
/*  900 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getThreadId(boolean)", new Object[] {
/*  901 */             Boolean.valueOf(swap)
/*      */           }); 
/*  903 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 140, swap);
/*      */     
/*  905 */     if (Trace.isOn) {
/*  906 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getThreadId(boolean)", 
/*  907 */           Integer.valueOf(traceRet1));
/*      */     }
/*  909 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTraceId(boolean swap) {
/*  915 */     if (Trace.isOn)
/*  916 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getTraceId(boolean)", new Object[] {
/*  917 */             Boolean.valueOf(swap)
/*      */           }); 
/*  919 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 144, swap);
/*      */     
/*  921 */     if (Trace.isOn) {
/*  922 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getTraceId(boolean)", 
/*  923 */           Integer.valueOf(traceRet1));
/*      */     }
/*  925 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getPal(boolean swap) {
/*  933 */     if (Trace.isOn) {
/*  934 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getPal(boolean)", new Object[] {
/*  935 */             Boolean.valueOf(swap)
/*      */           });
/*      */     }
/*  938 */     int[] result = new int[10];
/*  939 */     int currentOffset = this.offset + 208;
/*      */     
/*  941 */     for (int i = 0; i < 10; i++) {
/*  942 */       result[i] = this.dc.readU16(this.buffer, currentOffset, swap);
/*  943 */       currentOffset += 2;
/*      */     } 
/*      */     
/*  946 */     if (Trace.isOn) {
/*  947 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getPal(boolean)", result);
/*      */     }
/*  949 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getR() {
/*  957 */     byte[] result = new byte[12];
/*  958 */     int currentOffset = this.offset + 228;
/*      */     
/*  960 */     System.arraycopy(this.buffer, currentOffset, result, 0, 12);
/*      */     
/*  962 */     if (Trace.isOn) {
/*  963 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getR()", "getter", result);
/*      */     }
/*  965 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProductId(boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  974 */     if (Trace.isOn) {
/*  975 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getProductId(boolean,JmqiCodepage,JmqiTls)", new Object[] {
/*  976 */             Boolean.valueOf(swap), cp, tls
/*      */           });
/*      */     }
/*  979 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 148, 12, cp, tls);
/*      */     
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getProductId(boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*      */     }
/*      */     
/*  985 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQueueManagerId(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  993 */     if (Trace.isOn) {
/*  994 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getQueueManagerId(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*      */     }
/*      */     
/*  997 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 160, 48, cp, tls);
/*      */     
/*  999 */     if (Trace.isOn) {
/* 1000 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "getQueueManagerId(JmqiCodepage,JmqiTls)", traceRet1);
/*      */     }
/*      */     
/* 1003 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProcessIdentifier(int procId, boolean swap) {
/* 1010 */     if (Trace.isOn)
/* 1011 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setProcessIdentifier(int,boolean)", new Object[] {
/* 1012 */             Integer.valueOf(procId), Boolean.valueOf(swap)
/*      */           }); 
/* 1014 */     this.dc.writeI32(procId, this.buffer, this.offset + 136, swap);
/*      */     
/* 1016 */     if (Trace.isOn) {
/* 1017 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setProcessIdentifier(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setThreadIdentifier(int threadId, boolean swap) {
/* 1026 */     if (Trace.isOn)
/* 1027 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setThreadIdentifier(int,boolean)", new Object[] {
/* 1028 */             Integer.valueOf(threadId), Boolean.valueOf(swap)
/*      */           }); 
/* 1030 */     this.dc.writeI32(threadId, this.buffer, this.offset + 140, swap);
/*      */     
/* 1032 */     if (Trace.isOn) {
/* 1033 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setThreadIdentifier(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTraceIdentifier(int traceId, boolean swap) {
/* 1042 */     if (Trace.isOn)
/* 1043 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setTraceIdentifier(int,boolean)", new Object[] {
/* 1044 */             Integer.valueOf(traceId), Boolean.valueOf(swap)
/*      */           }); 
/* 1046 */     this.dc.writeI32(traceId, this.buffer, this.offset + 144, swap);
/*      */     
/* 1048 */     if (Trace.isOn) {
/* 1049 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setTraceIdentifier(int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProductIdentifier(String prodId, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1060 */     if (Trace.isOn) {
/* 1061 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setProductIdentifier(String,JmqiCodepage,JmqiTls)", new Object[] { prodId, cp, tls });
/*      */     }
/*      */     
/* 1064 */     this.dc.writeMQField(prodId, this.buffer, this.offset + 148, 12, cp, tls);
/*      */     
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setProductIdentifier(String,JmqiCodepage,JmqiTls)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQueueManagerId(String qmgrId, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1078 */     if (Trace.isOn) {
/* 1079 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setQueueManagerId(String,JmqiCodepage,JmqiTls)", new Object[] { qmgrId, cp, tls });
/*      */     }
/*      */     
/* 1082 */     this.dc.writeMQField(qmgrId, this.buffer, this.offset + 160, 48, cp, tls);
/*      */     
/* 1084 */     if (Trace.isOn)
/* 1085 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpID", "setQueueManagerId(String,JmqiCodepage,JmqiTls)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */