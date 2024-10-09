/*     */ package com.ibm.msg.client.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
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
/*     */ 
/*     */ public class JmsJndiDestinationImpl
/*     */   extends JmsDestinationImpl
/*     */   implements JmsDestination, Referenceable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8412850935846832190L;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiDestinationImpl.java");
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
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "static()");
/*     */     }
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.data("JmsJndiConnectionFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiDestinationImpl.java");
/*     */     }
/*     */     
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsJndiDestinationImpl() {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "<init>()");
/*     */     }
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "<init>()");
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
/*     */   public JmsJndiDestinationImpl(String connectionTypeName) {
/* 104 */     super(connectionTypeName);
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "<init>(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */ 
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "<init>(String)");
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
/*     */   public JmsJndiDestinationImpl(String connectionTypeName, String name) throws JMSException {
/* 124 */     super(connectionTypeName, name);
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "<init>(String,String)", new Object[] { connectionTypeName, name });
/*     */     }
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "<init>(String,String)");
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
/*     */   public Reference getReference() throws NamingException {
/* 145 */     Reference reference = new Reference(getClass().getName(), JmsJndiObjectFactory.class.getName(), null);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 150 */       Enumeration<String> propertyNames = getPropertyNames();
/* 151 */       while (propertyNames.hasMoreElements()) {
/* 152 */         String propertyName = propertyNames.nextElement();
/* 153 */         reference.add(new StringRefAddr(propertyName, getObjectProperty(propertyName).toString()));
/*     */       }
/*     */     
/* 156 */     } catch (JMSException e) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "getReference()", (Throwable)e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl", "getReference()", "getter", reference);
/*     */     }
/*     */     
/* 169 */     return reference;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsJndiDestinationImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */