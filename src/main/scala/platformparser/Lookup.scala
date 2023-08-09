package platformparser

import com.spotify.data.platform.DeviceParser

object Lookup {
  def main(args: Array[String]): Unit = {
    val parser = DeviceParser()
    println(
      parser.parseTyped(
        "Partnergooglecast_voice;Google_Nest_Mini;;5.28.59--1.56.500000"
      )
    )
    println(
      parser.parseTyped(
        "Partner google cast_voice;Google_Nest_Mini;;5.28.59--1.56.500000"
      )
    )
    println(
      parser.parseTyped(
        "Partnergooglecast_voice;Google_Nest_Mini;d7df0887fb71494ea994202cb473eae7;;5.28.59--1.56.500000"
      )
    )
    println(
      parser.parseTyped(
        "Partner google cast_voice;Google_Nest_Mini;d7df0887fb71494ea994202cb473eae7;;5.28.59--1.56.500000"
      )
    )
  }
}
