/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.TimeZone;
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
/*      */ public class MQQueueEnumeration
/*      */   implements Enumeration
/*      */ {
/*      */   private static final String sccsid = "@(#) com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/v6/jms/internal/MQQueueEnumeration.java, jmscc.wmq.compat, k710 1.27.1.4 12/02/02 10:44:26";
/*      */   private MQSession session;
/*      */   private MQQueueBrowser browser;
/*      */   private MQQueue queue;
/*      */   private JMSMessage message;
/*      */   
/*      */   static {
/*   64 */     if (Trace.isOn) {
/*   65 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueEnumeration.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean bufferFilled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQJMSMessage baseMessage;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQGetMessageOptions gmo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQMessageSelector messageSelector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String PROBE_01 = "01";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String PROBE_02 = "02";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WMQDestination destination;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  115 */   private String backoutRetryQueue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  121 */   private int backoutThreshold = 20;
/*      */ 
/*      */   
/*  124 */   private int type = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   private String aliasBaseQueueName = null;
/*      */ 
/*      */   
/*  132 */   private String mqDLQName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQQueueEnumeration(MQSession session, MQQueue queue, MQMessageSelector messageSelector, MQQueueBrowser callingBrowser, WMQDestination destination, MQJMSMessage bMsg, MQGetMessageOptions gmOpts) throws JMSException {
/*  156 */     if (Trace.isOn) {
/*  157 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "<init>(MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQMessageSelector,MQQueueBrowser,WMQDestination,MQJMSMessage,MQGetMessageOptions)", new Object[] { session, queue, messageSelector, callingBrowser, destination, bMsg, gmOpts });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  162 */     this.session = session;
/*  163 */     this.queue = queue;
/*  164 */     this.messageSelector = messageSelector;
/*  165 */     this.browser = callingBrowser;
/*  166 */     this.destination = destination;
/*      */ 
/*      */     
/*  169 */     this.gmo = new MQGetMessageOptions();
/*  170 */     this.gmo.options |= 0x10;
/*      */ 
/*      */     
/*  173 */     if (session.connection.getFailIfQuiesce() == 1) {
/*  174 */       this.gmo.options |= 0x2000;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  183 */     if (bMsg != null && gmOpts != null) {
/*  184 */       if (Trace.isOn) {
/*  185 */         Trace.traceData(this, "Using provided message at gmo", null);
/*      */       }
/*  187 */       this.baseMessage = bMsg;
/*      */ 
/*      */ 
/*      */       
/*  191 */       if ((gmOpts.matchOptions & 0x1) == 1) {
/*  192 */         if (Trace.isOn) {
/*  193 */           Trace.traceData(this, "Matching on message id", null);
/*      */         }
/*  195 */         this.gmo.matchOptions |= 0x1;
/*      */       } 
/*  197 */       if ((gmOpts.matchOptions & 0x2) == 2) {
/*  198 */         if (Trace.isOn) {
/*  199 */           Trace.traceData(this, "Matching on correlation id", null);
/*      */         }
/*  201 */         this.gmo.matchOptions |= 0x2;
/*      */       } 
/*      */     } else {
/*      */       
/*  205 */       if (Trace.isOn) {
/*  206 */         Trace.traceData(this, "baseMessage or gmo not set, using blank message", null);
/*      */       }
/*  208 */       this.baseMessage = new MQJMSMessage();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  215 */       this.type = Utils.inquireInt((MQManagedObject)this.queue, 20);
/*  216 */       if (this.type == 3) {
/*  217 */         obtainAliasQueueDetails();
/*      */       } else {
/*      */         
/*  220 */         int[] selectors = { 22, 20, 2019 };
/*  221 */         int[] intAttrs = new int[2];
/*  222 */         byte[] charAttr = new byte[48];
/*      */         
/*  224 */         queue.inquire(selectors, intAttrs, charAttr);
/*  225 */         JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), session.getQM().getHconn().getCcsid());
/*  226 */         String inqString = codepage.bytesToString(charAttr);
/*  227 */         String borq = inqString.substring(0, 48).trim();
/*      */         
/*  229 */         int thresh = intAttrs[0];
/*      */ 
/*      */ 
/*      */         
/*  233 */         this.backoutThreshold = thresh;
/*      */         
/*  235 */         if (Trace.isOn) {
/*  236 */           Trace.traceData(this, "returned from inquire, threshold = " + thresh + ", borq = '" + borq + "'", null);
/*      */         }
/*      */         
/*  239 */         if (borq.length() != 0) {
/*  240 */           this.backoutRetryQueue = borq;
/*      */         }
/*      */       } 
/*  243 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*  244 */         if (Trace.isOn) {
/*  245 */           Trace.traceData(this, "backoutRetryQueue is not valid, setting to DLQName", this.backoutRetryQueue);
/*      */         }
/*  247 */         this.backoutRetryQueue = session.getDLQName();
/*      */       }
/*      */     
/*  250 */     } catch (Exception e) {
/*  251 */       if (Trace.isOn) {
/*  252 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "<init>(MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQMessageSelector,MQQueueBrowser,WMQDestination,MQJMSMessage,MQGetMessageOptions)", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  260 */     if (Trace.isOn) {
/*  261 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "<init>(MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQMessageSelector,MQQueueBrowser,WMQDestination,MQJMSMessage,MQGetMessageOptions)");
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
/*      */   public boolean hasMoreElements() {
/*      */     boolean result;
/*  288 */     if (Trace.isOn) {
/*  289 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "hasMoreElements()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  295 */     if (this.bufferFilled) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  300 */       if (Trace.isOn) {
/*  301 */         Trace.traceData(this, "hasMoreElements found previously cached message", null);
/*      */       }
/*  303 */       result = true;
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  308 */         this.message = null;
/*  309 */         this.message = retrieveMessage();
/*      */       }
/*  311 */       catch (JMSException je) {
/*  312 */         if (Trace.isOn) {
/*  313 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "hasMoreElements()", (Throwable)je);
/*      */         }
/*      */       } 
/*      */       
/*  317 */       if (this.message == null) {
/*  318 */         result = false;
/*      */       } else {
/*      */         
/*  321 */         this.bufferFilled = true;
/*  322 */         result = true;
/*      */         
/*  324 */         if (Trace.isOn) {
/*  325 */           Trace.traceData(this, "hasMoreElements put message in cache", null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  331 */     if (Trace.isOn) {
/*  332 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "hasMoreElements()", 
/*  333 */           Boolean.valueOf(result));
/*      */     }
/*  335 */     return result;
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
/*      */   public Object nextElement() throws NoSuchElementException {
/*  351 */     if (Trace.isOn) {
/*  352 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()");
/*      */     }
/*      */     
/*      */     try {
/*  356 */       Object result = null;
/*      */       
/*  358 */       if (this.bufferFilled) {
/*      */         
/*  360 */         result = this.message;
/*  361 */         this.bufferFilled = false;
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  370 */           result = retrieveMessage();
/*      */         }
/*  372 */         catch (JMSException je) {
/*  373 */           if (Trace.isOn) {
/*  374 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/*  378 */           if (Trace.isOn) {
/*  379 */             Trace.traceData(this, "JMSException thrown by retrieveMessage()", null);
/*      */           }
/*      */         }
/*  382 */         catch (Exception e) {
/*  383 */           if (Trace.isOn) {
/*  384 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()", e, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  389 */           HashMap<Object, Object> ffstData = new HashMap<>();
/*  390 */           ffstData.put("Exception", e);
/*  391 */           ffstData.put("Message", "MQJMS1016");
/*  392 */           Trace.ffst(this, "nextElement()", "01", ffstData, null);
/*      */           
/*  394 */           if (Trace.isOn) {
/*  395 */             Trace.traceData(this, "Unexpected Exception thrown by retrieveMessage()", null);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  401 */       if (result == null) {
/*  402 */         NoSuchElementException traceRet1 = new NoSuchElementException();
/*  403 */         if (Trace.isOn) {
/*  404 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()", traceRet1, 1);
/*      */         }
/*      */         
/*  407 */         throw traceRet1;
/*      */       } 
/*      */       
/*  410 */       if (Trace.isOn) {
/*  411 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()", result);
/*      */       }
/*      */       
/*  414 */       return result;
/*      */     }
/*  416 */     catch (NoSuchElementException nsee) {
/*  417 */       if (Trace.isOn) {
/*  418 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()", nsee, 3);
/*      */       }
/*      */       
/*  421 */       if (Trace.isOn) {
/*  422 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "nextElement()", nsee, 2);
/*      */       }
/*      */       
/*  425 */       throw nsee;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQQueue getQueue() {
/*  436 */     if (Trace.isOn) {
/*  437 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "getQueue()", "getter", this.queue);
/*      */     }
/*      */     
/*  440 */     return this.queue;
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
/*      */   private JMSMessage retrieveMessage() throws JMSException {
/*  461 */     if (Trace.isOn) {
/*  462 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  467 */       JMSMessage message = null;
/*      */ 
/*      */       
/*  470 */       if (this.queue == null) {
/*  471 */         if (Trace.isOn) {
/*  472 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", null, 1);
/*      */         }
/*      */         
/*  475 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  483 */       while (message == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  494 */         MQJMSMessage tempBaseMessage = new MQJMSMessage();
/*  495 */         tempBaseMessage.setMessageId(this.baseMessage.getMessageId());
/*  496 */         tempBaseMessage.setCorrelationId(this.baseMessage.getCorrelationId());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  502 */           this.queue.getMsg2(tempBaseMessage, this.gmo);
/*      */           
/*  504 */           message = tempBaseMessage.createJMSMessage(this.session, (ProviderDestination)this.destination);
/*      */ 
/*      */           
/*  507 */           if (this.backoutThreshold != 0 && tempBaseMessage.getBackoutCount() >= this.backoutThreshold)
/*      */           {
/*      */             
/*  510 */             boolean cleanSession = !this.session.getCommitRequired();
/*      */ 
/*      */             
/*  513 */             if (cleanSession && !(this.session instanceof MQXASession)) {
/*  514 */               MQQueue getQueue = null;
/*  515 */               MQJMSMessage gotMsg = null;
/*      */               
/*  517 */               int openOpts = 8322;
/*      */               try {
/*  519 */                 getQueue = this.session.getQM().accessQueue(this.queue.name, openOpts);
/*      */                 
/*  521 */                 gotMsg = new MQJMSMessage();
/*      */                 
/*  523 */                 gotMsg.setMessageId(tempBaseMessage.getMessageId());
/*  524 */                 MQGetMessageOptions newGmo = new MQGetMessageOptions();
/*  525 */                 newGmo.options = 8194;
/*  526 */                 newGmo.matchOptions = 1;
/*  527 */                 getQueue.getMsg2(gotMsg, newGmo);
/*      */ 
/*      */                 
/*  530 */                 if (gotMsg.getBackoutCount() < this.backoutThreshold) {
/*  531 */                   if (Trace.isOn) {
/*  532 */                     Trace.data(this, "Destructively got message has a backout count below the threshold, aborting requeue", null);
/*      */                   }
/*      */ 
/*      */                   
/*  536 */                   this.session.getQM().backout();
/*  537 */                   message = null;
/*      */                   
/*      */                   continue;
/*      */                 } 
/*  541 */               } catch (MQException mqe) {
/*  542 */                 if (Trace.isOn) {
/*  543 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)mqe, 1);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  548 */                 this.session.getQM().backout();
/*  549 */                 message = null;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               try {
/*  554 */                 backoutRequeue(gotMsg, getQueue);
/*      */               }
/*  556 */               catch (JMSException je) {
/*  557 */                 int reason; if (Trace.isOn) {
/*  558 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je, 2);
/*      */                 }
/*      */ 
/*      */                 
/*  562 */                 Exception e = je.getLinkedException();
/*      */                 
/*  564 */                 if (e != null && e instanceof MQException) {
/*  565 */                   reason = ((MQException)e).reasonCode;
/*      */                 } else {
/*      */                   
/*  568 */                   reason = 2362;
/*      */                 } 
/*      */                 
/*      */                 try {
/*  572 */                   removeBadMessage(gotMsg, reason, getQueue);
/*      */                 }
/*  574 */                 catch (JMSException je2) {
/*  575 */                   if (Trace.isOn) {
/*  576 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je2, 3);
/*      */                   }
/*      */ 
/*      */                   
/*  580 */                   if (Trace.isOn) {
/*  581 */                     Trace.traceData(this, "removeBadMessage failed", null);
/*      */                   }
/*  583 */                   if (Trace.isOn) {
/*  584 */                     Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je2, 1);
/*      */                   }
/*      */ 
/*      */                   
/*  588 */                   throw je2;
/*      */                 } 
/*      */               } 
/*      */               
/*  592 */               this.session.getQM().commit();
/*      */               
/*  594 */               message = null;
/*      */             }
/*      */             else {
/*      */               
/*  598 */               if (Trace.isOn) {
/*  599 */                 Trace.data(this, "Skipping backout requeue processing as session transaction is unclean or an XA session", this.session);
/*      */               }
/*  601 */               message = null;
/*      */             }
/*      */           
/*      */           }
/*      */         
/*  606 */         } catch (MQException e) {
/*  607 */           if (Trace.isOn) {
/*  608 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)e, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  613 */           if (e.reasonCode == 2033) {
/*      */             
/*      */             try {
/*  616 */               this.queue.close();
/*      */               
/*  618 */               this.browser.removeEnumeration(this);
/*      */             
/*      */             }
/*  621 */             catch (MQException e2) {
/*  622 */               if (Trace.isOn) {
/*  623 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)e2, 5);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  629 */               HashMap<Object, Object> ffstData = new HashMap<>();
/*  630 */               ffstData.put("Exception", e2);
/*  631 */               ffstData.put("Message", "MQJMS2000");
/*  632 */               Trace.ffst(this, "retrieveMessage()", "02", ffstData, JMSException.class);
/*      */             }
/*      */             finally {
/*      */               
/*  636 */               if (Trace.isOn) {
/*  637 */                 Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()");
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  642 */               this.queue = null;
/*      */             } 
/*      */ 
/*      */             
/*  646 */             if (Trace.isOn) {
/*  647 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", null, 2);
/*      */             }
/*      */             
/*  650 */             return null;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  655 */           JMSException je = ConfigEnvironment.newException("MQJMS1025");
/*  656 */           je.setLinkedException((Exception)e);
/*      */ 
/*      */           
/*  659 */           MQConnection mqconnection = this.session.connection;
/*  660 */           mqconnection.deliverException(je);
/*      */           
/*  662 */           if (Trace.isOn) {
/*  663 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je, 2);
/*      */           }
/*      */           
/*  666 */           throw je;
/*      */         }
/*  668 */         catch (IOException e) {
/*  669 */           if (Trace.isOn) {
/*  670 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", e, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  676 */           JMSException je = ConfigEnvironment.newException("MQJMS1000");
/*  677 */           je.setLinkedException(e);
/*  678 */           if (Trace.isOn) {
/*  679 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je, 3);
/*      */           }
/*      */           
/*  682 */           throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  687 */         if (this.messageSelector != null && 
/*  688 */           !this.messageSelector.isSelected(message, tempBaseMessage)) {
/*  689 */           if (Trace.isOn) {
/*  690 */             Trace.traceData(this, "retrieveMessage() message rejected", null);
/*      */           }
/*      */           
/*  693 */           message = null;
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
/*      */         
/*  705 */         this.gmo.options &= 0xFFFFFFEF;
/*  706 */         this.gmo.options |= 0x20;
/*      */       } 
/*      */ 
/*      */       
/*  710 */       if (Trace.isOn) {
/*  711 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", message, 3);
/*      */       }
/*      */       
/*  714 */       return message;
/*      */     }
/*  716 */     catch (JMSException je) {
/*  717 */       if (Trace.isOn) {
/*  718 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je, 7);
/*      */       }
/*      */       
/*  721 */       if (Trace.isOn) {
/*  722 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "retrieveMessage()", (Throwable)je, 4);
/*      */       }
/*      */       
/*  725 */       throw je;
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
/*      */   private void backoutRequeue(MQJMSMessage msg, MQQueue gotQ) throws JMSException {
/*  742 */     if (Trace.isOn) {
/*  743 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", new Object[] { msg, gotQ });
/*      */     }
/*      */ 
/*      */     
/*  747 */     MQQueue destQ = null;
/*      */     
/*      */     try {
/*  750 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*      */ 
/*      */         
/*  753 */         if (this.type == 0) {
/*  754 */           this.type = Utils.inquireInt((MQManagedObject)this.queue, 20);
/*      */         }
/*      */         
/*  757 */         if (this.type == 3) {
/*  758 */           obtainAliasQueueDetails();
/*      */         } else {
/*      */           
/*  761 */           this.backoutRetryQueue = Utils.inquireString((MQManagedObject)this.queue, 2019);
/*      */         } 
/*      */       } 
/*      */       
/*  765 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*  766 */         JMSException jmse = ConfigEnvironment.newException("MQJMS1080");
/*  767 */         if (Trace.isOn) {
/*  768 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", (Throwable)jmse, 1);
/*      */         }
/*      */         
/*  771 */         throw jmse;
/*      */       } 
/*      */       
/*  774 */       this.backoutRetryQueue = this.backoutRetryQueue.trim();
/*      */ 
/*      */       
/*  777 */       if (Trace.isOn) {
/*  778 */         Trace.traceData(this, "BORQ = <" + this.backoutRetryQueue + ">", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  784 */         int openOptions = 8720;
/*      */         
/*  786 */         destQ = this.session.getQM().accessQueue(this.backoutRetryQueue, openOptions, "", null, null);
/*      */ 
/*      */ 
/*      */         
/*  790 */         MQPutMessageOptions pmo = new MQPutMessageOptions();
/*  791 */         pmo.options = 514;
/*  792 */         pmo.contextReference = gotQ;
/*      */         
/*  794 */         destQ.putMsg2(msg, pmo);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  799 */       catch (MQException mqe) {
/*  800 */         if (Trace.isOn) {
/*  801 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/*  804 */         if (mqe.reasonCode == 2033) {
/*      */           
/*  806 */           if (Trace.isOn) {
/*  807 */             Trace.traceData(this, "ProviderMessage to requeue has disappeared! Continue as normal", null);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  817 */         else if (Trace.isOn) {
/*  818 */           Trace.traceData(this, "Unable to requeue message", null);
/*      */         } 
/*      */ 
/*      */         
/*  822 */         JMSException jmse = ConfigEnvironment.newException("MQJMS1081");
/*  823 */         jmse.setLinkedException((Exception)mqe);
/*  824 */         if (Trace.isOn) {
/*  825 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", (Throwable)jmse, 2);
/*      */         }
/*      */         
/*  828 */         throw jmse;
/*      */       }
/*      */     
/*      */     }
/*  832 */     catch (JMSException je) {
/*  833 */       if (Trace.isOn) {
/*  834 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  837 */       if (Trace.isOn) {
/*  838 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  841 */       throw je;
/*      */     } finally {
/*      */       
/*  844 */       if (Trace.isOn) {
/*  845 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)");
/*      */       }
/*      */       
/*      */       try {
/*  849 */         if (destQ != null) {
/*  850 */           destQ.close();
/*      */         }
/*      */       }
/*  853 */       catch (MQException mqe) {
/*  854 */         if (Trace.isOn) {
/*  855 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)", (Throwable)mqe, 3);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  861 */     if (Trace.isOn) {
/*  862 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "backoutRequeue(MQJMSMessage,MQQueue)");
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
/*      */   private void deadLetter(MQJMSMessage msg, int reason, MQQueue gotQ) throws JMSException {
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", new Object[] { msg, 
/*  879 */             Integer.valueOf(reason), gotQ });
/*      */     }
/*      */     
/*  882 */     MQMessage reportMessage = null;
/*  883 */     String replyQ = null;
/*  884 */     String replyQMgr = null;
/*  885 */     MQQueue DLQ = null;
/*  886 */     MQQueue RQ = null;
/*      */ 
/*      */     
/*      */     try {
/*  890 */       if (this.mqDLQName == null) {
/*  891 */         this.mqDLQName = Utils.inquireString((MQManagedObject)this.session.getQM(), 2006);
/*      */       }
/*  893 */       if (Trace.isOn) {
/*  894 */         Trace.traceData(this, "DLQ = <" + this.mqDLQName + ">", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  899 */       if (this.mqDLQName == null || this.mqDLQName.length() == 0) {
/*  900 */         if (Trace.isOn) {
/*  901 */           Trace.traceData(this, "Unable to find DLQ name - is one defined?", null);
/*      */         }
/*  903 */         JMSException je = ConfigEnvironment.newException("MQJMS1079");
/*  904 */         if (Trace.isOn) {
/*  905 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  908 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  912 */       if ((msg.getReport() & 0x7000000) != 0) {
/*      */         
/*  914 */         reportMessage = generateReport(msg, reason);
/*  915 */         replyQ = msg.getReplyToQueueName().trim();
/*  916 */         replyQMgr = msg.getReplyToQueueManagerName().trim();
/*      */       } 
/*      */ 
/*      */       
/*  920 */       if (Trace.isOn) {
/*  921 */         Trace.traceData(this, "Constructing DLH", null);
/*      */       }
/*      */       
/*  924 */       DLH dlh = new DLH();
/*  925 */       dlh.reason = reason;
/*  926 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*  927 */         dlh.destQName = this.queue.name;
/*      */       } else {
/*      */         
/*  930 */         dlh.destQName = this.backoutRetryQueue;
/*      */       } 
/*      */       
/*  933 */       dlh.destQMgrName = this.session.getQMName();
/*  934 */       dlh.putApplType = 28;
/*  935 */       dlh.putApplName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */       
/*  938 */       GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/*  939 */       dlh.putDate = Utils.getDate(gc);
/*  940 */       dlh.putTime = Utils.getTime(gc);
/*  941 */       MQMessage deadMsg = new MQMessage();
/*      */       
/*  943 */       dlh.write(deadMsg, msg);
/*      */ 
/*      */ 
/*      */       
/*  947 */       int openOptions = 8720;
/*  948 */       DLQ = this.session.getQM().accessQueue(this.mqDLQName, openOptions, this.session.getQMName(), null, null);
/*      */       
/*  950 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/*  951 */       pmo.options = 514;
/*  952 */       pmo.contextReference = gotQ;
/*  953 */       DLQ.put(deadMsg, pmo);
/*      */       
/*  955 */       if (reportMessage != null) {
/*      */         
/*      */         try {
/*  958 */           openOptions = 8720;
/*  959 */           RQ = this.session.getQM().accessQueue(replyQ, openOptions, replyQMgr, null, null);
/*      */ 
/*      */           
/*  962 */           pmo = new MQPutMessageOptions();
/*  963 */           pmo.options = 514;
/*  964 */           pmo.contextReference = this.queue;
/*  965 */           RQ.put(reportMessage, pmo);
/*      */         
/*      */         }
/*  968 */         catch (MQException mqe) {
/*  969 */           if (Trace.isOn) {
/*  970 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  975 */           dlh.destQName = replyQ;
/*  976 */           dlh.destQMgrName = replyQMgr;
/*  977 */           dlh.reason = mqe.reasonCode;
/*  978 */           dlh.write(reportMessage);
/*      */           
/*  980 */           DLQ.put(reportMessage, pmo);
/*      */         }
/*      */       
/*      */       }
/*  984 */     } catch (MQException mqe) {
/*  985 */       if (Trace.isOn) {
/*  986 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */       
/*  990 */       if (mqe.reasonCode == 2033) {
/*      */         
/*  992 */         if (Trace.isOn) {
/*  993 */           Trace.traceData(this, "ProviderMessage to dead-letter has disappeared! Continue as normal", null);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 1001 */       else if (msg != null && msg.getPersistence() == 0) {
/* 1002 */         if (Trace.isOn) {
/* 1003 */           Trace.traceData(this, "Could not send report message, but message isnon-persistent - so subsume the error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 1010 */           if (Trace.isOn) {
/* 1011 */             Trace.traceData(this, "backing out attempt to send Report message", null);
/*      */           }
/* 1013 */           if (this.session.getTransacted() || this.session
/* 1014 */             .getAcknowledgeMode() == 2)
/*      */           {
/*      */ 
/*      */             
/* 1018 */             if (Trace.isOn) {
/* 1019 */               Trace.traceData(this, "Unable to backout Report message as session is transacted or Client Ack", null);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 1024 */             if (Trace.isOn) {
/* 1025 */               Trace.traceData(this, "backing out Report message", null);
/*      */             }
/* 1027 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 1030 */         } catch (MQException mqe2) {
/* 1031 */           if (Trace.isOn) {
/* 1032 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)mqe2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1038 */         JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1039 */         je.setLinkedException((Exception)mqe);
/*      */         
/* 1041 */         if (Trace.isOn) {
/* 1042 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1045 */         throw je;
/*      */       }
/*      */     
/* 1048 */     } catch (JMSException je) {
/* 1049 */       if (Trace.isOn) {
/* 1050 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1054 */       if (msg != null && msg.getPersistence() == 0) {
/* 1055 */         if (Trace.isOn) {
/* 1056 */           Trace.traceData(this, "ProviderMessage is non-persistent so ignoring error", null);
/*      */         }
/*      */       } else {
/*      */         
/*      */         try {
/* 1061 */           if (Trace.isOn) {
/* 1062 */             Trace.traceData(this, "backing out attempt to DLQ message", null);
/*      */           }
/* 1064 */           if (this.session.getTransacted() || this.session
/* 1065 */             .getAcknowledgeMode() == 2)
/*      */           {
/*      */ 
/*      */             
/* 1069 */             if (Trace.isOn) {
/* 1070 */               Trace.traceData(this, "Unable to backout DLQ'd message as session is transacted or Client Ack", null);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 1075 */             if (Trace.isOn) {
/* 1076 */               Trace.traceData(this, "backing out DLQ'd message", null);
/*      */             }
/* 1078 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 1081 */         } catch (MQException mqe) {
/* 1082 */           if (Trace.isOn) {
/* 1083 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)mqe, 5);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1088 */         if (Trace.isOn) {
/* 1089 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1092 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1098 */       if (DLQ != null) {
/* 1099 */         DLQ.close();
/*      */       }
/* 1101 */       if (RQ != null) {
/* 1102 */         RQ.close();
/*      */       }
/* 1104 */       if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2)
/*      */       {
/*      */ 
/*      */         
/* 1108 */         if (Trace.isOn) {
/* 1109 */           Trace.traceData(this, "Unable to commit requeued message as session is transacted or Client Ack", null);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1114 */         if (Trace.isOn) {
/* 1115 */           Trace.traceData(this, "commiting requeued message", null);
/*      */         }
/* 1117 */         this.session.getQM().commit();
/*      */       }
/*      */     
/* 1120 */     } catch (MQException mqe) {
/* 1121 */       if (Trace.isOn) {
/* 1122 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)mqe, 6);
/*      */       }
/*      */ 
/*      */       
/* 1126 */       JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1127 */       je.setLinkedException((Exception)mqe);
/* 1128 */       if (Trace.isOn) {
/* 1129 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1132 */       throw je;
/*      */     } 
/* 1134 */     if (Trace.isOn) {
/* 1135 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "deadLetter(MQJMSMessage,int,MQQueue)");
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
/*      */   private MQMessage generateReport(MQJMSMessage msg, int reason) throws JMSException {
/* 1150 */     if (Trace.isOn) {
/* 1151 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", new Object[] { msg, 
/* 1152 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 1155 */     MQMessage out = new MQMessage();
/* 1156 */     ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
/* 1157 */     DataOutputStream dataBuffer = new DataOutputStream(byteBuffer);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1163 */       out.report = 0;
/* 1164 */       out.messageType = 4;
/* 1165 */       out.expiry = -1;
/* 1166 */       out.feedback = reason;
/* 1167 */       out.encoding = msg.getEncoding();
/* 1168 */       out.characterSet = msg.getCharacterSet();
/* 1169 */       out.format = msg.getFormat();
/* 1170 */       out.priority = msg.getPriority();
/* 1171 */       out.persistence = msg.getPersistence();
/*      */       
/* 1173 */       if ((msg.getReport() & 0x80) == 128) {
/* 1174 */         out.messageId = msg.getMessageId();
/*      */       }
/*      */ 
/*      */       
/* 1178 */       if ((msg.getReport() & 0x40) == 64) {
/* 1179 */         out.correlationId = msg.getCorrelationId();
/*      */       }
/*      */       else {
/*      */         
/* 1183 */         out.correlationId = msg.getMessageId();
/*      */       } 
/*      */       
/* 1186 */       out.backoutCount = 0;
/* 1187 */       out.replyToQueueName = "";
/* 1188 */       out.replyToQueueManagerName = this.session.getQMName();
/* 1189 */       out.putApplicationType = 28;
/* 1190 */       out.putApplicationName = "MQ JMS Message Consumer";
/*      */ 
/*      */ 
/*      */       
/* 1194 */       if ((msg.getReport() & 0x7000000) == 117440512) {
/* 1195 */         byte[] buffer = msg.getMessageData();
/* 1196 */         out.write(buffer);
/*      */       }
/* 1198 */       else if ((msg.getReport() & 0x3000000) == 50331648) {
/*      */ 
/*      */ 
/*      */         
/* 1202 */         String format = msg.getFormat();
/* 1203 */         int origCharacterSet = msg.getCharacterSet();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1211 */           if (format.startsWith("MQH")) {
/* 1212 */             msg.writeTo(dataBuffer, origCharacterSet, true);
/*      */           }
/*      */         }
/* 1215 */         catch (IOException ioe) {
/* 1216 */           if (Trace.isOn) {
/* 1217 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", ioe, 1);
/*      */           
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1225 */     catch (IOException ioe) {
/* 1226 */       if (Trace.isOn) {
/* 1227 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", ioe, 2);
/*      */       }
/*      */ 
/*      */       
/* 1231 */       JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 1232 */       jmse.setLinkedException(ioe);
/* 1233 */       if (Trace.isOn) {
/* 1234 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1237 */       throw jmse;
/*      */     }
/* 1239 */     catch (MQException mqe) {
/* 1240 */       if (Trace.isOn) {
/* 1241 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", (Throwable)mqe, 3);
/*      */       }
/*      */ 
/*      */       
/* 1245 */       JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 1246 */       jmse.setLinkedException((Exception)mqe);
/* 1247 */       if (Trace.isOn) {
/* 1248 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", (Throwable)jmse, 2);
/*      */       }
/*      */       
/* 1251 */       throw jmse;
/*      */     } 
/*      */ 
/*      */     
/* 1255 */     if (Trace.isOn) {
/* 1256 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "generateReport(MQJMSMessage,int)", out);
/*      */     }
/*      */     
/* 1259 */     return out;
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
/*      */   private void removeBadMessage(MQJMSMessage msg, int reason, MQQueue gotQ) throws JMSException {
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "removeBadMessage(MQJMSMessage,int,MQQueue)", new Object[] { msg, 
/* 1276 */             Integer.valueOf(reason), gotQ });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1281 */       if ((msg.getReport() & 0x8000000) == 134217728) {
/* 1282 */         if (Trace.isOn) {
/* 1283 */           Trace.traceData(this, "Discarding message as per MQMD.Report", null);
/*      */         }
/* 1285 */         discard(msg, reason);
/*      */       } else {
/*      */         
/* 1288 */         if (Trace.isOn) {
/* 1289 */           Trace.traceData(this, "Dead-lettering message as per MQMD.Report", null);
/*      */         }
/* 1291 */         deadLetter(msg, reason, gotQ);
/*      */       }
/*      */     
/* 1294 */     } catch (JMSException jmse) {
/* 1295 */       if (Trace.isOn) {
/* 1296 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "removeBadMessage(MQJMSMessage,int,MQQueue)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1299 */       if (Trace.isOn) {
/* 1300 */         Trace.traceData(this, "throwing " + jmse, null);
/*      */       }
/* 1302 */       if (Trace.isOn) {
/* 1303 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "removeBadMessage(MQJMSMessage,int,MQQueue)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1306 */       throw jmse;
/*      */     } 
/*      */     
/* 1309 */     if (Trace.isOn) {
/* 1310 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "removeBadMessage(MQJMSMessage,int,MQQueue)");
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
/*      */   private void discard(MQJMSMessage msgRef, int reason) throws JMSException {
/* 1325 */     if (Trace.isOn) {
/* 1326 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", new Object[] { msgRef, 
/* 1327 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 1330 */     MQJMSMessage discardMessage = msgRef;
/* 1331 */     MQMessage reportMessage = null;
/*      */     
/*      */     try {
/* 1334 */       if ((discardMessage.getReport() & 0x7000000) != 0)
/*      */       {
/* 1336 */         reportMessage = generateReport(discardMessage, reason);
/*      */         
/* 1338 */         String replyQ = discardMessage.getReplyToQueueName().trim();
/* 1339 */         String replyQMgr = discardMessage.getReplyToQueueManagerName().trim();
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1344 */           int openOptions = 8720;
/*      */           
/* 1346 */           MQQueue RQ = this.session.getQM().accessQueue(replyQ, openOptions, replyQMgr, null, null);
/*      */ 
/*      */ 
/*      */           
/* 1350 */           MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 1351 */           pmo.options = 514;
/* 1352 */           pmo.contextReference = this.queue;
/* 1353 */           RQ.put(reportMessage, pmo);
/* 1354 */           RQ.close();
/*      */         }
/* 1356 */         catch (MQException mqe) {
/* 1357 */           if (Trace.isOn) {
/* 1358 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */           
/* 1362 */           if (Trace.isOn) {
/* 1363 */             Trace.traceData(this, "Could not put report message to replyToQ", null);
/* 1364 */             Trace.traceData(this, "Attempting to put to DLQ instead", null);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1371 */           if (this.mqDLQName == null) {
/* 1372 */             this.mqDLQName = Utils.inquireString((MQManagedObject)this.session.getQM(), 2006);
/*      */           }
/* 1374 */           if (Trace.isOn) {
/* 1375 */             Trace.traceData(this, "DLQ = <" + this.mqDLQName + ">", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1380 */           if (this.mqDLQName == null || this.mqDLQName.length() == 0) {
/* 1381 */             if (Trace.isOn) {
/* 1382 */               Trace.traceData(this, "Unable to find DLQ name - is one defined?", null);
/*      */             }
/* 1384 */             JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1385 */             if (Trace.isOn) {
/* 1386 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/* 1390 */             throw je;
/*      */           } 
/*      */ 
/*      */           
/* 1394 */           DLH dlh = new DLH();
/* 1395 */           dlh.reason = mqe.reasonCode;
/* 1396 */           dlh.destQName = replyQ;
/* 1397 */           dlh.destQMgrName = replyQMgr;
/* 1398 */           dlh.putApplType = 28;
/* 1399 */           dlh.putApplName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */           
/* 1402 */           GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 1403 */           dlh.putDate = Utils.getDate(gc);
/* 1404 */           dlh.putTime = Utils.getTime(gc);
/*      */ 
/*      */           
/* 1407 */           dlh.write(reportMessage);
/*      */ 
/*      */           
/* 1410 */           int openOptions = 8720;
/*      */           
/* 1412 */           MQQueue DLQ = this.session.getQM().accessQueue(this.mqDLQName, openOptions, this.session
/* 1413 */               .getQMName(), null, null);
/*      */ 
/*      */           
/* 1416 */           MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 1417 */           pmo.options = 514;
/* 1418 */           pmo.contextReference = this.queue;
/* 1419 */           DLQ.put(reportMessage, pmo);
/* 1420 */           DLQ.close();
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1425 */     } catch (MQException mqe) {
/* 1426 */       if (Trace.isOn) {
/* 1427 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */       
/* 1431 */       if (mqe.reasonCode == 2033) {
/*      */         
/* 1433 */         if (Trace.isOn) {
/* 1434 */           Trace.traceData(this, "ProviderMessage to discard has disappeared! Continue as normal", null);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 1442 */       else if (discardMessage.getPersistence() == 0) {
/* 1443 */         if (Trace.isOn) {
/* 1444 */           Trace.traceData(this, "Could not send report message, but message isnon-persistent - so subsume the error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */           
/* 1452 */           if (this.session.getTransacted() || this.session
/* 1453 */             .getAcknowledgeMode() == 2)
/*      */           {
/*      */ 
/*      */             
/* 1457 */             if (Trace.isOn) {
/* 1458 */               Trace.traceData(this, "Unable to backout the message as session is transacted or Client Ack", null);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 1463 */             if (Trace.isOn) {
/* 1464 */               Trace.traceData(this, "backing out the message", null);
/*      */             }
/* 1466 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 1469 */         } catch (MQException mqe2) {
/* 1470 */           if (Trace.isOn) {
/* 1471 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)mqe2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1477 */         JMSException je = ConfigEnvironment.newException("MQJMS1082");
/* 1478 */         je.setLinkedException((Exception)mqe);
/* 1479 */         if (Trace.isOn) {
/* 1480 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1483 */         throw je;
/*      */       }
/*      */     
/* 1486 */     } catch (JMSException je) {
/* 1487 */       if (Trace.isOn) {
/* 1488 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1492 */       if (discardMessage.getPersistence() == 0) {
/* 1493 */         if (Trace.isOn) {
/* 1494 */           Trace.traceData(this, "ProviderMessage is non-persistent so ignoring error", null);
/*      */         }
/*      */       } else {
/*      */         
/*      */         try {
/* 1499 */           if (this.session.getTransacted() || this.session
/* 1500 */             .getAcknowledgeMode() == 2)
/*      */           {
/*      */ 
/*      */             
/* 1504 */             if (Trace.isOn) {
/* 1505 */               Trace.traceData(this, "Unable to backout the message as session is transacted or Client Ack", null);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 1510 */             if (Trace.isOn) {
/* 1511 */               Trace.traceData(this, "backing out the message", null);
/*      */             }
/* 1513 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 1516 */         } catch (MQException mqe) {
/* 1517 */           if (Trace.isOn) {
/* 1518 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)mqe, 5);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1523 */         if (Trace.isOn) {
/* 1524 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1527 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1533 */       if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2)
/*      */       {
/*      */ 
/*      */         
/* 1537 */         if (Trace.isOn) {
/* 1538 */           Trace.traceData(this, "Unable to commit the report message as session is transacted or Client Ack", null);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1543 */         if (Trace.isOn) {
/* 1544 */           Trace.traceData(this, "commiting the report message", null);
/*      */         }
/* 1546 */         this.session.getQM().commit();
/*      */       }
/*      */     
/*      */     }
/* 1550 */     catch (MQException mqe) {
/* 1551 */       if (Trace.isOn) {
/* 1552 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)mqe, 6);
/*      */       }
/*      */ 
/*      */       
/* 1556 */       JMSException je = ConfigEnvironment.newException("MQJMS1082");
/* 1557 */       je.setLinkedException((Exception)mqe);
/* 1558 */       if (Trace.isOn) {
/* 1559 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1562 */       throw je;
/*      */     } 
/* 1564 */     if (Trace.isOn) {
/* 1565 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "discard(MQJMSMessage,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void obtainAliasQueueDetails() {
/* 1576 */     if (Trace.isOn) {
/* 1577 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "obtainAliasQueueDetails()");
/*      */     }
/*      */     
/* 1580 */     PropertyStore.register("com.ibm.mq.jms.useDefaultBOValues", false);
/* 1581 */     if (!PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.useDefaultBOValues").booleanValue()) {
/*      */       
/* 1583 */       if (this.aliasBaseQueueName == null || this.aliasBaseQueueName.length() == 0) {
/* 1584 */         this.aliasBaseQueueName = Utils.inquireString((MQManagedObject)this.queue, 2002);
/*      */       }
/* 1586 */       if (Trace.isOn) {
/* 1587 */         Trace.traceData(this, "Attempting to read Backout Requeue Queue from underlying LOCAL queue " + this.aliasBaseQueueName, null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1594 */         int options = 8232;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1599 */         MQQueue baseMqQueue = this.session.getQM().accessQueue(this.aliasBaseQueueName, options);
/*      */         
/* 1601 */         int[] intAttrs = new int[1];
/* 1602 */         byte[] charAttr = new byte[48];
/*      */         
/* 1604 */         JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.session.getQM().getHconn().getCcsid());
/* 1605 */         String inqString = codepage.bytesToString(charAttr);
/* 1606 */         this.backoutRetryQueue = inqString.substring(0, 48).trim();
/*      */         
/* 1608 */         this.backoutThreshold = intAttrs[0];
/*      */         
/* 1610 */         baseMqQueue.close();
/*      */       }
/* 1612 */       catch (Exception mqe) {
/* 1613 */         if (Trace.isOn) {
/* 1614 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "obtainAliasQueueDetails()", mqe);
/*      */         }
/*      */ 
/*      */         
/* 1618 */         HashMap<Object, Object> inserts = new HashMap<>();
/* 1619 */         inserts.put("XMSC_INSERT_DESTINATION_NAME", this.aliasBaseQueueName);
/* 1620 */         inserts.put("XMSC_INSERT_STRING", this.destination.getName());
/* 1621 */         inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(this.backoutThreshold));
/* 1622 */         inserts.put("XMSC_INSERT_EXCEPTION", mqe);
/* 1623 */         if (mqe instanceof MQException) {
/* 1624 */           inserts.put("XMSC_INSERT_COMP_CODE", Integer.valueOf(((MQException)mqe).getCompCode()));
/* 1625 */           inserts.put("XMSC_INSERT_REASON", Integer.valueOf(((MQException)mqe).getReason()));
/*      */         } 
/* 1627 */         Log.log(this, "obtainAliasQueueDetails()", "MQJMS1115", inserts);
/*      */       } 
/*      */     } 
/* 1630 */     if (Trace.isOn)
/* 1631 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueEnumeration", "obtainAliasQueueDetails()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueEnumeration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */