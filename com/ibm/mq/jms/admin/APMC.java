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
/*     */ public class APMC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMC.java";
/*     */   public static final String LONGNAME = "COMPMSG";
/*     */   public static final String SHORTNAME = "MC";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APMC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMC.java");
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
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("MC", props);
/*  86 */       if (value != null)
/*     */       {
/*  88 */         if (obj instanceof MQConnectionFactory) {
/*  89 */           ((MQConnectionFactory)obj).setMsgCompList(value);
/*     */         }
/*     */         else {
/*     */           
/*  93 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  94 */           String key = "JMSADM1016";
/*  95 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  96 */           JMSException iee = new JMSException(msg, key);
/*  97 */           if (Trace.isOn) {
/*  98 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APMC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 101 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 106 */     catch (JMSException e) {
/* 107 */       if (Trace.isOn) {
/* 108 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 115 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMC", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/*     */     try {
/* 145 */       String sVal = null;
/*     */       
/* 147 */       if (obj instanceof MQConnectionFactory) {
/* 148 */         sVal = ((MQConnectionFactory)obj).getMsgCompListAsString();
/*     */       }
/*     */       else {
/*     */         
/* 152 */         String detail = "object is an unexpected type";
/* 153 */         String key = "JMSADM1016";
/* 154 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 155 */         JMSException iee = new JMSException(msg, key);
/* 156 */         if (Trace.isOn) {
/* 157 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APMC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 160 */         throw iee;
/*     */       } 
/*     */       
/* 163 */       if (sVal != null)
/*     */       {
/* 165 */         props.put("COMPMSG", sVal);
/*     */       }
/*     */     } finally {
/*     */       
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String longName() {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMC", "longName()");
/*     */     }
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMC", "longName()", "COMPMSG");
/*     */     }
/* 193 */     return "COMPMSG";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMC", "shortName()");
/*     */     }
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMC", "shortName()", "MC");
/*     */     }
/* 207 */     return "MC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */