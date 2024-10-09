/*     */ package com.ibm.mq.ese.service;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PolicyBlackList
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/PolicyBlackList.java";
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.ese.service.PolicyBlackList", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/PolicyBlackList.java");
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
/*  53 */   static final String[] QUEUE_BLACK_LIST_ARRAY = new String[] { "SYSTEM.ADMIN.ACCOUNTING.QUEUE", "SYSTEM.ADMIN.ACTIVITY.QUEUE", "SYSTEM.ADMIN.CHANNEL.EVENT", "SYSTEM.ADMIN.COMMAND.EVENT", "SYSTEM.ADMIN.CONFIG.EVENT", "SYSTEM.ADMIN.LOGGER.EVENT", "SYSTEM.ADMIN.PERFM.EVENT", "SYSTEM.ADMIN.PUBSUB.EVENT", "SYSTEM.ADMIN.QMGR.EVENT", "SYSTEM.ADMIN.STATISTICS.QUEUE", "SYSTEM.ADMIN.TRACE.ROUTE.QUEUE", "SYSTEM.AUTH.DATA.QUEUE", "SYSTEM.BROKER.ADMIN.STREAM", "SYSTEM.BROKER.CONTROL.QUEUE", "SYSTEM.BROKER.DEFAULT.STREAM", "SYSTEM.BROKER.INTER.BROKER.COMMUNICATIONS", "SYSTEM.CHANNEL.INITQ", "SYSTEM.CHANNEL.SYNCQ", "SYSTEM.CICS.INITIATION.QUEUE", "SYSTEM.CLUSTER.COMMAND.QUEUE", "SYSTEM.CLUSTER.HISTORY.QUEUE", "SYSTEM.CLUSTER.REPOSITORY.QUEUE", "SYSTEM.CLUSTER.TRANSMIT.QUEUE", "SYSTEM.DEAD.LETTER.QUEUE", "SYSTEM.DURABLE.SUBSCRIBER.QUEUE", "SYSTEM.HIERARCHY.STATE", "SYSTEM.INTER.QMGR.CONTROL", "SYSTEM.INTER.QMGR.FANREQ", "SYSTEM.INTER.QMGR.PUBS", "SYSTEM.INTERNAL.REPLY.QUEUE", "SYSTEM.PENDING.DATA.QUEUE", "SYSTEM.PROTECTION.ERROR.QUEUE", "SYSTEM.PROTECTION.POLICY.QUEUE", "SYSTEM.RETAINED.PUB.QUEUE", "SYSTEM.SELECTION.EVALUATION.QUEUE", "SYSTEM.SELECTION.VALIDATION.QUEUE" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private static final SortedSet<String> BLACK_LIST = new TreeSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry("com.ibm.mq.ese.service.PolicyBlackList", "static()");
/*     */     }
/*  87 */     BLACK_LIST.addAll(Arrays.asList(QUEUE_BLACK_LIST_ARRAY));
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit("com.ibm.mq.ese.service.PolicyBlackList", "static()");
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
/*     */   public static boolean isPolicyBlackListed(String name) {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry("com.ibm.mq.ese.service.PolicyBlackList", "isPolicyBlackListed(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/* 106 */     if (name == null) {
/* 107 */       if (Trace.isOn) {
/* 108 */         Trace.exit("com.ibm.mq.ese.service.PolicyBlackList", "isPolicyBlackListed(String)", 
/* 109 */             Boolean.valueOf(false), 1);
/*     */       }
/* 111 */       return false;
/*     */     } 
/* 113 */     boolean ret = BLACK_LIST.contains(name.trim());
/*     */     
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit("com.ibm.mq.ese.service.PolicyBlackList", "isPolicyBlackListed(String)", 
/* 117 */           Boolean.valueOf(ret), 2);
/*     */     }
/* 119 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\PolicyBlackList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */