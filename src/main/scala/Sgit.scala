package sgit

import java.nio.file.{Paths, Files}
import java.nio.file.Path
import scala.util.control.Exception._

case class DirectoryPaths (
  base: Path,
  objects: Path,
  ref: Path
)

class Sgit(dirPaths: DirectoryPaths) {
  def init: Unit =  {
    if (Files.exists(Paths.get(".sgit"))) {
      throw new RuntimeException("Directory already exists")
    } else {
      buildObjects
      buildRefs
      buildHead
    }
  }

  def buildObjects = {
    Files.createDirectories(Paths.get(".sgit/objects/info"))
    Files.createDirectories(Paths.get(".sgit/objects/pack"))
  }

  def buildRefs = {
    Files.createDirectories(Paths.get(".sgit/refs/heads"))
    Files.createDirectories(Paths.get(".sgit/refs/tags"))
  }

  def buildHead = {
    Files.createFile(Paths.get(".sgit/HEAD"))
    val content: String = "ref: refs/heads/master"
    Files.write(Paths.get(".sgit/HEAD"), content.getBytes())
  }
}