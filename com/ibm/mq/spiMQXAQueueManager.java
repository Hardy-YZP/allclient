/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Hashtable;
/*     */ import javax.transaction.xa.XAException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class spiMQXAQueueManager
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/spiMQXAQueueManager.java";
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72, 5655-R36, 5724-L26, 5655-L82                (c) Copyright IBM Corp. 2008, 2011 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   static final int MQCA_Q_MGR_NAME = 2015;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.MQXAQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/spiMQXAQueueManager.java");
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
/*  76 */   private MQQueueManager qm = null;
/*  77 */   private spiMQXAResource rm = null;
/*     */ 
/*     */ 
/*     */   
/*  81 */   protected static final Object XAGROUP = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public spiMQXAQueueManager(String queueManagerName) throws MQException {
/*  95 */     super(MQSESSION.getJmqiEnv());
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.MQXAQueueManager", "<init>(String)", new Object[] { queueManagerName });
/*     */     }
/*     */     
/*     */     try {
/* 101 */       Hashtable<Object, Object> props = new Hashtable<>();
/*     */       
/* 103 */       props.put("Group", XAGROUP);
/*     */ 
/*     */       
/* 106 */       props.put("XAReq", Boolean.valueOf(true));
/*     */ 
/*     */       
/* 109 */       props.put("Thread affinity", Boolean.valueOf(true));
/* 110 */       this.qm = new MQQueueManager(queueManagerName, props);
/*     */     } finally {
/*     */       
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.finallyBlock(this, "com.ibm.mq.MQXAQueueManager", "<init>(String)");
/*     */       }
/*     */     } 
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.mq.MQXAQueueManager", "<init>(String)");
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
/*     */   public spiMQXAQueueManager(String queueManagerName, Hashtable p) throws MQException {
/* 132 */     super(MQSESSION.getJmqiEnv());
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.mq.MQXAQueueManager", "<init>(String,Hashtable)", new Object[] { queueManagerName, p });
/*     */     }
/*     */     
/*     */     try {
/* 138 */       Hashtable<String, Object> props = (Hashtable)p.clone();
/*     */       
/* 140 */       props.put("Group", XAGROUP);
/*     */ 
/*     */       
/* 143 */       props.put("XAReq", Boolean.valueOf(true));
/*     */ 
/*     */       
/* 146 */       props.put("Thread affinity", Boolean.valueOf(true));
/* 147 */       this.qm = new MQQueueManager(queueManagerName, props);
/*     */     } finally {
/*     */       
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.finallyBlock(this, "com.ibm.mq.MQXAQueueManager", "<init>(String,Hashtable)");
/*     */       }
/*     */     } 
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.MQXAQueueManager", "<init>(String,Hashtable)");
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
/*     */   public spiMQXAQueueManager(String queueManagerName, Hashtable p, MQConnectionManager connectionManager) throws MQException {
/* 173 */     super(MQSESSION.getJmqiEnv());
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.entry(this, "com.ibm.mq.MQXAQueueManager", "<init>(String,Hashtable,com.ibm.mq.MQConnectionManager)", new Object[] { queueManagerName, p, connectionManager });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 180 */       Hashtable<String, Object> props = (Hashtable)p.clone();
/*     */       
/* 182 */       props.put("Group", XAGROUP);
/*     */ 
/*     */       
/* 185 */       props.put("XAReq", Boolean.valueOf(true));
/*     */ 
/*     */       
/* 188 */       props.put("Thread affinity", Boolean.valueOf(true));
/* 189 */       this.qm = new MQQueueManager(queueManagerName, props, connectionManager);
/*     */     } finally {
/*     */       
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.finallyBlock(this, "com.ibm.mq.MQXAQueueManager", "<init>(String,Hashtable,com.ibm.mq.MQConnectionManager)");
/*     */       }
/*     */     } 
/*     */     
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.mq.MQXAQueueManager", "<init>(String,Hashtable,com.ibm.mq.MQConnectionManager)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public spiMQXAQueueManager(MQQueueManager qm) {
/* 265 */     super(MQSESSION.getJmqiEnv());
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.entry(this, "com.ibm.mq.MQXAQueueManager", "<init>(MQQueueManager)", new Object[] { qm });
/*     */     }
/*     */     
/*     */     try {
/* 271 */       this.qm = qm;
/*     */     } finally {
/*     */       
/* 274 */       if (Trace.isOn) {
/* 275 */         Trace.finallyBlock(this, "com.ibm.mq.MQXAQueueManager", "<init>(MQQueueManager)");
/*     */       }
/*     */     } 
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.mq.MQXAQueueManager", "<init>(MQQueueManager)");
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
/*     */   public MQQueueManager getMQQueueManager() {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.data(this, "com.ibm.mq.MQXAQueueManager", "getMQQueueManager()", "getter", this.qm);
/*     */     }
/* 298 */     return this.qm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQQueueManager getQueueManager() {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.MQXAQueueManager", "getQueueManager()", "getter", this.qm);
/*     */     }
/* 310 */     return this.qm;
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
/*     */   public spiMQXAResource getXAResource() throws XAException {
/* 326 */     this.rm = new spiMQXAResource(this.qm.getSession(), this.qm.Hconn.getHconn());
/*     */     
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.MQXAQueueManager", "getXAResource()", "getter", this.rm);
/*     */     }
/* 331 */     return this.rm;
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
/*     */   public void close() {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.entry(this, "com.ibm.mq.MQXAQueueManager", "close()");
/*     */     }
/*     */     try {
/* 348 */       this.rm.close();
/*     */     }
/* 350 */     catch (Exception e) {
/* 351 */       if (Trace.isOn) {
/* 352 */         Trace.catchBlock(this, "com.ibm.mq.MQXAQueueManager", "close()", e, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 361 */       this.qm.disconnect();
/*     */     }
/* 363 */     catch (Exception e) {
/* 364 */       if (Trace.isOn) {
/* 365 */         Trace.catchBlock(this, "com.ibm.mq.MQXAQueueManager", "close()", e, 2);
/*     */       }
/*     */     } 
/*     */     
/* 369 */     if (Trace.isOn)
/* 370 */       Trace.exit(this, "com.ibm.mq.MQXAQueueManager", "close()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\spiMQXAQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */