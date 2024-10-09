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
/*     */ public class APTOP
/*     */   extends AP
/*     */ {
/*     */   public static final String LONGNAME = "TOPIC";
/*     */   public static final String SHORTNAME = "TOP";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APTOP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTOP.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTOP", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  81 */       String value = (String)getProperty("TOP", props);
/*  82 */       if (value != null)
/*     */       {
/*  84 */         if (obj instanceof MQTopic) {
/*  85 */           ((MQTopic)obj).setBaseTopicName(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  90 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  91 */           String key = "JMSADM1016";
/*  92 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  93 */           JMSException iee = new JMSException(msg, key);
/*  94 */           if (Trace.isOn) {
/*  95 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APTOP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/*  98 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 103 */     catch (JMSException e) {
/* 104 */       if (Trace.isOn) {
/* 105 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTOP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 108 */       if (Trace.isOn) {
/* 109 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTOP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 112 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 116 */       if (Trace.isOn) {
/* 117 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTOP", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTOP", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTOP", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 144 */       if (obj instanceof MQTopic) {
/* 145 */         sVal = ((MQTopic)obj).getBaseTopicName();
/*     */       }
/*     */       else {
/*     */         
/* 149 */         String detail = "object is an unexpected type";
/* 150 */         String key = "JMSADM1016";
/* 151 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 152 */         JMSException iee = new JMSException(msg, key);
/* 153 */         if (Trace.isOn) {
/* 154 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTOP", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 157 */         throw iee;
/*     */       } 
/*     */       
/* 160 */       if (sVal != null)
/*     */       {
/* 162 */         props.put("TOPIC", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTOP", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTOP", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTOP", "longName()");
/*     */     }
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTOP", "longName()", "TOPIC");
/*     */     }
/* 193 */     return "TOPIC";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTOP", "shortName()");
/*     */     }
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTOP", "shortName()", "TOP");
/*     */     }
/* 207 */     return "TOP";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTOP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */