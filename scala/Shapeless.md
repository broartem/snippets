# Shapeless library snippets
Polymorphic mapper from `T` to `Option[T]`, which converts null to None and non-null value to Some(value):
```scala
import shapeless._

object ToOption extends Poly1 {
  implicit def anyCase[T]: Case.Aux[T, Option[T]] =
    at(a => Option(a))
}
(1 :: true :: (null: String) :: HNil).map(ToOption)  // will be Some(1) :: Some(true) :: None :: HNil
```
