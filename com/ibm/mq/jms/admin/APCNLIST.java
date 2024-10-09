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
/*     */ 
/*     */ public class APCNLIST
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCNLIST.java";
/*     */   public static final String LONGNAME = "CONNECTIONNAMELIST";
/*     */   public static final String SHORTNAME = "CNLIST";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APCNLIST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCNLIST.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  83 */       String value = (String)getProperty("CNLIST", props);
/*     */       
/*  85 */       if (value != null)
/*     */       {
/*  87 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/*  89 */             ((MQConnectionFactory)obj).setConnectionNameList(value);
/*     */           }
/*  91 */           catch (JMSException e) {
/*  92 */             if (Trace.isOn) {
/*  93 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/*  97 */             BAOException traceRet2 = new BAOException(4, "CNLIST", value);
/*  98 */             if (Trace.isOn) {
/*  99 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 1);
/*     */             }
/*     */             
/* 102 */             throw traceRet2;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 107 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 108 */           String key = "JMSADM1016";
/* 109 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 110 */           JMSException traceRet3 = new JMSException(msg, key);
/* 111 */           if (Trace.isOn) {
/* 112 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)traceRet3, 2);
/*     */           }
/*     */           
/* 115 */           throw traceRet3;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 120 */     catch (BAOException e) {
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 129 */       throw e;
/*     */     
/*     */     }
/* 132 */     catch (JMSException e) {
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 141 */       throw e;
/*     */     } 
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNLIST", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/*     */     String sVal;
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNLIST", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     if (obj instanceof MQConnectionFactory) {
/* 170 */       sVal = ((MQConnectionFactory)obj).getConnectionNameList();
/*     */     }
/*     */     else {
/*     */       
/* 174 */       String detail = "object is an unexpected type";
/* 175 */       String key = "JMSADM1016";
/* 176 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 177 */       JMSException traceRet1 = new JMSException(msg, key);
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCNLIST", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 182 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 186 */     if (sVal != null && sVal.length() > 0) {
/* 187 */       props.put("CONNECTIONNAMELIST", sVal);
/*     */     }
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNLIST", "setPropertyFromObject(Map<String , Object>,Object)");
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
/*     */   public String longName() {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNLIST", "longName()");
/*     */     }
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNLIST", "longName()", "CONNECTIONNAMELIST");
/*     */     }
/* 210 */     return "CONNECTIONNAMELIST";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNLIST", "shortName()");
/*     */     }
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNLIST", "shortName()", "CNLIST");
/*     */     }
/* 227 */     return "CNLIST";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCNLIST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */