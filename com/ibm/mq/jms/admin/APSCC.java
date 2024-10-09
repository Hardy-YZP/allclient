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
/*     */ public class APSCC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCC.java";
/*     */   public static final String LONGNAME = "SENDCHECKCOUNT";
/*     */   public static final String SHORTNAME = "SCC";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APSCC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCC.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  83 */     Object value = getProperty("SCC", props);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  88 */       if (value != null) {
/*  89 */         int iVal; if (value instanceof Integer) {
/*  90 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  94 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  96 */           catch (NumberFormatException e) {
/*  97 */             if (Trace.isOn) {
/*  98 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 101 */             BAOException traceRet1 = new BAOException(4, "SCC", value);
/* 102 */             if (Trace.isOn) {
/* 103 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 106 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 111 */         if (obj instanceof MQConnectionFactory) {
/* 112 */           ((MQConnectionFactory)obj).setSendCheckCount(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 116 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 117 */           String key = "JMSADM1016";
/* 118 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 119 */           JMSException iee = new JMSException(msg, key);
/* 120 */           if (Trace.isOn) {
/* 121 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 124 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 129 */     } catch (JMSException e) {
/* 130 */       JMSException jMSException1; if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)jMSException1, 2);
/*     */       }
/*     */       
/* 134 */       BAOException traceRet2 = new BAOException(4, "SCC", value);
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 139 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 167 */     if (obj instanceof MQConnectionFactory) {
/* 168 */       sVal = Integer.toString(((MQConnectionFactory)obj).getSendCheckCount());
/*     */     }
/*     */     else {
/*     */       
/* 172 */       String detail = "object is an unexpected type";
/* 173 */       String key = "JMSADM1016";
/* 174 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 175 */       JMSException iee = new JMSException(msg, key);
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSCC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 180 */       throw iee;
/*     */     } 
/*     */     
/* 183 */     if (sVal != null)
/*     */     {
/* 185 */       props.put("SENDCHECKCOUNT", sVal);
/*     */     }
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCC", "longName()");
/*     */     }
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCC", "longName()", "SENDCHECKCOUNT");
/*     */     }
/* 208 */     return "SENDCHECKCOUNT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCC", "shortName()");
/*     */     }
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCC", "shortName()", "SCC");
/*     */     }
/* 222 */     return "SCC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSCC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */