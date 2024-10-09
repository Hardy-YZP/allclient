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
/*     */ public class APTQP
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTQP.java";
/*     */   public static final String LONGNAME = "TEMPQPREFIX";
/*     */   public static final String SHORTNAME = "TQP";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jms.admin.APTQP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTQP.java");
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
/*     */   public String longName() {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTQP", "longName()");
/*     */     }
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTQP", "longName()", "TEMPQPREFIX");
/*     */     }
/*  66 */     return "TEMPQPREFIX";
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws JMSException, BAOException {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTQP", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("TQP", props);
/*  86 */       if (value != null)
/*     */       {
/*  88 */         if (obj instanceof MQConnectionFactory) {
/*  89 */           ((MQConnectionFactory)obj).setTempQPrefix(value);
/*     */         }
/*     */         else {
/*     */           
/*  93 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  94 */           String key = "JMSADM1016";
/*  95 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  96 */           JMSException iee = new JMSException(msg, key);
/*  97 */           if (Trace.isOn) {
/*  98 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APTQP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 101 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 106 */     catch (JMSException e) {
/* 107 */       if (Trace.isOn) {
/* 108 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTQP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTQP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 115 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTQP", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTQP", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTQP", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 147 */       if (obj instanceof MQConnectionFactory) {
/* 148 */         sVal = ((MQConnectionFactory)obj).getTempQPrefix();
/*     */       }
/*     */       else {
/*     */         
/* 152 */         String detail = "object is an unexpected type";
/* 153 */         String key = "JMSADM1016";
/* 154 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 155 */         JMSException iee = new JMSException(msg, key);
/* 156 */         if (Trace.isOn) {
/* 157 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTQP", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 160 */         throw iee;
/*     */       } 
/*     */       
/* 163 */       if (sVal != null)
/*     */       {
/* 165 */         props.put("TEMPQPREFIX", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTQP", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTQP", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTQP", "shortName()");
/*     */     }
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTQP", "shortName()", "TQP");
/*     */     }
/* 194 */     return "TQP";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTQP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */