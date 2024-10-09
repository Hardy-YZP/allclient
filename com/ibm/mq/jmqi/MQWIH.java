/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
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
/*     */ public class MQWIH
/*     */   extends AbstractMqHeaderStructure
/*     */ {
/*     */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQWIH.java";
/*     */   private static final int SIZEOF_SERVICENAME = 32;
/*     */   private static final int SIZEOF_SERVICESTEP = 8;
/*     */   private static final int SIZEOF_MSGTOKEN = 16;
/*     */   private static final int SIZEOF_RESERVED = 32;
/*     */   private static final String BLANK8 = "        ";
/*     */   private static final String BLANK32 = "                                ";
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.mq.jmqi.MQWIH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQWIH.java");
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
/*  76 */   private String serviceName = "                                ";
/*  77 */   private String serviceStep = "        ";
/*  78 */   private byte[] msgToken = CMQC.MQMTOK_NONE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQWIH(JmqiEnvironment env) {
/*  86 */     super(env);
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.jmqi.MQWIH", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*  90 */     MQHeader mqHeader = env.newMQHeader();
/*  91 */     mqHeader.setStrucId("WIH ");
/*  92 */     mqHeader.setVersion(1);
/*  93 */     mqHeader.setStrucLength(120);
/*  94 */     setMqHeader(mqHeader);
/*     */     
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.jmqi.MQWIH", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQWIH(JmqiEnvironment env, MQHeader mqHeader) {
/* 108 */     super(env, mqHeader);
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.jmqi.MQWIH", "<init>(JmqiEnvironment,MQHeader)", new Object[] { env, mqHeader });
/*     */     }
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.MQWIH", "<init>(JmqiEnvironment,MQHeader)");
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
/*     */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry("com.ibm.mq.jmqi.MQWIH", "getSizeV1(JmqiEnvironment,int)", new Object[] { env, 
/* 129 */             Integer.valueOf(ptrSize) });
/*     */     }
/* 131 */     int size = MQHeader.getSize(env, ptrSize);
/* 132 */     size += 88;
/*     */     
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit("com.ibm.mq.jmqi.MQWIH", "getSizeV1(JmqiEnvironment,int)", Integer.valueOf(size));
/*     */     }
/*     */     
/* 138 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrsize) throws JmqiException {
/*     */     int size;
/*     */     JmqiException e;
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry("com.ibm.mq.jmqi.MQWIH", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 153 */             Integer.valueOf(version), Integer.valueOf(ptrsize) });
/*     */     }
/*     */     
/* 156 */     switch (version) {
/*     */       case 1:
/* 158 */         size = getSizeV1(env, ptrsize);
/*     */         break;
/*     */       default:
/* 161 */         e = new JmqiException(env, -1, null, 2, 2142, null);
/*     */ 
/*     */         
/* 164 */         if (Trace.isOn) {
/* 165 */           Trace.throwing("com.ibm.mq.jmqi.MQWIH", "getSize(JmqiEnvironment,int,int)", e);
/*     */         }
/* 167 */         throw e;
/*     */     } 
/*     */ 
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit("com.ibm.mq.jmqi.MQWIH", "getSize(JmqiEnvironment,int,int)", 
/* 173 */           Integer.valueOf(size));
/*     */     }
/* 175 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 183 */     if (Trace.isOn)
/* 184 */       Trace.entry(this, "com.ibm.mq.jmqi.MQWIH", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 185 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 187 */     int size = getSize(this.env, getMqHeader().getVersion(), ptrSize);
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.jmqi.MQWIH", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 191 */           Integer.valueOf(size));
/*     */     }
/* 193 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jmqi.MQWIH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 205 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 207 */     int pos = offset;
/* 208 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 210 */     MQHeader mqHeader = getMqHeader();
/* 211 */     int version = mqHeader.getVersion();
/* 212 */     int strucLength = getSize(this.env, version, ptrSize);
/* 213 */     mqHeader.setStrucLength(strucLength);
/* 214 */     pos += mqHeader.writeToBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 216 */     dc.writeMQField(this.serviceName, buffer, pos, 32, cp, tls);
/* 217 */     pos += 32;
/*     */     
/* 219 */     dc.writeMQField(this.serviceStep, buffer, pos, 8, cp, tls);
/* 220 */     pos += 8;
/*     */     
/* 222 */     System.arraycopy(this.msgToken, 0, buffer, pos, 16);
/* 223 */     pos += 16;
/*     */     
/* 225 */     dc.clear(buffer, pos, 32);
/* 226 */     pos += 32;
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jmqi.MQWIH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 230 */           Integer.valueOf(pos));
/*     */     }
/* 232 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.mq.jmqi.MQWIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 244 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 247 */     MQHeader mqHeader = getMqHeader();
/* 248 */     int pos = mqHeader.readFromBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 250 */     String strucId = mqHeader.getStrucId();
/* 251 */     if (!strucId.equals("WIH ")) {
/* 252 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2333, null);
/*     */ 
/*     */       
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQWIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 259 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 263 */     pos += readBodyFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.jmqi.MQWIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 267 */           Integer.valueOf(pos));
/*     */     }
/* 269 */     return pos;
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
/*     */   public int readBodyFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.entry(this, "com.ibm.mq.jmqi.MQWIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 289 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 291 */     int pos = offset;
/* 292 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 293 */     MQHeader mqHeader = getMqHeader();
/*     */     
/* 295 */     this.serviceName = dc.readMQField(buffer, pos, 32, cp, tls);
/* 296 */     pos += 32;
/*     */     
/* 298 */     this.serviceStep = dc.readMQField(buffer, pos, 8, cp, tls);
/* 299 */     pos += 8;
/*     */     
/* 301 */     System.arraycopy(buffer, pos, this.msgToken, 0, 16);
/* 302 */     pos += 16;
/*     */     
/* 304 */     pos += 32;
/* 305 */     if (pos - offset + MQHeader.getSize(this.env, ptrSize) != mqHeader.getStrucLength()) {
/*     */       
/* 307 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2142, null);
/*     */ 
/*     */       
/* 310 */       if (Trace.isOn) {
/* 311 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQWIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*     */       }
/*     */       
/* 314 */       throw e;
/*     */     } 
/*     */     
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.exit(this, "com.ibm.mq.jmqi.MQWIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 319 */           Integer.valueOf(pos));
/*     */     }
/* 321 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 333 */     getMqHeader().addFieldsToFormatter(fmt);
/* 334 */     fmt.add("serviceName", this.serviceName);
/* 335 */     fmt.add("serviceStep", this.serviceStep);
/* 336 */     fmt.add("msgToken", this.msgToken);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQWIH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */