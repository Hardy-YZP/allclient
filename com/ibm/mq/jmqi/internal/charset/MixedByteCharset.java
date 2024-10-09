/*    */ package com.ibm.mq.jmqi.internal.charset;
/*    */ 
/*    */ import java.nio.charset.Charset;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MixedByteCharset
/*    */   extends CustomCharset
/*    */ {
/*    */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/MixedByteCharset.java";
/*    */   
/*    */   protected MixedByteCharset(String canonicalName, String[] aliases) {
/* 42 */     super(canonicalName, aliases);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean contains(Charset cs) {
/* 51 */     if (cs.getClass().equals(getClass())) {
/* 52 */       return true;
/*    */     }
/* 54 */     return false;
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
/*    */ 
/*    */   
/*    */   public CharsetDecoder newDecoder(String codePageFileName) {
/* 71 */     return new MixedByteCharsetDecoder(this, 1.0F, 1.0F, codePageFileName);
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
/*    */ 
/*    */   
/*    */   public CharsetEncoder newEncoder(String codePageFileName) {
/* 88 */     return new MixedByteCharsetEncoder(this, 2.0F, 4.0F, codePageFileName);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\MixedByteCharset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */