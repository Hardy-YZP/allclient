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
/*     */ public class SpiSyncPointOptions
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/SpiSyncPointOptions.java";
/*     */   private static final String lpiSPO_STRUC_ID = "LSO ";
/*     */   private static final int lpiSPO_VERSION_1 = 1;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_ACTIONS = 4;
/*  66 */   public static int lpiSYNCPT_NONE = 0;
/*     */   
/*  68 */   public static int lpiSYNCPT_START = 1;
/*     */   
/*  70 */   public static int lpiSYNCPT_END = 2;
/*     */   
/*  72 */   public static int lpiSYNCPT_PREPARE = 3;
/*     */   
/*  74 */   public static int lpiSYNCPT_COMMIT = 4;
/*     */   
/*  76 */   public static int lpiSYNCPT_ROLLBACK = 5;
/*     */   
/*  78 */   public static int lpiSYNCPT_ASYNC_COMMIT = 6;
/*     */   
/*  80 */   private int version = 1;
/*  81 */   private int options = 0;
/*  82 */   private int actions = 0;
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiSyncPointOptions(JmqiEnvironment env) {
/*  90 */     super(env);
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "<init>(JmqiEnvironment)");
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
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "getVersion()", "getter", 
/* 109 */           Integer.valueOf(this.version));
/*     */     }
/* 111 */     return this.version;
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
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "setVersion(int)", "setter", 
/* 124 */           Integer.valueOf(version));
/*     */     }
/* 126 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "getOptions()", "getter", 
/* 136 */           Integer.valueOf(this.options));
/*     */     }
/* 138 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "setOptions(int)", "setter", 
/* 150 */           Integer.valueOf(options));
/*     */     }
/* 152 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActions() {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "getActions()", "getter", 
/* 162 */           Integer.valueOf(this.actions));
/*     */     }
/* 164 */     return this.actions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActions(int actions) {
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "setActions(int)", "setter", 
/* 174 */           Integer.valueOf(actions));
/*     */     }
/* 176 */     this.actions = actions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 189 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 191 */     int pos = offset;
/* 192 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 194 */     dc.writeMQField("LSO ", buffer, pos, 4, cp, tls);
/* 195 */     pos += 4;
/*     */     
/* 197 */     dc.writeI32(this.version, buffer, pos, swap);
/* 198 */     pos += 4;
/*     */     
/* 200 */     dc.writeI32(this.options, buffer, pos, swap);
/* 201 */     pos += 4;
/*     */     
/* 203 */     dc.writeI32(this.actions, buffer, pos, swap);
/* 204 */     pos += 4;
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 208 */           Integer.valueOf(pos));
/*     */     }
/* 210 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 222 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 224 */     int pos = offset;
/* 225 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 227 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 228 */     if (!strucId.equals("LSO ")) {
/* 229 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */       
/* 231 */       if (Trace.isOn) {
/* 232 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 235 */       throw traceRet1;
/*     */     } 
/* 237 */     pos += 4;
/*     */ 
/*     */     
/* 240 */     pos += 4;
/*     */ 
/*     */     
/* 243 */     this.options = dc.readI32(buffer, pos, swap);
/* 244 */     pos += 4;
/*     */ 
/*     */     
/* 247 */     this.actions = dc.readI32(buffer, pos, swap);
/* 248 */     pos += 4;
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 252 */           Integer.valueOf(pos));
/*     */     }
/* 254 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 263 */     int size = 16;
/* 264 */     return size;
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
/* 276 */     int size = 0;
/* 277 */     switch (version) {
/*     */       case 1:
/* 279 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 286 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 297 */     if (Trace.isOn)
/* 298 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 299 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 301 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiSyncPointOptions", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 305 */           Integer.valueOf(size));
/*     */     }
/* 307 */     return size;
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
/* 319 */     fmt.add("version", this.version);
/* 320 */     fmt.add("options", this.options);
/* 321 */     String optionDescription = formatOptions(this.options, getFields(), "lpiSYNCPT_");
/* 322 */     fmt.add("option flags", optionDescription);
/* 323 */     fmt.add("actions", this.actions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 333 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 336 */     if (ref == null || (fields = ref.get()) == null) {
/* 337 */       fieldsRef = (Reference)new SoftReference<>(fields = SpiSyncPointOptions.class.getFields());
/*     */     }
/*     */     
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data("com.ibm.mq.jmqi.system.SpiSyncPointOptions", "getFields()", "getter", fields);
/*     */     }
/* 343 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\SpiSyncPointOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */