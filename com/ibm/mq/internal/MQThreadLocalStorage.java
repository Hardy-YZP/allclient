/*    */ package com.ibm.mq.internal;
/*    */ 
/*    */ import com.ibm.mq.XAtoJTA;
/*    */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
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
/*    */ public class MQThreadLocalStorage
/*    */   extends JmqiComponentTls
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/internal/MQThreadLocalStorage.java";
/*    */   private XAtoJTA xaToJta;
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn) {
/* 35 */       Trace.data("com.ibm.mq.internal.MQThreadLocalStorage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/internal/MQThreadLocalStorage.java");
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
/*    */   public XAtoJTA getXaToJta() {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.data(this, "com.ibm.mq.internal.MQThreadLocalStorage", "getXaToJta()", "getter", this.xaToJta);
/*    */     }
/*    */     
/* 57 */     return this.xaToJta;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setXaToJta(XAtoJTA xaToJta) {
/* 64 */     if (Trace.isOn) {
/* 65 */       Trace.data(this, "com.ibm.mq.internal.MQThreadLocalStorage", "setXaToJta(XAtoJTA)", "setter", xaToJta);
/*    */     }
/*    */     
/* 68 */     this.xaToJta = xaToJta;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\internal\MQThreadLocalStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */