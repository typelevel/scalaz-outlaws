package scalaz.outlaws
package core

import scalaz.Diev

trait DievOutlawInstances {
  implicit val dievEach = new Each[Diev] {
    def each[A](fa: Diev[A])(f: A ⇒ Unit) = fa.foldLeft[Unit](())((_, value) => f(value))
  }
}

object diev extends DievOutlawInstances
