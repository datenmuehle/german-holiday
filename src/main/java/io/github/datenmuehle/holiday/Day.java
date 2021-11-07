package io.github.datenmuehle.holiday;

import java.util.Arrays;
import java.util.List;

/**
 * Day class representing concrete day.
 */
public class Day
{
  String name;
  int day;
  int month;

  final List<Bundesland> bundeslandList;

  public Day(String name, int day, int month, Bundesland ...land) {
    this.name = name;
    this.day = day;
    this.month = month;
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

  public int getDay()
  {
    return day;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public int getMonth()
  {
    return month;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public List<Bundesland> getBundeslandList()
  {
    return bundeslandList;
  }

  public int compareTo(Day o) {
    int mc = month - o.getMonth();

    if (mc != 0) {
      return mc;
    }

    int dc = day - o.getDay();

    if (dc != 0) {
      return dc;
    }

    return name.compareTo(o.getName());
  }

  @Override
  public String toString() {
    return String.format("%s: %02d.%02d.", name, day, month);
  }
}
