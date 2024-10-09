package com.ibm.disthub2.spi;

public interface ServerLogConstants extends ClientLogConstants {
  public static final long LOGM_BROKER = 4503599627370496L;
  
  public static final long LOGM_GD = 36028797018963968L;
  
  public static final long LOGM_MC = 2251799813685248L;
  
  public static final long LOG_MSG_NOQOP = 18679472239127678L;
  
  public static final long LOG_MSG_SEND = 679891382422820L;
  
  public static final long LOG_MSG_RCV = 676821384706042L;
  
  public static final long LOG_CONN_FTRUPD = 416176008343532L;
  
  public static final long LOG_MSG_BADCTRL = 657922455867644L;
  
  public static final long LOG_MSG_QTRIMMED = 644222473842217L;
  
  public static final long LOG_SS_AUTHFAIL = 1216766389645062L;
  
  public static final long LOG_SS_AUTHTMOUT = 1220715032970157L;
  
  public static final long LOG_SS_SYNCSENT = 396466101594970L;
  
  public static final long LOG_SS_SYNCFAIL = 370769937016335L;
  
  public static final long LOG_SS_PRSBTS = 376266248609477L;
  
  public static final long LOG_CONN_NONBIO = 4880187244200978L;
  
  public static final long LOG_CNFERROR_INVTQOP = 18087799435185070L;
  
  public static final long LOG_IS_DUPPROC = 4581747044273572L;
  
  public static final long LOG_MSG_BADSEC = 2060364215354181L;
  
  public static final long LOG_MSG_NOPUB = 2046188979059273L;
  
  public static final long LOG_CONN_CLOSED = 397392213033586L;
  
  public static final long LOG_CONN_AUTH = 1533574296984321L;
  
  public static final long LOG_CONN_BADAUTH = 1509968728110634L;
  
  public static final long LOG_CONN_CULLED = 1498117070033566L;
  
  public static final long LOG_CONN_MAXEXCEEDED = 385021242250414L;
  
  public static final long LOG_CNFWARN_LOWBQ = 18107685376966672L;
  
  public static final long LOG_CNFWARN_LOWCQ = 18119763526812059L;
  
  public static final long LOG_CNFWARN_HIGHCOMP = 18097007054184663L;
  
  public static final long LOG_CNFWARN_NOBTS = 18118323651847099L;
  
  public static final long LOG_CNFWARN_NOSOCKSH = 18107128746027134L;
  
  public static final long LOG_CNF_BVERSION = 22646328011781654L;
  
  public static final long LOG_EP_STATTHDFAIL = 4578043510963262L;
  
  public static final long LOG_SNMP_STARTFAIL = -205699312121401L;
  
  public static final long LOG_SNMP_BADPOP = -176846420111726L;
  
  public static final long LOG_SNMP_BADMIBINST = -196072690916249L;
  
  public static final long LOG_SNMP_BADFACTINST = -196204552393911L;
  
  public static final long LOG_SNMP_STARTING = -157801791340757L;
  
  public static final long LOG_SNMP_ERRREC = -194111370989620L;
  
  public static final long LOG_SNMP_NOREC = -176152026894826L;
  
  public static final long LOG_SNMP_COMMIT = -144449542106158L;
  
  public static final long LOG_SNMP_DENYCHG = -169140501219737L;
  
  public static final long LOG_SNMP_BADGET = -197294687025354L;
  
  public static final long LOG_SNMP_TRAPINT = -186437441473790L;
  
  public static final long LOG_SNMP_TRAPSENT = -165619640170445L;
  
  public static final long LOG_SNMP_BADPOP2 = -194188825543276L;
  
  public static final long LOG_SNMP_BADTRAP = -186212052093382L;
  
  public static final long LOG_SNMP_BADSTATUP = -196362002998550L;
  
  public static final long LOG_CONN_DENY = 1533252160156182L;
  
  public static final long LOG_CONN_ACCEPT = 1531300104489930L;
  
  public static final long LOG_CONN_REAPED = 1504615748829543L;
  
  public static final long LOG_NB_CLSERRRD = 382345532260097L;
  
  public static final long LOG_NB_CLSERRWR = 360030376947179L;
  
  public static final long LOG_NB_LOOPBACKERR = 4587825642334689L;
  
  public static final long LOG_NB_DEREGERR = 357069936169961L;
  
  public static final long LOG_NB_CLSERR = 378938975477191L;
  
  public static final long LOG_DPI_BADLIB = -200321503144091L;
  
  public static final long LOG_DPI_UNXEXC = -199190144612255L;
  
  public static final long LOG_DPI_UNXRET = -201829301214574L;
  
  public static final long LOG_EMB_LOCKERR = 13599467049345320L;
  
  public static final long LOG_EMB_RESETERR = 13583952876367446L;
  
  public static final long LOG_BC_SVCFAIL = 13594763296655673L;
  
  public static final long LOG_BCTL_HNDLERR = 13603158939604059L;
  
  public static final long LOG_EP_STATSCHDFAIL = 4559437630447154L;
  
  public static final long LOG_SS_BOGUSNAME = 1211170709550215L;
  
  public static final long LOG_SS_INIT_FAIL = 1198984009034471L;
  
  public static final long LOG_SS_CONNECTED = 1501844182544114L;
  
  public static final long LOG_SS_CONN_REAPED = 1478167198479173L;
  
  public static final long LOG_SS_DISCONNECT = 1487849823530694L;
  
  public static final long LOG_EP_STATSBDHNDL = 4568509580094127L;
  
  public static final long LOG_GD_STREAMFAIL = 36068823679781888L;
  
  public static final long LOG_GD_STREAMNOALLOC = 36077789466058344L;
  
  public static final long LOG_GD_NOSTREAMS = 36080264002502633L;
  
  public static final long LOG_GD_BADCONVERT = 36089036068001441L;
  
  public static final long LOG_TOPO_CYCLE = 18100518014756796L;
  
  public static final long LOG_TOPO_BADCS = 18092615690179916L;
  
  public static final long LOG_LVM_PTL_RCVR = 36111517289685996L;
  
  public static final long LOG_GD_PSTORE_ERROR = 36081044581347445L;
  
  public static final long LOG_DMS_PFS_ERROR = 36081408144793951L;
  
  public static final long LOG_BIND_ERROR = 321058234763810L;
  
  public static final long LOG_MC_INTERFACE_BINDING = 2388818522734017L;
  
  public static final long LOG_MC_PGM_AUTH = 2350542147908317L;
  
  public static final long LOG_MC_PGM_LIB_NOT_FOUND = 2303212804132979L;
  
  public static final long LOG_MC_L_W_GENERAL_WARNING = 2334943794690829L;
  
  public static final long LOG_MC_L_E_GENERAL_ERROR = 2308758158581548L;
  
  public static final long LOG_MC_L_E_CONFIG_ENTRY = 2305194709790034L;
  
  public static final long LOG_MC_L_E_BAD_PARAMETER = 2292369615570551L;
  
  public static final long LOG_MC_L_E_SOCKET_CREATE = 2310225280488257L;
  
  public static final long LOG_MC_L_E_SET_MCAST_INTERF = 2300095124396990L;
  
  public static final long LOG_MC_L_E_INTERRUPT = 2311626914737891L;
  
  public static final long LOG_MC_L_E_TTL = 2297752267649092L;
  
  public static final long LOG_MC_L_E_MCAST_JOIN = 2296733767387169L;
  
  public static final long LOG_MC_L_E_MCAST_LEAVE = 2303877074430890L;
  
  public static final long LOG_MC_L_E_MEMORY_ALLOCATE = 2289061512926162L;
  
  public static final long LOG_MC_L_E_UNSUPPORTED_ENCODING = 2302512977605165L;
  
  public static final long LOG_MC_L_E_UNRESOLVED_ADDRESS = 2299288162915372L;
  
  public static final long LOG_MC_L_E_SOCKET_BUFFER_SIZE = 2308738514714688L;
  
  public static final long LOG_MC_L_E_INSUFF_PACKET_BUFFER = 2316027047501600L;
  
  public static final long LOG_MC_L_E_SERVICE_TERMINATION = 2318782398047272L;
  
  public static final long LOG_MC_L_E_PACKET_SEND = 2306030027493983L;
  
  public static final long LOG_MC_L_E_CLOCK_PROBLEM = 2287845542367571L;
  
  public static final long LOG_MC_L_E_SOCKET_CONFIG_PROBLEM = 2321948288617492L;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\ServerLogConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */