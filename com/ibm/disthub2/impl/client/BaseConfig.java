/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.client.ThreadProvider;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.DoPrivileged;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseConfig
/*     */   implements ExceptionConstants, Cloneable
/*     */ {
/*     */   protected static BaseConfig global;
/*     */   public static final int MAJOR_VERSION = 1;
/*     */   public static final int MINOR_VERSION = 2;
/*     */   public static final String SUBTOPIC_SEPARATOR = "/";
/*     */   public static final char SUBTOPIC_MATCHMANY_CHARB = '#';
/*     */   public static final char SUBTOPIC_MATCHONE_CHARB = '+';
/*     */   public static final char SUBTOPIC_SEPARATOR_CHARB = '/';
/*     */   public static final String CPID_SEPARATOR = "@";
/*     */   public static final char NONWILD_MARKER = '\001';
/*     */   public static final String TEMP_TOPIC_PREFIX = "\001TEMP/";
/*     */   public static final String EXTERNAL_TOPIC_PREFIX = "$TOPIC/";
/*     */   
/*     */   public static BaseConfig getBaseConfig() {
/*  92 */     return global;
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
/* 130 */   public static final int TOPIC_PREFIX_LENGTH = "$TOPIC/".length();
/*     */   public static final String EXTERNAL_TEMP_TOPIC_PREFIX = "$TEMP/";
/* 132 */   public static final int TEMP_TOPIC_PREFIX_LENGTH = "\001TEMP/".length();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public static String[] parameters = new String[] { "AUTH_PROTOCOLS", "AUTH_TIMEOUT", "CLIENT_PING_INTERVAL", "PING_TIMEOUT_MULTIPLE", "PING_MIN", "EXPECTED_MESSAGE_SIZE", "MAX_MESSAGE_SIZE", "MESSAGE_SIZE_EXTRA_ALLOWANCE", "DEBUG_NAME", "LOG_LEVEL_ERROR", "LOG_LEVEL_WARNING", "LOG_LEVEL_INFO", "DEBUG_LEVEL", "QOP_KEYSETUP_LIBRARY", "QOP_KEYSETUP_OPTIONS", "QOP_MSGHANDLER_LIBRARY", "QOP_MSGHANDLER_OPTIONS", "SSL_CLASS", "SSL_CLASS_ARGS", "SSL_CIPHER_SUITES", "SSL_KEYRING_FILE", "SSL_PASSPHRASE_FILE", "SSL_PASSPHRASE_LOADER", "SSL_PASSPHRASE_ARGS", "ENABLE_QOP_SECURITY", "CLIENT_SECURITY_IMPL", "ENABLE_TCP_NODELAY", "ENABLE_CLIENT_FLOW_CONTROL", "CLIENT_PING_INTERVAL", "PING_TIMEOUT_MULTIPLE", "PING_MIN" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public static String[] undocParameters = new String[] { "ALLOW_CONFIG_UPDATES" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public static String CLIENT_SECURITY_IMPL = "com.ibm.disthub2.impl.client.NoSecurity";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public String AUTH_PROTOCOLS = "PM";
/*     */ 
/*     */ 
/*     */   
/* 217 */   public long AUTH_TIMEOUT = 10000L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ENABLE_TCP_NODELAY = false;
/*     */ 
/*     */ 
/*     */   
/* 226 */   public ThreadProvider THREADER = null;
/*     */ 
/*     */   
/* 229 */   public String DEBUG_NAME = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public long LOG_LEVEL_ERROR = 0L;
/* 237 */   public long LOG_LEVEL_WARNING = 0L;
/* 238 */   public long LOG_LEVEL_INFO = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public int DEBUG_LEVEL = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public int EXPECTED_MESSAGE_SIZE = 200;
/*     */ 
/*     */   
/* 255 */   public int MAX_MESSAGE_SIZE = 100000;
/*     */ 
/*     */   
/* 258 */   public int MESSAGE_SIZE_EXTRA_ALLOWANCE = 500;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public String QOP_KEYSETUP_LIBRARY = "cryptolite-nonexport";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public String QOP_KEYSETUP_OPTIONS = "hash=m,ed=v";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public String QOP_MSGHANDLER_LIBRARY = "cryptolite-nonexport";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public String QOP_MSGHANDLER_OPTIONS = "hash=k,ed=v";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public String SSL_PEER_NAME = null;
/* 308 */   public Collection SSL_CERT_STORES = null;
/* 309 */   public Object SSL_SOCKET_FACTORY = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean ENABLE_QOP_SECURITY = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public String SSL_CLASS = "com.ibm.disthub2.broker.reference.security.JSSEAdaptor";
/*     */ 
/*     */   
/* 325 */   public String SSL_CLASS_ARGS = "Tivoli";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public String SSL_CIPHER_SUITES = "SSL_RSA_WITH_RC4_128_SHA  SSL_RSA_EXPORT_WITH_RC4_40_MD5";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public String SSL_KEYRING_FILE = null;
/*     */ 
/*     */ 
/*     */   
/* 350 */   public String SSL_PASSPHRASE_LOADER = "com.ibm.disthub2.broker.reference.GetSimpleData";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public String SSL_PASSPHRASE_ARGS = "";
/* 359 */   public String SSL_PASSPHRASE_FILE = "";
/*     */ 
/*     */   
/*     */   public boolean ENABLE_CLIENT_FLOW_CONTROL = false;
/*     */   
/* 364 */   public int CLIENT_PING_INTERVAL = 30000;
/*     */ 
/*     */   
/* 367 */   public int PING_TIMEOUT_MULTIPLE = 3;
/*     */ 
/*     */   
/* 370 */   public int PING_MIN = 100;
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean ALLOW_CONFIG_UPDATES = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean noMoreChanges = false;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String PARM_PREF = "DistHub.Parameter.";
/*     */ 
/*     */   
/*     */   protected static final String CONFIG_KEY = "DistHub.Config";
/*     */ 
/*     */   
/*     */   protected static boolean initClient;
/*     */ 
/*     */   
/*     */   protected static boolean initServer;
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*     */       try {
/* 398 */         Class.forName("com.ibm.disthub2.broker.reference.security.RealSecurity");
/* 399 */         CLIENT_SECURITY_IMPL = "com.ibm.disthub2.broker.reference.security.RealSecurity";
/* 400 */       } catch (Exception e) {
/* 401 */         Class.forName("com.ibm.disthub2.impl.client.NoSecurity");
/* 402 */         CLIENT_SECURITY_IMPL = "com.ibm.disthub2.impl.client.NoSecurity";
/*     */       } 
/* 404 */     } catch (Exception e) {
/*     */       
/* 406 */       throw new Error(ExceptionBuilder.buildReasonString(111981380, null));
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
/*     */   public static synchronized void initialize(Class<BaseConfig> cls) {
/* 431 */     if (initClient || initServer)
/*     */       return; 
/* 433 */     initClient = true;
/*     */     
/* 435 */     if (noMoreChanges && !ALLOW_CONFIG_UPDATES) {
/* 436 */       throw new IllegalStateException(ExceptionBuilder.buildReasonString(347943087, new Object[] { null }));
/*     */     }
/*     */     
/* 439 */     Properties config = null;
/*     */ 
/*     */     
/* 442 */     String conf = getProperty("DistHub.Config");
/* 443 */     if (conf != null) {
/* 444 */       config = new Properties();
/*     */       try {
/* 446 */         FileInputStream istr = new FileInputStream(conf);
/* 447 */         config.load(istr);
/* 448 */       } catch (IOException e) {
/* 449 */         throw new RuntimeException(ExceptionBuilder.buildReasonString(1662343319, new Object[] { e }));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 454 */     String[] parameters = null;
/* 455 */     String[] undocParameters = null;
/*     */     try {
/* 457 */       parameters = (String[])cls.getField("parameters").get(null);
/* 458 */       undocParameters = (String[])cls.getField("undocParameters").get(null);
/* 459 */       if (global == null)
/* 460 */         global = cls.newInstance(); 
/* 461 */     } catch (Exception e) {
/*     */       
/* 463 */       Assert.failure(e);
/*     */     }  int i;
/* 465 */     for (i = 0; i < parameters.length; i++) {
/* 466 */       String name = parameters[i];
/* 467 */       String val = getProperty("DistHub.Parameter." + name);
/* 468 */       if (val == null && config != null)
/* 469 */         val = config.getProperty(name); 
/* 470 */       if (val != null)
/* 471 */         setParameter(cls, name, val); 
/*     */     } 
/* 473 */     for (i = 0; i < undocParameters.length; i++) {
/* 474 */       String name = undocParameters[i];
/* 475 */       String val = getProperty("DistHub.Parameter." + name);
/* 476 */       if (val == null && config != null)
/* 477 */         val = config.getProperty(name); 
/* 478 */       if (val != null)
/* 479 */         setParameter(cls, name, val); 
/*     */     } 
/*     */   }
/*     */   
/* 483 */   private static Properties properties = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized String getProperty(String name) {
/* 495 */     if (properties == null) {
/* 496 */       properties = DoPrivileged.getProperties();
/*     */     }
/*     */     
/* 499 */     return properties.getProperty(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setParameter(String name, String val) {
/* 506 */     setParameter(BaseConfig.class, name, val);
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
/*     */   protected static synchronized void setParameter(Class cls, String name, String val) {
/* 526 */     Assert.condition(initClient);
/* 527 */     if (noMoreChanges && !ALLOW_CONFIG_UPDATES) {
/* 528 */       throw new IllegalStateException(ExceptionBuilder.buildReasonString(347943087, new Object[] { name }));
/*     */     }
/*     */     
/*     */     try {
/* 532 */       Field f = cls.getField(name);
/* 533 */       Class<?> type = f.getType();
/* 534 */       if (type == int.class) {
/* 535 */         if (val.startsWith("0x")) {
/* 536 */           f.set(global, Integer.valueOf(val.substring(2), 16));
/*     */         } else {
/* 538 */           f.set(global, Integer.valueOf(val));
/*     */         }  return;
/*     */       } 
/* 541 */       if (type == long.class) {
/* 542 */         if (val.startsWith("0x")) {
/* 543 */           f.set(global, Long.valueOf(val.substring(2), 16));
/*     */         } else {
/* 545 */           f.set(global, Long.valueOf(val));
/*     */         }  return;
/*     */       } 
/* 548 */       if (type == boolean.class) {
/* 549 */         f.set(global, Boolean.valueOf(val));
/*     */         return;
/*     */       } 
/* 552 */       if (type == String.class) {
/* 553 */         f.set(global, val);
/*     */         return;
/*     */       } 
/* 556 */       if (type == double.class) {
/* 557 */         f.set(global, Double.valueOf(val));
/*     */         return;
/*     */       } 
/* 560 */       if (type == float.class) {
/* 561 */         f.set(global, Float.valueOf(val));
/*     */         return;
/*     */       } 
/* 564 */     } catch (IllegalAccessException illegalAccessException) {
/*     */     
/* 566 */     } catch (NoSuchFieldException noSuchFieldException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(754669422, new Object[] { name, val }));
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
/*     */   protected static synchronized void setParameter(Class cls, Properties props) {
/* 585 */     if (!initClient) {
/* 586 */       initialize(cls);
/*     */     }
/* 588 */     for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements(); ) {
/* 589 */       String nextKey = (String)e.nextElement();
/* 590 */       String nextVal = props.getProperty(nextKey);
/* 591 */       setParameter(cls, nextKey, nextVal);
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
/*     */   protected static String getParameter(Class cls, String name) {
/*     */     try {
/* 610 */       Field f = cls.getField(name);
/* 611 */       Class<?> type = f.getType();
/* 612 */       if (type == String.class) {
/* 613 */         String temp = (String)f.get(global);
/* 614 */         return (temp == null) ? "" : temp;
/*     */       } 
/* 616 */       return String.valueOf(f.get(global));
/* 617 */     } catch (IllegalAccessException illegalAccessException) {
/*     */     
/* 619 */     } catch (NoSuchFieldException noSuchFieldException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 624 */     throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(1104995892, new Object[] { name }));
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
/*     */   protected static synchronized Properties getParameter(Class cls) {
/* 637 */     Properties list = new Properties();
/* 638 */     String[] pList = null;
/*     */     
/*     */     try {
/* 641 */       pList = (String[])cls.getField("parameters").get(null);
/* 642 */       for (int i = 0; i < pList.length; i++)
/* 643 */         list.put(pList[i], getParameter(cls, pList[i])); 
/* 644 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 647 */       Assert.failure(e);
/*     */     } 
/*     */     
/* 650 */     return list;
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
/*     */   protected static synchronized Properties getAllParameters(Class cls) {
/* 663 */     Properties list = new Properties();
/* 664 */     String[] pList = null;
/* 665 */     String[] upList = null;
/*     */     
/*     */     try {
/* 668 */       pList = (String[])cls.getField("parameters").get(null); int i;
/* 669 */       for (i = 0; i < pList.length; i++) {
/* 670 */         list.put(pList[i], getParameter(cls, pList[i]));
/*     */       }
/* 672 */       upList = (String[])cls.getField("undocParameters").get(null);
/* 673 */       for (i = 0; i < upList.length; i++)
/* 674 */         list.put(upList[i], getParameter(cls, upList[i])); 
/* 675 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 678 */       Assert.failure(e);
/*     */     } 
/*     */     
/* 681 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\BaseConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */