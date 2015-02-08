package pl.etenkowski.gametheory.blotto

import scala.util.Random

class StrategyGenerator(fieldSelector: Int => Int) extends Func {
  def createStrategy(fieldCount: Int)(soldierCount: Int): Strategy =
    iterate{ a: Strategy => a.putSoldier(fieldSelector(fieldCount)) }(Strategy(fieldCount), soldierCount)
}

class UniformFieldSelector extends (Int => Int) {
  override def apply(fieldCount: Int): Int = {
    count += 1
    (count - 1) % fieldCount
  }

  private var count = 0
}

class RandomFieldSelector extends (Int => Int) {
  override def apply(fieldCount: Int): Int = {
    random nextInt fieldCount
  }

  private val random = new Random
}