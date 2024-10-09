/*     */ package com.ibm.mq.ese.conv;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIHeader;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiCcsidConverter
/*     */   implements CcsidConverter
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/conv/JmqiCcsidConverter.java";
/*     */   private JmqiSystemEnvironment env;
/*     */   private JmqiMQ jmqi;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.ese.conv.JmqiCcsidConverter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/conv/JmqiCcsidConverter.java");
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
/*     */   public JmqiCcsidConverter(JmqiMQ jmqi, JmqiSystemEnvironment env) {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "<init>(JmqiMQ,JmqiSystemEnvironment)", new Object[] { jmqi, env });
/*     */     }
/*     */     
/*  77 */     this.jmqi = jmqi;
/*  78 */     this.env = env;
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "<init>(JmqiMQ,JmqiSystemEnvironment)");
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
/*     */   public byte[] convert(byte[] src, int srcCCSID, int pDstCCSID, int defaultCCSID) throws CcsidException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", new Object[] { src, 
/*  97 */             Integer.valueOf(srcCCSID), Integer.valueOf(pDstCCSID), 
/*  98 */             Integer.valueOf(defaultCCSID) });
/*     */     }
/*     */     
/* 101 */     int dstCCSID = pDstCCSID;
/* 102 */     if (dstCCSID == 0) {
/* 103 */       dstCCSID = defaultCCSID;
/*     */     }
/* 105 */     if (srcCCSID == dstCCSID) {
/* 106 */       if (Trace.isOn) {
/* 107 */         Trace.exit(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", src, 1);
/*     */       }
/*     */       
/* 110 */       return src;
/*     */     } 
/* 112 */     if (srcCCSID == 0) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.traceInfo(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte[], int, int, int)", "Message CCSID is 0", "");
/*     */       }
/*     */ 
/*     */       
/* 118 */       CcsidException ex = new CcsidException(AmsErrorMessages.mjm_msg_error_recv_getting_ccsid_and_encoding);
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.throwing(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", (Throwable)ex, 1);
/*     */       }
/*     */       
/* 123 */       throw ex;
/*     */     } 
/* 125 */     byte[] ret = null;
/*     */     try {
/* 127 */       JmqiCodepage cpSrc = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)this.env, srcCCSID);
/* 128 */       JmqiCodepage cpDst = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)this.env, dstCCSID);
/* 129 */       JmqiDC dc = this.env.getDC();
/* 130 */       ByteBuffer srcBuff = ByteBuffer.wrap(src);
/* 131 */       ByteBuffer targ = dc.convertBuffer(srcBuff, cpSrc, cpDst);
/* 132 */       ret = new byte[targ.limit()];
/* 133 */       System.arraycopy(targ.array(), 0, ret, 0, ret.length);
/*     */     }
/* 135 */     catch (JmqiException e) {
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.catchBlock(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 140 */       int componentId = this.jmqi.getTlsComponentId();
/* 141 */       JmqiComponentTls componentTls = this.env.getComponentTls(componentId);
/* 142 */       JmqiTls jTls = this.env.getJmqiTls(componentTls);
/* 143 */       jTls.lastException = e;
/*     */       
/* 145 */       HashMap<String, Object> inserts = new HashMap<>();
/* 146 */       inserts.put("AMS_INSERT_SOURCE_CCSID", Integer.toString(srcCCSID));
/* 147 */       inserts.put("AMS_INSERT_TARGET_CCSID", Integer.toString(dstCCSID));
/* 148 */       inserts.put("AMS_INSERT_MQ_COMPLETION_CODE", Integer.valueOf(e.getCompCode()).toString());
/* 149 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(e.getReason()).toString());
/* 150 */       CcsidException ex = new CcsidException(AmsErrorMessages.mqm_s_get_data_conversion_failed, inserts, (Throwable)e);
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 155 */       throw ex;
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.catchBlock(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", e, 2);
/*     */       }
/*     */ 
/*     */       
/* 163 */       HashMap<String, Object> inserts = new HashMap<>();
/* 164 */       inserts.put("AMS_INSERT_SOURCE_CCSID", Integer.toString(srcCCSID));
/* 165 */       inserts.put("AMS_INSERT_TARGET_CCSID", Integer.toString(dstCCSID));
/* 166 */       inserts.put("AMS_INSERT_MQ_COMPLETION_CODE", Integer.valueOf(2).toString());
/* 167 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(2195).toString());
/* 168 */       CcsidException ex = new CcsidException(AmsErrorMessages.mqm_s_get_data_conversion_failed, inserts, e);
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.throwing(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", (Throwable)ex, 3);
/*     */       }
/*     */       
/* 173 */       throw ex;
/*     */     } 
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convert(byte [ ],int,int,int)", ret, 2);
/*     */     }
/*     */     
/* 180 */     return ret;
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
/*     */   public void convertHeader(AMBIHeader header) throws CcsidException {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convertHeader(AMBIHeader)", new Object[] { header });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 199 */     int ccsid = header.getHeaderCCSID();
/* 200 */     if (ccsid == 0 && 
/* 201 */       Trace.isOn) {
/* 202 */       Trace.traceInfo(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convertHeader(AMBIHeader)", "header CCSID is 0", "");
/*     */     }
/*     */ 
/*     */     
/* 206 */     if (ccsid != 1208) {
/* 207 */       JmqiDC dc = this.env.getDC();
/* 208 */       byte[] formatData = header.getOrigFormatData();
/* 209 */       byte[] dynamicQueueData = header.getDynamicQueueNameData();
/*     */       
/*     */       try {
/* 212 */         JmqiCodepage cpSrc = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)this.env, ccsid);
/*     */         
/* 214 */         if (cpSrc == null) {
/* 215 */           throw new UnsupportedEncodingException(Integer.toString(ccsid));
/*     */         }
/*     */         
/* 218 */         JmqiCodepage cpDst = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)this.env, 1208);
/*     */ 
/*     */         
/* 221 */         if (cpDst == null) {
/* 222 */           throw new UnsupportedEncodingException(Integer.toString(1208));
/*     */         }
/*     */ 
/*     */         
/* 226 */         if (formatData != null) {
/* 227 */           ByteBuffer src = ByteBuffer.wrap(formatData);
/* 228 */           ByteBuffer dst = dc.convertBuffer(src, cpSrc, cpDst);
/* 229 */           header.setOrigFormat(dst.array());
/*     */         } 
/*     */         
/* 232 */         if (dynamicQueueData != null) {
/* 233 */           ByteBuffer src = ByteBuffer.wrap(dynamicQueueData);
/* 234 */           ByteBuffer dst = dc.convertBuffer(src, cpSrc, cpDst);
/* 235 */           header.setDynamicQueueName(dst.array());
/*     */         }
/*     */       
/* 238 */       } catch (JmqiException|UnsupportedEncodingException e) {
/* 239 */         if (Trace.isOn) {
/* 240 */           Trace.catchBlock(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convertHeader(AMBIHeader)", e);
/*     */         }
/*     */         
/* 243 */         HashMap<String, Object> inserts = new HashMap<>();
/* 244 */         inserts.put("AMS_INSERT_CHARACTER_ENCODING", Integer.valueOf(ccsid).toString());
/* 245 */         CcsidException ex = new CcsidException(AmsErrorMessages.mju_ambi_header_convert_error, inserts, e);
/* 246 */         if (Trace.isOn) {
/* 247 */           Trace.throwing(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convertHeader(AMBIHeader)", (Throwable)ex);
/*     */         }
/* 249 */         throw ex;
/*     */       } 
/*     */     } 
/*     */     
/* 253 */     if (Trace.isOn)
/* 254 */       Trace.exit(this, "com.ibm.mq.ese.conv.JmqiCcsidConverter", "convertHeader(AMBIHeader)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\conv\JmqiCcsidConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */