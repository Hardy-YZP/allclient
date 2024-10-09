/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.formats.FlatSchema;
/*     */ import com.ibm.disthub2.impl.formats.Schema;
/*     */ import com.ibm.disthub2.impl.formats.SymbolTable;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.Literal;
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.PositionAssigner;
/*     */ import com.ibm.disthub2.impl.matching.selector.Resolver;
/*     */ import com.ibm.disthub2.impl.matching.selector.Selector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SchemaResolver
/*     */   implements Resolver
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private FlatSchema schema;
/*     */   private SymbolTable symbolTable;
/*     */   private int parentIndex;
/*     */   
/*     */   public SchemaResolver(Schema schema, SymbolTable symTab, int type) {
/*  44 */     this.schema = schema.getFlatSchema();
/*  45 */     this.symbolTable = symTab;
/*  46 */     this.parentIndex = (type == 1) ? 24 : 31;
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
/*     */   public Selector resolve(Identifier id, PositionAssigner positionAssigner, Object context) {
/*     */     SymbolTable symTab;
/*     */     FlatSchema schema;
/*     */     Schema dynamicFieldSchema;
/*  64 */     if (context == null) {
/*  65 */       symTab = this.symbolTable;
/*  66 */       schema = this.schema;
/*  67 */       dynamicFieldSchema = (schema.variants[0]).schema;
/*     */     } else {
/*     */       
/*  70 */       symTab = (SymbolTable)((Object[])context)[0];
/*  71 */       schema = (FlatSchema)((Object[])context)[1];
/*  72 */       dynamicFieldSchema = null;
/*     */     } 
/*     */     
/*  75 */     if (id.caseOf != null) {
/*  76 */       if (id.caseOf.accessor instanceof DisthubValueAccessor) {
/*  77 */         int varIndex = ((DisthubValueAccessor)id.caseOf.accessor).index;
/*  78 */         String[] names = symTab.enumNames[varIndex - schema.fields.length];
/*  79 */         for (int j = 0; j < names.length; j++) {
/*  80 */           if (names[j].equals(id.name))
/*  81 */             return (Selector)new Literal(new NumericValue(j)); 
/*     */         } 
/*     */       } 
/*  84 */       id.type = 2;
/*  85 */       return (Selector)id;
/*     */     } 
/*     */ 
/*     */     
/*  89 */     for (int i = 0; i < symTab.indexNames.length; i++) {
/*  90 */       if (id.name.equals(nameOf(symTab.indexNames[i]))) {
/*     */ 
/*     */         
/*  93 */         if (i > schema.fields.length) {
/*     */ 
/*     */ 
/*     */           
/*  97 */           if (!id.mayBeNumeric()) {
/*  98 */             id.type = 2;
/*  99 */             return (Selector)id;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 104 */           id.accessor = new DisthubValueAccessor(dynamicFieldSchema, this.parentIndex, i, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 110 */           id.ordinalPosition = i - schema.fields.length;
/* 111 */           return (Selector)id;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         switch ((schema.fields[i]).field.getTypeCode()) {
/*     */           case 2:
/*     */           case 3:
/*     */           case 5:
/*     */           case 6:
/*     */           case 7:
/*     */           case 8:
/* 124 */             if (!id.mayBeNumeric()) {
/* 125 */               id.type = 2;
/* 126 */               return (Selector)id;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case -2:
/* 131 */             if (!id.mayBeString()) {
/* 132 */               id.type = 2;
/* 133 */               return (Selector)id;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 1:
/* 138 */             if (!id.mayBeBoolean()) {
/* 139 */               id.type = 2;
/* 140 */               return (Selector)id;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 0:
/* 145 */             if (!id.mayBeList()) {
/* 146 */               id.type = 2;
/* 147 */               return (Selector)id;
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           default:
/* 153 */             id.type = 2;
/* 154 */             return (Selector)id;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 159 */         id.accessor = new DisthubValueAccessor(dynamicFieldSchema, this.parentIndex, i, false);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 164 */         if (id.type == 4) {
/* 165 */           ((DisthubValueAccessor)id.accessor).nextRow = (schema.fields[i]).contents.fields.length - 1;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 171 */         id.ordinalPosition = i + schema.variants.length;
/* 172 */         return (Selector)id;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (this.parentIndex == 24 && context == null && id.name
/* 181 */       .startsWith("JMS")) {
/*     */       
/* 183 */       DisthubValueAccessor acc = DefaultResolver.nameToAccessor(id.name, id.type);
/* 184 */       if (acc != null && acc != DisthubValueAccessor.jmsProperty) {
/*     */         
/* 186 */         id.accessor = acc;
/*     */ 
/*     */         
/* 189 */         id
/* 190 */           .ordinalPosition = symTab.indexNames.length + DefaultResolver.findHeaderFieldIndex(acc);
/* 191 */         return (Selector)id;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 197 */     id.type = 2;
/* 198 */     return (Selector)id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String nameOf(Object o) {
/* 206 */     if (o instanceof String)
/* 207 */       return (String)o; 
/* 208 */     if (o == null) {
/* 209 */       return null;
/*     */     }
/* 211 */     return ((SymbolTable)o).name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object pushContext(Object oldContext, Identifier id) {
/*     */     SymbolTable oldTable;
/* 219 */     int index = ((DisthubValueAccessor)id.accessor).index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     if (oldContext == null) {
/* 227 */       oldTable = this.symbolTable;
/* 228 */       FlatSchema oldSchema = this.schema;
/*     */     } else {
/*     */       
/* 231 */       oldTable = (SymbolTable)((Object[])oldContext)[0];
/* 232 */       FlatSchema oldSchema = (FlatSchema)((Object[])oldContext)[1];
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 239 */     if (!(oldTable.indexNames[index] instanceof SymbolTable) || (this.schema.fields[index]).contents == null)
/*     */     {
/* 241 */       throw new IllegalStateException(); } 
/* 242 */     Object[] newContext = new Object[2];
/* 243 */     newContext[0] = oldTable.indexNames[index];
/* 244 */     newContext[1] = (this.schema.fields[index]).contents;
/* 245 */     return newContext;
/*     */   }
/*     */   
/*     */   public void restoreContext(Object toRestore) {}
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\SchemaResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */