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
/*     */ public class APCCSUB
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCSUB.java";
/*     */   public static final String LONGNAME = "BROKERCCSUBQ";
/*     */   public static final String SHORTNAME = "CCSUB";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APCCSUB", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCSUB.java");
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
/*  81 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCSUB", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  87 */       String value = (String)getProperty("CCSUB", props);
/*     */       
/*  89 */       if (value != null)
/*     */       {
/*     */         
/*  92 */         if (obj instanceof MQConnectionFactory) {
/*  93 */           ((MQConnectionFactory)obj).setBrokerCCSubQueue(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  98 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  99 */           String key = "JMSADM1016";
/* 100 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 101 */           JMSException iee = new JMSException(msg, key);
/* 102 */           if (Trace.isOn) {
/* 103 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCCSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 106 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 111 */     catch (JMSException e) {
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 116 */       if (Trace.isOn) {
/* 117 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCCSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 120 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCSUB", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCSUB", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCSUB", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 152 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 153 */         sVal = ((MQConnectionFactory)obj).getBrokerCCSubQueue();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 158 */         String detail = "object is an unexpected type";
/* 159 */         String key = "JMSADM1016";
/* 160 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 161 */         JMSException iee = new JMSException(msg, key);
/* 162 */         if (Trace.isOn) {
/* 163 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCCSUB", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 166 */         throw iee;
/*     */       } 
/*     */       
/* 169 */       if (sVal != null)
/*     */       {
/* 171 */         props.put("BROKERCCSUBQ", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCSUB", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCSUB", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCSUB", "longName()");
/*     */     }
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCSUB", "longName()", "BROKERCCSUBQ");
/*     */     }
/* 202 */     return "BROKERCCSUBQ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCSUB", "shortName()");
/*     */     }
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCSUB", "shortName()", "CCSUB");
/*     */     }
/* 216 */     return "CCSUB";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCCSUB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */