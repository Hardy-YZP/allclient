/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionBrowser;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsMessageReferenceHandler;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import com.ibm.msg.client.provider.ProviderConnectionBrowser;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
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
/*     */ public class JmsConnectionBrowserImpl
/*     */   extends JmsPropertyContextImpl
/*     */   implements JmsConnectionBrowser
/*     */ {
/*     */   private static final long serialVersionUID = -3527307861058577428L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionBrowserImpl.java";
/*     */   private JmsDestination destination;
/*     */   private JmsMessageReferenceHandler messageRefHandler;
/*     */   private ProviderConnectionBrowser providerConnectionBrowser;
/*     */   private JmsConnectionImpl connection;
/*     */   private boolean shared;
/*     */   private boolean durable;
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionBrowserImpl.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private State state = new State(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionBrowserImpl(JmsConnectionImpl conn, JmsDestination destination, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean shared, boolean durable) {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "<init>(JmsConnectionImpl,JmsDestination,String,JmsMessageReferenceHandler,int,boolean,boolean)", new Object[] { conn, destination, selector, messageRefHandler, 
/*     */ 
/*     */             
/*  93 */             Integer.valueOf(quantityHint), Boolean.valueOf(shared), Boolean.valueOf(durable) });
/*     */     }
/*     */     
/*  96 */     this.destination = destination;
/*  97 */     this.messageRefHandler = messageRefHandler;
/*  98 */     this.connection = conn;
/*  99 */     this.shared = shared;
/* 100 */     this.durable = durable;
/*     */ 
/*     */     
/*     */     try {
/* 104 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*     */     }
/* 106 */     catch (JMSException e) {
/* 107 */       if (Trace.isOn) {
/* 108 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "<init>(JmsConnectionImpl,JmsDestination,String,JmsMessageReferenceHandler,int,boolean,boolean)", "Caught expected exception", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "<init>(JmsConnectionImpl,JmsDestination,String,JmsMessageReferenceHandler,int,boolean,boolean)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionBrowserImpl(JmsConnectionImpl conn, JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal, boolean shared, boolean durable) {
/* 145 */     this(conn, (JmsDestination)topic, selector, messageRefHandler, quantityHint, shared, durable);
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "<init>(JmsConnectionImpl,JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean,boolean,boolean)", new Object[] { conn, topic, subName, selector, messageRefHandler, 
/*     */ 
/*     */             
/* 150 */             Integer.valueOf(quantityHint), Boolean.valueOf(noLocal), Boolean.valueOf(shared), 
/* 151 */             Boolean.valueOf(durable) });
/*     */     }
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "<init>(JmsConnectionImpl,JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean,boolean,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void start() throws JMSException {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "start()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 169 */     this.providerConnectionBrowser.start();
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void stop() throws JMSException {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "stop()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     this.providerConnectionBrowser.stop();
/*     */ 
/*     */     
/* 188 */     this.messageRefHandler.endDeliver();
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "stop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JMSException {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "close()");
/*     */     }
/* 204 */     close(false);
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "close()");
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
/*     */   public void close(boolean closingFromConnection) throws JMSException {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "close(boolean)", new Object[] {
/* 220 */             Boolean.valueOf(closingFromConnection)
/*     */           });
/*     */     }
/* 223 */     if (this.state.close()) {
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "close(boolean)", 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 234 */     this.connection.removeConnectionBrowser(this);
/*     */ 
/*     */ 
/*     */     
/* 238 */     JmsDestinationImplProxy.decrementUseCount((JmsDestinationImpl)this.destination);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     stop();
/*     */     
/* 249 */     this.state.setState(3);
/* 250 */     this.providerConnectionBrowser.close(closingFromConnection);
/*     */     
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "close(boolean)", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProviderConnectionBrowser getProviderConnectionBrowser() {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "getProviderConnectionBrowser()", "getter", this.providerConnectionBrowser);
/*     */     }
/*     */     
/* 268 */     return this.providerConnectionBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProviderConnectionBrowser(ProviderConnectionBrowser providerConnectionBrowser) {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "setProviderConnectionBrowser(ProviderConnectionBrowser)", "setter", providerConnectionBrowser);
/*     */     }
/*     */ 
/*     */     
/* 281 */     this.providerConnectionBrowser = providerConnectionBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 291 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnectionBrowser");
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 297 */     throw traceRet1;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 305 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnectionBrowser");
/*     */     
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionBrowserImpl", "readObject(ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 311 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsConnectionBrowserImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */