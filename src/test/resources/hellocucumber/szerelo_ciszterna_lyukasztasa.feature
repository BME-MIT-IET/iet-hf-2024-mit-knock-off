# new feature
# Tags: optional

Feature: A szerelo nem tud egy ciszternat lyukasztani, mert nem lehet

  Scenario: A szerelo megprobal egy ciszternat lyukasztani, nem jar sikkerl
    Given Egy tábla egy ciszterna ("c1"), hozza csatolva egy csovel ("cs1").
      And Egy szerelo ("sz1"), ami "c1"-n all.
      When "sz1" szerelo megprobalja kilyukasztani a "c1" ciszternat
      Then A "c1" ciszterna ugyanugy tud vizet fogadni