/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQSD;
/*     */ import com.ibm.mq.jmqi.MQSRO;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQSubscription
/*     */   extends MQManagedObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSubscription.java";
/*     */   private MQTopic topic;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.MQSubscription", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSubscription.java");
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
/*     */   protected MQSubscription(MQTopic mqTopic, MQSD mqSubscriptionDescriptor) throws MQException {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "<init>(MQTopic,MQSD)", new Object[] { mqTopic, mqSubscriptionDescriptor });
/*     */     }
/*     */ 
/*     */     
/*  65 */     this.topic = mqTopic;
/*  66 */     this.parentQmgr = this.topic.parentQmgr;
/*  67 */     this.Hconn = this.parentQmgr.Hconn;
/*  68 */     this.connected = this.parentQmgr.connected;
/*  69 */     this.osession = this.parentQmgr.osession;
/*  70 */     setConnectionReference(this.parentQmgr);
/*  71 */     setName(mqSubscriptionDescriptor.getObjectString().getVsString());
/*  72 */     setOpenOptions(mqSubscriptionDescriptor.getOptions());
/*  73 */     setAlternateUserId(mqSubscriptionDescriptor.getAlternateUserId());
/*     */     
/*  75 */     subscribe(mqSubscriptionDescriptor);
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "<init>(MQTopic,MQSD)");
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
/*     */   protected void subscribe(MQSD mqSubscriptionDescriptor) throws MQException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "subscribe(MQSD)", new Object[] { mqSubscriptionDescriptor });
/*     */     }
/*     */ 
/*     */     
/*  96 */     Pint completionCode = new Pint();
/*  97 */     Pint reason = new Pint();
/*  98 */     JmqiMQ jmqi = this.osession.getJmqi();
/*  99 */     jmqi
/* 100 */       .MQSUB(this.Hconn.getHconn(), mqSubscriptionDescriptor, this.topic.Hobj, this.Hobj, completionCode, reason);
/*     */     
/* 102 */     if (completionCode.x != 0 || reason.x != 0) {
/* 103 */       this.resourceOpen = false;
/*     */       
/* 105 */       MQException exception = new MQException(completionCode.x, reason.x, this, this.osession.getLastJmqiException());
/* 106 */       this.parentQmgr.errorOccurred(exception);
/*     */       
/* 108 */       if (Trace.isOn) {
/* 109 */         Trace.throwing(this, "com.ibm.mq.MQSubscription", "subscribe(MQSD)", exception);
/*     */       }
/* 111 */       throw exception;
/*     */     } 
/* 113 */     this.resourceOpen = true;
/* 114 */     setOpen(true);
/*     */     
/* 116 */     setName(mqSubscriptionDescriptor.getResolvedObjectString().getVsString());
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "subscribe(MQSD)");
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
/*     */   public int requestPublicationUpdate(int options) throws MQException {
/* 155 */     if (Trace.isOn)
/* 156 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "requestPublicationUpdate(int)", new Object[] {
/* 157 */             Integer.valueOf(options)
/*     */           }); 
/* 159 */     Pint completionCode = new Pint();
/* 160 */     Pint reason = new Pint();
/* 161 */     JmqiMQ jmqi = this.osession.getJmqi();
/* 162 */     MQSRO mqsro = this.env.newMQSRO();
/* 163 */     mqsro.setOptions(options);
/* 164 */     jmqi.MQSUBRQ(this.Hconn.getHconn(), this.Hobj.getHobj(), 1, mqsro, completionCode, reason);
/*     */ 
/*     */     
/* 167 */     if (completionCode.x != 0 || reason.x != 0) {
/*     */       
/* 169 */       MQException exception = new MQException(completionCode.x, reason.x, this, this.osession.getLastJmqiException());
/* 170 */       this.parentQmgr.errorOccurred(exception);
/*     */       
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.throwing(this, "com.ibm.mq.MQSubscription", "requestPublicationUpdate(int)", exception);
/*     */       }
/*     */       
/* 176 */       throw exception;
/*     */     } 
/*     */     
/* 179 */     int traceRet1 = mqsro.getNumPublications();
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "requestPublicationUpdate(int)", 
/* 182 */           Integer.valueOf(traceRet1));
/*     */     }
/* 184 */     return traceRet1;
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
/*     */   public void inquire(int[] selectors, int[] intAttrs, byte[] charAttrs) throws MQException {
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "inquire(int [ ],int [ ],byte [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "inquire(int [ ],int [ ],byte [ ])");
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
/*     */   public synchronized void set(int[] selectors, int[] intAttrs, byte[] charAttrs) throws MQException {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "set(int [ ],int [ ],byte [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "set(int [ ],int [ ],byte [ ])");
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
/*     */   public String getAttributeString(int aSelector, int length) throws MQException {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "getAttributeString(int,int)", new Object[] {
/* 246 */             Integer.valueOf(aSelector), Integer.valueOf(length)
/*     */           });
/*     */     }
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "getAttributeString(int,int)", null);
/*     */     }
/* 253 */     return null;
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
/*     */   public void setAttributeString(int aSelector, String aValue, int length) throws MQException {
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.entry(this, "com.ibm.mq.MQSubscription", "setAttributeString(int,String,int)", new Object[] {
/* 269 */             Integer.valueOf(aSelector), aValue, Integer.valueOf(length)
/*     */           });
/*     */     }
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.MQSubscription", "setAttributeString(int,String,int)");
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
/*     */   public String getAlternateUserId() throws MQException {
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.mq.MQSubscription", "getAlternateUserId()", "getter", null);
/*     */     }
/* 291 */     return null;
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
/*     */   public String getDescription() throws MQException {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.data(this, "com.ibm.mq.MQSubscription", "getDescription()", "getter", null);
/*     */     }
/* 306 */     return null;
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
/*     */   public int getOpenOptions() throws MQException {
/* 318 */     int traceRet1 = -1;
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data(this, "com.ibm.mq.MQSubscription", "getOpenOptions()", "getter", 
/* 321 */           Integer.valueOf(traceRet1));
/*     */     }
/* 323 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */