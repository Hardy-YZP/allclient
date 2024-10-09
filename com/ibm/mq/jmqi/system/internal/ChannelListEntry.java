/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChannelListEntry
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/ChannelListEntry.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jmqi.system.internal.ChannelListEntry", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/ChannelListEntry.java");
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
/*  61 */   private int transport = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private String name = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private int useCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean updateRequired = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private int totalWeight = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private String channelFile = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   private long modTime = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   private ChannelEntry alphaEntry = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   private ChannelEntry thisAlphaEntry = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private ChannelEntry weightedEntry = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   private ChannelEntry lastWeightedEntry = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   private ChannelEntry thisWeightedEntry = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   private int weightedEntryCount = 0; private boolean ordered; private Random random; public void checkUpdateRequired() { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "checkUpdateRequired()");  if (this.channelFile != null) {
/*     */       final File f = new File(this.channelFile); long currentModTime = ((Long)AccessController.<Long>doPrivileged(new PrivilegedAction<Long>() { public Long run() { if (Trace.isOn)
/*     */                 Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "run()");  Long traceRet1 = Long.valueOf(f.lastModified()); if (Trace.isOn)
/*     */                 Trace.exit(this, "com.ibm.mq.jmqi.system.internal.null", "run()", traceRet1);  return traceRet1; } }
/*     */         )).longValue(); if (this.modTime != currentModTime)
/*     */         this.updateRequired = true; 
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "checkUpdateRequired()");  }
/* 131 */   public ChannelListEntry(JmqiEnvironment env, int transportType) throws JmqiException { super(env);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     this.ordered = false;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "<init>(JmqiEnvironment,int)", new Object[] { env, Integer.valueOf(transportType) }); 
/*     */     this.transport = transportType;
/*     */     this.random = new Random();
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "<init>(JmqiEnvironment,int)");  }
/*     */   
/*     */   private void orderWeightedChannelEntry() {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "orderWeightedChannelEntry()");
/*     */     }
/*     */     
/* 280 */     assert !this.ordered;
/* 281 */     int seed = 0;
/* 282 */     int weight = 0;
/* 283 */     int randNum = 0;
/* 284 */     Random random = null;
/* 285 */     int remainingWeight = 0;
/*     */     
/* 287 */     ChannelEntry chlEntry = null;
/*     */     
/* 289 */     ChannelEntry previousChlEntry = null;
/*     */     
/* 291 */     ChannelEntry newChlEntry = null;
/*     */     
/* 293 */     ChannelEntry currentNewChlEntry = null;
/* 294 */     if (this.lastWeightedEntry != null) {
/* 295 */       this.lastWeightedEntry.setNextChannel(null);
/*     */     }
/*     */     try {
/* 298 */       seed = InetAddress.getLocalHost().getHostName().hashCode();
/*     */     }
/* 300 */     catch (UnknownHostException e) {
/* 301 */       if (Trace.isOn) {
/* 302 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "orderWeightedChannelEntry()", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 308 */     random = new Random(seed);
/* 309 */     remainingWeight = this.totalWeight;
/*     */     
/* 311 */     while (this.weightedEntry != null) {
/* 312 */       randNum = random.nextInt(remainingWeight);
/* 313 */       chlEntry = this.weightedEntry;
/* 314 */       previousChlEntry = null;
/* 315 */       weight = 0;
/*     */       
/* 317 */       while (chlEntry != null) {
/* 318 */         weight += chlEntry.getChannel().getClientChannelWeight();
/*     */         
/* 320 */         if (weight > randNum) {
/* 321 */           remainingWeight -= chlEntry.getChannel().getClientChannelWeight();
/*     */ 
/*     */           
/* 324 */           if (currentNewChlEntry != null) {
/* 325 */             currentNewChlEntry.setNextChannel(chlEntry);
/*     */           } else {
/*     */             
/* 328 */             newChlEntry = chlEntry;
/*     */           } 
/* 330 */           currentNewChlEntry = chlEntry;
/*     */ 
/*     */           
/* 333 */           if (previousChlEntry != null) {
/* 334 */             previousChlEntry.setNextChannel(chlEntry.getNextChannel());
/*     */             break;
/*     */           } 
/* 337 */           this.weightedEntry = chlEntry.getNextChannel();
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 342 */         previousChlEntry = chlEntry;
/* 343 */         chlEntry = chlEntry.getNextChannel();
/*     */       } 
/*     */     } 
/*     */     
/* 347 */     currentNewChlEntry.setNextChannel(newChlEntry);
/* 348 */     this.weightedEntry = newChlEntry;
/* 349 */     this.thisWeightedEntry = newChlEntry;
/* 350 */     this.lastWeightedEntry = currentNewChlEntry;
/*     */     
/* 352 */     this.ordered = true;
/*     */     
/* 354 */     if (Trace.isOn)
/* 355 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "orderWeightedChannelEntry()");  } public void createChannelEntryLists(Iterator<?> channelIterator) { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "createChannelEntryLists(Iterator<?>)", new Object[] { channelIterator });  while (channelIterator.hasNext()) {
/*     */       MQCD channel = (MQCD)channelIterator.next();
/*     */       if (channel.getTransportType() == this.transport)
/*     */         if (this.name == null || this.name.equals(channel.getQMgrName().trim()))
/*     */           addChannelEntry(channel);  
/*     */     } 
/*     */     if (this.weightedEntry != null)
/*     */       orderWeightedChannelEntry(); 
/*     */     this.thisAlphaEntry = this.alphaEntry;
/*     */     this.thisWeightedEntry = this.weightedEntry;
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "createChannelEntryLists(Iterator<?>)");  }
/* 368 */   public MQCD selectChannelEntry(MQCD cd, ThreadChannelEntry threadChlEntry) { if (Trace.isOn) {
/* 369 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "selectChannelEntry(MQCD,ThreadChannelEntry)", new Object[] { cd, threadChlEntry });
/*     */     }
/*     */     
/* 372 */     ChannelEntry chlEntry = null;
/*     */     
/* 374 */     if (cd != null) {
/* 375 */       chlEntry = selectNamedEntry(cd);
/*     */ 
/*     */     
/*     */     }
/* 379 */     else if (threadChlEntry.getThisWeightedEntry() != null) {
/* 380 */       threadChlEntry.setThisWeightedEntry(threadChlEntry.getThisWeightedEntry().getNextChannel());
/* 381 */       if (threadChlEntry.getThisWeightedEntry() != threadChlEntry.getFirstWeightedEntry()) {
/* 382 */         chlEntry = threadChlEntry.getThisWeightedEntry();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 387 */       if (threadChlEntry.getThisAlphaEntry() != null) {
/* 388 */         threadChlEntry.setThisAlphaEntry(threadChlEntry.getThisAlphaEntry().getNextChannel());
/*     */       } else {
/*     */         
/* 391 */         threadChlEntry.setThisAlphaEntry(this.thisAlphaEntry);
/*     */       } 
/*     */       
/* 394 */       if (threadChlEntry.getThisAlphaEntry() != null) {
/* 395 */         chlEntry = threadChlEntry.getThisAlphaEntry();
/*     */ 
/*     */       
/*     */       }
/* 399 */       else if (this.thisWeightedEntry != null) {
/*     */         
/* 401 */         if (this.thisWeightedEntry.getChannel().getConnectionAffinity() == 1) {
/* 402 */           chlEntry = this.thisWeightedEntry;
/* 403 */           threadChlEntry.setThisWeightedEntry(chlEntry);
/*     */ 
/*     */         
/*     */         }
/* 407 */         else if (threadChlEntry.seenWeightedEntryCount() < this.weightedEntryCount) {
/*     */           
/*     */           do {
/* 410 */             chlEntry = selectRandomEntry(threadChlEntry);
/*     */           }
/* 412 */           while (threadChlEntry.seenThisWeightedEntry(chlEntry));
/*     */ 
/*     */           
/* 415 */           threadChlEntry.rememberThisWeightedEntry(chlEntry);
/*     */ 
/*     */           
/* 418 */           threadChlEntry.setThisWeightedEntry(null);
/*     */         }
/*     */         else {
/*     */           
/* 422 */           chlEntry = null;
/*     */         } 
/*     */         
/* 425 */         threadChlEntry.setFirstWeightedEntry(chlEntry);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 430 */     MQCD traceRet1 = (chlEntry != null) ? chlEntry.getChannel() : null;
/*     */     
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "selectChannelEntry(MQCD,ThreadChannelEntry)", traceRet1);
/*     */     }
/*     */     
/* 436 */     return traceRet1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ChannelEntry selectNamedEntry(MQCD cd) {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "selectNamedEntry(MQCD)", new Object[] { cd });
/*     */     }
/*     */     
/* 449 */     ChannelEntry chlEntry = this.alphaEntry;
/* 450 */     ChannelEntry firstChlEntry = null;
/* 451 */     String chlName = cd.getChannelName();
/*     */     
/* 453 */     while (chlEntry != null && 
/* 454 */       chlEntry.getChannel().getChannelName().compareTo(chlName) != 0)
/*     */     {
/*     */       
/* 457 */       chlEntry = chlEntry.getNextChannel();
/*     */     }
/* 459 */     if (chlEntry == null) {
/*     */       
/* 461 */       chlEntry = this.weightedEntry;
/* 462 */       firstChlEntry = this.weightedEntry;
/* 463 */       while (chlEntry != null && 
/* 464 */         chlEntry.getChannel().getChannelName().compareTo(chlName) != 0) {
/*     */ 
/*     */         
/* 467 */         chlEntry = chlEntry.getNextChannel();
/* 468 */         if (chlEntry == firstChlEntry) {
/* 469 */           chlEntry = null;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "selectNamedEntry(MQCD)", chlEntry);
/*     */     }
/*     */     
/* 479 */     return chlEntry;
/*     */   } private void addChannelEntry(MQCD channel) { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "addChannelEntry(MQCD)", new Object[] { channel });  ChannelEntry chlEntry = new ChannelEntry(this.env, channel, null); if (chlEntry.getChannel().getMaxMsgLength() == 0) { chlEntry.getChannel().setMaxMsgLength(104857600); if (Trace.isOn)
/*     */         Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "addChannelEntry(MQCD)", "Found MaxMsgLength=0, Setting it to 100MB", new Object[] { channel });  }  if (chlEntry.getChannel().getClientChannelWeight() == 0) { if (this.thisAlphaEntry != null) { this.thisAlphaEntry.setNextChannel(chlEntry); } else { this.alphaEntry = chlEntry; }
/*     */        chlEntry.setNextChannel(null); this.thisAlphaEntry = chlEntry; }
/*     */     else { chlEntry.setNextChannel(this.weightedEntry); this.weightedEntry = chlEntry; this.totalWeight += channel.getClientChannelWeight(); this.weightedEntryCount++; }
/*     */      if (Trace.isOn)
/* 486 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "addChannelEntry(MQCD)");  } private ChannelEntry selectRandomEntry(ThreadChannelEntry threadChlEntry) { if (Trace.isOn) {
/* 487 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "selectRandomEntry(ThreadChannelEntry)", new Object[] { threadChlEntry });
/*     */     }
/*     */     
/* 490 */     ChannelEntry chlEntry = null;
/* 491 */     int randNum = 0;
/* 492 */     int weight = 0;
/*     */     
/* 494 */     randNum = this.random.nextInt(this.totalWeight);
/*     */     
/* 496 */     for (chlEntry = this.weightedEntry; chlEntry != null; chlEntry = chlEntry.getNextChannel()) {
/* 497 */       weight += chlEntry.getChannel().getClientChannelWeight();
/* 498 */       if (weight > randNum) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "selectRandomEntry(ThreadChannelEntry)", chlEntry);
/*     */     }
/*     */     
/* 507 */     return chlEntry; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUpdateRequired() {
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getUpdateRequired()", "getter", 
/* 516 */           Boolean.valueOf(this.updateRequired));
/*     */     }
/* 518 */     return this.updateRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 525 */     if (Trace.isOn) {
/* 526 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getName()", "getter", this.name);
/*     */     }
/*     */     
/* 529 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUseCount() {
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getUseCount()", "getter", 
/* 538 */           Integer.valueOf(this.useCount));
/*     */     }
/* 540 */     return this.useCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalWeight() {
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getTotalWeight()", "getter", 
/* 549 */           Integer.valueOf(this.totalWeight));
/*     */     }
/* 551 */     return this.totalWeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChannelFile() {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getChannelFile()", "getter", this.channelFile);
/*     */     }
/*     */     
/* 562 */     return this.channelFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getModTime() {
/* 569 */     if (Trace.isOn) {
/* 570 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getModTime()", "getter", 
/* 571 */           Long.valueOf(this.modTime));
/*     */     }
/* 573 */     return this.modTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getAlphaEntry() {
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getAlphaEntry()", "getter", this.alphaEntry);
/*     */     }
/*     */     
/* 584 */     return this.alphaEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getThisAlphaEntry() {
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getThisAlphaEntry()", "getter", this.thisAlphaEntry);
/*     */     }
/*     */     
/* 595 */     return this.thisAlphaEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getWeightedEntry() {
/* 602 */     if (Trace.isOn) {
/* 603 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getWeightedEntry()", "getter", this.weightedEntry);
/*     */     }
/*     */     
/* 606 */     return this.weightedEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getThisWeightedEntry() {
/* 613 */     if (Trace.isOn) {
/* 614 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "getThisWeightedEntry()", "getter", this.thisWeightedEntry);
/*     */     }
/*     */     
/* 617 */     return this.thisWeightedEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String qMgrName) {
/* 624 */     if (Trace.isOn) {
/* 625 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setName(String)", "setter", qMgrName);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 630 */     if (qMgrName != null) {
/* 631 */       this.name = qMgrName.trim();
/*     */     } else {
/*     */       
/* 634 */       this.name = qMgrName;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseCount(int count) {
/* 642 */     if (Trace.isOn) {
/* 643 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setUseCount(int)", "setter", 
/* 644 */           Integer.valueOf(count));
/*     */     }
/* 646 */     this.useCount = count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpdateRequired(boolean required) {
/* 653 */     if (Trace.isOn) {
/* 654 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setUpdateRequired(boolean)", "setter", 
/* 655 */           Boolean.valueOf(required));
/*     */     }
/* 657 */     this.updateRequired = required;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTotalWeight(int weight) {
/* 664 */     if (Trace.isOn) {
/* 665 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setTotalWeight(int)", "setter", 
/* 666 */           Integer.valueOf(weight));
/*     */     }
/* 668 */     this.totalWeight = weight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChannelFile(String fileName) {
/* 675 */     if (Trace.isOn) {
/* 676 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setChannelFile(String)", "setter", fileName);
/*     */     }
/*     */     
/* 679 */     this.channelFile = fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModTime(long time) {
/* 686 */     if (Trace.isOn) {
/* 687 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setModTime(long)", "setter", 
/* 688 */           Long.valueOf(time));
/*     */     }
/* 690 */     this.modTime = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlphaEntry(ChannelEntry aEntry) {
/* 698 */     if (Trace.isOn) {
/* 699 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setAlphaEntry(ChannelEntry)", "setter", aEntry);
/*     */     }
/*     */     
/* 702 */     this.alphaEntry = aEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThisAlphaEntry(ChannelEntry aEntry) {
/* 711 */     if (Trace.isOn) {
/* 712 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setThisAlphaEntry(ChannelEntry)", "setter", aEntry);
/*     */     }
/*     */     
/* 715 */     this.thisAlphaEntry = aEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWeightedEntry(ChannelEntry wEntry) {
/* 724 */     if (Trace.isOn) {
/* 725 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setWeightedEntry(ChannelEntry)", "setter", wEntry);
/*     */     }
/*     */     
/* 728 */     this.weightedEntry = wEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThisWeightedEntry(ChannelEntry wEntry) {
/* 737 */     if (Trace.isOn) {
/* 738 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelListEntry", "setThisWeightedEntry(ChannelEntry)", "setter", wEntry);
/*     */     }
/*     */     
/* 741 */     this.thisWeightedEntry = wEntry;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dumpLists() {
/* 746 */     System.out.println("Alpha List");
/* 747 */     dumpList(this.alphaEntry);
/* 748 */     System.out.println("Weghted List");
/* 749 */     dumpList(this.weightedEntry);
/*     */   }
/*     */ 
/*     */   
/*     */   private void dumpList(ChannelEntry head) {
/* 754 */     ChannelEntry current = head;
/*     */     
/* 756 */     while (current != null) {
/* 757 */       System.out.format("\t%s - %d%n", new Object[] { current.getChannel().getConnectionName(), Integer.valueOf(current.getChannel().getClientChannelWeight()) });
/* 758 */       current = current.getNextChannel();
/* 759 */       if (current == head)
/* 760 */         current = null; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\ChannelListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */