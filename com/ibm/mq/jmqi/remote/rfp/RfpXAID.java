/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiXid;
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
/*     */ 
/*     */ 
/*     */ public class RfpXAID
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXAID.java";
/*     */   private static final int FORMAT_ID_OFFSET = 0;
/*     */   private static final int GTRID_LENGTH_OFFSET = 4;
/*     */   private static final int BQUAL_LENGTH_OFFSET = 5;
/*     */   private static final int DATA_OFFSET = 6;
/*     */   private static final int MAX_GTRID_LENGTH = 64;
/*     */   private static final int MAX_BQUAL_LENGTH = 64;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpXAID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXAID.java");
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
/*     */   public RfpXAID(JmqiEnvironment env, byte[] buffer, int offset) {
/*  57 */     super(env, buffer, offset);
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
/*     */   public int getRoundedLength() {
/*  79 */     int gtridLength = this.buffer[this.offset + 4] & 0xFF;
/*  80 */     int bqualLength = this.buffer[this.offset + 5] & 0xFF;
/*  81 */     int roundedLength = gtridLength + bqualLength;
/*  82 */     roundedLength += 4;
/*  83 */     roundedLength += 2;
/*  84 */     roundedLength += 3;
/*  85 */     roundedLength &= 0xFFFFFFFC;
/*     */ 
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAID", "getRoundedLength()", "getter", 
/*  90 */           Integer.valueOf(roundedLength));
/*     */     }
/*  92 */     return roundedLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxLength() {
/*  97 */     int maxLength = 128;
/*  98 */     maxLength += 4;
/*  99 */     maxLength += 2;
/* 100 */     maxLength += 3;
/* 101 */     maxLength &= 0xFFFFFFFC;
/*     */     
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpXAID", "getMaxLength()", "getter", 
/* 105 */           Integer.valueOf(maxLength));
/*     */     }
/* 107 */     return maxLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXid(Xid xid, boolean swap) {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAID", "setXid(Xid,boolean)", new Object[] { xid, 
/* 119 */             Boolean.valueOf(swap) });
/*     */     }
/* 121 */     this.dc.writeI32(xid.getFormatId(), this.buffer, this.offset + 0, swap);
/* 122 */     byte[] gtrid = xid.getGlobalTransactionId();
/* 123 */     this.buffer[this.offset + 4] = (byte)gtrid.length;
/* 124 */     byte[] bqual = xid.getBranchQualifier();
/* 125 */     this.buffer[this.offset + 5] = (byte)bqual.length;
/* 126 */     System.arraycopy(gtrid, 0, this.buffer, this.offset + 6, gtrid.length);
/* 127 */     System.arraycopy(bqual, 0, this.buffer, this.offset + 6 + gtrid.length, bqual.length);
/*     */ 
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAID", "setXid(Xid,boolean)");
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
/*     */   public Xid getXid(boolean swap) throws JmqiException {
/* 144 */     if (Trace.isOn)
/* 145 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAID", "getXid(boolean)", new Object[] {
/* 146 */             Boolean.valueOf(swap)
/*     */           }); 
/* 148 */     JmqiXid xid = this.env.newJmqiXid();
/* 149 */     xid.setFormatId(this.dc.readI32(this.buffer, this.offset + 0, swap));
/* 150 */     int gtridLength = this.buffer[this.offset + 4] & 0xFF;
/* 151 */     int bqualLength = this.buffer[this.offset + 5] & 0xFF;
/* 152 */     byte[] gtrid = new byte[gtridLength];
/* 153 */     byte[] bqual = new byte[bqualLength];
/* 154 */     System.arraycopy(this.buffer, this.offset + 6, gtrid, 0, gtridLength);
/* 155 */     System.arraycopy(this.buffer, this.offset + 6 + gtridLength, bqual, 0, bqualLength);
/* 156 */     xid.setGlobalTransactionId(gtrid);
/* 157 */     xid.setBranchQualifier(bqual);
/*     */ 
/*     */     
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAID", "getXid(boolean)", xid);
/*     */     }
/* 163 */     return (Xid)xid;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpXAID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */