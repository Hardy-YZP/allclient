/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jms.SyntaxException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderQueueBrowser;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import javax.jms.InvalidDestinationException;
/*     */ import javax.jms.InvalidSelectorException;
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
/*     */ public class MQQueueBrowser
/*     */   extends WMQPropertyContext
/*     */   implements ProviderQueueBrowser
/*     */ {
/*     */   private static final long serialVersionUID = 2083457851210189095L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueBrowser.java";
/*     */   private WMQDestination queue;
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueBrowser.java");
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
/*  80 */   private MQMessageSelector messageSelector = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private MQSession session;
/*     */ 
/*     */ 
/*     */   
/*  88 */   private Vector enumerations = new Vector();
/*     */ 
/*     */   
/*  91 */   private MQJMSMessage baseMessage = null;
/*     */   private boolean specialSelector = false;
/*  93 */   private MQGetMessageOptions selectorGMO = new MQGetMessageOptions();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String PROBE_01 = "01";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQQueueBrowser(WMQDestination queue, String selectorString, MQSession session) throws JMSException {
/* 107 */     super(null);
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)", new Object[] { queue, selectorString, session });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 114 */       if (queue.isQueue()) {
/* 115 */         this.queue = queue;
/*     */       } else {
/* 117 */         String key = "MQJMS0003";
/* 118 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 119 */         InvalidDestinationException invalidDestinationException = new InvalidDestinationException(msg, key);
/* 120 */         if (Trace.isOn) {
/* 121 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)", (Throwable)invalidDestinationException, 1);
/*     */         }
/*     */         
/* 124 */         throw invalidDestinationException;
/*     */       } 
/*     */ 
/*     */       
/* 128 */       if (selectorString != null && !selectorString.equals("")) {
/* 129 */         this.messageSelector = new MQMessageSelector();
/*     */         
/* 131 */         this.baseMessage = new MQJMSMessage();
/*     */         
/*     */         try {
/* 134 */           this.selectorGMO.matchOptions = 0;
/* 135 */           this.specialSelector = this.messageSelector.setSelector(selectorString, this.baseMessage, this.selectorGMO);
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
/* 146 */           if (Trace.isOn) {
/* 147 */             Trace.traceData(this, "Selector set, special case flag is " + this.specialSelector, null);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 153 */           if (!this.specialSelector) {
/* 154 */             this.selectorGMO.matchOptions = 0;
/*     */           }
/*     */         }
/* 157 */         catch (SyntaxException e) {
/* 158 */           if (Trace.isOn) {
/* 159 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)", (Throwable)e, 1);
/*     */           }
/*     */ 
/*     */           
/* 163 */           this.messageSelector = null;
/*     */           
/* 165 */           String key = "MQJMS0004";
/* 166 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 167 */           InvalidSelectorException je = new InvalidSelectorException(msg, key);
/* 168 */           je.setLinkedException((Exception)e);
/* 169 */           je.initCause((Throwable)e);
/*     */           
/* 171 */           if (Trace.isOn) {
/* 172 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)", (Throwable)je, 2);
/*     */           }
/*     */           
/* 175 */           throw je;
/*     */         } 
/*     */       } 
/*     */       
/* 179 */       this.session = session;
/*     */     }
/* 181 */     catch (JMSException je) {
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)", (Throwable)je, 3);
/*     */       }
/*     */       
/* 190 */       throw je;
/*     */     } 
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "<init>(WMQDestination,String,MQSession)");
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
/*     */   public Enumeration getEnumeration() throws JMSException {
/* 213 */     Enumeration result = null;
/*     */ 
/*     */ 
/*     */     
/* 217 */     MQQueue baseQueue = this.session.getServicesMgr().getQueueForBrowse(this.queue, this.session);
/*     */ 
/*     */     
/* 220 */     result = new MQQueueEnumeration(this.session, baseQueue, this.messageSelector, this, this.queue, this.baseMessage, this.selectorGMO);
/*     */     
/* 222 */     this.enumerations.addElement(result);
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "getEnumeration()", "getter", result);
/*     */     }
/*     */     
/* 227 */     return result;
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
/*     */   public void close(boolean closingFromSession) throws JMSException {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "close(boolean)", new Object[] {
/* 243 */             Boolean.valueOf(closingFromSession)
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 248 */     for (int i = 0; i < this.enumerations.size(); i++) {
/* 249 */       MQQueueEnumeration enumVar = this.enumerations.elementAt(i);
/* 250 */       MQQueue queue = enumVar.getQueue();
/* 251 */       if (queue != null) {
/*     */         
/* 253 */         try { queue.close();
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
/* 268 */           if (Trace.isOn) {
/* 269 */             Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "close(boolean)");
/*     */           }
/*     */ 
/*     */           
/* 273 */           queue = null; } catch (MQException e2) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "close(boolean)", (Throwable)e2);  HashMap<Object, Object> ffstData = new HashMap<>(); ffstData.put("Exception", e2); ffstData.put("Message", "MQJMS2000"); Trace.ffst(this, "close()", "01", ffstData, JMSException.class); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "close(boolean)");  queue = null; }
/*     */       
/*     */       }
/*     */     } 
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "close(boolean)");
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
/*     */   protected void removeEnumeration(MQQueueEnumeration mqEnumeration) {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "removeEnumeration(MQQueueEnumeration)", new Object[] { mqEnumeration });
/*     */     }
/*     */     
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.traceData(this, "Removing enumeration " + mqEnumeration.toString(), null);
/*     */     }
/* 299 */     this.enumerations.remove(mqEnumeration);
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "removeEnumeration(MQQueueEnumeration)");
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "readObject(ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 317 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "readObject(ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 322 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "writeObject(ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 335 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "writeObject(ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 340 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "hashCode()");
/*     */     }
/*     */     
/* 353 */     int traceRet1 = super.hashCode();
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "hashCode()", 
/* 356 */           Integer.valueOf(traceRet1));
/*     */     }
/* 358 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "equals(Object)", new Object[] { o });
/*     */     }
/*     */     
/* 371 */     boolean traceRet1 = super.equals(o);
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueBrowser", "equals(Object)", 
/* 374 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */