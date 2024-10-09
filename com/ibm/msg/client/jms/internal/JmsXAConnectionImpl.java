/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsXAConnection;
/*     */ import com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnection;
/*     */ import com.ibm.msg.client.provider.ProviderXASession;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.XASession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsXAConnectionImpl
/*     */   extends JmsConnectionImpl
/*     */   implements JmsXAConnection
/*     */ {
/*     */   private static final String enableMaxMessagesForXAListenerPortsProperty = "com.ibm.mq.jms.tuning.enableMaxMessagesForXAListenerPorts";
/*     */   private static final long serialVersionUID = 7671947122063053105L;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXAConnectionImpl.java");
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
/*     */     
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "static()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  74 */     PropertyStore.register("com.ibm.mq.jms.tuning.enableMaxMessagesForXAListenerPorts", false);
/*     */ 
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "static()");
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
/*     */   public JmsXAConnectionImpl(JmsConnectionFactoryImpl connectionFactory) throws JMSException {
/*  93 */     super(connectionFactory);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "<init>(JmsConnectionFactoryImpl)", new Object[] { connectionFactory });
/*     */     }
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "<init>(JmsConnectionFactoryImpl)");
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
/*     */   public XASession createXASession() throws JMSException {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createXASession()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 118 */     this.state.checkNotClosed("JMSCC0008");
/*     */ 
/*     */     
/* 121 */     fixClientID();
/*     */     
/* 123 */     JmsXASessionImpl xaSession = instantiateXASession();
/*     */     
/* 125 */     ProviderXASession providerSession = getProviderXAConnection().createXASession(xaSession);
/* 126 */     xaSession.setProviderSession((ProviderSession)providerSession);
/*     */     
/* 128 */     synchronized (this.state) {
/*     */ 
/*     */ 
/*     */       
/* 132 */       if (this.state.equals(1)) {
/* 133 */         xaSession.start();
/*     */       }
/* 135 */       this.sessions.add(xaSession);
/*     */     } 
/*     */     
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createXASession()", xaSession);
/*     */     }
/*     */     
/* 142 */     return (XASession)xaSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsXASessionImpl instantiateXASession() throws JMSException {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "instantiateXASession()");
/*     */     }
/*     */     
/* 157 */     JmsXASessionImpl xaSession = new JmsXASessionImpl(this);
/*     */     
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "instantiateXASession()", xaSession);
/*     */     }
/*     */     
/* 163 */     return xaSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderXAConnection getProviderXAConnection() {
/* 170 */     ProviderXAConnection pxc = (ProviderXAConnection)getProviderConnection();
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "getProviderXAConnection()", "getter", pxc);
/*     */     }
/*     */     
/* 175 */     return pxc;
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
/*     */   public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", new Object[] { destination, messageSelector, sessionPool, 
/*     */             
/* 195 */             Integer.valueOf(maxMessages) });
/*     */     }
/* 197 */     ConnectionConsumer cc = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     boolean enableMaxMessagesForXAListenerPorts = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.tuning.enableMaxMessagesForXAListenerPorts").booleanValue();
/*     */     
/* 204 */     if (enableMaxMessagesForXAListenerPorts) {
/* 205 */       if (Trace.isOn) {
/* 206 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", "Property com.ibm.mq.jms.tuning.enableMaxMessagesForXAListenerPorts has been set to true, setting Maximum Messages to " + maxMessages);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 214 */       cc = super.createConnectionConsumer(destination, messageSelector, sessionPool, maxMessages);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       if (Trace.isOn) {
/* 228 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", "Property com.ibm.mq.jms.tuning.enableMaxMessagesForXAListenerPorts has either been set to false or is unset, so setting Maximum Messages to 1");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 235 */       cc = super.createConnectionConsumer(destination, messageSelector, sessionPool, 1);
/*     */     } 
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", cc);
/*     */     }
/*     */     
/* 242 */     return cc;
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
/*     */   public ConnectionConsumer createDurableConnectionConsumer(Topic destination, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { destination, subscriptionName, messageSelector, sessionPool, 
/*     */             
/* 264 */             Integer.valueOf(maxMessages) });
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
/* 276 */     ConnectionConsumer cc = super.createDurableConnectionConsumer(destination, subscriptionName, messageSelector, sessionPool, 1);
/*     */     
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", cc);
/*     */     }
/*     */     
/* 282 */     return cc;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXAConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */