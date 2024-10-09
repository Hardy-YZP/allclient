/*     */ package com.ibm.msg.client.commonservices.commandmanager;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CSPCommandManager;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CommandHandler;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CommandInitiator;
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
/*     */ public class PICommandManager
/*     */   implements CSPCommandManager
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/commandmanager/PICommandManager.java";
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/commandmanager/PICommandManager.java");
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
/*     */   public void addCommandHandler(CommandHandler handler, String commandType) throws CSIException {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "addCommandHandler(CommandHandler,String)", new Object[] { handler, commandType });
/*     */     }
/*     */ 
/*     */     
/*  59 */     String msg = NLSServices.getMessage("JMSCS0002");
/*  60 */     CSIException thrown = new CSIException(msg);
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "addCommandHandler(CommandHandler,String)", (Throwable)thrown);
/*     */     }
/*     */     
/*  65 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCommandHandler(CommandHandler handler) throws CSIException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "removeCommandHandler(CommandHandler)", new Object[] { handler });
/*     */     }
/*     */     
/*  78 */     String msg = NLSServices.getMessage("JMSCS0002");
/*  79 */     CSIException thrown = new CSIException(msg);
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "removeCommandHandler(CommandHandler)", (Throwable)thrown);
/*     */     }
/*     */     
/*  84 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() throws CSIException {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "start()");
/*     */     }
/*     */     
/*  97 */     String msg = NLSServices.getMessage("JMSCS0002");
/*  98 */     CSIException thrown = new CSIException(msg);
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "start()", (Throwable)thrown);
/*     */     }
/*     */     
/* 103 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws CSIException {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "stop()");
/*     */     }
/*     */     
/* 116 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 117 */     CSIException thrown = new CSIException(msg);
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "stop()", (Throwable)thrown);
/*     */     }
/*     */     
/* 122 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runCommand(CommandInitiator initiator, Command command) throws Exception {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "runCommand(CommandInitiator,Command)", new Object[] { initiator, command });
/*     */     }
/*     */     
/* 136 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 137 */     CSIException thrown = new CSIException(msg);
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "runCommand(CommandInitiator,Command)", (Throwable)thrown);
/*     */     }
/*     */     
/* 142 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Command runCommand(Command command) throws Exception {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "runCommand(Command)", new Object[] { command });
/*     */     }
/*     */     
/* 155 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 156 */     CSIException thrown = new CSIException(msg);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.PICommandManager", "runCommand(Command)", (Throwable)thrown);
/*     */     }
/*     */     
/* 161 */     throw thrown;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\commandmanager\PICommandManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */