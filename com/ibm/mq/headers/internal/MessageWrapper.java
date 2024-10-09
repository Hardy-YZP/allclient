/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MessageWrapper
/*     */   implements DataInput
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MessageWrapper.java";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.headers.internal.MessageWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MessageWrapper.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private static Class<?> MQMessageWrapperClass = null;
/*  60 */   private static Constructor<?> MQMessageWrapperInputConstructor = null;
/*  61 */   private static Constructor<?> MQMessageWrapperOutputConstructor = null;
/*     */   
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry("com.ibm.mq.headers.internal.MessageWrapper", "static()");
/*     */     }
/*     */     try {
/*  68 */       MQMessageWrapperClass = Class.forName("com.ibm.mq.headers.internal.MQMessageWrapper");
/*     */     }
/*  70 */     catch (ClassNotFoundException e) {
/*  71 */       if (Trace.isOn) {
/*  72 */         Trace.catchBlock("com.ibm.mq.headers.internal.MessageWrapper", "static()", e, 1);
/*     */       }
/*     */     } 
/*     */     
/*  76 */     if (MQMessageWrapperClass != null) {
/*     */       try {
/*  78 */         MQMessageWrapperInputConstructor = MQMessageWrapperClass.getConstructor(new Class[] { DataInput.class });
/*  79 */         MQMessageWrapperOutputConstructor = MQMessageWrapperClass.getConstructor(new Class[] { DataOutput.class });
/*     */       }
/*  81 */       catch (SecurityException e) {
/*  82 */         if (Trace.isOn) {
/*  83 */           Trace.catchBlock("com.ibm.mq.headers.internal.MessageWrapper", "static()", e, 2);
/*     */         
/*     */         }
/*     */       }
/*  87 */       catch (NoSuchMethodException e) {
/*  88 */         if (Trace.isOn) {
/*  89 */           Trace.catchBlock("com.ibm.mq.headers.internal.MessageWrapper", "static()", e, 3);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "static()");
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
/*     */   public static MessageWrapper wrap(DataInput message) throws MQDataException {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 113 */     if (message instanceof MessageWrapper) {
/* 114 */       MessageWrapper traceRet1 = (MessageWrapper)message;
/* 115 */       if (Trace.isOn) {
/* 116 */         Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataInput)", traceRet1, 1);
/*     */       }
/* 118 */       return traceRet1;
/*     */     } 
/*     */     
/* 121 */     if (MQMessageWrapperInputConstructor != null) {
/*     */       
/*     */       try {
/* 124 */         MessageWrapper traceRet2 = (MessageWrapper)MQMessageWrapperInputConstructor.newInstance(new Object[] { message });
/* 125 */         if (Trace.isOn) {
/* 126 */           Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataInput)", traceRet2, 2);
/*     */         }
/*     */         
/* 129 */         return traceRet2;
/*     */       }
/* 131 */       catch (Exception e) {
/* 132 */         if (Trace.isOn) {
/* 133 */           Trace.catchBlock("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataInput)", e);
/*     */         }
/*     */         
/* 136 */         MessageWrapper traceRet3 = new DataInputWrapper(message);
/* 137 */         if (Trace.isOn) {
/* 138 */           Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataInput)", traceRet3, 3);
/*     */         }
/*     */         
/* 141 */         return traceRet3;
/*     */       } 
/*     */     }
/*     */     
/* 145 */     MessageWrapper traceRet4 = new DataInputWrapper(message);
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataInput)", traceRet4, 4);
/*     */     }
/* 149 */     return traceRet4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MessageWrapper wrap(DataOutput message) {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataOutput)", new Object[] { message });
/*     */     }
/*     */     
/* 163 */     if (message instanceof MessageWrapper) {
/* 164 */       MessageWrapper traceRet1 = (MessageWrapper)message;
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataOutput)", traceRet1, 1);
/*     */       }
/*     */       
/* 169 */       return traceRet1;
/*     */     } 
/*     */     
/* 172 */     if (MQMessageWrapperOutputConstructor != null) {
/*     */       
/*     */       try {
/* 175 */         MessageWrapper traceRet2 = (MessageWrapper)MQMessageWrapperOutputConstructor.newInstance(new Object[] { message });
/* 176 */         if (Trace.isOn) {
/* 177 */           Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataOutput)", traceRet2, 2);
/*     */         }
/*     */         
/* 180 */         return traceRet2;
/*     */       }
/* 182 */       catch (Exception e) {
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.catchBlock("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataOutput)", e);
/*     */         }
/*     */         
/* 187 */         MessageWrapper traceRet3 = new DataOutputWrapper(message);
/* 188 */         if (Trace.isOn) {
/* 189 */           Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataOutput)", traceRet3, 3);
/*     */         }
/*     */         
/* 192 */         return traceRet3;
/*     */       } 
/*     */     }
/*     */     
/* 196 */     MessageWrapper traceRet4 = new DataOutputWrapper(message);
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit("com.ibm.mq.headers.internal.MessageWrapper", "wrap(DataOutput)", traceRet4, 4);
/*     */     }
/* 200 */     return traceRet4;
/*     */   }
/*     */   
/*     */   public abstract int getMessageType() throws IOException;
/*     */   
/*     */   public abstract int getDataOffset() throws IOException;
/*     */   
/*     */   public abstract void seek(int paramInt) throws IOException;
/*     */   
/*     */   public abstract void writeBytes(String paramString) throws IOException;
/*     */   
/*     */   public abstract int readInt() throws IOException;
/*     */   
/*     */   public abstract void writeString(String paramString) throws IOException;
/*     */   
/*     */   public abstract void writeInt(int paramInt) throws IOException;
/*     */   
/*     */   public abstract int getCharacterSet();
/*     */   
/*     */   public abstract int getEncoding();
/*     */   
/*     */   public abstract Store getStore(int paramInt1, int paramInt2, int paramInt3) throws IOException;
/*     */   
/*     */   public abstract DataOutput getReversed();
/*     */   
/*     */   public abstract void readFully(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void readFully(byte[] paramArrayOfbyte) throws IOException;
/*     */   
/*     */   public abstract void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void write(byte[] paramArrayOfbyte) throws IOException;
/*     */   
/*     */   public abstract String getFormat();
/*     */   
/*     */   public abstract int getTotalMessageLength();
/*     */   
/*     */   public abstract void shuffle(int paramInt1, int paramInt2, int paramInt3) throws IOException;
/*     */   
/*     */   public abstract void resizeBuffer(int paramInt) throws IOException;
/*     */   
/*     */   public abstract boolean isMQMessage();
/*     */   
/*     */   public abstract Object getDelegate();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MessageWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */