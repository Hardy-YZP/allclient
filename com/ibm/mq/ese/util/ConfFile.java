/*     */ package com.ibm.mq.ese.util;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Map;
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
/*     */ public class ConfFile
/*     */   extends Properties
/*     */ {
/*     */   private static final long serialVersionUID = -2273789952820755304L;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.ese.util.ConfFile", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/ConfFile.java");
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
/*     */   public synchronized Object put(Object key, Object value) throws DuplicateKeyException {
/*  62 */     if (containsKey(key)) {
/*  63 */       throw new DuplicateKeyException(key.toString());
/*     */     }
/*  65 */     return super.put(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void putAll(Map<?, ?> map) throws DuplicateKeyException {
/*  75 */     for (Object object : map.keySet()) {
/*  76 */       if (map.containsKey(object)) {
/*  77 */         throw new DuplicateKeyException(object.toString());
/*     */       }
/*     */     } 
/*  80 */     super.putAll(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object setProperty(String key, String value) throws DuplicateKeyException {
/*  91 */     if (containsKey(key)) {
/*  92 */       throw new DuplicateKeyException(key);
/*     */     }
/*  94 */     return super.setProperty(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void load(InputStream inStream) throws IOException {
/* 103 */     StringBuilder builder = new StringBuilder();
/* 104 */     BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
/* 105 */     String lineSeparator = SystemUtils.getSystemProperty("line.separator", "\n");
/* 106 */     String line = reader.readLine();
/* 107 */     while (line != null) {
/* 108 */       builder.append(line + lineSeparator);
/* 109 */       line = reader.readLine();
/*     */     } 
/*     */     
/* 112 */     String propertiesWithBackslashes = builder.toString().replace("\\", "\\\\");
/* 113 */     super.load(new ByteArrayInputStream(propertiesWithBackslashes.getBytes()));
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\es\\util\ConfFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */