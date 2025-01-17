package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class NV_NVDLA_SDP_RDMA_REG_dual extends Module{
    val io = IO(new Bundle{
        // clk
        val nvdla_core_clk = Input(Clock())

        //Register control interface
        val reg_rd_data = Output(UInt(32.W))
        val reg_offset = Input(UInt(12.W))
        val reg_wr_data = Input(UInt(32.W))//(UNUSED_DEC)
        val reg_wr_en = Input(Bool())

        //Writable register flop/trigger outputs
        val bn_base_addr_high = Output(UInt(32.W))
        val bn_base_addr_low = Output(UInt(32.W))
        val bn_batch_stride = Output(UInt(32.W))
        val bn_line_stride = Output(UInt(32.W))
        val bn_surface_stride = Output(UInt(32.W))
        val brdma_data_mode = Output(Bool())
        val brdma_data_size = Output(Bool())
        val brdma_data_use = Output(UInt(2.W))
        val brdma_disable = Output(Bool())
        val brdma_ram_type = Output(Bool())
        val bs_base_addr_high = Output(UInt(32.W))
        val bs_base_addr_low = Output(UInt(32.W))
        val bs_batch_stride = Output(UInt(32.W))
        val bs_line_stride = Output(UInt(32.W))
        val bs_surface_stride = Output(UInt(32.W))
        val channel = Output(UInt(13.W))
        val height = Output(UInt(13.W))
        val width_a = Output(UInt(13.W))
        val erdma_data_mode = Output(Bool())
        val erdma_data_size = Output(Bool())
        val erdma_data_use = Output(UInt(2.W))
        val erdma_disable = Output(Bool())
        val erdma_ram_type = Output(Bool())
        val ew_base_addr_high = Output(UInt(32.W))
        val ew_base_addr_low = Output(UInt(32.W))
        val ew_batch_stride = Output(UInt(32.W))
        val ew_line_stride = Output(UInt(32.W))
        val ew_surface_stride = Output(UInt(32.W))
        val batch_number = Output(UInt(5.W))
        val flying_mode = Output(Bool())
        val in_precision = Output(UInt(2.W))
        val out_precision = Output(UInt(2.W))
        val proc_precision = Output(UInt(2.W))
        val winograd = Output(Bool())
        val nrdma_data_mode = Output(Bool())
        val nrdma_data_size = Output(Bool())
        val nrdma_data_use = Output(UInt(2.W))
        val nrdma_disable = Output(Bool())
        val nrdma_ram_type = Output(Bool())
        val op_en_trigger = Output(Bool())
        val perf_dma_en = Output(Bool())
        val perf_nan_inf_count_en = Output(Bool())
        val src_base_addr_high = Output(UInt(32.W))
        val src_base_addr_low = Output(UInt(32.W))
        val src_ram_type = Output(Bool())
        val src_line_stride = Output(UInt(32.W))
        val src_surface_stride = Output(UInt(32.W))

        //Read-only register inputs
        val op_en = Input(Bool())
        val brdma_stall = Input(UInt(32.W))
        val erdma_stall = Input(UInt(32.W))
        val mrdma_stall = Input(UInt(32.W))
        val nrdma_stall = Input(UInt(32.W))
        val status_inf_input_num = Input(UInt(32.W))
        val status_nan_input_num = Input(UInt(32.W)) 
    })
    
    //      ┌─┐       ┌─┐
    //   ┌──┘ ┴───────┘ ┴──┐
    //   │                 │
    //   │       ───       │
    //   │  ─┬┘       └┬─  │
    //   │                 │
    //   │       ─┴─       │
    //   │                 │
    //   └───┐         ┌───┘
    //       │         │
    //       │         │
    //       │         │
    //       │         └──────────────┐
    //       │                        │
    //       │                        ├─┐
    //       │                        ┌─┘    
    //       │                        │
    //       └─┐  ┐  ┌───────┬──┐  ┌──┘         
    //         │ ─┤ ─┤       │ ─┤ ─┤         
    //         └──┴──┘       └──┴──┘ 
    withClock(io.nvdla_core_clk){

    // Address decode
    val nvdla_sdp_rdma_d_bn_base_addr_high_0_wren = (io.reg_offset === "h48".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bn_base_addr_low_0_wren = (io.reg_offset ===  "h44".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bn_batch_stride_0_wren = (io.reg_offset ===  "h54".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bn_line_stride_0_wren = (io.reg_offset ===  "h4c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bn_surface_stride_0_wren = (io.reg_offset === "h50".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_brdma_cfg_0_wren = (io.reg_offset === "h28".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bs_base_addr_high_0_wren = (io.reg_offset === "h30".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bs_base_addr_low_0_wren = (io.reg_offset === "h2c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bs_batch_stride_0_wren = (io.reg_offset === "h3c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bs_line_stride_0_wren = (io.reg_offset === "h34".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_bs_surface_stride_0_wren = (io.reg_offset === "h38".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_data_cube_channel_0_wren = (io.reg_offset === "h14".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_data_cube_height_0_wren = (io.reg_offset === "h10".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_data_cube_width_a_0_wren = (io.reg_offset === "h0c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_erdma_cfg_0_wren = (io.reg_offset === "h58".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_ew_base_addr_high_0_wren = (io.reg_offset === "h60".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_ew_base_addr_low_0_wren = (io.reg_offset === "h5c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_ew_batch_stride_0_wren = (io.reg_offset === "h6c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_ew_line_stride_0_wren = (io.reg_offset === "h64".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_ew_surface_stride_0_wren = (io.reg_offset === "h68".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_feature_mode_cfg_0_wren = (io.reg_offset === "h70".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_nrdma_cfg_0_wren = (io.reg_offset === "h40".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_op_enable_0_wren = (io.reg_offset === "h08".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_perf_brdma_read_stall_0_wren = (io.reg_offset === "h88".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_perf_enable_0_wren = (io.reg_offset === "h80".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_perf_erdma_read_stall_0_wren = (io.reg_offset === "h90".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_perf_mrdma_read_stall_0_wren = (io.reg_offset === "h84".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_perf_nrdma_read_stall_0_wren = (io.reg_offset === "h8c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_src_base_addr_high_0_wren = (io.reg_offset === "h1c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_src_base_addr_low_0_wren = (io.reg_offset === "h18".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_src_dma_cfg_0_wren = (io.reg_offset === "h74".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_src_line_stride_0_wren = (io.reg_offset === "h20".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_src_surface_stride_0_wren = (io.reg_offset === "h24".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_status_inf_input_num_0_wren = (io.reg_offset === "h7c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_sdp_rdma_d_status_nan_input_num_0_wren = (io.reg_offset === "h78".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)

    val nvdla_sdp_rdma_d_bn_base_addr_high_0_out = io.bn_base_addr_high
    val nvdla_sdp_rdma_d_bn_base_addr_low_0_out = io.bn_base_addr_low
    val nvdla_sdp_rdma_d_bn_batch_stride_0_out = io.bn_batch_stride
    val nvdla_sdp_rdma_d_bn_line_stride_0_out = io.bn_line_stride
    val nvdla_sdp_rdma_d_bn_surface_stride_0_out =  io.bn_surface_stride
    val nvdla_sdp_rdma_d_brdma_cfg_0_out = Cat("b0".asUInt(26.W), io.brdma_ram_type, io.brdma_data_mode, io.brdma_data_size, io.brdma_data_use, io.brdma_disable)
    val nvdla_sdp_rdma_d_bs_base_addr_high_0_out = io.bs_base_addr_high
    val nvdla_sdp_rdma_d_bs_base_addr_low_0_out = io.bs_base_addr_low
    val nvdla_sdp_rdma_d_bs_batch_stride_0_out = io.bs_batch_stride
    val nvdla_sdp_rdma_d_bs_line_stride_0_out = io.bs_line_stride
    val nvdla_sdp_rdma_d_bs_surface_stride_0_out = io.bs_surface_stride
    val nvdla_sdp_rdma_d_data_cube_channel_0_out = Cat("b0".asUInt(19.W), io.channel)
    val nvdla_sdp_rdma_d_data_cube_height_0_out = Cat("b0".asUInt(19.W), io.height)
    val nvdla_sdp_rdma_d_data_cube_width_a_0_out = Cat("b0".asUInt(19.W), io.width_a)
    val nvdla_sdp_rdma_d_erdma_cfg_0_out = Cat("b0".asUInt(26.W), io.erdma_ram_type, io.erdma_data_mode, io.erdma_data_size, io.erdma_data_use, io.erdma_disable)
    val nvdla_sdp_rdma_d_ew_base_addr_high_0_out = io.ew_base_addr_high
    val nvdla_sdp_rdma_d_ew_base_addr_low_0_out = io.ew_base_addr_low
    val nvdla_sdp_rdma_d_ew_batch_stride_0_out = io.ew_batch_stride
    val nvdla_sdp_rdma_d_ew_line_stride_0_out = io.ew_line_stride
    val nvdla_sdp_rdma_d_ew_surface_stride_0_out = io.ew_surface_stride
    val nvdla_sdp_rdma_d_feature_mode_cfg_0_out = Cat("b0".asUInt(19.W), io.batch_number, io.out_precision, io.proc_precision, io.in_precision, io.winograd, io.flying_mode)
    val nvdla_sdp_rdma_d_nrdma_cfg_0_out = Cat("b0".asUInt(26.W), io.nrdma_ram_type, io.nrdma_data_mode, io.nrdma_data_size, io.nrdma_data_use, io.nrdma_disable)
    val nvdla_sdp_rdma_d_op_enable_0_out = Cat("b0".asUInt(31.W), io.op_en)
    val nvdla_sdp_rdma_d_perf_brdma_read_stall_0_out = io.brdma_stall
    val nvdla_sdp_rdma_d_perf_enable_0_out = Cat("b0".asUInt(30.W), io.perf_nan_inf_count_en, io.perf_dma_en)
    val nvdla_sdp_rdma_d_perf_erdma_read_stall_0_out = io.erdma_stall
    val nvdla_sdp_rdma_d_perf_mrdma_read_stall_0_out = io.mrdma_stall
    val nvdla_sdp_rdma_d_perf_nrdma_read_stall_0_out = io.nrdma_stall
    val nvdla_sdp_rdma_d_src_base_addr_high_0_out = io.src_base_addr_high
    val nvdla_sdp_rdma_d_src_base_addr_low_0_out= io.src_base_addr_low
    val nvdla_sdp_rdma_d_src_dma_cfg_0_out = Cat("b0".asUInt(31.W), io.src_ram_type)
    val nvdla_sdp_rdma_d_src_line_stride_0_out = io.src_line_stride
    val nvdla_sdp_rdma_d_src_surface_stride_0_out = io.src_surface_stride
    val nvdla_sdp_rdma_d_status_inf_input_num_0_out = io.status_inf_input_num
    val nvdla_sdp_rdma_d_status_nan_input_num_0_out = io.status_nan_input_num

    io.op_en_trigger := nvdla_sdp_rdma_d_op_enable_0_wren

    //Output mux

    io.reg_rd_data := MuxLookup(io.reg_offset, "b0".asUInt(32.W), 
    Seq(      
    "h48".asUInt(32.W)  -> nvdla_sdp_rdma_d_bn_base_addr_high_0_out,
    "h44".asUInt(32.W)  -> nvdla_sdp_rdma_d_bn_base_addr_low_0_out,
    "h54".asUInt(32.W)  -> nvdla_sdp_rdma_d_bn_batch_stride_0_out,
    "h4c".asUInt(32.W)  -> nvdla_sdp_rdma_d_bn_line_stride_0_out,
    "h50".asUInt(32.W)  -> nvdla_sdp_rdma_d_bn_surface_stride_0_out,
    "h28".asUInt(32.W)  -> nvdla_sdp_rdma_d_brdma_cfg_0_out,
    "h30".asUInt(32.W)  -> nvdla_sdp_rdma_d_bs_base_addr_high_0_out,
    "h2c".asUInt(32.W)  -> nvdla_sdp_rdma_d_bs_base_addr_low_0_out,
    "h3c".asUInt(32.W)  -> nvdla_sdp_rdma_d_bs_batch_stride_0_out,
    "h34".asUInt(32.W)  -> nvdla_sdp_rdma_d_bs_line_stride_0_out,
    "h38".asUInt(32.W)  -> nvdla_sdp_rdma_d_bs_surface_stride_0_out,
    "h14".asUInt(32.W)  -> nvdla_sdp_rdma_d_data_cube_channel_0_out,
    "h10".asUInt(32.W)  -> nvdla_sdp_rdma_d_data_cube_height_0_out, 
    "h0c".asUInt(32.W)  -> nvdla_sdp_rdma_d_data_cube_width_a_0_out, 
    "h58".asUInt(32.W)  -> nvdla_sdp_rdma_d_erdma_cfg_0_out, 
    "h60".asUInt(32.W)  -> nvdla_sdp_rdma_d_ew_base_addr_high_0_out, 
    "h5c".asUInt(32.W)  -> nvdla_sdp_rdma_d_ew_base_addr_low_0_out, 
    "h6c".asUInt(32.W)  -> nvdla_sdp_rdma_d_ew_batch_stride_0_out, 
    "h64".asUInt(32.W)  -> nvdla_sdp_rdma_d_ew_line_stride_0_out, 
    "h68".asUInt(32.W)  -> nvdla_sdp_rdma_d_ew_surface_stride_0_out, 
    "h70".asUInt(32.W)  -> nvdla_sdp_rdma_d_feature_mode_cfg_0_out, 
    "h40".asUInt(32.W)  -> nvdla_sdp_rdma_d_nrdma_cfg_0_out, 
    "h08".asUInt(32.W)  -> nvdla_sdp_rdma_d_op_enable_0_out,
    "h88".asUInt(32.W)  -> nvdla_sdp_rdma_d_perf_brdma_read_stall_0_out,
    "h80".asUInt(32.W)  -> nvdla_sdp_rdma_d_perf_enable_0_out,
    "h90".asUInt(32.W)  -> nvdla_sdp_rdma_d_perf_erdma_read_stall_0_out,
    "h84".asUInt(32.W)  -> nvdla_sdp_rdma_d_perf_mrdma_read_stall_0_out,
    "h8c".asUInt(32.W)  -> nvdla_sdp_rdma_d_perf_nrdma_read_stall_0_out,
    "h1c".asUInt(32.W)  -> nvdla_sdp_rdma_d_src_base_addr_high_0_out,
    "h18".asUInt(32.W)  -> nvdla_sdp_rdma_d_src_base_addr_low_0_out,
    "h74".asUInt(32.W)  -> nvdla_sdp_rdma_d_src_dma_cfg_0_out,
    "h20".asUInt(32.W)  -> nvdla_sdp_rdma_d_src_line_stride_0_out,
    "h24".asUInt(32.W)  -> nvdla_sdp_rdma_d_src_surface_stride_0_out,
    "h7c".asUInt(32.W)  -> nvdla_sdp_rdma_d_status_inf_input_num_0_out,
    "h78".asUInt(32.W)  -> nvdla_sdp_rdma_d_status_nan_input_num_0_out                                                                              
    ))

    //Register flop declarations


    //yifengdu y.f.du1994@gmail.com update on Aug 1, 2019 
    //Solve Java heap space problem
    // Register: NVDLA_SDP_RDMA_D_BN_BASE_ADDR_HIGH_0    Field: bn_base_addr_high
    io.bn_base_addr_high := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bn_base_addr_high_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BN_BASE_ADDR_LOW_0    Field: bn_base_addr_low
    io.bn_base_addr_low := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bn_base_addr_low_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BN_BATCH_STRIDE_0    Field: bn_batch_stride
    io.bn_batch_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bn_batch_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BN_LINE_STRIDE_0    Field: bn_line_stride
    io.bn_line_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bn_line_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BN_SURFACE_STRIDE_0    Field: bn_surface_stride
    io.bn_surface_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bn_surface_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BRDMA_CFG_0    Field: brdma_data_mode
    io.brdma_data_mode := RegEnable(io.reg_wr_data(4), false.B, nvdla_sdp_rdma_d_brdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BRDMA_CFG_0    Field: brdma_data_size
    io.brdma_data_size := RegEnable(io.reg_wr_data(3), false.B, nvdla_sdp_rdma_d_brdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BRDMA_CFG_0    Field: brdma_data_use
    io.brdma_data_use := RegEnable(io.reg_wr_data(2, 1), "b00".asUInt(2.W), nvdla_sdp_rdma_d_brdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BRDMA_CFG_0    Field: brdma_disable
    io.brdma_disable := RegEnable(io.reg_wr_data(0), true.B, nvdla_sdp_rdma_d_brdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BRDMA_CFG_0    Field: brdma_ram_type
    io.brdma_ram_type := RegEnable(io.reg_wr_data(5), false.B, nvdla_sdp_rdma_d_brdma_cfg_0_wren)
    
    // Register: NVDLA_SDP_RDMA_D_BS_BASE_ADDR_HIGH_0    Field: bs_base_addr_high
    io.bs_base_addr_high := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bs_base_addr_high_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BS_BASE_ADDR_LOW_0    Field: bs_base_addr_low
    io.bs_base_addr_low := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bs_base_addr_low_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BS_BATCH_STRIDE_0    Field: bs_batch_stride
    io.bs_batch_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bs_batch_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BS_LINE_STRIDE_0    Field: bs_line_stride
    io.bs_line_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bs_line_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_BS_SURFACE_STRIDE_0    Field: bs_surface_stride
    io.bs_surface_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_bs_surface_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_DATA_CUBE_CHANNEL_0    Field: channel
    io.channel := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_sdp_rdma_d_data_cube_channel_0_wren)
    // Register: NVDLA_SDP_RDMA_D_DATA_CUBE_HEIGHT_0    Field: height
    io.height := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_sdp_rdma_d_data_cube_height_0_wren)
    // Register: NVDLA_SDP_RDMA_D_DATA_CUBE_width_a_0    Field: width_a
    io.width_a := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_sdp_rdma_d_data_cube_width_a_0_wren)
    // Register: NVDLA_SDP_RDMA_D_ERDMA_CFG_0    Field: erdma_data_mode
    io.erdma_data_mode := RegEnable(io.reg_wr_data(4), false.B, nvdla_sdp_rdma_d_erdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_ERDMA_CFG_0    Field: erdma_data_size
    io.erdma_data_size := RegEnable(io.reg_wr_data(3), false.B, nvdla_sdp_rdma_d_erdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_ERDMA_CFG_0    Field: erdma_data_use
    io.erdma_data_use := RegEnable(io.reg_wr_data(2, 1), "b00".asUInt(2.W), nvdla_sdp_rdma_d_erdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_ERDMA_CFG_0    Field: erdma_disable
    io.erdma_disable := RegEnable(io.reg_wr_data(0), true.B, nvdla_sdp_rdma_d_erdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_ERDMA_CFG_0    Field: erdma_ram_type
    io.erdma_ram_type := RegEnable(io.reg_wr_data(5), false.B, nvdla_sdp_rdma_d_erdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_EW_BASE_ADDR_HIGH_0    Field: ew_base_addr_high
    io.ew_base_addr_high := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_ew_base_addr_high_0_wren)
    // Register: NVDLA_SDP_RDMA_D_EW_BASE_ADDR_LOW_0    Field: ew_base_addr_low
    io.ew_base_addr_low := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_ew_base_addr_low_0_wren)
    // Register: NVDLA_SDP_RDMA_D_EW_BATCH_STRIDE_0    Field: ew_batch_stride
    io.ew_batch_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_ew_batch_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_EW_LINE_STRIDE_0    Field: ew_line_stride
    io.ew_line_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_ew_line_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_EW_SURFACE_STRIDE_0    Field: ew_surface_stride
    io.ew_surface_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_ew_surface_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_FEATURE_MODE_CFG_0    Field: batch_number
    io.batch_number := RegEnable(io.reg_wr_data(12, 8), "b0".asUInt(32.W), nvdla_sdp_rdma_d_feature_mode_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_FEATURE_MODE_CFG_0    Field: flying_mode
    io.flying_mode := RegEnable(io.reg_wr_data(0), false.B, nvdla_sdp_rdma_d_feature_mode_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_FEATURE_MODE_CFG_0    Field: in_precision
    io.in_precision := RegEnable(io.reg_wr_data(3, 2), "b01".asUInt(2.W), nvdla_sdp_rdma_d_feature_mode_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_FEATURE_MODE_CFG_0    Field: out_precision
    io.out_precision := RegEnable(io.reg_wr_data(7, 6), "b00".asUInt(2.W), nvdla_sdp_rdma_d_feature_mode_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_FEATURE_MODE_CFG_0    Field: proc_precision
    io.proc_precision := RegEnable(io.reg_wr_data(5, 4), "b01".asUInt(2.W), nvdla_sdp_rdma_d_feature_mode_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_FEATURE_MODE_CFG_0    Field: winograd
    io.winograd := RegEnable(io.reg_wr_data(1), false.B, nvdla_sdp_rdma_d_feature_mode_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_NRDMA_CFG_0    Field: nrdma_data_mode
    io.nrdma_data_mode := RegEnable(io.reg_wr_data(4), false.B, nvdla_sdp_rdma_d_nrdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_NRDMA_CFG_0    Field: nrdma_data_size
    io.nrdma_data_size := RegEnable(io.reg_wr_data(3), false.B, nvdla_sdp_rdma_d_nrdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_NRDMA_CFG_0    Field: nrdma_data_use
    io.nrdma_data_use := RegEnable(io.reg_wr_data(2, 1), "b00".asUInt(2.W), nvdla_sdp_rdma_d_nrdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_NRDMA_CFG_0    Field: nrdma_disable
    io.nrdma_disable := RegEnable(io.reg_wr_data(0), true.B, nvdla_sdp_rdma_d_nrdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_NRDMA_CFG_0    Field: nrdma_ram_type
    io.nrdma_ram_type := RegEnable(io.reg_wr_data(5), false.B, nvdla_sdp_rdma_d_nrdma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_PERF_ENABLE_0    Field: perf_dma_en
    io.perf_dma_en := RegEnable(io.reg_wr_data(0), false.B, nvdla_sdp_rdma_d_perf_enable_0_wren)
    // Register: NVDLA_SDP_RDMA_D_PERF_ENABLE_0    Field: perf_nan_inf_count_en
    io.perf_nan_inf_count_en := RegEnable(io.reg_wr_data(1), false.B, nvdla_sdp_rdma_d_perf_enable_0_wren)
    // Register: NVDLA_SDP_RDMA_D_SRC_BASE_ADDR_HIGH_0    Field: src_base_addr_high
    io.src_base_addr_high := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_src_base_addr_high_0_wren)
    // Register: NVDLA_SDP_RDMA_D_SRC_BASE_ADDR_LOW_0    Field: src_base_addr_low
    io.src_base_addr_low := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_src_base_addr_low_0_wren)
    // Register: NVDLA_SDP_RDMA_D_SRC_DMA_CFG_0    Field: src_ram_type
    io.src_ram_type := RegEnable(io.reg_wr_data(0), false.B, nvdla_sdp_rdma_d_src_dma_cfg_0_wren)
    // Register: NVDLA_SDP_RDMA_D_SRC_LINE_STRIDE_0    Field: src_line_stride
    io.src_line_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_src_line_stride_0_wren)
    // Register: NVDLA_SDP_RDMA_D_SRC_SURFACE_STRIDE_0    Field: src_surface_stride
    io.src_surface_stride := RegEnable(io.reg_wr_data, "b0".asUInt(32.W), nvdla_sdp_rdma_d_src_surface_stride_0_wren)                                                               

}}

object NV_NVDLA_SDP_RDMA_REG_dualDriver extends App {
  chisel3.Driver.execute(args, () => new NV_NVDLA_SDP_RDMA_REG_dual())
}