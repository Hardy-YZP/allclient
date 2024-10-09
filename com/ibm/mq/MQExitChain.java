/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class MQExitChain
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQExitChain.java";
/*     */   static final String className = "MQExitChain";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.MQExitChain", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQExitChain.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   ImmutableList collection = null;
/*  53 */   Vector<?> internals = null;
/*  54 */   protected int reasonCode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQExitChain() {
/*  60 */     super(JmqiSESSION.getJmqiEnv());
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.mq.MQExitChain", "<init>()");
/*     */     }
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data(this, "<init>()", "sccsid", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQExitChain.java");
/*     */     }
/*     */     
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.MQExitChain", "<init>()");
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
/*     */   public List getExitChain() {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.data(this, "com.ibm.mq.MQExitChain", "getExitChain()", "getter", this.collection);
/*     */     }
/*  84 */     return this.collection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReasonCode() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data(this, "com.ibm.mq.MQExitChain", "getReasonCode()", "getter", 
/*  96 */           Integer.valueOf(this.reasonCode));
/*     */     }
/*  98 */     return this.reasonCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Vector straighten(List collection) {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry("com.ibm.mq.MQExitChain", "straighten(List)", new Object[] { collection });
/*     */     }
/*     */     
/* 113 */     Vector<Object> vector = new Vector();
/* 114 */     Iterator iterator = collection.iterator();
/* 115 */     while (iterator.hasNext()) {
/* 116 */       Object object = iterator.next();
/* 117 */       if (object instanceof MQExitChain) {
/* 118 */         List list = ((MQExitChain)object).getExitChain();
/* 119 */         vector.addAll(straighten(list)); continue;
/*     */       } 
/* 121 */       vector.add(object);
/*     */     } 
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit("com.ibm.mq.MQExitChain", "straighten(List)", vector);
/*     */     }
/* 127 */     return vector;
/*     */   }
/*     */   
/*     */   static class ImmutableList
/*     */     extends AbstractList
/*     */   {
/* 133 */     private Object[] objects = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ImmutableList(List list) {
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.entry(this, "com.ibm.mq.ImmutableList", "<init>(List)", new Object[] { list });
/*     */       }
/* 144 */       if (list == null) {
/*     */         
/* 146 */         if (Trace.isOn) {
/* 147 */           Trace.exit(this, "com.ibm.mq.ImmutableList", "<init>(List)", 1);
/*     */         }
/*     */         return;
/*     */       } 
/* 151 */       int length = list.size();
/* 152 */       this.objects = new Object[length];
/* 153 */       for (int i = 0; i < length; i++) {
/* 154 */         this.objects[i] = list.get(i);
/*     */       }
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.exit(this, "com.ibm.mq.ImmutableList", "<init>(List)", 2);
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
/*     */     public int size() {
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.entry(this, "com.ibm.mq.ImmutableList", "size()");
/*     */       }
/* 173 */       if (this.objects == null) {
/*     */         
/* 175 */         if (Trace.isOn) {
/* 176 */           Trace.exit(this, "com.ibm.mq.ImmutableList", "size()", Integer.valueOf(0), 1);
/*     */         }
/* 178 */         return 0;
/*     */       } 
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.exit(this, "com.ibm.mq.ImmutableList", "size()", Integer.valueOf(this.objects.length), 2);
/*     */       }
/*     */       
/* 184 */       return this.objects.length;
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
/*     */     public Object get(int index) {
/* 196 */       if (Trace.isOn)
/* 197 */         Trace.entry(this, "com.ibm.mq.ImmutableList", "get(int)", new Object[] {
/* 198 */               Integer.valueOf(index)
/*     */             }); 
/* 200 */       if (Trace.isOn) {
/* 201 */         Trace.exit(this, "com.ibm.mq.ImmutableList", "get(int)", this.objects[index]);
/*     */       }
/* 203 */       return this.objects[index];
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQExitChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */