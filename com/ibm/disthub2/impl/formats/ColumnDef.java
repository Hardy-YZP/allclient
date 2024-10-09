/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ public final class ColumnDef
/*     */   implements SchemaCodes, Serializable, ExceptionConstants, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  72 */   private static final DebugObject debug = new DebugObject("ColumnDef");
/*     */   
/*     */   private byte typeCode;
/*  75 */   private byte access = -110;
/*     */ 
/*     */   
/*     */   private Schema schema;
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   TupleDef parent;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  89 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAccess(String access) {
/*  97 */     switch (access.charAt(0)) {
/*     */       case 'm':
/*  99 */         this.access = -110;
/*     */         return;
/*     */       case 'h':
/* 102 */         this.access = -100;
/*     */         return;
/*     */       case 'a':
/* 105 */         this.access = -101;
/*     */         return;
/*     */       case 'n':
/* 108 */         this.access = Byte.MIN_VALUE;
/*     */         return;
/*     */     } 
/* 111 */     throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(277537878, new Object[] { Character.valueOf(access.charAt(0)) }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNames(Names names, TupleDef parent) {
/* 122 */     setName(names.name);
/* 123 */     this.parent = parent;
/* 124 */     if (this.schema != null && names.subNames != null) {
/* 125 */       for (int i = 0; i < this.schema.getChoiceCount(); i++) {
/* 126 */         this.schema.getTupleDef(i).setNames(names.subNames[i], this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 135 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 144 */     String result = this.name;
/* 145 */     if (this.parent != null) {
/* 146 */       result = this.parent.getFullName() + "." + this.name;
/*     */     }
/* 148 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getShortName() {
/* 158 */     String result = null;
/* 159 */     if (this.name.length() > 0) {
/* 160 */       result = this.name;
/* 161 */     } else if (this.parent != null) {
/* 162 */       result = this.parent.getShortName();
/*     */     } 
/*     */ 
/*     */     
/* 166 */     return result;
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
/*     */   public String getCppFullName() {
/* 179 */     String context = this.parent.getCppFullName(true);
/* 180 */     if (context == null || context.length() == 0)
/* 181 */       return this.name; 
/* 182 */     if (this.name == null || this.name.length() == 0) {
/* 183 */       return context;
/*     */     }
/* 185 */     return context + "." + this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getTypeCode() {
/* 193 */     return this.typeCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getAccess() {
/* 201 */     return this.access;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Schema getSchema() {
/* 209 */     return this.schema;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnDef(byte typeCode) {
/* 217 */     this.typeCode = typeCode;
/* 218 */     this.name = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnDef(Schema schema) {
/* 227 */     this.typeCode = 0;
/* 228 */     this.schema = schema;
/* 229 */     this.name = "";
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
/*     */   ColumnDef(byte[] frame, int offset, int length) {
/* 244 */     this(frame, new int[] { offset, offset + length });
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
/*     */   ColumnDef(byte[] frame, int[] cursor) {
/* 257 */     this.name = "";
/* 258 */     this.typeCode = Schema.getByte(frame, cursor);
/* 259 */     if (this.typeCode <= -100) {
/*     */       
/* 261 */       this.access = this.typeCode;
/* 262 */       this.typeCode = Schema.getByte(frame, cursor);
/*     */     } else {
/*     */       
/* 265 */       this.access = -110;
/* 266 */     }  if (this.typeCode == 0) {
/* 267 */       this.schema = new Schema(frame, cursor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int encodedSize() {
/*     */     int size;
/* 277 */     if (this.typeCode != 0) {
/* 278 */       size = 1;
/*     */     } else {
/* 280 */       size = 1 + this.schema.encodedSize();
/* 281 */     }  if (this.access != -110) {
/* 282 */       size++;
/*     */     }
/* 284 */     return size;
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
/*     */   void encode(byte[] frame, int[] cursor) {
/* 297 */     if (this.access != -110)
/* 298 */       Schema.setByte(frame, cursor, this.access); 
/* 299 */     Schema.setByte(frame, cursor, this.typeCode);
/* 300 */     if (this.typeCode == 0) {
/* 301 */       this.schema.encode(frame, cursor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 309 */     String base = (this.schema != null) ? this.schema.toString() : typeNames[this.typeCode - -4];
/* 310 */     return ((this.name == null || this.name.length() == 0) ? base : (this.name + ": " + base)) + " ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\ColumnDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */