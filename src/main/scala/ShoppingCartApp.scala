import cats.data.Validated
import scala.io.StdIn.readLine

/**
 * This application makes the assuption that the scanned products are passed as input separated by a comma.
 * It will remove all trailing and leading white spaces.
 * valid example: Apple  , Apple  , Orange  , Apple
 * */

object ShoppingCartApp extends App {

  def parseItems(input: String): List[Item] = {
    val items = input.split(",")
    val cleanedItems = items.map(_.stripTrailing().stripLeading())

    cleanedItems.map(Item.fromString).flatMap {
      case Validated.Valid(item) => Some(item)
      case Validated.Invalid(error) =>
        println(error.toString)
        None
    }.toList
  }

  def total(input: String): Double = parseItems(input).map(_.price).sum

  val input = readLine()
  println(s"Total price is: £${total(input)}")

}

