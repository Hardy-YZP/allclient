/*      */ package com.ibm.mq.jms.admin;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AdminObject
/*      */ {
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/AdminObject.java";
/*      */   public static final String CLASSNAME = "com.ibm.mq.jms.admin.AdminObject";
/*      */   public static final int OBJTYPE_CONTEXT = -2;
/*      */   public static final int OBJTYPE_NONE = -1;
/*      */   public static final int OBJTYPE_CF = 8;
/*      */   public static final int OBJTYPE_QCF = 0;
/*      */   public static final int OBJTYPE_Q = 1;
/*      */   public static final int OBJTYPE_TCF = 2;
/*      */   public static final int OBJTYPE_T = 3;
/*      */   public static final int OBJTYPE_XACF = 9;
/*      */   public static final int OBJTYPE_XAQCF = 4;
/*      */   public static final int OBJTYPE_XATCF = 5;
/*      */   public static final int OBJTYPE_WSXAQCF = 6;
/*      */   public static final int OBJTYPE_WSXATCF = 7;
/*      */   public static final int ERROR_NONE = -1;
/*      */   public static final int ERROR_LEX = 0;
/*      */   public static final int ERROR_SYN_INVALID = 1;
/*      */   public static final int ERROR_SYN_MISSING = 2;
/*      */   public static final int ERROR_SEM = 3;
/*      */   public static final int ERROR_PRAG = 4;
/*      */   public static final int ERROR_OTHER = 5;
/*      */   static final int ERROR_TYPE_MISMATCH = 10;
/*      */   public static final String propVER = "VERSION";
/*      */   public static final String propTRAN = "TRANSPORT";
/*      */   public static final String propBPUB = "BROKERPUBQ";
/*      */   public static final String propBSUB = "BROKERSUBQ";
/*      */   public static final String propCID = "CLIENTID";
/*      */   public static final String propHOST = "HOSTNAME";
/*      */   public static final String propCHAN = "CHANNEL";
/*      */   public static final String propRCX = "RECEXIT";
/*      */   public static final String propSCX = "SECEXIT";
/*      */   public static final String propSDX = "SENDEXIT";
/*      */   public static final String propTM = "TEMPMODEL";
/*      */   public static final String propEXP = "EXPIRY";
/*      */   public static final String propPER = "PERSISTENCE";
/*      */   public static final String propQU = "QUEUE";
/*      */   public static final String propENC = "ENCODING";
/*      */   public static final String propCCSUB = "BROKERCCSUBQ";
/*      */   public static final String propMSEL = "MSGSELECTION";
/*      */   public static final String propSRI = "STATREFRESHINT";
/*      */   public static final String propMBS = "MSGBATCHSZ";
/*      */   
/*      */   static {
/*   64 */     if (Trace.isOn) {
/*   65 */       Trace.data("com.ibm.mq.jms.admin.AdminObject", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/AdminObject.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String propUAMQ = "USERAUTHMQCSP";
/*      */ 
/*      */   
/*      */   public static final String propUCP = "USECONNPOOLING";
/*      */ 
/*      */   
/*      */   public static final String propDESC = "DESCRIPTION";
/*      */ 
/*      */   
/*      */   public static final String propBCON = "BROKERCONQ";
/*      */ 
/*      */   
/*      */   public static final String propBQM = "BROKERQMGR";
/*      */ 
/*      */   
/*      */   public static final String propBDSUB = "BROKERDURSUBQ";
/*      */ 
/*      */   
/*      */   public static final String propQMGR = "QMANAGER";
/*      */ 
/*      */   
/*      */   public static final String propPORT = "PORT";
/*      */ 
/*      */   
/*      */   public static final String propCCS = "CCSID";
/*      */ 
/*      */   
/*      */   public static final String propRCXI = "RECEXITINIT";
/*      */ 
/*      */   
/*      */   public static final String propSCXI = "SECEXITINIT";
/*      */ 
/*      */   
/*      */   public static final String propSDXI = "SENDEXITINIT";
/*      */ 
/*      */   
/*      */   public static final String propBVER = "BROKERVER";
/*      */ 
/*      */   
/*      */   public static final String propPRI = "PRIORITY";
/*      */ 
/*      */   
/*      */   public static final String propTOP = "TOPIC";
/*      */ 
/*      */   
/*      */   public static final String propTC = "TARGCLIENT";
/*      */ 
/*      */   
/*      */   public static final String propMRET = "MSGRETENTION";
/*      */ 
/*      */   
/*      */   public static final String propCCDSUB = "BROKERCCDURSUBQ";
/*      */ 
/*      */   
/*      */   public static final String propPAI = "PUBACKINT";
/*      */ 
/*      */   
/*      */   public static final String propSPAG = "SYNCPOINTALLGETS";
/*      */ 
/*      */   
/*      */   public static final String propPINT = "POLLINGINT";
/*      */ 
/*      */   
/*      */   public static final String propFIQ = "FAILIFQUIESCE";
/*      */ 
/*      */   
/*      */   public static final String propLA = "LOCALADDRESS";
/*      */ 
/*      */   
/*      */   public static final String propTBPUBQ = "BROKERPUBQ";
/*      */ 
/*      */   
/*      */   public static final String propTBQM = "BROKERPUBQMGR";
/*      */ 
/*      */   
/*      */   public static final String propTQP = "TEMPQPREFIX";
/*      */ 
/*      */   
/*      */   public static final String propSBRWS = "SPARSESUBS";
/*      */ 
/*      */   
/*      */   public static final String propMCAST = "MULTICAST";
/*      */ 
/*      */   
/*      */   public static final String propDAUTH = "DIRECTAUTH";
/*      */ 
/*      */   
/*      */   public static final String propPHOST = "PROXYHOSTNAME";
/*      */ 
/*      */   
/*      */   public static final String propPPORT = "PROXYPORT";
/*      */ 
/*      */   
/*      */   public static final String propMBSZ = "MAXBUFFSIZE";
/*      */ 
/*      */   
/*      */   public static final String propHC = "COMPHDR";
/*      */ 
/*      */   
/*      */   public static final String propMC = "COMPMSG";
/*      */ 
/*      */   
/*      */   public static final String propSRC = "SSLRESETCOUNT";
/*      */ 
/*      */   
/*      */   public static final String propCNTAG = "CONNTAG";
/*      */ 
/*      */   
/*      */   public static final String propCNOPT = "CONNOPT";
/*      */ 
/*      */   
/*      */   public static final String propTCM = "TARGCLIENTMATCHING";
/*      */ 
/*      */   
/*      */   public static final String propCCDT = "CCDTURL";
/*      */ 
/*      */   
/*      */   public static final String propMNST = "MAPNAMESTYLE";
/*      */ 
/*      */   
/*      */   public static final String propPAALD = "PUTASYNCALLOWED";
/*      */ 
/*      */   
/*      */   public static final String propRAALD = "READAHEADALLOWED";
/*      */ 
/*      */   
/*      */   public static final String propRACP = "READAHEADCLOSEPOLICY";
/*      */ 
/*      */   
/*      */   public static final String propPVER = "PROVIDERVERSION";
/*      */ 
/*      */   
/*      */   public static final String propSCALD = "SHARECONVALLOWED";
/*      */ 
/*      */   
/*      */   public static final String propSCC = "SENDCHECKCOUNT";
/*      */ 
/*      */   
/*      */   public static final String propTTP = "TEMPTOPICPREFIX";
/*      */ 
/*      */   
/*      */   public static final String propWCFMT = "WILDCARDFORMAT";
/*      */ 
/*      */   
/*      */   public static final String propCROPT = "CLIENTRECONNECTOPTIONS";
/*      */ 
/*      */   
/*      */   public static final String propCRT = "CLIENTRECONNECTTIMEOUT";
/*      */ 
/*      */   
/*      */   public static final String propUMA = "UNMAPPABLEACTION";
/*      */ 
/*      */   
/*      */   public static final String propUMR = "UNMAPPABLEREPLACEMENT";
/*      */ 
/*      */   
/*      */   public static final String ipropVER = "VER";
/*      */ 
/*      */   
/*      */   public static final String ipropTRAN = "TRAN";
/*      */ 
/*      */   
/*      */   public static final String ipropBPUB = "BPUB";
/*      */ 
/*      */   
/*      */   public static final String ipropBSUB = "BSUB";
/*      */ 
/*      */   
/*      */   public static final String ipropCID = "CID";
/*      */ 
/*      */   
/*      */   public static final String ipropHOST = "HOST";
/*      */ 
/*      */   
/*      */   public static final String ipropCHAN = "CHAN";
/*      */ 
/*      */   
/*      */   public static final String ipropRCX = "RCX";
/*      */ 
/*      */   
/*      */   public static final String ipropSCX = "SCX";
/*      */ 
/*      */   
/*      */   public static final String ipropSDX = "SDX";
/*      */ 
/*      */   
/*      */   public static final String ipropTM = "TM";
/*      */ 
/*      */   
/*      */   public static final String ipropEXP = "EXP";
/*      */ 
/*      */   
/*      */   public static final String ipropPER = "PER";
/*      */ 
/*      */   
/*      */   public static final String ipropQU = "QU";
/*      */ 
/*      */   
/*      */   public static final String ipropENC = "ENC";
/*      */ 
/*      */   
/*      */   public static final String ipropCCSUB = "CCSUB";
/*      */ 
/*      */   
/*      */   public static final String ipropMSEL = "MSEL";
/*      */ 
/*      */   
/*      */   public static final String ipropSRI = "SRI";
/*      */ 
/*      */   
/*      */   public static final String ipropMBS = "MBS";
/*      */ 
/*      */   
/*      */   public static final String ipropUAMQ = "UAMQ";
/*      */ 
/*      */   
/*      */   public static final String ipropUCP = "UCP";
/*      */ 
/*      */   
/*      */   public static final String ipropDESC = "DESC";
/*      */ 
/*      */   
/*      */   public static final String ipropBCON = "BCON";
/*      */ 
/*      */   
/*      */   public static final String ipropBQM = "BQM";
/*      */ 
/*      */   
/*      */   public static final String ipropBDSUB = "BDSUB";
/*      */ 
/*      */   
/*      */   public static final String ipropQMGR = "QMGR";
/*      */ 
/*      */   
/*      */   public static final String ipropPORT = "PORT";
/*      */ 
/*      */   
/*      */   public static final String ipropCCS = "CCS";
/*      */ 
/*      */   
/*      */   public static final String ipropRCXI = "RCXI";
/*      */ 
/*      */   
/*      */   public static final String ipropSCXI = "SCXI";
/*      */ 
/*      */   
/*      */   public static final String ipropSDXI = "SDXI";
/*      */ 
/*      */   
/*      */   public static final String ipropBVER = "BVER";
/*      */ 
/*      */   
/*      */   public static final String ipropPRI = "PRI";
/*      */ 
/*      */   
/*      */   public static final String ipropTOP = "TOP";
/*      */ 
/*      */   
/*      */   public static final String ipropTC = "TC";
/*      */ 
/*      */   
/*      */   public static final String ipropMRET = "MRET";
/*      */ 
/*      */   
/*      */   public static final String ipropCCDSUB = "CCDSUB";
/*      */ 
/*      */   
/*      */   public static final String ipropPAI = "PAI";
/*      */ 
/*      */   
/*      */   public static final String ipropSPAG = "SPAG";
/*      */ 
/*      */   
/*      */   public static final String ipropPINT = "PINT";
/*      */ 
/*      */   
/*      */   public static final String ipropFIQ = "FIQ";
/*      */ 
/*      */   
/*      */   public static final String ipropLA = "LA";
/*      */ 
/*      */   
/*      */   public static final String ipropTBPUBQ = "BPUB";
/*      */ 
/*      */   
/*      */   public static final String ipropTBQM = "BPQM";
/*      */ 
/*      */   
/*      */   public static final String ipropTQP = "TQP";
/*      */ 
/*      */   
/*      */   public static final String ipropSBRWS = "SSUBS";
/*      */ 
/*      */   
/*      */   public static final String ipropMCAST = "MCAST";
/*      */ 
/*      */   
/*      */   public static final String ipropDAUTH = "DAUTH";
/*      */ 
/*      */   
/*      */   public static final String ipropPHOST = "PHOST";
/*      */ 
/*      */   
/*      */   public static final String ipropPPORT = "PPORT";
/*      */ 
/*      */   
/*      */   public static final String ipropMBSZ = "MBSZ";
/*      */ 
/*      */   
/*      */   public static final String ipropHC = "HC";
/*      */ 
/*      */   
/*      */   public static final String ipropMC = "MC";
/*      */ 
/*      */   
/*      */   public static final String ipropCNTAG = "CNTAG";
/*      */ 
/*      */   
/*      */   public static final String ipropCNOPT = "CNOPT";
/*      */ 
/*      */   
/*      */   public static final String ipropSRC = "SRC";
/*      */ 
/*      */   
/*      */   public static final String ipropTCM = "TCM";
/*      */ 
/*      */   
/*      */   public static final String ipropCCDT = "CCDT";
/*      */ 
/*      */   
/*      */   public static final String ipropMNST = "MNST";
/*      */ 
/*      */   
/*      */   public static final String ipropPAALD = "PAALD";
/*      */ 
/*      */   
/*      */   public static final String ipropRAALD = "RAALD";
/*      */ 
/*      */   
/*      */   public static final String ipropRACP = "RACP";
/*      */ 
/*      */   
/*      */   public static final String ipropPVER = "PVER";
/*      */ 
/*      */   
/*      */   public static final String ipropSCALD = "SCALD";
/*      */ 
/*      */   
/*      */   public static final String ipropSCC = "SCC";
/*      */ 
/*      */   
/*      */   public static final String ipropTTP = "TTP";
/*      */ 
/*      */   
/*      */   public static final String ipropWCFMT = "WCFMT";
/*      */ 
/*      */   
/*      */   public static final String ipropCROPT = "CROPT";
/*      */ 
/*      */   
/*      */   public static final String ipropCRT = "CRT";
/*      */ 
/*      */   
/*      */   public static final String ipropUMA = "UMA";
/*      */ 
/*      */   
/*      */   public static final String ipropUMR = "UMR";
/*      */ 
/*      */   
/*  441 */   private int errorFlag = -1;
/*  442 */   private String errorString1 = null;
/*  443 */   private String errorString2 = null;
/*      */   
/*  445 */   private BAO obj = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   AdminObject(int objType, Object newObj, String alias) {
/*  460 */     if (Trace.isOn) {
/*  461 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Object,String)", new Object[] {
/*  462 */             Integer.valueOf(objType), newObj, alias
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  476 */       String clsName = newObj.getClass().getName();
/*  477 */       switch (objType) {
/*      */         case 8:
/*  479 */           if (clsName.equals("com.ibm.mq.jms.MQConnectionFactory")) {
/*  480 */             this.obj = new CFBAO();
/*      */             break;
/*      */           } 
/*  483 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 0:
/*  487 */           if (clsName.equals("com.ibm.mq.jms.MQQueueConnectionFactory")) {
/*  488 */             this.obj = new QCFBAO();
/*      */             break;
/*      */           } 
/*  491 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 1:
/*  495 */           if (clsName.equals("com.ibm.mq.jms.MQQueue")) {
/*  496 */             this.obj = new QueueBAO();
/*      */             break;
/*      */           } 
/*  499 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 2:
/*  503 */           if (clsName.equals("com.ibm.mq.jms.MQTopicConnectionFactory")) {
/*  504 */             this.obj = new TCFBAO();
/*      */             break;
/*      */           } 
/*  507 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 3:
/*  511 */           if (clsName.equals("com.ibm.mq.jms.MQTopic")) {
/*  512 */             this.obj = new TopicBAO();
/*      */             break;
/*      */           } 
/*  515 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 9:
/*  519 */           if (clsName.equals("com.ibm.mq.jms.MQXAConnectionFactory")) {
/*  520 */             this.obj = new XACFBAO();
/*      */             break;
/*      */           } 
/*  523 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 4:
/*  527 */           if (clsName.equals("com.ibm.mq.jms.MQXAQueueConnectionFactory")) {
/*  528 */             this.obj = new XAQCFBAO();
/*      */             break;
/*      */           } 
/*  531 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 5:
/*  535 */           if (clsName.equals("com.ibm.mq.jms.MQXATopicConnectionFactory")) {
/*  536 */             this.obj = new XATCFBAO();
/*      */             break;
/*      */           } 
/*  539 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 6:
/*  543 */           if (clsName.equals("com.ibm.ejs.jms.mq.JMSWrapXAQueueConnectionFactory")) {
/*  544 */             this.obj = new WSXAQCFBAO();
/*      */             break;
/*      */           } 
/*  547 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         case 7:
/*  551 */           if (clsName.equals("com.ibm.ejs.jms.mq.JMSWrapXATopicConnectionFactory")) {
/*  552 */             this.obj = new WSXATCFBAO();
/*      */             break;
/*      */           } 
/*  555 */           this.errorFlag = 10;
/*      */           break;
/*      */         
/*      */         default:
/*  559 */           this.errorFlag = 10;
/*      */           break;
/*      */       } 
/*  562 */       if (this.errorFlag == -1) {
/*  563 */         this.obj.setFromObject(newObj);
/*      */       
/*      */       }
/*      */     }
/*  567 */     catch (BAOException be) {
/*  568 */       if (Trace.isOn) {
/*  569 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Object,String)", be);
/*      */       }
/*      */       
/*  572 */       this.errorFlag = be.getErrorFlag();
/*  573 */       this.errorString1 = be.getString1();
/*  574 */       this.errorString2 = be.getString2();
/*      */     }
/*      */     finally {
/*      */       
/*  578 */       if (Trace.isOn) {
/*  579 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Object,String)");
/*      */       }
/*      */     } 
/*  582 */     if (Trace.isOn) {
/*  583 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Object,String)");
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
/*      */   public AdminObject(int objType, Hashtable<String, Object> props) {
/*  600 */     this(objType, props, false);
/*  601 */     if (Trace.isOn)
/*  602 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Hashtable)", new Object[] {
/*  603 */             Integer.valueOf(objType), props
/*      */           }); 
/*  605 */     if (Trace.isOn) {
/*  606 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Hashtable)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AdminObject(int objType, Map<String, Object> props) {
/*  616 */     this(objType, props, false);
/*  617 */     if (Trace.isOn)
/*  618 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>)", new Object[] {
/*  619 */             Integer.valueOf(objType), props
/*      */           }); 
/*  621 */     if (Trace.isOn) {
/*  622 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>)");
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
/*      */   public AdminObject(int objType, Hashtable<String, Object> props, boolean checked) {
/*  638 */     this(objType, props, checked);
/*  639 */     if (Trace.isOn)
/*  640 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Hashtable,boolean)", new Object[] {
/*  641 */             Integer.valueOf(objType), props, Boolean.valueOf(checked)
/*      */           }); 
/*  643 */     if (Trace.isOn) {
/*  644 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Hashtable,boolean)");
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
/*      */   public AdminObject(int objType, Map<String, Object> props, boolean checked) {
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", new Object[] {
/*  662 */             Integer.valueOf(objType), props, 
/*  663 */             Boolean.valueOf(checked)
/*      */           });
/*      */     }
/*  666 */     normalisePropertyNames(props);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  674 */     boolean isWSObject = false;
/*      */     
/*  676 */     switch (objType) {
/*      */       
/*      */       case 8:
/*  679 */         this.obj = new CFBAO();
/*      */         break;
/*      */       
/*      */       case 0:
/*  683 */         this.obj = new QCFBAO();
/*      */         break;
/*      */       
/*      */       case 1:
/*  687 */         this.obj = new QueueBAO();
/*      */         break;
/*      */       
/*      */       case 2:
/*  691 */         this.obj = new TCFBAO();
/*      */         break;
/*      */       
/*      */       case 3:
/*  695 */         this.obj = new TopicBAO();
/*      */         break;
/*      */       
/*      */       case 9:
/*  699 */         this.obj = new XACFBAO();
/*      */         break;
/*      */       
/*      */       case 4:
/*  703 */         this.obj = new XAQCFBAO();
/*      */         break;
/*      */       
/*      */       case 5:
/*  707 */         this.obj = new XATCFBAO();
/*      */         break;
/*      */       
/*      */       case 6:
/*  711 */         isWSObject = true;
/*  712 */         this.obj = new WSXAQCFBAO();
/*      */         break;
/*      */       
/*      */       case 7:
/*  716 */         isWSObject = true;
/*  717 */         this.obj = new WSXATCFBAO();
/*      */         break;
/*      */       
/*      */       default:
/*  721 */         this.errorFlag = 10;
/*  722 */         if (Trace.isOn) {
/*  723 */           Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", 1);
/*      */         }
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  733 */       this.obj.unsupportedProperty(props);
/*      */       
/*  735 */       this.obj.semanticCheck(props);
/*      */       
/*  737 */       this.obj.setFromProperties(props);
/*      */     
/*      */     }
/*  740 */     catch (BAOException be) {
/*  741 */       if (Trace.isOn) {
/*  742 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", be, 1);
/*      */       }
/*      */       
/*  745 */       this.errorFlag = be.getErrorFlag();
/*  746 */       this.errorString1 = be.getString1();
/*  747 */       this.errorString2 = be.getString2();
/*      */     
/*      */     }
/*  750 */     catch (JMSException e) {
/*  751 */       if (Trace.isOn) {
/*  752 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", (Throwable)e, 2);
/*      */       }
/*      */       
/*  755 */       this.errorFlag = 5;
/*  756 */       this.errorString1 = e.getMessage();
/*      */     }
/*  758 */     catch (NoClassDefFoundError e) {
/*  759 */       if (Trace.isOn) {
/*  760 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", e, 3);
/*      */       }
/*      */ 
/*      */       
/*  764 */       if (isWSObject) {
/*  765 */         this.errorFlag = 5;
/*  766 */         this.errorString1 = ConfigEnvironment.getMessage("JMSADM4137");
/*  767 */         this.errorString2 = null;
/*      */       } else {
/*      */         
/*  770 */         if (Trace.isOn) {
/*  771 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", e);
/*      */         }
/*      */         
/*  774 */         throw e;
/*      */       } 
/*      */     } 
/*  777 */     if (Trace.isOn) {
/*  778 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "<init>(int,Map<String , Object>,boolean)", 2);
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
/*      */   private void normalisePropertyNames(Map<String, Object> props) {
/*  796 */     if (Trace.isOn) {
/*  797 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "normalisePropertyNames(Map<String , Object>)", new Object[] { props });
/*      */     }
/*      */     
/*  800 */     ArrayList<String> badKeys = new ArrayList<>(props.size());
/*      */     
/*  802 */     for (String keyString : props.keySet()) {
/*  803 */       if (!isAllUpperCase(keyString)) {
/*  804 */         badKeys.add(keyString);
/*      */       }
/*      */     } 
/*      */     
/*  808 */     for (String keyString : badKeys) {
/*  809 */       String upCaseKeyString = keyString.toUpperCase();
/*  810 */       props.put(upCaseKeyString, props.get(keyString));
/*  811 */       props.remove(keyString);
/*      */     } 
/*      */     
/*  814 */     if (Trace.isOn) {
/*  815 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "normalisePropertyNames(Map<String , Object>)");
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
/*      */   private boolean isAllUpperCase(String s) {
/*  828 */     if (Trace.isOn) {
/*  829 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "isAllUpperCase(String)", new Object[] { s });
/*      */     }
/*      */     
/*  832 */     int length = s.length();
/*  833 */     for (int i = 0; i < length; i++) {
/*  834 */       if (!Character.isUpperCase(s.charAt(i))) {
/*  835 */         if (Trace.isOn) {
/*  836 */           Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "isAllUpperCase(String)", 
/*  837 */               Boolean.valueOf(false), 1);
/*      */         }
/*  839 */         return false;
/*      */       } 
/*      */     } 
/*  842 */     if (Trace.isOn) {
/*  843 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "isAllUpperCase(String)", 
/*  844 */           Boolean.valueOf(true), 2);
/*      */     }
/*  846 */     return true;
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
/*      */   public Map<String, Object> getProperties() {
/*  859 */     if (Trace.isOn) {
/*  860 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "getProperties()");
/*      */     }
/*      */     try {
/*  863 */       if (this.obj == null) {
/*  864 */         if (Trace.isOn) {
/*  865 */           Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "getProperties()", null, 1);
/*      */         }
/*  867 */         return null;
/*      */       } 
/*      */       
/*  870 */       Map<String, Object> traceRet1 = this.obj.getProperties();
/*  871 */       if (Trace.isOn) {
/*  872 */         Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "getProperties()", traceRet1, 2);
/*      */       }
/*  874 */       return traceRet1;
/*      */     } finally {
/*      */       
/*  877 */       if (Trace.isOn) {
/*  878 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.AdminObject", "getProperties()");
/*      */       }
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
/*      */   public int getType() {
/*  892 */     if (Trace.isOn) {
/*  893 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "getType()");
/*      */     }
/*      */     try {
/*  896 */       if (this.obj == null) {
/*  897 */         if (Trace.isOn) {
/*  898 */           Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "getType()", 
/*  899 */               Integer.valueOf(-1), 1);
/*      */         }
/*  901 */         return -1;
/*      */       } 
/*      */       
/*  904 */       int traceRet1 = this.obj.getType();
/*  905 */       if (Trace.isOn) {
/*  906 */         Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "getType()", 
/*  907 */             Integer.valueOf(traceRet1), 2);
/*      */       }
/*  909 */       return traceRet1;
/*      */     } finally {
/*      */       
/*  912 */       if (Trace.isOn) {
/*  913 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.AdminObject", "getType()");
/*      */       }
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
/*      */   public Object getObject() {
/*  927 */     if (Trace.isOn) {
/*  928 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "getObject()");
/*      */     }
/*      */     try {
/*  931 */       if (this.obj == null) {
/*  932 */         if (Trace.isOn) {
/*  933 */           Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "getObject()", null, 1);
/*      */         }
/*  935 */         return null;
/*      */       } 
/*      */       
/*  938 */       Object traceRet1 = this.obj.getObject();
/*  939 */       if (Trace.isOn) {
/*  940 */         Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "getObject()", traceRet1, 2);
/*      */       }
/*  942 */       return traceRet1;
/*      */     } finally {
/*      */       
/*  945 */       if (Trace.isOn) {
/*  946 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.AdminObject", "getObject()");
/*      */       }
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
/*      */   public Object getBAOObject() {
/*      */     try {
/*  962 */       if (Trace.isOn) {
/*  963 */         Trace.data(this, "com.ibm.mq.jms.admin.AdminObject", "getBAOObject()", "getter", this.obj);
/*      */       }
/*  965 */       return this.obj;
/*      */     }
/*      */     finally {
/*      */       
/*  969 */       if (Trace.isOn) {
/*  970 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.AdminObject", "getBAOObject()");
/*      */       }
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
/*      */   public boolean errorRaised() {
/*  986 */     if (Trace.isOn) {
/*  987 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "errorRaised()");
/*      */     }
/*  989 */     boolean traceRet1 = (this.errorFlag != -1);
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "errorRaised()", 
/*  992 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  994 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetError() {
/* 1005 */     if (Trace.isOn) {
/* 1006 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminObject", "resetError()");
/*      */     }
/*      */     
/* 1009 */     this.errorFlag = -1;
/* 1010 */     this.errorString1 = null;
/* 1011 */     this.errorString2 = null;
/*      */     
/* 1013 */     if (Trace.isOn) {
/* 1014 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminObject", "resetError()");
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
/*      */   public int getErrorType() {
/* 1029 */     if (Trace.isOn) {
/* 1030 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminObject", "getErrorType()", "getter", 
/* 1031 */           Integer.valueOf(this.errorFlag));
/*      */     }
/* 1033 */     return this.errorFlag;
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
/*      */   public String getErrorString1() {
/* 1046 */     if (Trace.isOn) {
/* 1047 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminObject", "getErrorString1()", "getter", this.errorString1);
/*      */     }
/*      */     
/* 1050 */     return this.errorString1;
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
/*      */   public String getErrorString2() {
/* 1063 */     if (Trace.isOn) {
/* 1064 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminObject", "getErrorString2()", "getter", this.errorString2);
/*      */     }
/*      */     
/* 1067 */     return this.errorString2;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\AdminObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */