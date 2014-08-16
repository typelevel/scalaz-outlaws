package scalaz.outlaws
package std

import scala.Unit
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

trait FutureOutlawInstances {
  @deprecated("Functor[Future] breaks the composition law, https://issues.scala-lang.org/browse/SI-6284", "forever")
  implicit def futureOutlawInstances(implicit ec: ExecutionContext): Each[Future] = new Each[Future] {
    def each[A](fa: Future[A])(f: A ⇒ Unit) = fa foreach f
  }
}

object future extends FutureOutlawInstances
