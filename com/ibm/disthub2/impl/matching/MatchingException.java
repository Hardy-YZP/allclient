/*    */ package com.ibm.disthub2.impl.matching;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MatchingException
/*    */   extends Exception
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \nIBM MQSeries Integrator Version 2.0 - 5648-C63 \n(C) Copyright IBM Corp. 2000 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   
/*    */   public MatchingException() {}
/*    */   
/*    */   public MatchingException(String s) {
/* 53 */     super("Severe error in matching: " + s);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\MatchingException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */