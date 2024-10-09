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
/*     */ 
/*     */ public class APSCALD
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCALD.java";
/*     */   public static final String LONGNAME = "SHARECONVALLOWED";
/*     */   public static final String SHORTNAME = "SCALD";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APSCALD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSCALD.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  80 */     String value = (String)getProperty("SCALD", props);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       if (value != null) {
/*     */         int iVal;
/*  87 */         String sVal = value.toUpperCase();
/*  88 */         if (sVal.equals("YES")) {
/*  89 */           iVal = 1;
/*     */         }
/*  91 */         else if (sVal.equals("NO")) {
/*  92 */           iVal = 0;
/*     */         } else {
/*     */           
/*  95 */           BAOException traceRet1 = new BAOException(4, "SCALD", sVal);
/*  96 */           if (Trace.isOn) {
/*  97 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */           }
/*     */           
/* 100 */           throw traceRet1;
/*     */         } 
/*     */ 
/*     */         
/* 104 */         if (obj instanceof MQConnectionFactory) {
/* 105 */           ((MQConnectionFactory)obj).setShareConvAllowed(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 109 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 110 */           String key = "JMSADM1016";
/* 111 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 112 */           JMSException iee = new JMSException(msg, key);
/* 113 */           if (Trace.isOn) {
/* 114 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 117 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 122 */     } catch (JMSException e) {
/* 123 */       JMSException jMSException1; if (Trace.isOn) {
/* 124 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)jMSException1);
/*     */       }
/*     */       
/* 127 */       BAOException traceRet1 = new BAOException(4, "SCALD", value);
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 3);
/*     */       }
/*     */       
/* 132 */       throw traceRet1;
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */ 
/*     */       
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCALD", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCALD", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     try {
/*     */       int iVal;
/* 164 */       String sVal = null;
/*     */ 
/*     */       
/* 167 */       if (obj instanceof MQConnectionFactory) {
/* 168 */         iVal = ((MQConnectionFactory)obj).getShareConvAllowed();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 173 */         String detail = "object is an unexpected type";
/* 174 */         String key = "JMSADM1016";
/* 175 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 176 */         JMSException iee = new JMSException(msg, key);
/* 177 */         if (Trace.isOn) {
/* 178 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSCALD", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 181 */         throw iee;
/*     */       } 
/*     */       
/* 184 */       if (iVal == 0) {
/* 185 */         sVal = "NO";
/*     */       }
/* 187 */       else if (iVal == 1) {
/* 188 */         sVal = "YES";
/*     */       } 
/*     */       
/* 191 */       if (sVal != null)
/*     */       {
/* 193 */         props.put("SHARECONVALLOWED", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 198 */       if (Trace.isOn) {
/* 199 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSCALD", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCALD", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 218 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCALD", "longName()");
/*     */     }
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCALD", "longName()", "SHARECONVALLOWED");
/*     */     }
/* 223 */     return "SHARECONVALLOWED";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSCALD", "shortName()");
/*     */     }
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSCALD", "shortName()", "SCALD");
/*     */     }
/* 237 */     return "SCALD";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSCALD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */