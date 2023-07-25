package smb

import scala.collection.mutable

object HashMapAggregateOptimised {
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

    val output: mutable.Map[Int, Int] = new mutable.HashMap()

    orders.foreach { order =>
      val existingOrderValue = output.getOrElse(order.userId, 0)
      output.put(order.userId, existingOrderValue + order.value)
    }

    output.foreach(println)

  }

  case class Order(userId: Int, value: Int)
}
