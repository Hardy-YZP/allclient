/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
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
/*     */ public class RfpSOCKACT
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpSOCKACT.java";
/*     */   private static final int CONVERSATIONID_OFFSET = 0;
/*     */   private static final int REQUESTID_OFFSET = 4;
/*     */   private static final int TYPE_OFFSET = 8;
/*     */   private static final int PARM1_OFFSET = 12;
/*     */   private static final int PARM2_OFFSET = 16;
/*     */   private static final int QMGR_NAME_OFFSET = 20;
/*     */   private static final int QM_ID_OFFSET = 68;
/*     */   public static final int SIZE_CURRENT = 20;
/*     */   public static final int SIZE_RECONNECT_WITH_NAME = 68;
/*     */   public static final int SIZE_RECONNECT_WITH_ID = 116;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpSOCKACT.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private static final String[] typeStrings = new String[] { "<INVALID>", "rfpSAT_START_CONV", "rfpSAT_END_CONV", "rfpSAT_STOP_QUIESCE", "rfpSAT_PAUSE_DATA_SENDS", "rfpSAT_DATA_SENDS_PAUSED", "rfpSAT_RESUME_DATA_SENDS", "rfpSAT_QM_QUIESCE", "rfpSAT_ACCEPT_CONV", "rfpSAT_RECONNECT_CLIENTS" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpSOCKACT(JmqiEnvironment env, byte[] buffer, int offset) {
/* 100 */     super(env, buffer, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConversationId(int convId, boolean swap) {
/* 107 */     if (Trace.isOn)
/* 108 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setConversationId(int,boolean)", new Object[] {
/* 109 */             Integer.valueOf(convId), Boolean.valueOf(swap)
/*     */           }); 
/* 111 */     this.dc.writeI32(convId, this.buffer, this.offset + 0, swap);
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setConversationId(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequestId(int requestId, boolean swap) {
/* 123 */     if (Trace.isOn)
/* 124 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setRequestId(int,boolean)", new Object[] {
/* 125 */             Integer.valueOf(requestId), Boolean.valueOf(swap)
/*     */           }); 
/* 127 */     this.dc.writeI32(requestId, this.buffer, this.offset + 4, swap);
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setRequestId(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int type, boolean swap) {
/* 139 */     if (Trace.isOn)
/* 140 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setType(int,boolean)", new Object[] {
/* 141 */             RemoteConstantDecoder.formatSingleOption(type, "rfpSAT_"), Boolean.valueOf(swap)
/*     */           }); 
/* 143 */     this.dc.writeI32(type, this.buffer, this.offset + 8, swap);
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setType(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParm1(int parm1, boolean swap) {
/* 155 */     if (Trace.isOn)
/* 156 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setParm1(int,boolean)", new Object[] {
/* 157 */             Integer.valueOf(parm1), Boolean.valueOf(swap)
/*     */           }); 
/* 159 */     this.dc.writeI32(parm1, this.buffer, this.offset + 12, swap);
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setParm1(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParm2(int parm2, boolean swap) {
/* 171 */     if (Trace.isOn)
/* 172 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setParm2(int,boolean)", new Object[] {
/* 173 */             Integer.valueOf(parm2), Boolean.valueOf(swap)
/*     */           }); 
/* 175 */     this.dc.writeI32(parm2, this.buffer, this.offset + 16, swap);
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "setParm2(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConversationId(boolean swap) {
/* 187 */     if (Trace.isOn)
/* 188 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getConversationId(boolean)", new Object[] {
/* 189 */             Boolean.valueOf(swap)
/*     */           }); 
/* 191 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getConversationId(boolean)", 
/* 195 */           Integer.valueOf(traceRet1));
/*     */     }
/* 197 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequestId(boolean swap) {
/* 203 */     if (Trace.isOn)
/* 204 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getRequestId(boolean)", new Object[] {
/* 205 */             Boolean.valueOf(swap)
/*     */           }); 
/* 207 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getRequestId(boolean)", 
/* 211 */           Integer.valueOf(traceRet1));
/*     */     }
/* 213 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType(boolean swap) {
/* 219 */     if (Trace.isOn)
/* 220 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getType(boolean)", new Object[] {
/* 221 */             Boolean.valueOf(swap)
/*     */           }); 
/* 223 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 8, swap);
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getType(boolean)", 
/* 227 */           RemoteConstantDecoder.formatSingleOption(traceRet1, "rfpSAT_"));
/*     */     }
/* 229 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString(boolean swap) {
/* 237 */     int type = getType(swap);
/* 238 */     return (type >= 0 && type < typeStrings.length) ? typeStrings[type] : String.format("invalid type %d", new Object[] { Integer.valueOf(type) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParm1(boolean swap) {
/* 244 */     if (Trace.isOn)
/* 245 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getParm1(boolean)", new Object[] {
/* 246 */             Boolean.valueOf(swap)
/*     */           }); 
/* 248 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getParm1(boolean)", 
/* 252 */           Integer.valueOf(traceRet1));
/*     */     }
/* 254 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParm2(boolean swap) {
/* 260 */     if (Trace.isOn)
/* 261 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getParm2(boolean)", new Object[] {
/* 262 */             Boolean.valueOf(swap)
/*     */           }); 
/* 264 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getParm2(boolean)", 
/* 268 */           Integer.valueOf(traceRet1));
/*     */     }
/* 270 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMgrName(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getQMgrName(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 282 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 20, 48, cp, tls);
/*     */     
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getQMgrName(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 288 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQmId(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getQmId(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 300 */     String traceRet1 = this.dc.readMQField(this.buffer, this.offset + 68, 48, cp, tls);
/*     */     
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT", "getQmId(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 306 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpSOCKACT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */