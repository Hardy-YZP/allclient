/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP;
/*     */ import com.ibm.mq.jmqi.system.zrfp.SendZRFP;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
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
/*     */ public class RfpMPH
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMPH.java";
/*     */   private static final String rfpMPH_ID = "MPH ";
/*     */   private static final int rfpMPH_VERSION_1 = 1;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_LENGTH = 4;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMPH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMPH.java");
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
/*  71 */   private int version = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   private int length;
/*     */ 
/*     */ 
/*     */   
/*     */   private Triplet[] triplets;
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpMPH(JmqiEnvironment env, byte[] buffer, int offset) {
/*  84 */     super(env, buffer, offset);
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
/*     */   public int writeToBuffer(int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 100 */     if (Trace.isOn)
/* 101 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "writeToBuffer(int,boolean,JmqiCodepage,JmqiTls)", new Object[] {
/* 102 */             Integer.valueOf(ptrSize), 
/* 103 */             Boolean.valueOf(swap), cp, tls
/*     */           }); 
/* 105 */     int pos = this.offset;
/*     */     
/* 107 */     if (this.triplets == null) {
/* 108 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 6108, null);
/*     */ 
/*     */ 
/*     */       
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "writeToBuffer(int,boolean,JmqiCodepage,JmqiTls)", (Throwable)ex);
/*     */       }
/*     */       
/* 116 */       throw ex;
/*     */     } 
/*     */ 
/*     */     
/* 120 */     SendZRFP sendZRFP = new SendZRFP(this.env, tls);
/*     */ 
/*     */     
/* 123 */     this.dc.writeMQField("MPH ", this.buffer, pos, 4, cp, tls);
/* 124 */     pos += 4;
/*     */ 
/*     */     
/* 127 */     this.dc.writeI32(this.version, this.buffer, pos, swap);
/* 128 */     pos += 4;
/*     */ 
/*     */     
/* 131 */     int lengthPos = pos;
/* 132 */     pos += 4;
/*     */ 
/*     */     
/* 135 */     pos = sendZRFP.writeToBuffer(this.buffer, pos, this.triplets);
/*     */ 
/*     */     
/* 138 */     int zrfpLength = sendZRFP.getStructLength();
/* 139 */     this.length = 12 + zrfpLength;
/* 140 */     this.dc.writeI32(this.length, this.buffer, lengthPos, swap);
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "writeToBuffer(int,boolean,JmqiCodepage,JmqiTls)", 
/* 144 */           Integer.valueOf(pos));
/*     */     }
/* 146 */     return pos;
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
/*     */   public int readFromBuffer(int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 162 */     if (Trace.isOn)
/* 163 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "readFromBuffer(int,boolean,JmqiCodepage,JmqiTls)", new Object[] {
/* 164 */             Integer.valueOf(ptrSize), 
/* 165 */             Boolean.valueOf(swap), cp, tls
/*     */           }); 
/* 167 */     int pos = this.offset;
/*     */     
/* 169 */     ReceiveZRFP receiveZRFP = new ReceiveZRFP(this.env, tls);
/*     */     
/* 171 */     String strucId = this.dc.readMQField(this.buffer, pos, 4, cp, tls);
/* 172 */     if (!strucId.equals("MPH ")) {
/* 173 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */ 
/*     */ 
/*     */       
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "readFromBuffer(int,boolean,JmqiCodepage,JmqiTls)", (Throwable)ex, 1);
/*     */       }
/*     */       
/* 181 */       throw ex;
/*     */     } 
/* 183 */     pos += 4;
/*     */ 
/*     */     
/* 186 */     this.version = this.dc.readI32(this.buffer, pos, swap);
/* 187 */     pos += 4;
/*     */ 
/*     */     
/* 190 */     this.length = this.dc.readI32(this.buffer, pos, swap);
/* 191 */     pos += 4;
/*     */ 
/*     */     
/* 194 */     int zrfpLength = this.length - 12;
/* 195 */     if (zrfpLength < 0) {
/* 196 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 2005, null);
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "readFromBuffer(int,boolean,JmqiCodepage,JmqiTls)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 201 */       throw ex;
/*     */     } 
/*     */     
/* 204 */     pos = receiveZRFP.readFromBuffer(this.buffer, pos);
/* 205 */     this.triplets = receiveZRFP.getTriplets();
/*     */     
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "readFromBuffer(int,boolean,JmqiCodepage,JmqiTls)", 
/* 209 */           Integer.valueOf(pos));
/*     */     }
/* 211 */     return pos;
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
/*     */   public int getMPHLength(int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 228 */     int pos = this.offset;
/*     */ 
/*     */     
/* 231 */     String strucId = this.dc.readMQField(this.buffer, pos, 4, cp, tls);
/* 232 */     if (!strucId.equals("MPH ")) {
/* 233 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 234 */       throw ex;
/*     */     } 
/* 236 */     pos += 4;
/*     */ 
/*     */     
/* 239 */     pos += 4;
/*     */ 
/*     */     
/* 242 */     this.length = this.dc.readI32(this.buffer, pos, swap);
/*     */ 
/*     */     
/* 245 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderSizeV1() {
/* 252 */     int traceRet1 = 12;
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "getHeaderSizeV1()", "getter", 
/* 255 */           Integer.valueOf(traceRet1));
/*     */     }
/* 257 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "getVersion()", "getter", 
/* 266 */           Integer.valueOf(this.version));
/*     */     }
/* 268 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "setVersion(int)", "setter", 
/* 277 */           Integer.valueOf(version));
/*     */     }
/* 279 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "getLength()", "getter", 
/* 288 */           Integer.valueOf(this.length));
/*     */     }
/* 290 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(int length) {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "setLength(int)", "setter", 
/* 299 */           Integer.valueOf(length));
/*     */     }
/* 301 */     this.length = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Triplet[] getTriplets() {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "getTriplets()", "getter", this.triplets);
/*     */     }
/* 311 */     return this.triplets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTriplets(Triplet[] triplets) {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMPH", "setTriplets(Triplet [ ])", "setter", triplets);
/*     */     }
/*     */     
/* 322 */     this.triplets = triplets;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMPH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */