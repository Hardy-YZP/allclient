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
/*     */ public class APTRAN
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTRAN.java";
/*     */   public static final String LONGNAME = "TRANSPORT";
/*     */   public static final String SHORTNAME = "TRAN";
/*     */   public static final String TP_BINDINGS = "BIND";
/*     */   public static final String TP_CLIENT = "CLIENT";
/*     */   public static final String TP_BINDINGS_CLIENT = "BINDINGS_THEN_CLIENT";
/*     */   public static final String TP_DIRECT = "DIRECT";
/*     */   public static final String TP_DIRECTHTTP = "DIRECTHTTP";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APTRAN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTRAN.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  98 */       Object value = getProperty("TRAN", props);
/*     */ 
/*     */       
/* 101 */       if (value != null)
/*     */       {
/* 103 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/* 106 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 108 */             ((MQConnectionFactory)obj).setTransportType(iVal);
/*     */           }
/* 110 */           catch (JMSException e) {
/* 111 */             if (Trace.isOn) {
/* 112 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 116 */             BAOException be = new BAOException(4, "TRAN", Integer.toString(iVal));
/* 117 */             if (Trace.isOn) {
/* 118 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 121 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 126 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 127 */           String key = "JMSADM1016";
/* 128 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 129 */           JMSException iee = new JMSException(msg, key);
/* 130 */           if (Trace.isOn) {
/* 131 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 134 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 139 */     } catch (BAOException e) {
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 148 */       throw e;
/*     */     
/*     */     }
/* 151 */     catch (JMSException e) {
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 160 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTRAN", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTRAN", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 194 */       if (obj instanceof MQConnectionFactory) {
/* 195 */         iVal = ((MQConnectionFactory)obj).getTransportType();
/*     */       }
/*     */       else {
/*     */         
/* 199 */         String detail = "object is an unexpected type";
/* 200 */         String key = "JMSADM1016";
/* 201 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 202 */         JMSException iee = new JMSException(msg, key);
/* 203 */         if (Trace.isOn) {
/* 204 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTRAN", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 207 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 212 */       String value = valToString(iVal);
/*     */ 
/*     */       
/* 215 */       props.put("TRANSPORT", value);
/*     */     }
/*     */     finally {
/*     */       
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTRAN", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTRAN", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTRAN", "longName()");
/*     */     }
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTRAN", "longName()", "TRANSPORT");
/*     */     }
/* 246 */     return "TRANSPORT";
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
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTRAN", "shortName()");
/*     */     }
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTRAN", "shortName()", "TRAN");
/*     */     }
/* 263 */     return "TRAN";
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
/*     */   public static String valToString(int iVal) throws JMSException {
/*     */     String value;
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.entry("com.ibm.mq.jms.admin.APTRAN", "valToString(int)", new Object[] {
/* 278 */             Integer.valueOf(iVal)
/*     */           });
/*     */     }
/* 281 */     if (iVal == 0) {
/* 282 */       value = "BIND";
/*     */     }
/* 284 */     else if (iVal == 1) {
/* 285 */       value = "CLIENT";
/*     */     }
/* 287 */     else if (iVal == 8) {
/* 288 */       value = "BINDINGS_THEN_CLIENT";
/*     */     }
/* 290 */     else if (iVal == 2) {
/* 291 */       value = "DIRECT";
/*     */     }
/* 293 */     else if (iVal == 4) {
/* 294 */       value = "DIRECTHTTP";
/*     */     }
/*     */     else {
/*     */       
/* 298 */       String detail = "unexpected value: " + iVal;
/* 299 */       String key = "JMSADM1016";
/* 300 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 301 */       JMSException iee = new JMSException(msg, key);
/* 302 */       if (Trace.isOn) {
/* 303 */         Trace.throwing("com.ibm.mq.jms.admin.APTRAN", "valToString(int)", (Throwable)iee);
/*     */       }
/* 305 */       throw iee;
/*     */     } 
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit("com.ibm.mq.jms.admin.APTRAN", "valToString(int)", value);
/*     */     }
/* 311 */     return value;
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
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.entry("com.ibm.mq.jms.admin.APTRAN", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 327 */     String str = s.toUpperCase();
/* 328 */     if (str.equals("BIND")) {
/* 329 */       iVal = 0;
/*     */     }
/* 331 */     else if (str.equals("CLIENT")) {
/* 332 */       iVal = 1;
/*     */     }
/* 334 */     else if (str.equals("BINDINGS_THEN_CLIENT")) {
/* 335 */       iVal = 8;
/*     */     }
/* 337 */     else if (str.equals("DIRECT")) {
/* 338 */       iVal = 2;
/*     */     }
/* 340 */     else if (str.equals("DIRECTHTTP")) {
/* 341 */       iVal = 4;
/*     */     } else {
/*     */       
/* 344 */       BAOException traceRet1 = new BAOException(4, "TRAN", str);
/* 345 */       if (Trace.isOn) {
/* 346 */         Trace.throwing("com.ibm.mq.jms.admin.APTRAN", "stringToVal(String)", traceRet1);
/*     */       }
/* 348 */       throw traceRet1;
/*     */     } 
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.exit("com.ibm.mq.jms.admin.APTRAN", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 353 */     return iVal;
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
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.entry("com.ibm.mq.jms.admin.APTRAN", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 373 */       if (value instanceof Integer) {
/* 374 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 377 */       else if (value instanceof String) {
/* 378 */         String str = (String)value;
/* 379 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 384 */         String detail = "value supplied as an unexpected object type";
/* 385 */         String key = "JMSADM1016";
/* 386 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 387 */         JMSException iee = new JMSException(msg, key);
/* 388 */         if (Trace.isOn) {
/* 389 */           Trace.throwing("com.ibm.mq.jms.admin.APTRAN", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 391 */         throw iee;
/*     */       } 
/*     */       
/* 394 */       if (Trace.isOn) {
/* 395 */         Trace.exit("com.ibm.mq.jms.admin.APTRAN", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 397 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 401 */       if (Trace.isOn)
/* 402 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APTRAN", "objToInt(Object)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTRAN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */