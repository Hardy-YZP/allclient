/*    */ package com.ibm.disthub2.impl.server;
/*    */ 
/*    */ import com.ibm.disthub2.impl.formats.bridge.Jgram;
/*    */ import com.ibm.disthub2.impl.matching.MatchTarget;
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
/*    */ public abstract class InitialStateProcessor
/*    */   extends MatchTarget
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   public static final int NO_STATE = 2147483647;
/*    */   
/*    */   protected InitialStateProcessor(String targetName) {
/* 45 */     super(3, targetName);
/*    */   }
/*    */   
/*    */   public abstract Jgram[] getState();
/*    */   
/*    */   public abstract void processEvent(Jgram paramJgram);
/*    */   
/*    */   public abstract int length();
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\server\InitialStateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */