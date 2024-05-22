# new feature
# Tags: optional

Feature: A szerelo felvesz egy csoveget amin nem all senki

  Scenario: A szerelo megprobal felvenni egy csoveget, amin nem all senki, és sikerrel jar.
    Given Egy tábla egy pumpaval ("p1") egy forrassal ("f1"), koztuk egy csovel ("cs1").
      And Egy szerelo ("sz1"), ami "p1"-n all.
      When "sz1" szerelo megprobalja felvenni a szomszedos csovet
      Then "sz1" szerelonel van a "cs1" cso vege