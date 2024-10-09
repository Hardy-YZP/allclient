/*     */ package com.ibm.disthub2.spi;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExceptionBuilder
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  50 */   private static ClientTranslate translator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean translatorSet = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTranslator(ClientTranslate T) {
/*  62 */     translator = T;
/*  63 */     translatorSet = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTranslatorSet() {
/*  73 */     return translatorSet;
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
/*     */   public static String buildReasonString(int type, Object[] args) {
/*  92 */     if (translator != null) {
/*  93 */       return translator.translateReasonString(type, args);
/*     */     }
/*  95 */     return (new ExStruct(type, args)).toString();
/*     */   }
/*     */   
/*     */   public static ExStruct getExStruct(String reason) {
/*  99 */     return new ExStruct(reason);
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
/*     */   public static int extractType(String reason) {
/* 112 */     return (new ExStruct(reason)).type;
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
/*     */   public static String extractArg(String reason, int argNum) {
/* 126 */     ExStruct temp = new ExStruct(reason);
/* 127 */     if (temp.type == -1 || argNum < 0 || argNum >= temp
/*     */       
/* 129 */       .args.length) {
/* 130 */       return null;
/*     */     }
/* 132 */     return (String)temp.args[argNum];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int numArgs(String reason) {
/* 143 */     ExStruct temp = new ExStruct(reason);
/* 144 */     if (temp.type == -1) {
/* 145 */       return -1;
/*     */     }
/* 147 */     return temp.args.length;
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
/*     */   protected static String defaultBuild(int type, Object[] args) {
/* 163 */     return (new ExStruct(type, args)).toString();
/*     */   }
/*     */   
/*     */   public static class ExStruct {
/*     */     private int type;
/*     */     private Object[] args;
/*     */     
/*     */     public int getType() {
/* 171 */       return this.type; } public Object[] getArgs() {
/* 172 */       return this.args;
/*     */     }
/*     */     private ExStruct(int type, Object[] args) {
/* 175 */       this.type = type;
/* 176 */       this.args = args;
/*     */     }
/*     */ 
/*     */     
/*     */     public ExStruct(String reason) {
/* 181 */       this.type = -1;
/* 182 */       this.args = null;
/*     */ 
/*     */       
/* 185 */       if (reason == null || reason.length() == 0)
/* 186 */         return;  if (reason.indexOf('{') == -1 || reason.charAt(reason.length() - 1) != '}') {
/*     */         return;
/*     */       }
/* 189 */       int tempType = -1;
/* 190 */       String work = reason.substring(reason.indexOf('{') + 1, reason.length() - 1);
/* 191 */       int stop = work.indexOf('|');
/*     */       try {
/* 193 */         tempType = Integer.parseInt(work.substring(0, stop));
/* 194 */       } catch (Exception e) {
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       int argcount = 0;
/* 202 */       Object[] temp = new Object[1];
/* 203 */       if (stop + 1 < work.length()) {
/* 204 */         work = work.substring(stop + 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         while (true) {
/* 211 */           int limit = work.length();
/* 212 */           stop = 0;
/*     */           
/* 214 */           while (stop < limit && work
/* 215 */             .charAt(stop) != '|') {
/*     */             
/* 217 */             if (work.charAt(stop) == '\\') {
/*     */ 
/*     */               
/* 220 */               stop++;
/* 221 */               if (stop == limit || (work
/* 222 */                 .charAt(stop) != '\\' && work
/* 223 */                 .charAt(stop) != '|')) {
/*     */                 return;
/*     */               }
/*     */             } 
/*     */             
/* 228 */             stop++;
/*     */           } 
/*     */           
/* 231 */           if (stop == limit) {
/*     */             return;
/*     */           }
/* 234 */           if (argcount >= temp.length) {
/* 235 */             Object[] t2 = new Object[temp.length * 2];
/* 236 */             System.arraycopy(temp, 0, t2, 0, temp.length);
/* 237 */             temp = t2;
/*     */           } 
/* 239 */           temp[argcount++] = descIt(work.substring(0, stop));
/* 240 */           stop++;
/* 241 */           if (stop == limit)
/* 242 */             break;  work = work.substring(stop, limit);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 248 */       this.type = tempType;
/* 249 */       this.args = new Object[argcount];
/* 250 */       System.arraycopy(temp, 0, this.args, 0, argcount);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 254 */       StringBuffer result = new StringBuffer();
/* 255 */       result.append('{');
/* 256 */       result.append(this.type);
/* 257 */       result.append('|');
/* 258 */       if (this.args != null) {
/* 259 */         for (int i = 0; i < this.args.length; i++) {
/* 260 */           if (this.args[i] != null) {
/* 261 */             result.append(escIt(this.args[i].toString()));
/*     */           } else {
/* 263 */             result.append("null");
/* 264 */           }  result.append('|');
/*     */         } 
/*     */       }
/* 267 */       result.append('}');
/* 268 */       return result.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     private String escIt(String arg) {
/* 273 */       if (arg == null) {
/* 274 */         return arg;
/*     */       }
/*     */       
/* 277 */       StringBuffer convert = new StringBuffer();
/* 278 */       String cvt = arg;
/* 279 */       int next = cvt.indexOf('\\');
/* 280 */       while (next != -1) {
/* 281 */         if (next > 0)
/* 282 */           convert.append(cvt.substring(0, next)); 
/* 283 */         convert.append("\\\\");
/* 284 */         if (next + 1 < cvt.length()) {
/* 285 */           cvt = cvt.substring(next + 1);
/* 286 */           next = cvt.indexOf('\\'); continue;
/*     */         } 
/* 288 */         cvt = "";
/* 289 */         next = -1;
/*     */       } 
/*     */       
/* 292 */       convert.append(cvt);
/*     */ 
/*     */       
/* 295 */       cvt = convert.toString();
/* 296 */       convert = new StringBuffer();
/* 297 */       next = cvt.indexOf('|');
/* 298 */       while (next != -1) {
/* 299 */         if (next > 0)
/* 300 */           convert.append(cvt.substring(0, next)); 
/* 301 */         convert.append("\\|");
/* 302 */         if (next + 1 < cvt.length()) {
/* 303 */           cvt = cvt.substring(next + 1);
/* 304 */           next = cvt.indexOf('|'); continue;
/*     */         } 
/* 306 */         cvt = "";
/* 307 */         next = -1;
/*     */       } 
/*     */       
/* 310 */       convert.append(cvt);
/*     */       
/* 312 */       return convert.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     private String descIt(String arg) {
/* 317 */       if (arg == null) {
/* 318 */         return arg;
/*     */       }
/*     */       
/* 321 */       StringBuffer convert = new StringBuffer();
/* 322 */       String cvt = arg;
/* 323 */       int next = cvt.indexOf("\\\\");
/* 324 */       while (next != -1) {
/* 325 */         if (next > 0)
/* 326 */           convert.append(cvt.substring(0, next)); 
/* 327 */         convert.append('\\');
/* 328 */         if (next + 2 < cvt.length()) {
/* 329 */           cvt = cvt.substring(next + 2);
/* 330 */           next = cvt.indexOf("\\\\"); continue;
/*     */         } 
/* 332 */         cvt = "";
/* 333 */         next = -1;
/*     */       } 
/*     */       
/* 336 */       convert.append(cvt);
/*     */ 
/*     */       
/* 339 */       cvt = convert.toString();
/* 340 */       convert = new StringBuffer();
/* 341 */       next = cvt.indexOf("\\|");
/* 342 */       while (next != -1) {
/* 343 */         if (next > 0)
/* 344 */           convert.append(cvt.substring(0, next)); 
/* 345 */         convert.append('|');
/* 346 */         if (next + 2 < cvt.length()) {
/* 347 */           cvt = cvt.substring(next + 2);
/* 348 */           next = cvt.indexOf("\\|"); continue;
/*     */         } 
/* 350 */         cvt = "";
/* 351 */         next = -1;
/*     */       } 
/*     */       
/* 354 */       convert.append(cvt);
/*     */       
/* 356 */       return convert.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\ExceptionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */