/*     */ package com.ibm.mq.ese.config;
/*     */ 
/*     */ import com.ibm.mq.ese.core.PkiSpec;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.security.Security;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
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
/*     */ public class PkiConfig
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/PkiConfig.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.ese.config.PkiConfig", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/PkiConfig.java");
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
/*  68 */   public PkiSpec pkiSpec = new PkiSpec();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static final Set<String> TRUE = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  79 */     TRUE.addAll(Arrays.asList(new String[] { "true", "TRUE", "on", "ON", "1", "yes" }));
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
/*     */   public PkiConfig(Properties props) throws ConfigException {
/*  92 */     if (props == null) {
/*  93 */       throw new IllegalArgumentException("null");
/*     */     }
/*  95 */     List<String> crlUriList = new LinkedList<>();
/*  96 */     List<String> crlFilesList = new LinkedList<>();
/*  97 */     for (Map.Entry<Object, Object> entry : props.entrySet()) {
/*  98 */       String key = entry.getKey().toString();
/*  99 */       if (key.toUpperCase().startsWith("CRL.URI")) {
/* 100 */         crlUriList.add(key); continue;
/* 101 */       }  if (key.toUpperCase().startsWith("CRL.FILE")) {
/* 102 */         crlFilesList.add(key); continue;
/* 103 */       }  if (key.toUpperCase().startsWith("OCSP.ENABLE")) {
/* 104 */         String value = entry.getValue().toString();
/* 105 */         if (value != null && TRUE.contains(value.toLowerCase())) {
/* 106 */           enableOCSP(); continue;
/*     */         } 
/* 108 */         disableOCSP();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 113 */     initCrlFiles(props, crlFilesList);
/* 114 */     initCrlUris(props, crlUriList);
/* 115 */     initCrlLdap(props);
/* 116 */     String checkCDP = getPropertyCaseInsensitive(props, "CRL.CDP");
/*     */     
/* 118 */     if (TRUE.contains(checkCDP)) {
/* 119 */       this.pkiSpec.checkCDP = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void enableOCSP() {
/* 125 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 129 */             Security.setProperty("ocsp.enable", "true");
/* 130 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void disableOCSP() {
/* 137 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 141 */             Security.setProperty("ocsp.enable", "false");
/* 142 */             return null;
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
/*     */   private void initCrlLdap(Properties props) {
/* 154 */     String host = getPropertyCaseInsensitive(props, "CRL.LDAP.HOST");
/*     */     
/* 156 */     String port = getPropertyCaseInsensitive(props, "CRL.LDAP.PORT");
/*     */ 
/*     */     
/* 159 */     PkiSpec.ConnectionConfig connectionConfig = null;
/* 160 */     if (host != null) {
/* 161 */       connectionConfig = new PkiSpec.ConnectionConfig(-1, host, port);
/* 162 */       this.pkiSpec.ldapConfig.connections.add(0, connectionConfig);
/*     */     } 
/*     */     
/* 165 */     for (int i = 1; i < 10; i++) {
/* 166 */       host = getPropertyCaseInsensitive(props, "CRL.LDAP.HOST." + i);
/*     */       
/* 168 */       port = getPropertyCaseInsensitive(props, "CRL.LDAP.PORT." + i);
/*     */       
/* 170 */       if (host != null) {
/* 171 */         connectionConfig = new PkiSpec.ConnectionConfig(i, host, port);
/* 172 */         this.pkiSpec.ldapConfig.connections.add(connectionConfig);
/*     */       } 
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
/*     */   private String getPropertyCaseInsensitive(Properties props, String key) {
/* 186 */     String value = props.getProperty(key);
/* 187 */     if (value != null) {
/* 188 */       return value.trim();
/*     */     }
/* 190 */     value = props.getProperty(key.toLowerCase());
/* 191 */     if (value != null) {
/* 192 */       return value.trim();
/*     */     }
/* 194 */     value = props.getProperty(key.toUpperCase());
/* 195 */     if (value != null) {
/* 196 */       return value.trim();
/*     */     }
/* 198 */     Set<?> keys = props.keySet();
/* 199 */     for (Iterator<?> iterator = keys.iterator(); iterator.hasNext(); ) {
/* 200 */       String key2 = (String)iterator.next();
/* 201 */       if (key.equalsIgnoreCase(key2)) {
/* 202 */         String value2 = props.getProperty(key2);
/* 203 */         return (value2 == null) ? null : value2.trim();
/*     */       } 
/*     */     } 
/* 206 */     return null;
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
/*     */   private void initCrlUris(Properties props, List<String> crlUriList) throws ConfigException {
/* 220 */     int i = 0;
/* 221 */     Collections.sort(crlUriList);
/* 222 */     this.pkiSpec.crlUris = new URI[crlUriList.size()];
/* 223 */     String enc = null;
/*     */     
/*     */     try {
/* 226 */       enc = AccessController.<String>doPrivileged(new PrivilegedExceptionAction()
/*     */           {
/*     */             public Object run()
/*     */             {
/* 230 */               return System.getProperty("file.encoding");
/*     */             }
/*     */           });
/*     */     }
/* 234 */     catch (PrivilegedActionException e1) {
/* 235 */       throw new ConfigException(e1.getException());
/*     */     } 
/*     */     
/* 238 */     for (String uriKey : crlUriList) {
/* 239 */       String uriValue = props.getProperty(uriKey);
/* 240 */       if (uriValue == null) {
/*     */         continue;
/*     */       }
/* 243 */       uriValue = uriValue.trim();
/* 244 */       if (enc == null) {
/* 245 */         enc = "UTF-8";
/*     */       }
/*     */       try {
/* 248 */         String[] arr = uriValue.split("://");
/* 249 */         String scheme = null;
/* 250 */         String ssp = null;
/* 251 */         if (arr.length > 1) {
/* 252 */           scheme = arr[0];
/* 253 */           ssp = arr[1];
/*     */         } else {
/* 255 */           scheme = "file";
/* 256 */           ssp = arr[0];
/*     */         } 
/* 258 */         if (!ssp.startsWith("/")) {
/* 259 */           HashMap<String, String> inserts = new HashMap<>();
/* 260 */           inserts.put("AMS_INSERT_FILENAME", uriKey);
/* 261 */           ConfigException ex = new ConfigException(AmsErrorMessages.mqo_s_usermap_error_relative_path, (HashMap)inserts);
/* 262 */           throw ex;
/*     */         } 
/* 264 */         this.pkiSpec.crlUris[i++] = new URI(scheme, ssp, null);
/*     */       }
/* 266 */       catch (URISyntaxException e) {
/* 267 */         ConfigException ex = new ConfigException(AmsErrorMessages.mqo_s_usermap_error_parsing_config_file);
/* 268 */         throw ex;
/*     */       } 
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
/*     */   private void initCrlFiles(Properties props, List<String> crlFilesList) {
/* 281 */     Collections.sort(crlFilesList);
/* 282 */     this.pkiSpec.crlFiles = new String[crlFilesList.size()];
/* 283 */     int i = 0;
/* 284 */     for (String key : crlFilesList) {
/* 285 */       String value = props.getProperty(key);
/* 286 */       if (value != null) {
/* 287 */         this.pkiSpec.crlFiles[i++] = value.trim();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate(List<String> list) {
/*     */     int i;
/* 300 */     for (i = 0; i < this.pkiSpec.crlUris.length; i++) {
/* 301 */       URI uri = this.pkiSpec.crlUris[i];
/* 302 */       String scheme = uri.getScheme();
/* 303 */       if (scheme == null || (scheme != null && !scheme.equals("file"))) {
/* 304 */         list.add("CRL.URI");
/*     */         break;
/*     */       } 
/*     */     } 
/* 308 */     for (i = 0; i < this.pkiSpec.crlFiles.length; i++) {
/* 309 */       String file = this.pkiSpec.crlFiles[i];
/* 310 */       if (file == null || file.length() == 0) {
/* 311 */         list.add("CRL.FILE");
/*     */         break;
/*     */       } 
/*     */     } 
/* 315 */     Iterator<?> iterator = this.pkiSpec.ldapConfig.connections.iterator();
/* 316 */     while (iterator.hasNext()) {
/* 317 */       PkiSpec.ConnectionConfig cfg = (PkiSpec.ConnectionConfig)iterator.next();
/* 318 */       if (cfg.port == null || cfg.port.length() == 0) {
/* 319 */         addConnError(list, "CRL.LDAP.PORT", cfg.index);
/*     */       }
/*     */       try {
/* 322 */         cfg.portNum = Integer.valueOf(cfg.port).intValue();
/*     */       }
/* 324 */       catch (Exception e) {
/* 325 */         addConnError(list, "CRL.LDAP.PORT", cfg.index);
/*     */       } 
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
/*     */   private void addConnError(List<String> list, String fieldname, int index) {
/* 339 */     if (index == -1) {
/* 340 */       list.add(fieldname);
/*     */     } else {
/* 342 */       list.add(fieldname + "." + index);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\config\PkiConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */