/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DoPrivileged
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public static Properties getProperties() {
/*     */     try {
/*  54 */       Class.forName("java.security.AccessController");
/*  55 */       DoPrivileged privileged = (DoPrivileged)Class.forName("com.ibm.disthub2.impl.util.DoPrivilegedImpl").newInstance();
/*  56 */       return privileged.getPropertiesImpl();
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       return System.getProperties();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getProperty(String name, String defaultValue) {
/*     */     try {
/*  69 */       Class.forName("java.security.AccessController");
/*  70 */       DoPrivileged privileged = (DoPrivileged)Class.forName("com.ibm.disthub2.impl.util.DoPrivilegedImpl").newInstance();
/*  71 */       return privileged.getPropertyImpl(name, defaultValue);
/*     */     }
/*  73 */     catch (Exception cnfe) {
/*     */       
/*  75 */       return System.getProperty(name, defaultValue);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Socket createSocket(String host, int port) throws UnknownHostException, IOException {
/*     */     try {
/*  85 */       Class.forName("java.security.AccessController");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  91 */     catch (Exception e) {
/*     */       
/*  93 */       return new Socket(host, port);
/*     */     } 
/*     */     try {
/*  96 */       DoPrivileged privileged = (DoPrivileged)Class.forName("com.ibm.disthub2.impl.util.DoPrivilegedImpl").newInstance();
/*  97 */       return privileged.createSocketImpl(host, port);
/*     */     }
/*  99 */     catch (ClassNotFoundException cnfe) {
/*     */       
/* 101 */       return new Socket(host, port);
/*     */     }
/* 103 */     catch (InstantiationException ie) {
/*     */       
/* 105 */       return new Socket(host, port);
/*     */     }
/* 107 */     catch (IllegalAccessException iae) {
/*     */       
/* 109 */       return new Socket(host, port);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Thread createAndStartThread(Runnable r, boolean daemon) {
/*     */     try {
/* 120 */       Class.forName("java.security.AccessController");
/* 121 */       DoPrivileged privileged = (DoPrivileged)Class.forName("com.ibm.disthub2.impl.util.DoPrivilegedImpl").newInstance();
/* 122 */       return privileged.createAndStartThreadImpl(r, daemon);
/*     */     }
/* 124 */     catch (Exception e) {
/*     */       
/* 126 */       Thread t = new Thread(r);
/* 127 */       t.setDaemon(daemon);
/* 128 */       t.start();
/* 129 */       return t;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract Properties getPropertiesImpl();
/*     */   
/*     */   protected abstract String getPropertyImpl(String paramString1, String paramString2);
/*     */   
/*     */   protected abstract Socket createSocketImpl(String paramString, int paramInt) throws UnknownHostException, IOException;
/*     */   
/*     */   protected abstract Thread createAndStartThreadImpl(Runnable paramRunnable, boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\DoPrivileged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */