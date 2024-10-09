/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ class State
/*     */ {
/*     */   static final int NOT_INITIALIZED = 0;
/*     */   static final int STARTED = 1;
/*     */   static final int STOPPED = 2;
/*     */   static final int CLOSED = 3;
/*     */   static final int DISCONNECTED = 4;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.jms.internal.State", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/State.java");
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
/*  56 */   int minState = 0;
/*  57 */   public int maxState = 4;
/*     */   
/*  59 */   int state = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   State(int initialState) {
/*  68 */     if (Trace.isOn)
/*  69 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.State", "<init>(int)", new Object[] {
/*  70 */             Integer.valueOf(initialState)
/*     */           }); 
/*  72 */     this.state = initialState;
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.State", "<init>(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getState() {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.data(this, "com.ibm.msg.client.jms.internal.State", "getState()", "getter", 
/*  83 */           Integer.valueOf(this.state));
/*     */     }
/*  85 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setState(int newState) {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.data(this, "com.ibm.msg.client.jms.internal.State", "setState(int)", "setter", 
/*  97 */           Integer.valueOf(newState));
/*     */     }
/*     */     
/* 100 */     if (newState >= this.minState && newState <= this.maxState) {
/* 101 */       synchronized (this) {
/* 102 */         this.state = newState;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   synchronized boolean close() {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.State", "close()");
/*     */     }
/*     */     
/* 113 */     if (this.state == 3) {
/* 114 */       if (Trace.isOn) {
/* 115 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.State", "close()", Boolean.valueOf(true), 1);
/*     */       }
/*     */       
/* 118 */       return true;
/*     */     } 
/*     */     
/* 121 */     this.state = 3;
/*     */ 
/*     */ 
/*     */     
/* 125 */     notifyAll();
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.State", "close()", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkNotClosed(String message) throws JMSException {
/* 141 */     if (this.state == 3) {
/* 142 */       JMSException je = (JMSException)JmsErrorUtils.createException(message, null);
/* 143 */       throw je;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isClosed() {
/* 148 */     boolean traceRet1 = (this.state == 3);
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.data(this, "com.ibm.msg.client.jms.internal.State", "isClosed()", "getter", 
/* 151 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 153 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean equals(int otherState) {
/* 159 */     boolean traceRet1 = (this.state == otherState);
/* 160 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean equals(Object otherState) {
/* 166 */     if (otherState instanceof State) {
/* 167 */       boolean traceRet1 = (this.state == ((State)otherState).state);
/* 168 */       return traceRet1;
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 175 */     return this.state;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toString(int i) {
/* 180 */     StringBuffer text = new StringBuffer();
/* 181 */     if (0 == i) {
/* 182 */       text.append("NOT INITIALIZED");
/* 183 */     } else if (1 == i) {
/* 184 */       text.append("STARTED");
/* 185 */     } else if (2 == i) {
/* 186 */       text.append("STOPPED");
/* 187 */     } else if (3 == i) {
/* 188 */       text.append("CLOSED");
/* 189 */     } else if (4 == i) {
/* 190 */       text.append("DISCONNECTED");
/*     */     } else {
/* 192 */       text.append("??UNKNOWN??");
/*     */     } 
/* 194 */     return text.toString();
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
/*     */   public String toString() {
/* 206 */     StringBuffer text = new StringBuffer(super.toString());
/* 207 */     text.append(" ");
/* 208 */     text.append(toString(this.state));
/*     */     
/* 210 */     return text.toString();
/*     */   }
/*     */   
/*     */   State() {}
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\State.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */