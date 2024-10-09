/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class WMQRFH2FolderParser
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQRFH2FolderParser.java";
/*     */   private String folder;
/*     */   private String folderName;
/*     */   private boolean isEndTag;
/*     */   private int startTag;
/*     */   private int endTag;
/*     */   private int startFolderNameStartPosition;
/*     */   private int startFolderNameEndPosition;
/*     */   private int startAttrNameStartPosition;
/*     */   private int startAttrNameEndPosition;
/*     */   private int endFolderNameStartPosition;
/*     */   private int endFolderNameEndPosition;
/*     */   private int endAttrNameStartPosition;
/*     */   private int endAttrNameEndPosition;
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQRFH2FolderParser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQRFH2FolderParser.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFolder(String in, String expectedFolderName) throws JMSException {
/*  93 */     if (in == null) {
/*     */       
/*  95 */       HashMap<String, Object> info = new HashMap<>();
/*  96 */       info.put("expectedFolderName", expectedFolderName);
/*  97 */       Trace.ffst(this, "setFolder(String, String)", "XN00R001", info, JMSException.class);
/*     */     } 
/*  99 */     this.folder = in;
/*     */ 
/*     */     
/* 102 */     this.isEndTag = nextTag(0);
/* 103 */     if (this.isEndTag) {
/* 104 */       throwBadRFH2Exception();
/*     */     } else {
/* 106 */       this.startFolderNameStartPosition = this.startTag;
/* 107 */       this.startFolderNameEndPosition = this.endTag;
/*     */     } 
/*     */ 
/*     */     
/* 111 */     this.folderName = getTagName(this.startFolderNameStartPosition, this.startFolderNameEndPosition);
/* 112 */     if (!expectedFolderName.equals(this.folderName)) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.data(this, "throwBadRFH2Exception()", "Unexpected folder name " + this.folderName + ", expected = ", expectedFolderName);
/*     */       }
/*     */       
/* 117 */       throwBadRFH2Exception();
/*     */     } 
/*     */ 
/*     */     
/* 121 */     this.isEndTag = nextTag(this.startFolderNameEndPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() throws JMSException {
/* 131 */     if (!this.isEndTag) {
/* 132 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     this.endFolderNameStartPosition = this.startTag;
/* 139 */     this.endFolderNameEndPosition = this.endTag;
/*     */ 
/*     */     
/* 142 */     String endFolderName = getEndTagName(this.endFolderNameStartPosition, this.endFolderNameEndPosition);
/* 143 */     if (!this.folderName.equals(endFolderName)) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.data(this, "throwBadRFH2Exception()", "Unexpected end folder name " + endFolderName + ", expected = ", this.folderName);
/*     */       }
/*     */       
/* 148 */       throwBadRFH2Exception();
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNextElement() throws JMSException {
/* 160 */     this.startAttrNameStartPosition = this.startTag;
/* 161 */     this.startAttrNameEndPosition = this.endTag;
/*     */ 
/*     */     
/* 164 */     this.isEndTag = nextTag(this.startAttrNameEndPosition);
/* 165 */     if (this.isEndTag) {
/* 166 */       this.endAttrNameStartPosition = this.startTag;
/* 167 */       this.endAttrNameEndPosition = this.endTag;
/*     */     } else {
/* 169 */       throwBadRFH2Exception();
/*     */     } 
/*     */ 
/*     */     
/* 173 */     String tagName = getTagName(this.startAttrNameStartPosition, this.startAttrNameEndPosition);
/* 174 */     String endTagName = getEndTagName(this.endAttrNameStartPosition, this.endAttrNameEndPosition);
/* 175 */     if (!tagName.equals(endTagName)) {
/* 176 */       throwBadRFH2Exception();
/*     */     }
/*     */ 
/*     */     
/* 180 */     this.isEndTag = nextTag(this.endAttrNameEndPosition);
/*     */     
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "getNextElement()", "Element = ", tagName);
/*     */     }
/*     */     
/* 186 */     return tagName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getElementValue() throws JMSException {
/* 197 */     String valueName = getValueName(this.startAttrNameEndPosition, this.endAttrNameStartPosition);
/*     */ 
/*     */     
/* 200 */     this.isEndTag = nextTag(this.endAttrNameEndPosition);
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.data(this, "getElementValue()", "Value = ", valueName);
/*     */     }
/*     */     
/* 206 */     return valueName;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean nextTag(int position) throws JMSException {
/* 211 */     this.startTag = this.folder.indexOf('<', position);
/* 212 */     if (this.startTag < 0) {
/* 213 */       throwBadRFH2Exception();
/*     */     }
/*     */     
/* 216 */     this.endTag = this.folder.indexOf('>', this.startTag);
/* 217 */     if (this.endTag < 0) {
/* 218 */       throwBadRFH2Exception();
/*     */     }
/*     */     
/* 221 */     return (this.folder.charAt(this.startTag + 1) == '/');
/*     */   }
/*     */ 
/*     */   
/*     */   private String getTagName(int startpos, int endpos) {
/* 226 */     int spacePosition = this.folder.indexOf(' ', startpos);
/* 227 */     int endPosition = (spacePosition < 0) ? endpos : Math.min(endpos, spacePosition);
/* 228 */     int length = endPosition - startpos;
/* 229 */     return this.folder.substring(startpos + 1, startpos + length);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getEndTagName(int startpos, int endpos) {
/* 234 */     int spacePosition = this.folder.indexOf(' ', startpos);
/* 235 */     int endPosition = (spacePosition < 0) ? endpos : Math.min(endpos, spacePosition);
/* 236 */     int length = endPosition - startpos;
/* 237 */     return this.folder.substring(startpos + 2, startpos + length);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getValueName(int startPosition, int endPosition) {
/* 242 */     return this.folder.substring(startPosition + 1, endPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   private void throwBadRFH2Exception() throws JMSException {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "throwBadRFH2Exception()", "Folder = ", this.folder);
/*     */     }
/* 250 */     JMSException je = (JMSException)NLSServices.createException("JMSCMQ1050", null);
/*     */     
/* 252 */     throw je;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQRFH2FolderParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */