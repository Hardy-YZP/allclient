/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpMQCONN
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQCONN.java";
/*     */   private static final int QMGR_NAME_OFFSET = 0;
/*     */   private static final int APPL_NAME_OFFSET = 48;
/*     */   private static final int APPL_TYPE_OFFSET = 76;
/*     */   private static final int ACCT_TOKEN_OFFSET = 80;
/*     */   private static final int OPTIONS_OFFSET = 112;
/*     */   private static final int X_OPTIONS_OFFSET = 116;
/*     */   public static final int SIZE_FAP2 = 112;
/*     */   public static final int SIZE_TO_FAPMQCNO = 120;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQCONN.java");
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
/*     */   public RfpMQCONN(JmqiEnvironment env, byte[] buffer, int offset) {
/*  62 */     super(env, buffer, offset);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int getReconnectionIdOffset(RfpFAPMQCNO fapMQCNO, boolean swap) {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionIdOffset(RfpFAPMQCNO, boolean)", new Object[] { fapMQCNO, 
/* 105 */             Boolean.valueOf(swap) });
/*     */     }
/*     */     
/* 108 */     int idOffset = 120 + fapMQCNO.getSize(swap);
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionIdOffset(RfpFAPMQCNO, boolean)", idOffset);
/*     */     }
/*     */     
/* 114 */     return idOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int getReconnectionQmIdOffset(RfpFAPMQCNO fapMQCNO, boolean swap) {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionQmIdOffset(RfpFAPMQCNO, boolean)", new Object[] { fapMQCNO, 
/* 125 */             Boolean.valueOf(swap) });
/*     */     }
/*     */     
/* 128 */     int idOffset = getReconnectionIdOffset(fapMQCNO, swap) + 24;
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionQmIdOffset(RfpFAPMQCNO, boolean)", idOffset);
/*     */     }
/*     */     
/* 134 */     return idOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQMgrName(String qMgrName, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setQMgrName(String,JmqiCodepage,JmqiTls)", new Object[] { qMgrName, cp, tls });
/*     */     }
/*     */     
/* 147 */     this.dc.writeMQField(qMgrName, this.buffer, this.offset + 0, 48, cp, tls);
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setQMgrName(String,JmqiCodepage,JmqiTls)");
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
/*     */   public void setApplName(String applName, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setApplName(String,JmqiCodepage,JmqiTls)", new Object[] { applName, cp, tls });
/*     */     }
/*     */     
/* 166 */     this.dc.writeMQField(applName, this.buffer, this.offset + 48, 28, cp, tls);
/*     */     
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setApplName(String,JmqiCodepage,JmqiTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplType(int applType, boolean swap) {
/* 179 */     if (Trace.isOn)
/* 180 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setApplType(int,boolean)", new Object[] {
/* 181 */             Integer.valueOf(applType), Boolean.valueOf(swap)
/*     */           }); 
/* 183 */     this.dc.writeI32(applType, this.buffer, this.offset + 76, swap);
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setApplType(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcctToken(byte[] acctToken) throws JmqiException {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setAcctToken(byte [ ])", "setter", acctToken);
/*     */     }
/*     */ 
/*     */     
/* 200 */     System.arraycopy(acctToken, 0, this.buffer, this.offset + 80, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options, boolean swap) {
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setOptions(int,boolean)", new Object[] {
/* 210 */             RemoteConstantDecoder.formatOptions(options, "rfpOPT_"), Boolean.valueOf(swap)
/*     */           }); 
/* 212 */     this.dc.writeI32(options, this.buffer, this.offset + 112, swap);
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setOptions(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXOptions(int xOptions, boolean swap) {
/* 224 */     if (Trace.isOn)
/* 225 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setXOptions(int,boolean)", new Object[] {
/*     */             
/* 227 */             String.format("%d (0X%x) - %s", new Object[] { Integer.valueOf(xOptions), Integer.valueOf(xOptions), MQConstants.decodeOptionsForTrace(xOptions, "MQCNO_") }), Boolean.valueOf(swap)
/*     */           }); 
/* 229 */     this.dc.writeI32(xOptions, this.buffer, this.offset + 116, swap);
/*     */     
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setXOptions(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearReconnectionId(int idOffset) {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.data(this, "clearReconnectionId(int)", "setter", 
/* 244 */           Integer.valueOf(idOffset));
/*     */     }
/* 246 */     int start = this.offset + idOffset;
/* 247 */     int end = start + 24;
/* 248 */     Arrays.fill(this.buffer, start, end, (byte)0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReconnectionId(byte[] rcnId, int idOffset) {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "setReconnectionId(byte [ ], int)", "setter", new Object[] { rcnId, 
/* 260 */             Integer.valueOf(idOffset) });
/*     */     }
/* 262 */     System.arraycopy(rcnId, 0, this.buffer, this.offset + idOffset, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearReconnectionQmId(int idOffset) {
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.data(this, "clearReconnectionQmId(int)", "setter", 
/* 272 */           Integer.valueOf(idOffset));
/*     */     }
/* 274 */     int start = this.offset + idOffset;
/* 275 */     int end = start + 48;
/* 276 */     Arrays.fill(this.buffer, start, end, (byte)0);
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
/*     */   public void setReconnectionQmId(String qmid, JmqiCodepage cp, JmqiTls tls, int idOffset) throws JmqiException {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setReconnectionQmId(String,JmqiCodepage,JmqiTls,int)", new Object[] { qmid, cp, tls, 
/* 291 */             Integer.valueOf(idOffset) });
/*     */     }
/* 293 */     this.dc.writeMQField(qmid, this.buffer, this.offset + idOffset, 48, cp, tls);
/*     */     
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "setReconnectionQmId(String,JmqiCodepage,JmqiTls,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMgrName(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getQMgrName(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 311 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 0, 48, cp, tls);
/*     */     
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getQMgrName(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 317 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplName(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getApplName(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 329 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 48, 28, cp, tls);
/*     */     
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getApplName(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 335 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getApplType(boolean swap) {
/* 341 */     if (Trace.isOn)
/* 342 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getApplType(boolean)", new Object[] {
/* 343 */             Boolean.valueOf(swap)
/*     */           }); 
/* 345 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 76, swap);
/*     */     
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getApplType(boolean)", 
/* 349 */           Integer.valueOf(traceRet1));
/*     */     }
/* 351 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAcctToken(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getAcctToken(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 363 */     String traceRet1 = this.dc.readField(this.buffer, this.offset + 80, 32, cp, tls);
/*     */     
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getAcctToken(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 369 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions(boolean swap) {
/* 375 */     if (Trace.isOn)
/* 376 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getOptions(boolean)", new Object[] {
/* 377 */             Boolean.valueOf(swap)
/*     */           }); 
/* 379 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 112, swap);
/*     */     
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getOptions(boolean)", 
/* 383 */           RemoteConstantDecoder.formatOptions(traceRet1, "rfpOPT_"));
/*     */     }
/* 385 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXOptions(boolean swap) {
/* 392 */     if (Trace.isOn)
/* 393 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getXOptions(boolean)", new Object[] {
/* 394 */             Boolean.valueOf(swap)
/*     */           }); 
/* 396 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 116, swap);
/*     */     
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getXOptions(boolean)", 
/* 400 */           String.format("%d (0X%x) - %s", new Object[] { Integer.valueOf(traceRet1), Integer.valueOf(traceRet1), MQConstants.decodeOptionsForTrace(traceRet1, "MQCNO_") }));
/*     */     }
/* 402 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getReconnectionId(int idOffset) {
/* 410 */     byte[] retval = new byte[24];
/* 411 */     System.arraycopy(this.buffer, this.offset + idOffset, retval, 0, 24);
/* 412 */     if (Trace.isOn)
/* 413 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionId(int)", "getter", new Object[] {
/* 414 */             Integer.valueOf(idOffset), retval
/*     */           }); 
/* 416 */     return retval;
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
/*     */   public String getReconnectionQmId(JmqiCodepage cp, JmqiTls tls, int idOffset) throws JmqiException {
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionQmId(JmqiCodepage,JmqiTls,int)", new Object[] { cp, tls, 
/* 430 */             Integer.valueOf(idOffset) });
/*     */     }
/* 432 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + idOffset, 48, cp, tls);
/*     */     
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCONN", "getReconnectionQmId(JmqiCodepage,JmqiTls, int)", traceRet1);
/*     */     }
/*     */     
/* 438 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQCONN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */