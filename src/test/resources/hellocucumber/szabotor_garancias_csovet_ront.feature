# new feature
# Tags: optional

Feature: A szabotor nem tudja elrontani a csovet, mert foltozasi garancia van rajta

  Scenario: A szabotor megprobal elrontani egy csovet, de foltozasi garancia van rajta, ezert nem jar sikerrel.
    Given Egy t�bla egy pumpaval ("p1") egy forrassal ("f1"), koztuk egy csovel ("cs1").
      And Egy szerelo ("sz1"), ami "cs1"-n all.
      And Egy szabotor ("sz2"), ami "p1"-n all.
      And A "cs1" cso torott.
      And "sz1" szerelo megjavitja a mezot amin all
      And Eltelik egy kor.
      And "sz1" szerelo megprobal ellepni az 1 mezore
      And "sz2" szabotor megprobal ellepni az 0 mezore
      And Eltelik egy kor.
      When "sz2" szabotor megprobalja elrontani a csovet amin all
      Then "cs1" cso nem torott