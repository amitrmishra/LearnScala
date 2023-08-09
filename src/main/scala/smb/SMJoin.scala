package smb

object SMJoin {
  def main(args: Array[String]): Unit = {
    val sumOrders = List(
      SumOrders(101, 230),
      SumOrders(102, 100),
      SumOrders(104, 110)
    )
    val customers = List(
      Customer(101, "US"),
      Customer(103, "IN"),
      Customer(104, "RU"),
      Customer(106, "CH")
    )

    /*
      Implementing a full outer join
     */

    var left: Int = 0
    var rightStart: Int = 0
    var rightEnd: Int = 0

    while (left < sumOrders.length && rightStart < customers.length) {
      val leftUserId = sumOrders(left).userId
      val rightUserId = customers(rightStart).userId

      if (leftUserId < rightUserId) {
        println((leftUserId, sumOrders(left).totalOrderValue, null))
        left += 1
      } else if (leftUserId > rightUserId) {
        println((rightUserId, null, customers(rightStart).country))
        rightStart += 1
      } else {
        // Increment right pointer to cover all matching records
        rightEnd = rightStart
        while (
          rightEnd < customers.length && leftUserId == customers(
            rightEnd
          ).userId
        ) {
          println(
            (
              leftUserId,
              sumOrders(left).totalOrderValue,
              customers(rightEnd).country
            )
          )
          rightEnd += 1
        }
        // rightEnd is finally at the start of the next key group or has exhausted the right list

        // Increment left pointer to cover all matching records
        left += 1
        while (
          left < sumOrders.length && sumOrders(left).userId == leftUserId
        ) {
          for (right <- Range(rightStart, rightEnd)) {
            println(
              (
                leftUserId,
                sumOrders(left).totalOrderValue,
                customers(right).country
              )
            )
          }
          left += 1
        }

        rightStart = rightEnd
      }
    }

    // All the remaining records from the left dataset. Needed only for the Full Outer Join.
    // Till now, left is either beyond the left dataset or at the record whose id is smaller
    // than all the id(s) of the right dataset, ie. the record for which the right dataset was
    // fully iterated without having a match
    while (left < sumOrders.length) {
      println((sumOrders(left).userId, sumOrders(left).totalOrderValue, null))
      left += 1
    }

    // All the remaining records from the right dataset. Needed only for the Full Outer Join.
    // Till now, rightStart is either beyond the right dataset or at the record whose id is smaller
    // than all the id(s) of the left dataset, ie. the record for which the left dataset was
    // fully iterated without having a match
    while (rightStart < customers.length) {
      println(
        (customers(rightStart).userId, null, customers(rightStart).country)
      )
      rightStart += 1
    }

    // Worst case complexity will be O(m*n) if there are only duplicates and nothing else
    // Best case would be O(m+n) if there are no duplicates
  }

  case class SumOrders(userId: Int, totalOrderValue: Int)
  case class Customer(userId: Int, country: String)

}
