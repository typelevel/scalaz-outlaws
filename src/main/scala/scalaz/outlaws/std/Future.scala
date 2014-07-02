package scalaz.outlaws
package std

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

trait FutureOutlawInstances {
  implicit def futureOutlawInstances(implicit ec: ExecutionContext): Each[Future] = new Each[Future] {
    def each[A](fa: Future[A])(f: A ⇒ Unit) = fa foreach f
  }
}

object future extends FutureOutlawInstances
