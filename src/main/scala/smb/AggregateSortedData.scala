package smb

object AggregateSortedData {

  def main(args: Array[String]): Unit = {
    val orders = List[Order](
      Order(101, 100),
      Order(102, 120),
      Order(101, 140),
      Order(108, 200),
      Order(103, 320),
      Order(106, 100),
      Order(104, 180),
      Order(104, 180),
      Order(109, 200),
      Order(108, 140),
      Order(107, 150),
      Order(110, 100),
      Order(109, 200),
      Order(102, 300),
      Order(109, 150)
    )

    if (orders.isEmpty) return

    val sortedOrders = orders.sortBy(_.userId)

    var previousUserId: Int = sortedOrders.head.userId
    var sumOrders: Int = sortedOrders.head.value

    for (order <- sortedOrders.tail) {
      if (order.userId == previousUserId) {
        sumOrders += order.value
      } else {
        println(s"${(previousUserId, sumOrders)}")
        previousUserId = order.userId
        sumOrders = order.value
      }
    }
    println((previousUserId, sumOrders))
  }

  case class Order(userId: Int, value: Int)
}
