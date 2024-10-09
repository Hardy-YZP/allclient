/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class IBMUnicodeCharStream
/*     */   implements CharStream
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \nIBM MQSeries Integrator Version 2.0 - 5648-C63 \n(C) Copyright IBM Corp. 2000 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final boolean staticFlag = false;
/*  46 */   public int bufpos = -1;
/*     */   
/*     */   int bufsize;
/*     */   int available;
/*     */   int tokenBegin;
/*     */   private int[] bufline;
/*     */   private int[] bufcolumn;
/*  53 */   private int column = 0;
/*  54 */   private int line = 1;
/*     */   
/*     */   private Reader inputStream;
/*     */   
/*     */   private boolean prevCharIsCR = false;
/*     */   
/*     */   private boolean prevCharIsLF = false;
/*     */   private char[] nextCharBuf;
/*     */   private char[] buffer;
/*  63 */   private int maxNextCharInd = 0;
/*  64 */   private int nextCharInd = -1;
/*  65 */   private int inBuf = 0;
/*     */ 
/*     */   
/*     */   private final void ExpandBuff(boolean wrapAround) {
/*  69 */     char[] newbuffer = new char[this.bufsize + 2048];
/*  70 */     int[] newbufline = new int[this.bufsize + 2048];
/*  71 */     int[] newbufcolumn = new int[this.bufsize + 2048];
/*     */ 
/*     */     
/*     */     try {
/*  75 */       if (wrapAround)
/*     */       {
/*  77 */         System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
/*  78 */         System.arraycopy(this.buffer, 0, newbuffer, this.bufsize - this.tokenBegin, this.bufpos);
/*     */         
/*  80 */         this.buffer = newbuffer;
/*     */         
/*  82 */         System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
/*  83 */         System.arraycopy(this.bufline, 0, newbufline, this.bufsize - this.tokenBegin, this.bufpos);
/*  84 */         this.bufline = newbufline;
/*     */         
/*  86 */         System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
/*  87 */         System.arraycopy(this.bufcolumn, 0, newbufcolumn, this.bufsize - this.tokenBegin, this.bufpos);
/*  88 */         this.bufcolumn = newbufcolumn;
/*     */         
/*  90 */         this.bufpos += this.bufsize - this.tokenBegin;
/*     */       }
/*     */       else
/*     */       {
/*  94 */         System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
/*  95 */         this.buffer = newbuffer;
/*     */         
/*  97 */         System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
/*  98 */         this.bufline = newbufline;
/*     */         
/* 100 */         System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
/* 101 */         this.bufcolumn = newbufcolumn;
/*     */         
/* 103 */         this.bufpos -= this.tokenBegin;
/*     */       }
/*     */     
/* 106 */     } catch (Throwable t) {
/*     */       
/* 108 */       throw new Error(t.getMessage());
/*     */     } 
/*     */     
/* 111 */     this.available = this.bufsize += 2048;
/* 112 */     this.tokenBegin = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void FillBuff() throws IOException {
/* 118 */     if (this.maxNextCharInd == 4096)
/* 119 */       this.maxNextCharInd = this.nextCharInd = 0; 
/*     */     try {
/*     */       int i;
/* 122 */       if ((i = this.inputStream.read(this.nextCharBuf, this.maxNextCharInd, 4096 - this.maxNextCharInd)) == -1) {
/*     */ 
/*     */         
/* 125 */         this.inputStream.close();
/* 126 */         throw new IOException();
/*     */       } 
/*     */       
/* 129 */       this.maxNextCharInd += i;
/*     */       
/*     */       return;
/* 132 */     } catch (IOException e) {
/* 133 */       if (this.bufpos != 0) {
/*     */         
/* 135 */         this.bufpos--;
/* 136 */         backup(0);
/*     */       }
/*     */       else {
/*     */         
/* 140 */         this.bufline[this.bufpos] = this.line;
/* 141 */         this.bufcolumn[this.bufpos] = this.column;
/*     */       } 
/* 143 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final char ReadChar() throws IOException {
/* 149 */     if (++this.nextCharInd >= this.maxNextCharInd) {
/* 150 */       FillBuff();
/*     */     }
/* 152 */     return this.nextCharBuf[this.nextCharInd];
/*     */   }
/*     */ 
/*     */   
/*     */   public final char BeginToken() throws IOException {
/* 157 */     if (this.inBuf > 0) {
/*     */       
/* 159 */       this.inBuf--;
/* 160 */       return this.buffer[this.tokenBegin = (this.bufpos == this.bufsize - 1) ? (this.bufpos = 0) : ++this.bufpos];
/*     */     } 
/*     */ 
/*     */     
/* 164 */     this.tokenBegin = 0;
/* 165 */     this.bufpos = -1;
/*     */     
/* 167 */     return readChar();
/*     */   }
/*     */ 
/*     */   
/*     */   private final void AdjustBuffSize() {
/* 172 */     if (this.available == this.bufsize) {
/*     */       
/* 174 */       if (this.tokenBegin > 2048) {
/*     */         
/* 176 */         this.bufpos = 0;
/* 177 */         this.available = this.tokenBegin;
/*     */       } else {
/*     */         
/* 180 */         ExpandBuff(false);
/*     */       } 
/* 182 */     } else if (this.available > this.tokenBegin) {
/* 183 */       this.available = this.bufsize;
/* 184 */     } else if (this.tokenBegin - this.available < 2048) {
/* 185 */       ExpandBuff(true);
/*     */     } else {
/* 187 */       this.available = this.tokenBegin;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void UpdateLineColumn(char c) {
/* 192 */     this.column++;
/*     */     
/* 194 */     if (this.prevCharIsLF) {
/*     */       
/* 196 */       this.prevCharIsLF = false;
/* 197 */       this.line += this.column = 1;
/*     */     }
/* 199 */     else if (this.prevCharIsCR) {
/*     */       
/* 201 */       this.prevCharIsCR = false;
/* 202 */       if (c == '\n') {
/*     */         
/* 204 */         this.prevCharIsLF = true;
/*     */       } else {
/*     */         
/* 207 */         this.line += this.column = 1;
/*     */       } 
/*     */     } 
/* 210 */     switch (c) {
/*     */       
/*     */       case '\r':
/* 213 */         this.prevCharIsCR = true;
/*     */         break;
/*     */       case '\n':
/* 216 */         this.prevCharIsLF = true;
/*     */         break;
/*     */       case '\t':
/* 219 */         this.column--;
/* 220 */         this.column += 8 - (this.column & 0x7);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 226 */     this.bufline[this.bufpos] = this.line;
/* 227 */     this.bufcolumn[this.bufpos] = this.column;
/*     */   }
/*     */ 
/*     */   
/*     */   public final char readChar() throws IOException {
/* 232 */     if (this.inBuf > 0) {
/*     */       
/* 234 */       this.inBuf--;
/* 235 */       return this.buffer[(this.bufpos == this.bufsize - 1) ? (this.bufpos = 0) : ++this.bufpos];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 240 */     if (++this.bufpos == this.available) {
/* 241 */       AdjustBuffSize();
/*     */     }
/* 243 */     char c = ReadChar();
/*     */     
/* 245 */     UpdateLineColumn(c);
/*     */     
/* 247 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getColumn() {
/* 256 */     return this.bufcolumn[this.bufpos];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLine() {
/* 265 */     return this.bufline[this.bufpos];
/*     */   }
/*     */   
/*     */   public final int getEndColumn() {
/* 269 */     return this.bufcolumn[this.bufpos];
/*     */   }
/*     */   
/*     */   public final int getEndLine() {
/* 273 */     return this.bufline[this.bufpos];
/*     */   }
/*     */   
/*     */   public final int getBeginColumn() {
/* 277 */     return this.bufcolumn[this.tokenBegin];
/*     */   }
/*     */   
/*     */   public final int getBeginLine() {
/* 281 */     return this.bufline[this.tokenBegin];
/*     */   }
/*     */ 
/*     */   
/*     */   public final void backup(int amount) {
/* 286 */     this.inBuf += amount;
/* 287 */     if ((this.bufpos -= amount) < 0) {
/* 288 */       this.bufpos += this.bufsize;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBMUnicodeCharStream(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 294 */     this.inputStream = dstream;
/* 295 */     this.line = startline;
/* 296 */     this.column = startcolumn - 1;
/*     */     
/* 298 */     this.available = this.bufsize = buffersize;
/* 299 */     this.buffer = new char[buffersize];
/* 300 */     this.bufline = new int[buffersize];
/* 301 */     this.bufcolumn = new int[buffersize];
/* 302 */     this.nextCharBuf = new char[4096];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBMUnicodeCharStream(Reader dstream, int startline, int startcolumn) {
/* 308 */     this(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public void ReInit(Reader dstream, int startline, int startcolumn, int buffersize) {
/* 313 */     this.inputStream = dstream;
/* 314 */     this.line = startline;
/* 315 */     this.column = startcolumn - 1;
/*     */     
/* 317 */     if (this.buffer == null || buffersize != this.buffer.length) {
/*     */       
/* 319 */       this.available = this.bufsize = buffersize;
/* 320 */       this.buffer = new char[buffersize];
/* 321 */       this.bufline = new int[buffersize];
/* 322 */       this.bufcolumn = new int[buffersize];
/* 323 */       this.nextCharBuf = new char[4096];
/*     */     } 
/* 325 */     this.prevCharIsLF = this.prevCharIsCR = false;
/* 326 */     this.tokenBegin = this.inBuf = this.maxNextCharInd = 0;
/* 327 */     this.nextCharInd = this.bufpos = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ReInit(Reader dstream, int startline, int startcolumn) {
/* 333 */     ReInit(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBMUnicodeCharStream(InputStream dstream, int startline, int startcolumn, int buffersize) {
/* 338 */     this(new InputStreamReader(dstream), startline, startcolumn, 4096);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBMUnicodeCharStream(InputStream dstream, int startline, int startcolumn) {
/* 344 */     this(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ReInit(InputStream dstream, int startline, int startcolumn, int buffersize) {
/* 350 */     ReInit(new InputStreamReader(dstream), startline, startcolumn, 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public void ReInit(InputStream dstream, int startline, int startcolumn) {
/* 355 */     ReInit(dstream, startline, startcolumn, 4096);
/*     */   }
/*     */ 
/*     */   
/*     */   public final String GetImage() {
/* 360 */     if (this.bufpos >= this.tokenBegin) {
/* 361 */       return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
/*     */     }
/* 363 */     return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final char[] GetSuffix(int len) {
/* 369 */     char[] ret = new char[len];
/*     */     
/* 371 */     if (this.bufpos + 1 >= len) {
/* 372 */       System.arraycopy(this.buffer, this.bufpos - len + 1, ret, 0, len);
/*     */     } else {
/*     */       
/* 375 */       System.arraycopy(this.buffer, this.bufsize - len - this.bufpos - 1, ret, 0, len - this.bufpos - 1);
/*     */       
/* 377 */       System.arraycopy(this.buffer, 0, ret, len - this.bufpos - 1, this.bufpos + 1);
/*     */     } 
/*     */     
/* 380 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void Done() {
/* 385 */     this.nextCharBuf = null;
/* 386 */     this.buffer = null;
/* 387 */     this.bufline = null;
/* 388 */     this.bufcolumn = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adjustBeginLineColumn(int newLine, int newCol) {
/* 396 */     int len, start = this.tokenBegin;
/*     */ 
/*     */     
/* 399 */     if (this.bufpos >= this.tokenBegin) {
/*     */       
/* 401 */       len = this.bufpos - this.tokenBegin + this.inBuf + 1;
/*     */     }
/*     */     else {
/*     */       
/* 405 */       len = this.bufsize - this.tokenBegin + this.bufpos + 1 + this.inBuf;
/*     */     } 
/*     */     
/* 408 */     int i = 0, j = 0, k = 0;
/* 409 */     int nextColDiff = 0, columnDiff = 0;
/*     */     
/* 411 */     while (i < len && this.bufline[j = start % this.bufsize] == this.bufline[k = ++start % this.bufsize]) {
/*     */ 
/*     */       
/* 414 */       this.bufline[j] = newLine;
/* 415 */       nextColDiff = columnDiff + this.bufcolumn[k] - this.bufcolumn[j];
/* 416 */       this.bufcolumn[j] = newCol + columnDiff;
/* 417 */       columnDiff = nextColDiff;
/* 418 */       i++;
/*     */     } 
/*     */     
/* 421 */     if (i < len) {
/*     */       
/* 423 */       this.bufline[j] = newLine++;
/* 424 */       this.bufcolumn[j] = newCol + columnDiff;
/*     */       
/* 426 */       while (i++ < len) {
/*     */         
/* 428 */         if (this.bufline[j = start % this.bufsize] != this.bufline[++start % this.bufsize]) {
/* 429 */           this.bufline[j] = newLine++; continue;
/*     */         } 
/* 431 */         this.bufline[j] = newLine;
/*     */       } 
/*     */     } 
/*     */     
/* 435 */     this.line = this.bufline[j];
/* 436 */     this.column = this.bufcolumn[j];
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\IBMUnicodeCharStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */