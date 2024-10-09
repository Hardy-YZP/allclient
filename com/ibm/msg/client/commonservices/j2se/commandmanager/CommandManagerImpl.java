/*     */ package com.ibm.msg.client.commonservices.j2se.commandmanager;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.commandmanager.Command;
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
/*     */ public class CommandManagerImpl
/*     */   implements CSPCommandManager
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/commandmanager/CommandManagerImpl.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/commandmanager/CommandManagerImpl.java");
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
/*     */   public void addCommandHandler(CommandHandler handler, String commandType) {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "addCommandHandler(CommandHandler,String)", new Object[] { handler, commandType });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "addCommandHandler(CommandHandler,String)");
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
/*     */   public void removeCommandHandler(CommandHandler handler) {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "removeCommandHandler(CommandHandler)", new Object[] { handler });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "removeCommandHandler(CommandHandler)");
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
/*     */   public void start() {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "start()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "start()");
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
/*     */   public void stop() {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "stop()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "stop()");
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
/*     */   public Command runCommand(Command commandP) {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "runCommand(Command)", new Object[] { commandP });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "runCommand(Command)", commandP);
/*     */     }
/*     */     
/* 166 */     return commandP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runCommand(CommandInitiator initiator, Command command) {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "runCommand(CommandInitiator,Command)", new Object[] { initiator, command });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     if (Trace.isOn)
/* 188 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl", "runCommand(CommandInitiator,Command)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\commandmanager\CommandManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */