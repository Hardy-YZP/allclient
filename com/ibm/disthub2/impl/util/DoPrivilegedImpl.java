/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoPrivilegedImpl
/*     */   extends DoPrivileged
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public Properties getPropertiesImpl() {
/*  50 */     return 
/*  51 */       AccessController.<Properties>doPrivileged(new PrivilegedAction<Properties>() {
/*     */           public Object run() {
/*     */             try {
/*  54 */               return System.getProperties();
/*  55 */             } catch (AccessControlException ace) {
/*  56 */               return new Properties();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyImpl(final String name, final String defaultValue) {
/*  67 */     return 
/*  68 */       AccessController.<String>doPrivileged(new PrivilegedAction<String>() {
/*     */           public Object run() {
/*     */             try {
/*  71 */               return System.getProperty(name, defaultValue);
/*  72 */             } catch (AccessControlException ace) {
/*  73 */               return defaultValue;
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Socket createSocketImpl(final String host, final int port) throws UnknownHostException, IOException {
/*     */     try {
/*  87 */       return 
/*  88 */         AccessController.<Socket>doPrivileged(new PrivilegedExceptionAction<Socket>() {
/*     */             public Object run() throws UnknownHostException, IOException {
/*  90 */               return new Socket(host, port);
/*     */             }
/*     */           });
/*     */     
/*     */     }
/*  95 */     catch (PrivilegedActionException e) {
/*  96 */       Exception le = e.getException();
/*  97 */       if (le instanceof UnknownHostException)
/*  98 */         throw (UnknownHostException)le; 
/*  99 */       if (le instanceof IOException)
/* 100 */         throw (IOException)le; 
/* 101 */       if (le instanceof RuntimeException)
/*     */       {
/* 103 */         throw (RuntimeException)le;
/*     */       }
/* 105 */       Assert.failure(e);
/* 106 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Thread createAndStartThreadImpl(final Runnable run, final boolean daemon) {
/* 115 */     return 
/* 116 */       AccessController.<Thread>doPrivileged(new PrivilegedAction<Thread>() {
/*     */           public Object run() {
/* 118 */             Thread t = new Thread(run);
/* 119 */             t.setDaemon(daemon);
/* 120 */             t.start();
/* 121 */             return t;
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\DoPrivilegedImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */