/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsXAContext;
/*     */ import com.ibm.msg.client.jms.JmsXAQueueConnectionFactory;
/*     */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.JMSSecurityRuntimeException;
/*     */ import javax.jms.XAConnection;
/*     */ import javax.jms.XAJMSContext;
/*     */ import javax.jms.XAQueueConnection;
/*     */ import javax.jms.XAQueueConnectionFactory;
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
/*     */ public class MQXAQueueConnectionFactory
/*     */   extends MQQueueConnectionFactory
/*     */   implements JmsXAQueueConnectionFactory, XAQueueConnectionFactory
/*     */ {
/*     */   static final long serialVersionUID = -9036046242347141166L;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.jms.MQXAQueueConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAQueueConnectionFactory.java");
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
/*     */   public MQXAQueueConnectionFactory() {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "<init>()");
/*     */     }
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "<init>()");
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
/*     */   public XAConnection createXAConnection() throws JMSException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAConnection()");
/*     */     }
/*  94 */     XAQueueConnection xAQueueConnection = createXAQueueConnection();
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAConnection()", xAQueueConnection);
/*     */     }
/*     */     
/*  99 */     return (XAConnection)xAQueueConnection;
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
/*     */   public XAConnection createXAConnection(String userid, String password) throws JMSException {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 119 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */     
/* 122 */     XAQueueConnection xAQueueConnection = createXAQueueConnection(userid, password);
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAConnection(String,String)", xAQueueConnection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 130 */     return (XAConnection)xAQueueConnection;
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
/*     */   public XAQueueConnection createXAQueueConnection() throws JMSException {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAQueueConnection()");
/*     */     }
/*     */ 
/*     */     
/* 149 */     checkXASupported();
/*     */     
/* 151 */     MQXAQueueConnection wrapper = new MQXAQueueConnection();
/* 152 */     wrapper.setDelegate(createXAConnectionInternal(null, null));
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAQueueConnection()", wrapper);
/*     */     }
/*     */     
/* 157 */     return wrapper;
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
/*     */   public XAQueueConnection createXAQueueConnection(String userid, String password) throws JMSException {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAQueueConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 180 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */ 
/*     */     
/* 184 */     checkXASupported();
/*     */     
/* 186 */     MQXAQueueConnection wrapper = new MQXAQueueConnection();
/* 187 */     wrapper.setDelegate(createXAConnectionInternal(userid, password));
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAQueueConnection(String,String)", wrapper);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 195 */     return wrapper;
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
/*     */   public Reference getReference() throws NamingException {
/* 210 */     Reference ref = new Reference(getClass().getName(), MQXAQueueConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 213 */     Reference superClassReference = super.getReference();
/* 214 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 215 */     while (e.hasMoreElements()) {
/* 216 */       RefAddr currentRefAddr = e.nextElement();
/* 217 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/*     */     
/* 224 */     return ref;
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
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext()");
/*     */     }
/*     */     
/* 240 */     XAJMSContext context = null;
/*     */ 
/*     */     
/*     */     try {
/* 244 */       checkXASupported();
/*     */       
/* 246 */       jmsXAContext = createXAContextInternal(null, null, 1);
/*     */     
/*     */     }
/* 249 */     catch (JMSException je) {
/* 250 */       if (Trace.isOn) {
/* 251 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext()", (Throwable)je);
/*     */       }
/*     */       
/* 254 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.throwing(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext()", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 259 */       throw traceRet1;
/*     */     } 
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext()", jmsXAContext);
/*     */     }
/* 264 */     return (XAJMSContext)jmsXAContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAJMSContext createXAContext(String userName, String password) throws JMSRuntimeException, JMSSecurityRuntimeException {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext(String,String)", new Object[] { userName, "************" });
/*     */     }
/*     */     
/* 277 */     XAJMSContext traceRet1 = createXAContext(userName, password, 1);
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext(String,String)", traceRet1);
/*     */     }
/*     */     
/* 282 */     return traceRet1;
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
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext(String,String,int)", new Object[] { userName, "************", 
/*     */             
/* 299 */             Integer.valueOf(sessionMode) });
/*     */     }
/*     */     
/* 302 */     XAJMSContext context = null;
/*     */ 
/*     */     
/*     */     try {
/* 306 */       checkXASupported();
/*     */       
/* 308 */       jmsXAContext = createXAContextInternal(userName, password, sessionMode);
/*     */     
/*     */     }
/* 311 */     catch (JMSException je) {
/* 312 */       if (Trace.isOn) {
/* 313 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext(String,String,int)", (Throwable)je);
/*     */       }
/*     */       
/* 316 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 317 */       if (Trace.isOn) {
/* 318 */         Trace.throwing(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext(String,String,int)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 321 */       throw traceRet1;
/*     */     } 
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnectionFactory", "createXAContext(String,String,int)", jmsXAContext);
/*     */     }
/*     */     
/* 327 */     return (XAJMSContext)jmsXAContext;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXAQueueConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */