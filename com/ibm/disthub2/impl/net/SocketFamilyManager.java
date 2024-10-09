/*     */ package com.ibm.disthub2.impl.net;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.BaseConfig;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.client.SessionConfig;
/*     */ import com.ibm.disthub2.impl.net.proxy.ProxyConnectSocketFactory;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.SocketThreadPool;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.IOException;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SocketFamilyManager
/*     */   implements ExceptionConstants, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  51 */   private static final DebugObject debug = new DebugObject("SocketFamilyManager");
/*     */ 
/*     */   
/*     */   private static final String PREFIX = "com.ibm.disthub2.impl.net.";
/*     */ 
/*     */   
/*     */   private static final String CLIENT = "SocketFactory";
/*     */ 
/*     */   
/*     */   private static final String SERVER = "ServerSocketFactory";
/*     */   
/*  62 */   private static Hashtable nameTable = new Hashtable<>();
/*     */ 
/*     */   
/*  65 */   private static Hashtable schemeTable = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  73 */     register("tcp", "com.ibm.disthub2.impl.net.tcp.TcpSocketFactory", "com.ibm.disthub2.impl.net.tcp.TcpServerSocketFactory", true, false);
/*  74 */     register("http", "com.ibm.disthub2.impl.net.http.HttpSocketFactory", "com.ibm.disthub2.impl.net.http.HttpServerSocketFactory", true, true);
/*  75 */     register("connect-via-proxy", "com.ibm.disthub2.impl.net.proxy.ConnectSocketFactory", null, false, false);
/*     */ 
/*     */     
/*  78 */     registerURLScheme("", "tcp", 0);
/*  79 */     registerURLScheme("http", "http", 80);
/*  80 */     registerURLScheme("via-proxy", "connect-via-proxy", 443);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register(String publicName, String factoryName, String serverFactoryName, boolean useNBIO, boolean acceptTP) {
/* 124 */     Entry e = new Entry(factoryName, serverFactoryName, useNBIO, acceptTP);
/* 125 */     nameTable.put(publicName, e);
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
/*     */   public static void registerURLScheme(String schemeName, String familyName, int port) {
/* 142 */     URLScheme s = new URLScheme(familyName, port);
/* 143 */     schemeTable.put(schemeName, s);
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
/*     */   public static URLScheme lookupScheme(String schemeName) {
/* 157 */     return (URLScheme)schemeTable.get(schemeName);
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
/*     */   public static Socket createSocket(String familyName, String host, int port, BaseConfig baseConfig) throws IOException {
/*     */     Socket result;
/* 178 */     if (debug.debugIt(32)) {
/* 179 */       debug.debug(-165922073994779L, "createSocket", familyName, host, Integer.valueOf(port));
/*     */     }
/* 181 */     Entry e = (Entry)nameTable.get(familyName);
/* 182 */     if (e.factory == null) {
/* 183 */       e.factory = (SocketFactory)load(e.factoryName);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (e.factory instanceof ProxyConnectSocketFactory) {
/*     */       
/* 193 */       ProxyConnectSocketFactory factory = (ProxyConnectSocketFactory)e.factory;
/* 194 */       result = factory.createSocket(host, port, ((SessionConfig)baseConfig).HTTP_PROXY, ((SessionConfig)baseConfig).HTTP_PROXY_PORT);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 201 */       result = e.factory.createSocket(host, port);
/*     */     } 
/*     */     
/* 204 */     if (debug.debugIt(64)) {
/* 205 */       debug.debug(-142394261359015L, "createSocket", result);
/*     */     }
/* 207 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canUseNBIO(String familyName) {
/* 213 */     return ((Entry)nameTable.get(familyName)).useNBIO;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean needAcceptTP(String familyName) {
/* 219 */     return ((Entry)nameTable.get(familyName)).needAcceptTP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerSocket createServerSocket(String family, int port, SocketThreadPool stp) throws IOException {
/*     */     ServerSocket result;
/* 227 */     if (debug.debugIt(32)) {
/* 228 */       debug.debug(-165922073994779L, "createServerSocket", family, Integer.valueOf(port));
/*     */     }
/* 230 */     Entry e = (Entry)nameTable.get(family);
/* 231 */     if (e.serverFactory == null) {
/* 232 */       e.serverFactory = (ServerSocketFactory)load(e.serverFactoryName);
/*     */     }
/* 234 */     if (e.needAcceptTP) {
/* 235 */       result = e.serverFactory.createServerSocket(port, stp);
/*     */     } else {
/* 237 */       result = e.serverFactory.createServerSocket(port);
/*     */     } 
/* 239 */     if (debug.debugIt(64)) {
/* 240 */       debug.debug(-142394261359015L, "createServerSocket", result);
/*     */     }
/* 242 */     return result;
/*     */   }
/*     */   
/*     */   public static final class URLScheme
/*     */   {
/*     */     public String familyName;
/*     */     public int defaultPort;
/*     */     
/*     */     URLScheme(String name, int port) {
/* 251 */       this.familyName = name;
/* 252 */       this.defaultPort = port;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object load(String name) {
/*     */     try {
/* 261 */       return Class.forName(name).newInstance();
/*     */     }
/* 263 */     catch (ClassNotFoundException e) {
/* 264 */       throw Assert.failureError(e, ExceptionBuilder.buildReasonString(1266173347, new Object[] { name, e }));
/* 265 */     } catch (InstantiationException e) {
/* 266 */       throw Assert.failureError(e, ExceptionBuilder.buildReasonString(1266173347, new Object[] { name, e }));
/* 267 */     } catch (IllegalAccessException e) {
/* 268 */       throw Assert.failureError(e, ExceptionBuilder.buildReasonString(1266173347, new Object[] { name, e }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static class Entry
/*     */   {
/*     */     String factoryName;
/*     */     
/*     */     SocketFactory factory;
/*     */     String serverFactoryName;
/*     */     ServerSocketFactory serverFactory;
/*     */     boolean useNBIO;
/*     */     boolean needAcceptTP;
/*     */     
/*     */     Entry(String fName, String sfName, boolean nbio, boolean tp) {
/* 284 */       this.factoryName = fName;
/* 285 */       this.serverFactoryName = sfName;
/* 286 */       this.useNBIO = nbio;
/* 287 */       this.needAcceptTP = tp;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\net\SocketFamilyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */