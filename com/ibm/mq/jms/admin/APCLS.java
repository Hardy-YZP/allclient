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
/*     */ public class APCLS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCLS.java";
/*     */   public static final String LONGNAME = "CLONESUPP";
/*     */   public static final String SHORTNAME = "CLS";
/*     */   public static final String CLONE_ENABLED = "ENABLED";
/*     */   public static final String CLONE_DISABLED = "DISABLED";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APCLS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCLS.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  91 */       Object value = getProperty("CLS", props);
/*     */       
/*  93 */       if (value != null)
/*     */       {
/*  95 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/*  98 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 100 */             ((MQConnectionFactory)obj).setCloneSupport(iVal);
/*     */           }
/* 102 */           catch (JMSException e) {
/* 103 */             if (Trace.isOn) {
/* 104 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 108 */             BAOException be = new BAOException(4, "CLS", Integer.toString(iVal));
/* 109 */             if (Trace.isOn) {
/* 110 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 113 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 118 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 119 */           String key = "JMSADM1016";
/* 120 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 121 */           JMSException iee = new JMSException(msg, key);
/* 122 */           if (Trace.isOn) {
/* 123 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 126 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 131 */     } catch (BAOException e) {
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 140 */       throw e;
/*     */     
/*     */     }
/* 143 */     catch (JMSException e) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 152 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int i;
/*     */       
/* 186 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 187 */         i = ((MQConnectionFactory)obj).getCloneSupport();
/*     */       }
/*     */       else {
/*     */         
/* 191 */         String detail = "object is an unexpected type";
/* 192 */         String key = "JMSADM1016";
/* 193 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 194 */         JMSException iee = new JMSException(msg, key);
/* 195 */         if (Trace.isOn) {
/* 196 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCLS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 199 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 203 */       props.put("CLONESUPP", valToString(i));
/*     */     }
/*     */     finally {
/*     */       
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCLS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLS", "longName()");
/*     */     }
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLS", "longName()", "CLONESUPP");
/*     */     }
/* 234 */     return "CLONESUPP";
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
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLS", "shortName()");
/*     */     }
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLS", "shortName()", "CLS");
/*     */     }
/* 251 */     return "CLS";
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
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry("com.ibm.mq.jms.admin.APCLS", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 268 */     String str = s.toUpperCase();
/*     */     
/* 270 */     if (str.equals("ENABLED")) {
/* 271 */       iVal = 1;
/*     */     }
/* 273 */     else if (str.equals("DISABLED")) {
/* 274 */       iVal = 0;
/*     */     } else {
/*     */       
/* 277 */       BAOException traceRet1 = new BAOException(4, "CLS", str);
/* 278 */       if (Trace.isOn) {
/* 279 */         Trace.throwing("com.ibm.mq.jms.admin.APCLS", "stringToVal(String)", traceRet1);
/*     */       }
/* 281 */       throw traceRet1;
/*     */     } 
/*     */     
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit("com.ibm.mq.jms.admin.APCLS", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 287 */     return iVal;
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
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.entry("com.ibm.mq.jms.admin.APCLS", "valToString(int)", new Object[] {
/* 301 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/*     */     
/* 305 */     if (i == 1) {
/* 306 */       sVal = "ENABLED";
/*     */     }
/* 308 */     else if (i == 0) {
/* 309 */       sVal = "DISABLED";
/*     */     }
/*     */     else {
/*     */       
/* 313 */       String detail = "broker version unexpected value " + i;
/* 314 */       String key = "JMSADM1016";
/* 315 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 316 */       JMSException iee = new JMSException(msg, key);
/* 317 */       if (Trace.isOn) {
/* 318 */         Trace.throwing("com.ibm.mq.jms.admin.APCLS", "valToString(int)", (Throwable)iee);
/*     */       }
/* 320 */       throw iee;
/*     */     } 
/*     */     
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.exit("com.ibm.mq.jms.admin.APCLS", "valToString(int)", sVal);
/*     */     }
/* 326 */     return sVal;
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
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.entry("com.ibm.mq.jms.admin.APCLS", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */     
/*     */     try {
/*     */       int iVal;
/* 347 */       if (value instanceof Integer) {
/* 348 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 351 */       else if (value instanceof String) {
/* 352 */         String str = (String)value;
/* 353 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 358 */         String detail = "value supplied as an unexpected object type";
/* 359 */         String key = "JMSADM1016";
/* 360 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 361 */         JMSException iee = new JMSException(msg, key);
/* 362 */         if (Trace.isOn) {
/* 363 */           Trace.throwing("com.ibm.mq.jms.admin.APCLS", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 365 */         throw iee;
/*     */       } 
/*     */       
/* 368 */       if (Trace.isOn) {
/* 369 */         Trace.exit("com.ibm.mq.jms.admin.APCLS", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 371 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 375 */       if (Trace.isOn)
/* 376 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APCLS", "objToInt(Object)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCLS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */