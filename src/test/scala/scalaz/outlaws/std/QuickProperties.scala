package scalaz.outlaws.std

import scala.Predef.String
import scala.Unit
import org.scalacheck._

abstract class QuickProperties(name: String) extends Properties(name) {
  def checkAll(props: Properties): Unit = {
    val _ = props.properties map { case (name, prop) ⇒ property(name) = prop }
  }
}
