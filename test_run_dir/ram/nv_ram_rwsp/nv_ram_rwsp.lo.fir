circuit nv_ram_rwsp : @[:@2.0]
  module nv_ram_rwsp : @[:@3.2]
    input clock : Clock @[:@4.4]
    input reset : UInt<1> @[:@5.4]
    input io_re : UInt<1> @[:@6.4]
    input io_we : UInt<1> @[:@6.4]
    input io_ore : UInt<1> @[:@6.4]
    input io_ra : UInt<6> @[:@6.4]
    input io_wa : UInt<6> @[:@6.4]
    input io_pwrbus_ram_pd : UInt<32> @[:@6.4]
    input io_di : UInt<32> @[:@6.4]
    output io_dout : UInt<32> @[:@6.4]
  
    mem mem : @[nv_ram_rwsp.scala 27:22:@8.4]
      data-type => UInt<32>
      depth => 64
      read-latency => 1
      write-latency => 1
      reader => _T_29
      writer => _T_23
      read-under-write => undefined
    node _GEN_0 = validif(io_re, io_ra) @[nv_ram_rwsp.scala 34:28:@17.6]
    node _T_25 = _GEN_0 @[nv_ram_rwsp.scala 34:28:@15.6 nv_ram_rwsp.scala 34:28:@18.8]
    node _T_27 = or(_T_25, UInt<6>("h0")) @[nv_ram_rwsp.scala 34:28:@19.8]
    node _T_28 = bits(_T_27, 5, 0) @[nv_ram_rwsp.scala 34:28:@20.8]
    node _GEN_1 = mux(io_re, UInt<1>("h1"), UInt<1>("h0")) @[nv_ram_rwsp.scala 34:28:@17.6]
    node _GEN_2 = validif(io_re, _T_28) @[nv_ram_rwsp.scala 34:28:@17.6]
    node _GEN_3 = validif(io_re, clock) @[nv_ram_rwsp.scala 34:28:@17.6]
    reg _T_31 : UInt<32>, clock with :
      reset => (UInt<1>("h0"), _T_31) @[nv_ram_rwsp.scala 36:27:@24.8]
    node _GEN_4 = validif(io_ore, _T_31) @[nv_ram_rwsp.scala 35:18:@23.6]
    node _GEN_5 = validif(io_we, io_wa) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_6 = validif(io_we, clock) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_7 = mux(io_we, UInt<1>("h1"), UInt<1>("h0")) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_8 = validif(io_we, UInt<1>("h1")) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_9 = validif(io_we, io_di) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_10 = validif(eq(io_we, UInt<1>("h0")), _GEN_4) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_11 = mux(io_we, UInt<1>("h0"), _GEN_1) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_12 = validif(eq(io_we, UInt<1>("h0")), _GEN_2) @[nv_ram_rwsp.scala 29:14:@9.4]
    node _GEN_13 = validif(eq(io_we, UInt<1>("h0")), _GEN_3) @[nv_ram_rwsp.scala 29:14:@9.4]
    io_dout <= _GEN_10 @[nv_ram_rwsp.scala 36:17:@26.8]
    mem._T_29.addr <= _GEN_12 @[nv_ram_rwsp.scala 34:28:@21.8]
    mem._T_29.en <= _GEN_11 @[nv_ram_rwsp.scala 27:22:@8.4 nv_ram_rwsp.scala 34:28:@20.8]
    mem._T_29.clk <= _GEN_13 @[nv_ram_rwsp.scala 34:28:@21.8]
    mem._T_23.addr <= _GEN_5 @[:@10.6]
    mem._T_23.en <= _GEN_7 @[nv_ram_rwsp.scala 27:22:@8.4 :@10.6]
    mem._T_23.clk <= _GEN_6 @[:@10.6]
    mem._T_23.data <= _GEN_9 @[:@11.6]
    mem._T_23.mask <= _GEN_8 @[:@10.6 :@11.6]
    _T_31 <= mem._T_29.data @[nv_ram_rwsp.scala 36:27:@25.8]
