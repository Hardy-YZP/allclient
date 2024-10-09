package com.ibm.mq.ese.conv;

import com.ibm.mq.ese.core.AMBIHeader;

public interface CcsidConverter {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/conv/CcsidConverter.java";
  
  void convertHeader(AMBIHeader paramAMBIHeader) throws CcsidException;
  
  byte[] convert(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) throws CcsidException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\conv\CcsidConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */