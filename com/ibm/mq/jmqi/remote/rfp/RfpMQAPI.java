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
/*     */ public class RfpMQAPI
/*     */   extends RfpTSH
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQAPI.java";
/*     */   private static final int CALL_LENGTH_OFFSET = 0;
/*     */   private static final int RETURN_CODE_OFFSET = 4;
/*     */   private static final int FLAGS_OFFSET = 8;
/*     */   private static final int RMID_OFFSET = 12;
/*     */   private static final int COMP_CODE_OFFSET = 4;
/*     */   private static final int REASON_CODE_OFFSET = 8;
/*     */   private static final int HANDLE_OFFSET = 12;
/*     */   private static final int LAST_RECEIVED_INDEX_OFFSET = 4;
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQAPI.java");
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
/*     */   public RfpMQAPI(JmqiEnvironment env, byte[] buffer, int offset) {
/*  54 */     super(env, buffer, offset);
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
/*     */   
/* 123 */   private static int SIZE = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hdrSize() {
/* 129 */     return super.hdrSize() + SIZE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallLength(int callLength) {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setCallLength(int)", "setter", 
/* 137 */           Integer.valueOf(callLength));
/*     */     }
/*     */ 
/*     */     
/* 141 */     this.dc.writeI32(callLength, this.buffer, this.offset + super.hdrSize() + 0, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnCode(int returnCode, boolean swap) {
/* 148 */     if (Trace.isOn)
/* 149 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setReturnCode(int,boolean)", new Object[] {
/* 150 */             Integer.valueOf(returnCode), Boolean.valueOf(swap)
/*     */           }); 
/* 152 */     this.dc.writeI32(returnCode, this.buffer, this.offset + super.hdrSize() + 4, swap);
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setReturnCode(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags, boolean swap) {
/* 164 */     if (Trace.isOn)
/* 165 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setFlags(int,boolean)", new Object[] {
/* 166 */             Integer.valueOf(flags), Boolean.valueOf(swap)
/*     */           }); 
/* 168 */     this.dc.writeI32(flags, this.buffer, this.offset + super.hdrSize() + 8, swap);
/*     */     
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setFlags(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRMID(int rmid, boolean swap) {
/* 180 */     if (Trace.isOn)
/* 181 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setRMID(int,boolean)", new Object[] {
/* 182 */             Integer.valueOf(rmid), Boolean.valueOf(swap)
/*     */           }); 
/* 184 */     this.dc.writeI32(rmid, this.buffer, this.offset + super.hdrSize() + 12, swap);
/*     */     
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setRMID(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompCode(int compCode, boolean swap) {
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setCompCode(int,boolean)", new Object[] {
/* 198 */             Integer.valueOf(compCode), Boolean.valueOf(swap)
/*     */           }); 
/* 200 */     this.dc.writeI32(compCode, this.buffer, this.offset + super.hdrSize() + 4, swap);
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setCompCode(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReasonCode(int reasonCode, boolean swap) {
/* 212 */     if (Trace.isOn)
/* 213 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setReasonCode(int,boolean)", new Object[] {
/* 214 */             Integer.valueOf(reasonCode), Boolean.valueOf(swap)
/*     */           }); 
/* 216 */     this.dc.writeI32(reasonCode, this.buffer, this.offset + super.hdrSize() + 8, swap);
/*     */     
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setReasonCode(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandle(int handle, boolean swap) {
/* 228 */     if (Trace.isOn)
/* 229 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setHandle(int,boolean)", new Object[] {
/* 230 */             Integer.valueOf(handle), Boolean.valueOf(swap)
/*     */           }); 
/* 232 */     this.dc.writeI32(handle, this.buffer, this.offset + super.hdrSize() + 12, swap);
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setHandle(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastReceivedIndex(int lastReceivedIndex, boolean swap) {
/* 245 */     if (Trace.isOn)
/* 246 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setLastReceivedIndex(int,boolean)", new Object[] {
/* 247 */             Integer.valueOf(lastReceivedIndex), Boolean.valueOf(swap)
/*     */           }); 
/* 249 */     this.dc.writeI32(lastReceivedIndex, this.buffer, this.offset + super.hdrSize() + 4, swap);
/*     */     
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "setLastReceivedIndex(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCallLength() {
/* 260 */     int retval = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 0, false);
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getCallLength()", "getter", 
/* 264 */           Integer.valueOf(retval));
/*     */     }
/* 266 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReturnCode(boolean swap) {
/* 272 */     if (Trace.isOn)
/* 273 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getReturnCode(boolean)", new Object[] {
/* 274 */             Boolean.valueOf(swap)
/*     */           }); 
/* 276 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 4, swap);
/*     */     
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getReturnCode(boolean)", 
/* 280 */           Integer.valueOf(traceRet1));
/*     */     }
/* 282 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags(boolean swap) {
/* 288 */     if (Trace.isOn)
/* 289 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getFlags(boolean)", new Object[] {
/* 290 */             Boolean.valueOf(swap)
/*     */           }); 
/* 292 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 8, swap);
/*     */     
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getFlags(boolean)", 
/* 296 */           Integer.valueOf(traceRet1));
/*     */     }
/* 298 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRMID(boolean swap) {
/* 304 */     if (Trace.isOn)
/* 305 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getRMID(boolean)", new Object[] {
/* 306 */             Boolean.valueOf(swap)
/*     */           }); 
/* 308 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 12, swap);
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getRMID(boolean)", 
/* 312 */           Integer.valueOf(traceRet1));
/*     */     }
/* 314 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode(boolean swap) {
/* 320 */     if (Trace.isOn)
/* 321 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getCompCode(boolean)", new Object[] {
/* 322 */             Boolean.valueOf(swap)
/*     */           }); 
/* 324 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 4, swap);
/*     */     
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getCompCode(boolean)", 
/* 328 */           Integer.valueOf(traceRet1));
/*     */     }
/* 330 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReasonCode(boolean swap) {
/* 336 */     if (Trace.isOn)
/* 337 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getReasonCode(boolean)", new Object[] {
/* 338 */             Boolean.valueOf(swap)
/*     */           }); 
/* 340 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 8, swap);
/*     */     
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getReasonCode(boolean)", 
/* 344 */           Integer.valueOf(traceRet1));
/*     */     }
/* 346 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHandle(boolean swap) {
/* 352 */     if (Trace.isOn)
/* 353 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getHandle(boolean)", new Object[] {
/* 354 */             Boolean.valueOf(swap)
/*     */           }); 
/* 356 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + super.hdrSize() + 12, swap);
/*     */     
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQAPI", "getHandle(boolean)", 
/* 360 */           Integer.valueOf(traceRet1));
/*     */     }
/* 362 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */