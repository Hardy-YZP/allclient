package com.ibm.mq.exits;

import java.nio.ByteBuffer;

public interface WMQSecurityExit {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/WMQSecurityExit.java";
  
  ByteBuffer channelSecurityExit(MQCXP paramMQCXP, MQCD paramMQCD, ByteBuffer paramByteBuffer);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\exits\WMQSecurityExit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */