package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class NV_NVDLA_PDP_RDMA_REG_dual extends Module {
    val io = IO(new Bundle {
        // clk
        val nvdla_core_clk = Input(Clock())

        // Register control interface
        val reg_rd_data = Output(UInt(32.W))
        val reg_offset = Input(UInt(12.W))
        val reg_wr_data = Input(UInt(32.W))
        val reg_wr_en = Input(Bool())

        // Writable register flop/trigger outputs
        val cya = Output(UInt(32.W))
        val cube_in_channel = Output(UInt(13.W))
        val cube_in_height = Output(UInt(13.W))
        val cube_in_width = Output(UInt(13.W))
        val input_data = Output(UInt(2.W))
        val flying_mode = Output(Bool())
        val split_num = Output(UInt(8.W))
        val op_en_trigger = Output(Bool())
        val partial_width_in_first = Output(UInt(10.W))
        val partial_width_in_last = Output(UInt(10.W))
        val partial_width_in_mid = Output(UInt(10.W))
        val dma_en = Output(Bool())
        val kernel_stride_width = Output(UInt(4.W))
        val kernel_width = Output(UInt(4.W))
        val pad_width = Output(UInt(4.W))
        val src_base_addr_high = Output(UInt(32.W))
        val src_base_addr_low = Output(UInt(32.W))
        val src_line_stride = Output(UInt(32.W))
        val src_ram_type = Output(Bool())
        val src_surface_stride = Output(UInt(32.W))

        // Read-only register input
        val op_en = Input(Bool())
        val perf_read_stall = Input(UInt(32.W))
    })
//     
//          ┌─┐       ┌─┐
//       ┌──┘ ┴───────┘ ┴──┐
//       │                 │
//       │       ───       │
//       │  ─┬┘       └┬─  │
//       │                 │
//       │       ─┴─       │
//       │                 │
//       └───┐         ┌───┘
//           │         │
//           │         │
//           │         │
//           │         └──────────────┐
//           │                        │
//           │                        ├─┐
//           │                        ┌─┘    
//           │                        │
//           └─┐  ┐  ┌───────┬──┐  ┌──┘         
//             │ ─┤ ─┤       │ ─┤ ─┤         
//             └──┴──┘       └──┴──┘ 
    withClock(io.nvdla_core_clk){

    // Address decode
    val nvdla_pdp_rdma_d_cya_0_wren = (io.reg_offset === "h4c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_data_cube_in_channel_0_wren = (io.reg_offset === "h14".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_data_cube_in_height_0_wren = (io.reg_offset === "h10".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_data_cube_in_width_0_wren = (io.reg_offset === "h0c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_data_format_0_wren = (io.reg_offset === "h30".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_flying_mode_0_wren = (io.reg_offset === "h18".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_operation_mode_cfg_0_wren = (io.reg_offset === "h34".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_op_enable_0_wren = (io.reg_offset === "h08".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_partial_width_in_0_wren = (io.reg_offset === "h40".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_perf_enable_0_wren = (io.reg_offset === "h44".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_perf_read_stall_0_wren = (io.reg_offset === "h48".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_pooling_kernel_cfg_0_wren = (io.reg_offset === "h38".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_pooling_padding_cfg_0_wren = (io.reg_offset === "h3c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_src_base_addr_high_0_wren = (io.reg_offset === "h20".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_src_base_addr_low_0_wren = (io.reg_offset === "h1c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_src_line_stride_0_wren = (io.reg_offset === "h24".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_src_ram_cfg_0_wren = (io.reg_offset === "h2c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_rdma_d_src_surface_stride_0_wren = (io.reg_offset === "h28".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    
    val nvdla_pdp_rdma_d_cya_0_out = io.cya
    val nvdla_pdp_rdma_d_data_cube_in_channel_0_out =  Cat("b0".asUInt(19.W), io.cube_in_channel)
    val nvdla_pdp_rdma_d_data_cube_in_height_0_out = Cat("b0".asUInt(19.W), io.cube_in_height)
    val nvdla_pdp_rdma_d_data_cube_in_width_0_out =  Cat("b0".asUInt(19.W), io.cube_in_width)
    val nvdla_pdp_rdma_d_data_format_0_out =  Cat("b0".asUInt(30.W), io.input_data)
    val nvdla_pdp_rdma_d_flying_mode_0_out =  Cat("b0".asUInt(31.W), io.flying_mode)
    val nvdla_pdp_rdma_d_operation_mode_cfg_0_out =  Cat("b0".asUInt(24.W), io.split_num)
    val nvdla_pdp_rdma_d_op_enable_0_out =  Cat("b0".asUInt(31.W), io.op_en)
    val nvdla_pdp_rdma_d_partial_width_in_0_out =  Cat("b0".asUInt(2.W), io.partial_width_in_mid, io.partial_width_in_last, io.partial_width_in_first)
    val nvdla_pdp_rdma_d_perf_enable_0_out =  Cat("b0".asUInt(31.W), io.dma_en)
    val nvdla_pdp_rdma_d_perf_read_stall_0_out =  io.perf_read_stall
    val nvdla_pdp_rdma_d_pooling_kernel_cfg_0_out =  Cat("b0".asUInt(24.W), io.kernel_stride_width, io.kernel_width)
    val nvdla_pdp_rdma_d_pooling_padding_cfg_0_out =  Cat("b0".asUInt(28.W), io.pad_width)
    val nvdla_pdp_rdma_d_src_base_addr_high_0_out =  io.src_base_addr_high
    val nvdla_pdp_rdma_d_src_base_addr_low_0_out =  io.src_base_addr_low
    val nvdla_pdp_rdma_d_src_line_stride_0_out =  io.src_line_stride
    val nvdla_pdp_rdma_d_src_ram_cfg_0_out =  Cat("b0".asUInt(31.W), io.src_ram_type)
    val nvdla_pdp_rdma_d_src_surface_stride_0_out =  io.src_surface_stride

    io.op_en_trigger := nvdla_pdp_rdma_d_op_enable_0_wren

    // Output mux

    io.reg_rd_data := MuxLookup(io.reg_offset, "b0".asUInt(32.W), 
    Seq(      
    "h4c".asUInt(32.W)  -> nvdla_pdp_rdma_d_cya_0_out,
    "h14".asUInt(32.W)  -> nvdla_pdp_rdma_d_data_cube_in_channel_0_out,
    "h10".asUInt(32.W)  -> nvdla_pdp_rdma_d_data_cube_in_height_0_out,
    "h0c".asUInt(32.W)  -> nvdla_pdp_rdma_d_data_cube_in_width_0_out,
    "h30".asUInt(32.W)  -> nvdla_pdp_rdma_d_data_format_0_out,
    "h18".asUInt(32.W)  -> nvdla_pdp_rdma_d_flying_mode_0_out,
    "h34".asUInt(32.W)  -> nvdla_pdp_rdma_d_operation_mode_cfg_0_out,
    "h08".asUInt(32.W)  -> nvdla_pdp_rdma_d_op_enable_0_out,
    "h40".asUInt(32.W)  -> nvdla_pdp_rdma_d_partial_width_in_0_out,
    "h44".asUInt(32.W)  -> nvdla_pdp_rdma_d_perf_enable_0_out,
    "h48".asUInt(32.W)  -> nvdla_pdp_rdma_d_perf_read_stall_0_out,
    "h38".asUInt(32.W)  -> nvdla_pdp_rdma_d_pooling_kernel_cfg_0_out,
    "h3c".asUInt(32.W)  -> nvdla_pdp_rdma_d_pooling_padding_cfg_0_out,
    "h20".asUInt(32.W)  -> nvdla_pdp_rdma_d_src_base_addr_high_0_out,
    "h1c".asUInt(32.W)  -> nvdla_pdp_rdma_d_src_base_addr_low_0_out,
    "h24".asUInt(32.W)  -> nvdla_pdp_rdma_d_src_line_stride_0_out,
    "h2c".asUInt(32.W)  -> nvdla_pdp_rdma_d_src_ram_cfg_0_out,
    "h28".asUInt(32.W)  -> nvdla_pdp_rdma_d_src_surface_stride_0_out
    ))

    // Register flop declarations

    // Register: NVDLA_PDP_RDMA_D_CYA_0    Field: cya
    io.cya := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_rdma_d_cya_0_wren)
    // Register: NVDLA_PDP_RDMA_D_DATA_CUBE_IN_CHANNEL_0    Field: cube_in_channel
    io.cube_in_channel := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_rdma_d_data_cube_in_channel_0_wren)
    // Register: NVDLA_PDP_RDMA_D_DATA_CUBE_IN_HEIGHT_0    Field: cube_in_height
    io.cube_in_height := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_rdma_d_data_cube_in_height_0_wren)
    // Register: NVDLA_PDP_RDMA_D_DATA_CUBE_IN_WIDTH_0    Field: cube_in_width
    io.cube_in_width := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_rdma_d_data_cube_in_width_0_wren)
    // Register: NVDLA_PDP_RDMA_D_DATA_FORMAT_0    Field: input_data
    io.input_data := RegEnable(io.reg_wr_data(1, 0), "b0".asUInt(2.W), nvdla_pdp_rdma_d_data_format_0_wren)
    // Register: NVDLA_PDP_RDMA_D_FLYING_MODE_0    Field: flying_mode
    io.flying_mode := RegEnable(io.reg_wr_data(0), false.B, nvdla_pdp_rdma_d_flying_mode_0_wren)
    // Register: NVDLA_PDP_RDMA_D_OPERATION_MODE_CFG_0    Field: split_num
    io.split_num := RegEnable(io.reg_wr_data(7, 0), "b0".asUInt(8.W), nvdla_pdp_rdma_d_operation_mode_cfg_0_wren)
    // Register: NVDLA_PDP_RDMA_D_PARTIAL_WIDTH_IN_0    Field: partial_width_in_first
    io.partial_width_in_first := RegEnable(io.reg_wr_data(9, 0), "b0".asUInt(10.W), nvdla_pdp_rdma_d_partial_width_in_0_wren)
    // Register: NVDLA_PDP_RDMA_D_PARTIAL_WIDTH_IN_0    Field: partial_width_in_last
    io.partial_width_in_last := RegEnable(io.reg_wr_data(19, 10), "b0".asUInt(10.W), nvdla_pdp_rdma_d_partial_width_in_0_wren)
    // Register: NVDLA_PDP_RDMA_D_PARTIAL_WIDTH_IN_0    Field: partial_width_in_mid
    io.partial_width_in_mid := RegEnable(io.reg_wr_data(29, 20), "b0".asUInt(10.W), nvdla_pdp_rdma_d_partial_width_in_0_wren)
    // Register: NVDLA_PDP_RDMA_D_PERF_ENABLE_0    Field: dma_en
    io.dma_en := RegEnable(io.reg_wr_data(0), false.B, nvdla_pdp_rdma_d_perf_enable_0_wren)
    // Register: NVDLA_PDP_RDMA_D_POOLING_KERNEL_CFG_0    Field: kernel_stride_width
    io.kernel_stride_width := RegEnable(io.reg_wr_data(7, 4), "b0".asUInt(4.W), nvdla_pdp_rdma_d_pooling_kernel_cfg_0_wren)
    // Register: NVDLA_PDP_RDMA_D_POOLING_KERNEL_CFG_0    Field: kernel_width
    io.kernel_width := RegEnable(io.reg_wr_data(3, 0), "b0".asUInt(4.W), nvdla_pdp_rdma_d_pooling_kernel_cfg_0_wren)
    // Register: NVDLA_PDP_RDMA_D_POOLING_PADDING_CFG_0    Field: pad_width
    io.pad_width := RegEnable(io.reg_wr_data(3, 0), "b0".asUInt(4.W), nvdla_pdp_rdma_d_pooling_padding_cfg_0_wren)
    // Register: NVDLA_PDP_RDMA_D_SRC_BASE_ADDR_HIGH_0    Field: src_base_addr_high
    io.src_base_addr_high := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_rdma_d_src_base_addr_high_0_wren)
    // Register: NVDLA_PDP_RDMA_D_SRC_BASE_ADDR_LOW_0    Field: src_base_addr_low
    io.src_base_addr_low := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_rdma_d_src_base_addr_low_0_wren)
    // Register: NVDLA_PDP_RDMA_D_SRC_LINE_STRIDE_0    Field: src_line_stride
    io.src_line_stride := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_rdma_d_src_line_stride_0_wren)
    // Register: NVDLA_PDP_RDMA_D_SRC_RAM_CFG_0    Field: src_ram_type
    io.src_ram_type := RegEnable(io.reg_wr_data(0), false.B, nvdla_pdp_rdma_d_src_ram_cfg_0_wren)
    // Register: NVDLA_PDP_RDMA_D_SRC_SURFACE_STRIDE_0    Field: src_surface_stride
    io.src_surface_stride := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_rdma_d_src_surface_stride_0_wren)

}}