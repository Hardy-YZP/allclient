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
/*     */ public class APAEX
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APAEX.java";
/*     */   public static final String LONGNAME = "ASYNCEXCEPTION";
/*     */   public static final String SHORTNAME = "AEX";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jms.admin.APAEX", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APAEX.java");
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
/*     */   protected enum APAEXEnum
/*     */   {
/*  59 */     ALL(-1), CONNECTIONBROKEN(1);
/*     */     
/*     */     private int jmsConstant;
/*     */     
/*     */     APAEXEnum(int jmsConstant) {
/*  64 */       if (Trace.isOn)
/*  65 */         Trace.entry(this, "com.ibm.mq.jms.admin.APAEXEnum", "<init>(int)", new Object[] {
/*  66 */               Integer.valueOf(jmsConstant)
/*     */             }); 
/*  68 */       this.jmsConstant = jmsConstant;
/*  69 */       if (Trace.isOn) {
/*  70 */         Trace.exit(this, "com.ibm.mq.jms.admin.APAEXEnum", "<init>(int)");
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public static int stringToVal(String s) {
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.entry("com.ibm.mq.jms.admin.APAEXEnum", "stringToVal(String)", new Object[] { s });
/*     */       }
/*  79 */       int traceRet1 = (valueOf(s)).jmsConstant;
/*  80 */       if (Trace.isOn) {
/*  81 */         Trace.exit("com.ibm.mq.jms.admin.APAEXEnum", "stringToVal(String)", 
/*  82 */             Integer.valueOf(traceRet1));
/*     */       }
/*  84 */       return traceRet1;
/*     */     }
/*     */     
/*     */     public static String valToString(int i) {
/*  88 */       if (Trace.isOn)
/*  89 */         Trace.entry("com.ibm.mq.jms.admin.APAEXEnum", "valToString(int)", new Object[] {
/*  90 */               Integer.valueOf(i)
/*     */             }); 
/*  92 */       String returnVal = null;
/*     */       
/*  94 */       for (APAEXEnum e : values()) {
/*  95 */         if (e.jmsConstant == i) {
/*  96 */           returnVal = e.name();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.exit("com.ibm.mq.jms.admin.APAEXEnum", "valToString(int)", returnVal);
/*     */       }
/* 104 */       return returnVal;
/*     */     }
/*     */     
/*     */     public static int objectToVal(Object value) throws JMSException {
/*     */       int iVal;
/* 109 */       if (Trace.isOn) {
/* 110 */         Trace.entry("com.ibm.mq.jms.admin.APAEXEnum", "objectToVal(Object)", new Object[] { value });
/*     */       }
/*     */ 
/*     */       
/* 114 */       if (value instanceof Integer) {
/* 115 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 118 */       else if (value instanceof String) {
/* 119 */         String str = ((String)value).toUpperCase();
/* 120 */         iVal = stringToVal(str);
/*     */       }
/*     */       else {
/*     */         
/* 124 */         String detail = "value supplied as an unexpected object type " + value.getClass();
/* 125 */         String key = "JMSADM1016";
/* 126 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 127 */         JMSException traceRet1 = new JMSException(msg, key);
/* 128 */         if (Trace.isOn) {
/* 129 */           Trace.throwing("com.ibm.mq.jms.admin.APAEXEnum", "objectToVal(Object)", (Throwable)traceRet1);
/*     */         }
/* 131 */         throw traceRet1;
/*     */       } 
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.exit("com.ibm.mq.jms.admin.APAEXEnum", "objectToVal(Object)", Integer.valueOf(iVal));
/*     */       }
/*     */       
/* 137 */       return iVal;
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
/*     */   public static String valToString(int mret) {
/* 150 */     if (Trace.isOn)
/* 151 */       Trace.entry("com.ibm.mq.jms.admin.APAEX", "valToString(int)", new Object[] {
/* 152 */             Integer.valueOf(mret)
/*     */           }); 
/* 154 */     String retVal = APAEXEnum.valToString(mret);
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit("com.ibm.mq.jms.admin.APAEX", "valToString(int)", retVal);
/*     */     }
/* 158 */     return retVal;
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
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry("com.ibm.mq.jms.admin.APAEX", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/*     */     try {
/* 174 */       int traceRet2 = APCROPT.APCROPTEnum.stringToVal(s);
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.exit("com.ibm.mq.jms.admin.APAEX", "stringToVal(String)", Integer.valueOf(traceRet2));
/*     */       }
/*     */       
/* 179 */       return traceRet2;
/*     */     }
/* 181 */     catch (IllegalArgumentException e) {
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.catchBlock("com.ibm.mq.jms.admin.APAEX", "stringToVal(String)", e);
/*     */       }
/* 185 */       BAOException traceRet1 = new BAOException(4, "AEX", s);
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.throwing("com.ibm.mq.jms.admin.APAEX", "stringToVal(String)", traceRet1);
/*     */       }
/* 189 */       throw traceRet1;
/*     */     } finally {
/*     */       
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APAEX", "stringToVal(String)");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 218 */       Object value = getProperty("AEX", props);
/*     */ 
/*     */       
/* 221 */       if (value != null)
/*     */       {
/*     */         
/* 224 */         int iVal = APAEXEnum.objectToVal(value);
/*     */ 
/*     */         
/* 227 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 229 */             ((MQConnectionFactory)obj).setAsyncExceptions(iVal);
/*     */           }
/* 231 */           catch (Exception e) {
/* 232 */             if (Trace.isOn) {
/* 233 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */ 
/*     */             
/* 237 */             BAOException be = new BAOException(4, "AEX", Integer.toString(iVal));
/* 238 */             if (Trace.isOn) {
/* 239 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 242 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 247 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 248 */           String key = "JMSADM1016";
/* 249 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 250 */           JMSException iee = new JMSException(msg, key);
/* 251 */           if (Trace.isOn) {
/* 252 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 255 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 260 */     } catch (BAOException e) {
/* 261 */       if (Trace.isOn) {
/* 262 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 265 */       if (Trace.isOn) {
/* 266 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 269 */       throw e;
/*     */     }
/* 271 */     catch (JMSException e) {
/* 272 */       if (Trace.isOn) {
/* 273 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 280 */       throw e;
/*     */     } 
/*     */     
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAEX", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAEX", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/* 303 */     String sVal = null;
/*     */     
/* 305 */     if (obj instanceof MQConnectionFactory) {
/* 306 */       sVal = valToString(((MQConnectionFactory)obj).getAsyncExceptions());
/*     */     }
/*     */     else {
/*     */       
/* 310 */       String detail = "object is an unexpected type";
/* 311 */       String key = "JMSADM1016";
/* 312 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 313 */       JMSException iee = new JMSException(msg, key);
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APAEX", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 318 */       throw iee;
/*     */     } 
/*     */ 
/*     */     
/* 322 */     props.put("ASYNCEXCEPTION", String.valueOf(sVal));
/*     */     
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAEX", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAEX", "longName()");
/*     */     }
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAEX", "longName()", "ASYNCEXCEPTION");
/*     */     }
/* 342 */     return "ASYNCEXCEPTION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAEX", "shortName()");
/*     */     }
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAEX", "shortName()", "AEX");
/*     */     }
/* 356 */     return "AEX";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APAEX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */