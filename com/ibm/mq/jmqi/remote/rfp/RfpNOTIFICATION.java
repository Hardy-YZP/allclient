/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
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
/*     */ public class RfpNOTIFICATION
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpNOTIFICATION.java";
/*     */   private static final int VERSION_OFFSET = 0;
/*     */   private static final int HOBJ_OFFSET = 4;
/*     */   private static final int NOTIFICATION_CODE_OFFSET = 8;
/*     */   private static final int VALUE_OFFSET = 12;
/*     */   public static final int SIZE_V1 = 16;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpNOTIFICATION.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpNOTIFICATION(JmqiEnvironment env, byte[] buffer, int offset) {
/*  74 */     super(env, buffer, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion(boolean swap) {
/*  80 */     if (Trace.isOn)
/*  81 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getVersion(boolean)", new Object[] {
/*  82 */             Boolean.valueOf(swap)
/*     */           }); 
/*  84 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getVersion(boolean)", 
/*  88 */           Integer.valueOf(traceRet1));
/*     */     }
/*  90 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHobj(boolean swap) {
/*  96 */     if (Trace.isOn)
/*  97 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getHobj(boolean)", new Object[] {
/*  98 */             Boolean.valueOf(swap)
/*     */           }); 
/* 100 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getHobj(boolean)", 
/* 104 */           Integer.valueOf(traceRet1));
/*     */     }
/* 106 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNotificationCode(boolean swap) {
/* 112 */     if (Trace.isOn)
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getNotificationCode(boolean)", new Object[] {
/* 114 */             Boolean.valueOf(swap)
/*     */           }); 
/* 116 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getNotificationCode(boolean)", 
/* 120 */           RemoteConstantDecoder.decodeSingleOption(traceRet1, "rfpNC_"));
/*     */     }
/* 122 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue(boolean swap) {
/* 128 */     if (Trace.isOn)
/* 129 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getValue(boolean)", new Object[] {
/* 130 */             Boolean.valueOf(swap)
/*     */           }); 
/* 132 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */     
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "getValue(boolean)", 
/* 136 */           Integer.valueOf(traceRet1));
/*     */     }
/* 138 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version, boolean swap) {
/* 145 */     if (Trace.isOn)
/* 146 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setVersion(int,boolean)", new Object[] {
/* 147 */             Integer.valueOf(version), Boolean.valueOf(swap)
/*     */           }); 
/* 149 */     this.dc.writeI32(version, this.buffer, this.offset + 0, swap);
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHobj(int hobj, boolean swap) {
/* 161 */     if (Trace.isOn)
/* 162 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setHobj(int,boolean)", new Object[] {
/* 163 */             Integer.valueOf(hobj), Boolean.valueOf(swap)
/*     */           }); 
/* 165 */     this.dc.writeI32(hobj, this.buffer, this.offset + 4, swap);
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setHobj(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotificationCode(int notificationCode, boolean swap) {
/* 177 */     if (Trace.isOn)
/* 178 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setNotificationCode(int,boolean)", new Object[] {
/* 179 */             RemoteConstantDecoder.decodeSingleOption(notificationCode, "rfpNC_"), 
/* 180 */             Boolean.valueOf(swap)
/*     */           }); 
/* 182 */     this.dc.writeI32(notificationCode, this.buffer, this.offset + 8, swap);
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setNotificationCode(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(int value, boolean swap) {
/* 195 */     if (Trace.isOn)
/* 196 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setValue(int,boolean)", new Object[] {
/* 197 */             Integer.valueOf(value), Boolean.valueOf(swap)
/*     */           }); 
/* 199 */     this.dc.writeI32(value, this.buffer, this.offset + 12, swap);
/*     */     
/* 201 */     if (Trace.isOn)
/* 202 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION", "setValue(int,boolean)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpNOTIFICATION.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */