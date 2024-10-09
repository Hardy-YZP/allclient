/*    */ package com.ibm.mq.ese.pki;
/*    */ 
/*    */ import com.ibm.mq.ese.config.KeyStoreConfig;
/*    */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*    */ import com.ibm.mq.ese.core.SecurityProvider;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyStoreAccessFactory
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessFactory.java";
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.mq.ese.pki.KeyStoreAccessFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessFactory.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static KeyStoreAccess getInstance(KeyStoreConfig keyStoreConfig) {
/* 52 */     if (Trace.isOn) {
/* 53 */       Trace.entry("com.ibm.mq.ese.pki.KeyStoreAccessFactory", "getInstance(KeyStoreConfig)", new Object[] { keyStoreConfig });
/*    */     }
/*    */ 
/*    */     
/* 57 */     KeyStoreAccess ks = null;
/* 58 */     if (SecurityProvider.isPkcs11Enabled()) {
/* 59 */       if ("PKCS11IMPLKS".equals(keyStoreConfig.getType()) || "PKCS11"
/* 60 */         .equals(keyStoreConfig.getType())) {
/* 61 */         ks = new KeyStoreAccessPKCS11Impl(keyStoreConfig);
/* 62 */         if (keyStoreConfig.getSecondaryKeyStorePath() != null) {
/* 63 */           KeyStoreAccessJCEKSImpl ks2 = new KeyStoreAccessJCEKSImpl(keyStoreConfig, 1);
/* 64 */           ks = new CompositeKeyStoreAccess(ks, ks2);
/*    */         }
/*    */       
/* 67 */       } else if ("JCERACFKS".equals(keyStoreConfig.getType())) {
/* 68 */         ks = new KeyStoreAccessJCERACFKSImpl(keyStoreConfig);
/*    */       } else {
/* 70 */         ks = new KeyStoreAccessJCEKSImpl(keyStoreConfig);
/*    */       }
/*    */     
/*    */     }
/* 74 */     else if ("JCERACFKS".equals(keyStoreConfig.getType())) {
/* 75 */       ks = new KeyStoreAccessJCERACFKSImpl(keyStoreConfig);
/*    */     } else {
/* 77 */       ks = new KeyStoreAccessJCEKSImpl(keyStoreConfig);
/*    */     } 
/*    */ 
/*    */     
/* 81 */     if (Trace.isOn) {
/* 82 */       Trace.exit("com.ibm.mq.ese.pki.KeyStoreAccessFactory", "getInstance(KeyStoreConfig)", ks);
/*    */     }
/* 84 */     return ks;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\KeyStoreAccessFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */