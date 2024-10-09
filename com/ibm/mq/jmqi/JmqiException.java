/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.JmqiResource;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.util.Date;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiException
/*     */   extends Exception
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiException.java";
/*     */   private static final long serialVersionUID = -3219090079420825378L;
/*     */   public static final int NO_AMQ_MESSAGE = -1;
/*     */   public static final int AMQ6090 = 6090;
/*     */   public static final int AMQ6271 = 6271;
/*     */   public static final int AMQ9181 = 9181;
/*     */   public static final int AMQ9184 = 9184;
/*     */   public static final int AMQ9204 = 9204;
/*     */   public static final int AMQ9206 = 9206;
/*     */   public static final int AMQ9205 = 9205;
/*     */   public static final int AMQ9208 = 9208;
/*     */   public static final int AMQ9213 = 9213;
/*     */   public static final int AMQ9214 = 9214;
/*     */   public static final int AMQ9231 = 9231;
/*     */   public static final int AMQ9248 = 9248;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jmqi.JmqiException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiException.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int AMQ9296 = 9296;
/*     */ 
/*     */   
/*     */   public static final int AMQ9487 = 9487;
/*     */ 
/*     */   
/*     */   public static final int AMQ9496 = 9496;
/*     */ 
/*     */   
/*     */   public static final int AMQ9503 = 9503;
/*     */ 
/*     */   
/*     */   public static final int AMQ9516 = 9516;
/*     */ 
/*     */   
/*     */   public static final int AMQ9530 = 9530;
/*     */ 
/*     */   
/*     */   public static final int AMQ9509 = 9509;
/*     */ 
/*     */   
/*     */   public static final int AMQ9520 = 9520;
/*     */ 
/*     */   
/*     */   public static final int AMQ9523 = 9523;
/*     */ 
/*     */   
/*     */   public static final int AMQ9524 = 9524;
/*     */ 
/*     */   
/*     */   public static final int AMQ9525 = 9525;
/*     */ 
/*     */   
/*     */   public static final int AMQ9528 = 9528;
/*     */ 
/*     */   
/*     */   public static final int AMQ9535 = 9535;
/*     */ 
/*     */   
/*     */   public static final int AMQ9536 = 9536;
/*     */ 
/*     */   
/*     */   public static final int AMQ9546 = 9546;
/*     */ 
/*     */   
/*     */   public static final int AMQ9547 = 9547;
/*     */ 
/*     */   
/*     */   public static final int AMQ9555 = 9555;
/*     */ 
/*     */   
/*     */   public static final int AMQ9633 = 9633;
/*     */ 
/*     */   
/*     */   public static final int AMQ9635 = 9635;
/*     */ 
/*     */   
/*     */   public static final int AMQ9640 = 9640;
/*     */ 
/*     */   
/*     */   public static final int AMQ9694 = 9694;
/*     */ 
/*     */   
/*     */   public static final int AMQ9913 = 9913;
/*     */ 
/*     */   
/*     */   public static final int AMQ9915 = 9915;
/*     */ 
/*     */   
/*     */   public static final int AMQ9641 = 9641;
/*     */ 
/*     */   
/*     */   public static final int AMQ9643 = 9643;
/*     */ 
/*     */   
/*     */   public static final int AMQ9566 = 9566;
/*     */ 
/*     */   
/*     */   public static final int AMQ9636 = 9636;
/*     */ 
/*     */   
/*     */   public static final int AMQ9666 = 9666;
/*     */ 
/*     */   
/*     */   public static final int AMQ9714 = 9714;
/*     */ 
/*     */   
/*     */   public static final int AMQ9771 = 9771;
/*     */ 
/*     */   
/*     */   public static final int AMQ9999 = 9999;
/*     */ 
/*     */   
/*     */   public static final int AMQ6047 = 6047;
/*     */ 
/*     */   
/*     */   public static final int AMQ6051 = 6051;
/*     */ 
/*     */   
/*     */   public static final int AMQ6052 = 6052;
/*     */ 
/*     */   
/*     */   public static final int AMQ8562 = 8562;
/*     */ 
/*     */   
/*     */   public static final int AMQ8568 = 8568;
/*     */ 
/*     */   
/*     */   public static final int AMQ8598 = 8598;
/*     */ 
/*     */   
/*     */   public static final int AMQ9558 = 9558;
/*     */ 
/*     */   
/*     */   public static final int AMQ9567 = 9567;
/*     */ 
/*     */   
/*     */   public static final int AMQ9568 = 9568;
/*     */ 
/*     */   
/*     */   public static final int AMQ9695 = 9695;
/*     */ 
/*     */   
/*     */   public static final int AMQ9696 = 9696;
/*     */ 
/*     */   
/*     */   private static final String AMQ6090_ENG_SEVERITY = "0";
/*     */ 
/*     */   
/*     */   private static final String AMQ6090_ENG_MSG = "&MQ_long was unable to display an error message &6.";
/*     */ 
/*     */   
/*     */   private static final String AMQ6090_ENG_XPL = "&MQ_short has attempted to display the message associated with return code hexadecimal '&6'. The return code indicates that there is no message text associated with the message. Associated with the request are inserts &1 &2 &3 &4 &5.";
/*     */ 
/*     */   
/*     */   private static final String AMQ6090_ENG_URESP = "Use the standard facilities supplied with your system to record the problem identifier, and to save the generated output files. Contact your IBM support center. Do not discard these files until the problem has been resolved.";
/*     */ 
/*     */   
/*     */   private static final String MSG_PKG = "com.ibm.mq.jmqi.internal.";
/*     */   
/* 195 */   private int compCode = 0;
/*     */   
/* 197 */   private int reason = 0;
/*     */ 
/*     */   
/*     */   private int amqXXXX;
/*     */ 
/*     */   
/*     */   private String[] inserts;
/*     */   
/*     */   private boolean msgBuilt = false;
/*     */   
/* 207 */   private String msgSummary = null;
/*     */ 
/*     */   
/* 210 */   private String explanation = null;
/*     */ 
/*     */   
/* 213 */   private String userResponse = null;
/*     */ 
/*     */   
/* 216 */   private int severity = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String explanationText;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String actionText;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean initialised = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JmqiEnvironment env;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiException(JmqiEnvironment env, int amqXXXX, String[] inserts, int CompCode, int Reason, Throwable nestedException) {
/* 243 */     super(nestedException);
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "<init>(JmqiEnvironment,int,String [ ],int,int,Throwable)", new Object[] { env, 
/*     */             
/* 247 */             Integer.valueOf(amqXXXX), inserts, Integer.valueOf(CompCode), Integer.valueOf(Reason), nestedException });
/*     */     }
/*     */ 
/*     */     
/* 251 */     this.env = env;
/*     */ 
/*     */     
/* 254 */     this.amqXXXX = amqXXXX;
/*     */ 
/*     */     
/* 257 */     if (inserts != null) {
/* 258 */       this.inserts = new String[inserts.length];
/* 259 */       System.arraycopy(inserts, 0, this.inserts, 0, inserts.length);
/*     */     } 
/*     */ 
/*     */     
/* 263 */     initializeClass(env);
/*     */     
/* 265 */     this.compCode = CompCode;
/* 266 */     this.reason = Reason;
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "<init>(JmqiEnvironment,int,String [ ],int,int,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized void initializeClass(JmqiEnvironment env) {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.entry("com.ibm.mq.jmqi.JmqiException", "initializeClass(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 279 */     if (!initialised) {
/*     */       
/* 281 */       explanationText = JmqiResource.getStringIfAvailable(env, "MQJE115", new String[0]);
/* 282 */       if (explanationText == null) {
/* 283 */         explanationText = "EXPLANATION:";
/*     */       }
/*     */       
/* 286 */       actionText = JmqiResource.getStringIfAvailable(env, "MQJE116", new String[0]);
/* 287 */       if (actionText == null) {
/* 288 */         actionText = "ACTION:";
/*     */       }
/* 290 */       initialised = true;
/*     */     } 
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.exit("com.ibm.mq.jmqi.JmqiException", "initializeClass(JmqiEnvironment)");
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
/*     */   private void addInserts(CharSequence src, StringBuffer target) {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "addInserts(CharSequence,StringBuffer)", new Object[] { src, target });
/*     */     }
/*     */ 
/*     */     
/* 310 */     char lastChar1 = Character.MIN_VALUE;
/* 311 */     char lastChar2 = Character.MIN_VALUE;
/* 312 */     for (int i = 0; i < src.length(); i++) {
/* 313 */       char c = src.charAt(i);
/*     */ 
/*     */       
/* 316 */       boolean isEscape = false;
/*     */ 
/*     */       
/* 319 */       if (lastChar1 == '&')
/*     */       {
/* 321 */         if (c <= '6' && c >= '1') {
/*     */           
/* 323 */           isEscape = true;
/* 324 */           int idx = c - 49;
/* 325 */           if (idx == 5) {
/* 326 */             target.append(String.valueOf(this.amqXXXX));
/*     */           }
/* 328 */           else if (this.inserts != null && idx < this.inserts.length) {
/* 329 */             String ins = this.inserts[idx];
/* 330 */             if (ins == null) {
/* 331 */               ins = "?";
/*     */             }
/* 333 */             if (lastChar2 != '\'' && lastChar2 != '"') {
/* 334 */               target.append('\'');
/* 335 */               target.append(ins.trim());
/* 336 */               target.append('\'');
/*     */             } else {
/*     */               
/* 339 */               target.append(ins.trim());
/*     */             }
/*     */           
/*     */           }
/*     */         
/* 344 */         } else if (c == 'P' || c == 'p') {
/* 345 */           target.append(JmqiTools.getNewline());
/* 346 */           isEscape = true;
/*     */         
/*     */         }
/* 349 */         else if (src.subSequence(i, Math.min(i + 8, src.length())).equals("MQ_short")) {
/* 350 */           isEscape = true;
/* 351 */           target.append("MQ");
/* 352 */           i += 7;
/*     */         
/*     */         }
/* 355 */         else if (src.subSequence(i, Math.min(i + 7, src.length())).equals("MQ_long")) {
/* 356 */           isEscape = true;
/* 357 */           target.append("IBM MQ");
/* 358 */           i += 6;
/*     */         
/*     */         }
/* 361 */         else if (src.subSequence(i, Math.min(i + 10, src.length())).equals("MQSI_long")) {
/* 362 */           isEscape = true;
/* 363 */           target.append("WebSphere MQ Integrator");
/* 364 */           i += 9;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 369 */       if (isEscape) {
/* 370 */         lastChar2 = lastChar1;
/* 371 */         lastChar1 = Character.MIN_VALUE;
/*     */       }
/*     */       else {
/*     */         
/* 375 */         if (lastChar1 != '\000') {
/* 376 */           target.append(lastChar1);
/*     */         }
/*     */         
/* 379 */         lastChar2 = lastChar1;
/* 380 */         lastChar1 = c;
/*     */       } 
/*     */     } 
/*     */     
/* 384 */     if (lastChar1 != '\000') {
/* 385 */       target.append(lastChar1);
/*     */     }
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "addInserts(CharSequence,StringBuffer)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildMessage() {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "buildMessage()");
/*     */     }
/*     */     
/* 401 */     if (!this.msgBuilt) {
/*     */ 
/*     */       
/* 404 */       DecimalFormat fourDigit = new DecimalFormat();
/* 405 */       FieldPosition fp = new FieldPosition(0);
/* 406 */       fourDigit.setMinimumIntegerDigits(4);
/* 407 */       fourDigit.setGroupingUsed(false);
/*     */ 
/*     */       
/* 410 */       StringBuffer msgNameBuff = new StringBuffer(7);
/* 411 */       msgNameBuff.append("amq");
/* 412 */       fourDigit.format(this.amqXXXX, msgNameBuff, fp);
/* 413 */       String msgName = msgNameBuff.toString();
/*     */       
/* 415 */       ResourceBundle bundle = null;
/*     */ 
/*     */ 
/*     */       
/* 419 */       if (this.amqXXXX >= 0) {
/*     */         try {
/* 421 */           bundle = ResourceBundle.getBundle("com.ibm.mq.jmqi.internal." + msgName);
/*     */         }
/* 423 */         catch (MissingResourceException e) {
/* 424 */           if (Trace.isOn) {
/* 425 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiException", "buildMessage()", e, 1);
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 434 */       if (bundle == null) {
/*     */         try {
/* 436 */           msgName = "amq6090";
/* 437 */           bundle = ResourceBundle.getBundle("com.ibm.mq.jmqi.internal." + msgName);
/*     */         }
/* 439 */         catch (MissingResourceException e) {
/* 440 */           if (Trace.isOn) {
/* 441 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiException", "buildMessage()", e, 2);
/*     */           }
/*     */           
/* 444 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 449 */       String rawSeverity = null;
/* 450 */       String rawSummary = null;
/* 451 */       String rawExplaination = null;
/* 452 */       String rawUserResp = null;
/* 453 */       if (bundle != null) {
/*     */         try {
/* 455 */           rawSeverity = bundle.getString("SEVERITY");
/* 456 */           rawSummary = bundle.getString("MSG");
/* 457 */           rawExplaination = bundle.getString("XPL");
/* 458 */           rawUserResp = bundle.getString("URESP");
/*     */         }
/* 460 */         catch (MissingResourceException e) {
/* 461 */           if (Trace.isOn) {
/* 462 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiException", "buildMessage()", e, 3);
/*     */           }
/*     */           
/* 465 */           bundle = null;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 470 */       if (bundle == null) {
/* 471 */         msgName = "amq6090";
/* 472 */         rawSeverity = "0";
/* 473 */         rawSummary = "&MQ_long was unable to display an error message &6.";
/* 474 */         rawExplaination = "&MQ_short has attempted to display the message associated with return code hexadecimal '&6'. The return code indicates that there is no message text associated with the message. Associated with the request are inserts &1 &2 &3 &4 &5.";
/* 475 */         rawUserResp = "Use the standard facilities supplied with your system to record the problem identifier, and to save the generated output files. Contact your IBM support center. Do not discard these files until the problem has been resolved.";
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 480 */         this.severity = Integer.parseInt(rawSeverity);
/*     */       }
/* 482 */       catch (NumberFormatException e) {
/* 483 */         if (Trace.isOn) {
/* 484 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiException", "buildMessage()", e, 4);
/*     */         }
/*     */         
/* 487 */         this.severity = 0;
/*     */       } 
/*     */ 
/*     */       
/* 491 */       StringBuffer summBuffer = new StringBuffer();
/* 492 */       summBuffer.append(msgName.toUpperCase());
/* 493 */       summBuffer.append(": ");
/* 494 */       addInserts(rawSummary, summBuffer);
/* 495 */       this.msgSummary = summBuffer.toString();
/*     */ 
/*     */       
/* 498 */       summBuffer = new StringBuffer();
/* 499 */       addInserts(rawExplaination, summBuffer);
/* 500 */       this.explanation = summBuffer.toString();
/*     */ 
/*     */       
/* 503 */       summBuffer = new StringBuffer();
/* 504 */       addInserts(rawUserResp, summBuffer);
/* 505 */       this.userResponse = summBuffer.toString();
/*     */ 
/*     */       
/* 508 */       this.msgBuilt = true;
/*     */     } 
/* 510 */     if (Trace.isOn) {
/* 511 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "buildMessage()");
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
/*     */   public String getMessage() {
/* 524 */     String StringResponse = getMessage(true);
/* 525 */     if (Trace.isOn) {
/* 526 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiException", "getMessage()", "getter", StringResponse);
/*     */     }
/* 528 */     return StringResponse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage(boolean displayInserts) {
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "getMessage(boolean)", new Object[] {
/* 540 */             Boolean.valueOf(displayInserts)
/*     */           });
/*     */     }
/*     */     
/* 544 */     StringBuffer buff = new StringBuffer();
/* 545 */     buff.append("CC=");
/* 546 */     buff.append(this.compCode);
/* 547 */     buff.append(";RC=");
/* 548 */     buff.append(this.reason);
/*     */ 
/*     */     
/* 551 */     if (this.amqXXXX > 0) {
/* 552 */       buildMessage();
/* 553 */       buff.append(";");
/* 554 */       buff.append(this.msgSummary);
/*     */ 
/*     */       
/* 557 */       if (displayInserts)
/*     */       {
/* 559 */         if (this.inserts != null && this.inserts.length > 0) {
/* 560 */           buff.append(" [");
/* 561 */           boolean first = true;
/* 562 */           for (int i = 0; i < this.inserts.length; i++) {
/* 563 */             if (this.inserts[i] != null) {
/* 564 */               if (!first) {
/* 565 */                 buff.append(',');
/*     */               }
/* 567 */               buff.append(i + 1);
/* 568 */               buff.append("=");
/* 569 */               buff.append(this.inserts[i]);
/* 570 */               first = false;
/*     */             } 
/*     */           } 
/* 573 */           buff.append("]");
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 578 */     String traceRet1 = buff.toString();
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getMessage(boolean)", traceRet1);
/*     */     }
/* 582 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendWrap(StringBuffer buff, String text) {
/* 593 */     if (Trace.isOn) {
/* 594 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "appendWrap(StringBuffer,String)", new Object[] { buff, text });
/*     */     }
/*     */ 
/*     */     
/* 598 */     int lineLen = 0;
/* 599 */     String nl = JmqiTools.getNewline();
/* 600 */     StringTokenizer stok = new StringTokenizer(text, " \t");
/* 601 */     while (stok.hasMoreTokens()) {
/* 602 */       String word = stok.nextToken();
/* 603 */       if (lineLen == 0 || 80 - word.length() - lineLen - 1 > 0) {
/* 604 */         if (lineLen != 0) {
/* 605 */           buff.append(" ");
/* 606 */           lineLen++;
/*     */         } 
/* 608 */         buff.append(word);
/* 609 */         lineLen += word.length();
/*     */       } else {
/*     */         
/* 612 */         buff.append(nl);
/* 613 */         buff.append(word);
/* 614 */         lineLen = word.length();
/*     */       } 
/*     */ 
/*     */       
/* 618 */       int nlPos = word.indexOf(nl);
/* 619 */       if (nlPos >= 0) {
/* 620 */         lineLen = word.length() - nlPos - nl.length();
/*     */       }
/*     */     } 
/* 623 */     buff.append(JmqiTools.getNewline());
/* 624 */     if (Trace.isOn) {
/* 625 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "appendWrap(StringBuffer,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWmqMsgSummary() {
/* 636 */     if (Trace.isOn) {
/* 637 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgSummary()");
/*     */     }
/*     */ 
/*     */     
/* 641 */     if (this.amqXXXX <= 0) {
/* 642 */       if (Trace.isOn) {
/* 643 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgSummary()", null, 1);
/*     */       }
/* 645 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 649 */     buildMessage();
/*     */     
/* 651 */     if (Trace.isOn) {
/* 652 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgSummary()", this.msgSummary, 2);
/*     */     }
/* 654 */     return this.msgSummary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWmqMsgExplanation() {
/* 663 */     if (Trace.isOn) {
/* 664 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgExplanation()");
/*     */     }
/*     */ 
/*     */     
/* 668 */     if (this.amqXXXX <= 0) {
/* 669 */       if (Trace.isOn) {
/* 670 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgExplanation()", null, 1);
/*     */       }
/* 672 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 676 */     buildMessage();
/*     */     
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgExplanation()", this.explanation, 2);
/*     */     }
/* 681 */     return this.explanation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWmqMsgUserResponse() {
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgUserResponse()");
/*     */     }
/*     */ 
/*     */     
/* 695 */     if (this.amqXXXX <= 0) {
/* 696 */       if (Trace.isOn) {
/* 697 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgUserResponse()", null, 1);
/*     */       }
/* 699 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 703 */     buildMessage();
/*     */     
/* 705 */     if (Trace.isOn) {
/* 706 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgUserResponse()", this.userResponse, 2);
/*     */     }
/*     */     
/* 709 */     return this.userResponse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWmqMsgSeverity() {
/* 718 */     if (Trace.isOn) {
/* 719 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgSeverity()");
/*     */     }
/*     */ 
/*     */     
/* 723 */     if (this.amqXXXX <= 0) {
/* 724 */       int traceRet1 = -1;
/* 725 */       if (Trace.isOn) {
/* 726 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgSeverity()", 
/* 727 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 729 */       return traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 733 */     buildMessage();
/*     */     
/* 735 */     if (Trace.isOn) {
/* 736 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqMsgSeverity()", 
/* 737 */           Integer.valueOf(this.severity), 2);
/*     */     }
/* 739 */     return this.severity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWmqLogMessage() {
/* 748 */     if (Trace.isOn) {
/* 749 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "getWmqLogMessage()");
/*     */     }
/*     */ 
/*     */     
/* 753 */     if (this.amqXXXX <= 0) {
/* 754 */       if (Trace.isOn) {
/* 755 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqLogMessage()", null, 1);
/*     */       }
/* 757 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 761 */     buildMessage();
/*     */ 
/*     */     
/* 764 */     StringBuffer buff = new StringBuffer();
/*     */ 
/*     */     
/* 767 */     Date now = new Date();
/* 768 */     DateFormat df = DateFormat.getDateInstance(3);
/* 769 */     DateFormat tf = DateFormat.getTimeInstance(2);
/* 770 */     buff.append(df.format(now));
/* 771 */     buff.append(" ");
/* 772 */     buff.append(tf.format(now));
/* 773 */     buff.append(" - User(");
/* 774 */     buff.append(JmqiTools.getUsername());
/* 775 */     buff.append(") Program(java)");
/* 776 */     buff.append(JmqiTools.getNewline());
/*     */ 
/*     */     
/* 779 */     appendWrap(buff, this.msgSummary);
/* 780 */     buff.append(JmqiTools.getNewline());
/* 781 */     buff.append(explanationText);
/* 782 */     buff.append(JmqiTools.getNewline());
/* 783 */     appendWrap(buff, this.explanation);
/* 784 */     buff.append(actionText);
/* 785 */     buff.append(JmqiTools.getNewline());
/* 786 */     appendWrap(buff, this.userResponse);
/*     */ 
/*     */ 
/*     */     
/* 790 */     StringBuffer separator = new StringBuffer();
/* 791 */     separator.append("---- ");
/* 792 */     StackTraceElement[] stack = getStackTrace();
/* 793 */     if (stack.length > 0) {
/* 794 */       String className = stack[0].getClassName();
/* 795 */       int lastDot = className.lastIndexOf('.');
/* 796 */       if (lastDot > 0) {
/* 797 */         className = className.substring(lastDot + 1);
/*     */       }
/* 799 */       separator.append(className);
/* 800 */       separator.append(".java");
/* 801 */       int lineNo = stack[0].getLineNumber();
/* 802 */       if (lineNo > 0) {
/* 803 */         separator.append(" : ");
/* 804 */         separator.append(stack[0].getLineNumber());
/*     */       } 
/*     */     } 
/*     */     
/* 808 */     separator.append(" ");
/* 809 */     int padChars = 80 - separator.length();
/* 810 */     for (int i = 0; i < padChars; i++) {
/* 811 */       separator.append("-");
/*     */     }
/* 813 */     buff.append(separator);
/* 814 */     buff.append(JmqiTools.getNewline());
/*     */     
/* 816 */     String traceRet1 = buff.toString();
/* 817 */     if (Trace.isOn) {
/* 818 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "getWmqLogMessage()", traceRet1, 2);
/*     */     }
/* 820 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode() {
/* 829 */     if (Trace.isOn) {
/* 830 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiException", "getCompCode()", "getter", 
/* 831 */           Integer.valueOf(this.compCode));
/*     */     }
/* 833 */     return this.compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 842 */     if (Trace.isOn) {
/* 843 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiException", "getReason()", "getter", 
/* 844 */           Integer.valueOf(this.reason));
/*     */     }
/* 846 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String[] getInserts() {
/* 853 */     if (Trace.isOn) {
/* 854 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiException", "getInserts()", "getter", this.inserts);
/*     */     }
/* 856 */     return this.inserts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getAMQXXXX() {
/* 863 */     if (Trace.isOn) {
/* 864 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiException", "getAMQXXXX()", "getter", 
/* 865 */           Integer.valueOf(this.amqXXXX));
/*     */     }
/* 867 */     return this.amqXXXX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 878 */     if (Trace.isOn) {
/* 879 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiException", "writeReplace()");
/*     */     }
/* 881 */     Object traceRet1 = new JmqiExceptionFactory(this.env, this);
/* 882 */     if (Trace.isOn) {
/* 883 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiException", "writeReplace()", traceRet1);
/*     */     }
/* 885 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */