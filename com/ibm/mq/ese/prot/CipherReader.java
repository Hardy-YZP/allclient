/*     */ package com.ibm.mq.ese.prot;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIHeader;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
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
/*     */ 
/*     */ public final class CipherReader
/*     */ {
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("CipherReader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/CipherReader.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private PaddedBufferedBlockCipher decCipher = null;
/*     */ 
/*     */   
/*  67 */   byte[] ibuf = null;
/*  68 */   byte[] obuf = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CipherReader(BlockCipher engine) {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "CipherReader", "<init>(BlockCipher)", new Object[] { engine });
/*     */     }
/*  78 */     this.ibuf = new byte[engine.getBlockSize()];
/*  79 */     this.obuf = new byte[512];
/*     */     
/*  81 */     this.decCipher = new PaddedBufferedBlockCipher((BlockCipher)new CBCBlockCipher(engine));
/*     */ 
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.data(this, "CipherReader", "decCipher is ", this.decCipher);
/*     */     }
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "CipherReader", "<init>(BlockCipher)");
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
/*     */   public void decrypt(InputStream in, OutputStream out, byte[] key, byte[] iv) throws DataLengthException, IllegalStateException, IOException, InvalidCipherTextException {
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "CipherReader", "decrypt(InputStream,OutputStream,byte [ ],byte [ ])", new Object[] { in, out, key, iv });
/*     */     }
/*     */     
/* 111 */     ParametersWithIV parameterIV = new ParametersWithIV((CipherParameters)new KeyParameter(key), iv);
/*     */ 
/*     */     
/* 114 */     this.decCipher.init(false, (CipherParameters)parameterIV);
/*     */     
/* 116 */     int bytesIn = 0;
/* 117 */     int bytesOut = 0;
/*     */     
/* 119 */     while ((bytesIn = in.read(this.ibuf)) >= 0) {
/* 120 */       bytesOut = this.decCipher.processBytes(this.ibuf, 0, bytesIn, this.obuf, 0);
/* 121 */       out.write(this.obuf, 0, bytesOut);
/*     */     } 
/*     */     
/* 124 */     bytesOut = this.decCipher.doFinal(this.obuf, 0);
/* 125 */     out.write(this.obuf, 0, bytesOut);
/*     */     
/* 127 */     out.flush();
/*     */     
/* 129 */     in.close();
/* 130 */     out.close();
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "CipherReader", "decrypt(InputStream,OutputStream,byte [ ],byte [ ])");
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
/*     */   public byte[] read(AMBIHeader hdr, InputStream inputStream, byte[] key) throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "CipherReader", "read(AMBIHeader,InputStream,byte [ ])", new Object[] { hdr, inputStream, key });
/*     */     }
/*     */ 
/*     */     
/* 155 */     inputStream.skip(hdr.getKeyBlockSize());
/*     */     
/* 157 */     byte[] iv = new byte[hdr.getIVBlockSize()];
/* 158 */     inputStream.read(iv);
/*     */     
/* 160 */     ByteArrayOutputStream out = new ByteArrayOutputStream(hdr.getOrigSize());
/*     */     
/* 162 */     decrypt(inputStream, out, key, iv);
/*     */     
/* 164 */     byte[] traceRet1 = out.toByteArray();
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "CipherReader", "read(AMBIHeader,InputStream,byte [ ])", traceRet1);
/*     */     }
/* 168 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\CipherReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */