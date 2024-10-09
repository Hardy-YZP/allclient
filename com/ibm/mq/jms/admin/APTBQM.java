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
/*     */ public class APTBQM
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTBQM.java";
/*     */   public static final String LONGNAME = "BROKERPUBQMGR";
/*     */   public static final String SHORTNAME = "BPQM";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APTBQM", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTBQM.java");
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
/*     */   public String longName() {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTBQM", "longName()");
/*     */     }
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTBQM", "longName()", "BROKERPUBQMGR");
/*     */     }
/*  80 */     return "BROKERPUBQMGR";
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTBQM", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 102 */       String value = (String)getProperty("BPQM", props);
/* 103 */       if (value != null)
/*     */       {
/* 105 */         if (obj instanceof MQTopic) {
/* 106 */           ((MQTopic)obj).setBrokerPubQueueManager(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 111 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 112 */           String key = "JMSADM1016";
/* 113 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 114 */           JMSException iee = new JMSException(msg, key);
/* 115 */           if (Trace.isOn) {
/* 116 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APTBQM", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 119 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 124 */     catch (JMSException e) {
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTBQM", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTBQM", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 133 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTBQM", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTBQM", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTBQM", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 165 */       if (obj instanceof MQTopic) {
/* 166 */         sVal = ((MQTopic)obj).getBrokerPubQueueManager();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 171 */         String detail = "object is an unexpected type";
/* 172 */         String key = "JMSADM1016";
/* 173 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 174 */         JMSException iee = new JMSException(msg, key);
/* 175 */         if (Trace.isOn) {
/* 176 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTBQM", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 179 */         throw iee;
/*     */       } 
/*     */       
/* 182 */       if (sVal != null)
/*     */       {
/* 184 */         props.put("BROKERPUBQMGR", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTBQM", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTBQM", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTBQM", "shortName()");
/*     */     }
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTBQM", "shortName()", "BPQM");
/*     */     }
/* 213 */     return "BPQM";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTBQM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */