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
/*     */ public class APSS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSS.java";
/*     */   public static final String LONGNAME = "SUBSTORE";
/*     */   public static final String SHORTNAME = "SS";
/*     */   public static final String SUBSTORE_QUEUE = "QUEUE";
/*     */   public static final String SUBSTORE_BROKER = "BROKER";
/*     */   public static final String SUBSTORE_MIGRATE = "MIGRATE";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APSS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSS.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  92 */       Object value = getProperty("SS", props);
/*     */ 
/*     */ 
/*     */       
/*  96 */       if (value != null) {
/*  97 */         int iVal; if (value instanceof Integer) {
/*  98 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 101 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 105 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 107 */             ((MQConnectionFactory)obj).setSubscriptionStore(iVal);
/*     */           }
/* 109 */           catch (JMSException e) {
/* 110 */             if (Trace.isOn) {
/* 111 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 115 */             BAOException be = new BAOException(4, "SS", Integer.toString(iVal));
/* 116 */             if (Trace.isOn) {
/* 117 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 120 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 125 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 126 */           String key = "JMSADM1016";
/* 127 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 128 */           JMSException iee = new JMSException(msg, key);
/* 129 */           if (Trace.isOn) {
/* 130 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 133 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 138 */     } catch (BAOException e) {
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 147 */       throw e;
/*     */     
/*     */     }
/* 150 */     catch (JMSException e) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 159 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int i;
/*     */       
/* 193 */       if (obj instanceof MQConnectionFactory) {
/* 194 */         i = ((MQConnectionFactory)obj).getSubscriptionStore();
/*     */       }
/*     */       else {
/*     */         
/* 198 */         String detail = "object is an unexpected type";
/* 199 */         String key = "JMSADM1016";
/* 200 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 201 */         JMSException iee = new JMSException(msg, key);
/* 202 */         if (Trace.isOn) {
/* 203 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 206 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 211 */       String value = valToString(i);
/*     */ 
/*     */       
/* 214 */       props.put("SUBSTORE", value);
/*     */     }
/*     */     finally {
/*     */       
/* 218 */       if (Trace.isOn) {
/* 219 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSS", "longName()");
/*     */     }
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSS", "longName()", "SUBSTORE");
/*     */     }
/* 245 */     return "SUBSTORE";
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
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSS", "shortName()");
/*     */     }
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSS", "shortName()", "SS");
/*     */     }
/* 262 */     return "SS";
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
/*     */   public static int stringToVal(String s) throws BAOException {
/*     */     int iVal;
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.entry("com.ibm.mq.jms.admin.APSS", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 279 */     String str = s.toUpperCase();
/*     */     
/* 281 */     if (str.equals("QUEUE")) {
/* 282 */       iVal = 0;
/*     */     }
/* 284 */     else if (str.equals("BROKER")) {
/* 285 */       iVal = 1;
/*     */     }
/* 287 */     else if (str.equals("MIGRATE")) {
/* 288 */       iVal = 2;
/*     */     } else {
/*     */       
/* 291 */       BAOException traceRet1 = new BAOException(4, "SS", str);
/* 292 */       if (Trace.isOn) {
/* 293 */         Trace.throwing("com.ibm.mq.jms.admin.APSS", "stringToVal(String)", traceRet1);
/*     */       }
/* 295 */       throw traceRet1;
/*     */     } 
/*     */     
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.exit("com.ibm.mq.jms.admin.APSS", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 301 */     return iVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String valToString(int i) throws JMSException {
/*     */     String sVal;
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry("com.ibm.mq.jms.admin.APSS", "valToString(int)", new Object[] {
/* 315 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/*     */     
/* 319 */     if (i == 0) {
/* 320 */       sVal = "QUEUE";
/*     */     }
/* 322 */     else if (i == 1) {
/* 323 */       sVal = "BROKER";
/*     */     }
/* 325 */     else if (i == 2) {
/* 326 */       sVal = "MIGRATE";
/*     */     }
/*     */     else {
/*     */       
/* 330 */       String detail = "broker version unexpected value " + i;
/* 331 */       String key = "JMSADM1016";
/* 332 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 333 */       JMSException iee = new JMSException(msg, key);
/* 334 */       if (Trace.isOn) {
/* 335 */         Trace.throwing("com.ibm.mq.jms.admin.APSS", "valToString(int)", (Throwable)iee);
/*     */       }
/* 337 */       throw iee;
/*     */     } 
/*     */     
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.exit("com.ibm.mq.jms.admin.APSS", "valToString(int)", sVal);
/*     */     }
/* 343 */     return sVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */