/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.Security;
/*     */ import org.bouncycastle.jce.provider.BouncyCastleProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SecurityProvider
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/SecurityProvider.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.ese.core.SecurityProvider", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/SecurityProvider.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Provider
/*     */   {
/*     */     public static final String IBMJCE = "IBMJCE";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final String BC = "BC";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final String PKCS11 = "PKCS11";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final String IBMJCEFIPS = "IBMJCEFIPS";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final String IBMJCEPlusFIPS = "IBMJCEPlusFIPS";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final String BOUNCY_CASTLE = "BC";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean initialized = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SecurityProvider() {
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityProvider", "<init>()");
/*     */     }
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityProvider", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void init() {
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry("com.ibm.mq.ese.core.SecurityProvider", "init()");
/*     */     }
/*     */     
/* 111 */     if (initialized) {
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.exit("com.ibm.mq.ese.core.SecurityProvider", "init()");
/*     */       }
/*     */       return;
/*     */     } 
/* 117 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 121 */             if (Trace.isOn) {
/* 122 */               Trace.entry(this, "com.ibm.mq.ese.core.SecurityProvider", "run()");
/*     */             }
/* 124 */             Security.addProvider((java.security.Provider)new BouncyCastleProvider());
/* 125 */             SecurityProvider.initialized = true;
/* 126 */             if (Trace.isOn) {
/* 127 */               Trace.exit(this, "com.ibm.mq.ese.core.null", "run()", null);
/*     */             }
/* 129 */             return null;
/*     */           }
/*     */         });
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit("com.ibm.mq.ese.core.SecurityProvider", "init()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getProvider() {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data("com.ibm.mq.ese.core.SecurityProvider", "getProvider()", "getter", "BC");
/*     */     }
/*     */     
/* 147 */     return "BC";
/*     */   }
/*     */   
/*     */   public static boolean isPkcs11Enabled() {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry("com.ibm.mq.ese.core.SecurityProvider", "isPkcs11Enabled()");
/*     */     }
/* 154 */     boolean ret = true;
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit("com.ibm.mq.ese.core.SecurityProvider", "isPkcs11Enabled()", Boolean.valueOf(ret));
/*     */     }
/* 158 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\SecurityProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */