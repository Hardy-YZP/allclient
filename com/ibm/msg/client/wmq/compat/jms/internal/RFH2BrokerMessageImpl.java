/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*     */ import java.util.Enumeration;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RFH2BrokerMessageImpl
/*     */   extends MQBrokerMessage
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH2BrokerMessageImpl.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH2BrokerMessageImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private RFH2 rfh2 = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private Vector auxilliaryNVPairs = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RFH2BrokerMessageImpl() {
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "<init>()");
/*     */     }
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "<init>()");
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
/*     */   public void clear() {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "clear()");
/*     */     }
/*     */     
/* 101 */     this.rfh2 = null;
/* 102 */     this.auxilliaryNVPairs = new Vector();
/* 103 */     super.clear();
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "clear()");
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
/*     */   public void initializeFromMessage(MQMsg2 message) throws JMSException {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "initializeFromMessage(MQMsg2)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 127 */       clear();
/* 128 */       this.rfh2 = new RFH2(message);
/*     */       
/* 130 */       String pscrFolder = null;
/*     */ 
/*     */       
/* 133 */       setEncoding(this.rfh2.getEncoding());
/* 134 */       setCodedCharSetId(this.rfh2.getCodedCharSetId());
/* 135 */       setFormat(this.rfh2.getFormat());
/*     */ 
/*     */ 
/*     */       
/* 139 */       String nameValueString = this.rfh2.getNameValueString();
/*     */       
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.traceData(this, "nameValueString is " + nameValueString, null);
/*     */       }
/*     */ 
/*     */       
/* 146 */       int index1 = nameValueString.indexOf("<pscr>");
/* 147 */       int index2 = nameValueString.indexOf("</pscr>");
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.traceData(this, "searched for pscr folder. Got index " + index1 + " and " + index2, null);
/*     */       }
/*     */ 
/*     */       
/* 153 */       if (index1 > -1 && index2 > 0) {
/* 154 */         pscrFolder = nameValueString.substring(index1, index2 + 7);
/* 155 */         if (Trace.isOn) {
/* 156 */           Trace.traceData(this, "PscrFolder is " + pscrFolder, null);
/*     */         }
/* 158 */         if (pscrFolder != null) {
/* 159 */           parsePubSubFolder(pscrFolder);
/*     */         }
/*     */       } 
/*     */       
/* 163 */       index1 = nameValueString.indexOf("<psc>");
/* 164 */       index2 = nameValueString.indexOf("</psc>");
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.traceData(this, "searched for psc folder. Got index " + index1 + " and " + index2, null);
/*     */       }
/* 168 */       if (index1 > -1 && index2 > 0) {
/* 169 */         String pscFolder = nameValueString.substring(index1, index2 + 6);
/*     */         
/* 171 */         if (Trace.isOn) {
/* 172 */           Trace.traceData(this, "Parsing psc folder: \"" + pscFolder + "\"", null);
/*     */         }
/* 174 */         if (pscFolder != null) {
/* 175 */           parsePubSubFolder(pscFolder);
/*     */         }
/*     */       } 
/*     */       
/* 179 */       if (pscrFolder != null) {
/* 180 */         if (Trace.isOn) {
/* 181 */           Trace.traceData(this, "PscrFolder is " + pscrFolder, null);
/*     */         }
/* 183 */         parsePubSubFolder(pscrFolder);
/*     */       }
/*     */     
/*     */     }
/* 187 */     catch (JMSException je) {
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "initializeFromMessage(MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "initializeFromMessage(MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 196 */       throw je;
/*     */     } 
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "initializeFromMessage(MQMsg2)");
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
/*     */   public void writeToMessage(MQMsg2 message) throws JMSException {
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "writeToMessage(MQMsg2)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 218 */       if (this.rfh2 == null) {
/* 219 */         this.rfh2 = new RFH2();
/*     */       }
/*     */ 
/*     */       
/* 223 */       this.rfh2.setEncoding(getEncoding());
/* 224 */       this.rfh2.setCodedCharSetId(getCodedCharSetId());
/* 225 */       this.rfh2.setFormat(getFormat());
/*     */ 
/*     */ 
/*     */       
/* 229 */       StringBuffer nvpBuffer = new StringBuffer();
/*     */ 
/*     */ 
/*     */       
/* 233 */       nvpBuffer.append("<psc>");
/* 234 */       String value = get("MQPSCommand");
/* 235 */       if (value != null) {
/* 236 */         nvpBuffer.append("<Command>");
/* 237 */         appendToken(nvpBuffer, value);
/* 238 */         nvpBuffer.append("</Command>");
/*     */       } 
/*     */       
/* 241 */       Enumeration<String> enumVar = getFields();
/* 242 */       while (enumVar.hasMoreElements()) {
/* 243 */         String field = enumVar.nextElement();
/*     */ 
/*     */ 
/*     */         
/* 247 */         if (!field.equals("MQPSCommand") && !field.equals("MQPSStreamName")) {
/* 248 */           value = get(field);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 253 */           if (field.equals("MQPSRegOpts")) {
/* 254 */             field = "RegOpt";
/* 255 */           } else if (field.equals("MQPSPubOpts")) {
/* 256 */             field = "PubOpt";
/*     */           } else {
/*     */             
/* 259 */             field = field.substring(4);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 264 */           if (field.equals("QName")) {
/* 265 */             value = value.trim();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 270 */           nvpBuffer.append("<");
/* 271 */           appendToken(nvpBuffer, field);
/* 272 */           nvpBuffer.append(">");
/* 273 */           appendToken(nvpBuffer, value);
/* 274 */           nvpBuffer.append("</" + field + ">");
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 279 */       enumVar = this.auxilliaryNVPairs.elements();
/* 280 */       while (enumVar.hasMoreElements()) {
/* 281 */         String field = enumVar.nextElement();
/* 282 */         value = enumVar.nextElement();
/*     */         
/* 284 */         nvpBuffer.append("<");
/* 285 */         appendToken(nvpBuffer, field);
/* 286 */         nvpBuffer.append(">");
/* 287 */         appendToken(nvpBuffer, value);
/* 288 */         nvpBuffer.append(" </" + field + ">");
/*     */       } 
/*     */ 
/*     */       
/* 292 */       nvpBuffer.append("</psc>");
/*     */       
/* 294 */       String nameValueString = nvpBuffer.toString();
/* 295 */       if (Trace.isOn) {
/* 296 */         Trace.traceData(this, "RFH2 nameValueString is " + nameValueString, null);
/*     */       }
/* 298 */       this.rfh2.setNameValueString(nameValueString);
/* 299 */       this.rfh2.setNameValueLength(nameValueString.length());
/*     */ 
/*     */       
/* 302 */       this.rfh2.write(message);
/*     */     }
/* 304 */     catch (JMSException je) {
/* 305 */       if (Trace.isOn) {
/* 306 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "writeToMessage(MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 309 */       if (Trace.isOn) {
/* 310 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "writeToMessage(MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 313 */       throw je;
/*     */     } 
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "writeToMessage(MQMsg2)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHeaderFormat() {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "getHeaderFormat()", "getter", "MQHRF2  ");
/*     */     }
/*     */     
/* 328 */     return "MQHRF2  ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendToken(StringBuffer sb, String token) {
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "appendToken(StringBuffer,String)", new Object[] { sb, token });
/*     */     }
/*     */ 
/*     */     
/* 344 */     if (token.equals("")) {
/*     */       
/* 346 */       sb.append(token);
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "appendToken(StringBuffer,String)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 354 */     for (char c : token.toCharArray()) {
/* 355 */       if (c == '&') {
/* 356 */         sb.append("&amp;");
/* 357 */       } else if (c == '\'') {
/* 358 */         sb.append("&apos;");
/* 359 */       } else if (c == '"') {
/* 360 */         sb.append("&quot;");
/* 361 */       } else if (c == '>') {
/* 362 */         sb.append("&gt;");
/* 363 */       } else if (c == '<') {
/* 364 */         sb.append("&lt;");
/*     */       } else {
/* 366 */         sb.append(c);
/*     */       } 
/*     */     } 
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "appendToken(StringBuffer,String)", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String removeEscapes(String value) throws JMSException {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "removeEscapes(String)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/* 383 */     if (value == null) {
/* 384 */       if (Trace.isOn) {
/* 385 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "removeEscapes(String)", null, 1);
/*     */       }
/*     */       
/* 388 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 393 */     if (value.equals("") || value.indexOf('&') < 0) {
/* 394 */       if (Trace.isOn) {
/* 395 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "removeEscapes(String)", value, 2);
/*     */       }
/*     */       
/* 398 */       return value;
/*     */     } 
/*     */     
/* 401 */     StringBuffer sb = new StringBuffer(value.length());
/*     */ 
/*     */     
/* 404 */     for (int i = 0; i < value.length(); i++) {
/* 405 */       char ch = value.charAt(i);
/*     */       
/* 407 */       if (ch == '&') {
/* 408 */         String entity = value.substring(i + 1, i + 4);
/*     */         
/* 410 */         if (entity.startsWith("lt;")) {
/* 411 */           sb.append('<');
/* 412 */           i += 3;
/* 413 */         } else if (entity.startsWith("gt;")) {
/* 414 */           sb.append('>');
/* 415 */           i += 3;
/* 416 */         } else if (entity.startsWith("amp")) {
/* 417 */           sb.append("&");
/* 418 */           i += 4;
/* 419 */         } else if (entity.startsWith("apo")) {
/* 420 */           sb.append('\'');
/* 421 */           i += 5;
/* 422 */         } else if (entity.startsWith("quo")) {
/* 423 */           sb.append('"');
/* 424 */           i += 5;
/*     */         }
/*     */         else {
/*     */           
/* 428 */           if (Trace.isOn) {
/* 429 */             Trace.traceData(this, "Error: invalid escape sequence found &" + entity, null);
/*     */           }
/* 431 */           JMSException je = ConfigEnvironment.newException("MQJMS1054", "&" + entity);
/*     */           
/* 433 */           if (Trace.isOn) {
/* 434 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "removeEscapes(String)", (Throwable)je);
/*     */           }
/*     */ 
/*     */           
/* 438 */           throw je;
/*     */         } 
/*     */       } else {
/*     */         
/* 442 */         sb.append(ch);
/*     */       } 
/*     */     } 
/*     */     
/* 446 */     String traceRet1 = sb.toString();
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "removeEscapes(String)", traceRet1, 3);
/*     */     }
/*     */     
/* 451 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parsePubSubFolder(String folder) throws JMSException {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "parsePubSubFolder(String)", new Object[] { folder });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 471 */     String reason = null;
/*     */     
/* 473 */     StringTokenizer strtok = new StringTokenizer(folder, "<>");
/*     */     
/* 475 */     String foldername = strtok.nextToken();
/* 476 */     if (!foldername.equals("pscr") && !foldername.equals("psc")) {
/* 477 */       if (Trace.isOn) {
/* 478 */         Trace.traceData(this, "Invalid psc or pscr folder", null);
/*     */       }
/* 480 */       JMSException je = ConfigEnvironment.newException("MQJMS1050");
/* 481 */       if (Trace.isOn) {
/* 482 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "parsePubSubFolder(String)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 485 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 489 */     String name = strtok.nextToken();
/*     */ 
/*     */     
/* 492 */     while (!name.equals("/" + foldername)) {
/*     */       
/* 494 */       String tempvalue = strtok.nextToken();
/*     */ 
/*     */ 
/*     */       
/* 498 */       if (!tempvalue.equals("/" + name)) {
/*     */ 
/*     */         
/* 501 */         String value = removeEscapes(tempvalue);
/*     */ 
/*     */ 
/*     */         
/* 505 */         if (name.startsWith("Completion")) {
/* 506 */           if (value.equals("ok")) {
/*     */ 
/*     */             
/* 509 */             value = Integer.toString(0);
/* 510 */             reason = Integer.toString(0);
/* 511 */             rfh2_update("MQPSReason", reason);
/* 512 */             rfh2_update("MQPSReasonText", reason + " ");
/* 513 */           } else if (value.equals("warning")) {
/* 514 */             value = Integer.toString(1);
/* 515 */           } else if (value.equals("error")) {
/* 516 */             value = Integer.toString(2);
/*     */           } else {
/*     */             
/* 519 */             if (Trace.isOn) {
/* 520 */               Trace.traceData(this, "Invalid Completion Code in pscr folder", null);
/*     */             }
/* 522 */             JMSException je = ConfigEnvironment.newException("MQJMS1050");
/* 523 */             if (Trace.isOn) {
/* 524 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "parsePubSubFolder(String)", (Throwable)je, 2);
/*     */             }
/*     */ 
/*     */             
/* 528 */             throw je;
/*     */           } 
/*     */           
/* 531 */           if (Trace.isOn) {
/* 532 */             Trace.traceData(this, "CompletionCode is " + value, null);
/*     */           }
/* 534 */           rfh2_update("MQPSCompCode", value);
/* 535 */           String endname = strtok.nextToken();
/*     */         
/*     */         }
/* 538 */         else if (name.startsWith("Response")) {
/*     */ 
/*     */           
/* 541 */           if (value.startsWith("Reason")) {
/* 542 */             value = strtok.nextToken();
/*     */ 
/*     */             
/* 545 */             if (Trace.isOn) {
/* 546 */               Trace.traceData(this, "Reason is " + value, null);
/*     */             }
/* 548 */             if (value != null) {
/* 549 */               rfh2_update("MQPSReason", value);
/* 550 */               rfh2_update("MQPSReasonText", value + " ");
/*     */             } 
/*     */             
/* 553 */             String endname = strtok.nextToken();
/*     */             
/* 555 */             while (!endname.equals("/Response")) {
/* 556 */               endname = strtok.nextToken();
/*     */             }
/*     */           }
/*     */         
/*     */         }
/* 561 */         else if (name.startsWith("Reason")) {
/* 562 */           if (Trace.isOn) {
/* 563 */             Trace.traceData(this, "Reason is " + value, null);
/*     */           }
/* 565 */           if (value != null) {
/* 566 */             rfh2_update("MQPSReason", value);
/* 567 */             rfh2_update("MQPSReasonText", value + " ");
/*     */           } 
/* 569 */           String endname = strtok.nextToken();
/*     */         
/*     */         }
/* 572 */         else if (name.startsWith("RegOpt")) {
/* 573 */           if (Trace.isOn) {
/* 574 */             Trace.traceData(this, "RegOpt are " + value, null);
/*     */           }
/* 576 */           if (value != null) {
/* 577 */             rfh2_update("MQPSRegOpts", value);
/*     */           }
/* 579 */           String endname = strtok.nextToken();
/* 580 */         } else if (name.startsWith("PubOpt")) {
/* 581 */           if (Trace.isOn) {
/* 582 */             Trace.traceData(this, "PubOpt are " + value, null);
/*     */           }
/* 584 */           if (value != null) {
/* 585 */             rfh2_update("MQPSPubOpts", value);
/*     */           }
/* 587 */           String endname = strtok.nextToken();
/* 588 */         } else if (name.startsWith("DelOpt")) {
/* 589 */           if (Trace.isOn) {
/* 590 */             Trace.traceData(this, "DelOpt are " + value, null);
/*     */           }
/* 592 */           if (value != null) {
/* 593 */             rfh2_update("MQPSDelOpts", value);
/*     */           }
/* 595 */           String endname = strtok.nextToken();
/*     */         }
/*     */         else {
/*     */           
/* 599 */           if (Trace.isOn) {
/* 600 */             Trace.traceData(this, "Name is " + name + ", Value is " + value, null);
/*     */           }
/* 602 */           if (value != null) {
/* 603 */             rfh2_update("MQPS" + name, value);
/*     */           }
/* 605 */           String endname = strtok.nextToken();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 610 */         rfh2_update("MQPS" + name, "");
/*     */       } 
/*     */       
/* 613 */       name = strtok.nextToken();
/*     */     } 
/*     */     
/* 616 */     if (Trace.isOn) {
/* 617 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "parsePubSubFolder(String)");
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
/*     */   public void rfh2_update(String name, String value) throws JMSException {
/* 630 */     if (Trace.isOn) {
/* 631 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "rfh2_update(String,String)", new Object[] { name, value });
/*     */     }
/*     */     
/*     */     try {
/* 635 */       update(name, value);
/*     */     }
/* 637 */     catch (JMSException je) {
/* 638 */       if (Trace.isOn) {
/* 639 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "rfh2_update(String,String)", (Throwable)je);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 644 */       if (Trace.isOn) {
/* 645 */         Trace.traceData(this, "Repeated Name/Value found, saving in auxilliary array Name " + name + "Value " + value, null);
/*     */       }
/*     */       
/* 648 */       this.auxilliaryNVPairs.addElement(name);
/* 649 */       this.auxilliaryNVPairs.addElement(value);
/*     */     } 
/* 651 */     if (Trace.isOn)
/* 652 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2BrokerMessageImpl", "rfh2_update(String,String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\RFH2BrokerMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */