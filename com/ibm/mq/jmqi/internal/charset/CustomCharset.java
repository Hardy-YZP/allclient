/*    */ package com.ibm.mq.jmqi.internal.charset;
/*    */ 
/*    */ import java.nio.charset.Charset;
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
/*    */ public abstract class CustomCharset
/*    */   extends Charset
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/CustomCharset.java";
/*    */   
/*    */   protected CustomCharset(String canonicalName, String[] aliases) {
/* 35 */     super(canonicalName, aliases);
/*    */   }
/*    */   
/*    */   public abstract int getCcsid();
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\CustomCharset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */