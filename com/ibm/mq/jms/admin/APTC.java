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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APTC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTC.java";
/*     */   public static final String LONGNAME = "TARGCLIENT";
/*     */   public static final String SHORTNAME = "TC";
/*     */   public static final String JMS_COMPLIANT = "JMS";
/*     */   public static final String NONJMS_MQ = "MQ";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APTC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTC.java");
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
/*     */   public static String valToString(int iVal) throws JMSException {
/*     */     String sVal;
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry("com.ibm.mq.jms.admin.APTC", "valToString(int)", new Object[] {
/*  85 */             Integer.valueOf(iVal)
/*     */           });
/*     */     }
/*     */     
/*  89 */     if (iVal == 0) {
/*  90 */       sVal = "JMS";
/*     */     }
/*  92 */     else if (iVal == 1) {
/*  93 */       sVal = "MQ";
/*     */     }
/*     */     else {
/*     */       
/*  97 */       String detail = "object is an unexpected type";
/*  98 */       String key = "JMSADM1016";
/*  99 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 100 */       JMSException iee = new JMSException(msg, key);
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.throwing("com.ibm.mq.jms.admin.APTC", "valToString(int)", (Throwable)iee);
/*     */       }
/* 104 */       throw iee;
/*     */     } 
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit("com.ibm.mq.jms.admin.APTC", "valToString(int)", sVal);
/*     */     }
/* 110 */     return sVal;
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
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry("com.ibm.mq.jms.admin.APTC", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 126 */     String str = s.toUpperCase();
/*     */     
/* 128 */     if (str.equals("JMS")) {
/* 129 */       iVal = 0;
/*     */     }
/* 131 */     else if (str.equals("MQ")) {
/* 132 */       iVal = 1;
/*     */     } else {
/*     */       
/*     */       try {
/* 136 */         iVal = Integer.parseInt(str);
/*     */       }
/* 138 */       catch (NumberFormatException e) {
/* 139 */         if (Trace.isOn) {
/* 140 */           Trace.catchBlock("com.ibm.mq.jms.admin.APTC", "stringToVal(String)", e);
/*     */         }
/* 142 */         BAOException traceRet1 = new BAOException(4, "TC", str);
/* 143 */         if (Trace.isOn) {
/* 144 */           Trace.throwing("com.ibm.mq.jms.admin.APTC", "stringToVal(String)", traceRet1);
/*     */         }
/* 146 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit("com.ibm.mq.jms.admin.APTC", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 153 */     return iVal;
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
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 174 */       Object value = getProperty("TC", props);
/*     */ 
/*     */       
/* 177 */       if (value != null) {
/* 178 */         int iVal; if (value instanceof Integer) {
/* 179 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 182 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 186 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 188 */             ((MQDestination)obj).setTargetClient(iVal);
/*     */           }
/* 190 */           catch (JMSException e) {
/* 191 */             if (Trace.isOn) {
/* 192 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 196 */             BAOException be = new BAOException(4, "TC", Integer.toString(iVal));
/* 197 */             if (Trace.isOn) {
/* 198 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 201 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 206 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 207 */           String key = "JMSADM1016";
/* 208 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 209 */           JMSException iee = new JMSException(msg, key);
/* 210 */           if (Trace.isOn) {
/* 211 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 214 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 219 */     } catch (BAOException e) {
/* 220 */       if (Trace.isOn) {
/* 221 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 228 */       throw e;
/*     */     
/*     */     }
/* 231 */     catch (JMSException e) {
/* 232 */       if (Trace.isOn) {
/* 233 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 236 */       if (Trace.isOn) {
/* 237 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 240 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 272 */       if (obj instanceof MQDestination) {
/* 273 */         iVal = ((MQDestination)obj).getTargetClient();
/*     */       }
/*     */       else {
/*     */         
/* 277 */         String detail = "object is an unexpected type";
/* 278 */         String key = "JMSADM1016";
/* 279 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 280 */         JMSException iee = new JMSException(msg, key);
/* 281 */         if (Trace.isOn) {
/* 282 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 285 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 289 */       String value = valToString(iVal);
/*     */ 
/*     */       
/* 292 */       props.put("TARGCLIENT", value);
/*     */     }
/*     */     finally {
/*     */       
/* 296 */       if (Trace.isOn) {
/* 297 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTC", "longName()");
/*     */     }
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTC", "longName()", "TARGCLIENT");
/*     */     }
/* 323 */     return "TARGCLIENT";
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
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTC", "shortName()");
/*     */     }
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTC", "shortName()", "TC");
/*     */     }
/* 340 */     return "TC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */