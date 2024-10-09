/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpMQAPI;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI;
/*     */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN;
/*     */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIINOUT;
/*     */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT;
/*     */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteTagPool
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteTagPool.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteTagPool.java");
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
/*  67 */   private static Map<String, RemoteTagPool> qmgrToTagPool = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*  70 */   private static Object hashtableLock = new Object();
/*     */ 
/*     */   
/*     */   private int tagSize;
/*     */ 
/*     */   
/*  76 */   private byte[] baseTag = null;
/*     */ 
/*     */   
/*     */   private int tagIncrementIndex;
/*     */ 
/*     */   
/*     */   private int bytesForCounter;
/*     */ 
/*     */   
/*  85 */   private int maxIndex = 0;
/*     */ 
/*     */   
/*  88 */   private int indexIssued = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RemoteTagPool getInstance(JmqiEnvironment env, int tagSize, String qmid) {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getInstance(JmqiEnvironment,int,String)", new Object[] { env, 
/* 101 */             Integer.valueOf(tagSize), qmid });
/*     */     }
/*     */ 
/*     */     
/* 105 */     String hashKey = Integer.toString(tagSize) + qmid;
/*     */     
/* 107 */     RemoteTagPool tagPool = qmgrToTagPool.get(hashKey);
/*     */     
/* 109 */     if (tagPool == null) {
/* 110 */       synchronized (hashtableLock) {
/*     */         
/* 112 */         tagPool = qmgrToTagPool.get(hashKey);
/* 113 */         if (tagPool == null) {
/* 114 */           tagPool = new RemoteTagPool(env, tagSize);
/* 115 */           qmgrToTagPool.put(hashKey, tagPool);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getInstance(JmqiEnvironment,int,String)", tagPool);
/*     */     }
/*     */     
/* 124 */     return tagPool;
/*     */   }
/*     */ 
/*     */   
/*     */   private RemoteTagPool(JmqiEnvironment env, int tagSize) {
/* 129 */     super(env);
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/* 132 */             Integer.valueOf(tagSize) });
/*     */     }
/* 134 */     this.tagSize = tagSize;
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "<init>(JmqiEnvironment,int)");
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
/*     */   public synchronized byte[][] getTags(RemoteTls tls, int requestedNumber, RemoteSession session) throws JmqiException {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", new Object[] { tls, 
/* 162 */             Integer.valueOf(requestedNumber), session });
/*     */     }
/*     */ 
/*     */     
/* 166 */     byte[][] returnTags = new byte[requestedNumber][];
/* 167 */     Pint pCompCode = this.env.newPint();
/* 168 */     Pint pReason = this.env.newPint();
/* 169 */     RfpVerbArray verbArray = session.getVerbArray(6, pCompCode, pReason, false);
/*     */     
/* 171 */     if (pCompCode.x != 0) {
/* 172 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 173 */       ffstInfo.put("Completion Code", Integer.valueOf(pCompCode.x));
/* 174 */       ffstInfo.put("Reason Code", Integer.valueOf(pReason.x));
/* 175 */       ffstInfo.put("Description", "Unexpected failure returned by server");
/* 176 */       Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "01", ffstInfo, null);
/*     */       
/* 178 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, pCompCode.x, pReason.x, null);
/*     */       
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 184 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 188 */     for (int tagNumber = 0; tagNumber < requestedNumber; tagNumber++) {
/*     */       
/* 190 */       returnTags[tagNumber] = new byte[this.tagSize];
/*     */ 
/*     */       
/* 193 */       if (this.indexIssued >= this.maxIndex) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 199 */         boolean swap = session.isSwap();
/*     */ 
/*     */         
/* 202 */         RfpMQAPI sMQAPI = session.allocateMQAPI(140);
/* 203 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/* 204 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*     */         
/* 206 */         RfpINSPI inSpi = RfpINSPI.getInstance(this.env, session, commsBuffer, inSpiOffset);
/*     */         
/* 208 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/* 209 */         RfpRESERVESPIINOUT spiInOut = RfpRESERVESPIINOUT.getInstance(this.env, session, commsBuffer, spiInOutOffset, verbArray
/* 210 */             .getMaxInoutVersion());
/*     */         
/* 212 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/* 213 */         RfpRESERVESPIIN spiIn = RfpRESERVESPIIN.getInstance(this.env, session, commsBuffer, spiInOffset, verbArray
/* 214 */             .getMaxInVersion());
/*     */         
/* 216 */         int spiOutRelativeOffset = spiInOffset;
/* 217 */         RfpRESERVESPIOUT spiOut = RfpRESERVESPIOUT.getInstance(this.env, session, commsBuffer, 0, verbArray
/* 218 */             .getMaxOutVersion());
/* 219 */         spiOut.setDefaultVersion(swap);
/*     */ 
/*     */         
/* 222 */         inSpi.setVerbId(6, swap);
/* 223 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/* 224 */         inSpi.setOutStructLength(spiOut.getStructSize() + this.tagSize, swap);
/*     */ 
/*     */         
/* 227 */         spiInOut.initEyecatcher();
/* 228 */         spiInOut.setDefaultVersion(swap);
/* 229 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*     */ 
/*     */         
/* 232 */         spiIn.initEyecatcher();
/* 233 */         spiIn.setDefaultVersion(swap);
/* 234 */         spiIn.setLength(spiIn.getStructSize(), swap);
/* 235 */         spiIn.setTagSize(this.tagSize, swap);
/*     */ 
/*     */ 
/*     */         
/* 239 */         spiIn.setTagReservation(16777215, swap);
/*     */ 
/*     */ 
/*     */         
/* 243 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/* 244 */         sMQAPI.setTransLength(transLength);
/*     */ 
/*     */ 
/*     */         
/* 248 */         int callLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiOut.getStructSize() + this.tagSize;
/* 249 */         sMQAPI.setCallLength(callLength);
/*     */ 
/*     */         
/* 252 */         sMQAPI.setControlFlags1(48);
/*     */ 
/*     */         
/* 255 */         session.sendTSH(tls, (RfpTSH)sMQAPI);
/*     */ 
/*     */         
/* 258 */         RfpMQAPI replyTsh = session.receiveMQIFlow(tls);
/*     */         
/* 260 */         if (replyTsh.getSegmentType() != 156) {
/* 261 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/* 262 */           ffstInfo.put("ControlFlags1", Integer.valueOf(replyTsh.getControlFlags1()));
/* 263 */           ffstInfo.put("Description", "Unexpected reply received from server");
/* 264 */           Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "02", ffstInfo, null);
/*     */           
/* 266 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */ 
/*     */           
/* 269 */           if (Trace.isOn) {
/* 270 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet1, 2);
/*     */           }
/*     */           
/* 273 */           throw traceRet1;
/*     */         } 
/*     */         
/* 276 */         int compCode = replyTsh.getCompCode(swap);
/* 277 */         int reason = replyTsh.getReasonCode(swap);
/*     */         
/* 279 */         if (compCode != 0) {
/* 280 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/* 281 */           ffstInfo.put("Completion Code", Integer.valueOf(compCode));
/* 282 */           ffstInfo.put("Reason Code", Integer.valueOf(reason));
/* 283 */           ffstInfo.put("Description", "Unexpected failure returned by server");
/* 284 */           Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "03", ffstInfo, null);
/*     */           
/* 286 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, compCode, reason, null);
/*     */           
/* 288 */           if (Trace.isOn) {
/* 289 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet1, 3);
/*     */           }
/*     */           
/* 292 */           throw traceRet1;
/*     */         } 
/*     */ 
/*     */         
/* 296 */         byte[] receiveBuffer = replyTsh.getRfpBuffer();
/*     */         
/* 298 */         spiOut.setRfpBuffer(receiveBuffer);
/* 299 */         spiOut.setRfpOffset(replyTsh.getRfpOffset() + spiOutRelativeOffset);
/*     */         
/* 301 */         if (!spiOut.checkID()) {
/* 302 */           HashMap<String, Object> info = new HashMap<>();
/* 303 */           info.put("SegmentType", Integer.valueOf(replyTsh.getSegmentType()));
/* 304 */           info.put("ControlFlags1", Integer.valueOf(replyTsh.getControlFlags1()));
/* 305 */           info.put("Description", "Unexpected flow received from server");
/* 306 */           Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "04", info, null);
/* 307 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */           
/* 309 */           if (Trace.isOn) {
/* 310 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet3, 4);
/*     */           }
/*     */           
/* 313 */           throw traceRet3;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 318 */         int maxCounterVal = 0;
/* 319 */         int tagsInCounterRange = spiOut.getActualReservation(swap);
/*     */ 
/*     */         
/* 322 */         if (tagsInCounterRange <= 256) {
/* 323 */           this.bytesForCounter = 1;
/* 324 */           maxCounterVal = 256;
/*     */         }
/* 326 */         else if (tagsInCounterRange <= 65536) {
/* 327 */           this.bytesForCounter = 2;
/* 328 */           maxCounterVal = 65536;
/*     */         }
/* 330 */         else if (tagsInCounterRange <= 16777216) {
/* 331 */           this.bytesForCounter = 3;
/* 332 */           maxCounterVal = 16777216;
/*     */         } else {
/*     */           
/* 335 */           HashMap<String, Object> info = new HashMap<>();
/* 336 */           info.put("tagsInCounterRange", Integer.valueOf(tagsInCounterRange));
/* 337 */           info.put("Description", "Unexpected byte counter tag reservation allocation");
/* 338 */           Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "05", info, null);
/* 339 */           JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */           
/* 341 */           if (Trace.isOn) {
/* 342 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet4, 5);
/*     */           }
/*     */           
/* 345 */           throw traceRet4;
/*     */         } 
/*     */ 
/*     */         
/* 349 */         this.tagIncrementIndex = spiOut.getTagIncrementOffset(swap) - 1;
/*     */         
/* 351 */         if (this.tagIncrementIndex < 0 || this.tagIncrementIndex >= this.tagSize) {
/* 352 */           HashMap<String, Object> info = new HashMap<>();
/* 353 */           info.put("tagIncrementIndex", Integer.valueOf(this.tagIncrementIndex));
/* 354 */           info.put("Description", "Unexpected tag increment offset supplied by server");
/* 355 */           Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "05", info, null);
/* 356 */           JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */           
/* 358 */           if (Trace.isOn) {
/* 359 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet6, 6);
/*     */           }
/*     */           
/* 362 */           throw traceRet6;
/*     */         } 
/*     */ 
/*     */         
/* 366 */         this.baseTag = new byte[this.tagSize];
/*     */         
/* 368 */         System.arraycopy(spiOut.getRfpBuffer(), spiOut.getBaseReservationTagOffset(), this.baseTag, 0, this.tagSize);
/*     */ 
/*     */         
/* 371 */         if (Trace.isOn) {
/* 372 */           Trace.data(this, "getTags(RemoteTls,int,RemoteSession)", "Base Tag", this.baseTag);
/*     */         }
/*     */ 
/*     */         
/* 376 */         this.indexIssued = 0;
/*     */ 
/*     */         
/* 379 */         if ((session.getMQEncoding() & 0x2) != 0) {
/*     */           
/* 381 */           for (int i = 0; i < this.bytesForCounter; i++) {
/* 382 */             this.indexIssued |= (this.baseTag[this.tagIncrementIndex + i] & 0xFF) << i * 8;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 387 */           for (int i = 0; i < this.bytesForCounter; i++) {
/* 388 */             this.indexIssued |= (this.baseTag[this.tagIncrementIndex + i] & 0xFF) << (this.bytesForCounter - i - 1) * 8;
/*     */           }
/*     */         } 
/*     */         
/* 392 */         if (this.indexIssued + tagsInCounterRange > maxCounterVal) {
/* 393 */           HashMap<String, Object> info = new HashMap<>();
/* 394 */           info.put("tagsInCounterRange", Integer.valueOf(tagsInCounterRange));
/* 395 */           info.put("maxCounterVal", Integer.valueOf(maxCounterVal));
/* 396 */           info.put("indexIssued", Integer.valueOf(this.indexIssued));
/* 397 */           info.put("baseTag", this.baseTag);
/* 398 */           info.put("tagIncrementIndex", Integer.valueOf(this.tagIncrementIndex));
/* 399 */           info.put("bytesForCounter", Integer.valueOf(this.bytesForCounter));
/* 400 */           info.put("Description", "Queue manager returned counter which will wrap");
/* 401 */           Trace.ffst(this, "getTags(RemoteTls,int,RemoteSession)", "06", info, null);
/* 402 */           JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */           
/* 404 */           if (Trace.isOn) {
/* 405 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", (Throwable)traceRet5, 7);
/*     */           }
/*     */           
/* 408 */           throw traceRet5;
/*     */         } 
/* 410 */         System.arraycopy(this.baseTag, 0, returnTags[tagNumber], 0, this.tagSize);
/*     */ 
/*     */ 
/*     */         
/* 414 */         this.maxIndex = this.indexIssued + tagsInCounterRange - 1;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 419 */         this.indexIssued++;
/*     */ 
/*     */         
/* 422 */         System.arraycopy(this.baseTag, 0, returnTags[tagNumber], 0, this.tagSize);
/*     */ 
/*     */         
/* 425 */         if ((session.getMQEncoding() & 0x2) != 0) {
/*     */           
/* 427 */           for (int i = 0; i < this.bytesForCounter; i++) {
/* 428 */             returnTags[tagNumber][this.tagIncrementIndex + i] = (byte)(this.indexIssued >> i * 8 & 0xFF);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 433 */           for (int i = 0; i < this.bytesForCounter; i++) {
/* 434 */             returnTags[tagNumber][this.tagIncrementIndex + i] = (byte)(this.indexIssued >> (this.bytesForCounter - i - 1) * 8 & 0xFF);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 442 */     if (Trace.isOn && returnTags != null) {
/* 443 */       for (int i = 0; i < returnTags.length; i++) {
/* 444 */         if (returnTags[i] != null && 
/* 445 */           Trace.isOn) {
/* 446 */           Trace.data(this, "getTags(RemoteTls,int,RemoteSession)", "returnTags[" + i + "]", returnTags[i]);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 452 */     if (Trace.isOn) {
/* 453 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTagPool", "getTags(RemoteTls,int,RemoteSession)", returnTags);
/*     */     }
/*     */     
/* 456 */     return returnTags;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteTagPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */