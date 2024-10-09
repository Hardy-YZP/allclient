/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import javax.security.auth.x500.X500Principal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class X500NameWrapper
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/X500NameWrapper.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.ese.core.X500NameWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/X500NameWrapper.java");
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
/*     */ 
/*     */   
/*  54 */   private X500Principal x500Name = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private static String[][] attributesNames = (String[][])null;
/*     */   
/*  61 */   private String x500NameString = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static String[][] attributeReplacementNames = new String[][] { { "MAIL=", "EMAILADDRESS=" }, { "PC=", "POSTALCODE=" } };
/*     */ 
/*     */   
/*     */   static {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry("com.ibm.mq.ese.core.X500NameWrapper", "static()");
/*     */     }
/*  73 */     init();
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit("com.ibm.mq.ese.core.X500NameWrapper", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public X500NameWrapper(String subjectName) throws AMBIException {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.ese.core.X500NameWrapper", "<init>(String)", new Object[] { subjectName });
/*     */     }
/*     */     
/*     */     try {
/*  90 */       this.x500NameString = normalizeNames(subjectName);
/*  91 */       this.x500NameString = replaceAttributeNames(this.x500NameString);
/*  92 */       this.x500Name = new X500Principal(this.x500NameString);
/*     */     }
/*  94 */     catch (Exception e) {
/*  95 */       if (Trace.isOn) {
/*  96 */         Trace.catchBlock(this, "com.ibm.mq.ese.core.X500NameWrapper", "<init>(String)", e);
/*     */       }
/*  98 */       HashMap<String, Object> inserts = new HashMap<>();
/*  99 */       inserts.put("AMS_INSERT_SUBJECT_NAME", subjectName);
/* 100 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_policy_failed_to_create_ambix500Name_object, inserts, e);
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.throwing(this, "com.ibm.mq.ese.core.X500NameWrapper", "<init>(String)", ex);
/*     */       }
/* 104 */       throw ex;
/*     */     } 
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.ese.core.X500NameWrapper", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void init() {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry("com.ibm.mq.ese.core.X500NameWrapper", "init()");
/*     */     }
/* 120 */     attributesNames = new String[9][];
/*     */     
/* 122 */     int attributeSetIndex = 0;
/* 123 */     int arrayIndex = 0;
/*     */ 
/*     */     
/* 126 */     attributesNames[attributeSetIndex] = new String[3];
/* 127 */     attributesNames[attributeSetIndex][arrayIndex++] = "c";
/* 128 */     attributesNames[attributeSetIndex][arrayIndex++] = "countryName";
/* 129 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.6";
/*     */     
/* 131 */     attributeSetIndex++;
/* 132 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 135 */     attributesNames[attributeSetIndex] = new String[3];
/* 136 */     attributesNames[attributeSetIndex][arrayIndex++] = "l";
/* 137 */     attributesNames[attributeSetIndex][arrayIndex++] = "localityName";
/* 138 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.7";
/*     */     
/* 140 */     attributeSetIndex++;
/* 141 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 144 */     attributesNames[attributeSetIndex] = new String[3];
/* 145 */     attributesNames[attributeSetIndex][arrayIndex++] = "pc";
/* 146 */     attributesNames[attributeSetIndex][arrayIndex++] = "postalCode";
/* 147 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.17";
/*     */     
/* 149 */     attributeSetIndex++;
/* 150 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 153 */     attributesNames[attributeSetIndex] = new String[4];
/* 154 */     attributesNames[attributeSetIndex][arrayIndex++] = "st";
/* 155 */     attributesNames[attributeSetIndex][arrayIndex++] = "s";
/* 156 */     attributesNames[attributeSetIndex][arrayIndex++] = "StateOrProvince";
/* 157 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.8";
/*     */     
/* 159 */     attributeSetIndex++;
/* 160 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 163 */     attributesNames[attributeSetIndex] = new String[4];
/* 164 */     attributesNames[attributeSetIndex][arrayIndex++] = "street";
/* 165 */     attributesNames[attributeSetIndex][arrayIndex++] = "str";
/* 166 */     attributesNames[attributeSetIndex][arrayIndex++] = "streetAddress";
/* 167 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.9";
/*     */     
/* 169 */     attributeSetIndex++;
/* 170 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 173 */     attributesNames[attributeSetIndex] = new String[4];
/* 174 */     attributesNames[attributeSetIndex][arrayIndex++] = "o";
/* 175 */     attributesNames[attributeSetIndex][arrayIndex++] = "organizationname";
/* 176 */     attributesNames[attributeSetIndex][arrayIndex++] = "organisationname";
/* 177 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.10";
/*     */     
/* 179 */     attributeSetIndex++;
/* 180 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 183 */     attributesNames[attributeSetIndex] = new String[4];
/* 184 */     attributesNames[attributeSetIndex][arrayIndex++] = "ou";
/* 185 */     attributesNames[attributeSetIndex][arrayIndex++] = "organizationalUnitName";
/* 186 */     attributesNames[attributeSetIndex][arrayIndex++] = "organisationalUnitName";
/* 187 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.11";
/*     */     
/* 189 */     attributeSetIndex++;
/* 190 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 193 */     attributesNames[attributeSetIndex] = new String[3];
/* 194 */     attributesNames[attributeSetIndex][arrayIndex++] = "cn";
/* 195 */     attributesNames[attributeSetIndex][arrayIndex++] = "commonName";
/* 196 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.2.5.4.3";
/*     */     
/* 198 */     attributeSetIndex++;
/* 199 */     arrayIndex = 0;
/*     */ 
/*     */     
/* 202 */     attributesNames[attributeSetIndex] = new String[4];
/* 203 */     attributesNames[attributeSetIndex][arrayIndex++] = "mail";
/* 204 */     attributesNames[attributeSetIndex][arrayIndex++] = "emailAddress";
/* 205 */     attributesNames[attributeSetIndex][arrayIndex++] = "email";
/* 206 */     attributesNames[attributeSetIndex][arrayIndex++] = "OID.1.2.840.113549.1.9.1";
/*     */     
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit("com.ibm.mq.ese.core.X500NameWrapper", "init()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getPreferredName(String name) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry("com.ibm.mq.ese.core.X500NameWrapper", "getPreferredName(String)", new Object[] { name });
/*     */     }
/*     */     
/* 219 */     if (name != null && name.length() != 0) {
/* 220 */       for (int i = 0; i < attributesNames.length; i++) {
/*     */         
/* 222 */         for (int k = 0; k < (attributesNames[i]).length; k++) {
/* 223 */           if (name.toUpperCase().equals(attributesNames[i][k]
/* 224 */               .toUpperCase())) {
/*     */             
/* 226 */             String traceRet1 = attributesNames[i][0].toUpperCase();
/* 227 */             if (Trace.isOn) {
/* 228 */               Trace.exit("com.ibm.mq.ese.core.X500NameWrapper", "getPreferredName(String)", traceRet1, 1);
/*     */             }
/*     */             
/* 231 */             return traceRet1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit("com.ibm.mq.ese.core.X500NameWrapper", "getPreferredName(String)", name, 2);
/*     */     }
/* 239 */     return name;
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
/*     */   public static String normalizeNames(String name) {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry("com.ibm.mq.ese.core.X500NameWrapper", "normalizeNames(String)", new Object[] { name });
/*     */     }
/*     */     
/* 259 */     StringBuilder nameBuffer = new StringBuilder();
/*     */     
/* 261 */     boolean inQuote = false;
/* 262 */     boolean inEscape = false;
/* 263 */     boolean newEscape = false;
/* 264 */     boolean expectType = true;
/*     */     
/* 266 */     int tokenStart = 0;
/*     */     
/* 268 */     for (int c = 0; c < name.length(); c++) {
/* 269 */       switch (name.charAt(c)) {
/*     */         
/*     */         case '=':
/* 272 */           if (expectType) {
/*     */             
/* 274 */             String type = name.substring(tokenStart, c);
/* 275 */             String preferred = getPreferredName(type.trim());
/*     */             
/* 277 */             nameBuffer.append(preferred);
/* 278 */             nameBuffer.append('=');
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 284 */             expectType = false;
/* 285 */             tokenStart = c + 1;
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case '+':
/*     */         case ',':
/*     */         case ';':
/* 294 */           if (!inQuote && !inEscape) {
/* 295 */             String value = name.substring(tokenStart, c);
/* 296 */             nameBuffer.append(value);
/*     */             
/* 298 */             if (name.charAt(c) == '+') {
/* 299 */               nameBuffer.append('+');
/*     */             } else {
/* 301 */               nameBuffer.append(',');
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 308 */             expectType = true;
/* 309 */             tokenStart = c + 1;
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case '\\':
/* 316 */           if (!inEscape) {
/* 317 */             newEscape = true;
/*     */           }
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case '"':
/* 324 */           if (!inEscape) {
/* 325 */             inQuote = !inQuote;
/*     */           }
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 337 */       inEscape = newEscape;
/* 338 */       newEscape = false;
/*     */     } 
/*     */ 
/*     */     
/* 342 */     nameBuffer.append(name.substring(tokenStart));
/*     */     
/* 344 */     String traceRet1 = nameBuffer.toString();
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.exit("com.ibm.mq.ese.core.X500NameWrapper", "normalizeNames(String)", traceRet1);
/*     */     }
/* 348 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private String replaceAttributeNames(String subjectName) {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.entry(this, "com.ibm.mq.ese.core.X500NameWrapper", "replaceAttributeNames(String)", new Object[] { subjectName });
/*     */     }
/*     */     
/* 356 */     String x500NameStr = subjectName;
/* 357 */     for (int i = 0; i < attributeReplacementNames.length; i++) {
/*     */       
/* 359 */       int attributeIndex = x500NameStr.indexOf(attributeReplacementNames[i][0]);
/* 360 */       StringBuilder x500NameBuffer = new StringBuilder(x500NameStr);
/*     */       
/* 362 */       while (attributeIndex != -1) {
/* 363 */         x500NameBuffer.replace(attributeIndex, attributeIndex + attributeReplacementNames[i][0]
/* 364 */             .length(), attributeReplacementNames[i][1]);
/*     */         
/* 366 */         attributeIndex = x500NameBuffer.toString().indexOf(attributeReplacementNames[i][0]);
/*     */       } 
/*     */       
/* 369 */       x500NameStr = x500NameBuffer.toString();
/*     */     } 
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.exit(this, "com.ibm.mq.ese.core.X500NameWrapper", "replaceAttributeNames(String)", x500NameStr);
/*     */     }
/*     */     
/* 375 */     return x500NameStr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.mq.ese.core.X500NameWrapper", "toString()");
/*     */     }
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit(this, "com.ibm.mq.ese.core.X500NameWrapper", "toString()", this.x500NameString);
/*     */     }
/* 391 */     return this.x500NameString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEqual(X500NameWrapper name) {
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.entry(this, "com.ibm.mq.ese.core.X500NameWrapper", "isEqual(X500NameWrapper)", new Object[] { name });
/*     */     }
/*     */     
/* 405 */     boolean traceRet1 = this.x500Name.equals(name.x500Name);
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.exit(this, "com.ibm.mq.ese.core.X500NameWrapper", "isEqual(X500NameWrapper)", 
/* 408 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 410 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\X500NameWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */