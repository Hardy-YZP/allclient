/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
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
/*     */ class ManagedConnectionStore
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/ManagedConnectionStore.java";
/*     */   Hashtable<Tuple, Vector<StoredManagedConnection>> store;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.ManagedConnectionStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/ManagedConnectionStore.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ManagedConnectionStore() {
/*  64 */     super(MQSESSION.getJmqiEnv());
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
/*  78 */     this.store = new Hashtable<>();
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.ManagedConnectionStore", "<init>()"); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.ManagedConnectionStore", "<init>()"); 
/*     */   }
/*     */   void register(StoredManagedConnection smc) {
/*     */     Vector<StoredManagedConnection> bucket;
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.ManagedConnectionStore", "register(StoredManagedConnection)", new Object[] { smc });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     Object reqInfoKey = smc.cxReqInf.getKeyObject();
/*     */     
/*  97 */     Tuple hash = new Tuple(smc.mqMcf, reqInfoKey);
/*     */ 
/*     */     
/* 100 */     synchronized (this.store) {
/* 101 */       bucket = this.store.get(hash);
/* 102 */       if (bucket == null) {
/* 103 */         bucket = new Vector<>();
/* 104 */         this.store.put(hash, bucket);
/*     */       } 
/*     */     } 
/* 107 */     bucket.insertElementAt(smc, 0);
/*     */ 
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.ManagedConnectionStore", "register(StoredManagedConnection)");
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
/*     */   void deregister(StoredManagedConnection smc) {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.ManagedConnectionStore", "deregister(StoredManagedConnection)", new Object[] { smc });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     Object reqInfoKey = smc.cxReqInf.getKeyObject();
/*     */     
/* 134 */     Tuple hash = new Tuple(smc.mqMcf, reqInfoKey);
/*     */     
/* 136 */     Vector<StoredManagedConnection> bucket = this.store.get(hash);
/*     */ 
/*     */     
/* 139 */     if (bucket != null) {
/* 140 */       bucket.removeElement(smc);
/*     */     }
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.ManagedConnectionStore", "deregister(StoredManagedConnection)");
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
/*     */   StoredManagedConnection chooseOne(MQManagedConnectionFactory mqMcf, MQConnectionRequestInfo cxReqInf) {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.ManagedConnectionStore", "chooseOne(MQManagedConnectionFactory,MQConnectionRequestInfo)", new Object[] { mqMcf, cxReqInf });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     Tuple key = new Tuple(mqMcf, cxReqInf.getKeyObject());
/* 174 */     Vector<StoredManagedConnection> bucket = this.store.get(key);
/*     */ 
/*     */     
/* 177 */     if (bucket != null) {
/* 178 */       bucket = (Vector<StoredManagedConnection>)bucket.clone();
/* 179 */       for (StoredManagedConnection smc : bucket) {
/* 180 */         if (smc.mqManCon == null) {
/* 181 */           if (Trace.isOn) {
/* 182 */             Trace.exit(this, "com.ibm.mq.ManagedConnectionStore", "chooseOne(MQManagedConnectionFactory,MQConnectionRequestInfo)", null, 1);
/*     */           }
/*     */           
/* 185 */           return null;
/*     */         } 
/* 187 */         synchronized (smc.mqManCon) {
/*     */ 
/*     */           
/* 190 */           if (cxReqInf.variableIsSuitable(smc.mqManCon))
/*     */           {
/*     */             
/* 193 */             if (smc.use()) {
/* 194 */               if (Trace.isOn) {
/* 195 */                 Trace.exit(this, "com.ibm.mq.ManagedConnectionStore", "chooseOne(MQManagedConnectionFactory,MQConnectionRequestInfo)", smc, 2);
/*     */               }
/*     */               
/* 198 */               return smc;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.ManagedConnectionStore", "chooseOne(MQManagedConnectionFactory,MQConnectionRequestInfo)", null, 3);
/*     */     }
/*     */     
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class Tuple
/*     */   {
/*     */     MQManagedConnectionFactory o1;
/*     */ 
/*     */ 
/*     */     
/*     */     Object o2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Tuple(MQManagedConnectionFactory o1, Object o2) {
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.entry(this, "com.ibm.mq.Tuple", "<init>(MQManagedConnectionFactory,Object)", new Object[] { o1, o2 });
/*     */       }
/* 232 */       this.o1 = o1;
/* 233 */       this.o2 = o2;
/*     */       
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.exit(this, "com.ibm.mq.Tuple", "<init>(Object,Object)");
/*     */       }
/*     */     }
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
/*     */     public boolean equals(Object o) {
/* 250 */       if (Trace.isOn) {
/* 251 */         Trace.entry(this, "com.ibm.mq.Tuple", "equals(Object)", new Object[] { o });
/*     */       }
/* 253 */       boolean traceRet1 = false;
/* 254 */       if (o == null) {
/* 255 */         traceRet1 = false;
/* 256 */       } else if (!(o instanceof Tuple)) {
/* 257 */         traceRet1 = false;
/*     */       } else {
/* 259 */         Tuple t = (Tuple)o;
/* 260 */         traceRet1 = (this.o1.equals(t.o1) && this.o2.equals(t.o2));
/*     */       } 
/* 262 */       if (Trace.isOn) {
/* 263 */         Trace.exit(this, "com.ibm.mq.Tuple", "equals(Object)", Boolean.valueOf(traceRet1));
/*     */       }
/* 265 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.entry(this, "com.ibm.mq.Tuple", "hashCode()");
/*     */       }
/*     */       
/* 280 */       int traceRet1 = 0;
/* 281 */       if (null != this.o1) {
/* 282 */         traceRet1 = 13 * this.o1.hashCode();
/*     */       }
/* 284 */       if (null != this.o2) {
/* 285 */         traceRet1 += 17 * this.o2.hashCode();
/*     */       }
/*     */       
/* 288 */       if (Trace.isOn) {
/* 289 */         Trace.exit(this, "com.ibm.mq.Tuple", "hashCode()", Integer.valueOf(traceRet1));
/*     */       }
/* 291 */       return traceRet1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ManagedConnectionStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */