package io.github.datenmuehle.holiday;

/**
 * List of germanies federated states.
 */
public enum Bundesland
{
  BW("Badenwürtemberg"),
  BY("Bayern"),
  BE("Berlin"),
  BB("Brandenburg"),
  HB("Bremen"),
  HH("Hamburg"),
  HE("Hessen"),
  MV("Mecklenburg-Vorpommern"),
  NI("Niedersachsen"),
  NW("Nordreihn-Westfalen"),
  RP("Rheinland-Pfalz"),
  SL("Saarland"),
  SN("Saschen"),
  ST("Sachsenanhalt"),
  SH("Schleswig-Holstein"),
  TH("Thüringen"),
  DE("Bundesrepublik Deutschland")
  ;

  private final String value;

  Bundesland(String value) {
    this.value = value;
  }

  public String getValue()
  {
    return value;
  }
}
