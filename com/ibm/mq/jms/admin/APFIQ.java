/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
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
/*     */ 
/*     */ 
/*     */ public class APFIQ
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APFIQ.java";
/*     */   public static final String LONGNAME = "FAILIFQUIESCE";
/*     */   public static final String SHORTNAME = "FIQ";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APFIQ", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APFIQ.java");
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
/*     */   public static String valToString(int fiq) throws JMSException {
/*     */     String sVal;
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry("com.ibm.mq.jms.admin.APFIQ", "valToString(int)", new Object[] {
/*  76 */             Integer.valueOf(fiq)
/*     */           });
/*     */     }
/*     */     
/*  80 */     if (fiq == 1) {
/*  81 */       sVal = "YES";
/*     */     } else {
/*     */       
/*  84 */       sVal = "NO";
/*     */     } 
/*     */     
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit("com.ibm.mq.jms.admin.APFIQ", "valToString(int)", sVal);
/*     */     }
/*  90 */     return sVal;
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
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry("com.ibm.mq.jms.admin.APFIQ", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 106 */     String str = s.toUpperCase();
/*     */     
/* 108 */     if (str.equals("NO")) {
/* 109 */       iVal = 0;
/*     */     }
/* 111 */     else if (str.equals("YES")) {
/* 112 */       iVal = 1;
/*     */     } else {
/*     */       
/* 115 */       BAOException traceRet1 = new BAOException(4, "FIQ", str);
/* 116 */       if (Trace.isOn) {
/* 117 */         Trace.throwing("com.ibm.mq.jms.admin.APFIQ", "stringToVal(String)", traceRet1);
/*     */       }
/* 119 */       throw traceRet1;
/*     */     } 
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit("com.ibm.mq.jms.admin.APFIQ", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 125 */     return iVal;
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
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.jms.admin.APFIQ", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 146 */       Object value = getProperty("FIQ", props);
/*     */       
/* 148 */       int iVal = 0;
/*     */       
/* 150 */       if (value != null) {
/* 151 */         if (value instanceof Integer) {
/* 152 */           iVal = ((Integer)value).intValue();
/*     */         }
/* 154 */         else if (value instanceof Boolean) {
/*     */           
/* 156 */           boolean bVal = ((Boolean)value).booleanValue();
/*     */           
/* 158 */           if (bVal) {
/* 159 */             iVal = 1;
/*     */           } else {
/*     */             
/* 162 */             iVal = 0;
/*     */           } 
/*     */         } else {
/*     */           
/* 166 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 170 */         if (obj instanceof MQConnectionFactory)
/*     */         {
/* 172 */           ((MQConnectionFactory)obj).setFailIfQuiesce(iVal);
/*     */         
/*     */         }
/* 175 */         else if (obj instanceof MQDestination)
/*     */         {
/* 177 */           ((MQDestination)obj).setFailIfQuiesce(iVal);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/*     */           
/* 183 */           String detail = "object supplied as an unexpected type" + obj.getClass();
/* 184 */           String key = "JMSADM1016";
/* 185 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 186 */           JMSException iee = new JMSException(msg, key);
/* 187 */           if (Trace.isOn) {
/* 188 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APFIQ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 191 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 196 */     } catch (JMSException e) {
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APFIQ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 201 */       if (Trace.isOn) {
/* 202 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APFIQ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 205 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APFIQ", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.jms.admin.APFIQ", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.mq.jms.admin.APFIQ", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 236 */       if (obj instanceof MQConnectionFactory) {
/* 237 */         int fiq = ((MQConnectionFactory)obj).getFailIfQuiesce();
/* 238 */         sVal = valToString(fiq);
/*     */       }
/* 240 */       else if (obj instanceof MQDestination) {
/* 241 */         int fiq = ((MQDestination)obj).getFailIfQuiesce();
/*     */         
/* 243 */         sVal = valToString(fiq);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 248 */         String detail = "object is an unexpected type";
/* 249 */         String key = "JMSADM1016";
/* 250 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 251 */         JMSException iee = new JMSException(msg, key);
/* 252 */         if (Trace.isOn) {
/* 253 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APFIQ", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 256 */         throw iee;
/*     */       } 
/*     */       
/* 259 */       if (sVal != null)
/*     */       {
/* 261 */         props.put("FAILIFQUIESCE", sVal);
/*     */       
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 267 */       if (Trace.isOn) {
/* 268 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APFIQ", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.jms.admin.APFIQ", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.entry(this, "com.ibm.mq.jms.admin.APFIQ", "longName()");
/*     */     }
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.exit(this, "com.ibm.mq.jms.admin.APFIQ", "longName()", "FAILIFQUIESCE");
/*     */     }
/* 294 */     return "FAILIFQUIESCE";
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
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.mq.jms.admin.APFIQ", "shortName()");
/*     */     }
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.mq.jms.admin.APFIQ", "shortName()", "FIQ");
/*     */     }
/* 311 */     return "FIQ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APFIQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */