/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQQueue;
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
/*     */ public class APQMGR
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APQMGR.java";
/*     */   public static final String LONGNAME = "QMANAGER";
/*     */   public static final String SHORTNAME = "QMGR";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APQMGR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APQMGR.java");
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
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APQMGR", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("QMGR", props);
/*     */       
/*  87 */       if (value != null)
/*     */       {
/*  89 */         if (obj instanceof MQQueue) {
/*  90 */           ((MQQueue)obj).setBaseQueueManagerName(value);
/*     */         
/*     */         }
/*  93 */         else if (obj instanceof MQConnectionFactory) {
/*  94 */           ((MQConnectionFactory)obj).setQueueManager(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  99 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 100 */           String key = "JMSADM1016";
/* 101 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 102 */           JMSException iee = new JMSException(msg, key);
/* 103 */           if (Trace.isOn) {
/* 104 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APQMGR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 107 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 112 */     catch (JMSException e) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APQMGR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APQMGR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 121 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APQMGR", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.jms.admin.APQMGR", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.mq.jms.admin.APQMGR", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 153 */       if (obj instanceof MQQueue) {
/* 154 */         sVal = ((MQQueue)obj).getBaseQueueManagerName();
/*     */       
/*     */       }
/* 157 */       else if (obj instanceof MQConnectionFactory) {
/* 158 */         sVal = ((MQConnectionFactory)obj).getQueueManager();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 163 */         String detail = "object is an unexpected type";
/* 164 */         String key = "JMSADM1016";
/* 165 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 166 */         JMSException iee = new JMSException(msg, key);
/* 167 */         if (Trace.isOn) {
/* 168 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APQMGR", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 171 */         throw iee;
/*     */       } 
/*     */       
/* 174 */       if (sVal != null)
/*     */       {
/* 176 */         props.put("QMANAGER", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APQMGR", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.mq.jms.admin.APQMGR", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.mq.jms.admin.APQMGR", "longName()");
/*     */     }
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.jms.admin.APQMGR", "longName()", "QMANAGER");
/*     */     }
/* 207 */     return "QMANAGER";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.mq.jms.admin.APQMGR", "shortName()");
/*     */     }
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.jms.admin.APQMGR", "shortName()", "QMGR");
/*     */     }
/* 221 */     return "QMGR";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APQMGR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */