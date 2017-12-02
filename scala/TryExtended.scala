/*
 * Can be used like this:
 * ```
 * import my.package.TryExtended
 * 
 * assert(Try(1).zip(Try("2")) == Try(1,"2"))
 * ```
 */
implicit class TryExtended[+T](val wrapped: Try[T]) extends AnyVal {
  def zip[That](that: => Try[That]): Try[(T, That)] =
    for (a <- wrapped; b <- that) yield (a, b)
}

object TryExtended {
  // Similar to Future.sequence, but for Try
  def sequence[T](tries: Iterable[Try[T]]): Try[Seq[T]] =  {
    val acc = new ArrayBuffer[T]()
    tries.foreach { t =>
      if (t.isFailure) return t.map(_ => ArrayBuffer.empty)
      acc += t.get
    }
    Success(acc)
  }
}
