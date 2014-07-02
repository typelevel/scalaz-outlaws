package scalaz.outlaws.std

import scalaz.outlaws.std.set._

import scalaz._
import scalaz.std.set._
import scalaz.std.anyVal._
import scalaz.scalacheck.ScalazProperties._
import scalaz.scalacheck.ScalazArbitrary._
import org.scalacheck._

class SetSpec extends Properties("set") {
  def checkAll(props: Properties) {
    for ((name, prop) <- props.properties) yield {
      property(name) = prop
    }
  }

  checkAll(monadPlus.laws[Set])
  checkAll(traverse.laws[Set])
}
