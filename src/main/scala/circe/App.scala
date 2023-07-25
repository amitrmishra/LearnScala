package circe

import cats.syntax.functor._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object App {
  def main(args: Array[String]): Unit = {
    val foo: Foo = QuxV1(13)
//    val foo: Foo = QuxV2(13, Some(1))
//    val foo: Foo = QuxV2(13, None)
//    val foo: Foo = QuxV3(13, 1)
//    val foo: Foo = QuxV3(13, null)

    val json = foo.asJson.noSpaces
    println(json)

    val decodedQuxV1 = decode[QuxV1](json)
    println(decodedQuxV1)

    val decodedQuxV2 = decode[QuxV2](json)
    println(decodedQuxV2)

    val decodedQuxV3 = decode[QuxV3](json)
    println(decodedQuxV3)
  }

  implicit val encodeFoo: Encoder[Foo] = Encoder.instance {
    case bar @ Bar(_)        => bar.asJson
    case quxV1 @ QuxV1(_)    => quxV1.asJson
    case quxV2 @ QuxV2(_, _) => quxV2.asJson
    case quxV3 @ QuxV3(_, _) => quxV3.asJson
  }

  implicit val decodeFoo: Decoder[Foo] =
    List[Decoder[Foo]](
//      Decoder[Foo].widen,
      Decoder[Bar].widen,
      Decoder[QuxV1].widen,
      Decoder[QuxV2].widen,
      Decoder[QuxV3].widen
    ).reduceLeft(_ or _)
}

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class QuxV1(i: Int) extends Foo
case class QuxV2(i: Int, d: Option[Double]) extends Foo
case class QuxV3(i: Int, d: Double) extends Foo
