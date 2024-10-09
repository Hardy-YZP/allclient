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
/*     */ public class APRAALD
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRAALD.java";
/*     */   public static final String LONGNAME = "READAHEADALLOWED";
/*     */   public static final String SHORTNAME = "RAALD";
/*     */   public static final String ENABLED = "YES";
/*     */   public static final String DISABLED = "NO";
/*     */   public static final String AS_DEST = "AS_DEST";
/*     */   public static final String AS_Q_DEF = "AS_Q_DEF";
/*     */   public static final String AS_TOPIC_DEF = "AS_TOPIC_DEF";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APRAALD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRAALD.java");
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
/*  89 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRAALD", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  94 */     Object value = getProperty("RAALD", props);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  99 */       if (value != null) {
/* 100 */         int iVal; if (value instanceof Integer) {
/* 101 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/* 104 */           String str = ((String)value).toUpperCase();
/* 105 */           if (str.equals("YES")) {
/* 106 */             iVal = 1;
/*     */           }
/* 108 */           else if (str.equals("NO")) {
/* 109 */             iVal = 0;
/*     */           }
/* 111 */           else if (str.equals("AS_DEST")) {
/* 112 */             iVal = -1;
/*     */           }
/* 114 */           else if (str.equals("AS_Q_DEF")) {
/* 115 */             iVal = -1;
/*     */           }
/* 117 */           else if (str.equals("AS_TOPIC_DEF")) {
/* 118 */             iVal = -1;
/*     */           } else {
/*     */             
/* 121 */             BAOException traceRet1 = new BAOException(4, "RAALD", str);
/* 122 */             if (Trace.isOn) {
/* 123 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRAALD", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 126 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 131 */         if (obj instanceof MQDestination) {
/* 132 */           ((MQDestination)obj).setReadAheadAllowed(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 136 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 137 */           String key = "JMSADM1016";
/* 138 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 139 */           JMSException iee = new JMSException(msg, key);
/* 140 */           if (Trace.isOn) {
/* 141 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APRAALD", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 144 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 149 */     } catch (JMSException e) {
/* 150 */       JMSException jMSException1; if (Trace.isOn) {
/* 151 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRAALD", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)jMSException1);
/*     */       }
/*     */       
/* 154 */       BAOException traceRet2 = new BAOException(4, "RAALD", value);
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRAALD", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 159 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRAALD", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRAALD", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/* 185 */     String sVal = null;
/*     */     
/* 187 */     if (obj instanceof MQDestination) {
/* 188 */       iVal = ((MQDestination)obj).getReadAheadAllowed();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 193 */       String detail = "object is an unexpected type";
/* 194 */       String key = "JMSADM1016";
/* 195 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 196 */       JMSException iee = new JMSException(msg, key);
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRAALD", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 201 */       throw iee;
/*     */     } 
/*     */     
/* 204 */     if (iVal == 1) {
/* 205 */       sVal = "YES";
/*     */     }
/* 207 */     else if (iVal == 0) {
/* 208 */       sVal = "NO";
/*     */     }
/* 210 */     else if (iVal == -1) {
/* 211 */       sVal = "AS_DEST";
/*     */     }
/* 213 */     else if (iVal == -1) {
/* 214 */       sVal = "AS_Q_DEF";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     if (sVal != null)
/*     */     {
/* 223 */       props.put("READAHEADALLOWED", sVal);
/*     */     }
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRAALD", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRAALD", "longName()");
/*     */     }
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRAALD", "longName()", "READAHEADALLOWED");
/*     */     }
/* 246 */     return "READAHEADALLOWED";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRAALD", "shortName()");
/*     */     }
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRAALD", "shortName()", "RAALD");
/*     */     }
/* 260 */     return "RAALD";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APRAALD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */