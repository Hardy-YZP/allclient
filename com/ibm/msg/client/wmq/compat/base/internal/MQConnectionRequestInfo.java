/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ abstract class MQConnectionRequestInfo
/*     */   implements Cloneable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQConnectionRequestInfo.java";
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQConnectionRequestInfo.java");
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
/*     */   class DefaultKeyObject
/*     */   {
/*     */     public boolean equals(Object obj) {
/*  83 */       if (Trace.isOn) {
/*  84 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "equals(Object)", new Object[] { obj });
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       if (this == obj) {
/*  91 */         if (Trace.isOn) {
/*  92 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "equals(Object)", 
/*  93 */               Boolean.valueOf(true), 1);
/*     */         }
/*  95 */         return true;
/*     */       } 
/*     */ 
/*     */       
/*  99 */       if (obj instanceof DefaultKeyObject) {
/*     */ 
/*     */         
/* 102 */         DefaultKeyObject myObj = (DefaultKeyObject)obj;
/*     */ 
/*     */ 
/*     */         
/* 106 */         boolean traceRet1 = MQConnectionRequestInfo.this.fixedEquals(myObj.getOwner());
/* 107 */         if (Trace.isOn) {
/* 108 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "equals(Object)", 
/* 109 */               Boolean.valueOf(traceRet1), 2);
/*     */         }
/* 111 */         return traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 116 */       if (Trace.isOn) {
/* 117 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "equals(Object)", 
/* 118 */             Boolean.valueOf(false), 3);
/*     */       }
/* 120 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MQConnectionRequestInfo getOwner() {
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "getOwner()", "getter", MQConnectionRequestInfo.this);
/*     */       }
/*     */       
/* 133 */       return MQConnectionRequestInfo.this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "hashCode()");
/*     */       }
/*     */       
/* 146 */       int traceRet1 = MQConnectionRequestInfo.this.fixedHashCode();
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.DefaultKeyObject", "hashCode()", 
/* 149 */             Integer.valueOf(traceRet1));
/*     */       }
/* 151 */       return traceRet1;
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
/*     */   protected boolean hasVariablePortion = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   Object keyObject = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean equals(Object obj) {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 187 */     if (!(obj instanceof MQConnectionRequestInfo)) {
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "equals(Object)", 
/* 190 */             Boolean.valueOf(false), 1);
/*     */       }
/* 192 */       return false;
/*     */     } 
/*     */     
/* 195 */     if (fixedEquals(obj)) {
/*     */ 
/*     */       
/* 198 */       if (this.hasVariablePortion) {
/*     */ 
/*     */ 
/*     */         
/* 202 */         MQConnectionRequestInfo mqObj = (MQConnectionRequestInfo)obj;
/*     */ 
/*     */ 
/*     */         
/* 206 */         boolean traceRet1 = variableEquals(mqObj);
/* 207 */         if (Trace.isOn) {
/* 208 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "equals(Object)", 
/* 209 */               Boolean.valueOf(traceRet1), 2);
/*     */         }
/* 211 */         return traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "equals(Object)", 
/* 219 */             Boolean.valueOf(true), 3);
/*     */       }
/* 221 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "equals(Object)", 
/* 228 */           Boolean.valueOf(false), 4);
/*     */     }
/* 230 */     return false;
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
/*     */ 
/*     */ 
/*     */   
/*     */   Object getKeyObject() {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "getKeyObject()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 268 */     if (this.hasVariablePortion) {
/*     */ 
/*     */       
/* 271 */       if (this.keyObject == null)
/*     */       {
/* 273 */         this.keyObject = new DefaultKeyObject();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 278 */       if (Trace.isOn) {
/* 279 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "getKeyObject()", this.keyObject, 1);
/*     */       }
/*     */       
/* 282 */       return this.keyObject;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "getKeyObject()", this, 2);
/*     */     }
/*     */     
/* 291 */     return this;
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
/*     */   public final int hashCode() {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "hashCode()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 312 */     int hc = fixedHashCode();
/*     */ 
/*     */     
/* 315 */     if (this.hasVariablePortion) {
/* 316 */       hc += variableHashCode();
/*     */     }
/*     */ 
/*     */     
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "hashCode()", 
/* 322 */           Integer.valueOf(hc));
/*     */     }
/* 324 */     return hc;
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
/*     */   final boolean isSuitable(MQManagedConnectionJ11 mc) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", new Object[] { mc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 346 */     MQConnectionRequestInfo initialCxReqInf = mc.getInitialCxReqInf();
/*     */ 
/*     */ 
/*     */     
/* 350 */     if (fixedEquals(initialCxReqInf)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 356 */       if (this.hasVariablePortion) {
/*     */ 
/*     */ 
/*     */         
/* 360 */         boolean traceRet1 = variableIsSuitable(mc);
/* 361 */         if (Trace.isOn) {
/* 362 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", 
/* 363 */               Boolean.valueOf(traceRet1), 1);
/*     */         }
/* 365 */         return traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 372 */       if (Trace.isOn) {
/* 373 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", 
/* 374 */             Boolean.valueOf(true), 2);
/*     */       }
/* 376 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", 
/* 384 */           Boolean.valueOf(false), 3);
/*     */     }
/* 386 */     return false;
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
/*     */   protected final boolean objEquals(Object o1, Object o2) {
/* 399 */     boolean retVal = false;
/*     */ 
/*     */     
/* 402 */     if (o1 == null && o2 == null) {
/* 403 */       retVal = true;
/*     */     }
/* 405 */     else if (o1 != null && o2 != null) {
/*     */ 
/*     */       
/* 408 */       retVal = o1.equals(o2);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 414 */     return retVal;
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
/*     */   boolean variableEquals(MQConnectionRequestInfo obj) {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "variableEquals(MQConnectionRequestInfo)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "variableEquals(MQConnectionRequestInfo)", 
/* 442 */           Boolean.valueOf(true));
/*     */     }
/* 444 */     return true;
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
/*     */   public int variableHashCode() {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "variableHashCode()");
/*     */     }
/*     */     
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "variableHashCode()", 
/* 467 */           Integer.valueOf(0));
/*     */     }
/* 469 */     return 0;
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
/*     */   public boolean variableIsSuitable(MQManagedConnectionJ11 mc) {
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", new Object[] { mc });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/* 492 */           Boolean.valueOf(!this.hasVariablePortion));
/*     */     }
/* 494 */     return !this.hasVariablePortion;
/*     */   }
/*     */   
/*     */   abstract boolean fixedEquals(Object paramObject);
/*     */   
/*     */   public abstract int fixedHashCode();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQConnectionRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */