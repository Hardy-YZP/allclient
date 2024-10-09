/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
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
/*     */ public class MQPMR
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQPMR.java";
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.jmqi.MQPMR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQPMR.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private byte[] msgId = new byte[24];
/*  52 */   private byte[] correlId = new byte[24];
/*  53 */   private byte[] groupId = new byte[24];
/*  54 */   private int feedback = 0;
/*  55 */   private byte[] accountingToken = new byte[32];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQPMO owningPmo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQPMR(JmqiEnvironment env) {
/*  66 */     super(env);
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMR", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMR", "<init>(JmqiEnvironment)");
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
/*     */   public byte[] getAccountingToken() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data(this, "getAccountingToken()", this.accountingToken);
/*     */     }
/*  86 */     return this.accountingToken;
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
/*     */   public void setAccountingToken(byte[] accountingToken) {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data(this, "setAccountingToken(byte [ ])", accountingToken);
/*     */     }
/*     */     
/* 104 */     JmqiTools.byteArrayCopy(this.accountingToken, 0, accountingToken, 0, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getCorrelId() {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.data(this, "getCorrelId()", this.correlId);
/*     */     }
/* 117 */     return this.correlId;
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
/*     */   public void setCorrelId(byte[] correlId) {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data(this, "setCorrelId(byte [ ])", correlId);
/*     */     }
/* 133 */     JmqiTools.byteArrayCopy(correlId, 0, this.correlId, 0, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFeedback() {
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMR", "getFeedback()", "getter", 
/* 142 */           Integer.valueOf(this.feedback));
/*     */     }
/* 144 */     return this.feedback;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFeedback(int feedback) {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMR", "setFeedback(int)", "setter", 
/* 154 */           Integer.valueOf(feedback));
/*     */     }
/* 156 */     this.feedback = feedback;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getGroupId() {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "getGroupId()", this.groupId);
/*     */     }
/* 169 */     return this.groupId;
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
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "setGroupId(byte [ ])", groupId);
/*     */     }
/* 185 */     JmqiTools.byteArrayCopy(groupId, 0, this.groupId, 0, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getMsgId() {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "getMsgId()", this.msgId);
/*     */     }
/* 197 */     return this.msgId;
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
/*     */   public void setMsgId(byte[] msgId) {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.data(this, "setMsgId(byte [ ])", msgId);
/*     */     }
/* 213 */     JmqiTools.byteArrayCopy(msgId, 0, this.msgId, 0, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwningPmo(MQPMO pmo) {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMR", "setOwningPmo(MQPMO)", "setter", pmo);
/*     */     }
/* 226 */     this.owningPmo = pmo;
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
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 239 */     return calcRequiredBufferSize(this.owningPmo.getPutMsgRecFields());
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int calcRequiredBufferSize(int putMsgRecFields) {
/* 244 */     if (Trace.isOn)
/* 245 */       Trace.entry("com.ibm.mq.jmqi.MQPMR", "calcRequiredBufferSize(int)", new Object[] {
/* 246 */             Integer.valueOf(putMsgRecFields)
/*     */           }); 
/* 248 */     int size = 0;
/*     */     
/* 250 */     if ((putMsgRecFields & 0x1) != 0) {
/* 251 */       size += 24;
/*     */     }
/* 253 */     if ((putMsgRecFields & 0x2) != 0) {
/* 254 */       size += 24;
/*     */     }
/* 256 */     if ((putMsgRecFields & 0x4) != 0) {
/* 257 */       size += 24;
/*     */     }
/* 259 */     if ((putMsgRecFields & 0x8) != 0) {
/* 260 */       size += 4;
/*     */     }
/* 262 */     if ((putMsgRecFields & 0x10) != 0) {
/* 263 */       size += 32;
/*     */     }
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit("com.ibm.mq.jmqi.MQPMR", "calcRequiredBufferSize(int)", Integer.valueOf(size));
/*     */     }
/* 269 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 281 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 283 */     int pos = offset;
/* 284 */     int putMsgRecFields = this.owningPmo.getPutMsgRecFields();
/* 285 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 286 */     if ((putMsgRecFields & 0x1) != 0) {
/* 287 */       System.arraycopy(this.msgId, 0, buffer, pos, 24);
/* 288 */       pos += 24;
/*     */     } 
/* 290 */     if ((putMsgRecFields & 0x2) != 0) {
/* 291 */       System.arraycopy(this.correlId, 0, buffer, pos, 24);
/* 292 */       pos += 24;
/*     */     } 
/* 294 */     if ((putMsgRecFields & 0x4) != 0) {
/* 295 */       System.arraycopy(this.groupId, 0, buffer, pos, 24);
/* 296 */       pos += 24;
/*     */     } 
/* 298 */     if ((putMsgRecFields & 0x8) != 0) {
/* 299 */       dc.writeI32(this.feedback, buffer, pos, swap);
/* 300 */       pos += 4;
/*     */     } 
/* 302 */     if ((putMsgRecFields & 0x10) != 0) {
/* 303 */       System.arraycopy(this.accountingToken, 0, buffer, pos, 32);
/* 304 */       pos += 32;
/*     */     } 
/*     */     
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 309 */           Integer.valueOf(pos));
/*     */     }
/* 311 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 323 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 325 */     int pos = offset;
/* 326 */     int putMsgRecFields = this.owningPmo.getPutMsgRecFields();
/* 327 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 328 */     if ((putMsgRecFields & 0x1) != 0) {
/* 329 */       System.arraycopy(buffer, pos, this.msgId, 0, 24);
/* 330 */       pos += 24;
/*     */     } else {
/*     */       
/* 333 */       this.msgId = null;
/*     */     } 
/* 335 */     if ((putMsgRecFields & 0x2) != 0) {
/* 336 */       System.arraycopy(buffer, pos, this.correlId, 0, 24);
/* 337 */       pos += 24;
/*     */     } else {
/*     */       
/* 340 */       this.correlId = null;
/*     */     } 
/* 342 */     if ((putMsgRecFields & 0x4) != 0) {
/* 343 */       System.arraycopy(buffer, pos, this.groupId, 0, 24);
/* 344 */       pos += 24;
/*     */     } else {
/*     */       
/* 347 */       this.groupId = null;
/*     */     } 
/* 349 */     if ((putMsgRecFields & 0x8) != 0) {
/* 350 */       this.feedback = dc.readI32(buffer, pos, swap);
/* 351 */       pos += 4;
/*     */     } else {
/*     */       
/* 354 */       this.feedback = 0;
/*     */     } 
/* 356 */     if ((putMsgRecFields & 0x10) != 0) {
/* 357 */       System.arraycopy(buffer, pos, this.accountingToken, 0, 32);
/* 358 */       pos += 32;
/*     */     } else {
/*     */       
/* 361 */       this.accountingToken = null;
/*     */     } 
/*     */     
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 366 */           Integer.valueOf(pos));
/*     */     }
/* 368 */     return pos;
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
/* 380 */     fmt.add("msgId", this.msgId);
/* 381 */     fmt.add("correlId", this.correlId);
/* 382 */     fmt.add("groupId", this.groupId);
/* 383 */     fmt.add("feedback", this.feedback);
/* 384 */     fmt.add("accountingToken", this.accountingToken);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQPMR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */