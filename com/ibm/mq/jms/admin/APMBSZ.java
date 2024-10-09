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
/*     */ public class APMBSZ
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMBSZ.java";
/*     */   public static final String LONGNAME = "MAXBUFFSIZE";
/*     */   public static final String SHORTNAME = "MBSZ";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APMBSZ", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMBSZ.java");
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
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       Object value = getProperty("MBSZ", props);
/*     */ 
/*     */       
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
/*  98 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 101 */             BAOException traceRet1 = new BAOException(4, "MBSZ", value);
/* 102 */             if (Trace.isOn) {
/* 103 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 106 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 111 */         if (!(obj instanceof com.ibm.mq.jms.MQQueueConnectionFactory)) {
/*     */           try {
/* 113 */             ((MQConnectionFactory)obj).setMaxBufferSize(iVal);
/*     */           }
/* 115 */           catch (JMSException e) {
/* 116 */             if (Trace.isOn) {
/* 117 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */             
/* 121 */             BAOException be = new BAOException(4, "MBSZ", Integer.toString(iVal));
/* 122 */             if (Trace.isOn) {
/* 123 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
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
/* 136 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 139 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 144 */     } catch (BAOException e) {
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 153 */       throw e;
/*     */     
/*     */     }
/* 156 */     catch (JMSException e) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 165 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBSZ", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 193 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBSZ", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 200 */       if (!(obj instanceof com.ibm.mq.jms.MQQueueConnectionFactory)) {
/* 201 */         iVal = ((MQConnectionFactory)obj).getMaxBufferSize();
/*     */       }
/*     */       else {
/*     */         
/* 205 */         String detail = "object is an unexpected type";
/* 206 */         String key = "JMSADM1016";
/* 207 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 208 */         JMSException iee = new JMSException(msg, key);
/* 209 */         if (Trace.isOn) {
/* 210 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APMBSZ", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 213 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 217 */       props.put("MAXBUFFSIZE", String.valueOf(iVal));
/*     */     } finally {
/*     */       
/* 220 */       if (Trace.isOn) {
/* 221 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APMBSZ", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBSZ", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBSZ", "longName()");
/*     */     }
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBSZ", "longName()", "MAXBUFFSIZE");
/*     */     }
/* 246 */     return "MAXBUFFSIZE";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMBSZ", "shortName()");
/*     */     }
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMBSZ", "shortName()", "MBSZ");
/*     */     }
/* 260 */     return "MBSZ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMBSZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */