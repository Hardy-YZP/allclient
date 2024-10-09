/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface WMQConsumerOwner
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQConsumerOwner.java";
/*     */   
/*     */   WMQHobjCache getHobjCache();
/*     */   
/*     */   void addAsyncConsumer() throws JMSException;
/*     */   
/*     */   void decrementCloseCounter();
/*     */   
/*     */   int getCloseCounter();
/*     */   
/*     */   int getAckMode();
/*     */   
/*     */   WMQCommonConnection getConnection();
/*     */   
/*     */   Hconn getHconn();
/*     */   
/*     */   void lockHconn();
/*     */   
/*     */   void unlockHconn();
/*     */   
/*     */   void awaitHconn() throws InterruptedException;
/*     */   
/*     */   void signalHconn();
/*     */   
/*     */   boolean haveHconnLock();
/*     */   
/*     */   JmqiEnvironment getJmqiEnvironment();
/*     */   
/*     */   JmqiMQ getJmqiMQ();
/*     */   
/*     */   boolean getTransacted();
/*     */   
/*     */   void incrementCloseCounter();
/*     */   
/*     */   boolean isAsyncRunning();
/*     */   
/*     */   boolean isSubscriptionInUse(String paramString);
/*     */   
/*     */   void removeAsyncConsumer() throws JMSException;
/*     */   
/*     */   void removeSubscription(String paramString);
/*     */   
/*     */   void resumeAsyncService() throws JMSException;
/*     */   
/*     */   boolean suspendAsyncService() throws JMSException;
/*     */   
/*     */   void stop() throws JMSException;
/*     */   
/*     */   void syncpoint(boolean paramBoolean1, boolean paramBoolean2, WMQDestination paramWMQDestination) throws JMSException;
/*     */   
/*     */   void operationPerformed(Operation paramOperation, boolean paramBoolean);
/*     */   
/*     */   int getIntProperty(String paramString) throws JMSException;
/*     */   
/*     */   WMQThreadLocalStorage getThreadLocalStorage();
/*     */   
/*     */   public static final class Operation
/*     */   {
/* 202 */     public static final Operation ASYNCPUT = new Operation();
/* 203 */     public static final Operation GET = new Operation();
/* 204 */     public static final Operation SYNCPUT = new Operation();
/*     */     
/*     */     public boolean isAnyPut() {
/* 207 */       boolean traceRet1 = (equals(SYNCPUT) || equals(ASYNCPUT));
/* 208 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 217 */       if (equals(SYNCPUT)) {
/* 218 */         return "SYNCPUT";
/*     */       }
/* 220 */       if (equals(ASYNCPUT)) {
/* 221 */         return "ASYNCPUT";
/*     */       }
/* 223 */       if (equals(GET)) {
/* 224 */         return "GET";
/*     */       }
/* 226 */       return "UNLUCKY";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQConsumerOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */