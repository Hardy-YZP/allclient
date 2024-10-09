/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpMQOPEN_PRIV
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQOPEN_PRIV.java";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQOPEN_PRIV.java");
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
/*     */   public RfpMQOPEN_PRIV(JmqiEnvironment env, byte[] buffer, int offset) {
/*  73 */     super(env, buffer, offset);
/*     */   }
/*     */   
/*  76 */   private static final byte[] rfpMQOPEN_PRIV_STRUC_ID = new byte[] { 70, 79, 80, 65 };
/*     */   
/*     */   public static final int rfpMQOPEN_PRIV_VERSION_1 = 1;
/*     */   
/*     */   public static final int rfpMQOPEN_PRIV_VERSION_2 = 2;
/*     */   
/*     */   private static final int VERSION_OFFSET = 4;
/*     */   
/*     */   private static final int LENGTH_OFFSET = 8;
/*     */   
/*     */   private static final int DEF_PERSISTENCE_OFFSET = 12;
/*     */   
/*     */   private static final int DEF_PUT_RESPONSE_OFFSET = 16;
/*     */   
/*     */   private static final int DEF_READ_AHEAD_OFFSET = 20;
/*     */   
/*     */   private static final int PROPERTY_CONTROL_OFFSET = 24;
/*     */   
/*     */   private static final int POLICY_ERROR_QUEUE_OFFSET = 28;
/*     */   
/*     */   private static final int POLICY_DATA_OFFSET_OFFSET = 76;
/*     */   
/*     */   private static final int POLICY_DATA_LENGTH_OFFSET = 80;
/*     */   public static final int SIZE_CURRENT = 28;
/*     */   public static final int SIZE_V1 = 28;
/*     */   public static final int SIZE_V2 = 84;
/*     */   
/*     */   public void initEyecatcher() {
/* 104 */     System.arraycopy(rfpMQOPEN_PRIV_STRUC_ID, 0, this.buffer, this.offset, rfpMQOPEN_PRIV_STRUC_ID.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version, boolean swap) {
/* 111 */     if (Trace.isOn)
/* 112 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setVersion(int,boolean)", new Object[] {
/* 113 */             Integer.valueOf(version), Boolean.valueOf(swap)
/*     */           }); 
/* 115 */     this.dc.writeI32(version, this.buffer, this.offset + 4, swap);
/*     */     
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(int length, boolean swap) {
/* 127 */     if (Trace.isOn)
/* 128 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setLength(int,boolean)", new Object[] {
/* 129 */             Integer.valueOf(length), Boolean.valueOf(swap)
/*     */           }); 
/* 131 */     this.dc.writeI32(length, this.buffer, this.offset + 8, swap);
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefPersistence(int defPersistence, boolean swap) {
/* 143 */     if (Trace.isOn)
/* 144 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setDefPersistence(int,boolean)", new Object[] {
/* 145 */             Integer.valueOf(defPersistence), 
/* 146 */             Boolean.valueOf(swap)
/*     */           }); 
/* 148 */     this.dc.writeI32(defPersistence, this.buffer, this.offset + 12, swap);
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setDefPersistence(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefPutResponseType(int defPutResponseType, boolean swap) {
/* 161 */     if (Trace.isOn)
/* 162 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setDefPutResponseType(int,boolean)", new Object[] {
/* 163 */             Integer.valueOf(defPutResponseType), 
/* 164 */             Boolean.valueOf(swap)
/*     */           }); 
/* 166 */     this.dc.writeI32(defPutResponseType, this.buffer, this.offset + 16, swap);
/*     */     
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setDefPutResponseType(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefReadAhead(int defReadAhead, boolean swap) {
/* 179 */     if (Trace.isOn)
/* 180 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setDefReadAhead(int,boolean)", new Object[] {
/* 181 */             Integer.valueOf(defReadAhead), 
/* 182 */             Boolean.valueOf(swap)
/*     */           }); 
/* 184 */     this.dc.writeI32(defReadAhead, this.buffer, this.offset + 20, swap);
/*     */     
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setDefReadAhead(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropertyControl(int propertyControl, boolean swap) {
/* 197 */     if (Trace.isOn)
/* 198 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPropertyControl(int,boolean)", new Object[] {
/* 199 */             Integer.valueOf(propertyControl), 
/* 200 */             Boolean.valueOf(swap)
/*     */           }); 
/* 202 */     this.dc.writeI32(propertyControl, this.buffer, this.offset + 24, swap);
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPropertyControl(int,boolean)");
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
/*     */   public void setPolicyErrorQueue(String policyErrorQueue, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPolicyErrorQueue(String,JmqiCodepage,JmqiTls)", new Object[] { policyErrorQueue, cp, tls });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 224 */     this.dc.writeMQField(policyErrorQueue, this.buffer, this.offset + 28, 48, cp, tls);
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPolicyErrorQueue(String,JmqiCodepage,JmqiTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPolicyDataOffset(int policyDataOffset, boolean swap) {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPolicyDataOffset(int,boolean)", new Object[] {
/* 240 */             Integer.valueOf(policyDataOffset), 
/* 241 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 244 */     this.dc.writeI32(policyDataOffset, this.buffer, this.offset + 76, swap);
/*     */     
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPolicyDataOffset(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPolicyDataLength(int policyDataLength, boolean swap) {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPolicyDataLength(int,boolean)", new Object[] {
/* 260 */             Integer.valueOf(policyDataLength), 
/* 261 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 264 */     this.dc.writeI32(policyDataLength, this.buffer, this.offset + 80, swap);
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "setPolicyDataLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion(boolean swap) {
/* 276 */     if (Trace.isOn)
/* 277 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getVersion(boolean)", new Object[] {
/* 278 */             Boolean.valueOf(swap)
/*     */           }); 
/* 280 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getVersion(boolean)", 
/* 284 */           Integer.valueOf(traceRet1));
/*     */     }
/* 286 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 292 */     if (Trace.isOn)
/* 293 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getLength(boolean)", new Object[] {
/* 294 */             Boolean.valueOf(swap)
/*     */           }); 
/* 296 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getLength(boolean)", 
/* 300 */           Integer.valueOf(traceRet1));
/*     */     }
/* 302 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefPersistence(boolean swap) {
/* 308 */     if (Trace.isOn)
/* 309 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getDefPersistence(boolean)", new Object[] {
/* 310 */             Boolean.valueOf(swap)
/*     */           }); 
/* 312 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */     
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getDefPersistence(boolean)", 
/* 316 */           Integer.valueOf(traceRet1));
/*     */     }
/* 318 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefPutResponseType(boolean swap) {
/* 324 */     if (Trace.isOn)
/* 325 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getDefPutResponseType(boolean)", new Object[] {
/* 326 */             Boolean.valueOf(swap)
/*     */           }); 
/* 328 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*     */     
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getDefPutResponseType(boolean)", 
/* 332 */           Integer.valueOf(traceRet1));
/*     */     }
/* 334 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefReadAhead(boolean swap) {
/* 340 */     if (Trace.isOn)
/* 341 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getDefReadAhead(boolean)", new Object[] {
/* 342 */             Boolean.valueOf(swap)
/*     */           }); 
/* 344 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 20, swap);
/*     */     
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getDefReadAhead(boolean)", 
/* 348 */           Integer.valueOf(traceRet1));
/*     */     }
/* 350 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyControl(boolean swap) {
/* 356 */     if (Trace.isOn)
/* 357 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPropertyControl(boolean)", new Object[] {
/* 358 */             Boolean.valueOf(swap)
/*     */           }); 
/* 360 */     int propertyControl = this.dc.readI32(this.buffer, this.offset + 24, swap);
/*     */     
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPropertyControl(boolean)", 
/* 364 */           Integer.valueOf(propertyControl));
/*     */     }
/* 366 */     return propertyControl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPolicyErrorQueue(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPolicyErrorQueue(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */ 
/*     */     
/* 381 */     String policyErrorQueue = this.dc.readMQField(this.buffer, this.offset + 28, 48, cp, tls);
/*     */ 
/*     */     
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPolicyErrorQueue(JmqiCodepage,JmqiTls)", policyErrorQueue);
/*     */     }
/*     */     
/* 388 */     return policyErrorQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPolicyDataOffset(boolean swap) {
/* 394 */     if (Trace.isOn)
/* 395 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPolicyDataOffset(boolean)", new Object[] {
/* 396 */             Boolean.valueOf(swap)
/*     */           }); 
/* 398 */     int policyDataOffset = this.dc.readI32(this.buffer, this.offset + 76, swap);
/*     */     
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPolicyDataOffset(boolean)", 
/* 402 */           Integer.valueOf(policyDataOffset));
/*     */     }
/* 404 */     return policyDataOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPolicyDataLength(boolean swap) {
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPolicyDataLength(boolean)", new Object[] {
/* 412 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 415 */     int policyDataLength = this.dc.readI32(this.buffer, this.offset + 80, swap);
/*     */     
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV", "getPolicyDataLength(boolean)", 
/* 419 */           Integer.valueOf(policyDataLength));
/*     */     }
/* 421 */     return policyDataLength;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQOPEN_PRIV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */