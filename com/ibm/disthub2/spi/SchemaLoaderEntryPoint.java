package com.ibm.disthub2.spi;

public interface SchemaLoaderEntryPoint extends EntryPoint {
  public static final int GENERIC_SCHEMA = 0;
  
  public static final int PROPERTY_SCHEMA = 1;
  
  public static final int NONJMS_SCHEMA = 2;
  
  void addSchema(String paramString1, int paramInt, String paramString2) throws IllegalStateException, IllegalParameterException;
  
  void changeSchema(String paramString1, int paramInt, String paramString2) throws IllegalStateException, IllegalParameterException;
  
  void deleteSchema(String paramString);
  
  void deleteAllSchemas();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\SchemaLoaderEntryPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */