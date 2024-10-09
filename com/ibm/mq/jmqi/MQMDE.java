/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQMDE
/*     */   extends AbstractMqHeaderStructure
/*     */ {
/*     */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQMDE.java";
/*     */   private static final int SIZEOF_GROUP_ID = 24;
/*     */   private static final int SIZEOF_MSG_SEQ_NUMBER = 4;
/*     */   private static final int SIZEOF_OFFSET = 4;
/*     */   private static final int SIZEOF_MSG_FLAGS = 4;
/*     */   private static final int SIZEOF_ORIGINAL_LENGTH = 4;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.jmqi.MQMDE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQMDE.java");
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
/*  75 */   private byte[] groupId = new byte[24];
/*  76 */   private int msgSeqNumber = 1;
/*  77 */   private int physicalMsgOffset = 0;
/*  78 */   private int msgFlags = 0;
/*  79 */   private int originalLength = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(JmqiEnvironment env, int ptrSize) {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry("com.ibm.mq.jmqi.MQMDE", "getSizeV2(JmqiEnvironment,int)", new Object[] { env, 
/*  91 */             Integer.valueOf(ptrSize) });
/*     */     }
/*  93 */     int size = MQHeader.getSize(env, ptrSize);
/*  94 */     size += 40;
/*     */ 
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit("com.ibm.mq.jmqi.MQMDE", "getSizeV2(JmqiEnvironment,int)", 
/*  99 */           Integer.valueOf(size));
/*     */     }
/* 101 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/*     */     JmqiException e;
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry("com.ibm.mq.jmqi.MQMDE", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 116 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/*     */     
/* 119 */     switch (version) {
/*     */       case 2:
/* 121 */         size = getSizeV2(env, ptrSize);
/*     */         break;
/*     */       
/*     */       default:
/* 125 */         e = new JmqiException(env, -1, null, 2, 2248, null);
/*     */ 
/*     */         
/* 128 */         if (Trace.isOn) {
/* 129 */           Trace.throwing("com.ibm.mq.jmqi.MQMDE", "getSize(JmqiEnvironment,int,int)", e);
/*     */         }
/* 131 */         throw e;
/*     */     } 
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit("com.ibm.mq.jmqi.MQMDE", "getSize(JmqiEnvironment,int,int)", 
/* 135 */           Integer.valueOf(size));
/*     */     }
/* 137 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQMDE(JmqiEnvironment env) {
/* 146 */     super(env);
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMDE", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 150 */     MQHeader mqHeader = env.newMQHeader();
/* 151 */     mqHeader.setStrucId("MDE ");
/* 152 */     mqHeader.setVersion(2);
/* 153 */     mqHeader.setStrucLength(72);
/* 154 */     setMqHeader(mqHeader);
/*     */     
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMDE", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQMDE(JmqiEnvironment env, MQHeader mqHeader) {
/* 168 */     super(env, mqHeader);
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMDE", "<init>(JmqiEnvironment,MQHeader)", new Object[] { env, mqHeader });
/*     */     }
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMDE", "<init>(JmqiEnvironment,MQHeader)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getGroupId() {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.data(this, "getGroupId()", this.groupId);
/*     */     }
/* 188 */     return this.groupId;
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
/*     */   public void setGroupId(byte[] groupId) {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "setGroupId(byte [ ])", groupId);
/*     */     }
/* 204 */     JmqiTools.byteArrayCopy(groupId, 0, this.groupId, 0, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgFlags() {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "getMsgFlags()", "getter", 
/* 213 */           Integer.valueOf(this.msgFlags));
/*     */     }
/* 215 */     return this.msgFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgFlags(int msgFlags) {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "setMsgFlags(int)", "setter", 
/* 225 */           Integer.valueOf(msgFlags));
/*     */     }
/* 227 */     this.msgFlags = msgFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgSeqNumber() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "getMsgSeqNumber()", "getter", 
/* 237 */           Integer.valueOf(this.msgSeqNumber));
/*     */     }
/* 239 */     return this.msgSeqNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgSeqNumber(int msgSeqNumber) {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "setMsgSeqNumber(int)", "setter", 
/* 249 */           Integer.valueOf(msgSeqNumber));
/*     */     }
/* 251 */     this.msgSeqNumber = msgSeqNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffset() {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "getOffset()", "getter", 
/* 261 */           Integer.valueOf(this.physicalMsgOffset));
/*     */     }
/* 263 */     return this.physicalMsgOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOffset(int physicalMsgOffset) {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "setOffset(int)", "setter", 
/* 273 */           Integer.valueOf(physicalMsgOffset));
/*     */     }
/* 275 */     this.physicalMsgOffset = physicalMsgOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginalLength() {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "getOriginalLength()", "getter", 
/* 285 */           Integer.valueOf(this.originalLength));
/*     */     }
/* 287 */     return this.originalLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalLength(int originalLength) {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.data(this, "com.ibm.mq.jmqi.MQMDE", "setOriginalLength(int)", "setter", 
/* 297 */           Integer.valueOf(originalLength));
/*     */     }
/* 299 */     this.originalLength = originalLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 309 */     return getSize(this.env, getMqHeader().getVersion(), ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMDE", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 321 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 323 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 325 */     MQHeader mqHeader = getMqHeader();
/* 326 */     int version = mqHeader.getVersion();
/* 327 */     int strucLength = getSize(this.env, version, ptrSize);
/* 328 */     mqHeader.setStrucLength(strucLength);
/* 329 */     int pos = mqHeader.writeToBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 331 */     System.arraycopy(this.groupId, 0, buffer, pos, 24);
/* 332 */     pos += 24;
/*     */     
/* 334 */     dc.writeI32(this.msgSeqNumber, buffer, pos, swap);
/* 335 */     pos += 4;
/*     */     
/* 337 */     dc.writeI32(this.physicalMsgOffset, buffer, pos, swap);
/* 338 */     pos += 4;
/*     */     
/* 340 */     dc.writeI32(this.msgFlags, buffer, pos, swap);
/* 341 */     pos += 4;
/*     */     
/* 343 */     dc.writeI32(this.originalLength, buffer, pos, swap);
/* 344 */     pos += 4;
/*     */     
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMDE", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 348 */           Integer.valueOf(pos));
/*     */     }
/* 350 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMDE", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 362 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 364 */     int pos = offset;
/*     */     
/* 366 */     MQHeader mqHeader = getMqHeader();
/* 367 */     pos += mqHeader.readFromBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 369 */     String strucId = mqHeader.getStrucId();
/* 370 */     if (!strucId.equals("MDE ")) {
/* 371 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2248, null);
/*     */ 
/*     */       
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQMDE", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 378 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 382 */     pos += readBodyFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMDE", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 386 */           Integer.valueOf(pos));
/*     */     }
/* 388 */     return pos;
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
/*     */   public int readBodyFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.entry(this, "com.ibm.mq.jmqi.MQMDE", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 408 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 410 */     int pos = offset;
/* 411 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 412 */     MQHeader mqHeader = getMqHeader();
/*     */     
/* 414 */     System.arraycopy(buffer, pos, this.groupId, 0, 24);
/* 415 */     pos += 24;
/*     */     
/* 417 */     this.msgSeqNumber = dc.readI32(buffer, pos, swap);
/* 418 */     pos += 4;
/*     */     
/* 420 */     this.physicalMsgOffset = dc.readI32(buffer, pos, swap);
/* 421 */     pos += 4;
/*     */     
/* 423 */     this.msgFlags = dc.readI32(buffer, pos, swap);
/* 424 */     pos += 4;
/*     */     
/* 426 */     this.originalLength = dc.readI32(buffer, pos, swap);
/* 427 */     pos += 4;
/* 428 */     if (pos - offset + MQHeader.getSize(this.env, ptrSize) != mqHeader.getStrucLength()) {
/*     */       
/* 430 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2142, null);
/*     */ 
/*     */       
/* 433 */       if (Trace.isOn) {
/* 434 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQMDE", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*     */       }
/*     */       
/* 437 */       throw e;
/*     */     } 
/*     */     
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.exit(this, "com.ibm.mq.jmqi.MQMDE", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 442 */           Integer.valueOf(pos));
/*     */     }
/* 444 */     return pos;
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
/* 456 */     getMqHeader().addFieldsToFormatter(fmt);
/* 457 */     fmt.add("groupId", this.groupId);
/* 458 */     fmt.add("msgSeqNumber", this.msgSeqNumber);
/* 459 */     fmt.add("physicalMsgOffset", this.physicalMsgOffset);
/* 460 */     fmt.add("msgFlags", this.msgFlags);
/* 461 */     fmt.add("originalLength", this.originalLength);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQMDE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */