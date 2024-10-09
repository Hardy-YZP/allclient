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
/*     */ public class MQReceiveExitChain
/*     */   extends MQExitChain
/*     */   implements MQReceiveExit
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQReceiveExitChain.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.MQReceiveExitChain", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQReceiveExitChain.java");
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
/*     */   public MQReceiveExitChain() {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.entry(this, "com.ibm.mq.MQReceiveExitChain", "<init>()");
/*     */     }
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.exit(this, "com.ibm.mq.MQReceiveExitChain", "<init>()");
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
/*     */   public MQReceiveExitChain(List collection) {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.mq.MQReceiveExitChain", "<init>(List)", new Object[] { collection });
/*     */     }
/*     */ 
/*     */     
/*  78 */     setExitChain(collection);
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.MQReceiveExitChain", "<init>(List)");
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
/*     */   
/*     */   public byte[] receiveExit(MQChannelExit channelExitParms, MQChannelDefinition channelDefinition, byte[] agentBuffer) {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.MQReceiveExitChain", "receiveExit(MQChannelExit,MQChannelDefinition,byte [ ])", new Object[] { channelExitParms, channelDefinition, agentBuffer });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (this.internals == null || this.internals.isEmpty()) {
/*     */       
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.exit(this, "com.ibm.mq.MQReceiveExitChain", "receiveExit(MQChannelExit,MQChannelDefinition,byte [ ])", agentBuffer, 1);
/*     */       }
/*     */       
/* 114 */       return agentBuffer;
/*     */     } 
/*     */     
/* 117 */     Enumeration<?> enumVar = this.internals.elements();
/* 118 */     channelExitParms.exitResponse = 0;
/* 119 */     while (enumVar.hasMoreElements() && channelExitParms.exitResponse == 0) {
/* 120 */       MQReceiveExit exit = (MQReceiveExit)enumVar.nextElement();
/* 121 */       exit.receiveExit(channelExitParms, channelDefinition, agentBuffer);
/* 122 */       if (exit instanceof MQExternalReceiveExit) {
/* 123 */         this.reasonCode = ((MQExternalReceiveExit)exit).reasonCode;
/*     */       }
/*     */     } 
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.MQReceiveExitChain", "receiveExit(MQChannelExit,MQChannelDefinition,byte [ ])", agentBuffer, 2);
/*     */     }
/*     */     
/* 131 */     return agentBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExitChain(List collection) {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.data(this, "com.ibm.mq.MQReceiveExitChain", "setExitChain(List)", "setter", collection);
/*     */     }
/*     */     
/* 145 */     if (collection != null) {
/*     */       
/* 147 */       Iterator iterator = collection.iterator();
/* 148 */       while (iterator.hasNext()) {
/* 149 */         Object object = iterator.next();
/* 150 */         if (!(object instanceof MQReceiveExit)) {
/* 151 */           ClassCastException traceRet1 = new ClassCastException();
/*     */           
/* 153 */           if (Trace.isOn) {
/* 154 */             Trace.throwing(this, "com.ibm.mq.MQReceiveExitChain", "setExitChain(List)", traceRet1);
/*     */           }
/*     */           
/* 157 */           throw traceRet1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 161 */     this.collection = new MQExitChain.ImmutableList(collection);
/* 162 */     this.internals = straighten(this.collection);
/* 163 */     this.internals = clumpen(this.internals);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector clumpen(List collection) {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.entry(this, "com.ibm.mq.MQReceiveExitChain", "clumpen(List)", new Object[] { collection });
/*     */     }
/*     */     
/* 178 */     Vector<Object> vector = new Vector();
/* 179 */     Iterator iterator = collection.iterator();
/* 180 */     MQExternalReceiveExit newExit = null;
/* 181 */     while (iterator.hasNext()) {
/* 182 */       Object object = iterator.next();
/* 183 */       if (newExit == null) {
/*     */         
/* 185 */         if (object instanceof MQExternalReceiveExit) {
/* 186 */           newExit = (MQExternalReceiveExit)object;
/*     */           
/*     */           continue;
/*     */         } 
/* 190 */         vector.add(object);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 195 */       if (object instanceof MQExternalReceiveExit) {
/* 196 */         newExit = newExit.cloneAdd((MQExternalReceiveExit)object);
/*     */         
/*     */         continue;
/*     */       } 
/* 200 */       vector.add(newExit);
/* 201 */       newExit = null;
/* 202 */       vector.add(object);
/*     */     } 
/*     */ 
/*     */     
/* 206 */     if (newExit != null) {
/* 207 */       vector.add(newExit);
/*     */     }
/*     */     
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.MQReceiveExitChain", "clumpen(List)", vector);
/*     */     }
/* 213 */     return vector;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQReceiveExitChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */