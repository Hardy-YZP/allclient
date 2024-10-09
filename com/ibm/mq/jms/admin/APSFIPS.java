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
/*     */ public class APSFIPS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSFIPS.java";
/*     */   public static final String LONGNAME = "SSLFIPSREQUIRED";
/*     */   public static final String SHORTNAME = "SFIPS";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.admin.APSFIPS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSFIPS.java");
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
/*     */   public static String valToString(boolean bVal) throws JMSException {
/*     */     String sVal;
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry("com.ibm.mq.jms.admin.APSFIPS", "valToString(boolean)", new Object[] {
/*  72 */             Boolean.valueOf(bVal)
/*     */           });
/*     */     }
/*     */     
/*  76 */     if (bVal) {
/*  77 */       sVal = "YES";
/*     */     } else {
/*     */       
/*  80 */       sVal = "NO";
/*     */     } 
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit("com.ibm.mq.jms.admin.APSFIPS", "valToString(boolean)", sVal);
/*     */     }
/*  86 */     return sVal;
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
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry("com.ibm.mq.jms.admin.APSFIPS", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 102 */     String str = s.toUpperCase();
/*     */     
/* 104 */     if (str.equals("NO")) {
/* 105 */       bVal = false;
/*     */     }
/* 107 */     else if (str.equals("YES")) {
/* 108 */       bVal = true;
/*     */     } else {
/*     */       
/* 111 */       BAOException traceRet1 = new BAOException(4, "SFIPS", str);
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.throwing("com.ibm.mq.jms.admin.APSFIPS", "stringToVal(String)", traceRet1);
/*     */       }
/* 115 */       throw traceRet1;
/*     */     } 
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit("com.ibm.mq.jms.admin.APSFIPS", "stringToVal(String)", Boolean.valueOf(bVal));
/*     */     }
/* 121 */     return bVal;
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSFIPS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 141 */       Object value = getProperty("SFIPS", props);
/*     */       
/* 143 */       boolean bVal = false;
/*     */       
/* 145 */       if (value != null) {
/* 146 */         if (value instanceof Boolean) {
/* 147 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/*     */           
/* 150 */           bVal = stringToVal((String)value);
/*     */         } 
/*     */         
/* 153 */         if (obj instanceof MQConnectionFactory)
/*     */         {
/*     */           
/* 156 */           ((MQConnectionFactory)obj).setSSLFipsRequired(bVal);
/*     */         }
/*     */         else
/*     */         {
/* 160 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 161 */           String key = "JMSADM1016";
/* 162 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 163 */           JMSException iee = new JMSException(msg, key);
/* 164 */           if (Trace.isOn) {
/* 165 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSFIPS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 168 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 173 */     } catch (JMSException e) {
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSFIPS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSFIPS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 182 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSFIPS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSFIPS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSFIPS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 214 */       if (obj instanceof MQConnectionFactory) {
/* 215 */         boolean bVal = ((MQConnectionFactory)obj).getSSLFipsRequired();
/* 216 */         sVal = valToString(bVal);
/*     */       }
/*     */       else {
/*     */         
/* 220 */         String detail = "object is an unexpected type";
/* 221 */         String key = "JMSADM1016";
/* 222 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 223 */         JMSException iee = new JMSException(msg, key);
/* 224 */         if (Trace.isOn) {
/* 225 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSFIPS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 228 */         throw iee;
/*     */       } 
/*     */       
/* 231 */       if (sVal != null)
/*     */       {
/* 233 */         props.put("SSLFIPSREQUIRED", sVal);
/*     */       
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 239 */       if (Trace.isOn) {
/* 240 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSFIPS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSFIPS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSFIPS", "longName()");
/*     */     }
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSFIPS", "longName()", "SSLFIPSREQUIRED");
/*     */     }
/* 266 */     return "SSLFIPSREQUIRED";
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
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSFIPS", "shortName()");
/*     */     }
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSFIPS", "shortName()", "SFIPS");
/*     */     }
/* 283 */     return "SFIPS";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSFIPS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */