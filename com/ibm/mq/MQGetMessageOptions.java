/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQGMO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQGetMessageOptions.java";
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.MQGetMessageOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQGetMessageOptions.java");
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
/*  76 */   private MQGMO jmqiStructure = MQSESSION.getJmqiEnv().newMQGMO();
/*     */ 
/*     */ 
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
/*     */   
/* 100 */   public int options = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public int waitInterval = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String resolvedQueueName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public byte[] msgToken = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public int returnedLength = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public int matchOptions = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public char groupStatus = ' ';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public char segmentStatus = ' ';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public char segmentation = ' ';
/*     */   protected static final int sizeofMQGetMessageOptionsv1 = 72;
/*     */   protected static final int sizeofMQGetMessageOptionsv2 = 80;
/*     */   protected static final int sizeofMQGetMessageOptionsv3 = 100;
/*     */   private int version;
/*     */   
/*     */   public MQGetMessageOptions() {
/* 179 */     super(MQSESSION.getJmqiEnv());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     this.version = 1; if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQGetMessageOptions", "<init>()"); 
/*     */     if (Trace.isOn)
/* 234 */       Trace.exit(this, "com.ibm.mq.MQGetMessageOptions", "<init>()");  } protected final int getVersion() { if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.MQGetMessageOptions", "getVersion()", "getter", 
/* 236 */           Integer.valueOf(this.version));
/*     */     }
/* 238 */     return this.version; }
/*     */    public MQGetMessageOptions(boolean noReadBack) {
/*     */     this();
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQGetMessageOptions", "<init>(boolean)", new Object[] { Boolean.valueOf(noReadBack) }); 
/*     */     if (noReadBack == true)
/*     */       this.noReadBack = true; 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQGetMessageOptions", "<init>(boolean)"); 
/*     */   }
/*     */   protected void calcVersion(MQManagedConnectionJ11 manconn) {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.mq.MQGetMessageOptions", "calcVersion(MQManagedConnectionJ11)", new Object[] { manconn });
/*     */     }
/*     */     
/* 253 */     if (manconn != null) {
/* 254 */       int cmdLevel = manconn.getCmdLevel();
/*     */       
/* 256 */       if (cmdLevel >= 700) {
/* 257 */         this.version = 4;
/* 258 */       } else if (cmdLevel >= 510) {
/* 259 */         this.version = 3;
/*     */       } 
/*     */     } else {
/* 262 */       int minVersion = 1;
/*     */       
/* 264 */       if (this.matchOptions != 3) {
/* 265 */         minVersion = 2;
/*     */       }
/* 267 */       if (this.groupStatus != ' ') {
/* 268 */         minVersion = 2;
/*     */       }
/* 270 */       if (this.segmentStatus != ' ') {
/* 271 */         minVersion = 2;
/*     */       }
/* 273 */       if (this.segmentation != ' ') {
/* 274 */         minVersion = 2;
/*     */       }
/*     */       
/* 277 */       if ((this.matchOptions & 0x30) != 0) {
/* 278 */         minVersion = 3;
/*     */       }
/* 280 */       if (this.msgToken != null && !Arrays.equals(this.msgToken, MQConstants.MQMTOK_NONE)) {
/* 281 */         minVersion = 3;
/*     */       }
/*     */       
/* 284 */       int propOptions = 503316480;
/*     */ 
/*     */       
/* 287 */       if ((this.options & propOptions) != 0) {
/* 288 */         minVersion = 4;
/*     */       }
/*     */       
/* 291 */       this.version = minVersion;
/*     */     } 
/*     */     
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.MQGetMessageOptions", "calcVersion(MQManagedConnectionJ11)");
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
/*     */   protected MQGMO getJMQIStructure() {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.entry(this, "com.ibm.mq.MQGetMessageOptions", "getJMQIStructure()");
/*     */     }
/* 313 */     this.jmqiStructure.setVersion(this.version);
/* 314 */     this.jmqiStructure.setOptions(this.options);
/* 315 */     this.jmqiStructure.setWaitInterval(this.waitInterval);
/* 316 */     this.jmqiStructure.setResolvedQName(this.resolvedQueueName);
/* 317 */     this.jmqiStructure.setMsgToken(this.msgToken);
/* 318 */     this.jmqiStructure.setReturnedLength(this.returnedLength);
/* 319 */     this.jmqiStructure.setMatchOptions(this.matchOptions);
/* 320 */     this.jmqiStructure.setGroupStatus(this.groupStatus);
/* 321 */     this.jmqiStructure.setSegmentStatus(this.segmentStatus);
/* 322 */     this.jmqiStructure.setSegmentation(this.segmentation);
/*     */     
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.exit(this, "com.ibm.mq.MQGetMessageOptions", "getJMQIStructure()", this.jmqiStructure);
/*     */     }
/* 327 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry(this, "com.ibm.mq.MQGetMessageOptions", "updateFromJMQIStructure()");
/*     */     }
/*     */ 
/*     */     
/* 341 */     if (!this.noReadBack) {
/* 342 */       this.version = this.jmqiStructure.getVersion();
/* 343 */       this.options = this.jmqiStructure.getOptions();
/* 344 */       this.waitInterval = this.jmqiStructure.getWaitInterval();
/* 345 */       this.resolvedQueueName = this.jmqiStructure.getResolvedQName();
/* 346 */       this.msgToken = this.jmqiStructure.getMsgToken();
/* 347 */       this.returnedLength = this.jmqiStructure.getReturnedLength();
/* 348 */       this.matchOptions = this.jmqiStructure.getMatchOptions();
/* 349 */       this.groupStatus = (char)this.jmqiStructure.getGroupStatus();
/* 350 */       this.segmentStatus = (char)this.jmqiStructure.getSegmentStatus();
/* 351 */       this.segmentation = (char)this.jmqiStructure.getSegmentation();
/*     */     } 
/*     */     
/* 354 */     if (Trace.isOn)
/* 355 */       Trace.exit(this, "com.ibm.mq.MQGetMessageOptions", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQGetMessageOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */