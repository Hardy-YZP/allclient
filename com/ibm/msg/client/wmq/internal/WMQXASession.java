/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiXA;
/*     */ import com.ibm.mq.jmqi.JmqiXAResource;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderXASession;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.jms.JMSException;
/*     */ import javax.transaction.xa.XAException;
/*     */ import javax.transaction.xa.XAResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQXASession
/*     */   extends WMQSession
/*     */   implements ProviderXASession
/*     */ {
/*     */   private static final long serialVersionUID = 5763142128194608714L;
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQXASession.java";
/*     */   private transient JmqiXAResource xar;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQXASession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQXASession.java");
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
/*  72 */   private AtomicBoolean closed = new AtomicBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WMQXASession(WMQConnection connection, JmsPropertyContext propertyContext) throws JMSException {
/*  83 */     super(connection, 0, propertyContext);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "<init>(WMQConnection,JmsPropertyContext)", new Object[] { connection, propertyContext });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  94 */       JmqiXA jmqiXa = (JmqiXA)getJmqiMQ();
/*  95 */       Hconn hconn = getHconn();
/*  96 */       JmqiEnvironment jmqiEnv = getJmqiEnvironment();
/*  97 */       this.xar = jmqiEnv.newJmqiXAResource(jmqiXa, hconn);
/*     */     }
/*  99 */     catch (XAException xae) {
/* 100 */       if (Trace.isOn) {
/* 101 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "<init>(WMQConnection,JmsPropertyContext)", xae, 1);
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 106 */         close(false);
/*     */       }
/* 108 */       catch (JMSException jMSException) {
/* 109 */         if (Trace.isOn) {
/* 110 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "<init>(WMQConnection,JmsPropertyContext)", (Throwable)jMSException, 2);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       JMSException je = (JMSException)NLSServices.createException("JMSWMQ1068", null);
/*     */       
/* 121 */       je.setLinkedException(xae);
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "<init>(WMQConnection,JmsPropertyContext)", (Throwable)je);
/*     */       }
/*     */       
/* 126 */       throw je;
/*     */     } 
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "<init>(WMQConnection,JmsPropertyContext)");
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
/* 141 */     if (Trace.isOn)
/* 142 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "close(boolean)", new Object[] {
/* 143 */             Boolean.valueOf(closingFromConnection)
/*     */           }); 
/* 145 */     if (this.closed.get()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "close(boolean)", 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     if (this.xar != null) {
/*     */       try {
/* 167 */         this.xar.close();
/*     */       }
/* 169 */       catch (XAException xae) {
/* 170 */         if (Trace.isOn) {
/* 171 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "close(boolean)", xae);
/*     */         }
/*     */         
/* 174 */         JMSException je = (JMSException)NLSServices.createException("JMSWMQ2012", null);
/*     */         
/* 176 */         je.setLinkedException(xae);
/* 177 */         if (Trace.isOn) {
/* 178 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "close(boolean)", (Throwable)je);
/*     */         }
/*     */         
/* 181 */         throw je;
/*     */       } finally {
/*     */         
/* 184 */         if (Trace.isOn) {
/* 185 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "close(boolean)");
/*     */         }
/*     */         
/* 188 */         this.xar = null;
/* 189 */         this.closed.getAndSet(true);
/*     */ 
/*     */ 
/*     */         
/* 193 */         super.close(closingFromConnection);
/*     */       } 
/*     */     }
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "close(boolean)", 2);
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
/*     */   public void commit() throws JMSException {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "commit()");
/*     */     }
/* 213 */     Trace.ffst(this, "commit", "XN00I001", null, null);
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "commit()");
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
/*     */   public XAResource getXAResource() throws JMSException {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "getXAResource()", "getter", this.xar);
/*     */     }
/*     */     
/* 231 */     return (XAResource)this.xar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXASessionActive() {
/* 241 */     boolean isActive = false;
/* 242 */     if (this.xar != null) {
/* 243 */       isActive = this.xar.getActiveState();
/*     */     }
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "isXASessionActive()", "getter", 
/* 247 */           Boolean.valueOf(isActive));
/*     */     }
/* 249 */     return isActive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rollback() throws JMSException {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "rollback()");
/*     */     }
/* 263 */     Trace.ffst(this, "rollback", "XN00I002", null, null);
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "rollback()");
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
/*     */   public void syncpoint(boolean commit, boolean forceTransactedCommit, WMQDestination destination) {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "syncpoint(boolean,boolean,WMQDestination)", new Object[] {
/* 286 */             Boolean.valueOf(commit), 
/* 287 */             Boolean.valueOf(forceTransactedCommit), destination
/*     */           });
/*     */     }
/* 290 */     if (Trace.isOn)
/* 291 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXASession", "syncpoint(boolean,boolean,WMQDestination)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQXASession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */