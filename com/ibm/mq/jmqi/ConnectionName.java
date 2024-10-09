/*     */ package com.ibm.mq.jmqi;
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
/*     */ public class ConnectionName
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/ConnectionName.java";
/*     */   private String machine;
/*     */   private int port;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.ConnectionName", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/ConnectionName.java");
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
/*     */   public ConnectionName(String string, int defaultPort) {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.entry(this, "com.ibm.mq.jmqi.ConnectionName", "<init>(String,int)", new Object[] { string, 
/*  55 */             Integer.valueOf(defaultPort) });
/*     */     }
/*     */     
/*  58 */     this.machine = string;
/*  59 */     this.port = defaultPort;
/*     */     
/*  61 */     int first = string.indexOf('(');
/*     */     
/*  63 */     if (first > -1) {
/*     */       
/*  65 */       this.machine = string.substring(0, first).trim();
/*     */       
/*  67 */       int last = string.indexOf(')', first);
/*  68 */       if (last > first) {
/*  69 */         String portName = string.substring(first + 1, last);
/*  70 */         this.port = Integer.parseInt(portName);
/*     */       } 
/*     */     } 
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit(this, "com.ibm.mq.jmqi.ConnectionName", "<init>(String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMachine() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data(this, "com.ibm.mq.jmqi.ConnectionName", "getMachine()", "getter", this.machine);
/*     */     }
/*  86 */     return this.machine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMachine(String machine) {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.data(this, "com.ibm.mq.jmqi.ConnectionName", "setMachine(String)", "setter", machine);
/*     */     }
/*  96 */     this.machine = machine.trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPort() {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.mq.jmqi.ConnectionName", "getPort()", "getter", 
/* 105 */           Integer.valueOf(this.port));
/*     */     }
/* 107 */     return this.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPort(int port) {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.data(this, "com.ibm.mq.jmqi.ConnectionName", "setPort(int)", "setter", 
/* 116 */           Integer.valueOf(port));
/*     */     }
/* 118 */     this.port = port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.mq.jmqi.ConnectionName", "toString()");
/*     */     }
/* 129 */     StringBuffer sb = new StringBuffer();
/* 130 */     sb.append(this.machine);
/* 131 */     sb.append("(");
/* 132 */     sb.append(this.port);
/* 133 */     sb.append(")");
/* 134 */     String traceRet1 = sb.toString();
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.jmqi.ConnectionName", "toString()", traceRet1);
/*     */     }
/* 138 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\ConnectionName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */