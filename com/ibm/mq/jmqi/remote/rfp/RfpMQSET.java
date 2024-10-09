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
/*     */ public class RfpMQSET
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQSET.java";
/*     */   private static final int SELECTOR_COUNT_OFFSET = 0;
/*     */   private static final int INT_ATTR_COUNT_OFFSET = 4;
/*     */   private static final int CHAR_ATTR_LENGTH_OFFSET = 8;
/*     */   public static final int SIZE = 12;
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQSET.java");
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
/*     */   public RfpMQSET(JmqiEnvironment env, byte[] buffer, int offset) {
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
/*     */ 
/*     */   
/*     */   public void setSelectorCount(int selectorCount, boolean swap) {
/*  75 */     if (Trace.isOn)
/*  76 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "setSelectorCount(int,boolean)", new Object[] {
/*  77 */             Integer.valueOf(selectorCount), Boolean.valueOf(swap)
/*     */           }); 
/*  79 */     this.dc.writeI32(selectorCount, this.buffer, this.offset + 0, swap);
/*     */     
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "setSelectorCount(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntAttrCount(int intAttrCount, boolean swap) {
/*  91 */     if (Trace.isOn)
/*  92 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "setIntAttrCount(int,boolean)", new Object[] {
/*  93 */             Integer.valueOf(intAttrCount), Boolean.valueOf(swap)
/*     */           }); 
/*  95 */     this.dc.writeI32(intAttrCount, this.buffer, this.offset + 4, swap);
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "setIntAttrCount(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharAttrLength(int charAttrLength, boolean swap) {
/* 107 */     if (Trace.isOn)
/* 108 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "setCharAttrLength(int,boolean)", new Object[] {
/* 109 */             Integer.valueOf(charAttrLength), Boolean.valueOf(swap)
/*     */           }); 
/* 111 */     this.dc.writeI32(charAttrLength, this.buffer, this.offset + 8, swap);
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "setCharAttrLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectorCount(boolean swap) {
/* 122 */     if (Trace.isOn)
/* 123 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "getSelectorCount(boolean)", new Object[] {
/* 124 */             Boolean.valueOf(swap)
/*     */           }); 
/* 126 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "getSelectorCount(boolean)", 
/* 130 */           Integer.valueOf(traceRet1));
/*     */     }
/* 132 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntAttrCount(boolean swap) {
/* 138 */     if (Trace.isOn)
/* 139 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "getIntAttrCount(boolean)", new Object[] {
/* 140 */             Boolean.valueOf(swap)
/*     */           }); 
/* 142 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "getIntAttrCount(boolean)", 
/* 146 */           Integer.valueOf(traceRet1));
/*     */     }
/* 148 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharAttrLength(boolean swap) {
/* 154 */     if (Trace.isOn)
/* 155 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "getCharAttrLength(boolean)", new Object[] {
/* 156 */             Boolean.valueOf(swap)
/*     */           }); 
/* 158 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSET", "getCharAttrLength(boolean)", 
/* 162 */           Integer.valueOf(traceRet1));
/*     */     }
/* 164 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQSET.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */