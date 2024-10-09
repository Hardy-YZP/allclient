/*     */ package com.ibm.msg.client.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.Serializable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.Referenceable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsJndiXAConnectionFactoryImpl
/*     */   extends JmsXAConnectionFactoryImpl
/*     */   implements Referenceable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7054032339712581L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiXAConnectionFactoryImpl.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiXAConnectionFactoryImpl.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "static()");
/*     */     }
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.data("JmsJndiXAConnectionFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiXAConnectionFactoryImpl.java");
/*     */     }
/*     */     
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "static()");
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
/*     */   public JmsJndiXAConnectionFactoryImpl() {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "<init>()");
/*     */     }
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsJndiXAConnectionFactoryImpl(String connectionTypeName) throws JMSException {
/*  99 */     super(connectionTypeName);
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "<init>(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reference getReference() throws NamingException {
/* 116 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException("Internal Error: Provider should override to provide the reference");
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsJndiXAConnectionFactoryImpl", "getReference()", traceRet1);
/*     */     }
/*     */     
/* 121 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsJndiXAConnectionFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */