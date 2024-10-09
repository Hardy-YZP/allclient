/*    */ package com.ibm.mq.ese.util;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.io.File;
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
/*    */ public class PathResolver
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/PathResolver.java";
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.mq.ese.util.PathResolver", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/PathResolver.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   private static final String DEFAULT_KEYSTORE_KONF = File.separator + ".mqs" + File.separator + "keystore.conf";
/*    */ 
/*    */ 
/*    */   
/*    */   private static final String MQS_KEYSTORE_CONF = "MQS_KEYSTORE_CONF";
/*    */ 
/*    */ 
/*    */   
/*    */   public static File getKeystorePath() {
/* 53 */     String path = SystemUtils.getSystemProperty("MQS_KEYSTORE_CONF", null);
/* 54 */     if (path == null || path.length() == 0) {
/* 55 */       path = SystemUtils.getSystemProperty("user.home", "");
/* 56 */       path = path + DEFAULT_KEYSTORE_KONF;
/*    */     } 
/* 58 */     File traceRet1 = new File(path);
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.data("com.ibm.mq.ese.util.PathResolver", "getKeystorePath()", "getter", traceRet1);
/*    */     }
/* 62 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\es\\util\PathResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */