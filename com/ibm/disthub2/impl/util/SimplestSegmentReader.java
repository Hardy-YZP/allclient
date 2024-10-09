/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimplestSegmentReader
/*     */   extends SegmentReader
/*     */   implements LogConstants, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  53 */   private static final DebugObject debug = new DebugObject("SimplestSegmentReader");
/*     */ 
/*     */   
/*     */   private static final int SEG_HDR_SIZE = 6;
/*     */   
/*     */   InputStream ins;
/*     */   
/*  60 */   byte[] segHdr = new byte[6];
/*     */   
/*     */   byte[] b;
/*     */   
/*     */   public SimplestSegmentReader(InputStream ins, int segpay) {
/*  65 */     if (debug.debugIt(32)) {
/*  66 */       debug.debug(-165922073994779L, "SimplestSegmentReader", ins, Integer.valueOf(segpay));
/*     */     }
/*     */     
/*  69 */     this.ins = ins; this.segpay = segpay;
/*     */ 
/*     */     
/*  72 */     if (debug.debugIt(64)) {
/*  73 */       debug.debug(-142394261359015L, "SimplestSegmentReader");
/*     */     }
/*     */   }
/*     */   
/*     */   public SimplestSegmentReader(InputStream ins) {
/*  78 */     this(ins, 100);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void prepGet(byte[] b) throws IOException {
/*  86 */     if (debug.debugIt(32)) {
/*  87 */       debug.debug(-165922073994779L, "prepGet", b);
/*     */     }
/*     */     
/*  90 */     this.b = b;
/*     */ 
/*     */     
/*  93 */     if (debug.debugIt(64)) {
/*  94 */       debug.debug(-142394261359015L, "prepGet");
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
/*     */   public byte[] get() throws IOException {
/* 108 */     if (debug.debugIt(32)) {
/* 109 */       debug.debug(-165922073994779L, "get");
/*     */     }
/*     */     
/*     */     try {
/* 113 */       readFully(this.segHdr, 0, 6);
/*     */     }
/* 115 */     catch (EOFException e) {
/*     */       
/* 117 */       if (debug.debugIt(64)) {
/* 118 */         debug.debug(-142394261359015L, "get", null);
/*     */       }
/* 120 */       return null;
/*     */     } 
/*     */     
/* 123 */     if (this.magic != 0) {
/* 124 */       short mgc = (short)(((0xFF & this.segHdr[0]) << 8) + (0xFF & this.segHdr[1]));
/* 125 */       if (mgc != this.magic) {
/* 126 */         throw new IOException(ExceptionBuilder.buildReasonString(817859573, new Object[] { Integer.toHexString(mgc), Integer.toHexString(this.magic) }));
/*     */       }
/*     */     } 
/* 129 */     int segsz = ((0xFF & this.segHdr[2]) << 24) + ((0xFF & this.segHdr[3]) << 16) + ((0xFF & this.segHdr[4]) << 8) + (0xFF & this.segHdr[5]);
/*     */     
/* 131 */     if (segsz > this.segmax || segsz < 0) {
/*     */       
/* 133 */       SegmentLengthException e = new SegmentLengthException(ExceptionBuilder.buildReasonString(178920668, new Object[] { Integer.valueOf(segsz), Integer.valueOf(this.segmax) }));
/* 134 */       e.setSegmentSize(segsz);
/* 135 */       e.setSegmentMaximum(this.segmax);
/* 136 */       throw e;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (this.b == null || this.b.length < segsz + 6)
/*     */     {
/* 145 */       this.b = new byte[segsz + 6];
/*     */     }
/*     */     
/* 148 */     System.arraycopy(this.segHdr, 0, this.b, 0, 6);
/*     */     
/*     */     try {
/* 151 */       readFully(this.b, 6, segsz);
/*     */     }
/* 153 */     catch (EOFException e) {
/* 154 */       throw new IOException(ExceptionBuilder.buildReasonString(769495761, new Object[] { e }));
/*     */     } 
/*     */ 
/*     */     
/* 158 */     if (debug.debugIt(64)) {
/* 159 */       debug.debug(-142394261359015L, "get", this.b);
/*     */     }
/*     */     
/* 162 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readFully(byte[] b, int off, int len) throws IOException {
/* 167 */     if (debug.debugIt(32)) {
/* 168 */       debug.debug(-165922073994779L, "readFully", b, Integer.valueOf(off), Integer.valueOf(len));
/*     */     }
/*     */ 
/*     */     
/* 172 */     while (len > 0) {
/* 173 */       int i = this.ins.read(b, off, len);
/* 174 */       if (i < 0)
/* 175 */         throw new EOFException(ExceptionBuilder.buildReasonString(957449877, null)); 
/* 176 */       off += i;
/* 177 */       len -= i;
/*     */     } 
/*     */ 
/*     */     
/* 181 */     if (debug.debugIt(64)) {
/* 182 */       debug.debug(-142394261359015L, "readFully");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 188 */     if (debug.debugIt(32)) {
/* 189 */       debug.debug(-165922073994779L, "close");
/*     */     }
/*     */     
/* 192 */     this.ins.close();
/* 193 */     this.b = null;
/*     */ 
/*     */     
/* 196 */     if (debug.debugIt(64))
/* 197 */       debug.debug(-142394261359015L, "close"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\SimplestSegmentReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */