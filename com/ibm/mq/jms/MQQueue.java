/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.Referenceable;
/*     */ import javax.naming.StringRefAddr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQQueue
/*     */   extends MQDestination
/*     */   implements Queue, JmsQueue, Referenceable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 8412792851006107464L;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.MQQueue", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueue.java");
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
/*  71 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("baseQueueManagerName", String.class), new ObjectStreamField("baseQueueName", String.class) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQQueue() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "<init>()");
/*     */     }
/*     */     
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQQueue(JmsDestination delegate) {
/*  95 */     super(delegate);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "<init>(JmsDestination)", new Object[] { delegate });
/*     */     }
/*     */ 
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "<init>(JmsDestination)");
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
/*     */   public MQQueue(String queueName) throws JMSException {
/* 134 */     super(queueName);
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "<init>(String)", new Object[] { queueName });
/*     */     }
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "<init>(String)");
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
/*     */   public MQQueue(String queueManagerName, String queueName) throws JMSException {
/* 153 */     super(queueName);
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "<init>(String,String)", new Object[] { queueManagerName, queueName });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 160 */       setBaseQueueManagerName(queueManagerName);
/*     */     }
/* 162 */     catch (JMSException je) {
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "<init>(String,String)", (Throwable)je);
/*     */       }
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.throwing(this, "com.ibm.mq.jms.MQQueue", "<init>(String,String)", (Throwable)je);
/*     */       }
/* 169 */       throw je;
/*     */     } 
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "<init>(String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseQueueManagerName() {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "getBaseQueueManagerName()");
/*     */     }
/*     */     
/*     */     try {
/* 187 */       String traceRet1 = this.propertyDelegate.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.exit(this, "com.ibm.mq.jms.MQQueue", "getBaseQueueManagerName()", traceRet1, 1);
/*     */       }
/* 191 */       return traceRet1;
/*     */     }
/* 193 */     catch (JMSException je) {
/* 194 */       if (Trace.isOn) {
/* 195 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getBaseQueueManagerName()", (Throwable)je);
/*     */       }
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.exit(this, "com.ibm.mq.jms.MQQueue", "getBaseQueueManagerName()", null, 2);
/*     */       }
/* 200 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseQueueName() {
/* 211 */     String traceRet1 = getName();
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.data(this, "com.ibm.mq.jms.MQQueue", "getBaseQueueName()", "getter", traceRet1);
/*     */     }
/* 215 */     return traceRet1;
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
/*     */   public String getQueueURI() {
/* 227 */     String result = toURI();
/*     */     
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.data(this, "com.ibm.mq.jms.MQQueue", "getQueueURI()", "getter", result);
/*     */     }
/* 232 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQueueName() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "getQueueName()");
/*     */     }
/*     */     try {
/* 244 */       String traceRet1 = super.getQueueName();
/* 245 */       if (Trace.isOn) {
/* 246 */         Trace.exit(this, "com.ibm.mq.jms.MQQueue", "getQueueName()", traceRet1, 1);
/*     */       }
/* 248 */       return traceRet1;
/*     */     }
/* 250 */     catch (JMSException e) {
/* 251 */       if (Trace.isOn) {
/* 252 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getQueueName()", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 256 */       if (Trace.isOn) {
/* 257 */         Trace.exit(this, "com.ibm.mq.jms.MQQueue", "getQueueName()", "", 2);
/*     */       }
/* 259 */       return "";
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
/*     */   public Reference getReference() throws NamingException {
/* 271 */     String s = null;
/* 272 */     String u = null;
/* 273 */     Reference ref = new Reference(MQQueue.class.getName(), MQQueueFactory.class.getName(), null);
/*     */     
/* 275 */     ref.add(new StringRefAddr("VER", String.valueOf(getVersion())));
/*     */     
/* 277 */     s = getDescription();
/* 278 */     if (s != null) {
/* 279 */       ref.add(new StringRefAddr("DESC", s));
/*     */     }
/*     */     
/* 282 */     ref.add(new StringRefAddr("EXP", String.valueOf(getExpiry())));
/* 283 */     ref.add(new StringRefAddr("PRI", String.valueOf(getPriority())));
/* 284 */     ref.add(new StringRefAddr("PER", String.valueOf(getPersistence())));
/* 285 */     ref.add(new StringRefAddr("CCS", String.valueOf(getCCSID())));
/* 286 */     ref.add(new StringRefAddr("TC", String.valueOf(getTargetClient())));
/* 287 */     ref.add(new StringRefAddr("ENC", String.valueOf(getEncoding())));
/* 288 */     ref.add(new StringRefAddr("FIQ", String.valueOf(getFailIfQuiesce())));
/*     */     
/* 290 */     u = getAlternateUserId();
/* 291 */     if (u != null) {
/* 292 */       ref.add(new StringRefAddr("ALTU", u));
/*     */     }
/*     */     
/*     */     try {
/* 296 */       ref.add(new StringRefAddr("WCFMT", String.valueOf(getWildcardFormat())));
/*     */ 
/*     */     
/*     */     }
/* 300 */     catch (JMSException j) {
/* 301 */       if (Trace.isOn) {
/* 302 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 1);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 307 */       ref.add(new StringRefAddr("MBODY", String.valueOf(getMessageBodyStyle())));
/*     */ 
/*     */     
/*     */     }
/* 311 */     catch (JMSException j) {
/* 312 */       if (Trace.isOn) {
/* 313 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 2);
/*     */       }
/*     */     } 
/*     */     try {
/* 317 */       ref.add(new StringRefAddr("MDR", String.valueOf(getMQMDReadEnabled())));
/*     */ 
/*     */     
/*     */     }
/* 321 */     catch (JMSException j) {
/* 322 */       if (Trace.isOn) {
/* 323 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 3);
/*     */       }
/*     */     } 
/*     */     try {
/* 327 */       ref.add(new StringRefAddr("MDW", String.valueOf(getMQMDWriteEnabled())));
/*     */ 
/*     */     
/*     */     }
/* 331 */     catch (JMSException j) {
/* 332 */       if (Trace.isOn) {
/* 333 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 4);
/*     */       }
/*     */     } 
/*     */     try {
/* 337 */       ref.add(new StringRefAddr("MDCTX", String.valueOf(getMQMDMessageContext())));
/*     */ 
/*     */     
/*     */     }
/* 341 */     catch (JMSException j) {
/* 342 */       if (Trace.isOn) {
/* 343 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 5);
/*     */       }
/*     */     } 
/*     */     try {
/* 347 */       ref.add(new StringRefAddr("RACP", String.valueOf(getReadAheadClosePolicy())));
/*     */ 
/*     */     
/*     */     }
/* 351 */     catch (JMSException j) {
/* 352 */       if (Trace.isOn) {
/* 353 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 6);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 358 */       ref.add(new StringRefAddr("RAALD", String.valueOf(getReadAheadAllowed())));
/*     */ 
/*     */     
/*     */     }
/* 362 */     catch (JMSException j) {
/* 363 */       if (Trace.isOn) {
/* 364 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 7);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 369 */       ref.add(new StringRefAddr("PAALD", String.valueOf(getPutAsyncAllowed())));
/*     */ 
/*     */     
/*     */     }
/* 373 */     catch (JMSException j) {
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 8);
/*     */       }
/*     */     } 
/*     */     
/* 379 */     s = getBaseQueueName();
/* 380 */     if (s != null) {
/* 381 */       ref.add(new StringRefAddr("QU", s));
/*     */     }
/*     */     
/* 384 */     s = getBaseQueueManagerName();
/* 385 */     if (s != null) {
/* 386 */       ref.add(new StringRefAddr("QMGR", s));
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 391 */       ref.add(new StringRefAddr("UMA", String.valueOf(getUnmappableAction())));
/*     */ 
/*     */     
/*     */     }
/* 395 */     catch (JMSException j) {
/* 396 */       if (Trace.isOn) {
/* 397 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 9);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 402 */       ref.add(new StringRefAddr("UMR", String.valueOf(getUnmappableReplacement())));
/*     */ 
/*     */     
/*     */     }
/* 406 */     catch (JMSException j) {
/* 407 */       if (Trace.isOn) {
/* 408 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "getReference()", (Throwable)j, 10);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 414 */     Reference superClassReference = super.getReference();
/* 415 */     if (superClassReference != null) {
/* 416 */       Enumeration<RefAddr> e = superClassReference.getAll();
/* 417 */       while (e.hasMoreElements()) {
/* 418 */         RefAddr currentRefAddr = e.nextElement();
/* 419 */         ref.add(currentRefAddr);
/*     */       } 
/*     */     } 
/*     */     
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.jms.MQQueue", "getReference()", "getter", ref);
/*     */     }
/* 426 */     return ref;
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 431 */     if (Trace.isOn) {
/* 432 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 442 */     ObjectInputStream.GetField fields = in.readFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 462 */       if (!fields.defaulted("baseQueueManagerName")) {
/* 463 */         setBaseQueueManagerName((String)fields.get("baseQueueManagerName", (Object)null));
/*     */       }
/*     */       
/* 466 */       if (!fields.defaulted("baseQueueName")) {
/* 467 */         setBaseQueueName((String)fields.get("baseQueueName", (Object)null));
/*     */       }
/*     */     }
/* 470 */     catch (JMSException je) {
/* 471 */       if (Trace.isOn) {
/* 472 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQQueue", "readObject(java.io.ObjectInputStream)", (Throwable)je);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "readObject(java.io.ObjectInputStream)");
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
/*     */   public void setBaseQueueManagerName(String qmName) throws JMSException {
/* 493 */     if (Trace.isOn) {
/* 494 */       Trace.data(this, "com.ibm.mq.jms.MQQueue", "setBaseQueueManagerName(String)", "setter", qmName);
/*     */     }
/*     */     
/* 497 */     this.propertyDelegate.setStringProperty("XMSC_WMQ_QUEUE_MANAGER", qmName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseQueueName(String qName) throws JMSException {
/* 508 */     if (Trace.isOn) {
/* 509 */       Trace.data(this, "com.ibm.mq.jms.MQQueue", "setBaseQueueName(String)", "setter", qName);
/*     */     }
/* 511 */     this.propertyDelegate.setStringProperty("XMSC_DESTINATION_NAME", qName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFromReference(Reference ref) throws JMSException {
/* 521 */     if (Trace.isOn) {
/* 522 */       Trace.data(this, "com.ibm.mq.jms.MQQueue", "setFromReference(Reference)", "setter", ref);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 529 */     setCommonFromReference(ref);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 537 */     RefAddr addr = ref.get("QMGR");
/* 538 */     if (addr != null) {
/* 539 */       String qmgr = (String)addr.getContent();
/* 540 */       if (qmgr == null) {
/* 541 */         qmgr = "";
/*     */       }
/* 543 */       setBaseQueueManagerName(qmgr);
/*     */     } 
/*     */     
/* 546 */     addr = ref.get("QU");
/* 547 */     if (addr != null) {
/* 548 */       Object value = addr.getContent();
/* 549 */       if (value != null) {
/* 550 */         setBaseQueueName((String)value);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 567 */     ObjectOutputStream.PutField fields = out.putFields();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     fields.put("baseQueueManagerName", getBaseQueueManagerName());
/* 573 */     fields.put("baseQueueName", getBaseQueueName());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 578 */     out.writeFields();
/*     */     
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "writeObject(java.io.ObjectOutputStream)");
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
/*     */   public String toString() {
/* 594 */     if (Trace.isOn) {
/* 595 */       Trace.entry(this, "com.ibm.mq.jms.MQQueue", "toString()");
/*     */     }
/* 597 */     String uri = toURI();
/* 598 */     String result = (uri == null) ? "queue:///" : uri;
/* 599 */     if (Trace.isOn) {
/* 600 */       Trace.exit(this, "com.ibm.mq.jms.MQQueue", "toString()", result);
/*     */     }
/* 602 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */