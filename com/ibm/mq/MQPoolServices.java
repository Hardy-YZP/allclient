/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class MQPoolServices
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolServices.java";
/*     */   private Vector<MQPoolServicesEventListener> listeners;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.MQPoolServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolServices.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPoolServices() {
/*  66 */     super(MQSESSION.getJmqiEnv());
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
/*  81 */     this.listeners = new Vector<>();
/*     */     if (Trace.isOn) {
/*     */       Trace.entry(this, "com.ibm.mq.MQPoolServices", "<init>()");
/*     */     }
/*     */     if (Trace.isOn) {
/*     */       Trace.exit(this, "com.ibm.mq.MQPoolServices", "<init>()");
/*     */     }
/*     */   }
/*     */   
/*     */   public void addMQPoolServicesEventListener(MQPoolServicesEventListener eventListener) {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.MQPoolServices", "addMQPoolServicesEventListener(MQPoolServicesEventListener)", new Object[] { eventListener });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (eventListener == null) {
/*     */       
/*  99 */       NullPointerException traceRet1 = new NullPointerException();
/*     */       
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.throwing(this, "com.ibm.mq.MQPoolServices", "addMQPoolServicesEventListener(MQPoolServicesEventListener)", traceRet1);
/*     */       }
/*     */       
/* 105 */       throw traceRet1;
/*     */     } 
/*     */     
/* 108 */     if (this.listeners.isEmpty()) {
/* 109 */       MQEnvironment.registerPoolServices(this);
/*     */     }
/* 111 */     this.listeners.addElement(eventListener);
/*     */ 
/*     */     
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.MQPoolServices", "addMQPoolServicesEventListener(MQPoolServicesEventListener)");
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
/*     */   public void removeMQPoolServicesEventListener(MQPoolServicesEventListener eventListener) {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.mq.MQPoolServices", "removeMQPoolServicesEventListener(MQPoolServicesEventListener)", new Object[] { eventListener });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (eventListener == null) {
/*     */       
/* 136 */       NullPointerException traceRet1 = new NullPointerException();
/*     */       
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.throwing(this, "com.ibm.mq.MQPoolServices", "removeMQPoolServicesEventListener(MQPoolServicesEventListener)", traceRet1);
/*     */       }
/*     */       
/* 142 */       throw traceRet1;
/*     */     } 
/*     */     
/* 145 */     this.listeners.removeElement(eventListener);
/* 146 */     if (this.listeners.isEmpty()) {
/* 147 */       MQEnvironment.deregisterPoolServices(this);
/*     */     }
/*     */ 
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.MQPoolServices", "removeMQPoolServicesEventListener(MQPoolServicesEventListener)");
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
/*     */   public int getTokenCount() {
/* 165 */     int traceRet1 = MQEnvironment.poolTokenSet.size();
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.mq.MQPoolServices", "getTokenCount()", "getter", 
/* 168 */           Integer.valueOf(traceRet1));
/*     */     }
/* 170 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fireTokenAdded(MQPoolToken token) {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry(this, "com.ibm.mq.MQPoolServices", "fireTokenAdded(MQPoolToken)", new Object[] { token });
/*     */     }
/*     */ 
/*     */     
/* 186 */     MQPoolServicesEvent event = new MQPoolServicesEvent(this, 0, token);
/*     */     
/* 188 */     Vector<MQPoolServicesEventListener> v = (Vector<MQPoolServicesEventListener>)this.listeners.clone();
/* 189 */     for (MQPoolServicesEventListener l : v) {
/* 190 */       l.tokenAdded(event);
/*     */     }
/*     */ 
/*     */     
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.MQPoolServices", "fireTokenAdded(MQPoolToken)");
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
/*     */   void fireTokenRemoved(MQPoolToken token) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.MQPoolServices", "fireTokenRemoved(MQPoolToken)", new Object[] { token });
/*     */     }
/*     */ 
/*     */     
/* 213 */     MQPoolServicesEvent event = new MQPoolServicesEvent(this, 1, token);
/*     */     
/* 215 */     Vector<MQPoolServicesEventListener> v = (Vector<MQPoolServicesEventListener>)this.listeners.clone();
/* 216 */     for (MQPoolServicesEventListener l : v) {
/* 217 */       l.tokenRemoved(event);
/*     */     }
/*     */ 
/*     */     
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.mq.MQPoolServices", "fireTokenRemoved(MQPoolToken)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fireDefaultConnectionManagerChanged() {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.entry(this, "com.ibm.mq.MQPoolServices", "fireDefaultConnectionManagerChanged()");
/*     */     }
/*     */     
/* 237 */     MQPoolServicesEvent event = new MQPoolServicesEvent(this, 2);
/*     */     
/* 239 */     Vector<MQPoolServicesEventListener> v = (Vector<MQPoolServicesEventListener>)this.listeners.clone();
/* 240 */     for (MQPoolServicesEventListener l : v) {
/* 241 */       l.defaultConnectionManagerChanged(event);
/*     */     }
/*     */ 
/*     */     
/* 245 */     if (Trace.isOn)
/* 246 */       Trace.exit(this, "com.ibm.mq.MQPoolServices", "fireDefaultConnectionManagerChanged()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQPoolServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */