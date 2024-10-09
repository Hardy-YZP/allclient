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
/*     */ public class APPER
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPER.java";
/*     */   public static final String LONGNAME = "PERSISTENCE";
/*     */   public static final String SHORTNAME = "PER";
/*     */   public static final String PER_APP = "APP";
/*     */   public static final String PER_QDEF = "QDEF";
/*     */   public static final String PER_PERS = "PERS";
/*     */   public static final String PER_NON = "NON";
/*     */   public static final String PER_NPHIGH = "HIGH";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APPER", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPER.java");
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
/*     */   public static String valToString(int iVal) throws JMSException {
/*     */     String value;
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry("com.ibm.mq.jms.admin.APPER", "valToString(int)", new Object[] {
/*  91 */             Integer.valueOf(iVal)
/*     */           });
/*     */     }
/*     */     
/*  95 */     if (iVal == -2) {
/*  96 */       value = "APP";
/*     */     }
/*  98 */     else if (iVal == -1) {
/*  99 */       value = "QDEF";
/*     */     }
/* 101 */     else if (iVal == 2) {
/* 102 */       value = "PERS";
/*     */     }
/* 104 */     else if (iVal == 1) {
/* 105 */       value = "NON";
/*     */     }
/* 107 */     else if (iVal == 3) {
/* 108 */       value = "HIGH";
/*     */     }
/*     */     else {
/*     */       
/* 112 */       String detail = "object is an unexpected type";
/* 113 */       String key = "JMSADM1016";
/* 114 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 115 */       JMSException iee = new JMSException(msg, key);
/* 116 */       if (Trace.isOn) {
/* 117 */         Trace.throwing("com.ibm.mq.jms.admin.APPER", "valToString(int)", (Throwable)iee);
/*     */       }
/* 119 */       throw iee;
/*     */     } 
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit("com.ibm.mq.jms.admin.APPER", "valToString(int)", value);
/*     */     }
/* 125 */     return value;
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
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry("com.ibm.mq.jms.admin.APPER", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 141 */     String str = s.toUpperCase();
/*     */     
/* 143 */     if (str.equals("APP")) {
/* 144 */       iVal = -2;
/*     */     }
/* 146 */     else if (str.equals("QDEF")) {
/* 147 */       iVal = -1;
/*     */     }
/* 149 */     else if (str.equals("PERS")) {
/* 150 */       iVal = 2;
/*     */     }
/* 152 */     else if (str.equals("NON")) {
/* 153 */       iVal = 1;
/*     */     }
/* 155 */     else if (str.equals("HIGH")) {
/* 156 */       iVal = 3;
/*     */     } else {
/*     */       
/*     */       try {
/* 160 */         iVal = Integer.parseInt(str);
/*     */       }
/* 162 */       catch (NumberFormatException e) {
/* 163 */         if (Trace.isOn) {
/* 164 */           Trace.catchBlock("com.ibm.mq.jms.admin.APPER", "stringToVal(String)", e);
/*     */         }
/* 166 */         BAOException traceRet1 = new BAOException(4, "PER", str);
/* 167 */         if (Trace.isOn) {
/* 168 */           Trace.throwing("com.ibm.mq.jms.admin.APPER", "stringToVal(String)", traceRet1);
/*     */         }
/* 170 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit("com.ibm.mq.jms.admin.APPER", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 177 */     return iVal;
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
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 198 */       Object value = getProperty("PER", props);
/*     */ 
/*     */       
/* 201 */       if (value != null) {
/*     */         int iVal;
/* 203 */         if (value instanceof Integer) {
/* 204 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 207 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 211 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 213 */             ((MQDestination)obj).setPersistence(iVal);
/*     */           }
/* 215 */           catch (JMSException e) {
/* 216 */             if (Trace.isOn) {
/* 217 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 221 */             BAOException be = new BAOException(4, "PER", Integer.toString(iVal));
/* 222 */             if (Trace.isOn) {
/* 223 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 226 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 231 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 232 */           String key = "JMSADM1016";
/* 233 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 234 */           JMSException iee = new JMSException(msg, key);
/* 235 */           if (Trace.isOn) {
/* 236 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 239 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 244 */     } catch (BAOException e) {
/* 245 */       if (Trace.isOn) {
/* 246 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 249 */       if (Trace.isOn) {
/* 250 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 253 */       throw e;
/*     */     
/*     */     }
/* 256 */     catch (JMSException e) {
/* 257 */       if (Trace.isOn) {
/* 258 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 261 */       if (Trace.isOn) {
/* 262 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 265 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 269 */       if (Trace.isOn) {
/* 270 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPER", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPER", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 297 */       if (obj instanceof MQDestination) {
/* 298 */         iVal = ((MQDestination)obj).getPersistence();
/*     */       }
/*     */       else {
/*     */         
/* 302 */         String detail = "object is an unexpected type";
/* 303 */         String key = "JMSADM1016";
/* 304 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 305 */         JMSException iee = new JMSException(msg, key);
/* 306 */         if (Trace.isOn) {
/* 307 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APPER", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 310 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 314 */       String value = valToString(iVal);
/*     */ 
/*     */       
/* 317 */       props.put("PERSISTENCE", value);
/*     */     }
/*     */     finally {
/*     */       
/* 321 */       if (Trace.isOn) {
/* 322 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPER", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPER", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPER", "longName()");
/*     */     }
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPER", "longName()", "PERSISTENCE");
/*     */     }
/* 348 */     return "PERSISTENCE";
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
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPER", "shortName()");
/*     */     }
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPER", "shortName()", "PER");
/*     */     }
/* 365 */     return "PER";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPER.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */