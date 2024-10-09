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
/*     */ 
/*     */ public class APPAI
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPAI.java";
/*     */   public static final String LONGNAME = "PUBACKINT";
/*     */   public static final String SHORTNAME = "PAI";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.APPAI", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPAI.java");
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
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  87 */       Object value = getProperty("PAI", props);
/*     */ 
/*     */       
/*  90 */       if (value != null) {
/*  91 */         int iVal; if (value instanceof Integer) {
/*  92 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  96 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  98 */           catch (NumberFormatException e) {
/*  99 */             if (Trace.isOn) {
/* 100 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 103 */             BAOException traceRet1 = new BAOException(4, "PAI", value);
/* 104 */             if (Trace.isOn) {
/* 105 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 108 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 113 */         if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 115 */             ((MQConnectionFactory)obj).setPubAckInterval(iVal);
/*     */           }
/* 117 */           catch (Exception e) {
/* 118 */             if (Trace.isOn) {
/* 119 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */             }
/*     */ 
/*     */             
/* 123 */             BAOException be = new BAOException(4, "PAI", Integer.toString(iVal));
/* 124 */             if (Trace.isOn) {
/* 125 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 128 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 133 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 134 */           String key = "JMSADM1016";
/* 135 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 136 */           JMSException iee = new JMSException(msg, key);
/* 137 */           if (Trace.isOn) {
/* 138 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 141 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 146 */     } catch (BAOException e) {
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 155 */       throw e;
/*     */     
/*     */     }
/* 158 */     catch (JMSException e) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 167 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAI", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAI", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 202 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 203 */         int i = ((MQConnectionFactory)obj).getPubAckInterval();
/* 204 */         sVal = String.valueOf(i);
/*     */       }
/*     */       else {
/*     */         
/* 208 */         String detail = "object is an unexpected type";
/* 209 */         String key = "JMSADM1016";
/* 210 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 211 */         JMSException iee = new JMSException(msg, key);
/* 212 */         if (Trace.isOn) {
/* 213 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APPAI", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 216 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 220 */       props.put("PUBACKINT", sVal);
/*     */     }
/*     */     finally {
/*     */       
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APPAI", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAI", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAI", "longName()");
/*     */     }
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAI", "longName()", "PUBACKINT");
/*     */     }
/* 250 */     return "PUBACKINT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPAI", "shortName()");
/*     */     }
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPAI", "shortName()", "PAI");
/*     */     }
/* 264 */     return "PAI";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPAI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */