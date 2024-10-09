/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.UTFDataFormatException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Vector;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.Message;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsBytesMessageImpl
/*      */   extends JmsMessageImpl
/*      */   implements BytesMessage
/*      */ {
/*      */   private static final long serialVersionUID = 5350812021957171977L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsBytesMessageImpl.java";
/*      */   public static final int ENC_INTEGER_MASK = 15;
/*      */   public static final int ENC_FLOAT_MASK = 3840;
/*      */   public static final int ENC_INTEGER_UNDEFINED = 0;
/*      */   public static final int ENC_INTEGER_NORMAL = 1;
/*      */   public static final int ENC_INTEGER_REVERSED = 2;
/*      */   public static final int ENC_FLOAT_UNDEFINED = 0;
/*      */   public static final int ENC_FLOAT_IEEE_NORMAL = 256;
/*      */   public static final int ENC_FLOAT_IEEE_REVERSED = 512;
/*      */   public static final int ENC_FLOAT_S390 = 768;
/*      */   private ProviderBytesMessage providerBytesMessage;
/*      */   private boolean providerBytesBodySet;
/*      */   private int lastEncoding;
/*      */   
/*      */   static {
/*   53 */     if (Trace.isOn) {
/*   54 */       Trace.data("com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsBytesMessageImpl.java");
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
/*      */   private boolean requiresInit = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient ByteArrayOutputStream writeByteStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient DataOutputStream writeStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ARRAY_SIZE = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int integerCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] integerOffsets;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] integerSizes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector<int[]> integers;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector<Integer> floatOffsets;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector<Number> floatValues;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean populatedByClient = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] dataBuffer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  202 */   private int integerEncoding = 1;
/*  203 */   private int floatEncoding = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient ByteArrayInputStream readStream;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int streamOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean markInUse = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  223 */   private transient String cachedBytesToString = null;
/*      */ 
/*      */   
/*      */   protected JmsBytesMessageImpl(JmsSessionImpl session) throws JMSException {
/*  227 */     super(session);
/*  228 */     if (Trace.isOn) {
/*  229 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*  233 */     clearBody();
/*      */     
/*  235 */     this.messageType = "jms_bytes";
/*      */     
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(JmsSessionImpl)");
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
/*      */   public JmsBytesMessageImpl(String connectionType, Message message) throws JMSException {
/*  254 */     super(connectionType, message);
/*  255 */     if (Trace.isOn) {
/*  256 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(String,Message)", new Object[] { connectionType, message });
/*      */     }
/*      */ 
/*      */     
/*  260 */     clearBody();
/*  261 */     this.messageType = "jms_bytes";
/*      */     
/*  263 */     if (Trace.isOn) {
/*  264 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(String,Message)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JmsBytesMessageImpl(ProviderBytesMessage providerBytesMessage, JmsSessionImpl jmsSession, String connectionTypeName) throws JMSException {
/*  272 */     super((ProviderMessage)providerBytesMessage, jmsSession, connectionTypeName);
/*  273 */     if (Trace.isOn) {
/*  274 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(ProviderBytesMessage,JmsSessionImpl,String)", new Object[] { providerBytesMessage, jmsSession, connectionTypeName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  280 */     this.requiresInit = true;
/*      */     
/*  282 */     this.providerBytesMessage = providerBytesMessage;
/*      */ 
/*      */     
/*  285 */     setBodyReadOnly();
/*  286 */     this.messageType = "jms_bytes";
/*  287 */     if (Trace.isOn) {
/*  288 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(ProviderBytesMessage,JmsSessionImpl,String)");
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
/*      */   JmsBytesMessageImpl(JmsSessionImpl session, BytesMessage bytesMessage) throws JMSException {
/*  303 */     super(session, (Message)bytesMessage);
/*  304 */     if (Trace.isOn) {
/*  305 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(JmsSessionImpl,BytesMessage)", new Object[] { session, bytesMessage });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  310 */     clearBody();
/*      */ 
/*      */     
/*  313 */     bytesMessage.reset();
/*      */ 
/*      */     
/*  316 */     int MAX_BUFFER_SIZE = 32768;
/*  317 */     int bufferSize = 0;
/*      */     
/*  319 */     long bodyLength = bytesMessage.getBodyLength();
/*  320 */     if (bodyLength > 32768L) {
/*  321 */       bufferSize = 32768;
/*      */     } else {
/*      */       
/*  324 */       bufferSize = (int)bodyLength;
/*      */     } 
/*      */     
/*  327 */     byte[] buffer = new byte[bufferSize];
/*      */ 
/*      */     
/*  330 */     int nRead = 0;
/*      */     
/*  332 */     while ((nRead = bytesMessage.readBytes(buffer)) > 0) {
/*  333 */       writeBytes(buffer, 0, nRead);
/*      */     }
/*      */     
/*  336 */     this.messageType = "jms_bytes";
/*      */     
/*  338 */     if (Trace.isOn) {
/*  339 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(JmsSessionImpl,BytesMessage)");
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
/*      */   JmsBytesMessageImpl(JmsSessionImpl session, Message message, byte[] buffer) throws JMSException {
/*  353 */     super(session, message);
/*  354 */     if (Trace.isOn) {
/*  355 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(JmsSessionImpl,Message,byte [ ])", new Object[] { session, message, buffer });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  360 */     clearBody();
/*      */     
/*  362 */     writeBytes(buffer);
/*      */     
/*  364 */     this.messageType = "jms_bytes";
/*      */     
/*  366 */     if (Trace.isOn) {
/*  367 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "<init>(JmsSessionImpl,Message,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void lazyInit() {
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "lazyInit()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  384 */     this.requiresInit = false;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  389 */       this.dataBuffer = this.providerBytesMessage.getBytes();
/*      */       
/*  391 */       if (this.dataBuffer == null) {
/*  392 */         this.dataBuffer = new byte[0];
/*      */       }
/*      */       
/*  395 */       this.readStream = new ByteArrayInputStream(this.dataBuffer);
/*      */       
/*  397 */       if (propertyExists("JMS_IBM_Encoding")) {
/*  398 */         int encoding = getIntProperty("JMS_IBM_Encoding");
/*  399 */         this.integerEncoding = encoding & 0xF;
/*  400 */         this.floatEncoding = encoding & 0xF00;
/*      */       }
/*      */     
/*  403 */     } catch (JMSException je) {
/*  404 */       if (Trace.isOn) {
/*  405 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "lazyInit()", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  412 */     if (Trace.isOn) {
/*  413 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "lazyInit()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessage createProviderMessage(JmsSessionImpl session) throws Exception {
/*  423 */     if (Trace.isOn) {
/*  424 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "createProviderMessage(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*  428 */     if (session != null) {
/*  429 */       this.providerBytesMessage = getProviderMessageFactory().createBytesMessage(session.getProviderSession());
/*      */     } else {
/*      */       
/*  432 */       this.providerBytesMessage = getProviderMessageFactory().createBytesMessage(null);
/*      */     } 
/*      */     
/*  435 */     if (Trace.isOn) {
/*  436 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "createProviderMessage(JmsSessionImpl)", this.providerBytesMessage);
/*      */     }
/*      */     
/*  439 */     return (ProviderMessage)this.providerBytesMessage;
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
/*      */   protected ProviderMessage getProviderMessage() throws JMSException {
/*  452 */     if (!isBodyReadOnly()) {
/*      */       
/*  454 */       int encoding = this.integerEncoding | this.floatEncoding;
/*      */       
/*  456 */       if (propertyExists("JMS_IBM_Encoding")) {
/*  457 */         encoding = getIntProperty("JMS_IBM_Encoding");
/*      */       }
/*      */       
/*  460 */       if (!this.providerBytesBodySet || encoding != this.lastEncoding) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  466 */         byte[] encodedBody = encodeBody(encoding);
/*      */         
/*  468 */         this.providerBytesMessage.setBytes(encodedBody);
/*      */         
/*  470 */         this.lastEncoding = encoding;
/*  471 */         this.providerBytesBodySet = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "getProviderMessage()", "getter", this.providerBytesMessage);
/*      */     }
/*      */     
/*  480 */     return (ProviderMessage)this.providerBytesMessage;
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
/*      */   private byte[] encodeBody(int encoding) throws JMSException {
/*  498 */     if (Trace.isOn) {
/*  499 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", new Object[] {
/*  500 */             Integer.valueOf(encoding)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  509 */       this.dataBuffer = this.writeByteStream.toByteArray();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  517 */       if (this.populatedByClient) {
/*      */ 
/*      */ 
/*      */         
/*  521 */         int requestedIntegerEncoding = encoding & 0xF;
/*  522 */         int requestedFloatEncoding = encoding & 0xF00;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  529 */         if ((this.integerEncoding <= 1 && requestedIntegerEncoding == 2) || (this.integerEncoding == 2 && requestedIntegerEncoding <= 1)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  536 */           if (this.integers != null) {
/*  537 */             for (int i = 0; i < this.integers.size(); i += 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  544 */               int[] offsets = this.integers.elementAt(i);
/*  545 */               int[] sizes = this.integers.elementAt(i + 1);
/*      */               
/*  547 */               for (int k = 0; k < 20; k++) {
/*  548 */                 reverse(this.dataBuffer, offsets[k], sizes[k]);
/*      */               }
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  557 */           for (int j = 0; j < this.integerCount; j++) {
/*  558 */             reverse(this.dataBuffer, this.integerOffsets[j], this.integerSizes[j]);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  568 */         if (this.floatEncoding != requestedFloatEncoding) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  578 */           ByteArrayOutputStream floatbaos = new ByteArrayOutputStream(8);
/*  579 */           DataOutputStream floatstream = new DataOutputStream(floatbaos);
/*      */           
/*  581 */           if (this.floatOffsets != null && this.floatValues != null)
/*      */           {
/*  583 */             for (int i = 0; i < this.floatOffsets.size(); i++)
/*      */             {
/*  585 */               floatbaos.reset();
/*  586 */               Object value = this.floatValues.elementAt(i);
/*  587 */               int offset = ((Integer)this.floatOffsets.elementAt(i)).intValue();
/*      */               
/*  589 */               if (value instanceof Float) {
/*  590 */                 HashMap<String, Object> inserts; JMSException traceRet1; float floatValue = ((Float)value).floatValue();
/*      */                 
/*  592 */                 switch (requestedFloatEncoding) {
/*      */                   case 256:
/*      */                   case 512:
/*  595 */                     floatstream.writeInt(Float.floatToIntBits(floatValue));
/*      */                     break;
/*      */                   case 768:
/*  598 */                     floatstream.writeInt(Float390.floatToS390IntBits(floatValue));
/*      */                     break;
/*      */                   default:
/*  601 */                     inserts = new HashMap<>();
/*  602 */                     inserts.put("JMS_IBM_Encoding", Integer.toHexString(requestedFloatEncoding));
/*  603 */                     traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0063", inserts);
/*  604 */                     if (Trace.isOn) {
/*  605 */                       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", (Throwable)traceRet1, 1);
/*      */                     }
/*      */                     
/*  608 */                     throw traceRet1;
/*      */                 } 
/*  610 */                 byte[] floatbytes = floatbaos.toByteArray();
/*  611 */                 if (requestedFloatEncoding == 512) {
/*  612 */                   reverse(floatbytes, 0, 4);
/*      */                 }
/*      */                 
/*  615 */                 System.arraycopy(floatbytes, 0, this.dataBuffer, offset, 4);
/*      */               
/*      */               }
/*  618 */               else if (value instanceof Double) {
/*  619 */                 HashMap<String, Object> inserts; JMSException traceRet2; double doubleValue = ((Double)value).doubleValue();
/*      */                 
/*  621 */                 switch (requestedFloatEncoding) {
/*      */                   case 256:
/*      */                   case 512:
/*  624 */                     floatstream.writeLong(Double.doubleToLongBits(doubleValue));
/*      */                     break;
/*      */                   case 768:
/*  627 */                     floatstream.writeLong(Float390.doubleToS390LongBits(doubleValue));
/*      */                     break;
/*      */                   default:
/*  630 */                     inserts = new HashMap<>();
/*  631 */                     inserts.put("JMS_IBM_Encoding", Integer.toHexString(requestedFloatEncoding));
/*  632 */                     traceRet2 = (JMSException)JmsErrorUtils.createException("JMSCC0063", inserts);
/*  633 */                     if (Trace.isOn) {
/*  634 */                       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", (Throwable)traceRet2, 2);
/*      */                     }
/*      */                     
/*  637 */                     throw traceRet2;
/*      */                 } 
/*      */ 
/*      */                 
/*  641 */                 byte[] floatbytes = floatbaos.toByteArray();
/*  642 */                 if (requestedFloatEncoding == 512) {
/*  643 */                   reverse(floatbytes, 0, 8);
/*      */                 }
/*      */                 
/*  646 */                 System.arraycopy(floatbytes, 0, this.dataBuffer, offset, 8);
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  655 */         byte[] newBytes = new byte[this.dataBuffer.length];
/*  656 */         System.arraycopy(this.dataBuffer, 0, newBytes, 0, this.dataBuffer.length);
/*  657 */         if (Trace.isOn) {
/*  658 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", newBytes, 1);
/*      */         }
/*      */         
/*  661 */         return newBytes;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  666 */       if (Trace.isOn) {
/*  667 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", this.dataBuffer, 2);
/*      */       }
/*      */       
/*  670 */       return this.dataBuffer;
/*      */     
/*      */     }
/*  673 */     catch (IOException e) {
/*  674 */       if (Trace.isOn) {
/*  675 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  680 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0064", null);
/*  681 */       je.setLinkedException(e);
/*      */       
/*  683 */       if (Trace.isOn) {
/*  684 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "encodeBody(int)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  687 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBody() throws JMSException {
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "clearBody()");
/*      */     }
/*      */ 
/*      */     
/*  702 */     super.clearBody();
/*      */ 
/*      */ 
/*      */     
/*  706 */     this.dataBuffer = null;
/*  707 */     this.readStream = null;
/*  708 */     this.writeByteStream = new ByteArrayOutputStream();
/*  709 */     this.writeStream = new DataOutputStream(this.writeByteStream);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  718 */     this.populatedByClient = true;
/*      */ 
/*      */ 
/*      */     
/*  722 */     if (this.providerBytesMessage != null) {
/*      */       
/*  724 */       this.providerBytesMessage.removeProperty("JMS_IBM_Encoding");
/*  725 */       this.providerBytesMessage.removeProperty("JMS_IBM_Character_Set");
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
/*  744 */     this.integerEncoding = 1;
/*  745 */     this.floatEncoding = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  750 */     this.integerCount = 0;
/*      */     
/*  752 */     this.integerOffsets = new int[20];
/*      */     
/*  754 */     this.integerSizes = new int[20];
/*      */     
/*  756 */     if (this.integers != null) {
/*  757 */       this.integers.removeAllElements();
/*      */     }
/*      */ 
/*      */     
/*  761 */     if (this.floatOffsets != null) {
/*  762 */       this.floatOffsets.removeAllElements();
/*      */     }
/*  764 */     if (this.floatValues != null) {
/*  765 */       this.floatValues.removeAllElements();
/*      */     }
/*      */ 
/*      */     
/*  769 */     this.cachedBytesToString = null;
/*      */ 
/*      */     
/*  772 */     this.providerBytesBodySet = false;
/*      */     
/*  774 */     if (Trace.isOn) {
/*  775 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "clearBody()");
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
/*      */   public long getBodyLength() throws JMSException {
/*      */     long bLen;
/*  788 */     checkBodyReadable("getBodyLength");
/*      */     
/*  790 */     if (this.requiresInit) {
/*  791 */       lazyInit();
/*      */     }
/*      */     
/*  794 */     if (isBodyReadOnly()) {
/*  795 */       if (this.dataBuffer == null) {
/*  796 */         bLen = 0L;
/*      */       }
/*      */       else {
/*      */         
/*  800 */         bLen = this.dataBuffer.length;
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  807 */     else if (this.writeByteStream == null) {
/*  808 */       bLen = 0L;
/*      */     } else {
/*      */       
/*  811 */       bLen = this.writeByteStream.size();
/*      */     } 
/*      */ 
/*      */     
/*  815 */     if (Trace.isOn) {
/*  816 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "getBodyLength()", "getter", 
/*  817 */           Long.valueOf(bLen));
/*      */     }
/*  819 */     return bLen;
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
/*      */   public boolean readBoolean() throws JMSException {
/*  831 */     if (Trace.isOn) {
/*  832 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBoolean()");
/*      */     }
/*      */ 
/*      */     
/*  836 */     checkBodyReadable("readBoolean");
/*      */     
/*  838 */     if (this.requiresInit) {
/*  839 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/*  843 */     int byteRead = this.readStream.read();
/*      */     
/*  845 */     if (byteRead < 0) {
/*      */       
/*  847 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/*  848 */       if (Trace.isOn) {
/*  849 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBoolean()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  852 */       throw traceRet1;
/*      */     } 
/*      */     
/*  855 */     boolean traceRet2 = (byteRead != 0);
/*  856 */     if (Trace.isOn) {
/*  857 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBoolean()", 
/*  858 */           Boolean.valueOf(traceRet2));
/*      */     }
/*  860 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte readByte() throws JMSException {
/*  868 */     if (Trace.isOn) {
/*  869 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readByte()");
/*      */     }
/*      */ 
/*      */     
/*  873 */     checkBodyReadable("readByte");
/*      */     
/*  875 */     if (this.requiresInit) {
/*  876 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/*  880 */     int byteRead = this.readStream.read();
/*      */     
/*  882 */     if (byteRead < 0) {
/*      */       
/*  884 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/*  885 */       if (Trace.isOn) {
/*  886 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readByte()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  889 */       throw traceRet1;
/*      */     } 
/*      */     
/*  892 */     byte traceRet2 = (byte)byteRead;
/*  893 */     if (Trace.isOn) {
/*  894 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readByte()", 
/*  895 */           Byte.valueOf(traceRet2));
/*      */     }
/*  897 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readBytes(byte[] value, int length) throws JMSException {
/*  905 */     if (Trace.isOn) {
/*  906 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBytes(byte [ ],int)", new Object[] { value, 
/*  907 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */     
/*  911 */     checkBodyReadable("readBytes");
/*      */     
/*  913 */     if (this.requiresInit) {
/*  914 */       lazyInit();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  921 */     if (value.length < length || length < 0) {
/*  922 */       IndexOutOfBoundsException traceRet1 = new IndexOutOfBoundsException();
/*  923 */       if (Trace.isOn) {
/*  924 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBytes(byte [ ],int)", traceRet1);
/*      */       }
/*      */       
/*  927 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  935 */     int traceRet2 = this.readStream.read(value, 0, length);
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBytes(byte [ ],int)", 
/*  938 */           Integer.valueOf(traceRet2));
/*      */     }
/*  940 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readBytes(byte[] value) throws JMSException {
/*  948 */     if (Trace.isOn) {
/*  949 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */     
/*  952 */     int traceRet1 = readBytes(value, value.length);
/*  953 */     if (Trace.isOn) {
/*  954 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readBytes(byte [ ])", 
/*  955 */           Integer.valueOf(traceRet1));
/*      */     }
/*  957 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char readChar() throws JMSException {
/*  965 */     if (Trace.isOn) {
/*  966 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readChar()");
/*      */     }
/*      */     
/*  969 */     checkBodyReadable("readChar");
/*      */     
/*  971 */     if (this.requiresInit) {
/*  972 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/*  976 */     if (!this.markInUse) {
/*  977 */       this.readStream.mark(2);
/*      */     }
/*      */ 
/*      */     
/*  981 */     int byte1 = this.readStream.read();
/*  982 */     int byte2 = this.readStream.read();
/*  983 */     if (byte2 < 0) {
/*  984 */       this.readStream.reset();
/*  985 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/*  986 */       if (Trace.isOn) {
/*  987 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readChar()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  990 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  995 */     if (this.integerEncoding == 2) {
/*  996 */       char traceRet2 = (char)((byte2 << 8) + byte1);
/*  997 */       if (Trace.isOn) {
/*  998 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readChar()", 
/*  999 */             Character.valueOf(traceRet2), 1);
/*      */       }
/* 1001 */       return traceRet2;
/*      */     } 
/* 1003 */     char traceRet3 = (char)((byte1 << 8) + byte2);
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readChar()", 
/* 1006 */           Character.valueOf(traceRet3), 2);
/*      */     }
/* 1008 */     return traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double readDouble() throws JMSException {
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*      */       double traceRet1, traceRet2, traceRet3;
/*      */       
/* 1023 */       switch (this.floatEncoding) {
/*      */         
/*      */         case 256:
/* 1026 */           this.integerEncoding = 1;
/* 1027 */           traceRet1 = Double.longBitsToDouble(readLong());
/* 1028 */           if (Trace.isOn) {
/* 1029 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()", 
/* 1030 */                 Double.valueOf(traceRet1), 1);
/*      */           }
/* 1032 */           return traceRet1;
/*      */         
/*      */         case 512:
/* 1035 */           this.integerEncoding = 2;
/* 1036 */           traceRet2 = Double.longBitsToDouble(readLong());
/* 1037 */           if (Trace.isOn) {
/* 1038 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()", 
/* 1039 */                 Double.valueOf(traceRet2), 2);
/*      */           }
/* 1041 */           return traceRet2;
/*      */         
/*      */         case 768:
/* 1044 */           this.integerEncoding = 1;
/* 1045 */           traceRet3 = Float390.longS390BitsToDouble(readLong());
/* 1046 */           if (Trace.isOn) {
/* 1047 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()", 
/* 1048 */                 Double.valueOf(traceRet3), 3);
/*      */           }
/* 1050 */           return traceRet3;
/*      */       } 
/*      */       
/* 1053 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1054 */       inserts.put("JMS_IBM_Encoding", Integer.toHexString(this.floatEncoding));
/* 1055 */       JMSException traceRet4 = (JMSException)JmsErrorUtils.createException("JMSCC0063", inserts);
/* 1056 */       if (Trace.isOn) {
/* 1057 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()", (Throwable)traceRet4, 1);
/*      */       }
/*      */       
/* 1060 */       throw traceRet4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1067 */     catch (IOException ex) {
/* 1068 */       if (Trace.isOn) {
/* 1069 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()", ex);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1074 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1075 */       inserts.put("XMSC_INSERT_EXCEPTION", ex);
/* 1076 */       inserts.put("XMSC_INSERT_METHOD", "readDouble");
/* 1077 */       JMSException traceRet5 = (JMSException)JmsErrorUtils.createException("JMSCC0007", inserts);
/* 1078 */       traceRet5.setLinkedException(ex);
/* 1079 */       if (Trace.isOn) {
/* 1080 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readDouble()", (Throwable)traceRet5, 2);
/*      */       }
/*      */       
/* 1083 */       throw traceRet5;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float readFloat() throws JMSException {
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()");
/*      */     }
/*      */     
/*      */     try {
/*      */       float traceRet1, traceRet2, traceRet3;
/*      */       
/* 1099 */       switch (this.floatEncoding) {
/*      */         
/*      */         case 256:
/* 1102 */           this.integerEncoding = 1;
/* 1103 */           traceRet1 = Float.intBitsToFloat(readInt());
/* 1104 */           if (Trace.isOn) {
/* 1105 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()", 
/* 1106 */                 Float.valueOf(traceRet1), 1);
/*      */           }
/* 1108 */           return traceRet1;
/*      */         
/*      */         case 512:
/* 1111 */           this.integerEncoding = 2;
/* 1112 */           traceRet2 = Float.intBitsToFloat(readInt());
/* 1113 */           if (Trace.isOn) {
/* 1114 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()", 
/* 1115 */                 Float.valueOf(traceRet2), 2);
/*      */           }
/* 1117 */           return traceRet2;
/*      */         
/*      */         case 768:
/* 1120 */           this.integerEncoding = 1;
/* 1121 */           traceRet3 = Float390.intS390BitsToFloat(readInt());
/* 1122 */           if (Trace.isOn) {
/* 1123 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()", 
/* 1124 */                 Float.valueOf(traceRet3), 3);
/*      */           }
/* 1126 */           return traceRet3;
/*      */       } 
/*      */       
/* 1129 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1130 */       inserts.put("JMS_IBM_Encoding", Integer.toHexString(this.floatEncoding));
/* 1131 */       JMSException traceRet4 = (JMSException)JmsErrorUtils.createException("JMSCC0063", inserts);
/* 1132 */       if (Trace.isOn) {
/* 1133 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()", (Throwable)traceRet4, 1);
/*      */       }
/*      */       
/* 1136 */       throw traceRet4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1143 */     catch (IOException ex) {
/* 1144 */       if (Trace.isOn) {
/* 1145 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()", ex);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1153 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1154 */       inserts.put("XMSC_INSERT_EXCEPTION", ex);
/* 1155 */       inserts.put("XMSC_INSERT_METHOD", "readFloat");
/* 1156 */       JMSException traceRet5 = (JMSException)JmsErrorUtils.createException("JMSCC0007", inserts);
/* 1157 */       traceRet5.setLinkedException(ex);
/* 1158 */       if (Trace.isOn) {
/* 1159 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readFloat()", (Throwable)traceRet5, 2);
/*      */       }
/*      */       
/* 1162 */       throw traceRet5;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readInt() throws JMSException {
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readInt()");
/*      */     }
/*      */     
/* 1175 */     checkBodyReadable("readInt");
/*      */     
/* 1177 */     if (this.requiresInit) {
/* 1178 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/* 1182 */     if (!this.markInUse) {
/* 1183 */       this.readStream.mark(4);
/*      */     }
/*      */ 
/*      */     
/* 1187 */     int byte1 = this.readStream.read();
/* 1188 */     int byte2 = this.readStream.read();
/* 1189 */     int byte3 = this.readStream.read();
/* 1190 */     int byte4 = this.readStream.read();
/*      */     
/* 1192 */     if (byte4 < 0) {
/* 1193 */       this.readStream.reset();
/* 1194 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/* 1195 */       if (Trace.isOn) {
/* 1196 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readInt()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1199 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1204 */     if (this.integerEncoding == 2) {
/* 1205 */       int traceRet2 = (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
/* 1206 */       if (Trace.isOn) {
/* 1207 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readInt()", 
/* 1208 */             Integer.valueOf(traceRet2), 1);
/*      */       }
/* 1210 */       return traceRet2;
/*      */     } 
/* 1212 */     int traceRet3 = (byte1 << 24) + (byte2 << 16) + (byte3 << 8) + byte4;
/* 1213 */     if (Trace.isOn) {
/* 1214 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readInt()", 
/* 1215 */           Integer.valueOf(traceRet3), 2);
/*      */     }
/* 1217 */     return traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long readLong() throws JMSException {
/* 1224 */     if (Trace.isOn) {
/* 1225 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readLong()");
/*      */     }
/*      */     
/*      */     try {
/* 1229 */       checkBodyReadable("readLong");
/*      */       
/* 1231 */       if (this.requiresInit) {
/* 1232 */         lazyInit();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1238 */       this.readStream.mark(8);
/* 1239 */       this.markInUse = true;
/*      */ 
/*      */       
/* 1242 */       long int1 = readInt() & 0xFFFFFFFFL;
/* 1243 */       long int2 = readInt() & 0xFFFFFFFFL;
/*      */ 
/*      */ 
/*      */       
/* 1247 */       if (this.integerEncoding == 2) {
/* 1248 */         long traceRet1 = (int2 << 32L) + int1;
/* 1249 */         if (Trace.isOn) {
/* 1250 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readLong()", 
/* 1251 */               Long.valueOf(traceRet1), 1);
/*      */         }
/* 1253 */         return traceRet1;
/*      */       } 
/* 1255 */       long traceRet2 = (int1 << 32L) + int2;
/* 1256 */       if (Trace.isOn) {
/* 1257 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readLong()", 
/* 1258 */             Long.valueOf(traceRet2), 2);
/*      */       }
/* 1260 */       return traceRet2;
/*      */     } finally {
/*      */       
/* 1263 */       if (Trace.isOn) {
/* 1264 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readLong()");
/*      */       }
/*      */       
/* 1267 */       this.markInUse = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short readShort() throws JMSException {
/* 1275 */     if (Trace.isOn) {
/* 1276 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readShort()");
/*      */     }
/*      */     
/* 1279 */     checkBodyReadable("readShort");
/*      */     
/* 1281 */     if (this.requiresInit) {
/* 1282 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/* 1286 */     if (!this.markInUse) {
/* 1287 */       this.readStream.mark(2);
/*      */     }
/*      */ 
/*      */     
/* 1291 */     int byte1 = this.readStream.read();
/* 1292 */     int byte2 = this.readStream.read();
/* 1293 */     if (byte2 < 0) {
/*      */       
/* 1295 */       this.readStream.reset();
/* 1296 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/* 1297 */       if (Trace.isOn) {
/* 1298 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readShort()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1301 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1305 */     if (this.integerEncoding == 2) {
/* 1306 */       short traceRet2 = (short)((byte2 << 8) + byte1);
/* 1307 */       if (Trace.isOn) {
/* 1308 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readShort()", 
/* 1309 */             Short.valueOf(traceRet2), 1);
/*      */       }
/* 1311 */       return traceRet2;
/*      */     } 
/* 1313 */     short traceRet3 = (short)((byte1 << 8) + byte2);
/* 1314 */     if (Trace.isOn) {
/* 1315 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readShort()", 
/* 1316 */           Short.valueOf(traceRet3), 2);
/*      */     }
/* 1318 */     return traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readUnsignedByte() throws JMSException {
/* 1325 */     if (Trace.isOn) {
/* 1326 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedByte()");
/*      */     }
/*      */ 
/*      */     
/* 1330 */     checkBodyReadable("readUnsignedByte");
/*      */     
/* 1332 */     if (this.requiresInit) {
/* 1333 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/* 1337 */     int byteRead = this.readStream.read();
/*      */     
/* 1339 */     if (byteRead < 0) {
/*      */       
/* 1341 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/* 1342 */       if (Trace.isOn) {
/* 1343 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedByte()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1346 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1349 */     if (Trace.isOn) {
/* 1350 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedByte()", 
/* 1351 */           Integer.valueOf(byteRead));
/*      */     }
/* 1353 */     return byteRead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readUnsignedShort() throws JMSException {
/* 1361 */     if (Trace.isOn) {
/* 1362 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedShort()");
/*      */     }
/*      */ 
/*      */     
/* 1366 */     checkBodyReadable("readUnsignedShort");
/*      */     
/* 1368 */     if (this.requiresInit) {
/* 1369 */       lazyInit();
/*      */     }
/*      */ 
/*      */     
/* 1373 */     if (!this.markInUse) {
/* 1374 */       this.readStream.mark(2);
/*      */     }
/*      */ 
/*      */     
/* 1378 */     int byte1 = this.readStream.read();
/* 1379 */     int byte2 = this.readStream.read();
/* 1380 */     if (byte2 < 0) {
/*      */       
/* 1382 */       this.readStream.reset();
/* 1383 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/* 1384 */       if (Trace.isOn) {
/* 1385 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedShort()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1388 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1393 */     if (this.integerEncoding == 2) {
/* 1394 */       int traceRet2 = (byte2 << 8) + byte1;
/* 1395 */       if (Trace.isOn) {
/* 1396 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedShort()", 
/* 1397 */             Integer.valueOf(traceRet2), 1);
/*      */       }
/* 1399 */       return traceRet2;
/*      */     } 
/* 1401 */     int traceRet3 = (byte1 << 8) + byte2;
/* 1402 */     if (Trace.isOn) {
/* 1403 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUnsignedShort()", 
/* 1404 */           Integer.valueOf(traceRet3), 2);
/*      */     }
/* 1406 */     return traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readUTF() throws JMSException {
/* 1413 */     if (Trace.isOn) {
/* 1414 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUTF()");
/*      */     }
/* 1416 */     int savedEncoding = this.integerEncoding;
/*      */ 
/*      */     
/*      */     try {
/* 1420 */       checkBodyReadable("readUTF");
/*      */       
/* 1422 */       if (this.requiresInit) {
/* 1423 */         lazyInit();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1429 */       this.readStream.mark(8);
/* 1430 */       this.markInUse = true;
/*      */ 
/*      */       
/* 1433 */       this.integerEncoding = 1;
/*      */       
/* 1435 */       int length = readUnsignedShort();
/*      */ 
/*      */ 
/*      */       
/* 1439 */       byte[] utfBytes = new byte[length];
/*      */       
/* 1441 */       if (readBytes(utfBytes, length) != length) {
/*      */ 
/*      */         
/* 1444 */         this.readStream.reset();
/* 1445 */         JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0065", null);
/* 1446 */         if (Trace.isOn) {
/* 1447 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUTF()", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1450 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1453 */       String traceRet2 = new String(utfBytes, 0, length, "UTF8");
/* 1454 */       if (Trace.isOn) {
/* 1455 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUTF()", traceRet2);
/*      */       }
/*      */       
/* 1458 */       return traceRet2;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1463 */     catch (UnsupportedEncodingException ex) {
/* 1464 */       if (Trace.isOn) {
/* 1465 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUTF()", ex);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1470 */       JMSException traceRet3 = (JMSException)JmsErrorUtils.createException("JMSCC0066", null);
/* 1471 */       traceRet3.setLinkedException(ex);
/* 1472 */       if (Trace.isOn) {
/* 1473 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUTF()", (Throwable)traceRet3, 2);
/*      */       }
/*      */       
/* 1476 */       throw traceRet3;
/*      */     }
/*      */     finally {
/*      */       
/* 1480 */       if (Trace.isOn) {
/* 1481 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readUTF()");
/*      */       }
/*      */       
/* 1484 */       this.integerEncoding = savedEncoding;
/*      */       
/* 1486 */       this.markInUse = false;
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
/*      */   private void recordInteger(int offset, int length) {
/* 1501 */     if (Trace.isOn) {
/* 1502 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "recordInteger(int,int)", new Object[] {
/* 1503 */             Integer.valueOf(offset), Integer.valueOf(length)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1509 */     if (this.integerCount == 20) {
/* 1510 */       if (this.integers == null) {
/* 1511 */         this.integers = (Vector)new Vector<>();
/*      */       }
/* 1513 */       this.integers.addElement(this.integerOffsets);
/* 1514 */       this.integers.addElement(this.integerSizes);
/* 1515 */       this.integerOffsets = new int[20];
/* 1516 */       this.integerSizes = new int[20];
/* 1517 */       this.integerCount = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1522 */     this.integerOffsets[this.integerCount] = offset;
/* 1523 */     this.integerSizes[this.integerCount++] = length;
/*      */     
/* 1525 */     if (Trace.isOn) {
/* 1526 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "recordInteger(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset() throws JMSException {
/* 1537 */     if (Trace.isOn) {
/* 1538 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "reset()");
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
/* 1549 */     if (!isBodyReadOnly()) {
/*      */ 
/*      */       
/* 1552 */       this.dataBuffer = this.writeByteStream.toByteArray();
/*      */ 
/*      */       
/* 1555 */       this.lastEncoding = this.integerEncoding | this.floatEncoding;
/* 1556 */       this.providerBytesMessage.setBytes(encodeBody(this.lastEncoding));
/* 1557 */       this.providerBytesBodySet = true;
/*      */ 
/*      */       
/* 1560 */       this.writeStream = null;
/* 1561 */       this.writeByteStream = null;
/*      */ 
/*      */       
/* 1564 */       setBodyReadOnly();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1572 */     if (this.dataBuffer == null) {
/* 1573 */       this.dataBuffer = new byte[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1582 */     this.readStream = new ByteArrayInputStream(this.dataBuffer);
/*      */     
/* 1584 */     if (Trace.isOn) {
/* 1585 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "reset()");
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
/*      */   private void reverse(byte[] buffer, int offset, int length) {
/* 1600 */     if (Trace.isOn) {
/* 1601 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "reverse(byte [ ],int,int)", new Object[] { buffer, 
/* 1602 */             Integer.valueOf(offset), 
/* 1603 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1608 */     for (int i = 0; i < length / 2; i++) {
/* 1609 */       byte temp = buffer[offset + i];
/* 1610 */       buffer[offset + i] = buffer[offset + length - 1 - i];
/* 1611 */       buffer[offset + length - 1 - i] = temp;
/*      */     } 
/*      */     
/* 1614 */     if (Trace.isOn) {
/* 1615 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "reverse(byte [ ],int,int)");
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
/*      */   public String toString() {
/* 1629 */     if (Trace.isOn) {
/* 1630 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "toString()");
/*      */     }
/*      */     
/* 1633 */     if (this.cachedBytesToString == null) {
/*      */ 
/*      */ 
/*      */       
/* 1637 */       int MAX_LINES = 10;
/* 1638 */       int LINE_LENGTH = 40;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1643 */       StringBuffer retval = new StringBuffer();
/*      */ 
/*      */ 
/*      */       
/* 1647 */       if (!isBodyReadOnly()) {
/*      */         
/*      */         try {
/* 1650 */           getProviderMessage();
/*      */         }
/* 1652 */         catch (JMSException e) {
/* 1653 */           if (Trace.isOn) {
/* 1654 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "toString()", (Throwable)e);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1662 */       if (this.requiresInit) {
/* 1663 */         lazyInit();
/*      */       }
/*      */       
/* 1666 */       if (this.dataBuffer != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1672 */         int curpos = 0;
/* 1673 */         int lines_written = 0;
/*      */ 
/*      */         
/* 1676 */         while (lines_written < MAX_LINES) {
/* 1677 */           if (this.dataBuffer.length <= curpos + LINE_LENGTH) {
/*      */ 
/*      */             
/* 1680 */             binToHex(this.dataBuffer, curpos, this.dataBuffer.length - curpos, retval);
/* 1681 */             curpos = this.dataBuffer.length;
/* 1682 */             retval.append("\n");
/*      */             
/*      */             break;
/*      */           } 
/* 1686 */           binToHex(this.dataBuffer, curpos, LINE_LENGTH, retval);
/* 1687 */           retval.append("\n");
/* 1688 */           curpos += LINE_LENGTH;
/* 1689 */           lines_written++;
/*      */         } 
/*      */ 
/*      */         
/* 1693 */         if (curpos != this.dataBuffer.length) {
/* 1694 */           retval.append("...\n");
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1699 */       this.cachedBytesToString = retval.toString();
/*      */     } 
/*      */ 
/*      */     
/* 1703 */     String val = super.toString() + "\n" + this.cachedBytesToString;
/*      */     
/* 1705 */     if (Trace.isOn) {
/* 1706 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "toString()", val);
/*      */     }
/* 1708 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeBoolean(boolean value) throws JMSException {
/* 1716 */     if (Trace.isOn) {
/* 1717 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBoolean(boolean)", new Object[] {
/* 1718 */             Boolean.valueOf(value)
/*      */           });
/*      */     }
/*      */ 
/*      */     
/* 1723 */     checkBodyWriteable("writeBoolean");
/*      */ 
/*      */     
/* 1726 */     writeByte((byte)(value ? 1 : 0));
/*      */ 
/*      */     
/* 1729 */     this.cachedBytesToString = null;
/*      */ 
/*      */     
/* 1732 */     this.providerBytesBodySet = false;
/*      */     
/* 1734 */     if (Trace.isOn) {
/* 1735 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBoolean(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeByte(byte value) throws JMSException {
/* 1746 */     if (Trace.isOn) {
/* 1747 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeByte(byte)", new Object[] {
/* 1748 */             Byte.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 1753 */       checkBodyWriteable("writeByte");
/*      */ 
/*      */       
/* 1756 */       this.writeStream.writeByte(value);
/*      */ 
/*      */       
/* 1759 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 1762 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1768 */     catch (IOException ex) {
/* 1769 */       if (Trace.isOn) {
/* 1770 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeByte(byte)", ex);
/*      */       }
/*      */ 
/*      */       
/* 1774 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 1775 */       traceRet1.setLinkedException(ex);
/* 1776 */       if (Trace.isOn) {
/* 1777 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeByte(byte)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1780 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1784 */     if (Trace.isOn) {
/* 1785 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeByte(byte)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeBytes(byte[] value, int offset, int length) throws JMSException {
/* 1794 */     if (Trace.isOn) {
/* 1795 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/* 1796 */             Integer.valueOf(offset), 
/* 1797 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1802 */       checkBodyWriteable("writeBytes");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1807 */       this.writeStream.write(value, offset, length);
/*      */ 
/*      */       
/* 1810 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 1813 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1820 */     catch (IOException ex) {
/* 1821 */       if (Trace.isOn) {
/* 1822 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBytes(byte [ ],int,int)", ex);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1827 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 1828 */       traceRet1.setLinkedException(ex);
/* 1829 */       if (Trace.isOn) {
/* 1830 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBytes(byte [ ],int,int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1833 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1837 */     if (Trace.isOn) {
/* 1838 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBytes(byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeBytes(byte[] value) throws JMSException {
/* 1848 */     if (Trace.isOn) {
/* 1849 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 1853 */     writeBytes(value, 0, value.length);
/*      */     
/* 1855 */     if (Trace.isOn) {
/* 1856 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeBytes(byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeChar(char value) throws JMSException {
/* 1867 */     if (Trace.isOn) {
/* 1868 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeChar(char)", new Object[] {
/* 1869 */             Character.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 1874 */       checkBodyWriteable("writeChar");
/*      */ 
/*      */       
/* 1877 */       this.writeStream.writeChar(value);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1882 */       recordInteger(this.writeStream.size() - 2, 2);
/*      */ 
/*      */ 
/*      */       
/* 1886 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 1889 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1896 */     catch (IOException ex) {
/* 1897 */       if (Trace.isOn) {
/* 1898 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeChar(char)", ex);
/*      */       }
/*      */ 
/*      */       
/* 1902 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 1903 */       je.setLinkedException(ex);
/* 1904 */       if (Trace.isOn) {
/* 1905 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeChar(char)", (Throwable)je);
/*      */       }
/*      */       
/* 1908 */       throw je;
/*      */     } 
/* 1910 */     if (Trace.isOn) {
/* 1911 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeChar(char)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeDouble(double value) throws JMSException {
/* 1920 */     if (Trace.isOn) {
/* 1921 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeDouble(double)", new Object[] {
/* 1922 */             Double.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 1927 */       checkBodyWriteable("writeDouble");
/*      */ 
/*      */       
/* 1930 */       this.writeStream.writeLong(Double.doubleToLongBits(value));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1937 */       if (this.floatValues == null) {
/* 1938 */         this.floatValues = new Vector<>();
/*      */       }
/* 1940 */       this.floatValues.addElement(Double.valueOf(value));
/* 1941 */       if (this.floatOffsets == null) {
/* 1942 */         this.floatOffsets = new Vector<>();
/*      */       }
/* 1944 */       this.floatOffsets.addElement(Integer.valueOf(this.writeStream.size() - 8));
/*      */ 
/*      */ 
/*      */       
/* 1948 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 1951 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1958 */     catch (IOException ex) {
/* 1959 */       if (Trace.isOn) {
/* 1960 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeDouble(double)", ex);
/*      */       }
/*      */       
/* 1963 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 1964 */       je.setLinkedException(ex);
/* 1965 */       if (Trace.isOn) {
/* 1966 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeDouble(double)", (Throwable)je);
/*      */       }
/*      */       
/* 1969 */       throw je;
/*      */     } 
/*      */     
/* 1972 */     if (Trace.isOn) {
/* 1973 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeDouble(double)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeFloat(float value) throws JMSException {
/* 1983 */     if (Trace.isOn) {
/* 1984 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeFloat(float)", new Object[] {
/* 1985 */             Float.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 1990 */       checkBodyWriteable("writeFloat");
/*      */ 
/*      */       
/* 1993 */       this.writeStream.writeInt(Float.floatToIntBits(value));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1998 */       if (this.floatValues == null) {
/* 1999 */         this.floatValues = new Vector<>();
/*      */       }
/* 2001 */       this.floatValues.addElement(Float.valueOf(value));
/* 2002 */       if (this.floatOffsets == null) {
/* 2003 */         this.floatOffsets = new Vector<>();
/*      */       }
/* 2005 */       this.floatOffsets.addElement(Integer.valueOf(this.writeStream.size() - 4));
/*      */ 
/*      */       
/* 2008 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 2011 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2018 */     catch (IOException ex) {
/* 2019 */       if (Trace.isOn) {
/* 2020 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeFloat(float)", ex);
/*      */       }
/*      */       
/* 2023 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 2024 */       je.setLinkedException(ex);
/* 2025 */       if (Trace.isOn) {
/* 2026 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeFloat(float)", (Throwable)je);
/*      */       }
/*      */       
/* 2029 */       throw je;
/*      */     } 
/*      */     
/* 2032 */     if (Trace.isOn) {
/* 2033 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeFloat(float)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeInt(int value) throws JMSException {
/* 2043 */     if (Trace.isOn) {
/* 2044 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeInt(int)", new Object[] {
/* 2045 */             Integer.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 2050 */       checkBodyWriteable("writeInt");
/*      */ 
/*      */       
/* 2053 */       this.writeStream.writeInt(value);
/*      */ 
/*      */ 
/*      */       
/* 2057 */       recordInteger(this.writeStream.size() - 4, 4);
/*      */ 
/*      */ 
/*      */       
/* 2061 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 2064 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2071 */     catch (IOException ex) {
/* 2072 */       if (Trace.isOn) {
/* 2073 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeInt(int)", ex);
/*      */       }
/*      */       
/* 2076 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 2077 */       je.setLinkedException(ex);
/* 2078 */       if (Trace.isOn) {
/* 2079 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeInt(int)", (Throwable)je);
/*      */       }
/*      */       
/* 2082 */       throw je;
/*      */     } 
/*      */     
/* 2085 */     if (Trace.isOn) {
/* 2086 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeInt(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeLong(long value) throws JMSException {
/* 2095 */     if (Trace.isOn) {
/* 2096 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeLong(long)", new Object[] {
/* 2097 */             Long.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 2102 */       checkBodyWriteable("writeLong");
/*      */ 
/*      */       
/* 2105 */       this.writeStream.writeLong(value);
/*      */ 
/*      */ 
/*      */       
/* 2109 */       recordInteger(this.writeStream.size() - 8, 8);
/*      */ 
/*      */ 
/*      */       
/* 2113 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 2116 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2123 */     catch (IOException ex) {
/* 2124 */       if (Trace.isOn) {
/* 2125 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeLong(long)", ex);
/*      */       }
/*      */       
/* 2128 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 2129 */       je.setLinkedException(ex);
/* 2130 */       if (Trace.isOn) {
/* 2131 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeLong(long)", (Throwable)je);
/*      */       }
/*      */       
/* 2134 */       throw je;
/*      */     } 
/* 2136 */     if (Trace.isOn) {
/* 2137 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeLong(long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeObject(Object value) throws JMSException {
/* 2146 */     if (Trace.isOn) {
/* 2147 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeObject(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2153 */     checkBodyWriteable("writeObject");
/*      */     
/* 2155 */     if (value == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2160 */       NullPointerException npe = (NullPointerException)JmsErrorUtils.createException("JMSCC0103", null);
/* 2161 */       if (Trace.isOn) {
/* 2162 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeObject(Object)", npe, 1);
/*      */       }
/*      */       
/* 2165 */       throw npe;
/*      */     } 
/* 2167 */     if (value instanceof byte[]) {
/* 2168 */       writeBytes((byte[])value);
/*      */     }
/* 2170 */     else if (value instanceof String) {
/* 2171 */       writeUTF((String)value);
/*      */     }
/* 2173 */     else if (value instanceof Integer) {
/* 2174 */       writeInt(((Integer)value).intValue());
/*      */     }
/* 2176 */     else if (value instanceof Byte) {
/* 2177 */       writeByte(((Byte)value).byteValue());
/*      */     }
/* 2179 */     else if (value instanceof Short) {
/* 2180 */       writeShort(((Short)value).shortValue());
/*      */     }
/* 2182 */     else if (value instanceof Long) {
/* 2183 */       writeLong(((Long)value).longValue());
/*      */     }
/* 2185 */     else if (value instanceof Float) {
/* 2186 */       writeFloat(((Float)value).floatValue());
/*      */     }
/* 2188 */     else if (value instanceof Double) {
/* 2189 */       writeDouble(((Double)value).doubleValue());
/*      */     }
/* 2191 */     else if (value instanceof Character) {
/* 2192 */       writeChar(((Character)value).charValue());
/*      */     }
/* 2194 */     else if (value instanceof Boolean) {
/* 2195 */       writeBoolean(((Boolean)value).booleanValue());
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2200 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2201 */       inserts.put("XMSC_INSERT_OBJECT", value.getClass().getName());
/* 2202 */       JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0083", inserts);
/* 2203 */       if (Trace.isOn) {
/* 2204 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeObject(Object)", (Throwable)je2, 2);
/*      */       }
/*      */       
/* 2207 */       throw je2;
/*      */     } 
/*      */ 
/*      */     
/* 2211 */     this.cachedBytesToString = null;
/*      */ 
/*      */     
/* 2214 */     this.providerBytesBodySet = false;
/*      */     
/* 2216 */     if (Trace.isOn) {
/* 2217 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeObject(Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeShort(short value) throws JMSException {
/* 2228 */     if (Trace.isOn) {
/* 2229 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeShort(short)", new Object[] {
/* 2230 */             Short.valueOf(value)
/*      */           });
/*      */     }
/*      */     try {
/* 2234 */       checkBodyWriteable("writeShort");
/*      */ 
/*      */       
/* 2237 */       this.writeStream.writeShort(value);
/*      */ 
/*      */ 
/*      */       
/* 2241 */       recordInteger(this.writeStream.size() - 2, 2);
/*      */ 
/*      */ 
/*      */       
/* 2245 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 2248 */       this.providerBytesBodySet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2255 */     catch (IOException ex) {
/* 2256 */       if (Trace.isOn) {
/* 2257 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeShort(short)", ex);
/*      */       }
/*      */       
/* 2260 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 2261 */       je.setLinkedException(ex);
/* 2262 */       if (Trace.isOn) {
/* 2263 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeShort(short)", (Throwable)je);
/*      */       }
/*      */       
/* 2266 */       throw je;
/*      */     } 
/*      */     
/* 2269 */     if (Trace.isOn) {
/* 2270 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeShort(short)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeUTF(String value) throws JMSException {
/* 2280 */     if (Trace.isOn) {
/* 2281 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeUTF(String)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2287 */       checkBodyWriteable("writeUTF");
/*      */ 
/*      */       
/* 2290 */       this.writeStream.writeUTF(value);
/*      */ 
/*      */       
/* 2293 */       this.cachedBytesToString = null;
/*      */ 
/*      */       
/* 2296 */       this.providerBytesBodySet = false;
/*      */     
/*      */     }
/* 2299 */     catch (UTFDataFormatException ex) {
/* 2300 */       if (Trace.isOn) {
/* 2301 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeUTF(String)", ex, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2308 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0066", null);
/* 2309 */       traceRet1.setLinkedException(ex);
/* 2310 */       if (Trace.isOn) {
/* 2311 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeUTF(String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 2314 */       throw traceRet1;
/*      */     
/*      */     }
/* 2317 */     catch (IOException ex) {
/* 2318 */       if (Trace.isOn) {
/* 2319 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeUTF(String)", ex, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2324 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0067", null);
/* 2325 */       je.setLinkedException(ex);
/* 2326 */       if (Trace.isOn) {
/* 2327 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeUTF(String)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2330 */       throw je;
/*      */     } 
/*      */     
/* 2333 */     if (Trace.isOn) {
/* 2334 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeUTF(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 2340 */     if (Trace.isOn) {
/* 2341 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */     
/* 2345 */     if (this.requiresInit) {
/* 2346 */       lazyInit();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2353 */     if (isBodyReadOnly()) {
/*      */       
/* 2355 */       this.streamOffset = this.dataBuffer.length - this.readStream.available();
/*      */ 
/*      */     
/*      */     }
/* 2359 */     else if (this.writeByteStream != null) {
/* 2360 */       this.dataBuffer = this.writeByteStream.toByteArray();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2365 */     out.defaultWriteObject();
/*      */     
/* 2367 */     if (Trace.isOn) {
/* 2368 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "writeObject(java.io.ObjectOutputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 2376 */     if (Trace.isOn) {
/* 2377 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2382 */     in.defaultReadObject();
/*      */     
/* 2384 */     if (isBodyReadOnly()) {
/*      */       
/* 2386 */       this.readStream = new ByteArrayInputStream(this.dataBuffer);
/* 2387 */       this.readStream.skip(this.streamOffset);
/*      */     }
/*      */     else {
/*      */       
/* 2391 */       this.writeByteStream = new ByteArrayOutputStream();
/* 2392 */       this.writeStream = new DataOutputStream(this.writeByteStream);
/* 2393 */       this.writeStream.write(this.dataBuffer);
/*      */     } 
/*      */     
/* 2396 */     if (Trace.isOn) {
/* 2397 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "readObject(java.io.ObjectInputStream)");
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
/*      */   static int binToHex(byte[] bin, int start, int length, StringBuffer hex) {
/* 2412 */     if (Trace.isOn) {
/* 2413 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "binToHex(byte [ ],int,int,StringBuffer)", new Object[] { bin, 
/* 2414 */             Integer.valueOf(start), 
/* 2415 */             Integer.valueOf(length), hex });
/*      */     }
/*      */     
/* 2418 */     char[] BIN2HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*      */ 
/*      */     
/* 2421 */     int sum = 0;
/*      */     
/* 2423 */     for (int i = start; i < start + length; i++) {
/* 2424 */       int binByte = bin[i];
/*      */       
/* 2426 */       if (binByte < 0) {
/* 2427 */         binByte += 256;
/*      */       }
/*      */       
/* 2430 */       sum += binByte;
/* 2431 */       hex.append(BIN2HEX[binByte / 16]);
/* 2432 */       hex.append(BIN2HEX[binByte % 16]);
/*      */     } 
/*      */     
/* 2435 */     if (Trace.isOn) {
/* 2436 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "binToHex(byte [ ],int,int,StringBuffer)", 
/* 2437 */           Integer.valueOf(sum));
/*      */     }
/* 2439 */     return sum;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasBody() throws JMSException {
/* 2445 */     if (Trace.isOn) {
/* 2446 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "hasBody()");
/*      */     }
/*      */     
/* 2449 */     int size = 0;
/* 2450 */     if (isBodyReadOnly()) {
/*      */       
/* 2452 */       if (this.requiresInit) {
/* 2453 */         lazyInit();
/*      */       }
/*      */       
/* 2456 */       if (this.dataBuffer == null) {
/* 2457 */         size = 0;
/*      */       } else {
/*      */         
/* 2460 */         size = this.dataBuffer.length;
/*      */       }
/*      */     
/*      */     }
/* 2464 */     else if (this.writeByteStream == null) {
/* 2465 */       size = 0;
/*      */     } else {
/*      */       
/* 2468 */       size = this.writeByteStream.size();
/*      */     } 
/*      */ 
/*      */     
/* 2472 */     boolean traceRet1 = (size != 0);
/* 2473 */     if (Trace.isOn) {
/* 2474 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytesMessageImpl", "hasBody()", 
/* 2475 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 2477 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsBytesMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */