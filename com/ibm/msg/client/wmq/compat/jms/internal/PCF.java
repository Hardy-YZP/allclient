/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Vector;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ class PCF {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/PCF.java";
/*     */   private static final int MQCFH_STRUC_LENGTH = 36;
/*     */   private static final int MQCFH_VERSION_1 = 1;
/*     */   private static final int MQCFC_LAST = 1;
/*     */   static final int MQCFT_COMMAND = 1;
/*     */   static final int MQCFT_RESPONSE = 2;
/*     */   static final int MQCFT_INTEGER = 3;
/*     */   static final int MQCFT_STRING = 4;
/*     */   static final int MQCFT_INTEGER_LIST = 5;
/*     */   static final int MQCFT_STRING_LIST = 6;
/*     */   static final int MQCFT_EVENT = 7;
/*     */   static final int MQCACF_TOPIC = 3031;
/*     */   static final int MQCACF_PUBLISH_TIMESTAMP = 3034;
/*     */   static final int MQCACF_REG_TIME = 3038;
/*     */   static final int MQCACF_REG_USER_ID = 3039;
/*     */   static final int MQCACF_REG_Q_MGR_NAME = 3042;
/*     */   static final int MQCACF_REG_Q_NAME = 3043;
/*     */   static final int MQCACF_REG_CORREL_ID = 3044;
/*     */   static final int MQCMD_CLEAR_Q = 9;
/*     */   static final int MQCMD_INQUIRE_Q_NAMES = 18;
/*     */   static final int MQCA_Q_NAME = 2016;
/*     */   static final int MQIA_Q_TYPE = 20;
/*     */   static final int MQCFIL_STRUC_LENGTH_FIXED = 16;
/*     */   static final int MQCFIN_STRUC_LENGTH = 16;
/*     */   static final int MQCFSL_STRUC_LENGTH_FIXED = 24;
/*     */   static final int MQCFST_STRUC_LENGTH_FIXED = 20;
/*     */   int type;
/*     */   int strucLength;
/*     */   int version;
/*     */   int command;
/*     */   int msgSeqNumber;
/*     */   int control;
/*     */   int compCode;
/*     */   int reason;
/*     */   int parameterCount;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.PCF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/PCF.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   int[] paramTypes = null;
/* 109 */   Object[] paramValues = null;
/*     */ 
/*     */ 
/*     */   
/* 113 */   private Vector paramOut = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PCF(int command) {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(int)", new Object[] {
/* 130 */             Integer.valueOf(command)
/*     */           });
/*     */     }
/* 133 */     this.type = 1;
/* 134 */     this.strucLength = 36;
/* 135 */     this.version = 1;
/* 136 */     this.command = command;
/* 137 */     this.msgSeqNumber = 1;
/* 138 */     this.control = 1;
/* 139 */     this.compCode = 0;
/* 140 */     this.reason = 0;
/* 141 */     this.parameterCount = 0;
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(int)");
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
/*     */   PCF(MQMessage message) throws JMSException {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 168 */     if (message == null) {
/* 169 */       JMSException je = ConfigEnvironment.newException("MQJMS1074");
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 174 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 179 */       this.type = message.readInt();
/* 180 */       this.strucLength = message.readInt();
/* 181 */       this.version = message.readInt();
/* 182 */       this.command = message.readInt();
/* 183 */       this.msgSeqNumber = message.readInt();
/* 184 */       this.control = message.readInt();
/* 185 */       this.compCode = message.readInt();
/* 186 */       this.reason = message.readInt();
/* 187 */       this.parameterCount = message.readInt();
/*     */ 
/*     */       
/* 190 */       if (this.version != 1) {
/* 191 */         Exception traceRet1 = new Exception("Unknown version: " + this.version);
/* 192 */         if (Trace.isOn) {
/* 193 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)", traceRet1, 2);
/*     */         }
/*     */         
/* 196 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 200 */       this.paramTypes = new int[this.parameterCount];
/* 201 */       this.paramValues = new Object[this.parameterCount];
/*     */ 
/*     */       
/* 204 */       for (int p = 0; p < this.parameterCount; p++) {
/* 205 */         int paramType = message.readInt();
/* 206 */         this.paramTypes[p] = paramType;
/*     */         
/* 208 */         if (paramType == 4) {
/* 209 */           this.paramValues[p] = new MQCFST(message);
/* 210 */         } else if (paramType == 3) {
/* 211 */           this.paramValues[p] = new MQCFIN(message);
/* 212 */         } else if (paramType == 6) {
/* 213 */           this.paramValues[p] = new MQCFSL(message);
/*     */         }
/*     */         else {
/*     */           
/* 217 */           Exception traceRet2 = new Exception("Unknown paramType: " + paramType);
/* 218 */           if (Trace.isOn) {
/* 219 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)", traceRet2, 3);
/*     */           }
/*     */           
/* 222 */           throw traceRet2;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 227 */       if (Trace.isOn) {
/* 228 */         Trace.traceData(this, "The following fields have been read:\n" + toString(), null);
/*     */       
/*     */       }
/*     */     }
/* 232 */     catch (Exception e) {
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)", e);
/*     */       }
/*     */ 
/*     */       
/* 238 */       JMSException je = ConfigEnvironment.newException("MQJMS1086");
/* 239 */       je.setLinkedException(e);
/*     */       
/* 241 */       if (Trace.isOn) {
/* 242 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)", (Throwable)je, 4);
/*     */       }
/*     */       
/* 245 */       throw je;
/*     */     } 
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "<init>(MQMessage)");
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
/*     */   void write(MQMessage message) throws JMSException {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 271 */       message.messageType = 1;
/* 272 */       message.expiry = 1000;
/* 273 */       message.format = "MQADMIN ";
/* 274 */       message.feedback = 0;
/*     */ 
/*     */       
/* 277 */       message.writeInt(this.type);
/* 278 */       message.writeInt(this.strucLength);
/* 279 */       message.writeInt(this.version);
/* 280 */       message.writeInt(this.command);
/* 281 */       message.writeInt(this.msgSeqNumber);
/* 282 */       message.writeInt(this.control);
/* 283 */       message.writeInt(this.compCode);
/* 284 */       message.writeInt(this.reason);
/* 285 */       message.writeInt(this.parameterCount);
/*     */ 
/*     */       
/* 288 */       for (int p = 0; p < this.parameterCount; p++) {
/* 289 */         Object param = this.paramOut.elementAt(p);
/*     */         
/* 291 */         if (param instanceof MQCFST) {
/* 292 */           ((MQCFST)param).writeTo(message);
/* 293 */         } else if (param instanceof MQCFIN) {
/* 294 */           ((MQCFIN)param).writeTo(message);
/*     */         } else {
/*     */           
/* 297 */           Exception traceRet1 = new Exception("Unable to write unknown parameter: " + p);
/* 298 */           if (Trace.isOn) {
/* 299 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", traceRet1, 1);
/*     */           }
/*     */           
/* 302 */           throw traceRet1;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 307 */     } catch (Exception e) {
/* 308 */       if (Trace.isOn) {
/* 309 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", e);
/*     */       }
/*     */ 
/*     */       
/* 313 */       JMSException je = ConfigEnvironment.newException("MQJMS1085");
/* 314 */       je.setLinkedException(e);
/* 315 */       if (Trace.isOn) {
/* 316 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 319 */       throw je;
/*     */     } 
/*     */     
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
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
/*     */   public String toString() {
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "toString()");
/*     */     }
/* 340 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 342 */     buf.append("Type='" + this.type + "'\n");
/* 343 */     buf.append("StrucLength='" + this.strucLength + "'\n");
/* 344 */     buf.append("Version='" + this.version + "'\n");
/* 345 */     buf.append("Command='" + this.command + "'\n");
/* 346 */     buf.append("MsgSeqNumber='" + this.msgSeqNumber + "'\n");
/* 347 */     buf.append("Control='" + this.control + "'\n");
/* 348 */     buf.append("CompCode='" + this.compCode + "'\n");
/* 349 */     buf.append("Reason='" + this.reason + "'\n");
/* 350 */     buf.append("ParameterCount='" + this.parameterCount + "'\n");
/*     */     
/* 352 */     for (int p = 0; p < this.parameterCount; p++) {
/* 353 */       buf.append("  Parameter " + (p + 1) + "\n");
/* 354 */       buf.append("    " + this.paramValues[p].toString() + "\n");
/*     */     } 
/*     */     
/* 357 */     String traceRet1 = buf.toString();
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "toString()", traceRet1);
/*     */     }
/* 361 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getParameterCount() {
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterCount()", "getter", 
/* 374 */           Integer.valueOf(this.parameterCount));
/*     */     }
/* 376 */     return this.parameterCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object getParameterAt(int i) {
/* 387 */     if (Trace.isOn)
/* 388 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterAt(int)", new Object[] {
/* 389 */             Integer.valueOf(i)
/*     */           }); 
/* 391 */     if (i < this.parameterCount) {
/* 392 */       Object traceRet1 = this.paramValues[i];
/* 393 */       if (Trace.isOn) {
/* 394 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterAt(int)", traceRet1, 1);
/*     */       }
/*     */       
/* 397 */       return traceRet1;
/*     */     } 
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterAt(int)", null, 2);
/*     */     }
/*     */     
/* 403 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getParameterType(int i) {
/* 413 */     if (Trace.isOn)
/* 414 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterType(int)", new Object[] {
/* 415 */             Integer.valueOf(i)
/*     */           }); 
/* 417 */     if (i < this.parameterCount) {
/* 418 */       int traceRet1 = this.paramTypes[i];
/* 419 */       if (Trace.isOn) {
/* 420 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterType(int)", 
/* 421 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 423 */       return traceRet1;
/*     */     } 
/* 425 */     int traceRet2 = -1;
/* 426 */     if (Trace.isOn) {
/* 427 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getParameterType(int)", 
/* 428 */           Integer.valueOf(traceRet2), 2);
/*     */     }
/* 430 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getControl() {
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getControl()", "getter", 
/* 440 */           Integer.valueOf(this.control));
/*     */     }
/* 442 */     return this.control;
/*     */   }
/*     */ 
/*     */   
/*     */   int getMsgSeqNumber() {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getMsgSeqNumber()", "getter", 
/* 449 */           Integer.valueOf(this.msgSeqNumber));
/*     */     }
/* 451 */     return this.msgSeqNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   int getCompCode() {
/* 456 */     if (Trace.isOn) {
/* 457 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getCompCode()", "getter", 
/* 458 */           Integer.valueOf(this.compCode));
/*     */     }
/* 460 */     return this.compCode;
/*     */   }
/*     */ 
/*     */   
/*     */   int getReason() {
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "getReason()", "getter", 
/* 467 */           Integer.valueOf(this.reason));
/*     */     }
/* 469 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addParameter(int paramId, int value) {
/* 478 */     if (Trace.isOn)
/* 479 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "addParameter(int,int)", new Object[] {
/* 480 */             Integer.valueOf(paramId), Integer.valueOf(value)
/*     */           }); 
/* 482 */     if (this.paramOut == null) {
/* 483 */       this.paramOut = new Vector();
/*     */     }
/*     */     
/* 486 */     MQCFIN param = new MQCFIN(paramId, value);
/* 487 */     this.paramOut.addElement(param);
/* 488 */     this.parameterCount++;
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "addParameter(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void addParameter(int paramId, String value) {
/* 497 */     if (Trace.isOn)
/* 498 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "addParameter(int,String)", new Object[] {
/* 499 */             Integer.valueOf(paramId), value
/*     */           }); 
/* 501 */     if (this.paramOut == null) {
/* 502 */       this.paramOut = new Vector();
/*     */     }
/*     */     
/* 505 */     MQCFST param = new MQCFST(paramId, value);
/* 506 */     this.paramOut.addElement(param);
/* 507 */     this.parameterCount++;
/* 508 */     if (Trace.isOn) {
/* 509 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PCF", "addParameter(int,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class MQCFST
/*     */   {
/*     */     int strucLength;
/*     */ 
/*     */     
/*     */     int paramId;
/*     */ 
/*     */     
/*     */     int CCSID;
/*     */ 
/*     */     
/*     */     int strLength;
/*     */ 
/*     */     
/*     */     String value;
/*     */ 
/*     */ 
/*     */     
/*     */     MQCFST(MQMessage msg) throws Exception {
/* 534 */       if (Trace.isOn) {
/* 535 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "<init>(MQMessage)", new Object[] { msg });
/*     */       }
/*     */       
/* 538 */       this.strucLength = msg.readInt();
/* 539 */       this.paramId = msg.readInt();
/* 540 */       this.CCSID = msg.readInt();
/* 541 */       this.strLength = msg.readInt();
/*     */       
/* 543 */       byte[] strBuf = new byte[this.strLength];
/* 544 */       msg.readFully(strBuf);
/* 545 */       this.value = new String(strBuf, Charset.defaultCharset());
/*     */ 
/*     */       
/* 548 */       int mod = this.strLength % 4;
/* 549 */       if (mod != 0) {
/* 550 */         int skip = 4 - mod;
/* 551 */         byte[] dummy = new byte[skip];
/* 552 */         msg.readFully(dummy);
/*     */       } 
/* 554 */       if (Trace.isOn) {
/* 555 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "<init>(MQMessage)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MQCFST(int paramId, String value) {
/* 566 */       if (Trace.isOn)
/* 567 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "<init>(int,String)", new Object[] {
/* 568 */               Integer.valueOf(paramId), value
/*     */             }); 
/* 570 */       this.value = value;
/* 571 */       this.paramId = paramId;
/* 572 */       this.CCSID = 0;
/* 573 */       if (Trace.isOn) {
/* 574 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "<init>(int,String)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void writeTo(MQMessage msg) throws Exception {
/* 585 */       if (Trace.isOn) {
/* 586 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "writeTo(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { msg });
/*     */       }
/*     */ 
/*     */       
/* 590 */       this.strLength = (this.value == null) ? 0 : this.value.length();
/* 591 */       int padding = this.strLength % 4;
/*     */       
/* 593 */       if (padding != 0) {
/* 594 */         padding = 4 - padding;
/*     */       }
/*     */       
/* 597 */       this.strucLength = 20 + this.strLength + padding;
/*     */       
/* 599 */       msg.writeInt(4);
/* 600 */       msg.writeInt(this.strucLength);
/* 601 */       msg.writeInt(this.paramId);
/* 602 */       msg.writeInt(msg.characterSet);
/* 603 */       msg.writeInt(this.strLength);
/* 604 */       if (this.value != null) {
/* 605 */         msg.writeString(this.value);
/*     */       }
/*     */       
/* 608 */       while (padding-- > 0) {
/* 609 */         msg.write(32);
/*     */       }
/* 611 */       if (Trace.isOn) {
/* 612 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "writeTo(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 624 */       if (Trace.isOn) {
/* 625 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "toString()");
/*     */       }
/* 627 */       String traceRet1 = "MQCFST [StrucLength=" + this.strucLength + ",ParamId=" + this.paramId + ",CCSID=" + this.CCSID + ",StrLength=" + this.strLength + ",Value=" + this.value + "]";
/* 628 */       if (Trace.isOn) {
/* 629 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFST", "toString()", traceRet1);
/*     */       }
/*     */       
/* 632 */       return traceRet1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class MQCFIN
/*     */   {
/*     */     int strucLength;
/*     */ 
/*     */ 
/*     */     
/*     */     int paramId;
/*     */ 
/*     */     
/*     */     int value;
/*     */ 
/*     */ 
/*     */     
/*     */     MQCFIN(MQMessage msg) throws Exception {
/* 653 */       if (Trace.isOn) {
/* 654 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "<init>(MQMessage)", new Object[] { msg });
/*     */       }
/*     */       
/* 657 */       this.strucLength = msg.readInt();
/* 658 */       this.paramId = msg.readInt();
/* 659 */       this.value = msg.readInt();
/* 660 */       if (Trace.isOn) {
/* 661 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "<init>(MQMessage)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MQCFIN(int paramId, int value) {
/* 672 */       if (Trace.isOn)
/* 673 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "<init>(int,int)", new Object[] {
/* 674 */               Integer.valueOf(paramId), Integer.valueOf(value)
/*     */             }); 
/* 676 */       this.value = value;
/* 677 */       this.paramId = paramId;
/* 678 */       this.strucLength = 16;
/* 679 */       if (Trace.isOn) {
/* 680 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "<init>(int,int)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void writeTo(MQMessage msg) throws Exception {
/* 690 */       if (Trace.isOn) {
/* 691 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "writeTo(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { msg });
/*     */       }
/*     */       
/* 694 */       msg.writeInt(3);
/* 695 */       msg.writeInt(this.strucLength);
/* 696 */       msg.writeInt(this.paramId);
/* 697 */       msg.writeInt(this.value);
/* 698 */       if (Trace.isOn) {
/* 699 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "writeTo(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 711 */       if (Trace.isOn) {
/* 712 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "toString()");
/*     */       }
/* 714 */       String traceRet1 = "MQCFIN [StrucLength=" + this.strucLength + ",ParamId=" + this.paramId + ",Value=" + this.value + "]";
/* 715 */       if (Trace.isOn) {
/* 716 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFIN", "toString()", traceRet1);
/*     */       }
/*     */       
/* 719 */       return traceRet1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class MQCFSL
/*     */   {
/*     */     int strucLength;
/*     */ 
/*     */     
/*     */     int paramId;
/*     */ 
/*     */     
/*     */     int CCSID;
/*     */     
/*     */     int count;
/*     */     
/*     */     int strLength;
/*     */     
/*     */     String[] strings;
/*     */ 
/*     */     
/*     */     MQCFSL(MQMessage msg) throws Exception {
/* 743 */       if (Trace.isOn) {
/* 744 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFSL", "<init>(MQMessage)", new Object[] { msg });
/*     */       }
/*     */       
/* 747 */       this.strucLength = msg.readInt();
/* 748 */       this.paramId = msg.readInt();
/* 749 */       this.CCSID = msg.readInt();
/* 750 */       this.count = msg.readInt();
/* 751 */       this.strLength = msg.readInt();
/*     */       
/* 753 */       this.strings = new String[this.count];
/*     */       
/* 755 */       for (int s = 0; s < this.count; s++) {
/* 756 */         byte[] strBuf = new byte[this.strLength];
/* 757 */         msg.readFully(strBuf);
/* 758 */         this.strings[s] = new String(strBuf, Charset.defaultCharset());
/*     */       } 
/* 760 */       if (Trace.isOn) {
/* 761 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFSL", "<init>(MQMessage)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String[] getStrings() {
/* 772 */       if (Trace.isOn) {
/* 773 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFSL", "getStrings()", "getter", this.strings);
/*     */       }
/*     */       
/* 776 */       return this.strings;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 785 */       if (Trace.isOn) {
/* 786 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFSL", "toString()");
/*     */       }
/* 788 */       StringBuffer buf = new StringBuffer();
/* 789 */       buf.append("MQCFSL [StrucLength=" + this.strucLength + ",ParamId=" + this.paramId + ",CCSID=" + this.CCSID + ",Count=" + this.count + ",StrLength=" + this.strLength + ",Strings={");
/* 790 */       for (int i = 0; i < this.count; i++) {
/* 791 */         String s = this.strings[i];
/* 792 */         buf.append(s);
/* 793 */         if (i < this.count - 1) {
/* 794 */           buf.append(",");
/*     */         } else {
/* 796 */           buf.append("}]");
/*     */         } 
/*     */       } 
/* 799 */       String traceRet1 = buf.toString();
/* 800 */       if (Trace.isOn) {
/* 801 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQCFSL", "toString()", traceRet1);
/*     */       }
/*     */       
/* 804 */       return traceRet1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\PCF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */