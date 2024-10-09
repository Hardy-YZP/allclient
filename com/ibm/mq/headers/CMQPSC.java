/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQPSC;
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
/*     */ public interface CMQPSC
/*     */   extends CMQPSC
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/CMQPSC.java";
/*  37 */   public static final char[] MQPS_COMMAND_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'C', 'o', 'm', 'm', 'a', 'n', 'd', ' ' };
/*     */ 
/*     */   
/*  40 */   public static final char[] MQPS_COMP_CODE_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'C', 'o', 'm', 'p', 'C', 'o', 'd', 'e', ' ' };
/*     */ 
/*     */   
/*  43 */   public static final char[] MQPS_CORREL_ID_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'C', 'o', 'r', 'r', 'e', 'l', 'I', 'd', ' ' };
/*     */ 
/*     */   
/*  46 */   public static final char[] MQPS_DELETE_OPTIONS_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'D', 'e', 'l', 'O', 'p', 't', 's', ' ' };
/*     */ 
/*     */   
/*  49 */   public static final char[] MQPS_ERROR_ID_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'E', 'r', 'r', 'o', 'r', 'I', 'd', ' ' };
/*     */ 
/*     */   
/*  52 */   public static final char[] MQPS_ERROR_POS_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'E', 'r', 'r', 'o', 'r', 'P', 'o', 's', ' ' };
/*     */ 
/*     */   
/*  55 */   public static final char[] MQPS_INTEGER_DATA_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'I', 'n', 't', 'D', 'a', 't', 'a', ' ' };
/*     */ 
/*     */   
/*  58 */   public static final char[] MQPS_PARAMETER_ID_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'P', 'a', 'r', 'm', 'I', 'd', ' ' };
/*     */ 
/*     */   
/*  61 */   public static final char[] MQPS_PUBLICATION_OPTIONS_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'P', 'u', 'b', 'O', 'p', 't', 's', ' ' };
/*     */ 
/*     */   
/*  64 */   public static final char[] MQPS_PUBLISH_TIMESTAMP_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'P', 'u', 'b', 'T', 'i', 'm', 'e', ' ' };
/*     */ 
/*     */   
/*  67 */   public static final char[] MQPS_Q_MGR_NAME_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'Q', 'M', 'g', 'r', 'N', 'a', 'm', 'e', ' ' };
/*     */ 
/*     */   
/*  70 */   public static final char[] MQPS_Q_NAME_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'Q', 'N', 'a', 'm', 'e', ' ' };
/*     */ 
/*     */   
/*  73 */   public static final char[] MQPS_REASON_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'R', 'e', 'a', 's', 'o', 'n', ' ' };
/*     */ 
/*     */   
/*  76 */   public static final char[] MQPS_REASON_TEXT_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'R', 'e', 'a', 's', 'o', 'n', 'T', 'e', 'x', 't', ' ' };
/*     */ 
/*     */   
/*  79 */   public static final char[] MQPS_REGISTRATION_OPTIONS_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'R', 'e', 'g', 'O', 'p', 't', 's', ' ' };
/*     */ 
/*     */   
/*  82 */   public static final char[] MQPS_SEQUENCE_NUMBER_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'S', 'e', 'q', 'N', 'u', 'm', ' ' };
/*     */ 
/*     */   
/*  85 */   public static final char[] MQPS_STREAM_NAME_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'S', 't', 'r', 'e', 'a', 'm', 'N', 'a', 'm', 'e', ' ' };
/*     */ 
/*     */   
/*  88 */   public static final char[] MQPS_STRING_DATA_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'S', 't', 'r', 'i', 'n', 'g', 'D', 'a', 't', 'a', ' ' };
/*     */ 
/*     */   
/*  91 */   public static final char[] MQPS_SUBSCRIPTION_IDENTITY_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'S', 'u', 'b', 'I', 'd', 'e', 'n', 't', 'i', 't', 'y', ' ' };
/*     */ 
/*     */   
/*  94 */   public static final char[] MQPS_SUBSCRIPTION_NAME_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'S', 'u', 'b', 'N', 'a', 'm', 'e', ' ' };
/*     */ 
/*     */   
/*  97 */   public static final char[] MQPS_SUBSCRIPTION_USER_DATA_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'S', 'u', 'b', 'U', 's', 'e', 'r', 'D', 'a', 't', 'a', ' ' };
/*     */ 
/*     */   
/* 100 */   public static final char[] MQPS_TOPIC_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'T', 'o', 'p', 'i', 'c', ' ' };
/*     */ 
/*     */   
/* 103 */   public static final char[] MQPS_USER_ID_A = new char[] { ' ', 'M', 'Q', 'P', 'S', 'U', 's', 'e', 'r', 'I', 'd', ' ' };
/*     */ 
/*     */   
/* 106 */   public static final char[] MQPS_DELETE_PUBLICATION_A = new char[] { ' ', 'D', 'e', 'l', 'e', 't', 'e', 'P', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 109 */   public static final char[] MQPS_DEREGISTER_PUBLISHER_A = new char[] { ' ', 'D', 'e', 'r', 'e', 'g', 'P', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 112 */   public static final char[] MQPS_DEREGISTER_SUBSCRIBER_A = new char[] { ' ', 'D', 'e', 'r', 'e', 'g', 'S', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 115 */   public static final char[] MQPS_PUBLISH_A = new char[] { ' ', 'P', 'u', 'b', 'l', 'i', 's', 'h', ' ' };
/*     */   
/* 117 */   public static final char[] MQPS_REGISTER_PUBLISHER_A = new char[] { ' ', 'R', 'e', 'g', 'P', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 120 */   public static final char[] MQPS_REGISTER_SUBSCRIBER_A = new char[] { ' ', 'R', 'e', 'g', 'S', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 123 */   public static final char[] MQPS_REQUEST_UPDATE_A = new char[] { ' ', 'R', 'e', 'q', 'U', 'p', 'd', 'a', 't', 'e', ' ' };
/*     */ 
/*     */   
/* 126 */   public static final char[] MQPS_ADD_NAME_A = new char[] { ' ', 'A', 'd', 'd', 'N', 'a', 'm', 'e', ' ' };
/*     */   
/* 128 */   public static final char[] MQPS_ANONYMOUS_A = new char[] { ' ', 'A', 'n', 'o', 'n', ' ' };
/*     */   
/* 130 */   public static final char[] MQPS_CORREL_ID_AS_IDENTITY_A = new char[] { ' ', 'C', 'o', 'r', 'r', 'e', 'l', 'A', 's', 'I', 'd', ' ' };
/*     */ 
/*     */   
/* 133 */   public static final char[] MQPS_DEREGISTER_ALL_A = new char[] { ' ', 'D', 'e', 'r', 'e', 'g', 'A', 'l', 'l', ' ' };
/*     */ 
/*     */   
/* 136 */   public static final char[] MQPS_DIRECT_REQUESTS_A = new char[] { ' ', 'D', 'i', 'r', 'e', 'c', 't', 'R', 'e', 'q', ' ' };
/*     */ 
/*     */   
/* 139 */   public static final char[] MQPS_DUPLICATES_OK_A = new char[] { ' ', 'D', 'u', 'p', 's', 'O', 'K', ' ' };
/*     */   
/* 141 */   public static final char[] MQPS_FULL_RESPONSE_A = new char[] { ' ', 'F', 'u', 'l', 'l', 'R', 'e', 's', 'p', ' ' };
/*     */ 
/*     */   
/* 144 */   public static final char[] MQPS_INCLUDE_STREAM_NAME_A = new char[] { ' ', 'I', 'n', 'c', 'l', 'S', 't', 'r', 'e', 'a', 'm', 'N', 'a', 'm', 'e', ' ' };
/*     */ 
/*     */   
/* 147 */   public static final char[] MQPS_INFORM_IF_RETAINED_A = new char[] { ' ', 'I', 'n', 'f', 'o', 'r', 'm', 'I', 'f', 'R', 'e', 't', ' ' };
/*     */ 
/*     */   
/* 150 */   public static final char[] MQPS_IS_RETAINED_PUBLICATION_A = new char[] { ' ', 'I', 's', 'R', 'e', 't', 'a', 'i', 'n', 'e', 'd', 'P', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 153 */   public static final char[] MQPS_JOIN_EXCLUSIVE_A = new char[] { ' ', 'J', 'o', 'i', 'n', 'E', 'x', 'c', 'l', ' ' };
/*     */ 
/*     */   
/* 156 */   public static final char[] MQPS_JOIN_SHARED_A = new char[] { ' ', 'J', 'o', 'i', 'n', 'S', 'h', 'a', 'r', 'e', 'd', ' ' };
/*     */ 
/*     */   
/* 159 */   public static final char[] MQPS_LEAVE_ONLY_A = new char[] { ' ', 'L', 'e', 'a', 'v', 'e', 'O', 'n', 'l', 'y', ' ' };
/*     */ 
/*     */   
/* 162 */   public static final char[] MQPS_LOCAL_A = new char[] { ' ', 'L', 'o', 'c', 'a', 'l', ' ' };
/*     */   
/* 164 */   public static final char[] MQPS_LOCKED_A = new char[] { ' ', 'L', 'o', 'c', 'k', 'e', 'd', ' ' };
/*     */   
/* 166 */   public static final char[] MQPS_NEW_PUBLICATIONS_ONLY_A = new char[] { ' ', 'N', 'e', 'w', 'P', 'u', 'b', 's', 'O', 'n', 'l', 'y', ' ' };
/*     */ 
/*     */   
/* 169 */   public static final char[] MQPS_NO_ALTERATION_A = new char[] { ' ', 'N', 'o', 'A', 'l', 't', 'e', 'r', ' ' };
/*     */ 
/*     */   
/* 172 */   public static final char[] MQPS_NO_REGISTRATION_A = new char[] { ' ', 'N', 'o', 'R', 'e', 'g', ' ' };
/*     */   
/* 174 */   public static final char[] MQPS_NONE_A = new char[] { ' ', 'N', 'o', 'n', 'e', ' ' };
/*     */   
/* 176 */   public static final char[] MQPS_NON_PERSISTENT_A = new char[] { ' ', 'N', 'o', 'n', 'P', 'e', 'r', 's', ' ' };
/*     */ 
/*     */   
/* 179 */   public static final char[] MQPS_OTHER_SUBSCRIBERS_ONLY_A = new char[] { ' ', 'O', 't', 'h', 'e', 'r', 'S', 'u', 'b', 's', 'O', 'n', 'l', 'y', ' ' };
/*     */ 
/*     */   
/* 182 */   public static final char[] MQPS_PERSISTENT_A = new char[] { ' ', 'P', 'e', 'r', 's', ' ' };
/*     */   
/* 184 */   public static final char[] MQPS_PERSISTENT_AS_PUBLISH_A = new char[] { ' ', 'P', 'e', 'r', 's', 'A', 's', 'P', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 187 */   public static final char[] MQPS_PERSISTENT_AS_Q_A = new char[] { ' ', 'P', 'e', 'r', 's', 'A', 's', 'Q', 'u', 'e', 'u', 'e', ' ' };
/*     */ 
/*     */   
/* 190 */   public static final char[] MQPS_PUBLISH_ON_REQUEST_ONLY_A = new char[] { ' ', 'P', 'u', 'b', 'O', 'n', 'R', 'e', 'q', 'O', 'n', 'l', 'y', ' ' };
/*     */ 
/*     */   
/* 193 */   public static final char[] MQPS_RETAIN_PUBLICATION_A = new char[] { ' ', 'R', 'e', 't', 'a', 'i', 'n', 'P', 'u', 'b', ' ' };
/*     */ 
/*     */   
/* 196 */   public static final char[] MQPS_VARIABLE_USER_ID_A = new char[] { ' ', 'V', 'a', 'r', 'i', 'a', 'b', 'l', 'e', 'U', 's', 'e', 'r', 'I', 'd', ' ' };
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\CMQPSC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */