package smb

object BruteForceJoin {
  def main(args: Array[String]): Unit = {
    val sumOrders = List(
      SumOrders(101, 230),
      SumOrders(102, 100),
      SumOrders(103, 220),
      SumOrders(103, 230),
      SumOrders(104, 110)
    )
    val customers = List(
      Customer(101, "US"),
      Customer(103, "IN"),
      Customer(103, "RU"),
      Customer(105, "CH")
    )

    // Account for the inner and the left outer join
    sumOrders.foreach { sumOrder =>
      var matchFound: Boolean = false
      customers.foreach { customer =>
        if (sumOrder.userId == customer.userId) {
          println((sumOrder.userId, sumOrder.totalOrderValue, customer.country))
          matchFound = true
        }
      }
      if (!matchFound) {
        println((sumOrder.userId, sumOrder.totalOrderValue, null))
      }
    }

    // Account for the right outer join
    customers.foreach { customer =>
      var matchFound: Boolean = false
      sumOrders.foreach { sumOrder =>
        if (sumOrder.userId == customer.userId)
          matchFound = true
      }
      if (!matchFound) {
        println((customer.userId, null, customer.country))
      }
    }
  }

  case class SumOrders(userId: Int, totalOrderValue: Int)
  case class Customer(userId: Int, country: String)
}
