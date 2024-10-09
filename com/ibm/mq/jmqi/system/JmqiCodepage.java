/*      */ package com.ibm.mq.jmqi.system;
/*      */ 
/*      */ import com.ibm.mq.jmqi.CustomCharsetProvider;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.msg.client.commonservices.trace.DumpableComponent;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.CharsetDecoder;
/*      */ import java.nio.charset.CharsetEncoder;
/*      */ import java.nio.charset.CodingErrorAction;
/*      */ import java.nio.charset.UnsupportedCharsetException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.TreeMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiCodepage
/*      */   implements Cloneable
/*      */ {
/*      */   private static JmqiCodepageFactory factory;
/*      */   private int ccsid;
/*      */   public String charsetId;
/*      */   public String[] charsetAliases;
/*      */   public byte spaceByte;
/*      */   public boolean isAscii;
/*      */   public Charset charset;
/*      */   private int[] ccsids;
/*      */   protected CodingErrorAction unmappableAction;
/*      */   protected byte[] unmappableReplacement;
/*      */   protected boolean is16Bit;
/*      */   private JmqiCodepage nextInList;
/*      */   
/*      */   private static class JmqiCodepageFactory
/*      */     implements DumpableComponent
/*      */   {
/*   77 */     private Map<Integer, JmqiCodepage> byCcsid = new TreeMap<>();
/*   78 */     private Map<String, JmqiCodepage> byCharset = new TreeMap<>();
/*      */ 
/*      */ 
/*      */     
/*      */     private static class JmqiCodepageFactoryMapLock
/*      */     {
/*      */       private JmqiCodepageFactoryMapLock() {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*   89 */     private JmqiCodepageFactoryMapLock jmqiCodepageFactoryMapLock = new JmqiCodepageFactoryMapLock();
/*      */     
/*   91 */     private static String infileName = "META-INF/ccsid_merged.map";
/*      */     
/*   93 */     private CustomCharsetProvider provider = new CustomCharsetProvider();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     JmqiCodepageFactory() throws IOException {
/*  106 */       Trace.registerDumpableComponent(this);
/*      */       
/*  108 */       if (Trace.isOn) {
/*  109 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiCodepageFactory", "<init>");
/*      */       }
/*      */ 
/*      */       
/*  113 */       InputStream is = AccessController.<InputStream>doPrivileged(new PrivilegedAction<InputStream>()
/*      */           {
/*      */             
/*      */             public InputStream run()
/*      */             {
/*  118 */               InputStream resource = getClass().getClassLoader().getResourceAsStream(JmqiCodepage.JmqiCodepageFactory.infileName);
/*      */ 
/*      */               
/*  121 */               if (resource == null) {
/*  122 */                 ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/*  123 */                 resource = threadContextClassloader.getResourceAsStream(JmqiCodepage.JmqiCodepageFactory.infileName);
/*      */               } 
/*      */ 
/*      */               
/*  127 */               if (resource == null) {
/*  128 */                 resource = ClassLoader.getSystemResourceAsStream(JmqiCodepage.JmqiCodepageFactory.infileName);
/*      */               }
/*  130 */               return resource;
/*      */             }
/*      */           });
/*      */ 
/*      */       
/*  135 */       if (is == null) {
/*  136 */         FileNotFoundException traceRet1 = new FileNotFoundException(infileName);
/*  137 */         throw traceRet1;
/*      */       } 
/*      */       
/*  140 */       try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"))) {
/*      */         while (true) {
/*  142 */           String inLine = reader.readLine();
/*  143 */           if (inLine == null) {
/*      */             break;
/*      */           }
/*      */           
/*  147 */           CcsidDefinition def = CcsidDefinition.fromString(inLine);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  152 */           JmqiCodepage cp = new JmqiCodepage(def.ccsids()[0], def.ccsids(), null, def.aliases()[0], def.aliases(), (byte)0, def.isAscii(), def.is16Bit(), JmqiCodepage.defaultAction, JmqiCodepage.defaultReplacement);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  157 */           for (String name : def.aliases()) {
/*  158 */             this.byCharset.put(name.toUpperCase(), cp);
/*      */           }
/*  160 */           for (int ccsid : def.ccsids()) {
/*  161 */             this.byCcsid.put(Integer.valueOf(ccsid), cp);
/*      */           }
/*      */         } 
/*      */         
/*  165 */         if (!this.byCcsid.containsKey(Integer.valueOf(0))) {
/*  166 */           JmqiCodepage defaultCp = this.byCharset.get(Charset.defaultCharset().name().toUpperCase());
/*  167 */           if (defaultCp != null) {
/*  168 */             this.byCcsid.put(Integer.valueOf(0), defaultCp);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  174 */       if (Trace.isOn) {
/*  175 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiCodepageFactory", "<init>");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     JmqiCodepage getByCcsid(JmqiEnvironment env, int ccsid) {
/*      */       JmqiCodepage traceRet1;
/*  186 */       if (Trace.isOn) {
/*  187 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiCodepageFactory", "getByCcsid(JmqiEnvironment,int)", new Object[] {
/*  188 */               Integer.valueOf(ccsid)
/*      */             });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  195 */       synchronized (this.jmqiCodepageFactoryMapLock) {
/*  196 */         traceRet1 = this.byCcsid.get(Integer.valueOf(ccsid));
/*      */ 
/*      */         
/*  199 */         if (traceRet1 != null && traceRet1.spaceByte == 0) {
/*  200 */           if (Trace.isOn) {
/*  201 */             Trace.data(this, "getByCcsid(JmqiEnvironment,int)", "Initializing codepage for CCSID: " + ccsid);
/*      */           }
/*      */           
/*  204 */           traceRet1 = initializeCodepage(traceRet1);
/*      */         } 
/*      */       } 
/*      */       
/*  208 */       if (Trace.isOn) {
/*  209 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiCodepageFactory", "getByCcsid(JmqiEnvironment,int)", traceRet1);
/*      */       }
/*      */ 
/*      */       
/*  213 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     JmqiCodepage getByName(JmqiEnvironment env, String name) {
/*      */       JmqiCodepage traceRet1;
/*  221 */       if (Trace.isOn) {
/*  222 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiCodepageFactory", "getByName(JmqiEnvironment,String)", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  230 */       synchronized (this.jmqiCodepageFactoryMapLock) {
/*  231 */         traceRet1 = this.byCharset.get(name);
/*      */         
/*  233 */         if (traceRet1 != null && traceRet1.spaceByte == 0) {
/*  234 */           if (Trace.isOn) {
/*  235 */             Trace.data(this, "getByName(JmqiEnvironment,String)", "Initializing codepage for Charset name: " + name);
/*      */           }
/*      */           
/*  238 */           traceRet1 = initializeCodepage(traceRet1);
/*      */         } 
/*      */       } 
/*      */       
/*  242 */       if (Trace.isOn) {
/*  243 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiCodepageFactory", "getByName(JmqiEnvironment,String)", traceRet1);
/*      */       }
/*      */ 
/*      */       
/*  247 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private JmqiCodepage initializeCodepage(JmqiCodepage cp) {
/*  255 */       if (Trace.isOn) {
/*  256 */         Trace.data(this, "initializeCodepage(JmqiCodepage)", "Initializing codepage for JmqiCodepage:", cp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  266 */       JmqiCodepage result = cp;
/*      */ 
/*      */       
/*  269 */       Charset charset = null;
/*  270 */       for (String charsetName : cp.charsetAliases) {
/*      */         try {
/*  272 */           if (Trace.isOn) {
/*  273 */             Trace.data(this, "initializeCodepage(JmqiCodepage)", "Testing JVM for support for Charset name \"" + charsetName + "\"");
/*      */           }
/*      */ 
/*      */           
/*  277 */           charset = Charset.forName(charsetName);
/*      */           
/*  279 */           if (charset != null) {
/*  280 */             if (Trace.isOn) {
/*  281 */               Trace.data(this, "initializeCodepage(JmqiCodepage)", "JVM supports the Charset", charset);
/*      */             }
/*      */             
/*      */             break;
/*      */           } 
/*  286 */         } catch (UnsupportedCharsetException unsupportedCharsetException) {}
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  299 */       if (charset == null) {
/*      */ 
/*      */ 
/*      */         
/*  303 */         if (Trace.isOn) {
/*  304 */           Trace.data(this, "initializeCodepage(JmqiCodepage)", "JVM did not support the Charset.  Searching in predefiend tables...");
/*      */         }
/*      */         
/*  307 */         for (String charsetName : cp.charsetAliases) {
/*  308 */           charset = this.provider.charsetForName(charsetName);
/*  309 */           if (charset != null) {
/*  310 */             if (Trace.isOn) {
/*  311 */               Trace.data(this, "initializeCodepage(JmqiCodepage)", "Found a matching Charset in the predefined tables", charset);
/*      */             }
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  319 */       if (charset != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  325 */         byte spaceByte = " ".getBytes(charset)[0];
/*      */         
/*  327 */         if (Trace.isOn) {
/*  328 */           Trace.data(this, "initializeCodepage(JmqiCodepage)", "spaceByte determined to have the integer value:", 
/*  329 */               Byte.valueOf(spaceByte));
/*      */         }
/*      */         
/*  332 */         cp.charset = charset;
/*  333 */         cp.charsetId = charset.name();
/*  334 */         cp.spaceByte = spaceByte;
/*      */       }
/*      */       else {
/*      */         
/*  338 */         for (String name : cp.charsetAliases) {
/*  339 */           this.byCharset.remove(name.toUpperCase());
/*      */         }
/*  341 */         for (int ccsid : cp.ccsids) {
/*  342 */           this.byCcsid.remove(Integer.valueOf(ccsid));
/*      */         }
/*  344 */         result = null;
/*      */       } 
/*  346 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     public void dump(PrintWriter pw, int level) {
/*  351 */       String prefix = Trace.buildPrefix(level);
/*  352 */       pw.format("%s%s%n", new Object[] { prefix, toString() });
/*  353 */       pw.format("%n%s  ccsdid -> JmqiCodepage mappings%n", new Object[] { prefix });
/*  354 */       for (Map.Entry<Integer, JmqiCodepage> entry : this.byCcsid.entrySet()) {
/*  355 */         pw.format("%s    %6d : %s%n", new Object[] { prefix, entry.getKey(), ((JmqiCodepage)entry.getValue()).toString() });
/*      */       } 
/*  357 */       pw.format("%n%s  charset name -> JmqiCodepage mappings%n", new Object[] { prefix });
/*  358 */       for (Map.Entry<String, JmqiCodepage> entry : this.byCharset.entrySet()) {
/*  359 */         pw.format("%s    %-16s : %s%n", new Object[] { prefix, entry.getKey(), ((JmqiCodepage)entry.getValue()).toString() });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getComponentName() {
/*  366 */       return "JmqiCodepage";
/*      */     }
/*      */     
/*      */     public Enumeration<Integer> getCCSIDS() {
/*  370 */       return new Enumeration<Integer>()
/*      */         {
/*  372 */           ArrayList<Integer> filteredCCSIDS = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           Iterator<Integer> it;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean hasMoreElements() {
/*  390 */             return this.it.hasNext();
/*      */           }
/*      */ 
/*      */           
/*      */           public Integer nextElement() {
/*  395 */             return this.it.next();
/*      */           }
/*      */         };
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static {
/*      */     try {
/*  404 */       factory = new JmqiCodepageFactory();
/*      */     }
/*  406 */     catch (IOException e) {
/*      */       
/*  408 */       RuntimeException traceRet1 = new RuntimeException(e);
/*  409 */       throw traceRet1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  439 */   private static ThreadLocal<JmqiCodepage> cachedEncoderCodepages = new ThreadLocal<>();
/*  440 */   private static ThreadLocal<CharsetEncoder> cachedEncoders = new ThreadLocal<>();
/*  441 */   private static ThreadLocal<JmqiCodepage> cachedDecoderCodepages = new ThreadLocal<>();
/*  442 */   private static ThreadLocal<CharsetDecoder> cachedDecoders = new ThreadLocal<>();
/*      */   
/*      */   private static boolean configurationChecked;
/*      */   private static boolean mapCp1200ToUTF16BE;
/*      */   private static boolean useMQCcsidStandards = true;
/*      */   private static String mapCcsid1200FamilyToSpecificCharset;
/*  448 */   private static CodingErrorAction defaultAction = CodingErrorAction.REPORT;
/*      */   
/*      */   static {
/*      */     try {
/*  452 */       defaultReplacement = "?".getBytes("US-ASCII");
/*      */     }
/*  454 */     catch (UnsupportedEncodingException e) {
/*      */       
/*  456 */       defaultReplacement = new byte[] { 63 };
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] defaultReplacement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiCodepage(int ccsid, int[] ccsids, Charset charset, String charsetId, String[] charsetAliases, byte spaceByte, boolean isAscii, boolean is16Bit, CodingErrorAction unmappableAction, byte[] unmappableReplacement) {
/*  473 */     this.ccsid = ccsid;
/*  474 */     this.ccsids = ccsids;
/*  475 */     this.charset = charset;
/*  476 */     this.unmappableAction = unmappableAction;
/*  477 */     this.unmappableReplacement = unmappableReplacement;
/*  478 */     this.charsetId = charsetId;
/*  479 */     this.charsetAliases = charsetAliases;
/*  480 */     this.spaceByte = spaceByte;
/*  481 */     this.isAscii = isAscii;
/*  482 */     this.is16Bit = is16Bit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object other) {
/*  490 */     if (!(other instanceof JmqiCodepage)) {
/*  491 */       return false;
/*      */     }
/*  493 */     JmqiCodepage otherCp = (JmqiCodepage)other;
/*      */ 
/*      */     
/*  496 */     return (this.charset.equals(otherCp.charset) && this.unmappableAction
/*  497 */       .equals(otherCp.unmappableAction) && 
/*  498 */       Arrays.equals(this.unmappableReplacement, otherCp.unmappableReplacement));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  507 */     int hashCode = 0;
/*      */     
/*  509 */     hashCode += 31 * this.charset.hashCode();
/*  510 */     hashCode += 37 * this.unmappableAction.hashCode();
/*  511 */     hashCode += 43 * Arrays.hashCode(this.unmappableReplacement);
/*      */     
/*  513 */     return hashCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  524 */     String display_unmappableAction = "<UNSET>";
/*  525 */     if (this.unmappableAction != null) {
/*  526 */       display_unmappableAction = this.unmappableAction.toString();
/*      */     }
/*      */     
/*  529 */     int display_unmappableReplacement = -1;
/*  530 */     if (this.unmappableReplacement != null && this.unmappableReplacement.length > 0) {
/*  531 */       display_unmappableReplacement = this.unmappableReplacement[0];
/*      */     }
/*      */     
/*  534 */     return String.format("%d(%s) Unmappable Action: %s, Unmappable Replacement: %d, spaceByte: %d", new Object[] {
/*  535 */           Integer.valueOf(this.ccsid), this.charsetId, display_unmappableAction, Integer.valueOf(display_unmappableReplacement), Byte.valueOf(this.spaceByte)
/*      */         });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, int ccsid, int encoding) throws UnsupportedEncodingException {
/*  629 */     return getJmqiCodepage(env, ccsid, null, null, encoding);
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
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, int ccsid, CodingErrorAction unmappableAction, byte[] unmappableReplacement, int encoding) throws UnsupportedEncodingException {
/*  649 */     if (Trace.isOn) {
/*  650 */       Trace.data("com.ibm.mq.jmqi.system.JmqiCodepage", "JmqiCodepage.getJmqiCodepage(env, ccsid(" + ccsid + "), unmappableAction(" + unmappableAction + "), unmappableReplacement(" + unmappableReplacement + "), encoding(" + encoding + "))", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  658 */     if (!configurationChecked) {
/*  659 */       checkConfiguration(env);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  664 */       if (ccsid % 4096 == 1200) {
/*      */ 
/*      */         
/*  667 */         if (mapCcsid1200FamilyToSpecificCharset != null) {
/*  668 */           if (Trace.isOn) {
/*  669 */             Trace.data("com.ibm.mq.jmqi.system.JmqiCodepage", "JmqiCodepage.getJmqiCodepage(env," + ccsid + "," + encoding + "): Forcing CCSID map to Charset \"" + mapCcsid1200FamilyToSpecificCharset + "\"", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  674 */           return getJmqiCodepage(env, mapCcsid1200FamilyToSpecificCharset, unmappableAction, unmappableReplacement);
/*      */         } 
/*      */ 
/*      */         
/*  678 */         if (useMQCcsidStandards) {
/*  679 */           String charset = null;
/*  680 */           if ((encoding & 0xF) == 2) {
/*  681 */             charset = "x-UTF-16LE-BOM";
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*  687 */           else if (mapCp1200ToUTF16BE) {
/*  688 */             charset = "UnicodeBigUnmarked";
/*      */           } else {
/*      */             
/*  691 */             charset = "UTF-16";
/*      */           } 
/*      */           
/*  694 */           return getJmqiCodepage(env, charset, unmappableAction, unmappableReplacement);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  699 */       return getJmqiCodepage(env, ccsid, unmappableAction, unmappableReplacement);
/*      */     
/*      */     }
/*  702 */     catch (UnsupportedEncodingException uee) {
/*      */       
/*  704 */       if (Trace.isOn) {
/*  705 */         Trace.catchBlock("com.ibm.mq.jmqi.system.JmqiCodepage", "getJmqiCodepage(JmqiEncironment,int,CodeingErrorAction,byte[],int)", uee);
/*      */       }
/*      */ 
/*      */       
/*  709 */       throw uee;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, int ccsid) {
/*      */     try {
/*  721 */       return getJmqiCodepage(env, ccsid, (CodingErrorAction)null, (byte[])null);
/*      */     }
/*  723 */     catch (UnsupportedEncodingException e) {
/*  724 */       return null;
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
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, int ccsid, CodingErrorAction unmappableAction) throws UnsupportedEncodingException {
/*  736 */     return getJmqiCodepage(env, ccsid, unmappableAction, (byte[])null);
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
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, int ccsid, CodingErrorAction unmappableActionP, byte[] unmappableReplacementP) throws UnsupportedEncodingException {
/*  753 */     if (!configurationChecked) {
/*  754 */       checkConfiguration(env);
/*      */     }
/*  756 */     CodingErrorAction unmappableAction = (unmappableActionP != null) ? unmappableActionP : defaultAction;
/*  757 */     byte[] unmappableReplacement = (unmappableReplacementP != null) ? unmappableReplacementP : defaultReplacement;
/*      */     
/*  759 */     JmqiCodepage cp = factory.getByCcsid(env, ccsid);
/*      */ 
/*      */     
/*  762 */     if (cp == null && ccsid == 1051)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  769 */       cp = factory.getByCcsid(env, 819);
/*      */     }
/*      */     
/*  772 */     if (cp == null) {
/*  773 */       throw new UnsupportedEncodingException(String.valueOf(ccsid));
/*      */     }
/*      */     
/*  776 */     JmqiCodepage previous = null;
/*  777 */     JmqiCodepage found = null;
/*  778 */     for (JmqiCodepage candidate = cp; candidate != null; previous = candidate, candidate = candidate.nextInList) {
/*  779 */       if (candidate.unmappableAction == unmappableAction) {
/*  780 */         if (unmappableAction != CodingErrorAction.REPLACE) {
/*  781 */           found = candidate;
/*      */           break;
/*      */         } 
/*  784 */         if (Arrays.equals(candidate.unmappableReplacement, unmappableReplacement)) {
/*  785 */           found = candidate;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  790 */     if (found != null) {
/*  791 */       cp = found;
/*      */     } else {
/*      */       
/*      */       try {
/*  795 */         cp = (JmqiCodepage)previous.clone();
/*      */       }
/*  797 */       catch (CloneNotSupportedException e) {
/*      */         
/*  799 */         return null;
/*      */       } 
/*  801 */       cp.unmappableAction = unmappableAction;
/*  802 */       cp.unmappableReplacement = unmappableReplacement;
/*  803 */       previous.nextInList = cp;
/*      */     } 
/*      */     
/*  806 */     return cp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkConfiguration(JmqiEnvironment env) {
/*  816 */     Configuration cfg = (env != null) ? env.getConfiguration() : null;
/*      */     
/*  818 */     if (cfg != null) {
/*  819 */       String unmappableActionName = cfg.getChoiceValue(Configuration.UNMAPPABLE_ACTION);
/*  820 */       for (CodingErrorAction action : Arrays.<CodingErrorAction>asList(new CodingErrorAction[] { CodingErrorAction.REPLACE, CodingErrorAction.REPORT, CodingErrorAction.IGNORE })) {
/*  821 */         if (action.toString().endsWith(unmappableActionName)) {
/*  822 */           defaultAction = action;
/*      */         }
/*      */       } 
/*  825 */       defaultReplacement = new byte[] { (byte)cfg.getIntValue(Configuration.UNMAPPABLE_REPLACEMENT) };
/*  826 */       mapCp1200ToUTF16BE = cfg.getBoolValue(Configuration.CCSID_MAPCCSID1200TOUNICODEBIGUNMARKED);
/*  827 */       useMQCcsidStandards = !cfg.getBoolValue(Configuration.CCSID_MAPUTF16BYTEORDERBYCCSID);
/*  828 */       mapCcsid1200FamilyToSpecificCharset = cfg.getStringValue(Configuration.CCSID_MAPCCSID1200FAMILYTOSPECIFICCHARSET);
/*  829 */       configurationChecked = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static CodingErrorAction getUnmappableCharacterDefaultAction(JmqiEnvironment env) {
/*  836 */     if (!configurationChecked) {
/*  837 */       checkConfiguration(env);
/*      */     }
/*  839 */     return defaultAction;
/*      */   }
/*      */ 
/*      */   
/*      */   public static byte[] getUnmappableCharacterDefaultReplacement(JmqiEnvironment env) {
/*  844 */     if (!configurationChecked) {
/*  845 */       checkConfiguration(env);
/*      */     }
/*  847 */     return defaultReplacement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, String charset) {
/*      */     try {
/*  859 */       return getJmqiCodepage(env, charset, (CodingErrorAction)null, (byte[])null);
/*      */     }
/*  861 */     catch (UnsupportedEncodingException e) {
/*  862 */       return null;
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
/*      */   public static JmqiCodepage getJmqiCodepage(JmqiEnvironment env, String charset, CodingErrorAction unmappableActionP, byte[] unmappableReplacementP) throws UnsupportedEncodingException {
/*  877 */     if (!configurationChecked) {
/*  878 */       checkConfiguration(env);
/*      */     }
/*  880 */     CodingErrorAction unmappableAction = (unmappableActionP != null) ? unmappableActionP : defaultAction;
/*  881 */     byte[] unmappableReplacement = (unmappableReplacementP != null) ? unmappableReplacementP : defaultReplacement;
/*      */     
/*  883 */     JmqiCodepage cp = factory.getByName(env, charset.toUpperCase());
/*      */ 
/*      */     
/*  886 */     if (cp == null) {
/*      */       try {
/*  888 */         int ccsid = Integer.parseInt(charset);
/*  889 */         cp = factory.getByCcsid(env, ccsid);
/*      */       }
/*  891 */       catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  896 */     if (cp == null) {
/*  897 */       throw new UnsupportedEncodingException(String.valueOf(charset));
/*      */     }
/*      */     
/*  900 */     JmqiCodepage previous = null;
/*  901 */     JmqiCodepage found = null;
/*  902 */     for (JmqiCodepage candidate = cp; candidate != null; previous = candidate, candidate = candidate.nextInList) {
/*  903 */       if (candidate.unmappableAction == unmappableAction) {
/*  904 */         if (unmappableAction != CodingErrorAction.REPLACE) {
/*  905 */           found = candidate;
/*      */           break;
/*      */         } 
/*  908 */         if (Arrays.equals(candidate.unmappableReplacement, unmappableReplacement)) {
/*  909 */           found = candidate;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  914 */     if (found != null) {
/*  915 */       cp = found;
/*      */     } else {
/*      */       
/*      */       try {
/*  919 */         cp = (JmqiCodepage)previous.clone();
/*      */       }
/*  921 */       catch (CloneNotSupportedException e) {
/*      */         
/*  923 */         return null;
/*      */       } 
/*  925 */       cp.unmappableAction = unmappableAction;
/*  926 */       cp.unmappableReplacement = unmappableReplacement;
/*  927 */       previous.nextInList = cp;
/*      */     } 
/*      */     
/*  930 */     return cp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String bytesToString(byte[] bytes) throws CharacterCodingException {
/*  940 */     return bytesToString(bytes, 0, bytes.length);
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
/*      */   public String bytesToString(byte[] bytes, int offset, int length) throws CharacterCodingException {
/*  952 */     String retVal = getDecoder().decode(ByteBuffer.wrap(bytes, offset, length)).toString();
/*  953 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] stringToBytes(String string) throws CharacterCodingException {
/*  964 */     byte[] retVal = null;
/*  965 */     ByteBuffer bb = getEncoder().encode(CharBuffer.wrap(string.toCharArray()));
/*  966 */     retVal = new byte[bb.limit()];
/*  967 */     bb.get(retVal);
/*  968 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CharsetEncoder getEncoder() {
/*  976 */     JmqiCodepage cachedEncoderCodepage = cachedEncoderCodepages.get();
/*  977 */     CharsetEncoder encoder = null;
/*  978 */     if (equals(cachedEncoderCodepage)) {
/*  979 */       encoder = cachedEncoders.get();
/*      */     }
/*  981 */     if (encoder == null) {
/*  982 */       encoder = this.charset.newEncoder().onUnmappableCharacter(this.unmappableAction).onMalformedInput(this.unmappableAction);
/*  983 */       if (!this.is16Bit) {
/*  984 */         encoder.replaceWith(this.unmappableReplacement);
/*      */       }
/*  986 */       cachedEncoderCodepages.set(this);
/*  987 */       cachedEncoders.set(encoder);
/*      */     } 
/*  989 */     return encoder.reset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CharsetDecoder getDecoder() {
/*  996 */     JmqiCodepage cachedDecoderCodepage = cachedDecoderCodepages.get();
/*  997 */     CharsetDecoder decoder = null;
/*  998 */     if (equals(cachedDecoderCodepage)) {
/*  999 */       decoder = cachedDecoders.get();
/*      */     }
/* 1001 */     if (decoder == null) {
/* 1002 */       decoder = this.charset.newDecoder().onUnmappableCharacter(this.unmappableAction).onMalformedInput(this.unmappableAction);
/* 1003 */       cachedDecoderCodepages.set(this);
/* 1004 */       cachedDecoders.set(decoder);
/*      */     } 
/* 1006 */     return decoder.reset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCharsetName() {
/* 1013 */     return this.charset.name();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Enumeration getCCSIDs() {
/* 1022 */     return factory.getCCSIDS();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCCSID() {
/* 1032 */     if (this.ccsid == -1202)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1037 */       return 1200;
/*      */     }
/*      */     
/* 1040 */     return this.ccsid;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiCodepage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */