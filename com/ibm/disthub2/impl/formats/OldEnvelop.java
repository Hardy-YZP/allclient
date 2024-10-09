/*   */ package com.ibm.disthub2.impl.formats;
/*   */ 
/*   */ public interface OldEnvelop
/*   */ {
/*   */   public static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 6 */   public static final Schema thisSchema = new Schema(new byte[] { 1, 1, 8, -100, -4, -100, 1, -100, 0, 1, 1, 0, -101, 2, -101, 1, -101, -2, -101, 0, 1, 1, 0, 0, 1, 10, 0, 4, 6, 0, 1, 2, 0, 1, -2, 0, 1, 2, 0, 1, 5, 0, 1, 6, 0, 0, 2, -2, -2, 1, 5, 8, 0, 1, 2, 0, 1, -2, 0, 1, 2, 0, 1, 6, 0, 1, 2, 0, 1, -2, 1, 6, 1, 0, 1, 3, 0, -1, 2, -2, 0, 1, 9, 0, 1, 1, 1, 2, 1, 3, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -1, 0, 1, 8, 0, 0, 1, -3, 1, -4, 1, -2, -1, 1, 0, 1, 11, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -4, -1, 2, -2, 0, 1, 11, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -4, 1, -1, 1, -1, 0, 0, 2, 6, 5, 3, 5, 5, 5, 2, 5, 5, 2, 1, 0, 1, 1, -1, 3, -2, -2, -2, 2, 2, -2, 1, 5 }, new Names("com.ibm.gryphonimpl.formats.Envelop", new Names[] { new Names("", new Names[] { new Names("mdt", null), new Names("qopQuery", null), new Names("extendh", new Names[] { new Names("", null) }), new Names("priority", null), new Names("nonStop", null), new Names("topic", null), new Names("extenda", new Names[] { new Names("", null) }), new Names("payload", new Names[] { new Names("unknown", null), new Names("normal", new Names[] { new Names("mid", null), new Names("reply", new Names[] { new Names("absent", null), new Names("present", new Names[] { new Names("", null) }) }), new Names("track", new Names[] { new Names("absent", null), new Names("present", new Names[] { new Names("", null) }) }), new Names("body", new Names[] { new Names("unknown", null), new Names("simplectl", null), new Names("subscribeReq", new Names[] { new Names("subject", null), new Names("query", null) }), new Names("id", new Names[] { new Names("", null) }), new Names("jms", new Names[] { new Names("JMSCorrelationID", new Names[] { new Names("absent", null), new Names("present", new Names[] { new Names("", null) }) }), new Names("JMSTimestamp", new Names[] { new Names("absent", null), new Names("present", new Names[] { new Names("", null) }) }), new Names("JMSType", new Names[] { new Names("absent", null), new Names("present", new Names[] { new Names("", null) }) }), new Names("JMSDeliveryMode", null), new Names("JMSExpiration", null), new Names("JMSRedelivered", null), new Names("properties", new Names[] { new Names("absent", null), new Names("std", new Names[] { new Names("name", null), new Names("value", new Names[] { new Names("unknown", null), new Names("booleanType", new Names[] { new Names("", null) }), new Names("byteType", new Names[] { new Names("", null) }), new Names("shortType", new Names[] { new Names("", null) }), new Names("intType", new Names[] { new Names("", null) }), new Names("longType", new Names[] { new Names("", null) }), new Names("floatType", new Names[] { new Names("", null) }), new Names("doubleType", new Names[] { new Names("", null) }), new Names("stringType", new Names[] { new Names("", null) }) }) }), new Names("schematized", new Names[] { new Names("", null) }) }), new Names("JMSbody", new Names[] { new Names("unknown", null), new Names("Message", null), new Names("ObjectMessage", new Names[] { new Names("", null) }), new Names("BytesMessage", new Names[] { new Names("", null) }), new Names("TextMessage", new Names[] { new Names("", null) }), new Names("StreamMessage", new Names[] { new Names("value", new Names[] { new Names("unknown", null), new Names("booleanType", new Names[] { new Names("", null) }), new Names("byteType", new Names[] { new Names("", null) }), new Names("shortType", new Names[] { new Names("", null) }), new Names("charType", new Names[] { new Names("", null) }), new Names("intType", new Names[] { new Names("", null) }), new Names("longType", new Names[] { new Names("", null) }), new Names("floatType", new Names[] { new Names("", null) }), new Names("doubleType", new Names[] { new Names("", null) }), new Names("stringType", new Names[] { new Names("", null) }), new Names("bytesType", new Names[] { new Names("", null) }) }) }), new Names("MapMessage", new Names[] { new Names("name", null), new Names("value", new Names[] { new Names("unknown", null), new Names("booleanType", new Names[] { new Names("", null) }), new Names("byteType", new Names[] { new Names("", null) }), new Names("shortType", new Names[] { new Names("", null) }), new Names("charType", new Names[] { new Names("", null) }), new Names("intType", new Names[] { new Names("", null) }), new Names("longType", new Names[] { new Names("", null) }), new Names("floatType", new Names[] { new Names("", null) }), new Names("doubleType", new Names[] { new Names("", null) }), new Names("stringType", new Names[] { new Names("", null) }), new Names("bytesType", new Names[] { new Names("", null) }) }) }), new Names("SchematizedJMSMessage", new Names[] { new Names("", null) }) }) }), new Names("SchematizedNormalMessage", new Names[] { new Names("", null) }) }) }), new Names("pingReq", null), new Names("pingReply", null), new Names("error", new Names[] { new Names("id", null), new Names("code", null) }), new Names("connGrant", new Names[] { new Names("server", null), new Names("connection", null), new Names("client", null) }), new Names("connFail", new Names[] { new Names("code", null), new Names("version", null) }), new Names("featureExchange", new Names[] { new Names("query", null), new Names("feature", new Names[] { new Names("featureTable", new Names[] { new Names("featureName", null), new Names("paramName", null), new Names("paramValue", null) }) }) }), new Names("qopUpdate", new Names[] { new Names("qopCode", null), new Names("subject", null) }), new Names("notUnderstood", new Names[] { new Names("version", null) }) }) }) }));
/*   */   public static final long thisId = -6751798346294422662L;
/*   */   
/*   */   public static interface payload {
/*   */     public static interface notUnderstood {
/*   */       public static interface col {
/*   */         public static final int version = 0;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface qopUpdate {
/*   */       public static interface col {
/*   */         public static final int qopCode = 0;
/*   */         public static final int subject = 1;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface featureExchange {
/*   */       public static interface feature {
/*   */         public static interface col {
/*   */           public static final int featureName = 0;
/*   */           public static final int paramName = 1;
/*   */           public static final int paramValue = 2;
/*   */         }
/*   */       }
/*   */       
/*   */       public static interface col {
/*   */         public static final int query = 0;
/*   */         public static final int feature = 1;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface connFail {
/*   */       public static interface col {
/*   */         public static final int code = 0;
/*   */         public static final int version = 1;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface connGrant {
/*   */       public static interface col {
/*   */         public static final int server = 0;
/*   */         public static final int connection = 1;
/*   */         public static final int client = 2;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface error {
/*   */       public static interface col {
/*   */         public static final int id = 0;
/*   */         public static final int code = 1;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface normal {
/*   */       public static interface body {
/*   */         public static interface jms {
/*   */           public static interface JMSbody {
/*   */             public static interface MapMessage {
/*   */               public static interface value {
/*   */                 public static interface is {
/*   */                   public static final int unknown = 0;
/*   */                   public static final int booleanType = 1;
/*   */                   public static final int byteType = 2;
/*   */                   public static final int shortType = 3;
/*   */                   public static final int charType = 4;
/*   */                   public static final int intType = 5;
/*   */                   public static final int longType = 6;
/*   */                   public static final int floatType = 7;
/*   */                   public static final int doubleType = 8;
/*   */                   public static final int stringType = 9;
/*   */                   public static final int bytesType = 10;
/*   */                 }
/*   */               }
/*   */               
/*   */               public static interface col {
/*   */                 public static final int name = 0;
/*   */                 public static final int value = 1;
/*   */               }
/*   */             }
/*   */             
/*   */             public static interface StreamMessage {
/*   */               public static interface value {
/*   */                 public static interface is {
/*   */                   public static final int unknown = 0;
/*   */                   public static final int booleanType = 1;
/*   */                   public static final int byteType = 2;
/*   */                   public static final int shortType = 3;
/*   */                   public static final int charType = 4;
/*   */                   public static final int intType = 5;
/*   */                   public static final int longType = 6;
/*   */                   public static final int floatType = 7;
/*   */                   public static final int doubleType = 8;
/*   */                   public static final int stringType = 9;
/*   */                   public static final int bytesType = 10;
/*   */                 }
/*   */               }
/*   */               
/*   */               public static interface col {
/*   */                 public static final int value = 0;
/*   */               }
/*   */             }
/*   */             
/*   */             public static interface is {
/*   */               public static final int unknown = 0;
/*   */               public static final int Message = 1;
/*   */               public static final int ObjectMessage = 2;
/*   */               public static final int BytesMessage = 3;
/*   */               public static final int TextMessage = 4;
/*   */               public static final int StreamMessage = 5;
/*   */               public static final int MapMessage = 6;
/*   */               public static final int SchematizedJMSMessage = 7;
/*   */             }
/*   */           }
/*   */           
/*   */           public static interface properties {
/*   */             public static interface std {
/*   */               public static interface value {
/*   */                 public static interface is {
/*   */                   public static final int unknown = 0;
/*   */                   public static final int booleanType = 1;
/*   */                   public static final int byteType = 2;
/*   */                   public static final int shortType = 3;
/*   */                   public static final int intType = 4;
/*   */                   public static final int longType = 5;
/*   */                   public static final int floatType = 6;
/*   */                   public static final int doubleType = 7;
/*   */                   public static final int stringType = 8;
/*   */                 }
/*   */               }
/*   */               
/*   */               public static interface col {
/*   */                 public static final int name = 0;
/*   */                 public static final int value = 1;
/*   */               }
/*   */             }
/*   */             
/*   */             public static interface is {
/*   */               public static final int absent = 0;
/*   */               public static final int std = 1;
/*   */               public static final int schematized = 2;
/*   */             }
/*   */           }
/*   */           
/*   */           public static interface JMSType {
/*   */             public static interface is {
/*   */               public static final int absent = 0;
/*   */               public static final int present = 1;
/*   */             }
/*   */           }
/*   */           
/*   */           public static interface JMSTimestamp {
/*   */             public static interface is {
/*   */               public static final int absent = 0;
/*   */               public static final int present = 1;
/*   */             }
/*   */           }
/*   */           
/*   */           public static interface JMSCorrelationID {
/*   */             public static interface is {
/*   */               public static final int absent = 0;
/*   */               public static final int present = 1;
/*   */             }
/*   */           }
/*   */           
/*   */           public static interface col {
/*   */             public static final int JMSCorrelationID = 0;
/*   */             public static final int JMSTimestamp = 1;
/*   */             public static final int JMSType = 2;
/*   */             public static final int JMSDeliveryMode = 3;
/*   */             public static final int JMSExpiration = 4;
/*   */             public static final int JMSRedelivered = 5;
/*   */             public static final int properties = 6;
/*   */             public static final int JMSbody = 7;
/*   */           }
/*   */         }
/*   */         
/*   */         public static interface subscribeReq {
/*   */           public static interface col {
/*   */             public static final int subject = 0;
/*   */             public static final int query = 1;
/*   */           }
/*   */         }
/*   */         
/*   */         public static interface is {
/*   */           public static final int unknown = 0;
/*   */           public static final int simplectl = 1;
/*   */           public static final int subscribeReq = 2;
/*   */           public static final int id = 3;
/*   */           public static final int jms = 4;
/*   */           public static final int SchematizedNormalMessage = 5;
/*   */         }
/*   */       }
/*   */       
/*   */       public static interface track {
/*   */         public static interface is {
/*   */           public static final int absent = 0;
/*   */           public static final int present = 1;
/*   */         }
/*   */       }
/*   */       
/*   */       public static interface reply {
/*   */         public static interface is {
/*   */           public static final int absent = 0;
/*   */           public static final int present = 1;
/*   */         }
/*   */       }
/*   */       
/*   */       public static interface col {
/*   */         public static final int mid = 0;
/*   */         public static final int reply = 1;
/*   */         public static final int track = 2;
/*   */         public static final int body = 3;
/*   */       }
/*   */     }
/*   */     
/*   */     public static interface is {
/*   */       public static final int unknown = 0;
/*   */       public static final int normal = 1;
/*   */       public static final int pingReq = 2;
/*   */       public static final int pingReply = 3;
/*   */       public static final int error = 4;
/*   */       public static final int connGrant = 5;
/*   */       public static final int connFail = 6;
/*   */       public static final int featureExchange = 7;
/*   */       public static final int qopUpdate = 8;
/*   */       public static final int notUnderstood = 9;
/*   */     }
/*   */   }
/*   */   
/*   */   public static interface col {
/*   */     public static final int mdt = 0;
/*   */     public static final int qopQuery = 1;
/*   */     public static final int extendh = 2;
/*   */     public static final int priority = 3;
/*   */     public static final int nonStop = 4;
/*   */     public static final int topic = 5;
/*   */     public static final int extenda = 6;
/*   */     public static final int payload = 7;
/*   */   }
/*   */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\OldEnvelop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */