/*     */ package com.ibm.mq.jmqi.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
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
/*     */ public class QueueManagerInfo
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/QueueManagerInfo.java";
/*     */   public static final int ADVCAP_UNKNOWN = -1;
/*     */   private int commandLevel;
/*     */   private int platform;
/*     */   private int ccsid;
/*     */   private String name;
/*     */   private String uid;
/*     */   private String qsgName;
/*     */   private int advancedCapability;
/*     */   
/*     */   public QueueManagerInfo(JmqiEnvironment env) {
/*  43 */     super(env);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.qsgName = null;
/*  62 */     this.advancedCapability = -1;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "<init>(JmqiEnvironment)", new Object[] { env }); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "<init>(JmqiEnvironment)"); 
/*     */   }
/*     */   
/*     */   public int getAdvCap() {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getAdvCap()", "getter", 
/*  72 */           Integer.valueOf(this.advancedCapability));
/*     */     }
/*  74 */     return this.advancedCapability;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdvCap(int advCap) {
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setAdvCap(int)", "setter", 
/*  82 */           Integer.valueOf(advCap));
/*     */     }
/*  84 */     this.advancedCapability = advCap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQsgName() {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getQsgName()", "getter", this.qsgName);
/*     */     }
/*     */     
/*  95 */     return this.qsgName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQsgName(String qsgName) {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setQsgName(String)", "setter", qsgName);
/*     */     }
/*     */     
/* 106 */     this.qsgName = qsgName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommandLevel() {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getCommandLevel()", "getter", 
/* 115 */           Integer.valueOf(this.commandLevel));
/*     */     }
/* 117 */     return this.commandLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommandLevel(int commandLevel) {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setCommandLevel(int)", "setter", 
/* 126 */           Integer.valueOf(commandLevel));
/*     */     }
/* 128 */     this.commandLevel = commandLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getName()", "getter", this.name);
/*     */     }
/* 138 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setName(String)", "setter", name);
/*     */     }
/*     */     
/* 149 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlatform() {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getPlatform()", "getter", 
/* 158 */           Integer.valueOf(this.platform));
/*     */     }
/* 160 */     return this.platform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlatform(int platform) {
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setPlatform(int)", "setter", 
/* 169 */           Integer.valueOf(platform));
/*     */     }
/* 171 */     this.platform = platform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUid() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getUid()", "getter", this.uid);
/*     */     }
/* 182 */     return this.uid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUid(String uid) {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setUid(String)", "setter", uid);
/*     */     }
/*     */     
/* 193 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCcsid() {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "getCcsid()", "getter", 
/* 202 */           Integer.valueOf(this.ccsid));
/*     */     }
/* 204 */     return this.ccsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCcsid(int ccsid) {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.data(this, "com.ibm.mq.jmqi.internal.QueueManagerInfo", "setCcsid(int)", "setter", 
/* 213 */           Integer.valueOf(ccsid));
/*     */     }
/* 215 */     this.ccsid = ccsid;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\QueueManagerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */