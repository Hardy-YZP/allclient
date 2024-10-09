/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ import com.ibm.disthub2.impl.util.Hex;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StandardMessageHandle
/*     */   extends StandardMessageDataHandle
/*     */   implements MessageHandle, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  43 */   private static final DebugObject debug = new DebugObject("StandardMessageHandle");
/*     */ 
/*     */   
/*     */   private Schema[] schemata;
/*     */ 
/*     */   
/*     */   boolean legacy;
/*     */ 
/*     */   
/*     */   private byte[] cryptoContents;
/*     */ 
/*     */   
/*     */   private int cryptoOffset;
/*     */ 
/*     */   
/*     */   private int cryptoLength;
/*     */ 
/*     */   
/*     */   private int keyOffset;
/*     */ 
/*     */   
/*     */   private short keyLength;
/*     */ 
/*     */   
/*     */   StandardMessageHandle(boolean legacy, FlatSchema sfs) {
/*  68 */     super(sfs);
/*  69 */     this.legacy = legacy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   StandardMessageHandle(boolean legacy, FlatSchema sfs, byte[] contents, int offset, int length, MessageEncrypter encrypter, int sksl) {
/*  77 */     super(sfs);
/*  78 */     this.legacy = legacy;
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (!legacy) {
/*  83 */       int nSchemata = ArrayUtil.readShort(contents, offset);
/*  84 */       offset += 2;
/*  85 */       length -= 2;
/*  86 */       if (sksl != -1)
/*  87 */         sksl -= 2; 
/*  88 */       this.schemata = new Schema[nSchemata + 1];
/*  89 */       this.schemata[0] = (sfs.variants[0]).schema;
/*  90 */       for (int i = 0; i < nSchemata; i++) {
/*  91 */         long schemaId = ArrayUtil.readLong(contents, offset);
/*  92 */         offset += 8;
/*  93 */         length -= 8;
/*  94 */         if (sksl != -1)
/*  95 */           sksl -= 8; 
/*  96 */         this.schemata[i + 1] = (SchemaRegistry.retrieve(schemaId)).encoding;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     long multiChoice = ArrayUtil.readLong(contents, offset);
/*     */     
/* 107 */     offset += 8;
/* 108 */     length -= 8;
/* 109 */     if (sksl != -1)
/* 110 */       sksl -= 8; 
/* 111 */     if (encrypter != null) {
/*     */ 
/*     */       
/* 114 */       short keylen = ArrayUtil.readShort(contents, offset);
/*     */       
/* 116 */       offset += 2 + keylen;
/* 117 */       length -= 2 + keylen;
/* 118 */       if (sksl != -1) {
/* 119 */         sksl -= 2 + keylen;
/*     */         
/* 121 */         int cryptlen = length - sksl;
/* 122 */         if (cryptlen > 0) {
/*     */ 
/*     */ 
/*     */           
/* 126 */           this.cryptoContents = contents;
/* 127 */           this.cryptoOffset = offset + sksl;
/* 128 */           this.cryptoLength = cryptlen;
/* 129 */           this.keyOffset = offset - keylen;
/* 130 */           this.keyLength = keylen;
/* 131 */           byte[] newContents = new byte[length];
/* 132 */           System.arraycopy(contents, offset, newContents, 0, sksl);
/* 133 */           encrypter.decrypt(encrypter.generateKey(contents, offset - keylen, keylen), contents, offset + sksl, cryptlen, newContents, sksl);
/*     */           
/* 135 */           contents = newContents;
/* 136 */           offset = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/* 140 */     if (debug.debugIt(16)) {
/* 141 */       debug.debug(-153415734321212L, "oldMessage: sksl=" + sksl + ", multiChoice=" + multiChoice + ", contents=" + 
/* 142 */           Hex.toString(contents, offset, length));
/*     */     }
/*     */     
/* 145 */     oldMessage(contents, offset, length, sksl, multiChoice);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageHandle duplicate() {
/* 152 */     int len = getEncodedLength((MessageEncrypter)null);
/* 153 */     byte[] frame = new byte[len];
/* 154 */     toByteArray(frame, 0, len, (MessageEncrypter)null);
/* 155 */     return new StandardMessageHandle(this.legacy, this.schema, (byte[])frame.clone(), 0, len, null, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public short getInterpreterId() {
/* 161 */     return this.legacy ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Schema[] getSchemata() {
/* 168 */     if (this.schemata == null) {
/* 169 */       FastVector accum = new FastVector();
/* 170 */       getSchemata(accum, true);
/* 171 */       this.schemata = new Schema[accum.m_count];
/* 172 */       System.arraycopy(accum.m_data, 0, this.schemata, 0, accum.m_count);
/*     */     } 
/* 174 */     return this.schemata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean changed() {
/* 181 */     return this.changed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncodedLength(MessageEncrypter encrypter) {
/* 188 */     int ans = getEncodedLength();
/* 189 */     ans += 8;
/* 190 */     if (encrypter != null)
/*     */     {
/* 192 */       ans += encrypter.keySize() + 2; } 
/* 193 */     if (!this.legacy) {
/*     */       
/* 195 */       ans += 2;
/* 196 */       if (this.schemata == null)
/* 197 */         getSchemata(); 
/* 198 */       ans += 8 * (this.schemata.length - 1);
/*     */     } 
/* 200 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toByteArray(byte[] buffer, int offset, int length, MessageEncrypter encrypter) {
/* 207 */     int sksl, choiceOffset = offset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     if (!this.legacy) {
/* 213 */       if (this.schemata == null)
/* 214 */         getSchemata(); 
/* 215 */       ArrayUtil.writeShort(buffer, choiceOffset, (short)(this.schemata.length - 1));
/* 216 */       choiceOffset += 2;
/* 217 */       for (int i = 1; i < this.schemata.length; i++) {
/* 218 */         ArrayUtil.writeLong(buffer, choiceOffset, this.schemata[i].getId());
/* 219 */         choiceOffset += 8;
/*     */       } 
/*     */     } 
/* 222 */     int dataOffset = choiceOffset + 8;
/*     */     
/* 224 */     if (encrypter == null) {
/*     */       
/* 226 */       sksl = toByteArray(buffer, dataOffset);
/*     */     } else {
/*     */       
/* 229 */       dataOffset += encrypter.keySize() + 2;
/*     */ 
/*     */       
/* 232 */       if (this.cryptoDirty) {
/* 233 */         this.cryptoContents = null;
/*     */       }
/* 235 */       byte[] sideBuf = new byte[length - 8];
/* 236 */       sksl = toByteArray(sideBuf, 0);
/* 237 */       if (debug.debugIt(16)) {
/* 238 */         debug.debug(-153415734321212L, "toByteArray", "Encryption side buffer: " + 
/* 239 */             Hex.toString(sideBuf));
/*     */       }
/* 241 */       System.arraycopy(sideBuf, 0, buffer, dataOffset, sksl);
/*     */       
/* 243 */       if (this.cryptoContents != null) {
/*     */         
/* 245 */         int cryptOffset = dataOffset + sksl;
/* 246 */         int cryptlen = length + offset - cryptOffset;
/* 247 */         Assert.condition((cryptlen == this.cryptoLength));
/* 248 */         if (debug.debugIt(16))
/* 249 */           debug.debug(-153415734321212L, "toByteArray", "Reusing cryptoContents"); 
/* 250 */         ArrayUtil.writeShort(buffer, choiceOffset + 8, this.keyLength);
/* 251 */         System.arraycopy(this.cryptoContents, this.keyOffset, buffer, choiceOffset + 10, this.keyLength);
/* 252 */         System.arraycopy(this.cryptoContents, this.cryptoOffset, buffer, cryptOffset, cryptlen);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 258 */         int cryptOffset = dataOffset + sksl;
/* 259 */         int cryptlen = length + offset - cryptOffset;
/* 260 */         if (debug.debugIt(16)) {
/* 261 */           debug.debug(-153415734321212L, "toByteArray", "Encrypting at " + cryptOffset + " for length " + cryptlen);
/*     */         }
/* 263 */         short keysize = (short)encrypter.keySize();
/* 264 */         ArrayUtil.writeShort(buffer, choiceOffset + 8, keysize);
/* 265 */         this.keyOffset = choiceOffset + 10;
/* 266 */         encrypter.random(buffer, this.keyOffset, keysize);
/* 267 */         encrypter.encrypt(encrypter.generateKey(buffer, this.keyOffset, keysize), sideBuf, sksl, cryptlen, buffer, cryptOffset);
/*     */         
/* 269 */         this.cryptoContents = buffer;
/* 270 */         this.cryptoOffset = cryptOffset;
/* 271 */         this.cryptoLength = cryptlen;
/* 272 */         this.keyLength = keysize;
/*     */       } 
/*     */     } 
/*     */     
/* 276 */     ArrayUtil.writeLong(buffer, choiceOffset, this.map.multiChoice);
/*     */ 
/*     */ 
/*     */     
/* 280 */     return sksl + dataOffset - offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 291 */     this.schemata = null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\StandardMessageHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */