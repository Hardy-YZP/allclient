/*    */ package com.ibm.mq.jmqi;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringJoiner
/*    */ {
/*    */   protected String delimiter;
/*    */   protected String prefix;
/*    */   protected String suffix;
/* 36 */   protected List<String> elements = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringJoiner(String delimiter, String prefix, String suffix) {
/* 44 */     this.delimiter = delimiter;
/* 45 */     this.prefix = prefix;
/* 46 */     this.suffix = suffix;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringJoiner(String delimiter) {
/* 53 */     this(delimiter, "", "");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(String element) {
/* 60 */     this.elements.add(element);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     StringBuilder result = new StringBuilder();
/* 70 */     boolean firstElement = true;
/* 71 */     for (String element : this.elements) {
/* 72 */       if (!firstElement) {
/* 73 */         result.append(this.delimiter);
/*    */       } else {
/*    */         
/* 76 */         firstElement = false;
/*    */       } 
/* 78 */       result.append(element);
/*    */     } 
/*    */     
/* 81 */     return String.format("%s%s%s", new Object[] { this.prefix, result.toString(), this.suffix });
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\StringJoiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */