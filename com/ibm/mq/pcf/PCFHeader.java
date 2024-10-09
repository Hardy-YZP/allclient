/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.CachingHeader;
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.ReflectingStore;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class PCFHeader
/*     */   extends Header
/*     */   implements CachingHeader
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFHeader.java";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.pcf.PCFHeader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFHeader.java");
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
/*  62 */   protected com.ibm.mq.headers.pcf.PCFHeader delegate = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected PCFHeader(HeaderType type) {
/*  67 */     super(type);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeader", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/*  71 */     String myClassName = getClass().getName();
/*  72 */     int lastDotAt = myClassName.lastIndexOf('.');
/*  73 */     String baseClassName = myClassName.substring(lastDotAt + 1);
/*  74 */     String delegateClassName = "com.ibm.mq.headers.pcf." + baseClassName;
/*  75 */     Class<com.ibm.mq.headers.pcf.PCFHeader> delegateClass = null;
/*     */     try {
/*  77 */       delegateClass = (Class)Class.forName(delegateClassName);
/*  78 */       this.delegate = delegateClass.newInstance();
/*     */     }
/*  80 */     catch (Exception e) {
/*  81 */       if (Trace.isOn) {
/*  82 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeader", "<init>(HeaderType)", e);
/*     */       }
/*     */       
/*  85 */       if (Trace.isOn) {
/*  86 */         Trace.exit(this, "com.ibm.mq.pcf.PCFHeader", "<init>(HeaderType)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  92 */     super.delegate = (Header)this.delegate;
/*     */     
/*  94 */     this.delegate.store((Store)new ReflectingStore(this));
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.pcf.PCFHeader", "<init>(HeaderType)", 2);
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
/*     */   public void initialize(MQMessage message) throws MQException, IOException {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", new Object[] { message });
/*     */     }
/*     */     
/*     */     try {
/* 120 */       this.delegate.read((DataInput)message);
/*     */     }
/* 122 */     catch (MQDataException mde) {
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 127 */       MQException traceRet1 = new MQException(mde.completionCode, mde.reasonCode, mde.exceptionSource);
/*     */       
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 133 */       throw traceRet1;
/*     */     } 
/*     */     try {
/* 136 */       readCachedContent();
/*     */     }
/* 138 */     catch (MQException e) {
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", (Throwable)e, 2);
/*     */       }
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", (Throwable)e, 2);
/*     */       }
/* 145 */       throw e;
/*     */     }
/* 147 */     catch (IOException e) {
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", e, 3);
/*     */       }
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", e, 3);
/*     */       }
/* 154 */       throw e;
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", e, 4);
/*     */       }
/* 160 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)", traceRet2, 4);
/*     */       }
/* 164 */       throw traceRet2;
/*     */     } 
/* 166 */     this.delegate.store((Store)new ReflectingStore(this));
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.pcf.PCFHeader", "initialize(MQMessage)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final String getUnqualifiedClassName() {
/* 174 */     String className = getClass().getName();
/*     */     
/* 176 */     if (className.lastIndexOf('.') > 0) {
/* 177 */       className = className.substring(className.lastIndexOf('.') + 1);
/*     */     }
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.data(this, "com.ibm.mq.pcf.PCFHeader", "getUnqualifiedClassName()", "getter", className);
/*     */     }
/*     */     
/* 184 */     return className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(DataOutput message, int encoding, int characterSet) throws IOException {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeader", "write(DataOutput,int,int)", new Object[] { message, 
/* 194 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/* 196 */     if (store().hasData()) {
/* 197 */       writeCachedContent();
/*     */     }
/*     */     
/* 200 */     int traceRet1 = this.delegate.write(message, encoding, characterSet);
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.pcf.PCFHeader", "write(DataOutput,int,int)", 
/* 204 */           Integer.valueOf(traceRet1));
/*     */     }
/* 206 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeader", "toString()");
/*     */     }
/* 220 */     String traceRet1 = (this.delegate != null) ? this.delegate.toString() : "Uninitialised PCFHeader";
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.mq.pcf.PCFHeader", "toString()", traceRet1);
/*     */     }
/* 224 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeader", "size()");
/*     */     }
/* 235 */     int traceRet1 = this.delegate.size();
/*     */     
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.mq.pcf.PCFHeader", "size()", Integer.valueOf(traceRet1));
/*     */     }
/* 240 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getType();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */