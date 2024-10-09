/*      */ package com.ibm.mq.headers;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.headers.internal.store.Store;
/*      */ import com.ibm.mq.internal.MQCommonServices;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*      */ class MQDR
/*      */   extends JmqiObject
/*      */   implements MQDH.DistributionRecord
/*      */ {
/*      */   private String objectName;
/*      */   private String objectQMgrName;
/*      */   private byte[] msgId;
/*      */   private byte[] correlId;
/*      */   private byte[] groupId;
/*      */   private int feedback;
/*      */   private byte[] accountingToken;
/*      */   private int putMsgRecFields;
/*      */   
/*      */   static {
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.data("com.ibm.mq.headers.MQDR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDH.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQDR(String objectName, String objectQMgrName) {
/*  768 */     super(MQCommonServices.jmqiEnv);
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.entry(this, "com.ibm.mq.headers.MQDR", "<init>(String,String)", new Object[] { objectName, objectQMgrName });
/*      */     }
/*      */     
/*  773 */     this.objectName = objectName;
/*  774 */     this.objectQMgrName = objectQMgrName;
/*      */     
/*  776 */     if (Trace.isOn) {
/*  777 */       Trace.exit(this, "com.ibm.mq.headers.MQDR", "<init>(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQDR(String objectName, String objectQMgrName, byte[] msgId, byte[] correlId, byte[] groupId, int feedback, byte[] accountingToken) {
/*  790 */     this(objectName, objectQMgrName);
/*  791 */     if (Trace.isOn) {
/*  792 */       Trace.entry(this, "com.ibm.mq.headers.MQDR", "<init>(String,String,byte [ ],byte [ ],byte [ ],int,byte [ ])", new Object[] { objectName, objectQMgrName, msgId, correlId, groupId, 
/*      */             
/*  794 */             Integer.valueOf(feedback), accountingToken });
/*      */     }
/*  796 */     this.msgId = msgId;
/*  797 */     this.correlId = correlId;
/*  798 */     this.groupId = groupId;
/*  799 */     this.feedback = feedback;
/*  800 */     this.accountingToken = accountingToken;
/*      */     
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.exit(this, "com.ibm.mq.headers.MQDR", "<init>(String,String,byte [ ],byte [ ],byte [ ],int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQDR(Store store, int objectRecOffsetArg, int putMsgRecOffsetArg, int putMsgRecFields) throws IOException {
/*  811 */     super(MQCommonServices.jmqiEnv);
/*  812 */     if (Trace.isOn) {
/*  813 */       Trace.entry(this, "com.ibm.mq.headers.MQDR", "<init>(Store,int,int,int)", new Object[] { store, 
/*  814 */             Integer.valueOf(objectRecOffsetArg), Integer.valueOf(putMsgRecOffsetArg), 
/*  815 */             Integer.valueOf(putMsgRecFields) });
/*      */     }
/*  817 */     this.putMsgRecFields = putMsgRecFields;
/*      */     
/*  819 */     int objectRecOffset = objectRecOffsetArg;
/*  820 */     int putMsgRecOffset = putMsgRecOffsetArg;
/*      */     
/*  822 */     this
/*  823 */       .objectName = store.getString(null, objectRecOffset, 48, 1208);
/*  824 */     objectRecOffset += 48;
/*  825 */     this.objectQMgrName = store.getString(null, objectRecOffset, 48, 1208);
/*      */     
/*  827 */     objectRecOffset += 48;
/*      */     
/*  829 */     if ((putMsgRecFields & 0x1) == 1) {
/*  830 */       this.msgId = store.getBytes(null, putMsgRecOffset, CMQC.MQMI_NONE.length);
/*  831 */       putMsgRecOffset += CMQC.MQMI_NONE.length;
/*      */     } 
/*      */     
/*  834 */     if ((putMsgRecFields & 0x2) == 2) {
/*  835 */       this.correlId = store.getBytes(null, putMsgRecOffset, CMQC.MQCI_NONE.length);
/*  836 */       putMsgRecOffset += CMQC.MQCI_NONE.length;
/*      */     } 
/*      */     
/*  839 */     if ((putMsgRecFields & 0x4) == 4) {
/*  840 */       this.groupId = store.getBytes(null, putMsgRecOffset, CMQC.MQGI_NONE.length);
/*  841 */       putMsgRecOffset += CMQC.MQGI_NONE.length;
/*      */     } 
/*      */     
/*  844 */     if ((putMsgRecFields & 0x8) == 8) {
/*  845 */       this.feedback = store.getInt(null, putMsgRecOffset);
/*  846 */       putMsgRecOffset += 4;
/*      */     } 
/*      */     
/*  849 */     if ((putMsgRecFields & 0x10) == 16) {
/*  850 */       this.accountingToken = store.getBytes(null, putMsgRecOffset, 16);
/*  851 */       putMsgRecOffset += CMQC.MQACT_NONE.length;
/*      */     } 
/*      */     
/*  854 */     if (Trace.isOn) {
/*  855 */       Trace.exit(this, "com.ibm.mq.headers.MQDR", "<init>(Store,int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   int getMQPMRSize() {
/*  861 */     int traceRet1 = getPmrSize(this.putMsgRecFields);
/*  862 */     if (Trace.isOn) {
/*  863 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getMQPMRSize()", "getter", 
/*  864 */           Integer.valueOf(traceRet1));
/*      */     }
/*  866 */     return traceRet1;
/*      */   }
/*      */   
/*      */   int writeTo(Store store, int offset, int putMsgRecFieldsArg) throws IOException {
/*  870 */     if (Trace.isOn) {
/*  871 */       Trace.entry(this, "com.ibm.mq.headers.MQDR", "writeTo(Store,int,int)", new Object[] { store, 
/*  872 */             Integer.valueOf(offset), Integer.valueOf(putMsgRecFieldsArg) });
/*      */     }
/*  874 */     int pos = offset;
/*      */     
/*  876 */     store.setString(pos, this.objectName, 48, 1208);
/*  877 */     pos += 48;
/*  878 */     store.setString(pos, this.objectQMgrName, 48, 1208);
/*  879 */     pos += 48;
/*      */     
/*  881 */     if ((putMsgRecFieldsArg & 0x1) == 1) {
/*  882 */       store.setBytes(pos, this.msgId, CMQC.MQMI_NONE.length);
/*  883 */       pos += CMQC.MQMI_NONE.length;
/*      */     } 
/*      */     
/*  886 */     if ((putMsgRecFieldsArg & 0x2) == 2) {
/*  887 */       store.setBytes(pos, this.correlId, CMQC.MQCI_NONE.length);
/*  888 */       pos += CMQC.MQCI_NONE.length;
/*      */     } 
/*      */     
/*  891 */     if ((putMsgRecFieldsArg & 0x4) == 4) {
/*  892 */       store.setBytes(pos, this.groupId, CMQC.MQGI_NONE.length);
/*  893 */       pos += CMQC.MQGI_NONE.length;
/*      */     } 
/*      */     
/*  896 */     if ((putMsgRecFieldsArg & 0x8) == 8) {
/*  897 */       store.setInt(pos, this.feedback);
/*  898 */       pos += 4;
/*      */     } 
/*      */     
/*  901 */     if ((putMsgRecFieldsArg & 0x10) == 16) {
/*  902 */       store.setBytes(pos, this.accountingToken, 16);
/*  903 */       pos += CMQC.MQACT_NONE.length;
/*      */     } 
/*      */     
/*  906 */     int traceRet1 = pos - offset;
/*      */     
/*  908 */     if (Trace.isOn) {
/*  909 */       Trace.exit(this, "com.ibm.mq.headers.MQDR", "writeTo(Store,int,int)", 
/*  910 */           Integer.valueOf(traceRet1));
/*      */     }
/*  912 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getObjectName() {
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getObjectName()", "getter", this.objectName);
/*      */     }
/*  923 */     return this.objectName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getObjectQMgrName() {
/*  931 */     if (Trace.isOn) {
/*  932 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getObjectQMgrName()", "getter", this.objectQMgrName);
/*      */     }
/*      */     
/*  935 */     return this.objectQMgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgId() {
/*  943 */     if (Trace.isOn) {
/*  944 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getMsgId()", "getter", this.msgId);
/*      */     }
/*  946 */     return this.msgId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getCorrelId() {
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getCorrelId()", "getter", this.correlId);
/*      */     }
/*  957 */     return this.correlId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getGroupId() {
/*  965 */     if (Trace.isOn) {
/*  966 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getGroupId()", "getter", this.groupId);
/*      */     }
/*  968 */     return this.groupId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFeedback() {
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getFeedback()", "getter", 
/*  978 */           Integer.valueOf(this.feedback));
/*      */     }
/*  980 */     return this.feedback;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getAccountingToken() {
/*  988 */     if (Trace.isOn) {
/*  989 */       Trace.data(this, "com.ibm.mq.headers.MQDR", "getAccountingToken()", "getter", this.accountingToken);
/*      */     }
/*      */     
/*  992 */     return this.accountingToken;
/*      */   }
/*      */   
/*      */   static int getMQORSize() {
/*  996 */     int traceRet1 = 96;
/*  997 */     if (Trace.isOn) {
/*  998 */       Trace.data("com.ibm.mq.headers.MQDR", "getMQORSize()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*      */     
/* 1001 */     return traceRet1;
/*      */   }
/*      */   
/*      */   static int getPmrSize(int flags) {
/* 1005 */     if (Trace.isOn)
/* 1006 */       Trace.entry("com.ibm.mq.headers.MQDR", "getPmrSize(int)", new Object[] {
/* 1007 */             Integer.valueOf(flags)
/*      */           }); 
/* 1009 */     int size = 0;
/*      */     
/* 1011 */     if ((flags & 0x1) == 1) {
/* 1012 */       size += CMQC.MQMI_NONE.length;
/*      */     }
/*      */     
/* 1015 */     if ((flags & 0x2) == 2) {
/* 1016 */       size += CMQC.MQCI_NONE.length;
/*      */     }
/*      */     
/* 1019 */     if ((flags & 0x4) == 4) {
/* 1020 */       size += CMQC.MQGI_NONE.length;
/*      */     }
/*      */     
/* 1023 */     if ((flags & 0x8) == 8) {
/* 1024 */       size += 4;
/*      */     }
/*      */     
/* 1027 */     if ((flags & 0x10) == 16) {
/* 1028 */       size += CMQC.MQACT_NONE.length;
/*      */     }
/*      */     
/* 1031 */     if (Trace.isOn) {
/* 1032 */       Trace.exit("com.ibm.mq.headers.MQDR", "getPmrSize(int)", Integer.valueOf(size));
/*      */     }
/* 1034 */     return size;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQDR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */