/*     */ package com.ibm.mq.jmqi.internal.charset;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
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
/*     */ 
/*     */ 
/*     */ public abstract class SingleByteCharset
/*     */   extends CustomCharset
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/SingleByteCharset.java";
/*     */   private char[] bytesToChars;
/*     */   private short[] charsToBytes;
/*     */   
/*     */   public SingleByteCharset(String canonicalName, String[] aliases, char[] CP_to_chars) {
/*  51 */     super(canonicalName, aliases);
/*     */ 
/*     */     
/*  54 */     int largestValue = 0; int i;
/*  55 */     for (i = 0; i <= 255; i++) {
/*  56 */       if (largestValue < CP_to_chars[i]) {
/*  57 */         largestValue = CP_to_chars[i];
/*     */       }
/*     */     } 
/*  60 */     this.charsToBytes = new short[largestValue + 1];
/*  61 */     Arrays.fill(this.charsToBytes, (short)-1);
/*     */ 
/*     */     
/*  64 */     this.bytesToChars = CP_to_chars;
/*  65 */     for (i = 0; i <= 255; i++) {
/*  66 */       this.charsToBytes[this.bytesToChars[i]] = (short)(byte)i;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getBytesToChars() {
/*  74 */     return this.bytesToChars;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Charset cs) {
/*  83 */     if (cs.equals(this)) {
/*  84 */       return true;
/*     */     }
/*     */     
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsetDecoder newDecoder() {
/*  95 */     return new SingleByteCharsetDecoder(this, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharsetEncoder newEncoder() {
/* 103 */     return new SingleByteCharsetEncoder(this, 1.0F, 1.0F);
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
/*     */   public class SingleByteCharsetEncoder
/*     */     extends CharsetEncoder
/*     */   {
/*     */     public SingleByteCharsetEncoder(Charset cs, float averageBytesPerChar, float maxBytesPerChar, byte[] replacement) {
/* 130 */       super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SingleByteCharsetEncoder(Charset cs, float averageBytesPerChar, float maxBytesPerChar) {
/* 142 */       super(cs, averageBytesPerChar, maxBytesPerChar);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
/* 161 */       while (in.hasRemaining()) {
/*     */         
/* 163 */         if (out.hasRemaining()) {
/*     */           
/* 165 */           int inputChar = in.get();
/*     */ 
/*     */           
/* 168 */           short byteValue = -1;
/* 169 */           if (inputChar < SingleByteCharset.this.charsToBytes.length) {
/* 170 */             byteValue = SingleByteCharset.this.charsToBytes[inputChar];
/*     */           }
/* 172 */           if (byteValue != -1) {
/* 173 */             out.put((byte)byteValue);
/*     */             continue;
/*     */           } 
/* 176 */           CodingErrorAction uca = unmappableCharacterAction();
/* 177 */           if (uca == CodingErrorAction.IGNORE)
/*     */             continue; 
/* 179 */           if (uca == CodingErrorAction.REPLACE) {
/* 180 */             out.put(replacement()); continue;
/* 181 */           }  if (uca == CodingErrorAction.REPORT) {
/* 182 */             return CoderResult.unmappableForLength(1);
/*     */           }
/*     */           
/*     */           continue;
/*     */         } 
/* 187 */         return CoderResult.OVERFLOW;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 192 */       return CoderResult.UNDERFLOW;
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
/*     */   public class SingleByteCharsetDecoder
/*     */     extends CharsetDecoder
/*     */   {
/*     */     public SingleByteCharsetDecoder(Charset cs, float averageCharsPerByte, float maxCharsPerByte) {
/* 212 */       super(cs, averageCharsPerByte, maxCharsPerByte);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
/* 228 */       while (in.hasRemaining()) {
/* 229 */         if (out.hasRemaining()) {
/* 230 */           byte inputByte = in.get();
/* 231 */           int index = (inputByte + 256) % 256;
/* 232 */           char outputChar = SingleByteCharset.this.bytesToChars[index];
/* 233 */           out.put(outputChar); continue;
/*     */         } 
/* 235 */         return CoderResult.OVERFLOW;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 241 */       return CoderResult.UNDERFLOW;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\SingleByteCharset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */