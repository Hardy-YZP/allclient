/*     */ package com.ibm.msg.client.commonservices.Log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.log.CSPLog;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class StartupLog
/*     */   implements CSPLog
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/StartupLog.java";
/*     */   private static final String LINE = "-------------------------------------------------------\n";
/*     */   private Calendar calendar;
/*     */   private Date date;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.msg.client.commonservices.Log.StartupLog", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/StartupLog.java");
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
/*  64 */   private PrintStream outputStream = System.err;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StartupLog() {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "<init>()");
/*     */     }
/*  73 */     this.date = new Date();
/*  74 */     this.calendar = new GregorianCalendar();
/*  75 */     this.outputStream = Trace.errorStream;
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "<init>()");
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
/*     */   StringBuffer appendTimestamp(StringBuffer buffer, long millis) {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "appendTimestamp(StringBuffer,long)", new Object[] { buffer, 
/*  97 */             Long.valueOf(millis) });
/*     */     }
/*     */     
/* 100 */     this.date.setTime(millis);
/* 101 */     this.calendar.setTime(this.date);
/*     */     
/* 103 */     int day = this.calendar.get(5);
/* 104 */     int month = this.calendar.get(2) + 1;
/* 105 */     int year = this.calendar.get(1);
/* 106 */     int h = this.calendar.get(11);
/* 107 */     int m = this.calendar.get(12);
/* 108 */     int s = this.calendar.get(13);
/*     */     
/* 110 */     if (day < 10) {
/* 111 */       buffer.append("0" + day + "/");
/*     */     } else {
/* 113 */       buffer.append(day + "/");
/*     */     } 
/*     */     
/* 116 */     if (month < 10) {
/* 117 */       buffer.append("0" + month + "/");
/*     */     } else {
/* 119 */       buffer.append(month + "/");
/*     */     } 
/*     */     
/* 122 */     buffer.append(year + " ");
/*     */     
/* 124 */     if (h < 10) {
/* 125 */       buffer.append("0" + h + ":");
/*     */     } else {
/* 127 */       buffer.append(h + ":");
/*     */     } 
/*     */     
/* 130 */     if (m < 10) {
/* 131 */       buffer.append("0" + m + ":");
/*     */     } else {
/* 133 */       buffer.append(m + ":");
/*     */     } 
/*     */     
/* 136 */     if (s < 10) {
/* 137 */       buffer.append("0" + s + ":");
/*     */     } else {
/* 139 */       buffer.append(s + " ");
/*     */     } 
/*     */     
/* 142 */     buffer.append("[" + millis + "] ");
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "appendTimestamp(StringBuffer,long)", buffer);
/*     */     }
/*     */     
/* 148 */     return buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "close()");
/*     */     }
/*     */     
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private StringBuffer getBaseMessage(Object parentClass) {
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "getBaseMessage(Object)", new Object[] { parentClass });
/*     */     }
/*     */     
/* 171 */     StringBuffer output = new StringBuffer();
/* 172 */     appendTimestamp(output, System.currentTimeMillis());
/*     */     
/* 174 */     String className = null;
/* 175 */     if (parentClass instanceof String) {
/* 176 */       className = (String)parentClass;
/* 177 */     } else if (parentClass != null) {
/* 178 */       className = parentClass.getClass().getName();
/*     */     } 
/*     */     
/* 181 */     output.append('[' + Thread.currentThread().getName() + "] ");
/* 182 */     output.append(className);
/* 183 */     output.append(' ');
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "getBaseMessage(Object)", output);
/*     */     }
/*     */     
/* 189 */     return output;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "initialize()");
/*     */     }
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "initialize()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(Object parentClass, String parentClassName, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "log(Object,String,String,String,HashMap<String , ? extends Object>)", new Object[] { parentClass, parentClassName, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 218 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/*     */     
/* 220 */     String message = NLSServices.getMessage(key, inserts);
/* 221 */     String explanation = NLSServices.getExplanation(key, inserts);
/* 222 */     String action = NLSServices.getUserAction(key, inserts);
/*     */     
/* 224 */     output.append('\n');
/*     */     
/* 226 */     output.append(message);
/* 227 */     output.append('\n');
/*     */     
/* 229 */     output.append(NLSServices.getMessage("JMSCS0007"));
/* 230 */     output.append(explanation);
/* 231 */     output.append('\n');
/*     */     
/* 233 */     output.append(NLSServices.getMessage("JMSCS0008"));
/* 234 */     output.append(action);
/* 235 */     output.append('\n');
/* 236 */     output.append("-------------------------------------------------------\n");
/*     */     
/* 238 */     this.outputStream.println(output);
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "log(Object,String,String,String,HashMap<String , ? extends Object>)");
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
/*     */   public void logNLS(Object parentClass, String parentClassName, String methodSignature, String NLSMessage) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "logNLS(Object,String,String,String)", new Object[] { parentClass, parentClassName, methodSignature, NLSMessage });
/*     */     }
/*     */ 
/*     */     
/* 257 */     StringBuffer output = getBaseMessage((parentClass == null) ? parentClassName : parentClass);
/* 258 */     output.append('\n');
/*     */ 
/*     */     
/* 261 */     output.append(NLSMessage);
/* 262 */     output.append('\n');
/* 263 */     output.append("-------------------------------------------------------\n");
/*     */     
/* 265 */     this.outputStream.println(output);
/* 266 */     if (Trace.isOn)
/* 267 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.StartupLog", "logNLS(Object,String,String,String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\Log\StartupLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */