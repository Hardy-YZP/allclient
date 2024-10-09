package com.ibm.mq.ese.service;

import com.ibm.mq.ese.core.SecurityPolicy;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Pint;
import java.nio.ByteBuffer;

public interface PolicyService {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/PolicyService.java";
  
  public static final int MQESE_POLICY_VERSION_1 = 1;
  
  public static final int MQESE_ENC_ALG_NONE = 0;
  
  public static final int MQESE_ENC_ALG_RC2 = 1;
  
  public static final int MQESE_ENC_ALG_DES = 2;
  
  public static final int MQESE_ENC_ALG_3DES = 3;
  
  public static final int MQESE_ENC_ALG_AES128 = 4;
  
  public static final int MQESE_ENC_ALG_AES256 = 5;
  
  public static final int MQESE_SIGN_ALG_NONE = 0;
  
  public static final int MQESE_SIGN_ALG_MD5 = 1;
  
  public static final int MQESE_SIGN_ALG_SHA1 = 2;
  
  public static final int MQESE_SIGN_ALG_SHA224 = 3;
  
  public static final int MQESE_SIGN_ALG_SHA256 = 4;
  
  public static final int MQESE_SIGN_ALG_SHA384 = 5;
  
  public static final int MQESE_SIGN_ALG_SHA512 = 6;
  
  SecurityPolicy getPolicy(String paramString1, String paramString2, Hconn paramHconn, Pint paramPint1, Pint paramPint2) throws EseMQException;
  
  SecurityPolicy policyFromPcf(ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Hconn paramHconn) throws EseMQException;
  
  String getDefaultErrorQueueName();
  
  SecurityPolicy createNonePolicy(String paramString);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\PolicyService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */