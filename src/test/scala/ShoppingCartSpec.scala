import Item._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ShoppingCartSpec extends AnyWordSpec with Matchers {

  "ShoppingCartSpec" should {
    "Correctly compute total price for valid items" in {
      val input = "Apple, Apple, Orange, Apple"
      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)

      items shouldBe List(Apple, Apple, Orange, Apple)
      total shouldBe 2.05
    }

    "Correctly compute total price ONLY for valid items" in {
      val input = "Apple, Apple, Blueberries, Orange, Apple, Grapes"

      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)

      items shouldBe List(Apple, Apple, Orange, Apple)
      total shouldBe 2.05
    }

    "Correctly compute total price when empty input" in {
      val input = ""

      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)

      ShoppingCartApp.parseItems(input) shouldBe List.empty
      total shouldBe 0
    }

    "Correctly apply Apple discount when exact promotions used" in {
      val input = "Apple, Apple"
      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)
      val finalPrice = ShoppingCartApp.finalPrice(items)

      items shouldBe List(Apple, Apple)
      total shouldBe 1.2
      finalPrice shouldBe 0.6
    }

    "Correctly apply Apple discount when promotion not meet" in {
      val input = "Apple, Apple, Apple"
      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)
      val finalPrice = ShoppingCartApp.finalPrice(items)

      items shouldBe List(Apple, Apple, Apple)
      total shouldBe 1.8
      finalPrice shouldBe 1.2
    }

    "Correctly apply Orange discount when exact promotions used" in {
      val input = "Orange, Orange, Orange"
      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)
      val finalPrice = ShoppingCartApp.finalPrice(items)

      items shouldBe List(Orange, Orange, Orange)
      total shouldBe 0.75
      finalPrice shouldBe 0.5
    }

    "Correctly apply Orange discount when promotion not meet" in {
      val input = "Orange, Orange, Orange, Orange, Orange"
      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)
      val finalPrice = ShoppingCartApp.finalPrice(items)

      items shouldBe List(Orange, Orange, Orange, Orange, Orange)
      total shouldBe 1.25
      finalPrice shouldBe 1
    }

    "Correctly apply both promotions" in {
      val input = "Apple, Apple, Apple, Orange, Orange, Orange, Orange"
      val items = ShoppingCartApp.parseItems(input)
      val total = ShoppingCartApp.total(items)
      val finalPrice = ShoppingCartApp.finalPrice(items)

      items shouldBe List(Apple, Apple, Apple, Orange, Orange, Orange, Orange)
      total shouldBe 2.8
      finalPrice shouldBe 1.95
    }
  }
}
