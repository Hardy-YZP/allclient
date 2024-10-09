/*     */ package com.ibm.mq.ese.prot;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.security.SecureRandom;
/*     */ import org.bouncycastle.crypto.BlockCipher;
/*     */ import org.bouncycastle.crypto.CipherParameters;
/*     */ import org.bouncycastle.crypto.DataLengthException;
/*     */ import org.bouncycastle.crypto.InvalidCipherTextException;
/*     */ import org.bouncycastle.crypto.modes.CBCBlockCipher;
/*     */ import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
/*     */ import org.bouncycastle.crypto.params.KeyParameter;
/*     */ import org.bouncycastle.crypto.params.ParametersWithIV;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CipherWriter
/*     */ {
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("CipherWriter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/CipherWriter.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private PaddedBufferedBlockCipher encCipher = null;
/*     */ 
/*     */   
/*  67 */   private byte[] ibuf = null;
/*  68 */   private byte[] obuf = null;
/*  69 */   private byte[] iv = null;
/*     */   
/*  71 */   private static SecureRandom sr = new SecureRandom();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CipherWriter(BlockCipher engine) {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "CipherWriter", "<init>(BlockCipher)", new Object[] { engine });
/*     */     }
/*  81 */     this.ibuf = new byte[engine.getBlockSize()];
/*  82 */     this.iv = new byte[engine.getBlockSize()];
/*  83 */     this.obuf = new byte[512];
/*  84 */     this.encCipher = new PaddedBufferedBlockCipher((BlockCipher)new CBCBlockCipher(engine));
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "CipherWriter", "<init>(BlockCipher)");
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
/*     */   private void encrypt(InputStream in, OutputStream out, byte[] key) throws DataLengthException, IllegalStateException, IOException, InvalidCipherTextException {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "CipherWriter", "encrypt(InputStream,OutputStream,byte [ ])", new Object[] { in, out, key });
/*     */     }
/*     */ 
/*     */     
/* 110 */     ParametersWithIV parameterIV = new ParametersWithIV((CipherParameters)new KeyParameter(key), this.iv);
/*     */ 
/*     */     
/* 113 */     this.encCipher.init(true, (CipherParameters)parameterIV);
/*     */     
/* 115 */     int bytesIn = 0;
/* 116 */     int bytesOut = 0;
/*     */     
/* 118 */     while ((bytesIn = in.read(this.ibuf)) >= 0) {
/* 119 */       bytesOut = this.encCipher.processBytes(this.ibuf, 0, bytesIn, this.obuf, 0);
/* 120 */       out.write(this.obuf, 0, bytesOut);
/*     */     } 
/*     */     
/* 123 */     bytesOut = this.encCipher.doFinal(this.obuf, 0);
/* 124 */     out.write(this.obuf, 0, bytesOut);
/*     */     
/* 126 */     in.close();
/* 127 */     out.close();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "CipherWriter", "encrypt(InputStream,OutputStream,byte [ ])");
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
/*     */   public byte[] write(byte[] data, byte[] key) throws IOException, DataLengthException, IllegalStateException, InvalidCipherTextException {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry(this, "CipherWriter", "write(byte [ ],byte [ ])", new Object[] { data, key });
/*     */     }
/*     */ 
/*     */     
/* 151 */     sr.nextBytes(this.iv);
/*     */ 
/*     */     
/* 154 */     int outSizeEstimate = this.iv.length + data.length;
/* 155 */     ByteArrayOutputStream out = new ByteArrayOutputStream(outSizeEstimate * 11 / 10);
/*     */ 
/*     */     
/* 158 */     out.write(this.iv);
/*     */     
/* 160 */     encrypt(new ByteArrayInputStream(data), out, key);
/*     */     
/* 162 */     byte[] outBytes = out.toByteArray();
/*     */     
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "CipherWriter", "write(byte [ ],byte [ ])", outBytes);
/*     */     }
/* 167 */     return outBytes;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\CipherWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */