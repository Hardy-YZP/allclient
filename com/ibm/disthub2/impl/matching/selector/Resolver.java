package com.ibm.disthub2.impl.matching.selector;

public interface Resolver {
  Selector resolve(Identifier paramIdentifier, PositionAssigner paramPositionAssigner, Object paramObject);
  
  Object pushContext(Object paramObject, Identifier paramIdentifier);
  
  void restoreContext(Object paramObject);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Resolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */