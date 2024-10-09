/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Multi_1_1
/*     */   extends Schema
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2003 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public Multi_1_1() {
/* 126 */     super(new byte[] { 1, 4, 0, 7, 6, -2, -2, 5, 0, 1, 2, 0, 1, -2, 0, 1, 1, -1, 1, -2, 0, 1, 1, -1, 4, -2, -2, 5, 0, 1, 1, -1, 1, -2, 3, 6, -2, 0, 1, 1, -1, 3, -2, -2, 1, 4, 6, -2, 0, 1, 1, -1, 2, -2, 0, 1, 1, -1, 3, -2, -2, 3, 0, 1, 2, 0, -1, 2, -2, 3 }, "com.ibm.disthub2.impl.formats.Multi");
/*     */   }
/*     */   
/*     */   public static final class Namer { public static void setNames(Schema schema) {
/* 130 */       schema.setNames(new Names("com.ibm.disthub2.impl.formats.Multi", new Names[] { new Names("unknown", null), new Names("TopologyStatus", new Names[] { new Names("timestamp", null), new Names("myBrokerName", null), new Names("myHostName", null), new Names("myPort", null), new Names("clientServerCellName", new Names[] { new Names("absent", null), new Names("present", new Names[] { new Names("name", null) }) }), new Names("cellMembership", new Names[] { new Names("table", new Names[] { new Names("cell", null) }) }), new Names("neighbors", new Names[] { new Names("table", new Names[] { new Names("brokerName", null), new Names("hostName", null), new Names("port", null), new Names("cellMembership", new Names[] { new Names("table", new Names[] { new Names("cell", null) }) }) }) }) }), new Names("VMStatus", new Names[] { new Names("timestamp", null), new Names("hostingBrokerName", null), new Names("VMs", new Names[] { new Names("table", new Names[] { new Names("fromCell", null), new Names("toCell", null), new Names("status", null) }) }) }), new Names("VMNumbering", new Names[] { new Names("timestamp", null), new Names("numberingBrokerName", null), new Names("numbers", new Names[] { new Names("table", new Names[] { new Names("hostingBrokerName", null), new Names("VMs", new Names[] { new Names("table", new Names[] { new Names("fromCell", null), new Names("toCell", null), new Names("number", null) }) }) }) }), new Names("trees", new Names[] { new Names("absent", null), new Names("table", new Names[] { new Names("name", null), new Names("number", null) }) }) }) }));
/*     */     } }
/*     */ 
/*     */   
/*     */   public static interface Constants {
/*     */     public static final int TopologyStatus_timestamp = 0;
/*     */     public static final int myBrokerName = 1;
/*     */     public static final int TopologyStatus_myBrokerName = 1;
/*     */     public static final int myHostName = 2;
/*     */     public static final int TopologyStatus_myHostName = 2;
/*     */     public static final int myPort = 3;
/*     */     public static final int TopologyStatus_myPort = 3;
/*     */     public static final int name = 4;
/*     */     public static final int present_name = 4;
/*     */     public static final int clientServerCellName_present_name = 4;
/*     */     public static final int TopologyStatus_clientServerCellName_present_name = 4;
/*     */     public static final int cellMembership_table = 5;
/*     */     public static final int TopologyStatus_cellMembership_table = 5;
/*     */     public static final int neighbors_table = 6;
/*     */     public static final int TopologyStatus_neighbors_table = 6;
/*     */     public static final int VMStatus_timestamp = 7;
/*     */     public static final int hostingBrokerName = 8;
/*     */     public static final int VMStatus_hostingBrokerName = 8;
/*     */     public static final int VMs_table = 9;
/*     */     public static final int VMStatus_VMs_table = 9;
/*     */     public static final int VMNumbering_timestamp = 10;
/*     */     public static final int numberingBrokerName = 11;
/*     */     public static final int VMNumbering_numberingBrokerName = 11;
/*     */     public static final int numbers_table = 12;
/*     */     public static final int VMNumbering_numbers_table = 12;
/*     */     public static final int trees_table = 13;
/*     */     public static final int VMNumbering_trees_table = 13;
/*     */     public static final int primaryType = 14;
/*     */     public static final int clientServerCellName = 15;
/*     */     public static final int TopologyStatus_clientServerCellName = 15;
/*     */     public static final int cellMembership = 16;
/*     */     public static final int TopologyStatus_cellMembership = 16;
/*     */     public static final int neighbors = 17;
/*     */     public static final int TopologyStatus_neighbors = 17;
/*     */     public static final int VMs = 18;
/*     */     public static final int VMStatus_VMs = 18;
/*     */     public static final int numbers = 19;
/*     */     public static final int VMNumbering_numbers = 19;
/*     */     public static final int trees = 20;
/*     */     public static final int VMNumbering_trees = 20;
/*     */     public static final int primaryType_is_unknown = 0;
/*     */     public static final int primaryType_is_TopologyStatus = 1;
/*     */     public static final int primaryType_is_VMStatus = 2;
/*     */     public static final int primaryType_is_VMNumbering = 3;
/*     */     public static final int clientServerCellName_is_absent = 0;
/*     */     public static final int TopologyStatus_clientServerCellName_is_absent = 0;
/*     */     public static final int clientServerCellName_is_present = 1;
/*     */     public static final int TopologyStatus_clientServerCellName_is_present = 1;
/*     */     public static final int cellMembership_is_table = 0;
/*     */     public static final int TopologyStatus_cellMembership_is_table = 0;
/*     */     public static final int neighbors_is_table = 0;
/*     */     public static final int TopologyStatus_neighbors_is_table = 0;
/*     */     public static final int VMs_is_table = 0;
/*     */     public static final int VMStatus_VMs_is_table = 0;
/*     */     public static final int numbers_is_table = 0;
/*     */     public static final int VMNumbering_numbers_is_table = 0;
/*     */     public static final int trees_is_absent = 0;
/*     */     public static final int VMNumbering_trees_is_absent = 0;
/*     */     public static final int trees_is_table = 1;
/*     */     public static final int VMNumbering_trees_is_table = 1;
/*     */     
/*     */     public static interface trees_table_type {
/*     */       public static final int name = 0;
/*     */       public static final int number = 1;
/*     */       public static final int nextRow = 2;
/*     */       public static final int primaryType = 3;
/*     */       public static final int primaryType_is_table = 1;
/*     */     }
/*     */     
/*     */     public static interface numbers_table_type {
/*     */       public static final int hostingBrokerName = 0;
/*     */       public static final int table = 1;
/*     */       public static final int VMs_table = 1;
/*     */       public static final int nextRow = 2;
/*     */       public static final int primaryType = 3;
/*     */       public static final int VMs = 4;
/*     */       public static final int primaryType_is_table = 1;
/*     */       public static final int VMs_is_table = 0;
/*     */       
/*     */       public static interface table_type {
/*     */         public static final int fromCell = 0;
/*     */         public static final int toCell = 1;
/*     */         public static final int number = 2;
/*     */         public static final int nextRow = 3;
/*     */         public static final int primaryType = 4;
/*     */         public static final int primaryType_is_table = 1;
/*     */       }
/*     */     }
/*     */     
/*     */     public static interface VMs_table_type {
/*     */       public static final int fromCell = 0;
/*     */       public static final int toCell = 1;
/*     */       public static final int status = 2;
/*     */       public static final int nextRow = 3;
/*     */       public static final int primaryType = 4;
/*     */       public static final int primaryType_is_table = 1;
/*     */     }
/*     */     
/*     */     public static interface neighbors_table_type {
/*     */       public static final int brokerName = 0;
/*     */       public static final int hostName = 1;
/*     */       public static final int port = 2;
/*     */       public static final int table = 3;
/*     */       public static final int cellMembership_table = 3;
/*     */       public static final int nextRow = 4;
/*     */       public static final int primaryType = 5;
/*     */       public static final int cellMembership = 6;
/*     */       public static final int primaryType_is_table = 1;
/*     */       public static final int cellMembership_is_table = 0;
/*     */       
/*     */       public static interface table_type {
/*     */         public static final int cell = 0;
/*     */         public static final int nextRow = 1;
/*     */         public static final int primaryType = 2;
/*     */         public static final int primaryType_is_table = 1;
/*     */       }
/*     */     }
/*     */     
/*     */     public static interface cellMembership_table_type {
/*     */       public static final int cell = 0;
/*     */       public static final int nextRow = 1;
/*     */       public static final int primaryType = 2;
/*     */       public static final int primaryType_is_table = 1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\Multi_1_1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */