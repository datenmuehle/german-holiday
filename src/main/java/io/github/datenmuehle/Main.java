package io.github.datenmuehle;

import io.github.datenmuehle.holiday.Holiday;

import java.util.Date;

public class Main
{
  public static void main(String[] args) {
    Holiday holiday = new Holiday();
    System.out.println(holiday);

    System.out.println("is holiday: " + holiday.isHoliday(new Date()));
  }
}
