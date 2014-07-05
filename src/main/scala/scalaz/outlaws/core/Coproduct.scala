package scalaz.outlaws
package core

import scala.Unit
import scalaz.Coproduct

trait CoproductOutlawInstances {
  implicit def coproductEach[F[_]: Each, G[_]: Each] = new Each[({type λ[α] = Coproduct[F, G, α]})#λ] {
    def each[A](fa: Coproduct[F, G, A])(f: A ⇒ Unit): Unit =
      fa.run.fold(Each[F].each(_)(f), Each[G].each(_)(f))
  }
}

object each extends CoproductOutlawInstances

