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
/*     */ public final class Multi
/*     */   extends Schema
/*     */ {
/*     */   public Multi() {
/* 124 */     super(new byte[] { 1, 4, 0, 7, 6, -2, -2, 5, 0, 1, 2, 0, 1, -2, 0, 1, 1, -1, 1, -2, 0, 1, 1, -1, 4, -2, -2, 5, 0, 1, 1, -1, 1, -2, 3, 6, -2, 0, 1, 1, -1, 3, -2, -2, 1, 4, 6, -2, 0, 1, 1, -1, 2, -2, 0, 1, 1, -1, 3, -2, -2, 3, 0, 1, 2, 0, -1, 2, -2, 3 }, "com.ibm.disthub.impl.formats.Multi");
/*     */   }
/*     */   
/*     */   public static final class Symbols extends SymbolTable { public Symbols() {
/* 128 */       super(new Object[] { "TopologyStatus_timestamp", "myBrokerName", "myHostName", "myPort", "name", new SymbolTable(new Object[] { "cell", "nextRow", "primaryType" }, new String[][] { { null, "table" },  }, "cellMembership_table"), new SymbolTable(new Object[] { "brokerName", "hostName", "port", new SymbolTable(new Object[] { "cell", "nextRow", "primaryType" }, new String[][] { { null, "table" },  }, "table"), "nextRow", "primaryType", "cellMembership" }new String[][] { { null, "table" }, , { "table" },  }, "neighbors_table"), "VMStatus_timestamp", "hostingBrokerName", new SymbolTable(new Object[] { "fromCell", "toCell", "status", "nextRow", "primaryType" }, new String[][] { { null, "table" },  }, "VMs_table"), "VMNumbering_timestamp", "numberingBrokerName", new SymbolTable(new Object[] { "hostingBrokerName", new SymbolTable(new Object[] { "fromCell", "toCell", "number", "nextRow", "primaryType" }, new String[][] { { null, "table" },  }, "table"), "nextRow", "primaryType", "VMs" }new String[][] { { null, "table" }, , { "table" },  }, "numbers_table"), new SymbolTable(new Object[] { "name", "number", "nextRow", "primaryType" }, new String[][] { { null, "table" },  }, "trees_table"), "primaryType", "clientServerCellName", "cellMembership", "neighbors", "VMs", "numbers", "trees" }new String[][] { { "unknown", "TopologyStatus", "VMStatus", "VMNumbering" }, , { "absent", "present" }, , { "table" }, , { "table" }, , { "table" }, , { "table" }, , { "absent", "table" },  }, null);
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


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\Multi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */