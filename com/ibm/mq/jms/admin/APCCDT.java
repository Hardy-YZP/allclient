/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
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
/*     */ public class APCCDT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCDT.java";
/*     */   public static final String LONGNAME = "CCDTURL";
/*     */   public static final String SHORTNAME = "CCDT";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APCCDT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCDT.java");
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
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  86 */       String value = (String)getProperty("CCDT", props);
/*     */       
/*  88 */       if (value != null) {
/*  89 */         URL url = null;
/*     */         try {
/*  91 */           url = new URL(value);
/*     */         }
/*  93 */         catch (MalformedURLException mue) {
/*  94 */           if (Trace.isOn) {
/*  95 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)", mue, 1);
/*     */           }
/*     */           
/*  98 */           String detail = "invalid URL supplied";
/*  99 */           String key = "JMSADM1016";
/* 100 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 101 */           JMSException iee = new JMSException(msg, key);
/* 102 */           if (Trace.isOn) {
/* 103 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 106 */           throw iee;
/*     */         } 
/*     */ 
/*     */         
/* 110 */         if (obj instanceof MQConnectionFactory) {
/* 111 */           ((MQConnectionFactory)obj).setCCDTURL(url);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 116 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 117 */           String key = "JMSADM1016";
/* 118 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 119 */           JMSException iee = new JMSException(msg, key);
/* 120 */           if (Trace.isOn) {
/* 121 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 124 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 129 */     } catch (JMSException e) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 138 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 169 */       String sVal = null;
/*     */       
/* 171 */       if (obj instanceof MQConnectionFactory) {
/* 172 */         URL url = ((MQConnectionFactory)obj).getCCDTURL();
/* 173 */         if (url != null) {
/* 174 */           sVal = url.toString();
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 179 */         String detail = "object is an unexpected type";
/* 180 */         String key = "JMSADM1016";
/* 181 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */         JMSException iee = new JMSException(msg, key);
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCCDT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 187 */         throw iee;
/*     */       } 
/*     */       
/* 190 */       if (sVal != null)
/*     */       {
/* 192 */         props.put("CCDTURL", sVal);
/*     */       }
/*     */     } finally {
/*     */       
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCDT", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDT", "longName()");
/*     */     }
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDT", "longName()", "CCDTURL");
/*     */     }
/* 221 */     return "CCDTURL";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCDT", "shortName()");
/*     */     }
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCDT", "shortName()", "CCDT");
/*     */     }
/* 235 */     return "CCDT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCCDT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */