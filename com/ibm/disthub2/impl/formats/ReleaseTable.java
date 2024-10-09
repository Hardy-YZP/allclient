/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ReleaseTable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  38 */   public static Schema envelopSchema = new Envelop();
/*  39 */   public static Schema multiSchema = new Multi();
/*     */ 
/*     */   
/*     */   private static final int nSchemas = 2;
/*     */ 
/*     */   
/*  45 */   public static final Entry[] releases = new Entry[] { new Entry(65537, (short)-13647, (byte)1, (short)0), new Entry(65538, (short)-13647, (byte)1, (short)1) };
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int FMR2 = 65538;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  55 */     addSchemas(0, new Schema[] { new Envelop_1_1(), envelopSchema });
/*  56 */     addSchemas(1, new Schema[] { new Multi_1_1(), multiSchema });
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
/*     */   public static void addSchemas(int position, Schema[] schemas) {
/*  70 */     Assert.condition((schemas.length == releases.length));
/*  71 */     Schema current = schemas[schemas.length - 1];
/*  72 */     FlatSchema currentFlat = current.getFlatSchema();
/*  73 */     for (int i = 0; i < schemas.length; i++) {
/*  74 */       Schema schema = schemas[i];
/*  75 */       if (schema != null) {
/*  76 */         SchemaHash.Entry entry = SchemaRegistry.register(schema);
/*  77 */         SchemaHash comp = entry.compatibility;
/*  78 */         if (comp == null)
/*  79 */           entry.compatibility = comp = new SchemaHash(); 
/*  80 */         FlatSchema schemaFlat = schema.getFlatSchema();
/*  81 */         if (schema != current) {
/*     */ 
/*     */           
/*  84 */           SchemaHash.Entry cEntry = comp.get(current.getId());
/*  85 */           cEntry.map = new CompatibilityMap(currentFlat, schemaFlat, null);
/*     */ 
/*     */           
/*  88 */           new CompatibilityMap(schemaFlat, currentFlat, null);
/*     */         } 
/*     */         
/*  91 */         (releases[i]).schemas[position] = schema;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Entry
/*     */   {
/*     */     public int release;
/*     */     
/*     */     public Schema[] schemas;
/*     */     
/*     */     public short magic;
/*     */     public byte schemaVersion;
/*     */     public short interp;
/*     */     
/*     */     public Entry(int release, short magic, byte schemaVersion, short interp) {
/* 108 */       this.release = release; this.magic = magic; this.schemaVersion = schemaVersion; this.interp = interp;
/* 109 */       this.schemas = new Schema[2];
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\ReleaseTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */