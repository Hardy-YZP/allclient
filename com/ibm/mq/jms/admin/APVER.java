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
/*     */ public class APVER
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APVER.java";
/*     */   public static final String LONGNAME = "VERSION";
/*     */   public static final String SHORTNAME = "VER";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APVER", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APVER.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APVER", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.admin.APVER", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */     int ver;
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.jms.admin.APVER", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (obj instanceof MQDestination) {
/* 109 */       ver = ((MQDestination)obj).getVersion();
/*     */     }
/* 111 */     else if (obj instanceof MQConnectionFactory) {
/* 112 */       ver = ((MQConnectionFactory)obj).getVersion();
/*     */     }
/*     */     else {
/*     */       
/* 116 */       String detail = "object is an unexpected type " + obj.getClass();
/* 117 */       String key = "JMSADM1016";
/* 118 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 119 */       JMSException iee = new JMSException(msg, key);
/* 120 */       if (Trace.isOn) {
/* 121 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APVER", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 124 */       throw iee;
/*     */     } 
/*     */ 
/*     */     
/* 128 */     String value = String.valueOf(ver);
/*     */ 
/*     */     
/* 131 */     props.put("VERSION", value);
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jms.admin.APVER", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.jms.admin.APVER", "longName()");
/*     */     }
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jms.admin.APVER", "longName()", "VERSION");
/*     */     }
/* 153 */     return "VERSION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.jms.admin.APVER", "shortName()");
/*     */     }
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.mq.jms.admin.APVER", "shortName()", "VER");
/*     */     }
/* 167 */     return "VER";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APVER.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */