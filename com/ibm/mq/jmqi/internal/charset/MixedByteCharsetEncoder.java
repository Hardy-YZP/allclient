/*     */ package com.ibm.mq.jmqi.internal.charset;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
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
/*     */ public class MixedByteCharsetEncoder
/*     */   extends CharsetEncoder
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/MixedByteCharsetEncoder.java";
/*     */   private static final int SINGLE_BYTE = 0;
/*     */   private static final int DOUBLE_BYTE = 1;
/*  76 */   private int mode = 0;
/*     */   private static final int NO_LEFTOVER = -1;
/*  78 */   private int leftOver = -1;
/*     */ 
/*     */   
/*     */   private static final int SHIFT_IN = 14;
/*     */   
/*     */   private static final int SHIFT_OUT = 15;
/*     */   
/*  85 */   static ArrayList<Integer> charToByteList = new ArrayList<>();
/*     */   static Range[] ranges;
/*     */   private int byteValue;
/*     */   private int charValue;
/*     */   private int expectedCharValue;
/*     */   private int charRangeStart;
/*     */   private int charRangeEnd;
/*     */   private boolean inputFileEOF;
/*     */   private List<Range> rangeList;
/*     */   
/*     */   static class Range {
/*     */     public Range(int charRangeStart, int charRangeEnd, ArrayList<Integer> charToByteList) {
/*  97 */       this.rangeStart = charRangeStart;
/*  98 */       this.rangeEnd = charRangeEnd;
/*  99 */       this.charToByte = new int[charToByteList.size()];
/* 100 */       int i = 0;
/* 101 */       for (Integer c2b : charToByteList)
/*     */       {
/* 103 */         this.charToByte[i++] = c2b.intValue(); } 
/*     */     }
/*     */     
/*     */     int rangeStart;
/*     */     int rangeEnd;
/*     */     int[] charToByte;
/*     */   }
/*     */   
/*     */   protected MixedByteCharsetEncoder(MixedByteCharset cs, float averageBytesPerChar, float maxBytesPerChar, String codeTableFileName) {
/* 112 */     super(cs, averageBytesPerChar, maxBytesPerChar);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     this.expectedCharValue = 0;
/*     */ 
/*     */     
/* 126 */     this.charRangeStart = 0;
/* 127 */     this.charRangeEnd = 0;
/*     */ 
/*     */     
/* 130 */     this.inputFileEOF = false;
/*     */ 
/*     */     
/* 133 */     this.rangeList = new ArrayList<>();
/*     */     readCodeTables(codeTableFileName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readCodeTables(final String codeTableFileName) {
/* 142 */     BufferedReader br = AccessController.<BufferedReader>doPrivileged(new PrivilegedAction<BufferedReader>()
/*     */         {
/*     */           public BufferedReader run()
/*     */           {
/* 146 */             BufferedReader result = null;
/*     */             try {
/* 148 */               String filename = "META-INF/" + codeTableFileName;
/*     */ 
/*     */               
/* 151 */               InputStream resource = ClassLoader.getSystemResourceAsStream(filename);
/*     */ 
/*     */               
/* 154 */               if (resource == null) {
/* 155 */                 ClassLoader thisClassesLoader = getClass().getClassLoader();
/* 156 */                 resource = thisClassesLoader.getResourceAsStream(filename);
/*     */               } 
/*     */ 
/*     */               
/* 160 */               if (resource == null) {
/* 161 */                 ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/* 162 */                 resource = threadContextClassloader.getResourceAsStream(filename);
/*     */               } 
/*     */ 
/*     */               
/* 166 */               if (resource != null)
/*     */               {
/* 168 */                 result = new BufferedReader(new InputStreamReader(resource, Charset.defaultCharset()));
/*     */               }
/*     */             }
/* 171 */             catch (AccessControlException accessControlException) {}
/*     */ 
/*     */ 
/*     */             
/* 175 */             return result;
/*     */           }
/*     */         });
/*     */     
/* 179 */     if (br != null) {
/* 180 */       getAline(br);
/*     */       
/* 182 */       this.expectedCharValue = this.charValue;
/* 183 */       this.charRangeStart = this.charValue;
/*     */       
/* 185 */       while (!this.inputFileEOF) {
/* 186 */         if (this.charValue != this.expectedCharValue) {
/* 187 */           processBreak(this.charValue);
/*     */         }
/* 189 */         charToByteList.add(Integer.valueOf(this.byteValue));
/* 190 */         this.charRangeEnd = this.charValue;
/* 191 */         this.expectedCharValue = this.charValue + 1;
/*     */         
/* 193 */         getAline(br);
/*     */       } 
/* 195 */       processBreak(-1);
/*     */ 
/*     */       
/* 198 */       ranges = this.rangeList.<Range>toArray(new Range[this.rangeList.size()]);
/*     */       
/*     */       try {
/* 201 */         br.close();
/*     */       }
/* 203 */       catch (IOException iOException) {}
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
/* 214 */         inLine = br.readLine();
/*     */       }
/* 216 */       catch (IOException e) {
/* 217 */         this.inputFileEOF = true;
/*     */         
/*     */         return;
/*     */       } 
/* 221 */       if (inLine == null) {
/* 222 */         this.inputFileEOF = true;
/*     */         
/*     */         return;
/*     */       } 
/* 226 */       if (inLine.charAt(0) == '#')
/*     */         continue; 
/*     */       break;
/*     */     } 
/* 230 */     String[] fields = inLine.split("\\s+");
/* 231 */     this.byteValue = Integer.parseInt(fields[0], 16);
/* 232 */     this.charValue = Integer.parseInt(fields[1], 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processBreak(int charValue1) {
/* 240 */     Range range = new Range(this.charRangeStart, this.charRangeEnd, charToByteList);
/* 241 */     this.rangeList.add(range);
/* 242 */     charToByteList.clear();
/* 243 */     this.charRangeStart = charValue1;
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
/* 254 */     this.mode = 0;
/* 255 */     this.leftOver = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CoderResult implFlush(ByteBuffer out) {
/* 264 */     super.implFlush(out);
/* 265 */     if (this.mode != 0) {
/* 266 */       out.put((byte)15);
/* 267 */       this.mode = 0;
/*     */     } 
/* 269 */     return CoderResult.UNDERFLOW;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
/* 276 */     if (this.leftOver != -1 && out.hasRemaining()) {
/* 277 */       out.put((byte)this.leftOver);
/* 278 */       this.leftOver = -1;
/*     */     } 
/*     */ 
/*     */     
/* 282 */     while (in.hasRemaining()) {
/* 283 */       if (out.hasRemaining()) {
/*     */         
/* 285 */         int inputChar = in.get() & 0xFF;
/*     */ 
/*     */         
/* 288 */         int outputValue = -1;
/* 289 */         for (int dbRangeNo = 0; dbRangeNo < ranges.length && 
/* 290 */           inputChar >= (ranges[dbRangeNo]).rangeStart; dbRangeNo++) {
/*     */ 
/*     */ 
/*     */           
/* 294 */           if (inputChar >= (ranges[dbRangeNo]).rangeStart && inputChar <= (ranges[dbRangeNo]).rangeEnd) {
/* 295 */             outputValue = (ranges[dbRangeNo]).charToByte[inputChar - (ranges[dbRangeNo]).rangeStart];
/* 296 */             switch (this.mode) {
/*     */               case 0:
/* 298 */                 if (outputValue > 255) {
/* 299 */                   out.put((byte)14);
/* 300 */                   this.mode = 1;
/*     */                 } 
/*     */                 break;
/*     */               case 1:
/* 304 */                 if (outputValue < 256) {
/* 305 */                   out.put((byte)15);
/* 306 */                   this.mode = 0;
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           } 
/*     */         } 
/* 317 */         if (outputValue == -1) {
/* 318 */           CodingErrorAction uca = unmappableCharacterAction();
/* 319 */           if (uca != CodingErrorAction.IGNORE)
/*     */           {
/* 321 */             if (uca == CodingErrorAction.REPLACE) {
/* 322 */               outputValue = replacement()[0];
/* 323 */             } else if (uca == CodingErrorAction.REPORT) {
/* 324 */               return CoderResult.unmappableForLength(1);
/*     */             } 
/*     */           }
/*     */         } 
/* 328 */         if (outputValue != -1)
/*     */         {
/* 330 */           switch (this.mode) {
/*     */             case 0:
/* 332 */               out.put((byte)outputValue);
/*     */               continue;
/*     */             case 1:
/* 335 */               out.put((byte)(outputValue >> 8 & 0xFF));
/* 336 */               if (out.hasRemaining()) {
/* 337 */                 out.put((byte)(outputValue & 0xFF));
/*     */                 continue;
/*     */               } 
/* 340 */               this.leftOver = outputValue & 0xFF;
/*     */               continue;
/*     */           } 
/*     */         
/*     */         }
/*     */         continue;
/*     */       } 
/* 347 */       return CoderResult.OVERFLOW;
/*     */     } 
/*     */ 
/*     */     
/* 351 */     return CoderResult.UNDERFLOW;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\MixedByteCharsetEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */