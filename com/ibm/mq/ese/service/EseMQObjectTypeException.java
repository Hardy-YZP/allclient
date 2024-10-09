/*    */ package com.ibm.mq.ese.service;
/*    */ 
/*    */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.HashMap;
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
/*    */ public class EseMQObjectTypeException
/*    */   extends EseMQException
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQObjectTypeException.java";
/*    */   private static final long serialVersionUID = -4428431366640289959L;
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.mq.ese.service.EseMQObjectTypeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQObjectTypeException.java");
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
/*    */   public EseMQObjectTypeException() {
/* 54 */     super(AmsErrorMessages.show_object, getMap());
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQObjectTypeException", "<init>()");
/*    */     }
/* 58 */     setReason(2043);
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQObjectTypeException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static final HashMap<String, Object> getMap() {
/* 66 */     HashMap<String, Object> inserts = new HashMap<>();
/* 67 */     inserts.put("AMS_INSERT_STRINGIFIABLE_OBJECT", Integer.valueOf(2043));
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.data("com.ibm.mq.ese.service.EseMQObjectTypeException", "getMap()", "getter", inserts);
/*    */     }
/* 71 */     return inserts;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\EseMQObjectTypeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */