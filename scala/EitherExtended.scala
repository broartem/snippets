object EitherExtended {
  def sequence[L, R](eis: Seq[Either[L, R]]): Either[Seq[L], Seq[R]] = {
    val lefts: mutable.ArrayBuffer[L] = mutable.ArrayBuffer[L]()
    val rights: mutable.ArrayBuffer[R] = mutable.ArrayBuffer[R]()

    eis.foreach { eith =>
      if (eith.isLeft) {
        lefts += eith.left.get
      } else {
        rights += eith.right.get
      }
    }
    if (lefts.nonEmpty) Left(lefts) else Right(rights)
  }

  def rights[L, R](eis: Seq[Either[L, R]]): Seq[R] = {
    val rights: mutable.ArrayBuffer[R] = mutable.ArrayBuffer[R]()
    eis.foreach { eith => if (eith.isRight) rights += eith.right.get }
    rights
  }

  def lefts[L, R](eis: Seq[Either[L, R]]): Seq[L] = {
    val lefts: mutable.ArrayBuffer[L] = mutable.ArrayBuffer[L]()
    eis.foreach { eith => if (eith.isLeft) lefts += eith.left.get }
    lefts
  }
}
