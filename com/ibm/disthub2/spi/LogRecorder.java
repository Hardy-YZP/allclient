package com.ibm.disthub2.spi;

import java.io.OutputStream;

public interface LogRecorder extends Service, LogConstants {
  void log(long paramLong1, Thread paramThread, long paramLong2, String paramString, Object[] paramArrayOfObject);
  
  void debug(long paramLong1, Thread paramThread, long paramLong2, Object paramObject, String paramString, Object[] paramArrayOfObject);
  
  void dumpEvents(OutputStream paramOutputStream);
  
  boolean logIt(long paramLong);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\LogRecorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */