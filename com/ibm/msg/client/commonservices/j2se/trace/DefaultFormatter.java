/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
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
/*     */ public class DefaultFormatter
/*     */   extends Formatter
/*     */ {
/*  60 */   int maxTraceBytes = -1;
/*     */   private HashMap<String, MsgFormatter> msgFormatAdapters;
/*  62 */   static HashMap<Thread, Integer> indents = new HashMap<>();
/*     */   
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/DefaultFormatter.java";
/*     */   
/*     */   public DefaultFormatter() {
/*  67 */     this.msgFormatAdapters = new HashMap<>();
/*  68 */     this.msgFormatAdapters.put("METHOD_ENTRY", new EntryMsgFormatter());
/*  69 */     this.msgFormatAdapters.put("METHOD_EXIT", new ExitMsgFormatter());
/*  70 */     this.msgFormatAdapters.put("THROWING", new ThrowingMsgFormatter());
/*  71 */     this.msgFormatAdapters.put("CATCH_BLOCK", new CatchingMsgFormatter());
/*  72 */     this.msgFormatAdapters.put("FINALLY_BLOCK", new FinallyMsgFormatter());
/*  73 */     this.msgFormatAdapters.put("TRACE_DATA", new DataMsgFormatter());
/*  74 */     this.maxTraceBytes = DefaultTracer.getMaxTraceBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format(LogRecord record) {
/*  82 */     StringBuffer output = new StringBuffer(256);
/*     */     
/*  84 */     MsgFormatter formatter = this.msgFormatAdapters.get(record.getMessage());
/*  85 */     if (formatter == null) {
/*  86 */       formatter = this.msgFormatAdapters.get("TRACE_DATA");
/*     */     }
/*     */     
/*     */     try {
/*  90 */       formatter.formatMessage(record, output);
/*     */     }
/*  92 */     catch (Throwable t) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       output.append("\n");
/*     */ 
/*     */ 
/*     */       
/* 102 */       output.append(" *** Problem occurred formatting trace line: ");
/*     */       try {
/* 104 */         output.append(t);
/*     */       }
/* 106 */       catch (Throwable t2) {
/* 107 */         output.append(" Could not retrieve message from Throwable");
/*     */       } 
/* 109 */       output.append(" ***\n");
/*     */     } 
/*     */     
/* 112 */     String traceRet1 = output.toString();
/* 113 */     return traceRet1;
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
/*     */   abstract class MsgFormatter
/*     */   {
/* 136 */     private Date date = new Date();
/* 137 */     private Calendar calendar = new GregorianCalendar();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void appendBaseMessage(LogRecord record, StringBuffer output) {
/* 150 */       indent(output);
/* 151 */       appendTimestamp(output, record.getMillis());
/*     */       
/* 153 */       output.append('[' + Thread.currentThread().getName() + "] ");
/* 154 */       output.append(TraceUtils.foldPackageName(record.getSourceClassName()));
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
/*     */ 
/*     */     
/*     */     StringBuffer appendTimestamp(StringBuffer buffer, long millis) {
/* 172 */       this.date.setTime(millis);
/* 173 */       this.calendar.setTime(this.date);
/*     */       
/* 175 */       int h = this.calendar.get(11);
/* 176 */       int m = this.calendar.get(12);
/* 177 */       int s = this.calendar.get(13);
/*     */       
/* 179 */       if (h < 10) {
/* 180 */         buffer.append("0" + h + ":");
/*     */       } else {
/* 182 */         buffer.append(h + ":");
/*     */       } 
/*     */       
/* 185 */       if (m < 10) {
/* 186 */         buffer.append("0" + m + ":");
/*     */       } else {
/* 188 */         buffer.append(m + ":");
/*     */       } 
/*     */       
/* 191 */       if (s < 10) {
/* 192 */         buffer.append("0" + s + ":");
/*     */       } else {
/* 194 */         buffer.append(s + " ");
/*     */       } 
/*     */       
/* 197 */       buffer.append("[" + millis + "] ");
/*     */       
/* 199 */       return buffer;
/*     */     }
/*     */     
/*     */     final void decrementIndents() {
/* 203 */       int indentLevel = getIndentationLevel();
/* 204 */       if (indentLevel > 0) {
/* 205 */         indentLevel--;
/*     */       }
/* 207 */       setIndentationLevel(indentLevel);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void formatMessage(LogRecord param1LogRecord, StringBuffer param1StringBuffer);
/*     */ 
/*     */     
/*     */     final int getIndentationLevel() {
/* 216 */       int retVal = 0;
/* 217 */       if (DefaultFormatter.indents.containsKey(Thread.currentThread())) {
/* 218 */         Integer indentationLevel = DefaultFormatter.indents.get(Thread.currentThread());
/* 219 */         retVal = indentationLevel.intValue();
/*     */       } 
/*     */       
/* 222 */       return retVal;
/*     */     }
/*     */     
/*     */     final void incrementIndents() {
/* 226 */       int indentLevel = getIndentationLevel();
/* 227 */       indentLevel++;
/* 228 */       setIndentationLevel(indentLevel);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void indent(StringBuffer buffer) {
/* 239 */       int level = getIndentationLevel();
/* 240 */       for (int i = 0; i < level; i++) {
/* 241 */         buffer.append("  ");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     final void setIndentationLevel(int newLevel) {
/* 248 */       DefaultFormatter.indents.put(Thread.currentThread(), Integer.valueOf(newLevel));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class CatchingMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 258 */       appendBaseMessage(record, buffer);
/*     */       
/* 260 */       Throwable thrown = record.getThrown();
/*     */       
/* 262 */       if (thrown == null) {
/* 263 */         thrown = new Throwable("Unknown thowable");
/*     */       }
/*     */       
/* 266 */       buffer.append(' ' + record.getSourceMethodName() + ' ');
/* 267 */       buffer.append(thrown.getClass().getName());
/* 268 */       buffer.append(" exception caught: " + thrown.getMessage() + "\n");
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
/*     */   class DataMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 284 */       appendBaseMessage(record, buffer);
/* 285 */       buffer.append(' ');
/* 286 */       buffer.append(record.getMessage());
/* 287 */       buffer.append('\n');
/*     */       
/* 289 */       Object[] parameters = record.getParameters();
/* 290 */       if (parameters != null) {
/*     */         
/* 292 */         indent(buffer);
/* 293 */         buffer.append("Data:\n");
/*     */         
/* 295 */         Object parameter = null;
/* 296 */         for (int i = 0; i < parameters.length; i++) {
/* 297 */           parameter = parameters[i];
/*     */           
/* 299 */           if (parameter != null) {
/* 300 */             indent(buffer);
/* 301 */             buffer.append('\t');
/* 302 */             if (parameter instanceof byte[]) {
/* 303 */               buffer.append(TraceUtils.formatObjectDetailed(parameter, DefaultFormatter.this.maxTraceBytes));
/*     */             } else {
/*     */               
/* 306 */               buffer.append(parameter.toString());
/*     */             } 
/* 308 */             buffer.append('\n');
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class EntryMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 323 */       appendBaseMessage(record, buffer);
/* 324 */       buffer.append(" ==> ");
/* 325 */       buffer.append(record.getSourceMethodName() + " entry\n");
/*     */       
/* 327 */       incrementIndents();
/* 328 */       Object[] parameters = record.getParameters();
/*     */       
/* 330 */       if (parameters != null) {
/*     */         
/* 332 */         indent(buffer);
/* 333 */         buffer.append("params: \n");
/*     */         
/* 335 */         Object parameter = null;
/* 336 */         for (int i = 0; i < parameters.length; i++) {
/* 337 */           parameter = parameters[i];
/*     */           
/* 339 */           if (parameter != null) {
/* 340 */             indent(buffer);
/* 341 */             buffer.append('\t');
/* 342 */             buffer.append(parameter.getClass());
/* 343 */             buffer.append(" = ");
/*     */             
/* 345 */             if (parameter instanceof byte[]) {
/* 346 */               buffer.append(TraceUtils.bytesToHex((byte[])parameter, DefaultFormatter.this.maxTraceBytes));
/*     */             } else {
/*     */               
/* 349 */               buffer.append(parameter.toString());
/*     */             } 
/* 351 */             buffer.append('\n');
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class ExitMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 366 */       decrementIndents();
/* 367 */       appendBaseMessage(record, buffer);
/* 368 */       buffer.append(" <== ");
/* 369 */       buffer.append(record.getSourceMethodName() + " exit\n");
/*     */       
/* 371 */       Object[] returnVal = record.getParameters();
/*     */       
/* 373 */       if (returnVal != null && returnVal[0] != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 378 */         Object retValEntry = returnVal[0];
/* 379 */         indent(buffer);
/* 380 */         buffer.append(" return = ");
/* 381 */         if (retValEntry instanceof byte[]) {
/* 382 */           buffer.append(TraceUtils.bytesToHex((byte[])retValEntry, DefaultFormatter.this.maxTraceBytes));
/*     */         } else {
/* 384 */           buffer.append(retValEntry.toString());
/*     */         } 
/*     */         
/* 387 */         buffer.append('\n');
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class FinallyMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 397 */       appendBaseMessage(record, buffer);
/* 398 */       buffer.append(' ' + record.getSourceMethodName() + " finally\n");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class ThrowingMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void appendStackTrace(Throwable t, StringBuffer buffer) {
/* 409 */       StackTraceElement[] stack = t.getStackTrace();
/*     */       
/* 411 */       for (int i = 0; i < stack.length; i++) {
/* 412 */         indent(buffer);
/* 413 */         buffer.append("\tat " + stack[i] + '\n');
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 420 */       appendBaseMessage(record, buffer);
/*     */       
/* 422 */       Throwable thrown = record.getThrown();
/*     */       
/* 424 */       buffer.append(' ' + record.getSourceMethodName() + ' ');
/* 425 */       buffer.append(thrown.getClass().getName());
/* 426 */       buffer.append(" exception thrown: " + thrown.getMessage() + "\n");
/* 427 */       appendStackTrace(thrown, buffer);
/*     */       
/* 429 */       Throwable cause = thrown.getCause();
/*     */       
/* 431 */       while (cause != null) {
/* 432 */         incrementIndents();
/* 433 */         indent(buffer);
/* 434 */         buffer.append("\tCaused by " + cause.getClass().getName());
/* 435 */         buffer.append(" exception thrown: " + thrown.getMessage() + "\n");
/* 436 */         appendStackTrace(cause, buffer);
/* 437 */         decrementIndents();
/*     */         
/* 439 */         cause = cause.getCause();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\DefaultFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */