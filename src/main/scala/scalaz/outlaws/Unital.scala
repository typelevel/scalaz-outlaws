package scalaz.outlaws

import scalaz.{Applicative, Category, Equal, Monoid}

////
/**
  * Provides an identity element (`zero`). However it lacks the binary
  * `append` operation in [[scalaz.Semigroup]], so it is not subject
  * to the monoid laws.
  *
  * Example instances:
  *  - `Unital[Int]`: `zero` is `0`
  *  - `Unital[List[A]]`: `zero` is `Nil`
  *
  * References:
  *  - [[http://mathworld.wolfram.com/Unital.html]]
  *
  * @see [[scalaz.outlaws.syntax.UnitalOps]]
  *
  */
////
trait Unital[F] { self =>
  /** The identity element in this unital */
  def zero: F

  /** Whether `a` == `zero`. */
  def isMZero(a: F)(implicit eq: Equal[F]): Boolean =
    eq.equal(a, zero)

  final def ifEmpty[B](a: F)(t: => B)(f: => B)(implicit eq: Equal[F]): B =
    if (isMZero(a)) { t } else { f }

  final def onNotEmpty[B](a: F)(v: => B)(implicit eq: Equal[F], mb: Unital[B]): B =
    ifEmpty(a)(mb.zero)(v)

  final def onEmpty[A,B](a: F)(v: => B)(implicit eq: Equal[F], mb: Unital[B]): B =
    ifEmpty(a)(v)(mb.zero)

  ////
  val unitalSyntax = new scalaz.outlaws.syntax.UnitalSyntax[F] { def F = Unital.this }

}

object Unital {
  @inline def apply[F](implicit F: Unital[F]): Unital[F] = F

  ////

  /** Make a zero into an instance. */
  def instance[A](z: A): Unital[A] = new Unital[A] {
    val zero = z
  }

  /** Lazily make a zero into an instance. */
  def lazyInstance[A](z: => A): Unital[A] = new Unital[A] {
    lazy val zero = z
  }

  @inline implicit def monoidIsUnital[A : Monoid]: Unital[A] = new Unital[A] {
    val zero = Monoid[A].zero
  }

  private trait ApplicativeUnital[F[_], U] extends Unital[F[U]] {
    implicit def F: Applicative[F]
    implicit def U: Unital[U]
    val zero = F.point(U.zero)
  }

  /**A Unital for sequencing Applicative effects. */
  def liftUnital[F[_], U](implicit F0: Applicative[F], U0: Unital[U]): Unital[F[U]] = new ApplicativeUnital[F, U] {
    implicit def F: Applicative[F] = F0
    implicit def U: Unital[U] = U0
  }

  ////
}
