/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueConnectionFactory;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.QueueConnection;
/*     */ import javax.jms.QueueConnectionFactory;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.RefAddr;
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
/*     */ public class MQQueueConnectionFactory
/*     */   extends MQConnectionFactory
/*     */   implements QueueConnectionFactory, Referenceable, Serializable, JmsQueueConnectionFactory
/*     */ {
/*     */   static final long serialVersionUID = 3763813191978525893L;
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.mq.jms.MQQueueConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueConnectionFactory.java");
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
/*     */   public MQQueueConnectionFactory() {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "<init>()");
/*     */     }
/*     */     try {
/*  77 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)17);
/*     */     
/*     */     }
/*  80 */     catch (JMSException e) {
/*  81 */       if (Trace.isOn) {
/*  82 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*  84 */       RuntimeException traceRet1 = new RuntimeException((Throwable)e);
/*  85 */       if (Trace.isOn) {
/*  86 */         Trace.throwing(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "<init>()", traceRet1);
/*     */       }
/*  88 */       throw traceRet1;
/*     */     } 
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "<init>()");
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
/*     */   public QueueConnection createQueueConnection() throws JMSException {
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createQueueConnection()");
/*     */     }
/* 110 */     MQQueueConnection wrapper = new MQQueueConnection();
/* 111 */     wrapper.setDelegate(createCommonConnection((String)null, (String)null));
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createQueueConnection()", wrapper);
/*     */     }
/*     */     
/* 116 */     return wrapper;
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
/*     */   public QueueConnection createQueueConnection(String userid, String password) throws JMSException {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createQueueConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 139 */             Integer.valueOf(password.length()) });
/*     */     }
/* 141 */     MQQueueConnection wrapper = new MQQueueConnection();
/* 142 */     wrapper.setDelegate(createCommonConnection(userid, password));
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createQueueConnection(String,String)", wrapper);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 150 */     return wrapper;
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */     
/* 160 */     in.defaultReadObject();
/*     */     
/*     */     try {
/* 163 */       setStringProperty("XMSC_CONNECTION_TYPE_NAME", "com.ibm.msg.client.wmq");
/* 164 */       setIntProperty("XMSC_CONNECTION_TYPE", 1);
/*     */ 
/*     */       
/* 167 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)17);
/*     */     
/*     */     }
/* 170 */     catch (JMSException e) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "readObject(java.io.ObjectInputStream)", (Throwable)e);
/*     */       }
/*     */       
/* 175 */       Trace.ffst(this, "readObject", "XF00B001", null, JMSException.class);
/*     */     } 
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "readObject(java.io.ObjectInputStream)");
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
/*     */   public Connection createConnection() throws JMSException {
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createConnection()");
/*     */     }
/* 200 */     QueueConnection queueConnection = createQueueConnection();
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createConnection()", queueConnection);
/*     */     }
/*     */     
/* 205 */     return (Connection)queueConnection;
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
/*     */   public Connection createConnection(String userID, String password) throws JMSException {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createConnection(String,String)", new Object[] { userID, (password == null) ? password : 
/*     */ 
/*     */             
/* 226 */             Integer.valueOf(password.length()) });
/*     */     }
/* 228 */     QueueConnection queueConnection = createQueueConnection(userID, password);
/*     */     
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "createConnection(String,String)", queueConnection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 236 */     return (Connection)queueConnection;
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
/*     */   public Reference getReference() throws NamingException {
/* 252 */     Reference ref = new Reference(getClass().getName(), MQQueueConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 255 */     Reference superClassReference = super.getReference();
/* 256 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 257 */     while (e.hasMoreElements()) {
/* 258 */       RefAddr currentRefAddr = e.nextElement();
/* 259 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.data(this, "com.ibm.mq.jms.MQQueueConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/*     */     
/* 266 */     return ref;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */