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
/*     */ public class MQExternalReceiveExit
/*     */   extends MQExternalUserExit
/*     */   implements MQReceiveExit
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQExternalReceiveExit.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.MQExternalReceiveExit", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQExternalReceiveExit.java");
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
/*     */   public MQExternalReceiveExit() {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.MQExternalReceiveExit", "<init>()");
/*     */     }
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.MQExternalReceiveExit", "<init>()");
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
/*     */   public MQExternalReceiveExit(String libraryName, String entryPointName, String userData) {
/*  76 */     super(libraryName, entryPointName, userData);
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.MQExternalReceiveExit", "<init>(String,String,String)", new Object[] { libraryName, entryPointName, userData });
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.MQExternalReceiveExit", "<init>(String,String,String)");
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
/*     */   private MQExternalReceiveExit(String[] exitLibraries, String[] exitEntries, String[] dataStrings) {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.MQExternalReceiveExit", "<init>(String [ ],String [ ],String [ ])", new Object[] { exitLibraries, exitEntries, dataStrings });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.exitLibraries = exitLibraries;
/* 105 */     this.exitEntries = exitEntries;
/* 106 */     this.dataStrings = dataStrings;
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.MQExternalReceiveExit", "<init>(String [ ],String [ ],String [ ])");
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
/*     */   public byte[] receiveExit(MQChannelExit exitParms, MQChannelDefinition channelParms, byte[] data) {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.mq.MQExternalReceiveExit", "receiveExit(MQChannelExit,MQChannelDefinition,byte [ ])", new Object[] { exitParms, channelParms, data });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 133 */     byte[] buffer = userExit(exitParms, channelParms, data);
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.MQExternalReceiveExit", "receiveExit(MQChannelExit,MQChannelDefinition,byte [ ])", buffer);
/*     */     }
/*     */     
/* 139 */     return buffer;
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
/*     */   MQExternalReceiveExit cloneAdd(MQExternalReceiveExit exit) {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.MQExternalReceiveExit", "cloneAdd(MQExternalReceiveExit)", new Object[] { exit });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 157 */     String[] newLibraries = augment(this.exitLibraries, exit.getLibraryName());
/* 158 */     String[] newEntries = augment(this.exitEntries, exit.getEntryPointName());
/* 159 */     String[] newData = augment(this.dataStrings, exit.getUserData());
/*     */     
/* 161 */     MQExternalReceiveExit newExit = new MQExternalReceiveExit(newLibraries, newEntries, newData);
/*     */     
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.MQExternalReceiveExit", "cloneAdd(MQExternalReceiveExit)", newExit);
/*     */     }
/*     */     
/* 167 */     return newExit;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQExternalReceiveExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */