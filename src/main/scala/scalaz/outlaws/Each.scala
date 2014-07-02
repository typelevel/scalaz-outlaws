package scalaz.outlaws

/**
 * Run a side effect once for each `A` in the `F`.
 */
trait Each[F[_]]  { 
  def each[A](fa: F[A])(f: A => Unit)

  val eachSyntax = new syntax.EachSyntax[F] { def F = Each.this }
}

object Each {
  @inline def apply[F[_]](implicit F: Each[F]): Each[F] = F
}

import scalaz.Isomorphism._

trait IsomorphismEach[F[_], G[_]] extends Each[F] {
  implicit def G: Each[G]

  def iso: F <~> G

  def each[A](fa: F[A])(f: A => Unit) = G.each(iso.to(fa))(f)
}
