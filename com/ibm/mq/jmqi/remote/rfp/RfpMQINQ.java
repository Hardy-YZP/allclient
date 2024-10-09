/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ public class RfpMQINQ
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQINQ.java";
/*     */   private static final int SELECTOR_COUNT_OFFSET = 0;
/*     */   private static final int INT_ATTR_COUNT_OFFSET = 4;
/*     */   private static final int CHAR_ATTR_LENGTH_OFFSET = 8;
/*     */   public static final int SIZE = 12;
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQINQ.java");
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
/*     */   public RfpMQINQ(JmqiEnvironment env, byte[] buffer, int offset) {
/*  51 */     super(env, buffer, offset);
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
/*     */   public void setSelectorCount(int selectorCount, boolean swap) {
/*  73 */     if (Trace.isOn)
/*  74 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "setSelectorCount(int,boolean)", new Object[] {
/*  75 */             Integer.valueOf(selectorCount), Boolean.valueOf(swap)
/*     */           }); 
/*  77 */     this.dc.writeI32(selectorCount, this.buffer, this.offset + 0, swap);
/*     */     
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "setSelectorCount(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntAttrCount(int intAttrCount, boolean swap) {
/*  89 */     if (Trace.isOn)
/*  90 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "setIntAttrCount(int,boolean)", new Object[] {
/*  91 */             Integer.valueOf(intAttrCount), Boolean.valueOf(swap)
/*     */           }); 
/*  93 */     this.dc.writeI32(intAttrCount, this.buffer, this.offset + 4, swap);
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "setIntAttrCount(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharAttrLength(int charAttrLength, boolean swap) {
/* 105 */     if (Trace.isOn)
/* 106 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "setCharAttrLength(int,boolean)", new Object[] {
/* 107 */             Integer.valueOf(charAttrLength), Boolean.valueOf(swap)
/*     */           }); 
/* 109 */     this.dc.writeI32(charAttrLength, this.buffer, this.offset + 8, swap);
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "setCharAttrLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectorCount(boolean swap) {
/* 120 */     if (Trace.isOn)
/* 121 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "getSelectorCount(boolean)", new Object[] {
/* 122 */             Boolean.valueOf(swap)
/*     */           }); 
/* 124 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "getSelectorCount(boolean)", 
/* 128 */           Integer.valueOf(traceRet1));
/*     */     }
/* 130 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntAttrCount(boolean swap) {
/* 137 */     if (Trace.isOn)
/* 138 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "getIntAttrCount(boolean)", new Object[] {
/* 139 */             Boolean.valueOf(swap)
/*     */           }); 
/* 141 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "getIntAttrCount(boolean)", 
/* 145 */           Integer.valueOf(traceRet1));
/*     */     }
/* 147 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharAttrLength(boolean swap) {
/* 153 */     if (Trace.isOn)
/* 154 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "getCharAttrLength(boolean)", new Object[] {
/* 155 */             Boolean.valueOf(swap)
/*     */           }); 
/* 157 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQINQ", "getCharAttrLength(boolean)", 
/* 161 */           Integer.valueOf(traceRet1));
/*     */     }
/* 163 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQINQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */