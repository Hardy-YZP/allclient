/*     */ package com.ibm.msg.client.commonservices.trace;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NullTracer
/*     */   implements CSPTrace
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/NullTracer.java";
/*     */   
/*     */   public void catchBlock(Object parentClass, String methodSignature, Throwable thrown, int exitIndex) {}
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public void finallyBlock(Object parentClass, String methodSignature, int exitIndex) {}
/*     */   
/*     */   public void initialize() {}
/*     */   
/*     */   public void methodEntry(int level, Object parentClass, String methodSignature, Object[] parameters) {}
/*     */   
/*     */   public void methodExit(int level, Object parentClass, String methodSignature, Object returnValue, int exitIndex) {}
/*     */   
/*     */   public void setTraceFormatter(TraceFormatter newFormatter) {}
/*     */   
/*     */   public void setTraceHandler(TraceHandler newHandler) {}
/*     */   
/*     */   public void throwing(int level, Object parentClass, String methodSignature, Throwable thrown, int exitIndex) {}
/*     */   
/*     */   public String ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, String header) {
/* 136 */     System.err.flush();
/* 137 */     System.err.println("FFDC called in uninitialized Trace module\n");
/* 138 */     System.err.println(header);
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void catchBlock(int level, Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finallyBlock(int level, Object parentClass, String parentClassName, String methodSignature, int exitIndex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void methodEntry(int level, Object parentClass, String parentClassName, String methodSignature, Object[] parameters) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void methodExit(int level, Object parentClass, String parentClassName, String methodSignature, Object returnValue, int exitIndex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void throwing(int level, Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void traceData(int level, Object parentClass, String parentClassName, String methodSignature, String uniqueDescription, Object data) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTraceLevel() {
/* 201 */     return 0;
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
/* 217 */     return "<none>";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\NullTracer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */