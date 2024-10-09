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
/*     */ public class MQExternalSendExit
/*     */   extends MQExternalUserExit
/*     */   implements MQSendExit
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQExternalSendExit.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.MQExternalSendExit", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQExternalSendExit.java");
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
/*     */   public MQExternalSendExit() {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.MQExternalSendExit", "<init>()");
/*     */     }
/*     */     
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.MQExternalSendExit", "<init>()");
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
/*     */   public MQExternalSendExit(String libraryName, String entryPointName, String userData) {
/*  77 */     super(libraryName, entryPointName, userData);
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.MQExternalSendExit", "<init>(String,String,String)", new Object[] { libraryName, entryPointName, userData });
/*     */     }
/*     */ 
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.MQExternalSendExit", "<init>(String,String,String)");
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
/*     */   private MQExternalSendExit(String[] exitLibraries, String[] exitEntries, String[] dataStrings) {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.MQExternalSendExit", "<init>(String [ ],String [ ],String [ ])", new Object[] { exitLibraries, exitEntries, dataStrings });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 105 */     this.exitLibraries = exitLibraries;
/* 106 */     this.exitEntries = exitEntries;
/* 107 */     this.dataStrings = dataStrings;
/*     */     
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.mq.MQExternalSendExit", "<init>(String [ ],String [ ],String [ ])");
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
/*     */   public byte[] sendExit(MQChannelExit exitParms, MQChannelDefinition channelParms, byte[] data) {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.mq.MQExternalSendExit", "sendExit(MQChannelExit,MQChannelDefinition,byte [ ])", new Object[] { exitParms, channelParms, data });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 134 */     byte[] buffer = userExit(exitParms, channelParms, data);
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.MQExternalSendExit", "sendExit(MQChannelExit,MQChannelDefinition,byte [ ])", buffer);
/*     */     }
/*     */     
/* 140 */     return buffer;
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
/*     */   MQExternalSendExit cloneAdd(MQExternalSendExit exit) {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.mq.MQExternalSendExit", "cloneAdd(MQExternalSendExit)", new Object[] { exit });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 159 */     String[] newLibraries = augment(this.exitLibraries, exit.getLibraryName());
/* 160 */     String[] newEntries = augment(this.exitEntries, exit.getEntryPointName());
/* 161 */     String[] newData = augment(this.dataStrings, exit.getUserData());
/*     */     
/* 163 */     MQExternalSendExit newExit = new MQExternalSendExit(newLibraries, newEntries, newData);
/*     */     
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "com.ibm.mq.MQExternalSendExit", "cloneAdd(MQExternalSendExit)", newExit);
/*     */     }
/* 168 */     return newExit;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQExternalSendExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */