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
/*     */ public class APNTFY
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APNTFY.java";
/*     */   public static final String LONGNAME = "OUTCOMENOTIFICATION";
/*     */   public static final String SHORTNAME = "NOTIFY";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jms.admin.APNTFY", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APNTFY.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  74 */       Object value = getProperty("NOTIFY", props);
/*     */ 
/*     */       
/*  77 */       if (value != null) {
/*     */         boolean bVal;
/*  79 */         if (value instanceof Integer) {
/*  80 */           int intVal = ((Integer)value).intValue();
/*  81 */           bVal = (intVal == 1);
/*  82 */         } else if (value instanceof Boolean) {
/*  83 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/*     */           
/*  86 */           String str = ((String)value).toUpperCase();
/*  87 */           if (str.equals("NO")) {
/*  88 */             bVal = false;
/*  89 */           } else if (str.equals("YES")) {
/*  90 */             bVal = true;
/*     */           } else {
/*  92 */             BAOException traceRet1 = new BAOException(4, "NOTIFY", str);
/*  93 */             if (Trace.isOn) {
/*  94 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/*  97 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */         
/* 101 */         if (obj instanceof MQConnectionFactory) {
/* 102 */           ((MQConnectionFactory)obj).setOutcomeNotification(bVal);
/*     */         }
/*     */         else {
/*     */           
/* 106 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 107 */           String key = "JMSADM1016";
/* 108 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 109 */           JMSException iee = new JMSException(msg, key);
/* 110 */           if (Trace.isOn) {
/* 111 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 114 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 119 */     } catch (JMSException e) {
/* 120 */       if (Trace.isOn) {
/* 121 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 128 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.mq.jms.admin.APNTFY", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.jms.admin.APNTFY", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 157 */       if (obj instanceof MQConnectionFactory) {
/* 158 */         boolean b = ((MQConnectionFactory)obj).getOutcomeNotification();
/* 159 */         if (b) {
/* 160 */           sVal = "YES";
/*     */         } else {
/* 162 */           sVal = "NO";
/*     */         } 
/*     */       } else {
/*     */         
/* 166 */         String detail = "object is an unexpected type";
/* 167 */         String key = "JMSADM1016";
/* 168 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 169 */         JMSException iee = new JMSException(msg, key);
/* 170 */         if (Trace.isOn) {
/* 171 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APNTFY", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 174 */         throw iee;
/*     */       } 
/*     */       
/* 177 */       if (sVal != null)
/*     */       {
/* 179 */         props.put("OUTCOMENOTIFICATION", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APNTFY", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.mq.jms.admin.APNTFY", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.admin.APNTFY", "longName()");
/*     */     }
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jms.admin.APNTFY", "longName()", "OUTCOMENOTIFICATION");
/*     */     }
/* 208 */     return "OUTCOMENOTIFICATION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.jms.admin.APNTFY", "shortName()");
/*     */     }
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.exit(this, "com.ibm.mq.jms.admin.APNTFY", "shortName()", "NOTIFY");
/*     */     }
/* 222 */     return "NOTIFY";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APNTFY.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */