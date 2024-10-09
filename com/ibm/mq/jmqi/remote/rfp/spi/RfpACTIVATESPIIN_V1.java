/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RfpACTIVATESPIIN_V1
/*     */   extends RfpACTIVATESPIIN
/*     */ {
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_QNAME = 48;
/*     */   private static final int SIZEOF_QMGRNAME = 48;
/*     */   private static final int OPTIONS_OFFSET = 12;
/*     */   private static final int QNAME_OFFSET = 16;
/*     */   private static final int QMGRNAME_OFFSET = 64;
/*     */   private static final int MSGID_OFFSET = 112;
/*     */   
/*     */   static {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIIN.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpACTIVATESPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 147 */     super(env, buffer, offset, 1);
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "<init>(JmqiEnvironment,byte [ ],int)", new Object[] { env, buffer, 
/* 150 */             Integer.valueOf(offset) });
/*     */     }
/*     */     
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "<init>(JmqiEnvironment,byte [ ],int)");
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
/*     */   
/*     */   public void setOptions(int options, boolean swap) {
/* 185 */     if (Trace.isOn)
/* 186 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setOptions(int,boolean)", new Object[] {
/* 187 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           }); 
/* 189 */     this.dc.writeI32(options, getRfpBuffer(), this.offset + 12, swap);
/*     */ 
/*     */     
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setOptions(int,boolean)");
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
/*     */   public void setQMgrName(String QMgrName, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setQMgrName(String,JmqiCodepage,JmqiTls)", new Object[] { QMgrName, cp, tls });
/*     */     }
/*     */     
/* 210 */     this.dc.writeMQField(QMgrName, getRfpBuffer(), this.offset + 64, 48, cp, tls);
/*     */ 
/*     */     
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setQMgrName(String,JmqiCodepage,JmqiTls)");
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
/*     */   public void setQName(String QName, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setQName(String,JmqiCodepage,JmqiTls)", new Object[] { QName, cp, tls });
/*     */     }
/*     */     
/* 230 */     this.dc.writeMQField(QName, getRfpBuffer(), this.offset + 16, 48, cp, tls);
/*     */ 
/*     */     
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setQName(String,JmqiCodepage,JmqiTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgId(byte[] msgId) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "setMsgId(byte [ ])", "setter", msgId);
/*     */     }
/*     */     
/* 249 */     System.arraycopy(msgId, 0, getRfpBuffer(), this.offset + 112, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 257 */     int traceRet1 = 136;
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN_V1", "getStructSize()", "getter", 
/* 260 */           Integer.valueOf(traceRet1));
/*     */     }
/* 262 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpACTIVATESPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */