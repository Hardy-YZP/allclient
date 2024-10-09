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
/*     */ public class APCCS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCS.java";
/*     */   public static final String LONGNAME = "CCSID";
/*     */   public static final String SHORTNAME = "CCS";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APCCS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCCS.java");
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
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       Object value = getProperty("CCS", props);
/*     */       
/*  87 */       if (value != null) {
/*  88 */         int iVal = 0;
/*  89 */         if (value instanceof Integer) {
/*  90 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  94 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  96 */           catch (NumberFormatException e) {
/*  97 */             if (Trace.isOn) {
/*  98 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 101 */             BAOException traceRet1 = new BAOException(4, "CCS", value);
/* 102 */             if (Trace.isOn) {
/* 103 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 106 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 111 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 113 */             ((MQDestination)obj).setCCSID(iVal);
/*     */           }
/* 115 */           catch (JMSException e) {
/* 116 */             if (Trace.isOn) {
/* 117 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */             
/* 121 */             BAOException be = new BAOException(4, "CCS", Integer.toString(iVal));
/* 122 */             if (Trace.isOn) {
/* 123 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 126 */             throw be;
/*     */           }
/*     */         
/* 129 */         } else if (obj instanceof MQConnectionFactory) {
/*     */           try {
/* 131 */             ((MQConnectionFactory)obj).setCCSID(iVal);
/*     */           }
/* 133 */           catch (JMSException e) {
/* 134 */             if (Trace.isOn) {
/* 135 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */             }
/*     */ 
/*     */             
/* 139 */             BAOException be = new BAOException(4, "CCS", Integer.toString(iVal));
/* 140 */             if (Trace.isOn) {
/* 141 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", be, 3);
/*     */             }
/*     */             
/* 144 */             throw be;
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 150 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 151 */           String key = "JMSADM1016";
/* 152 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 153 */           JMSException iee = new JMSException(msg, key);
/* 154 */           if (Trace.isOn) {
/* 155 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 4);
/*     */           }
/*     */           
/* 158 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 163 */     } catch (BAOException e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", e, 5);
/*     */       }
/*     */       
/* 172 */       throw e;
/*     */     
/*     */     }
/* 175 */     catch (JMSException e) {
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 6);
/*     */       }
/*     */       
/* 184 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 217 */       if (obj instanceof MQDestination) {
/* 218 */         iVal = ((MQDestination)obj).getCCSID();
/*     */       
/*     */       }
/* 221 */       else if (obj instanceof MQConnectionFactory) {
/* 222 */         iVal = ((MQConnectionFactory)obj).getCCSID();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 227 */         String detail = "object is an unexpected type";
/* 228 */         String key = "JMSADM1016";
/* 229 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 230 */         JMSException iee = new JMSException(msg, key);
/* 231 */         if (Trace.isOn) {
/* 232 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCCS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 235 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 239 */       String value = String.valueOf(iVal);
/*     */ 
/*     */       
/* 242 */       props.put("CCSID", value);
/*     */     }
/*     */     finally {
/*     */       
/* 246 */       if (Trace.isOn) {
/* 247 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCCS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCS", "longName()");
/*     */     }
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCS", "longName()", "CCSID");
/*     */     }
/* 272 */     return "CCSID";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCCS", "shortName()");
/*     */     }
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCCS", "shortName()", "CCS");
/*     */     }
/* 286 */     return "CCS";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCCS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */