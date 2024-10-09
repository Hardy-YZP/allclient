package com.ibm.mq.headers;

import java.io.IOException;
import java.util.Collection;

public interface MQHeaderFactory {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderFactory.java";
  
  MQHeader create(String paramString) throws InstantiationException;
  
  MQHeader decode(MQHeaderContext paramMQHeaderContext) throws Exception, MQDataException, IOException;
  
  Collection<?> getSupportedFormats();
  
  Collection<?> getSupportedTypes();
  
  public static interface Registry {
    MQHeaderFactory getFactoryForFormat(String param1String);
    
    MQHeaderFactory getFactoryForType(String param1String);
    
    void register(MQHeaderFactory param1MQHeaderFactory);
    
    Collection<?> getRegisteredFormats();
    
    Collection<?> getRegisteredTypes();
  }
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQHeaderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */