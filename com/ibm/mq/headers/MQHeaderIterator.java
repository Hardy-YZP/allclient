/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQHeaderIterator
/*     */   extends JmqiObject
/*     */   implements Iterator
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderIterator.java";
/*     */   
/*     */   static {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data("com.ibm.mq.headers.MQHeaderIterator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderIterator.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   volatile MQHeaderFactory.Registry registry = MQHeaderRegistry.getDefault();
/*     */ 
/*     */ 
/*     */   
/*     */   private final MQHeaderContext context;
/*     */ 
/*     */ 
/*     */   
/*     */   private MQHeaderFactory factory;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQHeaderIterator(DataInput message) {
/* 119 */     super(MQCommonServices.jmqiEnv);
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/*     */     try {
/* 125 */       this.context = MQHeaderContext.createMQHeaderContext((DataInput)MessageWrapper.wrap(message));
/*     */     }
/* 127 */     catch (MQDataException e) {
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput)", e);
/*     */       }
/*     */       
/* 132 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.throwing(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput)", traceRet1);
/*     */       }
/*     */       
/* 137 */       throw traceRet1;
/*     */     } 
/*     */     
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput)");
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
/*     */   public MQHeaderIterator(DataInput message, String format, int encoding, int characterSet) {
/* 157 */     super(MQCommonServices.jmqiEnv);
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput,String,int,int)", new Object[] { message, format, 
/* 160 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 163 */       this.context = MQHeaderContext.createMQHeaderContext((DataInput)MessageWrapper.wrap(message), format, encoding, characterSet);
/*     */     }
/* 165 */     catch (MQDataException e) {
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput,String,int,int)", e);
/*     */       }
/*     */ 
/*     */       
/* 171 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.throwing(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput,String,int,int)", traceRet1);
/*     */       }
/*     */       
/* 176 */       throw traceRet1;
/*     */     } 
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "<init>(DataInput,String,int,int)");
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
/*     */   public synchronized boolean hasNext() {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "hasNext()");
/*     */     }
/*     */     try {
/* 196 */       String format = this.context.nextFormat();
/*     */       
/* 198 */       boolean traceRet1 = (format != null && this.registry.getFactoryForFormat(format) != null && this.context.available() > 0);
/*     */       
/* 200 */       if (Trace.isOn) {
/* 201 */         Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "hasNext()", 
/* 202 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 204 */       return traceRet1;
/*     */     
/*     */     }
/* 207 */     catch (IOException e) {
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderIterator", "hasNext()", e);
/*     */       }
/* 211 */       if (Trace.isOn) {
/* 212 */         Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "hasNext()", 
/* 213 */             Boolean.valueOf(false), 2);
/*     */       }
/* 215 */       return false;
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
/*     */   public Object next() {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "next()");
/*     */     }
/* 230 */     Object traceRet1 = nextHeader();
/*     */     
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "next()", traceRet1);
/*     */     }
/* 235 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized MQHeader nextHeader() {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "nextHeader()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 251 */       MQHeader next = null;
/* 252 */       String format = this.context.nextFormat();
/*     */       
/* 254 */       if (format != null) {
/* 255 */         this.factory = this.registry.getFactoryForFormat(format);
/*     */       }
/*     */       
/* 258 */       if (this.factory != null && (next = this.factory.decode(this.context)) != null && 
/* 259 */         next instanceof MQChainable) {
/* 260 */         MQChainable chainable = (MQChainable)next;
/*     */         
/* 262 */         this.context.setFormat(chainable.nextFormat());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 270 */         if (!"MQIMS   ".equals(chainable.format())) {
/* 271 */           this.context.setEncoding(chainable.nextEncoding());
/* 272 */           this.context.setCharacterSet(chainable.nextCharacterSet());
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 277 */       if (Trace.isOn) {
/* 278 */         Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "nextHeader()", next);
/*     */       }
/* 280 */       return next;
/*     */     
/*     */     }
/* 283 */     catch (EOFException e) {
/* 284 */       if (Trace.isOn) {
/* 285 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderIterator", "nextHeader()", e, 1);
/*     */       }
/* 287 */       NoSuchElementException traceRet1 = new NoSuchElementException();
/* 288 */       if (Trace.isOn) {
/* 289 */         Trace.throwing(this, "com.ibm.mq.headers.MQHeaderIterator", "nextHeader()", traceRet1, 1);
/*     */       }
/* 291 */       throw traceRet1;
/*     */     
/*     */     }
/* 294 */     catch (Exception e) {
/* 295 */       if (Trace.isOn) {
/* 296 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderIterator", "nextHeader()", e, 2);
/*     */       }
/* 298 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 299 */       if (Trace.isOn) {
/* 300 */         Trace.throwing(this, "com.ibm.mq.headers.MQHeaderIterator", "nextHeader()", traceRet2, 2);
/*     */       }
/* 302 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "remove()");
/*     */     }
/*     */ 
/*     */     
/* 316 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.throwing(this, "com.ibm.mq.headers.MQHeaderIterator", "remove()", traceRet1);
/*     */     }
/* 321 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataInput skipHeaders() throws MQDataException, IOException {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "skipHeaders()");
/*     */     }
/* 335 */     while (hasNext()) {
/* 336 */       next();
/*     */     }
/*     */     
/* 339 */     DataInput traceRet1 = this.context.getDataInput();
/*     */     
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "skipHeaders()", traceRet1);
/*     */     }
/* 344 */     return traceRet1;
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
/*     */   public Object getBody() throws MQDataException, IOException {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderIterator", "getBody()");
/*     */     }
/* 360 */     while (hasNext()) {
/* 361 */       next();
/*     */     }
/*     */     
/* 364 */     if ("MQSTR   ".equals(this.context.nextFormat())) {
/* 365 */       Object traceRet1 = getBodyAsText();
/*     */       
/* 367 */       if (Trace.isOn) {
/* 368 */         Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "getBody()", traceRet1, 1);
/*     */       }
/* 370 */       return traceRet1;
/*     */     } 
/* 372 */     Object traceRet2 = getBodyAsBytes();
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderIterator", "getBody()", traceRet2, 2);
/*     */     }
/* 376 */     return traceRet2;
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
/*     */   public byte[] getBodyAsBytes() throws MQDataException, IOException {
/* 389 */     while (hasNext()) {
/* 390 */       next();
/*     */     }
/*     */     
/* 393 */     byte[] bytes = new byte[this.context.available()];
/*     */     
/* 395 */     this.context.getDataInput().readFully(bytes);
/*     */     
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderIterator", "getBodyAsBytes()", "getter", bytes);
/*     */     }
/*     */     
/* 401 */     return bytes;
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
/*     */   public String getBodyAsText() throws MQDataException, IOException {
/*     */     String traceRet1;
/*     */     try {
/* 418 */       traceRet1 = CCSID.getJmqiCodepage(this.context.nextCharacterSet()).bytesToString(getBodyAsBytes());
/*     */     }
/* 420 */     catch (CharacterCodingException e) {
/* 421 */       if (Trace.isOn) {
/* 422 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderIterator", "getBodyAsText()", e);
/*     */       }
/* 424 */       UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.toString());
/* 425 */       if (Trace.isOn) {
/* 426 */         Trace.throwing(this, "com.ibm.mq.headers.MQHeaderIterator", "getBodyAsText()", traceRet2);
/*     */       }
/* 428 */       throw traceRet2;
/*     */     } 
/* 430 */     if (Trace.isOn) {
/* 431 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderIterator", "getBodyAsText()", "getter", traceRet1);
/*     */     }
/*     */     
/* 434 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQHeaderIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */