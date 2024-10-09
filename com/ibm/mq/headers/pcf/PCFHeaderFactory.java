/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.MQHeader;
/*     */ import com.ibm.mq.headers.MQHeaderContext;
/*     */ import com.ibm.mq.headers.MQHeaderFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PCFHeaderFactory
/*     */   implements MQHeaderFactory
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFHeaderFactory.java";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.headers.pcf.PCFHeaderFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFHeaderFactory.java");
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
/*     */   public MQHeader create(String type) throws InstantiationException {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "create(String)", new Object[] { type });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  70 */       MQHeader traceRet1 = (MQHeader)Class.forName(getClass().getPackage().getName() + "." + type).newInstance();
/*  71 */       if (Trace.isOn) {
/*  72 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "create(String)", traceRet1);
/*     */       }
/*     */       
/*  75 */       return traceRet1;
/*     */     
/*     */     }
/*  78 */     catch (IllegalAccessException e) {
/*  79 */       if (Trace.isOn) {
/*  80 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "create(String)", e, 1);
/*     */       }
/*  82 */       InstantiationException traceRet2 = new InstantiationException(e.toString());
/*  83 */       if (Trace.isOn) {
/*  84 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "create(String)", traceRet2, 1);
/*     */       }
/*     */       
/*  87 */       throw traceRet2;
/*     */     
/*     */     }
/*  90 */     catch (ClassNotFoundException e) {
/*  91 */       if (Trace.isOn) {
/*  92 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "create(String)", e, 2);
/*     */       }
/*  94 */       InstantiationException traceRet3 = new InstantiationException(e.toString());
/*  95 */       if (Trace.isOn) {
/*  96 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "create(String)", traceRet3, 2);
/*     */       }
/*     */       
/*  99 */       throw traceRet3;
/*     */     }  } public MQHeader decode(MQHeaderContext context) throws MQDataException, IOException { MQCFH mQCFH; MQCFIN mQCFIN; MQCFIL mQCFIL; MQCFST mQCFST; MQCFSL mQCFSL;
/*     */     MQCFBS mQCFBS;
/*     */     MQCFIN64 mQCFIN64;
/*     */     MQCFIL64 mQCFIL64;
/*     */     MQCFGR mQCFGR;
/*     */     MQCFIF mQCFIF;
/*     */     MQCFSF mQCFSF;
/*     */     MQCFBF mQCFBF;
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", new Object[] { context });
/*     */     }
/*     */     
/* 112 */     switch (context.sniff()) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/*     */       case 7:
/*     */       case 12:
/*     */       case 16:
/*     */       case 17:
/*     */       case 18:
/*     */       case 19:
/*     */       case 21:
/*     */       case 22:
/*     */       case 26:
/*     */       case 27:
/* 126 */         mQCFH = new MQCFH(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 127 */         if (Trace.isOn) {
/* 128 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFH, 1);
/*     */         }
/*     */         
/* 131 */         return (MQHeader)mQCFH;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 136 */         mQCFIN = new MQCFIN(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 137 */         if (Trace.isOn) {
/* 138 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIN, 2);
/*     */         }
/*     */         
/* 141 */         return (MQHeader)mQCFIN;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 146 */         mQCFIL = new MQCFIL(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 147 */         if (Trace.isOn) {
/* 148 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIL, 3);
/*     */         }
/*     */         
/* 151 */         return (MQHeader)mQCFIL;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 156 */         mQCFST = new MQCFST(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 157 */         if (Trace.isOn) {
/* 158 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFST, 4);
/*     */         }
/*     */         
/* 161 */         return (MQHeader)mQCFST;
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 166 */         mQCFSL = new MQCFSL(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 167 */         if (Trace.isOn) {
/* 168 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFSL, 5);
/*     */         }
/*     */         
/* 171 */         return (MQHeader)mQCFSL;
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 176 */         mQCFBS = new MQCFBS(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 177 */         if (Trace.isOn) {
/* 178 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFBS, 6);
/*     */         }
/*     */         
/* 181 */         return (MQHeader)mQCFBS;
/*     */ 
/*     */ 
/*     */       
/*     */       case 23:
/* 186 */         mQCFIN64 = new MQCFIN64(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 187 */         if (Trace.isOn) {
/* 188 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIN64, 7);
/*     */         }
/*     */         
/* 191 */         return (MQHeader)mQCFIN64;
/*     */ 
/*     */ 
/*     */       
/*     */       case 25:
/* 196 */         mQCFIL64 = new MQCFIL64(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 197 */         if (Trace.isOn) {
/* 198 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIL64, 8);
/*     */         }
/*     */         
/* 201 */         return (MQHeader)mQCFIL64;
/*     */ 
/*     */ 
/*     */       
/*     */       case 20:
/* 206 */         mQCFGR = new MQCFGR(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 207 */         if (Trace.isOn) {
/* 208 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFGR, 9);
/*     */         }
/*     */         
/* 211 */         return (MQHeader)mQCFGR;
/*     */ 
/*     */ 
/*     */       
/*     */       case 13:
/* 216 */         mQCFIF = new MQCFIF(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 217 */         if (Trace.isOn) {
/* 218 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIF, 10);
/*     */         }
/*     */         
/* 221 */         return (MQHeader)mQCFIF;
/*     */ 
/*     */ 
/*     */       
/*     */       case 14:
/* 226 */         mQCFSF = new MQCFSF(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 227 */         if (Trace.isOn) {
/* 228 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFSF, 11);
/*     */         }
/*     */         
/* 231 */         return (MQHeader)mQCFSF;
/*     */ 
/*     */ 
/*     */       
/*     */       case 15:
/* 236 */         mQCFBF = new MQCFBF(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 237 */         if (Trace.isOn) {
/* 238 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFBF, 12);
/*     */         }
/*     */         
/* 241 */         return (MQHeader)mQCFBF;
/*     */     } 
/*     */     
/* 244 */     MQDataException traceRet13 = new MQDataException(2, 3013, context);
/*     */     
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", (Throwable)traceRet13);
/*     */     }
/*     */     
/* 250 */     throw traceRet13; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getSupportedFormats() {
/* 262 */     Collection<String> traceRet1 = Arrays.asList(new String[] { "MQADMIN ", "MQEVENT ", "MQPCF   " });
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "getSupportedFormats()", "getter", traceRet1);
/*     */     }
/*     */     
/* 267 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getSupportedTypes() {
/* 277 */     Collection<String> traceRet1 = Arrays.asList(new String[] { 
/*     */           "MQCFH", "MQCFIN", "MQCFIL", "MQCFST", "MQCFSL", "MQCFIN64", "MQCFIL64", "MQCFBS", "MQCFGR", "MQCFIF", 
/* 279 */           "MQCFSF", "MQCFBF" }); if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFHeaderFactory", "getSupportedTypes()", "getter", traceRet1);
/*     */     }
/*     */     
/* 283 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFHeaderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */