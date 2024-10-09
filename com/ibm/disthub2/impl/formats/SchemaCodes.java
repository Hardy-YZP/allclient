/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface SchemaCodes
/*     */ {
/*     */   public static final byte CURRENT_VERSION = 1;
/*     */   public static final byte HIGHEST_ACCESS = -100;
/*     */   public static final byte HIGH_ACCESS = -101;
/*     */   public static final byte TYPICAL_ACCESS = -110;
/*     */   public static final byte NO_ACCESS = -128;
/*     */   public static final byte SCHEMA = 0;
/*     */   public static final byte DYNAMIC = -1;
/*     */   public static final byte STRING = -2;
/*     */   public static final byte OBJECT = -3;
/*     */   public static final byte BYTEARRAY = -4;
/*     */   public static final byte BOOLEAN = 1;
/*     */   public static final byte BYTE = 2;
/*     */   public static final byte SHORT = 3;
/*     */   public static final byte CHAR = 4;
/*     */   public static final byte INT = 5;
/*     */   public static final byte LONG = 6;
/*     */   public static final byte FLOAT = 7;
/*     */   public static final byte DOUBLE = 8;
/*     */   public static final int LEAST = -4;
/*     */   public static final int LEAST_FIXED = 1;
/* 149 */   public static final String[] typeNames = new String[] { "byte[]", "Serializable", "String", "dynamic", "submessage", "boolean", "byte", "short", "char", "int", "long", "float", "double" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public static final int[] typeSizes = new int[] { 1, 1, 2, 2, 4, 8, 4, 8 };
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\SchemaCodes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */