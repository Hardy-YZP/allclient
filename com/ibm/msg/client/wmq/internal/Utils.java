/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.transaction.Transaction;
/*     */ import javax.transaction.TransactionManager;
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
/*     */ class Utils
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/Utils.java";
/*     */   static final int MQCA_BASE_Q_NAME = 2002;
/*     */   static final int MQCA_Q_MGR_NAME = 2015;
/*     */   static final int MQCA_Q_NAME = 2016;
/*     */   static final int MQCA_Q_MGR_IDENTIFIER = 2032;
/*     */   static final int MQCA_DEAD_LETTER_Q_NAME = 2006;
/*     */   static final int MQCA_BACKOUT_REQ_Q_NAME = 2019;
/*     */   static final int MQIA_DEFINITION_TYPE = 7;
/*     */   static final int MQIA_Q_TYPE = 20;
/*     */   static final int MQIA_BACKOUT_THRESHOLD = 22;
/*     */   private static final String CLASSNAME = "com.ibm.msg.client.wmq.jms.internal.Utils";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.wmq.internal.Utils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/Utils.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static Object xactionManager = null;
/*     */   private static boolean wasNotDetected = false;
/*  75 */   private static final Object xactionManagerLock = new Object();
/*     */   
/*     */   private static class NullTxManager
/*     */   {
/*     */     private NullTxManager() {}
/*     */   }
/*     */   
/*  82 */   static String tmProperty = "com.ibm.ws390.jta.TransactionManager";
/*  83 */   static String tmClassProperty = "com.ibm.ws390.jta.TransactionManager.class";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  89 */     PropertyStore.register(tmProperty, "com.ibm.ws390.tx.TransactionManagerImpl");
/*     */ 
/*     */     
/*  92 */     PropertyStore.register(tmClassProperty, new NullTxManager());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isRRSTransactionInProgress() {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()");
/*     */     }
/* 103 */     synchronized (xactionManagerLock) {
/*     */       
/* 105 */       if (wasNotDetected) {
/* 106 */         if (Trace.isOn) {
/* 107 */           Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Previous call did not detect a WAS zOS TransactionManager", null);
/*     */         }
/*     */         
/* 110 */         if (Trace.isOn) {
/* 111 */           Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 112 */               Boolean.valueOf(false), 1);
/*     */         }
/* 114 */         return false;
/*     */       } 
/*     */       
/* 117 */       if (xactionManager == null) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 122 */           CSSystem.dynamicLoadClass("javax.transaction.TransactionManager", Utils.class);
/*     */         }
/* 124 */         catch (ClassNotFoundException cnfe) {
/* 125 */           if (Trace.isOn) {
/* 126 */             Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", cnfe, 1);
/*     */           }
/*     */           
/* 129 */           if (Trace.isOn) {
/* 130 */             Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Class javax.transaction.TransactionManager not found; WAS zOS not detected", null);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 135 */           wasNotDetected = true;
/* 136 */           if (Trace.isOn) {
/* 137 */             Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 138 */                 Boolean.valueOf(false), 2);
/*     */           }
/* 140 */           return false;
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
/* 151 */         Object tmClass = PropertyStore.getObjectProperty(tmClassProperty);
/* 152 */         String classname = PropertyStore.getStringProperty(tmProperty);
/*     */         
/* 154 */         Method getTxMgrMth = null;
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 159 */           Class<?> txMgrClass = null;
/* 160 */           if (tmClass instanceof NullTxManager) {
/* 161 */             txMgrClass = CSSystem.dynamicLoadClass(classname, Utils.class);
/*     */           } else {
/* 163 */             txMgrClass = (Class)tmClass;
/*     */           } 
/* 165 */           getTxMgrMth = txMgrClass.getMethod("getTransactionManager", (Class[])null);
/*     */         }
/* 167 */         catch (NullPointerException npe) {
/* 168 */           if (Trace.isOn) {
/* 169 */             Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", npe, 2);
/*     */           }
/*     */           
/* 172 */           wasNotDetected = true;
/* 173 */           if (Trace.isOn) {
/* 174 */             Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 175 */                 Boolean.valueOf(false), 3);
/*     */           }
/* 177 */           return false;
/*     */         
/*     */         }
/* 180 */         catch (ClassNotFoundException cnfe) {
/* 181 */           if (Trace.isOn) {
/* 182 */             Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", cnfe, 3);
/*     */           }
/*     */           
/* 185 */           if (Trace.isOn) {
/* 186 */             Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Class " + classname + " not found; WAS OS/390 not detected", null);
/*     */           }
/*     */           
/* 189 */           wasNotDetected = true;
/* 190 */           if (Trace.isOn) {
/* 191 */             Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 192 */                 Boolean.valueOf(false), 4);
/*     */           }
/* 194 */           return false;
/*     */         }
/* 196 */         catch (NoSuchMethodException nsme) {
/* 197 */           if (Trace.isOn) {
/* 198 */             Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", nsme, 4);
/*     */           }
/*     */           
/* 201 */           if (Trace.isOn) {
/* 202 */             Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Method getTransactionManager() not found on tm implementation", null);
/*     */           }
/*     */ 
/*     */           
/* 206 */           wasNotDetected = true;
/* 207 */           if (Trace.isOn) {
/* 208 */             Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 209 */                 Boolean.valueOf(false), 5);
/*     */           }
/* 211 */           return false;
/*     */         } 
/*     */ 
/*     */         
/*     */         try {
/* 216 */           TransactionManager txMgr = (TransactionManager)getTxMgrMth.invoke(null, (Object[])null);
/*     */           
/* 218 */           if (txMgr == null) {
/* 219 */             if (Trace.isOn)
/*     */             {
/* 221 */               Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "No TransactionManager present. Reverting to local transaction behaviour for this JVM", null);
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 227 */             wasNotDetected = true;
/* 228 */             if (Trace.isOn) {
/* 229 */               Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 230 */                   Boolean.valueOf(false), 6);
/*     */             }
/* 232 */             return false;
/*     */           } 
/*     */           
/* 235 */           xactionManager = txMgr;
/*     */         }
/* 237 */         catch (InvocationTargetException ite) {
/* 238 */           if (Trace.isOn) {
/* 239 */             Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", ite, 5);
/*     */           }
/*     */           
/* 242 */           if (Trace.isOn) {
/* 243 */             Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Error when finding TransactionManager", null);
/*     */           }
/*     */           
/* 246 */           wasNotDetected = true;
/* 247 */           if (Trace.isOn) {
/* 248 */             Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 249 */                 Boolean.valueOf(false), 7);
/*     */           }
/* 251 */           return false;
/*     */         }
/* 253 */         catch (IllegalAccessException iae) {
/* 254 */           if (Trace.isOn) {
/* 255 */             Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", iae, 6);
/*     */           }
/*     */           
/* 258 */           if (Trace.isOn) {
/* 259 */             Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Error when finding TransactionManager", null);
/*     */           }
/*     */           
/* 262 */           wasNotDetected = true;
/* 263 */           if (Trace.isOn) {
/* 264 */             Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 265 */                 Boolean.valueOf(false), 8);
/*     */           }
/* 267 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     Transaction tran = null;
/*     */     try {
/* 278 */       TransactionManager txMgr = (TransactionManager)xactionManager;
/* 279 */       tran = txMgr.getTransaction();
/*     */     }
/* 281 */     catch (Exception e) {
/* 282 */       if (Trace.isOn) {
/* 283 */         Trace.catchBlock("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", e, 7);
/*     */       }
/*     */       
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Unexpected exception while getting Transaction", null);
/*     */       }
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 291 */             Boolean.valueOf(false), 9);
/*     */       }
/* 293 */       return false;
/*     */     } 
/*     */     
/* 296 */     if (Trace.isOn) {
/* 297 */       if (tran == null) {
/* 298 */         Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "No global transaction present.", null);
/*     */       } else {
/* 300 */         Trace.traceData("com.ibm.msg.client.wmq.jms.internal.Utils", "Global transaction active on this thread.", null);
/*     */       } 
/*     */     }
/*     */     
/* 304 */     boolean traceRet2 = (tran != null);
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.exit("com.ibm.msg.client.wmq.internal.Utils", "isRRSTransactionInProgress()", 
/* 307 */           Boolean.valueOf(traceRet2), 10);
/*     */     }
/* 309 */     return traceRet2;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */