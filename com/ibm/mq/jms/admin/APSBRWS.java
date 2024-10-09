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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APSBRWS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSBRWS.java";
/*     */   public static final String LONGNAME = "SPARSESUBS";
/*     */   public static final String SHORTNAME = "SSUBS";
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.mq.jms.admin.APSBRWS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSBRWS.java");
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
/*     */   public static String valToString(boolean x) throws JMSException {
/*     */     String sVal;
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry("com.ibm.mq.jms.admin.APSBRWS", "valToString(boolean)", new Object[] {
/*  93 */             Boolean.valueOf(x)
/*     */           });
/*     */     }
/*     */     
/*  97 */     if (x) {
/*  98 */       sVal = "YES";
/*     */     } else {
/*     */       
/* 101 */       sVal = "NO";
/*     */     } 
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit("com.ibm.mq.jms.admin.APSBRWS", "valToString(boolean)", sVal);
/*     */     }
/* 107 */     return sVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean stringToVal(String s) throws BAOException {
/*     */     boolean bVal;
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry("com.ibm.mq.jms.admin.APSBRWS", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 123 */     String str = s.toUpperCase();
/*     */     
/* 125 */     if (str.equals("NO")) {
/* 126 */       bVal = false;
/*     */     }
/* 128 */     else if (str.equals("YES")) {
/* 129 */       bVal = true;
/*     */     } else {
/*     */       
/* 132 */       BAOException traceRet1 = new BAOException(4, "SSUBS", str);
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.throwing("com.ibm.mq.jms.admin.APSBRWS", "stringToVal(String)", traceRet1);
/*     */       }
/* 136 */       throw traceRet1;
/*     */     } 
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit("com.ibm.mq.jms.admin.APSBRWS", "stringToVal(String)", Boolean.valueOf(bVal));
/*     */     }
/* 142 */     return bVal;
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
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSBRWS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 163 */       Object value = getProperty("SSUBS", props);
/*     */ 
/*     */       
/* 166 */       if (value != null) {
/* 167 */         boolean bVal; if (value instanceof Integer) {
/* 168 */           int intVal = ((Integer)value).intValue();
/* 169 */           bVal = (intVal == 1);
/*     */         }
/* 171 */         else if (value instanceof Boolean) {
/* 172 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/*     */           
/* 175 */           bVal = stringToVal((String)value);
/*     */         } 
/*     */         
/* 178 */         if (obj instanceof MQConnectionFactory) {
/* 179 */           ((MQConnectionFactory)obj).setSparseSubscriptions(bVal);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 184 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 185 */           String key = "JMSADM1016";
/* 186 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 187 */           JMSException iee = new JMSException(msg, key);
/* 188 */           if (Trace.isOn) {
/* 189 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSBRWS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 192 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 197 */     } catch (JMSException e) {
/* 198 */       if (Trace.isOn) {
/* 199 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSBRWS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSBRWS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 206 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSBRWS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSBRWS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSBRWS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 237 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 238 */         boolean x = ((MQConnectionFactory)obj).getSparseSubscriptions();
/* 239 */         sVal = valToString(x);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 244 */         String detail = "object is an unexpected type";
/* 245 */         String key = "JMSADM1016";
/* 246 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 247 */         JMSException iee = new JMSException(msg, key);
/* 248 */         if (Trace.isOn) {
/* 249 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSBRWS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 252 */         throw iee;
/*     */       } 
/*     */       
/* 255 */       if (sVal != null)
/*     */       {
/* 257 */         props.put("SPARSESUBS", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 262 */       if (Trace.isOn) {
/* 263 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSBRWS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSBRWS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSBRWS", "longName()");
/*     */     }
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSBRWS", "longName()", "SPARSESUBS");
/*     */     }
/* 289 */     return "SPARSESUBS";
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
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSBRWS", "shortName()");
/*     */     }
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSBRWS", "shortName()", "SSUBS");
/*     */     }
/* 306 */     return "SSUBS";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSBRWS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */