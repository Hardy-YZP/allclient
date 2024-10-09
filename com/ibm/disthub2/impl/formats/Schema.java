/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.client.Schema;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.util.CryptoHash;
/*     */ import com.ibm.disthub2.impl.util.UTF;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Schema
/*     */   implements Schema, Serializable, ExceptionConstants, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  82 */   private static final DebugObject debug = new DebugObject("Schema");
/*     */   
/*     */   private static final int INIT_CHOICES = 8;
/*     */   
/*     */   private int nChoices;
/*     */   
/*     */   private TupleDef[] choices;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private transient byte[] encodedForm;
/*     */   private transient FlatSchema flat;
/*     */   private byte version;
/*     */   private transient long id;
/*     */   
/*     */   public Schema() {
/*  98 */     this.choices = new TupleDef[8];
/*  99 */     this.name = "";
/* 100 */     this.version = 1;
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
/*     */   public Schema(byte[] frame, int offset, int length) {
/* 113 */     this(frame, new int[] { offset, offset + length });
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
/*     */   protected Schema(byte[] frame, String name) {
/* 126 */     this(frame, new int[] { 0, frame.length });
/* 127 */     setName(name);
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
/*     */   public Schema(byte[] frame, Names names) {
/* 140 */     this(frame, new int[] { 0, frame.length });
/* 141 */     setNames(names);
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
/*     */   Schema(byte[] frame, int[] cursor) {
/* 153 */     this.version = getByte(frame, cursor);
/* 154 */     this.nChoices = getCount(frame, cursor);
/* 155 */     this.choices = new TupleDef[this.nChoices];
/* 156 */     for (int i = 0; i < this.nChoices; i++) {
/* 157 */       this.choices[i] = new TupleDef(frame, cursor);
/*     */     }
/* 159 */     this.name = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 167 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 176 */     this.name = (name == null) ? "" : name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNames(Names names) {
/* 184 */     setName(names.name);
/* 185 */     for (int i = 0; i < this.nChoices; i++) {
/* 186 */       this.choices[i].setNames(names.subNames[i], null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 195 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChoiceCount() {
/* 202 */     return this.nChoices;
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
/*     */   public TupleDef getTupleDef(int index) {
/* 215 */     TupleDef result = null;
/* 216 */     if (index >= 0 && index < this.nChoices) {
/* 217 */       return this.choices[index];
/*     */     }
/*     */     
/* 220 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(TupleDef choice) {
/* 227 */     if (this.nChoices == this.choices.length) {
/* 228 */       TupleDef[] newChoices = new TupleDef[this.nChoices * 2];
/* 229 */       System.arraycopy(this.choices, 0, newChoices, 0, this.nChoices);
/* 230 */       this.choices = newChoices;
/*     */     } 
/* 232 */     this.choices[this.nChoices++] = choice;
/* 233 */     this.encodedForm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlatSchema getFlatSchema() {
/* 243 */     if (this.flat == null) {
/* 244 */       this.flat = new FlatSchema(this);
/*     */     }
/* 246 */     return this.flat;
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
/*     */   static int getCount(byte[] frame, int[] cursor) {
/* 259 */     int ans = getByte(frame, cursor);
/*     */     
/* 261 */     if (ans < -1) {
/* 262 */       if ((ans & 0xC0) == 128) {
/* 263 */         ans = (ans & 0x3F) << 8 | getByte(frame, cursor) & 0xFF;
/*     */       }
/* 265 */       else if ((ans & 0xE0) == 192) {
/*     */         
/* 267 */         ans = (ans & 0x1F) << 16 | (getByte(frame, cursor) & 0xFF) << 8 | getByte(frame, cursor) & 0xFF;
/*     */       }
/* 269 */       else if ((ans & 0xF0) == 224) {
/*     */         
/* 271 */         ans = (ans & 0xF) << 24 | (getByte(frame, cursor) & 0xFF) << 16 | (getByte(frame, cursor) & 0xFF) << 8 | getByte(frame, cursor) & 0xFF;
/*     */       } else {
/*     */         
/* 274 */         throw new RuntimeException(ExceptionBuilder.buildReasonString(1700454902, null));
/*     */       } 
/*     */     }
/*     */     
/* 278 */     return ans;
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
/*     */   static void setCount(byte[] frame, int[] cursor, int val) {
/* 293 */     if (val < 0 || val > 268435455) {
/* 294 */       throw new RuntimeException(ExceptionBuilder.buildReasonString(-135628274, new Object[] { Integer.valueOf(val) }));
/*     */     }
/* 296 */     if (val < 128) {
/* 297 */       setByte(frame, cursor, (byte)val);
/*     */     }
/* 299 */     else if (val < 16384) {
/* 300 */       val |= 0x8000;
/* 301 */       setByte(frame, cursor, (byte)(val >> 8));
/* 302 */       setByte(frame, cursor, (byte)val);
/*     */     }
/* 304 */     else if (val < 2097152) {
/* 305 */       val |= 0xC00000;
/* 306 */       setByte(frame, cursor, (byte)(val >> 16));
/* 307 */       setByte(frame, cursor, (byte)(val >> 8));
/* 308 */       setByte(frame, cursor, (byte)val);
/*     */     } else {
/*     */       
/* 311 */       val |= 0xE0000000;
/* 312 */       setByte(frame, cursor, (byte)(val >> 24));
/* 313 */       setByte(frame, cursor, (byte)(val >> 16));
/* 314 */       setByte(frame, cursor, (byte)(val >> 8));
/* 315 */       setByte(frame, cursor, (byte)val);
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
/*     */   static byte getByte(byte[] frame, int[] cursor) {
/*     */     byte result;
/* 331 */     if (cursor[0] < cursor[1]) {
/* 332 */       cursor[0] = cursor[0] + 1; result = frame[cursor[0]];
/*     */     } else {
/*     */       
/* 335 */       throw new RuntimeException(ExceptionBuilder.buildReasonString(-551098147, null));
/*     */     } 
/*     */     
/* 338 */     return result;
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
/*     */   static void setByte(byte[] frame, int[] cursor, byte val) {
/* 352 */     if (cursor[0] < cursor[1]) {
/* 353 */       cursor[0] = cursor[0] + 1; frame[cursor[0]] = val;
/*     */     } else {
/*     */       
/* 356 */       throw new RuntimeException(ExceptionBuilder.buildReasonString(1334042977, null));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int encodedSize() {
/* 365 */     int size = 2;
/* 366 */     for (int i = 0; i < this.nChoices; i++) {
/* 367 */       size += this.choices[i].encodedSize();
/*     */     }
/*     */     
/* 370 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized byte[] toEncodedForm() {
/* 377 */     if (this.encodedForm == null) {
/*     */       
/* 379 */       this.encodedForm = new byte[encodedSize()];
/* 380 */       encode(this.encodedForm, new int[] { 0, this.encodedForm.length });
/*     */     } 
/*     */     
/* 383 */     return this.encodedForm;
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
/*     */   void encode(byte[] frame, int[] cursor) {
/* 395 */     setByte(frame, cursor, this.version);
/* 396 */     setCount(frame, cursor, this.nChoices);
/* 397 */     for (int i = 0; i < this.nChoices; i++) {
/* 398 */       this.choices[i].encode(frame, cursor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 406 */     if (this.encodedForm == null) {
/* 407 */       toEncodedForm();
/*     */     }
/* 409 */     return this.encodedForm.hashCode() + this.name.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 415 */     if (o instanceof Schema) {
/* 416 */       Schema s = (Schema)o;
/* 417 */       if (!this.name.equals(s.name)) {
/* 418 */         return false;
/*     */       }
/* 420 */       byte[] thisBytes = toEncodedForm();
/* 421 */       byte[] thatBytes = s.toEncodedForm();
/* 422 */       if (thisBytes.length != thatBytes.length) {
/* 423 */         return false;
/*     */       }
/* 425 */       for (int i = 0; i < thisBytes.length; i++) {
/* 426 */         if (thisBytes[i] != thatBytes[i]) {
/* 427 */           return false;
/*     */         }
/*     */       } 
/* 430 */       return true;
/*     */     } 
/*     */     
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized long getId() {
/* 442 */     if (this.id != 0L) {
/* 443 */       return this.id;
/*     */     }
/* 445 */     if (this.encodedForm == null) {
/* 446 */       toEncodedForm();
/*     */     }
/* 448 */     int nameLen = 0;
/* 449 */     char[] namebuf = null;
/* 450 */     if (this.name.length() > 0) {
/* 451 */       namebuf = this.name.toCharArray();
/* 452 */       nameLen = UTF.lengthUTF(namebuf, 0, namebuf.length);
/*     */     } 
/* 454 */     byte[] array = new byte[nameLen + this.encodedForm.length];
/* 455 */     if (namebuf != null) {
/* 456 */       UTF.charsToUTF(namebuf, 0, array, 0, namebuf.length);
/*     */     }
/* 458 */     System.arraycopy(this.encodedForm, 0, array, nameLen, this.encodedForm.length);
/*     */     
/* 460 */     this.id = CryptoHash.hash(array);
/*     */     
/* 462 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     StringBuffer ans;
/* 469 */     if (this.name != null && this.name.length() > 0) {
/* 470 */       ans = (new StringBuffer(this.name)).append(" { ");
/*     */     } else {
/*     */       
/* 473 */       ans = new StringBuffer("{");
/*     */     } 
/* 475 */     for (int i = 0; i < this.nChoices; i++) {
/* 476 */       ans.append(this.choices[i].toString());
/*     */     }
/* 478 */     return ans.append("}").toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\Schema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */