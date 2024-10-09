/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.DumpableObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
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
/*     */ public class SendQueueProcessorMonitorImpl
/*     */   implements SendQueueProcessorMonitor, DumpableObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/SendQueueProcessorMonitorImpl.java";
/*     */   private JmsSessionImpl session;
/*     */   private JmsSessionImpl.SendQueueProcessor2 processor;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/SendQueueProcessorMonitorImpl.java");
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
/*     */   public SendQueueProcessorMonitorImpl(JmsSessionImpl session, JmsSessionImpl.SendQueueProcessor2 processor) {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "<init>(JmsSessionImpl,SendQueueProcessor2)", new Object[] { session, processor });
/*     */     }
/*     */ 
/*     */     
/*  67 */     this.session = session;
/*  68 */     this.processor = processor;
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "<init>(JmsSessionImpl,SendQueueProcessor2)");
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
/*     */   public int getCurrentSendQueueDepth() {
/*  82 */     int traceRet1 = this.processor.size();
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "getCurrentSendQueueDepth()", "getter", 
/*  85 */           Integer.valueOf(traceRet1));
/*     */     }
/*  87 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxSendQueueDepth(int maxDepth) {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "setMaxSendQueueDepth(int)", "setter", 
/*  97 */           Integer.valueOf(maxDepth));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.session.setMaxQueueDepth(maxDepth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSendQueueDepth() {
/* 112 */     int traceRet1 = this.processor.getMaximumQueueDepth();
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "getMaxSendQueueDepth()", "getter", 
/* 115 */           Integer.valueOf(traceRet1));
/*     */     }
/* 117 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProcessorState() {
/* 126 */     String traceRet1 = this.processor.getState().toString();
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "getProcessorState()", "getter", traceRet1);
/*     */     }
/*     */     
/* 131 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String viewQueueContents() {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "viewQueueContents()");
/*     */     }
/*     */ 
/*     */     
/* 144 */     StringBuilder buffer = new StringBuilder();
/*     */ 
/*     */     
/* 147 */     Iterator<JmsSessionImpl.SendDetails> it = this.processor.iterator();
/* 148 */     while (it.hasNext()) {
/* 149 */       JmsSessionImpl.SendDetails details = it.next();
/* 150 */       JmsDestinationImpl dest = details.dest;
/* 151 */       JmsMessageProducerImpl prod = details.producer;
/* 152 */       buffer.append("Producer " + prod.hashCode() + ": sending to " + dest.toString());
/* 153 */       buffer.append('\n');
/*     */     } 
/*     */     
/* 156 */     String traceRet1 = buffer.toString();
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "viewQueueContents()", traceRet1);
/*     */     }
/*     */     
/* 161 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getProcessorTimeout() {
/* 170 */     long traceRet1 = this.processor.getPollTimeout();
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "getProcessorTimeout()", "getter", 
/* 173 */           Long.valueOf(traceRet1));
/*     */     }
/* 175 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProcessorTimeout(long timeout) {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "setProcessorTimeout(long)", "setter", 
/* 185 */           Long.valueOf(timeout));
/*     */     }
/* 187 */     this.processor.setPollTimeout(timeout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestClose() {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "requestClose()");
/*     */     }
/*     */ 
/*     */     
/* 201 */     this.processor.close();
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "requestClose()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter pw, int level) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 217 */             Integer.valueOf(level) });
/*     */     }
/* 219 */     String prefix = Trace.buildPrefix(level);
/* 220 */     pw.format("%s%s@%x (%s@%x(%s@%x))%n", new Object[] { prefix, safeClassName(this), Integer.valueOf(safeHashCode(this)), safeClassName(this.processor), Integer.valueOf(safeHashCode(this.processor)), safeClassName(this.session), 
/* 221 */           Integer.valueOf(safeHashCode(this.session)) });
/* 222 */     pw.format("%s  Current state %s%n", new Object[] { prefix, getProcessorState() });
/* 223 */     pw.format("%s  Timeout %s%n", new Object[] { prefix, Long.valueOf(getProcessorTimeout()) });
/* 224 */     pw.format("%s  Current queue depth %d (max %d)%n", new Object[] { prefix, Integer.valueOf(getCurrentSendQueueDepth()), Integer.valueOf(getMaxSendQueueDepth()) });
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "dump(PrintWriter,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int safeHashCode(Object o) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "safeHashCode(Object)", new Object[] { o });
/*     */     }
/*     */     
/* 238 */     int traceRet1 = (o == null) ? 0 : o.hashCode();
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "safeHashCode(Object)", 
/* 241 */           Integer.valueOf(traceRet1));
/*     */     }
/* 243 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private String safeClassName(Object o) {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "safeClassName(Object)", new Object[] { o });
/*     */     }
/*     */     
/* 251 */     String traceRet1 = (o == null) ? "<null>" : o.getClass().getName();
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessorMonitorImpl", "safeClassName(Object)", traceRet1);
/*     */     }
/*     */     
/* 256 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\SendQueueProcessorMonitorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */