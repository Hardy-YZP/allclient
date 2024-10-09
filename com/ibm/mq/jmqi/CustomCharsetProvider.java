/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.nio.charset.Charset;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomCharsetProvider
/*     */   implements Iterable<Charset>
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/CustomCharsetProvider.java";
/*  43 */   private static ArrayList<Charset> charsets = null;
/*     */   
/*     */   static {
/*  46 */     charsets = AccessController.<ArrayList<Charset>>doPrivileged(new PrivilegedAction<ArrayList<Charset>>()
/*     */         {
/*     */           public ArrayList<Charset> run()
/*     */           {
/*  50 */             ArrayList<Charset> result = new ArrayList<>();
/*     */             try {
/*  52 */               String filename = "META-INF/jmqiCharsets.dat";
/*     */ 
/*     */               
/*  55 */               InputStream resource = ClassLoader.getSystemResourceAsStream(filename);
/*     */ 
/*     */               
/*  58 */               if (resource == null) {
/*  59 */                 ClassLoader thisClassesLoader = getClass().getClassLoader();
/*  60 */                 resource = thisClassesLoader.getResourceAsStream(filename);
/*     */               } 
/*     */ 
/*     */               
/*  64 */               if (resource == null) {
/*  65 */                 ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/*  66 */                 resource = threadContextClassloader.getResourceAsStream(filename);
/*     */               } 
/*     */ 
/*     */               
/*  70 */               if (resource != null)
/*     */               {
/*  72 */                 BufferedReader br = new BufferedReader(new InputStreamReader(resource, Charset.defaultCharset()));
/*  73 */                 while (br.ready()) {
/*  74 */                   String line = br.readLine();
/*  75 */                   if (line != null && !line.startsWith("#")) {
/*     */                     
/*     */                     try {
/*  78 */                       Class<Charset> charsetClass = (Class)Class.forName(line);
/*  79 */                       Charset charset = charsetClass.newInstance();
/*  80 */                       result.add(charset);
/*     */                     }
/*  82 */                     catch (Exception exception) {}
/*     */                   }
/*     */                 } 
/*     */ 
/*     */                 
/*  87 */                 br.close();
/*     */               }
/*     */             
/*  90 */             } catch (AccessControlException accessControlException) {
/*     */ 
/*     */             
/*     */             }
/*  94 */             catch (IOException iOException) {}
/*     */ 
/*     */             
/*  97 */             return result;
/*     */           }
/*     */         });
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
/*     */   public Charset charsetForName(String charsetName) {
/* 111 */     String name = charsetName.trim();
/* 112 */     for (Charset charset : charsets) {
/* 113 */       if (charset.name().equals(name)) {
/* 114 */         return charset;
/*     */       }
/* 116 */       if (charset.aliases().contains(charsetName)) {
/* 117 */         return charset;
/*     */       }
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<Charset> charsets() {
/* 127 */     return charsets.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<Charset> iterator() {
/* 135 */     return charsets.iterator();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\CustomCharsetProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */