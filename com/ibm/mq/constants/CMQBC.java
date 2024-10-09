package com.ibm.mq.constants;

public interface CMQBC {
  public static final String copyright_notice = "Licensed Materials - Property of IBM (c) Copyright IBM Corp. 2008, 2023 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
  
  public static final String sccsid = "%Z% %W% %I% %E% %U%";
  
  public static final int MQCBO_NONE = 0;
  
  public static final int MQCBO_USER_BAG = 0;
  
  public static final int MQCBO_ADMIN_BAG = 1;
  
  public static final int MQCBO_COMMAND_BAG = 16;
  
  public static final int MQCBO_SYSTEM_BAG = 32;
  
  public static final int MQCBO_GROUP_BAG = 64;
  
  public static final int MQCBO_LIST_FORM_ALLOWED = 2;
  
  public static final int MQCBO_LIST_FORM_INHIBITED = 0;
  
  public static final int MQCBO_REORDER_AS_REQUIRED = 4;
  
  public static final int MQCBO_DO_NOT_REORDER = 0;
  
  public static final int MQCBO_CHECK_SELECTORS = 8;
  
  public static final int MQCBO_DO_NOT_CHECK_SELECTORS = 0;
  
  public static final int MQBL_NULL_TERMINATED = -1;
  
  public static final int MQITEM_INTEGER = 1;
  
  public static final int MQITEM_STRING = 2;
  
  public static final int MQITEM_BAG = 3;
  
  public static final int MQITEM_BYTE_STRING = 4;
  
  public static final int MQITEM_INTEGER_FILTER = 5;
  
  public static final int MQITEM_STRING_FILTER = 6;
  
  public static final int MQITEM_INTEGER64 = 7;
  
  public static final int MQITEM_BYTE_STRING_FILTER = 8;
  
  public static final int MQIT_INTEGER = 1;
  
  public static final int MQIT_STRING = 2;
  
  public static final int MQIT_BAG = 3;
  
  public static final int MQHA_FIRST = 4001;
  
  public static final int MQHA_BAG_HANDLE = 4001;
  
  public static final int MQHA_LAST_USED = 4001;
  
  public static final int MQHA_LAST = 6000;
  
  public static final int MQOA_FIRST = 1;
  
  public static final int MQOA_LAST = 9000;
  
  public static final int MQIASY_FIRST = -1;
  
  public static final int MQIASY_CODED_CHAR_SET_ID = -1;
  
  public static final int MQIASY_TYPE = -2;
  
  public static final int MQIASY_COMMAND = -3;
  
  public static final int MQIASY_MSG_SEQ_NUMBER = -4;
  
  public static final int MQIASY_CONTROL = -5;
  
  public static final int MQIASY_COMP_CODE = -6;
  
  public static final int MQIASY_REASON = -7;
  
  public static final int MQIASY_BAG_OPTIONS = -8;
  
  public static final int MQIASY_VERSION = -9;
  
  public static final int MQIASY_LAST_USED = -9;
  
  public static final int MQIASY_LAST = -2000;
  
  public static final int MQSEL_ANY_SELECTOR = -30001;
  
  public static final int MQSEL_ANY_USER_SELECTOR = -30002;
  
  public static final int MQSEL_ANY_SYSTEM_SELECTOR = -30003;
  
  public static final int MQSEL_ALL_SELECTORS = -30001;
  
  public static final int MQSEL_ALL_USER_SELECTORS = -30002;
  
  public static final int MQSEL_ALL_SYSTEM_SELECTORS = -30003;
  
  public static final int MQIND_NONE = -1;
  
  public static final int MQIND_ALL = -2;
  
  public static final int MQHB_UNUSABLE_HBAG = -1;
  
  public static final int MQHB_NONE = -2;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\constants\CMQBC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */