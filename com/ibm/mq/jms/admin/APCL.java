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
/*     */ public class APCL
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCL.java";
/*     */   public static final String LONGNAME = "CLEANUP";
/*     */   public static final String SHORTNAME = "CL";
/*     */   public static final String CLEANUP_AS_PROPERTY = "ASPROP";
/*     */   public static final String CLEANUP_NONE = "NONE";
/*     */   public static final String CLEANUP_SAFE = "SAFE";
/*     */   public static final String CLEANUP_STRONG = "STRONG";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APCL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCL.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  94 */       Object value = getProperty("CL", props);
/*     */       
/*  96 */       if (value != null)
/*     */       {
/*  98 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/* 101 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 103 */             ((MQConnectionFactory)obj).setCleanupLevel(iVal);
/*     */           }
/* 105 */           catch (JMSException e) {
/* 106 */             if (Trace.isOn) {
/* 107 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 111 */             BAOException be = new BAOException(4, "CL", Integer.toString(iVal));
/* 112 */             if (Trace.isOn) {
/* 113 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 116 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 121 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 122 */           String key = "JMSADM1016";
/* 123 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 124 */           JMSException iee = new JMSException(msg, key);
/* 125 */           if (Trace.isOn) {
/* 126 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 129 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 134 */     } catch (BAOException e) {
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 143 */       throw e;
/*     */     
/*     */     }
/* 146 */     catch (JMSException e) {
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 155 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCL", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCL", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int i;
/*     */       
/* 189 */       if (obj instanceof MQConnectionFactory) {
/* 190 */         i = ((MQConnectionFactory)obj).getCleanupLevel();
/*     */       }
/*     */       else {
/*     */         
/* 194 */         String detail = "object is an unexpected type";
/* 195 */         String key = "JMSADM1016";
/* 196 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 197 */         JMSException iee = new JMSException(msg, key);
/* 198 */         if (Trace.isOn) {
/* 199 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCL", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 202 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 206 */       props.put("CLEANUP", valToString(i));
/*     */     }
/*     */     finally {
/*     */       
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCL", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCL", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCL", "longName()");
/*     */     }
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCL", "longName()", "CLEANUP");
/*     */     }
/* 237 */     return "CLEANUP";
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
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCL", "shortName()");
/*     */     }
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCL", "shortName()", "CL");
/*     */     }
/* 254 */     return "CL";
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
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.entry("com.ibm.mq.jms.admin.APCL", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 271 */     String str = s.toUpperCase();
/*     */     
/* 273 */     if (str.equals("ASPROP")) {
/* 274 */       iVal = -1;
/*     */     }
/* 276 */     else if (str.equals("NONE")) {
/* 277 */       iVal = 0;
/*     */     }
/* 279 */     else if (str.equals("SAFE")) {
/* 280 */       iVal = 1;
/*     */     }
/* 282 */     else if (str.equals("STRONG")) {
/* 283 */       iVal = 2;
/*     */     } else {
/*     */       
/* 286 */       BAOException traceRet1 = new BAOException(4, "CL", str);
/* 287 */       if (Trace.isOn) {
/* 288 */         Trace.throwing("com.ibm.mq.jms.admin.APCL", "stringToVal(String)", traceRet1);
/*     */       }
/* 290 */       throw traceRet1;
/*     */     } 
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit("com.ibm.mq.jms.admin.APCL", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 296 */     return iVal;
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
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry("com.ibm.mq.jms.admin.APCL", "valToString(int)", new Object[] {
/* 310 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/*     */     
/* 314 */     if (i == -1) {
/* 315 */       sVal = "ASPROP";
/*     */     }
/* 317 */     else if (i == 0) {
/* 318 */       sVal = "NONE";
/*     */     }
/* 320 */     else if (i == 1) {
/* 321 */       sVal = "SAFE";
/*     */     }
/* 323 */     else if (i == 2) {
/* 324 */       sVal = "STRONG";
/*     */     }
/*     */     else {
/*     */       
/* 328 */       String detail = "broker version unexpected value " + i;
/* 329 */       String key = "JMSADM1016";
/* 330 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 331 */       JMSException iee = new JMSException(msg, key);
/* 332 */       if (Trace.isOn) {
/* 333 */         Trace.throwing("com.ibm.mq.jms.admin.APCL", "valToString(int)", (Throwable)iee);
/*     */       }
/* 335 */       throw iee;
/*     */     } 
/*     */     
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.exit("com.ibm.mq.jms.admin.APCL", "valToString(int)", sVal);
/*     */     }
/* 341 */     return sVal;
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
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.entry("com.ibm.mq.jms.admin.APCL", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */     
/*     */     try {
/*     */       int iVal;
/* 362 */       if (value instanceof Integer) {
/* 363 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 366 */       else if (value instanceof String) {
/* 367 */         String str = (String)value;
/* 368 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 373 */         String detail = "value supplied as an unexpected object type";
/* 374 */         String key = "JMSADM1016";
/* 375 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 376 */         JMSException iee = new JMSException(msg, key);
/* 377 */         if (Trace.isOn) {
/* 378 */           Trace.throwing("com.ibm.mq.jms.admin.APCL", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 380 */         throw iee;
/*     */       } 
/*     */       
/* 383 */       if (Trace.isOn) {
/* 384 */         Trace.exit("com.ibm.mq.jms.admin.APCL", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 386 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 390 */       if (Trace.isOn)
/* 391 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APCL", "objToInt(Object)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */