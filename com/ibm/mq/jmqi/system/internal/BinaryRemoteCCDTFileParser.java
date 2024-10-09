/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQAIR;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BinaryRemoteCCDTFileParser
/*     */   extends BinaryCCDTFileParser
/*     */ {
/*  48 */   private static final Class<BinaryRemoteCCDTFileParser> cclass = BinaryRemoteCCDTFileParser.class;
/*     */   
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/BinaryRemoteCCDTFileParser.java";
/*     */ 
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) Trace.data(cclass.getName(), "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/BinaryRemoteCCDTFileParser.java");
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BinaryRemoteCCDTFileParser(JmqiEnvironment env, URL url, InputStream ccdtInputStream) {
/*  63 */     super(env, url, ccdtInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processCRLRecord(BinaryCCDTFileParser.CCDTRecord crlRecord) throws JmqiException {
/*     */     JmqiCodepage cp;
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "processCRLRecord(CCDTRecord)", new Object[] { crlRecord });
/*     */     }
/*     */     
/*  78 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  79 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls(null);
/*  80 */     byte[] crlBuffer = crlRecord.segmentData;
/*     */ 
/*     */     
/*     */     try {
/*  84 */       cp = JmqiCodepage.getJmqiCodepage(this.env, "US-ASCII");
/*     */       
/*  86 */       if (cp == null) {
/*  87 */         UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException("US_ASCII");
/*  88 */         if (Trace.isOn) {
/*  89 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "processCRLRecord(CCDTRecord)", traceRet2, 1);
/*     */         }
/*     */         
/*  92 */         throw traceRet2;
/*     */       }
/*     */     
/*     */     }
/*  96 */     catch (UnsupportedEncodingException e) {
/*  97 */       if (Trace.isOn) {
/*  98 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "processCRLRecord(CCDTRecord)", e);
/*     */       }
/*     */       
/* 101 */       JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { "US-ASCII", null, null, null, "???" }, 2, 2195, e);
/*     */       
/* 103 */       if (Trace.isOn) {
/* 104 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "processCRLRecord(CCDTRecord)", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 107 */       throw traceRet1;
/*     */     } 
/*     */     
/* 110 */     int version = dc.readI32(crlBuffer, 20, true);
/*     */     
/* 112 */     if (version == 3) {
/*     */       
/* 114 */       int readPos = 1636;
/*     */       
/* 116 */       int crlDataLength = dc.readI32(crlBuffer, readPos, true);
/* 117 */       readPos += 4;
/*     */       
/* 119 */       int totalCrlBytesRead = 0;
/*     */ 
/*     */       
/* 122 */       int crlDataRead = 0;
/* 123 */       while (crlDataRead < crlDataLength + 4) {
/*     */         
/* 125 */         MQAIR authInfoRecord = this.env.newMQAIR();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 134 */         totalCrlBytesRead += 4;
/* 135 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 138 */         int connameLength = dc.readI32(crlBuffer, readPos, true);
/* 139 */         readPos += 4;
/*     */         
/* 141 */         totalCrlBytesRead += connameLength;
/* 142 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 145 */         byte[] connameNameBytes = new byte[connameLength];
/* 146 */         System.arraycopy(crlBuffer, readPos, connameNameBytes, 0, connameLength);
/* 147 */         readPos += connameLength;
/*     */         try {
/* 149 */           authInfoRecord.setAuthInfoConnName(new String(connameNameBytes, "US-ASCII"));
/*     */         }
/* 151 */         catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */ 
/*     */ 
/*     */         
/* 155 */         totalCrlBytesRead += 4;
/* 156 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 159 */         int fSize = dc.readI32(crlBuffer, readPos, true);
/* 160 */         readPos += 4;
/* 161 */         if (fSize != 4) {
/*     */           break;
/*     */         }
/*     */         
/* 165 */         totalCrlBytesRead += 4;
/* 166 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 169 */         authInfoRecord.setAuthInfoType(dc.readI32(crlBuffer, readPos, true));
/* 170 */         readPos += 4;
/*     */         
/* 172 */         totalCrlBytesRead += 4;
/* 173 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 176 */         int ldapUserNameLength = dc.readI32(crlBuffer, readPos, true);
/* 177 */         readPos += 4;
/*     */         
/* 179 */         totalCrlBytesRead += ldapUserNameLength;
/* 180 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 183 */         byte[] ldapUserNameBytes = new byte[ldapUserNameLength];
/* 184 */         System.arraycopy(crlBuffer, readPos, ldapUserNameBytes, 0, ldapUserNameLength);
/* 185 */         readPos += ldapUserNameLength;
/*     */         
/* 187 */         ldapUserNameBytes = restoreData(ldapUserNameBytes);
/* 188 */         authInfoRecord.setLdapUserName(dc.readField(ldapUserNameBytes, 0, ldapUserNameBytes.length, cp, jTls));
/*     */         
/* 190 */         totalCrlBytesRead += 4;
/* 191 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 194 */         int ldapPasswordLength = dc.readI32(crlBuffer, readPos, true);
/* 195 */         readPos += 4;
/*     */         
/* 197 */         totalCrlBytesRead += ldapPasswordLength;
/* 198 */         if (totalCrlBytesRead > crlDataLength) {
/*     */           break;
/*     */         }
/* 201 */         byte[] ldapPasswordBytes = new byte[ldapPasswordLength];
/* 202 */         System.arraycopy(crlBuffer, readPos, ldapPasswordBytes, 0, ldapPasswordLength);
/* 203 */         readPos += ldapPasswordLength;
/*     */         
/* 205 */         ldapPasswordBytes = restoreData(ldapPasswordBytes);
/* 206 */         authInfoRecord.setLdapPassword(dc.readField(ldapPasswordBytes, 0, ldapPasswordBytes.length, cp, jTls));
/*     */         
/* 208 */         this.authInfoRecords.addLast(authInfoRecord);
/*     */         
/* 210 */         crlDataRead += totalCrlBytesRead;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "processCRLRecord(CCDTRecord)");
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
/*     */   private static byte[] restoreData(byte[] cryptedData) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "restoreData(byte [ ])", new Object[] { cryptedData });
/*     */     }
/*     */     
/* 238 */     byte[] Data = new byte[8];
/*     */     
/* 240 */     byte[] clearData = null;
/* 241 */     byte[] DecrData = null;
/*     */     
/* 243 */     byte[] Key = { -33, 92, -12, 35, 66, -11, 106, 46 };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     int DataLen = cryptedData.length;
/*     */     
/* 250 */     if (DataLen % 8 == 0) {
/* 251 */       clearData = new byte[DataLen];
/*     */       
/* 253 */       for (int i = 0; i < DataLen; i += 8) {
/* 254 */         int j; for (j = 0; j < 8; j++) {
/* 255 */           Data[j] = cryptedData[i + j];
/*     */         }
/*     */         
/* 258 */         DecrData = Decrypt(Key, Data);
/*     */         
/* 260 */         for (j = 0; j < 8; j++) {
/* 261 */           clearData[i + j] = DecrData[j];
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "restoreData(byte [ ])", clearData);
/*     */     }
/* 269 */     return clearData;
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
/*     */   private static byte[] Decrypt(byte[] Key, byte[] Input) {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "Decrypt(byte [ ],byte [ ])", new Object[] { Key, Input });
/*     */     }
/*     */     
/* 285 */     byte[] BitData = convByteToBit(Key);
/*     */     
/* 287 */     KeySchedule(BitData);
/*     */     
/* 289 */     BitData = convByteToBit(Input);
/*     */     
/* 291 */     byte[] Doutput = Decipher(BitData);
/*     */     
/* 293 */     byte[] traceRet1 = convBitToByte(Doutput);
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "Decrypt(byte [ ],byte [ ])", traceRet1);
/*     */     }
/*     */     
/* 298 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 303 */   private static byte[] IP = new byte[] { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
/*     */ 
/*     */ 
/*     */   
/* 307 */   private static byte[] IP_1 = new byte[] { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static byte[] Decipher(byte[] Input) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "Decipher(byte [ ])", new Object[] { Input });
/*     */     }
/*     */     
/* 319 */     byte[] Output = new byte[64];
/* 320 */     byte[] tmpbuf = new byte[64];
/* 321 */     byte[] L = new byte[32];
/* 322 */     byte[] R = new byte[32];
/*     */     
/* 324 */     byte[] fptr = null;
/*     */     
/*     */     int i;
/* 327 */     for (i = 0; i < 64; i++) {
/* 328 */       tmpbuf[i] = Input[IP[i] - 1];
/*     */     }
/*     */     int j;
/* 331 */     for (j = 0; j < 32; j++) {
/* 332 */       L[j] = tmpbuf[j + 32];
/* 333 */       R[j] = tmpbuf[j];
/*     */     } 
/*     */ 
/*     */     
/* 337 */     for (i = 15; i >= 0; i--) {
/* 338 */       fptr = f(L, KS[i]);
/*     */       
/* 340 */       for (j = 0; j < 32; j++) {
/* 341 */         tmpbuf[j] = (byte)(R[j] ^ fptr[j]);
/* 342 */         tmpbuf[j + 32] = L[j];
/*     */       } 
/*     */       
/* 345 */       for (j = 0; j < 32; j++) {
/* 346 */         L[j] = tmpbuf[j];
/* 347 */         R[j] = tmpbuf[j + 32];
/*     */       } 
/*     */       
/* 350 */       for (j = 0; j < 32; j++) {
/* 351 */         tmpbuf[j] = L[j];
/* 352 */         tmpbuf[j + 32] = R[j];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 357 */     for (i = 0; i < 64; i++) {
/* 358 */       Output[i] = tmpbuf[IP_1[i] - 1];
/*     */     }
/*     */     
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "Decipher(byte [ ])", Output);
/*     */     }
/* 364 */     return Output;
/*     */   }
/*     */ 
/*     */   
/* 368 */   private static byte[][] KS = new byte[16][48];
/*     */ 
/*     */   
/* 371 */   private static byte[] PC_1C = new byte[] { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36 };
/*     */   
/* 373 */   private static byte[] PC_1D = new byte[] { 62, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
/*     */ 
/*     */   
/* 376 */   private static byte[] PC_2C = new byte[] { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2 };
/*     */   
/* 378 */   private static byte[] PC_2D = new byte[] { 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
/*     */ 
/*     */   
/* 381 */   private static byte[] Shifts = new byte[] { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static byte[] KeySchedule(byte[] bitKey) {
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "KeySchedule(byte [ ])", new Object[] { bitKey });
/*     */     }
/*     */ 
/*     */     
/* 392 */     byte[] C = new byte[28];
/* 393 */     byte[] D = new byte[28];
/*     */     
/*     */     int i;
/* 396 */     for (i = 0; i < 28; i++) {
/* 397 */       C[i] = bitKey[PC_1C[i] - 1];
/* 398 */       D[i] = bitKey[PC_1D[i] - 1];
/*     */     } 
/*     */     
/* 401 */     for (i = 0; i < 16; i++) {
/* 402 */       int j; for (j = 0; j < Shifts[i]; j++) {
/* 403 */         byte chc = C[0];
/* 404 */         byte chd = D[0];
/*     */         
/* 406 */         for (int k = 0; k < 27; k++) {
/* 407 */           C[k] = C[k + 1];
/* 408 */           D[k] = D[k + 1];
/*     */         } 
/*     */         
/* 411 */         C[27] = chc;
/* 412 */         D[27] = chd;
/*     */       } 
/*     */       
/* 415 */       for (j = 0; j < 24; j++) {
/* 416 */         KS[i][j] = C[PC_2C[j] - 1];
/* 417 */         KS[i][j + 24] = D[PC_2D[j] - 28 - 1];
/*     */       } 
/*     */     } 
/*     */     
/* 421 */     if (Trace.isOn) {
/* 422 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "KeySchedule(byte [ ])", bitKey);
/*     */     }
/* 424 */     return bitKey;
/*     */   }
/*     */ 
/*     */   
/* 428 */   private static byte[] E = new byte[] { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
/*     */ 
/*     */ 
/*     */   
/* 432 */   private static int[] P = new int[] { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
/*     */ 
/*     */   
/* 435 */   private static byte[][][] S = new byte[][][] { { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } }, { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } }, { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } }, { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } }, { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static byte[] f(byte[] R, byte[] K) {
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "f(byte [ ],byte [ ])", new Object[] { R, K });
/*     */     }
/*     */ 
/*     */     
/* 462 */     byte[] InputS = new byte[48];
/* 463 */     byte[] OutputS = new byte[32];
/* 464 */     byte[] RetF = new byte[32];
/*     */     int i;
/* 466 */     for (i = 0; i < 48; i++) {
/* 467 */       InputS[i] = (byte)(R[E[i] - 1] ^ K[i]);
/*     */     }
/*     */     
/* 470 */     for (i = 0; i < 8; i++) {
/* 471 */       int j = i * 6;
/*     */       
/* 473 */       int I = InputS[j] * 2 + InputS[j + 5];
/* 474 */       int J = InputS[j + 1] * 8 + InputS[j + 2] * 4 + InputS[j + 3] * 2 + InputS[j + 4];
/*     */       
/* 476 */       int k = i * 4;
/*     */       
/* 478 */       int temp = S[i][I][J];
/*     */       
/* 480 */       if ((temp & 0x8) > 0) {
/* 481 */         OutputS[k] = 1;
/*     */       } else {
/* 483 */         OutputS[k] = 0;
/*     */       } 
/*     */       
/* 486 */       if ((temp & 0x4) > 0) {
/* 487 */         OutputS[k + 1] = 1;
/*     */       } else {
/* 489 */         OutputS[k + 1] = 0;
/*     */       } 
/*     */       
/* 492 */       if ((temp & 0x2) > 0) {
/* 493 */         OutputS[k + 2] = 1;
/*     */       } else {
/* 495 */         OutputS[k + 2] = 0;
/*     */       } 
/*     */       
/* 498 */       if ((temp & 0x1) > 0) {
/* 499 */         OutputS[k + 3] = 1;
/*     */       } else {
/* 501 */         OutputS[k + 3] = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 505 */     for (i = 0; i < 32; i++) {
/* 506 */       RetF[i] = OutputS[P[i] - 1];
/*     */     }
/*     */     
/* 509 */     if (Trace.isOn) {
/* 510 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "f(byte [ ],byte [ ])", RetF);
/*     */     }
/* 512 */     return RetF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] convByteToBit(byte[] byteData) {
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "convByteToBit(byte [ ])", new Object[] { byteData });
/*     */     }
/*     */     
/* 524 */     byte[] bitData = new byte[64];
/*     */ 
/*     */     
/* 527 */     for (int i = 0; i < 64; i += 8) {
/* 528 */       int ch = byteData[i / 8];
/*     */       
/* 530 */       if (ch < 0) {
/* 531 */         ch += 256;
/*     */       }
/*     */       int mask;
/* 534 */       for (int j = 0; j < 8; j++, mask /= 2) {
/* 535 */         if ((ch & mask) > 0) {
/* 536 */           bitData[i + j] = 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 541 */     if (Trace.isOn) {
/* 542 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "convByteToBit(byte [ ])", bitData);
/*     */     }
/* 544 */     return bitData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] convBitToByte(byte[] bitData) {
/* 552 */     if (Trace.isOn) {
/* 553 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "convBitToByte(byte [ ])", new Object[] { bitData });
/*     */     }
/*     */     
/* 556 */     int i = 0;
/* 557 */     byte[] byteData = new byte[8];
/*     */     
/* 559 */     for (i = 0; i < 8; i++) {
/* 560 */       int j = i * 8;
/*     */       
/* 562 */       byteData[i] = (byte)(bitData[j] << 7 | bitData[j + 1] << 6 | bitData[j + 2] << 5 | bitData[j + 3] << 4 | bitData[j + 4] << 3 | bitData[j + 5] << 2 | bitData[j + 6] << 1 | bitData[j + 7]);
/*     */     } 
/*     */ 
/*     */     
/* 566 */     if (Trace.isOn) {
/* 567 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "convBitToByte(byte [ ])", byteData);
/*     */     }
/* 569 */     return byteData;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\BinaryRemoteCCDTFileParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */