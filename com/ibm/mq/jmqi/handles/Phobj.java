/*    */ package com.ibm.mq.jmqi.handles;
/*    */ 
/*    */ import com.ibm.mq.constants.CMQC;
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.JmqiObject;
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
/*    */ public class Phobj
/*    */   extends JmqiObject
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Phobj.java";
/*    */   private volatile Hobj hobj;
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.mq.jmqi.handles.Phobj", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Phobj.java");
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
/*    */   public Phobj(JmqiEnvironment env) {
/* 58 */     super(env);
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.Phobj", "<init>(JmqiEnvironment)", new Object[] { env });
/*    */     }
/*    */     
/* 63 */     this.hobj = CMQC.jmqi_MQHO_NONE;
/*    */     
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.Phobj", "<init>(JmqiEnvironment)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Hobj getHobj() {
/* 76 */     if (Trace.isOn) {
/* 77 */       Trace.data(this, "com.ibm.mq.jmqi.handles.Phobj", "getHobj()", "getter", this.hobj);
/*    */     }
/* 79 */     return this.hobj;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHobj(Hobj hobj) {
/* 87 */     if (Trace.isOn) {
/* 88 */       Trace.data(this, "com.ibm.mq.jmqi.handles.Phobj", "setHobj(Hobj)", "setter", hobj);
/*    */     }
/* 90 */     this.hobj = hobj;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 99 */     return String.format("Phobj@0x%8x - %s", new Object[] { Integer.valueOf(System.identityHashCode(this)), (this.hobj != null) ? this.hobj.toString() : "<null>" });
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\Phobj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */