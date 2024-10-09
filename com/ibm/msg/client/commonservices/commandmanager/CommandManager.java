/*     */ package com.ibm.msg.client.commonservices.commandmanager;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.CSIListener;
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CSPCommandManager;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CommandHandler;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CommandInitiator;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CommandManager
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/commandmanager/CommandManager.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/commandmanager/CommandManager.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   static CSPCommandManager functionalCommandManager = null;
/*     */   static boolean initialized = false;
/*     */   static boolean listening = false;
/*     */   
/*     */   static {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "static()");
/*     */     }
/*     */     
/*     */     try {
/*  66 */       initialize();
/*     */     }
/*  68 */     catch (CSIException csie) {
/*  69 */       if (Trace.isOn) {
/*  70 */         Trace.catchBlock("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "static()", (Throwable)csie);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCommandHandler(CommandHandler handler, String commandType) throws CSIException {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "addCommandHandler(CommandHandler,String)", new Object[] { handler, commandType });
/*     */     }
/*     */     
/*  93 */     functionalCommandManager.addCommandHandler(handler, commandType);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "addCommandHandler(CommandHandler,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeCommandHandler(CommandHandler handler) throws CSIException {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "removeCommandHandler(CommandHandler)", new Object[] { handler });
/*     */     }
/*     */     
/* 110 */     functionalCommandManager.removeCommandHandler(handler);
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "removeCommandHandler(CommandHandler)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void start() throws CSIException {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "start()");
/*     */     }
/* 125 */     functionalCommandManager.start();
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stop() throws CSIException {
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "stop()");
/*     */     }
/* 139 */     functionalCommandManager.stop();
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "stop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void runCommand(CommandInitiator initiator, Command command) throws Exception {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "runCommand(CommandInitiator,Command)", new Object[] { initiator, command });
/*     */     }
/*     */     
/* 156 */     functionalCommandManager.runCommand(initiator, command);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "runCommand(CommandInitiator,Command)");
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
/*     */   public static Command runCommand(Command command) throws Exception {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "runCommand(Command)", new Object[] { command });
/*     */     }
/*     */     
/* 174 */     Command traceRet1 = functionalCommandManager.runCommand(command);
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "runCommand(Command)", traceRet1);
/*     */     }
/*     */     
/* 179 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() throws CSIException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "initialize()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 192 */     if (initialized) {
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "initialize()", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 202 */       functionalCommandManager = CommonServices.getCommandManager();
/* 203 */       functionalCommandManager.start();
/* 204 */       initialized = true;
/*     */     }
/* 206 */     catch (CSIException e) {
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.catchBlock("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "initialize()", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 212 */       if (!listening) {
/*     */ 
/*     */         
/* 215 */         functionalCommandManager = new PICommandManager();
/*     */ 
/*     */ 
/*     */         
/* 219 */         CSIListener listener = new CSIListener()
/*     */           {
/*     */             public void onCSIInitialize()
/*     */             {
/* 223 */               if (Trace.isOn) {
/* 224 */                 Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.CommandManager", "onCSIInitialize()");
/*     */               }
/*     */               
/*     */               try {
/* 228 */                 CommandManager.initialize();
/* 229 */                 CommandManager.listening = false;
/* 230 */                 CommonServices.removeCSIListener(this);
/*     */               }
/* 232 */               catch (CSIException csie) {
/* 233 */                 if (Trace.isOn) {
/* 234 */                   Trace.catchBlock(this, "com.ibm.msg.client.commonservices.commandmanager.null", "onCSIInitialize()", (Throwable)csie);
/*     */                 }
/*     */ 
/*     */                 
/* 238 */                 HashMap<String, CSIException> hash = new HashMap<>();
/* 239 */                 hash.put("Exception", csie);
/*     */                 
/* 241 */                 Trace.ffst(this, "onCSIInitialize", "Failed to initialize CSI from CommandManager listener", hash, CSIException.class);
/*     */               } 
/*     */ 
/*     */               
/* 245 */               if (Trace.isOn) {
/* 246 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.commandmanager.null", "onCSIInitialize()");
/*     */               }
/*     */             }
/*     */           };
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 254 */         CommonServices.addCSIListener(listener);
/*     */         
/* 256 */         listening = true;
/*     */       } 
/*     */       
/* 259 */       if (Trace.isOn) {
/* 260 */         Trace.throwing("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "initialize()", (Throwable)e);
/*     */       }
/*     */       
/* 263 */       throw e;
/*     */     } 
/* 265 */     if (Trace.isOn)
/* 266 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.CommandManager", "initialize()", 2); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\commandmanager\CommandManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */