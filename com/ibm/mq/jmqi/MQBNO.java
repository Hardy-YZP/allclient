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
/*     */ public class MQBNO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQBNO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_TYPE = 4;
/*     */   private static final int SIZEOF_TIMEOUT = 4;
/*     */   private static final int SIZEOF_BALANCE_OPTIONS = 4;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.jmqi.MQBNO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQBNO.java");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int sizeofNativePointer) {
/*  70 */     if (Trace.isOn)
/*  71 */       Trace.entry("com.ibm.mq.jmqi.MQBNO", "getSizeV1(int)", new Object[] {
/*  72 */             Integer.valueOf(sizeofNativePointer)
/*     */           }); 
/*  74 */     int size = 20;
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit("com.ibm.mq.jmqi.MQBNO", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/*  78 */     return size;
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
/*     */ 
/*     */         
/* 104 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2139, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 113 */     int size = getSize(this.env, this.version, ptrSize);
/* 114 */     return size;
/*     */   }
/*     */   
/* 117 */   private int version = 1;
/* 118 */   private int type = 0;
/* 119 */   private int timeout = -1;
/* 120 */   private int balanceOptions = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQBNO(JmqiEnvironment env) {
/* 128 */     super(env);
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.mq.jmqi.MQBNO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jmqi.MQBNO", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/* 148 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToTraceBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 159 */     return writeToBuffer(buffer, offset, true, ptrSize, swap, cp, tls);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 169 */     return writeToBuffer(buffer, offset, false, ptrSize, swap, cp, tls);
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
/*     */   public int writeToBuffer(byte[] buffer, int offset, boolean obscure, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.jmqi.MQBNO", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 190 */             Integer.valueOf(offset), Boolean.valueOf(obscure), Integer.valueOf(ptrSize), 
/* 191 */             Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 193 */     int pos = offset;
/*     */     
/* 195 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 197 */     dc.writeMQField("BNO ", buffer, pos, 4, cp, tls);
/* 198 */     pos += 4;
/*     */     
/* 200 */     dc.writeI32(this.version, buffer, pos, swap);
/* 201 */     pos += 4;
/*     */     
/* 203 */     dc.writeI32(this.type, buffer, pos, swap);
/* 204 */     pos += 4;
/*     */     
/* 206 */     dc.writeI32(this.timeout, buffer, pos, swap);
/* 207 */     pos += 4;
/*     */     
/* 209 */     dc.writeI32(this.balanceOptions, buffer, pos, swap);
/* 210 */     pos += 4;
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.jmqi.MQBNO", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*     */           
/* 215 */           Integer.valueOf(pos));
/*     */     }
/* 217 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.jmqi.MQBNO", "readFromBuffer(byte [ ],int, int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 229 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), 
/* 230 */             Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 232 */     int pos = offset;
/*     */     
/* 234 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 236 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 237 */     if (!strucId.equals("BNO ")) {
/*     */       
/* 239 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2139, null);
/*     */ 
/*     */       
/* 242 */       if (Trace.isOn) {
/* 243 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQBNO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 246 */       throw traceRet1;
/*     */     } 
/* 248 */     pos += 4;
/*     */ 
/*     */     
/* 251 */     this.version = dc.readI32(buffer, pos, swap);
/* 252 */     pos += 4;
/*     */ 
/*     */     
/* 255 */     this.type = dc.readI32(buffer, pos, swap);
/* 256 */     pos += 4;
/*     */ 
/*     */     
/* 259 */     this.timeout = dc.readI32(buffer, pos, swap);
/* 260 */     pos += 4;
/*     */ 
/*     */     
/* 263 */     this.balanceOptions = dc.readI32(buffer, pos, swap);
/* 264 */     pos += 4;
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit(this, "com.ibm.mq.jmqi.MQBNO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*     */           
/* 269 */           Integer.valueOf(pos));
/*     */     }
/* 271 */     return pos;
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
/* 283 */     fmt.add("version", this.version);
/* 284 */     fmt.add("type", decodeType(this.type));
/* 285 */     fmt.add("timeout", decodeTimeout(this.timeout));
/* 286 */     fmt.add("balanceOptions", decodeRebalanceOptions(this.balanceOptions));
/*     */   }
/*     */   
/*     */   private String decodeRebalanceOptions(int balanceOptions) {
/* 290 */     return MQConstants.decodeOptionsForTrace(balanceOptions, "MQBNO_OPTIONS.*|MQBNO_IGNORE.*");
/*     */   }
/*     */   
/*     */   private String decodeTimeout(int timeout) {
/* 294 */     return MQConstants.decodeOptionsForTrace(timeout, "MQBNO_TIMEOUT.*");
/*     */   }
/*     */   
/*     */   private String decodeType(int type) {
/* 298 */     return MQConstants.decodeOptionsForTrace(type, "MQBNO_TYPE.*");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimeout() {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "getTimeout()", "getter", decodeTimeout(this.timeout));
/*     */     }
/* 308 */     return this.timeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeout(int timeout) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "setTimeout(int)", "setter", decodeTimeout(timeout));
/*     */     }
/* 318 */     this.timeout = timeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "getType()", "getter", decodeType(this.type));
/*     */     }
/* 328 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "setType()", "setter", decodeType(type));
/*     */     }
/* 338 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBalanceOptions() {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "getBalanceOptions()", "getter", decodeRebalanceOptions(this.balanceOptions));
/*     */     }
/* 348 */     return this.balanceOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBalanceOptions(int balanceOptions) {
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.mq.jmqi.MQBNO", "setBalanceOptions()", "setter", decodeRebalanceOptions(balanceOptions));
/*     */     }
/* 358 */     this.balanceOptions = balanceOptions;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQBNO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */