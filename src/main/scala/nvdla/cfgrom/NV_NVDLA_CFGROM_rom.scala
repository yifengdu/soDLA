package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class NV_NVDLA_CFGROM_rom extends Module {
    val io = IO(new Bundle {
        //general clock
        val nvdla_core_clk = Input(Clock())     

        val reg_rd_data = Output(UInt(32.W))
        val reg_offset = Input(UInt(12.W))
        val reg_wr_data = Input(UInt(32.W))
        val reg_wr_en = Input(Bool())
    }) 
    withClock(io.nvdla_core_clk){

    io.reg_rd_data := MuxLookup(io.reg_offset, "b0".asUInt(32.W), 
    Seq( 
     // Register NVDLA_CFGROM_CFGROM_HW_VERSION_0
     "h0".asUInt(32.W) -> "h10001".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_GLB_DESC_0
     "h4".asUInt(32.W) -> "h1".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CIF_DESC_0 
     "h8".asUInt(32.W) -> "h180002".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CIF_CAP_INCOMPAT_0
     "hc".asUInt(32.W) -> "h0".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CIF_CAP_COMPAT_0
     "h10".asUInt(32.W) -> "h0".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CIF_BASE_WIDTH_0
     "h14".asUInt(32.W) -> "h8".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CIF_BASE_LATENCY_0
     "h18".asUInt(32.W) -> "h32".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CIF_BASE_BURST_LENGTH_MAX_0
     "h1c".asUInt(32.W) -> "h80".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CIF_BASE_MEM_ADDR_WIDTH_0
     "h20".asUInt(32.W) -> "h400".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_DESC_0
     "h24".asUInt(32.W) -> "h340003".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_CAP_INCOMPAT_0
     "h28".asUInt(32.W) -> "h0".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_CAP_COMPAT_0
     "h2c".asUInt(32.W) -> "h10".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_FEATURE_TYPES_0
     "h30".asUInt(32.W) -> "h10".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_WEIGHT_TYPES_0
     "h34".asUInt(32.W) -> "h10".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_ATOMIC_C_0
     "h38".asUInt(32.W) -> "h8".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_ATOMIC_K_0
     "h3c".asUInt(32.W) -> "h8".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_ATOMIC_M_0
     "h40".asUInt(32.W) -> "h8".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_CBUF_BANK_NUM_0
     "h44".asUInt(32.W) -> "h20".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_CBUF_BANK_WIDTH_0
     "h48".asUInt(32.W) -> "h8".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_BASE_CBUF_BANK_DEPTH_0
     "h4c".asUInt(32.W) -> "h200".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_MULTI_BATCH_MAX_0
     "h50".asUInt(32.W) -> "h0".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_IMAGE_IN_FORMATS_PACKED_0
     "h54".asUInt(32.W) -> "hcfff001".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CDMA_IMAGE_IN_FORMATS_SEMI_0
     "h58".asUInt(32.W) -> "h3".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CBUF_DESC_0
     "h5c".asUInt(32.W) -> "h180004".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CBUF_CAP_INCOMPAT_0
     "h60".asUInt(32.W) -> "h0".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CBUF_CAP_COMPAT_0
     "h64".asUInt(32.W) -> "h0".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CBUF_BASE_CBUF_BANK_NUM_0
     "h68".asUInt(32.W) -> "h20".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CBUF_BASE_CBUF_BANK_WIDTH_0
     "h6c".asUInt(32.W) -> "h8".asUInt(32.W), 
     // Register NVDLA_CFGROM_CFGROM_CBUF_BASE_CBUF_BANK_DEPTH_0
     "h70".asUInt(32.W) -> "h200".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CBUF_BASE_CDMA_ID_0
     "h74".asUInt(32.W) -> "h3".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_DESC_0
     "h78".asUInt(32.W) -> "h300005".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_CAP_INCOMPAT_0
     "h7c".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_CAP_COMPAT_0
     "h80".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_FEATURE_TYPES_0
     "h84".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_WEIGHT_TYPES_0
     "h88".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_ATOMIC_C_0
     "h8c".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_ATOMIC_K_0
     "h90".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_ATOMIC_M_0
     "h94".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_CBUF_BANK_NUM_0
     "h98".asUInt(32.W) -> "h20".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_CBUF_BANK_WIDTH_0
     "h9c".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_CBUF_BANK_DEPTH_0
     "ha0".asUInt(32.W) -> "h200".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_BASE_CDMA_ID_0
     "ha4".asUInt(32.W) -> "h3".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CSC_MULTI_BATCH_MAX_0
     "ha8".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_DESC_0
     "hac".asUInt(32.W) -> "h1c0006".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_CAP_INCOMPAT_0
     "hb0".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_CAP_COMPAT_0
     "hb4".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_BASE_FEATURE_TYPES_0
     "hb8".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_BASE_WEIGHT_TYPES_0
     "hbc".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_BASE_ATOMIC_C_0
     "hc0".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_BASE_ATOMIC_K_0
     "hc4".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_A_BASE_CDMA_ID_0
     "hc8".asUInt(32.W) -> "h3".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_DESC_0
     "hcc".asUInt(32.W) -> "h1c0006".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_CAP_INCOMPAT_0
     "hd0".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_CAP_COMPAT_0
     "hd4".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_CAP_COMPAT_0
     "hd8".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_BASE_WEIGHT_TYPES_0
     "hdc".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_BASE_ATOMIC_C_0
     "he0".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_BASE_ATOMIC_K_0
     "he4".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CMAC_B_BASE_ATOMIC_K_0
     "he8".asUInt(32.W) -> "h3".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_DESC_0
     "hec".asUInt(32.W) -> "h200007".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_DESC_0
     "hf0".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_CAP_COMPAT_0
     "hf4".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_BASE_FEATURE_TYPES_0
     "hf8".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_BASE_WEIGHT_TYPES_0
     "hfc".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_BASE_ATOMIC_C_0
     "h100".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_BASE_ATOMIC_K_0
     "h104".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_BASE_ATOMIC_K_0
     "h108".asUInt(32.W) -> "h3".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_MULTI_BATCH_MAX_0
     "h10c".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CACC_MULTI_BATCH_MAX_0
     "h110".asUInt(32.W) -> "he0008".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_RDMA_CAP_INCOMPAT_0
     "h114".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_RDMA_CAP_COMPAT_0
     "h118".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_RDMA_BASE_ATOMIC_M_0
     "h11c".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_RDMA_BASE_SDP_ID_0
     "h120".asUInt(32.W) -> "h9".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_DESC_0
     "h124".asUInt(32.W) -> "h200009".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_CAP_INCOMPAT_0
     "h128".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_CAP_COMPAT_0
     "h12c".asUInt(32.W) -> "h18".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_BASE_FEATURE_TYPES_0
     "h130".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_BASE_WEIGHT_TYPES_0
     "h134".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_BASE_CDMA_ID_0
     "h138".asUInt(32.W) -> "h3".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_MULTI_BATCH_MAX_0
     "h13c".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_BS_THROUGHPUT_0
     "h140".asUInt(32.W) -> "h1".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_BN_THROUGHPUT_0
     "h144".asUInt(32.W) -> "h1".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_SDP_EW_THROUGHPUT_0
     "h148".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_RDMA_DESC_0
     "h14c".asUInt(32.W) -> "he000a".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_RDMA_CAP_INCOMPAT_0
     "h150".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_RDMA_CAP_COMPAT_0
     "h154".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_RDMA_BASE_ATOMIC_M_0
     "h158".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_RDMA_BASE_PDP_ID_0
     "h15c".asUInt(32.W) -> "hb".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_DESC_0
     "h160".asUInt(32.W) -> "h10000b".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_CAP_INCOMPAT_0
     "h164".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_CAP_COMPAT_0
     "h168".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_BASE_FEATURE_TYPES_0
     "h16c".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_PDP_BASE_THROUGHPUT_0
     "h170".asUInt(32.W) -> "h1".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_RDMA_DESC_0
     "h174".asUInt(32.W) -> "he000c".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_RDMA_CAP_INCOMPAT_0
     "h178".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_RDMA_CAP_COMPAT_0
     "h17c".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_RDMA_BASE_ATOMIC_M_0
     "h180".asUInt(32.W) -> "h8".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_RDMA_BASE_CDP_ID_0
     "h184".asUInt(32.W) -> "hd".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_DESC_0
     "h188".asUInt(32.W) -> "h10000d".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_CAP_INCOMPAT_0
     "h18c".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_CAP_COMPAT_0
     "h190".asUInt(32.W) -> "h0".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_BASE_FEATURE_TYPES_0
     "h194".asUInt(32.W) -> "h10".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_CDP_BASE_THROUGHPUT_0
     "h198".asUInt(32.W) -> "h1".asUInt(32.W),
     // Register NVDLA_CFGROM_CFGROM_END_OF_LIST_0
     "h19c".asUInt(32.W) -> "h0".asUInt(32.W)     
    ))
    
  }}