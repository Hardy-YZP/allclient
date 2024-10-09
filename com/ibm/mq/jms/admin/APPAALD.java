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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APPAALD
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPAALD.java";
/*     */   public static final String LONGNAME = "PUTASYNCALLOWED";
/*     */   public static final String SHORTNAME = "PAALD";
/*     */   public static final String ENABLED = "YES";
/*     */   public static final String DISABLED = "NO";
/*     */   public static final String AS_DEST = "AS_DEST";
/*     */   public static final String AS_Q_DEF = "AS_Q_DEF";
/*     */   public static final String AS_TOPIC_DEF = "AS_TOPIC_DEF";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APPAALD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPAALD.java");
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
/*     */ 
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAALD", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  94 */     Object value = getProperty("PAALD", props);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  99 */       if (value != null) {
/*     */         int iVal;
/* 101 */         if (value instanceof Integer) {
/* 102 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 105 */           String str = ((String)value).toUpperCase();
/* 106 */           if (str.equals("YES")) {
/* 107 */             iVal = 1;
/*     */           }
/* 109 */           else if (str.equals("NO")) {
/* 110 */             iVal = 0;
/*     */           }
/* 112 */           else if (str.equals("AS_DEST")) {
/* 113 */             iVal = -1;
/*     */           }
/* 115 */           else if (str.equals("AS_Q_DEF")) {
/* 116 */             iVal = -1;
/*     */           }
/* 118 */           else if (str.equals("AS_TOPIC_DEF")) {
/* 119 */             iVal = -1;
/*     */           } else {
/*     */             
/* 122 */             BAOException traceRet1 = new BAOException(4, "PAALD", str);
/* 123 */             if (Trace.isOn) {
/* 124 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPAALD", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 127 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 132 */         if (obj instanceof MQDestination) {
/* 133 */           ((MQDestination)obj).setPutAsyncAllowed(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 137 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 138 */           String key = "JMSADM1016";
/* 139 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 140 */           JMSException iee = new JMSException(msg, key);
/* 141 */           if (Trace.isOn) {
/* 142 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPAALD", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 145 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 150 */     } catch (JMSException e) {
/* 151 */       JMSException jMSException1; if (Trace.isOn) {
/* 152 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPAALD", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)jMSException1);
/*     */       }
/*     */       
/* 155 */       BAOException traceRet2 = new BAOException(4, "PAALD", value);
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPAALD", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 160 */       throw traceRet2;
/*     */     } 
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAALD", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAALD", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/* 181 */     String sVal = null;
/*     */     
/* 183 */     if (obj instanceof MQDestination) {
/* 184 */       iVal = ((MQDestination)obj).getPutAsyncAllowed();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 189 */       String detail = "object is an unexpected type";
/* 190 */       String key = "JMSADM1016";
/* 191 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 192 */       JMSException iee = new JMSException(msg, key);
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPAALD", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 197 */       throw iee;
/*     */     } 
/*     */     
/* 200 */     if (iVal == 1) {
/* 201 */       sVal = "YES";
/*     */     }
/* 203 */     else if (iVal == 0) {
/* 204 */       sVal = "NO";
/*     */     }
/* 206 */     else if (iVal == -1) {
/* 207 */       sVal = "AS_DEST";
/*     */     }
/* 209 */     else if (iVal == -1) {
/* 210 */       sVal = "AS_Q_DEF";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     if (sVal != null)
/*     */     {
/* 219 */       props.put("PUTASYNCALLOWED", sVal);
/*     */     }
/*     */     
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAALD", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAALD", "longName()");
/*     */     }
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAALD", "longName()", "PUTASYNCALLOWED");
/*     */     }
/* 242 */     return "PUTASYNCALLOWED";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAALD", "shortName()");
/*     */     }
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAALD", "shortName()", "PAALD");
/*     */     }
/* 256 */     return "PAALD";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPAALD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */