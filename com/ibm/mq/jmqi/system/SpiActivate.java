/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpiActivate
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/SpiActivate.java";
/*     */   private static final String lpiACTIVATE_STRUC_ID = "LACT";
/*     */   private static final int lpiACTIVATE_VERSION = 1;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_Q_NAME = 48;
/*     */   private static final int SIZEOF_QMGR_NAME = 48;
/*     */   private static final int SIZEOF_MSG_ID = 24;
/*     */   public static final int lpiACTIVATE_NONE = 0;
/*     */   public static final int lpiACTIVATE_ACTIVATE = 1;
/*     */   public static final int lpiACTIVATE_CANCEL = 2;
/*  76 */   private int version = 1;
/*  77 */   private int options = 0;
/*  78 */   private String qName = null;
/*  79 */   private String qMgrName = null;
/*  80 */   private byte[] msgId = new byte[24];
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiActivate(JmqiEnvironment env) {
/*  88 */     super(env);
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiActivate", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiActivate", "<init>(JmqiEnvironment)");
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
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "getVersion()", "getter", 
/* 107 */           Integer.valueOf(this.version));
/*     */     }
/* 109 */     return this.version;
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
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "setVersion(int)", "setter", 
/* 122 */           Integer.valueOf(version));
/*     */     }
/* 124 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "getOptions()", "getter", 
/* 134 */           Integer.valueOf(this.options));
/*     */     }
/* 136 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "setOptions(int)", "setter", 
/* 148 */           Integer.valueOf(options));
/*     */     }
/* 150 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getMsgId() {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "getMsgId()", "getter", this.msgId);
/*     */     }
/* 161 */     return this.msgId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgId(byte[] msgId) {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "setMsgId(byte [ ])", "setter", msgId);
/*     */     }
/*     */     
/* 173 */     this.msgId = msgId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMgrName() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "getQMgrName()", "getter", this.qMgrName);
/*     */     }
/* 184 */     return this.qMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQMgrName(String mgrName) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "setQMgrName(String)", "setter", mgrName);
/*     */     }
/*     */     
/* 196 */     this.qMgrName = mgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQName() {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "getQName()", "getter", this.qName);
/*     */     }
/* 207 */     return this.qName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQName(String queueName) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiActivate", "setQName(String)", "setter", queueName);
/*     */     }
/*     */     
/* 219 */     this.qName = queueName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 229 */     int size = 132;
/*     */     
/* 231 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/* 243 */     int size = 0;
/* 244 */     switch (version) {
/*     */       case 1:
/* 246 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 253 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(JmqiEnvironment env, int ptrSize) throws JmqiException {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiActivate", "getSize(JmqiEnvironment,int)", new Object[] { env, 
/* 265 */             Integer.valueOf(ptrSize) });
/*     */     }
/* 267 */     int traceRet1 = getSizeV1(ptrSize);
/*     */     
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiActivate", "getSize(JmqiEnvironment,int)", 
/* 271 */           Integer.valueOf(traceRet1));
/*     */     }
/* 273 */     return traceRet1;
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
/* 284 */     if (Trace.isOn)
/* 285 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiActivate", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 286 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 288 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiActivate", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 292 */           Integer.valueOf(size));
/*     */     }
/* 294 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiActivate", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 306 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 308 */     int pos = offset;
/* 309 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 311 */     dc.writeMQField("LACT", buffer, pos, 4, cp, tls);
/* 312 */     pos += 4;
/*     */     
/* 314 */     dc.writeI32(this.version, buffer, pos, swap);
/* 315 */     pos += 4;
/*     */     
/* 317 */     dc.writeI32(this.options, buffer, pos, swap);
/* 318 */     pos += 4;
/*     */     
/* 320 */     dc.writeMQField(this.qName, buffer, pos, 48, cp, tls);
/* 321 */     pos += 48;
/*     */     
/* 323 */     dc.writeMQField(this.qMgrName, buffer, pos, 48, cp, tls);
/* 324 */     pos += 48;
/*     */     
/* 326 */     System.arraycopy(this.msgId, 0, buffer, pos, 24);
/* 327 */     pos += 24;
/*     */     
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiActivate", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 331 */           Integer.valueOf(pos));
/*     */     }
/* 333 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiActivate", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 345 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 347 */     int pos = offset;
/* 348 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 350 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 351 */     if (!strucId.equals("LACT")) {
/* 352 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */ 
/*     */       
/* 355 */       if (Trace.isOn) {
/* 356 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.SpiActivate", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 359 */       throw traceRet1;
/*     */     } 
/* 361 */     pos += 4;
/*     */ 
/*     */     
/* 364 */     this.version = dc.readI32(buffer, pos, swap);
/* 365 */     pos += 4;
/*     */ 
/*     */     
/* 368 */     this.options = dc.readI32(buffer, pos, swap);
/* 369 */     pos += 4;
/*     */ 
/*     */     
/* 372 */     this.qName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 373 */     pos += 48;
/*     */ 
/*     */     
/* 376 */     this.qMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 377 */     pos += 48;
/*     */ 
/*     */     
/* 380 */     System.arraycopy(buffer, pos, this.msgId, 0, 24);
/* 381 */     pos += 24;
/*     */     
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiActivate", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 385 */           Integer.valueOf(pos));
/*     */     }
/* 387 */     return pos;
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
/* 399 */     fmt.add("version", this.version);
/* 400 */     fmt.add("options", this.options);
/* 401 */     String optionDescription = formatOptions(this.options, getFields(), "lpiACTIVATE_");
/* 402 */     fmt.add("option flags", optionDescription);
/* 403 */     fmt.add("qName", this.qName);
/* 404 */     fmt.add("qMgrName", this.qMgrName);
/* 405 */     fmt.add("msgId", this.msgId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 415 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 418 */     if (ref == null || (fields = ref.get()) == null) {
/* 419 */       fieldsRef = (Reference)new SoftReference<>(fields = SpiActivate.class.getFields());
/*     */     }
/*     */     
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.data("com.ibm.mq.jmqi.system.SpiActivate", "getFields()", "getter", fields);
/*     */     }
/* 425 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\SpiActivate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */