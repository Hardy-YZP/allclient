/*    */ package com.ibm.mq.pcf;
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
/*    */ @Deprecated
/*    */ public class CCSID
/*    */   extends CCSID
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/CCSID.java";
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.mq.pcf.CCSID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/CCSID.java");
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
/*    */ 
/*    */   
/*    */   public static String getCharSet(int ccsid, int encoding) throws UnsupportedEncodingException {
/* 61 */     if (Trace.isOn)
/* 62 */       Trace.entry("com.ibm.mq.pcf.CCSID", "getCharSet(int,int)", new Object[] {
/* 63 */             Integer.valueOf(ccsid), Integer.valueOf(encoding)
/*    */           }); 
/* 65 */     String charSet = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 71 */     if (ccsid == 1200) {
/* 72 */       if ((encoding & 0xF) == 2) {
/* 73 */         charSet = "UnicodeLittle";
/*    */       } else {
/* 75 */         charSet = "UnicodeBig";
/*    */       } 
/*    */     } else {
/* 78 */       charSet = getCodepage(ccsid);
/*    */     } 
/*    */     
/* 81 */     if (charSet != null) {
/* 82 */       if (Trace.isOn) {
/* 83 */         Trace.exit("com.ibm.mq.pcf.CCSID", "getCharSet(int,int)", charSet, 1);
/*    */       }
/* 85 */       return charSet;
/*    */     } 
/*    */ 
/*    */     
/* 89 */     String traceRet1 = "IBM-" + Integer.toString(ccsid);
/* 90 */     if (Trace.isOn) {
/* 91 */       Trace.exit("com.ibm.mq.pcf.CCSID", "getCharSet(int,int)", traceRet1, 2);
/*    */     }
/* 93 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\CCSID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */