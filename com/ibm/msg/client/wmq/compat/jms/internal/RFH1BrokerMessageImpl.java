/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*     */ import java.util.Enumeration;
/*     */ import java.util.NoSuchElementException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RFH1BrokerMessageImpl
/*     */   extends MQBrokerMessage
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH1BrokerMessageImpl.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH1BrokerMessageImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private RFH rfh = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private Vector auxilliaryNVPairs = new Vector();
/*     */   
/*     */   private static final int PARSE_STATE_START = 0;
/*     */   private static final int PARSE_STATE_UNQUOTED_TOKEN = 1;
/*     */   
/*     */   public RFH1BrokerMessageImpl() {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "<init>()");
/*     */     }
/*     */     
/*  92 */     if (Trace.isOn)
/*  93 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "<init>()"); 
/*     */   }
/*     */   
/*     */   private static final int PARSE_STATE_QUOTED_TOKEN = 2;
/*     */   private static final int PARSE_STATE_AFTER_ONE_QUOTE = 3;
/*     */   private static final int PARSE_STATE_FINISHED = 4;
/*     */   private static final int PARSE_TOKEN_NORMAL = 0;
/*     */   private static final int PARSE_TOKEN_SPACE = 1;
/*     */   private static final int PARSE_TOKEN_QUOTE = 2;
/*     */   private static final int PARSE_TOKEN_END = 3;
/*     */   
/*     */   public void clear() {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "clear()");
/*     */     }
/*     */     
/* 109 */     this.rfh = null;
/* 110 */     this.auxilliaryNVPairs = new Vector();
/* 111 */     super.clear();
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "clear()");
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
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 135 */       clear();
/* 136 */       this.rfh = new RFH(message);
/*     */ 
/*     */       
/* 139 */       setEncoding(this.rfh.getEncoding());
/* 140 */       setCodedCharSetId(this.rfh.getCodedCharSetId());
/* 141 */       setFormat(this.rfh.getFormat());
/*     */ 
/*     */ 
/*     */       
/* 145 */       String nameValueString = this.rfh.getNameValueString();
/*     */       
/* 147 */       Vector v = parseNameValues(nameValueString);
/* 148 */       Enumeration<String> e = v.elements();
/* 149 */       while (e.hasMoreElements()) {
/*     */         String name; String value;
/*     */         try {
/* 152 */           name = e.nextElement();
/* 153 */           value = e.nextElement();
/*     */         }
/* 155 */         catch (NoSuchElementException nsee) {
/* 156 */           if (Trace.isOn) {
/* 157 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)", nsee, 1);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 162 */           JMSException je = ConfigEnvironment.newException("MQJMS1087");
/* 163 */           je.setLinkedException(nsee);
/* 164 */           if (Trace.isOn) {
/* 165 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)", (Throwable)je, 1);
/*     */           }
/*     */ 
/*     */           
/* 169 */           throw je;
/*     */         } 
/*     */         
/*     */         try {
/* 173 */           update(name, value);
/*     */         }
/* 175 */         catch (JMSException je) {
/* 176 */           if (Trace.isOn) {
/* 177 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)", (Throwable)je, 2);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 183 */           this.auxilliaryNVPairs.addElement(name);
/* 184 */           this.auxilliaryNVPairs.addElement(value);
/*     */         }
/*     */       
/*     */       } 
/* 188 */     } catch (JMSException je) {
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)", (Throwable)je, 3);
/*     */       }
/*     */       
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 197 */       throw je;
/*     */     } 
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "initializeFromMessage(MQMsg2)");
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
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "writeToMessage(MQMsg2)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 219 */       if (this.rfh == null) {
/* 220 */         this.rfh = new RFH();
/*     */       }
/*     */ 
/*     */       
/* 224 */       this.rfh.setEncoding(getEncoding());
/* 225 */       this.rfh.setCodedCharSetId(getCodedCharSetId());
/* 226 */       this.rfh.setFormat(getFormat());
/*     */ 
/*     */ 
/*     */       
/* 230 */       StringBuffer nvpBuffer = new StringBuffer();
/* 231 */       boolean prependSpace = false;
/*     */ 
/*     */       
/* 234 */       String value = get("MQPSCommand");
/* 235 */       if (value != null) {
/* 236 */         if (prependSpace) {
/* 237 */           nvpBuffer.append(' ');
/*     */         }
/* 239 */         nvpBuffer.append("MQPSCommand");
/* 240 */         nvpBuffer.append(' ');
/* 241 */         appendToken(nvpBuffer, value);
/* 242 */         prependSpace = true;
/*     */       } 
/*     */ 
/*     */       
/* 246 */       Enumeration<String> enumVar = getFields();
/* 247 */       while (enumVar.hasMoreElements()) {
/* 248 */         String field = enumVar.nextElement();
/* 249 */         if (!field.equals("MQPSCommand")) {
/* 250 */           value = get(field);
/*     */ 
/*     */ 
/*     */           
/* 254 */           if (prependSpace) {
/* 255 */             nvpBuffer.append(' ');
/*     */           }
/* 257 */           appendToken(nvpBuffer, field);
/* 258 */           nvpBuffer.append(' ');
/* 259 */           appendToken(nvpBuffer, value);
/* 260 */           prependSpace = true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 265 */       enumVar = this.auxilliaryNVPairs.elements();
/* 266 */       while (enumVar.hasMoreElements()) {
/* 267 */         String field = enumVar.nextElement();
/* 268 */         value = enumVar.nextElement();
/*     */         
/* 270 */         if (prependSpace) {
/* 271 */           nvpBuffer.append(' ');
/*     */         }
/* 273 */         appendToken(nvpBuffer, field);
/* 274 */         nvpBuffer.append(' ');
/* 275 */         appendToken(nvpBuffer, value);
/* 276 */         prependSpace = true;
/*     */       } 
/*     */       
/* 279 */       String nameValueString = nvpBuffer.toString();
/* 280 */       this.rfh.setNameValueString(nameValueString);
/*     */ 
/*     */       
/* 283 */       this.rfh.write(message);
/*     */     }
/* 285 */     catch (JMSException je) {
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "writeToMessage(MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 290 */       if (Trace.isOn) {
/* 291 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "writeToMessage(MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 294 */       throw je;
/*     */     } 
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "writeToMessage(MQMsg2)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHeaderFormat() {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "getHeaderFormat()", "getter", "MQHRF   ");
/*     */     }
/*     */     
/* 309 */     return "MQHRF   ";
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
/*     */   private Vector parseNameValues(String nvsP) throws JMSException {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "parseNameValues(String)", new Object[] { nvsP });
/*     */     }
/*     */ 
/*     */     
/* 337 */     String nvs = nvsP;
/* 338 */     int state = 0;
/* 339 */     Vector<String> out = new Vector();
/* 340 */     String partialToken = null;
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.traceData(this, "parseNameValues>>" + nvs, null);
/*     */     }
/*     */ 
/*     */     
/* 346 */     String null_char = "\000";
/* 347 */     String blank = "";
/* 348 */     int i = nvs.indexOf(null_char);
/* 349 */     while (i != -1 && i < nvs.length()) {
/*     */       
/* 351 */       nvs = nvs.substring(0, i) + blank + nvs.substring(i + null_char.length(), nvs.length());
/*     */       
/* 353 */       i = nvs.indexOf(null_char, i);
/*     */     } 
/*     */     
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.traceData(this, "parseNameValues<<" + nvs, null);
/*     */     }
/*     */     try {
/* 360 */       StringTokenizer stok = new StringTokenizer(nvs, " \"\000", true);
/*     */       
/* 362 */       while (state != 4) {
/*     */         int tokType;
/*     */         String tok;
/* 365 */         if (stok.hasMoreElements()) {
/* 366 */           tok = stok.nextToken();
/*     */ 
/*     */           
/* 369 */           tokType = 0;
/* 370 */           if (tok.equals(" ")) {
/* 371 */             tokType = 1;
/* 372 */           } else if (tok.equals("\"")) {
/* 373 */             tokType = 2;
/* 374 */           } else if (tok.equals("\000")) {
/* 375 */             tokType = 3;
/*     */           } 
/*     */         } else {
/*     */           
/* 379 */           tokType = 3;
/* 380 */           tok = null;
/*     */         } 
/*     */         
/* 383 */         if (Trace.isOn) {
/* 384 */           Trace.traceData(this, "Parser state is " + state + ", token is " + tok + " of type " + tokType, null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 389 */         switch (state) {
/*     */           
/*     */           case 0:
/* 392 */             switch (tokType) {
/*     */ 
/*     */ 
/*     */               
/*     */               case 0:
/* 397 */                 out.addElement(tok);
/* 398 */                 state = 1;
/*     */                 continue;
/*     */ 
/*     */               
/*     */               case 1:
/* 403 */                 state = 0;
/*     */                 continue;
/*     */ 
/*     */               
/*     */               case 2:
/* 408 */                 state = 2;
/* 409 */                 partialToken = "";
/*     */                 continue;
/*     */ 
/*     */               
/*     */               case 3:
/* 414 */                 state = 4;
/*     */                 continue;
/*     */             } 
/*     */             
/* 418 */             badTokType();
/*     */             continue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 1:
/* 426 */             switch (tokType) {
/*     */               
/*     */               case 0:
/*     */               case 2:
/* 430 */                 invalidNVPairs();
/*     */                 continue;
/*     */               
/*     */               case 1:
/* 434 */                 state = 0;
/*     */                 continue;
/*     */               
/*     */               case 3:
/* 438 */                 state = 4;
/*     */                 continue;
/*     */             } 
/*     */             
/* 442 */             badTokType();
/*     */             continue;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 449 */             switch (tokType) {
/*     */               
/*     */               case 0:
/* 452 */                 partialToken = partialToken + tok;
/* 453 */                 state = 2;
/*     */                 continue;
/*     */               
/*     */               case 1:
/* 457 */                 partialToken = partialToken + ' ';
/* 458 */                 state = 2;
/*     */                 continue;
/*     */ 
/*     */ 
/*     */               
/*     */               case 2:
/* 464 */                 state = 3;
/*     */                 continue;
/*     */ 
/*     */               
/*     */               case 3:
/* 469 */                 invalidNVPairs();
/*     */                 continue;
/*     */             } 
/*     */             
/* 473 */             badTokType();
/*     */             continue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 3:
/* 482 */             switch (tokType) {
/*     */ 
/*     */               
/*     */               case 0:
/* 486 */                 invalidNVPairs();
/*     */                 continue;
/*     */ 
/*     */               
/*     */               case 1:
/* 491 */                 out.addElement(partialToken);
/* 492 */                 partialToken = null;
/* 493 */                 state = 0;
/*     */                 continue;
/*     */ 
/*     */               
/*     */               case 2:
/* 498 */                 partialToken = partialToken + '"';
/* 499 */                 state = 2;
/*     */                 continue;
/*     */ 
/*     */ 
/*     */               
/*     */               case 3:
/* 505 */                 out.addElement(partialToken);
/* 506 */                 partialToken = null;
/* 507 */                 state = 4;
/*     */                 continue;
/*     */             } 
/*     */             
/* 511 */             badTokType();
/*     */             continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 518 */         badState();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 523 */       if (Trace.isOn) {
/* 524 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "parseNameValues(String)", out);
/*     */       }
/*     */       
/* 527 */       return out;
/*     */     }
/* 529 */     catch (JMSException je) {
/* 530 */       if (Trace.isOn) {
/* 531 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "parseNameValues(String)", (Throwable)je);
/*     */       }
/*     */       
/* 534 */       if (Trace.isOn) {
/* 535 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "parseNameValues(String)", (Throwable)je);
/*     */       }
/*     */       
/* 538 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void badTokType() throws JMSException {
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "badTokType()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 554 */     String detail = "RFH1BrokerMessageImpl encountered impossible token type";
/*     */     
/* 556 */     JMSException je = ConfigEnvironment.newException("MQJMS1016", detail);
/* 557 */     if (Trace.isOn) {
/* 558 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "badTokType()", (Throwable)je);
/*     */     }
/*     */     
/* 561 */     throw je;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void badState() throws JMSException {
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "badState()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 577 */     String detail = "RFH1BrokerMessageImpl encountered impossible state value";
/*     */     
/* 579 */     JMSException je = ConfigEnvironment.newException("MQJMS1016", detail);
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "badState()", (Throwable)je);
/*     */     }
/*     */     
/* 584 */     throw je;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void invalidNVPairs() throws JMSException {
/* 594 */     if (Trace.isOn) {
/* 595 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "invalidNVPairs()");
/*     */     }
/*     */ 
/*     */     
/* 599 */     JMSException je = ConfigEnvironment.newException("MQJMS1087");
/* 600 */     if (Trace.isOn) {
/* 601 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "invalidNVPairs()", (Throwable)je);
/*     */     }
/*     */     
/* 604 */     throw je;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendToken(StringBuffer sb, String token) {
/* 614 */     if (Trace.isOn) {
/* 615 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "appendToken(StringBuffer,String)", new Object[] { sb, token });
/*     */     }
/*     */ 
/*     */     
/* 619 */     formatNameValueValue(sb, token);
/*     */     
/* 621 */     if (Trace.isOn) {
/* 622 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "appendToken(StringBuffer,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void formatNameValueValue(StringBuffer sb, String token) {
/* 632 */     if (Trace.isOn) {
/* 633 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "formatNameValueValue(StringBuffer,String)", new Object[] { sb, token });
/*     */     }
/*     */     
/* 636 */     if (Trace.isOn) {
/* 637 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "formatNameValueValue(StringBuffer,String)", new Object[] { sb.toString(), token });
/*     */     }
/*     */ 
/*     */     
/* 641 */     if (token.equals("")) {
/* 642 */       sb.append("\"\"");
/* 643 */     } else if (token.indexOf("\"") == -1) {
/* 644 */       if (token.indexOf(" ") == -1) {
/* 645 */         sb.append(token);
/*     */       } else {
/*     */         
/* 648 */         sb.append('"');
/* 649 */         sb.append(token);
/* 650 */         sb.append('"');
/*     */       } 
/*     */     } else {
/*     */       
/* 654 */       StringTokenizer tok = new StringTokenizer(token, "\"", true);
/* 655 */       sb.append('"');
/* 656 */       while (tok.hasMoreTokens()) {
/* 657 */         String s = tok.nextToken();
/* 658 */         if (s.equals("\"")) {
/* 659 */           sb.append("\"\""); continue;
/*     */         } 
/* 661 */         sb.append(s);
/*     */       } 
/*     */       
/* 664 */       sb.append("\"");
/*     */     } 
/* 666 */     if (Trace.isOn) {
/* 667 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "formatNameValueValue(StringBuffer,String)", new Object[] { sb.toString() });
/*     */     }
/* 669 */     if (Trace.isOn)
/* 670 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.RFH1BrokerMessageImpl", "formatNameValueValue(StringBuffer,String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\RFH1BrokerMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */