/*    */ package com.ibm.disthub2.impl.security;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
/*    */ import com.ibm.disthub2.impl.multicast.MulticastTopic;
/*    */ import com.ibm.disthub2.spi.Principal;
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
/*    */ public class MulticastSecurityContext
/*    */   extends SecurityContext
/*    */ {
/* 31 */   private static final DebugObject debug = new DebugObject("MulticastSecurityContext");
/*    */ 
/*    */   
/*    */   MulticastMessageProtection multicastMessageProtection;
/*    */ 
/*    */ 
/*    */   
/*    */   public static MulticastSecurityContext createMulticastSecurityContext(Principal principal, byte[] masterKey, boolean QOP, MulticastTopic topic) throws CryptoInstantiationException {
/* 39 */     MulticastMessageProtection mp = new MulticastMessageProtection(topic);
/* 40 */     MulticastSecurityContext sc = new MulticastSecurityContext(principal, masterKey, QOP, mp);
/* 41 */     return sc;
/*    */   }
/*    */ 
/*    */   
/*    */   public MulticastSecurityContext(Principal principal, byte[] masterKey, boolean QOP, MulticastMessageProtection mp) throws CryptoInstantiationException {
/* 46 */     super(principal, masterKey, QOP, mp);
/*    */     
/* 48 */     if (debug.debugIt(32)) {
/* 49 */       debug.debug(-165922073994779L, "MulticastSecurityContext", principal, masterKey, Boolean.valueOf(QOP));
/*    */     }
/* 51 */     this.multicastMessageProtection = mp;
/*    */     
/* 53 */     if (debug.debugIt(64)) {
/* 54 */       debug.debug(-142394261359015L, "MulticastSecurityContext");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getNextSendCount() {
/* 61 */     return this.multicastMessageProtection.getCounter(true);
/*    */   }
/*    */   
/*    */   public long getNextRcvCount() {
/* 65 */     return this.multicastMessageProtection.getCounter(false);
/*    */   }
/*    */   public void newMessage() {
/* 68 */     this.multicastMessageProtection.getCounter(true);
/*    */   }
/*    */   public byte[] getDecryptIV() {
/* 71 */     return null;
/*    */   }
/*    */   
/*    */   public byte[] getEncryptIV() {
/* 75 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\security\MulticastSecurityContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */