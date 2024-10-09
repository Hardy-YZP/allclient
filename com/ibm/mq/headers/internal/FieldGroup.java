/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.internal.MQCommonServices;
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
/*     */ public abstract class FieldGroup
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/FieldGroup.java";
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.headers.internal.FieldGroup", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/FieldGroup.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected FieldGroup() {
/*  47 */     super(MQCommonServices.jmqiEnv);
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "<init>()");
/*     */     }
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "<init>()");
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
/*     */   public HeaderField addMQLong(String name) {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLong(String)", new Object[] { name });
/*     */     }
/*     */     
/*  74 */     HeaderField traceRet1 = addField(new MQLongField(nextFieldOffset(), name));
/*     */     
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLong(String)", traceRet1);
/*     */     }
/*  79 */     return traceRet1;
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
/*     */   public HeaderField addMQLong(String name, int defaultValue) {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLong(String,int)", new Object[] { name, 
/*  93 */             Integer.valueOf(defaultValue) });
/*     */     }
/*  95 */     HeaderField traceRet1 = addMQLong(name, defaultValue, false);
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLong(String,int)", traceRet1);
/*     */     }
/*     */     
/* 101 */     return traceRet1;
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
/*     */   public HeaderField addMQLong(String name, int defaultValue, boolean fixed) {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLong(String,int,boolean)", new Object[] { name, 
/* 116 */             Integer.valueOf(defaultValue), Boolean.valueOf(fixed) });
/*     */     }
/* 118 */     HeaderField traceRet1 = addField(new MQLongField(nextFieldOffset(), name, defaultValue, fixed));
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLong(String,int,boolean)", traceRet1);
/*     */     }
/*     */     
/* 124 */     return traceRet1;
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
/*     */   public HeaderField addMQLongArray(String name, HeaderField lengthRef, HeaderField strucLengthRef) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLongArray(String,HeaderField,HeaderField)", new Object[] { name, lengthRef, strucLengthRef });
/*     */     }
/*     */ 
/*     */     
/* 149 */     HeaderField traceRet1 = addField(new MQLongArrayField(nextFieldOffset(), name, (MQLongField)lengthRef, (MQLongField)strucLengthRef));
/*     */ 
/*     */     
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQLongArray(String,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 156 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderField addMQInt64(String name) {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQInt64(String)", new Object[] { name });
/*     */     }
/*     */     
/* 170 */     HeaderField traceRet1 = addField(new MQInt64Field(nextFieldOffset(), name));
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQInt64(String)", traceRet1);
/*     */     }
/* 175 */     return traceRet1;
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
/*     */   public HeaderField addMQInt64Array(String name, HeaderField lengthRef, HeaderField strucLengthRef) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQInt64Array(String,HeaderField,HeaderField)", new Object[] { name, lengthRef, strucLengthRef });
/*     */     }
/*     */ 
/*     */     
/* 200 */     HeaderField traceRet1 = addField(new MQInt64ArrayField(nextFieldOffset(), name, (MQLongField)lengthRef, (MQLongField)strucLengthRef));
/*     */ 
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQInt64Array(String,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 207 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderField addMQChar(String name, int length) {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,int)", new Object[] { name, 
/* 220 */             Integer.valueOf(length) });
/*     */     }
/* 222 */     HeaderField traceRet1 = addField(new MQCharField(nextFieldOffset(), name, length));
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,int)", traceRet1);
/*     */     }
/*     */     
/* 228 */     return traceRet1;
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
/*     */   public HeaderField addMQChar(String name, String defaultValue) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,String)", new Object[] { name, defaultValue });
/*     */     }
/*     */     
/* 245 */     HeaderField traceRet1 = addField(new MQCharField(nextFieldOffset(), name, defaultValue));
/*     */     
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,String)", traceRet1);
/*     */     }
/*     */     
/* 251 */     return traceRet1;
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
/*     */   public HeaderField addMQChar(String name, String defaultValue, boolean fixed) {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,String,boolean)", new Object[] { name, defaultValue, 
/*     */             
/* 268 */             Boolean.valueOf(fixed) });
/*     */     }
/* 270 */     HeaderField traceRet1 = addField(new MQCharField(nextFieldOffset(), name, defaultValue, fixed));
/*     */     
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,String,boolean)", traceRet1);
/*     */     }
/*     */     
/* 276 */     return traceRet1;
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
/*     */   public HeaderField addMQChar(String name, HeaderField lengthRef, HeaderField strucLengthRef) {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,HeaderField,HeaderField)", new Object[] { name, lengthRef, strucLengthRef });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     HeaderField traceRet1 = addField(new MQCharFieldVariableLength(nextFieldOffset(), name, (MQLongField)lengthRef, (MQLongField)strucLengthRef));
/*     */ 
/*     */     
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 310 */     return traceRet1;
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
/*     */   public HeaderField addMQChar(String name, HeaderField lengthRef, HeaderField totalLengthRef, HeaderField ccsidRef) {
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,HeaderField,HeaderField,HeaderField)", new Object[] { name, lengthRef, totalLengthRef, ccsidRef });
/*     */     }
/*     */ 
/*     */     
/* 338 */     HeaderField traceRet1 = addField(new MQCharFieldVariableLength(nextFieldOffset(), name, (MQLongField)lengthRef, (MQLongField)totalLengthRef, (MQLongField)ccsidRef));
/*     */ 
/*     */     
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQChar(String,HeaderField,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 345 */     return traceRet1;
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
/*     */   public HeaderField addMQCharArray(String name, HeaderField sizeRef, HeaderField lengthRef, HeaderField totalLengthRef) {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQCharArray(String,HeaderField,HeaderField,HeaderField)", new Object[] { name, sizeRef, lengthRef, totalLengthRef });
/*     */     }
/*     */ 
/*     */     
/* 372 */     HeaderField traceRet1 = addField(new MQCharArrayField(nextFieldOffset(), name, (MQLongField)sizeRef, (MQLongField)lengthRef, (MQLongField)totalLengthRef));
/*     */ 
/*     */     
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQCharArray(String,HeaderField,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 379 */     return traceRet1;
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
/*     */   public HeaderField addMQCharArray(String name, HeaderField sizeRef, HeaderField lengthRef, HeaderField strucLengthRef, HeaderField ccsidRef) {
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQCharArray(String,HeaderField,HeaderField,HeaderField,HeaderField)", new Object[] { name, sizeRef, lengthRef, strucLengthRef, ccsidRef });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 410 */     HeaderField traceRet1 = addField(new MQCharArrayField(nextFieldOffset(), name, (MQLongField)sizeRef, (MQLongField)lengthRef, (MQLongField)strucLengthRef, (MQLongField)ccsidRef));
/*     */ 
/*     */ 
/*     */     
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQCharArray(String,HeaderField,HeaderField,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 418 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderField addMQByte(String name, int length) {
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQByte(String,int)", new Object[] { name, 
/* 431 */             Integer.valueOf(length) });
/*     */     }
/* 433 */     HeaderField traceRet1 = addField(new MQByteField(nextFieldOffset(), name, length));
/*     */     
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQByte(String,int)", traceRet1);
/*     */     }
/*     */     
/* 439 */     return traceRet1;
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
/*     */   public HeaderField addMQByte(String name, HeaderField lengthRef, HeaderField totalLengthRef) {
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQByte(String,HeaderField,HeaderField)", new Object[] { name, lengthRef, totalLengthRef });
/*     */     }
/*     */ 
/*     */     
/* 464 */     HeaderField traceRet1 = addField(new MQByteFieldVariableLength(nextFieldOffset(), name, (MQLongField)lengthRef, (MQLongField)totalLengthRef));
/*     */ 
/*     */     
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQByte(String,HeaderField,HeaderField)", traceRet1);
/*     */     }
/*     */     
/* 471 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderField addMQHeader(String name, Header header) {
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQHeader(String,Header)", new Object[] { name, header });
/*     */     }
/*     */     
/* 486 */     HeaderField traceRet1 = addField(new MQHeaderField(nextFieldOffset(), name, header));
/*     */     
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQHeader(String,Header)", traceRet1);
/*     */     }
/*     */     
/* 492 */     return traceRet1;
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
/*     */   public HeaderField addMQHeader(String name, HeaderType headerType, Class<?> headerClass) {
/* 504 */     if (Trace.isOn) {
/* 505 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQHeader(String,HeaderType,Class<?>)", new Object[] { name, headerType, headerClass });
/*     */     }
/*     */     
/* 508 */     HeaderField traceRet1 = addField(new MQHeaderField(nextFieldOffset(), name, headerType, headerClass));
/*     */ 
/*     */     
/* 511 */     if (Trace.isOn) {
/* 512 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addMQHeader(String,HeaderType,Class<?>)", traceRet1);
/*     */     }
/*     */     
/* 515 */     return traceRet1;
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
/*     */   public FieldGroup addFieldGroup(HeaderField trigger, int value) {
/* 530 */     if (Trace.isOn) {
/* 531 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addFieldGroup(HeaderField,int)", new Object[] { trigger, 
/* 532 */             Integer.valueOf(value) });
/*     */     }
/* 534 */     FieldGroup traceRet1 = new OptionalFieldGroup(this, OptionRule.createOptionRule((MQLongField)trigger, value));
/*     */ 
/*     */     
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addFieldGroup(HeaderField,int)", traceRet1);
/*     */     }
/*     */     
/* 541 */     return traceRet1;
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
/*     */   public FieldGroup addFieldGroup(HeaderField trigger, String value) {
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.entry(this, "com.ibm.mq.headers.internal.FieldGroup", "addFieldGroup(HeaderField,String)", new Object[] { trigger, value });
/*     */     }
/*     */     
/* 560 */     FieldGroup traceRet1 = new OptionalFieldGroup(this, OptionRule.createOptionRule((MQCharField)trigger, value));
/*     */ 
/*     */     
/* 563 */     if (Trace.isOn) {
/* 564 */       Trace.exit(this, "com.ibm.mq.headers.internal.FieldGroup", "addFieldGroup(HeaderField,String)", traceRet1);
/*     */     }
/*     */     
/* 567 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected abstract HeaderField addField(HeaderField paramHeaderField);
/*     */   
/*     */   protected abstract int nextFieldOffset();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\FieldGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */