/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQAIR;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Comparator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CCDTFileParser
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/CCDTFileParser.java";
/*  56 */   private static final Class<CCDTFileParser> cclass = CCDTFileParser.class;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) Trace.data(cclass.getName(), "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/CCDTFileParser.java");
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class ChlnameMQCDComparator
/*     */     implements Comparator<MQCDWrapper>
/*     */   {
/*     */     private ChlnameMQCDComparator() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(MQCDWrapper mqcdWrapper1, MQCDWrapper mqcdWrapper2) {
/*  74 */       if (Trace.isOn) {
/*  75 */         Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChlnameMQCDComparator", "compare(MQCDWrapper,MQCDWrapper)", new Object[] { mqcdWrapper1, mqcdWrapper2 });
/*     */       }
/*     */       
/*  78 */       int result = mqcdWrapper1.mqcd.getChannelName().compareTo(mqcdWrapper2.mqcd.getChannelName());
/*  79 */       if (result == 0) {
/*  80 */         result = mqcdWrapper1.seq - mqcdWrapper2.seq;
/*     */       }
/*  82 */       if (Trace.isOn) {
/*  83 */         Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChlnameMQCDComparator", "compare(MQCDWrapper,MQCDWrapper)", 
/*  84 */             Integer.valueOf(result));
/*     */       }
/*  86 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   protected int nextSeq = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected SortedSet<MQCDWrapper> channelDefinitions = new TreeSet<>(new ChlnameMQCDComparator());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected LinkedList<MQAIR> authInfoRecords = new LinkedList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CCDTFileParser newInstance(JmqiEnvironment env, URL url, boolean remote) throws JmqiException {
/*     */     CCDTFileParser parser;
/* 116 */     String methodSignature = "newInstance(JmqiEnvironment,URL,boolean)";
/* 117 */     if (Trace.isOn) Trace.entry(cclass.getName(), "newInstance(JmqiEnvironment,URL,boolean)", new Object[] { env, url, Boolean.valueOf(remote) });
/*     */ 
/*     */     
/* 120 */     if (url != null) {
/* 121 */       InputStream ccdtInputStream = open(env, url);
/* 122 */       if (BinaryCCDTFileParser.isBinary(env, url, ccdtInputStream)) {
/* 123 */         if (remote) {
/* 124 */           parser = new BinaryRemoteCCDTFileParser(env, url, ccdtInputStream);
/*     */         } else {
/* 126 */           parser = new BinaryCCDTFileParser(env, url, ccdtInputStream);
/*     */         } 
/*     */       } else {
/* 129 */         parser = new JSONCCDTFileParser(env, url, ccdtInputStream);
/*     */       } 
/*     */     } else {
/* 132 */       JmqiException exception = new JmqiException(env, 9516, new String[] { null, null, "" }, 2, 2278, null);
/* 133 */       if (Trace.isOn) Trace.throwing(cclass.getName(), "newInstance(JmqiEnvironment,URL,boolean)", (Throwable)exception); 
/* 134 */       throw exception;
/*     */     } 
/*     */     
/* 137 */     if (Trace.isOn) Trace.exit(cclass.getName(), "newInstance(JmqiEnvironment,URL,boolean)", parser);
/*     */     
/* 139 */     return parser;
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
/*     */   protected static InputStream open(JmqiEnvironment env, final URL url) throws JmqiException {
/* 151 */     String methodSignature = "open(JmqiEnvironment,URL)";
/* 152 */     if (Trace.isOn) Trace.entry(cclass.getName(), "open(JmqiEnvironment,URL)", new Object[] { env, url });
/*     */ 
/*     */     
/* 155 */     InputStream ccdtInputStream = null;
/*     */     try {
/* 157 */       Object ccdtInputStreamTemp = AccessController.doPrivileged(new PrivilegedAction()
/*     */           {
/*     */             public Object run()
/*     */             {
/* 161 */               String methodSignature = "run()";
/* 162 */               String className = "com.ibm.mq.jmqi.system.internal.null";
/* 163 */               if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.system.internal.null", "run()"); 
/*     */               try {
/* 165 */                 Object is = url.openStream();
/* 166 */                 if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.system.internal.null", "run()", is, 1); 
/* 167 */                 return is;
/*     */               }
/* 169 */               catch (IOException ioe) {
/* 170 */                 if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.null", "run()", ioe); 
/* 171 */                 if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.system.internal.null", "run()", ioe, 2); 
/* 172 */                 return ioe;
/*     */               } 
/*     */             }
/*     */           });
/* 176 */       if (ccdtInputStreamTemp instanceof IOException) {
/* 177 */         IOException exception = (IOException)ccdtInputStreamTemp;
/* 178 */         if (Trace.isOn) Trace.throwing(cclass.getName(), "open(JmqiEnvironment,URL)", exception, 1); 
/* 179 */         throw exception;
/*     */       } 
/* 181 */       ccdtInputStream = new BufferedInputStream((InputStream)ccdtInputStreamTemp);
/*     */     }
/* 183 */     catch (IOException e) {
/* 184 */       if (Trace.isOn) Trace.catchBlock(cclass.getName(), "open(JmqiEnvironment,URL)", e); 
/* 185 */       JmqiException exception = new JmqiException(env, 9516, new String[] { JmqiTools.getExSumm(e), null, url.toString() }, 2, 2278, e);
/* 186 */       if (Trace.isOn) Trace.throwing(cclass.getName(), "open(JmqiEnvironment,URL)", (Throwable)exception, 1); 
/* 187 */       throw exception;
/*     */     } 
/*     */     
/* 190 */     if (Trace.isOn) Trace.exit(cclass.getName(), "open(JmqiEnvironment,URL)", ccdtInputStream);
/*     */     
/* 192 */     return ccdtInputStream;
/*     */   }
/*     */ 
/*     */   
/* 196 */   protected InputStream ccdtInputStream = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final URL ccdtUrl;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CCDTFileParser(JmqiEnvironment env, URL ccdtUrl, InputStream ccdtInputStream) {
/* 207 */     super(env);
/* 208 */     this.ccdtInputStream = ccdtInputStream;
/* 209 */     this.ccdtUrl = ccdtUrl;
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
/*     */   public Set<MQCDWrapper> getChannelDefinitions() {
/* 223 */     return this.channelDefinitions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LinkedList<MQAIR> getAuthInfoRecords() {
/* 230 */     return this.authInfoRecords;
/*     */   }
/*     */   
/*     */   abstract void parse() throws JmqiException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\CCDTFileParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */