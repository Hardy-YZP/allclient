/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.util.Vector;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQSubAdmin
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubAdmin.java";
/*      */   
/*      */   static {
/*   89 */     if (Trace.isOn) {
/*   90 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubAdmin.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   private MQQueue adminQueue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSubAdmin(MQQueueManager qm) throws JMSException {
/*  121 */     if (Trace.isOn) {
/*  122 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "<init>(MQQueueManager)", new Object[] { qm });
/*      */     }
/*      */     
/*  125 */     int MAXRETRIES = 20;
/*  126 */     int MAXWAIT = 5000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  134 */     int attempt = 0;
/*      */ 
/*      */     
/*      */     while (true) {
/*      */       try {
/*  139 */         this.adminQueue = qm.accessQueue("SYSTEM.JMS.ADMIN.QUEUE", 8252);
/*      */ 
/*      */       
/*      */       }
/*  143 */       catch (MQException e) {
/*  144 */         if (Trace.isOn) {
/*  145 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "<init>(MQQueueManager)", (Throwable)e, 1);
/*      */         }
/*      */         
/*  148 */         switch (e.reasonCode) {
/*      */           
/*      */           case 2042:
/*  151 */             attempt++;
/*  152 */             if (attempt < 20) {
/*      */               
/*  154 */               if (Trace.isOn) {
/*  155 */                 Trace.traceData(this, "admin queue locked, suppressing exception and retrying", null);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/*  168 */                 Thread.sleep((long)(100.0D + 5000.0D * Math.random()));
/*      */               
/*      */               }
/*  171 */               catch (InterruptedException ie) {
/*  172 */                 if (Trace.isOn) {
/*  173 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "<init>(MQQueueManager)", ie, 2);
/*      */                 }
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*  180 */             je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.ADMIN.QUEUE");
/*  181 */             je.setLinkedException((Exception)e);
/*  182 */             if (Trace.isOn) {
/*  183 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "<init>(MQQueueManager)", (Throwable)je, 1);
/*      */             }
/*      */             
/*  186 */             throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  191 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.ADMIN.QUEUE");
/*  192 */         je.setLinkedException((Exception)e);
/*  193 */         if (Trace.isOn) {
/*  194 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "<init>(MQQueueManager)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  197 */         throw je;
/*      */       } 
/*      */       
/*      */       break;
/*      */     } 
/*      */     
/*  203 */     if (Trace.isOn) {
/*  204 */       Trace.traceData(this, "Obtaining the admin queue lock required " + attempt + " retries", null);
/*      */     }
/*      */     
/*  207 */     if (Trace.isOn) {
/*  208 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "<init>(MQQueueManager)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearAll() {
/*  219 */     if (Trace.isOn) {
/*  220 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "clearAll()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  226 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */     
/*  228 */     boolean carryOn = true;
/*  229 */     while (carryOn) {
/*      */       try {
/*  231 */         MQMessage msg = new MQMessage();
/*  232 */         this.adminQueue.get(msg, gmo);
/*      */       }
/*  234 */       catch (MQException e) {
/*  235 */         if (Trace.isOn) {
/*  236 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "clearAll()", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  241 */         carryOn = false;
/*      */       } 
/*      */     } 
/*      */     
/*  245 */     if (Trace.isOn) {
/*  246 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "clearAll()");
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
/*      */   public void close() throws JMSException {
/*  258 */     if (Trace.isOn) {
/*  259 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "close()");
/*      */     }
/*      */     
/*      */     try {
/*  263 */       if (this.adminQueue != null) {
/*  264 */         this.adminQueue.close();
/*  265 */         this.adminQueue = null;
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  271 */     catch (MQException e) {
/*  272 */       if (Trace.isOn) {
/*  273 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "close()", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/*  277 */       JMSException je = ConfigEnvironment.newException("MQJMS2000");
/*  278 */       je.setLinkedException((Exception)e);
/*      */       
/*  280 */       if (Trace.isOn) {
/*  281 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "close()", (Throwable)je);
/*      */       }
/*      */       
/*  284 */       throw je;
/*      */     } 
/*      */     
/*  287 */     if (Trace.isOn) {
/*  288 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "close()");
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
/*      */   int getNumRecords() throws JMSException {
/*  300 */     int i = -1;
/*      */     try {
/*  302 */       i = this.adminQueue.getCurrentDepth();
/*      */     }
/*  304 */     catch (MQException e) {
/*  305 */       if (Trace.isOn) {
/*  306 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getNumRecords()", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/*  310 */       JMSException je = ConfigEnvironment.newException("MQJMS2011");
/*  311 */       je.setLinkedException((Exception)e);
/*      */       
/*  313 */       if (Trace.isOn) {
/*  314 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getNumRecords()", (Throwable)je);
/*      */       }
/*      */       
/*  317 */       throw je;
/*      */     } 
/*      */     
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getNumRecords()", "getter", 
/*  322 */           Integer.valueOf(i));
/*      */     }
/*  324 */     return i;
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
/*      */   public Vector getAll() {
/*  338 */     Vector<MQSubEntry> retVec = new Vector();
/*      */     
/*  340 */     MQSubEntry ae = null;
/*  341 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */     
/*  343 */     gmo.options = 16;
/*      */     
/*  345 */     boolean carryOn = true;
/*  346 */     while (carryOn) {
/*      */       try {
/*  348 */         MQMessage msg = new MQMessage();
/*  349 */         this.adminQueue.get(msg, gmo);
/*      */         
/*  351 */         ae = new MQSubEntry(msg);
/*  352 */         retVec.addElement(ae);
/*      */         
/*  354 */         gmo.options = 32;
/*      */       
/*      */       }
/*  357 */       catch (MQException e) {
/*  358 */         if (Trace.isOn) {
/*  359 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getAll()", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  366 */         carryOn = false;
/*      */       } 
/*      */     } 
/*      */     
/*  370 */     if (Trace.isOn) {
/*  371 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getAll()", "getter", retVec);
/*      */     }
/*      */     
/*  374 */     return retVec;
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
/*      */   boolean contains(String key) throws JMSException {
/*  386 */     if (Trace.isOn) {
/*  387 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "contains(String)", new Object[] { key });
/*      */     }
/*      */     
/*  390 */     boolean traceRet1 = (get(key) != null);
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "contains(String)", 
/*  393 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  395 */     return traceRet1;
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
/*      */   MQSubEntry get(String key) throws JMSException {
/*  407 */     if (Trace.isOn) {
/*  408 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "get(String)", new Object[] { key });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  413 */     MQSubEntry traceRet1 = get(key, null, null, false);
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "get(String)", traceRet1);
/*      */     }
/*      */     
/*  418 */     return traceRet1;
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
/*      */   MQSubEntry get(String key, byte[] subscriberId, byte[] jvmReferenceId, boolean getNext) throws JMSException {
/*  438 */     if (Trace.isOn) {
/*  439 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "get(String,byte [ ],byte [ ],boolean)", new Object[] { key, subscriberId, jvmReferenceId, 
/*      */             
/*  441 */             Boolean.valueOf(getNext) });
/*      */     }
/*      */     
/*  444 */     MQSubEntry ae = null;
/*  445 */     MQSubEntry ret = null;
/*      */     
/*  447 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */     
/*  451 */     if (getNext) {
/*  452 */       gmo.options = 32;
/*      */     } else {
/*  454 */       gmo.options = 16;
/*      */     } 
/*      */     
/*  457 */     boolean carryOn = true;
/*  458 */     while (carryOn) {
/*      */       
/*      */       try {
/*  461 */         MQMessage msg = new MQMessage();
/*      */ 
/*      */         
/*  464 */         if (subscriberId != null) {
/*  465 */           msg.messageId = subscriberId;
/*      */         }
/*      */         
/*  468 */         if (jvmReferenceId != null) {
/*  469 */           msg.correlationId = jvmReferenceId;
/*      */         }
/*      */ 
/*      */         
/*  473 */         this.adminQueue.get(msg, gmo);
/*      */         
/*  475 */         ae = new MQSubEntry(msg);
/*      */         
/*  477 */         if (key == null && ae.isValid()) {
/*      */           
/*  479 */           ret = ae;
/*  480 */           carryOn = false; continue;
/*  481 */         }  if (key != null && ae.isValid() && ae.getName().equals(key)) {
/*      */           
/*  483 */           ret = ae;
/*  484 */           carryOn = false; continue;
/*      */         } 
/*  486 */         gmo.options = 32;
/*      */ 
/*      */       
/*      */       }
/*  490 */       catch (MQException e) {
/*  491 */         if (Trace.isOn) {
/*  492 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "get(String,byte [ ],byte [ ],boolean)", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  499 */         carryOn = false;
/*      */       } 
/*      */     } 
/*      */     
/*  503 */     if (Trace.isOn) {
/*  504 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "get(String,byte [ ],byte [ ],boolean)", ret);
/*      */     }
/*      */     
/*  507 */     return ret;
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
/*      */   MQSubEntry getResolved(MQQueueManager qm, BrokerConnectionInfo brk, String key, byte[] jvmRefId, boolean getNext) throws JMSException {
/*  536 */     if (Trace.isOn) {
/*  537 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getResolved(MQQueueManager,BrokerConnectionInfo,String,byte [ ],boolean)", new Object[] { qm, brk, key, jvmRefId, 
/*      */             
/*  539 */             Boolean.valueOf(getNext) });
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
/*  553 */     MQSubEntry subEntry1 = null;
/*  554 */     MQSubEntry subEntry2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  563 */     subEntry1 = get(key, null, jvmRefId, getNext);
/*      */ 
/*      */ 
/*      */     
/*  567 */     if (subEntry1 != null && subEntry1.getVersion() > 1) {
/*  568 */       if (Trace.isOn) {
/*  569 */         Trace.traceData(this, "Matching subscriber found. Checking for a second record.", null);
/*      */       }
/*  571 */       subEntry2 = get(null, subEntry1.getSubscriberId(), null, true);
/*      */     } 
/*      */     
/*  574 */     if (subEntry2 != null) {
/*      */ 
/*      */ 
/*      */       
/*  578 */       if (Trace.isOn) {
/*  579 */         Trace.traceData(this, "Second record found: earlier problem must have occurred.", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  585 */       if (get(null, subEntry1.getSubscriberId(), null, false) == null) {
/*      */         
/*  587 */         if (Trace.isOn) {
/*  588 */           Trace.traceData(this, "getResolved ERROR: couldn't reset browse cursor position", null);
/*      */         }
/*      */         
/*  591 */         JMSException je = ConfigEnvironment.newException("MQJMS3013");
/*  592 */         if (Trace.isOn) {
/*  593 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getResolved(MQQueueManager,BrokerConnectionInfo,String,byte [ ],boolean)", (Throwable)je);
/*      */         }
/*      */         
/*  596 */         throw je;
/*      */       } 
/*      */       
/*  599 */       if (subEntry1.getSubscriberState() == 't' || subEntry2.getSubscriberState() == 't')
/*      */       {
/*  601 */         if (Trace.isOn) {
/*  602 */           Trace.traceData(this, "Trying to resolve problem detected with earlier create or unsubscribe.", null);
/*      */         }
/*      */         
/*  605 */         SubscriptionHelper.deleteSubscriber(qm, brk, subEntry1);
/*      */ 
/*      */         
/*  608 */         remove(subEntry1);
/*  609 */         remove(subEntry2);
/*      */         
/*  611 */         if (jvmRefId != null) {
/*      */ 
/*      */           
/*  614 */           subEntry1.setSubscriberState('i');
/*      */         } else {
/*  616 */           subEntry1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  632 */         if (Trace.isOn) {
/*  633 */           Trace.traceData(this, "Trying to resolve problem detected with earlier re-create or close.", null);
/*      */         }
/*      */ 
/*      */         
/*  637 */         MQSubEntry newEntry = subEntry1;
/*      */         
/*  639 */         subEntry1.setSubscriberState('i');
/*      */         
/*  641 */         subEntry1.setStatusMgrId(MQC.MQCI_NONE);
/*      */ 
/*      */         
/*  644 */         remove(subEntry1);
/*      */ 
/*      */         
/*  647 */         add(newEntry, false);
/*      */ 
/*      */         
/*  650 */         remove(subEntry2);
/*      */ 
/*      */         
/*  653 */         subEntry1 = newEntry;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  658 */     else if (subEntry1 != null && subEntry1.getSubscriberState() == 't') {
/*      */       
/*  660 */       if (Trace.isOn) {
/*  661 */         Trace.traceData(this, "Trying to resolve problem detected with earlier create or unsubscribe.", null);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  666 */         SubscriptionHelper.deleteSubscriber(qm, brk, subEntry1);
/*      */       }
/*  668 */       catch (JMSException je) {
/*  669 */         if (Trace.isOn) {
/*  670 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getResolved(MQQueueManager,BrokerConnectionInfo,String,byte [ ],boolean)", (Throwable)je);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  675 */         if (Trace.isOn) {
/*  676 */           Trace.traceData(this, "cleanup ERROR: deleteSubscriber encountered error ", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  681 */       remove(subEntry1);
/*      */       
/*  683 */       if (jvmRefId != null) {
/*      */ 
/*      */         
/*  686 */         subEntry1.setSubscriberState('i');
/*      */       } else {
/*  688 */         subEntry1 = null;
/*      */       } 
/*      */     } 
/*      */     
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "getResolved(MQQueueManager,BrokerConnectionInfo,String,byte [ ],boolean)", subEntry1);
/*      */     }
/*      */     
/*  696 */     return subEntry1;
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
/*      */   public MQSubEntry remove(MQSubEntry subEntry) throws JMSException {
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "remove(MQSubEntry)", new Object[] { subEntry });
/*      */     }
/*      */ 
/*      */     
/*  715 */     MQSubEntry ae = null;
/*  716 */     MQSubEntry ret = null;
/*      */     
/*  718 */     byte[] subscriberId = subEntry.getSubscriberId();
/*  719 */     String key = subEntry.getName();
/*      */     
/*  721 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */     
/*  725 */     if (subscriberId == null) {
/*  726 */       gmo.options = 16;
/*      */     }
/*      */     
/*  729 */     boolean carryOn = true;
/*  730 */     while (carryOn) {
/*      */       try {
/*  732 */         MQMessage msg = new MQMessage();
/*      */ 
/*      */         
/*  735 */         if (subscriberId != null) {
/*  736 */           msg.messageId = subscriberId;
/*      */         }
/*      */ 
/*      */         
/*  740 */         this.adminQueue.get(msg, gmo);
/*      */         
/*  742 */         ae = new MQSubEntry(msg);
/*      */ 
/*      */         
/*  745 */         if (subscriberId != null) {
/*  746 */           ret = ae;
/*  747 */           carryOn = false;
/*      */           continue;
/*      */         } 
/*  750 */         if (ae.isValid() && ae.getName().equals(key)) {
/*      */           
/*  752 */           ret = ae;
/*  753 */           gmo.options = 256;
/*  754 */           MQMessage delmsg = new MQMessage();
/*  755 */           this.adminQueue.get(delmsg, gmo);
/*  756 */           carryOn = false;
/*      */           
/*      */           continue;
/*      */         } 
/*  760 */         gmo.options = 32;
/*      */ 
/*      */       
/*      */       }
/*  764 */       catch (MQException e) {
/*  765 */         if (Trace.isOn) {
/*  766 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "remove(MQSubEntry)", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  774 */         JMSException je = ConfigEnvironment.newException("MQJMS3013");
/*      */         
/*  776 */         je.setLinkedException((Exception)e);
/*      */         
/*  778 */         if (Trace.isOn) {
/*  779 */           Trace.traceData(this, "cannot remove msg!", null);
/*      */         }
/*  781 */         if (Trace.isOn) {
/*  782 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "remove(MQSubEntry)", (Throwable)je);
/*      */         }
/*      */         
/*  785 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  791 */     if (Trace.isOn) {
/*  792 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "remove(MQSubEntry)", ret);
/*      */     }
/*      */     
/*  795 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeByQueueName(String qName) {
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeByQueueName(String)", new Object[] { qName });
/*      */     }
/*      */     
/*  810 */     boolean ret = false;
/*  811 */     MQSubEntry ae = null;
/*  812 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */     
/*  814 */     gmo.options = 16;
/*      */     
/*  816 */     boolean carryOn = true;
/*  817 */     while (carryOn) {
/*      */       try {
/*  819 */         MQMessage msg = new MQMessage();
/*  820 */         this.adminQueue.get(msg, gmo);
/*      */         
/*  822 */         ae = new MQSubEntry(msg);
/*      */         
/*  824 */         if (ae.isValid() && ae.getQName().trim().equals(qName)) {
/*  825 */           gmo.options = 256;
/*  826 */           MQMessage delmsg = new MQMessage();
/*  827 */           this.adminQueue.get(delmsg, gmo);
/*  828 */           carryOn = false;
/*  829 */           ret = true; continue;
/*      */         } 
/*  831 */         gmo.options = 32;
/*      */       
/*      */       }
/*  834 */       catch (MQException e) {
/*  835 */         if (Trace.isOn) {
/*  836 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeByQueueName(String)", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  843 */         carryOn = false;
/*  844 */         ret = false;
/*      */       } 
/*      */     } 
/*      */     
/*  848 */     if (Trace.isOn) {
/*  849 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeByQueueName(String)", 
/*  850 */           Boolean.valueOf(ret));
/*      */     }
/*  852 */     return ret;
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
/*      */   public byte[] add(MQSubEntry adminEntry, boolean checkExists) throws JMSException {
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry,boolean)", new Object[] { adminEntry, 
/*  868 */             Boolean.valueOf(checkExists) });
/*      */     }
/*  870 */     byte[] subscriberId = null;
/*      */     
/*  872 */     if (checkExists && contains(adminEntry.getName())) {
/*  873 */       if (Trace.isOn) {
/*  874 */         Trace.traceData(this, "entry already exists!", null);
/*      */       }
/*      */       
/*  877 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/*      */       
/*  879 */       if (Trace.isOn) {
/*  880 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry,boolean)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  883 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  887 */     MQMessage m = adminEntry.toMessage();
/*      */     
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.traceData(this, "MQPUT with messageId: ", m.messageId);
/*      */     }
/*      */     
/*      */     try {
/*  894 */       this.adminQueue.put(m, new MQPutMessageOptions());
/*  895 */       subscriberId = m.messageId;
/*      */     
/*      */     }
/*  898 */     catch (MQException e) {
/*  899 */       if (Trace.isOn) {
/*  900 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry,boolean)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/*  904 */       if (Trace.isOn) {
/*  905 */         Trace.traceData(this, "unable to MQPUT new entry", null);
/*      */       }
/*      */       
/*  908 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/*  909 */       je.setLinkedException((Exception)e);
/*  910 */       if (Trace.isOn) {
/*  911 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry,boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  914 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry,boolean)", subscriberId);
/*      */     }
/*      */     
/*  922 */     return subscriberId;
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
/*      */   public byte[] add(MQSubEntry adminEntry) throws JMSException {
/*  935 */     if (Trace.isOn) {
/*  936 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry)", new Object[] { adminEntry });
/*      */     }
/*      */ 
/*      */     
/*  940 */     byte[] traceRet1 = add(adminEntry, true);
/*  941 */     if (Trace.isOn) {
/*  942 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "add(MQSubEntry)", traceRet1);
/*      */     }
/*      */     
/*  945 */     return traceRet1;
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
/*      */   public MQQueue checkInactive(MQQueueManager qm, MQSubEntry adminEntry) throws JMSException {
/*  963 */     if (Trace.isOn) {
/*  964 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", new Object[] { qm, adminEntry });
/*      */     }
/*      */ 
/*      */     
/*  968 */     MQQueue subQ = null;
/*      */ 
/*      */     
/*  971 */     boolean subInactive = false;
/*      */ 
/*      */     
/*  974 */     if (adminEntry.getSubscriberState() == 'i') {
/*  975 */       subInactive = true;
/*  976 */     } else if (adminEntry.getSubscriberState() != 'u') {
/*      */ 
/*      */       
/*  979 */       if (Trace.isOn) {
/*  980 */         Trace.traceData(this, "checkInactive: state is neither unknown or inactive!", null);
/*      */       }
/*  982 */       JMSException je = ConfigEnvironment.newException("MQJMS3005");
/*  983 */       if (Trace.isOn) {
/*  984 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  987 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  994 */       if (adminEntry.getSharedQueue() && !subInactive) {
/*      */ 
/*      */         
/*  997 */         int openOptions = 8202;
/*      */         
/*  999 */         MQQueue statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", openOptions);
/*      */ 
/*      */         
/* 1002 */         MQMessage jvmMessage = new MQMessage();
/* 1003 */         jvmMessage.messageId = adminEntry.getStatusMgrId();
/*      */ 
/*      */         
/* 1006 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1007 */         gmo.options = 16;
/*      */         try {
/* 1009 */           if (Trace.isOn) {
/* 1010 */             Trace.traceData(this, "attempting MQGET for jvm reference msg.", null);
/*      */           }
/* 1012 */           statusQueue.get(jvmMessage, gmo);
/*      */         }
/* 1014 */         catch (MQException e) {
/* 1015 */           if (Trace.isOn) {
/* 1016 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", (Throwable)e, 1);
/*      */           }
/*      */           
/* 1019 */           if (e.reasonCode == 2033) {
/* 1020 */             if (Trace.isOn) {
/* 1021 */               Trace.traceData(this, "shared subscriber assumed active.", null);
/*      */             }
/* 1023 */             JMSException je = ConfigEnvironment.newException("MQJMS3023");
/* 1024 */             je.setLinkedException((Exception)e);
/* 1025 */             if (Trace.isOn) {
/* 1026 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", (Throwable)je, 2);
/*      */             }
/*      */             
/* 1029 */             throw je;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1034 */           if (Trace.isOn) {
/* 1035 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", (Throwable)e, 3);
/*      */           }
/*      */           
/* 1038 */           throw e;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1043 */         statusQueue.close();
/*      */       } 
/*      */ 
/*      */       
/* 1047 */       if (Trace.isOn) {
/* 1048 */         Trace.traceData(this, "opening subscription queue", null);
/*      */       }
/*      */ 
/*      */       
/* 1052 */       if (!adminEntry.getSharedQueue())
/*      */       {
/*      */         
/* 1055 */         subQ = qm.accessQueue(adminEntry.getQName(), 8228);
/*      */       }
/*      */       else
/*      */       {
/* 1059 */         subQ = qm.accessQueue(adminEntry.getQName(), 8193);
/*      */       }
/*      */     
/*      */     }
/* 1063 */     catch (MQException e) {
/* 1064 */       JMSException je; if (Trace.isOn) {
/* 1065 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1070 */       if (e.reasonCode == 2042) {
/*      */         
/* 1072 */         je = ConfigEnvironment.newException("MQJMS3023");
/*      */       } else {
/* 1074 */         je = ConfigEnvironment.newException("MQJMS3005");
/*      */       } 
/*      */       
/* 1077 */       je.setLinkedException((Exception)e);
/*      */       
/* 1079 */       if (Trace.isOn) {
/* 1080 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1083 */       throw je;
/*      */     } 
/* 1085 */     if (Trace.isOn) {
/* 1086 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "checkInactive(MQQueueManager,MQSubEntry)", subQ);
/*      */     }
/*      */     
/* 1089 */     return subQ;
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
/*      */   public static byte[] addND(MQQueueManager qm, MQSubEntry adminEntry) throws JMSException {
/* 1104 */     if (Trace.isOn) {
/* 1105 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "addND(MQQueueManager,MQSubEntry)", new Object[] { qm, adminEntry });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1111 */     MQMessage ndEntry = adminEntry.toMessage();
/*      */ 
/*      */     
/*      */     try {
/* 1115 */       MQQueue statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", 8208);
/*      */       
/* 1117 */       statusQueue.put(ndEntry);
/*      */ 
/*      */       
/* 1120 */       statusQueue.close();
/*      */     
/*      */     }
/* 1123 */     catch (MQException e) {
/* 1124 */       if (Trace.isOn) {
/* 1125 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "addND(MQQueueManager,MQSubEntry)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 1129 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1130 */       je.setLinkedException((Exception)e);
/*      */       
/* 1132 */       if (Trace.isOn) {
/* 1133 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "addND(MQQueueManager,MQSubEntry)", (Throwable)je);
/*      */       }
/*      */       
/* 1136 */       throw je;
/*      */     } 
/*      */     
/* 1139 */     if (Trace.isOn) {
/* 1140 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "addND(MQQueueManager,MQSubEntry)", ndEntry.messageId);
/*      */     }
/*      */     
/* 1143 */     return ndEntry.messageId;
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
/*      */   public static void removeND(MQQueueManager qm, byte[] subscriberId) throws JMSException {
/* 1156 */     if (Trace.isOn) {
/* 1157 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeND(MQQueueManager,byte [ ])", new Object[] { qm, subscriberId });
/*      */     }
/*      */     
/* 1160 */     MQQueue statusQueue = null;
/*      */ 
/*      */     
/* 1163 */     MQMessage ndEntry = new MQMessage();
/* 1164 */     ndEntry.messageId = subscriberId;
/*      */ 
/*      */     
/*      */     try {
/* 1168 */       statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", 8194);
/*      */ 
/*      */       
/* 1171 */       statusQueue.get(ndEntry);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1176 */       statusQueue.close();
/*      */       
/* 1178 */       statusQueue = null;
/*      */     
/*      */     }
/* 1181 */     catch (MQException e) {
/* 1182 */       if (Trace.isOn) {
/* 1183 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeND(MQQueueManager,byte [ ])", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1188 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1189 */       je.setLinkedException((Exception)e);
/*      */       
/* 1191 */       if (Trace.isOn) {
/* 1192 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeND(MQQueueManager,byte [ ])", (Throwable)je);
/*      */       }
/*      */       
/* 1195 */       throw je;
/*      */     }
/*      */     finally {
/*      */       
/* 1199 */       if (Trace.isOn) {
/* 1200 */         Trace.finallyBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeND(MQQueueManager,byte [ ])");
/*      */       }
/*      */       
/* 1203 */       if (statusQueue != null) {
/*      */         
/*      */         try {
/* 1206 */           statusQueue.close();
/*      */         }
/* 1208 */         catch (Exception e) {
/* 1209 */           if (Trace.isOn) {
/* 1210 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeND(MQQueueManager,byte [ ])", e, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1215 */           if (Trace.isOn) {
/* 1216 */             Trace.traceData("MQSubAdmin", "closing statusQueue in finally clause generated exception", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1223 */     if (Trace.isOn)
/* 1224 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQSubAdmin", "removeND(MQQueueManager,byte [ ])"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQSubAdmin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */