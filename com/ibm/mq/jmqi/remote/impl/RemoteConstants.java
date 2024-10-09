/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface RemoteConstants
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConstants.java";
/*     */   public static final int CONN_LOCAL = 1;
/*     */   public static final int CONN_BROKEN = 2;
/*     */   public static final int CONN_MQ_CONNECTED = 4;
/*     */   public static final int CONN_XA_CONNECTED = 8;
/*     */   public static final int CONN_DISCONNECTING = 16;
/*     */   public static final int CONN_DELETED = 32;
/*     */   public static final int CONN_STARTED = 64;
/*     */   public static final int CONN_SUSPENDED = 128;
/*     */   public static final int CONN_QUIESCING = 256;
/*     */   public static final int CONN_CONSUMERS_CHANGED = 512;
/*     */   public static final int CONN_EVENT_HANDLER_SUSPENDED = 1024;
/*     */   public static final int CONN_MQ_CONNECTING = 2048;
/*     */   public static final int CONN_MQ_CONNECT_WAITING = 4096;
/*     */   public static final int CONN_INCALL = 8192;
/*     */   public static final int CONN_INCALL_INTERNAL = 16384;
/*     */   public static final int CONN_CLEANUP = 32768;
/*     */   public static final int CONN_ASYNC_THREAD_AFFINITY = 65536;
/*     */   public static final int CONN_STOP_PENDING = 131072;
/*     */   public static final int CONN_START_PENDING = 524288;
/*     */   public static final int CONN_SUSPEND_PENDING = 1048576;
/*     */   public static final int CONN_TXN = 67108864;
/*     */   public static final int CONN_TXN_DOOMED = 134217728;
/*     */   public static final int maxMsgsPerCB = 5;
/*     */   public static final int rpqST_UNHEALTHY = 1;
/*     */   public static final int rpqST_GETTER = 2;
/*     */   public static final int rpqST_GETTER_SIGNALLED = 4;
/*     */   public static final int rpqST_GET_INHIBITED = 8;
/*     */   @Deprecated
/*     */   public static final int rpqST_TRANSACTIONAL_NEXT = 16;
/*     */   public static final int rpqST_BROWSE = 32;
/*     */   public static final int rpqST_MULTICAST = 64;
/*     */   public static final int rpqST_QUIESCED = 128;
/*     */   public static final int rpqST_QM_QUIESCING = 256;
/*     */   public static final int rpqST_SELECTION_SET = 512;
/*     */   public static final int rpqST_STREAMING = 1024;
/*     */   public static final int rpqST_REQUEST_MSG_NEEDED = 2048;
/*     */   public static final int rpqST_BROWSE_FIRST_WAIT = 4096;
/*     */   public static final int rpqST_STREAMING_PAUSED = 8192;
/*     */   public static final int rpqST_STREAMING_REQUESTED = 16384;
/*     */   public static final int rpqST_STREAMING_INHIBITED = 32768;
/*     */   public static final int rpqST_SELECTION_CHANGED = 65536;
/*     */   public static final int rpqST_INPUT = 131072;
/*     */   public static final int rpqST_SUSPENDED = 262144;
/*     */   public static final int rpqST_FUNCTION_LOADED = 1048576;
/*     */   public static final int rpqST_DEREGISTER_OUTSTANDING = 2097152;
/*     */   public static final int rpqST_START_CALLED = 4194304;
/*     */   public static final int rpqST_STOP_CALLED = 8388608;
/*     */   public static final int rpqST_CALLBACK_ON_EMPTY = 16777216;
/*     */   public static final int rpqST_MQGET_CALLED = 33554432;
/*     */   public static final int rpqST_MQGET_WAITING = 67108864;
/*     */   public static final int rpqST_MSG_COPIED = 134217728;
/*     */   public static final int rpqST_TRANSACTIONAL = 268435456;
/*     */   public static final int rpqST_CLOSED = 536870912;
/*     */   public static final int rpqST_STREAMING_TXN_PAUSED = 1073741824;
/*     */   public static final int rfpQS_STREAMING = 1;
/*     */   public static final int rfpQS_STREAMING_BACKLOG = 2;
/*     */   public static final int rfpQS_STREAMING_INHIBITED = 4;
/*     */   public static final int rfpVS_SUSPENDED = 1;
/*     */   public static final int rfpVS_STARTED = 2;
/*     */   public static final int rfpVS_START_WAIT = 4;
/*     */   public static final int rfpVS_REGISTERED = 16;
/*     */   public static final int rfpMS_STREAMING_PAUSED = 1;
/*     */   public static final int rfpMS_SEGMENTATION_ALLOWED = 2;
/*     */   public static final int rfpMS_SEGMENT = 4;
/*     */   public static final int rfpMS_LAST_SEGMENT = 8;
/*     */   public static final int rfpMS_MSG_IN_GROUP = 16;
/*     */   public static final int rfpMS_LAST_MSG_IN_GROUP = 32;
/*     */   public static final int rpqMS_MESSAGE = 1;
/*     */   public static final int rpqMS_TXN = 2;
/*     */   public static final int rfpRMS_MSG_PROPERTIES_REQUESTED = 32;
/*     */   public static final int rfpMS_MSG_PROPERTIES_RETURNED = 64;
/*     */   public static final int XA_NONE = 0;
/*     */   public static final int XA_OPENED = 2;
/*     */   public static final int XA_TRAN_ACTIVE = 4;
/*     */   public static final int syncRequestId = 0;
/*     */   public static final int asyncRequestId = 1;
/*     */   
/*     */   public enum Event
/*     */   {
/*  89 */     CONNECTION_BROKEN(2009, false),
/*     */     
/*  91 */     CONNECTION_QUIESCING(2202, false),
/*     */     
/*  93 */     CONNECTION_STOPPING(2203, false),
/*     */     
/*  95 */     QMGR_QUIESCING(2161, true),
/*     */     
/*  97 */     QMGR_STOPPING(2162, true);
/*     */     
/*     */     private boolean raiseReconnecting;
/*     */     private int reason;
/*     */     
/*     */     Event(int reason, boolean raiseReconnecting) {
/* 103 */       this.reason = reason;
/* 104 */       this.raiseReconnecting = raiseReconnecting;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getReason() {
/* 111 */       return this.reason;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean getRaiseReconnecting() {
/* 118 */       return this.raiseReconnecting;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static Event lookupByReason(int candidateReason) {
/* 127 */       for (Event ee : values()) {
/* 128 */         if (ee.reason == candidateReason) {
/* 129 */           return ee;
/*     */         }
/*     */       } 
/* 132 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 140 */       return String.format("%s: Reason: %d (%s) RaiseReconnecting: %b", new Object[] { name(), Integer.valueOf(this.reason), MQConstants.lookupReasonCode(this.reason), Boolean.valueOf(this.raiseReconnecting) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */