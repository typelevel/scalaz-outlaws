package scalaz.outlaws
package core

import scala.Unit
import scalaz.LazyEitherT

trait LazyEitherTOutlawInstances {
  implicit def lazyEitherTEach[F[_]: Each,A,B] = new Each[({type λ[β]=LazyEitherT[F, A, β]})#λ] {
    def each[B](fa: LazyEitherT[F,A,B])(f: B ⇒ Unit): Unit = Each[F].each(fa.run)(_.fold(_ ⇒ (), {b ⇒ f(b)}))
  }
}

object lazyEitherT extends LazyEitherTOutlawInstances
