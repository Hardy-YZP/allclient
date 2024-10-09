/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.constants.MQConstants;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.URL;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class MQQueueManagerFactory
/*      */   extends JmqiObject
/*      */ {
/*      */   private static final String CONSTANT_LOOKUP_SYMBOL = "$";
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQQueueManagerFactory.java";
/*      */   
/*      */   static {
/*   67 */     if (Trace.isOn) {
/*   68 */       Trace.data("com.ibm.mq.MQQueueManagerFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQQueueManagerFactory.java");
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
/*   79 */   private static MQQueueManagerFactory theFactory = null;
/*      */ 
/*      */   
/*   82 */   private static QMTree qmTree = null;
/*      */ 
/*      */   
/*   85 */   private static final Object lock = new Object();
/*      */ 
/*      */   
/*      */   public static final String OVERRIDE_CONNECTION_DETAILS_FROM_PROPERTYSTORE = "com.ibm.mq.overrideConnectionDetails";
/*      */   
/*      */   public static final String OVERRIDE_CONNECTION_PREFIX = "mqj.";
/*      */   
/*      */   public static final String QMGR_NAME_PROPERTY = "qmgr";
/*      */   
/*      */   private boolean logOverrideValues;
/*      */ 
/*      */   
/*      */   private MQQueueManagerFactory() {
/*   98 */     super(MQSESSION.getJmqiEnv());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  991 */     this.logOverrideValues = true; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "<init>()");  NLSServices.addCatalogue("mqji", "MQJI"); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "<init>()"); 
/*      */   }
/*      */   public static MQQueueManagerFactory getInstance() { MQJavaLevel.traceBuildInfo(); synchronized (lock) { if (theFactory == null) { theFactory = new MQQueueManagerFactory(); qmTree = new QMTree(); }  }  if (Trace.isOn) Trace.data("com.ibm.mq.MQQueueManagerFactory", "getInstance()", "getter", theFactory);  return theFactory; } protected MQQueueManager createQueueManager(int optionsP, QueueManagerFactoryProperties properties, URL ccdtUrl) throws MQException { MQException traceRet1, e; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", new Object[] { Integer.valueOf(optionsP), properties, ccdtUrl });  if (CSSystem.currentPlatform() == CSSystem.Platform.OS_ZSERIES && esafDetected()) { MQException mQException = new MQException(2, 2012, this, "MQJE089"); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", mQException, 8);  throw mQException; }  int options = optionsP; String fid = "createQueueManager(int,QueueManagerFactoryProperties,URL)"; MQQueueManager qMgr = null; if (properties == null || !properties.isValid()) { MQException mQException = new MQException(2, 2046, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", mQException, 1);  throw mQException; }  boolean fromMQEnvironment = (options < 0); if (fromMQEnvironment) options ^= 0xFFFFFFFF;  switch (options) { case 0: if (!fromMQEnvironment) { qMgr = constructQueueManager(properties, ccdtUrl); break; }  traceRet1 = new MQException(1, 2046, "no available queue manager"); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", traceRet1, 2);  throw traceRet1;case 8: case 32: synchronized (lock) { Thread t = Thread.currentThread(); String mgrName = properties.getName(); if (mgrName == null || mgrName.trim().equals("")) { MQQueueManager qmTmp = createQueueManager(0, properties, ccdtUrl); byte[] b = new byte[48]; int[] arrayOfInt1 = new int[1]; int[] arrayOfInt2 = { 2015, 2 }; qmTmp.inquire(arrayOfInt2, arrayOfInt1, b); qmTmp.disconnect(); try { JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, arrayOfInt1[0]); if (cp == null) { UnsupportedEncodingException traceRet4 = new UnsupportedEncodingException(String.valueOf(arrayOfInt1[0])); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", traceRet4, 3);  throw traceRet4; }  mgrName = cp.bytesToString(b); } catch (CharacterCodingException|UnsupportedEncodingException e1) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", e1, 1);  }  if (mgrName != null) { mgrName = mgrName.trim(); } else { MQException traceRet2 = new MQException(2, 2195, "could not resolve default queue manager"); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", traceRet2, 4);  throw traceRet2; }  }  if (Trace.isOn) Trace.data(this, "createQueueManager(int,QueueManagerFactoryProperties,URL)", "thread: " + t + ", qMgrName: " + mgrName, "");  TreeElt treePtr = qmTree.getAssociateBranch(options); Collection<TreeElt> c = treePtr.children(); boolean threadFound = false; if (options == 8) { Iterator<TreeElt> iterator = c.iterator(); while (!threadFound && iterator.hasNext()) { TreeElt t1 = iterator.next(); if (t == (Thread)t1.getElement()) { TreeElt t2 = qmTree.getChild(treePtr, t); if (t2 != null) { treePtr = t2; c = treePtr.children(); threadFound = true; continue; }  c = new ArrayList<>(); continue; }  c = new ArrayList<>(); }  }  Iterator<TreeElt> i = c.iterator(); boolean found = false; while (!found && i.hasNext()) { TreeElt t3 = i.next(); QMElt q = (QMElt)t3.getElement(); if (mgrName.equals(q.name)) { found = true; treePtr = t3; }  }  if (Trace.isOn) Trace.data(this, "createQueueManager(int,QueueManagerFactoryProperties,URL)", "matching queue manager " + (found ? "found" : "not found"), "");  if (found) { QMElt q = (QMElt)treePtr.getElement(); qMgr = q.mgr; (properties.getMgr()).association = options; try { qmTree.addChild(treePtr, properties.getMgr()); } catch (QMTreeException qte) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", qte, 2);  MQException me = new MQException(2, 2195, "failed to update qmTree"); me.initCause(qte); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", me, 5);  throw me; }  } else { if (fromMQEnvironment) { MQException traceRet3 = new MQException(1, 2046, "no available queue manager"); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", traceRet3, 6);  throw traceRet3; }  qMgr = constructQueueManager(properties, ccdtUrl); try { if (options == 8 && !threadFound) { qmTree.addChild(treePtr, t); treePtr = qmTree.getChild(treePtr, t); }  QMElt q = new QMElt(mgrName, qMgr); qmTree.addChild(treePtr, q); treePtr = qmTree.getChild(treePtr, q); qmTree.addChild(treePtr, properties.getMgr()); (properties.getMgr()).association = options; } catch (QMTreeException qte) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", qte, 3);  MQException me = new MQException(2, 2195, "failed to update qmTree"); me.initCause(qte); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", me, 7);  throw me; }  }  }  break;default: e = new MQException(2, 2046, this); if (Trace.isOn) Trace.data(this, "createQueueManager(int,QueueManagerFactoryProperties,URL)", "bad context value: ", Integer.toString(options));  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", e, 8);  throw e; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "createQueueManager(int,QueueManagerFactoryProperties,URL)", qMgr);  return qMgr; } private boolean esafDetected() { String esaf = AccessController.<String>doPrivileged(new PrivilegedAction<String>() {
/*      */           public String run() { try { return System.getProperty("com.ibm.ims.esaf"); } catch (AccessControlException ace) { return null; }  }
/*  995 */         }); return (esaf != null); } protected void remove(MQQueueManager qm) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "remove(MQQueueManager)", new Object[] { qm });  String fid = "remove(MQQueueManager)"; synchronized (lock) { if (qmTree.leafContains(qm)) { TreeElt treePtr; MQException e; switch (qm.association) { case 0: break;case 8: case 32: treePtr = find(qm); if (treePtr != null) { try { qmTree.removeChild(treePtr, qm); while (treePtr.children().size() == 0 && treePtr.depth() != 1) { Object o = treePtr.getElement(); if (o instanceof MQQueueManager) ((MQQueueManager)o).connected = false;  treePtr = treePtr.getParent(); qmTree.removeChild(treePtr, o); }  } catch (QMTreeException qte) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManagerFactory", "remove(MQQueueManager)", qte);  MQException me = new MQException(2, 2195, "failed to remove qm: " + qm); me.initCause(qte); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "remove(MQQueueManager)", me, 1);  throw me; }  break; }  if (qm.association == 8) if (qmTree.leafContains(qm)) { MQException me = new MQException(2, 2042, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "remove(MQQueueManager)", me, 2);  throw me; }   break;default: e = new MQException(2, 2046, this); if (Trace.isOn) Trace.data(this, "remove(MQQueueManager)", "bad association value: ", Integer.toString(qm.association));  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "remove(MQQueueManager)", e, 3);  throw e; }  }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "remove(MQQueueManager)");  } static boolean isLastReference(MQQueueManager qm) { if (Trace.isOn) Trace.entry("com.ibm.mq.MQQueueManagerFactory", "isLastReference(MQQueueManager)", new Object[] { qm });  boolean traceRet1 = (getReferenceCount(qm) <= 1); if (Trace.isOn) Trace.exit("com.ibm.mq.MQQueueManagerFactory", "isLastReference(MQQueueManager)", Boolean.valueOf(traceRet1));  return traceRet1; } private static int getReferenceCount(MQQueueManager qm) { if (Trace.isOn) Trace.entry("com.ibm.mq.MQQueueManagerFactory", "getReferenceCount(MQQueueManager)", new Object[] { qm });  int references = 0; if (qm != null && qm.association != 0) { TreeElt t = find(qm); if (t != null && t.getParent() != null) references = t.getParent().children().size();  }  if (Trace.isOn) Trace.exit("com.ibm.mq.MQQueueManagerFactory", "getReferenceCount(MQQueueManager)", Integer.valueOf(references));  return references; } private Hashtable updateConnectionDetailsFromPropertyStore(Hashtable connectionPropertiesP) { if (Trace.isOn) {
/*  996 */       Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "updateConnectionDetailsFromPropertyStore(Hashtable)", new Object[] { connectionPropertiesP });
/*      */     }
/*      */ 
/*      */     
/* 1000 */     PropertyConvertingHashtable connectionProperties = new PropertyConvertingHashtable();
/* 1001 */     if (connectionPropertiesP != null)
/*      */     {
/* 1003 */       connectionProperties.putAllWithoutChecks(connectionPropertiesP);
/*      */     }
/*      */ 
/*      */     
/* 1007 */     HashMap<String, Object> propertyStoreProperties = PropertyStore.getAll();
/* 1008 */     StringBuilder builder = new StringBuilder();
/*      */ 
/*      */     
/* 1011 */     for (String key : propertyStoreProperties.keySet()) {
/* 1012 */       String propName = "";
/*      */       try {
/* 1014 */         if (key.startsWith("mqj.")) {
/* 1015 */           if (Trace.isOn) {
/* 1016 */             Trace.data(this, "updateConnectionDetailsFromPropertyStore(Hashtable)", "Looking to override", key);
/*      */           }
/* 1018 */           propName = key.substring("mqj.".length());
/* 1019 */           boolean indirectName = false;
/* 1020 */           boolean indirectValue = false;
/*      */ 
/*      */           
/* 1023 */           if (propName.startsWith("$") && propName.length() > 1) {
/* 1024 */             String lookupString = propName.substring(1).trim();
/* 1025 */             propName = lookupPropertyName(lookupString);
/* 1026 */             indirectName = true;
/*      */           } 
/*      */           
/* 1029 */           if (propName == null) {
/*      */             
/* 1031 */             HashMap<String, String> inserts = new HashMap<>();
/* 1032 */             inserts.put("XMSC_INSERT_INDIRECT_PROPERTY_OVERRIDE", key);
/* 1033 */             Log.log(this, "updateConnectionDetailsFromPropertyStore", "MQJIMQPROPERTY_OVERRIDE_LOOKUP_FAILED", inserts);
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/* 1038 */           if (Trace.isOn) {
/* 1039 */             Trace.data(this, "updateConnectionDetailsFromPropertyStore(Hashtable)", "Looking to override", propName);
/*      */           }
/*      */ 
/*      */           
/* 1043 */           Object newValue = propertyStoreProperties.get(key);
/*      */           
/* 1045 */           if (newValue != null && newValue instanceof String) {
/* 1046 */             if (((String)newValue).startsWith("$") && ((String)newValue).length() > 1) {
/* 1047 */               String lookupString = ((String)newValue).substring(1).trim();
/*      */               
/* 1049 */               Object lookedUp = lookupConstant(lookupString);
/* 1050 */               indirectValue = true;
/*      */               
/* 1052 */               if (lookedUp != null) {
/* 1053 */                 newValue = lookedUp;
/*      */               }
/*      */               else {
/*      */                 
/* 1057 */                 HashMap<String, String> inserts = new HashMap<>();
/* 1058 */                 inserts.put("XMSC_INSERT_INDIRECT_PROPERTY_OVERRIDE", (String)newValue);
/* 1059 */                 Log.log(this, "updateConnectionDetailsFromPropertyStore", "MQJIMQPROPERTY_OVERRIDE_LOOKUP_FAILED", inserts);
/*      */ 
/*      */ 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */             } else {
/* 1066 */               newValue = ((String)newValue).trim();
/*      */             } 
/*      */           }
/*      */ 
/*      */           
/* 1071 */           if (Trace.isOn) {
/* 1072 */             Trace.data(this, "updateConnectionDetailsFromPropertyStore(Hashtable)", "Looking to override with ", newValue);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1077 */           if (this.logOverrideValues) {
/* 1078 */             Object originalValue = connectionProperties.get(propName);
/* 1079 */             if (originalValue == null) {
/* 1080 */               originalValue = new String("<NULL>");
/*      */             }
/* 1082 */             builder.append(propName);
/* 1083 */             if (indirectName) {
/* 1084 */               builder.append(" (" + key + ")");
/*      */             }
/* 1086 */             builder.append("\n");
/*      */             
/* 1088 */             builder.append("\t" + originalValue.toString() + " -> ");
/* 1089 */             builder.append((newValue == null) ? "<NULL>" : newValue.toString());
/* 1090 */             if (indirectValue) {
/* 1091 */               builder.append(" (" + propertyStoreProperties.get(key) + ")");
/*      */             }
/* 1093 */             builder.append("\n");
/*      */           } 
/*      */           
/* 1096 */           connectionProperties.put(propName, newValue);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1108 */       catch (Exception e) {
/* 1109 */         builder.append(MQException.getNLSMsg("MQJE091", propName, e.toString()) + "\n");
/*      */       } 
/*      */     } 
/* 1112 */     if (this.logOverrideValues) {
/*      */       
/* 1114 */       HashMap<String, String> inserts = new HashMap<>();
/* 1115 */       inserts.put("XMSC_INSERT_PROPERTY_OVERRIDE_LIST", builder.toString());
/* 1116 */       Log.log(this, "updateConnectionDetailsFromPropertyStore", "MQJIMQPROPERTY_OVERRIDE", inserts);
/*      */       
/* 1118 */       this.logOverrideValues = false;
/*      */     } 
/*      */     
/* 1121 */     if (Trace.isOn) {
/* 1122 */       Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "updateConnectionDetailsFromPropertyStore(Hashtable)", connectionProperties);
/*      */     }
/*      */     
/* 1125 */     return connectionProperties; }
/*      */   private static TreeElt find(MQQueueManager qm) { if (Trace.isOn) Trace.entry("com.ibm.mq.MQQueueManagerFactory", "find(MQQueueManager)", new Object[] { qm });  TreeElt treePtr = null; synchronized (lock) { if (qmTree != null && qm != null) { treePtr = qmTree.root(); treePtr = qmTree.getChild(treePtr, Integer.valueOf(qm.association)); if (qm.association == 8) { Thread t = Thread.currentThread(); treePtr = qmTree.getChild(treePtr, t); }  if (treePtr != null) { Collection<TreeElt> c = treePtr.children(); Iterator<TreeElt> i = c.iterator(); boolean found = false; while (!found && i.hasNext()) { QMElt q = (QMElt)((TreeElt)i.next()).getElement(); TreeElt t2 = qmTree.getChild(treePtr, q); Collection<TreeElt> c2 = t2.children(); Iterator<TreeElt> i2 = c2.iterator(); while (!found && i2.hasNext()) { MQQueueManager qx = (MQQueueManager)((TreeElt)i2.next()).getElement(); if (qx == qm) { found = true; treePtr = qmTree.getChild(t2, qm); }  }  }  if (!found) treePtr = null;  }  }  }  if (Trace.isOn) Trace.exit("com.ibm.mq.MQQueueManagerFactory", "find(MQQueueManager)", treePtr);  return treePtr; }
/*      */   private MQQueueManager constructQueueManager(QueueManagerFactoryProperties props, URL ccdtUrl) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "constructQueueManager(QueueManagerFactoryProperties,URL)", new Object[] { props, ccdtUrl });  MQQueueManager qm = null; qm = procure(props.getName(), props.getProperties(), props.getConMgr(), ccdtUrl); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "constructQueueManager(QueueManagerFactoryProperties,URL)", qm);  return qm; }
/*      */   private MQQueueManager procure(String queueManagerNameP, Hashtable propertiesP, MQConnectionManager mqCxManP, URL ccdtUrl) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "procure(String,Hashtable,MQConnectionManager,URL)", new Object[] { queueManagerNameP, Trace.sanitizeMap(propertiesP, "password", "XXXXXXXXXXXX"), mqCxManP, ccdtUrl });  Hashtable properties = propertiesP; String queueManagerName = queueManagerNameP; try { MQConnectionManager mqCxMan = mqCxManP; if (mqCxMan == null) synchronized (MQEnvironment.poolTokenSet) { mqCxMan = MQEnvironment.defaultMQCxManager; }   PropertyStore.register("com.ibm.mq.overrideConnectionDetails", false); boolean overrideFromPropertyStore = PropertyStore.getBooleanProperty("com.ibm.mq.overrideConnectionDetails"); if (overrideFromPropertyStore) { HashMap<String, Object> propertyStoreProperties = PropertyStore.getAll(); if (propertyStoreProperties.containsKey("mqj.qmgr")) queueManagerName = (String)propertyStoreProperties.get("mqj.qmgr");  properties = updateConnectionDetailsFromPropertyStore(properties); }  MQQueueManager baseQM = obtainBaseMQQueueManager(queueManagerName, properties, mqCxMan, ccdtUrl); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "procure(String,Hashtable,MQConnectionManager,URL)", baseQM);  return baseQM; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.MQQueueManagerFactory", "procure(String,Hashtable,MQConnectionManager,URL)");  }  }
/* 1129 */   private MQQueueManager obtainBaseMQQueueManager(String qmgr, Hashtable<String, Object> properties, MQConnectionManager cxMan, URL ccdtUrl) throws MQException { MQQueueManager m; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "obtainBaseMQQueueManager(String,Hashtable,MQConnectionManager,URL)", new Object[] { qmgr, Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"), cxMan, ccdtUrl });  String transport = determineTransport(properties); MQManagedConnectionFactory mcf = MQSESSION.getMQManagedConnectionFactory(transport, qmgr, properties); MQConnectionRequestInfo cxReqInf = MQSESSION.getConnectionRequestInfo(transport, properties, ccdtUrl); try { m = (MQQueueManager)cxMan.allocateConnection(mcf, cxReqInf); } catch (MQResourceException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManagerFactory", "obtainBaseMQQueueManager(String,Hashtable,MQConnectionManager,URL)", e);  MQException traceRet2 = processException(e); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "obtainBaseMQQueueManager(String,Hashtable,MQConnectionManager,URL)", traceRet2);  throw traceRet2; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.MQQueueManagerFactory", "obtainBaseMQQueueManager(String,Hashtable,MQConnectionManager,URL)");  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "obtainBaseMQQueueManager(String,Hashtable,MQConnectionManager,URL)", m);  return m; } private String determineTransport(Hashtable properties) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "determineTransport(Hashtable)", new Object[] { Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX") });  String trans = MQEnvironment.getStringProperty("transport", properties); if (trans.equals("MQSeries")) { String hostname = MQEnvironment.getStringProperty("hostname", properties); if (hostname == null || hostname.trim().equals("")) { trans = "MQSeries Bindings"; } else { trans = "MQSeries Client"; }  }  if (!trans.equals("MQSeries Client")) { String scphs = MQEnvironment.getStringProperty("SSL Cipher Suite", properties); if (scphs != null && !scphs.trim().equals("")) { MQException traceRet1 = new MQException(2, 2396, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQQueueManagerFactory", "determineTransport(Hashtable)", traceRet1);  throw traceRet1; }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "determineTransport(Hashtable)", trans);  return trans; } private MQException processException(MQResourceException e) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "processException(MQResourceException)", new Object[] { e });  if (e instanceof MQConnectionAllocationException) { MQException traceRet1 = new MQException(2, 2025, "no more connections available"); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "processException(MQResourceException)", traceRet1, 1);  return traceRet1; }  MQException le = e.getLinkedException(); if (le != null) { if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "processException(MQResourceException)", le, 2);  return le; }  MQException traceRet3 = new MQException(2, 2195, this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "processException(MQResourceException)", traceRet3, 3);  return traceRet3; } protected String lookupPropertyName(String lookupString) throws Exception { if (Trace.isOn) {
/* 1130 */       Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "lookupPropertyName(String)", new Object[] { lookupString });
/*      */     }
/*      */ 
/*      */     
/* 1134 */     String propName = null;
/* 1135 */     Object lookedUp = lookupConstant(lookupString);
/* 1136 */     if (lookedUp instanceof String) {
/* 1137 */       propName = (String)lookedUp;
/*      */     }
/*      */     
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "lookupPropertyName(String)", propName);
/*      */     }
/* 1143 */     return propName; }
/*      */ 
/*      */   
/*      */   protected Object lookupConstant(String lookupString) throws Exception {
/* 1147 */     if (Trace.isOn) {
/* 1148 */       Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "lookupConstant(String)", new Object[] { lookupString });
/*      */     }
/*      */ 
/*      */     
/* 1152 */     if (Trace.isOn) {
/* 1153 */       Trace.data(this, "lookupConstant(String)", "Looking in MQConstants", null);
/*      */     }
/*      */     
/* 1156 */     Object lookedUp = MQConstants.getValue(lookupString);
/*      */     
/* 1158 */     if (lookedUp == null) {
/*      */       try {
/* 1160 */         if (Trace.isOn) {
/* 1161 */           Trace.data(this, "lookupConstant(String)", "Looking in MQC", null);
/*      */         }
/* 1163 */         lookedUp = MQC.class.getField(lookupString).get(MQC.class);
/*      */       }
/* 1165 */       catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException e) {
/* 1166 */         if (Trace.isOn) {
/* 1167 */           Trace.catchBlock(this, "com.ibm.mq.MQQueueManagerFactory", "lookupConstant(String)", e);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1173 */     if (Trace.isOn) {
/* 1174 */       Trace.exit(this, "com.ibm.mq.MQQueueManagerFactory", "lookupConstant(String)", lookedUp);
/*      */     }
/* 1176 */     return lookedUp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private enum TYPE
/*      */   {
/* 1184 */     STRING, INTEGER, LONG, BOOLEAN, UNMAPPABLE;
/*      */   }
/*      */   
/* 1187 */   private static HashMap<String, TYPE> propertyTypes = null;
/*      */   
/*      */   static {
/* 1190 */     propertyTypes = new HashMap<>();
/*      */     
/* 1192 */     propertyTypes.put("APPNAME", TYPE.STRING);
/* 1193 */     propertyTypes.put("CCSID", TYPE.INTEGER);
/* 1194 */     propertyTypes.put("channel", TYPE.STRING);
/* 1195 */     propertyTypes.put("connectOptions", TYPE.INTEGER);
/*      */ 
/*      */ 
/*      */     
/* 1199 */     propertyTypes.put("hostname", TYPE.STRING);
/* 1200 */     propertyTypes.put("Local Address Property", TYPE.STRING);
/* 1201 */     propertyTypes.put("password", TYPE.STRING);
/* 1202 */     propertyTypes.put("port", TYPE.INTEGER);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1207 */     propertyTypes.put("SSL Cipher Suite", TYPE.STRING);
/* 1208 */     propertyTypes.put("SSL Fips Required", TYPE.BOOLEAN);
/* 1209 */     propertyTypes.put("KeyResetCount", TYPE.INTEGER);
/* 1210 */     propertyTypes.put("SSL Peer Name", TYPE.STRING);
/*      */ 
/*      */     
/* 1213 */     propertyTypes.put("userID", TYPE.STRING);
/*      */   }
/*      */ 
/*      */   
/*      */   private class PropertyConvertingHashtable
/*      */     extends Hashtable<String, Object>
/*      */   {
/*      */     private static final long serialVersionUID = 824087787418110813L;
/*      */     
/*      */     private PropertyConvertingHashtable() {}
/*      */     
/*      */     public Object put(String key, Object valueP) {
/* 1225 */       Object convertedValue = null;
/*      */       
/* 1227 */       MQQueueManagerFactory.TYPE type = (MQQueueManagerFactory.TYPE)MQQueueManagerFactory.propertyTypes.get(key);
/*      */       
/* 1229 */       if (type == null)
/*      */       {
/* 1231 */         type = MQQueueManagerFactory.TYPE.STRING;
/*      */       }
/*      */       
/* 1234 */       switch (type)
/*      */       { case INTEGER:
/* 1236 */           convertedValue = Integer.valueOf(valueP.toString());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1247 */           return super.put(key, convertedValue); }  convertedValue = valueP.toString(); return super.put(key, convertedValue);
/*      */     }
/*      */     
/*      */     public void putAllWithoutChecks(Map<? extends K, ? extends V> t) {
/* 1251 */       putAll(t);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1257 */       Set<String> keys = keySet();
/* 1258 */       StringBuilder builder = new StringBuilder();
/* 1259 */       for (String key : keys) {
/* 1260 */         Object value = get(key);
/* 1261 */         builder.append(key + "\t" + value.toString() + " (" + value.getClass().getName() + ")\n");
/*      */       } 
/*      */       
/* 1264 */       return builder.toString();
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQQueueManagerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */