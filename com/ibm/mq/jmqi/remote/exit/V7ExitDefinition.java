/*     */ package com.ibm.mq.jmqi.remote.exit;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.exits.MQCXP;
/*     */ import com.ibm.mq.exits.WMQReceiveExit;
/*     */ import com.ibm.mq.exits.WMQSecurityExit;
/*     */ import com.ibm.mq.exits.WMQSendExit;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class V7ExitDefinition
/*     */   extends JavaExitDefinition
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/V7ExitDefinition.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/V7ExitDefinition.java");
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
/*     */   private V7ExitDefinition(JmqiEnvironment env, RemoteExitChain parent, String exitName, String exitUserData, boolean mqcdExit, int exitSpace, Object exitObject) {
/*  61 */     super(env, parent, exitName, exitUserData, mqcdExit, exitSpace, exitObject);
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int,Object)", new Object[] { env, parent, exitName, exitUserData, 
/*     */             
/*  65 */             Boolean.valueOf(mqcdExit), Integer.valueOf(exitSpace), exitObject });
/*     */     }
/*     */     
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int,Object)");
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
/*     */   static boolean isLoadable(Object exitObject) {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "isLoadable(Object)", new Object[] { exitObject });
/*     */     }
/*     */     
/*  86 */     boolean traceRet1 = (exitObject instanceof WMQSendExit || exitObject instanceof WMQSecurityExit || exitObject instanceof WMQReceiveExit);
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "isLoadable(Object)", 
/*  90 */           Boolean.valueOf(traceRet1));
/*     */     }
/*  92 */     return traceRet1;
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
/*     */   static void load(JmqiEnvironment env, RemoteExitChain parent, Object exitObject, String exitUserData, RemoteConnection connection, boolean mqcdExit) {
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,boolean)", new Object[] { env, parent, exitObject, exitUserData, connection, 
/*     */             
/* 110 */             Boolean.valueOf(mqcdExit) });
/*     */     }
/*     */     
/* 113 */     ExitDefinition exitDef = new V7ExitDefinition(env, parent, RemoteExitChain.padToLength(exitObject.getClass().getName(), JmqiEnvironment.getMqExitNameLength()), exitUserData, mqcdExit, 0, exitObject);
/*     */     
/* 115 */     parent.addExitDefinition(exitDef);
/*     */ 
/*     */ 
/*     */     
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object invoke(JmqiTls tls, RfpTSH tsh, MQCD cd, MQCXP mqcxp, byte[] mqcspStruct, boolean securityFirstInvocation) throws JmqiException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", new Object[] { tls, tsh, cd, mqcxp, mqcspStruct, 
/*     */             
/* 134 */             Boolean.valueOf(securityFirstInvocation) });
/*     */     }
/* 136 */     ByteBuffer exitBuffer = null;
/* 137 */     ByteBuffer retBuffer = null;
/* 138 */     if (tsh != null)
/* 139 */       exitBuffer = prepareBuffer(tsh, false, 0);  try {
/*     */       WMQSecurityExit secExit; WMQSendExit sendExit;
/*     */       WMQReceiveExit receiveExit;
/* 142 */       switch (this.exitType) {
/*     */         case 11:
/* 144 */           secExit = (WMQSecurityExit)this.object;
/* 145 */           retBuffer = secExit.channelSecurityExit(mqcxp, cd, exitBuffer);
/*     */           break;
/*     */         case 13:
/* 148 */           sendExit = (WMQSendExit)this.object;
/* 149 */           retBuffer = sendExit.channelSendExit(mqcxp, cd, exitBuffer);
/*     */           break;
/*     */         case 14:
/* 152 */           receiveExit = (WMQReceiveExit)this.object;
/* 153 */           retBuffer = receiveExit.channelReceiveExit(mqcxp, cd, exitBuffer);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/* 159 */     } catch (Exception e) {
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", e);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { getExitName() }, 2, 2407, e);
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 174 */       throw traceRet1;
/*     */     } 
/*     */     
/* 177 */     if (retBuffer != null) {
/* 178 */       retBuffer.position(retBuffer.limit());
/*     */     }
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", retBuffer);
/*     */     }
/*     */     
/* 184 */     return retBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "toString()");
/*     */     }
/* 195 */     String traceRet1 = super.toString() + "\nV7 Java Exit: " + this.object.getClass().getName();
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V7ExitDefinition", "toString()", traceRet1);
/*     */     }
/* 199 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\exit\V7ExitDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */