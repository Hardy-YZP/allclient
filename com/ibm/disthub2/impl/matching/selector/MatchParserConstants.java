/*    */ package com.ibm.disthub2.impl.matching.selector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface MatchParserConstants
/*    */ {
/*    */   public static final int EOF = 0;
/*    */   public static final int IS = 22;
/*    */   public static final int IN = 23;
/*    */   public static final int BETWEEN = 24;
/*    */   public static final int LIKE = 25;
/*    */   public static final int ESCAPE = 26;
/*    */   public static final int OR = 27;
/*    */   public static final int AND = 28;
/*    */   public static final int NOT = 29;
/*    */   public static final int NULL = 30;
/*    */   public static final int TRUE = 31;
/*    */   public static final int FALSE = 32;
/*    */   public static final int EMPTY = 33;
/*    */   public static final int ISA = 34;
/*    */   public static final int IDENTIFIER = 35;
/*    */   public static final int QUOTED_IDENTIFIER = 36;
/*    */   public static final int LETTER = 37;
/*    */   public static final int DIGIT = 38;
/*    */   public static final int FIELDSEP = 39;
/*    */   public static final int INTEGER_LITERAL = 40;
/*    */   public static final int FLOATING_POINT_LITERAL = 41;
/*    */   public static final int EXPONENT = 42;
/*    */   public static final int STRING_LITERAL = 43;
/*    */   public static final int DEFAULT = 0;
/* 32 */   public static final String[] tokenImage = new String[] { "<EOF>", "\"<\"", "\">\"", "\"<=\"", "\">=\"", "\"<>\"", "\"=\"", "\"+\"", "\"-\"", "\"*\"", "\"/\"", "\"(\"", "\")\"", "\",\"", "\".\"", "\"[\"", "\"]\"", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"\\f\"", "\"IS\"", "\"IN\"", "\"BETWEEN\"", "\"LIKE\"", "\"ESCAPE\"", "\"OR\"", "\"AND\"", "\"NOT\"", "\"NULL\"", "\"true\"", "\"false\"", "\"EMPTY\"", "\"ISA\"", "<IDENTIFIER>", "<QUOTED_IDENTIFIER>", "<LETTER>", "<DIGIT>", "<FIELDSEP>", "<INTEGER_LITERAL>", "<FLOATING_POINT_LITERAL>", "<EXPONENT>", "<STRING_LITERAL>" };
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\MatchParserConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */