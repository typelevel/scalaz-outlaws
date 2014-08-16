package scalaz.outlaws.std

import scalaz.Monoid
import scala.Double

trait DoubleOutlawInstances {
  @deprecated("Monoid[Double] violates the associativity law", "forever")
  implicit val doubleMonoid = new Monoid[Double] {
    def zero = 0D
    def append(a: Double, b: ⇒ Double) = a + b
  }
}

object double extends DoubleOutlawInstances
