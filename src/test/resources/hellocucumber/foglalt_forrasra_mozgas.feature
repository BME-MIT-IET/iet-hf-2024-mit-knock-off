# new feature
# Tags: optional

Feature: A szerelo akkor is forrasra tud mozogni, ha azon tartozkodnak

  Scenario: A szerelo megprobal ralepni a forrasra, amin tartozkodik egy masik szerelo, sikerrel jar.
    Given Egy tábla egy pumpaval ("p1") egy forassal ("f1"), koztuk egy csovel ("cs1").
      And Egy szerelo ("sz1"), ami "f1"-n all.
      And Egy szerelo ("sz2"), ami "cs1"-n all.
      When "sz2" szerelo megprobal ellepni az 1 mezore
      Then "sz2" mar "f1"-en tartozkodik