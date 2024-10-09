/*    */ package com.ibm.disthub2.impl.matching.selector;
/*    */ 
/*    */ import java.util.Hashtable;
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
/*    */ public final class PositionAssigner
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 40 */   private Hashtable positions = new Hashtable<>();
/* 41 */   private int nextPosition = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void assign(Identifier id) {
/* 48 */     String key = null;
/* 49 */     switch (id.type) {
/*    */       case -5:
/* 51 */         key = "S:" + id.name;
/*    */         break;
/*    */       case -6:
/* 54 */         key = "B:" + id.name;
/*    */         break;
/*    */       case 0:
/* 57 */         key = "U:" + id.name;
/*    */         break;
/*    */       case 4:
/*    */         break;
/*    */       default:
/* 62 */         key = "N:" + id.name; break;
/*    */     } 
/* 64 */     if (key != null) {
/* 65 */       Integer op = (Integer)this.positions.get(key);
/* 66 */       if (op == null) {
/* 67 */         op = Integer.valueOf(this.nextPosition++);
/* 68 */         this.positions.put(key, op);
/*    */       } 
/* 70 */       id.ordinalPosition = op.intValue();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\PositionAssigner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */