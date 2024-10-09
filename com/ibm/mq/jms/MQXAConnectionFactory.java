/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsXAConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXAContext;
/*     */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.JMSSecurityRuntimeException;
/*     */ import javax.jms.XAConnection;
/*     */ import javax.jms.XAConnectionFactory;
/*     */ import javax.jms.XAJMSContext;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXAConnectionFactory
/*     */   extends MQConnectionFactory
/*     */   implements JmsXAConnectionFactory, XAConnectionFactory
/*     */ {
/*     */   static final long serialVersionUID = 6683181249198063318L;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.mq.jms.MQXAConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAConnectionFactory.java");
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
/*     */   public MQXAConnectionFactory() {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactory", "<init>()");
/*     */     }
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactory", "<init>()");
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
/*     */   public XAConnection createXAConnection() throws JMSException {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAConnection()");
/*     */     }
/*     */ 
/*     */     
/* 101 */     checkXASupported();
/*     */     
/* 103 */     MQXAConnection wrapper = new MQXAConnection();
/* 104 */     wrapper.setDelegate(createXAConnectionInternal(null, null));
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAConnection()", wrapper);
/*     */     }
/* 108 */     return wrapper;
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
/*     */   public XAConnection createXAConnection(String userid, String password) throws JMSException {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAConnection(String,String)", new Object[] { userid, (password == null) ? password : 
/*     */ 
/*     */             
/* 130 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */ 
/*     */     
/* 134 */     checkXASupported();
/*     */     
/* 136 */     MQXAConnection wrapper = new MQXAConnection();
/* 137 */     wrapper.setDelegate(createXAConnectionInternal(userid, password));
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAConnection(String,String)", wrapper);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 145 */     return wrapper;
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
/* 160 */     Reference ref = new Reference(getClass().getName(), MQXAConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 163 */     Reference superClassReference = super.getReference();
/* 164 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 165 */     while (e.hasMoreElements()) {
/* 166 */       RefAddr currentRefAddr = e.nextElement();
/* 167 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.mq.jms.MQXAConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/* 173 */     return ref;
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
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext()");
/*     */     }
/*     */     
/* 189 */     XAJMSContext context = null;
/*     */ 
/*     */     
/*     */     try {
/* 193 */       checkXASupported();
/*     */       
/* 195 */       jmsXAContext = createXAContextInternal(null, null, 1);
/*     */     
/*     */     }
/* 198 */     catch (JMSException je) {
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext()", (Throwable)je);
/*     */       }
/* 202 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.throwing(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext()", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 207 */       throw traceRet1;
/*     */     } 
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext()", jmsXAContext);
/*     */     }
/* 212 */     return (XAJMSContext)jmsXAContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAJMSContext createXAContext(String userName, String password) throws JMSRuntimeException, JMSSecurityRuntimeException {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext(String,String)", new Object[] { userName, "************" });
/*     */     }
/*     */     
/* 225 */     XAJMSContext context = createXAContext(userName, password, 1);
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext(String,String)", context);
/*     */     }
/*     */     
/* 230 */     return context;
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
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext(String,String,int)", new Object[] { userName, "************", 
/*     */             
/* 247 */             Integer.valueOf(sessionMode) });
/*     */     }
/*     */     
/* 250 */     XAJMSContext context = null;
/*     */ 
/*     */     
/*     */     try {
/* 254 */       checkXASupported();
/*     */       
/* 256 */       jmsXAContext = createXAContextInternal(userName, password, sessionMode);
/*     */     
/*     */     }
/* 259 */     catch (JMSException je) {
/* 260 */       if (Trace.isOn) {
/* 261 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext(String,String,int)", (Throwable)je);
/*     */       }
/*     */       
/* 264 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 265 */       if (Trace.isOn) {
/* 266 */         Trace.throwing(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext(String,String,int)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 269 */       throw traceRet1;
/*     */     } 
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactory", "createXAContext(String,String,int)", jmsXAContext);
/*     */     }
/*     */     
/* 275 */     return (XAJMSContext)jmsXAContext;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXAConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */