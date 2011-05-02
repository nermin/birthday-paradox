import java.io.File
import sbt._
class BP (info: ProjectInfo) extends DefaultProject(info) {
  override def mainClass = Some("org.cs264.bp.BirthdayParadox")
  val zmqLib = new File("/usr/local/share/java/zmq.jar")
  val zmqLibPath = Path.fromFile(zmqLib)
  override def unmanagedClasspath = super.unmanagedClasspath +++ zmqLibPath
  System.setProperty("java.library.path", "/usr/local/lib")
  System.setProperty("request.bind", "5557")
  System.setProperty("response.bind", "5558")
  System.setProperty("worker.timeout.millis", "30000")
}