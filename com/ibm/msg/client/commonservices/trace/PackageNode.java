/*     */ package com.ibm.msg.client.commonservices.trace;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PackageNode
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6648153724426476290L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/PackageNode.java";
/*     */   private String name;
/*     */   private boolean included;
/*     */   private Map<String, PackageNode> children;
/*     */   
/*     */   public PackageNode(String name, PackageNode parent) {
/*  50 */     this.name = name;
/*  51 */     this.children = new TreeMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIncluded(boolean included) {
/*  58 */     this.included = included;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIncluded() {
/*  66 */     return this.included;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChild(PackageNode newChild) {
/*  77 */     this.children.put(newChild.name, newChild);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyChildren() {
/*  86 */     for (PackageNode child : this.children.values())
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  91 */       child.destroyChildren();
/*     */     }
/*     */ 
/*     */     
/*  95 */     this.children.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PackageNode getChild(String childName) {
/* 105 */     PackageNode child = this.children.get(childName);
/* 106 */     if (null == child) {
/* 107 */       return null;
/*     */     }
/* 109 */     return child;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 116 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PackageNode> getChildren() {
/* 123 */     return this.children.values();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\PackageNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */