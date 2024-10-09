/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpXA_INFO
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXA_INFO.java";
/*     */   private static final int XA_INFO_LENGTH_OFFSET = 0;
/*     */   private static final int XA_INFO_OFFSET = 1;
/*     */   public static final int SIZE = 257;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXA_INFO.java");
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
/*     */   public RfpXA_INFO(JmqiEnvironment env, byte[] buffer, int offset) {
/*  58 */     super(env, buffer, offset);
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
/*     */   public void setXaInfo(String xaInfo, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO", "setXaInfo(String,JmqiCodepage,JmqiTls)", new Object[] { xaInfo, cp, tls });
/*     */     }
/*     */     
/*  86 */     int length = this.dc.writeNullTerminatedField(xaInfo, this.buffer, this.offset + 1, cp, tls);
/*  87 */     this.buffer[this.offset + 0] = (byte)length;
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO", "setXaInfo(String,JmqiCodepage,JmqiTls)");
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
/*     */   public String getXaInfo(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO", "getXaInfo(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 106 */     String xaInfoStr = null;
/* 107 */     int stringLength = this.buffer[this.offset + 0] & 0xFF;
/* 108 */     if (stringLength >= 0 && stringLength <= 255) {
/* 109 */       xaInfoStr = this.dc.readField(this.buffer, this.offset + 1, stringLength, cp, tls);
/*     */     }
/*     */     else {
/*     */       
/* 113 */       HashMap<String, Object> info = new HashMap<>();
/* 114 */       info.put("stringLength", Integer.valueOf(stringLength));
/* 115 */       info.put("Description", "Invalid xa_info_length encountered");
/* 116 */       Trace.ffst(this, "sendTSH(RemoteTls,RfpTSH,RemoteSession)", "01", info, null);
/* 117 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO", "getXaInfo(JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 123 */       throw traceRet1;
/*     */     } 
/*     */     
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO", "getXaInfo(JmqiCodepage,JmqiTls)", xaInfoStr);
/*     */     }
/*     */     
/* 130 */     return xaInfoStr;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpXA_INFO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */