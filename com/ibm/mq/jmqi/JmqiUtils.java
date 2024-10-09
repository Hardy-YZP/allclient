/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.util.Cruise;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.InvalidAlgorithmParameterException;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.security.cert.CertStore;
/*      */ import java.security.cert.LDAPCertStoreParameters;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
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
/*      */ public class JmqiUtils
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiUtils.java";
/*      */   
/*      */   static {
/*   53 */     if (Trace.isOn) {
/*   54 */       Trace.data("com.ibm.mq.jmqi.JmqiUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiUtils.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   63 */   public static final String NL = JmqiTools.getNewline();
/*      */ 
/*      */   
/*      */   static CipherSuiteMapping[] mappingTable;
/*      */   
/*      */   public static final String protocol_SSLV3 = "SSLv3";
/*      */   
/*      */   public static final String protocol_TLS10 = "TLSv1";
/*      */   
/*      */   public static final String protocol_TLS12 = "TLSv1.2";
/*      */   
/*      */   public static final String protocol_TLS13 = "TLSv1.3";
/*      */ 
/*      */   
/*      */   private JmqiUtils(JmqiEnvironment env) {
/*   78 */     super(env);
/*   79 */     if (Trace.isOn) {
/*   80 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiUtils", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*   82 */     if (Trace.isOn) {
/*   83 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiUtils", "<init>(JmqiEnvironment)");
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
/*      */   @Cruise("Moved from RemoteHconn")
/*      */   public static Object loadAndInstantiateClass(JmqiEnvironment env, String className) throws JmqiException {
/*   99 */     if (Trace.isOn) {
/*  100 */       Trace.entry("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", new Object[] { className });
/*      */     }
/*      */     
/*  103 */     Class<?> acClass = null;
/*  104 */     ClassLoader loader = ClassLoader.getSystemClassLoader();
/*      */     try {
/*  106 */       acClass = loader.loadClass(className);
/*      */     }
/*  108 */     catch (ClassNotFoundException e) {
/*  109 */       if (Trace.isOn) {
/*  110 */         Trace.catchBlock("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", e, 1);
/*      */       }
/*      */ 
/*      */       
/*  114 */       JmqiException traceRet1 = new JmqiException(env, -1, null, 2, 2486, e);
/*      */       
/*  116 */       if (Trace.isOn) {
/*  117 */         Trace.throwing("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", traceRet1, 1);
/*      */       }
/*      */       
/*  120 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  126 */       Constructor<?> construct = acClass.getConstructor(new Class[0]);
/*      */       
/*  128 */       Object traceRet2 = construct.newInstance(new Object[] { "" });
/*  129 */       if (Trace.isOn) {
/*  130 */         Trace.exit("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", traceRet2);
/*      */       }
/*      */       
/*  133 */       return traceRet2;
/*      */     }
/*  135 */     catch (NoSuchMethodException e) {
/*  136 */       if (Trace.isOn) {
/*  137 */         Trace.catchBlock("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", e, 2);
/*      */       }
/*      */       
/*  140 */       JmqiException traceRet3 = new JmqiException(env, -1, null, 2, 2486, e);
/*      */       
/*  142 */       if (Trace.isOn) {
/*  143 */         Trace.throwing("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", traceRet3, 2);
/*      */       }
/*      */       
/*  146 */       throw traceRet3;
/*      */     
/*      */     }
/*  149 */     catch (IllegalAccessException e) {
/*  150 */       if (Trace.isOn) {
/*  151 */         Trace.catchBlock("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", e, 3);
/*      */       }
/*      */       
/*  154 */       JmqiException traceRet4 = new JmqiException(env, -1, null, 2, 2486, e);
/*      */       
/*  156 */       if (Trace.isOn) {
/*  157 */         Trace.throwing("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", traceRet4, 3);
/*      */       }
/*      */       
/*  160 */       throw traceRet4;
/*      */     
/*      */     }
/*  163 */     catch (InstantiationException e) {
/*  164 */       if (Trace.isOn) {
/*  165 */         Trace.catchBlock("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", e, 4);
/*      */       }
/*      */       
/*  168 */       JmqiException traceRet5 = new JmqiException(env, -1, null, 2, 2486, e);
/*      */       
/*  170 */       if (Trace.isOn) {
/*  171 */         Trace.throwing("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", traceRet5, 4);
/*      */       }
/*      */       
/*  174 */       throw traceRet5;
/*      */     
/*      */     }
/*  177 */     catch (Exception e) {
/*  178 */       if (Trace.isOn) {
/*  179 */         Trace.catchBlock("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", e, 5);
/*      */       }
/*      */       
/*  182 */       JmqiException traceRet6 = new JmqiException(env, -1, null, 2, 2486, e);
/*      */       
/*  184 */       if (Trace.isOn) {
/*  185 */         Trace.throwing("com.ibm.mq.jmqi.JmqiUils", "loadAndInstantiateClass(String)", traceRet6, 5);
/*      */       }
/*      */       
/*  188 */       throw traceRet6;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static String qmgrBytesToString(JmqiEnvironment env, Hconn hconn, byte[] charAttrs, int offset, int length) throws JmqiException, UnsupportedEncodingException {
/*  211 */     if (Trace.isOn) {
/*  212 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "qmgrBytesToString(JmqiEnvironment,Hconn,byte [ ],int,int)", new Object[] { env, hconn, charAttrs, 
/*      */             
/*  214 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*      */     }
/*  216 */     int ccsid = hconn.getCcsid();
/*  217 */     if (env instanceof JmqiSystemEnvironment) {
/*  218 */       String traceRet1; JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/*  219 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)sysenv, ccsid);
/*      */       
/*      */       try {
/*  222 */         traceRet1 = cp.bytesToString(charAttrs, offset, length).trim();
/*      */       }
/*  224 */       catch (CharacterCodingException e) {
/*  225 */         if (Trace.isOn) {
/*  226 */           Trace.catchBlock("com.ibm.mq.jmqi.JmqiUtils", "qmgrBytesToString(JmqiEnvironment,Hconn,byte [ ],int,int)", e);
/*      */         }
/*      */         
/*  229 */         UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.toString());
/*  230 */         if (Trace.isOn) {
/*  231 */           Trace.throwing("com.ibm.mq.jmqi.JmqiUtils", "qmgrBytesToString(JmqiEnvironment,Hconn,byte [ ],int,int)", traceRet2);
/*      */         }
/*      */         
/*  234 */         throw traceRet2;
/*      */       } 
/*  236 */       if (Trace.isOn) {
/*  237 */         Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "qmgrBytesToString(JmqiEnvironment,Hconn,byte [ ],int,int)", traceRet1, 1);
/*      */       }
/*      */       
/*  240 */       return traceRet1;
/*      */     } 
/*  242 */     if (Trace.isOn) {
/*  243 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "qmgrBytesToString(JmqiEnvironment,Hconn,byte [ ],int,int)", null, 2);
/*      */     }
/*      */     
/*  246 */     return null;
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
/*      */   public static byte[] stringToQmgrBytes(JmqiEnvironment env, Hconn hconn, String string, byte[] charAttrs, int offset, int length) throws JmqiException, UnsupportedEncodingException {
/*  263 */     if (Trace.isOn) {
/*  264 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", new Object[] { env, hconn, string, charAttrs, 
/*      */             
/*  266 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*      */     }
/*  268 */     int ccsid = hconn.getCcsid();
/*  269 */     if (env instanceof JmqiSystemEnvironment) {
/*  270 */       byte[] bytes; JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/*      */       
/*  272 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)sysenv, ccsid);
/*      */       
/*      */       try {
/*  275 */         bytes = cp.stringToBytes(string);
/*      */       }
/*  277 */       catch (CharacterCodingException e) {
/*  278 */         if (Trace.isOn) {
/*  279 */           Trace.catchBlock("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", e);
/*      */         }
/*      */         
/*  282 */         UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.toString());
/*  283 */         if (Trace.isOn) {
/*  284 */           Trace.throwing("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", traceRet2, 1);
/*      */         }
/*      */         
/*  287 */         throw traceRet2;
/*      */       } 
/*      */       
/*  290 */       if (bytes.length > length) {
/*  291 */         JmqiException traceRet1 = new JmqiException(env, -1, null, 2, 2005, null);
/*      */ 
/*      */         
/*  294 */         if (Trace.isOn) {
/*  295 */           Trace.throwing("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", traceRet1, 2);
/*      */         }
/*      */         
/*  298 */         throw traceRet1;
/*      */       } 
/*      */       
/*  301 */       if (offset + length > charAttrs.length) {
/*  302 */         JmqiException traceRet1 = new JmqiException(env, -1, null, 2, 2005, null);
/*  303 */         if (Trace.isOn) {
/*  304 */           Trace.throwing("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", traceRet1, 3);
/*      */         }
/*      */         
/*  307 */         throw traceRet1;
/*      */       } 
/*      */       int i;
/*  310 */       for (i = 0; i < bytes.length; i++) {
/*  311 */         charAttrs[offset + i] = bytes[i];
/*      */       }
/*  313 */       for (i = bytes.length; i < length; i++) {
/*  314 */         charAttrs[offset + i] = cp.spaceByte;
/*      */       }
/*      */       
/*  317 */       if (Trace.isOn) {
/*  318 */         Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", bytes, 1);
/*      */       }
/*      */       
/*  321 */       return bytes;
/*      */     } 
/*  323 */     if (Trace.isOn) {
/*  324 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "stringToQmgrBytes(JmqiEnvironment,Hconn,String,byte [ ],int,int)", null, 2);
/*      */     }
/*      */     
/*  327 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String arrayToHexString(byte[] array) {
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "arrayToHexString(byte [ ])", new Object[] { array });
/*      */     }
/*  340 */     String traceRet1 = JmqiTools.arrayToHexString(array);
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "arrayToHexString(byte [ ])", traceRet1);
/*      */     }
/*  344 */     return traceRet1;
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
/*      */   
/*      */   public static void hexDump(byte[] byteArray, ByteBuffer byteBuffer, int offset, int length, StringBuffer dumpBuffer) {
/*  364 */     if (Trace.isOn) {
/*  365 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "hexDump(byte [ ],ByteBuffer,int,int,StringBuffer)", new Object[] { byteArray, byteBuffer, 
/*  366 */             Integer.valueOf(offset), Integer.valueOf(length), dumpBuffer });
/*      */     }
/*      */     
/*  369 */     JmqiTools.hexDump(byteArray, byteBuffer, offset, length, dumpBuffer);
/*  370 */     if (Trace.isOn) {
/*  371 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "hexDump(byte [ ],ByteBuffer,int,int,StringBuffer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class CipherSuiteMapping
/*      */   {
/*      */     String suiteName;
/*      */ 
/*      */     
/*      */     String specName;
/*      */ 
/*      */     
/*      */     String[] aliases;
/*      */ 
/*      */     
/*      */     boolean FIPSCompliant;
/*      */     
/*      */     String protocol;
/*      */     
/*      */     boolean AliasCipher;
/*      */ 
/*      */     
/*      */     CipherSuiteMapping(String suiteName, String specName, String[] aliases, boolean FIPSCompliant, String protocol, boolean AliasCipher) {
/*  396 */       if (Trace.isOn) {
/*  397 */         Trace.entry(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "<init>(String,String,String [ ],boolean,String)", new Object[] { suiteName, specName, aliases, 
/*      */               
/*  399 */               Boolean.valueOf(FIPSCompliant), protocol, Boolean.valueOf(AliasCipher) });
/*      */       }
/*  401 */       this.suiteName = suiteName;
/*  402 */       this.specName = specName;
/*  403 */       this.aliases = aliases;
/*  404 */       this.FIPSCompliant = FIPSCompliant;
/*  405 */       this.protocol = protocol;
/*  406 */       this.AliasCipher = AliasCipher;
/*  407 */       if (Trace.isOn) {
/*  408 */         Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "<init>(String,String,String [ ],boolean,String,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  416 */       if (Trace.isOn) {
/*  417 */         Trace.entry(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "toString()");
/*      */       }
/*  419 */       String traceRet1 = "CipherSuiteMapping(\"" + this.suiteName + "\", \"" + this.specName + "\", " + ((this.aliases == null) ? "(String[])null" : ("new String[]{\"" + listAliases() + "\"}")) + ", " + this.FIPSCompliant + ", " + this.protocol + ")";
/*      */ 
/*      */       
/*  422 */       if (Trace.isOn) {
/*  423 */         Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "toString()", traceRet1);
/*      */       }
/*  425 */       return traceRet1;
/*      */     }
/*      */     
/*      */     private String listAliases() {
/*  429 */       if (Trace.isOn) {
/*  430 */         Trace.entry(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "listAliases()");
/*      */       }
/*      */       
/*  433 */       StringBuffer retval = new StringBuffer();
/*  434 */       boolean firstAlias = true;
/*  435 */       for (String alias : this.aliases) {
/*  436 */         if (firstAlias) {
/*  437 */           firstAlias = false;
/*      */         } else {
/*      */           
/*  440 */           retval.append(", ");
/*      */         } 
/*  442 */         retval.append("\"" + alias + "\"");
/*      */       } 
/*      */       
/*  445 */       String result = retval.toString();
/*      */       
/*  447 */       if (Trace.isOn) {
/*  448 */         Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "listAliases()", result);
/*      */       }
/*  450 */       return result;
/*      */     }
/*      */     
/*      */     boolean matchesSuite(String otherSuiteName) {
/*  454 */       if (Trace.isOn) {
/*  455 */         Trace.entry(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesSuite(String)", new Object[] { otherSuiteName });
/*      */       }
/*      */ 
/*      */       
/*  459 */       if (Trace.isOn) {
/*  460 */         Trace.data(this, "com.ibm.mq.jmqi.JmqiUtils.CipherSuiteMapping", "matchesSuite(String)", "this.suiteName", this.suiteName);
/*      */       }
/*      */ 
/*      */       
/*  464 */       boolean result = this.suiteName.equals(otherSuiteName);
/*      */       
/*  466 */       if (Trace.isOn) {
/*  467 */         Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesSuite(String)", 
/*  468 */             Boolean.valueOf(result));
/*      */       }
/*  470 */       return result;
/*      */     }
/*      */     
/*      */     boolean matchesSpec(String otherSpecName) {
/*  474 */       if (Trace.isOn) {
/*  475 */         Trace.entry(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesSpec(String)", new Object[] { otherSpecName });
/*      */       }
/*      */ 
/*      */       
/*  479 */       if (Trace.isOn) {
/*  480 */         Trace.data(this, "com.ibm.mq.jmqi.JmqiUtils.CipherSuiteMapping", "matchesSpec(String)", "this.specName", this.specName);
/*      */         
/*  482 */         Trace.data(this, "com.ibm.mq.jmqi.JmqiUtils.CipherSuiteMapping", "matchesSpec(String)", "this.aliases", 
/*  483 */             Arrays.toString((Object[])this.aliases));
/*      */       } 
/*      */ 
/*      */       
/*  487 */       if (this.specName.equals(otherSpecName)) {
/*  488 */         if (Trace.isOn) {
/*  489 */           Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesSpec(String)", 
/*  490 */               Boolean.valueOf(true), 1);
/*      */         }
/*  492 */         return true;
/*      */       } 
/*      */       
/*  495 */       if (this.aliases != null) {
/*  496 */         for (String alias : this.aliases) {
/*  497 */           if (alias.equals(otherSpecName)) {
/*  498 */             if (Trace.isOn) {
/*  499 */               Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesSpec(String)", 
/*  500 */                   Boolean.valueOf(true), 2);
/*      */             }
/*  502 */             return true;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  507 */       if (Trace.isOn) {
/*  508 */         Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesSpec(String)", 
/*  509 */             Boolean.valueOf(false), 3);
/*      */       }
/*  511 */       return false;
/*      */     }
/*      */     
/*      */     boolean matchesFips(boolean fipsRequired) {
/*  515 */       if (Trace.isOn) {
/*  516 */         Trace.entry(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesFips(boolean)", new Object[] {
/*  517 */               Boolean.valueOf(fipsRequired)
/*      */             });
/*      */       }
/*  520 */       if (!fipsRequired) {
/*      */         
/*  522 */         if (Trace.isOn) {
/*  523 */           Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesFips(boolean)", 
/*  524 */               Boolean.valueOf(true), 1);
/*      */         }
/*  526 */         return true;
/*      */       } 
/*      */       
/*  529 */       if (Trace.isOn) {
/*  530 */         Trace.exit(this, "com.ibm.mq.jmqi.CipherSuiteMapping", "matchesFips(boolean)", 
/*  531 */             Boolean.valueOf(this.FIPSCompliant), 2);
/*      */       }
/*  533 */       return this.FIPSCompliant;
/*      */     }
/*      */ 
/*      */     
/*      */     boolean isAliasCipher() {
/*  538 */       return this.AliasCipher;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean useIBMCipherMappings = true;
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean usingIBMMappings() {
/*  548 */     if (Trace.isOn) {
/*  549 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "usingIBMMappings()");
/*      */     }
/*      */     
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "usingIBMMappings()", 
/*  554 */           Boolean.valueOf(useIBMCipherMappings));
/*      */     }
/*  556 */     return useIBMCipherMappings;
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
/*  569 */   static CipherSuiteMapping[] IBMmappingTable = new CipherSuiteMapping[] { new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "ECDHE_ECDSA_3DES_EDE_CBC_SHA256", new String[] { "ECDHE_ECDSA_3DES_EDE_CBC_SHA" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "ECDHE_ECDSA_AES_128_CBC_SHA256", (String[])null, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "ECDHE_ECDSA_AES_128_GCM_SHA256", new String[] { "ECDHE_ECDSA_AES_128_GCM_AEAD" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "ECDHE_ECDSA_AES_256_CBC_SHA384", (String[])null, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "ECDHE_ECDSA_AES_256_GCM_SHA384", new String[] { "ECDHE_ECDSA_AES_256_GCM_AEAD" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_NULL_SHA", "ECDHE_ECDSA_NULL_SHA256", new String[] { "ECDHE_ECDSA_NULL_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_ECDSA_WITH_RC4_128_SHA", "ECDHE_ECDSA_RC4_128_SHA256", new String[] { "ECDHE_ECDSA_RC4_128_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "ECDHE_RSA_3DES_EDE_CBC_SHA256", new String[] { "ECDHE_RSA_3DES_EDE_CBC_SHA" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "ECDHE_RSA_AES_128_CBC_SHA256", (String[])null, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "ECDHE_RSA_AES_128_GCM_SHA256", new String[] { "ECDHE_RSA_AES_128_GCM_AEAD" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "ECDHE_RSA_AES_256_CBC_SHA384", (String[])null, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "ECDHE_RSA_AES_256_GCM_SHA384", new String[] { "ECDHE_RSA_AES_256_GCM_AEAD" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_NULL_SHA", "ECDHE_RSA_NULL_SHA256", new String[] { "ECDHE_RSA_NULL_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_ECDHE_RSA_WITH_RC4_128_SHA", "ECDHE_RSA_RC4_128_SHA256", new String[] { "ECDHE_RSA_RC4_128_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_EXPORT_WITH_RC4_40_MD5", "RC4_MD5_EXPORT", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_FIPS_WITH_3DES_EDE_CBC_SHA", "FIPS_WITH_3DES_EDE_CBC_SHA", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_FIPS_WITH_DES_CBC_SHA", "FIPS_WITH_DES_CBC_SHA", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", (String[])null, true, "TLSv1", false), new CipherSuiteMapping("SSL_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", (String[])null, true, "TLSv1", false), new CipherSuiteMapping("SSL_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_CBC_SHA256", (String[])null, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_128_GCM_SHA256", new String[] { "TLS_RSA_WITH_AES_128_GCM_AEAD" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", (String[])null, true, "TLSv1", false), new CipherSuiteMapping("SSL_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA256", (String[])null, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_AES_256_GCM_SHA384", new String[] { "TLS_RSA_WITH_AES_256_GCM_AEAD" }, true, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_DES_CBC_SHA", (String[])null, false, "TLSv1", false), new CipherSuiteMapping("SSL_RSA_WITH_NULL_MD5", "NULL_MD5", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_WITH_NULL_SHA", "NULL_SHA", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_WITH_NULL_SHA256", "TLS_RSA_WITH_NULL_SHA256", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_RC4_128_MD5", "RC4_MD5_US", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_WITH_RC4_128_SHA", "TLS_RSA_WITH_RC4_128_SHA256", new String[] { "TLS_RSA_WITH_RC4_128_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("*TLS12", "ANY_TLS12", (String[])null, true, "TLSv1.2", true), new CipherSuiteMapping("*TLS13", "ANY_TLS13", (String[])null, false, "TLSv1.3", true), new CipherSuiteMapping("*TLS12ORHIGHER", "ANY_TLS12_OR_HIGHER", (String[])null, true, "TLSv1.2", true), new CipherSuiteMapping("*TLS13ORHIGHER", "ANY_TLS13_OR_HIGHER", (String[])null, false, "TLSv1.3", true), new CipherSuiteMapping("*ANY", "ANY", (String[])null, true, "TLSv1.2", true), new CipherSuiteMapping("TLS_AES_128_GCM_SHA256", "TLS_AES_128_GCM_SHA256", new String[] { "TLS_AES_128_GCM_SHA256" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_AES_256_GCM_SHA384", "TLS_AES_256_GCM_SHA384", new String[] { "TLS_AES_256_GCM_SHA384" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_CHACHA20_POLY1305_SHA256", "TLS_CHACHA20_POLY1305_SHA256", new String[] { "TLS_CHACHA20_POLY1305_SHA256" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_AES_128_CCM_SHA256", "TLS_AES_128_CCM_SHA256", new String[] { "TLS_AES_128_CCM_SHA256" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_AES_128_CCM_8_SHA256", "TLS_AES_128_CCM_8_SHA256", new String[] { "TLS_AES_128_CCM_8_SHA256" }, false, "TLSv1.3", false) };
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  673 */   static CipherSuiteMapping[] OraclemappingTable = new CipherSuiteMapping[] { new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "ECDHE_ECDSA_3DES_EDE_CBC_SHA256", new String[] { "ECDHE_ECDSA_3DES_EDE_CBC_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "ECDHE_ECDSA_AES_128_CBC_SHA256", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "ECDHE_ECDSA_AES_128_GCM_SHA256", new String[] { "ECDHE_ECDSA_AES_128_GCM_AEAD" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "ECDHE_ECDSA_AES_256_CBC_SHA384", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "ECDHE_ECDSA_AES_256_GCM_SHA384", new String[] { "ECDHE_ECDSA_AES_256_GCM_AEAD" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_NULL_SHA", "ECDHE_ECDSA_NULL_SHA256", new String[] { "ECDHE_ECDSA_NULL_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", "ECDHE_ECDSA_RC4_128_SHA256", new String[] { "ECDHE_ECDSA_RC4_128_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "ECDHE_RSA_3DES_EDE_CBC_SHA256", new String[] { "ECDHE_RSA_3DES_EDE_CBC_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "ECDHE_RSA_AES_128_CBC_SHA256", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "ECDHE_RSA_AES_128_GCM_SHA256", new String[] { "ECDHE_RSA_AES_128_GCM_AEAD" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "ECDHE_RSA_AES_256_CBC_SHA384", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "ECDHE_RSA_AES_256_GCM_SHA384", new String[] { "ECDHE_RSA_AES_256_GCM_AEAD" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_NULL_SHA", "ECDHE_RSA_NULL_SHA256", new String[] { "ECDHE_RSA_NULL_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_ECDHE_RSA_WITH_RC4_128_SHA", "ECDHE_RSA_RC4_128_SHA256", new String[] { "ECDHE_RSA_RC4_128_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", (String[])null, false, "TLSv1", false), new CipherSuiteMapping("TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", (String[])null, false, "TLSv1", false), new CipherSuiteMapping("TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_CBC_SHA256", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_128_GCM_SHA256", new String[] { "TLS_RSA_WITH_AES_128_GCM_AEAD" }, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", (String[])null, false, "TLSv1", false), new CipherSuiteMapping("TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA256", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_AES_256_GCM_SHA384", new String[] { "TLS_RSA_WITH_AES_256_GCM_AEAD" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_DES_CBC_SHA", (String[])null, false, "TLSv1", false), new CipherSuiteMapping("TLS_RSA_WITH_NULL_SHA256", "TLS_RSA_WITH_NULL_SHA256", (String[])null, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_RC4_128_SHA", "TLS_RSA_WITH_RC4_128_SHA256", new String[] { "TLS_RSA_WITH_RC4_128_SHA" }, false, "TLSv1.2", false), new CipherSuiteMapping("SSL_RSA_WITH_NULL_MD5", "NULL_MD5", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_WITH_NULL_SHA", "NULL_SHA", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_EXPORT_WITH_RC4_40_MD5", "RC4_MD5_EXPORT", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("SSL_RSA_WITH_RC4_128_MD5", "RC4_MD5_US", (String[])null, false, "SSLv3", false), new CipherSuiteMapping("*TLS12", "ANY_TLS12", (String[])null, false, "TLSv1.2", true), new CipherSuiteMapping("*TLS13", "ANY_TLS13", (String[])null, false, "TLSv1.3", true), new CipherSuiteMapping("*TLS12ORHIGHER", "ANY_TLS12_OR_HIGHER", (String[])null, false, "TLSv1.2", true), new CipherSuiteMapping("*TLS13ORHIGHER", "ANY_TLS13_OR_HIGHER", (String[])null, false, "TLSv1.3", true), new CipherSuiteMapping("*ANY", "ANY", (String[])null, false, "TLSv1.2", true), new CipherSuiteMapping("TLS_AES_128_GCM_SHA256", "TLS_AES_128_GCM_SHA256", new String[] { "TLS_AES_128_GCM_SHA256" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_AES_256_GCM_SHA384", "TLS_AES_256_GCM_SHA384", new String[] { "TLS_AES_256_GCM_SHA384" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_CHACHA20_POLY1305_SHA256", "TLS_CHACHA20_POLY1305_SHA256", new String[] { "TLS_CHACHA20_POLY1305_SHA256" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_AES_128_CCM_SHA256", "TLS_AES_128_CCM_SHA256", new String[] { "TLS_AES_128_CCM_SHA256" }, false, "TLSv1.3", false), new CipherSuiteMapping("TLS_AES_128_CCM_8_SHA256", "TLS_AES_128_CCM_8_SHA256", new String[] { "TLS_AES_128_CCM_8_SHA256" }, false, "TLSv1.3", false) };
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
/*      */ 
/*      */   
/*      */   static {
/*  776 */     if (Trace.isOn) {
/*  777 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "static()");
/*      */     }
/*      */     
/*  780 */     String useIBMmappingsprop = AccessController.<String>doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  784 */             if (Trace.isOn) {
/*  785 */               Trace.entry(this, "com.ibm.mq.jmqi.JmqiUtils", "run()");
/*      */             }
/*      */             try {
/*  788 */               Object traceRet1 = System.getProperty("com.ibm.mq.cfg.useIBMCipherMappings", "true");
/*  789 */               if (Trace.isOn) {
/*  790 */                 Trace.exit(this, "com.ibm.mq.jmqi.null", "run()", traceRet1, 1);
/*      */               }
/*  792 */               return traceRet1;
/*      */             }
/*  794 */             catch (AccessControlException ace) {
/*  795 */               if (Trace.isOn) {
/*  796 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.null", "run()", ace);
/*      */               }
/*  798 */               if (Trace.isOn) {
/*  799 */                 Trace.exit(this, "com.ibm.mq.jmqi.null", "run()", null, 2);
/*      */               }
/*  801 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*  805 */     if (useIBMmappingsprop != null && useIBMmappingsprop.equalsIgnoreCase("false")) {
/*  806 */       useIBMCipherMappings = false;
/*  807 */       mappingTable = OraclemappingTable;
/*      */     } else {
/*      */       
/*  810 */       useIBMCipherMappings = true;
/*  811 */       mappingTable = IBMmappingTable;
/*      */     } 
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection<String> getCipherSpecs(boolean fipsRequired) {
/*  825 */     if (Trace.isOn) {
/*  826 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "getCipherSpecs(boolean)", new Object[] {
/*  827 */             Boolean.valueOf(fipsRequired)
/*      */           });
/*      */     }
/*  830 */     ArrayList<String> list = new ArrayList<>();
/*      */     
/*  832 */     for (CipherSuiteMapping mapping : mappingTable) {
/*  833 */       if (mapping.matchesFips(fipsRequired) && 
/*  834 */         mapping.specName != null) {
/*  835 */         list.add(mapping.specName);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  840 */     if (Trace.isOn) {
/*  841 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "getCipherSpecs(boolean)", list);
/*      */     }
/*  843 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toCipherSpec(String suiteName, boolean fipsRequired) {
/*  854 */     if (Trace.isOn) {
/*  855 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "toCipherSpec(String,boolean)", new Object[] { suiteName, 
/*  856 */             Boolean.valueOf(fipsRequired) });
/*      */     }
/*      */     
/*  859 */     String trimmedSuiteName = suiteName.trim();
/*  860 */     String specName = null;
/*  861 */     for (CipherSuiteMapping mapping : mappingTable) {
/*  862 */       if (mapping.matchesSuite(trimmedSuiteName) && 
/*  863 */         mapping.matchesFips(fipsRequired)) {
/*  864 */         specName = mapping.specName;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/*  870 */     if (Trace.isOn) {
/*  871 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "toCipherSpec(String,boolean)", specName);
/*      */     }
/*  873 */     return specName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toCipherSpec(String suiteName) {
/*  883 */     if (Trace.isOn) {
/*  884 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "toCipherSpec(String)", new Object[] { suiteName });
/*      */     }
/*      */     
/*  887 */     String specName = toCipherSuite(suiteName, false);
/*  888 */     if (specName == null) {
/*  889 */       specName = toCipherSuite(suiteName, true);
/*      */     }
/*      */     
/*  892 */     if (Trace.isOn) {
/*  893 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "toCipherSpec(String)", specName);
/*      */     }
/*  895 */     return specName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFipsCompatible(String suiteName) {
/*  904 */     if (Trace.isOn) {
/*  905 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "isFipsCompatible(String)", new Object[] { suiteName });
/*      */     }
/*      */ 
/*      */     
/*  909 */     String trimmedSuiteName = suiteName.trim();
/*  910 */     boolean result = false;
/*  911 */     for (CipherSuiteMapping mapping : mappingTable) {
/*  912 */       if (mapping.matchesSuite(trimmedSuiteName)) {
/*  913 */         result = mapping.FIPSCompliant;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "isFipsCompatible(String)", Boolean.valueOf(result));
/*      */     }
/*  921 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toCipherSuite(String specName, boolean fipsRequired) {
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "toCipherSuite(String,boolean)", new Object[] { specName, 
/*  934 */             Boolean.valueOf(fipsRequired) });
/*      */     }
/*      */     
/*  937 */     String trimmedSpecName = specName.trim();
/*  938 */     String suiteName = null;
/*  939 */     for (CipherSuiteMapping mapping : mappingTable) {
/*  940 */       if (mapping.matchesSpec(trimmedSpecName) && mapping.matchesFips(fipsRequired)) {
/*  941 */         suiteName = mapping.suiteName;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  946 */     if (Trace.isOn) {
/*  947 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "toCipherSuite(String,boolean)", suiteName);
/*      */     }
/*  949 */     return suiteName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toCipherSuite(String specName) {
/*  959 */     if (Trace.isOn) {
/*  960 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "toCipherSuite(String)", new Object[] { specName });
/*      */     }
/*      */     
/*  963 */     String suiteName = toCipherSuite(specName, false);
/*  964 */     if (suiteName == null) {
/*  965 */       suiteName = toCipherSuite(specName, true);
/*      */     }
/*      */     
/*  968 */     if (Trace.isOn) {
/*  969 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "toCipherSuite(String)", suiteName);
/*      */     }
/*  971 */     return suiteName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAliasCipherSuite(String suiteName) {
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "isAliasCipherSuite(String)", new Object[] { suiteName });
/*      */     }
/*      */     
/*  985 */     boolean returnVal = false;
/*      */     
/*  987 */     for (CipherSuiteMapping mapping : mappingTable) {
/*      */       
/*  989 */       if (mapping.isAliasCipher() && mapping.suiteName.equalsIgnoreCase(suiteName)) {
/*      */         
/*  991 */         returnVal = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "isAliasCipherSuite(String)", Boolean.valueOf(returnVal));
/*      */     }
/*  999 */     return returnVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAliasCipherSpec(String specName) {
/* 1009 */     if (Trace.isOn) {
/* 1010 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "isAliasCipherSpec(String)", new Object[] { specName });
/*      */     }
/*      */     
/* 1013 */     boolean returnVal = false;
/*      */     
/* 1015 */     for (CipherSuiteMapping mapping : mappingTable) {
/*      */       
/* 1017 */       if (mapping.isAliasCipher() && mapping.specName.equalsIgnoreCase(specName)) {
/*      */         
/* 1019 */         returnVal = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1024 */     if (Trace.isOn) {
/* 1025 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "isAliasCipherSpec(String)", Boolean.valueOf(returnVal));
/*      */     }
/* 1027 */     return returnVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] aliasCipherSuitetoCipherSuiteList(String aliasSuite, boolean tls13Allowed) {
/* 1038 */     if (Trace.isOn) {
/* 1039 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "aliasCipherSuitetoCipherSuiteList(String, boolean)", new Object[] { aliasSuite, Boolean.valueOf(tls13Allowed) });
/*      */     }
/* 1041 */     String[] returnVal = new String[0];
/* 1042 */     String[] protocols = ProtocolList.getProtocolList(aliasSuite, tls13Allowed);
/*      */     
/* 1044 */     if (protocols.length > 0)
/*      */     {
/* 1046 */       returnVal = protocolToCipherSuiteList(protocols);
/*      */     }
/* 1048 */     if (Trace.isOn) {
/* 1049 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "aliasCipherSuitetoCipherSuiteList(String)", returnVal);
/*      */     }
/* 1051 */     return returnVal;
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
/*      */   public static String[] aliasCipherSuitetoCipherSuiteList(String aliasSuite, boolean fipsRequired, boolean tls13Allowed) {
/* 1063 */     if (Trace.isOn) {
/* 1064 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "aliasCipherSuitetoCipherSuiteList(String, boolean, boolean)", new Object[] { aliasSuite, Boolean.valueOf(fipsRequired), Boolean.valueOf(tls13Allowed) });
/*      */     }
/* 1066 */     String[] returnVal = new String[0];
/* 1067 */     String[] protocols = ProtocolList.getProtocolList(aliasSuite, tls13Allowed);
/*      */     
/* 1069 */     if (protocols.length > 0)
/*      */     {
/* 1071 */       returnVal = protocolToCipherSuiteList(protocols, fipsRequired);
/*      */     }
/* 1073 */     if (Trace.isOn) {
/* 1074 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "aliasCipherSuitetoCipherSuiteList(String)", returnVal);
/*      */     }
/* 1076 */     return returnVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] protocolToCipherSuiteList(String[] protocols, boolean fipsRequired) {
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String[],boolean)", new Object[] { protocols, 
/* 1089 */             Boolean.valueOf(fipsRequired) });
/*      */     }
/*      */     
/* 1092 */     List<String> returnList = new ArrayList<>();
/*      */     
/* 1094 */     for (String protocol : protocols) {
/* 1095 */       String trimmedProtocol = protocol.trim();
/* 1096 */       for (CipherSuiteMapping mapping : mappingTable) {
/*      */         
/* 1098 */         if (mapping.protocol.equalsIgnoreCase(trimmedProtocol) && mapping.matchesFips(fipsRequired) && !mapping.isAliasCipher())
/*      */         {
/* 1100 */           if (!returnList.contains(mapping.suiteName)) {
/* 1101 */             returnList.add(mapping.suiteName);
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/* 1106 */     if (Trace.isOn) {
/* 1107 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String[],boolean)", returnList.toArray(new String[0]));
/*      */     }
/* 1109 */     return returnList.<String>toArray(new String[0]);
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
/*      */   public static String[] protocolToCipherSuiteList(String protocol, boolean fipsRequired) {
/* 1121 */     if (Trace.isOn) {
/* 1122 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String,boolean)", new Object[] { protocol, 
/* 1123 */             Boolean.valueOf(fipsRequired) });
/*      */     }
/*      */     
/* 1126 */     String trimmedProtocol = protocol.trim();
/* 1127 */     List<String> returnList = new ArrayList<>();
/* 1128 */     for (CipherSuiteMapping mapping : mappingTable) {
/*      */       
/* 1130 */       if (mapping.protocol.equalsIgnoreCase(trimmedProtocol) && mapping.matchesFips(fipsRequired) && !mapping.isAliasCipher())
/*      */       {
/* 1132 */         if (!returnList.contains(mapping.suiteName)) {
/* 1133 */           returnList.add(mapping.suiteName);
/*      */         }
/*      */       }
/*      */     } 
/* 1137 */     if (Trace.isOn) {
/* 1138 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String,boolean)", returnList.toArray(new String[0]));
/*      */     }
/* 1140 */     return returnList.<String>toArray(new String[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] protocolToCipherSuiteList(String[] protocols) {
/* 1150 */     if (Trace.isOn) {
/* 1151 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String)", new Object[] { protocols });
/*      */     }
/*      */     
/* 1154 */     List<String> returnList = new ArrayList<>();
/*      */     
/* 1156 */     for (String protocol : protocols) {
/* 1157 */       String trimmedProtocol = protocol.trim();
/* 1158 */       for (CipherSuiteMapping mapping : mappingTable) {
/*      */         
/* 1160 */         if (mapping.protocol.equalsIgnoreCase(trimmedProtocol) && !mapping.isAliasCipher())
/*      */         {
/* 1162 */           if (!returnList.contains(mapping.suiteName)) {
/* 1163 */             returnList.add(mapping.suiteName);
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/* 1168 */     if (Trace.isOn) {
/* 1169 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String)", returnList.toArray(new String[0]));
/*      */     }
/* 1171 */     return returnList.<String>toArray(new String[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] protocolToCipherSuiteList(String protocol) {
/* 1181 */     if (Trace.isOn) {
/* 1182 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String)", new Object[] { protocol });
/*      */     }
/*      */     
/* 1185 */     String trimmedProtocol = protocol.trim();
/* 1186 */     List<String> returnList = new ArrayList<>();
/* 1187 */     for (CipherSuiteMapping mapping : mappingTable) {
/*      */       
/* 1189 */       if (mapping.protocol.equalsIgnoreCase(trimmedProtocol) && !mapping.isAliasCipher())
/*      */       {
/* 1191 */         if (!returnList.contains(mapping.suiteName)) {
/* 1192 */           returnList.add(mapping.suiteName);
/*      */         }
/*      */       }
/*      */     } 
/* 1196 */     if (Trace.isOn) {
/* 1197 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "protocolToCipherSuiteList(String)", returnList.toArray(new String[0]));
/*      */     }
/* 1199 */     return returnList.<String>toArray(new String[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getProtocol(String specName) {
/* 1209 */     if (Trace.isOn) {
/* 1210 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "getProtocol(String)", new Object[] { specName });
/*      */     }
/*      */     
/* 1213 */     String trimmedSpecName = specName.trim();
/* 1214 */     String protocol = null;
/* 1215 */     for (CipherSuiteMapping mapping : mappingTable) {
/* 1216 */       if (mapping.matchesSpec(trimmedSpecName)) {
/* 1217 */         protocol = mapping.protocol;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1222 */     if (Trace.isOn) {
/* 1223 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "getProtocol(String)", protocol);
/*      */     }
/* 1225 */     return protocol;
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
/*      */   public static Collection<CertStore> getCertStoreCollectionFromSpaceSeperatedString(String string) throws Exception {
/* 1241 */     if (Trace.isOn) {
/* 1242 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "getCertStoreCollectionFromSpaceSeperatedString(String)", new Object[] { string });
/*      */     }
/*      */ 
/*      */     
/* 1246 */     List<CertStore> list = null;
/* 1247 */     Exception firstEx = null;
/*      */     
/* 1249 */     if (string != null) {
/* 1250 */       String[] words = string.split(" ");
/*      */       
/* 1252 */       if (words.length > 0) {
/*      */         
/* 1254 */         list = new ArrayList<>();
/* 1255 */         for (String word : words) {
/*      */ 
/*      */           
/*      */           try {
/*      */             
/* 1260 */             CertStore certStore = getCertStoreFromString(word);
/* 1261 */             list.add(certStore);
/*      */           }
/* 1263 */           catch (Exception e) {
/* 1264 */             if (Trace.isOn) {
/* 1265 */               Trace.catchBlock("com.ibm.mq.jmqi.JmqiUtils", "getCertStoreCollectionFromSpaceSeperatedString(String)", e);
/*      */             }
/*      */             
/* 1268 */             if (firstEx == null) {
/* 1269 */               firstEx = e;
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1276 */         if (list.isEmpty() && firstEx != null) {
/* 1277 */           if (Trace.isOn) {
/* 1278 */             Trace.throwing("com.ibm.mq.jmqi.JmqiUtils", "getCertStoreCollectionFromSpaceSeperatedString(String)", firstEx);
/*      */           }
/*      */           
/* 1281 */           throw firstEx;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1286 */     Collection<CertStore> traceRet1 = (list != null && list.isEmpty()) ? null : list;
/* 1287 */     if (Trace.isOn) {
/* 1288 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "getCertStoreCollectionFromSpaceSeperatedString(String)", traceRet1);
/*      */     }
/*      */     
/* 1291 */     return traceRet1;
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
/*      */   private static CertStore getCertStoreFromString(String uri) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
/*      */     String serverName;
/* 1304 */     if (Trace.isOn) {
/* 1305 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "getCertStoreFromString(String)", new Object[] { uri });
/*      */     }
/*      */ 
/*      */     
/* 1309 */     CertStore certStore = null;
/* 1310 */     int port = 389;
/*      */ 
/*      */ 
/*      */     
/*      */     int portpos;
/*      */ 
/*      */ 
/*      */     
/* 1318 */     if ((portpos = uri.indexOf(':', 7)) != -1) {
/* 1319 */       serverName = uri.substring(7, portpos);
/* 1320 */       port = Integer.parseInt(uri.substring(portpos + 1));
/*      */     } else {
/*      */       
/* 1323 */       serverName = uri.substring(7);
/*      */     } 
/*      */ 
/*      */     
/* 1327 */     LDAPCertStoreParameters params = new LDAPCertStoreParameters(serverName, port);
/*      */     
/* 1329 */     certStore = CertStore.getInstance("LDAP", params);
/*      */     
/* 1331 */     if (Trace.isOn) {
/* 1332 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "getCertStoreFromString(String)", certStore);
/*      */     }
/* 1334 */     return certStore;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void debug(StringBuffer sb, MQCBC mqcbc) {
/* 1342 */     if (Trace.isOn) {
/* 1343 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,MQCBC)", new Object[] { sb, mqcbc });
/*      */     }
/*      */ 
/*      */     
/* 1347 */     sb.append(" mqcbc:");
/* 1348 */     if (mqcbc == null) {
/* 1349 */       sb.append("null");
/*      */     } else {
/*      */       
/* 1352 */       sb.append(Integer.toHexString(System.identityHashCode(mqcbc)));
/* 1353 */       sb.append(":[CallType:");
/* 1354 */       sb.append(mqcbc.getCallType());
/* 1355 */       sb.append(" CompCode:");
/* 1356 */       sb.append(mqcbc.getCompCode());
/* 1357 */       sb.append(" Reason:");
/* 1358 */       sb.append(mqcbc.getReason());
/* 1359 */       sb.append(" BufferLength:");
/* 1360 */       sb.append(mqcbc.getBufferLength());
/* 1361 */       sb.append(" DataLength:");
/* 1362 */       sb.append(mqcbc.getDataLength());
/* 1363 */       sb.append("]");
/*      */     } 
/* 1365 */     sb.append(NL);
/* 1366 */     if (Trace.isOn) {
/* 1367 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,MQCBC)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void debug(StringBuffer sb, MQGMO mqgmo) {
/* 1377 */     if (Trace.isOn) {
/* 1378 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,MQGMO)", new Object[] { sb, mqgmo });
/*      */     }
/*      */ 
/*      */     
/* 1382 */     sb.append(" mqgmo:");
/* 1383 */     if (mqgmo == null) {
/* 1384 */       sb.append("null");
/*      */     } else {
/*      */       
/* 1387 */       sb.append(Integer.toHexString(System.identityHashCode(mqgmo)));
/* 1388 */       sb.append(":[Signal2:");
/* 1389 */       sb.append(mqgmo.getSignal2());
/* 1390 */       sb.append(" MsgToken:");
/* 1391 */       sb.append(arrayToHexString(mqgmo.getMsgToken()));
/* 1392 */       sb.append("]");
/*      */     } 
/* 1394 */     sb.append(NL);
/* 1395 */     if (Trace.isOn) {
/* 1396 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,MQGMO)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void debug(StringBuffer sb, String title, ByteBuffer pBuffer) {
/* 1407 */     if (Trace.isOn) {
/* 1408 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,String,ByteBuffer)", new Object[] { sb, title, pBuffer });
/*      */     }
/*      */ 
/*      */     
/* 1412 */     sb.append(" ");
/* 1413 */     sb.append(title);
/* 1414 */     sb.append(":");
/* 1415 */     if (pBuffer == null) {
/* 1416 */       sb.append("null");
/*      */     } else {
/*      */       
/* 1419 */       sb.append(Integer.toHexString(System.identityHashCode(pBuffer)));
/* 1420 */       sb.append(":[capacity:");
/* 1421 */       sb.append(pBuffer.capacity());
/* 1422 */       sb.append(": limit:");
/* 1423 */       sb.append(pBuffer.limit());
/* 1424 */       sb.append("]");
/* 1425 */       sb.append(NL);
/*      */       
/* 1427 */       hexDump(null, pBuffer, 0, pBuffer.limit(), sb);
/*      */     } 
/* 1429 */     sb.append(NL);
/* 1430 */     if (Trace.isOn) {
/* 1431 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,String,ByteBuffer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void debug(StringBuffer sb, String title, byte[] array) {
/* 1442 */     if (Trace.isOn) {
/* 1443 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,String,byte [ ])", new Object[] { sb, title, array });
/*      */     }
/*      */ 
/*      */     
/* 1447 */     sb.append(" ");
/* 1448 */     sb.append(title);
/* 1449 */     sb.append(":");
/* 1450 */     if (array == null) {
/* 1451 */       sb.append("null");
/*      */     } else {
/*      */       
/* 1454 */       sb.append(Integer.toHexString(System.identityHashCode(array)));
/* 1455 */       sb.append(":[length:");
/* 1456 */       sb.append(array.length);
/* 1457 */       sb.append("]");
/* 1458 */       sb.append(NL);
/*      */       
/* 1460 */       hexDump(array, null, 0, array.length, sb);
/*      */     } 
/* 1462 */     sb.append(NL);
/* 1463 */     if (Trace.isOn) {
/* 1464 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "debug(StringBuffer,String,byte [ ])");
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
/*      */   public static void debug(String title, Object pBuffer, Object mqgmo, Object mqcbc) {
/* 1477 */     if (Trace.isOn) {
/* 1478 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "debug(String,Object,Object,Object)", new Object[] { title, pBuffer, mqgmo, mqcbc });
/*      */     }
/*      */     
/* 1481 */     StringBuffer sb = new StringBuffer();
/* 1482 */     sb.append(title);
/* 1483 */     sb.append(NL);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1488 */     if (mqcbc instanceof MQCBC) {
/* 1489 */       debug(sb, (MQCBC)mqcbc);
/*      */     }
/* 1491 */     else if (mqcbc instanceof byte[]) {
/* 1492 */       debug(sb, "mqcbc", (byte[])mqcbc);
/*      */     }
/* 1494 */     else if (mqcbc instanceof ByteBuffer) {
/* 1495 */       debug(sb, "mqcbc", (ByteBuffer)mqcbc);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1501 */     if (mqgmo instanceof MQGMO) {
/* 1502 */       debug(sb, (MQGMO)mqgmo);
/*      */     }
/* 1504 */     else if (mqgmo instanceof byte[]) {
/* 1505 */       debug(sb, "mqgmo", (byte[])mqgmo);
/*      */     }
/* 1507 */     else if (mqgmo instanceof ByteBuffer) {
/* 1508 */       debug(sb, "mqgmo", (ByteBuffer)mqgmo);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1514 */     if (pBuffer instanceof byte[]) {
/* 1515 */       debug(sb, "pBuffer", (byte[])pBuffer);
/*      */     }
/* 1517 */     else if (pBuffer instanceof ByteBuffer) {
/* 1518 */       debug(sb, "pBuffer", (ByteBuffer)pBuffer);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1524 */     System.err.println(sb.toString());
/* 1525 */     if (Trace.isOn) {
/* 1526 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "debug(String,Object,Object,Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] arg) {
/* 1536 */     if (Trace.isOn) {
/* 1537 */       Trace.entry("com.ibm.mq.jmqi.JmqiUtils", "main(String [ ])", new Object[] { arg });
/*      */     }
/* 1539 */     System.out.println("<html>");
/* 1540 */     System.out.println("<body>");
/* 1541 */     System.out.println("<h1>");
/* 1542 */     System.out.println("SSL/TLS CipherSpecs and CipherSuites in IBM MQ Classes for Java and JMS");
/* 1543 */     System.out.println("</h1>");
/* 1544 */     System.out.println("<table cellpadding=\"4\" cellspacing=\"0\" summary=\"\" class=\"table\" width=\"100%\" rules=\"all\" frame=\"border\" border=\"1\">");
/* 1545 */     System.out.println("<caption>Table 1. CipherSpecs supported by IBM MQ and their equivalent CipherSuites</caption>");
/* 1546 */     System.out.println("<thead align=\"left\">");
/* 1547 */     System.out.println("<tr>");
/* 1548 */     System.out.print("<th valign=\"top\">CipherSpec(s)</th>");
/* 1549 */     System.out.print("<th>Equivalent CipherSuite</th>");
/* 1550 */     System.out.print("<th>Protocol</th>");
/* 1551 */     System.out.println("<th>FIPS-1402 compatible?</th>");
/* 1552 */     System.out.println("</tr>");
/* 1553 */     System.out.println("</thead>");
/* 1554 */     for (CipherSuiteMapping mapping : mappingTable) {
/* 1555 */       System.out.println("<tr>");
/* 1556 */       System.out.print("<td>");
/* 1557 */       System.out.print(mapping.specName);
/* 1558 */       if (mapping.aliases != null) {
/* 1559 */         for (String alias : mapping.aliases) {
/* 1560 */           System.out.format("<br>%s", new Object[] { alias });
/*      */         } 
/*      */       }
/* 1563 */       System.out.print("</td>");
/* 1564 */       System.out.format("<td>%s</td><td>%s</td><td>%b</td>%n", new Object[] { mapping.suiteName, mapping.protocol, 
/* 1565 */             Boolean.valueOf(mapping.FIPSCompliant) });
/* 1566 */       System.out.println("<tr>");
/*      */     } 
/* 1568 */     System.out.println("</table>");
/* 1569 */     System.out.println("</body>");
/* 1570 */     System.out.println("</html>");
/* 1571 */     if (Trace.isOn) {
/* 1572 */       Trace.exit("com.ibm.mq.jmqi.JmqiUtils", "main(String [ ])");
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ProtocolList
/*      */   {
/*      */     String[] basicList;
/*      */     String[] TLS13List;
/*      */     
/*      */     public static String[] getProtocolList(String cipherSpec, boolean isTLS13) {
/* 1582 */       if (Trace.isOn) {
/* 1583 */         Trace.entry(null, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ProtocolList", "getProtocolList(String, boolean)", new Object[] { cipherSpec, 
/* 1584 */               Boolean.valueOf(isTLS13) });
/*      */       }
/* 1586 */       String[] traceRet1 = new String[0];
/* 1587 */       ProtocolList protocolList = suiteToProtocolMappings.get(cipherSpec);
/* 1588 */       if (protocolList != null) {
/* 1589 */         traceRet1 = protocolList.getList(isTLS13);
/*      */       }
/* 1591 */       if (Trace.isOn) {
/* 1592 */         Trace.exit(null, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ProtocolList", "getProtocolList(String, boolean)", traceRet1);
/*      */       }
/* 1594 */       return traceRet1;
/*      */     }
/*      */     
/*      */     public String[] getList(boolean isTLS13) {
/* 1598 */       return isTLS13 ? this.TLS13List : this.basicList;
/*      */     }
/*      */ 
/*      */     
/*      */     protected ProtocolList(String[] basicList, String[] TLS13List) {
/* 1603 */       this.basicList = basicList;
/* 1604 */       this.TLS13List = TLS13List;
/*      */     }
/*      */ 
/*      */     
/*      */     protected ProtocolList(String[] basicList) {
/* 1609 */       this.basicList = basicList;
/* 1610 */       this.TLS13List = basicList;
/*      */     }
/*      */     
/* 1613 */     protected static HashMap<String, ProtocolList> suiteToProtocolMappings = new HashMap<>();
/*      */     static {
/* 1615 */       suiteToProtocolMappings.put("*TLS12", new ProtocolList(new String[] { "TLSv1.2" }));
/* 1616 */       suiteToProtocolMappings.put("*TLS13", new ProtocolList(new String[0], new String[] { "TLSv1.3" }));
/* 1617 */       suiteToProtocolMappings.put("*TLS12ORHIGHER", new ProtocolList(new String[] { "TLSv1.2" }, new String[] { "TLSv1.3", "TLSv1.2" }));
/* 1618 */       suiteToProtocolMappings.put("*TLS13ORHIGHER", new ProtocolList(new String[0], new String[] { "TLSv1.3" }));
/* 1619 */       suiteToProtocolMappings.put("*ANY", new ProtocolList(new String[] { "TLSv1.2", "TLSv1", "SSLv3" }, new String[] { "TLSv1.3", "TLSv1.2", "TLSv1", "SSLv3" }));
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */