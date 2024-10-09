package com.ibm.mq.headers;

import java.util.List;

public interface MQHeader extends MQData {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeader.java";
  
  Object getValue(String paramString);
  
  void setValue(String paramString, Object paramObject);
  
  String type();
  
  List<?> fields();
  
  public static interface Field {
    String getName();
    
    String getType();
    
    Object getValue();
    
    void setValue(Object param1Object);
  }
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */