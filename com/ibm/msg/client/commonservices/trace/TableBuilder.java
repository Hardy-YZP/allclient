/*     */ package com.ibm.msg.client.commonservices.trace;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableBuilder
/*     */ {
/*     */   static class Entry
/*     */     implements Comparable<Entry>
/*     */   {
/*     */     String key;
/*     */     Object value;
/*     */     
/*     */     public int compareTo(Entry o) {
/*  56 */       return this.key.compareTo(o.key);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/*  61 */       if (o instanceof Entry) {
/*  62 */         Entry other = (Entry)o;
/*  63 */         return (this.key.equals(other.key) && this.value.equals(other.value));
/*     */       } 
/*  65 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/*  70 */       return this.key.hashCode() | this.value.hashCode();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   ArrayList<Entry> entries = new ArrayList<>();
/*     */ 
/*     */   
/*  83 */   int maxKeyLength = 0;
/*     */ 
/*     */   
/*  86 */   String sep = ":-  ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean setOutputSorted = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 103 */     String lineSeparatorProperty = "line.separator";
/*     */ 
/*     */     
/* 106 */     PropertyStore.register(lineSeparatorProperty, "\n");
/* 107 */   } private static String lineSeparator = PropertyStore.line_separator;
/*     */   
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/TableBuilder.java";
/*     */   
/*     */   String startIndent;
/*     */   
/*     */   private char prefix;
/*     */   
/*     */   private boolean outputSorted;
/*     */   private int startIndentAmount;
/*     */   
/*     */   public TableBuilder() {
/* 119 */     this(3, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableBuilder(int startIndentAmount, boolean outputSorted) {
/* 129 */     this(startIndentAmount, outputSorted, '|');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableBuilder(int startIndentAmount, boolean outputSorted, char prefix) {
/* 140 */     this.prefix = prefix;
/* 141 */     this.outputSorted = outputSorted;
/* 142 */     this.startIndentAmount = startIndentAmount;
/* 143 */     int indentLength = startIndentAmount + ((prefix != '\000') ? 1 : 0);
/* 144 */     char[] indentArray = new char[indentLength];
/* 145 */     Arrays.fill(indentArray, ' ');
/* 146 */     if (prefix != '\000') {
/* 147 */       indentArray[0] = prefix;
/*     */     }
/* 149 */     this.startIndent = new String(indentArray);
/*     */     
/* 151 */     this.setOutputSorted = outputSorted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuffer toStringBuffer() {
/* 160 */     return new StringBuffer(toStringBuilder());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuilder toStringBuilder() {
/* 169 */     StringBuilder buffer = new StringBuilder();
/*     */ 
/*     */ 
/*     */     
/* 173 */     if (this.setOutputSorted) {
/* 174 */       Collections.sort(this.entries);
/*     */     }
/*     */     
/* 177 */     int count = 0;
/* 178 */     for (Entry entry : this.entries) {
/* 179 */       count++;
/* 180 */       String key = entry.key;
/* 181 */       int keySize = key.length();
/* 182 */       buffer.append(this.startIndent).append(key);
/*     */ 
/*     */       
/* 185 */       int spacesToAdd = this.maxKeyLength - keySize + 2;
/* 186 */       for (int i = 0; i < spacesToAdd; i++) {
/* 187 */         buffer.append(' ');
/*     */       }
/*     */       
/* 190 */       buffer.append(this.sep);
/*     */ 
/*     */ 
/*     */       
/* 194 */       Object value = entry.value;
/*     */       
/* 196 */       if (value instanceof TableBuilder) {
/* 197 */         buffer.append(lineSeparator).append(this.startIndent);
/*     */       }
/*     */       
/* 200 */       String stringValue = value.toString();
/* 201 */       stringValue = stringValue.replaceAll(lineSeparator, lineSeparator + this.startIndent);
/*     */       
/* 203 */       buffer.append(stringValue);
/*     */ 
/*     */       
/* 206 */       if (count < this.entries.size()) {
/* 207 */         buffer.append(lineSeparator);
/*     */       }
/*     */     } 
/*     */     
/* 211 */     return buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 220 */     return toStringBuilder().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(String key, Object value) {
/* 231 */     synchronized (this.entries) {
/*     */       
/* 233 */       Entry entry = new Entry();
/* 234 */       entry.key = key;
/*     */       
/* 236 */       if (value == null) {
/* 237 */         entry.value = "<null>";
/*     */       
/*     */       }
/* 240 */       else if (value instanceof Map) {
/*     */         
/* 242 */         TableBuilder mapTable = new TableBuilder(this.startIndentAmount, this.outputSorted, this.prefix);
/*     */ 
/*     */ 
/*     */         
/* 246 */         Map<?, ?> map = (Map<?, ?>)value;
/* 247 */         Set<?> entrySet = map.entrySet();
/*     */         
/* 249 */         for (Object o : entrySet) {
/* 250 */           Map.Entry<?, ?> ent = (Map.Entry<?, ?>)o;
/* 251 */           mapTable.append((String)ent.getKey(), ent.getValue());
/*     */         } 
/*     */ 
/*     */         
/* 255 */         entry.value = mapTable;
/*     */       
/*     */       }
/* 258 */       else if (value instanceof List) {
/*     */         
/* 260 */         TableBuilder listTable = new TableBuilder(this.startIndentAmount, this.outputSorted, this.prefix);
/* 261 */         List<?> list = (List)value;
/*     */ 
/*     */ 
/*     */         
/* 265 */         Iterator<?> iterator = list.iterator();
/* 266 */         int itemIndex = 0;
/* 267 */         while (iterator.hasNext()) {
/* 268 */           listTable.append(Integer.toString(itemIndex++), iterator.next());
/*     */         }
/*     */ 
/*     */         
/* 272 */         entry.value = listTable;
/*     */       }
/* 274 */       else if (value instanceof TableBuilder) {
/* 275 */         entry.value = value;
/*     */       } else {
/*     */         
/* 278 */         entry.value = value.toString();
/*     */       } 
/*     */ 
/*     */       
/* 282 */       this.entries.add(entry);
/*     */       
/* 284 */       int keyLength = key.length();
/* 285 */       if (key.length() > this.maxKeyLength) {
/* 286 */         this.maxKeyLength = keyLength;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(Map<String, Object> table) {
/* 296 */     if (table != null) {
/* 297 */       Set<Map.Entry<String, Object>> entrySet = table.entrySet();
/* 298 */       for (Map.Entry<String, Object> entry : entrySet)
/* 299 */         append(entry.getKey(), entry.getValue()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TableBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */