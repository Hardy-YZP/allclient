/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiUtils;
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
/*     */ public class APSCPHS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCPHS.java";
/*     */   public static final String LONGNAME = "SSLCIPHERSUITE";
/*     */   public static final String SHORTNAME = "SCPHS";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APSCPHS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCPHS.java");
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
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCPHS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("SCPHS", props);
/*  86 */       String sVal = null;
/*  87 */       if (value != null)
/*     */       {
/*  89 */         String cipherSuite = JmqiUtils.toCipherSuite(value, false);
/*  90 */         if (cipherSuite == null) {
/*  91 */           cipherSuite = JmqiUtils.toCipherSuite(value, true);
/*     */         }
/*  93 */         if (cipherSuite != null) {
/*  94 */           sVal = cipherSuite;
/*     */         }
/*     */         else {
/*     */           
/*  98 */           sVal = value;
/*     */         } 
/*     */ 
/*     */         
/* 102 */         if (obj instanceof MQConnectionFactory) {
/* 103 */           ((MQConnectionFactory)obj).setSSLCipherSuite(sVal);
/*     */         }
/*     */         else {
/*     */           
/* 107 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 108 */           String key = "JMSADM1016";
/* 109 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 110 */           JMSException iee = new JMSException(msg, key);
/* 111 */           if (Trace.isOn) {
/* 112 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSCPHS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 115 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 120 */     } catch (JMSException e) {
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSCPHS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSCPHS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 129 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCPHS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCPHS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCPHS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 161 */       if (obj instanceof MQConnectionFactory) {
/* 162 */         sVal = ((MQConnectionFactory)obj).getSSLCipherSuite();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 167 */         String detail = "object is an unexpected type";
/* 168 */         String key = "JMSADM1016";
/* 169 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 170 */         JMSException iee = new JMSException(msg, key);
/* 171 */         if (Trace.isOn) {
/* 172 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSCPHS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 175 */         throw iee;
/*     */       } 
/*     */       
/* 178 */       if (sVal != null)
/*     */       {
/* 180 */         props.put("SSLCIPHERSUITE", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 185 */       if (Trace.isOn) {
/* 186 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCPHS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCPHS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCPHS", "longName()");
/*     */     }
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCPHS", "longName()", "SSLCIPHERSUITE");
/*     */     }
/* 211 */     return "SSLCIPHERSUITE";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCPHS", "shortName()");
/*     */     }
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCPHS", "shortName()", "SCPHS");
/*     */     }
/* 225 */     return "SCPHS";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSCPHS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */