/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQTopic;
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
/*     */ public class APBVER
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APBVER.java";
/*     */   public static final String LONGNAME = "BROKERVER";
/*     */   public static final String SHORTNAME = "BVER";
/*     */   public static final String BROKER_V1 = "V1";
/*     */   public static final String BROKER_V2 = "V2";
/*     */   public static final String BROKER_UNSPECIFIED = "UNSPECIFIED";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jms.admin.APBVER", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APBVER.java");
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
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  94 */       Object value = getProperty("BVER", props);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       if (value != null) {
/* 104 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/* 107 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 109 */             ((MQConnectionFactory)obj).setBrokerVersion(iVal);
/*     */           }
/* 111 */           catch (JMSException e) {
/* 112 */             if (Trace.isOn) {
/* 113 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 117 */             BAOException be = new BAOException(4, "BVER", Integer.toString(iVal));
/* 118 */             if (Trace.isOn) {
/* 119 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 122 */             throw be;
/*     */           }
/*     */         
/*     */         }
/* 126 */         else if (obj instanceof MQTopic) {
/*     */           try {
/* 128 */             ((MQTopic)obj).setBrokerVersion(iVal);
/*     */           }
/* 130 */           catch (JMSException e) {
/* 131 */             if (Trace.isOn) {
/* 132 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */             
/* 136 */             BAOException be = new BAOException(4, "BVER", Integer.toString(iVal));
/* 137 */             if (Trace.isOn) {
/* 138 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 141 */             throw be;
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 147 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 148 */           String key = "JMSADM1016";
/* 149 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 150 */           JMSException iee = new JMSException(msg, key);
/* 151 */           if (Trace.isOn) {
/* 152 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 155 */           throw iee;
/*     */         }
/*     */       
/*     */       } 
/* 159 */     } catch (BAOException e) {
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 168 */       throw e;
/*     */     
/*     */     }
/* 171 */     catch (JMSException e) {
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 180 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBVER", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public static int stringToVal(String value) throws BAOException {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry("com.ibm.mq.jms.admin.APBVER", "stringToVal(String)", new Object[] { value });
/*     */     }
/* 205 */     int rVal = 0;
/* 206 */     if (value.equals("V1")) {
/* 207 */       rVal = 0;
/*     */     }
/* 209 */     else if (value.equals("V2")) {
/* 210 */       rVal = 1;
/*     */     }
/* 212 */     else if (value.equals("UNSPECIFIED")) {
/* 213 */       rVal = -1;
/*     */     } else {
/*     */       
/* 216 */       BAOException traceRet1 = new BAOException(4, "BVER", value);
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.throwing("com.ibm.mq.jms.admin.APBVER", "stringToVal(String)", traceRet1);
/*     */       }
/* 220 */       throw traceRet1;
/*     */     } 
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit("com.ibm.mq.jms.admin.APBVER", "stringToVal(String)", Integer.valueOf(rVal));
/*     */     }
/* 225 */     return rVal;
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
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.entry("com.ibm.mq.jms.admin.APBVER", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 246 */       if (value instanceof Integer) {
/* 247 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 250 */       else if (value instanceof String) {
/* 251 */         String str = (String)value;
/* 252 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 257 */         String detail = "value supplied as an unexpected object type";
/* 258 */         String key = "JMSADM1016";
/* 259 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 260 */         JMSException iee = new JMSException(msg, key);
/* 261 */         if (Trace.isOn) {
/* 262 */           Trace.throwing("com.ibm.mq.jms.admin.APBVER", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 264 */         throw iee;
/*     */       } 
/*     */       
/* 267 */       if (Trace.isOn) {
/* 268 */         Trace.exit("com.ibm.mq.jms.admin.APBVER", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 270 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 274 */       if (Trace.isOn) {
/* 275 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APBVER", "objToInt(Object)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBVER", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */ 
/*     */       
/* 299 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 300 */         int i = ((MQConnectionFactory)obj).getBrokerVersion();
/* 301 */         sVal = valToString(i);
/*     */       }
/* 303 */       else if (obj instanceof MQTopic) {
/* 304 */         int i = ((MQTopic)obj).getBrokerVersion();
/* 305 */         sVal = valToString(i);
/*     */       }
/*     */       else {
/*     */         
/* 309 */         String detail = "object is an unexpected type";
/* 310 */         String key = "JMSADM1016";
/* 311 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 312 */         JMSException iee = new JMSException(msg, key);
/* 313 */         if (Trace.isOn) {
/* 314 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APBVER", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 317 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 321 */       props.put("BROKERVER", sVal);
/*     */     }
/*     */     finally {
/*     */       
/* 325 */       if (Trace.isOn) {
/* 326 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APBVER", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBVER", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBVER", "longName()");
/*     */     }
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBVER", "longName()", "BROKERVER");
/*     */     }
/* 352 */     return "BROKERVER";
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
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBVER", "shortName()");
/*     */     }
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBVER", "shortName()", "BVER");
/*     */     }
/* 369 */     return "BVER";
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
/*     */   public static String valToString(int i) throws JMSException {
/*     */     String sVal;
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.entry("com.ibm.mq.jms.admin.APBVER", "valToString(int)", new Object[] {
/* 384 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/*     */     
/* 388 */     if (i == 0) {
/* 389 */       sVal = "V1";
/*     */     }
/* 391 */     else if (i == 1) {
/* 392 */       sVal = "V2";
/*     */     }
/* 394 */     else if (i == -1) {
/* 395 */       sVal = "UNSPECIFIED";
/*     */     }
/*     */     else {
/*     */       
/* 399 */       String detail = "broker version unexpected value " + i;
/* 400 */       String key = "JMSADM1016";
/* 401 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 402 */       JMSException iee = new JMSException(msg, key);
/* 403 */       if (Trace.isOn) {
/* 404 */         Trace.throwing("com.ibm.mq.jms.admin.APBVER", "valToString(int)", (Throwable)iee);
/*     */       }
/* 406 */       throw iee;
/*     */     } 
/*     */     
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.exit("com.ibm.mq.jms.admin.APBVER", "valToString(int)", sVal);
/*     */     }
/* 412 */     return sVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APBVER.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */