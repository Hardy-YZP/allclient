/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.jms.JMSException;
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
/*     */ public abstract class WMQMessage
/*     */   extends WMQMessageBase
/*     */   implements ProviderMessage, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6449317773014149134L;
/*     */   public static final String CLASS_BYTES = "jms_bytes";
/*     */   public static final String CLASS_MAP = "jms_map";
/*     */   public static final String CLASS_NONE = "jms_none";
/*     */   public static final String CLASS_OBJECT = "jms_object";
/*     */   public static final String CLASS_STREAM = "jms_stream";
/*     */   public static final String CLASS_TEXT = "jms_text";
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessage.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessage.java");
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
/*     */   public void clearBody() throws JMSException {}
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
/*     */   public String toString() {
/* 123 */     StringBuffer sb = new StringBuffer(512);
/*     */ 
/*     */     
/*     */     try {
/* 127 */       sb.append("\n  ProviderMessage class: " + this.messageClass);
/* 128 */       sb.append("\n  JMSType:         " + getJMSType());
/* 129 */       sb.append("\n  JMSDeliveryMode: " + getJMSDeliveryMode());
/* 130 */       sb.append("\n  JMSExpiration:   " + getJMSExpiration());
/* 131 */       sb.append("\n  JMSDeliveryDelay " + getJMSDeliveryDelay());
/* 132 */       sb.append("\n  JMSDeliveryTime: " + getJMSDeliveryTime());
/* 133 */       sb.append("\n  JMSPriority:     " + getJMSPriority());
/* 134 */       sb.append("\n  JMSMessageID:    " + getJMSMessageID());
/* 135 */       sb.append("\n  JMSTimestamp:    " + getJMSTimestamp());
/* 136 */       sb.append("\n  JMSCorrelationID:" + getJMSCorrelationID());
/* 137 */       sb.append("\n  JMSDestination:  " + getJMSDestinationAsString());
/* 138 */       sb.append("\n  JMSReplyTo:      " + getJMSReplyToAsString());
/* 139 */       sb.append("\n  JMSRedelivered:  " + getJMSRedelivered());
/*     */       
/* 141 */       Enumeration<String> enumeration = getPropertyNames();
/*     */ 
/*     */       
/* 144 */       ArrayList<String> keys = new ArrayList<>();
/* 145 */       while (enumeration.hasMoreElements()) {
/* 146 */         keys.add(enumeration.nextElement());
/*     */       }
/* 148 */       Collections.sort(keys);
/* 149 */       ListIterator<String> propNames = keys.listIterator();
/*     */       
/* 151 */       while (propNames.hasNext()) {
/* 152 */         String name = propNames.next();
/* 153 */         sb.append("\n    " + name + ": " + getObjectProperty(name));
/*     */       }
/*     */     
/* 156 */     } catch (JMSException jMSException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     String result = sb.toString();
/* 162 */     return result;
/*     */   }
/*     */   
/*     */   public abstract byte[] _exportBody(int paramInt, JmqiCodepage paramJmqiCodepage) throws JMSException;
/*     */   
/*     */   public abstract void _importBody(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, JmqiCodepage paramJmqiCodepage) throws JMSException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */