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
/*     */ public class APPPORT
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPPORT.java";
/*     */   public static final String LONGNAME = "PROXYPORT";
/*     */   public static final String SHORTNAME = "PPORT";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.admin.APPPORT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APPPORT.java");
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
/*  76 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPPORT", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPPORT", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPPORT", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPPORT", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPPORT", "longName()");
/*     */     }
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPPORT", "longName()", "PROXYPORT");
/*     */     }
/* 126 */     return "PROXYPORT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.jms.admin.APPPORT", "shortName()");
/*     */     }
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.mq.jms.admin.APPPORT", "shortName()", "PPORT");
/*     */     }
/* 140 */     return "PPORT";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APPPORT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */