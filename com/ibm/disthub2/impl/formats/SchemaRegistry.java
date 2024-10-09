/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.UTF;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.UTFDataFormatException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SchemaRegistry
/*     */   implements LogConstants, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  50 */   private static final DebugObject debug = new DebugObject("SchemaRegistry");
/*     */ 
/*     */   
/*  53 */   private static SchemaHash idTab = new SchemaHash();
/*     */   
/*  55 */   private static SchemaInterpreter[] interpTab = new SchemaInterpreter[2];
/*     */   
/*     */   static {
/*  58 */     interpTab[1] = new StandardInterpreter(false);
/*  59 */     interpTab[0] = new StandardInterpreter(true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SchemaHash.Entry register(Schema schema) {
/*  93 */     long id = schema.getId();
/*  94 */     SchemaHash.Entry entry = idTab.get(id);
/*  95 */     if (entry.encoding != null) {
/*     */       
/*  97 */       Assert.condition(schema.equals(entry.encoding));
/*     */     } else {
/*  99 */       entry.encoding = schema;
/* 100 */     }  return entry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MessageHandle getMessageHandle() {
/* 109 */     return interpTab[1].newMessage(ReleaseTable.envelopSchema.getFlatSchema());
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
/*     */   public static MessageHandle getMessageHandle(byte[] frame, MessageEncrypter encrypter) {
/*     */     MessageHandle result;
/* 127 */     if (debug.debugIt(32))
/* 128 */       debug.debug(-165922073994779L, "getMessageHandle", frame, encrypter); 
/* 129 */     short interpId = Framing.interpId(frame);
/*     */     
/* 131 */     if (interpId == -1) {
/* 132 */       register(frame, Framing.propagationBodyOffset(frame), Framing.fullLength(frame));
/* 133 */       result = null;
/*     */     } else {
/*     */       
/* 136 */       long schemaId = Framing.schemaId(frame);
/* 137 */       int sksl = (encrypter != null) ? Framing.sksl(frame) : -1;
/* 138 */       int offset = Framing.bodyOffset(frame);
/* 139 */       int length = Framing.fullLength(frame) - offset;
/*     */       
/* 141 */       if (interpId == 1 && schemaId == ReleaseTable.envelopSchema.getId()) {
/* 142 */         result = StandardInterpreter.fastDecode(frame, offset, length, encrypter, sksl);
/*     */       } else {
/* 144 */         SchemaInterpreter interp = interpTab[interpId];
/* 145 */         SchemaHash.Entry entry = idTab.get(schemaId);
/* 146 */         result = interp.decode(entry.encoding.getFlatSchema(), frame, offset, length, encrypter, sksl);
/*     */         
/* 148 */         if (schemaId != ReleaseTable.envelopSchema.getId())
/* 149 */           result = (MessageHandle)getCompatibility(ReleaseTable.envelopSchema, result, entry); 
/*     */       } 
/*     */     } 
/* 152 */     if (debug.debugIt(64))
/* 153 */       debug.debug(-142394261359015L, "getMessageHandle", result); 
/* 154 */     return result;
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
/*     */   public static MessageHandle getMessageHandle(short interpId, long schemaId, byte[] frame, int offset, int length) {
/* 176 */     SchemaInterpreter interp = interpTab[interpId];
/* 177 */     SchemaHash.Entry entry = idTab.get(schemaId);
/* 178 */     MessageHandle result = interp.decode(entry.encoding.getFlatSchema(), frame, offset, length, null, -1);
/*     */     
/* 180 */     if (schemaId != ReleaseTable.envelopSchema.getId())
/* 181 */       result = (MessageHandle)getCompatibility(ReleaseTable.envelopSchema, result, entry); 
/* 182 */     return result;
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
/*     */   public static MessageDataHandle getCompatibility(Schema access, MessageDataHandle handle, SchemaHash.Entry entry) {
/*     */     CompatibilityMap map;
/* 206 */     SchemaHash compat = entry.compatibility;
/* 207 */     if (compat == null)
/* 208 */       entry.compatibility = compat = new SchemaHash(); 
/* 209 */     SchemaHash.Entry centry = compat.get(access.getId());
/*     */     
/* 211 */     FlatSchema accessFlat = access.getFlatSchema();
/* 212 */     if (centry.map == null) {
/*     */ 
/*     */       
/* 215 */       centry
/* 216 */         .map = map = new CompatibilityMap(accessFlat, entry.encoding.getFlatSchema(), null);
/*     */     } else {
/* 218 */       map = centry.map;
/*     */     } 
/* 220 */     if (handle instanceof MessageHandle) {
/* 221 */       return new CompatibleMessageHandle(map, (MessageHandle)handle, accessFlat);
/*     */     }
/* 223 */     return new CompatibleMessageDataHandle(map, handle, accessFlat);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void register(byte[] msg, int offset, int fullLength) {
/*     */     String name;
/* 230 */     if (debug.debugIt(32)) {
/* 231 */       debug.debug(-165922073994779L, "register", msg, Integer.valueOf(offset));
/*     */     }
/*     */     
/* 234 */     boolean newStyle = false;
/* 235 */     if (msg[offset] == -1) {
/* 236 */       newStyle = true;
/* 237 */       offset++;
/*     */     } 
/*     */ 
/*     */     
/* 241 */     short utflen = ArrayUtil.readShort(msg, offset);
/* 242 */     offset += 2;
/*     */     
/* 244 */     if (utflen == 0) {
/* 245 */       name = "";
/*     */     } else {
/* 247 */       int charlen; char[] buf = new char[utflen];
/*     */ 
/*     */       
/* 250 */       try { charlen = UTF.UTFToChars(msg, offset, buf, 0, utflen); }
/* 251 */       catch (UTFDataFormatException e) { throw Assert.failureError(); }
/* 252 */        offset += utflen;
/* 253 */       name = new String(buf, 0, charlen);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 258 */     int schemaLen = fullLength - offset;
/* 259 */     if (newStyle) {
/* 260 */       schemaLen = ArrayUtil.readShort(msg, offset);
/* 261 */       offset += 2;
/*     */     } 
/*     */ 
/*     */     
/* 265 */     Schema toRegister = new Schema(msg, offset, schemaLen);
/* 266 */     offset += schemaLen;
/* 267 */     toRegister.setName(name);
/* 268 */     SchemaHash.Entry entry = register(toRegister);
/*     */ 
/*     */     
/* 271 */     if (newStyle) {
/* 272 */       int numMaps = ArrayUtil.readShort(msg, offset);
/* 273 */       offset += 2;
/* 274 */       for (int i = 0; i < numMaps; i++) {
/* 275 */         long schemaId = ArrayUtil.readLong(msg, offset);
/* 276 */         offset += 8;
/* 277 */         int len = ArrayUtil.readShort(msg, offset);
/* 278 */         offset += 2;
/* 279 */         CompatibilityMap map = new CompatibilityMap(msg, offset, len);
/* 280 */         offset += len;
/* 281 */         if (entry.compatibility == null)
/* 282 */           entry.compatibility = new SchemaHash(); 
/* 283 */         (entry.compatibility.get(schemaId)).map = map;
/*     */       } 
/*     */     } 
/*     */     
/* 287 */     if (debug.debugIt(64)) {
/* 288 */       debug.debug(-142394261359015L, "register");
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
/*     */   public static SchemaHash.Entry retrieve(long id) {
/* 302 */     return idTab.get(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MessageHandle reEncode(short interpId, MessageHandle oldHandle) {
/* 309 */     return interpTab[interpId].reEncode(oldHandle);
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
/*     */   public static ByteSequence getPropagationMessages(Schema[] schemata, PropagationContext context) {
/* 329 */     ByteSequence result = null;
/*     */     
/* 331 */     for (int i = 0; i < schemata.length; i++) {
/*     */       
/* 333 */       Schema schema = schemata[i];
/* 334 */       long id = schema.getId();
/*     */ 
/*     */       
/* 337 */       int release = context.testPropagation(id);
/* 338 */       if (release != -1) {
/*     */         
/* 340 */         boolean propagate = true;
/*     */         
/* 342 */         for (int j = 0; j < ReleaseTable.releases.length && 
/* 343 */           (ReleaseTable.releases[j]).release <= release; j++) {
/*     */           
/* 345 */           Schema[] rSchemas = (ReleaseTable.releases[j]).schemas;
/* 346 */           int k = 0; if (k < rSchemas.length) {
/* 347 */             Schema rs = rSchemas[k];
/* 348 */             if (rs != null && id == rs.getId())
/*     */             {
/*     */ 
/*     */               
/* 352 */               propagate = false;
/*     */             }
/*     */           } 
/*     */         } 
/* 356 */         if (propagate) {
/*     */           int nameLen;
/* 358 */           String name = schema.getName();
/* 359 */           char[] nameBuf = null;
/*     */           
/* 361 */           if (name == null || name.length() == 0) {
/* 362 */             nameLen = 0;
/*     */           } else {
/* 364 */             nameBuf = name.toCharArray();
/* 365 */             nameLen = UTF.lengthUTF(nameBuf, 0, nameBuf.length);
/*     */           } 
/*     */           
/* 368 */           byte[] encoded = schema.toEncodedForm();
/*     */           
/* 370 */           int length = 2 + nameLen + encoded.length;
/*     */           
/* 372 */           SchemaHash.Entry[] comps = null;
/* 373 */           if (release >= 65538) {
/*     */             
/* 375 */             length += 5;
/*     */             
/* 377 */             SchemaHash.Entry she = idTab.get(id);
/* 378 */             if (she.compatibility != null) {
/* 379 */               comps = she.compatibility.allEntries();
/* 380 */               if (comps != null)
/*     */               {
/* 382 */                 for (int k = 0; k < comps.length; k++)
/* 383 */                   length += 12 + ((comps[k]).map.toEncodedForm()).length;  } 
/*     */             } 
/*     */           } 
/* 386 */           byte[] ans = new byte[length];
/* 387 */           int offset = 0;
/* 388 */           if (release >= 65538) {
/* 389 */             ans[0] = -1;
/* 390 */             offset++;
/*     */           } 
/* 392 */           ArrayUtil.writeShort(ans, offset, (short)nameLen);
/* 393 */           offset += 2;
/* 394 */           if (nameLen > 0)
/* 395 */             UTF.charsToUTF(nameBuf, 0, ans, offset, nameLen); 
/* 396 */           offset += nameLen;
/* 397 */           if (release >= 65538) {
/* 398 */             ArrayUtil.writeShort(ans, offset, (short)encoded.length);
/* 399 */             offset += 2;
/*     */           } 
/* 401 */           System.arraycopy(encoded, 0, ans, offset, encoded.length);
/* 402 */           offset += encoded.length;
/* 403 */           if (release >= 65538)
/* 404 */             if (comps == null) {
/* 405 */               ArrayUtil.writeShort(ans, offset, (short)0);
/*     */             } else {
/* 407 */               ArrayUtil.writeShort(ans, offset, (short)comps.length);
/* 408 */               offset += 2;
/* 409 */               for (int k = 0; k < comps.length; k++) {
/* 410 */                 ArrayUtil.writeLong(ans, offset, (comps[k]).schemaId);
/* 411 */                 offset += 8;
/* 412 */                 byte[] toWrite = (comps[k]).map.toEncodedForm();
/* 413 */                 ArrayUtil.writeShort(ans, offset, (short)toWrite.length);
/* 414 */                 offset += 2;
/* 415 */                 System.arraycopy(toWrite, 0, ans, offset, toWrite.length);
/* 416 */                 offset += toWrite.length;
/*     */               } 
/*     */             }  
/* 419 */           if (result == null) {
/* 420 */             result = new ByteSequence(ans);
/*     */           } else {
/* 422 */             result.append(new ByteSequence(ans));
/*     */           } 
/*     */         } 
/* 425 */         context.recordPropagation(id);
/*     */       } 
/*     */     } 
/* 428 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\SchemaRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */