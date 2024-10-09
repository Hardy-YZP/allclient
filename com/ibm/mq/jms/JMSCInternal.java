/*    */ package com.ibm.mq.jms;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JMSCInternal
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSCInternal.java";
/*    */   public static final String SERIALIZE_APPNAME_KEY = "applicationName";
/*    */   public static final String SERIALIZE_ASYNC_EXCEPTIONS_KEY = "asyncExceptions";
/*    */   public static final String SERIALIZE_BASE_TOPIC_NAME_KEY = "baseTopicName";
/*    */   public static final String SERIALIZE_BASE_QUEUE_MANAGER_NAME_KEY = "baseQueueManagerName";
/*    */   public static final String SERIALIZE_BASE_QUEUE_NAME_KEY = "baseQueueName";
/*    */   public static final String SERIALIZE_BROKER_CC_DUR_SUB_QUEUE_KEY = "brokerCCDurSubQueue";
/*    */   public static final String SERIALIZE_BROKER_CC_SUB_QUEUE_KEY = "brokerCCSubQueue";
/*    */   public static final String SERIALIZE_BROKER_CONTROL_QUEUE = "brokerControlQueue";
/*    */   public static final String SERIALIZE_BROKER_DUR_SUB_QUEUE_KEY = "brokerDurSubQueue";
/*    */   public static final String SERIALIZE_BROKER_PUB_QUEUE_KEY = "brokerPubQueue";
/*    */   
/*    */   static {
/* 29 */     if (Trace.isOn)
/* 30 */       Trace.data("com.ibm.mq.jms.JMSCInternal", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSCInternal.java"); 
/*    */   }
/*    */   
/*    */   public static final String SERIALIZE_BROKER_PUB_QUEUE_MANAGER_KEY = "brokerPubQueueManager";
/*    */   public static final String SERIALIZE_BROKER_QUEUE_MANAGER_KEY = "brokerQueueManager";
/*    */   public static final String SERIALIZE_BROKER_SUB_QUEUE_KEY = "brokerSubQueue";
/*    */   public static final String SERIALIZE_BROKER_VERSION_KEY = "brokerVersion";
/*    */   public static final String SERIALIZE_BROKER_VERSION_SET_KEY = "bverSet";
/*    */   public static final String SERIALIZE_BROKER_VERSION_UNSPECIFIED_KEY = "brokerVersionUnspecified";
/*    */   public static final String SERIALIZE_CCDTURL_KEY = "ccdtUrl";
/*    */   public static final String SERIALIZE_ALTERNATE_USER_ID_KEY = "alternateUserId";
/*    */   public static final String SERIALIZE_CCSID_KEY = "CCSID";
/*    */   public static final String SERIALIZE_RECEIVE_CCSID_KEY = "receiveCCSID";
/*    */   public static final String SERIALIZE_RECEIVE_CONVERSION_KEY = "receiveConversion";
/*    */   public static final String SERIALIZE_CHANNEL_KEY = "channel";
/*    */   public static final String SERIALIZE_CLEANUP_INTERVAL_KEY = "cleanupInterval";
/*    */   public static final String SERIALIZE_CLEANUP_LEVEL_KEY = "cleanupLevel";
/*    */   public static final String SERIALIZE_CLIENT_ID_KEY = "clientId";
/*    */   public static final String SERIALIZE_CLIENT_RECONNECT_OPTIONS = "clientReconnectOptions";
/*    */   public static final String SERIALIZE_CLIENT_RECONNECT_TIMEOUT = "clientReconnectTimeout";
/*    */   public static final String SERIALIZE_CLONE_SUPPORT_KEY = "cloneSupport";
/*    */   public static final String SERIALIZE_CONNECTION_NAME_LIST = "connectionNameList";
/*    */   public static final String SERIALIZE_CONNECTION_OPTIONS_KEY = "connOptions";
/*    */   public static final String SERIALIZE_CONNECTION_TAG_KEY = "connTag";
/*    */   public static final String SERIALIZE_DELIVERY_MODE_KEY = "deliveryMode";
/*    */   public static final String SERIALIZE_DESCRIPTION_KEY = "description";
/*    */   public static final String SERIALIZE_DIRECT_AUTH_KEY = "directAuth";
/*    */   public static final String SERIALIZE_ENCODING_KEY = "encoding";
/*    */   public static final String SERIALIZE_EXPIRY_KEY = "expiry";
/*    */   public static final String SERIALIZE_FAIL_IF_QUIESCE_KEY = "failIfQuiesce";
/*    */   public static final String SERIALIZE_HEADER_COMPRESSION_LIST_KEY = "hdrCompList";
/*    */   public static final String SERIALIZE_HOST_NAME_KEY = "hostName";
/*    */   public static final String SERIALIZE_LOCAL_ADDRESS_KEY = "localAddress";
/*    */   public static final String SERIALIZE_MAP_NAME_STYLE_KEY = "mapNameStyle";
/*    */   public static final String SERIALIZE_MAX_BUFFER_SIZE_KEY = "maxBufferSize";
/*    */   public static final String SERIALIZE_MESSAGE_BATCH_SIZE_KEY = "msgBatchSize";
/*    */   public static final String SERIALIZE_MESSAGE_BODY_STYLE_KEY = "messageBodyStyle";
/*    */   public static final String SERIALIZE_MESSAGE_COMPRESSION_LIST_KEY = "msgCompList";
/*    */   public static final String SERIALIZE_MESSAGE_RETENTION_KEY = "messageRetention";
/*    */   public static final String SERIALIZE_MESSAGE_SELECTION_KEY = "messageSelection";
/*    */   public static final String SERIALIZE_MESSAGE_SELECTION_SET_KEY = "mselSet";
/*    */   public static final String SERIALIZE_MQMD_MESSAGE_CONTEXT_KEY = "MQMDMessageContext";
/*    */   public static final String SERIALIZE_MQMD_READ_ENABLED_KEY = "MQMDReadEnabled";
/*    */   public static final String SERIALIZE_MQMD_WRITE_ENABLED_KEY = "MQMDWriteEnabled";
/*    */   public static final String SERIALIZE_MULTICAST_KEY = "multicast";
/*    */   public static final String SERIALIZE_OPTIMISTIC_PUBLICATION_KEY = "optimisticPublication";
/*    */   public static final String SERIALIZE_OUTCOME_NOTIFICATION_KEY = "outcomeNotification";
/*    */   public static final String SERIALIZE_PERSISTENCE_KEY = "persistence";
/*    */   public static final String SERIALIZE_POLLING_INTERVAL_KEY = "pollingInterval";
/*    */   public static final String SERIALIZE_PORT_KEY = "port";
/*    */   public static final String SERIALIZE_PORT_SET_KEY = "portSet";
/*    */   public static final String SERIALIZE_PRIORITY_KEY = "priority";
/*    */   public static final String SERIALIZE_PROCESS_DURATION_KEY = "processDuration";
/*    */   public static final String SERIALIZE_PROVIDER_VERSION_KEY = "providerVersion";
/*    */   public static final String SERIALIZE_PROXY_HOST_NAME_KEY = "proxyHostName";
/*    */   public static final String SERIALIZE_PROXY_PORT_KEY = "proxyPort";
/*    */   public static final String SERIALIZE_PUB_ACK_INTERVAL_KEY = "pubAckInterval";
/*    */   public static final String SERIALIZE_PUT_ASYNC_ALLOWED_KEY = "putAsyncAllowed";
/*    */   public static final String SERIALIZE_QUEUE_MANAGER_KEY = "queueManager";
/*    */   public static final String SERIALIZE_READ_AHEAD_ALLOWED_KEY = "readAheadAllowed";
/*    */   public static final String SERIALIZE_READ_AHEAD_CLOSE_POLICY_KEY = "readAheadClosePolicy";
/*    */   public static final String SERIALIZE_RECEIVE_EXIT_KEY = "receiveExit";
/*    */   public static final String SERIALIZE_RECEIVE_EXIT_INIT_KEY = "receiveExitInit";
/*    */   public static final String SERIALIZE_RECEIVE_ISOLATION_KEY = "receiveIsolation";
/*    */   public static final String SERIALIZE_RESCAN_INTERVAL_KEY = "rescanInterval";
/*    */   public static final String SERIALIZE_SECURITY_EXIT_KEY = "securityExit";
/*    */   public static final String SERIALIZE_REPLY_TO_STYLE_KEY = "replyToStyle";
/*    */   static final String SERIALIZE_SECURITY_EXIT_INIT_KEY = "securityExitInit";
/*    */   static final String SERIALIZE_SEND_EXIT_KEY = "sendExit";
/*    */   static final String SERIALIZE_SEND_EXIT_INIT_KEY = "sendExitInit";
/*    */   static final String SERIALIZE_SEND_CHECK_COUNT_KEY = "sendCheckCount";
/*    */   static final String SERIALIZE_SHARE_CONV_ALLOWED_KEY = "shareConvAllowed";
/*    */   static final String SERIALIZE_SPARSE_SUBSCRIPTIONS_KEY = "sparseSubscriptions";
/*    */   static final String SERIALIZE_SSL_CERT_STORES_COLLECTION_KEY = "sslCertStores_coll";
/*    */   static final String SERIALIZE_SSL_CERT_STORES_STRING_KEY = "sslCertStores_string";
/*    */   static final String SERIALIZE_SSL_CIPHER_SUITE_KEY = "sslCipherSuite";
/*    */   static final String SERIALIZE_SSL_FIPS_REQUIRED_KEY = "sslFipsRequired";
/*    */   static final String SERIALIZE_SSL_PEER_NAME_KEY = "sslPeerName";
/*    */   static final String SERIALIZE_SSL_RESET_COUNT_KEY = "sslResetCount";
/*    */   static final String SERIALIZE_SSL_SOCKET_FACTORY_KEY = "sslSocketFactory";
/*    */   static final String SERIALIZE_STATUS_REFRESH_INTERVAL_KEY = "statusRefreshInterval";
/*    */   static final String SERIALIZE_SUBSCRIPTION_STORE_KEY = "subscriptionStore";
/*    */   static final String SERIALIZE_SYNCPOINT_ALL_GETS_KEY = "syncpointAllGets";
/*    */   static final String SERIALIZE_TARGET_CLIENT_KEY = "targetClient";
/*    */   static final String SERIALIZE_TARGET_CLIENT_MATCHING_KEY = "targetClientMatching";
/*    */   static final String SERIALIZE_TEMPORARY_MODEL_KEY = "temporaryModel";
/*    */   static final String SERIALIZE_TEMPORARY_QUEUE_PREFIX_KEY = "tempQPrefix";
/*    */   static final String SERIALIZE_TEMPORARY_TOPIC_PREFIX_KEY = "tempTopicPrefix";
/*    */   static final String SERIALIZE_TIME_TO_LIVE_KEY = "timeToLive";
/*    */   static final String SERIALIZE_TRANSPORT_TYPE_KEY = "transportType";
/*    */   static final String SERIALIZE_USE_CONNECTION_POOLING_KEY = "useConnectionPooling";
/*    */   static final String SERIALIZE_VERSION_KEY = "version";
/*    */   static final String SERIALIZE_WILDCARD_FORMAT_KEY = "wildcardFormat";
/*    */   static final String SERIALIZE_ORIGINAL_NAME = "originalName";
/*    */   static final String SERIALIZE_UNMAPPABLE_ACTION = "unmappableAction";
/*    */   static final String SERIALIZE_UNMAPPABLE_REPLACEMENT = "unmappableReplacement";
/*    */   static final String REFERENCE_APPLICATION_NAME_KEY = "APPNAME";
/*    */   static final String REFERENCE_BROKER_CCSUB_QUEUE_KEY = "CCSUB";
/*    */   static final String REFERENCE_BROKER_CONTROL_QUEUE_KEY = "BCON";
/*    */   static final String REFERENCE_BROKER_PUB_QUEUE_KEY = "BPUB";
/*    */   static final String REFERENCE_BROKER_QUEUE_MANAGER_KEY = "BQM";
/*    */   static final String REFERENCE_BROKER_SUB_QUEUE_KEY = "BSUB";
/*    */   static final String REFERENCE_BROKER_VERSION_KEY = "BVER";
/*    */   static final String REFERENCE_BROKER_VERSION_UNSPECIFIED_KEY = "BVERU";
/*    */   static final String REFERENCE_BROKER_VERSION_DEFAULT_KEY = "BVERD";
/*    */   static final String REFERENCE_CCDTURL_KEY = "CCDTURL";
/*    */   static final String REFERENCE_CCSID_KEY = "CCS";
/*    */   static final String REFERENCE_CHANNEL_KEY = "CHAN";
/*    */   static final String REFERENCE_CLEANUP_INTERVAL_KEY = "CLINT";
/*    */   static final String REFERENCE_CLEANUP_LEVEL_KEY = "CL";
/*    */   static final String REFERENCE_CLIENT_ID_KEY = "CID";
/*    */   static final String REFERENCE_CLONE_SUPPORT_KEY = "CLS";
/*    */   static final String REFERENCE_CONN_OPTS_KEY = "CTO";
/*    */   static final String REFERENCE_CONNTAG_KEY = "CT";
/*    */   static final String REFERENCE_DESCRIPTION_KEY = "DESC";
/*    */   static final String REFERENCE_DIRECTAUTH_KEY = "DAUTH";
/*    */   static final String REFERENCE_FAIL_IF_QUIESCE_KEY = "FIQ";
/*    */   static final String REFERENCE_HEADER_COMP_KEY = "HC";
/*    */   static final String REFERENCE_HEADER_COMP_STR_KEY = "HCS";
/*    */   static final String REFERENCE_HOST_NAME_KEY = "HOST";
/*    */   static final String REFERENCE_LOCAL_ADDRESS_KEY = "LA";
/*    */   static final String REFERENCE_MAP_NAME_STYLE_KEY = "MNS";
/*    */   static final String REFERENCE_MAXBUFFERSIZE_KEY = "MBSZ";
/*    */   static final String REFERENCE_MESSAGE_RETENTION_KEY = "MRET";
/*    */   static final String REFERENCE_MESSAGE_SELECTION_KEY = "MSEL";
/*    */   static final String REFERENCE_MSG_BATCH_SIZE_KEY = "MBS";
/*    */   static final String REFERENCE_MSG_COMP_KEY = "MC";
/*    */   static final String REFERENCE_MSG_COMP_STR_KEY = "MCS";
/*    */   static final String REFERENCE_MULTICAST_KEY = "MCAST";
/*    */   static final String REFERENCE_OPT_PUB_KEY = "OPTPUB";
/*    */   static final String REFERENCE_OUT_NOTIFY_KEY = "NOTIFY";
/*    */   static final String REFERENCE_POLLING_INTERVAL_KEY = "PINT";
/*    */   static final String REFERENCE_PORT_KEY = "PORT";
/*    */   static final String REFERENCE_PROC_DUR_KEY = "PROCDUR";
/*    */   static final String REFERENCE_PROXY_HOSTNAME_KEY = "PHOST";
/*    */   static final String REFERENCE_PROVIDER_VERSION_KEY = "PVER";
/*    */   static final String REFERENCE_PROXY_PORT_KEY = "PPORT";
/*    */   static final String REFERENCE_PUB_ACK_INTERVAL_KEY = "PAI";
/*    */   static final String REFERENCE_QUEUE_MANAGER_KEY = "QMGR";
/*    */   static final String REFERENCE_RCV_ISOL_KEY = "RCVISOL";
/*    */   static final String REFERENCE_RECEIVE_EXIT_KEY = "RCX";
/*    */   static final String REFERENCE_RECEIVE_EXIT_INIT_KEY = "RCXI";
/*    */   static final String REFERENCE_RESCAN_INTERVAL_KEY = "RINT";
/*    */   static final String REFERENCE_SECURITY_EXIT_KEY = "SCX";
/*    */   static final String REFERENCE_SECURITY_EXIT_INIT_KEY = "SCXI";
/*    */   static final String REFERENCE_SEND_EXIT_KEY = "SDX";
/*    */   static final String REFERENCE_SEND_EXIT_INIT_KEY = "SDXI";
/*    */   static final String REFERENCE_SEND_CHECK_COUNT_KEY = "SCC";
/*    */   static final String REFERENCE_SPARSE_SUBS_KEY = "SSUBS";
/*    */   static final String REFERENCE_SHARE_CONV_ALLOWED_KEY = "SCALD";
/*    */   static final String REFERENCE_SSL_CERT_STORES_KEY = "SCRL";
/*    */   static final String REFERENCE_SSL_CERT_STORES_COL_KEY = "SCRLC";
/*    */   static final String REFERENCE_SSL_CIPHER_SUITE_KEY = "SCPHS";
/*    */   static final String REFERENCE_SSL_FIPS_REQUIRED_KEY = "SFIPS";
/*    */   static final String REFERENCE_SSL_PEER_NAME_KEY = "SPEER";
/*    */   static final String REFERENCE_SSL_RESET_COUNT_KEY = "SRC";
/*    */   static final String REFERENCE_SSL_SCT_FAC_KEY = "SFAC";
/*    */   static final String REFERENCE_STATUS_REFRESH_INTERVAL_KEY = "SRI";
/*    */   static final String REFERENCE_SUBSCRIPTION_STORE_KEY = "SUBST";
/*    */   static final String REFERENCE_SYNCPOINT_ALL_GETS_KEY = "SPAG";
/*    */   static final String REFERENCE_TARG_CLIENT_MATCH_KEY = "TCM";
/*    */   static final String REFERENCE_TEMPORARY_MODEL_KEY = "TM";
/*    */   static final String REFERENCE_TEMP_TOPIC_PREFIX_KEY = "TTP";
/*    */   static final String REFERENCE_TEMPQ_PREFIX_KEY = "TQPFX";
/*    */   static final String REFERENCE_TRANSPORT_TYPE_KEY = "TRAN";
/*    */   static final String REFERENCE_USE_CONN_POOLING_KEY = "UCP";
/*    */   static final String REFERENCE_VERSION_KEY = "VER";
/*    */   static final String REFERENCE_WILDCARD_FORMAT_KEY = "WCFMT";
/*    */   static final String REFERENCE_ASYNC_EXCEPTIONS_KEY = "AEX";
/*    */   static final String REFERENCE_REPLY_TO_STYLE = "RTOST";
/*    */   static final String REFERENCE_CLIENT_RECONNECT_STATUS = "CRS";
/*    */   static final String REFERENCE_CLIENT_RECONNECT_OPTIONS = "CROPT";
/*    */   static final String REFERENCE_CLIENT_RECONNECT_TIMEOUT = "CRT";
/*    */   static final String REFERENCE_CLIENT_RECONNECT_HOSTS = "CRSHOSTS";
/*    */   static final String REFERENCE_CONNECTION_NAME_LIST = "CNLIST";
/*    */   static final String REFERENCE_ORIGINAL_NAME = "ORIGNAME";
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSCInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */