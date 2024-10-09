/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.jms.Session;
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
/*     */ public class JmsTls
/*     */ {
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTls", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTls.java");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean inCompletionListener = false;
/*     */   
/*  42 */   private Session completionListenerSession = null;
/*     */   
/*     */   private boolean inMessageListener = false;
/*  45 */   private Session messageListenerSession = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inFinalizer = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inCompletionListener() {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "inCompletionListener()");
/*     */     }
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "inCompletionListener()", 
/*  59 */           Boolean.valueOf(this.inCompletionListener));
/*     */     }
/*  61 */     return this.inCompletionListener;
/*     */   }
/*     */   
/*     */   public Session completionListenerSession() {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "completionListenerSession()");
/*     */     }
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "completionListenerSession()", this.completionListenerSession);
/*     */     }
/*     */     
/*  72 */     return this.completionListenerSession;
/*     */   }
/*     */   
/*     */   public boolean inMessageListener() {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "inMessageListener()");
/*     */     }
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "inMessageListener()", 
/*  81 */           Boolean.valueOf(this.inMessageListener));
/*     */     }
/*  83 */     return this.inMessageListener;
/*     */   }
/*     */   
/*     */   public Session messageListenerSession() {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "messageListenerSession()");
/*     */     }
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "messageListenerSession()", this.messageListenerSession);
/*     */     }
/*     */     
/*  94 */     return this.messageListenerSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inCompletionListener(boolean inCompletionListener, Session session) {
/* 102 */     if (Trace.isOn)
/* 103 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "inCompletionListener(boolean,Session)", new Object[] {
/*     */             
/* 105 */             Boolean.valueOf(inCompletionListener), session
/*     */           }); 
/* 107 */     this.inCompletionListener = inCompletionListener;
/* 108 */     this.completionListenerSession = session;
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "inCompletionListener(boolean,Session)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inMessageListener(boolean inMessageListener, Session session) {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "inMessageListener(boolean,Session)", new Object[] {
/* 123 */             Boolean.valueOf(inMessageListener), session
/*     */           });
/*     */     }
/* 126 */     this.inMessageListener = inMessageListener;
/* 127 */     this.messageListenerSession = session;
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "inMessageListener(boolean,Session)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 135 */   private static final ThreadLocal<JmsTls> myInstance = new ThreadLocal<JmsTls>()
/*     */     {
/*     */       protected JmsTls initialValue()
/*     */       {
/* 139 */         JmsTls traceRet1 = new JmsTls();
/* 140 */         if (Trace.isOn) {
/* 141 */           Trace.data("com.ibm.msg.client.jms.internal.JmsTls", "ThreadLocal<JmsTls>()", "getter", traceRet1);
/*     */         }
/* 143 */         return traceRet1;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public static JmsTls getInstance() {
/* 149 */     JmsTls traceRet1 = myInstance.get();
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTls", "getInstance()", "getter", traceRet1);
/*     */     }
/* 153 */     return traceRet1;
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
/*     */   public boolean setFinalizer() {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "setFinalizer()");
/*     */     }
/* 169 */     if (this.inFinalizer) {
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "setFinalizer()", 
/* 172 */             Boolean.valueOf(false), 1);
/*     */       }
/* 174 */       return false;
/*     */     } 
/*     */     
/* 177 */     this.inFinalizer = true;
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "setFinalizer()", 
/* 180 */           Boolean.valueOf(true), 2);
/*     */     }
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inFinalizer() {
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "inFinalizer()");
/*     */     }
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "inFinalizer()", 
/* 195 */           Boolean.valueOf(this.inFinalizer));
/*     */     }
/* 197 */     return this.inFinalizer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetFinalizer() {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTls", "unsetFinalizer()");
/*     */     }
/* 207 */     this.inFinalizer = false;
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTls", "unsetFinalizer()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */