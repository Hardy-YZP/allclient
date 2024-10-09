/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import java.util.logging.Filter;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceFilter
/*     */   implements Filter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/TraceFilter.java";
/*     */   private boolean stringMatching = false;
/*  50 */   private Pattern searchStringPattern = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TraceFilter() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TraceFilter(String searchString) {
/*  63 */     initialize(searchString);
/*     */   }
/*     */   
/*     */   private void initialize(String searchString) {
/*  67 */     setSearchString(searchString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSearchString(String searchString) {
/*  75 */     if (null != searchString) {
/*  76 */       this.searchStringPattern = Pattern.compile(searchString);
/*     */     }
/*     */     
/*  79 */     this.stringMatching = (null != searchString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLoggable(LogRecord record) {
/*  88 */     if (this.stringMatching) {
/*  89 */       String className = record.getSourceClassName();
/*  90 */       if (className != null) {
/*  91 */         StringBuffer match = new StringBuffer(className);
/*  92 */         match.append(".").append(record.getSourceMethodName());
/*     */         
/*  94 */         Matcher matcher = this.searchStringPattern.matcher(match.toString());
/*  95 */         if (matcher.matches()) {
/*  96 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 103 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\TraceFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */