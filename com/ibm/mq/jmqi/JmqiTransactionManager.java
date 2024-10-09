/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.transaction.HeuristicMixedException;
/*     */ import javax.transaction.HeuristicRollbackException;
/*     */ import javax.transaction.InvalidTransactionException;
/*     */ import javax.transaction.NotSupportedException;
/*     */ import javax.transaction.RollbackException;
/*     */ import javax.transaction.SystemException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiTransactionManager
/*     */   extends JmqiObject
/*     */   implements TransactionManager
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiTransactionManager.java";
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.jmqi.JmqiTransactionManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiTransactionManager.java");
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
/*     */   public JmqiTransactionManager(JmqiEnvironment env) {
/*  56 */     super(env);
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void begin() throws NotSupportedException, SystemException {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "begin()");
/*     */     }
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "begin()");
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
/*     */   public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "commit()");
/*     */     }
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "commit()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatus() throws SystemException {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "getStatus()", "getter", 
/* 106 */           Integer.valueOf(0));
/*     */     }
/* 108 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transaction getTransaction() throws SystemException {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "getTransaction()", "getter", null);
/*     */     }
/*     */     
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resume(Transaction arg0) throws InvalidTransactionException, IllegalStateException, SystemException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "resume(Transaction)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "resume(Transaction)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rollback() throws IllegalStateException, SecurityException, SystemException {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "rollback()");
/*     */     }
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "rollback()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRollbackOnly() throws IllegalStateException, SystemException {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "setRollbackOnly()");
/*     */     }
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "setRollbackOnly()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransactionTimeout(int arg0) throws SystemException {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "setTransactionTimeout(int)", "setter", 
/* 179 */           Integer.valueOf(arg0));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transaction suspend() throws SystemException {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "suspend()");
/*     */     }
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiTransactionManager", "suspend()", null);
/*     */     }
/* 195 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiTransactionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */