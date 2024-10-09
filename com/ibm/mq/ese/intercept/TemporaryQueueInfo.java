/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class TemporaryQueueInfo
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/TemporaryQueueInfo.java";
/*     */   private Hconn hconn;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.ese.intercept.TemporaryQueueInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/TemporaryQueueInfo.java");
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
/*  59 */   private String qmgrName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SmqiObject modelQinfo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private Set<Hobj> childHobjs = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TemporaryQueueInfo(Hconn hconn, String qmgr, SmqiObject qinfo) {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "<init>(Hconn,String,SmqiObject)", new Object[] { hconn, qmgr, qinfo });
/*     */     }
/*     */     
/*  86 */     this.hconn = hconn;
/*  87 */     this.qmgrName = qmgr;
/*  88 */     this.modelQinfo = qinfo;
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "<init>(Hconn,String,SmqiObject)");
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
/*     */   public String toString() {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "toString()");
/*     */     }
/* 106 */     StringBuilder buffer = new StringBuilder(97);
/* 107 */     buffer.append(this.modelQinfo.getResolvedName()).append('@')
/* 108 */       .append(this.qmgrName);
/* 109 */     String traceRet1 = buffer.toString();
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "toString()", traceRet1);
/*     */     }
/* 113 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChildHobj(Hobj openedHobj) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "addChildHobj(Hobj)", new Object[] { openedHobj });
/*     */     }
/*     */     
/* 126 */     this.childHobjs.add(openedHobj);
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "addChildHobj(Hobj)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQmgrName() {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.data(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "getQmgrName()", "getter", this.qmgrName);
/*     */     }
/*     */     
/* 142 */     return this.qmgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SmqiObject getModelQinfo() {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.data(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "getModelQinfo()", "getter", this.modelQinfo);
/*     */     }
/*     */     
/* 155 */     return this.modelQinfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Hobj> getChildHobjs() {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.data(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "getChildHobjs()", "getter", this.childHobjs);
/*     */     }
/*     */     
/* 167 */     return this.childHobjs;
/*     */   }
/*     */   
/*     */   public Hconn getHconn() {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.mq.ese.intercept.TemporaryQueueInfo", "getHconn()", "getter", this.hconn);
/*     */     }
/*     */     
/* 175 */     return this.hconn;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\TemporaryQueueInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */