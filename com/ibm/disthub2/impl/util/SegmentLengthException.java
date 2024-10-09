/*    */ package com.ibm.disthub2.impl.util;
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
/*    */ public class SegmentLengthException
/*    */   extends IOException
/*    */ {
/*    */   protected int segmentMaximum;
/*    */   protected int segmentSize;
/*    */   
/*    */   public SegmentLengthException() {
/* 37 */     super("unknown"); } public SegmentLengthException(String z) {
/* 38 */     super(z);
/*    */   }
/*    */   
/*    */   public void setSegmentSize(int s) {
/* 42 */     this.segmentSize = s;
/*    */   }
/*    */   
/*    */   public void setSegmentMaximum(int s) {
/* 46 */     this.segmentMaximum = s;
/*    */   }
/*    */   
/*    */   public int getSegmentSize() {
/* 50 */     return this.segmentSize;
/*    */   }
/*    */   
/*    */   public int getSegmentMaximum() {
/* 54 */     return this.segmentMaximum;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\SegmentLengthException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */