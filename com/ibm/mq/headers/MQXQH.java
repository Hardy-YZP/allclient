/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXQH
/*     */   extends Header
/*     */   implements MQChainable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQXQH.java";
/*     */   public static final int SIZE = 428;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.headers.MQXQH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQXQH.java");
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
/*  67 */   static final HeaderType TYPE = new HeaderType("MQXQH");
/*     */   
/*  69 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "XQH ");
/*  70 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  71 */   static final HeaderField RemoteQName = TYPE.addMQChar("RemoteQName", 48);
/*  72 */   static final HeaderField RemoteQMgrName = TYPE.addMQChar("RemoteQMgrName", 48);
/*     */   
/*  74 */   static final HeaderField MsgDesc = TYPE.addMQHeader("MsgDesc", MQMD1.TYPE, MQMD1.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQXQH() {
/*  80 */     super(TYPE);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "<init>()");
/*     */     }
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "<init>()");
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
/*     */   public MQXQH(DataInput message) throws MQDataException, IOException {
/*  98 */     this();
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 102 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput)");
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
/*     */   public MQXQH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 121 */     this();
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 124 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 127 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 129 */     catch (MQDataException mde) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.throwing(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 137 */       throw mde;
/*     */     }
/* 139 */     catch (IOException ioe) {
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.throwing(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 146 */       throw ioe;
/*     */     }
/* 148 */     catch (Exception e) {
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 152 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.throwing(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 157 */       throw traceRet1;
/*     */     } 
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "<init>(DataInput,int,int)");
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
/*     */   public String getStrucId() {
/* 173 */     String traceRet1 = getStringValue(StrucId);
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 177 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 186 */     int traceRet1 = getIntValue(Version);
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "getVersion()", "getter", 
/* 189 */           Integer.valueOf(traceRet1));
/*     */     }
/* 191 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRemoteQName() {
/* 200 */     String traceRet1 = getStringValue(RemoteQName);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "getRemoteQName()", "getter", traceRet1);
/*     */     }
/* 204 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRemoteQName(String value) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "setRemoteQName(String)", "setter", value);
/*     */     }
/* 216 */     setStringValue(RemoteQName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRemoteQMgrName() {
/* 225 */     String traceRet1 = getStringValue(RemoteQMgrName);
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "getRemoteQMgrName()", "getter", traceRet1);
/*     */     }
/* 229 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRemoteQMgrName(String value) {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "setRemoteQMgrName(String)", "setter", value);
/*     */     }
/* 241 */     setStringValue(RemoteQMgrName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMD1 getMsgDesc() {
/* 250 */     MQMD1 traceRet1 = (MQMD1)getValue(MsgDesc);
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "getMsgDesc()", "getter", traceRet1);
/*     */     }
/* 254 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgDesc(MQMD1 value) {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.headers.MQXQH", "setMsgDesc(MQMD1)", "setter", value);
/*     */     }
/* 266 */     setValue(MsgDesc, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "size()");
/*     */     }
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "size()", Integer.valueOf(428));
/*     */     }
/* 282 */     return 428;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "nextEncoding()");
/*     */     }
/* 293 */     int traceRet1 = getMsgDesc().getEncoding();
/*     */     
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 298 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 306 */     if (Trace.isOn)
/* 307 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "nextEncoding(int)", new Object[] {
/* 308 */             Integer.valueOf(value)
/*     */           }); 
/* 310 */     getMsgDesc().setEncoding(value);
/*     */     
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "nextCharacterSet()");
/*     */     }
/* 326 */     int traceRet1 = getMsgDesc().getCodedCharSetId();
/*     */     
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "nextCharacterSet()", 
/* 330 */           Integer.valueOf(traceRet1));
/*     */     }
/* 332 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 340 */     if (Trace.isOn)
/* 341 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "nextCharacterSet(int)", new Object[] {
/* 342 */             Integer.valueOf(value)
/*     */           }); 
/* 344 */     getMsgDesc().setCodedCharSetId(value);
/*     */     
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "nextFormat()");
/*     */     }
/* 360 */     String traceRet1 = getMsgDesc().getFormat();
/*     */     
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "nextFormat()", traceRet1);
/*     */     }
/* 365 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 376 */     getMsgDesc().setFormat(value);
/*     */     
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.entry(this, "com.ibm.mq.headers.MQXQH", "format()");
/*     */     }
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.exit(this, "com.ibm.mq.headers.MQXQH", "format()", "MQXMIT  ");
/*     */     }
/* 395 */     return "MQXMIT  ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQXQH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */