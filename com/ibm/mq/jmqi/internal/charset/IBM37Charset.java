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
/*     */ public class IBM37Charset
/*     */   extends SingleByteCharset
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/charset/IBM37Charset.java";
/*  31 */   private static final char[] CP37_to_chars = new char[] { Character.MIN_VALUE, '\001', '\002', '\003', '', '\t', '', '', '', '', '', '\013', '\f', '\r', '\016', '\017', '\020', '\021', '\022', '\023', '', '', '\b', '', '\030', '\031', '', '', '\034', '\035', '\036', '\037', '', '', '', '', '', '\n', '\027', '\033', '', '', '', '', '', '\005', '\006', '\007', '', '', '\026', '', '', '', '', '\004', '', '', '', '', '\024', '\025', '', '\032', ' ', ' ', 'â', 'ä', 'à', 'á', 'ã', 'å', 'ç', 'ñ', '¢', '.', '<', '(', '+', '|', '&', 'é', 'ê', 'ë', 'è', 'í', 'î', 'ï', 'ì', 'ß', '!', '$', '*', ')', ';', '¬', '-', '/', 'Â', 'Ä', 'À', 'Á', 'Ã', 'Å', 'Ç', 'Ñ', '¦', ',', '%', '_', '>', '?', 'ø', 'É', 'Ê', 'Ë', 'È', 'Í', 'Î', 'Ï', 'Ì', '`', ':', '#', '@', '\'', '=', '"', 'Ø', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', '«', '»', 'ð', 'ý', 'þ', '±', '°', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 'ª', 'º', 'æ', '¸', 'Æ', '¤', 'µ', '~', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '¡', '¿', 'Ð', 'Ý', 'Þ', '®', '^', '£', '¥', '·', '©', '§', '¶', '¼', '½', '¾', '[', ']', '¯', '¨', '´', '×', '{', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', '­', 'ô', 'ö', 'ò', 'ó', 'õ', '}', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', '¹', 'û', 'ü', 'ù', 'ú', 'ÿ', '\\', '÷', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '²', 'Ô', 'Ö', 'Ò', 'Ó', 'Õ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '³', 'Û', 'Ü', 'Ù', 'Ú', '' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBM37Charset() {
/* 293 */     super("IBM-037", new String[] { "ibm-37" }, CP37_to_chars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCcsid() {
/* 301 */     return 37;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\charset\IBM37Charset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */