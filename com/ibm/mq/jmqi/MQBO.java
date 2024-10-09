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
/*     */ 
/*     */ public class MQBO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQBO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jmqi.MQBO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQBO.java");
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
/*  62 */   private int version = 1;
/*     */   
/*  64 */   private int options = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int sizeofNativePointer) {
/*  74 */     int sizeOfStructureV1 = 12;
/*  75 */     return sizeOfStructureV1;
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
/*  88 */     int size = 0;
/*  89 */     switch (version) {
/*     */       case 1:
/*  91 */         size = getSizeV1(sizeofNativePointer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  98 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2385, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQBO(JmqiEnvironment env) {
/* 107 */     super(env);
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.jmqi.MQBO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.jmqi.MQBO", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.jmqi.MQBO", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/* 125 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.MQBO", "setOptions(int)", "setter", 
/* 137 */           Integer.valueOf(options));
/*     */     }
/* 139 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.MQBO", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/* 151 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.data(this, "com.ibm.mq.jmqi.MQBO", "setVersion(int)", "setter", 
/* 164 */           Integer.valueOf(version));
/*     */     }
/* 166 */     this.version = version;
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
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.jmqi.MQBO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 190 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 192 */     int pos = offset;
/* 193 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 195 */     dc.writeMQField("BO  ", buffer, pos, 4, cp, tls);
/* 196 */     pos += 4;
/*     */     
/* 198 */     dc.writeI32(this.version, buffer, pos, swap);
/* 199 */     pos += 4;
/*     */     
/* 201 */     dc.writeI32(this.options, buffer, pos, swap);
/* 202 */     pos += 4;
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.jmqi.MQBO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 206 */           Integer.valueOf(pos));
/*     */     }
/* 208 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.jmqi.MQBO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 220 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 222 */     int pos = offset;
/* 223 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 225 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 226 */     if (!strucId.equals("BO  ")) {
/*     */       
/* 228 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2134, null);
/*     */       
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQBO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 234 */       throw traceRet1;
/*     */     } 
/* 236 */     pos += 4;
/*     */ 
/*     */     
/* 239 */     pos += 8;
/*     */     
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit(this, "com.ibm.mq.jmqi.MQBO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 243 */           Integer.valueOf(pos));
/*     */     }
/* 245 */     return pos;
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
/* 257 */     fmt.add("version", this.version);
/* 258 */     fmt.add("options", this.options);
/* 259 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQBO_.*");
/* 260 */     fmt.add("option flags", optionDescription);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQBO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */