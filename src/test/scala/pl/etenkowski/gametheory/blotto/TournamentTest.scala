package pl.etenkowski.gametheory.blotto

class TournamentTest extends UnitSpec {
  it should "calculate payments of a single match" in {
    // given
    val agr = Strategy(Vector(0, 5, 10, 70, 15, 0, 0, 0, 0))
    val paf = Strategy(Vector(1, 0, 0, 0, 95, 2, 0, 1, 1))
    // when
    val result = Tournament.matchStrategies(agr, paf)
    // then
    result should be((3.5, 5.5))
  }

  it should "calculate all payments in the tournament" in {
    // given
    val s1 = Strategy(Vector(98, 1, 0, 0, 0, 0, 0, 0, 1))
    val s2 = Strategy(Vector(0, 95, 1, 1, 1, 1, 1, 0, 0))
    val s3 = Strategy(Vector(20, 20, 30, 20, 10, 0, 0, 0, 0))
    // when
    val result = Tournament.allMatches(List(s1, s2, s3))
    // then
    result should be(List(6.0, 10.5, 10.5))
  }
}