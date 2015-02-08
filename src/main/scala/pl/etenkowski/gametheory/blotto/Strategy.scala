package pl.etenkowski.gametheory.blotto

case class Strategy(soldiers: Vector[Int]) {
  def putSoldier(field: Int): Strategy = {
    val newCount = soldiers(field) + 1
    Strategy(soldiers.updated(field, newCount))
  }
}

object Strategy {
  def apply(fieldCount: Int): Strategy = Strategy(Vector.fill(fieldCount)(0))
}
