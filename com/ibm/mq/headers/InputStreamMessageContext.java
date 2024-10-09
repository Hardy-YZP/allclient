/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class InputStreamMessageContext
/*     */   extends MQHeaderContext
/*     */ {
/*     */   private final InputStream stream;
/*     */   private static final int SIZEOF_INT = 4;
/*     */   
/*     */   static {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data("com.ibm.mq.headers.InputStreamMessageContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderContext.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   InputStreamMessageContext(InputStream stream, String format, int encoding, int characterSet) {
/* 329 */     super(format, encoding, characterSet);
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.mq.headers.InputStreamMessageContext", "<init>(InputStream,String,int,int)", new Object[] { stream, format, 
/*     */             
/* 333 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/* 335 */     if (stream.markSupported()) {
/* 336 */       this.stream = stream;
/*     */     } else {
/* 338 */       this.stream = new DataInputStream(new BufferedInputStream(stream));
/*     */     } 
/*     */     
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.exit(this, "com.ibm.mq.headers.InputStreamMessageContext", "<init>(InputStream,String,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataInput getDataInput() {
/* 350 */     DataInput traceRet1 = (DataInput)this.stream;
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.data(this, "com.ibm.mq.headers.InputStreamMessageContext", "getDataInput()", "getter", traceRet1);
/*     */     }
/*     */     
/* 355 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.entry(this, "com.ibm.mq.headers.InputStreamMessageContext", "available()");
/*     */     }
/* 363 */     int traceRet1 = this.stream.available();
/*     */     
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.exit(this, "com.ibm.mq.headers.InputStreamMessageContext", "available()", 
/* 367 */           Integer.valueOf(traceRet1));
/*     */     }
/* 369 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mark() throws IOException {
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.entry(this, "com.ibm.mq.headers.InputStreamMessageContext", "mark()");
/*     */     }
/* 379 */     this.stream.mark(512);
/*     */     
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.exit(this, "com.ibm.mq.headers.InputStreamMessageContext", "mark()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void rewind() throws IOException {
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.entry(this, "com.ibm.mq.headers.InputStreamMessageContext", "rewind()");
/*     */     }
/* 391 */     this.stream.reset();
/*     */     
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.exit(this, "com.ibm.mq.headers.InputStreamMessageContext", "rewind()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int sniff() throws IOException {
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.entry(this, "com.ibm.mq.headers.InputStreamMessageContext", "sniff()");
/*     */     }
/* 406 */     this.stream.mark(4);
/*     */     
/* 408 */     int value = ((DataInput)this.stream).readInt();
/*     */     
/* 410 */     this.stream.reset();
/*     */     
/* 412 */     int traceRet1 = Store.isReversed(nextEncoding()) ? Store.reverse(value) : value;
/*     */     
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.exit(this, "com.ibm.mq.headers.InputStreamMessageContext", "sniff()", 
/* 416 */           Integer.valueOf(traceRet1));
/*     */     }
/* 418 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\InputStreamMessageContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */