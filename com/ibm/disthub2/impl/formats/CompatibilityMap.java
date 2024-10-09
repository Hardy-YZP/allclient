/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.client.SchemaViolationException;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CompatibilityMap
/*     */   implements ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public int[] indices;
/*     */   public int varBias;
/*     */   public int[][] setCases;
/*     */   public int[][] getCases;
/*     */   public CompatibilityMap[] tableMaps;
/*     */   private byte[] encodedForm;
/*     */   
/*     */   public CompatibilityMap(byte[] frame, int offset, int length) {
/*  86 */     this(frame, new int[] { offset, offset + length });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CompatibilityMap(byte[] frame, int[] cursor) {
/*  94 */     this.indices = new int[Schema.getCount(frame, cursor)]; int i;
/*  95 */     for (i = 0; i < this.indices.length; i++)
/*  96 */       this.indices[i] = Schema.getCount(frame, cursor); 
/*  97 */     this.varBias = Schema.getCount(frame, cursor);
/*  98 */     this.setCases = new int[Schema.getCount(frame, cursor)][];
/*  99 */     for (i = 0; i < this.setCases.length; i++) {
/* 100 */       int[] cases = new int[Schema.getCount(frame, cursor)];
/* 101 */       for (int k = 0; k < cases.length; k++)
/* 102 */         cases[k] = Schema.getCount(frame, cursor); 
/* 103 */       this.setCases[i] = cases;
/*     */     } 
/* 105 */     this.getCases = new int[Schema.getCount(frame, cursor)][];
/* 106 */     for (i = 0; i < this.getCases.length; i++) {
/* 107 */       int[] cases = new int[Schema.getCount(frame, cursor)];
/* 108 */       for (int k = 0; k < cases.length; k++)
/* 109 */         cases[k] = Schema.getCount(frame, cursor); 
/* 110 */       this.getCases[i] = cases;
/*     */     } 
/* 112 */     this.tableMaps = new CompatibilityMap[this.varBias];
/* 113 */     int numTables = Schema.getCount(frame, cursor);
/* 114 */     for (int j = 0; j < numTables; j++) {
/* 115 */       int tableIndex = Schema.getCount(frame, cursor);
/* 116 */       this.tableMaps[tableIndex] = new CompatibilityMap(frame, cursor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] toEncodedForm() {
/* 127 */     if (this.encodedForm == null) {
/* 128 */       this.encodedForm = new byte[encodedSize()];
/* 129 */       encode(this.encodedForm, new int[] { 0, this.encodedForm.length });
/*     */     } 
/* 131 */     return this.encodedForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void encode(byte[] frame, int[] cursor) {
/* 138 */     Schema.setCount(frame, cursor, this.indices.length); int i;
/* 139 */     for (i = 0; i < this.indices.length; i++)
/* 140 */       Schema.setCount(frame, cursor, this.indices[i]); 
/* 141 */     Schema.setCount(frame, cursor, this.varBias);
/* 142 */     Schema.setCount(frame, cursor, this.setCases.length);
/* 143 */     for (i = 0; i < this.setCases.length; i++) {
/* 144 */       int[] cases = this.setCases[i];
/* 145 */       Schema.setCount(frame, cursor, cases.length);
/* 146 */       for (int k = 0; k < cases.length; k++)
/* 147 */         Schema.setCount(frame, cursor, cases[k]); 
/*     */     } 
/* 149 */     Schema.setCount(frame, cursor, this.getCases.length);
/* 150 */     for (i = 0; i < this.getCases.length; i++) {
/* 151 */       int[] cases = this.getCases[i];
/* 152 */       Schema.setCount(frame, cursor, cases.length);
/* 153 */       for (int k = 0; k < cases.length; k++)
/* 154 */         Schema.setCount(frame, cursor, cases[k]); 
/*     */     } 
/* 156 */     int numTables = 0; int j;
/* 157 */     for (j = 0; j < this.tableMaps.length; j++) {
/* 158 */       if (this.tableMaps[j] != null)
/* 159 */         numTables++; 
/* 160 */     }  Schema.setCount(frame, cursor, numTables);
/* 161 */     for (j = 0; j < this.tableMaps.length; j++) {
/* 162 */       if (this.tableMaps[j] != null) {
/* 163 */         Schema.setCount(frame, cursor, j);
/* 164 */         this.tableMaps[j].encode(frame, cursor);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int countBytes(int toEncode) {
/* 173 */     if (toEncode < 128)
/* 174 */       return 1; 
/* 175 */     if (toEncode < 16384)
/* 176 */       return 2; 
/* 177 */     if (toEncode < 2097152) {
/* 178 */       return 3;
/*     */     }
/* 180 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int encodedSize() {
/* 187 */     int ans = countBytes(this.indices.length); int i;
/* 188 */     for (i = 0; i < this.indices.length; i++)
/* 189 */       ans += countBytes(this.indices[i]); 
/* 190 */     ans += countBytes(this.varBias);
/* 191 */     ans += countBytes(this.setCases.length);
/* 192 */     for (i = 0; i < this.setCases.length; i++) {
/* 193 */       int[] cases = this.setCases[i];
/* 194 */       ans += countBytes(cases.length);
/* 195 */       for (int k = 0; k < cases.length; k++)
/* 196 */         ans += countBytes(cases[k]); 
/*     */     } 
/* 198 */     ans += countBytes(this.getCases.length);
/* 199 */     for (i = 0; i < this.getCases.length; i++) {
/* 200 */       int[] cases = this.getCases[i];
/* 201 */       ans += countBytes(cases.length);
/* 202 */       for (int k = 0; k < cases.length; k++)
/* 203 */         ans += countBytes(cases[k]); 
/*     */     } 
/* 205 */     int numTables = 0; int j;
/* 206 */     for (j = 0; j < this.tableMaps.length; j++) {
/* 207 */       if (this.tableMaps[j] != null)
/* 208 */         numTables++; 
/* 209 */     }  ans += countBytes(numTables);
/* 210 */     for (j = 0; j < this.tableMaps.length; j++) {
/* 211 */       if (this.tableMaps[j] != null) {
/* 212 */         ans += countBytes(j);
/* 213 */         ans += this.tableMaps[j].encodedSize();
/*     */       } 
/* 215 */     }  return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int findVariantIndex(FlatSchema schema, Schema variant) {
/* 223 */     for (int i = 0; i < schema.variants.length; i++) {
/* 224 */       if ((schema.variants[i]).schema == variant)
/* 225 */         return i; 
/* 226 */     }  throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int findFieldIndex(FlatSchema schema, ColumnDef field) {
/* 234 */     for (int i = 0; i < schema.fields.length; i++) {
/* 235 */       if ((schema.fields[i]).field == field)
/* 236 */         return i; 
/* 237 */     }  throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static FlatSchema findTableSchema(FlatSchema schema, ColumnDef table) {
/* 245 */     for (int i = 0; i < schema.fields.length; i++) {
/* 246 */       if ((schema.fields[i]).field == table)
/* 247 */         return (schema.fields[i]).contents; 
/* 248 */     }  throw new IllegalArgumentException();
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
/*     */   public CompatibilityMap(FlatSchema access, FlatSchema encoding, ColumnDef tableCheck) {
/* 271 */     this.varBias = encoding.fields.length;
/* 272 */     this.indices = new int[access.fields.length + access.variants.length];
/* 273 */     this.setCases = new int[encoding.variants.length][];
/* 274 */     this.getCases = new int[encoding.variants.length][];
/* 275 */     this.tableMaps = new CompatibilityMap[access.fields.length];
/*     */ 
/*     */     
/* 278 */     for (int i = 0; i < this.indices.length; i++) {
/* 279 */       this.indices[i] = -1;
/*     */     }
/*     */     
/* 282 */     recordCompatibilityInfo((access.variants[0]).schema, (encoding.variants[0]).schema, access, encoding, tableCheck);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isEmpty(TupleDef tup) {
/* 290 */     return (tup.getColumnCount() == 0 && tup.getNextDef() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void violation(String from, String to) {
/* 297 */     throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-468191348, new Object[] { from, to }));
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
/*     */   private void recordCompatibilityInfo(Schema access, Schema encoding, FlatSchema accessFlat, FlatSchema encodingFlat, ColumnDef tableCheck) {
/* 311 */     int accessIndex = findVariantIndex(accessFlat, access);
/* 312 */     int encodingIndex = findVariantIndex(encodingFlat, encoding);
/* 313 */     this.indices[accessIndex + accessFlat.fields.length] = encodingIndex + this.varBias;
/*     */     
/* 315 */     int accessCount = access.getChoiceCount();
/* 316 */     int encodingCount = encoding.getChoiceCount();
/* 317 */     if (accessCount != encodingCount && (
/* 318 */       !isEmpty(access.getTupleDef(0)) || !isEmpty(encoding.getTupleDef(0)))) {
/* 319 */       violation(access.getName(), encoding.getName());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 324 */     int[] ourSets = this.setCases[encodingIndex] = new int[accessCount];
/* 325 */     int[] ourGets = this.getCases[encodingIndex] = new int[encodingCount];
/*     */     
/* 327 */     for (int i = 0; i < ((accessCount > encodingCount) ? encodingCount : accessCount); i++) {
/* 328 */       ourSets[i] = i; ourGets[i] = i;
/* 329 */       TupleDef accessTuple = access.getTupleDef(i);
/* 330 */       ColumnDef accessTable = accessTuple.getNextDef();
/* 331 */       TupleDef encodingTuple = encoding.getTupleDef(i);
/* 332 */       ColumnDef encodingTable = encodingTuple.getNextDef();
/*     */       
/* 334 */       if (accessTable == null) {
/* 335 */         if (encodingTable == null) {
/* 336 */           recordCompatibilityInfo(accessTuple, encodingTuple, accessFlat, encodingFlat, tableCheck);
/*     */         } else {
/*     */           
/* 339 */           violation(accessTuple.getFullName() + "(not table)", encodingTuple
/* 340 */               .getFullName() + "(table)");
/*     */         } 
/* 342 */       } else if (encodingTable != null) {
/*     */ 
/*     */         
/* 345 */         if (accessTable != tableCheck) {
/*     */           
/* 347 */           recordCompatibilityInfo(accessTable, encodingTable, accessFlat, encodingFlat);
/*     */         } else {
/*     */           
/* 350 */           recordCompatibilityInfo(accessTuple, encodingTuple, accessFlat, encodingFlat, tableCheck);
/*     */         } 
/*     */       } else {
/* 353 */         violation(accessTuple.getFullName() + "(table)", encodingTuple
/* 354 */             .getFullName() + "(not table)");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkExtraColumns(TupleDef tuple, int startCol, int count) {
/* 363 */     for (int i = startCol; i < count; i++) {
/* 364 */       ColumnDef col = tuple.getColumnDef(i);
/* 365 */       if (col.getTypeCode() != 0 || 
/* 366 */         !isEmpty(col.getSchema().getTupleDef(0))) {
/* 367 */         throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-905440897, new Object[] { col.getFullName() }));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isDeletingVariant(byte typeCode, Schema toCheck) {
/* 378 */     if (toCheck.getChoiceCount() == 2) {
/* 379 */       TupleDef tup = toCheck.getTupleDef(0);
/* 380 */       return (tup.getColumnCount() == 1 && tup
/* 381 */         .getColumnDef(0).getTypeCode() == typeCode && 
/* 382 */         isEmpty(toCheck.getTupleDef(1)));
/*     */     } 
/*     */     
/* 385 */     return false;
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
/*     */   private void recordCompatibilityInfo(TupleDef access, TupleDef encoding, FlatSchema accessFlat, FlatSchema encodingFlat, ColumnDef tableCheck) {
/* 398 */     int accessCount = access.getColumnCount();
/* 399 */     int encodeCount = encoding.getColumnCount();
/* 400 */     int count = (accessCount < encodeCount) ? accessCount : encodeCount;
/*     */ 
/*     */ 
/*     */     
/* 404 */     if (accessCount > count) {
/* 405 */       checkExtraColumns(access, count, accessCount);
/* 406 */     } else if (encodeCount > count) {
/* 407 */       checkExtraColumns(encoding, count, encodeCount);
/*     */     } 
/*     */ 
/*     */     
/* 411 */     for (int i = 0; i < count; i++) {
/* 412 */       ColumnDef accessDef = access.getColumnDef(i);
/* 413 */       ColumnDef encodingDef = encoding.getColumnDef(i);
/* 414 */       byte accessCode = accessDef.getTypeCode();
/* 415 */       byte encodingCode = encodingDef.getTypeCode();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 420 */       if (accessCode == 0)
/* 421 */       { if (encodingCode == 0)
/*     */         
/* 423 */         { recordCompatibilityInfo(accessDef.getSchema(), encodingDef.getSchema(), accessFlat, encodingFlat, tableCheck);
/*     */            }
/*     */         
/*     */         else
/*     */         
/*     */         { 
/* 429 */           if (!isDeletingVariant(encodingCode, accessDef.getSchema())) {
/* 430 */             violation(accessDef.getFullName(), encodingDef.getFullName());
/*     */           } else {
/*     */             
/* 433 */             accessDef = accessDef.getSchema().getTupleDef(0).getColumnDef(0);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 450 */           this.indices[findFieldIndex(accessFlat, accessDef)] = 
/* 451 */             findFieldIndex(encodingFlat, encodingDef); }  } else { if (encodingCode == 0) { if (!isDeletingVariant(accessCode, encodingDef.getSchema())) { violation(accessDef.getFullName(), encodingDef.getFullName()); } else { encodingDef = encodingDef.getSchema().getTupleDef(0).getColumnDef(0); }  } else if (accessCode != encodingCode) { violation(accessDef.getFullName(), encodingDef.getFullName()); }  this.indices[findFieldIndex(accessFlat, accessDef)] = findFieldIndex(encodingFlat, encodingDef); }
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 461 */     ColumnDef accessNext = access.getNextDef();
/* 462 */     if (accessNext != null) {
/* 463 */       this.indices[findFieldIndex(accessFlat, accessNext)] = 
/* 464 */         findFieldIndex(encodingFlat, encoding.getNextDef());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void recordCompatibilityInfo(ColumnDef accessTable, ColumnDef encodingTable, FlatSchema accessFlat, FlatSchema encodingFlat) {
/* 475 */     int accessIndex = findFieldIndex(accessFlat, accessTable);
/* 476 */     this.indices[accessIndex] = findFieldIndex(encodingFlat, encodingTable);
/*     */ 
/*     */     
/* 479 */     this.tableMaps[accessIndex] = new CompatibilityMap(findTableSchema(accessFlat, accessTable), 
/* 480 */         findTableSchema(encodingFlat, encodingTable), accessTable);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\CompatibilityMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */