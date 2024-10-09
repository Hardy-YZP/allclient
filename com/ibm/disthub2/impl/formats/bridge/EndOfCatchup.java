/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.util.Assert;
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
/*     */ public final class EndOfCatchup
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  39 */   private static final DebugObject debug = new DebugObject("EndOfCatchup");
/*     */ 
/*     */ 
/*     */   
/*     */   public static EndOfCatchup create() {
/*  44 */     Jgram jg = new Jgram(10);
/*  45 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  46 */     msg.setTrack(-1);
/*  47 */     EndOfCatchup ans = (EndOfCatchup)msg.setBody(28);
/*  48 */     ans.setTargetId(-1);
/*  49 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   EndOfCatchup(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  54 */     super(type, cursor, msg);
/*     */   }
/*     */   
/*     */   public int getTargetId() {
/*  58 */     return this.cursor.getInt(89);
/*     */   }
/*     */   
/*     */   public void setTargetId(int tid) {
/*  62 */     this.cursor.setInt(89, tid);
/*     */   }
/*     */   
/*     */   public void setPubend(long pid) {
/*  66 */     MessageBodyHandle[] peTbl = new MessageBodyHandle[1];
/*  67 */     peTbl[0] = this.cursor.newTableRow(90);
/*  68 */     peTbl[0].setLong(0, pid);
/*  69 */     this.cursor.setTable(90, peTbl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPubends(Object[] pids, int count) {
/*  78 */     MessageBodyHandle[] peTbl = new MessageBodyHandle[count];
/*  79 */     for (int i = 0; i < count; i++) {
/*  80 */       peTbl[i] = this.cursor.newTableRow(90);
/*  81 */       if (!(pids[i] instanceof Long))
/*  82 */         Assert.failure("Trying to use a non-Long for pubend id"); 
/*  83 */       peTbl[i].setLong(0, ((Long)pids[i]).longValue());
/*     */     } 
/*  85 */     this.cursor.setTable(90, peTbl);
/*     */   }
/*     */   
/*     */   public long[] getPubends() {
/*  89 */     MessageBodyHandle[] peTbl = this.cursor.getTable(90);
/*  90 */     long[] rslt = new long[peTbl.length];
/*  91 */     for (int i = 0; i < peTbl.length; i++)
/*  92 */       rslt[i] = peTbl[i].getLong(0); 
/*  93 */     return rslt;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  97 */     String rslt = this.cursor.getInt(89) + ",(";
/*  98 */     long[] pids = getPubends();
/*  99 */     for (int i = 0; i < pids.length; i++)
/* 100 */       rslt = rslt + pids[i] + ","; 
/* 101 */     rslt = rslt + ")";
/* 102 */     return rslt;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\EndOfCatchup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */