package nvdla

class nv_small
{
  val FEATURE_DATA_TYPE_INT8 = true
  val WEIGHT_DATA_TYPE_INT8 = true
  val WEIGHT_COMPRESSION_ENABLE = false
  val WINOGRAD_ENABLE = false
  val BATCH_ENABLE = false
  val SECONDARY_MEMIF_ENABLE = false
  val SDP_LUT_ENABLE = false
  val SDP_BS_ENABLE = false
  val SDP_BN_ENABLE = false
  val SDP_EW_ENABLE = false
  val BDMA_ENABLE = false
  val RUBIK_ENABLE = false
  val RUBIK_CONTRACT_ENABLE = false
  val RUBIK_RESHAPE_ENABLE = false
  val PDP_ENABLE = true
  val CDP_ENABLE = true
  val RETIMING_ENABLE = false
  val MAC_ATOMIC_C_SIZE = 8 
  val MAC_ATOMIC_K_SIZE = 8
  val MEMORY_ATOMIC_SIZE = 8
  val MAX_BATCH_SIZE = 0
  val CBUF_BANK_NUMBER = 32
  val CBUF_BANK_WIDTH = 8
  val CBUF_BANK_DEPTH = 512
  val SDP_BS_THROUGHPUT = 1
  val SDP_BN_THROUGHPUT = 1
  val SDP_EW_THROUGHPUT = 0
  val PDP_THROUGHPUT = 1
  val CDP_THROUGHPUT = 1
  val PRIMARY_MEMIF_LATENCY = 64
  val SECONDARY_MEMIF_LATENCY = 0
  val PRIMARY_MEMIF_MAX_BURST_LENGTH = 1
  val PRIMARY_MEMIF_WIDTH = 64
  val SECONDARY_MEMIF_MAX_BURST_LENGTH = 0
  val SECONDARY_MEMIF_WIDTH = 0
  val MEM_ADDRESS_WIDTH = 32
  val NUM_DMA_READ_CLIENTS = 7
  val NUM_DMA_WRITE_CLIENTS = 3
}

