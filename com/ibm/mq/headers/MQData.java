package com.ibm.mq.headers;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface MQData {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQData.java";
  
  int read(DataInput paramDataInput) throws MQDataException, IOException;
  
  int read(DataInput paramDataInput, int paramInt1, int paramInt2) throws Exception, MQDataException, IOException;
  
  int write(DataOutput paramDataOutput) throws IOException;
  
  int write(DataOutput paramDataOutput, int paramInt1, int paramInt2) throws IOException;
  
  int size();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */