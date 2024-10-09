package com.ibm.msg.client.wmq.compat.jms.internal;

import java.util.Hashtable;

public interface JMSStringResources {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSStringResources.java";
  
  public static final int MQJMS_EXCEPTION_INVALID_DESTINATION = 3;
  
  public static final int MQJMS_EXCEPTION_MESSAGE_EOF = 5;
  
  public static final int MQJMS_EXCEPTION_MESSAGE_FORMAT = 6;
  
  public static final int MQJMS_EXCEPTION_MESSAGE_NOT_READABLE = 7;
  
  public static final int MQJMS_EXCEPTION_MESSAGE_NOT_WRITABLE = 8;
  
  public static final int MQJMS_EXCEPTION_RESOURCE_ALLOCATION = 9;
  
  public static final int MQJMS_EXCEPTION_MSG_CREATE_ERROR = 1000;
  
  public static final int MQJMS_E_INTERNAL_ERROR = 1001;
  
  public static final int MQJMS_EXCEPTION_NULL_ELEMENT_NAME = 1002;
  
  public static final int MQJMS_EXCEPTION_NULL_PROPERTY_NAME = 1003;
  
  public static final int MQJMS_EXCEPTION_BUFFER_TOO_SMALL = 1004;
  
  public static final int MQJMS_EXCEPTION_UNEXPECTED_ERROR = 1005;
  
  public static final int MQJMS_E_INVALID_HEX_STRING = 1006;
  
  public static final int MQJMS_E_S390_DOUBLE_TOO_BIG = 1007;
  
  public static final int MQJMS_E_BAD_CCSID = 1008;
  
  public static final int MQJMS_E_INVALID_MAP_MESSAGE = 1009;
  
  public static final int MQJMS_E_INVALID_STREAM_MESSAGE = 1010;
  
  public static final int MQJMS_E_BYTE_TO_STRING = 1011;
  
  public static final int MQJMS_E_BAD_RFH2 = 1012;
  
  public static final int MQJMS_MSG_CLASS = 1013;
  
  public static final int MQJMS_E_BAD_MSG_CLASS = 1014;
  
  public static final int MQJMS_E_INVALID_SURROGATE = 1015;
  
  public static final int MQJMS_E_INVALID_ESCAPE = 1016;
  
  public static final int MQJMS_E_BAD_TYPE = 1017;
  
  public static final int MQJMS_E_UNSUPPORTED_TYPE = 1018;
  
  public static final int MQJMS_E_NO_SESSION = 1019;
  
  public static final int MQJMS_E_BAD_PROPERTY_NAME = 1020;
  
  public static final int MQJMS_E_BAD_ELEMENT_NAME = 1021;
  
  public static final int MQJMS_E_NO_UTF8 = 1022;
  
  public static final int MQJMS_E_SERIALISE_FAILED = 1023;
  
  public static final int MQJMS_E_DESERIALISE_FAILED = 1024;
  
  public static final int MQJMS_EXCEPTION_HAPPENED = 1025;
  
  public static final int MQJMS_CHARS_OMITTED = 1026;
  
  public static final int MQJMS_ENCODINGS = 1027;
  
  public static final int MQJMS_EXCEPTION_BAD_VALUE = 1028;
  
  String getErrorMessage(int paramInt);
  
  String getErrorMessage(int paramInt, Object paramObject);
  
  String getErrorMessage(int paramInt, Object paramObject1, Object paramObject2);
  
  Hashtable getJMS_IBM_names();
  
  String getMessage(int paramInt);
  
  String getMessage(int paramInt, Object paramObject);
  
  String getMessage(int paramInt, Object paramObject1, Object paramObject2);
  
  String getNativeKey(int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSStringResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */