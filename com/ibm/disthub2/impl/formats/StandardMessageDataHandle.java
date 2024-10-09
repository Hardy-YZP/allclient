/*      */ package com.ibm.disthub2.impl.formats;
/*      */ 
/*      */ import com.ibm.disthub2.client.MessageBodyHandle;
/*      */ import com.ibm.disthub2.client.Schema;
/*      */ import com.ibm.disthub2.client.SchemaViolationException;
/*      */ import com.ibm.disthub2.client.UninitializedAccessException;
/*      */ import com.ibm.disthub2.impl.client.DebugObject;
/*      */ import com.ibm.disthub2.impl.matching.selector.BooleanValue;
/*      */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*      */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*      */ import com.ibm.disthub2.impl.util.Assert;
/*      */ import com.ibm.disthub2.impl.util.FastVector;
/*      */ import com.ibm.disthub2.impl.util.Hex;
/*      */ import com.ibm.disthub2.impl.util.UTF;
/*      */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*      */ import com.ibm.disthub2.spi.ExceptionConstants;
/*      */ import com.ibm.disthub2.spi.LogConstants;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.io.UTFDataFormatException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StandardMessageDataHandle
/*      */   implements MessageDataHandle, ExceptionConstants, LogConstants, SchemaCodes
/*      */ {
/*      */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  118 */   private static final DebugObject debug = new DebugObject("StandardMessageDataHandle");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected FlatSchema schema;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MessageMap map;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] contents;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int tableOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int length;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int sksl;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] oTable;
/*      */ 
/*      */ 
/*      */   
/*      */   private int dataOffset;
/*      */ 
/*      */ 
/*      */   
/*      */   private Object[] cache;
/*      */ 
/*      */ 
/*      */   
/*      */   private Object[] cache2;
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] choiceCache;
/*      */ 
/*      */ 
/*      */   
/*      */   private StandardMessageHandle master;
/*      */ 
/*      */ 
/*      */   
/*  177 */   public static Object nullIndicator = new Object();
/*      */ 
/*      */ 
/*      */   
/*  181 */   private static StandardMessageDataHandle nullTable = new StandardMessageDataHandle(new FlatSchema(new Schema()));
/*      */ 
/*      */ 
/*      */   
/*      */   private char[] charbuf;
/*      */ 
/*      */   
/*      */   private static final int MIN_CHARBUF_LEN = 64;
/*      */ 
/*      */   
/*  191 */   private static Class[] valueTypes = new Class[] { byte[].class, null, String.class, StandardMessageDataHandle.class, StandardMessageDataHandle[].class, Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean changed;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean cryptoDirty;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StandardMessageDataHandle(FlatSchema sfs) {
/*  219 */     this.schema = sfs;
/*  220 */     this.cache = new Object[sfs.fields.length];
/*  221 */     this.cache2 = new Object[this.cache.length];
/*  222 */     this.cryptoDirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void makeChoiceCache() {
/*  228 */     this.choiceCache = new int[this.schema.variants.length];
/*  229 */     if (this.map == null) {
/*  230 */       for (int i = 0; i < this.schema.variants.length; i++) {
/*  231 */         this.choiceCache[i] = -1;
/*      */       }
/*      */     } else {
/*      */       
/*  235 */       for (int i = 0; i < this.map.choiceCodes.length; i++) {
/*  236 */         this.choiceCache[i] = this.map.choiceCodes[i];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StandardMessageDataHandle(FlatSchema sfs, StandardMessageHandle master) {
/*  245 */     this(sfs);
/*  246 */     this.master = master;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void oldMessage(byte[] contents, int offset, int length, int sksl, long multiChoice) {
/*  266 */     this.contents = contents;
/*  267 */     this.tableOffset = offset;
/*  268 */     this.length = length;
/*  269 */     this.sksl = sksl;
/*  270 */     this.cryptoDirty = false;
/*  271 */     this.map = StandardInterpreter.getMessageMap(this.schema, multiChoice);
/*  272 */     this.oTable = new int[this.map.offsetsNeeded];
/*  273 */     this.dataOffset = offset;
/*  274 */     for (int i = 0; i < this.oTable.length; i++) {
/*  275 */       this.oTable[i] = ArrayUtil.readInt(contents, this.dataOffset);
/*  276 */       this.dataOffset += 4;
/*      */     } 
/*  278 */     if (debug.debugIt(16)) {
/*  279 */       debug.debug(-153415734321212L, "oldMessage", "tableOffset=" + this.tableOffset + ",dataOffset=" + this.dataOffset + ",length=" + length);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void getSchemata(FastVector accum, boolean addTop) {
/*  299 */     if (addTop) {
/*  300 */       accum.addElement(getEncodingSchema());
/*      */     }
/*  302 */     for (int i = 0; i < this.schema.fields.length; i++) {
/*  303 */       if ((this.map != null && this.map.fields[i] != null) || this.cache[i] != null) {
/*      */         StandardMessageDataHandle row;
/*      */ 
/*      */         
/*  307 */         switch ((this.schema.fields[i]).field.getTypeCode()) {
/*      */           case -1:
/*  309 */             ((StandardMessageDataHandle)getDynamic(i, null)).getSchemata(accum, true);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 0:
/*  314 */             row = getTableRow(i);
/*      */ 
/*      */             
/*  317 */             if (row != null) {
/*  318 */               row.getSchemata(accum, false);
/*      */             }
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getLength(Object val) {
/*  331 */     if (val instanceof String)
/*      */     {
/*      */       
/*  334 */       return convertString((String)val);
/*      */     }
/*  336 */     if (val instanceof byte[])
/*      */     {
/*  338 */       return ((byte[])val).length;
/*      */     }
/*  340 */     if (val instanceof StandardMessageDataHandle) {
/*  341 */       if (val == nullTable) {
/*  342 */         return 8;
/*      */       }
/*      */       
/*  345 */       return ((StandardMessageDataHandle)val).getEncodedLength() + 8;
/*      */     } 
/*      */     
/*  348 */     if (val == nullIndicator) {
/*  349 */       return 0;
/*      */     }
/*      */     
/*  352 */     throw Assert.failureError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getEncodedLength() {
/*  360 */     if (this.contents != null)
/*      */     {
/*  362 */       return this.length;
/*      */     }
/*      */ 
/*      */     
/*  366 */     if (this.map == null) {
/*  367 */       this.map = StandardInterpreter.getMessageMap(this.schema, this.choiceCache);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  374 */     int ans = this.map.offsetsNeeded * 4;
/*      */ 
/*      */ 
/*      */     
/*  378 */     for (int i = 0; i < this.map.fields.length; i++) {
/*  379 */       if (this.map.fields[i] != null) {
/*  380 */         int len = (this.map.fields[i]).length;
/*  381 */         if (len >= 0) {
/*  382 */           ans += len;
/*      */         } else {
/*      */           
/*  385 */           Object val = this.cache[i];
/*  386 */           if (val == null) {
/*  387 */             uninit(i);
/*      */           }
/*      */           
/*  390 */           ans += 4;
/*      */           
/*  392 */           ans += getLength(val);
/*      */           
/*  394 */           if (len == -2) {
/*  395 */             ans += 10;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  400 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String readString(byte[] buffer, int offset, int len) {
/*  406 */     if (this.charbuf == null || this.charbuf.length < len) {
/*  407 */       this.charbuf = new char[(len > 64) ? len : 64];
/*      */     }
/*      */     try {
/*  410 */       int charlen = UTF.UTFToChars(buffer, offset, this.charbuf, 0, len);
/*  411 */       return new String(this.charbuf, 0, charlen);
/*      */     }
/*  413 */     catch (UTFDataFormatException e) {
/*  414 */       throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-63717088, new Object[] { e }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int convertString(String s) {
/*  422 */     int slen = s.length();
/*  423 */     if (this.charbuf == null || this.charbuf.length < slen) {
/*  424 */       this.charbuf = new char[(slen > 64) ? slen : 64];
/*      */     }
/*  426 */     s.getChars(0, slen, this.charbuf, 0);
/*  427 */     return UTF.lengthUTF(this.charbuf, 0, slen);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object readObject(byte typecode, byte[] buffer, int offset, int length, FlatSchema forTable) {
/*      */     int len;
/*      */     byte[] b;
/*  436 */     switch (typecode) {
/*      */       case 1:
/*  438 */         return BooleanValue.valueOf((buffer[offset] != 0));
/*      */       case 2:
/*  440 */         return new NumericValue(buffer[offset]);
/*      */       case 3:
/*  442 */         return new NumericValue(ArrayUtil.readShort(buffer, offset));
/*      */       case 4:
/*  444 */         return Character.valueOf((char)ArrayUtil.readShort(buffer, offset));
/*      */       case 5:
/*  446 */         return new NumericValue(ArrayUtil.readInt(buffer, offset));
/*      */       case 6:
/*  448 */         return new NumericValue(ArrayUtil.readLong(buffer, offset), true);
/*      */       case 7:
/*  450 */         return new NumericValue(Float.intBitsToFloat(ArrayUtil.readInt(buffer, offset)));
/*      */       case 8:
/*  452 */         return new NumericValue(Double.longBitsToDouble(ArrayUtil.readLong(buffer, offset)));
/*      */       case -4:
/*      */       case -3:
/*      */       case -2:
/*  456 */         len = ArrayUtil.readInt(buffer, offset);
/*  457 */         if (len == -1) {
/*  458 */           return nullIndicator;
/*      */         }
/*  460 */         Assert.condition((len >= 0 && len + offset <= length));
/*  461 */         offset += 4;
/*  462 */         if (typecode == -2) {
/*  463 */           return readString(buffer, offset, len);
/*      */         }
/*  465 */         b = new byte[len];
/*  466 */         System.arraycopy(buffer, offset, b, 0, len);
/*  467 */         return b;
/*      */       case -1:
/*  469 */         return readDynamic(buffer, offset, length, null);
/*      */       case 0:
/*  471 */         return readTableRow(buffer, offset, length, forTable);
/*      */     } 
/*  473 */     throw Assert.failureError();
/*      */   } private int writeObject(Object val, byte typecode, byte[] buffer, int offset) {
/*      */     int utflen;
/*      */     byte[] b;
/*      */     StandardMessageDataHandle table;
/*      */     int tlen;
/*      */     StandardMessageDataHandle dynam;
/*      */     int dlen;
/*  481 */     if (val == nullIndicator) {
/*  482 */       ArrayUtil.writeInt(buffer, offset, -1);
/*  483 */       return offset + 4;
/*      */     } 
/*      */     
/*  486 */     switch (typecode) {
/*      */       case 1:
/*  488 */         buffer[offset] = ((BooleanValue)val).booleanValue() ? 1 : 0;
/*  489 */         return offset + 1;
/*      */       case 2:
/*  491 */         buffer[offset] = ((NumericValue)val).byteValue();
/*  492 */         return offset + 1;
/*      */       case 3:
/*  494 */         ArrayUtil.writeShort(buffer, offset, ((NumericValue)val).shortValue());
/*  495 */         return offset + 2;
/*      */       case 4:
/*  497 */         ArrayUtil.writeShort(buffer, offset, (short)((Character)val).charValue());
/*  498 */         return offset + 2;
/*      */       case 5:
/*  500 */         ArrayUtil.writeInt(buffer, offset, ((NumericValue)val).intValue());
/*  501 */         return offset + 4;
/*      */       case 6:
/*  503 */         ArrayUtil.writeLong(buffer, offset, ((NumericValue)val).longValue());
/*  504 */         return offset + 8;
/*      */       case 7:
/*  506 */         ArrayUtil.writeInt(buffer, offset, Float.floatToIntBits(((NumericValue)val).floatValue()));
/*  507 */         return offset + 4;
/*      */       case 8:
/*  509 */         ArrayUtil.writeLong(buffer, offset, 
/*  510 */             Double.doubleToLongBits(((NumericValue)val).doubleValue()));
/*  511 */         return offset + 8;
/*      */       case -2:
/*  513 */         utflen = convertString((String)val);
/*  514 */         ArrayUtil.writeInt(buffer, offset, utflen);
/*  515 */         offset += 4;
/*  516 */         UTF.charsToUTF(this.charbuf, 0, buffer, offset, ((String)val).length());
/*  517 */         return offset + utflen;
/*      */       case -4:
/*      */       case -3:
/*  520 */         b = (byte[])val;
/*  521 */         ArrayUtil.writeInt(buffer, offset, b.length);
/*  522 */         offset += 4;
/*  523 */         System.arraycopy(b, 0, buffer, offset, b.length);
/*  524 */         return offset + b.length;
/*      */       
/*      */       case 0:
/*  527 */         table = (StandardMessageDataHandle)val;
/*      */         
/*  529 */         if (table == nullTable) {
/*  530 */           tlen = 8;
/*      */         } else {
/*      */           
/*  533 */           tlen = table.getEncodedLength() + 8;
/*      */         } 
/*  535 */         ArrayUtil.writeInt(buffer, offset, tlen);
/*  536 */         offset += 4;
/*  537 */         if (table == nullTable) {
/*  538 */           ArrayUtil.writeLong(buffer, offset, 0L);
/*      */         } else {
/*      */           
/*  541 */           ArrayUtil.writeLong(buffer, offset, table.map.multiChoice);
/*  542 */           table.toByteArray(buffer, offset + 8);
/*      */         } 
/*  544 */         return offset + tlen;
/*      */ 
/*      */       
/*      */       case -1:
/*  548 */         dynam = (StandardMessageDataHandle)val;
/*  549 */         dlen = dynam.getEncodedLength();
/*  550 */         ArrayUtil.writeInt(buffer, offset, dlen + 18);
/*  551 */         offset += 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  558 */         ArrayUtil.writeShort(buffer, offset, (short)1);
/*  559 */         offset += 2;
/*  560 */         ArrayUtil.writeLong(buffer, offset, dynam.getEncodingSchema().getId());
/*  561 */         offset += 8;
/*  562 */         ArrayUtil.writeLong(buffer, offset, dynam.map.multiChoice);
/*  563 */         offset += 8;
/*  564 */         dynam.toByteArray(buffer, offset);
/*  565 */         return offset + dlen;
/*      */     } 
/*  567 */     throw Assert.failureError();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int toByteArray(byte[] buffer, int offset) {
/*  581 */     if (this.contents != null) {
/*      */       
/*  583 */       if (debug.debugIt(16)) {
/*  584 */         debug.debug(-153415734321212L, "toByteArray", "Already assembled: tableOffset=" + this.tableOffset + ",offset=" + offset + ",length=" + this.length + ",buffer.length=" + buffer.length);
/*      */       }
/*  586 */       System.arraycopy(this.contents, this.tableOffset, buffer, offset, this.length);
/*  587 */       return this.sksl;
/*      */     } 
/*  589 */     int tableOffset = offset;
/*  590 */     offset += this.map.offsetsNeeded * 4;
/*  591 */     int dataOffset = offset;
/*  592 */     int[] oTable = new int[this.map.offsetsNeeded];
/*      */     
/*  594 */     int oTableIndex = 0;
/*  595 */     int sksl = offset - tableOffset; int i;
/*  596 */     for (i = 0; i < this.map.fields.length; i++) {
/*  597 */       if (this.map.fields[i] != null) {
/*  598 */         Object val = this.cache[i];
/*  599 */         if (val == null) {
/*  600 */           uninit(i);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  606 */         offset = writeObject(val, (this.schema.fields[i]).field.getTypeCode(), buffer, offset);
/*      */         
/*  608 */         if ((this.map.fields[i]).length == -1 && oTableIndex < oTable.length) {
/*  609 */           oTable[oTableIndex++] = offset - dataOffset;
/*      */         }
/*      */         
/*  612 */         if (i == this.map.lastH) {
/*  613 */           sksl = offset - tableOffset;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  619 */     this.contents = buffer;
/*  620 */     this.tableOffset = tableOffset;
/*  621 */     this.length = offset - tableOffset;
/*  622 */     this.sksl = sksl;
/*  623 */     this.oTable = oTable;
/*  624 */     this.dataOffset = dataOffset;
/*  625 */     this.choiceCache = null;
/*  626 */     this.changed = false;
/*  627 */     this.cryptoDirty = false;
/*      */     
/*  629 */     for (i = 0; i < oTable.length; i++) {
/*  630 */       ArrayUtil.writeInt(buffer, tableOffset, oTable[i]);
/*  631 */       tableOffset += 4;
/*      */     } 
/*  633 */     return sksl;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Schema getSchema() {
/*  639 */     return getInternalSchema();
/*      */   }
/*      */   
/*      */   public Schema getEncodingSchema() {
/*  643 */     return getInternalSchema();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Schema getInternalSchema() {
/*  649 */     return (this.schema.variants[0]).schema;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static IllegalArgumentException badIndex(int index) {
/*  655 */     return new IllegalArgumentException(ExceptionBuilder.buildReasonString(906945829, new Object[] { Integer.valueOf(index) }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SchemaViolationException schemaViolation(byte requested, int index) {
/*  662 */     byte actual = (this.schema.fields[index]).field.getTypeCode();
/*  663 */     return new SchemaViolationException(ExceptionBuilder.buildReasonString(-1037450017, new Object[] { typeNames[requested - -4], typeNames[actual - -4] }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkSchema(byte requested, int index) {
/*  670 */     if (requested != (this.schema.fields[index]).field.getTypeCode()) {
/*  671 */       schemaViolation(requested, index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void uninit(int index) {
/*  678 */     String name = this.schema.fields[index].getFullName(true);
/*  679 */     throw new UninitializedAccessException(ExceptionBuilder.buildReasonString(-1987922836, new Object[] { name }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getAbsoluteOffset(int index) {
/*  686 */     MessageMap.Remap remap = this.map.fields[index];
/*  687 */     if (remap == null) {
/*  688 */       uninit(index);
/*      */     }
/*  690 */     int varOffset = remap.offsetIndex;
/*  691 */     varOffset = (varOffset < 0) ? 0 : this.oTable[varOffset];
/*  692 */     return this.dataOffset + varOffset + remap.fixedIncr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(int index) {
/*      */     try {
/*  700 */       if (this.cache[index] != null) {
/*  701 */         return ((BooleanValue)this.cache[index]).booleanValue();
/*      */       }
/*      */     }
/*  704 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  706 */       throw badIndex(index);
/*      */     }
/*  708 */     catch (ClassCastException e) {
/*      */       
/*  710 */       throw schemaViolation((byte)1, index);
/*      */     } 
/*      */     
/*  713 */     checkSchema((byte)1, index);
/*  714 */     if (this.contents == null) {
/*  715 */       uninit(index);
/*      */     }
/*  717 */     int offset = getAbsoluteOffset(index);
/*  718 */     boolean ans = (this.contents[offset] != 0);
/*  719 */     this.cache[index] = BooleanValue.valueOf(ans);
/*  720 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByte(int index) {
/*      */     try {
/*  728 */       if (this.cache[index] != null) {
/*  729 */         return ((NumericValue)this.cache[index]).byteValue();
/*      */       }
/*      */     }
/*  732 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  734 */       throw badIndex(index);
/*      */     }
/*  736 */     catch (ClassCastException e) {
/*      */       
/*  738 */       throw schemaViolation((byte)2, index);
/*      */     } 
/*      */     
/*  741 */     checkSchema((byte)2, index);
/*  742 */     if (this.contents == null) {
/*  743 */       uninit(index);
/*      */     }
/*  745 */     int offset = getAbsoluteOffset(index);
/*  746 */     byte ans = this.contents[offset];
/*  747 */     this.cache[index] = new NumericValue(ans);
/*  748 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShort(int index) {
/*      */     try {
/*  756 */       if (this.cache[index] != null) {
/*  757 */         return ((NumericValue)this.cache[index]).shortValue();
/*      */       }
/*      */     }
/*  760 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  762 */       throw badIndex(index);
/*      */     }
/*  764 */     catch (ClassCastException e) {
/*      */       
/*  766 */       throw schemaViolation((byte)3, index);
/*      */     } 
/*      */     
/*  769 */     checkSchema((byte)3, index);
/*  770 */     if (this.contents == null) {
/*  771 */       uninit(index);
/*      */     }
/*  773 */     int offset = getAbsoluteOffset(index);
/*  774 */     short ans = ArrayUtil.readShort(this.contents, offset);
/*  775 */     this.cache[index] = new NumericValue(ans);
/*  776 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getChar(int index) {
/*      */     try {
/*  784 */       if (this.cache[index] != null) {
/*  785 */         return ((Character)this.cache[index]).charValue();
/*      */       }
/*      */     }
/*  788 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  790 */       throw badIndex(index);
/*      */     }
/*  792 */     catch (ClassCastException e) {
/*      */       
/*  794 */       throw schemaViolation((byte)4, index);
/*      */     } 
/*      */     
/*  797 */     checkSchema((byte)4, index);
/*  798 */     if (this.contents == null) {
/*  799 */       uninit(index);
/*      */     }
/*  801 */     int offset = getAbsoluteOffset(index);
/*  802 */     char ans = (char)ArrayUtil.readShort(this.contents, offset);
/*  803 */     this.cache[index] = Character.valueOf(ans);
/*  804 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(int index) {
/*      */     try {
/*  812 */       if (this.cache[index] != null) {
/*  813 */         return ((NumericValue)this.cache[index]).intValue();
/*      */       }
/*      */     }
/*  816 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  818 */       throw badIndex(index);
/*      */     }
/*  820 */     catch (ClassCastException e) {
/*      */       
/*  822 */       throw schemaViolation((byte)5, index);
/*      */     } 
/*      */     
/*  825 */     checkSchema((byte)5, index);
/*  826 */     if (this.contents == null) {
/*  827 */       uninit(index);
/*      */     }
/*  829 */     int offset = getAbsoluteOffset(index);
/*  830 */     int ans = ArrayUtil.readInt(this.contents, offset);
/*  831 */     this.cache[index] = new NumericValue(ans);
/*  832 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(int index) {
/*      */     try {
/*  840 */       if (this.cache[index] != null) {
/*  841 */         return ((NumericValue)this.cache[index]).longValue();
/*      */       }
/*      */     }
/*  844 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  846 */       throw badIndex(index);
/*      */     }
/*  848 */     catch (ClassCastException e) {
/*      */       
/*  850 */       throw schemaViolation((byte)6, index);
/*      */     } 
/*      */     
/*  853 */     checkSchema((byte)6, index);
/*  854 */     if (this.contents == null) {
/*  855 */       uninit(index);
/*      */     }
/*  857 */     int offset = getAbsoluteOffset(index);
/*  858 */     long ans = ArrayUtil.readLong(this.contents, offset);
/*  859 */     this.cache[index] = new NumericValue(ans, true);
/*  860 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(int index) {
/*      */     try {
/*  868 */       if (this.cache[index] != null) {
/*  869 */         return ((NumericValue)this.cache[index]).floatValue();
/*      */       }
/*      */     }
/*  872 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  874 */       throw badIndex(index);
/*      */     }
/*  876 */     catch (ClassCastException e) {
/*      */       
/*  878 */       throw schemaViolation((byte)7, index);
/*      */     } 
/*      */     
/*  881 */     checkSchema((byte)7, index);
/*  882 */     if (this.contents == null) {
/*  883 */       uninit(index);
/*      */     }
/*  885 */     int offset = getAbsoluteOffset(index);
/*  886 */     float ans = Float.intBitsToFloat(ArrayUtil.readInt(this.contents, offset));
/*  887 */     this.cache[index] = new NumericValue(ans);
/*  888 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDouble(int index) {
/*      */     try {
/*  896 */       if (this.cache[index] != null) {
/*  897 */         return ((NumericValue)this.cache[index]).doubleValue();
/*      */       }
/*      */     }
/*  900 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  902 */       throw badIndex(index);
/*      */     }
/*  904 */     catch (ClassCastException e) {
/*      */       
/*  906 */       throw schemaViolation((byte)8, index);
/*      */     } 
/*      */     
/*  909 */     checkSchema((byte)8, index);
/*  910 */     if (this.contents == null) {
/*  911 */       uninit(index);
/*      */     }
/*  913 */     int offset = getAbsoluteOffset(index);
/*  914 */     double ans = Double.longBitsToDouble(ArrayUtil.readLong(this.contents, offset));
/*  915 */     this.cache[index] = new NumericValue(ans);
/*  916 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(int index) {
/*      */     Object o;
/*      */     try {
/*  924 */       o = this.cache[index];
/*      */     }
/*  926 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  928 */       throw badIndex(index);
/*      */     } 
/*  930 */     if (o == nullIndicator) {
/*  931 */       return null;
/*      */     }
/*  933 */     if (o instanceof String) {
/*  934 */       return (String)o;
/*      */     }
/*  936 */     if (o != null) {
/*  937 */       throw schemaViolation((byte)-2, index);
/*      */     }
/*      */     
/*  940 */     checkSchema((byte)-2, index);
/*  941 */     if (this.contents == null) {
/*  942 */       uninit(index);
/*      */     }
/*  944 */     int offset = getAbsoluteOffset(index);
/*  945 */     int len = ArrayUtil.readInt(this.contents, offset);
/*  946 */     if (len == -1) {
/*  947 */       this.cache[index] = nullIndicator;
/*  948 */       return null;
/*      */     } 
/*  950 */     offset += 4;
/*      */     
/*  952 */     if (debug.debugIt(16)) {
/*  953 */       debug.debug(-153415734321212L, "getString", "len=" + len + ",offset=" + offset + ",dataOffset=" + this.dataOffset + ",length=" + this.length);
/*      */     }
/*  955 */     Assert.condition((len + offset <= this.tableOffset + this.length));
/*  956 */     String ans = readString(this.contents, offset, len);
/*  957 */     this.cache[index] = ans;
/*  958 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] getBytes(int index, byte typecode) {
/*      */     Object o;
/*      */     try {
/*  967 */       o = this.cache[index];
/*      */     }
/*  969 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/*  971 */       throw badIndex(index);
/*      */     } 
/*  973 */     if (o == nullIndicator) {
/*  974 */       return null;
/*      */     }
/*  976 */     if (o instanceof byte[]) {
/*  977 */       return (byte[])o;
/*      */     }
/*  979 */     if (o != null) {
/*  980 */       throw schemaViolation(typecode, index);
/*      */     }
/*      */     
/*  983 */     checkSchema(typecode, index);
/*  984 */     if (this.contents == null) {
/*  985 */       uninit(index);
/*      */     }
/*  987 */     int offset = getAbsoluteOffset(index);
/*  988 */     int len = ArrayUtil.readInt(this.contents, offset);
/*  989 */     if (len == -1) {
/*  990 */       this.cache[index] = nullIndicator;
/*  991 */       return null;
/*      */     } 
/*  993 */     offset += 4;
/*      */     
/*  995 */     Assert.condition((len + offset <= this.tableOffset + this.length));
/*  996 */     byte[] ans = new byte[len];
/*  997 */     System.arraycopy(this.contents, offset, ans, 0, len);
/*  998 */     this.cache[index] = ans;
/*  999 */     return ans;
/*      */   }
/*      */ 
/*      */   
/*      */   private Serializable deserialize(byte[] obj) throws IOException, ClassNotFoundException {
/* 1004 */     if (obj == null) {
/* 1005 */       return (Serializable)obj;
/*      */     }
/* 1007 */     ByteArrayInputStream str = new ByteArrayInputStream(obj);
/*      */ 
/*      */ 
/*      */     
/* 1011 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*      */     
/* 1013 */     ObjectInputStream objIn = new ExtendedObjectInputStream(str, cl);
/*      */     
/* 1015 */     return (Serializable)objIn.readObject();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Serializable getObject(int index) throws IOException, ClassNotFoundException {
/*      */     Serializable ans;
/*      */     try {
/* 1023 */       ans = (Serializable)this.cache2[index];
/*      */     }
/* 1025 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1027 */       throw badIndex(index);
/*      */     }
/* 1029 */     catch (ClassCastException e) {
/*      */       
/* 1031 */       throw schemaViolation((byte)-3, index);
/*      */     } 
/* 1033 */     if (ans == null)
/*      */     {
/* 1035 */       ans = deserialize(getEncodedObject(index));
/*      */     }
/* 1037 */     this.cache2[index] = ans;
/* 1038 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getEncodedObject(int index) {
/* 1044 */     return getBytes(index, (byte)-3);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getByteArray(int index) {
/* 1050 */     return getBytes(index, (byte)-4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getValue(int index) throws IOException, ClassNotFoundException {
/* 1056 */     Object o = getInternalValue(index);
/* 1057 */     if (o instanceof NumericValue) {
/* 1058 */       return ((NumericValue)o).toStandardWrapper();
/*      */     }
/* 1060 */     if (o instanceof BooleanValue) {
/* 1061 */       return ((BooleanValue)o).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
/*      */     }
/* 1063 */     if (o instanceof byte[] && (this.schema.fields[index]).field.getTypeCode() == -3) {
/* 1064 */       this.cache2[index] = o = deserialize((byte[])o);
/*      */     }
/* 1066 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getInternalValue(int index) {
/*      */     Object o;
/*      */     try {
/* 1077 */       o = this.cache[index];
/*      */     }
/* 1079 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1081 */       throw badIndex(index);
/*      */     } 
/* 1083 */     if (o == null) {
/*      */       
/* 1085 */       if (this.contents == null) {
/* 1086 */         uninit(index);
/*      */       }
/* 1088 */       int offset = getAbsoluteOffset(index);
/* 1089 */       byte typecode = (this.schema.fields[index]).field.getTypeCode();
/* 1090 */       FlatSchema forTable = (this.schema.fields[index]).contents;
/* 1091 */       o = readObject(typecode, this.contents, offset, this.tableOffset + this.length, forTable);
/* 1092 */       this.cache[index] = o;
/*      */     } 
/* 1094 */     if (o == nullIndicator || o == nullTable) {
/* 1095 */       return null;
/*      */     }
/* 1097 */     return o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StandardMessageDataHandle getSubhandle(FlatSchema sfs, byte[] contents, int offset, int length) {
/* 1105 */     StandardMessageDataHandle ans = new StandardMessageDataHandle(sfs, (this.master == null) ? (StandardMessageHandle)this : this.master);
/*      */     
/* 1107 */     long multiChoice = ArrayUtil.readLong(contents, offset);
/* 1108 */     ans.oldMessage(contents, offset + 8, length - 8, -1, multiChoice);
/* 1109 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StandardMessageDataHandle readTableRow(byte[] contents, int offset, int lenCheck, FlatSchema sfs) {
/* 1116 */     int len = ArrayUtil.readInt(contents, offset);
/* 1117 */     Assert.condition((len + offset <= lenCheck));
/* 1118 */     offset += 4;
/* 1119 */     if (len == 8)
/*      */     {
/* 1121 */       if (ArrayUtil.readLong(contents, offset) == 0L) {
/* 1122 */         return nullTable;
/*      */       }
/*      */     }
/*      */     
/* 1126 */     return getSubhandle(sfs, contents, offset, len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MessageDataHandle readDynamic(byte[] contents, int offset, int lenCheck, Schema access) {
/* 1133 */     int len = ArrayUtil.readInt(contents, offset);
/* 1134 */     Assert.condition((len >= 0 && len + offset <= lenCheck));
/* 1135 */     offset += 6;
/* 1136 */     long schemaId = ArrayUtil.readLong(contents, offset);
/* 1137 */     offset += 8;
/* 1138 */     SchemaHash.Entry e = SchemaRegistry.retrieve(schemaId);
/* 1139 */     if (e.encoding == null) {
/* 1140 */       throw new SchemaViolationException(ExceptionBuilder.buildReasonString(1092648792, null));
/*      */     }
/* 1142 */     FlatSchema sfs = e.encoding.getFlatSchema();
/* 1143 */     MessageDataHandle ans = getSubhandle(sfs, contents, offset, len - 10);
/* 1144 */     if (access == null || schemaId == access.getId()) {
/* 1145 */       return ans;
/*      */     }
/*      */     
/* 1148 */     return SchemaRegistry.getCompatibility(access, ans, e);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle getDynamic(int index, Schema schema) {
/*      */     try {
/* 1157 */       if (this.cache[index] != null) {
/* 1158 */         return (MessageDataHandle)this.cache[index];
/*      */       }
/*      */     }
/* 1161 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1163 */       throw badIndex(index);
/*      */     }
/* 1165 */     catch (ClassCastException e) {
/*      */ 
/*      */ 
/*      */       
/* 1169 */       throw schemaViolation((byte)-1, index);
/*      */     } 
/*      */     
/* 1172 */     checkSchema((byte)-1, index);
/* 1173 */     if (this.contents == null) {
/* 1174 */       uninit(index);
/*      */     }
/* 1176 */     int offset = getAbsoluteOffset(index);
/* 1177 */     MessageDataHandle ans = readDynamic(this.contents, offset, this.tableOffset + this.length, (Schema)schema);
/*      */ 
/*      */     
/* 1180 */     this.cache[index] = ans;
/* 1181 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StandardMessageDataHandle getTableRow(int index) {
/*      */     StandardMessageDataHandle ans;
/*      */     try {
/* 1191 */       ans = (StandardMessageDataHandle)this.cache[index];
/*      */     }
/* 1193 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1195 */       throw badIndex(index);
/*      */     }
/* 1197 */     catch (ClassCastException e) {
/*      */ 
/*      */ 
/*      */       
/* 1201 */       throw schemaViolation((byte)0, index);
/*      */     } 
/* 1203 */     if (ans == null) {
/*      */       
/* 1205 */       checkSchema((byte)0, index);
/* 1206 */       if (this.contents == null) {
/* 1207 */         uninit(index);
/*      */       }
/* 1209 */       int offset = getAbsoluteOffset(index);
/* 1210 */       ans = readTableRow(this.contents, offset, this.tableOffset + this.length, (this.schema.fields[index]).contents);
/* 1211 */       this.cache[index] = ans;
/*      */     } 
/* 1213 */     if (ans == nullTable)
/*      */     {
/* 1215 */       return null;
/*      */     }
/*      */     
/* 1218 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle getTableRow(int index, int rowIndex) {
/* 1225 */     if (rowIndex == 0) {
/* 1226 */       return getTableRow(index);
/*      */     }
/* 1228 */     if (rowIndex < 0) {
/* 1229 */       return null;
/*      */     }
/* 1231 */     MessageBodyHandle[] table = getTable(index);
/* 1232 */     if (rowIndex >= table.length) {
/* 1233 */       return null;
/*      */     }
/* 1235 */     return table[rowIndex];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle[] getTable(int index) {
/* 1243 */     StandardMessageDataHandle row = getTableRow(index);
/* 1244 */     if (this.cache2[index] != null) {
/* 1245 */       return (MessageBodyHandle[])this.cache2[index];
/*      */     }
/* 1247 */     int nextRow = (this.schema.fields[index]).contents.fields.length - 1;
/* 1248 */     int n = 0;
/* 1249 */     for (; row != null; row = row.getTableRow(nextRow)) {
/* 1250 */       n++;
/*      */     }
/* 1252 */     MessageBodyHandle[] ans = new MessageBodyHandle[n];
/* 1253 */     n = 0;
/* 1254 */     for (row = getTableRow(index); row != null; row = row.getTableRow(nextRow)) {
/* 1255 */       ans[n++] = row;
/*      */     }
/* 1257 */     this.cache2[index] = ans;
/* 1258 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle getSuccessor() {
/* 1264 */     if (this.schema.isTable) {
/* 1265 */       return getTableRow(this.schema.fields.length - 1);
/*      */     }
/*      */     
/* 1268 */     throw new SchemaViolationException("");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChoice(int index) {
/* 1275 */     int varIndex = index - this.schema.fields.length;
/*      */     try {
/* 1277 */       if (this.map != null) {
/* 1278 */         return this.map.choiceCodes[varIndex];
/*      */       }
/* 1280 */       if (this.choiceCache == null) {
/* 1281 */         return -1;
/*      */       }
/*      */       
/* 1284 */       return this.choiceCache[varIndex];
/*      */     
/*      */     }
/* 1287 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1289 */       throw badIndex(index);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPresent(int index) {
/* 1296 */     if (index < 0 || index >= this.schema.fields.length + this.schema.variants.length) {
/* 1297 */       throw badIndex(index);
/*      */     }
/* 1299 */     if (index < this.schema.fields.length) {
/*      */       
/* 1301 */       if (this.cache[index] != null) {
/* 1302 */         return true;
/*      */       }
/*      */       
/* 1305 */       if (this.contents == null) {
/* 1306 */         return false;
/*      */       }
/*      */       
/* 1309 */       return (this.map.fields[index] != null);
/*      */     } 
/*      */ 
/*      */     
/* 1313 */     index -= this.schema.fields.length;
/* 1314 */     if (this.contents == null)
/*      */     {
/* 1316 */       return (this.choiceCache != null && this.choiceCache[index] != -1);
/*      */     }
/*      */ 
/*      */     
/* 1320 */     return (this.map.choiceCodes[index] != -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void unassemble() {
/* 1330 */     for (int i = 0; i < this.map.fields.length; i++) {
/* 1331 */       if (this.map.fields[i] != null && this.cache[i] == null) {
/* 1332 */         int offset = getAbsoluteOffset(i);
/* 1333 */         byte typecode = (this.schema.fields[i]).field.getTypeCode();
/* 1334 */         FlatSchema forTable = (this.schema.fields[i]).contents;
/* 1335 */         this.cache[i] = readObject(typecode, this.contents, offset, this.tableOffset + this.length, forTable);
/*      */       } 
/*      */     } 
/*      */     
/* 1339 */     this.contents = null;
/* 1340 */     this.oTable = null;
/* 1341 */     makeChoiceCache();
/* 1342 */     this.map = null;
/* 1343 */     this.changed = this.cryptoDirty = true;
/* 1344 */     if (this.master != null) {
/* 1345 */       this.master.invalidate();
/*      */     } else {
/*      */       
/* 1348 */       ((StandardMessageHandle)this).invalidate();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setChoiceInternal(int varIndex, int choiceIndex) {
/* 1355 */     if (this.choiceCache[varIndex] == choiceIndex) {
/*      */       return;
/*      */     }
/*      */     
/* 1359 */     if (debug.debugIt(16)) {
/* 1360 */       String varName = this.schema.variants[varIndex].getFullName(true);
/* 1361 */       if (varName == null || varName.length() == 0) {
/* 1362 */         varName = String.valueOf(varIndex);
/*      */       }
/* 1364 */       String caseName = (this.schema.variants[varIndex]).schema.getTupleDef(choiceIndex).getName();
/* 1365 */       if (caseName == null || caseName.length() == 0) {
/* 1366 */         caseName = String.valueOf(choiceIndex);
/*      */       }
/* 1368 */       debug.debug(-153415734321212L, "setChoiceInternal", varName, caseName);
/*      */     } 
/*      */     
/* 1371 */     this.choiceCache[varIndex] = choiceIndex;
/* 1372 */     FlatSchema.Variant variant = this.schema.variants[varIndex];
/* 1373 */     if (variant.dominator != null) {
/* 1374 */       setChoiceInternal(variant.dominator.index, variant.domCase);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean assembledForField(int index) {
/* 1384 */     if (debug.debugIt(16)) {
/* 1385 */       String name = this.schema.fields[index].getFullName(true);
/* 1386 */       debug.debug(-153415734321212L, "assembledForField", name);
/*      */     } 
/* 1388 */     this.changed = true;
/* 1389 */     if (this.contents != null) {
/*      */       
/* 1391 */       if (this.map.fields[index] != null) {
/*      */ 
/*      */         
/* 1394 */         if (index > this.map.lastH) {
/* 1395 */           this.cryptoDirty = true;
/*      */         }
/* 1397 */         return true;
/*      */       } 
/*      */       
/* 1400 */       unassemble();
/*      */     } else {
/*      */       
/* 1403 */       if (this.choiceCache == null) {
/* 1404 */         makeChoiceCache();
/*      */       }
/* 1406 */       this.map = null;
/*      */     } 
/*      */     
/* 1409 */     FlatSchema.Field field = this.schema.fields[index];
/* 1410 */     setChoiceInternal(field.dominator.index, field.domCase);
/* 1411 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(int index, boolean val) {
/*      */     try {
/* 1418 */       checkSchema((byte)1, index);
/* 1419 */       this.cache[index] = BooleanValue.valueOf(val);
/*      */     }
/* 1421 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1423 */       throw badIndex(index);
/*      */     } 
/* 1425 */     if (assembledForField(index)) {
/* 1426 */       int offset = getAbsoluteOffset(index);
/* 1427 */       this.contents[offset] = val ? 1 : 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByte(int index, byte val) {
/*      */     try {
/* 1435 */       checkSchema((byte)2, index);
/* 1436 */       this.cache[index] = new NumericValue(val);
/*      */     }
/* 1438 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1440 */       throw badIndex(index);
/*      */     } 
/* 1442 */     if (assembledForField(index)) {
/* 1443 */       int offset = getAbsoluteOffset(index);
/* 1444 */       this.contents[offset] = val;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShort(int index, short val) {
/*      */     try {
/* 1452 */       checkSchema((byte)3, index);
/* 1453 */       this.cache[index] = new NumericValue(val);
/*      */     }
/* 1455 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1457 */       throw badIndex(index);
/*      */     } 
/* 1459 */     if (assembledForField(index)) {
/* 1460 */       int offset = getAbsoluteOffset(index);
/* 1461 */       ArrayUtil.writeShort(this.contents, offset, val);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChar(int index, char val) {
/*      */     try {
/* 1469 */       checkSchema((byte)4, index);
/* 1470 */       this.cache[index] = Character.valueOf(val);
/*      */     }
/* 1472 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1474 */       throw badIndex(index);
/*      */     } 
/* 1476 */     if (assembledForField(index)) {
/* 1477 */       int offset = getAbsoluteOffset(index);
/* 1478 */       ArrayUtil.writeShort(this.contents, offset, (short)val);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInt(int index, int val) {
/*      */     try {
/* 1486 */       checkSchema((byte)5, index);
/* 1487 */       this.cache[index] = new NumericValue(val);
/*      */     }
/* 1489 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1491 */       throw badIndex(index);
/*      */     } 
/* 1493 */     if (assembledForField(index)) {
/* 1494 */       int offset = getAbsoluteOffset(index);
/* 1495 */       ArrayUtil.writeInt(this.contents, offset, val);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(int index, long val) {
/* 1502 */     checkSchema((byte)6, index);
/*      */     try {
/* 1504 */       this.cache[index] = new NumericValue(val, true);
/*      */     }
/* 1506 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1508 */       throw badIndex(index);
/*      */     } 
/* 1510 */     if (assembledForField(index)) {
/* 1511 */       int offset = getAbsoluteOffset(index);
/* 1512 */       ArrayUtil.writeLong(this.contents, offset, val);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(int index, float val) {
/*      */     try {
/* 1520 */       checkSchema((byte)7, index);
/* 1521 */       this.cache[index] = new NumericValue(val);
/*      */     }
/* 1523 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1525 */       throw badIndex(index);
/*      */     } 
/* 1527 */     if (assembledForField(index)) {
/* 1528 */       int offset = getAbsoluteOffset(index);
/* 1529 */       ArrayUtil.writeInt(this.contents, offset, Float.floatToIntBits(val));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDouble(int index, double val) {
/*      */     try {
/* 1537 */       checkSchema((byte)8, index);
/* 1538 */       this.cache[index] = new NumericValue(val);
/*      */     }
/* 1540 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1542 */       throw badIndex(index);
/*      */     } 
/* 1544 */     if (assembledForField(index)) {
/* 1545 */       int offset = getAbsoluteOffset(index);
/* 1546 */       ArrayUtil.writeLong(this.contents, offset, Double.doubleToLongBits(val));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readjust(int index, int offset, int len) {
/* 1554 */     int oldlen = ArrayUtil.readInt(this.contents, offset);
/* 1555 */     if (len == oldlen) {
/* 1556 */       return offset;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1561 */     int oInx = (this.map.fields[index]).offsetIndex + 1;
/*      */     
/* 1563 */     while (oInx < this.oTable.length) {
/* 1564 */       this.oTable[oInx++] = this.oTable[oInx++] + len - oldlen;
/*      */     }
/*      */     
/* 1567 */     if (index <= this.map.lastH) {
/* 1568 */       this.sksl += len - oldlen;
/*      */     }
/*      */     
/* 1571 */     int balance = this.tableOffset + this.length - offset - 4 - oldlen;
/*      */     
/* 1573 */     byte[] oldcontents = this.contents;
/* 1574 */     int oldDataOffset = this.dataOffset;
/* 1575 */     int oldTableOffset = this.tableOffset;
/* 1576 */     this.length += len - oldlen;
/* 1577 */     this.contents = new byte[this.length];
/* 1578 */     this.tableOffset = this.dataOffset = 0;
/*      */     
/* 1580 */     for (int i = 0; i < this.oTable.length; i++) {
/* 1581 */       ArrayUtil.writeInt(this.contents, this.dataOffset, this.oTable[i]);
/* 1582 */       this.dataOffset += 4;
/*      */     } 
/*      */     
/* 1585 */     System.arraycopy(oldcontents, oldDataOffset, this.contents, this.dataOffset, offset - oldDataOffset);
/*      */     
/* 1587 */     if (balance > 0) {
/* 1588 */       System.arraycopy(oldcontents, offset + 4 + oldlen, this.contents, this.length - balance, balance);
/*      */     }
/* 1590 */     return offset - oldTableOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setString(int index, String val) {
/*      */     try {
/* 1597 */       checkSchema((byte)-2, index);
/* 1598 */       this.cache[index] = (val == null) ? nullIndicator : val;
/*      */     }
/* 1600 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1602 */       throw badIndex(index);
/*      */     } 
/* 1604 */     if (assembledForField(index)) {
/* 1605 */       int offset = getAbsoluteOffset(index);
/* 1606 */       if (val == null) {
/* 1607 */         offset = readjust(index, offset, 0);
/* 1608 */         ArrayUtil.writeInt(this.contents, offset, -1);
/*      */       } else {
/*      */         
/* 1611 */         int utflen = convertString(val);
/* 1612 */         offset = readjust(index, offset, utflen);
/* 1613 */         ArrayUtil.writeInt(this.contents, offset, utflen);
/* 1614 */         offset += 4;
/* 1615 */         UTF.charsToUTF(this.charbuf, 0, this.contents, offset, val.length());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setBytes(int index, byte[] val, byte typecode) {
/*      */     try {
/* 1624 */       checkSchema(typecode, index);
/* 1625 */       this.cache[index] = (val == null) ? nullIndicator : val;
/*      */     }
/* 1627 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1629 */       throw badIndex(index);
/*      */     } 
/* 1631 */     if (assembledForField(index)) {
/* 1632 */       int offset = getAbsoluteOffset(index);
/* 1633 */       if (val == null) {
/* 1634 */         offset = readjust(index, offset, 0);
/* 1635 */         ArrayUtil.writeInt(this.contents, offset, -1);
/*      */       } else {
/*      */         
/* 1638 */         offset = readjust(index, offset, val.length);
/* 1639 */         ArrayUtil.writeInt(this.contents, offset, val.length);
/* 1640 */         offset += 4;
/* 1641 */         System.arraycopy(val, 0, this.contents, offset, val.length);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncodedObject(int index, byte[] val) {
/* 1649 */     setBytes(index, val, (byte)-3);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] serialize(Serializable obj) throws IOException {
/* 1655 */     if (obj == null) {
/* 1656 */       return null;
/*      */     }
/* 1658 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 1659 */     (new ObjectOutputStream(baos)).writeObject(obj);
/* 1660 */     return baos.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(int index, Serializable val) throws IOException {
/* 1666 */     setEncodedObject(index, serialize(val));
/* 1667 */     this.cache2[index] = val;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByteArray(int index, byte[] val) {
/* 1673 */     setBytes(index, val, (byte)-4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValue(int index, Object val) throws IOException {
/*      */     byte typecode;
/*      */     try {
/* 1682 */       typecode = (this.schema.fields[index]).field.getTypeCode();
/*      */     }
/* 1684 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1686 */       throw badIndex(index);
/*      */     } 
/*      */     
/* 1689 */     if (val != null) {
/* 1690 */       Class<?> kind = valueTypes[typecode - -4];
/* 1691 */       if (kind != null) {
/*      */         
/* 1693 */         if (kind != val.getClass()) {
/* 1694 */           throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-82355140, new Object[] { val.getClass() }));
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1699 */       else if (!(val instanceof Serializable)) {
/* 1700 */         throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-82355140, new Object[] { val.getClass() }));
/*      */       }
/*      */     
/*      */     }
/* 1704 */     else if (typecode == -2 || typecode == -3 || typecode == -4) {
/* 1705 */       val = nullIndicator;
/*      */     }
/* 1707 */     else if (typecode != 0) {
/*      */       
/* 1709 */       throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-1383104902, new Object[] { typeNames[typecode - -4], "null" }));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1714 */     if (typecode == 0) {
/* 1715 */       setTable(index, (MessageBodyHandle[])val);
/*      */       
/*      */       return;
/*      */     } 
/* 1719 */     if (val instanceof Number) {
/* 1720 */       val = new NumericValue((Number)val);
/*      */     }
/* 1722 */     else if (val instanceof Boolean) {
/* 1723 */       val = BooleanValue.valueOf(((Boolean)val).booleanValue());
/*      */     } 
/* 1725 */     if (typecode == -3) {
/* 1726 */       this.cache2[index] = val;
/* 1727 */       this.cache[index] = serialize((Serializable)val);
/*      */     } else {
/*      */       
/* 1730 */       this.cache[index] = val;
/*      */     } 
/* 1732 */     if (assembledForField(index)) {
/* 1733 */       int offset = getAbsoluteOffset(index);
/* 1734 */       if (typecode < 1) {
/* 1735 */         int len = getLength(val);
/* 1736 */         offset = readjust(index, offset, len);
/*      */       } 
/* 1738 */       writeObject(val, typecode, this.contents, offset);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle newDynamic(Schema schema) {
/* 1745 */     FlatSchema sfs = ((Schema)schema).getFlatSchema();
/* 1746 */     return new StandardMessageDataHandle(sfs, (this.master == null) ? (StandardMessageHandle)this : this.master);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamic(int index, MessageBodyHandle val) {
/*      */     try {
/* 1753 */       checkSchema((byte)-1, index);
/* 1754 */       this.cache[index] = val;
/*      */     }
/* 1756 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1758 */       throw badIndex(index);
/*      */     } 
/* 1760 */     SchemaRegistry.register(((MessageDataHandle)val).getEncodingSchema());
/* 1761 */     if (assembledForField(index)) {
/* 1762 */       int offset = getAbsoluteOffset(index);
/* 1763 */       StandardMessageDataHandle dval = (StandardMessageDataHandle)val;
/* 1764 */       int dlen = dval.getEncodedLength() + 18;
/* 1765 */       offset = readjust(index, offset, dlen);
/* 1766 */       ArrayUtil.writeInt(this.contents, offset, dlen);
/* 1767 */       offset += 4;
/* 1768 */       ArrayUtil.writeShort(this.contents, offset, (short)1);
/* 1769 */       offset += 2;
/* 1770 */       ArrayUtil.writeLong(this.contents, offset, dval.getEncodingSchema().getId());
/* 1771 */       offset += 8;
/* 1772 */       ArrayUtil.writeLong(this.contents, offset, dval.map.multiChoice);
/* 1773 */       offset += 8;
/* 1774 */       dval.toByteArray(this.contents, offset);
/*      */     } 
/* 1776 */     if (this.master != null) {
/* 1777 */       this.master.invalidate();
/*      */     } else {
/*      */       
/* 1780 */       ((StandardMessageHandle)this).invalidate();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle newTableRow(int index) {
/*      */     try {
/* 1788 */       FlatSchema tschema = (this.schema.fields[index]).contents;
/* 1789 */       if (tschema == null) {
/* 1790 */         schemaViolation((byte)0, index);
/*      */       }
/* 1792 */       return new StandardMessageDataHandle(tschema, (this.master == null) ? (StandardMessageHandle)this : this.master);
/*      */     }
/* 1794 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1796 */       throw badIndex(index);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setTableRow(int index, StandardMessageDataHandle val) {
/* 1805 */     if (val == null) {
/* 1806 */       val = nullTable;
/*      */     }
/* 1808 */     this.cache[index] = val;
/* 1809 */     if (assembledForField(index)) {
/* 1810 */       int tlen, offset = getAbsoluteOffset(index);
/*      */       
/* 1812 */       if (val == nullTable) {
/* 1813 */         tlen = 8;
/*      */       } else {
/*      */         
/* 1816 */         tlen = val.getEncodedLength() + 8;
/*      */       } 
/* 1818 */       offset = readjust(index, offset, tlen);
/* 1819 */       ArrayUtil.writeInt(this.contents, offset, tlen);
/* 1820 */       offset += 4;
/* 1821 */       if (val == nullTable) {
/* 1822 */         ArrayUtil.writeLong(this.contents, offset, 0L);
/*      */       } else {
/*      */         
/* 1825 */         ArrayUtil.writeLong(this.contents, offset, val.map.multiChoice);
/* 1826 */         val.toByteArray(this.contents, offset + 8);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTable(int index, MessageBodyHandle[] val) {
/*      */     try {
/* 1835 */       checkSchema((byte)0, index);
/*      */     }
/* 1837 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1839 */       throw badIndex(index);
/*      */     } 
/*      */     
/* 1842 */     if (val == null || val.length == 0) {
/* 1843 */       val = null;
/*      */     } else {
/*      */       
/* 1846 */       int nextRow = (this.schema.fields[index]).contents.fields.length - 1;
/* 1847 */       StandardMessageDataHandle next = null;
/* 1848 */       for (int i = val.length - 1; i >= 0; i--) {
/* 1849 */         StandardMessageDataHandle thisRow = (StandardMessageDataHandle)val[i];
/* 1850 */         thisRow.setTableRow(nextRow, next);
/* 1851 */         next = thisRow;
/*      */       } 
/*      */     } 
/* 1854 */     setTableRow(index, (val == null) ? null : (StandardMessageDataHandle)val[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChoice(int index, int choice) {
/* 1860 */     int varIndex = index - this.schema.fields.length;
/*      */     try {
/* 1862 */       if (this.contents != null) {
/*      */ 
/*      */         
/* 1865 */         if (this.map.choiceCodes[varIndex] == choice) {
/*      */           return;
/*      */         }
/*      */         
/* 1869 */         unassemble();
/*      */       
/*      */       }
/* 1872 */       else if (this.choiceCache == null) {
/* 1873 */         makeChoiceCache();
/*      */       } 
/*      */ 
/*      */       
/* 1877 */       setChoiceInternal(varIndex, choice);
/*      */     }
/* 1879 */     catch (ArrayIndexOutOfBoundsException e) {
/*      */       
/* 1881 */       throw badIndex(index);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageMap getMap() {
/* 1888 */     return this.map;
/*      */   }
/*      */   
/*      */   public Object[] getVals() {
/* 1892 */     unassemble();
/* 1893 */     return this.cache;
/*      */   }
/*      */   
/*      */   public boolean isNull(Object toTest) {
/* 1897 */     return (toTest == nullIndicator);
/*      */   }
/*      */   
/*      */   public boolean isEmptyTable() {
/* 1901 */     return (this == nullTable);
/*      */   }
/*      */   
/*      */   public FlatSchema getFlatSchema() {
/* 1905 */     return this.schema;
/*      */   }
/*      */   
/*      */   public String dumpContents() {
/* 1909 */     return Hex.toString(this.contents);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\StandardMessageDataHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */