/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum RebalancingApplicationType
/*     */ {
/*  36 */   NONE("NONE", "XMSC_WMQ_REBALANCING_APPLICATION_TYPE", 0),
/*     */   
/*  38 */   SIMPLE("SIMPLE", "XMSC_WMQ_REBALANCING_APPLICATION_TYPE", 0),
/*     */   
/*  40 */   REQUEST_REPLY("REQUEST_REPLY", "XMSC_WMQ_REBALANCING_APPLICATION_TYPE", 1),
/*     */   
/*  42 */   MANAGED("MANAGED", "XMSC_WMQ_REBALANCING_APPLICATION_TYPE", 65536);
/*     */   
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/RebalancingApplicationType.java";
/*     */   private String tag;
/*     */   private String property;
/*     */   private int propertyValue;
/*     */   private static HashMap<String, RebalancingApplicationType> lookupByTag;
/*     */   private static HashMap<Integer, RebalancingApplicationType> lookupByValue;
/*     */   
/*     */   RebalancingApplicationType(String tag, String property, int propertyValue) {
/*  52 */     this.tag = tag;
/*  53 */     this.property = property;
/*  54 */     this.propertyValue = propertyValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  60 */     lookupByTag = new HashMap<>();
/*  61 */     lookupByValue = new HashMap<>();
/*  62 */     for (RebalancingApplicationType e : values()) {
/*  63 */       lookupByTag.put(e.tag, e);
/*  64 */       lookupByValue.put(Integer.valueOf(e.propertyValue), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RebalancingApplicationType byTag(String tag) {
/*  73 */     return lookupByTag.get(tag.toUpperCase());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RebalancingApplicationType byValue(String value) {
/*  81 */     return lookupByValue.get(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperty(JmsPropertyContext context) throws JMSException {
/*  89 */     context.setIntProperty(this.property, this.propertyValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTag() {
/*  96 */     return this.tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyValue() {
/* 103 */     return this.propertyValue;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\RebalancingApplicationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */