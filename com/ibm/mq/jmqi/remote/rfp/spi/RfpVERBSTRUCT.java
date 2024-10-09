/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ public class RfpVERBSTRUCT
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpVERBSTRUCT.java";
/*     */   protected static final int VERSION_OFFSET = 4;
/*     */   protected static final int LENGTH_OFFSET = 8;
/*     */   public static final int VERBSTRUCT_SIZE = 12;
/*     */   private final int spiVersion;
/*     */   protected static final int SPI_VERSION_0 = 0;
/*     */   protected static final int SPI_VERSION_1 = 1;
/*     */   protected static final int SPI_VERSION_2 = 2;
/*     */   protected static final int SPI_VERSION_3 = 3;
/*     */   public static final int SPI_VERBSTRUCT_TYPE_INOUT = 0;
/*     */   public static final int SPI_VERBSTRUCT_TYPE_IN = 1;
/*     */   public static final int SPI_VERBSTRUCT_TYPE_OUT = 2;
/*     */   public static final int SPI_VERBSTRUCT_TYPE_MAX = 2;
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpVERBSTRUCT.java");
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
/*     */   public RfpVERBSTRUCT(JmqiEnvironment env, byte[] buffer, int offset) {
/*  53 */     super(env, buffer, offset);
/*  54 */     this.spiVersion = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpVERBSTRUCT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  65 */     super(env, buffer, offset);
/*  66 */     this.spiVersion = spiVersion;
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
/*     */ 
/*     */   
/*     */   public static String decodeVerbStructType(int value) {
/*     */     String traceRet1, traceRet2, traceRet3;
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "decodeVerbStructType(int)", new Object[] {
/* 105 */             Integer.valueOf(value)
/*     */           });
/*     */     }
/* 108 */     switch (value) {
/*     */       case 0:
/* 110 */         traceRet1 = "SPI_VERBSTRUCT_TYPE_INOUT - 0";
/* 111 */         if (Trace.isOn) {
/* 112 */           Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "decodeVerbStructType(int)", traceRet1, 1);
/*     */         }
/*     */         
/* 115 */         return traceRet1;
/*     */       
/*     */       case 1:
/* 118 */         traceRet2 = "SPI_VERBSTRUCT_TYPE_IN - 1";
/* 119 */         if (Trace.isOn) {
/* 120 */           Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "decodeVerbStructType(int)", traceRet2, 2);
/*     */         }
/*     */         
/* 123 */         return traceRet2;
/*     */       
/*     */       case 2:
/* 126 */         traceRet3 = "SPI_VERBSTRUCT_TYPE_OUT - 2";
/* 127 */         if (Trace.isOn) {
/* 128 */           Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "decodeVerbStructType(int)", traceRet3, 3);
/*     */         }
/*     */         
/* 131 */         return traceRet3;
/*     */     } 
/*     */     
/* 134 */     String traceRet4 = String.format("SPI_VERBSTRUCT_TYPE_UNKNOWN - %d", new Object[] { Integer.valueOf(value) });
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "decodeVerbStructType(int)", traceRet4, 4);
/*     */     }
/*     */     
/* 139 */     return traceRet4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher(byte[] eyeCatcher) {
/* 148 */     System.arraycopy(eyeCatcher, 0, this.buffer, this.offset, eyeCatcher.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version, boolean swap) {
/* 155 */     if (Trace.isOn)
/* 156 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "setVersion(int,boolean)", new Object[] {
/* 157 */             Integer.valueOf(version), Boolean.valueOf(swap)
/*     */           }); 
/* 159 */     this.dc.writeI32(version, this.buffer, this.offset + 4, swap);
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "setVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultVersion(boolean swap) {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "setDefaultVersion(boolean)", "setter", 
/* 172 */           Boolean.valueOf(swap));
/*     */     }
/* 174 */     this.dc.writeI32(this.spiVersion, this.buffer, this.offset + 4, swap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(int length, boolean swap) {
/* 182 */     if (Trace.isOn)
/* 183 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "setLength(int,boolean)", new Object[] {
/* 184 */             Integer.valueOf(length), Boolean.valueOf(swap)
/*     */           }); 
/* 186 */     this.dc.writeI32(length, this.buffer, this.offset + 8, swap);
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "setLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion(boolean swap) {
/* 198 */     if (Trace.isOn)
/* 199 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "getVersion(boolean)", new Object[] {
/* 200 */             Boolean.valueOf(swap)
/*     */           }); 
/* 202 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "getVersion(boolean)", 
/* 206 */           Integer.valueOf(traceRet1));
/*     */     }
/* 208 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 215 */     if (Trace.isOn)
/* 216 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "getLength(boolean)", new Object[] {
/* 217 */             Boolean.valueOf(swap)
/*     */           }); 
/* 219 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "getLength(boolean)", 
/* 223 */           Integer.valueOf(traceRet1));
/*     */     }
/* 225 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT", "getStructSize()", "getter", 
/* 234 */           Integer.valueOf(12));
/*     */     }
/* 236 */     return 12;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpVERBSTRUCT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */