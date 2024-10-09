/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnection;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsTemporaryQueue;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsTemporaryQueueImpl
/*     */   extends JmsDestinationImpl
/*     */   implements JmsTemporaryQueue
/*     */ {
/*     */   private static final long serialVersionUID = -7727347894280057274L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTemporaryQueueImpl.java";
/*     */   private JmsConnection connection;
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTemporaryQueueImpl.java");
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
/*     */   private boolean deleted = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsTemporaryQueueImpl(JmsConnection connection, ProviderSession providerSession) throws JMSException {
/*  72 */     super(providerSession.getStringProperty("XMSC_CONNECTION_TYPE_NAME"), null);
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "<init>(JmsConnection,ProviderSession)", new Object[] { connection, providerSession });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  79 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*     */     }
/*  81 */     catch (JMSException e) {
/*  82 */       if (Trace.isOn) {
/*  83 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "<init>(JmsConnection,ProviderSession)", "Caught expected exception", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.connection = connection;
/*     */     
/*  91 */     ProviderDestination providerDest = providerSession.createTemporaryDestination(1, (JmsPropertyContext)this);
/*     */ 
/*     */     
/*  94 */     setProviderDestination(providerDest);
/*     */     
/*  96 */     setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)0);
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "<init>(JmsConnection,ProviderSession)");
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
/*     */   public boolean equals(Object o) {
/*     */     int i;
/* 113 */     boolean result = super.equals(o);
/* 114 */     if (result && 
/* 115 */       o instanceof JmsTemporaryQueueImpl) {
/* 116 */       JmsTemporaryQueueImpl other = (JmsTemporaryQueueImpl)o;
/* 117 */       if (this.connection == null && other.connection == null) {
/* 118 */         result = true;
/* 119 */       } else if (this.connection == null && other.connection == null) {
/* 120 */         result = false;
/*     */       } else {
/* 122 */         result = this.connection.equals(other.connection);
/*     */       } 
/* 124 */       i = result & ((this.deleted == other.deleted) ? 1 : 0);
/*     */     } 
/*     */     
/* 127 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 135 */     int hashCode = super.hashCode();
/* 136 */     hashCode += this.deleted ? 31 : 0;
/* 137 */     hashCode += (this.connection == null) ? 0 : (37 * this.connection.hashCode());
/* 138 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() throws JMSException {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "delete()");
/*     */     }
/*     */     
/* 149 */     if (!this.deleted) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       if (getUseCount() > 0) {
/* 156 */         HashMap<String, Object> inserts = new HashMap<>();
/* 157 */         inserts.put("XMSC_DESTINATION_NAME", getName());
/* 158 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3002", inserts);
/*     */         
/* 160 */         if (Trace.isOn) {
/* 161 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "delete()", (Throwable)je, 1);
/*     */         }
/*     */         
/* 164 */         throw je;
/*     */       } 
/*     */ 
/*     */       
/* 168 */       ((JmsConnectionImpl)this.connection).removeTemporaryDestination((JmsDestination)this);
/*     */       
/* 170 */       ProviderDestination providerDest = getProviderDestination();
/* 171 */       if (providerDest != null) {
/* 172 */         providerDest.delete();
/*     */       }
/*     */ 
/*     */       
/* 176 */       this.deleted = true;
/*     */     } else {
/* 178 */       HashMap<String, Object> inserts = new HashMap<>();
/* 179 */       inserts.put("XMSC_DESTINATION_NAME", toString());
/* 180 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3019", inserts);
/*     */       
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "delete()", (Throwable)je, 2);
/*     */       }
/*     */       
/* 186 */       throw je;
/*     */     } 
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "delete()");
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
/*     */   public String getQueueName() throws JMSException {
/* 202 */     String traceRet1 = getName();
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "getQueueName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 207 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsConnection getConnection() {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "getConnection()", "getter", this.connection);
/*     */     }
/*     */     
/* 217 */     return this.connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeleted() {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "isDeleted()", "getter", 
/* 228 */           Boolean.valueOf(this.deleted));
/*     */     }
/* 230 */     return this.deleted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialise(String connectionTypeName1, String name) throws JMSException {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "initialise(String,String)", new Object[] { connectionTypeName1, name });
/*     */     }
/*     */     
/* 245 */     setDefaultProperties();
/*     */     
/* 247 */     if (connectionTypeName1 != null) {
/* 248 */       setConnectionTypeName(connectionTypeName1);
/*     */     }
/*     */     
/* 251 */     if (Trace.isOn)
/* 252 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl", "initialise(String,String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTemporaryQueueImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */