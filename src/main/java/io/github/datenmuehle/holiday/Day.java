package io.github.datenmuehle.holiday;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Day class representing concrete day.
 */
public class Day
{
  String name;

  LocalDate date;

  final List<Bundesland> bundeslandList;

  /**
   * Instantiates a new Day.
   *
   * @param name Name of day
   * @param day day of month
   * @param month month
   * @param land country where day is a holiday
   */
  public Day(String name, int day, int month, Bundesland ...land) {
    this.name = name;
    this.date = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), month, day);
    bundeslandList = Arrays.asList(land);
  }

  /**
   * Instantiates a new Day.
   *
   * @param name Name of day
   * @param day day of month
   * @param month month
   * @param year year
   * @param land country where day is a holiday
   */
  public Day(String name, int day, int month, int year, Bundesland ...land) {
    this.name = name;
    this.date = LocalDate.of(year, month, day);
    bundeslandList = Arrays.asList(land);
  }

  /**
   * Instantiates a new Day.
   *
   * @param name Name of the Day
   * @param date the date
   * @param land country where day is a holiday
   */
  public Day(String name, LocalDate date, Bundesland ...land) {
    this.name = name;
    this.date = date;
    bundeslandList = Arrays.asList(land);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<Bundesland> getBundeslandList()
  {
    return bundeslandList;
  }

  public LocalDate getDate()
  {
    return date;
  }

  public int compareTo(Day o) {
    return date.compareTo(o.getDate());
  }

  @Override
  public String toString() {
    return String.format("%s: %02d.%02d.", name, date.getDayOfMonth(), date.getMonth().getValue());
  }
}
