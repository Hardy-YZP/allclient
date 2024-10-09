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
/*     */ public class APSPEER
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSPEER.java";
/*     */   public static final String LONGNAME = "SSLPEERNAME";
/*     */   public static final String SHORTNAME = "SPEER";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APSPEER", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSPEER.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPEER", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       String value = (String)getProperty("SPEER", props);
/*  85 */       if (value != null)
/*     */       {
/*  87 */         if (obj instanceof MQConnectionFactory) {
/*  88 */           ((MQConnectionFactory)obj).setSSLPeerName(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  93 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  94 */           String key = "JMSADM1016";
/*  95 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  96 */           JMSException iee = new JMSException(msg, key);
/*  97 */           if (Trace.isOn) {
/*  98 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSPEER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 101 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 106 */     catch (JMSException e) {
/* 107 */       if (Trace.isOn) {
/* 108 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSPEER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSPEER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 115 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSPEER", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPEER", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPEER", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 147 */       if (obj instanceof MQConnectionFactory) {
/* 148 */         sVal = ((MQConnectionFactory)obj).getSSLPeerName();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 153 */         String detail = "object is an unexpected type";
/* 154 */         String key = "JMSADM1016";
/* 155 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 156 */         JMSException iee = new JMSException(msg, key);
/* 157 */         if (Trace.isOn) {
/* 158 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSPEER", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 161 */         throw iee;
/*     */       } 
/*     */       
/* 164 */       if (sVal != null)
/*     */       {
/* 166 */         props.put("SSLPEERNAME", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSPEER", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPEER", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPEER", "longName()");
/*     */     }
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPEER", "longName()", "SSLPEERNAME");
/*     */     }
/* 197 */     return "SSLPEERNAME";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPEER", "shortName()");
/*     */     }
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPEER", "shortName()", "SPEER");
/*     */     }
/* 211 */     return "SPEER";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSPEER.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */