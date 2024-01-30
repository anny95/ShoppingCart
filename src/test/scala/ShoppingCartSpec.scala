import Item._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ShoppingCartSpec extends AnyWordSpec with Matchers {

  "ShoppingCartSpec Split input" should {
    "succeed for valid items" in {
      val input = "Apple, Apple, Orange, Apple"

      ShoppingCartApp.parseItems(input) shouldBe List(Apple, Apple, Orange, Apple)
    }

    "succeed for valid items and print invalid items" in {
      val input = "Apple, Apple, Blueberries, Orange, Apple, Grapes"

      ShoppingCartApp.parseItems(input) shouldBe List(Apple, Apple, Orange, Apple)
    }

    "succeed for empty input" in {
      val input = ""

      ShoppingCartApp.parseItems(input) shouldBe List.empty
    }
  }

  "ShoppingCartSpec Compute total price" should {
    "succeed for valid items" in {
      val input = "Apple, Apple, Orange, Apple"

      ShoppingCartApp.total(input) shouldBe 2.05
    }

    "succeed ONLY using valid items" in {
      val input = "Apple, Apple, Blueberries, Orange, Apple, Grapes"

      ShoppingCartApp.total(input) shouldBe 2.05
    }

    "return 0 if input is empty" in {
      val input = ""

      ShoppingCartApp.total(input) shouldBe 0
    }
  }
}
