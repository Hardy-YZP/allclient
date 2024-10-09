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
/*     */ public class APSCX
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCX.java";
/*     */   public static final String LONGNAME = "SECEXIT";
/*     */   public static final String SHORTNAME = "SCX";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APSCX", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCX.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCX", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       String value = (String)getProperty("SCX", props);
/*  85 */       if (value != null)
/*     */       {
/*  87 */         if (obj instanceof MQConnectionFactory) {
/*  88 */           ((MQConnectionFactory)obj).setSecurityExit(value);
/*     */         }
/*     */         else {
/*     */           
/*  92 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  93 */           String key = "JMSADM1016";
/*  94 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  95 */           JMSException iee = new JMSException(msg, key);
/*  96 */           if (Trace.isOn) {
/*  97 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSCX", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 100 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 105 */     catch (JMSException e) {
/* 106 */       if (Trace.isOn) {
/* 107 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSCX", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSCX", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 114 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 118 */       if (Trace.isOn) {
/* 119 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCX", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCX", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCX", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 146 */       if (obj instanceof MQConnectionFactory) {
/* 147 */         sVal = ((MQConnectionFactory)obj).getSecurityExit();
/*     */       }
/*     */       else {
/*     */         
/* 151 */         String detail = "object is an unexpected type";
/* 152 */         String key = "JMSADM1016";
/* 153 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 154 */         JMSException iee = new JMSException(msg, key);
/* 155 */         if (Trace.isOn) {
/* 156 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSCX", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 159 */         throw iee;
/*     */       } 
/*     */       
/* 162 */       if (sVal != null)
/*     */       {
/* 164 */         props.put("SECEXIT", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCX", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCX", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCX", "longName()");
/*     */     }
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCX", "longName()", "SECEXIT");
/*     */     }
/* 195 */     return "SECEXIT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCX", "shortName()");
/*     */     }
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCX", "shortName()", "SCX");
/*     */     }
/* 209 */     return "SCX";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSCX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */