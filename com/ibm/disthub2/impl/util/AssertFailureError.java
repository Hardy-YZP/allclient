/*    */ package com.ibm.disthub2.impl.util;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AssertFailureError
/*    */   extends Error
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   private Throwable m_exception;
/*    */   
/*    */   public AssertFailureError(String s, Throwable e) {
/* 47 */     super(s);
/* 48 */     this.m_exception = e;
/*    */   }
/*    */   
/*    */   public AssertFailureError(String s) {
/* 52 */     super(s);
/* 53 */     this.m_exception = null;
/*    */   }
/*    */   
/*    */   public void printStackTrace() {
/* 57 */     super.printStackTrace();
/* 58 */     if (this.m_exception != null)
/* 59 */       this.m_exception.printStackTrace(); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\AssertFailureError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */