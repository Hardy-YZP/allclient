/*    */ package com.ibm.mq.ese.prot;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.SecureRandom;
/*    */ import org.bouncycastle.crypto.BlockCipher;
/*    */ import org.bouncycastle.crypto.DataLengthException;
/*    */ import org.bouncycastle.crypto.InvalidCipherTextException;
/*    */ import org.bouncycastle.crypto.engines.AESFastEngine;
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
/*    */ 
/*    */ public class AMSSymmetricalTest
/*    */ {
/* 39 */   private static SecureRandom sr = new SecureRandom();
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
/*    */   public static void main(String[] arg) throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException {
/* 51 */     int keyBits = 128;
/* 52 */     byte[] key = new byte[16];
/* 53 */     sr.nextBytes(key);
/*    */     
/* 55 */     String testData = "My hat it has three corners, three corners has my hat, and had it not three corners, it would not be my hat";
/*    */     
/* 57 */     byte[] testBytes = testData.getBytes("UTF8");
/*    */ 
/*    */     
/* 60 */     AESFastEngine aESFastEngine = new AESFastEngine();
/*    */     
/* 62 */     CipherWriter writer = new CipherWriter((BlockCipher)aESFastEngine);
/*    */     
/* 64 */     byte[] encBytes = writer.write(testBytes, key);
/*    */ 
/*    */     
/* 67 */     CipherReader reader = new CipherReader((BlockCipher)aESFastEngine);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void dumpBytes(byte[] bytes, String label) {
/* 78 */     for (int i = 0; i < bytes.length; i++) {
/* 79 */       System.out.format("\t%s byte(%d) = %d%n", new Object[] { label, Integer.valueOf(i), Byte.valueOf(bytes[i]) });
/*    */     } 
/* 81 */     System.out.println();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\AMSSymmetricalTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */