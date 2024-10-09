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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LexObjectSelector
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LexObjectSelector.java";
/*     */   private static final String lexOS_STRUC_ID = "LOS ";
/*     */   public static final int lexOS_VERSION_1 = 1;
/*     */   public static final int lexOS_VERSION_2 = 2;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OBJECT_TYPE = 4;
/*     */   private static final int SIZEOF_OBJECT_NAME = 48;
/*     */   public static final int lexOS_QUEUE = 1;
/*     */   public static final int lexOS_PROCESS = 2;
/*     */   public static final int lexOS_QMGR = 4;
/*     */   public static final int lexOS_STGCLASS = 8;
/*     */   public static final int lexOS_ADMIN = 16;
/*     */   public static final int lexOS_CATALOGUE = 32;
/*     */   public static final int lexOS_OBJECTL = 64;
/*     */   public static final int lexOS_INTERNAL_ADMIN = 128;
/*     */   public static final int lexOS_INTERNAL_CATALOGUE = 256;
/*     */   public static final int lexOS_SCRATCHPAD = 512;
/*     */   public static final int lexOS_NAMELIST = 1024;
/*     */   public static final int lexOS_AUTHINFO = 2048;
/*     */   public static final int lexOS_CHANNEL = 4096;
/*     */   public static final int lexOS_CLIENTCHANNEL = 8192;
/*     */   public static final int lexOS_LISTENER = 16384;
/*     */   public static final int lexOS_SERVICE = 32768;
/*     */   public static final int lexOS_TOPIC = 36864;
/*     */   public static final int lexOS_COMMINFO = 40960;
/*     */   public static final int lexOS_CHLAUTH = 45056;
/*     */   public static final int lexOS_SUBSCRIPTION = 47104;
/*     */   public static final int lexOS_QLOCAL = 65537;
/*     */   public static final int lexOS_QREMOTE = 131073;
/*     */   public static final int lexOS_QALIAS = 262145;
/*     */   public static final int lexOS_QMODEL = 524289;
/*     */   private int version;
/*     */   private int objectType;
/*     */   private String objectName;
/*     */   private LexContext userContext;
/*     */   private LexCommandContext commandContext;
/*     */   
/*     */   public LexObjectSelector(JmqiEnvironment env) {
/* 148 */     super(env);
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 153 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/* 154 */     this.userContext = sysenv.newLexContext();
/* 155 */     this.commandContext = sysenv.newLexCommandContext();
/*     */     
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 167 */     if (Trace.isOn)
/* 168 */       Trace.entry("com.ibm.mq.jmqi.system.LexObjectSelector", "getFieldSizeV1(int)", new Object[] {
/* 169 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 171 */     int size = 0;
/* 172 */     size += 4;
/* 173 */     size += 4;
/* 174 */     size += 4;
/* 175 */     size += 48;
/* 176 */     size += LexContext.getFieldSizeV1(ptrSize);
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit("com.ibm.mq.jmqi.system.LexObjectSelector", "getFieldSizeV1(int)", 
/* 180 */           Integer.valueOf(size));
/*     */     }
/* 182 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 190 */     if (Trace.isOn)
/* 191 */       Trace.entry("com.ibm.mq.jmqi.system.LexObjectSelector", "getSizeV1(int)", new Object[] {
/* 192 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 194 */     int size = getFieldSizeV1(ptrSize);
/*     */ 
/*     */     
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit("com.ibm.mq.jmqi.system.LexObjectSelector", "getSizeV1(int)", 
/* 199 */           Integer.valueOf(size));
/*     */     }
/* 201 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize) {
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.entry("com.ibm.mq.jmqi.system.LexObjectSelector", "getFieldSizeV2(int)", new Object[] {
/* 210 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 212 */     int size = getFieldSizeV1(ptrSize);
/* 213 */     size += LexCommandContext.getFieldSizeV1(ptrSize);
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit("com.ibm.mq.jmqi.system.LexObjectSelector", "getFieldSizeV2(int)", 
/* 217 */           Integer.valueOf(size));
/*     */     }
/* 219 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 227 */     if (Trace.isOn)
/* 228 */       Trace.entry("com.ibm.mq.jmqi.system.LexObjectSelector", "getSizeV2(int)", new Object[] {
/* 229 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 231 */     int size = getFieldSizeV2(ptrSize);
/*     */ 
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit("com.ibm.mq.jmqi.system.LexObjectSelector", "getSizeV2(int)", 
/* 236 */           Integer.valueOf(size));
/*     */     }
/* 238 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/*     */     JmqiException traceRet1;
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry("com.ibm.mq.jmqi.system.LexObjectSelector", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 251 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/*     */     
/* 254 */     switch (version) {
/*     */       case 1:
/* 256 */         size = getSizeV1(ptrSize);
/*     */         break;
/*     */       case 2:
/* 259 */         size = getSizeV2(ptrSize);
/*     */         break;
/*     */       default:
/* 262 */         traceRet1 = new JmqiException(env, -1, null, 2, 2195, null);
/*     */ 
/*     */         
/* 265 */         if (Trace.isOn) {
/* 266 */           Trace.throwing("com.ibm.mq.jmqi.system.LexObjectSelector", "getSize(JmqiEnvironment,int,int)", (Throwable)traceRet1);
/*     */         }
/*     */         
/* 269 */         throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit("com.ibm.mq.jmqi.system.LexObjectSelector", "getSize(JmqiEnvironment,int,int)", 
/* 275 */           Integer.valueOf(size));
/*     */     }
/* 277 */     return size;
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
/* 288 */     if (Trace.isOn)
/* 289 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 290 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 292 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/*     */     
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 296 */           Integer.valueOf(traceRet1));
/*     */     }
/* 298 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getVersion()", "getter", 
/* 308 */           Integer.valueOf(this.version));
/*     */     }
/* 310 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "setVersion(int)", "setter", 
/* 320 */           Integer.valueOf(version));
/*     */     }
/* 322 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getObjectType() {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getObjectType()", "getter", 
/* 331 */           Integer.valueOf(this.objectType));
/*     */     }
/* 333 */     return this.objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectType(int objectType) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "setObjectType(int)", "setter", 
/* 342 */           Integer.valueOf(objectType));
/*     */     }
/* 344 */     this.objectType = objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getObjectName()", "getter", this.objectName);
/*     */     }
/*     */     
/* 355 */     return this.objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "setObjectName(String)", "setter", objectName);
/*     */     }
/*     */     
/* 366 */     this.objectName = objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexContext getUserContext() {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getUserContext()", "getter", this.userContext);
/*     */     }
/*     */     
/* 377 */     return this.userContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexCommandContext getCommandContext() {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "getCommandContext()", "getter", this.commandContext);
/*     */     }
/*     */     
/* 388 */     return this.commandContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 400 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 402 */     int pos = offset;
/* 403 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 405 */     dc.writeMQField("LOS ", buffer, pos, 4, cp, tls);
/* 406 */     pos += 4;
/*     */     
/* 408 */     dc.writeI32(this.version, buffer, pos, swap);
/* 409 */     pos += 4;
/*     */     
/* 411 */     dc.writeI32(this.objectType, buffer, pos, swap);
/* 412 */     pos += 4;
/*     */     
/* 414 */     dc.writeField(this.objectName, buffer, pos, 48, cp, tls);
/* 415 */     pos += 48;
/*     */     
/* 417 */     pos += this.userContext.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */ 
/*     */     
/* 420 */     if (this.version >= 2)
/*     */     {
/* 422 */       pos += this.commandContext.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 429 */           Integer.valueOf(pos));
/*     */     }
/* 431 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 443 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 445 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 446 */     int pos = offset;
/*     */     
/* 448 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 449 */     if (!strucId.equals("LOS ")) {
/* 450 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */ 
/*     */       
/* 453 */       if (Trace.isOn) {
/* 454 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 457 */       throw traceRet1;
/*     */     } 
/* 459 */     pos += 4;
/*     */ 
/*     */     
/* 462 */     this.version = dc.readI32(buffer, pos, swap);
/* 463 */     pos += 4;
/*     */ 
/*     */     
/* 466 */     this.objectType = dc.readI32(buffer, pos, swap);
/* 467 */     pos += 4;
/*     */ 
/*     */     
/* 470 */     this.objectName = dc.readField(buffer, pos, 48, cp, tls);
/* 471 */     pos += 48;
/*     */ 
/*     */     
/* 474 */     pos += this.userContext.readFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (this.version >= 2)
/*     */     {
/* 480 */       pos += this.commandContext.readFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 485 */     if (Trace.isOn) {
/* 486 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexObjectSelector", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 487 */           Integer.valueOf(pos));
/*     */     }
/* 489 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 500 */     fmt.add("objectType", this.objectType);
/* 501 */     fmt.add("objectName", this.objectName);
/* 502 */     fmt.add("userContext", this.userContext);
/* 503 */     fmt.add("commandContext", this.commandContext);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LexObjectSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */