/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.LogConstants;
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
/*     */ public final class TupleDef
/*     */   implements Serializable, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  43 */   private static final DebugObject debug = new DebugObject("TupleDef");
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int INIT_COLUMNS = 8;
/*     */ 
/*     */ 
/*     */   
/*     */   private int nColumns;
/*     */ 
/*     */ 
/*     */   
/*     */   private ColumnDef[] columns;
/*     */ 
/*     */ 
/*     */   
/*     */   private ColumnDef nextDef;
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private ColumnDef parent;
/*     */ 
/*     */   
/*     */   private boolean cppNullified;
/*     */ 
/*     */ 
/*     */   
/*     */   TupleDef(byte[] frame, int[] cursor) {
/*  75 */     int n = Schema.getCount(frame, cursor);
/*  76 */     if (n == -1) {
/*  77 */       setTable();
/*  78 */       this.nColumns = Schema.getCount(frame, cursor);
/*     */     } else {
/*     */       
/*  81 */       this.nColumns = n;
/*  82 */     }  this.columns = new ColumnDef[this.nColumns];
/*  83 */     for (int i = 0; i < this.nColumns; i++)
/*  84 */       this.columns[i] = new ColumnDef(frame, cursor); 
/*  85 */     this.name = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TupleDef() {
/*  94 */     this.columns = new ColumnDef[8];
/*  95 */     this.name = "";
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
/*     */   public void setName(String name) {
/* 108 */     this.name = (name == null) ? "" : name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNames(Names names, ColumnDef parent) {
/* 119 */     setName(names.name);
/* 120 */     this.parent = parent;
/* 121 */     for (int i = 0; i < this.nColumns; i++) {
/* 122 */       this.columns[i].setNames(names.subNames[i], this);
/*     */     }
/* 124 */     if (this.nextDef != null) {
/* 125 */       this.nextDef.setNames(new Names("", null), this);
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
/*     */   public String getShortName() {
/* 137 */     String result = this.name;
/* 138 */     if (this.name == null || this.name.length() == 0)
/* 139 */       if (this.parent == null) {
/* 140 */         result = null;
/*     */       } else {
/* 142 */         result = this.parent.getShortName();
/*     */       }  
/* 144 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 152 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 161 */     String result = this.name;
/* 162 */     if (this.parent != null) {
/* 163 */       result = this.parent.getFullName() + "." + this.name;
/*     */     }
/* 165 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCppFullName(boolean stopAtTable) {
/* 176 */     if (stopAtTable && this.nextDef != null)
/* 177 */       return null; 
/* 178 */     String context = null;
/* 179 */     if (this.parent != null && this.parent.parent != null)
/* 180 */       context = this.parent.parent.getCppFullName(stopAtTable); 
/* 181 */     if (context == null || context.length() == 0) {
/* 182 */       if (this.cppNullified) {
/* 183 */         return null;
/*     */       }
/* 185 */       return this.name;
/*     */     } 
/* 187 */     if (this.cppNullified || this.name == null || this.name.length() == 0) {
/* 188 */       return context;
/*     */     }
/* 190 */     return context + "." + this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nullifyCppName() {
/* 199 */     this.cppNullified = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/* 207 */     return this.nColumns;
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
/*     */   public ColumnDef getColumnDef(int index) {
/* 221 */     ColumnDef result = null;
/* 222 */     if (index >= 0 && index < this.nColumns) {
/* 223 */       result = this.columns[index];
/*     */     }
/* 225 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(ColumnDef col) {
/* 233 */     if (this.nColumns == this.columns.length) {
/* 234 */       ColumnDef[] newColumns = new ColumnDef[this.nColumns * 2];
/* 235 */       System.arraycopy(this.columns, 0, newColumns, 0, this.nColumns);
/* 236 */       this.columns = newColumns;
/*     */     } 
/* 238 */     this.columns[this.nColumns++] = col;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTable() {
/* 248 */     Schema s = new Schema();
/* 249 */     s.add(new TupleDef());
/* 250 */     s.add(this);
/*     */     
/* 252 */     this.nextDef = new ColumnDef(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnDef getNextDef() {
/* 261 */     return this.nextDef;
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
/*     */   TupleDef(byte[] frame, int offset, int length) {
/* 275 */     this(frame, new int[] { offset, offset + length });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int encodedSize() {
/* 283 */     int size = 1;
/* 284 */     if (this.nextDef != null) size++; 
/* 285 */     for (int i = 0; i < this.nColumns; i++) {
/* 286 */       size += this.columns[i].encodedSize();
/*     */     }
/* 288 */     return size;
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
/*     */   void encode(byte[] frame, int[] cursor) {
/* 301 */     if (this.nextDef != null)
/* 302 */       Schema.setByte(frame, cursor, (byte)-1); 
/* 303 */     Schema.setCount(frame, cursor, this.nColumns);
/* 304 */     for (int i = 0; i < this.nColumns; i++) {
/* 305 */       this.columns[i].encode(frame, cursor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     StringBuffer ans;
/* 314 */     if (this.name != null && this.name.length() > 0) {
/* 315 */       ans = (new StringBuffer(this.name)).append(" [ ");
/*     */     } else {
/* 317 */       ans = new StringBuffer("[");
/* 318 */     }  for (int i = 0; i < this.nColumns; i++)
/* 319 */       ans.append(this.columns[i].toString()); 
/* 320 */     if (this.nextDef != null) { ans.append("]* "); }
/* 321 */     else { ans.append("] "); }
/* 322 */      return ans.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\TupleDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */