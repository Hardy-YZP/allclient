/*     */ package com.ibm.mq.ese.config;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
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
/*     */ public class ConfigException
/*     */   extends AMBIException
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/ConfigException.java";
/*     */   private static final long serialVersionUID = 1185159740655230555L;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.ese.config.ConfigException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/ConfigException.java");
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
/*     */   public ConfigException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  51 */     super(msg, messageid, explanation, useraction, inserts);
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.exit(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigException(Exception exception) {
/*  65 */     super(exception);
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.mq.ese.config.ConfigException", "<init>(Exception)", new Object[] { exception });
/*     */     }
/*     */     
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit(this, "com.ibm.mq.ese.config.ConfigException", "<init>(Exception)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigException(String messageid) {
/*  77 */     super(messageid);
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String)", new Object[] { messageid });
/*     */     }
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigException(String messageid, Throwable cause) {
/*  89 */     super(messageid, cause);
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*     */     }
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 101 */     super(messageid, inserts);
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 114 */     super(messageid, inserts, cause);
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/* 120 */     if (Trace.isOn)
/* 121 */       Trace.exit(this, "com.ibm.mq.ese.config.ConfigException", "<init>(String,HashMap<String , ? extends Object>,Throwable)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\config\ConfigException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */