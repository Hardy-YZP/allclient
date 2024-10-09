/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQEnvironment
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQEnvironment.java";
/*     */   static final String CLASSNAME = "MQEnvironment";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQEnvironment.java");
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
/*  74 */   public static String hostname = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static int port = 1414;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static String channel = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static String userID = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static String password = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public static int CCSID = 819;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public static Collection<Integer> hdrCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public static Collection<Integer> msgCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public static String sslCipherSuite = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public static String sslPeerName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public static Collection<Object> sslCertStores = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public static Object sslSocketFactory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public static int sslResetCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean sslFipsRequired = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public static String localAddressSetting = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public static byte[] connTag = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public static int connOptions = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public static Hashtable<String, Object> properties = new MQEnvironmentPropertiesHashtable();
/*     */ 
/*     */   
/* 380 */   static int xaLicenseMsg = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean runningInWebSphere = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "static()");
/*     */     }
/*     */ 
/*     */     
/* 397 */     String firewallProperty = "com.ibm.msg.client.wmq.compat.base.internal.localAddress";
/* 398 */     PropertyStore.register(firewallProperty, "");
/* 399 */     String fwprop = PropertyStore.getStringProperty(firewallProperty);
/*     */ 
/*     */     
/* 402 */     if (fwprop != null && !fwprop.trim().equals(""))
/*     */     {
/*     */ 
/*     */       
/* 406 */       localAddressSetting = fwprop;
/*     */     }
/*     */ 
/*     */     
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean runningInWS() {
/* 421 */     if (Trace.isOn) {
/* 422 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "runningInWS()");
/*     */     }
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "runningInWS()", 
/* 426 */           Boolean.valueOf(runningInWebSphere));
/*     */     }
/* 428 */     return runningInWebSphere;
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
/*     */   protected static final String stringFromBytes(byte[] bData) {
/*     */     String retVal;
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "stringFromBytes(byte [ ])", new Object[] { bData });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 455 */       retVal = new String(bData, "819");
/*     */     }
/* 457 */     catch (UnsupportedEncodingException ex) {
/* 458 */       if (Trace.isOn) {
/* 459 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "stringFromBytes(byte [ ])", ex);
/*     */       }
/*     */ 
/*     */       
/* 463 */       if (Trace.isOn) {
/* 464 */         Trace.traceData("MQEnvironment", "Could not convert string using 819. Using default locale", null);
/*     */       }
/* 466 */       retVal = new String(bData, Charset.defaultCharset());
/*     */     } 
/*     */     
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "stringFromBytes(byte [ ])", retVal);
/*     */     }
/*     */     
/* 473 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final byte[] bytesFromString(String aValue) {
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "bytesFromString(String)", new Object[] { aValue });
/*     */     }
/*     */     
/* 487 */     byte[] retVal = null;
/*     */     try {
/* 489 */       retVal = aValue.getBytes("819");
/*     */     }
/* 491 */     catch (UnsupportedEncodingException ex) {
/* 492 */       if (Trace.isOn) {
/* 493 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "bytesFromString(String)", ex);
/*     */       }
/*     */ 
/*     */       
/* 497 */       if (Trace.isOn) {
/* 498 */         Trace.traceData("MQEnvironment", "Could not convert string using 819. Using default locale", null);
/*     */       }
/* 500 */       retVal = aValue.getBytes(Charset.defaultCharset());
/*     */     } 
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "bytesFromString(String)", retVal);
/*     */     }
/*     */     
/* 506 */     return retVal;
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
/*     */   static Object getDefaultProperty(Object key) {
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getDefaultProperty(Object)", new Object[] { key });
/*     */     }
/*     */ 
/*     */     
/* 539 */     Object<Object> property = (Object<Object>)properties.get(key);
/*     */     
/* 541 */     if (property == null) {
/*     */ 
/*     */       
/* 544 */       if (key.equals("CCSID")) {
/* 545 */         property = (Object<Object>)Integer.valueOf(CCSID);
/*     */       }
/*     */ 
/*     */       
/* 549 */       if (key.equals("channel")) {
/* 550 */         property = (Object<Object>)channel;
/*     */       }
/*     */ 
/*     */       
/* 554 */       if (key.equals("connectOptions")) {
/* 555 */         property = (Object<Object>)Integer.valueOf(connOptions);
/*     */       }
/*     */ 
/*     */       
/* 559 */       if (key.equals("hostname")) {
/* 560 */         property = (Object<Object>)hostname;
/*     */       }
/*     */ 
/*     */       
/* 564 */       if (key.equals("password")) {
/* 565 */         property = (Object<Object>)password;
/*     */       }
/*     */ 
/*     */       
/* 569 */       if (key.equals("port")) {
/* 570 */         property = (Object<Object>)Integer.valueOf(port);
/*     */       }
/*     */ 
/*     */       
/* 574 */       if (key.equals("receiveExit")) {
/* 575 */         property = null;
/*     */       }
/*     */ 
/*     */       
/* 579 */       if (key.equals("securityExit")) {
/* 580 */         property = null;
/*     */       }
/*     */ 
/*     */       
/* 584 */       if (key.equals("sendExit")) {
/* 585 */         property = null;
/*     */       }
/*     */ 
/*     */       
/* 589 */       if (key.equals("receiveExitData")) {
/* 590 */         property = null;
/*     */       }
/*     */ 
/*     */       
/* 594 */       if (key.equals("securitydExitData")) {
/* 595 */         property = null;
/*     */       }
/*     */ 
/*     */       
/* 599 */       if (key.equals("sendExitData")) {
/* 600 */         property = null;
/*     */       }
/*     */ 
/*     */       
/* 604 */       if (key.equals("transport")) {
/* 605 */         property = (Object<Object>)"MQSeries";
/*     */       }
/*     */ 
/*     */       
/* 609 */       if (key.equals("userID")) {
/* 610 */         property = (Object<Object>)userID;
/*     */       }
/*     */       
/* 613 */       if (key.equals("SSL Cipher Suite")) {
/* 614 */         property = (Object<Object>)sslCipherSuite;
/*     */       }
/*     */       
/* 617 */       if (key.equals("SSL Peer Name")) {
/* 618 */         property = (Object<Object>)sslPeerName;
/*     */       }
/*     */       
/* 621 */       if (key.equals("SSL CertStores")) {
/* 622 */         property = (Object<Object>)sslCertStores;
/*     */       }
/*     */       
/* 625 */       if (key.equals("SSL Socket Factory")) {
/* 626 */         property = (Object<Object>)sslSocketFactory;
/*     */       }
/*     */       
/* 629 */       if (key.equals("Local Address Property")) {
/* 630 */         property = (Object<Object>)localAddressSetting;
/*     */       }
/*     */       
/* 633 */       if (key.equals("Header Compression Property")) {
/* 634 */         Collection<Integer> collection = hdrCompList;
/*     */       }
/* 636 */       if (key.equals("ProviderMessage Compression Property")) {
/* 637 */         Collection<Integer> collection = msgCompList;
/*     */       }
/* 639 */       if (key.equals("ConnTag Property")) {
/* 640 */         byte[] arrayOfByte = connTag;
/*     */       }
/* 642 */       if (key.equals("KeyResetCount")) {
/* 643 */         property = (Object<Object>)Integer.valueOf(sslResetCount);
/*     */       }
/* 645 */       if (key.equals("SSL Fips Required")) {
/* 646 */         property = (Object<Object>)Boolean.valueOf(sslFipsRequired);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 651 */     if (Trace.isOn) {
/* 652 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getDefaultProperty(Object)", property);
/*     */     }
/*     */     
/* 655 */     return property;
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
/*     */   static Object getObjectProperty(String key, Map<String, Object> properties2) {
/* 672 */     if (Trace.isOn) {
/* 673 */       if (properties2.containsKey("password")) {
/* 674 */         Map<String, Object> propsNotPasswd = new HashMap<>(properties2);
/* 675 */         propsNotPasswd.put("password", "********");
/* 676 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getObjectProperty(String,Map)", new Object[] { key, propsNotPasswd });
/*     */       } else {
/* 678 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getObjectProperty(String,Map)", new Object[] { key, properties2 });
/*     */       } 
/*     */     }
/* 681 */     Object property = null;
/*     */ 
/*     */     
/* 684 */     if (properties2 != null) {
/* 685 */       property = properties2.get(key);
/*     */     }
/*     */ 
/*     */     
/* 689 */     if (property == null) {
/* 690 */       property = getDefaultProperty(key);
/*     */     }
/*     */     
/* 693 */     if (Trace.isOn) {
/* 694 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getObjectProperty(String,Hashtable)", key.equals("password") ? "********" : property);
/*     */     }
/* 696 */     return property;
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
/*     */   static String getStringProperty(String key, Map<String, Object> properties) {
/* 713 */     if (Trace.isOn) {
/* 714 */       if (properties.containsKey("password")) {
/* 715 */         Map<String, Object> propsNotPasswd = new HashMap<>(properties);
/* 716 */         propsNotPasswd.put("password", "********");
/* 717 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getStringProperty(String,Map)", new Object[] { key, propsNotPasswd });
/*     */       } else {
/* 719 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getStringProperty(String,Map)", new Object[] { key, properties });
/*     */       } 
/*     */     }
/* 722 */     Object object = getObjectProperty(key, properties);
/*     */     
/* 724 */     if (object instanceof String) {
/* 725 */       String traceRet1 = (String)object;
/* 726 */       if (Trace.isOn) {
/* 727 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getStringProperty(String,Hashtable)", key.equals("password") ? "********" : traceRet1, 1);
/*     */       }
/*     */ 
/*     */       
/* 731 */       return traceRet1;
/*     */     } 
/*     */     
/* 734 */     if (Trace.isOn) {
/* 735 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getStringProperty(String,Hashtable)", null, 2);
/*     */     }
/* 737 */     return null;
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
/*     */   static int getIntegerProperty(String key, Map<String, Object> mqProperties) {
/* 754 */     if (Trace.isOn) {
/* 755 */       if (mqProperties.containsKey("password")) {
/* 756 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 757 */         propsNotPasswd.put("password", "********");
/* 758 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getIntegerProperty(String,Map)", new Object[] { key, propsNotPasswd });
/*     */       } else {
/* 760 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getIntegerProperty(String,Map)", new Object[] { key, mqProperties });
/*     */       } 
/*     */     }
/* 763 */     Object object = getObjectProperty(key, mqProperties);
/*     */     
/* 765 */     if (object instanceof Integer) {
/* 766 */       int traceRet1 = ((Integer)object).intValue();
/* 767 */       if (Trace.isOn) {
/* 768 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getIntegerProperty(String,Map)", Integer.valueOf(traceRet1), 1);
/*     */       }
/* 770 */       return traceRet1;
/*     */     } 
/* 772 */     if (Trace.isOn) {
/* 773 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment", "getIntegerProperty(String,Hashtable)", Integer.valueOf(0), 2);
/*     */     }
/* 775 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */