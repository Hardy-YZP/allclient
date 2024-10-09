package com.ibm.disthub2.impl.matching;

public interface FormattedMessage {
  public static final String copyright = "Licensed Material - Property of IBM \nIBM MQSeries Integrator Version 2.0 - 5648-C63 \n(C) Copyright IBM Corp. 2000 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
  
  Object getFieldValue(String paramString) throws BadMessageFormatMatchingException, NoSuchFieldNameException;
  
  boolean isValidHeaderValue(String paramString) throws BadMessageFormatMatchingException, NoSuchFieldNameException;
  
  Object getHeaderValue(String paramString) throws BadMessageFormatMatchingException, NoSuchFieldNameException;
  
  Object getHeaderStringValue(String paramString) throws BadMessageFormatMatchingException, NoSuchFieldNameException;
  
  Object getHeaderNumberValue(String paramString) throws BadMessageFormatMatchingException, NoSuchFieldNameException;
  
  String getPropertiesTopic() throws BadMessageFormatMatchingException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\FormattedMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */