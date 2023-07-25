package platformparser

import com.spotify.data.platform.DeviceParser

object ReverseLookup {
  def main(args: Array[String]): Unit = {
    DeviceParser.defaultDeviceSpec
      .filter { device =>
        device.deviceType
          .contains("mobile") || device.deviceType.contains("desktop")
      }
      .foreach { d =>
        println(d)
        println
      }
  }
}
