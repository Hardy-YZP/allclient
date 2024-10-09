/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RFH2Folder
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH2Folder.java";
/*     */   static final int DT_STRING = 0;
/*     */   static final int DT_BOOLEAN = 1;
/*     */   static final int DT_BINHEX = 2;
/*     */   static final int DT_I1 = 3;
/*     */   static final int DT_I2 = 4;
/*     */   static final int DT_I4 = 5;
/*     */   static final int DT_I8 = 6;
/*     */   static final int DT_INT = 7;
/*     */   static final int DT_R4 = 8;
/*     */   static final int DT_R8 = 9;
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH2Folder.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private static final String[] DT_LOOKUP_TABLE = new String[] { "string", "boolean", "bin.hex", "i1", "i2", "i4", "i8", "int", "r4", "r8" };
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int DT_NONE = -1;
/*     */ 
/*     */   
/*  74 */   private String name = null;
/*  75 */   private int type = -1;
/*  76 */   private Vector children = null;
/*  77 */   private String content = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean ownRolled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RFH2Folder() {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "<init>()");
/*     */     }
/*  91 */     this.ownRolled = true;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RFH2Folder(String name) {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "<init>(String)", new Object[] { name });
/*     */     }
/*     */     
/* 107 */     this.name = name;
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setContent(String content, int type) throws Exception {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "setContent(String,int)", new Object[] { content, 
/* 122 */             Integer.valueOf(type) });
/*     */     }
/*     */     
/* 125 */     if (this.children != null) {
/* 126 */       Exception traceRet1 = new Exception("Cannot set content for a parental folder");
/* 127 */       if (Trace.isOn) {
/* 128 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "setContent(String,int)", traceRet1, 1);
/*     */       }
/*     */       
/* 131 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 135 */     if (this.ownRolled && type != -1) {
/* 136 */       Exception traceRet2 = new Exception("Cannot set typed content for ownRolled folder");
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "setContent(String,int)", traceRet2, 2);
/*     */       }
/*     */       
/* 141 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */     
/* 145 */     if (this.content != null) {
/* 146 */       Exception traceRet3 = new Exception("Content already set to '" + content + "'");
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "setContent(String,int)", traceRet3, 3);
/*     */       }
/*     */       
/* 151 */       throw traceRet3;
/*     */     } 
/*     */ 
/*     */     
/* 155 */     this.content = content;
/* 156 */     this.type = type;
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "setContent(String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setContent(String content) throws Exception {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "setContent(String)", "setter", content);
/*     */     }
/*     */     
/* 173 */     setContent(content, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addFolder(RFH2Folder child) throws Exception {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "addFolder(RFH2Folder)", new Object[] { child });
/*     */     }
/*     */ 
/*     */     
/* 187 */     if (this.ownRolled) {
/* 188 */       Exception traceRet1 = new Exception("Cannot add a folder to an ownRolled folder");
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "addFolder(RFH2Folder)", traceRet1, 1);
/*     */       }
/*     */       
/* 193 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 197 */     if (this.content != null) {
/* 198 */       Exception traceRet2 = new Exception("Cannot add a child folder to an infertile folder");
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "addFolder(RFH2Folder)", traceRet2, 2);
/*     */       }
/*     */       
/* 203 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (this.children == null) {
/* 208 */       this.children = new Vector();
/*     */     }
/*     */ 
/*     */     
/* 212 */     this.children.addElement(child);
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "addFolder(RFH2Folder)");
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
/*     */   String render() {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "render()");
/*     */     }
/* 231 */     StringBuffer buf = new StringBuffer();
/*     */ 
/*     */ 
/*     */     
/* 235 */     if (this.ownRolled) {
/* 236 */       buf.append(this.content);
/*     */     } else {
/*     */       
/* 239 */       buf.append("<" + this.name);
/*     */ 
/*     */       
/* 242 */       if (this.type != -1) {
/* 243 */         buf.append(" dt=\"" + DT_LOOKUP_TABLE[this.type] + "\"");
/*     */       }
/*     */       
/* 246 */       buf.append(">");
/*     */ 
/*     */ 
/*     */       
/* 250 */       if (this.children != null) {
/* 251 */         int numKids = this.children.size();
/* 252 */         for (int i = 0; i < numKids; i++) {
/* 253 */           RFH2Folder child = this.children.elementAt(i);
/* 254 */           buf.append(child.renderNoPad());
/*     */         } 
/*     */       } else {
/* 257 */         buf.append((this.content == null) ? "" : this.content);
/*     */       } 
/*     */ 
/*     */       
/* 261 */       buf.append("</" + this.name + ">");
/*     */     } 
/*     */ 
/*     */     
/* 265 */     int mod = buf.length() % 4;
/* 266 */     if (mod != 0) {
/* 267 */       for (int i = mod; i < 4; i++) {
/* 268 */         buf.append(" ");
/*     */       }
/*     */     }
/*     */     
/* 272 */     String traceRet1 = buf.toString();
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "render()", traceRet1);
/*     */     }
/*     */     
/* 277 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String renderNoPad() {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "renderNoPad()");
/*     */     }
/* 289 */     StringBuffer buf = new StringBuffer();
/*     */ 
/*     */     
/* 292 */     buf.append("<" + this.name);
/*     */ 
/*     */     
/* 295 */     if (this.type != -1) {
/* 296 */       buf.append(" dt=\"" + DT_LOOKUP_TABLE[this.type] + "\"");
/*     */     }
/*     */     
/* 299 */     buf.append(">");
/*     */ 
/*     */ 
/*     */     
/* 303 */     if (this.children != null) {
/* 304 */       int numKids = this.children.size();
/* 305 */       for (int i = 0; i < numKids; i++) {
/* 306 */         RFH2Folder child = this.children.elementAt(i);
/* 307 */         buf.append(child.renderNoPad());
/*     */       } 
/*     */     } else {
/* 310 */       buf.append((this.content == null) ? "" : this.content);
/*     */     } 
/*     */ 
/*     */     
/* 314 */     buf.append("</" + this.name + ">");
/*     */     
/* 316 */     String traceRet1 = buf.toString();
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2Folder", "renderNoPad()", traceRet1);
/*     */     }
/*     */     
/* 321 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\RFH2Folder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */