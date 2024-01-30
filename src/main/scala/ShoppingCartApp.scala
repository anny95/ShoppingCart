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

  def total(items: List[Item]): BigDecimal = items.map(_.price).sum

  def finalPrice(items: List[Item]): BigDecimal = {
    val discount = ApplePromotion.discount(items) + OrangePromotion.discount(items)
    total(items) - discount
  }


  val input = readLine()
  val items = parseItems(input)
  println(s"Final price is: £${finalPrice(items)}")
}

