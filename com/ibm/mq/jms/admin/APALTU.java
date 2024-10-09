/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQDestination;
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
/*     */ public class APALTU
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APALTU.java";
/*     */   public static final String LONGNAME = "ALTERNATEUSERID";
/*     */   public static final String SHORTNAME = "ALTU";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jms.admin.APALTU", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APALTU.java");
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
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.jms.admin.APALTU", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  76 */       String value = (String)getProperty("ALTU", props);
/*     */       
/*  78 */       if (value != null)
/*     */       {
/*  80 */         if (obj instanceof MQDestination) {
/*  81 */           ((MQDestination)obj).setAlternateUserId(value);
/*     */         }
/*     */         else {
/*     */           
/*  85 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  86 */           String key = "JMSADM1016";
/*  87 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  88 */           JMSException iee = new JMSException(msg, key);
/*  89 */           if (Trace.isOn) {
/*  90 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APALTU", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/*  93 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*  97 */     } catch (JMSException e) {
/*  98 */       if (Trace.isOn) {
/*  99 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APALTU", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 102 */       if (Trace.isOn) {
/* 103 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APALTU", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 106 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APALTU", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.exit(this, "com.ibm.mq.jms.admin.APALTU", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.mq.jms.admin.APALTU", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 137 */       if (obj instanceof MQDestination) {
/* 138 */         sVal = ((MQDestination)obj).getAlternateUserId();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 143 */         String detail = "object is an unexpected type";
/* 144 */         String key = "JMSADM1016";
/* 145 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 146 */         JMSException iee = new JMSException(msg, key);
/* 147 */         if (Trace.isOn) {
/* 148 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APALTU", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 151 */         throw iee;
/*     */       } 
/*     */       
/* 154 */       if (sVal != null)
/*     */       {
/* 156 */         props.put("ALTERNATEUSERID", sVal);
/*     */       }
/*     */     } finally {
/*     */       
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APALTU", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.jms.admin.APALTU", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.jms.admin.APALTU", "longName()");
/*     */     }
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.jms.admin.APALTU", "longName()", "ALTERNATEUSERID");
/*     */     }
/* 184 */     return "ALTERNATEUSERID";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.jms.admin.APALTU", "shortName()");
/*     */     }
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.jms.admin.APALTU", "shortName()", "ALTU");
/*     */     }
/* 198 */     return "ALTU";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APALTU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */