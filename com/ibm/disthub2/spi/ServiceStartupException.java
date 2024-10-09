/*    */ package com.ibm.disthub2.spi;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServiceStartupException
/*    */   extends Exception
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 40 */   public Throwable wrappedException = null;
/*    */ 
/*    */   
/*    */   public ServiceStartupException() {}
/*    */ 
/*    */   
/*    */   public ServiceStartupException(String reason) {
/* 47 */     super(reason);
/*    */   }
/*    */   
/*    */   public ServiceStartupException(String reason, Throwable wrap) {
/* 51 */     super(reason);
/* 52 */     this.wrappedException = wrap;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 56 */     if (this.wrappedException != null) {
/* 57 */       return super.toString() + " wrapped exception: " + this.wrappedException.toString();
/*    */     }
/* 59 */     return super.toString() + " wrapped exception: null";
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\ServiceStartupException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */