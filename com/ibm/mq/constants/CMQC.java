/*       */ package com.ibm.mq.constants;
/*       */ 
/*       */ import com.ibm.mq.jmqi.handles.Hconn;
/*       */ import com.ibm.mq.jmqi.handles.Hmsg;
/*       */ import com.ibm.mq.jmqi.handles.Hobj;
/*       */ import com.ibm.mq.jmqi.internal.HconnAdapter;
/*       */ import com.ibm.mq.jmqi.internal.HmsgAdapter;
/*       */ import com.ibm.mq.jmqi.internal.HobjAdapter;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public interface CMQC
/*       */ {
/*       */   public static final String copyright_notice = "Licensed Materials - Property of IBM (c) Copyright IBM Corp. 2008, 2023 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*       */   public static final String sccsid = "%Z% %W% %I% %E% %U%";
/*       */   
/*    63 */   public static final Hconn jmqi_MQHC_DEF_HCONN = (Hconn)new HconnAdapter()
/*       */     {
/*       */       public String toString()
/*       */       {
/*    67 */         return "MQHC_DEF_HCONN";
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*       */       public boolean isXAPrepared() {
/*    73 */         return false;
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       public void setXAPrepared(boolean prepared) {}
/*       */     };
/*       */ 
/*       */ 
/*       */   
/*    84 */   public static final Hconn jmqi_MQHC_UNUSABLE_HCONN = (Hconn)new HconnAdapter()
/*       */     {
/*       */       public String toString()
/*       */       {
/*    88 */         return "MQHC_UNUSABLE_HCONN";
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*       */       public boolean isXAPrepared() {
/*    94 */         return false;
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       public void setXAPrepared(boolean prepared) {}
/*       */     };
/*       */ 
/*       */ 
/*       */   
/*   105 */   public static final Hobj jmqi_MQHO_UNUSABLE_HOBJ = (Hobj)new HobjAdapter()
/*       */     {
/*       */       public int getIntegerHandle()
/*       */       {
/*   109 */         return -1;
/*       */       }
/*       */ 
/*       */       
/*       */       public String toString() {
/*   114 */         return "MQHO_UNUSABLE_HOBJ";
/*       */       }
/*       */     };
/*       */ 
/*       */   
/*   119 */   public static final Hobj jmqi_MQHO_NONE = (Hobj)new HobjAdapter()
/*       */     {
/*       */       public int getIntegerHandle()
/*       */       {
/*   123 */         return 0;
/*       */       }
/*       */ 
/*       */       
/*       */       public String toString() {
/*   128 */         return "MQHO_NONE";
/*       */       }
/*       */     };
/*       */ 
/*       */   
/*   133 */   public static final Hmsg jmqi_MQHM_NONE = (Hmsg)new HmsgAdapter()
/*       */     {
/*       */       public long getLongHandle()
/*       */       {
/*   137 */         return 0L;
/*       */       }
/*       */ 
/*       */       
/*       */       public String toString() {
/*   142 */         return "MQHM_NONE";
/*       */       }
/*       */     };
/*       */ 
/*       */   
/*   147 */   public static final Hmsg jmqi_MQHM_UNUSABLE_HMSG = (Hmsg)new HmsgAdapter()
/*       */     {
/*       */       public long getLongHandle()
/*       */       {
/*   151 */         return -1L;
/*       */       }
/*       */ 
/*       */       
/*       */       public String toString() {
/*   156 */         return "MQHM_UNUSABLE_HMSG";
/*       */       }
/*       */     };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQHC_DEF_HCONN = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQHC_UNUSABLE_HCONN = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQHC_UNASSOCIATED_HCONN = -3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_OPERATOR_MESSAGE_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_ABEND_CODE_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_ACCOUNTING_TOKEN_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_APPL_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_APPL_IDENTITY_DATA_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_APPL_NAME_LENGTH = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_APPL_ORIGIN_DATA_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_APPL_TAG_LENGTH = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_ARM_SUFFIX_LENGTH = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_ATTENTION_ID_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTH_INFO_CONN_NAME_LENGTH = 264;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTH_INFO_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTH_INFO_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTH_INFO_OCSP_URL_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTHENTICATOR_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTO_REORG_CATALOG_LENGTH = 44;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AUTO_REORG_TIME_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_BATCH_INTERFACE_ID_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_BRIDGE_NAME_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CANCEL_CODE_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CF_STRUC_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CF_STRUC_NAME_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CHANNEL_DATE_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CHANNEL_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CHANNEL_NAME_LENGTH = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CHANNEL_TIME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CHINIT_SERVICE_PARM_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CICS_FILE_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_AMQP_CLIENT_ID_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CLIENT_ID_LENGTH = 23;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CLIENT_USER_ID_LENGTH = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CLUSTER_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_COMM_INFO_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_COMM_INFO_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CONN_NAME_LENGTH = 264;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CONN_TAG_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CONNECTION_ID_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CORREL_ID_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CREATION_DATE_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CREATION_TIME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CSP_PASSWORD_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_DATE_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_DISTINGUISHED_NAME_LENGTH = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_DNS_GROUP_NAME_LENGTH = 18;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_DATA_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_INFO_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_NAME_LENGTH_AS400 = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_NAME_LENGTH_MVS = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_NAME_LENGTH_DEF = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_PD_AREA_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_EXIT_USER_AREA_LENGTH = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_FACILITY_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_FACILITY_LIKE_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_FORMAT_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_FUNCTION_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_GROUP_ID_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_APPL_FUNCTION_NAME_LENGTH = 10;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_INITIAL_KEY_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_INSTALLATION_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_INSTALLATION_NAME_LENGTH = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_INSTALLATION_PATH_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_JAAS_CONFIG_LENGTH = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LDAP_PASSWORD_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LDAP_BASE_DN_LENGTH = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LDAP_FIELD_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LDAP_CLASS_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LISTENER_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LISTENER_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LOCAL_ADDRESS_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LTERM_OVERRIDE_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LU_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LUWID_LENGTH = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MAX_EXIT_NAME_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MAX_MCA_USER_ID_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MAX_LDAP_MCA_USER_ID_LENGTH = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MAX_PROPERTY_NAME_LENGTH = 4095;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MAX_USER_ID_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MCA_JOB_NAME_LENGTH = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MCA_NAME_LENGTH = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MCA_USER_DATA_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MCA_USER_ID_LENGTH_WINDOWS = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MCA_USER_ID_LENGTH_OTHER = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LDAP_MCA_USER_ID_LENGTH_MVS = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_LDAP_MCA_USER_ID_LENGTH_OTHER = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MFS_MAP_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MODE_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MSG_HEADER_LENGTH = 4000;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MSG_ID_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_MSG_TOKEN_LENGTH = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_NAMELIST_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_NAMELIST_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_NHA_INSTANCE_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_OBJECT_INSTANCE_ID_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_OBJECT_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PASS_TICKET_APPL_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PASSWORD_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PROCESS_APPL_ID_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PROCESS_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PROCESS_ENV_DATA_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PROCESS_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PROCESS_USER_DATA_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PROGRAM_NAME_LENGTH = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PUT_APPL_NAME_LENGTH = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PUT_DATE_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_PUT_TIME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_Q_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_Q_MGR_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_Q_MGR_IDENTIFIER_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_Q_MGR_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_Q_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_QSG_NAME_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_REMOTE_SYS_ID_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SECURITY_ID_LENGTH = 40;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SELECTOR_LENGTH = 10240;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SERVICE_ARGS_LENGTH = 255;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SERVICE_COMMAND_LENGTH = 255;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SERVICE_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SERVICE_NAME_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SERVICE_PATH_LENGTH = 255;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SERVICE_STEP_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SHORT_CONN_NAME_LENGTH = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SHORT_DNAME_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_CIPHER_SPEC_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_CIPHER_SUITE_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_CRYPTO_HARDWARE_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_ENCRYP_KEY_REPO_PWD_LEN = 1536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_HANDSHAKE_STAGE_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_KEY_LIBRARY_LENGTH = 44;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_KEY_MEMBER_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_KEY_REPOSITORY_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_KEY_REPO_PWD_LEN = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_PEER_NAME_LENGTH = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SSL_SHORT_PEER_NAME_LENGTH = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_START_CODE_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_STORAGE_CLASS_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_STORAGE_CLASS_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUB_IDENTITY_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUB_POINT_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TCP_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TEMPORARY_Q_PREFIX_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TIME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TOPIC_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TOPIC_NAME_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TOPIC_STR_LENGTH = 10240;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TOTAL_EXIT_DATA_LENGTH = 999;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TOTAL_EXIT_NAME_LENGTH = 999;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TP_NAME_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TPIPE_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TRAN_INSTANCE_ID_LENGTH = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TRANSACTION_ID_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TRIGGER_DATA_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TRIGGER_PROGRAM_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TRIGGER_TERM_ID_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_TRIGGER_TRANS_ID_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_USER_ID_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_VERSION_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_XCF_GROUP_NAME_LENGTH = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_XCF_MEMBER_NAME_LENGTH = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SMDS_NAME_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CHLAUTH_DESC_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CUSTOM_LENGTH = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUITE_B_SIZE = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CERT_LABEL_LENGTH = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCC_OK = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCC_WARNING = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCC_FAILED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCC_UNKNOWN = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_APPL_FIRST = 900;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_APPL_LAST = 999;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ALIAS_BASE_Q_TYPE_ERROR = 2001;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ALREADY_CONNECTED = 2002;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BACKED_OUT = 2003;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BUFFER_ERROR = 2004;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BUFFER_LENGTH_ERROR = 2005;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHAR_ATTR_LENGTH_ERROR = 2006;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHAR_ATTRS_ERROR = 2007;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHAR_ATTRS_TOO_SHORT = 2008;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_BROKEN = 2009;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DATA_LENGTH_ERROR = 2010;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DYNAMIC_Q_NAME_ERROR = 2011;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ENVIRONMENT_ERROR = 2012;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_EXPIRY_ERROR = 2013;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FEEDBACK_ERROR = 2014;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GET_INHIBITED = 2016;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HANDLE_NOT_AVAILABLE = 2017;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HCONN_ERROR = 2018;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HOBJ_ERROR = 2019;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INHIBIT_VALUE_ERROR = 2020;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INT_ATTR_COUNT_ERROR = 2021;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INT_ATTR_COUNT_TOO_SMALL = 2022;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INT_ATTRS_ARRAY_ERROR = 2023;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYNCPOINT_LIMIT_REACHED = 2024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MAX_CONNS_LIMIT_REACHED = 2025;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MD_ERROR = 2026;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MISSING_REPLY_TO_Q = 2027;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_TYPE_ERROR = 2029;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_TOO_BIG_FOR_Q = 2030;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_TOO_BIG_FOR_Q_MGR = 2031;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_MSG_AVAILABLE = 2033;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_MSG_UNDER_CURSOR = 2034;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_AUTHORIZED = 2035;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_BROWSE = 2036;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_INPUT = 2037;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_INQUIRE = 2038;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_OUTPUT = 2039;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_SET = 2040;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_CHANGED = 2041;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_IN_USE = 2042;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_TYPE_ERROR = 2043;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OD_ERROR = 2044;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPTION_NOT_VALID_FOR_TYPE = 2045;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPTIONS_ERROR = 2046;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PERSISTENCE_ERROR = 2047;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PERSISTENT_NOT_ALLOWED = 2048;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PRIORITY_EXCEEDS_MAXIMUM = 2049;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PRIORITY_ERROR = 2050;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PUT_INHIBITED = 2051;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_DELETED = 2052;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_FULL = 2053;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_NOT_EMPTY = 2055;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_SPACE_NOT_AVAILABLE = 2056;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_TYPE_ERROR = 2057;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_NAME_ERROR = 2058;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_NOT_AVAILABLE = 2059;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REPORT_OPTIONS_ERROR = 2061;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SECOND_MARK_NOT_ALLOWED = 2062;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SECURITY_ERROR = 2063;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_COUNT_ERROR = 2065;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_LIMIT_EXCEEDED = 2066;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_ERROR = 2067;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_NOT_FOR_TYPE = 2068;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SIGNAL_OUTSTANDING = 2069;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SIGNAL_REQUEST_ACCEPTED = 2070;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STORAGE_NOT_AVAILABLE = 2071;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYNCPOINT_NOT_AVAILABLE = 2072;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRIGGER_CONTROL_ERROR = 2075;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRIGGER_DEPTH_ERROR = 2076;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRIGGER_MSG_PRIORITY_ERR = 2077;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRIGGER_TYPE_ERROR = 2078;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRUNCATED_MSG_ACCEPTED = 2079;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRUNCATED_MSG_FAILED = 2080;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_ALIAS_BASE_Q = 2082;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_OBJECT_NAME = 2085;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_OBJECT_Q_MGR = 2086;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_REMOTE_Q_MGR = 2087;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WAIT_INTERVAL_ERROR = 2090;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XMIT_Q_TYPE_ERROR = 2091;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XMIT_Q_USAGE_ERROR = 2092;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_PASS_ALL = 2093;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_PASS_IDENT = 2094;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_SET_ALL = 2095;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN_FOR_SET_IDENT = 2096;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONTEXT_HANDLE_ERROR = 2097;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONTEXT_NOT_AVAILABLE = 2098;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SIGNAL1_ERROR = 2099;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_ALREADY_EXISTS = 2100;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_DAMAGED = 2101;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RESOURCE_PROBLEM = 2102;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ANOTHER_Q_MGR_CONNECTED = 2103;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_REPORT_OPTION = 2104;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STORAGE_CLASS_ERROR = 2105;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_COD_NOT_VALID_FOR_XCF_Q = 2106;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XWAIT_CANCELED = 2107;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XWAIT_ERROR = 2108;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUPPRESSED_BY_EXIT = 2109;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FORMAT_ERROR = 2110;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOURCE_CCSID_ERROR = 2111;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOURCE_INTEGER_ENC_ERROR = 2112;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOURCE_DECIMAL_ENC_ERROR = 2113;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOURCE_FLOAT_ENC_ERROR = 2114;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TARGET_CCSID_ERROR = 2115;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TARGET_INTEGER_ENC_ERROR = 2116;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TARGET_DECIMAL_ENC_ERROR = 2117;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TARGET_FLOAT_ENC_ERROR = 2118;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_CONVERTED = 2119;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONVERTED_MSG_TOO_BIG = 2120;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TRUNCATED = 2120;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_EXTERNAL_PARTICIPANTS = 2121;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PARTICIPANT_NOT_AVAILABLE = 2122;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OUTCOME_MIXED = 2123;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OUTCOME_PENDING = 2124;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BRIDGE_STARTED = 2125;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BRIDGE_STOPPED = 2126;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_STORAGE_SHORTAGE = 2127;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UOW_IN_PROGRESS = 2128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_CONN_LOAD_ERROR = 2129;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_SERV_LOAD_ERROR = 2130;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_DEFS_ERROR = 2131;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_DEFS_LOAD_ERROR = 2132;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_CONV_LOAD_ERROR = 2133;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BO_ERROR = 2134;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DH_ERROR = 2135;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTIPLE_REASONS = 2136;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPEN_FAILED = 2137;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_DISC_LOAD_ERROR = 2138;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CNO_ERROR = 2139;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CICS_WAIT_FAILED = 2140;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DLH_ERROR = 2141;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HEADER_ERROR = 2142;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOURCE_LENGTH_ERROR = 2143;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TARGET_LENGTH_ERROR = 2144;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOURCE_BUFFER_ERROR = 2145;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TARGET_BUFFER_ERROR = 2146;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCOMPLETE_TRANSACTION = 2147;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_IIH_ERROR = 2148;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PCF_ERROR = 2149;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DBCS_ERROR = 2150;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_NAME_ERROR = 2152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_Q_MGR_NAME_ERROR = 2153;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECS_PRESENT_ERROR = 2154;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_RECORDS_ERROR = 2155;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RESPONSE_RECORDS_ERROR = 2156;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ASID_MISMATCH = 2157;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PMO_RECORD_FLAGS_ERROR = 2158;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PUT_MSG_RECORDS_ERROR = 2159;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONN_ID_IN_USE = 2160;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_QUIESCING = 2161;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_STOPPING = 2162;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DUPLICATE_RECOV_COORD = 2163;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PMO_ERROR = 2173;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_API_EXIT_NOT_FOUND = 2182;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_API_EXIT_LOAD_ERROR = 2183;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REMOTE_Q_NAME_ERROR = 2184;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_PERSISTENCE = 2185;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GMO_ERROR = 2186;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CICS_BRIDGE_RESTRICTION = 2187;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STOPPED_BY_CLUSTER_EXIT = 2188;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLUSTER_RESOLUTION_ERROR = 2189;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONVERTED_STRING_TOO_BIG = 2190;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TMC_ERROR = 2191;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STORAGE_MEDIUM_FULL = 2192;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PAGESET_FULL = 2192;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PAGESET_ERROR = 2193;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NAME_NOT_VALID_FOR_TYPE = 2194;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNEXPECTED_ERROR = 2195;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_XMIT_Q = 2196;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_DEF_XMIT_Q = 2197;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DEF_XMIT_Q_TYPE_ERROR = 2198;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DEF_XMIT_Q_USAGE_ERROR = 2199;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_MARKED_BROWSE_CO_OP = 2200;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NAME_IN_USE = 2201;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_QUIESCING = 2202;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_STOPPING = 2203;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADAPTER_NOT_AVAILABLE = 2204;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_ID_ERROR = 2206;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CORREL_ID_ERROR = 2207;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FILE_SYSTEM_ERROR = 2208;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_MSG_LOCKED = 2209;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOAP_DOTNET_ERROR = 2210;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOAP_AXIS_ERROR = 2211;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SOAP_URL_ERROR = 2212;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FILE_NOT_AUDITED = 2216;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_NOT_AUTHORIZED = 2217;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_TOO_BIG_FOR_CHANNEL = 2218;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CALL_IN_PROGRESS = 2219;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RMH_ERROR = 2220;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_ACTIVE = 2222;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_NOT_ACTIVE = 2223;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_DEPTH_HIGH = 2224;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_DEPTH_LOW = 2225;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_SERVICE_INTERVAL_HIGH = 2226;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_SERVICE_INTERVAL_OK = 2227;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_HEADER_FIELD_ERROR = 2228;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RAS_PROPERTY_ERROR = 2229;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNIT_OF_WORK_NOT_STARTED = 2232;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_AUTO_DEF_OK = 2233;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_AUTO_DEF_ERROR = 2234;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFH_ERROR = 2235;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFIL_ERROR = 2236;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFIN_ERROR = 2237;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFSL_ERROR = 2238;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFST_ERROR = 2239;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCOMPLETE_GROUP = 2241;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCOMPLETE_MSG = 2242;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_CCSIDS = 2243;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_ENCODINGS = 2244;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_UOW = 2245;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INVALID_MSG_UNDER_CURSOR = 2246;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MATCH_OPTIONS_ERROR = 2247;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MDE_ERROR = 2248;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_FLAGS_ERROR = 2249;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_SEQ_NUMBER_ERROR = 2250;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OFFSET_ERROR = 2251;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ORIGINAL_LENGTH_ERROR = 2252;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SEGMENT_LENGTH_ZERO = 2253;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UOW_NOT_AVAILABLE = 2255;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WRONG_GMO_VERSION = 2256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WRONG_MD_VERSION = 2257;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GROUP_ID_ERROR = 2258;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_BROWSE = 2259;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XQH_ERROR = 2260;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SRC_ENV_ERROR = 2261;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SRC_NAME_ERROR = 2262;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DEST_ENV_ERROR = 2263;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DEST_NAME_ERROR = 2264;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TM_ERROR = 2265;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLUSTER_EXIT_ERROR = 2266;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLUSTER_EXIT_LOAD_ERROR = 2267;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLUSTER_PUT_INHIBITED = 2268;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLUSTER_RESOURCE_ERROR = 2269;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_DESTINATIONS_AVAILABLE = 2270;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONN_TAG_IN_USE = 2271;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PARTIALLY_CONVERTED = 2272;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_ERROR = 2273;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPTION_ENVIRONMENT_ERROR = 2274;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CD_ERROR = 2277;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLIENT_CONN_ERROR = 2278;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_STOPPED_BY_USER = 2279;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HCONFIG_ERROR = 2280;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FUNCTION_ERROR = 2281;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_STARTED = 2282;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_STOPPED = 2283;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_CONV_ERROR = 2284;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SERVICE_NOT_AVAILABLE = 2285;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INITIALIZATION_FAILED = 2286;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TERMINATION_FAILED = 2287;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_Q_NAME = 2288;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SERVICE_ERROR = 2289;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_ALREADY_EXISTS = 2290;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_USER_ID_NOT_AVAILABLE = 2291;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_ENTITY = 2292;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_AUTH_ENTITY = 2293;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_REF_OBJECT = 2294;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_ACTIVATED = 2295;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_NOT_ACTIVATED = 2296;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UOW_CANCELED = 2297;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FUNCTION_NOT_SUPPORTED = 2298;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_TYPE_ERROR = 2299;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_COMMAND_TYPE_ERROR = 2300;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTIPLE_INSTANCE_ERROR = 2301;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYSTEM_ITEM_NOT_ALTERABLE = 2302;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BAG_CONVERSION_ERROR = 2303;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_OUT_OF_RANGE = 2304;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_NOT_UNIQUE = 2305;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INDEX_NOT_PRESENT = 2306;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STRING_ERROR = 2307;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ENCODING_NOT_SUPPORTED = 2308;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_NOT_PRESENT = 2309;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OUT_SELECTOR_ERROR = 2310;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STRING_TRUNCATED = 2311;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_WRONG_TYPE = 2312;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_ITEM_TYPE = 2313;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INDEX_ERROR = 2314;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYSTEM_BAG_NOT_ALTERABLE = 2315;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ITEM_COUNT_ERROR = 2316;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FORMAT_NOT_SUPPORTED = 2317;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_NOT_SUPPORTED = 2318;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ITEM_VALUE_ERROR = 2319;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HBAG_ERROR = 2320;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PARAMETER_MISSING = 2321;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CMD_SERVER_NOT_AVAILABLE = 2322;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STRING_LENGTH_ERROR = 2323;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INQUIRY_COMMAND_ERROR = 2324;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NESTED_BAG_NOT_SUPPORTED = 2325;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BAG_WRONG_TYPE = 2326;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ITEM_TYPE_ERROR = 2327;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYSTEM_BAG_NOT_DELETABLE = 2328;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYSTEM_ITEM_NOT_DELETABLE = 2329;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CODED_CHAR_SET_ID_ERROR = 2330;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_TOKEN_ERROR = 2331;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MISSING_WIH = 2332;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WIH_ERROR = 2333;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_ERROR = 2334;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_STRING_ERROR = 2335;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_COMMAND_ERROR = 2336;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_PARM_ERROR = 2337;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_DUPLICATE_PARM = 2338;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_PARM_MISSING = 2339;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHAR_CONVERSION_ERROR = 2340;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UCS2_CONVERSION_ERROR = 2341;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DB2_NOT_AVAILABLE = 2342;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_NOT_UNIQUE = 2343;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONN_TAG_NOT_RELEASED = 2344;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CF_NOT_AVAILABLE = 2345;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CF_STRUC_IN_USE = 2346;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CF_STRUC_LIST_HDR_IN_USE = 2347;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CF_STRUC_AUTH_FAILED = 2348;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CF_STRUC_ERROR = 2349;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONN_TAG_NOT_USABLE = 2350;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GLOBAL_UOW_CONFLICT = 2351;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_LOCAL_UOW_CONFLICT = 2352;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HANDLE_IN_USE_FOR_UOW = 2353;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UOW_ENLISTMENT_ERROR = 2354;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UOW_MIX_NOT_SUPPORTED = 2355;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WXP_ERROR = 2356;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CURRENT_RECORD_ERROR = 2357;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NEXT_OFFSET_ERROR = 2358;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_RECORD_AVAILABLE = 2359;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_LEVEL_INCOMPATIBLE = 2360;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NEXT_RECORD_ERROR = 2361;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BACKOUT_THRESHOLD_REACHED = 2362;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_NOT_MATCHED = 2363;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_JMS_FORMAT_ERROR = 2364;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SEGMENTS_NOT_SUPPORTED = 2365;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WRONG_CF_LEVEL = 2366;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONFIG_CREATE_OBJECT = 2367;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONFIG_CHANGE_OBJECT = 2368;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONFIG_DELETE_OBJECT = 2369;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONFIG_REFRESH_OBJECT = 2370;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_SSL_ERROR = 2371;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PARTICIPANT_NOT_DEFINED = 2372;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CF_STRUC_FAILED = 2373;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_API_EXIT_ERROR = 2374;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_API_EXIT_INIT_ERROR = 2375;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_API_EXIT_TERM_ERROR = 2376;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_EXIT_REASON_ERROR = 2377;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RESERVED_VALUE_ERROR = 2378;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_DATA_AVAILABLE = 2379;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SCO_ERROR = 2380;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_KEY_REPOSITORY_ERROR = 2381;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CRYPTO_HARDWARE_ERROR = 2382;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_AUTH_INFO_REC_COUNT_ERROR = 2383;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_AUTH_INFO_REC_ERROR = 2384;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_AIR_ERROR = 2385;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_AUTH_INFO_TYPE_ERROR = 2386;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_AUTH_INFO_CONN_NAME_ERROR = 2387;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_LDAP_USER_NAME_ERROR = 2388;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_LDAP_USER_NAME_LENGTH_ERR = 2389;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_LDAP_PASSWORD_ERROR = 2390;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_ALREADY_INITIALIZED = 2391;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_CONFIG_ERROR = 2392;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_INITIALIZATION_ERROR = 2393;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_INDEX_TYPE_ERROR = 2394;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFBS_ERROR = 2395;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_NOT_ALLOWED = 2396;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_JSSE_ERROR = 2397;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_PEER_NAME_MISMATCH = 2398;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_PEER_NAME_ERROR = 2399;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNSUPPORTED_CIPHER_SUITE = 2400;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_CERTIFICATE_REVOKED = 2401;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_CERT_STORE_ERROR = 2402;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLIENT_EXIT_LOAD_ERROR = 2406;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLIENT_EXIT_ERROR = 2407;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UOW_COMMITTED = 2408;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_KEY_RESET_ERROR = 2409;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_COMPONENT_NAME = 2410;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_LOGGER_STATUS = 2411;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_COMMAND_MQSC = 2412;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_COMMAND_PCF = 2413;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFIF_ERROR = 2414;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFSF_ERROR = 2415;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFGR_ERROR = 2416;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_NOT_ALLOWED_IN_GROUP = 2417;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FILTER_OPERATOR_ERROR = 2418;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NESTED_SELECTOR_ERROR = 2419;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_EPH_ERROR = 2420;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_FORMAT_ERROR = 2421;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CFBF_ERROR = 2422;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CLIENT_CHANNEL_CONFLICT = 2423;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SD_ERROR = 2424;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TOPIC_STRING_ERROR = 2425;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STS_ERROR = 2426;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_SUBSCRIPTION = 2428;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUBSCRIPTION_IN_USE = 2429;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STAT_TYPE_ERROR = 2430;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUB_USER_DATA_ERROR = 2431;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUB_ALREADY_EXISTS = 2432;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_IDENTITY_MISMATCH = 2434;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ALTER_SUB_ERROR = 2435;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DURABILITY_NOT_ALLOWED = 2436;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_RETAINED_MSG = 2437;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SRO_ERROR = 2438;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUB_NAME_ERROR = 2440;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OBJECT_STRING_ERROR = 2441;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTY_NAME_ERROR = 2442;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SEGMENTATION_NOT_ALLOWED = 2443;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CBD_ERROR = 2444;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CTLO_ERROR = 2445;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_CALLBACKS_ACTIVE = 2446;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CALLBACK_NOT_REGISTERED = 2448;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPTIONS_CHANGED = 2457;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_READ_AHEAD_MSGS = 2458;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_SYNTAX_ERROR = 2459;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HMSG_ERROR = 2460;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CMHO_ERROR = 2461;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DMHO_ERROR = 2462;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SMPO_ERROR = 2463;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_IMPO_ERROR = 2464;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTY_NAME_TOO_BIG = 2465;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROP_VALUE_NOT_CONVERTED = 2466;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROP_TYPE_NOT_SUPPORTED = 2467;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTY_VALUE_TOO_BIG = 2469;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROP_CONV_NOT_SUPPORTED = 2470;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTY_NOT_AVAILABLE = 2471;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROP_NUMBER_FORMAT_ERROR = 2472;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTY_TYPE_ERROR = 2473;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTIES_TOO_BIG = 2478;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PUT_NOT_RETAINED = 2479;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ALIAS_TARGTYPE_CHANGED = 2480;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DMPO_ERROR = 2481;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PD_ERROR = 2482;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CALLBACK_TYPE_ERROR = 2483;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CBD_OPTIONS_ERROR = 2484;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MAX_MSG_LENGTH_ERROR = 2485;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CALLBACK_ROUTINE_ERROR = 2486;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CALLBACK_LINK_ERROR = 2487;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPERATION_ERROR = 2488;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BMHO_ERROR = 2489;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNSUPPORTED_PROPERTY = 2490;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROP_NAME_NOT_CONVERTED = 2492;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GET_ENABLED = 2494;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MODULE_NOT_FOUND = 2495;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MODULE_INVALID = 2496;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MODULE_ENTRY_NOT_FOUND = 2497;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MIXED_CONTENT_NOT_ALLOWED = 2498;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_HANDLE_IN_USE = 2499;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HCONN_ASYNC_ACTIVE = 2500;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MHBO_ERROR = 2501;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PUBLICATION_FAILURE = 2502;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUB_INHIBITED = 2503;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_ALWAYS_FALSE = 2504;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XEPO_ERROR = 2507;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DURABILITY_NOT_ALTERABLE = 2509;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_TOPIC_NOT_ALTERABLE = 2510;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUBLEVEL_NOT_ALTERABLE = 2512;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTY_NAME_LENGTH_ERR = 2513;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DUPLICATE_GROUP_SUB = 2514;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GROUPING_NOT_ALTERABLE = 2515;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_INVALID_FOR_TYPE = 2516;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HOBJ_QUIESCED = 2517;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HOBJ_QUIESCED_NO_MSGS = 2518;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTION_STRING_ERROR = 2519;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RES_OBJECT_STRING_ERROR = 2520;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_SUSPENDED = 2521;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INVALID_DESTINATION = 2522;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INVALID_SUBSCRIPTION = 2523;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTOR_NOT_ALTERABLE = 2524;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RETAINED_MSG_Q_ERROR = 2525;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RETAINED_NOT_DELIVERED = 2526;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RFH_RESTRICTED_FORMAT_ERR = 2527;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_STOPPED = 2528;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ASYNC_UOW_CONFLICT = 2529;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ASYNC_XA_CONFLICT = 2530;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PUBSUB_INHIBITED = 2531;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MSG_HANDLE_COPY_FAILURE = 2532;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DEST_CLASS_NOT_ALTERABLE = 2533;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OPERATION_NOT_ALLOWED = 2534;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ACTION_ERROR = 2535;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_NOT_AVAILABLE = 2537;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HOST_NOT_AVAILABLE = 2538;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_CONFIG_ERROR = 2539;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_UNKNOWN_CHANNEL_NAME = 2540;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_LOOPING_PUBLICATION = 2541;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ALREADY_JOINED = 2542;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STANDBY_Q_MGR = 2543;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECTING = 2544;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECTED = 2545;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECT_QMID_MISMATCH = 2546;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECT_INCOMPATIBLE = 2547;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECT_FAILED = 2548;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CALL_INTERRUPTED = 2549;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_SUBS_MATCHED = 2550;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SELECTION_NOT_AVAILABLE = 2551;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_SSL_WARNING = 2552;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OCSP_URL_ERROR = 2553;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONTENT_ERROR = 2554;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECT_Q_MGR_REQD = 2555;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_RECONNECT_TIMED_OUT = 2556;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PUBLISH_EXIT_ERROR = 2557;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_COMMINFO_ERROR = 2558;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DEF_SYNCPOINT_INHIBITED = 2559;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTICAST_ONLY = 2560;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DATA_SET_NOT_AVAILABLE = 2561;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GROUPING_NOT_ALLOWED = 2562;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_GROUP_ADDRESS_ERROR = 2563;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTICAST_CONFIG_ERROR = 2564;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTICAST_INTERFACE_ERROR = 2565;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTICAST_SEND_ERROR = 2566;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MULTICAST_INTERNAL_ERROR = 2567;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONNECTION_NOT_AVAILABLE = 2568;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SYNCPOINT_NOT_ALLOWED = 2569;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SSL_ALT_PROVIDER_REQUIRED = 2570;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MCAST_PUB_STATUS = 2571;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_MCAST_SUB_STATUS = 2572;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PRECONN_EXIT_LOAD_ERROR = 2573;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PRECONN_EXIT_NOT_FOUND = 2574;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PRECONN_EXIT_ERROR = 2575;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CD_ARRAY_ERROR = 2576;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_BLOCKED = 2577;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CHANNEL_BLOCKED_WARNING = 2578;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUBSCRIPTION_CREATE = 2579;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUBSCRIPTION_DELETE = 2580;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUBSCRIPTION_CHANGE = 2581;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUBSCRIPTION_REFRESH = 2582;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INSTALLATION_MISMATCH = 2583;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_PRIVILEGED = 2584;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PROPERTIES_DISABLED = 2586;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_HMSG_NOT_AVAILABLE = 2587;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_EXIT_PROPS_NOT_SUPPORTED = 2588;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INSTALLATION_MISSING = 2589;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_FASTPATH_NOT_AVAILABLE = 2590;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CIPHER_SPEC_NOT_SUITE_B = 2591;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUITE_B_ERROR = 2592;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CERT_VAL_POLICY_ERROR = 2593;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_PASSWORD_PROTECTION_ERROR = 2594;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CSP_ERROR = 2595;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CERT_LABEL_NOT_ALLOWED = 2596;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ADMIN_TOPIC_STRING_ERROR = 2598;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_AMQP_NOT_AVAILABLE = 2599;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CCDT_URL_ERROR = 2600;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_Q_MGR_RECONNECT_REQUESTED = 2601;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BNO_ERROR = 2602;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_OUTBOUND_SNI_NOT_VALID = 2603;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REOPEN_EXCL_INPUT_ERROR = 6100;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REOPEN_INQUIRE_ERROR = 6101;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REOPEN_SAVED_CONTEXT_ERR = 6102;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REOPEN_TEMPORARY_Q_ERROR = 6103;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ATTRIBUTE_LOCKED = 6104;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CURSOR_NOT_VALID = 6105;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ENCODING_ERROR = 6106;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STRUC_ID_ERROR = 6107;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NULL_POINTER = 6108;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_CONNECTION_REFERENCE = 6109;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NO_BUFFER = 6110;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BINARY_DATA_LENGTH_ERROR = 6111;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_BUFFER_NOT_AUTOMATIC = 6112;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INSUFFICIENT_BUFFER = 6113;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INSUFFICIENT_DATA = 6114;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DATA_TRUNCATED = 6115;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_ZERO_LENGTH = 6116;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NEGATIVE_LENGTH = 6117;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NEGATIVE_OFFSET = 6118;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_FORMAT = 6119;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_OBJECT_STATE = 6120;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONTEXT_OBJECT_NOT_VALID = 6121;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_CONTEXT_OPEN_ERROR = 6122;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_STRUC_LENGTH_ERROR = 6123;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_CONNECTED = 6124;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_NOT_OPEN = 6125;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_DISTRIBUTION_LIST_EMPTY = 6126;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_INCONSISTENT_OPEN_OPTIONS = 6127;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_WRONG_VERSION = 6128;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_REFERENCE_ERROR = 6129;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_XR_NOT_AVAILABLE = 6130;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRC_SUB_JOIN_NOT_ALTERABLE = 29440;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQT_LOCAL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQT_MODEL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQT_ALIAS = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQT_REMOTE = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQT_CLUSTER = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCQT_LOCAL_Q = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCQT_ALIAS_Q = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCQT_REMOTE_Q = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCQT_Q_MGR_ALIAS = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQT_ALL = 1001;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQDT_PREDEFINED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQDT_PERMANENT_DYNAMIC = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQDT_TEMPORARY_DYNAMIC = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQDT_SHARED_DYNAMIC = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_GET_INHIBITED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_GET_ALLOWED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_PUT_INHIBITED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_PUT_ALLOWED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_SHAREABLE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_NOT_SHAREABLE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_BACKOUT_HARDENED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQA_BACKOUT_NOT_HARDENED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDS_PRIORITY = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDS_FIFO = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNPM_CLASS_NORMAL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNPM_CLASS_HIGH = 10;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTC_OFF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTC_ON = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTT_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTT_FIRST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTT_EVERY = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTT_DEPTH = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTRIGGER_RESTART_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTRIGGER_RESTART_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUS_NORMAL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUS_TRANSMISSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDL_SUPPORTED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDL_NOT_SUPPORTED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIT_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIT_MSG_ID = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIT_CORREL_ID = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIT_MSG_TOKEN = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIT_GROUP_ID = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBND_BIND_ON_OPEN = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBND_BIND_NOT_FIXED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBND_BIND_ON_GROUP = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_ALL = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_Q_MGR = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_COPY = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_SHARED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_GROUP = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_PRIVATE = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQSGD_LIVE = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREORG_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREORG_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQFS_DEFAULT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCEX_NOLIMIT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCEX_AS_PARENT = -2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREADA_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREADA_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREADA_DISABLED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREADA_INHIBITED = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQREADA_BACKLOG = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPROP_COMPATIBILITY = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPROP_NONE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPROP_ALL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPROP_FORCE_MQRFH2 = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPROP_V6COMPAT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQST_BEST_EFFORT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQST_MUST_DUP = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNC_MAX_NAMELIST_NAME_COUNT = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNT_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNT_Q = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNT_CLUSTER = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNT_AUTH_INFO = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQNT_ALL = 1001;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFR_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFR_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRECAUTO_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRECAUTO_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFCONLOS_TERMINATE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFCONLOS_TOLERATE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFCONLOS_ASQMGR = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_TYPE_COMMAND = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_TYPE_SERVER = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_CHECK_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_CHECK_ALL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_CHECK_Q_MGR_NAME = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_CHECK_NET_ADDR = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_CHECK_CHANNEL_NAME = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_TYPE_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_TYPE_ALL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_TYPE_SVR = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_TYPE_SDR = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_TYPE_RCVR = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQADOPT_TYPE_CLUSRCVR = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAUTO_START_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAUTO_START_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCHAD_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCHAD_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCLWL_USEQ_LOCAL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCLWL_USEQ_ANY = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCLWL_USEQ_AS_Q_MGR = -3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_1 = 100;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_101 = 101;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_110 = 110;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_114 = 114;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_120 = 120;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_200 = 200;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_201 = 201;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_210 = 210;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_211 = 211;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_220 = 220;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_221 = 221;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_230 = 230;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_320 = 320;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_420 = 420;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_500 = 500;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_510 = 510;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_520 = 520;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_530 = 530;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_531 = 531;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_600 = 600;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_700 = 700;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_701 = 701;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_710 = 710;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_711 = 711;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_750 = 750;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_800 = 800;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_801 = 801;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_802 = 802;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_900 = 900;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_901 = 901;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_902 = 902;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_903 = 903;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_904 = 904;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_905 = 905;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_910 = 910;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_911 = 911;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_912 = 912;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_913 = 913;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_914 = 914;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_915 = 915;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_920 = 920;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_921 = 921;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_922 = 922;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_923 = 923;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_924 = 924;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_925 = 925;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_930 = 930;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_931 = 931;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_LEVEL_932 = 932;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMDL_CURRENT_LEVEL = 932;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSRV_CONVERT_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSRV_CONVERT_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSRV_DLQ_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSRV_DLQ_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDNSWLM_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDNSWLM_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEXPI_OFF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIGQ_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIGQ_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIGQPA_DEFAULT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIGQPA_CONTEXT = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIGQPA_ONLY_IGQ = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIGQPA_ALTERNATE_OR_IGQ = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIPADDR_IPV4 = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIPADDR_IPV6 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMMBI_UNLIMITED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_NOT_AVAILABLE = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_NONE = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_Q_MGR = -3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_OFF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_ON = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_LOW = 17;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_MEDIUM = 33;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMON_HIGH = 65;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFUN_TYPE_UNKNOWN = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFUN_TYPE_JVM = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFUN_TYPE_PROGRAM = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFUN_TYPE_PROCEDURE = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFUN_TYPE_USERDEF = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFUN_TYPE_COMMAND = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTV_DETAIL_LOW = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTV_DETAIL_MEDIUM = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTV_DETAIL_HIGH = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_MVS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_OS390 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_ZOS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_OS2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_AIX = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_UNIX = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_OS400 = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_WINDOWS = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_WINDOWS_NT = 11;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_VMS = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_NSK = 13;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_NSS = 13;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_OPEN_TP1 = 15;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_VM = 18;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_TPF = 23;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_VSE = 27;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPL_APPLIANCE = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPROP_UNRESTRICTED_LENGTH = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSM_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSM_COMPAT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSM_ENABLED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSCLUS_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSCLUS_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQMOPT_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQMOPT_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQQMOPT_REPLY = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRCVTIME_MULTIPLY = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRCVTIME_ADD = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRCVTIME_EQUAL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRECORDING_DISABLED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRECORDING_Q = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRECORDING_MSG = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCYC_UPPER = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCYC_MIXED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSQQM_USE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSQQM_IGNORE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSSL_FIPS_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSSL_FIPS_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSP_AVAILABLE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSP_NOT_AVAILABLE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_CONTROL_Q_MGR = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_CONTROL_Q_MGR_START = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_CONTROL_MANUAL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_STATUS_STOPPED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_STATUS_STARTING = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_STATUS_RUNNING = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_STATUS_STOPPING = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSVC_STATUS_RETRYING = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTCPKEEP_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTCPKEEP_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTCPSTACK_SINGLE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTCPSTACK_MULTIPLE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTRAXSTR_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTRAXSTR_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCAP_NOT_SUPPORTED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCAP_SUPPORTED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCAP_EXPIRED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMEDIMGSCHED_MANUAL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMEDIMGSCHED_AUTO = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMEDIMGINTVL_OFF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMEDIMGLOGLN_OFF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMGRCOV_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMGRCOV_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMGRCOV_AS_Q_MGR = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLV_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLV_ALL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLV_ALL_DUR = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLV_ALL_AVAIL = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMASTER_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMASTER_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCOPE_ALL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCOPE_AS_PARENT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCOPE_QMGR = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSUB_DURABLE_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSUB_DURABLE_ALLOWED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSUB_DURABLE_INHIBITED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_BLOCK = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_PASSTHRU = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_SUB_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_SUB_INHIBITED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_SUB_ALLOWED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_PROXY_SUB_FORCE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_PROXY_SUB_FIRSTUSE = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_PUB_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_PUB_INHIBITED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTA_PUB_ALLOWED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTOPT_LOCAL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTOPT_CLUSTER = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTOPT_ALL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMC_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMC_ENABLED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMC_DISABLED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMC_ONLY = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIT_MULTICAST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDC_MANAGED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDC_PROVIDED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSPROP_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSPROP_COMPAT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSPROP_RFH2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPSPROP_MSGPROP = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRU_PUBLISH_ON_REQUEST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRU_PUBLISH_ALL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSUB_DURABLE_ALL = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSUB_DURABLE_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSUB_DURABLE_NO = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTSCOPE_QMGR = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTSCOPE_ALL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQVU_FIXED_USER = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQVU_ANY_USER = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQWS_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQWS_CHAR = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQWS_TOPIC = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUSRC_MAP = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUSRC_NOACCESS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUSRC_CHANNEL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQWARN_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQWARN_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_8K = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_16K = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_32K = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_64K = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_128K = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_256K = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_512K = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_1024K = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSB_1M = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSE_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSE_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDSE_NO = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFOFFLD_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFOFFLD_SMDS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFOFFLD_DB2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCFOFFLD_BOTH = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUSEDLQ_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUSEDLQ_NO = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQUSEDLQ_YES = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CCSID_PROPERTY = "CCSID";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_PROPERTY = "channel";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BALANCING_APPLICATION_TYPE_PROPERTY = "Balance Application Type";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_APPLICATION_TYPE_REQUEST_REPLY = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_APPLICATION_TYPE_SIMPLE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BALANCING_OPTIONS_PROPERTY = "Balance Options";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_OPTIONS_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_OPTIONS_IGNORE_TRANSACTIONS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BALANCING_TIMEOUT_PROPERTY = "Balance Timeout";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_TIMEOUT_AS_DEFAULT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_TIMEOUT_IMMEDIATE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int BALANCING_TIMEOUT_NEVER = -2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CONNECT_OPTIONS_PROPERTY = "connectOptions";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CONNTAG_PROPERTY = "ConnTag Property";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String HOST_NAME_PROPERTY = "hostname";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String ORB_PROPERTY = "ORB";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String PASSWORD_PROPERTY = "password";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String PORT_PROPERTY = "port";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_RECEIVE_EXIT_PROPERTY = "channelReceiveExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_RECEIVE_EXIT_USER_DATA_PROPERTY = "channelReceiveExitUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_SECURITY_EXIT_PROPERTY = "channelSecurityExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_SECURITY_EXIT_USER_DATA_PROPERTY = "channelSecurityExitUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String EXIT_CLASSPATH_PROPERTY = "exitClasspath";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_SEND_EXIT_PROPERTY = "channelSendExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CHANNEL_SEND_EXIT_USER_DATA_PROPERTY = "channelSendExitUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String RECEIVE_EXIT_PROPERTY = "receiveExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String RECEIVE_EXIT_USER_DATA_PROPERTY = "receiveExitUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String SECURITY_EXIT_PROPERTY = "securityExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String SECURITY_EXIT_USER_DATA_PROPERTY = "securityExitUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String SEND_EXIT_PROPERTY = "sendExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String SEND_EXIT_USER_DATA_PROPERTY = "sendExitUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MSG_EXIT_PROPERTY = "msgExit";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String APPNAME_PROPERTY = "APPNAME";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String TRANSPORT_PROPERTY = "transport";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String TRANSPORT_MQSERIES = "MQSeries";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String TRANSPORT_MQSERIES_CLIENT = "MQSeries Client";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String TRANSPORT_MQSERIES_BINDINGS = "MQSeries Bindings";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String TRANSPORT_MQJD = "MQJD";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String USER_ID_PROPERTY = "userID";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String THREAD_ACCESS = "Thread access";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MULTI_THREAD = "MULTI_THREAD";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SINGLE_THREAD = "SINGLE_THREAD";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String THREAD_AFFINITY_PROPERTY = "Thread affinity";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public static final String THREAD_AFFINITY = "Thread affinity";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String GROUP_PROPERTY = "Group";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String XA_REQ_PROPERTY = "XAReq";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String USE_QM_CCSID_PROPERTY = "Use QM CCSID";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String USE_MQCSP_AUTHENTICATION_PROPERTY = "Use MQCSP authentication";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_RESET_COUNT_PROPERTY = "KeyResetCount";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_FIPS_REQUIRED_PROPERTY = "SSL Fips Required";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SPI_PROPERTY = "SPI";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SPI_ENABLE = "SPI_ENABLE";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SPI_DISABLE = "SPI_DISABLE";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BATCHING_ENABLED_PROPERTY = "Batching enabled";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BATCH_SIZE_FLOOR_PROPERTY = "Batch size floor";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BATCH_SIZE_CEILING_PROPERTY = "Batch size ceiling";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BATCHING_THRESHOLD_PROPERTY = "Batching threshold";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BATCH_INTERVAL_PROPERTY = "Batch interval";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_CIPHER_SUITE_PROPERTY = "SSL Cipher Suite";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_PEER_NAME_PROPERTY = "SSL Peer Name";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_CERT_STORE_PROPERTY = "SSL CertStores";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_CRYPTO_HARDWARE_PROPERTY = "SSL CryptoHardware";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String SSL_SOCKET_FACTORY_PROPERTY = "SSL Socket Factory";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQAIR_ARRAY = "mqairArray";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String KEY_RESET_COUNT = "keyResetCount";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String FIPS_REQUIRED = "fipsRequired";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String ENCRYPTION_POLICY_SUITE_B = "encryptionPolicySuiteB";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CERTIFICATE_VALIDATION_POLICY = "certificateValPolicy";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String HDR_CMP_LIST = "hdrCmpList";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MSG_CMP_LIST = "msgCmpList";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String LOCAL_ADDRESS_PROPERTY = "Local Address Property";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String CERT_LABEL_PROPERTY = "CertificateLabel";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String LOCAL_ADDRESS_MARKER = "LastUsed Port Marker";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String BINDINGS_AUTHENTICATE = "Bindings Authentication";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_ALTERNATE_SECURITY_ID = "AlternateSecurityId";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_SUBSCRIPTION_EXPIRY = "SubscriptionExpiry";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_SUBSCRIPTION_USER_DATA = "SubscriptionUserData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_SUBSCRIPTION_CORRELATION_ID = "SubscriptionCorrelationId";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_PUBLICATION_PRIORITY = "PublicationPriority";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_PUBLICATION_ACCOUNTING_TOKEN = "PublicationAccountingToken";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSUB_PROP_PUBLICATION_APPLICATIONID_DATA = "PublicationApplicationIdData";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String WMQ_ALTERNATE_USER_ID = "alternateUserId";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String WMQ_MQMD_MESSAGE_CONTEXT = "mdMessageContext";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int WMQ_MDCTX_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int WMQ_MDCTX_SET_IDENTITY_CONTEXT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int WMQ_MDCTX_SET_ALL_CONTEXT = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String WMQ_MQMD_WRITE_ENABLED = "mdWriteEnabled";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String WMQ_MQMD_READ_ENABLED = "mdReadEnabled";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String WMQ_MESSAGE_BODY = "messageBody";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int WMQ_MESSAGE_BODY_JMS = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int WMQ_MESSAGE_BODY_MQ = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int WMQ_MESSAGE_BODY_UNSPECIFIED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTOPIC_OPEN_AS_SUBSCRIPTION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQTOPIC_OPEN_AS_PUBLICATION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQAIR_STRUC_ID = "AIR ";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIR_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIR_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIR_CURRENT_VERSION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIT_ALL = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIT_CRL_LDAP = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIT_OCSP = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIT_IDPW_OS = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAIT_IDPW_LDAP = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQBNO_STRUC_ID = "BNO ";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_LENGTH_1 = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_CURRENT_LENGTH = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_OPTIONS_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_OPTIONS_IGNORE_TRANS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_BALTYPE_SIMPLE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_BALTYPE_REQREP = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_BALTYPE_RA_MANAGED = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_TIMEOUT_AS_DEFAULT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_TIMEOUT_IMMEDIATE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBNO_TIMEOUT_NEVER = -2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQBMHO_STRUC_ID = "BMHO";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBMHO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBMHO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBMHO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBMHO_CURRENT_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBMHO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBMHO_DELETE_PROPERTIES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQBO_STRUC_ID = "BO  ";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBO_CURRENT_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQBO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCBC_STRUC_ID = "CBC ";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBC_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBC_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBC_CURRENT_VERSION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCF_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCF_READA_BUFFER_EMPTY = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_START_CALL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_STOP_CALL = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_REGISTER_CALL = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_DEREGISTER_CALL = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_EVENT_CALL = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_MSG_REMOVED = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_MSG_NOT_REMOVED = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBCT_MC_EVENT_CALL = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCS_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCS_SUSPENDED_TEMPORARY = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCS_SUSPENDED_USER_ACTION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCS_SUSPENDED = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCS_STOPPED = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRD_NO_RECONNECT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRD_NO_DELAY = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCBD_STRUC_ID = "CBD ";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBD_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBD_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_START_CALL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_STOP_CALL = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_REGISTER_CALL = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_DEREGISTER_CALL = 512;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_FAIL_IF_QUIESCING = 8192;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_EVENT_CALL = 16384;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBDO_MC_EVENT_CALL = 32768;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBT_MESSAGE_CONSUMER = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBT_EVENT_HANDLER = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCBD_FULL_MSG_LENGTH = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQVS_NULL_TERMINATED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCIH_STRUC_ID = "CIH ";
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_CURRENT_VERSION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_LENGTH_1 = 164;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_LENGTH_2 = 180;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_CURRENT_LENGTH = 180;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_PASS_EXPIRATION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_UNLIMITED_EXPIRATION = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_REPLY_WITHOUT_NULLS = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_REPLY_WITH_NULLS = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_SYNC_ON_RETURN = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCIH_NO_SYNC_ON_RETURN = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_OK = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_CICS_EXEC_ERROR = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_MQ_API_ERROR = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_BRIDGE_ERROR = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_BRIDGE_ABEND = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_APPLICATION_ABEND = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_SECURITY_ERROR = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_PROGRAM_NOT_AVAILABLE = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_BRIDGE_TIMEOUT = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCRC_TRANSID_NOT_AVAILABLE = 9;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_ONLY = 273;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_CONTINUE = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_FIRST = 17;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_MIDDLE = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_LAST = 272;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_COMMIT = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCUOWC_BACKOUT = 4352;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCGWI_DEFAULT = -2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCLT_PROGRAM = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCLT_TRANSACTION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCODL_AS_INPUT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCADSD_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCADSD_SEND = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCADSD_RECV = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCADSD_MSGFORMAT = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCT_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCT_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTES_NOSYNC = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTES_COMMIT = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTES_BACKOUT = 4352;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTES_ENDTASK = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  7080 */   public static final byte[] MQCFAC_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_MQCONN = "CONN";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_MQGET = "GET ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_MQINQ = "INQ ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_MQOPEN = "OPEN";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_MQPUT = "PUT ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_MQPUT1 = "PUT1";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCFUNC_NONE = "    ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCSC_START = "S   ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCSC_STARTDATA = "SD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCSC_TERMINPUT = "TD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCSC_NONE = "    ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCMHO_STRUC_ID = "CMHO";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_CURRENT_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_DEFAULT_VALIDATION = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_NO_VALIDATION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_VALIDATE = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCMHO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCTLO_STRUC_ID = "CTLO";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTLO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTLO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTLO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTLO_THREAD_AFFINITY = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCTLO_FAIL_IF_QUIESCING = 8192;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSCO_STRUC_ID = "SCO ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_VERSION_3 = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_VERSION_4 = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_VERSION_5 = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_VERSION_6 = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_CURRENT_VERSION = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUITE_B_NOT_AVAILABLE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUITE_B_NONE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUITE_B_128_BIT = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_SUITE_B_192_BIT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSCO_RESET_COUNT_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CERT_VAL_POLICY_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CERT_VAL_POLICY_ANY = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQ_CERT_VAL_POLICY_RFC5280 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCSP_STRUC_ID = "CSP ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSP_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSP_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSP_CURRENT_VERSION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSP_AUTH_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCSP_AUTH_USER_ID_AND_PWD = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQCNO_STRUC_ID = "CNO ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_3 = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_4 = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_5 = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_6 = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_7 = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_VERSION_8 = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_CURRENT_VERSION = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_STANDARD_BINDING = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_FASTPATH_BINDING = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_SERIALIZE_CONN_TAG_Q_MGR = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_SERIALIZE_CONN_TAG_QSG = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_RESTRICT_CONN_TAG_Q_MGR = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_RESTRICT_CONN_TAG_QSG = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_HANDLE_SHARE_NONE = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_HANDLE_SHARE_BLOCK = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_HANDLE_SHARE_NO_BLOCK = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_SHARED_BINDING = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ISOLATED_BINDING = 512;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ACCOUNTING_MQI_ENABLED = 4096;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ACCOUNTING_MQI_DISABLED = 8192;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ACCOUNTING_Q_ENABLED = 16384;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ACCOUNTING_Q_DISABLED = 32768;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_NO_CONV_SHARING = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ALL_CONVS_SHARE = 262144;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_CD_FOR_OUTPUT_ONLY = 524288;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_USE_CD_SELECTION = 1048576;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_GENERATE_CONN_TAG = 2097152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_RECONNECT_AS_DEF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_RECONNECT = 16777216;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_RECONNECT_DISABLED = 33554432;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_RECONNECT_Q_MGR = 67108864;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ACTIVITY_TRACE_ENABLED = 134217728;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_ACTIVITY_TRACE_DISABLED = 268435456;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCNO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  7643 */   public static final byte[] MQCT_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  7648 */   public static final byte[] MQCONNID_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQAN_NONE = "                            ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQDH_STRUC_ID = "DH  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDH_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDH_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDH_LENGTH_1 = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDH_CURRENT_LENGTH = 48;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDHF_NEW_MSG_IDS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDHF_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQDLH_STRUC_ID = "DLH ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLH_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLH_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLH_LENGTH_1 = 172;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDLH_CURRENT_LENGTH = 172;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQDMHO_STRUC_ID = "DMHO";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMHO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMHO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMHO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMHO_CURRENT_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMHO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQDMPO_STRUC_ID = "DMPO";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_CURRENT_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_DEL_FIRST = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_DEL_PROP_UNDER_CURSOR = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQDMPO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQGMO_STRUC_ID = "GMO ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_VERSION_3 = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_VERSION_4 = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_CURRENT_VERSION = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_LENGTH_1 = 72;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_LENGTH_2 = 80;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_LENGTH_3 = 100;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_LENGTH_4 = 112;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_CURRENT_LENGTH = 112;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_WAIT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_NO_WAIT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_SET_SIGNAL = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_FAIL_IF_QUIESCING = 8192;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_SYNCPOINT = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_SYNCPOINT_IF_PERSISTENT = 4096;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_NO_SYNCPOINT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_MARK_SKIP_BACKOUT = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_BROWSE_FIRST = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_BROWSE_NEXT = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_BROWSE_MSG_UNDER_CURSOR = 2048;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_MSG_UNDER_CURSOR = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_LOCK = 512;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_UNLOCK = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_ACCEPT_TRUNCATED_MSG = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_CONVERT = 16384;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_LOGICAL_ORDER = 32768;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_COMPLETE_MSG = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_ALL_MSGS_AVAILABLE = 131072;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_ALL_SEGMENTS_AVAILABLE = 262144;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_MARK_BROWSE_HANDLE = 1048576;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_MARK_BROWSE_CO_OP = 2097152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_UNMARK_BROWSE_CO_OP = 4194304;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_UNMARK_BROWSE_HANDLE = 8388608;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_UNMARKED_BROWSE_MSG = 16777216;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_PROPERTIES_FORCE_MQRFH2 = 33554432;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_NO_PROPERTIES = 67108864;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_PROPERTIES_IN_HANDLE = 134217728;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_PROPERTIES_COMPATIBILITY = 268435456;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_PROPERTIES_AS_Q_DEF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_BROWSE_HANDLE = 17825808;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQGMO_BROWSE_CO_OP = 18874384;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQWI_UNLIMITED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEC_MSG_ARRIVED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEC_WAIT_INTERVAL_EXPIRED = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEC_WAIT_CANCELED = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEC_Q_MGR_QUIESCING = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEC_CONNECTION_QUIESCING = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_MATCH_MSG_ID = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_MATCH_CORREL_ID = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_MATCH_GROUP_ID = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_MATCH_MSG_SEQ_NUMBER = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_MATCH_OFFSET = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_MATCH_MSG_TOKEN = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQGS_NOT_IN_GROUP = ' ';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQGS_MSG_IN_GROUP = 'G';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQGS_LAST_MSG_IN_GROUP = 'L';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQSS_NOT_A_SEGMENT = ' ';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQSS_SEGMENT = 'S';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQSS_LAST_SEGMENT = 'L';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQSEG_INHIBITED = ' ';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQSEG_ALLOWED = 'A';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  8316 */   public static final byte[] MQMTOK_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRL_UNDEFINED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQIIH_STRUC_ID = "IIH ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_LENGTH_1 = 84;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_CURRENT_LENGTH = 84;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_PASS_EXPIRATION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_UNLIMITED_EXPIRATION = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_REPLY_FORMAT_NONE = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_IGNORE_PURG = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIIH_CM0_REQUEST_RESPONSE = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQIAUT_NONE = "        ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  8405 */   public static final byte[] MQITII_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQITS_IN_CONVERSATION = 'C';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQITS_NOT_IN_CONVERSATION = ' ';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQITS_ARCHITECTED = 'A';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQICM_COMMIT_THEN_SEND = '0';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQICM_SEND_THEN_COMMIT = '1';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQISS_CHECK = 'C';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final char MQISS_FULL = 'F';
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQIMPO_STRUC_ID = "IMPO";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_CONVERT_TYPE = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_QUERY_LENGTH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_INQ_FIRST = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_INQ_NEXT = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_INQ_PROP_UNDER_CURSOR = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_CONVERT_VALUE = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQIMPO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQMD_STRUC_ID = "MD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD_CURRENT_VERSION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD_LENGTH_1 = 324;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD_LENGTH_2 = 364;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD_CURRENT_LENGTH = 364;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_EXCEPTION = 16777216;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_EXCEPTION_WITH_DATA = 50331648;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_EXCEPTION_WITH_FULL_DATA = 117440512;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_EXPIRATION = 2097152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_EXPIRATION_WITH_DATA = 6291456;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_EXPIRATION_WITH_FULL_DATA = 14680064;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COA = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COA_WITH_DATA = 768;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COA_WITH_FULL_DATA = 1792;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COD = 2048;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COD_WITH_DATA = 6144;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COD_WITH_FULL_DATA = 14336;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_PAN = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_NAN = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_ACTIVITY = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_NEW_MSG_ID = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_PASS_MSG_ID = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_COPY_MSG_ID_TO_CORREL_ID = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_PASS_CORREL_ID = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_DEAD_LETTER_Q = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_DISCARD_MSG = 134217728;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_PASS_DISCARD_AND_EXPIRY = 16384;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_REJECT_UNSUP_MASK = 270270464;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_ACCEPT_UNSUP_MASK = -270532353;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRO_ACCEPT_UNSUP_IF_XMIT_MASK = 261888;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_SYSTEM_FIRST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_REQUEST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_REPLY = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_DATAGRAM = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_REPORT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_MQE_FIELDS_FROM_MQE = 112;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_MQE_FIELDS = 113;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_SYSTEM_LAST = 65535;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_APPL_FIRST = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMT_APPL_LAST = 999999999;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQEI_UNLIMITED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_SYSTEM_FIRST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_QUIT = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_EXPIRATION = 258;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_COA = 259;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_COD = 260;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CHANNEL_COMPLETED = 262;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CHANNEL_FAIL_RETRY = 263;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CHANNEL_FAIL = 264;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_APPL_CANNOT_BE_STARTED = 265;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_TM_ERROR = 266;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_APPL_TYPE_ERROR = 267;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_STOPPED_BY_MSG_EXIT = 268;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_ACTIVITY = 269;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_XMIT_Q_MSG_ERROR = 271;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_PAN = 275;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NAN = 276;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_STOPPED_BY_CHAD_EXIT = 277;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_STOPPED_BY_PUBSUB_EXIT = 279;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NOT_A_REPOSITORY_MSG = 280;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_BIND_OPEN_CLUSRCVR_DEL = 281;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_MAX_ACTIVITIES = 282;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NOT_FORWARDED = 283;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NOT_DELIVERED = 284;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_UNSUPPORTED_FORWARDING = 285;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_UNSUPPORTED_DELIVERY = 286;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_DATA_LENGTH_ZERO = 291;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_DATA_LENGTH_NEGATIVE = 292;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_DATA_LENGTH_TOO_BIG = 293;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_BUFFER_OVERFLOW = 294;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_LENGTH_OFF_BY_ONE = 295;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_IIH_ERROR = 296;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NOT_AUTHORIZED_FOR_IMS = 298;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_DATA_LENGTH_TOO_SHORT = 299;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_IMS_ERROR = 300;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_IMS_FIRST = 301;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_IMS_LAST = 399;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_INTERNAL_ERROR = 401;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_NOT_AUTHORIZED = 402;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_BRIDGE_FAILURE = 403;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_CORREL_ID_ERROR = 404;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_CCSID_ERROR = 405;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_ENCODING_ERROR = 406;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_CIH_ERROR = 407;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_UOW_ERROR = 408;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_COMMAREA_ERROR = 409;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_APPL_NOT_STARTED = 410;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_APPL_ABENDED = 411;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_DLQ_ERROR = 412;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_CICS_UOW_BACKED_OUT = 413;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_PUBLICATIONS_ON_REQUEST = 501;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_SUBSCRIBER_IS_PUBLISHER = 502;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_MSG_SCOPE_MISMATCH = 503;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_SELECTOR_MISMATCH = 504;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_NOT_A_GROUPUR_MSG = 505;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_IMS_NACK_1A_REASON_FIRST = 600;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_IMS_NACK_1A_REASON_LAST = 855;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_SYSTEM_LAST = 65535;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_APPL_FIRST = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQFB_APPL_LAST = 999999999;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_NATIVE = 273;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_INTEGER_MASK = 15;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_DECIMAL_MASK = 240;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_FLOAT_MASK = 3840;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_RESERVED_MASK = -4096;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_INTEGER_UNDEFINED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_INTEGER_NORMAL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_INTEGER_REVERSED = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_DECIMAL_UNDEFINED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_DECIMAL_NORMAL = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_DECIMAL_REVERSED = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_FLOAT_UNDEFINED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_FLOAT_IEEE_NORMAL = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_FLOAT_IEEE_REVERSED = 512;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_FLOAT_S390 = 768;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_FLOAT_TNS = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_NORMAL = 273;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_REVERSED = 546;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_S390 = 785;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_TNS = 1041;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQENC_AS_PUBLISHED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_UNDEFINED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_DEFAULT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_Q_MGR = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_INHERIT = -2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_EMBEDDED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_APPL = -3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCCSI_AS_PUBLISHED = -4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_NONE = "        ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_ADMIN = "MQADMIN ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_AMQP = "MQAMQP  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_CHANNEL_COMPLETED = "MQCHCOM ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_CICS = "MQCICS  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_COMMAND_1 = "MQCMD1  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_COMMAND_2 = "MQCMD2  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_DEAD_LETTER_HEADER = "MQDEAD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_DIST_HEADER = "MQHDIST ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_EMBEDDED_PCF = "MQHEPCF ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_EVENT = "MQEVENT ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_IMS = "MQIMS   ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_IMS_VAR_STRING = "MQIMSVS ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_MD_EXTENSION = "MQHMDE  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_PCF = "MQPCF   ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_REF_MSG_HEADER = "MQHREF  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_RF_HEADER = "MQHRF   ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_RF_HEADER_1 = "MQHRF   ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_RF_HEADER_2 = "MQHRF2  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_STRING = "MQSTR   ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_TRIGGER = "MQTRIG  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_WORK_INFO_HEADER = "MQHWIH  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQFMT_XMIT_Q_HEADER = "MQXMIT  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRI_PRIORITY_AS_Q_DEF = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRI_PRIORITY_AS_PARENT = -2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRI_PRIORITY_AS_PUBLISHED = -3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRI_PRIORITY_AS_TOPIC_DEF = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPER_PERSISTENCE_AS_PARENT = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPER_NOT_PERSISTENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPER_PERSISTENT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPER_PERSISTENCE_AS_Q_DEF = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPER_PERSISTENCE_AS_TOPIC_DEF = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRT_RESPONSE_AS_PARENT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRT_SYNC_RESPONSE = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPRT_ASYNC_RESPONSE = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  9725 */   public static final byte[] MQMI_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  9731 */   public static final byte[] MQCI_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  9739 */   public static final byte[] MQCI_NEW_SESSION = new byte[] { 65, 77, 81, 33, 78, 69, 87, 95, 83, 69, 83, 83, 73, 79, 78, 95, 67, 79, 82, 82, 69, 76, 73, 68 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*  9745 */   public static final byte[] MQACT_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_UNKNOWN = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_CICS_LUOW_ID = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_OS2_DEFAULT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_DOS_DEFAULT = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_UNIX_NUMERIC_ID = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_OS400_ACCOUNT_TOKEN = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_WINDOWS_DEFAULT = 9;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_NT_SECURITY_ID = 11;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_AZUREAD_SECURITY_ID = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_MS_ACC_AUTH_SECURITY_ID = 13;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final byte MQACTT_USER = 25;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_UNKNOWN = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_NO_CONTEXT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_CICS = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_MVS = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_OS390 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_ZOS = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_IMS = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_OS2 = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_DOS = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_AIX = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_UNIX = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_QMGR = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_OS400 = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_WINDOWS = 9;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_CICS_VSE = 10;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_WINDOWS_NT = 11;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_VMS = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_GUARDIAN = 13;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_NSK = 13;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_VOS = 14;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_OPEN_TP1 = 15;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_VM = 18;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_IMS_BRIDGE = 19;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_XCF = 20;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_CICS_BRIDGE = 21;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_NOTES_AGENT = 22;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_TPF = 23;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_USER = 25;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_BROKER = 26;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_QMGR_PUBLISH = 26;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_JAVA = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_DQM = 29;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_CHANNEL_INITIATOR = 30;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_WLM = 31;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_BATCH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_RRS_BATCH = 33;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_SIB = 34;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_SYSTEM_EXTENSION = 35;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_MCAST_PUBLISH = 36;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_AMQP = 37;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_DEFAULT = 28;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_USER_FIRST = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQAT_USER_LAST = 999999999;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 10045 */   public static final byte[] MQGI_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_SEGMENTATION_INHIBITED = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_SEGMENTATION_ALLOWED = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_MSG_IN_GROUP = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_LAST_MSG_IN_GROUP = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_SEGMENT = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_LAST_SEGMENT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_REJECT_UNSUP_MASK = 4095;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_ACCEPT_UNSUP_MASK = -1048576;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMF_ACCEPT_UNSUP_IF_XMIT_MASK = 1044480;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOL_UNDEFINED = -1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQMDE_STRUC_ID = "MDE ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDE_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDE_CURRENT_VERSION = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDE_LENGTH_2 = 72;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDE_CURRENT_LENGTH = 72;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMDEF_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD1_LENGTH_1 = 324;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD1_CURRENT_LENGTH = 324;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD2_LENGTH_1 = 324;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD2_LENGTH_2 = 364;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMD2_CURRENT_LENGTH = 364;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQMHBO_STRUC_ID = "MHBO";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_LENGTH_1 = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_CURRENT_LENGTH = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_PROPERTIES_IN_MQRFH2 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_DELETE_PROPERTIES = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQMHBO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQOD_STRUC_ID = "OD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOD_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOD_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOD_VERSION_3 = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOD_VERSION_4 = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOD_CURRENT_VERSION = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOD_LENGTH_1 = 168;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOM_NO = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOM_YES = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_Q = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_NAMELIST = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_PROCESS = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_STORAGE_CLASS = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_Q_MGR = 5;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_CHANNEL = 6;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_AUTH_INFO = 7;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_TOPIC = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_COMM_INFO = 9;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_CF_STRUC = 10;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_LISTENER = 11;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_SERVICE = 12;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_RESERVED_1 = 999;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_ALL = 1001;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_ALIAS_Q = 1002;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_MODEL_Q = 1003;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_LOCAL_Q = 1004;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_REMOTE_Q = 1005;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_SENDER_CHANNEL = 1007;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_SERVER_CHANNEL = 1008;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_REQUESTER_CHANNEL = 1009;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_RECEIVER_CHANNEL = 1010;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_CURRENT_CHANNEL = 1011;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_SAVED_CHANNEL = 1012;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_SVRCONN_CHANNEL = 1013;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_CLNTCONN_CHANNEL = 1014;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_SHORT_CHANNEL = 1015;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_CHLAUTH = 1016;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_REMOTE_Q_MGR_NAME = 1017;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_PROT_POLICY = 1019;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_TT_CHANNEL = 1020;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_AMQP_CHANNEL = 1021;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQOT_AUTH_REC = 1022;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQPD_STRUC_ID = "PD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_LENGTH_1 = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_CURRENT_LENGTH = 24;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_SUPPORT_OPTIONAL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_SUPPORT_REQUIRED = 1048576;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_SUPPORT_REQUIRED_IF_LOCAL = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_REJECT_UNSUP_MASK = -1048576;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_ACCEPT_UNSUP_IF_XMIT_MASK = 1047552;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_ACCEPT_UNSUP_MASK = 1023;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_NO_CONTEXT = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPD_USER_CONTEXT = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_ALL = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_FORWARD = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_PUBLISH = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_REPLY = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_REPORT = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQCOPY_DEFAULT = 22;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQPMO_STRUC_ID = "PMO ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_VERSION_3 = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_CURRENT_VERSION = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_LENGTH_1 = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_SYNCPOINT = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_NO_SYNCPOINT = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_DEFAULT_CONTEXT = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_NEW_MSG_ID = 64;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_NEW_CORREL_ID = 128;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_PASS_IDENTITY_CONTEXT = 256;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_PASS_ALL_CONTEXT = 512;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_SET_IDENTITY_CONTEXT = 1024;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_SET_ALL_CONTEXT = 2048;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_ALTERNATE_USER_AUTHORITY = 4096;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_FAIL_IF_QUIESCING = 8192;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_NO_CONTEXT = 16384;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_LOGICAL_ORDER = 32768;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_ASYNC_RESPONSE = 65536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_SYNC_RESPONSE = 131072;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_RESOLVE_LOCAL_Q = 262144;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_WARN_IF_NO_SUBS_MATCHED = 524288;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_RETAIN = 2097152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_MD_FOR_OUTPUT_ONLY = 8388608;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_SCOPE_QMGR = 67108864;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_SUPPRESS_REPLYTO = 134217728;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_NOT_OWN_SUBS = 268435456;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_RESPONSE_AS_Q_DEF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_RESPONSE_AS_TOPIC_DEF = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMO_PUB_OPTIONS_MASK = 2097152;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMRF_MSG_ID = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMRF_CORREL_ID = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMRF_GROUP_ID = 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMRF_FEEDBACK = 8;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMRF_ACCOUNTING_TOKEN = 16;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQPMRF_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTP_NEW = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTP_FORWARD = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTP_REPLY = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQACTP_REPORT = 3;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQRFH_STRUC_ID = "RFH ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_VERSION_2 = 2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_STRUC_LENGTH_FIXED = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_STRUC_LENGTH_FIXED_2 = 36;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_LENGTH_1 = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_CURRENT_LENGTH = 32;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_NONE = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_NO_FLAGS = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH_FLAGS_RESTRICTED_MASK = -65536;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQNVS_APPL_TYPE = "OPT_APP_GRP ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQNVS_MSG_TYPE = "OPT_MSG_TYPE ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH2_LENGTH_2 = 36;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRFH2_CURRENT_LENGTH = 36;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQRMH_STRUC_ID = "RMH ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRMH_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRMH_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRMH_LENGTH_1 = 108;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRMH_CURRENT_LENGTH = 108;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRMHF_LAST = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQRMHF_NOT_LAST = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 11009 */   public static final byte[] MQOII_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final String MQSD_STRUC_ID = "SD  ";
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSD_VERSION_1 = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static final int MQSD_CURRENT_VERSION = 1;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/* 11033 */   public static final byte[] MQSID_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*       */   public static final byte MQSIDT_NONE = 0;
/*       */   public static final byte MQSIDT_NT_SECURITY_ID = 1;
/*       */   public static final byte MQSIDT_WAS_SECURITY_ID = 2;
/*       */   public static final String MQSMPO_STRUC_ID = "SMPO";
/*       */   public static final int MQSMPO_VERSION_1 = 1;
/*       */   public static final int MQSMPO_CURRENT_VERSION = 1;
/*       */   public static final int MQSMPO_LENGTH_1 = 20;
/*       */   public static final int MQSMPO_CURRENT_LENGTH = 20;
/*       */   public static final int MQSMPO_SET_FIRST = 0;
/*       */   public static final int MQSMPO_SET_PROP_UNDER_CURSOR = 1;
/*       */   public static final int MQSMPO_SET_PROP_AFTER_CURSOR = 2;
/*       */   public static final int MQSMPO_APPEND_PROPERTY = 4;
/*       */   public static final int MQSMPO_SET_PROP_BEFORE_CURSOR = 8;
/*       */   public static final int MQSMPO_NONE = 0;
/*       */   public static final String MQSRO_STRUC_ID = "SRO ";
/*       */   public static final int MQSRO_VERSION_1 = 1;
/*       */   public static final int MQSRO_CURRENT_VERSION = 1;
/*       */   public static final int MQSRO_LENGTH_1 = 16;
/*       */   public static final int MQSRO_CURRENT_LENGTH = 16;
/*       */   public static final int MQSRO_NONE = 0;
/*       */   public static final int MQSRO_FAIL_IF_QUIESCING = 8192;
/*       */   public static final String MQSTS_STRUC_ID = "STAT";
/*       */   public static final int MQSTS_VERSION_1 = 1;
/*       */   public static final int MQSTS_VERSION_2 = 2;
/*       */   public static final int MQSTS_CURRENT_VERSION = 2;
/*       */   public static final int MQSTS_LENGTH_1 = 224;
/*       */   public static final String MQTM_STRUC_ID = "TM  ";
/*       */   public static final int MQTM_VERSION_1 = 1;
/*       */   public static final int MQTM_CURRENT_VERSION = 1;
/*       */   public static final int MQTM_LENGTH_1 = 684;
/*       */   public static final int MQTM_CURRENT_LENGTH = 684;
/*       */   public static final int MQTMC_LENGTH_1 = 684;
/*       */   public static final int MQTMC_CURRENT_LENGTH = 684;
/*       */   public static final String MQTMC_STRUC_ID = "TMC ";
/*       */   public static final int MQTMC2_LENGTH_1 = 684;
/*       */   public static final int MQTMC2_LENGTH_2 = 732;
/*       */   public static final int MQTMC2_CURRENT_LENGTH = 732;
/*       */   public static final String MQTMC_VERSION_1 = "   1";
/*       */   public static final String MQTMC_VERSION_2 = "   2";
/*       */   public static final String MQTMC_CURRENT_VERSION = "   2";
/*       */   public static final String MQWIH_STRUC_ID = "WIH ";
/*       */   public static final int MQWIH_VERSION_1 = 1;
/*       */   public static final int MQWIH_CURRENT_VERSION = 1;
/*       */   public static final int MQWIH_LENGTH_1 = 120;
/*       */   public static final int MQWIH_CURRENT_LENGTH = 120;
/*       */   public static final int MQWIH_NONE = 0;
/*       */   public static final String MQXQH_STRUC_ID = "XQH ";
/*       */   public static final int MQXQH_VERSION_1 = 1;
/*       */   public static final int MQXQH_CURRENT_VERSION = 1;
/*       */   public static final int MQXQH_LENGTH_1 = 428;
/*       */   public static final int MQXQH_CURRENT_LENGTH = 428;
/*       */   public static final int MQHO_UNUSABLE_HOBJ = -1;
/*       */   public static final int MQHO_NONE = 0;
/*       */   public static final int MQCO_IMMEDIATE = 0;
/*       */   public static final int MQCO_NONE = 0;
/*       */   public static final int MQCO_DELETE = 1;
/*       */   public static final int MQCO_DELETE_PURGE = 2;
/*       */   public static final int MQCO_KEEP_SUB = 4;
/*       */   public static final int MQCO_REMOVE_SUB = 8;
/*       */   public static final int MQCO_QUIESCE = 32;
/*       */   public static final int MQOP_START = 1;
/*       */   public static final int MQOP_START_WAIT = 2;
/*       */   public static final int MQOP_STOP = 4;
/*       */   public static final int MQOP_REGISTER = 256;
/*       */   public static final int MQOP_DEREGISTER = 512;
/*       */   public static final int MQOP_SUSPEND = 65536;
/*       */   public static final int MQOP_RESUME = 131072;
/*       */   public static final long MQHM_UNUSABLE_HMSG = -1L;
/*       */   public static final long MQHM_NONE = 0L;
/*       */   public static final int MQBA_FIRST = 6001;
/*       */   public static final int MQBA_LAST = 8000;
/*       */   public static final int MQCA_ADMIN_TOPIC_NAME = 2105;
/*       */   public static final int MQCA_ALTERATION_DATE = 2027;
/*       */   public static final int MQCA_ALTERATION_TIME = 2028;
/*       */   public static final int MQCA_AMQP_SSL_CIPHER_SUITES = 2137;
/*       */   public static final int MQCA_AMQP_VERSION = 2136;
/*       */   public static final int MQCA_APPL_ID = 2001;
/*       */   public static final int MQCA_AUTH_INFO_CONN_NAME = 2053;
/*       */   public static final int MQCA_AUTH_INFO_DESC = 2046;
/*       */   public static final int MQCA_AUTH_INFO_NAME = 2045;
/*       */   public static final int MQCA_AUTH_INFO_OCSP_URL = 2109;
/*       */   public static final int MQCA_AUTO_REORG_CATALOG = 2091;
/*       */   public static final int MQCA_AUTO_REORG_START_TIME = 2090;
/*       */   public static final int MQCA_BACKOUT_REQ_Q_NAME = 2019;
/*       */   public static final int MQCA_BASE_OBJECT_NAME = 2002;
/*       */   public static final int MQCA_BASE_Q_NAME = 2002;
/*       */   public static final int MQCA_BATCH_INTERFACE_ID = 2068;
/*       */   public static final int MQCA_CERT_LABEL = 2121;
/*       */   public static final int MQCA_CF_STRUC_DESC = 2052;
/*       */   public static final int MQCA_CF_STRUC_NAME = 2039;
/*       */   public static final int MQCA_CHANNEL_AUTO_DEF_EXIT = 2026;
/*       */   public static final int MQCA_CHILD = 2101;
/*       */   public static final int MQCA_CHINIT_SERVICE_PARM = 2076;
/*       */   public static final int MQCA_CHLAUTH_DESC = 2118;
/*       */   public static final int MQCA_CICS_FILE_NAME = 2060;
/*       */   public static final int MQCA_CLUSTER_DATE = 2037;
/*       */   public static final int MQCA_CLUSTER_NAME = 2029;
/*       */   public static final int MQCA_CLUSTER_NAMELIST = 2030;
/*       */   public static final int MQCA_CLUSTER_Q_MGR_NAME = 2031;
/*       */   public static final int MQCA_CLUSTER_TIME = 2038;
/*       */   public static final int MQCA_CLUSTER_WORKLOAD_DATA = 2034;
/*       */   public static final int MQCA_CLUSTER_WORKLOAD_EXIT = 2033;
/*       */   public static final int MQCA_CLUS_CHL_NAME = 2124;
/*       */   public static final int MQCA_COMMAND_INPUT_Q_NAME = 2003;
/*       */   public static final int MQCA_COMMAND_REPLY_Q_NAME = 2067;
/*       */   public static final int MQCA_COMM_INFO_DESC = 2111;
/*       */   public static final int MQCA_COMM_INFO_NAME = 2110;
/*       */   public static final int MQCA_CONN_AUTH = 2125;
/*       */   public static final int MQCA_CREATION_DATE = 2004;
/*       */   public static final int MQCA_CREATION_TIME = 2005;
/*       */   public static final int MQCA_CUSTOM = 2119;
/*       */   public static final int MQCA_DEAD_LETTER_Q_NAME = 2006;
/*       */   public static final int MQCA_DEF_XMIT_Q_NAME = 2025;
/*       */   public static final int MQCA_DNS_GROUP = 2071;
/*       */   public static final int MQCA_ENV_DATA = 2007;
/*       */   public static final int MQCA_FIRST = 2001;
/*       */   public static final int MQCA_IGQ_USER_ID = 2041;
/*       */   public static final int MQCA_INITIAL_KEY = 2054;
/*       */   public static final int MQCA_INITIATION_Q_NAME = 2008;
/*       */   public static final int MQCA_INSTALLATION_DESC = 2115;
/*       */   public static final int MQCA_INSTALLATION_NAME = 2116;
/*       */   public static final int MQCA_INSTALLATION_PATH = 2117;
/*       */   public static final int MQCA_LAST = 4000;
/*       */   public static final int MQCA_LAST_USED = 2138;
/*       */   public static final int MQCA_LDAP_BASE_DN_GROUPS = 2132;
/*       */   public static final int MQCA_LDAP_BASE_DN_USERS = 2126;
/*       */   public static final int MQCA_LDAP_FIND_GROUP_FIELD = 2135;
/*       */   public static final int MQCA_LDAP_GROUP_ATTR_FIELD = 2134;
/*       */   public static final int MQCA_LDAP_GROUP_OBJECT_CLASS = 2133;
/*       */   public static final int MQCA_LDAP_PASSWORD = 2048;
/*       */   public static final int MQCA_LDAP_SHORT_USER_FIELD = 2127;
/*       */   public static final int MQCA_LDAP_USER_ATTR_FIELD = 2129;
/*       */   public static final int MQCA_LDAP_USER_NAME = 2047;
/*       */   public static final int MQCA_LDAP_USER_OBJECT_CLASS = 2128;
/*       */   public static final int MQCA_LU62_ARM_SUFFIX = 2074;
/*       */   public static final int MQCA_LU_GROUP_NAME = 2072;
/*       */   public static final int MQCA_LU_NAME = 2073;
/*       */   public static final int MQCA_MODEL_DURABLE_Q = 2096;
/*       */   public static final int MQCA_MODEL_NON_DURABLE_Q = 2097;
/*       */   public static final int MQCA_MONITOR_Q_NAME = 2066;
/*       */   public static final int MQCA_NAMELIST_DESC = 2009;
/*       */   public static final int MQCA_NAMELIST_NAME = 2010;
/*       */   public static final int MQCA_NAMES = 2020;
/*       */   public static final int MQCA_PARENT = 2102;
/*       */   public static final int MQCA_PASS_TICKET_APPL = 2086;
/*       */   public static final int MQCA_POLICY_NAME = 2112;
/*       */   public static final int MQCA_PROCESS_DESC = 2011;
/*       */   public static final int MQCA_PROCESS_NAME = 2012;
/*       */   public static final int MQCA_QSG_CERT_LABEL = 2131;
/*       */   public static final int MQCA_QSG_NAME = 2040;
/*       */   public static final int MQCA_Q_DESC = 2013;
/*       */   public static final int MQCA_Q_MGR_DESC = 2014;
/*       */   public static final int MQCA_Q_MGR_IDENTIFIER = 2032;
/*       */   public static final int MQCA_Q_MGR_NAME = 2015;
/*       */   public static final int MQCA_Q_NAME = 2016;
/*       */   public static final int MQCA_RECIPIENT_DN = 2114;
/*       */   public static final int MQCA_REMOTE_Q_MGR_NAME = 2017;
/*       */   public static final int MQCA_REMOTE_Q_NAME = 2018;
/*       */   public static final int MQCA_REPOSITORY_NAME = 2035;
/*       */   public static final int MQCA_REPOSITORY_NAMELIST = 2036;
/*       */   public static final int MQCA_RESUME_DATE = 2098;
/*       */   public static final int MQCA_RESUME_TIME = 2099;
/*       */   public static final int MQCA_SERVICE_DESC = 2078;
/*       */   public static final int MQCA_SERVICE_NAME = 2077;
/*       */   public static final int MQCA_SERVICE_START_ARGS = 2080;
/*       */   public static final int MQCA_SERVICE_START_COMMAND = 2079;
/*       */   public static final int MQCA_SERVICE_STOP_ARGS = 2082;
/*       */   public static final int MQCA_SERVICE_STOP_COMMAND = 2081;
/*       */   public static final int MQCA_SIGNER_DN = 2113;
/*       */   public static final int MQCA_SSL_CERT_ISSUER_NAME = 2130;
/*       */   public static final int MQCA_SSL_CRL_NAMELIST = 2050;
/*       */   public static final int MQCA_SSL_CRYPTO_HARDWARE = 2051;
/*       */   public static final int MQCA_SSL_KEY_LIBRARY = 2069;
/*       */   public static final int MQCA_SSL_KEY_MEMBER = 2070;
/*       */   public static final int MQCA_SSL_KEY_REPOSITORY = 2049;
/*       */   public static final int MQCA_SSL_KEY_REPO_PASSWORD = 2055;
/*       */   public static final int MQCA_STDERR_DESTINATION = 2084;
/*       */   public static final int MQCA_STDOUT_DESTINATION = 2083;
/*       */   public static final int MQCA_STORAGE_CLASS = 2022;
/*       */   public static final int MQCA_STORAGE_CLASS_DESC = 2042;
/*       */   public static final int MQCA_STREAM_QUEUE_NAME = 2138;
/*       */   public static final int MQCA_SYSTEM_LOG_Q_NAME = 2065;
/*       */   public static final int MQCA_TCP_NAME = 2075;
/*       */   public static final int MQCA_TOPIC_DESC = 2093;
/*       */   public static final int MQCA_TOPIC_NAME = 2092;
/*       */   public static final int MQCA_TOPIC_STRING = 2094;
/*       */   public static final int MQCA_TOPIC_STRING_FILTER = 2108;
/*       */   public static final int MQCA_TPIPE_NAME = 2085;
/*       */   public static final int MQCA_TRIGGER_CHANNEL_NAME = 2064;
/*       */   public static final int MQCA_TRIGGER_DATA = 2023;
/*       */   public static final int MQCA_TRIGGER_PROGRAM_NAME = 2062;
/*       */   public static final int MQCA_TRIGGER_TERM_ID = 2063;
/*       */   public static final int MQCA_TRIGGER_TRANS_ID = 2061;
/*       */   public static final int MQCA_USER_DATA = 2021;
/*       */   public static final int MQCA_USER_LIST = 4000;
/*       */   public static final int MQCA_VERSION = 2120;
/*       */   public static final int MQCA_XCF_GROUP_NAME = 2043;
/*       */   public static final int MQCA_XCF_MEMBER_NAME = 2044;
/*       */   public static final int MQCA_XMIT_Q_NAME = 2024;
/*       */   public static final int MQCA_XR_SSL_CIPHER_SUITES = 2123;
/*       */   public static final int MQCA_XR_VERSION = 2122;
/*       */   public static final int MQIA_ACCOUNTING_CONN_OVERRIDE = 136;
/*       */   public static final int MQIA_ACCOUNTING_INTERVAL = 135;
/*       */   public static final int MQIA_ACCOUNTING_MQI = 133;
/*       */   public static final int MQIA_ACCOUNTING_Q = 134;
/*       */   public static final int MQIA_ACTIVE_CHANNELS = 100;
/*       */   public static final int MQIA_ACTIVITY_CONN_OVERRIDE = 239;
/*       */   public static final int MQIA_ACTIVITY_RECORDING = 138;
/*       */   public static final int MQIA_ACTIVITY_TRACE = 240;
/*       */   public static final int MQIA_ADOPTNEWMCA_CHECK = 102;
/*       */   public static final int MQIA_ADOPTNEWMCA_INTERVAL = 104;
/*       */   public static final int MQIA_ADOPTNEWMCA_TYPE = 103;
/*       */   public static final int MQIA_ADOPT_CONTEXT = 260;
/*       */   public static final int MQIA_ADVANCED_CAPABILITY = 273;
/*       */   public static final int MQIA_AMQP_CAPABILITY = 265;
/*       */   public static final int MQIA_APPL_TYPE = 1;
/*       */   public static final int MQIA_ARCHIVE = 60;
/*       */   public static final int MQIA_AUTHENTICATION_FAIL_DELAY = 259;
/*       */   public static final int MQIA_AUTHENTICATION_METHOD = 266;
/*       */   public static final int MQIA_AUTHORITY_EVENT = 47;
/*       */   public static final int MQIA_AUTH_INFO_TYPE = 66;
/*       */   public static final int MQIA_AUTO_REORGANIZATION = 173;
/*       */   public static final int MQIA_AUTO_REORG_INTERVAL = 174;
/*       */   public static final int MQIA_BACKOUT_THRESHOLD = 22;
/*       */   public static final int MQIA_BASE_TYPE = 193;
/*       */   public static final int MQIA_BATCH_INTERFACE_AUTO = 86;
/*       */   public static final int MQIA_BRIDGE_EVENT = 74;
/*       */   public static final int MQIA_CAP_EXPIRY = 276;
/*       */   public static final int MQIA_CERT_VAL_POLICY = 252;
/*       */   public static final int MQIA_CF_CFCONLOS = 246;
/*       */   public static final int MQIA_CF_LEVEL = 70;
/*       */   public static final int MQIA_CF_OFFLDUSE = 229;
/*       */   public static final int MQIA_CF_OFFLOAD = 224;
/*       */   public static final int MQIA_CF_OFFLOAD_THRESHOLD1 = 225;
/*       */   public static final int MQIA_CF_OFFLOAD_THRESHOLD2 = 226;
/*       */   public static final int MQIA_CF_OFFLOAD_THRESHOLD3 = 227;
/*       */   public static final int MQIA_CF_RECAUTO = 244;
/*       */   public static final int MQIA_CF_RECOVER = 71;
/*       */   public static final int MQIA_CF_SMDS_BUFFERS = 228;
/*       */   public static final int MQIA_CHANNEL_AUTO_DEF = 55;
/*       */   public static final int MQIA_CHANNEL_AUTO_DEF_EVENT = 56;
/*       */   public static final int MQIA_CHANNEL_EVENT = 73;
/*       */   public static final int MQIA_CHECK_CLIENT_BINDING = 258;
/*       */   public static final int MQIA_CHECK_LOCAL_BINDING = 257;
/*       */   public static final int MQIA_CHINIT_ADAPTERS = 101;
/*       */   public static final int MQIA_CHINIT_CONTROL = 119;
/*       */   public static final int MQIA_CHINIT_DISPATCHERS = 105;
/*       */   public static final int MQIA_CHINIT_TRACE_AUTO_START = 117;
/*       */   public static final int MQIA_CHINIT_TRACE_TABLE_SIZE = 118;
/*       */   public static final int MQIA_CHLAUTH_RECORDS = 248;
/*       */   public static final int MQIA_CLUSTER_OBJECT_STATE = 256;
/*       */   public static final int MQIA_CLUSTER_PUB_ROUTE = 255;
/*       */   public static final int MQIA_CLUSTER_Q_TYPE = 59;
/*       */   public static final int MQIA_CLUSTER_WORKLOAD_LENGTH = 58;
/*       */   public static final int MQIA_CLWL_MRU_CHANNELS = 97;
/*       */   public static final int MQIA_CLWL_Q_PRIORITY = 96;
/*       */   public static final int MQIA_CLWL_Q_RANK = 95;
/*       */   public static final int MQIA_CLWL_USEQ = 98;
/*       */   public static final int MQIA_CMD_SERVER_AUTO = 87;
/*       */   public static final int MQIA_CMD_SERVER_CONTROL = 120;
/*       */   public static final int MQIA_CMD_SERVER_CONVERT_MSG = 88;
/*       */   public static final int MQIA_CMD_SERVER_DLQ_MSG = 89;
/*       */   public static final int MQIA_CODED_CHAR_SET_ID = 2;
/*       */   public static final int MQIA_COMMAND_EVENT = 99;
/*       */   public static final int MQIA_COMMAND_LEVEL = 31;
/*       */   public static final int MQIA_COMM_EVENT = 232;
/*       */   public static final int MQIA_COMM_INFO_TYPE = 223;
/*       */   public static final int MQIA_CONFIGURATION_EVENT = 51;
/*       */   public static final int MQIA_CPI_LEVEL = 27;
/*       */   public static final int MQIA_CURRENT_Q_DEPTH = 3;
/*       */   public static final int MQIA_DEFINITION_TYPE = 7;
/*       */   public static final int MQIA_DEF_BIND = 61;
/*       */   public static final int MQIA_DEF_CLUSTER_XMIT_Q_TYPE = 250;
/*       */   public static final int MQIA_DEF_INPUT_OPEN_OPTION = 4;
/*       */   public static final int MQIA_DEF_PERSISTENCE = 5;
/*       */   public static final int MQIA_DEF_PRIORITY = 6;
/*       */   public static final int MQIA_DEF_PUT_RESPONSE_TYPE = 184;
/*       */   public static final int MQIA_DEF_READ_AHEAD = 188;
/*       */   public static final int MQIA_DISPLAY_TYPE = 262;
/*       */   public static final int MQIA_DIST_LISTS = 34;
/*       */   public static final int MQIA_DNS_WLM = 106;
/*       */   public static final int MQIA_DURABLE_SUB = 175;
/*       */   public static final int MQIA_ENCRYPTION_ALGORITHM = 237;
/*       */   public static final int MQIA_EXPIRY_INTERVAL = 39;
/*       */   public static final int MQIA_FIRST = 1;
/*       */   public static final int MQIA_GROUP_UR = 221;
/*       */   public static final int MQIA_HARDEN_GET_BACKOUT = 8;
/*       */   public static final int MQIA_HIGH_Q_DEPTH = 36;
/*       */   public static final int MQIA_IGQ_PUT_AUTHORITY = 65;
/*       */   public static final int MQIA_INDEX_TYPE = 57;
/*       */   public static final int MQIA_INHIBIT_EVENT = 48;
/*       */   public static final int MQIA_INHIBIT_GET = 9;
/*       */   public static final int MQIA_INHIBIT_PUB = 181;
/*       */   public static final int MQIA_INHIBIT_PUT = 10;
/*       */   public static final int MQIA_INHIBIT_SUB = 182;
/*       */   public static final int MQIA_INTRA_GROUP_QUEUING = 64;
/*       */   public static final int MQIA_IP_ADDRESS_VERSION = 93;
/*       */   public static final int MQIA_KEY_REUSE_COUNT = 267;
/*       */   public static final int MQIA_LAST = 2000;
/*       */   public static final int MQIA_LAST_USED = 276;
/*       */   public static final int MQIA_LDAP_AUTHORMD = 263;
/*       */   public static final int MQIA_LDAP_NESTGRP = 264;
/*       */   public static final int MQIA_LDAP_SECURE_COMM = 261;
/*       */   public static final int MQIA_LISTENER_PORT_NUMBER = 85;
/*       */   public static final int MQIA_LISTENER_TIMER = 107;
/*       */   public static final int MQIA_LOCAL_EVENT = 49;
/*       */   public static final int MQIA_LOGGER_EVENT = 94;
/*       */   public static final int MQIA_LU62_CHANNELS = 108;
/*       */   public static final int MQIA_MASTER_ADMIN = 186;
/*       */   public static final int MQIA_MAX_CHANNELS = 109;
/*       */   public static final int MQIA_MAX_CLIENTS = 172;
/*       */   public static final int MQIA_MAX_GLOBAL_LOCKS = 83;
/*       */   public static final int MQIA_MAX_HANDLES = 11;
/*       */   public static final int MQIA_MAX_LOCAL_LOCKS = 84;
/*       */   public static final int MQIA_MAX_MSG_LENGTH = 13;
/*       */   public static final int MQIA_MAX_OPEN_Q = 80;
/*       */   public static final int MQIA_MAX_PRIORITY = 14;
/*       */   public static final int MQIA_MAX_PROPERTIES_LENGTH = 192;
/*       */   public static final int MQIA_MAX_Q_DEPTH = 15;
/*       */   public static final int MQIA_MAX_Q_FILE_SIZE = 274;
/*       */   public static final int MQIA_MAX_Q_TRIGGERS = 90;
/*       */   public static final int MQIA_MAX_RECOVERY_TASKS = 171;
/*       */   public static final int MQIA_MAX_RESPONSES = 230;
/*       */   public static final int MQIA_MAX_UNCOMMITTED_MSGS = 33;
/*       */   public static final int MQIA_MCAST_BRIDGE = 233;
/*       */   public static final int MQIA_MEDIA_IMAGE_INTERVAL = 269;
/*       */   public static final int MQIA_MEDIA_IMAGE_LOG_LENGTH = 270;
/*       */   public static final int MQIA_MEDIA_IMAGE_RECOVER_OBJ = 271;
/*       */   public static final int MQIA_MEDIA_IMAGE_RECOVER_Q = 272;
/*       */   public static final int MQIA_MEDIA_IMAGE_SCHEDULING = 268;
/*       */   public static final int MQIA_MONITORING_AUTO_CLUSSDR = 124;
/*       */   public static final int MQIA_MONITORING_CHANNEL = 122;
/*       */   public static final int MQIA_MONITORING_Q = 123;
/*       */   public static final int MQIA_MONITOR_INTERVAL = 81;
/*       */   public static final int MQIA_MSG_DELIVERY_SEQUENCE = 16;
/*       */   public static final int MQIA_MSG_DEQ_COUNT = 38;
/*       */   public static final int MQIA_MSG_ENQ_COUNT = 37;
/*       */   public static final int MQIA_MSG_MARK_BROWSE_INTERVAL = 68;
/*       */   public static final int MQIA_MULTICAST = 176;
/*       */   public static final int MQIA_NAMELIST_TYPE = 72;
/*       */   public static final int MQIA_NAME_COUNT = 19;
/*       */   public static final int MQIA_NPM_CLASS = 78;
/*       */   public static final int MQIA_NPM_DELIVERY = 196;
/*       */   public static final int MQIA_OPEN_INPUT_COUNT = 17;
/*       */   public static final int MQIA_OPEN_OUTPUT_COUNT = 18;
/*       */   public static final int MQIA_OUTBOUND_PORT_MAX = 140;
/*       */   public static final int MQIA_OUTBOUND_PORT_MIN = 110;
/*       */   public static final int MQIA_PAGESET_ID = 62;
/*       */   public static final int MQIA_PERFORMANCE_EVENT = 53;
/*       */   public static final int MQIA_PLATFORM = 32;
/*       */   public static final int MQIA_PM_DELIVERY = 195;
/*       */   public static final int MQIA_POLICY_VERSION = 238;
/*       */   public static final int MQIA_PROPERTY_CONTROL = 190;
/*       */   public static final int MQIA_PROT_POLICY_CAPABILITY = 251;
/*       */   public static final int MQIA_PROXY_SUB = 199;
/*       */   public static final int MQIA_PUBSUB_CLUSTER = 249;
/*       */   public static final int MQIA_PUBSUB_MAXMSG_RETRY_COUNT = 206;
/*       */   public static final int MQIA_PUBSUB_MODE = 187;
/*       */   public static final int MQIA_PUBSUB_NP_MSG = 203;
/*       */   public static final int MQIA_PUBSUB_NP_RESP = 205;
/*       */   public static final int MQIA_PUBSUB_SYNC_PT = 207;
/*       */   public static final int MQIA_PUB_COUNT = 215;
/*       */   public static final int MQIA_PUB_SCOPE = 219;
/*       */   public static final int MQIA_QMGR_CFCONLOS = 245;
/*       */   public static final int MQIA_QMOPT_CONS_COMMS_MSGS = 155;
/*       */   public static final int MQIA_QMOPT_CONS_CRITICAL_MSGS = 154;
/*       */   public static final int MQIA_QMOPT_CONS_ERROR_MSGS = 153;
/*       */   public static final int MQIA_QMOPT_CONS_INFO_MSGS = 151;
/*       */   public static final int MQIA_QMOPT_CONS_REORG_MSGS = 156;
/*       */   public static final int MQIA_QMOPT_CONS_SYSTEM_MSGS = 157;
/*       */   public static final int MQIA_QMOPT_CONS_WARNING_MSGS = 152;
/*       */   public static final int MQIA_QMOPT_CSMT_ON_ERROR = 150;
/*       */   public static final int MQIA_QMOPT_INTERNAL_DUMP = 170;
/*       */   public static final int MQIA_QMOPT_LOG_COMMS_MSGS = 162;
/*       */   public static final int MQIA_QMOPT_LOG_CRITICAL_MSGS = 161;
/*       */   public static final int MQIA_QMOPT_LOG_ERROR_MSGS = 160;
/*       */   public static final int MQIA_QMOPT_LOG_INFO_MSGS = 158;
/*       */   public static final int MQIA_QMOPT_LOG_REORG_MSGS = 163;
/*       */   public static final int MQIA_QMOPT_LOG_SYSTEM_MSGS = 164;
/*       */   public static final int MQIA_QMOPT_LOG_WARNING_MSGS = 159;
/*       */   public static final int MQIA_QMOPT_TRACE_COMMS = 166;
/*       */   public static final int MQIA_QMOPT_TRACE_CONVERSION = 168;
/*       */   public static final int MQIA_QMOPT_TRACE_MQI_CALLS = 165;
/*       */   public static final int MQIA_QMOPT_TRACE_REORG = 167;
/*       */   public static final int MQIA_QMOPT_TRACE_SYSTEM = 169;
/*       */   public static final int MQIA_QSG_DISP = 63;
/*       */   public static final int MQIA_Q_DEPTH_HIGH_EVENT = 43;
/*       */   public static final int MQIA_Q_DEPTH_HIGH_LIMIT = 40;
/*       */   public static final int MQIA_Q_DEPTH_LOW_EVENT = 44;
/*       */   public static final int MQIA_Q_DEPTH_LOW_LIMIT = 41;
/*       */   public static final int MQIA_Q_DEPTH_MAX_EVENT = 42;
/*       */   public static final int MQIA_Q_SERVICE_INTERVAL = 54;
/*       */   public static final int MQIA_Q_SERVICE_INTERVAL_EVENT = 46;
/*       */   public static final int MQIA_Q_TYPE = 20;
/*       */   public static final int MQIA_Q_USERS = 82;
/*       */   public static final int MQIA_READ_AHEAD = 189;
/*       */   public static final int MQIA_RECEIVE_TIMEOUT = 111;
/*       */   public static final int MQIA_RECEIVE_TIMEOUT_MIN = 113;
/*       */   public static final int MQIA_RECEIVE_TIMEOUT_TYPE = 112;
/*       */   public static final int MQIA_REMOTE_EVENT = 50;
/*       */   public static final int MQIA_RESPONSE_RESTART_POINT = 231;
/*       */   public static final int MQIA_RETENTION_INTERVAL = 21;
/*       */   public static final int MQIA_REVERSE_DNS_LOOKUP = 254;
/*       */   public static final int MQIA_SCOPE = 45;
/*       */   public static final int MQIA_SECURITY_CASE = 141;
/*       */   public static final int MQIA_SERVICE_CONTROL = 139;
/*       */   public static final int MQIA_SERVICE_TYPE = 121;
/*       */   public static final int MQIA_SHAREABILITY = 23;
/*       */   public static final int MQIA_SHARED_Q_Q_MGR_NAME = 77;
/*       */   public static final int MQIA_SIGNATURE_ALGORITHM = 236;
/*       */   public static final int MQIA_SSL_EVENT = 75;
/*       */   public static final int MQIA_SSL_FIPS_REQUIRED = 92;
/*       */   public static final int MQIA_SSL_RESET_COUNT = 76;
/*       */   public static final int MQIA_SSL_TASKS = 69;
/*       */   public static final int MQIA_START_STOP_EVENT = 52;
/*       */   public static final int MQIA_STATISTICS_AUTO_CLUSSDR = 130;
/*       */   public static final int MQIA_STATISTICS_CHANNEL = 129;
/*       */   public static final int MQIA_STATISTICS_INTERVAL = 131;
/*       */   public static final int MQIA_STATISTICS_MQI = 127;
/*       */   public static final int MQIA_STATISTICS_Q = 128;
/*       */   public static final int MQIA_STREAM_QUEUE_QOS = 275;
/*       */   public static final int MQIA_SUB_CONFIGURATION_EVENT = 242;
/*       */   public static final int MQIA_SUB_COUNT = 204;
/*       */   public static final int MQIA_SUB_SCOPE = 218;
/*       */   public static final int MQIA_SUITE_B_STRENGTH = 247;
/*       */   public static final int MQIA_SYNCPOINT = 30;
/*       */   public static final int MQIA_TCP_CHANNELS = 114;
/*       */   public static final int MQIA_TCP_KEEP_ALIVE = 115;
/*       */   public static final int MQIA_TCP_STACK_TYPE = 116;
/*       */   public static final int MQIA_TIME_SINCE_RESET = 35;
/*       */   public static final int MQIA_TOLERATE_UNPROTECTED = 235;
/*       */   public static final int MQIA_TOPIC_DEF_PERSISTENCE = 185;
/*       */   public static final int MQIA_TOPIC_NODE_COUNT = 253;
/*       */   public static final int MQIA_TOPIC_TYPE = 208;
/*       */   public static final int MQIA_TRACE_ROUTE_RECORDING = 137;
/*       */   public static final int MQIA_TREE_LIFE_TIME = 183;
/*       */   public static final int MQIA_TRIGGER_CONTROL = 24;
/*       */   public static final int MQIA_TRIGGER_DEPTH = 29;
/*       */   public static final int MQIA_TRIGGER_INTERVAL = 25;
/*       */   public static final int MQIA_TRIGGER_MSG_PRIORITY = 26;
/*       */   public static final int MQIA_TRIGGER_RESTART = 91;
/*       */   public static final int MQIA_TRIGGER_TYPE = 28;
/*       */   public static final int MQIA_UR_DISP = 222;
/*       */   public static final int MQIA_USAGE = 12;
/*       */   public static final int MQIA_USER_LIST = 2000;
/*       */   public static final int MQIA_USE_DEAD_LETTER_Q = 234;
/*       */   public static final int MQIA_WILDCARD_OPERATION = 216;
/*       */   public static final int MQIA_XR_CAPABILITY = 243;
/*       */   public static final int MQIAV_NOT_APPLICABLE = -1;
/*       */   public static final int MQIAV_UNDEFINED = -2;
/*       */   @Deprecated
/*       */   public static final int MQOPMODE_COMPAT = 0;
/*       */   @Deprecated
/*       */   public static final int MQOPMODE_NEW_FUNCTION = 1;
/*       */   public static final int MQMCB_DISABLED = 0;
/*       */   public static final int MQMCB_ENABLED = 1;
/*       */   public static final int MQKEY_REUSE_DISABLED = 0;
/*       */   public static final int MQKEY_REUSE_UNLIMITED = -1;
/*       */   public static final int MQGA_FIRST = 8001;
/*       */   public static final int MQGA_LAST = 9000;
/*       */   public static final int MQOO_BIND_AS_Q_DEF = 0;
/*       */   public static final int MQOO_READ_AHEAD_AS_Q_DEF = 0;
/*       */   public static final int MQOO_INPUT_AS_Q_DEF = 1;
/*       */   public static final int MQOO_INPUT_SHARED = 2;
/*       */   public static final int MQOO_INPUT_EXCLUSIVE = 4;
/*       */   public static final int MQOO_BROWSE = 8;
/*       */   public static final int MQOO_OUTPUT = 16;
/*       */   public static final int MQOO_INQUIRE = 32;
/*       */   public static final int MQOO_SET = 64;
/*       */   public static final int MQOO_SAVE_ALL_CONTEXT = 128;
/*       */   public static final int MQOO_PASS_IDENTITY_CONTEXT = 256;
/*       */   public static final int MQOO_PASS_ALL_CONTEXT = 512;
/*       */   public static final int MQOO_SET_IDENTITY_CONTEXT = 1024;
/*       */   public static final int MQOO_SET_ALL_CONTEXT = 2048;
/*       */   public static final int MQOO_ALTERNATE_USER_AUTHORITY = 4096;
/*       */   public static final int MQOO_FAIL_IF_QUIESCING = 8192;
/*       */   public static final int MQOO_BIND_ON_OPEN = 16384;
/*       */   public static final int MQOO_BIND_ON_GROUP = 4194304;
/*       */   public static final int MQOO_BIND_NOT_FIXED = 32768;
/*       */   public static final int MQOO_CO_OP = 131072;
/*       */   public static final int MQOO_NO_READ_AHEAD = 524288;
/*       */   public static final int MQOO_READ_AHEAD = 1048576;
/*       */   public static final int MQOO_NO_MULTICAST = 2097152;
/*       */   public static final int MQOO_RESOLVE_LOCAL_Q = 262144;
/*       */   public static final int MQOO_RESOLVE_LOCAL_TOPIC = 262144;
/*       */   public static final int MQOO_RESOLVE_NAMES = 65536;
/*       */   public static final int MQTYPE_AS_SET = 0;
/*       */   public static final int MQTYPE_NULL = 2;
/*       */   public static final int MQTYPE_BOOLEAN = 4;
/*       */   public static final int MQTYPE_BYTE_STRING = 8;
/*       */   public static final int MQTYPE_INT8 = 16;
/*       */   public static final int MQTYPE_INT16 = 32;
/*       */   public static final int MQTYPE_INT32 = 64;
/*       */   public static final int MQTYPE_LONG = 64;
/*       */   public static final int MQTYPE_INT64 = 128;
/*       */   public static final int MQTYPE_FLOAT32 = 256;
/*       */   public static final int MQTYPE_FLOAT64 = 512;
/*       */   public static final int MQTYPE_STRING = 1024;
/*       */   public static final int MQVL_NULL_TERMINATED = -1;
/*       */   public static final int MQVL_EMPTY_STRING = 0;
/*       */   public static final int MQSTAT_TYPE_ASYNC_ERROR = 0;
/*       */   public static final int MQSTAT_TYPE_RECONNECTION = 1;
/*       */   public static final int MQSTAT_TYPE_RECONNECTION_ERROR = 2;
/*       */   public static final int MQSO_NONE = 0;
/*       */   public static final int MQSO_NON_DURABLE = 0;
/*       */   public static final int MQSO_READ_AHEAD_AS_Q_DEF = 0;
/*       */   public static final int MQSO_ALTER = 1;
/*       */   public static final int MQSO_CREATE = 2;
/*       */   public static final int MQSO_RESUME = 4;
/*       */   public static final int MQSO_DURABLE = 8;
/*       */   public static final int MQSO_GROUP_SUB = 16;
/*       */   public static final int MQSO_MANAGED = 32;
/*       */   public static final int MQSO_SET_IDENTITY_CONTEXT = 64;
/*       */   public static final int MQSO_NO_MULTICAST = 128;
/*       */   public static final int MQSO_FIXED_USERID = 256;
/*       */   public static final int MQSO_ANY_USERID = 512;
/*       */   public static final int MQSO_PUBLICATIONS_ON_REQUEST = 2048;
/*       */   public static final int MQSO_NEW_PUBLICATIONS_ONLY = 4096;
/*       */   public static final int MQSO_FAIL_IF_QUIESCING = 8192;
/*       */   public static final int MQSO_ALTERNATE_USER_AUTHORITY = 262144;
/*       */   public static final int MQSO_WILDCARD_CHAR = 1048576;
/*       */   public static final int MQSO_WILDCARD_TOPIC = 2097152;
/*       */   public static final int MQSO_SET_CORREL_ID = 4194304;
/*       */   public static final int MQSO_SCOPE_QMGR = 67108864;
/*       */   public static final int MQSO_NO_READ_AHEAD = 134217728;
/*       */   public static final int MQSO_READ_AHEAD = 268435456;
/*       */   public static final int MQSR_ACTION_PUBLICATION = 1;
/*       */   public static final int MQ_MQTT_MAX_KEEP_ALIVE = 65536;
/*       */   public static final int MQ_SSL_KEY_PASSPHRASE_LENGTH = 1024;
/*       */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\constants\CMQC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */