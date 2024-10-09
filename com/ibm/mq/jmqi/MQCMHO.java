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
/*     */ public class MQCMHO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCMHO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jmqi.MQCMHO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCMHO.java");
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
/*  61 */   private int version = 1;
/*  62 */   private int options = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int sizeofNativePointer) {
/*  72 */     int sizeOfStructureV1 = 12;
/*  73 */     return sizeOfStructureV1;
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
/*  86 */     int size = 0;
/*  87 */     switch (version) {
/*     */       case 1:
/*  89 */         size = getSizeV1(sizeofNativePointer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  96 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2461, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCMHO(JmqiEnvironment env) {
/* 105 */     super(env);
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCMHO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCMHO", "<init>(JmqiEnvironment)");
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
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.jmqi.MQCMHO", "getVersion()", "getter", 
/* 124 */           Integer.valueOf(this.version));
/*     */     }
/* 126 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.data(this, "com.ibm.mq.jmqi.MQCMHO", "setVersion(int)", "setter", 
/* 138 */           Integer.valueOf(version));
/*     */     }
/* 140 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.data(this, "com.ibm.mq.jmqi.MQCMHO", "getOptions()", "getter", 
/* 151 */           Integer.valueOf(this.options));
/*     */     }
/* 153 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.data(this, "com.ibm.mq.jmqi.MQCMHO", "setOptions(int)", "setter", 
/* 164 */           Integer.valueOf(options));
/*     */     }
/* 166 */     this.options = options;
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
/* 177 */     int size = getSize(this.env, this.version, ptrSize);
/* 178 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCMHO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 191 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 193 */     int pos = offset;
/* 194 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 196 */     dc.writeMQField("CMHO", buffer, pos, 4, cp, tls);
/* 197 */     pos += 4;
/*     */     
/* 199 */     dc.writeI32(this.version, buffer, pos, swap);
/* 200 */     pos += 4;
/*     */     
/* 202 */     dc.writeI32(this.options, buffer, pos, swap);
/* 203 */     pos += 4;
/*     */     
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCMHO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 207 */           Integer.valueOf(pos));
/*     */     }
/* 209 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCMHO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 222 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 224 */     int pos = offset;
/* 225 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 227 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 228 */     if (!strucId.equals("CMHO")) {
/*     */       
/* 230 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 2461, null);
/*     */ 
/*     */       
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCMHO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", ex);
/*     */       }
/*     */       
/* 237 */       throw ex;
/*     */     } 
/* 239 */     pos += 4;
/*     */ 
/*     */     
/* 242 */     pos += 8;
/*     */     
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCMHO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 246 */           Integer.valueOf(pos));
/*     */     }
/* 248 */     return pos;
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
/* 260 */     fmt.add("Version", this.version);
/* 261 */     fmt.add("Options", this.options);
/* 262 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQCMHO_.*");
/* 263 */     fmt.add("option flags", optionDescription);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCMHO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */