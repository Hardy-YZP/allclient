/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.PropSchema;
/*     */ import com.ibm.disthub2.impl.formats.Schema;
/*     */ import com.ibm.disthub2.impl.formats.SymbolTable;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.IllegalParameterException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SchemaTarget
/*     */   extends MatchTarget
/*     */   implements ClientExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2004 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public int type;
/*     */   public SchemaResolver schema;
/*     */   private String queryResponse;
/*     */   
/*     */   public SchemaTarget(String topic, int type, Schema schema, SymbolTable symTab) throws IllegalParameterException {
/*  59 */     super(8, topic);
/*  60 */     this.type = type;
/*  61 */     switch (type) {
/*     */       case 0:
/*  63 */         this.queryResponse = "*";
/*     */         break;
/*     */       case 1:
/*     */         try {
/*  67 */           this.queryResponse = "PROPERTY_SCHEMA:" + PropSchema.encode(schema, symTab);
/*  68 */         } catch (RuntimeException e) {
/*  69 */           throw illegal(topic);
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  73 */         if (schema == null)
/*  74 */           throw illegal(topic); 
/*  75 */         this.queryResponse = schema.getClass().getName();
/*     */         break;
/*     */       default:
/*  78 */         throw illegal(topic);
/*     */     } 
/*  80 */     if (schema != null) {
/*  81 */       this.schema = new SchemaResolver(schema, symTab, type);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private IllegalParameterException illegal(String topic) {
/*  88 */     return new IllegalParameterException(ExceptionBuilder.buildReasonString(-1384630443, new Object[] { topic }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  96 */     return this.queryResponse;
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
/*     */   
/*     */   public static SchemaTarget getSchemaTarget(String topic, boolean hasWilds, MatchSpace matchSpace, Results results) throws MatchingException {
/* 121 */     if (hasWilds) {
/*     */       
/* 123 */       int firstWild = findFirstMatchManyWildcard(topic);
/* 124 */       if (firstWild == -1)
/* 125 */         firstWild = topic.length(); 
/* 126 */       int otherWild = findFirstMatchOneWildcard(topic);
/* 127 */       if (otherWild != -1) {
/* 128 */         firstWild = (otherWild < firstWild) ? otherWild : firstWild;
/*     */       }
/* 130 */       if (firstWild > 0) firstWild--; 
/* 131 */       topic = topic.substring(0, firstWild);
/*     */     } 
/* 133 */     if (results == null) {
/* 134 */       results = new Results();
/*     */     } else {
/* 136 */       results.reset();
/* 137 */     }  matchSpace.getAdmin(topic, results);
/* 138 */     return results.target;
/*     */   }
/*     */   
/*     */   public static final class Results implements SearchResults {
/*     */     public SchemaTarget target;
/*     */     
/*     */     public void reset() {
/* 145 */       this.target = null;
/*     */     } public void addObjects(FastVector[] objects) {
/* 147 */       if (objects != null && objects.length > 8) {
/* 148 */         FastVector v = objects[8];
/* 149 */         if (v != null && v.m_count > 0)
/* 150 */           this.target = (SchemaTarget)v.m_data[v.m_count - 1]; 
/*     */       } 
/*     */     }
/* 153 */     public Object provideCacheable(String topic) { return null; } public boolean acceptCacheable(Object cached) {
/* 154 */       return false;
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
/*     */   private static final int findFirstMatchManyWildcard(String topic) {
/* 170 */     int firstOccurrence = -1;
/*     */     
/* 172 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 175 */     if (topic == null || topiclength == 0) {
/* 176 */       return -1;
/*     */     }
/*     */     
/* 179 */     if (topiclength == 1) {
/*     */       
/* 181 */       if (topic.charAt(0) == '#') {
/* 182 */         return 0;
/*     */       }
/*     */       
/* 185 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 189 */     if (topic.charAt(0) == '#' && topic
/* 190 */       .charAt(1) == '/') {
/* 191 */       return 0;
/*     */     }
/*     */     
/* 194 */     firstOccurrence = topic.indexOf("/#/");
/* 195 */     if (firstOccurrence != -1) {
/* 196 */       return firstOccurrence + 1;
/*     */     }
/*     */     
/* 199 */     if (topic.charAt(topiclength - 2) == '/' && topic
/* 200 */       .charAt(topiclength - 1) == '#') {
/* 201 */       return topiclength - 1;
/*     */     }
/*     */     
/* 204 */     return firstOccurrence;
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
/*     */   private static final int findFirstMatchOneWildcard(String topic) {
/* 220 */     int firstOccurrence = -1;
/*     */     
/* 222 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 225 */     if (topic == null || topiclength == 0) {
/* 226 */       return -1;
/*     */     }
/*     */     
/* 229 */     if (topiclength == 1) {
/*     */       
/* 231 */       if (topic.charAt(0) == '+') {
/* 232 */         return 0;
/*     */       }
/*     */       
/* 235 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 239 */     if (topic.charAt(0) == '+' && topic
/* 240 */       .charAt(1) == '/') {
/* 241 */       return 0;
/*     */     }
/*     */     
/* 244 */     firstOccurrence = topic.indexOf("/+/");
/* 245 */     if (firstOccurrence != -1) {
/* 246 */       return firstOccurrence + 1;
/*     */     }
/*     */     
/* 249 */     if (topic.charAt(topiclength - 2) == '/' && topic
/* 250 */       .charAt(topiclength - 1) == '+') {
/* 251 */       return topiclength - 1;
/*     */     }
/*     */     
/* 254 */     return firstOccurrence;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\SchemaTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */