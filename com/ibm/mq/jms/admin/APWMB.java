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
/*     */ public class APWMB
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMB.java";
/*     */   public static final String LONGNAME = "MSGBODY";
/*     */   public static final String SHORTNAME = "MBODY";
/*     */   public static final String JMS = "JMS";
/*     */   public static final String MQ = "MQ";
/*     */   public static final String UNSPECIFIED = "UNSPECIFIED";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jms.admin.APWMB", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMB.java");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMB", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*  79 */     Object value = getProperty("MBODY", props);
/*     */     try {
/*  81 */       int iVal = 0;
/*  82 */       if (value != null) {
/*  83 */         if (value instanceof Integer) {
/*  84 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*  87 */           String str = ((String)value).toUpperCase();
/*  88 */           if (str.equals("JMS")) {
/*  89 */             iVal = 0;
/*     */           }
/*  91 */           else if (str.equals("MQ")) {
/*  92 */             iVal = 1;
/*     */           }
/*  94 */           else if (str.equals("UNSPECIFIED")) {
/*  95 */             iVal = 2;
/*     */           } else {
/*     */             
/*  98 */             BAOException traceRet1 = new BAOException(4, "MBODY", str);
/*  99 */             if (Trace.isOn) {
/* 100 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APWMB", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 103 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 108 */         if (obj instanceof MQDestination) {
/* 109 */           ((MQDestination)obj).setMessageBodyStyle(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 113 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 114 */           String key = "JMSADM1016";
/* 115 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 116 */           JMSException iee = new JMSException(msg, key);
/* 117 */           if (Trace.isOn) {
/* 118 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWMB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 121 */           throw iee;
/*     */         }
/*     */       
/*     */       } 
/* 125 */     } catch (JMSException e) {
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APWMB", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 130 */       BAOException traceRet2 = new BAOException(4, "MBODY", value);
/* 131 */       if (Trace.isOn) {
/* 132 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMB", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 135 */       throw traceRet2;
/*     */     } 
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMB", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMB", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/* 161 */     String sVal = null;
/*     */     
/* 163 */     if (obj instanceof MQDestination) {
/* 164 */       int iVal = ((MQDestination)obj).getMessageBodyStyle();
/* 165 */       if (iVal == 0) {
/* 166 */         sVal = "JMS";
/*     */       }
/* 168 */       else if (iVal == 1) {
/* 169 */         sVal = "MQ";
/*     */       }
/* 171 */       else if (iVal == 2) {
/* 172 */         sVal = "UNSPECIFIED";
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 177 */       String detail = "object is an unexpected type";
/* 178 */       String key = "JMSADM1016";
/* 179 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 180 */       JMSException iee = new JMSException(msg, key);
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMB", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 185 */       throw iee;
/*     */     } 
/* 187 */     if (sVal != null)
/*     */     {
/* 189 */       props.put("MSGBODY", sVal);
/*     */     }
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMB", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMB", "longName()");
/*     */     }
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMB", "longName()", "MSGBODY");
/*     */     }
/* 211 */     return "MSGBODY";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMB", "shortName()");
/*     */     }
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMB", "shortName()", "MBODY");
/*     */     }
/* 227 */     return "MBODY";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APWMB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */