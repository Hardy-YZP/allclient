/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.client.Topic;
/*     */ import com.ibm.disthub2.impl.formats.Schema;
/*     */ import com.ibm.disthub2.impl.formats.SymbolTable;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TopicImpl
/*     */   implements Topic, Serializable, LogConstants, ExceptionConstants
/*     */ {
/*  79 */   private static final DebugObject debug = new DebugObject("TopicImpl");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   static String[] propertyKeys = new String[] { "schema" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   static String[] propertyDefaults = new String[] { "*" };
/*     */ 
/*     */ 
/*     */   
/*  93 */   private static PropertyHandler[] handlers = new PropertyHandler[] { new SchemaPropertyHandler() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String fullName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propertiesSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int schemaType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String schemaName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Schema schema;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SymbolTable symTab;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setProperties(String[][] properties) throws IOException {
/* 143 */     for (int i = 0; i < propertyKeys.length; i++) {
/* 144 */       for (int j = 0; j < (properties[0]).length; j++) {
/* 145 */         if (propertyKeys[i].equals(properties[0][j])) {
/* 146 */           handlers[i].setProperty(this, properties[1][j]);
/*     */         }
/*     */       } 
/*     */     } 
/* 150 */     this.propertiesSet = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setProperties(TopicImpl from) {
/* 156 */     for (int i = 0; i < handlers.length; i++) {
/* 157 */       handlers[i].copyProperty(from, this);
/*     */     }
/* 159 */     this.propertiesSet = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 168 */     if (isTemporary()) {
/*     */       
/* 170 */       int brk = this.fullName.indexOf("/") + 1;
/* 171 */       brk = this.fullName.indexOf("/", brk) + 1;
/* 172 */       brk = this.fullName.indexOf("/", brk) + 1;
/* 173 */       return this.fullName.substring(brk);
/*     */     } 
/*     */     
/* 176 */     return this.fullName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTemporary() {
/* 182 */     if (debug.debugIt(32)) {
/* 183 */       debug.debug(-165922073994779L, "isTemporary");
/*     */     }
/*     */     
/* 186 */     boolean result = this.fullName.startsWith("\001TEMP/");
/*     */     
/* 188 */     if (debug.debugIt(64)) {
/* 189 */       debug.debug(-142394261359015L, "isTemporary", Boolean.valueOf(result));
/*     */     }
/*     */     
/* 192 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 202 */     if (o instanceof Topic) {
/* 203 */       return this.fullName.equals(((TopicImpl)o).fullName);
/*     */     }
/*     */     
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 215 */     return this.fullName.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicImpl(String name) {
/* 225 */     if (debug.debugIt(32)) {
/* 226 */       debug.debug(-165922073994779L, "TopicImpl", name);
/*     */     }
/*     */     
/* 229 */     if (name == null) {
/* 230 */       throw new NullPointerException(ExceptionBuilder.buildReasonString(32172136, new Object[] { name }));
/*     */     }
/*     */     
/* 233 */     if (name.startsWith("\001TEMP/")) {
/* 234 */       throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(32172136, new Object[] { name }));
/*     */     }
/*     */     
/* 237 */     this.fullName = name;
/*     */     
/* 239 */     if (debug.debugIt(64)) {
/* 240 */       debug.debug(-142394261359015L, "TopicImpl");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toStringInternal() {
/* 246 */     if (debug.debugIt(32)) {
/* 247 */       debug.debug(-165922073994779L, "toStringInternal");
/*     */     }
/*     */     
/* 250 */     if (debug.debugIt(64)) {
/* 251 */       debug.debug(-142394261359015L, "toStringInternal", this.fullName);
/*     */     }
/*     */     
/* 254 */     return this.fullName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TopicImpl(String fullName, boolean ignored) {
/* 265 */     if (debug.debugIt(32)) {
/* 266 */       debug.debug(-165922073994779L, "TopicImpl", fullName, Boolean.valueOf(ignored));
/*     */     }
/*     */     
/* 269 */     this.fullName = fullName;
/*     */     
/* 271 */     if (debug.debugIt(64)) {
/* 272 */       debug.debug(-142394261359015L, "TopicImpl");
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDefaultProperties() throws IOException {
/* 277 */     setProperties(new String[][] { propertyKeys, propertyDefaults });
/*     */   }
/*     */   
/*     */   private static final class SchemaPropertyHandler
/*     */     implements PropertyHandler {
/*     */     private static final String propKey = "PROPERTY_SCHEMA:";
/*     */     
/*     */     private SchemaPropertyHandler() {}
/*     */     
/* 286 */     private static final int propKeyLength = "PROPERTY_SCHEMA:".length();
/*     */     
/*     */     public void setProperty(TopicImpl topic, String value) throws IOException {
/* 289 */       if (value.equals("?")) {
/* 290 */         throw new IOException(ExceptionBuilder.buildReasonString(-1775623905, new Object[] { topic }));
/*     */       }
/* 292 */       if (value.equals("*")) {
/* 293 */         topic.schemaType = 0;
/* 294 */         topic.schemaName = null;
/* 295 */         topic.schema = null;
/* 296 */         topic.symTab = null;
/*     */       
/*     */       }
/* 299 */       else if (value.startsWith("PROPERTY_SCHEMA:")) {
/* 300 */         topic.schemaType = 1;
/* 301 */         topic.schemaName = null;
/*     */         try {
/* 303 */           PropSchema.decode(value.substring(propKeyLength), topic);
/*     */         }
/* 305 */         catch (RuntimeException e) {
/* 306 */           throw new IOException(ExceptionBuilder.buildReasonString(-1775623905, new Object[] { topic }));
/*     */         } 
/*     */       } else {
/*     */         
/* 310 */         topic.schemaType = 2;
/* 311 */         topic.schemaName = value;
/* 312 */         topic.schema = null;
/* 313 */         topic.symTab = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyProperty(TopicImpl from, TopicImpl to) {
/* 319 */       to.schemaType = from.schemaType;
/* 320 */       to.schemaName = from.schemaName;
/* 321 */       to.schema = from.schema;
/* 322 */       to.symTab = from.symTab;
/*     */     }
/*     */   }
/*     */   
/*     */   private static interface PropertyHandler {
/*     */     void setProperty(TopicImpl param1TopicImpl, String param1String) throws IOException;
/*     */     
/*     */     void copyProperty(TopicImpl param1TopicImpl1, TopicImpl param1TopicImpl2);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\TopicImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */