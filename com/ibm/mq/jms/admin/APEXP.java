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
/*     */ public class APEXP
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APEXP.java";
/*     */   public static final String LONGNAME = "EXPIRY";
/*     */   public static final String SHORTNAME = "EXP";
/*     */   public static final String EXP_APP = "APP";
/*     */   public static final String EXP_UNLIMITED = "UNLIM";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APEXP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APEXP.java");
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
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  90 */       Object value = getProperty("EXP", props);
/*     */ 
/*     */       
/*  93 */       if (value != null) {
/*  94 */         long exp; if (value instanceof Long) {
/*  95 */           exp = ((Long)value).longValue();
/*     */         } else {
/*     */           
/*  98 */           String str = ((String)value).toUpperCase();
/*  99 */           if (str.equals("APP")) {
/* 100 */             exp = -2L;
/*     */           }
/* 102 */           else if (str.equals("UNLIM")) {
/* 103 */             exp = 0L;
/*     */           } else {
/*     */             
/*     */             try {
/* 107 */               exp = Long.parseLong(str);
/*     */             }
/* 109 */             catch (NumberFormatException e) {
/* 110 */               if (Trace.isOn) {
/* 111 */                 Trace.catchBlock(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */               }
/*     */               
/* 114 */               BAOException be = new BAOException(4, "EXP", str);
/* 115 */               if (Trace.isOn) {
/* 116 */                 Trace.throwing(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */               }
/*     */               
/* 119 */               throw be;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 125 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 127 */             ((MQDestination)obj).setExpiry(exp);
/*     */           }
/* 129 */           catch (JMSException e) {
/* 130 */             if (Trace.isOn) {
/* 131 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */             
/* 135 */             BAOException be = new BAOException(4, "EXP", Long.toString(exp));
/* 136 */             if (Trace.isOn) {
/* 137 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 140 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 145 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 146 */           String key = "JMSADM1016";
/* 147 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 148 */           JMSException iee = new JMSException(msg, key);
/* 149 */           if (Trace.isOn) {
/* 150 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 153 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 158 */     } catch (BAOException e) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 167 */       throw e;
/*     */     
/*     */     }
/* 170 */     catch (JMSException e) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 179 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jms.admin.APEXP", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.mq.jms.admin.APEXP", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       long exp;
/*     */       
/* 211 */       if (obj instanceof MQDestination) {
/* 212 */         exp = ((MQDestination)obj).getExpiry();
/*     */       }
/*     */       else {
/*     */         
/* 216 */         String detail = "object is an unexpected type";
/* 217 */         String key = "JMSADM1016";
/* 218 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 219 */         JMSException iee = new JMSException(msg, key);
/* 220 */         if (Trace.isOn) {
/* 221 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APEXP", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 224 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 228 */       String value = valToString(exp);
/*     */ 
/*     */       
/* 231 */       props.put("EXPIRY", value);
/*     */     }
/*     */     finally {
/*     */       
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APEXP", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit(this, "com.ibm.mq.jms.admin.APEXP", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.jms.admin.APEXP", "longName()");
/*     */     }
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.admin.APEXP", "longName()", "EXPIRY");
/*     */     }
/* 262 */     return "EXPIRY";
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
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.jms.admin.APEXP", "shortName()");
/*     */     }
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.exit(this, "com.ibm.mq.jms.admin.APEXP", "shortName()", "EXP");
/*     */     }
/* 279 */     return "EXP";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String valToString(long exp) {
/*     */     String s;
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.entry("com.ibm.mq.jms.admin.APEXP", "valToString(long)", new Object[] {
/* 293 */             Long.valueOf(exp)
/*     */           });
/*     */     }
/*     */     
/* 297 */     if (exp == -2L) {
/* 298 */       s = "APP";
/*     */     }
/* 300 */     else if (exp == 0L) {
/* 301 */       s = "UNLIM";
/*     */     } else {
/*     */       
/* 304 */       s = String.valueOf(exp);
/*     */     } 
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.exit("com.ibm.mq.jms.admin.APEXP", "valToString(long)", s);
/*     */     }
/* 309 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long stringToVal(String s) throws BAOException {
/*     */     long exp;
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry("com.ibm.mq.jms.admin.APEXP", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */ 
/*     */     
/* 326 */     String str = s.toUpperCase();
/* 327 */     if (str.equals("APP")) {
/* 328 */       exp = -2L;
/*     */     }
/* 330 */     else if (str.equals("UNLIM")) {
/* 331 */       exp = 0L;
/*     */     } else {
/*     */       
/*     */       try {
/* 335 */         exp = Long.parseLong(str);
/*     */       }
/* 337 */       catch (NumberFormatException e) {
/* 338 */         if (Trace.isOn) {
/* 339 */           Trace.catchBlock("com.ibm.mq.jms.admin.APEXP", "stringToVal(String)", e);
/*     */         }
/* 341 */         BAOException be = new BAOException(4, "EXP", str);
/* 342 */         if (Trace.isOn) {
/* 343 */           Trace.throwing("com.ibm.mq.jms.admin.APEXP", "stringToVal(String)", be);
/*     */         }
/* 345 */         throw be;
/*     */       } 
/*     */     } 
/*     */     
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.exit("com.ibm.mq.jms.admin.APEXP", "stringToVal(String)", Long.valueOf(exp));
/*     */     }
/* 352 */     return exp;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APEXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */