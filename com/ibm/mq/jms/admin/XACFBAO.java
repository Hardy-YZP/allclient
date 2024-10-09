/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQXAConnectionFactory;
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
/*     */ public class XACFBAO
/*     */   extends CFBAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/XACFBAO.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.XACFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/XACFBAO.java");
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
/*     */   public XACFBAO() {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry(this, "com.ibm.mq.jms.admin.XACFBAO", "<init>()");
/*     */     }
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.jms.admin.XACFBAO", "<init>()");
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
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.data(this, "com.ibm.mq.jms.admin.XACFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  94 */       this.cf = (MQConnectionFactory)new MQXAConnectionFactory();
/*     */ 
/*     */ 
/*     */       
/*  98 */       _setFromProperties(props);
/*     */     }
/*     */     finally {
/*     */       
/* 102 */       if (Trace.isOn) {
/* 103 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.XACFBAO", "setFromProperties(Map<String , Object>)");
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
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.data(this, "com.ibm.mq.jms.admin.XACFBAO", "getType()", "getter", 
/* 119 */           Integer.valueOf(9));
/*     */     }
/* 121 */     return 9;
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
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.data(this, "com.ibm.mq.jms.admin.XACFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 135 */     if (obj instanceof MQXAConnectionFactory) {
/* 136 */       this.cf = (MQConnectionFactory)obj;
/*     */     } else {
/*     */       
/* 139 */       BAOException be = new BAOException(10, null, null);
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.throwing(this, "com.ibm.mq.jms.admin.XACFBAO", "setFromObject(Object)", be);
/*     */       }
/* 143 */       throw be;
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
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.mq.jms.admin.XACFBAO", "name()");
/*     */     }
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.mq.jms.admin.XACFBAO", "name()", "XACF");
/*     */     }
/* 160 */     return "XACF";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\XACFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */