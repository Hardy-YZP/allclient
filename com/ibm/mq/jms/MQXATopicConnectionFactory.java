/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsXAContext;
/*     */ import com.ibm.msg.client.jms.JmsXATopicConnectionFactory;
/*     */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.JMSSecurityRuntimeException;
/*     */ import javax.jms.XAConnection;
/*     */ import javax.jms.XAJMSContext;
/*     */ import javax.jms.XATopicConnection;
/*     */ import javax.jms.XATopicConnectionFactory;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXATopicConnectionFactory
/*     */   extends MQTopicConnectionFactory
/*     */   implements JmsXATopicConnectionFactory, XATopicConnectionFactory
/*     */ {
/*     */   static final long serialVersionUID = 2487052079664660562L;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.jms.MQXATopicConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXATopicConnectionFactory.java");
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
/*     */   public MQXATopicConnectionFactory() {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "<init>()");
/*     */     }
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "<init>()");
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
/*     */   public XAConnection createXAConnection() throws JMSException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAConnection()");
/*     */     }
/*  98 */     XATopicConnection xATopicConnection = createXATopicConnection((String)null, (String)null);
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAConnection()", xATopicConnection);
/*     */     }
/*     */     
/* 103 */     return (XAConnection)xATopicConnection;
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
/*     */   public XAConnection createXAConnection(String userid, String password) throws JMSException {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 121 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */     
/* 124 */     XATopicConnection xATopicConnection = createXATopicConnection(userid, password);
/*     */     
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAConnection(String,String)", xATopicConnection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 132 */     return (XAConnection)xATopicConnection;
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
/*     */   public XATopicConnection createXATopicConnection() throws JMSException {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXATopicConnection()");
/*     */     }
/*     */ 
/*     */     
/* 154 */     checkXASupported();
/*     */     
/* 156 */     MQXATopicConnection wrapper = new MQXATopicConnection();
/* 157 */     wrapper.setDelegate(createXAConnectionInternal(null, null));
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXATopicConnection()", wrapper);
/*     */     }
/*     */     
/* 162 */     return wrapper;
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
/*     */   public XATopicConnection createXATopicConnection(String userid, String password) throws JMSException {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXATopicConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 185 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */ 
/*     */     
/* 189 */     checkXASupported();
/*     */     
/* 191 */     MQXATopicConnection wrapper = new MQXATopicConnection();
/* 192 */     wrapper.setDelegate(createXAConnectionInternal(userid, password));
/*     */     
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXATopicConnection(String,String)", wrapper);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 200 */     return wrapper;
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
/* 213 */     Reference ref = new Reference(getClass().getName(), MQXATopicConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 216 */     Reference superClassReference = super.getReference();
/* 217 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 218 */     while (e.hasMoreElements()) {
/* 219 */       RefAddr currentRefAddr = e.nextElement();
/* 220 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/*     */     
/* 227 */     return ref;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAJMSContext createXAContext() throws JMSRuntimeException, JMSSecurityRuntimeException {
/*     */     JmsXAContext jmsXAContext;
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext()");
/*     */     }
/*     */     
/* 243 */     XAJMSContext context = null;
/*     */ 
/*     */     
/*     */     try {
/* 247 */       checkXASupported();
/*     */       
/* 249 */       jmsXAContext = createXAContextInternal(null, null, 1);
/*     */     
/*     */     }
/* 252 */     catch (JMSException je) {
/* 253 */       if (Trace.isOn) {
/* 254 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext()", (Throwable)je);
/*     */       }
/*     */       
/* 257 */       JmsErrorUtils.convertJMSException(je);
/*     */     } 
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext()", jmsXAContext);
/*     */     }
/* 262 */     return (XAJMSContext)jmsXAContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAJMSContext createXAContext(String userName, String password) throws JMSRuntimeException, JMSSecurityRuntimeException {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext(String,String)", new Object[] { userName, "************" });
/*     */     }
/*     */     
/* 275 */     XAJMSContext traceRet1 = createXAContext(userName, password, 1);
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext(String,String)", traceRet1);
/*     */     }
/*     */     
/* 280 */     return traceRet1;
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
/*     */   public XAJMSContext createXAContext(String userName, String password, int sessionMode) throws JMSRuntimeException, JMSSecurityRuntimeException {
/*     */     JmsXAContext jmsXAContext;
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext(String,String,int)", new Object[] { userName, "************", 
/*     */             
/* 297 */             Integer.valueOf(sessionMode) });
/*     */     }
/*     */     
/* 300 */     XAJMSContext context = null;
/*     */ 
/*     */     
/*     */     try {
/* 304 */       checkXASupported();
/*     */       
/* 306 */       jmsXAContext = createXAContextInternal(userName, password, sessionMode);
/*     */     
/*     */     }
/* 309 */     catch (JMSException je) {
/* 310 */       if (Trace.isOn) {
/* 311 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext(String,String,int)", (Throwable)je);
/*     */       }
/*     */       
/* 314 */       JmsErrorUtils.convertJMSException(je);
/*     */     } 
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactory", "createXAContext(String,String,int)", jmsXAContext);
/*     */     }
/*     */     
/* 320 */     return (XAJMSContext)jmsXAContext;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXATopicConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */