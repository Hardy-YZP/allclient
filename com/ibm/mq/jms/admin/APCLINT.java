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
/*     */ public class APCLINT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCLINT.java";
/*     */   public static final String LONGNAME = "CLEANUPINT";
/*     */   public static final String SHORTNAME = "CLINT";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APCLINT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCLINT.java");
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
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       Object value = getProperty("CLINT", props);
/*     */ 
/*     */       
/*  88 */       if (value != null) {
/*  89 */         long lVal; if (value instanceof Long) {
/*  90 */           lVal = ((Long)value).longValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  94 */             lVal = Long.parseLong((String)value);
/*     */           }
/*  96 */           catch (NumberFormatException e) {
/*  97 */             if (Trace.isOn) {
/*  98 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 101 */             BAOException traceRet1 = new BAOException(4, "CLINT", value);
/* 102 */             if (Trace.isOn) {
/* 103 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 106 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 111 */         if (obj instanceof MQConnectionFactory) {
/*     */ 
/*     */           
/*     */           try {
/* 115 */             ((MQConnectionFactory)obj).setCleanupInterval(lVal);
/*     */           }
/* 117 */           catch (Exception e) {
/* 118 */             if (Trace.isOn) {
/* 119 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */             }
/*     */ 
/*     */             
/* 123 */             BAOException be = new BAOException(4, "CLINT", Long.toString(lVal));
/* 124 */             if (Trace.isOn) {
/* 125 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
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
/* 138 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 141 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 146 */     } catch (BAOException e) {
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 155 */       throw e;
/*     */     
/*     */     }
/* 158 */     catch (JMSException e) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 167 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLINT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 195 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLINT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       long lVal;
/*     */       
/* 202 */       if (obj instanceof com.ibm.mq.jms.MQTopicConnectionFactory || obj instanceof MQConnectionFactory) {
/* 203 */         lVal = ((MQConnectionFactory)obj).getCleanupInterval();
/*     */       }
/*     */       else {
/*     */         
/* 207 */         String detail = "object is an unexpected type";
/* 208 */         String key = "JMSADM1016";
/* 209 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 210 */         JMSException iee = new JMSException(msg, key);
/* 211 */         if (Trace.isOn) {
/* 212 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCLINT", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 215 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 219 */       props.put("CLEANUPINT", String.valueOf(lVal));
/*     */     }
/*     */     finally {
/*     */       
/* 223 */       if (Trace.isOn) {
/* 224 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCLINT", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLINT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLINT", "longName()");
/*     */     }
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLINT", "longName()", "CLEANUPINT");
/*     */     }
/* 249 */     return "CLEANUPINT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCLINT", "shortName()");
/*     */     }
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCLINT", "shortName()", "CLINT");
/*     */     }
/* 263 */     return "CLINT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCLINT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */