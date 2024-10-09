package com.ibm.disthub2.spi;

import java.util.Properties;

public interface Service {
  void deregister();
  
  void locked();
  
  void reset();
  
  void start() throws ServiceNotStoppedException, ServiceStartupException;
  
  void stop() throws ServiceNotStartedException;
  
  void suspend() throws ServiceNotStartedException;
  
  void resume() throws ServiceNotSuspendedException;
  
  void setParameter(String paramString1, String paramString2, boolean paramBoolean) throws IllegalParameterException;
  
  void setParameter(String paramString1, String paramString2) throws IllegalParameterException;
  
  void setParameter(Properties paramProperties, boolean paramBoolean) throws IllegalParameterException;
  
  void setParameter(Properties paramProperties) throws IllegalParameterException;
  
  String getParameter(String paramString) throws IllegalParameterException;
  
  Properties getParameter();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\Service.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */