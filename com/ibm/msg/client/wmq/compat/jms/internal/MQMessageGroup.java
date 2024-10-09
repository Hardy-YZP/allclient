/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Hashtable;
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
/*     */ class MQMessageGroup
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageGroup.java";
/*     */   private Hashtable messageGroup;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageGroup.java");
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
/*     */   MQMessageGroup() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "<init>()");
/*     */     }
/*  62 */     this.messageGroup = new Hashtable<>();
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "<init>()");
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
/*     */   MQMessageReference removeMessage(MQMessageReference selector) {
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "removeMessage(MQMessageReference)", new Object[] { selector });
/*     */     }
/*     */     
/*  84 */     MQMessageReference result = null;
/*     */     
/*  86 */     result = (MQMessageReference)this.messageGroup.remove(selector);
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "removeMessage(MQMessageReference)", result);
/*     */     }
/*     */     
/*  92 */     return result;
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
/*     */   void addMessage(MQMessageReference message) {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "addMessage(MQMessageReference)", new Object[] { message });
/*     */     }
/*     */     
/* 109 */     this.messageGroup.put(message, message);
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "addMessage(MQMessageReference)");
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
/*     */   int size() {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "size()");
/*     */     }
/*     */     
/* 131 */     int result = this.messageGroup.size();
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "size()", 
/* 134 */           Integer.valueOf(result));
/*     */     }
/* 136 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clear() {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "clear()");
/*     */     }
/* 150 */     this.messageGroup.clear();
/* 151 */     if (Trace.isOn)
/* 152 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageGroup", "clear()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQMessageGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */