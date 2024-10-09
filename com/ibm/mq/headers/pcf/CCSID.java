/*    */ package com.ibm.mq.headers.pcf;
/*    */ 
/*    */ import com.ibm.mq.headers.CCSID;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.io.UnsupportedEncodingException;
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
/*    */ public class CCSID
/*    */   extends CCSID
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/CCSID.java";
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.mq.headers.pcf.CCSID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/CCSID.java");
/*    */     }
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
/*    */   
/*    */   public static String getCharSet(int ccsid, int encoding) throws UnsupportedEncodingException {
/* 59 */     if (Trace.isOn)
/* 60 */       Trace.entry("com.ibm.mq.headers.pcf.CCSID", "getCharSet(int,int)", new Object[] {
/* 61 */             Integer.valueOf(ccsid), Integer.valueOf(encoding)
/*    */           }); 
/* 63 */     String charSet = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     if (ccsid == 1200) {
/* 71 */       if ((encoding & 0xF) == 2) {
/* 72 */         charSet = "UnicodeLittle";
/*    */       } else {
/*    */         
/* 75 */         charSet = "UnicodeBig";
/*    */       } 
/*    */     } else {
/*    */       
/* 79 */       charSet = getCodepage(ccsid);
/*    */     } 
/*    */     
/* 82 */     if (charSet == null)
/*    */     {
/*    */       
/* 85 */       charSet = "IBM-" + Integer.toString(ccsid);
/*    */     }
/*    */     
/* 88 */     if (Trace.isOn) {
/* 89 */       Trace.exit("com.ibm.mq.headers.pcf.CCSID", "getCharSet(int,int)", charSet);
/*    */     }
/* 91 */     return charSet;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\CCSID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */