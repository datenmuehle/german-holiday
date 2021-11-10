package io.github.datenmuehle.holiday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static io.github.datenmuehle.holiday.Bundesland.BW;
import static io.github.datenmuehle.holiday.Bundesland.BY;
//import static io.github.datenmuehle.holiday.Bundesland.BE; // NOSONAR: just for documentation
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
public class Holidays
{
  private List<Day> days;

  /**
   * Instantiates holidate object with current year.
   */
  public Holidays() {
    init(Calendar.getInstance().get(Calendar.YEAR));
  }

  /**
   * Instantiates the holidates object with the given year.
   * @param year the year
   */
  public Holidays(int year) {
    init(year);
  }

  /**
   * Gets the holiday list.
   *
   * @return the list of holidays
   */
  public List<Day> getDays()
  {
    return new ArrayList<>(days);
  }

  /**
   * Indicates if date is a holiday.
   *
   * @param date the date to check
   * @return true if date is a holiday
   */
  public boolean isHoliday(LocalDate date) {
    Optional<Day> day = days.stream().filter(d -> d.getDate().compareTo(date) == 0).findFirst();
    return day.isPresent();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    days.forEach(d -> builder.append(d).append("\n"));
    return builder.toString();
  }

  private void init(int year)
  {
    days = new ArrayList<>(Arrays.asList(
      new Day("Neujahr", 1, 1, year, DE),
      new Day("Heilige drei Könige", 6, 1, year, BW,BY,ST),
      new Day("Tag der Arbeit", 1, 5, year, DE),
      new Day("Mariä Himmelfahrt", 15, 8, year, SL),
      new Day("Weltkindertag", 20, 9, year, TH),
      new Day("Tag der Deutschen Einheit", 3, 10, year, DE),
      new Day("Reformationstag", 31, 10, year, BB,HB,HH,MV,NI,SN,ST,SH,TH),
      new Day("Allerheiligen", 1, 11, year, BW,BY,NW,RP,SL),
      new Day("1. Weihnachtstag", 25, 12, year, DE),
      new Day("2. Weihnachtstag", 26, 12, year, DE)
    ));

    LocalDate easter = getEaster(year);

    days.add(new Day("Ostersonntag", easter.getDayOfMonth(), easter.getMonth().getValue(), year, BB));
    days.add(getDayOfLocalDate("Karfreitag", easter, -2, DE));
    days.add(getDayOfLocalDate("Ostermontag", easter, 1, DE));
    days.add(getDayOfLocalDate("Christi Himmelfahrt", easter,39, DE));
    days.add(getDayOfLocalDate("Pfingstsonntag", easter, 49, BB));
    days.add(getDayOfLocalDate("Pfingstmontag", easter, 50, DE));
    days.add(getDayOfLocalDate("Fronleichnam", easter,60, BW,BY,HE,NW,RP,SL,SN,TH));
    days.add(new Day("Buß- und Bettag", getBussAndBettag(year), SN));

    days.sort(Day::compareTo);
  }

  private Day getDayOfLocalDate(String name, LocalDate localDate, int days, Bundesland ...land)
  {
    LocalDate date = localDate.plusDays(days);
    return new Day(name, date, land);
  }

  private LocalDate getEaster(int year) {
    int a = year % 19;
    int k = year / 100;
    int m = 15 + (3*k + 3) / 4 - (8*k + 13) / 25;
    int d = (19*a + m) % 30;
    int s = 2 - (3*k + 3) / 4;
    int r = (d + a / 11) / 29;
    int og = 21 + d - r;
    int sz = 7 - (year + year / 4 + s) % 7;
    int oe = 7 - (og - sz) % 7;
    int os = (og + oe); // ter März

    return LocalDate.of(year, (os>31 ? 4 : 3), (os>31 ? os-31 : os));
  }

  private static LocalDate getBussAndBettag(int year) {
    LocalDate ld = LocalDate.of(year, 12, 25);
    return ld.minusDays(ld.getDayOfWeek().getValue()+32L);
  }
}
