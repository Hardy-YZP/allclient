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
/*     */ public class APCROPT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCROPT.java";
/*     */   public static final String LONGNAME = "CLIENTRECONNECTOPTIONS";
/*     */   public static final String SHORTNAME = "CROPT";
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.jms.admin.APCROPT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCROPT.java");
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
/*     */   protected enum APCROPTEnum
/*     */   {
/*  56 */     QMGR(67108864),
/*  57 */     ANY(16777216),
/*  58 */     DISABLED(33554432),
/*  59 */     ASDEF(0);
/*     */     
/*     */     private int jmsConstant;
/*     */     
/*     */     APCROPTEnum(int jmsConstant) {
/*  64 */       if (Trace.isOn)
/*  65 */         Trace.entry(this, "com.ibm.mq.jms.admin.APCROPTEnum", "<init>(int)", new Object[] {
/*  66 */               Integer.valueOf(jmsConstant)
/*     */             }); 
/*  68 */       this.jmsConstant = jmsConstant;
/*  69 */       if (Trace.isOn) {
/*  70 */         Trace.exit(this, "com.ibm.mq.jms.admin.APCROPTEnum", "<init>(int)");
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public static int stringToVal(String s) {
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.entry("com.ibm.mq.jms.admin.APCROPTEnum", "stringToVal(String)", new Object[] { s });
/*     */       }
/*  79 */       int traceRet1 = (valueOf(s)).jmsConstant;
/*  80 */       if (Trace.isOn) {
/*  81 */         Trace.exit("com.ibm.mq.jms.admin.APCROPTEnum", "stringToVal(String)", 
/*  82 */             Integer.valueOf(traceRet1));
/*     */       }
/*  84 */       return traceRet1;
/*     */     }
/*     */     
/*     */     public static String valToString(int i) {
/*  88 */       if (Trace.isOn)
/*  89 */         Trace.entry("com.ibm.mq.jms.admin.APCROPTEnum", "valToString(int)", new Object[] {
/*  90 */               Integer.valueOf(i)
/*     */             }); 
/*  92 */       String returnVal = null;
/*     */       
/*  94 */       for (APCROPTEnum e : values()) {
/*  95 */         if (e.jmsConstant == i) {
/*  96 */           returnVal = e.name();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.exit("com.ibm.mq.jms.admin.APCROPTEnum", "valToString(int)", returnVal);
/*     */       }
/* 104 */       return returnVal;
/*     */     }
/*     */     public static int objectToVal(Object value) {
/*     */       int iVal;
/* 108 */       if (Trace.isOn) {
/* 109 */         Trace.entry("com.ibm.mq.jms.admin.APCROPTEnum", "objectToVal(Object)", new Object[] { value });
/*     */       }
/*     */ 
/*     */       
/* 113 */       if (value instanceof Integer) {
/* 114 */         iVal = ((Integer)value).intValue();
/*     */       }
/*     */       else {
/*     */         
/* 118 */         String str = ((String)value).toUpperCase();
/* 119 */         iVal = stringToVal(str);
/*     */       } 
/*     */       
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.exit("com.ibm.mq.jms.admin.APCROPTEnum", "objectToVal(Object)", 
/* 124 */             Integer.valueOf(iVal));
/*     */       }
/* 126 */       return iVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String valToString(int mret) {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry("com.ibm.mq.jms.admin.APCROPT", "valToString(int)", new Object[] {
/* 139 */             Integer.valueOf(mret)
/*     */           });
/*     */     }
/* 142 */     String retVal = APCROPTEnum.valToString(mret);
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit("com.ibm.mq.jms.admin.APCROPT", "valToString(int)", retVal);
/*     */     }
/* 147 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/*     */     int val;
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry("com.ibm.mq.jms.admin.APCROPT", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 164 */       val = APCROPTEnum.stringToVal(s);
/*     */     }
/* 166 */     catch (IllegalArgumentException e) {
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.catchBlock("com.ibm.mq.jms.admin.APCROPT", "stringToVal(String)", e);
/*     */       }
/* 170 */       BAOException traceRet1 = new BAOException(4, "CROPT", s);
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.throwing("com.ibm.mq.jms.admin.APCROPT", "stringToVal(String)", traceRet1);
/*     */       }
/* 174 */       throw traceRet1;
/*     */     } 
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit("com.ibm.mq.jms.admin.APCROPT", "stringToVal(String)", Integer.valueOf(val));
/*     */     }
/* 180 */     return val;
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
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 199 */       Object value = getProperty("CROPT", props);
/*     */ 
/*     */       
/* 202 */       if (value != null)
/*     */       {
/*     */         
/* 205 */         int iVal = APCROPTEnum.objectToVal(value);
/*     */ 
/*     */         
/* 208 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 210 */             ((MQConnectionFactory)obj).setClientReconnectOptions(iVal);
/*     */           }
/* 212 */           catch (JMSException e) {
/* 213 */             if (Trace.isOn) {
/* 214 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 218 */             BAOException traceRet2 = new BAOException(4, "CROPT", valToString(iVal));
/* 219 */             if (Trace.isOn) {
/* 220 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 1);
/*     */             }
/*     */             
/* 223 */             throw traceRet2;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 228 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 229 */           String key = "JMSADM1016";
/* 230 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 231 */           JMSException traceRet3 = new JMSException(msg, key);
/* 232 */           if (Trace.isOn) {
/* 233 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)traceRet3, 2);
/*     */           }
/*     */           
/* 236 */           throw traceRet3;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 241 */     } catch (BAOException e) {
/* 242 */       if (Trace.isOn) {
/* 243 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 246 */       if (Trace.isOn) {
/* 247 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 250 */       throw e;
/*     */     
/*     */     }
/* 253 */     catch (JMSException e) {
/* 254 */       if (Trace.isOn) {
/* 255 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 262 */       throw e;
/*     */     } 
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCROPT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */     String sVal;
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCROPT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (obj instanceof MQConnectionFactory) {
/* 291 */       int mret = ((MQConnectionFactory)obj).getClientReconnectOptions();
/* 292 */       sVal = valToString(mret);
/*     */     }
/*     */     else {
/*     */       
/* 296 */       String detail = "object is an unexpected type";
/* 297 */       String key = "JMSADM1016";
/* 298 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 299 */       JMSException traceRet1 = new JMSException(msg, key);
/* 300 */       if (Trace.isOn) {
/* 301 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCROPT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 304 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 308 */     if (sVal != null && sVal.length() > 0) {
/* 309 */       props.put("CLIENTRECONNECTOPTIONS", sVal);
/*     */     }
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCROPT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCROPT", "longName()");
/*     */     }
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCROPT", "longName()", "CLIENTRECONNECTOPTIONS");
/*     */     }
/* 329 */     return "CLIENTRECONNECTOPTIONS";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCROPT", "shortName()");
/*     */     }
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCROPT", "shortName()", "CROPT");
/*     */     }
/* 343 */     return "CROPT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCROPT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */