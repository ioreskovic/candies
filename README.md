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
  Map(
    Id(0) -> Message(
      Definition(
        Id(0),
        Name(MSG1),
        Length(8),
        List(
          Producer(Vector__XXX)
        )
      ),
      List(
        Signal(
          Name(Mux1),
          Multiplexer,
          Offset(39),
          Length(4),
          Factor(1.0),
          Addend(0.0),
          Min(0.0),
          Max(0.0),
          TheUnit(),
          Unsigned,
          Little,
          List(
            Consumer(Vector__XXX)
          )
        ),
        Signal(
          Name(Reg1),
          Regular,
          Offset(16),
          Length(4),
          Factor(1.0),
          Addend(0.0),
          Min(0.0),
          Max(0.0),
          TheUnit(),
          Signed,
          Big,
          List(
            Consumer(Vector__XXX)
          )
        ),
        Signal(
          Name(Mex0),
          Multiplexed(0),
          Offset(7),
          Length(16),
          Factor(0.1),
          Addend(-40.0),
          Min(0.0),
          Max(0.0),
          TheUnit(degC),
          Signed,
          Little,
          List(
            Consumer(Vector__XXX)
          )
        ),
        Signal(
          Name(Mex3),
          Multiplexed(3),
          Offset(7),
          Length(16),
          Factor(0.1),
          Addend(-40.0),
          Min(0.0),
          Max(0.0),
          TheUnit(degC),
          Signed,
          Little,
          List(
            Consumer(Vector__XXX)
          )
        ),
        Signal(
          Name(Mex11),
          Multiplexed(11),
          Offset(7),
          Length(16),
          Factor(0.1),
          Addend(-40.0),
          Min(0.0),
          Max(0.0),
          TheUnit(degC),
          Signed,
          Little,
          List(
            Consumer(Vector__XXX)
          )
        )
      )
    ),
    Id(1) -> Message(
      Definition(Id(1), Name(MSG2), Length(8), List(Producer(Vector__XXX))),
      List(
        Signal(
          Name(Mux1),
          Multiplexer,
          Offset(39),
          Length(4),
          Factor(1.0),
          Addend(0.0),
          Min(0.0),
          Max(0.0),
          TheUnit(),
          Unsigned,
          Little,
          List(Consumer(Vector__XXX))),
        Signal(
          Name(Reg1),
          Regular,
          Offset(16),
          Length(4),
          Factor(1.0),
          Addend(0.0),
          Min(0.0),
          Max(0.0),
          TheUnit(),
          Signed,
          Big,
          List(Consumer(Vector__XXX))),
        Signal(
          Name(Mex0),
          Multiplexed(0),
          Offset(7),
          Length(16),
          Factor(0.1),
          Addend(-40.0),
          Min(0.0),
          Max(0.0),
          TheUnit(degC),
          Signed,
          Little,
          List(Consumer(Vector__XXX))),
        Signal(
          Name(Mex3),
          Multiplexed(3),
          Offset(7),
          Length(16),
          Factor(0.1),
          Addend(-40.0),
          Min(0.0),
          Max(0.0),
          TheUnit(degC),
          Signed,
          Little,
          List(Consumer(Vector__XXX))),
        Signal(
          Name(Mex11),
          Multiplexed(11),
          Offset(7),
          Length(16),
          Factor(0.1),
          Addend(-40.0),
          Min(0.0),
          Max(0.0),
          TheUnit(degC),
          Signed,
          Little,
          List(Consumer(Vector__XXX)))
      )
    )
  ),
  Map(
    Id(1) -> Cycle(Id(1), 33 milliseconds),
    Id(0) -> Cycle(Id(0), 25 milliseconds)
  )
)
```