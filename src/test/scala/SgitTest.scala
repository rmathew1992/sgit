import java.nio.file.{Paths, Files}
import java.io.File
import org.apache.commons.io.FileUtils
import org.scalatest.FunSpec

class SgitTest extends FunSpec {
  describe("Sgit") {
    describe("if directory is already initialized") {
      it("returns an error") {
        FileUtils.deleteDirectory(new File(".sgit"))
        Files.createDirectories(Paths.get(".sgit"))

        assertThrows[RuntimeException](Sgit.init)
      }
    }

    describe("if directory is not already initialized") {
      it("builds appropriate directories") {
        FileUtils.deleteDirectory(new File(".sgit"))
        assert(Files.exists(Paths.get(".sgit")) == false)

        Sgit.init

        assert(Files.exists(Paths.get(".sgit/objects/info")) == true)
        assert(Files.exists(Paths.get(".sgit/objects/pack")) == true)
      }

      it("builds refs file") {
        FileUtils.deleteDirectory(new File(".sgit"))
        assert(Files.exists(Paths.get(".sgit")) == false)

        Sgit.init

        assert(Files.exists(Paths.get(".sgit/refs/heads")) == true)
        assert(Files.exists(Paths.get(".sgit/refs/tags")) == true)
      }

      it("builds HEAD file") {
        assert(true)
      }
    }
  }
}
