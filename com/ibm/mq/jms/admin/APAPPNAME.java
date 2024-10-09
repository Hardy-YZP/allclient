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
/*     */ public class APAPPNAME
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APAPPNAME.java, jmscc.admin, k000, k000-L111005.1  1.14 08/11/25 22:06:05";
/*     */   public static final String LONGNAME = "APPLICATIONNAME";
/*     */   public static final String SHORTNAME = "APPNAME";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APAPPNAME", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APAPPNAME.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       String value = (String)getProperty("APPNAME", props);
/*     */       
/*  86 */       if (value != null)
/*     */       {
/*  88 */         if (value.length() == 0) {
/*     */           
/*  90 */           String detail = "value supplied is too short";
/*  91 */           String key = "JMSADM1016";
/*  92 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  93 */           JMSException iee = new JMSException(msg, key);
/*  94 */           if (Trace.isOn) {
/*  95 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/*  98 */           throw iee;
/*     */         } 
/*     */         
/* 101 */         if (value.length() > 28) {
/*     */           
/* 103 */           String detail = "value supplied is too long";
/* 104 */           String key = "JMSADM1016";
/* 105 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 106 */           JMSException iee = new JMSException(msg, key);
/* 107 */           if (Trace.isOn) {
/* 108 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 111 */           throw iee;
/*     */         } 
/*     */         
/* 114 */         if (obj instanceof MQConnectionFactory) {
/* 115 */           ((MQConnectionFactory)obj).setAppName(value);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 120 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 121 */           String key = "JMSADM1016";
/* 122 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 123 */           JMSException iee = new JMSException(msg, key);
/* 124 */           if (Trace.isOn) {
/* 125 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 128 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 133 */     } catch (JMSException e) {
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 142 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAPPNAME", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAPPNAME", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 174 */       if (obj instanceof MQConnectionFactory) {
/* 175 */         sVal = ((MQConnectionFactory)obj).getAppName();
/*     */       }
/*     */       else {
/*     */         
/* 179 */         String detail = "object is an unexpected type";
/* 180 */         String key = "JMSADM1016";
/* 181 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */         JMSException iee = new JMSException(msg, key);
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APAPPNAME", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 187 */         throw iee;
/*     */       } 
/*     */       
/* 190 */       if (sVal != null)
/*     */       {
/* 192 */         props.put("APPLICATIONNAME", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APAPPNAME", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAPPNAME", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAPPNAME", "longName()");
/*     */     }
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAPPNAME", "longName()", "APPLICATIONNAME");
/*     */     }
/* 223 */     return "APPLICATIONNAME";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jms.admin.APAPPNAME", "shortName()");
/*     */     }
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APAPPNAME", "shortName()", "APPNAME");
/*     */     }
/* 237 */     return "APPNAME";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APAPPNAME.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */