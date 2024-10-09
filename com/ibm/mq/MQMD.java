/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.nio.charset.CodingErrorAction;
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
/*      */ public class MQMD
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQMD.java";
/*      */   
/*      */   static {
/*  105 */     if (Trace.isOn) {
/*  106 */       Trace.data("com.ibm.mq.MQMD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQMD.java");
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
/*  120 */   private MQMD jmqiStructure = MQSESSION.getJmqiEnv().newMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  198 */   public int report = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   public int messageType = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  227 */   public int expiry = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  267 */   public int feedback = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  321 */   public int encoding = 273;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  345 */   public int characterSet = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  359 */   public CodingErrorAction unmappableAction = JmqiCodepage.getUnmappableCharacterDefaultAction(this.env);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  374 */   public byte[] unMappableReplacement = JmqiCodepage.getUnmappableCharacterDefaultReplacement(this.env);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  402 */   public String format = "        ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  411 */   public int priority = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  425 */   public int persistence = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  444 */   public byte[] messageId = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  462 */   public byte[] correlationId = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  473 */   public int backoutCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  484 */   public String replyToQueueName = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  492 */   public String replyToQueueManagerName = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  501 */   public String userId = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  511 */   public byte[] accountingToken = new byte[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  523 */   public String applicationIdData = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  563 */   public int putApplicationType = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  571 */   public String putApplicationName = "";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  576 */   public GregorianCalendar putDateTime = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  586 */   public String applicationOriginData = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  593 */   public byte[] groupId = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  598 */   public int messageSequenceNumber = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  603 */   public int offset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  617 */   public int messageFlags = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  622 */   public int originalLength = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  630 */     if (Trace.isOn) {
/*  631 */       Trace.data(this, "com.ibm.mq.MQMD", "getVersion()", "getter", Integer.valueOf(this.version));
/*      */     }
/*  633 */     return this.version;
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
/*      */   public void setVersion(int version) throws MQException {
/*  648 */     if (Trace.isOn) {
/*  649 */       Trace.data(this, "com.ibm.mq.MQMD", "setVersion(int)", "setter", Integer.valueOf(version));
/*      */     }
/*  651 */     if (version > 2 || version < 1) {
/*  652 */       MQException traceRet1 = new MQException(2, 2026, this, "MQJI013", "" + version);
/*      */       
/*  654 */       if (Trace.isOn) {
/*  655 */         Trace.throwing(this, "com.ibm.mq.MQMD", "setVersion(int)", traceRet1, 1);
/*      */       }
/*  657 */       throw traceRet1;
/*      */     } 
/*  659 */     this.version = version;
/*  660 */     if (version == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  670 */       boolean invalidGroupId = false;
/*      */       
/*  672 */       if (this.groupId != null) {
/*  673 */         for (int i = 0; i < this.groupId.length; i++) {
/*  674 */           if (this.groupId[i] != 0) {
/*  675 */             invalidGroupId = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  681 */       if (invalidGroupId || this.messageSequenceNumber != 1 || this.offset != 0 || this.messageFlags != 0 || this.originalLength != -1) {
/*      */ 
/*      */         
/*  684 */         MQException traceRet2 = new MQException(2, 2026, this, "MQJE055");
/*  685 */         if (Trace.isOn) {
/*  686 */           Trace.throwing(this, "com.ibm.mq.MQMD", "setVersion(int)", traceRet2, 2);
/*      */         }
/*  688 */         throw traceRet2;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  696 */   private int version = 2;
/*      */ 
/*      */   
/*      */   protected static final int sizeofMQMDv1 = 324;
/*      */   
/*      */   protected static final int sizeofMQMDv2 = 364;
/*      */ 
/*      */   
/*      */   public MQMD() {
/*  705 */     super(MQSESSION.getJmqiEnv());
/*  706 */     if (Trace.isOn) {
/*  707 */       Trace.entry(this, "com.ibm.mq.MQMD", "<init>()");
/*      */     }
/*      */     
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.exit(this, "com.ibm.mq.MQMD", "<init>()");
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
/*      */   protected final int sizeOfMQMD() {
/*  726 */     if (Trace.isOn) {
/*  727 */       Trace.entry(this, "com.ibm.mq.MQMD", "sizeOfMQMD()");
/*      */     }
/*  729 */     if (this.version == 1) {
/*      */       
/*  731 */       if (Trace.isOn) {
/*  732 */         Trace.exit(this, "com.ibm.mq.MQMD", "sizeOfMQMD()", Integer.valueOf(324), 1);
/*      */       }
/*  734 */       return 324;
/*      */     } 
/*  736 */     if (Trace.isOn) {
/*  737 */       Trace.exit(this, "com.ibm.mq.MQMD", "sizeOfMQMD()", Integer.valueOf(364), 2);
/*      */     }
/*  739 */     return 364;
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
/*      */   protected final byte[] setArrayToLength(byte[] array, int length) throws MQException {
/*      */     byte[] retVal;
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.entry(this, "com.ibm.mq.MQMD", "setArrayToLength(byte [ ],int)", new Object[] { array, 
/*  754 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/*      */     try {
/*  758 */       retVal = new byte[length];
/*  759 */       if (array == null || array.length == 0) {
/*  760 */         for (int i = 0; i < length; i++) {
/*  761 */           retVal[i] = 0;
/*      */         }
/*  763 */       } else if (array.length < length) {
/*      */         
/*  765 */         System.arraycopy(array, 0, retVal, 0, array.length);
/*  766 */         for (int i = array.length; i < length; i++) {
/*  767 */           retVal[i] = 0;
/*      */         }
/*      */       } else {
/*      */         
/*  771 */         System.arraycopy(array, 0, retVal, 0, length);
/*      */       }
/*      */     
/*  774 */     } catch (Exception ex) {
/*  775 */       if (Trace.isOn) {
/*  776 */         Trace.catchBlock(this, "com.ibm.mq.MQMD", "setArrayToLength(byte [ ],int)", ex);
/*      */       }
/*      */       
/*  779 */       MQException traceRet1 = new MQException(2, 2195, this, "MQJE044");
/*  780 */       if (Trace.isOn) {
/*  781 */         Trace.throwing(this, "com.ibm.mq.MQMD", "setArrayToLength(byte [ ],int)", traceRet1);
/*      */       }
/*  783 */       throw traceRet1;
/*      */     } 
/*      */     
/*  786 */     if (Trace.isOn) {
/*  787 */       Trace.exit(this, "com.ibm.mq.MQMD", "setArrayToLength(byte [ ],int)", retVal);
/*      */     }
/*  789 */     return retVal;
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
/*      */   protected final GregorianCalendar getDateAndTime(String date, String time) {
/*  804 */     if (Trace.isOn) {
/*  805 */       Trace.entry(this, "com.ibm.mq.MQMD", "getDateAndTime(String,String)", new Object[] { date, time });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  812 */     GregorianCalendar retVal = null;
/*  813 */     char[] rawPutDate = "00010101".toCharArray();
/*  814 */     char[] rawPutTime = "00000000".toCharArray();
/*  815 */     boolean someDateTimeProvided = false;
/*  816 */     if (date != null && date.trim().length() == 8) {
/*  817 */       rawPutDate = date.toCharArray();
/*  818 */       someDateTimeProvided = true;
/*      */     } 
/*  820 */     if (time != null && time.trim().length() == 8) {
/*  821 */       rawPutTime = time.toCharArray();
/*  822 */       someDateTimeProvided = true;
/*      */     } 
/*  824 */     if (someDateTimeProvided) {
/*      */       
/*  826 */       int year = (rawPutDate[0] - 48) * 1000;
/*  827 */       year += (rawPutDate[1] - 48) * 100;
/*  828 */       year += (rawPutDate[2] - 48) * 10;
/*  829 */       year += rawPutDate[3] - 48;
/*      */       
/*  831 */       int month = (rawPutDate[4] - 48) * 10;
/*  832 */       month += rawPutDate[5] - 48;
/*  833 */       month--;
/*      */       
/*  835 */       int day = (rawPutDate[6] - 48) * 10;
/*  836 */       day += rawPutDate[7] - 48;
/*      */       
/*  838 */       int hour = (rawPutTime[0] - 48) * 10;
/*  839 */       hour += rawPutTime[1] - 48;
/*      */       
/*  841 */       int minute = (rawPutTime[2] - 48) * 10;
/*  842 */       minute += rawPutTime[3] - 48;
/*      */       
/*  844 */       int second = (rawPutTime[4] - 48) * 10;
/*  845 */       second += rawPutTime[5] - 48;
/*      */       
/*  847 */       int millisecond = (rawPutTime[6] - 48) * 10;
/*  848 */       millisecond += rawPutTime[7] - 48;
/*  849 */       millisecond *= 10;
/*      */       
/*  851 */       retVal = new GregorianCalendar(year, month, day, hour, minute, second);
/*  852 */       retVal.setTimeZone(GMT);
/*  853 */       retVal.set(14, millisecond);
/*      */     } else {
/*  855 */       if (Trace.isOn) {
/*  856 */         Trace.data(this, "getDateAndTime(String,String)", "Invalid Date/Time. Date:", date);
/*  857 */         Trace.data(this, "getDateAndTime(String,String)", "Invalid Date/Time. Time:", time);
/*      */       } 
/*  859 */       retVal = null;
/*      */     } 
/*      */     
/*  862 */     if (Trace.isOn) {
/*  863 */       Trace.exit(this, "com.ibm.mq.MQMD", "getDateAndTime(String,String)", retVal);
/*      */     }
/*  865 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String getDate(GregorianCalendar dateTime) {
/*      */     String strDate;
/*  876 */     if (Trace.isOn) {
/*  877 */       Trace.entry("com.ibm.mq.MQMD", "getDate(GregorianCalendar)", new Object[] { dateTime });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  882 */     if (dateTime != null) {
/*      */ 
/*      */ 
/*      */       
/*  886 */       strDate = setNumberToLength(Integer.toString(dateTime.get(1)), 4) + setNumberToLength(Integer.toString(dateTime.get(2) + 1), 2) + setNumberToLength(Integer.toString(dateTime.get(5)), 2);
/*      */     } else {
/*  888 */       strDate = "        ";
/*      */     } 
/*      */     
/*  891 */     if (Trace.isOn) {
/*  892 */       Trace.exit("com.ibm.mq.MQMD", "getDate(GregorianCalendar)", strDate);
/*      */     }
/*  894 */     return strDate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String getTime(GregorianCalendar dateTime) {
/*      */     String strTime;
/*  906 */     if (Trace.isOn) {
/*  907 */       Trace.entry("com.ibm.mq.MQMD", "getTime(GregorianCalendar)", new Object[] { dateTime });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  912 */     if (dateTime != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  917 */       strTime = setNumberToLength(Integer.toString(dateTime.get(11)), 2) + setNumberToLength(Integer.toString(dateTime.get(12)), 2) + setNumberToLength(Integer.toString(dateTime.get(13)), 2) + setNumberToLength(Integer.toString(dateTime.get(14) / 10), 2);
/*      */     } else {
/*  919 */       strTime = "        ";
/*      */     } 
/*      */     
/*  922 */     if (Trace.isOn) {
/*  923 */       Trace.exit("com.ibm.mq.MQMD", "getTime(GregorianCalendar)", strTime);
/*      */     }
/*  925 */     return strTime;
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
/*      */   private static final String setNumberToLength(String strNumber, int iNewLength) {
/*      */     String strPadded;
/*  938 */     if (Trace.isOn) {
/*  939 */       Trace.entry("com.ibm.mq.MQMD", "setNumberToLength(String,int)", new Object[] { strNumber, 
/*  940 */             Integer.valueOf(iNewLength) });
/*      */     }
/*  942 */     int iOldLength = strNumber.length();
/*      */ 
/*      */     
/*  945 */     if (iOldLength > iNewLength) {
/*      */ 
/*      */       
/*  948 */       strPadded = strNumber.substring(0, iNewLength);
/*      */     }
/*  950 */     else if (iOldLength < iNewLength) {
/*  951 */       int leading = iNewLength - iOldLength;
/*  952 */       char[] padArray = new char[iNewLength];
/*  953 */       Arrays.fill(padArray, 0, leading, '0');
/*  954 */       System.arraycopy(strNumber.toCharArray(), 0, padArray, leading, iOldLength);
/*  955 */       strPadded = new String(padArray);
/*      */     } else {
/*  957 */       strPadded = strNumber;
/*      */     } 
/*      */     
/*  960 */     if (Trace.isOn) {
/*  961 */       Trace.exit("com.ibm.mq.MQMD", "setNumberToLength(String,int)", strPadded);
/*      */     }
/*  963 */     return strPadded;
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
/*      */ 
/*      */   
/*      */   protected final void setDateAndTime(int year, int month, int day, int hour, int minute, int second, int millis) {
/*  986 */     if (Trace.isOn)
/*  987 */       Trace.entry(this, "com.ibm.mq.MQMD", "setDateAndTime(int,int,int,int,int,int,int)", new Object[] {
/*  988 */             Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), 
/*  989 */             Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second), 
/*  990 */             Integer.valueOf(millis)
/*      */           }); 
/*  992 */     if (this.putDateTime == null) {
/*  993 */       this.putDateTime = new GregorianCalendar(year, month - 1, day, hour, minute, second);
/*      */     } else {
/*  995 */       this.putDateTime.set(year, month - 1, day, hour, minute, second);
/*      */     } 
/*  997 */     this.putDateTime.setTimeZone(GMT);
/*  998 */     this.putDateTime.set(14, millis);
/*      */     
/* 1000 */     if (Trace.isOn) {
/* 1001 */       Trace.exit(this, "com.ibm.mq.MQMD", "setDateAndTime(int,int,int,int,int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1007 */   private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
/* 1008 */   private static final GregorianCalendar gmtConverter = new GregorianCalendar(GMT);
/*      */ 
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
/* 1020 */     if (Trace.isOn) {
/* 1021 */       Trace.entry(this, "com.ibm.mq.MQMD", "convertCalendarToStrings(GregorianCalendar)", new Object[] { calendar });
/*      */     }
/*      */     
/* 1024 */     String[] jmqiPutDateTime = new String[2];
/* 1025 */     char[] rawPutDate = new char[8];
/* 1026 */     char[] rawPutTime = new char[8];
/* 1027 */     if (calendar == null) {
/*      */       
/* 1029 */       Arrays.fill(rawPutDate, ' ');
/* 1030 */       Arrays.fill(rawPutTime, ' ');
/*      */     } else {
/*      */       int year, month, day, hour, minute, second, hundredth;
/*      */ 
/*      */ 
/*      */       
/* 1036 */       synchronized (gmtConverter) {
/* 1037 */         gmtConverter.setTime(calendar.getTime());
/* 1038 */         year = gmtConverter.get(1);
/* 1039 */         month = gmtConverter.get(2) + 1;
/* 1040 */         day = gmtConverter.get(5);
/* 1041 */         hour = gmtConverter.get(11);
/* 1042 */         minute = gmtConverter.get(12);
/* 1043 */         second = gmtConverter.get(13);
/* 1044 */         hundredth = gmtConverter.get(14) / 10;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       int contrib;
/*      */ 
/*      */       
/* 1052 */       rawPutDate[0] = (char)(48 + (contrib = year / 1000));
/* 1053 */       year -= contrib * 1000;
/* 1054 */       rawPutDate[1] = (char)(48 + (contrib = year / 100));
/* 1055 */       year -= contrib * 100;
/* 1056 */       rawPutDate[2] = (char)(48 + (contrib = year / 10));
/* 1057 */       year -= contrib * 10;
/* 1058 */       rawPutDate[3] = (char)(48 + (contrib = year));
/*      */       
/* 1060 */       rawPutDate[4] = (char)(48 + (contrib = month / 10));
/* 1061 */       month -= contrib * 10;
/* 1062 */       rawPutDate[5] = (char)(48 + (contrib = month));
/*      */       
/* 1064 */       rawPutDate[6] = (char)(48 + (contrib = day / 10));
/* 1065 */       day -= contrib * 10;
/* 1066 */       rawPutDate[7] = (char)(48 + (contrib = day));
/*      */ 
/*      */       
/* 1069 */       rawPutTime[0] = (char)(48 + (contrib = hour / 10));
/* 1070 */       hour -= contrib * 10;
/* 1071 */       rawPutTime[1] = (char)(48 + (contrib = hour));
/*      */       
/* 1073 */       rawPutTime[2] = (char)(48 + (contrib = minute / 10));
/* 1074 */       minute -= contrib * 10;
/* 1075 */       rawPutTime[3] = (char)(48 + (contrib = minute));
/*      */       
/* 1077 */       rawPutTime[4] = (char)(48 + (contrib = second / 10));
/* 1078 */       second -= contrib * 10;
/* 1079 */       rawPutTime[5] = (char)(48 + (contrib = second));
/*      */       
/* 1081 */       rawPutTime[6] = (char)(48 + (contrib = hundredth / 10));
/* 1082 */       hundredth -= contrib * 10;
/* 1083 */       rawPutTime[7] = (char)(48 + (contrib = hundredth));
/*      */     } 
/*      */     
/* 1086 */     jmqiPutDateTime[0] = new String(rawPutDate);
/* 1087 */     jmqiPutDateTime[1] = new String(rawPutTime);
/*      */     
/* 1089 */     if (Trace.isOn) {
/* 1090 */       Trace.exit(this, "com.ibm.mq.MQMD", "convertCalendarToStrings(GregorianCalendar)", jmqiPutDateTime);
/*      */     }
/*      */     
/* 1093 */     return jmqiPutDateTime;
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
/*      */   protected MQMD getJMQIStructure(int pmoFlags) {
/* 1110 */     this.jmqiStructure.setVersion(this.version);
/* 1111 */     this.jmqiStructure.setReport(this.report);
/* 1112 */     this.jmqiStructure.setMsgType(this.messageType);
/* 1113 */     this.jmqiStructure.setExpiry(this.expiry);
/* 1114 */     this.jmqiStructure.setFeedback(this.feedback);
/* 1115 */     this.jmqiStructure.setEncoding(this.encoding);
/* 1116 */     this.jmqiStructure.setCodedCharSetId(this.characterSet);
/* 1117 */     this.jmqiStructure.setUnmappableAction(this.unmappableAction);
/* 1118 */     this.jmqiStructure.setUnMappableReplacement(this.unMappableReplacement);
/* 1119 */     this.jmqiStructure.setFormat(this.format);
/* 1120 */     this.jmqiStructure.setPriority(this.priority);
/* 1121 */     this.jmqiStructure.setPersistence(this.persistence);
/* 1122 */     this.jmqiStructure.setMsgId(this.messageId);
/* 1123 */     this.jmqiStructure.setCorrelId(this.correlationId);
/* 1124 */     this.jmqiStructure.setBackoutCount(this.backoutCount);
/* 1125 */     this.jmqiStructure.setReplyToQ(this.replyToQueueName);
/* 1126 */     this.jmqiStructure.setReplyToQMgr(this.replyToQueueManagerName);
/*      */     
/* 1128 */     boolean setAllCtx = ((pmoFlags & 0x800) != 0);
/* 1129 */     boolean setIdCts = (setAllCtx || (pmoFlags & 0x400) != 0);
/*      */     
/* 1131 */     if (setIdCts) {
/* 1132 */       this.jmqiStructure.setUserIdentifier(this.userId);
/* 1133 */       this.jmqiStructure.setAccountingToken(this.accountingToken);
/* 1134 */       this.jmqiStructure.setApplIdentityData(this.applicationIdData);
/*      */     } 
/*      */     
/* 1137 */     if (setAllCtx) {
/* 1138 */       this.jmqiStructure.setPutApplType(this.putApplicationType);
/* 1139 */       this.jmqiStructure.setPutApplName(this.putApplicationName);
/* 1140 */       this.jmqiStructure.setApplOriginData(this.applicationOriginData);
/* 1141 */       String[] jmqiPutDateTime = convertCalendarToStrings(this.putDateTime);
/* 1142 */       this.jmqiStructure.setPutDate(jmqiPutDateTime[0]);
/* 1143 */       this.jmqiStructure.setPutTime(jmqiPutDateTime[1]);
/*      */     } 
/*      */ 
/*      */     
/* 1147 */     if (this.version >= 2) {
/* 1148 */       this.jmqiStructure.setGroupId(this.groupId);
/* 1149 */       this.jmqiStructure.setMsgSeqNumber(this.messageSequenceNumber);
/* 1150 */       this.jmqiStructure.setOffset(this.offset);
/* 1151 */       this.jmqiStructure.setMsgFlags(this.messageFlags);
/* 1152 */       this.jmqiStructure.setOriginalLength(this.originalLength);
/*      */     } 
/*      */     
/* 1155 */     return this.jmqiStructure;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateFromJMQIStructure(int pmoFlags) {
/* 1166 */     this.version = this.jmqiStructure.getVersion();
/* 1167 */     this.report = this.jmqiStructure.getReport();
/* 1168 */     this.messageType = this.jmqiStructure.getMsgType();
/* 1169 */     this.expiry = this.jmqiStructure.getExpiry();
/* 1170 */     this.feedback = this.jmqiStructure.getFeedback();
/* 1171 */     this.encoding = this.jmqiStructure.getEncoding();
/* 1172 */     this.characterSet = this.jmqiStructure.getCodedCharSetId();
/* 1173 */     this.format = this.jmqiStructure.getFormat();
/* 1174 */     this.priority = this.jmqiStructure.getPriority();
/* 1175 */     this.persistence = this.jmqiStructure.getPersistence();
/* 1176 */     this.messageId = new byte[24];
/* 1177 */     System.arraycopy(this.jmqiStructure.getMsgId(), 0, this.messageId, 0, 24);
/* 1178 */     this.correlationId = new byte[24];
/* 1179 */     System.arraycopy(this.jmqiStructure.getCorrelId(), 0, this.correlationId, 0, 24);
/* 1180 */     this.backoutCount = this.jmqiStructure.getBackoutCount();
/* 1181 */     this.replyToQueueName = this.jmqiStructure.getReplyToQ();
/* 1182 */     this.replyToQueueManagerName = this.jmqiStructure.getReplyToQMgr();
/*      */     
/* 1184 */     boolean nSetAllCtx = ((pmoFlags & 0x800) == 0);
/* 1185 */     boolean nSetIdCts = (nSetAllCtx && (pmoFlags & 0x400) == 0);
/*      */     
/* 1187 */     if (nSetIdCts) {
/* 1188 */       this.userId = this.jmqiStructure.getUserIdentifier();
/* 1189 */       this.accountingToken = new byte[32];
/* 1190 */       System.arraycopy(this.jmqiStructure.getAccountingToken(), 0, this.accountingToken, 0, 32);
/* 1191 */       this.applicationIdData = this.jmqiStructure.getApplIdentityData();
/*      */     } 
/* 1193 */     if (nSetAllCtx) {
/* 1194 */       this.putApplicationType = this.jmqiStructure.getPutApplType();
/* 1195 */       this.putApplicationName = this.jmqiStructure.getPutApplName();
/* 1196 */       this.applicationOriginData = this.jmqiStructure.getApplOriginData();
/* 1197 */       this.putDateTime = getDateAndTime(this.jmqiStructure.getPutDate(), this.jmqiStructure.getPutTime());
/*      */     } 
/* 1199 */     if (this.version >= 2) {
/* 1200 */       this.groupId = new byte[24];
/* 1201 */       System.arraycopy(this.jmqiStructure.getGroupId(), 0, this.groupId, 0, 24);
/* 1202 */       this.messageSequenceNumber = this.jmqiStructure.getMsgSeqNumber();
/* 1203 */       this.offset = this.jmqiStructure.getOffset();
/* 1204 */       this.messageFlags = this.jmqiStructure.getMsgFlags();
/* 1205 */       this.originalLength = this.jmqiStructure.getOriginalLength();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQMD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */