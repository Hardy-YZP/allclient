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
/*     */ public class APCRT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCRT.java";
/*     */   public static final String LONGNAME = "CLIENTRECONNECTTIMEOUT";
/*     */   public static final String SHORTNAME = "CRT";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APCRT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCRT.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       Object value = getProperty("CRT", props);
/*     */       
/*  86 */       if (value != null) {
/*     */         int iVal;
/*     */         
/*  89 */         if (value instanceof Integer) {
/*  90 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*  93 */           iVal = Integer.parseInt((String)value);
/*     */         } 
/*     */ 
/*     */         
/*  97 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/*  99 */             ((MQConnectionFactory)obj).setClientReconnectTimeout(iVal);
/*     */           }
/* 101 */           catch (JMSException e) {
/* 102 */             if (Trace.isOn) {
/* 103 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 108 */             BAOException traceRet2 = new BAOException(4, "CRT", String.valueOf(iVal));
/* 109 */             if (Trace.isOn) {
/* 110 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 1);
/*     */             }
/*     */             
/* 113 */             throw traceRet2;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 118 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 119 */           String key = "JMSADM1016";
/* 120 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 121 */           JMSException traceRet3 = new JMSException(msg, key);
/* 122 */           if (Trace.isOn) {
/* 123 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)traceRet3, 2);
/*     */           }
/*     */           
/* 126 */           throw traceRet3;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 131 */     } catch (BAOException e) {
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 140 */       throw e;
/*     */     
/*     */     }
/* 143 */     catch (JMSException e) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 152 */       throw e;
/*     */     } 
/*     */     
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCRT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCRT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (obj instanceof MQConnectionFactory) {
/* 181 */       int mret = ((MQConnectionFactory)obj).getClientReconnectTimeout();
/* 182 */       sVal = String.valueOf(mret);
/*     */     }
/*     */     else {
/*     */       
/* 186 */       String detail = "object is an unexpected type";
/* 187 */       String key = "JMSADM1016";
/* 188 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 189 */       JMSException traceRet1 = new JMSException(msg, key);
/* 190 */       if (Trace.isOn) {
/* 191 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCRT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 194 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 198 */     if (sVal != null && !sVal.equals("")) {
/* 199 */       props.put("CLIENTRECONNECTTIMEOUT", sVal);
/*     */     }
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCRT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCRT", "longName()");
/*     */     }
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCRT", "longName()", "CLIENTRECONNECTTIMEOUT");
/*     */     }
/* 222 */     return "CLIENTRECONNECTTIMEOUT";
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
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCRT", "shortName()");
/*     */     }
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCRT", "shortName()", "CRT");
/*     */     }
/* 239 */     return "CRT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCRT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */