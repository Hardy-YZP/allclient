/*     */ package com.ibm.disthub2.impl.security;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.BaseConfig;
/*     */ import com.ibm.disthub2.impl.multicast.MulticastTopic;
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MulticastMessageProtection
/*     */   extends MessageProtection
/*     */ {
/*     */   private MessageProtection mp;
/*  45 */   private long version = 0L;
/*     */   
/*  47 */   private long counter = 0L;
/*     */   
/*  49 */   private long seenCount = 0L;
/*     */   
/*     */   private MulticastTopic multicastTopic;
/*     */ 
/*     */   
/*     */   public MulticastMessageProtection(MulticastTopic multicastTopic) throws CryptoInstantiationException {
/*  55 */     this.multicastTopic = multicastTopic;
/*  56 */     this.version = multicastTopic.timeStamp;
/*     */     
/*  58 */     BaseConfig config = BaseConfig.getBaseConfig();
/*  59 */     this.mp = MessageProtection.instantiateLibrary(config.QOP_MSGHANDLER_LIBRARY, config.QOP_MSGHANDLER_OPTIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int digestLength() {
/*  67 */     return this.mp.digestLength() + 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCounter(boolean increase) {
/*  75 */     return increase ? this.counter++ : this.counter;
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
/*     */   public void digest(Object state, byte[] data, int off, int len, byte[] to, int pos) {
/*  97 */     if (to == null || data.length == len || pos != 0) {
/*     */       
/*  99 */       this.mp.digest(state, data, off, len, to, pos);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 104 */       byte[] newData = new byte[len + 16];
/*     */       
/* 106 */       ArrayUtil.writeLong(newData, 0, this.version);
/* 107 */       ArrayUtil.writeLong(newData, 8, this.counter);
/*     */       
/* 109 */       this.mp.digest(state, newData, 0, len + 16, to, pos);
/*     */       
/* 111 */       ArrayUtil.writeLong(to, pos + digestLength() - 16, this.version);
/* 112 */       ArrayUtil.writeLong(to, pos + digestLength() - 8, this.counter);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compareBuffers(byte[] b1, int b1off, byte[] b2, int b2off, int len) {
/* 121 */     if (this.multicastTopic != null) {
/*     */ 
/*     */       
/* 124 */       long compareVersion = ArrayUtil.readLong(b1, b1off + len - 16);
/* 125 */       long currentCount = ArrayUtil.readLong(b1, b1off + len - 8);
/*     */ 
/*     */ 
/*     */       
/* 129 */       if (compareVersion != this.version && 
/* 130 */         this.multicastTopic.getKeyManager().hasKey(compareVersion)) {
/* 131 */         this.multicastTopic.newKey = compareVersion;
/* 132 */         this.counter = currentCount;
/* 133 */         return false;
/*     */       } 
/*     */       
/* 136 */       if (currentCount < this.seenCount)
/*     */       {
/*     */ 
/*     */         
/* 140 */         return false;
/*     */       }
/* 142 */       if (this.counter != currentCount) {
/*     */ 
/*     */ 
/*     */         
/* 146 */         this.multicastTopic.newCounter = true;
/* 147 */         this.counter = currentCount;
/* 148 */         return false;
/*     */       } 
/*     */       
/* 151 */       this.seenCount = currentCount;
/*     */     } 
/* 153 */     for (int i = 0; i < len; i++) {
/* 154 */       if (b1[b1off + i] != b2[b2off + i])
/* 155 */         return false; 
/* 156 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initLibraryInstance(String params) throws CryptoInstantiationException {
/* 164 */     this.mp.initLibraryInstance(params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object digestInitState(Object state) {
/* 172 */     return this.mp.digestInitState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void random(byte[] buf, int off, int len) {
/* 180 */     this.mp.random(buf, off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int keySize() {
/* 188 */     return this.mp.keySize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object generateKey(byte[] material, int off, int len) {
/* 196 */     return this.mp.generateKey(material, off, len);
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
/*     */   public void encryptCBC(Object key, byte[] iv, byte[] data, int off, int len, byte[] to, int pos) {
/* 210 */     this.mp.encryptCBC(key, iv, data, off, len, to, pos);
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
/*     */   public void decryptCBC(Object key, byte[] iv, byte[] data, int off, int len, byte[] to, int pos) {
/* 230 */     this.mp.decryptCBC(key, iv, data, off, len, to, pos);
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
/*     */   public final int blockSize() {
/* 244 */     return this.mp.blockSize();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\security\MulticastMessageProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */