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
/*     */ public class APBCON
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APBCON.java";
/*     */   public static final String LONGNAME = "BROKERCONQ";
/*     */   public static final String SHORTNAME = "BCON";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APBCON", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APBCON.java");
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
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBCON", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("BCON", props);
/*     */       
/*  87 */       if (value != null)
/*     */       {
/*     */         
/*  90 */         if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/*  91 */           ((MQConnectionFactory)obj).setBrokerControlQueue(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  96 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  97 */           String key = "JMSADM1016";
/*  98 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  99 */           JMSException iee = new JMSException(msg, key);
/* 100 */           if (Trace.isOn) {
/* 101 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APBCON", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 104 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 109 */     catch (JMSException e) {
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APBCON", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 114 */       if (Trace.isOn) {
/* 115 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APBCON", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 118 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APBCON", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBCON", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBCON", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 150 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 151 */         sVal = ((MQConnectionFactory)obj).getBrokerControlQueue();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 156 */         String detail = "object is an unexpected type";
/* 157 */         String key = "JMSADM1016";
/* 158 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 159 */         JMSException iee = new JMSException(msg, key);
/* 160 */         if (Trace.isOn) {
/* 161 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APBCON", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 164 */         throw iee;
/*     */       } 
/*     */       
/* 167 */       if (sVal != null)
/*     */       {
/* 169 */         props.put("BROKERCONQ", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APBCON", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBCON", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBCON", "longName()");
/*     */     }
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBCON", "longName()", "BROKERCONQ");
/*     */     }
/* 200 */     return "BROKERCONQ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBCON", "shortName()");
/*     */     }
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBCON", "shortName()", "BCON");
/*     */     }
/* 214 */     return "BCON";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APBCON.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */