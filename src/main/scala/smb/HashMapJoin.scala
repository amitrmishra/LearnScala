package smb

import scala.collection.mutable

object HashMapJoin {
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

    val customersMap: mutable.Map[Int, List[String]] = new mutable.HashMap()
    customers.foreach { customer =>
      customersMap.put(
        customer.userId,
        customersMap.getOrElse(customer.userId, List.empty) :+ customer.country
      )
    }

    sumOrders.foreach { sumOrder =>
      val countries = customersMap.getOrElse(sumOrder.userId, List.empty)
      if (countries.isEmpty)
        println((sumOrder.userId, sumOrder.totalOrderValue, null))
      else
        countries.foreach(country =>
          println((sumOrder.userId, sumOrder.totalOrderValue, country))
        )
    }

    // We are not sure if left dataset can fit into the memory
    //   The id(s) of the left dataset which have not matched have already been produced in the
    //     output, and so they need not be considered
    //   Pick *only* the id(s) from the left dataset which have matched and compare if there is any
    //     id in the right dataset which doesn't match. For those id(s), output the result with
    //     left side being null
    //   No need to scan the left side for each key in the right dataset, rather we pick all the
    //     relevant keys in the memory as Set.
    //
    // == Calculation ==
    //
    // Let's say we have 1Bn records each with 1KB size.
    // Total size of the file = 1TB (1 * 10^9 * 10^3)
    // Say, we all the 100% of the keys matching the right side
    // Means, for 1Bn such integer keys where each integer takes 16 bytes of memory in Java,
    //   16 GB (16 * 10^9) of RAM will be needed.
    // We are ignoring the memory footprint of right dataset as it should be much smaller than
    //   16GB, ideally in MBs.
    //
    // Right outer join could be (full_outer_join where right_value is not null)
    (customersMap.keySet diff sumOrders
      .map(_.userId)
      .filter(customersMap.contains)
      .toSet).foreach { userId =>
      customersMap(userId).foreach { country =>
        println((userId, null, country))
      }
    }
  }

  case class SumOrders(userId: Int, totalOrderValue: Int)
  case class Customer(userId: Int, country: String)
}
