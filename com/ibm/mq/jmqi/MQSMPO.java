/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
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
/*     */ public class MQSMPO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSMPO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_VALUE_ENCODING = 4;
/*     */   private static final int SIZEOF_VALUE_CCSID = 4;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jmqi.MQSMPO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSMPO.java");
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
/*  65 */   private int version = 1;
/*  66 */   private int options = 0;
/*  67 */   private int valueEncoding = 273;
/*  68 */   private int valueCCSID = -3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int sizeofNativePointer) {
/*  78 */     int sizeOfStructureV1 = 20;
/*  79 */     return sizeOfStructureV1;
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
/*  92 */     int size = 0;
/*  93 */     switch (version) {
/*     */       case 1:
/*  95 */         size = getSizeV1(sizeofNativePointer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 102 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2463, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQSMPO(JmqiEnvironment env) {
/* 111 */     super(env);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSMPO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSMPO", "<init>(JmqiEnvironment)");
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
/*     */   public int getVersion() {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "getVersion()", "getter", 
/* 130 */           Integer.valueOf(this.version));
/*     */     }
/* 132 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "setVersion(int)", "setter", 
/* 144 */           Integer.valueOf(version));
/*     */     }
/* 146 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "getOptions()", "getter", 
/* 157 */           Integer.valueOf(this.options));
/*     */     }
/* 159 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "setOptions(int)", "setter", 
/* 170 */           Integer.valueOf(options));
/*     */     }
/* 172 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValueEncoding() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "getValueEncoding()", "getter", 
/* 183 */           Integer.valueOf(this.valueEncoding));
/*     */     }
/* 185 */     return this.valueEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueEncoding(int valueEncoding) {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "setValueEncoding(int)", "setter", 
/* 196 */           Integer.valueOf(valueEncoding));
/*     */     }
/* 198 */     this.valueEncoding = valueEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValueCCSID() {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "getValueCCSID()", "getter", 
/* 209 */           Integer.valueOf(this.valueCCSID));
/*     */     }
/* 211 */     return this.valueCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueCCSID(int valueCCSID) {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.mq.jmqi.MQSMPO", "setValueCCSID(int)", "setter", 
/* 222 */           Integer.valueOf(valueCCSID));
/*     */     }
/* 224 */     this.valueCCSID = valueCCSID;
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
/* 235 */     int size = getSize(this.env, this.version, ptrSize);
/* 236 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSMPO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 249 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 251 */     int pos = offset;
/* 252 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 254 */     dc.writeMQField("SMPO", buffer, pos, 4, cp, tls);
/* 255 */     pos += 4;
/*     */     
/* 257 */     dc.writeI32(this.version, buffer, pos, swap);
/* 258 */     pos += 4;
/*     */     
/* 260 */     dc.writeI32(this.options, buffer, pos, swap);
/* 261 */     pos += 4;
/*     */     
/* 263 */     dc.writeI32(this.valueEncoding, buffer, pos, swap);
/* 264 */     pos += 4;
/*     */     
/* 266 */     dc.writeI32(this.valueCCSID, buffer, pos, swap);
/* 267 */     pos += 4;
/*     */     
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSMPO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 271 */           Integer.valueOf(pos));
/*     */     }
/* 273 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSMPO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 287 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 289 */     int pos = offset;
/* 290 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 292 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 293 */     if (!strucId.equals("SMPO")) {
/*     */       
/* 295 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 2463, null);
/*     */ 
/*     */       
/* 298 */       if (Trace.isOn) {
/* 299 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQSMPO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", ex);
/*     */       }
/*     */       
/* 302 */       throw ex;
/*     */     } 
/* 304 */     pos += 4;
/*     */ 
/*     */     
/* 307 */     pos += 16;
/*     */     
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSMPO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 311 */           Integer.valueOf(pos));
/*     */     }
/* 313 */     return pos;
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
/* 325 */     fmt.add("Version", this.version);
/* 326 */     fmt.add("Options", this.options);
/* 327 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQSMPO_.*");
/* 328 */     fmt.add("option flags", optionDescription);
/* 329 */     fmt.add("ValueEncoding", this.valueEncoding);
/* 330 */     fmt.add("ValueCCSID", this.valueCCSID);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQSMPO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */