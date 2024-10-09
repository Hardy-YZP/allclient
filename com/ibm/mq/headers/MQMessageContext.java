/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.internal.HeaderMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ class MQMessageContext extends MQHeaderContext {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMessageContext.java";
/*     */   
/*     */   static {
/*  13 */     if (Trace.isOn) {
/*  14 */       Trace.data("com.ibm.mq.headers.MQMessageContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMessageContext.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final MQMessage message;
/*     */   
/*     */   private int mark;
/*     */   
/*     */   private static final int SIZEOF_INT = 4;
/*     */ 
/*     */   
/*     */   MQMessageContext(MQMessage message) {
/*  28 */     super(message.format, message.encoding, message.characterSet);
/*  29 */     if (Trace.isOn) {
/*  30 */       Trace.entry(this, "com.ibm.mq.headers.MQMessageContext", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/*     */     
/*  33 */     this.message = message;
/*     */     
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.exit(this, "com.ibm.mq.headers.MQMessageContext", "<init>(MQMessage)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static MQHeaderContext getInstance(DataInput message) {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.entry("com.ibm.mq.headers.MQMessageContext", "getInstance(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/*  46 */     if (message instanceof MQMessage) {
/*  47 */       MQHeaderContext traceRet1 = new MQMessageContext((MQMessage)message);
/*  48 */       if (Trace.isOn) {
/*  49 */         Trace.exit("com.ibm.mq.headers.MQMessageContext", "getInstance(DataInput)", traceRet1);
/*     */       }
/*  51 */       return traceRet1;
/*     */     } 
/*     */     
/*  54 */     UnsupportedOperationException traceRet2 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001", new Object[] {
/*  55 */             message.getClass() }));
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.throwing("com.ibm.mq.headers.MQMessageContext", "getInstance(DataInput)", traceRet2);
/*     */     }
/*     */     
/*  60 */     throw traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataInput getDataInput() {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data(this, "com.ibm.mq.headers.MQMessageContext", "getDataInput()", "getter", this.message);
/*     */     }
/*     */     
/*  70 */     return (DataInput)this.message;
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.mq.headers.MQMessageContext", "available()");
/*     */     }
/*  78 */     int traceRet1 = this.message.getMessageLength() - this.message.getDataOffset();
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.headers.MQMessageContext", "available()", 
/*  82 */           Integer.valueOf(traceRet1));
/*     */     }
/*  84 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public void mark() throws IOException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.mq.headers.MQMessageContext", "mark()");
/*     */     }
/*  91 */     this.mark = this.message.getDataOffset();
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.headers.MQMessageContext", "mark()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void rewind() throws IOException {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.headers.MQMessageContext", "rewind()");
/*     */     }
/* 103 */     this.message.setDataOffset(this.mark);
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.headers.MQMessageContext", "rewind()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int sniff() throws IOException {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.headers.MQMessageContext", "sniff()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     int origEncoding = this.message.encoding;
/*     */     
/* 129 */     this.message.encoding = nextEncoding();
/*     */     
/* 131 */     int value = this.message.readInt();
/*     */ 
/*     */     
/* 134 */     this.message.encoding = origEncoding;
/*     */     
/* 136 */     this.message.seek(this.message.getDataOffset() - 4);
/*     */     
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.headers.MQMessageContext", "sniff()", Integer.valueOf(value));
/*     */     }
/* 141 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQMessageContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */