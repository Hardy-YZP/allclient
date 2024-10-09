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
/*     */ public class APPRDUR
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPRDUR.java";
/*     */   public static final String LONGNAME = "PROCESSDURATION";
/*     */   public static final String SHORTNAME = "PROCDUR";
/*     */   public static final String PROCESSING_SHORT = "SHORT";
/*     */   public static final String PROCESSING_UNKNOWN = "UNKNOWN";
/*     */   public static final String PROCESSING_DEFAULT = "DEFAULT";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.admin.APPRDUR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPRDUR.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  81 */       Object value = getProperty("PROCDUR", props);
/*     */ 
/*     */ 
/*     */       
/*  85 */       if (value != null)
/*     */       {
/*  87 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/*  90 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/*  92 */             ((MQConnectionFactory)obj).setProcessDuration(iVal);
/*     */           }
/*  94 */           catch (JMSException e) {
/*  95 */             if (Trace.isOn) {
/*  96 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 100 */             BAOException be = new BAOException(4, "PROCDUR", Integer.toString(iVal));
/* 101 */             if (Trace.isOn) {
/* 102 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 105 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 110 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 111 */           String key = "JMSADM1016";
/* 112 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 113 */           JMSException iee = new JMSException(msg, key);
/* 114 */           if (Trace.isOn) {
/* 115 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 118 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 123 */     } catch (BAOException e) {
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 132 */       throw e;
/*     */     
/*     */     }
/* 135 */     catch (JMSException e) {
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 144 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRDUR", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRDUR", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int i;
/*     */       
/* 174 */       if (obj instanceof MQConnectionFactory) {
/* 175 */         i = ((MQConnectionFactory)obj).getProcessDuration();
/*     */       }
/*     */       else {
/*     */         
/* 179 */         String detail = "object is an unexpected type";
/* 180 */         String key = "JMSADM1016";
/* 181 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */         JMSException iee = new JMSException(msg, key);
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APPRDUR", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 187 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 192 */       String value = valToString(i);
/*     */ 
/*     */       
/* 195 */       props.put("PROCESSDURATION", value);
/*     */     }
/*     */     finally {
/*     */       
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPRDUR", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRDUR", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRDUR", "longName()");
/*     */     }
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRDUR", "longName()", "PROCESSDURATION");
/*     */     }
/* 223 */     return "PROCESSDURATION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRDUR", "shortName()");
/*     */     }
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRDUR", "shortName()", "PROCDUR");
/*     */     }
/* 237 */     return "PROCDUR";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.entry("com.ibm.mq.jms.admin.APPRDUR", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */     
/*     */     try {
/*     */       int iVal;
/* 253 */       if (value instanceof Integer) {
/* 254 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 257 */       else if (value instanceof String) {
/* 258 */         String str = (String)value;
/* 259 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 264 */         String detail = "value supplied as an unexpected object type";
/* 265 */         String key = "JMSADM1016";
/* 266 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 267 */         JMSException iee = new JMSException(msg, key);
/* 268 */         if (Trace.isOn) {
/* 269 */           Trace.throwing("com.ibm.mq.jms.admin.APPRDUR", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 271 */         throw iee;
/*     */       } 
/*     */       
/* 274 */       if (Trace.isOn) {
/* 275 */         Trace.exit("com.ibm.mq.jms.admin.APPRDUR", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 277 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 281 */       if (Trace.isOn) {
/* 282 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APPRDUR", "objToInt(Object)");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/*     */     int iVal;
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.entry("com.ibm.mq.jms.admin.APPRDUR", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 297 */     String str = s.toUpperCase();
/*     */     
/* 299 */     if (str.equals("SHORT")) {
/* 300 */       iVal = 1;
/*     */     }
/* 302 */     else if (str.equals("UNKNOWN")) {
/* 303 */       iVal = 0;
/*     */     }
/* 305 */     else if (str.equals("DEFAULT")) {
/* 306 */       iVal = 0;
/*     */     } else {
/*     */       
/* 309 */       BAOException traceRet1 = new BAOException(4, "PROCDUR", str);
/* 310 */       if (Trace.isOn) {
/* 311 */         Trace.throwing("com.ibm.mq.jms.admin.APPRDUR", "stringToVal(String)", traceRet1);
/*     */       }
/* 313 */       throw traceRet1;
/*     */     } 
/*     */     
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit("com.ibm.mq.jms.admin.APPRDUR", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 319 */     return iVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String valToString(int i) throws JMSException {
/*     */     String sVal;
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.entry("com.ibm.mq.jms.admin.APPRDUR", "valToString(int)", new Object[] {
/* 330 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/*     */     
/* 334 */     if (i == 0) {
/* 335 */       sVal = "UNKNOWN";
/*     */     }
/* 337 */     else if (i == 1) {
/* 338 */       sVal = "SHORT";
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 346 */       String detail = "broker version unexpected value " + i;
/* 347 */       String key = "JMSADM1016";
/* 348 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 349 */       JMSException iee = new JMSException(msg, key);
/* 350 */       if (Trace.isOn) {
/* 351 */         Trace.throwing("com.ibm.mq.jms.admin.APPRDUR", "valToString(int)", (Throwable)iee);
/*     */       }
/* 353 */       throw iee;
/*     */     } 
/*     */     
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.exit("com.ibm.mq.jms.admin.APPRDUR", "valToString(int)", sVal);
/*     */     }
/* 359 */     return sVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPRDUR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */