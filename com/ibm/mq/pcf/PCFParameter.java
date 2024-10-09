/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.CCSID;
/*     */ import com.ibm.mq.headers.MQHeader;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.pcf.PCFConstants;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class PCFParameter
/*     */   extends PCFHeader
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFParameter.java";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.pcf.PCFParameter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFParameter.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   static final String[] pads = new String[] { "", " ", "  ", "   ", "" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PCFParameter nextParameter(MQMessage message) throws MQException, IOException {
/*     */     PCFParameter traceRet1, traceRet2, traceRet3, traceRet4, traceRet5, traceRet6, traceRet7, traceRet8, traceRet9, traceRet10, traceRet11;
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     message.writeBytes("");
/*  81 */     int pos = message.getDataOffset();
/*  82 */     int type = message.readInt();
/*  83 */     message.seek(pos);
/*     */     
/*  85 */     switch (type) {
/*     */       case 3:
/*  87 */         traceRet1 = new MQCFIN(message);
/*  88 */         if (Trace.isOn) {
/*  89 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet1, 1);
/*     */         }
/*  91 */         return traceRet1;
/*     */       
/*     */       case 5:
/*  94 */         traceRet2 = new MQCFIL(message);
/*  95 */         if (Trace.isOn) {
/*  96 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet2, 2);
/*     */         }
/*  98 */         return traceRet2;
/*     */       
/*     */       case 4:
/* 101 */         traceRet3 = new MQCFST(message);
/* 102 */         if (Trace.isOn) {
/* 103 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet3, 3);
/*     */         }
/* 105 */         return traceRet3;
/*     */       
/*     */       case 6:
/* 108 */         traceRet4 = new MQCFSL(message);
/* 109 */         if (Trace.isOn) {
/* 110 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet4, 4);
/*     */         }
/* 112 */         return traceRet4;
/*     */       
/*     */       case 9:
/* 115 */         traceRet5 = new MQCFBS(message);
/* 116 */         if (Trace.isOn) {
/* 117 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet5, 5);
/*     */         }
/* 119 */         return traceRet5;
/*     */       
/*     */       case 23:
/* 122 */         traceRet6 = new MQCFIN64(message);
/* 123 */         if (Trace.isOn) {
/* 124 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet6, 6);
/*     */         }
/* 126 */         return traceRet6;
/*     */       
/*     */       case 25:
/* 129 */         traceRet7 = new MQCFIL64(message);
/* 130 */         if (Trace.isOn) {
/* 131 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet7, 7);
/*     */         }
/* 133 */         return traceRet7;
/*     */       
/*     */       case 20:
/* 136 */         traceRet8 = new MQCFGR(message);
/* 137 */         if (Trace.isOn) {
/* 138 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet8, 8);
/*     */         }
/* 140 */         return traceRet8;
/*     */       
/*     */       case 13:
/* 143 */         traceRet9 = new MQCFIF(message);
/* 144 */         if (Trace.isOn) {
/* 145 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet9, 9);
/*     */         }
/* 147 */         return traceRet9;
/*     */       
/*     */       case 14:
/* 150 */         traceRet10 = new MQCFSF(message);
/* 151 */         if (Trace.isOn) {
/* 152 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet10, 10);
/*     */         }
/* 154 */         return traceRet10;
/*     */       
/*     */       case 15:
/* 157 */         traceRet11 = new MQCFBF(message);
/* 158 */         if (Trace.isOn) {
/* 159 */           Trace.exit("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", traceRet11, 11);
/*     */         }
/* 161 */         return traceRet11;
/*     */     } 
/*     */     
/* 164 */     MQException traceRet12 = new MQException(2, 3013, message);
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.throwing("com.ibm.mq.pcf.PCFParameter", "nextParameter(MQMessage)", (Throwable)traceRet12);
/*     */     }
/*     */     
/* 170 */     throw traceRet12;
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
/*     */   protected static int getStringLength(String string, int characterSet) throws UnsupportedEncodingException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry("com.ibm.mq.pcf.PCFParameter", "getStringLength(String,int)", new Object[] { string, 
/* 188 */             Integer.valueOf(characterSet) });
/*     */     }
/* 190 */     if (string == null) {
/* 191 */       if (Trace.isOn) {
/* 192 */         Trace.exit("com.ibm.mq.pcf.PCFParameter", "getStringLength(String,int)", 
/* 193 */             Integer.valueOf(0), 1);
/*     */       }
/* 195 */       return 0;
/*     */     } 
/* 197 */     int traceRet1 = (CCSID.convert(string, characterSet)).length;
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit("com.ibm.mq.pcf.PCFParameter", "getStringLength(String,int)", 
/* 200 */           Integer.valueOf(traceRet1), 2);
/*     */     }
/* 202 */     return traceRet1;
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
/*     */   protected static int writeString(MQMessage message, String string) throws IOException {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry("com.ibm.mq.pcf.PCFParameter", "writeString(MQMessage,String)", new Object[] { message, string });
/*     */     }
/*     */     
/* 220 */     if (string == null) {
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.exit("com.ibm.mq.pcf.PCFParameter", "writeString(MQMessage,String)", 
/* 223 */             Integer.valueOf(0), 1);
/*     */       }
/* 225 */       return 0;
/*     */     } 
/* 227 */     int pos = message.getDataOffset();
/*     */     
/* 229 */     message.writeString(string);
/*     */     
/* 231 */     int traceRet1 = message.getDataOffset() - pos;
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit("com.ibm.mq.pcf.PCFParameter", "writeString(MQMessage,String)", 
/* 234 */           Integer.valueOf(traceRet1), 2);
/*     */     }
/* 236 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PCFParameter(HeaderType type) {
/* 245 */     super(type);
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.pcf.PCFParameter", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.pcf.PCFParameter", "<init>(HeaderType)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.pcf.PCFParameter", "getHeaderVersion()", "getter", 
/* 299 */           Integer.valueOf(1));
/*     */     }
/* 301 */     return 1;
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
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry(this, "com.ibm.mq.pcf.PCFParameter", "equals(Object)", new Object[] { obj });
/*     */     }
/* 316 */     boolean areEqual = false;
/* 317 */     if (obj != null && obj instanceof PCFParameter) {
/* 318 */       PCFParameter other = (PCFParameter)obj;
/*     */       
/* 320 */       int t1 = getType();
/* 321 */       int t2 = other.getType();
/* 322 */       boolean typesMatch = (t1 == t2);
/*     */       
/* 324 */       int p1 = getParameter();
/* 325 */       int p2 = other.getParameter();
/* 326 */       boolean parmsMatch = (p1 == p2);
/*     */       
/* 328 */       Object v1 = getValue();
/* 329 */       Object v2 = other.getValue();
/*     */       
/* 331 */       boolean valuesMatch = v1.equals(v2);
/*     */       
/* 333 */       areEqual = (typesMatch && parmsMatch && valuesMatch);
/*     */     } 
/*     */ 
/*     */     
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.exit(this, "com.ibm.mq.pcf.PCFParameter", "equals(Object)", Boolean.valueOf(areEqual));
/*     */     }
/*     */     
/* 341 */     return areEqual;
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
/* 353 */   private static Pattern firstLastPattern = Pattern.compile(".*(_FIRST|_FIRST_USED|_LAST|_LAST_USED)$");
/*     */ 
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
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.entry(this, "com.ibm.mq.pcf.PCFParameter", "getParameterName()");
/*     */     }
/* 368 */     String parameterName = null;
/*     */     try {
/* 370 */       String potentialNames = PCFConstants.lookupParameter(getParameter());
/*     */       
/* 372 */       if (potentialNames != null) {
/* 373 */         StringBuffer result = new StringBuffer(potentialNames.length());
/* 374 */         StringTokenizer tok = new StringTokenizer(potentialNames, "/");
/* 375 */         while (tok.hasMoreTokens()) {
/* 376 */           String candidate = tok.nextToken();
/* 377 */           if (!firstLastPattern.matcher(candidate).matches()) {
/* 378 */             result.append(candidate);
/* 379 */             result.append("/");
/*     */           } 
/*     */         } 
/* 382 */         if (result.length() > 0) {
/* 383 */           result.setLength(result.length() - 1);
/* 384 */           parameterName = result.toString();
/*     */         } 
/*     */       } 
/*     */       
/* 388 */       String traceRet1 = parameterName;
/*     */       
/* 390 */       if (Trace.isOn) {
/* 391 */         Trace.exit(this, "com.ibm.mq.pcf.PCFParameter", "getParameterName()", traceRet1, 1);
/*     */       }
/* 393 */       return traceRet1;
/*     */     
/*     */     }
/* 396 */     catch (Throwable e) {
/* 397 */       if (Trace.isOn) {
/* 398 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFParameter", "getParameterName()", e);
/*     */       }
/* 400 */       if (Trace.isOn) {
/* 401 */         Trace.exit(this, "com.ibm.mq.pcf.PCFParameter", "getParameterName()", null, 2);
/*     */       }
/* 403 */       return null;
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
/*     */   protected String toString(MQHeader.Field field) {
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.entry(this, "com.ibm.mq.pcf.PCFParameter", "toString(Field)", new Object[] { field });
/*     */     }
/* 418 */     String string = super.toString(field);
/*     */     
/* 420 */     if (field.getName().equals("Parameter")) {
/* 421 */       Integer value = (Integer)field.getValue();
/* 422 */       String constant = PCFConstants.lookupParameter(value.intValue());
/*     */       
/* 424 */       string = string.substring(0, string.indexOf('(')) + "(" + constant + ")";
/*     */     } 
/*     */     
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.exit(this, "com.ibm.mq.pcf.PCFParameter", "toString(Field)", string);
/*     */     }
/* 430 */     return string;
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


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */