/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQAIR;
/*     */ import com.ibm.mq.jmqi.system.internal.CCDT;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteCCDT
/*     */   extends CCDT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteCCDT.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteCCDT.java");
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
/*     */   public RemoteCCDT(JmqiEnvironment env, URL url) throws JmqiException {
/*  62 */     super(env, url, true);
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "<init>(JmqiEnvironment,URL)", new Object[] { env, url });
/*     */     }
/*     */     
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "<init>(JmqiEnvironment,URL)");
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
/*     */   public MQAIR[] getAuthInfoRecords(int authInfoType) throws JmqiException {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "getAuthInfoRecords(int)", new Object[] {
/*  83 */             Integer.valueOf(authInfoType)
/*     */           });
/*     */     }
/*     */     
/*  87 */     int numRecords = 0;
/*  88 */     ArrayList<MQAIR> matchingRecords = new ArrayList<>();
/*  89 */     synchronized (this.listLock) {
/*     */       
/*  91 */       for (MQAIR authInfoRecord : this.parser.getAuthInfoRecords()) {
/*     */         
/*  93 */         if (authInfoRecord.getAuthInfoType() == authInfoType) {
/*  94 */           matchingRecords.add(authInfoRecord);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     MQAIR[] sizedRecords = null;
/* 100 */     if (numRecords > 0) {
/* 101 */       sizedRecords = new MQAIR[numRecords];
/* 102 */       sizedRecords = matchingRecords.<MQAIR>toArray(sizedRecords);
/*     */     } 
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteCCDT", "getAuthInfoRecords(int)", sizedRecords);
/*     */     }
/*     */     
/* 109 */     return sizedRecords;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteCCDT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */