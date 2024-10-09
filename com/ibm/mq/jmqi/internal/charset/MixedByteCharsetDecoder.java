/*     */ package com.ibm.mq.jmqi.internal.charset;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MixedByteCharsetDecoder
/*     */   extends CharsetDecoder
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/MixedByteCharsetDecoder.java";
/*     */   private static final int SINGLE_BYTE = 0;
/*     */   private static final int DOUBLE_BYTE_FIRST_BYTE = 1;
/*     */   private static final int DOUBLE_BYTE_SECOND_BYTE = 2;
/*  82 */   private int mode = 0;
/*     */ 
/*     */   
/*     */   private static final int SHIFT_IN = 14;
/*     */   
/*     */   private static final int SHIFT_OUT = 15;
/*     */   
/*  89 */   static int[] singleByteToChar = new int[256];
/*     */ 
/*     */   
/*  92 */   static ArrayList<Integer> doubleByteToCharList = new ArrayList<>(); static DoubleByteRange[] doubleByteRanges; private int byteValue;
/*     */   private int charValue;
/*     */   private int expectedByteValue;
/*     */   private int byteRangeStart;
/*     */   private int byteRangeEnd;
/*     */   private boolean inputFileEOF;
/*     */   private List<DoubleByteRange> dbRangeList;
/*     */   private int doubleByte;
/*     */   
/*     */   static class DoubleByteRange { int rangeStart;
/*     */     int rangeEnd;
/*     */     int[] doubleByteToChar;
/*     */     
/*     */     public DoubleByteRange(int byteRangeStart, int byteRangeEnd, ArrayList<Integer> doubleByteToCharList) {
/* 106 */       this.rangeStart = byteRangeStart;
/* 107 */       this.rangeEnd = byteRangeEnd;
/* 108 */       this.doubleByteToChar = new int[doubleByteToCharList.size()];
/* 109 */       int i = 0;
/* 110 */       for (Integer db2c : doubleByteToCharList)
/*     */       {
/* 112 */         this.doubleByteToChar[i++] = db2c.intValue();
/*     */       }
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MixedByteCharsetDecoder(Charset cs, float averageCharsPerByte, float maxCharsPerByte, String codeTableFileName) {
/* 123 */     super(cs, averageCharsPerByte, maxCharsPerByte);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.expectedByteValue = 0;
/*     */ 
/*     */     
/* 138 */     this.byteRangeStart = 0;
/* 139 */     this.byteRangeEnd = 0;
/*     */ 
/*     */     
/* 142 */     this.inputFileEOF = false;
/*     */ 
/*     */     
/* 145 */     this.dbRangeList = new ArrayList<>();
/*     */     readCodeTables(codeTableFileName);
/*     */     this.doubleByte = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readCodeTables(final String codeTableFileName) {
/* 156 */     BufferedReader br = AccessController.<BufferedReader>doPrivileged(new PrivilegedAction<BufferedReader>()
/*     */         {
/*     */           public BufferedReader run()
/*     */           {
/* 160 */             BufferedReader result = null;
/*     */             try {
/* 162 */               String filename = "META-INF/" + codeTableFileName;
/*     */ 
/*     */               
/* 165 */               InputStream resource = ClassLoader.getSystemResourceAsStream(filename);
/*     */ 
/*     */               
/* 168 */               if (resource == null) {
/* 169 */                 ClassLoader thisClassesLoader = getClass().getClassLoader();
/* 170 */                 resource = thisClassesLoader.getResourceAsStream(filename);
/*     */               } 
/*     */ 
/*     */               
/* 174 */               if (resource == null) {
/* 175 */                 ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/* 176 */                 resource = threadContextClassloader.getResourceAsStream(filename);
/*     */               } 
/*     */ 
/*     */               
/* 180 */               if (resource != null)
/*     */               {
/* 182 */                 result = new BufferedReader(new InputStreamReader(resource, Charset.defaultCharset()));
/*     */               }
/*     */             }
/* 185 */             catch (AccessControlException accessControlException) {}
/*     */ 
/*     */ 
/*     */             
/* 189 */             return result;
/*     */           }
/*     */         });
/*     */     
/* 193 */     if (br != null) {
/*     */       
/* 195 */       getAline(br);
/*     */ 
/*     */       
/* 198 */       while (!this.inputFileEOF && this.byteValue <= 255) {
/* 199 */         while (this.expectedByteValue < this.byteValue) {
/*     */           
/* 201 */           singleByteToChar[this.expectedByteValue] = 65535;
/* 202 */           this.expectedByteValue++;
/*     */         } 
/* 204 */         singleByteToChar[this.byteValue] = this.charValue;
/* 205 */         this.expectedByteValue = this.byteValue + 1;
/*     */         
/* 207 */         getAline(br);
/*     */       } 
/*     */       
/* 210 */       while (this.expectedByteValue <= 255) {
/*     */         
/* 212 */         singleByteToChar[this.expectedByteValue] = 65535;
/* 213 */         this.expectedByteValue++;
/*     */       } 
/*     */ 
/*     */       
/* 217 */       this.expectedByteValue = this.byteValue;
/* 218 */       this.byteRangeStart = this.byteValue;
/*     */       
/* 220 */       while (!this.inputFileEOF) {
/* 221 */         if (this.byteValue != this.expectedByteValue) {
/* 222 */           processBreak(this.byteValue);
/*     */         }
/* 224 */         doubleByteToCharList.add(Integer.valueOf(this.charValue));
/* 225 */         this.byteRangeEnd = this.byteValue;
/* 226 */         this.expectedByteValue = this.byteValue + 1;
/*     */         
/* 228 */         getAline(br);
/*     */       } 
/* 230 */       processBreak(-1);
/*     */ 
/*     */       
/* 233 */       doubleByteRanges = this.dbRangeList.<DoubleByteRange>toArray(new DoubleByteRange[0]);
/*     */       
/*     */       try {
/* 236 */         br.close();
/*     */       }
/* 238 */       catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getAline(BufferedReader br) {
/*     */     String inLine;
/*     */     while (true) {
/*     */       try {
/* 249 */         inLine = br.readLine();
/*     */       }
/* 251 */       catch (IOException e) {
/* 252 */         this.inputFileEOF = true;
/*     */         
/*     */         return;
/*     */       } 
/* 256 */       if (inLine == null) {
/* 257 */         this.inputFileEOF = true;
/*     */         
/*     */         return;
/*     */       } 
/* 261 */       if (inLine.charAt(0) == '#')
/*     */         continue; 
/*     */       break;
/*     */     } 
/* 265 */     String[] fields = inLine.split("\\s+");
/* 266 */     this.byteValue = Integer.parseInt(fields[0], 16);
/* 267 */     this.charValue = Integer.parseInt(fields[1], 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processBreak(int byteValue1) {
/* 275 */     DoubleByteRange dbr = new DoubleByteRange(this.byteRangeStart, this.byteRangeEnd, doubleByteToCharList);
/* 276 */     this.dbRangeList.add(dbr);
/* 277 */     doubleByteToCharList.clear();
/* 278 */     this.byteRangeStart = byteValue1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void implReset() {
/* 289 */     this.mode = 0;
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
/*     */   protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
/* 309 */     while (in.hasRemaining()) {
/* 310 */       if (out.hasRemaining()) {
/* 311 */         byte inputByte; char outputChar; int index; int dbRangeNo; char c1; switch (this.mode) {
/*     */           case 0:
/* 313 */             inputByte = in.get();
/* 314 */             if (inputByte == 14) {
/* 315 */               this.mode = 1;
/*     */               continue;
/*     */             } 
/* 318 */             index = (inputByte + 256) % 256;
/* 319 */             c1 = (char)singleByteToChar[index];
/* 320 */             out.put(c1);
/*     */             continue;
/*     */           
/*     */           case 1:
/* 324 */             this.doubleByte = (in.get() + 256) % 256;
/* 325 */             if (this.doubleByte == 15) {
/* 326 */               this.mode = 0;
/*     */               continue;
/*     */             } 
/* 329 */             this.mode = 2;
/*     */             continue;
/*     */ 
/*     */           
/*     */           case 2:
/* 334 */             this.doubleByte <<= 8;
/* 335 */             this.doubleByte |= (in.get() + 256) % 256;
/* 336 */             outputChar = Character.MAX_VALUE;
/* 337 */             for (dbRangeNo = 0; dbRangeNo < doubleByteRanges.length && 
/* 338 */               this.doubleByte >= (doubleByteRanges[dbRangeNo]).rangeStart; dbRangeNo++) {
/*     */ 
/*     */ 
/*     */               
/* 342 */               if (this.doubleByte >= (doubleByteRanges[dbRangeNo]).rangeStart && this.doubleByte <= (doubleByteRanges[dbRangeNo]).rangeEnd) {
/* 343 */                 outputChar = (char)(doubleByteRanges[dbRangeNo]).doubleByteToChar[this.doubleByte - (doubleByteRanges[dbRangeNo]).rangeStart];
/*     */                 break;
/*     */               } 
/*     */             } 
/* 347 */             if (outputChar == Character.MAX_VALUE) {
/* 348 */               return CoderResult.unmappableForLength(2);
/*     */             }
/* 350 */             out.put(outputChar);
/* 351 */             this.mode = 1;
/*     */             continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/* 360 */       return CoderResult.OVERFLOW;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 365 */     return CoderResult.UNDERFLOW;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\MixedByteCharsetDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */