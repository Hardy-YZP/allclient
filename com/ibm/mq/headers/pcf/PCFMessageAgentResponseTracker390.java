/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PCFMessageAgentResponseTracker390
/*     */   extends PCFMessageAgentResponseTracker
/*     */ {
/*     */   static {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data("com.ibm.mq.headers.pcf.PCFMessageAgentResponseTracker390", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessageAgentResponseTracker.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 190 */   private final Set<String> set = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLast(PCFMessage response) throws MQDataException, IOException {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTracker390", "isLast(PCFMessage)", new Object[] { response });
/*     */     }
/*     */ 
/*     */     
/* 201 */     Enumeration<PCFParameter> e = response.getParameters();
/* 202 */     String current = null;
/*     */     
/* 204 */     while (e.hasMoreElements()) {
/* 205 */       PCFParameter p = e.nextElement();
/* 206 */       int id = p.getParameter();
/*     */       
/* 208 */       if (id == 7003) {
/* 209 */         this.set.add(p.getStringValue()); continue;
/* 210 */       }  if (id == 7004) {
/* 211 */         this.set.add(current = p.getStringValue());
/*     */       }
/*     */     } 
/*     */     
/* 215 */     if (response.getControl() == 1 && current != null) {
/* 216 */       this.set.remove(current);
/*     */     }
/*     */     
/* 219 */     boolean traceRet1 = (this.set.size() == 0);
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTracker390", "isLast(PCFMessage)", 
/* 222 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 224 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFMessageAgentResponseTracker390.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */