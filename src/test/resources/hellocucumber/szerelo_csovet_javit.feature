# new feature
# Tags: optional

Feature: A szerelo megjavitja a torott csovet, ami ezutan nem torott

  Scenario: A szerelo megprobal megjavitani egy torott csovet, és sikerrel jar.
    Given Egy tábla egy pumpaval ("p1") egy forrassal ("f1"), koztuk egy csovel ("cs1").
      And A "cs1" cso torott.
      And Egy szerelo ("sz1"), ami "cs1"-n all.
      When "sz1" szerelo megprobalja megjavitani a mezot amin all
      Then "cs1" cso nem torott