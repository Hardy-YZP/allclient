/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQPD
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQPD.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_CONTEXT = 4;
/*     */   private static final int SIZEOF_COPYOPTIONS = 4;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jmqi.MQPD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQPD.java");
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
/*  69 */   private int version = 1;
/*  70 */   private int options = 0;
/*  71 */   private int support = 1;
/*  72 */   private int context = 0;
/*  73 */   private int copyOptions = 22;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int sizeofNativePointer) {
/*  83 */     int sizeOfStructureV1 = 24;
/*     */     
/*  85 */     return sizeOfStructureV1;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/*  98 */     int size = 0;
/*  99 */     switch (version) {
/*     */       case 1:
/* 101 */         size = getSizeV1(sizeofNativePointer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 108 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2482, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQPD(JmqiEnvironment env) {
/* 117 */     super(env);
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPD", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPD", "<init>(JmqiEnvironment)");
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
/*     */   public void setVersion(int value) {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "setVersion(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 138 */     this.version = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int value) {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "setOptions(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 151 */     this.options = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupport(int value) {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "setSupport(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 164 */     this.support = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContext(int value) {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "setContext(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 177 */     this.context = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCopyOptions(int value) {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "setCopyOptions(int)", "setter", 
/* 188 */           Integer.valueOf(value));
/*     */     }
/* 190 */     this.copyOptions = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 204 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/*     */     
/* 217 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSupport() {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "getSupport()", "getter", Integer.valueOf(this.support));
/*     */     }
/*     */     
/* 230 */     return this.support;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContext() {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "getContext()", "getter", Integer.valueOf(this.context));
/*     */     }
/*     */     
/* 243 */     return this.context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCopyOptions() {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.jmqi.MQPD", "getCopyOptions()", "getter", 
/* 254 */           Integer.valueOf(this.copyOptions));
/*     */     }
/* 256 */     return this.copyOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 267 */     int size = getSize(this.env, this.version, ptrSize);
/* 268 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 281 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 283 */     int pos = offset;
/* 284 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 286 */     dc.writeMQField("PD  ", buffer, pos, 4, cp, tls);
/* 287 */     pos += 4;
/*     */     
/* 289 */     dc.writeI32(this.version, buffer, pos, swap);
/* 290 */     pos += 4;
/*     */     
/* 292 */     dc.writeI32(this.options, buffer, pos, swap);
/* 293 */     pos += 4;
/*     */     
/* 295 */     dc.writeI32(this.support, buffer, pos, swap);
/* 296 */     pos += 4;
/*     */     
/* 298 */     dc.writeI32(this.context, buffer, pos, swap);
/* 299 */     pos += 4;
/*     */     
/* 301 */     dc.writeI32(this.copyOptions, buffer, pos, swap);
/* 302 */     pos += 4;
/*     */     
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 306 */           Integer.valueOf(pos));
/*     */     }
/* 308 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 321 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 323 */     int pos = offset;
/* 324 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 326 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 327 */     if (!strucId.equals("PD  ")) {
/*     */       
/* 329 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 2482, null);
/*     */ 
/*     */       
/* 332 */       if (Trace.isOn) {
/* 333 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQPD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", ex);
/*     */       }
/*     */       
/* 336 */       throw ex;
/*     */     } 
/* 338 */     pos += 4;
/*     */ 
/*     */     
/* 341 */     pos += 8;
/*     */ 
/*     */     
/* 344 */     this.support = dc.readI32(buffer, pos, swap);
/* 345 */     pos += 4;
/*     */ 
/*     */     
/* 348 */     this.context = dc.readI32(buffer, pos, swap);
/* 349 */     pos += 4;
/*     */ 
/*     */     
/* 352 */     this.copyOptions = dc.readI32(buffer, pos, swap);
/* 353 */     pos += 4;
/*     */     
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 357 */           Integer.valueOf(pos));
/*     */     }
/* 359 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 371 */     fmt.add("Version", this.version);
/* 372 */     fmt.add("Options", this.options);
/* 373 */     fmt.add("Support", this.support);
/* 374 */     fmt.add("Context", this.context);
/* 375 */     fmt.add("CopyOptions", this.copyOptions);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQPD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */