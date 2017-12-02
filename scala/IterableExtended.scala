/*
 * Can be used like this:
 * ```
 * import my.package.IterableExtended
 * 
 * assert(Iterable(1, 2).intersperse(Iterable(3, 4)) == Iterable(1,3,2,4))
 * ```
 */
implicit class IterableExtended[T](val wrapped: Iterable[T]) extends AnyVal {
    def intersperse(that: Iterable[T]): Iterable[T] = {
      val acc = new ArrayBuffer[T]()
      val wit = wrapped.iterator
      val tit = that.iterator
      while (wit.hasNext && tit.hasNext) {
        acc += wit.next()
        acc += tit.next()
      }
      if (wit.hasNext) acc ++= wit else acc ++= tit
      acc.result()
    }
    
    def minmax[B >: T](implicit cmp: Ordering[B]): Option[(T, T)] = {
      wrapped.headOption.map { head =>
        wrapped.tail.foldLeft((head, head)) { case ((min, max), x) =>
          (if (cmp.lt(x, min)) x else min, if (cmp.gt(x, max)) x else max) }
      }
    }
  }
