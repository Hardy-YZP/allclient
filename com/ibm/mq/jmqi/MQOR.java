/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
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
/*     */ public class MQOR
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQOR.java";
/*     */   private static final int SIZEOF_OBJECT_NAME = 48;
/*     */   private static final int SIZEOF_OBJECT_QMGR_NAME = 48;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.MQOR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQOR.java");
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
/*  60 */   private String objectName = null;
/*  61 */   private String objectQMgrName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int sizeofNativePointer) {
/*  72 */     int structureSize = 96;
/*  73 */     return structureSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQOR(JmqiEnvironment env) {
/*  82 */     super(env);
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOR", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOR", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.data(this, "com.ibm.mq.jmqi.MQOR", "getObjectName()", "getter", this.objectName);
/*     */     }
/* 100 */     return this.objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.mq.jmqi.MQOR", "setObjectName(String)", "setter", objectName);
/*     */     }
/* 111 */     this.objectName = objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectQMgrName() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.jmqi.MQOR", "getObjectQMgrName()", "getter", this.objectQMgrName);
/*     */     }
/* 122 */     return this.objectQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectQMgrName(String objectQMgrName) {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data(this, "com.ibm.mq.jmqi.MQOR", "setObjectQMgrName(String)", "setter", objectQMgrName);
/*     */     }
/*     */     
/* 134 */     this.objectQMgrName = objectQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 145 */     return getSize(this.env, ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 158 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 160 */     int pos = offset;
/* 161 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 163 */     dc.writeMQField(this.objectName, buffer, pos, 48, cp, tls);
/* 164 */     pos += 48;
/*     */     
/* 166 */     dc.writeMQField(this.objectQMgrName, buffer, pos, 48, cp, tls);
/* 167 */     pos += 48;
/*     */     
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 171 */           Integer.valueOf(pos));
/*     */     }
/* 173 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.mq.jmqi.MQOR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 185 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 187 */     int pos = offset;
/* 188 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 190 */     this.objectName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 191 */     pos += 48;
/*     */     
/* 193 */     this.objectQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 194 */     pos += 48;
/*     */     
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jmqi.MQOR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 198 */           Integer.valueOf(pos));
/*     */     }
/* 200 */     return pos;
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
/* 212 */     fmt.add("objectName", this.objectName);
/* 213 */     fmt.add("objectQMgrName", this.objectQMgrName);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQOR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */