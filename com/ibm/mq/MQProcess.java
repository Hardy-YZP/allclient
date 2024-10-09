/*     */ package com.ibm.mq;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQProcess
/*     */   extends MQManagedObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQProcess.java";
/*     */   private static final int MQOT_PROCESS = 3;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.MQProcess", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQProcess.java");
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
/*  73 */   private Pint completionCode = new Pint();
/*  74 */   private Pint reason = new Pint();
/*     */   
/*  76 */   private MQSESSION osession = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQProcess() {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.mq.MQProcess", "<init>()");
/*     */     }
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.MQProcess", "<init>()");
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
/*     */   public MQProcess(MQQueueManager qMgr, String processName, int openOptions) throws MQException {
/* 119 */     this(qMgr, processName, openOptions, (String)null, (String)null);
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int)", new Object[] { qMgr, processName, 
/* 122 */             Integer.valueOf(openOptions) });
/*     */     }
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int)");
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
/*     */ 
/*     */   
/*     */   public MQProcess(MQQueueManager qMgr, String processName, int openOptionsP, String queueManagerName, String alternateUserId) throws MQException {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int,String,String)", new Object[] { qMgr, processName, 
/* 165 */             Integer.valueOf(openOptionsP), queueManagerName, alternateUserId });
/*     */     }
/*     */     
/* 168 */     String fid = "<init>(MQQueueManager,String,int,String,String)";
/*     */     
/* 170 */     int openOptions = openOptionsP;
/* 171 */     if (qMgr == null) {
/* 172 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI001");
/*     */ 
/*     */       
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.throwing(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int,String,String)", traceRet1, 1);
/*     */       }
/*     */       
/* 179 */       throw traceRet1;
/*     */     } 
/*     */     
/* 182 */     if (this.osession == null) {
/* 183 */       this.osession = qMgr.getSession();
/*     */     }
/*     */     
/* 186 */     if (!qMgr.connected || this.osession == null) {
/* 187 */       MQException traceRet2 = new MQException(2, 2018, this, "MQJI002");
/*     */       
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.throwing(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int,String,String)", traceRet2, 2);
/*     */       }
/*     */       
/* 193 */       throw traceRet2;
/*     */     } 
/*     */     
/* 196 */     MQOD od = new MQOD();
/* 197 */     od.ObjectType = 3;
/*     */     
/* 199 */     if (processName != null && processName.length() > 0) {
/* 200 */       od.ObjectName = processName;
/*     */     }
/*     */     
/* 203 */     if (queueManagerName != null && queueManagerName.length() > 0) {
/* 204 */       od.ObjectQMgrName = queueManagerName;
/*     */     }
/*     */     
/* 207 */     if (alternateUserId != null && alternateUserId.length() > 0) {
/* 208 */       od.AlternateUserId = alternateUserId;
/*     */     }
/*     */     
/* 211 */     this.Hconn = qMgr.Hconn;
/* 212 */     this.connected = qMgr.connected;
/*     */     
/* 214 */     openOptions |= 0x20;
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "<init>(MQQueueManager,String,int,String,String)", "process = " + od.ObjectName + "\nqueue manager = " + od.ObjectQMgrName + "\nalternate user id = " + od.AlternateUserId + "\noptions = " + openOptions, "");
/*     */     }
/*     */ 
/*     */     
/* 221 */     this.osession.MQOPEN(this.Hconn.getHconn(), od, openOptions, this.Hobj, this.completionCode, this.reason);
/*     */ 
/*     */     
/* 224 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 225 */       this.resourceOpen = false;
/*     */       
/* 227 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 228 */       qMgr.errorOccurred(mqe);
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.throwing(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int,String,String)", mqe, 3);
/*     */       }
/*     */       
/* 233 */       throw mqe;
/*     */     } 
/*     */ 
/*     */     
/* 237 */     this.resourceOpen = true;
/*     */     
/* 239 */     qMgr.registerProcess(this);
/*     */ 
/*     */     
/* 242 */     this.name = processName;
/* 243 */     this.openOptions = openOptions;
/*     */     
/* 245 */     this.isOpen = true;
/* 246 */     this.openStatus = true;
/*     */     
/* 248 */     this.parentQmgr = qMgr;
/* 249 */     this.connectionReference = qMgr;
/* 250 */     if (alternateUserId != null) {
/* 251 */       this.alternateUserId = alternateUserId;
/*     */     }
/* 253 */     this.mqca_description = 2011;
/*     */     
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.exit(this, "com.ibm.mq.MQProcess", "<init>(MQQueueManager,String,int,String,String)");
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
/*     */   public synchronized void close() throws MQException {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry(this, "com.ibm.mq.MQProcess", "close()");
/*     */     }
/* 272 */     super.close();
/* 273 */     if (this.connectionReference != null) {
/* 274 */       this.connectionReference.unregisterProcess(this);
/*     */     }
/* 276 */     this.connectionReference = null;
/*     */     
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.mq.MQProcess", "close()");
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
/*     */   public String getApplicationId() throws MQException {
/* 297 */     String traceRet1 = getString(2001, 256);
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.data(this, "com.ibm.mq.MQProcess", "getApplicationId()", "getter", traceRet1);
/*     */     }
/* 301 */     return traceRet1;
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
/*     */   public int getApplicationType() throws MQException {
/* 326 */     int traceRet1 = getInt(1);
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.mq.MQProcess", "getApplicationType()", "getter", 
/* 329 */           Integer.valueOf(traceRet1));
/*     */     }
/* 331 */     return traceRet1;
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
/*     */   public String getEnvironmentData() throws MQException {
/* 343 */     String traceRet1 = getString(2007, 128);
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.data(this, "com.ibm.mq.MQProcess", "getEnvironmentData()", "getter", traceRet1);
/*     */     }
/* 347 */     return traceRet1;
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
/*     */   public String getUserData() throws MQException {
/* 359 */     String traceRet1 = getString(2021, 128);
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.MQProcess", "getUserData()", "getter", traceRet1);
/*     */     }
/* 363 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */