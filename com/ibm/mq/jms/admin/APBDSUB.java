/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQTopic;
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
/*     */ public class APBDSUB
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APBDSUB.java";
/*     */   public static final String LONGNAME = "BROKERDURSUBQ";
/*     */   public static final String SHORTNAME = "BDSUB";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APBDSUB", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APBDSUB.java");
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
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBDSUB", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  87 */       String value = (String)getProperty("BDSUB", props);
/*  88 */       if (value != null)
/*     */       {
/*  90 */         if (obj instanceof MQTopic) {
/*  91 */           ((MQTopic)obj).setBrokerDurSubQueue(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  96 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  97 */           String key = "JMSADM1016";
/*  98 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  99 */           JMSException iee = new JMSException(msg, key);
/* 100 */           if (Trace.isOn) {
/* 101 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APBDSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 104 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 109 */     catch (JMSException e) {
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APBDSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 114 */       if (Trace.isOn) {
/* 115 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APBDSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 118 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APBDSUB", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBDSUB", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 143 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBDSUB", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 150 */       if (obj instanceof MQTopic) {
/* 151 */         sVal = ((MQTopic)obj).getBrokerDurSubQueue();
/*     */       }
/*     */       else {
/*     */         
/* 155 */         String detail = "object is an unexpected type";
/* 156 */         String key = "JMSADM1016";
/* 157 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 158 */         JMSException iee = new JMSException(msg, key);
/* 159 */         if (Trace.isOn) {
/* 160 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APBDSUB", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 163 */         throw iee;
/*     */       } 
/*     */       
/* 166 */       if (sVal != null)
/*     */       {
/* 168 */         props.put("BROKERDURSUBQ", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APBDSUB", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBDSUB", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBDSUB", "longName()");
/*     */     }
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBDSUB", "longName()", "BROKERDURSUBQ");
/*     */     }
/* 199 */     return "BROKERDURSUBQ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.jms.admin.APBDSUB", "shortName()");
/*     */     }
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.jms.admin.APBDSUB", "shortName()", "BDSUB");
/*     */     }
/* 213 */     return "BDSUB";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APBDSUB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */