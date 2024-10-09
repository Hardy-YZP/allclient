/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQDateConverter
/*     */ {
/*     */   static {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQDateConverter.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private static WMQDateConverter instance = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQDateConverter.java";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "static()");
/*     */     }
/* 108 */     instance = new WMQDateConverter();
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "static()");
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
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "mqDateTimeToMillis(byte [ ],byte [ ])", new Object[] { time, date });
/*     */     }
/*     */     
/* 126 */     long traceRet1 = instance.fastDateToMillis(time, date);
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "mqDateTimeToMillis(byte [ ],byte [ ])", 
/* 129 */           Long.valueOf(traceRet1));
/*     */     }
/* 131 */     return traceRet1;
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
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "mqDateTimeToMillis(String,String)", new Object[] { time, date });
/*     */     }
/*     */     
/* 147 */     long traceRet1 = instance.fastDateToMillis(time
/* 148 */         .getBytes(Charset.forName("UTF-8")), date
/* 149 */         .getBytes(Charset.forName("UTF-8")));
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "mqDateTimeToMillis(String,String)", 
/* 153 */           Long.valueOf(traceRet1));
/*     */     }
/* 155 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 160 */   private GregorianCalendar calendar = null;
/*     */ 
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
/*     */   
/*     */   private long[] millisAtMonthStart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WMQDateConverter() {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "<init>()");
/*     */     }
/*     */     
/* 192 */     this.calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 193 */     this.calendar.set(14, 0);
/*     */     
/* 195 */     if (this.calendar.get(2) > 6) {
/*     */       
/* 197 */       this.firstMonthInTable = (this.calendar.get(1) - 1970) * 12;
/*     */     } else {
/*     */       
/* 200 */       this.firstMonthInTable = (this.calendar.get(1) - 1970) * 12 - 6;
/*     */     } 
/*     */     
/* 203 */     this.millisAtMonthStart = new long[12];
/* 204 */     for (int i = this.firstMonthInTable; i < this.firstMonthInTable + 12; i++) {
/* 205 */       this.calendar.set(i / 12 + 1970, i % 12, 1, 0, 0, 0);
/* 206 */       this.millisAtMonthStart[i - this.firstMonthInTable] = this.calendar.getTime().getTime();
/*     */     } 
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "<init>()");
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
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "fastDateToMillis(byte [ ],byte [ ])", new Object[] { time, date });
/*     */     }
/*     */     
/* 229 */     int dateYear = (date[0] - 48) * 1000 + (date[1] - 48) * 100 + (date[2] - 48) * 10 + date[3] - 48;
/*     */     
/* 231 */     int dateMonth = (date[4] - 48) * 10 + date[5] - 48;
/* 232 */     long dateDay = ((date[6] - 48) * 10 + date[7] - 48);
/* 233 */     long timeHour = ((time[0] - 48) * 10 + time[1] - 48);
/* 234 */     long timeMinute = ((time[2] - 48) * 10 + time[3] - 48);
/* 235 */     long timeSecond = ((time[4] - 48) * 10 + time[5] - 48);
/* 236 */     long timeMillis = ((time[6] - 48) * 100 + (time[7] - 48) * 10);
/* 237 */     int monthsSince1970 = (dateYear - 1970) * 12 + dateMonth;
/* 238 */     long retValue = 0L;
/* 239 */     if (monthsSince1970 > this.firstMonthInTable + 12) {
/*     */ 
/*     */ 
/*     */       
/* 243 */       if (System.currentTimeMillis() > this.millisAtMonthStart[11]) {
/* 244 */         updateTable();
/* 245 */         if (monthsSince1970 > this.firstMonthInTable + 12)
/*     */         {
/*     */           
/* 248 */           retValue = slowDateToMillis(dateYear, dateMonth, dateDay, timeHour, timeMinute, timeSecond, timeMillis);
/*     */         }
/*     */         else
/*     */         {
/* 252 */           retValue = this.millisAtMonthStart[monthsSince1970 - this.firstMonthInTable - 1] + (dateDay - 1L) * 86400000L + timeHour * 3600000L + timeMinute * 60000L + timeSecond * 1000L + timeMillis;
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 258 */         retValue = slowDateToMillis(dateYear, dateMonth, dateDay, timeHour, timeMinute, timeSecond, timeMillis);
/*     */       }
/*     */     
/* 261 */     } else if (monthsSince1970 > this.firstMonthInTable) {
/*     */ 
/*     */       
/* 264 */       retValue = this.millisAtMonthStart[monthsSince1970 - this.firstMonthInTable - 1] + (dateDay - 1L) * 86400000L + timeHour * 3600000L + timeMinute * 60000L + timeSecond * 1000L + timeMillis;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 269 */       retValue = slowDateToMillis(dateYear, dateMonth, dateDay, timeHour, timeMinute, timeSecond, timeMillis);
/*     */     } 
/*     */     
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "fastDateToMillis(byte [ ],byte [ ])", 
/* 274 */           Long.valueOf(retValue));
/*     */     }
/* 276 */     return retValue;
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
/* 296 */     if (Trace.isOn)
/* 297 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "slowDateToMillis(int,int,long,long,long,long,long)", new Object[] {
/* 298 */             Integer.valueOf(year), 
/* 299 */             Integer.valueOf(month), Long.valueOf(day), Long.valueOf(hour), Long.valueOf(minute), 
/* 300 */             Long.valueOf(second), Long.valueOf(millis)
/*     */           }); 
/* 302 */     this.calendar.set(year, month - 1, (int)day, (int)hour, (int)minute, (int)second);
/* 303 */     this.calendar.set(14, (int)millis);
/* 304 */     long traceRet1 = this.calendar.getTime().getTime();
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "slowDateToMillis(int,int,long,long,long,long,long)", 
/* 307 */           Long.valueOf(traceRet1));
/*     */     }
/* 309 */     return traceRet1;
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
/*     */   private void updateTable() {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "updateTable()");
/*     */     }
/*     */     
/*     */     int i;
/*     */     
/* 328 */     for (i = 6; i < 12; i++) {
/* 329 */       this.millisAtMonthStart[i - 6] = this.millisAtMonthStart[i];
/*     */     }
/* 331 */     this.calendar.set(14, 0);
/* 332 */     this.firstMonthInTable += 6;
/* 333 */     for (i = this.firstMonthInTable + 6; i < this.firstMonthInTable + 12; i++) {
/* 334 */       this.calendar.set(i / 12 + 1970, i % 12, 1, 0, 0, 0);
/* 335 */       this.millisAtMonthStart[i - this.firstMonthInTable] = this.calendar.getTime().getTime();
/*     */     } 
/* 337 */     if (Trace.isOn)
/* 338 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDateConverter", "updateTable()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQDateConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */