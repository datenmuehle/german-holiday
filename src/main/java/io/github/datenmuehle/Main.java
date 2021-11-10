package io.github.datenmuehle;

import io.github.datenmuehle.holiday.Holidays;

import java.util.Calendar;

public class Main
{
  public static void main(String[] args) {

    int year = Calendar.getInstance().get(Calendar.YEAR);

    if (args.length > 0) {
      year = Integer.parseInt(args[0]);
    }

    Holidays holidays = new Holidays(year);
    System.out.println(holidays); // NOSONAR: example code
  }
}
