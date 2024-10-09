/*    */ package com.ibm.msg.client.wmq.common;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ public class WMQCommonUtils
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQCommonUtils.java";
/*    */   
/*    */   static {
/* 31 */     if (Trace.isOn) {
/* 32 */       Trace.data("com.ibm.msg.client.wmq.common.WMQCommonUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQCommonUtils.java");
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
/*    */   public static String escapeString(String bean) {
/* 47 */     if (Trace.isOn) {
/* 48 */       Trace.entry("com.ibm.msg.client.wmq.common.WMQCommonUtils", "escapeString(String)", new Object[] { bean });
/*    */     }
/*    */ 
/*    */     
/* 52 */     StringBuffer buffer = new StringBuffer();
/*    */     
/* 54 */     char c = Character.MIN_VALUE;
/* 55 */     String s = null;
/*    */ 
/*    */ 
/*    */     
/* 59 */     if (bean == null) {
/* 60 */       if (Trace.isOn) {
/* 61 */         Trace.exit("com.ibm.msg.client.wmq.common.WMQCommonUtils", "escapeString(String)", "", 1);
/*    */       }
/*    */       
/* 64 */       return "";
/*    */     } 
/* 66 */     for (int i = 0; i < bean.length(); i++) {
/* 67 */       c = bean.charAt(i);
/* 68 */       switch (c) {
/*    */         case '"':
/*    */         case ':':
/*    */         case ';':
/*    */         case '\\':
/* 73 */           s = "0000" + Integer.toString(c, 16);
/* 74 */           buffer.append("\\u" + s.substring(s.length() - 4, s.length()));
/*    */           break;
/*    */ 
/*    */ 
/*    */         
/*    */         default:
/* 80 */           if (c < ' ' || c > '~') {
/* 81 */             s = "0000" + Integer.toString(c, 16);
/* 82 */             buffer.append("\\u" + s.substring(s.length() - 4, s.length())); break;
/*    */           } 
/* 84 */           buffer.append(c);
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     } 
/* 92 */     String traceRet1 = buffer.toString();
/*    */     
/* 94 */     if (Trace.isOn) {
/* 95 */       Trace.exit("com.ibm.msg.client.wmq.common.WMQCommonUtils", "escapeString(String)", traceRet1, 2);
/*    */     }
/*    */     
/* 98 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\WMQCommonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */