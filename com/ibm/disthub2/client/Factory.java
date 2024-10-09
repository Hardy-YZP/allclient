/*     */ package com.ibm.disthub2.client;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.BaseConfig;
/*     */ import com.ibm.disthub2.impl.client.ClientServices;
/*     */ import com.ibm.disthub2.impl.client.ConnectorImpl;
/*     */ import com.ibm.disthub2.impl.client.MessageImpl;
/*     */ import com.ibm.disthub2.impl.client.TopicImpl;
/*     */ import com.ibm.disthub2.spi.ClientTranslate;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.LogRecorder;
/*     */ import com.ibm.disthub2.spi.Principal;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Factory
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final String TCP_SOCKET = "tcp";
/*     */   public static final String HTTP_SOCKET = "http";
/*     */   public static final String PROXY_SOCKET = "connect-via-proxy";
/*     */   
/*     */   public static void setParameter(String name, String value) {
/* 133 */     ConnectorImpl.initialize(BaseConfig.class);
/* 134 */     BaseConfig.setParameter(name, value);
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
/*     */   public static void setTranslator(ClientTranslate T) {
/* 148 */     ConnectorImpl.initialize(BaseConfig.class);
/* 149 */     ExceptionBuilder.setTranslator(T);
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
/*     */   public static void setLogRecorder(LogRecorder LR) {
/* 164 */     if (ClientServices.logRecorder != null) {
/* 165 */       throw new IllegalStateException();
/*     */     }
/* 167 */     ConnectorImpl.initialize(BaseConfig.class);
/* 168 */     ClientServices.logRecorder = LR;
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
/*     */   public static Topic createTopic(String name) {
/* 184 */     ConnectorImpl.initialize(BaseConfig.class);
/* 185 */     return (Topic)new TopicImpl(name);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Connector createConnector(String socketTypeName, String host, int port, String user, String passwd, Listener listener) throws IOException {
/* 217 */     ConnectorImpl.initialize(BaseConfig.class);
/* 218 */     return (Connector)new ConnectorImpl(socketTypeName, host, port, user, passwd, listener, true, 
/* 219 */         BaseConfig.getBaseConfig());
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
/*     */   
/*     */   public static Connector createConnector(String socketTypeName, String host, int port, Principal creds, Listener listener) throws IOException {
/* 248 */     ConnectorImpl.initialize(BaseConfig.class);
/* 249 */     return (Connector)new ConnectorImpl(socketTypeName, host, port, creds, listener, true, 
/* 250 */         BaseConfig.getBaseConfig());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static Connector createConnector(String socketTypeName, String host, int port, String user, String passwd, SubscriptionListener slistener) throws IOException {
/* 281 */     ConnectorImpl.initialize(BaseConfig.class);
/* 282 */     return (Connector)new ConnectorImpl(socketTypeName, host, port, user, passwd, slistener, true, 
/* 283 */         BaseConfig.getBaseConfig());
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
/*     */   public static Message createMessage(Topic topic) {
/* 296 */     ConnectorImpl.initialize(BaseConfig.class);
/* 297 */     return (Message)new MessageImpl((TopicImpl)topic);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\client\Factory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */