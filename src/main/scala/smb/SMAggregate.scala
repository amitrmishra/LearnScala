package smb

object SMAggregate {

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

    var currentUserId: Int = sortedOrders.head.userId
    var currentSumOrders: Int = sortedOrders.head.value

    for (order <- sortedOrders.tail) {
      if (order.userId == currentUserId) {
        currentSumOrders += order.value
      } else {
        println(s"${(currentUserId, currentSumOrders)}")
        currentUserId = order.userId
        currentSumOrders = order.value
      }
    }
    println((currentUserId, currentSumOrders))
  }

  case class Order(userId: Int, value: Int)
}
