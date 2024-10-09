/*     */ package com.ibm.disthub2.impl.security;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageEncrypter;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MessageProtection
/*     */   implements MessageEncrypter, ClientExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  55 */   private static final DebugObject debug = new DebugObject("MessageProtection");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static final String[][] availLibs = new String[][] { { "cryptolite-export", "com.ibm.disthub2.impl.crypto.export.CryptoLiteWrapper" }, { "cryptolite-nonexport", "com.ibm.disthub2.impl.crypto.nonexport.CryptoLiteWrapper" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   static final Hashtable instantiatedLibraries = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void initLibraryInstance(String paramString) throws CryptoInstantiationException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final MessageProtection instantiateLibrary(String lib, String params) throws CryptoInstantiationException {
/* 113 */     String hash = lib + params;
/*     */     
/* 115 */     if (instantiatedLibraries.containsKey(hash)) {
/* 116 */       return (MessageProtection)instantiatedLibraries.get(hash);
/*     */     }
/*     */     int i;
/* 119 */     for (i = 0; i < availLibs.length && 
/* 120 */       !availLibs[i][0].equals(lib); i++);
/*     */ 
/*     */     
/* 123 */     if (i == availLibs.length) {
/* 124 */       throw new CryptoInstantiationException(ExceptionBuilder.buildReasonString(-464996387, new Object[] { lib }));
/*     */     }
/*     */     try {
/* 127 */       Class<?> lClass = Class.forName(availLibs[i][1]);
/* 128 */       MessageProtection instance = (MessageProtection)lClass.newInstance();
/* 129 */       instance.initLibraryInstance(params);
/* 130 */       instantiatedLibraries.put(hash, instance);
/* 131 */       return instance;
/* 132 */     } catch (Exception e) {
/* 133 */       if (e instanceof CryptoInstantiationException) {
/* 134 */         throw (CryptoInstantiationException)e;
/*     */       }
/* 136 */       throw new CryptoInstantiationException(ExceptionBuilder.buildReasonString(-911376984, new Object[] { e }));
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
/*     */   public abstract int digestLength();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object digestInitState(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void digest(Object paramObject, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void hmac(byte[] key, int koff, int klen, byte[] data, int doff, int dlen, byte[] to, int pos) {
/* 210 */     hmac(hmacPrepareKey(key, koff, klen), data, doff, dlen, to, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Object[] hmacPrepareKey(byte[] key) {
/* 217 */     return hmacPrepareKey(key, 0, key.length);
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
/*     */   public final Object[] hmacPrepareKey(byte[] key, int koff, int klen) {
/* 233 */     byte[] workKey = new byte[64];
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (klen > workKey.length) {
/* 238 */       digest(null, key, koff, klen, workKey, 0);
/*     */     } else {
/* 240 */       System.arraycopy(key, koff, workKey, 0, klen);
/*     */     } 
/*     */     
/*     */     int i;
/* 244 */     for (i = 0; i < workKey.length; i++)
/* 245 */       workKey[i] = (byte)(workKey[i] ^ 0x36); 
/* 246 */     Object istate = digestInitState(null);
/* 247 */     digest(istate, workKey, 0, workKey.length, null, 0);
/*     */ 
/*     */     
/* 250 */     Object ostate = digestInitState(null);
/* 251 */     for (i = 0; i < workKey.length; i++)
/* 252 */       workKey[i] = (byte)(workKey[i] ^ 0x6A); 
/* 253 */     digest(ostate, workKey, 0, workKey.length, null, 0);
/* 254 */     return new Object[] { istate, ostate };
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
/*     */   public final void hmac(Object[] prepkey, byte[] data, int doff, int dlen, byte[] to, int pos) {
/* 267 */     digest(prepkey[0], data, doff, dlen, to, pos);
/* 268 */     digest(prepkey[1], to, pos, digestLength(), to, pos);
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
/*     */   public abstract void random(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int keySize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object generateKey(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void encryptCBC(Object paramObject, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt1, int paramInt2, byte[] paramArrayOfbyte3, int paramInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void encrypt(Object key, byte[] data, int off, int len, byte[] to, int pos) {
/* 332 */     encrypt(key, null, data, off, len, to, pos);
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
/*     */   public void encrypt(Object key, byte[] iv, byte[] data, int off, int len, byte[] to, int pos) {
/* 379 */     if (len <= 0)
/* 380 */       return;  byte[] tiv = iv;
/* 381 */     if (tiv == null) tiv = new byte[blockSize()];
/*     */     
/* 383 */     if (len < blockSize()) {
/* 384 */       encryptCBC(key, null, tiv, 0, blockSize(), tiv, 0);
/* 385 */       for (int j = 0; j < len; j++) {
/* 386 */         tiv[j] = (byte)(tiv[j] ^ data[off++]); to[pos++] = (byte)(tiv[j] ^ data[off++]);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 391 */     int rem = len % blockSize();
/* 392 */     int lmr = len - rem;
/*     */     
/* 394 */     encryptCBC(key, tiv, data, off, lmr, to, pos);
/* 395 */     if (rem == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 400 */     for (int i = 0; i < rem; i++) {
/* 401 */       tiv[i] = (byte)(tiv[i] ^ data[off + lmr + i]);
/* 402 */       to[pos + lmr + i] = to[pos + lmr + i - blockSize()];
/*     */     } 
/*     */ 
/*     */     
/* 406 */     encryptCBC(key, null, tiv, 0, blockSize(), to, pos + lmr - blockSize());
/*     */     
/* 408 */     if (iv != null)
/*     */     {
/* 410 */       System.arraycopy(to, pos + lmr - blockSize(), iv, 0, blockSize());
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
/*     */   public abstract void decryptCBC(Object paramObject, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt1, int paramInt2, byte[] paramArrayOfbyte3, int paramInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void decrypt(Object key, byte[] data, int off, int len, byte[] to, int pos) {
/* 434 */     decrypt(key, null, data, off, len, to, pos);
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
/*     */   public void decrypt(Object key, byte[] iv, byte[] data, int off, int len, byte[] to, int pos) {
/* 475 */     if (len <= 0)
/* 476 */       return;  byte[] tiv = iv;
/*     */     
/* 478 */     if (tiv == null) tiv = new byte[blockSize()];
/*     */     
/* 480 */     if (len < blockSize()) {
/* 481 */       encryptCBC(key, null, tiv, 0, blockSize(), tiv, 0);
/* 482 */       for (int j = 0; j < len; j++) {
/* 483 */         byte z; to[pos++] = (byte)(tiv[j] ^ (z = data[off++]));
/* 484 */         tiv[j] = z;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 489 */     int rem = len % blockSize();
/* 490 */     if (rem == 0) {
/* 491 */       decryptCBC(key, tiv, data, off, len, to, pos);
/*     */       return;
/*     */     } 
/* 494 */     int lmr = len - rem;
/* 495 */     int lmrmb = lmr - blockSize();
/*     */ 
/*     */     
/* 498 */     decryptCBC(key, tiv, data, off, lmrmb, to, pos);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 503 */     byte[] riv = null;
/*     */     
/* 505 */     if (iv != null) {
/* 506 */       riv = new byte[blockSize()];
/*     */       
/* 508 */       System.arraycopy(data, off + lmrmb, riv, 0, blockSize());
/*     */     } 
/*     */     
/* 511 */     decryptCBC(key, null, data, off + lmrmb, blockSize(), to, pos + lmrmb);
/*     */ 
/*     */ 
/*     */     
/* 515 */     for (int i = 0; i < rem; i++) {
/* 516 */       byte x; to[pos + lmr + i] = (byte)(to[pos + lmrmb + i] ^ (x = data[off + lmr + i]));
/* 517 */       to[pos + lmrmb + i] = x;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 522 */     decryptCBC(key, tiv, to, pos + lmrmb, blockSize(), to, pos + lmrmb);
/*     */     
/* 524 */     if (riv != null)
/*     */     {
/* 526 */       System.arraycopy(riv, 0, iv, 0, blockSize());
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
/*     */   public abstract int blockSize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compareBuffers(byte[] b1, int b1off, byte[] b2, int b2off, int len) {
/* 555 */     for (int i = 0; i < len; i++) {
/* 556 */       if (b1[b1off + i] != b2[b2off + i])
/* 557 */         return false; 
/*     */     } 
/* 559 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\security\MessageProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */