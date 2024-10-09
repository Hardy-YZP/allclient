/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URI;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class PkiSpec
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/PkiSpec.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.ese.core.PkiSpec", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/PkiSpec.java");
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
/*  51 */   public String[] crlFiles = new String[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public URI[] crlUris = new URI[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkCDP = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public LdapConfig ldapConfig = new LdapConfig();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class LdapConfig
/*     */   {
/*  77 */     public List<PkiSpec.ConnectionConfig> connections = new LinkedList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ConnectionConfig
/*     */   {
/*  85 */     public String host = "";
/*  86 */     public String port = "389";
/*  87 */     public int index = 0;
/*     */     public int portNum;
/*     */     
/*     */     public ConnectionConfig(int idx, String host, String port) {
/*  91 */       if (Trace.isOn)
/*  92 */         Trace.entry(this, "com.ibm.mq.ese.core.ConnectionConfig", "<init>(int,String,String)", new Object[] {
/*  93 */               Integer.valueOf(idx), host, port
/*     */             }); 
/*  95 */       if (host != null) {
/*  96 */         this.host = host;
/*     */       }
/*  98 */       if (port != null) {
/*  99 */         this.port = port;
/*     */       }
/* 101 */       this.index = idx;
/* 102 */       if (Trace.isOn)
/* 103 */         Trace.exit(this, "com.ibm.mq.ese.core.ConnectionConfig", "<init>(int,String,String)"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\PkiSpec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */