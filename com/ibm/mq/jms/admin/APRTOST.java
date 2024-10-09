/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQDestination;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APRTOST
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRTOST.java";
/*     */   public static final String LONGNAME = "REPLYTOSTYLE";
/*     */   public static final String SHORTNAME = "RTOST";
/*     */   public static final String RS_DEFAULT = "DEFAULT";
/*     */   public static final String RS_MQMD = "MQMD";
/*     */   public static final String RS_RFH2 = "RFH2";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APRTOST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRTOST.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  90 */       Object value = getProperty("RTOST", props);
/*     */ 
/*     */       
/*  93 */       if (value != null) {
/*  94 */         int iVal; if (value instanceof Integer) {
/*  95 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*  98 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 102 */         if (obj instanceof MQDestination) {
/* 103 */           ((MQDestination)obj).setReplyToStyle(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 107 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 108 */           String key = "JMSADM1016";
/* 109 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 110 */           JMSException iee = new JMSException(msg, key);
/* 111 */           if (Trace.isOn) {
/* 112 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 115 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 120 */     } catch (BAOException e) {
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */       }
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 129 */       throw e;
/*     */     
/*     */     }
/* 132 */     catch (JMSException e) {
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 141 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRTOST", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRTOST", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 172 */       if (obj instanceof MQDestination) {
/* 173 */         iVal = ((MQDestination)obj).getReplyToStyle();
/*     */       }
/*     */       else {
/*     */         
/* 177 */         String detail = "object is an unexpected type";
/* 178 */         String key = "JMSADM1016";
/* 179 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 180 */         JMSException iee = new JMSException(msg, key);
/* 181 */         if (Trace.isOn) {
/* 182 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APRTOST", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 185 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 191 */       String sVal = "DEFAULT";
/* 192 */       if (iVal == 1) {
/* 193 */         sVal = "MQMD";
/*     */       }
/* 195 */       else if (iVal == 2) {
/* 196 */         sVal = "RFH2";
/*     */       } 
/* 198 */       props.put("REPLYTOSTYLE", sVal);
/*     */     }
/*     */     finally {
/*     */       
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRTOST", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRTOST", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String longName() {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRTOST", "longName()");
/*     */     }
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRTOST", "longName()", "REPLYTOSTYLE");
/*     */     }
/* 226 */     return "REPLYTOSTYLE";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRTOST", "shortName()");
/*     */     }
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRTOST", "shortName()", "RTOST");
/*     */     }
/* 240 */     return "RTOST";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/*     */     int iVal;
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry("com.ibm.mq.jms.admin.APRTOST", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 256 */     String str = s.toUpperCase();
/* 257 */     if (str.equals("DEFAULT")) {
/* 258 */       iVal = 0;
/*     */     }
/* 260 */     else if (str.equals("MQMD")) {
/* 261 */       iVal = 1;
/*     */     }
/* 263 */     else if (str.equals("RFH2")) {
/* 264 */       iVal = 2;
/*     */     } else {
/*     */       
/* 267 */       BAOException traceRet1 = new BAOException(4, "RTOST", str);
/* 268 */       if (Trace.isOn) {
/* 269 */         Trace.throwing("com.ibm.mq.jms.admin.APRTOST", "stringToVal(String)", traceRet1);
/*     */       }
/* 271 */       throw traceRet1;
/*     */     } 
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit("com.ibm.mq.jms.admin.APRTOST", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 276 */     return iVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APRTOST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */