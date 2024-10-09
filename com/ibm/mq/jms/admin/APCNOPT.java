/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class APCNOPT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCNOPT.java";
/*     */   public static final String LONGNAME = "CONNOPT";
/*     */   public static final String SHORTNAME = "CNOPT";
/*     */   private static final String RESTRICTQM = "RESTRICTQM";
/*     */   private static final String RESTRICTQSG = "RESTRICTQSG";
/*     */   private static final String SERIALQM = "SERIALQM";
/*     */   private static final String SERIALQSG = "SERIALQSG";
/*     */   private static final String STANDARD = "STANDARD";
/*     */   private static final String FASTPATH = "FASTPATH";
/*     */   private static final String SHARED = "SHARED";
/*     */   private static final String ISOLATED = "ISOLATED";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jms.admin.APCNOPT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCNOPT.java");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static String valToString(int mret) throws JMSException {
/*  93 */     if (Trace.isOn)
/*  94 */       Trace.entry("com.ibm.mq.jms.admin.APCNOPT", "valToString(int)", new Object[] {
/*  95 */             Integer.valueOf(mret)
/*     */           }); 
/*  97 */     String sVal = "";
/*     */     
/*  99 */     if ((mret & 0x8) != 0) {
/* 100 */       sVal = sVal + "RESTRICTQM ";
/*     */     }
/* 102 */     if ((mret & 0x10) != 0) {
/* 103 */       sVal = sVal + "RESTRICTQSG ";
/*     */     }
/*     */ 
/*     */     
/* 107 */     if ((mret & 0x2) != 0) {
/* 108 */       sVal = sVal + "SERIALQM ";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 113 */     if ((mret & 0x4) != 0) {
/* 114 */       sVal = sVal + "SERIALQSG ";
/*     */     }
/*     */     
/* 117 */     if ((mret & 0x1) != 0) {
/* 118 */       sVal = sVal + "FASTPATH ";
/*     */     }
/* 120 */     if ((mret & 0x100) != 0) {
/* 121 */       sVal = sVal + "SHARED ";
/*     */     }
/* 123 */     if ((mret & 0x200) != 0) {
/* 124 */       sVal = sVal + "ISOLATED ";
/*     */     }
/* 126 */     if (sVal.indexOf("FASTPATH") == -1 && sVal.indexOf("SHARED") == -1 && sVal
/* 127 */       .indexOf("ISOLATED") == -1) {
/* 128 */       sVal = sVal + "STANDARD";
/*     */     }
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit("com.ibm.mq.jms.admin.APCNOPT", "valToString(int)", sVal);
/*     */     }
/* 133 */     return sVal;
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
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry("com.ibm.mq.jms.admin.APCNOPT", "stringToVal(String)", new Object[] { s });
/*     */     }
/* 148 */     int iVal = 0;
/*     */     
/* 150 */     StringTokenizer st = new StringTokenizer(s.toUpperCase());
/* 151 */     while (st.hasMoreTokens()) {
/* 152 */       String str = st.nextToken();
/* 153 */       if (str.equals("RESTRICTQM")) {
/* 154 */         iVal |= 0x8; continue;
/* 155 */       }  if (str.equals("RESTRICTQSG")) {
/* 156 */         iVal |= 0x10;
/*     */         
/*     */         continue;
/*     */       } 
/* 160 */       if (str.equals("SERIALQM")) {
/* 161 */         iVal |= 0x2;
/*     */         
/*     */         continue;
/*     */       } 
/* 165 */       if (str.equals("SERIALQSG")) {
/* 166 */         iVal |= 0x4; continue;
/* 167 */       }  if (str.equals("STANDARD"))
/*     */         continue; 
/* 169 */       if (str.equals("FASTPATH")) {
/* 170 */         iVal |= 0x1; continue;
/* 171 */       }  if (str.equals("SHARED")) {
/* 172 */         iVal |= 0x100; continue;
/* 173 */       }  if (str.equals("ISOLATED")) {
/* 174 */         iVal |= 0x200; continue;
/*     */       } 
/* 176 */       BAOException traceRet1 = new BAOException(4, "CNOPT", str);
/*     */       
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.throwing("com.ibm.mq.jms.admin.APCNOPT", "stringToVal(String)", traceRet1);
/*     */       }
/* 181 */       throw traceRet1;
/*     */     } 
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit("com.ibm.mq.jms.admin.APCNOPT", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 187 */     return iVal;
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
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 209 */       Object value = getProperty("CNOPT", props);
/*     */       
/* 211 */       if (value != null) {
/*     */         int iVal;
/*     */         
/* 214 */         if (value instanceof Integer) {
/* 215 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/* 217 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 221 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 223 */             ((MQConnectionFactory)obj)
/* 224 */               .setMQConnectionOptions(iVal);
/*     */           }
/* 226 */           catch (JMSException e) {
/* 227 */             if (Trace.isOn) {
/* 228 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 233 */             BAOException be = new BAOException(4, "CNOPT", valToString(iVal));
/* 234 */             if (Trace.isOn) {
/* 235 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 238 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 243 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 244 */           String key = "JMSADM1016";
/* 245 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 246 */           JMSException iee = new JMSException(msg, key);
/* 247 */           if (Trace.isOn) {
/* 248 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 251 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 256 */     } catch (BAOException e) {
/* 257 */       if (Trace.isOn) {
/* 258 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 261 */       if (Trace.isOn) {
/* 262 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 265 */       throw e;
/*     */     
/*     */     }
/* 268 */     catch (JMSException e) {
/* 269 */       if (Trace.isOn) {
/* 270 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 273 */       if (Trace.isOn) {
/* 274 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 277 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 281 */       if (Trace.isOn) {
/* 282 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNOPT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNOPT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 313 */       if (obj instanceof MQConnectionFactory) {
/* 314 */         int mret = ((MQConnectionFactory)obj).getMQConnectionOptions();
/* 315 */         sVal = valToString(mret);
/*     */       }
/*     */       else {
/*     */         
/* 319 */         String detail = "object is an unexpected type";
/* 320 */         String key = "JMSADM1016";
/* 321 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 322 */         JMSException iee = new JMSException(msg, key);
/* 323 */         if (Trace.isOn) {
/* 324 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCNOPT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 327 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 331 */       if (sVal != null && sVal.length() > 0) {
/* 332 */         props.put("CONNOPT", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCNOPT", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNOPT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 359 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNOPT", "longName()");
/*     */     }
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNOPT", "longName()", "CONNOPT");
/*     */     }
/* 364 */     return "CONNOPT";
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
/* 376 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNOPT", "shortName()");
/*     */     }
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNOPT", "shortName()", "CNOPT");
/*     */     }
/* 381 */     return "CNOPT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCNOPT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */