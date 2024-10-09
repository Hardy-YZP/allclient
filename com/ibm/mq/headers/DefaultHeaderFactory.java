/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.HeaderMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultHeaderFactory
/*     */   implements MQHeaderFactory
/*     */ {
/*     */   final String format;
/*     */   final String type;
/*     */   final Class<?> headerClass;
/*     */   
/*     */   static {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.data("com.ibm.mq.headers.DefaultHeaderFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderRegistry.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHeaderFactory(String format, String type, String className) throws ClassNotFoundException {
/* 348 */     this(format, type, Class.forName(className));
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.entry(this, "com.ibm.mq.headers.DefaultHeaderFactory", "<init>(String,String,String)", new Object[] { format, type, className });
/*     */     }
/*     */     
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.exit(this, "com.ibm.mq.headers.DefaultHeaderFactory", "<init>(String,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultHeaderFactory(String format, String type, Class<?> headerClass) {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.entry(this, "com.ibm.mq.headers.DefaultHeaderFactory", "<init>(String,String,Class<?>)", new Object[] { format, type, headerClass });
/*     */     }
/*     */     
/* 364 */     if (!MQHeader.class.isAssignableFrom(headerClass)) {
/*     */       
/* 366 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0022", new Object[] { headerClass.getName(), MQHeader.class.getName() }));
/* 367 */       if (Trace.isOn) {
/* 368 */         Trace.throwing(this, "com.ibm.mq.headers.DefaultHeaderFactory", "<init>(String,String,Class<?>)", traceRet1);
/*     */       }
/*     */       
/* 371 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 375 */     this.format = format;
/* 376 */     this.type = type;
/* 377 */     this.headerClass = headerClass;
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.exit(this, "com.ibm.mq.headers.DefaultHeaderFactory", "<init>(String,String,Class<?>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQHeader create(String type) {
/* 388 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public MQHeader decode(MQHeaderContext context) throws MQDataException, IOException {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.entry(this, "com.ibm.mq.headers.null", "decode(MQHeaderContext)", new Object[] { context });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 399 */       String format = context.nextFormat();
/*     */       
/* 401 */       if (format != null && format.equals(this.format)) {
/* 402 */         MQHeader header = (MQHeader)this.headerClass.newInstance();
/*     */         
/* 404 */         header.read(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/*     */         
/* 406 */         if (Trace.isOn) {
/* 407 */           Trace.exit(this, "com.ibm.mq.headers.null", "decode(MQHeaderContext)", header, 1);
/*     */         }
/* 409 */         return header;
/*     */       } 
/* 411 */       if (Trace.isOn) {
/* 412 */         Trace.exit(this, "com.ibm.mq.headers.null", "decode(MQHeaderContext)", null, 2);
/*     */       }
/* 414 */       return null;
/*     */     
/*     */     }
/* 417 */     catch (Exception e) {
/* 418 */       if (Trace.isOn) {
/* 419 */         Trace.catchBlock(this, "com.ibm.mq.headers.null", "decode(MQHeaderContext)", e);
/*     */       }
/* 421 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 422 */       if (Trace.isOn) {
/* 423 */         Trace.throwing(this, "com.ibm.mq.headers.null", "decode(MQHeaderContext)", traceRet1);
/*     */       }
/* 425 */       throw traceRet1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<?> getSupportedFormats() {
/* 431 */     if (Trace.isOn) {
/* 432 */       Trace.entry(this, "com.ibm.mq.headers.null", "getSupportedFormats()");
/*     */     }
/* 434 */     Collection<?> traceRet1 = Arrays.asList((Object[])new String[] { this.format });
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.exit(this, "com.ibm.mq.headers.null", "getSupportedFormats()", traceRet1);
/*     */     }
/* 438 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<?> getSupportedTypes() {
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.entry(this, "com.ibm.mq.headers.null", "getSupportedTypes()");
/*     */     }
/* 446 */     Collection<?> traceRet1 = Arrays.asList((Object[])new String[] { this.type });
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.exit(this, "com.ibm.mq.headers.null", "getSupportedTypes()", traceRet1);
/*     */     }
/* 450 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.entry(this, "com.ibm.mq.headers.null", "toString()");
/*     */     }
/*     */ 
/*     */     
/* 460 */     String traceRet1 = getClass().getName() + "[Format " + this.format + " --> " + this.type + " (" + this.headerClass.getName() + ")]";
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.exit(this, "com.ibm.mq.headers.null", "toString()", traceRet1);
/*     */     }
/* 464 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\DefaultHeaderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */