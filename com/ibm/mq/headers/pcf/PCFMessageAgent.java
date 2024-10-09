/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.MQQueueManager;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
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
/*     */ public class PCFMessageAgent
/*     */   extends PCFAgent
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessageAgent.java";
/*     */   static final String copyright = "Copyright (c) IBM Corp. 2000, 2004   All rights reserved.";
/*     */   
/*     */   static {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.data("com.ibm.mq.headers.pcf.PCFMessageAgent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessageAgent.java");
/*     */     }
/*     */   }
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
/*     */   public PCFMessageAgent() {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>()");
/*     */     }
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>()");
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
/*     */   public PCFMessageAgent(MQQueueManager qmanager) throws MQDataException {
/* 133 */     super(qmanager);
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>(MQQueueManager)", new Object[] { qmanager });
/*     */     }
/*     */     
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>(MQQueueManager)");
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
/*     */   public PCFMessageAgent(String host, int port, String channel) throws MQDataException {
/* 155 */     super(host, port, channel);
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>(String,int,String)", new Object[] { host, 
/* 158 */             Integer.valueOf(port), channel });
/*     */     }
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>(String,int,String)");
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
/*     */   public PCFMessageAgent(String qmanager) throws MQDataException {
/* 175 */     super(qmanager);
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>(String)", new Object[] { qmanager });
/*     */     }
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "<init>(String)");
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
/*     */   public synchronized PCFMessage[] send(PCFMessage request) throws PCFException, MQDataException, IOException {
/*     */     PCFMessageAgentResponseTracker tracker;
/*     */     PCFMessage response;
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", new Object[] { request });
/*     */     }
/*     */     
/* 204 */     if (this.adminQueue == null) {
/* 205 */       MQDataException traceRet1 = new MQDataException(2, 6124, this);
/*     */       
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 211 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 216 */     if (this.usePlatformSettings && this.qmanager_platform == 1) {
/* 217 */       request.header.setType(16);
/* 218 */       request.header.setVersion(3);
/* 219 */       tracker = PCFMessageAgentResponseTracker.getInstance(true);
/*     */     } else {
/* 221 */       tracker = PCFMessageAgentResponseTracker.getInstance(false);
/*     */     } 
/*     */ 
/*     */     
/* 225 */     int pcfCommand = request.getCommand();
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", "Sending PCF command: ", 
/* 228 */           Integer.valueOf(pcfCommand));
/*     */     }
/*     */     
/* 231 */     MQMessage message = setRequestMQMD(new MQMessage());
/* 232 */     request.write((DataOutput)message);
/*     */     try {
/* 234 */       this.adminQueue.put(message, this.pmo);
/*     */     }
/* 236 */     catch (Exception e) {
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", e, 1);
/*     */       }
/*     */       
/* 241 */       MQDataException traceRet2 = MQDataException.getMQDataException(e);
/* 242 */       if (Trace.isOn) {
/* 243 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet2, 2);
/*     */       }
/*     */       
/* 246 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 251 */     Vector<PCFMessage> v = new Vector<>();
/* 252 */     int exceptionReason = 3008;
/* 253 */     boolean failed = false;
/* 254 */     byte[] correlationId = Arrays.copyOf(message.correlationId, 24);
/*     */     
/*     */     do {
/* 257 */       message.messageId = null;
/* 258 */       message.encoding = this.encoding;
/* 259 */       message.characterSet = this.defaultCharacterSet;
/* 260 */       message.correlationId = Arrays.copyOf(correlationId, 24);
/*     */       
/*     */       try {
/* 263 */         this.replyQueue.get(message, this.gmo);
/*     */       }
/* 265 */       catch (Exception e) {
/* 266 */         if (Trace.isOn) {
/* 267 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", e, 2);
/*     */         }
/*     */         
/* 270 */         MQDataException mqe = MQDataException.getMQDataException(e);
/*     */         
/* 272 */         if (mqe.completionCode != 1 || mqe.reasonCode != 2120) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 281 */           if (Trace.isOn) {
/* 282 */             Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)mqe, 3);
/*     */           }
/*     */           
/* 285 */           throw mqe;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 290 */       v.addElement(response = new PCFMessage((DataInput)message));
/*     */       
/* 292 */       if (!this.check || response.getCompCode() != 2)
/* 293 */         continue;  if (response.getReason() == 3008) {
/*     */ 
/*     */ 
/*     */         
/* 297 */         failed = true;
/* 298 */       } else if (exceptionReason == 3008) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 304 */         exceptionReason = response.getReason();
/*     */       }
/*     */     
/*     */     }
/* 308 */     while (!tracker.isLast(response));
/*     */     PCFMessage[] responses;
/* 310 */     v.copyInto((Object[])(responses = new PCFMessage[v.size()]));
/*     */     
/* 312 */     if (failed) {
/* 313 */       PCFException traceRet3 = new PCFException(2, exceptionReason, responses);
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet3, 4);
/*     */       }
/*     */       
/* 318 */       throw traceRet3;
/* 319 */     }  if (this.check && responses.length == 1 && responses[0].getCompCode() == 2) {
/*     */       
/* 321 */       PCFException traceRet4 = new PCFException(2, responses[0].getReason(), responses);
/* 322 */       if (Trace.isOn) {
/* 323 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", (Throwable)traceRet4, 5);
/*     */       }
/*     */       
/* 326 */       throw traceRet4;
/*     */     } 
/*     */     
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage)", responses);
/*     */     }
/* 332 */     return responses;
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
/*     */   public PCFMessage[] send(PCFMessage request, boolean check) throws PCFException, MQDataException, IOException {
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage,boolean)", new Object[] { request, 
/* 355 */             Boolean.valueOf(check) });
/*     */     }
/* 357 */     this.check = check;
/*     */     
/* 359 */     PCFMessage[] traceRet1 = send(request);
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage,boolean)", traceRet1);
/*     */     }
/*     */     
/* 364 */     return traceRet1;
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
/*     */   @Deprecated
/*     */   public PCFMessage[] send(PCFMessage request, boolean check, boolean usePlatformSettings) throws PCFException, MQDataException, IOException {
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage,boolean,boolean)", new Object[] { request, 
/* 389 */             Boolean.valueOf(check), 
/* 390 */             Boolean.valueOf(usePlatformSettings) });
/*     */     }
/* 392 */     this.check = check;
/* 393 */     this.usePlatformSettings = usePlatformSettings;
/*     */     
/* 395 */     PCFMessage[] traceRet1 = send(request);
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "send(PCFMessage,boolean,boolean)", traceRet1);
/*     */     }
/*     */     
/* 400 */     return traceRet1;
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
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "setCheckResponses(boolean)", "setter", 
/* 413 */           Boolean.valueOf(option));
/*     */     }
/* 415 */     this.check = option;
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
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgent", "setUsePlatformSettings(boolean)", "setter", 
/* 430 */           Boolean.valueOf(option));
/*     */     }
/* 432 */     this.usePlatformSettings = option;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFMessageAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */