/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.BadMessageFormatMatchingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface EvalContext
/*     */ {
/*  99 */   public static final EvalContext DUMMY = new EvalContext() {
/* 100 */       public Object getIdentifierValue(Identifier id, boolean ignoreType) { return null; } public Object getExprValue(int key) {
/* 101 */         return null;
/*     */       }
/*     */       
/*     */       public void saveExprValue(int key, Object value) {}
/*     */       
/*     */       public void prepareCache(int size) {}
/*     */     };
/*     */   
/*     */   Object getIdentifierValue(Identifier paramIdentifier, boolean paramBoolean) throws BadMessageFormatMatchingException;
/*     */   
/*     */   void prepareCache(int paramInt);
/*     */   
/*     */   Object getExprValue(int paramInt);
/*     */   
/*     */   void saveExprValue(int paramInt, Object paramObject);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\EvalContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */