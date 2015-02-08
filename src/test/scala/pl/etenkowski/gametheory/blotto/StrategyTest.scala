package pl.etenkowski.gametheory.blotto

class StrategyTest extends UnitSpec {
  "Strategy object" should "create an empty Strategy" in {
    // given
    // when
    val result = Strategy(3)
    // then
    result should be(Strategy(Vector(0, 0, 0)))
  }

  it should "increment the number of soldiers in the selected field" in {
    // given
    val strategy = Strategy(Vector(1, 2, 3))
    // when
    val result = strategy putSoldier 1
    // then
    result should be(Strategy(Vector(1, 3, 3)))
  }
}
