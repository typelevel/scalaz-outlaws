package scalaz.outlaws.std


import scalaz.outlaws.std.set._
import scala.{Int,Unit}
import scala.Predef.{Set,conforms}
import scalaz._
import scalaz.std.set._
import scalaz.std.anyVal._
import scalaz.scalacheck.ScalazProperties._
import scalaz.scalacheck.ScalazArbitrary._
import org.scalacheck._
import Arbitrary._

class SetSpec extends QuickProperties("set") {
  checkAll(monadPlus.laws[Set])
  checkAll(traverse.laws[Set])
}
