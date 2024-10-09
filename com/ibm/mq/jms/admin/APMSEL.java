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
/*     */ public class APMSEL
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMSEL.java";
/*     */   public static final String LONGNAME = "MSGSELECTION";
/*     */   public static final String SHORTNAME = "MSEL";
/*     */   public static final String MSEL_CLIENT = "CLIENT";
/*     */   public static final String MSEL_BROKER = "BROKER";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APMSEL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMSEL.java");
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
/*     */   public static String valToString(int iVal) throws JMSException {
/*     */     String sVal;
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry("com.ibm.mq.jms.admin.APMSEL", "valToString(int)", new Object[] {
/*  88 */             Integer.valueOf(iVal)
/*     */           });
/*     */     }
/*     */     
/*  92 */     if (iVal == 0) {
/*  93 */       sVal = "CLIENT";
/*     */     }
/*  95 */     else if (iVal == 1) {
/*  96 */       sVal = "BROKER";
/*     */     }
/*     */     else {
/*     */       
/* 100 */       String detail = "message selection unexpected value " + iVal;
/* 101 */       String key = "JMSADM1016";
/* 102 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 103 */       JMSException iee = new JMSException(msg, key);
/* 104 */       if (Trace.isOn) {
/* 105 */         Trace.throwing("com.ibm.mq.jms.admin.APMSEL", "valToString(int)", (Throwable)iee);
/*     */       }
/* 107 */       throw iee;
/*     */     } 
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit("com.ibm.mq.jms.admin.APMSEL", "valToString(int)", sVal);
/*     */     }
/* 113 */     return sVal;
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
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry("com.ibm.mq.jms.admin.APMSEL", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 129 */     String str = s.toUpperCase();
/*     */     
/* 131 */     if (str.equals("CLIENT")) {
/* 132 */       iVal = 0;
/*     */     }
/* 134 */     else if (str.equals("BROKER")) {
/* 135 */       iVal = 1;
/*     */     } else {
/*     */       
/* 138 */       BAOException traceRet1 = new BAOException(4, "MSEL", str);
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.throwing("com.ibm.mq.jms.admin.APMSEL", "stringToVal(String)", traceRet1);
/*     */       }
/* 142 */       throw traceRet1;
/*     */     } 
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit("com.ibm.mq.jms.admin.APMSEL", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 148 */     return iVal;
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
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry("com.ibm.mq.jms.admin.APMSEL", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 168 */       if (value instanceof Integer) {
/* 169 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 172 */       else if (value instanceof String) {
/* 173 */         String str = (String)value;
/* 174 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 179 */         String detail = "value supplied as an unexpected object type";
/* 180 */         String key = "JMSADM1016";
/* 181 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */         JMSException iee = new JMSException(msg, key);
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.throwing("com.ibm.mq.jms.admin.APMSEL", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 186 */         throw iee;
/*     */       } 
/*     */       
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.exit("com.ibm.mq.jms.admin.APMSEL", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 192 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APMSEL", "objToInt(Object)");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 218 */       Object value = getProperty("MSEL", props);
/*     */ 
/*     */       
/* 221 */       if (value != null)
/*     */       {
/* 223 */         int iVal = objToInt(value);
/*     */ 
/*     */         
/* 226 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 228 */             ((MQConnectionFactory)obj).setMessageSelection(iVal);
/*     */           }
/* 230 */           catch (JMSException e) {
/* 231 */             if (Trace.isOn) {
/* 232 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 236 */             BAOException be = new BAOException(4, "MSEL", Integer.toString(iVal));
/* 237 */             if (Trace.isOn) {
/* 238 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 241 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 246 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 247 */           String key = "JMSADM1016";
/* 248 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 249 */           JMSException iee = new JMSException(msg, key);
/* 250 */           if (Trace.isOn) {
/* 251 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 254 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 259 */     } catch (BAOException e) {
/* 260 */       if (Trace.isOn) {
/* 261 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 264 */       if (Trace.isOn) {
/* 265 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 268 */       throw e;
/*     */     
/*     */     }
/* 271 */     catch (JMSException e) {
/* 272 */       if (Trace.isOn) {
/* 273 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 280 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 284 */       if (Trace.isOn) {
/* 285 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMSEL", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMSEL", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 314 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 315 */         int i = ((MQConnectionFactory)obj).getMessageSelection();
/* 316 */         sVal = valToString(i);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 321 */         String detail = "object is an unexpected type";
/* 322 */         String key = "JMSADM1016";
/* 323 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 324 */         JMSException iee = new JMSException(msg, key);
/* 325 */         if (Trace.isOn) {
/* 326 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APMSEL", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 329 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 333 */       props.put("MSGSELECTION", sVal);
/*     */     }
/*     */     finally {
/*     */       
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMSEL", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMSEL", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMSEL", "longName()");
/*     */     }
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMSEL", "longName()", "MSGSELECTION");
/*     */     }
/* 364 */     return "MSGSELECTION";
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
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMSEL", "shortName()");
/*     */     }
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMSEL", "shortName()", "MSEL");
/*     */     }
/* 381 */     return "MSEL";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMSEL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */