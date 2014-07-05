package scalaz.outlaws
package syntax

import scala.Unit
import scalaz.Unapply

/** Wraps a value `self` and provides methods related to `Each` */
sealed abstract class EachOps[F[_],A] {
  def self: F[A]
  implicit def F: Each[F]

  final def foreach(f: A => Unit): Unit = F.each(self)(f)
}

sealed trait ToEachOps0 {
  implicit def ToEachOpsUnapply[FA](v: FA)(implicit F0: Unapply[Each, FA]) =
    new EachOps[F0.M,F0.A] {
      def self = F0(v)
      implicit def F: Each[F0.M] = F0.TC
    }
}

trait ToEachOps extends ToEachOps0 {
  implicit def ToEachOps[F[_],A](v: F[A])(implicit F0: Each[F]) =
    new EachOps[F,A] {
      def self = v
      implicit def F: Each[F] = F0
    }
}

trait EachSyntax[F[_]] {
  implicit def ToEachOps[A](v: F[A]): EachOps[F, A] = new EachOps[F,A] {
    def self = v
    implicit def F: Each[F] = EachSyntax.this.F
  }
  def F: Each[F]
}
