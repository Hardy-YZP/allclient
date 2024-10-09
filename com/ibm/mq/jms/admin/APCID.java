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
/*     */ public class APCID
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCID.java";
/*     */   public static final String LONGNAME = "CLIENTID";
/*     */   public static final String SHORTNAME = "CID";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APCID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCID.java");
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
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCID", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  81 */       String value = (String)getProperty("CID", props);
/*     */       
/*  83 */       if (value != null)
/*     */       {
/*  85 */         if (obj instanceof MQConnectionFactory) {
/*  86 */           ((MQConnectionFactory)obj).setClientID(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  91 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  92 */           String key = "JMSADM1016";
/*  93 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  94 */           JMSException iee = new JMSException(msg, key);
/*  95 */           if (Trace.isOn) {
/*  96 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCID", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/*  99 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 104 */     catch (JMSException e) {
/* 105 */       if (Trace.isOn) {
/* 106 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCID", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 109 */       if (Trace.isOn) {
/* 110 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCID", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 113 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCID", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCID", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCID", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 145 */       if (obj instanceof MQConnectionFactory) {
/* 146 */         sVal = ((MQConnectionFactory)obj).getClientID();
/*     */       }
/*     */       else {
/*     */         
/* 150 */         String detail = "object is an unexpected type";
/* 151 */         String key = "JMSADM1016";
/* 152 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 153 */         JMSException iee = new JMSException(msg, key);
/* 154 */         if (Trace.isOn) {
/* 155 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCID", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 158 */         throw iee;
/*     */       } 
/*     */       
/* 161 */       if (sVal != null)
/*     */       {
/* 163 */         props.put("CLIENTID", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCID", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCID", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCID", "longName()");
/*     */     }
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCID", "longName()", "CLIENTID");
/*     */     }
/* 194 */     return "CLIENTID";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCID", "shortName()");
/*     */     }
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCID", "shortName()", "CID");
/*     */     }
/* 208 */     return "CID";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */