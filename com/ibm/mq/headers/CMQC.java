/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
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
/*     */ @Deprecated
/*     */ public interface CMQC
/*     */   extends CMQC
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/CMQC.java";
/*     */   public static final int MQCMDL_LEVEL_521 = 521;
/*  38 */   public static final char[] MQAIR_STRUC_ID_ARRAY = new char[] { 'A', 'I', 'R', ' ' };
/*     */   
/*  40 */   public static final char[] MQBO_STRUC_ID_ARRAY = new char[] { 'B', 'O', ' ', ' ' };
/*     */   
/*  42 */   public static final char[] MQCIH_STRUC_ID_ARRAY = new char[] { 'C', 'I', 'H', ' ' };
/*     */   
/*  44 */   public static final char[] MQCFAC_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */   
/*  46 */   public static final char[] MQCFUNC_MQCONN_ARRAY = new char[] { 'C', 'O', 'N', 'N' };
/*     */   
/*  48 */   public static final char[] MQCFUNC_MQGET_ARRAY = new char[] { 'G', 'E', 'T', ' ' };
/*     */   
/*  50 */   public static final char[] MQCFUNC_MQINQ_ARRAY = new char[] { 'I', 'N', 'Q', ' ' };
/*     */   
/*  52 */   public static final char[] MQCFUNC_MQOPEN_ARRAY = new char[] { 'O', 'P', 'E', 'N' };
/*     */   
/*  54 */   public static final char[] MQCFUNC_MQPUT_ARRAY = new char[] { 'P', 'U', 'T', ' ' };
/*     */   
/*  56 */   public static final char[] MQCFUNC_MQPUT1_ARRAY = new char[] { 'P', 'U', 'T', '1' };
/*     */   
/*  58 */   public static final char[] MQCFUNC_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ' };
/*     */   
/*  60 */   public static final char[] MQCSC_START_ARRAY = new char[] { 'S', ' ', ' ', ' ' };
/*     */   
/*  62 */   public static final char[] MQCSC_STARTDATA_ARRAY = new char[] { 'S', 'D', ' ', ' ' };
/*     */   
/*  64 */   public static final char[] MQCSC_TERMINPUT_ARRAY = new char[] { 'T', 'D', ' ', ' ' };
/*     */   
/*  66 */   public static final char[] MQCSC_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ' };
/*     */   
/*  68 */   public static final char[] MQCNO_STRUC_ID_ARRAY = new char[] { 'C', 'N', 'O', ' ' };
/*     */   
/*  70 */   public static final char[] MQCT_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static final char[] MQCONNID_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/*  82 */   public static final char[] MQCSP_STRUC_ID_ARRAY = new char[] { 'C', 'S', 'P', ' ' };
/*     */   
/*  84 */   public static final char[] MQDH_STRUC_ID_ARRAY = new char[] { 'D', 'H', ' ', ' ' };
/*     */   
/*  86 */   public static final char[] MQDLH_STRUC_ID_ARRAY = new char[] { 'D', 'L', 'H', ' ' };
/*     */   
/*  88 */   public static final char[] MQGMO_STRUC_ID_ARRAY = new char[] { 'G', 'M', 'O', ' ' };
/*     */   
/*  90 */   public static final char[] MQMTOK_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/*  93 */   public static final char[] MQIIH_STRUC_ID_ARRAY = new char[] { 'I', 'I', 'H', ' ' };
/*     */   
/*  95 */   public static final char[] MQIAUT_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */   
/*  97 */   public static final char[] MQITII_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/* 100 */   public static final char[] MQMD_STRUC_ID_ARRAY = new char[] { 'M', 'D', ' ', ' ' };
/*     */   
/* 102 */   public static final char[] MQFMT_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */   
/* 104 */   public static final char[] MQFMT_ADMIN_ARRAY = new char[] { 'M', 'Q', 'A', 'D', 'M', 'I', 'N', ' ' };
/*     */   
/* 106 */   public static final char[] MQFMT_CHANNEL_COMPLETED_ARRAY = new char[] { 'M', 'Q', 'C', 'H', 'C', 'O', 'M', ' ' };
/*     */ 
/*     */   
/* 109 */   public static final char[] MQFMT_CICS_ARRAY = new char[] { 'M', 'Q', 'C', 'I', 'C', 'S', ' ', ' ' };
/*     */   
/* 111 */   public static final char[] MQFMT_COMMAND_1_ARRAY = new char[] { 'M', 'Q', 'C', 'M', 'D', '1', ' ', ' ' };
/*     */   
/* 113 */   public static final char[] MQFMT_COMMAND_2_ARRAY = new char[] { 'M', 'Q', 'C', 'M', 'D', '2', ' ', ' ' };
/*     */   
/* 115 */   public static final char[] MQFMT_DEAD_LETTER_HEADER_ARRAY = new char[] { 'M', 'Q', 'D', 'E', 'A', 'D', ' ', ' ' };
/*     */ 
/*     */   
/* 118 */   public static final char[] MQFMT_DIST_HEADER_ARRAY = new char[] { 'M', 'Q', 'H', 'D', 'I', 'S', 'T', ' ' };
/*     */   
/* 120 */   public static final char[] MQFMT_EMBEDDED_PCF_ARRAY = new char[] { 'M', 'Q', 'H', 'E', 'P', 'C', 'F', ' ' };
/*     */   
/* 122 */   public static final char[] MQFMT_EVENT_ARRAY = new char[] { 'M', 'Q', 'E', 'V', 'E', 'N', 'T', ' ' };
/*     */   
/* 124 */   public static final char[] MQFMT_IMS_ARRAY = new char[] { 'M', 'Q', 'I', 'M', 'S', ' ', ' ', ' ' };
/*     */   
/* 126 */   public static final char[] MQFMT_IMS_VAR_STRING_ARRAY = new char[] { 'M', 'Q', 'I', 'M', 'S', 'V', 'S', ' ' };
/*     */ 
/*     */   
/* 129 */   public static final char[] MQFMT_MD_EXTENSION_ARRAY = new char[] { 'M', 'Q', 'H', 'M', 'D', 'E', ' ', ' ' };
/*     */   
/* 131 */   public static final char[] MQFMT_PCF_ARRAY = new char[] { 'M', 'Q', 'P', 'C', 'F', ' ', ' ', ' ' };
/*     */   
/* 133 */   public static final char[] MQFMT_REF_MSG_HEADER_ARRAY = new char[] { 'M', 'Q', 'H', 'R', 'E', 'F', ' ', ' ' };
/*     */ 
/*     */   
/* 136 */   public static final char[] MQFMT_RF_HEADER_ARRAY = new char[] { 'M', 'Q', 'H', 'R', 'F', ' ', ' ', ' ' };
/*     */   
/* 138 */   public static final char[] MQFMT_RF_HEADER_1_ARRAY = new char[] { 'M', 'Q', 'H', 'R', 'F', ' ', ' ', ' ' };
/*     */   
/* 140 */   public static final char[] MQFMT_RF_HEADER_2_ARRAY = new char[] { 'M', 'Q', 'H', 'R', 'F', '2', ' ', ' ' };
/*     */   
/* 142 */   public static final char[] MQFMT_STRING_ARRAY = new char[] { 'M', 'Q', 'S', 'T', 'R', ' ', ' ', ' ' };
/*     */   
/* 144 */   public static final char[] MQFMT_TRIGGER_ARRAY = new char[] { 'M', 'Q', 'T', 'R', 'I', 'G', ' ', ' ' };
/*     */   
/* 146 */   public static final char[] MQFMT_WORK_INFO_HEADER_ARRAY = new char[] { 'M', 'Q', 'H', 'W', 'I', 'H', ' ', ' ' };
/*     */ 
/*     */   
/* 149 */   public static final char[] MQFMT_XMIT_Q_HEADER_ARRAY = new char[] { 'M', 'Q', 'X', 'M', 'I', 'T', ' ', ' ' };
/*     */ 
/*     */   
/* 152 */   public static final char[] MQMI_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/* 155 */   public static final char[] MQCI_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/* 158 */   public static final byte[] MQCI_NEW_SESSION_ARRAY = new byte[] { 65, 77, 81, 33, 78, 69, 87, 95, 83, 69, 83, 83, 73, 79, 78, 95, 67, 79, 82, 82, 69, 76, 73, 68 };
/*     */ 
/*     */ 
/*     */   
/* 162 */   public static final char[] MQACT_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */ 
/*     */   
/* 166 */   public static final char[] MQGI_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/* 169 */   public static final char[] MQMDE_STRUC_ID_ARRAY = new char[] { 'M', 'D', 'E', ' ' };
/*     */   
/* 171 */   public static final char[] MQOD_STRUC_ID_ARRAY = new char[] { 'O', 'D', ' ', ' ' };
/*     */   
/* 173 */   public static final char[] MQSID_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */ 
/*     */   
/* 177 */   public static final char[] MQPMO_STRUC_ID_ARRAY = new char[] { 'P', 'M', 'O', ' ' };
/*     */   
/* 179 */   public static final char[] MQRFH_STRUC_ID_ARRAY = new char[] { 'R', 'F', 'H', ' ' };
/*     */   
/* 181 */   public static final char[] MQRMH_STRUC_ID_ARRAY = new char[] { 'R', 'M', 'H', ' ' };
/*     */   
/* 183 */   public static final char[] MQOII_NONE_ARRAY = new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
/*     */ 
/*     */   
/* 186 */   public static final char[] MQSCO_STRUC_ID_ARRAY = new char[] { 'S', 'C', 'O', ' ' };
/*     */   
/* 188 */   public static final char[] MQTM_STRUC_ID_ARRAY = new char[] { 'T', 'M', ' ', ' ' };
/*     */   
/* 190 */   public static final char[] MQTMC_STRUC_ID_ARRAY = new char[] { 'T', 'M', 'C', ' ' };
/*     */   
/* 192 */   public static final char[] MQTMC_VERSION_1_ARRAY = new char[] { ' ', ' ', ' ', '1' };
/*     */   
/* 194 */   public static final char[] MQTMC_VERSION_2_ARRAY = new char[] { ' ', ' ', ' ', '2' };
/*     */ 
/*     */   
/*     */   public static final int MQTMC2_LENGTH = 732;
/*     */ 
/*     */   
/* 200 */   public static final char[] MQTMC_CURRENT_VERSION_ARRAY = new char[] { ' ', ' ', ' ', '2' };
/*     */   
/* 202 */   public static final char[] MQWIH_STRUC_ID_ARRAY = new char[] { 'W', 'I', 'H', ' ' };
/*     */   
/* 204 */   public static final char[] MQXQH_STRUC_ID_ARRAY = new char[] { 'X', 'Q', 'H', ' ' };
/*     */   @Deprecated
/*     */   public static final int MQ_EXIT_NAME_LENGTH = 128;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\CMQC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */