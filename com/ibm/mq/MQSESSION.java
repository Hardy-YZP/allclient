/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.internal.MQCommonServices;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.MQSTS;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Hashtable;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class MQSESSION
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSESSION.java";
/*      */   
/*      */   static {
/*   68 */     if (Trace.isOn) {
/*   69 */       Trace.data("com.ibm.mq.MQSESSION", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSESSION.java");
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
/*      */   
/*   84 */   protected MQManagedConnectionJ11 mqManCon = null;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int ENC_ASCII = 1;
/*      */ 
/*      */   
/*      */   protected static final int ENC_EBCDIC = 2;
/*      */ 
/*      */   
/*      */   protected boolean authenticateBindings = false;
/*      */ 
/*      */   
/*      */   private JmqiMQ jMQI;
/*      */ 
/*      */   
/*      */   private static final int CHARINITARRAYSIZE = 100;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAuthenticateBindings() {
/*  105 */     if (Trace.isOn) {
/*  106 */       Trace.data(this, "com.ibm.mq.MQSESSION", "getAuthenticateBindings()", "getter", 
/*  107 */           Boolean.valueOf(this.authenticateBindings));
/*      */     }
/*  109 */     return this.authenticateBindings;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAuthenticateBindings(boolean auth) {
/*  119 */     if (Trace.isOn) {
/*  120 */       Trace.data(this, "com.ibm.mq.MQSESSION", "setAuthenticateBindings(boolean)", "setter", 
/*  121 */           Boolean.valueOf(auth));
/*      */     }
/*  123 */     this.authenticateBindings = auth;
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
/*      */   
/*      */   public static JmqiEnvironment getJmqiEnv() {
/*  138 */     if (Trace.isOn) {
/*  139 */       Trace.data("com.ibm.mq.MQSESSION", "getJmqiEnv()", "getter", MQCommonServices.jmqiEnv);
/*      */     }
/*  141 */     return MQCommonServices.jmqiEnv;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQBACK(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  730 */     if (Trace.isOn) {
/*  731 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQBACK(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/*  735 */     this.jMQI.MQBACK(hconn, pCompCode, pReason);
/*  736 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQBACK(Hconn,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQBEGIN(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  761 */     if (Trace.isOn) {
/*  762 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQBEGIN(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/*  766 */     this.jMQI.MQBEGIN(hconn, null, pCompCode, pReason);
/*  767 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQBEGIN(Hconn,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQCLOSE(Hconn hconn, Phobj pHobj, int Options, Pint pCompCode, Pint pReason) {
/*  795 */     if (Trace.isOn) {
/*  796 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, pHobj, 
/*  797 */             Integer.valueOf(Options), pCompCode, pReason });
/*      */     }
/*      */     
/*  800 */     this.jMQI.MQCLOSE(hconn, pHobj, Options, pCompCode, pReason);
/*  801 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  803 */     if (Trace.isOn) {
/*  804 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQCMIT(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  830 */     if (Trace.isOn) {
/*  831 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQCMIT(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/*  835 */     this.jMQI.MQCMIT(hconn, pCompCode, pReason);
/*  836 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQCMIT(Hconn,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQCONNX_j(String pName, JmqiConnectOptions jmqiCno, MQConnectionOptions mqcno, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQCONNX_j(String,JmqiConnectOptions,MQConnectionOptions,Phconn,Pint,Pint)", new Object[] { pName, jmqiCno, mqcno, pHconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  903 */     if (mqcno == null) {
/*  904 */       pCompCode.x = 2;
/*  905 */       pReason.x = 2139;
/*      */       
/*  907 */       if (Trace.isOn) {
/*  908 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQCONNX_j(String,JmqiConnectOptions,MQConnectionOptions,Phconn,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  915 */     JmqiSP sp = (JmqiSP)this.jMQI;
/*  916 */     sp.jmqiConnect(pName, jmqiCno, mqcno.getJMQIStructure(), null, pHconn, pCompCode, pReason);
/*      */ 
/*      */     
/*  919 */     if (pCompCode.x == 1 || pCompCode.x == 0) {
/*  920 */       String userid = jmqiCno.getUserIdentifier();
/*  921 */       if (userid != null && !"".equals(userid.trim())) {
/*  922 */         Hconn hconn = pHconn.getHconn();
/*  923 */         String password = jmqiCno.getPassword();
/*  924 */         this.jMQI.authenticate(hconn, userid, password, pCompCode, pReason);
/*  925 */         if (pCompCode.x != 0) {
/*  926 */           Pint cc2 = this.env.newPint();
/*  927 */           Pint rc2 = this.env.newPint();
/*  928 */           this.jMQI.MQDISC(pHconn, cc2, rc2);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  933 */     errorOccured(pHconn, pCompCode.x, pReason.x);
/*      */     
/*  935 */     if (Trace.isOn) {
/*  936 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQCONNX_j(String,JmqiConnectOptions,MQConnectionOptions,Phconn,Pint,Pint)", 2);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQDISC(Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQDISC(Phconn,Pint,Pint)", new Object[] { pHconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  969 */     this.jMQI.MQDISC(pHconn, pCompCode, pReason);
/*  970 */     errorOccured(pHconn, pCompCode.x, pReason.x);
/*      */     
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQDISC(Phconn,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQGET(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQGetMessageOptions pGetMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pGetMsgOpts, 
/*      */             
/* 1007 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1011 */     if (pBuffer == null) {
/* 1012 */       pCompCode.x = 2;
/* 1013 */       pReason.x = 2004;
/*      */       
/* 1015 */       if (Trace.isOn) {
/* 1016 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 1025 */     if (pGetMsgOpts == null) {
/* 1026 */       pCompCode.x = 2;
/* 1027 */       pReason.x = 2186;
/* 1028 */       if (Trace.isOn) {
/* 1029 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1034 */     MQGMO jmqiGMO = pGetMsgOpts.getJMQIStructure();
/*      */ 
/*      */ 
/*      */     
/* 1038 */     if (pMsgDesc == null) {
/* 1039 */       pCompCode.x = 2;
/* 1040 */       pReason.x = 2026;
/* 1041 */       if (Trace.isOn) {
/* 1042 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1047 */     MQMD jmqiMD = pMsgDesc.getJMQIStructure(0);
/*      */ 
/*      */     
/* 1050 */     this.jMQI.MQGET(hconn, hobj, jmqiMD, jmqiGMO, BufferLength, pBuffer, pDataLength, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */     
/* 1054 */     pGetMsgOpts.updateFromJMQIStructure();
/* 1055 */     pMsgDesc.updateFromJMQIStructure(0);
/*      */     
/* 1057 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */ 
/*      */     
/* 1060 */     if (Trace.isOn) {
/* 1061 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 4);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQGET(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQGetMessageOptions pGetMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/* 1094 */     if (Trace.isOn) {
/* 1095 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pGetMsgOpts, 
/*      */             
/* 1097 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */     
/* 1100 */     String fid = "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)";
/*      */     
/* 1102 */     if (pBuffer == null) {
/* 1103 */       pCompCode.x = 2;
/* 1104 */       pReason.x = 2004;
/*      */       
/* 1106 */       if (Trace.isOn) {
/* 1107 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1114 */     if (pGetMsgOpts == null) {
/* 1115 */       pCompCode.x = 2;
/* 1116 */       pReason.x = 2186;
/* 1117 */       if (Trace.isOn) {
/* 1118 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1123 */     MQGMO jmqiGMO = pGetMsgOpts.getJMQIStructure();
/*      */ 
/*      */     
/* 1126 */     int originalOptions = jmqiGMO.getOptions();
/* 1127 */     if ((originalOptions & 0x1006) == 0) {
/* 1128 */       if (Trace.isOn) {
/* 1129 */         Trace.data(this, "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", "No SyncPoint action specified- implicit MQGMO_NO_SYNCPOINT...", "");
/*      */       }
/* 1131 */       jmqiGMO.setOptions(originalOptions | 0x4);
/*      */     } 
/*      */ 
/*      */     
/* 1135 */     this.jMQI.MQGET(hconn, hobj, pMsg2.getJMQIStructure(), jmqiGMO, BufferLength, pBuffer, pDataLength, pCompCode, pReason);
/*      */ 
/*      */     
/* 1138 */     jmqiGMO.setOptions(originalOptions);
/*      */ 
/*      */ 
/*      */     
/* 1142 */     pGetMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1144 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1146 */     if (Trace.isOn) {
/* 1147 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 3);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQINQ(Hconn hconn, Hobj hobj, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, Pint pCompCode, Pint pReason) {
/* 1181 */     if (Trace.isOn) {
/* 1182 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/* 1184 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/* 1185 */             Integer.valueOf(CharAttrLength), pCharAttrs, pCompCode, pReason });
/*      */     }
/*      */     
/* 1188 */     this.jMQI.MQINQ(hconn, hobj, SelectorCount, pSelectors, IntAttrCount, pIntAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/* 1189 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1191 */     if (Trace.isOn) {
/* 1192 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
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
/*      */   
/*      */   protected String MQINQ(Hconn hconn, Hobj hobj, int Selector, int AttrLength, Pint pCompCode, Pint pReason) {
/* 1208 */     if (Trace.isOn) {
/* 1209 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", new Object[] { hconn, hobj, 
/* 1210 */             Integer.valueOf(Selector), Integer.valueOf(AttrLength), pCompCode, pReason });
/*      */     }
/*      */     
/* 1213 */     String fid = "MQINQ(Hconn,Hobj,int,int,Pint,Pint)";
/*      */     
/* 1215 */     if (Trace.isOn) {
/* 1216 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", "STRANGE: 390/400 version of MQINQ called on client", "");
/*      */     }
/*      */     
/* 1219 */     if (Trace.isOn) {
/* 1220 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", null);
/*      */     }
/* 1222 */     return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQOPEN(Hconn hconn, MQOD pObjDesc, int Options, Phobj pHobj, Pint pCompCode, Pint pReason) {
/* 1249 */     if (Trace.isOn) {
/* 1250 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, pObjDesc, 
/* 1251 */             Integer.valueOf(Options), pHobj, pCompCode, pReason });
/*      */     }
/*      */     
/* 1254 */     if (pObjDesc == null) {
/* 1255 */       pCompCode.x = 2;
/* 1256 */       pReason.x = 2044;
/*      */       
/* 1258 */       if (Trace.isOn) {
/* 1259 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 1);
/*      */       }
/*      */       return;
/*      */     } 
/* 1263 */     MQOD jmqiOD = pObjDesc.getJMQIStructure();
/*      */ 
/*      */     
/* 1266 */     this.jMQI.MQOPEN(hconn, jmqiOD, Options, pHobj, pCompCode, pReason);
/*      */ 
/*      */     
/* 1269 */     pObjDesc.updateFromJMQIStructure();
/*      */     
/* 1271 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1273 */     if (Trace.isOn) {
/* 1274 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 2);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQPUT(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1300 */     if (Trace.isOn) {
/* 1301 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, 
/*      */             
/* 1303 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/* 1305 */     String fid = "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)";
/*      */     
/* 1307 */     if (pBuffer == null) {
/* 1308 */       pCompCode.x = 2;
/* 1309 */       pReason.x = 2004;
/*      */       
/* 1311 */       if (Trace.isOn) {
/* 1312 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1319 */     if (pPutMsgOpts == null) {
/* 1320 */       pCompCode.x = 2;
/* 1321 */       pReason.x = 2173;
/* 1322 */       if (Trace.isOn) {
/* 1323 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1328 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1329 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1332 */     if ((originalOptions & 0x6) == 0) {
/* 1333 */       if (Trace.isOn) {
/* 1334 */         Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", "No SyncPoint action specified- implicit MQPMO_NO_SYNCPOINT...", "");
/*      */       }
/* 1336 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     } 
/*      */ 
/*      */     
/* 1340 */     if (pMsgDesc == null) {
/* 1341 */       pCompCode.x = 2;
/* 1342 */       pReason.x = 2026;
/* 1343 */       if (Trace.isOn) {
/* 1344 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1349 */     MQMD jmqiMD = pMsgDesc.getJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1352 */     this.jMQI.MQPUT(hconn, hobj, jmqiMD, jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1357 */     pMsgDesc.updateFromJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1360 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1363 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1365 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1367 */     if (Trace.isOn) {
/* 1368 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 4);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQPUT(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1396 */     if (Trace.isOn) {
/* 1397 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pPutMsgOpts, 
/*      */             
/* 1399 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */     
/* 1402 */     String fid = "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)";
/*      */     
/* 1404 */     if (pBuffer == null) {
/* 1405 */       pCompCode.x = 2;
/* 1406 */       pReason.x = 2004;
/*      */       
/* 1408 */       if (Trace.isOn) {
/* 1409 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1416 */     if (pPutMsgOpts == null) {
/* 1417 */       pCompCode.x = 2;
/* 1418 */       pReason.x = 2173;
/* 1419 */       if (Trace.isOn) {
/* 1420 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1425 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1426 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1429 */     if ((originalOptions & 0x6) == 0) {
/* 1430 */       if (Trace.isOn) {
/* 1431 */         Trace.data(this, "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", "No SyncPoint action specified- implicit MQPMO_NO_SYNCPOINT...", "");
/*      */       }
/* 1433 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     } 
/*      */ 
/*      */     
/* 1437 */     this.jMQI.MQPUT(hconn, hobj, pMsg2.getJMQIStructure(), jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */     
/* 1440 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1443 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1445 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1447 */     if (Trace.isOn) {
/* 1448 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQPUT1(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1475 */     if (Trace.isOn) {
/* 1476 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, 
/*      */             
/* 1478 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */     
/* 1481 */     String fid = "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)";
/*      */     
/* 1483 */     if (pBuffer == null) {
/* 1484 */       pCompCode.x = 2;
/* 1485 */       pReason.x = 2004;
/*      */       
/* 1487 */       if (Trace.isOn) {
/* 1488 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1495 */     if (pObjDesc == null) {
/* 1496 */       pCompCode.x = 2;
/* 1497 */       pReason.x = 2044;
/* 1498 */       if (Trace.isOn) {
/* 1499 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1504 */     MQOD jmqiOD = pObjDesc.getJMQIStructure();
/*      */ 
/*      */     
/* 1507 */     if (pPutMsgOpts == null) {
/* 1508 */       pCompCode.x = 2;
/* 1509 */       pReason.x = 2173;
/* 1510 */       if (Trace.isOn) {
/* 1511 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1516 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1517 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1520 */     if ((originalOptions & 0x6) == 0) {
/* 1521 */       if (Trace.isOn) {
/* 1522 */         Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", "No SyncPoint action specified- implicit MQPMO_NO_SYNCPOINT...", "");
/*      */       }
/* 1524 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     } 
/*      */ 
/*      */     
/* 1528 */     if (pMsgDesc == null) {
/* 1529 */       pCompCode.x = 2;
/* 1530 */       pReason.x = 2026;
/* 1531 */       if (Trace.isOn) {
/* 1532 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1537 */     MQMD jmqiMD = pMsgDesc.getJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1540 */     this.jMQI.MQPUT1(hconn, jmqiOD, jmqiMD, jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1545 */     pMsgDesc.updateFromJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1548 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1551 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */ 
/*      */     
/* 1554 */     pObjDesc.updateFromJMQIStructure();
/*      */     
/* 1556 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1558 */     if (Trace.isOn) {
/* 1559 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 5);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQPUT1(Hconn hconn, MQOD pObjDesc, MQMsg2 pMsg2, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1587 */     if (Trace.isOn) {
/* 1588 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, pObjDesc, pMsg2, pPutMsgOpts, 
/*      */             
/* 1590 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */     
/* 1593 */     String fid = "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)";
/*      */     
/* 1595 */     if (pBuffer == null) {
/* 1596 */       pCompCode.x = 2;
/* 1597 */       pReason.x = 2004;
/*      */       
/* 1599 */       if (Trace.isOn) {
/* 1600 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1607 */     if (pObjDesc == null) {
/* 1608 */       pCompCode.x = 2;
/* 1609 */       pReason.x = 2044;
/* 1610 */       if (Trace.isOn) {
/* 1611 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1616 */     MQOD jmqiOD = pObjDesc.getJMQIStructure();
/*      */ 
/*      */     
/* 1619 */     if (pPutMsgOpts == null) {
/* 1620 */       pCompCode.x = 2;
/* 1621 */       pReason.x = 2173;
/* 1622 */       if (Trace.isOn) {
/* 1623 */         Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1628 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1629 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1632 */     if ((originalOptions & 0x6) == 0) {
/* 1633 */       if (Trace.isOn) {
/* 1634 */         Trace.data(this, "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", "No SyncPoint action specified- implicit MQPMO_NO_SYNCPOINT...", "");
/*      */       }
/* 1636 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     } 
/*      */ 
/*      */     
/* 1640 */     this.jMQI.MQPUT1(hconn, jmqiOD, pMsg2.getJMQIStructure(), jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */     
/* 1643 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1646 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */ 
/*      */     
/* 1649 */     pObjDesc.updateFromJMQIStructure();
/*      */     
/* 1651 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1653 */     if (Trace.isOn) {
/* 1654 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 4);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void MQSET(Hconn hconn, Hobj hobj, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, Pint pCompCode, Pint pReason) {
/* 1686 */     if (Trace.isOn) {
/* 1687 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/* 1689 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/* 1690 */             Integer.valueOf(CharAttrLength), pCharAttrs, pCompCode, pReason });
/*      */     }
/*      */     
/* 1693 */     this.jMQI.MQSET(hconn, hobj, SelectorCount, pSelectors, IntAttrCount, pIntAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/* 1694 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1696 */     if (Trace.isOn) {
/* 1697 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQSET(Hconn hconn, Hobj hobj, int Selector, String Attr, int AttrLength, Pint pCompCode, Pint pReason) {
/* 1721 */     if (Trace.isOn) {
/* 1722 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)", new Object[] { hconn, hobj, 
/* 1723 */             Integer.valueOf(Selector), Attr, Integer.valueOf(AttrLength), pCompCode, pReason });
/*      */     }
/*      */     
/* 1726 */     String fid = "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)";
/*      */ 
/*      */     
/* 1729 */     if (Trace.isOn) {
/* 1730 */       Trace.data(this, "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)", "STRANGE: 390/400 version of MQSET called on client", "");
/*      */     }
/*      */     
/* 1733 */     if (Trace.isOn) {
/* 1734 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQSTAT(Hconn hconn, int type, MQSTS stat, Pint pCompCode, Pint pReason) {
/* 1753 */     if (Trace.isOn) {
/* 1754 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", new Object[] { hconn, 
/* 1755 */             Integer.valueOf(type), stat, pCompCode, pReason });
/*      */     }
/* 1757 */     this.jMQI.MQSTAT(hconn, type, stat, pCompCode, pReason);
/* 1758 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1760 */     if (Trace.isOn) {
/* 1761 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void honourRRSInternal(int Hconn, Pint pCompCode, Pint pReason) {
/* 1790 */     if (Trace.isOn) {
/* 1791 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "honourRRSInternal(int,Pint,Pint)", new Object[] {
/* 1792 */             Integer.valueOf(Hconn), pCompCode, pReason
/*      */           });
/*      */     }
/* 1795 */     String fid = "honourRRSInternal(int,Pint,Pint)";
/*      */ 
/*      */     
/* 1798 */     if (Trace.isOn) {
/* 1799 */       Trace.data(this, "honourRRSInternal(int,Pint,Pint)", "WARNING: honourRRSInternal called", "");
/*      */     }
/*      */ 
/*      */     
/* 1803 */     if (Trace.isOn) {
/* 1804 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "honourRRSInternal(int,Pint,Pint)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) {
/* 1823 */     if (Trace.isOn) {
/* 1824 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1828 */     JmqiSP mqsp = (JmqiSP)this.jMQI;
/* 1829 */     mqsp.honourRRS(hconn, pCompCode, pReason);
/*      */     
/* 1831 */     if (Trace.isOn) {
/* 1832 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "honourRRS(Hconn,Pint,Pint)");
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
/*      */   
/*      */   public final boolean distributionListCapable(Hconn connectionHandle) {
/* 1848 */     if (Trace.isOn) {
/* 1849 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "distributionListCapable(Hconn)", new Object[] { connectionHandle });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1854 */     if (Trace.isOn) {
/* 1855 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "distributionListCapable(Hconn)", 
/* 1856 */           Boolean.valueOf(true));
/*      */     }
/* 1858 */     return true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setThreadAccess(String threadingType, Boolean threadAffinity) throws MQException {
/* 1883 */     if (Trace.isOn) {
/* 1884 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "setThreadAccess(String,Boolean)", new Object[] { threadingType, threadAffinity });
/*      */     }
/*      */     
/* 1887 */     String fid = "setThreadAccess(String,Boolean)";
/* 1888 */     if (threadingType == null || threadingType.equals("MULTI_THREAD")) {
/*      */ 
/*      */       
/* 1891 */       if (Trace.isOn)
/* 1892 */         Trace.data(this, "setThreadAccess(String,Boolean)", "Default multi-thread access will be used.", ""); 
/*      */     } else {
/* 1894 */       if (threadingType.equals("SINGLE_THREAD")) {
/* 1895 */         if (Trace.isOn) {
/* 1896 */           Trace.data(this, "setThreadAccess(String,Boolean)", "Single thread access mode not supported.", "");
/*      */         }
/* 1898 */         MQException traceRet1 = new MQException(2, 2012, this);
/*      */         
/* 1900 */         if (Trace.isOn) {
/* 1901 */           Trace.throwing(this, "com.ibm.mq.MQSESSION", "setThreadAccess(String,Boolean)", traceRet1, 1);
/*      */         }
/*      */         
/* 1904 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1907 */       if (Trace.isOn) {
/* 1908 */         Trace.data(this, "setThreadAccess(String,Boolean)", "Unknown thread access mode specified", "");
/*      */       }
/* 1910 */       MQException traceRet2 = new MQException(2, 2012, this);
/* 1911 */       if (Trace.isOn) {
/* 1912 */         Trace.throwing(this, "com.ibm.mq.MQSESSION", "setThreadAccess(String,Boolean)", traceRet2, 2);
/*      */       }
/*      */       
/* 1915 */       throw traceRet2;
/*      */     } 
/* 1917 */     if (Trace.isOn) {
/* 1918 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "setThreadAccess(String,Boolean)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final GregorianCalendar getInquireCalendar() {
/* 1949 */     if (Trace.isOn) {
/* 1950 */       Trace.entry("com.ibm.mq.MQSESSION", "getInquireCalendar()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1955 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 1956 */       GregorianCalendar traceRet1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 1957 */       if (Trace.isOn) {
/* 1958 */         Trace.exit("com.ibm.mq.MQSESSION", "getInquireCalendar()", traceRet1, 1);
/*      */       }
/* 1960 */       return traceRet1;
/*      */     } 
/*      */     
/* 1963 */     GregorianCalendar traceRet2 = new GregorianCalendar();
/* 1964 */     if (Trace.isOn) {
/* 1965 */       Trace.exit("com.ibm.mq.MQSESSION", "getInquireCalendar()", traceRet2, 2);
/*      */     }
/* 1967 */     return traceRet2;
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
/*      */   protected static final boolean backoutOnImplicitDisc() {
/* 1979 */     if (Trace.isOn) {
/* 1980 */       Trace.entry("com.ibm.mq.MQSESSION", "backoutOnImplicitDisc()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1985 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 1986 */       if (Trace.isOn) {
/* 1987 */         Trace.exit("com.ibm.mq.MQSESSION", "backoutOnImplicitDisc()", Boolean.valueOf(false), 1);
/*      */       }
/* 1989 */       return false;
/*      */     } 
/*      */     
/* 1992 */     if (Trace.isOn) {
/* 1993 */       Trace.exit("com.ibm.mq.MQSESSION", "backoutOnImplicitDisc()", Boolean.valueOf(true), 2);
/*      */     }
/* 1995 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getCharEncoding() {
/* 2001 */     if (Trace.isOn) {
/* 2002 */       Trace.data(this, "com.ibm.mq.MQSESSION", "getCharEncoding()", "getter", 
/* 2003 */           Integer.valueOf(1));
/*      */     }
/* 2005 */     return 1;
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 2022 */     if (Trace.isOn) {
/* 2023 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "authenticate(Hconn,String,String,Pint,Pint)", new Object[] { hconn, userId, (password == null) ? null : 
/* 2024 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*      */     }
/* 2026 */     pCompCode.x = 0;
/* 2027 */     pReason.x = 0;
/*      */     
/* 2029 */     if (Trace.isOn) {
/* 2030 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "authenticate(Hconn,String,String,Pint,Pint)", 
/*      */ 
/*      */           
/* 2033 */           Boolean.valueOf(true));
/*      */     }
/* 2035 */     return true;
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
/*      */   private MQSESSION(int jmqiBindType, boolean requestedThreadAffinity, String threadingType) throws MQException {
/* 2049 */     super(getJmqiEnv());
/* 2050 */     if (Trace.isOn) {
/* 2051 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "<init>(int,boolean,String)", new Object[] {
/* 2052 */             Integer.valueOf(jmqiBindType), Boolean.valueOf(requestedThreadAffinity), threadingType
/*      */           });
/*      */     }
/* 2055 */     int options = 0;
/* 2056 */     if (requestedThreadAffinity) {
/* 2057 */       options = 1;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2062 */       this.jMQI = getJmqiEnv().getMQI(jmqiBindType, options);
/*      */     }
/* 2064 */     catch (JmqiException e) {
/* 2065 */       if (Trace.isOn) {
/* 2066 */         Trace.catchBlock(this, "com.ibm.mq.MQSESSION", "<init>(int,boolean,String)", (Throwable)e);
/*      */       }
/*      */       
/* 2069 */       MQException traceRet1 = new MQException(2, e.getReason(), this, e);
/* 2070 */       if (Trace.isOn) {
/* 2071 */         Trace.throwing(this, "com.ibm.mq.MQSESSION", "<init>(int,boolean,String)", traceRet1);
/*      */       }
/* 2073 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2076 */     if (Trace.isOn) {
/* 2077 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "<init>(int,boolean,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static MQSESSION getSession(MQManagedConnectionJ11 mancon) throws MQException {
/* 2087 */     if (Trace.isOn) {
/* 2088 */       Trace.entry("com.ibm.mq.MQSESSION", "getSession(MQManagedConnectionJ11)", new Object[] { mancon });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2093 */     MQSESSION retVal = null;
/* 2094 */     boolean authenticateRRSBindings = false;
/* 2095 */     Boolean threadAffinityObject = (Boolean)mancon.getProperty("Thread affinity");
/* 2096 */     boolean requestedThreadAffinity = false;
/* 2097 */     if (threadAffinityObject != null && threadAffinityObject.booleanValue()) {
/* 2098 */       requestedThreadAffinity = true;
/*      */     }
/*      */     
/* 2101 */     String threadingType = mancon.getStringProperty("Thread access");
/* 2102 */     String transport = mancon.getStringProperty("transport");
/* 2103 */     String hostname = mancon.getStringProperty("hostname", "");
/*      */ 
/*      */     
/* 2106 */     if (transport.equals("MQSeries Bindings") || (transport.equals("MQSeries") && hostname.equals(""))) {
/*      */ 
/*      */       
/* 2109 */       retVal = new MQSESSION(0, requestedThreadAffinity, threadingType);
/*      */       
/* 2111 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && authenticateRRSBindings) {
/* 2112 */         retVal.setAuthenticateBindings(true);
/*      */       } else {
/*      */         
/* 2115 */         retVal.mqManCon = mancon;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2121 */     if (retVal == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2126 */       retVal = new MQSESSION(2, requestedThreadAffinity, threadingType);
/*      */ 
/*      */       
/* 2129 */       retVal.mqManCon = mancon;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2134 */     if (retVal == null) {
/*      */ 
/*      */ 
/*      */       
/* 2138 */       MQException traceRet1 = new MQException(2, 2298, "static method in MQSESSION");
/*      */       
/* 2140 */       if (Trace.isOn) {
/* 2141 */         Trace.throwing("com.ibm.mq.MQSESSION", "getSession(MQManagedConnectionJ11)", traceRet1);
/*      */       }
/*      */       
/* 2144 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2147 */     if (Trace.isOn) {
/* 2148 */       Trace.exit("com.ibm.mq.MQSESSION", "getSession(MQManagedConnectionJ11)", retVal);
/*      */     }
/* 2150 */     return retVal;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final MQManagedConnectionFactory getMQManagedConnectionFactory(String transport, String qmgrName, Hashtable properties) throws MQException {
/* 2169 */     if (Trace.isOn) {
/* 2170 */       Trace.entry("com.ibm.mq.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable)", new Object[] { transport, qmgrName, 
/* 2171 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX") });
/*      */     }
/* 2173 */     if (transport.equals("MQSeries Bindings")) {
/* 2174 */       MQManagedConnectionFactory traceRet1 = new MQBindingsManagedConnectionFactoryJ11(qmgrName, properties);
/*      */       
/* 2176 */       if (Trace.isOn) {
/* 2177 */         Trace.exit("com.ibm.mq.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable)", traceRet1, 1);
/*      */       }
/*      */       
/* 2180 */       return traceRet1;
/*      */     } 
/*      */     
/* 2183 */     if (transport.equals("MQSeries Client")) {
/* 2184 */       MQManagedConnectionFactory traceRet3 = new MQClientManagedConnectionFactoryJ11(qmgrName, properties);
/*      */       
/* 2186 */       if (Trace.isOn) {
/* 2187 */         Trace.exit("com.ibm.mq.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable)", traceRet3, 2);
/*      */       }
/*      */       
/* 2190 */       return traceRet3;
/*      */     } 
/*      */ 
/*      */     
/* 2194 */     MQException traceRet5 = new MQException(2, 2012, "static method in MQSESSION");
/*      */     
/* 2196 */     if (Trace.isOn) {
/* 2197 */       Trace.throwing("com.ibm.mq.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable)", traceRet5);
/*      */     }
/*      */     
/* 2200 */     throw traceRet5;
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
/*      */ 
/*      */   
/*      */   static final MQConnectionRequestInfo getConnectionRequestInfo(String transport, Hashtable<String, Object> properties, URL ccdtUrl) throws MQException {
/* 2216 */     if (Trace.isOn) {
/* 2217 */       Trace.entry("com.ibm.mq.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String , Object>,URL)", new Object[] { transport, 
/*      */             
/* 2219 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"), ccdtUrl });
/*      */     }
/* 2221 */     if (transport.equals("MQSeries Bindings")) {
/* 2222 */       MQConnectionRequestInfo traceRet1 = new BindingsConnectionRequestInfo(properties);
/*      */       
/* 2224 */       if (Trace.isOn) {
/* 2225 */         Trace.exit("com.ibm.mq.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String , Object>,URL)", traceRet1, 1);
/*      */       }
/*      */       
/* 2228 */       return traceRet1;
/* 2229 */     }  if (transport.equals("MQSeries Client")) {
/* 2230 */       MQConnectionRequestInfo traceRet2 = new ClientConnectionRequestInfo(properties, ccdtUrl);
/*      */       
/* 2232 */       if (Trace.isOn) {
/* 2233 */         Trace.exit("com.ibm.mq.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String , Object>,URL)", traceRet2, 2);
/*      */       }
/*      */       
/* 2236 */       return traceRet2;
/*      */     } 
/* 2238 */     MQException traceRet3 = new MQException(2, 2012, "static method in MQSESSION");
/*      */     
/* 2240 */     if (Trace.isOn) {
/* 2241 */       Trace.throwing("com.ibm.mq.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String , Object>,URL)", traceRet3);
/*      */     }
/*      */     
/* 2244 */     throw traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int getDefaultCCSID() {
/* 2254 */     if (Trace.isOn) {
/* 2255 */       Trace.entry("com.ibm.mq.MQSESSION", "getDefaultCCSID()");
/*      */     }
/* 2257 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 2258 */       if (Trace.isOn) {
/* 2259 */         Trace.exit("com.ibm.mq.MQSESSION", "getDefaultCCSID()", Integer.valueOf(500), 1);
/*      */       }
/* 2261 */       return 500;
/*      */     } 
/*      */     
/* 2264 */     if (Trace.isOn) {
/* 2265 */       Trace.exit("com.ibm.mq.MQSESSION", "getDefaultCCSID()", Integer.valueOf(819), 2);
/*      */     }
/* 2267 */     return 819;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2273 */   private static char[] charInitArray = null;
/*      */   
/*      */   static {
/* 2276 */     if (Trace.isOn) {
/* 2277 */       Trace.entry("com.ibm.mq.MQSESSION", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2282 */     charInitArray = new char[100];
/* 2283 */     for (int i = 0; i < 100; i++) {
/* 2284 */       charInitArray[i] = ' ';
/*      */     }
/*      */     
/* 2287 */     if (Trace.isOn) {
/* 2288 */       Trace.exit("com.ibm.mq.MQSESSION", "static()");
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
/*      */   public static String setStringToLength(String s, int length) {
/* 2300 */     if (Trace.isOn) {
/* 2301 */       Trace.entry("com.ibm.mq.MQSESSION", "setStringToLength(String,int)", new Object[] { s, 
/* 2302 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */     
/* 2306 */     String result = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2313 */     if (s != null && 
/* 2314 */       s.length() == length) {
/* 2315 */       if (Trace.isOn) {
/* 2316 */         Trace.exit("com.ibm.mq.MQSESSION", "setStringToLength(String,int)", s, 1);
/*      */       }
/* 2318 */       return s;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2323 */     if (s == null) {
/*      */ 
/*      */       
/* 2326 */       if (length <= 100) {
/* 2327 */         result = new String(charInitArray, 0, length);
/*      */       } else {
/* 2329 */         StringBuffer tmp = new StringBuffer(length);
/* 2330 */         for (int extraChars = length; extraChars > 0; extraChars -= 100) {
/* 2331 */           if (extraChars > 100) {
/* 2332 */             tmp.append(charInitArray);
/*      */           } else {
/* 2334 */             tmp.append(charInitArray, 0, extraChars);
/*      */           } 
/*      */         } 
/* 2337 */         result = tmp.toString();
/*      */       }
/*      */     
/*      */     }
/* 2341 */     else if (s.length() > length) {
/*      */       try {
/* 2343 */         result = s.substring(0, length);
/*      */       }
/* 2345 */       catch (StringIndexOutOfBoundsException e) {
/* 2346 */         if (Trace.isOn) {
/* 2347 */           Trace.catchBlock("com.ibm.mq.MQSESSION", "setStringToLength(String,int)", e);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2353 */       StringBuffer tmp = new StringBuffer(s);
/* 2354 */       tmp.ensureCapacity(length);
/* 2355 */       for (int extraChars = length - s.length(); extraChars > 0; extraChars -= 100) {
/* 2356 */         if (extraChars > 100) {
/* 2357 */           tmp.append(charInitArray);
/*      */         } else {
/* 2359 */           tmp.append(charInitArray, 0, extraChars);
/*      */         } 
/*      */       } 
/* 2362 */       result = tmp.toString();
/*      */     } 
/*      */ 
/*      */     
/* 2366 */     if (Trace.isOn) {
/* 2367 */       Trace.exit("com.ibm.mq.MQSESSION", "setStringToLength(String,int)", result, 2);
/*      */     }
/* 2369 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String getProductPrefix() {
/* 2380 */     if (Trace.isOn) {
/* 2381 */       Trace.entry("com.ibm.mq.MQSESSION", "getProductPrefix()");
/*      */     }
/* 2383 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 2384 */       if (Trace.isOn) {
/* 2385 */         Trace.exit("com.ibm.mq.MQSESSION", "getProductPrefix()", "CSQ", 1);
/*      */       }
/* 2387 */       return "CSQ";
/*      */     } 
/*      */     
/* 2390 */     if (Trace.isOn) {
/* 2391 */       Trace.exit("com.ibm.mq.MQSESSION", "getProductPrefix()", "AMQ", 2);
/*      */     }
/* 2393 */     return "AMQ";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JmqiMQ getJmqi() {
/* 2402 */     if (Trace.isOn) {
/* 2403 */       Trace.data(this, "com.ibm.mq.MQSESSION", "getJmqi()", "getter", this.jMQI);
/*      */     }
/* 2405 */     return this.jMQI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JmqiException getLastJmqiException() {
/* 2414 */     JmqiException traceRet1 = MQCommonServices.jmqiEnv.getLastException();
/* 2415 */     if (Trace.isOn) {
/* 2416 */       Trace.data(this, "com.ibm.mq.MQSESSION", "getLastJmqiException()", "getter", traceRet1);
/*      */     }
/* 2418 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private void errorOccured(Hconn hconn, int cc, int rc) {
/* 2422 */     if (Trace.isOn) {
/* 2423 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "errorOccured(Hconn,int,int)", new Object[] { hconn, 
/* 2424 */             Integer.valueOf(cc), Integer.valueOf(rc) });
/*      */     }
/* 2426 */     if (hconn != null) {
/* 2427 */       Phconn pHconn = this.env.newPhconn();
/* 2428 */       pHconn.setHconn(hconn);
/* 2429 */       errorOccured(pHconn, cc, rc);
/*      */     } 
/*      */     
/* 2432 */     if (Trace.isOn) {
/* 2433 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "errorOccured(Hconn,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void errorOccured(Phconn pHconn, int cc, int rc) {
/* 2439 */     if (Trace.isOn) {
/* 2440 */       Trace.entry(this, "com.ibm.mq.MQSESSION", "errorOccured(Phconn,int,int)", new Object[] { pHconn, 
/* 2441 */             Integer.valueOf(cc), Integer.valueOf(rc) });
/*      */     }
/*      */     
/* 2444 */     if (rc == 2162) {
/* 2445 */       Pint pCompCode = this.env.newPint();
/* 2446 */       Pint pReason = this.env.newPint();
/* 2447 */       this.jMQI.MQDISC(pHconn, pCompCode, pReason);
/*      */     } 
/*      */     
/* 2450 */     if (Trace.isOn)
/* 2451 */       Trace.exit(this, "com.ibm.mq.MQSESSION", "errorOccured(Phconn,int,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQSESSION.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */