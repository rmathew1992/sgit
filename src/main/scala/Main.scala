package sgit

import java.nio.file.Paths
import scala.concurrent.Await

object Main extends App {
  val paths = DirectoryPaths(
    Paths.get(".sgit"),
    Paths.get(".sgit/objects"),
    Paths.get(".sgit/refs" )
  )

  val sgit = new Sgit(paths)

  (args(0)) match {
    case "init" => sgit.init
    case _ => null
  }
}
