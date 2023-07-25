package sharing

import com.spotify.common.uri.SpotifyUri

object EntityIdParsing {
  def main(args: Array[String]): Unit = {
    val entityUri = "https://playlistart.byspotify.com/"
    println(new SpotifyUri(entityUri).getKind.getCanonicalName)
  }
}
