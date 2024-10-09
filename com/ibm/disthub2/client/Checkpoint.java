/*    */ package com.ibm.disthub2.client;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.CheckpointImpl;
/*    */ import java.io.IOException;
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
/*    */ public abstract class Checkpoint
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   
/*    */   public abstract byte[] toByteArray();
/*    */   
/*    */   public abstract void update(Increment paramIncrement);
/*    */   
/*    */   public static Checkpoint create(byte[] storedForm) throws IOException {
/* 42 */     return CheckpointImpl.create(storedForm);
/*    */   }
/*    */   
/*    */   public abstract String toString();
/*    */   
/*    */   public static abstract class Increment {
/*    */     public static Increment create(byte[] storedForm) throws IOException {
/* 49 */       return CheckpointImpl.IncrementImpl.create(storedForm);
/*    */     }
/*    */     
/*    */     public abstract byte[] toByteArray();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\client\Checkpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */