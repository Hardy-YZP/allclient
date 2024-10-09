/*      */ package com.ibm.mq.headers.internal;
/*      */ 
/*      */ import com.ibm.mq.headers.internal.store.Store;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class DataOutputWrapper
/*      */   extends MessageWrapper
/*      */ {
/*      */   static {
/*  842 */     if (Trace.isOn) {
/*  843 */       Trace.data("com.ibm.mq.headers.internal.DataOutputWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MessageWrapper.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  849 */   DataOutput delegate = null;
/*      */   
/*      */   DataOutputWrapper(DataOutput out) {
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "<init>(DataOutput)", new Object[] { out });
/*      */     }
/*      */     
/*  856 */     this.delegate = out;
/*  857 */     if (Trace.isOn) {
/*  858 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "<init>(DataOutput)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDataOffset() {
/*  866 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  867 */     if (Trace.isOn) {
/*  868 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getDataOffset()", traceRet1);
/*      */     }
/*      */     
/*  871 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void seek(int offset) {
/*  876 */     if (Trace.isOn) {
/*  877 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "seek(int)", new Object[] {
/*  878 */             Integer.valueOf(offset)
/*      */           });
/*      */     }
/*  881 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "seek(int)", traceRet1);
/*      */     }
/*      */     
/*  886 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeBytes(String s) throws IOException {
/*  891 */     if (Trace.isOn) {
/*  892 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "writeBytes(String)", new Object[] { s });
/*      */     }
/*      */     
/*  895 */     this.delegate.writeBytes(s);
/*  896 */     if (Trace.isOn) {
/*  897 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "writeBytes(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int readInt() throws IOException {
/*  904 */     if (Trace.isOn) {
/*  905 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readInt()");
/*      */     }
/*      */     
/*  908 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  909 */     if (Trace.isOn) {
/*  910 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readInt()", traceRet1);
/*      */     }
/*      */     
/*  913 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeString(String string) throws IOException {
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "writeString(String)", new Object[] { string });
/*      */     }
/*      */     
/*  922 */     this.delegate.writeBytes(string);
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "writeString(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeInt(int i) throws IOException {
/*  931 */     if (Trace.isOn)
/*  932 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "writeInt(int)", new Object[] {
/*  933 */             Integer.valueOf(i)
/*      */           }); 
/*  935 */     this.delegate.writeInt(i);
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "writeInt(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCharacterSet() {
/*  944 */     if (Trace.isOn) {
/*  945 */       Trace.data(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getCharacterSet()", "getter", 
/*  946 */           Integer.valueOf(0));
/*      */     }
/*  948 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  953 */     if (Trace.isOn) {
/*  954 */       Trace.data(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getEncoding()", "getter", 
/*  955 */           Integer.valueOf(273));
/*      */     }
/*  957 */     return 273;
/*      */   }
/*      */ 
/*      */   
/*      */   public Store getStore(int encoding, int characterSet, int size) throws IOException {
/*  962 */     if (Trace.isOn) {
/*  963 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getStore(int,int,int)", new Object[] {
/*  964 */             Integer.valueOf(encoding), Integer.valueOf(characterSet), 
/*  965 */             Integer.valueOf(size)
/*      */           });
/*      */     }
/*  968 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getStore(int,int,int)", traceRet1);
/*      */     }
/*      */     
/*  973 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DataOutput getReversed() {
/*  979 */     if (Trace.isOn) {
/*  980 */       Trace.data(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getReversed()", "getter", this.delegate);
/*      */     }
/*      */     
/*  983 */     return this.delegate;
/*      */   }
/*      */ 
/*      */   
/*      */   public void readFully(byte[] b, int off, int len) {
/*  988 */     if (Trace.isOn) {
/*  989 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readFully(byte [ ],int,int)", new Object[] { b, 
/*  990 */             Integer.valueOf(off), Integer.valueOf(len) });
/*      */     }
/*      */ 
/*      */     
/*  994 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  995 */     if (Trace.isOn) {
/*  996 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readFully(byte [ ],int,int)", traceRet1);
/*      */     }
/*      */     
/*  999 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void readFully(byte[] b) {
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readFully(byte [ ])", new Object[] { b });
/*      */     }
/*      */ 
/*      */     
/* 1009 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1010 */     if (Trace.isOn) {
/* 1011 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readFully(byte [ ])", traceRet1);
/*      */     }
/*      */     
/* 1014 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void write(byte[] b, int off, int len) throws IOException {
/* 1019 */     if (Trace.isOn) {
/* 1020 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "write(byte [ ],int,int)", new Object[] { b, 
/* 1021 */             Integer.valueOf(off), Integer.valueOf(len) });
/*      */     }
/* 1023 */     this.delegate.write(b, off, len);
/* 1024 */     if (Trace.isOn) {
/* 1025 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "write(byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(byte[] b) throws IOException {
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "write(byte [ ])", new Object[] { b });
/*      */     }
/*      */     
/* 1037 */     this.delegate.write(b);
/* 1038 */     if (Trace.isOn) {
/* 1039 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "write(byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/* 1046 */     if (Trace.isOn) {
/* 1047 */       Trace.data(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getFormat()", "getter", "        ");
/*      */     }
/*      */     
/* 1050 */     return "        ";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTotalMessageLength() {
/* 1056 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1057 */     if (Trace.isOn) {
/* 1058 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getTotalMessageLength()", traceRet1);
/*      */     }
/*      */     
/* 1061 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void shuffle(int from, int to, int length) throws IOException {
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "shuffle(int,int,int)", new Object[] {
/* 1068 */             Integer.valueOf(from), Integer.valueOf(to), Integer.valueOf(length)
/*      */           });
/*      */     }
/* 1071 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1072 */     if (Trace.isOn) {
/* 1073 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "shuffle(int,int,int)", traceRet1);
/*      */     }
/*      */     
/* 1076 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void resizeBuffer(int size) {
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "resizeBuffer(int)", new Object[] {
/* 1083 */             Integer.valueOf(size)
/*      */           });
/*      */     }
/* 1086 */     if (Trace.isOn) {
/* 1087 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "resizeBuffer(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMQMessage() {
/* 1094 */     if (Trace.isOn) {
/* 1095 */       Trace.data(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "isMQMessage()", "getter", 
/* 1096 */           Boolean.valueOf(false));
/*      */     }
/* 1098 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMessageType() throws IOException {
/* 1104 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1105 */     if (Trace.isOn) {
/* 1106 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getMessageType()", traceRet1);
/*      */     }
/*      */     
/* 1109 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean readBoolean() throws IOException {
/* 1114 */     if (Trace.isOn) {
/* 1115 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readBoolean()");
/*      */     }
/*      */     
/* 1118 */     if (Trace.isOn) {
/* 1119 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readBoolean()", 
/* 1120 */           Boolean.valueOf(false));
/*      */     }
/* 1122 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public byte readByte() throws IOException {
/* 1127 */     if (Trace.isOn) {
/* 1128 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readByte()");
/*      */     }
/*      */     
/* 1131 */     if (Trace.isOn) {
/* 1132 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readByte()", 
/* 1133 */           Byte.valueOf((byte)0));
/*      */     }
/* 1135 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public char readChar() throws IOException {
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readChar()");
/*      */     }
/*      */     
/* 1144 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1145 */     if (Trace.isOn) {
/* 1146 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readChar()", traceRet1);
/*      */     }
/*      */     
/* 1149 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public double readDouble() throws IOException {
/* 1154 */     if (Trace.isOn) {
/* 1155 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readDouble()");
/*      */     }
/*      */     
/* 1158 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1159 */     if (Trace.isOn) {
/* 1160 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readDouble()", traceRet1);
/*      */     }
/*      */     
/* 1163 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float readFloat() throws IOException {
/* 1168 */     if (Trace.isOn) {
/* 1169 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readFloat()");
/*      */     }
/*      */     
/* 1172 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1173 */     if (Trace.isOn) {
/* 1174 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readFloat()", traceRet1);
/*      */     }
/*      */     
/* 1177 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public String readLine() throws IOException {
/* 1182 */     if (Trace.isOn) {
/* 1183 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readLine()");
/*      */     }
/*      */     
/* 1186 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1187 */     if (Trace.isOn) {
/* 1188 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readLine()", traceRet1);
/*      */     }
/*      */     
/* 1191 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public long readLong() throws IOException {
/* 1196 */     if (Trace.isOn) {
/* 1197 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readLong()");
/*      */     }
/*      */     
/* 1200 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1201 */     if (Trace.isOn) {
/* 1202 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readLong()", traceRet1);
/*      */     }
/*      */     
/* 1205 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public short readShort() throws IOException {
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readShort()");
/*      */     }
/*      */     
/* 1214 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1215 */     if (Trace.isOn) {
/* 1216 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readShort()", traceRet1);
/*      */     }
/*      */     
/* 1219 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public String readUTF() throws IOException {
/* 1224 */     if (Trace.isOn) {
/* 1225 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readUTF()");
/*      */     }
/*      */     
/* 1228 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1229 */     if (Trace.isOn) {
/* 1230 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readUTF()", traceRet1);
/*      */     }
/*      */     
/* 1233 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int readUnsignedByte() throws IOException {
/* 1238 */     if (Trace.isOn) {
/* 1239 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readUnsignedByte()");
/*      */     }
/*      */     
/* 1242 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1243 */     if (Trace.isOn) {
/* 1244 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readUnsignedByte()", traceRet1);
/*      */     }
/*      */     
/* 1247 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int readUnsignedShort() throws IOException {
/* 1252 */     if (Trace.isOn) {
/* 1253 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readUnsignedShort()");
/*      */     }
/*      */     
/* 1256 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1257 */     if (Trace.isOn) {
/* 1258 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "readUnsignedShort()", traceRet1);
/*      */     }
/*      */     
/* 1261 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int skipBytes(int n) throws IOException {
/* 1266 */     if (Trace.isOn) {
/* 1267 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "skipBytes(int)", new Object[] {
/* 1268 */             Integer.valueOf(n)
/*      */           });
/*      */     }
/* 1271 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 1272 */     if (Trace.isOn) {
/* 1273 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "skipBytes(int)", traceRet1);
/*      */     }
/*      */     
/* 1276 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getDelegate() {
/* 1281 */     if (Trace.isOn) {
/* 1282 */       Trace.data(this, "com.ibm.mq.headers.internal.DataOutputWrapper", "getDelegate()", "getter", this.delegate);
/*      */     }
/*      */     
/* 1285 */     return this.delegate;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\DataOutputWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */