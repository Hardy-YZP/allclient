/*     */ package com.ibm.mq.jmqi.handles;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PbyteBuffer
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/PbyteBuffer.java";
/*     */   private ByteBuffer buffer;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.handles.PbyteBuffer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/PbyteBuffer.java");
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
/*  54 */   private int highWaterMark = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PbyteBuffer(JmqiEnvironment env, ByteBuffer buffer) {
/*  63 */     super(env);
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "<init>(JmqiEnvironment,ByteBuffer)", new Object[] { env, buffer });
/*     */     }
/*     */     
/*  68 */     this.buffer = buffer;
/*     */ 
/*     */     
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "<init>(JmqiEnvironment,ByteBuffer)");
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
/*     */   public PbyteBuffer(JmqiEnvironment env) {
/*  84 */     super(env);
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  89 */     this.buffer = null;
/*     */ 
/*     */     
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer getBuffer() {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "getBuffer()", "getter", this.buffer);
/*     */     }
/* 106 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBuffer(ByteBuffer buffer) {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.data(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "setBuffer(ByteBuffer)", "setter", buffer);
/*     */     }
/*     */     
/* 118 */     this.buffer = buffer;
/* 119 */     this.highWaterMark = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHighWaterMark(int highWaterMark) {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "setHighWaterMark(int)", "setter", 
/* 128 */           Integer.valueOf(highWaterMark));
/*     */     }
/* 130 */     this.highWaterMark = highWaterMark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHighWaterMark() {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.data(this, "com.ibm.mq.jmqi.handles.PbyteBuffer", "getHighWaterMark()", "getter", 
/* 139 */           Integer.valueOf(this.highWaterMark));
/*     */     }
/* 141 */     return this.highWaterMark;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\PbyteBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */