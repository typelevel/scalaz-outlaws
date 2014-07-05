package scalaz.outlaws
package core

import scala.Unit
import scalaz.OneAnd

trait OneAndOutlawInstances {
  implicit def oneAndEach[F[_]: Each]: Each[({type λ[α] = OneAnd[F, α]})#λ] =
    new Each[({type λ[α] = OneAnd[F, α]})#λ] {
      def each[A](fa: OneAnd[F, A])(f: A => Unit) = {
        f(fa.head)
        Each[F].each(fa.tail)(f)
      }
    }
}

object oneAnd extends OneAndOutlawInstances

