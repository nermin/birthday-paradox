import sbt._
class BP (info: ProjectInfo) extends DefaultProject(info) {
  override def mainClass = Some("org.cs264.bp.BirthdayParadox")
}