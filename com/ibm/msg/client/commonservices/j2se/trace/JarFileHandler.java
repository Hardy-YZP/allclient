/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.jar.JarOutputStream;
/*     */ import java.util.logging.FileHandler;
/*     */ import java.util.zip.ZipEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JarFileHandler
/*     */   extends FileHandler
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/JarFileHandler.java";
/*  47 */   private static int compLevel = 5;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String traceFileName;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String traceFileExtension = ".trc";
/*     */ 
/*     */   
/*     */   private static String tracepid;
/*     */ 
/*     */   
/*  61 */   private static int traceFileCount = 0;
/*     */ 
/*     */   
/*     */   BufferedOutputStream buffer;
/*     */   
/*     */   JarOutputStream jarStream;
/*     */ 
/*     */   
/*     */   public static void setPID(String pid) {
/*  70 */     tracepid = pid;
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
/*     */   public JarFileHandler() throws IOException, SecurityException {
/*  88 */     this.traceFileName = "mqjms";
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
/*     */   public JarFileHandler(String pattern) throws IOException, SecurityException {
/* 100 */     super(pattern);
/* 101 */     this.traceFileName = pattern;
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
/*     */   public JarFileHandler(String pattern, boolean append) throws IOException, SecurityException {
/* 114 */     super(pattern, append);
/* 115 */     this.traceFileName = pattern;
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
/*     */   public JarFileHandler(String pattern, int limit, int count) throws IOException, SecurityException {
/* 130 */     super(pattern, limit, count);
/* 131 */     this.traceFileName = pattern;
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
/*     */   public JarFileHandler(String pattern, int limit, int count, boolean append) throws IOException, SecurityException {
/* 148 */     super(pattern, limit, count, append);
/* 149 */     this.traceFileName = pattern;
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
/*     */   public synchronized void close() throws SecurityException {
/* 161 */     super.close();
/*     */     
/* 163 */     if (null != this.buffer) {
/*     */       try {
/* 165 */         this.buffer.flush();
/* 166 */         this.buffer.close();
/*     */       }
/* 168 */       catch (IOException iOException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 173 */     if (null != this.jarStream) {
/*     */       try {
/* 175 */         this.jarStream.close();
/*     */       }
/* 177 */       catch (IOException iOException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getNextTraceFileName() {
/* 187 */     String newName = this.traceFileName + traceFileCount + "_" + tracepid + ".trc";
/* 188 */     traceFileCount++;
/* 189 */     return newName;
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
/*     */   protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
/*     */     try {
/* 204 */       this.jarStream = new JarOutputStream(out);
/* 205 */       this.jarStream.setComment(generateJarComment());
/* 206 */       this.jarStream.setLevel(compLevel);
/*     */       
/* 208 */       this.jarStream.putNextEntry(new ZipEntry(getNextTraceFileName()));
/*     */       
/* 210 */       this.buffer = new BufferedOutputStream(this.jarStream);
/*     */       
/* 212 */       super.setOutputStream(this.buffer);
/*     */     }
/* 214 */     catch (IOException e) {
/*     */ 
/*     */       
/* 217 */       super.setOutputStream(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String generateJarComment() {
/* 223 */     StringBuilder comment = new StringBuilder();
/* 224 */     comment.append("IBM JMS Common Client Service Information file\n");
/* 225 */     comment.append("This archive will contain a number of files containing trace:\n");
/* 226 */     comment.append(" - Trace information will be contained in the files " + this.traceFileName + "*" + ".trc" + '\n');
/* 227 */     return comment.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\JarFileHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */