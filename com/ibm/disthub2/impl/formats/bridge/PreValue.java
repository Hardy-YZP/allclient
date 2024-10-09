/*    */ package com.ibm.disthub2.impl.formats.bridge;
/*    */ 
/*    */ import com.ibm.disthub2.impl.formats.Envelop;
/*    */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
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
/*    */ public final class PreValue
/*    */   extends Payload
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   
/*    */   public static Jgram create(byte[] mdt, long pubendID, long timestamp, boolean force, boolean curiousOnly, long fPrefix, long lPrefix, long startStamp, long endStamp, String srcCellFrom, String srcCellTo) {
/* 44 */     Jgram j = new Jgram(12);
/* 45 */     PreValue m = (PreValue)j.getPayload();
/*    */     
/* 47 */     m.cursor.setLong(107, fPrefix);
/* 48 */     m.cursor.setLong(106, lPrefix);
/* 49 */     m.cursor.setLong(108, startStamp);
/* 50 */     m.cursor.setLong(109, endStamp);
/* 51 */     m.cursor.setString(110, srcCellFrom);
/* 52 */     m.cursor.setString(111, srcCellTo);
/*    */     
/* 54 */     j.setMdt(mdt);
/* 55 */     j.setPubendID(pubendID);
/* 56 */     j.setStamp(timestamp);
/* 57 */     j.setForce(force);
/* 58 */     j.setCuriousOnly(curiousOnly);
/*    */     
/* 60 */     return j;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   PreValue(int type, MessageDataHandle cursor, Jgram jgram) {
/* 66 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSrcCellFrom() {
/* 71 */     return this.cursor.getString(110);
/*    */   }
/*    */   public String getSrcCellTo() {
/* 74 */     return this.cursor.getString(111);
/*    */   }
/*    */   public long getFprefix() {
/* 77 */     return this.cursor.getLong(107);
/*    */   }
/*    */   public long getLprefix() {
/* 80 */     return this.cursor.getLong(106);
/*    */   }
/*    */   public long getStartstamp() {
/* 83 */     return this.cursor.getLong(108);
/*    */   }
/*    */   public long getEndstamp() {
/* 86 */     return this.cursor.getLong(109);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\PreValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */