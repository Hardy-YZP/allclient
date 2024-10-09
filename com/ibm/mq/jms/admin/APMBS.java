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
/*     */ public class APMBS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMBS.java";
/*     */   public static final String LONGNAME = "MSGBATCHSZ";
/*     */   public static final String SHORTNAME = "MBS";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APMBS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMBS.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       Object value = getProperty("MBS", props);
/*     */ 
/*     */       
/*  87 */       if (value != null) {
/*  88 */         int iVal; if (value instanceof Integer) {
/*  89 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  93 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  95 */           catch (NumberFormatException e) {
/*  96 */             if (Trace.isOn) {
/*  97 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 100 */             BAOException traceRet1 = new BAOException(4, "MBS", value);
/* 101 */             if (Trace.isOn) {
/* 102 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 105 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 110 */         if (obj instanceof MQConnectionFactory) {
/*     */ 
/*     */           
/*     */           try {
/* 114 */             ((MQConnectionFactory)obj).setMsgBatchSize(iVal);
/*     */           }
/* 116 */           catch (Exception e) {
/* 117 */             if (Trace.isOn) {
/* 118 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */             }
/*     */ 
/*     */             
/* 122 */             BAOException be = new BAOException(4, "MBS", Integer.toString(iVal));
/* 123 */             if (Trace.isOn) {
/* 124 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 127 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 132 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 133 */           String key = "JMSADM1016";
/* 134 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 135 */           JMSException iee = new JMSException(msg, key);
/* 136 */           if (Trace.isOn) {
/* 137 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 140 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 145 */     } catch (BAOException e) {
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 154 */       throw e;
/*     */     
/*     */     }
/* 157 */     catch (JMSException e) {
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 166 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 201 */       if (obj instanceof MQConnectionFactory) {
/* 202 */         iVal = ((MQConnectionFactory)obj).getMsgBatchSize();
/*     */       }
/*     */       else {
/*     */         
/* 206 */         String detail = "object is an unexpected type";
/* 207 */         String key = "JMSADM1016";
/* 208 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 209 */         JMSException iee = new JMSException(msg, key);
/* 210 */         if (Trace.isOn) {
/* 211 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APMBS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 214 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 218 */       props.put("MSGBATCHSZ", String.valueOf(iVal));
/*     */     }
/*     */     finally {
/*     */       
/* 222 */       if (Trace.isOn) {
/* 223 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMBS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBS", "longName()");
/*     */     }
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBS", "longName()", "MSGBATCHSZ");
/*     */     }
/* 248 */     return "MSGBATCHSZ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBS", "shortName()");
/*     */     }
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBS", "shortName()", "MBS");
/*     */     }
/* 262 */     return "MBS";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMBS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */