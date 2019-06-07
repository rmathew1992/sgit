package sgit

import java.nio.file.{Paths, Files}
import java.io.File
import org.apache.commons.io.FileUtils
import org.scalatest.FunSpec
import org.scalatest.{BeforeAndAfterEach, Suite}

class SgitTest extends FunSpec with BeforeAndAfterEach {
  afterEach
  describe("Sgit") {
    describe("if directory is already initialized") {
      it("returns an error") {
        FileUtils.deleteDirectory(new File(".sgit"))
        Files.createDirectories(Paths.get(".sgit"))
        val paths = DirectoryPaths(
          Paths.get(".sgit"),
          Paths.get(".sgit/objects"),
          Paths.get(".sgit/refs" )
        )

        val sgit = new Sgit(paths)

        assertThrows[RuntimeException](sgit.init)
      }
    }

    describe("if directory is not already initialized") {
      it("builds appropriate directories") {
        FileUtils.deleteDirectory(new File(".sgit"))
        assert(Files.exists(Paths.get(".sgit")) == false)

        val paths = DirectoryPaths(
          Paths.get(".sgit"),
          Paths.get(".sgit/objects"),
          Paths.get(".sgit/refs" )
        )

        val sgit = new Sgit(paths)

        sgit.init

        assert(Files.exists(Paths.get(".sgit/objects/info")) == true)
        assert(Files.exists(Paths.get(".sgit/objects/pack")) == true)
      }

      it("builds refs file") {
        FileUtils.deleteDirectory(new File(".sgit"))
        assert(Files.exists(Paths.get(".sgit")) == false)

        val paths = DirectoryPaths(
          Paths.get(".sgit"),
          Paths.get(".sgit/objects"),
          Paths.get(".sgit/refs" )
        )

        val sgit = new Sgit(paths)

        sgit.init

        assert(Files.exists(Paths.get(".sgit/refs/heads")) == true)
        assert(Files.exists(Paths.get(".sgit/refs/tags")) == true)
      }

      it("builds HEAD file") {
        assert(true)
      }
    }
  }
}
