/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsCapabilityContext;
/*     */ import java.util.Map;
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
/*     */ public class JmsCapabilityContextImpl
/*     */   extends JmsReadablePropertyContextImpl
/*     */   implements JmsCapabilityContext
/*     */ {
/*     */   private static final long serialVersionUID = 8239416421696284451L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsCapabilityContextImpl.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsCapabilityContextImpl.java");
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
/*     */   public JmsCapabilityContextImpl() {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "<init>()");
/*     */     }
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "<init>()");
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
/*     */   public JmsCapabilityContextImpl(Map<String, Object> propsTable, boolean doCopy) {
/*  78 */     super(propsTable, doCopy);
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "<init>(Map<String , Object>,boolean)", new Object[] { propsTable, 
/*  81 */             Boolean.valueOf(doCopy) });
/*     */     }
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "<init>(Map<String , Object>,boolean)");
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
/*     */   public JmsCapabilityContextImpl(JmsCapabilityContextImpl other, boolean doCopy) {
/*  99 */     super(other, doCopy);
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "<init>(JmsCapabilityContextImpl,boolean)", new Object[] { other, 
/* 102 */             Boolean.valueOf(doCopy) });
/*     */     }
/* 104 */     if (Trace.isOn)
/* 105 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCapabilityContextImpl", "<init>(JmsCapabilityContextImpl,boolean)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsCapabilityContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */