/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.client.Schema;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.formats.Schema;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContextList;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.ValueAccessor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DisthubValueAccessor
/*     */   implements ValueAccessor
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public Schema schema;
/*     */   public int parentIndex;
/*     */   public DisthubValueAccessor child;
/*     */   public int index;
/*     */   public boolean isVariant;
/*     */   public int nextRow;
/*     */   public Action finalAction;
/* 111 */   public static final DisthubValueAccessor jmsProperty = new DisthubValueAccessor();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DisthubValueAccessor() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DisthubValueAccessor(int index, Action finalAction) {
/* 128 */     this.index = index;
/* 129 */     this.finalAction = finalAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DisthubValueAccessor(Schema schema, int parentIndex, int index, boolean isVariant) {
/* 138 */     this.schema = schema;
/* 139 */     this.parentIndex = parentIndex;
/* 140 */     this.index = index;
/* 141 */     this.isVariant = isVariant;
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
/*     */ 
/*     */   
/*     */   public Object getValue(MessageDataHandle handle) throws BadMessageFormatMatchingException {
/*     */     try {
/* 169 */       if (this.schema != null) {
/* 170 */         handle = (MessageDataHandle)handle.getDynamic(this.parentIndex, (Schema)this.schema);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 176 */       if (this.child != null) {
/* 177 */         return this.child.getValue(handle);
/*     */       }
/*     */       
/* 180 */       if (!handle.isPresent(this.index)) {
/* 181 */         return null;
/*     */       }
/*     */       
/* 184 */       if (this.isVariant) {
/* 185 */         return new NumericValue(handle.getChoice(this.index));
/*     */       }
/*     */       
/* 188 */       Object ans = handle.getInternalValue(this.index);
/*     */ 
/*     */       
/* 191 */       if (ans != null && this.finalAction != null) {
/* 192 */         ans = this.finalAction.convert(ans);
/*     */       }
/*     */ 
/*     */       
/* 196 */       if (ans instanceof MessageDataHandle) {
/* 197 */         return new EvalContextListImpl((MessageDataHandle)ans, this.nextRow);
/*     */       }
/* 199 */       return ans;
/* 200 */     } catch (RuntimeException e) {
/* 201 */       throw new BadMessageFormatMatchingException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Action {
/*     */     Object convert(Object param1Object);
/*     */   }
/*     */   
/*     */   private static final class EvalContextImpl
/*     */     implements EvalContext {
/*     */     private MessageDataHandle handle;
/*     */     private EvalContextImpl next;
/*     */     private boolean noNext;
/*     */     private int nextRow;
/*     */     
/*     */     EvalContextImpl(MessageDataHandle handle, int nextRow) {
/* 217 */       this.handle = handle;
/* 218 */       this.nextRow = nextRow;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     EvalContextImpl getNext() {
/* 224 */       if (this.next == null) {
/* 225 */         if (this.noNext) {
/* 226 */           return null;
/*     */         }
/* 228 */         MessageDataHandle nextHandle = (MessageDataHandle)this.handle.getSuccessor();
/* 229 */         if (nextHandle == null) {
/* 230 */           this.noNext = true;
/*     */         } else {
/* 232 */           this.next = new EvalContextImpl(nextHandle, this.nextRow);
/*     */         } 
/* 234 */       }  return this.next;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getExprValue(int key) {
/* 240 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void saveExprValue(int key, Object value) {}
/*     */ 
/*     */     
/*     */     public void prepareCache(int size) {}
/*     */     
/*     */     public Object getIdentifierValue(Identifier id, boolean ignoreType) throws BadMessageFormatMatchingException {
/* 250 */       return ((DisthubValueAccessor)id.accessor).getValue(this.handle);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class EvalContextListImpl
/*     */     implements EvalContextList
/*     */   {
/*     */     private FastVector cache;
/*     */ 
/*     */ 
/*     */     
/*     */     EvalContextListImpl(MessageDataHandle handle, int nextRow) {
/* 264 */       if (handle != null) {
/* 265 */         this.cache = new FastVector();
/* 266 */         this.cache.addElement(new DisthubValueAccessor.EvalContextImpl(handle, nextRow));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EvalContext index(int n) {
/* 274 */       if (this.cache == null)
/* 275 */         return null; 
/* 276 */       if (n >= this.cache.m_count)
/*     */       {
/* 278 */         for (int i = this.cache.m_count - 1; i < n; i++) {
/* 279 */           DisthubValueAccessor.EvalContextImpl next = ((DisthubValueAccessor.EvalContextImpl)this.cache.m_data[i]).getNext();
/* 280 */           if (next == null)
/* 281 */             return null; 
/* 282 */           this.cache.addElement(next);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 287 */       return (DisthubValueAccessor.EvalContextImpl)this.cache.m_data[n];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EvalContext successor(EvalContext pred) {
/* 293 */       if (pred == null) {
/* 294 */         return index(0);
/*     */       }
/* 296 */       return ((DisthubValueAccessor.EvalContextImpl)pred).getNext();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 302 */       return (this.cache == null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\DisthubValueAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */