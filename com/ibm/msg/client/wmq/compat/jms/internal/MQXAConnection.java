/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiXAResource;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnection;
/*     */ import com.ibm.msg.client.provider.ProviderXASession;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager;
/*     */ import javax.jms.JMSException;
/*     */ import javax.transaction.xa.XAException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXAConnection
/*     */   extends MQConnection
/*     */   implements ProviderXAConnection
/*     */ {
/*     */   private static final long serialVersionUID = 3871567461900448615L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQXAConnection.java";
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQXAConnection.java");
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
/*     */   public MQXAConnection(String username, String password, JmsPropertyContext connectionProps) throws JMSException {
/*  89 */     super(username, password, connectionProps);
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "<init>(String,String,JmsPropertyContext)", new Object[] { username, (password == null) ? password : 
/*     */ 
/*     */             
/*  95 */             Integer.valueOf(password.length()), connectionProps });
/*     */       
/*  97 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "<init>(String,String,JmsPropertyContext)");
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
/*     */   public ProviderXASession createXASession(JmsPropertyContext properties) throws JMSException {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "createXASession(JmsPropertyContext)", new Object[] { properties });
/*     */     }
/*     */ 
/*     */     
/* 123 */     MQSession ts = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 128 */       ts = (MQSession)createSessionInternal(true, 0, 1);
/*     */ 
/*     */       
/* 131 */       MQQueueManager qm = ts.getQM();
/*     */ 
/*     */       
/* 134 */       MQXAQueueManager xaqm = new MQXAQueueManager(qm);
/*     */       
/* 136 */       JmqiXAResource xar = xaqm.getXAResource();
/*     */ 
/*     */       
/* 139 */       MQXASession xats = new MQXASession(this, qm, ts, xar);
/* 140 */       xats.setPropertyContext(properties);
/*     */ 
/*     */       
/* 143 */       xats.setDistTransactionMode(1);
/*     */       
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "createXASession(JmsPropertyContext)", xats);
/*     */       }
/*     */       
/* 149 */       return xats;
/*     */     
/*     */     }
/* 152 */     catch (XAException e) {
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "createXASession(JmsPropertyContext)", e);
/*     */       }
/*     */       
/* 157 */       JMSException je = ConfigEnvironment.newException("MQJMS1068");
/* 158 */       je.setLinkedException(e);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.traceData(this, "Disabling XA transaction mode on MQSession", null);
/*     */       }
/* 166 */       ts.setDistTransactionMode(0);
/*     */ 
/*     */       
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "createXASession(JmsPropertyContext)", (Throwable)je);
/*     */       }
/*     */       
/* 173 */       throw je;
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
/*     */   public ProviderSession createSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 186 */     if (Trace.isOn)
/* 187 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "createSession(boolean,int)", new Object[] {
/* 188 */             Boolean.valueOf(transacted), 
/* 189 */             Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 191 */     ProviderSession traceRet1 = createSessionInternal(transacted, acknowledgeMode, 0);
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection", "createSession(boolean,int)", traceRet1);
/*     */     }
/*     */     
/* 196 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQXAConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */