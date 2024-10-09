/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQDestination;
/*     */ import com.ibm.mq.jms.MQTopic;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TopicBAO
/*     */   extends DestBAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/TopicBAO.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.TopicBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/TopicBAO.java");
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
/*  64 */   static final AP[] PROPERTIES = new AP[] { new APTOP(), new APBDSUB(), new APBVER(), new APTBPUBQ(), new APWCFMT(), new APTBQM(), new APTMCAST(), new APCCDSUB() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicBAO() {
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.jms.admin.TopicBAO", "<init>()");
/*     */     }
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.jms.admin.TopicBAO", "<init>()");
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
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data(this, "com.ibm.mq.jms.admin.TopicBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     this.d = (MQDestination)new MQTopic();
/*     */ 
/*     */ 
/*     */     
/* 111 */     _setFromProperties(props);
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
/*     */   public List<String> supportedProperties() {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.jms.admin.TopicBAO", "supportedProperties()");
/*     */     }
/* 126 */     List<String> result = super.supportedProperties();
/* 127 */     for (AP prop : PROPERTIES) {
/* 128 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.jms.admin.TopicBAO", "supportedProperties()", result);
/*     */     }
/* 134 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getProperties() {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.mq.jms.admin.TopicBAO", "getProperties()");
/*     */     }
/*     */ 
/*     */     
/* 150 */     if (this.d == null) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.exit(this, "com.ibm.mq.jms.admin.TopicBAO", "getProperties()", null, 1);
/*     */       }
/* 154 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 158 */     MQTopic t = (MQTopic)this.d;
/*     */     
/* 160 */     Map<String, Object> props = super.getProperties();
/*     */     
/* 162 */     for (AP prop : PROPERTIES) {
/*     */       try {
/* 164 */         prop.setPropertyFromObject(props, t);
/*     */       }
/* 166 */       catch (JMSException e) {
/* 167 */         if (Trace.isOn) {
/* 168 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.TopicBAO", "getProperties()", (Throwable)e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.jms.admin.TopicBAO", "getProperties()", props, 2);
/*     */     }
/* 177 */     return props;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.mq.jms.admin.TopicBAO", "getType()", "getter", 
/* 190 */           Integer.valueOf(3));
/*     */     }
/* 192 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromObject(Object obj) throws BAOException {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.mq.jms.admin.TopicBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 206 */     if (obj instanceof MQTopic) {
/* 207 */       this.d = (MQDestination)obj;
/*     */     } else {
/*     */       
/* 210 */       BAOException be = new BAOException(10, null, null);
/* 211 */       if (Trace.isOn) {
/* 212 */         Trace.throwing(this, "com.ibm.mq.jms.admin.TopicBAO", "setFromObject(Object)", be);
/*     */       }
/* 214 */       throw be;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String name() {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.mq.jms.admin.TopicBAO", "name()");
/*     */     }
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jms.admin.TopicBAO", "name()", "T");
/*     */     }
/* 231 */     return "T";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.admin.TopicBAO", "_setFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 247 */       super._setFromProperties(props);
/*     */       
/* 249 */       for (AP prop : PROPERTIES) {
/* 250 */         prop.setObjectFromProperty(this.d, props);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.TopicBAO", "_setFromProperties(Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 260 */     if (Trace.isOn)
/* 261 */       Trace.exit(this, "com.ibm.mq.jms.admin.TopicBAO", "_setFromProperties(Map<String , Object>)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\TopicBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */