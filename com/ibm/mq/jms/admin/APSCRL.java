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
/*     */ public class APSCRL
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCRL.java";
/*     */   public static final String LONGNAME = "SSLCRL";
/*     */   public static final String SHORTNAME = "SCRL";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APSCRL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCRL.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCRL", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       String value = (String)getProperty("SCRL", props);
/*     */       
/*  86 */       if (value != null)
/*     */       {
/*  88 */         if (obj instanceof MQConnectionFactory) {
/*  89 */           ((MQConnectionFactory)obj).setSSLCertStores(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  94 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  95 */           String key = "JMSADM1016";
/*  96 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  97 */           JMSException iee = new JMSException(msg, key);
/*  98 */           if (Trace.isOn) {
/*  99 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSCRL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 102 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 107 */     catch (JMSException e) {
/* 108 */       if (Trace.isOn) {
/* 109 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSCRL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSCRL", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 116 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 120 */       if (Trace.isOn) {
/* 121 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCRL", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCRL", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCRL", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 148 */       if (obj instanceof MQConnectionFactory) {
/* 149 */         sVal = ((MQConnectionFactory)obj).getSSLCertStoresAsString();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 154 */         String detail = "object is an unexpected type";
/* 155 */         String key = "JMSADM1016";
/* 156 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 157 */         JMSException iee = new JMSException(msg, key);
/* 158 */         if (Trace.isOn) {
/* 159 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSCRL", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 162 */         throw iee;
/*     */       } 
/*     */       
/* 165 */       if (sVal != null)
/*     */       {
/* 167 */         props.put("SSLCRL", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCRL", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCRL", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCRL", "longName()");
/*     */     }
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCRL", "longName()", "SSLCRL");
/*     */     }
/* 198 */     return "SSLCRL";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCRL", "shortName()");
/*     */     }
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCRL", "shortName()", "SCRL");
/*     */     }
/* 212 */     return "SCRL";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSCRL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */