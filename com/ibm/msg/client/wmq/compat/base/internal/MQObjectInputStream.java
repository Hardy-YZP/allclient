/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.util.SerializationValidator;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.StreamCorruptedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQObjectInputStream
/*     */   extends ObjectInputStream
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQObjectInputStream.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQObjectInputStream.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean recursiveCall = false;
/*     */ 
/*     */   
/*  68 */   ClassLoader cl = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQObjectInputStream(ClassLoader cl) throws IOException, SecurityException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "<init>(ClassLoader)", new Object[] { cl });
/*     */     }
/*     */     
/*  78 */     this.cl = cl;
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "<init>(ClassLoader)");
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
/*     */   public MQObjectInputStream(InputStream in, ClassLoader cl) throws IOException, StreamCorruptedException {
/*  95 */     super(in);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "<init>(InputStream,ClassLoader)", new Object[] { in, cl });
/*     */     }
/*     */     
/* 100 */     this.cl = cl;
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "<init>(InputStream,ClassLoader)");
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
/*     */   protected Class<?> resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", new Object[] { v });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     Class<?> returncl = SerializationValidator.getPrimitive(v.getName());
/* 126 */     if (returncl != null) {
/* 127 */       if (Trace.isOn) {
/* 128 */         Trace.traceData(this, "Resolved as primitive!", null);
/*     */       }
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", returncl, 2);
/*     */       }
/*     */       
/* 134 */       return returncl;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (!this.recursiveCall) {
/* 143 */       Trace.traceData(this, "First object in this stream, so we will call the validation. Classname is ", v.getName());
/* 144 */       SerializationValidator.getInstance().validateClassName(v.getName(), SerializationValidator.Mode.DESERIALIZE);
/* 145 */       this.recursiveCall = true;
/*     */     } else {
/*     */       
/* 148 */       Trace.traceData(this, "Not first object in this stream, so we will not call the validation. Classname is ", v.getName());
/*     */     } 
/*     */     
/*     */     try {
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.traceData(this, "Attempting to resolve class " + v.getName() + " using forName() with the classloader " + this.cl, null);
/*     */       }
/*     */       
/* 156 */       Class<?> traceRet1 = CSSystem.dynamicLoadClass(v.getName(), getClass(), false);
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", traceRet1, 1);
/*     */       }
/*     */       
/* 161 */       return traceRet1;
/*     */     }
/* 163 */     catch (ClassNotFoundException e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", e, 1);
/*     */       }
/*     */       
/*     */       try {
/* 169 */         if (Trace.isOn) {
/* 170 */           Trace.traceData(this, "forName() failed to resolve class " + v.getName() + " with classloader " + this.cl, null);
/*     */           
/* 172 */           Trace.traceData(this, "Now attempting to resolve class " + v.getName() + " using forName() with the classloader " + 
/* 173 */               getClass().getClassLoader(), null);
/*     */         } 
/* 175 */         Class<?> traceRet2 = CSSystem.dynamicLoadClass(v.getName(), getClass(), false);
/* 176 */         if (Trace.isOn) {
/* 177 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", traceRet2, 3);
/*     */         }
/*     */         
/* 180 */         return traceRet2;
/*     */       }
/* 182 */       catch (ClassNotFoundException ex) {
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", ex, 2);
/*     */         }
/*     */ 
/*     */         
/* 188 */         if (Trace.isOn) {
/* 189 */           Trace.traceData(this, "forName() failed to resolve class " + v.getName() + " with classloader " + 
/* 190 */               getClass().getClassLoader(), null);
/* 191 */           Trace.traceData(this, "Attempting to resolve class " + v.getName() + " using loadClass() with the classloader " + this.cl, null);
/*     */         } 
/*     */         
/* 194 */         Class<?> traceRet3 = this.cl.loadClass(v.getName());
/* 195 */         if (Trace.isOn) {
/* 196 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream", "resolveClass(ObjectStreamClass)", traceRet3, 4);
/*     */         }
/*     */         
/* 199 */         return traceRet3;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQObjectInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */