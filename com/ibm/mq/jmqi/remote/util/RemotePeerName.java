/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemotePeerName
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemotePeerName.java";
/*     */   private String channelName;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.util.RemotePeerName", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemotePeerName.java");
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
/*  53 */   private String originalName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   String CN = null;
/*  60 */   String T = null;
/*  61 */   String O = null;
/*  62 */   String L = null;
/*  63 */   String ST = null;
/*  64 */   String C = null;
/*  65 */   String SERIALNUMBER = null;
/*  66 */   String MAIL = null;
/*  67 */   String E = null;
/*  68 */   String UID = null;
/*  69 */   String STREET = null;
/*  70 */   String PC = null;
/*  71 */   String UNSTRUCTUREDNAME = null;
/*  72 */   String UNSTRUCTUREDADDRESS = null;
/*  73 */   String DNQ = null;
/*  74 */   Vector<String> DC = new Vector<>();
/*  75 */   Vector<String> OU = new Vector<>();
/*     */ 
/*     */   
/*     */   boolean matcher;
/*     */ 
/*     */   
/*     */   private static final int DNCLEAR = 1;
/*     */ 
/*     */   
/*     */   private static final int DNSYMBOL = 2;
/*     */ 
/*     */   
/*     */   private static final int DNVALUE = 3;
/*     */   
/*     */   private static final int DNQVALUE = 4;
/*     */   
/*     */   private static final int DNFINISHED = 5;
/*     */ 
/*     */   
/*     */   public RemotePeerName(JmqiEnvironment env, String channelName, String DN, boolean validate) throws JmqiException {
/*  95 */     super(env);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "<init>(JmqiEnvironment,RemoteConnection,String,boolean)", new Object[] { env, channelName, DN, 
/*  98 */             Boolean.valueOf(validate) });
/*     */     }
/* 100 */     this.channelName = channelName;
/* 101 */     this.originalName = DN;
/* 102 */     this.matcher = validate;
/* 103 */     parseDN(DN);
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "<init>(JmqiEnvironment,RemoteConnection,String,boolean)");
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
/*     */   public String getDN() {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "getDN()", "getter", this.originalName);
/*     */     }
/* 123 */     return this.originalName;
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
/*     */   private void parseDN(String DN) throws JmqiException {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", new Object[] { DN });
/*     */     }
/* 145 */     int state = 1;
/*     */     
/* 147 */     String symbol = "";
/* 148 */     String value = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (DN.equals("")) {
/* 157 */       state = 5;
/*     */     }
/*     */     try {
/* 160 */       for (int i = 0; i < DN.length(); i++) {
/* 161 */         char ch = DN.charAt(i);
/* 162 */         if (state == 1) {
/* 163 */           if (ch == '"' || ch == ',' || ch == ';' || ch == '=') {
/*     */             
/* 165 */             JmqiException traceRet1 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, Character.toString(ch) }, 2, 2399, null);
/*     */ 
/*     */             
/* 168 */             if (Trace.isOn) {
/* 169 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", (Throwable)traceRet1, 1);
/*     */             }
/* 171 */             throw traceRet1;
/* 172 */           }  if (ch != ' ' && ch != '\t') {
/* 173 */             symbol = symbol + ch;
/* 174 */             state = 2;
/*     */           } 
/* 176 */         } else if (state == 2) {
/* 177 */           if (ch == ' ' || ch == '"') {
/* 178 */             JmqiException traceRet2 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol }, 2, 2399, null);
/*     */             
/* 180 */             if (Trace.isOn) {
/* 181 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", (Throwable)traceRet2, 2);
/*     */             }
/* 183 */             throw traceRet2;
/* 184 */           }  if (ch == '=') {
/* 185 */             value = "";
/* 186 */             if (i + 1 >= DN.length()) {
/* 187 */               state = 3;
/*     */             }
/* 189 */             else if (DN.charAt(i + 1) == '"') {
/* 190 */               i++;
/* 191 */               state = 4;
/*     */             } else {
/* 193 */               state = 3;
/*     */             } 
/*     */           } else {
/*     */             
/* 197 */             symbol = symbol + ch;
/*     */           } 
/* 199 */         } else if (state == 3) {
/*     */ 
/*     */           
/* 202 */           if ((ch == ',' || ch == ';') && (i == 0 || DN.charAt(i - 1) != '\\')) {
/* 203 */             state = 1;
/*     */ 
/*     */             
/* 206 */             setValue(symbol, value.trim());
/* 207 */             symbol = "";
/*     */           } else {
/* 209 */             value = value + ch;
/*     */           } 
/* 211 */         } else if (state == 4) {
/* 212 */           if (ch == '"' && (i == 0 || DN.charAt(i - 1) != '\\')) {
/* 213 */             state = 5;
/*     */             
/* 215 */             setValue(symbol, value);
/* 216 */             symbol = "";
/*     */           } else {
/* 218 */             value = value + ch;
/*     */           } 
/* 220 */         } else if (state == 5) {
/* 221 */           if (ch == ',' || ch == ';') {
/* 222 */             state = 1;
/* 223 */           } else if (ch != ' ' && ch != '\t') {
/*     */             
/* 225 */             JmqiException traceRet3 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, Character.toString(ch) }, 2, 2399, null);
/*     */             
/* 227 */             if (Trace.isOn) {
/* 228 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", (Throwable)traceRet3, 3);
/*     */             }
/* 230 */             throw traceRet3;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 242 */       if (state == 3) {
/* 243 */         setValue(symbol, value.trim());
/*     */       }
/*     */ 
/*     */       
/* 247 */       if (state == 2 || state == 1) {
/* 248 */         JmqiException traceRet4 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol }, 2, 2399, null);
/*     */         
/* 250 */         if (Trace.isOn) {
/* 251 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", (Throwable)traceRet4, 4);
/*     */         }
/* 253 */         throw traceRet4;
/*     */       } 
/* 255 */     } catch (JmqiException e) {
/* 256 */       if (Trace.isOn) {
/* 257 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", (Throwable)e);
/*     */       }
/* 259 */       if (Trace.isOn) {
/* 260 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)", (Throwable)e, 5);
/*     */       }
/* 262 */       throw e;
/*     */     } finally {
/* 264 */       if (Trace.isOn) {
/* 265 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)");
/*     */       }
/*     */     } 
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "parseDN(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setValue(String rawsymbol, String value) throws JmqiException {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", new Object[] { rawsymbol, value });
/*     */     }
/* 282 */     String symbol = rawsymbol.toUpperCase();
/* 283 */     if (symbol.equals("CN")) {
/* 284 */       if (this.CN == null) {
/* 285 */         this.CN = value.toUpperCase();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 290 */         JmqiException traceRet1 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, "CN (x2)" }, 2, 2399, null);
/*     */ 
/*     */ 
/*     */         
/* 294 */         if (Trace.isOn) {
/* 295 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet1, 1);
/*     */         }
/* 297 */         throw traceRet1;
/*     */       } 
/* 299 */     } else if (symbol.equals("T")) {
/* 300 */       if (this.T == null) {
/* 301 */         this.T = value.toUpperCase();
/*     */       } else {
/* 303 */         JmqiException traceRet1 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, "T (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 306 */         if (Trace.isOn) {
/* 307 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet1, 2);
/*     */         }
/* 309 */         throw traceRet1;
/*     */       } 
/* 311 */     } else if (symbol.equals("O")) {
/* 312 */       if (this.O == null) {
/* 313 */         this.O = value.toUpperCase();
/*     */       } else {
/* 315 */         JmqiException traceRet3 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, "O (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 318 */         if (Trace.isOn) {
/* 319 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet3, 3);
/*     */         }
/* 321 */         throw traceRet3;
/*     */       } 
/* 323 */     } else if (symbol.equals("L")) {
/* 324 */       if (this.L == null) {
/* 325 */         this.L = value.toUpperCase();
/*     */       } else {
/* 327 */         JmqiException traceRet4 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, "L (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 330 */         if (Trace.isOn) {
/* 331 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet4, 4);
/*     */         }
/* 333 */         throw traceRet4;
/*     */       } 
/* 335 */     } else if (symbol.equals("ST") || symbol.equals("SP") || symbol.equals("S")) {
/* 336 */       if (this.ST == null) {
/* 337 */         this.ST = value.toUpperCase();
/*     */       } else {
/* 339 */         JmqiException traceRet5 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 342 */         if (Trace.isOn) {
/* 343 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet5, 5);
/*     */         }
/* 345 */         throw traceRet5;
/*     */       } 
/* 347 */     } else if (symbol.equals("C")) {
/* 348 */       if (this.C == null) {
/* 349 */         this.C = value.toUpperCase();
/*     */       } else {
/* 351 */         JmqiException traceRet6 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, "C (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 354 */         if (Trace.isOn) {
/* 355 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet6, 6);
/*     */         }
/* 357 */         throw traceRet6;
/*     */       } 
/* 359 */     } else if (symbol.equals("SERIALNUMBER")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 367 */       if (this.SERIALNUMBER == null) {
/* 368 */         this.SERIALNUMBER = value.toUpperCase();
/*     */       }
/*     */       else {
/*     */         
/* 372 */         if (this.matcher) {
/* 373 */           JmqiException traceRet8 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */           
/* 376 */           if (Trace.isOn) {
/* 377 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet8, 8);
/* 378 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 8);
/*     */           } 
/* 380 */           throw traceRet8;
/*     */         } 
/*     */ 
/*     */         
/* 384 */         if (Trace.isOn) {
/* 385 */           Trace.data("com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", "Additional SERIALNUMBER attribute found, with the value " + value + ". This is being ignored.");
/*     */         }
/*     */       }
/*     */     
/* 389 */     } else if (symbol.equals("MAIL")) {
/* 390 */       if (this.MAIL == null) {
/* 391 */         this.MAIL = value.toUpperCase();
/*     */       } else {
/* 393 */         JmqiException traceRet9 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 396 */         if (Trace.isOn) {
/* 397 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet9, 9);
/* 398 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 9);
/*     */         } 
/* 400 */         if (Trace.isOn) {
/* 401 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet9, 8);
/*     */         }
/* 403 */         throw traceRet9;
/*     */       } 
/* 405 */     } else if (symbol.equals("E")) {
/* 406 */       if (this.E == null) {
/* 407 */         this.E = value.toUpperCase();
/*     */       } else {
/* 409 */         JmqiException traceRet10 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 412 */         if (Trace.isOn) {
/* 413 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet10, 10);
/* 414 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 10);
/*     */         } 
/* 416 */         if (Trace.isOn) {
/* 417 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet10, 9);
/*     */         }
/* 419 */         throw traceRet10;
/*     */       } 
/* 421 */     } else if (symbol.equals("UID") || symbol.equals("USERID")) {
/* 422 */       if (this.UID == null) {
/* 423 */         this.UID = value.toUpperCase();
/*     */       } else {
/* 425 */         JmqiException traceRet11 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 428 */         if (Trace.isOn) {
/* 429 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet11, 11);
/* 430 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 11);
/*     */         } 
/* 432 */         if (Trace.isOn) {
/* 433 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet11, 10);
/*     */         }
/* 435 */         throw traceRet11;
/*     */       } 
/* 437 */     } else if (symbol.equals("STREET")) {
/* 438 */       if (this.STREET == null) {
/* 439 */         this.STREET = value.toUpperCase();
/*     */       } else {
/* 441 */         JmqiException traceRet12 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 444 */         if (Trace.isOn) {
/* 445 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet12, 12);
/* 446 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 12);
/*     */         } 
/* 448 */         if (Trace.isOn) {
/* 449 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet12, 11);
/*     */         }
/* 451 */         throw traceRet12;
/*     */       } 
/* 453 */     } else if (symbol.equals("PC") || symbol.equals("POSTALCODE")) {
/* 454 */       if (this.PC == null) {
/* 455 */         this.PC = value.toUpperCase();
/*     */       } else {
/* 457 */         JmqiException traceRet13 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 460 */         if (Trace.isOn) {
/* 461 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet13, 13);
/* 462 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 13);
/*     */         } 
/* 464 */         if (Trace.isOn) {
/* 465 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet13, 12);
/*     */         }
/* 467 */         throw traceRet13;
/*     */       } 
/* 469 */     } else if (symbol.equals("UNSTRUCTUREDNAME")) {
/* 470 */       if (this.UNSTRUCTUREDNAME == null) {
/* 471 */         this.UNSTRUCTUREDNAME = value.toUpperCase();
/*     */       } else {
/* 473 */         JmqiException traceRet14 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 476 */         if (Trace.isOn) {
/* 477 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet14, 14);
/* 478 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 14);
/*     */         } 
/* 480 */         if (Trace.isOn) {
/* 481 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet14, 13);
/*     */         }
/* 483 */         throw traceRet14;
/*     */       } 
/* 485 */     } else if (symbol.equals("UNSTRUCTUREDADDRESS")) {
/* 486 */       if (this.UNSTRUCTUREDADDRESS == null) {
/* 487 */         this.UNSTRUCTUREDADDRESS = value.toUpperCase();
/*     */       } else {
/* 489 */         JmqiException traceRet15 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 492 */         if (Trace.isOn) {
/* 493 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet15, 15);
/* 494 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 15);
/*     */         } 
/* 496 */         if (Trace.isOn) {
/* 497 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet15, 14);
/*     */         }
/* 499 */         throw traceRet15;
/*     */       } 
/* 501 */     } else if (symbol.equals("DNQ")) {
/* 502 */       if (this.DNQ == null) {
/* 503 */         this.DNQ = value.toUpperCase();
/*     */       } else {
/* 505 */         JmqiException traceRet16 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */         
/* 508 */         if (Trace.isOn) {
/* 509 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet16, 16);
/* 510 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", 16);
/*     */         } 
/* 512 */         if (Trace.isOn) {
/* 513 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet16, 15);
/*     */         }
/* 515 */         throw traceRet16;
/*     */       } 
/* 517 */     } else if (symbol.equals("OU")) {
/* 518 */       this.OU.add(value.toUpperCase());
/* 519 */     } else if (symbol.equals("DC")) {
/* 520 */       this.DC.add(value.toUpperCase());
/*     */     
/*     */     }
/* 523 */     else if (this.matcher) {
/* 524 */       JmqiException traceRet7 = new JmqiException(this.env, 9640, new String[] { null, null, this.channelName, null, symbol + " (x2)" }, 2, 2399, null);
/*     */ 
/*     */       
/* 527 */       if (Trace.isOn) {
/* 528 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)", (Throwable)traceRet7, 16);
/*     */       }
/* 530 */       throw traceRet7;
/*     */     } 
/*     */     
/* 533 */     if (Trace.isOn) {
/* 534 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "setValue(String,String)");
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
/*     */   public boolean isMatchingPeerName(RemotePeerName name) {
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "isMatchingPeerName(RemotePeerName)", new Object[] { name });
/*     */     }
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.data(this, "isMatchingPeerName(RemotePeerName)", "Comparing \"" + getDN() + "\" with \"" + name.getDN() + "\"", "");
/*     */     }
/* 554 */     boolean match = true;
/* 555 */     if (this.CN != null && !wequals(this.CN, name.CN)) {
/* 556 */       match = false;
/*     */     }
/* 558 */     if (this.T != null && !wequals(this.T, name.T)) {
/* 559 */       match = false;
/*     */     }
/* 561 */     if (this.O != null && !wequals(this.O, name.O)) {
/* 562 */       match = false;
/*     */     }
/* 564 */     if (this.L != null && !wequals(this.L, name.L)) {
/* 565 */       match = false;
/*     */     }
/* 567 */     if (this.ST != null && !wequals(this.ST, name.ST)) {
/* 568 */       match = false;
/*     */     }
/* 570 */     if (this.C != null && !wequals(this.C, name.C)) {
/* 571 */       match = false;
/*     */     }
/* 573 */     if (this.SERIALNUMBER != null && !wequals(this.SERIALNUMBER, name.SERIALNUMBER)) {
/* 574 */       match = false;
/*     */     }
/* 576 */     if (this.MAIL != null && !wequals(this.MAIL, name.MAIL)) {
/* 577 */       match = false;
/*     */     }
/* 579 */     if (this.E != null && !wequals(this.E, name.E)) {
/* 580 */       match = false;
/*     */     }
/* 582 */     if (this.UID != null && !wequals(this.UID, name.UID)) {
/* 583 */       match = false;
/*     */     }
/* 585 */     if (this.STREET != null && !wequals(this.STREET, name.STREET)) {
/* 586 */       match = false;
/*     */     }
/* 588 */     if (this.PC != null && !wequals(this.PC, name.PC)) {
/* 589 */       match = false;
/*     */     }
/* 591 */     if (this.UNSTRUCTUREDNAME != null && !wequals(this.UNSTRUCTUREDNAME, name.UNSTRUCTUREDNAME)) {
/* 592 */       match = false;
/*     */     }
/* 594 */     if (this.UNSTRUCTUREDADDRESS != null && !wequals(this.UNSTRUCTUREDADDRESS, name.UNSTRUCTUREDADDRESS)) {
/* 595 */       match = false;
/*     */     }
/* 597 */     if (this.DNQ != null && !wequals(this.DNQ, name.DNQ)) {
/* 598 */       match = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 604 */     if (this.OU.size() > name.OU.size()) {
/* 605 */       match = false;
/*     */     } else {
/* 607 */       for (int i = 0; i < this.OU.size(); i++) {
/* 608 */         if (!wequals(this.OU.elementAt(i), name.OU.elementAt(i))) {
/* 609 */           match = false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 614 */     if (this.DC.size() > name.DC.size()) {
/* 615 */       match = false;
/*     */     } else {
/* 617 */       for (int i = 0; i < this.DC.size(); i++) {
/* 618 */         if (!wequals(this.DC.elementAt(i), name.DC.elementAt(i))) {
/* 619 */           match = false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 624 */     if (Trace.isOn) {
/* 625 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "isMatchingPeerName(RemotePeerName)", Boolean.valueOf(match));
/*     */     }
/* 627 */     return match;
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
/*     */   public boolean wequals(String pattern, String real) {
/* 645 */     if (Trace.isOn) {
/* 646 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", new Object[] { pattern, real });
/*     */     }
/*     */     
/* 649 */     if (real == null) {
/*     */ 
/*     */       
/* 652 */       if (pattern != null && pattern.equals("*")) {
/*     */         
/* 654 */         if (Trace.isOn) {
/* 655 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(true), 1);
/*     */         }
/* 657 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 664 */       if (pattern != null) {
/*     */         
/* 666 */         if (Trace.isOn) {
/* 667 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(false), 2);
/*     */         }
/* 669 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 673 */       if (Trace.isOn) {
/* 674 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(true), 3);
/*     */       }
/* 676 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 681 */     String realsmashed = real.toUpperCase();
/*     */     
/* 683 */     if (pattern.equals("*")) {
/* 684 */       if (Trace.isOn) {
/* 685 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(true), 4);
/*     */       }
/* 687 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 691 */     if (pattern.charAt(0) == '*') {
/*     */       
/* 693 */       if (pattern.charAt(pattern.length() - 1) == '*') {
/* 694 */         boolean traceRet1 = (realsmashed.indexOf(realsmashed.substring(1, pattern.length() - 2)) != -1);
/*     */         
/* 696 */         if (Trace.isOn) {
/* 697 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(traceRet1), 5);
/*     */         }
/* 699 */         return traceRet1;
/*     */       } 
/*     */       
/* 702 */       boolean traceRet2 = realsmashed.endsWith(pattern.substring(1));
/*     */       
/* 704 */       if (Trace.isOn) {
/* 705 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(traceRet2), 6);
/*     */       }
/* 707 */       return traceRet2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 712 */     if (pattern.charAt(pattern.length() - 1) == '*' && pattern.charAt(pattern.length() - 2) != '\\') {
/* 713 */       boolean traceRet3 = realsmashed.startsWith(pattern.substring(0, pattern.length() - 1));
/*     */       
/* 715 */       if (Trace.isOn) {
/* 716 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(traceRet3), 7);
/*     */       }
/* 718 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 731 */     StringBuffer bPattern = new StringBuffer(pattern);
/*     */     
/* 733 */     for (int pos = 0; pos < bPattern.length(); pos++) {
/* 734 */       if (bPattern.charAt(pos) == '\\') {
/* 735 */         bPattern.deleteCharAt(pos);
/*     */       }
/*     */     } 
/*     */     
/* 739 */     String unescapedPattern = bPattern.toString();
/*     */     
/* 741 */     StringBuffer bReal = new StringBuffer(realsmashed);
/*     */     
/* 743 */     for (int i = 0; i < bReal.length(); i++) {
/* 744 */       if (bReal.charAt(i) == '\\') {
/* 745 */         bReal.deleteCharAt(i);
/*     */       }
/*     */     } 
/*     */     
/* 749 */     String unescapedRealsmashed = bReal.toString();
/*     */     
/* 751 */     if (Trace.isOn) {
/* 752 */       Trace.data(this, "wequals(String,String)", "Comparing ", unescapedPattern);
/* 753 */       Trace.data(this, "wequals(String,String)", "     with ", unescapedRealsmashed);
/*     */     } 
/*     */     
/* 756 */     boolean traceRet4 = unescapedPattern.equals(unescapedRealsmashed);
/*     */     
/* 758 */     if (Trace.isOn) {
/* 759 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemotePeerName", "wequals(String,String)", Boolean.valueOf(traceRet4), 8);
/*     */     }
/* 761 */     return traceRet4;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemotePeerName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */