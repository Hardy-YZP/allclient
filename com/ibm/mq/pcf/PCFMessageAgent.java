/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.MQQueueManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class PCFMessageAgent
/*     */   extends PCFAgent
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFMessageAgent.java";
/*     */   
/*     */   static {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data("com.ibm.mq.pcf.PCFMessageAgent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFMessageAgent.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean check = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean usePlatformSettings = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PCFMessageAgent() {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>()");
/*     */     }
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>()");
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
/*     */   public PCFMessageAgent(MQQueueManager qmanager) throws MQException {
/* 138 */     super(qmanager);
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>(MQQueueManager)", new Object[] { qmanager });
/*     */     }
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>(MQQueueManager)");
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
/*     */   public PCFMessageAgent(String host, int port, String channel) throws MQException {
/* 160 */     super(host, port, channel);
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>(String,int,String)", new Object[] { host, 
/* 163 */             Integer.valueOf(port), channel });
/*     */     }
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>(String,int,String)");
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
/*     */   public PCFMessageAgent(String qmanager) throws MQException {
/* 180 */     super(qmanager);
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>(String)", new Object[] { qmanager });
/*     */     }
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "<init>(String)");
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
/*     */   public synchronized PCFMessage[] send(PCFMessage request) throws PCFException, MQException, IOException {
/*     */     PCFMessageAgentResponseTracker tracker;
/*     */     PCFMessage response;
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", new Object[] { request });
/*     */     }
/*     */     
/* 209 */     if (this.adminQueue == null) {
/* 210 */       MQException traceRet1 = new MQException(2, 6124, this);
/* 211 */       if (Trace.isOn) {
/* 212 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet1, 1);
/*     */       }
/* 214 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (this.usePlatformSettings && this.qmanager_platform == 1) {
/* 220 */       request.header.type = 16;
/* 221 */       request.header.version = 3;
/* 222 */       tracker = new PCFMessageAgentResponseTracker390();
/*     */     } else {
/* 224 */       tracker = new PCFMessageAgentResponseTrackerNon390();
/*     */     } 
/*     */ 
/*     */     
/* 228 */     int pcfCommand = request.getCommand();
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", "Sending PCF command: ", 
/* 231 */           Integer.valueOf(pcfCommand));
/*     */     }
/*     */     
/* 234 */     MQMessage message = setRequestMQMD(new MQMessage());
/*     */     
/* 236 */     request.write(message);
/* 237 */     this.adminQueue.put(message, this.pmo);
/*     */ 
/*     */ 
/*     */     
/* 241 */     Vector<PCFMessage> v = new Vector<>();
/* 242 */     int exceptionReason = 3008;
/* 243 */     boolean failed = false;
/*     */     
/* 245 */     byte[] correlationId = Arrays.copyOf(message.correlationId, 24);
/*     */     
/*     */     do {
/* 248 */       message.messageId = null;
/* 249 */       message.encoding = this.encoding;
/* 250 */       message.characterSet = this.defaultCharacterSet;
/* 251 */       message.correlationId = Arrays.copyOf(correlationId, 24);
/*     */       
/*     */       try {
/* 254 */         this.replyQueue.get(message, this.gmo);
/*     */       }
/* 256 */       catch (MQException mqe) {
/* 257 */         if (Trace.isOn) {
/* 258 */           Trace.catchBlock(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)mqe);
/*     */         }
/*     */         
/* 261 */         if (mqe.completionCode != 1 || mqe.reasonCode != 2120) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 269 */           if (Trace.isOn) {
/* 270 */             Trace.throwing(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)mqe, 2);
/*     */           }
/* 272 */           throw mqe;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 278 */       v.addElement(response = new PCFMessage(message));
/*     */       
/* 280 */       if (!this.check || response.getCompCode() != 2)
/* 281 */         continue;  if (response.getReason() == 3008) {
/*     */ 
/*     */ 
/*     */         
/* 285 */         failed = true;
/* 286 */       } else if (exceptionReason == 3008) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 292 */         exceptionReason = response.getReason();
/*     */       }
/*     */     
/*     */     }
/* 296 */     while (!tracker.isLast(response));
/*     */     PCFMessage[] responses;
/* 298 */     v.copyInto((Object[])(responses = new PCFMessage[v.size()]));
/*     */     
/* 300 */     if (failed) {
/* 301 */       PCFException traceRet2 = new PCFException(2, exceptionReason, responses);
/* 302 */       if (Trace.isOn) {
/* 303 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet2, 3);
/*     */       }
/* 305 */       throw traceRet2;
/* 306 */     }  if (this.check && responses.length == 1 && responses[0].getCompCode() == 2) {
/*     */       
/* 308 */       PCFException traceRet3 = new PCFException(2, responses[0].getReason(), responses);
/* 309 */       if (Trace.isOn) {
/* 310 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet3, 4);
/*     */       }
/*     */       
/* 313 */       throw traceRet3;
/*     */     } 
/*     */     
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage)", responses);
/*     */     }
/* 319 */     return responses;
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
/*     */   @Deprecated
/*     */   public PCFMessage[] send(PCFMessage request, boolean check) throws PCFException, MQException, IOException {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage,boolean)", new Object[] { request, 
/* 342 */             Boolean.valueOf(check) });
/*     */     }
/* 344 */     this.check = check;
/*     */     
/* 346 */     PCFMessage[] traceRet1 = send(request);
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage,boolean)", traceRet1);
/*     */     }
/* 350 */     return traceRet1;
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
/*     */   @Deprecated
/*     */   public PCFMessage[] send(PCFMessage request, boolean check, boolean usePlatformSettings) throws PCFException, MQException, IOException {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage,boolean,boolean)", new Object[] { request, 
/* 376 */             Boolean.valueOf(check), Boolean.valueOf(usePlatformSettings) });
/*     */     }
/* 378 */     this.check = check;
/* 379 */     this.usePlatformSettings = usePlatformSettings;
/*     */     
/* 381 */     PCFMessage[] traceRet1 = send(request);
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgent", "send(PCFMessage,boolean,boolean)", traceRet1);
/*     */     }
/*     */     
/* 386 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCheckResponses(boolean option) {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgent", "setCheckResponses(boolean)", "setter", 
/* 399 */           Boolean.valueOf(option));
/*     */     }
/* 401 */     this.check = option;
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
/*     */   public void setUsePlatformSettings(boolean option) {
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgent", "setUsePlatformSettings(boolean)", "setter", 
/* 416 */           Boolean.valueOf(option));
/*     */     }
/* 418 */     this.usePlatformSettings = option;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFMessageAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */