/*     */ package com.ibm.msg.client.wmq.factories;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderConnection;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnection;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnectionFactory;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.MQXAConnection;
/*     */ import com.ibm.msg.client.wmq.internal.WMQXAConnection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQXAConnectionFactory
/*     */   extends WMQConnectionFactory
/*     */   implements ProviderXAConnectionFactory
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQXAConnectionFactory.java";
/*     */   private static final long serialVersionUID = 4815162342L;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQXAConnectionFactory.java");
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
/*     */   public WMQXAConnectionFactory(JmsPropertyContext props) throws JMSException {
/*  70 */     super(props);
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "<init>(JmsPropertyContext)", new Object[] { props });
/*     */     }
/*     */     
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "<init>(JmsPropertyContext)");
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
/*     */   public ProviderXAConnection createProviderXAConnection(JmsPropertyContext properties) throws JMSException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createProviderXAConnection(JmsPropertyContext)", new Object[] { properties });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     ProviderXAConnection xaConnection = (ProviderXAConnection)createProviderConnection(properties);
/*     */     
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createProviderXAConnection(JmsPropertyContext)", xaConnection);
/*     */     }
/*     */     
/* 104 */     return xaConnection;
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
/*     */   protected ProviderConnection createV7ProviderConnection(JmsPropertyContext connectionProps) throws JMSException {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", new Object[] { connectionProps });
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
/* 131 */     int jmqiType = -1;
/* 132 */     int transport = connectionProps.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/* 133 */     if (transport == 0) {
/* 134 */       jmqiType = 0;
/*     */     }
/* 136 */     else if (transport == 1) {
/* 137 */       checkClientConnectionAllowed(true);
/* 138 */       jmqiType = 2;
/*     */     } 
/*     */ 
/*     */     
/* 142 */     int jmqiOptions = 1;
/*     */ 
/*     */     
/* 145 */     if (WMQConnectionFactory.forceUseJmqiWorkerThread) {
/* 146 */       jmqiOptions |= 0x4;
/*     */     }
/* 148 */     if (WMQConnectionFactory.forceDontUseJmqiWorkerThread) {
/* 149 */       jmqiOptions |= 0x8;
/*     */     }
/* 151 */     if (WMQConnectionFactory.forceDontUseSharedHconn) {
/* 152 */       jmqiOptions |= 0x10;
/*     */     }
/* 154 */     if (WMQConnectionFactory.inheritRRSContext) {
/* 155 */       jmqiOptions |= 0x40;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 160 */     JmqiMQ jmqiMq = null;
/* 161 */     JmqiEnvironment jmqiEnvironment = getJmqiEnvironment();
/* 162 */     int jmqiCompId = getJmqiCompId();
/*     */     try {
/* 164 */       jmqiMq = jmqiEnvironment.getMQI(jmqiType, jmqiOptions);
/*     */     }
/* 166 */     catch (JmqiException e) {
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 172 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ6312", null);
/*     */       
/* 174 */       je.setLinkedException((Exception)e);
/*     */       
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)je);
/*     */       }
/*     */       
/* 180 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     WMQXAConnection wMQXAConnection = new WMQXAConnection(jmqiEnvironment, jmqiMq, jmqiCompId, connectionProps);
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", wMQXAConnection);
/*     */     }
/*     */     
/* 193 */     return (ProviderConnection)wMQXAConnection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProviderConnection createV6ProviderConnection(JmsPropertyContext connectionProps) throws JMSException {
/*     */     MQXAConnection mQXAConnection;
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", new Object[] { connectionProps });
/*     */     }
/*     */ 
/*     */     
/* 210 */     ProviderConnection connection = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*     */       HashMap<String, Object> info;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 223 */       int brokerVersion = connectionProps.getIntProperty("brokerVersion");
/* 224 */       int messageSelection = connectionProps.getIntProperty("XMSC_WMQ_MESSAGE_SELECTION");
/* 225 */       if (brokerVersion != 1 && messageSelection == 1) {
/* 226 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ3036", null);
/*     */         
/* 228 */         if (Trace.isOn) {
/* 229 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 232 */         throw je;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 241 */       String username = connectionProps.getStringProperty("XMSC_USERID");
/* 242 */       if (username == null) {
/* 243 */         username = "";
/*     */       }
/* 245 */       String password = connectionProps.getStringProperty("XMSC_PASSWORD");
/* 246 */       if (password == null) {
/* 247 */         password = "";
/*     */       }
/* 249 */       if (Trace.isOn) {
/* 250 */         Trace.traceData(this, "connecting as user: " + username, null);
/*     */       }
/*     */       
/* 253 */       int transportType = connectionProps.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/*     */       
/* 255 */       switch (transportType) {
/*     */         case 0:
/*     */         case 1:
/* 258 */           mQXAConnection = new MQXAConnection(username, password, connectionProps);
/*     */           break;
/*     */         default:
/* 261 */           info = new HashMap<>();
/* 262 */           info.put("connectionMode", Integer.toString(transportType));
/* 263 */           Trace.ffst(this, "(createV6ProviderConnection)", "XT002002", info, JMSException.class);
/*     */           break;
/*     */       } 
/* 266 */     } catch (JMSException je) {
/* 267 */       if (Trace.isOn) {
/* 268 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", (Throwable)je);
/*     */       }
/*     */       
/* 271 */       if (Trace.isOn) {
/* 272 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 275 */       throw je;
/*     */     } 
/*     */     
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQXAConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", mQXAConnection);
/*     */     }
/*     */     
/* 282 */     return (ProviderConnection)mQXAConnection;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\factories\WMQXAConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */