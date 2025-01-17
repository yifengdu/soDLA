package nvdla

import chisel3._
import chisel3.experimental._

class NV_NVDLA_CMAC_CORE_rt_in(useRealClock:Boolean = false)(implicit val conf: cmacConfiguration) extends Module {
    val io = IO(new Bundle {
        //clock
        val nvdla_core_clk = Input(Clock())

        // sc2mac dat&wt
        val sc2mac_dat_data = Input(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)))
        val sc2mac_dat_mask = Input(Vec(conf.CMAC_ATOMC, Bool()))
        val sc2mac_dat_pd = Input(UInt(9.W))
        val sc2mac_dat_pvld = Input(Bool())

        val sc2mac_wt_data = Input(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)))
        val sc2mac_wt_mask = Input(Vec(conf.CMAC_ATOMC, Bool()))
        val sc2mac_wt_sel = Input(Vec(conf.CMAC_ATOMK_HALF, Bool()))
        val sc2mac_wt_pvld = Input(Bool())
        
        // in dat&wt 
        val in_dat_data = Output(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)))
        val in_dat_mask = Output(Vec(conf.CMAC_ATOMC, Bool()))
        val in_dat_pd = Output(UInt(9.W))
        val in_dat_pvld = Output(Bool())
        val in_dat_stripe_st = Output(Bool())
        val in_dat_stripe_end = Output(Bool())

        val in_wt_data = Output(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)))
        val in_wt_mask = Output(Vec(conf.CMAC_ATOMC, Bool()))
        val in_wt_sel = Output(Vec(conf.CMAC_ATOMK_HALF, Bool()))
        val in_wt_pvld = Output(Bool())

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
    val internal_clock = if(useRealClock) io.nvdla_core_clk else clock

    class rt_inImpl{

    // retiming init

    val in_rt_dat_data_d = retiming(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)), conf.CMAC_IN_RT_LATENCY)
    val in_rt_dat_mask_d = Wire(Vec(conf.CMAC_ATOMC, Bool())) +: 
                            Seq.fill(conf.CMAC_IN_RT_LATENCY)(RegInit(VecInit(Seq.fill(conf.CMAC_ATOMC)(false.B)))) 
    val in_rt_dat_pvld_d = Wire(Bool()) +: 
                            Seq.fill(conf.CMAC_IN_RT_LATENCY)(RegInit(false.B))
    val in_rt_dat_pd_d = Wire(UInt(9.W)) +: 
                            Seq.fill(conf.CMAC_IN_RT_LATENCY)(RegInit("b0".asUInt(9.W)))


    val in_rt_wt_data_d = retiming(Vec(conf.CMAC_ATOMC, UInt(conf.CMAC_BPE.W)), conf.CMAC_IN_RT_LATENCY)
    val in_rt_wt_mask_d = Wire(Vec(conf.CMAC_ATOMC, Bool())) +: 
                            Seq.fill(conf.CMAC_IN_RT_LATENCY)(RegInit(VecInit(Seq.fill(conf.CMAC_ATOMC)(false.B)))) 
    val in_rt_wt_pvld_d = Wire(Bool()) +: 
                            Seq.fill(conf.CMAC_IN_RT_LATENCY)(RegInit(false.B))
    val in_rt_wt_sel_d =  Wire(Vec(conf.CMAC_ATOMK_HALF, Bool())) +: 
                            Seq.fill(conf.CMAC_IN_RT_LATENCY)(RegInit(VecInit(Seq.fill(conf.CMAC_ATOMK_HALF)(false.B)))) 

    // assign input

    in_rt_dat_pvld_d(0) := io.sc2mac_dat_pvld
    in_rt_dat_mask_d(0) := io.sc2mac_dat_mask
    in_rt_dat_pd_d(0) := io.sc2mac_dat_pd
    in_rt_wt_pvld_d(0) := io.sc2mac_wt_pvld
    in_rt_wt_mask_d(0) := io.sc2mac_wt_mask
    in_rt_wt_sel_d(0) := io.sc2mac_wt_sel

    in_rt_dat_data_d(0) := io.sc2mac_dat_data
    in_rt_wt_data_d(0) := io.sc2mac_wt_data

    //==========================================================
    // Retiming flops,add latency.
    //==========================================================   
    for(t <- 0 to conf.CMAC_IN_RT_LATENCY-1){
        in_rt_dat_pvld_d(t+1) := in_rt_dat_pvld_d(t)
        when(in_rt_dat_pvld_d(t)|in_rt_dat_pvld_d(t+1)){
            in_rt_dat_mask_d(t+1) := in_rt_dat_mask_d(t)
            in_rt_dat_pd_d(t+1) := in_rt_dat_pd_d(t)
        }

        in_rt_wt_pvld_d(t+1) := in_rt_wt_pvld_d(t)
        when(in_rt_wt_pvld_d(t)|in_rt_wt_pvld_d(t+1)){
            in_rt_wt_mask_d(t+1) := in_rt_wt_mask_d(t)
            in_rt_wt_sel_d(t+1) := in_rt_wt_sel_d(t)           
        }
        for(i <- 0 to conf.CMAC_ATOMC-1){
            when(in_rt_dat_mask_d(t)(i)){
                in_rt_dat_data_d(t+1)(i) := in_rt_dat_data_d(t)(i)                   
            }
            when(in_rt_wt_mask_d(t)(i)){
                in_rt_wt_data_d(t+1)(i) := in_rt_wt_data_d(t)(i) 
            }  
        }
    }          

    //assign output

    io.in_dat_pvld := in_rt_dat_pvld_d(conf.CMAC_IN_RT_LATENCY)
    io.in_dat_mask := in_rt_dat_mask_d(conf.CMAC_IN_RT_LATENCY)
    io.in_dat_pd := in_rt_dat_pd_d(conf.CMAC_IN_RT_LATENCY)
    io.in_wt_pvld := in_rt_wt_pvld_d(conf.CMAC_IN_RT_LATENCY)
    io.in_wt_mask := in_rt_wt_mask_d(conf.CMAC_IN_RT_LATENCY)
    io.in_wt_sel := in_rt_wt_sel_d(conf.CMAC_IN_RT_LATENCY)
    io.in_dat_data := in_rt_dat_data_d(conf.CMAC_IN_RT_LATENCY)
    io.in_wt_data := in_rt_wt_data_d(conf.CMAC_IN_RT_LATENCY)

    io.in_dat_stripe_st := io.in_dat_pd(conf.PKT_nvdla_stripe_info_stripe_st_FIELD)
    io.in_dat_stripe_end := io.in_dat_pd(conf.PKT_nvdla_stripe_info_stripe_end_FIELD)
    }

    val rt_in = withClock(internal_clock){new rt_inImpl}

}

object NV_NVDLA_CMAC_CORE_rt_inDriver extends App {
  implicit val conf: cmacConfiguration = new cmacConfiguration
  chisel3.Driver.execute(args, () => new NV_NVDLA_CMAC_CORE_rt_in(useRealClock = true))
}

    
    

