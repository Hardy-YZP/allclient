/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.util.SerializationValidator;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JmsObjectInputStream
/*     */   extends ObjectInputStream
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsObjectInputStream.java";
/*     */   
/*     */   static {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.data("com.ibm.msg.client.jms.internal.JmsObjectInputStream", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsObjectInputStream.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean recursiveCall = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsObjectInputStream(InputStream inputStream) throws IOException {
/*  87 */     super(inputStream);
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectInputStream", "<init>(InputStream)", new Object[] { inputStream });
/*     */     }
/*     */     
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectInputStream", "<init>(InputStream)");
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
/*     */   protected Class<?> resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectInputStream", "resolveClass(ObjectStreamClass)", new Object[] { v });
/*     */     }
/*     */ 
/*     */     
/* 110 */     String className = v.getName();
/*     */ 
/*     */     
/* 113 */     Class<?> primitiveClass = SerializationValidator.getPrimitive(className);
/* 114 */     if (primitiveClass != null) {
/* 115 */       if (Trace.isOn) {
/* 116 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectInputStream", "resolveClass(ObjectStreamClass)", primitiveClass, 1);
/*     */       }
/*     */       
/* 119 */       return primitiveClass;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (!this.recursiveCall) {
/* 128 */       Trace.traceData(this, "First object in this stream, so we will call the validation. Classname is ", className);
/* 129 */       SerializationValidator.getInstance().validateClassName(className, SerializationValidator.Mode.DESERIALIZE);
/* 130 */       this.recursiveCall = true;
/*     */     } else {
/*     */       
/* 133 */       Trace.traceData(this, "Not first object in this stream, so we will not call the validation. Classname is ", className);
/*     */     } 
/*     */     
/* 136 */     Class<?> traceRet1 = CSSystem.dynamicLoadClass(className, getClass(), false);
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectInputStream", "resolveClass(ObjectStreamClass)", traceRet1, 2);
/*     */     }
/*     */     
/* 141 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsObjectInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */