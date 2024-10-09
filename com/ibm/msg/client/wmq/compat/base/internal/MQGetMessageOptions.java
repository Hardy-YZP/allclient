/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQGetMessageOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQGetMessageOptions.java";
/*     */   protected static final int sizeofMQGetMessageOptionsv1 = 72;
/*     */   protected static final int sizeofMQGetMessageOptionsv2 = 80;
/*     */   protected static final int sizeofMQGetMessageOptionsv3 = 100;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQGetMessageOptions.java");
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
/*     */ 
/*     */ 
/*     */   
/*  89 */   public char groupStatus = ' ';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   private MQGMO jmqiStructure = MQSESSION.getJmqiEnv().newMQGMO();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public int matchOptions = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public byte[] msgToken = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean noReadBack = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public int options = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public String resolvedQueueName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public int returnedLength = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public char segmentation = ' ';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public char segmentStatus = ' ';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   private int version = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public int waitInterval = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQGetMessageOptions() {
/* 197 */     super(MQSESSION.getJmqiEnv());
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "<init>()");
/*     */     }
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "<init>()");
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
/*     */   public MQGetMessageOptions(boolean noReadBack) {
/* 219 */     this();
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "<init>(boolean)", new Object[] {
/* 222 */             Boolean.valueOf(noReadBack)
/*     */           });
/*     */     }
/* 225 */     if (noReadBack == true) {
/* 226 */       this.noReadBack = true;
/*     */     }
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "<init>(boolean)");
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
/*     */   protected void calcVersion(Hconn hconn) throws MQException {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)", new Object[] { hconn });
/*     */     }
/*     */ 
/*     */     
/* 247 */     int platform = 0;
/* 248 */     int cmdLevel = 0;
/* 249 */     int fapLevel = 0;
/*     */     
/* 251 */     if (hconn != null) {
/*     */       
/*     */       try {
/* 254 */         platform = hconn.getPlatform();
/* 255 */         cmdLevel = hconn.getCmdLevel();
/* 256 */         fapLevel = hconn.getFapLevel();
/*     */       
/*     */       }
/* 259 */       catch (JmqiException e) {
/* 260 */         if (Trace.isOn) {
/* 261 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)", (Throwable)e);
/*     */         }
/*     */ 
/*     */         
/* 265 */         MQException mqe = new MQException(e.getCompCode(), e.getReason(), this);
/* 266 */         if (Trace.isOn) {
/* 267 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)", (Throwable)mqe);
/*     */         }
/*     */         
/* 270 */         if (Trace.isOn) {
/* 271 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)", (Throwable)mqe);
/*     */         }
/*     */         
/* 274 */         throw mqe;
/*     */       } 
/*     */       
/* 277 */       if (cmdLevel >= 600 || (platform != 1 && cmdLevel >= 510)) {
/*     */ 
/*     */ 
/*     */         
/* 281 */         this.version = 3;
/* 282 */       } else if ((platform != 1 && fapLevel >= 4) || (platform == 1 && cmdLevel >= 530)) {
/*     */         
/* 284 */         this.version = 2;
/*     */       } else {
/* 286 */         this.version = 1;
/*     */       } 
/*     */       
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)", "Selected GMO version:", 
/* 291 */             Integer.valueOf(this.version));
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 298 */       int minVersion = 1;
/*     */ 
/*     */       
/* 301 */       if (this.matchOptions != 3) {
/* 302 */         minVersion = 2;
/*     */       }
/* 304 */       if (this.groupStatus != ' ') {
/* 305 */         minVersion = 2;
/*     */       }
/* 307 */       if (this.segmentStatus != ' ') {
/* 308 */         minVersion = 2;
/*     */       }
/* 310 */       if (this.segmentation != ' ') {
/* 311 */         minVersion = 2;
/*     */       }
/*     */ 
/*     */       
/* 315 */       if (this.msgToken != null && !Arrays.equals(this.msgToken, MQC.MQMTOK_NONE)) {
/* 316 */         minVersion = 3;
/*     */       }
/*     */ 
/*     */       
/* 320 */       this.version = minVersion;
/*     */       
/* 322 */       if (Trace.isOn) {
/* 323 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)", "Predicted GMO version (based on selected options - connection info unavailable):", 
/*     */ 
/*     */             
/* 326 */             Integer.valueOf(this.version));
/*     */       }
/*     */     } 
/*     */     
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "calcVersion(Hconn)");
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
/*     */   protected MQGMO getJMQIStructure(Hconn hconn) throws MQException {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "getJMQIStructure(Hconn)", new Object[] { hconn });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     this.jmqiStructure.setVersion(getVersion(hconn));
/*     */     
/* 356 */     this.jmqiStructure.setOptions(this.options);
/* 357 */     this.jmqiStructure.setWaitInterval(this.waitInterval);
/* 358 */     this.jmqiStructure.setResolvedQName(this.resolvedQueueName);
/* 359 */     this.jmqiStructure.setMsgToken(this.msgToken);
/* 360 */     this.jmqiStructure.setReturnedLength(this.returnedLength);
/* 361 */     this.jmqiStructure.setMatchOptions(this.matchOptions);
/* 362 */     this.jmqiStructure.setGroupStatus(this.groupStatus);
/* 363 */     this.jmqiStructure.setSegmentStatus(this.segmentStatus);
/* 364 */     this.jmqiStructure.setSegmentation(this.segmentation);
/*     */     
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "getJMQIStructure(Hconn)", this.jmqiStructure);
/*     */     }
/*     */     
/* 370 */     return this.jmqiStructure;
/*     */   }
/*     */   
/*     */   protected final int getVersion(Hconn hconn) throws MQException {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "getVersion(Hconn)", new Object[] { hconn });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 384 */     calcVersion(hconn);
/*     */     
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "getVersion(Hconn)", 
/* 388 */           Integer.valueOf(this.version));
/*     */     }
/* 390 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int sizeOfMQGetMessageOptions(Hconn hconn) throws MQException {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "sizeOfMQGetMessageOptions(Hconn)", new Object[] { hconn });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 403 */     int version = getVersion(hconn);
/*     */     
/* 405 */     int pointerSize = 4;
/* 406 */     int size = 0;
/*     */     
/*     */     try {
/* 409 */       size = MQGMO.getSize(this.env, version, pointerSize);
/*     */     }
/* 411 */     catch (JmqiException e) {
/* 412 */       if (Trace.isOn) {
/* 413 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "sizeOfMQGetMessageOptions(Hconn)", (Throwable)e);
/*     */       }
/*     */       
/* 416 */       HashMap<String, Object> data = new HashMap<>();
/* 417 */       data.put("version", Integer.toString(version));
/* 418 */       data.put("pointerSize", Integer.toString(pointerSize));
/* 419 */       data.put("exception", e);
/* 420 */       Trace.ffst(this, "sizeOfMQGetMessageOptions", "probe1", data, null);
/*     */     } 
/*     */     
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "sizeOfMQGetMessageOptions(Hconn)", 
/* 425 */           Integer.valueOf(size));
/*     */     }
/* 427 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "updateFromJMQIStructure()");
/*     */     }
/*     */ 
/*     */     
/* 440 */     if (!this.noReadBack) {
/*     */       
/* 442 */       this.version = this.jmqiStructure.getVersion();
/* 443 */       this.options = this.jmqiStructure.getOptions();
/* 444 */       this.waitInterval = this.jmqiStructure.getWaitInterval();
/* 445 */       this.resolvedQueueName = this.jmqiStructure.getResolvedQName();
/* 446 */       this.msgToken = this.jmqiStructure.getMsgToken();
/* 447 */       this.returnedLength = this.jmqiStructure.getReturnedLength();
/* 448 */       this.matchOptions = this.jmqiStructure.getMatchOptions();
/* 449 */       this.groupStatus = (char)this.jmqiStructure.getGroupStatus();
/* 450 */       this.segmentStatus = (char)this.jmqiStructure.getSegmentStatus();
/* 451 */       this.segmentation = (char)this.jmqiStructure.getSegmentation();
/*     */     } 
/* 453 */     if (Trace.isOn)
/* 454 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQGetMessageOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */