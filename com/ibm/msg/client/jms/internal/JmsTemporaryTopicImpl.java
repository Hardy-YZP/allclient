/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnection;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsTemporaryTopic;
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
/*     */ public class JmsTemporaryTopicImpl
/*     */   extends JmsDestinationImpl
/*     */   implements JmsTemporaryTopic
/*     */ {
/*     */   private static final long serialVersionUID = -300548482610122875L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTemporaryTopicImpl.java";
/*     */   private JmsConnection connection;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTemporaryTopicImpl.java");
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
/*     */   protected JmsTemporaryTopicImpl(JmsConnection connection, ProviderSession providerSession) throws JMSException {
/*  70 */     super(providerSession.getStringProperty("XMSC_CONNECTION_TYPE_NAME"), null);
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "<init>(JmsConnection,ProviderSession)", new Object[] { connection, providerSession });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  78 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*     */     }
/*  80 */     catch (JMSException e) {
/*  81 */       if (Trace.isOn) {
/*  82 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "<init>(JmsConnection,ProviderSession)", "Caught expected exception", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.connection = connection;
/*     */     
/*  90 */     ProviderDestination providerDest = providerSession.createTemporaryDestination(2, (JmsPropertyContext)this);
/*     */ 
/*     */     
/*  93 */     setProviderDestination(providerDest);
/*     */     
/*  95 */     setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)0);
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "<init>(JmsConnection,ProviderSession)");
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
/* 112 */     boolean result = super.equals(o);
/* 113 */     if (result && 
/* 114 */       o instanceof JmsTemporaryTopicImpl) {
/* 115 */       JmsTemporaryTopicImpl other = (JmsTemporaryTopicImpl)o;
/* 116 */       if (this.connection == null && other.connection == null) {
/* 117 */         result = true;
/* 118 */       } else if (this.connection == null && other.connection == null) {
/* 119 */         result = false;
/*     */       } else {
/* 121 */         result = this.connection.equals(other.connection);
/*     */       } 
/* 123 */       i = result & ((this.deleted == other.deleted) ? 1 : 0);
/*     */     } 
/*     */     
/* 126 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 134 */     int hashCode = super.hashCode();
/* 135 */     hashCode += this.deleted ? 31 : 0;
/* 136 */     hashCode += (this.connection == null) ? 0 : (37 * this.connection.hashCode());
/* 137 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() throws JMSException {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "delete()");
/*     */     }
/*     */     
/* 148 */     if (!this.deleted) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 154 */       if (getUseCount() > 0) {
/* 155 */         HashMap<String, Object> inserts = new HashMap<>();
/* 156 */         inserts.put("XMSC_DESTINATION_NAME", getName());
/* 157 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3025", inserts);
/*     */         
/* 159 */         if (Trace.isOn) {
/* 160 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "delete()", (Throwable)je, 1);
/*     */         }
/*     */         
/* 163 */         throw je;
/*     */       } 
/*     */ 
/*     */       
/* 167 */       ((JmsConnectionImpl)this.connection).removeTemporaryDestination((JmsDestination)this);
/*     */       
/* 169 */       ProviderDestination providerDest = getProviderDestination();
/* 170 */       if (providerDest != null) {
/* 171 */         providerDest.delete();
/*     */       }
/*     */ 
/*     */       
/* 175 */       this.deleted = true;
/*     */     } else {
/* 177 */       HashMap<String, Object> inserts = new HashMap<>();
/* 178 */       inserts.put("XMSC_DESTINATION_NAME", toString());
/* 179 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3019", inserts);
/*     */       
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "delete()", (Throwable)je, 2);
/*     */       }
/*     */       
/* 185 */       throw je;
/*     */     } 
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "delete()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTopicName() throws JMSException {
/* 200 */     String traceRet1 = getName();
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "getTopicName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 205 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsConnection getConnection() {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "getConnection()", "getter", this.connection);
/*     */     }
/*     */     
/* 215 */     return this.connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeleted() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "isDeleted()", "getter", 
/* 226 */           Boolean.valueOf(this.deleted));
/*     */     }
/* 228 */     return this.deleted;
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
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "initialise(String,String)", new Object[] { connectionTypeName1, name });
/*     */     }
/*     */     
/* 243 */     setDefaultProperties();
/*     */     
/* 245 */     if (connectionTypeName1 != null) {
/* 246 */       setConnectionTypeName(connectionTypeName1);
/*     */     }
/*     */     
/* 249 */     if (Trace.isOn)
/* 250 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl", "initialise(String,String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTemporaryTopicImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */