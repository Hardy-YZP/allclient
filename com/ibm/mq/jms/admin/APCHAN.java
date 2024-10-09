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
/*     */ public class APCHAN
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCHAN.java";
/*     */   public static final String LONGNAME = "CHANNEL";
/*     */   public static final String SHORTNAME = "CHAN";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APCHAN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCHAN.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCHAN", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       String value = (String)getProperty("CHAN", props);
/*     */       
/*  86 */       if (value != null)
/*     */       {
/*  88 */         if (obj instanceof MQConnectionFactory) {
/*  89 */           ((MQConnectionFactory)obj).setChannel(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  94 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  95 */           String key = "JMSADM1016";
/*  96 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  97 */           JMSException iee = new JMSException(msg, key);
/*  98 */           if (Trace.isOn) {
/*  99 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCHAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 102 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 107 */     catch (JMSException e) {
/* 108 */       if (Trace.isOn) {
/* 109 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCHAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCHAN", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 116 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 120 */       if (Trace.isOn) {
/* 121 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCHAN", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCHAN", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCHAN", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 148 */       if (obj instanceof MQConnectionFactory) {
/* 149 */         sVal = ((MQConnectionFactory)obj).getChannel();
/*     */       }
/*     */       else {
/*     */         
/* 153 */         String detail = "object is an unexpected type";
/* 154 */         String key = "JMSADM1016";
/* 155 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 156 */         JMSException iee = new JMSException(msg, key);
/* 157 */         if (Trace.isOn) {
/* 158 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCHAN", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 161 */         throw iee;
/*     */       } 
/*     */       
/* 164 */       if (sVal != null)
/*     */       {
/* 166 */         props.put("CHANNEL", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCHAN", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCHAN", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 192 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCHAN", "longName()");
/*     */     }
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCHAN", "longName()", "CHANNEL");
/*     */     }
/* 197 */     return "CHANNEL";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCHAN", "shortName()");
/*     */     }
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCHAN", "shortName()", "CHAN");
/*     */     }
/* 211 */     return "CHAN";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCHAN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */