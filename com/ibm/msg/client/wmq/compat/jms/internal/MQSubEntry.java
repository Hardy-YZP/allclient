/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQSubEntry
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubEntry.java";
/*     */   static final String SIGNATURE = "MQJMS_PS_SUBENTRY_v2";
/*     */   static final String v1SIGNATURE = "MQJMS_PS_ADMIN_ENTRY";
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubEntry.java");
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
/*     */   private boolean validity = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private String name = null;
/*  91 */   private String topic = null;
/*  92 */   private String qName = null;
/*  93 */   private String selector = null;
/*     */   private boolean noLocal = false;
/*     */   private byte[] statusMgrId;
/*     */   private byte[] subscriberId;
/*     */   private boolean shared_queue = false;
/*  98 */   private int version = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   private char subscriberState = 'u';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQSubEntry(String name, String topic, String qname, String sel, boolean noLocal, byte[] statusMgrId, boolean shared_queue) {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(String,String,String,String,boolean,byte [ ],boolean)", new Object[] { name, topic, qname, sel, 
/*     */             
/* 126 */             Boolean.valueOf(noLocal), statusMgrId, Boolean.valueOf(shared_queue) });
/*     */     }
/*     */     
/* 129 */     this.name = name;
/* 130 */     this.topic = topic;
/* 131 */     this.qName = qname;
/* 132 */     this.selector = sel;
/* 133 */     this.noLocal = noLocal;
/* 134 */     this.statusMgrId = statusMgrId;
/* 135 */     this.shared_queue = shared_queue;
/* 136 */     this.subscriberState = 'u';
/* 137 */     this.validity = true;
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(String,String,String,String,boolean,byte [ ],boolean)");
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
/*     */   MQSubEntry(String name, String topic, String qname, byte[] statusMgrId, boolean shared_queue) {
/* 153 */     this(name, topic, qname, null, false, statusMgrId, shared_queue);
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(String,String,String,byte [ ],boolean)", new Object[] { name, topic, qname, statusMgrId, 
/*     */             
/* 157 */             Boolean.valueOf(shared_queue) });
/*     */     }
/*     */     
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(String,String,String,byte [ ],boolean)");
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
/*     */   MQSubEntry(MQMessage m) {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(MQMessage)", new Object[] { m });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 187 */       String sig = m.readStringOfByteLength("MQJMS_PS_SUBENTRY_v2".length());
/* 188 */       if (!sig.equals("MQJMS_PS_SUBENTRY_v2"))
/*     */       {
/* 190 */         if (sig.equals("MQJMS_PS_ADMIN_ENTRY")) {
/* 191 */           if (Trace.isOn) {
/* 192 */             Trace.traceData(this, "v1 style subscriber entry found on DurSubAdmin queue.", null);
/*     */           }
/* 194 */           this.version = 1;
/*     */         } else {
/*     */           
/* 197 */           if (Trace.isOn) {
/* 198 */             Trace.traceData(this, "Non-DurSubEntry message on DurSubAdmin queue!", null);
/*     */           }
/* 200 */           if (Trace.isOn) {
/* 201 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(MQMessage)", 1);
/*     */           }
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 210 */       int l = m.readInt();
/* 211 */       this.name = m.readStringOfByteLength(l);
/*     */       
/* 213 */       l = m.readInt();
/* 214 */       this.topic = m.readStringOfByteLength(l);
/*     */       
/* 216 */       l = m.readInt();
/* 217 */       this.qName = m.readStringOfByteLength(l);
/*     */       
/* 219 */       l = m.readInt();
/* 220 */       if (l > 0) {
/* 221 */         this.selector = m.readStringOfByteLength(l);
/*     */       }
/*     */       
/* 224 */       this.noLocal = (m.readChar() == 'y');
/*     */ 
/*     */       
/* 227 */       if (this.version == 2) {
/* 228 */         this.shared_queue = (m.readChar() == 'y');
/*     */         
/* 230 */         this.subscriberState = m.readChar();
/*     */         
/* 232 */         this.statusMgrId = m.correlationId;
/* 233 */         this.subscriberId = m.messageId;
/*     */       } 
/*     */       
/* 236 */       this.validity = true;
/*     */     }
/* 238 */     catch (Exception e) {
/* 239 */       if (Trace.isOn) {
/* 240 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(MQMessage)", e);
/*     */       }
/*     */       
/* 243 */       if (Trace.isOn) {
/* 244 */         Trace.traceData(this, "Could not create MQSubEntry", null);
/*     */       }
/* 246 */       this.validity = false;
/*     */     } 
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "<init>(MQMessage)", 2);
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
/*     */   public void setName(String name) {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setName(String)", "setter", name);
/*     */     }
/*     */     
/* 268 */     this.name = name;
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
/*     */   public String getName() {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getName()", "getter", this.name);
/*     */     }
/*     */     
/* 285 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setTopic(String top) {
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setTopic(String)", "setter", top);
/*     */     }
/*     */     
/* 298 */     this.topic = top;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTopic() {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getTopic()", "getter", this.topic);
/*     */     }
/*     */     
/* 312 */     return this.topic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setQName(String qname) {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setQName(String)", "setter", qname);
/*     */     }
/*     */     
/* 325 */     this.qName = qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQName() {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getQName()", "getter", this.qName);
/*     */     }
/*     */     
/* 340 */     return this.qName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSelector(String sel) {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setSelector(String)", "setter", sel);
/*     */     }
/*     */     
/* 353 */     this.selector = sel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSelector() {
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getSelector()", "getter", this.selector);
/*     */     }
/*     */     
/* 365 */     return this.selector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNoLocal(boolean nl) {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setNoLocal(boolean)", "setter", 
/* 376 */           Boolean.valueOf(nl));
/*     */     }
/* 378 */     this.noLocal = nl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNoLocal() {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getNoLocal()", "getter", 
/* 388 */           Boolean.valueOf(this.noLocal));
/*     */     }
/* 390 */     return this.noLocal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSubscriberId(byte[] subId) {
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setSubscriberId(byte [ ])", "setter", subId);
/*     */     }
/*     */     
/* 403 */     this.subscriberId = subId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getSubscriberId() {
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getSubscriberId()", "getter", this.subscriberId);
/*     */     }
/*     */     
/* 413 */     return this.subscriberId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setStatusMgrId(byte[] smId) {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setStatusMgrId(byte [ ])", "setter", smId);
/*     */     }
/*     */     
/* 426 */     this.statusMgrId = smId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getStatusMgrId() {
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getStatusMgrId()", "getter", this.statusMgrId);
/*     */     }
/*     */     
/* 436 */     return this.statusMgrId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSharedQueue(boolean sq) {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setSharedQueue(boolean)", "setter", 
/* 447 */           Boolean.valueOf(sq));
/*     */     }
/* 449 */     this.shared_queue = sq;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getSharedQueue() {
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getSharedQueue()", "getter", 
/* 457 */           Boolean.valueOf(this.shared_queue));
/*     */     }
/* 459 */     return this.shared_queue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSubscriberState(char st) {
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setSubscriberState(char)", "setter", 
/* 470 */           Character.valueOf(st));
/*     */     }
/* 472 */     this.subscriberState = st;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   char getSubscriberState() {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getSubscriberState()", "getter", 
/* 480 */           Character.valueOf(this.subscriberState));
/*     */     }
/* 482 */     return this.subscriberState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setVersion(int v) {
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "setVersion(int)", "setter", 
/* 493 */           Integer.valueOf(v));
/*     */     }
/* 495 */     this.version = v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getVersion() {
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "getVersion()", "getter", 
/* 503 */           Integer.valueOf(this.version));
/*     */     }
/* 505 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isValid() {
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "isValid()", "getter", 
/* 516 */           Boolean.valueOf(this.validity));
/*     */     }
/* 518 */     return this.validity;
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
/*     */   MQMessage toMessage() {
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "toMessage()");
/*     */     }
/* 535 */     MQMessage ret = new MQMessage();
/*     */     
/*     */     try {
/* 538 */       ret.writeString("MQJMS_PS_SUBENTRY_v2");
/* 539 */       ret.writeInt(this.name.length());
/* 540 */       ret.writeString(this.name);
/* 541 */       ret.writeInt(this.topic.length());
/* 542 */       ret.writeString(this.topic);
/* 543 */       ret.writeInt(this.qName.length());
/* 544 */       ret.writeString(this.qName);
/*     */       
/* 546 */       if (this.selector == null) {
/* 547 */         ret.writeInt(0);
/*     */       } else {
/* 549 */         ret.writeInt(this.selector.length());
/* 550 */         ret.writeString(this.selector);
/*     */       } 
/*     */       
/* 553 */       ret.writeChar(this.noLocal ? 121 : 110);
/*     */       
/* 555 */       ret.writeChar(this.shared_queue ? 121 : 110);
/*     */       
/* 557 */       ret.writeChar(this.subscriberState);
/*     */       
/* 559 */       if (this.subscriberId != null) {
/* 560 */         ret.messageId = this.subscriberId;
/*     */       }
/* 562 */       if (this.statusMgrId != null) {
/* 563 */         ret.correlationId = this.statusMgrId;
/*     */       
/*     */       }
/*     */     }
/* 567 */     catch (Exception e) {
/* 568 */       if (Trace.isOn) {
/* 569 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "toMessage()", e);
/*     */       }
/*     */       
/* 572 */       if (Trace.isOn) {
/* 573 */         Trace.traceData(this, "Could not convert MQSubEntry to MQMessage", null);
/*     */       }
/* 575 */       ret = null;
/*     */     } 
/*     */     
/* 578 */     if (Trace.isOn) {
/* 579 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "toMessage()", ret);
/*     */     }
/*     */     
/* 582 */     return ret;
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
/*     */   public String toString() {
/* 596 */     if (Trace.isOn) {
/* 597 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "toString()");
/*     */     }
/*     */     
/* 600 */     String traceRet1 = "\nName         : " + this.name + "\nTopic        : " + this.topic + "\nQueue Name   : " + this.qName + "\nSelector     : " + this.selector + "\nnoLocal?     : " + (this.noLocal ? "Y" : "N") + "\nsubscriberId : " + Utils.bytesToHex(this.subscriberId) + "\nsharedQ?     : " + (this.shared_queue ? "Y" : "N");
/* 601 */     if (Trace.isOn) {
/* 602 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "toString()", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 606 */     return traceRet1;
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
/*     */   public boolean equals(Object obj) {
/* 626 */     if (Trace.isOn) {
/* 627 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 631 */     boolean ret = false;
/*     */     
/* 633 */     if (obj instanceof MQSubEntry) {
/* 634 */       MQSubEntry entry = (MQSubEntry)obj;
/*     */ 
/*     */       
/* 637 */       if (this.selector == null) {
/* 638 */         ret = (this.name.equals(entry.name) && this.topic.equals(entry.topic) && entry.selector == null);
/*     */       } else {
/* 640 */         ret = (this.name.equals(entry.name) && this.topic.equals(entry.topic) && this.selector.equals(entry.selector));
/*     */       } 
/*     */     } 
/*     */     
/* 644 */     if (Trace.isOn) {
/* 645 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "equals(Object)", 
/* 646 */           Boolean.valueOf(ret));
/*     */     }
/* 648 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 658 */     if (Trace.isOn) {
/* 659 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "hashCode()");
/*     */     }
/* 661 */     assert false : "hashCode not designed";
/* 662 */     int traceRet1 = -1;
/* 663 */     if (Trace.isOn) {
/* 664 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubEntry", "hashCode()", 
/* 665 */           Integer.valueOf(traceRet1));
/*     */     }
/* 667 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQSubEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */