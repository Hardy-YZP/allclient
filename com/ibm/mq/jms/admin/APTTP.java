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
/*     */ public class APTTP
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTTP.java";
/*     */   public static final String LONGNAME = "TEMPTOPICPREFIX";
/*     */   public static final String SHORTNAME = "TTP";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APTTP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTTP.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTTP", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  83 */     String value = (String)getProperty("TTP", props);
/*     */ 
/*     */     
/*     */     try {
/*  87 */       if (obj instanceof MQConnectionFactory) {
/*  88 */         ((MQConnectionFactory)obj).setTempTopicPrefix(value);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  93 */         String detail = "object supplied as an unexpected type " + obj.getClass();
/*  94 */         String key = "JMSADM1016";
/*  95 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  96 */         JMSException iee = new JMSException(msg, key);
/*  97 */         if (Trace.isOn) {
/*  98 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTTP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */         }
/*     */         
/* 101 */         throw iee;
/*     */       }
/*     */     
/*     */     }
/* 105 */     catch (JMSException e) {
/* 106 */       if (Trace.isOn) {
/* 107 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTTP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 110 */       BAOException traceRet1 = new BAOException(4, "TTP", value);
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTTP", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 2);
/*     */       }
/*     */       
/* 115 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTTP", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */     String sVal;
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTTP", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (obj instanceof MQConnectionFactory) {
/* 144 */       sVal = ((MQConnectionFactory)obj).getTempTopicPrefix();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 149 */       String detail = "object is an unexpected type";
/* 150 */       String key = "JMSADM1016";
/* 151 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 152 */       JMSException iee = new JMSException(msg, key);
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTTP", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 157 */       throw iee;
/*     */     } 
/*     */     
/* 160 */     if (sVal != null)
/*     */     {
/* 162 */       props.put("TEMPTOPICPREFIX", sVal);
/*     */     }
/*     */     
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTTP", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTTP", "longName()");
/*     */     }
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTTP", "longName()", "TEMPTOPICPREFIX");
/*     */     }
/* 185 */     return "TEMPTOPICPREFIX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTTP", "shortName()");
/*     */     }
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTTP", "shortName()", "TTP");
/*     */     }
/* 199 */     return "TTP";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTTP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */