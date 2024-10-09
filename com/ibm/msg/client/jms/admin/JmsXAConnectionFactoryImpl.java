/*     */ package com.ibm.msg.client.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConstants;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsXAConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXAContext;
/*     */ import com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsXAConnectionImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl;
/*     */ import com.ibm.msg.client.provider.ProviderConnection;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnection;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnectionFactory;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.XAConnection;
/*     */ import javax.jms.XAJMSContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsXAConnectionFactoryImpl
/*     */   extends JmsConnectionFactoryImpl
/*     */   implements JmsXAConnectionFactory
/*     */ {
/*     */   private static final long serialVersionUID = 4123977461522689892L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsXAConnectionFactoryImpl.java";
/*     */   protected ProviderXAConnectionFactory providerXAConnectionFactory;
/*     */   private String connectionTypeName;
/*     */   
/*     */   static {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.data("com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsXAConnectionFactoryImpl.java");
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
/*     */   public JmsXAConnectionFactoryImpl() {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "<init>()");
/*     */     }
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsXAConnectionFactoryImpl(String connectionTypeName) throws JMSException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "<init>(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */     
/* 103 */     setStringProperty("XMSC_CONNECTION_TYPE_NAME", connectionTypeName);
/*     */ 
/*     */     
/* 106 */     boolean found = false;
/* 107 */     for (int count = 0; count < JmsConstants.providerNames.length; count++) {
/* 108 */       if (JmsConstants.providerNames[count].equalsIgnoreCase(connectionTypeName)) {
/* 109 */         setIntProperty("XMSC_CONNECTION_TYPE", count);
/* 110 */         found = true;
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 118 */     if (!found) {
/* 119 */       setIntProperty("XMSC_CONNECTION_TYPE", -1);
/*     */       
/* 121 */       HashMap<String, String> data = new HashMap<>();
/* 122 */       data.put("XMSC_CONNECTION_TYPE_NAME", connectionTypeName);
/* 123 */       data.put("MSG", "Unkown connection type name");
/*     */       
/* 125 */       Trace.ffst("JmsXAConnectionFactoryImpl", "<init>String providerName)", "", data, JMSException.class);
/*     */     } 
/*     */ 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setProviderFactory() throws JMSException {
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "setProviderFactory()");
/*     */     }
/*     */ 
/*     */     
/* 145 */     if (this.providerFactory == null) {
/*     */       
/* 147 */       this.connectionTypeName = getStringProperty("XMSC_CONNECTION_TYPE_NAME");
/*     */       
/* 149 */       this
/* 150 */         .providerFactory = ((JmsFactoryFactoryImpl)JmsFactoryFactoryImpl.getInstance(this.connectionTypeName)).getProviderFactoryFactory();
/*     */ 
/*     */       
/* 153 */       ProviderXAConnectionFactory providerXACF = this.providerFactory.createProviderXAConnectionFactory((JmsPropertyContext)this);
/* 154 */       setProviderXAConnectionFactory(providerXACF);
/*     */     } 
/*     */     
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "setProviderFactory()");
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
/*     */   private void setProviderXAConnectionFactory(ProviderXAConnectionFactory providerXACF) throws JMSException {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "setProviderXAConnectionFactory(ProviderXAConnectionFactory)", "setter", providerXACF);
/*     */     }
/*     */ 
/*     */     
/* 175 */     this.providerXAConnectionFactory = providerXACF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAConnection createXAConnection() throws JMSException {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAConnection()");
/*     */     }
/*     */ 
/*     */     
/* 189 */     XAConnection traceRet1 = createXAConnection((String)null, (String)null);
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAConnection()", traceRet1);
/*     */     }
/*     */     
/* 194 */     return traceRet1;
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
/*     */   public XAConnection createXAConnection(String userID, String password) throws JMSException {
/*     */     JmsXAConnectionImpl connection;
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAConnection(String,String)", new Object[] { userID, (password == null) ? password : 
/*     */ 
/*     */             
/* 212 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */     
/* 215 */     setProviderFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     if (this instanceof javax.jms.QueueConnectionFactory) {
/* 224 */       JmsXAQueueConnectionImpl jmsXAQueueConnectionImpl = new JmsXAQueueConnectionImpl(this);
/* 225 */     } else if (this instanceof javax.jms.TopicConnectionFactory) {
/* 226 */       JmsXATopicConnectionImpl jmsXATopicConnectionImpl = new JmsXATopicConnectionImpl(this);
/*     */     } else {
/* 228 */       connection = new JmsXAConnectionImpl(this);
/*     */     } 
/*     */     
/* 231 */     if (userID != null) {
/* 232 */       connection.setStringProperty("XMSC_USERID", userID);
/*     */     }
/* 234 */     if (password != null) {
/* 235 */       connection.setStringProperty("XMSC_PASSWORD", password);
/*     */     }
/*     */ 
/*     */     
/* 239 */     ProviderXAConnection providerXAConnection = this.providerXAConnectionFactory.createProviderXAConnection((JmsPropertyContext)connection);
/* 240 */     connection.setProviderConnection((ProviderConnection)providerXAConnection);
/*     */     
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAConnection(String,String)", connection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 248 */     return (XAConnection)connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAJMSContext createXAContext() {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAContext()");
/*     */     }
/*     */     
/* 263 */     JmsXAContext context = createXAContextInternal((String)null, (String)null, 1);
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAContext()", context);
/*     */     }
/*     */     
/* 269 */     return (XAJMSContext)context;
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
/*     */   public XAJMSContext createXAContext(String userName, String password) {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAContext(String,String)", new Object[] { userName, (password == null) ? password : 
/*     */ 
/*     */             
/* 285 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */     
/* 288 */     JmsXAContext context = createXAContextInternal(userName, password, 1);
/*     */     
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsXAConnectionFactoryImpl", "createXAContext(String,String)", context);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 296 */     return (XAJMSContext)context;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsXAConnectionFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */