package scalaz.outlaws
package core

import scala.Unit
import scalaz.ListT

trait ListTOutlawInstances {
  implicit def listTEach[M[_]:Each] = new Each[({type λ[α]=ListT[M,α]})#λ] {
    def each[A](fa: ListT[M, A])(f: A⇒ Unit) = Each[M].each(fa.run)(_ foreach f)
  }
}

