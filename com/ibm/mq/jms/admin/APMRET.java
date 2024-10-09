/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APMRET
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMRET.java";
/*     */   public static final String LONGNAME = "MSGRETENTION";
/*     */   public static final String SHORTNAME = "MRET";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APMRET", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMRET.java");
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
/*     */   public static String valToString(int mret) throws JMSException {
/*     */     String sVal;
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry("com.ibm.mq.jms.admin.APMRET", "valToString(int)", new Object[] {
/*  76 */             Integer.valueOf(mret)
/*     */           });
/*     */     }
/*     */     
/*  80 */     if (mret == 1) {
/*  81 */       sVal = "YES";
/*     */     
/*     */     }
/*  84 */     else if (mret == 0) {
/*  85 */       sVal = "NO";
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  90 */       String detail = "mret unexpected value " + mret;
/*  91 */       String key = "JMSADM1016";
/*  92 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  93 */       JMSException iee = new JMSException(msg, key);
/*  94 */       if (Trace.isOn) {
/*  95 */         Trace.throwing("com.ibm.mq.jms.admin.APMRET", "valToString(int)", (Throwable)iee);
/*     */       }
/*  97 */       throw iee;
/*     */     } 
/*     */     
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit("com.ibm.mq.jms.admin.APMRET", "valToString(int)", sVal);
/*     */     }
/* 103 */     return sVal;
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
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry("com.ibm.mq.jms.admin.APMRET", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 119 */     String str = s.toUpperCase();
/*     */     
/* 121 */     if (str.equals("YES")) {
/* 122 */       iVal = 1;
/*     */     
/*     */     }
/* 125 */     else if (str.equals("NO")) {
/* 126 */       iVal = 0;
/*     */     } else {
/*     */       
/* 129 */       BAOException traceRet1 = new BAOException(4, "MRET", str);
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.throwing("com.ibm.mq.jms.admin.APMRET", "stringToVal(String)", traceRet1);
/*     */       }
/* 133 */       throw traceRet1;
/*     */     } 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit("com.ibm.mq.jms.admin.APMRET", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 139 */     return iVal;
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 160 */       Object value = getProperty("MRET", props);
/*     */ 
/*     */       
/* 163 */       if (value != null) {
/* 164 */         int iVal; if (value instanceof Integer) {
/* 165 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 168 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */         
/* 171 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 173 */             ((MQConnectionFactory)obj).setMessageRetention(iVal);
/*     */           }
/* 175 */           catch (JMSException e) {
/* 176 */             if (Trace.isOn) {
/* 177 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 181 */             BAOException be = new BAOException(4, "MRET", Integer.toString(iVal));
/* 182 */             if (Trace.isOn) {
/* 183 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 186 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 191 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 192 */           String key = "JMSADM1016";
/* 193 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 194 */           JMSException iee = new JMSException(msg, key);
/* 195 */           if (Trace.isOn) {
/* 196 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 199 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 204 */     } catch (BAOException e) {
/* 205 */       if (Trace.isOn) {
/* 206 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 213 */       throw e;
/*     */     
/*     */     }
/* 216 */     catch (JMSException e) {
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 225 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMRET", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMRET", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 259 */       if (obj instanceof com.ibm.mq.jms.MQQueueConnectionFactory || obj instanceof MQConnectionFactory) {
/* 260 */         int mret = ((MQConnectionFactory)obj).getMessageRetention();
/* 261 */         sVal = valToString(mret);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 266 */         String detail = "object is an unexpected type";
/* 267 */         String key = "JMSADM1016";
/* 268 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 269 */         JMSException iee = new JMSException(msg, key);
/* 270 */         if (Trace.isOn) {
/* 271 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APMRET", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 274 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 278 */       props.put("MSGRETENTION", sVal);
/*     */     }
/*     */     finally {
/*     */       
/* 282 */       if (Trace.isOn) {
/* 283 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMRET", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMRET", "setPropertyFromObject(Map<String , Object>,Object)");
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
/*     */   public String longName() {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMRET", "longName()");
/*     */     }
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMRET", "longName()", "MSGRETENTION");
/*     */     }
/* 309 */     return "MSGRETENTION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMRET", "shortName()");
/*     */     }
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMRET", "shortName()", "MRET");
/*     */     }
/* 326 */     return "MRET";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMRET.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */