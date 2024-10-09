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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APUMA
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APUMA.java";
/*     */   public static final String LONGNAME = "UNMAPPABLEACTION";
/*     */   public static final String SHORTNAME = "UMA";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APUMA", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APUMA.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  83 */       Object value = getProperty("UMA", props);
/*     */       
/*  85 */       if (value != null)
/*     */       {
/*  87 */         if (obj instanceof MQDestination) {
/*     */           try {
/*  89 */             ((MQDestination)obj).setUnmappableAction((String)value);
/*     */           }
/*  91 */           catch (JMSException e) {
/*  92 */             if (Trace.isOn) {
/*  93 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/*  97 */             BAOException be = new BAOException(4, "UMA", value);
/*  98 */             if (Trace.isOn) {
/*  99 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 102 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 107 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 108 */           String key = "JMSADM1016";
/* 109 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 110 */           JMSException iee = new JMSException(msg, key);
/* 111 */           if (Trace.isOn) {
/* 112 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 115 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 120 */     catch (BAOException e) {
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 129 */       throw e;
/*     */     
/*     */     }
/* 132 */     catch (JMSException e) {
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 141 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMA", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMA", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String value;
/*     */       
/* 174 */       if (obj instanceof MQDestination) {
/* 175 */         value = ((MQDestination)obj).getUnmappableAction();
/*     */       }
/*     */       else {
/*     */         
/* 179 */         String detail = "object is an unexpected type";
/* 180 */         String key = "JMSADM1016";
/* 181 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 182 */         JMSException iee = new JMSException(msg, key);
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APUMA", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 187 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 191 */       props.put("UNMAPPABLEACTION", value);
/*     */     }
/*     */     finally {
/*     */       
/* 195 */       if (Trace.isOn) {
/* 196 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APUMA", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMA", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMA", "longName()");
/*     */     }
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMA", "longName()", "UNMAPPABLEACTION");
/*     */     }
/* 221 */     return "UNMAPPABLEACTION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMA", "shortName()");
/*     */     }
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMA", "shortName()", "UMA");
/*     */     }
/* 235 */     return "UMA";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APUMA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */