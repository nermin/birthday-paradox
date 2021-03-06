package org.cs264.bp

import util.Random
import org.joda.time.DateTime
import net.rfas.GridMethods._
import java.io.{FileWriter, PrintWriter}

object BirthdayParadox {
  val rand = new Random(System.currentTimeMillis)

  def main(args: Array[String]) = {
    val numOfTrials = args(0).toInt
    val l = (2 to 100).toList
    val gl = l.grid
    val startTime = System.currentTimeMillis
    val results = gl.map(run(_, numOfTrials))
    val endTime = System.currentTimeMillis
    val percentages = results.map(_ / numOfTrials.toFloat * 100)
    writeCSV(l, percentages)
    println("Total time: " + (endTime - startTime)/1000F + "s.")
    System.exit(0)
  }

  private def writeCSV(groupSizes: List[Int], percentages: List[Float]) = {
    var pw: Option[PrintWriter] = None
    try {
      pw = Some(new PrintWriter(new FileWriter("output.csv")))
      val apw = pw.get
      apw.println("Group size, Probability")
      val data = groupSizes.zip(percentages)
      for (entry <- data) {
        apw.print(entry._1)
        apw.print(",")
        apw.printf("%5.2f", entry._2.asInstanceOf[AnyRef])
        apw.println
      }
      apw.flush
    } finally {
      if (pw.isDefined) pw.get.close
    }
  }

  private def run(groupSize: Int, numOfTrials: Int) = {
    val matches = for (i <- 1 to numOfTrials) yield simulate(groupSize)
    matches.count(_ == true)
  }

  private def simulate(groupSize: Int) = {
    val birthdays = for (i <- 1 to groupSize) yield generateBirthday
    anyRepeatedElems(birthdays)
  }

  private def generateBirthday = {
    val dt = new DateTime
    val yearDT = dt.withYear(rand.nextInt(71) + 1940)
    val monthDT = yearDT.withMonthOfYear(rand.nextInt(12) + 1)
    val birthday = monthDT.withDayOfMonth(rand.nextInt(monthDT.dayOfMonth.getMaximumValue) + 1)
    (birthday.getMonthOfYear, birthday.getDayOfMonth)
  }

  private def anyRepeatedElems(seq: IndexedSeq[(Int, Int)]) = seq.size != seq.distinct.size
}