package com.ibm.disthub2.spi;

public interface Principal {
  boolean equals(Object paramObject);
  
  String toString();
  
  int hashCode();
  
  String getName();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\Principal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */