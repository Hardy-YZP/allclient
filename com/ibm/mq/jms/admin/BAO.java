/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public abstract class BAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/BAO.java";
/*     */   public static final String CLASSNAME = "com.ibm.mq.jms.admin.BAO";
/*     */   public static final int OBJTYPE_CONTEXT = -2;
/*     */   public static final int OBJTYPE_NONE = -1;
/*     */   public static final int OBJTYPE_CF = 8;
/*     */   public static final int OBJTYPE_QCF = 0;
/*     */   public static final int OBJTYPE_Q = 1;
/*     */   public static final int OBJTYPE_TCF = 2;
/*     */   public static final int OBJTYPE_T = 3;
/*     */   public static final int OBJTYPE_XACF = 9;
/*     */   public static final int OBJTYPE_XAQCF = 4;
/*     */   public static final int OBJTYPE_XATCF = 5;
/*     */   public static final int OBJTYPE_WSXAQCF = 6;
/*     */   public static final int OBJTYPE_WSXATCF = 7;
/*     */   public static final int ERROR_NONE = -1;
/*     */   public static final int ERROR_LEX = 0;
/*     */   public static final int ERROR_SYN_INVALID = 1;
/*     */   public static final int ERROR_SYN_MISSING = 2;
/*     */   public static final int ERROR_SEM = 3;
/*     */   public static final int ERROR_PRAG = 4;
/*     */   public static final int ERROR_OTHER = 5;
/*     */   static final int ERROR_TYPE_MISMATCH = 10;
/*     */   public static final String propVER = "VERSION";
/*     */   public static final String propTRAN = "TRANSPORT";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.admin.BAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/BAO.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String propBPUB = "BROKERPUBQ";
/*     */ 
/*     */   
/*     */   public static final String propBSUB = "BROKERSUBQ";
/*     */ 
/*     */   
/*     */   public static final String propAPPNAME = "APPLICATIONNAME";
/*     */ 
/*     */   
/*     */   public static final String propCID = "CLIENTID";
/*     */ 
/*     */   
/*     */   public static final String propHOST = "HOSTNAME";
/*     */ 
/*     */   
/*     */   public static final String propCHAN = "CHANNEL";
/*     */ 
/*     */   
/*     */   public static final String propRCX = "RECEXIT";
/*     */ 
/*     */   
/*     */   public static final String propSCX = "SECEXIT";
/*     */ 
/*     */   
/*     */   public static final String propSDX = "SENDEXIT";
/*     */ 
/*     */   
/*     */   public static final String propTM = "TEMPMODEL";
/*     */ 
/*     */   
/*     */   public static final String propEXP = "EXPIRY";
/*     */ 
/*     */   
/*     */   public static final String propPER = "PERSISTENCE";
/*     */ 
/*     */   
/*     */   public static final String propQU = "QUEUE";
/*     */ 
/*     */   
/*     */   public static final String propENC = "ENCODING";
/*     */ 
/*     */   
/*     */   public static final String propCCSUB = "BROKERCCSUBQ";
/*     */ 
/*     */   
/*     */   public static final String propMSEL = "MSGSELECTION";
/*     */ 
/*     */   
/*     */   public static final String propSRI = "STATREFRESHINT";
/*     */ 
/*     */   
/*     */   public static final String propMBS = "MSGBATCHSZ";
/*     */ 
/*     */   
/*     */   public static final String propUAMQ = "USERAUTHMQCSP";
/*     */ 
/*     */   
/*     */   public static final String propUCP = "USECONNPOOLING";
/*     */ 
/*     */   
/*     */   public static final String propDESC = "DESCRIPTION";
/*     */ 
/*     */   
/*     */   public static final String propCCDT = "CCDTURL";
/*     */ 
/*     */   
/*     */   public static final String propBCON = "BROKERCONQ";
/*     */ 
/*     */   
/*     */   public static final String propBQM = "BROKERQMGR";
/*     */ 
/*     */   
/*     */   public static final String propBDSUB = "BROKERDURSUBQ";
/*     */ 
/*     */   
/*     */   public static final String propQMGR = "QMANAGER";
/*     */ 
/*     */   
/*     */   public static final String propPORT = "PORT";
/*     */ 
/*     */   
/*     */   public static final String propCCS = "CCSID";
/*     */ 
/*     */   
/*     */   public static final String propRCXI = "RECEXITINIT";
/*     */ 
/*     */   
/*     */   public static final String propSCXI = "SECEXITINIT";
/*     */ 
/*     */   
/*     */   public static final String propSDXI = "SENDEXITINIT";
/*     */ 
/*     */   
/*     */   public static final String propBVER = "BROKERVER";
/*     */ 
/*     */   
/*     */   public static final String propPRI = "PRIORITY";
/*     */ 
/*     */   
/*     */   public static final String propTOP = "TOPIC";
/*     */ 
/*     */   
/*     */   public static final String propTC = "TARGCLIENT";
/*     */ 
/*     */   
/*     */   public static final String propTCM = "TARGCLIENTMATCHING";
/*     */ 
/*     */   
/*     */   public static final String propMRET = "MSGRETENTION";
/*     */ 
/*     */   
/*     */   public static final String propCCDSUB = "BROKERCCDURSUBQ";
/*     */ 
/*     */   
/*     */   public static final String propPAI = "PUBACKINT";
/*     */ 
/*     */   
/*     */   public static final String propSPAG = "SYNCPOINTALLGETS";
/*     */ 
/*     */   
/*     */   public static final String propPINT = "POLLINGINT";
/*     */ 
/*     */   
/*     */   public static final String propSCRL = "SSLCRL";
/*     */ 
/*     */   
/*     */   public static final String propSPEER = "SSLPEERNAME";
/*     */ 
/*     */   
/*     */   public static final String propSCPHS = "SSLCIPHERSUITE";
/*     */ 
/*     */   
/*     */   public static final String propFIQ = "FAILIFQUIESCE";
/*     */ 
/*     */   
/*     */   public static final String propRESCAN = "RESCANINT";
/*     */ 
/*     */   
/*     */   public static final String propLA = "LOCALADDRESS";
/*     */ 
/*     */   
/*     */   public static final String propTBPUBQ = "BROKERPUBQ";
/*     */ 
/*     */   
/*     */   public static final String propTBQM = "BROKERPUBQMGR";
/*     */ 
/*     */   
/*     */   public static final String propTQP = "TEMPQPREFIX";
/*     */ 
/*     */   
/*     */   public static final String propPHOST = "PROXYHOSTNAME";
/*     */ 
/*     */   
/*     */   public static final String propPPORT = "PROXYPORT";
/*     */ 
/*     */   
/*     */   public static final String propMCAST = "MULTICAST";
/*     */ 
/*     */   
/*     */   public static final String propDAUTH = "DIRECTAUTH";
/*     */ 
/*     */   
/*     */   public static final String propMBSZ = "MAXBUFFSIZE";
/*     */ 
/*     */   
/*     */   public static final String propSRC = "SSLRESETCOUNT";
/*     */ 
/*     */   
/*     */   public static final String propHC = "COMPHDR";
/*     */ 
/*     */   
/*     */   public static final String propMC = "COMPMSG";
/*     */ 
/*     */   
/*     */   public static final String propCNTAG = "CONNTAG";
/*     */ 
/*     */   
/*     */   public static final String propCNOPT = "CONNOPT";
/*     */ 
/*     */   
/*     */   public static final String propRCISO = "RECEIVEISOLATION";
/*     */ 
/*     */   
/*     */   public static final String propPRDUR = "PROCESSDURATION";
/*     */ 
/*     */   
/*     */   public static final String propNTFY = "OUTCOMENOTIFICATION";
/*     */ 
/*     */   
/*     */   public static final String propOPPUB = "OPTIMISTICPUBLICATION";
/*     */ 
/*     */   
/*     */   public static final String propSFPIS = "SSLFIPSREQUIRED";
/*     */ 
/*     */   
/*     */   public static final String propSCALD = "SHARECONVALLOWED";
/*     */ 
/*     */   
/*     */   public static final String propPVER = "PROVIDERVERSION";
/*     */ 
/*     */   
/*     */   public static final String propSCC = "SENDCHECKCOUNT";
/*     */ 
/*     */   
/*     */   public static final String propTTP = "TEMPTOPICPREFIX";
/*     */ 
/*     */   
/*     */   public static final String propWCFMT = "WILDCARDFORMAT";
/*     */ 
/*     */   
/*     */   public static final String propRACP = "READAHEADCLOSEPOLICY";
/*     */ 
/*     */   
/*     */   public static final String propRAALD = "READAHEADALLOWED";
/*     */ 
/*     */   
/*     */   public static final String propPAALD = "PUTASYNCALLOWED";
/*     */ 
/*     */   
/*     */   public static final String propMNST = "MAPNAMESTYLE";
/*     */ 
/*     */   
/*     */   public static final String propAEX = "ASYNCEXCEPTION";
/*     */ 
/*     */   
/*     */   public static final String propWMB = "MSGBODY";
/*     */ 
/*     */   
/*     */   public static final String propWMRE = "MDREAD";
/*     */ 
/*     */   
/*     */   public static final String propWMWE = "MDWRITE";
/*     */ 
/*     */   
/*     */   public static final String propWMMC = "MDMSGCTX";
/*     */ 
/*     */   
/*     */   public static final String propRTOST = "REPLYTOSTYLE";
/*     */ 
/*     */   
/*     */   public static final String propCROPT = "CLIENTRECONNECTOPTIONS";
/*     */ 
/*     */   
/*     */   public static final String propCRT = "CLIENTRECONNECTTIMEOUT";
/*     */ 
/*     */   
/*     */   public static final String propUMA = "UNMAPPABLEACTION";
/*     */ 
/*     */   
/*     */   public static final String propUMR = "UNMAPPABLEREPLACEMENT";
/*     */ 
/*     */   
/*     */   public static final String ipropVER = "VER";
/*     */ 
/*     */   
/*     */   public static final String ipropTRAN = "TRAN";
/*     */ 
/*     */   
/*     */   public static final String ipropBPUB = "BPUB";
/*     */ 
/*     */   
/*     */   public static final String ipropBSUB = "BSUB";
/*     */ 
/*     */   
/*     */   public static final String ipropCID = "CID";
/*     */ 
/*     */   
/*     */   public static final String ipropAPPNAME = "APPNAME";
/*     */ 
/*     */   
/*     */   public static final String ipropHOST = "HOST";
/*     */ 
/*     */   
/*     */   public static final String ipropCHAN = "CHAN";
/*     */ 
/*     */   
/*     */   public static final String ipropRCX = "RCX";
/*     */ 
/*     */   
/*     */   public static final String ipropSCX = "SCX";
/*     */ 
/*     */   
/*     */   public static final String ipropSDX = "SDX";
/*     */ 
/*     */   
/*     */   public static final String ipropTM = "TM";
/*     */ 
/*     */   
/*     */   public static final String ipropEXP = "EXP";
/*     */ 
/*     */   
/*     */   public static final String ipropPER = "PER";
/*     */ 
/*     */   
/*     */   public static final String ipropQU = "QU";
/*     */ 
/*     */   
/*     */   public static final String ipropENC = "ENC";
/*     */ 
/*     */   
/*     */   public static final String ipropCCSUB = "CCSUB";
/*     */ 
/*     */   
/*     */   public static final String ipropMSEL = "MSEL";
/*     */ 
/*     */   
/*     */   public static final String ipropSRI = "SRI";
/*     */ 
/*     */   
/*     */   public static final String ipropMBS = "MBS";
/*     */ 
/*     */   
/*     */   public static final String ipropUAMQ = "UAMQ";
/*     */ 
/*     */   
/*     */   public static final String ipropUCP = "UCP";
/*     */ 
/*     */   
/*     */   public static final String ipropDESC = "DESC";
/*     */ 
/*     */   
/*     */   public static final String ipropCCDT = "CCDT";
/*     */ 
/*     */   
/*     */   public static final String ipropBCON = "BCON";
/*     */ 
/*     */   
/*     */   public static final String ipropBQM = "BQM";
/*     */ 
/*     */   
/*     */   public static final String ipropBDSUB = "BDSUB";
/*     */ 
/*     */   
/*     */   public static final String ipropQMGR = "QMGR";
/*     */ 
/*     */   
/*     */   public static final String ipropPORT = "PORT";
/*     */ 
/*     */   
/*     */   public static final String ipropCCS = "CCS";
/*     */ 
/*     */   
/*     */   public static final String ipropRCXI = "RCXI";
/*     */ 
/*     */   
/*     */   public static final String ipropSCXI = "SCXI";
/*     */ 
/*     */   
/*     */   public static final String ipropSDXI = "SDXI";
/*     */ 
/*     */   
/*     */   public static final String ipropBVER = "BVER";
/*     */ 
/*     */   
/*     */   public static final String ipropPRI = "PRI";
/*     */ 
/*     */   
/*     */   public static final String ipropTOP = "TOP";
/*     */ 
/*     */   
/*     */   public static final String ipropTC = "TC";
/*     */ 
/*     */   
/*     */   public static final String ipropTCM = "TCM";
/*     */ 
/*     */   
/*     */   public static final String ipropMRET = "MRET";
/*     */ 
/*     */   
/*     */   public static final String ipropCCDSUB = "CCDSUB";
/*     */ 
/*     */   
/*     */   public static final String ipropPAI = "PAI";
/*     */ 
/*     */   
/*     */   public static final String ipropSPAG = "SPAG";
/*     */ 
/*     */   
/*     */   public static final String ipropPINT = "PINT";
/*     */ 
/*     */   
/*     */   public static final String ipropSCRL = "SCRL";
/*     */ 
/*     */   
/*     */   public static final String ipropSPEER = "SPEER";
/*     */ 
/*     */   
/*     */   public static final String ipropSCPHS = "SCPHS";
/*     */ 
/*     */   
/*     */   public static final String ipropFIQ = "FIQ";
/*     */ 
/*     */   
/*     */   public static final String ipropRESCAN = "RINT";
/*     */ 
/*     */   
/*     */   public static final String ipropPHOST = "PHOST";
/*     */ 
/*     */   
/*     */   public static final String ipropPPORT = "PPORT";
/*     */ 
/*     */   
/*     */   public static final String ipropMCAST = "MCAST";
/*     */ 
/*     */   
/*     */   public static final String ipropDAUTH = "DAUTH";
/*     */ 
/*     */   
/*     */   public static final String ipropLA = "LA";
/*     */ 
/*     */   
/*     */   public static final String ipropTBPUBQ = "BPUB";
/*     */ 
/*     */   
/*     */   public static final String ipropTBQM = "BPQM";
/*     */ 
/*     */   
/*     */   public static final String ipropTQP = "TQP";
/*     */ 
/*     */   
/*     */   public static final String ipropMBSZ = "MBSZ";
/*     */ 
/*     */   
/*     */   public static final String ipropSRC = "SRC";
/*     */ 
/*     */   
/*     */   public static final String ipropHC = "HC";
/*     */ 
/*     */   
/*     */   public static final String ipropMC = "MC";
/*     */ 
/*     */   
/*     */   public static final String ipropCNTAG = "CNTAG";
/*     */ 
/*     */   
/*     */   public static final String ipropCNOPT = "CNOPT";
/*     */ 
/*     */   
/*     */   public static final String ipropRCISO = "RCVISOL";
/*     */ 
/*     */   
/*     */   public static final String ipropPRDUR = "PROCDUR";
/*     */ 
/*     */   
/*     */   public static final String ipropNTFY = "NOTIFY";
/*     */ 
/*     */   
/*     */   public static final String ipropOPPUB = "OPTPUB";
/*     */ 
/*     */   
/*     */   public static final String ipropSFIPS = "SFIPS";
/*     */ 
/*     */   
/*     */   public static final String ipropSCALD = "SCALD";
/*     */ 
/*     */   
/*     */   public static final String ipropPVER = "PVER";
/*     */ 
/*     */   
/*     */   public static final String ipropSCC = "SCC";
/*     */ 
/*     */   
/*     */   public static final String ipropTTP = "TTP";
/*     */ 
/*     */   
/*     */   public static final String ipropWCFMT = "WCFMT";
/*     */ 
/*     */   
/*     */   public static final String ipropRACP = "RACP";
/*     */ 
/*     */   
/*     */   public static final String ipropRAALD = "RAALD";
/*     */ 
/*     */   
/*     */   public static final String ipropPAALD = "PAALD";
/*     */ 
/*     */   
/*     */   public static final String ipropMNST = "MNST";
/*     */ 
/*     */   
/*     */   public static final String ipropAEX = "AEX";
/*     */ 
/*     */   
/*     */   public static final String ipropWMB = "MBODY";
/*     */ 
/*     */   
/*     */   public static final String ipropWMRE = "MDR";
/*     */ 
/*     */   
/*     */   public static final String ipropWMWE = "MDW";
/*     */ 
/*     */   
/*     */   public static final String ipropWMMC = "MDCTX";
/*     */ 
/*     */   
/*     */   public static final String ipropRTOST = "RTOST";
/*     */ 
/*     */   
/*     */   public static final String ipropCROPT = "CROPT";
/*     */ 
/*     */   
/*     */   public static final String ipropCRT = "CRT";
/*     */ 
/*     */   
/*     */   public static final String ipropUMA = "UMA";
/*     */ 
/*     */   
/*     */   public static final String ipropUMR = "UMR";
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsupportedProperty(Map<String, Object> properties) throws BAOException {
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.entry(this, "com.ibm.mq.jms.admin.BAO", "unsupportedProperty(Map<String , Object>)", new Object[] { properties });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 577 */     List<String> sProps = supportedProperties();
/*     */ 
/*     */ 
/*     */     
/* 581 */     for (String prop : properties.keySet()) {
/*     */       
/* 583 */       String shortName = AP.getShortName(prop);
/*     */       
/* 585 */       if (shortName == null || !sProps.contains(shortName)) {
/* 586 */         BAOException be = new BAOException(1, name(), prop);
/* 587 */         if (Trace.isOn) {
/* 588 */           Trace.throwing(this, "com.ibm.mq.jms.admin.BAO", "unsupportedProperty(Map<String , Object>)", be);
/*     */         }
/*     */         
/* 591 */         throw be;
/*     */       } 
/*     */     } 
/*     */     
/* 595 */     if (Trace.isOn)
/* 596 */       Trace.exit(this, "com.ibm.mq.jms.admin.BAO", "unsupportedProperty(Map<String , Object>)"); 
/*     */   }
/*     */   
/*     */   public abstract String name();
/*     */   
/*     */   public abstract void semanticCheck(Map<String, Object> paramMap) throws BAOException, JMSException;
/*     */   
/*     */   public abstract List<String> supportedProperties();
/*     */   
/*     */   public abstract void setFromProperties(Map<String, Object> paramMap) throws BAOException, JMSException;
/*     */   
/*     */   public abstract void setFromObject(Object paramObject) throws BAOException;
/*     */   
/*     */   public abstract Object getObject();
/*     */   
/*     */   public abstract int getType();
/*     */   
/*     */   public abstract Map<String, Object> getProperties();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\BAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */