# Shapeless library snippets
Polymorphic mapper from `T` to `Option[T]`, which converts `null` to `None` and non-null `value` to `Some(value)`:
```scala
import shapeless._

object ToOption extends Poly1 {
  implicit def anyCase[T]: Case.Aux[T, Option[T]] =
    at(a => Option(a))
}
(1 :: true :: (null: String) :: HNil).map(ToOption)  // will be Some(1) :: Some(true) :: None :: HNil
```
Polymorphic function to convert hlist head to Option type, which converts null to None and non-null value to Some(value):
```scala
object HeadToOpt extends Poly1 {
  implicit def hnilCase = at[HNil](identity)

  implicit def hconsCase[H, T <: HList, BT <: HList]
  (implicit tailCase: Case.Aux[T, BT]) =
    at[H::T](l => Option(l.head) :: l.tail)
}
assert(HeadToOpt(list) == Option(list.head) :: list.tail)
```
