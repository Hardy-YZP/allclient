/*    */ package com.ibm.disthub2.impl.formats;
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
/*    */ public abstract class PropagationContext
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   private int nSchemata;
/*    */   private static final int SCHEMA_ID_LEN = 2;
/* 40 */   private long[] schemaIds = new long[2];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int releaseVersion;
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
/*    */   public int testPropagation(long schemaid) {
/* 62 */     for (int i = 0; i < this.nSchemata; i++) {
/* 63 */       if (schemaid == this.schemaIds[i])
/* 64 */         return -1; 
/* 65 */     }  return this.releaseVersion;
/*    */   }
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
/*    */   public void recordPropagation(long schemaid) {
/* 80 */     if (this.nSchemata == this.schemaIds.length) {
/* 81 */       long[] newIds = new long[this.nSchemata * 2];
/* 82 */       System.arraycopy(this.schemaIds, 0, newIds, 0, this.nSchemata);
/* 83 */       this.schemaIds = newIds;
/*    */     } 
/* 85 */     this.schemaIds[this.nSchemata++] = schemaid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetPropagations() {
/* 95 */     this.nSchemata = 0;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\PropagationContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */