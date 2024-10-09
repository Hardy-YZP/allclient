package com.ibm.disthub2.spi;

public interface QopLoaderEntryPoint extends EntryPoint {
  public static final byte SA_UNDEFINED = 0;
  
  public static final byte SA_NONE = 1;
  
  public static final byte SA_CINTEGRITY = 2;
  
  public static final byte SA_MINTEGRITY = 6;
  
  public static final byte SA_PRIVACY = 14;
  
  void addQop(String paramString, byte paramByte) throws IllegalStateException, IllegalParameterException;
  
  void changeQop(String paramString, byte paramByte) throws IllegalStateException, IllegalParameterException;
  
  void deleteQop(String paramString);
  
  void deleteAllQops();
  
  byte getRequiredQop(String paramString);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\QopLoaderEntryPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */