/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.formats.MessageHandle;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Evaluator;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MatchingContext
/*     */   extends EvalCache
/*     */   implements EvalContext, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  67 */   private static final DebugObject debug = new DebugObject("MatchingContext");
/*     */ 
/*     */ 
/*     */   
/*     */   private MessageDataHandle handle;
/*     */ 
/*     */ 
/*     */   
/*     */   private FormattedMessage msg;
/*     */ 
/*     */ 
/*     */   
/*  79 */   private String prefix = "";
/*     */   
/*     */   private int prefixLen;
/*     */ 
/*     */   
/*     */   public void setMessage(MessageHandle handle) {
/*  85 */     this.handle = (MessageDataHandle)handle;
/*  86 */     this.msg = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessage(FormattedMessage msg) {
/*  93 */     this.msg = msg;
/*  94 */     this.handle = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 103 */     this.prefix = prefix;
/* 104 */     this.prefixLen = prefix.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getIdentifierValue(Identifier id, boolean ignoreType) throws BadMessageFormatMatchingException {
/* 112 */     int type = ignoreType ? 0 : id.type;
/* 113 */     if (this.handle != null) {
/*     */       DisthubValueAccessor acc;
/*     */       
/* 116 */       if (id.accessor instanceof DisthubValueAccessor) {
/*     */         
/* 118 */         acc = (DisthubValueAccessor)id.accessor;
/*     */       } else {
/*     */         
/* 121 */         if (debug.debugIt(16))
/* 122 */           debug.debug(-153415734321212L, "getIdentifierValue", "DM/NS match on " + id.name); 
/* 123 */         if (!id.name.startsWith(this.prefix))
/* 124 */           return null; 
/* 125 */         acc = DefaultResolver.nameToAccessor(id.name.substring(this.prefixLen), type);
/* 126 */         if (acc == null) {
/* 127 */           return null;
/*     */         }
/*     */       } 
/* 130 */       if (acc == DisthubValueAccessor.jmsProperty)
/*     */       {
/* 132 */         return jmsPropertyAccess(id.name.substring(this.prefixLen), type);
/*     */       }
/*     */       
/* 135 */       return acc.getValue(this.handle);
/*     */     } 
/* 137 */     if (this.msg == null)
/*     */     {
/* 139 */       return null;
/*     */     }
/*     */     
/* 142 */     return fmtMsgAccess(id.name, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object fmtMsgAccess(String name, int type) throws BadMessageFormatMatchingException {
/*     */     Object value;
/* 150 */     if (type == 4) {
/* 151 */       return null;
/*     */     }
/*     */     try {
/* 154 */       value = this.msg.getFieldValue(name);
/* 155 */       if (value == null)
/* 156 */         return null; 
/* 157 */     } catch (NoSuchFieldNameException e) {
/* 158 */       return null;
/*     */     } 
/* 160 */     if (MatchSpace.getPermissive()) {
/*     */ 
/*     */       
/* 163 */       Object castObj = typeCast(value, type);
/* 164 */       if (debug.debugIt(16))
/* 165 */         debug.debug(-153415734321212L, "fmtMsgAccess", "Have cast value to: " + castObj); 
/* 166 */       return castObj;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     Object checkObj = typeCheck(value, type);
/* 172 */     if (debug.debugIt(16))
/* 173 */       debug.debug(-153415734321212L, "fmtMsgAccess", "Have checked value : " + checkObj); 
/* 174 */     return checkObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object typeCheck(Object value, int type) {
/* 184 */     if (debug.debugIt(16)) {
/*     */       
/* 186 */       StringBuffer debugString = new StringBuffer();
/*     */       
/* 188 */       if (value != null) {
/*     */         
/* 190 */         debugString.append(value.getClass().getName());
/* 191 */         debugString.append(",");
/*     */       } 
/*     */       
/* 194 */       debugString.append("code=");
/* 195 */       debugString.append(type);
/*     */       
/* 197 */       debug.debug(-153415734321212L, "typeCheck", debugString.toString());
/*     */     } 
/*     */     
/* 200 */     switch (type) {
/*     */       case 0:
/* 202 */         return value;
/*     */       case -5:
/* 204 */         return (value instanceof String) ? value : null;
/*     */       case -6:
/* 206 */         return (value instanceof com.ibm.disthub2.impl.matching.selector.BooleanValue) ? value : null;
/*     */     } 
/* 208 */     if (value instanceof NumericValue)
/* 209 */       return value; 
/* 210 */     if (value instanceof Number) {
/*     */       
/* 212 */       NumericValue nVal = new NumericValue((Number)value);
/* 213 */       return nVal;
/*     */     } 
/*     */     
/* 216 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object typeCast(Object value, int type) {
/* 226 */     if (debug.debugIt(16))
/* 227 */       debug.debug(-153415734321212L, "typeCast", value.getClass().getName() + ",code=" + type); 
/* 228 */     switch (type) {
/*     */       case 0:
/* 230 */         return value;
/*     */       case -5:
/* 232 */         return (value instanceof String) ? value : null;
/*     */       case -6:
/* 234 */         return Evaluator.castToBoolean(value);
/*     */     } 
/* 236 */     if (value instanceof NumericValue)
/* 237 */       return value; 
/* 238 */     if (value instanceof Number) {
/*     */       
/* 240 */       NumericValue nVal = new NumericValue((Number)value);
/* 241 */       return nVal;
/*     */     } 
/*     */     
/* 244 */     return Evaluator.castToNumber(value);
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
/*     */   private Object jmsPropertyAccess(String name, int type) throws BadMessageFormatMatchingException {
/* 256 */     if (debug.debugIt(16))
/* 257 */       debug.debug(-153415734321212L, "jmsPropertyAccess", name, Integer.valueOf(type)); 
/* 258 */     Object val = null;
/*     */     try {
/* 260 */       if (!this.handle.isPresent(23)) {
/* 261 */         return null;
/*     */       }
/* 263 */       MessageDataHandle t = (MessageDataHandle)this.handle.getTableRow(23, 0);
/* 264 */       for (; t != null; 
/* 265 */         t = (MessageDataHandle)t.getSuccessor()) {
/* 266 */         if (name.equals(t.getString(0)))
/*     */         
/*     */         { 
/*     */ 
/*     */           
/* 271 */           if (debug.debugIt(16))
/* 272 */             debug.debug(-153415734321212L, "jmsPropertyAccess", "match on " + name); 
/* 273 */           int index = t.getChoice(11);
/* 274 */           val = t.getInternalValue(index);
/* 275 */           if (val != null)
/*     */             break;  } 
/*     */       } 
/* 278 */     } catch (RuntimeException e) {
/* 279 */       throw new BadMessageFormatMatchingException();
/*     */     } 
/* 281 */     if (val == null) {
/* 282 */       if (debug.debugIt(16))
/* 283 */         debug.debug(-153415734321212L, "jmsPropertyAccess", "null return for " + name); 
/* 284 */       return null;
/*     */     } 
/*     */     
/* 287 */     val = typeCheck(val, type);
/* 288 */     if (debug.debugIt(16))
/* 289 */       if (val == null) {
/* 290 */         debug.debug(-153415734321212L, "jmsPropertyAccess", name + " failed typeCheck");
/*     */       } else {
/* 292 */         debug.debug(-153415734321212L, "jmsPropertyAccess", name + "=" + val);
/*     */       }  
/* 294 */     return val;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\MatchingContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */