package scalaz.outlaws.std

import java.lang.Exception
import scala.Predef.String
import scala.Boolean
import scala.util.{Success,Try,Failure}
import scalaz.outlaws.std.utilTry._
import scalaz.Equal
import scalaz.std.anyVal._
import scalaz.std.string._
import scalaz.scalacheck.ScalazProperties._
import scalaz.scalacheck.ScalazArbitrary._
import org.scalacheck._
import org.scalacheck.Arbitrary._

class TrySpec extends QuickProperties("try") {

  implicit def tryEqual[A : Equal] = new Equal[Try[A]] {
    def equal(a1: Try[A], a2: Try[A]): Boolean = (a1,a2) match {
      case (Success(a), Success(b)) ⇒ Equal[A].equal(a,b)
        // ewww
      case (Failure(a), Failure(b)) ⇒ Equal[String].equal(a.getMessage(),b.getMessage())
      case _ ⇒ false
    }
  }

  implicit def arbitraryTry[A](implicit agen: Arbitrary[A]): Arbitrary[Try[A]] = Arbitrary {
    arbitrary[Boolean] flatMap { b ⇒
      if(b) arbitrary[A].map(Success.apply)
      else arbitrary[String].map(s ⇒ Failure(new Exception(s)))
    }
  }

  checkAll(monad.laws[Try])
  checkAll(plus.laws[Try])
  checkAll(traverse.laws[Try])
}
