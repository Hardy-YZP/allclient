/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SchemaHash
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static final int NBUCKET = 256;
/*     */   private static final int PERBUCKET = 2;
/*  43 */   private Entry[][] buckets = new Entry[256][];
/*  44 */   private int[] counts = new int[256];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry get(long schemaId) {
/*  53 */     int bindex = (int)schemaId & 0xFF;
/*  54 */     int count = this.counts[bindex];
/*  55 */     Entry[] bucket = this.buckets[bindex];
/*  56 */     for (int i = 0; i < count; i++) {
/*  57 */       if (schemaId == (bucket[i]).schemaId)
/*  58 */         return bucket[i]; 
/*  59 */     }  if (bucket == null) {
/*  60 */       this.buckets[bindex] = bucket = new Entry[2];
/*  61 */     } else if (bucket.length == count) {
/*  62 */       Entry[] newBucket = new Entry[count * 2];
/*  63 */       System.arraycopy(bucket, 0, newBucket, 0, count);
/*  64 */       this.buckets[bindex] = bucket = newBucket;
/*     */     } 
/*  66 */     bucket[count] = new Entry(schemaId);
/*  67 */     this.counts[bindex] = count + 1;
/*  68 */     return bucket[count];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry[] allEntries() {
/*  75 */     int count = 0;
/*  76 */     for (int i = 0; i < this.counts.length; i++)
/*  77 */       count += this.counts[i]; 
/*  78 */     if (count == 0)
/*  79 */       return null; 
/*  80 */     Entry[] ans = new Entry[count];
/*  81 */     count = 0;
/*  82 */     for (int j = 0; j < this.counts.length; j++) {
/*  83 */       for (int k = 0; k < this.counts[j]; k++)
/*  84 */         ans[count++] = this.buckets[j][k]; 
/*  85 */     }  return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Entry
/*     */   {
/*     */     public long schemaId;
/*     */ 
/*     */     
/*     */     public Schema encoding;
/*     */ 
/*     */     
/*     */     public SchemaHash compatibility;
/*     */ 
/*     */     
/*     */     CompatibilityMap map;
/*     */ 
/*     */ 
/*     */     
/*     */     public Entry(long schemaId) {
/* 106 */       this.schemaId = schemaId;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\SchemaHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */