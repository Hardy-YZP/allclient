/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ public class APCNTAG
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCNTAG.java";
/*     */   public static final String LONGNAME = "CONNTAG";
/*     */   public static final String SHORTNAME = "CNTAG";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APCNTAG", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APCNTAG.java");
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
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  85 */       String value = (String)getProperty("CNTAG", props);
/*     */       
/*  87 */       if (value != null)
/*     */       {
/*  89 */         if (obj instanceof MQConnectionFactory)
/*     */         {
/*     */           try {
/*  92 */             ((MQConnectionFactory)obj).setConnTag(value.getBytes("UTF8"));
/*     */           }
/*  94 */           catch (UnsupportedEncodingException ue) {
/*  95 */             if (Trace.isOn) {
/*  96 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)", ue, 1);
/*     */             }
/*     */             
/*  99 */             String detail = "UTF8 encoding not supported";
/* 100 */             String key = "JMSADM1059";
/* 101 */             String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 102 */             JMSException iee = new JMSException(msg, key);
/* 103 */             if (Trace.isOn) {
/* 104 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */             }
/*     */             
/* 107 */             throw iee;
/*     */           }
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 113 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 114 */           String key = "JMSADM1016";
/* 115 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 116 */           JMSException iee = new JMSException(msg, key);
/* 117 */           if (Trace.isOn) {
/* 118 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 121 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 126 */     catch (JMSException e) {
/* 127 */       if (Trace.isOn) {
/* 128 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 131 */       if (Trace.isOn) {
/* 132 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 135 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNTAG", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNTAG", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/*     */     try {
/* 164 */       String sVal = null;
/*     */       
/* 166 */       if (obj instanceof MQConnectionFactory) {
/*     */         try {
/* 168 */           sVal = (new String(((MQConnectionFactory)obj).getConnTag(), "UTF8")).trim();
/*     */         }
/* 170 */         catch (UnsupportedEncodingException ue) {
/* 171 */           if (Trace.isOn) {
/* 172 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.APCNTAG", "setPropertyFromObject(Map<String , Object>,Object)", ue);
/*     */           }
/*     */           
/* 175 */           String detail = "UTF8 encoding not supported";
/* 176 */           String key = "JMSADM1059";
/* 177 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 178 */           JMSException iee = new JMSException(msg, key);
/* 179 */           if (Trace.isOn) {
/* 180 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APCNTAG", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 183 */           throw iee;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 188 */         String detail = "object is an unexpected type";
/* 189 */         String key = "JMSADM1016";
/* 190 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 191 */         JMSException iee = new JMSException(msg, key);
/* 192 */         if (Trace.isOn) {
/* 193 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APCNTAG", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee, 2);
/*     */         }
/*     */         
/* 196 */         throw iee;
/*     */       } 
/*     */       
/* 199 */       if (sVal != null && !sVal.equals(""))
/*     */       {
/* 201 */         props.put("CONNTAG", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 206 */       if (Trace.isOn) {
/* 207 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APCNTAG", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNTAG", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNTAG", "longName()");
/*     */     }
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNTAG", "longName()", "CONNTAG");
/*     */     }
/* 232 */     return "CONNTAG";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.admin.APCNTAG", "shortName()");
/*     */     }
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.jms.admin.APCNTAG", "shortName()", "CNTAG");
/*     */     }
/* 246 */     return "CNTAG";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APCNTAG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */