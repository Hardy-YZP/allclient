/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class QMTree
/*     */   extends AbstractTree
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/QMTree.java";
/*     */   static final long serialVersionUID = 6503821404684521956L;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.QMTree", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/QMTree.java");
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
/*  69 */   private static final int[] ASSOCIATIONS = new int[] { 0, 8, 32 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   QMTree() {
/*  76 */     super(new TreeElt(null, MQQueueManagerFactory.getInstance()));
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.QMTree", "<init>()");
/*     */     }
/*     */     
/*  81 */     for (int i = 0; i < ASSOCIATIONS.length; i++) {
/*     */       
/*     */       try {
/*  84 */         super.addChild(root(), Integer.valueOf(ASSOCIATIONS[i]));
/*     */       }
/*  86 */       catch (QMTreeException te) {
/*  87 */         if (Trace.isOn) {
/*  88 */           Trace.catchBlock(this, "com.ibm.mq.QMTree", "<init>()", te);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.mq.QMTree", "<init>()");
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
/*     */   TreeElt getAssociateBranch(int scope) {
/* 107 */     if (Trace.isOn)
/* 108 */       Trace.entry(this, "com.ibm.mq.QMTree", "getAssociateBranch(int)", new Object[] {
/* 109 */             Integer.valueOf(scope)
/*     */           }); 
/* 111 */     TreeElt traceRet1 = getChild(root(), Integer.valueOf(scope));
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.QMTree", "getAssociateBranch(int)", traceRet1);
/*     */     }
/* 116 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addChild(TreeElt elt, Object o) throws QMTreeException {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.QMTree", "addChild(TreeElt,Object)", new Object[] { elt, o });
/*     */     }
/*     */     
/* 127 */     if (elt != null && elt.isRoot()) {
/* 128 */       QMTreeException traceRet1 = new QMTreeException("invalid addition");
/*     */       
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.throwing(this, "com.ibm.mq.QMTree", "addChild(TreeElt,Object)", traceRet1);
/*     */       }
/* 133 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 137 */     super.addChild(elt, o);
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.QMTree", "addChild(TreeElt,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeChild(TreeElt elt, Object o) throws QMTreeException {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.QMTree", "removeChild(TreeElt,Object)", new Object[] { elt, o });
/*     */     }
/*     */ 
/*     */     
/* 152 */     if (elt.isRoot()) {
/* 153 */       QMTreeException traceRet1 = new QMTreeException("invalid deletion");
/*     */       
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.throwing(this, "com.ibm.mq.QMTree", "removeChild(TreeElt,Object)", traceRet1);
/*     */       }
/* 158 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 162 */     super.removeChild(elt, o);
/*     */     
/* 164 */     if (Trace.isOn)
/* 165 */       Trace.exit(this, "com.ibm.mq.QMTree", "removeChild(TreeElt,Object)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\QMTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */