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
/*     */ public class APSRC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSRC.java";
/*     */   public static final String LONGNAME = "SSLRESETCOUNT";
/*     */   public static final String SHORTNAME = "SRC";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.admin.APSRC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSRC.java");
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
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  83 */       Object value = getProperty("SRC", props);
/*     */ 
/*     */       
/*  86 */       if (value != null) {
/*  87 */         int iVal; if (value instanceof Integer) {
/*  88 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  92 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  94 */           catch (NumberFormatException e) {
/*  95 */             if (Trace.isOn) {
/*  96 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/*  99 */             BAOException traceRet1 = new BAOException(4, "SRC", value);
/* 100 */             if (Trace.isOn) {
/* 101 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 104 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 109 */         if (obj instanceof MQConnectionFactory) {
/*     */ 
/*     */           
/*     */           try {
/* 113 */             ((MQConnectionFactory)obj).setSSLResetCount(iVal);
/*     */           }
/* 115 */           catch (Exception e) {
/* 116 */             if (Trace.isOn) {
/* 117 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */             }
/*     */ 
/*     */             
/* 121 */             BAOException be = new BAOException(4, "SRC", Integer.toString(iVal));
/* 122 */             if (Trace.isOn) {
/* 123 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 126 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 131 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 132 */           String key = "JMSADM1016";
/* 133 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 134 */           JMSException iee = new JMSException(msg, key);
/* 135 */           if (Trace.isOn) {
/* 136 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 139 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 144 */     } catch (BAOException e) {
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 153 */       throw e;
/*     */     
/*     */     }
/* 156 */     catch (JMSException e) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 165 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSRC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSRC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 200 */       if (obj instanceof MQConnectionFactory) {
/* 201 */         iVal = ((MQConnectionFactory)obj).getSSLResetCount();
/*     */       }
/*     */       else {
/*     */         
/* 205 */         String detail = "object is an unexpected type";
/* 206 */         String key = "JMSADM1016";
/* 207 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 208 */         JMSException iee = new JMSException(msg, key);
/* 209 */         if (Trace.isOn) {
/* 210 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSRC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 213 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 217 */       props.put("SSLRESETCOUNT", String.valueOf(iVal));
/*     */     }
/*     */     finally {
/*     */       
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSRC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSRC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSRC", "longName()");
/*     */     }
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSRC", "longName()", "SSLRESETCOUNT");
/*     */     }
/* 247 */     return "SSLRESETCOUNT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSRC", "shortName()");
/*     */     }
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSRC", "shortName()", "SRC");
/*     */     }
/* 261 */     return "SRC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSRC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */