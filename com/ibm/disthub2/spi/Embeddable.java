package com.ibm.disthub2.spi;

import java.util.Enumeration;
import java.util.Properties;

public interface Embeddable {
  public static final int ST_STOPPED = 1;
  
  public static final int ST_RUNNING = 2;
  
  public static final int ST_SUSPENDED = 3;
  
  public static final int ST_NOTLOCKED = 4;
  
  int getState();
  
  void setParameter(String paramString1, String paramString2, boolean paramBoolean) throws IllegalParameterException;
  
  void setParameter(String paramString1, String paramString2) throws IllegalParameterException;
  
  void setParameter(Properties paramProperties, boolean paramBoolean) throws IllegalParameterException;
  
  void setParameter(Properties paramProperties) throws IllegalParameterException;
  
  String getParameter(String paramString) throws IllegalParameterException;
  
  Properties getParameter();
  
  void start() throws ServiceNotStoppedException, ServiceStartupException, ConfigurationNotLockedException;
  
  void stop() throws ServiceNotStartedException;
  
  void suspend() throws ServiceNotStartedException;
  
  void resume() throws ServiceNotSuspendedException;
  
  void registerService(String paramString, Service paramService) throws ConfigurationLockedException;
  
  Service getService(String paramString) throws ConfigurationNotLockedException;
  
  Enumeration getServiceNames() throws ConfigurationNotLockedException;
  
  void checkAndLockServices() throws IllegalServiceException;
  
  void terminate() throws ServiceNotStoppedException;
  
  EntryPoint getEntryPoint(String paramString) throws ConfigurationNotLockedException;
  
  int debugRegister(DebugHandle paramDebugHandle);
  
  boolean logIt(long paramLong);
  
  void log(long paramLong, String paramString, Object[] paramArrayOfObject);
  
  void debug(long paramLong, Object paramObject, String paramString, Object[] paramArrayOfObject);
  
  void serviceFailed(Service paramService, String paramString, Throwable paramThrowable);
  
  void fatalError(Throwable paramThrowable);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\Embeddable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */