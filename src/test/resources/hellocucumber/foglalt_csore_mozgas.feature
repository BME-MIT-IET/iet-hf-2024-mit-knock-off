# new feature
# Tags: optional

Feature: A szabotor nem tud egy csore mozogni, amikor azon mar tartozkodik egy masik szabotor

  Scenario: A szabotor megprobal ralepni a csore, amin tartozkodik egy  szerelo,nem jar  sikerrel.
    Given Egy tábla egy forassal ("f1"), hozza csatolva egy csovel ("cs1").
      And Egy szerelo ("sz1"), ami "f1"-n all.
      And Egy szerelo ("sz2"), ami "cs1"-n all.
      When "sz2" szerelo megprobal ellepni az 1 mezore
      Then "sz1" marad "f1"-en