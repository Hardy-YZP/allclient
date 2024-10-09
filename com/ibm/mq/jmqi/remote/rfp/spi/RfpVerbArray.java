/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
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
/*     */ public class RfpVerbArray
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpVerbArray.java";
/*     */   private int verbId;
/*     */   private int maxInoutVersion;
/*     */   private int maxInVersion;
/*     */   private int maxOutVersion;
/*     */   private int flags;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpVerbArray.java");
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
/*     */   public RfpVerbArray(JmqiEnvironment env) {
/*  61 */     super(env);
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "<init>(JmqiEnvironment)");
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
/*  78 */   private static RfpVerbArray dummyVerbArray = null;
/*  79 */   private static RfpVerbArray queryVerbArray = null;
/*     */ 
/*     */   
/*     */   private static final int DUMMY_VERBID = -1;
/*     */   
/*     */   private static final int DUMMY_VERSION = 1;
/*     */   
/*     */   private static final int DUMMY_FLAGS = 0;
/*     */ 
/*     */   
/*     */   public static synchronized RfpVerbArray getDummy(JmqiEnvironment env) {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getDummy(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  94 */     if (dummyVerbArray == null) {
/*  95 */       dummyVerbArray = new RfpVerbArray(env);
/*  96 */       dummyVerbArray.verbId = -1;
/*  97 */       dummyVerbArray.maxInoutVersion = 1;
/*  98 */       dummyVerbArray.maxInVersion = 1;
/*  99 */       dummyVerbArray.maxOutVersion = 1;
/* 100 */       dummyVerbArray.flags = 0;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getDummy(JmqiEnvironment)", dummyVerbArray);
/*     */     }
/*     */     
/* 108 */     return dummyVerbArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized RfpVerbArray getQuerySpi(JmqiEnvironment env) {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getQuerySpi(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 120 */     if (queryVerbArray == null) {
/* 121 */       queryVerbArray = new RfpVerbArray(env);
/* 122 */       queryVerbArray.verbId = 1;
/* 123 */       queryVerbArray.maxInoutVersion = 1;
/* 124 */       queryVerbArray.maxInVersion = 1;
/* 125 */       queryVerbArray.maxOutVersion = 1;
/* 126 */       queryVerbArray.flags = 0;
/*     */     } 
/*     */ 
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getQuerySpi(JmqiEnvironment)", queryVerbArray);
/*     */     }
/*     */     
/* 134 */     return queryVerbArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRequiredBufferSize() {
/* 144 */     int traceRet1 = 20;
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getRequiredBufferSize()", "getter", 
/* 148 */           Integer.valueOf(traceRet1));
/*     */     }
/* 150 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerbId() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getVerbId()", "getter", 
/* 159 */           Integer.valueOf(this.verbId));
/*     */     }
/* 161 */     return this.verbId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxInoutVersion() {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getMaxInoutVersion()", "getter", 
/* 170 */           Integer.valueOf(this.maxInoutVersion));
/*     */     }
/* 172 */     return this.maxInoutVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxInVersion() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getMaxInVersion()", "getter", 
/* 181 */           Integer.valueOf(this.maxInVersion));
/*     */     }
/* 183 */     return this.maxInVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxOutVersion() {
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getMaxOutVersion()", "getter", 
/* 192 */           Integer.valueOf(this.maxOutVersion));
/*     */     }
/* 194 */     return this.maxOutVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "getFlags()", "getter", 
/* 203 */           Integer.valueOf(this.flags));
/*     */     }
/* 205 */     return this.flags;
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
/*     */   public int readFromBuffer(byte[] buffer, int offset, boolean swap) {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "readFromBuffer(byte [ ],int,boolean)", new Object[] { buffer, 
/* 219 */             Integer.valueOf(offset), 
/* 220 */             Boolean.valueOf(swap) });
/*     */     }
/* 222 */     int pos = offset;
/* 223 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 224 */     this.verbId = dc.readI32(buffer, pos, swap);
/* 225 */     pos += 4;
/* 226 */     this.maxInoutVersion = dc.readI32(buffer, pos, swap);
/* 227 */     pos += 4;
/* 228 */     this.maxInVersion = dc.readI32(buffer, pos, swap);
/* 229 */     pos += 4;
/* 230 */     this.maxOutVersion = dc.readI32(buffer, pos, swap);
/* 231 */     pos += 4;
/* 232 */     this.flags = dc.readI32(buffer, pos, swap);
/* 233 */     pos += 4;
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray", "readFromBuffer(byte [ ],int,boolean)", 
/* 239 */           Integer.valueOf(pos));
/*     */     }
/* 241 */     return pos;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpVerbArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */