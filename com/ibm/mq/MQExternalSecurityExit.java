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
/*     */ public class MQExternalSecurityExit
/*     */   extends MQExternalUserExit
/*     */   implements MQSecurityExit
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQExternalSecurityExit.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.MQExternalSecurityExit", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQExternalSecurityExit.java");
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
/*     */   public MQExternalSecurityExit() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.MQExternalSecurityExit", "<init>()");
/*     */     }
/*     */     
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.mq.MQExternalSecurityExit", "<init>()");
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
/*     */   public MQExternalSecurityExit(String libraryName, String entryPointName, String userData) {
/*  78 */     super(libraryName, entryPointName, userData);
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.MQExternalSecurityExit", "<init>(String,String,String)", new Object[] { libraryName, entryPointName, userData });
/*     */     }
/*     */ 
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.mq.MQExternalSecurityExit", "<init>(String,String,String)");
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
/*     */   public byte[] securityExit(MQChannelExit exitParms, MQChannelDefinition channelParms, byte[] data) {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.MQExternalSecurityExit", "securityExit(MQChannelExit,MQChannelDefinition,byte [ ])", new Object[] { exitParms, channelParms, data });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     byte[] buffer = userExit(exitParms, channelParms, data);
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.MQExternalSecurityExit", "securityExit(MQChannelExit,MQChannelDefinition,byte [ ])", buffer);
/*     */     }
/*     */     
/* 114 */     return buffer;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQExternalSecurityExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */