package scalaz.outlaws
package std

import scala.util.{Try,Success,Failure}
import scalaz.{Applicative,Monad,Plus,Traverse}

trait TryOutlawInstances {
  implicit val tryOutlawInstances = new Traverse[Try] with Monad[Try] with Plus[Try]{
    def point[A](a: ⇒ A): Try[A] = Success(a)
    override def map[A,B](fa: Try[A])(f: A ⇒ B) = fa map f
    def bind[A,B](fa: Try[A])(f: A ⇒ Try[B]) = fa flatMap f
    def traverseImpl[F[_], A, B](fa: Try[A])(f: A ⇒ F[B])(implicit F: Applicative[F]) : F[Try[B]] = fa match {
      case Success(a) ⇒ F.map(f(a))(Success.apply)
      case Failure(f) ⇒ F.point(Failure(f))
    }
    def plus[A](a: Try[A], b: ⇒ Try[A]) = a orElse b
  }

}

object utilTry extends TryOutlawInstances
