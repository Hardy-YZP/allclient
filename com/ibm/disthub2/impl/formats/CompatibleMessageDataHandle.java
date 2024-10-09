/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.client.Schema;
/*     */ import com.ibm.disthub2.client.SchemaViolationException;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompatibleMessageDataHandle
/*     */   implements MessageDataHandle, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   CompatibilityMap map;
/*     */   public MessageDataHandle encoding;
/*     */   FlatSchema schema;
/*     */   
/*     */   public CompatibleMessageDataHandle(CompatibilityMap map, MessageDataHandle encoding, FlatSchema schema) {
/*  70 */     this.map = map;
/*  71 */     this.encoding = encoding;
/*  72 */     this.schema = schema;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Schema getInternalSchema() {
/*  81 */     return (this.schema.variants[0]).schema;
/*     */   } public Schema getSchema() {
/*  83 */     return getInternalSchema();
/*     */   } public Schema getEncodingSchema() {
/*  85 */     return this.encoding.getEncodingSchema();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(int index) {
/*     */     try {
/*  92 */       return this.encoding.getBoolean(this.map.indices[index]);
/*  93 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  94 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(int index) {
/*     */     try {
/* 103 */       return this.encoding.getByte(this.map.indices[index]);
/* 104 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 105 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(int index) {
/*     */     try {
/* 114 */       return this.encoding.getShort(this.map.indices[index]);
/* 115 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 116 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getChar(int index) {
/*     */     try {
/* 125 */       return this.encoding.getChar(this.map.indices[index]);
/* 126 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 127 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(int index) {
/*     */     try {
/* 136 */       return this.encoding.getInt(this.map.indices[index]);
/* 137 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 138 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(int index) {
/*     */     try {
/* 147 */       return this.encoding.getLong(this.map.indices[index]);
/* 148 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 149 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(int index) {
/*     */     try {
/* 158 */       return this.encoding.getFloat(this.map.indices[index]);
/* 159 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 160 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(int index) {
/*     */     try {
/* 169 */       return this.encoding.getDouble(this.map.indices[index]);
/* 170 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 171 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(int index) {
/*     */     try {
/* 180 */       return this.encoding.getString(this.map.indices[index]);
/* 181 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 182 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Serializable getObject(int index) throws IOException, ClassNotFoundException {
/*     */     try {
/* 191 */       return this.encoding.getObject(this.map.indices[index]);
/* 192 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 193 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getEncodedObject(int index) {
/*     */     try {
/* 202 */       return this.encoding.getEncodedObject(this.map.indices[index]);
/* 203 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 204 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getByteArray(int index) {
/*     */     try {
/* 213 */       return this.encoding.getByteArray(this.map.indices[index]);
/* 214 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 215 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(int index) throws IOException, ClassNotFoundException {
/* 223 */     if (this.map.tableMaps[index] != null)
/*     */     {
/* 225 */       return getTableRow(index, 0); } 
/*     */     try {
/* 227 */       return this.encoding.getValue(this.map.indices[index]);
/* 228 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 229 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getInternalValue(int index) {
/* 237 */     if (this.map.tableMaps[index] != null)
/*     */     {
/* 239 */       return getTableRow(index, 0); } 
/*     */     try {
/* 241 */       return this.encoding.getInternalValue(this.map.indices[index]);
/* 242 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 243 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBodyHandle getDynamic(int index, Schema schema) {
/*     */     try {
/* 252 */       return this.encoding.getDynamic(this.map.indices[index], schema);
/* 253 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 254 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBodyHandle getTableRow(int index, int rowIndex) {
/*     */     try {
/* 264 */       MessageDataHandle ans = (MessageDataHandle)this.encoding.getTableRow(this.map.indices[index], rowIndex);
/*     */       
/* 266 */       return new CompatibleMessageDataHandle(this.map.tableMaps[index], ans, (this.schema.fields[index]).contents);
/*     */     }
/* 268 */     catch (ArrayIndexOutOfBoundsException e) {
/* 269 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBodyHandle[] getTable(int index) {
/*     */     try {
/* 279 */       MessageBodyHandle[] inner = this.encoding.getTable(this.map.indices[index]);
/* 280 */       MessageDataHandle[] ans = new MessageDataHandle[inner.length];
/* 281 */       for (int i = 0; i < inner.length; i++) {
/* 282 */         ans[i] = new CompatibleMessageDataHandle(this.map.tableMaps[index], (MessageDataHandle)inner[i], (this.schema.fields[index]).contents);
/*     */       }
/*     */       
/* 285 */       return (MessageBodyHandle[])ans;
/* 286 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 287 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBodyHandle getSuccessor() {
/* 295 */     return this.encoding.getSuccessor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChoice(int index) {
/*     */     int realIndex;
/*     */     try {
/* 304 */       realIndex = this.map.indices[index];
/* 305 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 306 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/* 308 */     if (realIndex == -1)
/* 309 */       return 0; 
/* 310 */     int choice = this.encoding.getChoice(realIndex);
/* 311 */     return this.map.getCases[realIndex - this.map.varBias][choice];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPresent(int index) {
/*     */     int realIndex;
/*     */     try {
/* 320 */       realIndex = this.map.indices[index];
/* 321 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 322 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/* 324 */     if (realIndex == -1) {
/* 325 */       return false;
/*     */     }
/* 327 */     return this.encoding.isPresent(realIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(int index, boolean val) {
/*     */     try {
/* 335 */       this.encoding.setBoolean(this.map.indices[index], val);
/* 336 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 337 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByte(int index, byte val) {
/*     */     try {
/* 346 */       this.encoding.setByte(this.map.indices[index], val);
/* 347 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 348 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShort(int index, short val) {
/*     */     try {
/* 357 */       this.encoding.setShort(this.map.indices[index], val);
/* 358 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 359 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChar(int index, char val) {
/*     */     try {
/* 368 */       this.encoding.setChar(this.map.indices[index], val);
/* 369 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 370 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int index, int val) {
/*     */     try {
/* 379 */       this.encoding.setInt(this.map.indices[index], val);
/* 380 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 381 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(int index, long val) {
/*     */     try {
/* 390 */       this.encoding.setLong(this.map.indices[index], val);
/* 391 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 392 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloat(int index, float val) {
/*     */     try {
/* 401 */       this.encoding.setFloat(this.map.indices[index], val);
/* 402 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 403 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDouble(int index, double val) {
/*     */     try {
/* 412 */       this.encoding.setDouble(this.map.indices[index], val);
/* 413 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 414 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(int index, String val) {
/*     */     try {
/* 423 */       this.encoding.setString(this.map.indices[index], val);
/* 424 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 425 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObject(int index, Serializable val) throws IOException {
/*     */     try {
/* 434 */       this.encoding.setObject(this.map.indices[index], val);
/* 435 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 436 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncodedObject(int index, byte[] val) {
/*     */     try {
/* 445 */       this.encoding.setEncodedObject(this.map.indices[index], val);
/* 446 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 447 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteArray(int index, byte[] val) {
/*     */     try {
/* 456 */       this.encoding.setByteArray(this.map.indices[index], val);
/* 457 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 458 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(int index, Object val) throws IOException {
/* 466 */     if (this.map.tableMaps[index] != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 472 */         setTable(index, (MessageBodyHandle[])val);
/*     */         return;
/* 474 */       } catch (ClassCastException e) {
/* 475 */         throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-82355140, new Object[] { val.getClass() }));
/*     */       } 
/*     */     }
/*     */     try {
/* 479 */       this.encoding.setValue(this.map.indices[index], val);
/* 480 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 481 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBodyHandle newDynamic(Schema schema) {
/* 489 */     return this.encoding.newDynamic(schema);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDynamic(int index, MessageBodyHandle val) {
/*     */     try {
/* 497 */       this.encoding.setDynamic(this.map.indices[index], val);
/* 498 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 499 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBodyHandle newTableRow(int index) {
/*     */     try {
/* 509 */       MessageDataHandle ans = (MessageDataHandle)this.encoding.newTableRow(this.map.indices[index]);
/* 510 */       return new CompatibleMessageDataHandle(this.map.tableMaps[index], ans, (this.schema.fields[index]).contents);
/*     */     }
/* 512 */     catch (ArrayIndexOutOfBoundsException e) {
/* 513 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTable(int index, MessageBodyHandle[] val) {
/* 523 */     int i = 0;
/*     */     try {
/* 525 */       MessageDataHandle[] repl = new MessageDataHandle[val.length];
/* 526 */       for (i = 0; i < val.length; i++)
/* 527 */         repl[i] = ((CompatibleMessageDataHandle)val[i]).encoding; 
/* 528 */       this.encoding.setTable(this.map.indices[index], (MessageBodyHandle[])repl);
/* 529 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 530 */       throw StandardMessageDataHandle.badIndex(index);
/* 531 */     } catch (ClassCastException e) {
/* 532 */       throw new SchemaViolationException(ExceptionBuilder.buildReasonString(-468191348, new Object[] { val[i].getSchema(), this.encoding.getSchema() }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChoice(int index, int choice) {
/*     */     int realIndex;
/*     */     try {
/* 542 */       realIndex = this.map.indices[index];
/* 543 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 544 */       throw StandardMessageDataHandle.badIndex(index);
/*     */     } 
/* 546 */     if (realIndex == -1)
/*     */       return; 
/* 548 */     this.encoding.setChoice(realIndex, this.map.setCases[realIndex - this.map.varBias][choice]);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\CompatibleMessageDataHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */