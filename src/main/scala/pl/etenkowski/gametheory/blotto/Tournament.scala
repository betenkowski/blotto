package pl.etenkowski.gametheory.blotto

object Tournament {
  def matchStrategies(s1: Strategy, s2: Strategy): (Double, Double) =
    s1.soldiers zip s2.soldiers map matchFields reduce((acc, cur) => (acc._1 + cur._1, acc._2 + cur._2))

  def allMatches(strategies: Seq[Strategy]): Seq[Double] = {
    for (s1 <- strategies) yield {
      val autoResult = matchStrategies(s1, s1)._1
      val allResults = strategies map (matchStrategies(s1, _)._1)
      allResults.sum - autoResult
    }
  }

  def tournamentWinner(strategies: Seq[Strategy]): (Strategy, Double) = {
    val result = allMatches(strategies)
    strategies zip result maxBy(_._2)
  }

  def placeInRandomTournament(strategy: Strategy)(strategyCount: Int): Int = {
    val fieldCount = strategy.soldiers.length
    val soldierCount = strategy.soldiers.sum
    val strategies = strategy +: createRandomStrategies(fieldCount, soldierCount, strategyCount)
    val results = allMatches(strategies)
    val sortedResults = strategies zip results sortBy(- _._2)
    (sortedResults indexWhere (_._1 == strategy)) + 1
  }

  def bestRandomStrategy(fieldCount: Int, soldierCount: Int)(strategyCount: Int): (Strategy, Double) = {
    val strategies = createRandomStrategies(fieldCount, soldierCount, strategyCount)
    tournamentWinner(strategies)
  }

  def twoPhaseBestRandomStrategy(fieldCount: Int, soldierCount: Int)(firstPhaseSize: Int, strategyCount: Int): (Strategy, Double) = {
    val brs = bestRandomStrategy(fieldCount, soldierCount) _
    val firstPhaseWinners = 1 to firstPhaseSize map (_ => brs(strategyCount)._1)
    tournamentWinner(firstPhaseWinners)
  }

  private def createRandomStrategies(fieldCount: Int, soldierCount: Int, strategyCount: Int) = {
    val randomFieldSelector = new RandomFieldSelector
    val strategyGenerator = new StrategyGenerator(randomFieldSelector).createStrategy(fieldCount) _
    1 to strategyCount map (_ => strategyGenerator(soldierCount))
  }

  private def matchFields(fields: (Int, Int)): (Double, Double) = {
    val (f1, f2) = fields
    if (f1 > f2)
      (1.0, 0.0)
    else if (f1 == f2)
      (0.5, 0.5)
    else
      (0.0, 1.0)
  }
}
