/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQXAQueueConnectionFactory;
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
/*     */ public class XAQCFBAO
/*     */   extends QCFBAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/XAQCFBAO.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.XAQCFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/XAQCFBAO.java");
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
/*     */   public XAQCFBAO() {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.mq.jms.admin.XAQCFBAO", "<init>()");
/*     */     }
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.exit(this, "com.ibm.mq.jms.admin.XAQCFBAO", "<init>()");
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
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.data(this, "com.ibm.mq.jms.admin.XAQCFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  96 */       this.cf = (MQConnectionFactory)new MQXAQueueConnectionFactory();
/*     */ 
/*     */ 
/*     */       
/* 100 */       _setFromProperties(props);
/*     */     }
/*     */     finally {
/*     */       
/* 104 */       if (Trace.isOn) {
/* 105 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.XAQCFBAO", "setFromProperties(Map<String , Object>)");
/*     */       }
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
/*     */   public int getType() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.jms.admin.XAQCFBAO", "getType()", "getter", 
/* 121 */           Integer.valueOf(4));
/*     */     }
/* 123 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromObject(Object obj) throws BAOException {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.jms.admin.XAQCFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 137 */     if (obj instanceof MQXAQueueConnectionFactory) {
/* 138 */       this.cf = (MQConnectionFactory)obj;
/*     */     } else {
/*     */       
/* 141 */       BAOException be = new BAOException(10, null, null);
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.jms.admin.XAQCFBAO", "setFromObject(Object)", be);
/*     */       }
/* 145 */       throw be;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String name() {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.mq.jms.admin.XAQCFBAO", "name()");
/*     */     }
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.exit(this, "com.ibm.mq.jms.admin.XAQCFBAO", "name()", "XAQCF");
/*     */     }
/* 162 */     return "XAQCF";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\XAQCFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */