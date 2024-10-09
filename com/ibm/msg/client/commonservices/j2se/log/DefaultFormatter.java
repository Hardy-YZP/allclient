/*     */ package com.ibm.msg.client.commonservices.j2se.log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Utils;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class DefaultFormatter
/*     */   extends Formatter
/*     */ {
/*     */   private static String lineSeparator;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/DefaultFormatter.java");
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
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry("com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "static()");
/*     */     }
/*  72 */     String lineSeparatorProperty = "line.separator";
/*     */ 
/*     */     
/*  75 */     PropertyStore.register(lineSeparatorProperty, "\n");
/*     */ 
/*     */     
/*  78 */     lineSeparator = PropertyStore.getStringProperty(lineSeparatorProperty);
/*  79 */   } private static final String LINE = "--------------------------------------------------------------------" + lineSeparator; static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/DefaultFormatter.java";
/*     */   static {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit("com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private HashMap<String, MsgFormatter> msgFormatAdapters;
/*     */ 
/*     */   
/*     */   class LogMsgFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/*  94 */       if (Trace.isOn) {
/*  95 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogMsgFormatter", "formatMessage(LogRecord,StringBuffer)", new Object[] { record, buffer });
/*     */       }
/*     */       
/*  98 */       String messageID = record.getMessage();
/*  99 */       Object[] parameters = record.getParameters();
/* 100 */       HashMap<String, ? extends Object> parameter = null;
/* 101 */       HashMap<String, Object> parameter2 = null;
/*     */ 
/*     */ 
/*     */       
/* 105 */       if (parameters != null && parameters[0] != null) {
/* 106 */         assert parameters[0] instanceof HashMap : parameters[false];
/* 107 */         parameter = DefaultFormatter.getParameterMap(parameters);
/*     */ 
/*     */         
/* 110 */         parameter2 = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 116 */         for (Map.Entry<String, ? extends Object> parameterEntry : parameter.entrySet()) {
/* 117 */           String key = parameterEntry.getKey();
/* 118 */           Object value = parameterEntry.getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 124 */           if (value instanceof byte[]) {
/* 125 */             value = Utils.formatObjectDetailed(value, DefaultFormatter.this.maxTraceBytes).toString();
/*     */           }
/*     */           
/* 128 */           parameter2.put(key, value);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 134 */       String message = NLSServices.getMessage(messageID, parameter2);
/* 135 */       String explanation = NLSServices.getExplanation(messageID, parameter2);
/* 136 */       String action = NLSServices.getUserAction(messageID, parameter2);
/*     */       
/* 138 */       appendBaseMessage(record, buffer);
/* 139 */       buffer.append(DefaultFormatter.lineSeparator);
/*     */       
/* 141 */       buffer.append(message);
/* 142 */       buffer.append(DefaultFormatter.lineSeparator + DefaultFormatter.lineSeparator);
/*     */       
/* 144 */       buffer.append(NLSServices.getMessage("JMSCS0007"));
/* 145 */       buffer.append(DefaultFormatter.lineSeparator);
/* 146 */       buffer.append(explanation);
/* 147 */       buffer.append(DefaultFormatter.lineSeparator + DefaultFormatter.lineSeparator);
/*     */       
/* 149 */       buffer.append(NLSServices.getMessage("JMSCS0008"));
/* 150 */       buffer.append(DefaultFormatter.lineSeparator);
/* 151 */       buffer.append(action);
/* 152 */       buffer.append(DefaultFormatter.lineSeparator);
/* 153 */       buffer.append(DefaultFormatter.LINE);
/* 154 */       if (Trace.isOn) {
/* 155 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogMsgFormatter", "formatMessage(LogRecord,StringBuffer)");
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
/*     */   class LogMsgNLSFormatter
/*     */     extends MsgFormatter
/*     */   {
/*     */     void formatMessage(LogRecord record, StringBuffer buffer) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogMsgNLSFormatter", "formatMessage(LogRecord,StringBuffer)", new Object[] { record, buffer });
/*     */       }
/*     */ 
/*     */       
/* 176 */       Object[] parameters = record.getParameters();
/* 177 */       String parameter = null;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (parameters != null) {
/* 183 */         assert parameters[0] instanceof String : parameters[false];
/* 184 */         parameter = (String)parameters[0];
/*     */         
/* 186 */         appendBaseMessage(record, buffer);
/* 187 */         buffer.append(DefaultFormatter.lineSeparator);
/*     */ 
/*     */         
/* 190 */         buffer.append(parameter);
/* 191 */         buffer.append(DefaultFormatter.lineSeparator);
/* 192 */         buffer.append(DefaultFormatter.LINE);
/*     */       } 
/*     */       
/* 195 */       if (Trace.isOn) {
/* 196 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogMsgNLSFormatter", "formatMessage(LogRecord,StringBuffer)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract class MsgFormatter
/*     */   {
/*     */     MsgFormatter() {
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.MsgFormatter", "<init>()");
/*     */       }
/* 220 */       if (Trace.isOn) {
/* 221 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.MsgFormatter", "<init>()");
/*     */       }
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
/*     */     void appendBaseMessage(LogRecord record, StringBuffer output) {
/* 234 */       if (Trace.isOn) {
/* 235 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.MsgFormatter", "appendBaseMessage(LogRecord,StringBuffer)", new Object[] { record, output });
/*     */       }
/*     */       
/* 238 */       appendTimestamp(output, record.getMillis());
/*     */       
/* 240 */       output.append('[' + Thread.currentThread().getName() + "] ");
/* 241 */       output.append(record.getSourceClassName());
/*     */       
/* 243 */       if (Trace.isOn) {
/* 244 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.MsgFormatter", "appendBaseMessage(LogRecord,StringBuffer)");
/*     */       }
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
/*     */     StringBuffer appendTimestamp(StringBuffer buffer, long millis) {
/* 260 */       if (Trace.isOn) {
/* 261 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.MsgFormatter", "appendTimestamp(StringBuffer,long)", new Object[] { buffer, 
/* 262 */               Long.valueOf(millis) });
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 268 */       DateFormat df = DateFormat.getDateTimeInstance(1, 1);
/* 269 */       buffer.append(df.format(new Date(millis)));
/*     */       
/* 271 */       if (Trace.isOn) {
/* 272 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.MsgFormatter", "appendTimestamp(StringBuffer,long)", buffer);
/*     */       }
/*     */       
/* 275 */       return buffer;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void formatMessage(LogRecord param1LogRecord, StringBuffer param1StringBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   int maxTraceBytes = -1;
/*     */ 
/*     */   
/*     */   public DefaultFormatter() {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "<init>()");
/*     */     }
/*     */     
/* 299 */     this.msgFormatAdapters = new HashMap<>();
/* 300 */     this.msgFormatAdapters.put("LOG_MSG", new LogMsgFormatter());
/* 301 */     this.msgFormatAdapters.put("LOG_MSG_NLS", new LogMsgNLSFormatter());
/*     */     
/*     */     try {
/* 304 */       this.maxTraceBytes = DefaultLogger.getMaxLogBytes();
/*     */     }
/* 306 */     catch (ClassCastException cce) {
/* 307 */       if (Trace.isOn) {
/* 308 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "<init>()", cce);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 313 */       this.maxTraceBytes = -1;
/*     */     } 
/*     */     
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static HashMap<String, Object> getParameterMap(Object[] parameters) {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.entry("com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "getParameterMap(Object [ ])", new Object[] { parameters });
/*     */     }
/*     */     
/* 329 */     HashMap<String, Object> traceRet1 = (HashMap<String, Object>)parameters[0];
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit("com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "getParameterMap(Object [ ])", traceRet1);
/*     */     }
/*     */     
/* 334 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format(LogRecord record) {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "format(LogRecord)", new Object[] { record });
/*     */     }
/*     */     
/* 346 */     StringBuffer output = new StringBuffer(256);
/*     */     
/* 348 */     MsgFormatter formatter = this.msgFormatAdapters.get(record.getMessage());
/* 349 */     if (formatter == null) {
/* 350 */       formatter = this.msgFormatAdapters.get("LOG_MSG");
/*     */     }
/*     */     
/*     */     try {
/* 354 */       formatter.formatMessage(record, output);
/*     */     }
/* 356 */     catch (Throwable t) {
/* 357 */       if (Trace.isOn) {
/* 358 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "format(LogRecord)", t);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 364 */       Object[] parameters = record.getParameters();
/* 365 */       HashMap<String, Object> parameter = null;
/* 366 */       if (parameters != null) {
/* 367 */         parameter = getParameterMap(parameters);
/*     */       }
/*     */ 
/*     */       
/* 371 */       TableBuilder table = new TableBuilder();
/* 372 */       table.populate(parameter);
/*     */       
/* 374 */       HashMap<String, Object> info = new HashMap<>();
/* 375 */       info.put("reason", "Invalid messageID passed to log");
/* 376 */       info.put("messageID", record.getMessage());
/* 377 */       info.put("data", table.toString());
/* 378 */       info.put("throwable", t);
/* 379 */       Trace.ffst(this, "format", "XS003001", info, null);
/*     */       
/* 381 */       if (Trace.isOn) {
/* 382 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "format(LogRecord)", "", 1);
/*     */       }
/*     */       
/* 385 */       return "";
/*     */     } 
/*     */     
/* 388 */     String traceRet1 = output.toString();
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultFormatter", "format(LogRecord)", traceRet1, 2);
/*     */     }
/*     */     
/* 393 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\log\DefaultFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */