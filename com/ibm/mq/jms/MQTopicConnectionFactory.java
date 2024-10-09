/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopicConnectionFactory;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.TopicConnection;
/*     */ import javax.jms.TopicConnectionFactory;
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
/*     */ public class MQTopicConnectionFactory
/*     */   extends MQConnectionFactory
/*     */   implements TopicConnectionFactory, JmsTopicConnectionFactory, Referenceable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 4166164173764320001L;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.jms.MQTopicConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicConnectionFactory.java");
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
/*     */   public MQTopicConnectionFactory() {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "<init>()");
/*     */     }
/*     */     
/*     */     try {
/*  75 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)18);
/*     */     
/*     */     }
/*  78 */     catch (JMSException e) {
/*  79 */       if (Trace.isOn) {
/*  80 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*  82 */       RuntimeException traceRet1 = new RuntimeException((Throwable)e);
/*  83 */       if (Trace.isOn) {
/*  84 */         Trace.throwing(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "<init>()", traceRet1);
/*     */       }
/*  86 */       throw traceRet1;
/*     */     } 
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "<init>()");
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
/*     */   public TopicConnection createTopicConnection() throws JMSException {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createTopicConnection()");
/*     */     }
/* 109 */     MQTopicConnection wrapper = new MQTopicConnection();
/* 110 */     wrapper.setDelegate(createCommonConnection((String)null, (String)null));
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createTopicConnection()", wrapper);
/*     */     }
/*     */     
/* 115 */     return wrapper;
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
/*     */   public TopicConnection createTopicConnection(String userid, String password) throws JMSException {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createTopicConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 139 */             Integer.valueOf(password.length()) });
/*     */     }
/* 141 */     MQTopicConnection wrapper = new MQTopicConnection();
/* 142 */     wrapper.setDelegate(createCommonConnection(userid, password));
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createTopicConnection(String,String)", wrapper);
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
/* 156 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
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
/* 167 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)18);
/*     */     
/*     */     }
/* 170 */     catch (JMSException e) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "readObject(java.io.ObjectInputStream)", (Throwable)e);
/*     */       }
/*     */       
/* 175 */       Trace.ffst(this, "readObject", "XF00C001", null, JMSException.class);
/*     */     } 
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "readObject(java.io.ObjectInputStream)");
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
/*     */   public Connection createConnection() throws JMSException {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createConnection()");
/*     */     }
/* 199 */     TopicConnection topicConnection = createTopicConnection();
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createConnection()", topicConnection);
/*     */     }
/*     */     
/* 204 */     return (Connection)topicConnection;
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
/*     */   public Connection createConnection(String userID, String password) throws JMSException {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createConnection(String,String)", new Object[] { userID, (password == null) ? password : 
/*     */ 
/*     */             
/* 224 */             Integer.valueOf(password.length()) });
/*     */     }
/* 226 */     TopicConnection topicConnection = createTopicConnection(userID, password);
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "createConnection(String,String)", topicConnection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 234 */     return (Connection)topicConnection;
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
/*     */   public Reference getReference() throws NamingException {
/* 247 */     Reference ref = new Reference(getClass().getName(), MQTopicConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 250 */     Reference superClassReference = super.getReference();
/* 251 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 252 */     while (e.hasMoreElements()) {
/* 253 */       RefAddr currentRefAddr = e.nextElement();
/* 254 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.data(this, "com.ibm.mq.jms.MQTopicConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/*     */     
/* 261 */     return ref;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */