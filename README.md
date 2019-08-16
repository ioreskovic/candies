# :candy: :candy: candies :candy: :candy:
An attempt to write an understandable, extensible, composable CAN DBC parser in Scala

Input:
```less
BA_ "GenMsgCycleTime" BO_ 1 33;

BO_ 0 MSG1: 8 Vector__XXX
 SG_ Mux1 M : 39|4@0+ (1,0) [0|0] "" Vector__XXX
 SG_ Reg1 : 16|4@1- (1,0) [0|0] "" Vector__XXX
 SG_ Mex0 m0 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
 SG_ Mex3 m3 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
 SG_ Mex11 m11 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX

BA_ "GenMsgCycleTime" BO_ 0 25;

BO_ 1 MSG2: 8 Vector__XXX
 SG_ Mux1 M : 39|4@0+ (1,0) [0|0] "" Vector__XXX
 SG_ Reg1 : 16|4@1- (1,0) [0|0] "" Vector__XXX
 SG_ Mex0 m0 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
 SG_ Mex3 m3 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
 SG_ Mex11 m11 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
```

Output:
```scala
Network(
  List(
    Message(
      0,
      MSG1,
      8,
      Vector__XXX,
      List(
        Signal(
          Reg1,
          Regular,
          16,
          4,
          Signed,
          Big,
          1.0,
          0.0,
          0.0,
          0.0,
          ,
          25 milliseconds,
          List(
            Vector__XXX
          )
        )
      ),
      Some(
        Multiplex(
          Signal(
            Mux1,
            Multiplexer,
            39,
            4,
            Unsigned,
            Little,
            1.0,
            0.0,
            0.0,
            0.0,
            ,
            25 milliseconds,
            List(
              Vector__XXX
            )
          ),
          Map(
            11 -> MuxGroup(
              11,
              List(
                Signal(
                  Mex11,
                  Multiplexed(11),
                  7,
                  16,
                  Signed,
                  Little,
                  0.1,
                  -40.0,
                  0.0,
                  0.0,
                  degC,
                  25 milliseconds,
                  List(
                    Vector__XXX
                  )
                )
              )
            ),
            3 -> MuxGroup(
              3,
              List(
                Signal(
                  Mex3,
                  Multiplexed(3),
                  7,
                  16,
                  Signed,
                  Little,
                  0.1,
                  -40.0,
                  0.0,
                  0.0,
                  degC,
                  25 milliseconds,
                  List(
                    Vector__XXX
                  )
                )
              )
            ),
            0 -> MuxGroup(
              0,
              List(
                Signal(
                  Mex0,
                  Multiplexed(0),
                  7,
                  16,
                  Signed,
                  Little,
                  0.1,
                  -40.0,
                  0.0,
                  0.0,
                  degC,
                  25 milliseconds,
                  List(
                    Vector__XXX
                  )
                )
              )
            )
          )
        )
      )
    ),
    Message(
      1,
      MSG2,
      8,
      Vector__XXX,
      List(
        Signal(
          Reg1,
          Regular,
          16,
          4,
          Signed,
          Big,
          1.0,
          0.0,
          0.0,
          0.0,
          ,
          33 milliseconds,
          List(
            Vector__XXX
          )
        )
      ),
      Some(
        Multiplex(
          Signal(
            Mux1,
            Multiplexer,
            39,
            4,
            Unsigned,
            Little,
            1.0,
            0.0,
            0.0,
            0.0,
            ,
            33 milliseconds,
            List(
              Vector__XXX
            )
          ),
          Map(
            11 -> MuxGroup(
              11,
              List(
                Signal(
                  Mex11,
                  Multiplexed(11),
                  7,
                  16,
                  Signed,
                  Little,
                  0.1,
                  -40.0,
                  0.0,
                  0.0,
                  degC,
                  33 milliseconds,
                  List(
                    Vector__XXX
                  )
                )
              )
            ),
            3 -> MuxGroup(
              3,
              List(
                Signal(
                  Mex3,
                  Multiplexed(3),
                  7,
                  16,
                  Signed,
                  Little,
                  0.1,
                  -40.0,
                  0.0,
                  0.0,
                  degC,
                  33 milliseconds,
                  List(
                    Vector__XXX
                  )
                )
              )
            ),
            0 -> MuxGroup(
              0,
              List(
                Signal(
                  Mex0,
                  Multiplexed(0),
                  7,
                  16,
                  Signed,
                  Little,
                  0.1,
                  -40.0,
                  0.0,
                  0.0,
                  degC,
                  33 milliseconds,
                  List(
                    Vector__XXX
                  )
                )
              )
            )
          )
        )
      )
    )
  )
)
```