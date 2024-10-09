/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
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
/*     */ abstract class MQConnectionRequestInfo
/*     */   extends JmqiObject
/*     */   implements Cloneable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionRequestInfo.java";
/*     */   protected boolean hasVariablePortion;
/*     */   Object keyObject;
/*     */   
/*     */   static {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.data("com.ibm.mq.MQConnectionRequestInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionRequestInfo.java");
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
/*     */   public MQConnectionRequestInfo() {
/*  74 */     super(MQSESSION.getJmqiEnv());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.hasVariablePortion = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     this.keyObject = null;
/*     */     if (Trace.isOn) {
/*     */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "<init>()");
/*     */     }
/*     */     if (Trace.isOn) {
/*     */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int hashCode() {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "hashCode()");
/*     */     }
/*     */ 
/*     */     
/* 121 */     int hc = fixedHashCode();
/*     */     
/* 123 */     if (this.hasVariablePortion) {
/* 124 */       hc += variableHashCode();
/*     */     }
/*     */ 
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "hashCode()", Integer.valueOf(hc));
/*     */     }
/* 131 */     return hc;
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
/*     */   public int variableHashCode() {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "variableHashCode()");
/*     */     }
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "variableHashCode()", 
/* 160 */           Integer.valueOf(0));
/*     */     }
/* 162 */     return 0;
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
/*     */   public final boolean equals(Object obj) {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 183 */     if (!(obj instanceof MQConnectionRequestInfo)) {
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "equals(Object)", 
/* 186 */             Boolean.valueOf(false), 1);
/*     */       }
/* 188 */       return false;
/*     */     } 
/*     */     
/* 191 */     if (fixedEquals(obj)) {
/*     */ 
/*     */       
/* 194 */       if (this.hasVariablePortion) {
/*     */ 
/*     */ 
/*     */         
/* 198 */         MQConnectionRequestInfo mqObj = (MQConnectionRequestInfo)obj;
/*     */ 
/*     */ 
/*     */         
/* 202 */         boolean traceRet1 = variableEquals(mqObj);
/*     */         
/* 204 */         if (Trace.isOn) {
/* 205 */           Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "equals(Object)", 
/* 206 */               Boolean.valueOf(traceRet1), 2);
/*     */         }
/* 208 */         return traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 213 */       if (Trace.isOn) {
/* 214 */         Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "equals(Object)", 
/* 215 */             Boolean.valueOf(true), 3);
/*     */       }
/* 217 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "equals(Object)", 
/* 224 */           Boolean.valueOf(false), 4);
/*     */     }
/* 226 */     return false;
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
/*     */   boolean variableEquals(MQConnectionRequestInfo obj) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "variableEquals(MQConnectionRequestInfo)", new Object[] { obj });
/*     */     }
/*     */     
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "variableEquals(MQConnectionRequestInfo)", 
/* 258 */           Boolean.valueOf(true));
/*     */     }
/* 260 */     return true;
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
/*     */   final boolean isSuitable(MQManagedConnectionJ11 mc) {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", new Object[] { mc });
/*     */     }
/*     */ 
/*     */     
/* 282 */     MQConnectionRequestInfo initialCxReqInf = mc.initialCxReqInf;
/*     */ 
/*     */     
/* 285 */     if (fixedEquals(initialCxReqInf)) {
/*     */ 
/*     */ 
/*     */       
/* 289 */       if (this.hasVariablePortion) {
/*     */ 
/*     */         
/* 292 */         boolean traceRet1 = variableIsSuitable(mc);
/*     */         
/* 294 */         if (Trace.isOn) {
/* 295 */           Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", 
/* 296 */               Boolean.valueOf(traceRet1), 1);
/*     */         }
/* 298 */         return traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 305 */       if (Trace.isOn) {
/* 306 */         Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", 
/* 307 */             Boolean.valueOf(true), 2);
/*     */       }
/* 309 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "isSuitable(MQManagedConnectionJ11)", 
/* 316 */           Boolean.valueOf(false), 3);
/*     */     }
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean variableIsSuitable(MQManagedConnectionJ11 mc) {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", new Object[] { mc });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/* 338 */           Boolean.valueOf(!this.hasVariablePortion));
/*     */     }
/* 340 */     return !this.hasVariablePortion;
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
/*     */   protected final boolean objEquals(Object o1, Object o2) {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "objEquals(Object,Object)", new Object[] { o1, o2 });
/*     */     }
/*     */     
/* 362 */     boolean retVal = false;
/*     */     
/* 364 */     if (o1 == null && o2 == null) {
/* 365 */       retVal = true;
/* 366 */     } else if (o1 != null && o2 != null) {
/*     */ 
/*     */       
/* 369 */       retVal = o1.equals(o2);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "objEquals(Object,Object)", 
/* 376 */           Boolean.valueOf(retVal));
/*     */     }
/* 378 */     return retVal;
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
/*     */   Object getKeyObject() {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.entry(this, "com.ibm.mq.MQConnectionRequestInfo", "getKeyObject()");
/*     */     }
/*     */ 
/*     */     
/* 401 */     if (this.hasVariablePortion) {
/*     */       
/* 403 */       if (this.keyObject == null)
/*     */       {
/* 405 */         this.keyObject = new DefaultKeyObject();
/*     */       }
/*     */ 
/*     */       
/* 409 */       if (Trace.isOn) {
/* 410 */         Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "getKeyObject()", this.keyObject, 1);
/*     */       }
/* 412 */       return this.keyObject;
/*     */     } 
/*     */ 
/*     */     
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.exit(this, "com.ibm.mq.MQConnectionRequestInfo", "getKeyObject()", this, 2);
/*     */     }
/* 419 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int fixedHashCode();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean fixedEquals(Object paramObject);
/*     */ 
/*     */ 
/*     */   
/*     */   class DefaultKeyObject
/*     */   {
/*     */     MQConnectionRequestInfo getOwner() {
/* 437 */       if (Trace.isOn) {
/* 438 */         Trace.data(this, "com.ibm.mq.DefaultKeyObject", "getOwner()", "getter", MQConnectionRequestInfo.this);
/*     */       }
/*     */       
/* 441 */       return MQConnectionRequestInfo.this;
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
/*     */     public boolean equals(Object obj) {
/* 453 */       if (Trace.isOn) {
/* 454 */         Trace.entry(this, "com.ibm.mq.DefaultKeyObject", "equals(Object)", new Object[] { obj });
/*     */       }
/*     */ 
/*     */       
/* 458 */       if (this == obj) {
/*     */         
/* 460 */         if (Trace.isOn) {
/* 461 */           Trace.exit(this, "com.ibm.mq.DefaultKeyObject", "equals(Object)", Boolean.valueOf(true), 1);
/*     */         }
/*     */         
/* 464 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 468 */       if (obj instanceof DefaultKeyObject) {
/*     */ 
/*     */         
/* 471 */         DefaultKeyObject myObj = (DefaultKeyObject)obj;
/*     */ 
/*     */ 
/*     */         
/* 475 */         boolean traceRet1 = MQConnectionRequestInfo.this.fixedEquals(myObj.getOwner());
/* 476 */         if (Trace.isOn) {
/* 477 */           Trace.exit(this, "com.ibm.mq.DefaultKeyObject", "equals(Object)", 
/* 478 */               Boolean.valueOf(traceRet1), 2);
/*     */         }
/* 480 */         return traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 485 */       if (Trace.isOn) {
/* 486 */         Trace.exit(this, "com.ibm.mq.DefaultKeyObject", "equals(Object)", Boolean.valueOf(false), 3);
/*     */       }
/*     */       
/* 489 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 499 */       if (Trace.isOn) {
/* 500 */         Trace.entry(this, "com.ibm.mq.DefaultKeyObject", "hashCode()");
/*     */       }
/* 502 */       int traceRet1 = MQConnectionRequestInfo.this.fixedHashCode();
/*     */       
/* 504 */       if (Trace.isOn) {
/* 505 */         Trace.exit(this, "com.ibm.mq.DefaultKeyObject", "hashCode()", Integer.valueOf(traceRet1));
/*     */       }
/* 507 */       return traceRet1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQConnectionRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */