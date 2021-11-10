package io.github.datenmuehle;

import io.github.datenmuehle.holiday.Day;
import io.github.datenmuehle.holiday.Holidays;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class HolidaysTest
{
  @Test
  public void testIsAllerheiligen() {
    Holidays holidays = new Holidays(2021);
    Assert.assertTrue(holidays.isHoliday(LocalDate.of(2021,11,1)));
  }

  @Test
  public void testEasterAt2027() {
    Holidays holidays = new Holidays(2027);
    Assert.assertTrue(holidays.isHoliday(LocalDate.of(2027,3,28)));
  }

  @Test
  public void testGetListOfHolidaysIn2030() {
    Holidays holidays = new Holidays(2030);
    List<Day> days = holidays.getDays();

    days.forEach(d -> System.out.println(d.toString()));

    Assert.assertTrue(days.size() > 0);
  }
}
