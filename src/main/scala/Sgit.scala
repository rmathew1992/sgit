import java.nio.file.{Paths, Files}
import scala.util.control.Exception._

object Sgit {
  def init: Unit =  {
    if (Files.exists(Paths.get(".sgit"))) {
      throw new RuntimeException("Directory already exists")
    } else {
      Files.createDirectories(Paths.get(".sgit/objects/info"))
      Files.createDirectories(Paths.get(".sgit/objects/pack"))
      Files.createDirectories(Paths.get(".sgit/refs/heads"))
      Files.createDirectories(Paths.get(".sgit/refs/tags"))
      Files.createFile(Paths.get(".sgit/HEAD"))
      val content: String = "ref: refs/heads/master"
      Files.write(Paths.get(".sgit/HEAD"), content.getBytes())
    }
  }
}