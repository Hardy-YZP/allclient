/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQSD;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
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
/*      */ public class MQTopic
/*      */   extends MQDestination
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQTopic.java";
/*      */   private boolean durable;
/*      */   private boolean managed;
/*      */   private boolean subscribed;
/*      */   private MQSubscription subscriptionReference;
/*      */   private MQDestination unmanagedDestinationReference;
/*      */   
/*      */   static {
/*   60 */     if (Trace.isOn) {
/*   61 */       Trace.data("com.ibm.mq.MQTopic", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQTopic.java");
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
/*      */   public MQTopic(MQQueueManager qMgr, String topicName, String topicObject, int openAs, int options) throws MQException {
/*  192 */     this(qMgr, topicName, topicObject, openAs, options, (String)null);
/*  193 */     if (Trace.isOn) {
/*  194 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,int)", new Object[] { qMgr, topicName, topicObject, 
/*  195 */             Integer.valueOf(openAs), 
/*  196 */             Integer.valueOf(options) });
/*      */     }
/*  198 */     if (Trace.isOn) {
/*  199 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,int)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQTopic(MQQueueManager qMgr, String topicName, String topicObject, int openAs, int options, String alternateUserId) throws MQException {
/*  303 */     super(8, qMgr, topicObject, options, alternateUserId);
/*  304 */     if (Trace.isOn) {
/*  305 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,int,String)", new Object[] { qMgr, topicName, topicObject, 
/*      */             
/*  307 */             Integer.valueOf(openAs), Integer.valueOf(options), alternateUserId });
/*      */     }
/*      */     
/*  310 */     if (openAs == 1) {
/*  311 */       if ((getOpenOptions() & 0x8) != 0) {
/*  312 */         MQException ex = new MQException(2, 2440, this);
/*      */         
/*  314 */         if (Trace.isOn) {
/*  315 */           Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,int,String)", ex);
/*      */         }
/*      */         
/*  318 */         throw ex;
/*      */       } 
/*      */       
/*  321 */       if ((getOpenOptions() & 0x20) == 0) {
/*  322 */         setOpenOptions(getOpenOptions() | 0x20);
/*      */       }
/*      */       
/*  325 */       openForSubscription((MQDestination)null, topicName, (String)null, (Hashtable<String, Object>)null);
/*      */     } else {
/*      */       
/*  328 */       openForPublication(topicName);
/*      */     } 
/*      */ 
/*      */     
/*  332 */     this.parentQmgr.registerTopic(this);
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,int,String)");
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
/*      */   private void openForPublication(String topicName) throws MQException {
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.entry(this, "com.ibm.mq.MQTopic", "openForPublication(String)", new Object[] { topicName });
/*      */     }
/*      */     
/*  353 */     String fid = "openForPublication(String)";
/*      */     
/*      */     try {
/*  356 */       setQueueManagerCmdLevel(this.Hconn.getHconn().getCmdLevel());
/*      */     }
/*  358 */     catch (JmqiException e) {
/*  359 */       if (Trace.isOn) {
/*  360 */         Trace.catchBlock(this, "com.ibm.mq.MQTopic", "openForPublication(String)", (Throwable)e);
/*      */       }
/*      */       
/*  363 */       MQException traceRet1 = new MQException(e.getCompCode(), e.getReason(), this, e);
/*  364 */       if (Trace.isOn) {
/*  365 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "openForPublication(String)", traceRet1);
/*      */       }
/*  367 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  371 */     this.subscribed = false;
/*      */ 
/*      */     
/*  374 */     MQOD mqObjectDescriptor = createMQOD();
/*  375 */     mqObjectDescriptor.setVersion(4);
/*  376 */     if (isValidStringParameter(topicName)) {
/*  377 */       mqObjectDescriptor.getObjectString().setVsString(topicName);
/*      */     }
/*      */ 
/*      */     
/*  381 */     if (Trace.isOn) {
/*  382 */       Trace.data(this, "openForPublication(String)", "topic string = " + mqObjectDescriptor.getObjectString() + "\ntopic object = " + mqObjectDescriptor
/*  383 */           .getObjectName() + "\nqueue manager = " + mqObjectDescriptor
/*  384 */           .getObjectQMgrName() + "\noptions = " + 
/*  385 */           getOpenOptions(), "");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  390 */     open(mqObjectDescriptor);
/*      */     
/*  392 */     if (Trace.isOn) {
/*  393 */       Trace.exit(this, "com.ibm.mq.MQTopic", "openForPublication(String)");
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
/*      */   public MQTopic(MQQueueManager qMgr, String topicName, String topicObject, int options, String alternateUserId, String subscriptionName) throws MQException {
/*  480 */     this(qMgr, topicName, topicObject, options, alternateUserId, subscriptionName, (Hashtable<String, Object>)null);
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,String,String)", new Object[] { qMgr, topicName, topicObject, 
/*      */             
/*  484 */             Integer.valueOf(options), alternateUserId, subscriptionName });
/*      */     }
/*  486 */     if (Trace.isOn) {
/*  487 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,String,String)");
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
/*      */   public MQTopic(MQQueueManager qMgr, String topicName, String topicObject, int options, String alternateUserId, String subscriptionName, Hashtable<String, Object> parameters) throws MQException {
/*  597 */     super(8, qMgr, topicObject, options, alternateUserId);
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,String,String,Hashtable<String , Object>)", new Object[] { qMgr, topicName, topicObject, 
/*      */             
/*  601 */             Integer.valueOf(options), alternateUserId, subscriptionName, parameters });
/*      */     }
/*      */     
/*  604 */     if (subscriptionName == null && (
/*  605 */       getOpenOptions() & 0x8) != 0) {
/*  606 */       MQException ex = new MQException(2, 2440, this);
/*      */       
/*  608 */       if (Trace.isOn) {
/*  609 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,String,String,Hashtable<String , Object>)", ex, 1);
/*      */       }
/*      */       
/*  612 */       throw ex;
/*      */     } 
/*      */ 
/*      */     
/*  616 */     if ((getOpenOptions() & 0x20) == 0) {
/*  617 */       setOpenOptions(getOpenOptions() | 0x20);
/*      */     }
/*      */     
/*  620 */     if (!validTopicParameters(parameters)) {
/*  621 */       MQException ex = new MQException(2, 2046, this);
/*  622 */       if (Trace.isOn) {
/*  623 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,String,String,Hashtable<String , Object>)", ex, 2);
/*      */       }
/*      */ 
/*      */       
/*  627 */       throw ex;
/*      */     } 
/*      */ 
/*      */     
/*  631 */     openForSubscription((MQDestination)null, topicName, subscriptionName, parameters);
/*      */ 
/*      */     
/*  634 */     this.parentQmgr.registerTopic(this);
/*  635 */     if (Trace.isOn) {
/*  636 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,String,String,int,String,String,Hashtable<String , Object>)");
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
/*      */   public MQTopic(MQQueueManager qMgr, MQDestination destination, String topicName, String topicObject, int options) throws MQException {
/*  720 */     this(qMgr, destination, topicName, topicObject, options, (String)null, (String)null, (Hashtable<String, Object>)null);
/*  721 */     if (Trace.isOn) {
/*  722 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int)", new Object[] { qMgr, destination, topicName, topicObject, 
/*      */             
/*  724 */             Integer.valueOf(options) });
/*      */     }
/*  726 */     if (Trace.isOn) {
/*  727 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int)");
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
/*      */   public MQTopic(MQQueueManager qMgr, MQDestination destination, String topicName, String topicObject, int options, String alternateUserId) throws MQException {
/*  821 */     this(qMgr, destination, topicName, topicObject, options, alternateUserId, (String)null, (Hashtable<String, Object>)null);
/*  822 */     if (Trace.isOn) {
/*  823 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String)", new Object[] { qMgr, destination, topicName, topicObject, 
/*      */             
/*  825 */             Integer.valueOf(options), alternateUserId });
/*      */     }
/*  827 */     if (Trace.isOn) {
/*  828 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String)");
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
/*      */   public MQTopic(MQQueueManager qMgr, MQDestination destination, String topicName, String topicObject, int options, String alternateUserId, String subscriptionName) throws MQException {
/*  941 */     this(qMgr, destination, topicName, topicObject, options, alternateUserId, subscriptionName, (Hashtable<String, Object>)null);
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String)", new Object[] { qMgr, destination, topicName, topicObject, 
/*      */             
/*  945 */             Integer.valueOf(options), alternateUserId, subscriptionName });
/*      */     }
/*      */     
/*  948 */     if (Trace.isOn) {
/*  949 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String)");
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
/*      */   public MQTopic(MQQueueManager qMgr, MQDestination destination, String topicName, String topicObject, int options, String alternateUserId, String subscriptionName, Hashtable<String, Object> parameters) throws MQException {
/* 1090 */     super(8, qMgr, topicObject, options, alternateUserId);
/* 1091 */     if (Trace.isOn) {
/* 1092 */       Trace.entry(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String,Hashtable<String , Object>)", new Object[] { qMgr, destination, topicName, topicObject, 
/*      */             
/* 1094 */             Integer.valueOf(options), alternateUserId, subscriptionName, parameters });
/*      */     }
/*      */ 
/*      */     
/* 1098 */     if (destination == null) {
/* 1099 */       MQException ex = new MQException(2, 2152, this);
/*      */       
/* 1101 */       if (Trace.isOn) {
/* 1102 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String,Hashtable<String , Object>)", ex, 1);
/*      */       }
/*      */ 
/*      */       
/* 1106 */       throw ex;
/*      */     } 
/*      */     
/* 1109 */     if ((options & 0x20) != 0) {
/* 1110 */       MQException ex = new MQException(2, 2046, this);
/* 1111 */       if (Trace.isOn) {
/* 1112 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String,Hashtable<String , Object>)", ex, 2);
/*      */       }
/*      */ 
/*      */       
/* 1116 */       throw ex;
/*      */     } 
/*      */     
/* 1119 */     if (!validTopicParameters(parameters)) {
/* 1120 */       MQException ex = new MQException(2, 2046, this);
/* 1121 */       if (Trace.isOn) {
/* 1122 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String,Hashtable<String , Object>)", ex, 3);
/*      */       }
/*      */ 
/*      */       
/* 1126 */       throw ex;
/*      */     } 
/*      */     
/* 1129 */     if (subscriptionName == null && (
/* 1130 */       options & 0x8) != 0) {
/* 1131 */       MQException ex = new MQException(2, 2440, this);
/* 1132 */       if (Trace.isOn) {
/* 1133 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String,Hashtable<String , Object>)", ex, 4);
/*      */       }
/*      */ 
/*      */       
/* 1137 */       throw ex;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1142 */     openForSubscription(destination, topicName, subscriptionName, parameters);
/*      */ 
/*      */     
/* 1145 */     this.parentQmgr.registerTopic(this);
/* 1146 */     if (Trace.isOn) {
/* 1147 */       Trace.exit(this, "com.ibm.mq.MQTopic", "<init>(MQQueueManager,MQDestination,String,String,int,String,String,Hashtable<String , Object>)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1153 */   private static HashSet<String> validParameters = new HashSet<>(Arrays.asList(new String[] { "SubscriptionExpiry", "SubscriptionUserData", "SubscriptionCorrelationId", "PublicationPriority", "PublicationAccountingToken", "PublicationApplicationIdData" }));
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1158 */   private static HashSet<String> integerParameters = new HashSet<>(Arrays.asList(new String[] { "SubscriptionExpiry", "PublicationPriority" }));
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
/*      */   private static boolean validTopicParameters(Hashtable<String, Object> parameters) {
/* 1175 */     if (Trace.isOn) {
/* 1176 */       Trace.entry("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", new Object[] { parameters });
/*      */     }
/*      */ 
/*      */     
/* 1180 */     if (parameters == null) {
/* 1181 */       if (Trace.isOn) {
/* 1182 */         Trace.exit("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", 
/* 1183 */             Boolean.valueOf(true), 1);
/*      */       }
/* 1185 */       return true;
/*      */     } 
/*      */     
/* 1188 */     for (Map.Entry<String, Object> me : parameters.entrySet()) {
/* 1189 */       String currentParameter = me.getKey();
/* 1190 */       if (!validParameters.contains(currentParameter)) {
/* 1191 */         if (Trace.isOn) {
/* 1192 */           Trace.exit("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", 
/* 1193 */               Boolean.valueOf(false), 2);
/*      */         }
/* 1195 */         return false;
/*      */       } 
/* 1197 */       Object currentParameterValue = me.getValue();
/*      */       
/* 1199 */       boolean isIntegerParameter = integerParameters.contains(currentParameter);
/*      */       
/* 1201 */       if (isIntegerParameter && currentParameterValue instanceof Integer) {
/* 1202 */         if (Trace.isOn) {
/* 1203 */           Trace.exit("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", 
/* 1204 */               Boolean.valueOf(true), 3);
/*      */         }
/* 1206 */         return true;
/*      */       } 
/*      */       
/* 1209 */       if (!(currentParameterValue instanceof String)) {
/* 1210 */         if (Trace.isOn) {
/* 1211 */           Trace.exit("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", 
/* 1212 */               Boolean.valueOf(false), 4);
/*      */         }
/* 1214 */         return false;
/*      */       } 
/* 1216 */       if (isIntegerParameter) {
/*      */         try {
/* 1218 */           Integer.parseInt((String)currentParameterValue);
/*      */         }
/* 1220 */         catch (NumberFormatException nfe) {
/* 1221 */           if (Trace.isOn) {
/* 1222 */             Trace.catchBlock("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", nfe);
/*      */           }
/*      */           
/* 1225 */           if (Trace.isOn) {
/* 1226 */             Trace.exit("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", 
/* 1227 */                 Boolean.valueOf(false), 5);
/*      */           }
/* 1229 */           return false;
/*      */         } 
/*      */       }
/*      */     } 
/* 1233 */     if (Trace.isOn) {
/* 1234 */       Trace.exit("com.ibm.mq.MQTopic", "validTopicParameters(Hashtable<String , Object>)", 
/* 1235 */           Boolean.valueOf(true), 6);
/*      */     }
/* 1237 */     return true;
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
/*      */   private void openForSubscription(MQDestination destination, String topicName, String subscriptionName, Hashtable<String, Object> parameters) throws MQException {
/* 1257 */     if (Trace.isOn) {
/* 1258 */       Trace.entry(this, "com.ibm.mq.MQTopic", "openForSubscription(MQDestination,String,String,Hashtable<String , Object>)", new Object[] { destination, topicName, subscriptionName, parameters });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1264 */       setQueueManagerCmdLevel(this.Hconn.getHconn().getCmdLevel());
/*      */     }
/* 1266 */     catch (JmqiException e) {
/* 1267 */       if (Trace.isOn) {
/* 1268 */         Trace.catchBlock(this, "com.ibm.mq.MQTopic", "openForSubscription(MQDestination,String,String,Hashtable<String , Object>)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 1272 */       MQException traceRet1 = new MQException(e.getCompCode(), e.getReason(), this, e);
/* 1273 */       if (Trace.isOn) {
/* 1274 */         Trace.throwing(this, "com.ibm.mq.MQTopic", "openForSubscription(MQDestination,String,String,Hashtable<String , Object>)", traceRet1);
/*      */       }
/*      */       
/* 1277 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1280 */     int options = getOpenOptions();
/*      */     
/* 1282 */     this.subscribed = true;
/*      */     
/* 1284 */     if (destination != null)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1289 */       this.Hobj = destination.Hobj;
/*      */     }
/* 1291 */     this.unmanagedDestinationReference = destination;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1297 */     boolean durableRequested = ((options & 0x8) == 8);
/* 1298 */     boolean durablePossible = (subscriptionName != null && subscriptionName.length() > 0);
/* 1299 */     this.durable = (durableRequested && durablePossible);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1305 */     this.managed = (destination == null);
/* 1306 */     if (this.managed) {
/* 1307 */       setOpenOptions(getOpenOptions() | 0x20);
/*      */     }
/*      */ 
/*      */     
/* 1311 */     MQSD mqSubscriptionDescriptor = createMQSD(topicName, subscriptionName, parameters);
/* 1312 */     this.subscriptionReference = new MQSubscription(this, mqSubscriptionDescriptor);
/* 1313 */     boolean subcriptionOpen = this.subscriptionReference.isOpen();
/* 1314 */     this.resourceOpen = subcriptionOpen;
/* 1315 */     setOpen(subcriptionOpen);
/* 1316 */     setName(mqSubscriptionDescriptor.getResolvedObjectString().getVsString());
/*      */     
/* 1318 */     if (Trace.isOn) {
/* 1319 */       Trace.exit(this, "com.ibm.mq.MQTopic", "openForSubscription(MQDestination,String,String,Hashtable<String , Object>)");
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
/*      */   private MQSD createMQSD(String topicName, String subscriptionName, Hashtable<String, Object> parameters) throws MQException {
/* 1336 */     if (Trace.isOn) {
/* 1337 */       Trace.entry(this, "com.ibm.mq.MQTopic", "createMQSD(String,String,Hashtable<String , Object>)", new Object[] { topicName, subscriptionName, parameters });
/*      */     }
/*      */ 
/*      */     
/* 1341 */     MQSD mqSubscriptionDescriptor = this.env.newMQSD();
/*      */     
/* 1343 */     String altUserId = getAlternateUserId();
/* 1344 */     if (isValidStringParameter(altUserId)) {
/* 1345 */       mqSubscriptionDescriptor.setAlternateUserId(altUserId);
/*      */     } else {
/*      */       
/* 1348 */       altUserId = mqSubscriptionDescriptor.getAlternateUserId();
/* 1349 */       setAlternateUserId(altUserId);
/*      */     } 
/* 1351 */     String objectName = getName();
/* 1352 */     if (isValidStringParameter(objectName)) {
/* 1353 */       mqSubscriptionDescriptor.setObjectName(objectName);
/*      */     } else {
/*      */       
/* 1356 */       objectName = mqSubscriptionDescriptor.getObjectName();
/* 1357 */       setName(objectName);
/*      */     } 
/* 1359 */     if (isValidStringParameter(topicName)) {
/* 1360 */       mqSubscriptionDescriptor.getObjectString().setVsString(topicName);
/*      */     }
/* 1362 */     int options = getOpenOptions();
/* 1363 */     mqSubscriptionDescriptor.setOptions(options);
/* 1364 */     if (isValidStringParameter(subscriptionName)) {
/* 1365 */       mqSubscriptionDescriptor.getSubName().setVsString(subscriptionName);
/*      */     }
/*      */     
/* 1368 */     if (parameters != null) {
/* 1369 */       String alternateSecurityId = (String)parameters.get("AlternateSecurityId");
/* 1370 */       if (isValidStringParameter(alternateSecurityId)) {
/* 1371 */         mqSubscriptionDescriptor.setAlternateSecurityId(alternateSecurityId);
/*      */       }
/* 1373 */       String pubAccToken = (String)parameters.get("PublicationAccountingToken");
/* 1374 */       if (isValidStringParameter(pubAccToken)) {
/* 1375 */         byte[] pubAccountingToken = pubAccToken.getBytes(Charset.defaultCharset());
/* 1376 */         mqSubscriptionDescriptor.setPubAccountingToken(pubAccountingToken);
/*      */       } 
/* 1378 */       String pubApplIdentityData = (String)parameters.get("PublicationApplicationIdData");
/* 1379 */       if (isValidStringParameter(pubApplIdentityData)) {
/* 1380 */         mqSubscriptionDescriptor.setPubApplIdentityData(pubApplIdentityData);
/*      */       }
/* 1382 */       Object pubPrior = parameters.get("PublicationPriority");
/* 1383 */       if (pubPrior != null) {
/* 1384 */         if (pubPrior instanceof Integer) {
/* 1385 */           mqSubscriptionDescriptor.setPubPriority(((Integer)pubPrior).intValue());
/*      */         }
/* 1387 */         else if (pubPrior instanceof String && 
/* 1388 */           isValidStringParameter((String)pubPrior)) {
/* 1389 */           mqSubscriptionDescriptor.setPubPriority(Integer.parseInt((String)pubPrior));
/*      */         } 
/*      */       }
/*      */       
/* 1393 */       String subCorrel = (String)parameters.get("SubscriptionCorrelationId");
/* 1394 */       if (isValidStringParameter(subCorrel)) {
/* 1395 */         byte[] subCorrelId = subCorrel.getBytes();
/* 1396 */         mqSubscriptionDescriptor.setSubCorrelId(subCorrelId);
/*      */       } 
/* 1398 */       Object subExp = parameters.get("SubscriptionExpiry");
/* 1399 */       if (subExp != null) {
/* 1400 */         if (subExp instanceof Integer) {
/* 1401 */           mqSubscriptionDescriptor.setSubExpiry(((Integer)subExp).intValue());
/*      */         }
/* 1403 */         else if (subExp instanceof String && 
/* 1404 */           isValidStringParameter((String)subExp)) {
/* 1405 */           Integer subExpiry = Integer.valueOf((String)subExp);
/* 1406 */           mqSubscriptionDescriptor.setSubExpiry(subExpiry.intValue());
/*      */         } 
/*      */       }
/*      */       
/* 1410 */       String subUserData = (String)parameters.get("SubscriptionUserData");
/* 1411 */       if (isValidStringParameter(subUserData)) {
/* 1412 */         mqSubscriptionDescriptor.getSubUserData().setVsString(subUserData);
/*      */       }
/*      */     } 
/*      */     
/* 1416 */     mqSubscriptionDescriptor.getResolvedObjectString().setVsBufSize(10240);
/* 1417 */     mqSubscriptionDescriptor.getSelectionString().setVsBufSize(10240);
/*      */     
/* 1419 */     if (Trace.isOn) {
/* 1420 */       Trace.exit(this, "com.ibm.mq.MQTopic", "createMQSD(String,String,Hashtable<String , Object>)", mqSubscriptionDescriptor);
/*      */     }
/*      */     
/* 1423 */     return mqSubscriptionDescriptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDurable() throws MQException {
/* 1434 */     if (Trace.isOn) {
/* 1435 */       Trace.data(this, "com.ibm.mq.MQTopic", "isDurable()", "getter", Boolean.valueOf(this.durable));
/*      */     }
/* 1437 */     return this.durable;
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
/*      */   public boolean isManaged() throws MQException {
/* 1449 */     if (Trace.isOn) {
/* 1450 */       Trace.data(this, "com.ibm.mq.MQTopic", "isManaged()", "getter", Boolean.valueOf(this.managed));
/*      */     }
/* 1452 */     return this.managed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSubscribed() throws MQException {
/* 1463 */     if (Trace.isOn) {
/* 1464 */       Trace.data(this, "com.ibm.mq.MQTopic", "isSubscribed()", "getter", 
/* 1465 */           Boolean.valueOf(this.subscribed));
/*      */     }
/* 1467 */     return this.subscribed;
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
/*      */   public MQSubscription getSubscriptionReference() throws MQException {
/* 1483 */     if (Trace.isOn) {
/* 1484 */       Trace.data(this, "com.ibm.mq.MQTopic", "getSubscriptionReference()", "getter", this.subscriptionReference);
/*      */     }
/*      */     
/* 1487 */     return this.subscriptionReference;
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
/*      */   public MQDestination getUnmanagedDestinationReference() throws MQException {
/* 1504 */     if (Trace.isOn) {
/* 1505 */       Trace.data(this, "com.ibm.mq.MQTopic", "getUnmanagedDestinationReference()", "getter", this.unmanagedDestinationReference);
/*      */     }
/*      */     
/* 1508 */     return this.unmanagedDestinationReference;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void close() throws MQException {
/* 1516 */     if (Trace.isOn) {
/* 1517 */       Trace.entry(this, "com.ibm.mq.MQTopic", "close()");
/*      */     }
/* 1519 */     if (this.Hobj.getHobj().equals(CMQC.jmqi_MQHO_UNUSABLE_HOBJ) || this.Hobj.getHobj().equals(CMQC.jmqi_MQHO_NONE)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1525 */       setOpen(false);
/* 1526 */       if (this.subscriptionReference != null) {
/* 1527 */         this.subscriptionReference.setOpen(false);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1532 */       super.close();
/*      */       
/* 1534 */       if (this.subscriptionReference != null && 
/* 1535 */         !this.subscriptionReference.Hobj.getHobj().equals(CMQC.jmqi_MQHO_UNUSABLE_HOBJ) && !this.subscriptionReference.Hobj.getHobj().equals(CMQC.jmqi_MQHO_NONE)) {
/* 1536 */         this.subscriptionReference.close();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1541 */     if (this.parentQmgr != null) {
/* 1542 */       this.parentQmgr.unregisterTopic(this);
/*      */     }
/*      */     
/* 1545 */     if (Trace.isOn)
/* 1546 */       Trace.exit(this, "com.ibm.mq.MQTopic", "close()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */