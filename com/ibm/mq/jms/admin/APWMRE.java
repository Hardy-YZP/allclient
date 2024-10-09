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
/*     */ public class APWMRE
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMRE.java";
/*     */   public static final String LONGNAME = "MDREAD";
/*     */   public static final String SHORTNAME = "MDR";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jms.admin.APWMRE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMRE.java");
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
/*  68 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMRE", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*  72 */     String value = (String)getProperty("MDR", props);
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
/*  85 */           BAOException traceRet1 = new BAOException(4, "MDR", str);
/*  86 */           if (Trace.isOn) {
/*  87 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWMRE", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */           }
/*     */           
/*  90 */           throw traceRet1;
/*     */         } 
/*     */ 
/*     */         
/*  94 */         if (obj instanceof MQDestination) {
/*  95 */           ((MQDestination)obj).setMQMDReadEnabled(bVal);
/*     */         }
/*     */         else {
/*     */           
/*  99 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 100 */           String key = "JMSADM1016";
/* 101 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 102 */           JMSException iee = new JMSException(msg, key);
/* 103 */           if (Trace.isOn) {
/* 104 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWMRE", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 107 */           throw iee;
/*     */         }
/*     */       
/*     */       } 
/* 111 */     } catch (JMSException e) {
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APWMRE", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 116 */       BAOException traceRet2 = new BAOException(4, "MDR", value);
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMRE", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 121 */       throw traceRet2;
/*     */     } 
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMRE", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMRE", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (obj instanceof MQDestination) {
/* 151 */       boolean bVal = ((MQDestination)obj).getMQMDReadEnabled();
/*     */       
/* 153 */       if (bVal) {
/* 154 */         sVal = "YES";
/*     */       } else {
/*     */         
/* 157 */         sVal = "NO";
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 162 */       String detail = "object is an unexpected type";
/* 163 */       String key = "JMSADM1016";
/* 164 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 165 */       JMSException iee = new JMSException(msg, key);
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMRE", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 170 */       throw iee;
/*     */     } 
/* 172 */     props.put("MDREAD", sVal);
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMRE", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMRE", "longName()");
/*     */     }
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMRE", "longName()", "MDREAD");
/*     */     }
/* 193 */     return "MDREAD";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMRE", "shortName()");
/*     */     }
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMRE", "shortName()", "MDR");
/*     */     }
/* 209 */     return "MDR";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APWMRE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */