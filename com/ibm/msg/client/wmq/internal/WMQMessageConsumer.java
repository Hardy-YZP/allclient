/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQSD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiSP;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.LpiSD;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*     */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*     */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.concurrent.locks.ReentrantLock;
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
/*     */ public class WMQMessageConsumer
/*     */   extends WMQPropertyContext
/*     */   implements ProviderMessageConsumer
/*     */ {
/*     */   private static final long serialVersionUID = 2284996314173494058L;
/*     */   public static final String sccsid1 = "@(#) com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageConsumer.java, jmscc.wmq, k000 1.97 09/03/30 10:20:54";
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageConsumer.java");
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     PropertyStore.register("com.ibm.mq.jms.SupportMQExtensions", false);
/*     */   }
/*     */   static void checkJmqiCallSuccess(String messageid, HashMap<String, ? extends Object> inserts, Pint completionCode, Pint reason, JmqiEnvironment environment, String probeid, WMQConsumerOwner owner, Hconn hconn) throws JMSException { if (Trace.isOn)
/*     */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,HashMap<String , ? extends Object>,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn)", new Object[] { messageid, inserts, completionCode, reason, environment, probeid, owner, hconn });  checkJmqiCallSuccess(messageid, inserts, completionCode, reason, environment, probeid, owner, hconn, false); if (Trace.isOn)
/*     */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,HashMap<String , ? extends Object>,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn)");  }
/*     */   static void checkJmqiCallSuccess(String messageid, String insert, String insertKey, Pint completionCode, Pint reason, JmqiEnvironment environment, String probeid, WMQConsumerOwner owner, Hconn hconn) throws JMSException { if (Trace.isOn)
/*     */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,String,String,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn)", new Object[] { messageid, insert, insertKey, completionCode, reason, environment, probeid, owner, hconn });  HashMap<String, String> inserts = new HashMap<>(); inserts.put(insertKey, insert); checkJmqiCallSuccess(messageid, (HashMap)inserts, completionCode, reason, environment, probeid, owner, hconn, false); if (Trace.isOn)
/*     */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,String,String,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn)");  }
/*     */   static void checkJmqiCallSuccess(String messageid, HashMap<String, ? extends Object> inserts, Pint completionCode, Pint reason, JmqiEnvironment environment, String probeid, WMQConsumerOwner owner, Hconn hconn, boolean fromAsyncConsumer) throws JMSException { if (Trace.isOn)
/*     */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,HashMap<String , ? extends Object>,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn,boolean)", new Object[] { messageid, inserts, completionCode, reason, environment, probeid, owner, hconn, Boolean.valueOf(fromAsyncConsumer) });  if (reason.x != 0 || completionCode.x != 0) {
/*     */       if (Reason.isImpossibleReason(reason.x, completionCode.x, (JmqiSP)owner.getJmqiMQ())) {
/*     */         HashMap<String, Object> info = new HashMap<>(); info.put("messageid", messageid); info.put("inserts", inserts); info.put("reason", reason); info.put("compcode", completionCode); info.put("hconn", hconn); Trace.ffst("WMQMessageConsumer", "checkJmqiCallSuccess", probeid, info, JMSException.class);
/*     */       }  boolean isConnectionBroken = Reason.isConnectionBroken(reason.x); if (isConnectionBroken || fromAsyncConsumer) {
/*     */         JMSException e = Reason.createException("JMSWMQ1107", null, reason.x, completionCode.x, environment); ((WMQConnection)owner.getConnection()).driveExceptionListener(e, isConnectionBroken);
/*     */       }  JMSException je = Reason.createException(messageid, inserts, reason.x, completionCode.x, environment); if (Trace.isOn)
/*     */         Trace.throwing("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,HashMap<String , ? extends Object>,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn,boolean)", (Throwable)je);  throw je;
/*     */     }  if (Trace.isOn)
/*     */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,HashMap<String , ? extends Object>,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn,boolean)");  } static void checkJmqiCallSuccess(String messageid, String insert, String insertKey, Pint completionCode, Pint reason, JmqiEnvironment environment, String probeid, WMQConsumerOwner owner, Hconn hconn, boolean fromAsyncConsumer) throws JMSException { if (Trace.isOn)
/*     */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,String,String,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn,boolean)", new Object[] { messageid, insert, insertKey, completionCode, reason, environment, probeid, owner, hconn, Boolean.valueOf(fromAsyncConsumer) }); 
/*     */     HashMap<String, String> inserts = new HashMap<>();
/*     */     inserts.put(insertKey, insert);
/*     */     checkJmqiCallSuccess(messageid, (HashMap)inserts, completionCode, reason, environment, probeid, owner, hconn, fromAsyncConsumer);
/*     */     if (Trace.isOn)
/* 427 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkJmqiCallSuccess(String,String,String,Pint,Pint,JmqiEnvironment,String,WMQConsumerOwner,Hconn,boolean)");  } public WMQMessageConsumer(WMQDestination destination, WMQSession session, String subscriptionName, String selector, boolean nolocal, boolean shared, boolean durable, JmsPropertyContext jmsProps) throws JMSException { super(jmsProps);
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "<init>(WMQDestination,WMQSession,String,String,boolean,boolean,boolean,JmsPropertyContext)", new Object[] { destination, session, subscriptionName, selector, 
/*     */             
/* 431 */             Boolean.valueOf(nolocal), 
/* 432 */             Boolean.valueOf(shared), Boolean.valueOf(durable), jmsProps });
/*     */     }
/*     */     
/* 435 */     this.destination = destination;
/* 436 */     this.session = session;
/* 437 */     this.subscriptionName = subscriptionName;
/* 438 */     this.selector = selector;
/* 439 */     this.nolocal = nolocal;
/* 440 */     this.shared = shared;
/* 441 */     this.durable = durable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 448 */     boolean supportMQExtensions = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.SupportMQExtensions").booleanValue();
/*     */     
/* 450 */     if (!supportMQExtensions) {
/* 451 */       String clientID = this.session.getConnection().getStringProperty("XMSC_CLIENT_ID");
/*     */       
/* 453 */       if (nolocal && destination.isTopic() && clientID != null && !clientID.equals("")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 459 */         this.selector = (this.selector == null || this.selector.equals("")) ? "" : (this.selector + " AND ");
/*     */ 
/*     */ 
/*     */         
/* 463 */         this
/*     */           
/* 465 */           .selector = this.selector + "( " + "XMSC_CLIENT_ID" + " IS NULL OR " + "XMSC_CLIENT_ID" + " <> '" + this.session.getConnection().getStringProperty("XMSC_CLIENT_ID") + "' )";
/*     */         
/* 467 */         if (Trace.isOn) {
/* 468 */           Trace.data(this, "<init>(WMQDestination,WMQSession,String,boolean,JmsPropertyContext)", "Edited selector to perform nolocal checks. Using extended selector = \"" + this.selector + "\"");
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 475 */     Trace.registerFFSTObject(this);
/*     */     
/* 477 */     if (!destination.isTopic()) {
/* 478 */       checkDestinationValidForNPHigh();
/*     */     }
/*     */     
/* 481 */     this.syncShadow = new WMQSyncConsumerShadow((JmsPropertyContext)this, this.session, this.destination, this.selector, this.nolocal, this.shared, this.durable, this.subscriptionName);
/*     */     
/* 483 */     this.currentShadow = this.syncShadow;
/*     */ 
/*     */     
/* 486 */     this.currentShadow.initialize();
/* 487 */     if (Trace.isOn)
/* 488 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "<init>(WMQDestination,WMQSession,String,String,boolean,boolean,boolean,JmsPropertyContext)");  } static void deleteDurableSubscription(WMQConsumerOwner owner, String subscriptionName) throws JMSException { if (Trace.isOn)
/*     */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "deleteDurableSubscription(WMQConsumerOwner,String)", new Object[] { owner, subscriptionName });  JmqiMQ tempMQ = owner.getJmqiMQ();
/*     */     Hconn tempHconn = owner.getHconn();
/*     */     JmqiEnvironment tempEnv = owner.getJmqiEnvironment();
/*     */     MQSD mqsd = tempEnv.newMQSD();
/*     */     mqsd.getSubName().setVsString(subscriptionName);
/*     */     mqsd.getSubName().setVsBufSize(10240);
/*     */     LpiSD spisd = ((JmqiSystemEnvironment)tempEnv).newSpiSD();
/*     */     spisd.setDestOpenOptions(2);
/*     */     Pint tempCC = tempEnv.newPint();
/*     */     Pint tempRC = tempEnv.newPint();
/*     */     Phobj tempPhsub = tempEnv.newPhobj();
/*     */     Phobj tempPhobj = tempEnv.newPhobj();
/*     */     tempPhobj.setHobj(CMQC.jmqi_MQHO_NONE);
/*     */     mqsd.setOptions(556);
/*     */     ((JmqiSP)tempMQ).spiSubscribe(tempHconn, spisd, mqsd, tempPhobj, tempPhsub, tempCC, tempRC);
/*     */     if (tempRC.x == 29440) {
/*     */       int spiOptions = spisd.getOptions();
/*     */       spiOptions |= 0x10;
/*     */       spiOptions |= 0x40;
/*     */       spisd.setOptions(spiOptions);
/*     */       ((JmqiSP)tempMQ).spiSubscribe(tempHconn, spisd, mqsd, tempPhobj, tempPhsub, tempCC, tempRC);
/*     */     } 
/*     */     checkJmqiCallSuccess("JMSWMQ0025", subscriptionName, "XMSC_DESTINATION_NAME", tempCC, tempRC, tempEnv, "XN00400D", owner, tempHconn);
/*     */     tempMQ.MQCLOSE(tempHconn, tempPhsub, 8, tempCC, tempRC);
/*     */     checkJmqiCallSuccess("JMSWMQ0025", subscriptionName, "XMSC_DESTINATION_NAME", tempCC, tempRC, tempEnv, "XN00400E", owner, tempHconn);
/*     */     tempMQ.MQCLOSE(tempHconn, tempPhobj, 0, tempCC, tempRC);
/*     */     checkJmqiCallSuccess("JMSWMQ2000", subscriptionName, "XMSC_DESTINATION_NAME", tempCC, tempRC, tempEnv, "XN00400F", owner, tempHconn);
/*     */     owner.removeSubscription(subscriptionName);
/*     */     if (Trace.isOn)
/*     */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "deleteDurableSubscription(WMQConsumerOwner,String)");  } private transient WMQAsyncConsumerShadow asyncShadow = null; private transient WMQConsumerShadow currentShadow = null; private WMQDestination destination; private boolean nolocal; private String selector;
/* 519 */   private void checkDestinationValidForNPHigh() throws JMSException { if (Trace.isOn) {
/* 520 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkDestinationValidForNPHigh()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 525 */     if (this.destination.isQueue()) {
/* 526 */       int destinationPersistence = this.destination.getIntProperty("deliveryMode");
/* 527 */       if (destinationPersistence == 3 && 
/* 528 */         !WMQUtils.isNPMClassHigh(this.session, this.destination)) {
/*     */ 
/*     */         
/* 531 */         HashMap<String, Object> inserts = new HashMap<>();
/* 532 */         inserts.put("XMSC_DESTINATION_NAME", this.destination.getName());
/* 533 */         inserts.put("persistence", Integer.valueOf(destinationPersistence));
/* 534 */         JMSException je = (JMSException)NLSServices.createException("JMSWMQ2010", inserts);
/* 535 */         if (Trace.isOn) {
/* 536 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkDestinationValidForNPHigh()", (Throwable)je);
/*     */         }
/*     */         
/* 539 */         throw je;
/*     */       } 
/*     */     } 
/*     */     
/* 543 */     if (Trace.isOn)
/* 544 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "checkDestinationValidForNPHigh()");  }
/*     */ 
/*     */   
/*     */   private WMQSession session;
/*     */   private String subscriptionName;
/*     */   private boolean shared;
/*     */   private boolean durable;
/*     */   private transient WMQSyncConsumerShadow syncShadow = null;
/*     */   private transient WMQPoison poison = null;
/*     */   
/*     */   public void close(boolean closingFromSession) throws JMSException {
/* 555 */     if (Trace.isOn) {
/* 556 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "close(boolean)", new Object[] {
/* 557 */             Boolean.valueOf(closingFromSession)
/*     */           });
/*     */     }
/* 560 */     close(closingFromSession, (ReentrantLock)null);
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "close(boolean)");
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
/*     */   public void close(boolean closingFromSession, ReentrantLock onMessageLock) throws JMSException {
/* 574 */     if (Trace.isOn) {
/* 575 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "close(boolean,ReentrantLock)", new Object[] {
/* 576 */             Boolean.valueOf(closingFromSession), onMessageLock
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 584 */       this.currentShadow.close(onMessageLock);
/*     */     } finally {
/*     */       
/* 587 */       if (Trace.isOn) {
/* 588 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "close(boolean,ReentrantLock)");
/*     */       }
/*     */ 
/*     */       
/* 592 */       Trace.deRegisterFFSTObject(this);
/*     */     } 
/*     */     
/* 595 */     if (Trace.isOn) {
/* 596 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "close(boolean,ReentrantLock)");
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
/*     */   public void handlePoisonMessage(ProviderMessage message) throws JMSException {
/* 611 */     if (Trace.isOn) {
/* 612 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "handlePoisonMessage(ProviderMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/* 616 */     initialisePoison();
/* 617 */     this.poison.handlePoisonMessage((WMQMessage)message);
/* 618 */     if (Trace.isOn) {
/* 619 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "handlePoisonMessage(ProviderMessage)");
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
/*     */   public boolean shouldMessageBeRequeued(int attempts, ProviderMessage message) throws JMSException {
/* 637 */     if (Trace.isOn) {
/* 638 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "shouldMessageBeRequeued(int,ProviderMessage)", new Object[] {
/* 639 */             Integer.valueOf(attempts), message
/*     */           });
/*     */     }
/*     */     
/* 643 */     initialisePoison();
/* 644 */     boolean requeued = this.poison.shouldMessageBeRequeued(attempts);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 651 */     message.setIntProperty("JMSXDeliveryCount", attempts + 1);
/* 652 */     if (Trace.isOn) {
/* 653 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "shouldMessageBeRequeued(int,ProviderMessage)", 
/* 654 */           Boolean.valueOf(requeued));
/*     */     }
/* 656 */     return requeued;
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
/*     */   public ProviderMessage receive(long timeOut) throws JMSException {
/* 669 */     if (Trace.isOn) {
/* 670 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "receive(long)", new Object[] {
/* 671 */             Long.valueOf(timeOut)
/*     */           });
/*     */     }
/* 674 */     ProviderMessage traceRet1 = this.currentShadow.receive(timeOut);
/*     */     
/* 676 */     if (Trace.isOn) {
/* 677 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "receive(long)", traceRet1);
/*     */     }
/*     */     
/* 680 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageListener(ProviderMessageListener listener) throws JMSException {
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "setMessageListener(ProviderMessageListener)", "setter", listener);
/*     */     }
/*     */     
/* 694 */     if (listener != null) {
/*     */       
/* 696 */       if (this.asyncShadow == null) {
/* 697 */         this.asyncShadow = new WMQAsyncConsumerShadow((JmsPropertyContext)this, this.session, this.destination, this.selector, this.nolocal, this.shared, this.durable, this.subscriptionName);
/*     */       }
/*     */       
/* 700 */       this.asyncShadow.initialize(this.currentShadow);
/* 701 */       this.currentShadow = this.asyncShadow;
/*     */       
/* 703 */       this.currentShadow.setMessageListener(listener);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 708 */       this.currentShadow.setMessageListener(null);
/* 709 */       if (this.syncShadow == null) {
/* 710 */         this.syncShadow = new WMQSyncConsumerShadow((JmsPropertyContext)this, this.session, this.destination, this.selector, this.nolocal, this.shared, this.durable, this.subscriptionName);
/*     */       }
/*     */       
/* 713 */       this.syncShadow.initialize(this.currentShadow);
/* 714 */       this.currentShadow = this.syncShadow;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(boolean deliverImmediately) throws JMSException {
/* 723 */     if (Trace.isOn)
/* 724 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "start(boolean)", new Object[] {
/* 725 */             Boolean.valueOf(deliverImmediately)
/*     */           }); 
/* 727 */     this.currentShadow.setRunning(true);
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "start(boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws JMSException {
/* 739 */     if (Trace.isOn) {
/* 740 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "stop()");
/*     */     }
/* 742 */     this.currentShadow.setRunning(false);
/* 743 */     if (Trace.isOn) {
/* 744 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "stop()");
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
/*     */   Hobj getHobj() {
/* 756 */     if (Trace.isOn) {
/* 757 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "getHobj()", "getter", this.currentShadow.hobj);
/*     */     }
/*     */     
/* 760 */     return this.currentShadow.hobj;
/*     */   }
/*     */   
/*     */   private void initialisePoison() throws JMSException {
/* 764 */     if (Trace.isOn) {
/* 765 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "initialisePoison()");
/*     */     }
/*     */     
/* 768 */     if (null == this.poison) {
/* 769 */       String subQueue = this.currentShadow.getSubscriptionQueue();
/* 770 */       this.poison = new WMQPoison(this.session, this.destination, getHobj(), subQueue);
/*     */     } 
/* 772 */     if (Trace.isOn) {
/* 773 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "initialisePoison()");
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
/*     */   public String toString() {
/* 785 */     if (Trace.isOn) {
/* 786 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "toString()");
/*     */     }
/* 788 */     TableBuilder builder = new TableBuilder();
/* 789 */     builder.append("Instance", super.toString());
/* 790 */     if (this.session == null) {
/* 791 */       builder.append("Parent Session", "<null>");
/*     */     } else {
/*     */       
/* 794 */       builder.append("Parent Session", this.session.getClass().getName() + '@' + Integer.toHexString(this.session.hashCode()));
/*     */     } 
/* 796 */     builder.append("destination", this.destination);
/* 797 */     builder.append("nolocal", Boolean.valueOf(this.nolocal));
/* 798 */     builder.append("selector", this.selector);
/* 799 */     builder.append("subcriptionName", this.subscriptionName);
/* 800 */     builder.append("asyncShadow", this.asyncShadow);
/* 801 */     builder.append("syncShadow", this.syncShadow);
/* 802 */     builder.append("currentShadow", this.currentShadow);
/*     */     
/* 804 */     String traceRet1 = builder.toString();
/* 805 */     if (Trace.isOn) {
/* 806 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "toString()", traceRet1);
/*     */     }
/*     */     
/* 809 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter pw, int level) {
/* 817 */     if (Trace.isOn) {
/* 818 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "dump(PrintWriter,int)", new Object[] { pw, 
/* 819 */             Integer.valueOf(level) });
/*     */     }
/* 821 */     String prefix = Trace.buildPrefix(level);
/* 822 */     pw.format("%s%s%n", new Object[] { prefix, super.toString() });
/* 823 */     if (this.creator != null) {
/* 824 */       pw.format("%s  created by \"%s\" (id: %d) @ %s - stack :%n", new Object[] { prefix, this.creator
/* 825 */             .getName(), Long.valueOf(this.creator.getId()), Trace.formatTimeStamp(this.createTime) });
/* 826 */       Trace.dumpStack(pw, level + 1, this.createStack);
/*     */     } 
/*     */     
/* 829 */     if (this.session == null) {
/* 830 */       pw.format("%s  Parent Session <null>%n", new Object[] { prefix });
/*     */     } else {
/*     */       
/* 833 */       pw.format("%s  Parent Session %s@%x%n", new Object[] { prefix, this.session.getClass().getName(), Integer.valueOf(this.session.hashCode()) });
/*     */     } 
/*     */     
/* 836 */     pw.format("%s  nolocal %b%n", new Object[] { prefix, Boolean.valueOf(this.nolocal) });
/* 837 */     pw.format("%s  selector %s%n", new Object[] { prefix, String.valueOf(this.selector) });
/* 838 */     pw.format("%s  subscriptionName %s%n", new Object[] { prefix, String.valueOf(this.subscriptionName) });
/* 839 */     if (this.asyncShadow != null) {
/* 840 */       pw.format("%s  asyncShadow %s received %d message(s), last @ %s%n", new Object[] { prefix, this.asyncShadow, Integer.valueOf(this.asyncShadow.getReceiveCount()), 
/* 841 */             Trace.formatTimeStamp(this.asyncShadow.getLastReceiveTime()) });
/*     */     }
/* 843 */     if (this.syncShadow != null) {
/* 844 */       pw.format("%s  syncShadow %s received %d message(s) last @ %s%n", new Object[] { prefix, this.syncShadow, Integer.valueOf(this.syncShadow.getReceiveCount()), Trace.formatTimeStamp(this.syncShadow.getLastReceiveTime()) });
/*     */     }
/*     */     
/* 847 */     pw.format("%s  currentShadow %s%n", new Object[] { prefix, String.valueOf(this.currentShadow) });
/* 848 */     if (this.destination == null) {
/* 849 */       pw.format("%s  destination is null!%n", new Object[] { prefix });
/*     */     } else {
/*     */       
/* 852 */       this.destination.dump(pw, level + 1);
/*     */     } 
/* 854 */     if (Trace.isOn) {
/* 855 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "dump(PrintWriter,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage lockMessage(long timeout) throws JMSException {
/* 866 */     if (Trace.isOn)
/* 867 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "lockMessage(long)", new Object[] {
/* 868 */             Long.valueOf(timeout)
/*     */           }); 
/* 870 */     ProviderMessage pmsg = this.syncShadow.receiveAndLock((int)timeout);
/*     */     
/* 872 */     if (Trace.isOn) {
/* 873 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "lockMessage(long)", pmsg);
/*     */     }
/*     */     
/* 876 */     return pmsg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLockedMessage(ProviderMessage msg) throws JMSException {
/* 884 */     if (Trace.isOn) {
/* 885 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "removeLockedMessage(ProviderMessage)", new Object[] { msg });
/*     */     }
/*     */     
/* 888 */     byte[] msgToken = ((WMQMessage)msg).getWmqMsgToken();
/* 889 */     int msgLength = ((WMQMessage)msg).getWmqMsgLength();
/*     */     
/* 891 */     this.syncShadow.deleteLockedMessage(msgToken, msgLength);
/* 892 */     if (Trace.isOn) {
/* 893 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "removeLockedMessage(ProviderMessage)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlockMessage() throws JMSException {
/* 904 */     if (Trace.isOn) {
/* 905 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "unlockMessage()");
/*     */     }
/* 907 */     this.syncShadow.unlock();
/* 908 */     if (Trace.isOn)
/* 909 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageConsumer", "unlockMessage()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQMessageConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */