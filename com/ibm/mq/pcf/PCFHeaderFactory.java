/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class PCFHeaderFactory
/*     */   implements MQHeaderFactory
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFHeaderFactory.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.pcf.PCFHeaderFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFHeaderFactory.java");
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
/*     */   public MQHeader create(String type) throws InstantiationException {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeaderFactory", "create(String)", new Object[] { type });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  71 */       MQHeader traceRet1 = (MQHeader)Class.forName(getClass().getPackage().getName() + "." + type).newInstance();
/*  72 */       if (Trace.isOn) {
/*  73 */         Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "create(String)", traceRet1);
/*     */       }
/*  75 */       return traceRet1;
/*     */     
/*     */     }
/*  78 */     catch (IllegalAccessException e) {
/*  79 */       if (Trace.isOn) {
/*  80 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeaderFactory", "create(String)", e, 1);
/*     */       }
/*  82 */       InstantiationException traceRet2 = new InstantiationException(e.toString());
/*  83 */       if (Trace.isOn) {
/*  84 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFHeaderFactory", "create(String)", traceRet2, 1);
/*     */       }
/*  86 */       throw traceRet2;
/*     */     
/*     */     }
/*  89 */     catch (ClassNotFoundException e) {
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFHeaderFactory", "create(String)", e, 2);
/*     */       }
/*  93 */       InstantiationException traceRet3 = new InstantiationException(e.toString());
/*  94 */       if (Trace.isOn) {
/*  95 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFHeaderFactory", "create(String)", traceRet3, 2);
/*     */       }
/*  97 */       throw traceRet3;
/*     */     }  } public MQHeader decode(MQHeaderContext context) throws MQException, IOException { MQCFH mQCFH; MQCFIN mQCFIN; MQCFIL mQCFIL; MQCFST mQCFST; MQCFSL mQCFSL;
/*     */     MQCFBS mQCFBS;
/*     */     MQCFIN64 mQCFIN64;
/*     */     MQCFIL64 mQCFIL64;
/*     */     MQCFGR mQCFGR;
/*     */     MQCFIF mQCFIF;
/*     */     MQCFSF mQCFSF;
/*     */     MQCFBF mQCFBF;
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", new Object[] { context });
/*     */     }
/*     */     
/* 110 */     switch (context.sniff()) {
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
/* 124 */         mQCFH = new MQCFH(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 125 */         if (Trace.isOn) {
/* 126 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFH, 1);
/*     */         }
/*     */         
/* 129 */         return (MQHeader)mQCFH;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 134 */         mQCFIN = new MQCFIN(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 135 */         if (Trace.isOn) {
/* 136 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIN, 2);
/*     */         }
/*     */         
/* 139 */         return (MQHeader)mQCFIN;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 144 */         mQCFIL = new MQCFIL(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 145 */         if (Trace.isOn) {
/* 146 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIL, 3);
/*     */         }
/*     */         
/* 149 */         return (MQHeader)mQCFIL;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 154 */         mQCFST = new MQCFST(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 155 */         if (Trace.isOn) {
/* 156 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFST, 4);
/*     */         }
/*     */         
/* 159 */         return (MQHeader)mQCFST;
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 164 */         mQCFSL = new MQCFSL(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 165 */         if (Trace.isOn) {
/* 166 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFSL, 5);
/*     */         }
/*     */         
/* 169 */         return (MQHeader)mQCFSL;
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 174 */         mQCFBS = new MQCFBS(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 175 */         if (Trace.isOn) {
/* 176 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFBS, 6);
/*     */         }
/*     */         
/* 179 */         return (MQHeader)mQCFBS;
/*     */ 
/*     */ 
/*     */       
/*     */       case 23:
/* 184 */         mQCFIN64 = new MQCFIN64(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 185 */         if (Trace.isOn) {
/* 186 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIN64, 7);
/*     */         }
/*     */         
/* 189 */         return (MQHeader)mQCFIN64;
/*     */ 
/*     */ 
/*     */       
/*     */       case 25:
/* 194 */         mQCFIL64 = new MQCFIL64(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 195 */         if (Trace.isOn) {
/* 196 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIL64, 8);
/*     */         }
/*     */         
/* 199 */         return (MQHeader)mQCFIL64;
/*     */ 
/*     */ 
/*     */       
/*     */       case 20:
/* 204 */         mQCFGR = new MQCFGR(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 205 */         if (Trace.isOn) {
/* 206 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFGR, 9);
/*     */         }
/*     */         
/* 209 */         return (MQHeader)mQCFGR;
/*     */ 
/*     */ 
/*     */       
/*     */       case 13:
/* 214 */         mQCFIF = new MQCFIF(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 215 */         if (Trace.isOn) {
/* 216 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFIF, 10);
/*     */         }
/*     */         
/* 219 */         return (MQHeader)mQCFIF;
/*     */ 
/*     */ 
/*     */       
/*     */       case 14:
/* 224 */         mQCFSF = new MQCFSF(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 225 */         if (Trace.isOn) {
/* 226 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFSF, 11);
/*     */         }
/*     */         
/* 229 */         return (MQHeader)mQCFSF;
/*     */ 
/*     */ 
/*     */       
/*     */       case 15:
/* 234 */         mQCFBF = new MQCFBF(context.getDataInput(), context.nextEncoding(), context.nextCharacterSet());
/* 235 */         if (Trace.isOn) {
/* 236 */           Trace.exit(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", mQCFBF, 12);
/*     */         }
/*     */         
/* 239 */         return (MQHeader)mQCFBF;
/*     */     } 
/*     */     
/* 242 */     MQException traceRet13 = new MQException(2, 3013, context);
/*     */     
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.throwing(this, "com.ibm.mq.pcf.PCFHeaderFactory", "decode(MQHeaderContext)", (Throwable)traceRet13);
/*     */     }
/*     */     
/* 248 */     throw traceRet13; }
/*     */ 
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
/* 260 */     Collection<String> traceRet1 = Arrays.asList(new String[] { "MQADMIN ", "MQEVENT ", "MQPCF   " });
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.pcf.PCFHeaderFactory", "getSupportedFormats()", "getter", traceRet1);
/*     */     }
/*     */     
/* 265 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getSupportedTypes() {
/* 275 */     Collection<String> traceRet1 = Arrays.asList(new String[] { 
/*     */           "MQCFH", "MQCFIN", "MQCFIL", "MQCFST", "MQCFSL", "MQCFIN64", "MQCFIL64", "MQCFBS", "MQCFGR", "MQCFIF", 
/* 277 */           "MQCFSF", "MQCFBF" }); if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.pcf.PCFHeaderFactory", "getSupportedTypes()", "getter", traceRet1);
/*     */     }
/*     */     
/* 281 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFHeaderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */