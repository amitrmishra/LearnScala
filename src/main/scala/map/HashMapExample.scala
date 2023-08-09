package map

import scala.collection.immutable.HashMap

object HashMapExample {
  def main(args: Array[String]): Unit = {
    val hashMap: HashMap[Int, String] = HashMap(
      101 -> "US",
      102 -> "EN"
    )
    println(s"Country for user 101 is: ${hashMap.get(101)}")
    println(s"Country for user 103 is: ${hashMap.get(103)}")
  }
}
