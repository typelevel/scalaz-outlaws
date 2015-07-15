package scalaz.outlaws
package syntax

import scalaz.Equal
import scalaz.syntax.Ops

/** Wraps a value `self` and provides methods related to `Unital` */
final class UnitalOps[F] private[syntax](val self: F)(implicit val F: Unital[F]) extends Ops[F] {
  ////
  final def ifEmpty[A](tv: => A)(fv: => A)(implicit e: Equal[F]): A = F.ifEmpty(self)(tv)(fv)

  final def isMZero(implicit e: Equal[F]): Boolean = F.isMZero(self)

  final def onNotEmpty[A](v: => A)(implicit ma: Unital[A], e: Equal[F]): A = F.onNotEmpty(self)(v)

  final def onEmpty[A](v: => A)(implicit ma: Unital[A], e: Equal[F]): A = F.onEmpty(self)(v)
  ////
}

trait ToUnitalOps {
  implicit def ToUnitalOps[F](v: F)(implicit F0: Unital[F]) =
    new UnitalOps[F](v)

  ////

  def mzero[F](implicit F: Unital[F]): F = F.zero
  def ∅[F](implicit F: Unital[F]): F = F.zero
  ////
}

trait UnitalSyntax[F] {
  implicit def ToUnitalOps(v: F): UnitalOps[F] = new UnitalOps[F](v)(UnitalSyntax.this.F)

  def F: Unital[F]
  ////
  def mzero(implicit F: Unital[F]): F = F.zero
  def ∅(implicit F: Unital[F]): F = F.zero
  ////
}
