package com.ibm.disthub2.impl.matching.selector;

public interface EvalContextList {
  EvalContext index(int paramInt);
  
  EvalContext successor(EvalContext paramEvalContext);
  
  boolean isEmpty();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\EvalContextList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */