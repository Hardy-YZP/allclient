/*     */ package com.ibm.msg.client.commonservices.passwordprotection;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQPBEFactory
/*     */ {
/*     */   public static final int ALGORITHM_PBE_COMPAT = 0;
/*     */   public static final int ALGORITHM_PBKDF2 = 1;
/*     */   public static final int ALGORITHM_PBKDF2_MD = 2;
/*     */   public static final int LATEST_ALGORITHM = 2;
/*  42 */   private static MQPBEFactory factoryInstance = new MQPBEFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private MQPasswordCipher[] instances = new MQPasswordCipher[3];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MQPBEFactory getFactoryInstance() {
/*  60 */     return factoryInstance;
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
/*     */   public static MQPasswordCipher getNewCipher(int algorithm, char[] initialKey, byte[] fixedSalt) throws PBEException {
/*     */     MQPBE1 mQPBE1;
/*     */     PBEException thrown;
/*     */     PBEException thrown2;
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", new Object[] {
/*  78 */             Integer.valueOf(algorithm), "********", "********"
/*     */           });
/*     */     }
/*  81 */     switch (algorithm) {
/*     */       case 0:
/*  83 */         thrown = new PBEException("Cannot handle old algorithms", PBEException.PBERC.ALGORITHMTOOOLD);
/*  84 */         if (Trace.isOn) {
/*  85 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", thrown);
/*     */         }
/*     */         
/*  88 */         throw thrown;
/*     */       case 1:
/*     */       case 2:
/*  91 */         mQPBE1 = new MQPBE1(initialKey, fixedSalt);
/*     */         break;
/*     */       default:
/*  94 */         thrown2 = new PBEException("Unrecognized algorithm: " + algorithm, PBEException.PBERC.UNRECOGNIZEDALGORITHM);
/*     */         
/*  96 */         if (Trace.isOn) {
/*  97 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", thrown2);
/*     */         }
/*     */         
/* 100 */         throw thrown2;
/*     */     } 
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", new Object[] { mQPBE1 });
/*     */     }
/*     */     
/* 106 */     return (MQPasswordCipher)mQPBE1;
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
/*     */   public synchronized MQPasswordCipher getSharedCipher(int algorithm, char[] intialKey, byte[] fixedSalt) throws PBEException {
/* 125 */     if (Trace.isOn)
/* 126 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getSharedCipher(int, char[], byte[])", new Object[] {
/* 127 */             Integer.valueOf(algorithm), "********", "********"
/*     */           }); 
/*     */     try {
/* 130 */       if (this.instances[algorithm] == null) {
/* 131 */         this.instances[algorithm] = getNewCipher(algorithm, intialKey, fixedSalt);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 136 */         this.instances[algorithm].reInitializeIfNecessary(intialKey, fixedSalt);
/*     */       } 
/* 138 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", e);
/*     */       }
/*     */       
/* 143 */       PBEException thrown = new PBEException("Failed to get Shared Cipher - Index out of bounds", PBEException.PBERC.NOSHAREDCIPHER);
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", thrown);
/*     */       }
/*     */       
/* 148 */       throw thrown;
/*     */     } 
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.MQPBEFactory", "getNewCipher(int, char[], byte[])", new Object[] { this.instances[algorithm] });
/*     */     }
/*     */     
/* 154 */     return this.instances[algorithm];
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\MQPBEFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */