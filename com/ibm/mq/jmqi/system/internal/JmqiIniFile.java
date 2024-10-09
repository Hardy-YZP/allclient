/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiIniFile
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/JmqiIniFile.java";
/*     */   public static final String PROPERTY_PREFIX = "com.ibm.mq.cfg.";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jmqi.system.internal.JmqiIniFile", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/JmqiIniFile.java");
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
/*     */ 
/*     */   
/*  69 */   protected Map<String, String> propertyMap = Collections.synchronizedMap(new TreeMap<>());
/*     */ 
/*     */   
/*  72 */   private JmqiException firstParseFailure = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedReader fileReader;
/*     */ 
/*     */ 
/*     */   
/*     */   private String currentLine;
/*     */ 
/*     */ 
/*     */   
/*     */   private String currentStanzaPrefix;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int LINE_IS_STANZA = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int LINE_IS_KEY = 2;
/*     */ 
/*     */   
/*     */   private static final int LINE_IS_COMMENT = 3;
/*     */ 
/*     */   
/*     */   private static final char STANZA_COMMENT1 = ';';
/*     */ 
/*     */   
/*     */   private static final char STANZA_COMMENT2 = '#';
/*     */ 
/*     */   
/*     */   private static final char STANZA_START_CHAR = ':';
/*     */ 
/*     */   
/*     */   private static final char STANZA_EQUAL_CHAR = '=';
/*     */ 
/*     */   
/*     */   private static final char STANZA_ESCAPE = '\\';
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiIniFile(JmqiEnvironment env, InputStream inStream) {
/* 115 */     this(env, inStream, (inStream != null) ? inStream.toString() : "");
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream)", new Object[] { env, inStream });
/*     */     }
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiIniFile(JmqiEnvironment env, InputStream inStream, String fDescr) {
/* 141 */     super(env);
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)", new Object[] { env, inStream, fDescr });
/*     */     }
/*     */     
/* 146 */     if (inStream != null) {
/*     */       
/*     */       try {
/* 149 */         this.fileReader = new BufferedReader(new InputStreamReader(inStream, env.getNativeCharSetName()));
/*     */       }
/* 151 */       catch (Exception e) {
/* 152 */         if (Trace.isOn) {
/* 153 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)", e, 1);
/*     */         }
/*     */ 
/*     */         
/* 157 */         this.firstParseFailure = new JmqiException(env, 9516, new String[] { JmqiTools.getExSumm(e), null, fDescr }, 2, 2195, e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 162 */     if (this.fileReader != null) {
/*     */       
/*     */       try {
/* 165 */         while ((this.currentLine = this.fileReader.readLine()) != null) {
/* 166 */           processCurrentLine(fDescr);
/*     */         
/*     */         }
/*     */       }
/* 170 */       catch (JmqiException e) {
/* 171 */         if (Trace.isOn) {
/* 172 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)", (Throwable)e, 2);
/*     */         }
/*     */         
/* 175 */         if (this.firstParseFailure == null) {
/* 176 */           this.firstParseFailure = e;
/*     */         
/*     */         }
/*     */       }
/* 180 */       catch (IOException e) {
/* 181 */         if (Trace.isOn) {
/* 182 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)", e, 3);
/*     */         }
/*     */         
/* 185 */         if (this.firstParseFailure == null) {
/* 186 */           this.firstParseFailure = new JmqiException(env, 9516, new String[] { JmqiTools.getExSumm(e), null, fDescr }, 2, 2195, e);
/*     */         }
/*     */       }
/*     */       finally {
/*     */         
/* 191 */         if (Trace.isOn) {
/* 192 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)");
/*     */         }
/*     */         
/*     */         try {
/* 196 */           this.fileReader.close();
/*     */         }
/* 198 */         catch (IOException e) {
/* 199 */           if (Trace.isOn) {
/* 200 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)", e, 4);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 205 */         this.fileReader = null;
/* 206 */         this.currentLine = null;
/* 207 */         this.currentStanzaPrefix = null;
/*     */       } 
/*     */     }
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "<init>(JmqiEnvironment,InputStream,String)");
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
/*     */   public String getAttributeValue(String attribute) {
/* 225 */     String traceRet1 = this.propertyMap.get(attribute.toLowerCase());
/*     */     
/* 227 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiException getFirstParseFailure() {
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.JmqiIniFile", "getFirstParseFailure()", "getter", this.firstParseFailure);
/*     */     }
/*     */     
/* 240 */     return this.firstParseFailure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuilder getSummary(StringBuilder buff) {
/* 251 */     StringBuilder buffer = (buff == null) ? new StringBuilder() : buff;
/* 252 */     for (Map.Entry<String, String> entry : this.propertyMap.entrySet()) {
/* 253 */       String key = entry.getKey();
/* 254 */       String value = entry.getValue();
/* 255 */       buffer.append(key);
/* 256 */       buffer.append("=");
/* 257 */       if (value != null) {
/* 258 */         buffer.append(value);
/*     */       }
/* 260 */       buffer.append(JmqiTools.getNewline());
/*     */     } 
/* 262 */     return buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void enterStanza(String stanzaName) {
/* 273 */     String stanzaStub = "com.ibm.mq.cfg." + stanzaName.toLowerCase();
/* 274 */     this.currentStanzaPrefix = stanzaStub;
/* 275 */     int uniqueIndex = 0;
/* 276 */     String numExisting = this.propertyMap.get(stanzaStub);
/* 277 */     if (numExisting != null) {
/* 278 */       uniqueIndex = Integer.parseInt(numExisting);
/* 279 */       this.currentStanzaPrefix = stanzaStub + numExisting;
/*     */     } 
/*     */ 
/*     */     
/* 283 */     this.propertyMap.put(stanzaStub, Integer.toString(uniqueIndex + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void storeAttributeValuePair(String attribute, String value) {
/* 294 */     this.propertyMap.put(this.currentStanzaPrefix + "." + attribute.toLowerCase(), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getCurrentLineType() {
/* 304 */     int lineType = 3;
/* 305 */     boolean nonBlankSeen = false;
/*     */     
/* 307 */     for (int i = 0; i < this.currentLine.length(); i++) {
/* 308 */       char c = this.currentLine.charAt(i);
/* 309 */       if (!Character.isWhitespace(c)) {
/* 310 */         lineType = 1;
/* 311 */         if (c == ';' || c == '#') {
/* 312 */           if (nonBlankSeen) {
/* 313 */             lineType = 1; break;
/*     */           } 
/* 315 */           lineType = 3;
/*     */           break;
/*     */         } 
/* 318 */         if (c == ':') {
/* 319 */           lineType = 2; break;
/*     */         } 
/* 321 */         if (c == '=') {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 326 */         nonBlankSeen = true;
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     return lineType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void processCurrentLine(String fDescr) throws JmqiException {
/*     */     int endPos, commentPos, i, equalsPos;
/*     */     String attribute, value;
/* 338 */     int startPos = 0;
/*     */ 
/*     */ 
/*     */     
/* 342 */     int lineType = getCurrentLineType();
/* 343 */     switch (lineType) {
/*     */       
/*     */       case 2:
/* 346 */         while (Character.isWhitespace(this.currentLine.charAt(startPos))) {
/* 347 */           startPos++;
/*     */         }
/*     */         
/* 350 */         endPos = this.currentLine.indexOf(':');
/* 351 */         while (endPos > 0 && Character.isWhitespace(this.currentLine.charAt(endPos - 1))) {
/* 352 */           endPos--;
/*     */         }
/*     */         
/* 355 */         enterStanza(this.currentLine.substring(startPos, endPos));
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 360 */         if (this.currentStanzaPrefix == null) {
/* 361 */           throw new JmqiException(this.env, 9555, new String[] { null, null, fDescr }, 2, 2195, null);
/*     */         }
/*     */ 
/*     */         
/* 365 */         commentPos = -1;
/* 366 */         for (i = 0; i < this.currentLine.length(); i++) {
/* 367 */           if (this.currentLine.charAt(i) == '\\') {
/* 368 */             if (i < this.currentLine.length() - 1 && (this.currentLine
/* 369 */               .charAt(i + 1) == ';' || this.currentLine.charAt(i + 1) == '#' || this.currentLine.charAt(i + 1) == '\\'))
/*     */             {
/*     */ 
/*     */               
/* 373 */               this.currentLine = this.currentLine.substring(0, i) + this.currentLine.substring(i + 1);
/*     */             }
/* 375 */           } else if (this.currentLine.charAt(i) == ';' || this.currentLine.charAt(i) == '#') {
/* 376 */             commentPos = i;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 381 */         if (commentPos >= 0) {
/*     */ 
/*     */ 
/*     */           
/* 385 */           endPos = commentPos;
/* 386 */           while (endPos > 0 && Character.isWhitespace(this.currentLine.charAt(endPos - 1))) {
/* 387 */             endPos--;
/*     */           }
/*     */         } else {
/* 390 */           endPos = this.currentLine.length();
/*     */         } 
/*     */ 
/*     */         
/* 394 */         while (Character.isWhitespace(this.currentLine.charAt(startPos))) {
/* 395 */           startPos++;
/*     */         }
/*     */ 
/*     */         
/* 399 */         equalsPos = this.currentLine.indexOf('=');
/*     */ 
/*     */         
/* 402 */         if (equalsPos > 0) {
/*     */ 
/*     */           
/* 405 */           int valueStartPos = equalsPos + 1;
/* 406 */           while (equalsPos > 0 && Character.isWhitespace(this.currentLine.charAt(equalsPos - 1))) {
/* 407 */             equalsPos--;
/*     */           }
/* 409 */           attribute = this.currentLine.substring(startPos, equalsPos);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 414 */           while (valueStartPos < endPos && Character.isWhitespace(this.currentLine.charAt(valueStartPos))) {
/* 415 */             valueStartPos++;
/*     */           }
/*     */           
/* 418 */           while (valueStartPos < endPos && Character.isWhitespace(this.currentLine.charAt(endPos - 1))) {
/* 419 */             endPos--;
/*     */           }
/* 421 */           value = this.currentLine.substring(valueStartPos, endPos);
/*     */         }
/*     */         else {
/*     */           
/* 425 */           while (startPos < endPos && Character.isWhitespace(this.currentLine.charAt(endPos - 1))) {
/* 426 */             endPos--;
/*     */           }
/* 428 */           attribute = this.currentLine.substring(startPos, endPos);
/* 429 */           value = "";
/*     */         } 
/*     */ 
/*     */         
/* 433 */         storeAttributeValuePair(attribute, value);
/*     */ 
/*     */       
/*     */       case 3:
/*     */         return;
/*     */     } 
/*     */     
/* 440 */     JmqiException traceRet1 = new JmqiException(this.env, 9555, new String[] { null, null, fDescr }, 2, 2195, null);
/*     */     
/* 442 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\JmqiIniFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */