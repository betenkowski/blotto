package pl.etenkowski.gametheory.blotto

trait Func {
  def iterate[A](f: A => A)(initialValue: A, times: Int): A =
    if (times < 1)
      initialValue
    else
      iterate(f)(f(initialValue), times - 1)
}
