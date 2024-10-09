/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.transaction.xa.Xid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiXid
/*     */   extends JmqiObject
/*     */   implements Xid
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiXid.java";
/*     */   private static final int SIZEOF_FORMAT_ID = 4;
/*     */   private static final int SIZEOF_GLOBAL_TRANSACTION_ID_LENGTH = 4;
/*     */   private static final int SIZEOF_BRANCH_QUALIFIER_LENGTH = 4;
/*     */   private int formatId;
/*     */   private byte[] globalTransactionId;
/*     */   private byte[] branchQualifier;
/*     */   static final String digits = "0123456789abcdef";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.JmqiXid", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiXid.java");
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
/*     */   protected JmqiXid(JmqiEnvironment env) {
/*  68 */     super(env);
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXid", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXid", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBranchQualifier() {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXid", "getBranchQualifier()", "getter", this.branchQualifier);
/*     */     }
/*     */     
/*  88 */     return this.branchQualifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBranchQualifier(byte[] branchQualifier) {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXid", "setBranchQualifier(byte [ ])", "setter", branchQualifier);
/*     */     }
/*     */     
/* 100 */     this.branchQualifier = branchQualifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFormatId() {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXid", "getFormatId()", "getter", 
/* 111 */           Integer.valueOf(this.formatId));
/*     */     }
/* 113 */     return this.formatId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormatId(int formatId) {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXid", "setFormatId(int)", "setter", 
/* 123 */           Integer.valueOf(formatId));
/*     */     }
/* 125 */     this.formatId = formatId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getGlobalTransactionId() {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXid", "getGlobalTransactionId()", "getter", this.globalTransactionId);
/*     */     }
/*     */     
/* 138 */     return this.globalTransactionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlobalTransactionId(byte[] globalTransactionId) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXid", "setGlobalTransactionId(byte [ ])", "setter", globalTransactionId);
/*     */     }
/*     */     
/* 150 */     this.globalTransactionId = globalTransactionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] allocateBuffer() {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry("com.ibm.mq.jmqi.JmqiXid", "allocateBuffer()");
/*     */     }
/* 163 */     byte[] traceRet1 = allocateBuffer(1);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit("com.ibm.mq.jmqi.JmqiXid", "allocateBuffer()", traceRet1);
/*     */     }
/* 167 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] allocateBuffer(int count) {
/* 178 */     if (Trace.isOn)
/* 179 */       Trace.entry("com.ibm.mq.jmqi.JmqiXid", "allocateBuffer(int)", new Object[] {
/* 180 */             Integer.valueOf(count)
/*     */           }); 
/* 182 */     int bufferSize = 152 * count;
/* 183 */     byte[] buffer = new byte[bufferSize];
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit("com.ibm.mq.jmqi.JmqiXid", "allocateBuffer(int)", buffer);
/*     */     }
/* 187 */     return buffer;
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
/*     */   public static int writeToBuffer(JmqiEnvironment env, Xid xid, byte[] buffer, int offset, boolean swap) throws JmqiException {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry("com.ibm.mq.jmqi.JmqiXid", "writeToBuffer(JmqiEnvironment,Xid,byte [ ],int,boolean)", new Object[] { env, xid, buffer, 
/*     */             
/* 205 */             Integer.valueOf(offset), Boolean.valueOf(swap) });
/*     */     }
/* 207 */     int pos = offset;
/* 208 */     int formatId = xid.getFormatId();
/* 209 */     byte[] globalTransactionId = xid.getGlobalTransactionId();
/* 210 */     byte[] branchQualifier = xid.getBranchQualifier();
/* 211 */     JmqiDC dc = ((JmqiSystemEnvironment)env).getDC();
/* 212 */     dc.writeI32(formatId, buffer, pos, swap);
/* 213 */     pos += 4;
/* 214 */     dc.writeI32(globalTransactionId.length, buffer, pos, swap);
/* 215 */     pos += 4;
/* 216 */     dc.writeI32(branchQualifier.length, buffer, pos, swap);
/* 217 */     pos += 4;
/* 218 */     System.arraycopy(globalTransactionId, 0, buffer, pos, globalTransactionId.length);
/* 219 */     pos += globalTransactionId.length;
/* 220 */     System.arraycopy(branchQualifier, 0, buffer, pos, branchQualifier.length);
/* 221 */     pos += branchQualifier.length;
/* 222 */     int traceRet1 = pos - offset;
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit("com.ibm.mq.jmqi.JmqiXid", "writeToBuffer(JmqiEnvironment,Xid,byte [ ],int,boolean)", 
/* 226 */           Integer.valueOf(traceRet1));
/*     */     }
/* 228 */     return traceRet1;
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
/*     */   public int readFromBuffer(byte[] buffer, int offset, boolean swap) throws JmqiException {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXid", "readFromBuffer(byte [ ],int,boolean)", new Object[] { buffer, 
/* 245 */             Integer.valueOf(offset), Boolean.valueOf(swap) });
/*     */     }
/* 247 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 248 */     int pos = offset;
/* 249 */     this.formatId = dc.readI32(buffer, pos, swap);
/* 250 */     pos += 4;
/* 251 */     int globalTransactionId_length = dc.readI32(buffer, pos, swap);
/* 252 */     pos += 4;
/* 253 */     int branchQualifier_length = dc.readI32(buffer, pos, swap);
/* 254 */     pos += 4;
/* 255 */     this.globalTransactionId = new byte[globalTransactionId_length];
/* 256 */     this.branchQualifier = new byte[branchQualifier_length];
/* 257 */     System.arraycopy(buffer, pos, this.globalTransactionId, 0, globalTransactionId_length);
/* 258 */     pos += globalTransactionId_length;
/* 259 */     System.arraycopy(buffer, pos, this.branchQualifier, 0, branchQualifier_length);
/* 260 */     pos += branchQualifier_length;
/* 261 */     int traceRet2 = 140;
/*     */     
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXid", "readFromBuffer(byte [ ],int,boolean)", 
/* 265 */           Integer.valueOf(traceRet2));
/*     */     }
/* 267 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXid", "toString()");
/*     */     }
/* 279 */     StringBuffer buf = new StringBuffer();
/* 280 */     buf.append("{JmqiXid@");
/* 281 */     buf.append(System.identityHashCode(this));
/* 282 */     buf.append(": formatId(");
/* 283 */     buf.append(Integer.toHexString(this.formatId));
/* 284 */     buf.append("), gtrid(");
/* 285 */     buf.append(toHexString(this.globalTransactionId));
/* 286 */     buf.append("), bqual(");
/* 287 */     buf.append(toHexString(this.branchQualifier));
/* 288 */     buf.append(")}");
/* 289 */     String traceRet1 = buf.toString();
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXid", "toString()", traceRet1);
/*     */     }
/* 293 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String toHexString(byte[] b) {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXid", "toHexString(byte [ ])", new Object[] { b });
/*     */     }
/* 302 */     if (b == null) {
/* 303 */       if (Trace.isOn) {
/* 304 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiXid", "toHexString(byte [ ])", null, 1);
/*     */       }
/* 306 */       return null;
/*     */     } 
/* 308 */     StringBuffer result = new StringBuffer(b.length * 2);
/* 309 */     for (int i = 0; i < b.length; i++) {
/* 310 */       result.append("0123456789abcdef".charAt(b[i] >> 4 & 0xF));
/* 311 */       result.append("0123456789abcdef".charAt(b[i] & 0xF));
/*     */     } 
/* 313 */     String traceRet1 = result.toString();
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXid", "toHexString(byte [ ])", traceRet1, 2);
/*     */     }
/* 317 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiXid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */