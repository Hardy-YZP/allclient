/*     */ package com.ibm.mq.jmqi.internal.charset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HP1051Charset
/*     */   extends SingleByteCharset
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/HP1051Charset.java";
/*     */   
/*     */   static {
/*  37 */     char[] CP1051_to_chars_above_0xa1 = { 'À', 'Â', 'È', 'Ê', 'Ë', 'Î', 'Ï', 'ˊ', 'ˋ', 'ˆ', '¨', '˜', 'Ù', 'Û', '₤', '¯', 'Ý', 'ý', '°', 'Ç', 'ç', 'Ñ', 'ñ', '¡', '¿', '¤', '£', '¥', '§', 'ƒ', '¢', 'â', 'ê', 'ô', 'û', 'á', 'é', 'ó', 'ú', 'à', 'è', 'ò', 'ù', 'ä', 'ë', 'ö', 'ü', 'Å', 'î', 'Ø', 'Æ', 'å', 'í', 'ø', 'æ', 'Ä', 'ì', 'Ö', 'Ü', 'É', 'ï', 'ß', 'Ô', 'Á', 'Ã', 'ã', 'Ð', 'ð', 'Í', 'Ì', 'Ó', 'Ò', 'Õ', 'õ', 'Š', 'š', 'Ú', 'Ÿ', 'ÿ', 'Þ', 'þ', '·', 'µ', '¶', '¾', '­', '¼', '½', 'ª', 'º', '«', '■', '»', '±', '\032' };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   private static char[] CP1051_to_chars = new char[256]; static {
/* 138 */     for (int i = 0; i < 161; i++) {
/* 139 */       CP1051_to_chars[i] = (char)i;
/*     */     }
/*     */ 
/*     */     
/* 143 */     System.arraycopy(CP1051_to_chars_above_0xa1, 0, CP1051_to_chars, 161, 94);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HP1051Charset() {
/* 150 */     super("hp-roman8", new String[] { "Cp1051", "ibm-1051", "roman8", "r8" }, CP1051_to_chars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCcsid() {
/* 158 */     return 1051;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\HP1051Charset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */