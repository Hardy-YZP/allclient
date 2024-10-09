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
/*     */ public class APPVER
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPVER.java";
/*     */   public static final String LONGNAME = "PROVIDERVERSION";
/*     */   public static final String SHORTNAME = "PVER";
/*     */   public static final String UNSPECIFIED = "UNSPECIFIED";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APPVER", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPVER.java");
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
/*     */ 
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPVER", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  86 */     String value = (String)getProperty("PVER", props);
/*     */ 
/*     */     
/*     */     try {
/*  90 */       if (value != null)
/*     */       {
/*     */         
/*  93 */         if (obj instanceof MQConnectionFactory) {
/*  94 */           ((MQConnectionFactory)obj).setProviderVersion(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  99 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 100 */           String key = "JMSADM1016";
/* 101 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 102 */           JMSException iee = new JMSException(msg, key);
/* 103 */           if (Trace.isOn) {
/* 104 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 107 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 112 */     catch (JMSException e) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPVER", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 117 */       BAOException traceRet1 = new BAOException(4, "PVER", value);
/* 118 */       if (Trace.isOn) {
/* 119 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPVER", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 2);
/*     */       }
/*     */       
/* 122 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPVER", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPVER", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (obj instanceof MQConnectionFactory) {
/* 151 */       sVal = ((MQConnectionFactory)obj).getProviderVersion();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 156 */       String detail = "object is an unexpected type";
/* 157 */       String key = "JMSADM1016";
/* 158 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 159 */       JMSException iee = new JMSException(msg, key);
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPVER", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 164 */       throw iee;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     if (sVal.equalsIgnoreCase("unspecified")) {
/* 171 */       sVal = "UNSPECIFIED";
/*     */     }
/*     */     
/* 174 */     if (sVal != null)
/*     */     {
/* 176 */       props.put("PROVIDERVERSION", sVal);
/*     */     }
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPVER", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPVER", "longName()");
/*     */     }
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPVER", "longName()", "PROVIDERVERSION");
/*     */     }
/* 199 */     return "PROVIDERVERSION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPVER", "shortName()");
/*     */     }
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPVER", "shortName()", "PVER");
/*     */     }
/* 213 */     return "PVER";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPVER.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */