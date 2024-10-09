/*      */ package com.ibm.mq.jmqi.remote.util;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.HashMap;
/*      */ import java.util.zip.DataFormatException;
/*      */ import java.util.zip.Deflater;
/*      */ import java.util.zip.Inflater;
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
/*      */ public class RemoteCompressor
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteCompressor.java";
/*      */   private static final byte ESCAPECHAR = -85;
/*      */   private byte[] previousMdGmo;
/*      */   private byte[] previousMdPmo;
/*      */   private byte[] previousPutMQMD;
/*      */   private byte[] previousGetMQMD;
/*      */   private byte[] previousAsyncMQMD;
/*      */   
/*      */   static {
/*   71 */     if (Trace.isOn) {
/*   72 */       Trace.data("com.ibm.mq.jmqi.remote.util.RemoteCompressor", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteCompressor.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   private int compLevel = 0;
/*   96 */   private Deflater compressor = null;
/*   97 */   private Inflater decompressor = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   private byte[] result = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteCompressor(JmqiEnvironment env, int cLevel) throws JmqiException {
/*  115 */     super(env);
/*  116 */     if (Trace.isOn) {
/*  117 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/*  118 */             Integer.valueOf(cLevel) });
/*      */     }
/*  120 */     this.previousMdGmo = new byte[MQGMO.getSize(env, 4, 4)];
/*  121 */     this.previousMdPmo = new byte[MQPMO.getSize(env, 3, 4)];
/*  122 */     this.previousPutMQMD = new byte[MQMD.getSize(env, 2, 4)];
/*  123 */     this.previousGetMQMD = new byte[MQMD.getSize(env, 2, 4)];
/*  124 */     this.previousAsyncMQMD = new byte[MQMD.getSize(env, 2, 4)];
/*  125 */     this.compLevel = cLevel;
/*  126 */     if (this.compLevel != 0) {
/*  127 */       this.compressor = new Deflater(this.compLevel);
/*  128 */       this.decompressor = new Inflater();
/*      */     } 
/*      */     
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "<init>(JmqiEnvironment,int)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] compressMsgSegment(RfpTSH tsh, int curHdrCompP, int curMsgCompP, int maxTxSize, boolean swap) throws JmqiException {
/*      */     int requiredBufferSize;
/*  153 */     if (Trace.isOn) {
/*  154 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "compressMsgSegment(RfpTSH,int,int,int,boolean)", new Object[] { tsh, 
/*      */             
/*  156 */             Integer.valueOf(curHdrCompP), Integer.valueOf(curMsgCompP), Integer.valueOf(maxTxSize), 
/*  157 */             Boolean.valueOf(swap) });
/*      */     }
/*  159 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  160 */     int curHdrComp = (curHdrCompP == -1) ? 0 : curHdrCompP;
/*  161 */     int curMsgComp = (curMsgCompP == -1) ? 0 : curMsgCompP;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  171 */     if (maxTxSize <= 12) {
/*  172 */       requiredBufferSize = maxTxSize + 13;
/*      */     } else {
/*      */       
/*  175 */       requiredBufferSize = maxTxSize * 2;
/*      */     } 
/*      */     
/*  178 */     if (this.result == null || this.result.length < requiredBufferSize) {
/*  179 */       this.result = new byte[requiredBufferSize];
/*      */     }
/*  181 */     byte[] segment = tsh.getRfpBuffer();
/*      */     
/*  183 */     int tcf2Offset = (tsh.getTshType() == 2) ? 19 : 11;
/*  184 */     int bytesToSend = tsh.getTransLength();
/*      */ 
/*      */     
/*  187 */     int headerLen = tsh.tshHdrSize() + tsh.getRfpOffset();
/*  188 */     int mqapiSize = headerLen + 16;
/*  189 */     System.arraycopy(segment, 0, this.result, 0, headerLen);
/*  190 */     int compressedBytes = headerLen;
/*  191 */     int mqmdSize = 0;
/*  192 */     int moSize = 0;
/*  193 */     int mqodSize = 0;
/*      */ 
/*      */     
/*  196 */     int segType = tsh.getSegmentType() & 0xFF;
/*  197 */     int ctrlFlags = tsh.getControlFlags1() & 0xFF;
/*  198 */     if ((ctrlFlags & 0x10) != 0) {
/*  199 */       int mqodVersion, mqmdVersion, version, pmoVersion, gmoVersion; if (Trace.isOn) {
/*  200 */         Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "In the first message segment", "");
/*      */       }
/*      */       
/*  203 */       headerLen += 16;
/*  204 */       compressedBytes = headerLen;
/*  205 */       System.arraycopy(segment, tsh.tshHdrSize(), this.result, tsh.tshHdrSize(), 16);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  210 */       if (curHdrComp != 0 || curMsgComp != 0) {
/*  211 */         this.result[headerLen] = 0;
/*  212 */         this.result[headerLen + 1] = 0;
/*  213 */         this.result[headerLen + 2] = 0;
/*  214 */         this.result[headerLen + 3] = (byte)curMsgComp;
/*  215 */         compressedBytes += 4;
/*      */         
/*  217 */         this.result[tcf2Offset] = (byte)(this.result[tcf2Offset] | 0x4);
/*      */       } 
/*  219 */       int currentPos = headerLen;
/*      */       
/*  221 */       switch (segType) {
/*      */         
/*      */         case 129:
/*      */         case 131:
/*  225 */           headerLen = bytesToSend;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 135:
/*  230 */           currentPos += 4;
/*  231 */           mqodVersion = dc.readI32(segment, currentPos, swap);
/*  232 */           mqodSize = MQOD.getSize(this.env, mqodVersion, 4);
/*  233 */           headerLen += mqodSize;
/*  234 */           currentPos += mqodSize - 4;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 134:
/*  240 */           currentPos += 4;
/*  241 */           mqmdVersion = dc.readI32(segment, currentPos, swap);
/*  242 */           mqmdSize = MQMD.getSize(this.env, mqmdVersion, 4);
/*  243 */           headerLen += mqmdSize;
/*  244 */           currentPos += mqmdSize;
/*  245 */           pmoVersion = dc.readI32(segment, currentPos, swap);
/*  246 */           moSize = MQPMO.getSize(this.env, pmoVersion, 4);
/*  247 */           headerLen += moSize;
/*  248 */           currentPos += moSize;
/*      */           break;
/*      */         
/*      */         case 133:
/*  252 */           currentPos += 4;
/*  253 */           version = dc.readI32(segment, currentPos, swap);
/*  254 */           mqmdSize = MQMD.getSize(this.env, version, 4);
/*  255 */           headerLen += mqmdSize;
/*  256 */           currentPos += mqmdSize;
/*  257 */           gmoVersion = dc.readI32(segment, currentPos, swap);
/*  258 */           moSize = MQGMO.getSize(this.env, gmoVersion, 4);
/*  259 */           headerLen += moSize;
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  264 */       if ((segType == 133 || segType == 134 || segType == 135) && bytesToSend - headerLen >= 4) {
/*  265 */         headerLen += 4;
/*      */       }
/*  267 */       if (Trace.isOn) {
/*  268 */         Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Header length is: ", Integer.toString(headerLen));
/*      */       }
/*      */       
/*  271 */       byte[] tempMQMD = new byte[mqmdSize];
/*  272 */       byte[] tempMO = new byte[moSize];
/*  273 */       if (curHdrComp == 8) {
/*      */         
/*  275 */         if (segType == 133 || segType == 134 || segType == 135) {
/*  276 */           System.arraycopy(segment, mqapiSize + mqodSize, tempMQMD, 0, mqmdSize);
/*  277 */           System.arraycopy(segment, mqapiSize + mqodSize + mqmdSize, tempMO, 0, moSize);
/*      */ 
/*      */ 
/*      */           
/*  281 */           if (bytesToSend >= headerLen) {
/*  282 */             if (segType == 133) {
/*  283 */               xorMsgSegment(this.previousGetMQMD, segment, mqapiSize + mqodSize + 8, mqmdSize - 8);
/*  284 */               xorMsgSegment(this.previousMdGmo, segment, mqapiSize + mqodSize + mqmdSize + 8, moSize - 8);
/*      */             } else {
/*      */               
/*  287 */               xorMsgSegment(this.previousPutMQMD, segment, mqapiSize + mqodSize + 8, mqmdSize - 8);
/*  288 */               xorMsgSegment(this.previousMdPmo, segment, mqapiSize + mqodSize + mqmdSize + 8, moSize - 8);
/*      */             } 
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  296 */         if (headerLen > bytesToSend) {
/*  297 */           headerLen = bytesToSend;
/*      */         }
/*  299 */         compressedBytes += compressRLE(segment, compressedBytes - 4, headerLen - compressedBytes + 4, this.result, compressedBytes);
/*  300 */         if (compressedBytes >= headerLen) {
/*      */ 
/*      */           
/*  303 */           if (Trace.isOn) {
/*  304 */             Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Compression increases header length - undoing", "");
/*      */           }
/*      */           
/*  307 */           System.arraycopy(tempMQMD, 0, segment, mqapiSize + mqodSize, mqmdSize);
/*  308 */           System.arraycopy(tempMO, 0, segment, mqapiSize + mqodSize + mqmdSize, moSize);
/*  309 */           System.arraycopy(this.result, mqapiSize + 4 + mqodSize, segment, mqapiSize + mqodSize, headerLen - mqapiSize + mqodSize);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  315 */           if (Trace.isOn) {
/*  316 */             Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Header compression completed", "");
/*      */           }
/*  318 */           this.result[tcf2Offset] = (byte)(this.result[tcf2Offset] | 0x1);
/*      */           
/*  320 */           if (segType == 133) {
/*  321 */             System.arraycopy(tempMQMD, 0, this.previousGetMQMD, 0, mqmdSize);
/*  322 */             if (mqmdSize < this.previousGetMQMD.length) {
/*  323 */               for (int i = mqmdSize; i < this.previousGetMQMD.length; i++) {
/*  324 */                 this.previousGetMQMD[i] = 0;
/*      */               }
/*      */             }
/*  327 */             System.arraycopy(tempMO, 0, this.previousMdGmo, 0, moSize);
/*  328 */             if (moSize < this.previousMdGmo.length) {
/*  329 */               for (int i = moSize; i < this.previousMdGmo.length; i++) {
/*  330 */                 this.previousMdGmo[i] = 0;
/*      */               }
/*      */             }
/*      */           }
/*  334 */           else if (segType == 134 || segType == 135) {
/*  335 */             System.arraycopy(tempMQMD, 0, this.previousPutMQMD, 0, mqmdSize);
/*  336 */             if (mqmdSize < this.previousPutMQMD.length) {
/*  337 */               for (int i = mqmdSize; i < this.previousPutMQMD.length; i++) {
/*  338 */                 this.previousPutMQMD[i] = 0;
/*      */               }
/*      */             }
/*  341 */             System.arraycopy(tempMO, 0, this.previousMdPmo, 0, moSize);
/*  342 */             if (moSize < this.previousMdPmo.length) {
/*  343 */               for (int i = moSize; i < this.previousMdPmo.length; i++) {
/*  344 */                 this.previousMdPmo[i] = 0;
/*      */               }
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/*  352 */         System.arraycopy(segment, compressedBytes - 4, this.result, compressedBytes, headerLen - compressedBytes + 4);
/*  353 */         compressedBytes = headerLen + 4;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  358 */       compressedBytes = headerLen;
/*      */     } 
/*      */ 
/*      */     
/*  362 */     int len = compressedBytes;
/*      */     
/*  364 */     int compressedLen = (tsh.getTshType() == 2) ? (len - 8) : len;
/*      */     
/*  366 */     this.result[mqapiSize] = (byte)(compressedLen >>> 8);
/*  367 */     this.result[mqapiSize + 1] = (byte)compressedLen;
/*      */     
/*  369 */     if (segType == 134 || segType == 135 || segType == 133 || segType == 131) {
/*  370 */       if (curMsgComp != 0) {
/*  371 */         if (bytesToSend - headerLen > 0) {
/*  372 */           int compMsgLen = 0;
/*  373 */           switch (curMsgComp) {
/*      */             case 1:
/*  375 */               compMsgLen = compressRLE(segment, headerLen, bytesToSend - headerLen, this.result, compressedBytes);
/*      */               break;
/*      */             
/*      */             case 2:
/*      */             case 4:
/*  380 */               compMsgLen = compressZLIB(segment, headerLen, bytesToSend - headerLen, this.result, compressedBytes);
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  389 */           int extraBytes = 0;
/*  390 */           if (curHdrComp == 0) {
/*  391 */             extraBytes = compMsgLen + 4;
/*      */           } else {
/*      */             
/*  394 */             extraBytes = compMsgLen;
/*      */           } 
/*  396 */           if (extraBytes < bytesToSend - headerLen)
/*      */           {
/*  398 */             if (Trace.isOn) {
/*  399 */               Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Message compression completed", "");
/*      */             }
/*  401 */             this.result[tcf2Offset] = (byte)(this.result[tcf2Offset] | 0x2);
/*  402 */             compressedBytes += compMsgLen;
/*      */           }
/*      */           else
/*      */           {
/*  406 */             if (Trace.isOn) {
/*  407 */               Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Message compression increases length - undoing", "");
/*      */             }
/*  409 */             System.arraycopy(segment, headerLen, this.result, compressedBytes, bytesToSend - headerLen);
/*  410 */             compressedBytes += bytesToSend - headerLen;
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/*  416 */         if (Trace.isOn) {
/*  417 */           Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "No Message Compression", "");
/*  418 */           Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "bytesToSend = ", Integer.toString(bytesToSend));
/*  419 */           Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "headerLen = ", Integer.toString(headerLen));
/*      */         } 
/*  421 */         if (bytesToSend > headerLen) {
/*  422 */           System.arraycopy(segment, headerLen, this.result, compressedBytes, bytesToSend - headerLen);
/*  423 */           compressedBytes += bytesToSend - headerLen;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  430 */     boolean hdrComp = ((this.result[tcf2Offset] & 0x1) == 0);
/*  431 */     boolean msgComp = ((this.result[tcf2Offset] & 0x2) == 0);
/*  432 */     if ((ctrlFlags & 0x10) != 0 && (curHdrComp != 0 || curMsgComp != 0) && hdrComp && msgComp) {
/*      */       
/*  434 */       for (int i = mqapiSize; i < this.result.length - 4; i++) {
/*  435 */         this.result[i] = this.result[i + 4];
/*      */       }
/*  437 */       compressedBytes -= 4;
/*      */       
/*  439 */       this.result[tcf2Offset] = (byte)(this.result[tcf2Offset] & 0xFFFFFFFB);
/*      */     } 
/*      */     
/*  442 */     len = compressedBytes;
/*  443 */     if (Trace.isOn) {
/*  444 */       Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Segment length after compression: ", Integer.toString(compressedBytes));
/*      */     }
/*  446 */     this.result[4] = (byte)(len >>> 24);
/*  447 */     this.result[5] = (byte)(len >>> 16);
/*  448 */     this.result[6] = (byte)(len >>> 8);
/*  449 */     this.result[7] = (byte)len;
/*  450 */     tsh.setRfpBuffer(this.result);
/*  451 */     tsh.setTransLength(len);
/*  452 */     if (Trace.isOn) {
/*  453 */       Trace.data(this, "compressMsgSegment(RfpTSH,int,int,int,boolean)", "Header length after compression: ", Integer.toString(compressedBytes));
/*      */     }
/*      */     
/*  456 */     if (Trace.isOn) {
/*  457 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "compressMsgSegment(RfpTSH,int,int,int,boolean)", this.result);
/*      */     }
/*      */     
/*  460 */     return this.result;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] decompressMsgSegment(RfpTSH tsh, int curMsgCompP, int maxTxSize, boolean swap) throws JmqiException {
/*  477 */     if (Trace.isOn) {
/*  478 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressMsgSegment(RfpTSH,int,int,boolean)", new Object[] { tsh, 
/*      */             
/*  480 */             Integer.valueOf(curMsgCompP), Integer.valueOf(maxTxSize), Boolean.valueOf(swap) });
/*      */     }
/*  482 */     int curMsgComp = curMsgCompP;
/*  483 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  484 */     byte[] segment = tsh.getRfpBuffer();
/*  485 */     int offset = tsh.getRfpOffset();
/*  486 */     int mqapiSize = tsh.tshHdrSize() + 16;
/*  487 */     int bytesInBuffer = tsh.getTransLength();
/*  488 */     int msgLen = 0;
/*  489 */     int compHdrLen = 0;
/*      */ 
/*      */     
/*  492 */     int segType = tsh.getSegmentType();
/*  493 */     if (segType < 0) {
/*  494 */       segType += 256;
/*      */     }
/*  496 */     int ctrlFlags = tsh.getControlFlags1();
/*  497 */     if (ctrlFlags < 0) {
/*  498 */       ctrlFlags += 256;
/*      */     }
/*  500 */     int ctrlFlags2 = tsh.getControlFlags2();
/*  501 */     if (ctrlFlags2 < 0) {
/*  502 */       ctrlFlags2 += 256;
/*      */     }
/*  504 */     int tshHdrSize = tsh.tshHdrSize();
/*  505 */     int mqmdSize = 0;
/*  506 */     int moSize = 0;
/*  507 */     int mqodSize = 0;
/*  508 */     int asyncMQMDOffset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  514 */     int requiredBufferSize = maxTxSize + 8;
/*      */ 
/*      */     
/*  517 */     byte[] decompressedResult = new byte[requiredBufferSize];
/*      */ 
/*      */     
/*  520 */     int decompressedBytes = tshHdrSize;
/*  521 */     System.arraycopy(segment, offset, decompressedResult, 0, decompressedBytes);
/*  522 */     if ((ctrlFlags & 0x10) == 0) {
/*  523 */       if (segType == 13) {
/*  524 */         decompressedBytes += 24;
/*  525 */         compHdrLen = tshHdrSize + 24;
/*      */         
/*  527 */         System.arraycopy(segment, offset + tshHdrSize, decompressedResult, tshHdrSize, decompressedBytes - tshHdrSize);
/*      */       } else {
/*      */         
/*  530 */         compHdrLen = tshHdrSize;
/*      */       } 
/*  532 */       if (Trace.isOn) {
/*  533 */         Trace.data(this, "decompressMsgSegment(RfpTSH,int,int,boolean)", "Compressed header length: ", Integer.toString(compHdrLen));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  545 */     if ((ctrlFlags & 0x10) != 0) {
/*  546 */       if (segType == 13) {
/*  547 */         decompressedBytes += 24;
/*      */       }
/*      */       else {
/*      */         
/*  551 */         decompressedBytes += 16;
/*      */       } 
/*  553 */       System.arraycopy(segment, offset + tshHdrSize, decompressedResult, tshHdrSize, decompressedBytes - tshHdrSize);
/*      */       
/*  555 */       compHdrLen |= (segment[offset + decompressedBytes] & 0xFF) << 8;
/*  556 */       compHdrLen |= segment[offset + decompressedBytes + 1] & 0xFF;
/*      */ 
/*      */       
/*  559 */       if (tsh.getTshType() == 2) {
/*  560 */         compHdrLen += 8;
/*      */       }
/*  562 */       if (Trace.isOn) {
/*  563 */         Trace.data(this, "decompressMsgSegment(RfpTSH,int,int,boolean)", "Compressed header length: ", Integer.toString(compHdrLen));
/*      */       }
/*      */       
/*  566 */       curMsgComp = segment[offset + decompressedBytes + 3];
/*      */       
/*  568 */       if ((ctrlFlags2 & 0x1) != 0) {
/*  569 */         int mqodVersion, mqmdVersion, version, pmoVersion, gmoVersion; if (Trace.isOn) {
/*  570 */           Trace.data(this, "decompressMsgSegment(RfpTSH,int,int,boolean)", "Header compression flag set", "");
/*      */         }
/*      */         
/*  573 */         int currentPos = decompressedBytes + 4;
/*      */         try {
/*  575 */           decompressedBytes += decompressRLE(segment, offset + currentPos, compHdrLen - currentPos, decompressedResult, decompressedBytes);
/*      */         }
/*  577 */         catch (JmqiException e) {
/*  578 */           if (Trace.isOn) {
/*  579 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressMsgSegment(RfpTSH,int,int,boolean)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */           
/*  583 */           if (Trace.isOn) {
/*  584 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressMsgSegment(RfpTSH,int,int,boolean)", (Throwable)e, 1);
/*      */           }
/*      */           
/*  587 */           throw e;
/*      */         } 
/*      */         
/*  590 */         currentPos = mqapiSize;
/*      */ 
/*      */ 
/*      */         
/*  594 */         switch (segType) {
/*      */ 
/*      */           
/*      */           case 151:
/*  598 */             mqodVersion = dc.readI32(decompressedResult, currentPos + 4, swap);
/*      */             
/*  600 */             mqodSize = MQOD.getSize(this.env, mqodVersion, 4);
/*  601 */             currentPos += mqodSize;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 150:
/*  607 */             mqmdVersion = dc.readI32(decompressedResult, currentPos + 4, swap);
/*      */             
/*  609 */             mqmdSize = MQMD.getSize(this.env, mqmdVersion, 4);
/*  610 */             currentPos += mqmdSize;
/*      */             
/*  612 */             pmoVersion = dc.readI32(decompressedResult, currentPos + 4, swap);
/*      */             
/*  614 */             moSize = MQPMO.getSize(this.env, pmoVersion, 4);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 149:
/*  620 */             version = dc.readI32(decompressedResult, currentPos + 4, swap);
/*      */             
/*  622 */             mqmdSize = MQMD.getSize(this.env, version, 4);
/*  623 */             currentPos += mqmdSize;
/*      */             
/*  625 */             gmoVersion = dc.readI32(decompressedResult, currentPos + 4, swap);
/*      */             
/*  627 */             moSize = MQGMO.getSize(this.env, gmoVersion, 4);
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  634 */         if (segType == 149 || segType == 150 || segType == 151 || segType == 13)
/*      */         {
/*      */ 
/*      */           
/*  638 */           if (decompressedBytes != maxTxSize)
/*      */           {
/*      */             
/*  641 */             if (segType == 149) {
/*  642 */               xorMsgSegment(this.previousGetMQMD, decompressedResult, mqapiSize + mqodSize + 8, mqmdSize - 8);
/*  643 */               xorMsgSegment(this.previousMdGmo, decompressedResult, mqapiSize + mqodSize + mqmdSize + 8, moSize - 8);
/*      */             
/*      */             }
/*  646 */             else if (segType == 13) {
/*  647 */               asyncMQMDOffset = tshHdrSize + 54 + (decompressedResult[tshHdrSize + 54] + 2 & 0xFFFFFFFC) + 2;
/*  648 */               version = dc.readI32(decompressedResult, asyncMQMDOffset + 4, swap);
/*      */               
/*  650 */               mqmdSize = MQMD.getSize(this.env, version, 4);
/*  651 */               xorMsgSegment(this.previousAsyncMQMD, decompressedResult, asyncMQMDOffset + 8, mqmdSize - 8);
/*      */             }
/*      */             else {
/*      */               
/*  655 */               xorMsgSegment(this.previousPutMQMD, decompressedResult, mqapiSize + mqodSize + 8, mqmdSize - 8);
/*  656 */               xorMsgSegment(this.previousMdPmo, decompressedResult, mqapiSize + mqodSize + mqmdSize + 8, moSize - 8);
/*      */             } 
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  666 */         if (tsh.getTshType() == 2) {
/*  667 */           decompressedResult[19] = (byte)(decompressedResult[19] & 0xFFFFFFFE);
/*      */         } else {
/*      */           
/*  670 */           decompressedResult[11] = (byte)(decompressedResult[11] & 0xFFFFFFFE);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  678 */         int cshHeaderSize = 0;
/*  679 */         if ((tsh.getControlFlags2() & 0x2) == 2) {
/*  680 */           cshHeaderSize = 4;
/*      */         }
/*      */         
/*  683 */         System.arraycopy(segment, offset + decompressedBytes + cshHeaderSize, decompressedResult, decompressedBytes, compHdrLen - decompressedBytes);
/*      */ 
/*      */ 
/*      */         
/*  687 */         decompressedBytes = compHdrLen - cshHeaderSize;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  692 */       if (segType == 149) {
/*  693 */         System.arraycopy(decompressedResult, mqapiSize + mqodSize, this.previousGetMQMD, 0, mqmdSize);
/*      */         
/*  695 */         if (mqmdSize < this.previousGetMQMD.length) {
/*  696 */           for (int i = mqmdSize; i < this.previousGetMQMD.length; i++) {
/*  697 */             this.previousGetMQMD[i] = 0;
/*      */           }
/*      */         }
/*      */         
/*  701 */         System.arraycopy(decompressedResult, mqapiSize + mqodSize + mqmdSize, this.previousMdGmo, 0, moSize);
/*      */         
/*  703 */         if (moSize < this.previousMdGmo.length) {
/*  704 */           for (int i = moSize; i < this.previousMdGmo.length; i++) {
/*  705 */             this.previousMdGmo[i] = 0;
/*      */           }
/*      */         }
/*      */       }
/*  709 */       else if (segType == 150 || segType == 151) {
/*  710 */         System.arraycopy(decompressedResult, mqapiSize + mqodSize, this.previousPutMQMD, 0, mqmdSize);
/*      */         
/*  712 */         if (mqmdSize < this.previousPutMQMD.length) {
/*  713 */           for (int i = mqmdSize; i < this.previousPutMQMD.length; i++) {
/*  714 */             this.previousPutMQMD[i] = 0;
/*      */           }
/*      */         }
/*      */         
/*  718 */         System.arraycopy(decompressedResult, mqapiSize + mqodSize + mqmdSize, this.previousMdPmo, 0, moSize);
/*      */         
/*  720 */         if (moSize < this.previousMdPmo.length) {
/*  721 */           for (int i = moSize; i < this.previousMdPmo.length; i++) {
/*  722 */             this.previousMdPmo[i] = 0;
/*      */           }
/*      */         }
/*      */       }
/*  726 */       else if (segType == 13) {
/*  727 */         System.arraycopy(decompressedResult, asyncMQMDOffset, this.previousAsyncMQMD, 0, mqmdSize);
/*      */         
/*  729 */         if (mqmdSize < this.previousAsyncMQMD.length) {
/*  730 */           for (int i = mqmdSize; i < this.previousAsyncMQMD.length; i++) {
/*  731 */             this.previousAsyncMQMD[i] = 0;
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  739 */     if ((ctrlFlags & 0x10) == 0 || segType == 149 || segType == 13) {
/*  740 */       if ((ctrlFlags2 & 0x2) != 0) {
/*      */         
/*      */         try {
/*  743 */           switch (curMsgComp) {
/*      */             case 1:
/*  745 */               decompressedBytes += decompressRLE(segment, offset + compHdrLen, bytesInBuffer - compHdrLen, decompressedResult, decompressedBytes);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 2:
/*      */             case 4:
/*  751 */               decompressedBytes += decompressZLIB(segment, offset + compHdrLen, bytesInBuffer - compHdrLen, decompressedResult, decompressedBytes);
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             default:
/*  757 */               System.arraycopy(segment, offset + compHdrLen, decompressedResult, decompressedBytes, msgLen);
/*      */               break;
/*      */           } 
/*      */         
/*  761 */         } catch (JmqiException mqe) {
/*  762 */           if (Trace.isOn) {
/*  763 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressMsgSegment(RfpTSH,int,int,boolean)", (Throwable)mqe, 2);
/*      */           }
/*      */ 
/*      */           
/*  767 */           if (Trace.isOn) {
/*  768 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressMsgSegment(RfpTSH,int,int,boolean)", (Throwable)mqe, 2);
/*      */           }
/*      */           
/*  771 */           throw mqe;
/*      */         } 
/*      */         
/*  774 */         if (tsh.getTshType() == 2) {
/*  775 */           decompressedResult[19] = (byte)(decompressedResult[19] & 0xFFFFFFFE);
/*      */         } else {
/*      */           
/*  778 */           decompressedResult[11] = (byte)(decompressedResult[11] & 0xFFFFFFFE);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  783 */         System.arraycopy(segment, offset + compHdrLen, decompressedResult, decompressedBytes, bytesInBuffer - compHdrLen);
/*      */         
/*  785 */         decompressedBytes += bytesInBuffer - compHdrLen;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  790 */     decompressedResult[4] = (byte)(decompressedBytes >>> 24);
/*  791 */     decompressedResult[5] = (byte)(decompressedBytes >>> 16);
/*  792 */     decompressedResult[6] = (byte)(decompressedBytes >>> 8);
/*  793 */     decompressedResult[7] = (byte)decompressedBytes;
/*      */     
/*  795 */     tsh.setRfpBuffer(decompressedResult);
/*  796 */     tsh.setRfpOffset(0);
/*  797 */     tsh.setTransLength(decompressedBytes);
/*      */     
/*  799 */     if (Trace.isOn) {
/*  800 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressMsgSegment(RfpTSH,int,int,boolean)", decompressedResult);
/*      */     }
/*      */     
/*  803 */     return decompressedResult;
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
/*      */ 
/*      */   
/*      */   public void xorMsgSegment(byte[] segmentP, byte[] toXOr, int start, int len) {
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "xorMsgSegment(byte [ ],byte [ ],int,int)", new Object[] { segmentP, toXOr, 
/*      */             
/*  822 */             Integer.valueOf(start), Integer.valueOf(len) });
/*      */     }
/*  824 */     byte[] segment = (segmentP != null) ? segmentP : new byte[len];
/*  825 */     for (int i = start; i < len + start; i++) {
/*  826 */       toXOr[i] = (byte)(toXOr[i] ^ segment[i - start + 8]);
/*      */     }
/*      */     
/*  829 */     if (Trace.isOn) {
/*  830 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "xorMsgSegment(byte [ ],byte [ ],int,int)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int compressRLE(byte[] uncompressedBuffer, int startPos, int len, byte[] compressedBuffer, int offset) {
/*  850 */     if (Trace.isOn) {
/*  851 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "compressRLE(byte [ ],int,int,byte [ ],int)", new Object[] { uncompressedBuffer, 
/*      */             
/*  853 */             Integer.valueOf(startPos), Integer.valueOf(len), compressedBuffer, Integer.valueOf(offset) });
/*      */     }
/*      */     
/*  856 */     int uncompressedBufferIndex = startPos;
/*  857 */     int resultBufferIndex = offset;
/*  858 */     int repeatCount = 1;
/*      */     
/*  860 */     byte previousByte = uncompressedBuffer[startPos];
/*  861 */     byte currentByte = 0;
/*  862 */     for (uncompressedBufferIndex = startPos + 1; uncompressedBufferIndex < startPos + len; uncompressedBufferIndex++) {
/*  863 */       currentByte = uncompressedBuffer[uncompressedBufferIndex];
/*  864 */       if (previousByte == -85) {
/*  865 */         compressedBuffer[resultBufferIndex++] = -85;
/*  866 */         if (compressedBuffer.length - resultBufferIndex >= 1) {
/*  867 */           compressedBuffer[resultBufferIndex++] = -85;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  872 */       else if (previousByte == currentByte && repeatCount < 255 && len + startPos - uncompressedBufferIndex > 1) {
/*  873 */         repeatCount++;
/*      */       }
/*      */       else {
/*      */         
/*  877 */         if (repeatCount >= 4) {
/*  878 */           if (compressedBuffer.length - resultBufferIndex >= 3) {
/*  879 */             if (len + startPos - uncompressedBufferIndex == 1)
/*      */             {
/*  881 */               if (previousByte == currentByte) {
/*  882 */                 repeatCount++;
/*  883 */                 uncompressedBufferIndex++;
/*      */               } 
/*      */             }
/*  886 */             compressedBuffer[resultBufferIndex++] = -85;
/*  887 */             compressedBuffer[resultBufferIndex++] = previousByte;
/*  888 */             compressedBuffer[resultBufferIndex++] = (byte)repeatCount;
/*      */           }
/*      */         
/*      */         }
/*  892 */         else if (compressedBuffer.length - resultBufferIndex >= repeatCount) {
/*  893 */           for (int i = 0; i < repeatCount; i++) {
/*  894 */             compressedBuffer[resultBufferIndex++] = previousByte;
/*      */           }
/*      */         } 
/*      */         
/*  898 */         repeatCount = 1;
/*      */       } 
/*      */       
/*  901 */       previousByte = currentByte;
/*  902 */       if (len + startPos - uncompressedBufferIndex == 1)
/*      */       {
/*  904 */         if (compressedBuffer.length - resultBufferIndex >= 1) {
/*  905 */           compressedBuffer[resultBufferIndex++] = currentByte;
/*  906 */           if (currentByte == -85 && 
/*  907 */             compressedBuffer.length - resultBufferIndex >= 1) {
/*  908 */             compressedBuffer[resultBufferIndex++] = currentByte;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  914 */     int traceRet1 = resultBufferIndex - offset;
/*      */     
/*  916 */     if (Trace.isOn) {
/*  917 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "compressRLE(byte [ ],int,int,byte [ ],int)", 
/*  918 */           Integer.valueOf(traceRet1));
/*      */     }
/*  920 */     return traceRet1;
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
/*      */ 
/*      */   
/*      */   public int decompressRLE(byte[] compressedBuffer, int startPos, int len, byte[] decompressedBuffer, int offset) throws JmqiException {
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressRLE(byte [ ],int,int,byte [ ],int)", new Object[] { compressedBuffer, 
/*      */             
/*  939 */             Integer.valueOf(startPos), Integer.valueOf(len), decompressedBuffer, 
/*  940 */             Integer.valueOf(offset) });
/*      */     }
/*  942 */     byte currentByte = 0;
/*  943 */     int compressedIndex = startPos;
/*  944 */     int decompressedIndex = offset;
/*  945 */     byte previousByte = compressedBuffer[startPos];
/*  946 */     boolean repeatSequence = false;
/*  947 */     boolean escapeSequence = false;
/*  948 */     for (compressedIndex = startPos + 1; compressedIndex < startPos + len; compressedIndex++) {
/*  949 */       currentByte = compressedBuffer[compressedIndex];
/*  950 */       if (repeatSequence) {
/*  951 */         int repeats = currentByte;
/*  952 */         if (repeats < 0) {
/*  953 */           repeats += 256;
/*      */         }
/*  955 */         byte repeatChar = previousByte;
/*  956 */         if (decompressedBuffer.length - decompressedIndex >= repeats) {
/*  957 */           for (int i = 0; i < repeats; i++) {
/*  958 */             decompressedBuffer[decompressedIndex++] = repeatChar;
/*      */           }
/*      */           
/*  961 */           compressedIndex++;
/*  962 */           if (compressedBuffer.length - compressedIndex >= 1) {
/*  963 */             currentByte = compressedBuffer[compressedIndex];
/*      */ 
/*      */             
/*  966 */             if (len + startPos - compressedIndex == 1) {
/*  967 */               decompressedBuffer[decompressedIndex++] = currentByte;
/*      */             }
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  973 */           HashMap<String, Object> info = new HashMap<>();
/*  974 */           info.put("decompressedBuffer.length", Integer.valueOf(decompressedBuffer.length));
/*  975 */           info.put("decompressedIndex", Integer.valueOf(decompressedIndex));
/*  976 */           info.put("repeats", Integer.valueOf(repeats));
/*  977 */           info.put("Description", "Provided buffer too small");
/*  978 */           Trace.ffst(this, "decompressRLE(byte [ ],int,int,byte [ ],int)", "01", info, null);
/*  979 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2005, null);
/*  980 */           if (Trace.isOn) {
/*  981 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressRLE(byte [ ],int,int,byte [ ],int)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/*  984 */           throw traceRet1;
/*      */         } 
/*  986 */         repeatSequence = false;
/*      */       
/*      */       }
/*  989 */       else if (previousByte == -85) {
/*  990 */         if (!escapeSequence) {
/*  991 */           if (currentByte == -85) {
/*      */             
/*  993 */             if (decompressedBuffer.length - decompressedIndex >= 1) {
/*  994 */               decompressedBuffer[decompressedIndex++] = -85;
/*  995 */               escapeSequence = true;
/*      */             }
/*      */             else {
/*      */               
/*  999 */               HashMap<String, Object> info = new HashMap<>();
/* 1000 */               info.put("decompressedBuffer.length", Integer.valueOf(decompressedBuffer.length));
/* 1001 */               info.put("decompressedIndex", Integer.valueOf(decompressedIndex));
/* 1002 */               info.put("Description", "Provided buffer too small");
/* 1003 */               Trace.ffst(this, "decompressRLE(byte [ ],int,int,byte [ ],int)", "02", info, null);
/* 1004 */               JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2005, null);
/* 1005 */               if (Trace.isOn) {
/* 1006 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressRLE(byte [ ],int,int,byte [ ],int)", (Throwable)traceRet2, 2);
/*      */               }
/*      */               
/* 1009 */               throw traceRet2;
/*      */             } 
/*      */           } else {
/*      */             
/* 1013 */             repeatSequence = true;
/*      */           } 
/*      */         } else {
/*      */           
/* 1017 */           escapeSequence = false;
/*      */         }
/*      */       
/*      */       }
/* 1021 */       else if (decompressedBuffer.length - decompressedIndex >= 1) {
/* 1022 */         decompressedBuffer[decompressedIndex++] = previousByte;
/*      */         
/* 1024 */         if (len + startPos - compressedIndex == 1 && !escapeSequence)
/*      */         {
/* 1026 */           if (decompressedBuffer.length - decompressedIndex >= 1) {
/* 1027 */             decompressedBuffer[decompressedIndex++] = currentByte;
/*      */           }
/*      */           else {
/*      */             
/* 1031 */             HashMap<String, Object> info = new HashMap<>();
/* 1032 */             info.put("decompressedBuffer.length", Integer.valueOf(decompressedBuffer.length));
/* 1033 */             info.put("decompressedIndex", Integer.valueOf(decompressedIndex));
/* 1034 */             info.put("Description", "Provided buffer too small");
/* 1035 */             Trace.ffst(this, "decompressRLE(byte [ ],int,int,byte [ ],int)", "03", info, null);
/* 1036 */             JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2005, null);
/* 1037 */             if (Trace.isOn) {
/* 1038 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressRLE(byte [ ],int,int,byte [ ],int)", (Throwable)traceRet3, 3);
/*      */             }
/*      */             
/* 1041 */             throw traceRet3;
/*      */           }
/*      */         
/*      */         }
/*      */       } else {
/*      */         
/* 1047 */         HashMap<String, Object> info = new HashMap<>();
/* 1048 */         info.put("decompressedBuffer.length", Integer.valueOf(decompressedBuffer.length));
/* 1049 */         info.put("decompressedIndex", Integer.valueOf(decompressedIndex));
/* 1050 */         info.put("Description", "Provided buffer too small");
/* 1051 */         Trace.ffst(this, "decompressRLE(byte [ ],int,int,byte [ ],int)", "04", info, null);
/* 1052 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2005, null);
/* 1053 */         if (Trace.isOn) {
/* 1054 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressRLE(byte [ ],int,int,byte [ ],int)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 1057 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1062 */       previousByte = currentByte;
/*      */     } 
/*      */     
/* 1065 */     int traceRet5 = decompressedIndex - offset;
/*      */     
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressRLE(byte [ ],int,int,byte [ ],int)", 
/* 1069 */           Integer.valueOf(traceRet5));
/*      */     }
/* 1071 */     return traceRet5;
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
/*      */ 
/*      */   
/*      */   public int compressZLIB(byte[] uncompressedBuffer, int startPos, int len, byte[] compressedBuffer, int offset) {
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "compressZLIB(byte [ ],int,int,byte [ ],int)", new Object[] { uncompressedBuffer, 
/*      */             
/* 1090 */             Integer.valueOf(startPos), Integer.valueOf(len), compressedBuffer, Integer.valueOf(offset) });
/*      */     }
/*      */     
/* 1093 */     this.compressor.setInput(uncompressedBuffer, startPos, len);
/*      */     
/* 1095 */     this.compressor.finish();
/* 1096 */     int compressedDataLength = this.compressor.deflate(compressedBuffer, offset, compressedBuffer.length - offset);
/* 1097 */     this.compressor.reset();
/*      */     
/* 1099 */     if (Trace.isOn) {
/* 1100 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "compressZLIB(byte [ ],int,int,byte [ ],int)", 
/* 1101 */           Integer.valueOf(compressedDataLength));
/*      */     }
/* 1103 */     return compressedDataLength;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int decompressZLIB(byte[] compressedBuffer, int startPos, int len, byte[] decompressedBuffer, int offset) throws JmqiException {
/* 1125 */     if (Trace.isOn) {
/* 1126 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressZLIB(byte [ ],int,int,byte [ ],int)", new Object[] { compressedBuffer, 
/*      */             
/* 1128 */             Integer.valueOf(startPos), Integer.valueOf(len), decompressedBuffer, 
/* 1129 */             Integer.valueOf(offset) });
/*      */     }
/* 1131 */     this.decompressor.setInput(compressedBuffer, startPos, len);
/* 1132 */     int decompressedBufferLength = 0;
/*      */     try {
/* 1134 */       decompressedBufferLength = this.decompressor.inflate(decompressedBuffer, offset, decompressedBuffer.length - offset);
/* 1135 */       if (!this.decompressor.finished()) {
/*      */ 
/*      */ 
/*      */         
/* 1139 */         byte[] overflowBuffer = new byte[32768];
/* 1140 */         int overflowBytesNeeded = this.decompressor.inflate(overflowBuffer, 0, overflowBuffer.length);
/*      */         
/* 1142 */         StringBuffer dumpBuffer = new StringBuffer();
/*      */         
/* 1144 */         JmqiTools.hexDump(decompressedBuffer, null, 0, offset, dumpBuffer);
/*      */         
/* 1146 */         HashMap<String, Object> info = new HashMap<>();
/* 1147 */         info.put("decompressedBuffer.length", Integer.valueOf(decompressedBuffer.length));
/* 1148 */         info.put("Inserting data at offset", Integer.valueOf(offset));
/* 1149 */         info.put("Additional space required (bytes)", Integer.valueOf(overflowBytesNeeded));
/* 1150 */         info.put("Buffer content to offset", "\n" + dumpBuffer);
/* 1151 */         info.put("Description", "Provided buffer too small");
/* 1152 */         Trace.ffst(this, "decompressZLIB(byte [ ],int,int,byte [ ],int)", "01", info, null);
/* 1153 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2005, null);
/* 1154 */         if (Trace.isOn) {
/* 1155 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressZLIB(byte [ ],int,int,byte [ ],int)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1158 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1161 */       this.decompressor.reset();
/*      */     }
/* 1163 */     catch (DataFormatException dfe) {
/* 1164 */       if (Trace.isOn) {
/* 1165 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressZLIB(byte [ ],int,int,byte [ ],int)", dfe);
/*      */       }
/*      */       
/* 1168 */       HashMap<String, Object> info = new HashMap<>();
/* 1169 */       info.put("decompressedBuffer.length", Integer.valueOf(decompressedBuffer.length));
/* 1170 */       info.put("Exception message", dfe.getMessage());
/* 1171 */       info.put("Description", "DataFormatException");
/* 1172 */       Trace.ffst(this, "decompressZLIB(byte [ ],int,int,byte [ ],int)", "02", info, null);
/* 1173 */       JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, dfe);
/* 1174 */       if (Trace.isOn) {
/* 1175 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressZLIB(byte [ ],int,int,byte [ ],int)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1178 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1181 */     if (Trace.isOn) {
/* 1182 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCompressor", "decompressZLIB(byte [ ],int,int,byte [ ],int)", 
/* 1183 */           Integer.valueOf(decompressedBufferLength));
/*      */     }
/*      */     
/* 1186 */     return decompressedBufferLength;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteCompressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */