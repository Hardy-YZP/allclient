/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.disthub2.impl.matching.MatchTarget;
/*      */ import com.ibm.mq.jms.SyntaxException;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderConnectionBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReferenceHandler;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import javax.jms.InvalidSelectorException;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQConnectionBrowser
/*      */   extends MatchTarget
/*      */   implements ProviderConnectionBrowser
/*      */ {
/*      */   static {
/*   64 */     if (Trace.isOn) {
/*   65 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQConnectionBrowser.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ReceiverFlag
/*      */   {
/*      */     boolean receiving = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   89 */   private static int matchTargetCount = 0;
/*      */ 
/*      */   
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQConnectionBrowser.java";
/*      */ 
/*      */   
/*      */   private MQQueueAgent agent;
/*      */   
/*      */   private MQConnection connection;
/*      */   
/*      */   private boolean correlIdAsIdentity = true;
/*      */   
/*      */   private WMQDestination dest;
/*      */   
/*      */   private boolean isDurable = false;
/*      */   
/*      */   private JMSException jmsException;
/*      */   
/*  107 */   private MQMessageSelector messageSelector = new MQMessageSelector();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String mqQueueName;
/*      */ 
/*      */ 
/*      */   
/*  116 */   private MQSubscription mqsub = null;
/*      */ 
/*      */   
/*      */   private volatile ProviderMessageReferenceHandler mrh;
/*      */   
/*      */   private boolean pubSub = false;
/*      */   
/*      */   private boolean qAgentActive = false;
/*      */   
/*  125 */   private Object qaLock = new Object();
/*      */   
/*      */   private int quantityHint;
/*  128 */   ReceiverFlag receiveFlag = new ReceiverFlag();
/*      */   
/*      */   protected MQConnectionBrowser(MQConnection conn, WMQDestination dest, String selectorString, ProviderMessageReferenceHandler mrh, int quantityHint) throws JMSException {
/*  131 */     super(4, "JMSND:browser" + Integer.toString(++matchTargetCount));
/*  132 */     if (Trace.isOn) {
/*  133 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "<init>(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int)", new Object[] { conn, dest, selectorString, mrh, 
/*      */             
/*  135 */             Integer.valueOf(quantityHint) });
/*      */     }
/*      */     
/*  138 */     if (Trace.isOn) {
/*  139 */       Trace.traceData(this, "Already returned from parent constructor.", null);
/*      */     }
/*  141 */     MQConnectionBrowserInit(conn, dest, selectorString, mrh, quantityHint, null);
/*      */     
/*  143 */     if (Trace.isOn) {
/*  144 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "<init>(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQConnectionBrowser(MQConnection conn, WMQDestination dest, String selectorString, ProviderMessageReferenceHandler mrh, int quantityHint, String name) throws JMSException {
/*  153 */     super(4, "JMS:" + name);
/*  154 */     if (Trace.isOn) {
/*  155 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "<init>(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", new Object[] { conn, dest, selectorString, mrh, 
/*      */             
/*  157 */             Integer.valueOf(quantityHint), name });
/*      */     }
/*      */     
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.traceData(this, "Already passed to parent constructor.", null);
/*      */     }
/*      */     
/*  164 */     MQConnectionBrowserInit(conn, dest, selectorString, mrh, quantityHint, name);
/*      */     
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "<init>(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void activateQueueAgent() throws JMSException {
/*  179 */     if (Trace.isOn) {
/*  180 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "activateQueueAgent()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  185 */       boolean act = false;
/*  186 */       synchronized (this.qaLock) {
/*  187 */         if (!this.qAgentActive) {
/*  188 */           act = true;
/*  189 */           this.qAgentActive = true;
/*      */         } 
/*      */       } 
/*  192 */       if (act) {
/*  193 */         this.agent.activate(this.connection);
/*      */       }
/*      */     }
/*  196 */     catch (JMSException je) {
/*  197 */       if (Trace.isOn) {
/*  198 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "activateQueueAgent()", (Throwable)je);
/*      */       }
/*      */       
/*  201 */       if (Trace.isOn) {
/*  202 */         Trace.traceData(this, "Leaving via Exception", null);
/*      */       }
/*  204 */       if (Trace.isOn) {
/*  205 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "activateQueueAgent()", (Throwable)je);
/*      */       }
/*      */       
/*  208 */       throw je;
/*      */     } 
/*      */     
/*  211 */     if (Trace.isOn) {
/*  212 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "activateQueueAgent()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkException() throws JMSException {
/*  226 */     if (Trace.isOn) {
/*  227 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "checkException()");
/*      */     }
/*      */     
/*  230 */     if (this.jmsException != null) {
/*  231 */       if (Trace.isOn) {
/*  232 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "checkException()", (Throwable)this.jmsException);
/*      */       }
/*      */       
/*  235 */       throw this.jmsException;
/*      */     } 
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "checkException()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close(boolean closingFromConnection) throws JMSException {
/*  251 */     if (Trace.isOn) {
/*  252 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)", new Object[] {
/*  253 */             Boolean.valueOf(closingFromConnection)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/*  258 */       deactivateQueueAgent();
/*  259 */       this.connection.removeBrowser(this);
/*      */       
/*      */       try {
/*  262 */         if (this.agent != null) {
/*  263 */           this.agent.removeBrowser(this);
/*      */         }
/*      */       }
/*  266 */       catch (JMSException je) {
/*  267 */         if (Trace.isOn) {
/*  268 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  271 */         if (Trace.isOn) {
/*  272 */           Trace.traceData(this, "MQQueueAgentThread produced exception", null);
/*  273 */           if (this.jmsException != null);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  282 */         this.jmsException = je;
/*      */       } 
/*      */       
/*  285 */       if (this.pubSub) {
/*      */         
/*      */         try {
/*  288 */           this.mqsub.close();
/*      */         }
/*  290 */         catch (JMSException je) {
/*  291 */           if (Trace.isOn) {
/*  292 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/*  296 */           if (Trace.isOn) {
/*  297 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)", (Throwable)je, 1);
/*      */           }
/*      */           
/*  300 */           throw je;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  306 */       this.mrh.endDeliver();
/*      */     
/*      */     }
/*  309 */     catch (JMSException je) {
/*  310 */       if (Trace.isOn) {
/*  311 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  314 */       if (Trace.isOn) {
/*  315 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  318 */       throw je;
/*      */     } 
/*      */     
/*  321 */     if (Trace.isOn) {
/*  322 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "close(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void deactivateQueueAgent() throws JMSException {
/*  336 */     if (Trace.isOn) {
/*  337 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deactivateQueueAgent()");
/*      */     }
/*      */     
/*  340 */     boolean act = false;
/*  341 */     synchronized (this.qaLock) {
/*  342 */       if (this.qAgentActive) {
/*  343 */         act = true;
/*  344 */         this.qAgentActive = false;
/*      */       } 
/*      */     } 
/*      */     
/*  348 */     if (act) {
/*  349 */       this.agent.deactivate();
/*      */     }
/*  351 */     if (Trace.isOn) {
/*  352 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deactivateQueueAgent()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean deliver(MQMessageReference message) {
/*  368 */     if (Trace.isOn) {
/*  369 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", new Object[] { message });
/*      */     }
/*      */     
/*  372 */     boolean result = false;
/*      */     
/*      */     try {
/*  375 */       synchronized (this) {
/*  376 */         if (null == this.mrh) {
/*  377 */           if (Trace.isOn) {
/*  378 */             Trace.traceData(this, "MRH is null!", null);
/*      */           }
/*  380 */           if (Trace.isOn) {
/*  381 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", 
/*  382 */                 Boolean.valueOf(false), 1);
/*      */           }
/*  384 */           return false;
/*      */         } 
/*      */         try {
/*  387 */           this.mrh.handleMessageReference(message);
/*      */         }
/*  389 */         catch (JMSException jmse) {
/*  390 */           if (Trace.isOn) {
/*  391 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", (Throwable)jmse, 1);
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*  399 */         catch (RuntimeException re) {
/*  400 */           if (Trace.isOn) {
/*  401 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", re, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  414 */           if (Trace.isOn) {
/*  415 */             Trace.traceData(this, "Caught RuntimeException from MessageReferenceHandler: " + re, null);
/*  416 */             Trace.traceData(this, "Not throwing exception. ProviderMessage should be redelivered after timeout", null);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  421 */           if (Trace.isOn) {
/*  422 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", re);
/*      */           }
/*      */           
/*  425 */           throw re;
/*      */         } 
/*      */       } 
/*  428 */       result = true;
/*  429 */       if (Trace.isOn) {
/*  430 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", 
/*  431 */             Boolean.valueOf(result), 2);
/*      */       }
/*  433 */       return result;
/*      */     }
/*  435 */     catch (RuntimeException re) {
/*  436 */       if (Trace.isOn) {
/*  437 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", re, 3);
/*      */       }
/*      */       
/*  440 */       if (Trace.isOn) {
/*  441 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "deliver(MQMessageReference)", 
/*  442 */             Boolean.valueOf(result), 3);
/*      */       }
/*  444 */       return result;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void endDeliver() {
/*  455 */     if (Trace.isOn) {
/*  456 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "endDeliver()");
/*      */     }
/*      */     
/*      */     try {
/*  460 */       this.mrh.endDeliver();
/*      */     }
/*  462 */     catch (JMSException jmse) {
/*  463 */       if (Trace.isOn) {
/*  464 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "endDeliver()", (Throwable)jmse);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "endDeliver()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQConnection getConnection() {
/*  479 */     if (Trace.isOn) {
/*  480 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getConnection()", "getter", this.connection);
/*      */     }
/*      */     
/*  483 */     return this.connection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected byte[] getCorrelId() {
/*  491 */     if (Trace.isOn) {
/*  492 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getCorrelId()");
/*      */     }
/*      */     
/*  495 */     if (this.mqsub != null) {
/*  496 */       byte[] traceRet1 = this.mqsub.getCorrelationId();
/*  497 */       if (Trace.isOn) {
/*  498 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getCorrelId()", traceRet1, 1);
/*      */       }
/*      */       
/*  501 */       return traceRet1;
/*      */     } 
/*  503 */     if (Trace.isOn) {
/*  504 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getCorrelId()", null, 2);
/*      */     }
/*      */     
/*  507 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSException getException() {
/*  518 */     if (Trace.isOn) {
/*  519 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getException()", "getter", this.jmsException);
/*      */     }
/*      */     
/*  522 */     return this.jmsException;
/*      */   }
/*      */ 
/*      */   
/*      */   protected MQMessageSelector getMessageSelector() {
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getMessageSelector()", "getter", this.messageSelector);
/*      */     }
/*      */     
/*  531 */     return this.messageSelector;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getQuantityHint() {
/*  536 */     if (Trace.isOn) {
/*  537 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getQuantityHint()", "getter", 
/*  538 */           Integer.valueOf(this.quantityHint));
/*      */     }
/*  540 */     return this.quantityHint;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getSelectorString() {
/*  545 */     String traceRet1 = this.messageSelector.getSelector();
/*  546 */     if (Trace.isOn) {
/*  547 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "getSelectorString()", "getter", traceRet1);
/*      */     }
/*      */     
/*  550 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isPubSub() {
/*  558 */     if (Trace.isOn) {
/*  559 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "isPubSub()", "getter", 
/*  560 */           Boolean.valueOf(this.pubSub));
/*      */     }
/*  562 */     return this.pubSub;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void MQConnectionBrowserInit(MQConnection conn, WMQDestination dest, String newSelectorString, ProviderMessageReferenceHandler mrh, int quantityHint, String name) throws JMSException {
/*  587 */     if (Trace.isOn) {
/*  588 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", new Object[] { conn, dest, newSelectorString, mrh, 
/*      */             
/*  590 */             Integer.valueOf(quantityHint), name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  596 */     String qatVersionProperty = "com.ibm.mq.jms.tuning.QATVersion";
/*  597 */     PropertyStore.register(qatVersionProperty, "");
/*  598 */     String qatVersion = PropertyStore.getStringProperty(qatVersionProperty);
/*      */     
/*  600 */     if (qatVersion != "" && !qatVersion.equals("1") && !qatVersion.equals("2")) {
/*      */ 
/*      */       
/*  603 */       if (Trace.isOn) {
/*  604 */         Trace.traceData(this, "Invalid value set for QATVersion", null);
/*      */       }
/*  606 */       JMSException je = ConfigEnvironment.newException("MQJMS1006", "QATVersion", qatVersion);
/*  607 */       if (Trace.isOn) {
/*  608 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/*  612 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  617 */       if (conn == null || dest == null || mrh == null) {
/*  618 */         if (Trace.isOn) {
/*  619 */           Trace.traceData(this, "null parameter passed to ConnectionBrowser constructor", null);
/*      */         }
/*  621 */         JMSException je = ConfigEnvironment.newException("MQJMS1093", this);
/*  622 */         if (Trace.isOn) {
/*  623 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/*  627 */         throw je;
/*      */       } 
/*      */       try {
/*  630 */         if (newSelectorString != null) {
/*  631 */           this.messageSelector.setSelector(newSelectorString);
/*      */         }
/*      */       }
/*  634 */       catch (SyntaxException se) {
/*  635 */         if (Trace.isOn) {
/*  636 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)se, 1);
/*      */         }
/*      */ 
/*      */         
/*  640 */         String key = "MQJMS0004";
/*  641 */         String msg = ConfigEnvironment.getErrorMessage(key);
/*  642 */         InvalidSelectorException je = new InvalidSelectorException(msg, key);
/*  643 */         je.setLinkedException((Exception)se);
/*  644 */         je.initCause((Throwable)se);
/*      */         
/*  646 */         if (Trace.isOn) {
/*  647 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/*  651 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  657 */         if (Trace.isOn) {
/*  658 */           Trace.traceData(this, "Sanity test 1. (quantityHint)", null);
/*      */         }
/*      */ 
/*      */         
/*  662 */         if (quantityHint < 0 || quantityHint > 2) {
/*      */           
/*  664 */           JMSException traceRet3 = ConfigEnvironment.newException("MQJMS1094");
/*  665 */           if (Trace.isOn) {
/*  666 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)traceRet3, 4);
/*      */           }
/*      */ 
/*      */           
/*  670 */           throw traceRet3;
/*      */         } 
/*  672 */         if (Trace.isOn) {
/*  673 */           Trace.traceData(this, "sanity test 1. passed", null);
/*      */         
/*      */         }
/*      */       }
/*  677 */       catch (JMSException je) {
/*  678 */         if (Trace.isOn) {
/*  679 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  685 */         if (Trace.isOn) {
/*  686 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 5);
/*      */         }
/*      */ 
/*      */         
/*  690 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  696 */         this.connection = conn;
/*  697 */         this.dest = dest;
/*  698 */         this.mrh = mrh;
/*  699 */         this.quantityHint = quantityHint;
/*      */         
/*  701 */         if (dest.isTopic()) {
/*      */           
/*  703 */           this.mqQueueName = pubSubSetup(dest, this.messageSelector.getSelector(), name);
/*      */           
/*  705 */           if (Trace.isOn) {
/*  706 */             Trace.traceData(this, "Topic-based browser using queue '" + this.mqQueueName + "'", null);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  711 */           this.mqQueueName = dest.getName();
/*  712 */           this.correlIdAsIdentity = false;
/*      */           
/*  714 */           if (Trace.isOn) {
/*  715 */             Trace.traceData(this, "Queue-based consumer using queue '" + this.mqQueueName + "'", null);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  720 */         this.agent = MQQueueAgent.getQueueAgent(this.connection, dest, this.mqQueueName, this.correlIdAsIdentity);
/*      */       
/*      */       }
/*  723 */       catch (JMSException je) {
/*  724 */         if (Trace.isOn) {
/*  725 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/*  729 */         if (Trace.isOn) {
/*  730 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 6);
/*      */         }
/*      */ 
/*      */         
/*  734 */         throw je;
/*      */       }
/*      */     
/*  737 */     } catch (JMSException je) {
/*  738 */       if (Trace.isOn) {
/*  739 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/*  743 */       if (Trace.isOn) {
/*  744 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)", (Throwable)je, 7);
/*      */       }
/*      */ 
/*      */       
/*  748 */       throw je;
/*      */     } 
/*      */     
/*  751 */     if (Trace.isOn) {
/*  752 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "MQConnectionBrowserInit(MQConnection,WMQDestination,String,ProviderMessageReferenceHandler,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String pubSubSetup(WMQDestination topic, String selector, String name) throws JMSException {
/*  775 */     if (Trace.isOn) {
/*  776 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "pubSubSetup(WMQDestination,String,String)", new Object[] { topic, selector, name });
/*      */     }
/*      */     
/*  779 */     MQSession mqs = null;
/*      */     
/*      */     try {
/*  782 */       this.pubSub = true;
/*  783 */       boolean sharedQ = true;
/*      */ 
/*      */       
/*  786 */       MQConnection conn = this.connection;
/*      */ 
/*      */ 
/*      */       
/*  790 */       MQSubscriptionEngine mqse = conn.getSubscriptionEngine();
/*      */ 
/*      */       
/*  793 */       String subQName = null;
/*  794 */       if (name == null) {
/*      */         
/*  796 */         subQName = MQSubscriptionEngine.validateQueueName(conn.getBrkCCSubQueue(), 0);
/*  797 */         if (Trace.isOn) {
/*  798 */           Trace.traceData(this, "Non-durable Browser on '" + subQName + "'", null);
/*      */         }
/*      */       } else {
/*      */         
/*  802 */         subQName = MQSubscriptionEngine.validateQueueName(topic.getStringProperty("brokerCCDurSubQueue"), 1);
/*  803 */         if (Trace.isOn) {
/*  804 */           Trace.traceData(this, "Durable Browser on '" + subQName + "'", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  809 */       if (subQName.endsWith("*")) {
/*  810 */         sharedQ = false;
/*  811 */         this.correlIdAsIdentity = false;
/*      */       } else {
/*  813 */         sharedQ = true;
/*  814 */         this.correlIdAsIdentity = true;
/*      */       } 
/*      */       
/*  817 */       if (name == null) {
/*      */         
/*  819 */         this.isDurable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  824 */         mqs = this.connection.getCBBrokerSession();
/*  825 */         this.mqsub = mqse.openSubscription(mqs, topic, selector, false, sharedQ, subQName);
/*      */       }
/*      */       else {
/*      */         
/*  829 */         this.isDurable = true;
/*      */ 
/*      */ 
/*      */         
/*  833 */         mqs = this.connection.getCBBrokerSession();
/*  834 */         this.mqsub = mqse.openDurableSubscription(mqs, topic, selector, false, sharedQ, subQName, name);
/*      */       } 
/*      */ 
/*      */       
/*  838 */       if (Trace.isOn) {
/*  839 */         Trace.traceData(this, "Topic-based browser using queue '" + this.mqsub.getQueueName() + "'", null);
/*  840 */         Trace.traceData(this, "This queue is" + (!sharedQ ? " NOT" : "") + " SHARED", null);
/*  841 */         Trace.traceData(this, "CorrelId '" + Utils.bytesToHex(this.mqsub.getCorrelationId()) + "'", null);
/*  842 */         Trace.traceData(this, "The subscription is " + (!this.isDurable ? "NON-" : "") + "DURABLE", null);
/*      */       } 
/*      */       
/*  845 */       String traceRet1 = this.mqsub.getQueueName();
/*  846 */       if (Trace.isOn) {
/*  847 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "pubSubSetup(WMQDestination,String,String)", traceRet1);
/*      */       }
/*      */       
/*  850 */       return traceRet1;
/*      */     
/*      */     }
/*  853 */     catch (JMSException je) {
/*  854 */       if (Trace.isOn) {
/*  855 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "pubSubSetup(WMQDestination,String,String)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  862 */         if (je.getErrorCode() == "MQJMS3023")
/*      */         {
/*  864 */           if (Trace.isOn) {
/*  865 */             Trace.traceData(this, "Already subscribed. Closing session as this will not be reused.", null);
/*      */           }
/*  867 */           mqs.close(false);
/*      */         }
/*      */       
/*  870 */       } catch (Exception e) {
/*  871 */         if (Trace.isOn) {
/*  872 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "pubSubSetup(WMQDestination,String,String)", e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  879 */         if (Trace.isOn) {
/*  880 */           Trace.traceData(this, "Exception closing session, rethrowing to ensure reconnection thread ends" + e.toString(), null);
/*      */         }
/*      */       } 
/*      */       
/*  884 */       if (Trace.isOn) {
/*  885 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "pubSubSetup(WMQDestination,String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  888 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void start() throws JMSException {
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "start()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     if (Trace.isOn) {
/*  906 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "start()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startInternal() throws JMSException {
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "startInternal()");
/*      */     }
/*      */ 
/*      */     
/*  923 */     this.jmsException = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  932 */     if (this.connection.getMessageRetention() == 1) {
/*  933 */       this.agent.addBrowser(this, true);
/*      */     } else {
/*  935 */       this.agent.addBrowser(this, false);
/*      */     } 
/*      */     
/*  938 */     if (Trace.isOn) {
/*  939 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "startInternal()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stop() throws JMSException {
/*  953 */     if (Trace.isOn) {
/*  954 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "stop()");
/*      */     }
/*      */     
/*      */     try {
/*  958 */       this.agent.removeBrowser(this);
/*  959 */       checkException();
/*  960 */       endDeliver();
/*      */     }
/*  962 */     catch (JMSException je) {
/*  963 */       if (Trace.isOn) {
/*  964 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "stop()", (Throwable)je);
/*      */       }
/*      */       
/*  967 */       if (Trace.isOn) {
/*  968 */         Trace.traceData(this, "Leaving via Exception", null);
/*      */       }
/*  970 */       if (Trace.isOn) {
/*  971 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "stop()", (Throwable)je);
/*      */       }
/*      */       
/*  974 */       throw je;
/*      */     } 
/*      */     
/*  977 */     if (Trace.isOn) {
/*  978 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "stop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  989 */     if (Trace.isOn) {
/*  990 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "toString()");
/*      */     }
/*      */     
/*  993 */     StringBuffer buff = new StringBuffer();
/*      */     try {
/*  995 */       if (Trace.isOn) {
/*  996 */         Trace.traceData(this, "ProviderConnection = " + this.connection, null);
/*  997 */         Trace.traceData(this, "ProviderDestination = " + this.dest, null);
/*  998 */         Trace.traceData(this, "Selector = " + this.messageSelector.getSelector(), null);
/*  999 */         Trace.traceData(this, "Quantity = " + this.quantityHint, null);
/*      */       } 
/*      */       
/* 1002 */       buff.append("ProviderConnection to '");
/* 1003 */       buff.append(((null == this.connection.qmgrName) ? "null" : this.connection.qmgrName) + "'\n");
/* 1004 */       buff.append("ProviderDestination = '");
/* 1005 */       buff.append(this.dest.toURI() + "'\n");
/* 1006 */       buff.append("Selector    = '");
/* 1007 */       buff.append(this.messageSelector.getSelector() + "'\n");
/* 1008 */       buff.append("Quantity    =  " + this.quantityHint);
/*      */       
/* 1010 */       String traceRet1 = buff.toString();
/* 1011 */       if (Trace.isOn) {
/* 1012 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "toString()", traceRet1, 1);
/*      */       }
/*      */       
/* 1015 */       return traceRet1;
/*      */     }
/* 1017 */     catch (Exception e) {
/* 1018 */       if (Trace.isOn) {
/* 1019 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "toString()", e);
/*      */       }
/*      */ 
/*      */       
/* 1023 */       String traceRet2 = buff.toString();
/* 1024 */       if (Trace.isOn) {
/* 1025 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnectionBrowser", "toString()", traceRet2, 2);
/*      */       }
/*      */       
/* 1028 */       return traceRet2;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQConnectionBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */