/*     */ package com.ibm.mq.jms.admin;
/*     */ 
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
/*     */ public class APPHOST
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPHOST.java";
/*     */   public static final String LONGNAME = "PROXYHOSTNAME";
/*     */   public static final String SHORTNAME = "PHOST";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.admin.APPHOST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPHOST.java");
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
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPHOST", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPHOST", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPHOST", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPHOST", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPHOST", "longName()");
/*     */     }
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPHOST", "longName()", "PROXYHOSTNAME");
/*     */     }
/* 123 */     return "PROXYHOSTNAME";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPHOST", "shortName()");
/*     */     }
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPHOST", "shortName()", "PHOST");
/*     */     }
/* 137 */     return "PHOST";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPHOST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */