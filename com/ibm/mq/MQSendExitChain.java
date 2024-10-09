/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class MQSendExitChain
/*     */   extends MQExitChain
/*     */   implements MQSendExit
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSendExitChain.java";
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.MQSendExitChain", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSendExitChain.java");
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
/*     */   public MQSendExitChain() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.MQSendExitChain", "<init>()");
/*     */     }
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.MQSendExitChain", "<init>()");
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
/*     */   public MQSendExitChain(List<MQSendExit> collection) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.MQSendExitChain", "<init>(List<MQSendExit>)", new Object[] { collection });
/*     */     }
/*     */ 
/*     */     
/*  81 */     setExitChain(collection);
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.MQSendExitChain", "<init>(List<MQSendExit>)");
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
/*     */   public byte[] sendExit(MQChannelExit channelExitParms, MQChannelDefinition channelDefinition, byte[] agentBuffer) {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.mq.MQSendExitChain", "sendExit(MQChannelExit,MQChannelDefinition,byte [ ])", new Object[] { channelExitParms, channelDefinition, agentBuffer });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (this.internals == null || this.internals.isEmpty()) {
/*     */       
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.exit(this, "com.ibm.mq.MQSendExitChain", "sendExit(MQChannelExit,MQChannelDefinition,byte [ ])", agentBuffer, 1);
/*     */       }
/*     */       
/* 116 */       return agentBuffer;
/*     */     } 
/*     */     
/* 119 */     Enumeration<?> enumVar = this.internals.elements();
/* 120 */     channelExitParms.exitResponse = 0;
/* 121 */     while (enumVar.hasMoreElements() && channelExitParms.exitResponse == 0) {
/* 122 */       MQSendExit exit = (MQSendExit)enumVar.nextElement();
/* 123 */       exit.sendExit(channelExitParms, channelDefinition, agentBuffer);
/* 124 */       if (exit instanceof MQExternalSendExit) {
/* 125 */         this.reasonCode = ((MQExternalSendExit)exit).reasonCode;
/*     */       }
/*     */     } 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.MQSendExitChain", "sendExit(MQChannelExit,MQChannelDefinition,byte [ ])", agentBuffer, 2);
/*     */     }
/*     */     
/* 133 */     return agentBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExitChain(List<?> collection) {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.mq.MQSendExitChain", "setExitChain(List<?>)", "setter", collection);
/*     */     }
/*     */     
/* 147 */     if (collection != null) {
/*     */       
/* 149 */       Iterator<?> iterator = collection.iterator();
/* 150 */       while (iterator.hasNext()) {
/* 151 */         Object object = iterator.next();
/* 152 */         if (!(object instanceof MQSendExit)) {
/* 153 */           ClassCastException traceRet1 = new ClassCastException();
/*     */           
/* 155 */           if (Trace.isOn) {
/* 156 */             Trace.throwing(this, "com.ibm.mq.MQSendExitChain", "setExitChain(List<?>)", traceRet1);
/*     */           }
/*     */           
/* 159 */           throw traceRet1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 163 */     this.collection = new MQExitChain.ImmutableList(collection);
/* 164 */     this.internals = straighten(this.collection);
/* 165 */     this.internals = clumpen(this.internals);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector<Object> clumpen(List<?> collection) {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.MQSendExitChain", "clumpen(List<?>)", new Object[] { collection });
/*     */     }
/*     */     
/* 180 */     Vector<Object> vector = new Vector();
/* 181 */     Iterator<?> iterator = collection.iterator();
/* 182 */     MQExternalSendExit newExit = null;
/* 183 */     while (iterator.hasNext()) {
/* 184 */       Object object = iterator.next();
/* 185 */       if (newExit == null) {
/*     */         
/* 187 */         if (object instanceof MQExternalSendExit) {
/* 188 */           newExit = (MQExternalSendExit)object;
/*     */           continue;
/*     */         } 
/* 191 */         vector.add(object);
/*     */         
/*     */         continue;
/*     */       } 
/* 195 */       if (object instanceof MQExternalSendExit) {
/* 196 */         newExit = newExit.cloneAdd((MQExternalSendExit)object);
/*     */         continue;
/*     */       } 
/* 199 */       vector.add(newExit);
/* 200 */       newExit = null;
/* 201 */       vector.add(object);
/*     */     } 
/*     */ 
/*     */     
/* 205 */     if (newExit != null) {
/* 206 */       vector.add(newExit);
/*     */     }
/*     */     
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.MQSendExitChain", "clumpen(List<?>)", vector);
/*     */     }
/* 212 */     return vector;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQSendExitChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */