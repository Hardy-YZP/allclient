/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.CCSID;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.MQHeader;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PCFParameter
/*     */   extends PCFHeader
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFParameter.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.headers.pcf.PCFParameter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFParameter.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   static final String[] pads = new String[] { "", " ", "  ", "   ", "" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PCFParameter nextParameter(DataInput message) throws MQDataException, IOException {
/*     */     PCFParameter traceRet1, traceRet2, traceRet3, traceRet4, traceRet5, traceRet6, traceRet7, traceRet8, traceRet9, traceRet10, traceRet11;
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  74 */     MessageWrapper wrappedMessage = MessageWrapper.wrap(message);
/*  75 */     int type = wrappedMessage.getMessageType();
/*     */     
/*  77 */     switch (type) {
/*     */       case 3:
/*  79 */         traceRet1 = new MQCFIN(message);
/*  80 */         if (Trace.isOn) {
/*  81 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet1, 1);
/*     */         }
/*     */         
/*  84 */         return traceRet1;
/*     */       
/*     */       case 5:
/*  87 */         traceRet2 = new MQCFIL(message);
/*  88 */         if (Trace.isOn) {
/*  89 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet2, 2);
/*     */         }
/*     */         
/*  92 */         return traceRet2;
/*     */       
/*     */       case 4:
/*  95 */         traceRet3 = new MQCFST(message);
/*  96 */         if (Trace.isOn) {
/*  97 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet3, 3);
/*     */         }
/*     */         
/* 100 */         return traceRet3;
/*     */       
/*     */       case 6:
/* 103 */         traceRet4 = new MQCFSL(message);
/* 104 */         if (Trace.isOn) {
/* 105 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet4, 4);
/*     */         }
/*     */         
/* 108 */         return traceRet4;
/*     */       
/*     */       case 9:
/* 111 */         traceRet5 = new MQCFBS(message);
/* 112 */         if (Trace.isOn) {
/* 113 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet5, 5);
/*     */         }
/*     */         
/* 116 */         return traceRet5;
/*     */       
/*     */       case 23:
/* 119 */         traceRet6 = new MQCFIN64(message);
/* 120 */         if (Trace.isOn) {
/* 121 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet6, 6);
/*     */         }
/*     */         
/* 124 */         return traceRet6;
/*     */       
/*     */       case 25:
/* 127 */         traceRet7 = new MQCFIL64(message);
/* 128 */         if (Trace.isOn) {
/* 129 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet7, 7);
/*     */         }
/*     */         
/* 132 */         return traceRet7;
/*     */       
/*     */       case 20:
/* 135 */         traceRet8 = new MQCFGR(message);
/* 136 */         if (Trace.isOn) {
/* 137 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet8, 8);
/*     */         }
/*     */         
/* 140 */         return traceRet8;
/*     */       
/*     */       case 13:
/* 143 */         traceRet9 = new MQCFIF(message);
/* 144 */         if (Trace.isOn) {
/* 145 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet9, 9);
/*     */         }
/*     */         
/* 148 */         return traceRet9;
/*     */       
/*     */       case 14:
/* 151 */         traceRet10 = new MQCFSF(message);
/* 152 */         if (Trace.isOn) {
/* 153 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet10, 10);
/*     */         }
/*     */         
/* 156 */         return traceRet10;
/*     */       
/*     */       case 15:
/* 159 */         traceRet11 = new MQCFBF(message);
/* 160 */         if (Trace.isOn) {
/* 161 */           Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", traceRet11, 11);
/*     */         }
/*     */         
/* 164 */         return traceRet11;
/*     */     } 
/*     */     
/* 167 */     MQDataException traceRet12 = new MQDataException(2, 3013, message);
/*     */     
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.throwing("com.ibm.mq.headers.pcf.PCFParameter", "nextParameter(DataInput)", (Throwable)traceRet12);
/*     */     }
/*     */     
/* 173 */     throw traceRet12;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getStringLength(String string, int characterSet) throws UnsupportedEncodingException {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry("com.ibm.mq.headers.pcf.PCFParameter", "getStringLength(String,int)", new Object[] { string, 
/* 186 */             Integer.valueOf(characterSet) });
/*     */     }
/* 188 */     if (string == null) {
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "getStringLength(String,int)", 
/* 191 */             Integer.valueOf(0), 1);
/*     */       }
/* 193 */       return 0;
/*     */     } 
/* 195 */     int traceRet1 = (CCSID.convert(string, characterSet)).length;
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "getStringLength(String,int)", 
/* 198 */           Integer.valueOf(traceRet1), 2);
/*     */     }
/* 200 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int writeString(DataOutput message, String string) throws IOException {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry("com.ibm.mq.headers.pcf.PCFParameter", "writeString(DataOutput,String)", new Object[] { message, string });
/*     */     }
/*     */     
/* 213 */     if (string == null) {
/* 214 */       if (Trace.isOn) {
/* 215 */         Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "writeString(DataOutput,String)", 
/* 216 */             Integer.valueOf(0), 1);
/*     */       }
/* 218 */       return 0;
/*     */     } 
/* 220 */     MessageWrapper wrappedMessage = MessageWrapper.wrap(message);
/* 221 */     int pos = wrappedMessage.getDataOffset();
/*     */     
/* 223 */     wrappedMessage.writeString(string);
/*     */     
/* 225 */     int traceRet1 = wrappedMessage.getDataOffset() - pos;
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit("com.ibm.mq.headers.pcf.PCFParameter", "writeString(DataOutput,String)", 
/* 228 */           Integer.valueOf(traceRet1), 2);
/*     */     }
/* 230 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected PCFParameter(HeaderType type) {
/* 234 */     super(type);
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFParameter", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/*     */     
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFParameter", "<init>(HeaderType)");
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
/*     */   public int getHeaderVersion() {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFParameter", "getHeaderVersion()", "getter", 
/* 274 */           Integer.valueOf(1));
/*     */     }
/* 276 */     return 1;
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
/*     */   public boolean equals(Object obj) {
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFParameter", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 292 */     boolean areEqual = false;
/* 293 */     if (obj != null && obj instanceof PCFParameter) {
/* 294 */       PCFParameter other = (PCFParameter)obj;
/*     */       
/* 296 */       int t1 = getType();
/* 297 */       int t2 = other.getType();
/* 298 */       boolean typesMatch = (t1 == t2);
/*     */       
/* 300 */       int p1 = getParameter();
/* 301 */       int p2 = other.getParameter();
/* 302 */       boolean parmsMatch = (p1 == p2);
/*     */       
/* 304 */       Object v1 = getValue();
/* 305 */       Object v2 = other.getValue();
/*     */       
/* 307 */       boolean valuesMatch = v1.equals(v2);
/*     */       
/* 309 */       areEqual = (typesMatch && parmsMatch && valuesMatch);
/*     */     } 
/*     */ 
/*     */     
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFParameter", "equals(Object)", 
/* 315 */           Boolean.valueOf(areEqual));
/*     */     }
/* 317 */     return areEqual;
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
/* 329 */   private static Pattern firstLastPattern = Pattern.compile(".*(_FIRST|_FIRST_USED|_LAST|_LAST_USED)$");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getParameterName() {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFParameter", "getParameterName()");
/*     */     }
/* 344 */     String parameterName = null;
/*     */     try {
/* 346 */       String potentialNames = PCFConstants.lookupParameter(getParameter());
/*     */       
/* 348 */       if (potentialNames != null) {
/* 349 */         StringBuffer result = new StringBuffer(potentialNames.length());
/* 350 */         StringTokenizer tok = new StringTokenizer(potentialNames, "/");
/* 351 */         while (tok.hasMoreTokens()) {
/* 352 */           String candidate = tok.nextToken();
/* 353 */           if (!firstLastPattern.matcher(candidate).matches()) {
/* 354 */             result.append(candidate);
/* 355 */             result.append("/");
/*     */           } 
/*     */         } 
/* 358 */         if (result.length() > 0) {
/* 359 */           result.setLength(result.length() - 1);
/* 360 */           parameterName = result.toString();
/*     */         } 
/*     */       } 
/*     */       
/* 364 */       String traceRet1 = parameterName;
/*     */       
/* 366 */       if (Trace.isOn) {
/* 367 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFParameter", "getParameterName()", traceRet1, 1);
/*     */       }
/*     */       
/* 370 */       return traceRet1;
/*     */     
/*     */     }
/* 373 */     catch (Throwable e) {
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFParameter", "getParameterName()", e);
/*     */       }
/* 377 */       if (Trace.isOn) {
/* 378 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFParameter", "getParameterName()", null, 2);
/*     */       }
/* 380 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String toString(MQHeader.Field field) {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFParameter", "toString(Field)", new Object[] { field });
/*     */     }
/*     */     
/* 390 */     String string = super.toString(field);
/*     */     
/* 392 */     if (field.getName().equals("Parameter")) {
/* 393 */       Integer value = (Integer)field.getValue();
/* 394 */       String constant = PCFConstants.lookupParameter(value.intValue());
/*     */       
/* 396 */       string = string.substring(0, string.indexOf('(')) + "(" + constant + ")";
/*     */     } 
/*     */     
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFParameter", "toString(Field)", string);
/*     */     }
/* 402 */     return string;
/*     */   }
/*     */   
/*     */   public abstract int getParameter();
/*     */   
/*     */   public abstract Object getValue();
/*     */   
/*     */   public abstract String getStringValue();
/*     */   
/*     */   public abstract int hashCode();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */