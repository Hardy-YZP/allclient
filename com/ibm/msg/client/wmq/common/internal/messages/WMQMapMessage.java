/*      */ package com.ibm.msg.client.wmq.common.internal.messages;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Vector;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMQMapMessage
/*      */   extends WMQMessage
/*      */   implements ProviderMapMessage
/*      */ {
/*      */   private static final long serialVersionUID = 4255553665929966757L;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMapMessage.java";
/*      */   
/*      */   static {
/*   47 */     if (Trace.isOn) {
/*   48 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMapMessage.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Tokenizer
/*      */   {
/*   68 */     private Vector<String> tokens = new Vector<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Tokenizer(String string) {
/*   76 */       if (Trace.isOn) {
/*   77 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.Tokenizer", "<init>(String)", new Object[] { string });
/*      */       }
/*      */       
/*   80 */       int length = string.length();
/*      */       
/*   82 */       boolean hide = false;
/*      */       
/*   84 */       int tokenStart = 0;
/*      */       
/*   86 */       boolean decorated = false;
/*      */       
/*   88 */       for (int index = 0; index < length; index++) {
/*   89 */         char ch = string.charAt(index);
/*   90 */         if (ch == '"') {
/*   91 */           hide = !hide;
/*      */         
/*      */         }
/*   94 */         else if (!hide) {
/*      */ 
/*      */           
/*   97 */           if (decorated) {
/*   98 */             if (ch == '>') {
/*   99 */               if (index > tokenStart) {
/*  100 */                 String token = string.substring(tokenStart + 1, index);
/*  101 */                 if (token.length() > 0) {
/*  102 */                   this.tokens.add(token);
/*      */                 }
/*      */               } 
/*  105 */               decorated = false;
/*  106 */               tokenStart = index;
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  111 */           else if (ch == '<') {
/*  112 */             if (index > tokenStart) {
/*  113 */               String token = string.substring(tokenStart + 1, index);
/*  114 */               if (token.length() > 0) {
/*  115 */                 this.tokens.add(token);
/*      */               }
/*      */             } 
/*  118 */             decorated = true;
/*  119 */             tokenStart = index;
/*      */           } 
/*      */         } 
/*      */       } 
/*  123 */       if (length - tokenStart > 2) {
/*  124 */         String token = string.substring(tokenStart + 1, length - 1);
/*  125 */         this.tokens.add(token);
/*      */       } 
/*  127 */       if (Trace.isOn) {
/*  128 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.Tokenizer", "<init>(String)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String nextToken() {
/*      */       String token;
/*  140 */       if (Trace.isOn) {
/*  141 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.Tokenizer", "nextToken()");
/*      */       }
/*      */ 
/*      */       
/*  145 */       if (this.tokens.size() > 0) {
/*  146 */         token = this.tokens.firstElement();
/*  147 */         this.tokens.remove(0);
/*      */       } else {
/*      */         
/*  150 */         NoSuchElementException traceRet1 = new NoSuchElementException();
/*  151 */         if (Trace.isOn) {
/*  152 */           Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.Tokenizer", "nextToken()", traceRet1);
/*      */         }
/*      */         
/*  155 */         throw traceRet1;
/*      */       } 
/*  157 */       if (Trace.isOn) {
/*  158 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.Tokenizer", "nextToken()", token);
/*      */       }
/*      */       
/*  161 */       return token;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   private Map<String, Object> mapBody = Collections.synchronizedMap(new HashMap<>());
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean mapNameStyle = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WMQMapMessage() throws JMSException {
/*  181 */     if (Trace.isOn) {
/*  182 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "<init>()");
/*      */     }
/*      */     
/*  185 */     this.messageClass = "jms_map";
/*  186 */     if (Trace.isOn) {
/*  187 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/*  198 */     if (Trace.isOn) {
/*  199 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/*  200 */             Integer.valueOf(encoding), codepage
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  206 */     StringBuilder body = new StringBuilder(100);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  211 */     Enumeration<String> elementNames = getMapNames();
/*      */     
/*  213 */     body.append("<map>");
/*      */ 
/*      */     
/*  216 */     while (elementNames.hasMoreElements()) {
/*  217 */       String name = elementNames.nextElement();
/*      */ 
/*      */       
/*  220 */       Object value = getObject(name);
/*      */ 
/*      */       
/*  223 */       eltFormatElement(name, value, body);
/*      */     } 
/*      */     
/*  226 */     body.append("</map>");
/*      */     
/*  228 */     byte[] bodyBytes = WMQUtils.computeBytesFromText(body.toString(), codepage);
/*      */     
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_exportBody(int,JmqiCodepage)", bodyBytes);
/*      */     }
/*      */     
/*  234 */     return bodyBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String _handleIllegalCharacters(String propertyName) throws JMSException {
/*  251 */     if (Trace.isOn) {
/*  252 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_handleIllegalCharacters(String)", new Object[] { propertyName });
/*      */     }
/*      */     
/*      */     try {
/*  256 */       if (propertyName.length() > 3 && propertyName.substring(0, 3).equals("elt")) {
/*  257 */         String name = propertyName;
/*  258 */         String realName = "";
/*  259 */         String nameIntro = "";
/*      */ 
/*      */ 
/*      */         
/*  263 */         boolean newNull = false;
/*  264 */         int nullIndex = 0;
/*  265 */         nullIndex = name.indexOf(" xsi:nil");
/*      */ 
/*      */         
/*  268 */         if (nullIndex != -1) {
/*  269 */           newNull = true;
/*      */         } else {
/*      */           
/*  272 */           newNull = false;
/*      */         } 
/*      */ 
/*      */         
/*  276 */         String nullbit = "";
/*  277 */         if (newNull)
/*      */         {
/*  279 */           nullbit = name.substring(nullIndex, nullIndex + 15);
/*      */         }
/*      */         
/*  282 */         int endbit = name.indexOf(" dt=");
/*  283 */         if (endbit != -1) {
/*  284 */           nameIntro = name.substring(0, name.indexOf("name=") + 5);
/*  285 */           int realNameStart = nameIntro.length() + 1;
/*  286 */           int realNameEnd = name.indexOf("dt=") - 2;
/*      */ 
/*      */           
/*  289 */           realName = name.substring(realNameStart, realNameEnd);
/*      */           
/*  291 */           realName = realName + name.substring(endbit, name.length());
/*  292 */           name = realName + nullbit;
/*  293 */           if (Trace.isOn) {
/*  294 */             Trace.traceData(this, "Altered Name = " + name, null);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  299 */           nameIntro = name.substring(0, name.indexOf("name=") + 5);
/*  300 */           int realNameStart = nameIntro.length() + 1;
/*  301 */           if (newNull) {
/*  302 */             realName = name.substring(realNameStart, name.indexOf("xsi:nil") - 2);
/*      */           } else {
/*      */             
/*  305 */             realName = name.substring(realNameStart, name.length() - 1);
/*      */           } 
/*      */           
/*  308 */           name = realName + nullbit;
/*  309 */           if (Trace.isOn) {
/*  310 */             Trace.traceData(this, "(String) Altered Name = " + name, null);
/*      */           }
/*      */         } 
/*  313 */         if (Trace.isOn) {
/*  314 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_handleIllegalCharacters(String)", name, 1);
/*      */         }
/*      */         
/*  317 */         return name;
/*      */       } 
/*      */ 
/*      */       
/*  321 */       if (Trace.isOn) {
/*  322 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_handleIllegalCharacters(String)", propertyName, 2);
/*      */       }
/*      */       
/*  325 */       return propertyName;
/*      */     }
/*  327 */     catch (Exception ex) {
/*  328 */       if (Trace.isOn) {
/*  329 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_handleIllegalCharacters(String)", ex);
/*      */       }
/*      */       
/*  332 */       JMSException jmsEx = (JMSException)NLSServices.createException("JMSCMQ1050", null);
/*  333 */       jmsEx.setLinkedException(ex);
/*  334 */       if (Trace.isOn) {
/*  335 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_handleIllegalCharacters(String)", (Throwable)jmsEx);
/*      */       }
/*      */       
/*  338 */       throw jmsEx;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _importBody(byte[] input, int startIndex, int endIndex, int encoding, JmqiCodepage codepage) throws JMSException {
/*  355 */     if (Trace.isOn) {
/*  356 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)", new Object[] { input, 
/*      */             
/*  358 */             Integer.valueOf(startIndex), Integer.valueOf(endIndex), Integer.valueOf(encoding), codepage });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  363 */     this.mapBody.clear();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  368 */     String body = WMQUtils.computeTextFromBytes(input, startIndex, endIndex, codepage);
/*      */ 
/*      */ 
/*      */     
/*  372 */     parseMapBody(body);
/*      */     
/*  374 */     if (Trace.isOn) {
/*  375 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBody() throws JMSException {
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "clearBody()");
/*      */     }
/*      */ 
/*      */     
/*  393 */     this.mapBody.clear();
/*      */     
/*  395 */     if (Trace.isOn) {
/*  396 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "clearBody()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void eltFormatElement(String name, Object valueP, StringBuilder buffer) throws JMSException {
/*  413 */     if (Trace.isOn) {
/*  414 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "eltFormatElement(String,Object,StringBuffer)", new Object[] { name, valueP, buffer });
/*      */     }
/*      */ 
/*      */     
/*  418 */     Object value = valueP;
/*      */     
/*  420 */     boolean written = false;
/*  421 */     buffer.append("<");
/*  422 */     if (this.mapNameStyle) {
/*      */       
/*  424 */       buffer.append("elt name=\"" + name + "\"");
/*      */     }
/*      */     else {
/*      */       
/*  428 */       buffer.append(name);
/*      */     } 
/*      */     
/*  431 */     if (value instanceof String) {
/*  432 */       buffer.append(">");
/*  433 */       WMQMessageUtils.backReference(buffer, (String)value);
/*  434 */       written = true;
/*      */     }
/*  436 */     else if (value instanceof Integer) {
/*  437 */       buffer.append(" dt='i4'>");
/*      */     }
/*  439 */     else if (value instanceof Short) {
/*  440 */       buffer.append(" dt='i2'>");
/*      */     }
/*  442 */     else if (value instanceof Byte) {
/*  443 */       buffer.append(" dt='i1'>");
/*      */     }
/*  445 */     else if (value instanceof Long) {
/*  446 */       buffer.append(" dt='i8'>");
/*      */     }
/*  448 */     else if (value instanceof Float) {
/*  449 */       buffer.append(" dt='r4'>");
/*      */     }
/*  451 */     else if (value instanceof Double) {
/*  452 */       buffer.append(" dt='r8'>");
/*      */     }
/*  454 */     else if (value instanceof byte[]) {
/*  455 */       buffer.append(" dt='bin.hex'>");
/*  456 */       WMQUtils.binToHex((byte[])value, 0, ((byte[])value).length, buffer);
/*  457 */       written = true;
/*      */     }
/*  459 */     else if (value instanceof Boolean) {
/*  460 */       buffer.append(" dt='boolean'>");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  466 */       if (((Boolean)value).booleanValue() == true) {
/*  467 */         value = "1";
/*      */       } else {
/*      */         
/*  470 */         value = "0";
/*      */       }
/*      */     
/*  473 */     } else if (value instanceof Character) {
/*  474 */       buffer.append(" dt='char'>");
/*  475 */       WMQMessageUtils.backReference(buffer, ((Character)value).toString());
/*      */ 
/*      */ 
/*      */       
/*  479 */       written = true;
/*      */     }
/*  481 */     else if (value == null) {
/*  482 */       buffer.append(" xsi:nil='true'>");
/*  483 */       buffer.append("</");
/*  484 */       if (this.mapNameStyle) {
/*  485 */         buffer.append("elt");
/*      */       } else {
/*      */         
/*  488 */         buffer.append(name);
/*      */       } 
/*  490 */       buffer.append(">");
/*  491 */       if (Trace.isOn) {
/*  492 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "eltFormatElement(String,Object,StringBuffer)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  500 */     if (!written) {
/*  501 */       buffer.append(value.toString());
/*      */     }
/*      */ 
/*      */     
/*  505 */     buffer.append("</");
/*  506 */     if (this.mapNameStyle) {
/*      */       
/*  508 */       buffer.append("elt");
/*      */     }
/*      */     else {
/*      */       
/*  512 */       buffer.append(name);
/*      */     } 
/*  514 */     buffer.append(">");
/*  515 */     if (Trace.isOn) {
/*  516 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "eltFormatElement(String,Object,StringBuffer)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> getMapNames() throws JMSException {
/*  531 */     Enumeration<String> e = WMQUtils.enumerationFromIterable(this.mapBody.keySet());
/*  532 */     if (Trace.isOn) {
/*  533 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "getMapNames()", "getter", e);
/*      */     }
/*      */     
/*  536 */     return e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(String name) throws JMSException {
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "getObject(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  550 */     Object object = this.mapBody.get(name);
/*      */     
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "getObject(String)", object);
/*      */     }
/*      */     
/*  556 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean itemExists(String name) throws JMSException {
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "itemExists(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  571 */     boolean itemExists = this.mapBody.containsKey(name);
/*      */     
/*  573 */     if (Trace.isOn) {
/*  574 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "itemExists(String)", 
/*  575 */           Boolean.valueOf(itemExists));
/*      */     }
/*  577 */     return itemExists;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void parseMapBody(String mapBody) throws JMSException {
/*  588 */     if (Trace.isOn) {
/*  589 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "parseMapBody(String)", new Object[] { mapBody });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  601 */     Tokenizer strtok = new Tokenizer(mapBody);
/*      */ 
/*      */     
/*  604 */     if (!strtok.nextToken().equals("map")) {
/*  605 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1047", null);
/*  606 */       if (Trace.isOn) {
/*  607 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "parseMapBody(String)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  610 */       throw je;
/*      */     } 
/*      */     
/*  613 */     String name = strtok.nextToken();
/*      */ 
/*      */     
/*  616 */     while (!name.equals("/map")) {
/*      */       
/*  618 */       if (name.length() > 3 && name.substring(0, 3).equals("elt")) {
/*  619 */         name = _handleIllegalCharacters(name);
/*      */       }
/*      */ 
/*      */       
/*  623 */       int nullIndex = 0;
/*  624 */       boolean newNull = false;
/*      */       try {
/*  626 */         nullIndex = name.indexOf(" xsi:nil");
/*  627 */         newNull = (nullIndex != -1);
/*      */       }
/*  629 */       catch (Exception e) {
/*  630 */         if (Trace.isOn) {
/*  631 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "parseMapBody(String)", e);
/*      */         }
/*      */ 
/*      */         
/*  635 */         newNull = false;
/*      */       } 
/*      */       
/*  638 */       boolean shortForm = false;
/*  639 */       if (name.charAt(name.length() - 1) == '/') {
/*  640 */         shortForm = true;
/*      */       }
/*  642 */       if (newNull) {
/*      */ 
/*      */         
/*  645 */         int spaceIndex = 0;
/*  646 */         spaceIndex = name.indexOf(" ");
/*  647 */         String type = "'string'";
/*  648 */         name = name.substring(0, spaceIndex);
/*      */         
/*  650 */         if (!shortForm) {
/*      */           
/*  652 */           String token = strtok.nextToken();
/*  653 */           if (token.charAt(0) != '/')
/*      */           {
/*  655 */             token = strtok.nextToken();
/*      */           }
/*  657 */           if (token.charAt(0) != '/') {
/*      */             
/*  659 */             JMSException je2 = (JMSException)NLSServices.createException("JMSCMQ1050", null);
/*  660 */             if (Trace.isOn) {
/*  661 */               Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "parseMapBody(String)", (Throwable)je2, 2);
/*      */             }
/*      */ 
/*      */             
/*  665 */             throw je2;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  670 */         setObject(name, WMQMessageUtils.deformatElement(type, null));
/*      */       } else {
/*      */         String type;
/*      */ 
/*      */         
/*  675 */         int index = name.indexOf(" dt=");
/*  676 */         if (index != -1) {
/*  677 */           type = name.substring(index + 4);
/*  678 */           name = name.substring(0, index);
/*  679 */           if (shortForm) {
/*  680 */             type = type.substring(0, type.length() - 1);
/*      */           }
/*      */         } else {
/*      */           
/*  684 */           type = "'string'";
/*  685 */           if (shortForm) {
/*  686 */             name = name.substring(0, name.length() - 1);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  691 */         if (shortForm) {
/*      */           
/*  693 */           setObject(name, WMQMessageUtils.deformatElement(type, ""));
/*      */         }
/*      */         else {
/*      */           
/*  697 */           String value = strtok.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  704 */           if (value.charAt(0) == '/' && (value.equals("/elt") || name.equals(value.substring(1, value.length())))) {
/*      */ 
/*      */             
/*  707 */             setObject(name, WMQMessageUtils.deformatElement(type, ""));
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  712 */             setObject(name, WMQMessageUtils.deformatElement(type, value));
/*      */ 
/*      */             
/*  715 */             String token = strtok.nextToken();
/*  716 */             if (token.charAt(0) != '/') {
/*      */               
/*  718 */               JMSException je3 = (JMSException)NLSServices.createException("JMSCMQ1047", null);
/*  719 */               if (Trace.isOn) {
/*  720 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "parseMapBody(String)", (Throwable)je3, 3);
/*      */               }
/*      */ 
/*      */               
/*  724 */               throw je3;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  731 */       name = strtok.nextToken();
/*      */     } 
/*  733 */     if (Trace.isOn) {
/*  734 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "parseMapBody(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(String name, boolean value) throws JMSException {
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setBoolean(String,boolean)", new Object[] { name, 
/*  748 */             Boolean.valueOf(value) });
/*      */     }
/*      */     
/*  751 */     if (!this.mapNameStyle) {
/*  752 */       checkMapNameStyle(name);
/*      */     }
/*  754 */     this.mapBody.put(name, Boolean.valueOf(value));
/*      */     
/*  756 */     if (Trace.isOn) {
/*  757 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setBoolean(String,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByte(String name, byte value) throws JMSException {
/*  770 */     if (Trace.isOn) {
/*  771 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setByte(String,byte)", new Object[] { name, 
/*  772 */             Byte.valueOf(value) });
/*      */     }
/*      */     
/*  775 */     if (!this.mapNameStyle) {
/*  776 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  779 */     this.mapBody.put(name, Byte.valueOf(value));
/*      */     
/*  781 */     if (Trace.isOn) {
/*  782 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setByte(String,byte)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytes(String name, byte[] value) throws JMSException {
/*  795 */     if (Trace.isOn) {
/*  796 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setBytes(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*  800 */     if (!this.mapNameStyle) {
/*  801 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  804 */     this.mapBody.put(name, value);
/*      */     
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setBytes(String,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setBytes(String,byte [ ],int,int)", new Object[] { name, value, 
/*  823 */             Integer.valueOf(offset), 
/*  824 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/*  827 */     if (!this.mapNameStyle) {
/*  828 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  831 */     byte[] bytes = new byte[length];
/*  832 */     System.arraycopy(value, offset, bytes, 0, length);
/*  833 */     this.mapBody.put(name, bytes);
/*      */     
/*  835 */     if (Trace.isOn) {
/*  836 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setBytes(String,byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChar(String name, char value) throws JMSException {
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setChar(String,char)", new Object[] { name, 
/*  851 */             Character.valueOf(value) });
/*      */     }
/*      */     
/*  854 */     if (!this.mapNameStyle) {
/*  855 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  858 */     this.mapBody.put(name, Character.valueOf(value));
/*      */     
/*  860 */     if (Trace.isOn) {
/*  861 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setChar(String,char)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDouble(String name, double value) throws JMSException {
/*  874 */     if (Trace.isOn) {
/*  875 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setDouble(String,double)", new Object[] { name, 
/*  876 */             Double.valueOf(value) });
/*      */     }
/*      */     
/*  879 */     if (!this.mapNameStyle) {
/*  880 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  883 */     this.mapBody.put(name, Double.valueOf(value));
/*      */     
/*  885 */     if (Trace.isOn) {
/*  886 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setDouble(String,double)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(String name, float value) throws JMSException {
/*  899 */     if (Trace.isOn) {
/*  900 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setFloat(String,float)", new Object[] { name, 
/*  901 */             Float.valueOf(value) });
/*      */     }
/*      */     
/*  904 */     if (!this.mapNameStyle) {
/*  905 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  908 */     this.mapBody.put(name, Float.valueOf(value));
/*      */     
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setFloat(String,float)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInt(String name, int value) throws JMSException {
/*  924 */     if (Trace.isOn) {
/*  925 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setInt(String,int)", new Object[] { name, 
/*  926 */             Integer.valueOf(value) });
/*      */     }
/*      */     
/*  929 */     if (!this.mapNameStyle) {
/*  930 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  933 */     this.mapBody.put(name, Integer.valueOf(value));
/*      */     
/*  935 */     if (Trace.isOn) {
/*  936 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setInt(String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(String name, long value) throws JMSException {
/*  949 */     if (Trace.isOn) {
/*  950 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setLong(String,long)", new Object[] { name, 
/*  951 */             Long.valueOf(value) });
/*      */     }
/*      */     
/*  954 */     if (!this.mapNameStyle) {
/*  955 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  958 */     this.mapBody.put(name, Long.valueOf(value));
/*      */     
/*  960 */     if (Trace.isOn) {
/*  961 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setLong(String,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(String name, Object value) throws JMSException {
/*  978 */     if (Trace.isOn) {
/*  979 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setObject(String,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*  983 */     if (!this.mapNameStyle) {
/*  984 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/*  987 */     this.mapBody.put(name, value);
/*      */     
/*  989 */     if (Trace.isOn) {
/*  990 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setObject(String,Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShort(String name, short value) throws JMSException {
/* 1003 */     if (Trace.isOn) {
/* 1004 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setShort(String,short)", new Object[] { name, 
/* 1005 */             Short.valueOf(value) });
/*      */     }
/*      */     
/* 1008 */     if (!this.mapNameStyle) {
/* 1009 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/* 1012 */     this.mapBody.put(name, Short.valueOf(value));
/*      */     
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setShort(String,short)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setString(String name, String value) throws JMSException {
/* 1029 */     if (Trace.isOn) {
/* 1030 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setString(String,String)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/* 1034 */     if (!this.mapNameStyle) {
/* 1035 */       checkMapNameStyle(name);
/*      */     }
/*      */     
/* 1038 */     this.mapBody.put(name, value);
/*      */     
/* 1040 */     if (Trace.isOn) {
/* 1041 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setString(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isMapNameStyle() {
/* 1054 */     if (Trace.isOn) {
/* 1055 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "isMapNameStyle()", "getter", 
/* 1056 */           Boolean.valueOf(this.mapNameStyle));
/*      */     }
/* 1058 */     return this.mapNameStyle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMapNameStyle(boolean mapNameStyle) {
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "setMapNameStyle(boolean)", "setter", 
/* 1069 */           Boolean.valueOf(mapNameStyle));
/*      */     }
/* 1071 */     this.mapNameStyle = mapNameStyle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkMapNameStyle(String name) throws JMSException {
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "checkMapNameStyle(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1091 */     boolean valid = true;
/*      */ 
/*      */ 
/*      */     
/* 1095 */     for (int i = 0; i < name.length(); i++) {
/* 1096 */       char ch = name.charAt(i);
/* 1097 */       if (i == 0) {
/* 1098 */         if (!Character.isJavaIdentifierStart(ch)) {
/* 1099 */           valid = false;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/* 1104 */       } else if (!Character.isJavaIdentifierPart(ch)) {
/* 1105 */         valid = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1110 */     if (!valid) {
/* 1111 */       HashMap<String, String> inserts = new HashMap<>();
/* 1112 */       inserts.put("XMSC_INSERT_PROPERTY", name);
/* 1113 */       JMSException jmsEx = (JMSException)NLSServices.createException("JMSCMQ1066", inserts);
/* 1114 */       if (Trace.isOn) {
/* 1115 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "checkMapNameStyle(String)", (Throwable)jmsEx);
/*      */       }
/*      */       
/* 1118 */       throw jmsEx;
/*      */     } 
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "checkMapNameStyle(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<?, ?> getMap(boolean deepClone) throws JMSException {
/* 1129 */     if (Trace.isOn)
/* 1130 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "getMap(boolean)", new Object[] {
/* 1131 */             Boolean.valueOf(deepClone)
/*      */           }); 
/* 1133 */     if (Trace.isOn) {
/* 1134 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "getMap(boolean)", this.mapBody);
/*      */     }
/*      */     
/* 1137 */     return this.mapBody;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasBody() {
/* 1142 */     if (Trace.isOn) {
/* 1143 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "hasBody()");
/*      */     }
/*      */     
/* 1146 */     boolean traceRet1 = !this.mapBody.isEmpty();
/* 1147 */     if (Trace.isOn) {
/* 1148 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage", "hasBody()", 
/* 1149 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1151 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMapMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */