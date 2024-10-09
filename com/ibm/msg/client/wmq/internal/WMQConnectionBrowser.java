/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.MQCBC;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQHeader;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQRFH;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.mq.jmqi.system.LpiSDSubProps;
/*      */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderConnectionBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReferenceHandler;
/*      */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
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
/*      */ public class WMQConnectionBrowser
/*      */   extends WMQPropertyContext
/*      */   implements ProviderConnectionBrowser
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConnectionBrowser.java";
/*      */   private static final long serialVersionUID = 1489582153L;
/*      */   private WMQConnection connection;
/*      */   private WMQDestination destination;
/*      */   private String fqSubName;
/*      */   private transient ProviderMessageReferenceHandler msgRefHandler;
/*      */   private int quantityHint;
/*      */   private boolean shared;
/*      */   private boolean durable;
/*      */   private static final int HEADERSIZE_DEFAULT = 5000;
/*      */   private transient WMQConnectionBrowserShadow shadow;
/*      */   
/*      */   static {
/*  101 */     if (Trace.isOn) {
/*  102 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConnectionBrowser.java");
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
/*      */   private class WMQConnectionBrowserShadow
/*      */     extends WMQAsyncConsumerShadow
/*      */   {
/*  116 */     private Hobj secondHobj = null;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean poisonMsgHandlerConfiguredWithSecondHobj = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionBrowserShadow(WMQConnection connection, WMQDestination destination, String selector, boolean shared, boolean durable, String subscriptionName) {
/*  126 */       super((JmsPropertyContext)connection, connection, destination, selector, false, shared, durable, subscriptionName);
/*  127 */       if (Trace.isOn) {
/*  128 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "<init>(WMQConnection,WMQDestination,String,boolean,boolean,String)", new Object[] { connection, destination, selector, 
/*      */               
/*  130 */               Boolean.valueOf(shared), Boolean.valueOf(durable), subscriptionName });
/*      */       }
/*      */ 
/*      */       
/*  134 */       if (Trace.isOn) {
/*  135 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "<init>(WMQConnection,WMQDestination,String,boolean,boolean,String)");
/*      */       }
/*      */     }
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
/*      */     void closeInternal(boolean closeHobj) throws JMSException {
/*  149 */       if (Trace.isOn) {
/*  150 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "closeInternal(boolean)", new Object[] {
/*  151 */               Boolean.valueOf(closeHobj)
/*      */             });
/*      */       }
/*      */       
/*  155 */       this.helper.removeAsyncConsumer();
/*      */       
/*  157 */       if (closeHobj) {
/*  158 */         Pint rc = this.env.newPint();
/*  159 */         Pint cc = this.env.newPint();
/*      */         
/*  161 */         if (this.destination.isTopic()) {
/*      */ 
/*      */           
/*  164 */           this.mq.MQCLOSE(this.hconn, this.phsub, 0, cc, rc);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  169 */           if (this.shared && rc.x == 2429) {
/*      */             
/*  171 */             if (Trace.isOn) {
/*  172 */               Trace.traceData(this, "closeInternal(boolean)", "Subscription in use for shared consumer. Leaving subscription in place", rc);
/*      */             }
/*      */           }
/*      */           else {
/*      */             
/*  177 */             WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0025", this.destination
/*  178 */                 .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN004009", this.helper, this.hconn);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  190 */         this.mq.MQCLOSE(this.hconn, this.phobj, 0, cc, rc);
/*      */       } 
/*      */       
/*  193 */       if (Trace.isOn) {
/*  194 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "closeInternal(boolean)");
/*      */       }
/*      */     }
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
/*      */     MQCBD computeCBD() {
/*  209 */       if (Trace.isOn) {
/*  210 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "computeCBD()");
/*      */       }
/*      */       
/*  213 */       MQCBD cbd = super.computeCBD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  221 */       if (0 == WMQConnectionBrowser.this.quantityHint) {
/*  222 */         cbd.setMaxMsgLength(0);
/*      */       }
/*  224 */       else if (1 == WMQConnectionBrowser.this.quantityHint) {
/*  225 */         cbd.setMaxMsgLength(5000);
/*      */       } 
/*      */       
/*  228 */       if (Trace.isOn) {
/*  229 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "computeCBD()", cbd);
/*      */       }
/*      */       
/*  232 */       return cbd;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     WMQGMO computeGMO(int waitTime) throws JMSException {
/*  244 */       if (Trace.isOn)
/*  245 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "computeGMO(int)", new Object[] {
/*  246 */               Integer.valueOf(waitTime)
/*      */             }); 
/*  248 */       WMQGMO gmo = super.computeGMO(waitTime);
/*  249 */       int options = gmo.getOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  256 */       int gmoVersion = gmo.getVersion();
/*  257 */       if (gmoVersion < 3) {
/*  258 */         gmo.setVersion(3);
/*      */       }
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
/*  279 */       gmo.setWaitInterval(0);
/*  280 */       options |= 0x1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  287 */       if (2 != WMQConnectionBrowser.this.quantityHint) {
/*  288 */         options |= 0x40;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  296 */       options |= 0x10;
/*  297 */       options |= 0x1000000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  304 */       options |= 0x200000;
/*      */ 
/*      */       
/*  307 */       options &= 0xFFFFFFFD;
/*  308 */       options &= 0xFFFFEFFF;
/*  309 */       options |= 0x4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  317 */       if (qmIsZOS() && WMQConnectionBrowser.this.quantityHint == 1) {
/*  318 */         options &= 0xFFFFBFFF;
/*      */       }
/*      */       
/*  321 */       gmo.setOptions(options);
/*      */       
/*  323 */       if (Trace.isOn) {
/*  324 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "computeGMO(int)", gmo);
/*      */       }
/*      */       
/*  327 */       return gmo;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int computeQueueOpenOptions() throws JMSException {
/*  337 */       if (Trace.isOn) {
/*  338 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "computeQueueOpenOptions()");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  346 */       int options = super.computeQueueOpenOptions();
/*  347 */       options |= 0x2000A;
/*  348 */       options &= 0xFFFFFFFB;
/*  349 */       options &= 0xFFFFFFFE;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  354 */       if (Trace.isOn) {
/*  355 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "computeQueueOpenOptions()", 
/*  356 */             Integer.valueOf(options));
/*      */       }
/*  358 */       return options;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void registerMessageListener() throws JMSException {
/*  363 */       if (Trace.isOn) {
/*  364 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "registerMessageListener()");
/*      */       }
/*      */       
/*  367 */       registerMessageListener(true);
/*  368 */       if (Trace.isOn) {
/*  369 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "registerMessageListener()");
/*      */       }
/*      */     }
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
/*      */     private void initialiseSecondHobj() throws JMSException {
/*  384 */       if (Trace.isOn) {
/*  385 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "initialiseSecondHobj()");
/*      */       }
/*      */       
/*  388 */       if (this.secondHobj != null) {
/*  389 */         if (Trace.isOn) {
/*  390 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "initialiseSecondHobj()", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  396 */       Pint rc = this.env.newPint();
/*  397 */       Pint cc = this.env.newPint();
/*      */       
/*  399 */       String qname = null;
/*  400 */       if (this.destination.isTopic()) {
/*  401 */         LpiSDSubProps subprops = this.spisd.getSubProps();
/*  402 */         qname = subprops.getDestinationQName();
/*      */       } else {
/*      */         
/*  405 */         qname = this.destination.getName();
/*      */       } 
/*      */       
/*  408 */       Phobj secondPhobj = this.env.newPhobj();
/*  409 */       MQOD mqod = this.env.newMQOD();
/*  410 */       mqod.setObjectName(qname);
/*      */       
/*  412 */       int queueOpenOptions = computeQueueOpenOptions();
/*  413 */       queueOpenOptions |= 0x8;
/*  414 */       queueOpenOptions &= 0xFFFDFFFF;
/*  415 */       queueOpenOptions &= 0xFFEFFFFF;
/*  416 */       queueOpenOptions |= 0x80000;
/*      */       
/*  418 */       SpiOpenOptions spiOO = computeSpiOpenOptions(queueOpenOptions);
/*  419 */       ((JmqiSP)this.mq).spiOpen(this.hconn, mqod, spiOO, secondPhobj, cc, rc);
/*      */ 
/*      */ 
/*      */       
/*  423 */       String messageId = "JMSWMQ2008";
/*  424 */       if (rc.x == 2045) {
/*  425 */         messageId = "JMSWMQ1017";
/*      */       }
/*      */ 
/*      */       
/*  429 */       WMQMessageConsumer.checkJmqiCallSuccess(messageId, this.destination
/*  430 */           .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00D00B", this.helper, this.hconn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  439 */       this.secondHobj = secondPhobj.getHobj();
/*      */       
/*  441 */       if (Trace.isOn) {
/*  442 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "initialiseSecondHobj()", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void configurePoisonMessageHandler() throws JMSException {
/*  449 */       if (this.poisonMsgHandlerConfiguredWithSecondHobj) {
/*      */         return;
/*      */       }
/*      */       
/*  453 */       initialisePoison();
/*  454 */       initialiseSecondHobj();
/*  455 */       this.poison.setHobj(this.secondHobj);
/*  456 */       this.poisonMsgHandlerConfiguredWithSecondHobj = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void close() throws JMSException {
/*  464 */       if (Trace.isOn) {
/*  465 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "close()");
/*      */       }
/*      */       
/*  468 */       Pint rc = this.env.newPint();
/*  469 */       Pint cc = this.env.newPint();
/*      */       
/*  471 */       if (this.secondHobj != null) {
/*  472 */         Phobj secondPhobj = this.env.newPhobj();
/*  473 */         secondPhobj.setHobj(this.secondHobj);
/*  474 */         this.mq.MQCLOSE(this.hconn, secondPhobj, 0, cc, rc);
/*  475 */         this.secondHobj = null;
/*  476 */         this.poisonMsgHandlerConfiguredWithSecondHobj = false;
/*      */       } 
/*  478 */       super.close();
/*  479 */       if (Trace.isOn) {
/*  480 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "close()");
/*      */       }
/*      */     }
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
/*      */     private boolean poisonous(MQMD md, MQGMO gmo, MQCBC cbc) throws JMSException {
/*  494 */       if (Trace.isOn) {
/*  495 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "poisonous(MQMD,MQGMO,MQCBC)", new Object[] { md, gmo, cbc });
/*      */       }
/*      */ 
/*      */       
/*  499 */       if (md.getBackoutCount() == 0) {
/*  500 */         if (Trace.isOn) {
/*  501 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "poisonous(MQMD,MQGMO,MQCBC)", 
/*  502 */               Boolean.valueOf(false), 1);
/*      */         }
/*  504 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  508 */       initialisePoison();
/*  509 */       if (this.poison.shouldMessageBeRequeued(md.getBackoutCount())) {
/*  510 */         handlePoisonMessage(md, gmo, cbc);
/*  511 */         if (Trace.isOn) {
/*  512 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "poisonous(MQMD,MQGMO,MQCBC)", 
/*  513 */               Boolean.valueOf(true), 2);
/*      */         }
/*  515 */         return true;
/*      */       } 
/*  517 */       if (Trace.isOn) {
/*  518 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "poisonous(MQMD,MQGMO,MQCBC)", 
/*  519 */             Boolean.valueOf(false), 3);
/*      */       }
/*  521 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void handlePoisonMessage(MQMD md, MQGMO gmo, MQCBC cbc) throws JMSException {
/*  533 */       if (Trace.isOn) {
/*  534 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", new Object[] { md, gmo, cbc });
/*      */       }
/*      */ 
/*      */       
/*  538 */       Pint rc = this.env.newPint();
/*  539 */       Pint cc = this.env.newPint();
/*      */       
/*  541 */       configurePoisonMessageHandler();
/*      */       
/*  543 */       MQGMO newgmo = this.env.newMQGMO();
/*  544 */       if (newgmo.getVersion() < 3) {
/*  545 */         newgmo.setVersion(3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  553 */       int options = 4096;
/*  554 */       newgmo.setOptions(options);
/*  555 */       newgmo.setMatchOptions(32);
/*  556 */       newgmo.setMsgToken(gmo.getMsgToken());
/*      */       
/*  558 */       MQMD newmqmd = this.env.newMQMD();
/*  559 */       newmqmd.setVersion(2);
/*  560 */       ByteBuffer buffer = ByteBuffer.allocate(cbc.getDataLength());
/*  561 */       Pint dataLen = this.env.newPint();
/*  562 */       int msgLen = cbc.getDataLength();
/*      */       
/*  564 */       this.mq.MQGET(this.hconn, this.secondHobj, newmqmd, newgmo, msgLen, buffer, dataLen, cc, rc);
/*      */       
/*  566 */       if (rc.x == 2080) {
/*      */         
/*  568 */         if (Trace.isOn) {
/*  569 */           Trace.data(this, "WMQConnectionBrowserShadow", "handlePoisonMessage", "Unable to get poison message as buffer size was too small. Buffer size used : ", 
/*      */ 
/*      */ 
/*      */               
/*  573 */               Integer.valueOf(cbc.getDataLength()));
/*  574 */           Trace.data(this, "WMQConnectionBrowserShadow", "handlePoisonMessage", "Trying again with an increased buffer size : ", 
/*      */ 
/*      */ 
/*      */               
/*  578 */               Integer.valueOf(dataLen.x));
/*      */         } 
/*      */         
/*  581 */         buffer = ByteBuffer.allocate(dataLen.x);
/*  582 */         msgLen = dataLen.x;
/*  583 */         this.mq.MQGET(this.hconn, this.secondHobj, newmqmd, newgmo, msgLen, buffer, dataLen, cc, rc);
/*      */       } 
/*      */       
/*  586 */       if (rc.x == 2033) {
/*      */         
/*  588 */         if (Trace.isOn) {
/*  589 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*      */       try {
/*  596 */         WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination
/*  597 */             .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00D00A", this.helper, this.hconn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  606 */       catch (JMSException je) {
/*  607 */         if (Trace.isOn) {
/*  608 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  612 */         if (cc.x == 1) {
/*  613 */           this.mq.MQBACK(this.hconn, cc, rc);
/*      */         }
/*  615 */         if (Trace.isOn) {
/*  616 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  619 */         throw je;
/*      */       } 
/*      */       
/*      */       try {
/*  623 */         this.poison.handlePoisonMessage(newmqmd, new ByteBuffer[] { (ByteBuffer)buffer.limit(dataLen.x) });
/*      */       }
/*  625 */       catch (JMSException je) {
/*  626 */         if (Trace.isOn) {
/*  627 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  632 */         if (newmqmd.getPersistence() == 1) {
/*      */           
/*  634 */           this.helper.removeAsyncConsumer();
/*      */ 
/*      */ 
/*      */           
/*  638 */           if (Trace.isOn) {
/*  639 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", (Throwable)je, 2);
/*      */           }
/*      */           
/*  642 */           throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  648 */         HashMap<String, JMSException> inserts = new HashMap<>();
/*  649 */         inserts.put("XMSC_INSERT_EXCEPTION", je);
/*  650 */         Log.log(this, "consumer()", "JMSWMQ0036", inserts);
/*      */       } 
/*  652 */       if (Trace.isOn) {
/*  653 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "handlePoisonMessage(MQMD,MQGMO,MQCBC)", 2);
/*      */       }
/*      */     }
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
/*      */     public void consumer(Hconn hconn2, MQMD mqmd2, MQGMO mqgmo2, ByteBuffer pBuffer, MQCBC mqcbc) {
/*  672 */       if (Trace.isOn) {
/*  673 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", new Object[] { hconn2, mqmd2, mqgmo2, pBuffer, mqcbc });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  679 */       int callType = mqcbc.getCallType();
/*  680 */       int reason = mqcbc.getReason();
/*  681 */       int compcode = mqcbc.getCompCode();
/*  682 */       if (0 != reason && 2033 != reason && 2079 != reason) {
/*  683 */         if (Trace.isOn) {
/*  684 */           Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "Unexpected reason", Integer.valueOf(reason));
/*      */         }
/*      */         
/*  687 */         if (Reason.isImpossibleReason(reason, compcode, null) || 2120 == reason || (WMQConnectionBrowser.this
/*  688 */           .quantityHint == 2 && 2079 == reason)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  696 */           HashMap<String, Object> data = new HashMap<>();
/*  697 */           data.put("callType", Integer.valueOf(callType));
/*  698 */           data.put("cc", Integer.valueOf(compcode));
/*  699 */           data.put("rc", Integer.valueOf(reason));
/*  700 */           data.put("hconn2", hconn2);
/*  701 */           data.put("md", mqmd2);
/*  702 */           data.put("gmo", mqgmo2);
/*  703 */           data.put("cbc", mqcbc);
/*  704 */           data.put("connection", WMQConnectionBrowser.this.connection);
/*  705 */           data.put("destination", this.destination);
/*  706 */           if (WMQConnectionBrowser.this.fqSubName != null) {
/*  707 */             data.put("subname", WMQConnectionBrowser.this.fqSubName);
/*      */           }
/*  709 */           Trace.ffst(this, "consumer", "XN00D004", data, null);
/*      */         } 
/*  711 */         if (Reason.isConnectionBroken(reason)) {
/*  712 */           JMSException e = Reason.createException("JMSWMQ1107", null, reason, compcode, this.env);
/*      */           try {
/*  714 */             WMQConnectionBrowser.this.connection.driveExceptionListener(e, true);
/*      */           }
/*  716 */           catch (JMSException ex) {
/*  717 */             if (Trace.isOn) {
/*  718 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)ex, 1);
/*      */             }
/*      */             
/*  721 */             HashMap<String, Object> info = new HashMap<>();
/*  722 */             info.put("exception", e);
/*  723 */             Trace.ffst(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "XN00D005", info, null);
/*      */           } 
/*      */         } 
/*      */       }  try {
/*      */         ByteBuffer pBuffer1; byte[] token; int msgLen; WMQMessageReference msgRef; Pint consumerRC;
/*      */         Pint consumerCC;
/*      */         JMSException e;
/*      */         HashMap<String, Object> data;
/*  731 */         if (2033 == reason) {
/*  732 */           if (Trace.isOn) {
/*  733 */             Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "Light traffic so send this batch off straight away", 
/*      */ 
/*      */ 
/*      */                 
/*  737 */                 Integer.valueOf(reason));
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
/*      */ 
/*      */ 
/*      */           
/*  753 */           WMQConnectionBrowser.this.msgRefHandler.endDeliver();
/*  754 */           if (Trace.isOn) {
/*  755 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 1);
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  761 */         switch (callType) {
/*      */           
/*      */           case 1:
/*  764 */             if (Trace.isOn) {
/*  765 */               Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "called to START", Integer.valueOf(reason));
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2:
/*  771 */             if (Trace.isOn) {
/*  772 */               Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "called to STOP", Integer.valueOf(reason));
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/*  778 */             if (Trace.isOn) {
/*  779 */               Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "called to REGISTER", Integer.valueOf(reason));
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 4:
/*  785 */             if (Trace.isOn) {
/*  786 */               Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "called to DEREGISTER", Integer.valueOf(reason));
/*      */             }
/*      */             break;
/*      */           
/*      */           case 7:
/*  791 */             if (Trace.isOn) {
/*  792 */               Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "called to BROWSE msgid=" + 
/*      */ 
/*      */                   
/*  795 */                   JmqiTools.arrayToHexString(mqmd2.getMsgId()) + " messagetoken=" + JmqiTools.arrayToHexString(mqgmo2.getMsgToken()), 
/*  796 */                   Integer.valueOf(reason));
/*      */             }
/*  798 */             pBuffer1 = pBuffer;
/*  799 */             if (0 != reason) {
/*  800 */               if (2 != WMQConnectionBrowser.this.quantityHint && 2079 == reason) {
/*  801 */                 if (Trace.isOn) {
/*  802 */                   Trace.data(this, "consumer()", "Got MQRC_TRUNCATED_MSG_ACCEPTED as expected", Integer.valueOf(reason));
/*      */                 }
/*      */                 
/*  805 */                 if (1 == WMQConnectionBrowser.this.quantityHint) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  811 */                   pBuffer1 = browseHeaderData(hconn2, mqmd2, mqgmo2, pBuffer1, mqcbc);
/*  812 */                   if (pBuffer1 == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*  817 */                     WMQConnectionBrowser.this.msgRefHandler.endDeliver();
/*  818 */                     if (Trace.isOn) {
/*  819 */                       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 6);
/*      */                     }
/*      */                     
/*  822 */                     if (Trace.isOn) {
/*  823 */                       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 2);
/*      */                     }
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*      */                     return;
/*      */                   } 
/*      */                 } 
/*  832 */               } else if (compcode == 1 && reason == 2110 && "        ".equals(mqmd2.getFormat())) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  839 */                 if (Trace.isOn) {
/*  840 */                   Trace.data(this, "consumer()", "Got MQRC_FORMAT_ERROR as warning but ignoring as Format field is set to MQFMT_NONE", 
/*      */ 
/*      */                       
/*  843 */                       Integer.valueOf(reason));
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */               else {
/*      */ 
/*      */                 
/*  851 */                 handlePoisonMessage(mqmd2, mqgmo2, mqcbc);
/*  852 */                 if (Trace.isOn) {
/*  853 */                   Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 3);
/*      */                 }
/*      */ 
/*      */                 
/*      */                 return;
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*  862 */             if (poisonous(mqmd2, mqgmo2, mqcbc)) {
/*  863 */               if (Trace.isOn) {
/*  864 */                 Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 4);
/*      */               }
/*      */               
/*      */               return;
/*      */             } 
/*      */             
/*  870 */             token = mqgmo2.getMsgToken();
/*  871 */             msgLen = mqcbc.getDataLength();
/*      */             
/*  873 */             msgRef = null;
/*  874 */             if (this.spisd == null) {
/*  875 */               msgRef = new WMQMessageReference(token, this.destination, msgLen, null);
/*      */             } else {
/*      */               
/*  878 */               LpiSDSubProps subprops = this.spisd.getSubProps();
/*      */               
/*  880 */               msgRef = new WMQMessageReference(token, this.destination, subprops.getDestinationQName(), subprops.getDestinationQMgr(), WMQConnectionBrowser.this.fqSubName, msgLen, this.spisd.getSubId());
/*      */             } 
/*      */             
/*  883 */             if (0 != WMQConnectionBrowser.this.quantityHint) {
/*      */               
/*      */               try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  891 */                 WMQMessage message = null;
/*  892 */                 if (1 == WMQConnectionBrowser.this.quantityHint) {
/*      */                   
/*  894 */                   message = unmarshal(mqmd2, pBuffer1, true);
/*      */                 } else {
/*      */                   
/*  897 */                   message = unmarshal(mqmd2, pBuffer1, false);
/*      */                 } 
/*      */                 
/*  900 */                 msgRef.setDataQuantity(WMQConnectionBrowser.this.quantityHint);
/*  901 */                 msgRef.setMessage((ProviderMessage)message);
/*      */               
/*      */               }
/*  904 */               catch (JMSException je) {
/*  905 */                 if (Trace.isOn) {
/*  906 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)je, 2);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  912 */                 if (Trace.isOn) {
/*  913 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)je, 7);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  922 */                 handlePoisonMessage(mqmd2, mqgmo2, mqcbc);
/*      */ 
/*      */                 
/*  925 */                 if (Trace.isOn) {
/*  926 */                   Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 5);
/*      */                 }
/*      */ 
/*      */                 
/*      */                 return;
/*      */               } 
/*      */             }
/*      */             
/*      */             try {
/*  935 */               WMQConnectionBrowser.this.msgRefHandler.handleMessageReference(msgRef);
/*      */             }
/*  937 */             catch (JMSException je) {
/*  938 */               if (Trace.isOn) {
/*  939 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)je, 3);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  946 */               this.helper.stop();
/*  947 */               if (Trace.isOn) {
/*  948 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)je);
/*      */               }
/*      */               
/*  951 */               throw je;
/*      */             } 
/*      */             break;
/*      */           
/*      */           case 5:
/*  956 */             if (Trace.isOn) {
/*  957 */               Trace.data(this, "WMQConnectionBrowserShadow", "consumer", "called for an EVENT", Integer.valueOf(reason));
/*      */             }
/*  959 */             consumerRC = this.env.newPint(mqcbc.getReason());
/*  960 */             consumerCC = this.env.newPint(mqcbc.getCompCode());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  967 */             if (consumerCC.x == 2 && consumerRC.x == 2518) {
/*  968 */               synchronized (this.delayedCloseLock) {
/*      */                 
/*  970 */                 this.delayedCloseFinished = true;
/*      */ 
/*      */                 
/*  973 */                 this.delayedCloseLock.notify();
/*      */               } 
/*      */               
/*      */               break;
/*      */             } 
/*  978 */             if (reason == 2494) {
/*  979 */               HashMap<String, Object> inserts = new HashMap<>();
/*  980 */               inserts.put("XMSC_INSERT_DESTINATION_NAME", this.destination.getName());
/*  981 */               Log.log(this, "consumer()", "JMSWMQ1118", inserts);
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/*  987 */             e = Reason.createException("JMSWMQ1107", null, reason, compcode, this.env);
/*      */             try {
/*  989 */               WMQConnectionBrowser.this.connection.driveExceptionListener(e, Reason.isConnectionBroken(reason));
/*      */             }
/*  991 */             catch (JMSException ex) {
/*  992 */               if (Trace.isOn) {
/*  993 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)ex, 4);
/*      */               }
/*      */ 
/*      */               
/*  997 */               HashMap<String, Object> info = new HashMap<>();
/*  998 */               info.put("exception", e);
/*  999 */               Trace.ffst(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "XN00D006", info, JMSException.class);
/*      */             } 
/* 1001 */             if (reason == 2016) {
/* 1002 */               HashMap<String, Object> inserts = new HashMap<>();
/* 1003 */               inserts.put("XMSC_INSERT_DESTINATION_NAME", this.destination.getName());
/* 1004 */               Log.log(this, "consumer()", "JMSWMQ1119", inserts);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1011 */             data = new HashMap<>();
/* 1012 */             data.put("callType", Integer.valueOf(callType));
/* 1013 */             data.put("cc", Integer.valueOf(compcode));
/* 1014 */             data.put("rc", Integer.valueOf(reason));
/* 1015 */             data.put("hconn2", hconn2);
/* 1016 */             data.put("md", mqmd2);
/* 1017 */             data.put("gmo", mqgmo2);
/* 1018 */             data.put("cbc", mqcbc);
/* 1019 */             data.put("connection", WMQConnectionBrowser.this.connection);
/* 1020 */             data.put("destination", this.destination);
/* 1021 */             if (WMQConnectionBrowser.this.fqSubName != null) {
/* 1022 */               data.put("subname", WMQConnectionBrowser.this.fqSubName);
/*      */             }
/* 1024 */             Trace.ffst(this, "consumer", "XN00D003", data, JMSException.class);
/*      */             break;
/*      */         } 
/* 1027 */       } catch (JMSException je) {
/* 1028 */         if (Trace.isOn) {
/* 1029 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)je, 5);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1038 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1039 */         inserts.put("XMSC_INSERT_EXCEPTION", je);
/* 1040 */         Log.log(this, "consumer()", "JMSWMQ0036", inserts);
/*      */         
/*      */         try {
/* 1043 */           boolean b = isConnectionBroken((Exception)je);
/* 1044 */           WMQConnectionBrowser.this.connection.driveExceptionListener(je, b);
/*      */         }
/* 1046 */         catch (JMSException ex) {
/* 1047 */           if (Trace.isOn) {
/* 1048 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)ex, 6);
/*      */           }
/*      */           
/* 1051 */           HashMap<String, Object> info = new HashMap<>();
/* 1052 */           info.put("exception1", je);
/* 1053 */           info.put("exception2", ex);
/* 1054 */           Trace.ffst(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "XN00D007", info, null);
/*      */         }
/*      */       
/* 1057 */       } catch (Throwable t) {
/* 1058 */         if (Trace.isOn) {
/* 1059 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", t, 7);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1064 */         HashMap<String, Object> data = new HashMap<>();
/* 1065 */         data.put("callType", Integer.valueOf(callType));
/* 1066 */         data.put("cc", Integer.valueOf(compcode));
/* 1067 */         data.put("rc", Integer.valueOf(reason));
/* 1068 */         data.put("hconn2", hconn2);
/* 1069 */         data.put("md", mqmd2);
/* 1070 */         data.put("gmo", mqgmo2);
/* 1071 */         data.put("cbc", mqcbc);
/* 1072 */         data.put("connection", WMQConnectionBrowser.this.connection);
/* 1073 */         data.put("destination", this.destination);
/* 1074 */         if (WMQConnectionBrowser.this.fqSubName != null) {
/* 1075 */           data.put("subname", WMQConnectionBrowser.this.fqSubName);
/*      */         }
/*      */         
/* 1078 */         if (pBuffer != null) {
/* 1079 */           data.put("pBuffer", pBuffer);
/*      */         } else {
/*      */           
/* 1082 */           data.put("pBuffer", "null");
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1087 */         data.put("exception", t);
/* 1088 */         Trace.ffst(this, "consumer", "XN00D008", data, null);
/*      */       } 
/*      */       
/* 1091 */       if (Trace.isOn) {
/* 1092 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 6);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void dumpQueueManager() {
/* 1105 */       if (Trace.isOn) {
/* 1106 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "dumpQueueManager()");
/*      */       }
/*      */       
/* 1109 */       Phobj dobj = this.env.newPhobj();
/* 1110 */       MQOD opd = this.env.newMQOD();
/* 1111 */       Pint cc = this.env.newPint();
/* 1112 */       Pint rc = this.env.newPint();
/* 1113 */       opd.setObjectName("SERVICE1.DUMP");
/* 1114 */       opd.setObjectType(999);
/* 1115 */       this.mq.MQOPEN(this.hconn, opd, 16, dobj, cc, rc);
/* 1116 */       if (Trace.isOn) {
/* 1117 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "dumpQueueManager()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMessage unmarshal(MQMD mqmd2, ByteBuffer pBuffer, boolean unmarshalHeadersOnly) throws JMSException {
/* 1124 */       if (Trace.isOn) {
/* 1125 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "unmarshal(MQMD,ByteBuffer,boolean)", new Object[] { mqmd2, pBuffer, 
/*      */               
/* 1127 */               Boolean.valueOf(unmarshalHeadersOnly) });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1133 */       if (null == this.receiveMarshal) {
/* 1134 */         this.receiveMarshal = WMQMarshal.newReceiveMarshal();
/*      */       }
/*      */       
/* 1137 */       pBuffer.rewind();
/* 1138 */       this.receiveMarshal.importMQMDMesageBuffer(WMQConnectionBrowser.this.connection, this.destination, mqmd2, pBuffer, 0, pBuffer.limit(), null);
/* 1139 */       WMQMessage message = this.receiveMarshal.exportProviderMessage(unmarshalHeadersOnly);
/* 1140 */       if (Trace.isOn) {
/* 1141 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "unmarshal(MQMD,ByteBuffer,boolean)", message);
/*      */       }
/*      */       
/* 1144 */       return message;
/*      */     }
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
/*      */     private ByteBuffer browseHeaderData(Hconn hconn2, MQMD mqmd2, MQGMO mqgmo2, ByteBuffer pBuffer, MQCBC mqcbc) throws JmqiException, JMSException {
/* 1165 */       if (Trace.isOn) {
/* 1166 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "browseHeaderData(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", new Object[] { hconn2, mqmd2, mqgmo2, pBuffer, mqcbc });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1171 */       pBuffer.rewind();
/* 1172 */       ByteBuffer buff = pBuffer;
/*      */       
/* 1174 */       int hdrLen = getHeaderLength(hconn2, mqmd2, pBuffer);
/* 1175 */       if (hdrLen < 0) {
/*      */         
/* 1177 */         buff.limit(0);
/* 1178 */         if (Trace.isOn) {
/* 1179 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "browseHeaderData(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", buff, 1);
/*      */         }
/*      */         
/* 1182 */         return buff;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1187 */       int msgLen = mqgmo2.getReturnedLength();
/* 1188 */       if (msgLen == -1) {
/* 1189 */         if (mqcbc.getDataLength() < pBuffer.limit()) {
/* 1190 */           msgLen = mqcbc.getDataLength();
/*      */         } else {
/*      */           
/* 1193 */           msgLen = pBuffer.limit();
/*      */         } 
/*      */       }
/*      */       
/* 1197 */       if (hdrLen > msgLen) {
/*      */         
/* 1199 */         buff = rebrowseMessage(mqgmo2.getMsgToken(), hdrLen);
/*      */       }
/* 1201 */       else if (hdrLen < pBuffer.limit()) {
/*      */         
/* 1203 */         pBuffer.limit(hdrLen);
/*      */       } 
/*      */       
/* 1206 */       if (Trace.isOn) {
/* 1207 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "browseHeaderData(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", buff, 2);
/*      */       }
/*      */       
/* 1210 */       return buff;
/*      */     }
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
/*      */     private ByteBuffer rebrowseMessage(byte[] token, int msgLen) throws JMSException {
/* 1225 */       if (Trace.isOn) {
/* 1226 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "rebrowseMessage(byte [ ],int)", new Object[] { token, 
/* 1227 */               Integer.valueOf(msgLen) });
/*      */       }
/* 1229 */       Pint rc = this.env.newPint();
/* 1230 */       Pint cc = this.env.newPint();
/*      */       
/* 1232 */       initialiseSecondHobj();
/*      */       
/* 1234 */       MQGMO gmo = this.env.newMQGMO();
/* 1235 */       if (gmo.getVersion() < 3) {
/* 1236 */         gmo.setVersion(3);
/*      */       }
/* 1238 */       int options = 84;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1246 */       if (qmIsZOS() && WMQConnectionBrowser.this.quantityHint == 1) {
/* 1247 */         options &= 0xFFFFBFFF;
/*      */       } else {
/*      */         
/* 1250 */         options |= this.gmoConvertOption;
/*      */       } 
/*      */       
/* 1253 */       gmo.setOptions(options);
/* 1254 */       gmo.setMatchOptions(32);
/* 1255 */       gmo.setMsgToken(token);
/*      */       
/* 1257 */       MQMD newmqmd = this.env.newMQMD();
/* 1258 */       ByteBuffer buffer = ByteBuffer.allocate(msgLen);
/* 1259 */       Pint dataLen = this.env.newPint();
/*      */       
/* 1261 */       this.mq.MQGET(this.hconn, this.secondHobj, newmqmd, gmo, msgLen, buffer, dataLen, cc, rc);
/* 1262 */       if (rc.x == 2033) {
/* 1263 */         if (Trace.isOn) {
/* 1264 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "rebrowseMessage(byte [ ],int)", "rebrowseMsg", "Message not available on rebrowse. Setting the returned buffer to null.");
/*      */         }
/*      */ 
/*      */         
/* 1268 */         buffer = null;
/*      */       }
/* 1270 */       else if (rc.x != 2079) {
/* 1271 */         WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination
/* 1272 */             .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00D009", this.helper, this.hconn);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1282 */       if (Trace.isOn) {
/* 1283 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "rebrowseMessage(byte [ ],int)", buffer);
/*      */       }
/*      */       
/* 1286 */       return buffer;
/*      */     }
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
/*      */     private int getHeaderLength(Hconn hconn2, MQMD mqmd2, ByteBuffer pBuffer) throws JmqiException, JMSException {
/*      */       JmqiCodepage cp;
/* 1303 */       if (Trace.isOn) {
/* 1304 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", new Object[] { hconn2, mqmd2, pBuffer });
/*      */       }
/*      */ 
/*      */       
/* 1308 */       if (!"MQHRF2  ".equals(mqmd2.getFormat())) {
/*      */         
/* 1310 */         int traceRet1 = -1;
/* 1311 */         if (Trace.isOn) {
/* 1312 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", 
/* 1313 */               Integer.valueOf(traceRet1), 1);
/*      */         }
/* 1315 */         return traceRet1;
/*      */       } 
/*      */       
/* 1318 */       MQRFH rfh = this.env.newMQRFH(10);
/* 1319 */       MQHeader header = rfh.getMqHeader();
/* 1320 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*      */       
/* 1322 */       WMQThreadLocalStorage tls = WMQConnectionBrowser.this.connection.getThreadLocalStorage();
/* 1323 */       JmqiTls jTls = sysenv.getJmqiTls((JmqiComponentTls)tls);
/*      */       
/* 1325 */       int encoding = mqmd2.getEncoding();
/* 1326 */       int previousCcsid = hconn2.getCcsid();
/* 1327 */       int ccsid = mqmd2.getCodedCharSetId();
/* 1328 */       if (ccsid == -2) {
/* 1329 */         ccsid = previousCcsid;
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 1334 */         cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*      */         
/* 1336 */         if (cp == null) {
/* 1337 */           UnsupportedEncodingException traceRet3 = new UnsupportedEncodingException(Integer.toString(ccsid));
/* 1338 */           if (Trace.isOn) {
/* 1339 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", traceRet3, 1);
/*      */           }
/*      */           
/* 1342 */           throw traceRet3;
/*      */         }
/*      */       
/*      */       }
/* 1346 */       catch (UnsupportedEncodingException e) {
/* 1347 */         if (Trace.isOn) {
/* 1348 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", e);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1353 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1354 */         inserts.put("CCSID", "ccsid:" + Integer.toString(ccsid));
/* 1355 */         JMSException je = (JMSException)NLSServices.createException("JMSWMQ1046", inserts);
/* 1356 */         if (Trace.isOn) {
/* 1357 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1360 */         throw je;
/*      */       } 
/*      */       
/* 1363 */       boolean swap = ((encoding & 0xF) == 2);
/* 1364 */       int ptrSize = 0;
/*      */       
/* 1366 */       header.readFromBuffer(pBuffer.array(), 0, ptrSize, swap, cp, jTls);
/* 1367 */       if (!"RFH ".equals(header.getStrucId())) {
/*      */         
/* 1369 */         int traceRet2 = -1;
/* 1370 */         if (Trace.isOn) {
/* 1371 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", 
/* 1372 */               Integer.valueOf(traceRet2), 2);
/*      */         }
/* 1374 */         return traceRet2;
/*      */       } 
/*      */       
/* 1377 */       int len = header.getStrucLength();
/*      */       
/* 1379 */       if (Trace.isOn) {
/* 1380 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowserShadow", "getHeaderLength(Hconn,MQMD,ByteBuffer)", 
/* 1381 */             Integer.valueOf(len), 3);
/*      */       }
/* 1383 */       return len;
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
/*      */   public WMQConnectionBrowser(WMQConnection connection, ProviderDestination destination, String fqSubName, String selector, boolean shared, boolean durable, ProviderMessageReferenceHandler msgRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 1435 */     super((JmsPropertyContext)connection);
/* 1436 */     if (Trace.isOn) {
/* 1437 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "<init>(WMQConnection,ProviderDestination,String,String,boolean,boolean,ProviderMessageReferenceHandler,int,boolean)", new Object[] { connection, destination, fqSubName, selector, 
/*      */             
/* 1439 */             Boolean.valueOf(shared), 
/* 1440 */             Boolean.valueOf(durable), msgRefHandler, Integer.valueOf(quantityHint), 
/* 1441 */             Boolean.valueOf(noLocal) });
/*      */     }
/* 1443 */     this.connection = connection;
/* 1444 */     this.destination = (WMQDestination)destination;
/* 1445 */     this.fqSubName = fqSubName;
/* 1446 */     this.msgRefHandler = msgRefHandler;
/* 1447 */     this.quantityHint = quantityHint;
/* 1448 */     this.shared = shared;
/* 1449 */     this.durable = durable;
/*      */     
/* 1451 */     this.shadow = new WMQConnectionBrowserShadow(connection, this.destination, selector, shared, durable, this.fqSubName);
/*      */ 
/*      */ 
/*      */     
/* 1455 */     connection.lockHconn();
/*      */     try {
/* 1457 */       boolean didSuspend = connection.suspendAsyncService();
/*      */       try {
/* 1459 */         this.shadow.initialize();
/*      */       } finally {
/*      */         
/* 1462 */         if (Trace.isOn) {
/* 1463 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "<init>(WMQConnection,ProviderDestination,String,String,boolean,boolean,ProviderMessageReferenceHandler,int,boolean)");
/*      */         }
/*      */ 
/*      */         
/* 1467 */         if (didSuspend) {
/* 1468 */           connection.resumeAsyncService();
/*      */         }
/*      */       } 
/* 1471 */       this.shadow.registerMessageListener();
/*      */     } finally {
/*      */       
/* 1474 */       connection.unlockHconn();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1479 */     if (getIntProperty("XMSC_WMQ_MESSAGE_RETENTION") == 0 && destination.isQueue()) {
/* 1480 */       WMQMessageRetentionProcessor mRetProc = WMQMessageRetentionProcessor.getInstance();
/* 1481 */       mRetProc.registerConnectionBrowser(this, this.destination, selector);
/*      */     } 
/* 1483 */     if (Trace.isOn) {
/* 1484 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "<init>(WMQConnection,ProviderDestination,String,String,boolean,boolean,ProviderMessageReferenceHandler,int,boolean)");
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
/*      */   public void close(boolean closingFromConnection) throws JMSException {
/* 1496 */     if (Trace.isOn) {
/* 1497 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "close(boolean)", new Object[] {
/* 1498 */             Boolean.valueOf(closingFromConnection)
/*      */           });
/*      */     }
/*      */     
/* 1502 */     if (getIntProperty("XMSC_WMQ_MESSAGE_RETENTION") == 0 && this.destination.isQueue()) {
/* 1503 */       WMQMessageRetentionProcessor mRetProc = WMQMessageRetentionProcessor.getInstance();
/* 1504 */       mRetProc.removeConnectionBrowser(this, this.destination);
/*      */     } 
/*      */     
/* 1507 */     this.shadow.close();
/*      */     
/* 1509 */     if (Trace.isOn) {
/* 1510 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "close(boolean)");
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
/*      */   public void start() throws JMSException {
/* 1523 */     if (Trace.isOn) {
/* 1524 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "start()");
/*      */     }
/*      */     
/* 1527 */     if (Trace.isOn) {
/* 1528 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "start()");
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
/*      */   public void stop() throws JMSException {
/* 1541 */     if (Trace.isOn) {
/* 1542 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "stop()");
/*      */     }
/*      */     
/* 1545 */     if (Trace.isOn) {
/* 1546 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "stop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQConnection getConnection() {
/* 1555 */     if (Trace.isOn) {
/* 1556 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnectionBrowser", "getConnection()", "getter", this.connection);
/*      */     }
/*      */     
/* 1559 */     return this.connection;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQConnectionBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */