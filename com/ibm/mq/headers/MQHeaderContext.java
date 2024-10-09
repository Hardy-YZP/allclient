/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.HeaderMessages;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MQHeaderContext
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderContext.java";
/*     */   private String format;
/*     */   private int encoding;
/*     */   private int characterSet;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.headers.MQHeaderContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderContext.java");
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
/*     */   public static MQHeaderContext createMQHeaderContext(DataInput messageP) {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput)", new Object[] { messageP });
/*     */     }
/*     */     
/*  71 */     DataInput message = messageP;
/*  72 */     String format = "        ";
/*     */     
/*  74 */     if (message instanceof MessageWrapper) {
/*  75 */       format = ((MessageWrapper)message).getFormat();
/*  76 */       message = (DataInput)((MessageWrapper)message).getDelegate();
/*     */     } 
/*     */     try {
/*  79 */       MQHeaderContext traceRet1 = MQMessageContext.getInstance(message);
/*  80 */       if (Trace.isOn) {
/*  81 */         Trace.exit("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput)", traceRet1, 1);
/*     */       }
/*     */       
/*  84 */       return traceRet1;
/*     */     }
/*  86 */     catch (Exception e) {
/*  87 */       if (Trace.isOn) {
/*  88 */         Trace.catchBlock("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput)", e);
/*     */       }
/*     */ 
/*     */       
/*  92 */       MQHeaderContext traceRet2 = new InputStreamMessageContext((InputStream)message, format, 273, 0);
/*     */ 
/*     */       
/*  95 */       if (Trace.isOn) {
/*  96 */         Trace.exit("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput)", traceRet2, 2);
/*     */       }
/*     */       
/*  99 */       return traceRet2;
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
/*     */   public static MQHeaderContext createMQHeaderContext(DataInput messageP, String format, int encoding, int characterSet) {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput,String,int,int)", new Object[] { messageP, format, 
/*     */             
/* 117 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/* 119 */     DataInput message = messageP;
/* 120 */     if (message instanceof MessageWrapper) {
/* 121 */       message = (DataInput)((MessageWrapper)message).getDelegate();
/*     */     }
/*     */     try {
/* 124 */       MQHeaderContext traceRet1 = MQMessageContext.getInstance(message);
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.exit("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput,String,int,int)", traceRet1, 1);
/*     */       }
/*     */       
/* 129 */       return traceRet1;
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.catchBlock("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput,String,int,int)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 140 */         MQHeaderContext traceRet2 = new InputStreamMessageContext((InputStream)message, format, encoding, characterSet);
/*     */         
/* 142 */         if (Trace.isOn) {
/* 143 */           Trace.exit("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput,String,int,int)", traceRet2, 2);
/*     */         }
/*     */         
/* 146 */         return traceRet2;
/*     */       }
/* 148 */       catch (Exception exception) {
/* 149 */         if (Trace.isOn) {
/* 150 */           Trace.catchBlock("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput,String,int,int)", exception, 2);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 157 */         IllegalArgumentException traceRet3 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0002", new Object[] {
/* 158 */                 message.getClass().getName() }));
/* 159 */         if (Trace.isOn) {
/* 160 */           Trace.throwing("com.ibm.mq.headers.MQHeaderContext", "createMQHeaderContext(DataInput,String,int,int)", traceRet3);
/*     */         }
/*     */         
/* 163 */         throw traceRet3;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQHeaderContext(String format, int encoding, int characterSet) {
/* 174 */     super(MQCommonServices.jmqiEnv);
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderContext", "<init>(String,int,int)", new Object[] { format, 
/* 177 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/* 179 */     this.format = format;
/* 180 */     this.encoding = encoding;
/* 181 */     this.characterSet = characterSet;
/*     */     
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderContext", "<init>(String,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQHeaderContext() {
/* 193 */     super(MQCommonServices.jmqiEnv);
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderContext", "<init>()");
/*     */     }
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderContext", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderContext", "nextFormat()");
/*     */     }
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderContext", "nextFormat()", this.format);
/*     */     }
/* 215 */     return this.format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderContext", "nextEncoding()");
/*     */     }
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderContext", "nextEncoding()", 
/* 229 */           Integer.valueOf(this.encoding));
/*     */     }
/* 231 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderContext", "nextCharacterSet()");
/*     */     }
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderContext", "nextCharacterSet()", 
/* 245 */           Integer.valueOf(this.characterSet));
/*     */     }
/* 247 */     return this.characterSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String format) {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderContext", "setFormat(String)", "setter", format);
/*     */     }
/*     */     
/* 260 */     this.format = format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int encoding) {
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderContext", "setEncoding(int)", "setter", 
/* 272 */           Integer.valueOf(encoding));
/*     */     }
/* 274 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharacterSet(int characterSet) {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderContext", "setCharacterSet(int)", "setter", 
/* 285 */           Integer.valueOf(characterSet));
/*     */     }
/* 287 */     this.characterSet = characterSet;
/*     */   }
/*     */   
/*     */   public abstract int available() throws IOException;
/*     */   
/*     */   public abstract DataInput getDataInput();
/*     */   
/*     */   public abstract int sniff() throws IOException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQHeaderContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */