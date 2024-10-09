/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQExternalUserExit
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQExternalUserExit.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.MQExternalUserExit", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQExternalUserExit.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected String[] exitLibraries = new String[] { "" };
/*  46 */   protected String[] exitEntries = new String[] { "" };
/*  47 */   protected String[] dataStrings = new String[] { "" };
/*     */ 
/*     */ 
/*     */   
/*     */   protected int reasonCode;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewBuffer(byte[] buffer) {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "setNewBuffer(byte [ ])", "setter", buffer);
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
/*     */   protected MQExternalUserExit() {
/*  69 */     super(JmqiSESSION.getJmqiEnv());
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.MQExternalUserExit", "<init>()");
/*     */     }
/*     */     
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.mq.MQExternalUserExit", "<init>()");
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
/*     */   protected MQExternalUserExit(String libraryName, String entryPointName, String userData) {
/*  90 */     super(JmqiSESSION.getJmqiEnv());
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.MQExternalUserExit", "<init>(String,String,String)", new Object[] { libraryName, entryPointName, userData });
/*     */     }
/*     */ 
/*     */     
/*  96 */     setLibraryName(libraryName);
/*  97 */     setEntryPointName(entryPointName);
/*  98 */     setUserData(userData);
/*     */     
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.mq.MQExternalUserExit", "<init>(String,String,String)");
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
/*     */   protected byte[] userExit(MQChannelExit exitParms, MQChannelDefinition channelParms, byte[] data) {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.MQExternalUserExit", "userExit(MQChannelExit,MQChannelDefinition,byte [ ])", new Object[] { exitParms, channelParms, data });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.MQExternalUserExit", "userExit(MQChannelExit,MQChannelDefinition,byte [ ])", null);
/*     */     }
/*     */     
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntryPointName(String entryPointName) {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "setEntryPointName(String)", "setter", entryPointName);
/*     */     }
/*     */     
/* 143 */     if (entryPointName == null) {
/* 144 */       this.exitEntries[0] = "";
/*     */     } else {
/*     */       
/* 147 */       this.exitEntries[0] = entryPointName;
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
/*     */   static String[] augment(String[] array, String newString) {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry("com.ibm.mq.MQExternalUserExit", "augment(String [ ],String)", new Object[] { array, newString });
/*     */     }
/*     */ 
/*     */     
/* 165 */     int length = array.length;
/* 166 */     String[] newArray = new String[1 + length];
/* 167 */     for (int i = 0; i < length; i++) {
/* 168 */       newArray[i] = array[i];
/*     */     }
/* 170 */     newArray[length] = newString;
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit("com.ibm.mq.MQExternalUserExit", "augment(String [ ],String)", newArray);
/*     */     }
/* 174 */     return newArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLibraryName(String libraryName) {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "setLibraryName(String)", "setter", libraryName);
/*     */     }
/*     */     
/* 188 */     if (libraryName == null) {
/* 189 */       this.exitLibraries[0] = "";
/*     */     } else {
/*     */       
/* 192 */       this.exitLibraries[0] = libraryName;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserData(String userData) {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "setUserData(String)", "setter", userData);
/*     */     }
/*     */     
/* 207 */     if (userData == null) {
/* 208 */       this.dataStrings[0] = "";
/*     */     } else {
/*     */       
/* 211 */       this.dataStrings[0] = userData;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserData() {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "getUserData()", "getter", this.dataStrings[0]);
/*     */     }
/*     */     
/* 226 */     return this.dataStrings[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntryPointName() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "getEntryPointName()", "getter", this.exitEntries[0]);
/*     */     }
/*     */     
/* 239 */     return this.exitEntries[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLibraryName() {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "getLibraryName()", "getter", this.exitLibraries[0]);
/*     */     }
/*     */     
/* 253 */     return this.exitLibraries[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReasonCode() {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.MQExternalUserExit", "getReasonCode()", "getter", 
/* 265 */           Integer.valueOf(this.reasonCode));
/*     */     }
/* 267 */     return this.reasonCode;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQExternalUserExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */