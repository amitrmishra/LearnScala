package hashcode

object HashCollision {
  def main(args: Array[String]): Unit = {
    println(s"""Hash of "Aa" is ${"Aa".hashCode}""")
    println(s"""Hash of "BB" is ${"BB".hashCode}""")
  }
}
