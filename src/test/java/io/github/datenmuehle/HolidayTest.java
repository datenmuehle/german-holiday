package io.github.datenmuehle;

import io.github.datenmuehle.holiday.Day;
import io.github.datenmuehle.holiday.Holiday;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class HolidayTest
{
  @Test
  public void testIsAllerheiligen() {
    Holiday holiday = new Holiday(2021);
    Assert.assertTrue(holiday.isHoliday(LocalDate.of(2021,11,1)));
  }

  @Test
  public void testEasterAt2027() {
    Holiday holiday = new Holiday(2027);
    Assert.assertTrue(holiday.isHoliday(LocalDate.of(2027,3,28)));
  }

  @Test
  public void testGetListOfHolidaysIn2030() {
    Holiday holiday = new Holiday(2030);
    List<Day> holidays = holiday.getHolidays();

    holidays.forEach(d -> System.out.println(d.toString()));

    Assert.assertTrue(holidays.size() > 0);
  }
}
