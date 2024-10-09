/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FlatSchema
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private Object[] interpCache;
/*  49 */   private FastVector tmpVariants = new FastVector();
/*  50 */   private FastVector tmpFields = new FastVector();
/*     */ 
/*     */ 
/*     */   
/*     */   private Variant dominator;
/*     */ 
/*     */ 
/*     */   
/*     */   private int domCase;
/*     */ 
/*     */ 
/*     */   
/*     */   private Schema table;
/*     */ 
/*     */ 
/*     */   
/*     */   public Variant[] variants;
/*     */ 
/*     */ 
/*     */   
/*     */   public Field[] fields;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTable;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterpreterCache(int id, Object toCache) {
/*  79 */     if (this.interpCache == null)
/*  80 */       this.interpCache = new Object[2]; 
/*  81 */     this.interpCache[id] = toCache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getInterpreterCache(int id) {
/*  91 */     if (this.interpCache != null && id < this.interpCache.length) {
/*  92 */       return this.interpCache[id];
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlatSchema(Schema schema) {
/* 104 */     this(schema, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FlatSchema(Schema schema, boolean isTable) {
/* 113 */     if (isTable) {
/* 114 */       this.table = schema;
/*     */     }
/* 116 */     initialize(schema, "");
/*     */     
/* 118 */     this.variants = new Variant[this.tmpVariants.m_count];
/* 119 */     System.arraycopy(this.tmpVariants.m_data, 0, this.variants, 0, this.variants.length);
/* 120 */     this.fields = new Field[this.tmpFields.m_count];
/* 121 */     System.arraycopy(this.tmpFields.m_data, 0, this.fields, 0, this.fields.length);
/* 122 */     this.variants[0].organize();
/*     */     
/* 124 */     this.tmpVariants = null;
/* 125 */     this.tmpFields = null;
/* 126 */     this.dominator = null;
/* 127 */     this.table = null;
/* 128 */     this.isTable = isTable;
/*     */ 
/*     */     
/* 131 */     if (this.interpCache == null) {
/* 132 */       this.interpCache = new Object[2];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize(Schema schema, String name) {
/* 140 */     Variant newVar = new Variant(this.tmpVariants.m_count, schema, this.dominator, this.domCase, name);
/* 141 */     this.tmpVariants.addElement(newVar);
/* 142 */     if (this.dominator != null)
/* 143 */       this.dominator.addSubVariant(newVar, this.domCase); 
/* 144 */     this.dominator = newVar;
/*     */     
/* 146 */     for (int i = 0; i < schema.getChoiceCount(); i++) {
/* 147 */       TupleDef choice = schema.getTupleDef(i);
/*     */       
/* 149 */       ColumnDef nextDef = choice.getNextDef();
/* 150 */       if (nextDef != null && nextDef.getSchema() != this.table) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 155 */         this.tmpFields.addElement(new Field(nextDef, this.dominator, i, new FlatSchema(nextDef
/* 156 */                 .getSchema(), true)));
/*     */       }
/*     */       else {
/*     */         
/* 160 */         this.domCase = i;
/* 161 */         for (int j = 0; j < choice.getColumnCount(); j++) {
/* 162 */           ColumnDef def = choice.getColumnDef(j);
/* 163 */           if (def.getTypeCode() == 0) {
/*     */             
/* 165 */             initialize(def.getSchema(), def.getName());
/*     */           } else {
/*     */             
/* 168 */             this.tmpFields.addElement(new Field(def, this.dominator, this.domCase, null));
/*     */           } 
/* 170 */         }  if (nextDef != null)
/*     */         {
/*     */           
/* 173 */           this.tmpFields.addElement(new Field(nextDef, this.dominator, i, this));
/*     */         }
/*     */       } 
/*     */     } 
/* 177 */     this.domCase = this.dominator.domCase;
/* 178 */     this.dominator = this.dominator.dominator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class Dominated
/*     */   {
/*     */     public FlatSchema.Variant dominator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int domCase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Dominated(FlatSchema.Variant dominator, int domCase) {
/* 202 */       this.dominator = dominator;
/* 203 */       this.domCase = domCase;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getFullName(boolean useTableName) {
/* 208 */       String prefix = "";
/* 209 */       if (this.dominator != null) {
/* 210 */         prefix = this.dominator.getFullName(useTableName);
/* 211 */         TupleDef choice = this.dominator.schema.getTupleDef(this.domCase);
/* 212 */         if (useTableName || choice.getNextDef() == null) {
/* 213 */           String caseName = choice.getName();
/* 214 */           if (caseName != null && caseName.length() > 0) {
/* 215 */             if (prefix.length() > 0)
/* 216 */               prefix = prefix + "_"; 
/* 217 */             prefix = prefix + caseName;
/*     */           } 
/*     */         } 
/*     */       } 
/* 221 */       String shortName = getName();
/* 222 */       if (shortName == null || shortName.length() == 0)
/* 223 */         return prefix; 
/* 224 */       if (prefix.length() == 0)
/* 225 */         return shortName; 
/* 226 */       return prefix + "_" + getName();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract String getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Variant
/*     */     extends Dominated
/*     */   {
/*     */     public int index;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Schema schema;
/*     */ 
/*     */ 
/*     */     
/*     */     private String shortName;
/*     */ 
/*     */ 
/*     */     
/*     */     private FastVector[] tmpSubVariants;
/*     */ 
/*     */ 
/*     */     
/*     */     public Variant[][] subVariants;
/*     */ 
/*     */ 
/*     */     
/*     */     public long[] caseMultiCounts;
/*     */ 
/*     */ 
/*     */     
/*     */     public long multiCount;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Variant(int index, Schema schema, Variant dominator, int domCase, String name) {
/* 274 */       super(dominator, domCase);
/* 275 */       this.index = index;
/* 276 */       this.schema = schema;
/* 277 */       this.shortName = name;
/* 278 */       this.tmpSubVariants = new FastVector[schema.getChoiceCount()];
/*     */     }
/*     */ 
/*     */     
/*     */     void addSubVariant(Variant subVar, int caseIndex) {
/* 283 */       FastVector addTo = this.tmpSubVariants[caseIndex];
/* 284 */       if (addTo == null)
/* 285 */         this.tmpSubVariants[caseIndex] = addTo = new FastVector(); 
/* 286 */       addTo.addElement(subVar);
/*     */     }
/*     */ 
/*     */     
/*     */     void organize() {
/* 291 */       if (this.tmpSubVariants == null)
/*     */         return; 
/* 293 */       this.caseMultiCounts = new long[this.tmpSubVariants.length];
/* 294 */       for (int i = 0; i < this.tmpSubVariants.length; i++) {
/* 295 */         FastVector theVec = this.tmpSubVariants[i];
/* 296 */         long accum = 1L;
/* 297 */         if (theVec != null) {
/* 298 */           if (this.subVariants == null)
/* 299 */             this.subVariants = new Variant[this.tmpSubVariants.length][]; 
/* 300 */           this.subVariants[i] = new Variant[theVec.m_count];
/* 301 */           for (int j = 0; j < theVec.m_count; j++) {
/* 302 */             Variant theSub = (Variant)theVec.m_data[j];
/* 303 */             theSub.organize();
/* 304 */             this.subVariants[i][j] = theSub;
/* 305 */             accum *= theSub.multiCount;
/*     */           } 
/*     */         } 
/* 308 */         this.caseMultiCounts[i] = accum;
/* 309 */         this.multiCount += accum;
/*     */       } 
/* 311 */       this.tmpSubVariants = null;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 316 */       return this.shortName;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Field
/*     */     extends Dominated
/*     */   {
/*     */     public ColumnDef field;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FlatSchema contents;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Field(ColumnDef field, FlatSchema.Variant dominator, int domCase, FlatSchema contents) {
/* 342 */       super(dominator, domCase);
/* 343 */       this.field = field;
/* 344 */       this.contents = contents;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 349 */       return this.field.getName();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\FlatSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */