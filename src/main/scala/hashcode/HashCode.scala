package hashcode

object HashCode {
  def main(args: Array[String]): Unit = {
    println(s"""hash code of  integer    10     is ${10.hashCode()}""")
    println(s"""hash code of  integer    -10    is ${-10.hashCode()}""")
    println(s"""hash code of  long       600L   is ${600L.hashCode()}""")
    println(s"""hash code of  float      3.1f   is ${3.1f.hashCode()}""")
    println(s"""hash code of  double     3.1D   is ${3.1d.hashCode()}""")
    println(s"""hash code of  character  'a'    is ${'a'.hashCode()}""")
    println(s"""hash code of  string     "abc"  is ${"abc".hashCode()}""")
  }
}
