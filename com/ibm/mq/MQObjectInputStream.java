/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.StreamCorruptedException;
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
/*     */ class MQObjectInputStream
/*     */   extends ObjectInputStream
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQObjectInputStream.java";
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.MQObjectInputStream", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQObjectInputStream.java");
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
/*  57 */   private static HashMap<String, Class<?>> primitiveClasses = new HashMap<>();
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry("com.ibm.mq.MQObjectInputStream", "static()");
/*     */     }
/*  62 */     primitiveClasses.put("boolean", boolean.class);
/*  63 */     primitiveClasses.put("char", char.class);
/*  64 */     primitiveClasses.put("byte", byte.class);
/*  65 */     primitiveClasses.put("short", short.class);
/*  66 */     primitiveClasses.put("int", int.class);
/*  67 */     primitiveClasses.put("long", long.class);
/*  68 */     primitiveClasses.put("float", float.class);
/*  69 */     primitiveClasses.put("double", double.class);
/*  70 */     primitiveClasses.put("void", void.class);
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit("com.ibm.mq.MQObjectInputStream", "static()");
/*     */     }
/*     */   }
/*     */   
/*  76 */   ClassLoader cl = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQObjectInputStream(ClassLoader cl) throws IOException, SecurityException {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.mq.MQObjectInputStream", "<init>(ClassLoader)", new Object[] { cl });
/*     */     }
/*     */     
/*  93 */     this.cl = cl;
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.MQObjectInputStream", "<init>(ClassLoader)");
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
/* 111 */     super(in);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.MQObjectInputStream", "<init>(InputStream,ClassLoader)", new Object[] { in, cl });
/*     */     }
/*     */     
/* 116 */     this.cl = cl;
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.MQObjectInputStream", "<init>(InputStream,ClassLoader)");
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
/*     */   protected Class<?> resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.MQObjectInputStream", "resolveClass(ObjectStreamClass)", new Object[] { v });
/*     */     }
/*     */     
/* 148 */     String fid = "resolveClass(ObjectStreamClass)";
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.data(this, "resolveClass(ObjectStreamClass)", "v = ", v.toString());
/*     */     }
/*     */     try {
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.data(this, "resolveClass(ObjectStreamClass)", "Attempting to resolve class ", v.getName() + " using forName() with the classloader " + this.cl);
/*     */       }
/* 156 */       Class<?> traceRet1 = Class.forName(v.getName(), true, this.cl);
/*     */       
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.exit(this, "com.ibm.mq.MQObjectInputStream", "resolveClass(ObjectStreamClass)", traceRet1, 1);
/*     */       }
/*     */       
/* 162 */       return traceRet1;
/*     */     
/*     */     }
/* 165 */     catch (ClassNotFoundException e) {
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.catchBlock(this, "com.ibm.mq.MQObjectInputStream", "resolveClass(ObjectStreamClass)", e, 1);
/*     */       }
/*     */ 
/*     */       
/* 171 */       Class<?> returncl = primitiveClasses.get(v.getName());
/* 172 */       if (returncl != null) {
/* 173 */         if (Trace.isOn) {
/* 174 */           Trace.exit(this, "com.ibm.mq.MQObjectInputStream", "resolveClass(ObjectStreamClass)", returncl, 2);
/*     */         }
/*     */         
/* 177 */         return returncl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 211 */       if (Trace.isOn)
/* 212 */         Trace.finallyBlock(this, "com.ibm.mq.MQObjectInputStream", "resolveClass(ObjectStreamClass)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQObjectInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */