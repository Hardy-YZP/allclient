/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQDateConverter
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDateConverter.java";
/*     */   
/*     */   static {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.data("com.ibm.mq.MQDateConverter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDateConverter.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private GregorianCalendar calendar = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long[] millisAtMonthStart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int firstMonthInTable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   private static MQDateConverter instance = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQDateConverter() {
/* 121 */     super(MQSESSION.getJmqiEnv());
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.MQDateConverter", "<init>()");
/*     */     }
/* 125 */     this.calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 126 */     this.calendar.set(14, 0);
/*     */     
/* 128 */     if (this.calendar.get(2) > 6) {
/*     */       
/* 130 */       this.firstMonthInTable = (this.calendar.get(1) - 1970) * 12;
/*     */     }
/*     */     else {
/*     */       
/* 134 */       this.firstMonthInTable = (this.calendar.get(1) - 1970) * 12 - 6;
/*     */     } 
/*     */     
/* 137 */     this.millisAtMonthStart = new long[12];
/* 138 */     for (int i = this.firstMonthInTable; i < this.firstMonthInTable + 12; i++) {
/* 139 */       this.calendar.set(i / 12 + 1970, i % 12, 1, 0, 0, 0);
/* 140 */       this.millisAtMonthStart[i - this.firstMonthInTable] = this.calendar.getTime().getTime();
/*     */     } 
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.MQDateConverter", "<init>()");
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
/*     */   private void updateTable() {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.mq.MQDateConverter", "updateTable()");
/*     */     }
/*     */     
/*     */     int i;
/* 165 */     for (i = 6; i < 12; i++) {
/* 166 */       this.millisAtMonthStart[i - 6] = this.millisAtMonthStart[i];
/*     */     }
/* 168 */     this.calendar.set(14, 0);
/* 169 */     this.firstMonthInTable += 6;
/* 170 */     for (i = this.firstMonthInTable + 6; i < this.firstMonthInTable + 12; i++) {
/* 171 */       this.calendar.set(i / 12 + 1970, i % 12, 1, 0, 0, 0);
/* 172 */       this.millisAtMonthStart[i - this.firstMonthInTable] = this.calendar.getTime().getTime();
/*     */     } 
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.MQDateConverter", "updateTable()");
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
/*     */   private long fastDateToMillis(byte[] time, byte[] date) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.MQDateConverter", "fastDateToMillis(byte [ ],byte [ ])", new Object[] { time, date });
/*     */     }
/*     */     
/* 196 */     int dateYear = (date[0] - 48) * 1000 + (date[1] - 48) * 100 + (date[2] - 48) * 10 + date[3] - 48;
/* 197 */     int dateMonth = (date[4] - 48) * 10 + date[5] - 48;
/* 198 */     long dateDay = ((date[6] - 48) * 10 + date[7] - 48);
/* 199 */     long timeHour = ((time[0] - 48) * 10 + time[1] - 48);
/* 200 */     long timeMinute = ((time[2] - 48) * 10 + time[3] - 48);
/* 201 */     long timeSecond = ((time[4] - 48) * 10 + time[5] - 48);
/* 202 */     long timeMillis = ((time[6] - 48) * 100 + (time[7] - 48) * 10);
/* 203 */     int monthsSince1970 = (dateYear - 1970) * 12 + dateMonth;
/* 204 */     long retValue = 0L;
/* 205 */     if (monthsSince1970 > this.firstMonthInTable + 12) {
/* 206 */       if (Trace.isOn) {
/* 207 */         Trace.data(this, "fastDateToMillis(byte [ ],byte [ ])", "date beyond last in table", "");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 212 */       if (System.currentTimeMillis() > this.millisAtMonthStart[11]) {
/* 213 */         updateTable();
/* 214 */         if (monthsSince1970 > this.firstMonthInTable + 12) {
/*     */ 
/*     */           
/* 217 */           retValue = slowDateToMillis(dateYear, dateMonth, dateDay, timeHour, timeMinute, timeSecond, timeMillis);
/*     */         } else {
/*     */           
/* 220 */           retValue = this.millisAtMonthStart[monthsSince1970 - this.firstMonthInTable - 1] + (dateDay - 1L) * 86400000L + timeHour * 3600000L + timeMinute * 60000L + timeSecond * 1000L + timeMillis;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 227 */         retValue = slowDateToMillis(dateYear, dateMonth, dateDay, timeHour, timeMinute, timeSecond, timeMillis);
/*     */       }
/*     */     
/* 230 */     } else if (monthsSince1970 > this.firstMonthInTable) {
/*     */ 
/*     */       
/* 233 */       retValue = this.millisAtMonthStart[monthsSince1970 - this.firstMonthInTable - 1] + (dateDay - 1L) * 86400000L + timeHour * 3600000L + timeMinute * 60000L + timeSecond * 1000L + timeMillis;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 239 */       retValue = slowDateToMillis(dateYear, dateMonth, dateDay, timeHour, timeMinute, timeSecond, timeMillis);
/*     */     } 
/*     */     
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.mq.MQDateConverter", "fastDateToMillis(byte [ ],byte [ ])", 
/* 244 */           Long.valueOf(retValue));
/*     */     }
/* 246 */     return retValue;
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
/*     */   private long slowDateToMillis(int year, int month, long day, long hour, long minute, long second, long millis) {
/* 266 */     if (Trace.isOn)
/* 267 */       Trace.entry(this, "com.ibm.mq.MQDateConverter", "slowDateToMillis(int,int,long,long,long,long,long)", new Object[] {
/* 268 */             Integer.valueOf(year), 
/* 269 */             Integer.valueOf(month), Long.valueOf(day), Long.valueOf(hour), Long.valueOf(minute), 
/* 270 */             Long.valueOf(second), Long.valueOf(millis)
/*     */           }); 
/* 272 */     this.calendar.set(year, month - 1, (int)day, (int)hour, (int)minute, (int)second);
/* 273 */     this.calendar.set(14, (int)millis);
/* 274 */     long traceRet1 = this.calendar.getTime().getTime();
/*     */     
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.exit(this, "com.ibm.mq.MQDateConverter", "slowDateToMillis(int,int,long,long,long,long,long)", 
/* 278 */           Long.valueOf(traceRet1));
/*     */     }
/* 280 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.entry("com.ibm.mq.MQDateConverter", "static()");
/*     */     }
/* 293 */     instance = new MQDateConverter();
/*     */     
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.exit("com.ibm.mq.MQDateConverter", "static()");
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
/*     */   public static synchronized long mqDateTimeToMillis(byte[] time, byte[] date) {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry("com.ibm.mq.MQDateConverter", "mqDateTimeToMillis(byte [ ],byte [ ])", new Object[] { time, date });
/*     */     }
/*     */     
/* 312 */     long traceRet1 = instance.fastDateToMillis(time, date);
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.exit("com.ibm.mq.MQDateConverter", "mqDateTimeToMillis(byte [ ],byte [ ])", 
/* 315 */           Long.valueOf(traceRet1));
/*     */     }
/* 317 */     return traceRet1;
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
/*     */   public static synchronized long mqDateTimeToMillis(String time, String date) {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.entry("com.ibm.mq.MQDateConverter", "mqDateTimeToMillis(String,String)", new Object[] { time, date });
/*     */     }
/*     */     
/* 333 */     long traceRet1 = instance.fastDateToMillis(time.getBytes(Charset.defaultCharset()), date.getBytes(Charset.defaultCharset()));
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.exit("com.ibm.mq.MQDateConverter", "mqDateTimeToMillis(String,String)", 
/* 336 */           Long.valueOf(traceRet1));
/*     */     }
/* 338 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQDateConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */