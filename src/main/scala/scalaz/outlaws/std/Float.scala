package scalaz.outlaws.std

import scalaz.Monoid
import scala.Float

trait FloatOutlawInstances {
  @deprecated("Monoid[Float] violates the associativity law", "forever")
  implicit val floatMonoid = new Monoid[Float] {
    def zero = 0F
    def append(a: Float, b: ⇒ Float) = a + b
  }
}

object float extends FloatOutlawInstances
