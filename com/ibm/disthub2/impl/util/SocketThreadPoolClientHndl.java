/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SocketThreadPoolClientHndl
/*     */   implements ReaderReadyI, WriterReadyI, LogConstants, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2020 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  78 */   private static final DebugObject debug = new DebugObject("SocketThreadPoolClientHndl");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean inCulledBlock = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public long activeStamp = -1L;
/*     */ 
/*     */   
/*     */   protected volatile Socket sock;
/*     */ 
/*     */   
/*     */   public volatile SocketThreadPoolClient stpc;
/*     */ 
/*     */   
/*     */   protected DebugObject dbg;
/*     */ 
/*     */   
/*     */   protected volatile boolean die;
/*     */ 
/*     */   
/*     */   protected volatile boolean deadRead;
/*     */ 
/*     */   
/*     */   protected volatile boolean deadWrite;
/*     */ 
/*     */   
/*     */   protected volatile int inReadIO;
/*     */ 
/*     */   
/*     */   protected volatile int inWriteIO;
/*     */   
/*     */   protected volatile Thread readThread;
/*     */   
/*     */   protected volatile Thread writeThread;
/*     */ 
/*     */   
/*     */   public abstract void readyToRead() throws SocketThreadPoolException;
/*     */ 
/*     */   
/*     */   public abstract void readyToWrite() throws SocketThreadPoolException;
/*     */ 
/*     */   
/*     */   public void enterCulledBlock() {
/* 140 */     if (debug.debugIt(32)) {
/* 141 */       debug.debug(-165922073994779L, "enterCulledBlock");
/*     */     }
/*     */ 
/*     */     
/* 145 */     this.inCulledBlock = true;
/* 146 */     this.activeStamp = -1L;
/*     */ 
/*     */     
/* 149 */     if (debug.debugIt(64)) {
/* 150 */       debug.debug(-142394261359015L, "enterCulledBlock");
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
/*     */   public void exitCulledBlock() {
/* 162 */     if (debug.debugIt(32)) {
/* 163 */       debug.debug(-165922073994779L, "exitCulledBlock");
/*     */     }
/*     */ 
/*     */     
/* 167 */     this.inCulledBlock = false;
/*     */ 
/*     */     
/* 170 */     if (debug.debugIt(64)) {
/* 171 */       debug.debug(-142394261359015L, "exitCulledBlock");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketThreadPoolClientHndl(Socket s, SocketThreadPoolClient c, DebugObject d) {
/* 198 */     this.die = false;
/*     */     
/* 200 */     this.deadRead = false;
/*     */     
/* 202 */     this.deadWrite = false;
/*     */     
/* 204 */     this.inReadIO = 0;
/*     */     
/* 206 */     this.inWriteIO = 0;
/*     */     
/* 208 */     this.readThread = null;
/*     */     
/* 210 */     this.writeThread = null;
/*     */     if (debug.debugIt(32))
/*     */       debug.debug(-165922073994779L, "SocketThreadPoolClientHndl", s, c, d); 
/*     */     this.sock = s;
/*     */     this.stpc = c;
/*     */     this.dbg = d;
/*     */     if (debug.debugIt(64))
/* 217 */       debug.debug(-142394261359015L, "SocketThreadPoolClientHndl");  } public synchronized void beginRead() throws IOException { if (debug.debugIt(32)) {
/* 218 */       debug.debug(-165922073994779L, "beingRead");
/*     */     }
/*     */ 
/*     */     
/* 222 */     if (this.die) {
/* 223 */       throw new IOException(ExceptionBuilder.buildReasonString(-2077573032, null));
/*     */     }
/* 225 */     this.inReadIO++;
/*     */ 
/*     */     
/* 228 */     if (debug.debugIt(64)) {
/* 229 */       debug.debug(-142394261359015L, "beginRead");
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void endRead() {
/* 236 */     if (debug.debugIt(32)) {
/* 237 */       debug.debug(-165922073994779L, "endRead");
/*     */     }
/*     */ 
/*     */     
/* 241 */     this.inReadIO--;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     if (debug.debugIt(64)) {
/* 250 */       debug.debug(-142394261359015L, "endRead");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void beginWrite() throws IOException {
/* 257 */     if (debug.debugIt(32)) {
/* 258 */       debug.debug(-165922073994779L, "endRead");
/*     */     }
/*     */ 
/*     */     
/* 262 */     if (this.die) {
/* 263 */       throw new IOException(ExceptionBuilder.buildReasonString(-2077573032, null));
/*     */     }
/* 265 */     this.inWriteIO++;
/*     */ 
/*     */     
/* 268 */     if (debug.debugIt(64)) {
/* 269 */       debug.debug(-142394261359015L, "beginWrite");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void endWrite() {
/* 276 */     if (debug.debugIt(32)) {
/* 277 */       debug.debug(-165922073994779L, "endWrite");
/*     */     }
/*     */ 
/*     */     
/* 281 */     this.inWriteIO--;
/*     */ 
/*     */     
/* 284 */     if (debug.debugIt(64)) {
/* 285 */       debug.debug(-142394261359015L, "endWrite");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void beginRead(Thread v) throws IOException {
/* 295 */     if (debug.debugIt(32)) {
/* 296 */       debug.debug(-165922073994779L, "beginRead", v);
/*     */     }
/*     */ 
/*     */     
/* 300 */     if (this.die) {
/* 301 */       throw new IOException(ExceptionBuilder.buildReasonString(-2077573032, null));
/*     */     }
/* 303 */     if (this.readThread != null) {
/* 304 */       if (this.readThread != v) {
/* 305 */         throw new Error(ExceptionBuilder.buildReasonString(711738836, new Object[] { v, this.readThread, this.sock }));
/*     */       }
/*     */     } else {
/*     */       
/* 309 */       this.readThread = v;
/*     */     } 
/* 311 */     this.inReadIO++;
/*     */ 
/*     */     
/* 314 */     if (debug.debugIt(64)) {
/* 315 */       debug.debug(-142394261359015L, "beginRead");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void endRead(Thread v) {
/* 322 */     if (debug.debugIt(32)) {
/* 323 */       debug.debug(-165922073994779L, "endRead", v);
/*     */     }
/*     */ 
/*     */     
/* 327 */     if (this.readThread != v) {
/* 328 */       throw new Error(ExceptionBuilder.buildReasonString(711738836, new Object[] { v, this.readThread, this.sock }));
/*     */     }
/* 330 */     if (--this.inReadIO == 0) {
/* 331 */       this.readThread = null;
/* 332 */       if (this.die) {
/* 333 */         this.deadRead = true;
/* 334 */         notifyAll();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 339 */     if (debug.debugIt(64)) {
/* 340 */       debug.debug(-142394261359015L, "endRead");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void beginWrite(Thread v) throws IOException {
/* 347 */     if (debug.debugIt(32)) {
/* 348 */       debug.debug(-165922073994779L, "beginWrite", v);
/*     */     }
/*     */ 
/*     */     
/* 352 */     if (this.die) {
/* 353 */       throw new IOException(ExceptionBuilder.buildReasonString(-2077573032, null));
/*     */     }
/* 355 */     if (this.writeThread != null) {
/* 356 */       if (this.writeThread != v) {
/* 357 */         throw new Error(ExceptionBuilder.buildReasonString(711738836, new Object[] { v, this.writeThread, this.sock }));
/*     */       }
/*     */     } else {
/*     */       
/* 361 */       this.writeThread = v;
/*     */     } 
/* 363 */     this.inWriteIO++;
/*     */ 
/*     */     
/* 366 */     if (debug.debugIt(64)) {
/* 367 */       debug.debug(-142394261359015L, "beginWrite");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void endWrite(Thread v) {
/* 374 */     if (debug.debugIt(32)) {
/* 375 */       debug.debug(-165922073994779L, "endWrite", v);
/*     */     }
/*     */ 
/*     */     
/* 379 */     if (this.writeThread != v) {
/* 380 */       throw new Error(ExceptionBuilder.buildReasonString(711738836, new Object[] { v, this.writeThread, this.sock }));
/*     */     }
/* 382 */     if (--this.inWriteIO == 0) {
/* 383 */       this.writeThread = null;
/* 384 */       if (this.die) {
/* 385 */         this.deadWrite = true;
/* 386 */         notifyAll();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 391 */     if (debug.debugIt(64)) {
/* 392 */       debug.debug(-142394261359015L, "endWrite");
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
/*     */   public synchronized void deregisterClient() throws SocketThreadPoolException {
/* 409 */     if (debug.debugIt(32)) {
/* 410 */       debug.debug(-165922073994779L, "deregisterClient");
/*     */     }
/*     */ 
/*     */     
/* 414 */     if (this.die) {
/* 415 */       throw new SocketThreadPoolException(ExceptionBuilder.buildReasonString(-2112060087, null));
/*     */     }
/* 417 */     this.die = true;
/*     */     
/* 419 */     boolean readKiller = false, writeKiller = false;
/*     */     
/* 421 */     if (!this.deadRead && this.readThread != null && Thread.currentThread() != this.readThread)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 427 */       readKiller = true;
/*     */     }
/* 429 */     if (!this.deadWrite && this.writeThread != null && Thread.currentThread() != this.writeThread)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 435 */       writeKiller = true;
/*     */     }
/*     */ 
/*     */     
/* 439 */     if (this.dbg.debugIt(8)) {
/* 440 */       this.dbg.debug(-174425605715460L, "readKiller=", 
/* 441 */           String.valueOf(readKiller), "writeKiller=", 
/* 442 */           String.valueOf(writeKiller));
/*     */     }
/*     */ 
/*     */     
/* 446 */     if (readKiller || writeKiller) {
/* 447 */       long wt = 60000L;
/* 448 */       long deadlinetime = System.currentTimeMillis() + wt;
/*     */ 
/*     */ 
/*     */       
/* 452 */       while ((readKiller && !this.deadRead) || (writeKiller && !this.deadWrite)) {
/* 453 */         if ((wt = deadlinetime - System.currentTimeMillis()) > 0L) {
/*     */           try {
/* 455 */             wait(wt);
/*     */           }
/* 457 */           catch (Exception exception) {}
/*     */           continue;
/*     */         } 
/* 460 */         SocketThreadPoolException e = new SocketThreadPoolException(((readKiller && !this.deadRead) ? "doRead() did not die. " : "") + ((writeKiller && !this.deadWrite) ? "doWrite() did not die. " : ""));
/*     */ 
/*     */ 
/*     */         
/* 464 */         if (this.dbg.debugIt(2)) {
/* 465 */           this.dbg.debug(-175584356845707L, e);
/*     */         }
/*     */         
/* 468 */         throw e;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 474 */     if (debug.debugIt(64))
/* 475 */       debug.debug(-142394261359015L, "deregisterClient"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\SocketThreadPoolClientHndl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */