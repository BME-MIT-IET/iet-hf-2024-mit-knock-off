# new feature
# Tags: optional

Feature: A szerelo nem tud felvenni pumpat, mert forrason all

  Scenario: A szerelo megprobal felvenni egy pumpat, de forrason all, ezert nem jar sikerrel.
    Given Egy tábla egy pumpaval ("p1") egy forrassal ("f1"), koztuk egy csovel ("cs1").
      And Egy szerelo ("sz1"), ami "f1"-n all.
      When "sz1" szerelo megprobal felvenni egy pumpat
      Then "sz1" szerelonel nincsen pumpa