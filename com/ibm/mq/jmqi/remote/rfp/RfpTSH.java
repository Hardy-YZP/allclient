/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteCommsBuffer;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
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
/*     */ 
/*     */ public class RfpTSH
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpTSH.java";
/*     */   private RemoteCommsBuffer parentBuffer;
/*     */   private ByteBuffer[] userDataBuffers;
/*     */   private int numUserDataBuffers;
/*     */   private byte[] userDataBuffer;
/*     */   private int userDataLength;
/*     */   private int transLength;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpTSH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpTSH.java");
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
/*  72 */   private ByteBuffer exitBuff = null;
/*  73 */   private int exitBuffLimit = 0;
/*  74 */   private int exitBuffPosition = 0;
/*     */   
/*     */   private int tshType;
/*     */   
/*     */   public static final int TSH = 0;
/*     */   
/*     */   public static final int TSHC = 1;
/*     */   
/*     */   public static final int TSHM = 2;
/*     */   
/*     */   public RfpTSH(JmqiEnvironment env, byte[] buffer, int offset) {
/*  85 */     super(env, buffer, offset);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     this.tshType = 0;
/*     */   }
/*     */   public void clearLuwid(int tshType) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "clearLuwid(int)", new Object[] { Integer.valueOf(tshType) });  this.dc.clear(this.buffer, this.offset + ((tshType == 2) ? 20 : 12), 8); if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "clearLuwid(int)");  }
/*     */   public void setTshType(int tshType) { this.tshType = tshType; }
/*     */   public int getTshType() { return this.tshType; }
/*     */   public String getTshTypeString() { return ((((this.tshType >= 0) ? 1 : 0) & ((this.tshType < typeStrings.length) ? 1 : 0)) != 0) ? typeStrings[this.tshType] : String.format("unknown TSH type %d", new Object[] { Integer.valueOf(this.tshType) }); }
/*     */   public RemoteCommsBuffer getParentBuffer() { if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getParentBuffer()", "getter", this.parentBuffer);  return this.parentBuffer; } public void setParentBuffer(RemoteCommsBuffer parentBuffer) { if (Trace.isOn)
/* 296 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setParentBuffer(RemoteCommsBuffer)", "setter", parentBuffer);  this.parentBuffer = parentBuffer; } private static final String[] typeStrings = new String[] { "TSH", "TSHC", "TSHM" };
/*     */   public byte[] getUserDataBuffer() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getUserDataBuffer()", "getter", this.userDataBuffer);  return this.userDataBuffer; }
/* 298 */   public ByteBuffer[] getUserDataBuffers() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getUserDataBuffers()", "getter", this.userDataBuffers);  return this.userDataBuffers; } public int getNumUserDataBuffers() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getNumUserDataBuffers()", "getter", Integer.valueOf(this.numUserDataBuffers));  return this.numUserDataBuffers; } public int getUserDataLength() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getUserDataLength()", "getter", Integer.valueOf(this.userDataLength));  return this.userDataLength; } public void setUserDataMulti(ByteBuffer[] userData, int numBuffs) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setUserDataMulti(ByteBuffer [ ],int)", new Object[] { userData, Integer.valueOf(numBuffs) });  this.userDataBuffers = userData; this.numUserDataBuffers = numBuffs; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setUserDataMulti(ByteBuffer [ ],int)");  } public void setUserDataSingle(byte[] userData, int userDataLength) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setUserDataSingle(byte [ ],int)", new Object[] { userData, Integer.valueOf(userDataLength) });  this.userDataBuffer = userData; this.userDataLength = userDataLength; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setUserDataSingle(byte [ ],int)");  } public int getBytesAvailableAfterTSH() { int traceRet1 = this.buffer.length - this.offset - hdrSize(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getBytesAvailableAfterTSH()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public static final String[] segmentTypeStrings = new String[] { "<INVALID>", "rfpTST_INITIAL_INFO", "rfpTST_RESYNCH", "rfpTST_RESET", "rfpTST_MESSAGE_DATA", "rfpTST_STATUS_INFO", "rfpTST_SECURITY_DATA", "rfpTST_PING_DATA", "rfpTST_USERID_DATA", "rfpTST_HEARTBEATS", "rfpTST_CONAUTH_INFO", "rfpTST_RENEGOTIATE_DATA", "rfpTST_SOCKET_ACTION", "rfpTST_ASYNC_MESSAGE", "rfpTST_REQUEST_MSGS", "rfpTST_NOTIFICATION" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 317 */   private static final byte[] rfpTSH_ID = new byte[] { 84, 83, 72, 32 };
/* 318 */   private static final byte[] rfpTSH_ID_ASCII = new byte[] { 84, 83, 72, 32 };
/* 319 */   private static final byte[] rfpTSH_ID_EBCDIC = new byte[] { -29, -30, -56, 64 };
/*     */   
/* 321 */   private static final byte[] rfpTSH_M_ID = new byte[] { 84, 83, 72, 77 };
/* 322 */   private static final byte[] rfpTSH_M_ID_ASCII = new byte[] { 84, 83, 72, 77 };
/* 323 */   private static final byte[] rfpTSH_M_ID_EBCDIC = new byte[] { -29, -30, -56, -44 };
/*     */ 
/*     */   
/* 326 */   private static final byte[] rfpTSH_C_ID = new byte[] { 84, 83, 72, 67 };
/* 327 */   private static final byte[] rfpTSH_C_ID_ASCII = new byte[] { 84, 83, 72, 67 };
/* 328 */   private static final byte[] rfpTSH_C_ID_EBCDIC = new byte[] { -29, -30, -56, -61 };
/*     */ 
/*     */   
/*     */   private static final int TRANS_LENGTH_OFFSET = 4;
/*     */ 
/*     */   
/*     */   private static final int ENCODING_OFFSET = 8;
/*     */ 
/*     */   
/*     */   private static final int SEGMENT_TYPE_OFFSET = 9;
/*     */ 
/*     */   
/*     */   private static final int CONTROL_FLAGS_1_OFFSET = 10;
/*     */ 
/*     */   
/*     */   private static final int CONTROL_FLAGS_2_OFFSET = 11;
/*     */ 
/*     */   
/*     */   private static final int LUWID_OFFSET = 12;
/*     */ 
/*     */   
/*     */   private static final int MQENCODING_OFFSET = 20;
/*     */ 
/*     */   
/*     */   private static final int CCSID_OFFSET = 24;
/*     */ 
/*     */   
/*     */   private static final int RESERVED_OFFSET = 26;
/*     */ 
/*     */   
/*     */   public static final int TSHC_SIZE = 28;
/*     */ 
/*     */   
/*     */   private static final int MPX_TRANS_LENGTH_OFFSET = 4;
/*     */ 
/*     */   
/*     */   private static final int MPX_CONV_ID_OFFSET = 8;
/*     */ 
/*     */   
/*     */   private static final int MPX_REQUEST_ID_OFFSET = 12;
/*     */ 
/*     */   
/*     */   private static final int MPX_ENCODING_OFFSET = 16;
/*     */   
/*     */   private static final int MPX_SEGMENT_TYPE_OFFSET = 17;
/*     */   
/*     */   private static final int MPX_CONTROL_FLAGS_1_OFFSET = 18;
/*     */   
/*     */   private static final int MPX_CONTROL_FLAGS_2_OFFSET = 19;
/*     */   
/*     */   private static final int MPX_LUWID_OFFSET = 20;
/*     */   
/*     */   private static final int MPX_MQENCODING_OFFSET = 28;
/*     */   
/*     */   private static final int MPX_CCSID_OFFSET = 32;
/*     */   
/*     */   private static final int MPX_RESERVED_OFFSET = 34;
/*     */   
/*     */   public static final int TSHM_SIZE = 36;
/*     */ 
/*     */   
/*     */   public int hdrSize() {
/* 390 */     return (this.tshType == 2) ? 36 : 28;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int tshHdrSize() {
/* 400 */     return (this.tshType == 2) ? 36 : 28;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher(int tshType) {
/*     */     byte[] eyecatcher;
/* 412 */     switch (tshType) {
/*     */       case 1:
/* 414 */         eyecatcher = rfpTSH_C_ID;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 419 */         eyecatcher = rfpTSH_M_ID;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 425 */         eyecatcher = rfpTSH_ID;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 430 */     System.arraycopy(eyecatcher, 0, this.buffer, this.offset, eyecatcher.length);
/*     */     
/* 432 */     this.dc.clear(this.buffer, this.offset + 26, 2);
/* 433 */     this.dc.clear(this.buffer, this.offset + 26, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConvId(int convId) throws JmqiException {
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setConvId(int)", "setter", 
/* 445 */           Integer.valueOf(convId));
/*     */     }
/* 447 */     if (this.tshType != 2) {
/* 448 */       HashMap<String, Object> info = new HashMap<>();
/* 449 */       info.put("tshType", Integer.valueOf(this.tshType));
/* 450 */       info.put("Description", "ConvId not valid for TSH (must be TSHM)");
/* 451 */       Trace.ffst(this, "setConvId(int)", "01", info, null);
/*     */       
/* 453 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/* 454 */       if (Trace.isOn) {
/* 455 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setConvId(int)", (Throwable)traceRet1);
/*     */       }
/* 457 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 461 */     this.dc.writeI32(convId, this.buffer, this.offset + 8, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequestId(int requestId) throws JmqiException {
/* 472 */     if (Trace.isOn) {
/* 473 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setRequestId(int)", "setter", 
/* 474 */           Integer.valueOf(requestId));
/*     */     }
/* 476 */     if (this.tshType != 2) {
/* 477 */       HashMap<String, Object> info = new HashMap<>();
/* 478 */       info.put("tshType", Integer.valueOf(this.tshType));
/* 479 */       info.put("Description", "RequestId not valid for TSH (must be TSHM)");
/* 480 */       Trace.ffst(this, "setRequestId(int)", "01", info, null);
/* 481 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       
/* 483 */       if (Trace.isOn) {
/* 484 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setRequestId(int)", (Throwable)traceRet1);
/*     */       }
/* 486 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 490 */     this.dc.writeI32(requestId, this.buffer, this.offset + 12, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransLength(int transLength) {
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setTransLength(int)", "setter", 
/* 502 */           Integer.valueOf(transLength));
/*     */     }
/*     */     
/* 505 */     this.transLength = transLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hardenTransLength() {
/* 514 */     this.dc.writeI32(this.transLength, this.buffer, this.offset + 4, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int encoding) {
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setEncoding(int)", "setter", 
/* 525 */           Integer.valueOf(encoding));
/*     */     }
/*     */     
/* 528 */     this.buffer[this.offset + ((this.tshType != 2) ? 8 : 16)] = (byte)encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSegmentType(int segmentType) {
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setSegmentType(int)", "setter", 
/* 539 */           Integer.valueOf(segmentType));
/*     */     }
/*     */     
/* 542 */     this.buffer[this.offset + ((this.tshType != 2) ? 9 : 17)] = (byte)segmentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setControlFlags1(int controlFlags1) {
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setControlFlags1(int)", "setter", 
/* 553 */           RemoteConstantDecoder.formatOptions(controlFlags1, "rfpTCF_"));
/*     */     }
/*     */     
/* 556 */     this.buffer[this.offset + ((this.tshType != 2) ? 10 : 18)] = (byte)controlFlags1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setControlFlags2(int controlFlags2) {
/* 565 */     if (Trace.isOn) {
/* 566 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setControlFlags2(int)", "setter", 
/* 567 */           RemoteConstantDecoder.formatOptions(controlFlags2, "rfpTCF2_"));
/*     */     }
/*     */     
/* 570 */     this.buffer[this.offset + ((this.tshType != 2) ? 11 : 19)] = (byte)controlFlags2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQEncoding(int mqEncoding, boolean swap) {
/* 581 */     if (Trace.isOn)
/* 582 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setMQEncoding(int,boolean)", new Object[] {
/* 583 */             Integer.valueOf(mqEncoding), Boolean.valueOf(swap)
/*     */           }); 
/* 585 */     this.dc.writeI32(mqEncoding, this.buffer, this.offset + ((this.tshType != 2) ? 20 : 28), swap);
/*     */     
/* 587 */     if (Trace.isOn) {
/* 588 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setMQEncoding(int,boolean)");
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
/*     */   public void setCcsid(int ccsid, boolean swap) {
/* 600 */     if (Trace.isOn)
/* 601 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setCcsid(int,boolean)", new Object[] {
/* 602 */             Integer.valueOf(ccsid), Boolean.valueOf(swap)
/*     */           }); 
/* 604 */     this.dc.writeU16(ccsid, this.buffer, this.offset + ((this.tshType != 2) ? 24 : 32), swap);
/*     */     
/* 606 */     if (Trace.isOn) {
/* 607 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setCcsid(int,boolean)");
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
/*     */   public int getConvId(boolean swap) throws JmqiException {
/* 620 */     if (Trace.isOn)
/* 621 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getConvId(boolean)", new Object[] {
/* 622 */             Boolean.valueOf(swap)
/*     */           }); 
/* 624 */     if (this.tshType != 2) {
/* 625 */       HashMap<String, Object> info = new HashMap<>();
/* 626 */       info.put("tshType", Integer.valueOf(this.tshType));
/* 627 */       info.put("Description", "ConvId not valid for TSH (must be TSHM)");
/* 628 */       Trace.ffst(this, "getConvId(boolean)", "01", info, null);
/* 629 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       
/* 631 */       if (Trace.isOn) {
/* 632 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getConvId(boolean)", (Throwable)traceRet1);
/*     */       }
/* 634 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 638 */     int traceRet2 = this.dc.readI32(this.buffer, this.offset + 8, false);
/*     */     
/* 640 */     if (Trace.isOn) {
/* 641 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getConvId(boolean)", 
/* 642 */           Integer.valueOf(traceRet2));
/*     */     }
/* 644 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequestId(boolean swap) throws JmqiException {
/* 655 */     if (Trace.isOn)
/* 656 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getRequestId(boolean)", new Object[] {
/* 657 */             Boolean.valueOf(swap)
/*     */           }); 
/* 659 */     if (this.tshType != 2) {
/* 660 */       HashMap<String, Object> info = new HashMap<>();
/* 661 */       info.put("tshType", Integer.valueOf(this.tshType));
/* 662 */       info.put("Description", "RequestId not valid for TSH (must be TSHM)");
/* 663 */       Trace.ffst(this, "getRequestId(boolean)", "01", info, null);
/* 664 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       
/* 666 */       if (Trace.isOn) {
/* 667 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getRequestId(boolean)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 670 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 674 */     int traceRet2 = this.dc.readI32(this.buffer, this.offset + 12, false);
/*     */     
/* 676 */     if (Trace.isOn) {
/* 677 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getRequestId(boolean)", 
/* 678 */           Integer.valueOf(traceRet2));
/*     */     }
/* 680 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTransLength() {
/* 691 */     if (Trace.isOn) {
/* 692 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getTransLength()", "getter", 
/* 693 */           Integer.valueOf(this.transLength));
/*     */     }
/* 695 */     return this.transLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void extractTransLength() {
/* 705 */     this.transLength = this.dc.readI32(this.buffer, this.offset + 4, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 714 */     int traceRet1 = this.buffer[this.offset + ((this.tshType != 2) ? 8 : 16)] & 0xFF;
/*     */     
/* 716 */     if (Trace.isOn) {
/* 717 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getEncoding()", "getter", 
/* 718 */           Integer.valueOf(traceRet1));
/*     */     }
/* 720 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSegmentType() {
/* 729 */     int traceRet1 = this.buffer[this.offset + ((this.tshType != 2) ? 9 : 17)] & 0xFF;
/*     */     
/* 731 */     if (Trace.isOn) {
/* 732 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getSegmentType()", "getter", 
/* 733 */           Integer.valueOf(traceRet1));
/*     */     }
/* 735 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getControlFlags1() {
/* 744 */     int traceRet1 = this.buffer[this.offset + ((this.tshType != 2) ? 10 : 18)] & 0xFF;
/*     */     
/* 746 */     if (Trace.isOn) {
/* 747 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getControlFlags1()", "getter", 
/* 748 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpTCF_"));
/*     */     }
/* 750 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getControlFlags2() {
/* 759 */     int traceRet1 = this.buffer[this.offset + ((this.tshType != 2) ? 11 : 19)] & 0xFF;
/*     */     
/* 761 */     if (Trace.isOn) {
/* 762 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getControlFlags2()", "getter", 
/* 763 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpTCF2_"));
/*     */     }
/* 765 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSegmentTypeString() {
/* 774 */     int segmentType = getSegmentType();
/* 775 */     return (segmentType >= 0 && segmentType < segmentTypeStrings.length) ? segmentTypeStrings[segmentType] : String.format("Invalid segment type %d", new Object[] { Integer.valueOf(segmentType) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMQEncoding(boolean swap) {
/* 786 */     if (Trace.isOn)
/* 787 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getMQEncoding(boolean)", new Object[] {
/* 788 */             Boolean.valueOf(swap)
/*     */           }); 
/* 790 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + ((this.tshType != 2) ? 20 : 28), swap);
/*     */     
/* 792 */     if (Trace.isOn) {
/* 793 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getMQEncoding(boolean)", 
/* 794 */           Integer.valueOf(traceRet1));
/*     */     }
/* 796 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCcsid(boolean swap) {
/* 806 */     if (Trace.isOn)
/* 807 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getCcsid(boolean)", new Object[] {
/* 808 */             Boolean.valueOf(swap)
/*     */           }); 
/* 810 */     int traceRet1 = this.dc.readU16(this.buffer, this.offset + ((this.tshType != 2) ? 24 : 32), swap);
/*     */     
/* 812 */     if (Trace.isOn) {
/* 813 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getCcsid(boolean)", 
/* 814 */           Integer.valueOf(traceRet1));
/*     */     }
/* 816 */     return traceRet1;
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
/*     */   public int checkEyecatcher() throws JmqiException {
/* 828 */     if (Trace.isOn) {
/* 829 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "checkEyecatcher()");
/*     */     }
/* 831 */     extractTransLength();
/*     */     
/* 833 */     if ((this.buffer[this.offset] == rfpTSH_ID_ASCII[0] && this.buffer[this.offset + 1] == rfpTSH_ID_ASCII[1] && this.buffer[this.offset + 2] == rfpTSH_ID_ASCII[2] && this.buffer[this.offset + 3] == rfpTSH_ID_ASCII[3]) || (this.buffer[this.offset] == rfpTSH_ID_EBCDIC[0] && this.buffer[this.offset + 1] == rfpTSH_ID_EBCDIC[1] && this.buffer[this.offset + 2] == rfpTSH_ID_EBCDIC[2] && this.buffer[this.offset + 3] == rfpTSH_ID_EBCDIC[3])) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 839 */       this.tshType = 0;
/*     */     }
/* 841 */     else if ((this.buffer[this.offset] == rfpTSH_M_ID_ASCII[0] && this.buffer[this.offset + 1] == rfpTSH_M_ID_ASCII[1] && this.buffer[this.offset + 2] == rfpTSH_M_ID_ASCII[2] && this.buffer[this.offset + 3] == rfpTSH_M_ID_ASCII[3]) || (this.buffer[this.offset] == rfpTSH_M_ID_EBCDIC[0] && this.buffer[this.offset + 1] == rfpTSH_M_ID_EBCDIC[1] && this.buffer[this.offset + 2] == rfpTSH_M_ID_EBCDIC[2] && this.buffer[this.offset + 3] == rfpTSH_M_ID_EBCDIC[3])) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 847 */       this.tshType = 2;
/*     */     }
/* 849 */     else if ((this.buffer[this.offset] == rfpTSH_C_ID_ASCII[0] && this.buffer[this.offset + 1] == rfpTSH_C_ID_ASCII[1] && this.buffer[this.offset + 2] == rfpTSH_C_ID_ASCII[2] && this.buffer[this.offset + 3] == rfpTSH_C_ID_ASCII[3]) || (this.buffer[this.offset] == rfpTSH_C_ID_EBCDIC[0] && this.buffer[this.offset + 1] == rfpTSH_C_ID_EBCDIC[1] && this.buffer[this.offset + 2] == rfpTSH_C_ID_EBCDIC[2] && this.buffer[this.offset + 3] == rfpTSH_C_ID_EBCDIC[3])) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 855 */       this.tshType = 1;
/*     */     }
/*     */     else {
/*     */       
/* 859 */       byte[] trBuff = new byte[4];
/* 860 */       System.arraycopy(this.buffer, this.offset, trBuff, 0, 4);
/* 861 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 862 */       ffstInfo.put("eyecatcheR", trBuff);
/* 863 */       ffstInfo.put("Description", "Invalid data received");
/* 864 */       Trace.ffst(this, "checkEyecatcher()", "01", ffstInfo, null);
/*     */       
/* 866 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, null);
/*     */       
/* 868 */       if (Trace.isOn) {
/* 869 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "checkEyecatcher()", (Throwable)e);
/*     */       }
/* 871 */       throw e;
/*     */     } 
/*     */     
/* 874 */     if (Trace.isOn) {
/* 875 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "checkEyecatcher()", 
/* 876 */           Integer.valueOf(this.tshType));
/*     */     }
/* 878 */     return this.tshType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer getExitBuff() {
/* 888 */     if (Trace.isOn) {
/* 889 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getExitBuff()", "getter", this.exitBuff);
/*     */     }
/* 891 */     return this.exitBuff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExitBuff(ByteBuffer exitBuff) {
/* 900 */     if (Trace.isOn) {
/* 901 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setExitBuff(ByteBuffer)", "setter", exitBuff);
/*     */     }
/*     */ 
/*     */     
/* 905 */     this.exitBuff = exitBuff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExitBuffLimit() {
/* 915 */     if (Trace.isOn) {
/* 916 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getExitBuffLimit()", "getter", 
/* 917 */           Integer.valueOf(this.exitBuffLimit));
/*     */     }
/* 919 */     return this.exitBuffLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExitBuffLimit(int exitBuffLimit) {
/* 928 */     if (Trace.isOn) {
/* 929 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setExitBuffLimit(int)", "setter", 
/* 930 */           Integer.valueOf(exitBuffLimit));
/*     */     }
/*     */     
/* 933 */     this.exitBuffLimit = exitBuffLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExitBuffPosition() {
/* 943 */     if (Trace.isOn) {
/* 944 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "getExitBuffPosition()", "getter", 
/* 945 */           Integer.valueOf(this.exitBuffPosition));
/*     */     }
/* 947 */     return this.exitBuffPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExitBuffPosition(int exitBuffPosition) {
/* 956 */     if (Trace.isOn) {
/* 957 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpTSH", "setExitBuffPosition(int)", "setter", 
/* 958 */           Integer.valueOf(exitBuffPosition));
/*     */     }
/*     */     
/* 961 */     this.exitBuffPosition = exitBuffPosition;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpTSH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */