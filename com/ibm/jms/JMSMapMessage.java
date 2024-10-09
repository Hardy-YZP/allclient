/*     */ package com.ibm.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.internal.JmsMapMessageImpl;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.MapMessage;
/*     */ import javax.jms.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JMSMapMessage
/*     */   extends JMSMessage
/*     */   implements MapMessage
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSMapMessage.java";
/*     */   static final long serialVersionUID = 3908796707964271920L;
/*     */   
/*     */   static {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data("com.ibm.jms.JMSMapMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSMapMessage.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("mapdata", Hashtable.class) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public JMSMapMessage() {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "<init>()");
/*     */     }
/* 154 */     assert false : "Do not call a message's default constructor, use javax.jms.Session methods instead";
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected JMSMapMessage(Message delegateMsg) {
/* 162 */     super(delegateMsg);
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "<init>(Message)", new Object[] { delegateMsg });
/*     */     }
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "<init>(Message)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String name) throws JMSException {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getBoolean(String)", new Object[] { name });
/*     */     }
/* 185 */     boolean traceRet1 = ((MapMessage)this.delegateMsg).getBoolean(name);
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getBoolean(String)", 
/* 188 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 190 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(String name) throws JMSException {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getByte(String)", new Object[] { name });
/*     */     }
/* 206 */     byte traceRet1 = ((MapMessage)this.delegateMsg).getByte(name);
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getByte(String)", Byte.valueOf(traceRet1));
/*     */     }
/* 210 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(String name) throws JMSException {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getBytes(String)", new Object[] { name });
/*     */     }
/* 226 */     byte[] traceRet1 = ((MapMessage)this.delegateMsg).getBytes(name);
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getBytes(String)", traceRet1);
/*     */     }
/* 230 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getChar(String name) throws JMSException {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getChar(String)", new Object[] { name });
/*     */     }
/* 246 */     char traceRet1 = ((MapMessage)this.delegateMsg).getChar(name);
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getChar(String)", 
/* 249 */           Character.valueOf(traceRet1));
/*     */     }
/* 251 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String name) throws JMSException {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getDouble(String)", new Object[] { name });
/*     */     }
/* 267 */     double traceRet1 = ((MapMessage)this.delegateMsg).getDouble(name);
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getDouble(String)", Double.valueOf(traceRet1));
/*     */     }
/*     */     
/* 272 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String name) throws JMSException {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getFloat(String)", new Object[] { name });
/*     */     }
/* 288 */     float traceRet1 = ((MapMessage)this.delegateMsg).getFloat(name);
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getFloat(String)", Float.valueOf(traceRet1));
/*     */     }
/* 292 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String name) throws JMSException {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getInt(String)", new Object[] { name });
/*     */     }
/* 308 */     int traceRet1 = ((MapMessage)this.delegateMsg).getInt(name);
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getInt(String)", Integer.valueOf(traceRet1));
/*     */     }
/* 312 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(String name) throws JMSException {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getLong(String)", new Object[] { name });
/*     */     }
/* 328 */     long traceRet1 = ((MapMessage)this.delegateMsg).getLong(name);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getLong(String)", Long.valueOf(traceRet1));
/*     */     }
/* 332 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<?> getMapNames() throws JMSException {
/* 343 */     Enumeration<?> traceRet1 = ((MapMessage)this.delegateMsg).getMapNames();
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.data(this, "com.ibm.jms.JMSMapMessage", "getMapNames()", "getter", traceRet1);
/*     */     }
/* 347 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObject(String name) throws JMSException {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getObject(String)", new Object[] { name });
/*     */     }
/* 369 */     Object traceRet1 = ((MapMessage)this.delegateMsg).getObject(name);
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getObject(String)", traceRet1);
/*     */     }
/* 373 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(String name) throws JMSException {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getShort(String)", new Object[] { name });
/*     */     }
/* 389 */     short traceRet1 = ((MapMessage)this.delegateMsg).getShort(name);
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getShort(String)", Short.valueOf(traceRet1));
/*     */     }
/* 393 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String name) throws JMSException {
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "getString(String)", new Object[] { name });
/*     */     }
/* 409 */     String traceRet1 = ((MapMessage)this.delegateMsg).getString(name);
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "getString(String)", traceRet1);
/*     */     }
/* 413 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean itemExists(String name) throws JMSException {
/* 425 */     if (Trace.isOn) {
/* 426 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "itemExists(String)", new Object[] { name });
/*     */     }
/* 428 */     boolean traceRet1 = ((MapMessage)this.delegateMsg).itemExists(name);
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "itemExists(String)", 
/* 431 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 433 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(String name, boolean value) throws JMSException {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setBoolean(String,boolean)", new Object[] { name, 
/* 449 */             Boolean.valueOf(value) });
/*     */     }
/* 451 */     ((MapMessage)this.delegateMsg).setBoolean(name, value);
/* 452 */     if (Trace.isOn) {
/* 453 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setBoolean(String,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByte(String name, byte value) throws JMSException {
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setByte(String,byte)", new Object[] { name, 
/* 471 */             Byte.valueOf(value) });
/*     */     }
/* 473 */     ((MapMessage)this.delegateMsg).setByte(name, value);
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setByte(String,byte)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(String name, byte[] value) throws JMSException {
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setBytes(String,byte [ ])", new Object[] { name, value });
/*     */     }
/*     */     
/* 495 */     ((MapMessage)this.delegateMsg).setBytes(name, value);
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setBytes(String,byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
/* 515 */     if (Trace.isOn) {
/* 516 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setBytes(String,byte [ ],int,int)", new Object[] { name, value, 
/* 517 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 519 */     ((MapMessage)this.delegateMsg).setBytes(name, value, offset, length);
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setBytes(String,byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChar(String name, char value) throws JMSException {
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setChar(String,char)", new Object[] { name, 
/* 539 */             Character.valueOf(value) });
/*     */     }
/* 541 */     ((MapMessage)this.delegateMsg).setChar(name, value);
/* 542 */     if (Trace.isOn) {
/* 543 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setChar(String,char)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDouble(String name, double value) throws JMSException {
/* 559 */     if (Trace.isOn) {
/* 560 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setDouble(String,double)", new Object[] { name, 
/* 561 */             Double.valueOf(value) });
/*     */     }
/* 563 */     ((MapMessage)this.delegateMsg).setDouble(name, value);
/* 564 */     if (Trace.isOn) {
/* 565 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setDouble(String,double)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloat(String name, float value) throws JMSException {
/* 581 */     if (Trace.isOn) {
/* 582 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setFloat(String,float)", new Object[] { name, 
/* 583 */             Float.valueOf(value) });
/*     */     }
/* 585 */     ((MapMessage)this.delegateMsg).setFloat(name, value);
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setFloat(String,float)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(String name, int value) throws JMSException {
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setInt(String,int)", new Object[] { name, 
/* 605 */             Integer.valueOf(value) });
/*     */     }
/* 607 */     ((MapMessage)this.delegateMsg).setInt(name, value);
/* 608 */     if (Trace.isOn) {
/* 609 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setInt(String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(String name, long value) throws JMSException {
/* 625 */     if (Trace.isOn) {
/* 626 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setLong(String,long)", new Object[] { name, 
/* 627 */             Long.valueOf(value) });
/*     */     }
/* 629 */     ((MapMessage)this.delegateMsg).setLong(name, value);
/* 630 */     if (Trace.isOn) {
/* 631 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setLong(String,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObject(String name, Object value) throws JMSException {
/* 651 */     if (Trace.isOn) {
/* 652 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setObject(String,Object)", new Object[] { name, value });
/*     */     }
/*     */     
/* 655 */     ((MapMessage)this.delegateMsg).setObject(name, value);
/* 656 */     if (Trace.isOn) {
/* 657 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setObject(String,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShort(String name, short value) throws JMSException {
/* 673 */     if (Trace.isOn) {
/* 674 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setShort(String,short)", new Object[] { name, 
/* 675 */             Short.valueOf(value) });
/*     */     }
/* 677 */     ((MapMessage)this.delegateMsg).setShort(name, value);
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setShort(String,short)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String name, String value) throws JMSException {
/* 695 */     if (Trace.isOn) {
/* 696 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "setString(String,String)", new Object[] { name, value });
/*     */     }
/*     */     
/* 699 */     ((MapMessage)this.delegateMsg).setString(name, value);
/* 700 */     if (Trace.isOn) {
/* 701 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "setString(String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 714 */     if (Trace.isOn) {
/* 715 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 725 */       String connectionType = "com.ibm.msg.client.wmq";
/* 726 */       Message superClassMsg = null;
/* 727 */       if (this.delegateMsg != null) {
/* 728 */         superClassMsg = this.delegateMsg;
/*     */       }
/* 730 */       this.delegateMsg = (Message)new JmsMapMessageImpl(connectionType, superClassMsg);
/*     */     }
/* 732 */     catch (JMSException e) {
/* 733 */       if (Trace.isOn) {
/* 734 */         Trace.catchBlock(this, "com.ibm.jms.JMSMapMessage", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 746 */     ObjectInputStream.GetField fields = in.readFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 764 */       if (!fields.defaulted("mapdata")) {
/* 765 */         Hashtable<String, Object> tempTable = (Hashtable<String, Object>)fields.get("mapdata", (Object)null);
/*     */         
/* 767 */         Iterator<Map.Entry<String, Object>> it = tempTable.entrySet().iterator();
/*     */         
/* 769 */         while (it.hasNext()) {
/* 770 */           Map.Entry<String, Object> entry = it.next();
/* 771 */           String key = entry.getKey();
/* 772 */           Object value = entry.getValue();
/* 773 */           setObject(key, value);
/*     */         }
/*     */       
/*     */       } 
/* 777 */     } catch (JMSException je) {
/* 778 */       if (Trace.isOn) {
/* 779 */         Trace.catchBlock(this, "com.ibm.jms.JMSMapMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 786 */     if (Trace.isOn) {
/* 787 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "readObject(java.io.ObjectInputStream)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 793 */     if (Trace.isOn) {
/* 794 */       Trace.entry(this, "com.ibm.jms.JMSMapMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 802 */     ObjectOutputStream.PutField fields = out.putFields();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 808 */       Enumeration<?> e = getMapNames();
/* 809 */       Hashtable<String, Object> outTable = new Hashtable<>();
/* 810 */       while (e.hasMoreElements()) {
/* 811 */         String key = (String)e.nextElement();
/* 812 */         outTable.put(key, getObject(key));
/*     */       } 
/*     */       
/* 815 */       fields.put("mapdata", outTable);
/*     */     }
/* 817 */     catch (JMSException je) {
/* 818 */       if (Trace.isOn) {
/* 819 */         Trace.catchBlock(this, "com.ibm.jms.JMSMapMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 828 */     out.writeFields();
/*     */     
/* 830 */     if (Trace.isOn)
/* 831 */       Trace.exit(this, "com.ibm.jms.JMSMapMessage", "writeObject(java.io.ObjectOutputStream)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSMapMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */