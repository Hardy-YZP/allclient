/*    */ package com.ibm.disthub2.impl.client;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MinimalIOException
/*    */   extends IOException
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 39 */   int errorcode = Integer.MAX_VALUE;
/*    */ 
/*    */   
/*    */   public MinimalIOException(int errorcode) {
/* 43 */     this.errorcode = errorcode;
/*    */   }
/*    */   
/*    */   public MinimalIOException(int errorcode, String s) {
/* 47 */     super(s);
/* 48 */     this.errorcode = errorcode;
/*    */   }
/*    */   
/*    */   public int getErrorCode() {
/* 52 */     return this.errorcode;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\MinimalIOException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */