package scalaz.outlaws
package std

import scala.{Boolean,Unit}
import scala.Predef.Set
import scalaz.{Traverse,MonadPlus,Applicative}

trait SetOutlawInstances {
  implicit val setOutlawInstnace: Traverse[Set] with MonadPlus[Set] with Each[Set] =
    new Traverse[Set] with MonadPlus[Set] with Each[Set] {

      def each[A](fa: Set[A])(f: A => Unit) = fa foreach f

      override def map[A,B](fa: Set[A])(f: A ⇒ B) = fa map f

      def bind[A, B](fa: Set[A])(f: A ⇒ Set[B]) = fa flatMap f
      def point[A](a: ⇒ A) = Set(a)
      def empty[A] = Set.empty[A]
      def plus[A](a: Set[A], b: ⇒ Set[A]) = a ++ b

      override def filter[A](fa: Set[A])(f: A ⇒ Boolean) = fa filter f
      
      def traverseImpl[F[_], A, B](fa: Set[A])(f: A ⇒ F[B])(implicit F: Applicative[F]) : F[Set[B]] = {
        fa.foldLeft[F[Set[B]]](F.point(empty[B])) { (s, a) ⇒
          F.apply2(s, f(a))(_ + _)
        }
      }
    }
}

object set extends SetOutlawInstances
