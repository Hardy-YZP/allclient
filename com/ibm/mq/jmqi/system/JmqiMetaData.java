/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiMetaData
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/JmqiMetaData.java";
/*     */   private static final String STRUC_ID = "JMD ";
/*     */   public static final int VERSION_1 = 1;
/*     */   public static final int VERSION_2 = 2;
/*     */   public static final int CURRENT_VERSION = 2;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_FLAGS = 4;
/*     */   private static final int SIZEOF_CMDLEVEL = 4;
/*     */   private static final int SIZEOF_CMVCLEVEL = 48;
/*     */   private static final int SIZEOF_INSTALLFLAGS = 4;
/*     */   public static final int ISUPPORT_SPICONNECT = 1;
/*     */   public static final int ISUPPORT_SPIPUT = 2;
/*     */   public static final int ISUPPORT_SPIGET = 4;
/*     */   public static final int ISUPPORT_SPISYNCPOINT = 8;
/*     */   public static final int ISUPPORT_SPIACTIVATEMESSAGE = 16;
/*     */   public static final int ISUPPORT_SPISUBSCRIBE = 32;
/*     */   public static final int ISUPPORT_SPIUNSUBSCRIBE = 64;
/*     */   public static final int ISUPPORT_SPINOTIFY = 128;
/*     */   public static final int ISUPPORT_MESSAGE_HANDLE = 256;
/*     */   public static final int xcsINSTDETAILS_FLAGS_NONE = 0;
/*     */   public static final int xcsINSTDETAILS_FLAGS_OS_UNREG = 1;
/*     */   public static final int xcsINSTDETAILS_FLAGS_LOCAL = 2;
/*     */   public static final int xcsINSTDETAILS_FLAGS_SINGLE_PROCESS = 4;
/*     */   public static final int xcsINSTDETAILS_FLAGS_STANDALONE = 8;
/*     */   public static final int xcsINSTDETAILS_FLAGS_INVALID = 32;
/*     */   public static final int xcsINSTDETAILS_FLAGS_32KEY = 64;
/* 108 */   private int version = 2;
/*     */   
/*     */   private int flags;
/*     */   
/*     */   private int cmdLevel;
/*     */   
/*     */   private String cmvcLevel;
/*     */   private int installFlags;
/*     */   
/*     */   public JmqiMetaData(JmqiEnvironment env) {
/* 118 */     super(env);
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getFlags()", "getter", 
/* 135 */           Integer.valueOf(this.flags));
/*     */     }
/* 137 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "setFlags(int)", "setter", 
/* 146 */           Integer.valueOf(flags));
/*     */     }
/* 148 */     this.flags = flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getVersion()", "getter", 
/* 158 */           Integer.valueOf(this.version));
/*     */     }
/* 160 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "setVersion(int)", "setter", 
/* 170 */           Integer.valueOf(version));
/*     */     }
/* 172 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCmdLevel() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getCmdLevel()", "getter", 
/* 181 */           Integer.valueOf(this.cmdLevel));
/*     */     }
/* 183 */     return this.cmdLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCmdLevel(int cmdLevel) {
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "setCmdLevel(int)", "setter", 
/* 192 */           Integer.valueOf(cmdLevel));
/*     */     }
/* 194 */     this.cmdLevel = cmdLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCmvcLevel() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getCmvcLevel()", "getter", this.cmvcLevel);
/*     */     }
/*     */     
/* 205 */     return this.cmvcLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCmvcLevel(String cmvcLevel) {
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "setCmvcLevel(String)", "setter", cmvcLevel);
/*     */     }
/*     */     
/* 216 */     this.cmvcLevel = cmvcLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInstallFlags() {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getInstallFlags()", "getter", 
/* 225 */           Integer.valueOf(this.installFlags));
/*     */     }
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getInstallFlags()", "getter", 
/* 229 */           Integer.valueOf(this.installFlags));
/*     */     }
/* 231 */     return this.installFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstallFlags(int installFlags) {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "setInstallFlags(int)", "setter", 
/* 240 */           Integer.valueOf(installFlags));
/*     */     }
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "setInstallFlags(int)", "setter", 
/* 244 */           Integer.valueOf(installFlags));
/*     */     }
/* 246 */     this.installFlags = installFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 254 */     if (Trace.isOn)
/* 255 */       Trace.entry("com.ibm.mq.jmqi.system.JmqiMetaData", "getSizeV1(int)", new Object[] {
/* 256 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 258 */     int size = 64;
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit("com.ibm.mq.jmqi.system.JmqiMetaData", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 262 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 270 */     if (Trace.isOn)
/* 271 */       Trace.entry("com.ibm.mq.jmqi.system.JmqiMetaData", "getSizeV2(int)", new Object[] {
/* 272 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 274 */     if (Trace.isOn)
/* 275 */       Trace.entry("com.ibm.mq.jmqi.system.JmqiMetaData", "getSizeV2(int)", new Object[] {
/* 276 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 278 */     int size = getSizeV1(ptrSize) + 4;
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit("com.ibm.mq.jmqi.system.JmqiMetaData", "getSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit("com.ibm.mq.jmqi.system.JmqiMetaData", "getSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 285 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(JmqiEnvironment env, int ptrSize) throws JmqiException {
/*     */     int size;
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getSize(JmqiEnvironment,int)", new Object[] { env, 
/* 297 */             Integer.valueOf(ptrSize) });
/*     */     }
/*     */ 
/*     */     
/* 301 */     if (this.version == 2) {
/* 302 */       size = getSizeV2(ptrSize);
/*     */     } else {
/* 304 */       size = getSizeV1(ptrSize);
/*     */     } 
/*     */     
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "getSize(JmqiEnvironment,int)", 
/* 309 */           Integer.valueOf(size));
/*     */     }
/* 311 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 320 */     return getSize(this.env, ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 330 */     int pos = offset;
/* 331 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */ 
/*     */     
/* 334 */     dc.writeMQField("JMD ", buffer, pos, 4, cp, tls);
/* 335 */     pos += 4;
/*     */ 
/*     */     
/* 338 */     dc.writeI32(this.version, buffer, pos, swap);
/* 339 */     pos += 4;
/*     */ 
/*     */     
/* 342 */     dc.writeI32(this.flags, buffer, pos, swap);
/* 343 */     pos += 4;
/*     */ 
/*     */     
/* 346 */     dc.writeI32(this.cmdLevel, buffer, pos, swap);
/* 347 */     pos += 4;
/*     */ 
/*     */     
/* 350 */     dc.writeMQField(this.cmvcLevel, buffer, pos, 48, cp, tls);
/* 351 */     pos += 48;
/*     */     
/* 353 */     if (this.version >= 2) {
/*     */ 
/*     */       
/* 356 */       dc.writeI32(this.installFlags, buffer, pos, swap);
/* 357 */       pos += 4;
/*     */     } 
/*     */     
/* 360 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 370 */     int pos = offset;
/* 371 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */ 
/*     */     
/* 374 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 375 */     if (!strucId.equals("JMD ")) {
/* 376 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */       
/* 378 */       if (Trace.isOn) {
/* 379 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.JmqiMetaData", "readFromBuffer(byte[],int,int,boolean,JmqiCodepage,JmqiTls )", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 382 */       throw traceRet1;
/*     */     } 
/* 384 */     pos += 4;
/*     */ 
/*     */     
/* 387 */     this.version = dc.readI32(buffer, pos, swap);
/* 388 */     pos += 4;
/*     */ 
/*     */     
/* 391 */     this.flags = dc.readI32(buffer, pos, swap);
/* 392 */     pos += 4;
/*     */ 
/*     */     
/* 395 */     this.cmdLevel = dc.readI32(buffer, pos, swap);
/* 396 */     pos += 4;
/*     */ 
/*     */     
/* 399 */     this.cmvcLevel = dc.readMQField(buffer, pos, 48, cp, tls);
/* 400 */     this.cmvcLevel = this.cmvcLevel.trim();
/* 401 */     pos += 48;
/*     */ 
/*     */     
/* 404 */     if (this.version >= 2) {
/*     */ 
/*     */       
/* 407 */       this.installFlags = dc.readI32(buffer, pos, swap);
/* 408 */       pos += 4;
/*     */     }
/*     */     else {
/*     */       
/* 412 */       this.installFlags = 0;
/*     */     } 
/*     */     
/* 415 */     return pos;
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
/* 427 */     fmt.add("version", this.version);
/* 428 */     fmt.add("flags", this.flags);
/* 429 */     fmt.add("cmdLevel", this.cmdLevel);
/* 430 */     fmt.add("cmvcLevel", this.cmvcLevel);
/* 431 */     fmt.add("installFlags", this.installFlags);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */