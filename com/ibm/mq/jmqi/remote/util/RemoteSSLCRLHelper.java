/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.ConnectionName;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQAIR;
/*     */ import com.ibm.mq.jmqi.MQSCO;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.cert.CRL;
/*     */ import java.security.cert.CertStore;
/*     */ import java.security.cert.CertStoreException;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.CertificateFactory;
/*     */ import java.security.cert.LDAPCertStoreParameters;
/*     */ import java.security.cert.X509CRLSelector;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.naming.NamingException;
/*     */ import javax.security.auth.x500.X500Principal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteSSLCRLHelper
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteSSLCRLHelper.java";
/*     */   private RemoteConnection conn;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteSSLCRLHelper.java");
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
/*     */   public RemoteSSLCRLHelper(JmqiEnvironment env, RemoteConnection conn) {
/*  72 */     super(env);
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "<init>(JmqiEnvironment,RemoteConnection)", new Object[] { env, conn });
/*     */     }
/*     */     
/*  77 */     this.conn = conn;
/*     */     
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "<init>(JmqiEnvironment,RemoteConnection)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkCRL(X509Certificate cert, Collection<?> sslCertStores) throws JmqiException {
/*     */     Certificate certificate;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", new Object[] { cert, sslCertStores });
/*     */     }
/*     */ 
/*     */     
/*  97 */     if (sslCertStores.size() == 0) {
/*     */       
/*  99 */       if (Trace.isOn) {
/* 100 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     CertStore certStore = null;
/*     */ 
/*     */     
/* 112 */     Exception rcse = null;
/* 113 */     boolean searched_a_CRL = false;
/* 114 */     Iterator<?> it = sslCertStores.iterator();
/*     */     
/* 116 */     while (it.hasNext() && !searched_a_CRL) {
/*     */       try {
/* 118 */         certStore = (CertStore)it.next();
/*     */       }
/* 120 */       catch (ClassCastException cce) {
/* 121 */         if (Trace.isOn) {
/* 122 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", cce, 1);
/*     */         }
/*     */         
/* 125 */         if (rcse == null) {
/* 126 */           rcse = cce;
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 132 */       if (certStore == null) {
/* 133 */         if (rcse == null) {
/* 134 */           rcse = new NullPointerException();
/*     */         }
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */       
/* 142 */       X509CRLSelector certSelector = new X509CRLSelector();
/*     */       
/* 144 */       X500Principal p = cert.getIssuerX500Principal();
/*     */       
/* 146 */       certSelector.addIssuer(p);
/*     */       
/* 148 */       Collection<?> crls = new Vector();
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*     */         Certificate certToCheck;
/*     */ 
/*     */         
/*     */         try {
/* 157 */           crls = certStore.getCRLs(certSelector);
/*     */         }
/* 159 */         catch (CertStoreException cse) {
/* 160 */           if (Trace.isOn) {
/* 161 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", cse, 3);
/*     */           }
/*     */ 
/*     */           
/* 165 */           Throwable cause = cse.getCause();
/*     */           
/* 167 */           if (cause instanceof NamingException) {
/*     */             
/* 169 */             NamingException ne = (NamingException)cause;
/*     */             
/* 171 */             if (Trace.isOn) {
/* 172 */               Trace.traceData(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "Assuming Exception means no CRL was found, so nothing to check", ne);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 193 */             if (Trace.isOn) {
/* 194 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", cse, 3);
/*     */             }
/*     */             
/* 197 */             throw cse;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 211 */           CertificateFactory cf = CertificateFactory.getInstance("X509");
/* 212 */           certToCheck = cf.generateCertificate(new ByteArrayInputStream(cert.getEncoded()));
/*     */         }
/* 214 */         catch (CertificateException ce) {
/* 215 */           if (Trace.isOn) {
/* 216 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", ce, 4);
/*     */           }
/*     */ 
/*     */           
/* 220 */           HashMap<String, Object> info = new HashMap<>();
/* 221 */           info.put("CertificateException className", ce.getClass().getName());
/* 222 */           info.put("CertificateException message", ce.getMessage());
/* 223 */           info.put("Description", "A can't happen exception");
/* 224 */           Trace.ffst(this, "checkCRL(X509Certificate,Collection<?>)", "01", info, null);
/*     */           
/* 226 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, ce);
/*     */           
/* 228 */           if (Trace.isOn) {
/* 229 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", (Throwable)traceRet2, 4);
/*     */           }
/*     */           
/* 232 */           throw traceRet2;
/*     */         } 
/*     */         
/* 235 */         if (crls.size() == 0) {
/* 236 */           searched_a_CRL = true;
/*     */           continue;
/*     */         } 
/* 239 */         for (Iterator<?> i = crls.iterator(); i.hasNext(); ) {
/* 240 */           CRL crl = (CRL)i.next();
/* 241 */           if (crl.isRevoked(certToCheck)) {
/*     */             
/* 243 */             JmqiException traceRet4 = new JmqiException(this.env, 9633, new String[] { null, null, this.conn.getChannelName() }, 2, 2401, null);
/*     */             
/* 245 */             if (Trace.isOn) {
/* 246 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", (Throwable)traceRet4, 6);
/*     */             }
/*     */             
/* 249 */             throw traceRet4;
/*     */           } 
/* 251 */           searched_a_CRL = true;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 256 */       catch (CertStoreException e) {
/* 257 */         Certificate certToCheck; if (Trace.isOn) {
/* 258 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", (Throwable)certToCheck, 6);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 264 */         if (rcse == null)
/*     */         {
/* 266 */           certificate = certToCheck;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 271 */     if (!searched_a_CRL) {
/*     */       
/* 273 */       JmqiException mqex = new JmqiException(this.env, 9666, new String[] { null, null, this.conn.getChannelName() }, 2, 2402, (Throwable)certificate);
/* 274 */       if (Trace.isOn) {
/* 275 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", (Throwable)mqex, 7);
/*     */       }
/*     */       
/* 278 */       throw mqex;
/*     */     } 
/*     */     
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "checkCRL(X509Certificate,Collection<?>)", 2);
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
/*     */   public Collection<CertStore> processAuthInfoRecords(MQSCO sslConfig) throws JmqiException {
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "processAuthInfoRecords(MQSCO)", new Object[] { sslConfig });
/*     */     }
/*     */     
/* 298 */     Collection<CertStore> crlCollection = new ArrayList<>();
/* 299 */     MQAIR[] authInfoRecords = sslConfig.getAuthInfoRecords();
/* 300 */     for (int i = 0; i < sslConfig.getAuthInfoRecCount(); i++) {
/* 301 */       MQAIR authInfoRecord = authInfoRecords[i];
/* 302 */       String authInfoConnName = authInfoRecord.getAuthInfoConnName();
/* 303 */       ConnectionName connectionName = new ConnectionName(authInfoConnName, 389);
/* 304 */       String server = connectionName.getMachine();
/* 305 */       int port = connectionName.getPort();
/*     */       try {
/* 307 */         LDAPCertStoreParameters certStoreParms = new LDAPCertStoreParameters(server, port);
/* 308 */         CertStore certStore = CertStore.getInstance("LDAP", certStoreParms);
/* 309 */         crlCollection.add(certStore);
/*     */       }
/* 311 */       catch (InvalidAlgorithmParameterException iape) {
/* 312 */         if (Trace.isOn) {
/* 313 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "processAuthInfoRecords(MQSCO)", iape, 1);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 320 */       catch (Exception e) {
/* 321 */         if (Trace.isOn) {
/* 322 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "processAuthInfoRecords(MQSCO)", e, 2);
/*     */         }
/*     */ 
/*     */         
/* 326 */         JmqiException mqex = new JmqiException(this.env, 9666, new String[] { null, null, this.conn.getChannelName() }, 2, 2402, e);
/* 327 */         if (Trace.isOn) {
/* 328 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "processAuthInfoRecords(MQSCO)", (Throwable)mqex);
/*     */         }
/*     */         
/* 331 */         throw mqex;
/*     */       } 
/*     */     } 
/*     */     
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper", "processAuthInfoRecords(MQSCO)", crlCollection);
/*     */     }
/*     */     
/* 339 */     return crlCollection;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteSSLCRLHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */