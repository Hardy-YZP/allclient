/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class WMQDestinationURIParser
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQDestinationURIParser.java";
/*     */   private static final char SUBTOPIC_MATCHMANY_CHAR = '#';
/*     */   private static final char SUBTOPIC_MATCHONE_CHAR = '+';
/*     */   private static final char SUBTOPIC_SEPARATOR_CHAR = '/';
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQDestinationURIParser.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean uriOutputTxtBaseCacheValid = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean uriOutputTxtPropsCacheValid = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private String uriOutputTxtBaseCache = "";
/*  82 */   private String uriOutputTxtPropsCache = "";
/*     */ 
/*     */   
/*  85 */   private int uriDomain = -1;
/*  86 */   private String uriName = "";
/*  87 */   private String uriQMName = "";
/*  88 */   private HashMap<String, Object> uriProps = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQDestinationURIParser() {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "<init>()");
/*     */     }
/*     */     
/* 102 */     initialise();
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "<init>()");
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
/*     */   public WMQDestinationURIParser(String uri) throws JMSException {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "<init>(String)", new Object[] { uri });
/*     */     }
/*     */     
/* 121 */     initialise();
/* 122 */     setUri(uri);
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "<init>(String)");
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
/*     */   public int getDomain() {
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getDomain()", "getter", 
/* 138 */           Integer.valueOf(this.uriDomain));
/*     */     }
/* 140 */     return this.uriDomain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDomain(int domain) {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setDomain(int)", new Object[] {
/* 151 */             Integer.valueOf(domain)
/*     */           });
/*     */     }
/* 154 */     switch (domain) {
/*     */       case 1:
/*     */       case 2:
/* 157 */         this.uriDomain = domain;
/*     */         break;
/*     */       default:
/* 160 */         this.uriDomain = -1; break;
/*     */     } 
/* 162 */     this.uriOutputTxtBaseCacheValid = false;
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setDomain(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   enum State
/*     */   {
/* 171 */     SEEKING_PERCENT, FIRST_DIGIT, SECOND_DIGIT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestinationName() {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getDestinationName()");
/*     */     }
/*     */ 
/*     */     
/* 185 */     String destinationName = "";
/*     */ 
/*     */ 
/*     */     
/* 189 */     if (this.uriOutputTxtBaseCache.startsWith("topic://") || this.uriOutputTxtBaseCache
/* 190 */       .startsWith("queue://")) {
/*     */       
/*     */       try {
/* 193 */         destinationName = URLDecoder.decode(preprocess(this.uriName), "UTF-8");
/*     */       }
/* 195 */       catch (UnsupportedEncodingException|IllegalArgumentException e) {
/* 196 */         if (Trace.isOn) {
/* 197 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getDestinationName()", e);
/*     */         }
/*     */ 
/*     */         
/* 201 */         destinationName = this.uriName;
/*     */       } 
/*     */     } else {
/*     */       
/* 205 */       destinationName = this.uriName;
/*     */     } 
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getDestinationName()", destinationName);
/*     */     }
/*     */ 
/*     */     
/* 212 */     return destinationName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String preprocess(String s) {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "preprocess(String)", new Object[] { s });
/*     */     }
/* 224 */     String result = s;
/* 225 */     for (char c : "!$&'()*+,;=".toCharArray()) {
/* 226 */       int pos = result.indexOf(c);
/* 227 */       if (pos != -1)
/*     */       {
/*     */         
/* 230 */         result = result.substring(0, pos) + (String)safeEncoding.get(Character.valueOf(c)) + result.substring(pos + 1);
/*     */       }
/*     */     } 
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "preprocess(String)", result);
/*     */     }
/* 236 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestinationName(String destName) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setDestinationName(String)", "setter", destName);
/*     */     }
/*     */     
/* 249 */     this.uriName = (destName == null) ? "" : destName;
/* 250 */     this.uriOutputTxtBaseCacheValid = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQmName() {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getQmName()", "getter", this.uriQMName);
/*     */     }
/*     */     
/* 263 */     return this.uriQMName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQmName(String qmName) {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setQmName(String)", "setter", qmName);
/*     */     }
/*     */     
/* 276 */     this.uriQMName = (qmName == null) ? "" : qmName;
/* 277 */     this.uriOutputTxtBaseCacheValid = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<String, Object> getProps() {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getProps()", "getter", this.uriProps);
/*     */     }
/*     */     
/* 290 */     return this.uriProps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPropsIntoPropertyContext(JmsPropertyContext jmsProps) throws JMSException {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getPropsIntoPropertyContext(JmsPropertyContext)", new Object[] { jmsProps });
/*     */     }
/*     */ 
/*     */     
/* 305 */     for (Map.Entry<String, Object> property : this.uriProps.entrySet()) {
/* 306 */       jmsProps.setStringProperty(property.getKey(), (property.getValue() == null) ? null : property.getValue().toString());
/*     */     }
/*     */     
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getPropsIntoPropertyContext(JmsPropertyContext)");
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
/*     */   public void setProps(HashMap<String, Object> props) {
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setProps(HashMap)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 329 */     if (props != null) {
/* 330 */       this.uriProps = props;
/* 331 */       this.uriOutputTxtPropsCacheValid = false;
/*     */     } 
/*     */   }
/*     */   
/* 335 */   private static Set<String> uriExcludeProperties = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String ENCODEABLE_CHARS = "!$&'()*+,;=";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   private static Map<Character, String> safeEncoding = new HashMap<>();
/*     */   static {
/* 348 */     for (char c : "!$&'()*+,;=".toCharArray()) {
/* 349 */       String cAsString = new String(new char[] { c });
/*     */       try {
/* 351 */         safeEncoding.put(Character.valueOf(c), URLEncoder.encode(cAsString, "UTF-8"));
/*     */       }
/* 353 */       catch (UnsupportedEncodingException e) {
/*     */         
/* 355 */         safeEncoding.put(Character.valueOf(c), cAsString);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "static()");
/*     */     }
/* 364 */     uriExcludeProperties.add("XMSC_OBJECT_IDENTITY");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 371 */     uriExcludeProperties.add("OBJECT_IDENTITY");
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "static()");
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
/*     */   public String getURI() throws JMSException {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getURI()");
/*     */     }
/*     */ 
/*     */     
/* 390 */     StringBuffer destURI = new StringBuffer();
/*     */ 
/*     */     
/* 393 */     if (this.uriOutputTxtBaseCacheValid) {
/* 394 */       destURI.append(this.uriOutputTxtBaseCache);
/*     */     } else {
/*     */       
/* 397 */       if (this.uriDomain == 1) {
/* 398 */         destURI.append("queue://");
/* 399 */         destURI.append(this.uriQMName);
/* 400 */         destURI.append("/");
/*     */       }
/* 402 */       else if (this.uriDomain == 2) {
/* 403 */         destURI.append("topic://");
/*     */       } else {
/*     */         
/* 406 */         destURI.append("://");
/*     */       } 
/* 408 */       destURI.append(this.uriName);
/* 409 */       this.uriOutputTxtBaseCache = destURI.toString();
/* 410 */       this.uriOutputTxtBaseCacheValid = true;
/*     */     } 
/*     */ 
/*     */     
/* 414 */     if (this.uriOutputTxtPropsCacheValid) {
/* 415 */       destURI.append(this.uriOutputTxtPropsCache);
/*     */     } else {
/*     */       
/* 418 */       boolean first = true;
/* 419 */       StringBuffer destProps = new StringBuffer();
/* 420 */       for (Map.Entry<String, Object> property : this.uriProps.entrySet()) {
/*     */ 
/*     */         
/* 423 */         String unEscapedName = property.getKey();
/* 424 */         if (uriExcludeProperties.contains(unEscapedName)) {
/*     */           continue;
/*     */         }
/* 427 */         String name = addEscapes(unEscapedName);
/*     */ 
/*     */         
/* 430 */         Object v = property.getValue();
/* 431 */         String value = (v == null) ? "" : v.toString();
/* 432 */         if (!name.equals("ibmxml")) {
/* 433 */           value = addEscapes(value);
/*     */         }
/*     */         
/* 436 */         if (first) {
/* 437 */           destProps.append("?");
/*     */         } else {
/*     */           
/* 440 */           destProps.append("&");
/*     */         } 
/* 442 */         destProps.append(name).append("=").append(value);
/* 443 */         first = false;
/*     */       } 
/* 445 */       String s = destProps.toString();
/* 446 */       destURI.append(s);
/* 447 */       this.uriOutputTxtPropsCache = s;
/* 448 */       this.uriOutputTxtPropsCacheValid = true;
/*     */     } 
/*     */     
/* 451 */     String result = destURI.toString();
/*     */     
/* 453 */     if (Trace.isOn) {
/* 454 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "getURI()", result);
/*     */     }
/*     */     
/* 457 */     return result;
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
/*     */   public void setUri(String uriP) throws JMSException {
/* 470 */     if (Trace.isOn) {
/* 471 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", new Object[] { uriP });
/*     */     }
/*     */ 
/*     */     
/* 475 */     String uri = uriP;
/* 476 */     String uriBaseTxt = "";
/* 477 */     String uriPropsTxt = "";
/* 478 */     boolean isValidUri = false;
/*     */ 
/*     */     
/* 481 */     if (uri == null || uri.trim().length() == 0) {
/* 482 */       HashMap<String, Object> inserts = new HashMap<>();
/* 483 */       inserts.put("XMSC_DESTINATION_NAME", uri);
/* 484 */       JMSException je1 = (JMSException)NLSServices.createException("JMSCMQ0005", inserts);
/* 485 */       if (Trace.isOn) {
/* 486 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", (Throwable)je1, 1);
/*     */       }
/*     */       
/* 489 */       throw je1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 497 */     uri = uri.trim();
/* 498 */     String name = uri;
/*     */     
/* 500 */     boolean looksLikeQueueName = name.startsWith("queue://");
/* 501 */     boolean looksLikeTopicName = (!looksLikeQueueName && name.startsWith("topic://"));
/*     */     
/* 503 */     if (looksLikeQueueName && this.uriDomain != 2) {
/*     */ 
/*     */       
/* 506 */       this.uriDomain = 1;
/* 507 */       name = name.substring("queue://".length());
/*     */ 
/*     */       
/* 510 */       int qmIndex = name.indexOf("/");
/* 511 */       if (qmIndex == 0) {
/*     */         
/* 513 */         name = name.substring(1);
/*     */       }
/* 515 */       else if (qmIndex > 0) {
/*     */ 
/*     */         
/* 518 */         this.uriQMName = name.substring(0, qmIndex);
/* 519 */         name = name.substring(qmIndex + 1);
/*     */       }
/*     */       else {
/*     */         
/* 523 */         HashMap<String, Object> inserts = new HashMap<>();
/* 524 */         inserts.put("XMSC_DESTINATION_NAME", uri);
/* 525 */         JMSException je2 = (JMSException)NLSServices.createException("JMSCMQ0005", inserts);
/* 526 */         if (Trace.isOn) {
/* 527 */           Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", (Throwable)je2, 2);
/*     */         }
/*     */         
/* 530 */         throw je2;
/*     */       } 
/* 532 */       isValidUri = true;
/*     */     }
/* 534 */     else if (looksLikeTopicName && this.uriDomain != 1) {
/*     */ 
/*     */       
/* 537 */       this.uriDomain = 2;
/* 538 */       name = name.substring("topic://".length());
/* 539 */       isValidUri = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 546 */     if ((looksLikeTopicName || looksLikeQueueName) && isValidUri == true) {
/*     */ 
/*     */       
/* 549 */       int propIndex = name.indexOf('?');
/* 550 */       if (propIndex > 0) {
/*     */         
/* 552 */         String props = name.substring(propIndex + 1);
/*     */         
/* 554 */         uriPropsTxt = name.substring(propIndex);
/* 555 */         uriBaseTxt = uri.substring(0, uri.length() - uriPropsTxt.length());
/*     */         
/* 557 */         name = name.substring(0, propIndex);
/*     */         
/* 559 */         StringTokenizer propToks = new StringTokenizer(props, "&");
/*     */         
/* 561 */         while (propToks.hasMoreTokens()) {
/* 562 */           String tok = propToks.nextToken();
/*     */           int delim;
/* 564 */           if ((delim = tok.indexOf('=')) > 0) {
/*     */ 
/*     */ 
/*     */             
/* 568 */             String propName = tok.substring(0, delim);
/* 569 */             String propValue = tok.substring(delim + 1);
/*     */             try {
/* 571 */               propName = URLDecoder.decode(propName, "UTF8");
/* 572 */               propValue = URLDecoder.decode(propValue, "UTF8");
/*     */             }
/* 574 */             catch (NoSuchMethodError e) {
/* 575 */               if (Trace.isOn) {
/* 576 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", e, 1);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 584 */               if (Trace.isOn) {
/* 585 */                 Trace.data(this, "setUri(String)", "URLDecoder was used with 1.3.1 jvm this is not supported");
/*     */               }
/*     */             }
/* 588 */             catch (UnsupportedEncodingException e) {
/* 589 */               if (Trace.isOn) {
/* 590 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", e, 2);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 595 */               HashMap<String, Object> inserts = new HashMap<>();
/* 596 */               inserts.put("XMSC_DESTINATION_NAME", uri);
/* 597 */               JMSException je = (JMSException)NLSServices.createException("JMSCMQ0005", inserts);
/* 598 */               if (Trace.isOn) {
/* 599 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", (Throwable)je, 3);
/*     */               }
/*     */ 
/*     */               
/* 603 */               throw je;
/*     */             } 
/* 605 */             this.uriProps.put(propName, propValue);
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 610 */         uriBaseTxt = uri;
/* 611 */         uriPropsTxt = "";
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 617 */     if (name == null || name.equals("") || name.indexOf("://") != -1) {
/*     */       
/* 619 */       HashMap<String, Object> inserts = new HashMap<>();
/* 620 */       inserts.put("XMSC_DESTINATION_NAME", uri);
/* 621 */       JMSException je3 = (JMSException)NLSServices.createException("JMSCMQ0005", inserts);
/* 622 */       if (Trace.isOn) {
/* 623 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)", (Throwable)je3, 4);
/*     */       }
/*     */       
/* 626 */       throw je3;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 635 */     this.uriName = name;
/*     */ 
/*     */     
/* 638 */     if (isValidUri == true) {
/* 639 */       this.uriOutputTxtBaseCache = uriBaseTxt;
/* 640 */       this.uriOutputTxtPropsCache = uriPropsTxt;
/* 641 */       this.uriOutputTxtBaseCacheValid = true;
/* 642 */       this.uriOutputTxtPropsCacheValid = true;
/*     */     } 
/*     */     
/* 645 */     if (Trace.isOn) {
/* 646 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "setUri(String)");
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
/*     */   public boolean containsAnyWildcard(int brokerVersion) {
/* 661 */     if (Trace.isOn) {
/* 662 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "containsAnyWildcard(int)", new Object[] {
/* 663 */             Integer.valueOf(brokerVersion)
/*     */           });
/*     */     }
/* 666 */     String topicString = null;
/*     */     
/* 668 */     topicString = getDestinationName();
/*     */     
/* 670 */     if (topicString != null) {
/* 671 */       if (brokerVersion == 0) {
/*     */         
/* 673 */         if (topicString.indexOf('*') >= 0 || topicString.indexOf('?') >= 0) {
/* 674 */           if (Trace.isOn) {
/* 675 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "containsAnyWildcard(int)", 
/* 676 */                 Boolean.valueOf(true), 1);
/*     */           }
/* 678 */           return true;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 683 */       else if (topicString.indexOf('+') >= 0 || topicString.indexOf('#') >= 0) {
/* 684 */         if (Trace.isOn) {
/* 685 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "containsAnyWildcard(int)", 
/* 686 */               Boolean.valueOf(true), 2);
/*     */         }
/* 688 */         return true;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 693 */     if (Trace.isOn) {
/* 694 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "containsAnyWildcard(int)", 
/* 695 */           Boolean.valueOf(false), 3);
/*     */     }
/* 697 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsWildcard() {
/* 708 */     if (Trace.isOn) {
/* 709 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "containsWildcard()");
/*     */     }
/*     */ 
/*     */     
/* 713 */     boolean result = false;
/* 714 */     String topicString = null;
/*     */     
/* 716 */     topicString = getDestinationName();
/*     */     
/* 718 */     result = (findFirstMatchManyWildcard(topicString) >= 0 || findFirstMatchOneWildcard(topicString) >= 0);
/*     */     
/* 720 */     if (Trace.isOn) {
/* 721 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "containsWildcard()", 
/* 722 */           Boolean.valueOf(result));
/*     */     }
/* 724 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int findFirstMatchManyWildcard(String topic) {
/* 735 */     if (Trace.isOn) {
/* 736 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", new Object[] { topic });
/*     */     }
/*     */ 
/*     */     
/* 740 */     int firstOccurrence = -1;
/*     */     
/* 742 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 745 */     if (topiclength == 0) {
/* 746 */       int traceRet1 = -1;
/*     */       
/* 748 */       if (Trace.isOn) {
/* 749 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 750 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 752 */       return traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 756 */     if (topiclength == 1) {
/* 757 */       if (topic.charAt(0) == '#') {
/* 758 */         if (Trace.isOn) {
/* 759 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 760 */               Integer.valueOf(0), 2);
/*     */         }
/* 762 */         return 0;
/*     */       } 
/*     */ 
/*     */       
/* 766 */       int traceRet2 = -1;
/* 767 */       if (Trace.isOn) {
/* 768 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 769 */             Integer.valueOf(traceRet2), 3);
/*     */       }
/* 771 */       return traceRet2;
/*     */     } 
/*     */ 
/*     */     
/* 775 */     if (topic.charAt(0) == '#' && topic.charAt(1) == '/') {
/* 776 */       if (Trace.isOn) {
/* 777 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 778 */             Integer.valueOf(0), 4);
/*     */       }
/* 780 */       return 0;
/*     */     } 
/*     */ 
/*     */     
/* 784 */     firstOccurrence = topic.indexOf("/#/");
/* 785 */     if (firstOccurrence != -1) {
/* 786 */       int traceRet3 = firstOccurrence + 1;
/* 787 */       if (Trace.isOn) {
/* 788 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 789 */             Integer.valueOf(traceRet3), 5);
/*     */       }
/* 791 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 796 */     if (topic.charAt(topiclength - 2) == '/' && topic.charAt(topiclength - 1) == '#') {
/* 797 */       int traceRet4 = topiclength - 1;
/* 798 */       if (Trace.isOn) {
/* 799 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 800 */             Integer.valueOf(traceRet4), 6);
/*     */       }
/* 802 */       return traceRet4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 807 */     if (Trace.isOn) {
/* 808 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchManyWildcard(String)", 
/* 809 */           Integer.valueOf(firstOccurrence), 7);
/*     */     }
/* 811 */     return firstOccurrence;
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
/*     */   private final int findFirstMatchOneWildcard(String topic) {
/* 823 */     if (Trace.isOn) {
/* 824 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", new Object[] { topic });
/*     */     }
/*     */ 
/*     */     
/* 828 */     int firstOccurrence = -1;
/*     */     
/* 830 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 833 */     if (topiclength == 0) {
/* 834 */       int traceRet1 = -1;
/* 835 */       if (Trace.isOn) {
/* 836 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 837 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 839 */       return traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 843 */     if (topiclength == 1) {
/* 844 */       if (topic.charAt(0) == '+') {
/* 845 */         if (Trace.isOn) {
/* 846 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 847 */               Integer.valueOf(0), 2);
/*     */         }
/* 849 */         return 0;
/*     */       } 
/*     */ 
/*     */       
/* 853 */       int traceRet2 = -1;
/* 854 */       if (Trace.isOn) {
/* 855 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 856 */             Integer.valueOf(traceRet2), 3);
/*     */       }
/* 858 */       return traceRet2;
/*     */     } 
/*     */ 
/*     */     
/* 862 */     if (topic.charAt(0) == '+' && topic.charAt(1) == '/') {
/* 863 */       if (Trace.isOn) {
/* 864 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 865 */             Integer.valueOf(0), 4);
/*     */       }
/* 867 */       return 0;
/*     */     } 
/*     */ 
/*     */     
/* 871 */     firstOccurrence = topic.indexOf("/+/");
/* 872 */     if (firstOccurrence != -1) {
/* 873 */       int traceRet3 = firstOccurrence + 1;
/* 874 */       if (Trace.isOn) {
/* 875 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 876 */             Integer.valueOf(traceRet3), 5);
/*     */       }
/* 878 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 883 */     if (topic.charAt(topiclength - 2) == '/' && topic.charAt(topiclength - 1) == '+') {
/* 884 */       int traceRet4 = topiclength - 1;
/* 885 */       if (Trace.isOn) {
/* 886 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 887 */             Integer.valueOf(traceRet4), 6);
/*     */       }
/* 889 */       return traceRet4;
/*     */     } 
/*     */ 
/*     */     
/* 893 */     if (Trace.isOn) {
/* 894 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "findFirstMatchOneWildcard(String)", 
/* 895 */           Integer.valueOf(firstOccurrence), 7);
/*     */     }
/* 897 */     return firstOccurrence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialise() {
/* 905 */     if (Trace.isOn) {
/* 906 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "initialise()");
/*     */     }
/*     */     
/* 909 */     this.uriOutputTxtBaseCacheValid = false;
/* 910 */     this.uriOutputTxtPropsCacheValid = false;
/* 911 */     this.uriOutputTxtBaseCache = "";
/* 912 */     this.uriOutputTxtPropsCache = "";
/* 913 */     this.uriDomain = -1;
/* 914 */     this.uriName = "";
/* 915 */     this.uriQMName = "";
/* 916 */     this.uriProps.clear();
/* 917 */     if (Trace.isOn) {
/* 918 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "initialise()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String addEscapes(String txt) {
/* 928 */     if (Trace.isOn) {
/* 929 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "addEscapes(String)", new Object[] { txt });
/*     */     }
/*     */ 
/*     */     
/* 933 */     String retTxt = txt;
/*     */     
/*     */     try {
/* 936 */       retTxt = URLEncoder.encode(retTxt, "UTF8");
/*     */     }
/* 938 */     catch (NoSuchMethodError e) {
/* 939 */       if (Trace.isOn) {
/* 940 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "addEscapes(String)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 947 */       if (Trace.isOn) {
/* 948 */         Trace.data(this, "addEscapes(String)", "URLEncoder was used with 1.3.1 jvm this is not supported");
/*     */       }
/*     */     }
/* 951 */     catch (UnsupportedEncodingException e) {
/* 952 */       if (Trace.isOn) {
/* 953 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "addEscapes(String)", e, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 958 */       if (Trace.isOn) {
/* 959 */         Trace.data(this, "addEscapes(String)", "UTF-8 encoder not supported - URI escape sequences not processed");
/*     */       }
/*     */     } 
/*     */     
/* 963 */     if (Trace.isOn) {
/* 964 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser", "addEscapes(String)", retTxt);
/*     */     }
/*     */     
/* 967 */     return retTxt;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQDestinationURIParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */