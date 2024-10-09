/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.EventObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQPoolServicesEvent
/*     */   extends EventObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolServicesEvent.java";
/*     */   static final long serialVersionUID = 1653589817060739739L;
/*     */   public static final int TOKEN_ADDED = 0;
/*     */   public static final int TOKEN_REMOVED = 1;
/*     */   public static final int DEFAULT_POOL_CHANGED = 2;
/*     */   protected int id;
/*     */   protected transient MQPoolToken token;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.MQPoolServicesEvent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolServicesEvent.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPoolServicesEvent(Object source, int eventID, MQPoolToken token) {
/*  89 */     super(source);
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.MQPoolServicesEvent", "<init>(Object,int,MQPoolToken)", new Object[] { source, 
/*  92 */             Integer.valueOf(eventID), token });
/*     */     }
/*  94 */     this.id = eventID;
/*  95 */     this.token = token;
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.mq.MQPoolServicesEvent", "<init>(Object,int,MQPoolToken)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPoolServicesEvent(Object source, int eventID) {
/* 111 */     super(source);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.MQPoolServicesEvent", "<init>(Object,int)", new Object[] { source, 
/* 114 */             Integer.valueOf(eventID) });
/*     */     }
/* 116 */     this.id = eventID;
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.MQPoolServicesEvent", "<init>(Object,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.data(this, "com.ibm.mq.MQPoolServicesEvent", "getId()", "getter", Integer.valueOf(this.id));
/*     */     }
/*     */     
/* 137 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPoolToken getToken() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.MQPoolServicesEvent", "getToken()", "getter", this.token);
/*     */     }
/* 151 */     return this.token;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQPoolServicesEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */