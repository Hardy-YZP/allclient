/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.validator.ChainedValidator;
/*     */ import com.ibm.mq.headers.internal.validator.FieldValidator;
/*     */ import com.ibm.mq.headers.internal.validator.NullValidator;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeaderType
/*     */   extends FieldGroup
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HeaderType.java";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.headers.internal.HeaderType", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HeaderType.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class DummyHeader
/*     */     extends Header
/*     */   {
/*     */     DummyHeader(HeaderType type) {
/*  65 */       super(type);
/*     */     }
/*     */   }
/*     */   
/*  69 */   static final Header dummyHeader = new DummyHeader(new HeaderType("DUMMY"));
/*     */   
/*     */   protected final String name;
/*  72 */   protected final List<HeaderField> fields = new ArrayList<>();
/*     */   
/*  74 */   protected OptionRule optionRule = null;
/*  75 */   protected FieldValidator validator = (FieldValidator)NullValidator.INSTANCE;
/*     */   
/*     */   protected int fixedSize;
/*     */   
/*     */   protected int fixedSizeFieldCount;
/*     */   
/*     */   protected int nextFieldOffset;
/*     */   
/*     */   protected boolean isVariableSize;
/*     */   protected boolean applied;
/*     */   
/*     */   public HeaderType(String name) {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String)", new Object[] { name });
/*     */     }
/*     */     
/*  91 */     this.name = name;
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String)");
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
/*     */   public HeaderType(String name, HeaderType superType) {
/* 107 */     this(name);
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String,HeaderType)", new Object[] { name, superType });
/*     */     }
/*     */     
/* 112 */     this.fields.addAll(superType.fields);
/* 113 */     this.fixedSize = superType.fixedSize;
/* 114 */     this.fixedSizeFieldCount = superType.fixedSizeFieldCount;
/* 115 */     this.nextFieldOffset = superType.nextFieldOffset;
/* 116 */     this.isVariableSize = superType.isVariableSize;
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String,HeaderType)");
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
/*     */   public HeaderType(String name, HeaderType superType, HeaderField versionField, int newVersionValue) {
/* 136 */     this(name, superType);
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String,HeaderType,HeaderField,int)", new Object[] { name, superType, versionField, 
/*     */             
/* 140 */             Integer.valueOf(newVersionValue) });
/*     */     }
/* 142 */     int index = this.fields.indexOf(versionField);
/*     */     
/* 144 */     if (index < 0) {
/* 145 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0007", new Object[] { versionField.name, superType.name }));
/*     */ 
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String,HeaderType,HeaderField,int)", traceRet1);
/*     */       }
/*     */       
/* 152 */       throw traceRet1;
/*     */     } 
/*     */     
/* 155 */     MQLongField newField = new MQLongField(versionField.getOffset(dummyHeader), versionField.name, newVersionValue, false);
/*     */ 
/*     */     
/* 158 */     newField.setIndex(index);
/* 159 */     this.fields.set(index, newField);
/*     */     
/* 161 */     this.optionRule = OptionRule.createOptionRule(newField, newVersionValue);
/* 162 */     this.isVariableSize = true;
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "<init>(String,HeaderType,HeaderField,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isVariableSize() {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderType", "isVariableSize()", "getter", 
/* 176 */           Boolean.valueOf(this.isVariableSize));
/*     */     }
/* 178 */     return this.isVariableSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getFixedSize() {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderType", "getFixedSize()", "getter", 
/* 188 */           Integer.valueOf(this.fixedSize));
/*     */     }
/* 190 */     return this.fixedSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getFixedSizeFieldCount() {
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderType", "getFixedSizeFieldCount()", "getter", 
/* 200 */           Integer.valueOf(this.fixedSizeFieldCount));
/*     */     }
/* 202 */     return this.fixedSizeFieldCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   HeaderType apply(Header h) {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "apply(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 215 */     this.applied = true;
/*     */     
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "apply(Header)", this);
/*     */     }
/* 220 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int nextFieldOffset() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "nextFieldOffset()");
/*     */     }
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "nextFieldOffset()", 
/* 234 */           Integer.valueOf(this.nextFieldOffset));
/*     */     }
/* 236 */     return this.nextFieldOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HeaderField addField(HeaderField field) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "addField(HeaderField)", new Object[] { field });
/*     */     }
/*     */     
/* 249 */     if (this.applied) {
/*     */       
/* 251 */       IllegalStateException traceRet1 = new IllegalStateException(HeaderMessages.getMessage("MQHDR0008"));
/*     */       
/* 253 */       if (Trace.isOn) {
/* 254 */         Trace.throwing(this, "com.ibm.mq.headers.internal.HeaderType", "addField(HeaderField)", traceRet1);
/*     */       }
/*     */       
/* 257 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 261 */     if (this.optionRule != null) {
/* 262 */       field.setOptionRule(this.optionRule);
/*     */     }
/*     */     
/* 265 */     field.setIndex(this.fields.size());
/* 266 */     this.fields.add(field);
/*     */     
/* 268 */     int size = field.size(dummyHeader);
/*     */     
/* 270 */     if (this.nextFieldOffset >= 0) {
/* 271 */       if (size > 0) {
/* 272 */         this.nextFieldOffset += size;
/*     */         
/* 274 */         if (!this.isVariableSize) {
/* 275 */           this.fixedSize += size;
/* 276 */           this.fixedSizeFieldCount++;
/*     */         } 
/*     */       } else {
/* 279 */         this.nextFieldOffset = -this.nextFieldOffset;
/*     */         
/* 281 */         this.isVariableSize = true;
/*     */       } 
/*     */     } else {
/* 284 */       this.isVariableSize = true;
/*     */     } 
/*     */     
/* 287 */     FieldValidator fieldValidator = field.getValidator();
/*     */     
/* 289 */     if (fieldValidator != null && fieldValidator != NullValidator.INSTANCE) {
/* 290 */       if (this.validator == null || this.validator == NullValidator.INSTANCE) {
/* 291 */         this.validator = fieldValidator;
/*     */       } else {
/* 293 */         this.validator = (FieldValidator)new ChainedValidator(fieldValidator, this.validator);
/*     */       } 
/*     */     }
/*     */     
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "addField(HeaderField)", field);
/*     */     }
/* 300 */     return field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFields() {
/* 308 */     List<HeaderField> list = new AbstractList<HeaderField>()
/*     */       {
/*     */         
/*     */         public HeaderField get(int index)
/*     */         {
/* 313 */           return HeaderType.this.fields.get(index);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int size() {
/* 319 */           return HeaderType.this.fields.size();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Iterator<HeaderField> iterator() {
/* 325 */           return HeaderType.this.fields.iterator();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public ListIterator<HeaderField> listIterator() {
/* 331 */           return HeaderType.this.fields.listIterator();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public ListIterator<HeaderField> listIterator(int index) {
/* 337 */           return HeaderType.this.fields.listIterator(index);
/*     */         }
/*     */       };
/* 340 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFieldCount() {
/* 348 */     int traceRet1 = this.fields.size();
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderType", "getFieldCount()", "getter", 
/* 351 */           Integer.valueOf(traceRet1));
/*     */     }
/* 353 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderField getField(int index) {
/* 361 */     if (Trace.isOn)
/* 362 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "getField(int)", new Object[] {
/* 363 */             Integer.valueOf(index)
/*     */           }); 
/* 365 */     HeaderField traceRet1 = this.fields.get(index);
/*     */     
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "getField(int)", traceRet1);
/*     */     }
/* 370 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HeaderField getField(String name) throws NoSuchElementException {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "getField(String)", new Object[] { name });
/*     */     }
/*     */     
/* 385 */     HeaderField match = null;
/*     */     
/* 387 */     for (HeaderField field : this.fields) {
/* 388 */       if (field.name().equals(name)) {
/* 389 */         match = field;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 394 */     if (match == null) {
/* 395 */       NoSuchElementException traceRet1 = new NoSuchElementException(HeaderMessages.getMessage("MQHDR0009", new Object[] { this.name, name }));
/*     */ 
/*     */       
/* 398 */       if (Trace.isOn) {
/* 399 */         Trace.throwing(this, "com.ibm.mq.headers.internal.HeaderType", "getField(String)", traceRet1);
/*     */       }
/*     */       
/* 402 */       throw traceRet1;
/*     */     } 
/*     */     
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "getField(String)", match);
/*     */     }
/* 408 */     return match;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldGroup addFieldGroup(HeaderField triggerP, int value) {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "addFieldGroup(HeaderField,int)", new Object[] { triggerP, 
/* 418 */             Integer.valueOf(value) });
/*     */     }
/* 420 */     HeaderField trigger = triggerP;
/*     */ 
/*     */ 
/*     */     
/* 424 */     if (!this.fields.contains(trigger)) {
/* 425 */       trigger = getField(trigger.name);
/*     */     }
/*     */     
/* 428 */     this.isVariableSize = true;
/*     */     
/* 430 */     FieldGroup traceRet1 = super.addFieldGroup(trigger, value);
/*     */     
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "addFieldGroup(HeaderField,int)", traceRet1);
/*     */     }
/*     */     
/* 436 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldGroup addFieldGroup(HeaderField triggerP, String value) {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "addFieldGroup(HeaderField,String)", new Object[] { triggerP, value });
/*     */     }
/*     */     
/* 448 */     HeaderField trigger = triggerP;
/*     */ 
/*     */ 
/*     */     
/* 452 */     if (!this.fields.contains(trigger)) {
/* 453 */       trigger = getField(trigger.name);
/*     */     }
/*     */     
/* 456 */     this.isVariableSize = true;
/*     */     
/* 458 */     FieldGroup traceRet1 = super.addFieldGroup(trigger, value);
/*     */     
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "addFieldGroup(HeaderField,String)", traceRet1);
/*     */     }
/*     */     
/* 464 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 472 */     if (Trace.isOn) {
/* 473 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "size(Header)", new Object[] { h });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (isVariableSize()) {
/* 479 */       int size = 0;
/* 480 */       int index = getFieldCount();
/*     */       
/* 482 */       while (index-- > this.fixedSizeFieldCount) {
/* 483 */         HeaderField field = getField(index);
/*     */         
/* 485 */         if (field.isPresent(h)) {
/* 486 */           size += field.size(h);
/*     */         }
/*     */       } 
/*     */       
/* 490 */       size += this.fixedSize;
/*     */       
/* 492 */       if (Trace.isOn) {
/* 493 */         Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "size(Header)", 
/* 494 */             Integer.valueOf(size), 1);
/*     */       }
/* 496 */       return size;
/*     */     } 
/* 498 */     int traceRet1 = getFixedSize();
/* 499 */     if (Trace.isOn) {
/* 500 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "size(Header)", 
/* 501 */           Integer.valueOf(traceRet1), 2);
/*     */     }
/* 503 */     return traceRet1;
/*     */   }
/*     */   
/*     */   final void validate(Header h) throws MQDataException, IOException {
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "validate(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 511 */     this.validator.validate(h);
/*     */     
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "validate(Header)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderType", "toString()");
/*     */     }
/* 527 */     StringBuffer sb = new StringBuffer(super.toString());
/*     */     
/* 529 */     for (HeaderField field : this.fields) {
/* 530 */       sb.append("\n\t" + field);
/*     */     }
/*     */     
/* 533 */     String traceRet1 = new String(sb);
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderType", "toString()", traceRet1);
/*     */     }
/* 537 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\HeaderType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */