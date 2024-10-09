/*     */ package com.ibm.msg.client.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.Referenceable;
/*     */ import javax.naming.StringRefAddr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsJndiConnectionFactoryImpl
/*     */   extends JmsConnectionFactoryImpl
/*     */   implements Referenceable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -805797469951126943L;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiConnectionFactoryImpl.java");
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
/*     */   public JmsJndiConnectionFactoryImpl() {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "<init>()");
/*     */     }
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected JmsJndiConnectionFactoryImpl(String connectionTypeName) throws JMSException {
/*  82 */     super(connectionTypeName);
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "<init>(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */     
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "<init>(String)");
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
/*     */   public Reference getReference() throws NamingException {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "getReference()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     Reference reference = new Reference(getClass().getName(), JmsJndiObjectFactory.class.getName(), null);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 112 */       Enumeration<String> propertyNames = getPropertyNames();
/* 113 */       while (propertyNames.hasMoreElements()) {
/* 114 */         String propertyName = propertyNames.nextElement();
/* 115 */         Object propertyValue = getObjectProperty(propertyName);
/*     */ 
/*     */ 
/*     */         
/* 119 */         if (propertyValue != null) {
/* 120 */           reference.add(new StringRefAddr(propertyName, propertyValue.toString())); continue;
/*     */         } 
/* 122 */         reference.add(new StringRefAddr(propertyName, null));
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 127 */     catch (JMSException e) {
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "getReference()", (Throwable)e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl", "getReference()", reference);
/*     */     }
/*     */     
/* 140 */     return reference;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsJndiConnectionFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */