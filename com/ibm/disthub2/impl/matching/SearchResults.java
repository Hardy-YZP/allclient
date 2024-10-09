package com.ibm.disthub2.impl.matching;

import com.ibm.disthub2.impl.util.FastVector;

public interface SearchResults {
  void addObjects(FastVector[] paramArrayOfFastVector);
  
  Object provideCacheable(String paramString) throws MatchingException;
  
  boolean acceptCacheable(Object paramObject);
  
  void reset();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\SearchResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */