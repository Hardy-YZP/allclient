/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.SearchResults;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SearchResults
/*     */   implements SearchResults, ClientLogConstants
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SearchResults.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SearchResults.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static final DebugObject debug = new DebugObject("SearchResults");
/*     */ 
/*     */ 
/*     */   
/*     */   FastVector m_receivers;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SearchResults() {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "<init>()");
/*     */     }
/*     */     
/*  77 */     if (debug.debugIt(32)) {
/*  78 */       debug.debug(-165922073994779L, "SearchResults");
/*     */     }
/*     */ 
/*     */     
/*  82 */     this.m_receivers = new FastVector();
/*     */ 
/*     */     
/*  85 */     if (debug.debugIt(64)) {
/*  86 */       debug.debug(-142394261359015L, "SearchResults");
/*     */     }
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "<init>()");
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
/*     */   public void addObjects(FastVector[] objects) {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "addObjects(FastVector [ ])", new Object[] { objects });
/*     */     }
/*     */ 
/*     */     
/* 107 */     if (debug.debugIt(32)) {
/* 108 */       debug.debug(-165922073994779L, "addObjects", objects);
/*     */     }
/*     */ 
/*     */     
/* 112 */     if (objects.length > 4 && objects[4] != null)
/*     */     {
/* 114 */       this.m_receivers.append(objects[4]);
/*     */     }
/*     */ 
/*     */     
/* 118 */     if (debug.debugIt(64)) {
/* 119 */       debug.debug(-142394261359015L, "addObjects");
/*     */     }
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "addObjects(FastVector [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object provideCacheable(String topic) {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "provideCacheable(String)", new Object[] { topic });
/*     */     }
/*     */ 
/*     */     
/* 137 */     if (debug.debugIt(32)) {
/* 138 */       debug.debug(-165922073994779L, "provideCacheable");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (debug.debugIt(64)) {
/* 144 */       debug.debug(-142394261359015L, "provideCacheable", null);
/*     */     }
/*     */ 
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "provideCacheable(String)", null);
/*     */     }
/*     */     
/* 152 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean acceptCacheable(Object cache) {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "acceptCacheable(Object)", new Object[] { cache });
/*     */     }
/*     */ 
/*     */     
/* 162 */     if (debug.debugIt(32)) {
/* 163 */       debug.debug(-165922073994779L, "acceptCacheable", cache);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 168 */     if (debug.debugIt(64)) {
/* 169 */       debug.debug(-142394261359015L, "acceptCacheable", Boolean.valueOf(false));
/*     */     }
/*     */ 
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "acceptCacheable(Object)", 
/* 175 */           Boolean.valueOf(false));
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "reset()");
/*     */     }
/*     */     
/* 188 */     if (debug.debugIt(32)) {
/* 189 */       debug.debug(-165922073994779L, "reset");
/*     */     }
/*     */ 
/*     */     
/* 193 */     this.m_receivers.m_count = 0;
/*     */ 
/*     */     
/* 196 */     if (debug.debugIt(64)) {
/* 197 */       debug.debug(-142394261359015L, "reset");
/*     */     }
/*     */     
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "reset()");
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
/*     */   public String toString() {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "toString()");
/*     */     }
/* 216 */     if (this.m_receivers.m_count == 0) {
/* 217 */       String traceRet1 = "<>";
/* 218 */       if (Trace.isOn) {
/* 219 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "toString()", traceRet1, 1);
/*     */       }
/*     */       
/* 222 */       return traceRet1;
/*     */     } 
/*     */     
/* 225 */     StringBuffer buf = new StringBuffer("< ");
/* 226 */     buf.append(this.m_receivers.m_data[0]);
/*     */     
/* 228 */     for (int i = 1; i < this.m_receivers.m_count; i++) {
/* 229 */       buf.append(", " + this.m_receivers.m_data[i]);
/*     */     }
/*     */     
/* 232 */     buf.append(" >");
/*     */     
/* 234 */     String traceRet2 = buf.toString();
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SearchResults", "toString()", traceRet2, 2);
/*     */     }
/*     */     
/* 239 */     return traceRet2;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\SearchResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */