/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.util.FeatureSet;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FeatureExchange
/*     */   extends Payload
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  53 */   private static final DebugObject debug = new DebugObject("FeatureExchage");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   FeatureSet contents = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FeatureExchange create() {
/*  68 */     FeatureExchange newF = (FeatureExchange)(new Jgram(7)).getPayload();
/*  69 */     newF.cursor.setChoice(160, 0);
/*  70 */     newF.setQuery(false);
/*  71 */     return newF;
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
/*     */   FeatureExchange(int type, MessageDataHandle cursor, Jgram jgram) {
/*  89 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FeatureSet getContents() {
/*  98 */     if (this.contents == null)
/*  99 */       rebuildFS(); 
/* 100 */     return this.contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContents(FeatureSet f) {
/* 111 */     this.contents = f;
/* 112 */     rebuildP();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rebuildP() {
/* 122 */     synchronized (this.contents) {
/* 123 */       int n = 0;
/* 124 */       for (Enumeration<String> e1 = this.contents.features(); e1.hasMoreElements(); ) {
/* 125 */         String nextEl = e1.nextElement();
/* 126 */         n += this.contents.get(nextEl).size();
/*     */       } 
/* 128 */       MessageBodyHandle[] fTable = new MessageBodyHandle[n];
/* 129 */       n = 0;
/* 130 */       for (Enumeration<String> enumeration1 = this.contents.features(); enumeration1.hasMoreElements(); ) {
/* 131 */         String nextEl = enumeration1.nextElement();
/* 132 */         for (Enumeration<String> e2 = this.contents.parameters(nextEl); e2.hasMoreElements(); n++) {
/* 133 */           String nextParam = e2.nextElement();
/* 134 */           String nextVal = this.contents.get(nextEl, nextParam);
/* 135 */           fTable[n] = this.cursor.newTableRow(43);
/* 136 */           fTable[n].setString(0, nextEl);
/* 137 */           fTable[n].setString(1, nextParam);
/* 138 */           fTable[n].setString(2, nextVal);
/*     */         } 
/*     */       } 
/* 141 */       this.cursor.setTable(43, fTable);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rebuildFS() {
/* 151 */     String featureName = null;
/* 152 */     String paramName = null;
/* 153 */     String paramVal = null;
/*     */ 
/*     */     
/* 156 */     this.contents = new FeatureSet();
/*     */ 
/*     */     
/* 159 */     MessageBodyHandle t = this.cursor.getTableRow(43, 0);
/* 160 */     for (; t != null; 
/* 161 */       t = t.getSuccessor()) {
/* 162 */       featureName = t.getString(0);
/* 163 */       paramName = t.getString(1);
/* 164 */       paramVal = t.getString(2);
/* 165 */       this.contents.put(featureName, paramName, paramVal);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuery(boolean val) {
/* 175 */     this.cursor.setBoolean(42, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getQuery() {
/* 185 */     return this.cursor.getBoolean(42);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\FeatureExchange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */