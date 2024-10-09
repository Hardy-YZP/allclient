/*     */ package com.ibm.mq.jmqi.internal;
/*     */ 
/*     */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*     */ import com.ibm.mq.constants.QmgrSplCapability;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
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
/*     */ public abstract class HconnAdapter
/*     */   implements Hconn
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/HconnAdapter.java";
/*     */   byte[] connTag;
/*     */   
/*     */   public int getCmdLevel() throws JmqiException {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getCmdLevel()", "getter", 
/*  55 */           Integer.valueOf(0));
/*     */     }
/*  57 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUid() throws JmqiException {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getUid()", "getter", null);
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() throws JmqiException {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getName()", "getter", null);
/*     */     }
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOriginalQueueManagerName() throws JmqiException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getOriginalQueueManagerName()", "getter", null);
/*     */     }
/*     */     
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlatform() throws JmqiException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getPlatform()", "getter", 
/* 101 */           Integer.valueOf(0));
/*     */     }
/* 103 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCcsid() throws JmqiException {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getCcsid()", "getter", 
/* 113 */           Integer.valueOf(0));
/*     */     }
/* 115 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXASupported() {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "isXASupported()", "getter", 
/* 125 */           Boolean.valueOf(false));
/*     */     }
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfSharingConversations() {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getNumberOfSharingConversations()", "getter", 
/*     */           
/* 138 */           Integer.valueOf(-1));
/*     */     }
/* 140 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFapLevel() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getFapLevel()", "getter", 
/* 150 */           Integer.valueOf(-1));
/*     */     }
/* 152 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getConnectionId() {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getConnectionId()", "getter", null);
/*     */     }
/*     */     
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConnectionIdAsString() {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getConnectionIdAsString()", "getter", null);
/*     */     }
/*     */     
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getConnectionArea() {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getConnectionArea()", "getter", null);
/*     */     }
/*     */     
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionArea(Object object) {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "setConnectionArea(Object)", "setter", object);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPointerSize() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getPointerSize()", "getter", 
/* 218 */           Integer.valueOf(0));
/*     */     }
/* 220 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isByteSwap() {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "isByteSwap()", "getter", 
/* 230 */           Boolean.valueOf(false));
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiCodepage getCodePage() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getCodePage()", "getter", null);
/*     */     }
/* 243 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQsgName() throws JmqiException {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getQsgName()", "getter", null);
/*     */     }
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QmgrSplCapability getQmgrSplCapability() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getQmgrSplCapability()", "getter", QmgrSplCapability.UNKNOWN);
/*     */     }
/*     */     
/* 269 */     return QmgrSplCapability.UNKNOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QmgrAdvancedCapability getQmgrAdvancedCapability() {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getQmgrAdvancedCapability()", "getter", QmgrAdvancedCapability.UNKNOWN);
/*     */     }
/*     */     
/* 282 */     return QmgrAdvancedCapability.UNKNOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConnToZos() {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "isConnToZos()", "getter", 
/* 292 */           Boolean.valueOf(false));
/*     */     }
/* 294 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRemoteProductId() {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getRemoteProductId()", "getter", null);
/*     */     }
/*     */     
/* 306 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "invalidate()");
/*     */     }
/* 318 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getConnTag() {
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "getConnTag()", this.connTag);
/*     */     }
/*     */     
/* 330 */     return this.connTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnTag(byte[] connTag) {
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HconnAdapter", "setConnTag()", connTag);
/*     */     }
/*     */     
/* 342 */     this.connTag = connTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onMessagePermitted() {
/* 351 */     return true;
/*     */   }
/*     */   
/*     */   public void leaveOnMessage() {}
/*     */   
/*     */   public void initiateConnectionStop() {}
/*     */   
/*     */   public void finishConnectionStop() {}
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\HconnAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */