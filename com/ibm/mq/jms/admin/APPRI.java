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
/*     */ public class APPRI
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPRI.java";
/*     */   public static final String LONGNAME = "PRIORITY";
/*     */   public static final String SHORTNAME = "PRI";
/*     */   public static final String PRI_APP = "APP";
/*     */   public static final String PRI_QDEF = "QDEF";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APPRI", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPRI.java");
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
/*     */   public static String valToString(int pri) throws JMSException {
/*     */     String value;
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry("com.ibm.mq.jms.admin.APPRI", "valToString(int)", new Object[] {
/*  86 */             Integer.valueOf(pri)
/*     */           });
/*     */     }
/*     */     
/*  90 */     if (pri == -2) {
/*  91 */       value = "APP";
/*     */     }
/*  93 */     else if (pri == -1) {
/*  94 */       value = "QDEF";
/*     */     } else {
/*     */       
/*  97 */       value = String.valueOf(pri);
/*     */     } 
/*     */     
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit("com.ibm.mq.jms.admin.APPRI", "valToString(int)", value);
/*     */     }
/* 103 */     return value;
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
/*     */     int pri;
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry("com.ibm.mq.jms.admin.APPRI", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 119 */     String str = s.toUpperCase();
/*     */     
/* 121 */     if (str.equals("APP")) {
/* 122 */       pri = -2;
/*     */     }
/* 124 */     else if (str.equals("QDEF")) {
/* 125 */       pri = -1;
/*     */     } else {
/*     */       
/*     */       try {
/* 129 */         pri = Integer.parseInt(str);
/*     */       }
/* 131 */       catch (NumberFormatException e) {
/* 132 */         if (Trace.isOn) {
/* 133 */           Trace.catchBlock("com.ibm.mq.jms.admin.APPRI", "stringToVal(String)", e);
/*     */         }
/* 135 */         BAOException traceRet1 = new BAOException(4, "PRI", str);
/* 136 */         if (Trace.isOn) {
/* 137 */           Trace.throwing("com.ibm.mq.jms.admin.APPRI", "stringToVal(String)", traceRet1);
/*     */         }
/* 139 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit("com.ibm.mq.jms.admin.APPRI", "stringToVal(String)", Integer.valueOf(pri));
/*     */     }
/* 146 */     return pri;
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
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 167 */       Object value = getProperty("PRI", props);
/*     */ 
/*     */       
/* 170 */       if (value != null) {
/*     */         int pri;
/* 172 */         if (value instanceof Integer) {
/* 173 */           pri = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 176 */           pri = stringToVal((String)value);
/*     */         } 
/*     */         
/* 179 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 181 */             ((MQDestination)obj).setPriority(pri);
/*     */           }
/* 183 */           catch (JMSException e) {
/* 184 */             if (Trace.isOn) {
/* 185 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 189 */             BAOException be = new BAOException(4, "PRI", Integer.toString(pri));
/* 190 */             if (Trace.isOn) {
/* 191 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 194 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 199 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 200 */           String key = "JMSADM1016";
/* 201 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 202 */           JMSException iee = new JMSException(msg, key);
/* 203 */           if (Trace.isOn) {
/* 204 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 207 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 212 */     } catch (BAOException e) {
/* 213 */       if (Trace.isOn) {
/* 214 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 221 */       throw e;
/*     */     
/*     */     }
/* 224 */     catch (JMSException e) {
/* 225 */       if (Trace.isOn) {
/* 226 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 233 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRI", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRI", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int pri;
/*     */       
/* 265 */       if (obj instanceof MQDestination) {
/* 266 */         pri = ((MQDestination)obj).getPriority();
/*     */       }
/*     */       else {
/*     */         
/* 270 */         String detail = "object is an unexpected type";
/* 271 */         String key = "JMSADM1016";
/* 272 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 273 */         JMSException iee = new JMSException(msg, key);
/* 274 */         if (Trace.isOn) {
/* 275 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APPRI", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 278 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 283 */       String value = valToString(pri);
/*     */ 
/*     */       
/* 286 */       props.put("PRIORITY", value);
/*     */     }
/*     */     finally {
/*     */       
/* 290 */       if (Trace.isOn) {
/* 291 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPRI", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRI", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRI", "longName()");
/*     */     }
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRI", "longName()", "PRIORITY");
/*     */     }
/* 317 */     return "PRIORITY";
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
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPRI", "shortName()");
/*     */     }
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPRI", "shortName()", "PRI");
/*     */     }
/* 334 */     return "PRI";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPRI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */