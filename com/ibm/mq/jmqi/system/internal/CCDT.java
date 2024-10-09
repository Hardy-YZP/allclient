/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
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
/*     */ 
/*     */ 
/*     */ public class CCDT
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/CCDT.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jmqi.system.internal.CCDT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/CCDT.java");
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
/*  63 */   protected Object listLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private URL ccdtUrl = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private File ccdtFile = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private long lastChangeTime = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final CCDTFileParser parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCDT(JmqiEnvironment env, URL url) throws JmqiException {
/*  92 */     this(env, url, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCDT(JmqiEnvironment env, URL url, boolean remote) throws JmqiException {
/* 102 */     super(env);
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "<init>(JmqiEnvironment,URL,boolean)", new Object[] { env, url, 
/* 105 */             Boolean.valueOf(remote) });
/*     */     }
/*     */     
/* 108 */     this.ccdtUrl = url;
/* 109 */     this.ccdtFile = new File(url.getFile());
/* 110 */     this.lastChangeTime = determineLastChangeTime();
/* 111 */     this.parser = CCDTFileParser.newInstance(env, this.ccdtUrl, remote);
/* 112 */     this.parser.parse();
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "<init>(JmqiEnvironment,URL)");
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
/*     */   public MQCD[] getChannels(String qMgrName, int transportType) throws JmqiException {
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getChannels(String,int)", new Object[] { qMgrName, 
/* 131 */             Integer.valueOf(transportType) });
/*     */     }
/*     */     
/* 134 */     String matchString = null;
/* 135 */     int asteriskPos = qMgrName.indexOf('*');
/* 136 */     if (asteriskPos != -1) {
/*     */       
/* 138 */       if (asteriskPos != 0) {
/*     */         
/* 140 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2058, null);
/*     */         
/* 142 */         if (Trace.isOn) {
/* 143 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getChannels(String,int)", (Throwable)traceRet1);
/*     */         }
/*     */         
/* 146 */         throw traceRet1;
/*     */       } 
/*     */       
/* 149 */       if (qMgrName.length() != 1) {
/* 150 */         matchString = qMgrName.substring(1);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 155 */       matchString = qMgrName;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 160 */     int numChannels = 0;
/* 161 */     MQCD[] matchingChannels = null;
/* 162 */     synchronized (this.listLock) {
/*     */ 
/*     */       
/* 165 */       matchingChannels = new MQCD[this.parser.getChannelDefinitions().size()];
/*     */ 
/*     */       
/* 168 */       Iterator<MQCD> channelIterator = getChannelDefinitions();
/* 169 */       while (channelIterator.hasNext()) {
/* 170 */         MQCD channel = channelIterator.next();
/*     */ 
/*     */         
/* 173 */         if ((matchString == null || matchString.equals(channel.getQMgrName().trim())) && channel.getTransportType() == transportType) {
/* 174 */           matchingChannels[numChannels] = channel;
/* 175 */           numChannels++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 181 */     MQCD[] sizedChannels = new MQCD[numChannels];
/* 182 */     for (int i = 0; i < numChannels; i++) {
/* 183 */       sizedChannels[i] = matchingChannels[i];
/*     */     }
/*     */     
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getChannels(String,int)", sizedChannels);
/*     */     }
/*     */     
/* 190 */     return sizedChannels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<MQCD> getChannelDefinitions() {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getChannelDefinitions()");
/*     */     }
/* 202 */     Iterator<MQCD> traceRet1 = new Iterator<MQCD>()
/*     */       {
/* 204 */         Iterator<MQCDWrapper> inner = CCDT.this.parser.getChannelDefinitions().iterator();
/*     */ 
/*     */         
/*     */         public boolean hasNext() {
/* 208 */           if (Trace.isOn) {
/* 209 */             Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "hasNext()");
/*     */           }
/*     */           
/* 212 */           boolean traceRet1 = this.inner.hasNext();
/* 213 */           if (Trace.isOn) {
/* 214 */             Trace.exit(this, "com.ibm.mq.jmqi.system.internal.null", "hasNext()", 
/* 215 */                 Boolean.valueOf(traceRet1));
/*     */           }
/* 217 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public MQCD next() {
/* 222 */           if (Trace.isOn) {
/* 223 */             Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "next()");
/*     */           }
/*     */           
/* 226 */           MQCD traceRet1 = ((MQCDWrapper)this.inner.next()).mqcd;
/* 227 */           if (Trace.isOn) {
/* 228 */             Trace.exit(this, "com.ibm.mq.jmqi.system.internal.null", "next()", traceRet1);
/*     */           }
/* 230 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 235 */           if (Trace.isOn) {
/* 236 */             Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "remove()");
/*     */           }
/*     */           
/* 239 */           UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/* 240 */           if (Trace.isOn) {
/* 241 */             Trace.throwing(this, "com.ibm.mq.jmqi.system.internal.null", "remove()", traceRet1);
/*     */           }
/* 243 */           throw traceRet1;
/*     */         }
/*     */       };
/*     */     
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getChannelDefinitions()", traceRet1);
/*     */     }
/*     */     
/* 251 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getCCDTFile() {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getCCDTFile()", "getter", this.ccdtFile);
/*     */     }
/* 261 */     return this.ccdtFile;
/*     */   }
/*     */   
/*     */   private long determineLastChangeTime() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "determineLastChangeTime()");
/* 267 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.CCDT", "determineLastChangeTime()", "ccdt file", this.ccdtFile);
/*     */     } 
/* 269 */     if (this.ccdtUrl == null) {
/* 270 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "determineLastChangeTime()", Integer.valueOf(0), 0);
/* 271 */       return 0L;
/*     */     } 
/*     */     
/* 274 */     long myLastChangeTime = 0L;
/*     */     try {
/* 276 */       URLConnection connectionToCcdtUrl = this.ccdtUrl.openConnection();
/* 277 */       myLastChangeTime = connectionToCcdtUrl.getLastModified();
/*     */     }
/* 279 */     catch (IOException ioe) {
/* 280 */       if (Trace.isOn) {
/* 281 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.CCDT", "determineLastChangeTime()", ioe);
/*     */         
/* 283 */         Trace.data(this, "com.ibm.mq.jmqi.system.internal.CCDT", "determineLastChangeTime()", "Could not determine last modified time for remote CCDT file.", 
/* 284 */             Long.valueOf(myLastChangeTime));
/*     */       } 
/*     */     } 
/*     */     
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "determineLastChangeTime()", 
/* 290 */           Long.valueOf(myLastChangeTime), 1);
/*     */     }
/* 292 */     return myLastChangeTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getCachedLastChangeTime() {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.CCDT", "getCachedLastChangeTime()", "getter", 
/* 301 */           Long.valueOf(this.lastChangeTime));
/*     */     }
/* 303 */     return this.lastChangeTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCcdtBeenModifiedSincePreviouslyParsed() {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.CCDT", "hasCcdtBeenModifiedSincePreviouslyParsed()");
/*     */     }
/* 313 */     if (this.lastChangeTime == 0L) {
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "hasCcdtBeenModifiedSincePreviouslyParsed()", Boolean.valueOf(true), 0);
/*     */       }
/* 317 */       return true;
/*     */     } 
/*     */     
/* 320 */     boolean hasChanged = (this.lastChangeTime != determineLastChangeTime());
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.CCDT", "hasCcdtBeenModifiedSincePreviouslyParsed()", Boolean.valueOf(hasChanged), 1);
/*     */     }
/* 324 */     return hasChanged;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\CCDT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */