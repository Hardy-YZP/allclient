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
/*     */ public class APHC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APHC.java";
/*     */   public static final String LONGNAME = "COMPHDR";
/*     */   public static final String SHORTNAME = "HC";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APHC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APHC.java");
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
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       Object value = getProperty("HC", props);
/*     */ 
/*     */       
/*  87 */       if (value != null) {
/*     */         String sVal;
/*     */         
/*  90 */         if (value instanceof String) {
/*  91 */           sVal = (String)value;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  96 */           String detail = "value supplied as an unexpected object type " + value.getClass();
/*  97 */           String key = "JMSADM1016";
/*  98 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/*  99 */           JMSException iee = new JMSException(msg, key);
/* 100 */           if (Trace.isOn) {
/* 101 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 104 */           throw iee;
/*     */         } 
/*     */ 
/*     */         
/* 108 */         if (obj instanceof MQConnectionFactory)
/*     */         {
/*     */           
/* 111 */           ((MQConnectionFactory)obj).setHdrCompList(sVal);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 116 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 117 */           String key = "JMSADM1016";
/* 118 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 119 */           JMSException iee = new JMSException(msg, key);
/* 120 */           if (Trace.isOn) {
/* 121 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 124 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 129 */     } catch (JMSException e) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 138 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.mq.jms.admin.APHC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.jms.admin.APHC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/*     */     try {
/* 167 */       String sVal = null;
/*     */       
/* 169 */       if (obj instanceof MQConnectionFactory) {
/* 170 */         sVal = ((MQConnectionFactory)obj).getHdrCompListAsString();
/*     */       }
/*     */       else {
/*     */         
/* 174 */         String detail = "object is an unexpected type";
/* 175 */         String key = "JMSADM1016";
/* 176 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 177 */         JMSException iee = new JMSException(msg, key);
/* 178 */         if (Trace.isOn) {
/* 179 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APHC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 182 */         throw iee;
/*     */       } 
/*     */       
/* 185 */       if (sVal != null)
/*     */       {
/* 187 */         props.put("COMPHDR", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APHC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.jms.admin.APHC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.entry(this, "com.ibm.mq.jms.admin.APHC", "longName()");
/*     */     }
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.jms.admin.APHC", "longName()", "COMPHDR");
/*     */     }
/* 218 */     return "COMPHDR";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.jms.admin.APHC", "shortName()");
/*     */     }
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.mq.jms.admin.APHC", "shortName()", "HC");
/*     */     }
/* 232 */     return "HC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APHC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */