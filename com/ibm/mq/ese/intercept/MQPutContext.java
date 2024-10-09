/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ public final class MQPutContext
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MQPutContext.java";
/*     */   private final ByteBuffer[] buffers;
/*     */   private final String origFormat;
/*     */   private final MessageBufferProcessor messageBufferProcessor;
/*     */   private final SmqiObject smqiObject;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.ese.intercept.MQPutContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MQPutContext.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPutContext(ByteBuffer buffer, String origFormat, MessageBufferProcessor mbp, SmqiObject eseObject) {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MQPutContext", "<init>(ByteBuffer,String,MessageBufferProcessor,SmqiObject)", new Object[] { buffer, origFormat, mbp, eseObject });
/*     */     }
/*     */ 
/*     */     
/*  96 */     this.buffers = new ByteBuffer[1];
/*  97 */     this.buffers[0] = buffer;
/*  98 */     this.origFormat = origFormat;
/*  99 */     this.messageBufferProcessor = mbp;
/* 100 */     this.smqiObject = eseObject;
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MQPutContext", "<init>(ByteBuffer,String,MessageBufferProcessor,SmqiObject)");
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
/*     */   public MQPutContext(ByteBuffer[] buffers, String origFormat, MessageBufferProcessor mbp, SmqiObject eseObject) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MQPutContext", "<init>(ByteBuffer [ ],String,MessageBufferProcessor,SmqiObject)", new Object[] { buffers, origFormat, mbp, eseObject });
/*     */     }
/*     */ 
/*     */     
/* 127 */     this.buffers = buffers;
/* 128 */     this.origFormat = origFormat;
/* 129 */     this.messageBufferProcessor = mbp;
/* 130 */     this.smqiObject = eseObject;
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MQPutContext", "<init>(ByteBuffer [ ],String,MessageBufferProcessor,SmqiObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer getBuffer() {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQPutContext", "getBuffer()", "getter", this.buffers[0]);
/*     */     }
/*     */     
/* 146 */     return this.buffers[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOrigFormat() {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQPutContext", "getOrigFormat()", "getter", this.origFormat);
/*     */     }
/*     */     
/* 157 */     return this.origFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer[] getBuffers() {
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQPutContext", "getBuffers()", "getter", this.buffers);
/*     */     }
/* 167 */     return this.buffers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBufferProcessor getMessageBufferProcessor() {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQPutContext", "getMessageBufferProcessor()", "getter", this.messageBufferProcessor);
/*     */     }
/*     */     
/* 181 */     return this.messageBufferProcessor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SmqiObject getSmqiObject() {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQPutContext", "getSmqiObject()", "getter", this.smqiObject);
/*     */     }
/*     */     
/* 192 */     return this.smqiObject;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\MQPutContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */