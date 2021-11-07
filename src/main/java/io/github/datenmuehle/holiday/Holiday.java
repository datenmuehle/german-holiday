package io.github.datenmuehle.holiday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static io.github.datenmuehle.holiday.Bundesland.BW;
import static io.github.datenmuehle.holiday.Bundesland.BY;
import static io.github.datenmuehle.holiday.Bundesland.BE;
import static io.github.datenmuehle.holiday.Bundesland.BB;
import static io.github.datenmuehle.holiday.Bundesland.HB;
import static io.github.datenmuehle.holiday.Bundesland.HH;
import static io.github.datenmuehle.holiday.Bundesland.HE;
import static io.github.datenmuehle.holiday.Bundesland.MV;
import static io.github.datenmuehle.holiday.Bundesland.NI;
import static io.github.datenmuehle.holiday.Bundesland.NW;
import static io.github.datenmuehle.holiday.Bundesland.RP;
import static io.github.datenmuehle.holiday.Bundesland.SL;
import static io.github.datenmuehle.holiday.Bundesland.SN;
import static io.github.datenmuehle.holiday.Bundesland.ST;
import static io.github.datenmuehle.holiday.Bundesland.SH;
import static io.github.datenmuehle.holiday.Bundesland.TH;
import static io.github.datenmuehle.holiday.Bundesland.DE;

/**
 * Class to provide a list of german holidays
 */
public class Holiday
{
  private static List<Day> holidays;

  static {
    holidays = new ArrayList<>(Arrays.asList(
      new Day("Neujahr", 1, 1, DE),
      new Day("Heilige drei Könige", 6, 1, BW,BY,ST),
      new Day("Tag der Arbeit", 1, 5, DE),
      new Day("Mariä Himmelfahrt", 15, 8, SL),
      new Day("Weltkindertag", 20, 9, TH),
      new Day("Tag der Deutschen Einheit", 3, 10, DE),
      new Day("Reformationstag", 31, 10, BB,HB,HH,MV,NI,SN,ST,SH,TH),
      new Day("Allerheiligen", 1, 11, BW,BY,NW,RP,SL),
      new Day("1. Weihnachtstag", 25, 12, DE),
      new Day("2. Weihnachtstag", 26, 12, DE)
    ));
  }

  /**
   * Instantiates holidate object with current year.
   */
  public Holiday() {
    init(Calendar.getInstance().get(Calendar.YEAR));
  }

  /**
   * Instantiates the holidates object with the given year.
   * @param year the year
   */
  public Holiday(int year) {
    init(year);
  }

  /**
   * Gets the holiday list.
   *
   * @return the list of holidays
   */
  public static List<Day> getHolidays()
  {
    return new ArrayList<>(holidays);
  }

  /**
   * Indicates if date is a holiday.
   *
   * @param date the date to check
   * @return true if date is a holiday
   */
  public boolean isHoliday(Date date) {
    return holidays.stream().filter(d -> d.day == date.getDate() && d.month == date.getMonth()).findFirst().isPresent();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    holidays.stream().forEach(d -> builder.append(d + "\n"));
    return builder.toString();
  }

  private void init(int year)
  {
    LocalDate easter = getEaster(year);

    holidays.add(new Day("Ostersonntag", easter.getDayOfMonth(), easter.getMonth().getValue(), BB));
    holidays.add(getDayOfLocalDate("Karfreitag", easter, -2, DE));
    holidays.add(getDayOfLocalDate("Ostermontag", easter, 1, DE));
    holidays.add(getDayOfLocalDate("Christi Himmelfahrt", easter,39, DE));
    holidays.add(getDayOfLocalDate("Pfingstsonntag", easter, 49, BB));
    holidays.add(getDayOfLocalDate("Pfingstmontag", easter, 50, DE));
    holidays.add(getDayOfLocalDate("Fronleichnam", easter,60, BW,BY,HE,NW,RP,SL,SN,TH));
    holidays.add(new Day("Buß- und Bettag", getBussAndBettag(year).getDayOfMonth(), getBussAndBettag(year).getMonth().getValue(), SN));

    holidays.sort(Day::compareTo);
  }

  private Day getDayOfLocalDate(String name, LocalDate localDate, int days, Bundesland ...land)
  {
    LocalDate date = localDate.plusDays(days);
    return new Day(name, date.getDayOfMonth(), date.getMonth().getValue(), land);
  }

  private LocalDate getEaster(int year) {
    int x = year;
    int a = x % 19;
    int k = x / 100;
    int m = 15 + (3*k + 3) / 4 - (8*k + 13) / 25;
    int d = (19*a + m) % 30;
    int s = 2 - (3*k + 3) / 4;
    int r = (d + a / 11) / 29;
    int og = 21 + d - r;
    int sz = 7 - (x + x / 4 + s) % 7;
    int oe = 7 - (og - sz) % 7;
    int os = (og + oe); // ter März

    return LocalDate.of(x, (os>31 ? 4 : 3), (os>31 ? os-31 : os));
  }

  private static LocalDate getBussAndBettag(int year) {
    LocalDate ld = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), 12, 25);
    return ld.minusDays(ld.getDayOfWeek().getValue()+32);
  }
}
