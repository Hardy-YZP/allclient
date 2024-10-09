package com.ibm.disthub2.impl.matching;

import com.ibm.disthub2.impl.matching.selector.Conjunction;
import com.ibm.disthub2.impl.matching.selector.EvalContext;

abstract class Matcher {
  private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
  
  abstract void put(String paramString, Conjunction paramConjunction, MatchTarget paramMatchTarget, InternTable paramInternTable, MatchTarget[] paramArrayOfMatchTarget) throws MatchingException;
  
  abstract void get(String paramString, EvalContext paramEvalContext, SearchResults paramSearchResults) throws MatchingException, BadMessageFormatMatchingException;
  
  abstract Matcher remove(String paramString, Conjunction paramConjunction, MatchTarget paramMatchTarget, InternTable paramInternTable, int paramInt) throws MatchingException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\Matcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */