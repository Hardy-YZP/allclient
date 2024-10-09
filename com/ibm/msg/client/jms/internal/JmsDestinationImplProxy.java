/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import javax.jms.JMSException;
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
/*     */ public class JmsDestinationImplProxy
/*     */   extends JmsDestinationImpl
/*     */ {
/*     */   private static final long serialVersionUID = -1297373271476937437L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsDestinationImplProxy.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsDestinationImplProxy.java");
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
/*     */   public static void decrementUseCount(JmsDestinationImpl destination) {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "decrementUseCount(JmsDestinationImpl)", new Object[] { destination });
/*     */     }
/*     */     
/*  72 */     _decrementUseCount(destination);
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "decrementUseCount(JmsDestinationImpl)");
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
/*     */   public static void incrementUseCount(JmsDestinationImpl destination) {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "incrementUseCount(JmsDestinationImpl)", new Object[] { destination });
/*     */     }
/*     */     
/*  90 */     _incrementUseCount(destination);
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "incrementUseCount(JmsDestinationImpl)");
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
/*     */   public static void setProviderDestination(JmsDestinationImpl destination) throws JMSException {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.data("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "setProviderDestination(JmsDestinationImpl)", "setter", destination);
/*     */     }
/*     */     
/* 109 */     _setProviderDestination(destination);
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
/*     */   public static ProviderDestination getProviderDestination(JmsDestinationImpl destination) throws JMSException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "getProviderDestination(JmsDestinationImpl)", new Object[] { destination });
/*     */     }
/*     */     
/* 125 */     ProviderDestination traceRet1 = _getProviderDestination(destination);
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsDestinationImplProxy", "getProviderDestination(JmsDestinationImpl)", traceRet1);
/*     */     }
/*     */     
/* 130 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsDestinationImplProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */