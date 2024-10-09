/*    */ package com.ibm.mq.jmqi.internal.charset;
/*    */ 
/*    */ import java.nio.charset.CharsetDecoder;
/*    */ import java.nio.charset.CharsetEncoder;
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
/*    */ public class IBM930Charset
/*    */   extends MixedByteCharset
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/IBM930Charset.java";
/*    */   
/*    */   protected IBM930Charset(String canonicalName, String[] aliases) {
/* 36 */     super(canonicalName, aliases);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBM930Charset() {
/* 43 */     this("IBM-930", new String[] { "Cp5026", "5026" });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCcsid() {
/* 52 */     return 930;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CharsetDecoder newDecoder() {
/* 61 */     return newDecoder("IBM930ByteToChar.dat");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CharsetEncoder newEncoder() {
/* 70 */     return newEncoder("IBM930CharToByte.dat");
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\IBM930Charset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */