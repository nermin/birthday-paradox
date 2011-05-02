package org.cs264.bp

import util.Random
import org.joda.time.DateTime
import net.rfas.GridMethods._

object BirthdayParadox {
  val NUM_OF_TRIALS = 10000
  val rand = new Random(System.currentTimeMillis)

  def main(args: Array[String]) = {
    val l = (2 to 100).toList
    val gl = l.grid
    val results = gl.map(run(_))
    val percentages = results.map(_ / NUM_OF_TRIALS.toFloat * 100)
    println(percentages)
  }

  private def run(groupSize: Int) = {
    val matches = for (i <- 1 to NUM_OF_TRIALS) yield simulate(groupSize)
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