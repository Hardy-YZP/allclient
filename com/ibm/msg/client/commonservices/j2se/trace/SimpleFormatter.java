/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.TraceFormatter;
/*     */ import com.ibm.msg.client.commonservices.trace.TraceHandler;
/*     */ import com.ibm.msg.client.commonservices.trace.TraceRecord;
/*     */ import java.util.HashMap;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.LogRecord;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleFormatter
/*     */   extends Formatter
/*     */   implements TraceFormatter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/SimpleFormatter.java";
/*     */   protected static final String entryTag = "==>";
/*     */   protected static final String exitTag = "<==";
/*     */   protected static final String throwTag = "thr";
/*     */   protected static final String catchTag = "cat";
/*     */   protected static final String finallyTag = "fin";
/*     */   protected static final String dataTag = "dat";
/* 158 */   static HashMap<String, MessageFormatAdapter> messageFormatAdapters = new HashMap<>();
/*     */   static {
/* 160 */     messageFormatAdapters.put("CATCH_BLOCK", new CatchFormatAdapter());
/* 161 */     messageFormatAdapters.put("TRACE_DATA", new DataFormatAdapter());
/* 162 */     messageFormatAdapters.put("METHOD_ENTRY", new EntryFormatAdapter());
/* 163 */     messageFormatAdapters.put("METHOD_EXIT", new ExitFormatAdapter());
/* 164 */     messageFormatAdapters.put("FINALLY_BLOCK", new FinallyFormatAdapter());
/* 165 */     messageFormatAdapters.put("THROWING", new ThrowFormatAdapter());
/* 166 */   } static int maxTraceBytes = DefaultTracer.getMaxTraceBytes();
/*     */   
/*     */   static {
/* 169 */     maxTraceBytes = -1;
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
/*     */   public String format(LogRecord record) {
/* 188 */     StringBuffer output = new StringBuffer(256);
/* 189 */     MessageFormatAdapter adapter = messageFormatAdapters.get(record.getMessage());
/* 190 */     if (adapter == null) {
/* 191 */       adapter = messageFormatAdapters.get("TRACE_DATA");
/*     */     }
/*     */     
/*     */     try {
/* 195 */       adapter.doFormatMessage(record, output);
/*     */     }
/* 197 */     catch (Throwable t) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 203 */       output.append("\n");
/*     */ 
/*     */ 
/*     */       
/* 207 */       output.append(" *** Problem occurred formatting trace line: ");
/*     */       try {
/* 209 */         output.append(t);
/*     */       }
/* 211 */       catch (Throwable t2) {
/* 212 */         output.append(" Could not retrieve message from Throwable");
/*     */       } 
/* 214 */       output.append(" ***\n");
/*     */     } 
/*     */     
/* 217 */     String traceRet1 = output.toString();
/* 218 */     return traceRet1;
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
/*     */   static abstract class MessageFormatAdapter
/*     */   {
/*     */     abstract void doFormatMessage(LogRecord param1LogRecord, StringBuffer param1StringBuffer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void appendBaseMessage(LogRecord record, StringBuffer buffer) {
/* 241 */       buffer.append(record.getMillis());
/* 242 */       buffer.append('#');
/* 243 */       buffer.append(Thread.currentThread().getName());
/* 244 */       buffer.append('#');
/* 245 */       buffer.append(TraceUtils.foldPackageName(record.getSourceClassName()));
/* 246 */       buffer.append('#');
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
/*     */   static class EntryFormatAdapter
/*     */     extends MessageFormatAdapter
/*     */   {
/*     */     void doFormatMessage(LogRecord record, StringBuffer buffer) {
/* 262 */       appendBaseMessage(record, buffer);
/* 263 */       buffer.append("==>");
/* 264 */       buffer.append('#');
/* 265 */       buffer.append(record.getSourceMethodName());
/*     */       
/* 267 */       Object[] parameters = record.getParameters();
/* 268 */       if (parameters != null) {
/*     */ 
/*     */         
/* 271 */         Object parameter = null;
/* 272 */         StringBuilder value = null;
/*     */ 
/*     */         
/* 275 */         for (int i = 0; i < parameters.length; i++) {
/* 276 */           parameter = parameters[i];
/* 277 */           if (parameter != null) {
/* 278 */             value = TraceUtils.formatObject(parameter, SimpleFormatter.maxTraceBytes);
/* 279 */             int length = value.length();
/*     */             
/* 281 */             buffer.append('#');
/* 282 */             buffer.append(length);
/* 283 */             buffer.append('#');
/* 284 */             buffer.append(parameter.getClass().getPackage().getName());
/* 285 */             buffer.append('.');
/* 286 */             buffer.append(parameter.getClass().getName());
/* 287 */             buffer.append('#');
/* 288 */             buffer.append(value);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 293 */       buffer.append("##\n");
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
/*     */   static class ExitFormatAdapter
/*     */     extends MessageFormatAdapter
/*     */   {
/*     */     void doFormatMessage(LogRecord record, StringBuffer buffer) {
/* 309 */       appendBaseMessage(record, buffer);
/*     */       
/* 311 */       buffer.append("<==");
/* 312 */       buffer.append('#');
/* 313 */       buffer.append(record.getSourceMethodName());
/*     */       
/* 315 */       Object[] parameters = record.getParameters();
/* 316 */       if (parameters != null && parameters[0] != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 321 */         Object parameter = parameters[0];
/* 322 */         StringBuilder value = TraceUtils.formatObject(parameter, SimpleFormatter.maxTraceBytes);
/* 323 */         int length = value.length();
/*     */         
/* 325 */         buffer.append('#');
/* 326 */         buffer.append(length);
/* 327 */         buffer.append('#');
/* 328 */         buffer.append(parameter.getClass().getName());
/* 329 */         buffer.append('#');
/* 330 */         buffer.append(value);
/*     */       } 
/*     */       
/* 333 */       buffer.append("##\n");
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
/*     */   static class ThrowFormatAdapter
/*     */     extends MessageFormatAdapter
/*     */   {
/*     */     void doFormatMessage(LogRecord record, StringBuffer buffer) {
/* 351 */       appendBaseMessage(record, buffer);
/*     */       
/* 353 */       buffer.append("thr");
/* 354 */       buffer.append('#');
/* 355 */       buffer.append(record.getSourceMethodName());
/* 356 */       buffer.append('#');
/*     */       
/* 358 */       Throwable thrown = record.getThrown();
/*     */       
/* 360 */       buffer.append(thrown.getClass().getName());
/* 361 */       buffer.append('#');
/* 362 */       buffer.append(thrown.getMessage());
/* 363 */       appendStackTrace(thrown, buffer);
/*     */       
/* 365 */       Throwable cause = thrown.getCause();
/*     */       
/* 367 */       while (cause != null) {
/* 368 */         buffer.append("#cause#");
/* 369 */         buffer.append(cause.getClass().getName());
/* 370 */         buffer.append('#');
/* 371 */         buffer.append(thrown.getMessage());
/* 372 */         appendStackTrace(cause, buffer);
/*     */         
/* 374 */         cause = cause.getCause();
/*     */       } 
/*     */       
/* 377 */       buffer.append("##\n");
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void appendStackTrace(Throwable t, StringBuffer buffer) {
/* 393 */       StackTraceElement[] stack = t.getStackTrace();
/*     */       
/* 395 */       for (int i = 0; i < stack.length; i++) {
/* 396 */         buffer.append('#');
/* 397 */         buffer.append(stack[i]);
/*     */       } 
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
/*     */   static class CatchFormatAdapter
/*     */     extends MessageFormatAdapter
/*     */   {
/*     */     void doFormatMessage(LogRecord record, StringBuffer buffer) {
/* 413 */       appendBaseMessage(record, buffer);
/*     */       
/* 415 */       buffer.append("cat");
/* 416 */       buffer.append('#');
/* 417 */       buffer.append(record.getSourceMethodName());
/* 418 */       buffer.append('#');
/*     */       
/* 420 */       Throwable thrown = record.getThrown();
/* 421 */       if (thrown == null) {
/* 422 */         thrown = new Throwable("Unknown Throwable");
/*     */       }
/*     */       
/* 425 */       buffer.append(thrown.getClass().getName());
/* 426 */       buffer.append('#');
/* 427 */       buffer.append(thrown.getMessage());
/*     */       
/* 429 */       buffer.append("##\n");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class FinallyFormatAdapter
/*     */     extends MessageFormatAdapter
/*     */   {
/*     */     void doFormatMessage(LogRecord record, StringBuffer buffer) {
/* 442 */       appendBaseMessage(record, buffer);
/*     */       
/* 444 */       buffer.append("fin");
/* 445 */       buffer.append('#');
/* 446 */       buffer.append(record.getSourceMethodName());
/*     */       
/* 448 */       buffer.append("##\n");
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
/*     */   static class DataFormatAdapter
/*     */     extends MessageFormatAdapter
/*     */   {
/*     */     void doFormatMessage(LogRecord record, StringBuffer buffer) {
/* 467 */       appendBaseMessage(record, buffer);
/*     */       
/* 469 */       buffer.append("dat");
/* 470 */       buffer.append('#');
/* 471 */       buffer.append(record.getMessage().length());
/* 472 */       buffer.append('#');
/* 473 */       buffer.append(record.getMessage());
/*     */       
/* 475 */       Object[] parameters = record.getParameters();
/* 476 */       if (parameters != null) {
/*     */         
/* 478 */         Object parameter = null;
/* 479 */         StringBuilder value = null;
/* 480 */         int length = 0;
/*     */         
/* 482 */         for (int i = 0; i < parameters.length; i++) {
/* 483 */           parameter = parameters[i];
/*     */           
/* 485 */           if (parameter != null) {
/* 486 */             value = TraceUtils.formatObject(parameter, SimpleFormatter.maxTraceBytes);
/* 487 */             length = value.length();
/*     */             
/* 489 */             buffer.append('#');
/* 490 */             buffer.append(length);
/* 491 */             buffer.append('#');
/* 492 */             buffer.append(value);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 497 */       buffer.append("##\n");
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
/*     */   public String format(TraceRecord record) {
/* 509 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHead(TraceHandler h) {
/* 518 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTail(TraceHandler h) {
/* 527 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\SimpleFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */