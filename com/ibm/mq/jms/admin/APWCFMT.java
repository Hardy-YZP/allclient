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
/*     */ public class APWCFMT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWCFMT.java";
/*     */   public static final String LONGNAME = "WILDCARDFORMAT";
/*     */   public static final String SHORTNAME = "WCFMT";
/*     */   public static final String WILDCARD_CHAR_ONLY = "CHAR_ONLY";
/*     */   public static final String WILDCARD_TOPIC_ONLY = "TOPIC_ONLY";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APWCFMT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWCFMT.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWCFMT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  90 */     Object value = getProperty("WCFMT", props);
/*     */     
/*     */     try {
/*  93 */       if (value != null) {
/*     */         int iVal;
/*  95 */         if (value instanceof Integer) {
/*  96 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*  99 */           String str = ((String)value).toUpperCase();
/* 100 */           if (str.equals("CHAR_ONLY")) {
/* 101 */             iVal = 1;
/*     */           }
/* 103 */           else if (str.equals("TOPIC_ONLY")) {
/* 104 */             iVal = 0;
/*     */           } else {
/*     */             
/* 107 */             BAOException traceRet1 = new BAOException(4, "WCFMT", str);
/* 108 */             if (Trace.isOn) {
/* 109 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APWCFMT", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 112 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 117 */         if (obj instanceof MQConnectionFactory) {
/* 118 */           ((MQConnectionFactory)obj).setWildcardFormat(iVal);
/*     */         }
/* 120 */         else if (obj instanceof MQDestination) {
/* 121 */           ((MQDestination)obj).setWildcardFormat(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 125 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 126 */           String key = "JMSADM1016";
/* 127 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 128 */           JMSException iee = new JMSException(msg, key);
/* 129 */           if (Trace.isOn) {
/* 130 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWCFMT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 133 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 138 */     } catch (JMSException e) {
/* 139 */       JMSException jMSException1; if (Trace.isOn) {
/* 140 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APWCFMT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)jMSException1);
/*     */       }
/*     */       
/* 143 */       BAOException traceRet2 = new BAOException(4, "WCFMT", value);
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWCFMT", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 148 */       throw traceRet2;
/*     */     } 
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWCFMT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/*     */     int iVal;
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWCFMT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/* 168 */     String sVal = null;
/*     */ 
/*     */     
/* 171 */     if (obj instanceof MQConnectionFactory) {
/* 172 */       iVal = ((MQConnectionFactory)obj).getWildcardFormat();
/*     */     }
/* 174 */     else if (obj instanceof MQDestination) {
/* 175 */       iVal = ((MQDestination)obj).getWildcardFormat();
/*     */     }
/*     */     else {
/*     */       
/* 179 */       String detail = "object is an unexpected type";
/* 180 */       String key = "JMSADM1016";
/* 181 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */       JMSException iee = new JMSException(msg, key);
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWCFMT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 187 */       throw iee;
/*     */     } 
/*     */     
/* 190 */     if (iVal == 1) {
/* 191 */       sVal = "CHAR_ONLY";
/*     */     }
/* 193 */     else if (iVal == 0) {
/* 194 */       sVal = "TOPIC_ONLY";
/*     */     } 
/*     */     
/* 197 */     if (sVal != null)
/*     */     {
/* 199 */       props.put("WILDCARDFORMAT", sVal);
/*     */     }
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWCFMT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWCFMT", "longName()");
/*     */     }
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWCFMT", "longName()", "WILDCARDFORMAT");
/*     */     }
/* 222 */     return "WILDCARDFORMAT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWCFMT", "shortName()");
/*     */     }
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWCFMT", "shortName()", "WCFMT");
/*     */     }
/* 236 */     return "WCFMT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APWCFMT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */