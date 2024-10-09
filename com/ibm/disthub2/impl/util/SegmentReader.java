/*    */ package com.ibm.disthub2.impl.util;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*    */ public abstract class SegmentReader
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   public static final short DEFAULT_MAGIC = -13647;
/* 22 */   private static final DebugObject debug = new DebugObject("SegmentReader");
/*    */ 
/*    */ 
/*    */   
/*    */   protected short magic;
/*    */ 
/*    */   
/*    */   protected int segpay;
/*    */ 
/*    */   
/*    */   protected int segmax;
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParms(short magic, int segmax) {
/* 37 */     this.magic = magic; this.segmax = segmax;
/*    */   }
/*    */   
/*    */   public abstract void prepGet(byte[] paramArrayOfbyte) throws IOException;
/*    */   
/*    */   public abstract byte[] get() throws IOException;
/*    */   
/*    */   public abstract void close() throws IOException;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\SegmentReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */