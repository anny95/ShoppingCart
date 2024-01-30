
trait Promotion {
  def discount(cart: Seq[Item]): BigDecimal
}

case object ApplePromotion extends Promotion {
  def discount(cart: Seq[Item]): BigDecimal = cart.count(_ == Item.Apple) / 2 * Item.Apple.price
}

case object OrangePromotion extends Promotion {
  def discount(cart: Seq[Item]): BigDecimal = cart.count(_ == Item.Orange) / 3 * Item.Orange.price
}