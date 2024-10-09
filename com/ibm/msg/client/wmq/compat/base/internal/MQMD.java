/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Arrays;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQMD
/*      */   extends JmqiObject
/*      */ {
/*      */   static {
/*  110 */     if (Trace.isOn) {
/*  111 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQMD.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  117 */   private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
/*      */   
/*  119 */   private static final GregorianCalendar gmtConverter = new GregorianCalendar(GMT);
/*      */   
/*  121 */   private GregorianCalendar putCalendar = null;
/*      */   
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQMD.java";
/*      */   
/*      */   protected static final int sizeofMQMDv1 = 324;
/*      */   
/*      */   protected static final int sizeofMQMDv2 = 364;
/*      */   
/*      */   static final String getDate(GregorianCalendar dateTime) {
/*      */     String strDate;
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getDate(GregorianCalendar)", new Object[] { dateTime });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  138 */     if (dateTime != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  143 */       strDate = setNumberToLength(Integer.toString(dateTime.get(1)), 4) + setNumberToLength(Integer.toString(dateTime.get(2) + 1), 2) + setNumberToLength(Integer.toString(dateTime.get(5)), 2);
/*      */     } else {
/*  145 */       strDate = "        ";
/*      */     } 
/*      */     
/*  148 */     if (Trace.isOn) {
/*  149 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getDate(GregorianCalendar)", strDate);
/*      */     }
/*      */     
/*  152 */     return strDate;
/*      */   }
/*      */ 
/*      */   
/*      */   static final String getTime(GregorianCalendar dateTime) {
/*      */     String strTime;
/*  158 */     if (Trace.isOn) {
/*  159 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getTime(GregorianCalendar)", new Object[] { dateTime });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  165 */     if (dateTime != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  171 */       strTime = setNumberToLength(Integer.toString(dateTime.get(11)), 2) + setNumberToLength(Integer.toString(dateTime.get(12)), 2) + setNumberToLength(Integer.toString(dateTime.get(13)), 2) + setNumberToLength(Integer.toString(dateTime.get(14) / 10), 2);
/*      */     } else {
/*  173 */       strTime = "        ";
/*      */     } 
/*      */     
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getTime(GregorianCalendar)", strTime);
/*      */     }
/*      */     
/*  180 */     return strTime;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String setNumberToLength(String strNumber, int iNewLength) {
/*      */     String strPadded;
/*  187 */     if (Trace.isOn) {
/*  188 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setNumberToLength(String,int)", new Object[] { strNumber, 
/*  189 */             Integer.valueOf(iNewLength) });
/*      */     }
/*  191 */     int iOldLength = strNumber.length();
/*      */ 
/*      */     
/*  194 */     if (iOldLength > iNewLength) {
/*      */ 
/*      */       
/*  197 */       strPadded = strNumber.substring(0, iNewLength);
/*      */     }
/*  199 */     else if (iOldLength < iNewLength) {
/*  200 */       int leading = iNewLength - iOldLength;
/*  201 */       char[] padArray = new char[iNewLength];
/*  202 */       Arrays.fill(padArray, 0, leading, '0');
/*  203 */       System.arraycopy(strNumber.toCharArray(), 0, padArray, leading, iOldLength);
/*  204 */       strPadded = new String(padArray);
/*      */     } else {
/*  206 */       strPadded = strNumber;
/*      */     } 
/*      */     
/*  209 */     if (Trace.isOn) {
/*  210 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setNumberToLength(String,int)", strPadded);
/*      */     }
/*      */     
/*  213 */     return strPadded;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  225 */   public byte[] accountingToken = new byte[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  237 */   public String applicationIdData = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  247 */   public String applicationOriginData = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  258 */   public int backoutCount = 0;
/*      */ 
/*      */   
/*      */   private int cachedCcsid;
/*      */ 
/*      */   
/*  264 */   private JmqiCodepage cachedCodepage = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  288 */   public int characterSet = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  305 */   public byte[] correlationId = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  359 */   public int encoding = 273;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  370 */   public int expiry = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  411 */   public int feedback = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  439 */   public String format = "        ";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  444 */   public byte[] groupId = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  452 */   private com.ibm.mq.jmqi.MQMD jmqiStructure = MQSESSION.getJmqiEnv().newMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  468 */   public int messageFlags = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  487 */   public byte[] messageId = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  492 */   public int messageSequenceNumber = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  512 */   public int messageType = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  517 */   public int offset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  522 */   public int originalLength = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  536 */   public int persistence = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  545 */   public int priority = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  552 */   public String putApplicationName = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  591 */   public int putApplicationType = 0;
/*      */ 
/*      */ 
/*      */   
/*  595 */   String putDate = "";
/*      */ 
/*      */ 
/*      */   
/*  599 */   public GregorianCalendar putDateTime = null;
/*  600 */   String putTime = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  608 */   public String replyToQueueManagerName = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  618 */   public String replyToQueueName = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  695 */   public int report = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  703 */   public String userId = "";
/*      */   
/*  705 */   private int version = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMD() {
/*  711 */     super(MQSESSION.getJmqiEnv());
/*  712 */     if (Trace.isOn) {
/*  713 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "<init>()");
/*      */     }
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String[] convertCalendarToStrings(GregorianCalendar calendar) {
/*  731 */     if (Trace.isOn) {
/*  732 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "convertCalendarToStrings(GregorianCalendar)", new Object[] { calendar });
/*      */     }
/*      */     
/*  735 */     String[] jmqiPutDateTime = new String[2];
/*  736 */     char[] rawPutDate = new char[8];
/*  737 */     char[] rawPutTime = new char[8];
/*      */     
/*  739 */     if (calendar == null) {
/*      */       
/*  741 */       Arrays.fill(rawPutDate, ' ');
/*  742 */       Arrays.fill(rawPutTime, ' ');
/*      */     } else {
/*      */       int year, month, day, hour, minute, second, hundredth;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  749 */       synchronized (gmtConverter) {
/*  750 */         gmtConverter.setTime(calendar.getTime());
/*  751 */         year = gmtConverter.get(1);
/*  752 */         month = gmtConverter.get(2) + 1;
/*  753 */         day = gmtConverter.get(5);
/*  754 */         hour = gmtConverter.get(11);
/*  755 */         minute = gmtConverter.get(12);
/*  756 */         second = gmtConverter.get(13);
/*  757 */         hundredth = gmtConverter.get(14) / 10;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       int contrib;
/*      */ 
/*      */ 
/*      */       
/*  767 */       rawPutDate[0] = (char)(48 + (contrib = year / 1000));
/*  768 */       year -= contrib * 1000;
/*  769 */       rawPutDate[1] = (char)(48 + (contrib = year / 100));
/*  770 */       year -= contrib * 100;
/*  771 */       rawPutDate[2] = (char)(48 + (contrib = year / 10));
/*  772 */       year -= contrib * 10;
/*  773 */       rawPutDate[3] = (char)(48 + (contrib = year));
/*      */       
/*  775 */       rawPutDate[4] = (char)(48 + (contrib = month / 10));
/*  776 */       month -= contrib * 10;
/*  777 */       rawPutDate[5] = (char)(48 + (contrib = month));
/*      */       
/*  779 */       rawPutDate[6] = (char)(48 + (contrib = day / 10));
/*  780 */       day -= contrib * 10;
/*  781 */       rawPutDate[7] = (char)(48 + (contrib = day));
/*      */ 
/*      */ 
/*      */       
/*  785 */       rawPutTime[0] = (char)(48 + (contrib = hour / 10));
/*  786 */       hour -= contrib * 10;
/*  787 */       rawPutTime[1] = (char)(48 + (contrib = hour));
/*      */       
/*  789 */       rawPutTime[2] = (char)(48 + (contrib = minute / 10));
/*  790 */       minute -= contrib * 10;
/*  791 */       rawPutTime[3] = (char)(48 + (contrib = minute));
/*      */       
/*  793 */       rawPutTime[4] = (char)(48 + (contrib = second / 10));
/*  794 */       second -= contrib * 10;
/*  795 */       rawPutTime[5] = (char)(48 + (contrib = second));
/*      */       
/*  797 */       rawPutTime[6] = (char)(48 + (contrib = hundredth / 10));
/*  798 */       hundredth -= contrib * 10;
/*  799 */       rawPutTime[7] = (char)(48 + (contrib = hundredth));
/*      */     } 
/*      */ 
/*      */     
/*  803 */     jmqiPutDateTime[0] = new String(rawPutDate);
/*  804 */     jmqiPutDateTime[1] = new String(rawPutTime);
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "convertCalendarToStrings(GregorianCalendar)", jmqiPutDateTime);
/*      */     }
/*      */     
/*  809 */     return jmqiPutDateTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final GregorianCalendar getDateAndTime(String date, String time) {
/*      */     GregorianCalendar retVal;
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getDateAndTime(String,String)", new Object[] { date, time });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  828 */     char[] rawPutDate = (date != null) ? date.toCharArray() : null;
/*  829 */     char[] rawPutTime = (time != null) ? time.toCharArray() : null;
/*  830 */     if (rawPutDate != null && rawPutDate.length == 8 && rawPutTime != null && rawPutTime.length == 8) {
/*      */ 
/*      */       
/*  833 */       int year = (rawPutDate[0] - 48) * 1000;
/*  834 */       year += (rawPutDate[1] - 48) * 100;
/*  835 */       year += (rawPutDate[2] - 48) * 10;
/*  836 */       year += rawPutDate[3] - 48;
/*      */       
/*  838 */       int month = (rawPutDate[4] - 48) * 10;
/*  839 */       month += rawPutDate[5] - 48;
/*  840 */       month--;
/*      */       
/*  842 */       int day = (rawPutDate[6] - 48) * 10;
/*  843 */       day += rawPutDate[7] - 48;
/*      */       
/*  845 */       int hour = (rawPutTime[0] - 48) * 10;
/*  846 */       hour += rawPutTime[1] - 48;
/*      */       
/*  848 */       int minute = (rawPutTime[2] - 48) * 10;
/*  849 */       minute += rawPutTime[3] - 48;
/*      */       
/*  851 */       int second = (rawPutTime[4] - 48) * 10;
/*  852 */       second += rawPutTime[5] - 48;
/*      */       
/*  854 */       int millisecond = (rawPutTime[6] - 48) * 10;
/*  855 */       millisecond += rawPutTime[7] - 48;
/*  856 */       millisecond *= 10;
/*      */       
/*  858 */       retVal = new GregorianCalendar(year, month, day, hour, minute, second);
/*  859 */       retVal.setTimeZone(GMT);
/*  860 */       retVal.set(14, millisecond);
/*      */     } else {
/*      */       
/*  863 */       retVal = new GregorianCalendar();
/*      */     } 
/*      */     
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getDateAndTime(String,String)", retVal);
/*      */     }
/*      */     
/*  870 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected com.ibm.mq.jmqi.MQMD getJMQIStructure(int pmoFlags) {
/*  886 */     this.jmqiStructure.setVersion(this.version);
/*  887 */     this.jmqiStructure.setReport(this.report);
/*  888 */     this.jmqiStructure.setMsgType(this.messageType);
/*  889 */     this.jmqiStructure.setExpiry(this.expiry);
/*  890 */     this.jmqiStructure.setFeedback(this.feedback);
/*  891 */     this.jmqiStructure.setEncoding(this.encoding);
/*  892 */     this.jmqiStructure.setCodedCharSetId(this.characterSet);
/*  893 */     this.jmqiStructure.setFormat(this.format);
/*  894 */     this.jmqiStructure.setPriority(this.priority);
/*  895 */     this.jmqiStructure.setPersistence(this.persistence);
/*  896 */     this.jmqiStructure.setMsgId(this.messageId);
/*  897 */     this.jmqiStructure.setCorrelId(this.correlationId);
/*  898 */     this.jmqiStructure.setBackoutCount(this.backoutCount);
/*  899 */     this.jmqiStructure.setReplyToQ(this.replyToQueueName);
/*  900 */     this.jmqiStructure.setReplyToQMgr(this.replyToQueueManagerName);
/*      */     
/*  902 */     boolean setAllCtx = ((pmoFlags & 0x800) != 0);
/*  903 */     boolean setIdCts = (setAllCtx || (pmoFlags & 0x400) != 0);
/*      */     
/*  905 */     if (setIdCts) {
/*  906 */       this.jmqiStructure.setUserIdentifier(this.userId);
/*  907 */       this.jmqiStructure.setAccountingToken(this.accountingToken);
/*  908 */       this.jmqiStructure.setApplIdentityData(this.applicationIdData);
/*      */     } 
/*      */     
/*  911 */     if (setAllCtx) {
/*  912 */       this.jmqiStructure.setPutApplType(this.putApplicationType);
/*  913 */       this.jmqiStructure.setPutApplName(this.putApplicationName);
/*  914 */       this.jmqiStructure.setApplOriginData(this.applicationOriginData);
/*  915 */       String[] jmqiPutDateTime = convertCalendarToStrings(this.putDateTime);
/*  916 */       this.jmqiStructure.setPutDate(jmqiPutDateTime[0]);
/*  917 */       this.jmqiStructure.setPutTime(jmqiPutDateTime[1]);
/*      */     } 
/*      */ 
/*      */     
/*  921 */     if (this.version >= 2) {
/*  922 */       this.jmqiStructure.setGroupId(this.groupId);
/*  923 */       this.jmqiStructure.setMsgSeqNumber(this.messageSequenceNumber);
/*  924 */       this.jmqiStructure.setOffset(this.offset);
/*  925 */       this.jmqiStructure.setMsgFlags(this.messageFlags);
/*  926 */       this.jmqiStructure.setOriginalLength(this.originalLength);
/*      */     } 
/*      */     
/*  929 */     return this.jmqiStructure;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  938 */     if (Trace.isOn) {
/*  939 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "getVersion()", "getter", 
/*  940 */           Integer.valueOf(this.version));
/*      */     }
/*  942 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] makeBytesFromString(String strToUse, int ccsid, boolean codepageIsAscii, boolean isInvariant) throws MQException {
/*  963 */     if (Trace.isOn) {
/*  964 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeBytesFromString(String,int,boolean,boolean)", new Object[] { strToUse, 
/*      */             
/*  966 */             Integer.valueOf(ccsid), Boolean.valueOf(codepageIsAscii), Boolean.valueOf(isInvariant) });
/*      */     }
/*  968 */     byte[] retBytes = null;
/*  969 */     boolean onlyInvariantChars = false;
/*      */ 
/*      */ 
/*      */     
/*  973 */     if (isInvariant) {
/*  974 */       onlyInvariantChars = true;
/*  975 */     } else if (codepageIsAscii) {
/*      */       
/*  977 */       onlyInvariantChars = true;
/*  978 */       for (int i = 0; i < strToUse.length() && onlyInvariantChars; i++) {
/*  979 */         onlyInvariantChars = (strToUse.charAt(i) < '');
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  984 */     if (codepageIsAscii && onlyInvariantChars) {
/*  985 */       retBytes = new byte[strToUse.length()];
/*  986 */       for (int i = 0; i < retBytes.length; i++) {
/*  987 */         retBytes[i] = (byte)strToUse.charAt(i);
/*      */       }
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  993 */         if (this.cachedCodepage == null || this.cachedCcsid != ccsid) {
/*  994 */           this.cachedCodepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), ccsid);
/*      */           
/*  996 */           if (this.cachedCodepage == null) {
/*      */             
/*  998 */             UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(ccsid));
/*  999 */             if (Trace.isOn) {
/* 1000 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeBytesFromString(String,int,boolean,boolean)", traceRet2, 1);
/*      */             }
/*      */             
/* 1003 */             throw traceRet2;
/*      */           } 
/*      */           
/* 1006 */           this.cachedCcsid = ccsid;
/*      */         } 
/*      */         
/* 1009 */         byte[] convertedBytes = this.cachedCodepage.stringToBytes(strToUse);
/* 1010 */         if (convertedBytes.length != strToUse.length()) {
/* 1011 */           retBytes = new byte[strToUse.length()];
/* 1012 */           System.arraycopy(convertedBytes, 0, retBytes, 0, retBytes.length);
/*      */         } else {
/* 1014 */           retBytes = convertedBytes;
/*      */         }
/*      */       
/*      */       }
/* 1018 */       catch (NullPointerException|java.nio.charset.CharacterCodingException|UnsupportedEncodingException e) {
/* 1019 */         if (Trace.isOn) {
/* 1020 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeBytesFromString(String,int,boolean,boolean)", e);
/*      */         }
/*      */         
/* 1023 */         MQException traceRet1 = new MQException(2, 2195, "MQJE046", this.cachedCodepage.toString());
/* 1024 */         this.cachedCodepage = null;
/* 1025 */         if (Trace.isOn) {
/* 1026 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeBytesFromString(String,int,boolean,boolean)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/* 1029 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */     
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeBytesFromString(String,int,boolean,boolean)", retBytes);
/*      */     }
/*      */     
/* 1037 */     return retBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String makeStringFromBytes(byte[] bytesToUse, int ccsid, boolean codepageIsAscii, boolean isInvariant) throws MQException {
/* 1057 */     if (Trace.isOn) {
/* 1058 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeStringFromBytes(byte [ ],int,boolean,boolean)", new Object[] { bytesToUse, 
/*      */             
/* 1060 */             Integer.valueOf(ccsid), Boolean.valueOf(codepageIsAscii), Boolean.valueOf(isInvariant) });
/*      */     }
/* 1062 */     String retStr = null;
/*      */     
/* 1064 */     boolean onlyInvariantChars = false;
/*      */ 
/*      */ 
/*      */     
/* 1068 */     if (isInvariant) {
/* 1069 */       onlyInvariantChars = true;
/* 1070 */     } else if (codepageIsAscii) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1075 */       onlyInvariantChars = true;
/* 1076 */       for (int i = 0; i < bytesToUse.length && onlyInvariantChars; i++) {
/* 1077 */         onlyInvariantChars = (bytesToUse[i] >= 0);
/*      */       }
/*      */     } 
/*      */     
/* 1081 */     if (onlyInvariantChars && codepageIsAscii) {
/* 1082 */       char[] chars = new char[bytesToUse.length];
/* 1083 */       for (int i = 0; i < bytesToUse.length; i++) {
/* 1084 */         chars[i] = (char)bytesToUse[i];
/*      */       }
/* 1086 */       retStr = new String(chars);
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 1092 */         if (this.cachedCcsid != ccsid || this.cachedCodepage == null) {
/* 1093 */           this.cachedCodepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), ccsid);
/*      */           
/* 1095 */           if (this.cachedCodepage == null) {
/*      */             
/* 1097 */             UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(ccsid));
/* 1098 */             if (Trace.isOn) {
/* 1099 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeStringFromBytes(byte [ ],int,boolean,boolean)", traceRet2, 1);
/*      */             }
/*      */             
/* 1102 */             throw traceRet2;
/*      */           } 
/*      */           
/* 1105 */           this.cachedCcsid = ccsid;
/*      */         } 
/*      */         
/* 1108 */         retStr = this.cachedCodepage.bytesToString(bytesToUse);
/*      */       }
/* 1110 */       catch (NullPointerException|java.nio.charset.CharacterCodingException|UnsupportedEncodingException e) {
/* 1111 */         if (Trace.isOn) {
/* 1112 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeStringFromBytes(byte [ ],int,boolean,boolean)", e);
/*      */         }
/*      */         
/* 1115 */         MQException traceRet1 = new MQException(2, 2195, "MQJE046", this.cachedCodepage.toString());
/* 1116 */         this.cachedCodepage = null;
/* 1117 */         if (Trace.isOn) {
/* 1118 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeStringFromBytes(byte [ ],int,boolean,boolean)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/* 1121 */         throw traceRet1;
/*      */       } 
/*      */     } 
/* 1124 */     if (Trace.isOn) {
/* 1125 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "makeStringFromBytes(byte [ ],int,boolean,boolean)", retStr);
/*      */     }
/*      */     
/* 1128 */     return retStr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DataInputStream readFrom(DataInputStream dataBuffer, int ccsid, boolean ccsidIsAscii) throws IOException, MQException {
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "readFrom(java.io.DataInputStream,int,boolean)", new Object[] { dataBuffer, 
/*      */             
/* 1151 */             Integer.valueOf(ccsid), Boolean.valueOf(ccsidIsAscii) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1156 */     if (dataBuffer.available() < 324) {
/* 1157 */       MQException traceRet1 = new MQException(2, 2195, "MQJE043");
/* 1158 */       if (Trace.isOn) {
/* 1159 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "readFrom(java.io.DataInputStream,int,boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1162 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1166 */     byte[] eyecatcherBytes = new byte[4];
/*      */ 
/*      */     
/* 1169 */     dataBuffer.read(eyecatcherBytes, 0, 4);
/* 1170 */     String eyecatcher = MQEnvironment.stringFromBytes(eyecatcherBytes);
/*      */     
/* 1172 */     if (!eyecatcher.equals("MD  ")) {
/* 1173 */       MQException traceRet2 = new MQException(2, 2195, "MQJE042", eyecatcher);
/* 1174 */       if (Trace.isOn) {
/* 1175 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "readFrom(java.io.DataInputStream,int,boolean)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1178 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 1182 */     this.version = dataBuffer.readInt();
/* 1183 */     this.report = dataBuffer.readInt();
/* 1184 */     this.messageType = dataBuffer.readInt();
/* 1185 */     this.expiry = dataBuffer.readInt();
/* 1186 */     this.feedback = dataBuffer.readInt();
/* 1187 */     this.encoding = dataBuffer.readInt();
/* 1188 */     this.characterSet = dataBuffer.readInt();
/*      */ 
/*      */     
/* 1191 */     byte[] fmtBytes = new byte[8];
/* 1192 */     dataBuffer.read(fmtBytes, 0, 8);
/* 1193 */     this.format = makeStringFromBytes(fmtBytes, ccsid, ccsidIsAscii, false);
/*      */     
/* 1195 */     this.priority = dataBuffer.readInt();
/* 1196 */     this.persistence = dataBuffer.readInt();
/* 1197 */     this.messageId = setArrayToLength(this.messageId, 24);
/* 1198 */     dataBuffer.read(this.messageId, 0, 24);
/* 1199 */     this.correlationId = setArrayToLength(this.correlationId, 24);
/* 1200 */     dataBuffer.read(this.correlationId, 0, 24);
/* 1201 */     this.backoutCount = dataBuffer.readInt();
/*      */     
/* 1203 */     byte[] replyToQBytes = new byte[48];
/* 1204 */     dataBuffer.read(replyToQBytes, 0, 48);
/* 1205 */     this.replyToQueueName = makeStringFromBytes(replyToQBytes, ccsid, ccsidIsAscii, true);
/*      */     
/* 1207 */     byte[] replyToQMgrBytes = new byte[48];
/* 1208 */     dataBuffer.read(replyToQMgrBytes, 0, 48);
/* 1209 */     this.replyToQueueManagerName = makeStringFromBytes(replyToQMgrBytes, ccsid, ccsidIsAscii, true);
/*      */     
/* 1211 */     byte[] userIDBytes = new byte[12];
/* 1212 */     dataBuffer.read(userIDBytes, 0, 12);
/* 1213 */     this.userId = makeStringFromBytes(userIDBytes, ccsid, ccsidIsAscii, false);
/*      */     
/* 1215 */     this.accountingToken = setArrayToLength(this.accountingToken, 32);
/* 1216 */     dataBuffer.read(this.accountingToken, 0, 32);
/*      */     
/* 1218 */     byte[] applIdBytes = new byte[32];
/* 1219 */     dataBuffer.read(applIdBytes, 0, 32);
/* 1220 */     this.applicationIdData = makeStringFromBytes(applIdBytes, ccsid, ccsidIsAscii, false);
/*      */     
/* 1222 */     this.putApplicationType = dataBuffer.readInt();
/*      */     
/* 1224 */     byte[] applBytes = new byte[28];
/* 1225 */     dataBuffer.read(applBytes, 0, 28);
/* 1226 */     this.putApplicationName = makeStringFromBytes(applBytes, ccsid, ccsidIsAscii, false);
/*      */     
/* 1228 */     byte[] dateBytes = new byte[8];
/* 1229 */     dataBuffer.read(dateBytes, 0, 8);
/* 1230 */     this.putDate = makeStringFromBytes(dateBytes, ccsid, ccsidIsAscii, true);
/*      */     
/* 1232 */     byte[] timeBytes = new byte[8];
/* 1233 */     dataBuffer.read(timeBytes, 0, 8);
/* 1234 */     this.putTime = makeStringFromBytes(timeBytes, ccsid, ccsidIsAscii, true);
/*      */     
/* 1236 */     byte[] originBytes = new byte[4];
/* 1237 */     dataBuffer.read(originBytes, 0, 4);
/* 1238 */     this.applicationOriginData = makeStringFromBytes(originBytes, ccsid, ccsidIsAscii, false);
/*      */     
/* 1240 */     this.putDateTime = getDateAndTime(this.putDate, this.putTime);
/*      */ 
/*      */     
/* 1243 */     if (this.version > 1) {
/* 1244 */       this.groupId = setArrayToLength(this.groupId, 24);
/* 1245 */       dataBuffer.read(this.groupId, 0, 24);
/* 1246 */       this.messageSequenceNumber = dataBuffer.readInt();
/* 1247 */       this.offset = dataBuffer.readInt();
/* 1248 */       this.messageFlags = dataBuffer.readInt();
/* 1249 */       this.originalLength = dataBuffer.readInt();
/*      */     } else {
/*      */       
/* 1252 */       this.groupId = MQC.MQGI_NONE;
/* 1253 */       this.messageSequenceNumber = 1;
/* 1254 */       this.offset = 0;
/* 1255 */       this.messageFlags = 0;
/* 1256 */       this.originalLength = -1;
/*      */     } 
/*      */     
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "readFrom(java.io.DataInputStream,int,boolean)", dataBuffer);
/*      */     }
/*      */     
/* 1263 */     return dataBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final byte[] setArrayToLength(byte[] array, int length) throws MQException {
/*      */     byte[] retVal;
/* 1277 */     if (Trace.isOn) {
/* 1278 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setArrayToLength(byte [ ],int)", new Object[] { array, 
/* 1279 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/*      */     try {
/* 1283 */       retVal = new byte[length];
/* 1284 */       if (array == null || array.length == 0) {
/* 1285 */         for (int i = 0; i < length; i++) {
/* 1286 */           retVal[i] = 0;
/*      */         }
/* 1288 */       } else if (array.length < length) {
/*      */         
/* 1290 */         System.arraycopy(array, 0, retVal, 0, array.length);
/* 1291 */         for (int i = array.length; i < length; i++) {
/* 1292 */           retVal[i] = 0;
/*      */         }
/*      */       } else {
/*      */         
/* 1296 */         System.arraycopy(array, 0, retVal, 0, length);
/*      */       }
/*      */     
/* 1299 */     } catch (Exception ex) {
/* 1300 */       if (Trace.isOn) {
/* 1301 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setArrayToLength(byte [ ],int)", ex);
/*      */       }
/*      */       
/* 1304 */       MQException traceRet1 = new MQException(2, 2195, "MQJE044");
/* 1305 */       if (Trace.isOn) {
/* 1306 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setArrayToLength(byte [ ],int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1309 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1312 */     if (Trace.isOn) {
/* 1313 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setArrayToLength(byte [ ],int)", retVal);
/*      */     }
/*      */     
/* 1316 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setDateAndTime(int year, int month, int day, int hour, int minute, int second, int millis) {
/* 1328 */     if (Trace.isOn) {
/* 1329 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setDateAndTime(int,int,int,int,int,int,int)", new Object[] {
/* 1330 */             Integer.valueOf(year), 
/* 1331 */             Integer.valueOf(month), Integer.valueOf(day), Integer.valueOf(hour), 
/* 1332 */             Integer.valueOf(minute), Integer.valueOf(second), Integer.valueOf(millis)
/*      */           });
/*      */     }
/* 1335 */     if (this.putDateTime == null) {
/* 1336 */       this.putDateTime = new GregorianCalendar(year, month - 1, day, hour, minute, second);
/*      */     } else {
/* 1338 */       this.putDateTime.set(year, month - 1, day, hour, minute, second);
/*      */     } 
/*      */     
/* 1341 */     this.putDateTime.setTimeZone(GMT);
/* 1342 */     this.putDateTime.set(14, millis);
/* 1343 */     if (Trace.isOn) {
/* 1344 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setDateAndTime(int,int,int,int,int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) throws MQException {
/* 1358 */     if (Trace.isOn) {
/* 1359 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setVersion(int)", "setter", 
/* 1360 */           Integer.valueOf(version));
/*      */     }
/*      */     
/* 1363 */     if (version > 2 || version < 1) {
/* 1364 */       MQException traceRet1 = new MQException(2, 2026, this, "MQJI013", "" + version);
/* 1365 */       if (Trace.isOn) {
/* 1366 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setVersion(int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1369 */       throw traceRet1;
/*      */     } 
/* 1371 */     this.version = version;
/* 1372 */     if (version == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1382 */       boolean invalidGroupId = false;
/*      */       
/* 1384 */       if (this.groupId != null) {
/* 1385 */         for (int i = 0; i < this.groupId.length; i++) {
/* 1386 */           if (this.groupId[i] != 0) {
/* 1387 */             invalidGroupId = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/* 1393 */       if (invalidGroupId || this.messageSequenceNumber != 1 || this.offset != 0 || this.messageFlags != 0 || this.originalLength != -1) {
/*      */ 
/*      */         
/* 1396 */         MQException traceRet2 = new MQException(2, 2026, this, "MQJE055");
/* 1397 */         if (Trace.isOn) {
/* 1398 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "setVersion(int)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 1401 */         throw traceRet2;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int sizeOfMQMD() {
/* 1413 */     if (Trace.isOn) {
/* 1414 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "sizeOfMQMD()");
/*      */     }
/* 1416 */     if (this.version == 1) {
/* 1417 */       if (Trace.isOn) {
/* 1418 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "sizeOfMQMD()", 
/* 1419 */             Integer.valueOf(324), 1);
/*      */       }
/* 1421 */       return 324;
/*      */     } 
/* 1423 */     if (Trace.isOn) {
/* 1424 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "sizeOfMQMD()", 
/* 1425 */           Integer.valueOf(364), 2);
/*      */     }
/* 1427 */     return 364;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateFromJMQIStructure(int pmoFlags) {
/* 1436 */     this.version = this.jmqiStructure.getVersion();
/* 1437 */     this.report = this.jmqiStructure.getReport();
/* 1438 */     this.messageType = this.jmqiStructure.getMsgType();
/* 1439 */     this.expiry = this.jmqiStructure.getExpiry();
/* 1440 */     this.feedback = this.jmqiStructure.getFeedback();
/* 1441 */     this.encoding = this.jmqiStructure.getEncoding();
/* 1442 */     this.characterSet = this.jmqiStructure.getCodedCharSetId();
/* 1443 */     this.format = this.jmqiStructure.getFormat();
/* 1444 */     this.priority = this.jmqiStructure.getPriority();
/* 1445 */     this.persistence = this.jmqiStructure.getPersistence();
/* 1446 */     this.messageId = new byte[24];
/* 1447 */     System.arraycopy(this.jmqiStructure.getMsgId(), 0, this.messageId, 0, 24);
/* 1448 */     this.correlationId = new byte[24];
/* 1449 */     System.arraycopy(this.jmqiStructure.getCorrelId(), 0, this.correlationId, 0, 24);
/* 1450 */     this.backoutCount = this.jmqiStructure.getBackoutCount();
/* 1451 */     this.replyToQueueName = this.jmqiStructure.getReplyToQ();
/* 1452 */     this.replyToQueueManagerName = this.jmqiStructure.getReplyToQMgr();
/*      */     
/* 1454 */     boolean nSetAllCtx = ((pmoFlags & 0x800) == 0);
/* 1455 */     boolean nSetIdCts = (nSetAllCtx && (pmoFlags & 0x400) == 0);
/*      */     
/* 1457 */     if (nSetIdCts) {
/* 1458 */       this.userId = this.jmqiStructure.getUserIdentifier();
/* 1459 */       this.accountingToken = new byte[32];
/* 1460 */       System.arraycopy(this.jmqiStructure.getAccountingToken(), 0, this.accountingToken, 0, 32);
/* 1461 */       this.applicationIdData = this.jmqiStructure.getApplIdentityData();
/*      */     } 
/* 1463 */     if (nSetAllCtx) {
/* 1464 */       this.putApplicationType = this.jmqiStructure.getPutApplType();
/* 1465 */       this.putApplicationName = this.jmqiStructure.getPutApplName();
/* 1466 */       this.applicationOriginData = this.jmqiStructure.getApplOriginData();
/* 1467 */       this.putDateTime = getDateAndTime(this.jmqiStructure.getPutDate(), this.jmqiStructure.getPutTime());
/*      */     } 
/* 1469 */     if (this.version >= 2) {
/* 1470 */       this.groupId = new byte[24];
/* 1471 */       System.arraycopy(this.jmqiStructure.getGroupId(), 0, this.groupId, 0, 24);
/* 1472 */       this.messageSequenceNumber = this.jmqiStructure.getMsgSeqNumber();
/* 1473 */       this.offset = this.jmqiStructure.getOffset();
/* 1474 */       this.messageFlags = this.jmqiStructure.getMsgFlags();
/* 1475 */       this.originalLength = this.jmqiStructure.getOriginalLength();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DataOutputStream writeTo(DataOutputStream dataBuffer, int ccsid, boolean ccsidIsAscii) throws IOException, MQException {
/* 1494 */     if (Trace.isOn) {
/* 1495 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "writeTo(java.io.DataOutputStream,int,boolean)", new Object[] { dataBuffer, 
/*      */             
/* 1497 */             Integer.valueOf(ccsid), Boolean.valueOf(ccsidIsAscii) });
/*      */     }
/* 1499 */     dataBuffer.writeBytes("MD  ");
/* 1500 */     dataBuffer.writeInt(this.version);
/* 1501 */     dataBuffer.writeInt(this.report);
/* 1502 */     dataBuffer.writeInt(this.messageType);
/* 1503 */     dataBuffer.writeInt(this.expiry);
/* 1504 */     dataBuffer.writeInt(this.feedback);
/* 1505 */     dataBuffer.writeInt(this.encoding);
/* 1506 */     dataBuffer.writeInt(this.characterSet);
/*      */     
/* 1508 */     String fmt = null;
/* 1509 */     fmt = MQSESSION.setStringToLength(this.format, 8);
/* 1510 */     dataBuffer.write(makeBytesFromString(fmt, ccsid, ccsidIsAscii, false));
/*      */     
/* 1512 */     dataBuffer.writeInt(this.priority);
/* 1513 */     dataBuffer.writeInt(this.persistence);
/*      */     
/* 1515 */     this.messageId = setArrayToLength(this.messageId, 24);
/* 1516 */     dataBuffer.write(this.messageId, 0, 24);
/*      */     
/* 1518 */     this.correlationId = setArrayToLength(this.correlationId, 24);
/* 1519 */     dataBuffer.write(this.correlationId, 0, 24);
/*      */     
/* 1521 */     dataBuffer.writeInt(this.backoutCount);
/*      */     
/* 1523 */     String replyToQ = null;
/* 1524 */     replyToQ = MQSESSION.setStringToLength(this.replyToQueueName, 48);
/* 1525 */     dataBuffer.write(makeBytesFromString(replyToQ, ccsid, ccsidIsAscii, true));
/*      */     
/* 1527 */     String replyToQMgr = null;
/* 1528 */     replyToQMgr = MQSESSION.setStringToLength(this.replyToQueueManagerName, 48);
/* 1529 */     dataBuffer.write(makeBytesFromString(replyToQMgr, ccsid, ccsidIsAscii, true));
/*      */     
/* 1531 */     String userID = null;
/* 1532 */     userID = MQSESSION.setStringToLength(this.userId, 12);
/* 1533 */     dataBuffer.write(makeBytesFromString(userID, ccsid, ccsidIsAscii, false));
/*      */     
/*      */     try {
/* 1536 */       this.accountingToken = setArrayToLength(this.accountingToken, 32);
/*      */     }
/* 1538 */     catch (Exception e) {
/* 1539 */       if (Trace.isOn) {
/* 1540 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "writeTo(java.io.DataOutputStream,int,boolean)", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1545 */     dataBuffer.write(this.accountingToken, 0, 32);
/*      */     
/* 1547 */     String applId = null;
/* 1548 */     applId = MQSESSION.setStringToLength(this.applicationIdData, 32);
/* 1549 */     dataBuffer.write(makeBytesFromString(applId, ccsid, ccsidIsAscii, false));
/*      */     
/* 1551 */     dataBuffer.writeInt(this.putApplicationType);
/*      */     
/* 1553 */     String applName = null;
/* 1554 */     applName = MQSESSION.setStringToLength(this.putApplicationName, 28);
/* 1555 */     dataBuffer.write(makeBytesFromString(applName, ccsid, ccsidIsAscii, false));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1567 */     if (this.putDateTime == null) {
/* 1568 */       String date = getDate(null);
/* 1569 */       String time = getTime(null);
/* 1570 */       dataBuffer.writeBytes(makeStringFromBytes(date.getBytes(Charset.defaultCharset()), ccsid, ccsidIsAscii, true));
/* 1571 */       dataBuffer.writeBytes(makeStringFromBytes(time.getBytes(Charset.defaultCharset()), ccsid, ccsidIsAscii, true));
/*      */     } else {
/*      */       
/* 1574 */       if (this.putCalendar == null)
/*      */       {
/* 1576 */         this.putCalendar = new GregorianCalendar(GMT);
/*      */       }
/*      */       
/* 1579 */       this.putCalendar.setTime(this.putDateTime.getTime());
/*      */       
/* 1581 */       String date = getDate(this.putCalendar);
/*      */       
/* 1583 */       dataBuffer.writeBytes(makeStringFromBytes(date.getBytes(Charset.defaultCharset()), ccsid, ccsidIsAscii, true));
/*      */       
/* 1585 */       String time = getTime(this.putCalendar);
/*      */       
/* 1587 */       dataBuffer.writeBytes(makeStringFromBytes(time.getBytes(Charset.defaultCharset()), ccsid, ccsidIsAscii, true));
/*      */     } 
/*      */     
/* 1590 */     String originData = null;
/*      */     
/* 1592 */     originData = MQSESSION.setStringToLength(this.applicationOriginData, 4);
/* 1593 */     dataBuffer.write(makeBytesFromString(originData, ccsid, ccsidIsAscii, false));
/*      */ 
/*      */     
/* 1596 */     if (this.version > 1) {
/* 1597 */       this.groupId = setArrayToLength(this.groupId, 24);
/* 1598 */       dataBuffer.write(this.groupId);
/* 1599 */       dataBuffer.writeInt(this.messageSequenceNumber);
/* 1600 */       dataBuffer.writeInt(this.offset);
/* 1601 */       dataBuffer.writeInt(this.messageFlags);
/* 1602 */       dataBuffer.writeInt(this.originalLength);
/*      */     } 
/*      */     
/* 1605 */     if (Trace.isOn) {
/* 1606 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMD", "writeTo(java.io.DataOutputStream,int,boolean)", dataBuffer);
/*      */     }
/*      */     
/* 1609 */     return dataBuffer;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQMD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */