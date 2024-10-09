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
/*     */ public class APPORT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPORT.java";
/*     */   public static final String LONGNAME = "PORT";
/*     */   public static final String SHORTNAME = "PORT";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APPORT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPORT.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       Object value = getProperty("PORT", props);
/*     */ 
/*     */       
/*  87 */       if (value != null) {
/*  88 */         int iVal; if (value instanceof Integer) {
/*  89 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  93 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  95 */           catch (NumberFormatException e) {
/*  96 */             if (Trace.isOn) {
/*  97 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 100 */             BAOException traceRet1 = new BAOException(4, "PORT", value);
/* 101 */             if (Trace.isOn) {
/* 102 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 105 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 110 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 112 */             ((MQConnectionFactory)obj).setPort(iVal);
/*     */           }
/* 114 */           catch (JMSException e) {
/* 115 */             if (Trace.isOn) {
/* 116 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */             
/* 120 */             BAOException be = new BAOException(4, "PORT", Integer.toString(iVal));
/* 121 */             if (Trace.isOn) {
/* 122 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 125 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 130 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 131 */           String key = "JMSADM1016";
/* 132 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 133 */           JMSException iee = new JMSException(msg, key);
/* 134 */           if (Trace.isOn) {
/* 135 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 138 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 143 */     } catch (BAOException e) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 152 */       throw e;
/*     */     
/*     */     }
/* 155 */     catch (JMSException e) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 164 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPORT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPORT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 199 */       if (obj instanceof MQConnectionFactory) {
/* 200 */         iVal = ((MQConnectionFactory)obj).getPort();
/*     */       }
/*     */       else {
/*     */         
/* 204 */         String detail = "object is an unexpected type";
/* 205 */         String key = "JMSADM1016";
/* 206 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 207 */         JMSException iee = new JMSException(msg, key);
/* 208 */         if (Trace.isOn) {
/* 209 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APPORT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 212 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 216 */       props.put("PORT", String.valueOf(iVal));
/*     */     }
/*     */     finally {
/*     */       
/* 220 */       if (Trace.isOn) {
/* 221 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPORT", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPORT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPORT", "longName()");
/*     */     }
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPORT", "longName()", "PORT");
/*     */     }
/* 246 */     return "PORT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPORT", "shortName()");
/*     */     }
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPORT", "shortName()", "PORT");
/*     */     }
/* 260 */     return "PORT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPORT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */