/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
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
/*     */ public class RfpINSPI
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpINSPI.java";
/*     */   private static final int VERBID_OFFSET = 0;
/*     */   private static final int OUTSTRUCTVERSION_OFFSET = 4;
/*     */   private static final int OUTSTRUCTLENGTH_OFFSET = 8;
/*     */   private static final int INSPI_SIZE = 12;
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpINSPI.java");
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
/*     */   private RfpINSPI(JmqiEnvironment env, byte[] buffer, int offset) {
/*  57 */     super(env, buffer, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RfpINSPI getInstance(JmqiEnvironment env, RemoteSession session, byte[] buffer, int offset) {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int)", new Object[] { env, session, buffer, 
/*     */             
/*  70 */             Integer.valueOf(offset) });
/*     */     }
/*  72 */     RfpINSPI result = session.getInSpi();
/*  73 */     if (result == null) {
/*  74 */       result = new RfpINSPI(env, buffer, offset);
/*  75 */       session.setInSpi(result);
/*     */     } else {
/*     */       
/*  78 */       result.setRfpBuffer(buffer);
/*  79 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int)", result);
/*     */     }
/*     */     
/*  87 */     return result;
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
/*     */   public void setVerbId(int verbId, boolean swap) {
/* 106 */     if (Trace.isOn)
/* 107 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "setVerbId(int,boolean)", new Object[] {
/* 108 */             Integer.valueOf(verbId), Boolean.valueOf(swap)
/*     */           }); 
/* 110 */     this.dc.writeI32(verbId, this.buffer, this.offset + 0, swap);
/*     */ 
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "setVerbId(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutStructVersion(int outStructVersion, boolean swap) {
/* 123 */     if (Trace.isOn)
/* 124 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "setOutStructVersion(int,boolean)", new Object[] {
/* 125 */             Integer.valueOf(outStructVersion), 
/* 126 */             Boolean.valueOf(swap)
/*     */           }); 
/* 128 */     this.dc.writeI32(outStructVersion, this.buffer, this.offset + 4, swap);
/*     */ 
/*     */     
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "setOutStructVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutStructLength(int outStructLength, boolean swap) {
/* 142 */     if (Trace.isOn)
/* 143 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "setOutStructLength(int,boolean)", new Object[] {
/* 144 */             Integer.valueOf(outStructLength), 
/* 145 */             Boolean.valueOf(swap)
/*     */           }); 
/* 147 */     this.dc.writeI32(outStructLength, this.buffer, this.offset + 8, swap);
/*     */ 
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "setOutStructLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI", "getStructSize()", "getter", 
/* 162 */           Integer.valueOf(12));
/*     */     }
/* 164 */     return 12;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpINSPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */