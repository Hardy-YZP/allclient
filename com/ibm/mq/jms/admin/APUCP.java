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
/*     */ public class APUCP
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APUCP.java";
/*     */   public static final String LONGNAME = "USECONNPOOLING";
/*     */   public static final String SHORTNAME = "UCP";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APUCP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APUCP.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  81 */       Object value = getProperty("UCP", props);
/*     */ 
/*     */       
/*  84 */       if (value != null) {
/*  85 */         boolean bVal; if (value instanceof Integer) {
/*  86 */           int intVal = ((Integer)value).intValue();
/*  87 */           bVal = (intVal == 1);
/*     */         }
/*  89 */         else if (value instanceof Boolean) {
/*  90 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/*     */           
/*  93 */           String str = ((String)value).toUpperCase();
/*  94 */           if (str.equals("NO")) {
/*  95 */             bVal = false;
/*     */           }
/*  97 */           else if (str.equals("YES")) {
/*  98 */             bVal = true;
/*     */           } else {
/*     */             
/* 101 */             BAOException traceRet1 = new BAOException(4, "UCP", str);
/* 102 */             if (Trace.isOn) {
/* 103 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 106 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 111 */         if (obj instanceof MQConnectionFactory) {
/* 112 */           ((MQConnectionFactory)obj).setUseConnectionPooling(bVal);
/*     */         }
/*     */         else {
/*     */           
/* 116 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 117 */           String key = "JMSADM1016";
/* 118 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 119 */           JMSException iee = new JMSException(msg, key);
/* 120 */           if (Trace.isOn) {
/* 121 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 124 */           throw iee;
/*     */         }
/*     */       
/*     */       } 
/* 128 */     } catch (JMSException e) {
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 137 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUCP", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUCP", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 168 */       if (obj instanceof MQConnectionFactory) {
/* 169 */         boolean b = ((MQConnectionFactory)obj).getUseConnectionPooling();
/* 170 */         if (b) {
/* 171 */           sVal = "YES";
/*     */         } else {
/*     */           
/* 174 */           sVal = "NO";
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 179 */         String detail = "object is an unexpected type";
/* 180 */         String key = "JMSADM1016";
/* 181 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */         JMSException iee = new JMSException(msg, key);
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APUCP", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 187 */         throw iee;
/*     */       } 
/*     */       
/* 190 */       if (sVal != null)
/*     */       {
/* 192 */         props.put("USECONNPOOLING", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APUCP", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUCP", "setPropertyFromObject(Map<String , Object>,Object)");
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
/*     */   public String longName() {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUCP", "longName()");
/*     */     }
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUCP", "longName()", "USECONNPOOLING");
/*     */     }
/* 223 */     return "USECONNPOOLING";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUCP", "shortName()");
/*     */     }
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUCP", "shortName()", "UCP");
/*     */     }
/* 237 */     return "UCP";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APUCP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */