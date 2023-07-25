package smb

object HashMapAggregateNaive {
  // Instead of printing result, it may be saved to a storage like a file or a DB
  def main(args: Array[String]): Unit = {
    val orders = List(
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

    val ordersGroupedByUserId = orders.groupBy(order => order.userId)

    val sumOrdersByUserId = ordersGroupedByUserId
      .map { case (userId: Int, ordersByUser: List[Order]) =>
        (userId, ordersByUser.map(_.value).sum)
      }

    sumOrdersByUserId.foreach(println)
  }

  case class Order(userId: Int, value: Int)

}
