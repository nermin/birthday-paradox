package org.cs264.bp

import util.Random
import org.joda.time.DateTime

object BirthdayParadox {
  val NUM_OF_TRIALS = 100000
  val rand = new Random(System.currentTimeMillis)

  def main(args: Array[String]) = {
    var numOfMatches = 0
    for (i <- 1 to NUM_OF_TRIALS) {
      if (simulate(23)) numOfMatches += 1
    }
    println(numOfMatches)
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