/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
/*     */ import com.ibm.msg.client.commonservices.trace.TraceFormatter;
/*     */ import com.ibm.msg.client.commonservices.trace.TraceHandler;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.HashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogManager;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.logging.MemoryHandler;
/*     */ import java.util.logging.StreamHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinimalTracer
/*     */   implements CSPTrace
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/MinimalTracer.java";
/*     */   private Logger logger;
/*     */   private LogManager manager;
/*     */   private MemoryHandler memHandler;
/*     */   private OutputStream outputStream;
/*     */   private String parentClassName;
/*     */   private StreamHandler sHandler;
/*     */   
/*     */   public MinimalTracer() {
/*  99 */     this.manager = LogManager.getLogManager();
/* 100 */     this.logger = Logger.getLogger("com.ibm.msg.client.commonservices.j2se.MinimalTracer");
/*     */ 
/*     */     
/* 103 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 107 */             MinimalTracer.this.logger.setLevel(Level.ALL);
/* 108 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 113 */     this.outputStream = new ByteArrayOutputStream();
/* 114 */     this.sHandler = new StreamHandler(this.outputStream, new DefaultFormatter());
/*     */ 
/*     */     
/* 117 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           
/*     */           public Object run()
/*     */           {
/* 122 */             MinimalTracer.this.sHandler.setLevel(Level.ALL);
/* 123 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     this.memHandler = new MemoryHandler(this.sHandler, 20, Level.SEVERE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 142 */             MinimalTracer.this.logger.addHandler(MinimalTracer.this.memHandler);
/* 143 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void catchBlock(int level, Object parentClass, String parentClassName2, String methodSignature, Throwable thrown, int exitIndex) {
/* 156 */     if (parentClass instanceof String) {
/* 157 */       this.parentClassName = (String)parentClass;
/*     */     } else {
/* 159 */       this
/* 160 */         .parentClassName = parentClass.getClass().getPackage().getName() + '.' + parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 163 */     this.logger.logp(Level.FINE, this.parentClassName, (exitIndex == -1) ? methodSignature : (methodSignature + "<exitIndex " + exitIndex + ">"), "CATCH_BLOCK", thrown);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 174 */     this.manager.reset();
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
/*     */   public void dumpFFSTTrace(String outputFileName) {
/* 187 */     this.memHandler.push();
/* 188 */     this.sHandler.flush();
/*     */     
/* 190 */     byte[] bytes = ((ByteArrayOutputStream)this.outputStream).toByteArray();
/*     */ 
/*     */     
/* 193 */     FileOutputStream fOut = null;
/*     */     try {
/* 195 */       fOut = new FileOutputStream(outputFileName, true);
/*     */       
/*     */       try {
/* 198 */         fOut.write(bytes);
/* 199 */         fOut.close();
/*     */       }
/* 201 */       catch (IOException ioe) {
/* 202 */         System.err.print("\nCould not write to file. ");
/* 203 */         throw ioe;
/*     */       }
/*     */     
/* 206 */     } catch (Exception e) {
/* 207 */       System.err.println("Caught exception: " + e);
/* 208 */       e.printStackTrace();
/* 209 */       System.err.println("Could not write data to " + outputFileName);
/* 210 */       System.err.println("Writing data to System.err instead:");
/* 211 */       System.err.println(new String(bytes));
/*     */     } finally {
/*     */       
/*     */       try {
/* 215 */         if (fOut != null) {
/* 216 */           fOut.close();
/*     */         }
/*     */       }
/* 219 */       catch (Exception exception) {}
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
/*     */   public void finallyBlock(int level, Object parentClass, String parentClassName2, String methodSignature, int exitIndex) {
/* 234 */     if (parentClass instanceof String) {
/* 235 */       this.parentClassName = (String)parentClass;
/*     */     } else {
/* 237 */       this
/* 238 */         .parentClassName = parentClass.getClass().getPackage().getName() + '.' + parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 241 */     this.logger.logp(Level.FINE, this.parentClassName, (exitIndex == -1) ? methodSignature : (methodSignature + "<exitIndex " + exitIndex + ">"), "FINALLY_BLOCK");
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
/*     */   public void initialize() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void methodEntry(int level, Object parentClass, String parentClassName2, String methodSignature, Object[] parameters) {
/* 265 */     if (parentClass instanceof String) {
/* 266 */       this.parentClassName = (String)parentClass;
/*     */     } else {
/* 268 */       this
/* 269 */         .parentClassName = parentClass.getClass().getPackage().getName() + '.' + parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 272 */     this.logger.logp(Level.FINE, this.parentClassName, methodSignature, "METHOD_ENTRY");
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
/*     */   public void methodExit(int level, Object parentClass, String parentClassName2, String methodSignature, Object returnValue, int exitIndex) {
/* 285 */     if (parentClass instanceof String) {
/* 286 */       this.parentClassName = (String)parentClass;
/*     */     } else {
/* 288 */       this
/* 289 */         .parentClassName = parentClass.getClass().getPackage().getName() + '.' + parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 292 */     this.logger.logp(Level.FINE, this.parentClassName, (exitIndex == -1) ? methodSignature : (methodSignature + "<exitIndex " + exitIndex + ">"), "METHOD_EXIT");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTraceFormatter(TraceFormatter newFormatter) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTraceHandler(TraceHandler newHandler) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void throwing(int level, Object parentClass, String parentClassName2, String methodSignature, Throwable thrown, int exitIndex) {
/* 321 */     if (parentClass instanceof String) {
/* 322 */       this.parentClassName = (String)parentClass;
/*     */     } else {
/* 324 */       this
/* 325 */         .parentClassName = parentClass.getClass().getPackage().getName() + '.' + parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 328 */     this.logger.logp(Level.FINE, this.parentClassName, (exitIndex == -1) ? methodSignature : (methodSignature + "<exitIndex " + exitIndex + ">"), "THROWING");
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
/*     */   public void traceData(int level, Object parentClass, String parentClassName2, String methodSignature, String uniqueDescription, Object data) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, String header) {
/* 350 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTraceLevel() {
/* 358 */     return Integer.MAX_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTraceLevel(int traceLevel) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOutputFileName() {
/* 374 */     return "<none>";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\MinimalTracer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */