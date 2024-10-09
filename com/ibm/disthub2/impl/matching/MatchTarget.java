/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MatchTarget
/*     */   implements Cloneable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final int ACL_TYPE = 0;
/*     */   public static final int QOP_TYPE = 1;
/*     */   public static final int SERVER_SUBSCRIPTION_TYPE = 2;
/*     */   public static final int INIT_STATE_TYPE = 3;
/*     */   public static final int CLIENT_IP_SUBSCRIPTION_TYPE = 4;
/*     */   public static final int DATAFLOW_SUBSCRIPTION_TYPE = 5;
/*     */   public static final int CONTROLMESSAGE_TYPE = 6;
/*     */   public static final int TOPIC_LOG_TYPE = 7;
/*     */   public static final int SCHEMA_TYPE = 8;
/*     */   public static final int MULTICAST_SUBSCRIPTION_TYPE = 9;
/*     */   public static final int MULTICAST_TOPIC_TYPE = 10;
/*     */   public static final int DURABLE_SUBSCRIPTION_TYPE = 11;
/*     */   public static final int MAX_TYPE = 11;
/*     */   public static final int NUM_TYPES = 12;
/*  75 */   public static final String[] TARGET_NAMES = new String[] { "acl", "qop", "server", "initial state", "IP client", "dataflow client", "control message", "topic log", "schema", "multicast client", "multicast topic", "durable subscription" };
/*     */ 
/*     */   
/*     */   private int type;
/*     */ 
/*     */   
/*     */   private int index;
/*     */ 
/*     */   
/*     */   private String targetName;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MatchTarget(int type, String targetName) {
/*  89 */     this.type = type;
/*  90 */     this.targetName = targetName;
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
/*     */   public final int type() {
/* 107 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIndex(int index) {
/* 116 */     this.index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIndex() {
/* 124 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getTargetName() {
/* 132 */     return this.targetName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setISP(MatchTarget isp) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MatchTarget duplicate() {
/*     */     try {
/* 157 */       return (MatchTarget)clone();
/* 158 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 160 */       Assert.failure();
/* 161 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\MatchTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */