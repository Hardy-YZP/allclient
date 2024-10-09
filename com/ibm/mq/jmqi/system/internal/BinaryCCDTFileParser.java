/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BinaryCCDTFileParser
/*     */   extends CCDTFileParser
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/BinaryCCDTFileParser.java";
/*  51 */   private static final Class<BinaryCCDTFileParser> cclass = BinaryCCDTFileParser.class;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) Trace.data(cclass.getName(), "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/BinaryCCDTFileParser.java");
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected static final byte[] HEADER = new byte[] { 65, 77, 81, 82 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private static final byte[] CRLHEADER = new byte[] { 123, 48, 48, 48, 83, 83, 76, 95, 67, 82, 76, 95, 68, 65, 84, 65, 48, 48, 48, 125 };
/*     */ 
/*     */   
/*     */   private static final int sizeofCCDTPointer = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class CCDTRecord
/*     */   {
/*     */     public static final int HEADER_LENGTH = 20;
/*     */ 
/*     */     
/*     */     public int segLength;
/*     */ 
/*     */     
/*     */     public int recLength;
/*     */     
/*     */     public int prev;
/*     */     
/*     */     public int next;
/*     */     
/*     */     public byte[] segmentData;
/*     */ 
/*     */     
/*     */     public CCDTRecord(ByteBuffer buffer) {
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDTRecord", "<init>(ByteBuffer)", new Object[] { buffer });
/*     */       }
/*     */       
/*  94 */       this.segLength = buffer.getInt();
/*  95 */       this.recLength = buffer.getInt();
/*  96 */       buffer.getInt();
/*  97 */       buffer.getInt();
/*  98 */       buffer.getInt();
/*     */       
/* 100 */       if (Trace.isOn) {
/* 101 */         Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDTRecord", "<init>(ByteBuffer)");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean isBinary(JmqiEnvironment env, URL url, InputStream ccdtInputStream) throws JmqiException {
/* 108 */     String methodSignature = "isBinary";
/* 109 */     if (Trace.isOn) Trace.entry(cclass.getName(), "isBinary", new Object[] { url });
/*     */     
/* 111 */     boolean isBinary = true;
/*     */     
/* 113 */     byte[] headerBytes = new byte[HEADER.length];
/*     */     
/* 115 */     ccdtInputStream.mark(headerBytes.length);
/*     */ 
/*     */     
/*     */     try {
/* 119 */       ccdtInputStream.read(headerBytes);
/* 120 */       if (!Arrays.equals(headerBytes, HEADER)) isBinary = false; 
/* 121 */     } catch (IOException e) {
/* 122 */       if (Trace.isOn) Trace.catchBlock(cclass.getName(), "isBinary", e); 
/* 123 */       JmqiException traceRet1 = new JmqiException(env, 9516, new String[] { JmqiTools.getExSumm(e), null, url.toString() }, 2, 2278, e);
/* 124 */       if (Trace.isOn) Trace.throwing(cclass.getName(), "isBinary", (Throwable)traceRet1, 1); 
/* 125 */       throw traceRet1;
/*     */     } 
/*     */     
/*     */     try {
/* 129 */       ccdtInputStream.reset();
/* 130 */     } catch (IOException e) {
/* 131 */       if (Trace.isOn) Trace.catchBlock("com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", e); 
/* 132 */       JmqiException traceRet1 = new JmqiException(env, 9516, new String[] { JmqiTools.getExSumm(e), null, url.toString() }, 2, 2278, e);
/* 133 */       if (Trace.isOn) Trace.throwing("com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", (Throwable)traceRet1, 2); 
/* 134 */       throw traceRet1;
/*     */     } 
/*     */     
/* 137 */     if (Trace.isOn) Trace.exit(cclass.getName(), "isBinary", Boolean.valueOf(isBinary));
/*     */     
/* 139 */     return isBinary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BinaryCCDTFileParser(JmqiEnvironment env, URL url, InputStream ccdtInputStream) {
/* 149 */     super(env, url, ccdtInputStream);
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
/*     */   public void parse() throws JmqiException {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse()", new Object[] { this.ccdtUrl });
/*     */     }
/*     */ 
/*     */     
/* 166 */     if (this.ccdtInputStream == null) this.ccdtInputStream = open(this.env, this.ccdtUrl);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 172 */       byte[] headerBytes = new byte[HEADER.length];
/*     */       try {
/* 174 */         if (this.ccdtInputStream.read(headerBytes) != HEADER.length) {
/* 175 */           JmqiException traceRet2 = new JmqiException(this.env, 9516, new String[] { "EOF", null, this.ccdtUrl.toString() }, 2, 2278, null);
/*     */           
/* 177 */           if (Trace.isOn) {
/* 178 */             Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", (Throwable)traceRet2, 3);
/*     */           }
/*     */           
/* 181 */           throw traceRet2;
/*     */         } 
/* 183 */         if (!Arrays.equals(headerBytes, HEADER)) {
/* 184 */           JmqiException traceRet3 = new JmqiException(this.env, 9555, new String[] { null, null, this.ccdtUrl.toString() }, 2, 2278, null);
/* 185 */           if (Trace.isOn) {
/* 186 */             Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", (Throwable)traceRet3, 4);
/*     */           }
/*     */           
/* 189 */           throw traceRet3;
/*     */         }
/*     */       
/* 192 */       } catch (IOException e) {
/* 193 */         if (Trace.isOn) {
/* 194 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", e, 2);
/*     */         }
/*     */         
/* 197 */         JmqiException traceRet4 = new JmqiException(this.env, 9516, new String[] { JmqiTools.getExSumm(e), null, this.ccdtUrl.toString() }, 2, 2278, e);
/*     */         
/* 199 */         if (Trace.isOn) {
/* 200 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", (Throwable)traceRet4, 5);
/*     */         }
/*     */         
/* 203 */         throw traceRet4;
/*     */       } 
/*     */ 
/*     */       
/* 207 */       boolean parsingComplete = false;
/* 208 */       CCDTRecord ccdtRecord = null;
/* 209 */       while (!parsingComplete)
/*     */       {
/*     */         
/* 212 */         ccdtRecord = nextRecord(this.ccdtInputStream, this.ccdtUrl);
/*     */ 
/*     */         
/* 215 */         if (ccdtRecord == null) {
/* 216 */           parsingComplete = true; continue;
/* 217 */         }  if (ccdtRecord.segLength < 20) {
/*     */           
/* 219 */           JmqiException traceRet5 = new JmqiException(this.env, 9555, new String[] { null, null, this.ccdtUrl.toString() }, 2, 2278, null);
/* 220 */           if (Trace.isOn) {
/* 221 */             Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", (Throwable)traceRet5, 6);
/*     */           }
/*     */           
/* 224 */           throw traceRet5;
/*     */         } 
/*     */         
/* 227 */         byte[] key = new byte[20];
/* 228 */         System.arraycopy(ccdtRecord.segmentData, 0, key, 0, 20);
/*     */ 
/*     */         
/* 231 */         boolean isCRL = true;
/* 232 */         for (int i = 0; i < CRLHEADER.length && i < 20; i++) {
/* 233 */           if (key[i] != CRLHEADER[i]) {
/* 234 */             isCRL = false;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 239 */         if (isCRL) {
/* 240 */           processCRLRecord(ccdtRecord, this.ccdtUrl); continue;
/*     */         } 
/* 242 */         processMQCDRecord(ccdtRecord, this.ccdtUrl);
/*     */       }
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 248 */       if (Trace.isOn) {
/* 249 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)");
/*     */       }
/*     */       try {
/* 252 */         this.ccdtInputStream.close();
/* 253 */         this.ccdtInputStream = null;
/*     */       }
/* 255 */       catch (IOException e) {
/* 256 */         if (Trace.isOn) {
/* 257 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)", e, 3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "parse(final URL)");
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
/*     */   private CCDTRecord nextRecord(InputStream ccdtInputStream, URL url) throws JmqiException {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "nextRecord(InputStream,URL)", new Object[] { ccdtInputStream, url });
/*     */     }
/*     */     
/* 281 */     CCDTRecord nextRecord = null;
/*     */     
/* 283 */     boolean foundRecord = false;
/* 284 */     while (!foundRecord) {
/*     */       
/* 286 */       ByteBuffer headerBuffer = ByteBuffer.allocate(20);
/* 287 */       headerBuffer.order(ByteOrder.LITTLE_ENDIAN);
/* 288 */       int headerBytesRead = 0;
/*     */       try {
/* 290 */         headerBytesRead = ccdtInputStream.read(headerBuffer.array());
/*     */       }
/* 292 */       catch (IOException e) {
/* 293 */         if (Trace.isOn) {
/* 294 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "nextRecord(InputStream,URL)", e, 1);
/*     */         }
/*     */         
/* 297 */         JmqiException traceRet1 = new JmqiException(this.env, 9516, new String[] { JmqiTools.getExSumm(e), null, url.toString() }, 2, 2278, e);
/*     */         
/* 299 */         if (Trace.isOn) {
/* 300 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "nextRecord(InputStream,URL)", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 303 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 313 */       if (headerBytesRead == 4) {
/*     */         
/* 315 */         int endInt = headerBuffer.getInt();
/* 316 */         if (endInt != 0 && 
/* 317 */           Trace.isOn) {
/* 318 */           Trace.data(this, "nextRecord(InputStream,URL)", "Termining 4 bytes of CCDT non-zero: " + Integer.toHexString(endInt));
/*     */         }
/*     */ 
/*     */         
/* 322 */         nextRecord = null;
/* 323 */         foundRecord = true; continue;
/* 324 */       }  if (headerBytesRead < 20) {
/*     */         
/* 326 */         if (Trace.isOn) {
/* 327 */           Trace.data(this, "nextRecord(InputStream,URL)", "Incomplete record. Read " + headerBytesRead + " bytes");
/*     */         }
/* 329 */         nextRecord = null;
/* 330 */         foundRecord = true;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 335 */       nextRecord = new CCDTRecord(headerBuffer);
/*     */ 
/*     */ 
/*     */       
/* 339 */       int segmentDataBytes = nextRecord.segLength - 20;
/*     */       
/* 341 */       if (segmentDataBytes < 0) {
/*     */         
/* 343 */         if (Trace.isOn) {
/* 344 */           Trace.data(this, "nextRecord(InputStream,URL)", "Record has invalid segment length: " + nextRecord.segLength);
/*     */         }
/* 346 */         nextRecord = null;
/* 347 */         foundRecord = true; continue;
/*     */       } 
/* 349 */       byte[] segmentBytes = new byte[segmentDataBytes];
/* 350 */       int segmentBytesRead = 0;
/*     */       try {
/* 352 */         int bytesRead = 0;
/*     */         do {
/* 354 */           bytesRead = ccdtInputStream.read(segmentBytes, segmentBytesRead, segmentDataBytes - segmentBytesRead);
/* 355 */           if (bytesRead <= 0)
/* 356 */             continue;  segmentBytesRead += bytesRead;
/*     */         
/*     */         }
/* 359 */         while (segmentBytesRead < segmentDataBytes && bytesRead > 0);
/*     */         
/* 361 */         if (segmentBytesRead < segmentDataBytes) {
/* 362 */           if (Trace.isOn) {
/* 363 */             Trace.data(this, "nextRecord(InputStream,URL)", "Unexpectedly reached EOF reading CCDT (read " + segmentBytesRead + " of " + segmentDataBytes + "byte segment)");
/*     */           }
/* 365 */           nextRecord = null;
/* 366 */           foundRecord = true; continue;
/* 367 */         }  if (nextRecord.recLength == 0) {
/*     */           
/* 369 */           nextRecord = null;
/* 370 */           foundRecord = false;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 375 */         nextRecord.segmentData = segmentBytes;
/* 376 */         foundRecord = true;
/*     */       
/*     */       }
/* 379 */       catch (IOException e) {
/* 380 */         if (Trace.isOn) {
/* 381 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "nextRecord(InputStream,URL)", e, 2);
/*     */         }
/*     */         
/* 384 */         JmqiException traceRet6 = new JmqiException(this.env, 9516, new String[] { JmqiTools.getExSumm(e), null, url.toString() }, 2, 2278, e);
/*     */         
/* 386 */         if (Trace.isOn) {
/* 387 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "nextRecord(InputStream,URL)", (Throwable)traceRet6, 2);
/*     */         }
/*     */         
/* 390 */         throw traceRet6;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "nextRecord(InputStream,URL)", nextRecord);
/*     */     }
/*     */     
/* 400 */     return nextRecord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processMQCDRecord(CCDTRecord mqcdRecord, URL url) throws JmqiException {
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "processMQCDRecord(CCDTRecord,URL)", new Object[] { mqcdRecord, url });
/*     */     }
/*     */ 
/*     */     
/* 412 */     MQCD channelDefinition = this.env.newMQCD();
/* 413 */     JmqiTls tls = ((JmqiSystemEnvironment)this.env).getJmqiTls(null);
/*     */     
/*     */     try {
/* 416 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, 819);
/*     */       
/* 418 */       channelDefinition.readFromBuffer(mqcdRecord.segmentData, 0, mqcdRecord.recLength, true, 4, true, cp, tls);
/*     */     }
/* 420 */     catch (Exception e) {
/* 421 */       if (Trace.isOn) {
/* 422 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "processMQCDRecord(CCDTRecord,URL)", e, 1);
/*     */       }
/*     */ 
/*     */       
/* 426 */       JmqiException traceRet1 = new JmqiException(this.env, 9555, new String[] { null, null, url.toString() }, 2, 2278, e);
/* 427 */       if (Trace.isOn) {
/* 428 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "processMQCDRecord(CCDTRecord,URL)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 431 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 436 */     if (channelDefinition.getConnectionName().indexOf(",") != -1) {
/* 437 */       String[] connectionNames = channelDefinition.getConnectionName().split(",");
/* 438 */       for (int i = 0; i < connectionNames.length; i++) {
/*     */         try {
/* 440 */           MQCDWrapper subDefinition = new MQCDWrapper((MQCD)channelDefinition.clone(), this.nextSeq++);
/* 441 */           subDefinition.mqcd.setConnectionName(connectionNames[i]);
/* 442 */           this.channelDefinitions.add(subDefinition);
/*     */         }
/* 444 */         catch (CloneNotSupportedException e) {
/* 445 */           if (Trace.isOn) {
/* 446 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "processMQCDRecord(CCDTRecord,URL)", e, 2);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 454 */       this.channelDefinitions.add(new MQCDWrapper(channelDefinition, this.nextSeq++));
/*     */     } 
/*     */     
/* 457 */     if (Trace.isOn)
/* 458 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "processMQCDRecord(CCDTRecord,URL)"); 
/*     */   }
/*     */   
/*     */   protected void processCRLRecord(CCDTRecord ccdtRecord, URL url) {}
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\BinaryCCDTFileParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */