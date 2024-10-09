package com.ibm.mq.constants;

public interface CMQZC {
  public static final String copyright_notice = "Licensed Materials - Property of IBM (c) Copyright IBM Corp. 2008, 2023 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
  
  public static final String sccsid = "%Z% %W% %I% %E% %U%";
  
  public static final int MQZIO_PRIMARY = 0;
  
  public static final int MQZIO_SECONDARY = 1;
  
  public static final int MQZTO_PRIMARY = 0;
  
  public static final int MQZTO_SECONDARY = 1;
  
  public static final int MQZCI_DEFAULT = 0;
  
  public static final int MQZCI_CONTINUE = 0;
  
  public static final int MQZCI_STOP = 1;
  
  public static final int MQZAS_VERSION_1 = 1;
  
  public static final int MQZAS_VERSION_2 = 2;
  
  public static final int MQZAS_VERSION_3 = 3;
  
  public static final int MQZAS_VERSION_4 = 4;
  
  public static final int MQZAS_VERSION_5 = 5;
  
  public static final int MQZAS_VERSION_6 = 6;
  
  public static final int MQZAO_CONNECT = 1;
  
  public static final int MQZAO_BROWSE = 2;
  
  public static final int MQZAO_INPUT = 4;
  
  public static final int MQZAO_OUTPUT = 8;
  
  public static final int MQZAO_INQUIRE = 16;
  
  public static final int MQZAO_SET = 32;
  
  public static final int MQZAO_PASS_IDENTITY_CONTEXT = 64;
  
  public static final int MQZAO_PASS_ALL_CONTEXT = 128;
  
  public static final int MQZAO_SET_IDENTITY_CONTEXT = 256;
  
  public static final int MQZAO_SET_ALL_CONTEXT = 512;
  
  public static final int MQZAO_ALTERNATE_USER_AUTHORITY = 1024;
  
  public static final int MQZAO_PUBLISH = 2048;
  
  public static final int MQZAO_SUBSCRIBE = 4096;
  
  public static final int MQZAO_RESUME = 8192;
  
  public static final int MQZAO_ALL_MQI = 16383;
  
  public static final int MQZAO_CREATE = 65536;
  
  public static final int MQZAO_DELETE = 131072;
  
  public static final int MQZAO_DISPLAY = 262144;
  
  public static final int MQZAO_CHANGE = 524288;
  
  public static final int MQZAO_CLEAR = 1048576;
  
  public static final int MQZAO_CONTROL = 2097152;
  
  public static final int MQZAO_CONTROL_EXTENDED = 4194304;
  
  public static final int MQZAO_AUTHORIZE = 8388608;
  
  public static final int MQZAO_ALL_ADMIN = 16646144;
  
  public static final int MQZAO_SYSTEM = 33554432;
  
  public static final int MQZAO_ALL = 50216959;
  
  public static final int MQZAO_REMOVE = 16777216;
  
  public static final int MQZAO_NONE = 0;
  
  public static final int MQZAO_CREATE_ONLY = 67108864;
  
  public static final int MQZAET_NONE = 0;
  
  public static final int MQZAET_PRINCIPAL = 1;
  
  public static final int MQZAET_GROUP = 2;
  
  public static final int MQZAET_UNKNOWN = 3;
  
  public static final int MQZSE_START = 1;
  
  public static final int MQZSE_CONTINUE = 0;
  
  public static final int MQZSL_NOT_RETURNED = 0;
  
  public static final int MQZSL_RETURNED = 1;
  
  public static final int MQZNS_VERSION_1 = 1;
  
  public static final int MQZUS_VERSION_1 = 1;
  
  public static final String MQZED_STRUC_ID = "ZED ";
  
  public static final int MQZED_VERSION_1 = 1;
  
  public static final int MQZED_VERSION_2 = 2;
  
  public static final int MQZED_CURRENT_VERSION = 2;
  
  public static final String MQZAC_STRUC_ID = "ZAC ";
  
  public static final int MQZAC_VERSION_1 = 1;
  
  public static final int MQZAC_CURRENT_VERSION = 1;
  
  public static final int MQZAC_LENGTH_1 = 84;
  
  public static final int MQZAC_CURRENT_LENGTH = 84;
  
  public static final int MQZAT_INITIAL_CONTEXT = 0;
  
  public static final int MQZAT_CHANGE_CONTEXT = 1;
  
  public static final String MQZAD_STRUC_ID = "ZAD ";
  
  public static final int MQZAD_VERSION_1 = 1;
  
  public static final int MQZAD_VERSION_2 = 2;
  
  public static final int MQZAD_CURRENT_VERSION = 2;
  
  public static final String MQZFP_STRUC_ID = "ZFP ";
  
  public static final int MQZFP_VERSION_1 = 1;
  
  public static final int MQZFP_CURRENT_VERSION = 1;
  
  public static final String MQZIC_STRUC_ID = "ZIC ";
  
  public static final int MQZIC_VERSION_1 = 1;
  
  public static final int MQZIC_CURRENT_VERSION = 1;
  
  public static final int MQZIC_LENGTH_1 = 84;
  
  public static final int MQZIC_CURRENT_LENGTH = 84;
  
  public static final int MQZID_INIT = 0;
  
  public static final int MQZID_TERM = 1;
  
  public static final int MQZID_INIT_AUTHORITY = 0;
  
  public static final int MQZID_TERM_AUTHORITY = 1;
  
  public static final int MQZID_CHECK_AUTHORITY = 2;
  
  public static final int MQZID_COPY_ALL_AUTHORITY = 3;
  
  public static final int MQZID_DELETE_AUTHORITY = 4;
  
  public static final int MQZID_SET_AUTHORITY = 5;
  
  public static final int MQZID_GET_AUTHORITY = 6;
  
  public static final int MQZID_GET_EXPLICIT_AUTHORITY = 7;
  
  public static final int MQZID_REFRESH_CACHE = 8;
  
  public static final int MQZID_ENUMERATE_AUTHORITY_DATA = 9;
  
  public static final int MQZID_AUTHENTICATE_USER = 10;
  
  public static final int MQZID_FREE_USER = 11;
  
  public static final int MQZID_INQUIRE = 12;
  
  public static final int MQZID_CHECK_PRIVILEGED = 13;
  
  public static final int MQZID_INIT_NAME = 0;
  
  public static final int MQZID_TERM_NAME = 1;
  
  public static final int MQZID_LOOKUP_NAME = 2;
  
  public static final int MQZID_INSERT_NAME = 3;
  
  public static final int MQZID_DELETE_NAME = 4;
  
  public static final int MQZID_INIT_USERID = 0;
  
  public static final int MQZID_TERM_USERID = 1;
  
  public static final int MQZID_FIND_USERID = 2;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\constants\CMQZC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */