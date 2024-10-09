package com.ibm.disthub2.spi;

public interface PrincipalDirectory extends Service {
  public static final short CHALLENGE_UP = 99;
  
  public static final short CHALLENGE_UP_pxid = 67;
  
  public static final short CHALLENGE_MPR = 19779;
  
  public static final short CHALLENGE_SSL_UP = 21315;
  
  public static final short CHALLENGE_SSL_PURE = 21059;
  
  Principal getUser(String paramString);
  
  String getPassword(String paramString);
  
  boolean checkPassword(String paramString1, String paramString2);
  
  boolean checkProtocol(String paramString, short paramShort);
  
  boolean verifyCertificate(Certificate paramCertificate, String paramString);
  
  boolean inDirectory(String paramString);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\PrincipalDirectory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */