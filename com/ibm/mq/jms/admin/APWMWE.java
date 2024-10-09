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
/*     */ public class APWMWE
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMWE.java";
/*     */   public static final String LONGNAME = "MDWRITE";
/*     */   public static final String SHORTNAME = "MDW";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jms.admin.APWMWE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMWE.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMWE", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*  72 */     String value = (String)getProperty("MDW", props);
/*     */     try {
/*  74 */       boolean bVal = false;
/*  75 */       if (value != null) {
/*     */         
/*  77 */         String str = value.toUpperCase();
/*  78 */         if (str.equals("YES")) {
/*  79 */           bVal = true;
/*     */         }
/*  81 */         else if (str.equals("NO")) {
/*  82 */           bVal = false;
/*     */         } else {
/*     */           
/*  85 */           BAOException traceRet1 = new BAOException(4, "MDW", str);
/*  86 */           if (Trace.isOn) {
/*  87 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWMWE", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */           }
/*     */           
/*  90 */           throw traceRet1;
/*     */         } 
/*     */         
/*  93 */         if (obj instanceof MQDestination) {
/*  94 */           ((MQDestination)obj).setMQMDWriteEnabled(bVal);
/*     */         }
/*     */         else {
/*     */           
/*  98 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/*  99 */           String key = "JMSADM1016";
/* 100 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 101 */           JMSException iee = new JMSException(msg, key);
/* 102 */           if (Trace.isOn) {
/* 103 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWMWE", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 106 */           throw iee;
/*     */         }
/*     */       
/*     */       } 
/* 110 */     } catch (JMSException e) {
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APWMWE", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 115 */       BAOException traceRet2 = new BAOException(4, "MDW", value);
/* 116 */       if (Trace.isOn) {
/* 117 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMWE", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 120 */       throw traceRet2;
/*     */     } 
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMWE", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/*     */     String sVal;
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMWE", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     if (obj instanceof MQDestination) {
/* 150 */       boolean bVal = ((MQDestination)obj).getMQMDWriteEnabled();
/*     */       
/* 152 */       if (bVal) {
/* 153 */         sVal = "YES";
/*     */       } else {
/*     */         
/* 156 */         sVal = "NO";
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 161 */       String detail = "object is an unexpected type";
/* 162 */       String key = "JMSADM1016";
/* 163 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 164 */       JMSException iee = new JMSException(msg, key);
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMWE", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 169 */       throw iee;
/*     */     } 
/* 171 */     props.put("MDWRITE", sVal);
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMWE", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMWE", "longName()");
/*     */     }
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMWE", "longName()", "MDWRITE");
/*     */     }
/* 192 */     return "MDWRITE";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMWE", "shortName()");
/*     */     }
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMWE", "shortName()", "MDW");
/*     */     }
/* 208 */     return "MDW";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APWMWE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */