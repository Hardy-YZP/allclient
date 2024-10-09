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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APRCISO
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRCISO.java";
/*     */   public static final String LONGNAME = "RECEIVEISOLATION";
/*     */   public static final String SHORTNAME = "RCVISOL";
/*     */   public static final String RCVISOL_COMMITTED = "COMMITTED";
/*     */   public static final String RCVISOL_UNCOMMITTED = "UNCOMMITTED";
/*     */   public static final String RCVISOL_DEFAULT = "DEFAULT";
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.jms.admin.APRCISO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRCISO.java");
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
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 101 */       Object value = getProperty("RCVISOL", props);
/*     */ 
/*     */ 
/*     */       
/* 105 */       if (value != null)
/*     */       {
/* 107 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/* 110 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 112 */             ((MQConnectionFactory)obj).setReceiveIsolation(iVal);
/*     */           }
/* 114 */           catch (JMSException e) {
/* 115 */             if (Trace.isOn) {
/* 116 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 120 */             BAOException be = new BAOException(4, "RCVISOL", Integer.toString(iVal));
/* 121 */             if (Trace.isOn) {
/* 122 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 125 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 130 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 131 */           String key = "JMSADM1016";
/* 132 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 133 */           JMSException iee = new JMSException(msg, key);
/* 134 */           if (Trace.isOn) {
/* 135 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 138 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 143 */     } catch (BAOException e) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 152 */       throw e;
/*     */     
/*     */     }
/* 155 */     catch (JMSException e) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 164 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCISO", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCISO", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int i;
/*     */       
/* 201 */       if (obj instanceof MQConnectionFactory) {
/* 202 */         i = ((MQConnectionFactory)obj).getReceiveIsolation();
/*     */       }
/*     */       else {
/*     */         
/* 206 */         String detail = "object is an unexpected type";
/* 207 */         String key = "JMSADM1016";
/* 208 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 209 */         JMSException iee = new JMSException(msg, key);
/* 210 */         if (Trace.isOn) {
/* 211 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APRCISO", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 214 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 219 */       String value = valToString(i);
/*     */ 
/*     */       
/* 222 */       props.put("RECEIVEISOLATION", value);
/*     */     }
/*     */     finally {
/*     */       
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRCISO", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCISO", "setPropertyFromObject(Map<String , Object>,Object)");
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
/*     */   public String longName() {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCISO", "longName()");
/*     */     }
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCISO", "longName()", "RECEIVEISOLATION");
/*     */     }
/* 252 */     return "RECEIVEISOLATION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCISO", "shortName()");
/*     */     }
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCISO", "shortName()", "RCVISOL");
/*     */     }
/* 268 */     return "RCVISOL";
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
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.entry("com.ibm.mq.jms.admin.APRCISO", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */     
/*     */     try {
/*     */       int iVal;
/* 286 */       if (value instanceof Integer) {
/* 287 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 290 */       else if (value instanceof String) {
/* 291 */         String str = (String)value;
/* 292 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 297 */         String detail = "value supplied as an unexpected object type";
/* 298 */         String key = "JMSADM1016";
/* 299 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 300 */         JMSException iee = new JMSException(msg, key);
/* 301 */         if (Trace.isOn) {
/* 302 */           Trace.throwing("com.ibm.mq.jms.admin.APRCISO", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 304 */         throw iee;
/*     */       } 
/*     */       
/* 307 */       if (Trace.isOn) {
/* 308 */         Trace.exit("com.ibm.mq.jms.admin.APRCISO", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 310 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APRCISO", "objToInt(Object)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/*     */     int iVal;
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.entry("com.ibm.mq.jms.admin.APRCISO", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 338 */     String str = s.toUpperCase();
/*     */     
/* 340 */     if (str.equals("COMMITTED")) {
/* 341 */       iVal = 0;
/*     */     }
/* 343 */     else if (str.equals("UNCOMMITTED")) {
/* 344 */       iVal = 1;
/*     */     }
/* 346 */     else if (str.equals("DEFAULT")) {
/* 347 */       iVal = 0;
/*     */     } else {
/*     */       
/* 350 */       BAOException traceRet1 = new BAOException(4, "RCVISOL", str);
/* 351 */       if (Trace.isOn) {
/* 352 */         Trace.throwing("com.ibm.mq.jms.admin.APRCISO", "stringToVal(String)", traceRet1);
/*     */       }
/* 354 */       throw traceRet1;
/*     */     } 
/*     */     
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.exit("com.ibm.mq.jms.admin.APRCISO", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 360 */     return iVal;
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
/*     */   public static String valToString(int i) throws JMSException {
/*     */     String sVal;
/* 377 */     if (Trace.isOn) {
/* 378 */       Trace.entry("com.ibm.mq.jms.admin.APRCISO", "valToString(int)", new Object[] {
/* 379 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/*     */     
/* 383 */     if (i == 0) {
/* 384 */       sVal = "COMMITTED";
/*     */     }
/* 386 */     else if (i == 1) {
/* 387 */       sVal = "UNCOMMITTED";
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 395 */       String detail = "broker version unexpected value " + i;
/* 396 */       String key = "JMSADM1016";
/* 397 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 398 */       JMSException iee = new JMSException(msg, key);
/* 399 */       if (Trace.isOn) {
/* 400 */         Trace.throwing("com.ibm.mq.jms.admin.APRCISO", "valToString(int)", (Throwable)iee);
/*     */       }
/* 402 */       throw iee;
/*     */     } 
/*     */     
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.exit("com.ibm.mq.jms.admin.APRCISO", "valToString(int)", sVal);
/*     */     }
/* 408 */     return sVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APRCISO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */