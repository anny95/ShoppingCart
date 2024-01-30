import cats.data.Validated
import enumeratum.values.{StringEnum, StringEnumEntry}

sealed abstract class Item(val value: String, val price: Double) extends StringEnumEntry

object Item extends StringEnum[Item] {

  final case object Apple extends Item("apple", 0.6)

  final case object Orange extends Item("orange", 0.25)

  def fromString(s: String): Validated[ItemNotAvailable, Item] =
    Validated.fromEither(withValueEither(s.toLowerCase).left.map(e => ItemNotAvailable(e.notFoundValue)))

  val values:IndexedSeq[Item] = findValues
}

case class ItemNotAvailable(item: String) extends
  Exception(s"Item: '$item' is unsupported. It will be disregarded from the following processes.")