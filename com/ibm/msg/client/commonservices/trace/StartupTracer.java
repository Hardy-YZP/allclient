/*     */ package com.ibm.msg.client.commonservices.trace;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class StartupTracer
/*     */   implements CSPTrace
/*     */ {
/*  53 */   private Date date = new Date();
/*  54 */   private Calendar calendar = new GregorianCalendar();
/*     */   
/*  56 */   private PrintStream stream = Trace.errorStream;
/*     */   private StartupTracer() {
/*  58 */     StringBuffer buffer = getBaseMessage(this);
/*  59 */     buffer.append("Startup Trace Initalized.... STARTUP_INIT");
/*  60 */     this.stream.println(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/StartupTracer.java";
/*     */   
/*     */   private static StartupTracer startupTracer;
/*     */ 
/*     */   
/*     */   public static StartupTracer getInstance() {
/*  70 */     if (startupTracer == null) {
/*  71 */       startupTracer = new StartupTracer();
/*     */     }
/*     */     
/*  74 */     return startupTracer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void catchBlock(int level, Object parentClass, String parentClassName, String methodSignature, Throwable thrownP, int exitIndex) {
/*  84 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/*  86 */     Throwable thrown = thrownP;
/*  87 */     if (thrown == null) {
/*  88 */       thrown = new Throwable("Unknown throwable");
/*     */     }
/*     */     
/*  91 */     output.append(methodSignature + " ");
/*  92 */     if (exitIndex != -1) {
/*  93 */       output.append("<exitIndex " + exitIndex + "> ");
/*     */     }
/*  95 */     output.append(thrown.getClass().getName());
/*  96 */     output.append(" caught: " + thrown.getMessage());
/*     */     
/*  98 */     appendStackTrace(thrown, output);
/*  99 */     this.stream.println(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finallyBlock(int level, Object parentClass, String parentClassName, String methodSignature, int exitIndex) {
/* 109 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/* 111 */     output.append(methodSignature);
/* 112 */     if (exitIndex != -1) {
/* 113 */       output.append(" <exitIndex " + exitIndex + ">");
/*     */     }
/* 115 */     output.append(" finally");
/*     */     
/* 117 */     this.stream.println(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void methodEntry(int level, Object parentClass, String parentClassName, String methodSignature, Object[] parameters) {
/* 127 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/* 129 */     output.append("==> ");
/* 130 */     output.append(methodSignature + " entry ");
/*     */     
/* 132 */     incrementIndents();
/*     */     
/* 134 */     if (parameters != null) {
/* 135 */       indent(output);
/* 136 */       output.append("params:");
/*     */       
/* 138 */       Object parameter = null;
/* 139 */       for (int i = 0; i < parameters.length; i++) {
/* 140 */         parameter = parameters[i];
/*     */         
/* 142 */         if (parameter != null) {
/* 143 */           if (i > 0) {
/* 144 */             indent(output);
/*     */           }
/* 146 */           output.append('\t');
/* 147 */           output.append(parameter.getClass());
/* 148 */           output.append(" = ");
/* 149 */           output.append(parameter.toString());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 155 */     this.stream.println(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void methodExit(int level, Object parentClass, String parentClassName, String methodSignature, Object returnValue, int exitIndex) {
/* 166 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/* 168 */     decrementIndents();
/* 169 */     output.append("<== ");
/* 170 */     output.append(methodSignature);
/* 171 */     if (exitIndex != -1) {
/* 172 */       output.append(" <exitIndex " + exitIndex + "> ");
/*     */     }
/* 174 */     output.append("exit ");
/*     */     
/* 176 */     if (returnValue != null) {
/* 177 */       indent(output);
/* 178 */       output.append(" return = ");
/* 179 */       output.append(returnValue.toString());
/*     */     } 
/*     */ 
/*     */     
/* 183 */     this.stream.println(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void throwing(int level, Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) {
/* 194 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/* 196 */     output.append(methodSignature + " ");
/* 197 */     if (exitIndex != -1) {
/* 198 */       output.append(" <exitIndex " + exitIndex + "> ");
/*     */     }
/* 200 */     output.append(thrown.getClass().getName());
/* 201 */     output.append(" exception thrown: " + thrown.getMessage());
/*     */ 
/*     */     
/* 204 */     Throwable cause = thrown.getCause();
/*     */     
/* 206 */     while (cause != null) {
/* 207 */       incrementIndents();
/* 208 */       indent(output);
/* 209 */       output.append("\tCaused by " + cause.getClass().getName());
/* 210 */       output.append(" exception thrown: " + thrown.getMessage());
/* 211 */       appendStackTrace(cause, output);
/* 212 */       decrementIndents();
/*     */       
/* 214 */       cause = cause.getCause();
/*     */     } 
/*     */     
/* 217 */     this.stream.println(output);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void appendStackTrace(Throwable t, StringBuffer buffer) {
/* 223 */     StackTraceElement[] stack = t.getStackTrace();
/*     */     
/* 225 */     for (int i = 0; i < stack.length; i++) {
/* 226 */       indent(buffer);
/* 227 */       buffer.append("\tat " + stack[i] + '\n');
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
/*     */   public void traceData(int level, Object parentClass, String parentClassName, String methodSignature, String uniqueDescription, Object data) {
/* 239 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/* 241 */     output.append(' ');
/* 242 */     output.append(uniqueDescription);
/*     */ 
/*     */     
/* 245 */     if (data != null) {
/* 246 */       indent(output);
/* 247 */       output.append(" Data==>");
/* 248 */       output.append(data.toString());
/*     */     } 
/*     */     
/* 251 */     this.stream.println(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void indent(StringBuffer output) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void incrementIndents() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decrementIndents() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
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
/*     */   public void setTraceFormatter(TraceFormatter newFormatter) {}
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
/*     */   private StringBuffer getBaseMessage(Object parentClass) {
/* 322 */     StringBuffer output = new StringBuffer();
/* 323 */     appendTimestamp(output, System.currentTimeMillis());
/*     */     
/* 325 */     String className = null;
/* 326 */     if (parentClass instanceof String) {
/* 327 */       className = (String)parentClass;
/* 328 */     } else if (parentClass != null) {
/* 329 */       className = parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 332 */     output.append('[' + Thread.currentThread().getName() + "] ");
/* 333 */     output.append(className);
/* 334 */     output.append(' ');
/*     */     
/* 336 */     return output;
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
/*     */   StringBuffer appendTimestamp(StringBuffer buffer, long millis) {
/* 353 */     this.date.setTime(millis);
/* 354 */     this.calendar.setTime(this.date);
/*     */     
/* 356 */     int h = this.calendar.get(11);
/* 357 */     int m = this.calendar.get(12);
/* 358 */     int s = this.calendar.get(13);
/*     */     
/* 360 */     if (h < 10) {
/* 361 */       buffer.append("0" + h + ":");
/*     */     } else {
/* 363 */       buffer.append(h + ":");
/*     */     } 
/*     */     
/* 366 */     if (m < 10) {
/* 367 */       buffer.append("0" + m + ":");
/*     */     } else {
/* 369 */       buffer.append(m + ":");
/*     */     } 
/*     */     
/* 372 */     if (s < 10) {
/* 373 */       buffer.append("0" + s + ":");
/*     */     } else {
/* 375 */       buffer.append(s + " ");
/*     */     } 
/*     */     
/* 378 */     buffer.append("[" + millis + "] ");
/*     */     
/* 380 */     return buffer;
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
/*     */   public String ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, String header) {
/* 393 */     this.stream.println(header);
/* 394 */     return "FFST called for " + probeID + " @ " + sourceClass + ":" + methodSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTraceLevel() {
/* 403 */     return Integer.MAX_VALUE;
/*     */   }
/*     */ 
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
/* 420 */     return "<System.err>";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\StartupTracer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */