/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.selector.BooleanValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.PositionAssigner;
/*     */ import com.ibm.disthub2.impl.matching.selector.Resolver;
/*     */ import com.ibm.disthub2.impl.matching.selector.Selector;
/*     */ import java.util.Hashtable;
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
/*     */ public final class DefaultResolver
/*     */   implements Resolver
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  59 */   private Hashtable positions = new Hashtable<>();
/*  60 */   private int nextPosition = 0;
/*     */ 
/*     */   
/*  63 */   private static final DisthubValueAccessor JMSPriority = new DisthubValueAccessor(2, null);
/*     */   
/*  65 */   private static final DisthubValueAccessor JMSType = new DisthubValueAccessor(19, null);
/*     */   
/*  67 */   private static final DisthubValueAccessor JMSCorrelationID = new DisthubValueAccessor(17, null);
/*     */   
/*  69 */   private static final DisthubValueAccessor JMSMessageID = new DisthubValueAccessor(11, new DisthubValueAccessor.Action()
/*     */       {
/*     */         public Object convert(Object arg)
/*     */         {
/*  73 */           return "ID:" + arg;
/*     */         }
/*     */       });
/*  76 */   private static final DisthubValueAccessor JMSTimestamp = new DisthubValueAccessor(18, new DisthubValueAccessor.Action()
/*     */       {
/*     */         
/*     */         public Object convert(Object arg)
/*     */         {
/*  81 */           return (arg == null) ? new NumericValue(0L, true) : arg;
/*     */         }
/*     */       });
/*  84 */   private static final DisthubValueAccessor JMSDeliveryMode = new DisthubValueAccessor(20, new DisthubValueAccessor.Action()
/*     */       {
/*     */         
/*     */         public Object convert(Object arg)
/*     */         {
/*  89 */           return ((BooleanValue)arg).booleanValue() ? "PERSISTENT" : "NON_PERSISTENT";
/*     */         }
/*     */       });
/*     */ 
/*     */ 
/*     */   
/*  95 */   private static final DisthubValueAccessor[] headerPositions = new DisthubValueAccessor[] { JMSPriority, JMSType, JMSCorrelationID, JMSMessageID, JMSTimestamp, JMSDeliveryMode };
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
/*     */   public static DisthubValueAccessor nameToAccessor(String name, int type) {
/* 115 */     if (name.startsWith("JMS")) {
/* 116 */       if (name.equals("JMSPriority")) {
/* 117 */         if (type != 1 && type != 0) {
/* 118 */           return null;
/*     */         }
/* 120 */         return JMSPriority;
/*     */       } 
/* 122 */       if (name.equals("JMSTimestamp")) {
/* 123 */         if (type != 1 && type != 0) {
/* 124 */           return null;
/*     */         }
/* 126 */         return JMSTimestamp;
/*     */       } 
/* 128 */       if (name.equals("JMSMessageID")) {
/* 129 */         if (type != -5 && type != 0) {
/* 130 */           return null;
/*     */         }
/* 132 */         return JMSMessageID;
/*     */       } 
/* 134 */       if (name.equals("JMSCorrelationID")) {
/* 135 */         if (type != -5 && type != 0) {
/* 136 */           return null;
/*     */         }
/* 138 */         return JMSCorrelationID;
/*     */       } 
/* 140 */       if (name.equals("JMSDeliveryMode")) {
/* 141 */         if (type != -5 && type != 0) {
/* 142 */           return null;
/*     */         }
/* 144 */         return JMSDeliveryMode;
/*     */       } 
/* 146 */       if (name.equals("JMSType")) {
/* 147 */         if (type != -5 && type != 0) {
/* 148 */           return null;
/*     */         }
/* 150 */         return JMSType;
/*     */       } 
/* 152 */       if (!name.startsWith("JMS_") && !name.startsWith("JMSX")) {
/* 153 */         return null;
/*     */       }
/*     */     } 
/*     */     
/* 157 */     return DisthubValueAccessor.jmsProperty;
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
/*     */   public static int findHeaderFieldIndex(DisthubValueAccessor acc) {
/* 174 */     for (int i = 0; i < headerPositions.length; i++) {
/* 175 */       if (acc == headerPositions[i]) {
/* 176 */         return i;
/*     */       }
/*     */     } 
/* 179 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selector resolve(Identifier id, PositionAssigner positionAssigner, Object context) {
/* 187 */     id.accessor = nameToAccessor(id.name, id.type);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     if (id.accessor == null || id.type == 4 || id.type == 2) {
/* 194 */       id.type = 2;
/* 195 */       return (Selector)id;
/*     */     } 
/*     */ 
/*     */     
/* 199 */     id.name = "JMS." + id.name;
/*     */ 
/*     */     
/* 202 */     positionAssigner.assign(id);
/*     */     
/* 204 */     return (Selector)id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object pushContext(Object a, Identifier b) {
/* 212 */     throw new IllegalStateException();
/*     */   }
/*     */   
/*     */   public void restoreContext(Object a) {
/* 216 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\DefaultResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */