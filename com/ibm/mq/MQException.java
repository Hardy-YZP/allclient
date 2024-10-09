/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.ResourceBundle;
/*      */ 
/*      */ 
/*      */ public class MQException
/*      */   extends Exception
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQException.java";
/*      */   static final long serialVersionUID = 7338332446909576329L;
/*      */   @Deprecated
/*      */   public static final int MQCC_OK = 0;
/*      */   @Deprecated
/*      */   public static final int MQCC_WARNING = 1;
/*      */   @Deprecated
/*      */   public static final int MQCC_FAILED = 2;
/*      */   @Deprecated
/*      */   public static final int MQCC_UNKNOWN = -1;
/*      */   @Deprecated
/*      */   public static final int MQRC_NONE = 0;
/*      */   @Deprecated
/*      */   public static final int MQRC_ALIAS_BASE_Q_TYPE_ERROR = 2001;
/*      */   @Deprecated
/*      */   public static final int MQRC_ALREADY_CONNECTED = 2002;
/*      */   @Deprecated
/*      */   public static final int MQRC_BACKED_OUT = 2003;
/*      */   @Deprecated
/*      */   public static final int MQRC_BUFFER_ERROR = 2004;
/*      */   @Deprecated
/*      */   public static final int MQRC_BUFFER_LENGTH_ERROR = 2005;
/*      */   @Deprecated
/*      */   public static final int MQRC_CHAR_ATTR_LENGTH_ERROR = 2006;
/*      */   
/*      */   static {
/*   40 */     if (Trace.isOn) {
/*   41 */       Trace.data("com.ibm.mq.MQException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQException.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHAR_ATTRS_ERROR = 2007;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHAR_ATTRS_TOO_SHORT = 2008;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONNECTION_BROKEN = 2009;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DATA_LENGTH_ERROR = 2010;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DYNAMIC_Q_NAME_ERROR = 2011;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ENVIRONMENT_ERROR = 2012;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_EXPIRY_ERROR = 2013;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FEEDBACK_ERROR = 2014;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_GET_INHIBITED = 2016;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HANDLE_NOT_AVAILABLE = 2017;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HCONN_ERROR = 2018;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HOBJ_ERROR = 2019;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INHIBIT_VALUE_ERROR = 2020;
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INT_ATTR_COUNT_ERROR = 2021;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INT_ATTR_COUNT_TOO_SMALL = 2022;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INT_ATTRS_ARRAY_ERROR = 2023;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SYNCPOINT_LIMIT_REACHED = 2024;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MAX_CONNS_LIMIT_REACHED = 2025;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MD_ERROR = 2026;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MISSING_REPLY_TO_Q = 2027;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_TYPE_ERROR = 2029;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_TOO_BIG_FOR_Q = 2030;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_TOO_BIG_FOR_Q_MGR = 2031;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NO_MSG_AVAILABLE = 2033;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NO_MSG_UNDER_CURSOR = 2034;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_AUTHORIZED = 2035;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_BROWSE = 2036;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_INPUT = 2037;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_INQUIRE = 2038;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_OUTPUT = 2039;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_SET = 2040;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_CHANGED = 2041;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_IN_USE = 2042;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_TYPE_ERROR = 2043;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OD_ERROR = 2044;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OPTION_NOT_VALID_FOR_TYPE = 2045;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OPTIONS_ERROR = 2046;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PERSISTENCE_ERROR = 2047;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PERSISTENT_NOT_ALLOWED = 2048;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PRIORITY_EXCEEDS_MAXIMUM = 2049;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PRIORITY_ERROR = 2050;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PUT_INHIBITED = 2051;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_DELETED = 2052;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_FULL = 2053;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_NOT_EMPTY = 2055;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_SPACE_NOT_AVAILABLE = 2056;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_TYPE_ERROR = 2057;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_MGR_NAME_ERROR = 2058;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_MGR_NOT_AVAILABLE = 2059;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_REPORT_OPTIONS_ERROR = 2061;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SECOND_MARK_NOT_ALLOWED = 2062;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SECURITY_ERROR = 2063;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_COUNT_ERROR = 2065;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_LIMIT_EXCEEDED = 2066;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_ERROR = 2067;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_NOT_FOR_TYPE = 2068;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SIGNAL_OUTSTANDING = 2069;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SIGNAL_REQUEST_ACCEPTED = 2070;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_STORAGE_NOT_AVAILABLE = 2071;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SYNCPOINT_NOT_AVAILABLE = 2072;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TRIGGER_CONTROL_ERROR = 2075;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TRIGGER_DEPTH_ERROR = 2076;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TRIGGER_MSG_PRIORITY_ERR = 2077;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TRIGGER_TYPE_ERROR = 2078;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TRUNCATED_MSG_ACCEPTED = 2079;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TRUNCATED_MSG_FAILED = 2080;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_ALIAS_BASE_Q = 2082;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_OBJECT_NAME = 2085;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_OBJECT_Q_MGR = 2086;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_REMOTE_Q_MGR = 2087;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_WAIT_INTERVAL_ERROR = 2090;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_XMIT_Q_TYPE_ERROR = 2091;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_XMIT_Q_USAGE_ERROR = 2092;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_PASS_ALL = 2093;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_PASS_IDENT = 2094;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_SET_ALL = 2095;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_OPEN_FOR_SET_IDENT = 2096;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONTEXT_HANDLE_ERROR = 2097;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONTEXT_NOT_AVAILABLE = 2098;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SIGNAL1_ERROR = 2099;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_ALREADY_EXISTS = 2100;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_DAMAGED = 2101;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RESOURCE_PROBLEM = 2102;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ANOTHER_Q_MGR_CONNECTED = 2103;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_REPORT_OPTION = 2104;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_STORAGE_CLASS_ERROR = 2105;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_COD_NOT_VALID_FOR_XCF_Q = 2106;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_XWAIT_CANCELED = 2107;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_XWAIT_ERROR = 2108;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SUPPRESSED_BY_EXIT = 2109;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FORMAT_ERROR = 2110;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SOURCE_CCSID_ERROR = 2111;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SOURCE_INTEGER_ENC_ERROR = 2112;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SOURCE_DECIMAL_ENC_ERROR = 2113;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SOURCE_FLOAT_ENC_ERROR = 2114;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TARGET_CCSID_ERROR = 2115;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TARGET_INTEGER_ENC_ERROR = 2116;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TARGET_DECIMAL_ENC_ERROR = 2117;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TARGET_FLOAT_ENC_ERROR = 2118;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NOT_CONVERTED = 2119;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONVERTED_MSG_TOO_BIG = 2120;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NO_EXTERNAL_PARTICIPANTS = 2121;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PARTICIPANT_NOT_AVAILABLE = 2122;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OUTCOME_MIXED = 2123;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OUTCOME_PENDING = 2124;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_STORAGE_SHORTAGE = 2127;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_CONN_LOAD_ERROR = 2129;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_SERV_LOAD_ERROR = 2130;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_DEFS_ERROR = 2131;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_DEFS_LOAD_ERROR = 2132;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_CONV_LOAD_ERROR = 2133;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MULTIPLE_REASONS = 2136;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OPEN_FAILED = 2137;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_DISC_LOAD_ERROR = 2138;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CNO_ERROR = 2139;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CICS_WAIT_FAILED = 2140;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DLH_ERROR = 2141;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HEADER_ERROR = 2142;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SOURCE_LENGTH_ERROR = 2143;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TARGET_LENGTH_ERROR = 2144;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SOURCE_BUFFER_ERROR = 2145;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TARGET_BUFFER_ERROR = 2146;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DBCS_ERROR = 2150;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_NAME_ERROR = 2152;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_Q_MGR_NAME_ERROR = 2153;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RECS_PRESENT_ERROR = 2154;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_RECORDS_ERROR = 2155;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RESPONSE_RECORDS_ERROR = 2156;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ASID_MISMATCH = 2157;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PMO_RECORD_FLAGS_ERROR = 2158;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PUT_MSG_RECORDS_ERROR = 2159;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONN_ID_IN_USE = 2160;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_MGR_QUIESCING = 2161;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_MGR_STOPPING = 2162;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DUPLICATE_RECOV_COORD = 2163;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PMO_ERROR = 2173;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_API_EXIT_NOT_FOUND = 2182;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_API_EXIT_LOAD_ERROR = 2183;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_REMOTE_Q_NAME_ERROR = 2184;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCONSISTENT_PERSISTENCE = 2185;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_GMO_ERROR = 2186;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_STOPPED_BY_CLUSTER_EXIT = 2188;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLUSTER_RESOLUTION_ERROR = 2189;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONVERTED_STRING_TOO_BIG = 2190;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TMC_ERROR = 2191;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PAGESET_FULL = 2192;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PAGESET_ERROR = 2193;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NAME_NOT_VALID_FOR_TYPE = 2194;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNEXPECTED_ERROR = 2195;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_XMIT_Q = 2196;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_DEF_XMIT_Q = 2197;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DEF_XMIT_Q_TYPE_ERROR = 2198;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DEF_XMIT_Q_USAGE_ERROR = 2199;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_MARKED_BROWSE_CO_OP = 2200;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NAME_IN_USE = 2201;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONNECTION_QUIESCING = 2202;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONNECTION_STOPPING = 2203;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ADAPTER_NOT_AVAILABLE = 2204;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_ID_ERROR = 2206;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CORREL_ID_ERROR = 2207;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FILE_SYSTEM_ERROR = 2208;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NO_MSG_LOCKED = 2209;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FILE_NOT_AUDITED = 2216;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONNECTION_NOT_AUTHORIZED = 2217;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_TOO_BIG_FOR_CHANNEL = 2218;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CALL_IN_PROGRESS = 2219;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RMH_ERROR = 2220;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_MGR_ACTIVE = 2222;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_MGR_NOT_ACTIVE = 2223;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_DEPTH_HIGH = 2224;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_DEPTH_LOW = 2225;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_SERVICE_INTERVAL_HIGH = 2226;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_SERVICE_INTERVAL_OK = 2227;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNIT_OF_WORK_NOT_STARTED = 2232;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_AUTO_DEF_OK = 2233;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_AUTO_DEF_ERROR = 2234;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CFH_ERROR = 2235;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CFIL_ERROR = 2236;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CFIN_ERROR = 2237;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CFSL_ERROR = 2238;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CFST_ERROR = 2239;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCOMPLETE_GROUP = 2241;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCOMPLETE_MSG = 2242;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCONSISTENT_CCSIDS = 2243;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCONSISTENT_ENCODINGS = 2244;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCONSISTENT_UOW = 2245;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INVALID_MSG_UNDER_CURSOR = 2246;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MATCH_OPTIONS_ERROR = 2247;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MDE_ERROR = 2248;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_FLAGS_ERROR = 2249;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_SEQ_NUMBER_ERROR = 2250;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OFFSET_ERROR = 2251;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ORIGINAL_LENGTH_ERROR = 2252;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SEGMENT_LENGTH_ZERO = 2253;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UOW_NOT_AVAILABLE = 2255;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_WRONG_GMO_VERSION = 2256;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_WRONG_MD_VERSION = 2257;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_GROUP_ID_ERROR = 2258;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCONSISTENT_BROWSE = 2259;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_XQH_ERROR = 2260;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SRC_ENV_ERROR = 2261;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SRC_NAME_ERROR = 2262;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DEST_ENV_ERROR = 2263;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DEST_NAME_ERROR = 2264;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TM_ERROR = 2265;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLUSTER_EXIT_ERROR = 2266;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLUSTER_EXIT_LOAD_ERROR = 2267;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLUSTER_PUT_INHIBITED = 2268;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLUSTER_RESOURCE_ERROR = 2269;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NO_DESTINATIONS_AVAILABLE = 2270;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OPTION_ENVIRONMENT_ERROR = 2274;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CD_ERROR = 2277;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLIENT_CONN_ERROR = 2278;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_STOPPED_BY_USER = 2279;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HCONFIG_ERROR = 2280;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FUNCTION_ERROR = 2281;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_STARTED = 2282;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_STOPPED = 2283;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_CONV_ERROR = 2284;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SERVICE_NOT_AVAILABLE = 2285;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INITIALIZATION_FAILED = 2286;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_TERMINATION_FAILED = 2287;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_Q_NAME = 2288;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SERVICE_ERROR = 2289;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_Q_ALREADY_EXISTS = 2290;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_USER_ID_NOT_AVAILABLE = 2291;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_ENTITY = 2292;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_AUTH_ENTITY = 2293;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNKNOWN_REF_OBJECT = 2294;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_ACTIVATED = 2295;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHANNEL_NOT_ACTIVATED = 2296;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UOW_CANCELED = 2297;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FUNCTION_NOT_SUPPORTED = 2298;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_TYPE_ERROR = 2299;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_COMMAND_TYPE_ERROR = 2300;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MULTIPLE_INSTANCE_ERROR = 2301;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SYSTEM_ITEM_NOT_ALTERABLE = 2302;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_BAG_CONVERSION_ERROR = 2303;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_OUT_OF_RANGE = 2304;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_NOT_UNIQUE = 2305;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INDEX_NOT_PRESENT = 2306;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_STRING_ERROR = 2307;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ENCODING_NOT_SUPPORTED = 2308;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_NOT_PRESENT = 2309;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OUT_SELECTOR_ERROR = 2310;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_STRING_TRUNCATED = 2311;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_WRONG_TYPE = 2312;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INCONSISTENT_ITEM_TYPE = 2313;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INDEX_ERROR = 2314;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SYSTEM_BAG_NOT_ALTERABLE = 2315;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ITEM_COUNT_ERROR = 2316;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_FORMAT_NOT_SUPPORTED = 2317;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SELECTOR_NOT_SUPPORTED = 2318;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ITEM_VALUE_ERROR = 2319;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HBAG_ERROR = 2320;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PARAMETER_MISSING = 2321;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CMD_SERVER_NOT_AVAILABLE = 2322;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_STRING_LENGTH_ERROR = 2323;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_INQUIRY_COMMAND_ERROR = 2324;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NESTED_BAG_NOT_SUPPORTED = 2325;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_BAG_WRONG_TYPE = 2326;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_ITEM_TYPE_ERROR = 2327;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SYSTEM_BAG_NOT_DELETABLE = 2328;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SYSTEM_ITEM_NOT_DELETABLE = 2329;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CODED_CHAR_SET_ID_ERROR = 2330;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_TOKEN_ERROR = 2331;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MISSING_WIH = 2332;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_WIH_ERROR = 2333;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RFH_ERROR = 2334;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RFH_STRING_ERROR = 2335;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RFH_COMMAND_ERROR = 2336;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RFH_PARM_ERROR = 2337;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RFH_DUPLICATE_PARM = 2338;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_RFH_PARM_MISSING = 2339;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CHAR_CONVERSION_ERROR = 2340;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UCS2_CONVERSION_ERROR = 2341;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_DB2_NOT_AVAILABLE = 2342;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_NOT_UNIQUE = 2343;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONN_TAG_NOT_RELEASED = 2344;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CF_NOT_AVAILABLE = 2345;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CF_STRUC_IN_USE = 2346;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CF_STRUC_LIST_HDR_IN_USE = 2347;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CF_STRUC_AUTH_FAILED = 2348;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CF_STRUC_ERROR = 2349;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CONN_TAG_NOT_USABLE = 2350;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_GLOBAL_UOW_CONFLICT = 2351;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_LOCAL_UOW_CONFLICT = 2352;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_HANDLE_IN_USE_FOR_UOW = 2353;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UOW_ENLISTMENT_ERROR = 2354;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UOW_MIX_NOT_SUPPORTED = 2355;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_WXP_ERROR = 2356;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CURRENT_RECORD_ERROR = 2357;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NEXT_OFFSET_ERROR = 2358;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NO_RECORD_AVAILABLE = 2359;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OBJECT_LEVEL_INCOMPATIBLE = 2360;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_NEXT_RECORD_ERROR = 2361;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_BACKOUT_THRESHOLD_REACHED = 2362;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_MSG_NOT_MATCHED = 2363;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_JMS_FORMAT_ERROR = 2364;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_PARTICIPANT_NOT_DEFINED = 2372;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_NOT_ALLOWED = 2396;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_JSSE_ERROR = 2397;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_PEER_NAME_MISMATCH = 2398;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_PEER_NAME_ERROR = 2399;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_UNSUPPORTED_CIPHER_SUITE = 2400;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_CERTIFICATE_REVOKED = 2401;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_CERT_STORE_ERROR = 2402;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_INITIALIZATION_ERROR = 2393;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLIENT_EXIT_LOAD_ERROR = 2406;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLIENT_EXIT_ERROR = 2407;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_SSL_KEY_RESET_ERROR = 2409;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_CLIENT_CHANNEL_CONFLICT = 2423;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_READ_AHEAD_MSGS = 2458;
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRC_OPTIONS_CHANGED = 2457;
/*      */   
/*      */   public static final String MQJI001 = "MQJI001";
/*      */   
/*      */   public static final String MQJI002 = "MQJI002";
/*      */   
/*      */   protected static final String MQJI003 = "MQJI003";
/*      */   
/*      */   protected static final String MQJI006 = "MQJI006";
/*      */   
/*      */   protected static final String MQJI008 = "MQJI008";
/*      */   
/*      */   protected static final String MQJI009 = "MQJI009";
/*      */   
/*      */   protected static final String MQJI011 = "MQJI011";
/*      */   
/*      */   public static final String MQJI013 = "MQJI013";
/*      */   
/*      */   protected static final String MQJI014 = "MQJI014";
/*      */   
/*      */   protected static final String MQJI015 = "MQJI015";
/*      */   
/*      */   public static final String MQJI016 = "MQJI016";
/*      */   
/*      */   public static final String MQJI017 = "MQJI017";
/*      */   
/*      */   protected static final String MQJI022 = "MQJI022";
/*      */   
/*      */   protected static final String MQJI023 = "MQJI023";
/*      */   
/*      */   protected static final String MQJI024 = "MQJI024";
/*      */   
/*      */   public static final String MQJI025 = "MQJI025";
/*      */   
/*      */   public static final String MQJI026 = "MQJI026";
/*      */   
/*      */   public static final String MQJI027 = "MQJI027";
/*      */   
/*      */   public static final String MQJI028 = "MQJI028";
/*      */   
/*      */   public static final String MQJI029 = "MQJI029";
/*      */   
/*      */   protected static final String MQJI030 = "MQJI030";
/*      */   
/*      */   protected static final String MQJI038 = "MQJI038";
/*      */   
/*      */   public static final String MQJI039 = "MQJI039";
/*      */   
/*      */   public static final String MQJI040 = "MQJI040";
/*      */   
/*      */   public static final String MQJI041 = "MQJI041";
/*      */   
/*      */   public static final String MQJI042 = "MQJI042";
/*      */   
/*      */   protected static final String MQJE001 = "MQJE001";
/*      */   
/*      */   protected static final String MQJE001b = "MQJE001b";
/*      */   
/*      */   protected static final String MQJE002 = "MQJE002";
/*      */   
/*      */   protected static final String MQJE003 = "MQJE003";
/*      */   
/*      */   protected static final String MQJE004 = "MQJE004";
/*      */   
/*      */   protected static final String MQJE005 = "MQJE005";
/*      */   
/*      */   protected static final String MQJE006 = "MQJE006";
/*      */   
/*      */   protected static final String MQJE007 = "MQJE007";
/*      */   
/*      */   protected static final String MQJE008 = "MQJE008";
/*      */   
/*      */   protected static final String MQJE009 = "MQJE009";
/*      */   
/*      */   protected static final String MQJE010 = "MQJE010";
/*      */   
/*      */   public static final String MQJE011 = "MQJE011";
/*      */   
/*      */   public static final String MQJE012 = "MQJE012";
/*      */   
/*      */   public static final String MQJE013 = "MQJE013";
/*      */   
/*      */   protected static final String MQJE016 = "MQJE016";
/*      */   
/*      */   protected static final String MQJE017 = "MQJE017";
/*      */   
/*      */   protected static final String MQJE018 = "MQJE018";
/*      */   
/*      */   protected static final String MQJE019 = "MQJE019";
/*      */   
/*      */   protected static final String MQJE020 = "MQJE020";
/*      */   
/*      */   protected static final String MQJE021 = "MQJE021";
/*      */   
/*      */   protected static final String MQJE022 = "MQJE022";
/*      */   
/*      */   protected static final String MQJE023 = "MQJE023";
/*      */   
/*      */   protected static final String MQJE024 = "MQJE024";
/*      */   
/*      */   protected static final String MQJE025 = "MQJE025";
/*      */   
/*      */   protected static final String MQJE026 = "MQJE026";
/*      */   
/*      */   protected static final String MQJE027 = "MQJE027";
/*      */   
/*      */   protected static final String MQJE028 = "MQJE028";
/*      */   
/*      */   protected static final String MQJE029 = "MQJE029";
/*      */   
/*      */   public static final String MQJE030 = "MQJE030";
/*      */   
/*      */   protected static final String MQJE031 = "MQJE031";
/*      */   
/*      */   protected static final String MQJE032 = "MQJE032";
/*      */   
/*      */   protected static final String MQJE033 = "MQJE033";
/*      */   
/*      */   protected static final String MQJE034 = "MQJE034";
/*      */   
/*      */   protected static final String MQJE035 = "MQJE035";
/*      */   
/*      */   protected static final String MQJE036 = "MQJE036";
/*      */   
/*      */   protected static final String MQJE037 = "MQJE037";
/*      */   
/*      */   protected static final String MQJE038 = "MQJE038";
/*      */   
/*      */   protected static final String MQJE039 = "MQJE039";
/*      */   
/*      */   protected static final String MQJE040 = "MQJE040";
/*      */   
/*      */   protected static final String MQJE041 = "MQJE041";
/*      */   
/*      */   public static final String MQJE042 = "MQJE042";
/*      */   
/*      */   public static final String MQJE043 = "MQJE043";
/*      */   
/*      */   public static final String MQJE044 = "MQJE044";
/*      */   
/*      */   public static final String MQJE046 = "MQJE046";
/*      */   
/*      */   public static final String MQJE055 = "MQJE055";
/*      */   
/*      */   public static final String MQJE056 = "MQJE056";
/*      */   
/*      */   protected static final String MQJE057 = "MQJE057";
/*      */   
/*      */   protected static final String MQJE058 = "MQJE058";
/*      */   
/*      */   public static final String MID_ProductName = "MID_ProductName";
/*      */   
/*      */   public static final String MID_MngCon_CmdLvl = "MID_MngCon_CmdLvl";
/*      */   
/*      */   public static final String MID_SecManError = "MID_SecManError";
/*      */   
/*      */   public static final String MID_XANativeError = "MID_SecManError";
/*      */   
/*      */   public static final String MID_OpenFailed = "MID_OpenFailed";
/*      */   
/*      */   public static final String MID_OpFailed = "MID_OpFailed";
/*      */   
/*      */   public static final String MID_ResourceClosed = "MID_ResourceClosed";
/*      */   
/*      */   public static final String MQJE064 = "MQJE064";
/*      */   
/*      */   protected static final String MQJE065 = "MQJE065";
/*      */   
/*      */   protected static final String MID_Rejected_XA_Client = "MID_Rejected_XA_Client";
/*      */   
/*      */   public static final String MQJE066 = "MQJE066";
/*      */   
/*      */   public static final String MQJE067 = "MQJE067";
/*      */   
/*      */   public static final String MQJE068 = "MQJE068";
/*      */   
/*      */   public static final String MQJE069 = "MQJE069";
/*      */   
/*      */   public static final String MQJE071 = "MQJE071";
/*      */   
/*      */   protected static final String MQJE080 = "MQJE080";
/*      */   
/*      */   protected static final String MQJE081 = "MQJE081";
/*      */   
/*      */   protected static final String MQJE082 = "MQJE082";
/*      */   
/*      */   protected static final String MQCCF_NOT_SUPPURTED = "MQCCF_NOT_SUPPURTED";
/*      */   
/*      */   protected static final String MQINVCRF_OBJECT = "MQINVCRF_OBJECT";
/*      */   
/*      */   protected static final String MQRESOURCE_ERROR = "MQRESOURCE_ERROR";
/*      */   
/*      */   protected static final String MQNULLPOINTER = "MQNULLPOINTER";
/*      */   
/*      */   protected static final String MQEOFEXCEPTION = "MQEOFEXCEPTION";
/*      */   
/*      */   protected static final String MQIOEXCEPTION = "MQIOEXCEPTION";
/*      */   
/*      */   protected static final String MQDECODEERROR = "MQDECODEERROR";
/*      */   
/*      */   protected static final String MQJE089 = "MQJE089";
/*      */   
/*      */   protected static final String MQJE090 = "MQJE090";
/*      */   
/*      */   protected static final String MQJE091 = "MQJE091";
/*      */   
/* 1206 */   public static volatile OutputStreamWriter log = null;
/*      */ 
/*      */   
/* 1209 */   private static ResourceBundle exceptionMessages = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int completionCode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int reasonCode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public transient Object exceptionSource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1237 */   private String ostrMessage = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1246 */   private final int msgId = 0;
/*      */   
/*      */   private final String v7msgId;
/*      */   
/*      */   private final int numInserts;
/*      */   private final String insert1;
/*      */   private final String insert2;
/*      */   private static boolean isInitialised = false;
/* 1254 */   private static final Object initialiseLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void initialiseBaseJavaClasses() {
/* 1264 */     if (Trace.isOn) {
/* 1265 */       Trace.entry("com.ibm.mq.MQException", "initialiseBaseJavaClasses()");
/*      */     }
/*      */     
/* 1268 */     if (!isInitialised) {
/* 1269 */       synchronized (initialiseLock) {
/* 1270 */         if (!isInitialised) {
/* 1271 */           initialiseBaseJavaClasses_inner();
/* 1272 */           isInitialised = true;
/*      */         } 
/*      */       } 
/*      */     }
/* 1276 */     if (Trace.isOn) {
/* 1277 */       Trace.exit("com.ibm.mq.MQException", "initialiseBaseJavaClasses()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static void initialiseBaseJavaClasses_inner() {
/* 1283 */     if (Trace.isOn) {
/* 1284 */       Trace.entry("com.ibm.mq.MQException", "initialiseBaseJavaClasses_inner()");
/*      */     }
/*      */     
/*      */     try {
/* 1288 */       exceptionMessages = ResourceBundle.getBundle("mqji");
/*      */     }
/* 1290 */     catch (MissingResourceException ex) {
/* 1291 */       if (Trace.isOn) {
/* 1292 */         Trace.catchBlock("com.ibm.mq.MQException", "initialiseBaseJavaClasses_inner()", ex);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1298 */     if (Trace.isOn) {
/* 1299 */       Trace.exit("com.ibm.mq.MQException", "initialiseBaseJavaClasses_inner()");
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
/*      */   public MQException(String message, String messageId, int reason, int compcode) {
/* 1313 */     super(message);
/* 1314 */     if (Trace.isOn) {
/* 1315 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(String,String,int,int)", new Object[] { message, messageId, 
/* 1316 */             Integer.valueOf(reason), Integer.valueOf(compcode) });
/*      */     }
/*      */     
/* 1319 */     this.ostrMessage = message;
/* 1320 */     this.v7msgId = messageId;
/* 1321 */     this.reasonCode = reason;
/* 1322 */     this.completionCode = compcode;
/*      */     
/* 1324 */     this.insert1 = null;
/* 1325 */     this.insert2 = null;
/* 1326 */     this.numInserts = 0;
/*      */     
/* 1328 */     if (Trace.isOn) {
/* 1329 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(String,String,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompCode() {
/* 1340 */     if (Trace.isOn) {
/* 1341 */       Trace.data(this, "com.ibm.mq.MQException", "getCompCode()", "getter", 
/* 1342 */           Integer.valueOf(this.completionCode));
/*      */     }
/* 1344 */     return this.completionCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getErrorCode() {
/* 1353 */     if (Trace.isOn) {
/* 1354 */       Trace.data(this, "com.ibm.mq.MQException", "getErrorCode()", "getter", this.v7msgId);
/*      */     }
/* 1356 */     return this.v7msgId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReason() {
/* 1365 */     if (Trace.isOn) {
/* 1366 */       Trace.data(this, "com.ibm.mq.MQException", "getReason()", "getter", 
/* 1367 */           Integer.valueOf(this.reasonCode));
/*      */     }
/* 1369 */     return this.reasonCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQException(int completionCode, int reasonCode, Object source) {
/* 1380 */     if (Trace.isOn) {
/* 1381 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object)", new Object[] {
/* 1382 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source
/*      */           });
/*      */     }
/* 1385 */     initialiseBaseJavaClasses();
/*      */     
/* 1387 */     this.completionCode = completionCode;
/* 1388 */     this.reasonCode = reasonCode;
/* 1389 */     this.exceptionSource = source;
/*      */     
/* 1391 */     this.v7msgId = "";
/* 1392 */     this.numInserts = 0;
/* 1393 */     this.insert1 = null;
/* 1394 */     this.insert2 = null;
/* 1395 */     if (Trace.isOn) {
/* 1396 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object)");
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
/*      */   public MQException(int completionCode, int reasonCode, Object source, Throwable cause) {
/* 1412 */     super(cause);
/* 1413 */     if (Trace.isOn) {
/* 1414 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,Throwable)", new Object[] {
/* 1415 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, cause
/*      */           });
/*      */     }
/*      */     
/* 1419 */     initialiseBaseJavaClasses();
/*      */     
/* 1421 */     this.completionCode = completionCode;
/* 1422 */     this.reasonCode = reasonCode;
/* 1423 */     this.exceptionSource = source;
/*      */     
/* 1425 */     this.v7msgId = "";
/* 1426 */     this.numInserts = 0;
/* 1427 */     this.insert1 = null;
/* 1428 */     this.insert2 = null;
/*      */     
/* 1430 */     if (Trace.isOn) {
/* 1431 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,Throwable)");
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
/*      */   protected MQException(int completionCode, int reasonCode, Object source, JmqiException cause) {
/* 1447 */     super((Throwable)cause);
/* 1448 */     if (Trace.isOn) {
/* 1449 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,JmqiException)", new Object[] {
/* 1450 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, cause
/*      */           });
/*      */     }
/*      */     
/* 1454 */     initialiseBaseJavaClasses();
/*      */     
/* 1456 */     this.completionCode = completionCode;
/* 1457 */     this.reasonCode = reasonCode;
/* 1458 */     this.exceptionSource = source;
/*      */     
/* 1460 */     this.v7msgId = "";
/* 1461 */     this.numInserts = 0;
/* 1462 */     this.insert1 = null;
/* 1463 */     this.insert2 = null;
/* 1464 */     if (Trace.isOn) {
/* 1465 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,JmqiException)");
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
/*      */   public MQException(int completionCode, int reasonCode, Object source, String msgId, JmqiException cause) {
/* 1480 */     super((Throwable)cause);
/* 1481 */     if (Trace.isOn) {
/* 1482 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,JmqiException)", new Object[] {
/* 1483 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, msgId, cause
/*      */           });
/*      */     }
/*      */     
/* 1487 */     initialiseBaseJavaClasses();
/*      */     
/* 1489 */     this.completionCode = completionCode;
/* 1490 */     this.reasonCode = reasonCode;
/* 1491 */     this.exceptionSource = source;
/*      */     
/* 1493 */     this.v7msgId = msgId;
/* 1494 */     this.numInserts = 0;
/* 1495 */     this.insert1 = null;
/* 1496 */     this.insert2 = null;
/*      */     
/* 1498 */     if (Trace.isOn) {
/* 1499 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,JmqiException)");
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
/*      */   public MQException(int completionCode, int reasonCode, Object source, String msgId) {
/* 1513 */     if (Trace.isOn) {
/* 1514 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String)", new Object[] {
/* 1515 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, msgId
/*      */           });
/*      */     }
/* 1518 */     initialiseBaseJavaClasses();
/*      */     
/* 1520 */     this.completionCode = completionCode;
/* 1521 */     this.reasonCode = reasonCode;
/* 1522 */     this.exceptionSource = source;
/*      */     
/* 1524 */     this.v7msgId = msgId;
/* 1525 */     this.numInserts = 0;
/* 1526 */     this.insert1 = null;
/* 1527 */     this.insert2 = null;
/*      */     
/* 1529 */     if (Trace.isOn) {
/* 1530 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQException(int completionCode, int reasonCode, Object source, String msgId, String insert, JmqiException cause) {
/* 1539 */     super((Throwable)cause);
/* 1540 */     if (Trace.isOn) {
/* 1541 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String,JmqiException)", new Object[] {
/*      */             
/* 1543 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, msgId, insert, cause
/*      */           });
/*      */     }
/*      */     
/* 1547 */     initialiseBaseJavaClasses();
/*      */     
/* 1549 */     this.completionCode = completionCode;
/* 1550 */     this.reasonCode = reasonCode;
/* 1551 */     this.exceptionSource = source;
/*      */     
/* 1553 */     this.v7msgId = msgId;
/* 1554 */     this.numInserts = 1;
/* 1555 */     this.insert1 = insert;
/* 1556 */     this.insert2 = null;
/*      */     
/* 1558 */     if (Trace.isOn) {
/* 1559 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String,JmqiException)");
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
/*      */   public MQException(int completionCode, int reasonCode, Object source, String msgId, String insert) {
/* 1574 */     if (Trace.isOn) {
/* 1575 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String)", new Object[] {
/* 1576 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, msgId, insert
/*      */           });
/*      */     }
/*      */     
/* 1580 */     initialiseBaseJavaClasses();
/*      */     
/* 1582 */     this.completionCode = completionCode;
/* 1583 */     this.reasonCode = reasonCode;
/* 1584 */     this.exceptionSource = source;
/*      */     
/* 1586 */     this.v7msgId = msgId;
/* 1587 */     this.numInserts = 1;
/* 1588 */     this.insert1 = insert;
/* 1589 */     this.insert2 = null;
/*      */     
/* 1591 */     if (Trace.isOn) {
/* 1592 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQException(int completionCode, int reasonCode, Object source, String msgId, String insert1, String insert2, JmqiException cause) {
/* 1599 */     super((Throwable)cause);
/* 1600 */     if (Trace.isOn) {
/* 1601 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String,String,JmqiException)", new Object[] {
/*      */             
/* 1603 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, msgId, insert1, insert2, cause
/*      */           });
/*      */     }
/*      */     
/* 1607 */     initialiseBaseJavaClasses();
/*      */     
/* 1609 */     this.completionCode = completionCode;
/* 1610 */     this.reasonCode = reasonCode;
/* 1611 */     this.exceptionSource = source;
/*      */     
/* 1613 */     this.v7msgId = msgId;
/* 1614 */     this.numInserts = 2;
/* 1615 */     this.insert1 = insert1;
/* 1616 */     this.insert2 = insert2;
/*      */     
/* 1618 */     if (Trace.isOn) {
/* 1619 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String,String,JmqiException)");
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
/*      */   public MQException(int completionCode, int reasonCode, Object source, String msgId, String insert1, String insert2) {
/* 1635 */     if (Trace.isOn) {
/* 1636 */       Trace.entry(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String,String)", new Object[] {
/* 1637 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source, msgId, insert1, insert2
/*      */           });
/*      */     }
/*      */     
/* 1641 */     initialiseBaseJavaClasses();
/*      */     
/* 1643 */     this.completionCode = completionCode;
/* 1644 */     this.reasonCode = reasonCode;
/* 1645 */     this.exceptionSource = source;
/*      */     
/* 1647 */     this.v7msgId = msgId;
/* 1648 */     this.numInserts = 2;
/* 1649 */     this.insert1 = insert1;
/* 1650 */     this.insert2 = insert2;
/*      */     
/* 1652 */     if (Trace.isOn) {
/* 1653 */       Trace.exit(this, "com.ibm.mq.MQException", "<init>(int,int,Object,String,String,String)");
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
/*      */   public String getMessage() {
/* 1669 */     if (this.ostrMessage == null)
/*      */     {
/*      */       
/* 1672 */       if (exceptionMessages == null) {
/* 1673 */         this.ostrMessage = "Message catalog not found, completion=" + this.completionCode + ", reason=" + this.reasonCode;
/*      */       }
/* 1675 */       else if (this.v7msgId == null || this.v7msgId.length() == 0) {
/*      */         
/* 1677 */         String message = exceptionMessages.getString("MQJE001b");
/* 1678 */         String[] inserts = new String[2];
/* 1679 */         inserts[0] = Integer.toString(this.completionCode);
/* 1680 */         inserts[1] = Integer.toString(this.reasonCode);
/* 1681 */         this.ostrMessage = MessageFormat.format(message, (Object[])inserts);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1686 */         String explanation = exceptionMessages.getString(this.v7msgId);
/* 1687 */         if (this.numInserts > 0) {
/* 1688 */           String[] arrayOfString = new String[this.numInserts];
/* 1689 */           if (this.numInserts > 0) {
/* 1690 */             arrayOfString[0] = this.insert1;
/*      */           }
/* 1692 */           if (this.numInserts > 1) {
/* 1693 */             arrayOfString[1] = this.insert2;
/*      */           }
/* 1695 */           explanation = MessageFormat.format(explanation, (Object[])arrayOfString);
/*      */         } 
/*      */ 
/*      */         
/* 1699 */         String message = exceptionMessages.getString("MQJE001");
/* 1700 */         String[] inserts = new String[3];
/* 1701 */         inserts[0] = Integer.toString(this.completionCode);
/* 1702 */         inserts[1] = Integer.toString(this.reasonCode);
/* 1703 */         inserts[2] = explanation;
/* 1704 */         this.ostrMessage = MessageFormat.format(message, (Object[])inserts);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1710 */     if (Trace.isOn) {
/* 1711 */       Trace.data(this, "com.ibm.mq.MQException", "getMessage()", "getter", this.ostrMessage);
/*      */     }
/* 1713 */     return this.ostrMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getNLSMsg(String msgId) {
/*      */     String msg;
/* 1722 */     if (Trace.isOn) {
/* 1723 */       Trace.entry("com.ibm.mq.MQException", "getNLSMsg(String)", new Object[] { msgId });
/*      */     }
/*      */     
/* 1726 */     initialiseBaseJavaClasses();
/*      */ 
/*      */     
/* 1729 */     if (exceptionMessages != null) {
/*      */       
/*      */       try {
/* 1732 */         msg = exceptionMessages.getString(msgId);
/*      */       }
/* 1734 */       catch (MissingResourceException mre) {
/* 1735 */         if (Trace.isOn) {
/* 1736 */           Trace.catchBlock("com.ibm.mq.MQException", "getNLSMsg(String)", mre);
/*      */         }
/* 1738 */         msg = "No entry found for key " + msgId;
/*      */       } 
/*      */     } else {
/*      */       
/* 1742 */       msg = "Message catalog not found";
/*      */     } 
/*      */     
/* 1745 */     if (Trace.isOn) {
/* 1746 */       Trace.exit("com.ibm.mq.MQException", "getNLSMsg(String)", msg);
/*      */     }
/* 1748 */     return msg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getNLSMsg(String msgId, String insert) {
/*      */     String msg;
/* 1758 */     if (Trace.isOn) {
/* 1759 */       Trace.entry("com.ibm.mq.MQException", "getNLSMsg(String,String)", new Object[] { msgId, insert });
/*      */     }
/*      */     
/* 1762 */     initialiseBaseJavaClasses();
/*      */ 
/*      */     
/* 1765 */     if (exceptionMessages != null) {
/* 1766 */       String tmp = exceptionMessages.getString(msgId);
/* 1767 */       String[] inserts = new String[1];
/* 1768 */       inserts[0] = insert;
/* 1769 */       msg = MessageFormat.format(tmp, (Object[])inserts);
/*      */     } else {
/*      */       
/* 1772 */       msg = "Message catalog not found";
/*      */     } 
/*      */     
/* 1775 */     if (Trace.isOn) {
/* 1776 */       Trace.exit("com.ibm.mq.MQException", "getNLSMsg(String,String)", msg);
/*      */     }
/* 1778 */     return msg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getNLSMsg(String msgId, String insert1, String insert2) {
/*      */     String msg;
/* 1789 */     if (Trace.isOn) {
/* 1790 */       Trace.entry("com.ibm.mq.MQException", "getNLSMsg(String,String,String)", new Object[] { msgId, insert1, insert2 });
/*      */     }
/*      */     
/* 1793 */     initialiseBaseJavaClasses();
/*      */ 
/*      */     
/* 1796 */     if (exceptionMessages != null) {
/* 1797 */       String tmp = exceptionMessages.getString(msgId);
/* 1798 */       String[] inserts = new String[2];
/* 1799 */       inserts[0] = insert1;
/* 1800 */       inserts[1] = insert2;
/* 1801 */       msg = " " + MessageFormat.format(tmp, (Object[])inserts);
/*      */     } else {
/*      */       
/* 1804 */       msg = " Message catalog not found";
/*      */     } 
/* 1806 */     if (Trace.isOn) {
/* 1807 */       Trace.exit("com.ibm.mq.MQException", "getNLSMsg(String,String,String)", msg);
/*      */     }
/* 1809 */     return msg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void logExclude(Integer avoidCode) {
/* 1819 */     if (Trace.isOn) {
/* 1820 */       Trace.entry("com.ibm.mq.MQException", "logExclude(Integer)", new Object[] { avoidCode });
/*      */     }
/*      */     
/* 1823 */     if (Trace.isOn) {
/* 1824 */       Trace.exit("com.ibm.mq.MQException", "logExclude(Integer)");
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
/*      */   public static void logInclude(Integer includeCode) {
/* 1836 */     if (Trace.isOn) {
/* 1837 */       Trace.entry("com.ibm.mq.MQException", "logInclude(Integer)", new Object[] { includeCode });
/*      */     }
/*      */     
/* 1840 */     if (Trace.isOn)
/* 1841 */       Trace.exit("com.ibm.mq.MQException", "logInclude(Integer)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */