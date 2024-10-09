/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQXid
/*     */   extends JmqiObject
/*     */   implements Xid
/*     */ {
/*     */   private int formatId;
/*     */   private byte[] gtrid;
/*     */   private byte[] bqual;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.MQXid", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQXid.java");
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
/*     */   public MQXid(int formatId, byte[] gtrid, byte[] bqual) {
/*  57 */     super(MQSESSION.getJmqiEnv());
/*  58 */     if (Trace.isOn)
/*  59 */       Trace.entry(this, "com.ibm.mq.MQXid", "<init>(int,byte [ ],byte [ ])", new Object[] {
/*  60 */             Integer.valueOf(formatId), gtrid, bqual
/*     */           }); 
/*  62 */     this.formatId = formatId;
/*  63 */     this.gtrid = gtrid;
/*  64 */     this.bqual = bqual;
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.MQXid", "<init>(int,byte [ ],byte [ ])");
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
/*     */   public int getFormatId() {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.data(this, "com.ibm.mq.MQXid", "getFormatId()", "getter", Integer.valueOf(this.formatId));
/*     */     }
/*  85 */     return this.formatId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getGlobalTransactionId() {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.mq.MQXid", "getGlobalTransactionId()", "getter", this.gtrid);
/*     */     }
/*  99 */     return this.gtrid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBranchQualifier() {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.MQXid", "getBranchQualifier()", "getter", this.bqual);
/*     */     }
/* 113 */     return this.bqual;
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
/*     */   public String toString() {
/* 128 */     StringBuffer result = new StringBuffer();
/* 129 */     result.append("format: " + this.formatId);
/* 130 */     result.append(", gtrid: " + byteToHex(this.gtrid));
/* 131 */     result.append(", bqual: " + byteToHex(this.bqual));
/* 132 */     return result.toString();
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
/*     */   public boolean equals(Object obj) {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.MQXid", "equals(Object)", new Object[] { obj });
/*     */     }
/* 150 */     Xid other = null;
/* 151 */     if (!(obj instanceof Xid)) {
/*     */       
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(false), 1);
/*     */       }
/* 156 */       return false;
/*     */     } 
/*     */     
/* 159 */     other = (Xid)obj;
/*     */     
/* 161 */     if (this.formatId != other.getFormatId()) {
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(false), 2);
/*     */       }
/* 165 */       return false;
/*     */     } 
/*     */     
/* 168 */     byte[] otherGtrid = other.getGlobalTransactionId();
/* 169 */     if (this.gtrid.length != otherGtrid.length) {
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(false), 3);
/*     */       }
/* 173 */       return false;
/*     */     } 
/* 175 */     for (int i = 0; i < this.gtrid.length; i++) {
/* 176 */       if (this.gtrid[i] != otherGtrid[i]) {
/* 177 */         if (Trace.isOn) {
/* 178 */           Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(false), 4);
/*     */         }
/* 180 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     byte[] otherBqual = other.getBranchQualifier();
/* 185 */     if (this.bqual.length != otherBqual.length) {
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(false), 5);
/*     */       }
/* 189 */       return false;
/*     */     } 
/* 191 */     for (int j = 0; j < this.bqual.length; j++) {
/* 192 */       if (this.bqual[j] != otherBqual[j]) {
/* 193 */         if (Trace.isOn) {
/* 194 */           Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(false), 6);
/*     */         }
/* 196 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.exit(this, "com.ibm.mq.MQXid", "equals(Object)", Boolean.valueOf(true), 7);
/*     */     }
/* 203 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.MQXid", "hashCode()");
/*     */     }
/*     */ 
/*     */     
/* 214 */     int result = this.formatId + 43 * Arrays.hashCode(this.gtrid) + 47 * Arrays.hashCode(this.bqual);
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.MQXid", "hashCode()", Integer.valueOf(result));
/*     */     }
/* 218 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   static final char[] Hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */   
/*     */   static String byteToHex(byte[] data) {
/* 227 */     StringBuffer result = new StringBuffer(128);
/* 228 */     if (data != null) {
/* 229 */       for (int i = 0; i < data.length; i++) {
/* 230 */         result.append(Hex[data[i] >> 4 & 0xF]);
/* 231 */         result.append(Hex[data[i] & 0xF]);
/*     */       } 
/*     */     }
/* 234 */     String traceRet1 = result.toString();
/* 235 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQXid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */