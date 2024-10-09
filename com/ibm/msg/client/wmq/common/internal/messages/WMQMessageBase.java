/*      */ package com.ibm.msg.client.wmq.common.internal.messages;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.zrfp.Constants;
/*      */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class WMQMessageBase
/*      */   extends WMQMessageHeader
/*      */ {
/*      */   private static final long serialVersionUID = -3273665458738463106L;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageBase.java";
/*      */   
/*      */   static {
/*   59 */     if (Trace.isOn) {
/*   60 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageBase.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isNullMessage = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   79 */   private WMQRFH2FolderParser rfh2FolderParser = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static WMQMessage _parseMcdFolder(String s, String fbClass, String forcedMessageClass) throws JMSException {
/*   96 */     if (Trace.isOn) {
/*   97 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMcdFolder(String,String,String)", new Object[] { s, fbClass, forcedMessageClass });
/*      */     }
/*      */     
/*  100 */     WMQMessage newMessage = null;
/*      */ 
/*      */     
/*  103 */     if (forcedMessageClass == null) {
/*  104 */       boolean fastpath = false;
/*  105 */       String folder = s.trim();
/*  106 */       if (folder.equals("<mcd><Msd>jms_text</Msd></mcd>")) {
/*  107 */         newMessage = new WMQTextMessage();
/*  108 */         newMessage.isNullMessage = false;
/*  109 */         fastpath = true;
/*  110 */       } else if (folder.equals("<mcd><Msd>jms_bytes</Msd></mcd>")) {
/*  111 */         newMessage = new WMQBytesMessage();
/*  112 */         fastpath = true;
/*  113 */       } else if (folder.equals("<mcd><Msd>jms_none</Msd></mcd>")) {
/*  114 */         newMessage = new WMQNullMessage();
/*  115 */         fastpath = true;
/*  116 */       } else if (folder.equals("<mcd><Msd>jms_map</Msd></mcd>")) {
/*  117 */         newMessage = new WMQMapMessage();
/*  118 */         fastpath = true;
/*  119 */       } else if (folder.equals("<mcd><Msd>jms_stream</Msd></mcd>")) {
/*  120 */         newMessage = new WMQStreamMessage();
/*  121 */         fastpath = true;
/*  122 */       } else if (folder.equals("<mcd><Msd>jms_object</Msd></mcd>")) {
/*  123 */         newMessage = new WMQObjectMessage();
/*  124 */         fastpath = true;
/*  125 */       } else if (folder.equals("<mcd><Msd>jms_text</Msd><msgbody xsi:nil=\"true\"></msgbody></mcd>")) {
/*  126 */         newMessage = new WMQTextMessage();
/*  127 */         newMessage.isNullMessage = true;
/*  128 */         fastpath = true;
/*  129 */       } else if (folder
/*  130 */         .equals("<mcd><Msd>jms_text</Msd><msgbody xsi:nil='true'></msgbody></mcd>")) {
/*  131 */         newMessage = new WMQTextMessage();
/*  132 */         newMessage.isNullMessage = true;
/*  133 */         fastpath = true;
/*      */       } 
/*      */ 
/*      */       
/*  137 */       if (fastpath) {
/*  138 */         if (Trace.isOn) {
/*  139 */           Trace.data("com.ibm.msg.client.wmq.internal.messages.WMQMessageBase", "_parseMcdFolder(String,String,String)", "Computed message type on the fastpath", newMessage);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  144 */         if (Trace.isOn) {
/*  145 */           Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMcdFolder(String,String,String)", newMessage, 1);
/*      */         }
/*      */         
/*  148 */         return newMessage;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  157 */     String jmsType = null;
/*      */     
/*  159 */     String msDomain = null;
/*  160 */     String msSet = null;
/*  161 */     String msFormat = null;
/*      */ 
/*      */     
/*  164 */     boolean isNullMsgFlag = false;
/*      */     
/*  166 */     StringTokenizer strtok = new StringTokenizer(s, "<>");
/*  167 */     if (!strtok.nextToken().startsWith("mcd")) {
/*  168 */       throwBadRFH2Exception();
/*      */     }
/*  170 */     String name = strtok.nextToken();
/*      */     
/*  172 */     while (!name.equals("/mcd")) {
/*  173 */       String token; boolean assumingTypeEmpty = false;
/*      */       
/*  175 */       boolean msgbodyField = false;
/*      */       
/*  177 */       String value = strtok.nextToken();
/*      */       
/*  179 */       if (name.startsWith("msgbody")) {
/*  180 */         msgbodyField = true;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  185 */         if (name.indexOf("xsi:nil=\"true\"") != -1) {
/*  186 */           isNullMsgFlag = true;
/*      */         }
/*      */ 
/*      */         
/*  190 */         if (name.indexOf("xsi:nil='true'") != -1) {
/*  191 */           isNullMsgFlag = true;
/*      */         }
/*  193 */       } else if (name.startsWith("Msd")) {
/*      */         
/*  195 */         if (forcedMessageClass == null) {
/*  196 */           if (value.equals("jms_none")) {
/*  197 */             newMessage = new WMQNullMessage();
/*  198 */           } else if (value.equals("jms_text")) {
/*      */             
/*  200 */             newMessage = new WMQTextMessage();
/*  201 */             newMessage.isNullMessage = false;
/*  202 */           } else if (value.equals("jms_bytes")) {
/*  203 */             newMessage = new WMQBytesMessage();
/*  204 */           } else if (value.equals("jms_map")) {
/*  205 */             newMessage = new WMQMapMessage();
/*  206 */           } else if (value.equals("jms_stream")) {
/*  207 */             newMessage = new WMQStreamMessage();
/*  208 */           } else if (value.equals("jms_object")) {
/*  209 */             newMessage = new WMQObjectMessage();
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/*  216 */             if (value.equals("/Msd")) {
/*  217 */               msDomain = "";
/*  218 */               assumingTypeEmpty = true;
/*      */             } else {
/*  220 */               msDomain = value;
/*      */             } 
/*      */             
/*  223 */             if (fbClass.equals("jms_text")) {
/*  224 */               newMessage = new WMQTextMessage();
/*  225 */               newMessage.isNullMessage = false;
/*  226 */             } else if (fbClass.equals("jms_bytes")) {
/*  227 */               newMessage = new WMQBytesMessage();
/*      */             } else {
/*  229 */               throwBadRFH2Exception();
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  238 */           if (value.equals("/Msd")) {
/*  239 */             assumingTypeEmpty = true;
/*      */           }
/*      */           
/*  242 */           if (forcedMessageClass.equals("jms_bytes")) {
/*  243 */             newMessage = new WMQBytesMessage();
/*      */           
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*  249 */       else if (name.startsWith("Type")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  256 */         if (value.equals("/Type")) {
/*  257 */           jmsType = "";
/*  258 */           assumingTypeEmpty = true;
/*      */         } else {
/*  260 */           jmsType = value;
/*      */         }
/*      */       
/*      */       }
/*  264 */       else if (name.startsWith("Set")) {
/*      */ 
/*      */         
/*  267 */         if (value.equals("/Set")) {
/*  268 */           msSet = "";
/*  269 */           assumingTypeEmpty = true;
/*      */         } else {
/*  271 */           msSet = value;
/*      */         }
/*      */       
/*  274 */       } else if (name.startsWith("Fmt")) {
/*      */ 
/*      */         
/*  277 */         if (value.equals("/Fmt")) {
/*  278 */           msFormat = "";
/*  279 */           assumingTypeEmpty = true;
/*      */         } else {
/*  281 */           msFormat = value;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  286 */       if (msgbodyField) {
/*      */ 
/*      */ 
/*      */         
/*  290 */         if (name.charAt(name.length() - 1) == '/') {
/*  291 */           assumingTypeEmpty = true;
/*      */           
/*  293 */           token = value;
/*  294 */         } else if (value.equals("/msgbody")) {
/*      */           
/*  296 */           assumingTypeEmpty = true;
/*  297 */           token = strtok.nextToken();
/*      */         } else {
/*  299 */           token = strtok.nextToken();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  304 */         token = strtok.nextToken();
/*      */       } 
/*      */       
/*  307 */       if (assumingTypeEmpty) {
/*  308 */         if (token.equals("/Msd") || token.equals("/Type") || token
/*  309 */           .equals("/Set") || token.equals("/Fmt")) {
/*      */           
/*  311 */           if (token.equals("/Msd")) {
/*  312 */             msDomain = value;
/*  313 */           } else if (token.equals("/Type")) {
/*  314 */             jmsType = value;
/*  315 */           } else if (token.equals("/Set")) {
/*  316 */             msSet = value;
/*  317 */           } else if (token.equals("/Fmt")) {
/*  318 */             msFormat = value;
/*      */           } 
/*  320 */           name = strtok.nextToken(); continue;
/*      */         } 
/*  322 */         name = token;
/*      */         continue;
/*      */       } 
/*  325 */       if (token.charAt(0) != '/') {
/*  326 */         throwBadRFH2Exception();
/*      */       }
/*  328 */       name = strtok.nextToken();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  334 */     if (newMessage == null)
/*      */     {
/*  336 */       if (forcedMessageClass != null && forcedMessageClass
/*  337 */         .equals("jms_bytes")) {
/*      */         
/*  339 */         newMessage = new WMQBytesMessage();
/*  340 */       } else if (fbClass != null) {
/*      */         
/*  342 */         if (fbClass.equals("jms_text")) {
/*  343 */           newMessage = new WMQTextMessage();
/*  344 */           newMessage.isNullMessage = false;
/*  345 */         } else if (fbClass.equals("jms_bytes")) {
/*  346 */           newMessage = new WMQBytesMessage();
/*      */         } else {
/*      */           
/*  349 */           if (Trace.isOn) {
/*  350 */             Trace.traceData("com.ibm.msg.client.wmq.internal.messages.WMQMessageBase", "_parseMcdFolder(String,String,String)", "No Msd, and fbClass not defined as text or bytes type. fbClass is ", fbClass);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  356 */           throwBadRFH2Exception();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  361 */         if (Trace.isOn) {
/*  362 */           Trace.traceData("com.ibm.msg.client.wmq.internal.messages.WMQMessageBase", "_parseMcdFolder(String,String,String)", "Unknown message type: no Msd, forcedMessageClass or fbClass declared", null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  368 */         throwBadRFH2Exception();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  374 */     if (msDomain != null) {
/*  375 */       StringBuffer sb = new StringBuffer();
/*  376 */       sb.append("mcd://");
/*  377 */       sb.append(msDomain);
/*  378 */       if (msSet != null) {
/*  379 */         sb.append('/');
/*  380 */         sb.append(msSet);
/*      */       } 
/*  382 */       if (jmsType != null) {
/*  383 */         if (msSet == null) {
/*  384 */           sb.append('/');
/*      */         }
/*  386 */         sb.append('/');
/*  387 */         sb.append(jmsType);
/*      */       } 
/*  389 */       if (msFormat != null) {
/*  390 */         sb.append("?format=");
/*  391 */         sb.append(msFormat);
/*      */       } 
/*  393 */       newMessage.setJMSType(sb.toString());
/*      */     
/*      */     }
/*  396 */     else if (jmsType != null) {
/*  397 */       newMessage.setJMSType(
/*  398 */           (String)WMQMessageUtils.deformatTypedElement(10, jmsType));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  403 */     newMessage.isNullMessage = isNullMsgFlag;
/*      */     
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMcdFolder(String,String,String)", newMessage, 2);
/*      */     }
/*      */     
/*  409 */     return newMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static WMQMessage setTripletMcd(Triplet mcdTriplet, String fbClass, String forcedMessageClass) throws JMSException {
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMcd(Triplet,String,String)", new Object[] { mcdTriplet, fbClass, forcedMessageClass });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  434 */     int size = mcdTriplet.size();
/*  435 */     if (!"mcd".equals(mcdTriplet.getLabel()) || size == 0) {
/*  436 */       throwMsgCreateErrorException();
/*      */     }
/*      */     
/*  439 */     WMQMessage newMessage = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  444 */     if (forcedMessageClass == null && mcdTriplet.getKey(0).equals("Msd")) {
/*  445 */       boolean fastpath = false;
/*      */       
/*  447 */       String str = (String)mcdTriplet.getValue(0);
/*  448 */       if (size == 1) {
/*  449 */         if (str.equals("jms_text")) {
/*  450 */           newMessage = new WMQTextMessage();
/*  451 */           newMessage.isNullMessage = false;
/*  452 */           fastpath = true;
/*  453 */         } else if (str.equals("jms_bytes")) {
/*  454 */           newMessage = new WMQBytesMessage();
/*  455 */           fastpath = true;
/*  456 */         } else if (str.equals("jms_none")) {
/*  457 */           newMessage = new WMQNullMessage();
/*  458 */           fastpath = true;
/*  459 */         } else if (str.equals("jms_map")) {
/*  460 */           newMessage = new WMQMapMessage();
/*  461 */           fastpath = true;
/*  462 */         } else if (str.equals("jms_stream")) {
/*  463 */           newMessage = new WMQStreamMessage();
/*  464 */           fastpath = true;
/*  465 */         } else if (str.equals("jms_object")) {
/*  466 */           newMessage = new WMQObjectMessage();
/*  467 */           fastpath = true;
/*      */         } 
/*  469 */       } else if (size == 2) {
/*  470 */         String secondKey = mcdTriplet.getKey(1);
/*  471 */         int secondType = mcdTriplet.getType(1);
/*  472 */         if (str.equals("jms_text") && secondKey
/*  473 */           .equals("msgbody") && secondType == 9) {
/*      */           
/*  475 */           newMessage = new WMQTextMessage();
/*  476 */           newMessage.isNullMessage = true;
/*  477 */           fastpath = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  482 */       if (fastpath) {
/*  483 */         if (Trace.isOn) {
/*  484 */           Trace.data("com.ibm.msg.client.wmq.internal.messages.WMQMessageBase", "setTripletMcd(Triplet,String,String)", "Computed message type on the fastpath", newMessage);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  489 */         if (Trace.isOn) {
/*  490 */           Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMcd(Triplet,String,String)", newMessage, 1);
/*      */         }
/*      */         
/*  493 */         return newMessage;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  502 */     String msdValue = null;
/*  503 */     String setValue = null;
/*  504 */     String typeValue = null;
/*  505 */     String fmtValue = null;
/*  506 */     boolean nullMsgBody = false;
/*      */ 
/*      */     
/*  509 */     for (int i = 0; i < size; i++) {
/*  510 */       String key = mcdTriplet.getKey(i);
/*  511 */       int type = mcdTriplet.getType(i);
/*      */       
/*  513 */       if (type == 0) {
/*  514 */         String value = (String)mcdTriplet.getValue(i);
/*  515 */         if (key == null) {
/*  516 */           throwInvalidTripletException(mcdTriplet);
/*      */         }
/*  518 */         char index = key.charAt(0);
/*  519 */         switch (index) {
/*      */           case 'M':
/*  521 */             if (key.equals("Msd")) {
/*  522 */               msdValue = value;
/*      */             }
/*      */             break;
/*      */           case 'S':
/*  526 */             if (key.equals("Set")) {
/*  527 */               setValue = value;
/*      */             }
/*      */             break;
/*      */           case 'T':
/*  531 */             if (key.equals("Type")) {
/*  532 */               typeValue = value;
/*      */             }
/*      */             break;
/*      */           case 'F':
/*  536 */             if (key.equals("Fmt")) {
/*  537 */               fmtValue = value;
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*  544 */       } else if (type == 9) {
/*  545 */         if (key == null) {
/*  546 */           throwInvalidTripletException(mcdTriplet);
/*      */         }
/*  548 */         if (key.equals("msgbody")) {
/*  549 */           nullMsgBody = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  555 */     String domainValue = null;
/*  556 */     if (msdValue != null) {
/*  557 */       if (forcedMessageClass == null) {
/*  558 */         if (msdValue.equals("jms_text")) {
/*  559 */           newMessage = new WMQTextMessage();
/*  560 */         } else if (msdValue.equals("jms_bytes")) {
/*  561 */           newMessage = new WMQBytesMessage();
/*  562 */         } else if (msdValue.equals("jms_none")) {
/*  563 */           newMessage = new WMQNullMessage();
/*  564 */         } else if (msdValue.equals("jms_map")) {
/*  565 */           newMessage = new WMQMapMessage();
/*  566 */         } else if (msdValue.equals("jms_stream")) {
/*  567 */           newMessage = new WMQStreamMessage();
/*  568 */         } else if (msdValue.equals("jms_object")) {
/*  569 */           newMessage = new WMQObjectMessage();
/*      */         } else {
/*  571 */           domainValue = msdValue;
/*  572 */           if (fbClass.equals("jms_text")) {
/*  573 */             newMessage = new WMQTextMessage();
/*  574 */           } else if (fbClass.equals("jms_bytes")) {
/*  575 */             newMessage = new WMQBytesMessage();
/*      */           } else {
/*  577 */             throwInvalidTripletException(mcdTriplet);
/*      */           }
/*      */         
/*      */         } 
/*  581 */       } else if (forcedMessageClass.equals("jms_bytes")) {
/*  582 */         newMessage = new WMQBytesMessage();
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  591 */     else if (forcedMessageClass != null && forcedMessageClass
/*  592 */       .equals("jms_bytes")) {
/*      */       
/*  594 */       newMessage = new WMQBytesMessage();
/*  595 */     } else if (fbClass != null) {
/*      */       
/*  597 */       if (fbClass.equals("jms_text")) {
/*  598 */         newMessage = new WMQTextMessage();
/*  599 */         newMessage.isNullMessage = false;
/*  600 */       } else if (fbClass.equals("jms_bytes")) {
/*  601 */         newMessage = new WMQBytesMessage();
/*      */       } else {
/*      */         
/*  604 */         if (Trace.isOn) {
/*  605 */           Trace.traceData("com.ibm.msg.client.wmq.internal.messages.WMQMessageBase", "setTripletMcd(Triplet,String,String)", "No Msd, and fbClass not defined as text or bytes type. fbClass is ", fbClass);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  611 */         throwInvalidTripletException(mcdTriplet);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  616 */       if (Trace.isOn) {
/*  617 */         Trace.traceData("com.ibm.msg.client.wmq.internal.messages.WMQMessageBase", "setTripletMcd(Triplet,String,String)", "Unknown message type: no Msd, forcedMessageClass or fbClass declared", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  623 */       throwInvalidTripletException(mcdTriplet);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  631 */     if (domainValue != null) {
/*  632 */       StringBuffer sb = new StringBuffer();
/*  633 */       sb.append("mcd://");
/*  634 */       sb.append(domainValue);
/*  635 */       if (setValue != null) {
/*  636 */         sb.append('/');
/*  637 */         sb.append(setValue);
/*      */       } 
/*  639 */       if (typeValue != null) {
/*  640 */         if (setValue == null) {
/*  641 */           sb.append('/');
/*      */         }
/*  643 */         sb.append('/');
/*  644 */         sb.append(typeValue);
/*      */       } 
/*  646 */       if (fmtValue != null) {
/*  647 */         sb.append("?format=");
/*  648 */         sb.append(fmtValue);
/*      */       } 
/*  650 */       newMessage.setJMSType(sb.toString());
/*      */     
/*      */     }
/*  653 */     else if (typeValue != null) {
/*  654 */       newMessage.setJMSType(typeValue);
/*      */     } 
/*      */ 
/*      */     
/*  658 */     newMessage.isNullMessage = nullMsgBody;
/*      */     
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMcd(Triplet,String,String)", newMessage, 2);
/*      */     }
/*      */     
/*  664 */     return newMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String _getJmsFolder(boolean persistenceFromMD) throws JMSException {
/*  681 */     if (Trace.isOn)
/*  682 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getJmsFolder(boolean)", new Object[] {
/*  683 */             Boolean.valueOf(persistenceFromMD)
/*      */           }); 
/*  685 */     StringBuilder rfhstr = new StringBuilder(40);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  703 */     rfhstr.append("<jms>");
/*      */ 
/*      */     
/*  706 */     String destinationAsString = getJMSDestinationAsString();
/*  707 */     if (destinationAsString == null) {
/*  708 */       HashMap<String, Object> info = new HashMap<>();
/*  709 */       info.put("info", "jmsDestinationAsString is null");
/*  710 */       info.put("persistenceFromMD", Boolean.valueOf(persistenceFromMD));
/*  711 */       Trace.ffst(this, "_getJmsFolder(boolean)", "XN003001", info, JMSException.class);
/*      */     } 
/*      */     
/*  714 */     WMQMessageUtils.formatElement("Dst", destinationAsString, rfhstr);
/*      */ 
/*      */     
/*  717 */     String rToString = getJMSReplyToAsString();
/*  718 */     if (rToString != null) {
/*  719 */       WMQMessageUtils.formatElement("Rto", rToString, rfhstr);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  730 */     Long timestamp = getJMSTimestamp();
/*      */     
/*  732 */     if (timestamp != null && timestamp.longValue() >= 0L) {
/*  733 */       rfhstr.append("<Tms>");
/*  734 */       rfhstr.append(String.valueOf(timestamp));
/*  735 */       rfhstr.append("</Tms>");
/*      */     } 
/*      */ 
/*      */     
/*  739 */     Long expiration = getJMSExpiration();
/*      */     
/*  741 */     if (expiration != null && expiration.longValue() != 0L) {
/*      */       
/*  743 */       rfhstr.append("<Exp>");
/*  744 */       rfhstr.append(String.valueOf(expiration));
/*  745 */       rfhstr.append("</Exp>");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  755 */     String correlationId = getJMSCorrelationID();
/*  756 */     if (correlationId != null) {
/*  757 */       WMQMessageUtils.formatElement("Cid", correlationId, rfhstr);
/*      */     }
/*      */ 
/*      */     
/*  761 */     Integer priority = getJMSPriority();
/*  762 */     assert priority != null;
/*  763 */     if (priority.intValue() != 4) {
/*  764 */       rfhstr.append("<Pri>");
/*  765 */       rfhstr.append(priority);
/*  766 */       rfhstr.append("</Pri>");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  779 */     Integer deliveryMode = getJMSDeliveryMode();
/*  780 */     assert deliveryMode != null;
/*  781 */     if ((!persistenceFromMD && !this.hideDeliveryMode) || (persistenceFromMD == true && deliveryMode
/*  782 */       .intValue() != 2)) {
/*  783 */       rfhstr.append("<Dlv>");
/*  784 */       rfhstr.append(deliveryMode);
/*  785 */       rfhstr.append("</Dlv>");
/*      */     } 
/*      */ 
/*      */     
/*  789 */     if (propertyExists("JMS_IBM_ConnectionID")) {
/*      */ 
/*      */       
/*  792 */       String connectionID = getStringProperty("JMS_IBM_ConnectionID");
/*  793 */       if (connectionID != null) {
/*      */ 
/*      */         
/*  796 */         rfhstr.append("<Uci dt='");
/*  797 */         rfhstr.append("bin.hex");
/*  798 */         rfhstr.append("'>");
/*  799 */         rfhstr.append(connectionID);
/*  800 */         rfhstr.append("</Uci>");
/*      */       } else {
/*      */         
/*  803 */         HashMap<String, Object> info = new HashMap<>();
/*  804 */         info.put("connectionID", null);
/*  805 */         Trace.ffst(this, "_getJmsFolder", "XM009001", info, JMSException.class);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  814 */     Object jmsxGroupId = getObjectProperty("JMSXGroupID");
/*  815 */     if (jmsxGroupId instanceof String) {
/*  816 */       WMQMessageUtils.formatElement("Gid", jmsxGroupId, rfhstr);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  822 */     if (propertyExists("JMSXGroupSeq")) {
/*      */       try {
/*  824 */         int jmsxGroupSeq = getIntProperty("JMSXGroupSeq");
/*  825 */         WMQMessageUtils.formatElement("Seq", "", 
/*  826 */             Integer.toString(jmsxGroupSeq), rfhstr);
/*      */       
/*      */       }
/*  829 */       catch (NumberFormatException nfe) {
/*  830 */         if (Trace.isOn) {
/*  831 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getJmsFolder(boolean)", nfe);
/*      */         }
/*      */ 
/*      */         
/*  835 */         if (Trace.isOn) {
/*  836 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getJmsFolder(boolean)", nfe);
/*      */ 
/*      */ 
/*      */           
/*  840 */           String groupSeqNo = getStringProperty("JMSXGroupSeq");
/*  841 */           Trace.traceData(this, "_getJmsFolder(boolean)", "Ignoring invalid value for JMSXGroupSeq property.", groupSeqNo);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  850 */     rfhstr.append("</jms>");
/*      */     
/*  852 */     String jmsFolder = rfhstr.toString();
/*      */     
/*  854 */     if (Trace.isOn) {
/*  855 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getJmsFolder(boolean)", jmsFolder);
/*      */     }
/*      */     
/*  858 */     return jmsFolder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Triplet getTripletJms(boolean persistenceFromMD) throws JMSException {
/*  869 */     if (Trace.isOn) {
/*  870 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletJms(boolean)", new Object[] {
/*  871 */             Boolean.valueOf(persistenceFromMD)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  889 */     Triplet jmsTriplet = new Triplet("jms", 10);
/*      */ 
/*      */     
/*  892 */     String destinationAsString = getJMSDestinationAsString();
/*  893 */     if (destinationAsString == null) {
/*  894 */       HashMap<String, Object> info = new HashMap<>();
/*  895 */       info.put("info", "jmsDestinationAsString is null");
/*  896 */       info.put("persistenceFromMD", Boolean.valueOf(persistenceFromMD));
/*  897 */       Trace.ffst(this, "getTripletJms(boolean)", "XM009002", info, JMSException.class);
/*      */     } 
/*      */     
/*  900 */     jmsTriplet.add("Dst", destinationAsString);
/*      */ 
/*      */     
/*  903 */     String rToString = getJMSReplyToAsString();
/*  904 */     if (rToString != null) {
/*  905 */       jmsTriplet.add("Rto", rToString);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     Long timestamp = getJMSTimestamp();
/*      */     
/*  921 */     if (timestamp != null && timestamp.longValue() >= 0L)
/*      */     {
/*  923 */       jmsTriplet.add("Tms", String.valueOf(timestamp));
/*      */     }
/*      */ 
/*      */     
/*  927 */     Long expiration = getJMSExpiration();
/*  928 */     if (expiration != null && expiration.longValue() != 0L)
/*      */     {
/*      */       
/*  931 */       jmsTriplet.add("Exp", String.valueOf(expiration));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  941 */     String correlationId = getJMSCorrelationID();
/*  942 */     if (correlationId != null && !correlationId.startsWith("ID:")) {
/*  943 */       jmsTriplet.add("Cid", correlationId);
/*      */     }
/*      */ 
/*      */     
/*  947 */     Integer priority = getJMSPriority();
/*  948 */     assert priority != null;
/*  949 */     if (priority.intValue() != 4)
/*      */     {
/*  951 */       jmsTriplet.add("Pri", String.valueOf(priority));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  964 */     Integer deliveryMode = getJMSDeliveryMode();
/*  965 */     assert deliveryMode != null;
/*  966 */     if ((!persistenceFromMD && !this.hideDeliveryMode) || (persistenceFromMD == true && deliveryMode
/*  967 */       .intValue() != 2))
/*      */     {
/*  969 */       jmsTriplet.add("Dlv", String.valueOf(deliveryMode));
/*      */     }
/*      */ 
/*      */     
/*  973 */     if (propertyExists("JMS_IBM_ConnectionID")) {
/*      */ 
/*      */       
/*  976 */       String connectionID = getStringProperty("JMS_IBM_ConnectionID");
/*  977 */       if (connectionID != null) {
/*      */ 
/*      */         
/*  980 */         byte[] connectionIDAsBytes = WMQUtils.hexToBin(connectionID, 0);
/*  981 */         jmsTriplet.add("Uci", connectionIDAsBytes, Constants.TYPE_BYTE_STRING);
/*      */       }
/*      */       else {
/*      */         
/*  985 */         HashMap<String, Object> info = new HashMap<>();
/*  986 */         info.put("connectionID", null);
/*  987 */         Trace.ffst(this, "getTripletJms", "XM009003", info, JMSException.class);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  996 */     Object jmsxGroupId = getObjectProperty("JMSXGroupID");
/*  997 */     if (jmsxGroupId instanceof String) {
/*  998 */       jmsTriplet.add("Gid", (String)jmsxGroupId);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1008 */     if (propertyExists("JMSXGroupSeq")) {
/* 1009 */       int jmsxGroupSeq = getIntProperty("JMSXGroupSeq");
/*      */       
/* 1011 */       jmsTriplet.add("Seq", String.valueOf(jmsxGroupSeq));
/*      */     } 
/*      */     
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletJms(boolean)", jmsTriplet);
/*      */     }
/*      */     
/* 1018 */     return jmsTriplet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String _getMcdFolder() throws JMSException {
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getMcdFolder()");
/*      */     }
/*      */     
/* 1037 */     String jmsType = getJMSType();
/*      */ 
/*      */     
/* 1040 */     String quickMcdFolder = null;
/* 1041 */     if (this.jmsType_Domain == null && jmsType == null) {
/* 1042 */       if (this.messageClass.equals("jms_text") && !this.isNullMessage) {
/*      */         
/* 1044 */         quickMcdFolder = "<mcd><Msd>jms_text</Msd></mcd>";
/* 1045 */       } else if (this.messageClass.equals("jms_text") && this.isNullMessage == true) {
/*      */         
/* 1047 */         quickMcdFolder = "<mcd><Msd>jms_text</Msd><msgbody xsi:nil=\"true\"></msgbody></mcd>";
/* 1048 */       } else if (this.messageClass.equals("jms_bytes")) {
/* 1049 */         quickMcdFolder = "<mcd><Msd>jms_bytes</Msd></mcd>";
/* 1050 */       } else if (this.messageClass.equals("jms_none")) {
/* 1051 */         quickMcdFolder = "<mcd><Msd>jms_none</Msd></mcd>";
/* 1052 */       } else if (this.messageClass.equals("jms_object")) {
/* 1053 */         quickMcdFolder = "<mcd><Msd>jms_object</Msd></mcd>";
/* 1054 */       } else if (this.messageClass.equals("jms_map")) {
/* 1055 */         quickMcdFolder = "<mcd><Msd>jms_map</Msd></mcd>";
/* 1056 */       } else if (this.messageClass.equals("jms_stream")) {
/* 1057 */         quickMcdFolder = "<mcd><Msd>jms_stream</Msd></mcd>";
/*      */       } else {
/*      */         
/* 1060 */         HashMap<String, Object> info = new HashMap<>();
/* 1061 */         info.put("message class", this.messageClass);
/* 1062 */         info.put("is null message", Boolean.valueOf(this.isNullMessage));
/* 1063 */         Trace.ffst(this, "_getMcdFolder", "XN003002", info, JMSException.class);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1069 */     if (quickMcdFolder != null) {
/* 1070 */       if (Trace.isOn) {
/* 1071 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getMcdFolder()", quickMcdFolder, 1);
/*      */       }
/*      */       
/* 1074 */       return quickMcdFolder;
/*      */     } 
/*      */     
/* 1077 */     StringBuilder rfhstr = new StringBuilder(40);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1082 */     rfhstr.append("<mcd><Msd>");
/*      */ 
/*      */ 
/*      */     
/* 1086 */     if (this.jmsType_Domain != null) {
/* 1087 */       rfhstr.append(this.jmsType_Domain);
/* 1088 */       rfhstr.append("</Msd>");
/*      */       
/* 1090 */       if (this.jmsType_Set != null && this.jmsType_Set.length() > 0) {
/* 1091 */         WMQMessageUtils.formatElement("Set", this.jmsType_Set, rfhstr);
/*      */       }
/*      */       
/* 1094 */       if (this.jmsType_Type != null && this.jmsType_Type.length() > 0) {
/* 1095 */         WMQMessageUtils.formatElement("Type", this.jmsType_Type, rfhstr);
/*      */       }
/*      */       
/* 1098 */       if (this.jmsType_Format != null && this.jmsType_Format.length() > 0) {
/* 1099 */         WMQMessageUtils.formatElement("Fmt", this.jmsType_Format, rfhstr);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1109 */       rfhstr.append(this.messageClass);
/* 1110 */       rfhstr.append("</Msd>");
/*      */ 
/*      */       
/* 1113 */       if (jmsType != null) {
/* 1114 */         WMQMessageUtils.formatElement("Type", jmsType, rfhstr);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1119 */     if (this.messageClass.equals("jms_text") && this.isNullMessage == true)
/*      */     {
/* 1121 */       rfhstr.append("<msgbody xsi:nil=\"true\"></msgbody>");
/*      */     }
/*      */ 
/*      */     
/* 1125 */     rfhstr.append("</mcd>");
/*      */     
/* 1127 */     String mcdFolder = rfhstr.toString();
/*      */     
/* 1129 */     if (Trace.isOn) {
/* 1130 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getMcdFolder()", mcdFolder, 2);
/*      */     }
/*      */     
/* 1133 */     return mcdFolder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Triplet getTripletMcd() throws JMSException {
/* 1143 */     if (Trace.isOn) {
/* 1144 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletMcd()");
/*      */     }
/*      */     
/* 1147 */     String jmsType = getJMSType();
/*      */ 
/*      */     
/* 1150 */     Triplet quickMcdTriplet = null;
/* 1151 */     if (this.jmsType_Domain == null && jmsType == null) {
/* 1152 */       if (this.messageClass.equals("jms_text") && !this.isNullMessage) {
/*      */         
/* 1154 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_TEXT;
/* 1155 */       } else if (this.messageClass.equals("jms_text") && this.isNullMessage == true) {
/*      */         
/* 1157 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_TEXT_NULL_MSG;
/* 1158 */       } else if (this.messageClass.equals("jms_bytes")) {
/* 1159 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_BYTES;
/* 1160 */       } else if (this.messageClass.equals("jms_none")) {
/* 1161 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_NONE;
/* 1162 */       } else if (this.messageClass.equals("jms_object")) {
/* 1163 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_OBJECT;
/* 1164 */       } else if (this.messageClass.equals("jms_map")) {
/* 1165 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_MAP;
/* 1166 */       } else if (this.messageClass.equals("jms_stream")) {
/* 1167 */         quickMcdTriplet = WMQMessageUtils.TRIPLET_MCD_CLASS_STREAM;
/*      */       } else {
/*      */         
/* 1170 */         HashMap<String, Object> info = new HashMap<>();
/* 1171 */         info.put("message class", this.messageClass);
/* 1172 */         info.put("is null message", Boolean.valueOf(this.isNullMessage));
/* 1173 */         Trace.ffst(this, "getTripletMcd", "XM009004", info, JMSException.class);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1179 */     if (quickMcdTriplet != null) {
/* 1180 */       if (Trace.isOn) {
/* 1181 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletMcd()", quickMcdTriplet, 1);
/*      */       }
/*      */       
/* 1184 */       return quickMcdTriplet;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1189 */     Triplet mcdTriplet = new Triplet("mcd", 5);
/*      */ 
/*      */ 
/*      */     
/* 1193 */     if (this.jmsType_Domain != null) {
/* 1194 */       mcdTriplet.add("Msd", this.jmsType_Domain);
/*      */       
/* 1196 */       if (this.jmsType_Set != null && this.jmsType_Set.length() > 0) {
/* 1197 */         mcdTriplet.add("Set", this.jmsType_Set);
/*      */       }
/*      */       
/* 1200 */       if (this.jmsType_Type != null && this.jmsType_Type.length() > 0) {
/* 1201 */         mcdTriplet.add("Type", this.jmsType_Type);
/*      */       }
/*      */       
/* 1204 */       if (this.jmsType_Format != null && this.jmsType_Format.length() > 0) {
/* 1205 */         mcdTriplet.add("Fmt", this.jmsType_Format);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1215 */       mcdTriplet.add("Msd", this.messageClass);
/*      */ 
/*      */       
/* 1218 */       if (jmsType != null) {
/* 1219 */         mcdTriplet.add("Type", jmsType);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1224 */     if (this.messageClass.equals("jms_text") && this.isNullMessage == true)
/*      */     {
/* 1226 */       mcdTriplet.add("msgbody", null, Constants.TYPE_NULL);
/*      */     }
/*      */     
/* 1229 */     if (Trace.isOn) {
/* 1230 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletMcd()", mcdTriplet, 2);
/*      */     }
/*      */     
/* 1233 */     return mcdTriplet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String _getMQExtFolder() throws JMSException {
/* 1248 */     if (Trace.isOn) {
/* 1249 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getMQExtFolder()");
/*      */     }
/*      */     
/* 1252 */     StringBuilder rfhstr = null;
/*      */ 
/*      */     
/* 1255 */     String armCorrelator = getStringProperty("JMS_IBM_ArmCorrelator");
/* 1256 */     String wrmCorrelator = getStringProperty("JMS_IBM_RMCorrelator");
/* 1257 */     long deliveryDelay = getJMSDeliveryDelay();
/* 1258 */     long deliveryTime = getJMSDeliveryTime();
/*      */     
/* 1260 */     if (armCorrelator != null || wrmCorrelator != null || deliveryDelay > 0L) {
/* 1261 */       rfhstr = new StringBuilder(40);
/* 1262 */       rfhstr.append("<mqext>");
/* 1263 */       if (armCorrelator != null) {
/* 1264 */         WMQMessageUtils.formatElement("Arm", armCorrelator, rfhstr);
/*      */       }
/* 1266 */       if (wrmCorrelator != null) {
/* 1267 */         WMQMessageUtils.formatElement("Wrm", wrmCorrelator, rfhstr);
/*      */       }
/* 1269 */       if (deliveryDelay > 0L) {
/* 1270 */         rfhstr.append(String.format("<Dly>%d</Dly>", new Object[] { Long.valueOf(deliveryDelay) }));
/* 1271 */         rfhstr.append(String.format("<Dtm>%d</Dtm>", new Object[] { Long.valueOf(deliveryTime) }));
/*      */       } 
/* 1273 */       rfhstr.append("</mqext>");
/*      */     } 
/*      */     
/* 1276 */     String mqextFolder = (rfhstr != null) ? rfhstr.toString() : null;
/*      */     
/* 1278 */     if (Trace.isOn) {
/* 1279 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getMQExtFolder()", mqextFolder);
/*      */     }
/*      */     
/* 1282 */     return mqextFolder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Triplet getTripletMqext() throws JMSException {
/* 1296 */     String armCorrelator = getStringProperty("JMS_IBM_ArmCorrelator");
/* 1297 */     String wrmCorrelator = getStringProperty("JMS_IBM_RMCorrelator");
/* 1298 */     long deliveryDelay = getJMSDeliveryDelay();
/* 1299 */     long deliveryTime = getJMSDeliveryTime();
/*      */     
/* 1301 */     Triplet mqextTriplet = null;
/*      */     
/* 1303 */     if (armCorrelator != null || wrmCorrelator != null) {
/* 1304 */       mqextTriplet = new Triplet("mqext", 4);
/*      */       
/* 1306 */       if (armCorrelator != null) {
/* 1307 */         mqextTriplet.add("Arm", armCorrelator);
/*      */       }
/* 1309 */       if (wrmCorrelator != null) {
/* 1310 */         mqextTriplet.add("Wrm", wrmCorrelator);
/*      */       }
/* 1312 */       if (deliveryDelay > 0L) {
/* 1313 */         mqextTriplet.add("Dly", String.valueOf(deliveryDelay));
/*      */       }
/* 1315 */       if (deliveryDelay > 0L) {
/* 1316 */         mqextTriplet.add("Dtm", String.valueOf(deliveryTime));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1321 */     if (Trace.isOn) {
/* 1322 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletMqext()", "getter", mqextTriplet);
/*      */     }
/*      */     
/* 1325 */     return mqextTriplet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String _getUsrFolder() throws JMSException {
/* 1337 */     if (Trace.isOn) {
/* 1338 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getUsrFolder()");
/*      */     }
/*      */     
/* 1341 */     StringBuilder rfhstr = new StringBuilder(40);
/* 1342 */     Iterator<String> propNames = this.properties.keySet().iterator();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1347 */     rfhstr.append("<usr>");
/*      */ 
/*      */     
/* 1350 */     while (propNames.hasNext()) {
/* 1351 */       String name = propNames.next();
/*      */ 
/*      */ 
/*      */       
/* 1355 */       if (!name.startsWith("JMS")) {
/*      */         
/* 1357 */         Object value = getObjectProperty(name);
/*      */ 
/*      */         
/* 1360 */         WMQMessageUtils.formatElement(name, value, rfhstr);
/*      */       } 
/*      */     } 
/*      */     
/* 1364 */     rfhstr.append("</usr>");
/*      */     
/* 1366 */     String usrFolder = rfhstr.toString();
/* 1367 */     if (Trace.isOn) {
/* 1368 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_getUsrFolder()", usrFolder);
/*      */     }
/*      */     
/* 1371 */     return usrFolder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Triplet getTripletUsr() throws JMSException {
/* 1381 */     Set<String> propSet = this.properties.keySet();
/* 1382 */     int size = propSet.size();
/* 1383 */     Triplet usrTriplet = null;
/*      */     
/* 1385 */     if (size > 0) {
/* 1386 */       Iterator<String> propNames = propSet.iterator();
/* 1387 */       usrTriplet = new Triplet("usr", size);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1392 */       while (propNames.hasNext()) {
/* 1393 */         String name = propNames.next();
/*      */ 
/*      */ 
/*      */         
/* 1397 */         if (!name.startsWith("JMS")) {
/* 1398 */           Object value = getObjectProperty(name);
/*      */           
/* 1400 */           if (value != null) {
/*      */             
/* 1402 */             Integer type = WMQMessageUtils.datatypeForTriplets.get(value.getClass());
/* 1403 */             usrTriplet.add(name, value, type); continue;
/*      */           } 
/* 1405 */           usrTriplet.add(name, null, Constants.TYPE_NULL);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1411 */     if (Trace.isOn) {
/* 1412 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "getTripletUsr()", "getter", usrTriplet);
/*      */     }
/*      */     
/* 1415 */     return usrTriplet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void _parseJmsFolder(String s, boolean persistenceFromMD) throws JMSException {
/* 1429 */     if (Trace.isOn) {
/* 1430 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseJmsFolder(String,boolean)", new Object[] { s, 
/* 1431 */             Boolean.valueOf(persistenceFromMD) });
/*      */     }
/*      */ 
/*      */     
/* 1435 */     if (this.rfh2FolderParser == null) {
/* 1436 */       this.rfh2FolderParser = new WMQRFH2FolderParser();
/*      */     }
/*      */ 
/*      */     
/* 1440 */     this.rfh2FolderParser.setFolder(s, "jms");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1447 */     setJMSDeliveryMode(-3);
/*      */     
/* 1449 */     while (this.rfh2FolderParser.hasNext()) {
/* 1450 */       String element = this.rfh2FolderParser.getNextElement();
/* 1451 */       String value = this.rfh2FolderParser.getElementValue();
/*      */ 
/*      */ 
/*      */       
/* 1455 */       if (element.equals("Dst")) {
/* 1456 */         String tmp = (String)WMQMessageUtils.deformatTypedElement(10, value);
/*      */         
/* 1458 */         setJMSDestinationAsString(tmp); continue;
/* 1459 */       }  if (value != null && element.equals("Tms")) {
/*      */         try {
/* 1461 */           long timestamp = Long.parseLong(value);
/* 1462 */           setJMSTimestamp(timestamp);
/*      */         }
/* 1464 */         catch (NumberFormatException e) {
/* 1465 */           if (Trace.isOn) {
/* 1466 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseJmsFolder(String,boolean)", e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1471 */           if (Trace.isOn) {
/* 1472 */             Trace.data(this, "_parseJmsFolder(String,boolean)", "Unexpected value for Tms element in the jms folder of the RFH2", value);
/*      */           }
/*      */           
/* 1475 */           throwBadRFH2Exception();
/*      */         }  continue;
/* 1477 */       }  if (element.equals("Dlv") && !persistenceFromMD) {
/*      */         
/* 1479 */         if (value.equals("1")) {
/*      */           
/* 1481 */           setJMSDeliveryMode(1); continue;
/* 1482 */         }  if (value.equals("2")) {
/*      */           
/* 1484 */           setJMSDeliveryMode(2);
/*      */           
/*      */           continue;
/*      */         } 
/* 1488 */         int deliveryMode = Integer.parseInt(value);
/* 1489 */         setJMSDeliveryMode(deliveryMode); continue;
/*      */       } 
/* 1491 */       if (element.equals("Rto")) {
/* 1492 */         String tmp = (String)WMQMessageUtils.deformatTypedElement(10, value);
/*      */         
/* 1494 */         setJMSReplyToAsString(tmp); continue;
/* 1495 */       }  if (element.equals("Exp")) {
/* 1496 */         long expiration = Long.parseLong(value);
/* 1497 */         setJMSExpiration(expiration); continue;
/* 1498 */       }  if (element.equals("Cid")) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1503 */         setJMSCorrelationID(
/* 1504 */             (String)WMQMessageUtils.deformatTypedElement(10, value)); continue;
/*      */       } 
/* 1506 */       if (element.equals("Uci")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1516 */         if (value.length() == 48) {
/* 1517 */           setStringProperty("JMS_IBM_ConnectionID", value);
/*      */           continue;
/*      */         } 
/* 1520 */         if (Trace.isOn) {
/* 1521 */           Trace.data(this, "_parseJmsFolder(String,boolean)", "Unexpected value for Uci element in the jms folder of the RFH2", value);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1527 */         throwBadRFH2Exception(); continue;
/*      */       } 
/* 1529 */       if (element.equals("Gid")) {
/* 1530 */         setObjectProperty("JMSXGroupID", 
/* 1531 */             WMQMessageUtils.deformatTypedElement(10, value)); continue;
/*      */       } 
/* 1533 */       if (element.equals("Seq")) {
/*      */         try {
/* 1535 */           setObjectProperty("JMSXGroupSeq", WMQMessageUtils.deformatTypedElement(6, value));
/*      */         }
/* 1537 */         catch (JMSException e) {
/* 1538 */           if (Trace.isOn) {
/* 1539 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseJmsFolder(String,boolean)", (Throwable)e, 2);
/*      */           }
/*      */ 
/*      */           
/* 1543 */           if (Trace.isOn) {
/* 1544 */             Trace.data(this, "_parseJmsFolder(String,boolean)", "Unexpected value for Seq element in the jms folder of the RFH2", value);
/*      */           }
/*      */           
/* 1547 */           throwBadRFH2Exception();
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1552 */     if (Trace.isOn) {
/* 1553 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseJmsFolder(String,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTripletJms(Triplet jmsTriplet, boolean persistenceFromMD) throws JMSException {
/* 1571 */     if (Trace.isOn) {
/* 1572 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletJms(Triplet,boolean)", new Object[] { jmsTriplet, 
/*      */             
/* 1574 */             Boolean.valueOf(persistenceFromMD) });
/*      */     }
/*      */     
/* 1577 */     if (!"jms".equals(jmsTriplet.getLabel())) {
/* 1578 */       throwMsgCreateErrorException();
/*      */     }
/*      */ 
/*      */     
/* 1582 */     int size = jmsTriplet.size();
/* 1583 */     if (size == 0) {
/* 1584 */       if (Trace.isOn) {
/* 1585 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletJms(Triplet,boolean)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1595 */     String dstValue = null;
/* 1596 */     String rtoValue = null;
/* 1597 */     String tmsValue = null;
/* 1598 */     String expValue = null;
/* 1599 */     String cidValue = null;
/* 1600 */     String dlvValue = null;
/* 1601 */     String gidValue = null;
/* 1602 */     String seqValue = null;
/* 1603 */     byte[] uciValue = null;
/*      */ 
/*      */     
/* 1606 */     for (int i = 0; i < size; i++) {
/* 1607 */       String key = jmsTriplet.getKey(i);
/* 1608 */       int type = jmsTriplet.getType(i);
/*      */       
/* 1610 */       if (type == 0) {
/* 1611 */         String value = (String)jmsTriplet.getValue(i);
/* 1612 */         if (key == null || value == null) {
/* 1613 */           throwInvalidTripletException(jmsTriplet);
/*      */         }
/* 1615 */         char index = key.charAt(0);
/* 1616 */         switch (index) {
/*      */           case 'D':
/* 1618 */             if (key.equals("Dst")) {
/* 1619 */               dstValue = value; break;
/* 1620 */             }  if (key.equals("Dlv")) {
/* 1621 */               dlvValue = value;
/*      */             }
/*      */             break;
/*      */           case 'R':
/* 1625 */             if (key.equals("Rto")) {
/* 1626 */               rtoValue = value;
/*      */             }
/*      */             break;
/*      */           case 'T':
/* 1630 */             if (key.equals("Tms")) {
/* 1631 */               tmsValue = value;
/*      */             }
/*      */             break;
/*      */           case 'E':
/* 1635 */             if (key.equals("Exp")) {
/* 1636 */               expValue = value;
/*      */             }
/*      */             break;
/*      */           case 'C':
/* 1640 */             if (key.equals("Cid")) {
/* 1641 */               cidValue = value;
/*      */             }
/*      */             break;
/*      */           case 'G':
/* 1645 */             if (key.equals("Gid")) {
/* 1646 */               gidValue = value;
/*      */             }
/*      */             break;
/*      */           case 'S':
/* 1650 */             if (key.equals("Seq")) {
/* 1651 */               seqValue = value;
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/* 1658 */       } else if (type == 2) {
/* 1659 */         byte[] value = (byte[])jmsTriplet.getValue(i);
/* 1660 */         if (key == null || value == null) {
/* 1661 */           throwInvalidTripletException(jmsTriplet);
/*      */         }
/* 1663 */         if (key.equals("Uci")) {
/* 1664 */           uciValue = value;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1672 */     if (dstValue != null) {
/* 1673 */       setJMSDestinationAsString(dstValue);
/*      */     }
/*      */ 
/*      */     
/* 1677 */     if (rtoValue != null) {
/* 1678 */       setJMSReplyToAsString(rtoValue);
/*      */     }
/*      */ 
/*      */     
/* 1682 */     if (tmsValue != null) {
/* 1683 */       long timestamp = Long.parseLong(tmsValue);
/* 1684 */       setJMSTimestamp(timestamp);
/*      */     } 
/*      */ 
/*      */     
/* 1688 */     if (expValue != null) {
/* 1689 */       long expiration = Long.parseLong(expValue);
/* 1690 */       setJMSExpiration(expiration);
/*      */     } 
/*      */ 
/*      */     
/* 1694 */     if (cidValue != null) {
/* 1695 */       setJMSCorrelationID(cidValue);
/*      */     }
/*      */ 
/*      */     
/* 1699 */     if (dlvValue != null && !persistenceFromMD)
/*      */     {
/* 1701 */       if (dlvValue.equals("1")) {
/*      */         
/* 1703 */         setJMSDeliveryMode(1);
/* 1704 */       } else if (dlvValue.equals("2")) {
/*      */         
/* 1706 */         setJMSDeliveryMode(2);
/*      */       }
/*      */       else {
/*      */         
/* 1710 */         int deliveryMode = Integer.parseInt(dlvValue);
/* 1711 */         setJMSDeliveryMode(deliveryMode);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1716 */     if (gidValue != null) {
/* 1717 */       setStringProperty("JMSXGroupID", gidValue);
/*      */     }
/*      */ 
/*      */     
/* 1721 */     if (seqValue != null) {
/* 1722 */       int seq = Integer.parseInt(seqValue);
/* 1723 */       setIntProperty("JMSXGroupSeq", seq);
/*      */     } 
/*      */ 
/*      */     
/* 1727 */     if (uciValue != null) {
/*      */ 
/*      */ 
/*      */       
/* 1731 */       StringBuffer sb = new StringBuffer(48);
/* 1732 */       WMQUtils.binToHexUpperCase(uciValue, 0, uciValue.length, sb);
/* 1733 */       setStringProperty("JMS_IBM_ConnectionID", sb
/* 1734 */           .toString());
/*      */     } 
/* 1736 */     if (Trace.isOn) {
/* 1737 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletJms(Triplet,boolean)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void _parseMQExtFolder(String s) throws JMSException {
/* 1754 */     if (Trace.isOn) {
/* 1755 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMQExtFolder(String)", new Object[] { s });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1760 */     if (this.rfh2FolderParser == null) {
/* 1761 */       this.rfh2FolderParser = new WMQRFH2FolderParser();
/*      */     }
/*      */ 
/*      */     
/* 1765 */     this.rfh2FolderParser.setFolder(s, "mqext");
/*      */ 
/*      */ 
/*      */     
/* 1769 */     while (this.rfh2FolderParser.hasNext()) {
/* 1770 */       String element = this.rfh2FolderParser.getNextElement();
/* 1771 */       String value = this.rfh2FolderParser.getElementValue();
/*      */       
/* 1773 */       if (element.equals("Arm")) {
/* 1774 */         String arm = (String)WMQMessageUtils.deformatTypedElement(10, value);
/*      */         
/* 1776 */         setStringProperty("JMS_IBM_ArmCorrelator", arm); continue;
/* 1777 */       }  if (element.equals("Wrm")) {
/* 1778 */         String wrm = (String)WMQMessageUtils.deformatTypedElement(10, value);
/*      */         
/* 1780 */         setStringProperty("JMS_IBM_RMCorrelator", wrm); continue;
/*      */       } 
/* 1782 */       if (element.equals("Dly")) {
/* 1783 */         setJMSDeliveryDelay(Long.parseLong(value)); continue;
/* 1784 */       }  if (element.equals("Dtm")) {
/* 1785 */         setJMSDeliveryTime(Long.parseLong(value));
/*      */       }
/*      */     } 
/*      */     
/* 1789 */     if (Trace.isOn) {
/* 1790 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMQExtFolder(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTripletMqext(Triplet mqextTriplet) throws JMSException {
/* 1808 */     if (Trace.isOn) {
/* 1809 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMqext(Triplet)", new Object[] { mqextTriplet });
/*      */     }
/*      */ 
/*      */     
/* 1813 */     if (!"mqext".equals(mqextTriplet.getLabel())) {
/* 1814 */       throwMsgCreateErrorException();
/*      */     }
/*      */ 
/*      */     
/* 1818 */     int size = mqextTriplet.size();
/* 1819 */     if (size == 0) {
/* 1820 */       if (Trace.isOn) {
/* 1821 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMqext(Triplet)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1828 */     String armValue = null;
/* 1829 */     String wrmValue = null;
/* 1830 */     String dlyValue = null;
/* 1831 */     String dtmValue = null;
/*      */ 
/*      */     
/* 1834 */     for (int i = 0; i < size; i++) {
/* 1835 */       String key = mqextTriplet.getKey(i);
/* 1836 */       int type = mqextTriplet.getType(i);
/*      */       
/* 1838 */       if (type == 0) {
/* 1839 */         String value = (String)mqextTriplet.getValue(i);
/* 1840 */         if (key == null || value == null) {
/* 1841 */           throwInvalidTripletException(mqextTriplet);
/*      */         }
/* 1843 */         char index = key.charAt(0);
/* 1844 */         switch (index) {
/*      */           case 'A':
/* 1846 */             if (key.equals("Arm")) {
/* 1847 */               armValue = value;
/*      */             }
/*      */             break;
/*      */           case 'D':
/* 1851 */             if (key.equals("Dly")) {
/* 1852 */               dlyValue = value; break;
/* 1853 */             }  if (key.equals("Dtm")) {
/* 1854 */               dtmValue = value;
/*      */             }
/*      */             break;
/*      */           
/*      */           case 'W':
/* 1859 */             if (key.equals("Wrm")) {
/* 1860 */               wrmValue = value;
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/* 1873 */     if (armValue != null) {
/* 1874 */       setStringProperty("JMS_IBM_ArmCorrelator", armValue);
/*      */     }
/*      */ 
/*      */     
/* 1878 */     if (wrmValue != null) {
/* 1879 */       setStringProperty("JMS_IBM_RMCorrelator", wrmValue);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1884 */     if (dlyValue != null) {
/* 1885 */       long deliveryDelay = Long.parseLong(dlyValue);
/* 1886 */       setJMSDeliveryDelay(deliveryDelay);
/*      */     } 
/*      */ 
/*      */     
/* 1890 */     if (dtmValue != null) {
/* 1891 */       long deliveryTime = Long.parseLong(dtmValue);
/* 1892 */       setJMSDeliveryTime(deliveryTime);
/*      */     } 
/*      */     
/* 1895 */     if (Trace.isOn) {
/* 1896 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMqext(Triplet)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void _parseMQPsFolder(String s) throws JMSException {
/* 1910 */     if (Trace.isOn) {
/* 1911 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMQPsFolder(String)", new Object[] { s });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1916 */     if (this.rfh2FolderParser == null) {
/* 1917 */       this.rfh2FolderParser = new WMQRFH2FolderParser();
/*      */     }
/*      */ 
/*      */     
/* 1921 */     this.rfh2FolderParser.setFolder(s, "mqps");
/*      */ 
/*      */ 
/*      */     
/* 1925 */     while (this.rfh2FolderParser.hasNext()) {
/* 1926 */       String element = this.rfh2FolderParser.getNextElement();
/* 1927 */       String value = this.rfh2FolderParser.getElementValue();
/*      */       
/* 1929 */       if (element.equals("Ret")) {
/* 1930 */         if (value.equals("1"))
/* 1931 */           setIntProperty("JMS_IBM_Retain", 1); 
/*      */         continue;
/*      */       } 
/* 1934 */       if (element.equals("Top")) {
/*      */ 
/*      */ 
/*      */         
/* 1938 */         if (getJMSDestinationAsString() == null) {
/* 1939 */           StringBuilder jmsTopic = new StringBuilder("topic://");
/* 1940 */           jmsTopic.append(value);
/*      */           
/* 1942 */           setJMSDestinationAsString(jmsTopic.toString());
/*      */         }  continue;
/* 1944 */       }  if (element.equals("Sud")) {
/* 1945 */         setStringProperty("JMS_IBM_SubscriptionUserData", value);
/*      */       }
/*      */     } 
/*      */     
/* 1949 */     if (Trace.isOn) {
/* 1950 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseMQPsFolder(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTripletMqps(Triplet mqpsTriplet) throws JMSException {
/* 1965 */     if (Trace.isOn) {
/* 1966 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMqps(Triplet)", new Object[] { mqpsTriplet });
/*      */     }
/*      */ 
/*      */     
/* 1970 */     if (!"mqps".equals(mqpsTriplet.getLabel())) {
/* 1971 */       throwMsgCreateErrorException();
/*      */     }
/*      */ 
/*      */     
/* 1975 */     int size = mqpsTriplet.size();
/* 1976 */     if (size == 0) {
/* 1977 */       if (Trace.isOn) {
/* 1978 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMqps(Triplet)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1985 */     Boolean retValue = null;
/*      */ 
/*      */     
/* 1988 */     for (int i = 0; i < size; i++) {
/* 1989 */       String key = mqpsTriplet.getKey(i);
/* 1990 */       int type = mqpsTriplet.getType(i);
/*      */       
/* 1992 */       if (type == 1) {
/* 1993 */         Boolean value = (Boolean)mqpsTriplet.getValue(i);
/* 1994 */         if (key == null || value == null) {
/* 1995 */           throwInvalidTripletException(mqpsTriplet);
/*      */         }
/* 1997 */         if (key.equals("Ret")) {
/* 1998 */           retValue = value;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2006 */     if (retValue != null && retValue.booleanValue() == true) {
/* 2007 */       setIntProperty("JMS_IBM_Retain", 1);
/*      */     }
/*      */     
/* 2010 */     if (Trace.isOn) {
/* 2011 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletMqps(Triplet)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void _parseUsrFolder(String sp) throws JMSException {
/* 2028 */     if (Trace.isOn) {
/* 2029 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseUsrFolder(String)", new Object[] { sp });
/*      */     }
/*      */ 
/*      */     
/* 2033 */     String s = sp.trim();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2039 */       StringTokenizer openBracketStrtok = new StringTokenizer(s, "<");
/* 2040 */       String openBracketToken = openBracketStrtok.nextToken();
/* 2041 */       openBracketToken = openBracketStrtok.nextToken();
/*      */ 
/*      */       
/* 2044 */       while (!openBracketToken.equals("/usr>"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2050 */         boolean nullElement = false;
/* 2051 */         int nullIndex = openBracketToken.indexOf(" xsi:nil");
/* 2052 */         int rightTriBracketIndex = openBracketToken.indexOf(">");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2058 */         if (nullIndex != -1 && nullIndex < rightTriBracketIndex) {
/*      */ 
/*      */           
/* 2061 */           int dtindex = openBracketToken.indexOf(" dt=");
/* 2062 */           if (dtindex != -1 && dtindex < nullIndex) {
/* 2063 */             nullIndex = dtindex;
/*      */           }
/* 2065 */           nullElement = true;
/*      */         } 
/*      */         
/* 2068 */         if (nullElement) {
/* 2069 */           setObjectProperty(openBracketToken.substring(0, nullIndex), null);
/* 2070 */           StringTokenizer closeBracketStrtok = new StringTokenizer(openBracketToken, ">");
/* 2071 */           String closeBracketToken = closeBracketStrtok.nextToken();
/* 2072 */           if (closeBracketToken.charAt(closeBracketToken.length() - 1) != '/') {
/*      */ 
/*      */ 
/*      */             
/* 2076 */             openBracketToken = openBracketStrtok.nextToken();
/* 2077 */             if (openBracketToken.charAt(0) != '/')
/*      */             {
/* 2079 */               throwBadRFH2Exception();
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           String propertyName, propertyType;
/* 2084 */           StringTokenizer closeBracketStrtok = new StringTokenizer(openBracketToken, ">");
/* 2085 */           int closeBracketTokenCount = closeBracketStrtok.countTokens();
/* 2086 */           String closeBracketToken = closeBracketStrtok.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2092 */           boolean emptyElement = (closeBracketToken.charAt(closeBracketToken.length() - 1) == '/');
/*      */ 
/*      */           
/* 2095 */           int dtIndex = closeBracketToken.indexOf(" dt=");
/* 2096 */           if (dtIndex != -1) {
/* 2097 */             propertyType = closeBracketToken.substring(dtIndex + 4, closeBracketToken
/* 2098 */                 .length() - (emptyElement ? 1 : 0));
/* 2099 */             propertyName = closeBracketToken.substring(0, closeBracketToken.indexOf(' '));
/*      */           } else {
/* 2101 */             if (emptyElement) {
/* 2102 */               propertyName = closeBracketToken.substring(0, closeBracketToken.length() - 1);
/*      */             } else {
/* 2104 */               propertyName = closeBracketToken;
/*      */             } 
/* 2106 */             propertyType = "'string'";
/*      */           } 
/*      */ 
/*      */           
/* 2110 */           if (emptyElement) {
/* 2111 */             setObjectProperty(propertyName, WMQMessageUtils.deformatElement(propertyType, ""));
/*      */           } else {
/* 2113 */             if (closeBracketTokenCount == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2118 */               setObjectProperty(propertyName, WMQMessageUtils.deformatElement(propertyType, ""));
/*      */             } else {
/*      */               
/* 2121 */               String propertyValue = closeBracketStrtok.nextToken();
/*      */               
/* 2123 */               setObjectProperty(propertyName, WMQMessageUtils.deformatElement(propertyType, propertyValue));
/*      */             } 
/*      */ 
/*      */             
/* 2127 */             openBracketToken = openBracketStrtok.nextToken();
/* 2128 */             if (openBracketToken.charAt(0) != '/') {
/* 2129 */               throwBadRFH2Exception();
/*      */             }
/*      */           } 
/*      */         } 
/* 2133 */         openBracketToken = openBracketStrtok.nextToken();
/*      */       }
/*      */     
/*      */     }
/* 2137 */     catch (Exception ex) {
/* 2138 */       if (Trace.isOn) {
/* 2139 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseUsrFolder(String)", ex, 2);
/*      */       }
/*      */       
/* 2142 */       JMSException jmsEx = (JMSException)NLSServices.createException("JMSCMQ1050", null);
/* 2143 */       jmsEx.setLinkedException(ex);
/* 2144 */       if (Trace.isOn) {
/* 2145 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseUsrFolder(String)", (Throwable)jmsEx);
/*      */       }
/*      */       
/* 2148 */       throw jmsEx;
/*      */     } 
/*      */     
/* 2151 */     if (Trace.isOn) {
/* 2152 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "_parseUsrFolder(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTripletUsr(Triplet usrTriplet) throws JMSException {
/* 2167 */     if (Trace.isOn) {
/* 2168 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletUsr(Triplet)", new Object[] { usrTriplet });
/*      */     }
/*      */ 
/*      */     
/* 2172 */     if (!"usr".equals(usrTriplet.getLabel())) {
/* 2173 */       throwMsgCreateErrorException();
/*      */     }
/*      */ 
/*      */     
/* 2177 */     int size = usrTriplet.size();
/* 2178 */     if (size == 0) {
/* 2179 */       if (Trace.isOn) {
/* 2180 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletUsr(Triplet)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2187 */     for (int i = 0; i < size; i++) {
/* 2188 */       String key = usrTriplet.getKey(i);
/* 2189 */       Object value = usrTriplet.getValue(i);
/* 2190 */       int type = usrTriplet.getType(i);
/* 2191 */       if (key == null || (type != 9 && value == null))
/*      */       {
/* 2193 */         throwInvalidTripletException(usrTriplet);
/*      */       }
/* 2195 */       setObjectProperty(key, value);
/*      */     } 
/* 2197 */     if (Trace.isOn) {
/* 2198 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageBase", "setTripletUsr(Triplet)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void throwBadRFH2Exception() throws JMSException {
/* 2206 */     JMSException je = (JMSException)NLSServices.createException("JMSCMQ1050", null);
/*      */     
/* 2208 */     throw je;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void throwMsgCreateErrorException() throws JMSException {
/* 2213 */     HashMap<String, Object> inserts = new HashMap<>();
/* 2214 */     inserts.put("XMSC_MESSAGE_ID", "unspecified");
/* 2215 */     inserts.put("XMSC_CORRELATION_ID", "unspecified");
/*      */     
/* 2217 */     JMSException je = (JMSException)NLSServices.createException("JMSCMQ1000", inserts);
/*      */     
/* 2219 */     throw je;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void throwInvalidTripletException(Triplet triplet) throws JMSException {
/* 2225 */     HashMap<String, Object> inserts = new HashMap<>();
/* 2226 */     inserts.put("XMSC_INSERT_PROPERTY", triplet.getLabel());
/* 2227 */     inserts.put("XMSC_INSERT_VALUE", triplet.toString());
/* 2228 */     JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/*      */     
/* 2230 */     throw je;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMessageBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */