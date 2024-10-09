/*    */ package com.ibm.msg.client.wmq.common;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
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
/*    */ public class WMQConnectionNameList
/*    */   extends ArrayList<WMQConnectionName>
/*    */ {
/*    */   private static final long serialVersionUID = -2884038860014130163L;
/*    */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQConnectionNameList.java";
/*    */   
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.msg.client.wmq.common.WMQConnectionNameList", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQConnectionNameList.java");
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
/*    */   public String toString() {
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.WMQConnectionNameList", "toString()");
/*    */     }
/* 59 */     StringBuilder buffer = new StringBuilder();
/* 60 */     Iterator<WMQConnectionName> iterator = iterator();
/* 61 */     while (iterator.hasNext()) {
/* 62 */       WMQConnectionName wcn = iterator.next();
/* 63 */       buffer.append(",");
/* 64 */       buffer.append(wcn);
/*    */     } 
/*    */ 
/*    */     
/* 68 */     buffer.deleteCharAt(0);
/* 69 */     String traceRet1 = buffer.toString();
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.WMQConnectionNameList", "toString()", traceRet1);
/*    */     }
/*    */     
/* 74 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\WMQConnectionNameList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */