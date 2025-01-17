package nvdla

import chisel3._
import chisel3.experimental._
import chisel3.util._

class NV_NVDLA_PDP_REG_dual extends Module {
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
        val cube_out_channel = Output(UInt(13.W))
        val cube_out_height = Output(UInt(13.W))
        val cube_out_width = Output(UInt(13.W))
        val input_data = Output(UInt(2.W))
        val dst_base_addr_high = Output(UInt(32.W))
        val dst_base_addr_low = Output(UInt(32.W))
        val dst_line_stride = Output(UInt(32.W))
        val dst_ram_type = Output(Bool())
        val dst_surface_stride = Output(UInt(32.W))
        val nan_to_zero = Output(Bool())
        val flying_mode = Output(Bool())
        val pooling_method = Output(UInt(2.W))
        val split_num = Output(UInt(8.W))
        val op_en_trigger = Output(Bool())
        val partial_width_in_first = Output(UInt(10.W))
        val partial_width_in_last = Output(UInt(10.W))
        val partial_width_in_mid = Output(UInt(10.W))
        val partial_width_out_first = Output(UInt(10.W))
        val partial_width_out_last = Output(UInt(10.W))
        val partial_width_out_mid = Output(UInt(10.W))
        val dma_en = Output(Bool())
        val kernel_height = Output(UInt(4.W))
        val kernel_stride_height = Output(UInt(4.W))
        val kernel_stride_width = Output(UInt(4.W))
        val kernel_width = Output(UInt(4.W))
        val pad_bottom = Output(UInt(3.W))
        val pad_left = Output(UInt(3.W))
        val pad_right = Output(UInt(3.W))
        val pad_top = Output(UInt(3.W))
        val pad_value_1x = Output(UInt(19.W))
        val pad_value_2x = Output(UInt(19.W))
        val pad_value_3x = Output(UInt(19.W))
        val pad_value_4x = Output(UInt(19.W))
        val pad_value_5x = Output(UInt(19.W))
        val pad_value_6x = Output(UInt(19.W))
        val pad_value_7x = Output(UInt(19.W))
        val recip_kernel_height = Output(UInt(17.W))
        val recip_kernel_width = Output(UInt(17.W))
        val src_base_addr_high = Output(UInt(32.W))
        val src_base_addr_low = Output(UInt(32.W))
        val src_line_stride = Output(UInt(32.W))
        val src_surface_stride = Output(UInt(32.W))

        // Read-only register input
        val inf_input_num = Input(UInt(32.W))
        val nan_input_num = Input(UInt(32.W))
        val nan_output_num = Input(UInt(32.W))
        val op_en = Input(Bool())
        val perf_write_stall = Input(UInt(32.W))
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
    val nvdla_pdp_d_cya_0_wren = (io.reg_offset === "h9c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_cube_in_channel_0_wren = (io.reg_offset === "h14".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_cube_in_height_0_wren = (io.reg_offset === "h10".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_cube_in_width_0_wren = (io.reg_offset === "h0c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_cube_out_channel_0_wren = (io.reg_offset === "h20".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_cube_out_height_0_wren = (io.reg_offset === "h1c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_cube_out_width_0_wren = (io.reg_offset === "h18".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_data_format_0_wren = (io.reg_offset === "h84".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_dst_base_addr_high_0_wren = (io.reg_offset === "h74".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_dst_base_addr_low_0_wren = (io.reg_offset === "h70".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_dst_line_stride_0_wren = (io.reg_offset === "h78".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_dst_ram_cfg_0_wren = (io.reg_offset === "h80".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_dst_surface_stride_0_wren = (io.reg_offset === "h7c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_inf_input_num_0_wren = (io.reg_offset === "h88".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_nan_flush_to_zero_0_wren = (io.reg_offset === "h28".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_nan_input_num_0_wren = (io.reg_offset === "h8c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_nan_output_num_0_wren = (io.reg_offset === "h90".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_operation_mode_cfg_0_wren = (io.reg_offset === "h24".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_op_enable_0_wren = (io.reg_offset === "h08".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_partial_width_in_0_wren = (io.reg_offset === "h2c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_partial_width_out_0_wren = (io.reg_offset === "h30".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_perf_enable_0_wren = (io.reg_offset === "h94".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_perf_write_stall_0_wren = (io.reg_offset === "h98".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_kernel_cfg_0_wren = (io.reg_offset === "h34".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_cfg_0_wren = (io.reg_offset === "h40".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_1_cfg_0_wren = (io.reg_offset === "h44".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_2_cfg_0_wren = (io.reg_offset === "h48".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_3_cfg_0_wren = (io.reg_offset === "h4c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_4_cfg_0_wren = (io.reg_offset === "h50".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_5_cfg_0_wren = (io.reg_offset === "h54".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_6_cfg_0_wren = (io.reg_offset === "h58".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_pooling_padding_value_7_cfg_0_wren = (io.reg_offset === "h5c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_recip_kernel_height_0_wren = (io.reg_offset === "h3c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_recip_kernel_width_0_wren = (io.reg_offset === "h38".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_src_base_addr_high_0_wren = (io.reg_offset === "h64".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_src_base_addr_low_0_wren = (io.reg_offset === "h60".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_src_line_stride_0_wren = (io.reg_offset === "h68".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    val nvdla_pdp_d_src_surface_stride_0_wren = (io.reg_offset === "h6c".asUInt(32.W)) & io.reg_wr_en ;  //spyglass disable UnloadedNet-ML //(W528)
    
    val nvdla_pdp_d_cya_0_out = io.cya
    val nvdla_pdp_d_data_cube_in_channel_0_out = Cat("b0".asUInt(19.W), io.cube_in_channel) 
    val nvdla_pdp_d_data_cube_in_height_0_out = Cat("b0".asUInt(19.W), io.cube_in_height)
    val nvdla_pdp_d_data_cube_in_width_0_out = Cat("b0".asUInt(19.W), io.cube_in_width)
    val nvdla_pdp_d_data_cube_out_channel_0_out = Cat("b0".asUInt(19.W), io.cube_out_channel)
    val nvdla_pdp_d_data_cube_out_height_0_out = Cat("b0".asUInt(19.W), io.cube_out_height)
    val nvdla_pdp_d_data_cube_out_width_0_out = Cat("b0".asUInt(19.W), io.cube_out_width)
    val nvdla_pdp_d_data_format_0_out = Cat("b0".asUInt(30.W), io.input_data)
    val nvdla_pdp_d_dst_base_addr_high_0_out = io.dst_base_addr_high
//    val nvdla_pdp_d_dst_base_addr_low_0_out =  dst_base_addr_low, 5'b0 };
    val nvdla_pdp_d_dst_base_addr_low_0_out =  io.dst_base_addr_low
    val nvdla_pdp_d_dst_line_stride_0_out =  io.dst_line_stride 
    val nvdla_pdp_d_dst_ram_cfg_0_out = Cat("b0".asUInt(31.W), io.dst_ram_type)
    val nvdla_pdp_d_dst_surface_stride_0_out = io.dst_surface_stride
    val nvdla_pdp_d_inf_input_num_0_out = io.inf_input_num
    val nvdla_pdp_d_nan_flush_to_zero_0_out = Cat("b0".asUInt(31.W), io.nan_to_zero)
    val nvdla_pdp_d_nan_input_num_0_out = io.nan_input_num
    val nvdla_pdp_d_nan_output_num_0_out = io.nan_output_num
    val nvdla_pdp_d_operation_mode_cfg_0_out = Cat("b0".asUInt(16.W), io.split_num, "b0".asUInt(3.W), io.flying_mode, "b0".asUInt(2.W), io.pooling_method)
    val nvdla_pdp_d_op_enable_0_out =  Cat("b0".asUInt(31.W), io.op_en)
    val nvdla_pdp_d_partial_width_in_0_out =  Cat("b0".asUInt(2.W), io.partial_width_in_mid, io.partial_width_in_last, io.partial_width_in_first)
    val nvdla_pdp_d_partial_width_out_0_out = Cat("b0".asUInt(2.W), io.partial_width_out_mid, io.partial_width_out_last, io.partial_width_out_first)
    val nvdla_pdp_d_perf_enable_0_out = Cat("b0".asUInt(31.W), io.dma_en)
    val nvdla_pdp_d_perf_write_stall_0_out = io.perf_write_stall
    val nvdla_pdp_d_pooling_kernel_cfg_0_out = Cat("b0".asUInt(8.W), io.kernel_stride_height, io.kernel_stride_width, "b0".asUInt(4.W), io.kernel_height, "b0".asUInt(4.W), io.kernel_width)
    val nvdla_pdp_d_pooling_padding_cfg_0_out = Cat("b0".asUInt(17.W), io.pad_bottom, false.B, io.pad_right, false.B, io.pad_top, false.B, io.pad_left)
    val nvdla_pdp_d_pooling_padding_value_1_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_1x)
    val nvdla_pdp_d_pooling_padding_value_2_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_2x)
    val nvdla_pdp_d_pooling_padding_value_3_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_3x)
    val nvdla_pdp_d_pooling_padding_value_4_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_4x)
    val nvdla_pdp_d_pooling_padding_value_5_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_5x)
    val nvdla_pdp_d_pooling_padding_value_6_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_6x)
    val nvdla_pdp_d_pooling_padding_value_7_cfg_0_out = Cat("b0".asUInt(13.W), io.pad_value_7x)
    val nvdla_pdp_d_recip_kernel_height_0_out = Cat("b0".asUInt(15.W), io.recip_kernel_height)
    val nvdla_pdp_d_recip_kernel_width_0_out = Cat("b0".asUInt(15.W), io.recip_kernel_width)
    val nvdla_pdp_d_src_base_addr_high_0_out = io.src_base_addr_high
    val nvdla_pdp_d_src_base_addr_low_0_out = io.src_base_addr_low 
    val nvdla_pdp_d_src_line_stride_0_out = io.src_line_stride
    val nvdla_pdp_d_src_surface_stride_0_out = io.src_surface_stride

    io.op_en_trigger := nvdla_pdp_d_op_enable_0_wren

    // Output mux

    io.reg_rd_data := MuxLookup(io.reg_offset, "b0".asUInt(32.W), 
    Seq(      
    "h9c".asUInt(32.W)  -> nvdla_pdp_d_cya_0_out,
    "h14".asUInt(32.W)  -> nvdla_pdp_d_data_cube_in_channel_0_out,
    "h10".asUInt(32.W)  -> nvdla_pdp_d_data_cube_in_height_0_out,
    "h0c".asUInt(32.W)  -> nvdla_pdp_d_data_cube_in_width_0_out,
    "h20".asUInt(32.W)  -> nvdla_pdp_d_data_cube_out_channel_0_out,
    "h1c".asUInt(32.W)  -> nvdla_pdp_d_data_cube_out_height_0_out,
    "h18".asUInt(32.W)  -> nvdla_pdp_d_data_cube_out_width_0_out,
    "h84".asUInt(32.W)  -> nvdla_pdp_d_data_format_0_out,
    "h74".asUInt(32.W)  -> nvdla_pdp_d_dst_base_addr_high_0_out,
    "h70".asUInt(32.W)  -> nvdla_pdp_d_dst_base_addr_low_0_out,
    "h78".asUInt(32.W)  -> nvdla_pdp_d_dst_line_stride_0_out,
    "h80".asUInt(32.W)  -> nvdla_pdp_d_dst_ram_cfg_0_out,
    "h7c".asUInt(32.W)  -> nvdla_pdp_d_dst_surface_stride_0_out,
    "h88".asUInt(32.W)  -> nvdla_pdp_d_inf_input_num_0_out,
    "h28".asUInt(32.W)  -> nvdla_pdp_d_nan_flush_to_zero_0_out,
    "h8c".asUInt(32.W)  -> nvdla_pdp_d_nan_input_num_0_out,
    "h90".asUInt(32.W)  -> nvdla_pdp_d_nan_output_num_0_out,
    "h24".asUInt(32.W)  -> nvdla_pdp_d_operation_mode_cfg_0_out,
    "h08".asUInt(32.W)  -> nvdla_pdp_d_op_enable_0_out,
    "h2c".asUInt(32.W)  -> nvdla_pdp_d_partial_width_in_0_out,
    "h30".asUInt(32.W)  -> nvdla_pdp_d_partial_width_out_0_out,
    "h94".asUInt(32.W)  -> nvdla_pdp_d_perf_enable_0_out,
    "h98".asUInt(32.W)  -> nvdla_pdp_d_perf_write_stall_0_out,
    "h34".asUInt(32.W)  -> nvdla_pdp_d_pooling_kernel_cfg_0_out,
    "h40".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_cfg_0_out,
    "h44".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_1_cfg_0_out,
    "h48".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_2_cfg_0_out,
    "h4c".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_3_cfg_0_out,
    "h50".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_4_cfg_0_out,
    "h54".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_5_cfg_0_out,
    "h58".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_6_cfg_0_out,
    "h5c".asUInt(32.W)  -> nvdla_pdp_d_pooling_padding_value_7_cfg_0_out,
    "h3c".asUInt(32.W)  -> nvdla_pdp_d_recip_kernel_height_0_out,
    "h38".asUInt(32.W)  -> nvdla_pdp_d_recip_kernel_width_0_out,
    "h64".asUInt(32.W)  -> nvdla_pdp_d_src_base_addr_high_0_out,
    "h60".asUInt(32.W)  -> nvdla_pdp_d_src_base_addr_low_0_out,
    "h68".asUInt(32.W)  -> nvdla_pdp_d_src_line_stride_0_out,
    "h6c".asUInt(32.W)  -> nvdla_pdp_d_src_surface_stride_0_out
    ))

    // Register flop declaration

    // Register: NVDLA_PDP_D_CYA_0    Field: cya
    io.cya := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_cya_0_wren)   
    // Register: NVDLA_PDP_D_DATA_CUBE_IN_CHANNEL_0    Field: cube_in_channel
    io.cube_in_channel := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_d_data_cube_in_channel_0_wren)   
    // Register: NVDLA_PDP_D_DATA_CUBE_IN_HEIGHT_0    Field: cube_in_height
    io.cube_in_height := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_d_data_cube_in_height_0_wren)  
    // Register: NVDLA_PDP_D_DATA_CUBE_IN_WIDTH_0    Field: cube_in_width 
    io.cube_in_width := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_d_data_cube_in_width_0_wren)   
    // Register: NVDLA_PDP_D_DATA_CUBE_OUT_CHANNEL_0    Field: cube_out_channel
    io.cube_out_channel := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_d_data_cube_out_channel_0_wren)  
    // Register: NVDLA_PDP_D_DATA_CUBE_OUT_HEIGHT_0    Field: cube_out_height 
    io.cube_out_height := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_d_data_cube_out_height_0_wren)  
    // Register: NVDLA_PDP_D_DATA_CUBE_OUT_WIDTH_0    Field: cube_out_width 
    io.cube_out_width := RegEnable(io.reg_wr_data(12, 0), "b0".asUInt(13.W), nvdla_pdp_d_data_cube_out_width_0_wren)   
    // Register: NVDLA_PDP_D_DATA_FORMAT_0    Field: input_data
    io.input_data := RegEnable(io.reg_wr_data(1, 0), "b0".asUInt(2.W), nvdla_pdp_d_data_format_0_wren)   
    // Register: NVDLA_PDP_D_DST_BASE_ADDR_HIGH_0    Field: dst_base_addr_high
    io.dst_base_addr_high := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_dst_base_addr_high_0_wren)  
    // Register: NVDLA_PDP_D_DST_BASE_ADDR_LOW_0    Field: dst_base_addr_low 
    io.dst_base_addr_low := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_dst_base_addr_low_0_wren)   
    // Register: NVDLA_PDP_D_DST_LINE_STRIDE_0    Field: dst_line_stride
    io.dst_line_stride := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_dst_line_stride_0_wren)   
    // Register: NVDLA_PDP_D_DST_RAM_CFG_0    Field: dst_ram_type
    io.dst_ram_type := RegEnable(io.reg_wr_data(0), false.B, nvdla_pdp_d_dst_ram_cfg_0_wren)   
    // Register: NVDLA_PDP_D_DST_SURFACE_STRIDE_0    Field: dst_surface_stride
    io.dst_surface_stride := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_dst_surface_stride_0_wren) 
    // Register: NVDLA_PDP_D_NAN_FLUSH_TO_ZERO_0    Field: nan_to_zero  
    io.nan_to_zero := RegEnable(io.reg_wr_data(0), false.B, nvdla_pdp_d_nan_flush_to_zero_0_wren)   
    // Register: NVDLA_PDP_D_OPERATION_MODE_CFG_0    Field: flying_mode
    io.flying_mode := RegEnable(io.reg_wr_data(4), false.B, nvdla_pdp_d_operation_mode_cfg_0_wren)   
    // Register: NVDLA_PDP_D_OPERATION_MODE_CFG_0    Field: pooling_method
    io.pooling_method := RegEnable(io.reg_wr_data(1, 0), "b0".asUInt(2.W), nvdla_pdp_d_operation_mode_cfg_0_wren)   
    // Register: NVDLA_PDP_D_OPERATION_MODE_CFG_0    Field: split_num
    io.split_num := RegEnable(io.reg_wr_data(15, 8), "b0".asUInt(8.W), nvdla_pdp_d_operation_mode_cfg_0_wren)  
    // Register: NVDLA_PDP_D_PARTIAL_WIDTH_IN_0    Field: partial_width_in_first 
    io.partial_width_in_first := RegEnable(io.reg_wr_data(9, 0), "b0".asUInt(10.W), nvdla_pdp_d_partial_width_in_0_wren) 
    // Register: NVDLA_PDP_D_PARTIAL_WIDTH_IN_0    Field: partial_width_in_last  
    io.partial_width_in_last := RegEnable(io.reg_wr_data(19, 10), "b0".asUInt(10.W), nvdla_pdp_d_partial_width_in_0_wren)   
    // Register: NVDLA_PDP_D_PARTIAL_WIDTH_IN_0    Field: partial_width_in_mid
    io.partial_width_in_mid := RegEnable(io.reg_wr_data(29, 20), "b0".asUInt(10.W), nvdla_pdp_d_partial_width_in_0_wren)  
    // Register: NVDLA_PDP_D_PARTIAL_WIDTH_OUT_0    Field: partial_width_out_first 
    io.partial_width_out_first := RegEnable(io.reg_wr_data(9, 0), "b0".asUInt(10.W), nvdla_pdp_d_partial_width_out_0_wren)  
    // Register: NVDLA_PDP_D_PARTIAL_WIDTH_OUT_0    Field: partial_width_out_last 
    io.partial_width_out_last := RegEnable(io.reg_wr_data(19, 10), "b0".asUInt(10.W), nvdla_pdp_d_partial_width_out_0_wren)   
    // Register: NVDLA_PDP_D_PARTIAL_WIDTH_OUT_0    Field: partial_width_out_mid
    io.partial_width_out_mid := RegEnable(io.reg_wr_data(29, 20), "b0".asUInt(10.W), nvdla_pdp_d_partial_width_out_0_wren)  
    // Register: NVDLA_PDP_D_PERF_ENABLE_0    Field: dma_en 
    io.dma_en := RegEnable(io.reg_wr_data(0), false.B, nvdla_pdp_d_perf_enable_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_KERNEL_CFG_0    Field: kernel_heigh
    io.kernel_height := RegEnable(io.reg_wr_data(11, 8), "b0".asUInt(4.W), nvdla_pdp_d_pooling_kernel_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_KERNEL_CFG_0    Field: kernel_stride_height
    io.kernel_stride_height := RegEnable(io.reg_wr_data(23, 20), "b0".asUInt(4.W), nvdla_pdp_d_pooling_kernel_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_KERNEL_CFG_0    Field: kernel_stride_width
    io.kernel_stride_width := RegEnable(io.reg_wr_data(19, 16), "b0".asUInt(4.W), nvdla_pdp_d_pooling_kernel_cfg_0_wren)  
    // Register: NVDLA_PDP_D_POOLING_KERNEL_CFG_0    Field: kernel_width 
    io.kernel_width := RegEnable(io.reg_wr_data(3, 0), "b0".asUInt(4.W), nvdla_pdp_d_pooling_kernel_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_CFG_0    Field: pad_bottom
    io.pad_bottom := RegEnable(io.reg_wr_data(14, 12), "b0".asUInt(3.W), nvdla_pdp_d_pooling_padding_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_CFG_0    Field: pad_left
    io.pad_left := RegEnable(io.reg_wr_data(2, 0), "b0".asUInt(3.W), nvdla_pdp_d_pooling_padding_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_CFG_0    Field: pad_right
    io.pad_right := RegEnable(io.reg_wr_data(10, 8), "b0".asUInt(3.W), nvdla_pdp_d_pooling_padding_cfg_0_wren)  
    // Register: NVDLA_PDP_D_POOLING_PADDING_CFG_0    Field: pad_top 
    io.pad_top := RegEnable(io.reg_wr_data(6, 4), "b0".asUInt(3.W), nvdla_pdp_d_pooling_padding_cfg_0_wren)  
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_1_CFG_0    Field: pad_value_1x 
    io.pad_value_1x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_1_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_2_CFG_0    Field: pad_value_2x
    io.pad_value_2x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_2_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_3_CFG_0    Field: pad_value_3x
    io.pad_value_3x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_3_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_4_CFG_0    Field: pad_value_4x
    io.pad_value_4x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_4_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_5_CFG_0    Field: pad_value_5x
    io.pad_value_5x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_5_cfg_0_wren)   
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_6_CFG_0    Field: pad_value_6x
    io.pad_value_6x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_6_cfg_0_wren) 
    // Register: NVDLA_PDP_D_POOLING_PADDING_VALUE_7_CFG_0    Field: pad_value_7x  
    io.pad_value_7x := RegEnable(io.reg_wr_data(18, 0), "b0".asUInt(19.W), nvdla_pdp_d_pooling_padding_value_7_cfg_0_wren)   
    // Register: NVDLA_PDP_D_RECIP_KERNEL_HEIGHT_0    Field: recip_kernel_height
    io.recip_kernel_height := RegEnable(io.reg_wr_data(16, 0), "b0".asUInt(17.W), nvdla_pdp_d_recip_kernel_height_0_wren)   
    // Register: NVDLA_PDP_D_RECIP_KERNEL_WIDTH_0    Field: recip_kernel_width
    io.recip_kernel_width := RegEnable(io.reg_wr_data(16, 0), "b0".asUInt(17.W), nvdla_pdp_d_recip_kernel_width_0_wren)   
    // Register: NVDLA_PDP_D_SRC_BASE_ADDR_HIGH_0    Field: src_base_addr_high
    io.src_base_addr_high := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_src_base_addr_high_0_wren)  
    // Register: NVDLA_PDP_D_SRC_BASE_ADDR_LOW_0    Field: src_base_addr_low
    io.src_base_addr_low := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_src_base_addr_low_0_wren)   
    // Register: NVDLA_PDP_D_SRC_LINE_ST,RIDE_0    Field: src_line_stride
    io.src_line_stride := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_src_line_stride_0_wren)   
    // Register: NVDLA_PDP_D_SRC_SURFACE_STRIDE_0    Field: src_surface_stride
    io.src_surface_stride := RegEnable(io.reg_wr_data(31, 0), "b0".asUInt(32.W), nvdla_pdp_d_src_surface_stride_0_wren)   

}}