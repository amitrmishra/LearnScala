package serde

import com.spotify.sparkey.{CompressionType, Sparkey}
import org.apache.commons.lang3.SerializationUtils
import serde.apache.Model.{Classification => ApacheClassification}
import serde.apache.{Model => ApacheModel}
import serde.proto.{Model => ProtoModel}

import java.io.File
import java.time.Instant

object App {
  def main(args: Array[String]): Unit = {
    val key1: String = "accessory_1"
    val classifications1: Array[ApacheClassification] = Array(
      new ApacheClassification("car", 1),
      new ApacheClassification("headphones", 100)
    )
    val value1: ApacheModel =
      new ApacheModel(classifications1, java.util.Date.from(Instant.now()))

    val key2: String = "accessory_2"
    val value2: ProtoModel = ProtoModel
      .newBuilder()
      .addClassifications(
        0,
        ProtoModel.Classification
          .newBuilder()
          .setAccessoryType("speaker")
//          .setDau(10)
          .build()
      )
      .addClassifications(
        1,
        ProtoModel.Classification
          .newBuilder()
          .setAccessoryType("wearable")
//          .setDau(10)
          .build()
      )
      .setDateAdded(com.google.protobuf.Timestamp.newBuilder().setSeconds(10))
      .build()

    import java.nio.file.Files
    import java.nio.file.Paths
    val tmpdir = "/tmp/sparkey"
    Files.createDirectories(Paths.get(tmpdir))

    val sparkeyWriter = Sparkey.createNew(
      new File(tmpdir + "/data"),
      CompressionType.SNAPPY,
      512
    )
    sparkeyWriter.put(key1, value1.toString)
//    sparkeyWriter.put(key1.getBytes(), SerializationUtils.serialize(value1))
    sparkeyWriter.put(key2.getBytes(), value2.toByteArray)
    sparkeyWriter.flush()
    sparkeyWriter.writeHash()
    sparkeyWriter.close()

  }
}
