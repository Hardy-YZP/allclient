/*      */ package com.ibm.disthub2.impl.client;
/*      */ 
/*      */ import com.ibm.disthub2.client.Message;
/*      */ import com.ibm.disthub2.client.MessageBodyHandle;
/*      */ import com.ibm.disthub2.client.Schema;
/*      */ import com.ibm.disthub2.client.SchemaViolationException;
/*      */ import com.ibm.disthub2.client.Topic;
/*      */ import com.ibm.disthub2.impl.formats.Envelop;
/*      */ import com.ibm.disthub2.impl.formats.FlatSchema;
/*      */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*      */ import com.ibm.disthub2.impl.formats.MessageHandle;
/*      */ import com.ibm.disthub2.impl.formats.Schema;
/*      */ import com.ibm.disthub2.impl.util.Assert;
/*      */ import com.ibm.disthub2.impl.util.VectorClock;
/*      */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*      */ import com.ibm.disthub2.spi.ExceptionConstants;
/*      */ import com.ibm.disthub2.spi.LogConstants;
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.util.Dictionary;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MessageImpl
/*      */   implements Message, LogConstants, ExceptionConstants, Cloneable, Envelop.Constants
/*      */ {
/*  102 */   private static final DebugObject debug = new DebugObject("MessageImpl");
/*      */ 
/*      */   
/*      */   public MessageHandle cursor;
/*      */ 
/*      */   
/*      */   private boolean topicsArePrefixed;
/*      */ 
/*      */   
/*      */   private MessageBodyHandle bodyHandle;
/*      */ 
/*      */   
/*      */   protected TopicImpl dest;
/*      */ 
/*      */   
/*      */   private String[] propertyNames;
/*      */ 
/*      */   
/*      */   private Object[] propertyValues;
/*      */ 
/*      */   
/*      */   protected ConnectorImpl conn;
/*      */ 
/*      */   
/*  126 */   public SubscriptionInfo subInfo = null;
/*      */   
/*      */   public boolean targetted = false;
/*      */   
/*      */   public boolean silenceMsg = false;
/*      */   
/*      */   public boolean gapMsg = false;
/*      */   
/*  134 */   public long gsPub = 0L;
/*  135 */   public long gsTic = 0L;
/*      */   
/*  137 */   public VectorClock vc = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageImpl(TopicImpl topic) {
/*  147 */     this(ConnectorImpl.newNormalMessage(topic.fullName, 4, null, 0), null);
/*      */ 
/*      */ 
/*      */     
/*  151 */     if (debug.debugIt(32)) {
/*  152 */       debug.debug(-165922073994779L, "MessageImpl", topic);
/*      */     }
/*      */     
/*  155 */     this.topicsArePrefixed = false;
/*  156 */     if (topic.propertiesSet) {
/*  157 */       this.dest = topic;
/*      */     }
/*  159 */     defaults();
/*      */     
/*  161 */     if (debug.debugIt(64)) {
/*  162 */       debug.debug(-142394261359015L, "MessageImpl");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void defaults() {
/*  168 */     if (debug.debugIt(32)) {
/*  169 */       debug.debug(-165922073994779L, "defaults");
/*      */     }
/*      */     
/*  172 */     this.cursor.setChoice(153, 0);
/*      */     
/*  174 */     this.cursor.setChoice(155, 0);
/*      */     
/*  176 */     this.cursor.setChoice(154, 0);
/*      */     
/*  178 */     this.cursor.setChoice(156, 0);
/*      */     
/*  180 */     this.cursor.setBoolean(20, false);
/*  181 */     this.cursor.setBoolean(22, false);
/*  182 */     this.cursor.setLong(21, 0L);
/*  183 */     this.cursor.setChoice(157, 1);
/*  184 */     this.cursor.setChoice(158, 0);
/*      */     
/*  186 */     if (debug.debugIt(64)) {
/*  187 */       debug.debug(-142394261359015L, "defaults");
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
/*      */   public MessageImpl(MessageHandle cursor, ConnectorImpl conn) {
/*  199 */     if (debug.debugIt(32)) {
/*  200 */       debug.debug(-165922073994779L, "MessageImpl", cursor);
/*      */     }
/*      */     
/*  203 */     this.cursor = cursor;
/*  204 */     this.conn = conn;
/*      */ 
/*      */     
/*  207 */     Assert.condition(cursor.isPresent(147));
/*      */     
/*  209 */     this.topicsArePrefixed = (cursor.getChoice(147) == 0);
/*      */     
/*  211 */     if (debug.debugIt(64)) {
/*  212 */       debug.debug(-142394261359015L, "MessageImpl");
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
/*      */   public MessageImpl(MessageHandle cursor) {
/*  224 */     if (debug.debugIt(32)) {
/*  225 */       debug.debug(-165922073994779L, "MessageImpl", cursor);
/*      */     }
/*      */     
/*  228 */     this.cursor = cursor;
/*      */ 
/*      */     
/*  231 */     Assert.condition(cursor.isPresent(147));
/*      */     
/*  233 */     this.topicsArePrefixed = (cursor.getChoice(147) == 0);
/*      */     
/*  235 */     if (debug.debugIt(64)) {
/*  236 */       debug.debug(-142394261359015L, "MessageImpl");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageImpl() {
/*  244 */     this(ConnectorImpl.newNormalMessage("", 4, null, 0), null);
/*      */     
/*  246 */     if (debug.debugIt(32)) {
/*  247 */       debug.debug(-165922073994779L, "MessageImpl");
/*      */     }
/*      */     
/*  250 */     this.topicsArePrefixed = false;
/*  251 */     defaults();
/*      */     
/*  253 */     if (debug.debugIt(64)) {
/*  254 */       debug.debug(-142394261359015L, "MessageImpl");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() {
/*      */     MessageBodyHandle from, to;
/*  262 */     MessageImpl ans = new MessageImpl((TopicImpl)getTopic());
/*  263 */     ans.setPriority(getPriority());
/*  264 */     ans.setPersistent(getPersistent());
/*  265 */     ans.setReplyTopic(getReplyTopic());
/*      */     
/*  267 */     ans.cursor.setLong(11, getMessageID());
/*      */     
/*  269 */     int type = getMessageType();
/*  270 */     if (type != 7) {
/*      */       
/*  272 */       ans.setProperties(getProperties());
/*  273 */       ans.setCorrelationID(getCorrelationID());
/*  274 */       ans.setTimestamp(getTimestamp());
/*  275 */       ans.setJMSTypeField(getJMSTypeField());
/*      */     } 
/*      */     
/*  278 */     switch (type) {
/*      */ 
/*      */       
/*      */       case 3:
/*  282 */         ans.setBytesBody(getBytesBody());
/*      */         break;
/*      */       case 4:
/*  285 */         ans.setTextBody(getTextBody());
/*      */         break;
/*      */       case 2:
/*  288 */         ans.setObjectRaw(getObjectRaw());
/*      */         break;
/*      */       case 5:
/*  291 */         ans.setFields(getFields());
/*      */         break;
/*      */       case 6:
/*  294 */         ans.setNamedFields(getNamedFields());
/*      */         break;
/*      */       case 7:
/*  297 */         from = getSchematizedBody(null);
/*  298 */         to = ans.setSchematizedBody(from.getSchema());
/*  299 */         copyHandle(from, to); break;
/*      */     } 
/*  301 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void copyHandle(MessageBodyHandle from, MessageBodyHandle to) {
/*  307 */     FlatSchema fs = ((Schema)from.getSchema()).getFlatSchema(); int i;
/*  308 */     for (i = 0; i < fs.variants.length; i++) {
/*  309 */       int choice = from.getChoice(i + fs.fields.length);
/*  310 */       if (choice != -1) {
/*  311 */         to.setChoice(i + fs.fields.length, choice);
/*      */       }
/*      */     } 
/*  314 */     for (i = 0; i < fs.fields.length; i++) {
/*  315 */       if (from.isPresent(i)) {
/*  316 */         MessageBodyHandle fromD; MessageBodyHandle toD; MessageBodyHandle[] fromT; MessageBodyHandle[] toT; int j; switch ((fs.fields[i]).field.getTypeCode()) {
/*      */           case -1:
/*  318 */             fromD = from.getDynamic(i, null);
/*  319 */             toD = to.newDynamic(fromD.getSchema());
/*  320 */             copyHandle(fromD, toD);
/*  321 */             to.setDynamic(i, toD);
/*      */             break;
/*      */           case 0:
/*  324 */             fromT = from.getTable(i);
/*  325 */             toT = new MessageBodyHandle[fromT.length];
/*  326 */             for (j = 0; j < fromT.length; j++) {
/*  327 */               toT[j] = to.newTableRow(i);
/*  328 */               copyHandle(fromT[j], toT[j]);
/*      */             } 
/*  330 */             to.setTable(i, toT);
/*      */             break;
/*      */           
/*      */           case -3:
/*  334 */             ((MessageDataHandle)to).setEncodedObject(i, ((MessageDataHandle)from)
/*  335 */                 .getEncodedObject(i));
/*      */             break;
/*      */           default:
/*      */             
/*  339 */             try { to.setValue(i, from.getValue(i)); }
/*      */             
/*  341 */             catch (IOException iOException) {  }
/*  342 */             catch (ClassNotFoundException classNotFoundException) {}
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private IllegalStateException stateException(int type) {
/*  355 */     if (debug.debugIt(32)) {
/*  356 */       debug.debug(-165922073994779L, "stateException", Integer.valueOf(type));
/*      */     }
/*      */     
/*  359 */     return new IllegalStateException(ExceptionBuilder.buildReasonString(type, null));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMessageType() {
/*      */     int result;
/*  365 */     if (debug.debugIt(32)) {
/*  366 */       debug.debug(-165922073994779L, "getMessageType");
/*      */     }
/*      */ 
/*      */     
/*  370 */     if (this.bodyHandle != null || this.cursor.isPresent(31)) {
/*  371 */       result = 7;
/*      */     } else {
/*      */       
/*  374 */       result = this.cursor.getChoice(157);
/*      */     } 
/*      */     
/*  377 */     if (debug.debugIt(64)) {
/*  378 */       debug.debug(-142394261359015L, "getMessageType", Integer.valueOf(result));
/*      */     }
/*      */     
/*  381 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBody() {
/*  387 */     if (debug.debugIt(32)) {
/*  388 */       debug.debug(-165922073994779L, "clearBody");
/*      */     }
/*      */     
/*  391 */     this.bodyHandle = null;
/*  392 */     this.cursor.setChoice(157, 1);
/*      */     
/*  394 */     if (debug.debugIt(64)) {
/*  395 */       debug.debug(-142394261359015L, "clearBody");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesBody() {
/*  402 */     if (debug.debugIt(32)) {
/*  403 */       debug.debug(-165922073994779L, "getBytesBody");
/*      */     }
/*      */     
/*  406 */     byte[] result = null;
/*  407 */     if (this.cursor.isPresent(26)) {
/*  408 */       result = this.cursor.getByteArray(26);
/*      */     } else {
/*      */       
/*  411 */       throw stateException(-405169512);
/*      */     } 
/*      */     
/*  414 */     if (debug.debugIt(64)) {
/*  415 */       debug.debug(-142394261359015L, "getBytesBody", result);
/*      */     }
/*      */     
/*  418 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytesBody(byte[] bodyVal) {
/*  424 */     if (debug.debugIt(32)) {
/*  425 */       debug.debug(-165922073994779L, "setBytesBody", bodyVal);
/*      */     }
/*      */     
/*  428 */     this.bodyHandle = null;
/*  429 */     this.cursor.setByteArray(26, bodyVal);
/*      */     
/*  431 */     if (debug.debugIt(64)) {
/*  432 */       debug.debug(-142394261359015L, "setBytesBody");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTextBody() {
/*  439 */     if (debug.debugIt(32)) {
/*  440 */       debug.debug(-165922073994779L, "getTextBody");
/*      */     }
/*      */     
/*  443 */     String result = null;
/*  444 */     if (this.cursor.isPresent(27)) {
/*  445 */       result = this.cursor.getString(27);
/*      */     } else {
/*      */       
/*  448 */       throw stateException(1368123826);
/*      */     } 
/*      */     
/*  451 */     if (debug.debugIt(64)) {
/*  452 */       debug.debug(-142394261359015L, "getTextBody", result);
/*      */     }
/*      */     
/*  455 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextBody(String text) {
/*  461 */     if (debug.debugIt(32)) {
/*  462 */       debug.debug(-165922073994779L, "setTextBody", text);
/*      */     }
/*      */     
/*  465 */     this.bodyHandle = null;
/*  466 */     this.cursor.setString(27, text);
/*      */     
/*  468 */     if (debug.debugIt(64)) {
/*  469 */       debug.debug(-142394261359015L, "setTextBody");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Serializable getObjectBody() throws IOException, ClassNotFoundException {
/*  476 */     if (debug.debugIt(32)) {
/*  477 */       debug.debug(-165922073994779L, "getObjectBody");
/*      */     }
/*      */     
/*  480 */     Serializable answer = null;
/*  481 */     if (this.cursor.isPresent(25)) {
/*  482 */       answer = this.cursor.getObject(25);
/*      */     } else {
/*      */       
/*  485 */       throw stateException(1623779279);
/*      */     } 
/*      */     
/*  488 */     if (debug.debugIt(64)) {
/*  489 */       debug.debug(-142394261359015L, "getObjectBody", answer);
/*      */     }
/*      */     
/*  492 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getObjectRaw() {
/*  498 */     if (debug.debugIt(32)) {
/*  499 */       debug.debug(-165922073994779L, "getObjectRaw");
/*      */     }
/*      */     
/*  502 */     byte[] result = this.cursor.getEncodedObject(25);
/*      */     
/*  504 */     if (debug.debugIt(64)) {
/*  505 */       debug.debug(-142394261359015L, "getObjectRaw", result);
/*      */     }
/*      */     
/*  508 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectBody(Serializable obj) throws IOException {
/*  514 */     if (debug.debugIt(32)) {
/*  515 */       debug.debug(-165922073994779L, "setObjectBody", obj);
/*      */     }
/*      */     
/*  518 */     this.bodyHandle = null;
/*  519 */     this.cursor.setObject(25, obj);
/*      */     
/*  521 */     if (debug.debugIt(64)) {
/*  522 */       debug.debug(-142394261359015L, "setObjectBody");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectRaw(byte[] obj) {
/*  529 */     if (debug.debugIt(32)) {
/*  530 */       debug.debug(-165922073994779L, "setObjectRaw", obj);
/*      */     }
/*      */     
/*  533 */     this.cursor.setEncodedObject(25, obj);
/*      */     
/*  535 */     if (debug.debugIt(64)) {
/*  536 */       debug.debug(-142394261359015L, "setObjectRaw");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector getFields() {
/*  543 */     if (debug.debugIt(32)) {
/*  544 */       debug.debug(-165922073994779L, "getFields");
/*      */     }
/*      */     
/*  547 */     Vector<Object> ans = null;
/*  548 */     if (this.cursor.isPresent(28)) {
/*  549 */       ans = new Vector();
/*      */       
/*  551 */       for (MessageBodyHandle t = this.cursor.getTableRow(28, 0); t != null; t = t.getSuccessor()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  556 */         int index = t.getChoice(12) - 1;
/*      */         try {
/*  558 */           ans.addElement(t.getValue(index));
/*      */         }
/*  560 */         catch (ClassNotFoundException e) {
/*      */ 
/*      */           
/*  563 */           throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */         }
/*  565 */         catch (IOException e) {
/*      */           
/*  567 */           throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  572 */       throw stateException(-412851445);
/*      */     } 
/*      */     
/*  575 */     if (debug.debugIt(64)) {
/*  576 */       debug.debug(-142394261359015L, "getFields", ans);
/*      */     }
/*      */     
/*  579 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFields(Vector fields) {
/*  585 */     if (debug.debugIt(32)) {
/*  586 */       debug.debug(-165922073994779L, "setFields", fields);
/*      */     }
/*      */     
/*  589 */     this.bodyHandle = null;
/*  590 */     for (int i = 0; i < fields.size(); i++) {
/*  591 */       Object value = fields.elementAt(i);
/*  592 */       if (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Boolean) && !(value instanceof Short) && !(value instanceof Long) && !(value instanceof byte[]) && !(value instanceof Byte) && !(value instanceof Character) && !(value instanceof Double) && !(value instanceof Float) && value != null)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  597 */         throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(1612396596, new Object[] { value }));
/*      */       }
/*      */     } 
/*      */     
/*  601 */     MessageBodyHandle[] fieldTable = new MessageBodyHandle[fields.size()];
/*      */     
/*  603 */     for (int j = 0; j < fields.size(); j++) {
/*  604 */       int choice; fieldTable[j] = this.cursor.newTableRow(28);
/*  605 */       Object value = fields.elementAt(j);
/*      */       
/*  607 */       if (value instanceof String) {
/*  608 */         choice = 8;
/*      */       }
/*  610 */       else if (value instanceof Integer) {
/*  611 */         choice = 4;
/*      */       }
/*  613 */       else if (value instanceof Boolean) {
/*  614 */         choice = 0;
/*      */       }
/*  616 */       else if (value instanceof Short) {
/*  617 */         choice = 2;
/*      */       }
/*  619 */       else if (value instanceof Long) {
/*  620 */         choice = 5;
/*      */       }
/*  622 */       else if (value instanceof byte[] || value == null) {
/*  623 */         choice = 9;
/*      */       }
/*  625 */       else if (value instanceof Byte) {
/*  626 */         choice = 1;
/*      */       }
/*  628 */       else if (value instanceof Character) {
/*  629 */         choice = 3;
/*      */       }
/*  631 */       else if (value instanceof Double) {
/*  632 */         choice = 7;
/*      */       }
/*  634 */       else if (value instanceof Float) {
/*  635 */         choice = 6;
/*      */       }
/*      */       else {
/*      */         
/*  639 */         throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */       } 
/*      */       try {
/*  642 */         fieldTable[j].setValue(choice, value);
/*      */       }
/*  644 */       catch (IOException e) {
/*      */         
/*  646 */         throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */       } 
/*      */     } 
/*  649 */     this.cursor.setTable(28, fieldTable);
/*  650 */     if (debug.debugIt(64)) {
/*  651 */       debug.debug(-142394261359015L, "setFields");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Dictionary getNamedFields() {
/*  658 */     if (debug.debugIt(32)) {
/*  659 */       debug.debug(-165922073994779L, "getNamedFields");
/*      */     }
/*      */     
/*  662 */     Dictionary<Object, Object> ans = null;
/*  663 */     if (this.cursor.isPresent(29)) {
/*  664 */       ans = new Hashtable<>();
/*      */       
/*  666 */       for (MessageBodyHandle t = this.cursor.getTableRow(29, 0); t != null; t = t.getSuccessor()) {
/*      */         Object val;
/*      */ 
/*      */ 
/*      */         
/*  671 */         int index = t.getChoice(13);
/*      */         
/*      */         try {
/*  674 */           val = t.getValue(index);
/*      */         }
/*  676 */         catch (ClassNotFoundException e) {
/*      */ 
/*      */           
/*  679 */           throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */         }
/*  681 */         catch (IOException e) {
/*      */           
/*  683 */           throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */         } 
/*  685 */         if (val != null) {
/*  686 */           ans.put(t.getString(0), val);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  692 */       throw stateException(-784342957);
/*      */     } 
/*      */     
/*  695 */     if (debug.debugIt(64)) {
/*  696 */       debug.debug(-142394261359015L, "getNamedFields", ans);
/*      */     }
/*      */     
/*  699 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNamedFields(Dictionary fields) {
/*  705 */     if (debug.debugIt(32)) {
/*  706 */       debug.debug(-165922073994779L, "setNamedFields", fields);
/*      */     }
/*      */     
/*  709 */     this.bodyHandle = null;
/*  710 */     int n = 0;
/*  711 */     for (Enumeration e = fields.keys(); e.hasMoreElements(); ) {
/*  712 */       Object name = e.nextElement();
/*  713 */       if (!(name instanceof String)) {
/*  714 */         throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(1612396596, new Object[] { name }));
/*      */       }
/*  716 */       Object value = fields.get(name);
/*  717 */       if (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Boolean) && !(value instanceof Short) && !(value instanceof Long) && !(value instanceof byte[]) && !(value instanceof Byte) && !(value instanceof Character) && !(value instanceof Double) && !(value instanceof Float))
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  722 */         throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(1612396596, new Object[] { value }));
/*      */       }
/*  724 */       n++;
/*      */     } 
/*  726 */     MessageBodyHandle[] fieldTable = new MessageBodyHandle[n];
/*  727 */     n = 0;
/*  728 */     for (Enumeration<String> enumeration = fields.keys(); enumeration.hasMoreElements(); n++) {
/*  729 */       int choice; String name = enumeration.nextElement();
/*  730 */       Object value = fields.get(name);
/*  731 */       fieldTable[n] = this.cursor.newTableRow(29);
/*  732 */       fieldTable[n].setString(0, name);
/*      */       
/*  734 */       if (value instanceof String) {
/*  735 */         choice = 9;
/*      */       }
/*  737 */       else if (value instanceof Integer) {
/*  738 */         choice = 5;
/*      */       }
/*  740 */       else if (value instanceof Boolean) {
/*  741 */         choice = 1;
/*      */       }
/*  743 */       else if (value instanceof Short) {
/*  744 */         choice = 3;
/*      */       }
/*  746 */       else if (value instanceof Long) {
/*  747 */         choice = 6;
/*      */       }
/*  749 */       else if (value instanceof byte[]) {
/*  750 */         choice = 10;
/*      */       }
/*  752 */       else if (value instanceof Byte) {
/*  753 */         choice = 2;
/*      */       }
/*  755 */       else if (value instanceof Character) {
/*  756 */         choice = 4;
/*      */       }
/*  758 */       else if (value instanceof Double) {
/*  759 */         choice = 8;
/*      */       }
/*  761 */       else if (value instanceof Float) {
/*  762 */         choice = 7;
/*      */       }
/*      */       else {
/*      */         
/*  766 */         throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */       } 
/*      */       try {
/*  769 */         fieldTable[n].setValue(choice, value);
/*      */       }
/*  771 */       catch (IOException f) {
/*      */         
/*  773 */         throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */       } 
/*      */     } 
/*  776 */     this.cursor.setTable(29, fieldTable);
/*      */     
/*  778 */     if (debug.debugIt(64)) {
/*  779 */       debug.debug(-142394261359015L, "setNamedFields");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPubendID() {
/*  786 */     return this.cursor.getLong(5);
/*      */   }
/*      */   
/*      */   public long getStamp() {
/*  790 */     return this.cursor.getLong(6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle getSchematizedBody(Schema schema) {
/*  796 */     if (this.bodyHandle == null) {
/*  797 */       if (this.cursor.isPresent(31)) {
/*  798 */         this.bodyHandle = this.cursor.getDynamic(31, schema);
/*      */       } else {
/*      */         
/*  801 */         throw stateException(990251738);
/*      */       } 
/*      */     }
/*  804 */     return this.bodyHandle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBodyHandle setSchematizedBody(Schema schema) {
/*  811 */     this.cursor.setChoice(152, 5);
/*      */     
/*  813 */     this.bodyHandle = this.cursor.newDynamic(schema);
/*      */     
/*  815 */     return this.bodyHandle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkJMS() {
/*  822 */     if (this.cursor.getChoice(152) != 4) {
/*  823 */       throw stateException(-361403236);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Dictionary getProperties() {
/*  830 */     if (debug.debugIt(32)) {
/*  831 */       debug.debug(-165922073994779L, "getProperties");
/*      */     }
/*      */     
/*  834 */     checkJMS();
/*  835 */     if (this.propertyNames == null) {
/*  836 */       getPropertiesFromMessage();
/*      */     }
/*  838 */     Dictionary<Object, Object> ans = new Hashtable<>();
/*  839 */     if (this.propertyNames != null) {
/*  840 */       for (int i = 0; i < this.propertyNames.length; i++) {
/*  841 */         ans.put(this.propertyNames[i], this.propertyValues[i]);
/*      */       }
/*      */     }
/*      */     
/*  845 */     if (debug.debugIt(64)) {
/*  846 */       debug.debug(-142394261359015L, "getProperties", ans);
/*      */     }
/*      */     
/*  849 */     return ans;
/*      */   }
/*      */   
/*      */   private void getPropertiesFromMessage() {
/*      */     MessageBodyHandle[] props;
/*      */     int i;
/*  855 */     switch (this.cursor.getChoice(156)) {
/*      */ 
/*      */       
/*      */       case 1:
/*  859 */         props = this.cursor.getTable(23);
/*  860 */         this.propertyNames = new String[props.length];
/*  861 */         this.propertyValues = new Object[props.length];
/*  862 */         for (i = 0; i < props.length; i++) {
/*  863 */           this.propertyNames[i] = props[i].getString(0);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  868 */           int propertyType = props[i].getChoice(11);
/*      */           try {
/*  870 */             this.propertyValues[i] = props[i].getValue(propertyType);
/*      */           }
/*  872 */           catch (ClassNotFoundException e) {
/*      */ 
/*      */             
/*  875 */             throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */           }
/*  877 */           catch (IOException e) {
/*      */             
/*  879 */             throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */           } 
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2:
/*  886 */         if (this.dest == null && 
/*  887 */           this.conn != null) {
/*      */           try {
/*  889 */             this.dest = this.conn.createTopicInternal(getTopicString(), true);
/*      */           }
/*  891 */           catch (IOException iOException) {}
/*      */         }
/*      */         
/*  894 */         if (this.dest != null && this.dest.schema != null && this.dest.symTab != null) {
/*      */ 
/*      */           
/*      */           try {
/*  898 */             Object[] indexNames = this.dest.symTab.indexNames;
/*      */             int len;
/*  900 */             for (len = 0; len < indexNames.length && 
/*  901 */               indexNames[len] != null; len++);
/*      */ 
/*      */ 
/*      */             
/*  905 */             this.propertyNames = new String[len];
/*  906 */             System.arraycopy(indexNames, 0, this.propertyNames, 0, len);
/*      */           }
/*  908 */           catch (ClassCastException e) {
/*  909 */             throw new IllegalStateException(ExceptionBuilder.buildReasonString(772649117, new Object[] { this.dest.schema.getName() }));
/*      */           } 
/*  911 */           this.propertyValues = new Object[this.propertyNames.length];
/*  912 */           MessageBodyHandle sprops = this.cursor.getDynamic(24, (Schema)this.dest.schema);
/*  913 */           for (int j = 0; j < this.propertyNames.length; j++) {
/*      */             try {
/*  915 */               this.propertyValues[j] = sprops.getValue(j);
/*      */             }
/*  917 */             catch (ClassNotFoundException e) {
/*      */               
/*  919 */               throw new IllegalStateException(ExceptionBuilder.buildReasonString(772649117, new Object[] { this.dest.schema.getName() }));
/*      */             }
/*  921 */             catch (IOException e) {
/*      */               
/*  923 */               throw new IllegalStateException(ExceptionBuilder.buildReasonString(772649117, new Object[] { this.dest.schema.getName() }));
/*      */             } 
/*      */           } 
/*      */           return;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  933 */     this.propertyNames = null;
/*  934 */     this.propertyValues = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperties(Dictionary properties) {
/*  941 */     if (debug.debugIt(32)) {
/*  942 */       debug.debug(-165922073994779L, "setProperties", properties);
/*      */     }
/*      */ 
/*      */     
/*  946 */     this.cursor.setChoice(152, 4);
/*  947 */     this.bodyHandle = null;
/*      */ 
/*      */ 
/*      */     
/*  951 */     int n = properties.size();
/*  952 */     this.propertyNames = new String[n];
/*  953 */     this.propertyValues = new Object[n];
/*  954 */     n = 0;
/*  955 */     for (Enumeration e = properties.keys(); e.hasMoreElements(); n++) {
/*  956 */       Object name = e.nextElement();
/*  957 */       if (!(name instanceof String)) {
/*  958 */         this.propertyNames = null;
/*  959 */         this.propertyValues = null;
/*  960 */         throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(1612396596, new Object[] { name }));
/*      */       } 
/*  962 */       this.propertyNames[n] = (String)name;
/*  963 */       Object value = properties.get(name);
/*  964 */       if (!(value instanceof String) && !(value instanceof Number) && !(value instanceof Boolean)) {
/*      */         
/*  966 */         this.propertyNames = null;
/*  967 */         this.propertyValues = null;
/*  968 */         throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(1612396596, new Object[] { name }));
/*      */       } 
/*  970 */       this.propertyValues[n] = value;
/*      */     } 
/*      */     
/*  973 */     if (debug.debugIt(64)) {
/*  974 */       debug.debug(-142394261359015L, "setProperties");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearProperties() {
/*  981 */     if (debug.debugIt(32)) {
/*  982 */       debug.debug(-165922073994779L, "clearProperties");
/*      */     }
/*      */     
/*  985 */     this.bodyHandle = null;
/*  986 */     this.propertyNames = null;
/*  987 */     this.propertyValues = null;
/*  988 */     this.cursor.setChoice(156, 0);
/*      */ 
/*      */     
/*  991 */     if (debug.debugIt(64)) {
/*  992 */       debug.debug(-142394261359015L, "clearProperties");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCorrelationID() {
/*  999 */     if (debug.debugIt(32)) {
/* 1000 */       debug.debug(-165922073994779L, "getCorrelationID");
/*      */     }
/*      */     
/* 1003 */     checkJMS();
/* 1004 */     String result = null;
/* 1005 */     if (this.cursor.getChoice(153) != 0) {
/* 1006 */       result = this.cursor.getString(17);
/*      */     }
/*      */     
/* 1009 */     if (debug.debugIt(64)) {
/* 1010 */       debug.debug(-142394261359015L, "getCorrelationID", result);
/*      */     }
/*      */     
/* 1013 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCorrelationID(String value) {
/* 1019 */     if (debug.debugIt(32)) {
/* 1020 */       debug.debug(-165922073994779L, "setCorrelationID", value);
/*      */     }
/*      */     
/* 1023 */     this.bodyHandle = null;
/* 1024 */     if (value == null) {
/* 1025 */       this.cursor.setChoice(153, 0);
/*      */     }
/*      */     else {
/*      */       
/* 1029 */       this.cursor.setString(17, value);
/*      */     } 
/*      */     
/* 1032 */     if (debug.debugIt(64)) {
/* 1033 */       debug.debug(-142394261359015L, "setCorrelationID");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getMessageID() {
/* 1040 */     if (debug.debugIt(32)) {
/* 1041 */       debug.debug(-165922073994779L, "getMessageID");
/*      */     }
/*      */     
/* 1044 */     long result = this.cursor.getLong(11);
/*      */     
/* 1046 */     if (debug.debugIt(64)) {
/* 1047 */       debug.debug(-142394261359015L, "getMessageID", Long.valueOf(result));
/*      */     }
/*      */     
/* 1050 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageID(long id) {
/* 1057 */     if (debug.debugIt(32)) {
/* 1058 */       debug.debug(-165922073994779L, "setMessageID", Long.valueOf(id));
/*      */     }
/*      */     
/* 1061 */     this.cursor.setLong(11, id);
/*      */     
/* 1063 */     if (debug.debugIt(64)) {
/* 1064 */       debug.debug(-142394261359015L, "setMessageID");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPriority() {
/* 1071 */     if (debug.debugIt(32)) {
/* 1072 */       debug.debug(-165922073994779L, "getPriority");
/*      */     }
/*      */     
/* 1075 */     int result = this.cursor.getByte(2);
/*      */     
/* 1077 */     if (debug.debugIt(64)) {
/* 1078 */       debug.debug(-142394261359015L, "getPriority", Integer.valueOf(result));
/*      */     }
/*      */     
/* 1081 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(int value) {
/* 1087 */     if (debug.debugIt(32)) {
/* 1088 */       debug.debug(-165922073994779L, "setPriority", Integer.valueOf(value));
/*      */     }
/*      */     
/* 1091 */     this.cursor.setByte(2, (byte)value);
/*      */     
/* 1093 */     if (debug.debugIt(64)) {
/* 1094 */       debug.debug(-142394261359015L, "setPriority");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getTimestamp() {
/* 1101 */     if (debug.debugIt(32)) {
/* 1102 */       debug.debug(-165922073994779L, "getTimestamp");
/*      */     }
/*      */     
/* 1105 */     checkJMS();
/* 1106 */     long result = 0L;
/* 1107 */     if (this.cursor.isPresent(18)) {
/* 1108 */       result = this.cursor.getLong(18);
/*      */     }
/*      */     
/* 1111 */     if (debug.debugIt(64)) {
/* 1112 */       debug.debug(-142394261359015L, "getTimestamp", Long.valueOf(result));
/*      */     }
/*      */     
/* 1115 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTimestamp(long value) {
/* 1121 */     if (debug.debugIt(32)) {
/* 1122 */       debug.debug(-165922073994779L, "setTimestamp", Long.valueOf(value));
/*      */     }
/*      */     
/* 1125 */     this.bodyHandle = null;
/* 1126 */     if (value == 0L) {
/* 1127 */       this.cursor.setChoice(154, 0);
/*      */     }
/*      */     else {
/*      */       
/* 1131 */       this.cursor.setLong(18, value);
/*      */     } 
/*      */     
/* 1134 */     if (debug.debugIt(64)) {
/* 1135 */       debug.debug(-142394261359015L, "setTimestamp");
/*      */     }
/*      */   }
/*      */   
/*      */   public long getJMSExpirationField() {
/* 1140 */     checkJMS();
/* 1141 */     return this.cursor.getLong(21);
/*      */   }
/*      */   
/*      */   public void setJMSExpirationField(long value) {
/* 1145 */     this.bodyHandle = null;
/* 1146 */     this.cursor.setLong(21, value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSTypeField() {
/* 1152 */     if (debug.debugIt(32)) {
/* 1153 */       debug.debug(-165922073994779L, "getJMSTypeField");
/*      */     }
/*      */     
/* 1156 */     checkJMS();
/* 1157 */     String result = null;
/* 1158 */     if (this.cursor.isPresent(19)) {
/* 1159 */       result = this.cursor.getString(19);
/*      */     }
/*      */     
/* 1162 */     if (debug.debugIt(64)) {
/* 1163 */       debug.debug(-142394261359015L, "getJMSTypeField", result);
/*      */     }
/*      */     
/* 1166 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSTypeField(String value) {
/* 1172 */     if (debug.debugIt(32)) {
/* 1173 */       debug.debug(-165922073994779L, "setJMSTypeField", value);
/*      */     }
/*      */     
/* 1176 */     this.bodyHandle = null;
/* 1177 */     if (value == null) {
/* 1178 */       this.cursor.setChoice(155, 0);
/*      */     } else {
/*      */       
/* 1181 */       this.cursor.setString(19, value);
/*      */     } 
/*      */     
/* 1184 */     if (debug.debugIt(64)) {
/* 1185 */       debug.debug(-142394261359015L, "setJMSTypeField");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPersistent() {
/* 1192 */     if (this.cursor.isPresent(10)) {
/* 1193 */       return this.cursor.getBoolean(10);
/*      */     }
/*      */     
/* 1196 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistent(boolean value) {
/* 1203 */     this.cursor.setBoolean(10, value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Topic getReplyTopic() {
/* 1209 */     if (debug.debugIt(32)) {
/* 1210 */       debug.debug(-165922073994779L, "getReplyTopic");
/*      */     }
/*      */     
/* 1213 */     String ans = getReplyString();
/* 1214 */     Topic result = null;
/* 1215 */     if (ans != null) {
/* 1216 */       if (this.conn != null) {
/*      */         try {
/* 1218 */           result = this.conn.createTopicInternal(ans, true);
/*      */         }
/* 1220 */         catch (IOException iOException) {}
/*      */       }
/* 1222 */       if (result == null) {
/* 1223 */         result = new TopicImpl(ans, true);
/*      */       }
/*      */     } 
/*      */     
/* 1227 */     if (debug.debugIt(64)) {
/* 1228 */       debug.debug(-142394261359015L, "getReplyTopic", result);
/*      */     }
/*      */     
/* 1231 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyString() {
/* 1238 */     if (debug.debugIt(32)) {
/* 1239 */       debug.debug(-165922073994779L, "getReplyString");
/*      */     }
/*      */     
/* 1242 */     String result = null;
/* 1243 */     if (this.cursor.isPresent(12)) {
/* 1244 */       result = toInternalTopic(this.cursor.getString(12));
/*      */     }
/*      */     
/* 1247 */     if (debug.debugIt(64)) {
/* 1248 */       debug.debug(-142394261359015L, "getReplyString", result);
/*      */     }
/*      */     
/* 1251 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyTopic(Topic value) {
/* 1257 */     if (debug.debugIt(32)) {
/* 1258 */       debug.debug(-165922073994779L, "setReplyTopic", value);
/*      */     }
/*      */     
/* 1261 */     if (value == null) {
/* 1262 */       setReplyString(null);
/*      */     } else {
/*      */       
/* 1265 */       setReplyString(((TopicImpl)value).fullName);
/*      */     } 
/*      */     
/* 1268 */     if (debug.debugIt(64)) {
/* 1269 */       debug.debug(-142394261359015L, "setReplyTopic");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReplyString(String value) {
/* 1277 */     if (debug.debugIt(32)) {
/* 1278 */       debug.debug(-165922073994779L, "setReplyString", value);
/*      */     }
/*      */     
/* 1281 */     if (value == null) {
/* 1282 */       this.cursor.setChoice(150, 0);
/*      */     }
/*      */     else {
/*      */       
/* 1286 */       this.cursor.setString(12, toExternalTopic(value));
/*      */     } 
/*      */     
/* 1289 */     if (debug.debugIt(64)) {
/* 1290 */       debug.debug(-142394261359015L, "setReplyString");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Topic getTopic() {
/*      */     Topic result;
/* 1297 */     if (debug.debugIt(32)) {
/* 1298 */       debug.debug(-165922073994779L, "getTopic");
/*      */     }
/*      */ 
/*      */     
/* 1302 */     String fullName = getTopicString();
/* 1303 */     if (this.dest == null && 
/* 1304 */       this.conn != null) {
/*      */       try {
/* 1306 */         this.dest = this.conn.createTopicInternal(fullName, true);
/*      */       }
/* 1308 */       catch (IOException iOException) {}
/*      */     }
/*      */     
/* 1311 */     if (this.dest != null) {
/* 1312 */       result = this.dest;
/*      */     } else {
/*      */       
/* 1315 */       result = new TopicImpl(fullName, true);
/*      */     } 
/*      */     
/* 1318 */     if (debug.debugIt(64)) {
/* 1319 */       debug.debug(-142394261359015L, "getTopic", result);
/*      */     }
/*      */     
/* 1322 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTopicString() {
/* 1329 */     if (debug.debugIt(32)) {
/* 1330 */       debug.debug(-165922073994779L, "getTopicString");
/*      */     }
/*      */     
/* 1333 */     String result = this.cursor.getString(4);
/* 1334 */     if (result != null && result.length() > 0) {
/* 1335 */       result = toInternalTopic(result);
/*      */     } else {
/*      */       
/* 1338 */       result = null;
/*      */     } 
/*      */     
/* 1341 */     if (debug.debugIt(64)) {
/* 1342 */       debug.debug(-142394261359015L, "getTopicString", result);
/*      */     }
/*      */     
/* 1345 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTopic(Topic value) {
/* 1351 */     if (debug.debugIt(32)) {
/* 1352 */       debug.debug(-165922073994779L, "setTopic", value);
/*      */     }
/*      */     
/* 1355 */     this.dest = (TopicImpl)value;
/* 1356 */     setTopicString(this.dest.fullName);
/* 1357 */     if (!this.dest.propertiesSet) {
/* 1358 */       this.dest = null;
/*      */     }
/*      */     
/* 1361 */     if (debug.debugIt(64)) {
/* 1362 */       debug.debug(-142394261359015L, "setTopic");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTopicString(String value) {
/* 1370 */     if (debug.debugIt(32)) {
/* 1371 */       debug.debug(-165922073994779L, "setTopicString", value);
/*      */     }
/*      */     
/* 1374 */     this.cursor.setString(4, toExternalTopic(value));
/*      */     
/* 1376 */     if (debug.debugIt(64)) {
/* 1377 */       debug.debug(-142394261359015L, "setTopicString");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void prepareToSend(ConnectorImpl conn) throws IOException {
/*      */     String topicName;
/* 1386 */     this.conn = conn;
/*      */ 
/*      */     
/* 1389 */     if (this.dest == null) {
/* 1390 */       topicName = getTopicString();
/*      */     } else {
/*      */       
/* 1393 */       topicName = this.dest.fullName;
/*      */     } 
/* 1395 */     if (topicName.startsWith("\001TEMP/")) {
/*      */ 
/*      */       
/* 1398 */       if (this.bodyHandle != null) {
/* 1399 */         this.cursor.setDynamic(31, this.bodyHandle);
/*      */       }
/* 1401 */       else if (this.propertyNames != null) {
/* 1402 */         encodeGenericProperties();
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/* 1407 */     if (this.dest == null || !this.dest.propertiesSet) {
/* 1408 */       this.dest = conn.createTopicInternal(topicName, true);
/*      */     }
/*      */     
/* 1411 */     if (this.dest.schemaType == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1418 */       if (this.bodyHandle == null) {
/* 1419 */         throw new IOException(ExceptionBuilder.buildReasonString(-830305446, new Object[] { topicName }));
/*      */       }
/* 1421 */       String realName = ((MessageDataHandle)this.bodyHandle).getInternalSchema().getName();
/* 1422 */       if (!realName.equals(this.dest.schemaName)) {
/* 1423 */         throw new IOException(ExceptionBuilder.buildReasonString(-830305446, new Object[] { topicName }));
/*      */       }
/* 1425 */       this.cursor.setDynamic(31, this.bodyHandle);
/*      */     } else {
/* 1427 */       if (this.bodyHandle != null) {
/* 1428 */         throw new IOException(ExceptionBuilder.buildReasonString(-830305446, new Object[] { topicName }));
/*      */       }
/* 1430 */       if (this.dest.schemaType == 1) {
/* 1431 */         encodeSchematizedProperties();
/*      */       }
/* 1433 */       else if (this.propertyNames != null) {
/* 1434 */         encodeGenericProperties();
/*      */       } 
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
/*      */   private void encodeSchematizedProperties() throws IOException {
/* 1449 */     Object[] schemaNames = this.dest.symTab.indexNames;
/* 1450 */     MessageBodyHandle props = this.cursor.newDynamic((Schema)this.dest.schema); int i;
/* 1451 */     for (i = 0; i < schemaNames.length; i++) {
/* 1452 */       if (schemaNames[i] != null) {
/* 1453 */         Object value = null;
/* 1454 */         for (int j = 0; j < this.propertyNames.length; j++) {
/* 1455 */           if (schemaNames[i].equals(this.propertyNames[j])) {
/* 1456 */             value = this.propertyValues[j];
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         try {
/* 1461 */           props.setValue(i, value);
/*      */         }
/* 1463 */         catch (SchemaViolationException e) {
/* 1464 */           throw new IOException(ExceptionBuilder.buildReasonString(-830305446, new Object[] { this.dest.fullName }));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1468 */     for (i = 0; i < this.propertyNames.length; i++) {
/* 1469 */       boolean notFound = true;
/* 1470 */       for (int j = 0; j < schemaNames.length; j++) {
/* 1471 */         if (this.propertyNames[i].equals(schemaNames[j])) {
/* 1472 */           notFound = false;
/*      */           break;
/*      */         } 
/*      */       } 
/* 1476 */       if (notFound) {
/* 1477 */         throw new IOException(ExceptionBuilder.buildReasonString(-830305446, new Object[] { this.dest.fullName }));
/*      */       }
/*      */     } 
/* 1480 */     this.cursor.setDynamic(24, props);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void encodeGenericProperties() {
/* 1487 */     MessageBodyHandle[] table = new MessageBodyHandle[this.propertyNames.length];
/* 1488 */     for (int i = 0; i < table.length; i++) {
/* 1489 */       int choice; table[i] = this.cursor.newTableRow(23);
/* 1490 */       table[i].setString(0, this.propertyNames[i]);
/* 1491 */       Object value = this.propertyValues[i];
/*      */       
/* 1493 */       if (value instanceof String) {
/* 1494 */         choice = 8;
/*      */       }
/* 1496 */       else if (value instanceof Integer) {
/* 1497 */         choice = 4;
/*      */       }
/* 1499 */       else if (value instanceof Boolean) {
/* 1500 */         choice = 1;
/*      */       }
/* 1502 */       else if (value instanceof Short) {
/* 1503 */         choice = 3;
/*      */       }
/* 1505 */       else if (value instanceof Long) {
/* 1506 */         choice = 5;
/*      */       }
/* 1508 */       else if (value instanceof Byte) {
/* 1509 */         choice = 2;
/*      */       }
/* 1511 */       else if (value instanceof Double) {
/* 1512 */         choice = 7;
/*      */       }
/* 1514 */       else if (value instanceof Float) {
/* 1515 */         choice = 6;
/*      */       }
/*      */       else {
/*      */         
/* 1519 */         throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */       } 
/*      */       try {
/* 1522 */         table[i].setValue(choice, value);
/*      */       }
/* 1524 */       catch (IOException f) {
/*      */         
/* 1526 */         throw new InternalError(ExceptionBuilder.buildReasonString(1714011970, null));
/*      */       } 
/*      */     } 
/* 1529 */     this.cursor.setTable(23, table);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toDefaultExternalTopic(String topic) {
/* 1536 */     if (debug.debugIt(32)) {
/* 1537 */       debug.debug(-165922073994779L, "toDefaultExternalTopic", topic);
/*      */     }
/*      */     
/* 1540 */     String result = topic;
/* 1541 */     if (topic.startsWith("\001TEMP/")) {
/*      */       
/* 1543 */       result = "$TEMP/" + topic.substring(BaseConfig.TEMP_TOPIC_PREFIX_LENGTH);
/*      */     } else {
/*      */       
/* 1546 */       result = "$TOPIC/" + topic;
/*      */     } 
/* 1548 */     if (debug.debugIt(64)) {
/* 1549 */       debug.debug(-142394261359015L, "toDefaultExternalTopic", result);
/*      */     }
/*      */     
/* 1552 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String toExternalTopic(String topic) {
/* 1559 */     if (debug.debugIt(32)) {
/* 1560 */       debug.debug(-165922073994779L, "toExternalTopic", topic);
/*      */     }
/*      */     
/* 1563 */     String result = topic;
/*      */     
/* 1565 */     if (this.topicsArePrefixed) {
/* 1566 */       result = toDefaultExternalTopic(topic);
/*      */     }
/*      */     
/* 1569 */     if (debug.debugIt(64)) {
/* 1570 */       debug.debug(-142394261359015L, "toExternalTopic", result);
/*      */     }
/*      */     
/* 1573 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toDefaultInternalTopic(String topic) {
/* 1580 */     if (debug.debugIt(32)) {
/* 1581 */       debug.debug(-165922073994779L, "toDefaultInternalTopic", topic);
/*      */     }
/*      */     
/* 1584 */     String result = topic;
/* 1585 */     if (topic.startsWith("$TEMP/")) {
/*      */       
/* 1587 */       result = "\001TEMP/" + topic.substring(BaseConfig.TEMP_TOPIC_PREFIX_LENGTH);
/*      */     } else {
/*      */       
/* 1590 */       result = topic.substring(BaseConfig.TOPIC_PREFIX_LENGTH);
/*      */     } 
/* 1592 */     if (debug.debugIt(64)) {
/* 1593 */       debug.debug(-142394261359015L, "toDefaultInternalTopic", result);
/*      */     }
/*      */     
/* 1596 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String toInternalTopic(String topic) {
/* 1603 */     if (debug.debugIt(32)) {
/* 1604 */       debug.debug(-165922073994779L, "toInternalTopic", topic);
/*      */     }
/*      */     
/* 1607 */     String result = topic;
/* 1608 */     if (this.topicsArePrefixed) {
/* 1609 */       result = toDefaultInternalTopic(topic);
/*      */     }
/*      */     
/* 1612 */     if (debug.debugIt(64)) {
/* 1613 */       debug.debug(-142394261359015L, "toInternalTopic", result);
/*      */     }
/*      */     
/* 1616 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\MessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */