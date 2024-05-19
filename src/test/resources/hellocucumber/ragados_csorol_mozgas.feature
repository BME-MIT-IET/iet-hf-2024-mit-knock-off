# new feature
# Tags: optional

Feature: A szerelo nem tud egy csorol elmozogni, ha az ragad

  Scenario: A szerelo megprobal elmozogni egy ragados csorol, nem jar sikerrel
    Given Egy tábla egy pumpa ("p1"), hozza csatolva egy csovel ("cs1"), ami ragad.
      And Egy szerelo ("sz1"), ami "cs1"-n all.
      When "sz1" szerelo megprobal ellepni az 1 mezore
      Then "sz1" marad "cs1"-en