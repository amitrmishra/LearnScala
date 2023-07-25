package hashcode

object InconsistentHashCode {
  def main(args: Array[String]): Unit = {
    val hashCodeObject = new InconsistentHashCode(10)
    println(s"""hash code on first invocation:  ${hashCodeObject.hashCode()}""")
    println(s"""hash code on second invocation: ${hashCodeObject.hashCode()}""")
  }
}

class InconsistentHashCode(value: Int) {
  override def hashCode(): Int = {
    System.currentTimeMillis().toInt
  }
}
