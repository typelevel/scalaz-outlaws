package scalaz.outlaws
package core

import scalaz.\/

trait EitherOutlawInstances {
  implicit def eitherEach[A] = new Each[({type λ[β]=A\/β})#λ] {
    def each[B](fa: A \/ B)(f: B ⇒ Unit): Unit = fa.fold(_ ⇒ (), f)
  }
}

object either extends EitherOutlawInstances

