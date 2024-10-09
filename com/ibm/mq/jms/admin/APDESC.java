/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APDESC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APDESC.java";
/*     */   public static final String LONGNAME = "DESCRIPTION";
/*     */   public static final String SHORTNAME = "DESC";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APDESC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APDESC.java");
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
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDESC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("DESC", props);
/*     */       
/*  87 */       if (value != null)
/*     */       {
/*  89 */         if (obj instanceof MQDestination) {
/*  90 */           ((MQDestination)obj).setDescription(value);
/*     */         
/*     */         }
/*  93 */         else if (obj instanceof MQConnectionFactory) {
/*  94 */           ((MQConnectionFactory)obj).setDescription(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  99 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 100 */           String key = "JMSADM1016";
/* 101 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 102 */           JMSException iee = new JMSException(msg, key);
/* 103 */           if (Trace.isOn) {
/* 104 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APDESC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 107 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 112 */     catch (JMSException e) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APDESC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APDESC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 121 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APDESC", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDESC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDESC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 154 */       if (obj instanceof MQDestination) {
/* 155 */         sVal = ((MQDestination)obj).getDescription();
/*     */       
/*     */       }
/* 158 */       else if (obj instanceof MQConnectionFactory) {
/* 159 */         sVal = ((MQConnectionFactory)obj).getDescription();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 164 */         String detail = "object is an unexpected type";
/* 165 */         String key = "JMSADM1016";
/* 166 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 167 */         JMSException iee = new JMSException(msg, key);
/* 168 */         if (Trace.isOn) {
/* 169 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APDESC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 172 */         throw iee;
/*     */       } 
/*     */       
/* 175 */       if (sVal != null)
/*     */       {
/* 177 */         props.put("DESCRIPTION", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APDESC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDESC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDESC", "longName()");
/*     */     }
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDESC", "longName()", "DESCRIPTION");
/*     */     }
/* 208 */     return "DESCRIPTION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDESC", "shortName()");
/*     */     }
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDESC", "shortName()", "DESC");
/*     */     }
/* 222 */     return "DESC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APDESC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */