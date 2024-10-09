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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpFAPMQCNO
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpFAPMQCNO.java";
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpFAPMQCNO.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   private int version = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpFAPMQCNO(JmqiEnvironment env, byte[] buffer, int offset) {
/*  54 */     super(env, buffer, offset);
/*     */   }
/*     */   
/*  57 */   static final byte[] rfpFAPMQCNO_STRUC_ID = new byte[] { 70, 67, 78, 79 };
/*     */ 
/*     */   
/*     */   public static final int rfpFAPMQCNO_VERSION_1 = 1;
/*     */ 
/*     */   
/*     */   public static final int rfpFAPMQCNO_VERSION_2 = 2;
/*     */ 
/*     */   
/*     */   public static final int rfpFAPMQCNO_VERSION_3 = 3;
/*     */ 
/*     */   
/*     */   public static final int rfpFAPMQCNO_VERSION_4 = 4;
/*     */ 
/*     */   
/*     */   public static final int rfpFAPMQCNO_CURRENT_VERSION = 4;
/*     */ 
/*     */   
/*     */   public static final int rfpCF_CAP_UNKNOWN = 0;
/*     */ 
/*     */   
/*     */   public static final int rfpCF_SPCAP_SUPPORTED = 1;
/*     */ 
/*     */   
/*     */   public static final int rfpCF_SPCAP_NOT_SUPPORTED = 2;
/*     */ 
/*     */   
/*     */   public static final int rfpCF_ACCEPT_QM_HINTS = 4;
/*     */ 
/*     */   
/*     */   private static final int VERSION_OFFSET = 4;
/*     */ 
/*     */   
/*     */   private static final int CAPABILITY_FLAGS_OFFSET = 8;
/*     */ 
/*     */   
/*     */   private static final int SIZE_TO_UNION = 12;
/*     */ 
/*     */   
/*     */   private static final int CONN_TAG_OFFSET = 12;
/*     */ 
/*     */   
/*     */   private static final int CONNECTION_ID_OFFSET = 12;
/*     */ 
/*     */   
/*     */   private static final int UNION_SIZE = 128;
/*     */ 
/*     */   
/*     */   private static final int RET_CONN_TAG_OFFSET = 140;
/*     */ 
/*     */   
/*     */   private static final int RET_CONN_TAG_SIZE = 128;
/*     */   
/*     */   private static final int REBALANCING_TYPE_OFFSET = 268;
/*     */   
/*     */   private static final int REBALANCING_TYPE_SIZE = 4;
/*     */   
/*     */   private static final int REBALANCING_TIMEOUT_OFFSET = 272;
/*     */   
/*     */   private static final int REBALANCING_TIMEOUT_SIZE = 4;
/*     */   
/*     */   private static final int REBALANCING_OPTIONS_OFFSET = 276;
/*     */   
/*     */   private static final int REBALANCING_OPTIONS_SIZE = 4;
/*     */   
/*     */   public static final int SIZE_V2 = 140;
/*     */   
/*     */   public static final int SIZE_V1 = 140;
/*     */   
/*     */   public static final int SIZE_V3 = 268;
/*     */   
/*     */   public static final int SIZE_V4 = 280;
/*     */   
/*     */   public static final int SIZE_CURRENT = 280;
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 134 */     System.arraycopy(rfpFAPMQCNO_STRUC_ID, 0, this.buffer, this.offset, rfpFAPMQCNO_STRUC_ID.length);
/*     */     
/* 136 */     this.dc.clear(this.buffer, this.offset + 8, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version, boolean swap) {
/* 143 */     if (Trace.isOn)
/* 144 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setVersion(int,boolean)", new Object[] {
/* 145 */             Integer.valueOf(version), Boolean.valueOf(swap)
/*     */           }); 
/* 147 */     this.version = version;
/* 148 */     this.dc.writeI32(version, this.buffer, this.offset + 4, swap);
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCapabilityFlags(int capabilityFlags, boolean swap) {
/* 160 */     if (Trace.isOn)
/* 161 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setCapabilityFlags(int,boolean)", new Object[] {
/* 162 */             Integer.valueOf(capabilityFlags), 
/* 163 */             Boolean.valueOf(swap)
/*     */           }); 
/* 165 */     this.dc.writeI32(capabilityFlags, this.buffer, this.offset + 8, swap);
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setCapabilityFlags(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRebalancingApplicationType(int type, boolean swap) {
/* 176 */     if (Trace.isOn)
/* 177 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRebalancingApplicationType(int,boolean)", new Object[] {
/* 178 */             Integer.valueOf(type), 
/* 179 */             Boolean.valueOf(swap)
/*     */           }); 
/* 181 */     this.dc.writeI32(type, this.buffer, this.offset + 268, swap);
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRebalancingApplicationType(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRebalancingTimeout(int timeout, boolean swap) {
/* 192 */     if (Trace.isOn)
/* 193 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRebalancingTimeout(int,boolean)", new Object[] {
/* 194 */             Integer.valueOf(timeout), 
/* 195 */             Boolean.valueOf(swap)
/*     */           }); 
/* 197 */     this.dc.writeI32(timeout, this.buffer, this.offset + 272, swap);
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRebalancingTimeout(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRebalancingOptions(int options, boolean swap) {
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRebalancingOptions(int,boolean)", new Object[] {
/* 210 */             Integer.valueOf(options), 
/* 211 */             Boolean.valueOf(swap)
/*     */           }); 
/* 213 */     this.dc.writeI32(options, this.buffer, this.offset + 276, swap);
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRebalancingOptions(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnTag(byte[] connTag) {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setConnTag(byte [ ])", "setter", connTag);
/*     */     }
/*     */ 
/*     */     
/* 228 */     System.arraycopy(connTag, 0, this.buffer, this.offset + 12, 128);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRetConnTag(byte[] connTag) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setRetConnTag(byte [ ])", "setter", connTag);
/*     */     }
/*     */ 
/*     */     
/* 239 */     System.arraycopy(connTag, 0, this.buffer, this.offset + 140, 128);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionId(byte[] connectionId) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "setConnectionId(byte [ ])", "setter", connectionId);
/*     */     }
/*     */ 
/*     */     
/* 250 */     System.arraycopy(connectionId, 0, this.buffer, this.offset + 12, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion(boolean swap) {
/* 258 */     if (Trace.isOn)
/* 259 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getVersion(boolean)", new Object[] {
/* 260 */             Boolean.valueOf(swap)
/*     */           }); 
/* 262 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getVersion(boolean)", 
/* 266 */           Integer.valueOf(traceRet1));
/*     */     }
/* 268 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCapabilityFlags(boolean swap) {
/* 276 */     if (Trace.isOn)
/* 277 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getCapabilityFlags(boolean)", new Object[] {
/* 278 */             Boolean.valueOf(swap)
/*     */           }); 
/* 280 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getCapabilityFlags(boolean)", 
/* 283 */           Integer.valueOf(traceRet1));
/*     */     }
/* 285 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void getConnTag(byte[] connTag) {
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getConnTag(byte [ ])", new Object[] { connTag });
/*     */     }
/*     */     
/* 295 */     System.arraycopy(this.buffer, this.offset + 12, connTag, 0, 128);
/*     */     
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getConnTag(byte [ ])", connTag);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRetConnTag(byte[] connTag) {
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getRetConnTag(byte [ ])", new Object[] { connTag });
/*     */     }
/*     */     
/* 310 */     System.arraycopy(this.buffer, this.offset + 140, connTag, 0, 128);
/*     */     
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getRetConnTag(byte [ ])", connTag);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getConnectionId(byte[] connectionId) {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getConnectionId(byte [ ])", new Object[] { connectionId });
/*     */     }
/*     */     
/* 325 */     System.arraycopy(this.buffer, this.offset + 12, connectionId, 0, 24);
/*     */     
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getConnectionId(byte [ ])", connectionId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initVersion(int fapLevel, boolean swap) {
/* 339 */     if (Trace.isOn)
/* 340 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "initVersion(int,boolean)", new Object[] {
/* 341 */             Integer.valueOf(fapLevel), Boolean.valueOf(swap)
/*     */           }); 
/* 343 */     if (fapLevel >= 15) {
/* 344 */       setVersion(4, swap);
/*     */     }
/* 346 */     else if (fapLevel >= 14) {
/* 347 */       setVersion(3, swap);
/*     */     }
/* 349 */     else if (fapLevel >= 12) {
/* 350 */       setVersion(2, swap);
/*     */     } else {
/*     */       
/* 353 */       setVersion(1, swap);
/*     */     } 
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "initVersion(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean supportsRebalancingOptions() {
/* 365 */     boolean result = (this.version >= 4);
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "supportsRebalancingOptions()", "getter", Boolean.valueOf(result));
/*     */     }
/* 369 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(boolean swap) {
/* 377 */     if (Trace.isOn) {
/* 378 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getSize(boolean)", new Object[] { Boolean.valueOf(swap) });
/*     */     }
/* 380 */     int size = 0;
/* 381 */     switch (this.version) {
/*     */       case 1:
/* 383 */         size = 140;
/*     */         break;
/*     */       case 2:
/* 386 */         size = 140;
/*     */         break;
/*     */       case 3:
/* 389 */         size = 268;
/*     */         break;
/*     */       case 4:
/* 392 */         size = 280;
/*     */         break;
/*     */       default:
/* 395 */         size = 280; break;
/*     */     } 
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO", "getSize(boolean)", size);
/*     */     }
/* 400 */     return size;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpFAPMQCNO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */