/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.impl.formats.ColumnDef;
/*     */ import com.ibm.disthub2.impl.formats.FlatSchema;
/*     */ import com.ibm.disthub2.impl.formats.Schema;
/*     */ import com.ibm.disthub2.impl.formats.SymbolTable;
/*     */ import com.ibm.disthub2.impl.formats.TupleDef;
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
/*     */ public final class PropSchema
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public static String encode(Schema schema, SymbolTable symTab) {
/*  51 */     StringBuffer ans = new StringBuffer(schema.getName());
/*  52 */     TupleDef tuple = schema.getTupleDef(0);
/*  53 */     encodeTuple(symTab.indexNames, schema.getFlatSchema(), ans, tuple);
/*  54 */     return ans.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void encodeTuple(Object[] indexNames, FlatSchema schema, StringBuffer ans, TupleDef tuple) {
/*  62 */     ans.append("[");
/*  63 */     for (int i = 0; i < tuple.getColumnCount(); i++) {
/*  64 */       ColumnDef col = tuple.getColumnDef(i);
/*  65 */       Schema variant = col.getSchema();
/*  66 */       if (variant == null) {
/*     */         
/*  68 */         ans.append(getSymbol(schema, indexNames, col)).append("(")
/*  69 */           .append(col.getTypeCode()).append(")");
/*     */       } else {
/*  71 */         encodeTuple(indexNames, schema, ans, variant.getTupleDef(1));
/*     */       } 
/*  73 */     }  ans.append("]");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getSymbol(FlatSchema schema, Object[] indexNames, ColumnDef col) {
/*  80 */     for (int i = 0; i < schema.fields.length; i++) {
/*  81 */       if (col == (schema.fields[i]).field)
/*  82 */         return (String)indexNames[i]; 
/*  83 */     }  return null;
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
/*     */   public static void decode(String encoded, TopicImpl target) {
/*  97 */     int index = encoded.indexOf('[');
/*  98 */     String name = encoded.substring(0, index);
/*  99 */     FastVector names = new FastVector();
/* 100 */     TupleDef tuple = decodeTuple(encoded.substring(index), names);
/* 101 */     int extents = countExtents(tuple);
/* 102 */     Object[] indexNames = new Object[names.m_count + extents];
/* 103 */     for (int i = 0; i < names.m_count; i++)
/* 104 */       indexNames[i] = names.m_data[i]; 
/* 105 */     Schema theSchema = new Schema();
/* 106 */     theSchema.add(tuple);
/* 107 */     theSchema.setName(name);
/* 108 */     target.schema = theSchema;
/* 109 */     target.symTab = new SymbolTable(indexNames, new String[0][], null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static TupleDef decodeTuple(String encoded, FastVector names) {
/* 116 */     encoded = encoded.substring(1, encoded.length() - 1);
/* 117 */     TupleDef ans = new TupleDef();
/*     */     while (true) {
/* 119 */       int exIndex = encoded.indexOf('[');
/* 120 */       int typeIndex = encoded.indexOf('(');
/* 121 */       if (exIndex != -1 && exIndex < typeIndex) {
/*     */ 
/*     */         
/* 124 */         TupleDef extend = decodeTuple(encoded.substring(exIndex), names);
/* 125 */         Schema var = new Schema();
/* 126 */         var.add(new TupleDef());
/* 127 */         var.add(extend);
/* 128 */         ans.add(new ColumnDef(var));
/*     */         
/*     */         break;
/*     */       } 
/* 132 */       if (typeIndex == -1) {
/*     */         break;
/*     */       }
/* 135 */       names.addElement(encoded.substring(0, typeIndex));
/* 136 */       int endIndex = encoded.indexOf(')', typeIndex + 1);
/* 137 */       byte typecode = (byte)Integer.parseInt(encoded.substring(typeIndex + 1, endIndex));
/* 138 */       ans.add(new ColumnDef(typecode));
/* 139 */       encoded = encoded.substring(endIndex + 1);
/*     */     } 
/* 141 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int countExtents(TupleDef tuple) {
/* 150 */     int size = tuple.getColumnCount();
/* 151 */     if (size < 1)
/* 152 */       return 1; 
/* 153 */     Schema next = tuple.getColumnDef(size - 1).getSchema();
/* 154 */     if (next == null)
/* 155 */       return 1; 
/* 156 */     return 1 + countExtents(next.getTupleDef(1));
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\PropSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */