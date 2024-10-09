/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.nio.charset.CharacterCodingException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JMSMapMessage
/*      */   extends JMSMessage
/*      */   implements ProviderMapMessage
/*      */ {
/*      */   static final long serialVersionUID = 3908796707964271920L;
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSMapMessage.java";
/*      */   
/*      */   static {
/*  124 */     if (Trace.isOn) {
/*  125 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSMapMessage.java");
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
/*  137 */   private Map<String, Object> mapdata = Collections.synchronizedMap(new HashMap<>());
/*      */ 
/*      */ 
/*      */   
/*      */   boolean readOnly = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean inExportBody = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mapNameStyle = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSMapMessage(JMSStringResources jmsStrings) throws JMSException {
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "<init>(JMSStringResources)", new Object[] { jmsStrings });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  161 */     this.messageClass = "jms_map";
/*      */ 
/*      */     
/*  164 */     this.jmsStrings = jmsStrings;
/*      */     
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "<init>(JMSStringResources)");
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
/*      */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/*  182 */     if (Trace.isOn) {
/*  183 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/*  184 */             Integer.valueOf(encoding), codepage
/*      */           });
/*      */     }
/*  187 */     this.inExportBody = true;
/*  188 */     StringBuffer body = new StringBuffer(100);
/*      */     
/*      */     try {
/*  191 */       body.append("<map>");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  198 */       for (Map.Entry<String, Object> entry : this.mapdata.entrySet()) {
/*  199 */         eltFormatElement(entry.getKey(), entry.getValue(), body);
/*      */       }
/*      */       
/*  202 */       body.append("</map>");
/*  203 */       this.inExportBody = false;
/*  204 */       byte[] traceRet1 = codepage.stringToBytes(body.toString());
/*  205 */       if (Trace.isOn) {
/*  206 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_exportBody(int,JmqiCodepage)", traceRet1);
/*      */       }
/*      */       
/*  209 */       return traceRet1;
/*      */     }
/*  211 */     catch (CharacterCodingException ex) {
/*  212 */       if (Trace.isOn) {
/*  213 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_exportBody(int,JmqiCodepage)", ex);
/*      */       }
/*      */       
/*  216 */       JMSException jmsEx = newJMSException(1008, codepage);
/*  217 */       jmsEx.setLinkedException(ex);
/*      */       
/*  219 */       if (Trace.isOn) {
/*  220 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_exportBody(int,JmqiCodepage)", (Throwable)jmsEx);
/*      */       }
/*      */       
/*  223 */       throw jmsEx;
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
/*      */   public void _importBody(byte[] wireformat, int dataStart, int encoding, JmqiCodepage codepage) throws JMSException {
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", new Object[] { wireformat, 
/*      */             
/*  240 */             Integer.valueOf(dataStart), Integer.valueOf(encoding), codepage });
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
/*      */     try {
/*  263 */       String body = codepage.bytesToString(wireformat, dataStart, wireformat.length - dataStart);
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
/*  274 */       Tokenizer strtok = new Tokenizer(body);
/*      */ 
/*      */       
/*  277 */       if (!strtok.nextToken().equals("map")) {
/*  278 */         JMSException traceRet1 = newMessageFormatException(1009);
/*  279 */         if (Trace.isOn) {
/*  280 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  283 */         throw traceRet1;
/*      */       } 
/*      */       
/*  286 */       String name = strtok.nextToken();
/*      */ 
/*      */       
/*  289 */       while (!name.equals("/map")) {
/*      */         
/*  291 */         if (name.length() > 3 && name.substring(0, 3).equals("elt")) {
/*  292 */           name = _handleIllegalCharacters(name);
/*      */         }
/*      */ 
/*      */         
/*  296 */         int nullIndex = 0;
/*  297 */         boolean newNull = false;
/*      */         try {
/*  299 */           nullIndex = name.indexOf(" xsi:nil");
/*  300 */           newNull = (nullIndex != -1);
/*      */         }
/*  302 */         catch (Exception e) {
/*  303 */           if (Trace.isOn) {
/*  304 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", e, 1);
/*      */           }
/*      */ 
/*      */           
/*  308 */           newNull = false;
/*      */         } 
/*      */         
/*  311 */         boolean shortForm = false;
/*      */         
/*  313 */         if (name.charAt(name.length() - 1) == '/') {
/*  314 */           shortForm = true;
/*      */         }
/*  316 */         if (newNull) {
/*      */ 
/*      */           
/*  319 */           int spaceIndex = 0;
/*  320 */           spaceIndex = name.indexOf(" ");
/*  321 */           String type = "'string'";
/*  322 */           name = name.substring(0, spaceIndex);
/*      */           
/*  324 */           if (!shortForm) {
/*      */             
/*  326 */             String token = strtok.nextToken();
/*  327 */             if (token.charAt(0) != '/')
/*      */             {
/*  329 */               token = strtok.nextToken();
/*      */             }
/*  331 */             if (token.charAt(0) != '/') {
/*      */               
/*  333 */               JMSException traceRet2 = newMessageFormatException(1012);
/*  334 */               if (Trace.isOn) {
/*  335 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)traceRet2, 2);
/*      */               }
/*      */               
/*  338 */               throw traceRet2;
/*      */             } 
/*      */           } 
/*  341 */           setObject(name, deformatElement(type, null));
/*      */         } else {
/*      */           String type;
/*      */ 
/*      */           
/*  346 */           int index = name.indexOf(" dt=");
/*  347 */           if (index != -1) {
/*  348 */             type = name.substring(index + 4);
/*  349 */             name = name.substring(0, index);
/*  350 */             if (shortForm) {
/*  351 */               type = type.substring(0, type.length() - 1);
/*      */             }
/*      */           } else {
/*      */             
/*  355 */             type = "'string'";
/*  356 */             if (shortForm) {
/*  357 */               name = name.substring(0, name.length() - 1);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  362 */           if (shortForm) {
/*      */             
/*  364 */             setObject(name, deformatElement(type, ""));
/*      */           }
/*      */           else {
/*      */             
/*  368 */             String value = strtok.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  375 */             if (value.charAt(0) == '/' && (value.equals("/elt") || name.equals(value.substring(1, value.length())))) {
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
/*  390 */               setObject(name, deformatElement(type, ""));
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  395 */               setObject(name, deformatElement(type, value));
/*      */ 
/*      */               
/*  398 */               String token = strtok.nextToken();
/*  399 */               if (token.charAt(0) != '/') {
/*      */                 
/*  401 */                 JMSException traceRet3 = newMessageFormatException(1009);
/*  402 */                 if (Trace.isOn) {
/*  403 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)traceRet3, 3);
/*      */                 }
/*      */                 
/*  406 */                 throw traceRet3;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  413 */         name = strtok.nextToken();
/*      */       } 
/*  415 */       this.readOnly = true;
/*      */     }
/*  417 */     catch (CharacterCodingException ex) {
/*  418 */       if (Trace.isOn) {
/*  419 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", ex, 2);
/*      */       }
/*      */       
/*  422 */       JMSException jmsEx = newJMSException(1008, codepage);
/*  423 */       jmsEx.setLinkedException(ex);
/*  424 */       if (Trace.isOn) {
/*  425 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)jmsEx, 4);
/*      */       }
/*      */       
/*  428 */       throw jmsEx;
/*      */     }
/*  430 */     catch (Exception ex) {
/*  431 */       if (Trace.isOn) {
/*  432 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", ex, 3);
/*      */       }
/*      */       
/*  435 */       JMSException jmsEx = newMessageFormatException(1009);
/*  436 */       jmsEx.setLinkedException(ex);
/*  437 */       if (Trace.isOn) {
/*  438 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)jmsEx, 5);
/*      */       }
/*      */       
/*  441 */       throw jmsEx;
/*      */     } 
/*  443 */     if (Trace.isOn) {
/*  444 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_importBody(byte [ ],int,int,JmqiCodepage)");
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
/*  457 */     if (Trace.isOn) {
/*  458 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "clearBody()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  463 */     this.readOnly = false;
/*      */ 
/*      */     
/*  466 */     this.mapdata.clear();
/*      */     
/*  468 */     if (Trace.isOn) {
/*  469 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "clearBody()");
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
/*      */   public boolean getBoolean(String name) throws JMSException {
/*  484 */     if (Trace.isOn) {
/*  485 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getBoolean(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  489 */     if (name == null) {
/*  490 */       JMSException traceRet1 = newJMSException(1002, null);
/*  491 */       if (Trace.isOn) {
/*  492 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getBoolean(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  495 */       throw traceRet1;
/*      */     } 
/*      */     
/*  498 */     boolean traceRet2 = toBoolean(this.mapdata.get(name));
/*  499 */     if (Trace.isOn) {
/*  500 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getBoolean(String)", 
/*  501 */           Boolean.valueOf(traceRet2));
/*      */     }
/*  503 */     return traceRet2;
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
/*      */   public byte getByte(String name) throws JMSException {
/*  517 */     if (Trace.isOn) {
/*  518 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getByte(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  522 */     if (name == null) {
/*  523 */       JMSException traceRet1 = newJMSException(1002, null);
/*  524 */       if (Trace.isOn) {
/*  525 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getByte(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  528 */       throw traceRet1;
/*      */     } 
/*      */     
/*  531 */     byte traceRet2 = toByte(this.mapdata.get(name));
/*  532 */     if (Trace.isOn) {
/*  533 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getByte(String)", 
/*  534 */           Byte.valueOf(traceRet2));
/*      */     }
/*  536 */     return traceRet2;
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
/*      */   public byte[] getBytes(String name) throws JMSException {
/*  550 */     if (Trace.isOn) {
/*  551 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getBytes(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  555 */     if (name == null) {
/*  556 */       JMSException traceRet1 = newJMSException(1002, null);
/*  557 */       if (Trace.isOn) {
/*  558 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getBytes(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  561 */       throw traceRet1;
/*      */     } 
/*      */     
/*  564 */     byte[] traceRet2 = toBytes(this.mapdata.get(name));
/*  565 */     if (Trace.isOn) {
/*  566 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getBytes(String)", traceRet2);
/*      */     }
/*      */     
/*  569 */     return traceRet2;
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
/*      */   public char getChar(String name) throws JMSException {
/*  583 */     if (Trace.isOn) {
/*  584 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getChar(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  588 */     if (name == null) {
/*  589 */       JMSException traceRet1 = newJMSException(1002, null);
/*  590 */       if (Trace.isOn) {
/*  591 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getChar(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  594 */       throw traceRet1;
/*      */     } 
/*      */     
/*  597 */     char traceRet2 = toChar(this.mapdata.get(name));
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getChar(String)", 
/*  600 */           Character.valueOf(traceRet2));
/*      */     }
/*  602 */     return traceRet2;
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
/*      */   public double getDouble(String name) throws JMSException {
/*  616 */     if (Trace.isOn) {
/*  617 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getDouble(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  621 */     if (name == null) {
/*  622 */       JMSException traceRet1 = newJMSException(1002, null);
/*  623 */       if (Trace.isOn) {
/*  624 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getDouble(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  627 */       throw traceRet1;
/*      */     } 
/*      */     
/*  630 */     double traceRet2 = toDouble(this.mapdata.get(name));
/*  631 */     if (Trace.isOn) {
/*  632 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getDouble(String)", 
/*  633 */           Double.valueOf(traceRet2));
/*      */     }
/*  635 */     return traceRet2;
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
/*      */   public float getFloat(String name) throws JMSException {
/*  649 */     if (Trace.isOn) {
/*  650 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getFloat(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  654 */     if (name == null) {
/*  655 */       JMSException traceRet1 = newJMSException(1002, null);
/*  656 */       if (Trace.isOn) {
/*  657 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getFloat(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  660 */       throw traceRet1;
/*      */     } 
/*      */     
/*  663 */     float traceRet2 = toFloat(this.mapdata.get(name));
/*  664 */     if (Trace.isOn) {
/*  665 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getFloat(String)", 
/*  666 */           Float.valueOf(traceRet2));
/*      */     }
/*  668 */     return traceRet2;
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
/*      */   public int getInt(String name) throws JMSException {
/*  682 */     if (Trace.isOn) {
/*  683 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getInt(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  687 */     if (name == null) {
/*  688 */       JMSException traceRet1 = newJMSException(1002, null);
/*  689 */       if (Trace.isOn) {
/*  690 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getInt(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  693 */       throw traceRet1;
/*      */     } 
/*      */     
/*  696 */     int traceRet2 = toInt(this.mapdata.get(name));
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getInt(String)", 
/*  699 */           Integer.valueOf(traceRet2));
/*      */     }
/*  701 */     return traceRet2;
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
/*      */   public long getLong(String name) throws JMSException {
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getLong(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  720 */     if (name == null) {
/*  721 */       JMSException traceRet1 = newJMSException(1002, null);
/*  722 */       if (Trace.isOn) {
/*  723 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getLong(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  726 */       throw traceRet1;
/*      */     } 
/*      */     
/*  729 */     long traceRet2 = toLong(this.mapdata.get(name));
/*  730 */     if (Trace.isOn) {
/*  731 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getLong(String)", 
/*  732 */           Long.valueOf(traceRet2));
/*      */     }
/*  734 */     return traceRet2;
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
/*      */   public Enumeration<String> getMapNames() throws JMSException {
/*  746 */     Enumeration<String> traceRet1 = WMQUtils.enumerationFromIterable(this.mapdata.keySet());
/*  747 */     if (Trace.isOn) {
/*  748 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getMapNames()", "getter", traceRet1);
/*      */     }
/*      */     
/*  751 */     return traceRet1;
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
/*      */   public Object getObject(String name) throws JMSException {
/*  768 */     if (Trace.isOn) {
/*  769 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getObject(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  775 */     if (name == null) {
/*  776 */       JMSException traceRet1 = newJMSException(1002, null);
/*  777 */       if (Trace.isOn) {
/*  778 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getObject(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  781 */       throw traceRet1;
/*      */     } 
/*      */     
/*  784 */     Object retval = this.mapdata.get(name);
/*      */ 
/*      */ 
/*      */     
/*  788 */     if (retval == null) {
/*  789 */       if (Trace.isOn) {
/*  790 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getObject(String)", null, 1);
/*      */       }
/*      */       
/*  793 */       return null;
/*      */     } 
/*      */     
/*  796 */     if (Trace.isOn) {
/*  797 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getObject(String)", retval, 2);
/*      */     }
/*      */     
/*  800 */     return retval;
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
/*      */   public short getShort(String name) throws JMSException {
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getShort(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  818 */     if (name == null) {
/*  819 */       JMSException traceRet1 = newJMSException(1002, null);
/*  820 */       if (Trace.isOn) {
/*  821 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getShort(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  824 */       throw traceRet1;
/*      */     } 
/*      */     
/*  827 */     short traceRet2 = toShort(this.mapdata.get(name));
/*  828 */     if (Trace.isOn) {
/*  829 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getShort(String)", 
/*  830 */           Short.valueOf(traceRet2));
/*      */     }
/*  832 */     return traceRet2;
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
/*      */   public String getString(String name) throws JMSException {
/*  846 */     if (Trace.isOn) {
/*  847 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getString(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  852 */     if (name == null) {
/*  853 */       JMSException traceRet1 = newJMSException(1002, null);
/*  854 */       if (Trace.isOn) {
/*  855 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getString(String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  858 */       throw traceRet1;
/*      */     } 
/*      */     
/*  861 */     Object retval = this.mapdata.get(name);
/*      */ 
/*      */     
/*  864 */     if (retval == null) {
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getString(String)", null, 1);
/*      */       }
/*      */       
/*  869 */       return null;
/*      */     } 
/*      */     
/*  872 */     if (retval instanceof byte[]) {
/*  873 */       JMSException traceRet2 = newMessageFormatException(1011);
/*  874 */       if (Trace.isOn) {
/*  875 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getString(String)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  878 */       throw traceRet2;
/*      */     } 
/*  880 */     String traceRet3 = retval.toString();
/*  881 */     if (Trace.isOn) {
/*  882 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getString(String)", traceRet3, 2);
/*      */     }
/*      */     
/*  885 */     return traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isSettableMapName(String elementName) {
/*  896 */     if (Trace.isOn) {
/*  897 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "isSettableMapName(String)", new Object[] { elementName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  907 */     boolean reply = true;
/*      */ 
/*      */ 
/*      */     
/*  911 */     for (int i = 0; i < elementName.length(); i++) {
/*  912 */       char ch = elementName.charAt(i);
/*  913 */       if (i == 0) {
/*  914 */         if (!Character.isJavaIdentifierStart(ch)) {
/*  915 */           reply = false;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*  920 */       } else if (!Character.isJavaIdentifierPart(ch)) {
/*  921 */         reply = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  926 */     if (Trace.isOn) {
/*  927 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "isSettableMapName(String)", 
/*  928 */           Boolean.valueOf(reply));
/*      */     }
/*  930 */     return reply;
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
/*      */   public boolean itemExists(String name) throws JMSException {
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "itemExists(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  948 */     if (name == null) {
/*  949 */       JMSException traceRet1 = newJMSException(1003);
/*  950 */       if (Trace.isOn) {
/*  951 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "itemExists(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  954 */       throw traceRet1;
/*      */     } 
/*      */     
/*  957 */     boolean retval = this.mapdata.containsKey(name);
/*      */     
/*  959 */     if (Trace.isOn) {
/*  960 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "itemExists(String)", 
/*  961 */           Boolean.valueOf(retval));
/*      */     }
/*  963 */     return retval;
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
/*      */   public void setBoolean(String name, boolean value) throws JMSException {
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBoolean(String,boolean)", new Object[] { name, 
/*  978 */             Boolean.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/*  982 */     if (this.readOnly == true) {
/*  983 */       JMSException traceRet1 = newMessageNotWriteableException();
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBoolean(String,boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  988 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  992 */     if (name == null || name.trim().length() == 0) {
/*  993 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/*  994 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/*  995 */       if (Trace.isOn) {
/*  996 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBoolean(String,boolean)", traceRet2, 2);
/*      */       }
/*      */       
/*  999 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1002 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1003 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1004 */       if (Trace.isOn) {
/* 1005 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBoolean(String,boolean)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1008 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1011 */     this.mapdata.put(name, Boolean.valueOf(value));
/*      */     
/* 1013 */     if (Trace.isOn) {
/* 1014 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBoolean(String,boolean)");
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
/*      */   public void setByte(String name, byte value) throws JMSException {
/* 1030 */     if (Trace.isOn) {
/* 1031 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setByte(String,byte)", new Object[] { name, 
/* 1032 */             Byte.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1036 */     if (this.readOnly == true) {
/* 1037 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1038 */       if (Trace.isOn) {
/* 1039 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setByte(String,byte)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1042 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1046 */     if (name == null || name.trim().length() == 0) {
/* 1047 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1048 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1049 */       if (Trace.isOn) {
/* 1050 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setByte(String,byte)", traceRet2, 2);
/*      */       }
/*      */       
/* 1053 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1056 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1057 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1058 */       if (Trace.isOn) {
/* 1059 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setByte(String,byte)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1062 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1065 */     this.mapdata.put(name, Byte.valueOf(value));
/*      */     
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setByte(String,byte)");
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
/*      */   public void setBytes(String name, byte[] value) throws JMSException {
/* 1084 */     if (Trace.isOn) {
/* 1085 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1090 */     if (value == null) {
/* 1091 */       setObject(name, (Object)null);
/*      */     }
/* 1093 */     else if (value.length == 0) {
/* 1094 */       setObject(name, value);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1100 */       setBytes(name, value, 0, value.length);
/*      */     } 
/* 1102 */     if (Trace.isOn) {
/* 1103 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ])");
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
/*      */   public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
/* 1121 */     if (Trace.isOn) {
/* 1122 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ],int,int)", new Object[] { name, value, 
/* 1123 */             Integer.valueOf(offset), 
/* 1124 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */     
/* 1128 */     if (this.readOnly == true) {
/* 1129 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1130 */       if (Trace.isOn) {
/* 1131 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ],int,int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1134 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1138 */     if (name == null || name.trim().length() == 0) {
/* 1139 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1140 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1141 */       if (Trace.isOn) {
/* 1142 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ],int,int)", traceRet2, 2);
/*      */       }
/*      */       
/* 1145 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1148 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1149 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1150 */       if (Trace.isOn) {
/* 1151 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ],int,int)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1154 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1157 */     if (value.length == 0) {
/* 1158 */       this.mapdata.put(name, new byte[0]);
/*      */     } else {
/*      */       
/* 1161 */       byte[] elementValue = new byte[length];
/* 1162 */       System.arraycopy(value, offset, elementValue, 0, length);
/* 1163 */       this.mapdata.put(name, elementValue);
/*      */     } 
/*      */     
/* 1166 */     if (Trace.isOn) {
/* 1167 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBytes(String,byte [ ],int,int)");
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
/*      */   public void setChar(String name, char value) throws JMSException {
/* 1183 */     if (Trace.isOn) {
/* 1184 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setChar(String,char)", new Object[] { name, 
/* 1185 */             Character.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1189 */     if (this.readOnly == true) {
/* 1190 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1191 */       if (Trace.isOn) {
/* 1192 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setChar(String,char)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1195 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1199 */     if (name == null || name.trim().length() == 0) {
/* 1200 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1201 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1202 */       if (Trace.isOn) {
/* 1203 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setChar(String,char)", traceRet2, 2);
/*      */       }
/*      */       
/* 1206 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1209 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1210 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1211 */       if (Trace.isOn) {
/* 1212 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setChar(String,char)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1215 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1218 */     this.mapdata.put(name, Character.valueOf(value));
/*      */     
/* 1220 */     if (Trace.isOn) {
/* 1221 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setChar(String,char)");
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
/*      */   public void setDouble(String name, double value) throws JMSException {
/* 1237 */     if (Trace.isOn) {
/* 1238 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setDouble(String,double)", new Object[] { name, 
/* 1239 */             Double.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1243 */     if (this.readOnly == true) {
/* 1244 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1245 */       if (Trace.isOn) {
/* 1246 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setDouble(String,double)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1249 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1253 */     if (name == null || name.trim().length() == 0) {
/* 1254 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1255 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1256 */       if (Trace.isOn) {
/* 1257 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setDouble(String,double)", traceRet2, 2);
/*      */       }
/*      */       
/* 1260 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1263 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1264 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1265 */       if (Trace.isOn) {
/* 1266 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setDouble(String,double)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1269 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1272 */     this.mapdata.put(name, Double.valueOf(value));
/*      */     
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setDouble(String,double)");
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
/*      */   public void setFloat(String name, float value) throws JMSException {
/* 1291 */     if (Trace.isOn) {
/* 1292 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setFloat(String,float)", new Object[] { name, 
/* 1293 */             Float.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1297 */     if (this.readOnly == true) {
/* 1298 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1299 */       if (Trace.isOn) {
/* 1300 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setFloat(String,float)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1303 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1307 */     if (name == null || name.trim().length() == 0) {
/* 1308 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1309 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1310 */       if (Trace.isOn) {
/* 1311 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setFloat(String,float)", traceRet2, 2);
/*      */       }
/*      */       
/* 1314 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1317 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1318 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1319 */       if (Trace.isOn) {
/* 1320 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setFloat(String,float)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1323 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1326 */     this.mapdata.put(name, Float.valueOf(value));
/*      */     
/* 1328 */     if (Trace.isOn) {
/* 1329 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setFloat(String,float)");
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
/*      */   public void setInt(String name, int value) throws JMSException {
/* 1345 */     if (Trace.isOn) {
/* 1346 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setInt(String,int)", new Object[] { name, 
/* 1347 */             Integer.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1351 */     if (this.readOnly == true) {
/* 1352 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1353 */       if (Trace.isOn) {
/* 1354 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setInt(String,int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1357 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1361 */     if (name == null || name.trim().length() == 0) {
/* 1362 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1363 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1364 */       if (Trace.isOn) {
/* 1365 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setInt(String,int)", traceRet2, 2);
/*      */       }
/*      */       
/* 1368 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1371 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1372 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1373 */       if (Trace.isOn) {
/* 1374 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setInt(String,int)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1377 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1380 */     this.mapdata.put(name, Integer.valueOf(value));
/*      */     
/* 1382 */     if (Trace.isOn) {
/* 1383 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setInt(String,int)");
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
/*      */   public void setLong(String name, long value) throws JMSException {
/* 1399 */     if (Trace.isOn) {
/* 1400 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setLong(String,long)", new Object[] { name, 
/* 1401 */             Long.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1405 */     if (this.readOnly == true) {
/* 1406 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1407 */       if (Trace.isOn) {
/* 1408 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setLong(String,long)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1411 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1415 */     if (name == null || name.trim().length() == 0) {
/* 1416 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1417 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1418 */       if (Trace.isOn) {
/* 1419 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setLong(String,long)", traceRet2, 2);
/*      */       }
/*      */       
/* 1422 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1425 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1426 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1427 */       if (Trace.isOn) {
/* 1428 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setLong(String,long)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1431 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1434 */     this.mapdata.put(name, Long.valueOf(value));
/*      */     
/* 1436 */     if (Trace.isOn) {
/* 1437 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setLong(String,long)");
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
/*      */   
/*      */   public void setObject(String name, Object value) throws JMSException {
/* 1457 */     if (Trace.isOn) {
/* 1458 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setObject(String,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1463 */     if (this.readOnly == true) {
/* 1464 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1465 */       if (Trace.isOn) {
/* 1466 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setObject(String,Object)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1469 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1473 */     if (name == null || name.trim().length() == 0) {
/* 1474 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1475 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1476 */       if (Trace.isOn) {
/* 1477 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setObject(String,Object)", traceRet2, 2);
/*      */       }
/*      */       
/* 1480 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1483 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1484 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1485 */       if (Trace.isOn) {
/* 1486 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setObject(String,Object)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1489 */       throw traceRet3;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1494 */     if (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Short) && !(value instanceof Byte) && !(value instanceof Long) && !(value instanceof Float) && !(value instanceof Double) && !(value instanceof Boolean) && !(value instanceof Character) && !(value instanceof byte[]) && value != null) {
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
/* 1506 */       JMSException traceRet4 = newMessageFormatException(1018, value.getClass());
/* 1507 */       if (Trace.isOn) {
/* 1508 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setObject(String,Object)", (Throwable)traceRet4, 4);
/*      */       }
/*      */       
/* 1511 */       throw traceRet4;
/*      */     } 
/*      */ 
/*      */     
/* 1515 */     this.mapdata.put(name, value);
/*      */     
/* 1517 */     if (Trace.isOn) {
/* 1518 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setObject(String,Object)");
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
/*      */   public void setShort(String name, short value) throws JMSException {
/* 1534 */     if (Trace.isOn) {
/* 1535 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setShort(String,short)", new Object[] { name, 
/* 1536 */             Short.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 1540 */     if (this.readOnly == true) {
/* 1541 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1542 */       if (Trace.isOn) {
/* 1543 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setShort(String,short)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1546 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1550 */     if (name == null || name.trim().length() == 0) {
/* 1551 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1552 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1553 */       if (Trace.isOn) {
/* 1554 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setShort(String,short)", traceRet2, 2);
/*      */       }
/*      */       
/* 1557 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1560 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1561 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1562 */       if (Trace.isOn) {
/* 1563 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setShort(String,short)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1566 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1569 */     this.mapdata.put(name, Short.valueOf(value));
/*      */     
/* 1571 */     if (Trace.isOn) {
/* 1572 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setShort(String,short)");
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
/*      */   public void setString(String name, String value) throws JMSException {
/* 1588 */     if (Trace.isOn) {
/* 1589 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setString(String,String)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1594 */     if (this.readOnly == true) {
/* 1595 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1596 */       if (Trace.isOn) {
/* 1597 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setString(String,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1600 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1604 */     if (name == null || name.trim().length() == 0) {
/* 1605 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 1606 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 1607 */       if (Trace.isOn) {
/* 1608 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setString(String,String)", traceRet2, 2);
/*      */       }
/*      */       
/* 1611 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1614 */     if (!this.mapNameStyle && !isSettableMapName(name)) {
/* 1615 */       JMSException traceRet3 = newMessageFormatException(1021, name);
/* 1616 */       if (Trace.isOn) {
/* 1617 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setString(String,String)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1620 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1623 */     this.mapdata.put(name, value);
/*      */     
/* 1625 */     if (Trace.isOn) {
/* 1626 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setString(String,String)");
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
/*      */   public String toString() {
/* 1640 */     if (Trace.isOn) {
/* 1641 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "toString()");
/*      */     }
/*      */     
/* 1644 */     StringBuffer retval = new StringBuffer();
/*      */ 
/*      */ 
/*      */     
/* 1648 */     retval.append(super.toString());
/* 1649 */     retval.append("\n");
/* 1650 */     retval.append(this.mapdata.toString());
/*      */     
/* 1652 */     String traceRet1 = retval.toString();
/* 1653 */     if (Trace.isOn) {
/* 1654 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "toString()", traceRet1);
/*      */     }
/*      */     
/* 1657 */     return traceRet1;
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
/*      */   public String _handleIllegalCharacters(String propertyName) throws JMSException {
/* 1674 */     if (Trace.isOn) {
/* 1675 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_handleIllegalCharacters(String)", new Object[] { propertyName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1681 */       if (propertyName.length() > 3 && propertyName.substring(0, 3).equals("elt")) {
/* 1682 */         String name = propertyName;
/* 1683 */         String realName = "";
/* 1684 */         String nameIntro = "";
/*      */ 
/*      */ 
/*      */         
/* 1688 */         boolean newNull = false;
/* 1689 */         int nullIndex = 0;
/* 1690 */         nullIndex = name.indexOf(" xsi:nil");
/*      */ 
/*      */         
/* 1693 */         if (nullIndex != -1) {
/* 1694 */           newNull = true;
/*      */         } else {
/*      */           
/* 1697 */           newNull = false;
/*      */         } 
/*      */ 
/*      */         
/* 1701 */         String nullbit = "";
/* 1702 */         if (newNull)
/*      */         {
/* 1704 */           nullbit = name.substring(nullIndex, nullIndex + 15);
/*      */         }
/*      */         
/* 1707 */         int endbit = name.indexOf(" dt=");
/* 1708 */         if (endbit != -1) {
/* 1709 */           nameIntro = name.substring(0, name.indexOf("name=") + 5);
/* 1710 */           int realNameStart = nameIntro.length() + 1;
/* 1711 */           int realNameEnd = name.indexOf("dt=") - 2;
/*      */ 
/*      */           
/* 1714 */           realName = name.substring(realNameStart, realNameEnd);
/*      */           
/* 1716 */           realName = realName + name.substring(endbit, name.length());
/* 1717 */           name = realName + nullbit;
/* 1718 */           if (Trace.isOn) {
/* 1719 */             Trace.traceData(this, "Altered Name = " + name, null);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 1724 */           nameIntro = name.substring(0, name.indexOf("name=") + 5);
/* 1725 */           int realNameStart = nameIntro.length() + 1;
/* 1726 */           if (newNull) {
/* 1727 */             realName = name.substring(realNameStart, name.indexOf("xsi:nil") - 2);
/*      */           } else {
/*      */             
/* 1730 */             realName = name.substring(realNameStart, name.length() - 1);
/*      */           } 
/*      */           
/* 1733 */           name = realName + nullbit;
/* 1734 */           if (Trace.isOn) {
/* 1735 */             Trace.traceData(this, "(String) Altered Name = " + name, null);
/*      */           }
/*      */         } 
/*      */         
/* 1739 */         if (Trace.isOn) {
/* 1740 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_handleIllegalCharacters(String)", name, 1);
/*      */         }
/*      */         
/* 1743 */         return name;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1748 */       if (Trace.isOn) {
/* 1749 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_handleIllegalCharacters(String)", propertyName, 2);
/*      */       }
/*      */       
/* 1752 */       return propertyName;
/*      */     }
/* 1754 */     catch (Exception ex) {
/* 1755 */       if (Trace.isOn) {
/* 1756 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_handleIllegalCharacters(String)", ex);
/*      */       }
/*      */       
/* 1759 */       JMSException jmsEx = newMessageFormatException(1012);
/* 1760 */       jmsEx.setLinkedException(ex);
/* 1761 */       if (Trace.isOn) {
/* 1762 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_handleIllegalCharacters(String)", (Throwable)jmsEx);
/*      */       }
/*      */       
/* 1765 */       throw jmsEx;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void _setBodyReadOnly() {
/* 1772 */     if (Trace.isOn) {
/* 1773 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_setBodyReadOnly()");
/*      */     }
/*      */     
/* 1776 */     this.readOnly = true;
/* 1777 */     if (Trace.isOn) {
/* 1778 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "_setBodyReadOnly()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getInExportBody() {
/* 1786 */     if (Trace.isOn) {
/* 1787 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getInExportBody()", "getter", 
/* 1788 */           Boolean.valueOf(this.inExportBody));
/*      */     }
/* 1790 */     return this.inExportBody;
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
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1803 */     if (Trace.isOn) {
/* 1804 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 1807 */     in.defaultReadObject();
/*      */ 
/*      */     
/* 1810 */     if (this.messageClass.equals("jms_map")) {
/* 1811 */       this.messageClass = "jms_map";
/*      */     }
/* 1813 */     if (Trace.isOn) {
/* 1814 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "readObject(java.io.ObjectInputStream)");
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
/*      */   void eltFormatElement(String name, Object valueP, StringBuffer buffer) throws JMSException {
/* 1831 */     if (Trace.isOn) {
/* 1832 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "eltFormatElement(String,Object,StringBuffer)", new Object[] { name, valueP, buffer });
/*      */     }
/*      */ 
/*      */     
/* 1836 */     Object value = valueP;
/*      */     
/* 1838 */     boolean written = false;
/* 1839 */     buffer.append("<");
/* 1840 */     if (this.mapNameStyle) {
/*      */       
/* 1842 */       buffer.append("elt name=\"" + name + "\"");
/*      */     }
/*      */     else {
/*      */       
/* 1846 */       buffer.append(name);
/*      */     } 
/*      */     
/* 1849 */     if (value instanceof String) {
/* 1850 */       buffer.append(">");
/* 1851 */       backReference(buffer, (String)value);
/* 1852 */       written = true;
/*      */     }
/* 1854 */     else if (value instanceof Integer) {
/* 1855 */       buffer.append(" dt='i4'>");
/*      */     }
/* 1857 */     else if (value instanceof Short) {
/* 1858 */       buffer.append(" dt='i2'>");
/*      */     }
/* 1860 */     else if (value instanceof Byte) {
/* 1861 */       buffer.append(" dt='i1'>");
/*      */     }
/* 1863 */     else if (value instanceof Long) {
/* 1864 */       buffer.append(" dt='i8'>");
/*      */     }
/* 1866 */     else if (value instanceof Float) {
/* 1867 */       buffer.append(" dt='r4'>");
/*      */     }
/* 1869 */     else if (value instanceof Double) {
/* 1870 */       buffer.append(" dt='r8'>");
/*      */     }
/* 1872 */     else if (value instanceof byte[]) {
/* 1873 */       buffer.append(" dt='bin.hex'>");
/* 1874 */       binToHex((byte[])value, 0, ((byte[])value).length, buffer);
/* 1875 */       written = true;
/*      */     }
/* 1877 */     else if (value instanceof Boolean) {
/* 1878 */       buffer.append(" dt='boolean'>");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1883 */       if (((Boolean)value).booleanValue() == true) {
/* 1884 */         value = "1";
/*      */       } else {
/*      */         
/* 1887 */         value = "0";
/*      */       }
/*      */     
/* 1890 */     } else if (value instanceof Character) {
/* 1891 */       buffer.append(" dt='char'>");
/* 1892 */       backReference(buffer, ((Character)value).toString());
/*      */       
/* 1894 */       written = true;
/*      */     }
/* 1896 */     else if (value == null) {
/*      */ 
/*      */ 
/*      */       
/* 1900 */       buffer.append(" xsi:nil='true'>");
/* 1901 */       buffer.append("</");
/* 1902 */       if (this.mapNameStyle) {
/* 1903 */         buffer.append("elt");
/*      */       } else {
/*      */         
/* 1906 */         buffer.append(name);
/*      */       } 
/* 1908 */       buffer.append(">");
/* 1909 */       if (Trace.isOn) {
/* 1910 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "eltFormatElement(String,Object,StringBuffer)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 1918 */     if (!written) {
/* 1919 */       buffer.append(value.toString());
/*      */     }
/*      */ 
/*      */     
/* 1923 */     buffer.append("</");
/* 1924 */     if (this.mapNameStyle) {
/*      */       
/* 1926 */       buffer.append("elt");
/*      */     }
/*      */     else {
/*      */       
/* 1930 */       buffer.append(name);
/*      */     } 
/* 1932 */     buffer.append(">");
/*      */     
/* 1934 */     if (Trace.isOn) {
/* 1935 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "eltFormatElement(String,Object,StringBuffer)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Tokenizer
/*      */   {
/* 1947 */     private Vector<String> tokens = new Vector<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Tokenizer(String string) {
/* 1955 */       if (Trace.isOn) {
/* 1956 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Tokenizer", "<init>(String)", new Object[] { string });
/*      */       }
/*      */       
/* 1959 */       int length = string.length();
/*      */       
/* 1961 */       boolean hide = false;
/*      */       
/* 1963 */       int tokenStart = 0;
/*      */       
/* 1965 */       boolean decorated = false;
/*      */       
/* 1967 */       for (int index = 0; index < length; index++) {
/* 1968 */         char ch = string.charAt(index);
/* 1969 */         if (ch == '"') {
/* 1970 */           hide = !hide;
/*      */         
/*      */         }
/* 1973 */         else if (!hide) {
/*      */ 
/*      */           
/* 1976 */           if (decorated) {
/* 1977 */             if (ch == '>') {
/* 1978 */               if (index > tokenStart) {
/* 1979 */                 String token = string.substring(tokenStart + 1, index);
/* 1980 */                 if (token.length() > 0) {
/* 1981 */                   this.tokens.add(token);
/*      */                 }
/*      */               } 
/* 1984 */               decorated = false;
/* 1985 */               tokenStart = index;
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 1990 */           else if (ch == '<') {
/* 1991 */             if (index > tokenStart) {
/* 1992 */               String token = string.substring(tokenStart + 1, index);
/* 1993 */               if (token.length() > 0) {
/* 1994 */                 this.tokens.add(token);
/*      */               }
/*      */             } 
/* 1997 */             decorated = true;
/* 1998 */             tokenStart = index;
/*      */           } 
/*      */         } 
/*      */       } 
/* 2002 */       if (length - tokenStart > 2) {
/* 2003 */         String token = string.substring(tokenStart + 1, length - 1);
/* 2004 */         this.tokens.add(token);
/*      */       } 
/* 2006 */       if (Trace.isOn) {
/* 2007 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Tokenizer", "<init>(String)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private String nextToken() {
/*      */       String token;
/* 2015 */       if (Trace.isOn) {
/* 2016 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Tokenizer", "nextToken()");
/*      */       }
/*      */       
/* 2019 */       if (this.tokens.size() > 0) {
/* 2020 */         token = this.tokens.firstElement();
/* 2021 */         this.tokens.remove(0);
/*      */       } else {
/*      */         
/* 2024 */         NoSuchElementException traceRet1 = new NoSuchElementException();
/* 2025 */         if (Trace.isOn) {
/* 2026 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Tokenizer", "nextToken()", traceRet1);
/*      */         }
/*      */         
/* 2029 */         throw traceRet1;
/*      */       } 
/* 2031 */       if (Trace.isOn) {
/* 2032 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Tokenizer", "nextToken()", token);
/*      */       }
/*      */       
/* 2035 */       return token;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/* 2042 */     if (Trace.isOn) {
/* 2043 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setBatchProperties(Map<String , Object>)", "setter", properties);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends String, ? extends Object> m) {
/* 2051 */     if (Trace.isOn) {
/* 2052 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "putAll(Map<? extends String , ? extends Object>)", new Object[] { m });
/*      */     }
/*      */ 
/*      */     
/* 2056 */     if (Trace.isOn) {
/* 2057 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "putAll(Map<? extends String , ? extends Object>)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<?, ?> getMap(boolean deepClone) throws JMSException {
/* 2065 */     if (Trace.isOn)
/* 2066 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getMap(boolean)", new Object[] {
/* 2067 */             Boolean.valueOf(deepClone)
/*      */           }); 
/* 2069 */     if (Trace.isOn) {
/* 2070 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getMap(boolean)", null);
/*      */     }
/*      */     
/* 2073 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasBody() {
/* 2078 */     if (Trace.isOn) {
/* 2079 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "hasBody()");
/*      */     }
/*      */     
/* 2082 */     if (Trace.isOn) {
/* 2083 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "hasBody()", 
/* 2084 */           Boolean.valueOf(false));
/*      */     }
/* 2086 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryTime() throws JMSException {
/* 2092 */     if (Trace.isOn) {
/* 2093 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "getJMSDeliveryTime()", "getter", 
/* 2094 */           Long.valueOf(0L));
/*      */     }
/* 2096 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 2101 */     if (Trace.isOn)
/* 2102 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage", "setJMSDeliveryTime(long)", "setter", 
/* 2103 */           Long.valueOf(deliveryTime)); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSMapMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */