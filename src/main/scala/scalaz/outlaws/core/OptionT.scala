package scalaz.outlaws
package core

import scalaz.OptionT

trait OptionTOutlawInstances {
  implicit def optionTEach[F[_] : Each] = new Each[({type λ[α]=OptionT[F,α]})#λ] {
    def each[A](fa: OptionT[F,A])(f: A ⇒ Unit) = Each[F].each(fa.run)(_ foreach f)
  }
}

