/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*      */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MessageEOFException;
/*      */ import javax.jms.MessageFormatException;
/*      */ import javax.jms.MessageNotReadableException;
/*      */ import javax.jms.MessageNotWriteableException;
/*      */ import javax.jms.ResourceAllocationException;
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
/*      */ public abstract class JMSMessage
/*      */   implements JmsPropertyContext, ProviderMessage, Serializable
/*      */ {
/*      */   static final long serialVersionUID = -4436266789041169376L;
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSMessage.java";
/*      */   public static final String trimJMSXUserID = "com.ibm.mq.jms.trimJMSXUserID";
/*      */   
/*      */   static {
/*  268 */     if (Trace.isOn) {
/*  269 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSMessage.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  295 */   String messageClass = "jms_none";
/*      */ 
/*      */   
/*      */   public static final String CLASS_NONE = "jms_none";
/*      */   
/*      */   public static final String CLASS_TEXT = "jms_text";
/*      */   
/*      */   public static final String CLASS_OBJECT = "jms_object";
/*      */   
/*      */   public static final String CLASS_MAP = "jms_map";
/*      */   
/*      */   public static final String CLASS_STREAM = "jms_stream";
/*      */   
/*      */   public static final String CLASS_BYTES = "jms_bytes";
/*      */   
/*      */   private static final String MCD_CLASS_NONE = "<mcd><Msd>jms_none</Msd></mcd>";
/*      */   
/*      */   private static final String MCD_CLASS_TEXT = "<mcd><Msd>jms_text</Msd></mcd>";
/*      */   
/*      */   private static final String MCD_CLASS_OBJECT = "<mcd><Msd>jms_object</Msd></mcd>";
/*      */   
/*      */   private static final String MCD_CLASS_MAP = "<mcd><Msd>jms_map</Msd></mcd>";
/*      */   
/*      */   private static final String MCD_CLASS_STREAM = "<mcd><Msd>jms_stream</Msd></mcd>";
/*      */   
/*      */   private static final String MCD_CLASS_BYTES = "<mcd><Msd>jms_bytes</Msd></mcd>";
/*      */   
/*      */   private static final String MCD_CLASS_TEXT_NULL_MSG = "<mcd><Msd>jms_text</Msd><msgbody xsi:nil=\"true\"></msgbody></mcd>";
/*      */   
/*  324 */   private static final byte[] OPEN_MCDMSD = "<mcd><Msd>".getBytes(Charset.defaultCharset());
/*  325 */   private static final byte[] CLOSE_MSDMCD = "</Msd></mcd>".getBytes(Charset.defaultCharset());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String ENCODING_PROPERTY = "JMS_IBM_Encoding";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String CHARSET_PROPERTY = "JMS_IBM_Character_Set";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  342 */   private static final char[] BIN2HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
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
/*  356 */   private transient JMSAcknowledgePoint session = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean gotByConsume = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  375 */   transient JMSStringResources jmsStrings = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private String jmsStringResourcesClassName;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean propertiesReadOnly = false;
/*      */ 
/*      */ 
/*      */   
/*  387 */   protected Map properties = Collections.synchronizedMap(new HashMap<>());
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
/*  418 */   private String correlationId = null;
/*  419 */   private byte[] nativeCorrelId = null;
/*  420 */   private int deliveryMode = 2;
/*      */   private boolean hideDeliveryMode = false;
/*  422 */   private long expiration = 0L;
/*  423 */   private long timeToLive = 0L;
/*  424 */   private int priority = 4;
/*      */   private boolean redelivered = false;
/*  426 */   private long timestamp = -1L;
/*  427 */   private String type = null;
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
/*  440 */   private transient MQSession destinationFactory = null;
/*  441 */   private ProviderDestination destination = null;
/*  442 */   private transient String destinationString = null;
/*  443 */   private ProviderDestination replyTo = null;
/*  444 */   private transient String replyToString = null;
/*      */ 
/*      */ 
/*      */   
/*  448 */   private String messageId = null;
/*  449 */   private byte[] nativeMessageId = null;
/*      */ 
/*      */   
/*  452 */   private String msDomain = null;
/*  453 */   private String msFormat = null;
/*  454 */   private String msSet = null;
/*  455 */   private String msType = null;
/*      */ 
/*      */   
/*  458 */   private String psTopic = null;
/*  459 */   private String psConnID = null;
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
/*      */   boolean isNullMessage = true;
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
/*  485 */   private static Object HELD_INTERNAL = "JMSMessage.HELD_INTERNAL";
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
/*  496 */   private transient byte[] userIDAsBytes = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
/*      */   
/*  498 */   private transient byte[] putApplNameAsBytes = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
/*      */ 
/*      */   
/*  501 */   private transient byte[] putDateAsBytes = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32 };
/*      */   
/*  503 */   private transient byte[] putTimeAsBytes = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32 };
/*      */ 
/*      */   
/*      */   private transient int deliveryCountAsInt;
/*      */ 
/*      */   
/*      */   private transient int msgTypeAsInt;
/*      */ 
/*      */   
/*      */   private transient int groupSeqAsInt;
/*      */ 
/*      */   
/*      */   private transient int putApplTypeAsInt;
/*      */   
/*      */   private transient int feedbackAsInt;
/*      */   
/*      */   private transient int reportExceptionAsInt;
/*      */   
/*      */   private transient int reportExpirationAsInt;
/*      */   
/*      */   private transient int reportCOAAsInt;
/*      */   
/*      */   private transient int reportCODAsInt;
/*      */   
/*      */   private transient int reportPANAsInt;
/*      */   
/*      */   private transient int reportNANAsInt;
/*      */   
/*      */   private transient int passCorrelIDAsInt;
/*      */   
/*      */   private transient int reportMsgIDAsInt;
/*      */   
/*      */   private transient int reportDiscardAsInt;
/*      */   
/*      */   private transient boolean lastMsgInGroupAsBool;
/*      */   
/*      */   private int ccsidForStrings;
/*      */   
/*      */   private boolean ccsidForStringsIsAscii;
/*      */   
/*      */   private boolean stringsNeedCcsidConversion;
/*      */   
/*  545 */   private JmqiCodepage cachedCp = null;
/*      */ 
/*      */   
/*  548 */   private String armCorrelator = null;
/*      */ 
/*      */   
/*  551 */   private String wrmCorrelator = null;
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
/*      */   private String convertBytesToString(byte[] b, boolean isInvariant) throws JMSException {
/*  581 */     if (Trace.isOn) {
/*  582 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "convertBytesToString(byte [ ],boolean)", new Object[] { b, 
/*  583 */             Boolean.valueOf(isInvariant) });
/*      */     }
/*  585 */     String retVal = null;
/*  586 */     boolean canDoQuickConversion = false;
/*      */     
/*  588 */     if (this.stringsNeedCcsidConversion) {
/*  589 */       if (this.ccsidForStringsIsAscii) {
/*  590 */         if (isInvariant) {
/*  591 */           canDoQuickConversion = true;
/*      */         }
/*      */         else {
/*      */           
/*  595 */           canDoQuickConversion = true;
/*  596 */           for (int i = 0; i < b.length && canDoQuickConversion; i++) {
/*  597 */             canDoQuickConversion = (b[i] >= 0);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } else {
/*      */       
/*  603 */       canDoQuickConversion = true;
/*      */     } 
/*      */     
/*  606 */     if (canDoQuickConversion) {
/*      */       
/*      */       try {
/*  609 */         retVal = new String(b, "UTF-8");
/*      */       }
/*  611 */       catch (UnsupportedEncodingException e) {
/*  612 */         if (Trace.isOn) {
/*  613 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "convertBytesToString(byte [ ],boolean)", e, 1);
/*      */         }
/*      */         
/*  616 */         retVal = null;
/*      */       } 
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  622 */         if (this.cachedCp == null) {
/*  623 */           this.cachedCp = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.ccsidForStrings);
/*      */           
/*  625 */           if (this.cachedCp == null) {
/*      */             
/*  627 */             UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(this.ccsidForStrings));
/*  628 */             if (Trace.isOn) {
/*  629 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "convertBytesToString(byte [ ],boolean)", traceRet2, 1);
/*      */             }
/*      */             
/*  632 */             throw traceRet2;
/*      */           } 
/*      */         } 
/*      */         
/*  636 */         retVal = this.cachedCp.bytesToString(b);
/*      */       }
/*  638 */       catch (CharacterCodingException|UnsupportedEncodingException e) {
/*  639 */         if (Trace.isOn) {
/*  640 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "convertBytesToString(byte [ ],boolean)", e, 2);
/*      */         }
/*      */         
/*  643 */         this.cachedCp = null;
/*  644 */         JMSException traceRet1 = newJMSException(1001);
/*  645 */         if (Trace.isOn) {
/*  646 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "convertBytesToString(byte [ ],boolean)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/*  649 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */     
/*  653 */     if (Trace.isOn) {
/*  654 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "convertBytesToString(byte [ ],boolean)", retVal);
/*      */     }
/*      */     
/*  657 */     return retVal;
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
/*      */   public void _setCcsidForStrings(int _ccsid, boolean _ccsidIsAscii, boolean _stringsNeedCcsidConversion) {
/*  670 */     if (Trace.isOn)
/*  671 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setCcsidForStrings(int,boolean,boolean)", new Object[] {
/*  672 */             Integer.valueOf(_ccsid), 
/*  673 */             Boolean.valueOf(_ccsidIsAscii), Boolean.valueOf(_stringsNeedCcsidConversion)
/*      */           }); 
/*  675 */     this.ccsidForStrings = _ccsid;
/*  676 */     this.ccsidForStringsIsAscii = _ccsidIsAscii;
/*  677 */     this.stringsNeedCcsidConversion = _stringsNeedCcsidConversion;
/*  678 */     if (Trace.isOn) {
/*  679 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setCcsidForStrings(int,boolean,boolean)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object getInternalPropForName(String name) throws JMSException {
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getInternalPropForName(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  707 */     Object retVal = null;
/*      */     
/*  709 */     if (name.equals("JMSXUserID")) {
/*  710 */       retVal = convertBytesToString(this.userIDAsBytes, false);
/*  711 */       PropertyStore.register("com.ibm.mq.jms.trimJMSXUserID", false);
/*  712 */       Boolean isTrim = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.trimJMSXUserID");
/*  713 */       if (isTrim != null && isTrim.booleanValue() && retVal != null) {
/*  714 */         retVal = ((String)retVal).trim();
/*  715 */         if (Trace.isOn) {
/*  716 */           Trace.data(this, "getInternalPropForName(String)", "JMXUserID without pad", retVal);
/*      */         }
/*      */       }
/*      */     
/*  720 */     } else if (name.equals("JMSXAppID")) {
/*  721 */       retVal = convertBytesToString(this.putApplNameAsBytes, false);
/*      */     }
/*  723 */     else if (name.equals("JMS_IBM_PutDate")) {
/*  724 */       retVal = convertBytesToString(this.putDateAsBytes, true);
/*      */     }
/*  726 */     else if (name.equals("JMS_IBM_PutTime")) {
/*  727 */       retVal = convertBytesToString(this.putTimeAsBytes, true);
/*      */     }
/*  729 */     else if (name.equals("JMSXDeliveryCount")) {
/*  730 */       retVal = Integer.valueOf(this.deliveryCountAsInt);
/*      */     }
/*  732 */     else if (name.equals("JMS_IBM_MsgType")) {
/*  733 */       retVal = Integer.valueOf(this.msgTypeAsInt);
/*      */     }
/*  735 */     else if (name.equals("JMSXGroupSeq")) {
/*  736 */       retVal = Integer.valueOf(this.groupSeqAsInt);
/*      */     }
/*  738 */     else if (name.equals("JMS_IBM_PutApplType")) {
/*  739 */       retVal = Integer.valueOf(this.putApplTypeAsInt);
/*      */     }
/*  741 */     else if (name.equals("JMS_IBM_Feedback")) {
/*  742 */       retVal = Integer.valueOf(this.feedbackAsInt);
/*      */     }
/*  744 */     else if (name.equals("JMS_IBM_Report_Exception")) {
/*  745 */       retVal = Integer.valueOf(this.reportExceptionAsInt);
/*      */     }
/*  747 */     else if (name.equals("JMS_IBM_Report_Expiration")) {
/*  748 */       retVal = Integer.valueOf(this.reportExpirationAsInt);
/*      */     }
/*  750 */     else if (name.equals("JMS_IBM_Report_COA")) {
/*  751 */       retVal = Integer.valueOf(this.reportCOAAsInt);
/*      */     }
/*  753 */     else if (name.equals("JMS_IBM_Report_COD")) {
/*  754 */       retVal = Integer.valueOf(this.reportCODAsInt);
/*      */     }
/*  756 */     else if (name.equals("JMS_IBM_Report_PAN")) {
/*  757 */       retVal = Integer.valueOf(this.reportPANAsInt);
/*      */     }
/*  759 */     else if (name.equals("JMS_IBM_Report_NAN")) {
/*  760 */       retVal = Integer.valueOf(this.reportNANAsInt);
/*      */     }
/*  762 */     else if (name.equals("JMS_IBM_Report_Pass_Correl_ID")) {
/*  763 */       retVal = Integer.valueOf(this.passCorrelIDAsInt);
/*      */     }
/*  765 */     else if (name.equals("JMS_IBM_Report_Pass_Msg_ID")) {
/*  766 */       retVal = Integer.valueOf(this.reportMsgIDAsInt);
/*      */     }
/*  768 */     else if (name.equals("JMS_IBM_Report_Discard_Msg")) {
/*  769 */       retVal = Integer.valueOf(this.reportDiscardAsInt);
/*      */     }
/*  771 */     else if (name.equals("JMS_IBM_Last_Msg_In_Group")) {
/*  772 */       retVal = Boolean.valueOf(this.lastMsgInGroupAsBool);
/*      */     }
/*  774 */     else if (name.equals("JMS_IBM_ArmCorrelator")) {
/*  775 */       retVal = _getJMSIBMArmCorrelator();
/*      */     }
/*  777 */     else if (name.equals("JMS_IBM_RMCorrelator")) {
/*  778 */       retVal = _getJMSIBMWrmCorrelator();
/*      */     } else {
/*      */       
/*  781 */       JMSException traceRet1 = newJMSException(1001);
/*  782 */       if (Trace.isOn) {
/*  783 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getInternalPropForName(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  786 */       throw traceRet1;
/*      */     } 
/*      */     
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getInternalPropForName(String)", retVal);
/*      */     }
/*      */     
/*  793 */     return retVal;
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
/*      */   public void _setJMSXUserIDFromBytes(byte[] id) {
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXUserIDFromBytes(byte [ ])", new Object[] { id });
/*      */     }
/*      */ 
/*      */     
/*  810 */     int length = Math.min(id.length, this.userIDAsBytes.length);
/*  811 */     System.arraycopy(id, 0, this.userIDAsBytes, 0, length);
/*  812 */     this.properties.put("JMSXUserID", HELD_INTERNAL);
/*      */     
/*  814 */     if (Trace.isOn) {
/*  815 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXUserIDFromBytes(byte [ ])");
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
/*      */   public void _setJMSXPutAppIDFromBytes(byte[] id) throws JMSException {
/*  832 */     if (Trace.isOn) {
/*  833 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXPutAppIDFromBytes(byte [ ])", new Object[] { id });
/*      */     }
/*      */ 
/*      */     
/*  837 */     int length = Math.min(id.length, this.putApplNameAsBytes.length);
/*  838 */     System.arraycopy(id, 0, this.putApplNameAsBytes, 0, length);
/*  839 */     this.properties.put("JMSXAppID", HELD_INTERNAL);
/*      */     
/*  841 */     if (Trace.isOn) {
/*  842 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXPutAppIDFromBytes(byte [ ])");
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
/*      */   public void _setJMSXDeliveryCountFromInt(int c) {
/*  856 */     if (Trace.isOn) {
/*  857 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXDeliveryCountFromInt(int)", new Object[] {
/*  858 */             Integer.valueOf(c)
/*      */           });
/*      */     }
/*  861 */     this.deliveryCountAsInt = c;
/*  862 */     this.properties.put("JMSXDeliveryCount", HELD_INTERNAL);
/*      */     
/*  864 */     if (Trace.isOn) {
/*  865 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXDeliveryCountFromInt(int)");
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
/*      */   public void _setJMSIBMMsgTypeFromInt(int t) {
/*  880 */     if (Trace.isOn)
/*  881 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMMsgTypeFromInt(int)", new Object[] {
/*  882 */             Integer.valueOf(t)
/*      */           }); 
/*  884 */     this.msgTypeAsInt = t;
/*  885 */     this.properties.put("JMS_IBM_MsgType", HELD_INTERNAL);
/*  886 */     if (Trace.isOn) {
/*  887 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMMsgTypeFromInt(int)");
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
/*      */   public int _getJMSIBMMsgTypeAsInt() throws JMSException {
/*      */     int retVal;
/*  902 */     if (Trace.isOn) {
/*  903 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMMsgTypeAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  908 */     if (HELD_INTERNAL.equals("JMS_IBM_MsgType")) {
/*  909 */       retVal = this.msgTypeAsInt;
/*      */     } else {
/*      */       
/*  912 */       retVal = getIntProperty("JMS_IBM_MsgType");
/*      */     } 
/*      */     
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMMsgTypeAsInt()", 
/*  917 */           Integer.valueOf(retVal));
/*      */     }
/*  919 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSXGroupSeqFromInt(int g) {
/*  930 */     if (Trace.isOn) {
/*  931 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXGroupSeqFromInt(int)", new Object[] {
/*  932 */             Integer.valueOf(g)
/*      */           });
/*      */     }
/*  935 */     this.groupSeqAsInt = g;
/*  936 */     this.properties.put("JMSXGroupSeq", HELD_INTERNAL);
/*      */     
/*  938 */     if (Trace.isOn) {
/*  939 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXGroupSeqFromInt(int)");
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
/*      */   public int _getJMSXGroupSeqAsInt() throws JMSException {
/*      */     int retVal;
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSXGroupSeqAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  960 */     if (HELD_INTERNAL.equals("JMSXGroupSeq")) {
/*  961 */       retVal = this.groupSeqAsInt;
/*      */     } else {
/*      */       
/*  964 */       retVal = getIntProperty("JMSXGroupSeq");
/*      */     } 
/*      */     
/*  967 */     if (Trace.isOn) {
/*  968 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSXGroupSeqAsInt()", 
/*  969 */           Integer.valueOf(retVal));
/*      */     }
/*  971 */     return retVal;
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
/*      */   public void _setJMSIBMPutDateFromBytes(byte[] b) throws JMSException {
/*  983 */     if (Trace.isOn) {
/*  984 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPutDateFromBytes(byte [ ])", new Object[] { b });
/*      */     }
/*      */ 
/*      */     
/*  988 */     int length = Math.min(b.length, this.putDateAsBytes.length);
/*  989 */     System.arraycopy(b, 0, this.putDateAsBytes, 0, length);
/*  990 */     this.properties.put("JMS_IBM_PutDate", HELD_INTERNAL);
/*      */     
/*  992 */     if (Trace.isOn) {
/*  993 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPutDateFromBytes(byte [ ])");
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
/*      */   public void _setJMSIBMPutTimeFromBytes(byte[] b) throws JMSException {
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPutTimeFromBytes(byte [ ])", new Object[] { b });
/*      */     }
/*      */ 
/*      */     
/* 1013 */     int length = Math.min(b.length, this.putTimeAsBytes.length);
/* 1014 */     System.arraycopy(b, 0, this.putTimeAsBytes, 0, length);
/* 1015 */     this.properties.put("JMS_IBM_PutTime", HELD_INTERNAL);
/*      */     
/* 1017 */     if (Trace.isOn) {
/* 1018 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPutTimeFromBytes(byte [ ])");
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
/*      */   public void _setJMSIBMPutApplTypeFromInt(int t) {
/* 1031 */     if (Trace.isOn) {
/* 1032 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPutApplTypeFromInt(int)", new Object[] {
/* 1033 */             Integer.valueOf(t)
/*      */           });
/*      */     }
/* 1036 */     this.putApplTypeAsInt = t;
/* 1037 */     this.properties.put("JMS_IBM_PutApplType", HELD_INTERNAL);
/*      */     
/* 1039 */     if (Trace.isOn) {
/* 1040 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPutApplTypeFromInt(int)");
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
/*      */   public void _setJMSIBMFeedbackFromInt(int f) {
/* 1056 */     if (Trace.isOn) {
/* 1057 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMFeedbackFromInt(int)", new Object[] {
/* 1058 */             Integer.valueOf(f)
/*      */           });
/*      */     }
/* 1061 */     this.feedbackAsInt = f;
/* 1062 */     this.properties.put("JMS_IBM_Feedback", HELD_INTERNAL);
/*      */     
/* 1064 */     if (Trace.isOn) {
/* 1065 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMFeedbackFromInt(int)");
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
/*      */   public int _getJMSIBMFeedbackAsInt() throws JMSException {
/*      */     int retVal;
/* 1082 */     if (Trace.isOn) {
/* 1083 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMFeedbackAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1088 */     if (HELD_INTERNAL.equals("JMS_IBM_Feedback")) {
/* 1089 */       retVal = this.feedbackAsInt;
/*      */     } else {
/*      */       
/* 1092 */       retVal = getIntProperty("JMS_IBM_Feedback");
/*      */     } 
/*      */     
/* 1095 */     if (Trace.isOn) {
/* 1096 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMFeedbackAsInt()", 
/* 1097 */           Integer.valueOf(retVal));
/*      */     }
/* 1099 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportExceptionFromInt(int r) {
/* 1110 */     if (Trace.isOn) {
/* 1111 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportExceptionFromInt(int)", new Object[] {
/* 1112 */             Integer.valueOf(r)
/*      */           });
/*      */     }
/* 1115 */     this.reportExceptionAsInt = r;
/* 1116 */     this.properties.put("JMS_IBM_Report_Exception", HELD_INTERNAL);
/*      */     
/* 1118 */     if (Trace.isOn) {
/* 1119 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportExceptionFromInt(int)");
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
/*      */   public int _getJMSIBMReportExceptionAsInt() throws JMSException {
/*      */     int retVal;
/* 1135 */     if (Trace.isOn) {
/* 1136 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportExceptionAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1141 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_Exception")) {
/* 1142 */       retVal = this.reportExceptionAsInt;
/*      */     } else {
/*      */       
/* 1145 */       retVal = getIntProperty("JMS_IBM_Report_Exception");
/*      */     } 
/*      */     
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportExceptionAsInt()", 
/* 1150 */           Integer.valueOf(retVal));
/*      */     }
/* 1152 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportExpirationFromInt(int e) {
/* 1163 */     if (Trace.isOn) {
/* 1164 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportExpirationFromInt(int)", new Object[] {
/* 1165 */             Integer.valueOf(e)
/*      */           });
/*      */     }
/* 1168 */     this.reportExpirationAsInt = e;
/* 1169 */     this.properties.put("JMS_IBM_Report_Expiration", HELD_INTERNAL);
/*      */     
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportExpirationFromInt(int)");
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
/*      */   public int _getJMSIBMReportExpirationAsInt() throws JMSException {
/*      */     int retVal;
/* 1188 */     if (Trace.isOn) {
/* 1189 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportExpirationAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1194 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_Expiration")) {
/* 1195 */       retVal = this.reportExpirationAsInt;
/*      */     } else {
/*      */       
/* 1198 */       retVal = getIntProperty("JMS_IBM_Report_Expiration");
/*      */     } 
/*      */     
/* 1201 */     if (Trace.isOn) {
/* 1202 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportExpirationAsInt()", 
/* 1203 */           Integer.valueOf(retVal));
/*      */     }
/* 1205 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportCOAFromInt(int r) {
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportCOAFromInt(int)", new Object[] {
/* 1218 */             Integer.valueOf(r)
/*      */           });
/*      */     }
/* 1221 */     this.reportCOAAsInt = r;
/* 1222 */     this.properties.put("JMS_IBM_Report_COA", HELD_INTERNAL);
/*      */     
/* 1224 */     if (Trace.isOn) {
/* 1225 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportCOAFromInt(int)");
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
/*      */   public int _getJMSIBMReportCOAAsInt() throws JMSException {
/*      */     int retVal;
/* 1241 */     if (Trace.isOn) {
/* 1242 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportCOAAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1247 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_COA")) {
/* 1248 */       retVal = this.reportCOAAsInt;
/*      */     } else {
/*      */       
/* 1251 */       retVal = getIntProperty("JMS_IBM_Report_COA");
/*      */     } 
/*      */     
/* 1254 */     if (Trace.isOn) {
/* 1255 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportCOAAsInt()", 
/* 1256 */           Integer.valueOf(retVal));
/*      */     }
/* 1258 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportCODFromInt(int r) {
/* 1269 */     if (Trace.isOn) {
/* 1270 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportCODFromInt(int)", new Object[] {
/* 1271 */             Integer.valueOf(r)
/*      */           });
/*      */     }
/* 1274 */     this.reportCODAsInt = r;
/* 1275 */     this.properties.put("JMS_IBM_Report_COD", HELD_INTERNAL);
/*      */     
/* 1277 */     if (Trace.isOn) {
/* 1278 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportCODFromInt(int)");
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
/*      */   public int _getJMSIBMReportCODAsInt() throws JMSException {
/*      */     int retVal;
/* 1294 */     if (Trace.isOn) {
/* 1295 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportCODAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1300 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_COD")) {
/* 1301 */       retVal = this.reportCODAsInt;
/*      */     } else {
/*      */       
/* 1304 */       retVal = getIntProperty("JMS_IBM_Report_COD");
/*      */     } 
/*      */     
/* 1307 */     if (Trace.isOn) {
/* 1308 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportCODAsInt()", 
/* 1309 */           Integer.valueOf(retVal));
/*      */     }
/* 1311 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportPANFromInt(int r) {
/* 1322 */     if (Trace.isOn) {
/* 1323 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportPANFromInt(int)", new Object[] {
/* 1324 */             Integer.valueOf(r)
/*      */           });
/*      */     }
/* 1327 */     this.reportPANAsInt = r;
/* 1328 */     this.properties.put("JMS_IBM_Report_PAN", HELD_INTERNAL);
/*      */     
/* 1330 */     if (Trace.isOn) {
/* 1331 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportPANFromInt(int)");
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
/*      */   public int _getJMSIBMReportPANAsInt() throws JMSException {
/*      */     int retVal;
/* 1347 */     if (Trace.isOn) {
/* 1348 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportPANAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1353 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_PAN")) {
/* 1354 */       retVal = this.reportPANAsInt;
/*      */     } else {
/*      */       
/* 1357 */       retVal = getIntProperty("JMS_IBM_Report_PAN");
/*      */     } 
/*      */     
/* 1360 */     if (Trace.isOn) {
/* 1361 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportPANAsInt()", 
/* 1362 */           Integer.valueOf(retVal));
/*      */     }
/* 1364 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportNANFromInt(int r) {
/* 1375 */     if (Trace.isOn) {
/* 1376 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportNANFromInt(int)", new Object[] {
/* 1377 */             Integer.valueOf(r)
/*      */           });
/*      */     }
/* 1380 */     this.reportNANAsInt = r;
/* 1381 */     this.properties.put("JMS_IBM_Report_NAN", HELD_INTERNAL);
/*      */     
/* 1383 */     if (Trace.isOn) {
/* 1384 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportNANFromInt(int)");
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
/*      */   public int _getJMSIBMReportNANAsInt() throws JMSException {
/*      */     int retVal;
/* 1400 */     if (Trace.isOn) {
/* 1401 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportNANAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1406 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_NAN")) {
/* 1407 */       retVal = this.reportNANAsInt;
/*      */     } else {
/*      */       
/* 1410 */       retVal = getIntProperty("JMS_IBM_Report_NAN");
/*      */     } 
/*      */     
/* 1413 */     if (Trace.isOn) {
/* 1414 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportNANAsInt()", 
/* 1415 */           Integer.valueOf(retVal));
/*      */     }
/* 1417 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMPassCorrelIDFromInt(int c) {
/* 1428 */     if (Trace.isOn) {
/* 1429 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPassCorrelIDFromInt(int)", new Object[] {
/* 1430 */             Integer.valueOf(c)
/*      */           });
/*      */     }
/* 1433 */     this.passCorrelIDAsInt = c;
/* 1434 */     this.properties.put("JMS_IBM_Report_Pass_Correl_ID", HELD_INTERNAL);
/*      */     
/* 1436 */     if (Trace.isOn) {
/* 1437 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMPassCorrelIDFromInt(int)");
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
/*      */   public int _getJMSIBMPassCorrelIDAsInt() throws JMSException {
/*      */     int retVal;
/* 1453 */     if (Trace.isOn) {
/* 1454 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMPassCorrelIDAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1459 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_Pass_Correl_ID")) {
/* 1460 */       retVal = this.passCorrelIDAsInt;
/*      */     } else {
/*      */       
/* 1463 */       retVal = getIntProperty("JMS_IBM_Report_Pass_Correl_ID");
/*      */     } 
/*      */     
/* 1466 */     if (Trace.isOn) {
/* 1467 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMPassCorrelIDAsInt()", 
/* 1468 */           Integer.valueOf(retVal));
/*      */     }
/* 1470 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportMsgIDFromInt(int i) {
/* 1481 */     if (Trace.isOn) {
/* 1482 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportMsgIDFromInt(int)", new Object[] {
/* 1483 */             Integer.valueOf(i)
/*      */           });
/*      */     }
/* 1486 */     this.reportMsgIDAsInt = i;
/* 1487 */     this.properties.put("JMS_IBM_Report_Pass_Msg_ID", HELD_INTERNAL);
/*      */     
/* 1489 */     if (Trace.isOn) {
/* 1490 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportMsgIDFromInt(int)");
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
/*      */   public int _getJMSIBMReportMsgIDAsInt() throws JMSException {
/*      */     int retVal;
/* 1506 */     if (Trace.isOn) {
/* 1507 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportMsgIDAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1512 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_Pass_Msg_ID")) {
/* 1513 */       retVal = this.reportMsgIDAsInt;
/*      */     } else {
/*      */       
/* 1516 */       retVal = getIntProperty("JMS_IBM_Report_Pass_Msg_ID");
/*      */     } 
/*      */     
/* 1519 */     if (Trace.isOn) {
/* 1520 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportMsgIDAsInt()", 
/* 1521 */           Integer.valueOf(retVal));
/*      */     }
/* 1523 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMReportDiscardFromInt(int d) {
/* 1534 */     if (Trace.isOn) {
/* 1535 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportDiscardFromInt(int)", new Object[] {
/* 1536 */             Integer.valueOf(d)
/*      */           });
/*      */     }
/* 1539 */     this.reportDiscardAsInt = d;
/* 1540 */     this.properties.put("JMS_IBM_Report_Discard_Msg", HELD_INTERNAL);
/*      */     
/* 1542 */     if (Trace.isOn) {
/* 1543 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMReportDiscardFromInt(int)");
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
/*      */   public int _getJMSIBMReportDiscardAsInt() throws JMSException {
/*      */     int retVal;
/* 1560 */     if (Trace.isOn) {
/* 1561 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportDiscardAsInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1566 */     if (HELD_INTERNAL.equals("JMS_IBM_Report_Discard_Msg")) {
/* 1567 */       retVal = this.reportDiscardAsInt;
/*      */     } else {
/*      */       
/* 1570 */       retVal = getIntProperty("JMS_IBM_Report_Discard_Msg");
/*      */     } 
/*      */     
/* 1573 */     if (Trace.isOn) {
/* 1574 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMReportDiscardAsInt()", 
/* 1575 */           Integer.valueOf(retVal));
/*      */     }
/* 1577 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMLastMsgInGroupFromBool(boolean l) {
/* 1588 */     if (Trace.isOn) {
/* 1589 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMLastMsgInGroupFromBool(boolean)", new Object[] {
/* 1590 */             Boolean.valueOf(l)
/*      */           });
/*      */     }
/* 1593 */     this.lastMsgInGroupAsBool = l;
/* 1594 */     this.properties.put("JMS_IBM_Last_Msg_In_Group", HELD_INTERNAL);
/*      */     
/* 1596 */     if (Trace.isOn) {
/* 1597 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMLastMsgInGroupFromBool(boolean)");
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
/*      */   public boolean _getJMSIBMLastMsgInGroupAsBool() throws JMSException {
/*      */     boolean retVal;
/* 1613 */     if (Trace.isOn) {
/* 1614 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMLastMsgInGroupAsBool()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1619 */     if (HELD_INTERNAL.equals("JMS_IBM_Last_Msg_In_Group")) {
/* 1620 */       retVal = this.lastMsgInGroupAsBool;
/*      */     } else {
/*      */       
/* 1623 */       retVal = getBooleanProperty("JMS_IBM_Last_Msg_In_Group");
/*      */     } 
/*      */     
/* 1626 */     if (Trace.isOn) {
/* 1627 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMLastMsgInGroupAsBool()", 
/* 1628 */           Boolean.valueOf(retVal));
/*      */     }
/* 1630 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSMessage() {
/* 1639 */     if (Trace.isOn) {
/* 1640 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "<init>()");
/*      */     }
/*      */     
/* 1643 */     if (Trace.isOn) {
/* 1644 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "<init>()");
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
/*      */   public static JMSMessage _copyFromMessage(ProviderSession session, ProviderMessage inputMsg) throws JMSException {
/* 1681 */     if (Trace.isOn) {
/* 1682 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_copyFromMessage(ProviderSession,ProviderMessage)", new Object[] { session, inputMsg });
/*      */     }
/*      */     
/* 1685 */     JMSMessage outputMsg = null;
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
/* 1699 */     if (inputMsg instanceof ProviderTextMessage) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1705 */       ProviderTextMessage textMsg = ((MQSession)session).createTextMessage();
/*      */ 
/*      */ 
/*      */       
/* 1709 */       textMsg.setText(((ProviderTextMessage)inputMsg).getText());
/* 1710 */       outputMsg = (JMSMessage)textMsg;
/*      */     }
/* 1712 */     else if (inputMsg instanceof ProviderBytesMessage) {
/*      */ 
/*      */       
/* 1715 */       ProviderBytesMessage bytesMsg = ((MQSession)session).createBytesMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1720 */       ((JMSBytesMessage)inputMsg).reset();
/*      */ 
/*      */ 
/*      */       
/* 1724 */       byte[] copyBuffer = new byte[4000];
/*      */ 
/*      */       
/*      */       while (true) {
/* 1728 */         int bytesRead = ((JMSBytesMessage)inputMsg).readBytes(copyBuffer);
/* 1729 */         if (bytesRead < 0) {
/*      */           break;
/*      */         }
/*      */         
/* 1733 */         ((JMSBytesMessage)bytesMsg).writeBytes(copyBuffer, 0, bytesRead);
/*      */       } 
/*      */       
/* 1736 */       outputMsg = (JMSMessage)bytesMsg;
/*      */     }
/* 1738 */     else if (inputMsg instanceof ProviderMapMessage) {
/*      */ 
/*      */       
/* 1741 */       ProviderMapMessage mapMsg = ((MQSession)session).createMapMessage();
/*      */ 
/*      */ 
/*      */       
/* 1745 */       Enumeration<String> mapNames = ((ProviderMapMessage)inputMsg).getMapNames();
/*      */ 
/*      */       
/* 1748 */       while (mapNames.hasMoreElements()) {
/* 1749 */         String name = mapNames.nextElement();
/* 1750 */         mapMsg.setObject(name, ((ProviderMapMessage)inputMsg).getObject(name));
/*      */       } 
/*      */       
/* 1753 */       outputMsg = (JMSMessage)mapMsg;
/*      */     }
/* 1755 */     else if (inputMsg instanceof ProviderStreamMessage) {
/*      */ 
/*      */       
/* 1758 */       ProviderStreamMessage streamMsg = ((MQSession)session).createStreamMessage();
/*      */ 
/*      */       
/* 1761 */       ((ProviderStreamMessage)inputMsg).reset();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*      */         while (true) {
/* 1768 */           streamMsg.writeObject(((ProviderStreamMessage)inputMsg).readObject());
/*      */         }
/*      */       }
/* 1771 */       catch (MessageEOFException ex) {
/* 1772 */         if (Trace.isOn) {
/* 1773 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_copyFromMessage(ProviderSession,ProviderMessage)", (Throwable)ex, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1778 */         outputMsg = (JMSMessage)streamMsg;
/*      */       } 
/* 1780 */     } else if (inputMsg instanceof com.ibm.msg.client.provider.ProviderObjectMessage) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1785 */       JMSObjectMessage objectMsg = (JMSObjectMessage)((MQSession)session).createObjectMessage();
/*      */       
/* 1787 */       objectMsg.setObject(((JMSObjectMessage)inputMsg).getObject());
/* 1788 */       outputMsg = objectMsg;
/*      */     }
/*      */     else {
/*      */       
/* 1792 */       outputMsg = (JMSMessage)((MQSession)session).createMessage();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1818 */     outputMsg.setJMSType(inputMsg.getJMSType());
/*      */     
/* 1820 */     outputMsg.setJMSCorrelationID(inputMsg.getJMSCorrelationID());
/*      */     
/* 1822 */     String rTo = inputMsg.getJMSReplyToAsString();
/* 1823 */     outputMsg.setJMSReplyToAsString(rTo);
/*      */     
/* 1825 */     Enumeration<String> propertyNames = inputMsg.getPropertyNames();
/* 1826 */     while (propertyNames.hasMoreElements()) {
/* 1827 */       String name = propertyNames.nextElement();
/*      */ 
/*      */       
/*      */       try {
/* 1831 */         outputMsg.setObjectProperty(name, inputMsg.getObjectProperty(name));
/*      */       }
/* 1833 */       catch (Exception e) {
/* 1834 */         if (Trace.isOn) {
/* 1835 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_copyFromMessage(ProviderSession,ProviderMessage)", e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1840 */         if (Trace.isOn) {
/* 1841 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "warning: property " + name + " discarded", null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1846 */     if (Trace.isOn) {
/* 1847 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_copyFromMessage(ProviderSession,ProviderMessage)", outputMsg);
/*      */     }
/*      */     
/* 1850 */     return outputMsg;
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
/*      */   public String _getJmsFolder(boolean persistenceFromMD) throws JMSException {
/* 1881 */     if (Trace.isOn)
/* 1882 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJmsFolder(boolean)", new Object[] {
/* 1883 */             Boolean.valueOf(persistenceFromMD)
/*      */           }); 
/* 1885 */     StringBuffer rfhstr = new StringBuffer(100);
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
/* 1910 */     rfhstr.append("<jms>");
/*      */     
/* 1912 */     String destString = getJMSDestinationAsString();
/* 1913 */     if (destString == null) {
/* 1914 */       JMSException traceRet1 = newJMSException(1001);
/* 1915 */       if (Trace.isOn) {
/* 1916 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJmsFolder(boolean)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1919 */       throw traceRet1;
/*      */     } 
/* 1921 */     formatElement("Dst", destString, rfhstr);
/*      */     
/* 1923 */     String rToString = getJMSReplyToAsString();
/* 1924 */     if (rToString != null)
/*      */     {
/* 1926 */       formatElement("Rto", rToString, rfhstr);
/*      */     }
/*      */     
/* 1929 */     if (this.timestamp >= 0L) {
/*      */ 
/*      */       
/* 1932 */       rfhstr.append("<Tms>");
/* 1933 */       rfhstr.append(String.valueOf(this.timestamp));
/* 1934 */       rfhstr.append("</Tms>");
/*      */     } 
/*      */     
/* 1937 */     if (this.expiration != 0L) {
/*      */       
/* 1939 */       rfhstr.append("<Exp>");
/* 1940 */       rfhstr.append(String.valueOf(this.expiration));
/* 1941 */       rfhstr.append("</Exp>");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1951 */     if (this.correlationId != null && !this.correlationId.startsWith("ID:")) {
/* 1952 */       formatElement("Cid", this.correlationId, rfhstr);
/*      */     }
/*      */     
/* 1955 */     if (this.priority != 4) {
/*      */       
/* 1957 */       rfhstr.append("<Pri>");
/* 1958 */       rfhstr.append(this.priority);
/* 1959 */       rfhstr.append("</Pri>");
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
/* 1972 */     if ((!persistenceFromMD && !this.hideDeliveryMode) || (persistenceFromMD == true && this.deliveryMode != 2)) {
/*      */       
/* 1974 */       rfhstr.append("<Dlv>");
/* 1975 */       rfhstr.append(this.deliveryMode);
/* 1976 */       rfhstr.append("</Dlv>");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1983 */     Object jmsxGroupId = getObjectProperty("JMSXGroupID");
/* 1984 */     if (jmsxGroupId instanceof String) {
/* 1985 */       formatElement("Gid", jmsxGroupId, rfhstr);
/*      */     }
/*      */ 
/*      */     
/* 1989 */     Object jmsxGroupSeq = getObjectProperty("JMSXGroupSeq");
/* 1990 */     if (jmsxGroupSeq instanceof Integer) {
/* 1991 */       formatElement("Seq", "", jmsxGroupSeq.toString(), rfhstr);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1997 */     rfhstr.append("</jms>");
/*      */     
/* 1999 */     String traceRet2 = rfhstr.toString();
/* 2000 */     if (Trace.isOn) {
/* 2001 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJmsFolder(boolean)", traceRet2);
/*      */     }
/*      */     
/* 2004 */     return traceRet2;
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
/*      */   public String _getMcdFolder() throws JMSException {
/* 2020 */     if (Trace.isOn) {
/* 2021 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2027 */     if (this.msDomain == null && this.type == null) {
/* 2028 */       if (this.messageClass == "jms_text" && this.isNullMessage == true) {
/* 2029 */         if (Trace.isOn) {
/* 2030 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_text</Msd><msgbody xsi:nil=\"true\"></msgbody></mcd>", 1);
/*      */         }
/*      */         
/* 2033 */         return "<mcd><Msd>jms_text</Msd><msgbody xsi:nil=\"true\"></msgbody></mcd>";
/*      */       } 
/* 2035 */       if ("jms_text".equals(this.messageClass) && !this.isNullMessage) {
/* 2036 */         if (Trace.isOn) {
/* 2037 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_text</Msd></mcd>", 2);
/*      */         }
/*      */         
/* 2040 */         return "<mcd><Msd>jms_text</Msd></mcd>";
/*      */       } 
/* 2042 */       if ("jms_bytes".equals(this.messageClass)) {
/* 2043 */         if (Trace.isOn) {
/* 2044 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_bytes</Msd></mcd>", 3);
/*      */         }
/*      */         
/* 2047 */         return "<mcd><Msd>jms_bytes</Msd></mcd>";
/*      */       } 
/* 2049 */       if ("jms_none".equals(this.messageClass)) {
/* 2050 */         if (Trace.isOn) {
/* 2051 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_none</Msd></mcd>", 4);
/*      */         }
/*      */         
/* 2054 */         return "<mcd><Msd>jms_none</Msd></mcd>";
/*      */       } 
/* 2056 */       if ("jms_object".equals(this.messageClass)) {
/* 2057 */         if (Trace.isOn) {
/* 2058 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_object</Msd></mcd>", 5);
/*      */         }
/*      */         
/* 2061 */         return "<mcd><Msd>jms_object</Msd></mcd>";
/*      */       } 
/* 2063 */       if ("jms_map".equals(this.messageClass)) {
/* 2064 */         if (Trace.isOn) {
/* 2065 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_map</Msd></mcd>", 6);
/*      */         }
/*      */         
/* 2068 */         return "<mcd><Msd>jms_map</Msd></mcd>";
/*      */       } 
/* 2070 */       if ("jms_stream".equals(this.messageClass)) {
/* 2071 */         if (Trace.isOn) {
/* 2072 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", "<mcd><Msd>jms_stream</Msd></mcd>", 7);
/*      */         }
/*      */         
/* 2075 */         return "<mcd><Msd>jms_stream</Msd></mcd>";
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2082 */       if (Trace.isOn) {
/* 2083 */         Trace.traceData(this, "Unhandled message class type in _getMcdFolder() - " + this.messageClass, null);
/*      */       }
/* 2085 */       JMSException traceRet1 = newJMSException(1001);
/* 2086 */       if (Trace.isOn) {
/* 2087 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 2090 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 2094 */     StringBuffer rfhstr = new StringBuffer(40);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2099 */     rfhstr.append("<mcd><Msd>");
/*      */ 
/*      */ 
/*      */     
/* 2103 */     if (this.msDomain != null) {
/* 2104 */       rfhstr.append(this.msDomain);
/* 2105 */       rfhstr.append("</Msd>");
/*      */       
/* 2107 */       if (this.msSet != null && this.msSet.length() > 0) {
/* 2108 */         formatElement("Set", this.msSet, rfhstr);
/*      */       }
/*      */       
/* 2111 */       if (this.msType != null && this.msType.length() > 0) {
/* 2112 */         formatElement("Type", this.msType, rfhstr);
/*      */       }
/*      */       
/* 2115 */       if (this.msFormat != null && this.msFormat.length() > 0) {
/* 2116 */         formatElement("Fmt", this.msFormat, rfhstr);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 2127 */       rfhstr.append(this.messageClass);
/* 2128 */       rfhstr.append("</Msd>");
/*      */ 
/*      */       
/* 2131 */       if (this.type != null) {
/* 2132 */         formatElement("Type", this.type, rfhstr);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2137 */     if ("jms_text".equals(this.messageClass) && this.isNullMessage == true) {
/* 2138 */       rfhstr.append("<msgbody xsi:nil=\"true\"></msgbody>");
/*      */     }
/*      */ 
/*      */     
/* 2142 */     rfhstr.append("</mcd>");
/*      */     
/* 2144 */     String traceRet2 = rfhstr.toString();
/* 2145 */     if (Trace.isOn) {
/* 2146 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMcdFolder()", traceRet2, 8);
/*      */     }
/*      */     
/* 2149 */     return traceRet2;
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
/*      */   public String _getPscFolder() throws JMSException {
/* 2166 */     if (Trace.isOn) {
/* 2167 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getPscFolder()");
/*      */     }
/*      */ 
/*      */     
/* 2171 */     if (this.psTopic == null || this.psConnID == null) {
/*      */ 
/*      */       
/* 2174 */       String traceRet2 = "<psc></psc>";
/* 2175 */       if (Trace.isOn) {
/* 2176 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getPscFolder()", traceRet2, 1);
/*      */       }
/*      */       
/* 2179 */       return traceRet2;
/*      */     } 
/*      */     
/* 2182 */     StringBuffer rfhstr = new StringBuffer(128);
/*      */ 
/*      */     
/* 2185 */     rfhstr.append("<psc><Command>Publish</Command><PubOpt>None</PubOpt>");
/*      */ 
/*      */     
/* 2188 */     rfhstr.append("<Topic>");
/* 2189 */     backReference(rfhstr, this.psTopic);
/* 2190 */     rfhstr.append("</Topic>");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2196 */     rfhstr.append("<UNIQUE_CONNECTION_ID>" + this.psConnID + "</UNIQUE_CONNECTION_ID></psc>");
/*      */     
/* 2198 */     String traceRet1 = rfhstr.toString();
/* 2199 */     if (Trace.isOn) {
/* 2200 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getPscFolder()", traceRet1, 2);
/*      */     }
/*      */     
/* 2203 */     return traceRet1;
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
/*      */   public long _getTimeToLive() {
/* 2215 */     if (Trace.isOn) {
/* 2216 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getTimeToLive()");
/*      */     }
/*      */     
/* 2219 */     if (Trace.isOn) {
/* 2220 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getTimeToLive()", 
/* 2221 */           Long.valueOf(this.timeToLive));
/*      */     }
/* 2223 */     return this.timeToLive;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String _getUsrFolder() throws JMSException {
/* 2234 */     if (Trace.isOn) {
/* 2235 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getUsrFolder()");
/*      */     }
/*      */     
/* 2238 */     StringBuffer rfhstr = new StringBuffer(40);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2243 */     Iterator<String> propNames = this.properties.keySet().iterator();
/*      */     
/* 2245 */     rfhstr.append("<usr>");
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
/* 2257 */     while (propNames.hasNext()) {
/* 2258 */       String name = propNames.next();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2264 */       if (!name.startsWith("JMS")) {
/*      */         
/* 2266 */         Object value = getObjectProperty(name);
/*      */ 
/*      */         
/* 2269 */         formatElement(name, value, rfhstr);
/*      */       } 
/*      */     } 
/*      */     
/* 2273 */     rfhstr.append("</usr>");
/*      */     
/* 2275 */     String traceRet1 = rfhstr.toString();
/* 2276 */     if (Trace.isOn) {
/* 2277 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getUsrFolder()", traceRet1);
/*      */     }
/*      */     
/* 2280 */     return traceRet1;
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
/*      */   public String _getMQExtFolder() throws JMSException {
/* 2298 */     if (Trace.isOn) {
/* 2299 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMQExtFolder()");
/*      */     }
/*      */     
/* 2302 */     StringBuffer rfhstr = null;
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
/* 2315 */     String armCorrelator1 = _getJMSIBMArmCorrelator();
/* 2316 */     String wrmCorrelator1 = _getJMSIBMWrmCorrelator();
/* 2317 */     if (armCorrelator1 != null || wrmCorrelator1 != null) {
/* 2318 */       rfhstr = new StringBuffer(40);
/* 2319 */       rfhstr.append("<mqext>");
/* 2320 */       if (armCorrelator1 != null) {
/* 2321 */         formatElement("Arm", armCorrelator1, rfhstr);
/*      */       }
/* 2323 */       if (wrmCorrelator1 != null) {
/* 2324 */         formatElement("Wrm", wrmCorrelator1, rfhstr);
/*      */       }
/* 2326 */       rfhstr.append("</mqext>");
/*      */     } 
/*      */     
/* 2329 */     String traceRet1 = (rfhstr != null) ? rfhstr.toString() : null;
/* 2330 */     if (Trace.isOn) {
/* 2331 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getMQExtFolder()", traceRet1);
/*      */     }
/*      */     
/* 2334 */     return traceRet1;
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
/*      */   public static String _idToString(byte[] id) {
/* 2348 */     if (Trace.isOn) {
/* 2349 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_idToString(byte [ ])", new Object[] { id });
/*      */     }
/*      */ 
/*      */     
/* 2353 */     if (id == null) {
/* 2354 */       if (Trace.isOn) {
/* 2355 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_idToString(byte [ ])", null, 1);
/*      */       }
/*      */       
/* 2358 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 2362 */     int len = id.length;
/* 2363 */     char[] idchars = new char[3 + 2 * len];
/* 2364 */     idchars[0] = 'I';
/* 2365 */     idchars[1] = 'D';
/* 2366 */     idchars[2] = ':';
/*      */     
/* 2368 */     int sum = 0;
/*      */ 
/*      */     
/* 2371 */     for (int i = 0; i < len; i++) {
/* 2372 */       int binByte = id[i];
/*      */       
/* 2374 */       if (binByte < 0) {
/* 2375 */         binByte += 256;
/*      */       }
/*      */       
/* 2378 */       sum += binByte;
/* 2379 */       idchars[2 * i + 3] = BIN2HEX[binByte / 16];
/* 2380 */       idchars[2 * i + 4] = BIN2HEX[binByte % 16];
/*      */     } 
/*      */ 
/*      */     
/* 2384 */     if (sum == 0) {
/* 2385 */       if (Trace.isOn) {
/* 2386 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_idToString(byte [ ])", null, 2);
/*      */       }
/*      */       
/* 2389 */       return null;
/*      */     } 
/*      */     
/* 2392 */     String traceRet1 = new String(idchars);
/* 2393 */     if (Trace.isOn) {
/* 2394 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_idToString(byte [ ])", traceRet1, 3);
/*      */     }
/*      */     
/* 2397 */     return traceRet1;
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
/*      */   public void _parseJmsFolder(MQSession destFactory, String s, boolean persistenceFromMD) throws JMSException {
/* 2435 */     if (Trace.isOn) {
/* 2436 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolder(MQSession,String,boolean)", new Object[] { destFactory, s, 
/*      */             
/* 2438 */             Boolean.valueOf(persistenceFromMD) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2447 */     this.deliveryMode = -2;
/* 2448 */     StringTokenizer strtok = new StringTokenizer(s, "<>");
/*      */     
/* 2450 */     if (!strtok.nextToken().equals("jms")) {
/* 2451 */       JMSException traceRet1 = newJMSException(1001);
/* 2452 */       if (Trace.isOn) {
/* 2453 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolder(MQSession,String,boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 2456 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2459 */     String name = "";
/* 2460 */     while (!name.equals("/jms")) {
/*      */ 
/*      */       
/* 2463 */       strtok.nextToken("<");
/*      */ 
/*      */       
/* 2466 */       String token = strtok.nextToken("<");
/* 2467 */       int to = token.indexOf(">");
/* 2468 */       name = token.substring(0, to);
/* 2469 */       String value = token.substring(to + 1);
/*      */ 
/*      */       
/* 2472 */       if (name.charAt(0) != '/') {
/* 2473 */         if (name.startsWith("Cid")) {
/* 2474 */           setJMSCorrelationID((String)deformatElement("'string'", value));
/*      */         }
/* 2476 */         else if (name.startsWith("Tms")) {
/* 2477 */           this.timestamp = Long.parseLong(value);
/*      */         }
/* 2479 */         else if (name.startsWith("Exp")) {
/* 2480 */           this.expiration = Long.parseLong(value);
/*      */         }
/* 2482 */         else if (name.startsWith("Rto")) {
/* 2483 */           String tmp = (String)deformatElement("'string'", value);
/* 2484 */           _setJMSReplyToAsString(destFactory, tmp);
/*      */         }
/* 2486 */         else if (name.startsWith("Dst")) {
/* 2487 */           String tmp = (String)deformatElement("'string'", value);
/* 2488 */           _setJMSDestinationAsString(destFactory, tmp);
/*      */         }
/* 2490 */         else if (!persistenceFromMD && name.startsWith("Dlv")) {
/* 2491 */           this.deliveryMode = Integer.parseInt(value);
/*      */         }
/* 2493 */         else if (name.startsWith("Gid")) {
/* 2494 */           setObjectProperty("JMSXGroupID", deformatElement("'string'", value));
/*      */         }
/* 2496 */         else if (name.startsWith("Seq")) {
/* 2497 */           setObjectProperty("JMSXGroupSeq", deformatElement("'i4'", value));
/*      */         } 
/*      */ 
/*      */         
/* 2501 */         token = strtok.nextToken(">");
/*      */ 
/*      */         
/* 2504 */         if (token.charAt(1) != '/') {
/* 2505 */           JMSException traceRet2 = newMessageFormatException(1012);
/* 2506 */           if (Trace.isOn) {
/* 2507 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolder(MQSession,String,boolean)", (Throwable)traceRet2, 2);
/*      */           }
/*      */           
/* 2510 */           throw traceRet2;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2515 */     if (Trace.isOn) {
/* 2516 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolder(MQSession,String,boolean)");
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
/*      */   public void _parseJmsFolderUtf8(byte[] rawData, int offset, int length, MQSession destFactory, boolean persistenceFromMD) throws JMSException {
/* 2553 */     if (Trace.isOn) {
/* 2554 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolderUtf8(byte [ ],int,int,MQSession,boolean)", new Object[] { rawData, 
/*      */             
/* 2556 */             Integer.valueOf(offset), Integer.valueOf(length), destFactory, 
/* 2557 */             Boolean.valueOf(persistenceFromMD) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2564 */       this.deliveryMode = -2;
/*      */       
/* 2566 */       int jmsLen = length;
/*      */ 
/*      */ 
/*      */       
/* 2570 */       while (rawData[offset + jmsLen - 1] != 62) {
/* 2571 */         jmsLen--;
/*      */       }
/*      */ 
/*      */       
/* 2575 */       byte[] openJmsTag = { 60, 106, 109, 115, 62 };
/* 2576 */       byte[] closeJmsTag = { 60, 47, 106, 109, 115, 62 };
/* 2577 */       for (int i = 0; i < openJmsTag.length; i++) {
/* 2578 */         if (rawData[offset + i] != openJmsTag[i]) {
/* 2579 */           JMSException traceRet1 = newMessageFormatException(1012);
/* 2580 */           if (Trace.isOn) {
/* 2581 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolderUtf8(byte [ ],int,int,MQSession,boolean)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/* 2584 */           throw traceRet1;
/*      */         } 
/*      */       } 
/* 2587 */       int j = offset + jmsLen - closeJmsTag.length;
/* 2588 */       for (int k = 0; k < closeJmsTag.length; k++) {
/* 2589 */         if (rawData[j] != closeJmsTag[k]) {
/* 2590 */           JMSException traceRet2 = newMessageFormatException(1012);
/* 2591 */           if (Trace.isOn) {
/* 2592 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolderUtf8(byte [ ],int,int,MQSession,boolean)", (Throwable)traceRet2, 2);
/*      */           }
/*      */           
/* 2595 */           throw traceRet2;
/*      */         } 
/* 2597 */         j++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2604 */       int TAG_VALUE_DST = 4486004;
/* 2605 */       int TAG_VALUE_TMS = 5533043;
/* 2606 */       int TAG_VALUE_EXP = 4552816;
/* 2607 */       int TAG_VALUE_PRI = 5272169;
/* 2608 */       int TAG_VALUE_DLV = 4484214;
/* 2609 */       int TAG_VALUE_CID = 4417892;
/* 2610 */       int TAG_VALUE_RTO = 5403759;
/* 2611 */       int TAG_VALUE_GID = 4680036;
/* 2612 */       int TAG_VALUE_SEQ = 5465457;
/* 2613 */       int index = offset + openJmsTag.length;
/* 2614 */       int folderEnd = offset + jmsLen - closeJmsTag.length;
/* 2615 */       while (index < folderEnd) {
/*      */         String dstString, cidString, rtoString, gidString; int seqNum;
/* 2617 */         while (rawData[index] != 60) {
/* 2618 */           index++;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2623 */         index++;
/* 2624 */         int elementNameValue = 0;
/* 2625 */         boolean endOfName = false;
/*      */         
/* 2627 */         while (rawData[index] != 62) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2635 */           if (rawData[index] == 32) {
/* 2636 */             endOfName = true;
/*      */           }
/*      */           
/* 2639 */           if (!endOfName) {
/* 2640 */             char c = (char)rawData[index];
/* 2641 */             elementNameValue = (elementNameValue << 8) + c;
/*      */           } 
/* 2643 */           index++;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2648 */         int elementStart = ++index;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2656 */         while (rawData[index] != 62) {
/* 2657 */           index++;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2664 */         int elementEnd = index;
/* 2665 */         while (rawData[elementEnd] != 60) {
/* 2666 */           elementEnd--;
/*      */         }
/* 2668 */         int elementLen = elementEnd - elementStart;
/*      */ 
/*      */         
/* 2671 */         while (rawData[index] != 60) {
/* 2672 */           index++;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2677 */         switch (elementNameValue) {
/*      */           case 4486004:
/* 2679 */             dstString = safeUTF8ToString(rawData, elementStart, elementLen);
/* 2680 */             _setJMSDestinationAsString(destFactory, dstString);
/*      */             continue;
/*      */           case 5533043:
/* 2683 */             this.timestamp = utf8ToLong(rawData, elementStart, elementLen);
/*      */             continue;
/*      */           case 4552816:
/* 2686 */             this.expiration = utf8ToLong(rawData, elementStart, elementLen);
/*      */             continue;
/*      */ 
/*      */           
/*      */           case 5272169:
/*      */             continue;
/*      */           
/*      */           case 4484214:
/* 2694 */             if (!persistenceFromMD) {
/* 2695 */               this.deliveryMode = utf8ToInt(rawData, elementStart, elementLen);
/*      */             }
/*      */             continue;
/*      */           case 4417892:
/* 2699 */             cidString = unsafeUTF8ToString(rawData, elementStart, elementLen);
/* 2700 */             setJMSCorrelationID(cidString);
/*      */             continue;
/*      */           case 5403759:
/* 2703 */             rtoString = safeUTF8ToString(rawData, elementStart, elementLen);
/* 2704 */             _setJMSReplyToAsString(destFactory, rtoString);
/*      */             continue;
/*      */           case 4680036:
/* 2707 */             gidString = safeUTF8ToString(rawData, elementStart, elementLen);
/* 2708 */             setObjectProperty("JMSXGroupID", gidString);
/*      */             continue;
/*      */           case 5465457:
/* 2711 */             seqNum = utf8ToInt(rawData, elementStart, elementLen);
/* 2712 */             setObjectProperty("JMSXGroupSeq", Integer.valueOf(seqNum));
/*      */             continue;
/*      */         } 
/*      */ 
/*      */         
/* 2717 */         if (Trace.isOn) {
/* 2718 */           Trace.traceData(this, "unknown element name in jms folder: " + Integer.toHexString(elementNameValue), null);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 2724 */     catch (ArrayIndexOutOfBoundsException aioobe) {
/* 2725 */       if (Trace.isOn) {
/* 2726 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolderUtf8(byte [ ],int,int,MQSession,boolean)", aioobe);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2734 */       JMSException traceRet3 = newMessageFormatException(1012);
/* 2735 */       if (Trace.isOn) {
/* 2736 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolderUtf8(byte [ ],int,int,MQSession,boolean)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 2739 */       throw traceRet3;
/*      */     } 
/*      */     
/* 2742 */     if (Trace.isOn) {
/* 2743 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseJmsFolderUtf8(byte [ ],int,int,MQSession,boolean)");
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
/*      */   
/*      */   public void _parseMQExtFolder(String s) throws JMSException {
/* 2762 */     if (Trace.isOn) {
/* 2763 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolder(String)", new Object[] { s });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2770 */     StringTokenizer strtok = new StringTokenizer(s, "<>");
/*      */     
/* 2772 */     if (!strtok.nextToken().equals("mqext")) {
/* 2773 */       JMSException traceRet1 = newJMSException(1001);
/* 2774 */       if (Trace.isOn) {
/* 2775 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolder(String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 2778 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2781 */     String name = strtok.nextToken();
/* 2782 */     while (!name.equals("/mqext")) {
/*      */       
/* 2784 */       String value, token = strtok.nextToken();
/*      */       
/* 2786 */       if (token.charAt(0) == '/') {
/* 2787 */         value = "";
/*      */       } else {
/*      */         
/* 2790 */         value = token;
/*      */       } 
/*      */       
/* 2793 */       if (name.startsWith("Arm")) {
/* 2794 */         _setJMSIBMArmCorrelator((String)deformatElement("'string'", value));
/*      */       }
/* 2796 */       else if (name.startsWith("Wrm")) {
/* 2797 */         _setJMSIBMWrmCorrelator((String)deformatElement("'string'", value));
/*      */       } 
/*      */ 
/*      */       
/* 2801 */       if (token.charAt(0) != '/') {
/* 2802 */         token = strtok.nextToken();
/*      */       }
/*      */ 
/*      */       
/* 2806 */       if (token.charAt(0) != '/') {
/* 2807 */         JMSException traceRet2 = newMessageFormatException(1012);
/* 2808 */         if (Trace.isOn) {
/* 2809 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolder(String)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 2812 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2817 */       name = strtok.nextToken();
/*      */     } 
/*      */     
/* 2820 */     if (Trace.isOn) {
/* 2821 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolder(String)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _parseMQExtFolderUtf8(byte[] rawData, int offset, int length1) throws JMSException {
/* 2843 */     if (Trace.isOn) {
/* 2844 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolderUtf8(byte [ ],int,int)", new Object[] { rawData, 
/* 2845 */             Integer.valueOf(offset), 
/* 2846 */             Integer.valueOf(length1) });
/*      */     }
/* 2848 */     int extLength = length1;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2853 */       while (rawData[offset + extLength - 1] != 62) {
/* 2854 */         extLength--;
/*      */       }
/*      */ 
/*      */       
/* 2858 */       byte[] openJmsTag = { 60, 109, 113, 101, 120, 116, 62 };
/* 2859 */       byte[] closeJmsTag = { 60, 47, 109, 113, 101, 120, 116, 62 };
/* 2860 */       for (int i = 0; i < openJmsTag.length; i++) {
/* 2861 */         if (rawData[offset + i] != openJmsTag[i]) {
/* 2862 */           JMSException traceRet1 = newMessageFormatException(1012);
/* 2863 */           if (Trace.isOn) {
/* 2864 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolderUtf8(byte [ ],int,int)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/* 2867 */           throw traceRet1;
/*      */         } 
/*      */       } 
/* 2870 */       int j = offset + extLength - closeJmsTag.length;
/* 2871 */       for (int k = 0; k < closeJmsTag.length; k++) {
/* 2872 */         if (rawData[j] != closeJmsTag[k]) {
/* 2873 */           JMSException traceRet2 = newMessageFormatException(1012);
/* 2874 */           if (Trace.isOn) {
/* 2875 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolderUtf8(byte [ ],int,int)", (Throwable)traceRet2, 2);
/*      */           }
/*      */           
/* 2878 */           throw traceRet2;
/*      */         } 
/*      */         
/* 2881 */         j++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2888 */       int TAG_VALUE_ARM = 4289133;
/* 2889 */       int TAG_VALUE_WAS = 5730925;
/*      */       
/* 2891 */       int index = offset + openJmsTag.length;
/* 2892 */       int folderEnd = offset + extLength - closeJmsTag.length;
/* 2893 */       while (index < folderEnd) {
/*      */         String armString, wasString;
/* 2895 */         while (rawData[index] != 60) {
/* 2896 */           index++;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2901 */         index++;
/* 2902 */         int elementNameValue = 0;
/* 2903 */         boolean endOfName = false;
/*      */         
/* 2905 */         while (rawData[index] != 62) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2913 */           if (rawData[index] == 32) {
/* 2914 */             endOfName = true;
/*      */           }
/*      */           
/* 2917 */           if (!endOfName) {
/* 2918 */             char c = (char)rawData[index];
/* 2919 */             elementNameValue = (elementNameValue << 8) + c;
/*      */           } 
/* 2921 */           index++;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2926 */         int elementStart = ++index;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2934 */         while (rawData[index] != 62) {
/* 2935 */           index++;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2942 */         int elementEnd = index;
/* 2943 */         while (rawData[elementEnd] != 60) {
/* 2944 */           elementEnd--;
/*      */         }
/* 2946 */         int elementLen = elementEnd - elementStart;
/*      */ 
/*      */         
/* 2949 */         while (rawData[index] != 60) {
/* 2950 */           index++;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2955 */         switch (elementNameValue) {
/*      */           case 4289133:
/* 2957 */             armString = safeUTF8ToString(rawData, elementStart, elementLen);
/* 2958 */             _setJMSIBMArmCorrelator(armString);
/*      */             continue;
/*      */           case 5730925:
/* 2961 */             wasString = safeUTF8ToString(rawData, elementStart, elementLen);
/* 2962 */             _setJMSIBMWrmCorrelator(wasString);
/*      */             continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2968 */         if (Trace.isOn) {
/* 2969 */           Trace.traceData(this, "unknown element name in mqext folder: " + Integer.toHexString(elementNameValue), null);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 2975 */     catch (ArrayIndexOutOfBoundsException aioobe) {
/* 2976 */       if (Trace.isOn) {
/* 2977 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolderUtf8(byte [ ],int,int)", aioobe);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2985 */       JMSException traceRet3 = newMessageFormatException(1012);
/* 2986 */       if (Trace.isOn) {
/* 2987 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolderUtf8(byte [ ],int,int)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 2990 */       throw traceRet3;
/*      */     } 
/* 2992 */     if (Trace.isOn) {
/* 2993 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMQExtFolderUtf8(byte [ ],int,int)");
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
/*      */   private String safeUTF8ToString(byte[] data, int offset, int length) throws JMSException {
/* 3030 */     if (Trace.isOn) {
/* 3031 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "safeUTF8ToString(byte [ ],int,int)", new Object[] { data, 
/* 3032 */             Integer.valueOf(offset), 
/* 3033 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */     
/* 3037 */     int ESC_AMP = 6385008;
/* 3038 */     int ESC_GT = 26484;
/* 3039 */     int ESC_LT = 27764;
/* 3040 */     int ESC_APOS = 1634758515;
/* 3041 */     int ESC_QUOT = 1903521652;
/*      */     
/* 3043 */     char[] ch = new char[length];
/*      */ 
/*      */ 
/*      */     
/* 3047 */     int rawIndex = offset;
/* 3048 */     int charIndex = 0;
/* 3049 */     boolean inEscape = false;
/* 3050 */     int escapeVal = 0;
/* 3051 */     while (rawIndex - offset < length) {
/* 3052 */       byte c = data[rawIndex];
/*      */ 
/*      */ 
/*      */       
/* 3056 */       if (inEscape) {
/*      */         
/* 3058 */         if (c == 59) {
/* 3059 */           JMSException traceRet1; inEscape = false;
/*      */           
/* 3061 */           switch (escapeVal) {
/*      */             case 6385008:
/* 3063 */               ch[charIndex] = '&';
/*      */               break;
/*      */             case 26484:
/* 3066 */               ch[charIndex] = '>';
/*      */               break;
/*      */             case 27764:
/* 3069 */               ch[charIndex] = '<';
/*      */               break;
/*      */             case 1634758515:
/* 3072 */               ch[charIndex] = '\'';
/*      */               break;
/*      */             case 1903521652:
/* 3075 */               ch[charIndex] = '"';
/*      */               break;
/*      */             default:
/* 3078 */               traceRet1 = newMessageFormatException(1012);
/* 3079 */               if (Trace.isOn) {
/* 3080 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "safeUTF8ToString(byte [ ],int,int)", (Throwable)traceRet1);
/*      */               }
/*      */               
/* 3083 */               throw traceRet1;
/*      */           } 
/*      */           
/* 3086 */           charIndex++;
/*      */         }
/*      */         else {
/*      */           
/* 3090 */           if (c < 0) {
/* 3091 */             c = (byte)(c + 256);
/*      */           }
/* 3093 */           escapeVal <<= 8;
/* 3094 */           escapeVal += c;
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 3099 */       else if (c == 38) {
/* 3100 */         inEscape = true;
/* 3101 */         escapeVal = 0;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3106 */         ch[charIndex] = (char)data[rawIndex];
/* 3107 */         charIndex++;
/*      */       } 
/* 3109 */       rawIndex++;
/*      */     } 
/*      */     
/* 3112 */     String traceRet2 = new String(ch, 0, charIndex);
/* 3113 */     if (Trace.isOn) {
/* 3114 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "safeUTF8ToString(byte [ ],int,int)", traceRet2);
/*      */     }
/*      */     
/* 3117 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String unsafeUTF8ToString(byte[] data, int offset, int length) throws JMSException {
/* 3143 */     if (Trace.isOn) {
/* 3144 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", new Object[] { data, 
/* 3145 */             Integer.valueOf(offset), 
/* 3146 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3153 */       for (int i = 0; i < length; i++) {
/* 3154 */         if (data[i + offset] < 0) {
/* 3155 */           String traceRet1 = expandRefs(new String(data, offset, length, "UTF8"));
/* 3156 */           if (Trace.isOn) {
/* 3157 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", traceRet1, 1);
/*      */           }
/*      */           
/* 3160 */           return traceRet1;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3166 */       String traceRet2 = safeUTF8ToString(data, offset, length);
/* 3167 */       if (Trace.isOn) {
/* 3168 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", traceRet2, 2);
/*      */       }
/*      */       
/* 3171 */       return traceRet2;
/*      */     }
/* 3173 */     catch (JMSException je) {
/* 3174 */       if (Trace.isOn) {
/* 3175 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/* 3179 */       if (Trace.isOn) {
/* 3180 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 3183 */       throw je;
/*      */     }
/* 3185 */     catch (UnsupportedEncodingException ue) {
/* 3186 */       if (Trace.isOn) {
/* 3187 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", ue, 2);
/*      */       }
/*      */       
/* 3190 */       JMSException traceRet3 = newMessageFormatException(1012);
/* 3191 */       if (Trace.isOn) {
/* 3192 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "unsafeUTF8ToString(byte [ ],int,int)", (Throwable)traceRet3, 2);
/*      */       }
/*      */       
/* 3195 */       throw traceRet3;
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
/*      */   private int utf8ToInt(byte[] data, int offset, int length) {
/* 3207 */     if (Trace.isOn) {
/* 3208 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "utf8ToInt(byte [ ],int,int)", new Object[] { data, 
/* 3209 */             Integer.valueOf(offset), 
/* 3210 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/* 3213 */     int end = length + offset;
/* 3214 */     int total = 0;
/* 3215 */     for (int i = offset; i < end; i++) {
/* 3216 */       int digit = data[i] - 48;
/* 3217 */       if (digit >= 0 && digit < 10) {
/* 3218 */         total = total * 10 + digit;
/*      */       } else {
/*      */         
/* 3221 */         char[] cdata = new char[length];
/* 3222 */         for (int j = 0; j < length; j++) {
/* 3223 */           cdata[j] = (char)data[offset + j];
/*      */         }
/* 3225 */         String s = new String(cdata);
/* 3226 */         int traceRet1 = Integer.parseInt(s);
/* 3227 */         if (Trace.isOn) {
/* 3228 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "utf8ToInt(byte [ ],int,int)", 
/* 3229 */               Integer.valueOf(traceRet1), 1);
/*      */         }
/* 3231 */         return traceRet1;
/*      */       } 
/*      */     } 
/* 3234 */     if (Trace.isOn) {
/* 3235 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "utf8ToInt(byte [ ],int,int)", 
/* 3236 */           Integer.valueOf(total), 2);
/*      */     }
/* 3238 */     return total;
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
/*      */   private long utf8ToLong(byte[] data, int offset, int length) {
/* 3251 */     if (Trace.isOn) {
/* 3252 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "utf8ToLong(byte [ ],int,int)", new Object[] { data, 
/* 3253 */             Integer.valueOf(offset), 
/* 3254 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/* 3257 */     int end = length + offset;
/* 3258 */     long total = 0L;
/* 3259 */     for (int i = offset; i < end; i++) {
/* 3260 */       int digit = data[i] - 48;
/* 3261 */       if (digit >= 0 && digit < 10) {
/* 3262 */         total = total * 10L + digit;
/*      */       } else {
/*      */         
/* 3265 */         char[] cdata = new char[length];
/* 3266 */         for (int j = 0; j < length; j++) {
/* 3267 */           cdata[j] = (char)data[offset + j];
/*      */         }
/* 3269 */         String s = new String(cdata);
/* 3270 */         long traceRet1 = Long.parseLong(s);
/* 3271 */         if (Trace.isOn) {
/* 3272 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "utf8ToLong(byte [ ],int,int)", 
/* 3273 */               Long.valueOf(traceRet1), 1);
/*      */         }
/* 3275 */         return traceRet1;
/*      */       } 
/*      */     } 
/* 3278 */     if (Trace.isOn) {
/* 3279 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "utf8ToLong(byte [ ],int,int)", 
/* 3280 */           Long.valueOf(total), 2);
/*      */     }
/* 3282 */     return total;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JMSMessage _parseMcdFolder(ProviderSession session, JMSStringResources jmsStrings, String s, String fbClass, boolean forceBytesMessage) throws JMSException {
/* 3306 */     if (Trace.isOn) {
/* 3307 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolder(ProviderSession,JMSStringResources,String,String,boolean)", new Object[] { session, jmsStrings, s, fbClass, 
/*      */             
/* 3309 */             Boolean.valueOf(forceBytesMessage) });
/*      */     }
/* 3311 */     JMSMessage msg = null;
/*      */ 
/*      */     
/* 3314 */     if (forceBytesMessage == true) {
/* 3315 */       msg = (JMSMessage)((MQSession)session).createBytesMessage();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3321 */     String jmsType = null;
/*      */     
/* 3323 */     String msDomain = null;
/* 3324 */     String msSet = null;
/* 3325 */     String msFormat = null;
/*      */ 
/*      */     
/* 3328 */     boolean isNullMsgFlag = false;
/*      */     
/* 3330 */     StringTokenizer strtok = new StringTokenizer(s, "<>");
/* 3331 */     if (!strtok.nextToken().equals("mcd")) {
/* 3332 */       msg = new JMSNullMessage(jmsStrings);
/*      */       
/* 3334 */       JMSException traceRet1 = msg.newMessageFormatException(1012);
/* 3335 */       if (Trace.isOn) {
/* 3336 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolder(ProviderSession,JMSStringResources,String,String,boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */       
/* 3340 */       throw traceRet1;
/*      */     } 
/* 3342 */     String name = strtok.nextToken();
/*      */     
/* 3344 */     while (!name.startsWith("/mcd")) {
/* 3345 */       String token; boolean assumingTypeEmpty = false;
/*      */       
/* 3347 */       boolean msgbodyField = false;
/*      */       
/* 3349 */       String value = strtok.nextToken();
/*      */       
/* 3351 */       if (name.startsWith("msgbody")) {
/* 3352 */         msgbodyField = true;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3357 */         if (name.indexOf("xsi:nil=\"true\"") != -1) {
/* 3358 */           isNullMsgFlag = true;
/*      */         }
/*      */ 
/*      */         
/* 3362 */         if (name.indexOf("xsi:nil='true'") != -1) {
/* 3363 */           isNullMsgFlag = true;
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 3369 */       else if (name.startsWith("Msd") && !forceBytesMessage) {
/* 3370 */         if (value.equals("jms_none")) {
/* 3371 */           msg = (JMSMessage)((MQSession)session).createMessage();
/*      */         }
/* 3373 */         else if (value.equals("jms_text")) {
/*      */           
/* 3375 */           msg = (JMSMessage)((MQSession)session).createTextMessage();
/* 3376 */           msg.isNullMessage = false;
/*      */         }
/* 3378 */         else if (value.equals("jms_bytes")) {
/* 3379 */           msg = (JMSMessage)((MQSession)session).createBytesMessage();
/*      */         }
/* 3381 */         else if (value.equals("jms_map")) {
/* 3382 */           msg = (JMSMessage)((MQSession)session).createMapMessage();
/*      */         }
/* 3384 */         else if (value.equals("jms_stream")) {
/* 3385 */           msg = (JMSMessage)((MQSession)session).createStreamMessage();
/*      */         }
/* 3387 */         else if (value.equals("jms_object")) {
/* 3388 */           msg = (JMSMessage)((MQSession)session).createObjectMessage();
/*      */         } else {
/*      */           
/* 3391 */           msDomain = value;
/* 3392 */           if ("jms_text".equals(fbClass)) {
/* 3393 */             msg = (JMSMessage)((MQSession)session).createTextMessage();
/* 3394 */             msg.isNullMessage = false;
/*      */           }
/* 3396 */           else if ("jms_bytes".equals(fbClass)) {
/* 3397 */             msg = (JMSMessage)((MQSession)session).createBytesMessage();
/*      */           } else {
/*      */             
/* 3400 */             msg = new JMSNullMessage(jmsStrings);
/*      */ 
/*      */             
/* 3403 */             JMSException traceRet2 = msg.newMessageFormatException(1014);
/* 3404 */             if (Trace.isOn) {
/* 3405 */               Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolder(ProviderSession,JMSStringResources,String,String,boolean)", (Throwable)traceRet2, 2);
/*      */             }
/*      */ 
/*      */             
/* 3409 */             throw traceRet2;
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 3414 */       } else if (name.startsWith("Type")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3421 */         if (value.equals("/Type")) {
/* 3422 */           jmsType = "";
/* 3423 */           assumingTypeEmpty = true;
/*      */         } else {
/*      */           
/* 3426 */           jmsType = value;
/*      */         }
/*      */       
/*      */       }
/* 3430 */       else if (name.startsWith("Set")) {
/*      */ 
/*      */         
/* 3433 */         if (value.equals("/Set")) {
/* 3434 */           msSet = "";
/* 3435 */           assumingTypeEmpty = true;
/*      */         } else {
/*      */           
/* 3438 */           msSet = value;
/*      */         }
/*      */       
/*      */       }
/* 3442 */       else if (name.startsWith("Fmt")) {
/*      */ 
/*      */         
/* 3445 */         if (value.equals("/Fmt")) {
/* 3446 */           msFormat = "";
/* 3447 */           assumingTypeEmpty = true;
/*      */         } else {
/*      */           
/* 3450 */           msFormat = value;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3455 */       if (msgbodyField) {
/*      */ 
/*      */         
/* 3458 */         if (name.charAt(name.length() - 1) == '/') {
/* 3459 */           assumingTypeEmpty = true;
/*      */           
/* 3461 */           token = value;
/*      */         }
/* 3463 */         else if (value.equals("/msgbody")) {
/*      */           
/* 3465 */           assumingTypeEmpty = true;
/* 3466 */           token = strtok.nextToken();
/*      */         } else {
/*      */           
/* 3469 */           token = strtok.nextToken();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 3474 */         token = strtok.nextToken();
/*      */       } 
/*      */       
/* 3477 */       if (assumingTypeEmpty) {
/* 3478 */         if (token.equals("/Type") || token.equals("/Set") || token.equals("/Fmt")) {
/*      */           
/* 3480 */           if (token.equals("/Type")) {
/* 3481 */             jmsType = value;
/*      */           }
/* 3483 */           else if (token.equals("/Set")) {
/* 3484 */             msSet = value;
/*      */           }
/* 3486 */           else if (token.equals("/Fmt")) {
/* 3487 */             msFormat = value;
/*      */           } 
/* 3489 */           name = strtok.nextToken();
/*      */           continue;
/*      */         } 
/* 3492 */         name = token;
/*      */         
/*      */         continue;
/*      */       } 
/* 3496 */       if (token.charAt(0) != '/') {
/* 3497 */         msg = new JMSNullMessage(jmsStrings);
/*      */ 
/*      */         
/* 3500 */         JMSException traceRet3 = msg.newMessageFormatException(1012);
/* 3501 */         if (Trace.isOn) {
/* 3502 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolder(ProviderSession,JMSStringResources,String,String,boolean)", (Throwable)traceRet3, 3);
/*      */         }
/*      */ 
/*      */         
/* 3506 */         throw traceRet3;
/*      */       } 
/* 3508 */       name = strtok.nextToken();
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
/* 3524 */     if (msDomain != null) {
/* 3525 */       StringBuffer sb = new StringBuffer();
/* 3526 */       sb.append("mcd://");
/* 3527 */       sb.append(msDomain);
/* 3528 */       if (msSet != null) {
/* 3529 */         sb.append('/');
/* 3530 */         sb.append(msSet);
/*      */       } 
/* 3532 */       if (jmsType != null) {
/* 3533 */         if (msSet == null) {
/* 3534 */           sb.append('/');
/*      */         }
/* 3536 */         sb.append('/');
/* 3537 */         sb.append(jmsType);
/*      */       } 
/* 3539 */       if (msFormat != null) {
/* 3540 */         sb.append("?format=");
/* 3541 */         sb.append(msFormat);
/*      */       } 
/* 3543 */       msg.setJMSType(sb.toString());
/*      */     
/*      */     }
/* 3546 */     else if (jmsType != null) {
/* 3547 */       msg.setJMSType((String)msg.deformatElement("'string'", jmsType));
/*      */     } 
/*      */ 
/*      */     
/* 3551 */     msg.isNullMessage = isNullMsgFlag;
/*      */     
/* 3553 */     if (Trace.isOn) {
/* 3554 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolder(ProviderSession,JMSStringResources,String,String,boolean)", msg);
/*      */     }
/*      */     
/* 3557 */     return msg;
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
/*      */   public static JMSMessage _parseMcdFolderUtf8(ProviderSession session, JMSStringResources jmsStrings, String fbClass, byte[] data, int off, int len, boolean forceBytesMessage) throws JMSException {
/* 3605 */     if (Trace.isOn) {
/* 3606 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolderUtf8(ProviderSession,JMSStringResources,String,byte [ ],int,int,boolean)", new Object[] { session, jmsStrings, fbClass, data, 
/*      */             
/* 3608 */             Integer.valueOf(off), 
/* 3609 */             Integer.valueOf(len), Boolean.valueOf(forceBytesMessage) });
/*      */     }
/*      */     
/* 3612 */     boolean matchFailed = false;
/* 3613 */     JMSMessage retMsg = null;
/*      */     
/* 3615 */     int mcdLen = len;
/*      */ 
/*      */ 
/*      */     
/* 3619 */     while (data[off + mcdLen - 1] != 62) {
/* 3620 */       mcdLen--;
/*      */     }
/* 3622 */     int lastByte = off + mcdLen - 1;
/*      */ 
/*      */ 
/*      */     
/* 3626 */     if (forceBytesMessage == true) {
/* 3627 */       retMsg = (JMSMessage)((MQSession)session).createBytesMessage();
/*      */     }
/* 3629 */     else if (lastByte < OPEN_MCDMSD.length + CLOSE_MSDMCD.length) {
/* 3630 */       matchFailed = true;
/*      */     }
/* 3632 */     else if (data[off + 0] == 60 && data[off + 1] == 109 && data[off + 2] == 99 && data[off + 3] == 100 && data[off + 4] == 62 && data[off + 5] == 60 && data[off + 6] == 77 && data[off + 7] == 115 && data[off + 8] == 100 && data[off + 9] == 62 && data[off + 10] == 106 && data[off + 11] == 109 && data[off + 12] == 115 && data[off + 13] == 95 && data[lastByte - 11] == 60 && data[lastByte - 10] == 47 && data[lastByte - 9] == 77 && data[lastByte - 8] == 115 && data[lastByte - 7] == 100 && data[lastByte - 6] == 62 && data[lastByte - 5] == 60 && data[lastByte - 4] == 47 && data[lastByte - 3] == 109 && data[lastByte - 2] == 99 && data[lastByte - 1] == 100) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3640 */       if (data[off + 14] == 110 && data[off + 15] == 111 && data[off + 16] == 110 && data[off + 17] == 101 && lastByte == off + "<mcd><Msd>jms_none</Msd></mcd>"
/* 3641 */         .length() - 1) {
/* 3642 */         retMsg = (JMSMessage)((MQSession)session).createMessage();
/*      */       }
/* 3644 */       else if (data[off + 14] == 116 && data[off + 15] == 101 && data[off + 16] == 120 && data[off + 17] == 116 && lastByte == off + "<mcd><Msd>jms_text</Msd></mcd>"
/* 3645 */         .length() - 1) {
/* 3646 */         retMsg = (JMSMessage)((MQSession)session).createTextMessage();
/* 3647 */         retMsg.isNullMessage = false;
/*      */       }
/* 3649 */       else if (data[off + 14] == 111 && data[off + 15] == 98 && data[off + 16] == 106 && data[off + 17] == 101 && data[off + 18] == 99 && data[off + 19] == 116 && lastByte == off + "<mcd><Msd>jms_object</Msd></mcd>"
/* 3650 */         .length() - 1) {
/* 3651 */         retMsg = (JMSMessage)((MQSession)session).createObjectMessage();
/*      */       }
/* 3653 */       else if (data[off + 14] == 109 && data[off + 15] == 97 && data[off + 16] == 112 && lastByte == off + "<mcd><Msd>jms_map</Msd></mcd>"
/* 3654 */         .length() - 1) {
/* 3655 */         retMsg = (JMSMessage)((MQSession)session).createMapMessage();
/*      */       }
/* 3657 */       else if (data[off + 14] == 115 && data[off + 15] == 116 && data[off + 16] == 114 && data[off + 17] == 101 && data[off + 18] == 97 && data[off + 19] == 109 && lastByte == off + "<mcd><Msd>jms_stream</Msd></mcd>"
/* 3658 */         .length() - 1) {
/* 3659 */         retMsg = (JMSMessage)((MQSession)session).createStreamMessage();
/*      */       }
/* 3661 */       else if (data[off + 14] == 98 && data[off + 15] == 121 && data[off + 16] == 116 && data[off + 17] == 101 && data[off + 18] == 115 && lastByte == off + "<mcd><Msd>jms_bytes</Msd></mcd>"
/* 3662 */         .length() - 1) {
/* 3663 */         retMsg = (JMSMessage)((MQSession)session).createBytesMessage();
/*      */       } else {
/*      */         
/* 3666 */         matchFailed = true;
/*      */       } 
/*      */     } else {
/*      */       
/* 3670 */       matchFailed = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3676 */     if (matchFailed) {
/* 3677 */       String mcdStr = null;
/*      */       try {
/* 3679 */         mcdStr = new String(data, off, mcdLen, "UTF8");
/*      */       }
/* 3681 */       catch (UnsupportedEncodingException ue) {
/* 3682 */         if (Trace.isOn) {
/* 3683 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolderUtf8(ProviderSession,JMSStringResources,String,byte [ ],int,int,boolean)", ue);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3690 */         int key = 1012;
/* 3691 */         MessageFormatException traceRet1 = new MessageFormatException(jmsStrings.getErrorMessage(key), jmsStrings.getNativeKey(key));
/* 3692 */         if (Trace.isOn) {
/* 3693 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolderUtf8(ProviderSession,JMSStringResources,String,byte [ ],int,int,boolean)", (Throwable)traceRet1);
/*      */         }
/*      */ 
/*      */         
/* 3697 */         throw traceRet1;
/*      */       } 
/* 3699 */       retMsg = _parseMcdFolder(session, jmsStrings, mcdStr, fbClass, forceBytesMessage);
/*      */     } 
/*      */     
/* 3702 */     if (Trace.isOn) {
/* 3703 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseMcdFolderUtf8(ProviderSession,JMSStringResources,String,byte [ ],int,int,boolean)", retMsg);
/*      */     }
/*      */ 
/*      */     
/* 3707 */     return retMsg;
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
/*      */   public void _parseUsrFolder(String s) throws JMSException {
/* 3725 */     if (Trace.isOn) {
/* 3726 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseUsrFolder(String)", new Object[] { s });
/*      */     }
/*      */ 
/*      */     
/* 3730 */     s = s.trim();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3736 */       StringTokenizer openBracketStrtok = new StringTokenizer(s, "<");
/* 3737 */       String openBracketToken = openBracketStrtok.nextToken();
/* 3738 */       openBracketToken = openBracketStrtok.nextToken();
/*      */ 
/*      */       
/* 3741 */       while (!openBracketToken.equals("/usr>"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3747 */         boolean nullElement = false;
/* 3748 */         int nullIndex = openBracketToken.indexOf(" xsi:nil");
/* 3749 */         int rightTriBracketIndex = openBracketToken.indexOf(">");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3755 */         if (nullIndex != -1 && nullIndex < rightTriBracketIndex) {
/*      */ 
/*      */           
/* 3758 */           int dtindex = openBracketToken.indexOf(" dt=");
/* 3759 */           if (dtindex != -1 && dtindex < nullIndex) {
/* 3760 */             nullIndex = dtindex;
/*      */           }
/* 3762 */           nullElement = true;
/*      */         } 
/*      */         
/* 3765 */         if (nullElement) {
/* 3766 */           setObjectProperty(openBracketToken.substring(0, nullIndex), null);
/* 3767 */           StringTokenizer closeBracketStrtok = new StringTokenizer(openBracketToken, ">");
/* 3768 */           String closeBracketToken = closeBracketStrtok.nextToken();
/* 3769 */           if (closeBracketToken.charAt(closeBracketToken.length() - 1) != '/') {
/*      */ 
/*      */ 
/*      */             
/* 3773 */             openBracketToken = openBracketStrtok.nextToken();
/* 3774 */             if (openBracketToken.charAt(0) != '/') {
/*      */               
/* 3776 */               JMSException traceRet1 = newMessageFormatException(1012);
/* 3777 */               if (Trace.isOn) {
/* 3778 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.v6.jms.internal.JMSMessage", "_parseUsrFolder(String)", (Throwable)traceRet1, 1);
/*      */               }
/*      */               
/* 3781 */               throw traceRet1;
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           String propertyName, propertyType;
/* 3786 */           StringTokenizer closeBracketStrtok = new StringTokenizer(openBracketToken, ">");
/* 3787 */           int closeBracketTokenCount = closeBracketStrtok.countTokens();
/* 3788 */           String closeBracketToken = closeBracketStrtok.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3794 */           boolean emptyElement = (closeBracketToken.charAt(closeBracketToken.length() - 1) == '/');
/*      */ 
/*      */           
/* 3797 */           int dtIndex = closeBracketToken.indexOf(" dt=");
/* 3798 */           if (dtIndex != -1) {
/* 3799 */             propertyType = closeBracketToken.substring(dtIndex + 4, closeBracketToken
/* 3800 */                 .length() - (emptyElement ? 1 : 0));
/* 3801 */             propertyName = closeBracketToken.substring(0, closeBracketToken.indexOf(' '));
/*      */           } else {
/* 3803 */             if (emptyElement) {
/* 3804 */               propertyName = closeBracketToken.substring(0, closeBracketToken.length() - 1);
/*      */             } else {
/* 3806 */               propertyName = closeBracketToken;
/*      */             } 
/* 3808 */             propertyType = "'string'";
/*      */           } 
/*      */ 
/*      */           
/* 3812 */           if (emptyElement) {
/* 3813 */             setObjectProperty(propertyName, deformatElement(propertyType, ""));
/*      */           } else {
/* 3815 */             if (closeBracketTokenCount == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3820 */               setObjectProperty(propertyName, deformatElement(propertyType, ""));
/*      */             } else {
/*      */               
/* 3823 */               String propertyValue = closeBracketStrtok.nextToken();
/*      */               
/* 3825 */               setObjectProperty(propertyName, deformatElement(propertyType, propertyValue));
/*      */             } 
/*      */ 
/*      */             
/* 3829 */             openBracketToken = openBracketStrtok.nextToken();
/* 3830 */             if (openBracketToken.charAt(0) != '/') {
/* 3831 */               JMSException traceRet2 = newMessageFormatException(1012);
/* 3832 */               if (Trace.isOn) {
/* 3833 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.v6.jms.internal.JMSMessage", "_parseUsrFolder(String)", (Throwable)traceRet2, 2);
/*      */               }
/*      */               
/* 3836 */               throw traceRet2;
/*      */             } 
/*      */           } 
/*      */         } 
/* 3840 */         openBracketToken = openBracketStrtok.nextToken();
/*      */       }
/*      */     
/* 3843 */     } catch (Exception ex) {
/* 3844 */       if (Trace.isOn) {
/* 3845 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseUsrFolder(String)", ex, 2);
/*      */       }
/*      */       
/* 3848 */       JMSException jmsEx = newMessageFormatException(1012);
/* 3849 */       jmsEx.setLinkedException(ex);
/* 3850 */       if (Trace.isOn) {
/* 3851 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseUsrFolder(String)", (Throwable)jmsEx, 5);
/*      */       }
/*      */       
/* 3854 */       throw jmsEx;
/*      */     } 
/*      */     
/* 3857 */     if (Trace.isOn) {
/* 3858 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_parseUsrFolder(String)");
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
/*      */   public void _setJMSMessageIDAsBytes(byte[] messageId1) throws JMSException {
/* 3873 */     if (Trace.isOn) {
/* 3874 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSMessageIDAsBytes(byte [ ])", new Object[] { messageId1 });
/*      */     }
/*      */ 
/*      */     
/* 3878 */     this.messageId = null;
/* 3879 */     this.nativeMessageId = messageId1;
/*      */     
/* 3881 */     if (Trace.isOn) {
/* 3882 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSMessageIDAsBytes(byte [ ])");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSXObjectProperty(String name, Object value) throws JMSException {
/* 3905 */     if (Trace.isOn) {
/* 3906 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXObjectProperty(String,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3916 */     this.properties.put(name, value);
/*      */     
/* 3918 */     if (Trace.isOn) {
/* 3919 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSXObjectProperty(String,Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setReadOnly() {
/* 3930 */     if (Trace.isOn) {
/* 3931 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setReadOnly()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3936 */     this.propertiesReadOnly = true;
/*      */     
/* 3938 */     _setBodyReadOnly();
/* 3939 */     if (Trace.isOn) {
/* 3940 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setReadOnly()");
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
/*      */ 
/*      */   
/*      */   public void _setSession(JMSAcknowledgePoint session1) {
/* 3960 */     if (Trace.isOn) {
/* 3961 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setSession(JMSAcknowledgePoint)", new Object[] { session1 });
/*      */     }
/*      */     
/* 3964 */     this.session = session1;
/* 3965 */     if (Trace.isOn) {
/* 3966 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setSession(JMSAcknowledgePoint)");
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
/*      */   
/*      */   public void _setTimeToLive(long currentTime, long timeToLive1) {
/* 3985 */     if (Trace.isOn) {
/* 3986 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setTimeToLive(long,long)", new Object[] {
/* 3987 */             Long.valueOf(currentTime), 
/* 3988 */             Long.valueOf(timeToLive1)
/*      */           });
/*      */     }
/*      */     
/* 3992 */     if (timeToLive1 == 0L) {
/* 3993 */       this.expiration = 0L;
/*      */     }
/*      */     else {
/*      */       
/* 3997 */       this.expiration = timeToLive1 + currentTime;
/*      */     } 
/*      */     
/* 4000 */     this.timeToLive = timeToLive1;
/*      */     
/* 4002 */     if (Trace.isOn) {
/* 4003 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setTimeToLive(long,long)");
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
/*      */   public byte[] _stringToId(String idString) throws JMSException {
/* 4021 */     if (Trace.isOn) {
/* 4022 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_stringToId(String)", new Object[] { idString });
/*      */     }
/*      */ 
/*      */     
/* 4026 */     byte[] traceRet1 = hexToBin(idString, 3);
/* 4027 */     if (Trace.isOn) {
/* 4028 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_stringToId(String)", traceRet1);
/*      */     }
/*      */     
/* 4031 */     return traceRet1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void acknowledge() throws JMSException {
/* 4056 */     if (Trace.isOn) {
/* 4057 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "acknowledge()");
/*      */     }
/*      */     
/* 4060 */     if (this.session == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4068 */       if (Trace.isOn) {
/* 4069 */         Trace.traceData(this, "acknowledge() not valid for this session type (session is null)", null);
/*      */       }
/*      */     } else {
/*      */       
/* 4073 */       this.session._acknowledge(this.gotByConsume);
/*      */     } 
/* 4075 */     if (Trace.isOn) {
/* 4076 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "acknowledge()");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void backReference(StringBuffer sb, String string) throws JMSException {
/* 4099 */     if (Trace.isOn) {
/* 4100 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "backReference(StringBuffer,String)", new Object[] { sb, string });
/*      */     }
/*      */ 
/*      */     
/* 4104 */     for (int i = 0; i < string.length(); i++) {
/* 4105 */       char ch = string.charAt(i);
/* 4106 */       if ('<' == ch) {
/* 4107 */         sb.append("&lt;");
/*      */       }
/* 4109 */       else if ('>' == ch) {
/* 4110 */         sb.append("&gt;");
/*      */       }
/* 4112 */       else if ('&' == ch) {
/* 4113 */         sb.append("&amp;");
/*      */       
/*      */       }
/* 4116 */       else if ('"' == ch) {
/* 4117 */         sb.append("&quot;");
/*      */       }
/* 4119 */       else if ('\'' == ch) {
/* 4120 */         sb.append("&apos;");
/*      */       
/*      */       }
/* 4123 */       else if ('?' <= ch && ch < '?') {
/*      */         
/* 4125 */         if (i + 1 >= string.length()) {
/* 4126 */           String insert = Integer.toHexString(ch) + " ?";
/* 4127 */           JMSException traceRet1 = newJMSException(1015, insert);
/* 4128 */           if (Trace.isOn) {
/* 4129 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "backReference(StringBuffer,String)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/* 4132 */           throw traceRet1;
/*      */         } 
/*      */         
/* 4135 */         int next = string.charAt(++i);
/* 4136 */         if (56320 > next || next >= 57344) {
/* 4137 */           String insert = Integer.toHexString(ch) + " " + Integer.toHexString(next);
/* 4138 */           JMSException traceRet2 = newJMSException(1015, insert);
/* 4139 */           if (Trace.isOn) {
/* 4140 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "backReference(StringBuffer,String)", (Throwable)traceRet2, 2);
/*      */           }
/*      */           
/* 4143 */           throw traceRet2;
/*      */         } 
/* 4145 */         next = (ch - 55296 << 10) + next - 56320 + 65536;
/* 4146 */         sb.append("&#x");
/* 4147 */         sb.append(Integer.toHexString(next));
/* 4148 */         sb.append(";");
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 4155 */         sb.append(ch);
/*      */       } 
/*      */     } 
/* 4158 */     if (Trace.isOn) {
/* 4159 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "backReference(StringBuffer,String)");
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
/*      */   static int binToHex(byte[] bin, int start, int length, StringBuffer hex) {
/* 4175 */     if (Trace.isOn) {
/* 4176 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "binToHex(byte [ ],int,int,StringBuffer)", new Object[] { bin, 
/* 4177 */             Integer.valueOf(start), 
/* 4178 */             Integer.valueOf(length), hex });
/*      */     }
/*      */ 
/*      */     
/* 4182 */     int sum = 0;
/*      */     
/* 4184 */     for (int i = start; i < start + length; i++) {
/* 4185 */       int binByte = bin[i];
/*      */       
/* 4187 */       if (binByte < 0) {
/* 4188 */         binByte += 256;
/*      */       }
/*      */       
/* 4191 */       sum += binByte;
/* 4192 */       hex.append(BIN2HEX[binByte / 16]);
/* 4193 */       hex.append(BIN2HEX[binByte % 16]);
/*      */     } 
/*      */     
/* 4196 */     if (Trace.isOn) {
/* 4197 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "binToHex(byte [ ],int,int,StringBuffer)", 
/* 4198 */           Integer.valueOf(sum));
/*      */     }
/* 4200 */     return sum;
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
/*      */   public void clearProperties() throws JMSException {
/* 4219 */     if (Trace.isOn) {
/* 4220 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "clearProperties()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4225 */     this.propertiesReadOnly = false;
/*      */ 
/*      */     
/* 4228 */     this.properties.clear();
/*      */     
/* 4230 */     if (Trace.isOn) {
/* 4231 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "clearProperties()");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Object deformatElement(String datatype, String value) throws JMSException {
/* 4254 */     if (Trace.isOn) {
/* 4255 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", new Object[] { datatype, value });
/*      */     }
/*      */ 
/*      */     
/* 4259 */     if (Trace.isOn) {
/* 4260 */       if (value == null) {
/* 4261 */         Trace.traceData(this, "type is " + datatype + " value is null", null);
/*      */       }
/* 4263 */       else if (value.length() <= 50) {
/*      */         
/* 4265 */         Trace.traceData(this, "type is " + datatype + " value is " + value, null);
/*      */       } else {
/*      */         
/* 4268 */         Trace.traceData(this, "type is " + datatype + " value starts " + value.substring(0, 50), null);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4274 */     char openingQuote = datatype.charAt(0);
/*      */     
/* 4276 */     if (openingQuote != '\'' && openingQuote != '"') {
/*      */       
/* 4278 */       JMSException traceRet1 = newMessageFormatException();
/* 4279 */       if (Trace.isOn) {
/* 4280 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 4283 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4289 */     String type1 = datatype.substring(1, datatype.indexOf(openingQuote, 1));
/* 4290 */     if (type1.equals("string")) {
/* 4291 */       if (value == null) {
/* 4292 */         if (Trace.isOn) {
/* 4293 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", null, 1);
/*      */         }
/*      */         
/* 4296 */         return null;
/*      */       } 
/* 4298 */       Object traceRet2 = expandRefs(value);
/* 4299 */       if (Trace.isOn) {
/* 4300 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet2, 2);
/*      */       }
/*      */       
/* 4303 */       return traceRet2;
/*      */     } 
/* 4305 */     if (type1.equals("i4")) {
/*      */       
/* 4307 */       Object traceRet3 = Integer.valueOf(value);
/* 4308 */       if (Trace.isOn) {
/* 4309 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet3, 3);
/*      */       }
/*      */       
/* 4312 */       return traceRet3;
/*      */     } 
/* 4314 */     if (type1.equals("i2")) {
/*      */       
/* 4316 */       Object traceRet4 = Short.valueOf(value);
/* 4317 */       if (Trace.isOn) {
/* 4318 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet4, 4);
/*      */       }
/*      */       
/* 4321 */       return traceRet4;
/*      */     } 
/* 4323 */     if (type1.equals("i8")) {
/*      */       
/* 4325 */       Object traceRet5 = Long.valueOf(value);
/* 4326 */       if (Trace.isOn) {
/* 4327 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet5, 5);
/*      */       }
/*      */       
/* 4330 */       return traceRet5;
/*      */     } 
/* 4332 */     if (type1.equals("int")) {
/*      */       
/* 4334 */       Object traceRet6 = Long.valueOf(value);
/* 4335 */       if (Trace.isOn) {
/* 4336 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet6, 6);
/*      */       }
/*      */       
/* 4339 */       return traceRet6;
/*      */     } 
/* 4341 */     if (type1.equals("i1")) {
/*      */       
/* 4343 */       Object traceRet7 = Byte.valueOf(value);
/* 4344 */       if (Trace.isOn) {
/* 4345 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet7, 7);
/*      */       }
/*      */       
/* 4348 */       return traceRet7;
/*      */     } 
/* 4350 */     if (type1.equals("r4")) {
/*      */       
/* 4352 */       Object traceRet8 = Float.valueOf(value);
/* 4353 */       if (Trace.isOn) {
/* 4354 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet8, 8);
/*      */       }
/*      */       
/* 4357 */       return traceRet8;
/*      */     } 
/* 4359 */     if (type1.equals("r8")) {
/*      */       
/* 4361 */       Object traceRet9 = Double.valueOf(value);
/* 4362 */       if (Trace.isOn) {
/* 4363 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet9, 9);
/*      */       }
/*      */       
/* 4366 */       return traceRet9;
/*      */     } 
/* 4368 */     if (type1.equals("bin.hex")) {
/* 4369 */       if (value.length() == 0) {
/* 4370 */         Object traceRet10 = new byte[0];
/* 4371 */         if (Trace.isOn) {
/* 4372 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet10, 10);
/*      */         }
/*      */         
/* 4375 */         return traceRet10;
/*      */       } 
/*      */       
/* 4378 */       Object traceRet11 = hexToBin(value, 0);
/* 4379 */       if (Trace.isOn) {
/* 4380 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet11, 11);
/*      */       }
/*      */       
/* 4383 */       return traceRet11;
/*      */     } 
/* 4385 */     if (type1.equals("boolean")) {
/*      */       
/* 4387 */       if (value.equals("1")) {
/* 4388 */         Object traceRet12 = Boolean.valueOf(true);
/* 4389 */         if (Trace.isOn) {
/* 4390 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet12, 12);
/*      */         }
/*      */         
/* 4393 */         return traceRet12;
/*      */       } 
/* 4395 */       if (value.equals("0")) {
/* 4396 */         Object traceRet13 = Boolean.valueOf(false);
/* 4397 */         if (Trace.isOn) {
/* 4398 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet13, 13);
/*      */         }
/*      */         
/* 4401 */         return traceRet13;
/*      */       } 
/*      */       
/* 4404 */       JMSException traceRet14 = newMessageFormatException();
/* 4405 */       if (Trace.isOn) {
/* 4406 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", (Throwable)traceRet14, 2);
/*      */       }
/*      */       
/* 4409 */       throw traceRet14;
/*      */     } 
/*      */     
/* 4412 */     if (type1.equals("char")) {
/*      */       
/* 4414 */       Object traceRet15 = Character.valueOf(expandRefs(value).charAt(0));
/* 4415 */       if (Trace.isOn) {
/* 4416 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", traceRet15, 14);
/*      */       }
/*      */       
/* 4419 */       return traceRet15;
/*      */     } 
/*      */     
/* 4422 */     JMSException traceRet16 = newMessageFormatException(1018, type1);
/* 4423 */     if (Trace.isOn) {
/* 4424 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "deformatElement(String,String)", (Throwable)traceRet16, 3);
/*      */     }
/*      */     
/* 4427 */     throw traceRet16;
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
/*      */   String expandRefs(String string) throws JMSException {
/* 4445 */     if (Trace.isOn) {
/* 4446 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "expandRefs(String)", new Object[] { string });
/*      */     }
/*      */     
/* 4449 */     if (string.length() == 0) {
/* 4450 */       String traceRet1 = "";
/* 4451 */       if (Trace.isOn) {
/* 4452 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "expandRefs(String)", traceRet1, 1);
/*      */       }
/*      */       
/* 4455 */       return traceRet1;
/*      */     } 
/* 4457 */     StringBuffer sb = new StringBuffer(string.length());
/*      */ 
/*      */     
/* 4460 */     for (int i = 0; i < string.length(); i++) {
/* 4461 */       char ch = string.charAt(i);
/* 4462 */       if ('&' == ch) {
/* 4463 */         String entity = string.substring(i + 1, i + 4);
/*      */         
/* 4465 */         if (entity.equals("lt;")) {
/* 4466 */           sb.append('<');
/* 4467 */           i += 3;
/*      */         }
/* 4469 */         else if (entity.equals("gt;")) {
/* 4470 */           sb.append('>');
/* 4471 */           i += 3;
/*      */         }
/* 4473 */         else if (string.substring(i + 1, i + 5).equals("amp;")) {
/* 4474 */           sb.append("&");
/* 4475 */           i += 4;
/*      */         }
/* 4477 */         else if (string.substring(i + 1, i + 6).equals("apos;")) {
/*      */           
/* 4479 */           sb.append("'");
/* 4480 */           i += 5;
/*      */         }
/* 4482 */         else if (string.substring(i + 1, i + 6).equals("quot;")) {
/*      */           
/* 4484 */           sb.append('"');
/* 4485 */           i += 5;
/*      */         } else {
/*      */           
/* 4488 */           JMSException traceRet2 = newMessageFormatException(1016, "&" + entity);
/* 4489 */           if (Trace.isOn) {
/* 4490 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "expandRefs(String)", (Throwable)traceRet2);
/*      */           }
/*      */           
/* 4493 */           throw traceRet2;
/*      */         } 
/*      */       } else {
/*      */         
/* 4497 */         sb.append(ch);
/*      */       } 
/*      */     } 
/* 4500 */     String traceRet3 = sb.toString();
/* 4501 */     if (Trace.isOn) {
/* 4502 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "expandRefs(String)", traceRet3, 2);
/*      */     }
/*      */     
/* 4505 */     return traceRet3;
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
/*      */   void formatElement(String name, Object value1, StringBuffer buffer) throws JMSException {
/* 4519 */     if (Trace.isOn) {
/* 4520 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "formatElement(String,Object,StringBuffer)", new Object[] { name, value1, buffer });
/*      */     }
/*      */ 
/*      */     
/* 4524 */     Object value = value1;
/* 4525 */     boolean written = false;
/* 4526 */     buffer.append("<");
/* 4527 */     buffer.append(name);
/*      */     
/* 4529 */     if (value instanceof String) {
/* 4530 */       buffer.append(">");
/* 4531 */       backReference(buffer, (String)value);
/* 4532 */       written = true;
/*      */     }
/* 4534 */     else if (value instanceof Integer) {
/* 4535 */       buffer.append(" dt='i4'>");
/*      */     }
/* 4537 */     else if (value instanceof Short) {
/* 4538 */       buffer.append(" dt='i2'>");
/*      */     }
/* 4540 */     else if (value instanceof Byte) {
/* 4541 */       buffer.append(" dt='i1'>");
/*      */     }
/* 4543 */     else if (value instanceof Long) {
/* 4544 */       buffer.append(" dt='i8'>");
/*      */     }
/* 4546 */     else if (value instanceof Float) {
/* 4547 */       buffer.append(" dt='r4'>");
/*      */     }
/* 4549 */     else if (value instanceof Double) {
/* 4550 */       buffer.append(" dt='r8'>");
/*      */     }
/* 4552 */     else if (value instanceof byte[]) {
/* 4553 */       buffer.append(" dt='bin.hex'>");
/* 4554 */       binToHex((byte[])value, 0, ((byte[])value).length, buffer);
/* 4555 */       written = true;
/*      */     }
/* 4557 */     else if (value instanceof Boolean) {
/* 4558 */       buffer.append(" dt='boolean'>");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4564 */       if (((Boolean)value).booleanValue() == true) {
/* 4565 */         value = "1";
/*      */       } else {
/*      */         
/* 4568 */         value = "0";
/*      */       }
/*      */     
/* 4571 */     } else if (value instanceof Character) {
/* 4572 */       buffer.append(" dt='char'>");
/* 4573 */       backReference(buffer, ((Character)value).toString());
/*      */ 
/*      */ 
/*      */       
/* 4577 */       written = true;
/*      */     }
/* 4579 */     else if (value == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4585 */       buffer.append(" xsi:nil='true'>");
/* 4586 */       buffer.append("</");
/* 4587 */       buffer.append(name);
/* 4588 */       buffer.append(">");
/* 4589 */       if (Trace.isOn) {
/* 4590 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "formatElement(String,Object,StringBuffer)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 4598 */     if (!written) {
/* 4599 */       buffer.append(value.toString());
/*      */     }
/*      */ 
/*      */     
/* 4603 */     buffer.append("</");
/* 4604 */     buffer.append(name);
/* 4605 */     buffer.append(">");
/*      */     
/* 4607 */     if (Trace.isOn) {
/* 4608 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "formatElement(String,Object,StringBuffer)", 2);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void formatElement(String name, String type1, String value, StringBuffer buffer) throws JMSException {
/* 4630 */     if (Trace.isOn) {
/* 4631 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "formatElement(String,String,String,StringBuffer)", new Object[] { name, type1, value, buffer });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4637 */     buffer.append("<");
/* 4638 */     buffer.append(name);
/*      */     
/* 4640 */     if (type1 != null && !type1.equals("")) {
/* 4641 */       buffer.append(" dt=" + type1);
/*      */     }
/*      */     
/* 4644 */     buffer.append(">");
/*      */ 
/*      */ 
/*      */     
/* 4648 */     buffer.append(value);
/*      */ 
/*      */     
/* 4651 */     buffer.append("</");
/* 4652 */     buffer.append(name);
/* 4653 */     buffer.append(">");
/*      */     
/* 4655 */     if (Trace.isOn) {
/* 4656 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "formatElement(String,String,String,StringBuffer)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBooleanProperty(String name) throws JMSException {
/* 4682 */     if (Trace.isOn) {
/* 4683 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getBooleanProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 4687 */     if (name == null) {
/* 4688 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4689 */       if (Trace.isOn) {
/* 4690 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getBooleanProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4693 */       throw traceRet1;
/*      */     } 
/* 4695 */     boolean traceRet2 = toBoolean(_getProperty(name));
/* 4696 */     if (Trace.isOn) {
/* 4697 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getBooleanProperty(String)", 
/* 4698 */           Boolean.valueOf(traceRet2));
/*      */     }
/* 4700 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByteProperty(String name) throws JMSException {
/* 4723 */     if (Trace.isOn) {
/* 4724 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getByteProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 4728 */     if (name == null) {
/* 4729 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4730 */       if (Trace.isOn) {
/* 4731 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getByteProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4734 */       throw traceRet1;
/*      */     } 
/* 4736 */     byte traceRet2 = toByte(_getProperty(name));
/* 4737 */     if (Trace.isOn) {
/* 4738 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getByteProperty(String)", 
/* 4739 */           Byte.valueOf(traceRet2));
/*      */     }
/* 4741 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesProperty(String name) throws JMSException {
/* 4763 */     if (Trace.isOn) {
/* 4764 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getBytesProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/* 4767 */     if (name == null) {
/* 4768 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4769 */       if (Trace.isOn) {
/* 4770 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getBytesProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4773 */       throw traceRet1;
/*      */     } 
/* 4775 */     byte[] traceRet2 = toBytes(name);
/* 4776 */     if (Trace.isOn) {
/* 4777 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getBytesProperty(String)", traceRet2);
/*      */     }
/*      */     
/* 4780 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public char getCharProperty(String name) throws JMSException {
/* 4802 */     if (Trace.isOn) {
/* 4803 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getCharProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/* 4806 */     if (name == null) {
/* 4807 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4808 */       if (Trace.isOn) {
/* 4809 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getCharProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4812 */       throw traceRet1;
/*      */     } 
/* 4814 */     char traceRet2 = toChar(name);
/* 4815 */     if (Trace.isOn) {
/* 4816 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getCharProperty(String)", 
/* 4817 */           Character.valueOf(traceRet2));
/*      */     }
/* 4819 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDoubleProperty(String name) throws JMSException {
/* 4841 */     if (Trace.isOn) {
/* 4842 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getDoubleProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 4846 */     if (name == null) {
/* 4847 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4848 */       if (Trace.isOn) {
/* 4849 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getDoubleProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4852 */       throw traceRet1;
/*      */     } 
/* 4854 */     double traceRet2 = toDouble(_getProperty(name));
/* 4855 */     if (Trace.isOn) {
/* 4856 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getDoubleProperty(String)", 
/* 4857 */           Double.valueOf(traceRet2));
/*      */     }
/* 4859 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloatProperty(String name) throws JMSException {
/* 4882 */     if (Trace.isOn) {
/* 4883 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getFloatProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 4887 */     if (name == null) {
/* 4888 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4889 */       if (Trace.isOn) {
/* 4890 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getFloatProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4893 */       throw traceRet1;
/*      */     } 
/* 4895 */     float traceRet2 = toFloat(_getProperty(name));
/* 4896 */     if (Trace.isOn) {
/* 4897 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getFloatProperty(String)", 
/* 4898 */           Float.valueOf(traceRet2));
/*      */     }
/* 4900 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntProperty(String name) throws JMSException {
/* 4923 */     if (Trace.isOn) {
/* 4924 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getIntProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 4928 */     if (name == null) {
/* 4929 */       JMSException traceRet1 = newJMSException(1003, null);
/* 4930 */       if (Trace.isOn) {
/* 4931 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getIntProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4934 */       throw traceRet1;
/*      */     } 
/* 4936 */     int traceRet2 = toInt(_getProperty(name));
/* 4937 */     if (Trace.isOn) {
/* 4938 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getIntProperty(String)", 
/* 4939 */           Integer.valueOf(traceRet2));
/*      */     }
/* 4941 */     return traceRet2;
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
/*      */ 
/*      */   
/*      */   public String getJMSCorrelationID() throws JMSException {
/* 4962 */     if (this.correlationId == null && this.nativeCorrelId != null)
/*      */     {
/*      */       
/* 4965 */       this.correlationId = _idToString(this.nativeCorrelId);
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
/* 4980 */     if (Trace.isOn) {
/* 4981 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSCorrelationID()", "getter", this.correlationId);
/*      */     }
/*      */     
/* 4984 */     return this.correlationId;
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
/*      */   public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
/* 4999 */     if (Trace.isOn) {
/* 5000 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSCorrelationIDAsBytes()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 5007 */       if (this.nativeCorrelId == null && this.correlationId != null) {
/* 5008 */         this.nativeCorrelId = this.correlationId.getBytes("UTF8");
/*      */       }
/* 5010 */       if (Trace.isOn) {
/* 5011 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSCorrelationIDAsBytes()", this.nativeCorrelId);
/*      */       }
/*      */       
/* 5014 */       return this.nativeCorrelId;
/*      */     }
/* 5016 */     catch (UnsupportedEncodingException ex) {
/* 5017 */       if (Trace.isOn) {
/* 5018 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSCorrelationIDAsBytes()", ex);
/*      */       }
/*      */       
/* 5021 */       JMSException jmsEx = newJMSException(1022);
/* 5022 */       jmsEx.setLinkedException(ex);
/* 5023 */       if (Trace.isOn) {
/* 5024 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSCorrelationIDAsBytes()", (Throwable)jmsEx);
/*      */       }
/*      */       
/* 5027 */       throw jmsEx;
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
/*      */   public Integer getJMSDeliveryMode() throws JMSException {
/* 5039 */     Integer traceRet1 = Integer.valueOf(this.deliveryMode);
/* 5040 */     if (Trace.isOn) {
/* 5041 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSDeliveryMode()", "getter", traceRet1);
/*      */     }
/*      */     
/* 5044 */     return traceRet1;
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
/*      */   public Long getJMSExpiration() throws JMSException {
/* 5073 */     Long traceRet1 = Long.valueOf(this.expiration);
/* 5074 */     if (Trace.isOn) {
/* 5075 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSExpiration()", "getter", traceRet1);
/*      */     }
/*      */     
/* 5078 */     return traceRet1;
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
/*      */   public String getJMSMessageID() throws JMSException {
/* 5108 */     if (this.messageId == null && this.nativeMessageId != null) {
/* 5109 */       this.messageId = _idToString(this.nativeMessageId);
/* 5110 */       this.nativeMessageId = null;
/*      */     } 
/*      */     
/* 5113 */     if (Trace.isOn) {
/* 5114 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSMessageID()", "getter", this.messageId);
/*      */     }
/*      */     
/* 5117 */     return this.messageId;
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
/*      */   public Integer getJMSPriority() throws JMSException {
/* 5134 */     Integer traceRet1 = Integer.valueOf(this.priority);
/* 5135 */     if (Trace.isOn) {
/* 5136 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSPriority()", "getter", traceRet1);
/*      */     }
/*      */     
/* 5139 */     return traceRet1;
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
/*      */   public Boolean getJMSRedelivered() throws JMSException {
/* 5156 */     Boolean traceRet1 = Boolean.valueOf(this.redelivered);
/* 5157 */     if (Trace.isOn) {
/* 5158 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSRedelivered()", "getter", traceRet1);
/*      */     }
/*      */     
/* 5161 */     return traceRet1;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public Long getJMSTimestamp() throws JMSException {
/* 5183 */     Long traceRet1 = (this.timestamp == -1L) ? null : Long.valueOf(this.timestamp);
/* 5184 */     if (Trace.isOn) {
/* 5185 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSTimestamp()", "getter", traceRet1);
/*      */     }
/*      */     
/* 5188 */     return traceRet1;
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
/*      */   public String getJMSType() throws JMSException {
/* 5200 */     if (Trace.isOn) {
/* 5201 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSType()", "getter", this.type);
/*      */     }
/*      */     
/* 5204 */     return this.type;
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
/*      */   
/*      */   public long getLongProperty(String name) throws JMSException {
/* 5224 */     if (Trace.isOn) {
/* 5225 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getLongProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 5229 */     if (name == null) {
/* 5230 */       JMSException traceRet1 = newJMSException(1003, null);
/* 5231 */       if (Trace.isOn) {
/* 5232 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getLongProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5235 */       throw traceRet1;
/*      */     } 
/* 5237 */     long traceRet2 = toLong(_getProperty(name));
/* 5238 */     if (Trace.isOn) {
/* 5239 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getLongProperty(String)", 
/* 5240 */           Long.valueOf(traceRet2));
/*      */     }
/* 5242 */     return traceRet2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/* 5269 */     if (Trace.isOn) {
/* 5270 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getObjectProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5276 */     if (name == null) {
/* 5277 */       JMSException traceRet1 = newJMSException(1003, null);
/* 5278 */       if (Trace.isOn) {
/* 5279 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getObjectProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5282 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 5286 */     Object retval = _getProperty(name);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5291 */     if (retval == null) {
/* 5292 */       if (Trace.isOn) {
/* 5293 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getObjectProperty(String)", null, 1);
/*      */       }
/*      */       
/* 5296 */       return null;
/*      */     } 
/*      */     
/* 5299 */     if (Trace.isOn) {
/* 5300 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getObjectProperty(String)", retval, 2);
/*      */     }
/*      */     
/* 5303 */     return retval;
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
/*      */   public Enumeration getPropertyNames() throws JMSException {
/* 5315 */     if (Trace.isOn) {
/* 5316 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getPropertyNames()");
/*      */     }
/*      */     
/* 5319 */     final Iterator iterator = this.properties.keySet().iterator();
/* 5320 */     Enumeration e = new Enumeration()
/*      */       {
/*      */         public boolean hasMoreElements()
/*      */         {
/* 5324 */           if (Trace.isOn) {
/* 5325 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "hasMoreElements()");
/*      */           }
/*      */           
/* 5328 */           boolean b = iterator.hasNext();
/* 5329 */           if (Trace.isOn) {
/* 5330 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "hasMoreElements()", 
/* 5331 */                 Boolean.valueOf(b));
/*      */           }
/* 5333 */           return b;
/*      */         }
/*      */ 
/*      */         
/*      */         public Object nextElement() {
/* 5338 */           if (Trace.isOn) {
/* 5339 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "nextElement()");
/*      */           }
/*      */           
/* 5342 */           Object o = iterator.next();
/* 5343 */           if (Trace.isOn) {
/* 5344 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "nextElement()", o);
/*      */           }
/* 5346 */           return o;
/*      */         }
/*      */       };
/* 5349 */     if (Trace.isOn) {
/* 5350 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getPropertyNames()", e);
/*      */     }
/*      */     
/* 5353 */     return e;
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
/*      */   public short getShortProperty(String name) throws JMSException {
/* 5372 */     if (Trace.isOn) {
/* 5373 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getShortProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 5377 */     if (name == null) {
/* 5378 */       JMSException traceRet1 = newJMSException(1003, null);
/* 5379 */       if (Trace.isOn) {
/* 5380 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getShortProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5383 */       throw traceRet1;
/*      */     } 
/* 5385 */     short traceRet2 = toShort(_getProperty(name));
/* 5386 */     if (Trace.isOn) {
/* 5387 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getShortProperty(String)", 
/* 5388 */           Short.valueOf(traceRet2));
/*      */     }
/* 5390 */     return traceRet2;
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
/*      */ 
/*      */   
/*      */   public String getStringProperty(String name) throws JMSException {
/* 5411 */     if (Trace.isOn) {
/* 5412 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getStringProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5418 */     if (name == null) {
/* 5419 */       JMSException traceRet1 = newJMSException(1003, null);
/* 5420 */       if (Trace.isOn) {
/* 5421 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getStringProperty(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5424 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5427 */     Object retval = _getProperty(name);
/*      */ 
/*      */ 
/*      */     
/* 5431 */     if (retval == null) {
/* 5432 */       if (Trace.isOn) {
/* 5433 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getStringProperty(String)", null, 1);
/*      */       }
/*      */       
/* 5436 */       return null;
/*      */     } 
/*      */     
/* 5439 */     String traceRet2 = retval.toString();
/* 5440 */     if (Trace.isOn) {
/* 5441 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getStringProperty(String)", traceRet2, 2);
/*      */     }
/*      */     
/* 5444 */     return traceRet2;
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
/*      */   byte[] hexToBin(String hex, int start) throws JMSException {
/* 5459 */     if (Trace.isOn) {
/* 5460 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "hexToBin(String,int)", new Object[] { hex, 
/* 5461 */             Integer.valueOf(start) });
/*      */     }
/*      */     
/* 5464 */     int length = hex.length() - start;
/*      */ 
/*      */     
/* 5467 */     if (length == 0) {
/* 5468 */       byte[] traceRet1 = new byte[0];
/* 5469 */       if (Trace.isOn) {
/* 5470 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "hexToBin(String,int)", traceRet1, 1);
/*      */       }
/*      */       
/* 5473 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 5477 */     if (length < 0 || length % 2 != 0) {
/* 5478 */       JMSException traceRet2 = newJMSException(1006, hex.substring(start));
/* 5479 */       if (Trace.isOn) {
/* 5480 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "hexToBin(String,int)", (Throwable)traceRet2, 1);
/*      */       }
/*      */       
/* 5483 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5488 */     length /= 2;
/*      */ 
/*      */     
/* 5491 */     byte[] retval = new byte[length];
/*      */ 
/*      */     
/* 5494 */     for (int i = 0; i < length; i++) {
/* 5495 */       int digit1 = Character.digit(hex.charAt(2 * i + start), 16) << 4;
/* 5496 */       int digit2 = Character.digit(hex.charAt(2 * i + start + 1), 16);
/*      */ 
/*      */ 
/*      */       
/* 5500 */       if (digit1 < 0 || digit2 < 0) {
/* 5501 */         JMSException traceRet3 = newJMSException(1006, hex.substring(start));
/* 5502 */         if (Trace.isOn) {
/* 5503 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "hexToBin(String,int)", (Throwable)traceRet3, 2);
/*      */         }
/*      */         
/* 5506 */         throw traceRet3;
/*      */       } 
/*      */       
/* 5509 */       retval[i] = (byte)(digit1 + digit2);
/*      */     } 
/* 5511 */     if (Trace.isOn) {
/* 5512 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "hexToBin(String,int)", retval, 2);
/*      */     }
/*      */     
/* 5515 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isSettablePropertyName(String propertyName) {
/* 5526 */     if (Trace.isOn) {
/* 5527 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", new Object[] { propertyName });
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
/* 5541 */     if (propertyName == null) {
/* 5542 */       if (Trace.isOn) {
/* 5543 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", 
/* 5544 */             Boolean.valueOf(false), 1);
/*      */       }
/* 5546 */       return false;
/*      */     } 
/* 5548 */     if (!Character.isJavaIdentifierStart(propertyName.charAt(0))) {
/* 5549 */       if (Trace.isOn) {
/* 5550 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", 
/* 5551 */             Boolean.valueOf(false), 2);
/*      */       }
/* 5553 */       return false;
/*      */     } 
/* 5555 */     for (int i = 1; i < propertyName.length(); i++) {
/* 5556 */       if (!Character.isJavaIdentifierPart(propertyName.charAt(i))) {
/* 5557 */         if (Trace.isOn) {
/* 5558 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", 
/* 5559 */               Boolean.valueOf(false), 3);
/*      */         }
/* 5561 */         return false;
/*      */       } 
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
/* 5577 */     if (propertyName.startsWith("JMS")) {
/* 5578 */       if (this.jmsStrings.getJMS_IBM_names().containsKey(propertyName) || propertyName
/*      */ 
/*      */         
/* 5581 */         .equals("JMSXGroupID") || propertyName
/* 5582 */         .equals("JMSXGroupSeq") || propertyName
/*      */ 
/*      */         
/* 5585 */         .equals("JMSXUserID") || propertyName
/* 5586 */         .equals("JMSXAppID") || propertyName
/* 5587 */         .equals("JMSXDeliveryCount") || propertyName
/* 5588 */         .equals("JMSXState") || propertyName
/* 5589 */         .equals("JMSXProducerTXID") || propertyName
/* 5590 */         .equals("JMSXConsumerTXID") || propertyName
/* 5591 */         .equals("JMSXRcvTimestamp") || propertyName
/*      */ 
/*      */ 
/*      */         
/* 5595 */         .equals("JMS_IBM_MQMD_Report") || propertyName.equals("JMS_IBM_MQMD_MsgType") || propertyName
/* 5596 */         .equals("JMS_IBM_MQMD_Expiry") || propertyName.equals("JMS_IBM_MQMD_Feedback") || propertyName
/* 5597 */         .equals("JMS_IBM_MQMD_Encoding") || propertyName.equals("JMS_IBM_MQMD_CodedCharSetId") || propertyName
/* 5598 */         .equals("JMS_IBM_MQMD_Format") || propertyName.equals("JMS_IBM_MQMD_Priority") || propertyName
/* 5599 */         .equals("JMS_IBM_MQMD_Persistence") || propertyName.equals("JMS_IBM_MQMD_MsgId") || propertyName
/* 5600 */         .equals("JMS_IBM_MQMD_CorrelId") || propertyName.equals("JMS_IBM_MQMD_BackoutCount") || propertyName
/* 5601 */         .equals("JMS_IBM_MQMD_ReplyToQ") || propertyName.equals("JMS_IBM_MQMD_ReplyToQMgr") || propertyName
/* 5602 */         .equals("JMS_IBM_MQMD_UserIdentifier") || propertyName
/* 5603 */         .equals("JMS_IBM_MQMD_AccountingToken") || propertyName
/* 5604 */         .equals("JMS_IBM_MQMD_ApplIdentityData") || propertyName
/* 5605 */         .equals("JMS_IBM_MQMD_PutApplType") || propertyName.equals("JMS_IBM_MQMD_PutApplName") || propertyName
/* 5606 */         .equals("JMS_IBM_MQMD_PutDate") || propertyName.equals("JMS_IBM_MQMD_PutTime") || propertyName
/* 5607 */         .equals("JMS_IBM_MQMD_ApplOriginData") || propertyName.equals("JMS_IBM_MQMD_GroupId") || propertyName
/* 5608 */         .equals("JMS_IBM_MQMD_MsgSeqNumber") || propertyName.equals("JMS_IBM_MQMD_Offset") || propertyName
/* 5609 */         .equals("JMS_IBM_MQMD_MsgFlags") || propertyName.equals("JMS_IBM_MQMD_OriginalLength")) {
/*      */         
/* 5611 */         if (Trace.isOn) {
/* 5612 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", 
/* 5613 */               Boolean.valueOf(true), 4);
/*      */         }
/* 5615 */         return true;
/*      */       } 
/*      */       
/* 5618 */       if (Trace.isOn) {
/* 5619 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", 
/* 5620 */             Boolean.valueOf(false), 5);
/*      */       }
/* 5622 */       return false;
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
/* 5635 */     if (Trace.isOn) {
/* 5636 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isSettablePropertyName(String)", 
/* 5637 */           Boolean.valueOf(true), 6);
/*      */     }
/* 5639 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSException newJMSException(int key) {
/* 5650 */     if (Trace.isOn)
/* 5651 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newJMSException(int)", new Object[] {
/* 5652 */             Integer.valueOf(key)
/*      */           }); 
/* 5654 */     String exString = this.jmsStrings.getErrorMessage(key);
/* 5655 */     JMSException traceRet1 = new JMSException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = 3777406741865360456L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5661 */           if (Trace.isOn) {
/* 5662 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5665 */           super.setLinkedException(exception);
/*      */           try {
/* 5667 */             initCause(exception);
/*      */           }
/* 5669 */           catch (IllegalStateException ise) {
/* 5670 */             if (Trace.isOn) {
/* 5671 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5676 */           if (Trace.isOn) {
/* 5677 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5683 */     if (Trace.isOn) {
/* 5684 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newJMSException(int)", traceRet1);
/*      */     }
/*      */     
/* 5687 */     return traceRet1;
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
/*      */   JMSException newJMSException(int key, Object insert) {
/* 5700 */     if (Trace.isOn)
/* 5701 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newJMSException(int,Object)", new Object[] {
/* 5702 */             Integer.valueOf(key), insert
/*      */           }); 
/* 5704 */     String exString = this.jmsStrings.getErrorMessage(key, insert);
/* 5705 */     JMSException traceRet1 = new JMSException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = -7941672008307698281L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5711 */           if (Trace.isOn) {
/* 5712 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5715 */           super.setLinkedException(exception);
/*      */           try {
/* 5717 */             initCause(exception);
/*      */           }
/* 5719 */           catch (IllegalStateException ise) {
/* 5720 */             if (Trace.isOn) {
/* 5721 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5726 */           if (Trace.isOn) {
/* 5727 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5733 */     if (Trace.isOn) {
/* 5734 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newJMSException(int,Object)", traceRet1);
/*      */     }
/*      */     
/* 5737 */     return traceRet1;
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
/*      */   JMSException newJMSException(int key, Object insert1, Object insert2) {
/* 5751 */     if (Trace.isOn) {
/* 5752 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newJMSException(int,Object,Object)", new Object[] {
/* 5753 */             Integer.valueOf(key), insert1, insert2
/*      */           });
/*      */     }
/* 5756 */     String exString = this.jmsStrings.getErrorMessage(key, insert1, insert2);
/* 5757 */     JMSException traceRet1 = new JMSException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = 4241609402337085932L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5763 */           if (Trace.isOn) {
/* 5764 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5767 */           super.setLinkedException(exception);
/*      */           try {
/* 5769 */             initCause(exception);
/*      */           }
/* 5771 */           catch (IllegalStateException ise) {
/* 5772 */             if (Trace.isOn) {
/* 5773 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5778 */           if (Trace.isOn) {
/* 5779 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5785 */     if (Trace.isOn) {
/* 5786 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newJMSException(int,Object,Object)", traceRet1);
/*      */     }
/*      */     
/* 5789 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSException newMessageEOFException() {
/* 5800 */     if (Trace.isOn) {
/* 5801 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageEOFException()");
/*      */     }
/*      */     
/* 5804 */     int key = 5;
/* 5805 */     String exString = this.jmsStrings.getErrorMessage(key);
/* 5806 */     MessageEOFException messageEOFException = new MessageEOFException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = 6478073496630946705L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5812 */           if (Trace.isOn) {
/* 5813 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5816 */           super.setLinkedException(exception);
/*      */           try {
/* 5818 */             initCause(exception);
/*      */           }
/* 5820 */           catch (IllegalStateException ise) {
/* 5821 */             if (Trace.isOn) {
/* 5822 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5827 */           if (Trace.isOn) {
/* 5828 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5834 */     if (Trace.isOn) {
/* 5835 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageEOFException()", messageEOFException);
/*      */     }
/*      */     
/* 5838 */     return (JMSException)messageEOFException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSException newMessageFormatException() {
/* 5849 */     if (Trace.isOn) {
/* 5850 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageFormatException()");
/*      */     }
/*      */     
/* 5853 */     int key = 6;
/* 5854 */     String exString = this.jmsStrings.getErrorMessage(key);
/*      */     
/* 5856 */     MessageFormatException messageFormatException = new MessageFormatException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = -7104407445704689018L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5862 */           if (Trace.isOn) {
/* 5863 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5866 */           super.setLinkedException(exception);
/*      */           try {
/* 5868 */             initCause(exception);
/*      */           }
/* 5870 */           catch (IllegalStateException ise) {
/* 5871 */             if (Trace.isOn) {
/* 5872 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5877 */           if (Trace.isOn) {
/* 5878 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5884 */     if (Trace.isOn) {
/* 5885 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageFormatException()", messageFormatException);
/*      */     }
/*      */     
/* 5888 */     return (JMSException)messageFormatException;
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
/*      */   JMSException newMessageFormatException(int key) {
/* 5900 */     if (Trace.isOn) {
/* 5901 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageFormatException(int)", new Object[] {
/* 5902 */             Integer.valueOf(key)
/*      */           });
/*      */     }
/* 5905 */     String exString = this.jmsStrings.getErrorMessage(key);
/*      */     
/* 5907 */     MessageFormatException messageFormatException = new MessageFormatException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = 4875574440026796740L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5913 */           if (Trace.isOn) {
/* 5914 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5917 */           super.setLinkedException(exception);
/*      */           try {
/* 5919 */             initCause(exception);
/*      */           }
/* 5921 */           catch (IllegalStateException ise) {
/* 5922 */             if (Trace.isOn) {
/* 5923 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5928 */           if (Trace.isOn) {
/* 5929 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5935 */     if (Trace.isOn) {
/* 5936 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageFormatException(int)", messageFormatException);
/*      */     }
/*      */     
/* 5939 */     return (JMSException)messageFormatException;
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
/*      */   JMSException newMessageFormatException(int key, Object insert) {
/* 5952 */     if (Trace.isOn)
/* 5953 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageFormatException(int,Object)", new Object[] {
/* 5954 */             Integer.valueOf(key), insert
/*      */           }); 
/* 5956 */     String exString = this.jmsStrings.getErrorMessage(key, insert);
/*      */     
/* 5958 */     MessageFormatException messageFormatException = new MessageFormatException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = 3803930645470321505L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 5964 */           if (Trace.isOn) {
/* 5965 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 5968 */           super.setLinkedException(exception);
/*      */           try {
/* 5970 */             initCause(exception);
/*      */           }
/* 5972 */           catch (IllegalStateException ise) {
/* 5973 */             if (Trace.isOn) {
/* 5974 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5979 */           if (Trace.isOn) {
/* 5980 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 5986 */     if (Trace.isOn) {
/* 5987 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageFormatException(int,Object)", messageFormatException);
/*      */     }
/*      */     
/* 5990 */     return (JMSException)messageFormatException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSException newMessageNotReadableException() {
/* 6001 */     if (Trace.isOn) {
/* 6002 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageNotReadableException()");
/*      */     }
/*      */     
/* 6005 */     int key = 7;
/*      */     
/* 6007 */     String exString = this.jmsStrings.getErrorMessage(key);
/*      */     
/* 6009 */     MessageNotReadableException messageNotReadableException = new MessageNotReadableException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = -3701015364679331753L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 6015 */           if (Trace.isOn) {
/* 6016 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 6019 */           super.setLinkedException(exception);
/*      */           try {
/* 6021 */             initCause(exception);
/*      */           }
/* 6023 */           catch (IllegalStateException ise) {
/* 6024 */             if (Trace.isOn) {
/* 6025 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 6030 */           if (Trace.isOn) {
/* 6031 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 6037 */     if (Trace.isOn) {
/* 6038 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageNotReadableException()", messageNotReadableException);
/*      */     }
/*      */     
/* 6041 */     return (JMSException)messageNotReadableException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSException newMessageNotWriteableException() {
/* 6052 */     if (Trace.isOn) {
/* 6053 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageNotWriteableException()");
/*      */     }
/*      */     
/* 6056 */     int key = 8;
/*      */     
/* 6058 */     String exString = this.jmsStrings.getErrorMessage(key);
/*      */     
/* 6060 */     MessageNotWriteableException messageNotWriteableException = new MessageNotWriteableException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = -1958471774800933551L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 6066 */           if (Trace.isOn) {
/* 6067 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 6070 */           super.setLinkedException(exception);
/*      */           try {
/* 6072 */             initCause(exception);
/*      */           }
/* 6074 */           catch (IllegalStateException ise) {
/* 6075 */             if (Trace.isOn) {
/* 6076 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 6081 */           if (Trace.isOn) {
/* 6082 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 6088 */     if (Trace.isOn) {
/* 6089 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newMessageNotWriteableException()", messageNotWriteableException);
/*      */     }
/*      */     
/* 6092 */     return (JMSException)messageNotWriteableException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSException newResourceAllocationException() {
/* 6103 */     if (Trace.isOn) {
/* 6104 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newResourceAllocationException()");
/*      */     }
/*      */     
/* 6107 */     int key = 9;
/*      */     
/* 6109 */     String exString = this.jmsStrings.getErrorMessage(key);
/*      */     
/* 6111 */     ResourceAllocationException resourceAllocationException = new ResourceAllocationException(exString, this.jmsStrings.getNativeKey(key))
/*      */       {
/*      */         private static final long serialVersionUID = 3777406741865360456L;
/*      */ 
/*      */         
/*      */         public void setLinkedException(Exception exception) {
/* 6117 */           if (Trace.isOn) {
/* 6118 */             Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLinkedException(Exception)", new Object[] { exception });
/*      */           }
/*      */           
/* 6121 */           super.setLinkedException(exception);
/*      */           try {
/* 6123 */             initCause(exception);
/*      */           }
/* 6125 */           catch (IllegalStateException ise) {
/* 6126 */             if (Trace.isOn) {
/* 6127 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)", ise);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 6132 */           if (Trace.isOn) {
/* 6133 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "setLinkedException(Exception)");
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 6139 */     if (Trace.isOn) {
/* 6140 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "newResourceAllocationException()", resourceAllocationException);
/*      */     }
/*      */     
/* 6143 */     return (JMSException)resourceAllocationException;
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
/*      */   public boolean propertyExists(String name) throws JMSException {
/* 6158 */     if (Trace.isOn) {
/* 6159 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "propertyExists(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 6164 */     if (name == null) {
/* 6165 */       JMSException traceRet1 = newJMSException(1003, null);
/* 6166 */       if (Trace.isOn) {
/* 6167 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "propertyExists(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 6170 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6173 */     boolean retval = this.properties.containsKey(name);
/*      */     
/* 6175 */     if (Trace.isOn) {
/* 6176 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "propertyExists(String)", 
/* 6177 */           Boolean.valueOf(retval));
/*      */     }
/* 6179 */     return retval;
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
/*      */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/* 6195 */     if (Trace.isOn) {
/* 6196 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBatchProperties(Map<String , Object>)", "setter", properties);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6203 */     for (Map.Entry<?, ?> batchProperty : properties.entrySet())
/*      */     {
/*      */       
/* 6206 */       setObjectProperty((String)batchProperty.getKey(), batchProperty.getValue());
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
/*      */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/* 6223 */     if (Trace.isOn) {
/* 6224 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBooleanProperty(String,boolean)", new Object[] { name, 
/* 6225 */             Boolean.valueOf(value) });
/*      */     }
/*      */     
/* 6228 */     if (this.propertiesReadOnly == true) {
/* 6229 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6230 */       if (Trace.isOn) {
/* 6231 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBooleanProperty(String,boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6234 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 6238 */     if (name == null || name.trim().length() == 0) {
/* 6239 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6240 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6241 */       if (Trace.isOn) {
/* 6242 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBooleanProperty(String,boolean)", traceRet2, 2);
/*      */       }
/*      */       
/* 6245 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 6249 */     if (!isSettablePropertyName(name)) {
/* 6250 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6251 */       if (Trace.isOn) {
/* 6252 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBooleanProperty(String,boolean)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6255 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6258 */     this.properties.put(name, Boolean.valueOf(value));
/*      */     
/* 6260 */     if (Trace.isOn) {
/* 6261 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBooleanProperty(String,boolean)");
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
/*      */ 
/*      */   
/*      */   public void setByteProperty(String name, byte value) throws JMSException {
/* 6281 */     if (Trace.isOn) {
/* 6282 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setByteProperty(String,byte)", new Object[] { name, 
/* 6283 */             Byte.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 6287 */     if (this.propertiesReadOnly == true) {
/* 6288 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6289 */       if (Trace.isOn) {
/* 6290 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setByteProperty(String,byte)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6293 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6296 */     if (name == null || name.trim().length() == 0) {
/* 6297 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6298 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6299 */       if (Trace.isOn) {
/* 6300 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setByteProperty(String,byte)", traceRet2, 2);
/*      */       }
/*      */       
/* 6303 */       throw traceRet2;
/*      */     } 
/*      */     
/* 6306 */     if (!isSettablePropertyName(name)) {
/*      */       
/* 6308 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6309 */       if (Trace.isOn) {
/* 6310 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setByteProperty(String,byte)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6313 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6316 */     this.properties.put(name, Byte.valueOf(value));
/*      */     
/* 6318 */     if (Trace.isOn) {
/* 6319 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setByteProperty(String,byte)");
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
/*      */ 
/*      */   
/*      */   public void setBytesProperty(String name, byte[] value) throws JMSException {
/* 6339 */     if (Trace.isOn) {
/* 6340 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 6345 */     if (this.propertiesReadOnly == true) {
/* 6346 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6347 */       if (Trace.isOn) {
/* 6348 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBytesProperty(String,byte [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6351 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6354 */     if (name == null || name.trim().length() == 0) {
/* 6355 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6356 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6357 */       if (Trace.isOn) {
/* 6358 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBytesProperty(String,byte [ ])", traceRet2, 2);
/*      */       }
/*      */       
/* 6361 */       throw traceRet2;
/*      */     } 
/*      */     
/* 6364 */     if (!isSettablePropertyName(name)) {
/*      */       
/* 6366 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6367 */       if (Trace.isOn) {
/* 6368 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBytesProperty(String,byte [ ])", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6371 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6374 */     this.properties.put(name, value);
/*      */     
/* 6376 */     if (Trace.isOn) {
/* 6377 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setBytesProperty(String,byte [ ])");
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
/*      */ 
/*      */   
/*      */   public void setCharProperty(String name, char value) throws JMSException {
/* 6397 */     if (Trace.isOn) {
/* 6398 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setCharProperty(String,char)", new Object[] { name, 
/* 6399 */             Character.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 6403 */     if (this.propertiesReadOnly == true) {
/* 6404 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6405 */       if (Trace.isOn) {
/* 6406 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setCharProperty(String,char)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6409 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6412 */     if (name == null || name.trim().length() == 0) {
/* 6413 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6414 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6415 */       if (Trace.isOn) {
/* 6416 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setCharProperty(String,char)", traceRet2, 2);
/*      */       }
/*      */       
/* 6419 */       throw traceRet2;
/*      */     } 
/*      */     
/* 6422 */     if (!isSettablePropertyName(name)) {
/*      */       
/* 6424 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6425 */       if (Trace.isOn) {
/* 6426 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setCharProperty(String,char)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6429 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6432 */     this.properties.put(name, Character.valueOf(value));
/*      */     
/* 6434 */     if (Trace.isOn) {
/* 6435 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setCharProperty(String,char)");
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
/*      */ 
/*      */   
/*      */   public void setDoubleProperty(String name, double value) throws JMSException {
/* 6455 */     if (Trace.isOn) {
/* 6456 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setDoubleProperty(String,double)", new Object[] { name, 
/* 6457 */             Double.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 6461 */     if (this.propertiesReadOnly == true) {
/* 6462 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6463 */       if (Trace.isOn) {
/* 6464 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setDoubleProperty(String,double)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6467 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6470 */     if (name == null || name.trim().length() == 0) {
/* 6471 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6472 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6473 */       if (Trace.isOn) {
/* 6474 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setDoubleProperty(String,double)", traceRet2, 2);
/*      */       }
/*      */       
/* 6477 */       throw traceRet2;
/*      */     } 
/*      */     
/* 6480 */     if (!isSettablePropertyName(name)) {
/* 6481 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6482 */       if (Trace.isOn) {
/* 6483 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setDoubleProperty(String,double)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6486 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6489 */     this.properties.put(name, Double.valueOf(value));
/*      */     
/* 6491 */     if (Trace.isOn) {
/* 6492 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setDoubleProperty(String,double)");
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
/*      */ 
/*      */   
/*      */   public void setFloatProperty(String name, float value) throws JMSException {
/* 6512 */     if (Trace.isOn) {
/* 6513 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setFloatProperty(String,float)", new Object[] { name, 
/* 6514 */             Float.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 6518 */     if (this.propertiesReadOnly == true) {
/* 6519 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6520 */       if (Trace.isOn) {
/* 6521 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setFloatProperty(String,float)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6524 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6527 */     if (name == null || name.trim().length() == 0) {
/* 6528 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6529 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6530 */       if (Trace.isOn) {
/* 6531 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setFloatProperty(String,float)", traceRet2, 2);
/*      */       }
/*      */       
/* 6534 */       throw traceRet2;
/*      */     } 
/*      */     
/* 6537 */     if (!isSettablePropertyName(name)) {
/* 6538 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6539 */       if (Trace.isOn) {
/* 6540 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setFloatProperty(String,float)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6543 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6546 */     this.properties.put(name, Float.valueOf(value));
/*      */     
/* 6548 */     if (Trace.isOn) {
/* 6549 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setFloatProperty(String,float)");
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
/*      */ 
/*      */   
/*      */   public void setIntProperty(String name, int value) throws JMSException {
/* 6569 */     if (Trace.isOn) {
/* 6570 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setIntProperty(String,int)", new Object[] { name, 
/* 6571 */             Integer.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 6575 */     if (this.propertiesReadOnly == true) {
/* 6576 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 6577 */       if (Trace.isOn) {
/* 6578 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setIntProperty(String,int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6581 */       throw traceRet1;
/*      */     } 
/*      */     
/* 6584 */     if (name == null || name.trim().length() == 0) {
/* 6585 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 6586 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 6587 */       if (Trace.isOn) {
/* 6588 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setIntProperty(String,int)", traceRet2, 2);
/*      */       }
/*      */       
/* 6591 */       throw traceRet2;
/*      */     } 
/*      */     
/* 6594 */     if (!isSettablePropertyName(name)) {
/* 6595 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 6596 */       if (Trace.isOn) {
/* 6597 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setIntProperty(String,int)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 6600 */       throw traceRet3;
/*      */     } 
/*      */     
/* 6603 */     this.properties.put(name, Integer.valueOf(value));
/*      */     
/* 6605 */     if (Trace.isOn) {
/* 6606 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setIntProperty(String,int)");
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
/*      */   public void setJMSCorrelationID(String correlationID) throws JMSException {
/* 6654 */     if (Trace.isOn) {
/* 6655 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSCorrelationID(String)", "setter", correlationID);
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
/* 6666 */     if (correlationID == null) {
/* 6667 */       this.nativeCorrelId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 6677 */     else if (correlationID.startsWith("ID:")) {
/*      */       
/* 6679 */       this.nativeCorrelId = _stringToId(correlationID);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 6687 */       this.nativeCorrelId = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6693 */     this.correlationId = correlationID;
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
/*      */   public void setJMSCorrelationIDAsBytes(byte[] correlID) throws JMSException {
/* 6741 */     if (Trace.isOn) {
/* 6742 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSCorrelationIDAsBytes(byte [ ])", "setter", correlID);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6752 */     if (correlID == null) {
/* 6753 */       this.nativeCorrelId = null;
/* 6754 */       this.correlationId = null;
/*      */     } else {
/*      */       
/* 6757 */       this.nativeCorrelId = new byte[correlID.length];
/* 6758 */       System.arraycopy(correlID, 0, this.nativeCorrelId, 0, correlID.length);
/*      */ 
/*      */ 
/*      */       
/* 6762 */       this.correlationId = null;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
/* 6783 */     if (Trace.isOn) {
/* 6784 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSDeliveryMode(int)", "setter", 
/* 6785 */           Integer.valueOf(deliveryMode));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 6790 */     if (deliveryMode == -2) {
/* 6791 */       this.deliveryMode = 2;
/* 6792 */       this.hideDeliveryMode = true;
/*      */     }
/* 6794 */     else if (deliveryMode == -3) {
/* 6795 */       this.deliveryMode = -2;
/* 6796 */       this.hideDeliveryMode = true;
/*      */     } else {
/*      */       
/* 6799 */       this.deliveryMode = deliveryMode;
/* 6800 */       this.hideDeliveryMode = false;
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
/*      */   public void setJMSExpiration(long expiration) throws JMSException {
/* 6818 */     if (Trace.isOn) {
/* 6819 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSExpiration(long)", "setter", 
/* 6820 */           Long.valueOf(expiration));
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
/* 6833 */     this.expiration = expiration;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSMessageID(String id) throws JMSException {
/* 6857 */     if (Trace.isOn) {
/* 6858 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSMessageID(String)", "setter", id);
/*      */     }
/*      */ 
/*      */     
/* 6862 */     this.messageId = id;
/* 6863 */     this.nativeMessageId = null;
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
/*      */ 
/*      */   
/*      */   public void setJMSPriority(int priority) throws JMSException {
/* 6884 */     if (Trace.isOn) {
/* 6885 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSPriority(int)", "setter", 
/* 6886 */           Integer.valueOf(priority));
/*      */     }
/*      */     
/* 6889 */     this.priority = priority;
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
/*      */   public void setJMSRedelivered(boolean redelivered) throws JMSException {
/* 6905 */     if (Trace.isOn) {
/* 6906 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSRedelivered(boolean)", "setter", 
/* 6907 */           Boolean.valueOf(redelivered));
/*      */     }
/*      */     
/* 6910 */     this.redelivered = redelivered;
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
/*      */   public void setJMSTimestamp(long timestamp) throws JMSException {
/* 6926 */     if (Trace.isOn) {
/* 6927 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSTimestamp(long)", "setter", 
/* 6928 */           Long.valueOf(timestamp));
/*      */     }
/* 6930 */     this.timestamp = timestamp;
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
/*      */   public void setJMSType(String type) throws JMSException {
/*      */     String path;
/* 6949 */     if (Trace.isOn) {
/* 6950 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", new Object[] { type });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6956 */     if (type == null || !type.startsWith("mcd://")) {
/* 6957 */       this.type = type;
/* 6958 */       this.msDomain = null;
/* 6959 */       this.msSet = null;
/* 6960 */       this.msType = null;
/* 6961 */       this.msFormat = null;
/* 6962 */       if (Trace.isOn) {
/* 6963 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
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
/* 6985 */     if (!"jms_text".equals(this.messageClass) && !"jms_bytes".equals(this.messageClass)) {
/* 6986 */       JMSException traceRet1 = newJMSException(1028, "JMSType", type);
/* 6987 */       if (Trace.isOn) {
/* 6988 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 6991 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 6995 */     String msDomain1 = null;
/* 6996 */     String msSet1 = null;
/* 6997 */     String msType1 = null;
/* 6998 */     String msFormat1 = null;
/*      */ 
/*      */ 
/*      */     
/* 7002 */     int index = 6;
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
/* 7017 */     int query = type.indexOf('?', index);
/*      */     
/* 7019 */     if (query == -1) {
/* 7020 */       path = type;
/*      */     } else {
/*      */       
/* 7023 */       path = type.substring(0, query);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 7028 */     int separator = path.indexOf('/', index);
/*      */     
/* 7030 */     if (separator == -1) {
/*      */ 
/*      */ 
/*      */       
/* 7034 */       if (path.length() <= 6) {
/* 7035 */         JMSException traceRet2 = newJMSException(1028, "JMSType", type);
/* 7036 */         if (Trace.isOn) {
/* 7037 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 7040 */         throw traceRet2;
/*      */       } 
/* 7042 */       msDomain1 = path.substring(index);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 7048 */       if (separator == index) {
/* 7049 */         JMSException traceRet3 = newJMSException(1028, "JMSType", type);
/* 7050 */         if (Trace.isOn) {
/* 7051 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 7054 */         throw traceRet3;
/*      */       } 
/*      */       
/* 7057 */       msDomain1 = path.substring(index, separator);
/* 7058 */       index = separator + 1;
/*      */ 
/*      */       
/* 7061 */       separator = path.indexOf('/', index);
/*      */       
/* 7063 */       if (separator == -1) {
/* 7064 */         msSet1 = path.substring(index);
/*      */       } else {
/*      */         
/* 7067 */         msSet1 = path.substring(index, separator);
/* 7068 */         index = separator + 1;
/* 7069 */         msType1 = path.substring(index);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 7075 */     if (query > 0)
/*      */     {
/*      */       
/* 7078 */       if (type.regionMatches(true, query, "?format=", 0, 8)) {
/* 7079 */         msFormat1 = type.substring(query + 8);
/*      */         
/* 7081 */         if (msFormat1.indexOf('&') != -1) {
/* 7082 */           JMSException traceRet5 = newJMSException(1028, "JMSType", type);
/* 7083 */           if (Trace.isOn) {
/* 7084 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", (Throwable)traceRet5, 4);
/*      */           }
/*      */           
/* 7087 */           throw traceRet5;
/*      */         } 
/*      */       } else {
/*      */         
/* 7091 */         JMSException traceRet6 = newJMSException(1028, "JMSType", type);
/* 7092 */         if (Trace.isOn) {
/* 7093 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", (Throwable)traceRet6, 5);
/*      */         }
/*      */         
/* 7096 */         throw traceRet6;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7102 */     this.type = type;
/* 7103 */     this.msDomain = msDomain1;
/* 7104 */     this.msSet = msSet1;
/* 7105 */     this.msType = msType1;
/* 7106 */     this.msFormat = msFormat1;
/*      */     
/* 7108 */     if (Trace.isOn) {
/* 7109 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSType(String)", 2);
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
/*      */   public void setLongProperty(String name, long value) throws JMSException {
/* 7127 */     if (Trace.isOn) {
/* 7128 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLongProperty(String,long)", new Object[] { name, 
/* 7129 */             Long.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 7133 */     if (this.propertiesReadOnly == true) {
/* 7134 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 7135 */       if (Trace.isOn) {
/* 7136 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLongProperty(String,long)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 7139 */       throw traceRet1;
/*      */     } 
/*      */     
/* 7142 */     if (name == null || name.trim().length() == 0) {
/* 7143 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 7144 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 7145 */       if (Trace.isOn) {
/* 7146 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLongProperty(String,long)", traceRet2, 2);
/*      */       }
/*      */       
/* 7149 */       throw traceRet2;
/*      */     } 
/*      */     
/* 7152 */     if (!isSettablePropertyName(name)) {
/* 7153 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 7154 */       if (Trace.isOn) {
/* 7155 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLongProperty(String,long)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 7158 */       throw traceRet3;
/*      */     } 
/*      */     
/* 7161 */     this.properties.put(name, Long.valueOf(value));
/*      */     
/* 7163 */     if (Trace.isOn) {
/* 7164 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setLongProperty(String,long)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectProperty(String name, Object value) throws JMSException {
/* 7187 */     if (Trace.isOn) {
/* 7188 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setObjectProperty(String,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7193 */     if (this.propertiesReadOnly == true) {
/* 7194 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 7195 */       if (Trace.isOn) {
/* 7196 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setObjectProperty(String,Object)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 7199 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 7203 */     if (name == null || name.trim().length() == 0) {
/* 7204 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 7205 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 7206 */       if (Trace.isOn) {
/* 7207 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setObjectProperty(String,Object)", traceRet2, 2);
/*      */       }
/*      */       
/* 7210 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 7215 */     if (!isSettablePropertyName(name)) {
/* 7216 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 7217 */       if (Trace.isOn) {
/* 7218 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setObjectProperty(String,Object)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 7221 */       throw traceRet3;
/*      */     } 
/*      */     
/* 7224 */     if (value instanceof byte[]) {
/*      */ 
/*      */       
/* 7227 */       this.properties.put(name, value);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 7232 */       if (!(value instanceof String) && !(value instanceof Number) && !(value instanceof Boolean) && value != null) {
/*      */         
/* 7234 */         MessageFormatException traceRet4 = new MessageFormatException("Invalid Object type");
/* 7235 */         if (Trace.isOn) {
/* 7236 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setObjectProperty(String,Object)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 7239 */         throw traceRet4;
/*      */       } 
/* 7241 */       this.properties.put(name, value);
/*      */     } 
/*      */     
/* 7244 */     if (Trace.isOn) {
/* 7245 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setObjectProperty(String,Object)");
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
/*      */   public void setShortProperty(String name, short value) throws JMSException {
/* 7263 */     if (Trace.isOn) {
/* 7264 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setShortProperty(String,short)", new Object[] { name, 
/* 7265 */             Short.valueOf(value) });
/*      */     }
/*      */ 
/*      */     
/* 7269 */     if (this.propertiesReadOnly == true) {
/* 7270 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 7271 */       if (Trace.isOn) {
/* 7272 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setShortProperty(String,short)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 7275 */       throw traceRet1;
/*      */     } 
/*      */     
/* 7278 */     if (name == null || name.trim().length() == 0) {
/* 7279 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 7280 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 7281 */       if (Trace.isOn) {
/* 7282 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setShortProperty(String,short)", traceRet2, 2);
/*      */       }
/*      */       
/* 7285 */       throw traceRet2;
/*      */     } 
/*      */     
/* 7288 */     if (!isSettablePropertyName(name)) {
/* 7289 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 7290 */       if (Trace.isOn) {
/* 7291 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setShortProperty(String,short)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 7294 */       throw traceRet3;
/*      */     } 
/*      */     
/* 7297 */     this.properties.put(name, Short.valueOf(value));
/*      */     
/* 7299 */     if (Trace.isOn) {
/* 7300 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setShortProperty(String,short)");
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
/*      */   public void setStringProperty(String name, String value) throws JMSException {
/* 7318 */     if (Trace.isOn) {
/* 7319 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setStringProperty(String,String)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7324 */     if (this.propertiesReadOnly == true) {
/* 7325 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 7326 */       if (Trace.isOn) {
/* 7327 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setStringProperty(String,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 7330 */       throw traceRet1;
/*      */     } 
/*      */     
/* 7333 */     if (name == null || name.trim().length() == 0) {
/* 7334 */       String exString = this.jmsStrings.getErrorMessage(1020, name);
/* 7335 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(exString);
/* 7336 */       if (Trace.isOn) {
/* 7337 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setStringProperty(String,String)", traceRet2, 2);
/*      */       }
/*      */       
/* 7340 */       throw traceRet2;
/*      */     } 
/*      */     
/* 7343 */     if (!isSettablePropertyName(name)) {
/* 7344 */       JMSException traceRet3 = newMessageFormatException(1020, name);
/* 7345 */       if (Trace.isOn) {
/* 7346 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setStringProperty(String,String)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 7349 */       throw traceRet3;
/*      */     } 
/*      */     
/* 7352 */     this.properties.put(name, value);
/*      */     
/* 7354 */     if (Trace.isOn) {
/* 7355 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setStringProperty(String,String)");
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
/*      */   public void removeProperty(String name) throws JMSException {
/* 7373 */     if (Trace.isOn) {
/* 7374 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "removeProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/* 7377 */     this.properties.remove(name);
/* 7378 */     if (Trace.isOn) {
/* 7379 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "removeProperty(String)");
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
/*      */   private void _setJMSDestinationAsString(MQSession df, String destString) throws JMSException {
/* 7397 */     if (Trace.isOn) {
/* 7398 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSDestinationAsString(MQSession,String)", new Object[] { df, destString });
/*      */     }
/*      */ 
/*      */     
/* 7402 */     if (destString == null) {
/* 7403 */       this.destination = null;
/* 7404 */       this.destinationString = null;
/* 7405 */       if (this.replyTo != null || this.replyToString == null) {
/* 7406 */         this.destinationFactory = null;
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 7412 */       if (df == null || (this.destinationFactory != null && df != this.destinationFactory)) {
/* 7413 */         if (Trace.isOn) {
/* 7414 */           Trace.traceData(this, "Internal error: Invalid destination factory, or did not match existing df", null);
/*      */         }
/* 7416 */         JMSException traceRet1 = newJMSException(1001);
/* 7417 */         if (Trace.isOn) {
/* 7418 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSDestinationAsString(MQSession,String)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 7421 */         throw traceRet1;
/*      */       } 
/*      */       
/* 7424 */       this.destination = null;
/* 7425 */       this.destinationString = destString;
/* 7426 */       this.destinationFactory = df;
/*      */     } 
/*      */     
/* 7429 */     if (Trace.isOn) {
/* 7430 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSDestinationAsString(MQSession,String)");
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
/*      */   private void _setJMSReplyToAsString(MQSession df, String rToString) throws JMSException {
/* 7448 */     if (Trace.isOn) {
/* 7449 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSReplyToAsString(MQSession,String)", new Object[] { df, rToString });
/*      */     }
/*      */ 
/*      */     
/* 7453 */     if (rToString == null || rToString.trim().length() == 0) {
/* 7454 */       this.replyTo = null;
/* 7455 */       this.replyToString = null;
/* 7456 */       if (this.destination != null || this.destinationString == null) {
/* 7457 */         this.destinationFactory = null;
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 7463 */       if (df == null || (this.destinationFactory != null && df != this.destinationFactory)) {
/* 7464 */         if (Trace.isOn) {
/* 7465 */           Trace.traceData(this, "Internal error: Invalid destination factory, or did not match existing df", null);
/*      */         }
/* 7467 */         JMSException traceRet1 = newJMSException(1001);
/* 7468 */         if (Trace.isOn) {
/* 7469 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSReplyToAsString(MQSession,String)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 7472 */         throw traceRet1;
/*      */       } 
/*      */       
/* 7475 */       this.replyTo = null;
/* 7476 */       this.replyToString = rToString;
/* 7477 */       this.destinationFactory = df;
/*      */     } 
/*      */     
/* 7480 */     if (Trace.isOn) {
/* 7481 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSReplyToAsString(MQSession,String)");
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
/*      */   public boolean _hasJMSReplyTo() {
/* 7497 */     if (Trace.isOn) {
/* 7498 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_hasJMSReplyTo()");
/*      */     }
/*      */     
/* 7501 */     boolean traceRet1 = (this.replyToString != null || this.replyTo != null);
/* 7502 */     if (Trace.isOn) {
/* 7503 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_hasJMSReplyTo()", 
/* 7504 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 7506 */     return traceRet1;
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
/*      */   
/*      */   private Object _getProperty(String name) throws JMSException {
/* 7526 */     if (Trace.isOn) {
/* 7527 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7532 */     Object value = this.properties.get(name);
/*      */ 
/*      */ 
/*      */     
/* 7536 */     if (value == HELD_INTERNAL) {
/* 7537 */       value = getInternalPropForName(name);
/*      */     }
/*      */     
/* 7540 */     if (Trace.isOn) {
/* 7541 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getProperty(String)", value);
/*      */     }
/*      */     
/* 7544 */     return value;
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
/*      */   boolean toBoolean(Object value) throws JMSException {
/* 7557 */     if (Trace.isOn) {
/* 7558 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBoolean(Object)", new Object[] { value });
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
/* 7570 */     if (value == null) {
/* 7571 */       if (Trace.isOn) {
/* 7572 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBoolean(Object)", 
/* 7573 */             Boolean.valueOf(false), 1);
/*      */       }
/* 7575 */       return false;
/*      */     } 
/* 7577 */     if (value instanceof Boolean) {
/* 7578 */       boolean traceRet1 = ((Boolean)value).booleanValue();
/* 7579 */       if (Trace.isOn) {
/* 7580 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBoolean(Object)", 
/* 7581 */             Boolean.valueOf(traceRet1), 2);
/*      */       }
/* 7583 */       return traceRet1;
/*      */     } 
/* 7585 */     if (value instanceof String) {
/* 7586 */       boolean traceRet2 = Boolean.valueOf((String)value).booleanValue();
/* 7587 */       if (Trace.isOn) {
/* 7588 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBoolean(Object)", 
/* 7589 */             Boolean.valueOf(traceRet2), 3);
/*      */       }
/* 7591 */       return traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 7595 */     JMSException traceRet3 = newMessageFormatException(1017, value.getClass());
/* 7596 */     if (Trace.isOn) {
/* 7597 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBoolean(Object)", (Throwable)traceRet3);
/*      */     }
/*      */     
/* 7600 */     throw traceRet3;
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
/*      */   byte toByte(Object value) throws JMSException {
/* 7614 */     if (Trace.isOn) {
/* 7615 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toByte(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7622 */     if (value == null) {
/* 7623 */       NumberFormatException traceRet1 = new NumberFormatException();
/* 7624 */       if (Trace.isOn) {
/* 7625 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toByte(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 7628 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7638 */     if (value instanceof Byte) {
/* 7639 */       byte traceRet2 = ((Byte)value).byteValue();
/* 7640 */       if (Trace.isOn) {
/* 7641 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toByte(Object)", 
/* 7642 */             Byte.valueOf(traceRet2), 1);
/*      */       }
/* 7644 */       return traceRet2;
/*      */     } 
/* 7646 */     if (value instanceof String) {
/* 7647 */       byte traceRet3 = Byte.valueOf((String)value).byteValue();
/* 7648 */       if (Trace.isOn) {
/* 7649 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toByte(Object)", 
/* 7650 */             Byte.valueOf(traceRet3), 2);
/*      */       }
/* 7652 */       return traceRet3;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 7657 */     JMSException traceRet4 = newMessageFormatException(1017, value.getClass());
/* 7658 */     if (Trace.isOn) {
/* 7659 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toByte(Object)", (Throwable)traceRet4, 2);
/*      */     }
/*      */     
/* 7662 */     throw traceRet4;
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
/*      */   byte[] toBytes(Object value) throws JMSException {
/* 7677 */     if (Trace.isOn) {
/* 7678 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBytes(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7687 */     if (value == null) {
/* 7688 */       if (Trace.isOn) {
/* 7689 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBytes(Object)", null, 1);
/*      */       }
/*      */       
/* 7692 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7701 */     if (value instanceof byte[]) {
/* 7702 */       byte[] traceRet1 = (byte[])value;
/* 7703 */       if (Trace.isOn) {
/* 7704 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBytes(Object)", traceRet1, 2);
/*      */       }
/*      */       
/* 7707 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 7711 */     JMSException traceRet2 = newMessageFormatException(1017, value.getClass());
/* 7712 */     if (Trace.isOn) {
/* 7713 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toBytes(Object)", (Throwable)traceRet2);
/*      */     }
/*      */     
/* 7716 */     throw traceRet2;
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
/*      */   char toChar(Object value) throws JMSException {
/* 7730 */     if (Trace.isOn) {
/* 7731 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toChar(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7736 */     if (value == null) {
/* 7737 */       NullPointerException traceRet1 = new NullPointerException();
/* 7738 */       if (Trace.isOn) {
/* 7739 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toChar(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 7742 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7751 */     if (value instanceof Character) {
/* 7752 */       char traceRet2 = ((Character)value).charValue();
/* 7753 */       if (Trace.isOn) {
/* 7754 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toChar(Object)", 
/* 7755 */             Character.valueOf(traceRet2));
/*      */       }
/* 7757 */       return traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 7761 */     JMSException traceRet3 = newMessageFormatException(1017, value.getClass());
/* 7762 */     if (Trace.isOn) {
/* 7763 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toChar(Object)", (Throwable)traceRet3, 2);
/*      */     }
/*      */     
/* 7766 */     throw traceRet3;
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
/*      */   double toDouble(Object value) throws JMSException {
/* 7780 */     if (Trace.isOn) {
/* 7781 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toDouble(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7786 */     if (value == null) {
/* 7787 */       NullPointerException traceRet1 = new NullPointerException();
/* 7788 */       if (Trace.isOn) {
/* 7789 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toDouble(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 7792 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7801 */     if (value instanceof Double) {
/* 7802 */       double traceRet2 = ((Double)value).doubleValue();
/* 7803 */       if (Trace.isOn) {
/* 7804 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toDouble(Object)", 
/* 7805 */             Double.valueOf(traceRet2), 1);
/*      */       }
/* 7807 */       return traceRet2;
/*      */     } 
/* 7809 */     if (value instanceof Float) {
/* 7810 */       double traceRet3 = ((Float)value).doubleValue();
/* 7811 */       if (Trace.isOn) {
/* 7812 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toDouble(Object)", 
/* 7813 */             Double.valueOf(traceRet3), 2);
/*      */       }
/* 7815 */       return traceRet3;
/*      */     } 
/* 7817 */     if (value instanceof String) {
/* 7818 */       double traceRet4 = Double.valueOf((String)value).doubleValue();
/* 7819 */       if (Trace.isOn) {
/* 7820 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toDouble(Object)", 
/* 7821 */             Double.valueOf(traceRet4), 3);
/*      */       }
/* 7823 */       return traceRet4;
/*      */     } 
/*      */ 
/*      */     
/* 7827 */     JMSException traceRet5 = newMessageFormatException(1017, value.getClass());
/* 7828 */     if (Trace.isOn) {
/* 7829 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toDouble(Object)", (Throwable)traceRet5, 2);
/*      */     }
/*      */     
/* 7832 */     throw traceRet5;
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
/*      */   float toFloat(Object value) throws JMSException {
/* 7846 */     if (Trace.isOn) {
/* 7847 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toFloat(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 7851 */     if (value == null) {
/* 7852 */       NullPointerException traceRet1 = new NullPointerException();
/* 7853 */       if (Trace.isOn) {
/* 7854 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toFloat(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 7857 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7866 */     if (value instanceof Float) {
/* 7867 */       float traceRet2 = ((Float)value).floatValue();
/* 7868 */       if (Trace.isOn) {
/* 7869 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toFloat(Object)", 
/* 7870 */             Float.valueOf(traceRet2), 1);
/*      */       }
/* 7872 */       return traceRet2;
/*      */     } 
/* 7874 */     if (value instanceof String) {
/* 7875 */       float traceRet3 = Float.valueOf((String)value).floatValue();
/* 7876 */       if (Trace.isOn) {
/* 7877 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toFloat(Object)", 
/* 7878 */             Float.valueOf(traceRet3), 2);
/*      */       }
/* 7880 */       return traceRet3;
/*      */     } 
/*      */ 
/*      */     
/* 7884 */     JMSException traceRet4 = newMessageFormatException(1017, value.getClass());
/* 7885 */     if (Trace.isOn) {
/* 7886 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toFloat(Object)", (Throwable)traceRet4, 2);
/*      */     }
/*      */     
/* 7889 */     throw traceRet4;
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
/*      */   int toInt(Object value) throws JMSException {
/* 7903 */     if (Trace.isOn) {
/* 7904 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 7908 */     if (value == null) {
/* 7909 */       NumberFormatException traceRet1 = new NumberFormatException();
/* 7910 */       if (Trace.isOn) {
/* 7911 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 7914 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7923 */     if (value instanceof Integer) {
/* 7924 */       int traceRet2 = ((Integer)value).intValue();
/* 7925 */       if (Trace.isOn) {
/* 7926 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", 
/* 7927 */             Integer.valueOf(traceRet2), 1);
/*      */       }
/* 7929 */       return traceRet2;
/*      */     } 
/* 7931 */     if (value instanceof Short) {
/* 7932 */       int traceRet3 = ((Short)value).intValue();
/* 7933 */       if (Trace.isOn) {
/* 7934 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", 
/* 7935 */             Integer.valueOf(traceRet3), 2);
/*      */       }
/* 7937 */       return traceRet3;
/*      */     } 
/* 7939 */     if (value instanceof Byte) {
/* 7940 */       int traceRet4 = ((Byte)value).intValue();
/* 7941 */       if (Trace.isOn) {
/* 7942 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", 
/* 7943 */             Integer.valueOf(traceRet4), 3);
/*      */       }
/* 7945 */       return traceRet4;
/*      */     } 
/* 7947 */     if (value instanceof String) {
/* 7948 */       int traceRet5 = Integer.parseInt((String)value);
/* 7949 */       if (Trace.isOn) {
/* 7950 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", 
/* 7951 */             Integer.valueOf(traceRet5), 4);
/*      */       }
/* 7953 */       return traceRet5;
/*      */     } 
/*      */ 
/*      */     
/* 7957 */     JMSException traceRet6 = newMessageFormatException(1017, value.getClass());
/* 7958 */     if (Trace.isOn) {
/* 7959 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toInt(Object)", (Throwable)traceRet6, 2);
/*      */     }
/*      */     
/* 7962 */     throw traceRet6;
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
/*      */   long toLong(Object value) throws JMSException {
/* 7975 */     if (Trace.isOn) {
/* 7976 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 7980 */     if (value == null) {
/* 7981 */       NumberFormatException traceRet1 = new NumberFormatException();
/* 7982 */       if (Trace.isOn) {
/* 7983 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 7986 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7995 */     if (value instanceof Long) {
/* 7996 */       long traceRet2 = ((Long)value).longValue();
/* 7997 */       if (Trace.isOn) {
/* 7998 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", 
/* 7999 */             Long.valueOf(traceRet2), 1);
/*      */       }
/* 8001 */       return traceRet2;
/*      */     } 
/* 8003 */     if (value instanceof Integer) {
/* 8004 */       long traceRet3 = ((Integer)value).longValue();
/* 8005 */       if (Trace.isOn) {
/* 8006 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", 
/* 8007 */             Long.valueOf(traceRet3), 2);
/*      */       }
/* 8009 */       return traceRet3;
/*      */     } 
/* 8011 */     if (value instanceof Short) {
/* 8012 */       long traceRet4 = ((Short)value).longValue();
/* 8013 */       if (Trace.isOn) {
/* 8014 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", 
/* 8015 */             Long.valueOf(traceRet4), 3);
/*      */       }
/* 8017 */       return traceRet4;
/*      */     } 
/* 8019 */     if (value instanceof Byte) {
/* 8020 */       long traceRet5 = ((Byte)value).longValue();
/* 8021 */       if (Trace.isOn) {
/* 8022 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", 
/* 8023 */             Long.valueOf(traceRet5), 4);
/*      */       }
/* 8025 */       return traceRet5;
/*      */     } 
/* 8027 */     if (value instanceof String) {
/* 8028 */       long traceRet6 = Long.parseLong((String)value);
/* 8029 */       if (Trace.isOn) {
/* 8030 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", 
/* 8031 */             Long.valueOf(traceRet6), 5);
/*      */       }
/* 8033 */       return traceRet6;
/*      */     } 
/*      */ 
/*      */     
/* 8037 */     JMSException traceRet7 = newMessageFormatException(1017, value.getClass());
/* 8038 */     if (Trace.isOn) {
/* 8039 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toLong(Object)", (Throwable)traceRet7, 2);
/*      */     }
/*      */     
/* 8042 */     throw traceRet7;
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
/*      */   short toShort(Object value) throws JMSException {
/* 8056 */     if (Trace.isOn) {
/* 8057 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toShort(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 8061 */     if (value == null) {
/* 8062 */       NumberFormatException traceRet1 = new NumberFormatException();
/* 8063 */       if (Trace.isOn) {
/* 8064 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toShort(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 8067 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8076 */     if (value instanceof Short) {
/* 8077 */       short traceRet2 = ((Short)value).shortValue();
/* 8078 */       if (Trace.isOn) {
/* 8079 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toShort(Object)", 
/* 8080 */             Short.valueOf(traceRet2), 1);
/*      */       }
/* 8082 */       return traceRet2;
/*      */     } 
/* 8084 */     if (value instanceof Byte) {
/* 8085 */       short traceRet3 = ((Byte)value).shortValue();
/* 8086 */       if (Trace.isOn) {
/* 8087 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toShort(Object)", 
/* 8088 */             Short.valueOf(traceRet3), 2);
/*      */       }
/* 8090 */       return traceRet3;
/*      */     } 
/* 8092 */     if (value instanceof String) {
/* 8093 */       short traceRet4 = Short.valueOf((String)value).shortValue();
/* 8094 */       if (Trace.isOn) {
/* 8095 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toShort(Object)", 
/* 8096 */             Short.valueOf(traceRet4), 3);
/*      */       }
/* 8098 */       return traceRet4;
/*      */     } 
/*      */ 
/*      */     
/* 8102 */     JMSException traceRet5 = newMessageFormatException(1017, value.getClass());
/* 8103 */     if (Trace.isOn) {
/* 8104 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toShort(Object)", (Throwable)traceRet5, 2);
/*      */     }
/*      */     
/* 8107 */     throw traceRet5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 8118 */     if (Trace.isOn) {
/* 8119 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toString()");
/*      */     }
/* 8121 */     StringBuffer retval = new StringBuffer();
/*      */     
/*      */     try {
/* 8124 */       retval.append("\n" + this.jmsStrings.getMessage(1013) + ": " + this.messageClass);
/* 8125 */       retval.append("\n  JMSType:         " + this.type);
/* 8126 */       retval.append("\n  JMSDeliveryMode: " + this.deliveryMode);
/* 8127 */       retval.append("\n  JMSExpiration:   " + this.expiration);
/* 8128 */       retval.append("\n  JMSPriority:     " + this.priority);
/* 8129 */       retval.append("\n  JMSMessageID:    " + getJMSMessageID());
/* 8130 */       retval.append("\n  JMSTimestamp:    " + this.timestamp);
/* 8131 */       retval.append("\n  JMSCorrelationID:" + getJMSCorrelationID());
/* 8132 */       retval.append("\n  JMSDestination:  " + getJMSDestinationAsString());
/* 8133 */       retval.append("\n  JMSReplyTo:      " + getJMSReplyToAsString());
/* 8134 */       retval.append("\n  JMSRedelivered:  " + this.redelivered);
/*      */       
/* 8136 */       Enumeration<String> propertyNames = getPropertyNames();
/*      */       
/* 8138 */       while (propertyNames.hasMoreElements()) {
/* 8139 */         String name = propertyNames.nextElement();
/* 8140 */         retval.append("\n  " + name + ":" + getObjectProperty(name));
/*      */       }
/*      */     
/* 8143 */     } catch (JMSException ex) {
/* 8144 */       if (Trace.isOn) {
/* 8145 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toString()", (Throwable)ex);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8154 */     String traceRet1 = retval.toString();
/* 8155 */     if (Trace.isOn) {
/* 8156 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "toString()", traceRet1);
/*      */     }
/*      */     
/* 8159 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 8170 */     if (Trace.isOn) {
/* 8171 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */     
/* 8175 */     if (this.jmsStrings != null) {
/* 8176 */       this.jmsStringResourcesClassName = this.jmsStrings.getClass().getName();
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
/* 8187 */     String[] possiblyInternalProps = { "JMSXUserID", "JMSXAppID", "JMS_IBM_PutDate", "JMS_IBM_PutTime", "JMSXDeliveryCount", "JMS_IBM_MsgType", "JMSXGroupSeq", "JMS_IBM_PutApplType", "JMS_IBM_Feedback", "JMS_IBM_Report_Exception", "JMS_IBM_Report_Expiration", "JMS_IBM_Report_COA", "JMS_IBM_Report_COD", "JMS_IBM_Report_PAN", "JMS_IBM_Report_NAN", "JMS_IBM_Report_Pass_Correl_ID", "JMS_IBM_Report_Pass_Msg_ID", "JMS_IBM_Report_Discard_Msg", "JMS_IBM_Last_Msg_In_Group" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 8195 */       for (int i = 0; i < possiblyInternalProps.length; i++) {
/* 8196 */         String propName = possiblyInternalProps[i];
/* 8197 */         if (HELD_INTERNAL.equals(propName)) {
/* 8198 */           this.properties.put(propName, getInternalPropForName(propName));
/*      */         }
/*      */       }
/*      */     
/* 8202 */     } catch (JMSException je) {
/* 8203 */       if (Trace.isOn) {
/* 8204 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8210 */       IOException traceRet2 = new IOException(je.getMessage());
/* 8211 */       if (Trace.isOn) {
/* 8212 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "writeObject(java.io.ObjectOutputStream)", traceRet2);
/*      */       }
/*      */       
/* 8215 */       throw traceRet2;
/*      */     } 
/*      */     
/* 8218 */     out.defaultWriteObject();
/* 8219 */     if (Trace.isOn) {
/* 8220 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "writeObject(java.io.ObjectOutputStream)");
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
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 8233 */     if (Trace.isOn) {
/* 8234 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 8237 */     in.defaultReadObject();
/*      */     
/* 8239 */     if (this.jmsStringResourcesClassName != null) {
/*      */       try {
/* 8241 */         Class<JMSStringResources> jsrClass = CSSystem.dynamicLoadClass(this.jmsStringResourcesClassName, getClass(), false);
/* 8242 */         this.jmsStrings = jsrClass.newInstance();
/*      */       }
/* 8244 */       catch (Exception e) {
/* 8245 */         if (Trace.isOn) {
/* 8246 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "readObject(java.io.ObjectInputStream)", e);
/*      */         }
/*      */ 
/*      */         
/* 8250 */         ClassNotFoundException traceRet1 = new ClassNotFoundException("unable to recreate JMSStringResources from " + this.jmsStringResourcesClassName);
/*      */         
/* 8252 */         if (Trace.isOn) {
/* 8253 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "readObject(java.io.ObjectInputStream)", traceRet1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 8258 */         throw traceRet1;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8266 */     if (this.userIDAsBytes == null) {
/* 8267 */       this.userIDAsBytes = new byte[12];
/* 8268 */       Arrays.fill(this.userIDAsBytes, (byte)32);
/*      */     } 
/*      */     
/* 8271 */     if (this.putApplNameAsBytes == null) {
/* 8272 */       this.putApplNameAsBytes = new byte[28];
/* 8273 */       Arrays.fill(this.userIDAsBytes, (byte)32);
/*      */     } 
/*      */     
/* 8276 */     if (this.putDateAsBytes == null) {
/* 8277 */       this.putDateAsBytes = new byte[8];
/* 8278 */       Arrays.fill(this.putDateAsBytes, (byte)32);
/*      */     } 
/*      */     
/* 8281 */     if (this.putTimeAsBytes == null) {
/* 8282 */       this.putTimeAsBytes = new byte[8];
/* 8283 */       Arrays.fill(this.putTimeAsBytes, (byte)32);
/*      */     } 
/* 8285 */     if (Trace.isOn) {
/* 8286 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "readObject(java.io.ObjectInputStream)");
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
/*      */   public void setPSCTopic(String psTopic) throws JMSException {
/* 8299 */     if (Trace.isOn) {
/* 8300 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setPSCTopic(String)", "setter", psTopic);
/*      */     }
/*      */     
/* 8303 */     this.psTopic = psTopic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPSCConnID(String psConnID) throws JMSException {
/* 8314 */     if (Trace.isOn) {
/* 8315 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setPSCConnID(String)", "setter", psConnID);
/*      */     }
/*      */     
/* 8318 */     this.psConnID = psConnID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String _getJMSIBMArmCorrelator() {
/* 8327 */     if (Trace.isOn) {
/* 8328 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMArmCorrelator()");
/*      */     }
/*      */     
/* 8331 */     String retVal = null;
/* 8332 */     if (HELD_INTERNAL.equals("JMS_IBM_ArmCorrelator")) {
/* 8333 */       retVal = this.armCorrelator;
/*      */     } else {
/*      */       
/*      */       try {
/* 8337 */         retVal = getStringProperty("JMS_IBM_ArmCorrelator");
/*      */       }
/* 8339 */       catch (JMSException je) {
/* 8340 */         if (Trace.isOn) {
/* 8341 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMArmCorrelator()", (Throwable)je);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 8348 */     if (Trace.isOn) {
/* 8349 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMArmCorrelator()", retVal);
/*      */     }
/*      */     
/* 8352 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String _getJMSIBMWrmCorrelator() {
/* 8361 */     if (Trace.isOn) {
/* 8362 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMWrmCorrelator()");
/*      */     }
/*      */     
/* 8365 */     String retVal = null;
/* 8366 */     if (HELD_INTERNAL.equals("JMS_IBM_RMCorrelator")) {
/* 8367 */       retVal = this.wrmCorrelator;
/*      */     } else {
/*      */       
/*      */       try {
/* 8371 */         retVal = getStringProperty("JMS_IBM_RMCorrelator");
/*      */       }
/* 8373 */       catch (JMSException je) {
/* 8374 */         if (Trace.isOn) {
/* 8375 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMWrmCorrelator()", (Throwable)je);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 8382 */     if (Trace.isOn) {
/* 8383 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_getJMSIBMWrmCorrelator()", retVal);
/*      */     }
/*      */     
/* 8386 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void _setJMSIBMArmCorrelator(String correlator) {
/* 8395 */     if (Trace.isOn) {
/* 8396 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMArmCorrelator(String)", new Object[] { correlator });
/*      */     }
/*      */     
/* 8399 */     this.armCorrelator = correlator;
/* 8400 */     this.properties.put("JMS_IBM_ArmCorrelator", HELD_INTERNAL);
/* 8401 */     if (Trace.isOn) {
/* 8402 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMArmCorrelator(String)");
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
/*      */   public void _setJMSIBMWrmCorrelator(String correlator) {
/* 8414 */     if (Trace.isOn) {
/* 8415 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMWrmCorrelator(String)", new Object[] { correlator });
/*      */     }
/*      */     
/* 8418 */     this.wrmCorrelator = correlator;
/* 8419 */     this.properties.put("JMS_IBM_RMCorrelator", HELD_INTERNAL);
/* 8420 */     if (Trace.isOn) {
/* 8421 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "_setJMSIBMWrmCorrelator(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGotByConsume(boolean consumed) {
/* 8432 */     if (Trace.isOn) {
/* 8433 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setGotByConsume(boolean)", "setter", 
/* 8434 */           Boolean.valueOf(consumed));
/*      */     }
/* 8436 */     this.gotByConsume = consumed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setGotByConsume() {
/* 8444 */     if (Trace.isOn) {
/* 8445 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setGotByConsume()");
/*      */     }
/*      */     
/* 8448 */     if (Trace.isOn) {
/* 8449 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setGotByConsume()", 
/* 8450 */           Boolean.valueOf(this.gotByConsume));
/*      */     }
/* 8452 */     return this.gotByConsume;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSDestinationAsString() throws JMSException {
/* 8462 */     if (this.destinationString == null && this.destination != null) {
/* 8463 */       this.destinationString = this.destination.toURI();
/*      */     }
/*      */     
/* 8466 */     if (Trace.isOn) {
/* 8467 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSDestinationAsString()", "getter", this.destinationString);
/*      */     }
/*      */     
/* 8470 */     return this.destinationString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSReplyToAsString() throws JMSException {
/* 8479 */     if (this.replyToString == null && this.replyTo != null) {
/* 8480 */       this.replyToString = this.replyTo.toURI();
/*      */     }
/*      */     
/* 8483 */     if (Trace.isOn) {
/* 8484 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSReplyToAsString()", "getter", this.replyToString);
/*      */     }
/*      */     
/* 8487 */     return this.replyToString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDestinationAsString(String newDestination) throws JMSException {
/* 8495 */     if (Trace.isOn) {
/* 8496 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSDestinationAsString(String)", "setter", newDestination);
/*      */     }
/*      */ 
/*      */     
/* 8500 */     this.destinationString = newDestination;
/* 8501 */     this.destination = null;
/* 8502 */     this.destinationFactory = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSReplyToAsString(String newReplyTo) throws JMSException {
/* 8510 */     if (Trace.isOn) {
/* 8511 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSReplyToAsString(String)", "setter", newReplyTo);
/*      */     }
/*      */ 
/*      */     
/* 8515 */     this.replyToString = newReplyTo;
/* 8516 */     this.replyTo = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 8524 */     if (Trace.isOn) {
/* 8525 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "clear()");
/*      */     }
/* 8527 */     this.properties.clear();
/* 8528 */     if (Trace.isOn) {
/* 8529 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "clear()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/* 8539 */     if (Trace.isOn) {
/* 8540 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "containsKey(Object)", new Object[] { key });
/*      */     }
/*      */     
/* 8543 */     boolean traceRet1 = this.properties.containsKey(key);
/* 8544 */     if (Trace.isOn) {
/* 8545 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "containsKey(Object)", 
/* 8546 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 8548 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/* 8556 */     if (Trace.isOn) {
/* 8557 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "containsValue(Object)", new Object[] { value });
/*      */     }
/*      */     
/* 8560 */     boolean traceRet1 = this.properties.containsValue(value);
/* 8561 */     if (Trace.isOn) {
/* 8562 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "containsValue(Object)", 
/* 8563 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 8565 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set entrySet() {
/* 8573 */     if (Trace.isOn) {
/* 8574 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "entrySet()");
/*      */     }
/* 8576 */     Set traceRet1 = this.properties.entrySet();
/* 8577 */     if (Trace.isOn) {
/* 8578 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "entrySet()", traceRet1);
/*      */     }
/*      */     
/* 8581 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/* 8589 */     if (Trace.isOn) {
/* 8590 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "get(Object)", new Object[] { key });
/*      */     }
/*      */     
/* 8593 */     Object traceRet1 = this.properties.get(key);
/* 8594 */     if (Trace.isOn) {
/* 8595 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "get(Object)", traceRet1);
/*      */     }
/*      */     
/* 8598 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/* 8606 */     boolean traceRet1 = this.properties.isEmpty();
/* 8607 */     if (Trace.isOn) {
/* 8608 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "isEmpty()", "getter", 
/* 8609 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 8611 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set keySet() {
/* 8619 */     if (Trace.isOn) {
/* 8620 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "keySet()");
/*      */     }
/* 8622 */     Set traceRet1 = this.properties.keySet();
/* 8623 */     if (Trace.isOn) {
/* 8624 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "keySet()", traceRet1);
/*      */     }
/*      */     
/* 8627 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(String arg0, Object arg1) {
/* 8635 */     if (Trace.isOn) {
/* 8636 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "put(String,Object)", new Object[] { arg0, arg1 });
/*      */     }
/*      */     
/* 8639 */     Object traceRet1 = this.properties.put(arg0, arg1);
/* 8640 */     if (Trace.isOn) {
/* 8641 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "put(String,Object)", traceRet1);
/*      */     }
/*      */     
/* 8644 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends String, ? extends Object> m) {
/* 8652 */     if (Trace.isOn) {
/* 8653 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "putAll(Map<? extends String , ? extends Object>)", new Object[] { m });
/*      */     }
/*      */     
/* 8656 */     this.properties.putAll(m);
/* 8657 */     if (Trace.isOn) {
/* 8658 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "putAll(Map<? extends String , ? extends Object>)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/* 8669 */     if (Trace.isOn) {
/* 8670 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "remove(Object)", new Object[] { key });
/*      */     }
/*      */     
/* 8673 */     Object traceRet1 = this.properties.remove(key);
/* 8674 */     if (Trace.isOn) {
/* 8675 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "remove(Object)", traceRet1);
/*      */     }
/*      */     
/* 8678 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/* 8686 */     if (Trace.isOn) {
/* 8687 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "size()");
/*      */     }
/* 8689 */     int traceRet1 = this.properties.size();
/* 8690 */     if (Trace.isOn) {
/* 8691 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "size()", 
/* 8692 */           Integer.valueOf(traceRet1));
/*      */     }
/* 8694 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection values() {
/* 8702 */     if (Trace.isOn) {
/* 8703 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "values()");
/*      */     }
/* 8705 */     Collection traceRet1 = this.properties.values();
/* 8706 */     if (Trace.isOn) {
/* 8707 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "values()", traceRet1);
/*      */     }
/*      */     
/* 8710 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryDelay() throws JMSException {
/* 8721 */     if (Trace.isOn) {
/* 8722 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "getJMSDeliveryDelay()", "getter", 
/* 8723 */           Long.valueOf(0L));
/*      */     }
/* 8725 */     return 0L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryDelay(long deliveryDelay) throws JMSException {
/* 8736 */     if (Trace.isOn)
/* 8737 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage", "setJMSDeliveryDelay(long)", "setter", 
/* 8738 */           Long.valueOf(deliveryDelay)); 
/*      */   }
/*      */   
/*      */   public abstract byte[] _exportBody(int paramInt, JmqiCodepage paramJmqiCodepage) throws JMSException;
/*      */   
/*      */   public abstract void _importBody(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, JmqiCodepage paramJmqiCodepage) throws JMSException;
/*      */   
/*      */   abstract void _setBodyReadOnly();
/*      */   
/*      */   public abstract void clearBody() throws JMSException;
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */