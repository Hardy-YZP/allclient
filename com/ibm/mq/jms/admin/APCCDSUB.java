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
/*     */ public class APCCDSUB
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCDSUB.java";
/*     */   public static final String LONGNAME = "BROKERCCDURSUBQ";
/*     */   public static final String SHORTNAME = "CCDSUB";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APCCDSUB", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCDSUB.java");
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
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDSUB", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  86 */       String value = (String)getProperty("CCDSUB", props);
/*     */       
/*  88 */       if (value != null)
/*     */       {
/*     */         
/*  91 */         if (obj instanceof MQTopic) {
/*  92 */           ((MQTopic)obj).setBrokerCCDurSubQueue(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  97 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  98 */           String key = "JMSADM1016";
/*  99 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 100 */           JMSException iee = new JMSException(msg, key);
/* 101 */           if (Trace.isOn) {
/* 102 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 105 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 110 */     catch (JMSException e) {
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCDSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 115 */       if (Trace.isOn) {
/* 116 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDSUB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 119 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCDSUB", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDSUB", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDSUB", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 151 */       if (obj instanceof MQTopic) {
/* 152 */         sVal = ((MQTopic)obj).getBrokerCCDurSubQueue();
/*     */       }
/*     */       else {
/*     */         
/* 156 */         String detail = "object is an unexpected type";
/* 157 */         String key = "JMSADM1016";
/* 158 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 159 */         JMSException iee = new JMSException(msg, key);
/* 160 */         if (Trace.isOn) {
/* 161 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDSUB", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 164 */         throw iee;
/*     */       } 
/*     */       
/* 167 */       if (sVal != null)
/*     */       {
/* 169 */         props.put("BROKERCCDURSUBQ", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCDSUB", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDSUB", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 195 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDSUB", "longName()");
/*     */     }
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDSUB", "longName()", "BROKERCCDURSUBQ");
/*     */     }
/* 200 */     return "BROKERCCDURSUBQ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDSUB", "shortName()");
/*     */     }
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDSUB", "shortName()", "CCDSUB");
/*     */     }
/* 214 */     return "CCDSUB";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCCDSUB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */